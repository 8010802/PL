import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        // Проверяем наличие аргумента командной строки (путь к файлу)
        if (args.length < 1) {
            System.out.println("Необходимо указать путь к файлу в аргументах командной строки.\n" +
                    "java -classpath ./src/task4 Task4 ./src/task4/task4.txt\n");
            return;
        }

        String filePath = args[0]; // Получаем путь к файлу из аргументов командной строки

        try {
            // Читаем содержимое файла
            int[] nums = readIntArrayFromFile(filePath);

            // Сортируем массив
            Arrays.sort(nums);

            // Вычисляем медиану
            int median = nums[nums.length / 2];

            // Вычисляем минимальное количество ходов
            int minMoves = 0;
            for (int num : nums) {
                minMoves += Math.abs(num - median);
            }

            // Выводим результат
            System.out.println("Минимальное количество ходов: " + (minMoves - 1));
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private static int[] readIntArrayFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int size = 0;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                size++;
            }
        }
        reader.close();

        int[] nums = new int[size];
        reader = new BufferedReader(new FileReader(filePath));
        int index = 0;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                nums[index] = Integer.parseInt(line);
                index++;
            }
        }
        reader.close();

        return nums;
    }
}
