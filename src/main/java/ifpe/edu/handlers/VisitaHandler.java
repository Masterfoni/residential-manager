/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.edu.handlers;

import ifpe.edu.entities.Visita;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DaniloP
 */
@Stateless
public class VisitaHandler extends HttpServlet {
    
    @PersistenceContext(name ="resmanager")
    private EntityManager entityManager;
    
    
    public boolean insertVisita(Visita visita)
    {
        boolean sucess;
        
        try{
            entityManager.persist(visita);
            entityManager.flush();
            
            sucess = true;
        }catch (Exception e){
            e.printStackTrace();
            
            sucess = false;
        }
     
        return sucess;
    }

    public Object getVisitas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
