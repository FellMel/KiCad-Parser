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

import de.parresum.kicad.parser.annotations.SExprParameter;
import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.annotations.SExprSymbolType;
import de.parresum.kicad.parser.eescheme.shape.Arc;
import de.parresum.kicad.parser.eescheme.shape.Bezier;
import de.parresum.kicad.parser.eescheme.shape.Circle;
import de.parresum.kicad.parser.eescheme.shape.Polyline;
import de.parresum.kicad.parser.eescheme.shape.Rectangle;
import de.parresum.kicad.parser.eescheme.shape.Text;
import de.parresum.kicad.parser.model.MirrorType;
import de.parresum.kicad.parser.model.PositionAt;
import de.parresum.kicad.parser.model.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The symbol token defines a symbol or sub-unit of a parent symbol.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Symbol {

   /**
    * Name of the symbol aka library identifier
    */
   @SExprParameter(1)
   private String name;

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
    * The optional unitName defines the display name of a subunit in the symbol editor and symbol chooser. It is only
    * permitted for child symbol tokens embedded in a parent symbol.
    */
   @SExprSymbol("unit_name")
   private String unitName;

   /**
    * The optional extends attribute defines the "LIBRARY_ID" of another symbol inside the current library from which to
    * derive a new symbol. Extended symbols currently can only have different SYMBOL_PROPERTIES than their parent
    * symbol.
    */
   @SExprSymbol("extends")
   private String extendsSymbol;

   /**
    * The position defines the X and Y coordinates and angle of rotation of the symbol.
    */
   @SExprSymbol("at")
   private PositionAt position;

   /**
    * The optional pinNumbers defines the visibility setting of the symbol pin numbers for the entire symbol. If not
    * defined, all of the pin numbers in the symbol are visible.
    */
   @SExprSymbol("pin_numbers")
   private PinNumber pinNumbers;

   @SExprSymbol(value = "power", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean isPower;

   /**
    * The optional pinNames defines the attributes for all of the pin names of the symbol. If the pinNames is not
    * defined, all symbol pins are shown with the default offset.
    */
   @SExprSymbol("pin_names")
   private PinNames pinNames;

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

   @SExprSymbol(value = "duplicate_pin_numbers_are_jumpers"/* , symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE */)
   private Boolean duplicatePinNubersAreJumpers;

   @SExprSymbol("passthrough")
   private PassthroughType passthrough;

   @SExprSymbol("mirror")
   private MirrorType mirror;

   @SExprSymbol(value = "fields_autoplaced", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean fieldsAutoplaced;

   // ------------------------------------------------------------------------
   /**
    * The graphic items section is list of graphical arcs, circles, curves, lines, polygons, rectangles and text that
    * define the symbol drawing. This section can be empty if the symbol has no graphical items.
    */
   @SExprSymbol("polyline")
   private List<Polyline> polylines;

   @SExprSymbol("rectangle")
   private List<Rectangle> rectangles;

   @SExprSymbol("circle")
   private List<Circle> circles;

   @SExprSymbol("arc")
   private List<Arc> arcs;

   @SExprSymbol("bezier")
   private List<Bezier> beziers;

   @SExprSymbol("text")
   private List<Text> texts;

   // ------------------------------------------------------------------------
   @SExprSymbol("default_instance")
   private DefaultInstance defaultInstance;

   /**
    * The properties is a list of properties that define the symbol. The following properties are mandatory when
    * defining a parent symbol: "Reference", "Value", "Footprint", and "Datasheet". All other properties are optional.
    * Unit symbols cannot have any properties.
    */
   @SExprSymbol("property")
   private List<Property> properties;

   /**
    * The pins is a list of pins that are used by the symbol. This section can be empty if the symbol does not have any
    * pins.
    */
   @SExprSymbol("pin")
   private List<Pin> pins;

   /**
    * one or more child symbol tokens embedded in a parent symbol.
    */
   @SExprSymbol("symbol")
   private List<Symbol> symbols;

   /**
    * The instances defines a list of symbol instances grouped by project. Every symbol will have a least one instance.
    */
   @SExprSymbol("instances")
   private List<Instance> instances;

   @SExprSymbol("body_style")
   private Integer bodyStyle; // ???

   @SExprSymbol(value = "body_styles", parameterMappings = { "demorgan" })
   private boolean bodyStyleDeMorgan;

   @SExprSymbol("jumper_pin_groups")
   private JumperPinGroups jumperGroups;

   @SExprSymbol(value = "embedded_fonts"/* , symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE */)
   private Boolean embeddedFonts;

}
