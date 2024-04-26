package bbl.time;

import java.util.Comparator;

import bbl.util.Arrays;

public class ProximityAdjuster implements TimePointAdjuster
{
	TimePoint[] timePoints;
	public ProximityAdjuster(TimePoint[] points)
	{
		timePoints=points;
		for(int i=0; i<timePoints.length;i++)
		{
			timePoints[i]=timePoints[i].convert(TimeUnit.SECOND);
		}
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
			ret=timePoints[index];
			ret = new TimePoint(timePoints[index].getAmount(), timePoints[index].getTimeUnit());
			ret=ret.convert(point.getTimeUnit());
		}
		return ret ;
	}

}
