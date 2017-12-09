package ifpe.edu.handlers;

import ifpe.edu.entities.Visita;
import ifpe.edu.utils.EmailSender;
import ifpe.edu.utils.RequestResult;
import ifpe.edu.utils.VisitaRequestResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Stateless
public class VisitaHandler {
    
    @PersistenceContext(name = "resmanager")
    private EntityManager entityManager;
    
    public VisitaHandler(){
    
    }
    
    public RequestResult marcarCompareceu(Visita visita) 
    {
        RequestResult output = new RequestResult();
        output.hasErrors = false;
        
        visita.setReserva(1);
        
        try {
            entityManager.merge(visita);
        } catch (Exception e) {
            e.printStackTrace();
            output.hasErrors = true;
        }
        
        return output;
    }
    
    public RequestResult insertVisita(Visita visita)
    {
        RequestResult output = new RequestResult();   
        output.hasErrors = false;
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<Visita>> violations = validator.validate(visita);

        if(violations.size() <= 0)
        {
            try {
                entityManager.persist(visita);
                entityManager.flush();
            } catch (Exception e){
                output.message = e.getMessage();
                output.hasErrors = true;
            }
        }
        else
        {
            output.hasErrors = true;
            output.message = violations.iterator().next().getMessage();
        }
        
        return output;
    }

    public List<Visita> getVisitasNaoComparecidas() {
        List<Visita>  visitaCadastradas = new ArrayList<Visita>();
        
        try {
            visitaCadastradas = entityManager.createNamedQuery("Visita.getVisitasNaoComparecidas", Visita.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return visitaCadastradas;
    }
    
    public void deleteVisita(Long visitaId)
    {
        entityManager.remove(entityManager.createNamedQuery("Visita.findById", Visita.class).setParameter("id", visitaId)
                      .getSingleResult());
    }
    
    public VisitaRequestResult findVisita(Long id)
    {
        VisitaRequestResult output = new VisitaRequestResult();
        output.hasErrors = false;
        
        try{
            output.data = entityManager.createNamedQuery("Visita.findById", Visita.class).setParameter("id", id)
                            .getSingleResult();
        }catch (Exception e){
            output.hasErrors = true;
            output.message = e.getMessage();
        }
        
        return output;
    }
}
