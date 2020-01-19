package com.automation.qa.util;

import com.automation.qa.model.Message;
import com.automation.qa.model.User;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.StandardLocation;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/*
 * Description : Long way to serialise JSON (Array of JSON) to it Objects. It will throw exception when JSON is not an Array of JSON
 *
 */
public class JSONToJsonObjectExp1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONToJsonObjectExp1.class);

    public static void main(String[] args) throws IOException, URISyntaxException {
        File file = Paths.get(Objects.requireNonNull(JSONToJsonObjectExp1.class.getClassLoader().getResource("json.json")).toURI()).toFile();
        JSONToJsonObjectExp1 jsonToJsonObjectExp = new JSONToJsonObjectExp1();
        List<Message> messages = jsonToJsonObjectExp.readJsonStream(new FileInputStream(file));
        messages.forEach( message -> LOGGER.info(message.toString()));
    }

    public List<Message> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return readMessagesArray(reader);
        }
    }

    public List<Message> readMessagesArray(JsonReader reader) throws IOException {
        List<Message> messages = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    public Message readMessage(JsonReader reader) throws IOException {
        long id = -1;
        String text = null;
        User user = null;
        List<Double> geo = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextLong();
            } else if (name.equals("text")) {
                text = reader.nextString();
            } else if (name.equals("geo") && reader.peek() != JsonToken.NULL) {
                geo = readDoublesArray(reader);
            } else if (name.equals("user")) {
                user = readUser(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Message(id, text, geo, user);
    }

    public List<Double> readDoublesArray(JsonReader reader) throws IOException {
        List<Double> doubles = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            doubles.add(reader.nextDouble());
        }
        reader.endArray();
        return doubles;
    }

    public User readUser(JsonReader reader) throws IOException {
        String username = null;
        int followersCount = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                username = reader.nextString();
            } else if (name.equals("followers_count")) {
                followersCount = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new User(username, followersCount);
    }
}
