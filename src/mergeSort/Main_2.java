package mergeSort;

import java.util.Scanner;

public class Main_2 {
    public static long a;
    public static long b;
    public static long c;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();

        System.out.println(compute(b));

    }

    private static long compute(long count) {

        if(count == 1) return a % c;

        if(count == 0) return 1 % c;

        long temp = compute(count / 2) % c;

        long ret = (temp * temp) % c;
        if(count % 2 == 1) ret = (ret * a) % c;

        return ret;
    }
}
