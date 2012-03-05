/**
 * Javascript object containing all common functions to handle
 * processing Status objects
 */

var resumeId;
var pageId;

function createSection(event){
	var title = $("#sectionTitle").val();
	var details = $("#sectionDetails").val();
	
	
	$.getJSON('profile/createSection.ajax', {resume : resumeId, page : pageId, sectionTitle : title, sectionDetails : details}, function(data) {
		//iterate through returned updates and update UI
		var section = $.parseJSON(data.section);
				$('#'+pageId).append(
						"<b>" + section.title + "</b><br/><hr/>"
						+ section.details
						+ "<br/><br/>"
				);
				
		$('#' + pageId).fadeIn();
		
		disablePopup("#createSectionBackground", "#createSectionPopup");
		$("#sectionTitle").val($("#sectionTitle").attr('defaultVal'));
	    $("#sectionTitle").css({color:'grey'});
	    $("#sectionDetails").val($("#sectionDetails").attr('defaultVal'));
	    $("#sectionDetails").css({color:'grey'});
	});
}