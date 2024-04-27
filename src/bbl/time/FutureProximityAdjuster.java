package bbl.time;

import java.util.Comparator;

import bbl.util.Arrays;

public class FutureProximityAdjuster implements TimePointAdjuster
{
	TimePoint[] timePoints;
	public FutureProximityAdjuster(TimePoint[] points)
	{
		timePoints=Arrays.copy(points);
		Arrays.bubbleSort(timePoints);
	}
	
	@Override
	public TimePoint adjust(TimePoint point) 
	{
		int index=Arrays.binarySearch(timePoints, point,(a,b)->a.compareTo(b));
		TimePoint ret=null;
		if (index<0) index=-(index+1);
		if(index<(timePoints.length))
		{
			while (index<timePoints.length-1 && timePoints[index].compareTo(timePoints[index+1])==0) index++;
			ret=timePoints[index];
			ret=ret.convert(point.getTimeUnit());
		}
		return ret ;
	}

}
