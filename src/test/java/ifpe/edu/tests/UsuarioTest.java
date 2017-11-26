package ifpe.edu.tests;

import ifpe.edu.entities.TipoUsuario;
import ifpe.edu.entities.Usuario;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class UsuarioTest {
    
    private static Validator validator;
    
    @BeforeClass
    public static void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void usuarioIsInvalid() {
        Usuario usr = new Usuario();
        usr.setCpf("2312312");
        usr.setNome("");
        usr.setLogin("");
        
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usr);
        assertNotEquals(0, violations.size());
    }
    
    @Test
    public void usuarioIsValid() {
        Usuario usr = new Usuario();
        TipoUsuario usrType = new TipoUsuario();
        usrType.setDescricao("Condomino");
        usrType.setId(Long.MAX_VALUE);
        
        usr.setLogin("loginTeste");
        usr.setNome("nomeTeste");
        usr.setSenha("senhaTeste");
        usr.setSexo("Masculino");
        usr.setTipoUsuario(usrType);
        usr.setId(Long.MAX_VALUE);
        usr.setCpf("107.393.994-46");
        
        Set<ConstraintViolation<Usuario>> violations = validator.validate(usr);
        assertEquals(0, violations.size());
    }
}
