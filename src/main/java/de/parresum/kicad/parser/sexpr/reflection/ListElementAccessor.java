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
import java.util.ArrayList;
import java.util.List;

import de.parresum.kicad.parser.annotations.SExprSymbol;

/**
 * helper to access list elements
 */
public class ListElementAccessor extends ObjectAccessor {

   public ListElementAccessor(final Field field, final SExprSymbol symbol)
         throws NoSuchMethodException, SecurityException {
      super(field, symbol);
   }

//   @Override
//   protected void checkType() {
//      if (field.getType().isPrimitive()) {
//         throw new IllegalStateException("Object accessor can't handle type " + field.getType().getSimpleName());
//      }
//      if (!field.getType().isAssignableFrom(List.class)) {
//         throw new IllegalStateException("Object accessor can't handle type " + field.getType().getSimpleName());
//      }
//   }

   @Override
   public boolean isList() {
      return true;
   }

   @Override
   public boolean isAtom() {
      return isWrapperType();
   }

   @Override
   public Class<?> getType() {
      final Type type = field.getGenericType();
      final ParameterizedType pt = (ParameterizedType) type;
      final Class<?> targetType = (Class<?>) pt.getActualTypeArguments()[0];
      return targetType;

   }

   @Override
   public Object getObject(final Object target) throws IllegalArgumentException, IllegalAccessException {

      return field.get(target);
   }

   @Override
   public void set(final Object target, final Object value) throws IllegalArgumentException, IllegalAccessException {
      if (value == null) {
         return;
      }

      @SuppressWarnings("unchecked")
      List<Object> list = (List<Object>) field.get(target);
      if (list == null) {
         list = new ArrayList<>();
         field.set(target, list);
      }
      final Class<?> targetType = getType();

      if (targetType.isAssignableFrom(value.getClass())) {
         list.add(value);
      }

   }
}