class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"/admin-index"(view:"/admin-index")
		"500"(view:'/error')
		
		"/login/$action?"(controller: "login")
		"/logout/$action?"(controller: "logout")
	}
}
