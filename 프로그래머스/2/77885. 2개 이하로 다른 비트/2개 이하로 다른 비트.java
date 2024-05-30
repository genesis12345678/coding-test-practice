import java.util.Arrays;

class Solution {
    public long[] solution(long[] numbers) {
        long[] result = new long[numbers.length];
        System.arraycopy(numbers, 0, result, 0, result.length);

        for (int i = 0; i < result.length; i++) {
            result[i]++;
            result[i] += (result[i] ^ numbers[i]) >> 2;
        }

        return result;
    }
}