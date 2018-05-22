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
else isc._preLog=[isc._pTM]}isc.definingFramework=true;isc.DataSource.create({
ID:"QuartzScheduler",
serverConstructor:"com.isomorphic.scheduler.QuartzScheduler",
fields:[
{
canEdit:false,
name:"name",
type:"text"
},
{
canEdit:false,
name:"state",
type:"intEnum",
valueMap:{
"0":"Shutdown",
"1":"Standby",
"2":"Started"
}
}
],
operationBinding:[
{
operationId:"start",
operationType:"custom"
},
{
operationId:"shutdown",
operationType:"custom"
},
{
operationId:"standby",
operationType:"custom"
},
{
operationId:"doit",
operationType:"custom",
serverObject:{
className:"com.isomorphic.scheduler.QuartzScheduler",
methodName:"doit"
}
}
]
})
isc.DataSource.create({
ID:"QuartzJobs",
serverConstructor:"com.isomorphic.scheduler.QuartzJobs",
fields:[
{
name:"group",
primaryKey:true,
required:true,
type:"string"
},
{
name:"name",
primaryKey:true,
required:true,
type:"string"
},
{
name:"description",
type:"string"
},
{
name:"className",
required:true,
type:"string"
},
{
defaultValue:"false",
name:"volatility",
type:"boolean"
},
{
defaultValue:"true",
name:"durability",
type:"boolean"
},
{
defaultValue:"true",
name:"recover",
type:"boolean"
},
{
name:"dataMap",
showIf:"false",
type:"Object"
}
]
})
isc.DataSource.create({
ID:"QuartzTriggers",
serverConstructor:"com.isomorphic.scheduler.QuartzTriggers",
fields:[
{
name:"jobGroup",
required:true,
showIf:"false",
type:"string"
},
{
name:"jobName",
required:true,
showIf:"false",
type:"string"
},
{
name:"group",
primaryKey:true,
required:true,
type:"string"
},
{
name:"name",
primaryKey:true,
required:true,
type:"string"
},
{
name:"description",
type:"string"
},
{
name:"dataMap",
showIf:"false",
type:"Object"
},
{
name:"startTime",
type:"date"
},
{
name:"endTime",
type:"date"
},
{
name:"cronExpression",
required:true,
type:"text"
},
{
name:"timeZone",
type:"text"
},
{
defaultValue:"false",
name:"volatility",
type:"boolean"
},
{
defaultValue:"0",
name:"misfireInstruction",
type:"intEnum",
valueMap:{
"0":"MISFIRE_INSTRUCTION_SMART_POLICY",
"1":"MISFIRE_INSTRUCTION_FIRE_ONCE_NOW",
"2":"MISFIRE_INSTRUCTION_DO_NOTHING"
}
},
{
canEdit:false,
name:"state",
type:"intEnum",
valueMap:{
"0":"Normal",
"1":"Paused",
"2":"Complete",
"3":"Error",
"4":"Blocked",
"-1":"None"
}
}
]
})
isc.defineClass("QuartzManager","SectionStack");isc.A=isc.QuartzManager.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.visibilityMode="multiple";isc.A.jobsPauseBtnDefaults={_constructor:"IButton",title:"Pause Job",prompt:"Suspends all triggers associated with selected job",click:function(){var _1=this.creator.jobsGrid;if(!_1.anySelected()){isc.say("Please select a job first");return}
var _2=_1.getSelectedRecord();var _3=this;QuartzJobs.performCustomOperation("pauseJob",{group:_2.group,name:_2.name},function(_4){_3.creator.triggersGrid.invalidateCache();isc.say('Job Paused')})}};isc.A.jobsResumeBtnDefaults={_constructor:"IButton",title:"Resume Job",prompt:"Resumes all triggers associated with selected job",click:function(){var _1=this.creator.jobsGrid;if(!_1.anySelected()){isc.say("Please select a job first");return}
var _2=_1.getSelectedRecord();var _3=this;QuartzJobs.performCustomOperation("resumeJob",{group:_2.group,name:_2.name},function(_4){_3.creator.triggersGrid.invalidateCache();isc.say('Job Resumed')})}};isc.A.jobsTriggerBtnDefaults={_constructor:"IButton",title:"Trigger Job",prompt:"Triggers selected job immediately",click:function(){var _1=this.creator.jobsGrid;if(!_1.anySelected()){isc.say("Please select a job first");return}
var _2=_1.getSelectedRecord();QuartzJobs.performCustomOperation("triggerJob",{group:_2.group,name:_2.name},function(_3){isc.say('Job Triggered')})}};isc.A.jobsRefreshBtnDefaults={_constructor:"ImgButton",showRollOver:false,size:16,src:"[SKIN]actions/refresh.png",prompt:"Refresh jobs",click:function(){this.creator.jobsGrid.invalidateCache();this.creator.triggersGrid.setData([])}};isc.A.jobsAddBtnDefaults={_constructor:"ImgButton",size:16,showRollOver:false,src:"[SKIN]actions/add.png",prompt:"Add job",click:"this.creator.jobsGrid.startEditingNew()"};isc.A.jobsRemoveBtnDefaults={_constructor:"ImgButton",size:16,showRollOver:false,src:"[SKIN]actions/remove.png",prompt:"Remove job",click:function(){var _1=this;isc.ask("Are you sure you wish to delete the selected job?  This will remove all"+" triggers associated with this job.",function(_2){if(_2)_1.creator.jobsGrid.removeSelectedData(function(_3){_1.creator.triggersGrid.setData([])})})}};isc.A.jobsGridDefaults={_constructor:"ListGrid",autoDraw:false,height:"30%",dataSource:"QuartzJobs",useAllDataSourceFields:true,autoFetchData:true,selectionType:"single",recordDoubleClick:function(){isc.say("The Quartz APIs do not allow modification of job metadata without destroying"+" all triggers attached to the job, so you must remove and re-create the job if"+" that's your intention");return},selectionChanged:function(_1,_2){if(_2){this.creator.triggersGrid.filterData({jobGroup:_1.group,jobName:_1.name})}else{this.creator.triggersGrid.setData([])}},remove:function(){}};isc.A.triggersPauseBtnDefaults={_constructor:"IButton",title:"Pause Trigger",prompt:"Suspends selected trigger",click:function(){var _1=this.creator.triggersGrid;if(!_1.anySelected()){isc.say("Please select a trigger first");return}
var _2=_1.getSelectedRecord();QuartzTriggers.performCustomOperation("pauseTrigger",{group:_2.group,name:_2.name},function(_3){_1.invalidateCache();isc.say('Trigger Paused')})}};isc.A.triggersResumeBtnDefaults={_constructor:"IButton",title:"Resume Trigger",prompt:"Resumes selected trigger",click:function(){var _1=this.creator.triggersGrid;if(!_1.anySelected()){isc.say("Please select a trigger first");return}
var _2=_1.getSelectedRecord();QuartzTriggers.performCustomOperation("resumeTrigger",{group:_2.group,name:_2.name},function(_3){_1.invalidateCache();isc.say('Trigger Resumed')})}};isc.A.triggersRefreshBtnDefaults={_constructor:"ImgButton",showRollOver:false,size:16,src:"[SKIN]actions/refresh.png",prompt:"Refresh jobs",click:"this.creator.triggersGrid.invalidateCache()"};isc.A.triggersAddBtnDefaults={_constructor:"ImgButton",size:16,showRollOver:false,src:"[SKIN]actions/add.png",prompt:"Add trigger",click:function(){var _1=this.creator.jobsGrid;if(!_1.anySelected()){isc.say("Please select a job first");return}
var _2=_1.getSelectedRecord();this.creator.triggersGrid.startEditingNew({jobGroup:_2.group,jobName:_2.name})}};isc.A.triggersRemoveBtnDefaults={_constructor:"ImgButton",size:16,showRollOver:false,src:"[SKIN]actions/remove.png",prompt:"Remove job",click:function(){var _1=this;isc.ask("Are you sure you wish to remove the selected trigger?",function(_2){if(_2)_1.creator.jobsGrid.removeSelectedData(function(_3){_1.creator.triggersGrid.invalidateCache()})})}};isc.A.triggersGridDefaults={_constructor:"ListGrid",canEdit:true,autoDraw:false,dataSource:"QuartzTriggers",useAllDataSourceFields:true,selectionType:"single",remove:function(){}};isc.B.push(isc.A.initWidget=function isc_QuartzManager_initWidget(){this.Super("initWidget",arguments);this.jobsPauseBtn=this.createAutoChild("jobsPauseBtn");this.jobsResumeBtn=this.createAutoChild("jobsResumeBtn");this.jobsTriggerBtn=this.createAutoChild("jobsTriggerBtn");this.jobsRefreshBtn=this.createAutoChild("jobsRefreshBtn");this.jobsAddBtn=this.createAutoChild("jobsAddBtn");this.jobsRemoveBtn=this.createAutoChild("jobsRemoveBtn");this.jobsGrid=this.createAutoChild("jobsGrid");this.addSection({title:"Jobs",expanded:true,items:[this.jobsGrid],controls:[this.jobsPauseBtn,this.jobsResumeBtn,this.jobsTriggerBtn,this.jobsRefreshBtn,this.jobsAddBtn,this.jobsRemoveBtn]});;this.triggersPauseBtn=this.createAutoChild("triggersPauseBtn");this.triggersResumeBtn=this.createAutoChild("triggersResumeBtn");this.triggersRefreshBtn=this.createAutoChild("triggersRefreshBtn");this.triggersAddBtn=this.createAutoChild("triggersAddBtn");this.triggersRemoveBtn=this.createAutoChild("triggersRemoveBtn");this.triggersGrid=this.createAutoChild("triggersGrid");this.addSection({title:"Triggers",expanded:true,items:[this.triggersGrid],controls:[this.triggersPauseBtn,this.triggersResumeBtn,this.triggersRefreshBtn,this.triggersAddBtn,this.triggersRemoveBtn]});}
);isc.B._maxIndex=isc.C+1;isc._moduleEnd=isc._SQLBrowser_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('SQLBrowser module init time: ' + (isc._moduleEnd-isc._moduleStart) + 'ms','loadTime');delete isc.definingFramework;}else{if(window.isc && isc.Log && isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'SQLBrowser'.");}
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

