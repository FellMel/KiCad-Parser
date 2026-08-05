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

import de.parresum.kicad.parser.annotations.SExprSymbol;
import de.parresum.kicad.parser.model.LayerType;
import de.parresum.kicad.parser.model.PositionAt;
import de.parresum.kicad.parser.model.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * an embedded image.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Image {

   /**
    * ID of the image
    */
   @SExprSymbol("uuid")
   private UUID uuid;

   /**
    * Position and orientation of the image
    */
   @SExprSymbol("at")
   private PositionAt position;

   /**
    * a factor to scale the image
    */
   @SExprSymbol("scale")
   private Double scaleFactor;

   /**
    * Board layer containing the image
    */
   @SExprSymbol("layer")
   private LayerType layer;

   /**
    * Data of the image in PNG, Base64 encoded
    */
   @SExprSymbol("data") // KicadParserSymbolSetType.TreatParametersAsOneString
   private ImageData imageData;

   @SExprSymbol("locked")
   private boolean locked;

}
