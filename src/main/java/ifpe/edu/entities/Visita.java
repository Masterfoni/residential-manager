package ifpe.edu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
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
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "TB_VISITA")
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Visita.getVisitasNaoComparecidas", 
                query = "SELECT v FROM Visita v WHERE v.finalizada = 0 ORDER BY v.dataVisita DESC"),
    @NamedQuery(name = "Visita.findById", query = "SELECT v FROM Visita v WHERE v.id = :id")
})
public class Visita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Você deve preencher o nome da visita.")
    @Column(name = "TXT_NOME")
    private String nome;

    @CPF(message = "CPF incorreto!")
    @Column(name = "TXT_CPF")
    private String cpf;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    private Usuario usuario;

    @NotNull
    @Column(name = "VF_FINALIZADA")
    private int finalizada;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CRIACAO")
    private Date dataCriacao;
    
    @NotNull(message = "Data da visita deve ser informada!")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_VISITA")
    private Date dataVisita;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getReserva() {
        return finalizada;
    }

    public void setReserva(int finalizada) {
        this.finalizada = finalizada;
    }
    
    public Date getDataCriacao(){
     
        return dataCriacao;
    }
    
    public void setDataCriacao(Date dataCriacao){
        this.dataCriacao = dataCriacao;
    }
    
    public Date getDataVisita(){
        return dataVisita;
    }
    
    public void setDataVisita(Date dataVisita){
        this.dataVisita = dataVisita;
    }
       
}
