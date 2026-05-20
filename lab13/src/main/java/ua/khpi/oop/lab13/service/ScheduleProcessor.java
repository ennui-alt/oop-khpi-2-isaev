package ua.khpi.oop.lab13.service;

import ua.khpi.oop.lab13.model.Lesson;
import ua.khpi.oop.lab13.model.ScheduleResult;
import ua.khpi.oop.lab13.model.WeekDay;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleProcessor {
    private static final Pattern LINE_PATTERN = Pattern.compile(
            "^(Понеділок|Вівторок|Середа|Четвер|П['’]ятниця|Субота|Неділя);" +
                    "([1-9]);" +
                    "(\\d{2}:\\d{2})-(\\d{2}:\\d{2});" +
                    "([A-ZА-ЯІЇЄҐ]?\\d{2,3}(-[А-ЯA-ZІЇЄҐ])?);" +
                    "([^;]+);" +
                    "(.+)$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
    );

    public String normalizeLine(String line) {
        if (line == null) {
            return "";
        }

        return line.trim()
                .replaceAll("\\s+", " ")
                .replaceAll("\\s*;\\s*", ";");
    }

    public Lesson parseLine(String line) {
        String normalizedLine = normalizeLine(line);
        Matcher matcher = LINE_PATTERN.matcher(normalizedLine);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("рядок не відповідає формату: " + line);
        }

        WeekDay day = WeekDay.fromText(matcher.group(1));
        int number = Integer.parseInt(matcher.group(2));

        LocalTime startTime = LocalTime.parse(matcher.group(3));
        LocalTime endTime = LocalTime.parse(matcher.group(4));

        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("початок пари пізніше або дорівнює кінцю: " + line);
        }

        String classroom = matcher.group(5).trim();
        String teacher = matcher.group(7).trim();
        String subject = matcher.group(8).trim();

        return new Lesson(day, number, startTime, endTime, classroom, teacher, subject);
    }

    public ScheduleResult processLinesInThreads(List<String> lines) throws InterruptedException {
        ScheduleResult result = new ScheduleResult();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<LineResult>> futures = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            int lineNumber = i + 1;
            String line = lines.get(i);

            Callable<LineResult> task = () -> handleOneLine(lineNumber, line);
            futures.add(executor.submit(task));
        }

        for (Future<LineResult> future : futures) {
            try {
                LineResult lineResult = future.get();

                if (lineResult.lesson != null) {
                    result.addLesson(lineResult.lesson);
                } else {
                    result.addError(lineResult.error);
                }
            } catch (ExecutionException e) {
                result.addError("помилка в потоці: " + e.getMessage());
            }
        }

        executor.shutdown();

        result.getLessons().sort(
                Comparator.comparingInt((Lesson lesson) -> lesson.getDay().getOrder())
                        .thenComparingInt(Lesson::getNumber)
                        .thenComparing(Lesson::getStartTime)
        );

        return result;
    }

    public String buildReport(ScheduleResult result) {
        StringBuilder builder = new StringBuilder();

        builder.append("Звіт з обробки розкладу занять\n");
        builder.append("================================\n");
        builder.append("Коректних записів: ").append(result.getLessons().size()).append('\n');
        builder.append("Помилкових записів: ").append(result.getErrors().size()).append("\n\n");

        builder.append("Розклад:\n");

        WeekDay currentDay = null;

        for (Lesson lesson : result.getLessons()) {
            if (lesson.getDay() != currentDay) {
                currentDay = lesson.getDay();
                builder.append('\n').append(currentDay.getTitle()).append(":\n");
            }

            builder.append("  ")
                    .append(lesson)
                    .append('\n');
        }

        if (!result.getErrors().isEmpty()) {
            builder.append("\nПомилки:\n");

            for (String error : result.getErrors()) {
                builder.append("  - ").append(error).append('\n');
            }
        }

        return builder.toString();
    }

    private LineResult handleOneLine(int lineNumber, String line) {
        String threadName = Thread.currentThread().getName();

        System.out.println("Потік " + threadName + " обробляє рядок №" + lineNumber);

        if (normalizeLine(line).isBlank()) {
            return LineResult.error("рядок №" + lineNumber + " порожній");
        }

        try {
            Lesson lesson = parseLine(line);
            return LineResult.success(lesson);
        } catch (IllegalArgumentException e) {
            return LineResult.error("рядок №" + lineNumber + ": " + e.getMessage());
        }
    }

    private static class LineResult {
        private final Lesson lesson;
        private final String error;

        private LineResult(Lesson lesson, String error) {
            this.lesson = lesson;
            this.error = error;
        }

        private static LineResult success(Lesson lesson) {
            return new LineResult(lesson, null);
        }

        private static LineResult error(String error) {
            return new LineResult(null, error);
        }
    }
}