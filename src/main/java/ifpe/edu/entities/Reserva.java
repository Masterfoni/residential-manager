package ifpe.edu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_RESERVA")
@NamedQueries({
    @NamedQuery(name = "Reserva.getReservasPendentes", query = "SELECT r FROM Reserva r WHERE r.situacao = 0"),
    @NamedQuery(name = "Reserva.findById", query = "SELECT r FROM Reserva r WHERE r.id = :id")
})
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DT_INICIO")
    private Date dataInicio;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DT_FIM")
    private Date dataFim;
    
    @NotNull
    @Column(name="SITUACAO")
    private int situacao;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="ID_USUARIO", referencedColumnName="ID")
    private Usuario usuario;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="ID_DEPENDENCIA", referencedColumnName="ID")
    private Dependencia dependencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }
}
