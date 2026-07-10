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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Symbol {

   @SExprParameter(1)
   private String name;

   @SExprSymbol("uuid")
   private UUID uuid;

   @SExprSymbol("lib_name")
   private String libName;

   @SExprSymbol("lib_id")
   private String libraryIdentifier;

   @SExprSymbol("extends")
   private String extendsSymbol;

   @SExprSymbol("at")
   private PositionAt position;

   @SExprSymbol("pin_numbers")
   private PinNumber pinNumbers;

   @SExprSymbol(value = "power", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean isPower;

   @SExprSymbol("pin_names")
   private PinNames pinNames;

   @SExprSymbol(value = "exclude_from_sim"/* , symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE */)
   private Boolean excludeFromSim;

   @SExprSymbol("unit")
   private Integer unit;

   @SExprSymbol("unit_name")
   private String unitName;

   @SExprSymbol(value = "in_bom", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean inBom;

   @SExprSymbol(value = "on_board", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean onBoard;

   @SExprSymbol(value = "dnp", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean dnp;

   @SExprSymbol(value = "in_pos_files", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean inPosFiles; // ????

   @SExprSymbol(value = "duplicate_pin_numbers_are_jumpers"/* , symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE */)
   private Boolean duplicatePinNubersAreJumpers;

   @SExprSymbol("passthrough")
   private PassthroughType passthrough;

   @SExprSymbol("mirror")
   private MirrorType mirror;

   @SExprSymbol(value = "fields_autoplaced", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean fieldsAutoplaced;

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

   @SExprSymbol("default_instance")
   private DefaultInstance defaultInstance;

   @SExprSymbol("property")
   private List<Property> properties;

   @SExprSymbol("pin")
   private List<Pin> pins;

   @SExprSymbol("symbol")
   private List<Symbol> symbols;

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
