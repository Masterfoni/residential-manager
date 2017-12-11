package ifpe.edu.handlers;

import ifpe.edu.entities.Reserva;
import ifpe.edu.utils.RequestResult;
import ifpe.edu.utils.ReservaRequestResult;
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
public class ReservaHandler {
    
    @PersistenceContext(name = "resmanager")
    private EntityManager entityManager;
    
    public ReservaHandler(){
    
    }
    
    public List<Reserva> getReservasPendentes() {
        List<Reserva>  reservasCadastradas = new ArrayList<Reserva>();
        
        try {
            reservasCadastradas = entityManager.createNamedQuery("Reserva.getReservasPendentes", Reserva.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return reservasCadastradas;
    }
    
    public RequestResult insertReserva(Reserva reserva)
    {
        RequestResult output = new RequestResult();   
        output.hasErrors = false;
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<Reserva>> violations = validator.validate(reserva);

        if(violations.size() <= 0)
        {
            try {
                entityManager.persist(reserva);
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
    
    public ReservaRequestResult findReserva(Long id)
    {
        ReservaRequestResult output = new ReservaRequestResult();
        output.hasErrors = false;
        
        try{
            output.data = entityManager.createNamedQuery("Reserva.findById", Reserva.class).setParameter("id", id)
                            .getSingleResult();
        }catch (Exception e){
            output.hasErrors = true;
            output.message = e.getMessage();
        }
        
        return output;
    }
    
    public RequestResult aprovarReserva(Reserva reserva) 
    {
        RequestResult output = new RequestResult();
        output.hasErrors = false;
        
        reserva.setSituacao(3);
        
        try {
            entityManager.merge(reserva);
        } catch (Exception e) {
            e.printStackTrace();
            output.hasErrors = true;
        }
        
        return output;
    }
    
    public RequestResult reprovarReserva(Reserva reserva) 
    {
        RequestResult output = new RequestResult();
        output.hasErrors = false;
        
        reserva.setSituacao(1);
        
        try {
            entityManager.merge(reserva);
        } catch (Exception e) {
            e.printStackTrace();
            output.hasErrors = true;
        }
        
        return output;
    }
}
