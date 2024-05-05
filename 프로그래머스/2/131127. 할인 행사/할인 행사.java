import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        int result = 0;
        Map<String, Integer> disMap = new HashMap<>();
        for (int i = 0; i <= discount.length - 10; i++) {
            boolean find = true;

            for (int j = i; j < i + 10; j++) {
                disMap.put(discount[j], disMap.getOrDefault(discount[j], 0) + 1);
            }

            for (String s : want) {
                if (wantMap.get(s) != disMap.get(s)) {
                    find = false;
                    break;
                }
            }
            if (find) {
                result++;
            }

            disMap.clear();
        }

        return result;
    }
}