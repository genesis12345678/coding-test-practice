package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



/**
 * 아이디어
 * 1. 처음부터 5의 배수라면 N / 5가 정답이다.
 * 2. 5의 배수가 아니라면 3만큼 빼주고 count++
 * 3. 주어진 최대값은 5kg이고 가장 적은 개수가 목표니까 5의 배수인지 최우선적으로 계산하고 다음으로 3kg의 수를 계산한다.
 * 4. 5로는 안나눠지고 계속 3kg를 빼기만 하다가 음수까지 왔을 경우에는 3kg와 5kg로 Nkg를 만들 수 없게 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2839">백준 2839번 : 그리디 알고리즘 - 설탕 배달</a>
 * @since 2023-12-27
 */

public class One {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        int count = 0;
        while (true) {
            if (N % 5 == 0) {
                count += N / 5;
                break;
            }
            count++;
            N -= 3;
            if (N < 0) {
                count = -1;
                break;
            }
        }
        System.out.println(count);
    }
}
