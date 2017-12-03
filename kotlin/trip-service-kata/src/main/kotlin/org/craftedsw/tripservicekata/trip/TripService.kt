package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import java.util.*

open class TripService(private val tripDao: TripDAO) {

	fun userTrips(user: User, loggedInUser: User?): List<Trip> {
		validate(loggedInUser)

		return if (user.isFriendsWith(loggedInUser)) {
			tripsBy(user)
		} else {
			noTrips()
		}
	}

	private fun validate(loggedInUser: User?) {
		if (loggedInUser == null) {
			throw UserNotLoggedInException()
		}
	}

	private fun noTrips(): ArrayList<Trip> = ArrayList()

	private fun tripsBy(user: User): List<Trip> = tripDao.tripsBy(user)
}
