import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String[] temp = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        Arrays.sort(temp, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        
        if (temp[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(temp).forEach(sb::append);

        return sb.toString();
    }
}