package servlets;

import quests.Quest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static quests.QuestLoader.loadQuestFromYaml;


@WebServlet(name = "step", value = "/nextStep")
public class QuestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Quest quest = loadQuestFromYaml("Quest1.yml");

        List<Quest.Option> options = new ArrayList<>();
        options.add(quest.decisions().get("step2").options().get(0));
        options.add(quest.decisions().get("step2").options().get(1));

        List<String> listOfAnswers = new ArrayList<>();
        listOfAnswers.add(options.get(0).title());
        listOfAnswers.add(options.get(1).title());

        session.setAttribute("prompt", quest.decisions().get("step2").prompt());
        session.setAttribute("listOfAnswers", listOfAnswers);

        //for the end
        session.setAttribute("ending", quest.endings().get("ending1"));

        getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
        //getServletContext().getRequestDispatcher("/quest.jsp").forward(req, resp);
    }
}
