package ifpe.edu.entities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
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
@Table(name="TB_TIPO_USUARIO")
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.getCondomino", query = "SELECT tu FROM TipoUsuario tu WHERE tu.descricao = 'condomino'"),
    @NamedQuery(name = "TipoUSuario.findTipoById", query = "SELECT tu FROM TipoUsuario tu WHERE tu.id = :id"),
    @NamedQuery(name = "TipoUSuario.getTiposUsuario", query = "SELECT tu FROM TipoUsuario tu")
})

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
