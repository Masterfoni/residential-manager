package ifpe.edu.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PARAMETRO_SISTEMA")
@NamedQueries({
    @NamedQuery(name = "ParametroSistema.findByKey", query = "SELECT ps FROM ParametroSistema ps WHERE ps.chave = :chave")
})
public class ParametroSistema implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column (name = "TXT_CHAVE")
    private String chave;
    
    @NotNull
    @Column (name = "TXT_VALOR")
    private String valor;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
}
