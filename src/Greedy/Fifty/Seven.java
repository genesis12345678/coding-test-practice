package Greedy.Fifty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 아이디어
 * NxNxN크기의 정육면체에서 3면, 2면, 1면이 보이는 주사위의 개수를 구한다.
 * 3면이 보이는 주사위 : 4개 (바닥은 안보이므로 위에 꼭짓점 4개)
 * 2면이 보이는 주사위 : (위) 4(N-1) + (옆4개) 4(N-1) = 4(N-1) + 4(N-2) = 8N - 12개
 * 1면이 보이는 주사위 : (위) (N-2)^2 + (옆4개) 4((N-2)(N-1)) = (N-2)(N-2) + 4((N-2)(N-1)) = 5N^2 - 16N + 12개
 * A와 F, E와 B, C와 D 중 작은 수 3개를 골라 정렬한다.(이 3개는 항상 인접한다.)
 * 정렬한 첫번째  : 1면의 최솟값
 * 1면의 최솟값 + 정렬한 두번째 : 2면의 최솟값
 * 2면의 최솟값 + 정렬한 세번째 : 3면의 최솟값
 * 각각 주사위의 개수만큼 곱해주고 더한다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/1041">백준 1041번 : 그리디 알고리즘 - 주사위</a>
 * @since 2024-01-09
 */
public class Seven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 주사위 6개
        int[] arr = Arrays.stream(br.readLine().split(" "))
                          .mapToInt(Integer::parseInt)
                          .toArray();

        int[] min = new int[3]; // 항상 인접하는 3면의 각 값이 저장될 배열

        min[0] = Math.min(arr[0], arr[5]); // A와 F
        min[1] = Math.min(arr[1], arr[4]); // E와 B
        min[2] = Math.min(arr[2], arr[3]); // C와 D

        Arrays.sort(min);

        min[1] += min[0]; // 1면의 최솟값 + 정렬한 두번째
        min[2] += min[1]; // 2면의 최솟값 + 정렬한 세번째

                            // 주사위가 한개라면 가장 큰 값이 안 보이게 하면 된다.
        long count = N == 1 ? Arrays.stream(arr).sum() - Arrays.stream(arr).max().getAsInt()
                            : 4L * min[2] + // 3면이 보이는 주사위
                            (8L * N - 12) * min[1] + // 2면이 보이는 주사위
                            (5L * N * N - (16L * N) + 12) * min[0]; // 1면이 보이는 주사위

        System.out.println(count);
    }
}
