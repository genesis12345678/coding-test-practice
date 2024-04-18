package programmers.DfsBfs;

public class TargetNumber {

    static int sum = 0;
    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;

        dfs(numbers, 0, target, 0);

        System.out.println(sum);
    }

    private static void dfs(int[] numbers, int depth, int target, int result) {
        if (depth == numbers.length) {
            if (result == target) {
                sum++;
            }
            return;
        }

        dfs(numbers, depth + 1, target, result + numbers[depth]);
        dfs(numbers, depth + 1, target, result - numbers[depth]);
    }
}
