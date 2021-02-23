package homework.day4.newsletter;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet(name = "Newsletter", value = "/newsletter")
public class Newsletter extends HttpServlet {
    private static final String NOT_UNIQUE_EMAIL_MESSAGE = "Mamy w bazie ten email";
    private static final String TOO_LONG_PARAMETERS_MESSAGE = "Nazwa i email mogą mieć maksymalnie 255 znaków";
    private static final String ERROR_MESSAGE = "Nie można stworzyć użytkownika";

    private static final String SUCCESS_MESSAGE = "Dodaliśmy Twój adres email do bazy!";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        //tworzymy użytkownika i obsługujemy ewentualne błędy
        try {
            FormUsers u = new FormUsers(name,email);
            setCookieForm(request,response, SUCCESS_MESSAGE);
            System.out.println(name + " " + email);
        } catch (SQLIntegrityConstraintViolationException ex) {
            deleteCookieFormByError(request, response, NOT_UNIQUE_EMAIL_MESSAGE );
            ex.printStackTrace();
            System.out.println(name + " " + email);
            System.out.println("Pierwszy błąc");
        } catch (MysqlDataTruncation ex) {
            deleteCookieFormByError(request, response,TOO_LONG_PARAMETERS_MESSAGE );
            ex.printStackTrace();
            System.out.println(name + " " + email);
            System.out.println("Drugi błąd");
        } catch (SQLException ex) {
            deleteCookieFormByError(request,response, ERROR_MESSAGE);
            ex.printStackTrace();
            System.out.println(name + " " + email);
            System.out.println("Trzeci błąd");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/newsletter.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        setCookieForm(request, response, "");
        getServletContext().getRequestDispatcher("/WEB-INF/newsletter.jsp").forward(request,response);
    }

    private void deleteCookieFormByError (HttpServletRequest request, HttpServletResponse response, String message){
        request.setAttribute("message", message);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("form".equals(c.getName())) {
                    request.setAttribute("form", true);
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
    }

    private void setCookieForm (HttpServletRequest request, HttpServletResponse response, String message){
        boolean isCookie = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("form".equals(c.getName())) {
                    request.setAttribute("form", false); //muszę czytać to atrybutem, bo za pierwszym razem nie chce mi czytać ciasteczka nawet jak wysyłam atrybutem
                    isCookie = true;
                }
            }
        }
        if (isCookie == false){
            Cookie c = new Cookie("form", "no");
            c.setMaxAge(60*60*24);
            response.addCookie(c);
            request.setAttribute("form", true);
        }
        request.setAttribute("message", "<br>" + message);
    }
}
