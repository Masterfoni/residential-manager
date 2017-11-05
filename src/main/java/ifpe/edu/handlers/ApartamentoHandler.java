package ifpe.edu.handlers;

import ifpe.edu.entities.Apartamento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class ApartamentoHandler {

    @PersistenceContext(name = "resmanager") 
    private EntityManager entityManager;
    
    public ApartamentoHandler() {
        
    }
    
    public List<Apartamento> getApartamentosDesocupados() 
    {
        List<Apartamento> apartamentosAchados = new ArrayList<Apartamento>();

        try {
            apartamentosAchados = entityManager.createNamedQuery("Apartamento.getApartamentos", Apartamento.class)
                                  .getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }        
        
        return apartamentosAchados;
    }
}
