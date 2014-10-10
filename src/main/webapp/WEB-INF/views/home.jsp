<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<form action="testing" method="post">
	<script type="text/javascript"
	     src="http://www.google.com/recaptcha/api/challenge?k=6Ld7xPsSAAAAAP5roP7kJMeOXc5pupY-CZU7nx5T">
	  </script>
	  <noscript>
	     <iframe src="http://www.google.com/recaptcha/api/noscript?k=6Ld7xPsSAAAAAP5roP7kJMeOXc5pupY-CZU7nx5T"
	         height="300" width="500" frameborder="0"></iframe><br>
	     <textarea name="recaptcha_challenge_field" rows="3" cols="40">
	     </textarea>
	     <input type="hidden" name="recaptcha_response_field"
	         value="manual_challenge">
	  </noscript>
</form>

<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

