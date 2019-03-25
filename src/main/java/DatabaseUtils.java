import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseUtils {
    private static JdbcConnectionSource source;

    DatabaseUtils() throws SQLException {
        source = new JdbcConnectionSource("jdbc:sqlite:user.db");
        TableUtils.createTableIfNotExists(source , User.class);
    }

    public ConnectionSource getSource() throws Exception {
        if (source == null) {
            throw new Exception("Invalid Connection Source");
        }
        return source;
    }
}