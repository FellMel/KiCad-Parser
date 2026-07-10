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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

/**
 * Base helper to access fields
 *
 */
public abstract class AbstractAccessor implements Accessor {
   // TODO: format pattern by annotation
   private final static DecimalFormat FORMAT = new DecimalFormat("#.####", new DecimalFormatSymbols(Locale.US));

   /**
    * field to access
    */
   protected final Field field;

   public AbstractAccessor(final Field field) {
      super();
      this.field = field;
      field.setAccessible(true);
   }

   /**
    * gets the name of the field, this accessor is for
    *
    * @return name of the field
    */
   @Override
   public String getFieldName() {
      return field.getName();
   }

   /**
    * try to parse a value as boolean
    *
    * @param value value to parse
    * @return the parsed value or null, when not parseable
    */
   protected Boolean parseBoolean(final String value) {
      if (value != null) {
         if ("yes".equalsIgnoreCase(value) || "y".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value)) {
            return true;
         }
         if ("no".equalsIgnoreCase(value) || "n".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
            return false;
         }
      }
      return null;
   }

   /**
    * try to parse a value as int
    *
    * @param value value to parse
    * @return the parsed value or null, when value is null
    */
   protected Integer parseInt(final String value) {
      if (value != null) {
         return Integer.parseInt(value);
      }
      return null;
   }

   /**
    * try to parse a value as long
    *
    * @param value value to parse
    * @return the parsed value or null, when value is null
    */
   protected Long parseLong(final String value) {
      if (value != null) {
         return Long.parseLong(value);
      }
      return null;
   }

   /**
    * try to parse a value as double
    *
    * @param value value to parse
    * @return the parsed value or null, when value is null
    */
   protected Double parseDouble(final String value) throws ParseException {
      if (value != null) {
         return FORMAT.parse(value).doubleValue();
      }
      return null;
   }

   protected String writeDouble(final Double value) {
      if (value != null) {
         return FORMAT.format(value);
      }
      return null;
   }

   /**
    * try to parse a value as ENum
    *
    * @param value value to parse
    * @return the parsed value or null, when value is null
    */
   protected Object parseEnum(final String value) {
      if (value != null) {
         for (final Object item : field.getType().getEnumConstants()) {
            if (item.toString().equalsIgnoreCase(value)) {
               return item;
            }
         }
      }
      return null;
   }

}
