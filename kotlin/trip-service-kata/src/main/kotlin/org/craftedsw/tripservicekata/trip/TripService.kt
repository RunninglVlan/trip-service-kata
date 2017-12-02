package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import org.craftedsw.tripservicekata.user.UserSession
import java.util.*

open class TripService {

	fun getTripsByUser(user: User): List<Trip> {
		var tripList: List<Trip> = ArrayList<Trip>()
		val loggedUser: User? = loggedInUser()
		var isFriend: Boolean = false
		if (loggedUser != null) {
			for (friend in user.friends) {
				if (friend == loggedUser) {
					isFriend = true
					break
				}
			}
			if (isFriend) {
				tripList = tripsBy(user)
			}
			return tripList
		} else {
			throw UserNotLoggedInException()
		}
	}

	protected open fun loggedInUser(): User? {
		return UserSession.instance.loggedUser
	}

	protected open fun tripsBy(user: User): List<Trip> {
		return TripDAO.findTripsByUser(user)
	}
}
