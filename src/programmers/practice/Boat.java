package programmers.practice;

import java.util.Arrays;

public class Boat {
    public static void main(String[] args) {
        int[] people = {70, 80, 50};
        int limit = 100;

        System.out.println(solution(people, limit));
    }

    static int solution(int[] people, int limit) {

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
