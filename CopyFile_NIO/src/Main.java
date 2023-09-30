import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] file_1 = {"C:\\Users\\Chekesh\\Desktop\\Системка.txt", "C:\\Users\\Chekesh\\Desktop\\копия сис.txt"};
        String[] file_2 = {"C:\\Users\\Chekesh\\Desktop\\Параллелизм.txt", "C:\\Users\\Chekesh\\Desktop\\копия пар.txt"};

        long startTime = System.currentTimeMillis();
        oneThread(file_1);
        oneThread(file_2);
        long endTime = System.currentTimeMillis();
        System.out.println("С одним потоком: " + (endTime-startTime));

        long startTwoTime = System.currentTimeMillis();
        twoThread(file_1);
        twoThread(file_2);
        long endTwoTime = System.currentTimeMillis();
        System.out.println("С двумя потоками: " + (endTwoTime-startTwoTime));

        open(file_1);


    }
    static void twoThread(String[] file) {
        Thread thread = new Thread(() -> {
            oneThread(file);

        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void oneThread(String[] file) {
        Path pathRead = Path.of(file[0]);
        Path pathWrite = Path.of(file[1]);

        List<String> line;
        try {
            line = Files.readAllLines(pathRead);
            Files.write(pathWrite, line, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void open(String[] file) {
        try {
            ProcessBuilder prok = new ProcessBuilder("notepad.exe", "Системка");
            prok.directory(new File("C:\\Users\\Chekesh\\Desktop"));
            prok.start();
            ProcessBuilder prok_2 = new ProcessBuilder("notepad.exe", "копия сис");
            prok_2.directory(new File("C:\\Users\\Chekesh\\Desktop"));
            prok_2.start();
            ProcessBuilder prok_3 = new ProcessBuilder("notepad.exe", "Параллелизм");
            prok_3.directory(new File("C:\\Users\\Chekesh\\Desktop"));
            prok_3.start();
            ProcessBuilder prok_4 = new ProcessBuilder("notepad.exe", "копия пар");
            prok_4.directory(new File("C:\\Users\\Chekesh\\Desktop"));
            prok_4.start();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}