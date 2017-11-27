import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

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
        ParserJava java1 = new ParserJava("/home/linuxpenguin/IdeaProjects/Seminar/src/Parser.java");
//        System.out.println(java1.getContent());
        System.out.println(java1.getClasses());
        System.out.println(java1.getAttributes());
        System.out.println(java1.getMethods());
        System.out.println(java1.getContentHeight());
        System.out.println(java1.getContentWidth());
        System.out.println(java1.getExtendRelationship());
        System.out.println(java1.getImplementRelationship());
    }

    public ParserJava(String filePath) throws IOException
    {
        FileInputStream file = new FileInputStream(filePath);
        CompilationUnit unit = JavaParser.parse(file);

        getClassDeclaration(unit);
        getAttributeDeclaration(unit);
        getConstructorDeclaration(unit);
        getMethodDeclaration(unit);
        getRelationshipDeclaration(unit);

//        System.out.println(classes.trim());
//        System.out.println(attributes.trim());
//        System.out.println(methods);
//        System.out.println("MaxHeight :" + contentHeight);
//        System.out.println("MaxWidth :" + contentWidth);
//        System.out.println("Extends :" + extendRelationship);
//        System.out.println("Implement :" + implementRelationship);
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
     * Get the declaration of attributes
     * @param unit Compilation unit
     */
    private void getAttributeDeclaration(CompilationUnit unit)
    {
        NodeList<TypeDeclaration<?>> types = unit.getTypes();
        for (TypeDeclaration<?> type : types)
        {
            NodeList<BodyDeclaration<?>> members = type.getMembers();
            for (BodyDeclaration<?> member : members)
            {
                if (member instanceof FieldDeclaration)
                {
                    FieldDeclaration field = (FieldDeclaration) member;
                    content += AttributeParser(field) + "\n";
                    attributes += AttributeParser(field) + "\n";
                    contentHeight++;
                    calculateContentWidth(AttributeParser(field).length());
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
     * Get the declaration of class
     * @param unit Compilation unit
     * @return Name of class
     */
    private void getClassDeclaration(CompilationUnit unit)
    {
        NodeList<TypeDeclaration<?>> types = unit.getTypes();
        for (TypeDeclaration<?> type : types)
        {
            if (type instanceof ClassOrInterfaceDeclaration)
            {
                ClassOrInterfaceDeclaration name = (ClassOrInterfaceDeclaration) type;
                content += name.getNameAsString() + "\n";
                classes += name.getNameAsString() + "\n";
                contentHeight++;
                calculateContentWidth(name.getNameAsString().length());
            }
        }
    }

    /**
     * Get the declaration of methods
     * @param unit Compilation unit
     */
    private void getMethodDeclaration(CompilationUnit unit)
    {
        NodeList<TypeDeclaration<?>> types = unit.getTypes();
        for (TypeDeclaration<?> type : types)
        {
            NodeList<BodyDeclaration<?>> members = type.getMembers();
            for (BodyDeclaration<?> member : members)
            {
                if (member instanceof MethodDeclaration)
                {
                    MethodDeclaration method = (MethodDeclaration) member;
                    content += MethodParser(method) + "\n";
                    methods += MethodParser(method) + "\n";
                    contentHeight++;
                    calculateContentWidth(MethodParser(method).length());
                }
            }
        }
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
     * Get extends or implements of class
     * @param unit Compilation unit
     */
    private void getRelationshipDeclaration(CompilationUnit unit)
    {
        NodeList<TypeDeclaration<?>> types = unit.getTypes();
        for (TypeDeclaration<?> type : types)
        {
            if (type instanceof ClassOrInterfaceDeclaration)
            {
                ClassOrInterfaceDeclaration classOrInterface = (ClassOrInterfaceDeclaration) type;
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
            }
        }
    }

    /**
     * Get constructor declaration
     * @param unit Compilation unit
     */
    private void getConstructorDeclaration(CompilationUnit unit)
    {
        NodeList<TypeDeclaration<?>> types = unit.getTypes();
        for (TypeDeclaration<?> type : types)
        {
            NodeList<BodyDeclaration<?>> members = type.getMembers();
            for (BodyDeclaration<?> member : members)
            {
                if (member instanceof ConstructorDeclaration)
                {
                    ConstructorDeclaration constructor = (ConstructorDeclaration) member;
                    content += ConstructorParser(constructor) + "\n";
                    methods += ConstructorParser(constructor) + "\n";
                    contentHeight++;
                    calculateContentWidth(ConstructorParser(constructor).length());
                }
            }
        }
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

    private void calculateContentWidth(int stringWidth)
    {
        if (stringWidth > contentWidth)
            contentWidth = stringWidth;
    }
}