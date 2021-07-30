package by.kozlov.tasks.first.model;

import java.util.Comparator;

public class CaloriesComparator implements Comparator<Vegetable> {

    public CaloriesComparator(){

    }

    @Override
    public int compare(Vegetable o1, Vegetable o2) {
        return (o1.getKcal() - o2.getKcal());
    }
}
