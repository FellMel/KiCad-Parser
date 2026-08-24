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
package de.parresum.kicad.parser.eescheme;

import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.model.PointList;
import de.parresum.kicad.parser.model.Stroke;
import de.parresum.kicad.parser.model.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Bus define buses in the schematic.
 *
 * @author Kai Uwe Bachmann
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Bus {

   /**
    * The UUID defines the universally unique identifier for the bus.
    */
   @SExprSymbol("uuid")
   private UUID uuid;

   /**
    * The points defines the list of X and Y coordinates of start and end points of the bus.
    */
   @SExprSymbol("pts")
   private PointList pointList;

   /**
    * The Stroke defines how the bus is drawn.
    */
   @SExprSymbol("stroke")
   private Stroke stroke;

}
