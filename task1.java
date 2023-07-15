/*
Реализуйте структуру телефонной книги с помощью HashMap.
Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с разными телефонами,
их необходимо считать, как одного человека с разными телефонами. Вывод должен быть отсортирован по убыванию
числа телефонов.
Пример ввода:
Иванов 234234
Иванов 32523
Иванов 5687
Иванов: 234234, 32523, 5687

Варианты Map:
Map<String, ArrayList>
Map<String, String>

Пример меню:
1. Добавить контакт
2. Вывести всех
3. Выход
*/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class task1 {
    
    public static void addContact(Map<String,List<String>> pb) {
        Scanner iScanner = new Scanner(System.in, "cp866");
        System.out.println("Введите фамилию");
        String lastName = iScanner.nextLine();
        System.out.println("Введите телефон");
        String phone = iScanner.nextLine();
        if (!pb.containsKey(lastName)) {
            List<String> arr = new ArrayList<String>();
            arr.add(phone);
            pb.put(lastName,arr);
            System.out.printf("Добавлен контакт %s, телефон %s",lastName,phone);
            System.out.println();
        }
        else {
            List<String> arr = pb.get(lastName);
            boolean isExists = false;
            for (var item: arr) {
                if (item.equals(phone)) {
                    isExists = true;
                }
            }
            if (!isExists) {
                arr.add(phone);
                pb.put(lastName, arr);
                System.out.printf("К контакту %s добавлен номер телефона %s",lastName,phone);
                System.out.println();
            } else {
                System.out.printf("У контакта %s уже есть номер телефона %s",lastName,phone);
                System.out.println();
            } 
            
        }
    }

    public static void printContacts(Map<String,List<String>> pb) {
        for (var item : pb.entrySet()) {
            System.out.printf("%s: ",item.getKey());
            for (var phone: item.getValue()) {
                System.out.printf("%s ",phone.toString());
            }
            System.out.println();
        }
        System.out.println("  ");
    }

    public static void printSortedContacts(Map<String, List<String>> pb) {
        String[] arr = new String[pb.size()];
        String temp;
        int i=0;
        for (var item : pb.entrySet()) {
            arr[i] = String.format("%s: ", item.getKey().trim());
            for (var phone: item.getValue()) {
                arr[i] = arr[i]+String.format(" %s",phone.toString());
            }
            i++;
        }
        int len, lenJ;
        for (i = 0; i < arr.length; i++) {
            len = arr[i].split(":")[1].split(" ").length;
            for (int j = i + 1; j < arr.length; j++) {
                lenJ = arr[j].split(":")[1].split(" ").length;
                if (lenJ > len) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
            System.out.println(arr[i]);
        }
        System.out.println(" ");
    }


    public static void main(String[] args) {
        Map<String, List<String>> phoneBook = new HashMap<String, List<String>>();
        Scanner iScanner = new Scanner(System.in, "cp866");
        boolean flagEnd = false;
        while (!flagEnd) {
            System.out.println("[1] Добавить контакт");
            System.out.println("[2] Вывести всех");
            System.out.println("[3] Вывести сортированный");
            System.out.println("[4] Выход");
            String menu = iScanner.nextLine();
            switch (menu) {
                case "1":
                    addContact(phoneBook);
                    break;
                case "2":
                    printContacts(phoneBook);
                    break;
                case "3":
                    printSortedContacts(phoneBook);
                    break;
                case "4":
                    flagEnd = true;
            }
        }
        iScanner.close();
    }

}
    


