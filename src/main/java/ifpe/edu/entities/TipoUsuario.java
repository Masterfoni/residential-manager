package ifpe.edu.entities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_TIPO_USUARIO")
@Access(AccessType.FIELD)
public class TipoUsuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
    
    @NotNull
    @Column (name="TXT_DESCRICAO")
    protected String descricao;

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
