package servlets;

import quests.Quest;
import services.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> buttons = Utils.getQuestNames(getServletContext());

        HttpSession session = req.getSession();
        session.setAttribute("quests", buttons);

        getServletContext().getRequestDispatcher("/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String selectedQuest = req.getParameter("questValue");
        Quest quest = Quest.loadQuestFromYaml("questStories/" + selectedQuest + ".yml");

        HttpSession session = req.getSession();
        session.setAttribute("quest", quest);
        session.setAttribute("title", quest.title());
        session.setAttribute("story", quest.story());
        session.setAttribute("step", "step1");
        session.setAttribute("prompt", quest.decisions().get("step1").prompt());
        session.setAttribute("listOfAnswers", quest.getTitleOptions("step1"));
        session.setAttribute("optionTitle", "Select option:");

        getServletContext().getRequestDispatcher(Utils.getNextStepJSP("step1")).forward(req, resp);
    }
}