<!DOCTYPE html>
<html>
   <head>
	  <meta name="layout" content="meritmain">   
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
<body>
	<br/>

    <% unlockedAchievements.each { achievement -> %>
    <div class="meritBadge">
            <img src="images/award-ribbon-badge-shaded.png"/>
            <div class="meritBadgeTitle"><g:message code="${achievement.messageKey}" 
            	args="${[achievement.messageArguments]}"/></div>
            <div class="meritBadgeDate"><g:formatDate date="${achievement.unlockedDate}" type="datetime" style="SHORT"/></div>
    </div>	
    <% } %>


</body>
</html>