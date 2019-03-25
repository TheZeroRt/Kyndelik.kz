import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.DatabaseTypeUtils;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserController {
    private Dao <User,Long> dao;
            Logger logger;

    public UserController() throws Exception {
        DatabaseUtils databaseUtils = new DatabaseUtils();
        dao = DaoManager.createDao(databaseUtils.getSource(), User.class);
    }

    public void create(Context context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(User.class, new UserDeserializer());

        mapper.registerModule(module);
        User readValue = mapper.readValue(context.body(), User.class);

        System.out.println(context.body());
        try {
            dao.create(readValue);
            context.status(201);
        }
        catch (SQLException e){
        }
    }




    public String find (Integer id) throws SQLException, JsonProcessingException {
        System.out.println(dao.queryForId((long)id));

        User user = new User(dao.queryForId((long) id));

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(User.class, new UserSerializer());

        mapper.registerModule(module);


        return mapper.writeValueAsString(user);
    }

    public List<User> findByName (String name) throws SQLException {
        QueryBuilder<User, Long> queryBuilder =
                dao.queryBuilder();

        queryBuilder.where().eq("name" ,name);
        PreparedQuery<User> preparedQuery = queryBuilder.prepare();
        List<User> userList = dao.query(preparedQuery);
        return userList;
    }


}
