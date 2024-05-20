package programmers.practice;

import java.util.ArrayList;
import java.util.Arrays;

public class Tuple {
    public static void main(String[] args) {
        String s1 = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        String s2 = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        String s3 = "{{20,111},{111}}";
        String s4 = "{{123}}";
        String s5 = "{{4,2,3},{3},{2,3,4,1},{2,3}}";

        System.out.println(Arrays.toString(solution(s1)));
        System.out.println(Arrays.toString(solution(s2)));
        System.out.println(Arrays.toString(solution(s3)));
        System.out.println(Arrays.toString(solution(s4)));
        System.out.println(Arrays.toString(solution(s5)));
    }

    private static int[] solution(String s) {

        s = s.substring(2, s.length() - 2);

        String[] split = s.split("\\},\\{");

        Arrays.sort(split, (o1, o2) -> o1.length() - o2.length());
        ArrayList<Integer> list = new ArrayList<>();
        for (String str : split) {
            String[] temp = str.split(",");

            for (String string : temp) {
                int num = Integer.parseInt(string);

                if (!list.contains(num)) {
                    list.add(num);
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
