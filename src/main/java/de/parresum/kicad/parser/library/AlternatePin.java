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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Specifies alternate pins
 *
 * @author Kai Uwe Bachmann
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlternatePin {
   /**
    * The name attribute defines the name of the pin. It must have an identically named hierarchical label in the
    * associated schematic file.
    */
   @SExprParameter(1)
   private String name;

   /**
    * defines the pin electrical connection.
    */
   @SExprParameter(2)
   private PinType electricalPinType;

   /**
    * defines the graphical style used to draw the pin.
    */
   @SExprParameter(3)
   private PinShapeType graphicPinShape;

}
