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

/**
 * Helper to access a boolean field
 *
 */
public class BooleanAccessor extends AbstractAccessor {

   /** true, when field is implicite true by element existence */
   protected final boolean impliciteTrue;
   /**
    * optional mappings for the boolean value
    */
   private final String[] mappings;

   public BooleanAccessor(final Field field, final SExprParameter param) {
      this(field, param.parameterMappings(), (param.symbolSetType() == SExprSymbolType.IMPLICIT_BOOL_TRUE));
   }

   public BooleanAccessor(final Field field, final String[] mappings, final boolean impliciteTrue) {
      super(field);
      if (field.getType() != Boolean.class && field.getType() != boolean.class) {
         throw new IllegalStateException("Bool accessor can't handle type " + field.getType().getSimpleName());
      }
      if (mappings != null && mappings.length > 0) {
         this.mappings = mappings;
      } else {
         this.mappings = null;
      }
      this.impliciteTrue = impliciteTrue;
   }

   @Override
   public boolean match(final SAtom value) {
      if (value != null && !value.isString() && parse(value.asString(null)) != null) {
         return true;
      }
      return false;

   }

   @Override
   public String get(final Object target) throws IllegalArgumentException, IllegalAccessException {
      final Boolean tmp = (Boolean) field.get(target);
      if (tmp == null) {
         return null;
      }
      if (tmp == false && impliciteTrue) {
         return null;
      }
      if (mappings != null && mappings.length > 0) {
         return tmp ? mappings[0] : null;
      }
      return tmp ? "yes" : "no";
   }

   @Override
   public void set(final Object target, final SAtom value) throws IllegalArgumentException, IllegalAccessException {
      if (value != null) {
         field.set(target, parse(value.asString(null)));
      }
   }

   protected Boolean parse(final String value) {
      if (mappings != null) {
         if (value != null) {
            for (final String item : mappings) {
               if (item.equalsIgnoreCase(value)) {
                  return true;
               }
            }
         }
         return null;
      }
      return parseBoolean(value);
   }

}
