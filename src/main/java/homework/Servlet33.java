package homework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet33", value = "/servlet33")
public class Servlet33 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String species = request.getParameter("species");
        String breed = request.getParameter("breed");
        String weight = request.getParameter("weight");
        String toy = request.getParameter("toy");

        if (!name.isEmpty()){
            session.setAttribute("name", name);
        }
        if (!species.isEmpty()){
            session.setAttribute("species", species);
        }
        if (!breed.isEmpty()){
            session.setAttribute("breed", breed);
        }
        if (!weight.isEmpty()){
            session.setAttribute("weight", weight);
        }
        if (!toy.isEmpty()){
            session.setAttribute("toy", toy);
        }

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();

        String name = "";
        String species = "";
        String breed = "";
        String weight = "";
        String toy = "";

        if (session.getAttribute("name") != null){
            name = (String) session.getAttribute("name");
        }
        if (session.getAttribute("species") != null){
            species = (String) session.getAttribute("species");
        }
        if (session.getAttribute("breed") != null){
            breed = (String) session.getAttribute("breed");
        }
        if (session.getAttribute("weight") != null){
            weight = (String) session.getAttribute("weight");
        }
        if (session.getAttribute("toy") != null){
            toy = (String) session.getAttribute("toy");
        }

        writer.append("<form action='' method = 'post'>");
        writer.append("<label>");
        writer.append("Imię:");
        writer.append("<input type ='text' name = 'name' value = '"+name+"'> <br>");
        writer.append("</label>");
        writer.append("<label>");
        writer.append("Gatunek:");
        writer.append("<input type ='text' name = 'species' value = '" +species+"'> <br>");
        writer.append("</label>");
        writer.append("<label>");
        writer.append("Rasa:");
        writer.append("<input type ='text' name = 'breed' value = '"+breed+"'> <br>");
        writer.append("</label>");
        writer.append("<label>");
        writer.append("Waga:");
        writer.append("<input type ='text' name = 'weight' value = '"+weight+"'> <br>");
        writer.append("</label>");
        writer.append("<label>");
        writer.append("Ulubiona zabawka:");
        writer.append("<input type ='text' name = 'toy' value = '"+toy +"'> <br>");
        writer.append("</label>");
        writer.append("<input type = 'submit' value = 'Wpisz zwierzaka!'>");
        writer.append("</form>");

    }
}
