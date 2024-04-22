package programmers.practice;

public class MakeMin {

    static int[] a;
    static int[] b;
    static int min = Integer.MAX_VALUE;
    static boolean[] visit = new boolean[1001];

    public static void main(String[] args) {
        a = new int[]{1, 4, 2};
        b = new int[]{5, 4, 4};

        solution(0, 0);
        System.out.println(min);
    }

    private static void solution(int index, int sum) {
        if (index == a.length) {
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < a.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                solution(index + 1, sum + a[index] * b[i]);
                visit[i] = false;
            }
        }
    }
}
