package bbl.timetest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bbl.time.*;


class TmePointTest {

	@Test
	void testBetween() 
	{
		TimePoint point1=new TimePoint(10, TimeUnit.HOUR);
		TimePoint point2=new TimePoint(3600*20, TimeUnit.SECOND);
		TimePoint point3=TimeUnit.MINUTE.between(point1, point2);
		assertEquals(-600,point3.getAmount());
		TimePoint point4=TimeUnit.SECOND.between(point1, point2);
		assertEquals(-36000,point4.getAmount());;
		TimePoint point5=TimeUnit.HOUR.between(point1, point2);
		assertEquals(-10,point5.getAmount());
		TimePoint point6=TimeUnit.HOUR.between(point2, point1);
		assertEquals(10,point6.getAmount());
	}
	
	@Test
	void convertTest()
	{
		TimePoint timePoint = new TimePoint(10,TimeUnit.HOUR);
		TimePoint pointActual=timePoint.convert(TimeUnit.SECOND);
		assertEquals(36000,pointActual.getAmount());
	}
	
	@Test
	void plusAdjusterTest()
	{
		TimePoint timePoint1=new TimePoint(10,TimeUnit.HOUR);
		TimePoint timePoint2=new TimePoint(60,TimeUnit.MINUTE);
		TimePoint actual=timePoint2.with(new PlusAdjuster(timePoint1));
		assertEquals(660, actual.getAmount());
		assertEquals(TimeUnit.MINUTE, actual.getTimeUnit());		
	}
	
	@Test
	void timePointEqualsTest()
	{
		TimePoint timePoint1=new TimePoint(10,TimeUnit.HOUR);
		TimePoint timePoint2=new TimePoint(60,TimeUnit.MINUTE);		
		assertTrue(timePoint1.equals(timePoint1));
		assertFalse(timePoint1.equals(null));
		int a=1;
		assertFalse(timePoint1.equals(a));
		assertFalse(timePoint1.equals(timePoint2));
		TimePoint timePoint3=new TimePoint(1,TimeUnit.HOUR);
		assertTrue(timePoint2.equals(timePoint3));
	}
	
	@Test 
	void timePointCompareToTest()
	{
		TimePoint timePoint1=new TimePoint(10,TimeUnit.HOUR);
		TimePoint timePoint2=new TimePoint(60,TimeUnit.MINUTE);	
		TimePoint timePoint3=new TimePoint(60*60,TimeUnit.SECOND);
		assertTrue(timePoint1.compareTo(timePoint2)>0);
		assertFalse(timePoint2.compareTo(timePoint1)>0);
		assertTrue(timePoint3.compareTo(timePoint1)<0);
		assertEquals(0,timePoint3.compareTo(timePoint2));
	}
	
	@Test
	void featureProximityAdjusterTest()
	{
		
		TimePoint[] timePoints = new TimePoint[] {	new TimePoint(1,TimeUnit.HOUR),
													new TimePoint(58*60,TimeUnit.SECOND),
													new TimePoint(58,TimeUnit.MINUTE), 
													new TimePoint(3700,TimeUnit.SECOND)
												  };
		FutureProximityAdjuster futureProximityAdjuster=new FutureProximityAdjuster(timePoints);
		
		TimePoint timePoint1= new TimePoint(59,TimeUnit.MINUTE);
		TimePoint actual1=timePoint1.with(futureProximityAdjuster);
		assertEquals(60,actual1.getAmount());
		
		TimePoint timePoint2= new TimePoint(0,TimeUnit.HOUR);
		TimePoint actual2=timePoint2.with(futureProximityAdjuster);
		assertEquals(0,actual2.getAmount());
		
		TimePoint timePoint3= new TimePoint(10,TimeUnit.HOUR);
		TimePoint actual3=timePoint3.with(futureProximityAdjuster);
		assertTrue(actual3==null);
		
		TimePoint[] timePoints1 = new TimePoint[] {	new TimePoint(1,TimeUnit.HOUR),
													new TimePoint(3600,TimeUnit.SECOND),
													new TimePoint(60,TimeUnit.MINUTE), 
													
												  };
		FutureProximityAdjuster futureProximityAdjuster1=new FutureProximityAdjuster(timePoints1);
		TimePoint timePoint4= new TimePoint(60,TimeUnit.MINUTE);
		TimePoint actual4=timePoint4.with(futureProximityAdjuster1);
		assertTrue(actual4==null);
	}
}
