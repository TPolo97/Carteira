package Control;

import DAO.UsuarioDAO;
import Model.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "loginBean")
@ManagedBean
@RequestScoped
public class LoginBean {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
  private Usuario usuario = new Usuario();
  
  public void gravar(){
        FacesContext context = FacesContext.getCurrentInstance();
        boolean resultado = usuarioDAO.gravar(usuario);
        if (resultado){
            usuario = new Usuario();
            context.addMessage(null, new FacesMessage("Conta cadastrada com sucesso!"));
        }else{
            context.addMessage(null, new FacesMessage("Erro ao cadastradar conta!"));
        }
    }
   
  public String envia() {
        usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
        if (usuario == null) {
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!","Erro no Login!"));
            return null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Login efetuado com sucesso!"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);                       
            return null;
        }
  }
 
  public Usuario getUsuario() {
    return usuario;
  }
 
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
