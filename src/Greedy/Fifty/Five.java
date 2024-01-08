package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 아이디어
 * 무거운 무게부터 처리하기 위해 박스와 크레인 모두 내림차순 정렬한다.
 * 박스를 다 옮길 때까지 크레인이 몇번 움직이는지 계산한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1092">백준 1092번 : 그리디 알고리즘 - 배</a>
 * @since 2024-01-08
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

//        Integer[] crane = new Integer[N];

//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            crane[i] = Integer.parseInt(st.nextToken());
//        }
        Integer[] crane = Arrays.stream(br.readLine().split(" "))
                                .map(Integer::parseInt)
                                .toArray(Integer[]::new);

        int M = Integer.parseInt(br.readLine());
//        Integer[] box = new Integer[M];

//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < M; i++) {
//            box[i] = Integer.parseInt(st.nextToken());
//        }
        Integer[] box = Arrays.stream(br.readLine().split(" "))
                              .map(Integer::parseInt)
                              .toArray(Integer[]::new);

        Arrays.sort(crane, Collections.reverseOrder());
        Arrays.sort(box, Collections.reverseOrder());

        /**
         * 박스의 최대 무게가 크레인의 최대 무게보다 무겁다면
         * 모든 박스를 옮길 수 없으므로 -1 출력
         */
        if (crane[0] < box[0]) {
            System.out.println(-1);
            return;
        }
        int count = 0;

        /**
         * int[] cranePosition = new int[N]; -> 이것이 시간을 줄일 수 있는 핵심이다.
         * 아래 주석처리로 실행해도 동작은 문제 없으나 시간 초과가 발생한다.
         * visited로 방문처리를 해도 탐색해봤자 어차피 해당 크레인이 들지 못하는(박스가 더 무거운) 박스를 탐색하여 불필요한 탐색을 하게 된다.
         * 그래서 크레인과 박스 모두 정렬은 이미 돼있으니까 각 크레인이 들지 못하는 박스는 아예 탐색을 하지 않게 범위를 좁혀준다.
         */
        int[] cranePosition = new int[N];
        boolean[] visited = new boolean[M];
        while (M > 0) {
            for (int i = 0; i < N; i++) {
                if (M == 0) break;
                int max = crane[i];

                for (int j = cranePosition[i]; j < box.length; j++) {
                    if(visited[j]) continue;
                    if (max >= box[j]) {
                        visited[j] = true;
                        M--;
                        break;
                    } else {
                        cranePosition[i]++;
                    }
                }

                // 시간 초과
//                for (int j = 0; j < box.length; j++) {
//                    if(visited[j]) continue;
//                    if (max >= box[j]) {
//                        visited[j] = true;
//                        M--;
//                        break;
//                    }
//                }
            }
            count++;
        }
        System.out.println(count);
    }
}
