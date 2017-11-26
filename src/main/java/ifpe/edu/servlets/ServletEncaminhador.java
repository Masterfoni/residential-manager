package ifpe.edu.servlets;

import ifpe.edu.handlers.InformativoHandler;
import ifpe.edu.handlers.VisitaHandler;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletEncaminhador", urlPatterns = {"/ServletEncaminhador"})
public class ServletEncaminhador extends HttpServlet {

    @EJB
    InformativoHandler inforHandler;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher reqDisp;
        HttpSession session = request.getSession();
        
        request.setAttribute("errorMessage", "");
        request.setAttribute("successMessage", "");
        
        session.setAttribute("inforList", inforHandler.getInformativos());
        
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
            request.setAttribute("errorMessage", "");
            request.setAttribute("successMessage", "");
            
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
        if(action.equals("RVISITA"))
        {
            reqDisp = request.getRequestDispatcher("/visitas/cadastro-visitas.jsp");
            reqDisp.forward(request, response);
        }
        else if(action.equals("GVISITA"))
        {
            request.setAttribute("errorMessage", "");
            request.setAttribute("sucessMessage", "");
            
            reqDisp = request.getRequestDispatcher("/visitas/gerenciar-visitas.jsp");
            reqDisp.forward(request, response);
        }
    }
}
