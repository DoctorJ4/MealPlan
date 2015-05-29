package doctorj.mealplan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class viewRecipesActivity extends Activity {
    RecipeHelper db;
    List<Recipe> recipes;
    int editLevel = 0;
    private String [] categories = {"Italian", "American", "Mexican"};
    private List<String> listFields = new ArrayList<>();
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new RecipeHelper(this);
        recipes = new ArrayList<>();
        populateListView();
    }

    private void refreshListView(String category){
        recipes = db.getRecipesCategory(category);
        listFields = new ArrayList<>();
        for(int i = 0; i < recipes.size(); i++)
            listFields.add(recipes.get(i).getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items, listFields);
        ListView mealTag = (ListView) findViewById(R.id.MealList);
        mealTag.setAdapter(adapter);
        setListViewClickListener(mealTag);
    }

    private void populateListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.meal_items, this.categories);
        ListView mealTag = (ListView) findViewById(R.id.MealList);
        mealTag.setAdapter(adapter);
        setListViewClickListener(mealTag);
    }

    private void setListViewClickListener(ListView LV)
    {
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (editLevel == 0) {
                    editLevel = 1;
                    refreshListView(categories[position]);
                } else {
                    Intent act = new Intent(getApplicationContext(), RecipeViewActivity.class);
                    act.putExtra("StringName", recipes.get(position).getName());
                    act.putExtra("StringIngredients", recipes.get(position).getIngredientsString());
                    act.putExtra("StringDirections", recipes.get(position).getDirections());
                    startActivity(act);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                finish();
            }
            else{
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }//onActivityResult

    @Override
    public void onBackPressed() {
        if(editLevel == 0) {
            super.onBackPressed();
            finish();
        }
        else if (editLevel == 1) {
            editLevel = 0;
            populateListView();
        }
    }

}
