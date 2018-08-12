package com.wf.domain;

public class Reservation {
	private Rentee rentee;
	private Rental rental;
	private ReservationDescription reservationDescription;
	private Double tax = 0.0;
	private Double fee = 0.0;
	private Double discount = 0.0;
	private int reservationTimeslot = 0;
	
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

	public ReservationDescription getReservationDescription() {
		return reservationDescription;
	}

	public void setReservationDescription(ReservationDescription reservationDescription) {
		this.reservationDescription = reservationDescription;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
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
		totalPrice = (((reservationTimeslot * rental.getReservationTimeslot()) * rental.getPriceRate()) + tax + fee) - getDiscount();
		return totalPrice;
	}
	
	public Reservation clone() {
		Reservation clone = new Reservation();
		clone.rentee = rentee.clone();
		clone.rental = rental.clone();
		clone.reservationDescription = reservationDescription.clone();
		clone.tax = tax;
		clone.fee = fee;
		clone.discount = discount;
		clone.reservationTimeslot = reservationTimeslot;
		return clone;
	}
}
