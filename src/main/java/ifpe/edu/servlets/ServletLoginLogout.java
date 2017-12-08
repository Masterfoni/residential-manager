package ifpe.edu.servlets;

import ifpe.edu.handlers.UsuarioHandler;
import ifpe.edu.entities.Usuario;
import ifpe.edu.handlers.ApartamentoHandler;
import ifpe.edu.handlers.InformativoHandler;
import ifpe.edu.handlers.ParametroSistemaHandler;
import ifpe.edu.utils.Encoder;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet( urlPatterns = {"/ServletLoginLogout", "/informativos/ServletLoginLogout"})
public class ServletLoginLogout extends HttpServlet {

    @EJB
    UsuarioHandler usHandler;
    
    @EJB
    ApartamentoHandler apHandler;
    
    @EJB
    InformativoHandler inforHandler;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        encaminhaSolicitacaoDefault(request, response);
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
            session.setAttribute("inforList", inforHandler.getInformativos());
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
        else
        {   
            reqDisp = request.getRequestDispatcher("/index/index.jsp");
            request.setAttribute("apartamentos", apHandler.getApartamentosDesocupados());
            reqDisp.forward(request, response);
        }
    }
    
    private void realizaLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Usuario usuario = usHandler.findUsuario(request.getParameter("valLogin"), Encoder.generateHash(request.getParameter("valSenha")));
        RequestDispatcher reqDisp;
        
        if(usuario != null) 
        {
            HttpSession session = request.getSession();
            
            session.setAttribute("userId", usuario.getId());
            session.setAttribute("userType", usuario.getTipoUsuario().getId());
            session.setAttribute("inforList", inforHandler.getInformativos());
            
            System.out.println("ID DO USUARIO: " + usuario.getId());
            System.out.println("ID DO TIPO DO USUARIO: " + usuario.getTipoUsuario().getId());
            
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
        else
        {
            String mensagemErro = "Usuário não encontrado! Tem certeza que digitou seus dados corretamente?";
            request.setAttribute("errorMessage", mensagemErro);
            
            
            reqDisp = request.getRequestDispatcher("/index/index.jsp");
            request.setAttribute("apartamentos", apHandler.getApartamentosDesocupados());
            reqDisp.forward(request, response);
        }
    }
    
    private void realizaLogout(HttpServletRequest resRequest, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = resRequest.getSession();
        session.invalidate();
        
        RequestDispatcher dispatcher = resRequest.getRequestDispatcher("/index/index.jsp");
        resRequest.setAttribute("apartamentos", apHandler.getApartamentosDesocupados());
        dispatcher.forward(resRequest, response);
    }
}
