package ifpe.edu.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletEncaminhador", urlPatterns = {"/ServletEncaminhador"})
public class ServletEncaminhador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher reqDisp;
        reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
        reqDisp.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("ACTION");
        
        RequestDispatcher reqDisp;
        
        if(action.equals("CINFORMATIVO"))
        {
            reqDisp = request.getRequestDispatcher("/informativos/cadastro-informativo.jsp");
            reqDisp.forward(request, response);
        }
        else if(action.equals("VINFORMATIVO"))
        {
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
    }
}
