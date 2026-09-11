/*
 *     Copyright 2026 Parresum Soft @ http://parresum.de
 *
 * Licensed under the Apache License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.parresum.kicad.parser.project;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 *
 * @author Kai Uwe Bachmann
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DesignDefaults {
   private boolean applyDefaultsToFpBarcodes;
   private boolean applyDefaultsToFpDimensions;
   private boolean applyDefaultsToFpFields;
   private boolean applyDefaultsToFpShapes;
   private boolean applyDefaultsToFpText;
   private double boardOutlineLineWidth;
   private double copperLineWidth;
   private boolean copperTextItalic;
   private double copperTextSizeH;
   private double copperTextSizeV;
   private double copperTextThickness;
   private boolean copperTextUpright;
   private double courtyardLineWidth;
   private int dimensionPrecision;
   private int dimensionUnits;
   private DesignDimensions dimensions;
   private double fabLineWidth;
   private boolean fabTextItalic;
   private double fabTextSizeH;
   private double fabTextSizeV;
   private double fabTextThickness;
   private boolean fabTextUpright;
   private double otherLineWidth;
   private boolean otherTextItalic;
   private double otherTextSizeH;
   private double otherTextSizeV;
   private double otherTextThickness;
   private boolean otherTextUpright;
   private DesignPads pads;
   private double silkLineWidth;
   private boolean silkTextItalic;
   private double silkTextSizeH;
   private double silkTextSizeV;
   private double silkTextThickness;
   private boolean silkTextUpright;
   private DesignZones zones;
}
