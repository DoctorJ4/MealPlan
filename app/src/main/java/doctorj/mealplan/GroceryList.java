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
        listLength = 0;
        ArrayList<Integer> amounts = new ArrayList<Integer>();
    }

    void addGL(String name, int num)// TODO -> MIGHT NOT NEED THIS ANYMORE because of next function
    {
        int temp;
        //while foodNames is not empty
        for(int i = 0; i < listLength; i++)
        {
            if(name == foodNames.get(i))
            {
                //TODO -> add to entry's amount
                //amounts[i] += num;
                temp = amounts.get(i) + num;
                amounts.set(i, temp);
                return;
            }

        }
        //TODO -> add entry ??? DONE ???
        foodNames.add(name);
        amounts.add(num);
        listLength++;

        return;
    }

    void addGL(Ingredient []obj)
    {
        //while foodNames is not empty
        int i = 0;
        int temp;
        for(i = 0; i < obj.length; i++)
        {
            if(obj[i].getName() == foodNames.get(i))
            {
                //add to entry's amount
                temp = amounts.get(i) + obj[i].getAmount();
                amounts.set(i, temp);
                return;
            }
            else //add entry
            {
                foodNames.add(obj[i].getName());
                amounts.add(obj[i].getAmount());
                listLength++;
            }
        }
        return;
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
        return foodNames;
    }

    ArrayList<Integer> returnAmounts()
    {
        return amounts;
    }
}
