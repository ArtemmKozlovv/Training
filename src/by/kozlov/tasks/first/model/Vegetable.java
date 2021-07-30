package by.kozlov.tasks.first.model;

abstract public class Vegetable{

    private String name;
    private int kCal;
    private double weight;
    private String state;

    public Vegetable(String name){
        this.name = name;
    }

    public Vegetable(String name, int kCal, int weight) {
        if (kCal < 0){
            throw new IllegalArgumentException("Cannot create vegetable with " + kCal + " kCal.");
        }
        if (weight <= 0){
            throw new IllegalArgumentException("Cannot create " + weight + "g of" + name);
        }
        this.name = name;
        this.kCal = kCal;
        this.weight = weight;
    }

    public Vegetable(String name, int kCal) {
        if (kCal < 0){
            throw new IllegalArgumentException("Cannot create vegetable with " + kCal + " kCal.");
        }
        this.name = name;
        this.kCal = kCal;
    }

    public String toString(){
        return getWeight() + " gramms of " + name + " (" + this.state + "), " + this.getTotalKcal() + " kcal (100 gramms = " + this.getKcal() +" kcal)";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kCal;
    }

    public void setKcal(int kCal) {
        this.kCal = kCal;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTotalKcal(){
        return getKcal() * getWeight() / 100;
    }

}
