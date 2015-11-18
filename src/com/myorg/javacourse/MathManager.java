package com.myorg.javacourse;

public class MathManager {

	private double radius ;
	private double areaOfACircle;
	private double angleB , hypotenus ; 
	private double opposite;
	private double base, exp  ;
	private double power ;
	
	public void setRadius(double initialRadius){
		radius = initialRadius ;
	}
	public void setTriangle(double iniTialangleB , double initialHypotenus){
		angleB = iniTialangleB ;
		hypotenus = initialHypotenus ;
	}
	public void setBaseNExp(double initialBase , double initialExp ){
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
		String line1 = new String("calculation 1: Area of circle with radius " + radius + " is : " + areaOfACircle );
		String line2 = new String("calculation 2: Length of opposite where angle B is  "+ angleB + "and hypotenus is " + hypotenus + " is : " + opposite);
		String line3 = new String("calculation 3: Power of " +base+ " with exp of	"+exp+"  is :  " + power );

		return resultStr = (line1 + "<br>" + line2 + "<br>" +line3 );			
		
	}
	
}
