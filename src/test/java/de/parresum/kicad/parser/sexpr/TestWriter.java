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
package de.parresum.kicad.parser.sexpr;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.parresum.kicad.parser.model.ListModel;
import de.parresum.kicad.parser.model.SubModel;
import de.parresum.kicad.parser.model.TestModel;
import de.parresum.kicad.parser.sexpr.SExpressionWriter;
import de.parresum.kicad.parser.sexpr.reflection.SExprClassHelper;

public class TestWriter {

   @Test
   public void testWrite() throws Exception {
      final TestModel test = new TestModel();
      test.setFlo(3.1415);
      test.setVal(9955);
      test.setYes(false);
      test.setWelt("Greetings\nto all");

      final SubModel sub = new SubModel();
      sub.setId("uuid");
      sub.setName("Heinz");
      test.setSub(sub);

      final ListModel list1 = new ListModel();
      list1.setId("kai");
      list1.setName("uwe");

      final ListModel list2 = new ListModel();
      list2.setId("manu");
      list2.setName("sigrid");

      final List<ListModel> list = new ArrayList<>();
      list.add(list1);
      list.add(list2);
      test.setList(list);

      final FileWriter file = new FileWriter("target/test.out");
      final SExpressionWriter writer = new SExpressionWriter(file);
      SExprClassHelper.write(writer, "test", test);
      file.close();
   }

}
