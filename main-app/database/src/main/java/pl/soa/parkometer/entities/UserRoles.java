package pl.soa.parkometer.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_roles", schema = "public", catalog = "admin")
public class UserRoles implements Serializable {
    private int roleId;
    private String login;
    private String userrole;

    @Id
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "login", nullable = true, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "userrole", nullable = true, length = 30)
    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles that = (UserRoles) o;
        return roleId == that.roleId &&
                Objects.equals(login, that.login) &&
                Objects.equals(userrole, that.userrole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, login, userrole);
    }
}
