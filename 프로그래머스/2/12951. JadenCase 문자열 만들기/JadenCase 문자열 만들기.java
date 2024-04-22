class Solution {
    public String solution(String s) {
        
        StringBuilder sb = new StringBuilder();
        
        String[] arr = s.split(" ");
        
        for(int i = 0; i < arr.length; i++){
            String str = arr[i];
            
            if(str.isEmpty()){
                sb.append(" ");
                continue;
            }
            
            sb.append(str.substring(0, 1).toUpperCase());
            sb.append(str.substring(1).toLowerCase());
            sb.append(" ");
        }
        
        if(s.toString().substring(s.length() - 1, s.length()).equals(" ")){
            return sb.toString();
        }
        
        return sb.toString().substring(0, sb.length() - 1);
    }
}