class Solution {
    public int[] solution(int[] sequence, int k) {
        int start = 0;
        int end = 0;

        int sum = 0;
        int len = Integer.MAX_VALUE;

        int[] result = new int[2];
        result[0] = Integer.MAX_VALUE;
        result[1] = Integer.MAX_VALUE;

        while (end < sequence.length) {

            sum += sequence[end];

            while (sum > k && start <= end) {
                sum -= sequence[start++];
            }

            if (sum == k) {
                if ((end - start + 1) < len) {
                    len = end - start + 1;
                    result[0] = start;
                    result[1] = end;
                } else if ((end - start + 1) == len && result[0] > start) {
                    result[0] = start;
                    result[1] = end;
                }
            }
            end++;
        }

        return result;
    }
}