package by.kozlov.epam.myproject.entity;

import java.io.Serializable;
import java.sql.Date;

public class Tour extends Entity {
    private String name;
    private String country;
    private Long cost;
    private String aboutTour;
    private Date dateSql;



    private Long date() { //миллисекунды, ранного времени
        return System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getAboutTour() {
        return aboutTour;
    }

    public void setAboutTour(String aboutTour) {
        this.aboutTour = aboutTour;
    }

    public Date getDateSql() {
        return dateSql;
    }

    public void setDateSql(Date dateSql) {
        this.dateSql = dateSql;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCostEdit(){
        String str = (long)cost/100+"руб. "+(long)cost%100+"коп.";
        return str;
    }

    public int getDaysLeft() {
        long millisecondsLeft = dateSql.getTime() - date();
        int daysLeft = (int) (millisecondsLeft / (24 * 60 * 60 * 1000));
        return daysLeft;
    }

    private boolean getHotTour(){
        if (getDaysLeft() <= 7){
            return true;
        }
        return false;
    }

    // >=once=10% >=3times=15% >=5times=20%
    private double discounts(User user){
        int count_of_tours = user.getCount_of_tours();
        if (count_of_tours >= 1){
            if (count_of_tours >= 3){
                if (count_of_tours >= 5){
                    return 0.2;
                }
                return 0.15;
            }
            return 0.1;
        }
        return 0;
    }

    public long getCostWithDiscounts(){
        double discountForHotTour = 0;
        if (getHotTour()){
            discountForHotTour = 0.2;
        }
        long temp = cost - (long)(cost*discountForHotTour);
        return temp;
    }

    public String getCostWithDiscountsEdit(){
        double discountForHotTour = 0;
        if (getHotTour()){
            discountForHotTour = 0.2;
        }
        long temp = cost - (long)(cost*discountForHotTour);
        String str = (long)temp/100+"руб. "+(long)temp%100+"коп.";
        return str;
    }
}
