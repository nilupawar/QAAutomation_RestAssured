package com.automation.qa.examples;

import com.automation.qa.model.Message;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
* Description : Reduced code to serialise JSON (Array of JSON) to it Objects.
* It can handle object as simple object or array of objects
*/
public class JSONToJsonObjectExp2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONToJsonObjectExp2.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(JSONToJsonObjectExp2.class.getClassLoader().getResource("json.json")).toURI());

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(new JsonReader(Files.newBufferedReader(path)));
        Gson gson = new Gson();
        List<Message> messages = new ArrayList<>();

        if(jsonElement.isJsonArray()){
            jsonElement.getAsJsonArray().iterator().forEachRemaining(jsonElement1 -> messages.add(gson.fromJson(jsonElement1.toString(),Message.class)));
        }else if(jsonElement.isJsonObject()){
            JsonElement jsonElement1 = jsonElement.getAsJsonObject();
            messages.add(gson.fromJson(jsonElement1.toString(),Message.class));
        }
        messages.forEach( message -> LOGGER.info(message.toString()));
    }
}
