import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class MainSerializer {
    public static void main(String[] args) throws JsonProcessingException {
        User user = new User("Sara","SaiJa","Saija.Za@smail.net","+103278152654");

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(User.class, new UserSerializer());

        mapper.registerModule(module);

        System.out.println(mapper.writeValueAsString(user));

    }
}
