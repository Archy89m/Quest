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

        List<String> buttons = Utils.getQuestNames(getServletContext(), Quest.QUESTS_PATH_WEB_SERVER);

        HttpSession session = req.getSession();
        session.setAttribute("quests", buttons);

        getServletContext().getRequestDispatcher("/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String selectedQuest = req.getParameter("questValue");
        Quest quest = Quest.loadQuestFromYaml(Quest.QUESTS_PATH_SOURCE + selectedQuest + Quest.QUEST_FILE_FORMAT);

        HttpSession session = req.getSession();
        session.setAttribute("quest", quest);
        session.setAttribute("title", quest.title());
        session.setAttribute("story", quest.story());
        session.setAttribute("step", quest.getFirstStep());
        session.setAttribute("prompt", quest.decisions().get(quest.getFirstStep()).prompt());
        session.setAttribute("listOfAnswers", quest.getTitleOptions(quest.getFirstStep()));
        session.setAttribute("optionTitle", Quest.OPTION_SELECT);

        getServletContext().getRequestDispatcher(quest.getNextStepJSP(quest.getFirstStep())).forward(req, resp);
    }
}