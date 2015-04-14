package doctorj.mealplan;

import java.util.List;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class MealPlan {
    private Recipe recipes [];
    private GroceryList gl;
    private int planLength;
    private String schedule [];

    public MealPlan(String month, int days, RecipeHelper db)
    {
        List <String> mealNames = db.getAllNames();
        this.recipes = new Recipe [days];
        for(int j = 0; j < this.recipes.length; j++)
        {
            this.recipes[j] = new Recipe();//TODO write sql to fill array of meal_objects with all Recipe fields
        }
        this.schedule = new String [days];
        for(int k = 0; k < schedule.length; k++)
        {
            this.schedule[k] = month + " " + (k+1) + "\n" + mealNames.get(k % mealNames.size());
        }
        this.planLength = days;


        for(int i = 0; i < this.planLength; i++)
        {
            this.recipes[i].setName(mealNames.get(i % mealNames.size()));
        }
        sendIngredientsToGL();
    }

    /*public MealPlan(String month, int days, String [] mealNames)
    {

        this.recipes = new Recipe [days];
        for(int j = 0; j < this.recipes.length; j++)
        {
            this.recipes[j] = new Recipe();
        }
        this.schedule = new String [days];
        for(int k = 0; k < schedule.length; k++)
        {
            this.schedule[k] = month + " " + (k+1) + "\n" + mealNames[k];
        }
        this.planLength = days;
        //write sql to fill array of meal_objects with all Recipe fields

        for(int i = 0; i < this.planLength; i++)
        {
            this.recipes[i].setName(mealNames[i]);
        }
        sendIngredientsToGL();
    }*/

    public String getMealName(int num)
    {
        return recipes[num].getName();
    }

    public String [] getNames()
    {

        String names [];
        names = new String [this.planLength];
        //loop to fill array with names
        for(int i = 0; i < this.planLength; i++)
        {
            names[i] = this.recipes[i].getName();
        }
        return names;
    }

    public String [] getSchedule()
    {
        return this.schedule;
    }

    public void sendIngredientsToGL()
    {
        /*for(int i = 0; i < planLength; i ++) //TODO -> FIX - A (two parts) -> GroceryList
            this.gl.addGL(this.recipes[i].getIngredients());*/
        return;
    }
}
