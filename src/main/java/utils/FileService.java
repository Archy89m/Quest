package utils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileService {

    public static List<String> getResourceFiles(ServletContext context) throws IOException {

        List<String> fileList = new ArrayList<>();
        String fullPath = context.getRealPath("/WEB-INF/classes/questStories/");

        File directory = new File(fullPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile())
                        fileList.add(file.getName());
                }
            }
        }
        return fileList;
    }

    public static List<String> getQuestNames(ServletContext context) throws IOException {

        List<String> fileList = getResourceFiles(context);
        fileList.replaceAll(filename -> filename.replace(".yml", ""));
        return fileList;
    }
}
