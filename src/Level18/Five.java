package Level18;

import java.io.*;
import java.util.*;

/**
 * <a href = "https://www.acmicpc.net/problem/20920">백준 20920번 : 심화2 - 영단어 암기는 괴로워</a>
 * @since 2023-12-25
 */
public class Five {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        /**
         * 길이가 M이상만 map에 저장한다.
         * key는 입력값이고, value는 중복단어면 나올때마다 +1
         * 처음 나오는 단어면 1
         */
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (s.length() >= M) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        // map에서 key들(입력값 == 단어들)을 가져와서 list에 저장
        List<String> list = new ArrayList<>(map.keySet());

       // list에 단어들을 정렬한다.
        Collections.sort(list, (o1, o2) -> {
            /**
             * 만약 두 개의 value를 비교해서 0이 아니다 == value가 다르다 == 빈도가 다르다
             * compare(A,B) -> A가 B보다 작으면 음수, 같으면 0, 크면 양수
             * 빈도수 내림차순으로 정렬됨.
             * 만약 오름차순으로 하고싶다면 o2와 o1의 순서를 바꾸면 된다.
            */
            int freq = Integer.compare(map.get(o2), map.get(o1));
            if (freq != 0) {
                return freq;
            /**
             * freq가 0(== 빈도가 같다), 다음 조건으로 넘어온다.
             * 길이가 같다면 길이를 비교한다.
             * 길이 내림차순으로 정렬됨.
             * 만약 오름차순으로 하고싶다면 순서만 바꾸면 된다.
             */
            } else if (o1.length() != o2.length()) {
                return o2.length() - o1.length();
                /**
                 * 빈도도 같고, 길이가 같다면 사전순으로 정렬한다.
                 * compareTo는 유니코드 포인트 순서로 비교한다.
                 * o1이 o2보다 사전순으로 앞에 있다면 음수를 반환 == o1이 사전순 앞에 위치한다.
                 * 사전순 오름차순으로 정렬한다.
                 * 마찬가지로 내림차순으로 하고싶다면 o1과 o2의 위치를 바꾸면 된다.
                 */
            }else
                return o1.compareTo(o2);
        });

        for (String s : list) {
            bw.write(s + "\n");
        }
        bw.flush();
        bw.close();
    }
}
