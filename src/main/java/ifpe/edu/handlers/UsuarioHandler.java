package ifpe.edu.handlers;

import ifpe.edu.entities.TipoUsuario;
import ifpe.edu.entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioHandler {

    @PersistenceContext(name = "resmanager") 
    private EntityManager entityManager;
    
    public UsuarioHandler() {
        
    }
    
    public long insertUsuario(Usuario usuario)
    {
        long idRetorno = 0;
        TipoUsuario condomino = new TipoUsuario();
        
        try {
            condomino = entityManager.createNamedQuery("TipoUsuario.getCondomino", TipoUsuario.class)
                        .getSingleResult();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        usuario.setTipoUsuario(condomino);
        
        try {
            entityManager.persist(usuario);
            entityManager.flush();
            
            idRetorno = usuario.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return idRetorno;
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
