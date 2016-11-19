package at.time.record.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import at.time.record.model.User;

public class UserSerializer implements JsonSerializer<User> {

	@Override
	public JsonElement serialize(User user, Type type, JsonSerializationContext context) {
		return new JsonPrimitive(user.getOid());
	}

}
