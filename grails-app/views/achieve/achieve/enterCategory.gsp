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
		<div class="alert alert-info">1. <g:message code="prompt.achievement.nameDescriptionAndDate"/></div>
		<div class="alert alert-info"><strong>2. <g:message code="prompt.achievement.categorize"/></strong></div>
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
      <legend>Categorize!</legend>

      <div class="control-group">
        <label class="control-label" for="category">Type a Category:</label>

        <div class="controls">
        	<g:field name="name" autofocus="true" value="${achievement?.category?.name}"
        		class="input-xlarge"
        		type="text"/>

        </div>
      </div>

      <div class="well" id="displayCategories">
      	<g:hiddenField name="selectedCategories" value="${suggestedCategories*.name.join(',')}"/>
      	<g:each in="${suggestedCategories}">	
      		<a class="btn btn-inverse categoryButton">${it.name} <i class="icon-remove icon-white"></i></a>
      	</g:each>
      </div>

      <div class="form-actions">
        <g:submitButton name="continue" value="Apply and move on to Skill-ify!" class="btn btn-primary"/>
        <g:submitButton name="saveAndDone" value="Save and Done" class="btn btn-primary"/>        
		<g:submitButton name="cancel" value="Cancel" class="btn"/>  
      </div>
    </fieldset>
  </g:form>
<script>
	jQuery.noConflict();
	jQuery(function() {	
		listenToCategoryButtons()
		
		jQuery('#name').keypress(function(event) {
			if ( event.which == 13 ) {
				var categoryName = jQuery('#name').val();
				if ('' != categoryName) {
					event.preventDefault();
					addSelectedCategory(jQuery('#name').val());
					jQuery('#name').val('');
				}
			}
		});
	
		jQuery.ajaxSetup({"error":function(XMLHttpRequest,textStatus, errorThrown) { 
			alert("Ouch! Something went wrong - sorry, but we have to start over.")
			window.location.href='<g:createLink controller="userDashboard" />'		
		}});		
		
		jQuery( "#name" ).autocomplete({
			source: '<g:createLink controller="achieve" action="categoryList" />',
			select:function(event,ui) {
				addSelectedCategory(ui.item.value)
				jQuery( "#name" ).val('')
			}
		});			
	});
	
	function addSelectedCategory(categoryName) {		
		var currentCats = jQuery('#selectedCategories').val()	
		categoryName = categoryName.trim()
		var addlCat = currentCats == "" ? categoryName : currentCats + ',' + categoryName	;
		var cats = addlCat.split(/\s*\,\s*/)
		cats = jQuery.unique(cats)
		jQuery('#selectedCategories').val(cats.join(','));	
		jQuery('.categoryButton').remove();	
		jQuery.each(cats,function() {
			if (this.length > 0) {
				jQuery('<a class="btn btn-inverse categoryButton">' + this.trim() 
				+ ' <i class="icon-remove icon-white "></i></a>').appendTo("#displayCategories");			
			}
		});
			
		listenToCategoryButtons();	
	}
	
	function listenToCategoryButtons() {
		jQuery('.categoryButton').click(function() {
			jQuery(this).slideUp(200, function(){jQuery(this).remove();});	
			var cats = jQuery('#selectedCategories').val().split(',')
			var clickedCat = jQuery(this).html().split('<i')[0].trim()
			cats = jQuery.unique(cats)
			var index = jQuery.inArray(clickedCat,cats)
			
			cats.splice(index,1)	
			jQuery('#selectedCategories').val(cats.join(','))
		});
	}
	</script>   
</body>
</html>
