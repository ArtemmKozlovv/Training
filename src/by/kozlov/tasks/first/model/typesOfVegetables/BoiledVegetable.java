package by.kozlov.tasks.first.model.typesOfVegetables;

import by.kozlov.tasks.first.model.Vegetable;

public class BoiledVegetable extends Vegetable {

    public BoiledVegetable(String name, int kCal, int weight){
        super(name, kCal, weight);
        setState("Boiled vegetable");
    }
    public BoiledVegetable(String name, int kCal){
        super(name, kCal);
        setState("Boiled vegetable");
    }

    @Override
    public double getWeight() {
        return (int)Math.round(super.getWeight() * 0.9); // Weight change during boiling -10%
    }

    @Override
    public int getKcal() {
        return (int)Math.round(super.getKcal() * 0.85); // kCal change during boiling -15%
    }

}
