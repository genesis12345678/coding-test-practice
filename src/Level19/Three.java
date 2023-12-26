package Level19;

import java.io.*;

public class Three {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            count = 0; // 각 테스트마다 count = 0 초기화
            String s = br.readLine();
            bw.write(isPalindrome(s) + " " + count + "\n");
        }
        bw.flush();
    }

    static int isPalindrome(String s) {
        /**
         * 0 = 시작 문자
         * s.length() - 1 = 끝 문자
         */
        return recursion(s, 0, s.length() - 1);
    }

    static int recursion(String s, int left, int right) {
        count++; // 이 함수를 실행할 때마다 호출횟수 증가
        /**
         * left가 right보다 크거나 같다면, 중간에 올 때까지 문자가 같았다는 뜻이므로
         * 팰린드롬 문자열이니까 return 1
         */
        if(left >= right) return 1;
        /**
         * left와 right의 문자가 서로 다르다 == 대칭하지 못하다
         * 즉 팰린드롬 문자열이 아니니까 return 0
         */
        else if(s.charAt(left) != s.charAt(right)) return 0;

        // left와 right의 범위를 좁혀가면서 문자가 서로 대칭되는지 확인한다.
        else return recursion(s, left + 1, right - 1);
    }
}
