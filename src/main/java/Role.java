import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.javalin.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public enum Role implements io.javalin.security.Role {
    ADMIN,
    USER,
    ANONIMUS;

//    public static Role getUserRole (Context context) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(User.class, new UserDeserializer());
//
//        mapper.registerModule(module);
//
//        User readValue = mapper.readValue(context.body() , User.class);
//
//        UserController userController = new UserController();
//
//    }

    public static Role setRoleByString(String text){
        if (text.toLowerCase().equals("admin")){
            return Role.ADMIN;
        }
        else {
            return Role.USER;
        }
    }

    public static Role getUserRole(Context ctx) throws Exception {

        try {
            System.out.println(ctx.basicAuthCredentials().toString());
        } catch (NullPointerException NO_AUTOROZATION){
            System.out.println("NO AUTORIZATION");
            return Role.ANONIMUS;
        }

        if (ctx.basicAuthCredentials().getUsername().isEmpty()
                || ctx.basicAuthCredentials().getPassword().isEmpty()){
            System.out.println("ANONIMUS ENTERED");
            return Role.ANONIMUS;
        }

        UserController userController = new UserController();
        if (userController.findByName(ctx.basicAuthCredentials().getUsername()).isEmpty()){
            System.out.println("NO COINCEDENCE");
            return Role.ANONIMUS;
        }
        else {
            System.out.println("COINCEDENCE");
            return userController.findByName(ctx.basicAuthCredentials().getUsername()).get(0).getRole();
        }


    }

}
