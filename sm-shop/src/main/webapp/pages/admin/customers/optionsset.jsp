<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false"%>

<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>


<script type="text/javascript">

$(document).ready(function() {

	$('#sortOrder').numeric();

});
	
</script>



<div class="tabbable">

	<jsp:include page="/common/adminTabs.jsp" />

	<div class="tab-content">

		<div class="tab-pane active" id="customer-section">



			<div class="sm-ui-component">


				<h3>
					<s:message code="menu.customer-options-set"
						text="Manage customer options set" />
				</h3>

				<c:url var="createOptionSet"
					value="/admin/customers/optionsset/save.html" />
				<form:form method="POST" modelAttribute="optionSet"
					action="${createOptionSet}" cssClass="form-inline">


					<div class="control-group">
						<label>
							<s:message code="label.customer.option" text="Option" />
						</label>
						<div class="controls">
							<form:select path="customerOption.id">
								<form:options items="${options}" itemValue="id"
									itemLabel="descriptionsSettoList[0].name" />
							</form:select>
						</div>
					</div>


					<div class="control-group">
						<label>
							<s:message code="label.customer.option.value" text="Option value" />
						</label>
						<div class="controls">
							<form:select path="customerOptionValue.id">
								<form:options items="${optionsValues}" itemValue="id"
									itemLabel="descriptionsSettoList[0].name" />
							</form:select>
							<span class="help-inline"> <c:if
									test="${errorMessageAssociation!=null}">
									<span id="identifiererrors" class="error"><c:out
											value="${errorMessageAssociation}" />
									</span>
								</c:if> </span>
						</div>
					</div>


					<div class="control-group">
						<label class="required">
							<s:message code="label.entity.order" text="Order" />
						</label>
						<div class="controls">
							<form:input id="sortOrder" path="sortOrder" />
							<span class="help-inline"><form:errors path="sortOrder"
									cssClass="error" />
							</span>

						</div>
					</div>


					<button type="submit" class="btn btn-success">
						<s:message code="label.generic.add" text="Add" />
					</button>

				</form:form>



				<!-- Listing grid include -->
				<c:set value="/admin/customers/optionsset/paging.html"
					var="pagingUrl" scope="request" />
				<c:set value="/admin/customers/optionsset/remove.html"
					var="removeUrl" scope="request" />
				<c:set value="/admin/customers/optionsset/edit.html" var="editUrl"
					scope="request" />
				<c:set value="/admin/customers/optionsset/list.html"
					var="afterRemoveUrl" scope="request" />
				<c:set var="entityId" value="id" scope="request" />
				<c:set var="groupByEntity" value="optionCode" scope="request" />
				<c:set var="componentTitleKey" value="menu.customer-options-list"
					scope="request" />
				<c:set var="gridHeader"
					value="/pages/admin/customers/optionsset-gridHeader.jsp"
					scope="request" />
				<c:set var="canRemoveEntry" value="true" scope="request" />

				<jsp:include page="/pages/admin/components/list.jsp"></jsp:include>
				<!-- End listing grid include -->




			</div>











		</div>


	</div>

</div>
