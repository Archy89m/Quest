package quests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Quest(String title,
                    String story,
                    Map<String, Decision> decisions,
                    Map<String, Ending> endings) {

    public record Decision(String prompt, List<Option> options) {}

    public record Option(String title, String story, String next) {}

    public record Ending(String story) {}

    public static Quest loadQuestFromYaml(String yamlFilePath) throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        InputStream inputStream = Quest.class.getClassLoader().getResourceAsStream(yamlFilePath);
        if (inputStream == null)
            throw new IllegalArgumentException("File " + yamlFilePath + " not found in resources");

        return mapper.readValue(inputStream, Quest.class);
    }

    public List<String> getTitleOptions(String nextStep) {

        List<Quest.Option> options = decisions().get(nextStep).options();
        List<String> titleOptions = new ArrayList<>();

        for (Quest.Option option: options)
            titleOptions.add(option.title());

        return titleOptions;
    }

    public HashMap<String, String> getAnswerData(String currentStep, String answer) {

        HashMap<String, String> answerData = new HashMap<>();

        Quest.Decision currentDecision = decisions().get(currentStep);

        for (Quest.Option option: currentDecision.options()) {
            if (option.title().equals(answer)) {
                answerData.put("answerStory", option.story());
                answerData.put("nextStep", option.next());
            }
        }
        return answerData;
    }
}