package Level9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Five {
    public static boolean prime[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        prime = new boolean[N + 1];
        getPrime();

        int sum = 0;
        int min = 0;
        for (int i = M; i <= N; i++) {
            if (!prime[i]) {
                sum += i;
                if (min == 0) {
                    min = i;
                }
            }
        }
        if (sum == 0) {
            System.out.println(-1);
        }else{
            System.out.println(sum);
            System.out.println(min);
        }
    }

    /**
     * 에라토스테네스의 체 (범위 내에서 소수를 구하는 알고리즘)
     * 소수가 아니면 true
     * 소수면 false
     */
    public static void getPrime() {
        prime[0] = true;
        prime[1] = true;
        // 2,3,5,7
        for (int i = 2; i <= Math.abs(Math.sqrt(prime.length)) ; i++) {
            if(prime[i]) continue;
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}
