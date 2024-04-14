import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import quests.Quest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.*;


class QuestTest {

    public Quest quest;

    @BeforeEach
    public void loadTestQuest() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        InputStream inputStream = QuestTest.class.getClassLoader().getResourceAsStream("questStories/TestQuestStory.yml");
        if (inputStream == null)
            throw new IllegalArgumentException("File questStories/TestQuestStory.yml not found in test resources");

        quest = mapper.readValue(inputStream, Quest.class);
    }

    @Test
    public void CheckTheQuestFileStructure() {

        Set<Map.Entry<String, Quest.Decision>> entryDecision = quest.decisions().entrySet();
        String key = entryDecision.iterator().next().getKey();
        Quest.Decision decision = entryDecision.iterator().next().getValue();
        List<Quest.Option> options = decision.options();

        Set<Map.Entry<String, Quest.Ending>> entryEnding = quest.endings().entrySet();
        String keyEnding = entryEnding.iterator().next().getKey();
        String valueEnding = entryEnding.iterator().next().getValue().story();

        assertEquals(quest.title(), "Title");
        assertEquals(quest.story(), "Story");

        assertTrue(entryDecision.size() > 1);
        assertEquals(key, "step1");
        assertEquals(decision.prompt(), "Step1_Prompt");
        assertTrue(options.size() > 1);
        assertEquals(options.get(0).title(), "Step1_Option1_Title");
        assertEquals(options.get(0).story(), "Step1_Option1_Story");
        assertEquals(options.get(0).next(), "step2");

        assertTrue(entryEnding.size() > 1);
        assertEquals(keyEnding, "ending1");
        assertEquals(valueEnding, "ending1");
    }

    @ParameterizedTest
    @ValueSource(strings = {"step2"})
    public void getTitleOptions(String nextStep) {

        List<String> titleOptions = quest.getTitleOptions(nextStep);

        assertEquals(titleOptions.get(0), "Step2_Option1_Title");
        assertEquals(titleOptions.get(1), "Step2_Option2_Title");

    }

    @ParameterizedTest
    @CsvSource({"step2, Step2_Option1_Title"})
    public void getAnswerData(String currentStep, String answer) {

        HashMap<String, String> answerData = quest.getAnswerData(currentStep, answer);

        assertEquals(answerData.get("answerStory"), "Step2_Option1_Story");
        assertEquals(answerData.get("nextStep"), "step4");
    }

    @Test
    public void getFirstStep() {
        assertEquals(quest.decisions().entrySet().iterator().next().getKey(), "step1");
    }

    @ParameterizedTest
    @ValueSource(strings = {"step5", "ending2"})
    public void getNextStepJSP(String nextStep) {

        String nextJSP = quest.getNextStepJSP(nextStep);

        if (nextStep.equals("step5")) {
            assertEquals(nextJSP, "/quest.jsp");
        } else {
            assertEquals(nextJSP, "/result.jsp");
        }
    }
}
