package Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
       
      @Id
      @Column(name="id", nullable=false, unique=true)
      private int id;
       
      @Column(name="userName", nullable=false, unique=true)
      private String nomeUsuario;
       
      @Column(name="password", nullable=false, unique=false)
      private String senha;
      
      @Column(name="saldo", nullable=false, unique=false)
      private Double saldo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      
      
      
      public String getNomeUsuario() {
            return nomeUsuario;
      }
       
      public void setNomeUsuario(String nomeUsuario) {
            this.nomeUsuario = nomeUsuario;
      }
       
      public String getSenha() {
            return senha;
      }
       
      public void setSenha(String senha) {
            this.senha = senha;
      }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
      
      
 }
