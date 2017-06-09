package czw.util;

import java.util.Date;

public class ScheduleUtil {
	
	private Date startedAt;
	
	private Date finishedAt;
	
	private RoomType type;
	
	private double price;

	public ScheduleUtil(){}
	
	public ScheduleUtil(Date startedAt, Date finishedAt, RoomType type, double price) {
		this.startedAt = startedAt;
		this.finishedAt = finishedAt;
		this.type = type;
		this.price = price;
	}

	public Date getStartedAt() {
		return startedAt;
	}

	public Date getFinishedAt() {
		return finishedAt;
	}

	public RoomType getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public void setFinishedAt(Date finishedAt) {
		this.finishedAt = finishedAt;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}