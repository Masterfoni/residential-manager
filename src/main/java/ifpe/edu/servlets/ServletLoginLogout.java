package ifpe.edu.servlets;

import ifpe.edu.handlers.UsuarioHandler;
import ifpe.edu.entities.Usuario;
import ifpe.edu.handlers.ApartamentoHandler;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet
public class ServletLoginLogout extends HttpServlet {

    @EJB
    UsuarioHandler usHandler;
    
    @EJB
    ApartamentoHandler apHandler;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("apartamentos", apHandler.getApartamentosDesocupados());
        request.getRequestDispatcher("/index/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("ACTION");
        
        if(action.equals("LOGIN"))
        {
            realizaLogin(request, response);
        }
        else if(action.equals("LOGOUT"))
        {
            realizaLogout(request, response);
        }
        else
        {
            encaminhaSolicitacaoDefault(request, response);
        }
    }
    
    private void encaminhaSolicitacaoDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        RequestDispatcher reqDisp;
        
        if(session.getAttribute("userId") != null)
        {
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
        else
        {
            reqDisp = request.getRequestDispatcher("/index/index.jsp");
            request.setAttribute("errorMessage", "Por favor, realize o login no sistema.");
            reqDisp.forward(request, response);
        }
    }
    
    private void realizaLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Usuario usuario = usHandler.findUsuario(request.getParameter("valLogin"), request.getParameter("valSenha"));
        RequestDispatcher reqDisp;
        
        if(usuario != null) 
        {
            HttpSession session = request.getSession();
            session.setAttribute("userId", usuario.getId());
            
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
        else
        {
            String mensagemErro = "Usuário não encontrado! Tem certeza que digitou seus dados corretamente?";
            request.setAttribute("errorMessage", mensagemErro);
            
            reqDisp = request.getRequestDispatcher("/index/index.jsp");
            reqDisp.forward(request, response);
        }
    }
    
    private void realizaLogout(HttpServletRequest resRequest, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = resRequest.getSession();
        session.invalidate();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index/index.jsp");
        dispatcher.forward(resRequest, response);
    }
}
