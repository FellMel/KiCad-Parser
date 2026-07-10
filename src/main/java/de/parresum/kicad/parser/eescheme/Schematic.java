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

   @SExprSymbol("version")
   private String version;

   @SExprSymbol("generator")
   private String generator;

   @SExprSymbol("generator_version")
   private String generatorVersion;

   @SExprSymbol("uuid")
   private UUID uuid;

   @SExprSymbol("host")
   private Host host;

   @SExprSymbol("page")
   private Page page;

   @SExprSymbol("paper")
   private PageSettings paper;

   @SExprSymbol("title_block")
   private TitleBlock titleBlock;

   @SExprSymbol("lib_symbols")
   private LibSymbol libSymbols;

   @SExprSymbol("junction")
   private List<Junction> junctions;

   @SExprSymbol("no_connect")
   private List<NoConnect> noConnects;

   @SExprSymbol("bus_entry")
   private List<BusEntry> busEntries;

   @SExprSymbol("wire")
   private List<Wire> wires;

   @SExprSymbol("bus")
   private List<Bus> buses;

   @SExprSymbol("bus_alias")
   private List<BusAlias> busAliases;

   @SExprSymbol("image")
   private List<Image> images;

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

   @SExprSymbol("text")
   private List<Text> texts;

   @SExprSymbol("rule_area")
   private List<RuleArea> ruleAreas;

   @SExprSymbol("table")
   private List<Table> tables;

   @SExprSymbol("label")
   private List<Label> localLabels;

   @SExprSymbol("global_label")
   private List<GlobalLabel> globalLabels;

   @SExprSymbol("hierarchical_label")
   private List<HierarchicalLabel> hierarchicalLabels;

   @SExprSymbol("symbol")
   private List<Symbol> symbols;

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
