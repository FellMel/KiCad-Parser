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
package de.parresum.kicad.parser.model;

import de.parresum.kicad.parser.annotations.SExprParameter;
import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.annotations.SExprSymbolType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Font definition of a text
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Font {

   /**
    * Defiens the font face used for the text
    */
   @SExprSymbol("face")
   private String fontFace;

   /**
    * defiens the font height and width
    */
   @SExprSymbol("size")
   private Size size;

   /**
    * defiens the line thickness of the font
    */
   @SExprSymbol("thickness")
   private Double thickness;

   /**
    * true, if the text should be bold
    */
   @SExprParameter(value = 1, parameterMappings = { "bold" })
   @SExprSymbol(value = "bold", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean bold;

   /**
    * true, if the text should be italic
    */
   @SExprParameter(value = 2, parameterMappings = { "italic" })
   @SExprSymbol(value = "italic", symbolSetType = SExprSymbolType.IMPLICIT_BOOL_TRUE)
   private boolean italic;

   /**
    * defines the spacing between lines as a ration of standard line spacing
    */
   @SExprSymbol("line_spacing")
   private Integer lineSpacing;

   /**
    * color of the font
    */
   @SExprSymbol("color")
   private Color color;
}
