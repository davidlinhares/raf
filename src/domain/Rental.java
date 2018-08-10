package domain;

@Reservable
public abstract class Rental {
	private Double tax = 0.0;
	private Double priceRate = 0.0;
	private Double reservationTimeslot = 0.0;
	
	public Double getTax() {
		return tax;
	}
	
	public Double getPriceRate() {
		return priceRate;
	}
	
	public Double getReservationTimeslot() {
		return reservationTimeslot;
	}
	
	public Double getBasePrice() {
		return (priceRate) + ((priceRate / 100) * tax);
	}
}
