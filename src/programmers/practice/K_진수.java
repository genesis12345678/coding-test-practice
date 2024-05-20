package programmers.practice;

import java.util.Arrays;

public class K_진수 {
    public static void main(String[] args) {
        int n1 = 437674;
        int n2 = 110011;
        int n3 = 1_000_000;

        int k1 = 3;
        int k2 = 10;
        int k3 = 3;

        System.out.println(solution(n1, k1));
        System.out.println(solution(n2, k2));
        System.out.println(solution(n3, k3));
    }

    private static int solution(int n, int k) {

        String num = convert(n, k);

        String[] split = num.split("0");
        int count = 0;
        for (String s : split) {
            if (s.isEmpty()) {
                continue;
            }
            long temp = Long.parseLong(s);
            if (isPrime(temp)) {
                count++;
            }
        }

        return count;
    }

    static String convert(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n /= k;
        }

        return sb.reverse().toString();
    }

    static boolean isPrime(long n) {

        if (n == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
