package quests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

public class QuestLoader {
    public static Quest loadQuestFromYaml(String yamlFilePath) throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        InputStream inputStream = QuestLoader.class.getClassLoader().getResourceAsStream(yamlFilePath);
        if (inputStream == null)
            throw new IllegalArgumentException("File " + yamlFilePath + " not found in resources");

        return mapper.readValue(inputStream, Quest.class);
    }
}
