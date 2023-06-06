package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CirclePosition {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо указать имена двух файлов в терминале.\n " +
                    "java -classpath src task2.CirclePosition src/task2/circle.txt src/task2/points.txt");
            return;
        }

//        java -classpath src task2.CirclePosition src/task2/circle.txt src/task2/points.txt


        String circleFile = args[0];
        String pointsFile = args[1];

        try {
            // Чтение данных о круге из файла
            Scanner circleScanner = new Scanner(new File(circleFile));
            float centerX = circleScanner.nextFloat();
            float centerY = circleScanner.nextFloat();
            float radius = circleScanner.nextFloat();
            circleScanner.close();

            // Чтение данных о точках из файла
            Scanner pointsScanner = new Scanner(new File(pointsFile));

            while (pointsScanner.hasNextFloat()) {
                float x = pointsScanner.nextFloat();
                float y = pointsScanner.nextFloat();
                int position = calculatePosition(centerX, centerY, radius, x, y);
                if (position == 0) {
                    System.out.println(position);
                }
            }

            pointsScanner.close();
            pointsScanner = new Scanner(new File(pointsFile));

            while (pointsScanner.hasNextFloat()) {
                float x = pointsScanner.nextFloat();
                float y = pointsScanner.nextFloat();
                int position = calculatePosition(centerX, centerY, radius, x, y);
                if (position == 1) {
                    System.out.println(position);
                }
            }

            pointsScanner.close();
            pointsScanner = new Scanner(new File(pointsFile));

            while (pointsScanner.hasNextFloat()) {
                float x = pointsScanner.nextFloat();
                float y = pointsScanner.nextFloat();
                int position = calculatePosition(centerX, centerY, radius, x, y);
                if (position == 2) {
                    System.out.println(position);
                }
            }

            pointsScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла.");
            e.printStackTrace();
        }
    }

    // Метод для определения положения точки относительно окружности
    private static int calculatePosition(float centerX, float centerY, float radius, float x, float y) {
        float distanceSquared = (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY);
        float radiusSquared = radius * radius;
        if (distanceSquared < radiusSquared) {
            return 1; // Точка внутри окружности
        } else if (distanceSquared > radiusSquared) {
            return 2; // Точка снаружи окружности
        } else {
            return 0; // Точка лежит на окружности
        }
    }
}
