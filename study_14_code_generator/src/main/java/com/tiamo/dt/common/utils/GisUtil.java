package com.tiamo.dt.common.utils;

public class GisUtil {

    // 地球半径（千米）
    public static final double EARTH_RADIUS_KM = 6371.0;
    // 地球半径（米）
    public static final double EARTH_RADIUS_M = EARTH_RADIUS_KM * 1000;
    // 地球参数 (WGS-84 椭球体)
    public static final double EARTH_SEMI_MAJOR_AXIS = 6378137.0; // 长半轴 a (米)
    public static final double EARTH_ECCENTRICITY_SQ = 6.69437999014e-3; // 第一偏心率平方 e^2
    public static final double GRAVITY = 9.80665; // 重力加速度 (m/s^2)

    /**
     * 计算地表水平距离（米）
     */
    public static double calculateHorizontalDistance(double lon1, double lat1, double lon2, double lat2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_M * c;
    }

    /**
     * 通过经纬度计算两点之间距离
     * @param lat1
     * @param lon1
     * @param alt1
     * @param lat2
     * @param lon2
     * @param alt2
     * @return
     */
    public static double calculateDistance(double lon1, double lat1, double alt1,
                                            double lon2, double lat2, double alt2) {
        // 地球平均半径 (米)
        final double R = EARTH_RADIUS_M;

        // 转换为弧度
        double r1 = Math.toRadians(lat1);
        double r2 = Math.toRadians(lat2);;
        double d1 = Math.toRadians(lat2 - lat1);
        double d2 = Math.toRadians(lon2 - lon1);

        // Haversine公式计算地表距离
        double a = Math.sin(d1/2) * Math.sin(d1/2) +
                Math.cos(r1) * Math.cos(r2) *
                        Math.sin(d2/2) * Math.sin(d2/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double horizontalDistance = R * c;

        // 加入高度差修正
        double h = Math.abs(alt1 - alt2);
        return Math.sqrt(horizontalDistance * horizontalDistance + h * h);
    }

    /**
     * 计算两点间的初始方位角（度）
     */
    public static double calculateBearing(double lon1, double lat1,  double lon2, double lat2) {
        double lat1Rad = Math.toRadians(lat1);
        double lat2Rad = Math.toRadians(lat2);
        double dLonRad = Math.toRadians(lon2 - lon1);

        double y = Math.sin(dLonRad) * Math.cos(lat2Rad);
        double x = Math.cos(lat1Rad) * Math.sin(lat2Rad) -
                Math.sin(lat1Rad) * Math.cos(lat2Rad) * Math.cos(dLonRad);

        double bearing = Math.toDegrees(Math.atan2(y, x));
        return (bearing + 360) % 360; // 标准化到0-360度
    }

    /**
     * 计算仰角（俯仰角）- 从起点到终点的垂直角度
     */
    public static double calculateElevationAngle(double lon1, double lat1, double alt1,
                                                 double lon2, double lat2, double alt2) {
        double horizontalDist = calculateHorizontalDistance(lat1, lon1, lat2, lon2);
        double verticalDiff = alt2 - alt1;

        return Math.toDegrees(Math.atan2(verticalDiff, horizontalDist));
    }

    /**
     * 根据起点、距离和方位角计算终点坐标
     */
    public static double[] calculateDestination(double lat, double lon, double distance, double bearing) {
        double latRad = Math.toRadians(lat);
        double lonRad = Math.toRadians(lon);
        double bearingRad = Math.toRadians(bearing);

        double angularDistance = distance / EARTH_RADIUS_KM;

        double destLatRad = Math.asin(Math.sin(latRad) * Math.cos(angularDistance) +
                Math.cos(latRad) * Math.sin(angularDistance) * Math.cos(bearingRad));

        double destLonRad = lonRad + Math.atan2(Math.sin(bearingRad) * Math.sin(angularDistance) * Math.cos(latRad),
                Math.cos(angularDistance) - Math.sin(latRad) * Math.sin(destLatRad));

        double destLat = Math.toDegrees(destLatRad);
        double destLon = Math.toDegrees(destLonRad);

        return new double[]{destLat, destLon};
    }

    /**
     * 根据起点、水平距离、方位角和高度变化计算终点坐标
     */
    public static double[] calculate3DDestination(double lon, double lat, double alt,
                                                  double horizontalDistance, double bearing,
                                                  double targetAltitude, double total3DDistance) {
        // 计算水平方向的新位置
        double angularDistance = horizontalDistance / EARTH_RADIUS_M;
        double latRad = Math.toRadians(lat);
        double lonRad = Math.toRadians(lon);
        double bearingRad = Math.toRadians(bearing);

        double destLatRad = Math.asin(Math.sin(latRad) * Math.cos(angularDistance) +
                Math.cos(latRad) * Math.sin(angularDistance) * Math.cos(bearingRad));

        double destLonRad = lonRad + Math.atan2(Math.sin(bearingRad) * Math.sin(angularDistance) * Math.cos(latRad),
                Math.cos(angularDistance) - Math.sin(latRad) * Math.sin(destLatRad));

        double destLat = Math.toDegrees(destLatRad);
        double destLon = Math.toDegrees(destLonRad);

        // 线性插值计算当前高度
        double progressRatio = horizontalDistance / (total3DDistance *
                (horizontalDistance / Math.sqrt(horizontalDistance * horizontalDistance +
                        Math.pow(targetAltitude - alt, 2))));

        double currentAltitude = alt + (targetAltitude - alt) * progressRatio;

        return new double[]{destLon, destLat, currentAltitude};
    }

}
