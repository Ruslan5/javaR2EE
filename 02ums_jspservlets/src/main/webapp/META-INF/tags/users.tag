<!-- TagFile 01: Create a Tag File -->
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute
	name="bt"
	type="com.mir.servlets.entity.User"
	required="true"
	rtexprvalue="true"
%>

<h2
	style="font-size: medium;
	font-family: Verdana;
	vertical-align: middle;
	text-align: left;
	color: #3300ff">
	${bt.user}
</h2>
<p>[${bt.login}]	</p>