package pl.soa.parkometer.entities;

import javax.persistence.*;

@Entity
public class Worker {
    private int workerId;
    private String login;
    private String passwd;
    private String name;
    private String surname;
    private Role role;
    private Zone zone;

    @Id
    @Column(name = "worker_id", nullable = false)
    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "passwd", nullable = false, length = 30)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 30)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (workerId != worker.workerId) return false;
        if (login != null ? !login.equals(worker.login) : worker.login != null) return false;
        if (passwd != null ? !passwd.equals(worker.passwd) : worker.passwd != null) return false;
        if (name != null ? !name.equals(worker.name) : worker.name != null) return false;
        if (surname != null ? !surname.equals(worker.surname) : worker.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workerId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name = "zone_id", referencedColumnName = "zone_id")
    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
