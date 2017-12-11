package ifpe.edu.servlets;

import com.google.gson.Gson;
import ifpe.edu.entities.Transparencia;
import ifpe.edu.entities.Usuario;
import ifpe.edu.handlers.ParametroSistemaHandler;
import ifpe.edu.handlers.TransparenciaHandler;
import ifpe.edu.handlers.UsuarioHandler;
import ifpe.edu.utils.BoolRequestResult;
import ifpe.edu.utils.EmailSender;
import ifpe.edu.utils.RequestResult;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

@WebServlet(name = "ServletTransparencia", urlPatterns = {"/ServletTransparencia"})
public class ServletTransparencia extends HttpServlet {
    
    @EJB
    UsuarioHandler userHandler;
    
    @EJB
    TransparenciaHandler transpHandler;
    
    @EJB
    ParametroSistemaHandler psHandler;
    
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idToDelete = Long.parseLong(request.getParameter("transparenciaId"));
        
        BoolRequestResult resultado = transpHandler.deleteTransparencia(idToDelete);
        
        Map<String, String> options = new LinkedHashMap<>();
        options.put("Success", ""+resultado.data);
        
        String json = new Gson().toJson(options);

        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(json);
    }
    
    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("ACTION");

        if(action.equals("CADASTRAR"))
        {
            cadastrarTransparencia(request, response);
        }
        if(action.equals("SELECTANO"))
        {
            refiltrarTransparencias(request, response);
        }
    }
    
    public void refiltrarTransparencias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        RequestDispatcher reqDisp;
        
        int valorAno = Integer.parseInt(request.getParameter("valAno"));
        
        session.setAttribute("janTranspList", transpHandler.getTransparenciasByMesAno(1, valorAno));
        session.setAttribute("fevTranspList", transpHandler.getTransparenciasByMesAno(2, valorAno));
        session.setAttribute("marTranspList", transpHandler.getTransparenciasByMesAno(3, valorAno));
        session.setAttribute("abrTranspList", transpHandler.getTransparenciasByMesAno(4, valorAno));
        session.setAttribute("maiTranspList", transpHandler.getTransparenciasByMesAno(5, valorAno));
        session.setAttribute("junTranspList", transpHandler.getTransparenciasByMesAno(6, valorAno));
        session.setAttribute("julTranspList", transpHandler.getTransparenciasByMesAno(7, valorAno));
        session.setAttribute("agoTranspList", transpHandler.getTransparenciasByMesAno(8, valorAno));
        session.setAttribute("setTranspList", transpHandler.getTransparenciasByMesAno(9, valorAno));
        session.setAttribute("outTranspList", transpHandler.getTransparenciasByMesAno(10, valorAno));
        session.setAttribute("novTranspList", transpHandler.getTransparenciasByMesAno(11, valorAno));
        session.setAttribute("dezTranspList", transpHandler.getTransparenciasByMesAno(12, valorAno));

        session.setAttribute("transpList", transpHandler.getTransparenciasAdicionadasByAno(valorAno));
        
        reqDisp = request.getRequestDispatcher("/visualizar-transparencia/visualizar-transparencia.jsp");
        reqDisp.forward(request, response);
    }
                      
    public void cadastrarTransparencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        RequestDispatcher reqDisp;
        
        Long userId = (Long)session.getAttribute("userId");
        
        if(userId != null)
        {
            Transparencia novaTransparencia = new Transparencia();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            
            novaTransparencia.setUsuario(userHandler.findUsuario(userId));
            novaTransparencia.setDescricao(request.getParameter("valDescricao"));
            
            String dataString = request.getParameter("valData");

            try {
                String numberString = request.getParameter("valValor").replace(".", "");
                numberString = numberString.replaceAll(",", ".");
                novaTransparencia.setValor(Double.parseDouble(numberString));
                
                Date dataVigencia = date.parse(dataString);
                novaTransparencia.setDataVigencia(dataVigencia);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            RequestResult resultado = transpHandler.AdicionarTransparencia(novaTransparencia);
            
            if(!resultado.hasErrors)
            {
                request.setAttribute("successMessage", "Transparência Postada Com Sucesso!");
                
                String smtpUsername = psHandler.findParametro("SMTPUSERNAME").data.getValor();
                String smtpPassword = psHandler.findParametro("SMTPPASSWORD").data.getValor();
                
                for(Usuario usr : userHandler.getUsuarios())
                {
                    EmailSender notificador = new EmailSender("Um novo gasto foi postado por parte do síndico!", smtpUsername, smtpPassword);
                    notificador.setDestinatario(usr.getEmail());
                    notificador.start();
                }
            }
            else
            {
                request.setAttribute("errorMessage", resultado.message);
            }
            
            session.setAttribute("transpList", transpHandler.getTransparenciaAdicionadas());
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
    }
}
