package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.util.HashCoder;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Embeddable
public class BirthDate implements ValueObject, Comparable<BirthDate>{

    /**
     * The Customer's Birth Date
     */
    @Temporal(TemporalType.DATE)
    private Calendar birthDate;

    /**
     * Constructor for BirthDate
     * @param day day of birth
     * @param month month of birth
     * @param year year of birth
     */
    public BirthDate(int day, int month, int year){
        if(day<1 || month<1 || year<1 || day>31 || month>12){
            throw new IllegalArgumentException("Invalid birth date");
        }else{
            Preconditions.noneNull(day,month,year, "Customer must specify a birth date");

            this.birthDate = new GregorianCalendar(year,month,day);
        }
    }

    /**
     * empty constructor for ORM
     */
    protected BirthDate(){
        //for ORM
    }

    /**
     * Creates a BirthDate object
     * @param day day of birth
     * @param month month of birth
     * @param year year of birth
     * @return a new BirthDate object
     */
    public static BirthDate valueOf(int day, int month, int year) {
        return new BirthDate(day, month, year);
    }

    /**
     * Return true if both object are equal or false otherwise
     * @param o the other object
     * @return true if both object are equal or false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BirthDate)) {
            return false;
        }

        final BirthDate other = (BirthDate) o;
        return birthDate.equals(other.birthDate);
    }

    /**
     * @return transforms this object to string form
     */
    @Override
    public String toString() {
        return String.format("Birth Date: %02d-%02d-%04d (DD-MM-YY)",birthDate.get(Calendar.DAY_OF_MONTH),birthDate.get(Calendar.MONTH),birthDate.get(Calendar.YEAR));
    }

    /**
     * @return the object's hashcode
     */
    @Override
    public int hashCode() {
        return new HashCoder().with(birthDate).code();
    }

    /**
     * @param o the other object
     * @return 1 if the other object is smaller, 0 if they are equal and -1 if the other object is bigger
     */
    @Override
    public int compareTo(BirthDate o) {
        return birthDate.compareTo(o.birthDate);
    }

    public int age() {
        return Period.between(LocalDateTime.ofInstant(birthDate.toInstant(), birthDate.getTimeZone().toZoneId()).toLocalDate(), LocalDate.from(LocalDateTime.now())).getYears();
    }
}
