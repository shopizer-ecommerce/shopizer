/*
 * Isomorphic SmartClient
 * Version v8.2p_2012-06-03 (2012-06-03)
 * Copyright(c) 1998 and beyond Isomorphic Software, Inc. All rights reserved.
 * "SmartClient" is a trademark of Isomorphic Software, Inc.
 *
 * licensing@smartclient.com
 *
 * http://smartclient.com/license
 */

if(window.isc&&window.isc.module_Core&&!window.isc.module_SQLBrowser){isc.module_SQLBrowser=1;isc._moduleStart=isc._SQLBrowser_start=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc._moduleEnd&&(!isc.Log||(isc.Log && isc.Log.logIsDebugEnabled('loadTime')))){isc._pTM={ message:'SQLBrowser load/parse time: ' + (isc._moduleStart-isc._moduleEnd) + 'ms', category:'loadTime'};
if(isc.Log && isc.Log.logDebug)isc.Log.logDebug(isc._pTM.message,'loadTime')
else if(isc._preLog)isc._preLog[isc._preLog.length]=isc._pTM
else isc._preLog=[isc._pTM]}isc.definingFramework=true;isc.defineClass("SQLEditor","VLayout");isc.A=isc.SQLEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.sqlInputFormDefaults={_constructor:"DynamicForm",height:150,showResizeBar:true};isc.A.actionButtonsDefaults={_constructor:"HLayout",layoutMargin:5,membersMargin:5,height:20};isc.A.execSQLButtonDefaults={_constructor:"IButton",title:"Exec SQL",click:"this.creator.execSQL();",autoParent:"actionButtons"};isc.A.previewGridDefaults={_constructor:"ListGrid",minFieldWidth:100,autoFetchData:false};isc.B.push(isc.A.initWidget=function isc_SQLEditor_initWidget(){this.Super("initWidget",arguments);var _1=this;this.addAutoChild("sqlInputForm",{fields:[{name:"sql",showTitle:false,type:"textarea",width:"*",height:"*",colSpan:"*",keyPress:function(_2,_3,_4){if(_4=='Enter'&&isc.EH.ctrlKeyDown()){if(isc.Browser.isSafari)_2.setValue(_2.getElementValue());_1.execSQL();if(isc.Browser.isSafari)return false}}}]});this.addAutoChildren(["actionButtons","execSQLButton"])}
,isc.A.execSQL=function isc_SQLEditor_execSQL(){var _1=this.sqlInputForm.getValue("sql");if(_1){_1=_1.trim().replace(/(.*);+/,"$1");var _2=isc.DataSource.get("DataSourceStore");_2.performCustomOperation("dsFromSQL",{dbName:this.config.name,sql:_1},this.getID()+".dsLoaded(data)")}}
,isc.A.dsLoaded=function isc_SQLEditor_dsLoaded(_1){var _2=_1.ds;if(!this.previewGrid)this.addAutoChild("previewGrid",{dataSource:_2});else this.previewGrid.setDataSource(_2);this.previewGrid.fetchData()}
);isc.B._maxIndex=isc.C+3;isc.defineClass("SQLTableBrowser","VLayout");isc.A=isc.SQLTableBrowser.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.previewGridDefaults={_constructor:"ListGrid",canDragSelectText:true,autoFetchData:false,height:"*",minFieldWidth:100,showFilterEditor:true,canEdit:true};isc.A.previewGridStripDefaults={_constructor:"GridToolStrip",width:"100%",generateDSButtonDefaults:{_constructor:"IAutoFitButton",title:"Show DataSource",layoutAlign:"center",click:"this.creator.creator.showDS()"},members:["autoChild:removeButton","autoChild:addButton","autoChild:exportButton","autoChild:generateDSButton","starSpacer","autoChild:refreshButton","autoChild:totalRowsIndicator"]};isc.B.push(isc.A.initWidget=function isc_SQLTableBrowser_initWidget(){this.Super("initWidget",arguments);var _1=isc.DataSource.get("DataSourceStore");_1.performCustomOperation("dsFromTable",{schema:this.schema,dbName:this.dbName,tableName:this.config.name},this.getID()+".dsLoaded(data)")}
,isc.A.dsLoaded=function isc_SQLTableBrowser_dsLoaded(_1){this.dataSource=_1.ds;this.dataSourceXML=_1.dsXML;this.addAutoChild("previewGrid",{dataSource:this.dataSource});this.addAutoChild("previewGridStrip",{grid:this.previewGrid});this.previewGrid.filterData()}
,isc.A.showDS=function isc_SQLTableBrowser_showDS(){isc.say(this.dataSourceXML.asHTML(),null,{blurbProperties:{canSelectText:true,wrap:false}})}
);isc.B._maxIndex=isc.C+3;isc.defineClass("DBPane","TabSet");isc.A=isc.DBPane.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.sqlEditorDefaults={_constructor:"SQLEditor"};isc.A.tablePaneDefaults={_constructor:"SQLTableBrowser"};isc.B.push(isc.A.initWidget=function isc_DBPane_initWidget(){this.Super("initWidget",arguments);this.sqlEditor=this.createAutoChild("sqlEditor",{config:this.config});this.addTab({title:"SQL Editor",pane:this.sqlEditor})}
,isc.A.showTableBrowser=function isc_DBPane_showTableBrowser(_1){var _2=this.escapeForId(this.config.name+'_'+_1.name);this.showPane({ID:_2,title:_1.name,paneClass:"tablePane"},_1)}
,isc.A.escapeForId=function isc_DBPane_escapeForId(_1){return isc.isA.String(_1)?_1.replace(/(\/|\.)/g,'_'):_1}
,isc.A.showPane=function isc_DBPane_showPane(_1,_2){var _3=this.getTab(_1.ID);if(_3){this.selectTab(_3);return}
_3={};isc.addProperties(_3,_1,{canClose:true,pane:this.createAutoChild(_1.paneClass,{config:_2,dbName:this.config.name})});this.addTab(_3);this.selectTab(_3);this.currentPane=_3.pane}
);isc.B._maxIndex=isc.C+4;isc.DataSource.create({
ID:"DBSchema",
fields:[
{
name:"name"
},
{
name:"itemType"
},
{
name:"type"
},
{
name:"length",
type:"integer"
},
{
name:"primaryKey",
type:"boolean"
},
{
hidden:true,
name:"path",
primaryKey:true
},
{
foreignKey:"DBSchema.path",
hidden:true,
name:"parentID"
}
],
operationBindings:[
{
language:"groovy",
operationType:"fetch",
script:"\n            if (!com.isomorphic.auth.DevModeAuthFilter.devModeAuthorized(request)) throw new Exception(\"Not Authorized\");\n            import com.isomorphic.sql.*;\n           \n\n	    "
}
]
})
isc.defineClass("DBSchemaTree","ListGrid");isc.A=isc.DBSchemaTree.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.showFilterEditor=true;isc.A.filterOnKeypress=true;isc.A.serverType="sql";isc.A.emptyMessage="No tables defined";isc.A.canExpandRecords=true;isc.A.detailDefaults={_constructor:"ListGrid",autoFitData:"vertical",autoFitMaxRecords:8,showResizeBar:true};isc.B.push(isc.A.initWidget=function isc_DBSchemaTree_initWidget(){this.dataSource=isc.DataSource.create({ID:this.getID()+"$73h",clientOnly:true,fields:[{name:"name",title:"Name"},{name:"type",title:"Type",width:60,valueMap:["table","view"]}]});this.Super("initWidget",arguments)}
,isc.A.selectionChanged=function isc_DBSchemaTree_selectionChanged(_1,_2){this.tableSelected(_1.name)}
,isc.A.tableSelected=function isc_DBSchemaTree_tableSelected(_1){}
,isc.A.getExpansionComponent=function isc_DBSchemaTree_getExpansionComponent(_1){var _2=this.createAutoChild("detail",{sortField:"primaryKey",sortDirection:"descending",fields:[{name:"name",title:"Column",formatCellValue:function(_3,_1){if(_1.primaryKey)return"<b>"+_3+"</b>";return _3}},{name:"type",title:"Type",width:50},{name:"length",title:"Length",width:45},{name:"primaryKey",title:"PK",type:"boolean",showIf:"false",width:22}]});isc.DMI.call("isc_builtin","com.isomorphic.tools.BuiltinRPC","getFieldsFromTable",_1.name,this.schema,this.serverType,this.db.name,function(_3,_4){_2.setData(_4)});return _2}
,isc.A.invalidateCache=function isc_DBSchemaTree_invalidateCache(){this.setData([]);this.loadSchema(this.db)}
,isc.A.loadSchema=function isc_DBSchemaTree_loadSchema(_1){this.db=_1;isc.showPrompt("Loading schema for database: "+_1.name);isc.DMI.call("isc_builtin","com.isomorphic.tools.BuiltinRPC","getTables",this.serverType,_1.name,true,true,this.catalog,this.schema,this.includeList,this.excludeList,this.getID()+".loadSchemaReply(data)")}
,isc.A.loadSchemaReply=function isc_DBSchemaTree_loadSchemaReply(_1){isc.clearPrompt();for(var i=0;i<_1.length;i++){_1[i].name=_1[i].TABLE_NAME;_1[i].type=_1[i].TABLE_TYPE.toLowerCase()}
this.setData(isc.ResultSet.create({dataSource:this.dataSource,allRows:_1}));this.sort("name");if(this.schemaLoaded)this.fireCallback("schemaLoaded")}
);isc.B._maxIndex=isc.C+7;isc.DBSchemaTree.registerStringMethods({schemaLoaded:""});isc.DataSource.create({
ID:"DataSourceStore",
fields:[
{
name:"ID",
primaryKey:true
},
{
name:"version"
},
{
length:50000,
name:"dsXML",
type:"text"
},
{
hidden:true,
name:"config"
},
{
hidden:true,
name:"dbName"
},
{
hidden:true,
name:"tableName"
},
{
hidden:true,
name:"schema"
},
{
hidden:true,
name:"sql"
},
{
hidden:true,
name:"ds",
type:"DataSource"
}
],
operationBindings:[
{
language:"groovy",
operationId:"dsFromSQL",
operationType:"custom",
script:"\n            if (!com.isomorphic.auth.DevModeAuthFilter.devModeAuthorized(request)) throw new Exception(\"Not Authorized\");\n            import com.isomorphic.sql.*;\n           \n            def dsId = values.dbName+\"_devSqlBrowser_\"+System.currentTimeMillis();\n            def conn = SQLConnectionManager.getConnection(values.dbName);\n            def ds = SQLDataSource.fromTable(conn, null, values.schema, dsId, \"sql\", values.dbName,\n                        [operationType:\"fetch\", customSQL: values.sql], true, null);\n            SQLConnectionManager.free(conn);\n\n/*\n            def ds = DataSource.fromConfig([\n                ID: dsId,\n                dbName: values.dbName,\n                serverType: \"sql\",\n                autoDeriveSchema: \"true\",\n                operationBindings: [\n                    [type: \"fetch\", autoDeriveSchemaOperation: \"true\", tableClause: values.sql]\n                ]\n            ]);\n*/\n            return [ds:ds];\n	    "
},
{
language:"groovy",
operationId:"dsFromTable",
operationType:"custom",
script:"\n            if (!com.isomorphic.auth.DevModeAuthFilter.devModeAuthorized(request)) throw new Exception(\"Not Authorized\");\n            import com.isomorphic.sql.*;\n\n            def dsId = values.dbName+\"_\"+values.tableName+\"_devTableBrowser_\"+System.currentTimeMillis();\n            def ds = SQLDataSource.fromTable(null, values.tableName, values.schema, dsId, \"sql\", values.dbName);\n            return [ds:ds, dsXML:ds.toXML()];\n	    "
},
{
language:"groovy",
operationId:"dsFromConfig",
operationType:"custom",
script:"\n            if (!com.isomorphic.auth.DevModeAuthFilter.devModeAuthorized(request)) throw new Exception(\"Not Authorized\");\n            import com.isomorphic.store.*;\n            import com.isomorphic.datasource.*;\n\n            def ds = DataSource.fromConfig(values.config, dsRequest);\n            if (ds != null) {\n                DataStructCache.addCachedObjectWithNoConfigFile(ds.getName(), ds);\n            }\n            return [ds:ds, dsXML: ds.toXML()];\n	    "
}
]
})
isc.DataSource.create({
ID:"DBListDS",
dropExtraFields:false,
fields:[
{
name:"name",
primaryKey:true
},
{
name:"type"
},
{
name:"version"
},
{
name:"status"
}
],
operationBindings:[
{
language:"groovy",
operationType:"fetch",
script:"\n            if (!com.isomorphic.auth.DevModeAuthFilter.devModeAuthorized(request)) throw new Exception(\"Not Authorized\");\n            import com.isomorphic.sql.*;\n            import com.isomorphic.tools.*;\n\n            def dbNames = SQLConnectionManager.getDefinedDatabaseNames();\n            dbNames.collect{\n                def name = it;\n\n                // type and version\n                def type = \"N/A\";\n                def version = \"N/A\";\n                def status = \"OK\";\n                try {\n                    def conn = SQLConnectionManager.getConnection(name);\n                    def smd = new SQLMetaData(conn);\n                    def result = smd.getProductNameAndVersion();\n                    SQLConnectionManager.free(conn);\n                    type = result.productName;\n                    version = result.productVersion;\n                } catch (Exception e) { \n                    if (e.toString() =~ /ClassNotFoundException/) status = \"JDBC driver not installed\";\n                    else status = \"Unable to connect\";\n                }\n\n                [name:name, status: status, type: type, version: version];\n            }\n	    "
}
]
})
isc.defineClass("DBCompactList","DynamicForm");isc.A=isc.DBCompactList.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.width=200;isc.A.numCols=2;isc.A.colWidths=[80,"*"];isc.B.push(isc.A.initWidget=function isc_DBCompactList_initWidget(){this.fields=[{name:"dbName",title:"Database",type:"select",width:"*",type:"select",width:120,optionDataSource:"DBListDS",displayField:"name",valueField:"name",change:"if (this.form.databaseChanged) this.form.fireCallback('databaseChanged', 'dbName', [value])",valueMap:{}}];this.Super("initWidget",arguments)}
,isc.A.getSelectedDB=function isc_DBCompactList_getSelectedDB(){return this.getValue("dbName")}
,isc.A.setSelectedDB=function isc_DBCompactList_setSelectedDB(_1){return this.setValue("dbName",_1)}
);isc.B._maxIndex=isc.C+3;isc.DBCompactList.registerStringMethods({databaseChanged:"dbName"});isc.defineClass("DBList","ListGrid");isc.A=isc.DBList.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.dataSource="DBListDS";isc.A.showFilterEditor=true;isc.A.filterOnKeypress=true;isc.A.sortField="name";isc.B.push(isc.A.initWidget=function isc_DBList_initWidget(){this.Super("initWidget",arguments)}
,isc.A.dataArrived=function isc_DBList_dataArrived(){this.Super("dataArrived",arguments);if(!this.initialCriteriaSet){var _1={status:"OK"};this.setFilterEditorCriteria(_1);this.initialCriteriaSet=true;this.filterData(_1)}
this.initialCriteriaSet=false}
,isc.A.cellHoverHTML=function isc_DBList_cellHoverHTML(_1){if(!this.hoverDV)this.hoverDV=isc.DetailViewer.create({dataSource:this.dataSource,width:200,autoDraw:false});this.hoverDV.setData(_1);return this.hoverDV.getInnerHTML()}
,isc.A.destroy=function isc_DBList_destroy(){this.Super("destroy",arguments);if(this.hoverDV)this.hoverDV.destroy()}
);isc.B._maxIndex=isc.C+4;isc.defineClass("SQLBrowser","VLayout");isc.A=isc.SQLBrowser;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.showWindow=function isc_c_SQLBrowser_showWindow(_1,_2){isc.Window.create({title:"SQL Browser",width:"100%",height:"100%",canDragReposition:false,closeClick:function(){this.destroy()},items:[isc.SQLBrowser.create({autoDraw:false},_2)]},_1).show()}
);isc.B._maxIndex=isc.C+1;isc.A=isc.SQLBrowser.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.dbListDefaults={_constructor:"DBList",height:150,canDragSelectText:true,autoFetchData:true,canHover:true,defaultFields:[{name:"name"},{name:"status"}]};isc.A.dbListRefreshButtonDefaults={_constructor:"Img",size:16,src:"[SKIN]/actions/refresh.png",click:"this.creator.dbList.invalidateCache()"};isc.A.dbSchemaTreeDefaults={_constructor:"DBSchemaTree",canDragSelectText:true,animateFolders:false,showConnectors:false,recordClick:function(_1,_2){this.creator.showTablePane(_2)}};isc.A.dbSchemaRefreshButtonDefaults={_constructor:"Img",size:16,src:"[SKIN]/actions/refresh.png",click:"this.creator.dbSchemaTree.invalidateCache()"};isc.A.leftSectionDefaults={_constructor:"SectionStack",headerHeight:25,width:300,showResizeBar:true,animateSections:isc.Browser.isSafari,visibilityMode:"visible",autoParent:"mainLayout"};isc.A.mainLayoutDefaults={_constructor:"HLayout",height:"*"};isc.A.rightPaneDefaults={_constructor:"TabSet",tabs:[{name:"welcome",title:"Welcome",ID:"dsb_welcome_tab",canClose:true,pane:isc.Label.create({height:10,autoDraw:false,overflow:"visible",contents:"Select a database on the left..."})}]};isc.A.autoChildren=["mainLayout"];isc.A.dbPaneDefaults={_constructor:"DBPane"};isc.B.push(isc.A.initWidget=function isc_SQLBrowser_initWidget(){this.Super("initWidget",arguments);this.dbList=this.createAutoChild("dbList",{selectionChanged:"if (state) this.creator.databaseChanged(record)"});this.dbListRefreshButton=this.createAutoChild("dbListRefreshButton");this.dbSchemaTree=this.createAutoChild("dbSchemaTree",{});this.dbSchemaRefreshButton=this.createAutoChild("dbSchemaRefreshButton");this.leftSection=this.createAutoChild("leftSection",{sections:[{name:"databases",title:"Databases",expanded:true,controls:[this.dbListRefreshButton],items:[this.dbList]},{name:"tables",title:"Tables & Views",expanded:true,controls:[this.dbSchemaRefreshButton],items:[this.dbSchemaTree]}]});this.addAutoChildren(this.autoChildren);this.mainLayout.addMember(this.leftSection);this.rightPane=this.createAutoChild("rightPane");this.mainLayout.addMember(this.rightPane)}
,isc.A.showDBPane=function isc_SQLBrowser_showDBPane(){var _1=this.db;this.showPane({ID:this.escapeForId("db_"+_1.name),title:_1.name,paneClass:"dbPane"},_1)}
,isc.A.databaseChanged=function isc_SQLBrowser_databaseChanged(_1){if(_1.status=="OK"){this.db=_1;this.dbSchemaTree.loadSchema(_1);this.showDBPane()}}
,isc.A.showTablePane=function isc_SQLBrowser_showTablePane(_1){this.showDBPane();this.currentPane.showTableBrowser(_1)}
,isc.A.escapeForId=function isc_SQLBrowser_escapeForId(_1){return isc.isA.String(_1)?_1.replace(/(\/|\.)/g,'_'):_1}
,isc.A.showPane=function isc_SQLBrowser_showPane(_1,_2){var _3=this.rightPane.getTab(_1.ID);if(_3){this.currentPane=_3.pane;this.rightPane.selectTab(_3);return}
_3={};isc.addProperties(_3,_1,{canClose:true,pane:this.createAutoChild(_1.paneClass,{config:_2})});var _4=this.rightPane.getTab(0);if(_4&&_4.name=="welcome")this.rightPane.removeTab(0);this.rightPane.addTab(_3);this.rightPane.selectTab(_3);this.currentPane=_3.pane}
);isc.B._maxIndex=isc.C+6;isc._moduleEnd=isc._SQLBrowser_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('SQLBrowser module init time: ' + (isc._moduleEnd-isc._moduleStart) + 'ms','loadTime');delete isc.definingFramework;}else{if(window.isc && isc.Log && isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'SQLBrowser'.");}
/*
 * Isomorphic SmartClient
 * Version v8.2p_2012-06-03 (2012-06-03)
 * Copyright(c) 1998 and beyond Isomorphic Software, Inc. All rights reserved.
 * "SmartClient" is a trademark of Isomorphic Software, Inc.
 *
 * licensing@smartclient.com
 *
 * http://smartclient.com/license
 */

