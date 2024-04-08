package servlets;

import quests.Quest;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static quests.QuestLoader.loadQuestFromYaml;

@WebServlet(name = "RedirectServlet", value = "/redirect")
public class CatalogServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> buttons = Arrays.asList("Quest 1", "Quest 2", "Quest 3");

        HttpSession session = req.getSession();
        session.setAttribute("quests", buttons);
        //session.setAttribute();

        getServletContext().getRequestDispatcher("/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Quest quest = loadQuestFromYaml("Quest1.yml");

        List<Quest.Option> options = new ArrayList<>();
        options.add(quest.decisions().get("step1").options().get(0));
        options.add(quest.decisions().get("step1").options().get(1));

        List<String> listOfAnswers = new ArrayList<>();
        listOfAnswers.add(options.get(0).title());
        listOfAnswers.add(options.get(1).title());

        session.setAttribute("title", quest.title());
        session.setAttribute("story", quest.story());
        session.setAttribute("quest", req.getParameter("questValue"));
        session.setAttribute("prompt", quest.decisions().get("step1").prompt());
        session.setAttribute("listOfAnswers", listOfAnswers);

        getServletContext().getRequestDispatcher("/quest.jsp").forward(req, resp);

    }
}
