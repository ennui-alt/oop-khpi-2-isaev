import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть кількість рядків: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] lines = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Введіть рядок " + (i + 1) + ": ");
            lines[i] = sc.nextLine();
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += lines[i].length();
        }

        double avg = (double) sum / n;

        System.out.println("\nСередня довжина: " + avg);

        System.out.println("\nРядки з довжиною МЕНШЕ середньої:");
        for (int i = 0; i < n; i++) {
            if (lines[i].length() < avg) {
                System.out.println(lines[i] + " (довжина: " + lines[i].length() + ")");
            }
        }

        System.out.println("\nРядки з довжиною НЕ МЕНШЕ середньої:");
        for (int i = 0; i < n; i++) {
            if (lines[i].length() >= avg) {
                System.out.println(lines[i] + " (довжина: " + lines[i].length() + ")");
            }
        }

        sc.close();
    }
}