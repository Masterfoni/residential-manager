package ifpe.edu.handlers;

import ifpe.edu.entities.Informativo;
import ifpe.edu.utils.BoolRequestResult;
import java.util.ArrayList;
import java.util.List;
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
    
    public BoolRequestResult deleteInformativo(Long informativoId) {
        BoolRequestResult resultado = new BoolRequestResult();
        
        try {
            resultado.hasErrors = false;
            entityManager.remove(
                    entityManager.createNamedQuery("Informativo.findById", Informativo.class)
                    .setParameter("id", informativoId).getSingleResult());
            
            resultado.data = true;
        } catch (Exception e) {
            resultado.hasErrors = true;
            resultado.message = e.getMessage();
            e.printStackTrace();
        }

        return resultado;
    }
    
    public List<Informativo> getInformativos() {
        List<Informativo> informativosAchados = new ArrayList<Informativo>();
        
        try {
            informativosAchados = entityManager
                                  .createNamedQuery("Informativo.getInformativos", Informativo.class)
                                  .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return informativosAchados;
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
