import java.util.Scanner;

public class CircularArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение n: ");
        int n = scanner.nextInt();
        System.out.print("Введите значение m: ");
        int m = scanner.nextInt();

        // Решение задачи
        int[] path = findPath(n, m);

        // Вывод пути
        System.out.print("Полученный путь: ");
        for (int i = 0; i < n; i++) {
            System.out.print(path[i]);
        }
        System.out.println();
    }

    // Метод для нахождения пути по круговому массиву
    private static int[] findPath(int n, int m) {
        int[] path = new int[n];
        int currentIndex = 0;
        int count = 0;
        while (count < n) {
            path[count] = currentIndex + 1;
            currentIndex = (currentIndex + m - 1) % n;
            count++;
        }
        return path;
    }
}
