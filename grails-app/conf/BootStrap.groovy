import com.davai.merit.EventService
import com.davai.merit.event.*

class BootStrap {
	def eventService

    def init = { servletContext ->
    	log.info "initing BootStrap"
    	eventService.registerHandler(LoginEvent.class, "loginEventHandlerService")
    	
    	
    }
    def destroy = {
    }
}
