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
public class Schematic {
   private int annotateStartNum;
   private SchematicAnnotation annotation;
   private String bomExportFilename;
   private List<String> bomFmtPresets;
   private BomFmtSettings bomFmtSettings;
   private List<BomPreset> bomPresets;
   private BomSettings bomSettings;

   private Map<String, List<String>> busAliases;
   private double connectionGridSize;
   private Drawing drawing;
   private String legacyLibDir;
   private List<String> legacyLibList;
   private Meta meta;
   private String netFormatName;
   private Ngspice ngspice;
   private String pageLayoutDescrFile;
   private String plotDirectory;
   private boolean reuseDesignators;
   private boolean spaceSaveAllEvents;
   private boolean spiceAdjustPassiveValues;
   private boolean spiceCurrentSheetAsRoot;
   private String spiceExternalCommand;
   private boolean spiceModelCurrentSheetAsRoot;
   private boolean spiceSaveAllCurrents;
   private boolean spiceSaveAllDissipations;
   private boolean spiceSaveAllVoltages;

   private int subpartFirstId;
   private int subpartIdSeparator;

   private List<TopLevelSheet> topLevelSheets;
   private String usedDesignators;
   private List<Variant> variants;

   private List<String> lastOpenedFiles;

}
