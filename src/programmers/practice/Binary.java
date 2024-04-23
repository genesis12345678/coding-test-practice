package programmers.practice;

import java.util.Arrays;

public class Binary {


    public static void main(String[] args) {
        String s = "01110";

        System.out.println(Arrays.toString(solution(s)));

    }

    static int[] solution(String s) {

        int zeroCount = 0;
        int count = 0;

        String temp = s;

        while (!temp.equals("1")) {
            count++;

            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) == '0') {
                    zeroCount++;
                }
            }

            temp = temp.replaceAll("0", "");

            int length = temp.length();

            StringBuilder sb = new StringBuilder();

            while (length > 0) {
                sb.append(length % 2);
                length /= 2;
            }

            temp = sb.reverse().toString();
        }

        return new int[]{count, zeroCount};
    }
}
