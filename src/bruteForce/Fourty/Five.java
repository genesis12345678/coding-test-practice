package bruteForce.Fourty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/16987">백준 16987번 - 브루트포스 : 계란으로 계란치기</a>
 * @since 2024-05-17
 */
public class Five {

    static int n;
    static Egg[] eggs;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        eggs = new Egg[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            eggs[i] = new Egg(s, w);
        }

        dfs(0);

        System.out.println(count);
    }

    static void dfs(int index) {

        //모든 계란에 대해 탐색했을 때 깨진 계란 개수 최댓값 갱신
        if (index == n) {
            int temp = 0;
            for (Egg egg : eggs) {
                if (egg.s <= 0) {
                    temp++;
                }
            }
            count = Math.max(temp, count);
            return;
        }

        //손에 든 계란이 깨지면 넘어가기
        if (eggs[index].s <= 0) {
            dfs(index + 1);
            return;
        }

        boolean broken = false;
        for (int i = 0; i < n; i++) {
            if (i != index && eggs[i].s > 0) {
                broken = true;
                eggs[index].s -= eggs[i].w;
                eggs[i].s -= eggs[index].w;

                dfs(index + 1);

                eggs[index].s += eggs[i].w;
                eggs[i].s += eggs[index].w;
            }
        }

        //깨지지 않은 계란이 없으면 넘어가기
        if (!broken) {
            dfs(index + 1);
        }
    }

    static class Egg{
        int s, w;

        public Egg(int s, int w) {
            this.s = s; //내구도
            this.w = w; //무게
        }
    }
}
