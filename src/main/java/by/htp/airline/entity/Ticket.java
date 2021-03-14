package by.htp.airline.entity;

import java.util.Date;

public class Ticket {

	private int ticketId;
	private int flightId;
	private int ticketNumber;
	private String placeNumber;
	private double startingPrice;
	private double finalPrice;
	private boolean luggage;
	private boolean priorityBoarding;
	private boolean priorityRegistration;
	private int userId;
	private int accountId;
	private Date departing;
	private String flightTime;
	private Date arriving;
	private String flightNumber;
	private String fromPlace;
	private String toPlace;
	private String planeType;
	private boolean placesVisible;
	private boolean bookedPlacesVisible;

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getPlaceNumber() {
		return placeNumber;
	}

	public void setPlaceNumber(String placeNumber) {
		this.placeNumber = placeNumber;
	}

	public double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public boolean isLuggage() {
		return luggage;
	}

	public void setLuggage(boolean luggage) {
		this.luggage = luggage;
	}

	public boolean isPriorityBoarding() {
		return priorityBoarding;
	}

	public void setPriorityBoarding(boolean priorityBoarding) {
		this.priorityBoarding = priorityBoarding;
	}

	public boolean isPriorityRegistration() {
		return priorityRegistration;
	}

	public void setPriorityRegistration(boolean priorityRegistration) {
		this.priorityRegistration = priorityRegistration;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Date getDeparting() {
		return departing;
	}

	public void setDeparting(Date departing) {
		this.departing = departing;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public Date getArriving() {
		return arriving;
	}

	public void setArriving(Date arriving) {
		this.arriving = arriving;
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

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public boolean isPlacesVisible() {
		return placesVisible;
	}

	public void setPlacesVisible(boolean placesVisible) {
		this.placesVisible = placesVisible;
	}

	public boolean isBookedPlacesVisible() {
		return bookedPlacesVisible;
	}

	public void setBookedPlacesVisible(boolean bookedPlacesVisible) {
		this.bookedPlacesVisible = bookedPlacesVisible;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((arriving == null) ? 0 : arriving.hashCode());
		result = prime * result + (bookedPlacesVisible ? 1231 : 1237);
		result = prime * result + ((departing == null) ? 0 : departing.hashCode());
		long temp;
		temp = Double.doubleToLongBits(finalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + flightId;
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((flightTime == null) ? 0 : flightTime.hashCode());
		result = prime * result + ((fromPlace == null) ? 0 : fromPlace.hashCode());
		result = prime * result + (luggage ? 1231 : 1237);
		result = prime * result + ((placeNumber == null) ? 0 : placeNumber.hashCode());
		result = prime * result + (placesVisible ? 1231 : 1237);
		result = prime * result + ((planeType == null) ? 0 : planeType.hashCode());
		result = prime * result + (priorityBoarding ? 1231 : 1237);
		result = prime * result + (priorityRegistration ? 1231 : 1237);
		temp = Double.doubleToLongBits(startingPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ticketId;
		result = prime * result + ticketNumber;
		result = prime * result + ((toPlace == null) ? 0 : toPlace.hashCode());
		result = prime * result + userId;
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
		Ticket other = (Ticket) obj;
		if (accountId != other.accountId)
			return false;
		if (arriving == null) {
			if (other.arriving != null)
				return false;
		} else if (!arriving.equals(other.arriving))
			return false;
		if (bookedPlacesVisible != other.bookedPlacesVisible)
			return false;
		if (departing == null) {
			if (other.departing != null)
				return false;
		} else if (!departing.equals(other.departing))
			return false;
		if (Double.doubleToLongBits(finalPrice) != Double.doubleToLongBits(other.finalPrice))
			return false;
		if (flightId != other.flightId)
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
		if (luggage != other.luggage)
			return false;
		if (placeNumber == null) {
			if (other.placeNumber != null)
				return false;
		} else if (!placeNumber.equals(other.placeNumber))
			return false;
		if (placesVisible != other.placesVisible)
			return false;
		if (planeType == null) {
			if (other.planeType != null)
				return false;
		} else if (!planeType.equals(other.planeType))
			return false;
		if (priorityBoarding != other.priorityBoarding)
			return false;
		if (priorityRegistration != other.priorityRegistration)
			return false;
		if (Double.doubleToLongBits(startingPrice) != Double.doubleToLongBits(other.startingPrice))
			return false;
		if (ticketId != other.ticketId)
			return false;
		if (ticketNumber != other.ticketNumber)
			return false;
		if (toPlace == null) {
			if (other.toPlace != null)
				return false;
		} else if (!toPlace.equals(other.toPlace))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [ticketId=" + ticketId + ", flightId=" + flightId + ", ticketNumber="
				+ ticketNumber + ", placeNumber=" + placeNumber + ", startingPrice=" + startingPrice + ", finalPrice="
				+ finalPrice + ", luggage=" + luggage + ", priorityBoarding=" + priorityBoarding
				+ ", priorityRegistration=" + priorityRegistration + ", userId=" + userId + ", accountId=" + accountId
				+ ", departing=" + departing + ", flightTime=" + flightTime + ", arriving=" + arriving
				+ ", flightNumber=" + flightNumber + ", fromPlace=" + fromPlace + ", toPlace=" + toPlace
				+ ", planeType=" + planeType + ", placesVisible=" + placesVisible + ", bookedPlacesVisible="
				+ bookedPlacesVisible + "]";
	}

}
