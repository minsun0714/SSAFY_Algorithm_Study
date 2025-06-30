package etc._4_4;

import java.util.*;

// 반복문
class Solution {
	public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
    	
    	HashSet<String> set = new HashSet<>(Arrays.asList(supplies));
    	ArrayList<String> result = new ArrayList<>();
        
    	// 뭔가 만들어졌는가?
        boolean isAdded = true;
        
        // 뭔가 만들어진 동안
        while (isAdded) {
        	isAdded = false;
            for (int i = 0; i < recipes.length; i++) {
                // 이미 레시피 만들었음
                if (result.contains(recipes[i])) continue;

                boolean isAbleToMake = true;
                for (String ingredient : ingredients.get(i)) {
                    // 모두 재료로 존재
                    if (!set.contains(ingredient)) {
                        isAbleToMake = false;
                        break;
                    }
                }

                if (!isAbleToMake) continue; // 못 만들면 넘김
                
                // 만들 수 있다
                result.add(recipes[i]);
                set.add(recipes[i]);
                isAdded = true;
            }
        }

        return result;
    }
}