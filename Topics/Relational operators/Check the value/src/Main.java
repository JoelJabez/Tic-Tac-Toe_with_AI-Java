import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        System.out.println(checkRange(number));
    }

    private static boolean checkRange(int n) {
        return 10 > n && n > 0;
    }
}