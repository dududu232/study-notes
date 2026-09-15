package com.tiamo.dt.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LocalDateTimeUtil {

    private static final String defaultPattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转为时间
     * @param text
     * @param pattern
     * @return
     */
    public static LocalDateTime parse(CharSequence text,String pattern){
        return LocalDateTime.parse(text,DateTimeFormatter.ofPattern(pattern));
    }
    //字符串转为时间
    public static LocalDateTime parse(CharSequence text){
        return parse(text,defaultPattern);
    }







    /**
     * 拆分带日期的时间范围（LocalDateTime）
     * 支持自定义时间单位的核心方法
     * @param start 开始时间
     * @param end 结束时间
     * @param amount 每个分段的数量
     * @param unit 时间单位（ChronoUnit，如秒、分钟、小时、天等）
     * @return 拆分后的时间范围列表
     */
    public static List<TimeRange> splitDateTime(LocalDateTime start, LocalDateTime end, long amount, ChronoUnit unit) {
        // 参数校验
        validateParams(start, end, amount, unit);

        List<TimeRange> result = new ArrayList<>();
        LocalDateTime currentStart = start;

        while (true) {
            // 计算当前分段的结束时间（使用指定的时间单位）
            LocalDateTime currentEnd = currentStart.plus(amount, unit);

            // 若当前结束时间超过总结束时间，则以总结束时间为终点
            if (currentEnd.isAfter(end)) {
                result.add(new TimeRange(currentStart, end));
                break;
            }

            // 添加当前分段
            result.add(new TimeRange(currentStart, currentEnd));

            // 准备下一分段的开始时间
            currentStart = currentEnd;

            // 防止无限循环（兜底逻辑）
            if (currentStart.isAfter(end)) {
                break;
            }
        }

        return result;
    }

    /**
     * 兼容原有逻辑的重载方法（分钟单位）
     * @param start 开始时间
     * @param end 结束时间
     * @param duration 每个分段的时长（单位：分钟）
     * @return 拆分后的时间范围列表
     */
    public static List<TimeRange> splitDateTime(LocalDateTime start, LocalDateTime end, int duration) {
        return splitDateTime(start, end, duration, ChronoUnit.MINUTES);
    }

    /**
     * 兼容原有逻辑的重载方法（Duration类型）
     * @param start 开始时间
     * @param end 结束时间
     * @param duration 每个分段的时长
     * @return 拆分后的时间范围列表
     */
    public static List<TimeRange> splitDateTime(LocalDateTime start, LocalDateTime end, Duration duration) {
        // 优先按分钟处理，若需要更精准可扩展支持其他单位
        return splitDateTime(start, end, duration.toMinutes(), ChronoUnit.MINUTES);
    }

    /**
     * 扩展方法：按小时拆分
     * 方便用户直接调用，无需手动指定ChronoUnit.HOURS
     * @param start 开始时间
     * @param end 结束时间
     * @param hours 每个分段的小时数
     * @return 拆分后的时间范围列表
     */
    public static List<TimeRange> splitByHours(LocalDateTime start, LocalDateTime end, long hours) {
        return splitDateTime(start, end, hours, ChronoUnit.HOURS);
    }

    /**
     * 扩展方法：按天拆分
     * 方便用户直接调用，无需手动指定ChronoUnit.DAYS
     * @param start 开始时间
     * @param end 结束时间
     * @param days 每个分段的天数
     * @return 拆分后的时间范围列表
     */
    public static List<TimeRange> splitByDays(LocalDateTime start, LocalDateTime end, long days) {
        return splitDateTime(start, end, days, ChronoUnit.DAYS);
    }

    /**
     * 校验参数（适配自定义时间单位）
     */
    private static void validateParams(LocalDateTime start, LocalDateTime end, long amount, ChronoUnit unit) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("开始时间和结束时间不能为空");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("开始时间不能晚于结束时间");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("分段时长数量必须大于0");
        }
        if (unit == null) {
            throw new IllegalArgumentException("时间单位不能为空");
        }
        // 过滤不支持的时间单位（LocalDateTime不支持的单位，如年、月）
        if (!isSupportedUnit(unit)) {
            throw new IllegalArgumentException("不支持的时间单位：" + unit + "，请使用秒、分钟、小时、天等单位");
        }
    }

    /**
     * 检查是否为LocalDateTime支持的时间单位
     * @param unit 时间单位
     * @return 是否支持
     */
    private static boolean isSupportedUnit(ChronoUnit unit) {
        return unit == ChronoUnit.SECONDS ||
                unit == ChronoUnit.MINUTES ||
                unit == ChronoUnit.HOURS ||
                unit == ChronoUnit.DAYS;
    }

    @Data
    @AllArgsConstructor
    public static class TimeRange {
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;

        // 重写toString，方便调试查看
        @Override
        public String toString() {
            return "[" + startDateTime + " - " + endDateTime + "]";
        }
    }


}
