package at.time.user.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import at.time.user.dao.UserDao;
import at.time.user.model.User;
import at.time.user.services.UserService;

@Path("user")
@RequestScoped
public class RestService {

	@GET
	@Path("{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("oid") final String oid) {
		final User user = new UserDao().getBySozVers(oid);
		return user != null ? user.toGson() : StringUtils.EMPTY;
	}

	@POST
	@Path("create")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response saveUser(final String gsonUser) {
		final User user = new Gson().fromJson(gsonUser, User.class);
		if (user != null) {
			new UserService().saveUser(user);
			return Response.status(200).entity(user.toGson()).build();
		}
		return Response.status(500).entity("Could not create User").build();
	}

}
