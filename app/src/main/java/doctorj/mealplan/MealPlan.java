package doctorj.mealplan;



import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan {
    private Recipe recipes [];
    private GroceryList gl;
    private int planLength;

    public MealPlan(int days)
    {
        this.recipes = new Recipe [days];
        this.planLength = days;
        //TODO write sql to fill array meal_objects with all Recipe fields

        //copied code

        //copied code

        sendIngredientsToGL();
    }

    public String getMealName(int num)
    {
        return recipes[num].getName();
    }

    public String [] getNames()
    {

        String list [];
        list = new String [this.planLength];
        //loop to fill array with names
        for(int i = 0; i < this.planLength; i++)
            list[i] = this.recipes[i].getName();
        return list;
    }

    public void sendIngredientsToGL()
    {
        /*for(int i = 0; i < planLength; i ++) //TODO -> FIX - A (two parts) -> GroceryList
            this.gl.addGL(this.recipes[i].getIngredients());*/
        return;
    }
}
