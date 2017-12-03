package org.craftedsw.tripservicekata.trip

import org.craftedsw.tripservicekata.exception.CollaboratorCallException
import org.craftedsw.tripservicekata.user.User
import spock.lang.Specification

class TripDAOShould extends Specification {

	def 'throw exception when retrieving user trips'() {
		when:
		new TripDAO().tripsBy(new User())

		then:
		thrown(CollaboratorCallException)
	}
}
