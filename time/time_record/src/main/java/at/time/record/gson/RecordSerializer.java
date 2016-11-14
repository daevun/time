package at.time.record.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import at.time.record.model.Record;

public class RecordSerializer implements JsonSerializer<Record> {

	@Override
	public JsonElement serialize(Record record, Type typeOfSrc, JsonSerializationContext context) {
		final JsonObject json = new JsonObject();
		json.addProperty("oid", record.getOid());
		json.addProperty("begin", record.getBegin().toString());
		json.addProperty("end", record.getEnd().toString());
		json.addProperty("user", record.getUser().getOid());
		return json;
	}

}
