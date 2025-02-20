package com.clip_ms.Time.Convertor.Controller;

import com.clip_ms.Time.Convertor.Service.TimeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/convert")
@AllArgsConstructor
public class TimeController {
    private final TimeService timeService;
    @PostMapping("/time")
    public LocalDateTime clipMsTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("localDateTime: {}", localDateTime);
        return timeService.clipMilliS(localDateTime);
    }
}
