package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import java.util.*

open class TripService(private val tripDao: TripDAO) {

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

	private fun tripsBy(user: User): List<Trip> = tripDao.tripsBy(user)
}
