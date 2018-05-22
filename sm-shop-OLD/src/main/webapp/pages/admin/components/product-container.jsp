<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				

<script>
								//isc.showConsole();
								//Always fetch products for a given category
								isc.RestDataSource.create({ 
									ID:"products", 
									dataFormat:"json", 
									dataURL: "<c:url value="/admin/products/paging.html" />",
									operationBindings:[ 
										{operationType:"fetch", dataProtocol:"postParams"} 
									]
								});
								
								isc.RestDataSource.create({ 
									ID:"container", 
									dataFormat:"json", 
									operationBindings:[ 
										{operationType:"remove", dataProtocol:"postParams",dataURL: "<c:url value="${containerRemoveUrl}" />&removeEntity=<c:out value="${removeEntity}" />"},
										{operationType:"fetch", dataProtocol:"postParams",dataURL: "<c:url value="${containerFetchUrl}" />"},
										{operationType:"add", dataProtocol:"postParams",dataURL: "<c:url value="${containerAddUrl}" />"},
										{operationType:"update", dataProtocol:"postParams",dataURL: "<c:url value="${containerUpdateUrl}" />"}
									],
									transformResponse : function (dsResponse, dsRequest, jsonData) {
										var status = isc.XMLTools.selectObjects(jsonData, "/response/status");
										if (status != 0 && status !=9999) {
											var msg = isc.XMLTools.selectObjects(jsonData, "/response/statusMessage");
												alert("! " + msg);
												//window.location='<c:url value="${reloadUrl}" />';
										}
										if(status == 9999) {
											window.location='<c:url value="${reloadUrl}" />';
										}
									}
								});  
								

								//iterate from category objects to display data
      							isc.TreeGrid.create({
    								ID:"categoryTree",
    								showResizeBar: false,
    								data: isc.Tree.create({
        								modelType: "parent",
        								nameProperty: "Name",
        								idField: "categoryId",
        								parentIdField: "parentId",
        								data: [
										{categoryId:"-1", parentId:"0", Name:"<s:message code="label.category.root" text="Root" />", isFolder: true},
										<c:forEach items="${categories}" var="category" varStatus="status">
            								{categoryId:'<c:out value="${category.id}" />', parentId:'<c:choose><c:when test="${category.parent!=null}"><c:out value="${category.parent.id}" /></c:when><c:otherwise>-1</c:otherwise></c:choose>', Name:'<c:out value="${category.descriptions[0].name}" />', isFolder: true}
            								<c:if test="${status.count<fn:length(categories)}">,</c:if>
            							</c:forEach>
        								]
    								}),

    								nodeClick:"itemList.fetchData({categoryId:node.categoryId})",
    								showHeader:false,
    								leaveScrollbarGap:false,
    								animateFolders:true,
    								canAcceptDroppedRecords:false,
    								canReparentNodes:false,
    								selectionType:"single",
    								animateRowsMaxTime:750
							  });
							  
							  
							  isc.ListGrid.create({
    								ID: "itemList",
    								dataSource: "products",
    								showRecordComponents: true,    
    								showRecordComponentsByCell: true,
    								
    								autoFetchData: false,
    								showFilterEditor: true,
    								filterOnKeypress: true,
									dataFetchMode:"paged",
									canDragRecordsOut: true,
    								dragDataAction: "copy",
    								alternateRecordStyles: true,
    						        fields:[
										<jsp:include page="${gridHeader}"></jsp:include>
    							    ],
    								selectionType: "single",
    								createRecordComponent : function (record, colNum) {  
        								var fieldName = this.getFieldName(colNum);
        								if (fieldName == "buttonField") {  
	           								var button = isc.IButton.create({
	                							height: 18,
	                							width: 65,
	               					 			title: "<s:message code="label.entity.details" text="Details"/>",
	                							click : function () {
	                    							var url = '<c:url value="/admin/products/editProduct.html" />?id=' + record["productId"];
	                    							<c:if test="${appendQueryStringToEdit!=null && appendQueryStringToEdit!=''}">
	                    									url = url + '&<c:out value="${appendQueryStringToEdit}" />' ;
	                    							</c:if>
	                    							window.location=url;
	                							}
	            							});
	            						}
	            						return button;  
            						}



								});
								
								isc.ListGrid.create({
    									ID: "containerList",
    									dataSource: "container",
    									canAcceptDroppedRecords: true,
    									canRemoveRecords: true,
    									canReorderRecords: false,
    									alternateRecordStyles: true,    
    									autoFetchData: true,
    									preventDuplicates: true,
    									leaveScrollbarGap: false,
    									fields: [
        									<jsp:include page="${gridHeaderContainer}"></jsp:include>
    									],								   
    									removeData: function () {
											if (confirm('<s:message code="label.entity.remove.confirm" text="Do you really want to remove this record ?" />')) {
												return this.Super("removeData", arguments);
											}
								   		}
								   		//recordDrop: function (dropRecords, targetRecord, index, sourceWidget) {
											//alert(dropRecords.length);
											//alert(dropRecords.length);
											//var rolesNotAddedMessage = '';
											//for (i=0; i < dropRecords.length; i++) {
											//if (selectedRoleList.data.find("roleId", dropRecords.get(i).roleId)) {
											//	rolesNotAddedMessage = rolesNotAddedMessage + "\'" + dropRecords.get(i).roleName + "\' is already in the list." + "<br>";
											//}
											//else {
											//	this.Super("recordDrop", newDropRecords, targetRecord, index, sourceWidget);
											//}
											//if (rolesNotAddedMessage != ''){ 
												//alert(dropRecords[i].productId);
												
												//isc.say(dropRecords.get(i).productId);
											//}
											//} 
											
											//this.Super("recordDrop", dropRecords, targetRecord, index, sourceWidget);

									//}
								});


// Define application layout
// ---------------------------------------------------------------------

isc.HLayout.create({
    ID:"pageLayout",
    width: "700",
    height: "600",
    position:"relative",
    members:[
        isc.SectionStack.create({
            ID:"leftSideLayout",
            width:200,
            showResizeBar:true,
            visibilityMode:"multiple",
            animateSections:true,
            sections:[
                {title:"<s:message code="label.categories.title" text="Categories" />", autoShow:true, items:[categoryTree]}
            ]
        }),
        isc.SectionStack.create({
            ID:"middleSideLayout",
            width:280,
            visibilityMode:"multiple",
            animateSections:true,
            sections:[
                {title:"<s:message code="menu.catalogue-products" text="Products" />", autoShow:true, items:[itemList]}
            ]
        }),
        isc.Img.create({src:"", width:10, height:32, layoutAlign:"center",
        	click:"itemList.transferSelectedData(containerList)"
    	}),
    	isc.SectionStack.create({
            ID:"rightSideLayout",
            width:205,
            showResizeBar:false,
            visibilityMode:"multiple",
            animateSections:true,
            sections:[
                {title:"<s:message code="${componentTitleKey}" text="{componentTitleKey} UNDEFINED"/>", autoShow:true, items:[containerList]}
            ]
        }),
    ]
});



isc.Page.setEvent("load", "pageLayout.draw()");

      			     

								
								
</script>
	      			     