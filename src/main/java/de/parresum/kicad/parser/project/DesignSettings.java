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
import java.util.Map;

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
public class DesignSettings {
   private DesignDefaults defaults;
   private List<?> diffPairDimensions;
   private List<?> drcExclusions;
   private boolean ruleSeveritieslegacyCourtyardsOverlap;
   private boolean ruleSeveritieslegacyNoCourtyardDefined;
   private Meta meta;

   private Map<String, Severity> ruleSeverities;
   private Rules rules;

   private List<TeardropOption> teardropOptions;
   private List<TeardropParameter> teardropParameters;

   private List<?> trackWidths;

   private TuningPatternSettings tuningPatternSettings;

   private List<ViaDimensions> viaDimensions;
   private boolean zonesAllowExternalFillets;
   private boolean zonesUseNoOutline;

}
