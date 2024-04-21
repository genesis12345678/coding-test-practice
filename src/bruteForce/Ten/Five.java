package bruteForce.Ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[9];

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i != j) {
                    if (sum - arr[i] - arr[j] == 100) {
                        arr[i] = arr[j] = 0;
                        Arrays.sort(arr);

                        for (int k = 2; k < 9; k++) {
                            System.out.println(arr[k]);
                        }

                        return;
                    }
                }
            }
        }
    }
}
