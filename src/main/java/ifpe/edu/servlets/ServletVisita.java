package ifpe.edu.servlets;

import com.google.gson.Gson;
import ifpe.edu.entities.Visita;
import ifpe.edu.handlers.UsuarioHandler;
import ifpe.edu.handlers.InformativoHandler;
import ifpe.edu.handlers.VisitaHandler;
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

@WebServlet(name ="ServletVisita", urlPatterns = {"/ServletVisita"})
public class ServletVisita extends HttpServlet {

    @EJB
    UsuarioHandler userHandler;
    
    @EJB 
    VisitaHandler vsHandler;
    
    @EJB
    InformativoHandler inforHandler;
    
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
        String id = request.getParameter("visitaId");
        String action = request.getParameter("ACTION");
        
        if(action.equals("CADASTRAR"))
        {
            cadastrarVisita(request, response);
        }
        else if(action.equals("DELETAR"))
        {
            deletarVisita(request, response, id);
        }
        else if(action.equals("ATUALIZAR"))
        {
            atualizarVisita(request, response, id);
        }
    }
    
    public void atualizarVisita(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException 
    {
        Long visitaId = Long.parseLong(id);
        
        vsHandler.marcarCompareceu(vsHandler.findVisita(visitaId).data);
        
        Map<String, String> options = new LinkedHashMap<>();
        options.put("Success", "TRUE");
        
        String json = new Gson().toJson(options);

        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(json);
    }
    
    public void deletarVisita(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException 
    {
        Long visitaId = Long.parseLong(id);
        
        vsHandler.deleteVisita(visitaId);
        
        Map<String, String> options = new LinkedHashMap<>();
        options.put("Success", "TRUE");
        
        String json = new Gson().toJson(options);

        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(json);
    }
    
    public void cadastrarVisita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        RequestDispatcher reqDisp;
        
        Long userId = (Long)session.getAttribute("userId");
        
        if(userId != null)
        {
            Visita novaVisita = new Visita();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            String dataString = request.getParameter("valData");
            
            novaVisita.setCpf(request.getParameter("valCpf"));
            novaVisita.setNome(request.getParameter("valNome"));
            novaVisita.setUsuario(userHandler.findUsuario(userId));
            novaVisita.setReserva(0);
            novaVisita.setDataCriacao(new Date());
            
            try {
                Date dataVisita = sdf.parse(dataString);
                novaVisita.setDataVisita(dataVisita);
            } catch (Exception e) {
                e.printStackTrace();
                novaVisita.setDataVisita(new Date());
            }
        
            RequestResult resultado = vsHandler.insertVisita(novaVisita);
            
            if(!resultado.hasErrors)
            {
                request.setAttribute("successMessage", "Visita Cadastrada com Sucesso!");
            }
            else
            {
                request.setAttribute("errorMessage", resultado.message);
            }
            
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
    }
}
