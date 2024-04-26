package bbl.time;

import java.util.Objects;

import bbl.shapes.Shape;

public class TimePoint implements Comparable<TimePoint>
{
	int amount;
	public int getAmount() {
		return amount;
	}
	public TimeUnit getTimeUnit() 
	{
		return timeUnit;
	}
	TimeUnit timeUnit;
	public TimePoint(int amount, TimeUnit timeUnit)
	{
		this.amount = amount;
		this.timeUnit = timeUnit;
	}
	public TimePoint convert(TimeUnit second)
	{
		// returns new TimePoint with a given TimeUnit
		int newamount=amount*timeUnit.getValue()/second.getValue();
		TimePoint newTimePoint = new TimePoint(newamount,second);
		return newTimePoint;
	}
	
	public TimePoint with(TimePointAdjuster adjuster)
	{
		
		// returns new TimePoint based on any TimePointAdjuster
		
		return adjuster.adjust(this);
	}
	
	@Override
	public int compareTo(TimePoint o) 
	{
		TimePoint t=TimeUnit.SECOND.between(this,o);
		return t.getAmount();
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimePoint other = (TimePoint) obj;
		return convert(TimeUnit.SECOND).amount == other.convert(TimeUnit.SECOND).amount;
	}

	
	
}
