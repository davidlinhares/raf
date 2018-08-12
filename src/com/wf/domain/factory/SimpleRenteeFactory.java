package com.wf.domain.factory;

import com.wf.domain.Rentee;

public class SimpleRenteeFactory implements RenteeFactory {

	@Override
	public Rentee createRentee(Class klass) {
		if(Rentee.class.isAssignableFrom(klass.getClass())) {
			try {
				Object object = klass.newInstance();
				Rentee rentee = (Rentee) object;
				return rentee;
			} catch(Exception e) {
				// IllegalAccessException, InstantiationException
				System.out.println(e);
				return null;
			}
		} else {
			System.out.println("Class " + klass.getName() + " not Assignable From " + Rentee.class.getName());
			return null;
		}
	}

}
