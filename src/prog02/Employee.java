package prog02;

public class Employee extends User {

    private final static String FORMAT = "%-20s %-20s %7.1f";

    private final String department;
    private final String rank;
    private final double  salary;

    public Employee(String name, int age, Gender gender, String country, String mail, String department, String rank, double salary) {
        super(name, age, gender, country, mail);
        this.department = department;
        this.rank = rank;
        this.salary = salary;
    }

    @Override
    public String toString() {
        String format = "%s " + FORMAT;
        return String.format(format, super.toString(), department, rank, salary);
    }
}
