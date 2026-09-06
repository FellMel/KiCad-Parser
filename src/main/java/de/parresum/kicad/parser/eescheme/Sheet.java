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

import java.util.ArrayList;
import java.util.List;

import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.annotations.SExprSymbolType;
import de.parresum.kicad.parser.model.Fill;
import de.parresum.kicad.parser.model.PositionAt;
import de.parresum.kicad.parser.model.Property;
import de.parresum.kicad.parser.model.Size;
import de.parresum.kicad.parser.model.Stroke;
import de.parresum.kicad.parser.model.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Sheet defines a hierarchical sheet of the schematic.
 *
 * @author Kai Uwe Bachmann
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Sheet {

   @SExprSymbol("uuid")
   private UUID uuid;

   /**
    * The at attribute defines the X and Y coordinates and angle of rotation of the sheet in the schematic.
    */
   @SExprSymbol("at")
   private PositionAt at;

   /**
    * The size attributes define the WIDTH and HEIGHT of the sheet.
    */
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

   /**
    * The optional fieldsAutoplaced indicates if the properties have been automatically placed.
    */
   @SExprSymbol(value = "fields_autoplaced", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean fieldsAutoplaced;

   /**
    * The stroke defines how the sheet outline is drawn.
    */
   @SExprSymbol("stroke")
   private Stroke stroke;

   /**
    * The fill defines how the sheet is filled.
    */
   @SExprSymbol("fill")
   private Fill fill;

   /**
    * The properties defines the name and the fileName of the sheet. This properties are mandatory.
    */
   @SExprSymbol("property")
   private List<Property> properties = new ArrayList<>();

   /**
    * The pins is a list of hierarchical pins that map a hierarchical label defined in the associated schematic file.
    */
   @SExprSymbol("pin")
   private List<Pin> pins = new ArrayList<>();

   /**
    * The instances defines a list of sheet instances grouped by project. Every sheet will have a least one instance.
    */
   @SExprSymbol("instances")
   private List<Instance> instances = new ArrayList<>();

}
