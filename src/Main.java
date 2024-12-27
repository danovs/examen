import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // 1 задание - исключения
        // checkExceptions();

        // 2 задание - чтение файла, вывод кол-ва строк, вывод строк с макс. длинной
        Scanner scanner = new Scanner(System.in);

        // Просим пользователя ввести путь
        System.out.println("Введите путь:");

        // Читаем строку, введенную пользователем
        String input = scanner.nextLine();

        try {
            // Вызываем метод readFile, чтобы прочитать содержимое файла
            List<String> lines = readFile(input);

            // выводим количество строк в файле
            System.out.println("Количество строк в файле: " + lines.size());

            // выводим строки с максимальной длиной
            // находим макс длину
            int maxLength = 0;
            for (String line : lines)
            {
                if (line.length() > maxLength)
                {
                    maxLength = line.length();
                }
            }

            // Выводим строки, длина которых равна максимальной
            System.out.println("Строки с максимальной длиной (" + maxLength + " символов):");
            for (String line : lines)
            {
                if (line.length() == maxLength)
                {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            // Обрабатываем исключения, если они произошли
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            // Закрываем scanner
            scanner.close();
        }
    }

    private static void checkExceptions()
    {
        try
        {
            System.out.println(1/0);
        }
        catch (ArithmeticException e)
        {
            throw new ArithmeticException("Делить на 0 нельзя!");
        }
    }


    public static List<String> readFile(String filePath) throws IOException
    {
        // Проверяем существование файла
        Path path = Paths.get(filePath);
        if (!Files.exists(path))
        {
            throw new FileNotFoundException("Файл не найден: " + filePath);
        }

        // Проверяем доступность файла для чтения
        if (!Files.isReadable(path))
        {
            throw new IOException("Нет доступа для чтения файла: " + filePath);
        }

        // Считываем строки из файла
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
        }
        // в случае ошибок
        catch (IOException e)
        {
            throw new IOException("Ошибка при чтении файла: " + filePath, e);
        }

        return lines;
    }
}