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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="TB_TRANSPARENCIA")
@NamedQueries({
    @NamedQuery(name ="Transparencia.getTransparenciaAdicionadas", query = "SELECT t FROM Transparencia t ORDER BY t.dataVigencia"),
    @NamedQuery(name ="Transparencia.getTransparenciasByMes", query = "SELECT t FROM Transparencia t WHERE FUNC('MONTH' , t.dataVigencia) = :mes ORDER BY t.dataVigencia"),
    @NamedQuery(name ="Transparencia.getTransparenciasByMesAno", query = "SELECT t FROM Transparencia t WHERE FUNC('MONTH' , t.dataVigencia) = :mes AND FUNC('YEAR', t.dataVigencia) = :ano ORDER BY t.dataVigencia"),
    @NamedQuery(name ="Transparencia.getTransparenciasByAno", query = "SELECT t FROM Transparencia t WHERE FUNC('YEAR', t.dataVigencia) = :ano ORDER BY t.dataVigencia"),
    @NamedQuery(name ="Transparencia.findById", query = "SELECT t FROM Transparencia t WHERE t.id = :id")
})
public class Transparencia implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
    
    @NotBlank(message = "Informe uma Descrição!")
    @Column(name="TXT_DESCRICAO", length = 9999)
    private String descricao;
    
    @NotNull(message = "Data da Vigência deve ser informada!")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DT_VIGENCIA")
    private Date dataVigencia;
    
    @Min(value = 1, message = "Valor deve ser maior ou igual à 1, no mínimo.")
    @Column(name="NUM_VALOR")
    private double valor;
    
    @OneToOne
    @JoinColumn(name="ID_USUARIO", referencedColumnName="ID")
    private Usuario usuario;

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

    public Date getDataVigencia() {
        return dataVigencia;
    }

    public void setDataVigencia(Date dataVigencia) {
        this.dataVigencia = dataVigencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
