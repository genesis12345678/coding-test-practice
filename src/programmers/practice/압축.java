package programmers.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 압축 {
    public static void main(String[] args) {
        String msg1 = "KAKAO";
        String msg2 = "TOBEORNOTTOBEORTOBEORNOT";
        String msg3 = "ABABABABABABABAB";

        System.out.println(Arrays.toString(solution(msg1)));
        System.out.println(Arrays.toString(solution(msg2)));
        System.out.println(Arrays.toString(solution(msg3)));
    }

    private static int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            char c = (char) ('A' + i);
            map.put(String.valueOf(c) , i + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();

        int next = 27;

        for (int i = 0; i < msg.length();) {
            String w = String.valueOf(msg.charAt(i));
//            int j = i + 1;
//
//            while (j <= msg.length()) {
//                String wc = msg.substring(i, j);
//                if (map.containsKey(wc)) {
//                    w = wc;
//                    j++;
//                } else {
//                    break;
//                }
//            }

            int j = i + 1;
            for (; j <= msg.length(); j++) {
                String wc = msg.substring(i, j);
                if (map.containsKey(wc)) {
                    w = wc;
                } else {
                    break;
                }
            }

            list.add(map.get(w));

            if (j <= msg.length()) {
                map.put(msg.substring(i, j), next++);
            }

            i += w.length();
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
