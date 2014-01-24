<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
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
<g:eachError bean="${unlockedAchievement}">
	<div class="alert alert-error">
		<g:message error="${it}"/>
	</div>
</g:eachError>
</ul>

  <g:form class="form-horizontal">
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
        <g:submitButton name="continue" value="Continue" class="btn btn-primary"/>
        <g:submitButton name="saveAndDone" value="Save and Done" class="btn btn-primary"/>        
		<g:submitButton name="cancel" value="Cancel" class="btn"/>                
      </div>
    </fieldset>
  </g:form>
<script>
	jQuery.noConflict();
	jQuery(function() {	

		jQuery.ajaxSetup({"error":function(XMLHttpRequest,textStatus, errorThrown) { 
			alert("Ouch! Something went wrong - sorry, but we have to start over.")
			window.location.href='<g:createLink controller="userDashboard" />'		
		}});		
			
		jQuery( "#name" ).autocomplete({
			source: '<g:createLink controller="achieve" action="achievementList" />'
		});		
	});
</script>  
</body>
</html>
