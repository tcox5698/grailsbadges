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
		<div class="alert alert-info"><strong>1. <g:message code="prompt.achievement.nameDescriptionAndDate"/></strong></div>
		<div class="alert alert-info">2. <g:message code="prompt.achievement.categorize"/></div>
		<div class="alert alert-info">3. <g:message code="prompt.achievement.skillifyAndSave"/></div>		
	</div>
  </div>

<ul>
<g:eachError bean="${achievement}">
	<div class="alert alert-error">
		<g:message error="${it}"/>
	</div>
</g:eachError>
</ul>

  <form class="form-horizontal" action="${createLink(uri:'/achieve/saveName')}">
    <fieldset>
      <legend>1. <g:message code="prompt.achievement.nameDescriptionAndDate"/></legend>

      <div class="control-group">
        <label class="control-label" for="name"><g:message code="label.name"/>:</label>

        <div class="controls">
        	<g:field name="name" autofocus="true" value="${achievement?.name}"
        		class="input-xlarge" required="true"
        		type="text"/>

          <p class="help-block"><g:message code="prompt.achievement.name"/></p>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="description"><g:message code="label.description"/>:</label>

        <div class="controls">
			<g:textArea name="description" value="${achievement?.description}"
        		class="input-xlarge" maxlength="999"/>

	  <p class="help-block"><g:message code="prompt.achievement.description"/></p>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="unlockedDate"><g:message code="label.date"/>:</label>

        <div class="controls">
          <g:datePicker name="unlockedDate" precision="day" relativeYears="[-2..2]"/>
        </div>
      </div>

      <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="buttonAction" 
        	value="applyAndNext"><g:message code="button.applyAndMoveOnToCategorize"/>
        	<i class="icon-step-forward icon-white"></i></button>         
        <button type="submit" class="btn btn-primary" name="buttonAction" 
        	value="saveAndFinish"><g:message code="button.saveNowImDone"/> 
        	<i class="icon-ok icon-white"></i></button> 
        <button class="btn"><g:message code="button.cancel"/></button>
      </div>
    </fieldset>
  </form>

</body>
</html>
