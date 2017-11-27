import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Parser extends FileFinder implements Interface, Interfacetest1
{
    protected int a;
    private String b;

    public static void main(String[] args)
    {
        File file = new File("/home/linuxpenguin/IdeaProjects/Seminar/src/test");
        // Initialize buffer reader.
        BufferedReader bufferedReader = null;
        try
        {
            // Open file for reading.
            bufferedReader = new BufferedReader(new FileReader(file));

            // Read all content in the file.
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
//                String className = classParser(line);    ///////////////////////////////////////////////////////
//                if(!isEmptyString(className))
//                    System.out.println(className);
                String attributeName = attributeParser(line);
                if(!isEmptyString(attributeName, 5, 6));
                    System.out.println(attributeName);
            }
        }
        catch (IOException ex)
        {
            // If there is no file.
            System.out.println("An IOException was caught!");
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                // Close the file.
                bufferedReader.close();
            }
            catch (IOException ex)
            {
                // If there is no file.
                System.out.println("An IOException was caught!");
                ex.printStackTrace();
            }
        }
    }

    public Parser()
    {

    }

    public Parser(String abc, int o)
    {

    }

    public static String classParser(String aString)
    {
        String unprocessString = aString;
        StringTokenizer stringTokenizer = new StringTokenizer(aString);

        // Create new empty string to store tokens.
        String newString = "";

        // Access to sum tokens.
        boolean access = false;
        while(stringTokenizer.hasMoreTokens())
        {
            String str = stringTokenizer.nextToken();
            if(str.equals("class"))
            {
                access = true;
                str = stringTokenizer.nextToken();
            }
            if(str.endsWith("{"))
                str = str.replace("{", "");
            if(access && !str.equals("{"))
                newString += str + " ";
        }
        return newString;
    }

    public static String attributeParser(String aString)
    {
        StringTokenizer stringTokenizer = new StringTokenizer(aString);
        String newString = "";
        String visibility = "";
        String type = "";
        String name = "";
        while(stringTokenizer.hasMoreTokens())
        {
            String str = stringTokenizer.nextToken();
            if (str.equals("public") || str.equals("private") || str.equals("protected") || str.equals("package"))
            {
                if (str.equals("public"))
                    visibility += "+";
                else if (str.equals("private"))
                    visibility += "-";
                else if (str.equals("protected"))
                    visibility += "#";
                else
                    visibility += "~";

                str = stringTokenizer.nextToken();

                if (str.equals("class"))
                {
                    str = stringTokenizer.nextToken();
                    name += str;
                    return name;
                }
                else
                {
                    type += ": " + str;
                    str = stringTokenizer.nextToken();
                    if (str.endsWith(";"))
                        name = str.replace(";", "");
                    else
                        name += str;
                }
            }
        }
        return visibility + name + type;
    }


    /**
     * Check if string is empty or not.
     * @param aString String input.
     * @return True if string is empty, false if string is not.
     */
    private static boolean isEmptyString(String aString, int a, int b)
    {
        if (aString.equals(""))
            return true;
        return false;
    }
}
