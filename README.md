# KiCad-Parser
A simple parser to read and write [KiCad](https://www.kicad.org) files in Java

The parser is tested to read all Sample files found in KiCad project.

The writer isn't tested fully, yet

S-Expression parser and Java Model
==================================

Model Classes are designed as POJO with annotations to control parsing and writing. The model classes must contain a public default constructor.

Example:

    public class TestModel {
    
       @SExprParameter(1)
       private int val;
    
       @SExprParameter(2)
       private double flo;
    
       @SExprParameter(3)
       private String world;
    
       @SExprParameter(4)
       private boolean yes;
    
       @SExprSymbol("sub")
       private SubModel sub;
    
       @SExprSymbol("list")
       private List<ListModel> list;
    
    }

There are two main annotations:
* SExprParameter marks a field as simple atom in SExpr. It's value is the position within the enclosing Element.
* SExprSymbol is a complex Element. It's value is the name of the element

Additional there is a third annotation, SExprModel, which could control the order of the values written to output.

The example object could be represented by this SExpr

    (test 9955 3.1415 "Greetings\nto all" no
      (sub "Heinz" "uuid")
      (list "einstein" "albert")
      (list "curie" "marie")
    )

Supported types
---------------

Currently Integer, Long, Double, Boolean, Enums and String are supported.

Double values are written with up to 4 decimals

Booleans are read with 'yes' or 'true' as true. All other values are treated as false.
On writing 'yes' and 'no' are used

Lists of this types are supported, too. The type is recognized by the generic parameter via reflection. 

Complex objects must be defined and annotated.
 
SExprParameter
--------------
Marks a field as simple atom within SExpr. Its value is the position within the enclosing list.
While reading, the parser tries to read the value as the given type. When the value can't be converted, the field is skipped and 
the parser tries the next parameter field.
A special meaning is the position -1. This is used for parameter lists. In this case, there is only one parameter value allowed.

Beside the value, there are two further optional values in this annotation
* symbolSetType: controls some special modes to convert the value
* parameterMappings: for boolean types, there is the possibility to give some alternate values for true. On writing, the first value of the mapping is used.

SExprSymbol
-----------
Fields with this annotation is parsed as complex objects aka list in SExpr. 

A complex object has a name and one ore more values which can be atoms or complex objects, too.

When a primitive type (or its wrapper) is annotated as symbol, it has one further value

    @SexprSymbol("home")
    String home;
    
    (home "my home is my castle")

The value is converted to the type.

On Lists, the symbol has one ore more value of the same type.

    @SExprSymbol("my_list")
    List<Integer> myList;
    
    (my_list 12 43 90)
    
The type is automatically recognized by the parser.

On Objects, the symbol has a bunch of atoms and/or lists, representing the data of the given object.
    
Additional to the value, the annotation can have a symbolSetType and a parameterMapping, too.


SExprModel
----------
The SExprModel annotation can be used on classes. Currently only the option 'order' is supported. This value contains the names
of the fields for writing in a given order. All other annotated fields will be appended after these fields in an unspecified order. 

SExprSymbolType
---------------
With this value, it is possible to control the parsing and writing of values

* SET_PARAMETER is the default behavior. The value is simply converted to the target type
* IMPLICITE_BOOL_TRUE is a special handling for boolean values. In this mode, a boolean element don't need a value. 
  It's existence (without a value) is treated as true
* TREAT_PARAM_AS_ONE_STRING tells the parser to read the value as string even when it isn't enclosed in quotes

Parsing and Writing objects
===========================

Objects can simply written to file by

      final FileWriter file = new FileWriter("test.sexp");
      final SExpressionWriter writer = new SExpressionWriter(file);
      SExprClassHelper.write(writer, "test", test);
      file.close();

The object test will be written to the file 'test.sexp' according to the annotations defined in its class

For reading the object back you can use this code

     final Object result = SExpressionParser.parse(new FileReader("test.sexp"), new TestModel());

The file is read and the fields in TestModel are filled.

KiCad Objects
=============

Currently only schematic files ('*.kicad_sch') are supported. The documentation of the objects and fields can be read at
[KiCad File formats](https://dev-docs.kicad.org/en/file-formats/index.html).

Sadly not all fields are documented, yet, but the parser should work on all files created by KiCad 10.0
 
