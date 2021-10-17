package by.kozlov.epam.myproject.entity;

public class  User extends Entity{

    private String login;
    private String password;
    private Role role;
    private String name;
    private String surname;
    private int count_of_tours;
    private int id_tour;

    public int getCount_of_tours() {
        return count_of_tours;
    }

    public void setCount_of_tours(int count_of_tours) {
        this.count_of_tours = count_of_tours;
    }

    public int getId_tour() {
        return id_tour;
    }

    public void setId_tour(int id_tour) {
        this.id_tour = id_tour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + getId() +
                ", login = " + login +
                ", password = " + password +
                ", role = " + role +
                ", name = " + name +
                ", surname = " + surname +
                '}';
    }
}
