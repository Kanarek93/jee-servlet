package homework.day4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Servlet311", value = "/servlet311")
public class Servlet311 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> lang = new HashMap<>();
        lang.put("en", "Hello");
        lang.put("pl", "Cześć");
        lang.put("de", "Hallo");
        lang.put("es", "Hola");
        lang.put("fr", "Salut");
        String message = "";

        //licznik obecności zapisywany do sesji
        //(jeżeli ktoś był, a ciasteczka nie będzie to i tak wyświetli się Cześć!)
        HttpSession session = request.getSession();
        int counter = 0;
        if (session.getAttribute("counter") != null){
            counter = (int) session.getAttribute("counter");
        }
        request.setAttribute("counter", counter);

        Cookie[] cookies = request.getCookies();
        //ustawiam od razu polskie powitanie, bo jeżeli ktoś był zawsze ma się wyświetlić Cześć
        message = lang.get("pl") + "!";
        for (Cookie c : cookies){
            if ("lang".equals(c.getName())) {
                if (lang.get(c.getValue()) != null) {
                    message = lang.get(c.getValue()) + "!";
                }
            }
        }
        //ustawiam atrybut powiadomienia
        request.setAttribute("message", message);
        //dodaje licznik do sesji
        session.setAttribute("counter", counter+1);
        getServletContext().getRequestDispatcher("/WEB-INF/servlet311Form.jsp").forward(request,response);

    }
}
