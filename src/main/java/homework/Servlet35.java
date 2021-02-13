package homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet35", value = "/servlet35")
public class Servlet35 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        String num1S = request.getParameter("num1");
        String num2S = request.getParameter("num2");
        String num3S = request.getParameter("num3");
        String num4S = request.getParameter("num4");

        try {
            int num1 = Integer.parseInt(num1S);
            int num2 = Integer.parseInt(num2S);
            int num3 = Integer.parseInt(num3S);
            int num4 = Integer.parseInt(num4S);

            double sum = num1 + num2 + num3 + num4;
            double avg = ((double) sum)/4;
            int product = num1 * num2 * num3 * num4;

            writer.append("<pre> Liczby: <br>");
            writer.append("-" + num1 + "<br>");
            writer.append("-" + num2 + "<br>");
            writer.append("-" + num3 + "<br>");
            writer.append("-" + num4 + "<br>");
            writer.append("Średnia: <br>");
            writer.append("-" + avg + "<br>");
            writer.append("Suma: <br>");
            writer.append("-" + sum + "<br>");
            writer.append("Iloczyn: <br>");
            writer.append("-" + product + "<br> </pre>");


        } catch (NumberFormatException e) {
            writer.append("Niepoprawne dane");
        }
        }
}
