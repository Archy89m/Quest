package services;

import quests.Quest;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Utils {

    public static List<String> getResourceFiles(ServletContext context, String catalogPath) {

        List<String> fileList = new ArrayList<>();
        String fullPath = context.getRealPath(catalogPath);

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

    public static List<String> getQuestNames(ServletContext context, String path) {

        List<String> fileList = getResourceFiles(context, path);
        fileList.replaceAll(filename -> filename.replace(Quest.QUEST_FILE_FORMAT, ""));
        return fileList;
    }
}