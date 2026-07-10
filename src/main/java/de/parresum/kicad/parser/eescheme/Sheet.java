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

import java.util.List;

import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.annotations.SExprSymbolType;
import de.parresum.kicad.parser.model.Fill;
import de.parresum.kicad.parser.model.PositionAt;
import de.parresum.kicad.parser.model.Size;
import de.parresum.kicad.parser.model.Stroke;
import de.parresum.kicad.parser.model.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Sheet {

   @SExprSymbol("uuid")
   private UUID uuid;

   @SExprSymbol("at")
   private PositionAt at;

   @SExprSymbol("size")
   private Size size;

   @SExprSymbol("exclude_from_sim")
   private boolean excludeFromSim;

   @SExprSymbol("in_bom")
   private boolean inBom;

   @SExprSymbol("on_board")
   private boolean onBoard;

   @SExprSymbol("dnp")
   private boolean dnp;

   @SExprSymbol(value = "fields_autoplaced", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean fieldsAutoplaced;

   @SExprSymbol("stroke")
   private Stroke stroke;

   @SExprSymbol("fill")
   private Fill fill;

   @SExprSymbol("property")
   private List<Property> properties;

   @SExprSymbol("pin")
   private List<Pin> pins;

   @SExprSymbol("instances")
   private List<Instance> instances;

}
