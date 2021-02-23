package homework.day4.newsletter;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class FormUsers {

    private long id;
    private String name;
    private String email;

    public FormUsers(String name, String email)
            throws SQLException,
            SQLIntegrityConstraintViolationException,
            MysqlDataTruncation {
        if (isEmail(email)) {
            this.name = name;
            this.email = email;
            this.id = FormUsersDAO.create(this);
        }
        else {
            throw new SQLException("To nie jest email");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private boolean isEmail (String address) {
        if (address.matches(
                "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}")){
            return true;
        }
        else {
            System.out.println("Wprowadzona dana nie ma formatu adresu email");
            return false;
        }
    }
}
