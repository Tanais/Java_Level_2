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
        String[] wordArray = new String[] {"Tost", "Vikadin", "Mail", "Tost", "Test", "Vasya", "Vikadin" , "Vikadin"};
        String word;
        int count = 0;
        HashMap<String,Integer> keyValue = new HashMap<String,Integer>();
        for (int i = 0; i < wordArray.length; i++) {
            count = 0;
            word = wordArray[i];
            for (int j = 0; j < wordArray.length; j++) {
                if (wordArray[j].equals(word)){
                    count++;
                }
                keyValue.put(word, count);
            }
        }
        System.out.println(keyValue);
    }

    public static void taskTwo (){
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.putInfo("Ivanov", "+51794112");
        phoneBook.putInfo("Ivanov", "+5179411");
        phoneBook.putInfo("Ivanov", "+5194112");


    }

}


