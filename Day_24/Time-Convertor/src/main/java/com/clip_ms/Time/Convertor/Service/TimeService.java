package com.clip_ms.Time.Convertor.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {

    public LocalDateTime clipMilliS(LocalDateTime dt)
    {
        String res="";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        res=dt.format(formatter);
        LocalDateTime clipped = LocalDateTime.parse(res, formatter);
        return clipped;
    }

}
