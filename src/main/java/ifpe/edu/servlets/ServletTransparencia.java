package ifpe.edu.servlets;

import ifpe.edu.entities.Informativo;
import ifpe.edu.entities.Transparencia;
import ifpe.edu.entities.Usuario;
import ifpe.edu.handlers.TransparenciaHandler;
import ifpe.edu.handlers.UsuarioHandler;
import ifpe.edu.utils.RequestResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

@WebServlet(name = "ServletTransparencia", urlPatterns = {"/ServletTransparencia"})
public class ServletTransparencia extends HttpServlet {
    
    @EJB
    UsuarioHandler userHandler;
    
    @EJB
    TransparenciaHandler transpHandler;
    
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        RequestDispatcher reqDisp;
        HttpSession session = request.getSession();
        
        request.setAttribute("errorMessage", "");
        request.setAttribute("sucessMessage", "");
    }
    
    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    
        //String id = request.getParameter("TransparenciaId");
        String action = request.getParameter("ACTION");
        
       if(action.equals("CADASTRAR"))
       {
           cadastrarTransparencia(request, response);
       }
    }
                      
    public void cadastrarTransparencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        RequestDispatcher reqDisp;
        
        Long userId = (Long)session.getAttribute("userId");
        
        if(userId != null)
        {
            Transparencia novaTransparencia = new Transparencia();
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            
            novaTransparencia.setUsuario(userHandler.findUsuario(userId));
            String dataString = request.getParameter("valData");
            Double valor = Double.parseDouble(request.getParameter("valValor"));
           
            novaTransparencia.setDescricao(request.getParameter("valDecricao"));
            novaTransparencia.setValor(valor);
            
            try{
                Date dataVigencia = date.parse(dataString);
                novaTransparencia.setDataVigencia(dataVigencia);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            
            RequestResult resultado = transpHandler.AdicionarTransparencia(novaTransparencia);
            
            if(!resultado.hasErrors)
            {
                request.setAttribute("sucessMessage", "Transparencia Postada Com Sucesso!");
            }else
            {
                request.setAttribute("errorMessage", resultado.message);
            }
            
            reqDisp = request.getRequestDispatcher("/homepage/homepage.jsp");
            reqDisp.forward(request, response);
        }
    
    }

}
