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
import java.text.ParseException;

import de.parresum.kicad.parser.sexpr.SAtom;

/**
 * Helper to access double fields
 *
 */
public class DoubleAccessor extends AbstractAccessor {

   public DoubleAccessor(final Field field) {
      super(field);
      if (field.getType() != Double.class && field.getType() != double.class) {
         throw new IllegalStateException("Double accessor can't handle type " + field.getType().getSimpleName());
      }
   }

   @Override
   public boolean match(final SAtom value) throws ParseException {
      try {
         if (value != null && !value.isString()) {
            parseDouble(value.asString(null));
            return true;
         }
      } catch (final NumberFormatException e) {
         // nothing to do, yet
      }
      return false;

   }

   @Override
   public String get(final Object target) throws IllegalArgumentException, IllegalAccessException {
      final Double tmp = (Double) field.get(target);
      if (tmp == null) {
         return null;
      }
      return writeDouble(tmp);
   }

   @Override
   public void set(final Object target, final SAtom value)
         throws IllegalArgumentException, IllegalAccessException, ParseException {
      if (value != null) {
         field.set(target, parseDouble(value.asString(null)));
      }

   }

}