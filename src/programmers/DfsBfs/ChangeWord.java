package programmers.DfsBfs;

public class ChangeWord {

    static boolean[] visit;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        visit = new boolean[words.length];

        dfs(begin, target, words, 0);

        System.out.println(min);
    }

    private static void dfs(String begin, String target, String[] words, int depth) {
        if (begin.equals(target)) {
            min = Math.min(min, depth);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (visit[i]) {
                continue;
            }

            int same = 0;
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) == words[i].charAt(j)) {
                    same++;
                }
            }

            if (same == begin.length() - 1) {
                visit[i] = true;
                dfs(words[i], target, words, depth + 1);
                visit[i] = false;
            }
        }
    }
}
