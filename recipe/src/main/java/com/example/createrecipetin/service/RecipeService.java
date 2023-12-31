package com.example.createrecipetin.service;

import com.example.createrecipetin.models.Ingredients;
import com.example.createrecipetin.models.Instructions;
import com.example.createrecipetin.models.Recipe;
import com.example.createrecipetin.repositories.InstructionsRepo;
import com.example.createrecipetin.repositories.IngredientsRepo;
import com.example.createrecipetin.repositories.RecipeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /** Services **/
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private InstructionService instructionService;


    //ResponseEntity represents the whole http response;
    // status code, headers, and body
    public Recipe save(Recipe recipe){
        recipeRepo.save(recipe);

        Ingredients ingredients = ingredientService.saveRecipe(recipe);
        Instructions instructions = instructionService.saveRecipe(recipe);
        return recipe;
    }

    public List<Recipe> findAll(){
        List<Recipe> recipeList= recipeRepo.findAll();
        return  recipeList;
    }

    public Optional<Recipe> update(Recipe newRecipe, Long id){
        Optional<Recipe> recipe = getById(id)
                .map(recipe1 -> {
                    recipe1.setIngredient(newRecipe.getIngredient());
                    recipe1.setInstruction(newRecipe.getInstruction());
                    return save(recipe1);
                });
        return recipe;
    }

    public Optional<Recipe> getById(Long id){
        Optional<Recipe> r = recipeRepo.findById(id);//recipeRepo.findById(id);
        return r;
    }

    public Optional<Recipe> deleteById(Long id) throws Exception {


        Optional<Recipe> deleted = getById(id);

        if(deleted == null){
            throw new Exception("Recipe not found!");

        }else{
            recipeRepo.delete(deleted.get());
        }

        return deleted;
    }

    public void deleteAll() throws Exception {

        List<Recipe> list = recipeRepo.findAll();

        if(list.isEmpty()){
            throw new Exception("Recipe not found!");
        }
        recipeRepo.deleteAll(list);


    }



}
