package com.wf.domain.factory;

import com.wf.domain.Rental;

public interface RentalFactory {
	public Rental createRental(Class klass);
}
