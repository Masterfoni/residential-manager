package ifpe.edu.handlers;

import ifpe.edu.entities.Informativo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InformativoHandler {

    @PersistenceContext(name = "resmanager") 
    private EntityManager entityManager;
    
    public InformativoHandler() {
        
    }
    
    public boolean insertInformativo(Informativo informativo)
    {
        boolean sucesso;
        
        try {
            entityManager.persist(informativo);
            entityManager.flush();
            
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
            
            sucesso = false;
        }
        
        return sucesso;
    }
        
    public Informativo findInformativo(Long id) 
    {
        Informativo informativoAchado = null;
        
        try {
            informativoAchado = entityManager.createNamedQuery("Informativo.findById", Informativo.class)
                            .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }        
        
        return informativoAchado;
    }
}
