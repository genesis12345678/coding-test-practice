package Greedy.Twenty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 일단 각 가방에 넣을 수 있는 만큼 넣고 그 가방에서 최대가격의 보석만 넣는다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1202">백준 1202번 : 그리디 알고리즘 - 보석 도둑</a>
 * @since 2024-01-01
 */
public class Nine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        // 최고 가격을 꺼내야 하기 때문에 최대힙으로 생성해준다.
        Queue<Integer> qu = new PriorityQueue<>(Collections.reverseOrder());

        int[][] jewels = new int[N][2]; // 각 보석의 무게(M)와 가격(V) 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 무게
            int V = Integer.parseInt(st.nextToken()); // 가격
            jewels[i][0] = M;
            jewels[i][1] = V;
        }

        Arrays.sort(jewels, Comparator.comparingInt(o -> o[0])); // 무게 오름차순 정렬

        int[] bags = new int[K]; // 각 가방에 담길 수 있는 최대 무게 정보
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags); // 최대 무게 오름차순 정렬

        int jewelsIdx = 0;
        long sum = 0;
        /**
         * 최대 가격의 합이 300,000 x 1,000,000 = 3000억 이므로 int의 범위(약 21억)을 벗어난다. 그래서 long을 사용한다.
         * 보석의 무게와 가방의 최대 무게가 오름차순 정렬되어 있어 가방에 넣을 수 있는 보석을 빠르게 찾을 수 있다.
         * 각 가방에는 한 개의 보석만 들어갈 수 있으므로 jewelsIdx를 0으로 초기화하지 않고 계속 이어나간다.
         */
        for (int bag : bags) {
            while (jewelsIdx < jewels.length && bag >= jewels[jewelsIdx][0]) {
                qu.offer(jewels[jewelsIdx++][1]);
            }
            if (!qu.isEmpty()) {
                sum += qu.poll();
            }
        }
        System.out.println(sum);
    }
}


