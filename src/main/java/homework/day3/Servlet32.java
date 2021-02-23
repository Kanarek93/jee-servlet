package homework.day3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "Servlet32", value = "/servlet32")
public class Servlet32 extends HttpServlet {

    private static final String ERROR_MESSAGE = "Wprowadzono niepoprawną daną";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        //patern na liczbę binarną
        Pattern compiledPattern = Pattern.compile("[01]+");

        String valS = request.getParameter("binary");
        int result = 0;
        if (valS != null) {
            //Nie sprawdzam czy wprowadzona dana nie jest pusta, bo regex to wyklucza
            Matcher matcher = compiledPattern.matcher(valS);
            if (matcher.matches()) {
                char[] numbers = valS.toCharArray();
                System.out.println("długość tablicy " + numbers.length);
                for (int j = 0, i = numbers.length - 1; i >= 0; i--, j++) {
                    result += Integer.parseInt((numbers[i] + "")) * Math.pow(2, j);
                }
                writer.append(valS + " = " + result);
            } else {
                writer.append(ERROR_MESSAGE);
            }
        } else {
            writer.append(ERROR_MESSAGE);
        }

    }
}
