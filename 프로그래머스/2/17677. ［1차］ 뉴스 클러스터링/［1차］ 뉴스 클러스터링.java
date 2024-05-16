import java.util.ArrayList;

class Solution {
    public int solution(String str1, String str2) {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for(int i = 0; i < str1.length() - 1; i++){
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);
            
            if(!isAlpha(c1) || !isAlpha(c2)){
                continue;
            }
            
            list1.add(str1.substring(i, i + 2));
        }
        
        for(int i = 0; i < str2.length() - 1; i++){
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);
            
            if(!isAlpha(c1) || !isAlpha(c2)){
                continue;
            }
            
            list2.add(str2.substring(i, i + 2));
        }
        
        int intersection = 0;
        int union = 0;
        
        for(String s : list1){
            if(list2.remove(s)){
                intersection++;
            }
            union++;
        }
        
        union += list2.size();
        
        double d;
        if(union == 0){
            d = 1;
        } else {
            d = (double) intersection / union;
        }
        
        return (int) (d * 65536);
    }
    
    public boolean isAlpha(char c){
        return 'a' <= c && c <= 'z';
    }
}