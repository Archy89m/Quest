package servlets;

import quests.Quest;
import quests.QuestLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "StepServlet", value = "/start")
public class StepServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            session.removeAttribute(attributeName);
        }

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        Quest quest = (Quest) session.getAttribute("quest");
        String nextStep = (String) session.getAttribute("step");

        session.setAttribute("answerStory", "");
        session.setAttribute("prompt", quest.decisions().get(nextStep).prompt());
        session.setAttribute("listOfAnswers", QuestLogic.getTitleOptions(quest.decisions().get(nextStep).options()));
        session.setAttribute("optionTitle", "Select option:");

        getServletContext().getRequestDispatcher(QuestLogic.getNextStepJSP(nextStep)).forward(req, resp);
    }
}
