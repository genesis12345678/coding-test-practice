package bruteForce.Ten;

public class Three {

    static boolean[] visit = new boolean[10001];

    public static void main(String[] args){

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10000; i++) {
            if (d(i) <= 10000) {
                visit[d(i)] = true;
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (!visit[i]) {
                sb.append(i).append("\n");
            }
        }

        System.out.print(sb);
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
