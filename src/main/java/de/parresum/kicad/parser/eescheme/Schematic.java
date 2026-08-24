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

/**
 * A Schematic.
 *
 * @author Kai Uwe Bachmann
 */
import java.util.List;

import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.eescheme.label.GlobalLabel;
import de.parresum.kicad.parser.eescheme.label.HierarchicalLabel;
import de.parresum.kicad.parser.eescheme.label.Label;
import de.parresum.kicad.parser.eescheme.shape.Arc;
import de.parresum.kicad.parser.eescheme.shape.Bezier;
import de.parresum.kicad.parser.eescheme.shape.Circle;
import de.parresum.kicad.parser.eescheme.shape.Polyline;
import de.parresum.kicad.parser.eescheme.shape.Rectangle;
import de.parresum.kicad.parser.eescheme.shape.RuleArea;
import de.parresum.kicad.parser.eescheme.shape.Text;
import de.parresum.kicad.parser.eescheme.shape.TextBox;
import de.parresum.kicad.parser.library.Library;
import de.parresum.kicad.parser.model.Page;
import de.parresum.kicad.parser.model.PageSettings;
import de.parresum.kicad.parser.model.TitleBlock;
import de.parresum.kicad.parser.model.UUID;
import de.parresum.kicad.parser.model.table.Table;
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
public class Schematic {

   /**
    * The version token attribute defines the schematic version using the YYYYMMDD date format.
    */
   @SExprSymbol("version")
   private String version;

   /**
    * The generator token attribute defines the program used to write the file.
    */
   @SExprSymbol("generator")
   private String generator;

   /**
    * Version of the generator using the YYYYMMDD date format.
    */
   @SExprSymbol("generator_version")
   private String generatorVersion;

   /**
    * The uuid token defines the globally unique identifier that identifies the schematic.
    */
   @SExprSymbol("uuid")
   private UUID uuid;

   @SExprSymbol("host")
   private Host host;

   @SExprSymbol("page")
   private Page page;

   /**
    * The paper token defines the drawing page size and orientation.
    */
   @SExprSymbol("paper")
   private PageSettings paper;

   @SExprSymbol("title_block")
   private TitleBlock titleBlock;

   /**
    * The lib_symbols token defines a symbol library contain all of the symbols used in the schematic.
    */
   @SExprSymbol("lib_symbols")
   private Library libSymbols;

   /**
    * The junction token defines a junction in the schematic. The junction section will not exist if there are no
    * junctions in the schematic.
    */
   @SExprSymbol("junction")
   private List<Junction> junctions;

   /**
    * The no_connect token defines a unused pin connection in the schematic. The no connect section will not exist if
    * there are not any no connects in the schematic.
    */
   @SExprSymbol("no_connect")
   private List<NoConnect> noConnects;

   /**
    * The bus_entry token defines a bus entry in the schematic. The bus entry section will not exist if there are no bus
    * entries in the schematic.
    */
   @SExprSymbol("bus_entry")
   private List<BusEntry> busEntries;

   /**
    * The wire tokens define wires in the schematic. This section will not exist if there are no wires in the schematic.
    */
   @SExprSymbol("wire")
   private List<Wire> wires;

   /**
    * The bus tokens define buses in the schematic. This section will not exist if there are no buses in the schematic.
    */
   @SExprSymbol("bus")
   private List<Bus> buses;

   @SExprSymbol("bus_alias")
   private List<BusAlias> busAliases;

   @SExprSymbol("image")
   private List<Image> images;

   /**
    * The polyline token defines one or more lines that may or may not represent a polygon. This section will not exist
    * if there are no lines in the schematic.
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

   @SExprSymbol("text_box")
   private List<TextBox> textBoxes;

   /**
    * The text token defines graphical text in a schematic.
    */
   @SExprSymbol("text")
   private List<Text> texts;

   @SExprSymbol("rule_area")
   private List<RuleArea> ruleAreas;

   @SExprSymbol("table")
   private List<Table> tables;

   /**
    * The label token defines an wire or bus label name in a schematic.
    */
   @SExprSymbol("label")
   private List<Label> localLabels;

   /**
    * The global_label token defines a label name that is visible across all schematics in a design. This section will
    * not exist if no global labels are defined in the schematic.
    */
   @SExprSymbol("global_label")
   private List<GlobalLabel> globalLabels;

   /**
    * The hierarchical_label section defines labels that are used by hierarchical sheets to define connections between
    * sheet in hierarchical designs. This section will not exist if no global labels are defined in the schematic.
    */
   @SExprSymbol("hierarchical_label")
   private List<HierarchicalLabel> hierarchicalLabels;

   /**
    * The symbol token in the symbol section of the schematic defines an instance of a symbol from the library symbol
    * section of the schematic.
    */
   @SExprSymbol("symbol")
   private List<Symbol> symbols;

   /**
    * The sheet token defines a hierarchical sheet of the schematic.
    */
   @SExprSymbol("sheet")
   private List<Sheet> sheets;

   @SExprSymbol("sheet_instances")
   private List<SheetInstance> sheetInstance;

   @SExprSymbol("embedded_fonts")
   private boolean embeddedFonts;

   @SExprSymbol("symbol_instances")
   private List<SheetInstance> symbolInstances; // ?????

   @SExprSymbol("netclass_flag")
   private List<NetclassFlag> netclassFlag;

   @SExprSymbol("net_chain")
   private List<NetChain> netChains;

   // ------------------------------------
   @SExprSymbol("group")
   private List<Group> group;

}
