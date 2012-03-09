package com.davai.merit.listener

import org.springframework.context.ApplicationListener 
import org.springframework.security.authentication.event.AuthenticationSuccessEvent

class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
	def eventService

	void onApplicationEvent(AuthenticationSuccessEvent event) { 
		System.out.println("logging auth success event: " + event) 
	} 
}