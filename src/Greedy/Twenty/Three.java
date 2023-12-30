package Greedy.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 아이디어
 * 최소의 비교를 위해서 작은 수 2개를 선택해 합을 구한 뒤
 * 더해진 값을 포함하여 남아있는 수들 중에서 다시 작은 수 2개를 선택해서 합을 구한다.
 * 이 과정을 반복해야 하는데 일반 배열로 하기에는 너무 오랜 시간이 걸릴 것이다.
 * 그래서 자동으로 정렬이 되는 우선순위 큐를 이용한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1715">백준 1715번 : 그리디 알고리즘 - 카드 정렬하기</a>
 * @since 2023-12-30
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // default로 최소힙을 사용한다.
        // 최대힙을 쓰고 싶다면 -> Queue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> que = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            que.add(Integer.parseInt(br.readLine()));
        }

        int sum = 0;

        while (que.size() > 1) {
            int a = que.poll(); // 데이터를 빼면 자동으로 다음으로 작은 값이 제일 위로 올라온다.(첫번째로 작은 값)
            int b = que.poll(); // 두번째로 작은 값
            sum += a + b;
            que.add(a + b); // 가장 적은 묶음 2개를 다시 우선순위 큐에 넣어 재정렬을 해준다.
        }
        System.out.println(sum);
    }
}
