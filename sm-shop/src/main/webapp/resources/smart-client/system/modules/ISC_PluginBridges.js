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

if(window.isc&&window.isc.module_Core&&!window.isc.module_PluginBridges){isc.module_PluginBridges=1;isc._moduleStart=isc._PluginBridges_start=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc._moduleEnd&&(!isc.Log||(isc.Log && isc.Log.logIsDebugEnabled('loadTime')))){isc._pTM={ message:'PluginBridges load/parse time: ' + (isc._moduleStart-isc._moduleEnd) + 'ms', category:'loadTime'};
if(isc.Log && isc.Log.logDebug)isc.Log.logDebug(isc._pTM.message,'loadTime')
else if(isc._preLog)isc._preLog[isc._preLog.length]=isc._pTM
else isc._preLog=[isc._pTM]}isc.definingFramework=true;isc.ClassFactory.defineClass("BrowserPlugin","Canvas");isc.A=isc.BrowserPlugin;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.instances=[];isc.B.push(isc.A.handleDragMoveNotify=function isc_c_BrowserPlugin_handleDragMoveNotify(){}
);isc.B._maxIndex=isc.C+1;isc.A=isc.BrowserPlugin.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.src="";isc.A.extraHTML="";isc.A.installPlugin=true;isc.A.redrawOnResize=false;isc.A.$jp=false;isc.A._redrawWithParent=false;isc.A.useDragMask=true;isc.A.usePlaceholderDragMask=!isc.Browser.isMoz;isc.A.dragPlaceholderMessage="Dragging...";isc.A.dragPlaceholderStyle="normal";isc.B.push(isc.A.initWidget=function isc_BrowserPlugin_initWidget(){isc.BrowserPlugin.instances.add(this);if(this.useDragMask)isc.EH.registerMaskableItem(this,true)}
,isc.A.destroy=function isc_BrowserPlugin_destroy(){isc.BrowserPlugin.instances.remove(this);this.Super("destroy",arguments)}
,isc.A.draw=function isc_BrowserPlugin_draw(){this.Super("draw",arguments);if(this.backMaskCausesBurnThrough){var _1=this;this.getParentElements().map(function(_2){if(_2.useBackMask){_1.logInfo("Suppressing backmask of ancestor: "+_2.getID());if(_2._backMask){_2._backMask.suppressed=true;_2._backMask.hide()}else{if(!_2.$49t)_2.$49t={};_2.$49t.suppressed=true}}})}}
,isc.A.getPluginHandle=function isc_BrowserPlugin_getPluginHandle(){return document.getElementById(this.getPluginID())}
,isc.A.getPluginID=function isc_BrowserPlugin_getPluginID(){return this.getID()+"_plugin"}
,isc.A.$mb=function isc_BrowserPlugin__showDragMask(){if(!this.usePlaceholderDragMask)return this.Super("$mb",arguments);var _1=this.getPluginHandle();if(_1){_1.style.visibility="hidden";if(!this.$49u)this.$49u=this.createDragPlaceholder();if(this.$49u){isc.addProperties(this.$49u,{$jr:this,getTarget:function(){return this.$jr}});this.$49u.setRect(this.getPageRect());this.$49u.show()}}}
,isc.A.$mc=function isc_BrowserPlugin__hideDragMask(){if(!this.usePlaceholderDragMask)return this.Super("$mc",arguments);var _1=this.getPluginHandle();if(_1){_1.style.visibility="inherit";if(this.$49u)this.$49u.hide()}}
,isc.A.createDragPlaceholder=function isc_BrowserPlugin_createDragPlaceholder(){return isc.Label.create({align:"center",contents:this.dragPlaceholderMessage,styleName:this.dragPlaceholderStyle})}
);isc.B._maxIndex=isc.C+8;isc.ClassFactory.defineClass("Applet","BrowserPlugin");isc.A=isc.Applet;isc.A.appletScanInterval=500;isc.A=isc.Applet;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.initComplete=function isc_c_Applet_initComplete(_1){this.jvmVersionString=_1;this.jvmVersion=parseFloat(_1);this.logInfo("ISCEventProxy init complete - jvmVersion: "+_1+" - derived version: "+this.jvmVersion)}
,isc.A.idForName=function isc_c_Applet_idForName(_1){if(_1&&_1.endsWith("$260"))return _1.substring(0,_1.length-7)}
,isc.A.startJavaEventProxy=function isc_c_Applet_startJavaEventProxy(){if(this.eventProxyApplet)return;this.eventProxyApplet=isc.Applet.create({top:-1000,width:10,height:10,autoDraw:false,useJavaEventProxy:false,useDragMask:true,params:{debug:this.debug,useEventMasks:this.useEventMasks,appletScanInterval:this.appletScanInterval},$mb:function(){var _1=this.getPluginHandle();if(_1)_1.showDragMask()},$mc:function(){var _1=this.getPluginHandle();if(_1)_1.hideDragMask()},ID:"isc_eventProxyApplet",archive:isc.Page.getURL("[HELPERS]isomorphic_applets.jar"),code:"com/isomorphic/applets/ISCEventProxy.class"});this.eventProxyApplet.draw()}
);isc.B._maxIndex=isc.C+3;isc.A=isc.Applet.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.mayScript=true;isc.A.scriptable=true;isc.A.classID="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93";isc.A.objectCodeBase="http://java.sun.com/products/plugin/1.3/jinstall-13-win32.cab#Version=1,3,0,0";isc.A.useTag="applet";isc.A.useClipDiv=false;isc.A.useJavaEventProxy=isc.Browser.isIE;isc.A.useDragMask=!isc.Browser.isIE;isc.A.usePlaceholderDragMask=false;isc.A.backMaskCausesBurnThrough=isc.Browser.isMoz;isc.B.push(isc.A.draw=function isc_Applet_draw(){if(this.useJavaEventProxy)isc.Applet.startJavaEventProxy();this.Super("draw",arguments)}
,isc.A.getInnerHTML=function isc_Applet_getInnerHTML(){var _1=isc.StringBuffer.newInstance();if(this.code==null&&this.src!=null)this.code=this.src;if(this.useTag=="applet"){_1.append("<applet name='",this.getPluginID(),"' width='100%' height='100%'"," iscCanvasID='",this.getID(),"'");if(this.mayScript)_1.append(" mayScript");if(this.scriptable)_1.append(" scriptable");if(this.code)_1.append(" code='",this.code,"'");if(this.codeBase)_1.append(" codeBase='",this.codeBase,"'");if(this.archive)_1.append(" archive='",this.archive,"'");if(this.alt)_1.append(" alt='",this.alt,"'");if(this.extraHTML)_1.append(" ",this.extraHTML);_1.append(">");if(this.params){for(var _2 in this.params){_1.append("<param name='",_2,"' value='",this.params[_2],"'>")}}
if(this.altHTML)_1.append(this.altHTML);_1.append("</applet>")}else if(this.useTag=="object"){_1.append("<object classid='",this.classID,"' codebase='",this.objectCodeBase,"' width='100%' height='100%'");if(this.extraHTML)_1.append(" ",this.extraHTML);_1.append(">");_1.append("<param name='name' value='",this.getPluginID(),"'>");_1.append("<param name='iscCanvasID' value='",this.getID(),"'>");if(this.mayScript)_1.append("<param name='mayscript' value='true'>");if(this.scriptable)_1.append("<param name='scriptable' value='true'>");if(this.code)_1.append("<param name='code' value='",this.code,"'>");if(this.codeBase)_1.append("<param name='codeBase' value='",this.codeBase,"'>");if(this.archive)_1.append("<param name='archive' value='",this.archive,"'>");if(this.alt)_1.append("<param name='alt' value='",this.alt,"'>");if(this.params){for(var _2 in this.params){_1.append("<param name='",_2,"' value='",this.params[_2],"'>")}}
_1.append("</object>")}
return _1.toString()}
,isc.A.getPluginID=function isc_Applet_getPluginID(){if(!this.name)this.name=this.getID()+"$260";return this.name}
,isc.A.getPluginHandle=function isc_Applet_getPluginHandle(){return document[this.getPluginID()]}
,isc.A.repaint=function isc_Applet_repaint(){var _1=this.getPluginHandle();if(_1)_1.repaint()}
,isc.A.repaintOnDragStop=function isc_Applet_repaintOnDragStop(){return this.useJavaEventProxy&&isc.Applet.jvmVersion<1.4}
,isc.A.$mc=function isc_Applet__hideDragMask(){this.Super("$mc",arguments);if(this.repaintOnDragStop())this.repaint()}
);isc.B._maxIndex=isc.C+7;isc.ClassFactory.defineClass("Flashlet","BrowserPlugin");isc.A=isc.Flashlet;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.flashAvailable=function isc_c_Flashlet_flashAvailable(){if(this.flashSupported!=null)return this.flashSupported;isc.Flashlet.flashVersion=0;isc.Flashlet.flashSupported=false;if(isc.Browser.isIE){var _1=isc.StringBuffer.concat('on error resume next\n','For i = 2 to 9\n','If Not(IsObject(CreateObject("ShockwaveFlash.ShockwaveFlash." & i))) Then\n','Else\n','isc.Flashlet.flashSupported = true\n','isc.Flashlet.flashVersion = i\n','End If\n','Next');window.execScript(_1,"VBScript")}else{var _2=navigator.plugins["Shockwave Flash"];if(_2==null)_2=navigator.plugins["Shockwave Flash 2.0"];if(_2!=null){this.flashSupported=true;var _3=_2.description.substring(16),_4=parseFloat(_3.split(" ")[0]);this.$43y=_3;this.flashVersion=_4}else{this.flashSupported=false}}
return this.flashSupported}
,isc.A.getFlashVersion=function isc_c_Flashlet_getFlashVersion(){if(this.flashAvailable())return this.flashVersion}
);isc.B._maxIndex=isc.C+2;isc.A=isc.Flashlet.getPrototype();isc.A.useClipDiv=false;isc.A.useDragMask=false;isc.A.classID="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000";isc.A.codeBase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=5,0,0,0";isc.A.pluginsPage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash";isc.A.type="application/x-shockwave-flash";isc.A=isc.Flashlet.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.setSrc=function isc_Flashlet_setSrc(_1){this.src=_1;this.markForRedraw()}
,isc.A.getInnerHTML=function isc_Flashlet_getInnerHTML(){var _1=isc.SB.create();if(this.name==null)this.name=this.getPluginID();var _2=window.location.protocol,_3=this.codeBase;if(_2&&_2.startsWith("https")&&_3&&_3.startsWith("http://")){_3=_3.replace("http://","https://")}
_1.append("<object classid='",this.classID,"' codebase='",_3,"' width='100%' height='100%' ID='",this.name,"'");if(this.extraObjectHTML)_1.append(" ",this.extraObjectHTML);_1.append(">");var _4={};isc.addProperties(_4,this.params);if(!_4.movie)_4.movie=this.src||this.movie;if(!_4.wmode)_4.wmode="opaque"
for(var _5 in _4)
_1.append("<param name='",_5,"' value='",_4[_5],"'>");_1.append("<embed width='100%' height='100%' name='",this.name,"' src=\"",this.src,"\" pluginspage=\"",this.pluginsPage,"\" type='",this.type,"'");for(var _5 in _4)
_1.append(" ",_5,"='",_4[_5],"'");if(this.extraEmbedHTML)_1.append(" ",this.extraEmbedHTML);_1.append(">");_1.append("</embed>");_1.append("</object>");return _1.toString()}
,isc.A.getPluginID=function isc_Flashlet_getPluginID(){return this.getID()+"$261"}
,isc.A.getPluginHandle=function isc_Flashlet_getPluginHandle(){if(this.name==null)return null;if(isc.Browser.isIE)return window[this.name];return document[this.name]}
);isc.B._maxIndex=isc.C+4;isc.ClassFactory.defineClass("SVG","BrowserPlugin");isc.A=isc.SVG.getPrototype();isc.A.useNativeMask=true;isc.A.pluginsPage="http://www.adobe.com/svg/viewer/install/";isc.A.src=isc.Page.getHelperDir()+"svgCanvas.svg";isc.A=isc.SVG;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.register=function isc_c_SVG_register(_1){var _2=_1.getTarget();var _3=_2.getOwnerDocument();var _4=this.getSVGCanvas(_3);_4.addProperties({svgElement:_2,svgDocument:_3,svgDoc:_3,svgRoot:_3.getRootElement()});var _5=_4.getPluginHandle();if(_5["window"])_5["window"].svgCanvas=_4;if(isc.isA.Function(_4.initSVG))_4.initSVG();else if(isc.isA.Function(_4.initsvg))_4.initsvg();if(_4.useNativeMask)_4.$266();if(_4.$267)_4.showNativeMask()}
,isc.A.getSVGCanvas=function isc_c_SVG_getSVGCanvas(_1){var _2=_1.getURL();if(_2.indexOf("#")==-1){this.logError("Can't locate svgCanvas for svgDocument. Use SVG.create() to render SVGs");return null}
var _3=_2.substring(_2.indexOf("#")+1,_2.length);var _4=window[_3];if(!_4){this.logError("Can't locate svg instance for id: "+_3+" did you call SVG.register(evt)?");return null}
return _4}
);isc.B._maxIndex=isc.C+2;isc.A=isc.SVG.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.draw=function isc_SVG_draw(){this.Super("draw",arguments);if(isc.Browser.isIE)isc.EH.registerMaskableItem(this,true)}
,isc.A.mouseOut=function isc_SVG_mouseOut(){if(this.useNativeMask){this.hideNativeMask();this.Super("$mc")}}
,isc.A.$mb=function isc_SVG__showDragMask(){if(this.useNativeMask)this.showNativeMask();else this.Super("$mb")}
,isc.A.$mc=function isc_SVG__hideDragMask(){if(this.useNativeMask)this.hideNativeMask();else this.Super("$mc")}
,isc.A.handleSVGEvent=function isc_SVG_handleSVGEvent(_1){if(_1.type=="mousemove"){if(this.lastMouseMoveX==_1.clientX&&this.lastMouseMoveY==_1.clientY)return;this.lastMouseMoveX=_1.clientX;this.lastMouseMoveY=_1.clientY}
var _2={type:_1.type,target:this,clientX:_1.clientX,clientY:_1.clientY};isc.EventHandler.handleSyntheticEvent(_2)}
,isc.A.$266=function isc_SVG__makeSVGEventMask(){this.$268=this.svgDoc.createElement("rect");this.$269=this.getID()+"_SVGEventMask";var _1={id:this.$269,x:"-1073741823",y:"-1073741823",width:"2147483647",height:"2147483647",opacity:"0.0",visibility:"hidden",onmousemove:"svgCanvas.handleSVGEvent(evt)",onmouseup:"svgCanvas.handleSVGEvent(evt)",onmouseout:"svgCanvas.handleSVGEvent(evt)",onclick:"svgCanvas.handleSVGEvent(evt)",oncontextmenu:"svgCanvas.handleSVGEvent(evt)"};for(var _2 in _1)this.$268.setAttribute(_2,_1[_2]);this.svgRoot.appendChild(this.$268)}
,isc.A.setZIndex=function isc_SVG_setZIndex(){}
,isc.A.showNativeMask=function isc_SVG_showNativeMask(){if(!this.svgDoc){this.logWarn("showNativeMask called before SVG.register() - deferring until SVG.register()");this.$267=true;return}
if(this.$268){this.svgRoot.removeChild(this.$268);this.svgRoot.appendChild(this.$268)}else{this.$266()}
this.$268.setAttribute("visibility","visible")}
,isc.A.hideNativeMask=function isc_SVG_hideNativeMask(){if(this.$268)this.$268.setAttribute("visibility","hidden")}
,isc.A.getInnerHTML=function isc_SVG_getInnerHTML(){if(isc.Browser.isIE){return"<embed name='"+this.getPluginID()+"' src=\""+isc.Page.getURL(this.src)+"#"+this.getID()+"\" width='100%' height='100%'"+(this.installPlugin?"pluginspage='"+this.pluginsPage+"'":"")+" type='image/svg+xml' "+this.extraHTML+" >"}
return this.Super("getInnerHTML",arguments)}
,isc.A.destroy=function isc_SVG_destroy(){if(this.$268)delete this.$268;var _1=this.getPluginHandle();if(_1&&_1["window"])delete _1["window"].svgCanvas;this.Super("destroy")}
,isc.A.setNodeAttributes=function isc_SVG_setNodeAttributes(_1,_2){for(var _3 in _2)_1.setAttribute(_3,_2[_3])}
);isc.B._maxIndex=isc.C+12;isc.ClassFactory.defineClass("ActiveXControl","BrowserPlugin");isc.A=isc.ActiveXControl.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.getInnerHTML=function isc_ActiveXControl_getInnerHTML(){var _1=isc.StringBuffer.newInstance();var _2=this.classID?this.classID:"clsid:"+this.uuid;_1.append("<object classid='",_2,"' codebase='",this.codeBase,"' id='"+this.getPluginID(),"' width='100%' height='100%'");if(this.extraHTML)_1.append(" ",this.extraHTML);_1.append(">");_1.append("<param name='iscCanvasID' value='",this.getID(),"'>");if(this.params){for(var _3 in this.params){_1.append("<param name='",_3,"' value='",this.params[_3],"'>")}}
_1.append("</object>");return _1.toString()}
,isc.A.getPluginID=function isc_ActiveXControl_getPluginID(){if(!this.id)this.id=this.getID()+"$27a";return this.id}
,isc.A.getPluginHandle=function isc_ActiveXControl_getPluginHandle(){return window[this.getPluginID()]}
);isc.B._maxIndex=isc.C+3;isc._moduleEnd=isc._PluginBridges_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('PluginBridges module init time: ' + (isc._moduleEnd-isc._moduleStart) + 'ms','loadTime');delete isc.definingFramework;}else{if(window.isc && isc.Log && isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'PluginBridges'.");}
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

