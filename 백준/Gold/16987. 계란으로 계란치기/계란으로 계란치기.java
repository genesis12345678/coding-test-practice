import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

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

        if (!broken) {
            dfs(index + 1);
        }
    }

    static class Egg{
        int s, w;

        public Egg(int s, int w) {
            this.s = s;
            this.w = w;
        }
    }
}
