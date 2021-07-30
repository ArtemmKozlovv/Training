package by.kozlov.tasks.first.model.vegetables;

import by.kozlov.tasks.first.model.typesOfVegetables.BoiledVegetable;

public class Carrot extends BoiledVegetable {

    public Carrot(int weight) {
        super("Carrot", 41, weight);
    }

    public Carrot() {
        super("Carrot", 41);
    }

}
