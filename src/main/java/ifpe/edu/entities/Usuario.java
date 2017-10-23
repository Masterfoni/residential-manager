package ifpe.edu.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TB_USUARIO")
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Usuario.findByLoginSenha", query = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha")
})
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
    
    @NotNull
    @Column (name="TXT_NOME")
    private String nome;

    @NotNull
    @Column(name="TXT_LOGIN", unique = true)
    private String login;

    @NotNull
    @Column (name="TXT_SENHA")
    private String senha;

    @NotNull
    @Column (name="TXT_CPF", unique=true)
    private String cpf;

    @NotNull
    @Column (name="TXT_RG", unique=true)
    private String rg;

    @NotNull
    @Column (name="TXT_SEXO")
    private String sexo;
    
    @NotNull
    @OneToOne
    @JoinColumn(name="ID_TIPO_USUARIO", referencedColumnName = "ID")
    private TipoUsuario tipoUsuario;
    
    public String getNome() {
            return nome;
    }

    public void setNome(String nome) {
            this.nome = nome;
    }

    public String getSenha() {
            return senha;
    }

    public void setSenha(String senha) {
            this.senha = senha;
    }

    public String getCpf() {
            return cpf;
    }

    public void setCpf(String cpf) {
            this.cpf = cpf;
    }

    public String getRg() {
            return rg;
    }

    public void setRg(String rg) {
            this.rg = rg;
    }

    public String getSexo() {
            return sexo;
    }

    public void setSexo(String sexo) {
            this.sexo = sexo;
    }

    public Long getId() {
            return id;
    }

    public void setId(Long id) {
            this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
