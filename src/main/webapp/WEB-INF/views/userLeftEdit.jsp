<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<h3>
	<a href="#">Control Panel</a>
</h3>
<div>
<br/>
	<div class="small_text">Not the resume you were looking for?</div>
	<br />
	<div id="user_status">
		<form action="<c:url value='/profile/view'/>" method="post">
			<input type='text' id="userName" name="userName"
				defaultVal="Enter Persons Name" class="clearText" /> <input
				type="submit" id="searchResume" value="Search" />
		</form>
	</div>

	<br />
	<br />
</div>