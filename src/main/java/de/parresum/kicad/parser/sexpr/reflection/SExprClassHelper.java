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

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.parresum.kicad.parser.annotations.SExprModel;
import de.parresum.kicad.parser.annotations.SExprParameter;
import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.sexpr.SAtom;
import de.parresum.kicad.parser.sexpr.SExpressionWriter;
import de.parresum.kicad.parser.sexpr.SNode;

/**
 * Helper to parse and write Objects
 */
public class SExprClassHelper {
   /** cache with already analyzed classes */
   private final static Map<Class<?>, SExprDescriptor> CACHE = new HashMap<>();

   /**
    * analyze the annotations within a type
    *
    * @param <T>
    * @param type type of Class to analyze
    * @return the descriptor for the class
    * @throws NoSuchMethodException
    * @throws SecurityException
    */
   public static <T> SExprDescriptor analyzeType(final Class<T> type) throws NoSuchMethodException, SecurityException {
      if (CACHE.containsKey(type)) {
         return CACHE.get(type);
      }

      Class<?> curType = type;
      final SExprModel model = curType.getAnnotation(SExprModel.class);
      final List<String> order = new ArrayList<>();
      if (model != null) {
         final String[] tmp = model.order();
         for (final String item : tmp) {
            order.add(item);
         }
      }
      final SExprDescriptor descriptor = new SExprDescriptor(type, order);
      while (curType != null && curType != Object.class) {
         for (final Field field : curType.getDeclaredFields()) {
            if (field.isSynthetic()) {
               continue;
            }

            if ((field.getModifiers() & Modifier.STATIC) != 0) {
               continue;
            }

            final SExprParameter expr = field.getAnnotation(SExprParameter.class);
            if (expr != null) {
               descriptor.addParameter(expr.value(), fieldToAccessor(field, expr));
            }
            final SExprSymbol symbol = field.getAnnotation(SExprSymbol.class);
            if (symbol != null) {
               descriptor.addElement(symbol.value(), fieldToObjectAccessor(field, symbol));
            }

         }
         curType = curType.getSuperclass();
      }
      CACHE.put(type, descriptor);
      return descriptor;
   }

   /**
    * Fill an object from SNodes
    *
    * @param node   node to fill the object from
    * @param target object to fill
    * @throws NoSuchMethodException
    * @throws SecurityException
    * @throws IllegalArgumentException
    * @throws IllegalAccessException
    * @throws InstantiationException
    * @throws InvocationTargetException
    * @throws ParseException
    */
   public static void parse(final SNode node, final Object target)
         throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException,
         InstantiationException, InvocationTargetException, ParseException {

      final Class<? extends Object> type = target.getClass();
      System.out.println("parsing type " + type.getSimpleName());

      final SExprDescriptor descriptor = analyzeType(type);
      int paramPos = 0;
      for (final SNode entry : node.asList()) {
         if (entry.isAtom()) {
            if (paramPos == 0) { // Name-Position
               paramPos++;
               continue;
            }

            do {
               final SAtom atom = entry.asAtom();
               System.out.println("Parsing argument " + paramPos + " : " + atom.asString(null));
               Accessor field = descriptor.getParameter(paramPos);
               if (field == null) {
                  field = descriptor.getParameter(-1);
               }
               if (field == null) {
                  System.err.println("Missing attribut of position " + paramPos + " for class " + type.getSimpleName());
                  throw new IllegalArgumentException(
                        "Missing attribut of position " + paramPos + " for class " + type.getSimpleName());
               }
               paramPos++;

               if (field.match(atom)) {
                  field.set(target, atom);
                  break;
               }
            } while (true);

         } else {
            String name = entry.getName();
            if (name.equals("do_not_autoplace")) {
               System.out.println("." + entry.get(1).asAtom().asString(""));
            }
            boolean elementWithoutName = false;
            // Element ohne Namen
            if (entry.get(0).asAtom().isString()) {
               name = "<NO_NAME>";
               elementWithoutName = true;
            }
            System.out.println("Parsing Element " + name);
            final ElementAccessor field = descriptor.getElement(name);
            if (field == null) {
               System.err.println("Field not found: " + name + " in class " + type.getSimpleName());
               throw new IllegalArgumentException("Field not found: " + name + " in class " + type.getSimpleName());
            }

            Object value = null;
            if (field.isAtom()) {
               if (field.isList()) {
                  final List<Object> tmp = new ArrayList<>();
                  boolean first = true;
                  for (final SNode arg : entry.asList()) {
                     if (first && !elementWithoutName) {
                        first = false;
                        continue;
                     }

                     final Object val = field.createInstance(arg.asAtom().asString(null));
                     tmp.add(val);
                  }
                  value = tmp;
               } else {
                  final SNode arg = entry.get(1);
                  value = field.createInstance(arg != null ? arg.asAtom().asString(null) : null);
               }
            } else {
               value = field.createInstance();
               parse(entry, value);
            }
            field.set(target, value);
         }
      }

   }

   /**
    * writes an object to the writer
    *
    * @param writer writer to write the object to
    * @param name   name of the element of the object
    * @param symbol object to write
    * @throws IOException
    * @throws IllegalArgumentException
    * @throws IllegalAccessException
    * @throws NoSuchMethodException
    * @throws SecurityException
    */
   public static void write(final SExpressionWriter writer, final String name, final Object symbol) throws IOException,
         IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException {
      if (symbol == null) {
         return;
      }

      // TODO: symbol & argument
      // TODO: argument-Lists

      final SExprDescriptor descriptor = analyzeType(symbol.getClass());
      writer.startNode(name);

      for (final String fieldName : descriptor.getOrder()) {
         if (fieldName == null) {
            continue;
         }
         final Accessor field = descriptor.getField(fieldName);
         if (field == null) {
            continue;
         }

         if (field instanceof ElementAccessor) {
            final ElementAccessor accessor = (ElementAccessor) field;
            final Object value = accessor.getObject(symbol);
            if (value != null) {
               if (accessor.isList()) {
                  for (final Object item : (Iterable<?>) value) {
                     write(writer, accessor.getSymbolName(), item);
                  }
               } else if (accessor.isAtom()) {

                  writer.writeAtom(accessor.getSymbolName(), (String) value);
               } else {
                  write(writer, accessor.getSymbolName(), value);
               }
            }
         } else {
            final String val = field.get(symbol);
            if (val == null) {
               continue;
            }
            writer.writeStringValue(val);
         }
      }

//      int pos = 1;
//      Accessor field;
//      while ((field = descriptor.getParameter(pos++)) != null) {
//         final String val = field.get(symbol);
//         if (val == null) {
//            continue;
//         }
//         writer.writeStringValue(val);
//      }
//
//      for (final Map.Entry<String, ElementAccessor> entry : descriptor.getElements()) {
//         final Object value = entry.getValue().get(symbol);
//         if (value != null) {
//            if (entry.getValue().isList()) {
//               for (final Object item : (Iterable<?>) value) {
//                  write(writer, entry.getKey(), item);
//               }
//            } else if (entry.getValue().isAtom()) {
//
//               writer.writeAtom(entry.getKey(), (String) value);
//            } else {
//               write(writer, entry.getKey(), value);
//            }
//         }
//      }

      writer.endNode();

   }

   private static AbstractAccessor fieldToAccessor(final Field field, final SExprParameter param) {
      if (field.getType().isAssignableFrom(List.class)) {
         return new ListAccessor(field);
      }

      if (field.getType() == String.class) {
         return new StringAccessor(field, param);
      }
      if (field.getType() == Integer.class || field.getType() == int.class) {
         return new IntAccessor(field);
      }

      if (field.getType() == Long.class || field.getType() == long.class) {
         return new LongAccessor(field);
      }
      if (field.getType() == Double.class || field.getType() == double.class) {
         return new DoubleAccessor(field);
      }
      if (field.getType() == Boolean.class || field.getType() == boolean.class) {
         return new BooleanAccessor(field, param);
      }
      if (field.getType().isEnum()) {
         return new EnumAccessor(field);
      }
      throw new IllegalStateException("UnsupportedType: " + field.getType().getSimpleName());

   }

   private static ElementAccessor fieldToObjectAccessor(final Field field, final SExprSymbol symbol)
         throws NoSuchMethodException, SecurityException {
      if (field.getType().isAssignableFrom(List.class)) {
         return new ListElementAccessor(field, symbol);
      }

      if (field.getType() == String.class) {
         return new StringElementAccessor(field, symbol);
      }
      if (field.getType() == Integer.class || field.getType() == int.class) {
         return new IntElementAccessor(field, symbol);
      }

      if (field.getType() == Long.class || field.getType() == long.class) {
         return new LongElementAccessor(field, symbol);
      }
      if (field.getType() == Double.class || field.getType() == double.class) {
         return new DoubleElementAccessor(field, symbol);
      }
      if (field.getType() == Boolean.class || field.getType() == boolean.class) {
         return new BooleanElementAccessor(field, symbol);
      }
      if (field.getType().isEnum()) {
         return new EnumElementAccessor(field, symbol);
      }

      return new ObjectAccessor(field, symbol);
   }

}
