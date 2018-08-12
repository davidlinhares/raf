package com.wf.domain.factory;

import com.wf.domain.Rental;

public class SimpleRentalFactory implements RentalFactory {

	@Override
	public Rental createRental(Class klass) {
		if(Rental.class.isAssignableFrom(klass.getClass())) {
			try {
				Object object = klass.newInstance();
				Rental rental = (Rental) object;
				return rental;
			} catch(Exception e) {
				// IllegalAccessException, InstantiationException
				System.out.println(e);
				return null;
			}
		} else {
			System.out.println("Class " + klass.getName() + " not Assignable From " + Rental.class.getName());
			return null;
		}
	}

}
