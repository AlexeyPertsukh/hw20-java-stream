package prog01;

import java.util.*;
import java.util.Map.Entry;

public class Main {
/*
Переписать последнюю классную работу императивным подходом(циклами)
//public class Main5 {
//    public static void main(String[] args) {
//        List<Human> list = DataBase.getHumans();
//
//        //сгруппировать в мапе списки людей по специальности
//        //отобразить по отдельности эти списки
//
//
//        final Map<Speciality, List<Human>> specialityListMap = list.stream()
//                .collect(Collectors.groupingBy(Human::getSpeciality));
//
//        specialityListMap.forEach(((speciality, humans) -> {
//            System.out.println(speciality);
//            humans.forEach(System.out::println);
//            System.out.println();
//        }));
//
//
//    }
 */

    public static void main(String[] args) {
        List<Human> list = DataBase.getHumans();
        printHumansList(list, "Работники всех специальностей");

        //map для хранения отдельных списков(list) сотрудников по ключу "специальность"
        Map<Speciality, List<Human>> map = getHumansMap(list);

        //распечатать map
        printHumansMap(map);

    }


    //делает мапу ключ/значение = специальность/список_сотрудников(list) из общего списка
    public static Map<Speciality, List<Human>> getHumansMap(List<Human> list) {
        Map<Speciality, List<Human>> map = new HashMap<>();

        Map<Speciality, Integer > mapSpeciality = new HashMap<>();  //map для хранения специальностей без дубликатов
        for (Human human : list) {
            mapSpeciality.put(human.getSpeciality(), 0);
        }

        //заполнение map списками(list) работников, каждый список- отдельная специальность
        for (Speciality key : mapSpeciality.keySet()) {
            List<Human> listTmp = getListBySpeciality(list, key);
            map.put(key, listTmp);
        }

        return map;
    }


    //возвращает список с рабочими конкретной специальности из общего списка(где работники всех специальностей лежат скопом)
    public static List<Human> getListBySpeciality(List<Human> list, Speciality keySpeciality ) {
        List<Human> listTmp = new ArrayList<>();
        for (Human human : list) {
            String specialityName = human.getSpeciality().name();
            String keyName = keySpeciality.name();
            if(keyName.equalsIgnoreCase(specialityName)) {
                listTmp.add(human);
            }
        }
        //сортируем список по имени работника
        listTmp.sort((human1, human2) -> human1.getName().compareToIgnoreCase(human2.getName()));
        return listTmp;
    }


    //печать содержимого map в порядке возрастания специальностей
    //Map не позволяет сортировать значения, поэтому нужно извращаться через Map -> ArrayList
    //вместо Map можно было бы (?) использовать SortedMap - его можно сортировать
    public static void printHumansMap(Map<Speciality, List<Human>> map) {

        ArrayList<Entry<Speciality, List<Human>>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> a.getKey().name().compareToIgnoreCase(b.getKey().name()));

        for (Entry<Speciality, List<Human>> entry : list) {
                printHumansList(entry.getValue());
        }
    }

    public static void printHumansList(List<Human> list, String message) {
        if(list.isEmpty()) {
            return;
        }

        int i = 1;
        for (Human human : list) {
            if(i == 1) {
                System.out.println(message);
                System.out.println(".........");
            }
            System.out.println(i + ".  " + human);
            i++;
        }
        System.out.println();
    }

    public static void printHumansList(List<Human> list) {
        if(list.isEmpty()) {
            return;
        }

        String message = list.get(0).getSpeciality().name();
        printHumansList(list, message);
    }


}
