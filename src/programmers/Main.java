package programmers;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {
                {2, 5, 3},
                {4, 4, 1},
                {1, 7, 3}
        };

        int[] result = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int s = commands[i][0];
            int e = commands[i][1];
            int k = commands[i][2];

            int[] temp = new int[e - s + 1];
            int index = 0;
            for (int j = s - 1; j < e; j++) {
                temp[index++] = array[j];
            }

            Arrays.sort(temp);
            result[i] = temp[k - 1];
        }

        System.out.println("result = " + Arrays.toString(result));
    }
}
