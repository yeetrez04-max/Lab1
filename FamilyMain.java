package idk;
/* Project: Lab 1 - Family Tree
 * Class: FamilyMain.java
 * Author: Nyiringango Rango
 * Date: September 27, 2025
 * This program reads a file of family relationships and allows
 * the user to look up a person. It prints that person’s maternal
 * and paternal lines, number of records, children, and siblings.
 */

import java.util.*;
import java.io.*;

public class FamilyMain {

    public static void main(String[] args) throws FileNotFoundException {
        giveIntro();
        Scanner console = new Scanner(System.in);
        Scanner input = new Scanner(new File("tudor.dat"));
        FamilyDatabase family = new FamilyDatabase(input);
        family.print();
        doMatches(family, console);
    }

    // ----------------------------------------------------------------
    // Prints an introduction to the program
    public static void giveIntro() {
        System.out.println("This program reads an input file with family");
        System.out.println("information and provides information about the");
        System.out.println("maternal line, paternal line, children, and siblings.");
        System.out.println();
    }

 // ----------------------------------------------------------------
 // Repeatedly prompts the user for a person, then prints information
 public static void doMatches(FamilyDatabase family, Scanner console) {
     System.out.print("next person (enter to quit)? ");
     String name = console.nextLine();

     while (name.length() > 0) {
         Person next = family.find(name);

         if (next == null) {
             System.out.println("No match found for: " + name);
         } else {
             System.out.println("===========================================");
             System.out.println(" Information for: " + next.getName());
             System.out.println("===========================================\n");

             int maternalCount = countMaternal(next);
             int paternalCount = countPaternal(next);

             System.out.println("-- Maternal Line (" + maternalCount + " record" 
                                + (maternalCount != 1 ? "s" : "") + ") --");
             showMaternal(next);
             System.out.println();

             System.out.println("-- Paternal Line (" + paternalCount + " record" 
                                + (paternalCount != 1 ? "s" : "") + ") --");
             showPaternal(next);
             System.out.println();

             showChildrenWithCount(next);
             System.out.println();

             showSiblings(next);
             System.out.println("===========================================\n");
         }

         System.out.print("next person (enter to quit)? ");
         name = console.nextLine();
     }
 }


    // ----------------------------------------------------------------
    // Shows maternal ancestors for given person
    public static void showMaternal(Person current) {
        System.out.println("Maternal line:");
        int level = 1;

        while (current != null) {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(current.getName());
            current = current.getMother();
            level++;
        }
    }

    // ----------------------------------------------------------------
    // Counts maternal records for a given person
    public static int countMaternal(Person current) {
        int count = 0;

        while (current != null) {
            count++;
            current = current.getMother();
        }
        return count;
    }

    // ----------------------------------------------------------------
    // Shows paternal ancestors for given person
    public static void showPaternal(Person current) {
        System.out.println("Paternal line:");
        int level = 1;

        while (current != null) {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(current.getName());
            current = current.getFather();
            level++;
        }
    }

    // ----------------------------------------------------------------
    // Counts paternal records for a given person
    public static int countPaternal(Person current) {
        int count = 0;

        while (current != null) {
            count++;
            current = current.getFather();
        }
        return count;
    }

    // ----------------------------------------------------------------
    // Shows children for given person, including count
    public static void showChildrenWithCount(Person current) {
        ArrayList<Person> kids = current.getChildren();
        System.out.println("Children (" + kids.size() + "):");

        if (kids.size() == 0) {
            System.out.println("    none");
        } else {
            for (Person kid : kids) {
                System.out.println("    " + kid.getName());
            }
        }
    }

    // ----------------------------------------------------------------
    // Shows siblings for given person (same mother and father)
    public static void showSiblings(Person current) {
        ArrayList<Person> siblings = new ArrayList<Person>();

        Person mother = current.getMother();
        Person father = current.getFather();

        if (mother != null && father != null) {
            // Use mother's children, check both parents match
            for (Person child : mother.getChildren()) {
                if (child != current && child.getFather() == father) {
                    siblings.add(child);
                }
            }
        }

        System.out.println("Siblings (" + siblings.size() + "):");

        if (siblings.size() == 0) {
            System.out.println("    none");
        } else {
            for (Person sib : siblings) {
                System.out.println("    " + sib.getName());
            }
        }
    }
}
