package ifpe.edu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TB_INFORMATIVO")
@NamedQueries({
    @NamedQuery(name = "Informativo.findById", query = "SELECT i FROM Informativo i WHERE i.id = :id"),
    @NamedQuery(name = "Informativo.getInformativos", query = "SELECT i FROM Informativo i ORDER BY i.dataCriacao")
})
public class Informativo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column (name ="TXT_DESCRICAO")
    protected  String descricao;
    
    @OneToOne
    @JoinColumn(name="ID_USUARIO", referencedColumnName = "ID")
    private Usuario usuario;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "DT_CRIACAO")
    private Date dataCriacao;
    
    public Long getId() {
        
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     public String getDescricao() {
        
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

     public Usuario getUsuario() {
        
        return usuario;
    }

    public void setUsuarioId(Usuario usuario) {
        this.usuario = usuario;
    }
    
     public Date getDataCriacao() {
        
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    } 
}
