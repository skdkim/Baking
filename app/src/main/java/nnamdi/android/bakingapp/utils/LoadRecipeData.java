package nnamdi.android.bakingapp.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nnamdi.android.bakingapp.models.Ingredient;
import nnamdi.android.bakingapp.models.Recipe;
import nnamdi.android.bakingapp.models.Step;

/**
 * Created by Nnamdi on 2/18/2018.
 */

public class LoadRecipeData {
    private final String TAG = this.getClass().getName();

    public Recipe loadRecipeFromSharedPreferences(String jsonString){

        Recipe r = new Recipe();
        try{
            JSONObject recipe = new JSONObject(jsonString);

            r.setId(recipe.getInt("id"));
            r.setImage(recipe.getString("image"));
            r.setName(recipe.getString("name"));
            r.setServings(recipe.getInt("servings"));

            ArrayList<Ingredient> ingredientsArray = new ArrayList<>();
            JSONArray ingredientsJsonArray = recipe.getJSONArray("ingredients");

            for(int j = 0; j <ingredientsJsonArray.length(); j++){
                Ingredient ingredient = new Ingredient();
                JSONObject iObject = ingredientsJsonArray.getJSONObject(j);

                ingredient.setIngredient(iObject.getString("ingredient"));
                ingredient.setMeasure(iObject.getString("measure"));
                ingredient.setQuantity(iObject.getInt("quantity"));
                ingredientsArray.add(ingredient);
            }

            ArrayList<Step> stepsArray = new ArrayList<>();
            JSONArray stepsJsonArray = recipe.getJSONArray("steps");

            for(int k = 0; k< stepsJsonArray.length(); k++){
                Step step = new Step();
                JSONObject sObject = stepsJsonArray.getJSONObject(k);

                step.setDescription(sObject.getString("description"));
                step.setId(sObject.getInt("id"));
                step.setShortDescription(sObject.getString("shortDescription"));
                step.setThumbnailURL(sObject.getString("thumbnailURL"));
                step.setVideoURL(sObject.getString("videoURL"));
                stepsArray.add(step);

            }

            r.setIngredients(ingredientsArray);
            r.setSteps(stepsArray);
            return r;
        }catch(JSONException e){
            Log.v(TAG, "Error in JSON file: " + e);
            return null;
        }

    }

}
