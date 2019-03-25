import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {

    protected UserSerializer() {
        super(User.class);
    }

    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name" , user.getName());
        jsonGenerator.writeStringField("surname", user.getSurname());
        jsonGenerator.writeStringField("mail", user.getMail());
        jsonGenerator.writeStringField("phone", user.getPhone());
        jsonGenerator.writeEndObject();
        //New comment
    }
}
