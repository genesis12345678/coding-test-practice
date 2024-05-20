package programmers.practice;

import java.util.ArrayList;

public class VowelDictionary {

    static String[] str = {"A", "E", "I", "O", "U"};
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String word1 = "AAAAE";
        String word2 = "AAAE";
        String word3 = "I";
        String word4 = "EIO";

        solution(word1);
        solution(word2);
        solution(word3);
        solution(word4);

        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());
    }

    static void solution(String word) {
        dfs(0,"");
    }

    static void dfs(int depth, String now) {

        list.add(now);

        if (depth == 5) {
            return;
        }

        for (String s : str) {
            dfs(depth + 1, now + s);
        }

    }

}
