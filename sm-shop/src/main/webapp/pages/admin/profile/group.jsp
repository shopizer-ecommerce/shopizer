<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>




<div class="tabbable">

	<jsp:include page="/common/adminTabs.jsp" />

	<div class="tab-content">

		<div class="tab-pane active" id="catalogue-section">



			<div class="sm-ui-component">


				<h3>
					<c:choose>
						<c:when test="${group.group.id!=null && group.group.id>0}">
							<s:message code="label.group.editgroup" text="Edit group" />
							<c:out value="${category.code}" />
						</c:when>
						<c:otherwise>
							<s:message code="label.group.creategroup" text="Create group" />
						</c:otherwise>
					</c:choose>

				</h3>
				<br />

				<c:url var="groupSave" value="/admin/group/save.html" />


				<form:form method="POST" modelAttribute="group" action="${groupSave}">


					<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success"
						style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
						<s:message code="message.success" text="Request successfull" />
					</div>


					<form:hidden path="group.id" />

					<div class="control-group">
						<label class="required"><s:message code="label.group.name"
								text="Group name" /></label>
						<div class="controls">
							<form:input cssClass="input-large" path="group.groupName" />
							<span class="help-inline"><form:errors path="group.groupName"
									cssClass="error" /></span>
						</div>
					</div>

					<div class="control-group">
						<label><s:message code="label.entity.type" text="Type" /></label>
						<div class="controls">
							<form:select path="group.groupType">
								<form:options items="${group.types}" />
							</form:select>
							<span class="help-inline"><form:errors path="group.groupType"
									cssClass="error" /></span>
						</div>
					</div>

					<c:if test="${group.group.groupType != 'ADMIN'}">
						<div class="form-actions">

							<div class="pull-right">

								<button type="submit" class="btn btn-success">
									<s:message code="button.label.submit" text="Submit" />
								</button>

							</div>

						</div>
					</c:if>
					
     
				</form:form>

			</div>

		</div>


	</div>

</div>
