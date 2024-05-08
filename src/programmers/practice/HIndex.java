package programmers.practice;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(solution(citations));
    }

    static int solution(int[] citations) {
        Arrays.sort(citations);

        int s = 0;
        int e = citations.length - 1;
        int result = 0;

        while (s <= e) {
            int mid = (s + e) / 2;
            int h = citations.length - mid;

            if (citations[mid] >= h) {
                result = h;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return result;
    }
}
