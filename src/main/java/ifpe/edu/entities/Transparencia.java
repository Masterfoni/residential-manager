package ifpe.edu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_TRANSPARENCIA")
public class Transparencia implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
    
    @Column(name="TXT_DESCRICAO")
    private String descricao;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DT_VIGENCIA")
    private Date dataVigencia;
    
    @NotNull
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
