package com.soft1851.share.user.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 描述:
 * 日期判断方法
 *
 * @author：Guorc
 * @create 2020-10-15 12:46
 */
@Slf4j
public class DateUtil {
    public static int checkAllotSigin(Date date) throws Exception {

        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //将Date类型转换成String类型
        String time = sdf.format(date);
        log.info("转换后的时间:" + time);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localTime = LocalDateTime.parse(time, dtf);
        log.info("当前的localTime:" + localTime);
        LocalDateTime startTime = LocalDate.now().atTime(0, 0, 0);
        log.info("startTime:" + startTime);
        LocalDateTime endTime = LocalDate.now().atTime(23, 59, 59);
        log.info("endTime:" + endTime);
        System.out.println(localTime.isBefore(startTime));
        //如果小于今天的开始日期
        if (localTime.isBefore(startTime)) {
//            /**判断是否小于昨天，小于昨天证明签到不连续，签到记录表签到连续次数设置为0*/
//            Date newTime = new Date();
//            //将下面的 理解成  yyyy-MM-dd 00：00：00 更好理解点
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String todayStr = format.format(newTime);
//            Date today = format.parse(todayStr);
//            //昨天 86400000=24*60*60*1000 一天  大于昨天 至少为前天
//            if ((today.getTime() - date.getTime()) > 86400000) {
//                result = 2;
//                log.info("小于今天的开始日期,至少为前天的时间,连续签到终止");
//            } else {
//                result = 0;
//                log.info("小于今天的开始日期,最后一次签到是昨天，连续签到未终止");
//            }
            log.info("数据库的数据小于今天的开始日期，没有签到，可以签到");
            return 0;
        }
        //如果大于今天的开始日期，小于今天的结束日期
        if (localTime.isAfter(startTime) && localTime.isBefore(endTime)) {
            log.info("大于今天的开始日期，小于今天的结束日期，已经签到");
            result = 1;
        }
        //如果大于今天的结束日期
        if (localTime.isAfter(endTime)) {
            log.info("大于今天的结束日期，数据乱了？？？");
            result = 2;
        }

        return result;
    }
}
