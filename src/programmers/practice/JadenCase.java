package programmers.practice;

public class JadenCase {
    public static void main(String[] args) {
        String s = "3people unFollowed me";

        System.out.println(solution(s));
    }

    private static String solution(String s) {

        StringBuilder sb = new StringBuilder();

        String[] arr = s.split(" ");

        for (String str : arr) {

            if (str.isEmpty()) {
                sb.append(" ");
                continue;
            }

            sb.append(str.substring(0, 1).toUpperCase());
            sb.append(str.substring(1).toLowerCase());
            sb.append(" ");
        }

        if (s.endsWith(" ")) {
            return sb.toString();
        }

        return sb.substring(0, sb.length() - 1);
    }
}
