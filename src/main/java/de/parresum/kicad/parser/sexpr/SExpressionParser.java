/*
 *     Copyright 2026 Parresum Soft @ http://parresum.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.parresum.kicad.parser.sexpr;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import de.parresum.kicad.parser.sexpr.reflection.SExprClassHelper;

/**
 * Extremely high-performance, lightweight, and streaming S-Expression parser. Reads directly from a Reader
 * character-by-character to maximize speed and minimize memory allocations.
 */
public class SExpressionParser {

   /**
    * parse from a string to SNodes
    *
    * @param input string to parse from
    * @return the root node read
    * @throws IOException when an error occours
    */
   public static SNode parse(final String input) throws IOException {
      return parse(new StringReader(input));
   }

   /**
    * parse from a reader to SNodes
    *
    * @param input reader to parse from
    * @return the root node read
    * @throws IOException when an error occours
    */
   public static SNode parse(final Reader reader) throws IOException {
      final Lexer lexer = new Lexer(reader);
      final Token token = lexer.nextToken();
      if (token == null) {
         return null;
      }
      if (token.type != TokenType.LPAREN) {
         throw new IllegalArgumentException(
               "Invalid S-Expression format: must start with '(' at line " + lexer.getLine());
      }
      return parseList(lexer);
   }

   /**
    * Parse from a String to an Object
    *
    * @param <T>    Type of Object to parse to
    *
    * @param input  String to read from
    * @param target Object to fill with parsed content
    * @return the parsed object
    * @throws IOException when an error occours
    */
   public static <T> T parse(final String input, final T target) throws IOException {
      return parse(new StringReader(input), target);
   }

   /**
    * Parse from a Reader to an Object
    *
    * @param <T>    Type of Object to parse to
    *
    * @param input  Reader to read from
    * @param target Object to fill with parsed content
    * @return the parsed object
    * @throws IOException when an error occours
    */
   public static <T> T parse(final Reader reader, final T target) throws IOException {
      try {
         final SNode tree = parse(reader);
         SExprClassHelper.parse(tree, target);
         return target;

      } catch (ParseException | NoSuchMethodException | SecurityException | IllegalAccessException
            | InstantiationException | InvocationTargetException e) {
         throw new RuntimeException("Error parsing S-Expression stream", e);
      }
   }

   /**
    * Parse to SNodes
    *
    * @param lexer lexer to tokenize input
    * @return Parsed S-Nodes
    * @throws IOException when an error occours
    */
   private static SList parseList(final Lexer lexer) throws IOException {
      final SList list = new SList();
      while (true) {
         final Token token = lexer.nextToken();
         if (token == null) {
            throw new IllegalArgumentException(
                  "Unexpected end of file. Unmatched parentheses at line " + lexer.getLine());
         }
         if (token.type == TokenType.RPAREN) {
            break;
         }
         if (token.type == TokenType.LPAREN) {
            list.add(parseList(lexer));
         } else if (token.type == TokenType.ATOM) {
            list.add(new SAtom(token.value, false));
         } else if (token.type == TokenType.SATOM) {
            list.add(new SAtom(token.value, true));
         }
      }
      return list;
   }

   /**
    * Recognized Token types
    */
   private enum TokenType {
      /** Left parenthese */
      LPAREN,
      /** Right parentthese */
      RPAREN,
      /** Smple Atom */
      ATOM,

      /** String atom */
      SATOM
   }

   /**
    * Token, read from input
    */
   private static class Token {
      /** Token type */
      final TokenType type;
      /** Token value */
      final String value;

      Token(final TokenType type, final String value) {
         this.type = type;
         this.value = value;
      }
   }

   /** Tokenizer to read input */
   private static class Lexer {
      /** Reader to read input from */
      private final Reader reader;

      /** last char read */
      private int lastChar = ' ';

      /** line counter */
      private int line = 1;

      /** End Of File */
      private boolean eof = false;

      Lexer(final Reader reader) throws IOException {
         this.reader = reader;
         readNext();
      }

      int getLine() {
         return line;
      }

      private void readNext() throws IOException {
         if (eof) {
            return;
         }
         lastChar = reader.read();
         if (lastChar == -1) {
            eof = true;
         } else if (lastChar == '\n') {
            line++;
         }
      }

      Token nextToken() throws IOException {
         // Skip whitespaces and comments starting with '#' (if any)
         while (!eof) {
            if (Character.isWhitespace(lastChar)) {
               readNext();
            } else if (lastChar == '#') { // Comment support
               while (!eof && lastChar != '\n' && lastChar != '\n') {
                  readNext();
               }
            } else {
               break;
            }
         }

         if (eof) {
            return null;
         }

         if (lastChar == '(') {
            readNext();
            return new Token(TokenType.LPAREN, "(");
         }
         if (lastChar == ')') {
            readNext();
            return new Token(TokenType.RPAREN, ")");
         }

         if (lastChar == '"') {
            return parseQuotedString();
         }

         return parseUnquotedSymbol();
      }

      private Token parseQuotedString() throws IOException {
         final StringBuilder sb = new StringBuilder();
         readNext(); // consume opening quote '"'
         while (!eof && lastChar != '"') {
            if (lastChar == '\\') {
               readNext();
               if (eof) {
                  break;
               }
               if (lastChar == 'n') {
                  sb.append('\n');
               } else if (lastChar == 'r') {
                  sb.append('\n');
               } else if (lastChar == 't') {
                  sb.append('	');
               } else {
                  sb.append((char) lastChar);
               }
            } else {
               sb.append((char) lastChar);
            }
            readNext();
         }
         if (!eof) {
            readNext(); // consume closing quote '"'
         }
         return new Token(TokenType.SATOM, sb.toString());
      }

      private Token parseUnquotedSymbol() throws IOException {
         final StringBuilder sb = new StringBuilder();
         // read until parenthese or white space
         while (!eof && lastChar != '(' && lastChar != ')' && !Character.isWhitespace(lastChar)) {
            sb.append((char) lastChar);
            readNext();
         }
         return new Token(TokenType.ATOM, sb.toString());
      }
   }
}
