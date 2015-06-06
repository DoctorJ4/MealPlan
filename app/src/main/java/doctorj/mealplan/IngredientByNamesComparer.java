package doctorj.mealplan;

import java.util.Comparator;

/**
 * Created by Jesse on 6/6/2015.
 */
public class IngredientByNamesComparer implements Comparator<Ingredient> {
    @Override
    public int compare(Ingredient x, Ingredient y) {
        return x.getName().compareTo( y.getName());
    }
}