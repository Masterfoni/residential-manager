package ifpe.edu.servlets;

import ifpe.edu.entities.Visita;
import ifpe.edu.handlers.UsuarioHandler;
import ifpe.edu.entities.Usuario;
import ifpe.edu.entities.Visita_;
import ifpe.edu.handlers.VisitaHandler;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DaniloP
 */
@WebServlet(name ="ServletVisita", urlPatterns = {"/ServletVisita"})
public class ServletVisita extends HttpServlet {

    @EJB
    UsuarioHandler userHandler;
    
    @EJB 
    VisitaHandler vsHandler;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        RequestDispatcher reqDisp;
        
        Long userId = (Long)session.getAttribute("userId");
        
        if(userId != null)
        {
            Usuario atualUsuario = userHandler.findUsuario(userId);
            Visita novaVisita = new Visita();
            Date dataCriacao = new Date();
            
            novaVisita.setCpf(request.getParameter("valCpf"));
            novaVisita.setNome(request.getParameter("valNome"));
            novaVisita.setUsuario(atualUsuario);
            novaVisita.setReserva(0);
            novaVisita.setDataCriacao(dataCriacao);
        
            if(vsHandler.insertVisita(novaVisita))
            {
                request.setAttribute("sucessMessage", "Visita Cadastrada com Sucesso!");
            }
            else
            {
                request.setAttribute("errrorMessage", "Ocorreu um Erro ao cadastrar a Visita");
            }
            
            session.setAttribute("visitaList", vsHandler.getVisita());
            
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
        
    }
}
