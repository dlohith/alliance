<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <link href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery-ui.min.js" type="text/javascript"></script>
    <link href="${pageContext.servletContext.contextPath}/resources/arcanalayout/css/keyboard.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery.keyboard.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/arcanalayout/js/jquery.keyboard.extension-typing.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#keyboard').keyboard({
                preventPaste: true,
                autoAccept: true
            })
            .addTyping();
        });
    </script>
    
    <form id="form1" >
    <div>
        Textbox with virtual key pad
        <input ID="keyboard" MaxLength="4"></input>
    </div>
    </form>