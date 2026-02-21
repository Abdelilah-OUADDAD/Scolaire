package com.System.Util;

import java.util.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {

    public static Date getCurrentDateGMT() {

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("GMT"));

        return Date.from(zonedDateTime.toInstant());
    }
}
