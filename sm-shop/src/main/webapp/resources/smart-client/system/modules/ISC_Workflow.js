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

if(window.isc&&window.isc.module_Core&&!window.isc.module_Workflow){isc.module_Workflow=1;isc._moduleStart=isc._Workflow_start=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc._moduleEnd&&(!isc.Log||(isc.Log && isc.Log.logIsDebugEnabled('loadTime')))){isc._pTM={ message:'Workflow load/parse time: ' + (isc._moduleStart-isc._moduleEnd) + 'ms', category:'loadTime'};
if(isc.Log && isc.Log.logDebug)isc.Log.logDebug(isc._pTM.message,'loadTime')
else if(isc._preLog)isc._preLog[isc._preLog.length]=isc._pTM
else isc._preLog=[isc._pTM]}isc.definingFramework=true;isc.defineClass("ProcessElement");isc.ProcessElement.addProperties({})
isc.defineClass("ProcessSequence","ProcessElement");isc.ProcessSequence.addProperties({})
isc.defineClass("Task","ProcessElement");isc.Task.addProperties({})
isc.defineClass("Process","Task");isc.A=isc.Process.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.autoStart=false;isc.B.push(isc.A.init=function isc_Process_init(){var _1=this.Super("init",arguments);if(this.autoStart)this.start();return _1}
,isc.A.getElement=function isc_Process_getElement(_1){return this.$87g(this,_1)}
,isc.A.$87g=function isc_Process__searchElement(_1,_2){if(_1.sequences){for(var i=0;i<_1.sequences.length;i++){var s=_1.sequences[i];if(s.ID==_2){return s}else if(s.sequences||s.elements){var _5=this.$87g(s,_2);if(_5)return _5}}}
if(_1.elements){for(var i=0;i<_1.elements.length;i++){var e=_1.elements[i];if(e.ID==_2){return e}else if(e.sequences||e.elements){var _5=this.$87g(e,_2);if(_5)return _5}}}}
,isc.A.start=function isc_Process_start(){if(this.executionStack==null){this.executionStack=[]}
while(this.$39n()){var _1=this.$87h();if(_1){if(!_1.executeElement(this)){return}}}
if(this.finished)this.finished(this.state)}
,isc.A.$39n=function isc_Process__next(){var _1=this.executionStack.last();if(_1==null){if(this.startElement){return this.$87i(this,this.startElement)}else if(this.sequences&&this.sequences.length>0){this.executionStack.add({el:this,sIndex:0});return this.sequences[0]}else if(this.elements&&this.elements.length>0){this.executionStack.add({el:this,eIndex:0});return this.elements[0]}else{isc.logWarn("There are neither sequences or elements. Nothing to execute.")}}else{var _2=null;if(_1.sIndex!=null){_2=_1.el.sequences[_1.sIndex]}else if(_1.eIndex!=null){_2=_1.el.elements[_1.eIndex]}
if(_2.nextElement){this.executionStack=[];return this.$87i(this,_2.nextElement)}else{return this.$87j()}}}
,isc.A.$87i=function isc_Process__gotoElement(_1,_2){var _3={el:_1};this.executionStack.add(_3);if(_1.sequences){for(var i=0;i<_1.sequences.length;i++){var s=_1.sequences[i];_3.sIndex=i;if(s.ID==_2){return s}else if(s.sequences||s.elements){var _6=this.$87i(s,_2);if(_6)return _6}}}
delete _3.sIndex;if(_1.elements){for(var i=0;i<_1.elements.length;i++){var e=_1.elements[i];_3.eIndex=i;if(e.ID==_2){return e}else if(e.sequences||e.elements){var _6=this.$87i(e,_2);if(_6)return _6}}}
this.executionStack.removeAt(this.executionStack.length-1)}
,isc.A.$87j=function isc_Process__findNextElement(){var _1=this.executionStack.last();if(_1.eIndex!=null&&_1.el!=this){if(_1.eIndex==_1.el.elements.length-1){this.executionStack.removeAt(this.executionStack.length-1);if(_1.el==this){return}else{return this.$87j()}}else{_1.eIndex++;return _1.el.elements[_1.eIndex]}}}
,isc.A.$87h=function isc_Process__getFirstTask(){var _1=this.executionStack.last();var _2=null;if(_1.sIndex!=null){_2=_1.el.sequences[_1.sIndex]}else if(_1.eIndex!=null){_2=_1.el.elements[_1.eIndex]}
if(_2.sequences==null&&_2.elements==null){return _2}
var _3={el:_2};this.executionStack.add(_3);if(_2.sequences){for(var i=0;i<_2.sequences.length;i++){_3.sIndex=i
var _5=this.$87h(_2.sequences[i]);if(_5)return _5}}
if(_2.elements){for(var i=0;i<_2.elements.length;i++){_3.eIndex=i
var _5=this.$87h(_2.elements[i]);if(_5)return _5}}
this.executionStack.removeAt(this.executionStack.length-1)}
,isc.A.setNextElement=function isc_Process_setNextElement(_1){this.executionStack=[];this.startElement=_1}
);isc.B._maxIndex=isc.C+9;isc.Process.registerStringMethods({finished:"state"});isc.defineClass("ServiceTask","Task");isc.A=isc.ServiceTask.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.operationType="fetch";isc.B.push(isc.A.executeElement=function isc_ServiceTask_executeElement(_1){var _2=this.dataSource;if(_2.getClassName==null||_2.getClassName()!="DataSource"){_2=isc.DataSource.get(_2)}
var _3={};if(this.inputFieldList){for(var i=0;i<this.inputFieldList.length;i++){_3[this.inputFieldList[i]]=_1.state[this.inputFieldList[i]]}}
if(this.inputField){_3[this.inputField]=_1.state[this.inputField]}
var _5=null;if(this.operationType=="fetch"){if(this.criteria){_5=this.criteria;this.$87k(_5,_3)}
if(this.fixedCriteria){if(_5==null){_5=this.fixedCriteria}else{_5=isc.DataSource.combineCriteria(_5,this.fixedCriteria)}}}
if(_5==null){_5=_3}
var _6=this;_2.performDSOperation(this.operationType,_5,function(_11){if(_11.data.length>0){if(_6.outputField&&_6.outputField.startsWith("$")){var _5=_11.data;if(_5.length==1)_5=_5[0];var _7=_6.outputField.substring(1);isc.Class.evaluate("state."+_7+" = data",{state:_1.state,data:_5})}else{var _8=[];if(_6.outputFieldList){_8.addList(_6.outputFieldList)}
if(_6.outputField)_8.add(_6.outputField);for(var i=0;i<_8.length;i++){var _9=_8[i];var _10=_11.data[0][_9];if(typeof _10!='undefined'){if(_11.data.length>1){_10=[_10];for(var i=1;i<_11.data.length;i++){_10.add(_11.data[i][_9])}}
_1.state[_9]=_10}}}}
_1.start()});return false}
,isc.A.$87k=function isc_ServiceTask__processCriteriaExpressions(_1,_2){for(var _3 in _1){if(_3=="criteria"){this.$87k(_1.criteria,_2)}else if(_1[_3].startsWith("$input")){var _4="state."+_1[_3].replace("$input",this.inputField);_1[_3]=isc.Class.evaluate(_4,{state:_2})}else if(_1[_3].startsWith("$inputRecord")){var _4=_1[_3].replace("$inputRecord","state");_1[_3]=isc.Class.evaluate(_4,{state:_2})}}}
);isc.B._maxIndex=isc.C+2;isc.defineClass("ScriptTask","Task");isc.A=isc.ScriptTask.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.isAsync=false;isc.B.push(isc.A.getInputData=function isc_ScriptTask_getInputData(){return this.inputData}
,isc.A.setOutputData=function isc_ScriptTask_setOutputData(_1){this.$87l(this.process,null,_1)}
,isc.A.getInputRecord=function isc_ScriptTask_getInputRecord(){return this.inputRecord}
,isc.A.setOutputRecord=function isc_ScriptTask_setOutputRecord(_1){this.$87l(this.process,_1)}
,isc.A.executeElement=function isc_ScriptTask_executeElement(_1){var _2;var _3;if(this.inputFieldList){_3={};for(var i=0;i<this.inputFieldList.length;i++){_3[this.inputFieldList[i]]=_1.state[this.inputFieldList[i]]}}
if(this.inputField){_2=_1.state[this.inputField];if(_3){_3[this.inputField]=_2}}
this.inputData=_2;this.inputRecord=_3;this.process=_1;try{var _5=this.execute(_2,_3)}catch(e){isc.logWarn("Error while executing ScriptTask: "+e.toString())}
if(this.isAsync){return false}
if(typeof _5=='undefined'){return true}
this.$87m(_1,_5);return true}
,isc.A.$87m=function isc_ScriptTask__processTaskOutput(_1,_2){if(this.outputFieldList){for(var i=0;i<this.outputFieldList.length;i++){if(typeof _2[this.outputFieldList[i]]!='undefined'){_1.state[this.outputFieldList[i]]=_2[this.outputFieldList[i]]}}}
if(this.outputField){if(this.outputFieldList==null){if(typeof _2!='undefined'){_1.state[this.outputField]=_2}}else{if(typeof _2[this.outputField]!='undefined'){_1.state[this.outputField]=_2[this.outputField]}}}}
,isc.A.$87l=function isc_ScriptTask__finishTask(_1,_2,_3){if(_2==null){this.$87m(_1,_3)}else{if(_3){_2[this.outputField]=_3}
this.$87m(_1,_2)}
if(this.isAsync){_1.start()}}
);isc.B._maxIndex=isc.C+7;isc.ScriptTask.registerStringMethods({execute:"input,inputRecord"});isc.defineClass("XORGateway","ProcessElement");isc.A=isc.XORGateway;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.$87n=function isc_c_XORGateway__processFieldsRecursively(_1,_2){var _3=[];for(var _4 in _1){if(isc.isA.Object(_1[_4])&&!isc.isA.Array(_1[_4])&&!isc.isA.RegularExpression(_1[_4])&&!isc.isA.Date(_1[_4]))
{var _5=_2==null?_4:(_2+"."+_4);_3.addList(this.$87n(_1[_4],_5))}else{_3.add({name:(_2==null?_4:(_2+"."+_4))})}}
return _3}
);isc.B._maxIndex=isc.C+1;isc.A=isc.XORGateway.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.executeElement=function isc_XORGateway_executeElement(_1){var _2=isc.XORGateway.$87n(_1.state);var _3=isc.DataSource.create({fields:_2});if(_3.applyFilter([_1.state],this.criteria).length==1){if(this.nextElement)_1.setNextElement(this.nextElement)}else{if(this.failureElement)_1.setNextElement(this.failureElement)}
return true}
);isc.B._maxIndex=isc.C+1;isc.defineClass("DecisionGateway","ProcessElement");isc.A=isc.DecisionGateway.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.executeElement=function isc_DecisionGateway_executeElement(_1){var _2=isc.XORGateway.$87n(_1.state);var _3=isc.DataSource.create({fields:_2});for(var _4 in this.criteriaMap){if(_3.applyFilter([_1.state],this.criteriaMap[_4]).length==1){_1.setNextElement(_4);return true}}
if(this.defaultElement)_1.setNextElement(this.defaultElement);return true}
);isc.B._maxIndex=isc.C+1;isc.defineClass("UserTask","Task");isc.A=isc.UserTask.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.cancelEditing=function isc_UserTask_cancelEditing(){if(this.process){var _1=this.process
delete this.process;_1.setNextElement(this.cancelElement);_1.start()}}
,isc.A.completeEditing=function isc_UserTask_completeEditing(){if(this.process){var _1;if(this.targetVM){_1=this.targetVM.getValues()}else if(this.targetForm){_1=this.targetForm.getValues()}
var _2=this.process;delete this.process;_2.state[this.inputField]=_1;_2.start()}}
,isc.A.executeElement=function isc_UserTask_executeElement(_1){this.process=_1;if(this.targetView&&isc.isA.String(this.targetView)){this.targetView=window[this.targetView]}
if(this.targetVM&&isc.isA.String(this.targetVM)){this.targetVM=window[this.targetVM]}
if(this.targetForm&&isc.isA.String(this.targetForm)){this.targetForm=window[this.targetForm]}
if(this.targetView==null){isc.logWarn("TargetView should be set for UserTask");return true}
if(this.targetForm==null){if(this.targetView.getClassName()=="DynamicForm"){this.targetForm=this.targetView}}
if(this.targetForm==null&&this.targetVM==null){isc.logWarn("Rather targetForm or targetVM should be set for UserTask or "+"targetView should be a DynamicForm");return true}
this.targetView.showRecursively();if(this.targetVM){this.targetVM.setValues(isc.clone(_1.state[this.inputField]));this.targetVM.userTask=this}
if(this.targetForm){this.targetForm.setValues(isc.clone(_1.state[this.inputField]));this.targetForm.userTask=this}
return false}
);isc.B._maxIndex=isc.C+3;isc._moduleEnd=isc._Workflow_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('Workflow module init time: ' + (isc._moduleEnd-isc._moduleStart) + 'ms','loadTime');delete isc.definingFramework;}else{if(window.isc && isc.Log && isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'Workflow'.");}
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

