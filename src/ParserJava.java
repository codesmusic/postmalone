package tq.s.uml;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Parse java file to class, attributes, methods,...
 *
 * @author Nguyen Duy Quang
 */
public class ParserJava {

    private String content = ""; // Store class name, attributes, methods, and relationship between classes
    private int contentHeight; // Number of lines in content
    private int contentWidth; // Width of the longest line in content
    private String extendRelationship = ""; // Store class that class extends
    private String implementRelationship = ""; // Store interfaces that class implements
    private String classes = ""; // Store class name
    private String methods = ""; // Store class methods
    private String attributes = ""; // Store class attributes
    private boolean appeared = false; // Java class appearance

//    public static void main(String[] args) throws ParseProblemException
//    {
//        // Find all the java files in project recursively. Need to change path to project folder
//        FileFinder fileFinder = new FileFinder("/home/linuxpenguin/IdeaProjects/milkcoffee/");
//
//        // An array that store java file path
//        ArrayList<String> javaList =  fileFinder.getFileList();
//
//        // Number of java file inside javaList
//        int size = javaList.size();
//
//        // Create an array of ParserJava
//        ParserJava[] javas = new ParserJava[size];
//
//        System.out.println(size);
//
//        for (int i = 0; i < size; i++)
//        {
//            System.out.println(javaList.get(i));
//            javas[i] = new ParserJava(javaList.get(i));
//        }
//
//        // Print all methods of all java files
//        for (ParserJava java : javas)
//        {
//            System.out.println(java.getMethods());
//        }
//
//    }
    /**
     * Constructor
     *
     * @param filePath Path of the file
     */
    public ParserJava(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            CompilationUnit unit = JavaParser.parse(file);

            getClassParser(unit);
        } catch (IOException ex) {
            System.out.println("No file was found!");
            ex.printStackTrace();
        } catch (ParseProblemException ex) {
            System.out.println("Input java file was wrong!");
            ex.printStackTrace();
        }
    }
    
    /**
     * Content getter
     * @return Content of java file that is parsed
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Content height getter
     * @return Number of lines
     */
    public int getContentHeight() {
        return contentHeight;
    }
    
    /**
     * Content width getter
     * @return Width of content
     */
    public int getContentWidth() {
        return contentWidth;
    }

    /**
     * Extend getter
     * @return Extend class that being extended
     */
    public String getExtendRelationship() {
        return extendRelationship;
    }

    /**
     * Implement getter
     * @return Implement class that being implemented
     */
    public String getImplementRelationship() {
        return implementRelationship;
    }

    /**
     * Class name getter
     * @return Name of class in java file
     */
    public String getClasses() {
        return classes;
    }

    /**
     * Methods getter
     * @return Methods of java file
     */
    public String getMethods() {
        return methods;
    }
    
    /**
     * Attributes getter
     * @return Attributes of java file
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * Extend setter
     * @param extendRelationship Name of class being set
     */
    public void setExtendRelationship(String extendRelationship) {
        this.extendRelationship = extendRelationship;
    }

    /**
     * Parse java class
     *
     * @param unit Compilation unit
     */
    private void getClassParser(CompilationUnit unit) {
        NodeList<TypeDeclaration<?>> types = unit.getTypes();
        for (TypeDeclaration<?> type : types) {
            if (type instanceof ClassOrInterfaceDeclaration) {

                ClassOrInterfaceDeclaration classOrInterface = (ClassOrInterfaceDeclaration) type;

                // Get class
                content += classOrInterface.getNameAsString() + "\n";
                classes += classOrInterface.getNameAsString();
                contentHeight++;
                calculateContentWidth(classOrInterface.getNameAsString().length());

                // Get class relationship
                NodeList<ClassOrInterfaceType> classList = classOrInterface.getExtendedTypes();
                NodeList<ClassOrInterfaceType> interfaceList = classOrInterface.getImplementedTypes();
                for (ClassOrInterfaceType list : classList) {
                    extendRelationship += list.getNameAsString() + " ";
                }
                for (ClassOrInterfaceType list : interfaceList) {
                    implementRelationship += list.getNameAsString() + " ";
                }

                // Get members in class
                NodeList<BodyDeclaration<?>> members = classOrInterface.getMembers();

                for (BodyDeclaration<?> member : members) {
                    // Get constructors
                    if (member instanceof ConstructorDeclaration) {
                        ConstructorDeclaration constructor = (ConstructorDeclaration) member;
                        content += ConstructorParser(constructor) + "\n";
                        methods += ConstructorParser(constructor) + "\n";
                        contentHeight++;
                        calculateContentWidth(ConstructorParser(constructor).length());
                    }

                    // Get fields
                    if (member instanceof FieldDeclaration) {
                        FieldDeclaration field = (FieldDeclaration) member;
                        content += AttributeParser(field) + "\n";
                        attributes += AttributeParser(field) + "\n";
                        contentHeight++;
                        calculateContentWidth(AttributeParser(field).length());
                    }

                    // Get methods
                    if (member instanceof MethodDeclaration) {
                        MethodDeclaration method = (MethodDeclaration) member;
                        content += MethodParser(method) + "\n";
                        methods += MethodParser(method) + "\n";
                        contentHeight++;
                        calculateContentWidth(MethodParser(method).length());
                    }
                }
            }

        }
    }

    /**
     * Parse attributes in class
     *
     * @param field Field declaration
     * @return Information of attributes
     */
    private String AttributeParser(FieldDeclaration field) {
        String name = "";
        NodeList<VariableDeclarator> variables = field.getVariables();
        for (VariableDeclarator variable : variables) {
            name = variable.getNameAsString();
        }

        String type = " : " + field.getElementType().asString();

        String visibility = "";
        if (field.isPublic()) {
            visibility = "+ ";
        } else if (field.isPrivate()) {
            visibility = "- ";
        } else if (field.isProtected()) {
            visibility = "# ";
        }

        return visibility + name + type;
    }

    /**
     * Parse method to string
     *
     * @param method Methods
     * @return Information of methods
     */
    private String MethodParser(MethodDeclaration method) {
        String visibility = "";
        if (method.isPublic()) {
            visibility = "+ ";
        } else if (method.isPrivate()) {
            visibility = "- ";
        } else if (method.isProtected()) {
            visibility = "# ";
        }

        String string = method.getDeclarationAsString(false, false, false);
        String type = string.substring(0, string.indexOf(" "));
        String name = string.substring(string.indexOf(" ") + 1, string.length());

        return visibility + name + " : " + type;
    }

    /**
     * Parse contructor in class
     *
     * @param constructor Constructor
     * @return Information of constructor
     */
    private String ConstructorParser(ConstructorDeclaration constructor) {
        String visibility = "";
        if (constructor.isPublic()) {
            visibility = "+ ";
        } else if (constructor.isPrivate()) {
            visibility = "- ";
        } else if (constructor.isProtected()) {
            visibility = "# ";
        }
        return visibility + constructor.getDeclarationAsString(false, false, false);
    }

    /**
     * Calculate the maximum width of string
     *
     * @param stringWidth Width of string
     */
    private void calculateContentWidth(int stringWidth) {
        if (stringWidth > contentWidth) {
            contentWidth = stringWidth;
        }
    }

    /**
     * Replace the appearance of extend class
     * @param javas Name of class being set
     */
    public static void replaceAppearance(ParserJava[] javas) {
        for (ParserJava java : javas) {
            for (ParserJava jav : javas) {
                if (java.getExtendRelationship().trim().equals(jav.getClasses().trim()) && !java.appeared) {
                    java.appeared = true;
                }
            }
        }
        for (ParserJava java : javas) {
            if (!java.appeared) {
                java.setExtendRelationship("");
            }
        }
    }
}
