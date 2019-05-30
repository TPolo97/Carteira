package DAO;

import Model.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioDAO {
    @PersistenceContext(unitName = "CarteiraPU")
    EntityManagerFactory f = Persistence.createEntityManagerFactory("CarteiraPU");
    private EntityManager em = f.createEntityManager();
    
    public boolean gravar(Usuario u) {
        boolean sucesso = false;
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sucesso;
    }
    
    public Usuario getUsuario(String nomeUsuario, String senha) { 
      try {
        Usuario usuario = (Usuario) em.createQuery("SELECT u from Usuario u where u.nomeUsuario =:name and u.senha = :senha").setParameter("name", nomeUsuario).setParameter("senha", senha).getSingleResult();
        return usuario;
      } catch (NoResultException e) {
            return null;
      }
    }
 
  public boolean inserirUsuario(Usuario usuario) {
          try {
                em.persist(usuario);
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }
     
    public boolean deletarUsuario(Usuario usuario) {
          try {
                em.remove(usuario);
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }
}
