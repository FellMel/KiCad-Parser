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

import java.util.List;

import de.parresum.kicad.parser.annotations.SExprParameter;
import de.parresum.kicad.parser.annotations.SExprSymbol;

public class TestModel {

   @SExprParameter(1)
   private int val;

   @SExprParameter(2)
   private double flo;

   @SExprParameter(3)
   private String welt;

   @SExprParameter(4)
   private boolean yes;

   @SExprSymbol("sub")
   private SubModel sub;

   @SExprSymbol("list")
   private List<ListModel> list;

   public int getVal() {
      return val;
   }

   public void setVal(final int val) {
      this.val = val;
   }

   public double getFlo() {
      return flo;
   }

   public void setFlo(final double flo) {
      this.flo = flo;
   }

   public String getWelt() {
      return welt;
   }

   public void setWelt(final String welt) {
      this.welt = welt;
   }

   public boolean isYes() {
      return yes;
   }

   public void setYes(final boolean yes) {
      this.yes = yes;
   }

   public SubModel getSub() {
      return sub;
   }

   public void setSub(final SubModel sub) {
      this.sub = sub;
   }

   public List<ListModel> getList() {
      return list;
   }

   public void setList(final List<ListModel> list) {
      this.list = list;
   }
}
