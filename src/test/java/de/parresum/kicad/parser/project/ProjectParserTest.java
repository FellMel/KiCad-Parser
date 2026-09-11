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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 *
 *
 * @author Kai Uwe Bachmann
 */
public class ProjectParserTest {
   @Test
   @Order(1)
   public void testPrepare() throws IOException {
      final File srcDir = new File("src/test/resources/project");
      final File destDir = new File("target/test/projectKopie");
      destDir.mkdirs();

      final File[] listFiles = srcDir.listFiles();
      for (final File file : listFiles) {
         final File toFile = new File(destDir, file.getName());
         Files.copy(file.toPath(), toFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
      }

   }

   @Test
   @Order(2)
   public void testParseAll() throws Exception {
      final File dir = new File("target/test/projectKopie");

      final File[] listFiles = dir.listFiles();
      Arrays.sort(listFiles, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
      for (final File file : listFiles) {
         if (file.isFile() && file.getName().endsWith("kicad_pro")) {
            parse(file);
         }
      }
   }

   private void parse(final File file) throws IOException {
      System.err.println("Parsing file " + file.getName());

      final Project project = ProjectParser.readProject(file);

      // nach erfolgreichem Parsen von hier löschen
      file.delete();

   }

}
