public class Main {

    static boolean[] visit = new boolean[10001];

    public static void main(String[] args) {

        for (int i = 1; i <= 10000; i++) {
            if (d(i) <= 10000) {
                visit[d(i)] = true;
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (!visit[i]) {
                System.out.println(i);
            }
        }
    }

    static int d(int n) {

        int sum = n;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }
}
