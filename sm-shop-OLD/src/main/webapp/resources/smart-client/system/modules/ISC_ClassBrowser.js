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

if(window.isc&&window.isc.module_Core&&!window.isc.module_ClassBrowser){isc.module_ClassBrowser=1;isc._moduleStart=isc._ClassBrowser_start=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc._moduleEnd&&(!isc.Log||(isc.Log && isc.Log.logIsDebugEnabled('loadTime')))){isc._pTM={ message:'ClassBrowser load/parse time: ' + (isc._moduleStart-isc._moduleEnd) + 'ms', category:'loadTime'};
if(isc.Log && isc.Log.logDebug)isc.Log.logDebug(isc._pTM.message,'loadTime')
else if(isc._preLog)isc._preLog[isc._preLog.length]=isc._pTM
else isc._preLog=[isc._pTM]}isc.definingFramework=true;isc.defineClass("JavaClassPane","VLayout");isc.A=isc.JavaClassPane.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.sourceViewDefaults={_constructor:"HTMLFlow",autoDraw:false,height:"*"};isc.B.push(isc.A.initWidget=function isc_JavaClassPane_initWidget(){this.Super("initWidget",arguments);this.sourceView=this.createAutoChild("sourceView",{contents:"Loading..."});this.addMember(this.sourceView);this.loadSource()}
,isc.A.loadSource=function isc_JavaClassPane_loadSource(){isc.DMI.call("isc_builtin","com.isomorphic.tools.BuiltinRPC","getJavaSource",this.config.path,this.getID()+".loadSourceReply(data)")}
,isc.A.loadSourceReply=function isc_JavaClassPane_loadSourceReply(_1){var _2=isc.JSSyntaxHiliter.create();this.sourceView.setContents(_2.hilite(_1))}
);isc.B._maxIndex=isc.C+3;isc.DataSource.create({
ID:"JVMClassTreeDS",
fields:[
{
name:"name"
},
{
name:"path",
primaryKey:true
},
{
foreignKey:"JVMClassTreeDS.path",
hidden:true,
name:"parentID"
},
{
name:"isFolder",
type:"boolean"
}
],
operationBindings:[
{
language:"groovy",
operationType:"fetch",
script:"\n            if (!com.isomorphic.auth.DevModeAuthFilter.devModeAuthorized(request)) throw new Exception(\"Not Authorized\");\n\n            def namespace = criteria.parentID;\n            def classLoader = Thread.currentThread().getContextClassLoader();\n\n            if (namespace == null) {\n                return classLoader.getPackages().collect{it.name}.collect{\n                    def dotIndex = it.indexOf(\".\");\n                    dotIndex != -1 ? it.substring(0, dotIndex) : it;\n                }.unique().sort().collect{\n                    [name: it, path: it, parentID: namespace];\n                }\n            }\n\n            // non-root\n            def namespaces = classLoader.getPackages().collect{it.name}.findAll{ it.startsWith(namespace+\".\") }.collect{ it.substring(namespace.length()+1) }.collect{\n                def dotIndex = it.indexOf(\".\");\n                dotIndex != -1 ? it.substring(0, dotIndex) : it;\n            }.unique().sort().collect{\n                [name: it, path: namespace+\".\"+it, parentID: namespace];\n            };\n\n            // lookup classes for this namespace...\n            def resources = classLoader.getResources(namespace.replace('.', '/'));\n            def resource = resources.hasMoreElements() ? resources.nextElement() : null;\n            def children;\n            def url;\n            if (resource) {\n                url = resource.getFile();\n                log.warn(url);\n                if (url.startsWith(\"jar:\")) {\n            \n                } else {\n                    try {\n                        def file = new File(url);\n                        children = file.list().findAll{ it =~ /\\.class$/}.sort().collect{ \n                            def name = it.substring(0, it.length()-6);\n                            [name:name,path:namespace+\".\"+name,parentID:namespace,isFolder:false] \n                        };\n                    } catch (ignore) {}\n                }\n            }\n\n            namespaces?.addAll(children?:[]);\n            return namespaces;\n    	"
}
]
})
isc.defineClass("JVMClassTree","TreeGrid");isc.A=isc.JVMClassTree.getPrototype();isc.A.dataSource="JVMClassTreeDS";isc.A.animateFolders=false;isc.JVMClassTree.registerStringMethods({});isc.defineClass("ClassBrowser","VLayout");isc.A=isc.ClassBrowser;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.showWindow=function isc_c_ClassBrowser_showWindow(_1,_2){isc.Window.create({title:"Class Browser",width:"100%",height:"100%",canDragReposition:false,closeClick:function(){this.destroy()},items:[isc.ClassBrowser.create({autoDraw:false},_2)]},_1).show()}
);isc.B._maxIndex=isc.C+1;isc.A=isc.ClassBrowser.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.classTreeDefaults={_constructor:"JVMClassTree",autoDraw:false,autoFetchData:true,recordDoubleClick:function(_1,_2){if(this.data.isLeaf(_2))this.creator.showClassPane(_2)}};isc.A.leftSectionDefaults={_constructor:"SectionStack",headerHeight:25,width:300,showResizeBar:true,animateSections:isc.Browser.isSafari,visibilityMode:"visible",autoParent:"mainLayout"};isc.A.mainLayoutDefaults={_constructor:"HLayout",height:"*"};isc.A.rightPaneDefaults={_constructor:"TabSet",tabs:[{name:"welcome",title:"Welcome",ID:"dsb_welcome_tab",canClose:true,pane:isc.Label.create({height:10,autoDraw:false,overflow:"visible",contents:"Select a class on the left..."})}]};isc.A.autoChildren=["mainLayout"];isc.A.classPaneDefaults={_constructor:"JavaClassPane"};isc.B.push(isc.A.initWidget=function isc_ClassBrowser_initWidget(){this.Super("initWidget",arguments);this.classTree=this.createAutoChild("classTree",{selectionChanged:"if (state) this.creator.classChanged(record)"});this.leftSection=this.createAutoChild("leftSection",{sections:[{name:"classes",title:"Classes",expanded:true,controls:[],items:[this.classTree]}]});this.addAutoChildren(this.autoChildren);this.mainLayout.addMember(this.leftSection);this.rightPane=this.createAutoChild("rightPane");this.mainLayout.addMember(this.rightPane)}
,isc.A.classChanged=function isc_ClassBrowser_classChanged(_1){this.showClassPane(_1)}
,isc.A.showClassPane=function isc_ClassBrowser_showClassPane(_1){var _2="class_"+this.escapeForId(_1.path);this.showPane({ID:_2,title:"Class: "+_1.name,paneClass:"classPane"},_1)}
,isc.A.escapeForId=function isc_ClassBrowser_escapeForId(_1){return isc.isA.String(_1)?_1.replace(/(\/|\.)/g,'_'):_1}
,isc.A.showPane=function isc_ClassBrowser_showPane(_1,_2){var _3=this.rightPane.getTab(_1.ID);if(_3){this.currentPane=_3.pane;this.rightPane.selectTab(_3);return}
_3={};isc.addProperties(_3,_1,{canClose:true,pane:this.createAutoChild(_1.paneClass,{config:_2})});var _4=this.rightPane.getTab(0);if(_4&&_4.name=="welcome")this.rightPane.removeTab(0);this.rightPane.addTab(_3);this.rightPane.selectTab(_3);this.currentPane=_3.pane}
);isc.B._maxIndex=isc.C+5;isc._moduleEnd=isc._ClassBrowser_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('ClassBrowser module init time: ' + (isc._moduleEnd-isc._moduleStart) + 'ms','loadTime');delete isc.definingFramework;}else{if(window.isc && isc.Log && isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'ClassBrowser'.");}
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

