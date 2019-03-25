import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import io.javalin.Javalin;
import org.mindrot.jbcrypt.BCrypt;

import java.io.FileInputStream;

import static io.javalin.security.SecurityUtil.roles;

/** 2.0 Have Access Manager and B.CRYPT*/

public class Main{
    public static void main(String args[]) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, "Cp866"));

        Javalin app = Javalin.create().enableStaticFiles("/public");
        UserController userController = new UserController();

        System.out.println(userController.findByName("Sara").get(0).getRole());

/**JACALIN поси=мотреть access manager */

        app.accessManager((handler, ctx, permittedRoles) -> {
            Role userRole = Role.getUserRole(ctx);
            if (permittedRoles.contains(userRole)) {
                handler.handle(ctx);
            }
            else {
                ctx.status(401).result("Unauthorized...");
            }
        });

        app.start(9000);

        app.routes(() -> {
            app.get("/",    ctx -> {
//                ctx.result(new FileInputStream("C:\\Users\\ANUS\\IdeaProjects\\javalinJACKsonDB #2\\src\\main\\resources\\public\\Start.html"));
                ctx.redirect("http://localhost:9000/Start.html"); },
                    roles(Role.ADMIN, Role.USER , Role.ANONIMUS));

            app.get("/create", ctx -> {
                new UserController().create(ctx);
                System.out.println("New User");
                ctx.redirect("http://localhost:9000/New_user_created.html");},
                    roles(Role.ADMIN));

            app.get("/user/:id",    ctx -> {
                System.out.println("Somebody found out the "+ctx.pathParam("id")+" user");
                String userId=ctx.pathParam("id");
                ctx.result(userController.find(Integer.valueOf(userId))); },
                    roles(Role.ADMIN , Role.USER));
        });


        app.post("/test", ctx->{
            ctx.result("Searching:"+ctx.basicAuthCredentials().getUsername());
            System.out.println(ctx.basicAuthCredentials().getUsername());
            System.out.println(ctx.basicAuthCredentials().getPassword());
            try {
                System.out.println(userController.findByName(ctx.basicAuthCredentials().getUsername()).get(0).getRole());
            }
            catch (Exception NO_USER_WITH_THIS_NAME){
                System.out.println(ctx.basicAuthCredentials().getUsername()+" was not found in server");
            }

        });


        app.post("/getusers", ctx->{
            System.out.println(ctx.basicAuthCredentials().getUsername());
            System.out.println(ctx.basicAuthCredentials().getPassword());
            System.out.println(userController.findByName(ctx.basicAuthCredentials().getUsername()).get(0).getRole());
            try {
                if (userController.findByName(ctx.basicAuthCredentials().getUsername()).get(0).getRole().equals(Role.ADMIN)
                        && BCrypt.checkpw(ctx.basicAuthCredentials().getPassword(),
                            userController.findByName(ctx.basicAuthCredentials().getUsername()).get(0).getPasword())){
                    System.out.println(userController.findByName(ctx.basicAuthCredentials().getUsername()).get(0).getName()+" ENETERED " );
                }
            }
            catch (Exception UNCORECT_LOGIN_OR_PASSWORD){
                //403 no
                //401 who are you?
                ctx.status(401);
            }
        });

        app.get("/user1/:id",ctx->{
            System.out.println("Somebody found out the "+ctx.pathParam("id")+" user");
            String userId=ctx.pathParam("id");
            ctx.result(userController.find(Integer.valueOf(userId)));

        });



    }
}
