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
import de.parresum.kicad.parser.model.PositionAt;
import de.parresum.kicad.parser.model.Size;
import de.parresum.kicad.parser.model.Stroke;
import de.parresum.kicad.parser.model.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The BusEntry defines a bus entry in the schematic.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BusEntry {

   /**
    * The UUID defines the universally unique identifier for the bus entry.
    */
   @SExprSymbol("uuid")
   private UUID uuid;

   /**
    * The position defines the X and Y coordinates of the bus entry.
    */
   @SExprSymbol("at")
   private PositionAt position;

   /**
    * The size token attributes define the X and Y distance of the end point from the position of the bus entry.
    */
   @SExprSymbol("size")
   private Size size;

   /**
    * The stroke defines how the bus entry is drawn.
    */
   @SExprSymbol("stroke")
   private Stroke stroke;

}
