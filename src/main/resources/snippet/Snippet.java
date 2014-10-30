package snippet;

public class Snippet {
	<link href="jquery-ui.css" rel="stylesheet" type="text/css" />
	    <script src="jquery.min.js" type="text/javascript"></script>
	    <script src="jquery-ui.min.js" type="text/javascript"></script>
	    <link href="keyboard.css" rel="stylesheet" type="text/css" />
	    <script src="jquery.keyboard.js" type="text/javascript"></script>
	    <script src="jquery.keyboard.extension-typing.js" type="text/javascript"></script>
	    <script type="text/javascript">
	        $(document).ready(function () {
	            $('#keyboard').keyboard({
	                preventPaste: true,
	                autoAccept: true
	            })
	            .addTyping();
	        });
	    </script>
}

