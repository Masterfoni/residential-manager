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
    @Column (name ="IMAGEM") 
    @Basic(fetch=FetchType.EAGER)
    private byte[] imagem;
    
    @OneToOne
    @JoinColumn(name="ID_USUARIO", referencedColumnName = "ID")
    private Usuario usuario;
    
    @NotNull
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

     public Long getImagem() {
        
        return imagem;
    }

    public void setImagem(Long imagem) {
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
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informativo)) {
            return false;
        }
        Informativo other = (Informativo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ifpe.edu.entities.Informativo[ id=" + id + " ]";
    }
    
}
