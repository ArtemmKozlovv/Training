package by.kozlov.tasks.first.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Salad {
    private String name;
    private List<Vegetable> ingredients = new ArrayList();

    public Salad(){
        name = "Unknown";
    }

    public Salad(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getIngredients(){
        return ingredients;
    }

    public boolean addIngredient(Vegetable vegetable){
        return this.ingredients.add(vegetable);
    }

    public void showRecipe() {
        if (this.ingredients.isEmpty()) {
            System.out.println("You haven't added any ingredients yet!");
        } else {
            System.out.println("The salad " + this.name + " contains:");
            Iterator var2 = this.ingredients.iterator();

            while(var2.hasNext()) {
                Vegetable vegetable = (Vegetable)var2.next();
                System.out.println(vegetable.toString());
            }

            System.out.println("----------------------------");
            System.out.println("Total energy: " + this.countCalories() + "kcal");
        }
    }

    public void infoAboutTheSalad(){
        System.out.println("The name of the salad is " + name + ".\nA good choice for vegetarians who love Olivier salad's.\n");
    }

    public int countCalories(){
        int kCal = 0;
        Vegetable vegetable;
        for (Iterator var4 = this.ingredients.iterator(); var4.hasNext(); kCal += vegetable.getTotalKcal()){
            vegetable = (Vegetable)var4.next();
        }
        return kCal;
    }

    public void showIngredientsByCalories(double lowerCalories, double upperCalories) {
        System.out.println("Ingredients for calories [" + lowerCalories + ", " + upperCalories + "]");
        Iterator var8 = this.ingredients.iterator();

        while(var8.hasNext()) {
            Vegetable vegetable = (Vegetable)var8.next();
            double calories = vegetable.getKcal();
            if (calories >= lowerCalories && calories <= upperCalories) {
                System.out.println(vegetable.getName() + ", " + vegetable.getKcal() + "kcal per 100g");
            }
        }

    }

    public void sortIngredientByCalories(){
        Collections.sort(this.ingredients, new CaloriesComparator());
    }

    public void sortIngredientByWeight(){
        Collections.sort(this.ingredients, new WeightComparator());
    }
}
