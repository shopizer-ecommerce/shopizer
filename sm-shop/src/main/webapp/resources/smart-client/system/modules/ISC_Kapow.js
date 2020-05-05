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

if(window.isc&&window.isc.module_Core&&!window.isc.module_Kapow){isc.module_Kapow=1;isc._moduleStart=isc._Kapow_start=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc._moduleEnd&&(!isc.Log||(isc.Log && isc.Log.logIsDebugEnabled('loadTime')))){isc._pTM={ message:'Kapow load/parse time: ' + (isc._moduleStart-isc._moduleEnd) + 'ms', category:'loadTime'};
if(isc.Log && isc.Log.logDebug)isc.Log.logDebug(isc._pTM.message,'loadTime')
else if(isc._preLog)isc._preLog[isc._preLog.length]=isc._pTM
else isc._preLog=[isc._pTM]}isc.definingFramework=true;isc.defineClass("RobotServerPicker","Window");isc.A=isc.RobotServerPicker.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.autoCenter=true;isc.A.autoSize=true;isc.A.isModal=true;isc.A.title="Select Robot Server";isc.A.formConstructor="DynamicForm";isc.A.formDefaults={width:300,numCols:2,colWidths:[150,"*"],defaultItems:[{name:"robotServerURL",title:"Robot Server URL",defaultValue:"http://127.0.0.1:50080"},{name:"next",type:"button",title:"Next",click:"form.creator.nextClick()",startRow:true},{name:"cancel",type:"button",title:"Cancel",click:"form.creator.hide()",endRow:false,startRow:false}]};isc.A.myAutoChildren=["form"];isc.B.push(isc.A.initWidget=function isc_RobotServerPicker_initWidget(){this.Super("initWidget",arguments);this.form=this.createAutoChild("form");this.addItem(this.form)}
,isc.A.nextClick=function isc_RobotServerPicker_nextClick(){var _1=this.form.getValue("robotServerURL");window.robotServerURL=_1;this.hide();this.fireCallback("robotServerSelected","robotServerURL",[_1])}
);isc.B._maxIndex=isc.C+2;isc.RobotServerPicker.registerStringMethods({robotServerSelected:"robotServerURL"});isc._moduleEnd=isc._Kapow_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('Kapow module init time: ' + (isc._moduleEnd-isc._moduleStart) + 'ms','loadTime');delete isc.definingFramework;}else{if(window.isc && isc.Log && isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'Kapow'.");}
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

