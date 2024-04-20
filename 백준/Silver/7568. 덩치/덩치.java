import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Person[] people = new Person[n];
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            people[i] = new Person(w, h);
        }

        for (int i = 0; i < n; i++) {
            int rank = 1;

            for (int j = 0; j < n; j++) {

                if (i != j) {
                    Person p1 = people[i];
                    Person p2 = people[j];

                    if (p1.weight < p2.weight && p1.height < p2.height) {
                        rank++;
                    }
                }

            }

            result[i] = rank;
        }

        for (int i : result) {
            System.out.print(i + " ");
        }

    }

    static class Person{
        int weight, height;

        public Person(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }
    }
}
