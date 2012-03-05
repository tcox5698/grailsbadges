package com.davai.merit

import com.davai.merit.event.*
import com.davai.merit.handler.*

class EventService {

	def handlerRegistry = [:]

    def processEvent(Event event) {
		def handler = handlerRegistry[event.getClass().name]
		
		handler.handleEvent(event)
    }
    
    def registerHandler(Class eventClass, Object handler) {
		System.out.println("classname: " + eventClass.name)
    	handlerRegistry[eventClass.name] = handler;
    }
}
