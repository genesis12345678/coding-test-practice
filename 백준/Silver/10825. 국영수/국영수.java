import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            students[i] = new Student(name, korean, english, math);
        }

        Arrays.stream(students)
                .sorted()
                .map(Student::getName)
                .forEach(System.out::println);
    }

    static class Student implements Comparable<Student>{
        String name;
        int korean, english, math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Student o) {
            if (this.korean == o.korean && this.english == o.english && this.math == o.math) {
                return this.name.compareTo(o.name);
            }
            if (this.korean == o.korean && this.english == o.english) {
                return o.math - this.math;
            }
            if (this.korean == o.korean) {
                return this.english - o.english;
            }

            return o.korean - this.korean;
        }
    }
}
