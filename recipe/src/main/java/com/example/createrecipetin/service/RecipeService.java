package com.example.createrecipetin.service;


import com.example.createrecipetin.models.Ingredients;
import com.example.createrecipetin.models.Instructions;
import com.example.createrecipetin.models.Recipe;
import com.example.createrecipetin.repositories.InstructionsRepo;
import com.example.createrecipetin.repositories.IngredientsRepo;
import com.example.createrecipetin.repositories.RecipeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeService {

    @Autowired
    private RecipeRepo recipeRepo;
    @Autowired
    private InstructionsRepo instructionsRepo;
    @Autowired
    private IngredientsRepo ingredientsRepo;

    //ResponseEntity represents the whole http response;
    // status code, headers, and body
    public Recipe save(Recipe recipe){
        //recipeRepo.save(recipe);
        Recipe newRecipe = recipeRepo.save(recipe);

        Long recipe_id = newRecipe.getId();
        Ingredients ingredients = newRecipe.getIngredient();
        ingredients.setRecipe(newRecipe);
        Instructions instructions = newRecipe.getInstruction();
        instructions.setRecipe(newRecipe);
        return newRecipe;
    }


}
