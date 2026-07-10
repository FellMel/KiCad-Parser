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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;

import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.sexpr.SAtom;

/**
 * helper to access object elements
 */
public class ObjectAccessor implements ElementAccessor {

   protected final Field field;
   private final Constructor<?> ctor;
   private final Method valueOf;
   private final String symbolName;

   public ObjectAccessor(final Field field, final SExprSymbol symbol) throws NoSuchMethodException, SecurityException {
      this.field = field;
      field.setAccessible(true);
      final Class<?> type = getType();
      if (isWrapperType()) {
         ctor = null;
         if (type != String.class) {
            valueOf = type.getMethod("valueOf", String.class);
         } else {
            valueOf = null;
         }
      } else {
         ctor = type.getConstructor();
         ctor.setAccessible(true);
         valueOf = null;
      }
      symbolName = symbol.value();
   }

   @Override
   public String getSymbolName() {
      return symbolName;
   }

   @Override
   public String getFieldName() {
      return field.getName();
   }

   /**
    * checks whether type is a wrapper
    *
    * @return true, when wrapper
    */
   protected boolean isWrapperType() {
      final Class<?> type = getType();
      return (type == Boolean.class || type == Integer.class || type == Long.class || type == Double.class
            || type == String.class);

   }

   @Override
   public Class<?> getType() {
      return field.getType();
   }

   @Override
   public boolean isList() {
      return false;
   }

   @Override
   public String get(final Object target) throws IllegalArgumentException, IllegalAccessException {
      final Object tmp = this.getObject(target);
      if (tmp != null) {
         return tmp.toString();
      }
      return null;
   }

   @Override
   public void set(final Object taget, final SAtom value)
         throws IllegalArgumentException, IllegalAccessException, ParseException {
      throw new IllegalStateException("set with atom not supported for objects");

   }

   @Override
   public Object getObject(final Object target) throws IllegalArgumentException, IllegalAccessException {
      return field.get(target);
   }

   @Override
   public void set(final Object target, final Object value) throws IllegalArgumentException, IllegalAccessException {
      field.set(target, value);
   }

   @Override
   public boolean isAtom() {
      return false;
   }

   @Override
   public Object createInstance()
         throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      if (ctor != null) {
         return ctor.newInstance();
      }
      throw new IllegalStateException("Can't instantiate class of type " + getType() + " without parameter");

   }

   @Override
   public Object createInstance(final String value)
         throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      if (getType() == String.class) {
         return value;
      }
      if (valueOf != null) {
         return valueOf.invoke(null, value);
      }
      return createInstance();
   }

   @Override
   public boolean match(final SAtom value) {
      // TODO Auto-generated method stub
      return false;
   }

}
