package idk;

/* Project: Lab 1 - Family Tree
 * Class: FamilyDatabase.java
 * Author: Nyiringango Rango
 * Date: September 27, 2025
 * This class stores a list of Person objects and processes the family
 * relationships based on the input file.
 */

import java.util.*;

public class FamilyDatabase {
    private ArrayList<Person> people;

    // ----------------------------------------------------------------
    // Constructs the database, reading people and parent info
    public FamilyDatabase(Scanner input) {
        this.people = new ArrayList<Person>();
        String name = input.nextLine();

        while (name.length() > 0) {
            this.people.add(new Person(name));
            name = input.nextLine();
        }

        processParents(input);
    }

    // ----------------------------------------------------------------
    // Returns a reference to the person with the given name
    // Returns null if not found
    public Person find(String name) {
        for (Person p : this.people) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // ----------------------------------------------------------------
    // Displays the names from the database to System.out
    public void print() {
        System.out.println("The database contains the following names:");
        for (Person p : this.people) {
            System.out.println("    " + p.getName());
        }
        System.out.println();
    }

    // ----------------------------------------------------------------
    // Reads birth records, updating mother/father/children info
    private void processParents(Scanner input) {
        while (input.hasNextLine()) {
            String personName = input.nextLine();
            String motherName = input.nextLine();
            String fatherName = input.nextLine();

            Person p = find(personName);

            if (!motherName.equals("unknown")) {
                Person mother = find(motherName);
                p.setMother(mother);
                mother.addChild(p);
            }

            if (!fatherName.equals("unknown")) {
                Person father = find(fatherName);
                p.setFather(father);
                father.addChild(p);
            }
        }
    }
}
