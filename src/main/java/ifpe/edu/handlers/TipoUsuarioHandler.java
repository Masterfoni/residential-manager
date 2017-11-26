package ifpe.edu.handlers;

import ifpe.edu.entities.TipoUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class TipoUsuarioHandler {
    @PersistenceContext(name = "resmanager") 
    private EntityManager entityManager;
    
    public List<TipoUsuario> getTiposUsuario() 
    {
        List<TipoUsuario> tiposUsuarioAchados = new ArrayList<TipoUsuario>();

        try {
            tiposUsuarioAchados = entityManager.createNamedQuery("TipoUsuario.getTiposUsuario", TipoUsuario.class)
                                  .getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }        
        
        return tiposUsuarioAchados;
    }
}
