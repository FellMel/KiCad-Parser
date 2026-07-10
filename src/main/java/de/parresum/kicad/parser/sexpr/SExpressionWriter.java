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
import java.io.Writer;

/**
 * Simple writer to write SExpr files
 */
public class SExpressionWriter {
   /** Type last written */
   private enum State {
      NONE, START, ATTRIBUTE, ELEMENT
   }

   /** Writer to write to */
   private final Writer writer;
   /** level of indentation */
   private int indent = 0;

   /** type of element last written */
   private State lastWritten = State.NONE;

   /**
    * Creates a writer
    *
    * @param writer writer to write to
    */
   public SExpressionWriter(final Writer writer) {
      this.writer = writer;
   }

   /**
    * Starts an element
    *
    * @param name name of the element to write
    * @throws IOException when an error occurs
    */
   public void startNode(final String name) throws IOException {
      writeIndent(State.ELEMENT);
      writer.write("(");
      writer.write(name);
      indent++;
      lastWritten = State.START;
   }

   /**
    * Close an element
    *
    * @throws IOException when an error occurs
    */
   public void endNode() throws IOException {
      indent--;
      writeIndent(State.NONE);
      writer.write(")");
      lastWritten = State.ELEMENT;
   }

   /**
    * Writes a simple value as Element
    *
    * @param name  Name of the element
    * @param value value of the element
    * @throws IOException when an error occurs
    */
   public void writeAtom(final String name, final String value) throws IOException {
      writeAtom(name, value, false);
   }

   /**
    * Writes a simple value as Element
    *
    * @param name     Name of the element
    * @param value    value of the element
    * @param optional true, when the element should be suppressed if value is null or empty
    * @throws IOException when an error occurs
    */
   public void writeAtom(final String name, final String value, final boolean optional) throws IOException {
      if (optional && (value == null || value.isEmpty())) {
         return;
      }

      writeIndent(State.ELEMENT);
      writer.write(String.format("(%s %s)", name, value));
      lastWritten = State.ELEMENT;

   }

   /**
    * Writes a simple boolean value as element
    *
    * @param name  name of the element
    * @param value value to write
    * @throws IOException when an error occurs
    */
   public void writeAtom(final String name, final boolean value) throws IOException {

      writeIndent(State.ELEMENT);
      writer.write(String.format("(%s %s)", name, value ? "yes" : "no"));
      lastWritten = State.ELEMENT;
   }

   /**
    * Writes a list of values as element
    *
    * @param name   name of the Element
    * @param values values to write
    * @throws IOException when an error occurs
    */
   public void writeAtom(final String name, final String... values) throws IOException {

      writeIndent(State.ELEMENT);
      writer.write(String.format("(%s", name));
      for (final String val : values) {
         writer.write(" ");
         writer.write(val);
      }
      writer.write(")");
      lastWritten = State.ELEMENT;

   }

   /**
    * writes a String value (quoted) as element
    *
    * @param name  name of the element
    * @param value value to write
    * @throws IOException when an error occurs
    */
   public void writeStringAtom(final String name, final String value) throws IOException {
      writeStringAtom(name, value, false);
   }

   /**
    * writes a String value (quoted) as element
    *
    * @param name     name of the element
    * @param value    value to write
    * @param optional true, when the element should be suppressed if value is null or empty
    * @throws IOException when an error occurs
    */
   public void writeStringAtom(final String name, final String value, final boolean optional) throws IOException {
      if (optional && (value == null || value.isEmpty())) {
         return;
      }

      writeIndent(State.ELEMENT);
      writer.write(String.format("(%s %s)", name, escape(value)));
      lastWritten = State.ELEMENT;
   }

   /**
    * Writes a String argument
    *
    * @param value
    * @throws IOException
    */
   public void writeStringValue(final String value) throws IOException {

      writeIndent(State.ATTRIBUTE);
      writer.write(value);
   }

   /**
    * Writes iindentation, newlines or other spaces
    *
    * @param next Type of next element to write
    * @throws IOException when an error occurs
    */
   private void writeIndent(final State next) throws IOException {
      switch (lastWritten) {
         case NONE:
            return;

         case START: // Fall through
         case ATTRIBUTE:
            if (next == State.ATTRIBUTE) {
               writer.write(" ");
               return;
            }
            if (next == State.NONE) {
               return;
            }
            break;

         case ELEMENT:
            break;
      }

      writer.write("\n");
      for (int i = 0; i < indent; i++) {
         writer.write("  ");
      }
   }

   /**
    * Quote and Escape a sting
    *
    * @param value string to quote
    * @return quoted string
    */
   public static String escape(final String value) {
      if (value == null) {
         return "\"\"";
      }
      return "\"" + value.replace("\\", "\\\\")//
            .replace("\"", "\\\"")//
            .replace("\n", "\\n") + "\"";
   }

}
