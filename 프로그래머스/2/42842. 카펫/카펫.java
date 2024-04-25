class Solution {
    public int[] solution(int brown, int yellow) {
        int area = brown + yellow;
        
        for(int h = 3; h <= Math.sqrt(area); h++){
            if(area % h == 0){
                int width = area / h;
                
                if((width - 2) * (h - 2) == yellow){
                    return new int[]{width, h};
                }
            }
        }
        
        return null;
    }
}