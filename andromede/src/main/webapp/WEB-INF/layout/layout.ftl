<#macro layout>
<html>
	<head>
		<link rel="stylesheet" href="<@spring.url '/resources/css/bootstrap.css'/>"/>
		<link rel="stylesheet" href="<@spring.url '/resources/css/bootstrap.min.css'/>"/>
		<script src="<@spring.url '/resources/js/jquery.js'/>"></script>
		<script src="<@spring.url '/resources/js/bootstrap.min.js'/>"></script>
	</head>

	<body>

		<#include "header.ftl"/>

		<#include "menu.ftl"/>

		<#nested/>
		
		<#include "footer.ftl"/>
	
	</body>
</html>
</#macro>