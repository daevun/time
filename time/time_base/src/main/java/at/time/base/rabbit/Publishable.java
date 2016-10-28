package at.time.base.rabbit;

import com.google.gson.Gson;

public interface Publishable {

	String getContentType();

	default String toGson() {
		return new Gson().toJson(this);
	}

}
