package ifpe.edu.handlers;

import ifpe.edu.entities.Visita;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServlet;

@Stateless
public class VisitaHandler extends HttpServlet {
    
    @PersistenceContext(name ="resmanager")
    private EntityManager entityManager;
    
    public VisitaHandler(){
    
    }
    
    public boolean insertVisita(Visita visita)
    {
        boolean sucesso;
        
        try {
            entityManager.persist(visita);
            entityManager.flush();
            
            sucesso= true;
        } catch (Exception e){
            e.printStackTrace();
            
            sucesso = false;
        }
        
        return sucesso;
    }

    public List<Visita> getVisitas() {
        List<Visita>  visitaCadastradas = new ArrayList<Visita>();
        
        try {
            visitaCadastradas = entityManager.createNamedQuery("Visita.getVisitas", Visita.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return visitaCadastradas;
    }
    
    public Visita findVisita(Long id)
    {
        Visita visita = null;
        
        try{
            visita = entityManager.createNamedQuery("Visita.findById", Visita.class).setParameter("id", id)
                        .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return visita;
    }
}
