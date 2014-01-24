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
  <g:form class="form-horizontal">
    <fieldset>
      <legend>What skill level did this demonstrate?</legend>

      <div class="control-group">
        <label class="control-label" for="category">Select a skill level:</label>

        <div class="controls">
          <g:radioGroup name="skillLevel"
              labels="${skillLevels*.name}"
              values="${skillLevels*.id}"
              value="${skillLevels[0].id}">
			<h3 >${it.radio} ${it.label.split(":")[0]} <small>${it.label.split(":")[1]}</small> </h3>
		  </g:radioGroup>

        </div>
      </div>

      <div class="form-actions">
        <g:submitButton name="saveAndDone" value="Save and Done" class="btn btn-primary"/>        
		<g:submitButton name="cancel" value="Cancel" class="btn"/>  
      </div>
    </fieldset>
  </g:form>
</body>
</html>
