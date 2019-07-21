<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>				
				



<div class="tabbable">

						<jsp:include page="/common/adminTabs.jsp" />
  					
  					 	<div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">			
								<h3><s:message code="label.categories.hierarchy.title" text="Category hierarchy" /></h3>	
								<br/>
								<div class="well">
									<s:message code="label.category.hierarchy.text" text="Drag categories to re-organize the hierarchy" />
								</div>
								<br/>
      							
			      			     <script>
			      			     
			      			     

///isc.showConsole();
      			     
      			     
// User Interface
// ---------------------------------------------------------------------


								
								//iterate from category objects to display data
      							isc.TreeGrid.create({
    								ID:"categoryTree",
    								border:1,
    								showResizeBar: false,

    								data: isc.Tree.create({
        								modelType: "parent",
        								nameProperty: "Name",
        								idField: "categoryId",
        								parentIdField: "parentId",
        								data: [
										{categoryId:"-1", parentId:"0", Name:"<s:message code="label.category.root" text="Root" />", isFolder: true},
										<c:forEach items="${categories}" var="category" varStatus="status">
            								{categoryId:'<c:out value="${category.category.id}" />', parentId:'<c:choose><c:when test="${category.category.parent!=null}"><c:out value="${category.category.parent.id}" /></c:when><c:otherwise>-1</c:otherwise></c:choose>', Name:'<c:out value="${category.descriptions[0].name}" />', isFolder: true}
            								<c:if test="${status.count<fn:length(categories)}">,</c:if>
            							</c:forEach>
        								]
    								}),


    								//nodeClick:"itemList.fetchData({categoryId:node.categoryId})",
    								showHeader:false,
    								leaveScrollbarGap:false,
    								animateFolders:true,
    								canReorderRecords: true,
									canAcceptDroppedRecords: true,
    								canReparentNodes:true,
    								selectionType:"single",
    								animateRowsMaxTime:750,
									folderDrop: function (dragRecords, dropFolder, index, sourceWidget) {
										var record=categoryTree.getSelectedRecord();
										var newUnit=dropFolder.SysId;
										var newRecord=record;
										newRecord.ReportsTo=newUnit;
										
										//ajax call
										$.ajax({
  											type: 'POST',
  											dataType: "json",
  											url: "<c:url value="/admin/categories/moveCategory.html" />",
  											data: "parentId="+ dropFolder.categoryId + "&childId=" + newRecord.categoryId,
  											success: function(response) { 
  												var msg = isc.XMLTools.selectObjects(response, "/response/statusMessage");
  												var status = isc.XMLTools.selectObjects(response, "/response/status");

  												
  												if(status==0 || status ==9999) {
  													categoryTree.removeData(record);
  	  												categoryTree.data.addList([newRecord],dropFolder, index);
  												} else {
  													if(msg!=null && msg !='') {
  														alert("! " + msg);
  													}
  												}
  												
  											},
  											error: function(jqXHR,textStatus,errorThrown) { 
  												alert(jqXHR + "-" + textStatus + "-" + errorThrown);

  												//alert(data.statusMessage);
  											}
  											
										});

										
										
										
										
										//alert(index);
										//alert(dropFolder.categoryId);
										//alert(newRecord.categoryId);
									}



							  });

							  






// Define application layout
// ---------------------------------------------------------------------

isc.HLayout.create({
    ID:"pageLayout",
    width: "680",
    height: "600",
    position:"relative",
    members:[
             
             isc.SectionStack.create({
                 ID:"mainLayout",
                 visibilityMode:"multiple",
                 animateSections:true,
                 sections:[
                     {title:"<s:message code="label.categories.hierarchy.title" text="Category hierarchy"/>", autoShow:true, items:[categoryTree]}
                 ]
             })
             
             
   ]
});

isc.Page.setEvent("load", "pageLayout.draw()");
			      			     
			      			     </script>
			      			     
			      			     
			      			     
      					</div>
      					

      			     
 


      			     
      			     
    


   					</div>


  					</div>

				</div>	      			     
