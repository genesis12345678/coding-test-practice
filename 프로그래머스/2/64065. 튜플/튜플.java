import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    public int[] solution(String s) {
        String[] split = s.substring(2, s.length() - 2).split("\\},\\{");
        Arrays.sort(split, (o1, o2) -> o1.length() - o2.length());
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for(String str : split){
            String[] temp = str.split(",");
            
            for(String string : temp){
                int num = Integer.parseInt(string);
                
                if(!list.contains(num)){
                    list.add(num);
                }
            }
        }
        
        int[] result = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }
        
        return result;
    }
}