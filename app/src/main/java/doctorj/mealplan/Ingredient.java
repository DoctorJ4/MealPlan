package doctorj.mealplan;

/**
 * Created by Jesse on 3/30/2015.
 */
public class Ingredient {
    private String name;
    private double amount;
    private String measurement;

    Ingredient(){}

    Ingredient(String nm, double am, String mm)
    {
        this.name = nm;
        this.amount = am;
        this.measurement = mm;
    }

    public double getAmount(){return this.amount;}
    public String getName(){return this.name;}
    public String getMeasurement(){return this.measurement;}
    public void setAmount(double amt){this.amount = amt;}
    public void setName(String nm){this.name = nm;}
    public void setMeasurement(String ms){this.measurement = ms;}
}
