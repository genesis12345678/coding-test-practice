package programmers.practice;

public class Matches {
    public static void main(String[] args) {
        int n = 8;
        int a = 4;
        int b = 7;

        System.out.println(solution(n, a, b));
    }

    private static int solution(int n, int a, int b) {
        int count = 0;

        while (a != b) {

            a = (a + 1) / 2;
            b = (b + 1) / 2;

            count++;
        }
        return count;
    }
}
