package doctorj.mealplan;

/**
 * Created by Jesse on 12/4/2015.
 */
public class DatabaseIngredientCreateObject {
    public String name;
    public double amount;
    public String measurement;
    DatabaseIngredientCreateObject(String name, double amount, String measurement){
        this.name = name;
        this.amount = amount;
        this.measurement = measurement;
    }
}
