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
import java.util.List;

/**
 * Represents a nested S-Expression list enclosed in parenthesis. Example: (paper "A4")
 */
public class SList extends SNode {
   /**
    * List of children inside this List
    */
   private final List<SNode> children = new ArrayList<>();

   @Override
   public boolean isList() {
      return true;
   }

   @Override
   public List<SNode> asList() {
      return children;
   }

   @Override
   public SNode get(final int i) {
      if (i >= 0 && i < children.size()) {
         return children.get(i);
      }
      return null;
   }

   @Override
   public String getName() {
      if (!children.isEmpty() && children.get(0).isAtom()) {
         return children.get(0).asAtom().toString();
      }
      return "";
   }

   /**
    * adds a node to the list
    *
    * @param node node to add
    */
   public void add(final SNode node) {
      if (node != null) {
         children.add(node);
      }
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder();
      sb.append("(");
      for (int i = 0; i < children.size(); i++) {
         if (i > 0) {
            sb.append(" ");
         }
         sb.append(children.get(i).toString());
      }
      sb.append(")");
      return sb.toString();
   }
}
