<%@ taglib prefix='j' uri='http://www.thirdmindmedia.co.uk/butterfly/jsonarray' %>
<script type="text/JavaScript">
	//style the left panel accordion boxes
	$(function() {
		$("#resume_pages").addClass("ui-accordion ui-accordion-icons ui-widget ui-helper-reset")
		.find("h3")
	    .addClass("ui-accordion-header ui-state-active ui-helper-reset ui-state-default ui-corner-top ui-corner-bottom")
	    .next()
	    .addClass("ui-accordion-content ui-accordion-content-active ui-helper-reset ui-widget-content ui-corner-bottom");
	});
	
	$(document).ready(function(){

		$("#createSection").click(createSection);
		
		$(".createNewSectionbutton").click(function(event){
			resumeId = $(this).attr("resume");
			pageId = $(this).attr("pageId");
			centerPopup("#createSectionBackground", "#createSectionPopup");
			loadPopup(null, "#createSectionBackground", "#createSectionPopup");
		});
		
		$("#createSectionPopupClose").click(function(){
			disablePopup("#createSectionBackground", "#createSectionPopup");
		});
		$("#createSectionBackground").click(function(){
			disablePopup("#createSectionBackground", "#createSectionPopup");
		});
		$(document).keypress(function(e){
			if(e.keyCode==27){
				disablePopup("#createSectionBackground", "#createSectionPopup");
			}
		});	
	});
</script>

<div id="createSectionPopup" class="popupWindow">  
    <a id="createSectionPopupClose" class="popupWindowClose">x</a>  
    <h1>Create New Section</h1>  
    <div id="createSectionContent">
    	<div class="content_header_text"></div>
   		<input id="sectionTitle" type="text" defaultVal="Title.." class="autoWidth clearText">
		<textarea id="sectionDetails" rows="15" cols="2" defaultVal="Details.." class="clearText"></textarea> 
		<input type="submit" id="createSection" value="Create Section" />
    </div>  
</div>  
<div id="createSectionBackground" class="popupBackground"></div> 

<h1><j:jsonGetString key="userName" json="${resume}" />'s Resume</h1>
<hr/>
<j:jsonGetArray key="pages" json="${resume}" var="pages" />
<div id="resume_pages">
	<j:forEachJson items="${pages}" var="page">
		<h3><a href="#"><j:jsonGetString key="pageName" json="${page}" /></a></h3>
		<div id="<j:jsonGetString key="pageName" json="${page}" />">
			<j:jsonGetArray key="sections" json="${page}" var="sections" />
			<j:forEachJson items="${sections}" var="section">
				<b><j:jsonGetString key="sectionTitle" json="${section}" /></b><br/><hr/>
				<j:jsonGetString key="sectionContent" json="${section}" />
				<br/><br/>
			</j:forEachJson>
		</div>
	
		<button id="<j:jsonGetString key="pageName" json="${page}" />Btn" 
			pageId="<j:jsonGetString key="pageName" json="${page}" />" 
			resume="<j:jsonGetString key="userName" json="${resume}" />" 
			class="createNewSectionbutton">Create New Section
		</button><br/>
	</j:forEachJson>
</div>