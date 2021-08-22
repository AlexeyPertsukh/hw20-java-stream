package prog02;

import java.util.*;
import java.util.stream.Collectors;

public class Prog {
    private final static String[] MAILS = {"gmail.com", "mail.ru"};
    public final static int WOMAN_AGE_FROM = 18;
    public final static int WOMAN_AGE_TO = 30;

    private final Scanner sc;
    private List<User> list;
    private String command;
    private boolean endProg;

    public Prog() {
        list = DataBase.getUsers();
        sc = new Scanner(System.in);
    }

    public void go() {
        printPeoples();
        do {
            printCommands();
            inputCommand();
            processedCommand();
            My.inputCharToContinue(sc);
        } while(!endProg);
    }

    public void printAverageAge() {
        System.out.println("Средний возраст: " + averageAge());
        System.out.println("Средний возраст несовершеннолетних: " + averageAge(18));
        System.out.println();
    }

    public void printPeoples() {
        printTableHeader("N   ");
        list = list.stream().sorted(new ComparatorTypeObject().thenComparing(User::getName))
                            .collect(Collectors.toList());
//        list.forEach(System.out::println);    // можно так, но не будут распечатаны номера записей

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%-3s %s \n", i + 1, list.get(i));
        }

        System.out.println();
    }

    //средний возраст
    public int averageAge(int max) {
        double aver = list.stream().filter((u) -> u.getAge() < max)
                            .collect(Collectors.averagingInt(User::getAge));
        return (int) aver;
    }

    public int averageAge() {
        return averageAge(Integer.MAX_VALUE);
    }

    private void printTableHeader(String str) {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%s%-40s %-11s %-10s %-17s %-30s %-20s %-20s %s \n", str, "ФИО", "ПОЛ","ВОЗРАСТ","СТРАНА","EMAIL","ОТДЕЛ","ДОЛЖНОСТЬ","ОКЛАД, ГРН");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    private void printTableHeader() {
        printTableHeader("");
    }

    public void printPeoplesByMail() {
        System.out.printf("Люди с почтой %s \n ", getMailForPeoplesSearch() );
        printTableHeader();

        list.stream().filter((u) -> {
            for (String mail : MAILS) {
                if(u.getMail().contains(mail)) {
                    return true;
                }
            }
            return false;
        } )
        .sorted(new ComparatorTypeObject().thenComparing(User::getName))
        .forEach(System.out::println);

        System.out.println();
    }

    public void printUkraineWomansAgeFrom18to30() {
        System.out.printf("Сотрудницы, проживающие в Украине, в возрасте от %d до %d лет включительно \n", WOMAN_AGE_FROM, WOMAN_AGE_TO );
        printTableHeader();
        list.stream().filter((u) -> (u.getAge() >= WOMAN_AGE_FROM && u.getAge() <= WOMAN_AGE_TO) && u.getCountry().equalsIgnoreCase("Украина"))
                .sorted(Comparator.comparing(User::getName))
//              .sorted((a,b) -> a.getName().compareToIgnoreCase(b.getName())) //можно и так
                .forEach(System.out::println);


        System.out.println();
    }

    private void printCommands() {
        //список команд
        for (Command cmd : Command.values()) {
            String addition = "";
            if(cmd == Command.PRINT_PEOPLES_BY_MAIL) {
                addition = " " + getMailForPeoplesSearch();
            }
            System.out.printf("%s - %s%s \n",cmd.getKey(), cmd.getNameRus(), addition);
        }
        System.out.println("-----------------------");
        //ввод команд

    }

    private String getMailForPeoplesSearch() {
        String mails = "";

        for (int i = 0; i < MAILS.length; i++) {
            mails += MAILS[i];
            if(i < MAILS.length - 1) {
                mails += ", ";
            }
        }
        return mails;
    }

    public void inputCommand() {
        System.out.print("Введите команду: ");
        command = sc.next();
        System.out.println();
    }

    public void processedCommand() {
        if(command.equalsIgnoreCase(Command.END.getKey())) {
            endProg = true;
            return;
        }

        if(command.equalsIgnoreCase(Command.PRINT_PEOPLE.getKey())) {
            printPeoples();
            return;
        }

        if(command.equalsIgnoreCase(Command.PRINT_AVERAGE_AGE.getKey())) {
            printAverageAge();
            return;
        }

        if(command.equalsIgnoreCase(Command.PRINT_PEOPLES_BY_MAIL.getKey())) {
            printPeoplesByMail();
            return;
        }

        if(command.equalsIgnoreCase(Command.PRINT_WANT_WOMAN.getKey())) {
            printUkraineWomansAgeFrom18to30();
            return;
        }

        if(command.equalsIgnoreCase(Command.ADD.getKey())) {
            add();
            return;
        }

        if(command.equalsIgnoreCase(Command.DEL.getKey())) {
            printPeoples();
            del();
            System.out.println();
            return;
        }

        //Неизвестная команда
        System.out.println("Неизвестная команда");
    }

    //добавить человека или сотрудника
    private void add() {
        String name;
        int age;
        char chGender;
        Gender gender;
        String country;
        String mail;

        String department;
        String rank;
        double  salary;

        System.out.print("ФИО: ");
        name = sc.next();

        chGender = My.nextCharLowerCase(sc, "Пол (M - мужчина, F - женщина): ", 'M', 'м','F');  // русская и английская М

        gender = (chGender == 'm' || chGender == 'м') ? Gender.MAN : Gender.WOMAN;

        age = My.nextInt(sc, "Возраст (1-130 лет): ", 1, 130);

        System.out.print("Страна: ");
        country = sc.next();

        System.out.print("E-MAIL: ");
        mail = sc.next();
        char chStateEmploye =  My.nextCharLowerCase(sc, "Это сотрудник? (Y - да, N - нет): ",'Y','N');
        //сотрудник?
        if(chStateEmploye == 'y') {
            System.out.print("Отдел: ");
            department = sc.next();

            System.out.print("Должность: ");
            rank = sc.next();

            salary = My.nextDouble(sc,"Введите оклад: ",1, Double.MAX_VALUE);

            list.add( new Employee(name, age, gender, country, mail, department, rank, salary));

        }
        else {
            list.add( new User(name, age, gender, country, mail));
        }
        System.out.println("Данные введены");
        System.out.println();

    }

    private void del() {
        int num = My.nextInt(sc, "Введите номер записи для удаления: ");
        try {
            User delUser = list.remove(num - 1);
            System.out.printf("Запись #%d %s успешно удалена \n", num, delUser.getName());
            System.out.printf("Осталось записей: %d \n", list.size());
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.println("Вы ввели неправильный номер записи для удаления");
        }

    }

}
