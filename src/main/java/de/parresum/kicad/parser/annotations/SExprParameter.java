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
package de.parresum.kicad.parser.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a field read and write as simple Argument (S-Expr Atom)
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SExprParameter {
   /**
    * Position of the Argument. -1 means a list of same items
    */
   int value();

   /**
    * Sets mode to handle values.
    *
    * On parameter, only SET_PARAMETER and TREAT_PARAM_AS_ONE_STRING are supported
    */
   SExprSymbolType symbolSetType() default SExprSymbolType.SET_PARAMETER;

   /**
    * A Mapping of the parameter, especially for boolean types.
    *
    * The values are used to parse the value true. For writing, the first value is used
    */
   String[] parameterMappings() default {};

}
