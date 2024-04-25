package programmers.practice;

public class NextBigNum {
    public static void main(String[] args) {
        int n = 78;

        System.out.println(solution(n));
    }

    private static int solution(int n) {

        int count = Integer.bitCount(n);

        while (Integer.bitCount(++n) != count) {
        }

        return n;
    }
}
