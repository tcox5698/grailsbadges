<!DOCTYPE html>
<html>
   <head>
	  <meta name="layout" content="main">   
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <newrelic:browserTimingHeader/>
		<title>Merit Badges - Dashboard</title>      
   </head>
  <body>
  	<div class="row-fluid dashRow">
  		<div class="span6 dashCell">
		<h2><g:message code="label.recent.achievements" default="Recent Achievements"/></h2>
		<% unlockedAchievements.each { achievement -> %>
		<div class="meritBadge">
				<g:img dir="images" file="award-ribbon-badge-shaded.png"/>
				<div class="meritBadgeTitle"><g:message code="${achievement.messageKey}" 
					args="${[achievement.messageArguments]}"/>${achievement.name}</div>
				<div class="meritBadgeDate"><g:formatDate date="${achievement.unlockedDate}" type="date"
					 style="SHORT" timezone="${tz}"/></div>
		</div>	
		<% } %>
		<a href="${createLink(uri:'/userDashboard/userAchievementList')}">(see all achievements)</a>
		</div> <!-- end span6-->
 	
  		<div class="span6 dashCell">  	
			<h2><g:message code="label.progress.in.depth" default="Progress in Depth"/></h2>  		
			<div id="outerDiv" style="width:400px;height:250px">
				<div class="chartDiv" id="progressChart">
				</div>
			</div>
		</div> <!-- end span6-->    
  	</div> <!-- end row-fluid-->    
  	<div class="row-fluid dashRow"> 		
  		<div class="span6 dashCell">  	
			<h2><g:message code="label.progress.in.depth" default="Top Five Strengths"/></h2>  		
			<div id="outerDiv" style="width:400px;height:250px">
				<div class="chartDiv" id="strengthsChart">
				</div>
			</div>

		</div> <!-- end span6-->   			
  		<%--<div class="span6 dashCell">  	
			<h2><g:message code="label.progress.in.depth" default="Progress in Depth"/></h2>  		
			<div id="outerDiv" style="width:400px;height:250px">
				[another chart here]
			</div>			
		</div> <!-- end span6-->--%>    		
  	</div> <!-- end row-fluid-->		
<script>
	jQuery.noConflict();
	jQuery(function() {	
		doPlotProgress();
		doPlotStrengths();
	});			
	
	function doPlotStrengths() {
		var options = {};
				options.data = [];
				options.labels = [];
				options.containerDivId = 'outerDiv';
				options.chartDivId = 'strengthsChart';
				options.chartType = 'bar';
			
					{jQuery.getJSON("${createLink(uri:'/userDashboard/userStrengthsChartData')}", function(data) {
						jQuery.each(data,function(i, item){
								this.data.push(item.value);
								this.labels.push(item.label);
						}.bind(this));
				
						createChart(this);
					}.bind(options)); }
			
// 				options.data = [2, 5, 6, 10, 15, 16];
// 		 		options.labels = ['JavaScript', 'Release Management', 'Database', 'Java', 'Swimming', 'Cooking'];
// 				createChart(options);	
	}
		
	function doPlotProgress() {
		var options = {};
		options.data = [];
		options.labels = [];
		options.containerDivId = 'outerDiv';
		options.chartDivId = 'progressChart';
		options.chartType = 'line';
	
			{jQuery.getJSON("${createLink(uri:'/userDashboard/userProgressDepthChartData')}", function(data) {
				jQuery.each(data,function(i, item){
						this.data.push(item.value);
						this.labels.push(item.label);
				}.bind(this));
		
				createChart(this);
			}.bind(options)); }
	
// 		options.data = [2, 5, 6, 10, 15, 16, 16, 25, 25];
// 		options.labels = ['2010-06', '2010-07', '2010-08', '2010-09', '2010-10', '2010-11', '2010-12', '2011-01', '2011-02'];
		//createChart(options);
	}		
		
	function createChart(options) {
		var
			containerDiv = jQuery('#' + options.containerDivId),
			chartDiv = jQuery('#' + options.chartDivId);
	
		options.width = containerDiv.width() - 15;
		options.height = containerDiv.height() - 35;
		chartDiv.html('');
	
		chartDiv.width(options.width);
		chartDiv.height(options.height);
	
		if (options.chartType == 'line') {
			createLineChart(options);
		}
		if (options.chartType == 'bar') {
			createBarChart(options);
		}
	}			
		
	function createBarChart(options) {
		new Ico.BarGraph( options.chartDivId, options.data,
				{   labels: {
					  values: options.labels
					  ,angle: -30
					}
				  , colors: ['#339933' ]
				  , font: { 'font-family': 'Arial', 'font-size': 15, 'fill' : 'black', stroke: 'none' }
				  //, color: '#ccccff'
				  , background: { attributes: { stroke: 'none', gradient: '0-#ccf:20-#aaa', corners: 5 } }
				  //, meanline: true
				  , grid : true
				  , mouse_pointer: true
				  , status_bar : true
				  , x_padding_left: 50
				  , width: options.width
				  , height: options.height
				}
			  ).clear().draw();
	}

	function createLineChart(options) {
		new Ico.LineGraph( options.chartDivId, options.data,
				{   labels: {
					  values: options.labels,
					  //angle: -30
					}
				  , colors: ['#339933' ]
				  , font: { 'font-family': 'Arial', 'font-size': 15, 'fill' : 'black', stroke: 'none' }
				  //, color: '#ccccff'
				  , background: { attributes: { stroke: 'none', gradient: '0-#ccf:20-#aaa', corners: 5 } }
				  , curve_amount: 1
				  //, grid : true
				  , mouse_pointer: true
				  , status_bar : true
				  , x_padding_left: 25
				  , x_padding_right: 25
				  , width: options.width
				  , height: options.height
				  , dot_radius:0
				}
			  ).clear().draw();
	}	
</script>
  <newrelic:browserTimingFooter/>
  </body>

</html>