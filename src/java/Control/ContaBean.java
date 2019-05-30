package Control;

import DAO.ContaDAO;
import Model.Conta;
import Model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named(value = "contaBean")
@ManagedBean
@RequestScoped
public class ContaBean implements Serializable {
    
    @EJB
    private ContaDAO contaDao = new ContaDAO();
    private Conta conta = new Conta();
    private List<Conta> lista;
    
    public void novo(){
        conta = new Conta();
    }
    
    public int atribuiUser(){
        Usuario u = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return u.getId();
    }
    
    public void gravar(){
        FacesContext context = FacesContext.getCurrentInstance();
        conta.setUserID(atribuiUser());
        boolean resultado = contaDao.gravar(conta);
        if (resultado){
            novo();
            context.addMessage(null, new FacesMessage("Conta cadastrada com sucesso!"));
        }else{
            context.addMessage(null, new FacesMessage("Erro ao cadastradar conta!"));
        }
    }
    
    public List<Conta> listarDeb(){
        List<Conta> l = contaDao.listarDebito();
        System.out.println(l);
        return l;
    }
    
    public List<Conta> listarCre(){
        List<Conta> l = contaDao.listarCredito();
        return l;
    }
        
    public void selecionar(ActionEvent evento) {
        Long id = (Long) evento.getComponent().getAttributes().get("id");
        conta = contaDao.selecionar(id);
    }
    
    public void remover() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean resultado = contaDao.remover(conta);
 
        if (resultado) {
            conta = new Conta();
            context.addMessage(null, new FacesMessage("Conta removida com sucesso"));
        } else {
            context.addMessage(null, new FacesMessage("Falha ao remover conta!"));
        }
    }

    public ContaDAO getContaDao() {
        return contaDao;
    }

    public void setContaDao(ContaDAO contaDao) {
        this.contaDao = contaDao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Conta> getLista() {
        return lista;
    }

    public void setLista(List<Conta> lista) {
        this.lista = lista;
    }
    
    
    
}
