package controller;

import controllerRequest.InputRequest;

public abstract class Controller {
	
	public static void AddData() {

	}

	public static void searchData(InputRequest request) {
		SearchController.SEARCH_DATA(request);
	}

	public static void addEntityToDb() {

	}

}
