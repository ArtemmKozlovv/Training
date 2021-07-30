package by.kozlov.tasks.first.console;

import by.kozlov.tasks.first.model.Chef;
import by.kozlov.tasks.first.model.Salads.VegetarianOlivier;

public class Runner {

    public static void main(String[] args) {
        Chef chef = new Chef("Artsiom Kazlou", new VegetarianOlivier()); // cooking salad
        chef.menu();
    }
}
