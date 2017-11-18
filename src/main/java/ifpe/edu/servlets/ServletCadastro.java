package ifpe.edu.servlets;

import ifpe.edu.entities.Usuario;
import ifpe.edu.handlers.ApartamentoHandler;
import ifpe.edu.handlers.UsuarioHandler;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class ServletCadastro extends HttpServlet {

    @EJB
    UsuarioHandler usHandler;
    
    @EJB
    ApartamentoHandler apHandler;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher reqDisp;
        
        if(cadastrarUsuario(request, response))
        {
            reqDisp = request.getRequestDispatcher("/index/index.jsp");
            request.setAttribute("successMessage", "Cadastro realizado com sucesso!");
            reqDisp.forward(request, response);
        }
        else
        {
            reqDisp = request.getRequestDispatcher("/index/index.jsp");
            request.setAttribute("errorMessage", "Houveram problemas no cadastro, revise seus dados.");
            reqDisp.forward(request, response);
        }
    }
    
    private boolean cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
    {
        Boolean sucesso;
        
        Usuario novoUsuario = new Usuario();
        
        novoUsuario.setCpf(request.getParameter("valCpf"));
        novoUsuario.setLogin(request.getParameter("valLogin"));
        novoUsuario.setSenha(request.getParameter("valSenha"));
        novoUsuario.setNome(request.getParameter("valNome"));
        novoUsuario.setSexo(request.getParameter("valSexo"));
        
        if(!novoUsuario.getSenha().equals(request.getParameter("valConfSenha")))
        {
            sucesso = false;
        }
        else
        {
            if(usHandler.insertUsuario(novoUsuario) > 0)
            {
                long apId = Long.decode(request.getParameter("valApartamento"));
                
                apHandler.setDonoApartamento(apId, novoUsuario); 
            }
            
            sucesso = true;
        }
        
        return sucesso;
    }
}
