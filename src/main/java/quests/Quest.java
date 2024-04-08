package quests;

import java.util.List;
import java.util.Map;

public record Quest(String title,
                    String story,
                    Map<String, Decision> decisions,
                    Map<String, Ending> endings) {

    public record Decision(String prompt, List<Option> options) {}

    public record Option(String title, String story, String next) {}

    public record Ending(String story) {}

}
