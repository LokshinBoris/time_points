package bbl.time;

public class PlusAdjuster implements TimePointAdjuster
{
	TimePoint timePoint;
	
	public PlusAdjuster(TimePoint timePoint)
	{
		this.timePoint = timePoint;
	}

	@Override
	public TimePoint adjust(TimePoint point)
	{
		TimePoint pointSec= point.convert(TimeUnit.SECOND);
		TimePoint timePointSec = timePoint.convert(TimeUnit.SECOND);
		int sumSeconds=pointSec.getAmount()+timePointSec.getAmount();
		TimePoint resSec=new TimePoint(sumSeconds,TimeUnit.SECOND);
		return resSec.convert(point.timeUnit);
	}

}
