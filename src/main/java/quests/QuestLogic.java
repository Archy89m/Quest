package quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestLogic {

    public static List<String> getTitleOptions(List<Quest.Option> options) {

        List<String> titleOptions = new ArrayList<>();

        for (Quest.Option option: options)
            titleOptions.add(option.title());

        return titleOptions;
    }

    public static HashMap<String, String> getAnswerData(Quest quest, String currentStep, String answer) {

        HashMap<String, String> answerData = new HashMap<>();

        Quest.Decision currentDecision = quest.decisions().get(currentStep);

        for (Quest.Option option: currentDecision.options()) {
            if (option.title().equals(answer)) {
                answerData.put("answerStory", option.story());
                answerData.put("nextStep", option.next());
            }
        }
        return answerData;
    }

    public static String getNextStepJSP(String nextStep) {

        if (nextStep.contains("ending")) {
            return "/result.jsp";
        } else {
            return "/quest.jsp";
        }
    }
}
