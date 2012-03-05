<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Resume Builder</title>

<!-- jQuery Library -->
<link rel="Stylesheet" type="text/css" href="<c:url value='/resources/styles/blue/jquery-ui-1.8.14.custom.css' />" />	
<script type="text/javascript" src="<c:url value='/resources/libs/jquery/jquery-1.4.4.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/libs/arbor/arbor.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/libs/arbor/arbor-tween.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/libs/jquery/jquery-ui-1.8.14.custom.min.js' />"></script>


<!--  Required Javascript files  -->
<script type="text/javascript" src="<c:url value='/resources/javascript/resumeFunctions.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/javascript/popupFunctions.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/javascript/fieldUtil.js' />"></script>

<!-- Core StyleSheet -->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/styles/main_style.css' />"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/styles/atlas-arbor.css' />"/>

<script language="javascript" type="text/javascript">
function clearText(field){

    if (field.defaultValue == field.value) field.value = '';
    else if (field.value == '') field.value = field.defaultValue;

}
</script>