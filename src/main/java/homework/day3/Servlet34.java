package homework.day3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet34", value = "/servlet34")
public class Servlet34 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        int visit = 0;
        boolean isCookie = false;
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies){
            if ("visit".equals(c.getName())){
                String visitS = c.getValue();
                try {
                    visit = Integer.parseInt(visitS);
                    isCookie = true;
                    c.setValue("" + (visit+1));
                    response.addCookie(c);
                } catch (NumberFormatException e){
                    writer.append("Wystąpił niespodziewany błąd");
                }
            }
        }
        if (isCookie) {
            writer.append("Witaj, odwiedziłeś nas już " + visit + " razy!");
        }
        else {
            writer.append("Witaj pierwszy raz na naszej stronie");
            Cookie c = new Cookie("visit", "1");
            c.setMaxAge(60*60*24*365);
            response.addCookie(c);
        }
    }
}
