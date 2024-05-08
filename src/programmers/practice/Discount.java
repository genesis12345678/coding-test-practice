package programmers.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Discount {
    public static void main(String[] args) {
        String[] want1 = {"banana", "apple", "rice", "pork", "pot"};
        String[] want2 = {"apple"};

        int[] number1 = {3, 2, 2, 2, 1};
        int[] number2 = {10};

        String[] discount1 = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        String[] discount2 = {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};

        System.out.println(solution(want1, number1, discount1));
        System.out.println(solution(want2, number2, discount2));

    }

    static int solution(String[] want, int[] number, String[] discount) {

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
