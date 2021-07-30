package by.kozlov.tasks.first.model;

import java.util.Comparator;

public class WeightComparator implements Comparator<Vegetable>{
    public WeightComparator(){

    }


    @Override
    public int compare(Vegetable o1, Vegetable o2) {
        return (int)(o1.getWeight() - o2.getWeight());
    }
}
