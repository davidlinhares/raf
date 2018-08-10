package domain;

public abstract class Reservation {
	private Rentee rentee;
	private Rental rental;
	private Double discount;
	private int reservationTimeslot;
	
	public Rentee getRentee() {
		return rentee;
	}

	public void setRentee(Rentee rentee) {
		this.rentee = rentee;
	}

	public Rental getRental() {
		return rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public Double getDiscount() {
		return discount;
	}
	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public int getReservationTimeslot() {
		return reservationTimeslot;
	}
	
	public void setReservationTimeslot(int reservationTimeslot) {
		this.reservationTimeslot = reservationTimeslot;
	}
	
	public Double getTotalPrice() {
		Double totalPrice = 0.0;
		totalPrice = (reservationTimeslot * rental.getBasePrice()) - getDiscount();
		return totalPrice;
	}
}
