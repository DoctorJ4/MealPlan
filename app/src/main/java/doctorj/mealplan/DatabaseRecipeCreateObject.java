package doctorj.mealplan;

import java.util.List;

/**
 * Created by Jesse on 12/4/2015.
 */
public class DatabaseRecipeCreateObject {
    public String name;
    public List<Ingredient> ingredients;
    public int portions;
    public String directions;
    public String category;
    public boolean favorite;
    DatabaseRecipeCreateObject(String name, int portions, String directions, String category, boolean favorite){
        this.name = name;
        this.portions = portions;
        this.directions = directions;
        this.category = category;
        this.favorite = favorite;
    }
}
