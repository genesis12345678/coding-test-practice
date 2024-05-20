package programmers.practice;

import java.util.ArrayList;
import java.util.HashSet;

public class NewsClustering {
    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";

        String str3 = "handshake";
        String str4 = "shake hands";

        String str5 = "aa1+aa2";
        String str6 = "AAAA12";

        String str7 = "E=M*C^2";
        String str8 = "e=m*c^2";

        System.out.println(solution(str1, str2));
        System.out.println(solution(str3, str4));
        System.out.println(solution(str5, str6));
        System.out.println(solution(str7, str8));
    }

    private static int solution(String str1, String str2) {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);

            if (!isAlpha(c1) || !isAlpha(c2)) {
                continue;
            }

            list1.add(str1.substring(i, i + 2));
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);

            if (!isAlpha(c1) || !isAlpha(c2)) {
                continue;
            }

            list2.add(str2.substring(i, i + 2));
        }

        int intersection = 0;
        int union = 0;

        for (String s : list1) {
            if (list2.remove(s)) {
                intersection++;
            }
            union++;
        }

        union += list2.size();

        double d;
        if (union == 0) {
            d = 1;
        } else {
            d = (double) intersection / union;
        }


        return (int) (d * 65536);
    }

    private static boolean isAlpha(char c) {
        return 'a' <= c && c <= 'z';
    }
}
