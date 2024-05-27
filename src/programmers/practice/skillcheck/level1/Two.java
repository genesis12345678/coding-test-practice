package programmers.practice.skillcheck.level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Two {
    public static void main(String[] args) {
        int[] lottos1 = {44, 1, 0, 0, 31, 25};
        int[] lottos2 = {0, 0, 0, 0, 0, 0};
        int[] lottos3 = {45, 4, 35, 20, 3, 9};

        int[] win_nums1 = {31, 10, 45, 1, 6, 19};
        int[] win_nums2 = {38, 19, 20, 40, 15, 25};
        int[] win_nums3 = {20, 9, 3, 45, 4, 35};

        int[] result1 = solution(lottos1, win_nums1);
        int[] result2 = solution(lottos2, win_nums2);
        int[] result3 = solution(lottos3, win_nums3);

        System.out.println("result1 = " + Arrays.toString(result1));
        System.out.println("result2 = " + Arrays.toString(result2));
        System.out.println("result3 = " + Arrays.toString(result3));
    }

    private static int[] solution(int[] lottos, int[] win_nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : win_nums) {
            set.add(num);
        }

        int zero = 0;
        int count = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                zero++;
            }
            if (set.contains(lotto)) {
                count++;
            }
        }

        int min = count;
        int max = count + zero;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(6, 1);
        map.put(5, 2);
        map.put(4, 3);
        map.put(3, 4);
        map.put(2, 5);
        map.put(1, 6);
        map.put(0, 6);

        return new int[]{map.get(max), map.get(min)};
    }
}
