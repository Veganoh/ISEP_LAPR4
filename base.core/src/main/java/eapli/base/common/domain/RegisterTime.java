package eapli.base.common.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Embeddable
public class RegisterTime implements ValueObject, Comparable<RegisterTime> {
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    protected RegisterTime(final Calendar date) {
        setDate(date);
    }

    protected RegisterTime() {
        //for ORM only
    }

    private void setDate(Calendar date) {
        if (date == null || date.after(Calendar.getInstance()))
            throw new IllegalArgumentException("Invalid date and time, must not be null or in the future");
        else
            this.date = date;
    }

    /**
     * Parses a date and time in the format YYYY-MM-DD HH:MM:SS, such as 2011-12-03 10:15:30
     *
     * @param date a string formatted according to {@link DateTimeFormatter#ISO_LOCAL_DATE_TIME}
     * @return the parsed RegisterTime object
     */
    public static RegisterTime valueOf(final String date) {
        try {
            Calendar cal = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cal.setTime(sdf.parse(date));
            return new RegisterTime(cal);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Register time must be in the format yyyy-MM-dd HH:mm:ss.");
        }
    }

    public static RegisterTime valueOf(final Calendar date) {
        return new RegisterTime(date);
    }

    public static RegisterTime now() {
        return new RegisterTime(Calendar.getInstance());
    }

    public Calendar value() {
        return this.date;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterTime)) {
            return false;
        }

        final RegisterTime that = (RegisterTime) o;
        return this.date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return this.date.hashCode();
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(this.date);
    }

    @Override
    public int compareTo(RegisterTime o) {
        return this.date.compareTo(o.date);
    }
}
