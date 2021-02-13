package homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet31", value = "/servlet31")
public class Servlet31 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String[] desc = {"EUR -> USD ","USD -> EUR ","EUR -> PLN", "PLN -> EUR ", "USD -> PLN ", "PLN -> USD "  };
        double[] exchangeRate = {1.21, 0.82, 4.49, 0.22, 3.70, 0.27 };
        PrintWriter writer = response.getWriter();

        String vauleS =  request.getParameter("howmuch");
        String currS =  request.getParameter("currency");

        System.out.println(vauleS);
        System.out.println(currS);
        if (vauleS.isEmpty() || currS.isEmpty()){
            writer.append("Niepoprawne dane, spróbuj jeszcze raz");
        }
        else {
            try {
                double vaule = Double.parseDouble(vauleS);
                int curr = Integer.parseInt(currS);

                double result = vaule*exchangeRate[curr];

                writer.append(vauleS + " " + desc[curr] + " " + result);
            } catch (NumberFormatException e) {
                writer.append("Niepoprawne dane");
            }
        }
    }
}
