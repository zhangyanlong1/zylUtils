package com.utils;

public class MathUtils {

	   /**
  * 地球半径,单位 km
  */
 private static final double EARTH_RADIUS = 6378.137;

 /**
   * @description 根据经纬度，计算两点间的距离
   * @param longitude1 第一个点的经度
   * @param latitude1  第一个点的纬度
   * @param longitude2 第二个点的经度
   * @param latitude2  第二个点的纬度
   * @return 返回距离 单位千米
 
   */
 public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
     // 纬度
     double lat1 = Math.toRadians(latitude1);
     double lat2 = Math.toRadians(latitude2);
     // 经度
     double lng1 = Math.toRadians(longitude1);
     double lng2 = Math.toRadians(longitude2);
     // 纬度之差
     double a = lat1 - lat2;
     // 经度之差
     double b = lng1 - lng2;
     // 计算两点距离的公式
     double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
             Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
     // 弧长乘地球半径, 返回单位: 千米
     s = s * EARTH_RADIUS * 1000;
    
     return s;
 }
	
 /**
  * 根据经纬度计算环数
  * @param x1
  * @param y1
  * @return
  */
 public static int getLooplevel(double x1,double y1) {
		long round = Math.round( Math.sqrt( Math.pow(x1 - 39,2  ) +  Math.pow(y1 - 116,2  ) ));
		if(round>0&&round<=15) {
			return 2;
		}else if(round>15&&round<=30) {
			return 3;
		}else if(round>30&&round<=40) {
			return 4;
		}else if(round>40&&round<=60) {
			return 5;
		}else if(round>60&&round<=70){
			return 6;
		}else {
			return 7;
		}
 }
 
/**
 * 计算违规类型
 */
 public static String passtype(String typeid,int looplevel,String carid) {
	 if(typeid.equals("A")&&looplevel==2) {
		 return "摩托车A进入2环";
	 }else if(typeid.equals("B")&&looplevel<=4){
		 return "摩托车B进入4环";
	 }else if(typeid.equals("C")&&carid.substring(0,1).equals("京")&&looplevel<=5) {
		 return "外地牌照不能进入5环";
	 }
	return null;
 }
 
 
 
}
