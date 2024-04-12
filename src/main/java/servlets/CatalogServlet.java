package servlets;

import quests.QuestLogic;
import utils.FileService;
import quests.Quest;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import static quests.Quest.loadQuestFromYaml;

@WebServlet(name = "RedirectServlet", value = "/catalog")
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> buttons = FileService.getQuestNames(getServletContext());

        HttpSession session = req.getSession();
        session.setAttribute("quests", buttons);

        getServletContext().getRequestDispatcher("/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String selectedQuest = req.getParameter("questValue");
        Quest quest = loadQuestFromYaml(selectedQuest + ".yml");

        HttpSession session = req.getSession();
        session.setAttribute("quest", quest);
        session.setAttribute("title", quest.title());
        session.setAttribute("story", quest.story());
        session.setAttribute("step", "step1");
        session.setAttribute("prompt", quest.decisions().get("step1").prompt());
        session.setAttribute("listOfAnswers", QuestLogic.getTitleOptions(quest.decisions().get("step1").options()));
        session.setAttribute("optionTitle", "Select option:");

        getServletContext().getRequestDispatcher(QuestLogic.getNextStepJSP("step1")).forward(req, resp);
    }
}
