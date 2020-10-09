package com.soft1851.share.gateway;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;

/**
 * 描述:
 *
 * @author：Guorc
 * @create 2020-10-09 10:54
 */
@Data
public class TimeBetweenConfig {
    private LocalTime start;
    private LocalTime end;
}
