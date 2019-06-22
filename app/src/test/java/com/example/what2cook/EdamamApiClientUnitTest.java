package com.example.what2cook;

import com.example.what2cook.model.RecipeSimple;
import com.example.what2cook.restclient.EdamamApiClient;
import com.example.what2cook.restclient.HttpResponseWrapper;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class EdamamApiClientUnitTest {

    @Test
    public void test_client() throws IOException, ExecutionException, InterruptedException {
        EdamamApiClient client = new EdamamApiClient();
        List<String> ingredients = Arrays.asList("chicken,spinach");
        HttpResponseWrapper httpWrapper = client.getRecipesByIngredientsList(ingredients);
        List<RecipeSimple> recipeList = httpWrapper.convertJsonToRecipeObjectList();
        print(recipeList);
    }

    private void print(List<RecipeSimple> recipeSimpleList) {
        for (RecipeSimple recipe : recipeSimpleList) {
            System.out.println(recipe.toString());
        }
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}