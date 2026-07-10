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

import de.parresum.kicad.parser.annotations.SExprParameter;
import de.parresum.kicad.parser.annotations.SExprSymbolType;
import de.parresum.kicad.parser.sexpr.SAtom;
import de.parresum.kicad.parser.sexpr.SExpressionWriter;

/**
 * Helper to Access String fields as simple Atom
 *
 */
public class StringAccessor extends AbstractAccessor {

   private final boolean treatParamAsOneString;

   public StringAccessor(final Field field, final SExprParameter params) {
      this(field, params.symbolSetType() == SExprSymbolType.TREAT_PARAM_AS_ONE_STRING);
   }

   public StringAccessor(final Field field, final boolean treatParamAsOneString) {
      super(field);
      if (field.getType() != String.class) {
         throw new IllegalStateException("String accessor can't handle type " + field.getType().getSimpleName());
      }
      this.treatParamAsOneString = treatParamAsOneString;
   }

   @Override
   public String get(final Object target) throws IllegalArgumentException, IllegalAccessException {
      final String tmp = (String) field.get(target);
      if (tmp == null) {
         return null;
      }
      return SExpressionWriter.escape(tmp);
   }

   @Override
   public boolean match(final SAtom value) {
      return (value != null && (value.isString() || treatParamAsOneString));
   }

   @Override
   public void set(final Object target, final SAtom value) throws IllegalArgumentException, IllegalAccessException {
      final String tmp = value.asString(null);
      field.set(target, parse(tmp));

   }

   protected String parse(final String value) {

      return value;
   }
}
