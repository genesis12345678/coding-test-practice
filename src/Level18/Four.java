package Level18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href = "https://www.acmicpc.net/problem/2108">백준 2108번 : 심화2 - 통계학</a>
 * @since 2023-12-25
 */
public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 입력 범위 : -4000 ~ 4000
        int[] arr = new int[8001];

        int sum = 0; // 총 합계(평균 구할 때)
        int max = Integer.MIN_VALUE; // 최대값(범위 구할 때)
        int min = Integer.MAX_VALUE; // 최소값(범위 구할 때)
        int middle = max; // 중앙값
        int mode = max; // 최빈값
        // 중앙값과 최빈값은 입력 범위(-4000 ~ 4000)를 제외한 수로 초기화한다.

        for (int i = 0; i < N; i++) {
            /**
             * 카운팅 정렬 방식
             * 입력과 동시에 sum에 누적합을 바로 더해준다.
             * +4000을 하는 이유는 입력이 음수(-)가 왔을 때 에러 방지
             * (입력되는 정수의 절댓값은 4000을 넘지 않는다.)
             */
            int num = Integer.parseInt(br.readLine());
            sum += num;
            arr[num + 4000]++;

            // max과 min값을 각각 구해준다.
            if (max < num) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        /**
         * midIndex : 중앙 인덱스
         * N은 항상 홀수이기 때문에 +1을 해야 정확한 중앙 인덱스를 구할 수 있다.
         * 예를 들어 N = 5면 1, 2, 3, 4, 5 인데 N / 2 = 2가 나온다.
         * +1을 통해 정확한 중간인 3번째 위치를 구할 수 있다.
         */
        int midIndex = N / 2 + 1;
        int count = 0; // 중앙값을 찾기 위한 빈도 누적 수
        int modeMax = 0; // 최빈값의 최대값
        boolean flag = false;

        // 최소 -4000이 올수 있으므로 +4000을 해주어야 한다.
        for (int i = min + 4000; i <= max + 4000; i++) {
            // 위에서 int[8001]로 초기화 한 걸 8000번 반복하는 게 아닌 최소에서 최대만큼만 반복하면 된다.
            // arr[i] = 빈도수, 0이라면 한 번도 나오지 않은 것이므로 신경 쓰지 않는다.
            if (arr[i] > 0) {
                /**
                 * 중앙값 찾기
                 * 빈도 누적 수가 중앙 인덱스보다 작다 == 아직 중앙 위치까지 오지 못했다.
                 * 빈도수를 누적해 간다.
                 * 그러다가 빈도 누적수가 중앙 인덱스를 오버하거나 같아질 경우는 그 위치에 있는 값이 중앙값이 된다.
                 * 처음에 count == midIndex로 해서 실패했는데 원인은 arr[i]==빈도수가 2개 이상 일수도 있어 midIndex를 뛰어 넘게 된다.
                 * 처음에 카운팅 정렬할 때 인덱스 범위 에러 방지를 위해 +4000을 했으므로
                 * -4000을 해야 입력받은 수를 구할 수 있다.
                 */
                if (count < midIndex) {
                    count += arr[i];
                    if (count >= midIndex) {
                        middle = i - 4000;
                    }
                }
                /**
                 * 최빈값 찾기
                 * 이 반복문은 min에서 max로 순회하니까 크기 오름차순으로 순회한다.
                 * 즉, 처음 나온 최대 최빈값은 가장 작은 최빈값이고
                 * 같은 최대 최빈값이 있다면 두 번째로 작은 최빈값을 찾아야 하는데
                 * 가장 작은 최빈값에 다음으로 찾은 같은 최대 최빈값이 두 번째로 작은 최빈값이 된다.
                 * 그 이후에 나오는 같은 최대 최빈값은 무시해도 된다.
                 */
                if(modeMax < arr[i]){
                    modeMax = arr[i];
                    mode = i - 4000;
                    flag = true; // 첫 등장을 의미하는 true로 변경한다.
                }
                /**
                 * 이전 최대최빈값과 빈도수가 동일하다 == 최대 최빈값이 2개 이상이다.
                 * 그와 동시에 flag == true면 처음 등장한 최대최빈값에 다음으로 큰 수 == 두번째로 작은 최대최빈값이 된다.
                 * 다시 flag를 false로 해야 이후에 나오는 같은 최대최빈값을 무시할 수 있다.
                 */
                else if (modeMax == arr[i] && flag) {
                    mode = i - 4000;
                    flag = false;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        /**
         * 평균의 경우 문제에서 첫째 자리에서 반올림을 하라했다.
         * 그냥 sum / N 을 하면 소수점 이하는 버려지게 된다.
         * Math.round는
         */
        sb.append((int) Math.round((double) sum / N)).append("\n");
        sb.append(middle).append("\n");
        sb.append(mode).append("\n");
        sb.append(max - min).append("\n");
        System.out.println(sb);
    }

}
