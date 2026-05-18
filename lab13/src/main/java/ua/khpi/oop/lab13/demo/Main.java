package ua.khpi.oop.lab13.demo;

import ua.khpi.oop.lab13.model.ScheduleResult;
import ua.khpi.oop.lab13.service.ScheduleProcessor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> lines = readFileFromResources("schedule.txt");

            ScheduleProcessor processor = new ScheduleProcessor();

            ScheduleResult result = processor.processLinesInThreads(lines);

            String report = processor.buildReport(result);

            System.out.println();
            System.out.println(report);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Не вдалося прочитати файл: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Роботу потоків було перервано");
            Thread.currentThread().interrupt();
        }
    }

    private static List<String> readFileFromResources(String fileName)
            throws IOException, URISyntaxException {
        URL resource = Main.class.getClassLoader().getResource(fileName);

        if (resource == null) {
            throw new IOException("файл не знайдено: " + fileName);
        }

        return Files.readAllLines(Path.of(resource.toURI()));
    }
}