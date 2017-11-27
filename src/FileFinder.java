import java.io.File;
import java.io.FilenameFilter;

/**
 * This class is to find all the java file in project folder
 * @author Nguyen Duy Quang
 */
public class FileFinder
{
    public static void main(String[] args)
    {
        String projectPath = "/home/linuxpenguin/IdeaProjects/Seminar/";
        File[] javaFiles = findFileJava(getSrcPath(projectPath));
        for (File file : javaFiles)
            System.out.println(file);
    }

    /**
     * Navigate to the source folder of project
     * @param projectPath Path of project folder
     * @return Path of source folder that contains java files
     */
    private static String getSrcPath(String projectPath)
    {
        return projectPath + "/src";
    }

    /**
     * Find all java files in folder
     * @param folderPath Folder that contains java files
     * @return A list of java files
     */
    private static File[] findFileJava(String folderPath)
    {
        File dir = new File(folderPath);
        File[] fileList = dir.listFiles(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".java");
            }
        });
        return fileList;
    }
}
