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

import java.text.ParseException;

import de.parresum.kicad.parser.sexpr.SAtom;

public interface Accessor {
   /**
    * gets the name of the field, this accessor is for
    *
    * @return name of the field
    */
   public String getFieldName();

   /**
    * gets the value of the field as string
    *
    * @param target object to get the field from
    * @return the string content of the field
    * @throws IllegalArgumentException
    * @throws IllegalAccessException
    */
   public String get(Object target) throws IllegalArgumentException, IllegalAccessException;

   /**
    * sets a value by an S-Atom
    *
    * @param taget Object to set the field in
    * @param value value to set to the field
    * @throws IllegalArgumentException
    * @throws IllegalAccessException
    * @throws ParseException
    */
   public void set(Object taget, SAtom value) throws IllegalArgumentException, IllegalAccessException, ParseException;

   /**
    * checks whether the content could converted to the field type
    *
    * @param value content to check
    * @return true, when concvertible
    */
   public boolean match(SAtom value) throws ParseException;

}
