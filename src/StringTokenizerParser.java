//
//import java.util.StringTokenizer;
//
///**
// * This class is to parse string to tokens using StringTokenizer
// * @author Duy Quang
// */
//public class StringTokenizerParser
//{
//    public static String parserStringFromLine(String aString)
//    {
//        StringTokenizer stringTokenizer = new StringTokenizer(aString);
//
//        // Create new empty string to store tokens.
//        String newString = "";
//
//        // Access to sum tokens.
//        boolean access = false;
//        while(stringTokenizer.hasMoreTokens())
//        {
//            String str = stringTokenizer.nextToken();
//            if(str.equals("class"))
//            {
//                access = true;
//                str = stringTokenizer.nextToken();
//            }
//            if(access && !str.equals("{"))
//                newString += str + " ";
//        }
//        return newString;
//    }
//
//    private static void printString(String aString)
//    {
//        System.out.println(aString);
//    }
//}
