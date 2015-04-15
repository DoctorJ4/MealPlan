package doctorj.mealplan;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class RecipeViewActivity extends ActionBarActivity {

    MealPlan selectd;
    String RecipeName;
    String Ingredients;
    String Directions;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeview);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeview);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String name = extras.getString("StringName");
        String ingredients = extras.getString("StringIngredients");
        String directions = extras.getString("StringDirections");

        final TextView nameView = (TextView)
                findViewById(R.id.Name);
        final TextView ingView = (TextView)
                findViewById(R.id.Ingredients);
        final TextView directionsView = (TextView)
                findViewById(R.id.Directions);
        nameView.setText(name);
        ingView.setText(ingredients);
        directionsView.setText(directions);

        //populateTextBoxes();
    }

    private void populateTextBoxes()
    {
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.recipe_layout,this.RecipeName);
        TextView nameTag = (TextView) findViewById(R.id.Name);
        nameTag.setText(this.RecipeName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display__details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
