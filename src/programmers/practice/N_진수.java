package programmers.practice;

public class N_진수 {
    public static void main(String[] args) {
        int n1 = 2, n2 = 16, n3 = 16;
        int t1 = 4, t2 = 16, t3 = 16;
        int m1 = 2, m2 = 2, m3 = 2;
        int p1 = 1, p2 = 1, p3 = 2;

        System.out.println(solution(n1, t1, m1, p1));
        System.out.println(solution(n2, t2, m2, p2));
        System.out.println(solution(n3, t3, m3, p3));
    }

    private static String solution(int n, int t, int m, int p) {

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
