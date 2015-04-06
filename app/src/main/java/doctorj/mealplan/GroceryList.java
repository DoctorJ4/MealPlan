package doctorj.mealplan;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class GroceryList {
    private ArrayList<Integer> amounts;
    private List<String> foodNames;
    private int listLength;

    //Constructor
    public GroceryList()
    {
        this.listLength = 0;
        //this.amounts = new ArrayList<Integer>();
    }

    void addGL(String name, int num)
    {
        int i = 0;
        int temp = 0;
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

    /*void addGL(Ingredient []obj)//TODO -> FIX - A (two parts) -> MealPlan
    {
        //send every object field into grocery list
        //for(int i = 0; i < obj.length; i++)
            addGL(obj[i].getName, obj[i].getAmount);
    }*/

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

    ArrayList<Integer> returnAmounts()
    {
        return this.amounts;
    }
}
