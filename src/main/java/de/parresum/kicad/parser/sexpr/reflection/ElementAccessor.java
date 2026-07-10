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

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Base for Helper to access fields within objects
 *
 */
public interface ElementAccessor extends Accessor {

   /**
    * gets the name of the field, this accessor is for
    *
    * @return name of the field
    */
   @Override
   public String getFieldName();

   /**
    * gets the SExpr-symbol name of this field
    *
    * @return the symbol name
    */
   public String getSymbolName();

   /**
    * Checks whether field is a list
    *
    * @return
    */
   public boolean isList();

   /**
    * Gets type of the field. If field is a list, return the generic type
    *
    * @return type of the field
    */
   public Class<?> getType();

   /**
    * Gets the content of the field
    *
    * @param target Object to read the field from
    * @return the value of the field
    * @throws IllegalArgumentException
    * @throws IllegalAccessException
    */
   public Object getObject(final Object target) throws IllegalArgumentException, IllegalAccessException;

   /**
    * Sets the content of a field
    *
    * @param taget Object to set the field in
    * @param value Value to set to the field
    * @throws IllegalArgumentException
    * @throws IllegalAccessException
    */
   public void set(final Object taget, final Object value) throws IllegalArgumentException, IllegalAccessException;

   /**
    * Checks whether field is a primitive type or a wrapper
    *
    * @return true on primitive types
    */
   public boolean isAtom();

   /**
    * Creates a instance of the field type
    *
    * @return the created empty) instance
    * @throws InstantiationException
    * @throws IllegalAccessException
    * @throws IllegalArgumentException
    * @throws InvocationTargetException
    */
   public Object createInstance()
         throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

   /**
    * Creates a instance of the field type and initialize it by String.
    *
    * This is used for Primitive type Wrappers.
    *
    * @param value String representation of the field to create
    * @return the created empty) instance
    * @throws InstantiationException
    * @throws IllegalAccessException
    * @throws IllegalArgumentException
    * @throws InvocationTargetException
    */

   public Object createInstance(String value) throws InstantiationException, IllegalAccessException,
         IllegalArgumentException, InvocationTargetException, ParseException;
}
