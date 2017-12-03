package org.craftedsw.tripservicekata.user

import org.craftedsw.tripservicekata.trip.Trip

class UserBuilder {
	private User[] users = []
	private Trip[] trips = []

	static aUser() {
		return new UserBuilder()
	}

	def friendsWith(User... users) {
		this.users = users
		return this
	}

	def withTrips(Trip... trips) {
		this.trips = trips
		return this
	}

	def build() {
		def user = new User()
		users.toList().forEach { user.addFriend(it) }
		trips.toList().forEach { user.addTrip(it) }
		return user
	}
}