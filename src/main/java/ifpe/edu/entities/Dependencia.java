package ifpe.edu.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name="TB_DEPENDENCIA")
@Access(AccessType.FIELD)
public class Dependencia {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column (name="TXT_NOME")
    protected String nome;
    
    @NotNull
    @Column (name="TXT_LOCAL")
    protected String local;
    
    @NotNull
    @Column (name="VF_ATIVA")
    protected int ativa;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public int getAtiva() {
        return ativa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setAtiva(int ativa) {
        this.ativa = ativa;
    }
}
