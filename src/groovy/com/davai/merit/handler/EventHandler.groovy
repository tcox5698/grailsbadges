package com.davai.merit.handler

import com.davai.merit.event.*

interface EventHandler {
	void handleEvent(Event event) 
}