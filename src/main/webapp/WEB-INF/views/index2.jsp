<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<tiles:insertAttribute name="header" />
</head>
<body>
<script type="text/JavaScript">
	//style the left panel accordion boxes
	$(function() {
		$("#user_menu_action").addClass("ui-accordion ui-accordion-icons ui-widget ui-helper-reset")
		.find("h3")
	    .addClass("ui-accordion-header ui-state-active ui-helper-reset ui-state-default ui-corner-top ui-corner-bottom")
	    .next()
	    .addClass("ui-accordion-content ui-accordion-content-active ui-helper-reset ui-widget-content ui-corner-bottom");
	});
</script>



<div id="container">
	<div id="page_content">
		<tiles:insertAttribute name="mainContent" />
	</div>	
</div>
</body>
</html>