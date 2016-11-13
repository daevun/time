package at.time.user.rabbit;

import com.google.gson.Gson;

public interface Publishable {

	String contentType();

	default String toGson() {
		return new Gson().toJson(this);
	}

}
