package doctorj.mealplan;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class Ingredient {
    private String name;
    private int amount;
    private String measurement;

    public int getAmount(){return this.amount;}
    public String getName(){return this.name;}
    public String getMeasurement(){return this.measurement;}
    public void setAmount(int amt){this.amount = amt;}
    public void setName(String nm){this.name = nm;}
    public void setMeasurement(String ms){this.measurement = ms;}
}
