package doctorj.mealplan;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class GroceryList {
    private List<Ingredient> ingredients;

    public GroceryList()
    {
        ingredients = new ArrayList<>();
    }

    void addGL(Ingredient ing)// TODO -> consider measurements
    {
        int i;
        for(Ingredient tempIng : ingredients) {
            if (tempIng.getName().equals(ing.getName())) {
                i = this.ingredients.indexOf(tempIng);
                this.ingredients.get(i).setAmount(this.ingredients.get(i).getAmount() + ing.getAmount());

                return;
            }
        }

        this.ingredients.add(ing);
    }

    void addGL(List<Ingredient> ings)
    {

        for(int i = 0; i < ings.size(); i++)
            addGL(ings.get(i));
    }

    void sort()
    {
        IngredientByNamesComparer comp = new IngredientByNamesComparer();
        Collections.sort(ingredients, comp);
    }


    public String getListString(){
        String GLstring = "";
        if(ingredients.isEmpty())
            GLstring = "Error in Grocery List";

        for (int i = 0; i < ingredients.size(); i++) {
            GLstring = GLstring + ingredients.get(i).getAmount() + " " + ingredients.get(i).getName() + "\n";
        }

        return GLstring;
    }

}

