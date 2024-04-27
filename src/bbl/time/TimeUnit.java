package bbl.time;

public enum TimeUnit
{
 HOUR(3600), MINUTE(60), SECOND(1);
	int value;
	TimeUnit(int value)
	{
		this.value=value;
	}
	public int getValue()
	{
		return value;
	}
	public TimePoint between(TimePoint point1, TimePoint point2)
	{
		// return newTimePoint= point1-point2 
		TimePoint newTimePoint = new TimePoint(point1.convert(this).getAmount()-point2.convert(this).getAmount(),this);
		return newTimePoint;
	}
	
}
