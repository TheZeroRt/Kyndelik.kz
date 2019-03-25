import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class MainDeserializer {
    public static void main(String args[]) throws IOException {

        String json = "{\n" +
                "\t\"name\": \"Sara\",\n" +
                "\t\"surname\": \"SaiJa\",\n" +
                "\t\"mail\": \"Saija.Za@smail.net\",\n" +
                "\t\"phone\": \"+103278152654\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(User.class, new UserDeserializer());

        mapper.registerModule(module);

        User readValue = mapper.readValue(json, User.class);
        System.out.println(readValue.toString());
    }

}
