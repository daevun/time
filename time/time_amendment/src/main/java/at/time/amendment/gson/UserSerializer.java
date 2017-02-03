package at.time.amendment.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import at.time.amendment.model.User;

public class UserSerializer implements JsonSerializer<User> {

	@Override
	public JsonElement serialize(final User user, final Type type, final JsonSerializationContext context) {
		return new JsonPrimitive(user.getOid());
	}

}
