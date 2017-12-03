package org.craftedsw.tripservicekata.user

import spock.lang.Specification

import static org.craftedsw.tripservicekata.user.UserBuilder.aUser

class UserShould extends Specification {

	static final BOB = new User()
	static final PAUL = new User()

	def 'inform when users are friends or not'() {
		given:
		def user = aUser().friendsWith(BOB).build()

		expect:
		user.isFriendsWith(anotherUser) == areFriends

		where:
		anotherUser | areFriends
		BOB         | true
		PAUL        | false
	}
}
