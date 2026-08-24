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

import java.util.List;

import de.parresum.kicad.parser.annotations.SExprParameter;
import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.eescheme.shape.Arc;
import de.parresum.kicad.parser.eescheme.shape.Bezier;
import de.parresum.kicad.parser.eescheme.shape.Circle;
import de.parresum.kicad.parser.eescheme.shape.Polyline;
import de.parresum.kicad.parser.eescheme.shape.Rectangle;
import de.parresum.kicad.parser.eescheme.shape.Text;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Definition of the graphical part of a library symbol
 *
 * @author Kai Uwe Bachmann
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GraphSymbol {
   /**
    * Name of the symbol part
    */
   @SExprParameter(1)
   private String name;

   /**
    * The optional unitName defines the display name of a subunit in the symbol editor and symbol chooser. It is only
    * permitted for child symbol tokens embedded in a parent symbol.
    */
   @SExprSymbol("unit_name")
   private String unitName;

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

   /**
    * The pins is a list of pins that are used by the symbol. This section can be empty if the symbol does not have any
    * pins.
    */
   @SExprSymbol("pin")
   private List<GraphPin> pins;

}
