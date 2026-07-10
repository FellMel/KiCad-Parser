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

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import de.parresum.kicad.parser.sexpr.SAtom;

/**
 * helper to access list arguments
 */
public class ListAccessor extends AbstractAccessor {

   public ListAccessor(final Field field) {
      super(field);
      checkType();
   }

   /**
    * check, whether field type is a list
    */
   protected void checkType() {
      if (field.getType().isPrimitive()) {
         throw new IllegalStateException("Object accessor can't handle type " + field.getType().getSimpleName());
      }
      if (!field.getType().isAssignableFrom(List.class)) {
         throw new IllegalStateException("Object accessor can't handle type " + field.getType().getSimpleName());
      }
   }

   /**
    * gets the generic type of the list
    *
    * @return
    */
   public Class<?> getType() {
      final Type type = field.getGenericType();
      final ParameterizedType pt = (ParameterizedType) type;
      final Class<?> targetType = (Class<?>) pt.getActualTypeArguments()[0];
      return targetType;

   }

   @Override
   public boolean match(final SAtom value) {
      try {
         if (value != null && parse(value.asString(null)) != null) {
            return true;
         }
      } catch (ParseException | NumberFormatException e) {
         // nothing to do, yet
      }
      return false;

   }

   @Override
   public String get(final Object target) throws IllegalArgumentException, IllegalAccessException {
      final Integer tmp = (Integer) field.get(target);
      if (tmp == null) {
         return null;
      }
      return tmp.toString();
   }

   @Override
   public void set(final Object target, final SAtom value)
         throws IllegalArgumentException, IllegalAccessException, ParseException {
      if (value == null) {
         return;
      }

      @SuppressWarnings("unchecked")
      List<Object> list = (List<Object>) field.get(target);
      if (list == null) {
         list = new ArrayList<>();
         field.set(target, list);
      }

      list.add(parse(value.asString(null)));
   }

   /**
    * parse the value to the generic type
    *
    * @param value
    * @return
    * @throws ParseException
    */
   protected Object parse(final String value) throws ParseException {
      final Class<?> type = getType();

      if (type == Boolean.class) {
         return parseBoolean(value);
      }
      if (type == Integer.class) {
         return parseInt(value);
      }
      if (type == Long.class) {
         return parseLong(value);
      }
      if (type == Double.class) {
         return parseDouble(value);
      }
      if (type == String.class) {
         return value;
      }

      return null;
   }
}
