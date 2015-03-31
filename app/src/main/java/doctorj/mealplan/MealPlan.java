package doctorj.mealplan;
import java.util.Map;
/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan {
    private MealObjects collection [];
    private GroceryList gl;
    private int planLength;
    private Map schedule; //TODO -> initialize in constructor to contain dates and names


    public MealPlan(int days)
    {
        collection = new MealObjects [days];
        planLength = days;
        //TODO write sql to fill array meal_objects with all MealObjects fields

        sendIngredientsToGL();
    }

    public String getMealName(int num)
    {
        return collection[num].getName();
    }

    public String [] getNames()
    {

        String list [];
        list = new String [planLength];
        //TODO -> write loop to fill array with names
        return list;
    }

    public void sendIngredientsToGL()
    {
        for(int i = 0; i < planLength; i ++)
            gl.addGL(collection[i].getIngredients());
        return;
    }
}
