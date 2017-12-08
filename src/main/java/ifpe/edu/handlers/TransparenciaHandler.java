package ifpe.edu.handlers;

import ifpe.edu.entities.Transparencia;
import ifpe.edu.utils.RequestResult;
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
import javax.ws.rs.core.Request;

@Stateless
public class TransparenciaHandler {
    
    @PersistenceContext(name = "resmanager")
    private EntityManager entityManager;
    
    public TransparenciaHandler(){
    
    }
    
    public RequestResult AdicionarTransparencia(Transparencia transparencia)
    {
        RequestResult output = new RequestResult();
        output.hasErrors = false;
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        Set<ConstraintViolation<Transparencia>> violations = validator.validate(transparencia);
        
        if(violations.size()<=0)
        {
            try{
                entityManager.persist(transparencia);
                entityManager.flush();
            }catch (Exception e)
            {
                output.message = e.getMessage();
                output.hasErrors = true;
            }
        }else
        {   
            output.hasErrors = true;
            output.message = violations.iterator().next().getMessage();
        }
        
        return output;
    }
    
    public List<Transparencia> getTransparenciaAdicionadas(){
        List<Transparencia> transparenciasAdicionadas = new ArrayList<Transparencia>();
        
        try{
            transparenciasAdicionadas = entityManager
                    .createNamedQuery("Transparencia.getTransparenciaAdicionadas", Transparencia.class).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return transparenciasAdicionadas;
    }
    
    public void deleteTransparencia(Long transparenciaId)
    {
        entityManager.remove(entityManager.createNamedQuery("Transparencia.findyId", Transparencia.class).setParameter("id", transparenciaId)
                .getSingleResult());
    }
/*
    public TransparenciaRequestResult findTransparencia(Long id)
    {
        TransparenciaRequestResult output = new TransparenciaRequestResult();
        output.hasErrors = false;
        
        try{
            output.data = entityManager.createNamedQuery("transparencia.findByid", Transparencia.class).setParameter("id", id)
                    .getSingleResult();
        }catch(Exception e)
        {
            output.hasErrors = true;
            output.message = e.getMessage();
        
        }
                
            return output;
    }*/
}
