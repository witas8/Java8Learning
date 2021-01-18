import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DataAPI {
    public static void main(String[] args) {
        System.out.println("\nCLOCK API:");
        clockAPIExample();

        System.out.println("\nTIMEZONE API:");
        timeZoneAPIExample();

        System.out.println("\nLOCALTIME API:");
        localTimeAPIExample();

        System.out.println("\nLOCALEDATE API:");
        localDateAPIExample();

        System.out.println("\nLOCATETIMEDATE API:");
        localeDateTimeAPIExample();
    }

    static void clockAPIExample(){
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis(); //to retrieve the current milliseconds
        System.out.println("Millis now: " + millis);

        Instant instant = clock.instant();
        Date date = Date.from(instant);
        System.out.println("Date now: " + date.toString());
    }

    static void timeZoneAPIExample(){
        // Timezones are represented by a ZoneId
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId myZone = ZoneId.of("Europe/Warsaw");
        System.out.println("Warsaw Zone: " + myZone.getRules());
    }

    static void localTimeAPIExample() {
        //LocalTime represents a time without a timezone
        ZoneId warsawZone = ZoneId.of("Europe/Warsaw");
        ZoneId brazilZone = ZoneId.of("Brazil/East");

        LocalTime nowWarsaw = LocalTime.now(warsawZone);
        LocalTime nowBrazil = LocalTime.now(brazilZone);

        System.out.println("Local Time in Warsaw: " + nowWarsaw);

        long hoursBetween = ChronoUnit.HOURS.between(nowWarsaw, nowBrazil);
        long minutesBetween = ChronoUnit.MINUTES.between(nowBrazil, nowWarsaw);

        System.out.println("Hours: " + hoursBetween);
        System.out.println("Hours: " + minutesBetween);
    }

    static void localDateAPIExample(){
        LocalDate today = LocalDate.now();
        LocalDate tommorow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tommorow.minusDays(2);
        System.out.println(yesterday);

        LocalDate independanceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independanceDay.getDayOfWeek();
        System.out.println("Day of the week: " + dayOfWeek);
    }

    static void localeDateTimeAPIExample(){
        //combines date and time to one instance
        LocalDateTime sylvester = LocalDateTime.of(2021, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println("Day of the week: " + dayOfWeek);

        long minuteOfDay = sylvester.getLong(ChronoField.MICRO_OF_DAY);
        System.out.println("How sylvester day will be long in minutes? Answer: " + minuteOfDay);

    }
}
