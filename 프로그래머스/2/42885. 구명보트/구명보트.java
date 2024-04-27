import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int s = 0;
        int e = people.length - 1;
        int count = 0;

        while (s <= e) {
            if (people[s] + people[e] <= limit) {
                s++;
            }
            e--;
            count++;
        }

        return count;
    }
}