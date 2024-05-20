import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static ArrayList<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n <= 10) {
            System.out.println(n);
            return;
        }
        if (n >= 1023) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            backTrack(i);
        }

        Collections.sort(list);
        System.out.println(list.get(n));
    }

    static void backTrack(long num) {
        list.add(num);

        for (int i = 0; i < num % 10; i++) {
            backTrack(num * 10 + i);
        }
    }
}
