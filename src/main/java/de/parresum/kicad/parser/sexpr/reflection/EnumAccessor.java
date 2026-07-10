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

import de.parresum.kicad.parser.sexpr.SAtom;

/**
 * helper to access enum arguments
 */
public class EnumAccessor extends AbstractAccessor {

   public EnumAccessor(final Field field) {
      super(field);
      if (!field.getType().isEnum()) {
         throw new IllegalStateException("Enum accessor can't handle type " + field.getType().getSimpleName());
      }
   }

   @Override
   public boolean match(final SAtom value) {
      if (value != null && parseEnum(value.asString(null)) != null) {
         return true;
      }
      return false;

   }

   @Override
   public String get(final Object target) throws IllegalArgumentException, IllegalAccessException {
      final Object tmp = field.get(target);
      if (tmp == null) {
         return null;
      }
      return tmp.toString();
   }

   @Override
   public void set(final Object taget, final SAtom value) throws IllegalArgumentException, IllegalAccessException {
      field.set(taget, parseEnum(value.asString(null)));
   }

}
