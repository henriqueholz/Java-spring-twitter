package com.henrique.posterr.util;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class DateUtil {

    public Timestamp currentTimestamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    public String currentDate(Timestamp timestamp) {
        return new SimpleDateFormat("MMMMM dd,yyyy").format(timestamp);
    }
}

