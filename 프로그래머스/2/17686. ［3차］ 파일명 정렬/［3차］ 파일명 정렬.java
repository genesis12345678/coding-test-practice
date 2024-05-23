import java.util.Arrays;

class Solution {
    
    public class Filename implements Comparable<Filename>{
        String filename;
        String head;
        int number;
        
        public Filename(String filename, String head, int number){
            this.filename = filename;
            this.head = head;
            this.number = number;
        }
        
        @Override
        public int compareTo(Filename o){
            if(this.head.equals(o.head)){
                return this.number - o.number;
            }
            
            return this.head.compareTo(o.head);
        }
    }
    
    public String[] solution(String[] files) {
        
        Filename[] filenames = new Filename[files.length];
        
        for(int i = 0; i < files.length; i++){
            String[] split = split(files[i]);
            filenames[i] = new Filename(files[i], split[0], Integer.parseInt(split[1]));
        }
        
        Arrays.sort(filenames);
        
        String[] result = new String[files.length];
        
        for(int i = 0; i < files.length; i++){
            result[i] = filenames[i].filename;
        }
        
        return result;
        
    }
    
    public String[] split(String file){
        String[] arr = new String[3];
        arr[2] = "";
        
        int index = 0;
        
        while(index < file.length() && !Character.isDigit(file.charAt(index))){
            index++;
        }
        
        arr[0] = file.substring(0, index).toLowerCase();
        
        int temp = index;
        
        while(index < file.length() && Character.isDigit(file.charAt(index))){
            index++;
        }
        
        arr[1] = file.substring(temp, index);
        arr[2] = file.substring(index);
        
        return arr;
    }
    
    
}