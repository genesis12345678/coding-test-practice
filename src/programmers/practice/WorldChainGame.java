package programmers.practice;

import java.util.Arrays;
import java.util.HashSet;

public class WorldChainGame {
    public static void main(String[] args) {

        int n = 5;
//        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
//        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};

        System.out.println(Arrays.toString(solution(n, words)));
    }

    static int[] solution(int n, String[] words) {
        int[] result = new int[2];

        HashSet<String> used = new HashSet<>();

        for (int i = 1; i < words.length; i++) {

            String before = words[i - 1];
            String next = words[i];

            used.add(before);

            char prev = before.charAt(before.length() - 1);
            char now = next.charAt(0);

            if (used.contains(next) || prev != now) {
                result[0] = i % n + 1;
                result[1] = i / n + 1;
                return result;
            }

            used.add(next);
        }

        return result;
    }
}
