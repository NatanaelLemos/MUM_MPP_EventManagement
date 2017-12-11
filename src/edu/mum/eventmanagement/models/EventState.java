package edu.mum.eventmanagement.models;

public enum EventState {
	pending,
	approved,
	notApproved,
	locked //Valid only for event. After it achieve the due date, it will be lock and after that nobody can create schedule anymore
}
