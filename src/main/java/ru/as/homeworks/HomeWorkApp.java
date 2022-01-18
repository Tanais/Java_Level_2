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
        String[] wordArray = new String[] {"Мурзик", "Барсик", "Батон", "Васька", "Батон", "Мурзик", "Ночь" , "Ночь"};
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

        phoneBook.put("Иванов", "+51794112");
        phoneBook.put("Васильев", "+51511414");
        phoneBook.put("Иванов", "+5179411");
        phoneBook.put("Петрова", "+5194112");
        phoneBook.put("Петров", "+5194112");

        phoneBook.get("Иванов");
        phoneBook.get("Петров");
    }

}


