/**
 *
 */
module de.parresum.kicad.parser {
   requires java.desktop;
   requires org.apache.logging.log4j;
   requires static lombok;

   exports de.parresum.kicad.parser.eescheme;
   exports de.parresum.kicad.parser.eescheme.shape;
   exports de.parresum.kicad.parser.eescheme.label;
   exports de.parresum.kicad.parser.library;
   exports de.parresum.kicad.parser.model;
   exports de.parresum.kicad.parser.model.table;
   exports de.parresum.kicad.parser.sexpr;

}