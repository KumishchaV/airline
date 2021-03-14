package by.htp.airline.entity;

import java.util.Date;

public class FlightHasEmployee {

	private int flightId;
	private int employeeId;
	private int flightInfoId;
	private String name;
	private String role;
	private Date date;
	private String flightNumber;
	private String toPlace;
	private String fromPlace;
	private String planeType;
	private int numberOfPilots;
	private int numberOfStewardesses;
	private boolean unBlockedAdd;
	private boolean unBlockedEdit;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getFlightInfoId() {
		return flightInfoId;
	}

	public void setFlightInfoId(int flightInfoId) {
		this.flightInfoId = flightInfoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
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

	public boolean isUnBlockedAdd() {
		return unBlockedAdd;
	}

	public void setUnBlockedAdd(boolean unBlockedAdd) {
		this.unBlockedAdd = unBlockedAdd;
	}

	public boolean isUnBlockedEdit() {
		return unBlockedEdit;
	}

	public void setUnBlockedEdit(boolean unBlockedEdit) {
		this.unBlockedEdit = unBlockedEdit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + employeeId;
		result = prime * result + flightId;
		result = prime * result + flightInfoId;
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((fromPlace == null) ? 0 : fromPlace.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numberOfPilots;
		result = prime * result + numberOfStewardesses;
		result = prime * result + ((planeType == null) ? 0 : planeType.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((toPlace == null) ? 0 : toPlace.hashCode());
		result = prime * result + (unBlockedAdd ? 1231 : 1237);
		result = prime * result + (unBlockedEdit ? 1231 : 1237);
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
		FlightHasEmployee other = (FlightHasEmployee) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (employeeId != other.employeeId)
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfPilots != other.numberOfPilots)
			return false;
		if (numberOfStewardesses != other.numberOfStewardesses)
			return false;
		if (planeType == null) {
			if (other.planeType != null)
				return false;
		} else if (!planeType.equals(other.planeType))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (toPlace == null) {
			if (other.toPlace != null)
				return false;
		} else if (!toPlace.equals(other.toPlace))
			return false;
		if (unBlockedAdd != other.unBlockedAdd)
			return false;
		if (unBlockedEdit != other.unBlockedEdit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [flightId=" + flightId + ", employeeId=" + employeeId
				+ ", flightInfoId=" + flightInfoId + ", name=" + name + ", role=" + role + ", date=" + date
				+ ", flightNumber=" + flightNumber + ", toPlace=" + toPlace + ", fromPlace=" + fromPlace
				+ ", planeType=" + planeType + ", numberOfPilots=" + numberOfPilots + ", numberOfStewardesses="
				+ numberOfStewardesses + ", unBlockedAdd=" + unBlockedAdd + ", unBlockedEdit=" + unBlockedEdit + "]";
	}

}
