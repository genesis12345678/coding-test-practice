package Greedy.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아이디어
 * N x M 내에서 정해진 이동 경로를 어떻게 이동할 수 있는지 생각해야 한다.
 * 시작점 포함 이동 횟수 4번 이후로는 다른 이동 방법을 사용해야 하기 때문에 제한은 존재한다.
 * 직접 그리면서 하면 이해가 잘 된다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1783">백준 1783번 : 그리디 알고리즘 - 병든 나이트</a>
 * @since 2024-01-06
 */
public class Eight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int count = 0;

        /**
         * 1. 2칸 위, 1칸 오른쪽
         * 2. 1칸 위, 2칸 오른쪽
         * 3. 1칸 아래, 2칸 오른쪽
         * 4. 2칸 아래, 1칸 오른쪽
         */
        if (N == 1) { // 1이라면 어떤 이동 방법이든 이동 할 수가 없다.
            count = 1;
        } else if (N == 2) { // 2라면 2,3번 방법으로 이동 가능한데 4번은 넘길 수 없다.
            count = Math.min(4, (M + 1) / 2);
        } else if (N >= 3) { // 3부터는 4가지 이동 방법 모두 가능해서 M의 개수로 따져봐야 한다.
            /**
             * M이 7보다 크면 4가지 이동 방법 모두 가능한데 처음에 1,2,3,4 한번씩 사용한 후
             * 최대한 적게 이동시킬 수 있는 1,4번을 계속해서 사용할 경우
             * 처음에 2,3번 사용했을 때 오른쪽으로 1칸씩 건너뛰어서 -2만큼 줄어들게 된다.
             */
            if (M >= 7) {
                count = M - 2;
            } else {
                // 1,4번 으로 3번 이동한 후에는 2,3번을 한번씩은 써야 하는데 공간이 안 된다.
                count = Math.min(4, M);
            }
        }
        System.out.println(count);
    }
}
