package by.kozlov.tasks.first.model.typesOfVegetables;

import by.kozlov.tasks.first.model.Vegetable;

public class PickledVegetable extends Vegetable {

    public PickledVegetable(String name, int kCal, int weight){
        super(name, kCal, weight);
        setState("Pickled vegetable");
    }
    public PickledVegetable(String name, int kCal){
        super(name, kCal);
        setState("Pickled vegetable");
    }

    @Override
    public double getWeight() {
        return (int)Math.round(super.getWeight() * 1.1); // Weight change during pickling +10%
    }

    @Override
    public int getKcal() {
        return (int)Math.round(super.getKcal() * 0.67); // kCal change during pickling -33%
    }

}
