package org.sigerar.converter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.vraptor.LogicRequest;
import org.vraptor.converter.ConversionException;
import org.vraptor.converter.Converter;
import org.vraptor.converter.basic.LocaleCalendarDateConverter;
import org.vraptor.i18n.JstlWrapper;

public class DateTimeConverter implements Converter {

    private final JstlWrapper jstlWrapper = new JstlWrapper();
    private static final Logger LOG = Logger.getLogger(LocaleCalendarDateConverter.class);

    /**
     * Define a type to be formatted. Use DateTime instead of LocalDateTime to match with requirements of joda-time-hibernate-1.2 - EXCEPTION: can't convert LocalDateTime to DateTime.
     * @param value to be converted.
     * @param type that can be [LocalTime | DateTime | LocalDate].
     * @param context VRaptor injection.
     * @return a DateTime converted.
     * @throws ConversionException
     */
    public Object convert(String value, Class<?> type, LogicRequest context) throws ConversionException {
        DateTimeConverter.LOG.debug("+++++++++++++++++++++++++USING CUSTOM CONVERTER FOR DATETIME+++++++++++++++++++++++++");
        if (value == null || value.equals("")) {
            return null;
        }

        try {
            // OLD WAY! - only return a Date instead of a DateTime!!
            //return LocalDateTime.fromDateFields(getDateFormat(type, context).parse(value)).toDateTime();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(getDateFormat(type, context).parse(value));
            return LocalDateTime.fromCalendarFields(calendar).toDateTime();
        } catch (Exception e) {
            DateTimeConverter.LOG.error("Unable to parse string " + value + " --- Custom converter!!! ", e);
            throw new ConversionException("invalid_date", "Unable to parse string " + value + " --- Custom converter!!! ", e);
        }
    }

    public Class<?>[] getSupportedTypes() {
        return new Class<?>[]{DateTime.class};
    }

    public Locale getLocale(LogicRequest contex) {
        Locale locale = jstlWrapper.findLocale(contex);
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return locale;
    }

    private DateFormat getDateFormat(Class<?> type, LogicRequest context) {
        if (type.equals(LocalTime.class)) {
            DateTimeConverter.LOG.debug("Type is equals to LocalTime!!");
            return DateFormat.getTimeInstance(DateFormat.SHORT, getLocale(context));
        //} else if (type.equals(LocalDateTime.class)) {
        } else if (type.equals(DateTime.class)) {
            DateTimeConverter.LOG.debug("Type is equals to LocalDateTime!!");
            return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, getLocale(context));
        }

        DateTimeConverter.LOG.debug("Type is equals to LocalDate!!");
        return DateFormat.getDateInstance(DateFormat.SHORT, getLocale(context));
    }
}
