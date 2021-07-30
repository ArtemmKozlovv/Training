package by.kozlov.tasks.first.model.Salads;

import by.kozlov.tasks.first.model.Salad;
import by.kozlov.tasks.first.model.vegetables.*;

public class VegetarianOlivier extends Salad {
    public VegetarianOlivier(){
        this.setName("Vegetarian Olivier");
        this.addIngredient(new Potato(1000));
        this.addIngredient(new Cucumber(100));
        this.addIngredient(new Onion(100));
        this.addIngredient(new Pea(100));
        this.addIngredient(new Carrot(500));
        this.addIngredient(new SeaKale(100));
    }
}
