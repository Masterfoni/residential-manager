/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author DaniloP
 */
@Entity
@Table(name = "TB_VISITA")
@NamedQueries({
    @NamedQuery(name = "Visita.getVisitas", query = "SELECT v FROM Visita v ORDER BY v.dataCriacao"),
    @NamedQuery(name = "Visita.findById", query = "SELECT v FROM Visita v WHERE v.id = :id")
})
public class Visita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "TXT_NOME")
    private String nome;

    @NotNull
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
    @Column(name ="DT_CRIACAO")
    private Date dataCriacao;

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
       
}
