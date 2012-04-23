<!DOCTYPE html>
<html>
   <head>
	  <meta name="layout" content="main">   
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
  <body>
	<br/>
    <h3><g:message code="label.recent.achievements" default="Recent Achievements"/></h3>
    <% unlockedAchievements.each { achievement -> %>
    <div class="meritBadge">
            <img src="images/award-ribbon-badge-shaded.png"/>
            <div class="meritBadgeTitle"><g:message code="${achievement.messageKey}" 
            	args="${[achievement.messageArguments]}"/></div>
            <div class="meritBadgeDate"><g:formatDate date="${achievement.unlockedDate}" type="date"
            	 style="SHORT" timezone="${tz}"/></div>
    </div>	
    <% } %>


  </body>
</html>