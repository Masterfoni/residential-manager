package ifpe.edu.handlers;

import ifpe.edu.entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioHandler {

    @PersistenceContext(name = "resmanager") 
    private EntityManager entityManager;
    
    public UsuarioHandler() {
        
    }
        
    public Usuario findUsuario(String login, String senha) 
    {
        Usuario usuarioAchado = null;
        try {
            usuarioAchado = entityManager.createNamedQuery("Usuario.findByLoginSenha", Usuario.class)
                            .setParameter("login", login).setParameter("senha", senha).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }        
        
        return usuarioAchado;
    }
}
