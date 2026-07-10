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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PositionAt extends Position {

   /**
    * Optional angle of the element in degree.
    *
    * Text-Elements are in tenth of degrees.
    */
   @SExprParameter(3)
   private Double angle;

   @SExprParameter(4)
   private String locked;

   public PositionAt(final double x, final double y, final Double angle) {
      super(x, y);
      this.angle = angle;
      this.locked = locked;
   }

   public PositionAt(final double x, final double y, final Double angle, final String locked) {
      super(x, y);
      this.angle = angle;
      this.locked = locked;
   }

}
