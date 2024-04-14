package quests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public record Quest(String title,
                    String story,
                    Map<String, Decision> decisions,
                    Map<String, Ending> endings) {

    public static final String QUESTS_PATH_WEB_SERVER = "/WEB-INF/classes/questStories/";
    public static final String QUESTS_PATH_SOURCE = "questStories/";
    public static final String OPTION_SELECT = "Select option:";
    public static final String OPTION_RESULT = "Result:";
    public static final String QUEST_FILE_FORMAT = ".yml";

    public record Decision(String prompt, List<Option> options) {}

    public record Option(String title, String story, String next) {}

    public record Ending(String story) {}

    public static Quest loadQuestFromYaml(String ymlFilePath) throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        InputStream inputStream = Quest.class.getClassLoader().getResourceAsStream(ymlFilePath);
        if (inputStream == null)
            throw new IllegalArgumentException("File " + ymlFilePath + " not found in resources");

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

    public String getFirstStep() {
        return decisions().entrySet().iterator().next().getKey();
    }

    public String getNextStepJSP(String nextStep) {

        if (nextStep.contains("ending")) {
            return "/result.jsp";
        } else {
            return "/quest.jsp";
        }
    }
}