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
import java.lang.reflect.InvocationTargetException;

import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.annotations.SExprSymbolType;

/**
 * Helper to access boolean fields
 *
 */
public class BooleanElementAccessor extends BooleanAccessor implements ElementAccessor {

   private final String symbolName;

   public BooleanElementAccessor(final Field field, final SExprSymbol symbol) {
      super(field, symbol.parameterMappings(), symbol.symbolSetType() == SExprSymbolType.IMPLICIT_BOOL_TRUE);
      symbolName = symbol.value();
   }

   @Override
   public String getSymbolName() {
      return symbolName;
   }

   @Override
   public boolean isList() {
      return false;
   }

   @Override
   public Class<?> getType() {
      return String.class;
   }

   @Override
   public boolean isAtom() {

      return true;
   }

   @Override
   public Object getObject(final Object target) throws IllegalArgumentException, IllegalAccessException {
      return get(target);
   }

   @Override
   public void set(final Object target, final Object value) throws IllegalArgumentException, IllegalAccessException {
      field.set(target, value);
   }

   @Override
   public Object createInstance()
         throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      if (impliciteTrue) {
         return false;
      }
      return null;
   }

   @Override
   public Object createInstance(final String value)
         throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      if (value == null && impliciteTrue) {
         return true;
      }
      return parse(value);
   }

}
