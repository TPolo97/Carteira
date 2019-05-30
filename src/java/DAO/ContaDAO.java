package DAO;

import Model.Conta;
import Model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

@Stateless
public class ContaDAO {
    @PersistenceContext(unitName = "CarteiraPU")
    EntityManagerFactory f = Persistence.createEntityManagerFactory("CarteiraPU");
    private EntityManager em = f.createEntityManager();
    Usuario u = new Usuario();

    public boolean gravar(Conta c) {
        boolean sucesso = false;
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sucesso;
    }
    
    public List<Conta> listarDebito() {
        List<Conta> lista = null;
        try {                        
            Query query = em.createQuery("Select c from Conta c where c.tipo = false");
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public List<Conta> listarCredito() {
        List<Conta> lista = null;
        try {
            Query query = em.createQuery("Select c from Conta c where c.tipo = true");
            lista = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Conta selecionar(Long codigo){
        Conta conta = null;
        try {
            conta = em.find(Conta.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return conta;
    }
    
    public boolean remover(Conta conta){
        boolean sucesso = false;
        try {
            em.getTransaction().begin();
            conta = em.find(Conta.class, conta.getId());
            em.remove(conta);
            em.getTransaction().commit();
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return sucesso;
    }
}
