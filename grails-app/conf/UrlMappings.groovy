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
		
		"/admin/person/$action?"(controller: "person")
		"/admin/category/$action?"(controller: "category")	
		"/admin/role/$action?"(controller: "role")		
		"/admin/unlockedAchievement/$action?"(controller: "unlockedAchievement")			
		"/admin/user/$action?"(controller: "user")	
		"/admin/securityInfo/$action?"(controller: "securityInfo")	
		
		"/admin/registrationCode/$action?"(controller: "registrationCode")				
		"/admin/aclClass/$action?"(controller: "aclClass")	
		"/admin/aclEntry/$action?"(controller: "aclEntry")	
		"/admin/aclObjectIdentity/$action?"(controller: "aclObjectIdentity")	
		"/admin/aclSid/$action?"(controller: "aclSid")			
		"/admin/persistentLogin/$action?"(controller: "persistentLogin")	
		"/admin/requestMap/$action?"(controller: "requestMap")	
	}
}
