package ifpe.edu.handlers;

import ifpe.edu.entities.Apartamento;
import ifpe.edu.entities.Usuario;
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
    
    public void setDonoApartamento(long idApartamento, Usuario novoDono)
    {
        Apartamento apNovoDono = entityManager.createNamedQuery("Apartamento.findById", Apartamento.class)
                                 .setParameter("id", idApartamento).getSingleResult();
        
        apNovoDono.setUsuario(novoDono);
        apNovoDono.setOcupado(1);
        
        entityManager.merge(apNovoDono);
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
