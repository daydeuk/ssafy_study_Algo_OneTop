import java.util.HashSet;
class Solution {
    public int solution(int[] nums) {
        int pick = nums.length / 2;
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int num : nums) {
            set.add(num);
        }
        
        if(set.size() > pick) {
            return pick;
        }
        return set.size();
    }
}
