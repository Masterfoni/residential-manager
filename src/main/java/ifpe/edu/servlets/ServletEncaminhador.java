package ifpe.edu.servlets;

import ifpe.edu.handlers.InformativoHandler;
import ifpe.edu.handlers.TransparenciaHandler;
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
    
    @EJB
    VisitaHandler visitaHandler;
    
    @EJB
    TransparenciaHandler transpHandler;
    
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
        
        HttpSession session = request.getSession();
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
            
            session.setAttribute("inforList", inforHandler.getInformativos());
            
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
        if(action.equals("RVISITA"))
        {
            reqDisp = request.getRequestDispatcher("/cadastro-visitas/cadastro-visitas.jsp");
            reqDisp.forward(request, response);
        }
        else if(action.equals("GVISITA"))
        {
            request.setAttribute("errorMessage", "");
            request.setAttribute("sucessMessage", "");
            
            session.setAttribute("visitaList", visitaHandler.getVisitasNaoComparecidas());
            
            reqDisp = request.getRequestDispatcher("/gerenciar-visitas/gerenciar-visitas.jsp");
            reqDisp.forward(request, response);
        }
        if(action.equals("CTRANSPARENCIA"))
        {
            reqDisp = request.getRequestDispatcher("/cadastro-transparencia/cadastro-transparencia.jsp");
            reqDisp.forward(request, response);
        }
        else if(action.equals("VTRANSPARENCIA"))
        {
            request.setAttribute("errorMessage", "");
            request.setAttribute("successMessage", "");
            
            session.setAttribute("janTranspList", transpHandler.getTransparenciasByMesAno(1, 2017));
            session.setAttribute("fevTranspList", transpHandler.getTransparenciasByMesAno(2, 2017));
            session.setAttribute("marTranspList", transpHandler.getTransparenciasByMesAno(3, 2017));
            session.setAttribute("abrTranspList", transpHandler.getTransparenciasByMesAno(4, 2017));
            session.setAttribute("maiTranspList", transpHandler.getTransparenciasByMesAno(5, 2017));
            session.setAttribute("junTranspList", transpHandler.getTransparenciasByMesAno(6, 2017));
            session.setAttribute("julTranspList", transpHandler.getTransparenciasByMesAno(7, 2017));
            session.setAttribute("agoTranspList", transpHandler.getTransparenciasByMesAno(8, 2017));
            session.setAttribute("setTranspList", transpHandler.getTransparenciasByMesAno(9, 2017));
            session.setAttribute("outTranspList", transpHandler.getTransparenciasByMesAno(10, 2017));
            session.setAttribute("novTranspList", transpHandler.getTransparenciasByMesAno(11, 2017));
            session.setAttribute("dezTranspList", transpHandler.getTransparenciasByMesAno(12, 2017));
            
            session.setAttribute("transpList", transpHandler.getTransparenciasAdicionadasByAno(2017));
            
            reqDisp = request.getRequestDispatcher("/visualizar-transparencia/visualizar-transparencia.jsp");
            reqDisp.forward(request, response);
        }
    }
}
