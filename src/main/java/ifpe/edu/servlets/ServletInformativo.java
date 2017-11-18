package ifpe.edu.servlets;

import ifpe.edu.entities.Informativo;
import ifpe.edu.entities.Usuario;
import ifpe.edu.handlers.InformativoHandler;
import ifpe.edu.handlers.UsuarioHandler;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletInformativo", urlPatterns = {"/ServletInformativo"})
public class ServletInformativo extends HttpServlet {
    
    @EJB
    InformativoHandler infoHandler;
    
    @EJB
    UsuarioHandler usHandler;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long usId = (Long)session.getAttribute("userId");
        Date dataAtual = new Date();
        
        Usuario atualUsuario = usHandler.findUsuario(usId);
        
        Informativo novoInformativo = new Informativo();
        novoInformativo.setDescricao(request.getParameter("editor1"));
        novoInformativo.setUsuarioId(atualUsuario);
        novoInformativo.setDataCriacao(dataAtual);
        
        infoHandler.insertInformativo(novoInformativo);
        
    }

}
