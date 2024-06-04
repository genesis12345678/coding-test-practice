import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, check(i));
        }

        System.out.print(max);
    }

    private static int check(int num) {
        int count = 0;
        double highest = 0;

        for (int i = num - 1; i >= 0; i--) {
            double temp = (double) (arr[num] - arr[i]) / (num - i);

            if (i == num - 1 || temp < highest) {
                count++;
                highest = temp;
            }
        }

        for (int i = num + 1; i < n; i++) {
            double temp = (double) (arr[num] - arr[i]) / (num - i);

            if (i == num + 1 || temp > highest) {
                count++;
                highest = temp;
            }
        }

        return count;
    }
}
