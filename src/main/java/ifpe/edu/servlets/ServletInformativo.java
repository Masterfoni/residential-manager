package ifpe.edu.servlets;

import com.google.gson.Gson;
import ifpe.edu.entities.Informativo;
import ifpe.edu.entities.Usuario;
import ifpe.edu.handlers.InformativoHandler;
import ifpe.edu.handlers.ParametroSistemaHandler;
import ifpe.edu.handlers.UsuarioHandler;
import ifpe.edu.utils.BoolRequestResult;
import ifpe.edu.utils.EmailSender;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
    
    @EJB
    ParametroSistemaHandler psHandler;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long idToDelete = Long.parseLong(request.getParameter("deleteId"));
        
        BoolRequestResult resultado = infoHandler.deleteInformativo(idToDelete);
        
        Map<String, String> options = new LinkedHashMap<>();
        options.put("Success", ""+resultado.data);
        
        
        String json = new Gson().toJson(options);

        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(json);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher reqDisp;
    
        Long usId = (Long)session.getAttribute("userId");
        
        if(usId != null)
        {
            Date dataAtual = new Date();

            Usuario atualUsuario = usHandler.findUsuario(usId);

            Informativo novoInformativo = new Informativo();
            novoInformativo.setDescricao(request.getParameter("editor1"));
            novoInformativo.setUsuario(atualUsuario);
            novoInformativo.setDataCriacao(dataAtual);

            if(infoHandler.insertInformativo(novoInformativo))
            {
                request.setAttribute("successMessage", "Informativo cadastrado com sucesso!");
                
                EmailSender notificador = 
                new EmailSender("Um novo informativo foi postado pelo síndico do seu condomínio.",
                psHandler.findParametro("SMTPUSERNAME").data.getValor(),
                psHandler.findParametro("SMTPPASSWORD").data.getValor());
                
                for(Usuario usr : usHandler.getUsuarios())
                {
                    notificador.setDestinatario(usr.getEmail());
                    notificador.send();
                }
            }
            else 
            {
                request.setAttribute("errorMessage", "Ocorreu um problema ao cadastrar o informativo.");
            }

            session.setAttribute("inforList", infoHandler.getInformativos());

            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
    }
}
