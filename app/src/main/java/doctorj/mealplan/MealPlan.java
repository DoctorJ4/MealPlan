package doctorj.mealplan;
/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan {
    private MealObjects collection [];
    private GroceryList gl;
    private int planLength;

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
        //loop to fill array with names
        for(int i = 0; i < planLength; i++)
            list[i] = collection[i].getName();
        return list;
    }

    public void sendIngredientsToGL()
    {
        /*for(int i = 0; i < planLength; i ++) //TODO -> FIX - A (two parts) -> GroceryList
            gl.addGL(collection[i].getIngredients());*/
        return;
    }
}
