package ifpe.edu.servlets;

import com.google.gson.Gson;
import ifpe.edu.entities.Reserva;
import ifpe.edu.handlers.DependenciaHandler;
import ifpe.edu.handlers.InformativoHandler;
import ifpe.edu.handlers.ParametroSistemaHandler;
import ifpe.edu.handlers.ReservaHandler;
import ifpe.edu.handlers.UsuarioHandler;
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

@WebServlet(name = "ServletReserva", urlPatterns = {"/ServletReserva"})
public class ServletReserva extends HttpServlet 
{
    @EJB
    UsuarioHandler userHandler;
    
    @EJB
    InformativoHandler inforHandler;
    
    @EJB
    ParametroSistemaHandler psHandler;
    
    @EJB
    DependenciaHandler depHandler;
    
    @EJB
    ReservaHandler resHandler;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("visitaId");
        String action = request.getParameter("ACTION");
        
        if(action.equals("CADASTRAR"))
        {
            cadastrarReserva(request, response);
        }
        else if(action.equals("REJEITAR"))
        {
            rejeitarReserva(request, response, id);
        }
        else if(action.equals("APROVAR"))
        {
            aprovarReserva(request, response, id);
        }
    }
    
    public void aprovarReserva(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException 
    {
        Long reservaId = Long.parseLong(id);
        
        Reserva reserva = resHandler.findReserva(reservaId).data;
        
        RequestResult resultado = resHandler.aprovarReserva(reserva);
                    
        Map<String, String> options = new LinkedHashMap<>();
        
        if(!resultado.hasErrors)
        {
            EmailSender notificador = new EmailSender("Sua reserva para a seguinte dependência: " + reserva.getDependencia().getNome() + ", foi aprovada!",
            psHandler.findParametro("SMTPUSERNAME").data.getValor(),
            psHandler.findParametro("SMTPPASSWORD").data.getValor());

            notificador.setDestinatario(reserva.getUsuario().getEmail());
            notificador.start();
            
            options.put("Success", "TRUE");
        }
        else 
        {
            options.put("Success", "FALSE");
        }
        
        String json = new Gson().toJson(options);

        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(json);
    }
    
    public void rejeitarReserva(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException 
    {
        Long reservaId = Long.parseLong(id);
        
        Reserva reserva = resHandler.findReserva(reservaId).data;
        RequestResult resultado = resHandler.reprovarReserva(reserva);
        
        Map<String, String> options = new LinkedHashMap<>();
        
        if(!resultado.hasErrors) 
        {
            EmailSender notificador = new EmailSender("Sua reserva para a seguinte dependência: " + reserva.getDependencia().getNome() + " foi rejeitada!",
            psHandler.findParametro("SMTPUSERNAME").data.getValor(),
            psHandler.findParametro("SMTPPASSWORD").data.getValor());
            
            notificador.setDestinatario(reserva.getUsuario().getEmail());
            notificador.start();
            
            options.put("Success", "TRUE");
        }
        else
        {
            options.put("Success", "FALSE");
        }
        
        String json = new Gson().toJson(options);

        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8"); 
        response.getWriter().write(json);
    }
    
    public void cadastrarReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        RequestDispatcher reqDisp;
        
        Long userId = (Long)session.getAttribute("userId");
        
        if(userId != null)
        {
            Reserva novaReserva = new Reserva();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            String dataInicioString = request.getParameter("valDataInicio");
            String dataFimString = request.getParameter("valDataFim");
            
            long dependenciaId = Long.decode(request.getParameter("valDependencia"));
            
            novaReserva.setDependencia(depHandler.findDependencia(dependenciaId));
            novaReserva.setSituacao(0);
            novaReserva.setUsuario(userHandler.findUsuario(userId));
            
            try {
                Date dataReservaInicio = sdf.parse(dataInicioString);
                Date dataReservaFim = sdf.parse(dataFimString);
                
                novaReserva.setDataFim(dataReservaInicio);
                novaReserva.setDataInicio(dataReservaFim);
            } catch (Exception e) {
                e.printStackTrace();
                
                novaReserva.setDataFim(new Date());
                novaReserva.setDataInicio(new Date());
            }
            
            if(novaReserva.getDataInicio().before(new Date()))
            {
                request.setAttribute("errorMessage", "Data início não pode ser inferior à atual.");
            }
            else if(novaReserva.getDataInicio().before(novaReserva.getDataFim()))
            {
                request.setAttribute("errorMessage", "Data de início não pode ser inferior à final.");
            }
            else
            {
                RequestResult resultado = resHandler.insertReserva(novaReserva);

                if(!resultado.hasErrors)
                {
                    request.setAttribute("successMessage", "Reserva solicitada com sucesso!");
                }
                else
                {
                    request.setAttribute("errorMessage", resultado.message);
                }
            }
            
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
    }
}
