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
package de.parresum.kicad.parser.library;

import de.parresum.kicad.parser.annotations.SExprParameter;
import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.annotations.SExprSymbolType;
import de.parresum.kicad.parser.model.PositionAt;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * defines a graphical pin in a symbol definition.
 *
 * @author Kai Uwe Bachmann
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GraphPin {

   /**
    * defines the pin electrical connection.
    */
   @SExprParameter(1)
   private PinType electricalPinType;

   /**
    * defines the graphical style used to draw the pin.
    */
   @SExprParameter(2)
   private PinShapeType graphicPinShape;

   /**
    * defines the X and Y coordinates and rotation angle of the connection point of the pin relative to the symbol
    * origin position. The only supported rotation angles for pins are 0, 90, 180, and 270 degrees.
    */
   @SExprSymbol("at")
   private PositionAt position;

   @SExprParameter(value = 3, parameterMappings = { "hide" })
   @SExprSymbol(value = "hide", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean hide;

   /**
    * defines the length of the pin.
    */
   @SExprSymbol("length")
   private double length;

   /**
    * containing the name of the pin
    */
   @SExprSymbol("name")
   private PinName pinName;

   @SExprSymbol("number")
   private PinNumber pinNumber;

   @SExprSymbol("alternate")
   private AlternatePin alternatePin;

}
