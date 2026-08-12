/**
 *
 */
module de.parresum.kicad.parser {
   requires java.desktop;
   requires org.apache.logging.log4j;
   requires static lombok;

   exports de.parresum.kicad.parser.eescheme;
   exports de.parresum.kicad.parser.model;
   exports de.parresum.kicad.parser.sexpr;

}