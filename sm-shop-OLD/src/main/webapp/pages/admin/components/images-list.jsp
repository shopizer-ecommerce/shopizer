<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				

			<script>
			      			     
							


								
								isc.RestDataSource.create({ 
									ID:"dataSource", 
									dataFormat:"json",  
									operationBindings:[ 
										{operationType:"fetch", dataProtocol:"postParams",dataURL: "<c:url value="${pagingUrl}" />"},
										{operationType:"remove", dataProtocol:"postParams",dataURL: "<c:url value="${removeUrl}" />"},
										{operationType:"update", dataProtocol:"postParams", dataURL: "<c:url value="${defaultImageUrl}" />"}
									],
									transformResponse : function (dsResponse, dsRequest, jsonData) {
										var status = isc.XMLTools.selectObjects(jsonData, "/response/status");
										if (status != 0) {
											//if(status==9999) {//operation completed
												//reload 
											//	window.location='<c:url value="${refreshUrl}" />';
											//}
											//var msg = isc.XMLTools.selectObjects(jsonData, "/response/statusMessage");
											//alert("! " + msg);
										}
									}
								}); 
								

							  
								isc.TileGrid.create({
								    ID:"imageList",
								    tileWidth:150,
								    tileHeight:200,
								    dataSource:"dataSource",
								    autoFetchData:true,
								    showAllRecords:true,
								    animateTileChange:true,
								    recordClick:"selectImage(record.name)",
								    fields: [
										{name:"id", cellStyle: "name"},
								        {name:"picture", type:"image", imageWidth:150},
								        {name:"name", cellStyle: "name"}
								    ],

								    getTile : function (record) {
								        // override getTile() and add a "Remove" button
								        var canvas = this.Super("getTile", arguments);
								        canvas.addChild(this.getRemoveButton(this.getRecord(record)));
									<c:if test="${canSetDefaultEntry==true}"> 
								        canvas.addChild(this.getSetDefaultButton(this.getRecord(record)));
									</c:if>
								        return canvas;
								    },
								    
								    getRemoveButton : function (record) {
								        var removeButton = isc.ImgButton.create({
								            src: "<c:url value='/resources/img/admin/remove.png'/>",
								            showHover: true,
								            prompt: "<s:message code='label.generic.remove' text='Remove' />",
								            size: 15,
								            showFocused: false,
								            showRollOver: false,
								            snapTo: "TR",
								            showDown: false,
								            margin: 2,
								            tileGrid: this,
								            record: record,
								            click : function () {
								            	if (confirm('<s:message code="label.entity.remove.confirm" text="Do you really want to remove this record ?" />')) {

								        			$.ajax({
								        				  type: 'POST',
								        				  url: '<c:url value="${removeUrl}"/>',
								        				  data: 'id=' + record.id + '&name=' + record.name,
								        				  dataType: 'json',
								        				  success: function(response){
								        			
								        						var status = isc.XMLTools.selectObjects(response, "/response/status");
								        						if(status==0 || status ==9999) {
								        							
								        							//reload
								        							window.location='<c:url value="${refreshUrl}" />';
								        							
								        						} else {
								        							
								        							
								        						}
								        			
								        				  
								        				  },
								        				  error: function(xhr, textStatus, errorThrown) {
								        				  	alert('error ' + errorThrown);
								        				  }
								        				  
								        				});
								            		
								            		
								            		
												} 
								            }
								        });

								        return removeButton;
								    }
								   <c:if test="${canSetDefaultEntry==true}"> 
								   ,
								    getSetDefaultButton : function (record) {
								        var setDefaultButton = isc.ImgButton.create({
								            src: '<c:url value="' + record.defaultImageCheckmark + '"/>',
								            showHover: true,
								            prompt: "<s:message code='label.generic.setAsDefault' text='Set as default' />",
								            size: 15,
								            showFocused: false,
								            showRollOver: false,
								            snapTo: "TL",
								            showDown: false,
								            margin: 2,
								            tileGrid: this,
								            record: record,
								            click : function () {
								            	if (confirm('<s:message code="label.entity.setAsDefault.confirm" text="Do you really want to set this entity as default?" />')) {
								        			$.ajax({
								        				  type: 'POST',
								        				  url: '<c:url value="${defaultImageUrl}"/>',
								        				  data: 'id=' + record.id + '&name=' + record.name,
								        				  dataType: 'json',
								        				  success: function(response) {
								        						var status = isc.XMLTools.selectObjects(response, "/response/status");
								        						if (status==0 || status ==9999) {
								        							//reload
								        							window.location='<c:url value="${refreshUrl}" />';
								        						} else {
								        						}
								        				  },
								        				  error: function(xhr, textStatus, errorThrown) {
								        				  	alert('error ' + errorThrown);
								        				  }
													});
												}
								            }
								        });

								        return setDefaultButton;
								    }
								</c:if>
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
            ID:"mainLayout",
            visibilityMode:"multiple",
            animateSections:true,
            sections:[
                {title:"<s:message code="${componentTitleKey}" text="{componentTitleKey} UNDEFINED"/>", autoShow:true, items:[imageList]}
            ]
        })
    ]
});

isc.Page.setEvent("load", "pageLayout.draw()");
			      			     
			 </script>
	      			     