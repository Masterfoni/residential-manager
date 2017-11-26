package ifpe.edu.tests;

import ifpe.edu.entities.Apartamento;
import ifpe.edu.entities.Informativo;
import ifpe.edu.entities.Usuario;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApartamentoTest {
    
    private static Validator validator;
    
    @BeforeClass
    public static void setUpClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        System.out.println("ifpe.edu.tests.InformativoTest.setUpClass()");
    }
    
    @Test
    public void apartamentoIsValid(){
        Apartamento apto = new Apartamento();
        apto.setAndar(1);
        apto.setId(Long.MAX_VALUE);
        apto.setNumero(0);
        
        Set<ConstraintViolation<Apartamento>> violations = validator.validate(apto);
        assertEquals(1, violations.size());
    }
    
}
