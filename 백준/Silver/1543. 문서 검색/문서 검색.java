import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String doc = br.readLine();
        String search = br.readLine();

        int count = 0;
        for (int i = 0; i <= doc.length() - search.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < search.length(); j++) {
                if (search.charAt(j) != doc.charAt(i + j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                i += search.length() - 1;
                count++;
            }
        }

        System.out.println(count);
    }
}
