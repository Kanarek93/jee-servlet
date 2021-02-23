package homework.day3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "Sess38", value = "/sess38")
public class Sess38 extends HttpServlet {
    private String[][] countriesCapitals = {{"Niemcy", "Czechy", "Słowacja", "Ukraina", "Białoruś", "Litwa", "Rosja"},{"berlin", "praga", "bratysława", "kijów", "mińsk", "wilno", "moskwa"}};
    private static int INDEX_OF_COUNTRY = 0;
    private static int INDEX_OF_CAPITAL = 1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();

        int resultCounter = 0;
        if (session.getAttribute("answers") != null){
            List<String> answers = (List<String>) session.getAttribute("answers");

            for (int i = 0; i< answers.size(); i++){
                if (countriesCapitals[INDEX_OF_CAPITAL][i].equals(answers.get(i).toLowerCase())){
                    resultCounter++;
                }
            }
            writer.append("</pre>");
            writer.append("Poprawnych odpowiedzi: "+resultCounter);
        } else {
            writer.append("Nieznany błąd - brak odpowiedzi");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();

        //Tworze zmiennę do których przypiszę zmienne sesyjne
        int counter = 0;
        List<String> answers = new ArrayList<>();
        if (session.getAttribute("counter") != null){
            counter = (int) session.getAttribute("counter");
        }
        if (session.getAttribute("answers") != null){
            answers = (List<String>) session.getAttribute("answers");

        }
        //Sprawdzam czy został przysłany parametr capital
        String answer = request.getParameter("capital");
        if (answer != null) {
            if (answer.isEmpty()) {
                answer = "Brak odpowiedzi";
            }
            answers.add(answer);
            session.setAttribute("answers", answers);
        }
        
        if (counter >= countriesCapitals[INDEX_OF_COUNTRY].length){
            writer.append("<form action = '' method = 'post'>");
            writer.append("<input type = 'submit' value = 'Sprawdź wynik'>");
            writer.append("</form>");
        }
        else {
            session.setAttribute("counter", counter+1);
            writer.append("<form action = '' method = 'get'>");
            writer.append("Podaj stolicę kraju: " + countriesCapitals[INDEX_OF_COUNTRY][counter] + "<br>");
            writer.append("<input type = 'text' name = 'capital'>");
            writer.append("<input type = 'submit' value = 'Dalej'>");
            writer.append("</form>");
        }

    }
}
