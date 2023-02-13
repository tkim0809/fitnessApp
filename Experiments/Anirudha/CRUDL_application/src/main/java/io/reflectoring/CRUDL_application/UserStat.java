package io.reflectoring.CRUDL_application;

public class UserStat {
    private String name;
    private String email;
    private int weight;

    public UserStat(String name, String email, int weight) {
        this.name = name;
        this.email = email;
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getWeight() {
        return weight;
    }
}
