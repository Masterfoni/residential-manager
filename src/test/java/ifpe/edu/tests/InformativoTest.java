package ifpe.edu.tests;

import ifpe.edu.entities.Informativo;
import ifpe.edu.entities.Usuario;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InformativoTest {
    
    private static Validator validator;
    
    @BeforeClass
    public static void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        System.out.println("ifpe.edu.tests.InformativoTest.setUpClass()");
    }
    
    @Test
    public void informativoMessageIsNull(){
        Informativo info = new Informativo();
        Usuario usr = new Usuario();
        info.setDescricao(null);
        info.setDataCriacao(new Date());
        info.setId(Long.MAX_VALUE);
        info.setUsuario(usr);
        
        Set<ConstraintViolation<Informativo>> violations = validator.validate(info);
        assertEquals(1, violations.size());
        assertEquals("Descrição deve ser informada!", violations.iterator().next().getMessage());
    }
}
