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
		<div class="alert alert-info">2. <g:message code="prompt.achievement.categorize"/></div>
		<div class="alert alert-info"><strong>3. <g:message code="prompt.achievement.skillifyAndSave"/></strong></div>

	</div>
  </div>
  <form class="form-horizontal" action="${createLink(uri:'/userDashboard')}">
    <fieldset>
      <legend>What skill level did this demonstrate?</legend>

      <div class="control-group">
        <label class="control-label" for="category">Select a skill level:</label>

        <div class="controls">
          <g:radioGroup name="lovesGrails"
              labels="['1. Learner','2. Beginner','3. Practitioner','4. Expert','5. Leader','6. Authority']"
              values="['Learner','Beginner','Practitioner','Expert','Leader','Authority']">
<p>${it.radio} ${it.label}</p>
</g:radioGroup>

        </div>
      </div>

      <div class="form-actions">
        <button type="submit" class="btn btn-primary">Save now - I'm done! <i class="icon-ok icon-white"></i></button>  
        <button class="btn">Cancel</button>
      </div>
    </fieldset>
  </form>
</body>
</html>
