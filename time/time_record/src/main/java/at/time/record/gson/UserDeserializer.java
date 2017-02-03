package at.time.record.gson;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import at.time.record.dao.UserDao;
import at.time.record.model.User;

@Named
public class UserDeserializer implements JsonDeserializer<User> {

	@Inject
	private UserDao userDao;

	@Override
	public User deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {
		return userDao.getByOid(json.getAsLong());
	}

}
