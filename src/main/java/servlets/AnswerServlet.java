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
import java.util.HashMap;


@WebServlet("/getAnswer")
public class AnswerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String selectedAnswer = req.getParameter("selectedAnswer");

        HttpSession session = req.getSession();

        Quest quest = (Quest) session.getAttribute("quest");
        String currentStep = (String) session.getAttribute("step");

        HashMap<String, String> answerData = quest.getAnswerData(currentStep, selectedAnswer);

        session.setAttribute("answerStory", answerData.get("answerStory"));
        session.setAttribute("step", answerData.get("nextStep"));
        session.setAttribute("listOfAnswers", new ArrayList<>());
        session.setAttribute("optionTitle", Quest.OPTION_RESULT);

        getServletContext().getRequestDispatcher(quest.getNextStepJSP(answerData.get("nextStep"))).forward(req, resp);
    }
}