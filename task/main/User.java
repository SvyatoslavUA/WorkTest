package main;

public class User {
    private String name;
    private int age;

    private User(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public static User createUser(final String name, final int age) {
        return new User(name, age);
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}




