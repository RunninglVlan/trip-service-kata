package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession
import java.util.*

open class TripService {

	fun getTripsByUser(user: User): List<Trip> {
		val loggedInUser: User = loggedInUser() ?: throw UserNotLoggedInException()

		return if (user.isFriendsWith(loggedInUser)) {
			tripsBy(user)
		} else {
			noTrips()
		}
	}

	private fun noTrips(): ArrayList<Trip> = ArrayList()

	protected open fun loggedInUser(): User? {
		return UserSession.instance.loggedUser
	}

	protected open fun tripsBy(user: User): List<Trip> {
		return TripDAO.findTripsByUser(user)
	}
}
