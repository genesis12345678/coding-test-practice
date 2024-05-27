package programmers.practice;

import java.util.Arrays;

public class FileSort {
    public static void main(String[] args) {
        String[] files1 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};

        String[] result1 = solution(files1);
        String[] result2 = solution(files2);

        System.out.println("result1 = " + Arrays.toString(result1));
        System.out.println("result2 = " + Arrays.toString(result2));
    }

    private static String[] solution(String[] files) {

        Filename[] filenames = new Filename[files.length];

        for (int i = 0; i < files.length; i++) {
            String[] split = split(files[i]);
            filenames[i] = new Filename(files[i], split[0], Integer.parseInt(split[1]));
        }

        Arrays.sort(filenames);

        String[] result = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            result[i] = filenames[i].filename;
        }

        return result;
    }

    private static String[] split(String file) {
        String[] arr = new String[3];
        int index = 0;

        while (index < file.length() && !Character.isDigit(file.charAt(index))) {
            index++;
        }
        arr[0] = file.substring(0, index).toLowerCase();

        int temp = index;
        while (index < file.length() && Character.isDigit(file.charAt(index))) {
            index++;
        }

        arr[1] = file.substring(temp, index);
        arr[2] = file.substring(index);
        return arr;
    }

    static class Filename implements Comparable<Filename> {

        String filename;
        String head;
        int number;

        public Filename(String filename, String head, int number) {
            this.filename = filename;
            this.head = head;
            this.number = number;
        }

        @Override
        public int compareTo(Filename o) {
            if (this.head.equals(o.head)) {
                return this.number - o.number;
            }

            return this.head.compareTo(o.head);
        }
    }

}
