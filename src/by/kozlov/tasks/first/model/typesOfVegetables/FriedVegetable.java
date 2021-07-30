package by.kozlov.tasks.first.model.typesOfVegetables;


import by.kozlov.tasks.first.model.Vegetable;

public class FriedVegetable extends Vegetable {

    public FriedVegetable(String name, int kCal, int weight){
        super(name, kCal, weight);
        setState("Fried vegetable");
    }
    public FriedVegetable(String name, int kCal){
        super(name, kCal);
        setState("Fried vegetable");
    }

    @Override
    public double getWeight() {
        return (int)Math.round(super.getWeight() * 0.69); // Weight change during frying -31%
    }

    @Override
    public int getKcal() {
        return (int)Math.round(super.getKcal() * 2.1); // kCal change during frying +110%
    }

}
