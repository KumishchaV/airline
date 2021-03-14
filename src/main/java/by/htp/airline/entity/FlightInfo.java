package by.htp.airline.entity;

public class FlightInfo {

	private int flightInfoId;
	private String flightNumber;
	private String fromPlace;
	private String toPlace;
	private String flightTime;
	private double startingPrice;
	private int planeId;
	private String planeType;
	private int numberOfSeats;
	private int numberOfPilots;
	private int numberOfStewardesses;
	private boolean unBlockedFlightInfo; 

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

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public int getPlaneId() {
		return planeId;
	}

	public void setPlaneId(int planeId) {
		this.planeId = planeId;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public int getNumberOfPilots() {
		return numberOfPilots;
	}

	public void setNumberOfPilots(int numberOfPilots) {
		this.numberOfPilots = numberOfPilots;
	}

	public int getNumberOfStewardesses() {
		return numberOfStewardesses;
	}

	public void setNumberOfStewardesses(int numberOfStewardesses) {
		this.numberOfStewardesses = numberOfStewardesses;
	}

	public boolean isUnBlockedFlightInfo() {
		return unBlockedFlightInfo;
	}

	public void setUnBlockedFlightInfo(boolean unBlockedFlightInfo) {
		this.unBlockedFlightInfo = unBlockedFlightInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + flightInfoId;
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((flightTime == null) ? 0 : flightTime.hashCode());
		result = prime * result + ((fromPlace == null) ? 0 : fromPlace.hashCode());
		result = prime * result + numberOfPilots;
		result = prime * result + numberOfSeats;
		result = prime * result + numberOfStewardesses;
		result = prime * result + planeId;
		result = prime * result + ((planeType == null) ? 0 : planeType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(startingPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((toPlace == null) ? 0 : toPlace.hashCode());
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
		FlightInfo other = (FlightInfo) obj;
		if (flightInfoId != other.flightInfoId)
			return false;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (flightTime == null) {
			if (other.flightTime != null)
				return false;
		} else if (!flightTime.equals(other.flightTime))
			return false;
		if (fromPlace == null) {
			if (other.fromPlace != null)
				return false;
		} else if (!fromPlace.equals(other.fromPlace))
			return false;
		if (numberOfPilots != other.numberOfPilots)
			return false;
		if (numberOfSeats != other.numberOfSeats)
			return false;
		if (numberOfStewardesses != other.numberOfStewardesses)
			return false;
		if (planeId != other.planeId)
			return false;
		if (planeType == null) {
			if (other.planeType != null)
				return false;
		} else if (!planeType.equals(other.planeType))
			return false;
		if (Double.doubleToLongBits(startingPrice) != Double.doubleToLongBits(other.startingPrice))
			return false;
		if (toPlace == null) {
			if (other.toPlace != null)
				return false;
		} else if (!toPlace.equals(other.toPlace))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [flightInfoId=" + flightInfoId + ", flightNumber=" + flightNumber
				+ ", fromPlace=" + fromPlace + ", toPlace=" + toPlace + ", flightTime=" + flightTime
				+ ", startingPrice=" + startingPrice + ", planeId=" + planeId + ", planeType=" + planeType
				+ ", numberOfSeats=" + numberOfSeats + ", numberOfPilots=" + numberOfPilots + ", numberOfStewardesses="
				+ numberOfStewardesses + "]";
	}

}
