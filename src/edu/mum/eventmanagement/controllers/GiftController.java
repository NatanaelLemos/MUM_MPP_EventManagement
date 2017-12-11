package edu.mum.eventmanagement.controllers;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.GiftRepository;

public class GiftController extends ControllerBase<Gift> {

	public GiftController() {
		super(new GiftRepository());
	}

}
