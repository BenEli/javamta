package com.myorg.javacourse;

public class MathManager {

	
	private static double radius ;
	private double areaOfACircle;
	private static double angleB , hypotenus ; 
	private double  opposite;
	private static double base, exp  ;
	private double power ;
	
	public static void setRadius(double initialRadius){
		radius = initialRadius ;
	}
	public static void setTriangle(double iniTialangleB , double initialHypotenus){
		angleB = iniTialangleB ;
		hypotenus = initialHypotenus ;
	}
	public static void setBaseNExp(double initialBase , double initialExp ){
		base = initialBase ;
		exp = initialExp ;
	}
	public void circleArea (){	
		areaOfACircle = Math.PI * Math.pow(radius, 2) ;	
	}
	public void opposite(){
		opposite = Math.sin(Math.toRadians(angleB))*hypotenus ;	
	}
	public void power(){
		power = Math.pow(base, exp);
	}
	
	public String getResults(){
		String resultStr ;
		circleArea();
		opposite();
		power();
		String line1 = new String("calculation 1: Area of circle with radius " + radius + " = " + areaOfACircle );
		String line2 = new String("calculation 2: Length of opposite where angle B is  "+ angleB + "and hypotenus is " + hypotenus + " = " + opposite);
		String line3 = new String("calculation 3: Power of " +base+ " with exp of	"+exp+"  =  " + power );

		return resultStr = (line1 + "<br>" + line2 + "<br>" +line3 );			
		
	}
	
}
