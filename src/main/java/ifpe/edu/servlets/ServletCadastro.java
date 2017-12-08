package ifpe.edu.servlets;

import ifpe.edu.utils.LongRequestResult;
import ifpe.edu.entities.Usuario;
import ifpe.edu.handlers.ApartamentoHandler;
import ifpe.edu.handlers.ParametroSistemaHandler;
import ifpe.edu.handlers.UsuarioHandler;
import ifpe.edu.utils.Encoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.security.util.Password;

@WebServlet
public class ServletCadastro extends HttpServlet {

    @EJB
    UsuarioHandler usHandler;
    
    @EJB
    ApartamentoHandler apHandler;
    
    @EJB
    ParametroSistemaHandler psHandler;
    
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
        String shaUserToken = Encoder.generateHash(request.getParameter("valToken"));
        
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
        else if(!psHandler.findParametro("CONDTOKEN").data.getValor().equals(shaUserToken))
        {
            resultado.hasErrors = true;
            resultado.message = "Seu token está incorreto!";
        }
        else
        {
            novoUsuario.setSenha(Encoder.generateHash(novoUsuario.getSenha()));
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
