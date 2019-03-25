import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer (){
        this(null);
    }

    public UserDeserializer (Class<User> user){
        super(user);
    }

    public User deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        String surname = node.get("surname").asText();
        String mail = node.get("mail").asText();
        String phone = node.get("phone").asText();
        String password = node.get("password").asText();
        Role role = Role.setRoleByString(node.get("role").asText());

        password = BCrypt.hashpw(password, BCrypt.gensalt());

        return new User(name,surname,mail,phone,password,role);
    }
}
