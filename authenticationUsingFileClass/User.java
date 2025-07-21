package authenticationUsingFileClass;

import authenticationUsingFileClass.enums.UserType;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;
    private UserType userType;

    public User(String name, String password, UserType userType) {
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return name + "," + password + "," + userType.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return name.equals(user.name) &&
                password.equals(user.password) &&
                userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, userType);
    }
}
