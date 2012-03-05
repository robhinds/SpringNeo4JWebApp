/**
 * Javascript object containing all common functions to handle
 * processing People - primarily for the popup window
 */

//0 = disabled; 1 = enabled;
var popupStatus = 0;
var dataLoaded=false;


function loadPopup(loadDataFn, bgDiv, popupDiv){
	if(popupStatus==0){
		if (!dataLoaded && loadDataFn){
			loadDataFn();
		}
		$(bgDiv).css({"opacity": "0.7"});
		$(bgDiv).fadeIn("slow");
		$(popupDiv).fadeIn("slow");
		popupStatus = 1;
	}
}

function disablePopup(bgDiv, popupDiv){
	if(popupStatus==1){
		$(bgDiv).fadeOut("slow");
		$(popupDiv).fadeOut("slow");
		popupStatus = 0;
	}
}

function centerPopup(bgDiv, popupDiv){
	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $(popupDiv).height();
	var popupWidth = $(popupDiv).width();

	$(popupDiv).css({
		"position": "absolute",
		"top": windowHeight/2-popupHeight/2,
		"left": windowWidth/2-popupWidth/2
	});
	
	$(bgDiv).css({"height": windowHeight});

}
