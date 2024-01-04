package Greedy.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 아이디어
 * 멀티탭이 모두 사용중일 때 가장 나중에 사용될 콘센트를 찾아 제거해야 한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1700">백준 1700번 : 그리디 알고리즘 - 멀티탭 스케줄링</a>
 * @since 2024-01-04
 */
public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;

        // 멀티탭에 꽂혀있는 전기용품이다. Set이기 때문에 이미 꽂혀있는 용품은 알아서 스킵한다.
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < K; i++) {
            /**
             * 멀티탭에 꽂혀있지 않으면서 멀티탭이 꽉 찼을 경우
             * 새로운 전기용품을 꽂기 위해 하나를 빼야 한다.
             */
            if (!set.contains(arr[i]) && set.size() >= N) {
                List<Integer> list = new ArrayList<>(); // 현재 꽂혀 있는 것들 중 나중에 재사용될 전기용품들
                Set<Integer> remain = new HashSet<>(set);
                // 현재 꽂혀 있는 Set이랑 똑같은데
                // 밑 for문을 진행하면 앞으로 사용하지 않을 전기용품만 남는다.

                // 남은 전기용품들을 확인한다.
                for (int j = i; j < K; j++) {
                    /**
                     * 멀티탭에 이미 꽂혀있다. == 재사용되는 전기용품이다. == 빼면 안 된다. -> list에 추가한다.
                     * Set을 써버리면 순서가 보장이 안 된다.
                     * remain.remove()를 통해 remain에는 앞으로 사용되지 않을 전기용품만 남는다.
                     */
                    if (set.contains(arr[j]) && !list.contains(arr[j])) {
                        list.add(arr[j]);
                        remain.remove(arr[j]);
                    }
                }

                if (list.size() == N) { // 재사용될 전기용품들이 모두 사용중이다.
                    set.remove(list.get(list.size() - 1)); // 가장 마지막으로 사용될 전기용품을 제거한다.
                } else { // 모두 사용중이 아니라면 remain에 남아있는 것들 중 아무거나 하나 제거해도 상관 없다.
                    List<Integer> temp = new ArrayList<>(remain);
                    set.remove(temp.get(0));
                }
                count++;
            }

            set.add(arr[i]);
        }

        System.out.println(count);
    }
}

