class Solution {
    public int solution(String skill, String[] skill_trees) {
        int count = 0;
        
        for(String st : skill_trees){
            if(check(skill, st)){
                count++;
            }
        }
        
        return count;
    }
    
    public boolean check(String skill, String skillTree){
        int index = 0;
        
        for(char ch : skillTree.toCharArray()){
            int temp = skill.indexOf(ch);
            
            if(temp == -1){
                continue;
            }
            
            if(temp == index){
                index++;
            } else {
                return false;
            }
        }
        
        return true;
    }
}