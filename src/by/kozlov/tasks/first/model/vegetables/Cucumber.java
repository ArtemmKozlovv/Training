package by.kozlov.tasks.first.model.vegetables;

import by.kozlov.tasks.first.model.typesOfVegetables.FreshVegetable;

public class Cucumber extends FreshVegetable {

    public Cucumber(int weight) {
        super("Cucumber", 16, weight);
    }

    public Cucumber() {
        super("Cucumber", 16);
    }

}
