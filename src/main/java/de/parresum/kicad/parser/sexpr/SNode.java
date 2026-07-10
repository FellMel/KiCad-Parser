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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base abstract class for KiCad S-Expression Nodes. Designed for high-performance traversals and type-safe value
 * retrieval.
 */
public abstract class SNode {
   /**
    * Tells whether the node is an atom.
    *
    * @return true, when node is an atom
    */
   public boolean isAtom() {
      return false;
   }

   /**
    * Tells, whether the node is a list.
    *
    * @return true, when node is a list
    */
   public boolean isList() {
      return false;
   }

   /**
    * returns the node as an atom
    *
    * @return the casted node
    * @throws UnsupportedOperationException when node is not an atom
    */
   public SAtom asAtom() {
      throw new UnsupportedOperationException("Not an Atom node");
   }

   /**
    * returns the content of the node as a list
    *
    * @return the List of sub nodes. When node is an atom, the list is empty
    */
   public List<SNode> asList() {
      return Collections.emptyList();
   }

   /**
    * gets the n-th sub element of the node
    *
    * @param i the index of the element to return
    * @return the element or null, when not exists
    */
   public SNode get(final int i) {
      return null;
   }

   /**
    * If this node is a list and contains at least one Atom, returns its value. Useful for identifying nodes like (wire
    * ...), (symbol ...)
    *
    * @return name of the element
    */
   public String getName() {
      return "";
   }

   /**
    * Searches for a sub-list whose first element matches the specified key. E.g. get("at") on a symbol node returns the
    * (at X Y Angle) node.
    *
    * @param name name of the element to search for
    * @return the node with the given name or null, when not found
    */
   public SNode get(final String key) {
      if (!isList()) {
         return null;
      }
      for (final SNode child : asList()) {
         if (child.isList() && !child.asList().isEmpty()) {
            final SNode head = child.asList().get(0);
            if (head.isAtom() && key.equals(head.asAtom())) {
               return child;
            }
         }
      }
      return null;
   }

   /**
    * Searches for all sub-lists whose first element matches the specified key.
    *
    * @param name name of the element to search for
    * @return the list of nodes with the given name. Could be empty, when no elements found
    */
   public List<SNode> getAll(final String key) {
      if (!isList()) {
         return Collections.emptyList();
      }
      final List<SNode> results = new ArrayList<>();
      for (final SNode child : asList()) {
         if (child.isList() && !child.asList().isEmpty()) {
            final SNode head = child.asList().get(0);
            if (head.isAtom() && key.equals(head.asAtom())) {
               results.add(child);
            }
         }
      }
      return results;
   }

   /**
    * Gets the string value of a named sub-property. E.g. getString("generator", null) inside (generator eeschema)
    * returns "eeschema".
    *
    * @param key          the name of the element to search for
    * @param defaultValue the value returned, when the element wasn't found.
    */
   public String getString(final String key, final String defaultValue) {
      final SNode node = get(key);
      if (node != null && node.isList() && node.asList().size() > 1) {
         final SNode valNode = node.asList().get(1);
         if (valNode.isAtom()) {
            return valNode.asAtom().asString(defaultValue);
         }
      }
      return defaultValue;
   }

   /**
    * Gets the double value of a named sub-property.
    *
    * @param key          the name of the element to search for
    * @param defaultValue the value returned, when the element wasn't found.
    */
   public double getDouble(final String key, final double defaultValue) {
      final String val = getString(key, null);
      if (val == null) {
         return defaultValue;
      }
      try {
         return Double.parseDouble(val);
      } catch (final NumberFormatException e) {
         return defaultValue;
      }
   }

   /**
    * Gets the integer value of a named sub-property.
    *
    * @param key          the name of the element to search for
    * @param defaultValue the value returned, when the element wasn't found.
    */
   public int getInt(final String key, final int defaultValue) {
      final String val = getString(key, null);
      if (val == null) {
         return defaultValue;
      }
      try {
         return Integer.parseInt(val);
      } catch (final NumberFormatException e) {
         return defaultValue;
      }
   }

   /**
    * Gets the boolean value of a named sub-property (e.g., matching "yes"/"no" or "true"/"false").
    *
    * @param key          the name of the element to search for
    * @param defaultValue the value returned, when the element wasn't found.
    */
   public boolean getBoolean(final String key, final boolean defaultValue) {
      final String val = getString(key, null);
      if (val == null) {
         return defaultValue;
      }
      return "yes".equalsIgnoreCase(val) || "true".equalsIgnoreCase(val);
   }
}
