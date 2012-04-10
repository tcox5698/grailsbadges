package com.davai.merit

import com.davai.merit.event.*
import com.davai.merit.handler.*
import org.springframework.web.context.support.WebApplicationContextUtils 
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.context.ApplicationContext 
import org.springframework.context.support.GenericApplicationContext

class EventService {

	def handlerRegistry = [:]

    def processEvent(Event event) {
		def handlerName = handlerRegistry[event.getClass().name]
		
		log.error("handlerName: " + handlerName)
		
		def GenericApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(ServletContextHolder.getServletContext()); 	
	    def EventHandler handler = ctx.getBean(handlerName)    
		handler.handleEvent(event)
    }
    
    def registerHandler(Class eventClass, String handlerName) {    
    	handlerRegistry[eventClass.name] = handlerName;
    }
}
