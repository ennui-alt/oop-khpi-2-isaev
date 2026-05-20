package ua.khpi.oop.lab13.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.khpi.oop.lab13.model.Lesson;
import ua.khpi.oop.lab13.model.ScheduleResult;
import ua.khpi.oop.lab13.model.WeekDay;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleProcessorTest {
    private ScheduleProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new ScheduleProcessor();
    }

    @Test
    void normalizeLineShouldRemoveExtraSpaces() {
        String line = "  Понеділок ; 1 ; 08:30-10:05 ; 305-А ; Вакарчук С. І. ; Java  ";

        String result = processor.normalizeLine(line);

        assertEquals("Понеділок;1;08:30-10:05;305-А;Вакарчук С. І.;Java", result);
    }

    @Test
    void parseLineShouldCreateLessonFromCorrectLine() {
        String line = "Понеділок;1;08:30-10:05;305-А;Вакарчук С. І.;Об'єктно-орієнтоване програмування";

        Lesson lesson = processor.parseLine(line);

        assertEquals(WeekDay.MONDAY, lesson.getDay());
        assertEquals(1, lesson.getNumber());
        assertEquals("08:30-10:05", lesson.getTime());
        assertEquals("305-А", lesson.getClassroom());
        assertEquals("Вакарчук С. І.", lesson.getTeacher());
        assertEquals("Об'єктно-орієнтоване програмування", lesson.getSubject());
    }

    @Test
    void parseLineShouldRejectWrongFormat() {
        String line = "Неправильний рядок без потрібного формату";

        assertThrows(IllegalArgumentException.class, () -> processor.parseLine(line));
    }

    @Test
    void parseLineShouldRejectWrongTimeOrder() {
        String line = "Середа;2;10:25-09:00;301;Кароль Т. Г.;Некоректний час";

        assertThrows(IllegalArgumentException.class, () -> processor.parseLine(line));
    }

    @Test
    void processLinesInThreadsShouldSeparateCorrectAndWrongLines() throws InterruptedException {
        List<String> lines = List.of(
                "Понеділок;1;08:30-10:05;305-А;Вакарчук С. І.;Java",
                "Вівторок;1;08:30-10:05;A301;Сумська О. В.;Комп'ютерні мережі",
                "Неправильний рядок без потрібного формату",
                "Середа;2;10:25-09:00;301;Кароль Т. Г.;Некоректний час"
        );

        ScheduleResult result = processor.processLinesInThreads(lines);

        assertEquals(2, result.getLessons().size());
        assertEquals(2, result.getErrors().size());
    }

    @Test
    void processLinesInThreadsShouldSortLessonsByDayAndNumber() throws InterruptedException {
        List<String> lines = List.of(
                "Середа;3;12:35-14:10;404-Б;Лижичко Р. С.;Java",
                "Понеділок;2;10:25-12:00;210;Пономарьов О. В.;Бази даних",
                "Понеділок;1;08:30-10:05;305-А;Вакарчук С. І.;ООП"
        );

        ScheduleResult result = processor.processLinesInThreads(lines);

        assertEquals(WeekDay.MONDAY, result.getLessons().get(0).getDay());
        assertEquals(1, result.getLessons().get(0).getNumber());

        assertEquals(WeekDay.MONDAY, result.getLessons().get(1).getDay());
        assertEquals(2, result.getLessons().get(1).getNumber());

        assertEquals(WeekDay.WEDNESDAY, result.getLessons().get(2).getDay());
        assertEquals(3, result.getLessons().get(2).getNumber());
    }

    @Test
    void buildReportShouldContainMainInformation() throws InterruptedException {
        List<String> lines = List.of(
                "Понеділок;1;08:30-10:05;305-А;Вакарчук С. І.;Java",
                "Неправильний рядок без потрібного формату"
        );

        ScheduleResult result = processor.processLinesInThreads(lines);
        String report = processor.buildReport(result);

        assertTrue(report.contains("Звіт з обробки розкладу занять"));
        assertTrue(report.contains("Коректних записів: 1"));
        assertTrue(report.contains("Помилкових записів: 1"));
        assertTrue(report.contains("Вакарчук С. І."));
        assertTrue(report.contains("Помилки:"));
    }
}