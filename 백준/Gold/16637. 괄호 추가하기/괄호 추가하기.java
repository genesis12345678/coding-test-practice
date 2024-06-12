import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static ArrayList<Character> operations = new ArrayList<>();
    static ArrayList<Integer> numbers = new ArrayList<>();
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                numbers.add(input.charAt(i) - '0');
            } else {
                operations.add(input.charAt(i));
            }
        }

        dfs(0, numbers.get(0));
        System.out.println(max);

    }

    private static void dfs(int index, int result) {
        if (index >= operations.size()) {
            max = Math.max(max, result);
            return;
        }

        dfs(index + 1, calculate(operations.get(index), result, numbers.get(index + 1)));

        if (index + 1 < operations.size()) {
            int temp = calculate(operations.get(index + 1), numbers.get(index + 1), numbers.get(index + 2));
            dfs(index + 2, calculate(operations.get(index), result, temp));
        }
    }

    static int calculate(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }
}
