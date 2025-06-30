// 문제 링크 :
// https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/?envType=daily-question&envId=2025-03-21
// simple
// [ 106 ms | 50 mb ]
import java.util.*;
public class Leet_Daily_2115 {
    class Solution {
        public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            List<String> result = new ArrayList<>();
            Map<String, Set<String>> map = new HashMap<>();
            for(int i=0; i<recipes.length; i++){
                Set<String> ingredientsSet = new HashSet<>(ingredients.get(i));
                map.put(recipes[i], ingredientsSet);
            }
            Set<String> suppliesSet = new HashSet<>(Arrays.asList(supplies));
            boolean toggle = true;
            while(toggle){
                toggle = false;
                Iterator<Map.Entry<String, Set<String>>> iterator = map.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, Set<String>> entry = iterator.next();
                    Set<String> ingredientsSet = entry.getValue();
                    ingredientsSet.removeAll(suppliesSet);
                    if(ingredientsSet.isEmpty()){
                        String entRecipe = entry.getKey();
                        result.add(entRecipe);
                        suppliesSet.add(entRecipe);
                        toggle = true;
                        iterator.remove();
                    }
                }
            }
            return result;
        }
    }
}
