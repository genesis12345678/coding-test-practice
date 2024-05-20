class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder result = new StringBuilder();
        StringBuilder game = new StringBuilder();

        for (int i = 0; i < t * m; i++) {
            game.append(Integer.toString(i, n).toUpperCase());
        }

        for (int i = 0; i < t; i++) {
            result.append(game.charAt(i * m + (p - 1)));
        }

        return result.toString();
    }
}