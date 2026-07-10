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
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import de.parresum.kicad.parser.eescheme.Schematic;

public class TestExampleParser {
   @Test
   public void testParseAll() throws Exception {
      final File dir = new File("src/test/resources/exampleKopie");

      final File[] listFiles = dir.listFiles();
      Arrays.sort(listFiles);
      for (final File file : listFiles) {
         if (file.isFile() && file.getName().endsWith("kicad_sch")) {
            parse(file);
         }
      }
   }

   private void parse(final File file) {
      System.err.println("Parsing file " + file.getName());
      try (FileReader infile = new FileReader(file)) {
         final Object result = SExpressionParser.parse(infile, new Schematic());
         // nach erfolgreichem Parsen von hier löschen
         file.delete();
      } catch (final IOException e) {

      }

   }
}
