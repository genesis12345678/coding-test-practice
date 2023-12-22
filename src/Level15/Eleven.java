package Level15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 결국 마지막에 열려 있다는 것은 홀수번 진행됐다는 뜻이다.
 * 1부터 n까지 제곱수를 구하면 된다.
 * 제곱수의 특징이 약수가 홀수개이다.
 */
public class Eleven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println((int)Math.sqrt(n));
    }
}
