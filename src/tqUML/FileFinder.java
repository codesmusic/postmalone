package tqUML;

import java.io.File;
import java.util.ArrayList;

/**
 * This class is to find all the java file in project folder
 * @author Nguyen Duy Quang
 */
public class FileFinder
{
    // Store file paths
    private ArrayList<String> fileList = new ArrayList<>();

    /**
     * Default constructor
     */
    public FileFinder()
    {

    }

    /**
     * Constructor
     * @param projectPath Path of project folder
     */
    public FileFinder(String projectPath)
    {
        findFileJava(projectPath);
    }

    /**
     * Getter
     * @return Array list of file path
     */
    public ArrayList<String> getFileList() {
        return fileList;
    }

    /**
     * Find all java files in folder
     * @param projectPath Folder that contains java files
     * @return A list of java files
     */
    private void findFileJava(String projectPath)
    {

        File dir = new File(projectPath);

        File[] fList = dir.listFiles();
        for (File file : fList)
        {
            if (file.isFile())
            {
                if (file.getName().endsWith(".java"))
                {
                    fileList.add(file.getAbsolutePath());
                }
            }
            else if (file.isDirectory())
                findFileJava(file.getAbsolutePath());
        }
    }
}
