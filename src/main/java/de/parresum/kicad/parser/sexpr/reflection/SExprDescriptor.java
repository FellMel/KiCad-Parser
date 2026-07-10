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
package de.parresum.kicad.parser.sexpr.reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Descriptor to hold informations about a Class to parse
 */
public class SExprDescriptor {
   /**
    * class the descriptor is for
    */
   private final Class<?> clazz;

   /**
    * List of Accessors to access simple atoms (arguments)
    */
   private final Map<Integer, Accessor> parameter = new HashMap<>();

   /**
    * List of Accessors to access elements
    */
   private final Map<String, ElementAccessor> elements = new HashMap<>();

   /**
    * Map from field name to accessor
    */
   private final Map<String, Accessor> fields = new HashMap<>();

   /**
    * Optional order of the fields for writing
    */
   private final ArrayList<String> order;

   private final boolean withoutOrder;

   /**
    * Constructor
    *
    * @param clazz class the descriptor is for
    */
   public SExprDescriptor(final Class<?> clazz, final List<String> order) {
      super();
      this.clazz = clazz;
      this.order = new ArrayList<>(order);
      withoutOrder = order.isEmpty();
   }

   /**
    * adds an argument to the descriptor
    *
    * @param pos   position of the argument
    * @param field accessor to the field
    */
   public void addParameter(final int pos, final Accessor field) {
      parameter.put(pos, field);
      final String name = field.getFieldName();
      fields.put(name, field);
      if (!order.contains(name)) {
         if (withoutOrder && pos >= 1) {

            order.ensureCapacity(pos);
            while (order.size() < pos) {
               order.add(null);
            }
            order.add(pos - 1, name);
         } else {
            order.add(name);
         }
      }
   }

   /**
    * adds an element to the descriptor
    *
    * @param name  name of the element associated with the field
    * @param field accessor to the field
    */
   public void addElement(final String name, final ElementAccessor field) {
      elements.put(name, field);
      final String fieldName = field.getFieldName();
      fields.put(fieldName, field);
      if (!order.contains(fieldName)) {
         order.add(fieldName);
      }
   }

   /**
    * gets a argument accessor
    *
    * @param pos position of the argument
    * @return accessor or null
    */
   public Accessor getParameter(final int pos) {
      return parameter.get(pos);
   }

   /**
    * gets an element accessor
    *
    * @param name name of the element to get the accor for
    * @return accessor or null
    */
   public ElementAccessor getElement(final String name) {
      return elements.get(name);
   }

   /**
    * gets the accessor of a specific field
    *
    * @param name name of the field to get the accessor for
    * @return the accessor or null
    */
   public Accessor getField(final String name) {
      return fields.get(name);
   }

   /**
    * gets all element accessors
    *
    * @return all accessors
    */
   public Set<Map.Entry<String, ElementAccessor>> getElements() {
      return elements.entrySet();
   }

   /**
    * gets the declared order of fields for writing
    *
    * @return the order
    */
   public List<String> getOrder() {
      return order;
   }

}
