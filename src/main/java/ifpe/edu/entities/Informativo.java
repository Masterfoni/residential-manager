/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.edu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "TB_INFORMATIVO")
public class Informativo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column (name ="TXT_DESCRICAO")
    protected  String descricao;
    
    @Lob    
    @Column (name ="FOTO_IMAGEM", columnDefinition = "LONGBLOB") 
    private byte[] imagem;
    
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

     public byte[] getImagem() {
        
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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
