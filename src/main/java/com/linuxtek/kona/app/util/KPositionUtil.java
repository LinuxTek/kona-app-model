package com.linuxtek.kona.app.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.entity.KPosition;

public class KPositionUtil {
	private static Logger logger = LoggerFactory.getLogger(KPositionUtil.class);
	
    
    // 1 meter = 0.000621371 miles
    public final static double MILES_PER_METER = 0.000621371;
    
    // 1 meter = 0.000621371 miles
    public final static double METERS_PER_MILE = 1/MILES_PER_METER;
    
    //1 meter is equal to 1.31233595801 steps, or 0.000621371192237 miles.
    public final static double STEPS_PER_METER = 1.31233595801;

    // 1 meter/sec = 26.8224 min/mile
    public final static double MINS_PER_MILE = 1/(MILES_PER_METER*60);

    // 1 meter/sec = 16.6666667 mins/km
    public final static double MINS_PER_KM = 1/(.001*60);
    
    // 1 pound = 0.453592 kgs
    public final static double KG_PER_POUND = 0.453592;
    
    // 1kg = 2.20462 lbs
    public final static double POUNDS_PER_KG = 2.20462;
    
    // 1 inch = 2.54 cm
    public final static double CM_PER_INCH = 2.54;

    // 1 inch = 1/2.54 cm
    public final static double INCH_PER_CM = 1/CM_PER_INCH;

    /*
    // 150 pounds = 68.04 kg
    public final static double AVERAGE_WEIGHT = 68;
    
    // 27.5 pounds = 12.5 kg
    public final static double AVERAGE_BIKE_WEIGHT = 12.5;
    */
    
    public static double calcDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6372797.560856;
        
        double dLat = Math.toRadians(lat2-lat1);  
        
        double dLon = Math.toRadians(lon2-lon1);  
        
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +  
           Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *  
           Math.sin(dLon/2) * Math.sin(dLon/2);  
        
        double c = 2 * Math.asin(Math.sqrt(a));  
        
        double dist = R * c;  
        
        logger.debug("calcDistance: ("+lat1+", "+lon1+") ("+lat2+", "+lon2+") = " + dist);
        
        return dist;
     }  

    public static <T extends KPosition> double getDistanceTraveled(List<T> positionList)  {
        // Calculate the total distance traveled
        double total = 0.0;
        
        for (int i=0; i<positionList.size()-1; i++) {
            total += calcDistance(
                positionList.get(i).getLatitude(), positionList.get(i).getLongitude(),
                positionList.get(i+1).getLatitude(), positionList.get(i+1).getLongitude()
            );
        }
        
        return total;
    }
    
    public static <T extends KPosition> T getFurthestPosition(T start, List<T> positionList)  {
        // Calculate the total distance traveled
        T max = null;
        
        double maxDistance = -1.0;
        
        for (int i=0; i<positionList.size(); i++) {
            double distance = calcDistance(start.getLatitude(), start.getLongitude(),
                positionList.get(i).getLatitude(), positionList.get(i).getLongitude());
            
            if (distance > maxDistance) {
                maxDistance = distance;
                max = positionList.get(i);
            }
        }
        
        return max;
    }
    
    public static <T extends KPosition> double getAverageAltitude(List<T> positionList)  {
        double altitude = 0.0;
        
        if (positionList == null || positionList.size() == 0) return altitude;
        
        for (int i=0; i<positionList.size()-1; i++) {
            if (positionList.get(i).getAltitude() == null) continue;
            altitude += positionList.get(i).getAltitude(); 
        }
        
        return altitude/positionList.size();
    }
    
    public static <T extends KPosition> double getAverageGrade(List<T> positionList)  {
        double grade = 0.0;
        
        if (positionList == null || positionList.size() == 0) return grade;
        
        for (int i=0; i<positionList.size()-1; i++) {
            if (positionList.get(i).getLatitude() == null) continue;
            if (positionList.get(i).getLongitude() == null) continue;
            if (positionList.get(i).getAltitude() == null) continue;
            
            if (positionList.get(i+1).getLatitude() == null) continue;
            if (positionList.get(i+1).getLongitude() == null) continue;
            if (positionList.get(i+1).getAltitude() == null) continue;
            
            double altitude = positionList.get(i+1).getAltitude() - positionList.get(i).getAltitude();
            
            double distance = calcDistance(
                positionList.get(i).getLatitude(), positionList.get(i).getLongitude(),
                positionList.get(i+1).getLatitude(), positionList.get(i+1).getLongitude()
            );
            
            if (distance == 0.0) continue;
            
            grade += (altitude/distance);
        }
        
        return grade/positionList.size();
    }

    public static <T extends KPosition> Long getCumulativeTime(List<T> positionList) {
        int count = positionList.size();
        
        if (count < 2) return 0L;

        T first = positionList.get(0);
        T last = positionList.get(count-1);
        
        Long startTime = first.getTimestamp();
        Long endTime = last.getTimestamp();

        if (startTime == null || endTime == null) {
            return 0L;
        }

        Long time = endTime - startTime;
        
        return time;
    }
    
    public static <T extends KPosition> double getAverageAccuracy(List<T> positionList) {
        double accuracy = 0.0;
        
        if (positionList.size() > 0) {
            for (int i=0; i<positionList.size(); i++) {
                accuracy += positionList.get(i).getAccuracy();
            }
            
            accuracy = accuracy/positionList.size();
        }
        
        return accuracy;
    }
    
    
    // convert meters to miles
    public static double toMiles(double distance) {
        double miles = distance * MILES_PER_METER;
        return miles;
    }
    
    public static double toMeters(double distance) {
        double meters = distance * METERS_PER_MILE;
        return meters;
    }
    
    //return pace in mins/km or min/mile
    public static double toPace(double speed, boolean metric) {
        double pace;
        logger.debug("toPace: raw speed: " + speed);
        
        if (speed <= 0.0) return 0.0;

        if (metric)
            pace = MINS_PER_KM / speed;
        else
            pace = MINS_PER_MILE / speed;

        logger.debug("toPace: converted pace: " + pace);
        return pace;
    }
    
    
    // convert speed (meters/sec) to pace (mins/mile)
    public static double toMinsPerMile(double speed) {
        double pace = 0.0;
        if (speed > 0.0) {
            pace = MINS_PER_MILE / speed;
        }
        return pace;
    }
}
