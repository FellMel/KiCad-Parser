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

public enum LayerType {

   F_CU("F.Cu"), // Front Coper Layer
   IN1_CU("In1.Cu"), // Inner Copper Layer 1
   IN2_CU("In2.Cu"), // Inner Copper Layer 2
   IN3_CU("In3.Cu"), // Inner Copper Layer 3
   IN4_CU("In4.Cu"), // Inner Copper Layer 4
   IN5_CU("In5.Cu"), // Inner Copper Layer 5
   IN6_CU("In6.Cu"), // Inner Copper Layer 6
   IN7_CU("In7.Cu"), // Inner Copper Layer 7
   IN8_CU("In8.Cu"), // Inner Copper Layer 8
   IN9_CU("In9.Cu"), // Inner Copper Layer 9
   IN10_CU("In10.Cu"), // Inner Copper Layer 10
   IN11_CU("In11.Cu"), // Inner Copper Layer 11
   IN12_CU("In12.Cu"), // Inner Copper Layer 12
   IN13_CU("In13.Cu"), // Inner Copper Layer 13
   IN14_CU("In14.Cu"), // Inner Copper Layer 14
   IN15_CU("In15.Cu"), // Inner Copper Layer 15
   IN16_CU("In16.Cu"), // Inner Copper Layer 16
   IN17_CU("In17.Cu"), // Inner Copper Layer 17
   IN18_CU("In18.Cu"), // Inner Copper Layer 18
   IN19_CU("In19.Cu"), // Inner Copper Layer 19
   IN20_CU("In20.Cu"), // Inner Copper Layer 20
   IN21_CU("In21.Cu"), // Inner Copper Layer 21
   IN22_CU("In22.Cu"), // Inner Copper Layer 22
   IN23_CU("In23.Cu"), // Inner Copper Layer 23
   IN24_CU("In24.Cu"), // Inner Copper Layer 24
   IN25_CU("In25.Cu"), // Inner Copper Layer 25
   IN26_CU("In26.Cu"), // Inner Copper Layer 26
   IN27_CU("In27.Cu"), // Inner Copper Layer 27
   IN28_CU("In28.Cu"), // Inner Copper Layer 28
   IN29_CU("In29.Cu"), // Inner Copper Layer 29
   IN30_CU("In30.Cu"), // Inner Copper Layer 30
   B_CU("B.Cu"), // Back Copper Layer
   B_ADHES("B.Adhes"), // Back adhesive Layer
   F_ADHES("F.Adhes"), // Front Adhesive Layer
   B_PASTE("B.Paste"), // Back solder Paste layer
   F_PASTE("F.Paste"), // Front solder Paste layer
   B_SILKS("B.SilkS"), // Back silk screen layer
   F_SILKS("F.SilkS"), // Front silk screen layer
   B_MASK("B.Mask"), // Back solder mask layer
   F_MASK("F.Mask"), // Front solder mask layer
   DWGS_USER("Dwgs.User"), // User drawing layer
   CMTS_USER("Cmts.User"), // User comments layer
   ECO1_USER("Eco1.User"), // user engeneering change order layer 1
   ECO2_USER("Eco2.User"), // user engeneering change order layer 2
   EDGE_CUTS("Edge.Cuts"), // Board outline layer
   F_CRTYD("F.CrtYd"), // Footprint Front courtyard layer
   B_CRTYD("B.CrtYd"), // Footprint Back courtyard layer
   F_FAB("F.Fab"), // Footprint Front fabrication layer
   B_FAB("B.Fab"), // Footprint Back fabrication layer
   USER_1("User.1"), // User defined layer 1
   USER_2("User.2"), // User defined layer 2
   USER_3("User.3"), // User defined layer 3
   USER_4("User.4"), // User defined layer 4
   USER_5("User.5"), // User defined layer 5
   USER_6("User.6"), // User defined layer 6
   USER_7("User.7"), // User defined layer 7
   USER_8("User.8"), // User defined layer 8
   USER_9("User.9") // User defined layer 9
   ;

   public final String name;

   private LayerType(final String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return name;
   }

}
