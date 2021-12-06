import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class HomeTask5 {

    private static final String[] DOF = {null, "воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота"};

    public static void main(String... a) {
        Calendar cal = Calendar.getInstance();
        // Abe Lincoln's Birthday: February 12, 1809, died April 15, 1865
        //   How old when he died?
        //   How many days did he live?
        {
            System.out.println("---===---");
            Date born = new Date(1809, Calendar.FEBRUARY, 12), died = new Date(1855, Calendar.APRIL, 15);
            System.out.println("Линкольн помер в возрасте " + ((died.getTime() - born.getTime()) / 60000 / 60 / 24 / 365) + ".");
            System.out.println("Он прожил " + ((died.getTime() - born.getTime()) / 60000 / 60 / 24) + " дней.");
        }
        // Bennedict Cumberbatch, July 19, 1976
        //   Born in a leap year?
        //   How many days in the year he was born?
        //   How many decades old is he?
        //   What was the day of the week on his 21st birthday?
        {
            System.out.println("---===---");
            cal.set(Calendar.YEAR, 1976);
            cal.set(Calendar.MONTH, Calendar.JULY);
            cal.set(Calendar.DAY_OF_MONTH, 19);
            int days = cal.getMaximum(Calendar.DAY_OF_YEAR);
            System.out.println((days == 365 ? "Нет" : "Да") + ", " + days);
            System.out.println("Он прожил " + (new Date().getTime() - cal.getTimeInMillis()) / 60000 / 60 / 24 / 365 / 10 + " десятилетий");
            cal.add(Calendar.YEAR, 21);
            System.out.println("Это был " + DOF[cal.get(Calendar.DAY_OF_WEEK)]);
        }

        // Train departs Boston at 1:45PM and arrives New York 7:25PM
        //   How many minutes long is the train ride?
        //   If the train was delayed 1 hour 19 minutes, what is the actual arrival time?
        {
            System.out.println("---===---");
            Calendar depart = Calendar.getInstance(), arriv = Calendar.getInstance();
            depart.set(Calendar.HOUR_OF_DAY, 13);
            depart.set(Calendar.MINUTE, 45);
            arriv.set(Calendar.HOUR_OF_DAY, 19);
            arriv.set(Calendar.MINUTE, 25);
            System.out.println("Поезд будет в пути " + (arriv.getTimeInMillis() - depart.getTimeInMillis()) / 60000 + " минут.");
            arriv.add(Calendar.HOUR, 1);
            arriv.add(Calendar.MINUTE, 19);
            System.out.println("В случае опаздывания, прибытие состоится в " + arriv.get(Calendar.HOUR_OF_DAY) + ':' + arriv.get(Calendar.MINUTE));

        }
        // Flight: Boston to Miami, leaves March 24th 9:15PM. Flight time is 4 hours 15 minutes
        //   When does it arrive in Miami?
        //   When does it arrive if the flight is delays 4 hours 27 minutes?
        {
            System.out.println("---===---");
            cal.set(2021, Calendar.MARCH, 24, 21, 15, 0);
            cal.add(Calendar.MINUTE, 15 + 4 * 60);
            System.out.println("Самолет прилетит в Маями в дату " + cal.getTime());
            cal.add(Calendar.MINUTE, 27 + 4 * 60);
            System.out.println("Самолет прилетит в Маями в дату " + cal.getTime());
        }
        // School semester starts the second Tuesday of September of this year.
        //   Hint: Look at the TemporalAdjusters class
        //   What is the date?
        {
            System.out.println("---===---");
            LocalDate start = LocalDate.of(2021, Month.SEPTEMBER, 01).with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.TUESDAY));
            System.out.println("Это будет " + start);

            //   School summer vacation starts June 25th
            //   Assuming:
            //     *  Two weeks off in December
            //     *  Two other vacation weeks
            //     *  School is taught Monday - Friday
            //   How many days of school are there?
            //   Hint: keep track of the short weeks also
            Calendar end = Calendar.getInstance(), start2 = Calendar.getInstance();
            end.set(2022, Calendar.JUNE, 25);
            start2.set(2021, Calendar.SEPTEMBER, start.getDayOfMonth());
            long days_total = (end.getTimeInMillis() - start2.getTimeInMillis()) / 1000 / 60 / 60 / 24; // всего дней
            days_total -= days_total * 2 / 7; // убрали выходные
            days_total -= 20; // убрали 4 недели каникул
            System.out.println("В школе будет проведено " + days_total + " дней.");
        }
        // A meeting is schedule for 1:30 PM next Tuesday. If today is Tuesday, assume it is today.
        //   What is the time of the week's meetings?
        {
            System.out.println("---===---");
            Calendar calendar = Calendar.getInstance();
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) System.out.println("Встреча сегодня в 13:30.");
            else System.out.println("Встреча на следующей неделе, во вторник в 13:30.");
        }

        // Using the following information:
        //   * Use America/New_York as the time zone for Boston Logan Airport(BOS).
        //   * Use America/Los_Angeles as the time zone for San Francisco Airport (SFO).
        //   * Use Asia/Calcutta as the time zone for Bangalore's Bengaluru International Airport (BLR)

        final ZoneId BOS = ZoneId.of("America/New_York"),
                SFO = ZoneId.of("America/Los_Angeles"),
                BLR = ZoneId.of("Asia/Calcutta");
        DateTimeFormatter f = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.DAY_OF_MONTH, 2).appendLiteral('.')
                .appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('.')
                .appendValue(ChronoField.YEAR, 4).appendLiteral(' ')
                .appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':')
                .appendValue(ChronoField.MINUTE_OF_HOUR, 2).toFormatter();

        // Flight 123, San Francisco to  Boston, leaves SFO at 10:30 PM June 13, 2014
        // The flight is 5 hours 30 minutes
        //   What is the local time in Boston when the flight takes off?
        //   What is the local time at Boston Logan airport when the flight arrives?
        //   What is the local time in San Francisco when the flight arrives?
        {
            System.out.println("---===---");
            LocalDate takeoffDate = LocalDate.of(2014, Month.JUNE, 13);
            LocalTime takeoffTime = LocalTime.of(22, 30);
            ZonedDateTime takeoff = ZonedDateTime.of(takeoffDate, takeoffTime, SFO),
                    takeoffb = takeoff.withZoneSameInstant(BOS);
            System.out.println("Взлет по Бостону: " + takeoffb.format(f));
            ZonedDateTime arriveb = takeoffb.plusHours(5).plusMinutes(30), arrive = arriveb.withZoneSameInstant(SFO);
            System.out.println("Прибытие по Бостону: " + arriveb.format(f));
            System.out.println("Прибытие по Сан-Франциско: " + arrive.format(f));
        }

        // Flight 456, San Francisco to Bangalore, India, leaves SFO at Saturday, 10:30 PM June 28, 2014
        // The flight time is 22 hours
        //   Will the traveler make a meeting in Bangalore Monday at 9 AM local time?
        //   Can the traveler call her husband at a reasonable time when she arrives?
        {
            System.out.println("---===---");
            LocalDate takeoffDate = LocalDate.of(2014, Month.JUNE, 28);
            LocalTime takeoffTime = LocalTime.of(22, 30);
            ZonedDateTime takeoff = ZonedDateTime.of(takeoffDate, takeoffTime, SFO);
            ZonedDateTime arrive = takeoff.plusHours(22).withZoneSameInstant(BLR);
            ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2014, Month.JUNE, 30), LocalTime.of(9, 0), BLR);
            System.out.println("Прибытие в Бангалор по местному времени: " + arrive.format(f) +
                    "; Может ли он назначить встречу на 9 утра понедельника? Конечно "
                    + (meeting.isBefore(arrive) || meeting.isEqual(arrive) ? "может!" : "нет, не может!"));
            System.out.println("Зависит от того, что считать нормой)");
        }

        // Flight 123, San Francisco to Boston, leaves SFO at 10:30 PM Saturday, November 1st, 2014
        // Flight time is 5 hours 30 minutes.
        //   What day and time does the flight arrive in Boston?
        //   What happened?
        {
            System.out.println("---===---");
            LocalDate takeoffDate = LocalDate.of(2014, Month.NOVEMBER, 1);
            LocalTime takeoffTime = LocalTime.of(22, 30);
            ZonedDateTime takeoff = ZonedDateTime.of(takeoffDate, takeoffTime, SFO);
            ZonedDateTime arrive = takeoff.plusHours(5).plusMinutes(30).withZoneSameInstant(BOS);
            System.out.println("Прибытие в Бостон по местному времени: " + arrive.format(f));
            System.out.println("Что произошло? Самолет перелетел из Сан-Франциско в Бостон.\nА если серьезно, то перевод на зимнее время))");
        }
    }

}
