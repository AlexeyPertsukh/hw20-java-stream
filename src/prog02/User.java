package prog02;

public class User {

    public final static String FORMAT = "%-40s %-11s %-10d %-17s %-30s";

    private final String name;
    private final int age;
    private final Gender gender;
    private final String country;
    private final String mail;


    public User(String name, int age, Gender gender, String country, String mail) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }


    public String getCountry() {
        return country;
    }

    public String getMail() {
        return mail;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format(FORMAT, name, gender.getNameRus(), age, country, mail);
    }
}
