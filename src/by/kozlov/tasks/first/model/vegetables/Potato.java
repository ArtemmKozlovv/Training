package by.kozlov.tasks.first.model.vegetables;

import by.kozlov.tasks.first.model.typesOfVegetables.BoiledVegetable;

public class Potato extends BoiledVegetable {

    public Potato(int weight) {
        super("Potato", 76, weight);
    }

    public Potato() {
        super("Potato", 76);
    }

}
