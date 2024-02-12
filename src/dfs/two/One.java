package dfs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 아이디어
 * 1부터 시작해서 마지막에 자신의 숫자를 호출하는 싸이클이 발생하는 숫자를 골라야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/2668">백준 2668번 : 깊이우선탐색 - 숫자고르기</a>
 * @since 2024-02-12
 */
public class One {

    static int[] arr;
    static boolean[] visit;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        visit = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //i로 시작해서 i를 만날 수 있는지 확인한다.
        for (int i = 1; i <= N; i++) {
            visit[i] = true;//시작 방문 처리
            dfs(i, i);
            visit[i] = false;//다음 탐색에 영향이 가지 않게 초기화
        }

        StringBuilder sb = new StringBuilder();
        //첫째 줄에 뽑힌 정수들의 개수 출력
        sb.append(list.size()).append("\n");

        //뽑힌 정수들을 오름차순으로 출력
        list.stream()
            .sorted()
            .forEach(n -> sb.append(n).append("\n"));

        System.out.println(sb);
    }

    static void dfs(int start, int target) {
        if (!visit[arr[start]]) {
            visit[arr[start]] = true;
            dfs(arr[start], target);
        }
        //싸이클이 발생한 경우 결과 리스트에 저장한다.
        if (arr[start] == target) {
            list.add(target);
        }

        visit[arr[start]] = false;//다음 탐색에 영향이 가지 않게 초기화
    }
}
