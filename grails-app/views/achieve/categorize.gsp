<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
  <meta name="generator" content="HTML Tidy for Mac OS X (vers 31 October 2006 - Apple Inc. build 15.3.6), see www.w3.org">
  <meta name="layout" content="main">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title></title>
</head>

<body>
  <br>
  <div class="row">
  	<h1>Log an Achievement!</h1>
	<div class="span12 well">
		<div class="alert alert-info">1. <g:message code="prompt.achievement.nameDescriptionAndDate"/></div>
		<div class="alert alert-info"><strong>2. <g:message code="prompt.achievement.categorize"/></strong></div>
		<div class="alert alert-info">3. <g:message code="prompt.achievement.skillifyAndSave"/></div>	
	</div>
  </div>
  <form class="form-horizontal" action="${createLink(uri:'/achieve/skillify')}">
    <fieldset>
      <legend>Categorize!</legend>

      <div class="control-group">
        <label class="control-label" for="category">Type a Category:</label>

        <div class="controls">
          <input type="text" class="input-xlarge" id="category">

        </div>
      </div>

      <div class="well">
      	<a class="btn btn-inverse ">Java <i class="icon-remove icon-white"></i></a>
      	<a class="btn btn-inverse ">Release Process <i class="icon-remove icon-white"></i></a>
      	<a class="btn btn-inverse ">SQL <i class="icon-remove icon-white"></i></a>
      	<a class="btn btn-inverse ">Database Schema Design<i class="icon-remove icon-white"></i></a>
      	<a class="btn btn-inverse ">Automated Functional Testing <i class="icon-remove icon-white"></i></a>	      	
      </div>

      <div class="form-actions">
        <button type="submit" class="btn btn-primary">Apply and move on to Skill-ify<i class="icon-step-forward icon-white"></i></button> 
        <button type="submit" class="btn btn-primary">Save now - I'm done! <i class="icon-ok icon-white"></i></button>         
        <button class="btn">Cancel</button>
      </div>
    </fieldset>
  </form>
</body>
</html>
