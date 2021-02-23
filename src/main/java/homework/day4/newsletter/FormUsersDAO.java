package homework.day4.newsletter;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class FormUsersDAO {

    //insert
    private static final String CREATE_USER_QUERY =
            "INSERT INTO `formUsers`(`name`, `email`) VALUES (?, ?)";

    //UWAGA DO SPRAWDZAJACEGO
    //Czy to jest tak jak miało być w warsztacie II? Jeszcze go nie poprawiłam,
    //ale jak tutaj trafiło się analogiczne zadanko to postanowiłam to zrobić chyba poprawnie
    //Zaimplementowałam wiele rzeczy zanim doszłam do tego momentu, więc chcę wiedzieć czy jest okej ;)
    public static long create (FormUsers u)
            throws SQLException,
            SQLIntegrityConstraintViolationException,
            MysqlDataTruncation {
        try (Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                System.out.println(rs.getLong(1));
                return rs.getLong(1);
            }
            throw new SQLException("Nie udało się uzyskać klucza");
        }
    }

}
