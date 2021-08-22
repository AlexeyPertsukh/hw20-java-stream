package prog02;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static List<User> getUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User("Ярмоленко Андрей ", 8, Gender.MAN, "Украина", "andrey@mail.ru"));
        list.add(new User("Королёва Ирина ", 15, Gender.WOMAN, "Россия", "lapochka@rambler.ru"));
        list.add(new User("Дарио Сарна", 22, Gender.MAN, "Хорватия", "dario@rambler.ru"));
        list.add(new User("Мальцева Маргарита", 17, Gender.WOMAN, "Белоруссия", "amargo@rambler.ru"));
        list.add(new User("Вирджис Саманта", 23, Gender.WOMAN, "США", "sexySamanta@gmail.com"));

        list.add(new Employee("Абрикосов Владислав", 34, Gender.MAN, "Украина", "vlad@mail.ru", "IT", "сисадмин", 17750));
        list.add(new Employee("Караваев Александр", 27, Gender.MAN, "Узбекистан", "alex@toop.ru", "SEO", "менеджер", 13000));
        list.add(new Employee("Лазарева Анна", 19, Gender.WOMAN, "Украина", "anna@yandex.ru", "Бухгалтерия", "главбух", 14500));
        list.add(new Employee("Мораес Жуниор", 26, Gender.MAN, "Бразилия", "brasilio@gmail.com", "Маргетинг", "маркетолог", 15200));
        list.add(new Employee("Бубликова Света", 18, Gender.WOMAN, "Украина", "sweta@pochta.ru", "Бухгалтерия", "бухгалтер", 11800));

        return list;
    }
}
