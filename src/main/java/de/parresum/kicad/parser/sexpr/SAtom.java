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

/**
 * Represents a single scalar string or symbol in KiCad S-Expressions.
 */
public class SAtom extends SNode {
   /** the value of the atom */
   private final String value;

   /** flag signaling the value is a string (quoted in file) */
   private final boolean quoted;

   /**
    * creates a new atom
    *
    * @param value  the value of the atom
    * @param quoted true, when the value is treated as string
    */
   public SAtom(final String value, final boolean quoted) {
      this.value = value != null ? value : "";
      this.quoted = quoted;
   }

   @Override
   public boolean isAtom() {
      return true;
   }

   /**
    * tells whether the atom is a string
    *
    * @return true, when value was quoted
    */
   public boolean isString() {
      return quoted;
   }

   @Override
   public SAtom asAtom() {
      return this;
   }

   @Override
   public String toString() {
      // Safe formatting: wrap in quotes and escape if it contains spaces or structural S-Expression characters
      if (value.isEmpty() || value.contains(" ") || value.contains("(") || value.contains(")") || value.contains("\"")
            || value.contains("\\") || value.contains("\n")) {
         return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n") + "\"";
      }
      return value;
   }

   /**
    * returns the value of the atom as string
    *
    * @param defaultValue default value, when value is not set
    * @return the value
    */
   public String asString(final String defaultValue) {
      if (value == null) {
         return defaultValue;
      }
      return value;

   }

   /**
    * returns the value of the atom as double
    *
    * @param defaultValue default value, when value is not set or not parseable as double
    * @return the value
    */
   public double asDouble(final double defaultValue) {
      if (value == null) {
         return defaultValue;
      }
      try {
         return Double.parseDouble(value);
      } catch (final NumberFormatException e) {
         return defaultValue;
      }

   }

   /**
    * returns the value of the atom as integer
    *
    * @param defaultValue default value, when value is not set or not parseable as integer
    * @return the value
    */
   public int asInt(final int defaultValue) {
      if (value == null) {
         return defaultValue;
      }
      try {
         return Integer.parseInt(value);
      } catch (final NumberFormatException e) {
         return defaultValue;
      }
   }

   /**
    * returns the value of the atom as boolean
    *
    * @param defaultValue default value, when value is not set
    * @return the value
    */
   public boolean asBoolean(final boolean defaultValue) {
      if (value == null) {
         return defaultValue;
      }
      return "yes".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
   }

}
