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
package de.parresum.kicad.parser.circuit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.parresum.kicad.parser.eescheme.Junction;
import de.parresum.kicad.parser.eescheme.LibSymbol;
import de.parresum.kicad.parser.eescheme.Pin;
import de.parresum.kicad.parser.eescheme.PinNumber;
import de.parresum.kicad.parser.eescheme.PinShapeType;
import de.parresum.kicad.parser.eescheme.PinType;
import de.parresum.kicad.parser.eescheme.Property;
import de.parresum.kicad.parser.eescheme.Schematic;
import de.parresum.kicad.parser.eescheme.ShapeType;
import de.parresum.kicad.parser.eescheme.Symbol;
import de.parresum.kicad.parser.eescheme.Wire;
import de.parresum.kicad.parser.eescheme.label.GlobalLabel;
import de.parresum.kicad.parser.eescheme.label.Label;
import de.parresum.kicad.parser.eescheme.shape.Rectangle;
import de.parresum.kicad.parser.eescheme.shape.Text;
import de.parresum.kicad.parser.model.Color;
import de.parresum.kicad.parser.model.Fill;
import de.parresum.kicad.parser.model.FillType;
import de.parresum.kicad.parser.model.HorizontalAlignment;
import de.parresum.kicad.parser.model.PaperSizeType;
import de.parresum.kicad.parser.model.Position;
import de.parresum.kicad.parser.model.PositionAt;
import de.parresum.kicad.parser.model.Size;
import de.parresum.kicad.parser.model.Stroke;
import de.parresum.kicad.parser.model.StrokeType;
import de.parresum.kicad.parser.model.TitleBlock;
import de.parresum.kicad.parser.model.VerticalAlignment;
import de.parresum.kicad.parser.sexpr.SExpressionParser;
import de.parresum.kicad.parser.sexpr.SExpressionWriter;
import de.parresum.kicad.parser.sexpr.reflection.SExprClassHelper;

public class IncDecTest {
   @Test
   public void test2Simple() throws Exception {
      try (FileReader infile = new FileReader("src/test/resources/incdec2bit.kicad_sch");
            final FileWriter outfile = new FileWriter("target/incdec2bit.kicad_sch");) {

         final Schematic result = SExpressionParser.parse(infile, new Schematic());

         checkScheme(result);

         final SExpressionWriter writer = new SExpressionWriter(outfile);
         SExprClassHelper.write(writer, "kicad_sch", result);

      }

      // reparse written file and check structure again
      try (FileReader infile = new FileReader("target/incdec2bit.kicad_sch")) {

         final Schematic result = SExpressionParser.parse(infile, new Schematic());

         checkScheme(result);

      }

   }

   private void checkScheme(final Schematic object) {
      assertEquals("20260306", object.getVersion());
      assertEquals("eeschema", object.getGenerator());
      assertEquals("10.0", object.getGeneratorVersion());
      assertEquals("5921ef06-6d84-435b-8937-d22059470707", object.getUuid().getUuid());
      assertEquals(PaperSizeType.A4, object.getPaper().getPaperSize());
      checkTitleBlock(object.getTitleBlock());
      checkLibSymbols(object.getLibSymbols());
      checkJunctions(object.getJunctions());
      checkWires(object.getWires());
      checkLabels(object.getLocalLabels());
      checkGlobalLabels(object.getGlobalLabels());
      checkSymbols(object.getSymbols());

      assertEquals("/", object.getSheetInstance().get(0).getPaths().get(0).getAbsolutePath());
      assertEquals("7", object.getSheetInstance().get(0).getPaths().get(0).getPage());
      assertEquals(false, object.isEmbeddedFonts());
   }

   private void checkTitleBlock(final TitleBlock title) {
      assertNotNull(title);
      assertEquals("Inc / Dec 2 Bit", title.getTitle());
   }

   private void checkLibSymbols(final LibSymbol lib) {
      assertNotNull(lib);
      assertEquals(7, lib.getSymbols().size());
      checkLibSymbol0(lib.getSymbols().get(0));
      checkLibSymbol1(lib.getSymbols().get(1));
      checkLibSymbol2(lib.getSymbols().get(2));
      checkLibSymbol3(lib.getSymbols().get(3));
      checkLibSymbol4(lib.getSymbols().get(4));
      checkLibSymbol5(lib.getSymbols().get(5));
      checkLibSymbol6(lib.getSymbols().get(6));
   }

   // ------------------------------------------------------
   private void checkLibSymbol0(final Symbol symbol) {
      assertEquals("And 2_1", symbol.getName());
      assertEquals(true, symbol.getPinNumbers().isHide());
      assertEquals(0.0, symbol.getPinNames().getOffset());
      assertEquals(true, symbol.getPinNames().isHide());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.getDuplicatePinNubersAreJumpers());

      final PositionAt at = new PositionAt(0.0, 0.0, 0.0, null);
      final Size size = new Size(1.27, 1.27);

      checkProperty(symbol.getProperties().get(0), "Reference", "and", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", at, false, false, false, size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", at, false, false, true, size);

      assertEquals(2, symbol.getSymbols().size());
      checkLibSymbol0_0(symbol.getSymbols().get(0));
      checkLibSymbol0_1(symbol.getSymbols().get(1));
      assertEquals(false, symbol.getEmbeddedFonts());
   }

   private void checkLibSymbol0_0(final Symbol symbol) {
      assertEquals("And 2_1_0_1", symbol.getName());
      assertEquals(1, symbol.getRectangles().size());
      final Rectangle rect = symbol.getRectangles().get(0);
      assertEquals(new Position(-2.54, 2.54), rect.getStartPosition());
      assertEquals(new Position(2.54, -2.54), rect.getEndPosition());
      assertEquals(new Stroke(0.0, StrokeType.DEFAULT), rect.getStroke());
      assertEquals(new Fill(FillType.NONE), rect.getFill());

   }

   private void checkLibSymbol0_1(final Symbol symbol) {
      assertEquals("And 2_1_1_1", symbol.getName());
      assertEquals(1, symbol.getTexts().size());

      final Text text = symbol.getTexts().get(0);
      final Size size = new Size(1.27, 1.27);
      assertEquals("&", text.getText());
      assertEquals(new PositionAt(-1.27, 1.016, 0.0), text.getPosition());
      assertEquals(size, text.getTextEffects().getFont().getSize());
      final PinNumber number = new PinNumber("", size);

      assertEquals(3, symbol.getPins().size());
      checkPin(symbol.getPins().get(0), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, 1.27, 0.0), 2.54, "A",
            size, number);
      checkPin(symbol.getPins().get(1), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, -1.27, 0.0), 2.54, "B",
            size, number);
      checkPin(symbol.getPins().get(2), PinType.OUTPUT, PinShapeType.LINE, new PositionAt(5.08, 0.0, 180.0), 2.54, "O",
            size, number);

   }

   // ------------------------------------------------------
   private void checkLibSymbol1(final Symbol symbol) {
      assertEquals("Xor 2_1", symbol.getName());
      assertEquals(true, symbol.getPinNumbers().isHide());
      assertEquals(0.0, symbol.getPinNames().getOffset());
      assertEquals(true, symbol.getPinNames().isHide());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.getDuplicatePinNubersAreJumpers());

      final PositionAt at = new PositionAt(0.0, 0.0, 0.0, null);
      final Size size = new Size(1.27, 1.27);

      checkProperty(symbol.getProperties().get(0), "Reference", "or", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", at, false, false, false, size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", at, false, false, true, size);

      assertEquals(2, symbol.getSymbols().size());
      checkLibSymbol1_0(symbol.getSymbols().get(0));
      checkLibSymbol1_1(symbol.getSymbols().get(1));
      assertEquals(false, symbol.getEmbeddedFonts());

   }

   private void checkLibSymbol1_0(final Symbol symbol) {
      assertEquals("Xor 2_1_0_1", symbol.getName());
      assertEquals(1, symbol.getRectangles().size());
      final Rectangle rect = symbol.getRectangles().get(0);
      assertEquals(new Position(-2.54, 2.54), rect.getStartPosition());
      assertEquals(new Position(2.54, -2.54), rect.getEndPosition());
      assertEquals(new Stroke(0.0, StrokeType.DEFAULT), rect.getStroke());
      assertEquals(new Fill(FillType.NONE), rect.getFill());

   }

   private void checkLibSymbol1_1(final Symbol symbol) {
      assertEquals("Xor 2_1_1_1", symbol.getName());
      assertEquals(1, symbol.getTexts().size());

      final Text text = symbol.getTexts().get(0);
      final Size size = new Size(1.27, 1.27);
      assertEquals("=1", text.getText());
      assertEquals(new PositionAt(-0.762, 1.27, 0.0), text.getPosition());
      assertEquals(size, text.getTextEffects().getFont().getSize());
      final PinNumber number = new PinNumber("", size);

      assertEquals(3, symbol.getPins().size());
      checkPin(symbol.getPins().get(0), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, 1.27, 0.0), 2.54, "A",
            size, number);
      checkPin(symbol.getPins().get(1), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, -1.27, 0.0), 2.54, "B",
            size, number);
      checkPin(symbol.getPins().get(2), PinType.OUTPUT, PinShapeType.LINE, new PositionAt(5.08, 0.0, 180.0), 2.54, "O",
            size, number);

   }

   // ------------------------------------------------------
   private void checkLibSymbol2(final Symbol symbol) {
      assertEquals("Xor 2_2", symbol.getName());
      assertEquals(true, symbol.getPinNumbers().isHide());
      assertEquals(0.0, symbol.getPinNames().getOffset());
      assertEquals(true, symbol.getPinNames().isHide());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.getDuplicatePinNubersAreJumpers());

      final PositionAt at = new PositionAt(0.0, 0.0, 0.0, null);
      final Size size = new Size(1.27, 1.27);

      checkProperty(symbol.getProperties().get(0), "Reference", "or", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", at, false, false, false, size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", at, false, false, true, size);

      assertEquals(2, symbol.getSymbols().size());
      checkLibSymbol2_0(symbol.getSymbols().get(0));
      checkLibSymbol2_1(symbol.getSymbols().get(1));
      assertEquals(false, symbol.getEmbeddedFonts());
   }

   private void checkLibSymbol2_0(final Symbol symbol) {
      assertEquals("Xor 2_2_0_1", symbol.getName());
      assertEquals(1, symbol.getRectangles().size());
      final Rectangle rect = symbol.getRectangles().get(0);
      assertEquals(new Position(-2.54, 2.54), rect.getStartPosition());
      assertEquals(new Position(2.54, -2.54), rect.getEndPosition());
      assertEquals(new Stroke(0.0, StrokeType.DEFAULT), rect.getStroke());
      assertEquals(new Fill(FillType.NONE), rect.getFill());

   }

   private void checkLibSymbol2_1(final Symbol symbol) {
      assertEquals("Xor 2_2_1_1", symbol.getName());
      assertEquals(1, symbol.getTexts().size());

      final Text text = symbol.getTexts().get(0);
      final Size size = new Size(1.27, 1.27);
      assertEquals("=1", text.getText());
      assertEquals(new PositionAt(-0.762, 1.27, 0.0), text.getPosition());
      assertEquals(size, text.getTextEffects().getFont().getSize());
      final PinNumber number = new PinNumber("", size);

      assertEquals(3, symbol.getPins().size());
      checkPin(symbol.getPins().get(0), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, 1.27, 0.0), 2.54, "A",
            size, number);
      checkPin(symbol.getPins().get(1), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, -1.27, 0.0), 2.54, "B",
            size, number);
      checkPin(symbol.getPins().get(2), PinType.OUTPUT, PinShapeType.LINE, new PositionAt(5.08, 0.0, 180.0), 2.54, "O",
            size, number);

   }

   // ------------------------------------------------------
   private void checkLibSymbol3(final Symbol symbol) {
      assertEquals("Xor 2_3", symbol.getName());
      assertEquals(true, symbol.getPinNumbers().isHide());
      assertEquals(0.0, symbol.getPinNames().getOffset());
      assertEquals(true, symbol.getPinNames().isHide());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.getDuplicatePinNubersAreJumpers());

      final PositionAt at = new PositionAt(0.0, 0.0, 0.0, null);
      final Size size = new Size(1.27, 1.27);

      checkProperty(symbol.getProperties().get(0), "Reference", "or", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", at, false, false, false, size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", at, false, false, true, size);

      assertEquals(2, symbol.getSymbols().size());
      checkLibSymbol3_0(symbol.getSymbols().get(0));
      checkLibSymbol3_1(symbol.getSymbols().get(1));
      assertEquals(false, symbol.getEmbeddedFonts());
   }

   private void checkLibSymbol3_0(final Symbol symbol) {
      assertEquals("Xor 2_3_0_1", symbol.getName());
      assertEquals(1, symbol.getRectangles().size());
      final Rectangle rect = symbol.getRectangles().get(0);
      assertEquals(new Position(-2.54, 2.54), rect.getStartPosition());
      assertEquals(new Position(2.54, -2.54), rect.getEndPosition());
      assertEquals(new Stroke(0.0, StrokeType.DEFAULT), rect.getStroke());
      assertEquals(new Fill(FillType.NONE), rect.getFill());

   }

   private void checkLibSymbol3_1(final Symbol symbol) {
      assertEquals("Xor 2_3_1_1", symbol.getName());
      assertEquals(1, symbol.getTexts().size());

      final Text text = symbol.getTexts().get(0);
      final Size size = new Size(1.27, 1.27);
      assertEquals("=1", text.getText());
      assertEquals(new PositionAt(-0.762, 1.27, 0.0), text.getPosition());
      assertEquals(size, text.getTextEffects().getFont().getSize());
      final PinNumber number = new PinNumber("", size);

      assertEquals(3, symbol.getPins().size());
      checkPin(symbol.getPins().get(0), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, 1.27, 0.0), 2.54, "A",
            size, number);
      checkPin(symbol.getPins().get(1), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, -1.27, 0.0), 2.54, "B",
            size, number);
      checkPin(symbol.getPins().get(2), PinType.OUTPUT, PinShapeType.LINE, new PositionAt(5.08, 0.0, 180.0), 2.54, "O",
            size, number);

   }

   // ------------------------------------------------------
   private void checkLibSymbol4(final Symbol symbol) {
      assertEquals("toplevel.kicad_sch:And 2", symbol.getName());
      assertEquals(true, symbol.getPinNumbers().isHide());
      assertEquals(0.0, symbol.getPinNames().getOffset());
      assertEquals(true, symbol.getPinNames().isHide());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.getDuplicatePinNubersAreJumpers());

      final PositionAt at = new PositionAt(0.0, 0.0, 0.0, null);
      final Size size = new Size(1.27, 1.27);

      checkProperty(symbol.getProperties().get(0), "Reference", "and", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", at, false, false, false, size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", at, false, false, true, size);

      assertEquals(2, symbol.getSymbols().size());
      checkLibSymbol4_0(symbol.getSymbols().get(0));
      checkLibSymbol4_1(symbol.getSymbols().get(1));
      assertEquals(false, symbol.getEmbeddedFonts());
   }

   private void checkLibSymbol4_0(final Symbol symbol) {
      assertEquals("And 2_0_1", symbol.getName());
      assertEquals(1, symbol.getRectangles().size());
      final Rectangle rect = symbol.getRectangles().get(0);
      assertEquals(new Position(-2.54, 2.54), rect.getStartPosition());
      assertEquals(new Position(2.54, -2.54), rect.getEndPosition());
      assertEquals(new Stroke(0.0, StrokeType.DEFAULT), rect.getStroke());
      assertEquals(new Fill(FillType.NONE), rect.getFill());

   }

   private void checkLibSymbol4_1(final Symbol symbol) {
      assertEquals("And 2_1_1", symbol.getName());
      assertEquals(1, symbol.getTexts().size());

      final Text text = symbol.getTexts().get(0);
      final Size size = new Size(1.27, 1.27);
      assertEquals("&", text.getText());
      assertEquals(new PositionAt(-1.27, 1.016, 0.0), text.getPosition());
      assertEquals(size, text.getTextEffects().getFont().getSize());
      final PinNumber number = new PinNumber("", size);

      assertEquals(3, symbol.getPins().size());
      checkPin(symbol.getPins().get(0), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, 1.27, 0.0), 2.54, "A",
            size, number);
      checkPin(symbol.getPins().get(1), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, -1.27, 0.0), 2.54, "B",
            size, number);
      checkPin(symbol.getPins().get(2), PinType.OUTPUT, PinShapeType.LINE, new PositionAt(5.08, 0.0, 180.0), 2.54, "O",
            size, number);

   }

   // ------------------------------------------------------
   private void checkLibSymbol5(final Symbol symbol) {
      assertEquals("toplevel.kicad_sch:And 3", symbol.getName());
      assertEquals(true, symbol.getPinNumbers().isHide());
      assertEquals(0.0, symbol.getPinNames().getOffset());
      assertEquals(true, symbol.getPinNames().isHide());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.getDuplicatePinNubersAreJumpers());

      final PositionAt at = new PositionAt(0.0, 0.0, 0.0, null);
      final Size size = new Size(1.27, 1.27);

      checkProperty(symbol.getProperties().get(0), "Reference", "and", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", at, false, false, false, size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", at, false, false, true, size);

      assertEquals(2, symbol.getSymbols().size());
      checkLibSymbol5_0(symbol.getSymbols().get(0));
      checkLibSymbol5_1(symbol.getSymbols().get(1));
      assertEquals(false, symbol.getEmbeddedFonts());
   }

   private void checkLibSymbol5_0(final Symbol symbol) {
      assertEquals("And 3_0_1", symbol.getName());
      assertEquals(1, symbol.getRectangles().size());
      final Rectangle rect = symbol.getRectangles().get(0);
      assertEquals(new Position(-2.54, 3.81), rect.getStartPosition());
      assertEquals(new Position(2.54, -3.81), rect.getEndPosition());
      assertEquals(new Stroke(0.0, StrokeType.DEFAULT), rect.getStroke());
      assertEquals(new Fill(FillType.NONE), rect.getFill());

   }

   private void checkLibSymbol5_1(final Symbol symbol) {
      assertEquals("And 3_1_1", symbol.getName());
      assertEquals(1, symbol.getTexts().size());

      final Text text = symbol.getTexts().get(0);
      final Size size = new Size(1.27, 1.27);
      assertEquals("&", text.getText());
      assertEquals(new PositionAt(-1.27, 2.54, 0.0), text.getPosition());
      assertEquals(size, text.getTextEffects().getFont().getSize());
      final PinNumber number = new PinNumber("", size);

      assertEquals(4, symbol.getPins().size());
      checkPin(symbol.getPins().get(0), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, 2.54, 0.0), 2.54, "A",
            size, number);
      checkPin(symbol.getPins().get(1), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, 0.0, 0.0), 2.54, "B",
            size, number);
      checkPin(symbol.getPins().get(2), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, -2.54, 0.0), 2.54, "C",
            size, number);
      checkPin(symbol.getPins().get(3), PinType.OUTPUT, PinShapeType.LINE, new PositionAt(5.08, 0.0, 180.0), 2.54, "O",
            size, number);

   }

   // ------------------------------------------------------
   private void checkLibSymbol6(final Symbol symbol) {
      assertEquals("toplevel.kicad_sch:Xor 2", symbol.getName());
      assertEquals(true, symbol.getPinNumbers().isHide());
      assertEquals(0.0, symbol.getPinNames().getOffset());
      assertEquals(true, symbol.getPinNames().isHide());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.getDuplicatePinNubersAreJumpers());

      final PositionAt at = new PositionAt(0.0, 0.0, 0.0, null);
      final Size size = new Size(1.27, 1.27);

      checkProperty(symbol.getProperties().get(0), "Reference", "or", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", at, false, false, false, size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", at, false, false, true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", at, false, false, true, size);

      assertEquals(2, symbol.getSymbols().size());
      checkLibSymbol6_0(symbol.getSymbols().get(0));
      checkLibSymbol6_1(symbol.getSymbols().get(1));
      assertEquals(false, symbol.getEmbeddedFonts());
   }

   private void checkLibSymbol6_0(final Symbol symbol) {
      assertEquals("Xor 2_0_1", symbol.getName());
      assertEquals(1, symbol.getRectangles().size());
      final Rectangle rect = symbol.getRectangles().get(0);
      assertEquals(new Position(-2.54, 2.54), rect.getStartPosition());
      assertEquals(new Position(2.54, -2.54), rect.getEndPosition());
      assertEquals(new Stroke(0.0, StrokeType.DEFAULT), rect.getStroke());
      assertEquals(new Fill(FillType.NONE), rect.getFill());

   }

   private void checkLibSymbol6_1(final Symbol symbol) {
      assertEquals("Xor 2_1_1", symbol.getName());
      assertEquals(1, symbol.getTexts().size());

      final Text text = symbol.getTexts().get(0);
      final Size size = new Size(1.27, 1.27);
      assertEquals("=1", text.getText());
      assertEquals(new PositionAt(-0.762, 1.27, 0.0), text.getPosition());
      assertEquals(size, text.getTextEffects().getFont().getSize());
      final PinNumber number = new PinNumber("", size);

      assertEquals(3, symbol.getPins().size());
      checkPin(symbol.getPins().get(0), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, 1.27, 0.0), 2.54, "A",
            size, number);
      checkPin(symbol.getPins().get(1), PinType.INPUT, PinShapeType.LINE, new PositionAt(-5.08, -1.27, 0.0), 2.54, "B",
            size, number);
      checkPin(symbol.getPins().get(2), PinType.OUTPUT, PinShapeType.LINE, new PositionAt(5.08, 0.0, 180.0), 2.54, "O",
            size, number);

   }

   // ------------------------------------------------------
   // ------------------------------------------------------
   private void checkJunctions(final List<Junction> junctions) {
      assertEquals(8, junctions.size());
      final Color color = new Color(0, 0, 0, 0.0);
      checkJunction(junctions.get(0), new Position(137.16, 31.75), 0, color, "0d33d6db-acbf-472b-a54b-b096ba749add");
      checkJunction(junctions.get(1), new Position(133.35, 52.07), 0, color, "2ad815a2-d802-4d7b-b4e2-16602be27ef2");
      checkJunction(junctions.get(2), new Position(130.81, 54.61), 0, color, "620a65e3-f121-481c-bddc-d0c78b902a8f");
      checkJunction(junctions.get(3), new Position(109.22, 43.18), 0, color, "6d839c6d-9e73-49a0-9806-118509ff9c82");
      checkJunction(junctions.get(4), new Position(102.87, 29.21), 0, color, "7ea7f424-4ecf-4b29-ab1c-138a819eb0c0");
      checkJunction(junctions.get(5), new Position(102.87, 55.88), 0, color, "8fe2c512-df07-4eeb-b6c4-d2eee60fb9ba");
      checkJunction(junctions.get(6), new Position(133.35, 35.56), 0, color, "c6ed68c6-99f4-455b-afc2-30b3ed8d3b94");
      checkJunction(junctions.get(7), new Position(137.16, 38.1), 0, color, "ca18e9b4-0e01-4c8a-b5d7-25e16e925f99");
   }

   private void checkWires(final List<Wire> wires) {
      assertEquals(36, wires.size());

      checkWire(wires.get(0), "007c30d1-8126-465a-a289-fa938ade2411", new Position(133.35, 35.56),
            new Position(139.7, 35.56));
      checkWire(wires.get(1), "07980419-750d-4b47-b2b2-21992de6d51c", new Position(111.76, 53.34),
            new Position(109.22, 53.34));
      checkWire(wires.get(2), "0a024f1d-0473-428d-b0e0-e245ad035de2", new Position(137.16, 38.1),
            new Position(137.16, 49.53));
      checkWire(wires.get(3), "0c2045f0-3081-45bf-9dd1-883e4a269707", new Position(165.1, 63.5),
            new Position(181.61, 63.5));
      checkWire(wires.get(4), "1995927c-2360-41ed-a55d-fe183aa136e7", new Position(109.22, 43.18),
            new Position(154.94, 43.18));
      checkWire(wires.get(5), "1999482e-a583-4987-bc1d-84bcee0a57f9", new Position(121.92, 35.56),
            new Position(133.35, 35.56));
      checkWire(wires.get(6), "218dad9c-c461-4b44-bbf3-5a8a758dfc09", new Position(133.35, 35.56),
            new Position(133.35, 52.07));
      checkWire(wires.get(7), "22791a63-0cc4-47d5-a8d0-6652ccfb0aac", new Position(121.92, 54.61),
            new Position(130.81, 54.61));
      checkWire(wires.get(8), "2b9c6404-a302-48f0-a53e-953202fe139a", new Position(102.87, 29.21),
            new Position(92.71, 29.21));
      checkWire(wires.get(9), "38fe650a-42ce-4506-8a69-89e26dfeac14", new Position(102.87, 29.21),
            new Position(154.94, 29.21));

      checkWire(wires.get(10), "3a20f407-8488-44fd-a563-74b029b5f23e", new Position(102.87, 29.21),
            new Position(102.87, 34.29));
      checkWire(wires.get(11), "3af495f4-1391-4bd8-addd-5c88733d9fc8", new Position(165.1, 30.48),
            new Position(181.61, 30.48));
      checkWire(wires.get(12), "3f295e13-8c4d-4e6f-b53f-f0ecae362e7c", new Position(137.16, 31.75),
            new Position(137.16, 38.1));
      checkWire(wires.get(13), "4049de0f-bead-4c9b-a868-ff3225cd4661", new Position(92.71, 55.88),
            new Position(102.87, 55.88));
      checkWire(wires.get(14), "44805f10-14eb-4f8a-9cd6-13802cc941b5", new Position(111.76, 34.29),
            new Position(102.87, 34.29));
      checkWire(wires.get(15), "4aaa8c0f-5603-4665-afdc-0eb3d1ec73d5", new Position(137.16, 38.1),
            new Position(139.7, 38.1));
      checkWire(wires.get(16), "51df9ee9-37b2-4be5-8ecb-d59269cf4799", new Position(154.94, 64.77),
            new Position(130.81, 64.77));
      checkWire(wires.get(17), "698ef590-0ba8-4249-a964-42ec69feb0d6", new Position(152.4, 36.83),
            new Position(152.4, 40.64));
      checkWire(wires.get(18), "6cda6015-f872-483a-b61b-c10adf7b34aa", new Position(111.76, 55.88),
            new Position(102.87, 55.88));
      checkWire(wires.get(19), "71ce19b1-0f44-46e4-971c-a55b796db4a9", new Position(154.94, 62.23),
            new Position(133.35, 62.23));

      checkWire(wires.get(20), "7209c498-94d4-4e5a-9487-afde09227088", new Position(137.16, 49.53),
            new Position(154.94, 49.53));
      checkWire(wires.get(21), "7aaf735f-6c75-45a1-b027-6fd40510cc9e", new Position(109.22, 53.34),
            new Position(109.22, 43.18));
      checkWire(wires.get(22), "9491b7ad-f629-4b06-b9d8-9097368e6063", new Position(92.71, 24.13),
            new Position(137.16, 24.13));
      checkWire(wires.get(23), "9e64393c-e4f5-4966-a586-f882074ab7b1", new Position(130.81, 54.61),
            new Position(130.81, 64.77));
      checkWire(wires.get(24), "a5fc3a7b-5393-4055-a2a3-a3e680d056d2", new Position(130.81, 54.61),
            new Position(154.94, 54.61));
      checkWire(wires.get(25), "b9d58519-29fd-4bb4-993c-2fe465402a71", new Position(154.94, 31.75),
            new Position(137.16, 31.75));
      checkWire(wires.get(26), "c973215e-2da9-4aaf-bf95-4e096961ab73", new Position(152.4, 40.64),
            new Position(154.94, 40.64));
      checkWire(wires.get(27), "d3537b35-b5a1-438b-acf2-1fd0beef95f3", new Position(152.4, 36.83),
            new Position(149.86, 36.83));
      checkWire(wires.get(28), "dbdf9a56-ba78-4d87-8699-25a74ba889e6", new Position(133.35, 52.07),
            new Position(154.94, 52.07));
      checkWire(wires.get(29), "df282ea3-a1b9-4e41-a858-38a2942044d2", new Position(137.16, 24.13),
            new Position(137.16, 31.75));

      checkWire(wires.get(30), "e33be84d-30a4-4f25-b656-87420d1a7603", new Position(133.35, 52.07),
            new Position(133.35, 62.23));
      checkWire(wires.get(31), "e58bce60-98b1-4c89-9339-9d68b659de94", new Position(165.1, 52.07),
            new Position(181.61, 52.07));
      checkWire(wires.get(32), "e820d301-6d67-4a88-9c5a-feb856d82611", new Position(165.1, 41.91),
            new Position(181.61, 41.91));
      checkWire(wires.get(33), "ee9a41b5-059e-429c-ba07-3102365444d2", new Position(102.87, 36.83),
            new Position(111.76, 36.83));
      checkWire(wires.get(34), "f5936b87-2114-46b0-b425-9e7ce0a6c250", new Position(92.71, 43.18),
            new Position(109.22, 43.18));
      checkWire(wires.get(35), "fd903622-1b5a-45dc-a8ab-a5eceb7ac70d", new Position(102.87, 36.83),
            new Position(102.87, 55.88));

   }

   private void checkLabels(final List<Label> labels) {
      assertEquals(2, labels.size());

      final Size size = new Size(1.27, 1.27);

      Label label = labels.get(0);
      assertEquals("dec1", label.getText());
      assertEquals("0b9f8f1d-1af7-4787-9e73-206242382253", label.getUuid().getUuid());
      assertEquals(new PositionAt(125.73, 57.15, 180.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.RIGHT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(VerticalAlignment.BOTTOM, label.getTextEffects().getJustify().getVerticalAllignment());

      label = labels.get(1);
      assertEquals("dec0", label.getText());
      assertEquals("8efbf2ce-efe7-4c0f-aebe-fa9e77541156", label.getUuid().getUuid());
      assertEquals(new PositionAt(127.0, 35.56, 180.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.RIGHT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(VerticalAlignment.BOTTOM, label.getTextEffects().getJustify().getVerticalAllignment());

   }

   private void checkGlobalLabels(final List<GlobalLabel> labels) {
      assertEquals(8, labels.size());
      final Size size = new Size(1.27, 1.27);

      GlobalLabel label = labels.get(0);
      assertEquals("carry_borrow_in", label.getText());
      assertEquals(ShapeType.INPUT, label.getShape());
      assertEquals("21484eea-1d5f-44b2-a982-32dd40a692fb", label.getUuid().getUuid());
      assertEquals(true, label.isFieldsAutoplaced());
      assertEquals(new PositionAt(92.71, 24.13, 180.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.RIGHT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(null, label.getTextEffects().getJustify().getVerticalAllignment());
      checkProperty(label.getProperties().get(0), "Intersheetrefs", "${INTERSHEET_REFS}",
            new PositionAt(74.9083, 24.13, 0.0), false, false, true, size);

      label = labels.get(1);
      assertEquals("carry_borrow_out", label.getText());
      assertEquals(ShapeType.OUTPUT, label.getShape());
      assertEquals("37c9ff68-f373-4cf3-a1b6-9e782288fca1", label.getUuid().getUuid());
      assertEquals(true, label.isFieldsAutoplaced());
      assertEquals(new PositionAt(181.61, 52.07, 0.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.LEFT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(null, label.getTextEffects().getJustify().getVerticalAllignment());
      checkProperty(label.getProperties().get(0), "Intersheetrefs", "${INTERSHEET_REFS}",
            new PositionAt(200.6816, 52.07, 0.0), false, false, true, size);

      label = labels.get(2);
      assertEquals("decrement", label.getText());
      assertEquals(ShapeType.INPUT, label.getShape());
      assertEquals("6a68bde5-6c87-404d-9b8d-071f1aa770ac", label.getUuid().getUuid());
      assertEquals(true, label.isFieldsAutoplaced());
      assertEquals(new PositionAt(92.71, 55.88, 180.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.RIGHT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(null, label.getTextEffects().getJustify().getVerticalAllignment());
      checkProperty(label.getProperties().get(0), "Intersheetrefs", "${INTERSHEET_REFS}",
            new PositionAt(79.8672, 55.88, 0.0), false, false, true, size);

      label = labels.get(3);
      assertEquals("carry_lookahead_out", label.getText());
      assertEquals(ShapeType.OUTPUT, label.getShape());
      assertEquals("78320385-e7a9-4b10-b453-8ff450a47d5c", label.getUuid().getUuid());
      assertEquals(true, label.isFieldsAutoplaced());
      assertEquals(new PositionAt(181.61, 63.5, 0.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.LEFT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(null, label.getTextEffects().getJustify().getVerticalAllignment());
      checkProperty(label.getProperties().get(0), "Intersheetrefs", "${INTERSHEET_REFS}",
            new PositionAt(204.0076, 63.5, 0.0), false, false, true, size);

      label = labels.get(4);
      assertEquals("d0_in", label.getText());
      assertEquals(ShapeType.INPUT, label.getShape());
      assertEquals("7b62d687-7f21-408f-ab61-6591bfb6781f", label.getUuid().getUuid());
      assertEquals(true, label.isFieldsAutoplaced());
      assertEquals(new PositionAt(92.71, 29.21, 180.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.RIGHT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(null, label.getTextEffects().getJustify().getVerticalAllignment());
      checkProperty(label.getProperties().get(0), "Intersheetrefs", "${INTERSHEET_REFS}",
            new PositionAt(84.6449, 29.21, 0.0), false, false, true, size);

      label = labels.get(5);
      assertEquals("d0_out", label.getText());
      assertEquals(ShapeType.OUTPUT, label.getShape());
      assertEquals("afa16123-08c4-4937-a10e-d78806a5e7ff", label.getUuid().getUuid());
      assertEquals(true, label.isFieldsAutoplaced());
      assertEquals(new PositionAt(181.61, 30.48, 0.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.LEFT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(null, label.getTextEffects().getJustify().getVerticalAllignment());
      checkProperty(label.getProperties().get(0), "Intersheetrefs", "${INTERSHEET_REFS}",
            new PositionAt(190.945, 30.48, 0.0), false, false, true, size);

      label = labels.get(6);
      assertEquals("d1_in", label.getText());
      assertEquals(ShapeType.INPUT, label.getShape());
      assertEquals("b6de0a5b-7529-4823-b18b-1f63888fc555", label.getUuid().getUuid());
      assertEquals(true, label.isFieldsAutoplaced());
      assertEquals(new PositionAt(92.71, 43.18, 180.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.RIGHT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(null, label.getTextEffects().getJustify().getVerticalAllignment());
      checkProperty(label.getProperties().get(0), "Intersheetrefs", "${INTERSHEET_REFS}",
            new PositionAt(84.6449, 43.18, 0.0), false, false, true, size);

      label = labels.get(7);
      assertEquals("d1_out", label.getText());
      assertEquals(ShapeType.OUTPUT, label.getShape());
      assertEquals("be91593c-6b29-41d9-af4c-99e59dfc41f4", label.getUuid().getUuid());
      assertEquals(true, label.isFieldsAutoplaced());
      assertEquals(new PositionAt(181.61, 41.91, 0.0), label.getPosition());
      assertEquals(size, label.getTextEffects().getFont().getSize());
      assertEquals(HorizontalAlignment.LEFT, label.getTextEffects().getJustify().getHoritontalAllignment());
      assertEquals(null, label.getTextEffects().getJustify().getVerticalAllignment());
      checkProperty(label.getProperties().get(0), "Intersheetrefs", "${INTERSHEET_REFS}",
            new PositionAt(190.945, 41.91, 0.0), false, false, true, size);

   }

   // ------------------------------------------------------
   // ------------------------------------------------------
   private void checkSymbols(final List<Symbol> symbols) {
      assertEquals(7, symbols.size());

      checkSymbol0(symbols.get(0));
      checkSymbol1(symbols.get(1));
      checkSymbol2(symbols.get(2));
      checkSymbol3(symbols.get(3));
      checkSymbol4(symbols.get(4));
      checkSymbol5(symbols.get(5));
      checkSymbol6(symbols.get(6));
   }

   private void checkSymbol0(final Symbol symbol) {
      assertEquals("Xor 2_2", symbol.getLibName());
      assertEquals("toplevel.kicad_sch:Xor 2", symbol.getLibraryIdentifier());
      assertEquals("1004df9f-d720-4c63-ac73-76bcf8710f6d", symbol.getUuid().getUuid());
      assertEquals(new PositionAt(116.84, 54.61, 0.0), symbol.getPosition());
      assertEquals(1, symbol.getUnit());
      assertEquals(1, symbol.getBodyStyle());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.isDnp());
      assertEquals(true, symbol.isFieldsAutoplaced());

      final Size size = new Size(1.27, 1.27);
      checkProperty(symbol.getProperties().get(0), "Reference", "or8", new PositionAt(116.84, 54.61, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", new PositionAt(116.84, 54.61, 0.0), false, false, false,
            size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", new PositionAt(116.84, 54.61, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", new PositionAt(116.84, 54.61, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", new PositionAt(116.84, 54.61, 0.0), false, false,
            true, size);

      assertEquals("", symbol.getPins().get(0).getName());
      assertEquals("53a9ddc7-5665-4847-bff0-d604fb770655", symbol.getPins().get(0).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(1).getName());
      assertEquals("aac14c73-532e-4a09-91c1-9084b4c5dc8e", symbol.getPins().get(1).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(2).getName());
      assertEquals("e32473ac-af7c-4036-aea3-b89d6e89614a", symbol.getPins().get(2).getUuid().getUuid());

      assertEquals("z80", symbol.getInstances().get(0).getProject().getName());
      assertEquals("/7111835c-1872-403d-bebe-0bf1548bc034",
            symbol.getInstances().get(0).getProject().getPaths().get(0).getAbsolutePath());
      assertEquals("or8", symbol.getInstances().get(0).getProject().getPaths().get(0).getReference());
      assertEquals(1, symbol.getInstances().get(0).getProject().getPaths().get(0).getUnit());

   }

   private void checkSymbol1(final Symbol symbol) {
      // assertEquals("", symbol.getLibName());
      assertEquals("toplevel.kicad_sch:And 3", symbol.getLibraryIdentifier());
      assertEquals("3a040342-aa68-4514-857f-33f13afed8ca", symbol.getUuid().getUuid());
      assertEquals(new PositionAt(160.02, 52.07, 0.0), symbol.getPosition());
      assertEquals(1, symbol.getUnit());
      assertEquals(1, symbol.getBodyStyle());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.isDnp());
      assertEquals(true, symbol.isFieldsAutoplaced());

      final Size size = new Size(1.27, 1.27);
      checkProperty(symbol.getProperties().get(0), "Reference", "and10", new PositionAt(160.02, 52.07, 0.0), false,
            false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", new PositionAt(160.02, 52.07, 0.0), false, false, false,
            size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", new PositionAt(160.02, 52.07, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", new PositionAt(160.02, 52.07, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", new PositionAt(160.02, 52.07, 0.0), false, false,
            true, size);

      assertEquals("", symbol.getPins().get(0).getName());
      assertEquals("6b805829-5998-4da5-b6d0-a20989aeaf5d", symbol.getPins().get(0).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(1).getName());
      assertEquals("b3b2a922-7a17-468f-af8d-1bd8aca98790", symbol.getPins().get(1).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(2).getName());
      assertEquals("7074c271-307a-42a8-ac88-6d5b26a08346", symbol.getPins().get(2).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(3).getName());
      assertEquals("b61b3b38-44bb-442e-b787-0b4e2813c283", symbol.getPins().get(3).getUuid().getUuid());

      assertEquals("z80", symbol.getInstances().get(0).getProject().getName());
      assertEquals("/7111835c-1872-403d-bebe-0bf1548bc034",
            symbol.getInstances().get(0).getProject().getPaths().get(0).getAbsolutePath());
      assertEquals("and10", symbol.getInstances().get(0).getProject().getPaths().get(0).getReference());
      assertEquals(1, symbol.getInstances().get(0).getProject().getPaths().get(0).getUnit());

   }

   private void checkSymbol2(final Symbol symbol) {
      assertEquals("Xor 2_1", symbol.getLibName());
      assertEquals("toplevel.kicad_sch:Xor 2", symbol.getLibraryIdentifier());
      assertEquals("3b148c17-c82d-4b4a-b8f6-6f138999d4b0", symbol.getUuid().getUuid());
      assertEquals(new PositionAt(160.02, 30.48, 0.0), symbol.getPosition());
      assertEquals(1, symbol.getUnit());
      assertEquals(1, symbol.getBodyStyle());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.isDnp());
      assertEquals(true, symbol.isFieldsAutoplaced());

      final Size size = new Size(1.27, 1.27);
      checkProperty(symbol.getProperties().get(0), "Reference", "or7", new PositionAt(160.02, 30.48, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", new PositionAt(160.02, 30.48, 0.0), false, false, false,
            size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", new PositionAt(160.02, 30.48, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", new PositionAt(160.02, 30.48, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", new PositionAt(160.02, 30.48, 0.0), false, false,
            true, size);

      assertEquals("", symbol.getPins().get(0).getName());
      assertEquals("a9f2cf4a-43c9-4d8c-a481-1cc0c65204c8", symbol.getPins().get(0).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(1).getName());
      assertEquals("abb614af-3588-4592-8c0f-994070f24ba9", symbol.getPins().get(1).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(2).getName());
      assertEquals("79f592bb-3787-4c65-9f9c-b0e6616110bd", symbol.getPins().get(2).getUuid().getUuid());

      assertEquals("z80", symbol.getInstances().get(0).getProject().getName());
      assertEquals("/7111835c-1872-403d-bebe-0bf1548bc034",
            symbol.getInstances().get(0).getProject().getPaths().get(0).getAbsolutePath());
      assertEquals("or7", symbol.getInstances().get(0).getProject().getPaths().get(0).getReference());
      assertEquals(1, symbol.getInstances().get(0).getProject().getPaths().get(0).getUnit());

   }

   private void checkSymbol3(final Symbol symbol) {
      assertEquals("Xor 2_3", symbol.getLibName());
      assertEquals("toplevel.kicad_sch:Xor 2", symbol.getLibraryIdentifier());
      assertEquals("8ee3fdc4-6f84-40cd-aa63-f4a3298739d9", symbol.getUuid().getUuid());
      assertEquals(new PositionAt(116.84, 35.56, 0.0), symbol.getPosition());
      assertEquals(1, symbol.getUnit());
      assertEquals(1, symbol.getBodyStyle());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.isDnp());
      assertEquals(true, symbol.isFieldsAutoplaced());

      final Size size = new Size(1.27, 1.27);
      checkProperty(symbol.getProperties().get(0), "Reference", "or9", new PositionAt(116.84, 35.56, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", new PositionAt(116.84, 35.56, 0.0), false, false, false,
            size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", new PositionAt(116.84, 35.56, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", new PositionAt(116.84, 35.56, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", new PositionAt(116.84, 35.56, 0.0), false, false,
            true, size);

      assertEquals("", symbol.getPins().get(0).getName());
      assertEquals("d99e16ff-8140-48cf-b889-aa0c7a5c8059", symbol.getPins().get(0).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(1).getName());
      assertEquals("ae1b575a-40cc-4ac7-a610-2f43e89748ea", symbol.getPins().get(1).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(2).getName());
      assertEquals("92d3ac4e-acb3-44fa-a413-ebe1aafce5b2", symbol.getPins().get(2).getUuid().getUuid());

      assertEquals("z80", symbol.getInstances().get(0).getProject().getName());
      assertEquals("/7111835c-1872-403d-bebe-0bf1548bc034",
            symbol.getInstances().get(0).getProject().getPaths().get(0).getAbsolutePath());
      assertEquals("or9", symbol.getInstances().get(0).getProject().getPaths().get(0).getReference());
      assertEquals(1, symbol.getInstances().get(0).getProject().getPaths().get(0).getUnit());

   }

   private void checkSymbol4(final Symbol symbol) {
      // assertEquals("", symbol.getLibName());
      assertEquals("toplevel.kicad_sch:And 2", symbol.getLibraryIdentifier());
      assertEquals("d0ba5d11-66ff-43e4-8266-53fa6bf2fafc", symbol.getUuid().getUuid());
      assertEquals(new PositionAt(144.78, 36.83, 0.0), symbol.getPosition());
      assertEquals(1, symbol.getUnit());
      assertEquals(1, symbol.getBodyStyle());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.isDnp());
      assertEquals(true, symbol.isFieldsAutoplaced());

      final Size size = new Size(1.27, 1.27);
      checkProperty(symbol.getProperties().get(0), "Reference", "and9", new PositionAt(144.78, 36.83, 0.0), false,
            false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", new PositionAt(144.78, 36.83, 0.0), false, false, false,
            size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", new PositionAt(144.78, 36.83, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", new PositionAt(144.78, 36.83, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", new PositionAt(144.78, 36.83, 0.0), false, false,
            true, size);

      assertEquals("", symbol.getPins().get(0).getName());
      assertEquals("b1cf8030-2345-4a7b-a6e4-cb13ec0a842d", symbol.getPins().get(0).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(1).getName());
      assertEquals("b3d73604-1107-47fa-b101-f69f4642aaea", symbol.getPins().get(1).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(2).getName());
      assertEquals("00719296-37d7-41fd-ac8f-0493ec7fd2a8", symbol.getPins().get(2).getUuid().getUuid());

      assertEquals("z80", symbol.getInstances().get(0).getProject().getName());
      assertEquals("/7111835c-1872-403d-bebe-0bf1548bc034",
            symbol.getInstances().get(0).getProject().getPaths().get(0).getAbsolutePath());
      assertEquals("and9", symbol.getInstances().get(0).getProject().getPaths().get(0).getReference());
      assertEquals(1, symbol.getInstances().get(0).getProject().getPaths().get(0).getUnit());

   }

   private void checkSymbol5(final Symbol symbol) {
      // assertEquals("", symbol.getLibName());
      assertEquals("toplevel.kicad_sch:Xor 2", symbol.getLibraryIdentifier());
      assertEquals("d69d8889-54e8-4d76-8a31-2b7fa2959bb4", symbol.getUuid().getUuid());
      assertEquals(new PositionAt(160.02, 41.91, 0.0), symbol.getPosition());
      assertEquals(1, symbol.getUnit());
      assertEquals(1, symbol.getBodyStyle());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.isDnp());
      assertEquals(true, symbol.isFieldsAutoplaced());

      final Size size = new Size(1.27, 1.27);
      checkProperty(symbol.getProperties().get(0), "Reference", "or6", new PositionAt(160.02, 41.91, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", new PositionAt(160.02, 41.91, 0.0), false, false, false,
            size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", new PositionAt(160.02, 41.91, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", new PositionAt(160.02, 41.91, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", new PositionAt(160.02, 41.91, 0.0), false, false,
            true, size);

      assertEquals("", symbol.getPins().get(0).getName());
      assertEquals("00d54209-0549-4cfc-960e-c86e6cf7cf0f", symbol.getPins().get(0).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(1).getName());
      assertEquals("90253f45-8524-4da6-951e-7839143eb299", symbol.getPins().get(1).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(2).getName());
      assertEquals("0a72540d-c0c1-44dc-b999-26a1f907410e", symbol.getPins().get(2).getUuid().getUuid());

      assertEquals("z80", symbol.getInstances().get(0).getProject().getName());
      assertEquals("/7111835c-1872-403d-bebe-0bf1548bc034",
            symbol.getInstances().get(0).getProject().getPaths().get(0).getAbsolutePath());
      assertEquals("or6", symbol.getInstances().get(0).getProject().getPaths().get(0).getReference());
      assertEquals(1, symbol.getInstances().get(0).getProject().getPaths().get(0).getUnit());

   }

   private void checkSymbol6(final Symbol symbol) {
      assertEquals("And 2_1", symbol.getLibName());
      assertEquals("toplevel.kicad_sch:And 2", symbol.getLibraryIdentifier());
      assertEquals("d8a07bba-5b33-43b6-ac7c-f481f8ce7e9f", symbol.getUuid().getUuid());
      assertEquals(new PositionAt(160.02, 63.5, 0.0), symbol.getPosition());
      assertEquals(1, symbol.getUnit());
      assertEquals(1, symbol.getBodyStyle());
      assertEquals(false, symbol.getExcludeFromSim());
      assertEquals(true, symbol.isInBom());
      assertEquals(true, symbol.isOnBoard());
      assertEquals(true, symbol.isInPosFiles());
      assertEquals(false, symbol.isDnp());
      assertEquals(true, symbol.isFieldsAutoplaced());

      final Size size = new Size(1.27, 1.27);
      checkProperty(symbol.getProperties().get(0), "Reference", "and11", new PositionAt(160.02, 63.5, 0.0), false,
            false, true, size);
      checkProperty(symbol.getProperties().get(1), "Value", "", new PositionAt(160.02, 63.5, 0.0), false, false, false,
            size);
      checkProperty(symbol.getProperties().get(2), "Footprint", "", new PositionAt(160.02, 63.5, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(3), "Datasheet", "", new PositionAt(160.02, 63.5, 0.0), false, false,
            true, size);
      checkProperty(symbol.getProperties().get(4), "Description", "", new PositionAt(160.02, 63.5, 0.0), false, false,
            true, size);

      assertEquals("", symbol.getPins().get(0).getName());
      assertEquals("602e02fd-2524-4254-8c77-884ac61df775", symbol.getPins().get(0).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(1).getName());
      assertEquals("b7a1c682-b3f2-4978-af45-26cb99ee763b", symbol.getPins().get(1).getUuid().getUuid());

      assertEquals("", symbol.getPins().get(2).getName());
      assertEquals("706d3b93-2f30-470e-922a-62afaa27d378", symbol.getPins().get(2).getUuid().getUuid());

      assertEquals("z80", symbol.getInstances().get(0).getProject().getName());
      assertEquals("/7111835c-1872-403d-bebe-0bf1548bc034",
            symbol.getInstances().get(0).getProject().getPaths().get(0).getAbsolutePath());
      assertEquals("and11", symbol.getInstances().get(0).getProject().getPaths().get(0).getReference());
      assertEquals(1, symbol.getInstances().get(0).getProject().getPaths().get(0).getUnit());

   }

// ------------------------------------------------------
   // ------------------------------------------------------
   private void checkProperty(final Property property, final String name, final String value, final PositionAt pos,
         final boolean showName, final boolean autoPlace, final boolean hide, final Size fontSize) {
      assertEquals(name, property.getKey());
      assertEquals(value, property.getValue());
      assertEquals(pos, property.getAt());
      assertEquals(showName, property.isShowName());
      assertEquals(autoPlace, property.isDoNotAutoplace());
      assertEquals(hide, property.isHide());
      assertEquals(fontSize, property.getEffects().getFont().getSize());
   }

   private void checkPin(final Pin pin, final PinType pinType, final PinShapeType shape, final PositionAt at,
         final double length, final String name, final Size size, final PinNumber number) {
      assertEquals(pinType, pin.getElectricalPinType());
      assertEquals(shape, pin.getGraphicPinShape());
      assertEquals(at, pin.getPosition());
      assertEquals(length, pin.getLength());
      assertEquals(name, pin.getPinName().getName());
      assertEquals(size, pin.getPinName().getEffects().getFont().getSize());
      assertEquals(number, pin.getPinNumber());

   }

   private void checkJunction(final Junction junction, final Position at, final double diameter, final Color color,
         final String uuid) {
      assertEquals(at, junction.getPosition());
      assertEquals(diameter, junction.getDiameter());
      assertEquals(color, junction.getColor());
      assertEquals(uuid, junction.getUuid().getUuid());

   }

   private void checkWire(final Wire wire, final String uuid, final Position... pts) {
      assertEquals(uuid, wire.getUuid().getUuid());
      assertEquals(0.0, wire.getStroke().getWidth());
      assertEquals(StrokeType.DEFAULT, wire.getStroke().getType());
      assertEquals(pts.length, wire.getPoints().getPoints().size());
      for (int i = 0; i < pts.length; i++) {
         assertEquals(pts[i], wire.getPoints().getPoints().get(i), "point " + i);
      }
   }
}
