package ru.example.edu;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Person> people = new MyLinkedList<>();
        people.add(new Person("Иван1", 1));
        people.add(new Person("Иван2", 12));
        people.add(new Person("Иван3", 9));
        people.add(new Person("Иван4", 2));
        people.add(new Person("Иван5", 5));

        for (Person person : people) {
            System.out.println(person);
        }

    }
}