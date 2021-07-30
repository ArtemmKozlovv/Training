package by.kozlov.tasks.first.model.typesOfVegetables;

import by.kozlov.tasks.first.model.Vegetable;

public class FreshVegetable extends Vegetable {

    public FreshVegetable(String name, int kCal, int weight){
        super(name, kCal, weight);
        setState("Fresh vegetable");
    }
    public FreshVegetable(String name, int kCal){
        super(name, kCal);
        setState("Fresh vegetable");
    }

    // weight and calorie don't change
}
