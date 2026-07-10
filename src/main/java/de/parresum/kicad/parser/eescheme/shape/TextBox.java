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
package de.parresum.kicad.parser.eescheme.shape;

import de.parresum.kicad.parser.annotations.SExprParameter;
import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.model.Margin;
import de.parresum.kicad.parser.model.PositionAt;
import de.parresum.kicad.parser.model.Size;
import de.parresum.kicad.parser.model.TextEffects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TextBox extends AbstractShape {
   @SExprParameter(1)
   private String text;

   @SExprSymbol("at")
   private PositionAt at;

   @SExprSymbol("size")
   private Size size;

   @SExprSymbol("effects")
   private TextEffects effects;

   @SExprSymbol("margins")
   private Margin margins;

}
