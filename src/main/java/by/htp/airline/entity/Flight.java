package by.htp.airline.entity;

import java.util.Date;

public class Flight {

	private int flightId;
	private Date date;
	private Date nowDate;
	private String strDate;
	private int filling;
	private int numberOfSeats;
	private int flightInfoId;
	private String flightNumber;
	private String fromPlace;
	private String toPlace;
	private double startingPrice;
	private boolean unBlockedFlight;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public int getFilling() {
		return filling;
	}

	public void setFilling(int filling) {
		this.filling = filling;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public int getFlightInfoId() {
		return flightInfoId;
	}

	public void setFlightInfoId(int flightInfoId) {
		this.flightInfoId = flightInfoId;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public boolean isUnBlockedFlight() {
		return unBlockedFlight;
	}

	public void setUnBlockedFlight(boolean unBlockedFlight) {
		this.unBlockedFlight = unBlockedFlight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + filling;
		result = prime * result + flightId;
		result = prime * result + flightInfoId;
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((fromPlace == null) ? 0 : fromPlace.hashCode());
		result = prime * result + ((nowDate == null) ? 0 : nowDate.hashCode());
		result = prime * result + numberOfSeats;
		long temp;
		temp = Double.doubleToLongBits(startingPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((strDate == null) ? 0 : strDate.hashCode());
		result = prime * result + ((toPlace == null) ? 0 : toPlace.hashCode());
		result = prime * result + (unBlockedFlight ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (filling != other.filling)
			return false;
		if (flightId != other.flightId)
			return false;
		if (flightInfoId != other.flightInfoId)
			return false;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (fromPlace == null) {
			if (other.fromPlace != null)
				return false;
		} else if (!fromPlace.equals(other.fromPlace))
			return false;
		if (nowDate == null) {
			if (other.nowDate != null)
				return false;
		} else if (!nowDate.equals(other.nowDate))
			return false;
		if (numberOfSeats != other.numberOfSeats)
			return false;
		if (Double.doubleToLongBits(startingPrice) != Double.doubleToLongBits(other.startingPrice))
			return false;
		if (strDate == null) {
			if (other.strDate != null)
				return false;
		} else if (!strDate.equals(other.strDate))
			return false;
		if (toPlace == null) {
			if (other.toPlace != null)
				return false;
		} else if (!toPlace.equals(other.toPlace))
			return false;
		if (unBlockedFlight != other.unBlockedFlight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [flightId=" + flightId + ", date=" + date + ", nowDate=" + nowDate
				+ ", strDate=" + strDate + ", filling=" + filling + ", numberOfSeats=" + numberOfSeats
				+ ", flightInfoId=" + flightInfoId + ", flightNumber=" + flightNumber + ", fromPlace=" + fromPlace
				+ ", toPlace=" + toPlace + ", startingPrice=" + startingPrice + ", unBlockedFlight=" + unBlockedFlight
				+ "]";
	}

}
