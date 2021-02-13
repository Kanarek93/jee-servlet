package homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(name = "Sess37", value = "/sess37")
public class Sess37 extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        HttpSession session = request.getSession();
        //dla bezpieczeństwa importuje, sprawdzę czy nie jest nullem i będzie jeden try do parsowania

        if (session.getAttribute("num1") != null && session.getAttribute("num2") != null) {

        String sumS = request.getParameter("sum");
        String diffS = request.getParameter("diff");
        String prodS = request.getParameter("prod");
            try {
                int num1 = (int) session.getAttribute("num1");
                int num2 = (int) session.getAttribute("num2");
                int sum = Integer.parseInt(sumS);
                int diff = Integer.parseInt(diffS);
                int prod = Integer.parseInt(prodS);

                writer.append("<pre>");
                writer.append(""+num1+"&#9;+&#9;"+num2+"&#9;=&#9;" + sum );
                if (sum == num1+num2){
                    writer.append("<span style = 'color: green'>&#9;Correct</span>");
                } else  {
                    writer.append("<span style = 'color : red'>&#9;Wrong</span>");
                }
                writer.append("<br>");
                writer.append(""+num1+"&#9;-&#9;"+num2+"&#9;=&#9;" + diff );
                if (diff == num1-num2){
                    writer.append("<span style = 'color: green'>&#9;Correct</span>");
                } else  {
                    writer.append("<span style = 'color : red'>&#9;Wrong</span>");
                }
                writer.append("<br>");
                writer.append(""+num1+"&#9;*&#9;"+num2+"&#9;=&#9;" + prod );
                if (prod == num1*num2){
                    writer.append("<span style = 'color: green'>&#9;Correct</span>");
                } else  {
                    writer.append("<span style = 'color : red'>&#9;Wrong</span>");
                }
                writer.append("<br>");
            } catch (NumberFormatException e) {
                writer.append("Dane muszą być liczbami");
            }
        } else {
            writer.append("Niepoprawne dane");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        Random random = new Random();

        int num1 = random.nextInt(981) +20;
        int num2 = random.nextInt(981) +20;

        session.setAttribute("num1", num1);
        session.setAttribute("num2", num2);

        writer.append("Wylosowano liczby: "+num1+" i "+num2+"<br>");
        writer.append("<form action = '' method = 'post'>");
        writer.append("<label>Suma:<input type = 'number' name = 'sum'></label><br>");
        writer.append("<label>Różnica:<input type = 'number' name ='diff' ></label><br>");
        writer.append("<label>Iloczyn:<input type = 'number' name ='prod' ></label><br>");
        writer.append("<input type = 'submit' value = 'Sprawdź'>");
        writer.append("</form>");

    }
}
