package ifpe.edu.handlers;

import ifpe.edu.entities.Transparencia;
import ifpe.edu.utils.BoolRequestResult;
import ifpe.edu.utils.RequestResult;
import ifpe.edu.utils.TransparenciaRequestResult;
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
            try {
                entityManager.persist(transparencia);
                entityManager.flush();
            } catch (Exception e) {
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

    public List<Transparencia> getTransparenciasByMes(int mes)
    {
        List<Transparencia> transparenciasAdicionadas = new ArrayList<>();
        
        try {
            transparenciasAdicionadas = entityManager
                    .createNamedQuery("Transparencia.getTransparenciasByMes", Transparencia.class)
                    .setParameter("mes", mes).getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return transparenciasAdicionadas;
    }
    
    public List<Transparencia> getTransparenciasAdicionadasByAno(int ano)
    {
        List<Transparencia> transparenciasAdicionadas = new ArrayList<>();
        
        try {
            transparenciasAdicionadas = entityManager
                    .createNamedQuery("Transparencia.getTransparenciasByAno", Transparencia.class)
                    .setParameter("ano", ano).getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return transparenciasAdicionadas;
    }
    
    public List<Transparencia> getTransparenciasByMesAno(int mes, int ano)
    {
        List<Transparencia> transparenciasAdicionadas = new ArrayList<>();
        
        try {
            transparenciasAdicionadas = entityManager
                    .createNamedQuery("Transparencia.getTransparenciasByMesAno", Transparencia.class)
                    .setParameter("mes", mes)
                    .setParameter("ano", ano).getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return transparenciasAdicionadas;
    }
    
    public List<Transparencia> getTransparenciaAdicionadas()
    {
        List<Transparencia> transparenciasAdicionadas = new ArrayList<>();
        
        try {
            transparenciasAdicionadas = entityManager
                    .createNamedQuery("Transparencia.getTransparenciaAdicionadas", Transparencia.class).getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return transparenciasAdicionadas;
    }

    public BoolRequestResult deleteTransparencia(Long transparenciaId)
    {
        BoolRequestResult resultado = new BoolRequestResult();
        
        try {
            resultado.hasErrors = false;
            
            entityManager.remove(entityManager.createNamedQuery("Transparencia.findById", Transparencia.class).setParameter("id", transparenciaId)
                    .getSingleResult());
            
            resultado.data = true;
        } catch (Exception e) {
            resultado.hasErrors = true;
            resultado.message = e.getMessage();
            e.printStackTrace();
        }
        
        return resultado;
    }

    public TransparenciaRequestResult findTransparencia(Long id)
    {
        TransparenciaRequestResult output = new TransparenciaRequestResult();
        output.hasErrors = false;
        
        try {
            output.data = entityManager.createNamedQuery("Transparencia.findById", Transparencia.class).setParameter("id", id)
                    .getSingleResult();
        } catch(Exception e) {
            output.hasErrors = true;
            output.message = e.getMessage();
        }
        
        return output;
    }
}
