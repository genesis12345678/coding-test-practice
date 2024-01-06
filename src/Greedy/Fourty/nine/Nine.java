package Greedy.Fourty.nine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 아이디어
 * 유니온 파인드 알고리즘을 사용해 1 ~ g 까지의 범위를 좁혀나간다.
 */

/**
 * <a href = "https://www.acmicpc.net/problem/10775">백준 10775번 : 그리디 알고리즘 - 공항</a>
 * @since 2024-01-06
 */
public class Nine {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int count = 0;

        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());

            int a = find(g);
            /**
             * 차선책이 0이라면 더 이상 도킹이 불가능하다.
             */
            if (a == 0) {
                break;
            }
            count++;
            /**
             * -1한 값이랑 집합을 하여 해당 게이트에 대한 차선책을 저장해 둔다.
             */
            union(a, a - 1);
        }

        System.out.println(count);
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        parent[a] = find(parent[a]);
        return parent[a];
    }

    static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
}
