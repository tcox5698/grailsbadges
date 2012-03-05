package com.davai.merit

import com.davai.merit.event.LoginEvent
import com.davai.merit.handler.*

import grails.test.mixin.*
import org.junit.*
import groovy.mock.interceptor.MockFor

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(EventService)
class EventServiceTests extends junit.framework.TestCase {

    void testSomething() {
    	def inputPersonId = "inputPersonId"
    	def handled = [:]
    
	    LoginEvent loginEvent = new LoginEvent(personId: inputPersonId)
	    
		def mockController = mockFor(EventHandler.class)
		mockController.demand.handleEvent { event -> 
			System.out.println("handler called!")
			if (!event.personId.equals(inputPersonId)) {
				throw new RuntimeException("barfola")
			}
			handled["wasHandled"] = true
		}

		def mockHandler = mockController.createMock()
			    
	    service.registerHandler(LoginEvent.class, mockHandler)
		
		//EXECUTE
		service.processEvent(loginEvent)
		
		//VERIFY
		boolean wasHandled = handled["wasHandled"]
		assertTrue(wasHandled)
    }
}
