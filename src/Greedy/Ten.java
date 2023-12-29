package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 현실처럼 기름값이 싼 데서 많이 넣어야 한다.
 * 현재 기름값이 최소 기름값보다 싸다면 현재 주유소만 사용해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/13305">백준 13305번 : 그리디 알고리즘 - 주유소</a>
 * @since 2023-12-29
 */

public class Ten {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] distance = new int[N - 1]; // 도시 간 거리
        int[] price = new int[N]; // 리터당 가격

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < price.length; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        int min = price[0]; // 시작이 일단 최소 가격이다.
        long sum = 0; // sum이 최대 10억 * 10억이 될 수 있기 때문에 long타입 사용

        for (int i = 0; i < N - 1; i++) { // 마지막 도시는 계산할 필요 없기 때문에 N-1 까지
            if (min > price[i]) { // 리터당 가격이 더 싼데를 찾았다면
                min = price[i]; // 최소가격을 갱신해준다.
            }
            sum += (long) distance[i] * min; // 거리와 최소 리터당 가격을 곱해 누적합
        }

        System.out.println(sum);
    }
}
