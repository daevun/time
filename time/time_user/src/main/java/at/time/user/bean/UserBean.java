package at.time.user.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import at.time.user.model.User;
import at.time.user.services.UserService;

@RequestScoped
@Named
public class UserBean implements Serializable {

	private static final long serialVersionUID = -4838655066393173243L;

	private String name;
	private String surname;
	private String sozVers;

	@Inject
	private UserService service;

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

	public String getSozVers() {
		return sozVers;
	}

	public void setSozVers(String sozVers) {
		this.sozVers = sozVers;
	}

	public String createUser() {
		final User user = service.createUser();
		user.setName(getName());
		user.setSurname(getSurname());
		user.setSozVers(getSozVers());
		service.saveUser(user);
		final UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
		return view.getViewId() + "?faces-redirect=true";
	}

}
