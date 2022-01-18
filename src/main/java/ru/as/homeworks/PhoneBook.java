package ru.as.homeworks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PhoneBook {

        private HashMap<String, String> phonebook = new HashMap<>();

        private ArrayList<String> getPhone(String name) {
            ArrayList<String> phones = new ArrayList<>();

            Set<Map.Entry<String, String>> phonebookMap = phonebook.entrySet();
            for (Map.Entry<String, String> entry : phonebookMap) {
                if (entry.getValue() == name)
                    phones.add(entry.getKey());
            }
            return phones;
        }

        void get(String name) {
            ArrayList<String> phones = getPhone(name);
            for (String phone : phones) {
                System.out.println("Номер " + name + ": " + phone);
            }
        }

        void put(String name, String phone) {
            phonebook.put(phone, name);
        }
}

