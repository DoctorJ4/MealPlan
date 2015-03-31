package doctorj.mealplan;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
/**
 * Created by Jesse Dodson on 3/30/2015.
 */
public class GroceryList {
    private Set amounts;
    //private String []foodNames;
    private int listLength;
    private List foodNames;
    private Map ing;

    //Constructor
    public GroceryList() {listLength = 0;}

    void addGL(String name, int num)// TODO -> MIGHT NOT NEED THIS ANYMORE because of next function
    {
        //while foodNames is not empty
        for(int i = 0; i < listLength; i++)
        {
            if(name == foodNames.get(i))
            {
                //TODO -> add to entry's amount
                //amounts[i] += num;
                return;
            }

        }
        //TODO -> add entry
        //foodNames.add(name);
        //amounts[i] = num;
        listLength++;

        return;
    }

    void addGL(Ingredient []obj)
    {
        //while foodNames is not empty
        int i = 0;
        for(i = 0; i < listLength; i++)
        {
            if(obj[i].name == foodNames.get(i))
            {
                //TODO -> add to entry's amount
                amounts[i] += obj.num;
                return;
            }
        }
        //TODO -> add entry
        /*foodNames = Arrays.copyOf(foodNames, listLength + 1);
        foodNames[listLength - 1] = obj.name;*/


        //TODO -> after collection is made for foodNames and amounts
        foodNames.add(obj.name);
        amounts.add(obj.num);

        listLength++;

        return;
    }

    void subGL()
    {
        //find food name or return
        //if food name exists subtract amount
        //reload GL
    }

    String [] returnListNames()
    {
        return foodNames;
    }

    int [] returnAmounts()
    {
        return amounts;
    }
}
