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

import java.io.File;
import java.io.FileReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.parresum.kicad.parser.model.TestModel;
import de.parresum.kicad.parser.sexpr.SExpressionParser;

public class TestParser {

   @Test
   public void test1Simple() throws Exception {
      final File file = new File("src/test/resources/example.sex");

      final Object result = SExpressionParser.parse(new FileReader(file), new TestModel());
      Assertions.assertNotNull(result);
   }

}
