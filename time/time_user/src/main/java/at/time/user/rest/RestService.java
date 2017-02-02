package at.time.user.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import at.time.user.dao.UserDao;
import at.time.user.model.User;
import at.time.user.services.UserService;

@Path("user")
@RequestScoped
public class RestService {

	@Inject
	private UserService service;

//	public RestService() {
//		register(new ApplicationBinder());
//		packages(true, "at.time.user.binder");
//	}

	@GET
	@Path("{oid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(@PathParam("oid") final String oid) {
		final User user = new UserDao().getBySozVers(oid);
		return user != null ? user.toString() : StringUtils.EMPTY;
	}

	@POST
	@Path("create")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String saveUser(final String gsonUser) {
		final User user = new Gson().fromJson(gsonUser, User.class);
		service.saveUser(user);
		return user.toString();
	}

}
