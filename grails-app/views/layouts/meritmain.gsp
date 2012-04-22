<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Merit Badges"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'meritmain.css')}" type="text/css">
        <r:require modules="bootstrap"/>
 		<g:layoutHead/>
        <g:javascript library="jquery" plugin="jquery"/>		
		
        <r:layoutResources />
	</head>
	<body>
		    

		
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">Merit Badges</a>
          <div class="nav-collapse">
            <ul class="nav">
              <li><a href="#">Home</a></li>
			  <li><a href="userDashboard">Dashboard</a></li>              
              <li><a href="admin-index">Admin</a></li>
              <sec:ifLoggedIn>
			  <li><a href="#user"><i class="icon-user icon-white"></i><sec:username /></a></li>
			  <!--<li><a href="#achievements"><span class="badge badge-success">2</span></a></li>-->
			  <li><a href="${request.contextPath}/j_spring_security_logout">sign out</a></li>
			  </sec:ifLoggedIn>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>		
		
		<div class="container">	
			<g:layoutBody/>
		</div>
      
		<footer class="container navbar navbar-fixed-top">
        <hr>
		<p>&copy; Davai 2012 
		</p>
		</footer>		
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
        <r:layoutResources />
	</body>
</html>