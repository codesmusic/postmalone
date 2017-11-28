import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Parse java file to class, attributes, methods,...
 * @author Nguyen Duy Quang
 */
public class ParserJava
{
    private String content = "";
    private int contentHeight;
    private int contentWidth;
    private String extendRelationship = "";
    private String implementRelationship = "";
    private String classes = "";
    private String methods = "";
    private String attributes = "";

    public static void main(String[] args) throws Exception
    {
//        ParserJava java1 = new ParserJava("/home/linuxpenguin/IdeaProjects/Seminar/src/Parser.java");

        String projectPath = "/home/linuxpenguin/IdeaProjects/milkcoffee/";
        FileFinder fileFinder = new FileFinder(projectPath);
        File[] javaList = fileFinder.getFileList();
        ParserJava[] java1 = new ParserJava[javaList.length];
        int i = 0;
        for (File java : javaList)
        {
            java1[i] = new ParserJava(java.getAbsolutePath());
            i++;
            System.out.println(java.getAbsolutePath());
        }
        int j = 0;
        for(File java : javaList)
        {
            System.out.println(java1[j].getClasses());
            System.out.println(java1[j].getMethods());
            System.out.println(java1[j].getExtendRelationship());
            j++;
        }
    }

    /**
     * Constructor
     * @param filePath Path of the file
     * @throws IOException
     */
    public ParserJava(String filePath) throws IOException
    {
        FileInputStream file = new FileInputStream(filePath);
        CompilationUnit unit = JavaParser.parse(file);

        getClassParser(unit);
    }

    public String getContent() {
        return content;
    }

    public int getContentHeight() {
        return contentHeight;
    }

    public int getContentWidth() {
        return contentWidth;
    }

    public String getExtendRelationship() {
        return extendRelationship;
    }

    public String getImplementRelationship() {
        return implementRelationship;
    }

    public String getClasses() {
        return classes;
    }

    public String getMethods() {
        return methods;
    }

    public String getAttributes() {
        return attributes;
    }

    /**
     * Parse java class
     * @param unit Compilation unit
     */
    private void getClassParser(CompilationUnit unit)
    {
        NodeList<TypeDeclaration<?>> types = unit.getTypes();
        for (TypeDeclaration<?> type : types)
        {
            if (type instanceof ClassOrInterfaceDeclaration)
            {

                ClassOrInterfaceDeclaration classOrInterface = (ClassOrInterfaceDeclaration) type;
<<<<<<< Updated upstream

                // Get class
                content += classOrInterface.getNameAsString() + "\n";
                classes += classOrInterface.getNameAsString() + "\n";
                contentHeight++;
                calculateContentWidth(classOrInterface.getNameAsString().length());

                // Get class relationship
                NodeList<ClassOrInterfaceType> classList = classOrInterface.getExtendedTypes();
                NodeList<ClassOrInterfaceType> interfaceList = classOrInterface.getImplementedTypes();
                for (ClassOrInterfaceType list : classList)
                {
                    extendRelationship += list.getNameAsString() + " ";
                }
                for (ClassOrInterfaceType list : interfaceList)
                {
                    implementRelationship += list.getNameAsString() + " ";
                }

                // Get members in class
                NodeList<BodyDeclaration<?>> members = classOrInterface.getMembers();

                for (BodyDeclaration<?> member : members)
                {
                    // Get constructors
                    if (member instanceof ConstructorDeclaration)
                    {
                        ConstructorDeclaration constructor = (ConstructorDeclaration) member;
                        content += ConstructorParser(constructor) + "\n";
                        methods += ConstructorParser(constructor) + "\n";
                        contentHeight++;
                        calculateContentWidth(ConstructorParser(constructor).length());
                    }

                    // Get fields
                    if (member instanceof FieldDeclaration)
                    {
                        FieldDeclaration field = (FieldDeclaration) member;
                        content += AttributeParser(field) + "\n";
                        attributes += AttributeParser(field) + "\n";
                        contentHeight++;
                        calculateContentWidth(AttributeParser(field).length());
                    }

                    // Get methods
                    if (member instanceof MethodDeclaration)
                    {
                        MethodDeclaration method = (MethodDeclaration) member;
                        content += MethodParser(method) + "\n";
                        methods += MethodParser(method) + "\n";
                        contentHeight++;
                        calculateContentWidth(MethodParser(method).length());
=======
                if (classOrInterface.isClassOrInterfaceDeclaration())
                {
                    // Get class
                    classes += classOrInterface.getNameAsString() + "\n";
                    contentHeight++;
                    calculateContentWidth(classOrInterface.getNameAsString().length());

                    // Get class relationship
                    NodeList<ClassOrInterfaceType> classList = classOrInterface.getExtendedTypes();
                    NodeList<ClassOrInterfaceType> interfaceList = classOrInterface.getImplementedTypes();
                    for (ClassOrInterfaceType list : classList)
                    {
                        extendRelationship += list.getNameAsString() + " ";
                    }
                    for (ClassOrInterfaceType list : interfaceList)
                    {
                        implementRelationship += list.getNameAsString() + " ";
                    }

                    // Get members in class
                    NodeList<BodyDeclaration<?>> members = classOrInterface.getMembers();

                    for (BodyDeclaration<?> member : members)
                    {
                        // Get constructors
                        if (member instanceof ConstructorDeclaration)
                        {
                            ConstructorDeclaration constructor = (ConstructorDeclaration) member;
                            content += ConstructorParser(constructor) + "\n";
                            methods += ConstructorParser(constructor) + "\n";
                            contentHeight++;
                            calculateContentWidth(ConstructorParser(constructor).length());
                        }

                        // Get fields
                        if (member instanceof FieldDeclaration)
                        {
                            FieldDeclaration field = (FieldDeclaration) member;
                            content += AttributeParser(field) + "\n";
                            attributes += AttributeParser(field) + "\n";
                            contentHeight++;
                            calculateContentWidth(AttributeParser(field).length());
                        }

                        // Get methods
                        if (member instanceof MethodDeclaration)
                        {
                            MethodDeclaration method = (MethodDeclaration) member;
                            content += MethodParser(method) + "\n";
                            methods += MethodParser(method) + "\n";
                            contentHeight++;
                            calculateContentWidth(MethodParser(method).length());
                        }
>>>>>>> Stashed changes
                    }
                }

            }

        }
    }

    /**
     * Parse attributes in class
     * @param field Field declaration
     * @return Information of attributes
     */
    private String AttributeParser(FieldDeclaration field)
    {
        String name = "";
        NodeList<VariableDeclarator> variables = field.getVariables();
        for (VariableDeclarator variable : variables)
        {
            name = variable.getNameAsString();
        }

        String type = " : " + field.getElementType().asString();

        String visibility = "";
        if (field.isPublic())
            visibility = "+ ";
        else if (field.isPrivate())
            visibility = "- ";
        else if (field.isProtected())
            visibility = "# ";

        return visibility + name + type;
    }

    /**
     * Parse method to string
     * @param method Methods
     * @return Information of methods
     */
    private String MethodParser(MethodDeclaration method)
    {
        String visibility = "";
        if (method.isPublic())
            visibility = "+ ";
        else if (method.isPrivate())
            visibility = "- ";
        else if (method.isProtected())
            visibility = "# ";

        String string = method.getDeclarationAsString(false,false,false);
        String type = string.substring(0, string.indexOf(" "));
        String name = string.substring(string.indexOf(" ")+1, string.length());

        return visibility + name + " : " + type;
    }

    /**
     * Parse contructor in class
     * @param constructor Constructor
     * @return Information of constructor
     */
    private String ConstructorParser(ConstructorDeclaration constructor)
    {
        String visibility = "";
        if (constructor.isPublic())
            visibility = "+ ";
        else if (constructor.isPrivate())
            visibility = "- ";
        else if (constructor.isProtected())
            visibility = "# ";
        return visibility + constructor.getDeclarationAsString(false, false, false);
    }

    /**
     * Calculate width
     * @param stringWidth Maximum width of string
     */
    private void calculateContentWidth(int stringWidth)
    {
        if (stringWidth > contentWidth)
            contentWidth = stringWidth;
    }
}