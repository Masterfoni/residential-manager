package ifpe.edu.handlers;

import ifpe.edu.entities.TipoUsuario;
import ifpe.edu.entities.Usuario;
import ifpe.edu.utils.LongRequestResult;
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
public class UsuarioHandler {
    
    @PersistenceContext(name = "resmanager") 
    private EntityManager entityManager;
    
    public UsuarioHandler() {
        
    }
    
    public LongRequestResult insertUsuario(Usuario usuario)
    {
        LongRequestResult resultado = new LongRequestResult();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        
        TipoUsuario condomino = new TipoUsuario();
        
        try {
            condomino = entityManager.createNamedQuery("TipoUsuario.getCondomino", TipoUsuario.class)
                        .getSingleResult();
            
        } catch (Exception e) {
            resultado.hasErrors = true;
            resultado.message = "Não foi possível buscar o tipo de usuário específico. Detalhes: " + e.getMessage();
            e.printStackTrace();
        }
        
        if(!resultado.hasErrors)
        {
            usuario.setTipoUsuario(condomino);
            
            Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

            if(violations.size() <= 0)
            {
                try {

                    entityManager.persist(usuario);
                    entityManager.flush();

                    resultado.Data.add(usuario.getId());
                } catch (Exception e) {
                    resultado.hasErrors = true;
                    resultado.message = e.getMessage();
                    e.printStackTrace();
                }
            }
            else
            {
                resultado.hasErrors = true;
                resultado.message = violations.iterator().next().getMessage();
            }
        }
            
        return resultado;
    }
    
    public List<Usuario> getUsuarios()
    {
        List<Usuario> usuariosAchados = new ArrayList<Usuario>();
        
        try {
            usuariosAchados = entityManager.createNamedQuery("Usuario.getUsuarios", Usuario.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usuariosAchados;
    }
        
    public Usuario findUsuario(String login, String senha) 
    {
        Usuario usuarioAchado = null;
        try {
            usuarioAchado = entityManager.createNamedQuery("Usuario.findByLoginSenha", Usuario.class)
                            .setParameter("login", login).setParameter("senha", senha).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }        
        
        return usuarioAchado;
    }
    
    public Usuario findUsuario(Long id)
    {
        Usuario usuarioAchado = null;
        
        try {
            usuarioAchado = entityManager.createNamedQuery("Usuario.findById", Usuario.class)
                            .setParameter("id", id).getSingleResult();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usuarioAchado;
    }
}
