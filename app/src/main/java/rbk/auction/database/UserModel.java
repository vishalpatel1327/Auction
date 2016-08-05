package rbk.auction.database;

/**
 * Created by DELL-PC on 04-08-2016.
 */
public class UserModel {

    int id;
    String email;
    String Password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
