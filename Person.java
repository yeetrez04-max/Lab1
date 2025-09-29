package idk;

/* Project: Lab 1 - Family Tree
 * Class: Person.java
 * Author: Nyiringango Rango
 * Date: September 27, 2025
 * This class records the name and immediate blood relations of an
 * individual (mother, father, children).
 */

import java.util.*;

public class Person {
    private String name;
    private Person mother;
    private Person father;
    private ArrayList<Person> children;

    // ----------------------------------------------------------------
    // Constructs a person object with no family links
    public Person(String name) {
        this.name = name;
        this.children = new ArrayList<Person>();
    }

    // ----------------------------------------------------------------
    // Returns this person's name
    public String getName() {
        return this.name;
    }

    // ----------------------------------------------------------------
    // Returns this person's mother (null if not known)
    public Person getMother() {
        return this.mother;
    }

    // ----------------------------------------------------------------
    // Returns this person's father (null if not known)
    public Person getFather() {
        return this.father;
    }

    // ----------------------------------------------------------------
    // Returns this person's list of children
    public ArrayList<Person> getChildren() {
        return this.children;
    }

    // ----------------------------------------------------------------
    // Records the mother of this person
    public void setMother(Person mother) {
        this.mother = mother;
    }

    // ----------------------------------------------------------------
    // Records the father of this person
    public void setFather(Person father) {
        this.father = father;
    }

    // ----------------------------------------------------------------
    // Records another child of this person
    public void addChild(Person child) {
        this.children.add(child);
    }
}
