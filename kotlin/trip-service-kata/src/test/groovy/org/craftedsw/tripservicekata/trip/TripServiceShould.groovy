package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException
import org.craftedsw.tripservicekata.user.User
import spock.lang.Specification

import static org.craftedsw.tripservicekata.user.UserBuilder.aUser

class TripServiceShould extends Specification {

	static final GUEST = null
	static final SOME_USER = new User()
	static final REGISTERED_USER = new User()
	static final ANOTHER_USER = new User()

	static final LONDON = new Trip()
	static final RIGA = new Trip()

	def tripDao = Stub(TripDAO)
	def service = new TripService(tripDao)

	def 'throw exception if user is not logged in'() {
		when:
		service.userTrips(SOME_USER, GUEST)

		then:
		thrown(UserNotLoggedInException)
	}

	def 'return no trips if users are strangers'() {
		given:
		def stranger = aUser()
				.friendsWith(SOME_USER)
				.withTrips(LONDON)
				.build()

		expect:
		service.userTrips(stranger, REGISTERED_USER) == []
	}

	def 'return friend trips if users are friends'() {
		given:
		def friend = aUser()
				.friendsWith(REGISTERED_USER, ANOTHER_USER)
				.withTrips(LONDON, RIGA)
				.build()
		tripDao.tripsBy(friend) >> friend.trips

		expect:
		service.userTrips(friend, REGISTERED_USER).size() == 2
	}
}
