package doctorj.mealplan;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class GroceryList {
    private ArrayList<Double> amounts = new ArrayList<>();
    private List<String> foodNames = new ArrayList<>();
    private int listLength;

    //Constructor
    public GroceryList()
    {
        this.listLength = 0;
        //this.amounts = new ArrayList<Integer>();
    }

    void addGL(String name, double num)
    {
        int i = 0;
        double temp = 0;
        if(this.foodNames.contains(name))
        {
            i = this.foodNames.indexOf(name);
            temp = this.amounts.get(i) + num;
            this.amounts.set(i, temp);
            return;
        }
        //add new entry
        this.foodNames.add(name);
        this.amounts.add(num);
        this.listLength++;
        return;
    }

    void addGL(List<Ingredient> ings)//TODO -> FIX - A (two parts) -> MealPlan
    {
        //send every object field into grocery list
        for(int i = 0; i < ings.size(); i++)
            addGL(ings.get(i).getName(), ings.get(i).getAmount());
    }

    void subGL()
    {
        //find food name or return
        //if food name exists subtract amount
        //reload GL
        return;
    }

    List<String> returnListNames()
    {
        return this.foodNames;
    }

    ArrayList<Double> returnAmounts()
    {
        return this.amounts;
    }

    public String getListString(){
        String GLstring = "";
        if(foodNames.isEmpty())
            GLstring = "Error in Grocery List";

        for (int i = 0; i < foodNames.size(); i++) {
            GLstring = GLstring + amounts.get(i) + " " + foodNames.get(i) + "\n";
        }

        return GLstring;
    }
}
