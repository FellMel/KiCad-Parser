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
public class Rules {
   private Boolean allowBlindBuriedVias;
   private Boolean allowMicrovias;
   private Double maxError;
   private Double minApertureClearance;
   private Double minClearance;
   private Double minConnection;
   private Double minCopperEdgeClearance;
   private Double minGrooveWidth;
   private Double minHoleClearance;
   private Double minHoleToHole;
   private Double minMicroviaDiameter;
   private Double minMicroviaDrill;
   private Double minResolvedSpokes;
   private Double minSilkClearance;
   private Double minTextHeight;
   private Double minTextThickness;
   private Double minThroughHoleDiameter;
   private Double minTrackWidth;
   private Double minViaAnnularWidth;
   private Double minViaAnnulus;
   private Double minViaDiameter;
   private Double solderMaskClearance;
   private Double solderMaskMinWidth;
   private Double solderMaskToCopperClearance;
   private Double solderPasteClearance;
   private Double solderPasteMarginRatio;
   private Boolean useHeightForLengthCalcs;

}
