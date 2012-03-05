package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.*

import grails.test.*
import groovy.mock.interceptor.*


class LoginEventHandlerTests extends GroovyTestCase {
	void testHandleEvent_SavesEventStatistic() {
		def personId = "inputPersonId"
		def LoginEventHandler handler = new LoginEventHandler()
		def loginEvent = new LoginEvent("personId":personId)
		
		def objectServiceController = new MockFor(ObjectService.class)
		
		handler.objectService = new ObjectService()
		
		//EXECUTE
		handler.handleEvent(loginEvent)
	}
}