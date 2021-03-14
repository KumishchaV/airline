package by.htp.airline.entity;

public class Plane {

	private int planeId;
	private String planeType;
	private int numberOfSeats;
	private int numberOfPilots;
	private int numberOfStewardesses;
	private boolean unBlockedEdit; 

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
		result = prime * result + numberOfPilots;
		result = prime * result + numberOfSeats;
		result = prime * result + numberOfStewardesses;
		result = prime * result + planeId;
		result = prime * result + ((planeType == null) ? 0 : planeType.hashCode());
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
		Plane other = (Plane) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [planeId=" + planeId + ", planeType=" + planeType
				+ ", numberOfSeats=" + numberOfSeats + ", numberOfPilots=" + numberOfPilots + ", numberOfStewardesses="
				+ numberOfStewardesses + "]";
	}

}