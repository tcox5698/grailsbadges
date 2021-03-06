<!DOCTYPE html>
<html>
   <head>
	  <meta name="layout" content="main">   
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Merit Badges - User Achievements</title>
   </head>
  <body>
  		<table class="table table-condensed" id="userAchievementListTable">
  			<thead >
  			<tr><th colspan="100%" class="pagingCell"><g:paginate controller="userDashboard" action="userAchievementList" 
  				total="${achievementCount}" max="7"/></th></tr>
  			<tr id="columnHeaders">
  				<th>Date</th>
  				<th>Description</th>
				<th>Categories</th>  				  				
  				<th>Skill Level</th>
  				<th>Multiplier</th>
  
  			</tr>
			</thead>
			<tbody>  				
		<% unlockedAchievements.each { achievement -> %>
  			<tr>
  				<td><h3><g:formatDate date="${achievement.unlockedDate}" type="date"
					 style="SHORT" timezone="${tz}"/></h3></td>
  				<td><h3>${achievement.name ? achievement.name : g.message(code:achievement.messageKey,args:achievement.getMessageArgs())}</h3></td>
  				<td><h3>${achievement.categories.collect{it.name}.join(", ")}</h3> </td>  		
   				<td><h3>${achievement.skillLevel?.name}</h3> <small>${achievement.skillLevel?.description}</small></td>
   				<td><h3>${achievement.skillLevel?.multiplier}</h3></td>
		
  			</tr>  	
		<% } %>  	
					</tbody>	
					<tfoot>  			
						<tr><th colspan="100%"><g:paginate controller="userDashboard" action="userAchievementList" 
  				total="${achievementCount}" max="7"/></th></tr>
  					</tfoot>
  		</table>	
  </body>

</html>