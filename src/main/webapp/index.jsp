<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html;
charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<link rel="stylesheet" href="styles.css" />
<title>Reversi</title>
</head>
<body>
	<form action="Game" method="POST">
		<div id="content">
			<h1>Reversi</h1>
			<div>
				<span id="dark">Dark: ${game.darkCount}</span><span id="light">Light:
					${game.lightCount}</span>
			</div>
			<span>Current Player: ${game.currentPlayer}</span> <br />
			<div class="board" id="board">
				<div class="label"></div>
				<div class="label">A</div>
				<div class="label">B</div>
				<div class="label">C</div>
				<div class="label">D</div>
				<div class="label">E</div>
				<div class="label">F</div>
				<div class="label">G</div>
				<div class="label">H</div>
				<div class="label"></div>

				<c:forEach items="${grids}" var="mark" varStatus="count">
					<c:choose>
						<c:when test="${count.index == 0}">
							<div class="label">1</div>
						</c:when>
						<c:when test="${count.index % 8 == 0}">
							<div class="label">
								<fmt:formatNumber type="number" pattern="#"
									value="${count.index/8}" />
							</div>
							<div class="label">
								<fmt:formatNumber type="number" pattern="#"
									value="${(count.index/8)+1}" />
							</div>
						</c:when>
					</c:choose>

					<c:choose>
						<c:when test="${mark == null && valid[count.index]}">
							<button class="cell button" type="submit" name="action"
								value="loc ${count.index}"></button>
						</c:when>
						<c:otherwise>
							<div class="cell ${mark}"></div>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<div class="label">8</div>
				<div class="label"></div>
				<div class="label">A</div>
				<div class="label">B</div>
				<div class="label">C</div>
				<div class="label">D</div>
				<div class="label">E</div>
				<div class="label">F</div>
				<div class="label">G</div>
				<div class="label">H</div>
				<div class="label"></div>

				<c:if test="${over}">
					<div class="winning-message">
						${game.winner} !
						<button type="submit" name="action" value="restart">Quit</button>
					</div>
				</c:if>

			</div>
			<br />
			<button type="submit" name="action" value="restart">Quit</button>
		</div>
	</form>
</body>
</html>
