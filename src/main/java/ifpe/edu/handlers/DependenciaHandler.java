package ifpe.edu.handlers;

import ifpe.edu.entities.Dependencia;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class DependenciaHandler {
    @PersistenceContext(name = "resmanager") 
    private EntityManager entityManager;
    
    public DependenciaHandler() {
    
    }
    
    public Dependencia findDependencia(Long id)
    {
        Dependencia dependenciaAchada = null;
        
        try {
            dependenciaAchada = entityManager.createNamedQuery("Dependencia.findById", Dependencia.class)
                            .setParameter("id", id).getSingleResult();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return dependenciaAchada;
    }
    
    public List<Dependencia> getDependenciasAtivas() 
    {
        List<Dependencia> dependenciasAchadas = new ArrayList<Dependencia>();

        try {
            dependenciasAchadas = entityManager.createNamedQuery("Dependencia.getDependenciasAtivas", Dependencia.class)
                                  .getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }        
        
        return dependenciasAchadas;
    }
}

