package ifpe.edu.servlets;

import ifpe.edu.utils.LongRequestResult;
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
        
        LongRequestResult resultadoCadastro;
        
        resultadoCadastro = cadastrarUsuario(request, response);
        
        if(!resultadoCadastro.hasErrors)
        {
            reqDisp = request.getRequestDispatcher("/index/index.jsp");
            request.setAttribute("successMessage", "Cadastro realizado com sucesso!");
            request.setAttribute("apartamentos", apHandler.getApartamentosDesocupados());
            reqDisp.forward(request, response);
        }
        else
        {
            reqDisp = request.getRequestDispatcher("/index/index.jsp");
            request.setAttribute("errorMessage", resultadoCadastro.message);
            request.setAttribute("apartamentos", apHandler.getApartamentosDesocupados());
            reqDisp.forward(request, response);
        }
    }
    
    private LongRequestResult cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
    {
        LongRequestResult resultado = new LongRequestResult();
        
        Usuario novoUsuario = new Usuario();
        
        novoUsuario.setCpf(request.getParameter("valCpf"));
        novoUsuario.setLogin(request.getParameter("valLogin"));
        novoUsuario.setSenha(request.getParameter("valSenha"));
        novoUsuario.setNome(request.getParameter("valNome"));
        novoUsuario.setSexo(request.getParameter("valSexo"));
        novoUsuario.setEmail(request.getParameter("valEmail"));
        
        if(!novoUsuario.getSenha().equals(request.getParameter("valConfSenha")))
        {
            resultado.hasErrors = true;
            resultado.message = "Valor das senhas não confere!";
        }
        else
        {
            resultado = usHandler.insertUsuario(novoUsuario);
            
            if(!resultado.hasErrors)
            {
                long apId = Long.decode(request.getParameter("valApartamento"));
                apHandler.setDonoApartamento(apId, novoUsuario);
            }
        }
        
        return resultado;
    }
}
