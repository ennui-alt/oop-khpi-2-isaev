package ua.khpi.oop.lab13.model;

public enum WeekDay {
    MONDAY("Понеділок", 1),
    TUESDAY("Вівторок", 2),
    WEDNESDAY("Середа", 3),
    THURSDAY("Четвер", 4),
    FRIDAY("П'ятниця", 5),
    SATURDAY("Субота", 6),
    SUNDAY("Неділя", 7);

    private final String title;
    private final int order;

    WeekDay(String title, int order) {
        this.title = title;
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public int getOrder() {
        return order;
    }

    public static WeekDay fromText(String text) {
        String fixedText = text.trim().replace("’", "'");

        for (WeekDay day : values()) {
            if (day.title.equalsIgnoreCase(fixedText)) {
                return day;
            }
        }

        throw new IllegalArgumentException("невідомий день тижня: " + text);
    }
}