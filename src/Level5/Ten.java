package Level5;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Ten {
    public static void main(String[] args) throws IOException {
        Map<Character, Integer> dial = new HashMap<>();

        for (char i = 'A'; i <= 'Z'; i++) {
            switch (i) {
                case 'A': case 'B': case 'C':
                    dial.put(i, 3);
                    break;
                case 'D': case 'E': case 'F':
                    dial.put(i, 4);
                    break;
                case 'G': case 'H': case 'I':
                    dial.put(i, 5);
                    break;
                case 'J': case 'K': case 'L':
                    dial.put(i, 6);
                    break;
                case 'M': case 'N': case 'O':
                    dial.put(i, 7);
                    break;
                case 'P': case 'R': case 'Q': case 'S':
                    dial.put(i, 8);
                    break;
                case 'T': case 'U': case 'V':
                    dial.put(i, 9);
                    break;
                default:
                    dial.put(i, 10);
                    break;
            }
        }

        int count = 0;

        while (true) {
            char read = (char)System.in.read();

            if (read < 'A') {
                break;
            }

            count += dial.get(read);
        }

        System.out.println(count);
    }
}
