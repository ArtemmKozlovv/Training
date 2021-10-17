package by.kozlov.epam.myproject.entity;

public enum Role {
    ADMIN("Администратор"),
    TRAVEL_AGENT("Агент"),
    USER("Пользователь");

    private String name;

    private Role(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
