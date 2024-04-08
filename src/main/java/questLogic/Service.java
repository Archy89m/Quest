package questLogic;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Service {

    public static List<String> getResourceFiles(ServletContext context) throws IOException {

        List<String> fileList = new ArrayList<>();
        String fullPath = context.getRealPath("/WEB-INF/classes/");

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

}
