package at.time.user.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import at.time.user.model.User;
import at.time.user.services.UserService;

@ManagedBean
@ViewScoped
@Named
public class UserBean implements Serializable {

	private static final long serialVersionUID = -4838655066393173243L;

	private String oid;
	private String name;
	private String surname;

	@Inject
	private UserService service;

	public String getOid() {
		return oid;
	}

	public void setOid(final String oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String createUser() {
		final User user = service.createUser();
		user.setOid(getOid());
		user.setName(getName());
		user.setSurname(getSurname());
		service.saveUser(user);
		final UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
		return view.getViewId() + "?faces-redirect=true";
	}

}
