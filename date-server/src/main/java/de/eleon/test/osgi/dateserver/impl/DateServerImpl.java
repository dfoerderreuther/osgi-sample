package de.eleon.test.osgi.dateserver.impl;

import de.eleon.test.osgi.dateserver.DateServer;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.Date;

@Component
public class DateServerImpl implements DateServer {

    private DateTime startDate;

    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");

    @Activate
    public void activate() {
        this.startDate = DateTime.now();
    }

    public String currentDate() {
        return dateTimeFormatter.print(DateTime.now());
    }

    @Override
    public String startDate() {
        return dateTimeFormatter.print(this.startDate);
    }

    @Override
    public String uptime() {
        Period duration = new Period(this.startDate, DateTime.now());
        PeriodFormatter minutesAndSeconds = new PeriodFormatterBuilder()
                .printZeroAlways()
                .appendDays()
                .appendSeparator(":")
                .appendMinutes()
                .appendSeparator(":")
                .appendSeconds()
                .toFormatter();
        return minutesAndSeconds.print(duration);
    }

}
