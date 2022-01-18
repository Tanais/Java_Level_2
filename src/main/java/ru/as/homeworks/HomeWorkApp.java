package ru.as.homeworks;


import java.util.HashMap;

public class HomeWorkApp
{
    public static void main( String[] args )
    {
        taskOne();
        taskTwo();
    }

    public static void taskOne (){
        System.out.println("Задача номер 1");
        String[] wordArray = new String[] {"Tost", "Vikadin", "Mail", "Tost", "Test", "Vasya", "Vikadin" , "Vikadin"};
        String word;
        int count;
        HashMap<String,Integer> mapWords = new HashMap<>();
        for (String s : wordArray) {
            count = 0;
            word = s;
            for (String value : wordArray) {
                if (value.equals(word)) {
                    count++;
                }
                mapWords.put(word, count);
            }
        }

        System.out.printf("Все уникальные слова: %s \n", mapWords.keySet());
        for (String item: mapWords.keySet())
        {

            System.out.printf("Слово %s встречается %s раз \n", item, mapWords.get(item));
        }
    }

    public static void taskTwo (){
        System.out.println("Задача номер 2");
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.put("Ivanov", "+51794112");
        phoneBook.put("Vasilev", "+51511414");
        phoneBook.put("Ivanov", "+5179411");
        phoneBook.put("Petrov", "+5194112");
        phoneBook.put("Ivanov", "+5194112");

        phoneBook.get("Ivanov");

    }

}


