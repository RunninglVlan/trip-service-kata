package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import java.util.*

open class TripService {

	fun getTripsByUser(user: User, loggedInUser: User?): List<Trip> {
		if (loggedInUser == null) {
			throw UserNotLoggedInException()
		}

		return if (user.isFriendsWith(loggedInUser)) {
			tripsBy(user)
		} else {
			noTrips()
		}
	}

	private fun noTrips(): ArrayList<Trip> = ArrayList()

	protected open fun tripsBy(user: User): List<Trip> {
		return TripDAO.findTripsByUser(user)
	}
}
