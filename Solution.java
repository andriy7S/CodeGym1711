package com.codegym.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
CRUD 2

*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<>();
    private static SimpleDateFormat mm_dd_yyyy = new SimpleDateFormat("MM dd yyyy");
    private static SimpleDateFormat mmm_dd_yyyy = new SimpleDateFormat("MMM dd yyyy");

    static {
        allPeople.add(Person.createMale("Donald Chump", new Date()));  // id=0
        allPeople.add(Person.createMale("Larry Gates", new Date()));  // id=1
    }

    public static void main(String[] args) throws ParseException {

        switch (args[0]) {

            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i = i + 3) {
                        String name = args[i];
                        String sex = args[i + 1];
                        String bd = args[i + 2];
                        final Date parseBirthDay = mm_dd_yyyy.parse(bd);

                        if (sex.equals("m")) {
                            final Person male = Person.createMale(name, parseBirthDay);
                            allPeople.add(male);
                            System.out.println(allPeople.size() - 1);
                        } else {
                            final Person female = Person.createFemale(name, parseBirthDay);
                            allPeople.add(female);
                            System.out.println(allPeople.size() - 1);
                        }
                    }
                }
                break;

            case "-u":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i = i + 4) {
                        final Person person = allPeople.get(Integer.parseInt(args[i]));
                        String name = args[i + 1];
                        String sex = args[i + 2];
                        String bd = args[i + 3];

                        final Date parseBirthDay = mm_dd_yyyy.parse(bd);

                        person.setName(name);
                        if (sex.equals("m")) {
                            person.setSex(Sex.MALE);
                        } else {
                            person.setSex(Sex.FEMALE);
                        }
                        person.setBirthDate(parseBirthDay);
                    }
                }
                break;

            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        final Person person = allPeople.get(Integer.parseInt(args[i]));
                        person.setName(null);
                        person.setBirthDate(null);
                        person.setSex(null);
                    }
                }
                break;

            case "-i":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        String id = args[i];
                        final Person person = allPeople.get(Integer.parseInt(id));
                        final String sex = person.getSex().equals(Sex.MALE) ? "m" : "f";
                        System.out.println(person.getName() + " " + sex + " " + mmm_dd_yyyy.format(person.getBirthDate()));
                    }
                }
                break;

        }
    }
}
