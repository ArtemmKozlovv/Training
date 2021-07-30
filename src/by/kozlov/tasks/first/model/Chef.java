package by.kozlov.tasks.first.model;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Chef {
    private String name; // Chef's name
    private Salad salad; // A salad that he can cooks

    public Chef(){
        name = "Michael";
        salad = new Salad();
    }

    public Chef(String name){
        this.name = name;
        salad = new Salad();
    }

    public Chef(String name, Salad salad){
        this.name = name;
        this.salad = salad;
    }

    public void showIngredientsForCalories(Scanner read) {
        System.out.println("Enter the lower limit:");

        double lowerCalories;
        try {
            lowerCalories = read.nextDouble();
        } catch (InputMismatchException var8) {
            System.out.println("Wrong input!");
            read.next();
            return;
        }

        System.out.println("Enter the upper limit:");

        double upperCalories;
        try {
            upperCalories = read.nextDouble();
        } catch (InputMismatchException var7) {
            System.out.println("Wrong input!");
            read.next();
            return;
        }

        this.salad.showIngredientsByCalories(lowerCalories, upperCalories);
    }

    public void menu(){
        Scanner read = new Scanner(System.in);
        
        while (true){
            System.out.println("Choose one of the options : ");
            System.out.println("\t1) Information about the cooked salad.");
            System.out.println("\t2) Calculation of the caloric content of the salad.");
            System.out.println("\t3) Sort the vegetables for the salad by weight.");
            System.out.println("\t4) Sort the vegetables for the salad by calories.");
            System.out.println("\t5) Find the vegetables in the salad that correspond to a given range of calories.");
            System.out.println("\t6) Exit.");

            int key = read.nextInt();
            switch (key){
                case 1:
                    this.salad.infoAboutTheSalad();
                    break;
                case 2:
                    this.salad.showRecipe();
                    break;
                case 3:
                    this.salad.sortIngredientByWeight();
                    break;
                case 4:
                    this.salad.sortIngredientByCalories();
                    break;
                case 5:
                    showIngredientsForCalories(read);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong option!");
            }
        }
    }
}
