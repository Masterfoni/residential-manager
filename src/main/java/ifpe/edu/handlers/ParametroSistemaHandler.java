package ifpe.edu.handlers;

import ifpe.edu.entities.ParametroSistema;
import ifpe.edu.utils.ParametroSistemaRequestResult;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ParametroSistemaHandler {
    @PersistenceContext(name = "resmanager") 
    private EntityManager entityManager;
    
    public ParametroSistemaHandler() {
    
    }
    
    public ParametroSistemaRequestResult findParametro(String chave)
    {
        ParametroSistemaRequestResult resultado = new ParametroSistemaRequestResult();
        resultado.hasErrors = false;
        
        try {
            resultado.data = entityManager.createNamedQuery("ParametroSistema.findByKey", ParametroSistema.class)
                                          .setParameter("chave", chave).getSingleResult();
        } catch (Exception e) {
            resultado.hasErrors = true;
            resultado.message = e.getMessage();
        }
        
        return resultado;
    }
}

