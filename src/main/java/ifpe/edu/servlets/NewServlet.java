package ifpe.edu.servlets;

import ifpe.edu.entities.Usuario;
import java.io.IOException;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    
    @PersistenceContext(name = "resmanager", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("CRIANDO USUARIO");
        
        Usuario usr = new Usuario();
        
        usr.setCpf("jaja");
        usr.setNome("jaja2");
        
        em.persist(usr);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
