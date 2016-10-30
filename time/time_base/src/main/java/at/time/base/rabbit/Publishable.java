package at.time.base.rabbit;

import com.google.gson.Gson;

public interface Publishable {

	String contentType();

	default String toGson() {
		return new Gson().toJson(this);
	}

}
