package at.time.report.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.GsonBuilder;

import at.time.report.dao.RecordDao;
import at.time.report.dao.UserDao;
import at.time.report.gson.UserSerializer;
import at.time.report.model.User;

@Path("report")
@RequestScoped
public class RestService {

	@GET
	@Path("{oid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRecords(@PathParam("oid") final String sozVers) {
		final User user = new UserDao().getBySozVers(sozVers);
		return new GsonBuilder().registerTypeAdapter(User.class, new UserSerializer()).setPrettyPrinting().create()
				.toJson(new RecordDao().getAllBy(user));

	}

}
