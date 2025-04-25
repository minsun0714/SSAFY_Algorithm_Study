import java.util.*;
//dfs
class Solution {
    public static List<String> answer;
    public static Map<String, Food> category;
    
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        answer = new ArrayList<>();
        category = new HashMap<>();
        for(int i = 0; i<recipes.length; i++){
            String recipe = recipes[i];
            category.putIfAbsent(recipe, new Food(recipe));
            category.get(recipe).setNeed(ingredients.get(i).size());
            
            for(String ingredient:ingredients.get(i)){
                category.putIfAbsent(ingredient, new Food(ingredient));
                category.get(ingredient).addRecipe(category.get(recipe));
            }
        }

        for(String supply: supplies){
            dfs(supply);
        }
        return answer;
    }

    public void dfs(String name){
        Food food = category.get(name);
        if(food==null){
            return;
        }
        if(food.need!=0&&food.increse_and_check()){
            answer.add(food.name);
        }
        if(food.need==food.have && food.recipes.size()>0){
            for(String recipe : new ArrayList<>(food.recipes)){
                dfs(recipe);
            }
        }
    }

    class Food{
        public Set<String> recipes = new HashSet<>();
        public String name;
        private int need = 0;
        private int have = 0;
        
        public Food(String name){
            this.name = name;
        }
        public void addRecipe(Food recipe){
            this.recipes.add(recipe.name);
        }
        public void setNeed(int need){
            this.need = need;
        }
        public boolean increse_and_check(){
            have++;
            if(have==need){
                return true;
            }
            else return false;
        }
    }
}
