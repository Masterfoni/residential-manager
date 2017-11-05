package ifpe.edu.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_APARTAMENTO")
@NamedQueries({
    @NamedQuery(name = "Apartamento.getApartamentos", query = "SELECT a FROM Apartamento a WHERE a.ocupado = 0")
})
public class Apartamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="ID_USUARIO", referencedColumnName = "ID")
    private Usuario usuario;
    
    @NotNull
    @Column(name = "NUM_NUMERO")
    private int numero;
    
    @NotNull
    @Column(name = "NUM_ANDAR")
    private int andar;
    
    @NotNull
    @Column (name="VF_OCUPADO")
    protected int ocupado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }    

    public int getOcupado() {
        return ocupado;
    }

    public void setOcupado(int ocupado) {
        this.ocupado = ocupado;
    }
}
