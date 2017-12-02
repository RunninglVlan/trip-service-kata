package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import spock.lang.Specification

class TripServiceShould extends Specification {

	static final GUEST = null
	static final SOME_USER = new User()
	static final REGISTERED_USER = new User()
	static final ANOTHER_USER = new User()
	static final LONDON = new Trip()
	static final RIGA = new Trip()

	def service = new TestableTripService()

	def loggedInUser

	def 'throw exception if user is not logged in'() {
		given:
		loggedInUser = GUEST

		when:
		service.getTripsByUser(SOME_USER)

		then:
		thrown(UserNotLoggedInException)
	}

	def 'return no trips if users are strangers'() {
		given:
		loggedInUser = REGISTERED_USER
		def stranger = new User()
		stranger.addFriend(SOME_USER)
		stranger.addTrip(LONDON)

		expect:
		service.getTripsByUser(stranger) == []
	}

	def 'return friend trips if users are friends'() {
		given:
		loggedInUser = REGISTERED_USER
		def friend = new User()
		friend.addFriend(REGISTERED_USER)
		friend.addFriend(ANOTHER_USER)
		friend.addTrip(LONDON)
		friend.addTrip(RIGA)

		expect:
		service.getTripsByUser(friend).size() == 2
	}

	class TestableTripService extends TripService {
		User loggedInUser() {
			return loggedInUser
		}

		List<Trip> tripsBy(User user) {
			return user.trips
		}
	}
}
