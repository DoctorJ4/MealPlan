package doctorj.mealplan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GroceryListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        final TextView groceryListView;
        groceryListView = (TextView) findViewById(R.id.grocery_list_text);
        populateGroceryList(groceryListView);
    }

    private void populateGroceryList(TextView GLview)
    {
        String listString = getGLstring();
        GLview.setText(listString);
    }

    private String getGLstring(){
        GroceryList GL = new GroceryList();
        for(int i = 0; i < GlobalPlan.globalPlan.getLength(); i++)
            GL.addGL(GlobalPlan.globalPlan.getMealIngredients(i));

        return GL.getListString();
    }

}
