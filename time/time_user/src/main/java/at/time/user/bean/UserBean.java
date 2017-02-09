package at.time.user.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import at.time.user.model.User;
import at.time.user.services.UserService;

@RequestScoped
@Named
public class UserBean implements Serializable {

	private static final long serialVersionUID = -4838655066393173243L;

	private String name;
	private String surname;
	private String sozVers;
	private String externalUser;

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

	public void setSozVers(final String sozVers) {
		this.sozVers = sozVers;
	}

	public String getExternalUser() {
		return externalUser;
	}

	public void setExternalUser(final String externalUser) {
		this.externalUser = externalUser;
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

	public void getAndSaveExternalUser() {
		final Client client = Client.create();

		final WebResource webResource = client.resource("https://employees.stage.sexy/employees/" + getExternalUser());

		final ClientResponse response = webResource.accept(MediaType.TEXT_HTML).get(ClientResponse.class);
		final String htmlUser = response.getEntity(String.class);
		String lastName = StringUtils.substringBetween(htmlUser, "<strong>Lastname:</strong>", "</p>");
		String firstName = StringUtils.substringBetween(htmlUser, "<strong>Firstname:</strong>", "</p>");
		String sozVers = StringUtils.substringBetween(htmlUser, "<strong>Social security numer:</strong>", "</p>");

		lastName = StringUtils.deleteWhitespace(lastName);
		lastName = StringUtils.chomp(lastName);

		firstName = StringUtils.deleteWhitespace(firstName);
		firstName = StringUtils.chomp(firstName);

		sozVers = StringUtils.deleteWhitespace(sozVers);
		sozVers = StringUtils.chomp(sozVers);

		final User user = service.createUser();
		user.setSurname(lastName);
		user.setName(firstName);
		user.setSozVers(sozVers);
		service.saveUser(user);
	}

}
