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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Drawing {
   private double dashedLinesDashLengthRatio;
   private double dashedLinesGapLengthRatio;
   private double defaultBusThickness;
   private double defaultJunctionSize;
   private double defaultLineThickness;
   private double defaultTextSize;
   private double defaultWireThickness;
   private List<String> fieldNames;
   private int hopOverSizeChoice;
   private boolean intersheetsRefOwnPage;
   private String intersheetsRefPrefix;
   private boolean intersheetsRefShort;
   private boolean intersheetsRefShow;
   private String intersheetsRefSuffix;
   private int junctionSizeChoice;
   private double labelSizeRatio;

   @JsonProperty("operating_point_overlay_i_precision")
   private int operatingPointOverlayIPrecision;

   @JsonProperty("operating_point_overlay_i_range")
   private String operatingPointOverlayIRange;

   @JsonProperty("operating_point_overlay_v_precision")
   private int operatingPointOverlayVPrecision;

   @JsonProperty("operating_point_overlay_v_range")
   private String operatingPointOverlayVRange;

   private double overbarOffsetRatio;

   private double pinSymbolSize;

   private double textOffsetRatio;

}
