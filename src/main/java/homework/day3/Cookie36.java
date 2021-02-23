package homework.day3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Cookie36", value = "/rememberMe")
public class Cookie36 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        String name = request.getParameter("name");
        String checked = request.getParameter("remember");

        if (name.isEmpty()){
            writer.append("Nic nie wpisano!");
        }
        else {
            writer.append("Cześć "+name+"!");
            if ("on".equals(checked)) {
                Cookie c = new Cookie("name", name);
                response.addCookie(c);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        boolean isCookie = false;

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies){
            if ("name".equals(c.getName())){
                writer.append("Cześć "+c.getValue()+". Twoje dane zostały wczytane z ciasteczka");
                isCookie = true;
            }
        }
        if (!isCookie) {
            writer.append("<form action = '' method = 'post'>");
            writer.append("<label> Imię: <input type = 'text' name = 'name'></label>");
            writer.append("<input type = 'checkbox' name = 'remember'> Zapamiętaj mnie");
            writer.append("<input type = 'submit' value = 'Wyślij'>");
            writer.append("</form>");
        }
    }
}
