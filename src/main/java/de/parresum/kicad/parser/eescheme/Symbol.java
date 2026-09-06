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
import de.parresum.kicad.parser.model.MirrorType;
import de.parresum.kicad.parser.model.PositionAt;
import de.parresum.kicad.parser.model.Property;
import de.parresum.kicad.parser.model.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The symbol token defines a symbol within a scheme.
 *
 * @author Kai Uwe Bachmann
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Symbol {

   /**
    * ID of the symbol
    */
   @SExprSymbol("uuid")
   private UUID uuid;

   @SExprSymbol("lib_name")
   private String libName;

   @SExprSymbol("lib_id")
   private String libraryIdentifier;

   /**
    * The position defines the X and Y coordinates and angle of rotation of the symbol.
    */
   @SExprSymbol("at")
   private PositionAt position;

   @SExprSymbol(value = "exclude_from_sim"/* , symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE */)
   private Boolean excludeFromSim;

   /**
    * The unit attribute defines which unit in the symbol library definition that the schematic symbol represents.
    */
   @SExprSymbol("unit")
   private Integer unit;

   /**
    * The inBom defines, if a symbol is to be include in the bill of material output.
    */
   @SExprSymbol(value = "in_bom", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean inBom;

   /**
    * The onBoard defines, if a symbol is to be exported from the schematic to the printed circuit board.
    */
   @SExprSymbol(value = "on_board", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean onBoard;

   @SExprSymbol(value = "dnp", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean dnp;

   @SExprSymbol(value = "in_pos_files", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean inPosFiles;

   @SExprSymbol("passthrough")
   private PassthroughType passthrough;

   @SExprSymbol("mirror")
   private MirrorType mirror;

   @SExprSymbol(value = "fields_autoplaced", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean fieldsAutoplaced;

   // ------------------------------------------------------------------------
   @SExprSymbol("default_instance")
   private DefaultInstance defaultInstance;

   /**
    * The properties is a list of properties that define the symbol. The following properties are mandatory when
    * defining a parent symbol: "Reference", "Value", "Footprint", and "Datasheet". All other properties are optional.
    * Unit symbols cannot have any properties.
    */
   @SExprSymbol("property")
   private List<Property> properties = new ArrayList<>();

   /**
    * The pins is a list of pins that are used by the symbol. This section can be empty if the symbol does not have any
    * pins.
    */
   @SExprSymbol("pin")
   private List<Pin> pins = new ArrayList<>();

   /**
    * The instances defines a list of symbol instances grouped by project. Every symbol will have a least one instance.
    */
   @SExprSymbol("instances")
   private List<Instance> instances = new ArrayList<>();

   @SExprSymbol("body_style")
   private Integer bodyStyle; // ???

}
