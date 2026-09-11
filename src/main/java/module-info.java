/**
 *
 */
module de.parresum.kicad.parser {
   requires java.desktop;
   requires org.apache.logging.log4j;
   requires static lombok;
   requires com.fasterxml.jackson.core;
   requires com.fasterxml.jackson.databind;

   exports de.parresum.kicad.parser.eescheme;
   exports de.parresum.kicad.parser.eescheme.shape;
   exports de.parresum.kicad.parser.eescheme.label;
   exports de.parresum.kicad.parser.library;
   exports de.parresum.kicad.parser.model;
   exports de.parresum.kicad.parser.model.table;
   exports de.parresum.kicad.parser.sexpr;
   exports de.parresum.kicad.parser.project;

   opens de.parresum.kicad.parser.project to com.fasterxml.jackson.databind;
}