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

if(window.isc&&window.isc.module_Core&&!window.isc.module_Grids){isc.module_Grids=1;isc._moduleStart=isc._Grids_start=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc._moduleEnd&&(!isc.Log||(isc.Log && isc.Log.logIsDebugEnabled('loadTime')))){isc._pTM={ message:'Grids load/parse time: ' + (isc._moduleStart-isc._moduleEnd) + 'ms', category:'loadTime'};
if(isc.Log && isc.Log.logDebug)isc.Log.logDebug(isc._pTM.message,'loadTime')
else if(isc._preLog)isc._preLog[isc._preLog.length]=isc._pTM
else isc._preLog=[isc._pTM]}isc.definingFramework=true;isc.ClassFactory.defineInterface("List");isc.List.addInterfaceMethods({init:function(){if(!this.data)this.data=[]},first:function(){return this.get(0)},last:function(){return this.get(this.getLength()-1)},indexOf:function(_1,_2,_3){if(_2==null)_2=0;if(_3==null)_3=this.getLength()-1;for(var i=_2;i<=_3;i++){if(this.get(i)==_1)return i}
return-1},lastIndexOf:function(_1,_2,_3){if(_2==null)_2=this.getLength()-1;if(_3==null)_3=0;for(var i=_2;i>=_3;i--)
if(this.get(i)==_1)return i;return-1},findNextIndex:function(_1,_2,_3,_4){var _5=this.getLength();if(_1==null)_1=0;else if(_1>=_5)return-1;if(_4==null)_4=_5-1;if(_2==null)return-1;if(isc.isA.String(_2)){for(var i=_1;i<=_4;i++){var _7=this.get(i);if(_7&&_7[_2]==_3)return i}
return-1}else{return this.findNextMatch(_2,_1,_4)}},findAll:function(_1,_2){if(_1==null)return null;if(isc.isA.String(_1)){var _3=null,l=this.getLength();for(var i=0;i<l;i++){var _6=this.get(i);if(_6&&_6[_1]==_2){if(_3==null)_3=[];_3.add(_6)}}
return _3}else{return this.findAllMatches(_1)}},getRange:function(_1,_2){if(_2==null)_2=this.getLength()-1;var _3=[];for(var i=_1;i<_2;i++){_3[_3.length]=this.get(i)}
return _3},duplicate:function(){return this.newInstance().addList(this)},add:function(_1,_2){var _3;if(_2!==_3){return this.addAt(_1,_2)}
this.addAt(_1,this.getLength());return _1},setLength:function(_1){this.$ed();if(_1>this.getLength()){var _2;while(_1>this.getLength())this.add(_2)}else{while(_1<this.getLength())this.removeAt(this.getLength()-1)}
this.$ee()},addListAt:function(_1,_2){this.$ed();var _3=_1.getLength();for(var i=0;i<_3;i++){this.addAt(_1.get(i),_2+i)}
this.$ee();return _1},remove:function(_1){var _2=this.indexOf(_1);if(_2==-1)return false;this.$ed();var _3=this.getLength();for(var i=_2;i<_3;i++)this.set(i,this.get(i+1));this.setLength(_3-1);this.$ee();return true},removeList:function(_1){if(_1==null)return null;this.$ed();var _2=false;for(var i=0;i<this.getLength();i++){var _4=this.get(i);if(_1.contains(_4)){_2=true;this.removeAt(i);i--}}
this.$ee();return _1},sort:function(_1){var _2=this.getRange(0,this.getLength());_2.sort(_1);for(var i=0;i<_2.length;i++)this.set(i,_2[i]);return this},getProperty:function(_1){var _2=[];for(var i=0;i<this.getLength();i++){var _4=this.get(i);_2[i]=_4!=null?_4[_1]:null}
return _2},sortByProperty:function(_1,_2,_3,_4){var _5=this.getRange(0,this.getLength());_5.sortByProperty(_1,_2,_3,_4);for(var i=0;i<_5.length;i++)this.set(i,_5[i]);return this},dataChanged:function(){if(this.onDataChanged)this.onDataChanged()}});isc.$27b=function(){var _1=["isEmpty","contains","containsAll","intersect","equals","getItems","addList","getValueMap","removeEvery","$ed","$ee","$52z","getItem","setItem","removeItem","clearAll","find","findIndex","findAllIndices","findNextMatch","findAllMatches","findByKeys","size","subList","addAll","removeAll","clear"];var _2={};for(var i=0;i<_1.length;i++){var _4=_1[i];_2[_4]=Array.prototype[_4]}
isc.List.addInterfaceMethods(_2)}
isc.$27b();isc.A=isc.isA;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$27c="List";isc.B.push(isc.A.List=function isc_isA_List(_1){if(_1==null)return false;if(isc.isA.Array(_1))return true;return _1.isA&&_1.isA(this.$27c)}
);isc.B._maxIndex=isc.C+1;isc.ClassFactory.defineClass("Tree",null,"List");isc.A=isc.Tree.getPrototype();isc.A.getProperty=isc.List.getInstanceProperty("getProperty");isc.A=isc.Tree;isc.A.FOLDERS_AND_LEAVES=null;isc.A.FOLDERS_ONLY="folders";isc.A.LEAVES_ONLY="leaves";isc.A.UNLOADED=null;isc.A.LOADING="loading";isc.A.FOLDERS_LOADED="foldersLoaded";isc.A.LOADED="loaded";isc.A.PARENT="parent";isc.A.CHILDREN="children";isc.A.STRICT="strict";isc.A.KEEP_PARENTS="keepParents";isc.A.autoID=0;isc.A=isc.Tree.getPrototype();isc.A.modelType="children";isc.A.isFolderProperty="isFolder";isc.A.reportCollisions=true;isc.A.autoSetupParentLinks=true;isc.A.pathDelim="/";isc.A.treeProperty="$42c";isc.A.nameProperty="name";isc.A.titleProperty="title";isc.A.childrenProperty="children";isc.A.cacheOpenList=true;isc.A.discardParentlessNodes=false;isc.A.indexByLevel=false;isc.A.showOpenIcon=false;isc.A.showDropIcon=false;isc.A.sortDirection=Array.ASCENDING;isc.A.showRoot=false;isc.A.autoOpenRoot=true;isc.A.separateFolders=false;isc.A.sortFoldersBeforeLeaves=true;isc.A.defaultNodeTitle="Untitled";isc.A.defaultLoadState=isc.Tree.UNLOADED;isc.A=isc.Tree.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$864=["autoOpenRoot","childrenProperty","defaultIsFolder","defaultNodeTitle","discardParentlessNodes","idField","isFolderProperty","modelType","nameProperty","parentIdField","pathDelim","reportCollisions","rootValue","showRoot","titleProperty","isMultiDSTree","dataSource","operation"];isc.A.$865="openProperty";isc.A.$64z="treeLinking";isc.A.$27d=0;isc.A.loadBatchSize=50;isc.B.push(isc.A.init=function isc_Tree_init(){this.setupProperties();this.setRoot(this.root||this.makeRoot());if(this.loadOnInit&&this.loadBatchSize>=0)this.loadSubtree(null,null,true)}
,isc.A.setupProperties=function isc_Tree_setupProperties(){if(this.ID==null||window[this.ID]!=this)isc.ClassFactory.addGlobalID(this);if(!this.parentProperty)this.parentProperty="_parent_"+this.ID;if(!this.isFolderProperty)this.isFolderProperty="_isFolder_"+this.ID;if(this.idField==null)this.idField="id";if(this.parentIdField==null)this.parentIdField="parentId";if(!this.openProperty)this.openProperty="_isOpen_"+this.ID;if(this.indexByLevel)this.$76h=[]}
,isc.A.duplicate=function isc_Tree_duplicate(_1,_2){var _3=isc.Tree.create();var _4;for(var i=0;i<this.$864.length;i++){var _6=this.$864[i],_7=this[_6];if(_7!==_4)_3[_6]=_7}
var _7=this[this.$865];if(_7!==_4&&!_7.startsWith("_isOpen_"))_3[this.$865]=_7;_3.setRoot(this.getCleanNodeData(this.getRoot(),false,_2));if(_1){var _8=this.getOpenList(null,isc.Tree.FOLDERS_AND_LEAVES,null,null,null,null,true);_8=this.getCleanNodeData(_8,false,_2);_3.linkNodes(_8)}
return _3}
,isc.A.destroy=function isc_Tree_destroy(){this.destroyed=true;if(window[this.ID]==this)window[this.ID]=null}
,isc.A.makeRoot=function isc_Tree_makeRoot(){var _1={};var _2;if(this.idField!==_2)_1[this.idField]=this.rootValue;_1[this.treeProperty]=this.ID;return _1}
,isc.A.convertToFolder=function isc_Tree_convertToFolder(_1){_1[this.isFolderProperty]=true}
,isc.A.makeNode=function isc_Tree_makeNode(_1,_2){var _3=this.find(_1);if(_3){if(_2)this.convertToFolder(_3);return _3}
var _4=_1.split(this.pathDelim);var _5=this.getRoot()[this.nameProperty];if(_5.endsWith(this.pathDelim)){_5=_5.substring(0,_5.length-this.pathDelim.length)}
if(_4[0]!=_5)_4.addAt(_5,0);var _6=_4[_4.length-1],_7=(_6!=isc.emptyString);if(!_7){_4.length=_4.length-1;_6=_4[_4.length-1]}
var _8=_4.slice(0,(_4.length-1)).join(this.pathDelim)+this.pathDelim;var _9=this.find(_8);if(_9==null){_9=this.find(_8.substring(0,_8.length-this.pathDelim.length))}
if(!_9){_9=this.makeNode(_8,_2)}else if(!this.isFolder(_9)){this.convertToFolder(_9)}
var _3={};_3[this.nameProperty]=_6;if(!_7)this.convertToFolder(_3);return this.add(_3,_9)}
,isc.A.isRoot=function isc_Tree_isRoot(_1){return this.root==_1}
,isc.A.setupParentLinks=function isc_Tree_setupParentLinks(_1){if(!_1)_1=this.root;if(_1[this.idField]!=null)this.nodeIndex[_1[this.idField]]=_1;var _2=_1[this.childrenProperty];if(_2){this.setLoadState(_1,isc.Tree.LOADED);if(!isc.isAn.Array(_2)){_2=_1[this.childrenProperty]=[_2]}}
if(!_2||_2.length==0)return;for(var i=0,_4=_2.length,_5;i<_4;i++){_5=_2[i];if(!_5)continue;if(_5[this.parentIdField]==null&&_1[this.idField]!=null)
_5[this.parentIdField]=_1[this.idField];_5[this.parentProperty]=_1;this.$76i(_5,_1);if(this.isFolder(_5)){this.setupParentLinks(_5)}else if(_5[this.idField]!=null){this.nodeIndex[_5[this.idField]]=_5}}}
,isc.A.connectByParentID=function isc_Tree_connectByParentID(_1,_2,_3,_4,_5){this.linkNodes(_1,_2,_3,_4,_5)}
,isc.A.connectByParentId=function isc_Tree_connectByParentId(_1,_2,_3,_4,_5){this.linkNodes(_1,_2,_3,_4,_5)}
,isc.A.linkNodes=function isc_Tree_linkNodes(_1,_2,_3,_4,_5,_6,_7){if(this.modelType=="fields"){this.connectByFields(_1);return}
_1=_1||this.data;_2=(_2!=null)?_2:this.idField;_3=(_3!=null)?_3:this.parentIdField;_4=(_4!=null)?_4:this.rootValue;var _8=[];_8.addList(_1);var _9={};for(var i=0;i<_8.length;i++){var _11=_8[i][_2];if(_11!=null)_9[_11]=_8[i]}
for(var i=0;i<_8.length;i++){var _12=_8[i];if(this.nodeIndex[_12[_2]]==_12)continue;if(_12==null)continue;var _13=_12[_3],_14=_13!=null?_9[_13]:null,_15=[];while(_14!=null){if(_14)_15.add(_14);_13=_14[_3];_14=_13!=null&&_13!=_12[_3]?_9[_13]:null}
if(_15.length>0){for(var _16=(_15.length-1);_16>=0;_16--){if(this.logIsDebugEnabled(this.$64z)){this.logDebug("linkNodes running - adding interlinked parents to the tree in "+" reverse hierarchical order -- currently adding node with id:"+_15[_16][_2],this.$64z)}
this.$640(_15[_16],_2,_3,_6,_4);delete _9[_15[_16][_2]]}}
this.$640(_12,_2,_3,_6,_4);delete _9[_12[_2]]}
this.$736(true);if(!_7)this.dataChanged()}
,isc.A.connectByParentID=function isc_Tree_connectByParentID(_1,_2,_3,_4,_5){this.linkNodes(_1,_2,_3,_4,_5)}
,isc.A.connectByParentId=function isc_Tree_connectByParentId(_1,_2,_3,_4,_5){this.linkNodes(_1,_2,_3,_4,_5)}
,isc.A.$640=function isc_Tree__linkNode(_1,_2,_3,_4,_5){var _6=this.logIsDebugEnabled(this.$64z);var _7=_1[_2],_8=_1[_3],_9,_10=(_5==null),_11=(_8==null||_8==-1||_8===isc.emptyString),_12=this.nodeIndex[_8];if(_12){if(_6){this.logDebug("found parent "+_12[_2]+" for child "+_1[_2],this.$64z)}
this.$27e(_1,_12)}else if(!_10&&_8==_5){if(_6){this.logDebug("root node: "+_1[_2],this.$64z)}
this.$27e(_1,this.root)}else{if(!_11&&this.discardParentlessNodes){this.logWarn("Couldn't find parent: "+_8+" for node with id:"+_7,this.$64z)}else{var _13=_4||this.root;if(_6){this.logDebug("child:"+_1[_2]+(_11?" has no explicit parent ":(" unable to find specified parent:"+_8))+"- linking to default node "+_13[_2],this.$64z)}
this.$27e(_1,_13)}}}
,isc.A.connectByFields=function isc_Tree_connectByFields(_1){if(!_1)_1=this.data;for(var i=0;i<_1.length;i++){this.addNodeByFields(_1[i])}}
,isc.A.addNodeByFields=function isc_Tree_addNodeByFields(_1){var _2=this.root;for(var i=0;i<this.fieldOrder.length;i++){var _4=this.fieldOrder[i],_5=_1[_4];var _6=isc.isA.String(_5)?_5:_5+isc.emptyString,_7=this.findChildNum(_2,_6),_8;if(_7!=-1){_8=this.getChildren(_2).get(_7)}else{_8={};_8[this.nameProperty]=_6;this.add(_8,_2);this.convertToFolder(_8)}
_2=_8}
this.add(_1,_2)}
,isc.A.getRoot=function isc_Tree_getRoot(){return this.root}
,isc.A.setRoot=function isc_Tree_setRoot(_1,_2){this.root=_1;if(_1&&isc.endsWith(this.parentProperty,this.ID))_1[this.parentProperty]=null;this.root[this.treeProperty]=this.ID;if(this.rootValue==null)this.rootValue=this.root[this.idField];var _3=this.root[this.nameProperty];if(_3==null||_3==isc.emptyString)this.root[this.nameProperty]=this.pathDelim;if(!this.isFolder(this.root))this.convertToFolder(this.root);this.nodeIndex={};if("parent"==this.modelType){if(this.data)this.linkNodes()}else if("fields"==this.modelType){if(this.data)this.connectByFields()}else if("children"==this.modelType){if(this.autoSetupParentLinks)this.setupParentLinks();if(this.data){var _4=this.data;this.data=null;this.addList(_4,this.root)}}else{this.logWarn("Unsupported modelType: "+this.modelType)}
if(_2!==false&&(this.autoOpenRoot||_2)){this.openFolder(_1)}
this.setupParentLinks();this.$736();this.dataChanged()}
,isc.A.getCleanNodeData=function isc_Tree_getCleanNodeData(_1,_2,_3,_4){if(_1==null)return null;var _5=[],_6=false;if(!isc.isAn.Array(_1)){_1=[_1];_6=true}
for(var i=0;i<_1.length;i++){var _8=_1[i],_9={};for(var _10 in _8){if(_10==this.parentProperty||(!_4&&_10=="$27g")||_10=="$42c"||_10=="__ref"||_10.startsWith("_isOpen_")||_10.startsWith("_isFolder_")||_10.startsWith("$399")||_10=="$40a"||_10.startsWith("_selection_")||(_2==false&&_10==this.childrenProperty))continue;_9[_10]=_8[_10];if(_10==this.childrenProperty&&isc.isAn.Array(_9[_10])){_9[_10]=this.getCleanNodeData(_9[_10],false,_3,_4)}}
_5.add(_9)}
if(_6)return _5[0];return _5}
,isc.A.getName=function isc_Tree_getName(_1){var _2=isc.$ad;if(!_1)return _2;var _3=_1[this.nameProperty];if(_3==null)_3=_1[this.idField];if(_3==null){if(!this.isDescendantOf(_1,this.root)&&_1!=this.root)return null;if(!this.$27h)this.$27h=isc.Tree.autoID+++"_";_3=this.$27h+this.$27d++}
if(!isc.isA.String(_3))_3=_2+_3;_1[this.nameProperty]=_3;return _3}
,isc.A.getTitle=function isc_Tree_getTitle(_1){if(!_1)return null;if(_1[this.titleProperty]!=null)return _1[this.titleProperty];var _2=_1[this.nameProperty];if(_2==null)_2=this.defaultNodeTitle;return(isc.endsWith(_2,this.pathDelim)?_2.substring(0,_2.length-this.pathDelim.length):_2)}
,isc.A.getPath=function isc_Tree_getPath(_1){var _2=this.getParent(_1);if(_2==null)return this.getName(_1);var _3=this.getName(_2);return this.getPath(_2)+(_3==this.pathDelim?isc.emptyString:this.pathDelim)+this.getName(_1)}
,isc.A.getParentPath=function isc_Tree_getParentPath(_1){var _2=this.getName(_1),_3=this.getPath(_1);return _3.substring(0,_3.length-_2.length-this.pathDelim.length)}
,isc.A.getParent=function isc_Tree_getParent(_1){if(_1==null)return null;return _1[this.parentProperty]}
,isc.A.getParents=function isc_Tree_getParents(_1){var _2=[],_3=this.getParent(_1);while(_3){_2.add(_3);if(_3==this.root)break;_3=this.getParent(_3)}
return _2}
,isc.A.getLevel=function isc_Tree_getLevel(_1){return this.getParents(_1).length}
,isc.A.$59a=function isc_Tree__getFollowingSiblingLevels(_1){var _2=[],_3=this.getParents(_1),_4=_3.length;for(var i=0;i<_4;i++){var _6=this.getChildren(_3[i]);if(_6.indexOf(_1)!=_6.length-1)_2.add(_4-i);_1=_3[i]}
return _2}
,isc.A.isFolder=function isc_Tree_isFolder(_1){if(_1==null)return false;var _2=_1[this.isFolderProperty];if(_2!=null)return _2;if(_1[this.childrenProperty])return true;var _3=this.getName(_1);if(_3==null)return false;return isc.endsWith(_3,this.pathDelim)}
,isc.A.isLeaf=function isc_Tree_isLeaf(_1){return!this.isFolder(_1)}
,isc.A.isFirst=function isc_Tree_isFirst(_1){var _2=this.getParent(_1);if(!_2)return true;var _3=this.getChildren(_2,this.opendisplayNodeType,this.$27i,this.sortDirection,null,this.$45g);return _3[0]==_1}
,isc.A.isLast=function isc_Tree_isLast(_1){var _2=this.getParent(_1);if(!_2)return true;var _3=this.getChildren(_2,this.opendisplayNodeType,this.$27i,this.sortDirection,null,this.$45g);return _3[_3.length-1]==_1}
,isc.A.findById=function isc_Tree_findById(_1){return this.find(this.idField,_1)}
,isc.A.find=function isc_Tree_find(_1,_2){var _3;if(_2===_3&&isc.isA.String(_1))return this.$27j(_1);if(_2!==_3){if(_1==this.idField)return this.nodeIndex[_2];if(this.root[_1]==_2)return this.root;return this.getDescendants().find(_1,_2)}else{var _4=this.getDescendants();_4.add(this.root);return _4.find(_1)}}
,isc.A.findAll=function isc_Tree_findAll(_1,_2){return this.getDescendants().findAll(_1,_2)}
,isc.A.$27j=function isc_Tree__findByPath(_1){if(_1==this.pathDelim)return this.root;var _2=this.getPath(this.root);if(_1==_2)return this.root;var _3=this.root,_4=0,_5=this.pathDelim.length;if(isc.startsWith(_1,_2)){_4=_2.length}else if(isc.startsWith(_1,this.pathDelim)){_4+=_5}
while(true){var _6=_1.indexOf(this.pathDelim,_4);if(_6==_4){_4+=_5;continue}
var _7=(_6!=-1),_8=_1.substring(_4,_7?_6:_1.length),_9=this.findChildNum(_3,_8);if(_9==-1)return null;_3=_3[this.childrenProperty][_9];if(!_7)return _3;_4=_6+_5;if(_4==_1.length)return _3}}
,isc.A.findChildNum=function isc_Tree_findChildNum(_1,_2){var _3=this.getChildren(_1);if(_3==null)return-1;if(_2==null)return-1;var _4=_3.getLength(),_5=isc.endsWith(_2,this.pathDelim),_6=this.pathDelim.length;for(var i=0;i<_4;i++){var _8=this.getName(_3.get(i)),_9=_8.length-_2.length;if(_9==0&&_8==_2)return i;if(_9==_6){if(isc.startsWith(_8,_2)&&isc.endsWith(_8,this.pathDelim)&&!_5)
{return i}}else if(_5&&_9==-_6){if(isc.startsWith(_2,_8))return i}}
return-1}
,isc.A.getChildren=function isc_Tree_getChildren(_1,_2,_3,_4,_5,_6,_7){if(_3==null&&this.$27i==null&&this.separateFolders){this.sortByProperty()}
if(_1==null)_1=this.root;if(this.isLeaf(_1))return null;if(_1[this.childrenProperty]==null){if(_7)return null;var _8=[];_1[this.childrenProperty]=_8;return _8}
var _9=_1[this.childrenProperty],_10;if(_5){_10=[];for(var i=0,_12=_9.length;i<_12;i++){var _13=_9[i];if(this.fireCallback(_5,"node,parent,tree",[_13,_1,this]))
_10[_10.length]=_13}
_9=_10}
if(_2==isc.Tree.FOLDERS_ONLY){_10=[];for(var i=0,_12=_9.length;i<_12;i++){if(this.isFolder(_9[i]))_10[_10.length]=_9[i]}}else if(_2==isc.Tree.LEAVES_ONLY){_10=[];for(var i=0,_12=_9.length;i<_12;i++){if(this.isLeaf(_9[i]))_10[_10.length]=_9[i]}}else{_10=_9}
if(_3){if(!this.$684||this.alwaysSortGroupHeaders||(this.$684!=this.sortProp&&_1!=this.getRoot())||(this.$684==this.sortProp&&_1==this.getRoot()))
{_10.sortByProperty(this.sortProp,_4,_3,_6)}}
return _10}
,isc.A.getFolders=function isc_Tree_getFolders(_1,_2,_3,_4,_5){return this.getChildren(_1,isc.Tree.FOLDERS_ONLY,_2,_3,_4,_5)}
,isc.A.getLeaves=function isc_Tree_getLeaves(_1,_2,_3,_4,_5){return this.getChildren(_1,isc.Tree.LEAVES_ONLY,_2,_3,_4,_5)}
,isc.A.getLevelNodes=function isc_Tree_getLevelNodes(_1,_2){if(this.indexByLevel&&(_2==null||_2==this.getRoot())){return this.$76h[_1]||[]}else{if(!_2)_2=this.getRoot();var _3=this.getChildren(_2);if(_1==0)return _3;var _4=[];if(!_3)return _4;for(var i=0;i<_3.length;i++){var _6=this.getLevelNodes(_1-1,_3[i]);if(_6)_4.addList(_6)}
return _4}}
,isc.A.getDepth=function isc_Tree_getDepth(){if(this.$76h)return this.$76h.length;return null}
,isc.A.hasChildren=function isc_Tree_hasChildren(_1,_2){var _3=this.getChildren(_1,_2);return _3!=null&&_3.length>0}
,isc.A.hasFolders=function isc_Tree_hasFolders(_1){return this.hasChildren(_1,isc.Tree.FOLDERS_ONLY)}
,isc.A.hasLeaves=function isc_Tree_hasLeaves(_1){return this.hasChildren(_1,isc.Tree.LEAVES_ONLY)}
,isc.A.isDescendantOf=function isc_Tree_isDescendantOf(_1,_2){if(_1==_2)return false;var _3=_1;while(_3!=null){if(_3==_2)return true;_3=_3[this.parentProperty]}
return false}
,isc.A.getDescendants=function isc_Tree_getDescendants(_1,_2,_3){if(!_1)_1=this.root;var _4=[];if(!_3)_3=function(){return true};if(this.isLeaf(_1))return _4;var _5=this.getChildren(_1);if(!_5)return _4;for(var i=0,_7=_5.length,_8;i<_7;i++){_8=_5[i];if(this.isFolder(_8)){if(_2!=isc.Tree.LEAVES_ONLY&&_3(_8))
_4[_4.length]=_8;_4=_4.concat(this.getDescendants(_8,_2,_3))}else{if(_2!=isc.Tree.FOLDERS_ONLY&&_3(_8)){_4[_4.length]=_8}}}
return _4}
,isc.A.getDescendantFolders=function isc_Tree_getDescendantFolders(_1,_2){return this.getDescendants(_1,isc.Tree.FOLDERS_ONLY,_2)}
,isc.A.getDescendantLeaves=function isc_Tree_getDescendantLeaves(_1,_2){return this.getDescendants(_1,isc.Tree.LEAVES_ONLY,_2)}
,isc.A.dataChanged=function isc_Tree_dataChanged(){}
,isc.A.add=function isc_Tree_add(_1,_2,_3){if(_2==null&&this.modelType==isc.Tree.PARENT){var _4=_1[this.parentIdField];if(_4!=null)_2=this.findById(_4)}
if(isc.isA.String(_2)){_2=this.find(_2)}else if(!this.getParent(_2)&&_2!==this.getRoot()){isc.logWarn('Tree.add(): specified parent node:'+this.echo(_2)+' is not in the tree, returning');return null}
if(!_2){var _5=this.getParentPath(_1);if(_5)_2=this.find(_5);if(!_2)return null}
this.$27e(_1,_2,_3);this.$736(true);this.dataChanged();return _1}
,isc.A.$580=function isc_Tree__reportCollision(_1){if(this.reportCollisions){this.logWarn("Adding node to tree with id property set to:"+_1+". A node with this ID is already present in this Tree - that node will be "+"replaced. Note that this warning may be disabled by setting the "+"reportCollisions attribute to false.")}}
,isc.A.$27e=function isc_Tree__add(_1,_2,_3){var _4=_1[this.idField];if(_4!=null&&this.modelType==isc.Tree.PARENT){var _5=this.findById(_4);if(_5){this.$580(_4);this.remove(_5)}}
this.getName(_1);this.convertToFolder(_2);var _6=_2[this.childrenProperty];if(!_6)_6=_2[this.childrenProperty]=[];if(_6!=null&&!isc.isAn.Array(_6))
_2[this.childrenProperty]=_6=[_6];if(_3==null||_3>_6.length){_6.add(_1)}else{_6.addAt(_1,_3)}
var _7=this.idField
_1[this.parentIdField]=_2[_7];_1[this.parentProperty]=_2;_1[this.treeProperty]=this.ID;if(_1[_7]!=null)this.nodeIndex[_1[_7]]=_1;this.setLoadState(_2,isc.Tree.LOADED);this.$76i(_1,_2,_3)
var _8=_1[this.childrenProperty];if(_8!=null){_1[this.childrenProperty]=[];if(!isc.isAn.Array(_8))this.$27e(_8,_1);else if(_8.length>0)this.$27k(_8,_1);this.setLoadState(_1,isc.Tree.LOADED)}else{var _9=_1[this.isFolderProperty];if(_9!=null&&!isc.isA.Boolean(_9))
_9=isc.booleanValue(_9,true);if(_9==null&&this.defaultIsFolder)_9=true;_1[this.isFolderProperty]=_9}}
,isc.A.$76i=function isc_Tree__addToLevelCache(_1,_2,_3){if(!this.indexByLevel)return;var _4=this.getLevel(_2);if(!this.$76h[_4])this.$76h[_4]=[];var _5=this.$76h[_4];if(_5.length==0){if(!isc.isAn.Array(_1)){_5.push(_1)}else{_5.concat(_1)}}else{if(!isc.isAn.Array(_1)){if(_5.contains(_1))return}else{var _6=[];for(var j=0;j<_1.length;j++){if(!_5.contains(_1[j])){_6.push(_1[j])}}}
var _8=false,_9=0,i=0;for(i;i<_5.length;i++){if(this.getParent(_5[i])==_2){_8=true}else if(_8){break}else{continue}
if(_9===_3){break}
_9++}
if(!isc.isAn.Array(_1)){_5.splice(i,0,_1)}else{if(i==0){this.$76h[_4]=_6.concat(_5)}else if(i==_5.length){this.$76h[_4]=_5.concat(_6)}else{this.$76h[_4]=_5.slice(0,i).concat(_6,_5.slice(i))}}}}
,isc.A.addList=function isc_Tree_addList(_1,_2,_3){if(isc.isA.String(_2))_2=this.find(_2);if(!_2)return false;this.$27k(_1,_2,_3);this.$736(true);this.dataChanged();return _1}
,isc.A.$27k=function isc_Tree__addList(_1,_2,_3){for(var i=0,_5=_1.length;i<_5;i++){this.$27e(_1[i],_2,_3!=null?_3++:null)}}
,isc.A.move=function isc_Tree_move(_1,_2,_3){this.moveList([_1],_2,_3)}
,isc.A.moveList=function isc_Tree_moveList(_1,_2,_3){var _4=_1[0],_5=this.getParent(_4),_6=this.getChildren(_5).indexOf(_4);this.removeList(_1);if(_2==_5&&_1.length==1){if(_3>_6)_3--}else{var _7=this.getChildren(_2);if(_7&&_3>_7.length)_3=_7.length}
this.addList(_1,_2,_3);this.dataChanged()}
,isc.A.remove=function isc_Tree_remove(_1,_2){var _3=this.getParent(_1);if(!_3)return false;var _4=this.getChildren(_3);if(!_4)return false;if(_4.remove(_1)){delete this.nodeIndex[_1[this.idField]];if(!_2){this.$736(true);this.dataChanged()}
this.removeChildrenFromNodeIndex(_1);this.$76j(_1);return true}
return false}
,isc.A.removeChildrenFromNodeIndex=function isc_Tree_removeChildrenFromNodeIndex(_1){var _2=this.getChildren(_1,null,null,null,null,null,true);if(!_2)return;for(var i=0;i<_2.length;i++){this.removeChildrenFromNodeIndex(_2[i]);delete this.nodeIndex[_2[i][this.idField]]}}
,isc.A.removeList=function isc_Tree_removeList(_1){var _2=false;for(var _3=_1.length-1,i=_3;i>=0;i--){if(this.remove(_1[i],true))_2=true}
if(_2){this.$736(true);this.dataChanged()}
return _2}
,isc.A.$76j=function isc_Tree__removeFromLevelCache(_1,_2){if(!this.indexByLevel)return;_2=_2||this.getLevel(_1)-1;var _3=this.getChildren(_1);if(_3){for(var i=0;i<_3.length;i++){this.$76j(_3[i],_2+1)}}
if(this.$76h[_2]){var _5=this.$76h[_2];for(var i=0;i<_5.length;i++){if(_5[i]==_1){_5.splice(i,1);break}}}}
,isc.A.getLoadState=function isc_Tree_getLoadState(_1){if(!_1)return null;if(!_1.$27g)return this.defaultLoadState;return _1.$27g}
,isc.A.isLoaded=function isc_Tree_isLoaded(_1){var _2=this.getLoadState(_1);return(_2==isc.Tree.LOADED||_2==isc.Tree.LOADING)}
,isc.A.setLoadState=function isc_Tree_setLoadState(_1,_2){_1.$27g=_2}
,isc.A.loadRootChildren=function isc_Tree_loadRootChildren(_1){this.loadChildren(this.root,_1)}
,isc.A.loadChildren=function isc_Tree_loadChildren(_1,_2){if(!_1)_1=this.root;this.setLoadState(_1,isc.Tree.LOADED);if(_2){this.fireCallback(_2,"node",[_1],this)}}
,isc.A.unloadChildren=function isc_Tree_unloadChildren(_1,_2){if(this.isLeaf(_1))return;var _3;if(_2==isc.Tree.LEAVES_ONLY){_3=this.getLeaves(_1);_1[this.childrenProperty]=this.getFolders(_1);this.setLoadState(_1,isc.Tree.FOLDERS_LOADED)}else{_3=_1[this.childrenProperty];_1[this.childrenProperty]=[];this.setLoadState(_1,isc.Tree.UNLOADED)}
if(_3){for(var i=0;i<_3.length;i++){var _1=_3[i];delete this.nodeIndex[_1[this.idField]]}}
this.$736(true);this.dataChanged()}
,isc.A.reloadChildren=function isc_Tree_reloadChildren(_1,_2){this.unloadChildren(_1,_2);this.loadChildren(_1,_2)}
,isc.A.$736=function isc_Tree__clearNodeCache(_1){if(_1)this.$737=null;this.$27m=null}
,isc.A.isOpen=function isc_Tree_isOpen(_1){return _1!=null&&!!_1[this.openProperty]}
,isc.A.getOpenFolders=function isc_Tree_getOpenFolders(_1){if(_1==null)_1=this.root;var _2=this.getDescendantFolders(_1,new Function("node","return node."+this.openProperty));if(this.isOpen(_1))_2.add(_1);return _2}
,isc.A.getOpenFolderPaths=function isc_Tree_getOpenFolderPaths(_1){var _2=this.getOpenFolders(_1);for(var i=0;i<_2.length;i++){_2[i]=this.getPath(_2[i])}
return _2}
,isc.A.changeDataVisibility=function(node,newState,callback){if(this.isLeaf(node))return false;node[this.openProperty]=newState;this.$736();if(newState&&!this.isLoaded(node)){this.loadChildren(node,callback)}}
,isc.A.toggleFolder=function isc_Tree_toggleFolder(_1){this.changeDataVisibility(_1,!this.isOpen(_1))}
,isc.A.openFolder=function isc_Tree_openFolder(_1,_2){if(_1==null)_1=this.root;if(!this.isOpen(_1)){this.changeDataVisibility(_1,true,_2)}}
,isc.A.openFolders=function isc_Tree_openFolders(_1){for(var i=0;i<_1.length;i++){var _3=_1[i];if(_3==null)continue;if(isc.isA.String(_3))_3=this.find(_3);if(_3!=null){this.openFolder(_3)}}}
,isc.A.closeFolder=function isc_Tree_closeFolder(_1){if(this.isOpen(_1)){this.changeDataVisibility(_1,false)}}
,isc.A.closeFolders=function isc_Tree_closeFolders(_1){for(var i=0;i<_1.length;i++){var _3=_1[i];if(_3==null)continue;if(isc.isA.String(_3))_3=this.find(_3);if(_3!=null){this.closeFolder(_3)}}}
,isc.A.openAll=function isc_Tree_openAll(_1){if(!_1)_1=this.root;var _2=this.getDescendants(_1,isc.Tree.FOLDERS_ONLY);for(var i=0,_4=_2.length;i<_4;i++){if(!this.isOpen(_2[i])){this.changeDataVisibility(_2[i],true)}}
this.changeDataVisibility(_1,true)}
,isc.A.closeAll=function isc_Tree_closeAll(_1){if(!_1)_1=this.root;var _2=this.getDescendants(_1,isc.Tree.FOLDERS_ONLY);for(var i=0,_4=_2.length;i<_4;i++){if(this.isOpen(_2[i])){this.changeDataVisibility(_2[i],false)}}
if(!(_1==this.root&&this.showRoot==false))this.changeDataVisibility(_1,false)}
,isc.A.getOpenList=function isc_Tree_getOpenList(_1,_2,_3,_4,_5,_6,_7){if(!_1)_1=this.root;if(_3==null)_3=this.$27i;if(_4==null)_4=this.sortDirection;if(_6==null)_6=this.$45g;if(this.isLeaf(_1)){if(_1==this.root)return[];return null}
var _8=[];if(_2!=isc.Tree.LEAVES_ONLY)_8[_8.length]=_1;if(!_7&&!this.isOpen(_1))return _8;var _9=this.getChildren(_1,_2,_3,_4,_5,_6);for(var i=0,_11=_9.length,_12;i<_11;i++){_12=_9[i];if(!_12){continue}
var _13=_12[this.childrenProperty];if(_13&&_13.length){_8=_8.concat(this.getOpenList(_12,_2,_3,_4,_5,_6,_7))}else{if(_2!=isc.Tree.FOLDERS_ONLY){_8[_8.length]=_12}}}
if(!this.showRoot&&_8[0]==this.root){_8=_8.slice(1,_8.length)}
return _8}
,isc.A.$27l=function isc_Tree__getOpenList(){if(!this.$27m||!this.cacheOpenList){this.$27m=this.getOpenList(this.root,this.openDisplayNodeType,this.$27i,this.sortDirection,this.openListCriteria)}
return this.$27m}
,isc.A.getNodeList=function isc_Tree_getNodeList(){if(!this.$737||!this.cacheAllList){this.$737=this.getAllNodes(this.root)}
return this.$737}
,isc.A.getAllNodes=function isc_Tree_getAllNodes(_1){return this.getOpenList(_1,null,null,null,null,null,true)}
,isc.A.getLength=function isc_Tree_getLength(){return this.$27l().length}
,isc.A.get=function isc_Tree_get(_1){return this.$27l()[_1]}
,isc.A.getRange=function isc_Tree_getRange(_1,_2){return this.$27l().slice(_1,_2)}
,isc.A.indexOf=function isc_Tree_indexOf(_1,_2,_3){return this.$27l().indexOf(_1,_2,_3)}
,isc.A.lastIndexOf=function isc_Tree_lastIndexOf(_1,_2,_3){return this.$27l().lastIndexOf(_1,_2,_3)}
,isc.A.getAllItems=function isc_Tree_getAllItems(){return this.$27l()}
,isc.A.sortByProperty=function isc_Tree_sortByProperty(_1,_2,_3,_4){if(_1!=null)this.sortProp=_1;if(_2!=null)this.sortDirection=_2;if(_3&&isc.isA.Function(_3)){this.$27i=_3}else{this.$27n()}
this.$45g=_4;this.$736(true);this.dataChanged()}
,isc.A.$27n=function isc_Tree__makeOpenNormalizer(){var _1=this.sortProp,_2=this.sortDirection,_3=this.separateFolders!=false;var _4=isc.SB.create();_4.append("var __tree__ = ",this.getID(),";\rvar value = '';");_4.append("if (__tree__ == null) return;\r");if(_3){var _5,_6;if(this.sortFoldersBeforeLeaves){_5="0:";_6="1:"}else{_5="1:";_6="0:"}
_4.append("value+=(__tree__.isFolder(obj) ? '"+_5+"' : '"+_6+"');")}
if(_1&&_1!="title"){_4.append("var prop = obj['",_1,"'];","if (isc.isA.Number(prop)) {","if (prop > 0) prop = '1' + prop.stringify(12,true);","else {","prop = 999999999999 + prop;","prop = '0' + prop.stringify(12,true);","}","} else if (isc.isA.Date(prop)) prop = prop.getTime();","if (prop != null) value += prop + ':';")}
if(_1){_4.append("var title = __tree__.getTitle(obj);","if (isc.isA.Number(title)) {","if (title > 0) title = '1' + title.stringify(12,true);","else {","title = 999999999999 + prop;","title = '0' + title.stringify(12,true);","}","} else if (isc.isA.Date(title)) title = title.getTime();","if (title != null) {title = title + ''; value += title.toLowerCase()}")}
_4.append("return value;");this.addMethods({$27i:new Function("obj,property",_4.toString())})}
,isc.A.loadSubtree=function isc_Tree_loadSubtree(_1,_2,_3){if(!_1)_1=this.getRoot();if(_2==null)_2=this.loadBatchSize;this.$27o=_3?2:1;var _4=0,_5=1;while(_4<_2){var _6=this.$27p(_2,_1,_4,_5++);if(_6==0)break;_4+=_6}
this.$27o=null;if(_4>0)this.$736(true)}
,isc.A.loadingBatch=function isc_Tree_loadingBatch(_1){if(_1)return this.$27o==2;else return this.$27o}
,isc.A.$27p=function isc_Tree__loadToDepth(_1,_2,_3,_4){var _5=0;if(!this.isOpen(_2)){if(!this.isLoaded(_2))this.loadChildren(_2);if(this.isLoaded(_2)){if(this.openFolder(_2)===false)return _5}
if(_2.children){_5+=_2.children.length;_3+=_2.children.length}}
var _6=_2.children;if(_3>=_1||_4==0||_6==null)return _5;for(var i=0;i<_6.length;i++){var _8=_6[i];var _9=this.$27p(_1,_8,_3,_4-1);_5+=_9;_3+=_9;if(_3>=_1)return _5}
return _5}
,isc.A.getFilteredTree=function isc_Tree_getFilteredTree(_1,_2,_3){_2=_2||isc.Tree.STRICT;var _3=this.dataSource||_3;if(!_3){isc.logWarn("Cannot apply filter to Tree without dataSource");return null}
var _4=this.duplicate(true,true);_4.$866(_1,_2,_3,_4.getRoot());return _4}
,isc.A.$866=function isc_Tree__filterChildren(_1,_2,_3,_4){if(_4.children==null||_4.children.length==0)return false;var _5=_4.children,_6=false;if(isc.isA.String(_3))_3=isc.DS.get(_3);for(var i=_5.length-1;i>=0;i--){var _8=_5[i],_9=false;if(_8.children!=null&&_8.children.length>0){_9=this.$866(_1,_2,_3,_8)}
_6=_6||_9;if(!_9||_2==isc.Tree.STRICT){var _10=_3.applyFilter([_8],_1);if(_10!=null&&_10.length>0){_6=true}else{this.remove(_8,true)}}}
return _6}
);isc.B._maxIndex=isc.C+98;isc.A=isc.Tree;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.findChildrenProperty=function isc_c_Tree_findChildrenProperty(_1,_2){if(!isc.isAn.Object(_1))return;if(!_2)_2="any";var _3=(_2=="any"),_4=(_2=="object"),_5=(_2=="array"),_6=(_2=="objectArray");for(var _7 in _1){var _8=_1[_7];if(isc.isAn.Object(_8)){if(_3)return _7;if(isc.isAn.Array(_8)){if(isc.isAn.Object(_8[0]))return _7;if(!_4&&!_6)return _7}else{if(_4)return _7}}}}
,isc.A.discoverTree=function isc_c_Tree_discoverTree(_1,_2,_3){if(!_2)_2={};var _4=_2.childrenMode||"any";var _5=_2.scanMode||"branch";var _6=_2.tieMode||"node";var _7=_2.newChildrenProperty||isc.Tree.getInstanceProperty("childrenProperty"),_8=_2.typeProperty||"nodeType",_9=_2.nameProperty||"name";if(!isc.isAn.Array(_1))_1=[_1];var _10;if(_5=="level"||_5=="branch"){var _11={};for(var i=0;i<_1.length;i++){var _13=_1[i],_14=null;if(_13.$72r)continue;_14=this.findChildrenProperty(_13,_4);if(_14==null)continue;_11[_14]=(_11[_14]||0);_11[_14]++}
var _15=isc.getValues(_11),_16=isc.getKeys(_11);if(_16.length==0){return}else if(_16.length==1){_10=_16[0]}else if(_6=="node"){}else if(_6=="stop"){return}else{var _17=_15.max(),_18=_15.indexOf(_17);_10=_16[_18]}}
var _19=[];for(var i=0;i<_1.length;i++){var _13=_1[i];var _20=_10;if(_13.$72r)continue;if(!_20){_20=this.findChildrenProperty(_13,_4)}
if(_20==null)continue;var _21=_13[_20];if(_21!=null&&!isc.isAn.Array(_21))_21=[_21];else if(_21==null)_21=[];_13[_7]=_21;for(var j=0;j<_21.length;j++){var _23=_21[j];if(isc.isA.String(_23)){_21[j]=_23={name:_23,$72r:true}}
_23[_8]=_20}
if(_5=="level"){_19.addAll(_21)}else{this.discoverTree(_21,_2,_20)}}
if(_5=="level"&&_19.length>0)this.discoverTree(_19,_2)}
,isc.A.getCleanNodeData=function isc_c_Tree_getCleanNodeData(_1,_2,_3,_4){if(_1==null)return null;var _5=[],_6=false;if(!isc.isAn.Array(_1)){_1=[_1];_6=true}
for(var i=0;i<_1.length;i++){var _8=_1[i],_9={};if(_4==null){var _10=_8.$42c;if(_10)_4=window[_10]}
for(var _11 in _8){if((_4!=null&&_11==_4.parentProperty)||_11=="$27g"||_11=="$42c"||_11=="__ref"||_11.startsWith("_isOpen_")||_11.startsWith("_isFolder_")||_11.startsWith("$399")||_11=="$40a"||_11.startsWith("_selection_")||(_2==false&&_4&&_11==_4.childrenProperty))
{continue}
_9[_11]=_8[_11];if(_3&&_4&&_11==_4.childrenProperty&&isc.isAn.Array(_9[_11]))
{_9[_11]=_4.getCleanNodeData(_9[_11],true,true,_4)}}
_5.add(_9)}
if(_6)return _5[0];return _5}
);isc.B._maxIndex=isc.C+3;isc.ClassFactory.defineClass("Selection");isc.A=isc.Selection.getPrototype();isc.A.enabledProperty="enabled";isc.A.canSelectProperty="canSelect";isc.A.cascadeSelection=false;isc.A.$q7=true;isc.A=isc.Selection;isc.A.NONE="none";isc.A.SINGLE="single";isc.A.MULTIPLE="multiple";isc.A.SIMPLE="simple";isc.A.$24e=0;isc.A=isc.Selection.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$74f="up";isc.A.$74g="down";isc.A.selectionRangeNotLoadedMessage="Can't select that many records at once.<br><br>"+"Please try working in smaller batches.";isc.B.push(isc.A.init=function isc_Selection_init(){if(!this.selectionProperty)this.selectionProperty="_selection_"+isc.Selection.$24e++;this.partialSelectionProperty="$74h"+this.selectionProperty;this.setData((this.data?this.data:[]))}
,isc.A.destroy=function isc_Selection_destroy(){if(this.data)this.ignoreData(this.data);delete this.data}
,isc.A.setData=function isc_Selection_setData(_1){if(this.data!=null)this.ignoreData(this.data);this.data=_1;if(this.data!=null)this.observeData(this.data);this.markForRedraw()}
,isc.A.observeData=function isc_Selection_observeData(_1){this.observe(_1,"dataChanged","observer.dataChanged()");if(_1.dataArrived)this.observe(_1,"dataArrived","observer.dataChanged()")}
,isc.A.ignoreData=function isc_Selection_ignoreData(_1){if(!_1)return;if(this.isObserving(_1,"dataChanged"))this.ignore(_1,"dataChanged");if(this.isObserving(_1,"dataArrived"))this.ignore(_1,"dataArrived")}
,isc.A.dataChanged=function isc_Selection_dataChanged(){this.markForRedraw()}
,isc.A.markForRedraw=function isc_Selection_markForRedraw(){this.$q7=true}
,isc.A.isSelected=function isc_Selection_isSelected(_1){if(this.$q7){this.cacheSelection()}
if(_1==null)return false;if(isc.isAn.XMLNode(_1))return"true"==_1.getAttribute(this.selectionProperty);return!!_1[this.selectionProperty]}
,isc.A.isPartiallySelected=function isc_Selection_isPartiallySelected(_1){if(this.$q7)this.cacheSelection();if(_1==null)return false;if(isc.isAn.XMLNode(_1))return"true"==_1.getAttribute(this.partialSelectionProperty);return!!_1[this.partialSelectionProperty]}
,isc.A.anySelected=function isc_Selection_anySelected(){return this.getSelection().length>0}
,isc.A.multipleSelected=function isc_Selection_multipleSelected(){return this.getSelection().length>1}
,isc.A.getSelection=function isc_Selection_getSelection(_1){if(this.$q7){if(this.$89z)this.$89z=false;this.cacheSelection()}
var _2=this.$27q;if(_1==true&&_2!=null&&_2.length>0){var _3=this.$27q;_2=[];for(var i=0;i<_3.length;i++){var _5=_3[i];if(!this.isPartiallySelected(_5)){_2[_2.length]=_5}}}
return _2}
,isc.A.getSelectedRecord=function isc_Selection_getSelectedRecord(){var _1=this.getSelection();if(_1&&_1.length>0)return _1[0]}
,isc.A.cacheSelection=function isc_Selection_cacheSelection(){if(this.$89z||this.$890||this.$92o)return;this.$27q=[];var _1=this.getItemList(),_2=isc.isA.ResultSet!=null&&isc.isA.ResultSet(_1),_3=_1.getLength();if(_2&&!_1.lengthIsKnown()){this.$q7=false;return}
this.$89z=true;var _4=false;for(var i=0;i<_3;i++){if(_2&&!_1.rowIsLoaded(i))continue;var _6=_1.get(i);if(_6!=null&&this.isSelected(_6)){if(this.cascadeSelection&&!this.isPartiallySelected(_6)){this.setSelected(_6,true,null,true);_4=true}
if(!_4){this.$27q[this.$27q.length]=_6}}}
if(_4){this.$27q=[];for(var i=0;i<_3;i++){if(_2&&!_1.rowIsLoaded(i))continue;var _6=_1.get(i);if(_6!=null&&this.isSelected(_6)){this.$27q[this.$27q.length]=_6}}}
this.$89z=false;this.$q7=false}
,isc.A.setSelected=function isc_Selection_setSelected(_1,_2,_3,_4){if(_1==null)return false;if(this.data==null||this.data.destroyed)return false;if(_1[this.enabledProperty]==false)return false;if(_1[this.canSelectProperty]==false)return false;var _5=this.$890;this.$890=true;var _6=this.selectionProperty,_7=this.partialSelectionProperty,_8=this.data.childrenProperty||"children",_9=isc.isAn.XMLNode(_1),_10=(_9?_1.getAttribute(_7):_1[_7]);if(_2==null)_2=true;if(this.cascadeSelection&&!this.useRemoteSelection){if(_3==this.$74f){var _11=false,_12=_1[_8]?_1[_8].length:0;for(var i=0;i<_12;i++){var _14=_1[_8].get(i),_15=isc.isAn.XMLNode(_14),_16=(_15?_14.getAttribute(_7):_14[_7]);if(_16||(_2&&!this.isSelected(_14))||(!_2&&this.isSelected(_14)))
{_11=true;break}}
if(_9){_1.setAttribute(_7,_11+"")}else{_1[_7]=_11}
if(_2!=_11)_2=true}else if(_1[_8]&&_1[_8].length>0){if(_9){_1.removeAttribute(_7)}else{delete _1[_7]}}}
var _17=_9?_1.getAttribute(_6):_1[_6];if(_17==null)_17=false;if(_9){_1.setAttribute(_6,(_2==true)+"")}else{_1[_6]=_2}
this.lastSelectionItem=_1;this.lastSelectionState=_2;this.lastSelectionPreviousState=_17;this.lastSelectionPartialValue=_11;this.lastSelectionPreviousPartialValue=_10;var _18=(_9?_1.getAttribute(_7):_1[_7]);var _19=true;if(_2==_17&&_18==_10){_19=false}
if(!_4&&_19==false){if(!_5)this.$890=false;return false}
this.markForRedraw();if(_19&&this.target&&this.target.selectionChange){this.target.selectionChange(_1,_2)}
if(this.cascadeSelection&&!this.useRemoteSelection)
{var _20=_1,_21=_2,_22=_17,_23=_11,_24=_10;var _25=false;if(this.cascadeSyncOnly==null){_25=true;this.cascadeSyncOnly=!_19}
if(_3!=this.$74f&&!_9&&_1[_8]&&_1[_8].length>0)
{this.selectList(_1[_8],_2,this.$74g)}
if(_19||_25){if(_3!=this.$74g&&isc.isA.Tree(this.data)){var _26=this.data.getParent(_1);if(_26){this.setSelected(_26,_2,this.$74f)}}}
this.lastSelectionItem=_20;this.lastSelectionState=_21;this.lastSelectionPreviousState=_22;this.lastSelectionPartialValue=_23;this.lastSelectionPreviousPartialValue=_24;if(_25){this.cascadeSyncOnly=null}}
if(!_5)this.$890=false;return true}
,isc.A.select=function isc_Selection_select(_1){return this.setSelected(_1,true)}
,isc.A.deselect=function isc_Selection_deselect(_1){return this.setSelected(_1,false)}
,isc.A.selectSingle=function isc_Selection_selectSingle(_1){this.deselectAll();return this.select(_1)}
,isc.A.selectList=function isc_Selection_selectList(_1,_2){if(_2==null)_2=true;if(!_1)return false;this.cacheSelection();var _3=this.$27q;var _4=_1.getLength(),_5=[],_6=[];for(var i=0;i<_4;i++){var _8=_1.get(i),_9=this.isSelected(_8);if(_9==_2)continue;_5[_5.length]=_8;if(!_2){_6[_5.length-1]=_3.indexOf(_8)}}
var _10=this.$92o;this.$92o=true;var _11=false,_4=_5.length;for(var i=0;i<_4;i++){var _8=_5[i];if(_2){_3[_3.length]=_8}else{_3.removeAt(_6[i])}
_11=this.setSelected(_8,_2)||_11}
this.$92o=_10;this.cacheSelection();return _11}
,isc.A.deselectList=function isc_Selection_deselectList(_1){this.selectList(_1,false)}
,isc.A.selectAll=function isc_Selection_selectAll(){return this.selectRange(0,this.getItemList().getLength())}
,isc.A.deselectAll=function isc_Selection_deselectAll(){return this.deselectList(this.getSelection())}
,isc.A.selectItem=function isc_Selection_selectItem(_1){return this.selectRange(_1,_1+1)}
,isc.A.deselectItem=function isc_Selection_deselectItem(_1){return this.deselectRange(_1,_1+1)}
,isc.A.selectRange=function isc_Selection_selectRange(_1,_2,_3){if(_3==null)_3=true;var _4=this.data;if(isc.isA.ResultSet!=null&&isc.isA.ResultSet(_4)&&!_4.rangeIsLoaded(_1,_2))
{isc.warn(this.selectionRangeNotLoadedMessage);return false}
return this.selectList(_4.getRange(_1,_2),_3)}
,isc.A.deselectRange=function isc_Selection_deselectRange(_1,_2){return this.selectRange(_1,_2,false)}
,isc.A.selectOnMouseDown=function isc_Selection_selectOnMouseDown(_1,_2){var _3=_1.selectionType||isc.Selection.MULTIPLE;if(_3==isc.Selection.NONE)return false;this.startRow=this.lastRow=_2;this.logDebug("selectOnMouseDown: recordNum: "+_2);var _4=this.data.get(_2),_5=this.isSelected(_4),_6=this.getSelection();if(Array.isLoading(_4))return false;this.deselectRecordOnMouseUp=false;this.deselectOthersOnMouseUp=false;var _7=(isc.Browser.isMac?isc.EventHandler.metaKeyDown():isc.EventHandler.ctrlKeyDown()),_8=isc.EH.shiftKeyDown();if(_3==isc.Selection.SINGLE){if(_7&&_5)this.deselect(_4);else if(!_5)this.selectSingle(_4);return true}else if(_8){if(_6.length==0){this.select(_4);return true}else{var _9=this.data,_10=_9.indexOf(_6[0]),_11=_9.indexOf(_6.last());if(_2>=_11){this.selectRange(_10,_2+1)}else if(_2<=_10){this.selectRange(_2,_11+1)}else{this.selectRange(_10,_2+1);this.deselectRange(_2+1,_11+1)}
return true}}else if(_3==isc.Selection.SIMPLE){if(!_5){this.select(_4);return true}else{this.deselectRecordOnMouseUp=true;return false}}else if(_7){this.setSelected(_4,!_5);return true}else{if(!_5){this.selectSingle(_4);return true}else if(isc.EventHandler.rightButtonDown()){this.deselectOnDragMove=true;return false}else{if(this.dragSelection){if(this.simpleDeselect){this.deselectAll();this.selectOriginOnDragMove=true;return true}
this.selectSingle(_4);return true}else{if(this.simpleDeselect){this.deselectAllOnMouseUp=true}else{this.deselectOthersOnMouseUp=(_6.length>1)}
return false}}}}
,isc.A.selectOnDragMove=function isc_Selection_selectOnDragMove(_1,_2){var _3=this.startRow,_4=this.lastRow;if(_2<0){this.logWarn("selectOnDragMove: got negative coordinate: "+_2);return}
if(_2==_4)return;if(this.selectOriginOnDragMove){this.select(this.data.getItem(_3));this.selectOriginOnDragMove=false}else if(this.deselectOnDragMove||this.deselectAllOnMouseUp||this.deselectOthersOnMouseUp){this.selectSingle(this.data.getItem(_3));this.deselectAllOnMouseUp=this.deselectOthersOnMouseUp=this.deselectOnDragMove=false}
if((_2>_3&&_3>_4)||(_4>_3&&_3>_2))
{this.deselectAll();if(_3>_2){this.selectRange(_2,_3+1)}else{this.selectRange(_3,_2+1)}}else if(_3>=_4&&_4>_2){this.selectRange(_2,_4)}else if(_3>=_2&&_2>_4){this.deselectRange(_4,_2)}else if(_3<=_2&&_2<_4){this.deselectRange(_2+1,_4+1)}else if(_3<=_4&&_4<_2){this.selectRange(_4,_2+1)}else{this.logWarn("dragMove case not handled: lastRow: "+_4+", currRow: "+_2+", startRow "+_3)}
this.lastRow=_2}
,isc.A.selectOnMouseUp=function isc_Selection_selectOnMouseUp(_1,_2){if(_1.selectionType==isc.Selection.NONE)return false;this.logDebug("selectOnMouseUp: recordNum: "+_2);if(this.deselectOthersOnMouseUp){this.selectSingle(this.data.getItem(_2));this.deselectOthersOnMouseUp=false;return true}else if(this.deselectRecordOnMouseUp){this.deselect(this.data.getItem(_2));this.deselectRecordOnMouseUp=false;return true}else if(this.deselectAllOnMouseUp){this.deselectAll();this.deselectAllOnMouseUp=false;return true}else
return false}
,isc.A.getItemList=function isc_Selection_getItemList(){if(this.data&&isc.isA.Tree(this.data))return this.data.getNodeList();return(this.data?this.data:[])}
);isc.B._maxIndex=isc.C+30;isc.ClassFactory.defineClass("DetailViewer","Canvas","DataBoundComponent");isc.A=isc.DetailViewer.getPrototype();isc.A.dataFetchMode="basic";isc.A.dataArity="either";isc.A.fieldIdProperty="name";isc.A.recordsPerBlock=1;isc.A.blockSeparator="<br><br>";isc.A.showEmptyField=true;isc.A.emptyCellValue="&nbsp;";isc.A.labelPrefix="";isc.A.labelSuffix=":";isc.A.valueAlign="left";isc.A.wrapValues=true;isc.A.useInnerWidth=true;isc.A.clipValues=false;isc.A.styleName="detailViewer";isc.A.blockStyle="detailBlock";isc.A.labelStyle="detailLabel";isc.A.cellStyle="detail";isc.A.headerStyle="detailHeader";isc.A.separatorStyle="detail";isc.A.cellPadding=3;isc.A.showEmptyMessage=true;isc.A.emptyMessage="No items to display.";isc.A.emptyMessageStyle="normal";isc.A.loadingMessage="&nbsp;${loadingImage}";isc.A.loadingMessageStyle="normal";isc.A.defaultHeight=35;isc.A.showLabel=true;isc.A=isc.DetailViewer.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$g4="date";isc.B.push(isc.A.initWidget=function isc_DetailViewer_initWidget(){this.Super("initWidget",arguments);if(this.fieldState!=null)this.setFieldState(this.fieldState);else this.setFields(this.fields)}
,isc.A.setData=function isc_DetailViewer_setData(_1){if(this.data)this.ignore(this.data,"dataChanged");this.data=_1;if(this.data&&this.data.dataChanged){this.observe(this.data,"dataChanged","observer.dataChanged()")}
this.markForRedraw("new data")}
,isc.A.dataChanged=function isc_DetailViewer_dataChanged(){this.applyHilites();this.markForRedraw()}
,isc.A.getData=function isc_DetailViewer_getData(){return this.data}
,isc.A.getFields=function isc_DetailViewer_getFields(){return this.fields}
,isc.A.getInnerHTML=function isc_DetailViewer_getInnerHTML(){var _1=this.getData();if(this.fields==null||this.fields.length==0){return"Note: you must define detailViewer.fields to specify what to display!"}
if(isc.ResultSet!=null&&isc.isA.ResultSet(_1)&&!_1.lengthIsKnown()){_1.get(0);return this.loadingMessageHTML()}
if(_1==null||(_1.getLength&&_1.getLength()==0)){return this.emptyMessageHTML()}
if(!isc.isA.List(_1))_1=[_1];if(Array.isLoading(_1.get(0))&&this.isOffline()){return this.emptyMessageHTML()}
if(_1.getLength()==1||this.recordsPerBlock=="*"){return this.getBlockHTML(_1)}else{var _2=isc.StringBuffer.newInstance();for(var _3=0;_3<_1.getLength();_3+=this.recordsPerBlock){_2.append(this.getBlockHTML(_1.getRange(_3,_3+this.recordsPerBlock)),this.blockSeparator)}
return _2.toString()}}
,isc.A.getBlockHTML=function isc_DetailViewer_getBlockHTML(_1){var _2=_1.getLength();var _3="<TABLE BORDER=0 CELLSPACING=0 CLASS="+this.blockStyle+" WIDTH="+(this.useInnerWidth&&!this.isPrinting?this.getInnerWidth():"'100%'")+" CELLPADDING="+this.cellPadding+(this.clipValues?" STYLE='table-layout:fixed'":"");_3+=">";var _4=this.fields;for(var _5=0,_6=_4.length;_5<_6;_5++){var _7=_4[_5];if(!_7||_7.hidden||_7.visible==false)continue;if(_7.showIf){if(!isc.isA.Function(_7.showIf)){isc.Func.replaceWithMethod(_7,"showIf","viewer,valueList")}
if(_7.showIf(this,_1)==false)continue}
var _8=_7.type?_7.type:"";if(_8!="separator"&&_8!="header"&&!this.showEmptyField){var _9=true;for(var i=0;i<_1.getLength();i++){var _11=_1.get(i)[_7[this.fieldIdProperty]]
if(!(_11==null||_11=="")){_9=false;break}}
if(_9)continue}
if(_7.output){if(!isc.isA.Function(_7.output)){isc.Func.replaceWithMethod(_7,"output","fieldNum,field,valueList")}
_3+=_7.output(_5,_7,_1)}else{_3+=this.outputItem(_5,_7,_1)}}
_3+="</TABLE>";return _3}
,isc.A.outputItem=function isc_DetailViewer_outputItem(_1,_2,_3){var _4=(_2.type?_2.type:"value"),_5="output_"+_4,_6="";if(!this[_5])_5="output_value";_6+="<TR"+(this.rowClass!=null?" CLASS='"+this.rowClass+"'":"")+">";_6+=this[_5](_1,_2,_3);_6+="</TR>\r";return _6}
,isc.A.output_blob=function isc_DetailViewer_output_blob(_1,_2,_3){return this.output_binary(_1,_2,_3)}
,isc.A.output_upload=function isc_DetailViewer_output_upload(_1,_2,_3){return this.output_binary(_1,_2,_3)}
,isc.A.output_binary=function isc_DetailViewer_output_binary(_1,_2,_3){var _4="<TD WIDTH=10% CLASS='"+(this.isPrinting?this.printLabelStyle||this.labelStyle:this.labelStyle)+"' ALIGN=RIGHT"+(this.wrapLabel?">":" NOWRAP><NOBR>")+this.labelPrefix+(_2.title?_2.title:_2[this.fieldIdProperty])+this.labelSuffix+"<\/NOBR><\/TD>";for(var i=0;i<_3.getLength();i++){var _6=_3.get(i),_7=this.getData().indexOf(_6),_8=_2.nativeName||_2.name,_9=_6[_8+"_filename"],_10=isc.Canvas.imgHTML("[SKIN]actions/view.png",16,16,null,"style='cursor:"+isc.Canvas.HAND+"' onclick='"+this.getID()+".viewRow("+_7+")'"),_11=isc.Canvas.imgHTML("[SKIN]actions/download.png",16,16,null,"style='cursor:"+isc.Canvas.HAND+"' onclick='"+this.getID()+".downloadRow("+_7+")'"),_12=_10+"&nbsp;"+_11+"&nbsp;"+_9;_4+="<TD CLASS='"+this.getCellStyle(_12,_2,_6,this)+"'>"+_12+"<\/TD>"}
return _4}
,isc.A.viewRow=function isc_DetailViewer_viewRow(_1){isc.DS.get(this.dataSource).viewFile(this.getData().get(_1))}
,isc.A.downloadRow=function isc_DetailViewer_downloadRow(_1){isc.DS.get(this.dataSource).downloadFile(this.getData().get(_1))}
,isc.A.output_value=function isc_DetailViewer_output_value(_1,_2,_3){var _4;if(this.showLabel){_4="<TD WIDTH=10% CLASS='"+(this.isPrinting?this.printLabelStyle||this.labelStyle:this.labelStyle)+"' ALIGN=RIGHT"+(this.wrapLabel?">":" NOWRAP><NOBR>")+this.labelPrefix+(_2.title?_2.title:_2[this.fieldIdProperty])+this.labelSuffix+"<\/NOBR><\/TD>"}else{_4=""}
if(_2.valueMap&&isc.isA.String(_2.valueMap))
_2.valueMap=this.getGlobalReference(_2.valueMap);for(var i=0;i<_3.getLength();i++){var _6=_3.get(i),_7;if(_2.type=="image"){var _8=isc.Canvas.getFieldImageDimensions(_2,_6);var _9=this.getCellValue(_6,_2),_10=_2.imageURLPrefix||_2.baseURL||_2.imgDir;_7=this.imgHTML(_9,_8.width,_8.height,null,_2.extraStuff,_10,_2.activeAreaHTML)}else{_7=this.getCellValue(_6,_2)}
var _11=this.getRawValue(_6,_2);var _12;if(_2.getCellStyle){_12=_2.getCellStyle(_11,_2,_6,this)}else{_12=(this.getCellStyle(_11,_2,_6,this)||this.cellStyle)}
var _13=" style='";if(this.clipValues)_13+="overflow:hidden;";_13+="text-align:"+this.valueAlign;if(this.getCellCSSText){var _14=this.getCellCSSText(_11,_2,_6,this);if(_14!=null)_13+=isc.semi+_14}
_13+="'";_4+="<TD CLASS='"+_12+"'"+_13+(this.wrapValues?">":" NOWRAP><NOBR>")+_7+(this.wrapValues?"<\/NOBR>":"")+"<\/TD>"}
return _4}
,isc.A.getRawValue=function isc_DetailViewer_getRawValue(_1,_2){if(!_1||!_2)return null;if(_2.dataPath)return isc.Canvas.$70o(_2.dataPath,_1);return _1[_2.name]}
,isc.A.getCellCSSText=function isc_DetailViewer_getCellCSSText(_1,_2,_3,_4){return this.getRecordHiliteCSSText(_3,"",_2)}
,isc.A.getCellStyle=function isc_DetailViewer_getCellStyle(_1,_2,_3,_4){if(_2){if(_2.getCellStyle)return _2.getCellStyle(_1,_2,_3,_4);if(this.isPrinting&&_2.printCellStyle){return _2.printCellStyle}
if(_2.cellStyle){return _2.cellStyle}}
return(this.isPrinting&&this.printCellStyle!=null)?this.printCellStyle:this.cellStyle}
,isc.A.getSelectedRecord=function isc_DetailViewer_getSelectedRecord(){return this.data.get(0)}
,isc.A.getCellValue=function isc_DetailViewer_getCellValue(_1,_2){var _3=this.getRawValue(_1,_2);if(isc.isA.String(_2.formatCellValue)){_2.formatCellValue=isc.Func.expressionToFunction("value,record,field,viewer",_2.formatCellValue)}
if(_2.getCellValue!=null){if(isc.isA.String(_2.getCellValue)){_2.getCellValue=isc.Func.expressionToFunction("value,record,field,viewer",_2.getCellValue)}
_3=_2.getCellValue(_3,_1,_2,this);if(_2.formatCellValue)_3=_2.formatCellValue(_3,_1,_2,this)}else{if(_2.valueMap!=null)_3=isc.getValueForKey(_3,_2.valueMap);if(_2.formatCellValue)_3=_2.formatCellValue(_3,_1,_2,this);if(_3==null||isc.is.emptyString(_3))_3=this.emptyCellValue}
if(_2.formatCellValue==null&&this.formatCellValue){_3=this.formatCellValue(_3,_1,_2)}else{_3=this.$17c(_1,_2,_3);var _4=_2.escapeHTML;if(_4==null)_4=_2.asHTML;if(_4)_3=_3.asHTML()}
if(_2){if(_2.userFormula)_3=this.getFormulaFieldValue(_2,_1);else if(_2.userSummary)_3=this.getSummaryFieldValue(_2,_1);else if(_2.type=="imageFile"){if(_2.showFileInline!=false){if(!_1[_2[this.fieldIdProperty]+"$68c"]){var _5=isc.Canvas.getFieldImageDimensions(_2,_1),_6=this.getDataSource().streamFile(_1);_3=_1[_2[this.fieldIdProperty]+"$68c"]=this.imgHTML(_6,_5.width,_5.height)}else
_3=_1[_2[this.fieldIdProperty]+"$68c"]}else{_3=this.getViewDownloadHTML(_2,_1)}}else if(_2.showFileInline==true){this.logWarn("getCellValue(): Unsupported field-type for showFileInline: "+_2.type)}}
var _7=this.getFieldHilites(_1,_2);if(_7!=null)_3=this.applyHiliteHTML(_7,_3);return _3}
,isc.A.getViewDownloadHTML=function isc_DetailViewer_getViewDownloadHTML(_1,_2){if(_2==null)return null;var _3=_1.nativeName||_1.name,_4=_2[_3+"_filename"];if(_4==null||isc.isA.emptyString(_4))return"&nbsp;";var _5=isc.Canvas.imgHTML("[SKIN]actions/view.png",16,16,null,"style='cursor:"+isc.Canvas.HAND+"' onclick='"+this.getID()+".viewFile("+_2+","+_1+")'");var _6=isc.Canvas.imgHTML("[SKIN]actions/download.png",16,16,null,"style='cursor:"+isc.Canvas.HAND+"' onclick='alert('running');"+this.getID()+".downloadFile("+_2+","+_1+")'");return"<nobr>"+_5+"&nbsp;"+_6+"&nbsp;"+_4+"</nobr>"}
,isc.A.viewFile=function isc_DetailViewer_viewFile(_1,_2){isc.DS.get(this.dataSource).viewFile(_1,_2.name)}
,isc.A.downloadFile=function isc_DetailViewer_downloadFile(_1,_2){isc.DS.get(this.dataSource).downloadFile(_1,_2.name)}
,isc.A.$17c=function isc_DetailViewer__formatDataType(_1,_2,_3){if(isc.isA.Date(_3)){if(this.$851(_2)){var _4=isc.SimpleType.inheritsFrom(_2.type,"time");_3=isc.Time.toTime(_3,this.$30o(_2),_4)}else{if(isc.SimpleType.inheritsFrom(_2.type,"date")&&!isc.SimpleType.inheritsFrom(_2.type,"datetime"))
{_3=_3.toShortDate(this.$45i(_2),false)}else{_3=_3.toNormalDate(this.$45i(_2))}}}
if(_2.$64!=null){_3=_2.$62.normalDisplayFormatter(_3,_2,this,_1)}
return isc.iscToLocaleString(_3)}
,isc.A.$851=function isc_DetailViewer__formatAsTime(_1){if(_1==null)return false;if(_1.dateFormatter==null&&_1.timeFormatter!=null)return true;if(_1.timeFormatter==null&&_1.dateFormatter!=null)return false;return isc.SimpleType.inheritsFrom(_1.type,"time")}
,isc.A.$45i=function isc_DetailViewer__getDateFormatter(_1){if(_1.dateFormatter)return _1.dateFormatter;if(_1.displayFormat!=null&&isc.SimpleType.inheritsFrom(_1.type,"date")){return _1.displayFormat}
if(this.datetimeFormatter!=null&&isc.SimpleType.inheritsFrom(_1.type,"datetime")){return this.datetimeFormatter}
return this.dateFormatter}
,isc.A.$30o=function isc_DetailViewer__getTimeFormatter(_1){if(_1.timeFormatter)return _1.timeFormatter;if(_1.displayFormat!=null&&isc.SimpleType.inheritsFrom(_1.type,"time")){return _1.displayFormat}
return this.timeFormatter}
,isc.A.getRecordIndex=function isc_DetailViewer_getRecordIndex(_1){var _2=this.Super('getRecordIndex',arguments);if(_2==-1)_2=0;return _2}
,isc.A.output_header=function isc_DetailViewer_output_header(_1,_2,_3){return"<TD COLSPAN="+(_3.getLength()+1)+" CLASS='"+(this.isPrinting&&this.printHeaderStyle?this.printHeaderStyle:this.headerStyle)+"'>"+_2.value+"</TD>"}
,isc.A.output_separator=function isc_DetailViewer_output_separator(_1,_2,_3){var _4=(_2.width==null?_2.defaultSeparatorWidth:_2.width),_5=(_2.height==null?_2.defaultSeparatorHeight:_2.height);return"<TD COLSPAN="+(_3.getLength()+1)+" CLASS='"+this.separatorStyle+"'>"+isc.Canvas.spacerHTML(_4,_5)+"</TD>"}
,isc.A.getEmptyMessage=function isc_DetailViewer_getEmptyMessage(){return this.emptyMessage}
,isc.A.getLoadingMessage=function isc_DetailViewer_getLoadingMessage(){return this.loadingMessage==null?"&nbsp;":this.loadingMessage.evalDynamicString(this,{loadingImage:this.imgHTML(isc.Canvas.loadingImageSrc,isc.Canvas.loadingImageSize,isc.Canvas.loadingImageSize)})}
,isc.A.emptyMessageHTML=function isc_DetailViewer_emptyMessageHTML(){if(this.isOffline()){if(!this.showOfflineMessage)return"&nbsp;"}else{if(!this.showEmptyMessage)return"&nbsp;"}
return"<TABLE WIDTH=100%>"+"<TR><TD CLASS='"+this.emptyMessageStyle+"' ALIGN=CENTER><BR><BR>"+(this.isOffline()?this.offlineMessage:this.getEmptyMessage())+"<\/TD><\/TR><\/TABLE>"}
,isc.A.loadingMessageHTML=function isc_DetailViewer_loadingMessageHTML(){return"<TABLE WIDTH=100%>"+"<TR><TD CLASS='"+this.loadingMessageStyle+"' ALIGN=CENTER><BR><BR>"+this.getLoadingMessage()+"<\/TD><\/TR><\/TABLE>"}
,isc.A.setFieldState=function isc_DetailViewer_setFieldState(_1){if(_1==null&&this.fieldState!=null){if(isc.isA.String(this.fieldState)){_1=this.evalViewState(this.fieldState,"fieldState")}}else _1=this.evalViewState(_1,"fieldState");this.completeFields=this.$31y(_1,true);this.setFields(this.completeFields);this.markForRedraw();this.fieldStateChanged()}
,isc.A.setFields=function isc_DetailViewer_setFields(_1){if(this.completeFields==null||this.fields==null)this.fields=[];this.completeFields=this.bindToDataSource(_1);if(this.completeFields==null)this.completeFields=[];this.deriveVisibleFields()}
,isc.A.deriveVisibleFields=function isc_DetailViewer_deriveVisibleFields(){this.fields.setArray(this.getVisibleFields(this.completeFields))}
,isc.A.getVisibleFields=function isc_DetailViewer_getVisibleFields(_1){var _2=_1.duplicate();for(var i=0;i<_1.length;i++){var _4=_1.get(i);if(!this.fieldShouldBeVisible(_4)||_4.visible==false)_2.remove(_4)}
return _2}
,isc.A.getTitleFieldValue=function isc_DetailViewer_getTitleFieldValue(_1){var _2=this.getDataSource().getTitleField(),_3=this.getCellValue(_1,this.getDataSource().getField(_2));return _3}
,isc.A.getStandaloneFieldValue=function isc_DetailViewer_getStandaloneFieldValue(_1,_2){var _3=this.getCellValue(_1,this.getField(_2));return _3}
,isc.A.hideField=function isc_DetailViewer_hideField(_1){this.toggleField(_1,false)}
,isc.A.showField=function isc_DetailViewer_showField(_1){this.toggleField(_1,true)}
,isc.A.toggleField=function isc_DetailViewer_toggleField(_1,_2){var _3=this.getField(_1);_3.showIf=_2?"true":"false";_3.visible=_2;this.setFields(this.getAllFields());this.markForRedraw();this.fieldStateChanged()}
,isc.A.getField=function isc_DetailViewer_getField(_1){var _2=this.getAllFields(),_3=this.fields,_4;if(isc.isAn.Object(_1)&&_1[this.fieldIdProperty]!=null){_4=_2.find(this.fieldIdProperty,_1[this.fieldIdProperty])||_3.find(this.fieldIdProperty,_1[this.fieldIdProperty])}else if(isc.isA.Number(_1)){_4=_2[_1]||_3[_1]}else{_4=_2.find(this.fieldIdProperty,_1)||_3.find(this.fieldIdProperty,_1)}
return _4}
,isc.A.getFormattedValue=function isc_DetailViewer_getFormattedValue(_1,_2,_3){return this.getCellValue(_1,this.getSpecifiedField(_2))}
,isc.A.getPivotedExportData=function isc_DetailViewer_getPivotedExportData(_1){var _2=[],_3=this.getAllFields(),_4=this.data,_5,_6,_7;if(isc.isA.Object(_1)){_5=_1.includeHiddenFields;_6=_1.allowedProperties;_7=_1.alwaysExportExpandedStyles}
if(isc.isA.ResultSet(_4))_4=_4.getAllLoadedRows();if(!isc.isA.Array(_4))_4=[_4];for(var _8=0;_8<_3.length;_8++){var _9=_3[_8],_10={},_11=this.recordsPerBlock;_10.title=_9.title||_9.name;if(isc.isA.String(_10.title))_10.title=this.htmlUnescapeExportFieldTitle(_10.title);if(_11==null)_11=1;if(_11=="*")_11=100000;if((!this.fields.contains(_9))&&!_5)continue;for(var _12=0;_12<_11&&_12<_4.getLength();_12++)
{var _13=_4[_12],_14=this.getFieldNum(_9.name),_15="value"+(_12+1),_16=_15+"$style";var _17=this.getExportFieldValue(_13,_9.name,_14);if(!(_17==null||_17=="&nbsp;"))_10[_15]=_17;this.addDetailedExportFieldValue(_10,_16,_13,_9,_14,_6,_7);if(_10[_16]==null||_10[_16]=="&nbsp;")
delete _10[_16]}
_2.push(_10)}
return _2}
);isc.B._maxIndex=isc.C+45;isc.DetailViewer.registerStringMethods({getCellValue:"record,field",getCellStyle:"value,field,record,viewer",getCellCSSText:"value,field,record,viewer",formatCellValue:"value,record,field,viewer",fieldStateChanged:""});isc.ClassFactory.defineClass("GridRenderer","Canvas");isc.A=isc.GridRenderer;isc.A.SELECTED="Selected";isc.A.DISABLED="Disabled";isc.A.OVER="Over";isc.A.standardStyleSuffixes=["","Over","Selected","SelectedOver","Disabled","DisabledOver","DisabledSelected","DisabledSelectedOver","Dark","OverDark","SelectedDark","SelectedOverDark","DisabledDark"];isc.A=isc.GridRenderer.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.totalRows=0;isc.A.drawAllMaxCells=250;isc.A.recordCanSelectProperty="canSelect";isc.A.isSeparatorProperty="isSeparator";isc.A.singleCellValueProperty="singleCellValue";isc.A.instantScrollTrackRedraw=true;isc.A.scrollRedrawDelay=75;isc.A.drawAheadRatio=1.3;isc.A.quickDrawAheadRatio=1.0;isc.A.cellHeight=20;isc.A.fixedRowHeights=true;isc.A.fixedColumnWidths=true;isc.A.cellSpacing=0;isc.A.cellPadding=2;isc.A.canSelectOnRightMouse=true;isc.A.hoverByCell=true;isc.A.backgroundColor="white";isc.A.tableStyle="listTable";isc.A.baseStyle="cell";isc.A.alternateRowFrequency=1;isc.A.emptyCellValue="&nbsp;";isc.A.fastCellUpdates=true;isc.A.overflow="auto";isc.A.$r9=true;isc.A.canFocus=true;isc.A.animateRowsMaxTime=1000;isc.A.snapToCells=false;isc.A.snapInsideBorder=false;isc.A.snapHDirection=isc.Canvas.BEFORE;isc.A.snapVDirection=isc.Canvas.BEFORE;isc.B.push(isc.A.setFastCellUpdates=function isc_GridRenderer_setFastCellUpdates(_1){if(_1&&!isc.Browser.isIE){this.fastCellUpdates=false;return}
if(_1==this.fastCellUpdates)return;this.fastCellUpdates=_1;this.markForRedraw()}
);isc.B._maxIndex=isc.C+1;isc.A=isc.GridRenderer.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$57k=["<DIV style='position:"+(isc.Page.isRTL()?"inline":"relative")+";z-index:",,";'>",,"</DIV>"];isc.A.$623="<table style='position:absolute;top:0px;font-size:1px;height:100%;width:100%;z-index:1;overflow:hidden;visibility:hidden;'><tr><td>&nbsp;</td></tr></table>";isc.A.maxAnimateSlideInRows=100;isc.A.$27r="none";isc.A.screenReader_suppressHandleFocus=true;isc.A.$77c={};isc.A.$27s="<DIV role='presentation' cellClipDiv=true style='overflow:hidden;";isc.A.$80q="<div>";isc.A.$80r="</div>";isc.A.$57l="within";isc.A.$57m="expand";isc.A.$14k="cell";isc.A.alignSnapToMap={left:{top:"TL",center:"L",bottom:"BL"},right:{top:"TR",center:"R",bottom:"BR"},center:{top:"T",center:"C",bottom:"B"}};isc.A.recordCustomStyleProperty="customStyle";isc.A.showSelectedStyle=true;isc.A.$27t="NOBR";isc.A.$27u="cellClipDiv";isc.A.$o5="height";isc.A.$27v="minHeight";isc.A.$39=";";isc.A.$721="padding-top:0px;padding-bottom:0px;";isc.A.$722="overflow:hidden;";isc.A.avgRowHeight=60;isc.B.push(isc.A.initWidget=function isc_GridRenderer_initWidget(){if(!this.$26a)this.setColumnWidths([]);if(this.selection)this.setSelection(this.selection);if(this.overflow==isc.Canvas.VISIBLE){this.showAllRows=true}
if(!this.fixedRowHeights&&this.virtualScrolling==null)this.virtualScrolling=true;if(!this.fixedRowHeights&&!this.showAllRows){if(this.showCustomScrollbars==false){this.logInfo("Variable height records cannot be used with native scrollbars;"+" setting showCustomScrollbars:true on this GridRenderer and using"+" the special 'NativeScrollbar' class as a scrollbarConstructor.");this.showCustomScrollbars=true;this.scrollbarConstructor="NativeScrollbar"}}
this.setFastCellUpdates(this.fastCellUpdates)}
,isc.A.shouldShowAllColumns=function isc_GridRenderer_shouldShowAllColumns(){if(this.showAllColumns)return true;if(!this.fixedRowHeights&&!this.showAllRows)return true;if(this.overflow==isc.Canvas.VISIBLE){return true}
return false}
,isc.A.isEmpty=function isc_GridRenderer_isEmpty(){return false}
,isc.A.$27w=function isc_GridRenderer__showEmptyMessage(_1,_2){return this.getEmptyMessageHTML(_1,_2,this.grid.isOffline())}
,isc.A.getEmptyMessageHTML=function isc_GridRenderer_getEmptyMessageHTML(_1,_2,_3){if(!_3){if(!this.showEmptyMessage)return"&nbsp;"}else{if(!this.showOfflineMessage)return"&nbsp;"}
if(this.isPrinting){if(_1==null)_1=0;if(_2==null)_2=this.fields?this.fields.getLength()-1:0;return"<TABLE role='presentation' cellspacing=0 style='width:100%'"+(this.emptyMessageTableStyle?(" class='"+this.emptyMessageTableStyle+"'"):"")+">"+this.grid.getPrintHeaders(_1,_2)+"<TR><TD  ALIGN=CENTER VALIGN=TOP class='"+(_3?this.offlineMessageStyle:this.emptyMessageStyle)+"' colspan='"+((_2-_1)+1)+"'>"+(_3?this.getOfflineMessage():this.getEmptyMessage())+"</TD></TR></TABLE>"}
var _4=this.getInnerWidth(),_5=0;if(this.expandEmptyMessageToMatchFields&&this.$26a){_5=this.$26a.sum()-_4;if(_5<0)_5=0}
var _6=_5&&this.overflow!=isc.Canvas.VISIBLE;var _7=isc.StringBuffer.create();_7.append("<TABLE role='presentation' BORDER=0 MARGIN=0 CELLSPACING=0",(this.emptyMessageTableStyle?(" CLASS='"+this.emptyMessageTableStyle+"'"):"")," style='width:",(_4+_5),"px;",(isc.Browser.isSafari?"height"+this.getInnerHeight()+":px;'":"' HEIGHT=100%"),"><TR><TD ALIGN=CENTER VALIGN=TOP CLASS='",(_3?this.offlineMessageStyle:this.emptyMessageStyle),"' style='padding-left:0px;padding-right:0px;'>",(_3?this.getOfflineMessage():this.getEmptyMessage()),(_5&&_6?"<br>"+isc.Canvas.spacerHTML(_4,1):null),"</TD>");if(_5&&_6){_7.append("<TD style='padding-left:0px;padding-right:0px;'>",isc.Canvas.spacerHTML(_5,1),"</TD>")}
_7.append("</TR></TABLE>");return _7.release()}
,isc.A.getEmptyMessage=function isc_GridRenderer_getEmptyMessage(){return this.emptyMessage}
,isc.A.getOfflineMessage=function isc_GridRenderer_getOfflineMessage(){return this.grid.offlineMessage}
,isc.A.getInnerHTML=function isc_GridRenderer_getInnerHTML(){var _1=this.getTableHTML(),_2=this.$57k;_2[1]=this.getTableZIndex();_2[3]=_1;if(isc.Browser.isMoz)_2[5]=this.$623;return _2.join(isc.emptyString)}
,isc.A.isFastScrolling=function isc_GridRenderer_isFastScrolling(){return this.isDragScrolling()||this.isRepeatTrackScrolling()}
,isc.A.shouldUseQuickDrawAheadRatio=function isc_GridRenderer_shouldUseQuickDrawAheadRatio(){return this.useQuickDrawAheadRatio||this.isFastScrolling()}
,isc.A.doneFastScrolling=function isc_GridRenderer_doneFastScrolling(){var _1=this.$50z;if(_1){this.$50v=true;this.markForRedraw("Done Fast scrolling.")}}
,isc.A.addDrawAhead=function isc_GridRenderer_addDrawAhead(_1,_2,_3,_4,_5){var _6=this.shouldUseQuickDrawAheadRatio(),_7=_6&&this.quickDrawAheadRatio!=null?this.quickDrawAheadRatio:this.drawAheadRatio,_8=Math.ceil((_2-_1)*_7);if(this.$50v)_4=null;if(_4!=null){if(_4)_2=_1+_8;else _1=_2-_8}else{if(_1==0)_2=_8;else{var _9=Math.ceil((_8-(_2-_1))/2);_1-=_9;_2+=_9}}
if(_1<0){_2-=_1;_1=0}
if(_2>=_3){var _10=_2-(_3-1);_1=Math.max(0,(_1-_10));_2=Math.max(0,_3-1)}
if(_6)this.$50z=true;else delete this.$50z;return[_1,_2]}
,isc.A.getExtraRowHeight=function isc_GridRenderer_getExtraRowHeight(_1,_2){var _3=0;for(var _4=_1;_4<_2;_4++){var _5=this.getRowHeight(this.getCellRecord(_4,0),_4),_6=(_5-this.cellHeight);if(_6>0){_3+=_6}}
return _3}
,isc.A.getDrawArea=function isc_GridRenderer_getDrawArea(_1){var _2=this.getTotalRows(),_3,_4,_5;var _6=_2*this.fields.length,_7=_6<=this.drawAllMaxCells&&!isc.EH.dragging&&!this.isAnimating()&&!(this.parentElement&&this.parentElement.isAnimating());if(this.showAllRows||_7){_3=0;_4=Math.max(_2-1,0)}else{var _8=this.$80e();_3=_8[0];_4=_8[1];_5=_8[2]}
var _9,_10,_11=this.fields.length,_12;if(_1!=null){_9=_1;_10=_1+1}else if(_7||this.shouldShowAllColumns()){_9=0;_10=_11-1}else{var _13=this.getVisibleColumns();_12=(this.lastScrollLeft==null?null:this.lastScrollLeft<this.getScrollLeft());var _14=this.addDrawAhead(_13[0],_13[1],_11,_12);_9=_14[0];_10=_14[1]}
if(this.cacheDOM&&!this.$508){this.$508=_4-_3;this.$509=_10-_9}
return[_3,_4,_9,_10]}
,isc.A.$80e=function isc_GridRenderer__getDrawRows(){var _1=this.$27x();var _2=(this.lastScrollTop==null?null:this.lastScrollTop<this.getScrollTop());var _3=this.getTotalRows();var _4=this.addDrawAhead(_1[0],_1[1],_3,_2,true);_4[2]=_2;return _4}
,isc.A.getRowCoordinate=function isc_GridRenderer_getRowCoordinate(_1){var _2=this.getEventRow(_1),_3=this.getRowTop(_2),_4=_1-_3,_5=this.getRowSize(_2),_6=_4/ _5;return _2+_6}
,isc.A.scrollToRatio=function isc_GridRenderer_scrollToRatio(_1,_2,_3,_4,_5){if(!_1||!this.$60s){return this.invokeSuper(isc.GridRenderer,"scrollToRatio",_1,_2,_3,_4,_5)}
var _6=this.getTotalRows()-1,_7=_2*_6,_8=Math.floor(_7),_9=Math.round((_7-_8)*this.getRowSize(_8));this.$27y=_8;this.$27z=_9;this.$270(_3||"scrollToRatio");if(this.isDirty()){this.$271=_2;this.$27y=_8;this.$27z=_9}}
,isc.A.getScrollRatio=function isc_GridRenderer_getScrollRatio(_1,_2,_3,_4){if(!_1||!this.$60s){return this.invokeSuper(isc.GridRenderer,"getScrollRatio",_1,_2,_3,_4)}
if(this.isDirty()&&this.$271!=null)return this.$271;var _5=this.getTotalRows()-1;if(_5<=0)return 0;var _6=this.getScrollTop(),_7=this.getRowCoordinate(_6),_8=_7/ _5;return Math.min(1,_8)}
,isc.A.getViewportRatio=function isc_GridRenderer_getViewportRatio(_1,_2,_3,_4){if(!_1||!this.$60s){return this.invokeSuper(isc.GridRenderer,"getViewportRatio",_1,_2,_3,_4)}
var _5=this.$272||this.getAvgRowHeight();return Math.min(1,(this.getViewportHeight()/_5)/this.getTotalRows())}
,isc.A.$273=function isc_GridRenderer__storeTargetRow(_1,_2){if(this.$274)return;if(this.isEmpty())return;var _3,_4,_5=this.getTotalRows()-1;if(_2>0){_3=_1+this.getViewportHeight();_4=this.getEventRow(_3);if(_4==-2&&_5>=0){_4=_5}}else{_3=_1;_4=this.getEventRow(_3)}
var _6=_1;if(_4<0||_4>_5){this.$27y=_5;this.$27z=0;_6=this.getRowTop(_5)}else{this.$27y=_4;this.$27z=_1-this.getRowTop(this.$27y)+_2;if(Math.abs(this.$27z)>this.getViewportHeight()){this.logInfo("storeTargetRow: targetRow: "+_4+" with offset: "+this.$27z+", clearing","virtualScrolling");this.$27z=this.$27y=null}}
return _6}
,isc.A.$270=function isc_GridRenderer__scrollToTargetRow(_1){var _2=this.$27y,_3=this.$27z;var _4=this.getRowTop(_2)+_3;this.$274=true;this.$sz=null;this.scrollTo(null,_4,_1||"targetRow");this.$274=false;this.$271=null}
,isc.A.scrollIntoView=function isc_GridRenderer_scrollIntoView(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10){if((_10!=null)&&(_10!=this)){return}
this.invokeSuper(isc.GridRenderer,"scrollIntoView",_1,_2,_3,_4,_5,_6,_7,_8,_9,_10)}
,isc.A.scrollTo=function isc_GridRenderer_scrollTo(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;if(this.$60s&&_2!=null&&_3!="nativeScroll"){var _5=this.getScrollTop(),_6=_2-_5;if(_6!=0){this.$273(_5,_6);_2=Math.min(_2,this.getRowTop(this.getTotalRows()-1))}}
this.invokeSuper(isc.GridRenderer,"scrollTo",_1,_2,_3,_4);if(this.isDirty()||this.$80f)return;var _7=(this.$276()||this.$277());if(_7){if(!this.isFastScrolling()&&this.instantScrollTrackRedraw){this.redraw("scrolled")}else if(this.scrollRedrawDelay==0){this.markForRedraw("scrolled")}else{var _8=this;this.fireOnPause("scrollRedraw",function(){_8.markForRedraw("scrolled")},this.scrollRedrawDelay)}
this.$250=true}}
,isc.A.$276=function isc_GridRenderer__needRowRedraw(){if(this.showAllRows)return false;var _1=this.getVisibleRows(),_2=_1[0],_3=_1[1];var _4=this.getTotalRows();if(_3>_4-1)_3=_4-1;var _5=(_2<this.$252||_3>this.$253);return _5}
,isc.A.$277=function isc_GridRenderer__needColumnRedraw(){if(this.shouldShowAllColumns())return false;var _1=this.getVisibleColumns(),_2=_1[0],_3=_1[1],_4=(_2<this.$254||_3>this.$255);return _4}
,isc.A.setOverflow=function isc_GridRenderer_setOverflow(_1){if(_1==isc.Canvas.VISIBLE){this.showAllRows=true}
return this.Super("setOverflow",arguments)}
,isc.A.getRowChunkNum=function isc_GridRenderer_getRowChunkNum(_1){return Math.round(_1/ this.$508)}
,isc.A.getColChunkNum=function isc_GridRenderer_getColChunkNum(_1){return Math.round(_1/ this.$509)}
,isc.A.getTableChunk=function isc_GridRenderer_getTableChunk(_1,_2){var _3=this.$51a;if(!_3)return;_1=_1||0;_2=_2||0;var _4=_3[_1];return _4?_4[_2]:null}
,isc.A.getTableChunkAt=function isc_GridRenderer_getTableChunkAt(_1,_2){var _3=this.getRowChunkNum(_1),_4=this.getColChunkNum(_2),_5=this.getTableChunk(_3,_4);if(_5!=null){this.$51b=_3*this.$508;this.$254=_4*this.$509;return _5}}
,isc.A.$rd=function isc_GridRenderer__updateInnerHTML(_1,_2,_3,_4){if(this.cacheDOM){this.drawVisibleChunks()}else{this.$29j();this.$756=true;this.invokeSuper(isc.GridRenderer,"$rd",_1,_2,_3,_4);delete this.$756}}
,isc.A.$p9=function isc_GridRenderer__updateParentHTML(_1,_2,_3,_4){this.$29j();this.$756=true;this.invokeSuper(isc.GridRenderer,"$p9",_1,_2,_3,_4);delete this.$756}
,isc.A.drawVisibleChunks=function isc_GridRenderer_drawVisibleChunks(){var _1=this.getVisibleRows(),_2=this.getVisibleColumns(),_3=this.getRowChunkNum(_1[0]),_4=this.getColChunkNum(_2[0]),_5=this.getRowChunkNum(_1[1]),_6=this.getColChunkNum(_2[1]);for(var _7=_3;_7<_5;_7++){for(var _8=_4;_8<_6;_8++){if(this.getTableChunk(_7,_8)==null){this.logWarn("drawing chunk: "+[_7,_8]);this.renderTableChunk(_7,_8)}}}
var _9=this.getTableHTML()}
,isc.A.renderTableChunk=function isc_GridRenderer_renderTableChunk(_1,_2){var _3=_1*this.$508,_4=_3+this.$508,_5=_2*this.$509,_6=_5+this.$509;var _7=this.getTableHTML([_5,_6],_3,_4),_8=isc.Element.insertAdjacentHTML(this.getHandle(),"beforeEnd",_7,true);var _9=this.$51a=this.$51a||[],_10=_9[_1]=_9[_1]||[];_10[_2]=_8}
,isc.A.getDrawnRows=function isc_GridRenderer_getDrawnRows(){return this.getVisibleRows()}
,isc.A.startRowAnimation=function isc_GridRenderer_startRowAnimation(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10){this.finishRowAnimation();if(!this.isDrawn()||!this.isVisible()){if(_4!=null){var _11=_9?this.parentElement:this;_11.fireCallback(_4)}
return}
if(_1==null)_1=true;if(_2==null)_2=0;if(_3==null)_3=this.getTotalRows()-1;if(_2==_3){this.logWarn("startRowAnimation passed empty row range, aborting: "+[_2,_3]);return}
var _12=this.readyToRedraw("animating show / hide of rows",false);if(!_12){this.$278=[_1,_2,_3,_4,_5,_6,_7,_8,_9];this.$279=isc.Timer.setTimeout({target:this,methodName:"$28a"},0);return}
if((_3-_2)>this.maxAnimateSlideInRows)_8=false;this.$73q=_8;var _13=this.$28b(_1,_2,_3,_4,_9);this.animateRowHeight(this.$28c,(_1?_13:0),{target:this,methodName:"$28d"},_5,_6,_7,_8)}
,isc.A.$28a=function isc_GridRenderer__delayedStartRowAnimation(){if(this.$278==null){this.logWarn("Unable to perform delayed row animation - bailing");return}
var _1=this.$278,_2=_1[0],_3=_1[1],_4=_1[2],_5=_1[3],_6=_1[4],_7=_1[5],_8=_1[6],_9=_1[7],_10=_1[8];this.$278=null;this.$279=null;this.startRowAnimation(_2,_3,_4,_5,_6,_7,_8,_9,_10,true)}
,isc.A.$28b=function isc_GridRenderer__initializeShowHideRow(_1,_2,_3,_4,_5){var _6=0;if(this.$28c==_2&&this.$28e==_3){var _7=this.getTableElement(this.$28c,0),_8=this.$28g(_7);if(!_8){_6=(_3-_2)*this.cellHeight}else _6=_8.scrollHeight}else{this.$28c=_2;this.$28e=_3;if(!_1){var _9=this.$26b();for(var i=_2;i<_3;i++){_6+=_9[i]}
this.$28f=_6;this.redraw("initializing animated hide row")}else{this.$28f=1;this.redraw("initializing animated show row");var _7=this.getTableElement(this.$28c,0),_8=this.$28g(_7);if(!_8){_6=(_3-_2)*this.cellHeight}else _6=_8.scrollHeight}
if(this.isDirty())this.redraw("Initializing row animation requires second redraw")}
this.$28h={callback:_4,target:(_5?this.parentElement:this)};return _6}
,isc.A.finishRowAnimation=function isc_GridRenderer_finishRowAnimation(){if(this.$28c!=null){this.finishAnimateRowHeight()}else{if(this.$279!=null){isc.Timer.clearTimeout(this.$279);var _1=this.$278,_2=_1[0],_3=_1[1],_4=_1[2],_5=_1[3],_6=_1[4],_7=_1[5];delete this.$278;delete this.$279;if(!this.readyToRedraw()){this.logWarn("Finish row animation called while Grid is not ready to redraw. "+"GridRenderer HTML will not be updated when callback fires.","animation");var _8=_7?this.parentElement:this;if(_5)_8.fireCallback(_5)}else{var _9=this.$28b(_2,_3,_4,_5,_7);this.setRowHeight(_3,(_2?_9:1));this.$28d()}}}}
,isc.A.$28d=function isc_GridRenderer__rowShowComplete(){var _1=this.$28h;delete this.$28h;delete this.$28c;delete this.$28e;delete this.$28f;if(_1&&_1.callback)_1.target.fireCallback(_1.callback)}
,isc.A.animateRowHeight=function isc_GridRenderer_animateRowHeight(_1,_2,_3,_4,_5,_6,_7,_8){if(!this.isDrawn()){if(_3){var _9=(_8?this.parentElement:this);_9.fireCallback(_3)}
return}
if(this.$28i!=null){this.logInfo("early finish of row animation, because new animation started","animation")
this.finishAnimateRowHeight()}
var _10=this.getRowSize(_1);if(_4!=null){var _11=(_2-_10);if(_11<0)_11=0-_11;_5=Math.round((_11/ _4)*1000);if(_5>this.animateRowsMaxTime)_5=this.animateRowsMaxTime}
this.$28j={$24t:_1,$28k:_10,$28l:_2,$03:_3,$1n:_7,$28m:_8}
_6=(_6||this.$27r);if(this.logIsInfoEnabled("animation")){this.logInfo("starting row animation, duration: "+_5+", effect: "+_6,"animation")}
this.$28i=this.registerAnimation({target:this,method:this.$28n},_5,_6);if(this.overflow==isc.Canvas.AUTO||this.overflow==isc.Canvas.SCROLL)
this.$417=true}
,isc.A.$28n=function isc_GridRenderer__fireRowAnimation(_1){var _2=this.$28j,_3=_2.$24t,_4=this.$04(_2.$28k,_2.$28l,_1);if(isc.Browser.isSafari&&_2.$28k>_2.$28l)
this.$28o=true;this.setRowHeight(_3,_4,null,isc.emptyString,true,true,true);if(isc.Browser.isSafari)delete this.$28o;if(_2.$1n){var _5=this.$28g(this.getTableElement(_3,0));if(_5){var _6=_5.scrollHeight,_7=_5.offsetHeight;if(_6>_7)_5.scrollTop=_6-_7;else _5.scrollTop=0}}
if(_1==1){isc.Timer.setTimeout({target:this,methodName:"$28p"},0)}}
,isc.A.$28p=function isc_GridRenderer__rowAnimationComplete(){delete this.$417;this.adjustOverflow("row animation complete");var _1=this.$28j;delete this.$28i;delete this.$28j;if(_1&&_1.$03){var _2=_1.$28m?this.parentElement:this;_2.fireCallback(_1.$03)}}
,isc.A.finishAnimateRowHeight=function isc_GridRenderer_finishAnimateRowHeight(){if(!this.$28i)return;this.cancelAnimation(this.$28i);this.$28n(1)}
,isc.A.$80s=function isc_GridRenderer__getPrintChildren(){return this.$29a}
,isc.A.getTablePrintHTML=function isc_GridRenderer_getTablePrintHTML(_1,_2,_3,_4,_5){return this.getTableHTML(_1,_2,_3,_4,_5)}
,isc.A.draw=function isc_GridRenderer_draw(){if(isc.screenReader&&this.screenReader_suppressHandleFocus){this.clipHandleIsFocusHandle=this.isEmpty()}
return this.Super("draw",arguments)}
,isc.A.handleKeyDown=function isc_GridRenderer_handleKeyDown(_1,_2){var _3=this.Super("handleKeyDown",arguments);if(_3!=false&&isc.screenReader&&this.screenReader_suppressHandleFocus){var _4=isc.EH.clickMaskUp(),_5=false;if(_4){var _6=isc.EH.clickMaskRegistry;for(var i=0;i<_6.length;i++){if(isc.EH.isHardMask(_6[i])){_5=true;break}}}
if(!_5){var _8=_1.keyName;if(_8=="Tab"){this.$kf(!isc.EH.shiftKeyDown())
return false}}}
return _3}
,isc.A.getTableHTML=function isc_GridRenderer_getTableHTML(_1,_2,_3,_4,_5,_6){if(isc.$cv)arguments.$cw=this;var _7=isc.timeStamp();if(this.isEmpty()){this.$252=this.$253=this.$254=this.$255=null;if(this.isPrinting){if(this.$568&&this.printChunkOnly)return this.$27w();return this.grid.getPrintHeaders(_26,_27)+this.$27w()+this.grid.getPrintFooters(_26,_27)}
return this.$27w()}
if(this.isPrinting&&(!this.$568||_2==0)){var _8=this.$80s();if(_8!=null&&_8.length>0){for(var i=0;i<_8.length;i++){var _10=_8[i];if(_10.$80t!=null)continue;var _11={component:_10,colNum:_1,startRow:_2,endRow:_3,descreteCols:_4,asyncCallback:_5};var _12=_8[i].getPrintHTML(this.printProperties,_5==null?null:{target:this,methodName:"gotComponentPrintHTML",context:_11});if(_12!=null){_10.$80t=_12}else{return null}}}}
var _13=(_2!=null&&_3!=null),_14=_2!=null?_2:0,_15=_3!=null?_3:this.getTotalRows();var _16=this.getDrawArea(),_17=this.grid,_18;if(_17){if(_17.$30d){_18=_17.$30d==null?0:isc.isAn.Array(_17.$30d)?_17.$30d[0]:_17.$30d}else if(_17.data&&_17.data.getFirstUsedIndex&&_16[0]==0){_18=_17.data.getFirstUsedIndex()}
if(_18){var _19=_16[1]-_16[0],_20=_18+_19,_21=this.getTotalRows();if(_20>=_21){_18-=(_20-(_21-1))
_20=_21-1}
if(_18<0)_18=0;_16[0]=_18;_16[1]=_20}}
if(!_13){this.$252=_16[0];this.$253=_16[1];if(this.$28c!=null){this.$253+=(this.$28e-this.$28c);var _21=this.getTotalRows();if(this.$253>=_21)this.$253=_21-1}
_2=this.$252;_3=this.$253+1;var _22=(_2==0&&_3==this.getTotalRows());if(this.virtualScrolling){this.$60s=!_22&&!this.fixedRowHeights}
if(!this.$60s){delete this.$27y;delete this.$60t;delete this.$271;delete this.$60u}}else{var _23=_16[0],_24=_16[1]+1;if(this.$28q){if(_23>_3||_24<_2){_2=_3}else{if(!this.$73q){_2=Math.max(_2,_23);_3=Math.min(_3,_24)}}}}
this.$254=_16[2];this.$255=_16[3];var _25=_1!=null&&isc.isAn.Array(_1),_26,_27;if(!_25)_4=false;if(_1!=null){if(_25){_26=_1[0];_27=_1[1]+1}else{_26=_1;_27=_1+1}}else{_26=this.$254;_27=this.$255+1}
var _28;if(_4)_28=_1;else{_28=[];for(var i=_26;i<_27;i++){_28[_28.length]=i}}
var _29=_28.length;var _30=(this.shouldShowAllColumns()||_1!=null);var _31=isc.StringBuffer.create(),_32=this.fields,_33=this.$26a;this.$28r=_28[0]!=0?null:this.$26a[0];var _34,_35,_36,_37;if(!_30||this.leftSpace!=null||this.rightSpace!=null){_34=(this.leftSpace!=null)?this.leftSpace:0;_35=(this.rightSpace!=null)?this.rightSpace:0;if(!_30){_34+=this.$26a.slice(0,_26).sum();_35+=this.$26a.slice(_27,this.$26a.length).sum()}
_36=this.$26a.sum()
_37=(this.cacheDOM||(isc.Browser.isIE&&!isc.Browser.isIE8)?"margin":"padding")}
var _38=this.autoFit;var _39="";if(_1!=null){if(!_38&&this.fixedColumnWidths){_39=" WIDTH=100%"}}else if(this.isPrinting&&this.autoFit){_39=" WIDTH=100%"}else if((isc.Browser.isIE8Strict||isc.Browser.isMoz||isc.Browser.isSafari)&&!_38)
{var _40=this.$26a.slice(_26,_27).sum();_39=" WIDTH="+_40}
var _41=this.startSpace||0;if(_2!=_14){var _42=((_2-_14)*this.getAvgRowHeight());this.$514=_42;_41+=_42}else{this.$514=0}
var _43=true;var _44=(_15-_14)*this.getAvgRowHeight();if(isc.Browser.isIE){if(_44>1300000)_43=false}
if(!_13)this.$79b=_43;if(_44>10000000){this.logWarn("This grid is showing "+(_15-_14).toLocalizedString()+" rows. Due to native rendering limitations, grids with this many rows"+" may not appear correctly on all browsers. Consider filtering the data"+" displayed to the user to reduce the total number of rows displayed at a time."+" This will improve usability as well as avoiding unpredictable behavior.")}
if(!this.cacheDOM&&!this.isPrinting){_31.append("<DIV style='width:1px;");if(_43){_31.append("height:",_41,"px;overflow:hidden;")}
if(_41==0)_31.append("display:none;");_31.append("' ");if(_13||this.isPrinting){_31.append(">")}else{_31.append(" ID="+this.getID()+"$28s>")}
_31.append(isc.Canvas.spacerHTML(1,_41),"</DIV>")}
if(!this.$568||(_2==0&&!this.printChunkOnly)){_31.append("<TABLE role='presentation' BORDER=0",_39,((!_13&&!this.isPrinting)?" ID="+this.getTableElementId():null),(this.tableStyle&&isc.Browser.isDOM?" CLASS='"+this.tableStyle+this.$ob:isc.$ad)," CELLSPACING=",this.cellSpacing," CELLPADDING=",this.cellPadding," STYLE='",(isc.Browser.isDOM&&!_38&&this.fixedColumnWidths?"table-layout:fixed;overflow:hidden;wrap:false;":""),(!_30?_37+(this.isRTL()?"-right: ":"-left:")+_34+"px;"+_37+(this.isRTL()?"-left:":"-right:")+_35+"px;":""),(this.cacheDOM&&this.$514>0?"margin-top:"+this.$514+"px;":""),(this.$27y!=null&&!(isc.Browser.isIE&&this.$r9)?"visibility:hidden;":""),"'>",(isc.Browser.isMoz?"<TBODY>":""));var _45=0,_46=0,_47=this.$54p();if(isc.Browser.isStrict&&(isc.Browser.isSafari||isc.Browser.isIE)){if((isc.Browser.isIE&&!isc.Browser.isIE8)||(isc.Browser.isSafari&&isc.Browser.safariVersion<530))
{_46=this.$54q()}
_45=(this.fixedRowHeights?0:this.cellPadding*2);_45+=(this.fixedRowHeights?isc.Element.$ym(_47):isc.Element.$yo(_47))}
this.$28t=_45;this.$28u=_46;if(!_38&&isc.Browser.isDOM){for(var i=0;i<_28.length;i++){_31.append("<COL WIDTH=",(_33[_28[i]]-_46),">")}}
_31.append("<TBODY>")}
var _48=this.cellHeight,_49=(this.wrapCells?"":"<NOBR>"),_50=(this.wrapCells?"":"</NOBR>");var _51=0;if(isc.Browser.isDOM){var _52=this.$28v(_48);var _53=[];_53[0]="<TD";_53[3]=" ALIGN=";_53[17]=this.fastCellUpdates?"' ":"' CLASS=";if(!_13&&!this.isPrinting&&this.getCellElementId)_53[19]=" ID=";_53[23]=">"+_49;_53[30]=_50+(_52?"</DIV></TD>":"</TD>");var _54=1,_55=2,_56=4,_57=5,_58=6,_59=7,_60=9,_61=10,_62=11,_63=18,_64=20,_65=21,_66=24;var _67="<TR",_68="</TR>",_69=">",_70=" HEIGHT=",_71=" VALIGN=";if(isc.Browser.isMobileWebkit)_67+=" onmousedown=\"return true;\"";var _72;if(isc.screenReader){_72=" tabIndex=-1"}
var _73=isc.Canvas.ariaEnabled();var _74=[],_75=0,_76=[];this.$50d(_28,_38,_46,_52);if(this.isPrinting&&(!this.$568||(_2==0&&!this.printChunkOnly))){_31.append(this.grid.getPrintHeaders(_26,_27))}
for(var _77=_2;_77<_3;_77++){var _78=(!_13&&this.$28c==_77);var _79=this.getCellRecord(_77);var _80=_78||this.$282(_77,_79);_31.append(_67);if(_72!=null){if(this.screenReader_suppressHandleFocus){if(this.getNativeFocusRow()==_77){_31.append(" tabIndex=",this.getTabIndex(),isc.Canvas.$ry,this.$qn(),isc.Canvas.$rz,this.$qo())}else{_31.append(_72)}}else{_31.append(_72)}}
if(!_13&&!this.isPrinting&&this.getRowElementId){_31.append(" ID=",this.getRowElementId(_77,_77-_2))}
if(_73&&this.getRowRole!=null){var _81=this.getRowRole(_77,_79);if(_81!=null)_31.append(" role='",_81,"'");var _82=this.getRowAriaState(_77,_79);if(_82)_31.append(isc.Canvas.getAriaStateAttributes(_82))}
_31.append(_69);var _83=_78?this.$28f:(this.getRowHeight!=null?this.getRowHeight(_79,_77):_48);var _84;if(_78){_84=true}else{_84=this.fixedRowHeights;if(_84&&this.shouldFixRowHeight!=null){_84=(this.shouldFixRowHeight(_79,_77)!=false)}}
if(_84){_53[_54]=_70;_53[_55]=_83-_45;_53[_61]=null}else{_53[_54]=null;_53[_55]=null;if(!_80&&_83==this.cellHeight&&!this.fixedRowHeights)
{_53[_61]=null}else{_53[_61]=this.$281(_79,_77)}}
if(_52){_53[_65]=">"+this.$27s+this.$283(_83,_79,_77,_78)}
var _85=_80?this.$67p(_79,_77,_26,_27):null;for(var i=0;i<_28.length;i++){_1=_28[i];if(this.useCellRecords)_79=this.getCellRecord(_77,_1);var _86=_32[_1],_87=_79;if(_87==null)_87=this.getCellRecord(_77,_1);if(_74[_1]>0){_86.$28w[_77]=_76[_1];_74[_1]--;if(_74[_1]==0){_75--;_76[_1]=null}
continue}
_53[_56]=this.getCellAlign(_79,_86,_77,_1);var _88=this.getCellVAlign(_79,_86,_77,_1);if(_88!=null){_53[_57]=_71
_53[_58]=_88}
if(_85!=null&&(_1==_85[0])){_51++;_53[_59]=this.$67q(_85[1]-_85[0]);if(_52){_53[_65+1]=this.$ob}}else{_53[_59]=_86.$28x;if(this.getRowSpan){var _89=this.getRowSpan(_79,_77,_1);if(_89>1){var _90=" ROWSPAN="+_89;if(_53[_56]!=null)
_53[_56]+=_90;else
_53[_56]=_90;_74[_1]=_89-1;_75++;_76[_1]=_77;if(_86.$28w==null)_86.$28w={};_86.$28w[_77]=_77}}
if(_73&&this.getCellRole!=null){var _91=this.getCellRole(_77,_1,_79);if(_91!=null){var _92=this.getCellAriaState(_77,_1,_79);_53[_60]=" role='"+_91+(_92?isc.Canvas.getAriaStateAttributes(_92):"")}else{_53[_60]=null}}
if(_52){_53[_65+1]=_86.$28y}else{_53[_65+1]=null}}
var _93=this.getCellStyle(_79,_77,_1),_94=(this.getCellCSSText?this.getCellCSSText(_79,_77,_1):null);if(_78){var _95="padding:0px;border:0px;";if(_94)_94+=";"+_95
else _94=_95}
if(!this.fastCellUpdates){_53[_62]=_94
_53[_63]=_93}else{var _96=this.$77d(_93);_53[_62]=_96;_53[_62+1]=_94}
if(_78){this.$28q=true;var _97=this.getTableHTML(null,this.$28c,this.$28e);delete this.$28q;if(!_52){_53[_66]=isc.SB.concat(this.$27s,this.$283(_83,_79,_77,_78),this.$ob,this.$oa,_97,"</DIV>")}else{_53[_66]=_97}}else
_53[_66]=this.$22k(_79,_77,_1);if(!_13&&this.getCellElementId){_53[_64]=this.getCellElementId(_77,_77-_2,_1,_1-_26)}
_31.append(_53);if(!_13&&_87!=null&&_87.$29a!=null){if(_87.$29a[0]&&_87.$29a[0].rowNum==null)
{this.updateEmbeddedComponentCoords(_87.$29a,_87,_77,_1)}}
if(_80&&(_1==_85[0])){i+=_85[1]-_85[0]}}
_31.append(_68);if(this.isPrinting&&_79.$29a!=null){var _98=_79.$29a;for(var _99=0;_99<_98.length;_99++){var _100=_98[_99];if(_100.$57n==null&&_100.$80t!=null){_31.append(_67,_69,'<td colspan="',_29,'">',_100.$80t,"</td>",_68);delete _100.$80t}}}
if(_78){_77=this.$28e-1}}}
if(!this.$568||(_3==this.getTotalRows()&&!this.printChunkOnly)){if(this.grid&&this.isPrinting){_31.append(this.grid.getPrintFooters(_26,_27))}
_31.append("</TABLE>")}
var _101=_15-_3,_102=(!_13&&this.$60s);var _103=this.cacheDOM?0:(this.endSpace||0);this.$515=0;if(!this.showAllRows&&(_101!=0||_102)){var _104=_101*this.getAvgRowHeight();if(_102&&_101==0){var _105=this.getViewportHeight();if(_104<_105){_104=_105}}
this.$515=_104;_103+=this.$515}
if(!this.cacheDOM&&!this.isPrinting){_31.append("<DIV style='width:1px;");if(_43){_31.append("height:",_103,"px;overflow:hidden;")}
if(_103==0)_31.append("display:none;");_31.append("' ");if(_13||this.isPrinting){_31.append(">")}else{_31.append(" ID="+this.getID()+"$284>")}
_31.append(isc.Canvas.spacerHTML(1,_103),"</DIV>")}
if(this.logIsDebugEnabled("gridHTML")){var _106=(isc.timeStamp()-_7),_107=(_29*(_3-_2)),_108=(_106/ _107),_109=(1000/_108);if(_108.toFixed!=null)_108=_108.toFixed(2);if(_109.toFixed!=null)_109=_109.toFixed(2);this.logDebug("getTableHTML: columns "+(_4?_28:_26+"->"+(_27-1))+", rows "+_2+"->"+(_3-1)+", time: "+_106+"ms ("+_107+" cells at "+_108+"ms per cell, "+_109+" cells per second), "+"spacerHeights: ["+[_41,_103]+"], "+"left/right pad: ["+[_34,_35]+"], "+_51+" single cell rows","gridHTML")}
var _110=_31.release();if(_6){if(_5!=null){this.fireCallback(_5,"HTML,callback",[_110,_5])}
return null}
return _110}
,isc.A.setFocus=function isc_GridRenderer_setFocus(_1,_2){if(isc.screenReader&&_2=="focus on mousedown"){var _3=this.getEventRow();if(_3!=null&&!this.isEmpty()){if(_3==-2)_3=this.getTotalRows()-1;if(_3>=0){this.$86a(_3,true)}}}
return this.Super("setFocus",arguments)}
,isc.A.$86a=function isc_GridRenderer__putNativeFocusInRow(_1,_2){var _3=this.screenReader_suppressHandleFocus;if(_3)this.$vj(false);this.$86b=_1;if(_3)this.$vj(true);if(this.screenReader_suppressHandleFocus){this.clipHandleIsFocusHandle=this.isEmpty()}
if(_2==null)_2=!this.hasFocus;if(_2){return}
var _4=this.getFocusHandle();if(_4)_4.focus();if(!_3)isc.EH.$ke=null;_4.focus();if(!_3)isc.EH.$ke=this}
,isc.A.getFocusHandle=function isc_GridRenderer_getFocusHandle(){if(!isc.screenReader)return this.Super("getFocusHandle",arguments);if(isc.screenReader){var _1=this.getNativeFocusRow();var _2=this.getTableElement(_1);if(_2!=null)return _2;return this.Super("getFocusHandle",arguments)}}
,isc.A.gotComponentPrintHTML=function isc_GridRenderer_gotComponentPrintHTML(_1,_2){var _3=_2.context,_4=_3.component;if(_3.asyncCallback==null){return}
_4.$80t=_1;return this.getTableHTML(_3.colNum,_3.startRow,_3.endRow,_3.discreteCols,_3.asyncCallback,true)}
,isc.A.$77d=function isc_GridRenderer__getEscapedStyleText(_1){if(this.$77c[_1]!=null)return this.$77c[_1];var _2=isc.Element.getStyleText(_1,true);this.$77c[_1]=_2.replaceAll("'",'"');return this.$77c[_1]}
,isc.A.getCellVAlign=function isc_GridRenderer_getCellVAlign(_1,_2,_3,_4){return null}
,isc.A.getCellAlign=function isc_GridRenderer_getCellAlign(_1,_2,_3,_4){return _2.cellAlign||_2.align}
,isc.A.$67p=function isc_GridRenderer__getSingleCellSpan(_1,_2,_3,_4){return[_3,_4]}
,isc.A.$67q=function isc_GridRenderer__getTDSpanHTML(_1){if(!isc.GridRenderer.$67r){isc.GridRenderer.$67r={$67s:{},$67t:{}}}
var _2=this.fixedRowHeights?isc.GridRenderer.$67r.$67s:isc.GridRenderer.$67r.$67t;if(_2[_1])return _2[_1];else{return _2[_1]=" COLSPAN="+_1+" STYLE='"+(this.fixedRowHeights?"padding-top:0px;padding-bottom:0px;":"")}}
,isc.A.$54p=function isc_GridRenderer__getFirstRecordStyle(){var _1=this.grid,_2=0;if(_1){if(_1.$30d){_2=_1.$30d==null?0:isc.isAn.Array(_1.$30d)?_1.$30d[0]:_1.$30d}else if(_1.data&&_1.data.getFirstUsedIndex){_2=_1.data.getFirstUsedIndex()}}
return(this.getBaseStyle!=null?this.getBaseStyle(this.getCellRecord(_2,0),0,0):this.baseStyle)}
,isc.A.$50d=function isc_GridRenderer__cacheColumnHTML(_1,_2,_3,_4){var _5=this.fields,_6=this.$26a;for(var i=0;i<_1.length;i++){var _8=_1[i],_9=_5[_8];_9.$28w=null;if(_2){_9.$28x=(isc.Browser.isIE&&!isc.Browser.isIEStrict)?" STYLE='":" STYLE='OVERFLOW:hidden;";_9.$28y=this.$ob}else{var _10=isc.Browser.isIE8Strict?" STYLE='overflow:hidden;":" STYLE='";_9.$28x=(isc.Browser.isIE?" WIDTH="+(_6[_8]-_3)+_10:" STYLE='"+this.$28z(_8));if(_4){_9.$28y=this.$280(_8)+this.$ob}}
if(!this.fixedRowHeights){_9.$28x+=this.$281()}
if(this.fixedRowHeights)_9.$28x+="padding-top:0px;padding-bottom:0px;"}}
,isc.A.$28v=function isc_GridRenderer__writeDiv(_1){return(isc.Browser.isSafari||(isc.Browser.isOpera&&!this.autoFit&&(this.fixedColumnWidths||this.fixedRowHeights))||(isc.Browser.isMoz&&isc.Browser.geckoVersion>=20040113&&this.fixedColumnWidths&&!this.autoFit)||(this.fixedRowHeights&&(this.wrapCells||this.enforceVClipping)&&(isc.Browser.isMoz||(isc.Browser.isStrict&&isc.Browser.isIE))))}
,isc.A.$282=function isc_GridRenderer__drawRecordAsSingleCell(_1,_2){return(_2&&(_2[this.singleCellValueProperty]!=null||_2[this.isSeparatorProperty]||(Array.isLoading(_2)&&!(isc.Browser.isSafari&&(_1==0||_1==this.$252)))))}
,isc.A.$283=function isc_GridRenderer__getCellDivCSSHeight(_1,_2,_3,_4){var _5=_4||(this.fixedRowHeights&&(this.enforceVClipping||this.wrapCells)&&(this.shouldFixRowHeight==null||this.shouldFixRowHeight(_2,_3)!=false));if(_5){var _6=_1-2*this.cellSpacing-
(_4?0:2);if(_3==this.$285){for(var i=0;i<this.$286.getItems().length;i++){_6=Math.max(this.$286.getItems()[i].getHeight(),this.$286.getItems()[i].iconHeight)}}
if(_6<1)_6=1;return(isc.Browser.isMoz||isc.Browser.isSafari?"MAX-HEIGHT:":"HEIGHT:")+_6+"px;"}
return isc.$ad}
,isc.A.$280=function isc_GridRenderer__getFieldDivWidthHTML(_1){if(!this.fixedColumnWidths||this.autoFit)return isc.emptyString;return"WIDTH:"+this.getInnerColumnWidth(_1)+"px;"}
,isc.A.$281=function isc_GridRenderer__getMinHeightCSSText(_1,_2){var _3=(_2!=null?this.getRowHeight(_1,_2):this.cellHeight),_4=isc.Browser.isIE,_5=isc.Browser.isStrict;if(_5&&(_4||isc.Browser.isSafari))_3-=this.$28t;if(_4&&!_5&&!(this.autoFit||!this.fixedColumnWidths)){return"MIN-HEIGHT:"+_3+"px;"}
return"HEIGHT:"+_3+"px;"}
,isc.A.$28z=function isc_GridRenderer__getCSSTextForColWidth(_1){if(isc.Browser.isIE||this.autoFit)return isc.$ad;if(this.$287==null){this.$287=[];for(var i=0;i<this.$26a.length;i++){var _3=this.$26a[i];this.$287[i]="WIDTH:"+_3+(this.fixedColumnWidths?"px;OVERFLOW:hidden;":"px;")}}
return this.$287[_1]}
,isc.A.getCellRecord=function isc_GridRenderer_getCellRecord(_1,_2){return null}
,isc.A.findRowNum=function isc_GridRenderer_findRowNum(_1){return-1}
,isc.A.findColNum=function isc_GridRenderer_findColNum(_1){return-1}
,isc.A.$22k=function isc_GridRenderer__getCellValue(_1,_2,_3){var _4=this.getCellValue(_1,_2,_3,this);if(!this.isPrinting&&this.$80g(_1)){var _5=this.$667(_1);if(_5.allWithin){if(_5.extraHeight&&(_5.extraHeight>this.cellHeight)){_4=[_4,this.$80q,isc.Canvas.spacerHTML(1,_5.extraHeight-this.cellHeight),this.$80r].join(isc.emptyString)}}else if(_5.extraHeight&&_5.extraHeight>0){_4=[_4,this.$80q,isc.Canvas.spacerHTML(1,_5.extraHeight),this.$80r].join(isc.emptyString)}}else if(_1&&_1.$29a!=null){var _6=_1.$29a||[];for(var i=0;i<_6.length;i++){var _8=_1.$29a[i];if(_8==null)continue;if(_8.$57n!=_3)continue;var _9=(_8.embeddedPosition==this.$57l);var _10=_8.$80t;if(_10!=null){_4+=_9?this.$80q+_10+this.$80r:_10;delete _8.$80t}}}
return _4}
,isc.A.$80g=function isc_GridRenderer__writeEmbeddedComponentSpacer(_1){return(_1&&_1.$29a)!=null}
,isc.A.getCellValue=function isc_GridRenderer_getCellValue(_1,_2,_3){return this.emptyCellValue}
,isc.A.getTotalRows=function isc_GridRenderer_getTotalRows(){return this.totalRows}
);isc.evalBoundary;isc.B.push(isc.A.setColumnWidth=function isc_GridRenderer_setColumnWidth(_1,_2){this.fields[_1].width=this.$26a[_1]=_2;this.$287=null;this.markForRedraw("setColumnWidth")}
,isc.A.setColumnWidths=function isc_GridRenderer_setColumnWidths(_1){var _2=this.$26a;this.$26a=_1.duplicate();this.$287=null;if(_2!=null&&_1!=null&&_2.length==_1.length){if(_2==_1)return;var _3=false;for(var i=0;i<_2.length;i++){if(_2[i]!=_1[i])_3=true}
if(!_3)return;if(!this.fixedColumnWidths&&!this.wrapCells&&this.isDrawn()&&_1.length==1){var _5=this.$28r||_2[0],_6=_1[0],_7=this.getColumnSize(0);if((_5==_6)||(_7>_5&&_7>=_6)){return}}}
this.markForRedraw("setColumnWidths")}
,isc.A.shouldRedrawOnResize=function isc_GridRenderer_shouldRedrawOnResize(_1,_2,_3){if(this.redrawOnResize!=null)return this.redrawOnResize;if(isc.isA.ListGrid(this.parentElement)&&isc.isA.Layout(this.parentElement.parentElement))
{var _4=this.parentElement.parentElement.getMembers();if(_4&&_4.map("isAnimating").or())return false}
if(this.$276()||this.$277())return true;if(this.isEmpty())return true;return false}
,isc.A.getRowHeight=function isc_GridRenderer_getRowHeight(_1,_2){var _3=this.updateHeightForEmbeddedComponents(_1,_2,this.cellHeight);return _3}
,isc.A.updateHeightForEmbeddedComponents=function isc_GridRenderer_updateHeightForEmbeddedComponents(_1,_2,_3){if(_1&&_1.$29a){var _4=this.$667(_1,_2);if(_4.allWithin&&_4.extraHeight>0){_3=Math.max(_3,_4.extraHeight)}else{_3+=_4.extraHeight}}
return _3}
,isc.A.$667=function isc_GridRenderer__getExtraEmbeddedComponentHeight(_1,_2){var _3=_1.$29a||[],_4=0,_5=true,_6=this.isPrinting;for(var i=0;i<_3.length;i++){var _8=_1.$29a[i];if(_6)continue;if(_2!=null)_8.$289=_2;var _9=(_8.embeddedPosition==this.$57l);if(!_9)_5=false;var _10=_8.getVisibleHeight();var _11=(_9?(_10>this.cellHeight?_10:0):_10);if(_8._percent_height!=null){_8.height=_8._percent_height;_11=this.cellHeight}
var _12=_8.specifiedHeight;if(_9&&_12&&isc.isA.String(_12)&&_12.contains("%"))
_11=0;if(_11>_4){_4=_11}}
return{allWithin:_5,extraHeight:_4}}
,isc.A.getCellStartRow=function isc_GridRenderer_getCellStartRow(_1,_2){var _3=this.fields[_2].$28w;if(_3==null||_3[_1]==null)return _1;return _3[_1]}
,isc.A.getCellRowSpan=function isc_GridRenderer_getCellRowSpan(_1,_2){var _3=this.fields[_2].$28w;var _4=this.getCellStartRow(_1,_2);if(_4==_1)return 1;var _5=_1+1,_6=_1-_4+1;while(_5<=this.$253&&_3[_5]==_4)
{_5++;_6++}
return _6}
,isc.A.addEmbeddedComponent=function isc_GridRenderer_addEmbeddedComponent(_1,_2,_3,_4,_5){if(_5==null)_5=this.$57m;var _6=((_5==this.$57m)||!this.fixedRowHeights);if(!isc.isA.Canvas(_1)){_1.autoDraw=false;var _7=isc.ClassFactory.getClass(_1._constructor);if(_7==null)_7=isc.Canvas;_1=_7.create(_1)}
var _8=false;if(this.$29a&&this.$29a.contains(_1)){if(_2.$29a&&_2.$29a.contains(_1)&&_1.embeddedPosition==_5&&_1.$289==_3&&_1.$57n==_4)
{return}
if(_5==_1.embeddedPosition&&!_6){_8=!this.isDirty()}
this.removeEmbeddedComponent(_1.embeddedRecord,_1,true)}else if(!_6){_8=!this.isDirty()}
if(!_2.$29a)_2.$29a=[];_2.$29a.add(_1);if(this.$29a==null)this.$29a=[];this.$29a.add(_1);_1.embeddedPosition=_5;_1.embeddedRecord=_2;_1.$289=_3;_1.$57n=_4;_1.$669=this.getID();_1.percentBox="custom";if(_1.parentElement!=this){var _9=this.$417;this.$417=true;_1.hide();this.addChild(_1);if(_9==null)delete this.$417}
this.observe(_1,"resized","observer.$80d(observed, deltaX, deltaY)");_1.$29b=_1._redrawWithParent;_1._redrawWithParent=false;_1.$668=_1.bubbleMouseEvents;if(!_1.bubbleMouseEvents){_1.bubbleMouseEvents=["mouseDown","mouseUp","click","doubleClick","contextClick"]}
if(_8&&(_3==-1||_4==-1)){_8=false}
if(_8){this.placeEmbeddedComponent(_1)}else{this.markForRedraw("added embedded component")}
return _1}
,isc.A.$80d=function isc_GridRenderer__handleEmbeddedComponentResize(_1,_2,_3){var _4=_1.embeddedPosition;if(_4!=this.$57l){if(_3!=null&&_3!=0)this.markForRedraw('embedded component resized')}else{this.placeEmbeddedComponent(_1)}}
,isc.A.updateEmbeddedComponentCoords=function isc_GridRenderer_updateEmbeddedComponentCoords(_1,_2,_3,_4){_1.setProperty("$289",_3)}
,isc.A.placeEmbeddedComponent=function isc_GridRenderer_placeEmbeddedComponent(_1){var _2=_1.$289;if(_2==null||(this.$252==null||this.$253==null)||(_2<this.$252||_2>this.$253))
{if(_1.isDrawn())_1.clear();return}
var _3=_1.embeddedRecord,_4=_1.embeddedPosition,_5=_1.$57n,_6=this.getRowTop(_2),_7=_5!=null?this.getColumnLeft(_5):0,_8=(_5!=null&&_5>=0)?this.getColumnWidth(_5):Math.min(this.getInnerWidth()+this.getScrollLeft(),this.$26a.sum());if(_4==this.$57l){var _9=this.getEmbeddedComponentSnapTo(_1,_3,_2,_5),_10=_1.snapEdge||_9;var _11=this.getRowSize(_2),_12=_1._percent_width,_13=_1._percent_height,_14,_15;var _16=0,_17=_3.$29a;for(var i=0;i<_17.length;i++){var _19=_3.$29a[i];var _20=(_19.embeddedPosition==this.$57l);if(_20)continue;var _21=_19.getVisibleHeight();if(_21>_16){_16=_21}}
_11-=_16;if(_1.snapOffsetLeft)_8-=_1.snapOffsetLeft;if(isc.isA.String(_12)&&_12.endsWith("%")){_14=Math.round((parseInt(_12)*_8)/100)}
if(isc.isA.String(_13)&&_13.endsWith("%")){_15=Math.round((parseInt(_13)*_11)/100)}
var _22=_15!=null?_15:_1.getHeight(),_23=_14!=null?_14:_1.getWidth();if(_15||_14){_1.resizeTo(_14,_15);_1._percent_width=_12;_1._percent_height=_13}
isc.Canvas.snapToEdge([_7,_6,_8,_11],_9,_1,_10)}else{_1.moveTo(_7,_6);_1.setWidth(_8)}
var _24=this.isDrawn();if(_24&&!_1.isDrawn())_1.draw();var _25=this.isDirty(),_26=this.getRowHeight(_3,_2),_27=!this.isDirty()||_25;if(_27&&(_26!=this.getRowSize(_2))){this.setRowHeight(_2,_26,_3);this.refreshRow(_2)}
if(_24){if(_4!=this.$57l){var _28=this.getDrawnRowHeight(_2)-_1.getVisibleHeight()-1;_1.moveTo(null,this.getRowTop(_2)+_28)}
if(!_1.isVisible()){if(this.shouldAnimateEmbeddedComponent(_1)){_1.animateShow()}else{_1.show()}}}
this.updateEmbeddedComponentZIndex(_1)}
,isc.A.getEmbeddedComponentSnapTo=function isc_GridRenderer_getEmbeddedComponentSnapTo(_1,_2,_3,_4){if(_1.snapTo!=null)return _1.snapTo;if(_4==null){return"TL"}
var _5=this.getCellAlign(_2,this.fields[_4],_3,_4)||"center",_6=this.getCellVAlign(_2,this.fields[_4],_3,_4)||"center";var _7=this.alignSnapToMap[_5][_6];return _7}
,isc.A.shouldAnimateEmbeddedComponent=function isc_GridRenderer_shouldAnimateEmbeddedComponent(_1){return false}
,isc.A.updateEmbeddedComponentZIndex=function isc_GridRenderer_updateEmbeddedComponentZIndex(_1){}
,isc.A.getEmbeddedComponent=function isc_GridRenderer_getEmbeddedComponent(_1,_2){if(isc.isA.Number(_1))_1=this.getCellRecord(_1,0);var _3=_1.$29a;if(_3==null)return;var _4=null;if(isc.isA.Number(_2))
_4=_3.find({$57n:_2,$669:this.getID()});return _4}
,isc.A.removeEmbeddedComponent=function isc_GridRenderer_removeEmbeddedComponent(_1,_2,_3){if(isc.isA.Number(_1))_1=this.getCellRecord(_1,0);var _4=_1.$29a;if(_4==null)return;if(isc.isA.Number(_2))
_2=_4.find({$57n:_2,$669:this.getID()});if(!_2)
_2=_4.find({$669:this.getID()});if(!_4.contains(_2))return;if(this.isObserving(_2,"resized")){this.ignore(_2,"resized")}
_1.$29a.remove(_2);if(_1.$29a.length==0)_1.$29a=null;if(this.$29a)this.$29a.remove(_2);_2._redrawWithParent=_2.$29b;_2.$29b=null;_2.bubbleMouseEvents=_2.$668;var _5=_2.embeddedPosition==this.$57m;_2.embeddedPosition=null;_2.$289=null;_2.$57n=null;_2.$669=null;if(_3){_2.hide();return}
if(_2.destroyOnUnEmbed)_2.destroy();else{this.removeChild(_2)}
if(_5){this.markForRedraw("removed embedded component")}}
,isc.A.$29c=function isc_GridRenderer__resetEmbeddedComponents(){var _1=this.$29a;if(_1==null)return;_1.setProperty("$289",null)}
,isc.A.$29d=function isc_GridRenderer__placeEmbeddedComponents(){var _1=this.$29a;if(_1==null)return;_1.sortByProperty("$289",true);for(var i=0;i<_1.length;i++){this.placeEmbeddedComponent(_1[i])}}
,isc.A.getTableZIndex=function isc_GridRenderer_getTableZIndex(){return 1000}
,isc.A.getCellStyle=function isc_GridRenderer_getCellStyle(_1,_2,_3){if(_1&&_1[this.recordCustomStyleProperty]!=null){return _1[this.recordCustomStyleProperty]}
var _4=this.getCellStyleIndex(_1,_2,_3);return this.getCellStyleName(_4,_1,_2,_3)}
,isc.A.getCellStyleName=function isc_GridRenderer_getCellStyleName(_1,_2,_3,_4){var _5=isc.GridRenderer.standardStyleSuffixes;if(this.getBaseStyle){var _6=this.getBaseStyle(_2,_3,_4);if(_6!==this.baseStyle){if(_1==0)return _6;return _6+_5[_1]}}
if(!this.$29e){this.$29e=[];for(var i=0;i<_5.length;i++){this.$29e[i]=this.baseStyle+_5[i]}}
return this.$29e[_1]}
,isc.A.getCellStyleIndex=function isc_GridRenderer_getCellStyleIndex(_1,_2,_3){var _4=0;var _5=true;if(this.grid!=null){var _6=this.grid.getField(this.grid.getFieldNumFromLocal(_3,this));_5=!_6?true:_6.showAlternateStyle!=false}
if(this.alternateRowStyles&&_5){var _7=(Math.floor(_2/ this.alternateRowFrequency)%2==1);if(_7)_4+=8}
if(!this.cellIsEnabled(_2,_3)){_4+=4}else{if(this.shouldShowRollOver(_2,_3)&&!this.isPrinting&&_2==this.lastOverRow&&(!this.useCellRollOvers||_3==this.lastOverCol))
{_4+=1}
if(this.showSelectedStyle&&this.selectionEnabled()){var _8=(this.canSelectCells?this.selection.cellIsSelected(_2,_3):this.selection.isSelected(_1));if(_8)_4+=2}}
return _4}
,isc.A.cellIsEnabled=function isc_GridRenderer_cellIsEnabled(_1,_2){return true}
,isc.A.getTableElementId=function isc_GridRenderer_getTableElementId(){return this.getCanvasName()+"table"}
,isc.A.getDOMTable=function isc_GridRenderer_getDOMTable(_1,_2){if(this.cacheDOM)return this.getTableChunkAt(_1,_2);if((_1!=null&&(_1-this.$252<0||_1>this.$253))||(_2!=null&&(_2-this.$254<0||_2>this.$255)))
return null;var _3=this.$29f;if(_3==null){var _4=this.getTableElementId();var _3=isc.Element.get(_4);if(_3==null)return null;if(this.$756){this.logInfo("getTableElement() called while updating table HTML. "+"This call may be invalid as the table is being rewritten in the DOM. "+"Suppressing caching of the current element.","redrawing");return _3}}
return this.$29f=_3}
,isc.A.getTableElement=function isc_GridRenderer_getTableElement(_1,_2){var _3=this.getDOMTable(_1,_2);if(_1==null)return _3;if(!_3)return null;var _4=_1-(this.$252>0?this.$252:0);if(_4<0){return null}
var _5;if(this.$29g!=null)_5=this.$29g[_4];if(_5==null)_5=_3.rows[_4];if(_5==null)return null;if(!this.$756){if(this.$29g==null)this.$29g=[];this.$29g[_4]=_5}
if(_2==null)return _5;var _6=_2-this.$254;if(_6<0){return null}
if(this.getRowSpan){var _7=this.getCellStartRow(_1,_6);if(_7!=_4){_4=_7;_5=this.getTableElement(_7)}
if(_5.cells.length<(this.$255-this.$254+1)){var _8=0;for(var i=0;i<_6;i++){if(this.fields[i].$28w!=null&&this.fields[i].$28w[_4]!=null&&this.fields[i].$28w[_4]!=_4)_8++}
_6-=_8}}
return _5.cells[_6]}
,isc.A.$29h=function isc_GridRenderer__updateCellStyle(_1,_2,_3,_4,_5){if(_4==null)_4=this.getTableElement(_2,_3);if(_4==null)return;if(_1==null)_1=this.getCellRecord(_2,_3);if(_5==null)_5=this.getCellStyle(_1,_2,_3);if(this.fastCellUpdates){_4.style.cssText=this.$29i(_1,_2,_3,_5)}else{if(_4.className!=_5)_4.className=_5;if(this.getCellCSSText){_4.style.cssText=this.$29i(_1,_2,_3,_5)}}
if(this.shouldRefreshCellHTML(_1,_2,_3)){this.refreshCellValue(_2,_3)}
if(!this.isDrawn())return;var _6=this.fixedRowHeights&&(this.shouldFixRowHeight==null||this.shouldFixRowHeight(_1,_2)!=false),_7=(this.getRowHeight!=null?this.getRowHeight(_1,_2):this.cellHeight);this.setRowHeight(_2,_7,_1,_5,_6)}
,isc.A.$28g=function isc_GridRenderer__getCellClipDiv(_1){if(_1==null)return null;var _2=_1.childNodes[0];if(!_2)return null;if(_2.tagName==this.$27t)_2=_2.childNodes[0];if(_2&&(_2.cellClipDiv||(_2.getAttribute&&_2.getAttribute(this.$27u))))
{return _2}
return null}
,isc.A.setRowHeight=function isc_GridRenderer_setRowHeight(_1,_2,_3,_4,_5,_6){var _7=this.$254,_8=this.$255;if(_5==null){if(_3==null)_3=this.getCellRecord(_1,_7);_5=this.fixedRowHeights&&(this.shouldFixRowHeight==null||this.shouldFixRowHeight(_3,_1)!=false)}
var _9=this.getTableElement(_1,_7),_10=_9?parseInt(_9.height):null,_11;if(!isc.isA.Number(_10))_10=null;if((isc.Browser.isSafari||isc.Browser.isIE)&&isc.Browser.isStrict){if(_3==null)_3=this.getCellRecord(_1,_7);var _12=_4;if(_12==null)_12=this.getCellStyle(_3,_1,_7)
_2-=this.fixedRowHeights?isc.Element.$ym(_12):isc.Element.$yo(_12);if(!this.fixedRowHeights)_2-=(this.cellPadding*2)}
if((!_5&&_10!=null)||(_10!=_2&&!(_10==null&&_2==isc.emptyString)))
{_11=true}
if(!_11)return;var _13=isc.isA.Number(_2);if(_13&&_2<=0)_2=_5?0:1;var _14=this.getTableElement(_1);if(_2==0&&_5){_14.style.display="none"}else{_14.style.display=isc.emptyString;for(var i=_7;i<=_8;i++){var _16=this.getTableElement(_1,i);if(_16){var _17=(!isc.Browser.isIE||isc.Browser.isStrict)?this.$o5:this.$27v;if(_5){_16.height=_2;_16.style[_17]=isc.emptyString}else{_16.height=isc.emptyString;_16.style[_17]=_2}
var _18=this.$28g(_16),_19=(_5?(_13?_2+isc.px:_2):isc.emptyString);if(_18){if(isc.Browser.isMoz||isc.Browser.isSafari)
_18.style.maxHeight=_19;else
_18.style.height=_19}}}}
if(isc.Browser.isSafari&&this.$28o){var _20=this.getTableElement(_1);if(_20!=null){_20.innerHTML=_20.innerHTML}}
this.$29j();if(_6){this.adjustOverflow("cell height changed")}else{this.$t6("cell height changed")}}
,isc.A.$29i=function isc_GridRenderer__getCompleteCellCSSText(_1,_2,_3,_4){var _5=null;if(this.fixedRowHeights)_5=this.$721;else{_5=this.$281(_1,_2)}
if(isc.Browser.isIE8Strict){if(_5==null)_5=this.$722;else _5+=this.$722}
if(isc.Browser.isMoz||isc.Browser.isSafari){if(_5==null)_5=this.$28z(_3);else _5+=this.$28z(_3)}
if(this.fastCellUpdates){if(_4==null)_4=this.getCellStyle(_1,_2,_3);var _6=isc.Element.getStyleText(_4,true);if(_6==null&&isc.Page.$29k){this.logInfo("fastCellUpdates set to true but this page loads styles from a "+"remote stylesheet. This is unsupported - disabling fastCellUpdates.");this.fastCellUpdates=false;this.redraw()}
if(_5!=null)_5+=_6;else _5=_6}
if(this.getCellCSSText){var _7=this.getCellCSSText(_1,_2,_3)
if(_7!=null){if(!_7.endsWith(this.$39)){_7+=this.$39}
if(_5!=null)_5+=_7
else _5=_7}}
return _5}
,isc.A.shouldRefreshCellHTML=function isc_GridRenderer_shouldRefreshCellHTML(_1,_2,_3){return this.showHiliteInCells}
,isc.A.$29l=function isc_GridRenderer__readyToRefreshCell(_1,_2){if((isc.EH.$ku||isc.EH.$km)&&isc.EH.lastEvent.target==this){var _3=this.getEventRow();if(_3!=_1)return true;if(_2!=null){var _4=this.getEventColumn();if(_2!=_4)return true}
return false}
return true}
,isc.A.refreshCellValue=function isc_GridRenderer_refreshCellValue(_1,_2){var _3=this.getTableElement(_1,_2);if(!_3)return;if(!this.$29l(_1,_2)){this.delayCall("refreshCellValue",[_1,_2]);return}
var _4=this.getCellRecord(_1,_2),_5=this.fields[_2];if(!_5){this.logDebug("refreshCell called for invalid field "+_2);return}
var _6="";var _7=this.getCellStyle(_4,_1,_2),_8=this.$28v(_9);if(_8){_6+=this.$27s;var _9=(this.getRowHeight!=null?this.getRowHeight(_4,_1):this.cellHeight);_6+=this.$283(_9,_4,_1);var _10=this.$282(_1,_4);if(!_10){_6+=this.$280(_2)}
_6+="'>"}
if(!this.wrapCells)_6+="<NOBR>";_6+=this.$22k(_4,_1,_2);if(!this.wrapCells)_6+="</NOBR>";if(_8)_6+="</DIV>";_3.innerHTML=_6}
,isc.A.setCellStyle=function isc_GridRenderer_setCellStyle(_1,_2,_3){return this.setRowStyle(_1,_3,_2)}
,isc.A.setRowStyle=function isc_GridRenderer_setRowStyle(_1,_2,_3){if(isc.$cv)arguments.$cw=this;if(_1==null||_1<0){this.logWarn("setRowStyle: bad rowNum: "+_1);return false}
var _4=this.getTableElement(_1,_3);if(_4==null){return false}
var _5=this.getCellRecord(_1,_3);if(_5&&_5.$29m){return}
if(_3!=null){this.$29h(_5,_1,_3,_4,_2)}else{var _6=this.getTableElement(_1);if(_6!=null){var _7="TD",_8=(!this.shouldShowAllColumns()?this.$254:0),_9=(!this.shouldShowAllColumns()?this.$255:this.fields.length-1),_10=0;for(var _11=_8;_11<=_9;_11++,_10++){var _4;if(this.showColumnsSeparately||this.cacheDOM){_4=this.getTableElement(_1,_11)}else{_4=_6.cells[_10]}
if(_4==null)continue;this.$29h(_5,_1,_11,_4,_2)}}}
return true}
,isc.A.refreshCellStyle=function isc_GridRenderer_refreshCellStyle(_1,_2,_3){return this.setCellStyle(_1,_2,_3)}
,isc.A.refreshCell=function isc_GridRenderer_refreshCell(_1,_2){this.refreshCellStyle(_1,_2);if(!this.shouldRefreshCellHTML())this.refreshCellValue(_1,_2)}
,isc.A.refreshRow=function isc_GridRenderer_refreshRow(_1){if(!this.$29l(_1)){this.delayCall("refreshRow",[_1])}
for(var i=0;i<this.fields.length;i++){this.refreshCell(_1,i)}}
,isc.A.refreshCellStyles=function isc_GridRenderer_refreshCellStyles(_1,_2){this.logDebug("refreshing cell styles: "+_1.length+" cells");for(var i=0;i<_1.length;i++){var _4=_1[i][0],_5=_1[i][1];var _6=this.getTableElement(_4,_5);if(_6==null){continue}else{this.$29h(null,_4,_5,_6,_2)}}
return true}
,isc.A.getCellPageRect=function isc_GridRenderer_getCellPageRect(_1,_2){return[this.getColumnPageLeft(_2),this.getRowPageTop(_1),this.getColumnSize(_2),this.getRowSize(_1)]}
,isc.A.getColumnLeft=function isc_GridRenderer_getColumnLeft(_1){if(this.isRTL()){return this.getScrollWidth()-this.$26a.sum(0,_1+1)-
(this.vscrollOn?this.getScrollbarSize():0)}else{return this.$26a.sum(0,_1)}}
,isc.A.getColumnPageLeft=function isc_GridRenderer_getColumnPageLeft(_1){return this.getPageLeft()-this.getScrollLeft()+this.getColumnLeft(_1)+(this.isRTL()&&this.vscrollOn?this.getScrollbarSize():0)}
,isc.A.getColumnWidth=function isc_GridRenderer_getColumnWidth(_1){return this.$26a[_1]}
,isc.A.getInnerColumnWidth=function isc_GridRenderer_getInnerColumnWidth(_1){var _2=this.getColumnWidth(_1);if(_2==null)return null;return(_2-(2*this.cellSpacing+this.$54q()))}
,isc.A.$54q=function isc_GridRenderer__getCellHBorderPad(_1){if(!_1&&this.$54r!=null)return this.$54r;var _2=this.$54p(),_3=isc.Element.$tr(_2,true),_4=isc.Element.$ts(_2,true),_5=isc.Element.$yn(_2);if(_3==null)_3=this.cellPadding;if(_4==null)_4=this.cellPadding;this.$54r=(_3+_4+_5);return this.$54r}
,isc.A.getRowTop=function isc_GridRenderer_getRowTop(_1){if(_1<this.$252)return this.getAvgRowHeight()*_1;var _2=this.$29n(),_3=this.$26b();if(_1>this.$253){return _2+_3.sum()+(((_1-1)-this.$253)*this.getAvgRowHeight())}
return _2+_3.sum(0,_1-this.$252)}
,isc.A.getRowPageTop=function isc_GridRenderer_getRowPageTop(_1){return this.getPageTop()+this.getTopBorderSize()+(this.getRowTop(_1)-this.getScrollTop())}
,isc.A.getRowSize=function isc_GridRenderer_getRowSize(_1){return this.getDrawnRowHeight(_1)}
,isc.A.getDrawnRowHeight=function isc_GridRenderer_getDrawnRowHeight(_1){if(this.$252==null||this.$253==null||_1<this.$252||_1>this.$253)
{return this.getAvgRowHeight()}
var _2=_1-this.$252,_3=this.$26b();return _3[_2]}
,isc.A.getColumnSize=function isc_GridRenderer_getColumnSize(_1){if((this.fixedFieldWidths&&!this.autoSize)||(_1<this.$254||_1>this.$255))
{return this.getColumnWidth(_1)}
var _2=_1-this.$254,_3=this.getColumnSizes();return _3[_2]}
,isc.A.$29n=function isc_GridRenderer__getUndrawnHeight(){return this.$252*this.getAvgRowHeight()}
,isc.A.$26b=function isc_GridRenderer__getDrawnRowHeights(){if(this.$8s!=null)return this.$8s;var _1=this.$8s=[];var _2=this.getTableElement();if(!_2||!_2.rows){delete this.$8s;return _1}
var _3=this.getDrawnRows(),_4=_3[1]-_3[0]+1,_5=false;for(var _6=0;_6<=_4;_6++){var _7=this.cacheDOM?this.getTableElement(_6+this.$252):_2.rows[_6];if(_7){var _8=isc.Browser.isSafari&&isc.Browser.safariVersion<500;if(this.getRowSpan&&this.fullRowSpans){_1[_6]=_7.offsetHeight;_5=true;continue}
var _9=(_8&&(this.fixedRowHeights==false||(this.shouldFixRowHeight!=null&&this.shouldFixRowHeight(this.getCellRecord(_6),_6)==false))),_10,_11=[];if(!_8||!_9){_10=_7.cells[_7.cells.length-1]}else{for(var k=0;k<_7.cells.length;k++){_11[k]=_7.cells[k]}}
if(_9){_1[_6]=0;for(var _13=0;_13<_11.length;_13++){var _14=_11[_13],_15=_14.offsetHeight;var _16=parseInt(_14.style?_14.style.height:null);if(isc.Browser.isStrict){if(this.cellPadding)_16+=this.cellPadding;_16+=isc.Element.$yo(_14.className)}
if(isc.isA.Number(_16)&&_16>_15)
_15=_16;if(_15>_1[_6])_1[_6]=_15}
_1[_6]+=this.cellSpacing}else if(_10){if(!_8){_1[_6]=_10.offsetHeight}else{var _17=parseInt(_10.height);if(_17!=null&&isc.isA.Number(_17)){if(isc.Browser.isStrict){_17+=isc.Element.$ym(_10.className)}}else{_17=_10.offsetHeight||0}
_1[_6]=_17}
_1[_6]+=this.cellSpacing}}
var _15=_1[_6];if(_15!=0&&_15!=null)_5=true}
_1[0]+=this.cellSpacing;if(!_5){this.logWarn("row heights not yet available; returning all zeroes");this.$8s=null}
if(isc.Browser.isSafari&&!isc.Page.isLoaded())this.$8s=null;return _1}
,isc.A.getColumnSizes=function isc_GridRenderer_getColumnSizes(){if(this.$29o!=null)return this.$29o;if(!this.isDrawn())return this.$26a.duplicate()||[];if(this.fixedColumnWidths&&isc.Browser.version>=5){return(this.$29o=this.$26a.duplicate())}else{var _1=this.$29o=[];var _2=this.getTableElement(this.$252);if(_2==null){this.$29o=_1.concat(this.$26a);return this.$29o}
var _3=(isc.Browser.isMac?this.cellSpacing:0);for(var _4=0;_4<this.fields.length;_4++){var _5;if(this.showColumnsSeparately){_5=this.getTableElement(this.$252,_4)}else if(!(isc.Browser.isSafari&&isc.Browser.safariVersion<125)){_5=_2.cells[_4]}
if(_5){_1[_4]=_5.offsetWidth+_3}else{_1[_4]=this.$26a[_4]}}
this.innerWidth=this.getTableElement().offsetWidth;return _1}}
,isc.A.getEventRow=function isc_GridRenderer_getEventRow(_1){if(this.isEmpty())return-2;if(_1==null)_1=this.getOffsetY();if(this.startSpace)_1-=this.startSpace;var _2=this.$29n();if(_1<=_2)return Math.floor(_1/ this.getAvgRowHeight());var _3=_1-_2,_4=this.$26b();var _5=this.inWhichPosition(_4,_3),_6;if(_5>=0){_6=this.$252+_5}else{var _7=_3-_4.sum();_6=this.$253+1+Math.floor(_7/ this.getAvgRowHeight());if(_6>=this.getTotalRows())_6=-2}
return _6}
,isc.A.getEventColumn=function isc_GridRenderer_getEventColumn(_1){var _2=this.getColumnSizes();if(_1==null)_1=this.getOffsetX();return this.inWhichPosition(_2,_1,this.getTextDirection())}
,isc.A.getFocusRow=function isc_GridRenderer_getFocusRow(){return 0}
,isc.A.getFocusCol=function isc_GridRenderer_getFocusCol(){return 0}
,isc.A.getNativeFocusRow=function isc_GridRenderer_getNativeFocusRow(){var _1=this.$86b;if(_1==null)_1=this.getFocusRow();var _2=this.getDrawnRows();if(_2!=null&&_2.length>0&&(_1==null||_1<_2[0]||_1>_2[1])){_1=this.$27x()[0];if(this.getRowTop(_1)<this.getScrollTop())_1+=1}
return _1}
,isc.A.getNearestRowToEvent=function isc_GridRenderer_getNearestRowToEvent(){var _1=this.getEventRow();if(_1<0){var _2=this.getVisibleRows();if(_1==-1)return _2[0];if(_1==-2)return _2[1]}
return _1}
,isc.A.getNearestColToEvent=function isc_GridRenderer_getNearestColToEvent(){var _1=this.getEventColumn();if(_1<0){var _2=this.getVisibleColumns();if(_1==-1)return _2[0];if(_1==-2)return _2[1]}
return _1}
,isc.A.$27x=function isc_GridRenderer__getViewportFillRows(){var _1=Math.floor(this.getScrollTop()/this.getAvgRowHeight()),_2=_1+Math.ceil(this.getViewportHeight()/this.cellHeight);if(this.startSpace){var _3=Math.floor(this.startSpace/ this.getAvgRowHeight());_1=Math.max(0,_1-_3);_2=Math.max(0,_2-_3)}
var _4=this.virtualScrolling&&!this.fixedRowHeights&&this.$27y!=null;if(_4){if(_1==0&&_2>=(this.getTotalRows()-1))_4=false}
if(!_4)return[_1,_2];var _5=this.$27y;if(this.$27z<0)_5+=Math.floor(this.$27z/ this.cellHeight);if(_5<0)_5=0;var _6=_5+Math.ceil(this.getViewportHeight()/this.cellHeight);return[_5,_6]}
,isc.A.getAvgRowHeight=function isc_GridRenderer_getAvgRowHeight(){return this.fixedRowHeights?this.cellHeight:Math.max(this.cellHeight,this.avgRowHeight)}
,isc.A.getVisibleRows=function isc_GridRenderer_getVisibleRows(){var _1=this.getScrollTop();var _2=[this.getEventRow(_1),this.getEventRow(_1+this.getInnerHeight())];if(_2[1]==-2){var _3=this.getTotalRows();if(_3==0){_2[0]=-1;_2[1]=-1}else{_2[1]=this.getTotalRows()-1}}
return _2}
,isc.A.getVisibleColumns=function isc_GridRenderer_getVisibleColumns(){var _1=this.$26a;if(this.overflow==isc.Canvas.VISIBLE)return[0,_1.length-1];var _2=this.getScrollLeft();if(this.isRTL()){var _3=this.getScrollWidth()-this.getInnerWidth(),_2=_3-_2}
var _4=this.inWhichPosition(_1,_2),_5=this.inWhichPosition(_1,_2+this.getInnerWidth());if(_5==-2)_5=this.$26a.length-1;return[_4,_5]}
,isc.A.getDrawnRows=function isc_GridRenderer_getDrawnRows(){if(this.cacheDOM)return this.getVisibleRows();return[this.$252,this.$253]}
,isc.A.shouldShowRollOver=function isc_GridRenderer_shouldShowRollOver(_1,_2){return(this.showRollOver&&!this.$28j)}
,isc.A.updateRollOver=function isc_GridRenderer_updateRollOver(_1,_2){this.setRowStyle(_1,null,(this.useCellRollOvers?_2:null))}
,isc.A.startHover=function isc_GridRenderer_startHover(){}
,isc.A.mouseMove=function isc_GridRenderer_mouseMove(_1,_2){if(this.$29p())return;var _3=this.getEventRow(),_4=this.getEventColumn();var _5=(_3>=0&&_4>=0&&this.cellIsEnabled(_3,_4));var _6=this.lastOverRow,_7=this.lastMouseOverRow,_8=this.lastOverCol,_9=this.lastMouseOverCol;if(!(_3==_6&&_4==_8)){if(_6!=null&&_8!=null){this.lastOverRow=null;this.lastOverCol=null;if(_3!=_6||_4<0||this.useCellRollOvers){this.updateRollOver(_6,_8,_5)}}
if(_5){this.lastOverRow=_3;this.lastOverCol=_4;if(_6!=_3||this.useCellRollOvers){if(this.shouldShowRollOver(_3,_4)){this.updateRollOver(_3,_4)}}}}
if(!(_3==_7&&_4==_9)){if(_7!=null&&_9!=null){this.lastMouseOverRow=null;this.lastMouseOverCol=null;if((_3!=_7||_4<0||this.hoverByCell)&&this.getCanHover()&&!this.keepHoverActive)
{this.stopHover()}
var _10=this.getCellRecord(_7,_9);if(this.cellOut){this.cellOut(_10,_7,_9)}
if(_3!=_7&&this.rowOut){this.rowOut(_10,_7,_9)}}
if(_5){this.lastMouseOverRow=_3;this.lastMouseOverCol=_4;if(_3!=_7||this.hoverByCell){if(this.getCanHover()){isc.Hover.setAction(this,this.$29q,[_3,_4],this.hoverDelay)}}
if(this.cellOver){this.cellOver(this.getCellRecord(_3,_4),_3,_4)}
if(_3!=_7&&this.rowOver){this.rowOver(this.getCellRecord(_3,_4),_3,_4)}}}
if(_3>=0&&_4>=0){if(this.cellMove){this.cellMove(this.getCellRecord(_3,_4),_3,_4)}
if(this.rowMove){this.rowMove(this.getCellRecord(_3,_4),_3,_4)}}}
,isc.A.$29p=function isc_GridRenderer__suppressEventHandling(){if(this.$28j!=null)return true;return false}
,isc.A.mouseOut=function isc_GridRenderer_mouseOut(){var _1=isc.EH.getTarget();if(this.$29a){var _2=this.$29a;for(var i=0;i<_2.length;i++){if(_2[i].contains(_1,true)){return}}}
if(_1==this&&!isc.EH.getDragTarget()){return}
if(this.getCanHover())this.stopHover();if(this.lastOverRow!=null&&this.lastOverCol!=null){var _4=this.lastOverRow,_5=this.lastOverCol;this.lastOverRow=null;this.lastOverCol=null;if(this.shouldShowRollOver(_4,_5)){this.updateRollOver(_4,_5)}}
if(this.lastMouseOverRow!=null&&this.lastMouseOverCol!=null){var _4=this.lastMouseOverRow,_5=this.lastMouseOverCol,_6=this.getCellRecord(_4,_5);this.lastMouseOverRow=null;this.lastMouseOverCol=null;if(this.cellOut){this.cellOut(_6,_4,_5)}
if(this.rowOut){this.rowOut(_6,_4,_5)}}}
,isc.A.$29q=function isc_GridRenderer__cellHover(_1,_2){var _3=this.getCellRecord(_1,_2);var _4;if(this.cellHover&&this.cellHover(_3,_1,_2)==false)_4=false;if(this.rowHover&&this.rowHover(_3,_1,_2)==false)_4=false;if(_4==false)return;if(this.showHover)this.$29r(_3,_1,_2)}
,isc.A.$29r=function isc_GridRenderer__showHover(_1,_2,_3){var _4=this.$wc();var _5=this.$784(_1,_2,_3);if(!_5)_5=this.cellHoverHTML(_1,_2,_3);isc.Hover.show(_5,_4,this.cellHoverBoundary(_2,_3),this.getHoverTarget())}
,isc.A.$784=function isc_GridRenderer__getCellHoverComponent(_1,_2,_3){}
,isc.A.getHoverTarget=function isc_GridRenderer_getHoverTarget(){return this}
,isc.A.cellHoverHTML=function isc_GridRenderer_cellHoverHTML(_1,_2,_3){return null}
,isc.A.getCellHoverComponent=function isc_GridRenderer_getCellHoverComponent(_1,_2,_3){return null}
,isc.A.cellHoverBoundary=function isc_GridRenderer_cellHoverBoundary(_1,_2){return null}
,isc.A.showContextMenu=function isc_GridRenderer_showContextMenu(){if(this.$29p())return false;var _1=this.getEventRow(),_2=this.getEventColumn();var _3=isc.EH.isKeyEvent();if(_3){_1=this.getFocusRow(),_2=this.getFocusCol()}
if(_1>=0&&_2>=0&&this.cellIsEnabled(_1,_2)){var _4=this.getCellRecord(_1,_2),_5;if(this.cellContextClick)
if(this.cellContextClick(_4,_1,_2)==false)_5=false;if(this.rowContextClick)
if(this.rowContextClick(_4,_1,_2)==false)_5=false;if(this.recordContextClick)
if(this.recordContextClick(_4,_1,_2)==false)_5=false;if(_5==false)return false}
return this.Super("showContextMenu")}
,isc.A.setSelection=function isc_GridRenderer_setSelection(_1){this.selection=_1;if(this.selection.isA("CellSelection")){this.observe(this.selection,"selectionChanged","observer.$29s(observed.changedCells)")}else{this.observe(this.selection,"setSelected","observer.$895(observed);")}}
,isc.A.clearSelection=function isc_GridRenderer_clearSelection(){if(this.selection){if(this.isObserving(this.selection,"selectionChanged"))
this.ignore(this.selection,"selectionChanged");if(this.isObserving(this.selection,"setSelected"))
this.ignore(this.selection,"setSelected");delete this.selection}}
,isc.A.$29s=function isc_GridRenderer__cellSelectionChanged(_1){if(this.cellSelectionChanged){if(this.cellSelectionChanged(_1)==false)return false}
this.refreshCellStyles(_1)}
,isc.A.$895=function isc_GridRenderer__setSelectedObservation(_1){var _2=false;if(!!_1.lastSelectionPreviousState!=!!_1.lastSelectionState){_2=true}else if(_1.lastSelectionState&&(!!_1.lastSelectionPartialValue!=!!_1.lastSelectionPreviousPartialValue))
{_2=true}
if(_2){this.$29t(_1.lastSelectionItem,!!_1.lastSelectionState,_1.cascadeSyncOnly)}}
,isc.A.$29t=function isc_GridRenderer__rowSelectionChanged(_1,_2,_3){if(!_3){if(this.handleSelectionChanged(_1,_2)==false){return false}}
var _4=this.selection,_5=_4.lastSelectionItem,_6=_4.data.indexOf(_5,this.$252,this.$253);if(_6==-1)_6=_4.data.indexOf(_5);if(_6==-1)return;this.updateRowSelection(_6)}
,isc.A.handleSelectionChanged=function isc_GridRenderer_handleSelectionChanged(_1,_2){if(this.selectionChanged)return this.selectionChanged(_1,_2)}
,isc.A.updateRowSelection=function isc_GridRenderer_updateRowSelection(_1){this.setRowStyle(_1)}
,isc.A.selectionEnabled=function isc_GridRenderer_selectionEnabled(){return this.selection!=null}
,isc.A.canSelectRecord=function isc_GridRenderer_canSelectRecord(_1){return(_1!=null&&_1[this.recordCanSelectProperty]!==false)}
,isc.A.mouseDown=function isc_GridRenderer_mouseDown(){if(this.$29p())return;var _1=this.getEventRow(),_2=this.getEventColumn();if(!(_1>=0&&_2>=0))return;if(!this.cellIsEnabled(_1,_2))return false;this.$29u=_1;this.$29v=_2;this.$723=isc.EH.getX();this.$724=isc.EH.getY();var _3=this.getCellRecord(_1,_2);if(!isc.EH.rightButtonDown()){return this.$29w(_3,_1,_2)}else{return this.$29x(_3,_1,_2)}}
,isc.A.rightMouseDown=function isc_GridRenderer_rightMouseDown(){return this.mouseDown()}
,isc.A.$29w=function isc_GridRenderer__cellMouseDown(_1,_2,_3){var _4;if(this.cellMouseDown&&(this.cellMouseDown(_1,_2,_3)==false))_4=false;if(this.rowMouseDown&&(this.rowMouseDown(_1,_2,_3)==false))_4=false;if(this.recordMouseDown&&this.recordMouseDown(_2,_3)==false)_4=false;if(_4==false)return false;this.selectOnMouseDown(_1,_2,_3)}
,isc.A.selectOnMouseDown=function isc_GridRenderer_selectOnMouseDown(_1,_2,_3){if(!this.selectionEnabled())return true;if(_2>=0&&_3>=0&&this.canSelectRecord(_1)&&!this.$89v()){this.$828=true;var _4=this.selection.selectOnMouseDown(this,_2,_3);if(_4&&this.fireSelectionUpdated()){this.fireSelectionUpdated()}}}
,isc.A.$89v=function isc_GridRenderer__shouldSelectOnMouseUp(){if(isc.EH.dragTarget!=null&&isc.EH.dragOperation==isc.EH.DRAG_SCROLL){return true}
return false}
,isc.A.$29x=function isc_GridRenderer__cellRightMouseDown(_1,_2,_3){if(this.canSelectOnRightMouse)this.selectOnRightMouseDown(_1,_2,_3)}
,isc.A.selectOnRightMouseDown=function isc_GridRenderer_selectOnRightMouseDown(_1,_2,_3){this.selectOnMouseDown(_1,_2,_3)}
,isc.A.mouseUp=function isc_GridRenderer_mouseUp(){if(this.$29p())return;var _1=this.getEventRow(),_2=this.getEventColumn();if(!(_1>=0&&_2>=0))return;if(!this.cellIsEnabled(_1,_2))return;var _3=this.getCellRecord(_1,_2);var _4;if(this.cellMouseUp&&(this.cellMouseUp(_3,_1,_2)==false))_4=false;if(this.rowMouseUp&&(this.rowMouseUp(_3,_1,_2)==false))_4=false;if(this.recordMouseUp&&this.recordMouseUp(_1,_2)==false)_4=false;if(_4==false)return _4;this.selectOnMouseUp(_3,_1,_2)}
,isc.A.selectOnMouseUp=function isc_GridRenderer_selectOnMouseUp(_1,_2,_3){if(!this.selectionEnabled())return true;if(_2>=0&&_3>=0){if(this.$89v()){this.selection.selectOnMouseDown(this,_2,_3)}
this.grid.$67j=true;var _4=this.selection.selectOnMouseUp(this,_2,_3);this.grid.$67j=null;if(_4){this.markForRedraw();if(this.$828){if(this.fireSelectionUpdated)this.fireSelectionUpdated();if(this.grid.getCurrentCheckboxField()!=null){this.grid.updateCheckboxHeaderState()}
this.$828=null}}}}
,isc.A.click=function isc_GridRenderer_click(){if(this.$29p())return;var _1=this.getEventRow(),_2=this.getEventColumn();return this.$29y(_1,_2)}
,isc.A.$29y=function isc_GridRenderer__rowClick(_1,_2){this.$29z=this.$290=null;var _3=this.$29u;if(_3!=null&&_1!=_3){if(isc.EH.getX()==this.$723){_1=this.$29u}else{return}}
if(isc.EH.getY()==this.$724){_2=this.$29v}
if(!(_1>=0&&_2>=0))return;if(!this.cellIsEnabled(_1,_2))return false;this.$29z=_1;var _4=this.getCellRecord(_1,_2),_5;if(!this.$22n(_4,_1,_2))_5=false;if(this.rowClick&&(this.rowClick(_4,_1,_2)==false))
_5=false;this.$29u=null;return _5}
,isc.A.$22n=function isc_GridRenderer__cellClick(_1,_2,_3){if(this.$29v!=_3){this.$290=null;return}
this.$290=_3;this.$291=null;return!(this.cellClick&&(this.cellClick(_1,_2,_3)==false))}
,isc.A.doubleClick=function isc_GridRenderer_doubleClick(){if(this.$29p())return;var _1=this.getEventRow(),_2=this.getEventColumn();if(!(_1>=0&&_2>=0))return;if(!this.cellIsEnabled(_1,_2))return false;if(_1!=this.$29z){return this.$29y(_1,_2)}
var _3=this.getCellRecord(_1,_2),_4;if(_2!=this.$290){_4=this.$22n(_3,_1,_2)}else if(this.cellDoubleClick&&(this.cellDoubleClick(_3,_1,_2)==false))
{_4=false}
if(this.rowDoubleClick&&(this.rowDoubleClick(_3,_1,_2)==false))
_4=false;this.$29u=this.$29v=null;this.$29z=this.$290=null;if(_4==false)return false}
,isc.A.dragMove=function isc_GridRenderer_dragMove(){if(this.$29p()||!this.selectionEnabled()||!this.canDragSelect)
return true;var _1=this.getNearestRowToEvent(),_2=this.getNearestColToEvent();this.selection.selectOnDragMove(this,_1,_2)}
,isc.A.dragStop=function isc_GridRenderer_dragStop(){this.fireSelectionUpdated()}
,isc.A.noSnapDragOffset=function isc_GridRenderer_noSnapDragOffset(_1){return this.snapToCells}
,isc.A.getHSnapPosition=function isc_GridRenderer_getHSnapPosition(_1,_2){if(!this.snapToCells){return this.Super("getHSnapPosition",arguments)}
var _3=this.ns.EH,_4=_2||this.snapHDirection,_5=this.snapHGap?Math.floor(_1/ this.snapHGap):this.getEventColumn(_1),_6=this.snapHGap?(_5*this.snapHGap):this.getColumnLeft(_5),_7=this.snapHGap?_6+this.snapHGap:this.getColumnLeft(_5)+this.getColumnSize(_5),_8=this.snapHGap?_5+1:this.getEventColumn(_7+1),_9;if(_8>=0){_9=this.snapHGap?_8*this.snapHGap:this.getColumnLeft(_8)}else{_9=_6}
var _10=_6+(this.snapHGap?this.snapHGap:this.getColumnSize(_5))/2;if(this.snapInsideBorder){var _11=isc.Element.$tl(this.baseStyle)
var _12=isc.Element.$tm(this.baseStyle)
_6+=_11;_7-=_12;_9+=_11}
if(_3.dragOperation==_3.DRAG_RESIZE){var _13=isc.EH.resizeEdge.contains("L");return _13?_6:_7}else{if(_4==isc.Canvas.BEFORE){return _6}else if(_4==isc.Canvas.AFTER){return _9}else{if(_1<=_10){return _6}else{return _9}}}}
);isc.evalBoundary;isc.B.push(isc.A.getVSnapPosition=function isc_GridRenderer_getVSnapPosition(_1,_2){if(!this.snapToCells){return this.Super("getVSnapPosition",arguments)}
var _3=this.ns.EH,_4=_2||this.snapVDirection,_5=this.snapVGap?Math.floor(_1/ this.snapVGap):this.getEventRow(_1),_6=this.snapVGap?(_5*this.snapVGap):this.getRowTop(_5),_7=this.snapVGap?_6+this.snapVGap:this.getRowTop(_5)+this.getRowSize(_5),_8=this.snapVGap?_5+1:this.getEventRow(_7+1),_9;if(_8>=0){_9=this.snapVGap?_8*this.snapVGap:this.getRowTop(_8)}else{_9=_6}
var _10=_6+(this.snapVGap?this.snapVGap:this.getRowSize(_5))/2;if(this.snapInsideBorder){var _11=isc.Element.$tn(this.baseStyle)
var _12=isc.Element.$to(this.baseStyle)
_6+=_11;_7-=_12;_9+=_11}
if(_3.dragOperation==_3.DRAG_RESIZE){var _13=isc.EH.resizeEdge.contains("T");return _13?_6:_7}else{if(_4==isc.Canvas.BEFORE){return _6}else if(_4==isc.Canvas.AFTER){return _9}else{if(_1<=_10)return _6;else return _9}}}
,isc.A.getColumnAutoSize=function isc_GridRenderer_getColumnAutoSize(_1,_2,_3){if(this.getTotalRows()==0){return null}
var _4=this.$292=this.$292||isc.Canvas.create({columnSizer:true,overflow:"hidden",top:-1000,width:1,height:1,autoDraw:false,_generated:true});var _5=this.autoFit,_6=this.wrapCells;this.autoFit=true;this.wrapCells=false;if(_2==null||_3==null){var _7=this.getDrawArea();_2=_7[0];_3=_7[1]+1}
this.$92p=true;_4.setContents(this.getTableHTML(_1,_2,_3,true));delete this.$92p;this.autoFit=_5;this.wrapCells=_6;if(!_4.isDrawn()){_4.draw()}else{if(_4.isDirty())_4.redraw();if(_4.$uu)_4.adjustOverflow("Check autoFit column sizing")}
var _8;if(isc.isA.Array(_1)){var _9,_10=_4.getHandle().childNodes;for(var i=0;i<_10.length;i++){if(_10[i].tagName.toLowerCase()=="table"){_9=_10[i];break}}
if(_9&&_9.rows[0]){var _12=_9.rows[0],_13=_12.cells;_8=[];for(var i=0;i<_13.length;i++){_8[i]=_13[i].clientWidth}}}else{_8=_4.getScrollWidth()}
return _8}
,isc.A.redraw=function isc_GridRenderer_redraw(_1,_2,_3,_4){this.$29c();this.invokeSuper(isc.GridRenderer,"redraw",_1,_2,_3,_4);delete this.$50v}
,isc.A.modifyContent=function isc_GridRenderer_modifyContent(){if(!this.$28c)this.$29d();if(this.$27y!=null){this.$80f=true;this.$270("scrollToRow in modifyContent");this.$80f=null;var _1=this.getTableElement();if(_1)_1.style.visibility="inherit"}
if(this.$60s){var _2=this.$26b().sum();if(_2<this.getViewportHeight()){this.$515=0;var _3=isc.Element.get(this.getID()+"$284"),_4=this.$515+(this.endSpace||0);if(_3){if(_4==0)_3.style.display="none"
else _3.style.display="";_3.style.height=_4+"px"}}
var _5=this.getVisibleRows(),_6=Math.max(1,_5[1]-_5[0]),_7=_6/ this.getTotalRows(),_8=this.getViewportRatio(true);if(isc.isA.Number(_7)&&((_8==1&&_7<1)||_8/ _7>1.25))
{this.$272=Math.max(this.cellHeight,Math.round(this.getViewportHeight()/_6))}}}
,isc.A.setStartSpace=function isc_GridRenderer_setStartSpace(_1){if(!isc.isA.Number(_1)||_1==this.startSpace)return;var _2=this.startSpace&&this.startSpace>_1;this.startSpace=_1;if(!this.isDrawn())return;var _3=_1+this.$514,_4=isc.Element.get(this.getID()+"$28s");if(_4){if(_3==0)_4.style.display="none";else _4.style.display="";if(this.$79b){_4.style.height=_3+"px"}
if(!_2||!this.$79b){_4.innerHTML=isc.Canvas.spacerHTML(1,_3)}
this.$t6()}}
,isc.A.setEndSpace=function isc_GridRenderer_setEndSpace(_1){if(!isc.isA.Number(_1)||_1==this.endSpace)return;var _2=this.endSpace&&this.endSpace>_1;this.endSpace=_1;if(!this.isDrawn())return;var _3=_1+this.$515,_4=isc.Element.get(this.getID()+"$284");if(_4){if(_3==0)_4.style.display="none";else _4.style.display="";if(this.$79b)_4.style.height=_3+"px";if(!_2||!this.$79b){_4.innerHTML=isc.Canvas.spacerHTML(1,_3)}
this.$t6()}}
,isc.A.setLeftSpace=function isc_GridRenderer_setLeftSpace(_1){if(this.leftSpace==_1)return;this.leftSpace=_1;this.redraw()}
,isc.A.setRightSpace=function isc_GridRenderer_setRightSpace(_1){if(this.rightSpace==_1)return;this.rightSpace=_1;this.redraw()}
,isc.A.clear=function isc_GridRenderer_clear(){this.Super("clear",arguments);this.$29j();delete this.$50v}
,isc.A.$29j=function isc_GridRenderer__clearTableCache(){this.$29g=null;this.$29f=null;delete this.$29o;delete this.$293;delete this.$8s;this.$250=false}
);isc.B._maxIndex=isc.C+189;isc.GridRenderer.$294={getCellRecord:"rowNum,colNum",getCellValue:"record,rowNum,colNum,gridBody",findRowNum:"record",findColNum:"record",getBaseStyle:"record,rowNum,colNum",getCellStyle:"record,rowNum,colNum",getCellCSSText:"record,rowNum,colNum",cellIsEnabled:"rowNum,colNum",getRowHeight:"record,rowNum",getRowSpan:"record,rowNum,colNum",cellOut:"record,rowNum,colNum",cellOver:"record,rowNum,colNum",rowOut:"record,rowNum,colNum",rowOver:"record,rowNum,colNum",cellMove:"record,rowNum,colNum",rowMove:"record,rowNum,colNum",cellContextClick:"record,rowNum,colNum",rowContextClick:"record,rowNum,colNum",recordContextClick:"record,recordNum,fieldNum",cellMouseDown:"record,rowNum,colNum",rowMouseDown:"record,rowNum,colNum",recordMouseDown:"recordNum,fieldNum",cellMouseUp:"record,rowNum,colNum",rowMouseUp:"record,rowNum,colNum",recordMouseUp:"recordNum,fieldNum",selectOnMouseDown:"record,rowNum,colNum",selectOnRightMouseDown:"record,rowNum,colNum",selectOnMouseUp:"record,rowNum,colNum",cellClick:"record,rowNum,colNum",cellDoubleClick:"record,rowNum,colNum",rowClick:"record,rowNum,colNum",rowDoubleClick:"record,rowNum,colNum",cellHover:"record,rowNum,colNum",rowHover:"record,rowNum,colNum",cellHoverHTML:"record,rowNum,colNum",getCellHoverComponent:"record,rowNum,colNum",selectionChanged:"record,state",selectionUpdated:"record,recordList",cellSelectionChanged:"cellList",getRowElementId:"rowNum,physicalRowNum",getCellElementId:"rowNum,physicalRowNum,colNum,physicalColNum",shouldFixRowHeight:"record,rowNum",updateEmbeddedComponentZIndex:"component",updateEmbeddedComponentCoords:"component,record,rowNum,colNum",getRowRole:"rowNum,record",getRowAriaState:"rowNum,record",getCellRole:"rowNum,colNum,record",getCellAriaState:"rowNum,colNum,record"};isc.GridRenderer.registerStringMethods(isc.GridRenderer.$294);isc.ClassFactory.defineClass("ListGrid","VLayout","DataBoundComponent");isc.addGlobal("ListViewer",isc.ListGrid);isc.defineClass("GridBody",isc.GridRenderer);isc.A=isc.GridBody.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.adjustOverflowWhileDirty=false;isc.A.expandEmptyMessageToMatchFields=true;isc.B.push(isc.A.adjustOverflow=function isc_GridBody_adjustOverflow(_1,_2,_3,_4,_5){if(this.$80o)return;var _6=this.grid;if(_6==null)return this.Super("adjustOverflow",arguments);var _7=_6.data,_8=false;;if(isc.isA.ResultSet(_7)&&!_7.lengthIsKnown()){if(_6.emptyMessageHeight==null){return this.invokeSuper(isc.GridBody,"adjustOverflow",_1,_2,_3,_4,_5)}
_8=true}
var _9=(this.autoFitData=="both"),_10=_9,_11=_6&&_6.frozenFields!=null,_12=_11&&_6&&(_6.frozenBody==this);if(!_9)_9=(this.autoFitData=="vertical");if(!_10)_10=(this.autoFitData=="horizontal");if(_10||_9){var _13,_14,_15,_16,_17,_18,_19;if(_9){var _20=this.grid.getAutoFitMinBodyHeight();_13=_20;var _21=_8?0:this.getTotalRows(),_22=_21;_15=0;if(this.autoFitMaxRecords){_22=Math.min(_22,this.autoFitMaxRecords)}
if(_22>0){var _23=this.$26b();var _24=this.$252,_25=this.$253;if(this.$252==null){_24=_22;_25=_22}
if(_24>0){_24=Math.min(_24,_22);for(var i=0;i<_24;i++){_15+=this.getRowHeight?this.getRowHeight(i):this.cellHeight}}
if(_25<_22-1){for(var i=_25+1;i<_22;i++){_15+=this.getRowHeight?this.getRowHeight(i):this.cellHeight}}
_25=Math.min(_25,_22-1);for(var i=0;i<=_25-_24;i++){_15+=_23[i]}
_17=_21>_22;var _27=this.getAutoFitMaxHeight();if(_27&&_15>_27){_15=_27;_17=true}}else{if(this.grid.emptyMessageHeight!=null){_15=this.grid.emptyMessageHeight}}
if(this.autoFitExtraRecords&&this.autoFitExtraRecords>0){var _28=Math.round(this.autoFitExtraRecords*this.cellHeight);_15+=_28}}else{_17=this.getScrollHeight()>this.getHeight()}
if(_10&&!_12){var _14=this.grid.getInnerWidth(),_29;if(_11){var _30=this.grid.getFrozenSlots(this.grid.$26a);_29=_30.sum();_14-=_29}
var _31=this.getColumnSizes(),_32=_31.sum();if(this.autoFitMaxColumns){var _33=this.autoFitMaxColumns;if(_11){_33=Math.max(1,_33-this.grid.frozenFields.length)}
if(_33<_31.length){_31=_31.slice(0,this.autoFitMaxColumns)}}
var _34=_31.sum();if(this.autoFitMaxWidth){var _35=this.autoFitMaxWidth;if(_11)_35=Math.max(20,_35-_29);_34=Math.min(_35,_34)}
_16=(this.overflow==isc.Canvas.SCROLL)?true:(this.overflow==isc.Canvas.AUTO)?(_32>Math.max(_14,_34)):false}else{_16=this.overflow==isc.Canvas.SCROLL?true:this.overflow==isc.Canvas.AUTO?this.getScrollWidth()>this.getWidth():false}
if(_9&&_15!=null){_15+=this.getVBorderPad()+this.getVMarginSize();if(_16){_15+=this.getScrollbarSize();var _27=this.getAutoFitMaxHeight()
if(_27&&_15>_27){_15=_27}}
if(_15>_13){_13=_15;this.$70u=true}else{if(this.$70u)delete this.$70u}}
if(_10&&!_12&&_34!=null){_34+=this.getHBorderPad()+this.getHMarginSize();if(_17||this.alwaysShowVScrollbar){_34+=this.getScrollbarSize();if(this.autoFitMaxWidth){var _35=this.autoFitMaxWidth;if(_11)_35=Math.max(20,_35-_29);_34=Math.min(_35,_34)}}
if(_34>_14){_14=_34;this.$70v=true}else{if(this.$70v)delete this.$70v}}
this.$80o=true;_19=this.getDelta(this.$o5,_13,this.getHeight());_18=this.getDelta(this.$o6,_14,this.getWidth());delete this.$80o;if(_19!=null||_18!=null){this.resizeBy(_18,_19,null,null,true)}
if(_18!=null||(_12&&_10)){var _36=this.grid,_37=(_14-(_17?_36.getScrollbarSize():0)),_38=_37;if(_11&&_36.headerLayout){if(_12){_38=this.getWidth()+_36.body.getWidth();if(_36.autoFitMaxWidth!=null&&(_38+_36.getHBorderPad()+_36.getHMarginSize()>_36.autoFitMaxWidth))
{return _36.body.adjustOverflow()}
_38-=(_36.body.vscrollOn?_36.getScrollbarSize():0)}else{_38=_37+_36.frozenBody.getWidth()}
_36.headerLayout.setWidth(_38)}
if(!_12){var _39=_36.header;if(_39&&_39.isDrawn()){_39.setWidth(_37)}}}}
var _40=(!_9&&this.$70u),_41=(!_10&&this.$70v);if(_40||_41){delete this.$70u;delete this.$70v;var _42=_40?this.grid.getAutoFitMinBodyHeight():null,_43=_41?(!_11?this.grid.getInnerWidth():(this.grid.getInnerWidth()-this.grid.frozenBody.getWidth())):null;this.resizeTo(_14,_13);this.grid.$45m("autoFitData mode changed")}
var _44=this.invokeSuper(isc.GridBody,"adjustOverflow",_1,_2,_3,_4,_5);if(!_12)this.grid.bodyOverflowed();return _44}
,isc.A.getAutoFitMaxHeight=function isc_GridBody_getAutoFitMaxHeight(){return this.grid?this.grid.getAutoFitMaxBodyHeight():null}
,isc.A.resizeBy=function isc_GridBody_resizeBy(_1,_2,_3,_4,_5){if(!_5){this.$1i=this.getWidth()+(_1!=null?_1:0)}
return this.invokeSuper(isc.GridBody,"resizeBy",_1,_2,_3,_4,_5)}
,isc.A.cellContextClick=function isc_GridBody_cellContextClick(_1,_2,_3){var _4=this.grid.getFieldNumFromLocal(_3,this);return this.grid.$298(_1,_2,_4)}
,isc.A.$29y=function isc_GridBody__rowClick(_1,_2){if(!this.grid)return;var _3;var _4=this.grid.getFieldNumFromLocal(_2,this);var _5=this.grid.getField(_4)
if(_5&&_5.isRemoveField){this.grid.removeRecordClick(_1,_2);_3=false}else{_3=this.Super("$29y",arguments)}
return _3}
,isc.A.$784=function isc_GridBody__getCellHoverComponent(_1,_2,_3){if(this.grid&&isc.isA.ListGrid(this.grid)){var _4=this.grid.getFieldNumFromLocal(_3,this);return this.grid.$784(_1,_2,_4)}}
,isc.A.getInnerHTML=function isc_GridBody_getInnerHTML(){this.grid.bodyDrawing(this);return this.Super("getInnerHTML",arguments)}
,isc.A.getTablePrintHTML=function isc_GridBody_getTablePrintHTML(_1){var _2=_1.startRow,_3=_1.endRow,_4=_3!=null?(_3-_2):this.getTotalRows(),_5=this.printMaxRows,_6=_1.printWidths,_7=_1.printProps;var _8={target:this,methodName:"gotTablePrintHTML",printContext:_1,printCallback:_1.callback}
_1.callback=_8;if(_5<_4){this.logDebug("get table print html - breaking HTML into chunks","printing");if(_2==null)_2=_1.startRow=0;if(_3==null)_3=_1.endRow=this.getTotalRows();this.getPrintHTMLChunk(_1);return null}
var _9=this.grid.$569(_6,_7);var _10=this.getTableHTML(null,_2,_3,null,_8);this.grid.$57a(_9);return _10}
,isc.A.gotTablePrintHTML=function isc_GridBody_gotTablePrintHTML(_1,_2){var _3=_2.printCallback;if(_3){this.fireCallback(_3,"HTML,callback",[_1,_3])}}
,isc.A.getPrintHTMLChunk=function isc_GridBody_getPrintHTMLChunk(_1,_2){var _3=this.grid.$569(_1.printWidths);this.$568=true;this.$80u=true;var _4=_1.startRow,_5=_1.endRow,_6=this.printMaxRows,_7=_1.callback;this.currentPrintProperties=_1.printProps;if(!_1.html)_1.html=[];var _8=_1.chunkEndRow=Math.min(_5,(_4+_6)),_9=this.getTableHTML(null,_4,_8,null,{target:this,methodName:"gotPrintChunkHTML",printContext:_1,printCallback:_1.callback});this.grid.$57a(_3);this.$568=false;if(_9!=null){delete this.$80u;this.gotPrintChunkHTML(_9,{printContext:_1});if(_2)return _9}}
,isc.A.gotPrintChunkHTML=function isc_GridBody_gotPrintChunkHTML(_1,_2){var _3=_2.printContext,_4=_3.startRow,_5=_3.endRow,_6=_3.chunkEndRow,_7=this.printMaxRows,_8=_3.callback;_3.html.add(_1);if(_6<_5){_3.startRow=_6;return this.delayCall("getPrintHTMLChunk",[_3],0)}
if(_8!=null){var _9=_3.html.join(isc.emptyString);this.fireCallback(_8,"HTML,callback",[_9,_8])}}
,isc.A.gotComponentPrintHTML=function isc_GridBody_gotComponentPrintHTML(_1,_2){var _3=_2.context.asyncCallback,_4=_3.printContext;var _5=_4.printWidths;var _6=this.grid.$569(_5);if(this.$80u){this.$568=true}
var _1=this.Super("gotComponentPrintHTML",arguments);if(this.$568)delete this.$568;if(_1!=null){delete this.$80u}else{this.grid.$57a(_6)}}
,isc.A.getCellVAlign=function isc_GridBody_getCellVAlign(_1,_2,_3,_4){if(this.grid&&this.grid.getCellVAlign){var _5=this.grid.getFieldNumFromLocal(_4,this);return this.grid.getCellVAlign(_1,_3,_5)}}
,isc.A.getCellAlign=function isc_GridBody_getCellAlign(_1,_2,_3,_4){if(this.grid&&this.grid.getCellAlign!=null){var _5=this.grid.getFieldNumFromLocal(_4,this);return this.grid.getCellAlign(_1,_3,_5)}else return _2.cellAlign||_2.align}
,isc.A.$282=function isc_GridBody__drawRecordAsSingleCell(_1,_2,_3){var _4=this.grid;if(_4.showNewRecordRow&&_4.$299(_1))return true;return isc.GridRenderer.$b4.$282.call(this,_1,_2,_3)}
,isc.A.showSingleCellCheckboxField=function isc_GridBody_showSingleCellCheckboxField(_1){var _2=this.grid;return _2&&_2.showSingleCellCheckboxField(_1)}
,isc.A.$67p=function isc_GridBody__getSingleCellSpan(_1,_2,_3,_4){if(_2==this.$28c||!this.showSingleCellCheckboxField(_1)||(this.grid&&this.grid.frozenBody!=null&&this.grid.frozenBody!=this))
{return[_3,_4]}
return[Math.max(_3,1),_4]}
,isc.A.mouseWheel=function isc_GridBody_mouseWheel(){if(this.frozen&&this.grid!=null){var _1=this.ns.EH.lastEvent.wheelDelta;var _2=this.scrollTop+Math.round(_1*isc.Canvas.scrollWheelDelta);this.grid.body.scrollTo(null,_2,"frozenMouseWheel");return false}
return this.Super("mouseWheel",arguments)}
,isc.A.$80e=function isc_GridBody__getDrawRows(){if(this.frozen&&this.grid){var _1=this.grid;return _1.body.$80e()}
return this.Super("$80e",arguments)}
,isc.A.doneFastScrolling=function isc_GridBody_doneFastScrolling(){if(!this.frozen&&this.grid!=null&&this.grid.frozenBody!=null){var _1=this.$50z;this.Super("doneFastScrolling",arguments);if(_1){this.grid.frozenBody.$50v=true;this.grid.frozenBody.markForRedraw("Done fast scrolling on unfrozen body")}}}
,isc.A.scrollTo=function isc_GridBody_scrollTo(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;if(_1!=null){var _5=this.getScrollWidth()-this.getViewportWidth();_1=Math.max(0,Math.min(_5,_1))}
if(_2!=null){var _6=this.getScrollHeight()-this.getViewportHeight();_2=Math.max(0,Math.min(_6,_2))}
var _7=this.grid;this.invokeSuper(null,"scrollTo",_1,_2,_3,_4);var _8=this.$80h;if(!_8)_7.bodyScrolled(_1,_2,this.frozen);if(!this.isDirty()&&_7.$30a){_7.$286.itemsMoved()}}
,isc.A.addEmbeddedComponent=function isc_GridBody_addEmbeddedComponent(_1,_2,_3,_4,_5){var _6=this.invokeSuper(isc.GridBody,"addEmbeddedComponent",_1,_2,_3,_4,_5);if(_1.$57n!=null&&_1.$57n!=-1){var _4=_1.$57n;_1.$81k=this.fields[_4].name}
return _1}
,isc.A.updateHeightForEmbeddedComponents=function isc_GridBody_updateHeightForEmbeddedComponents(_1,_2,_3){if(_1&&!_1.$29a&&this.grid.showRecordComponents&&this.grid.recordComponentHeight!=null)
{var _4=this.$667(_1,_2);if(_4.allWithin&&_4.extraHeight>0){_3=Math.max(_3,_4.extraHeight)}else{_3+=_4.extraHeight}
return _3}
return this.invokeSuper(isc.GridBody,"updateHeightForEmbeddedComponents",_1,_2,_3)}
,isc.A.$667=function isc_GridBody__getExtraEmbeddedComponentHeight(_1,_2){var _3=this.invokeSuper(isc.GridBody,"$667",_1,_2);if(this.grid.showRecordComponents&&this.grid.recordComponentHeight!=null){_3.extraHeight=Math.max(_3.extraHeight,this.grid.recordComponentHeight)}
return _3}
,isc.A.$80g=function isc_GridBody__writeEmbeddedComponentSpacer(_1){if(_1&&this.grid&&this.grid.showRecordComponents&&this.grid.recordComponentHeight!=null)
{return true}
return this.invokeSuper(isc.GridBody,"$80g",_1)}
,isc.A.getAvgRowHeight=function isc_GridBody_getAvgRowHeight(){if(this.grid)return this.grid.getAvgRowHeight(this);return this.Super("getAvgRowHeight",arguments)}
,isc.A.shouldShowAllColumns=function isc_GridBody_shouldShowAllColumns(){if(this.showAllColumns){return true}
if(!this.fixedRowHeights&&!this.showAllRows){if(this.grid.canExpandRecords&&this.grid.$82v){return false}
return true}
if(this.overflow==isc.Canvas.VISIBLE){return true}
return false}
,isc.A.redraw=function isc_GridBody_redraw(_1,_2,_3,_4){this.$58p=true;var _5=this.grid;if(_5.alwaysShowEditors&&!_5.$30a){_5.startEditing(null,null,true,null,true)}
var _6=_5.$286,_7=_5.$30a,_8,_9,_10,_11,_12;_5.$69l();if(!_5.leaveScrollbarGap&&_5.predictScrollbarGap&&(this.overflow==isc.Canvas.AUTO)){var _13=this.vscrollOn,_14=!_5.isEmpty()&&(_5.getTotalRows()*_5.cellHeight)>this.getInnerHeight();if(_13!=_14){delete this.$773;_5.$45m("body redrawing with changed vertical scroll-state")}}
var _15=false;if(_7){this.logInfo("redraw with editors showing, editForm.hasFocus: "+_6.hasFocus,"gridEdit");_8=_5.getEditCol();this.$516();_12=this.$517()}else if(isc.screenReader){_15=(this.hasFocus||isc.EH.$lg==this)||(this.editForm&&this.editForm.hasFocus())}
if(this.$773!=null){var _16=this.$773;delete this.$773;_5.$45m(_16)}
var _17=this.getDrawArea();var _18=this.grid,_19=this.$74k;if(!_19)_19=this.$74k=[0,0,0,0];var _18=this.grid,_20=_18.getRecord(_17[0]),_21=_18.getRecord(_17[1]),_22=(_20&&_20!=Array.LOADING)&&(_21&&_21!=Array.LOADING);;if(_22&&!_19.equals(_17))
{if(!this.frozen){_18.$74l(_19[0],_19[1],_19[2],_19[3],this);this.$74k=_17}}
if(!(this.frozen&&(_1==this.$81l||_1=="scrolled"))){_18.updateRecordComponents()}
delete this.$316;this.invokeSuper(isc.GridBody,"redraw",_1,_2,_3,_4);delete this.$58p;if(_7){if(_12!=null&&_12.length>0){_6.removeItems(_12)}
_5.$30b(null,true,this);_5.updateEditRow(_5.getEditRow());if(_6.hasFocus||(this.$519&&isc.EH.getFocusCanvas()==null))
{this.$518(_8)}else{delete this.$519}}else{if(_6!=null){_5.$30b(null,null,this)}
if(isc.screenReader){this.$86a(this.getNativeFocusRow(),!_15)}}}
,isc.A.setHandleDisabled=function isc_GridBody_setHandleDisabled(_1){var _2=this.grid;if(this.isDrawn()&&_2&&_2.$30a){this.markForRedraw("Grid body disabled while editing")}
return this.Super("setHandleDisabled",arguments)}
,isc.A.$517=function isc_GridBody__updateEditItems(){var _1=this.grid,_2=_1.getEditForm(),_3=[],_4=_2.getItems();if(!_1.editByCell){var _5=_1.getEditRow(),_6=_1.getRecord(_5),_7=_1.getEditFormItemFieldWidths(_6);var _4=_2.getItems(),_8=_4.getProperty(this.fieldIdProperty),_9=_1.getDrawnFields(),_10=_9.getProperty(this.fieldIdProperty);var _11=_4.length==_9.length,_12=false;for(var i=0;i<_4.length;i++){var _14=_10.indexOf(_8[i]);if(_14==-1){_12=true;_3.add(_4[i])}else{_4[i].width=_7[_4[i].colNum];_4[i].$8t=null;_4[i].redrawing()}}
if(!_11||_12){var _15=_1.getEditedRecord(_5,0);for(var i=0;i<_9.length;i++){if(!_8.contains(_10[i])){var _16=_1.fields.indexOf(_9[i]);var _17=_1.getEditItem(_9[i],_6,_15,_5,_16,_7[_16]);_2.addItem(_17)}}}}
return _3}
,isc.A.$516=function isc_GridBody__storeFocusForRedraw(){var _1=this.grid,_2=_1.getEditForm(),_3=_1.getEditCol();if(_2.hasFocus){var _4=_2.getFocusSubItem();if(_4){_4.updateValue();var _5=_4;while(_4.parentItem!=null){_4=_4.parentItem}
if(!_1.canEditCell(_4.rowNum,_4.colNum)||_3!=_4.colNum){_2.blur()}else{if(_4.hasFocus){_4.rememberSelection();this.$519=[_4.$17r,_4.$17s]}
_2.$106()}}}
_2.$10u=true}
,isc.A.$518=function isc_GridBody__restoreFocusAfterRedraw(_1){var _2=this.grid,_3=_2.getEditForm(),_4=_3.getItem(_2.getEditorName(_2.getEditRow(),_1));if(_4!=null&&_4.isDrawn()){var _5=_2.body.getScrollLeft(),_6=_2.body.getScrollTop(),_7=_2.body.getViewportWidth(),_8=_2.body.getViewportHeight(),_9=_4.getRect(),_10=_9[0]<_5||_9[1]<_6||_9[0]+_9[2]>(_5+_7)||_9[1]+_9[3]>(_6+_8);if(!_10){_3.$11b(_4);if(this.$519&&this.$519[0]!=null){_4.setSelectionRange(this.$519[0],this.$519[1])}
delete this.$519}}}
,isc.A.focusAtEnd=function isc_GridBody_focusAtEnd(_1){var _2=this.grid,_3=_2?_2.getEditForm():null;if(_3){_3.focusAtEnd(_1)}else{return this.Super("focusAtEnd",arguments)}}
,isc.A.isDirty=function isc_GridBody_isDirty(_1,_2,_3){return this.invokeSuper(null,"isDirty",_1,_2,_3)||this.grid.isDirty()}
,isc.A.cellMove=function isc_GridBody_cellMove(_1,_2,_3){var _4=isc.EH.lastEvent?isc.EH.lastEvent.nativeTarget:null;if(_4&&_4.getAttribute!=null&&(_4.getAttribute("isErrorIcon")=="true"))
{if(this.grid.$79y!=null){var _5=this.grid.$79y[0],_6=this.grid.$79y[1];if(_5!=_2||_6!=_3){this.grid.$790()}}
if(this.grid.$79y==null){this.grid.$79z(_2,_3)}}else{if(this.grid.$79y!=null){this.grid.$790()}}}
,isc.A.shouldShowRollOver=function isc_GridBody_shouldShowRollOver(_1,_2,_3,_4){if(!this.grid.showRollOver||this.$28j)return false;var _5=this.grid;if(_5.$30a&&!_5.editByCell&&_1==_5.$285)return false;return true}
,isc.A.updateRollOver=function isc_GridBody_updateRollOver(_1,_2,_3){var _4=this.grid;if(_4.showRollOverCanvas){if(!_3){var _5=!(this.lastOverRow==_1&&this.lastOverCol==_2);_4.updateRollOverCanvas(_1,_2,_5)}}
this.setRowStyle(_1,null,(this.useCellRollOvers?_2:null));var _6=(this==_4.body?_4.frozenBody:_4.body);if(_6){_6.lastOverRow=this.lastOverRow;_6.lastOverCol=this.lastOverCol;_6.setRowStyle(_1,null,(this.useCellRollOvers?_2:null))}}
,isc.A.selectOnMouseDown=function isc_GridBody_selectOnMouseDown(_1,_2,_3){var _4=true,_5=this.grid.selectionAppearance,_6=(_5=="checkbox");if(_6){if((this.grid.frozenFields!=null&&this.grid.frozenBody!=this)||(this.grid.getCheckboxFieldPosition()!=_3))
{_4=false}}
if(_4){this.invokeSuper(isc.GridBody,"selectOnMouseDown",_1,_2,_3)}
if(isc.screenReader){this.$86a(_2)}}
,isc.A.mouseUp=function isc_GridBody_mouseUp(){var _1=isc.EH.getTarget();if(this.grid&&_1!=this&&this.grid.$30a){var _2=this.grid.getEditForm();while(_1!=this){if(_1.canvasItem&&_2.items.contains(_1.canvasItem)){return}}}
return this.Super("mouseUp",arguments)}
,isc.A.selectOnMouseUp=function isc_GridBody_selectOnMouseUp(_1,_2,_3){var _4=this.grid.getCheckboxFieldPosition(),_5=this.grid.selectionAppearance;if(_5!="checkbox"||(_5=="checkbox"&&_4==_3)){this.invokeSuper(isc.GridBody,"selectOnMouseUp",_1,_2,_3)}}
,isc.A.handleSelectionChanged=function isc_GridBody_handleSelectionChanged(_1,_2){var _3=this.Super("handleSelectionChanged",arguments);this.grid.handleViewStateChanged();return _3}
,isc.A.$29h=function isc_GridBody__updateCellStyle(_1,_2,_3,_4,_5,_6,_7,_8){this.invokeSuper(isc.GridBody,"$29h",_1,_2,_3,_4,_5,_6,_7,_8);var _9=this.grid;if(_9&&_9.getEditRow()==_2){var _10=_9.getFieldName(_9.getFieldNumFromLocal(_3,this)),_11=_9.getEditForm(),_12=_11?_11.getItem(_10):null;if(_12&&_12.gridCellStyleChanged){if(_5==null)_5=this.getCellStyle(_1,_2,_3);_12.gridCellStyleChanged(_1,_2,_3,_5)}}}
,isc.A.getHoverTarget=function isc_GridBody_getHoverTarget(){return this.grid}
,isc.A.keyPress=function isc_GridBody_keyPress(_1,_2){return this.grid.bodyKeyPress(_1,_2)}
,isc.A.getFocusRow=function isc_GridBody_getFocusRow(){return this.grid.getFocusRow()}
,isc.A.getFocusCol=function isc_GridBody_getFocusCol(){var _1=this.grid.$32b();return this.grid.getLocalFieldNum(_1)}
,isc.A.$lf=function isc_GridBody__focusChanged(_1){var _2=this.Super("$lf",arguments);var _3=isc.EH.lastEvent;if(_3.target==this&&(_3.eventType==isc.EH.MOUSE_DOWN||_3.eventType==isc.EH.MOUSE_UP||_3.eventType==isc.EH.CLICK||_3.eventType==isc.EH.DOUBLE_CLICK))return _2;var _4,_5=this.grid;if(_1&&_5.isEditable()){if(_5.editOnFocus&&_5.isEditable()&&_5.getEditRow()==null)
{if(this.logIsInfoEnabled("gridEdit")){this.logInfo("Editing on focus: eventType: "+_3.eventType+", lastTarget "+_3.target,"gridEdit")}
if(_5.$30c){delete _5.$30c}else{_4=_5.findNextEditCell(0,0,true,true);if(_4!=null)
_5.handleEditCellEvent(_4[0],_4[1],isc.ListGrid.FOCUS)}}}
if(isc.screenReader){if(_1){if(_4==null){var _3=isc.EH.lastEvent,_6=_3.eventType;var _7=this.getNativeFocusRow();_5.$88(_7);this.$86a(_7)}}else{_5.clearLastHilite()}}
return _2}
,isc.A.$86a=function isc_GridBody__putNativeFocusInRow(_1,_2){var _3=this.grid;if(_3&&_3.hiliteOnNativeRowFocus&&!_2)_3.$88(_1);return this.Super("$86a",arguments)}
,isc.A.updateRowSelection=function isc_GridBody_updateRowSelection(_1){var _2=this.grid;if(!_2)return;if(_2.showSelectionCanvas)_2.updateSelectionCanvas();if(_2.$67j)return;this.invokeSuper(isc.GridBody,"updateRowSelection",_1);if(isc.Canvas.ariaEnabled()&&_2.selection){this.setRowAriaState(_1,"selected",_2.selection.isSelected(_2.getRecord(_1)))}
if(_2.getCurrentCheckboxField()!=null){var _3=_2.getCheckboxFieldPosition();if(_2)_2.refreshCell(_1,_3);var _4=(isc.isAn.Array(_2.data)||(isc.isA.ResultSet(_2.data)&&_2.data.allMatchingRowsCached())),_5=_2.getSelection()||[];if(_4){if(_5.length==_2.data.getLength()){_2.$63m(true)}else{_2.$63m(false)}}}else if(_2.getTreeFieldNum&&_2.selectionAppearance=="checkbox"){var _6=_2.getTreeFieldNum();_2.refreshCell(_1,_6)}}
,isc.A.$29s=function isc_GridBody__cellSelectionChanged(_1,_2,_3,_4){var _5=this.grid;if(_5&&_5.showSelectionCanvas)_5.updateSelectionCanvas();return this.invokeSuper(isc.GridBody,"$29s",_1,_2,_3,_4)}
,isc.A.shouldAnimateEmbeddedComponent=function isc_GridBody_shouldAnimateEmbeddedComponent(_1){var _2=this.grid;if(_1==_2.selectionCanvas)return _2.animateSelection;if(_1==_2.selectionUnderCanvas)return _2.animateSelectionUnder;if(_1==_2.rollOverCanvas)return _2.animateRollOver;if(_1==_2.rollUnderCanvas)return _2.animateRollUnder;return false}
,isc.A.$80d=function isc_GridBody__handleEmbeddedComponentResize(_1,_2,_3){this.Super("$80d",arguments);this.grid.$80d(this,_1,_2,_3)}
,isc.A.draw=function isc_GridBody_draw(_1,_2,_3,_4){var _5=this.grid;if(_5.getEditRow()!=null){var _6=_5.getEditRow(),_7=_5.getRecord(_6),_8=_5.getEditCol(),_9=_5.$286,_10=_5.getEditRowItems(_7,_6,_8,_5.editByCell),_11=_9.getItems();var _12=_11==null||_10.length!=_11.length;if(!_12){var _13=_11.getProperty("name");for(var i=0;i<_10.length;i++){if(!_13.contains(_10[i].name)){_12=true;break}}}
if(_12){this.logDebug("calling setItems on form from body draw","gridEdit");_9.setItems(_10)}else{this.logDebug("Skipping setItems() on form from body draw","gridEdit")}
_9.$10u=true}
delete this.$316;this.invokeSuper(null,"draw",_1,_2,_3,_4);if(_5.$286){_5.$30b(null,null,this)}
_5.updateEditRow(_5.getEditRow());if(_5.$30d!=null){var _15=isc.isAn.Array(_5.$30d)?_5.$30d[0]:_5.$30d,_16=isc.isAn.Array(_5.$30d)?_5.$30d[1]||0:0;_5.scrollCellIntoView(_15,_16);delete _5.$30d}
this.grid.updateRecordComponents()}
,isc.A.layoutChildren=function isc_GridBody_layoutChildren(_1,_2,_3,_4){this.invokeSuper(null,"layoutChildren",_1,_2,_3,_4);if(!this.isDrawn()||(this.grid.frozenFields&&!this.grid.bodyLayout.isDrawn())){return}
if(_1=="scrolling state changed"){if(this.$28i==null){this.grid.layoutChildren("body scroll changed");delete this.$30e}else{this.$30e=true}}}
,isc.A.$28p=function isc_GridBody__rowAnimationComplete(){this.Super("$28p",arguments);if(this.$30e){this.grid.layoutChildren("body scroll changed during animation");delete this.$30e}}
,isc.A.handleMoved=function isc_GridBody_handleMoved(_1,_2,_3,_4){this.invokeSuper(null,"handleMoved",_1,_2,_3,_4);var _5=this.grid;if(_5.$30a){_5.$286.itemsMoved()}}
,isc.A.handleParentMoved=function isc_GridBody_handleParentMoved(_1,_2,_3,_4){this.invokeSuper(null,"handleParentMoved",_1,_2,_3,_4);var _5=this.grid;if(_5.$30a){_5.$286.itemsMoved()}}
,isc.A.setVisibility=function isc_GridBody_setVisibility(_1,_2,_3,_4){this.invokeSuper(null,"setVisibility",_1,_2,_3,_4);var _5=this.grid;if(_5.$30a)_5.$286.itemsVisibilityChanged()}
,isc.A.parentVisibilityChanged=function isc_GridBody_parentVisibilityChanged(_1,_2,_3,_4){this.invokeSuper(null,"parentVisibilityChanged",_1,_2,_3,_4);var _5=this.grid;if(_5.$30a)_5.$286.itemsVisibilityChanged()}
,isc.A.clear=function isc_GridBody_clear(){var _1=this.grid;_1.$69l();this.Super("clear",arguments);if(_1.$30a){_1.$30b(null,null,this);_1.$286.itemsVisibilityChanged()}}
,isc.A.zIndexChanged=function isc_GridBody_zIndexChanged(){this.Super("zIndexChanged",arguments);var _1=this.grid;if(_1&&_1.$30a)_1.$286.itemsZIndexChanged()}
,isc.A.parentZIndexChanged=function isc_GridBody_parentZIndexChanged(_1,_2,_3,_4){this.invokeSuper(null,"zIndexChanged",_1,_2,_3,_4);var _5=this.grid;if(_5.$30a)_5.$286.itemsZIndexChanged()}
,isc.A.redrawFormItem=function isc_GridBody_redrawFormItem(_1,_2){var _3=this.grid;if(_3&&(_1.form==_3.$286)){var _4=_3.getEditRow(),_5=_3.getColNum(_1.getFieldName());if(_3.getEditCol()==_5){_3.storeUpdatedEditorValue()}
_3.refreshCell(_4,_5,false,true)}else
return this.markForRedraw("Form Item Redraw "+(_2?_2:isc.emptyString))}
,isc.A.sizeFormItem=function isc_GridBody_sizeFormItem(_1){var _2=this.grid;var _3=_1.width,_4;if(isc.isA.String(_3)){var _5=_2.getEditFormItemFieldWidths(_1.record),_6=_5[_2.getFieldNum(_1.getFieldName())];if(_3=="*"){_4=_6}else if(_3[_3.length-1]=="%"){var _7=parseInt(_3);if(isc.isA.Number(_7)){_4=Math.floor(_6*(_7/ 100))}}}
var _8=_1.height,_9;if(isc.isA.String(_8)){var _10=_2.cellHeight;if(_3=="*"){_9=_10}else if(_8[_8.length-1]=="%"){var _11=parseInt(_8);if(isc.isA.Number(_11)){_9=Math.floor(_10*(_11/ 100))}}}
if(_9!=null||_4!=null){_1.$8t=[_4==null?_1.width:_4,_9==null?_1.height:_9]}}
,isc.A.startRowAnimation=function isc_GridBody_startRowAnimation(_1,_2,_3,_4,_5,_6,_7,_8,_9){this.finishRowAnimation();var _10=(_9&&(this.$34y!=null)),_11=this.grid;if(_10){_11.$34v=true;_11.data.openFolder(this.$34y);_11.$34v=null}
this.Super("startRowAnimation",arguments);if(_10){_11.$34v=true;_11.data.closeFolder(this.$34y);_11.$34v=null}
delete this.$34y}
);isc.B._maxIndex=isc.C+65;isc.A=isc.ListGrid;isc.A.CORNER="corner";isc.A.FIELD="field";isc.A.BEFORE="before";isc.A.AFTER="after";isc.A.OVER="over";isc.A.CLICK_OUTSIDE="click_outside";isc.A.CLICK="click";isc.A.DOUBLE_CLICK="doubleClick";isc.A.ENTER_KEYPRESS="enter";isc.A.ESCAPE_KEYPRESS="escape";isc.A.UP_ARROW_KEYPRESS="arrow_up";isc.A.DOWN_ARROW_KEYPRESS="arrow_down";isc.A.LEFT_ARROW_KEYPRESS="arrow_left";isc.A.RIGHT_ARROW_KEYPRESS="arrow_right";isc.A.TAB_KEYPRESS="tab";isc.A.SHIFT_TAB_KEYPRESS="shift_tab";isc.A.EDIT_FIELD_CHANGE="field_change";isc.A.EDIT_ROW_CHANGE="row_change";isc.A.PROGRAMMATIC="programmatic";isc.A.FOCUS="focus";isc.A.$295=["selection","selectionType","canSelectCells","canDragSelect","canSelectOnRightMouse","recordCanSelectProperty","canDrag","canAcceptDrop","canDrop","autoFit","wrapCells","cellSpacing","cellPadding","cellHeight","enforceVClipping","autoFitData","autoFitMaxRecords","autoFitMaxWidth","autoFitMaxColumns","autoFitMaxHeight","autoFitExtraRecords","showAllColumns","drawAllMaxCells","drawAheadRatio","quickDrawAheadRatio","instantScrollTrackRedraw","scrollRedrawDelay","printMaxRows","animateRowsMaxTime","fastCellUpdates","showRollOver","useCellRollOvers","canHover","showHover","hoverDelay","hoverWidth","hoverHeight","hoverAlign","hoverVAlign","hoverStyle","hoverOpacity","hoverMoveWithMouse","hoverByCell","keepHoverActive","cellHoverOutset","showEmptyMessage","emptyMessageStyle","emptyMessageTableStyle","showOfflineMessage","offlineMessageStyle","useCellRecords","singleCellValueProperty","isSeparatorProperty","accessKey","canFocus","_useNativeTabIndex","tableStyle","baseStyle","recordCustomStyleProperty","showSelectedStyle","fullRowSpans","showFocusOutline"];isc.A.$296=["getCellStyleName","getCellStyleIndex","getRowTop","getRowPageTop","getRowSize","getDrawnRowHeight","getCellPageRect","getVisibleRows","getDrawnRows"];isc.A.$297=["getTotalRows","isEmpty","cellIsEnabled","willAcceptDrop","scrolled","getTableElementId","getRowElementId","getCellElementId","shouldFixRowHeight","getEmptyMessage","getCanHover","stopHover","updateEmbeddedComponentZIndex"];isc.A=isc.ListGrid;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.makeBodyMethods=function isc_c_ListGrid_makeBodyMethods(_1){var _2=this.$cf;if(_2==null){_2=this.$cf=[,"this.grid.$81e = this;"+"var returnVal = this.grid.",,"(",,");"+"this.grid.$81e=null;"+"return returnVal;"]}
var _3={};for(var i=0;i<_1.length;i++){var _5=_1[i],_6=isc.GridRenderer.getArgString(_5);if(isc.contains(_6,"colNum")){_2[0]="if (this.fields[colNum]) colNum = this.fields[colNum].masterIndex;"}else if(isc.isAn.emptyString(_6)){_6="body";_2[0]="body = this;"}else{_2[0]=null}
_2[2]=_5;_2[4]=_6;var _7=_2.join(isc.emptyString);_3[_5]=new Function(_6,_7)}
return _3}
,isc.A.classInit=function isc_c_ListGrid_classInit(){this.addMethods(isc.ClassFactory.makePassthroughMethods(this.$296,"body"));var _1={};var _2=isc.getKeys(isc.GridRenderer.$294),_1=isc.ListGrid.makeBodyMethods(_2);isc.addProperties(_1,isc.ListGrid.makeBodyMethods(this.$297));this.$30f=_1;var _3={},_4=[,"var $81e = this.$81e || this.body;"+" if ($81e == null) {"+"return;"+"}"+"if($81e.__orig_",,")return $81e.__orig_",,"(",,")"],_5="__orig_",_6=isc.GridRenderer.getPrototype();for(var i=0;i<_2.length;i++){var _8=_2[i],_9=isc.GridRenderer.getArgString(_8);if(isc.ListGrid.getInstanceProperty(_8)==null){if(isc.contains(_9,"colNum")){_4[0]="if (colNum != null && colNum >= 0) colNum = this.getLocalFieldNum(colNum);"}else{_4[0]=null}
_4[2]=_4[4]=_8;_4[6]=_9
_3[_8]=new Function(_9,_4.join(isc.emptyString))}
_6[_5+_8]=_6[_8]}
this.$30g=_3;this.addMethods(_3)}
);isc.B._maxIndex=isc.C+2;isc.A=isc.ListGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.styleName="listGrid";isc.A.useCellRecords=false;isc.A.recordEnabledProperty="enabled";isc.A.canExpandRecordProperty="canExpand";isc.A.animateFolders=true;isc.A.animateFolderTime=100;isc.A.animateFolderSpeed=3000;isc.A.animateRowsMaxTime=1000;isc.A.autoFetchDisplayMap=true;isc.A.warnOnUnmappedValueFieldChange=true;isc.A.showDetailFields=true;isc.A.groupStartOpen="first";isc.A.canCollapseGroup=true;isc.A.showGroupTitleColumn=true;isc.A.groupTitleColumnDefaults={canEdit:false,canFilter:false,canHide:false,canReorder:false,showDefaultContextMenu:false,showHeaderContextMenuButton:false,autoFreeze:true,sortNormalizer:function(_1,_2,_3){return _1.groupTitle},autoFitWidth:true,autoFitWidthApproach:"value",title:"&nbsp;"};isc.A.groupTitleColumnName="groupTitle";isc.A.showGroupSummaryInHeader=false;isc.A.groupNodeStyle="groupNode";isc.A.groupIcon="[SKINIMG]/TreeGrid/opener.gif";isc.A.groupIconSize=16;isc.A.groupIndentSize=20;isc.A.groupLeadingIndent=10;isc.A.canGroupBy=true;isc.A.groupByMaxRecords=1000;isc.A.nullGroupTitle="-none-";isc.A.valueIconSize=16;isc.A.valueIconLeftPadding=2;isc.A.valueIconRightPadding=2;isc.A.imageSize=16;isc.A.headerSpanVAlign="center";isc.A.showTreeColumnPicker=true;isc.A.fetchDelay=300;isc.A.uniqueMatch=true;isc.A.overflow=isc.Canvas.HIDDEN;isc.A.backgroundColor="white";isc.A.minHeight=50;isc.A.defaultWidth=200;isc.A.drawAllMaxCells=250;isc.A.drawAheadRatio=1.3;isc.A.quickDrawAheadRatio=1.0;isc.A.scrollRedrawDelay=75;isc.A.dataFetchDelay=300;isc.A.bodyConstructor="GridBody";isc.A.bodyOverflow=isc.Canvas.AUTO;isc.A.bodyBackgroundColor="white";isc.A.allowMismatchedHeaderBodyBorder=true;isc.A.emptyCellValue="&nbsp;";isc.A.cellHeight=20;isc.A.normalCellHeight=20;isc.A.fixedRecordHeights=true;isc.A.fixedFieldWidths=true;isc.A.autoFitMaxRecords=50;isc.A.autoFitMaxColumns=50;isc.A.canAutoFitFields=true;isc.A.headerAutoFitEvent="doubleClick";isc.A.autoFitFieldsFillViewport=true;isc.A.autoFitWidthApproach="value";isc.A.autoFitIconFields="title";isc.A.leaveScrollbarGap=true;isc.A.resizeFieldsForScrollbar=true;isc.A.cellSpacing=0;isc.A.cellPadding=2;isc.A.timeFormatter="toShortPaddedTime";isc.A.$30h=["<a href='",,"' target='",,"' onclick='if(window.",,") return ",,".$30i(event,",,",",,");'>",,"</a>"];isc.A.$30j="\\'";isc.A.$30k="_blank";isc.A.linkTextProperty="linkText";isc.A.fastCellUpdates=isc.Browser.isIE&&!isc.Browser.isIE9;isc.A.normalBaseStyle="cell";isc.A.tallBaseStyle="cell";isc.A.editFailedBaseStyle=null;isc.A.editFailedCSSText="color:red;border:1px solid red;";isc.A.editPendingBaseStyle=null;isc.A.editPendingCSSText="color:#0066CC;";isc.A.recordCustomStyleProperty="customStyle";isc.A.recordBaseStyleProperty="_baseStyle";isc.A.shrinkForFreeze=false;isc.A.alternateRecordFrequency=1;isc.A.recordCSSTextProperty="cssText";isc.A.showSelectedStyle=true;isc.A.generateClickOnSpace=true;isc.A.generateDoubleClickOnEnter=true;isc.A.arrowKeyAction="select";isc.A.recordComponentPoolingMode="viewport";isc.A.poolComponentsPerColumn=true;isc.A.showRollOver=!isc.Browser.isTouch;isc.A.backgroundComponentDefaults={snapTo:"TL",autoDraw:false,opacity:"50%"};isc.A.showHover=true;isc.A.cellHoverOutset=5;isc.A.hoverStyle="gridHover";isc.A.selectionAppearance="rowStyle";isc.A.recordCanSelectProperty="canSelect";isc.A.showEmptyMessage=true;isc.A.emptyMessage="No items to show.";isc.A.emptyMessageStyle="emptyMessage";isc.A.filterButtonPrompt="Filter";isc.A.loadingDataMessage="${loadingImage}&nbsp;Loading data...";isc.A.loadingDataMessageStyle="loadingDataMessage";isc.A.loadingMessage="&nbsp;";isc.A.singleCellValueProperty="singleCellValue";isc.A.isSeparatorProperty="isSeparator";isc.A.filterEditorDefaults={shouldPrint:false};isc.A.filterEditorHeight=22;isc.A.autoFetchTextMatchStyle="substring";isc.A.canEditNew=false;isc.A.recordEditProperty="_canEdit";isc.A.editValuesTestedProperty="$30l";isc.A.autoValidate=true;isc.A.warnOnRemoval=false;isc.A.warnOnRemovalMessage="Are you sure you want to delete this record?";isc.A.recordRemovedProperty="$899";isc.A.removedCSSText="text-decoration:line-through;";isc.A.removeIcon="[SKIN]/actions/remove.png";isc.A.unremoveIcon="[SKIN]/actions/undo.png";isc.A.removeIconSize=16;isc.A.animateRemoveRecord=true;isc.A.animateRemoveTime=100;isc.A.animateRemoveSpeed=200;isc.A.removeFieldTitle="[Remove record]";isc.A.removeFieldDefaults={type:"icon",width:21,showHeaderContextMenuButton:false,showDefaultContextMenu:false,showTitle:false,canEdit:false,canSort:false,canGroupBy:false,canExport:false,canFilter:false,showGroupSummary:false,showGridSummary:false,summaryValue:"&nbsp;"};isc.A.autoSaveEdits=true;isc.A.showErrorIcons=true;isc.A.errorIconHeight=16;isc.A.errorIconWidth=16;isc.A.errorIconSrc="[SKIN]/validation_error_icon.png";isc.A.cancelEditingConfirmationMessage="Cancelling this edit will discard unsaved changes for this record. Continue?";isc.A.confirmDiscardEdits=true;isc.A.confirmDiscardEditsMessage="This action will discard all unsaved changes for this list.";isc.A.discardEditsSaveButtonTitle="Save";isc.A.newRecordRowMessage="-- Add New Row --";isc.A.enterKeyEditAction="done";isc.A.escapeKeyEditAction="cancel";isc.A.editEvent=isc.EH.DOUBLE_CLICK;isc.A.editOnF2Keypress=true;isc.A.selectOnEdit=true;isc.A.enumCriteriaAsInitialValues=true;isc.A.autoSelectEditors=true;isc.A.editFormDefaults={canSelectText:true,autoDraw:false,errorOrientation:"left",showErrorText:false,showErrorStyle:false,itemKeyPress:function(_1,_2,_3){return this.grid.editorKeyPress(_1,_2,_3)}};isc.A.longTextEditorThreshold=255;isc.A.longTextEditorType="PopUpTextAreaItem";isc.A.headerHeight=22;isc.A.minFieldWidth=15;isc.A.showHeader=true;isc.A.headerBackgroundColor="#CCCCCC";isc.A.headerDefaults={instantRelayout:true,enforcePolicy:false,itemClick:function(_1,_2){this.Super("itemClick",arguments);this.grid.$666(_2,this)},itemDoubleClick:function(_1,_2){this.Super("itemDoubleClick",arguments);this.grid.$774(_2,this)},showContextMenu:function(){return this.grid.headerBarContextClick(this)},backgroundRepeat:isc.Canvas.NO_REPEAT,shouldPrint:false};isc.A.headerButtonDefaults={getCurrentCursor:function(){var _1=this.parentElement?this.parentElement.grid:null;var _2;if(_1&&this.masterIndex!=null){var _3=_1.getField(this.masterIndex),_4=(_1.canSort!=false&&_1.$91k(_3)!=false);if(_4)_2=isc.Canvas.HAND;else _2=isc.Canvas.DEFAULT}else{if(this.isSorterButton){if(!_1&&isc.isA.ListGrid(this.parentElement))_1=this.parentElement;var _4=(_1.canSort!=false&&_1.$91k(_1.$60z())!=false);if(_4)_2=isc.Canvas.HAND;else _2=isc.Canvas.DEFAULT}else{_2=this.getClass().getPrototype().cursor}}
this.cursor=_2;return this.Super("getCurrentCursor",arguments)},dragScrollType:"parentsOnly",minWidth:20};isc.A.sorterConstructor=isc.Button;isc.A.sorterDefaults={_redrawWithParent:false,getTitle:function(){return this.creator.getSortArrowImage()},click:function(){return this.creator.sorterClick()},showContextMenu:function(){return this.creator.sorterContextClick()},isSorterButton:true,showTitle:true,align:"center",canFocus:false};isc.A.canSort=true;isc.A.canUnsort=false;isc.A.invalidateCacheOnUnsort=false;isc.A.sortDirection=Array.ASCENDING;isc.A.canPickFields=true;isc.A.canPickOmittedFields=false;isc.A.openRecordEditorContextMenuItemTitle="Edit";isc.A.dismissEmbeddedComponentContextMenuItemTitle="Dismiss";isc.A.deleteRecordContextMenuItemTitle="Delete";isc.A.canOpenRecordDetailGrid=true;isc.A.canOpenRecordEditor=true;isc.A.recordEditorSaveButtonTitle="Save";isc.A.recordEditorCancelButtonTitle="Cancel";isc.A.headerMenuButtonIcon="[SKIN]/ListGrid/headerMenuButton_icon.gif";isc.A.headerMenuButtonIconWidth=7;isc.A.headerMenuButtonIconHeight=7;isc.A.headerMenuButtonWidth=16;isc.A.headerMenuButtonHeight="100%";isc.A.canDragRecordsOut=false;isc.A.canReorderFields=true;isc.A.canResizeFields=true;isc.A.dragAppearance=isc.EH.TRACKER;isc.A.dragResizeAppearance=isc.EH.OUTLINE;isc.A.dragTrackerMode="title";isc.A.resizeFieldsInRealTime=(isc.Browser.isIE&&isc.Browser.isWin)||(isc.Browser.isFirefox&&isc.Browser.geckoVersion>=20080529)||(isc.Browser.isSafari&&isc.Browser.safariVersion>=500);isc.A.embeddedComponentIndent=25;isc.A.nestedGridDefaults={height:150};isc.A.skinImgDir="images/ListGrid/";isc.A.sortAscendingImage={src:"[SKIN]sort_ascending.gif",width:7,height:7};isc.A.sortDescendingImage={src:"[SKIN]sort_descending.gif",width:7,height:7};isc.A.trackerImage={src:"[SKIN]tracker.gif",width:16,height:16};isc.A.booleanTrueImage=null;isc.A.booleanFalseImage=null;isc.A.booleanPartialImage=null;isc.A.booleanImageWidth=16;isc.A.booleanImageHeight=16;isc.A.mozBodyOutlineColor="white";isc.A.mozBodyNoHeaderOutlineColor="red";isc.A.rowNumberStyle="specialCol";isc.A.rowNumberStart=1;isc.A.rowNumberFieldDefaults={name:"$74y",excludeFromState:true,canEdit:false,canFilter:false,canGroupBy:false,canSort:false,canExport:false,canHide:false,canReorder:false,canDragResize:false,canHilite:false,showAlternateStyle:false,$74z:true,showHeaderContextMenuButton:false,showDefaultContextMenu:false,keyboardFiresRecordClick:false,showGroupSummary:false,showGridSummary:false,summaryValue:"&nbsp;",formatCellValue:function(_1,_2,_3,_4,_5){if(_5.isGrouped){if(_2==null||_2.$52e)return"&nbsp;";var _6=_5.getGroupedRecordIndex(_2);if(_6==-1)return null;return(_5.rowNumberStart+_6)}else{return this.rowNumberStart+_3}},autoFreeze:true};isc.A.$740=30;isc.A.canExpandMultipleRecords=true;isc.A.maxExpandedRecordsPrompt="This grid is limited to \${count} simultaneously expanded records.  Please collapse some expanded records and retry.";isc.A.expansionFieldDefaults={name:"$72v",canEdit:false,canFilter:false,canGroupBy:false,canSort:false,canExport:false,canHide:false,canReorder:false,canDragResize:false,canHilite:false,$72w:true,showHeaderContextMenuButton:false,showDefaultContextMenu:false,keyboardFiresRecordClick:false,cellAlign:"center",showGroupSummary:false,showGridSummary:false,summaryValue:"&nbsp;",recordClick:function(_1,_2,_3,_4,_5,_6,_7){if(!_1.canExpandRecords||!_4.$72w)return;if(!_1.$811(_2,_3))return;if(_2.expanded)_1.collapseRecord(_2);else _1.expandRecord(_2)},formatCellValue:function(_1,_2,_3,_4,_5){_2=_5.getCellRecord(_3,_4);if(_2==null||_2.$52e)return null;if(!_5.$811(_2,_3))return null;return _5.getValueIconHTML(_2.expanded?_5.expansionFieldTrueImage:_5.expansionFieldFalseImage,this)},autoFreeze:true};isc.A.expansionFieldTrueImage="[SKINIMG]/ListGrid/group_opened.gif";isc.A.expansionFieldFalseImage="[SKINIMG]/ListGrid/group_opening.gif";isc.A.expansionFieldExtraWidth=16;isc.A.$74a=0;isc.A.expansionDetailFieldDefaults={_constructor:isc.HTMLFlow,autoDraw:false,width:"100%",height:"100%"};isc.A.expansionDetailsDefaults={_constructor:isc.DetailViewer,autoDraw:false,width:"100%"};isc.A.expansionRelatedDefaults={_constructor:isc.ListGrid,autoDraw:false,width:"100%",autoFitData:"vertical",autoFitMaxRecords:4};isc.A.expansionEditorSaveDialogPrompt="You have unsaved changes - do you want to save them now?";isc.A.expansionEditorShowSaveDialog=false;isc.A.expansionEditorDefaults={_constructor:"DynamicForm",autoDraw:false,numCols:4,colWidths:["*","*","*","*"],width:"100%",saveOperationType:"update"};isc.A.expansionDetailRelatedDefaults={_constructor:isc.HLayout,autoDraw:false,width:"100%",height:"100%"};isc.A.expansionLayoutDefaults={_constructor:isc.VLayout,autoDraw:false,width:"100%",height:10,overflow:"visible"};isc.A.recordDetailDSProperty="detailDS";isc.A.expansionCanEdit=false;isc.B.push(isc.A.shouldAnimateFolder=function isc_ListGrid_shouldAnimateFolder(_1){if(!this.animateFolders||!this.isDrawn())return false;var _2=this.data.isFolder(_1)?this.data.getOpenList(_1):null;if(_2==null||_2.length<=1)return false;return(_2.length<=this.getAnimateFolderMaxRows())}
,isc.A.getAnimateFolderMaxRows=function isc_ListGrid_getAnimateFolderMaxRows(){var _1=this.animateFolderMaxRows;if(_1==null){var _2=this.body?this.body.$27x():[0,0];_1=Math.min(75,(_2[1]-_2[0])*3)}
return _1}
,isc.A.getGroupTitleField=function isc_ListGrid_getGroupTitleField(){return this.groupTitleField}
,isc.A.showingGroupTitleColumn=function isc_ListGrid_showingGroupTitleColumn(){return(this.isGrouped&&this.showGroupSummary&&this.showGroupSummaryInHeader&&this.showGroupTitleColumn&&this.getGroupTitleField()==null)}
,isc.A.getGroupTitleColumn=function isc_ListGrid_getGroupTitleColumn(){var _1=this;var _2=isc.addProperties({$84g:true,getAutoFreezePosition:function(){return _1.getGroupTitleColumnPosition()}},this.groupTitleColumnDefaults,this.groupTitleColumnProperties);if(_2.name==null){_2.name=this.groupTitleColumnName}
return _2}
,isc.A.getGroupTitleColumnPosition=function isc_ListGrid_getGroupTitleColumnPosition(){if(!this.showingGroupTitleColumn())return-1;return(this.showRowNumbers?1:0)}
,isc.A.singleCellGroupHeaders=function isc_ListGrid_singleCellGroupHeaders(){if(this.getGroupTitleField()!=null)return false;if(this.showGroupSummary&&this.showGroupSummaryInHeader)return false;return true}
,isc.A.getBreadth=function isc_ListGrid_getBreadth(){return this.getInnerWidth()}
,isc.A.$30m=function isc_ListGrid__formatDateCellValue(_1,_2,_3,_4,_5,_6){if(isc.isA.Date(_1)){if(_3.$851(_2)){var _7=_3.$30o(_2);var _8=isc.SimpleType.inheritsFrom(_2.type,"time");return isc.Time.toTime(_1,_7,_8)}
var _9=_2&&isc.SimpleType.inheritsFrom(_2.type,"datetime"),_10=!_9&&isc.SimpleType.inheritsFrom(_2.type,"date"),_7=_3.$45i(_2);if(_9)return _1.toShortDateTime(_7,true);return _1.toShortDate(_7,!_10)}
return _1}
,isc.A.$851=function isc_ListGrid__formatAsTime(_1){if(_1==null)return false;if(_1.timeFormatter!=null&&_1.dateFormatter==null)return true;if(_1.dateFormatter!=null&&_1.timeFormatter==null)return false;return isc.SimpleType.inheritsFrom(_1.type,"time")}
,isc.A.$45i=function isc_ListGrid__getDateFormatter(_1){if(_1==null)return this.dateFormatter;if(_1.dateFormatter!=null)return _1.dateFormatter;if(_1.displayFormat!=null&&isc.SimpleType.inheritsFrom(_1.type,"date")){return _1.displayFormat}
if(this.datetimeFormatter!=null&&isc.SimpleType.inheritsFrom(_1.type,"datetime")){return this.datetimeFormatter}
return this.dateFormatter}
,isc.A.$45j=function isc_ListGrid__getDateInputFormat(_1){var _2;if(_1)_2=_1.inputFormat
if(!_2)_2=this.dateInputFormat;return _2}
,isc.A.$30n=function isc_ListGrid__formatNumberCellValue(_1,_2,_3,_4,_5,_6){if(isc.isA.Number(_1)){var _7=(_2.numberFormatter||_2.formatter||_3.numberFormatter);return _1.toFormattedString(_7)}
return _1}
,isc.A.$30o=function isc_ListGrid__getTimeFormatter(_1){if(_1!=null){if(_1.timeFormatter!=null)return _1.timeFormatter;if(_1.displayFormat!=null&&isc.SimpleType.inheritsFrom(_1.type,"time")){return _1.displayFormat}}
return this.timeFormatter}
,isc.A.$30p=function isc_ListGrid__formatTimeCellValue(_1,_2,_3,_4,_5,_6){var _7=_1;if(isc.isA.String(_7)){_7=isc.Time.parseInput(_7,true)}
if(isc.isA.Date(_7)){if(!_3.$851(_2)){return _7.toShortDate(_3.$45i(_2),true)}
var _8=_3.$30o(_2);return isc.Time.toTime(_7,_8,true)}
return _1}
,isc.A.$30q=function isc_ListGrid__formatBinaryCellValue(_1,_2,_3,_4,_5,_6){if(isc.isA.String(_1))return _1;if(_4==null)return null;var _7=_2.name,_8=_2.nativeName||_7,_9=_4[_8+"_filename"],_1;if(_2.type=="imageFile"&&_2.showFileInline==true){var _10=_7+"$68c";if(!_4[_10]){var _11=isc.Canvas.getFieldImageDimensions(_2,_4),_12=_3.getDataSource().streamFile(_4,_2.name);_11.width=_11.width||_3.imageSize;_11.height=_11.height||_3.imageSize;_1=_4[_10]=isc.Canvas.imgHTML(_12,_11.width,_11.height)}else
_1=_4[_10]}else{if(_2.showFileInline==true){this.logWarn("$30q(): Unsupported field-type for showFileInline: "+_2.type)}
if(_9==null||isc.isA.emptyString(_9))return this.emptyCellValue;var _13=isc.Canvas.imgHTML("[SKIN]actions/view.png",16,16,null,"style='cursor:"+isc.Canvas.HAND+"' onclick='"+_3.getID()+".viewRow("+_5+")'");var _14=isc.Canvas.imgHTML("[SKIN]actions/download.png",16,16,null,"style='cursor:"+isc.Canvas.HAND+"' onclick='"+_3.getID()+".downloadRow("+_5+")'");_1=_13+"&nbsp;"+_14+"&nbsp;"+_9}
return _1}
,isc.A.$30r=function isc_ListGrid__formatLinkCellValue(_1,_2,_3,_4,_5,_6){if(_1==null||isc.is.emptyString(_1))return _1;var _7=_2.target?_2.target.replaceAll(_3.$ob,_3.$30j):_3.$30k;var _8=_2.linkTextProperty?_2.linkTextProperty:_3.linkTextProperty;var _9=(_4&&_4[_8])?_4[_8]:_2.linkText||_1;var _10=_1;if(_2.linkURLPrefix)_10=_2.linkURLPrefix+_10;if(_2.linkURLSuffix)_10=_10+_2.linkURLSuffix;_10=_10.replaceAll(_3.$ob,_3.$30j);if(_7=="javascript"){_10="javascript:void"}
var _11=_3.$30h;_11[1]=_10;_11[3]=_7;var _12=_3.getID();_11[5]=_12;_11[7]=_12;_11[9]=_5;_11[11]=_6;_11[13]=_9;return _11.join(isc.emptyString)}
,isc.A.$30i=function isc_ListGrid__linkClicked(_1,_2,_3){var _4=(this.destroyed||!this.isDrawn()||!this.isVisible()||isc.EH.targetIsMasked(this.body)||!this.recordIsEnabled(_2,_3));var _5=this.getRecord(_2),_6=this.getField(_3);if(_1.target=="javascript"||_6.target=="javascript"){_4=true;this.cellClick(_5,_2,_3)}
if(_4){if(!isc.Browser.isIE){_1.preventDefault()}
return false}
return true}
,isc.A.$30s=function isc_ListGrid__formatImageCellValue(_1,_2,_3,_4,_5,_6){if(_1==null||_1==isc.emptyString)return isc.emptyString;var _7=isc.Canvas.getFieldImageDimensions(_2,_4);_7.width=_7.width||_3.imageSize;_7.height=_7.height||_3.imageSize;var _8=_1,_9=_2.imageURLPrefix||_2.baseURL||_2.imgDir;if(_2.imageURLSuffix!=null)_8+=_2.imageURLSuffix;return isc.Canvas.imgHTML(_8,_7.width,_7.height,null,_2.extraStuff,_9,_2.activeAreaHTML)}
,isc.A.$59e=function isc_ListGrid__formatIconCellValue(_1,_2,_3,_4,_5,_6){if(isc.isA.RecordEditor(_3)&&_3.isAFilterEditor()&&_2.canFilter==false)return null;if(_2.$59f)return _2.$59f;_2.$59f=isc.Canvas.imgHTML(_2.cellIcon||_2.icon,_2.iconWidth||_2.iconSize||_3.imageSize,_2.iconHeight||_2.iconSize||_3.imageSize);return _2.$59f}
,isc.A.setFastCellUpdates=function isc_ListGrid_setFastCellUpdates(_1){if(this.body!=null){this.body.setFastCellUpdates(_1);_1=this.body.fastCellUpdates}
if(this.frozenBody!=null){this.frozenBody.setFastCellUpdates(_1)}
this.fastCellUpdates=_1}
,isc.A.shouldShowRemoveField=function isc_ListGrid_shouldShowRemoveField(){if(this.fieldSourceGrid!=null)return this.fieldSourceGrid.shouldShowRemoveField();return this.canRemoveRecords}
,isc.A.markRecordRemoved=function isc_ListGrid_markRecordRemoved(_1,_2){if(!isc.isA.Number(_1))_1=this.findRowNum(_1);if(this.getEditRow()==_1)this.hideInlineEditor();var _3=this.getRecord(_1);this.selection.deselect(_3);this.setEditValue(_1,this.recordRemovedProperty,true,false,false);if(!_2)this.refreshRow(_1)}
,isc.A.recordMarkedAsRemoved=function isc_ListGrid_recordMarkedAsRemoved(_1){if(_1==null)return;if(!isc.isA.Number(_1))_1=this.findRowNum(_1);return(this.getEditValue(_1,this.recordRemovedProperty)==true)}
,isc.A.unmarkRecordRemoved=function isc_ListGrid_unmarkRecordRemoved(_1,_2){if(!isc.isA.Number(_1))_1=this.findRowNum(_1);this.clearEditValue(_1,this.recordRemovedProperty);if(!_2)this.refreshRow(_1)}
,isc.A.markSelectionRemoved=function isc_ListGrid_markSelectionRemoved(){var _1=this.getSelectedRecords();if(_1!=null){for(var i=0;i<_1.length;i++){this.markRecordRemoved(_1[i],true)}}
this.markForRedraw()}
,isc.A.shouldDeferRemoval=function isc_ListGrid_shouldDeferRemoval(){if(this.deferRemoval!=null)return this.deferRemoval;return!this.autoSaveEdits}
,isc.A.editorKeyDown=function isc_ListGrid_editorKeyDown(_1,_2){if(isc.Browser.isMoz&&_1&&_1.multiple&&isc.isA.NativeSelectItem(_1)&&_2=="Enter")
{_1.$695=_1.getValue()}
if(_2=="Tab"){_1.$90y=true}
if(isc.Browser.isSafari&&isc.Browser.safariVersion>=525.13&&_2=="Tab"){return false}}
,isc.A.editorKeyPress=function isc_ListGrid_editorKeyPress(_1,_2,_3){var _4=isc.EH,_5,_6;var _7=isc.isA.PopUpTextAreaItem(_1)||(isc.RichTextItem&&isc.isA.RichTextItem(_1))||isc.isA.TextAreaItem(_1);if(_2=="Tab"){if(!_1.$90y)return;delete _1.$90y;var _8=_4.shiftKeyDown();if(this.ns.isA.ContainerItem(_1)||(_1.icons!=null&&_1.icons.length>0&&!this.ns.isA.PopUpTextAreaItem(_1)))
{if(!this.$11s(_1,_8)){return false}}
_6=_8?isc.ListGrid.SHIFT_TAB_KEYPRESS:isc.ListGrid.TAB_KEYPRESS;_5=false}else if(_2=="Enter"){if(_1.getFocusIconIndex()!=null)return;if(_7&&isc.EH.altKeyDown()==false){return _5}
if(_1.$695!=null){var _9=_1.$695;delete _1.$695;_1.setValue(_9)}
_6=isc.ListGrid.ENTER_KEYPRESS;_5=false}else if(_2=="Escape"){_6=isc.ListGrid.ESCAPE_KEYPRESS;_5=false}else if(_2=="Arrow_Up"){var _10=_7;if(_10&&!isc.EH.altKeyDown())return _5;if(isc.isA.SelectItem(_1)&&!isc.EH.ctrlKeyDown())return _5;if(isc.EH.ctrlKeyDown()&&isc.EH.shiftKeyDown())return _5;_6=isc.ListGrid.UP_ARROW_KEYPRESS;_5=false}else if(_2=="Arrow_Down"){var _10=_7;if(_10&&!isc.EH.altKeyDown())return _5;if(isc.isA.SelectItem(_1)&&!isc.EH.ctrlKeyDown())return _5;if(isc.EH.ctrlKeyDown&&isc.EH.shiftKeyDown())return _5;_6=isc.ListGrid.DOWN_ARROW_KEYPRESS;_5=false}else if(this.moveEditorOnArrow&&this.$51k){if(_2=="Arrow_Left"){_6=isc.ListGrid.LEFT_ARROW_KEYPRESS;_5=false}else if(_2=="Arrow_Right"){_6=isc.ListGrid.RIGHT_ARROW_KEYPRESS;_5=false}}
if(_6!=null){if(isc.EH.clickMaskUp()){isc.EH.setMaskedFocusCanvas(null,isc.EH.clickMaskRegistry.last())}
this.cellEditEnd(_6)}
return _5}
,isc.A.$11s=function isc_ListGrid__moveFocusWithinItem(_1,_2){if(!_1)return true;return(!_1.$11s(!_2))}
,isc.A.$30t=function isc_ListGrid__editFormItem_elementFocus(_1){var _2=this.form,_3=_2.grid;var _4,_5,_6,_7;if(_3.$30a){_4=_3.$285;if(!_3.editByCell){_4=_3.$285;_6=this.getFieldName();_5=_3.fields.findIndex(_3.fieldIdProperty,_6);if(!_1){_7=(_3.$30u!=_5);if(_7){_3.setNewEditCell(_4,_5);_3.cellEditEnd(isc.ListGrid.EDIT_FIELD_CHANGE)}}}else{_5=_3.$30u}}
this.Super("elementFocus",arguments);if(_3.$30a){var _8=this.$30v,_9=this.$30w;delete this.$30v;delete this.$30w;var _10=isc.addProperties({},_3.getCellRecord(_4,_5),_3.$300(_4,_5));if(_9){var _6=_3.getFieldName(_5);_3.$30x(this,_4,_5,_10[_6])}
if(_8)_3.$30z(this,_4,_10)}else{_3.logWarn("suppressing editorEnter handlers on focus as listGrid.$30a is null")}}
,isc.A.getParentGroupIndex=function isc_ListGrid_getParentGroupIndex(_1){if(!this.isGrouped)return 0;var _2=this.groupTree,_3=_2.getParent(_1),_4=_2.getChildren(_2.getParent(_3)),_5=0;for(var i=0;i<_4.length;i++){var _7=_4[i];if(_7.groupValue==_3.groupValue){_5=i;break}}
return _5}
,isc.A.getGroupedRecordIndex=function isc_ListGrid_getGroupedRecordIndex(_1){if(!this.isGrouped)return-1;var _2=this.groupTree,_3=_2==null?null:_2.getParent(_1);if(_3==null)return-1;var _4=_2.getChildren(_2.getParent(_3)),_5=0,_6=0;for(var i=0;i<_4.length;i++){var _8=_4[i];if(_8.groupValue==_3.groupValue){var _9=_2.getChildren(_8);for(var j=0;j<_9.length;j++){if(this.objectsAreEqual(_9[j],_1)){return _6+j}}}
var _11=_2.getChildren(_8),_12=_11.length;if(this.showGroupSummary&&!this.showGroupSummaryInHeader){for(var _13=_12-1;_13>=0;_13--){if(_11[_13].groupSummaryRecordProperty)_12-=1;else break}}
_6+=_12}
return _6}
,isc.A.objectsAreEqual=function isc_ListGrid_objectsAreEqual(_1,_2){for(var _3 in _1){if(_1[_3]!=_2[_3])return false}
return true}
,isc.A.getRowNumberField=function isc_ListGrid_getRowNumberField(){var _1=this,_2={width:this.$740,baseStyle:this.rowNumberStyle,rowNumberStart:this.rowNumberStart,getAutoFreezePosition:function(){return _1.getRowNumberFieldPosition()}};isc.addProperties(_2,this.rowNumberFieldDefaults,this.rowNumberFieldProperties);_2.title="&nbsp;";return _2}
,isc.A.getCurrentRowNumberField=function isc_ListGrid_getCurrentRowNumberField(){var _1=this.completeFields||this.fields,_2=_1.find(this.fieldIdProperty,"$74y");return!_2?null:isc.isAn.Array(_2)?_2[0]:_2}
,isc.A.isRowNumberField=function isc_ListGrid_isRowNumberField(_1){if(!_1||!_1.$74z)return false;else return true}
,isc.A.getRowNumberFieldPosition=function isc_ListGrid_getRowNumberFieldPosition(){if(this.fieldSourceGrid)return this.fieldSourceGrid.getRowNumberFieldPosition();if(!this.showRowNumbers)return-1;return 0}
,isc.A.shouldShowRowNumberField=function isc_ListGrid_shouldShowRowNumberField(){return this.fieldSourceGrid?this.fieldSourceGrid.shouldShowRowNumberField():(this.showRowNumbers==true)}
,isc.A.shouldShowExpansionField=function isc_ListGrid_shouldShowExpansionField(){return this.fieldSourceGrid?this.fieldSourceGrid.shouldShowExpansionField():this.canExpandRecords==true}
,isc.A.getExpansionField=function isc_ListGrid_getExpansionField(){var _1=this,_2={excludeFromState:true,width:this.$72y()+this.expansionFieldExtraWidth,getAutoFreezePosition:function(){return _1.getExpansionFieldPosition()}};_2.valueIconWidth=this.$72y();_2.valueIconHeight=this.$72z();isc.addProperties(_2,this.expansionFieldDefaults,this.expansionFieldProperties);_2.title="&nbsp;";return _2}
,isc.A.getCurrentExpansionField=function isc_ListGrid_getCurrentExpansionField(){var _1=this.completeFields||this.fields,_2=_1.find(this.fieldIdProperty,"$72v");return!_2?null:isc.isAn.Array(_2)?_2[0]:_2}
,isc.A.$72y=function isc_ListGrid__getExpansionFieldImageWidth(){return this.expansionFieldImageWidth||this.booleanImageWidth||(isc.CheckboxItem?isc.CheckboxItem.getInstanceProperty("valueIconWidth"):null)}
,isc.A.$72z=function isc_ListGrid__getExpansionFieldImageHeight(){return this.expansionFieldImageHeight||this.booleanImageHeight||(isc.CheckboxItem?isc.CheckboxItem.getInstanceProperty("valueIconHeight"):null)}
,isc.A.isExpansionField=function isc_ListGrid_isExpansionField(_1){if(!_1||!_1.$72w)return false;else return true}
,isc.A.getExpansionFieldPosition=function isc_ListGrid_getExpansionFieldPosition(){if(this.fieldSourceGrid!=null)return this.fieldSourceGrid.getExpansionFieldPosition();if(!this.canExpandRecords)return-1;var _1=0;if(this.showRowNumbers)_1+=1;if(this.showingGroupTitleColumn())_1+=1;return _1}
,isc.A.$811=function isc_ListGrid__canExpandRecord(_1,_2){if(_1==null)_1=this.getRecord(_2);if(_1==null)return false;return this.canExpandRecord(_1,_2)}
,isc.A.canExpandRecord=function isc_ListGrid_canExpandRecord(_1,_2){return _1[this.canExpandRecordProperty]==false?false:true&&(this.canExpandRecords!=false)}
,isc.A.setCanExpandRecords=function isc_ListGrid_setCanExpandRecords(_1){if(this.canExpandRecords==_1)return;if(!_1){var _2=this.data;if(_2){var _3=this.data.findAll("expanded",true);if(_3!=null){for(var i=0;i<_3.length;i++){this.collapseRecord(_3[i])}}}}
this.canExpandRecords=_1;this.refreshFields()}
,isc.A.expandRecord=function isc_ListGrid_expandRecord(_1){var _2,_3=this.getRecordIndex(_1);if(!_1.expanded){if(this.onExpandRecord!=null&&!this.onExpandRecord(_1))return;if(!this.canExpandMultipleRecords){if(this.$74b)
this.collapseRecord(this.$74b);this.$74b=_1}else if(this.maxExpandedRecords){if(this.$74a==this.maxExpandedRecords){var _4=this.maxExpandedRecordsPrompt.evalDynamicString(this,{count:this.maxExpandedRecords});isc.say(_4);return}}
_2=this.getExpansionComponent(_1);var _5=this.createAutoChild("expansionLayout",{layoutLeftMargin:this.embeddedComponentIndent,members:[_2]});_5.isExpansionComponent=true;this.addEmbeddedComponent(_5,_1,this.data.indexOf(_1));_1.expanded=true;_1.hasExpansionComponent=true;this.$74a++;if(!this.canExpandMultipleRecords)this.$74b=_1}
this.delayCall("markForRedraw",["Expanded Record"]);this.refreshRow(_3)}
,isc.A.collapseRecord=function isc_ListGrid_collapseRecord(_1){var _2=(_1&&_1.$29a)?_1.$29a.find("isExpansionComponent",true):null;if(isc.isA.Layout(_2)){var _3=_2.getMember(0);if(isc.isA.DynamicForm(_3)&&_3.valuesHaveChanged()){if(this.autoSaveEdits==true){var _4=this;if(this.expansionEditorShowSaveDialog){isc.confirm(this.expansionEditorSaveDialogPrompt,function(_8){if(_8){_4.saveExpansionDetail(_3,_2,_1)}else{_4.$84t(_1,_2)}})}else{this.saveExpansionDetail(_3,_2,_1)}
return}else{var _5=_3.getChangedValues(),_6=this.getRecordIndex(_1),_4=this;if(this.expansionEditorShowSaveDialog){isc.confirm("You have unsaved changes - do you want to save them now?",function(_8){if(_8){for(var _7 in _5){_4.setEditValue(_6,_7,_5[_7])}
_4.$84t(_1,_2)}else{_4.$84t(_1,_2)}});return}else{for(var _7 in _5){this.setEditValue(_6,_7,_5[_7])}
this.$84t(_1,_2);return}}}}
this.$84t(_1,_2)}
,isc.A.saveExpansionDetail=function isc_ListGrid_saveExpansionDetail(_1,_2,_3){var _4=this;_1.saveData(function(_5,_6,_7){if(_6){_3=_6;_4.$84t(_3,_2)}},{showPrompt:true,promptStyle:"cursor"})}
,isc.A.$84t=function isc_ListGrid__collapseRecord(_1,_2){_2=_2||(_1&&_1.$29a?_1.$29a.find("isExpansionComponent",true):null);if(_1.expanded){if(this.onCollapseRecord!=null&&!this.onCollapseRecord(_1))return;if(this.$74b&&this.$74b==_1)
delete this.$74b;this.removeEmbeddedComponent(_1,_2?_2:this.frozenFields?this.frozenFields.length:0);this.$74a--}
_1.expanded=false;this.$74c();this.markForRedraw()}
,isc.A.getCurrentExpansionComponent=function isc_ListGrid_getCurrentExpansionComponent(_1){if(isc.isA.Number(_1))_1=this.getRecord(_1);if(!_1.hasExpansionComponent)return null;var _2=_1.$29a?_1.$29a.find("isExpansionComponent",true):null;if(_2){return _2.members[0]}
return null}
,isc.A.getExpansionComponent=function isc_ListGrid_getExpansionComponent(_1){return this.$84d(_1,true,false)}
,isc.A.getRelatedDataSource=function isc_ListGrid_getRelatedDataSource(_1){return isc.DS.getDataSource(_1[this.recordDetailDSProperty])||isc.DS.get(this.detailDS)}
);isc.B._maxIndex=isc.C+56;isc.A=isc.ListGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.warnOnReusedFields=true;isc.A.autoFitExpandLengthThreshold=10;isc.A.$81l="dataChanged";isc.A.$70q=0;isc.A.$g2="boolean";isc.A.$604="set fields";isc.A.$12c="checkbox";isc.A.checkboxFieldDefaults={name:"_checkboxField",excludeFromState:true,canEdit:false,shouldPrint:false,canFilter:false,canGroupBy:false,canSort:false,canExport:false,canHide:false,canReorder:false,canDragResize:false,canHilite:false,$63f:true,type:"boolean",showDefaultContextMenu:false,showHeaderContextMenuButton:false,hoverHTML:"return null;",autoFreeze:true,showGroupSummary:false,showGridSummary:false,summaryValue:"&nbsp;"};isc.A.$63g=15;isc.A.$812="Getting listGrid fieldWidths. ";isc.A.gridComponents=["filterEditor","header","body","summaryRow"];isc.A.showComponentPropertyMap={header:"showHeader",filterEditor:"showFilterEditor",summaryRow:"showGridSummary"};isc.A.$880="body";isc.A.$881="header";isc.A.iconPadding=2;isc.A.$71t="summary";isc.A.$302="<HR>";isc.A.$303=["style='margin-left:",,"px;margin-right:",,"px;'"];isc.A.$g2="boolean";isc.A.$gx="text";isc.A.$54y={" ":true,"\n":true,"\r":true,"\r\n":true};isc.A.$54t="&nbsp;";isc.A.$687={text:true,TextItem:true,select:true,SelectItem:true,combobox:true,ComboBoxItem:true,comboBox:true,checkbox:true,CheckboxItem:true,date:true,DateItem:true,spinner:true,SpinnerItem:true,popUpTextArea:true,PopUpTextAreaItem:true};isc.A.$18r="false";isc.A.$18q="true";isc.A.$81m="$81n";isc.A.$81o=0;isc.A.$57m="expand";isc.A.printAutoFit=true;isc.A.printWrapCells=true;isc.A.printHeaderStyle="printHeader";isc.A.printMaxRows=100;isc.A.defaultCellHoverComponentWidth=300;isc.A.defaultCellHoverComponentHeight=150;isc.A.$20s="Arrow_Up";isc.A.$20t="Arrow_Down";isc.A.$304="Space";isc.A.$10j="Enter";isc.A.$51g="f2";isc.A.$51g="f2";isc.A.$20w="Escape";isc.A.$51h="Backspace";isc.A.$51i="Delete";isc.A.$51l="keyPress";isc.A.$27r="none";isc.A.$305="focus";isc.A.$12b="select";isc.A.$306="activate";isc.A.hiliteOnNativeRowFocus=true;isc.A._useFocusProxy=false;isc.A.showFocusOutline=!isc.Browser.isSafari;isc.A.showGridSummary=false;isc.A.invalidSummaryValue="&nbsp;";isc.A.includeInSummaryProperty="includeInSummary";isc.A.gridSummaryRecordProperty="isGridSummary";isc.A.groupSummaryRecordProperty="isGroupSummary";isc.A.$71t="summary";isc.A.recordSummaryBaseStyle="recordSummaryCell";isc.A.summaryRowConstructor="ListGrid";isc.A.summaryRowDefaults={showRollOver:false};isc.A.summaryRowHeight=20;isc.A.summaryRowStyle="gridSummaryCell";isc.A.$18q="true";isc.A.updateEditorItemsInPlace=true;isc.A.$307={time:true,TimeItem:true};isc.A.$g5="time";isc.A.$308={date:true,DateItem:true};isc.A.$68k={datetime:true,dateTime:true,DatetimeItem:true,DateTimeItem:true};isc.A.$g4="date";isc.A.$68l="datetime";isc.A.$309={popUpTextArea:true,PopUpTextAreaItem:true};isc.A.$31a={checkbox:true,CheckboxItem:true};isc.A.$g2="boolean";isc.A.$12c="checkbox";isc.A.$12i="CycleItem";isc.A.$31b={select:true,SelectItem:true};isc.A.$45k=["change","changed","defaultDynamicValue"];isc.A.$31c=["change","changed","defaultDynamicValue","keyPress","click","showIf","enableIf"];isc.A.$31d={};isc.A.$81p="column remap";isc.A.$31e=0;isc.A.$31f="rowNum,colNum,editCompletionEvent,success";isc.A.$88z=true;isc.A.$60m="toggle frozen fields";isc.A.$81q="rebuilding body";isc.A.selectionCanvasDefaults={opacity:20};isc.A.selectionUnderCanvasDefaults={};isc.A.rollOverCanvasDefaults={snapTo:"TL",width:"100%",height:"100%"};isc.A.rollUnderCanvasDefaults={snapTo:"TL",width:"100%",height:"100%"};isc.A.$616="header height changed";isc.A.$617="header visibility changed";isc.A.headerMenuButtonDefaults={snapTo:"R",canFocus:false,resizeFrom:"R",title:null,iconSpacing:0,click:function(){this.parentElement.grid.headerMenuButtonClick()},mouseOut:function(){var _1=isc.EH.getTarget();if(!_1||!this.parentElement.contains(_1))this.hide();this.Super("mouseOut",arguments)}};isc.A.predictScrollbarGap=true;isc.A.sortFieldAscendingText="Sort Ascending";isc.A.sortFieldDescendingText="Sort Descending";isc.A.clearSortFieldText="Clear Sort";isc.A.clearAllSortingText="Clear All Sorting";isc.A.clearFilterText="Clear Filter";isc.A.configureSortText="Configure Sort";isc.A.autoFitFieldText="Auto Fit";isc.A.autoFitAllText="Auto Fit All Columns";isc.A.fieldVisibilitySubmenuTitle="Columns";isc.A.freezeFieldText="Freeze ${title}";isc.A.unfreezeFieldText="Unfreeze ${title}";isc.A.groupByText="Group by ${title}";isc.A.ungroupText="Ungroup";isc.A.headerContextMenuConstructor="Menu";isc.A.headerContextMenuDefaults={hide:function(){this.Super("hide",arguments);if(this.grid){this.grid.$92m=false;if(this.grid.headerMenuButton&&this.grid.headerMenuButton.isVisible()){this.grid.headerMenuButton.hide()}}},doSort:function(_1,_2){if(_2=="unsort")this.grid.toggleSort(this.grid.getFieldName(_1),_2);else this.grid.sort(_1,_2)},canHover:true,showHover:true,cellHoverHTML:function(_1,_2,_3){return _1.prompt},groupField:function(_1){var _2=this.grid;if((_1.targetField&&_1.targetField.groupingMode)||((!_2.groupByField)||!_2.groupByField.contains(_1.fieldName))){_2.groupBy(_1.fieldName)}},ungroup:function(){this.grid.ungroup()}};isc.A.$31g="sort";isc.A.canMultiSort=true;isc.A.sortNumeralStyle="sortNumeral";isc.A.$73y="setSort";isc.A.chartConstructor="FacetChart";isc.A.chartType="Column";isc.A.groupIdField="groupId";isc.A.groupParentIdField="groupParentId";isc.A.retainOpenStateOnRegroup=true;isc.A.groupIconPadding=5;isc.A.showGroupSummary=false;isc.A.groupSummaryStyle="gridSummaryCell";isc.A.$31h={canDragSelect:true,canDragSelectText:true,canDragRecordsOut:true,canReorderRecords:true,canAcceptDroppedRecords:true};isc.A.$45l={bodyBackgroundColor:"backgroundColor",bodyStyleName:"styleName",fixedRecordHeights:"fixedRowHeights",fixedFieldWidths:"fixedColumnWidths",alternateRecordStyles:"alternateRowStyles",alternateRecordFrequency:"alternateRowFrequency",showAllRecords:"showAllRows",canSelectText:"canDragSelectText"};isc.B.push(isc.A.initWidget=function isc_ListGrid_initWidget(){this.Super("initWidget",arguments);if(this.showRecordComponents){delete this.showRecordComponents;this.setShowRecordComponents(true)}
if(this.fields==null&&this.defaultFields!=null){this.fields=isc.shallowClone(this.defaultFields)}else if(this.warnOnReusedFields&&this.fields!=null){if(this.fields.$696){this.logWarn("ListGrid initialized with this.fields attribute set to an array "+"which is already being displayed in another ListGrid instance. To reuse "+"standard field configuration across multiple ListGrids, use "+"listGrid.defaultFields rather than assigning directly to listGrid.fields.")}else{var _1;if(isc.isAn.Array(this.fields)){_1=this.fields.findIndex("$697",true)!=-1}else if(isc.isAn.Object(this.fields)){for(var _2 in this.fields){var _3=this.fields[_2];if(_3&&_3.$697){_1=true;break}}}
if(_1){this.logWarn("ListGrid initialized with this.fields attribute set to an array "+"containing fields which are already being displayed in another ListGrid "+"instance. To reuse standard field configuration across multiple ListGrids, use "+"listGrid.defaultFields rather than assigning directly to listGrid.fields.")}}}
if(this.canEditNew)this.listEndEditAction=this.rowEndEditAction="next";if(this.alwaysShowEditors){this.editByCell=false;this.selectionType="none";this.selectOnEdit=false;if(this.canGroup!=false){this.logInfo("grouping functionality is not supported when alwaysShowEditors is true."+" Explicitly disabling this.canGroup","inactiveEditorHTML");this.canGroup=false}
if(this.modalEditing){this.logInfo("modalEditing is not supported when alwaysShowEditors is true."+" Explicitly setting this.modalEditing to fales","inactiveEditorHTML");this.modalEditing=false}
this.editEvent="click";if(!this.isEditable()){this.logInfo("alwaysShowEditors has been set for this grid but canEdit is unset and "+"no fields are explicitly marked as editable. Defaulting this.canEdit to true. "+"Note that to avoid this override developers can explicitly specify canEdit "+"at the grid or field level","inactiveEditorHTML");this.canEdit=true}}
if(this.canExpandRecords||(this.fixedRecordHeights==false&&this.virtualScrolling==null))
{if(this.fixedRecordHeights)this.$82v=this.fixedRecordHeights;this.fixedRecordHeights=false;this.virtualScrolling=true}
if(this.canAddFormulaFields&&isc.FormulaBuilder==null){this.logInfo("Required modules for adding formula fields not present - setting "+"canAddFormulaFields to false.");this.canAddFormulaFields=false}
if(this.canAddSummaryFields&&isc.SummaryBuilder==null){this.logInfo("Required modules for adding summary fields not present - setting "+"canAddSummaryFields to false.");this.canAddSummaryFields=false}
if(this.loadingMessage==null||this.loadingMessage==isc.emptyString)
this.loadingMessage="&nbsp;";if(this.autoFitData!=null){this.$58o=this.overflow;this.setOverflow("visible")}
if(this.canCollapseGroup==false)this.groupStartOpen="all";this.$765=this.sortDirection?"ascending":"descending";this.setData(this.data?null:this.getDefaultData());this.setSelectionAppearance(this.selectionAppearance,true);this.$31i()}
,isc.A.getDefaultData=function isc_ListGrid_getDefaultData(){return[]}
,isc.A.$31i=function isc_ListGrid__setUpDragProperties(){this.canDrag=!this.canDragSelectText&&(this.canDrag||this.canDragRecordsOut||this.canReorderRecords||this.canDragSelect);this.canDrop=(this.canDrop||this.canDragRecordsOut||this.canReorderRecords);this.canAcceptDrop=(this.canAcceptDrop||this.canAcceptDroppedRecords||this.canReorderRecords)}
,isc.A.getEmptyMessage=function isc_ListGrid_getEmptyMessage(){if(isc.ResultSet&&isc.isA.ResultSet(this.data)&&!this.data.lengthIsKnown()){if(isc.Offline&&isc.Offline.isOffline()){return this.offlineMessage}
return this.loadingDataMessage==null?"&nbsp;":this.loadingDataMessage.evalDynamicString(this,{loadingImage:this.imgHTML(isc.Canvas.loadingImageSrc,isc.Canvas.loadingImageSize,isc.Canvas.loadingImageSize)})}
if(this.isOffline()){return this.offlineMessage}
return this.emptyMessage==null?"&nbsp;":this.emptyMessage.evalDynamicString(this,{loadingImage:this.imgHTML(isc.Canvas.loadingImageSrc,isc.Canvas.loadingImageSize,isc.Canvas.loadingImageSize)})}
,isc.A.isEmpty=function isc_ListGrid_isEmpty(){if(!this.data)return true;if(!this.fields||this.fields.length==0)return true;if(isc.ResultSet&&isc.isA.ResultSet(this.data)){if(this.data.isPaged()){if(!this.data.isEmpty())return false;var _1=this.getAllEditRows();if(_1&&_1.length>0){for(var i=0;i<_1.length;i++){if(_1[i]>=0)return false}}
return true}else{if(this.data.lengthIsKnown())return this.getTotalRows()<=0;else return true}}else{return(this.getTotalRows()<=0)}}
,isc.A.setData=function isc_ListGrid_setData(_1){if(this.data==_1)return;if(!this.preserveEditsOnSetData)this.discardAllEdits();this.clearLastHilite();if(this.data){this.$31j(this.data);if(this.data.$31k&&isc.isA.Function(this.data.destroy))
this.data.destroy()}
if(_1)this.data=_1;if(!this.data)return;this.$31m(this.data);this.regroup(true);this.calculateRecordSummaries(null,true);if(!this.selection||(this.data!=this.selection.data)){this.createSelectionModel()}
if(this.preserveEditsOnSetData)this.$31n();var _2=isc.ResultSet&&isc.isA.ResultSet(this.data)&&!this.data.lengthIsKnown();if(!_2&&this.$686()&&!this.$30a){this.startEditing(null,null,true,null,true)}
var _3=this.getSort();if(_3)this.setSort(_3);if(this.hilites!=null)this.applyHilites();if(isc.ResultSet&&isc.isA.ResultSet(this.data)&&this.body&&this.body.overflow=="visible")
{this.body.showAllRows=false}
if(this.summaryRow&&this.showGridSummary){this.summaryRow.$855()}
this.$74a=0;if(!this.canExpandMultipleRecords&&this.$74b)
delete this.$74b;if(this.$75p){this.setSelectedState(this.$75p);delete this.$75p}
this.updateFieldWidthsForAutoFitValue("setData called.");this.$25a("setData")}
,isc.A.getAutoFitExpandField=function isc_ListGrid_getAutoFitExpandField(){if(!this.autoFitFieldsFillViewport)return null;if(this.autoFitExpandField!=null){var _1=this.getField(this.autoFitExpandField);if(_1!=null&&this.fields&&this.fields.contains(_1)&&(!this.frozenFields||!this.frozenFields.contains(_1)))
{return _1}}
var _2=[],_3=[];if(this.fields){for(var i=0;i<this.fields.length;i++){var _1=this.fields[i];if(!_1.showValueIconOnly&&(_1.type=="text"||_1.type==null))
{if(!this.$54d&&_1.frozen)continue;_2.add(_1);if(_2[i]!=null&&_2[i].length!=null){_3.add(_2[i])}}}}
if(_3.length>0){_3.sortByProperty("length",Array.DESCENDING);if(_3.last().length>=this.autoFitExpandLengthThreshold||_3.length==_2.length)
{return _3[0]}}
if(_2.length>0){var i=0;_1=_2[i]
while(_1!=null&&_1.length!=null&&_1.length<this.autoFitExpandLengthThreshold)
{i++;_1=_2[i]}
return _1}
return null}
,isc.A.updateFieldWidthsForAutoFitValue=function isc_ListGrid_updateFieldWidthsForAutoFitValue(_1){if(!this.body||this.body.$773){return}
var _2=this.fields||[];for(var i=0;i<_2.length;i++){var _4=this.shouldAutoFitField(_2[i]);if(!_4)continue;var _5=this.getAutoFitWidthApproach(_2[i]);if(_5=="value"||_5=="both"){this.fields.$775=false;this.body.$773="Updating field widths for field auto-fit"+(_1?(":"+_1):".");break}}}
,isc.A.invalidateCache=function isc_ListGrid_invalidateCache(){if(this.getCheckboxFieldPosition()!=-1){this.deselectAllRecords();this.$63m(false)}
if(this.body&&this.body.$74k)delete this.body.$74k;return this.Super("invalidateCache",arguments)}
,isc.A.$66c=function isc_ListGrid__canSort(_1){if(!isc.isAn.Object(_1)){_1=this.getSpecifiedField(_1)}
if(_1==null)return false;var _2=(_1.canSort==false)?false:this.canSort==false?false:true;_2=_2&&this.$91k(_1);return _2}
,isc.A.$91k=function isc_ListGrid__canSortData(_1){var _2=isc.isAn.Object(_1)?_1:this.getSpecifiedField(_1);if(_2==null)return false;if(isc.isAn.Array(this.data))return true;if(_2.canSortClientOnly==true){if(isc.isA.ResultSet(this.data)){if(!this.data.lengthIsKnown()||!this.data.canSortOnClient()){return false}}}
return true}
,isc.A.$91d=function isc_ListGrid__canMultiSort(){var _1=(this.canMultiSort!=false)&&this.canSort&&this.$91l();return _1}
,isc.A.$91l=function isc_ListGrid__canMultiSortData(){var _1=this.canMultiSort;if(!this.data.setSort){_1=false}else if(this.getDataSource()&&_1!=false){_1=this.getDataSource().canMultiSort&&this.canSort}
return _1}
,isc.A.$766=function isc_ListGrid__getFieldSortDirection(_1){var _2;var _3=_1?_1.sortDirection:null;if(_3!=null){_2=Array.shouldSortAscending(_3)?"ascending":"descending"}else if(this.sortDirection!=null){_2=Array.shouldSortAscending(this.sortDirection)?"ascending":"descending"}else{_2=Array.shouldSortAscending(this.$765)?"ascending":"descending"}
return _2}
,isc.A.createSelectionModel=function isc_ListGrid_createSelectionModel(){this.invokeSuper(isc.ListGrid,"createSelectionModel",arguments);if(isc.isA.Canvas(this.body)){this.body.setSelection(this.selection);if(this.frozenBody)this.frozenBody.setSelection(this.selection)}}
,isc.A.destroySelectionModel=function isc_ListGrid_destroySelectionModel(){if(this.body)this.body.clearSelection();if(this.frozenBody)this.frozenBody.clearSelection();return this.Super("destroySelectionModel",arguments)}
,isc.A.setSelectionType=function isc_ListGrid_setSelectionType(_1,_2){this.selectionType=_1;if(this.body)this.body.selectionType=_1}
,isc.A.setSelectionAppearance=function isc_ListGrid_setSelectionAppearance(_1,_2){if(this.selectionAppearance==_1&&!_2)return;this.selectionAppearance=_1;if(_2&&this.selectionType==null){this.selectionType=(_1=="checkbox"?isc.Selection.SIMPLE:isc.Selection.MULTIPLE)}
if(this.completeFields!=null){var _3=[];for(var i=0;i<this.completeFields.length;i++){var _5=this.completeFields[i];if(this.isCheckboxField(_5))continue;_3.add(_5)}
this.setFields(_3)}}
,isc.A.setBodyOverflow=function isc_ListGrid_setBodyOverflow(_1){this.bodyOverflow=_1;if(this.body)this.body.setOverflow(this.bodyOverflow)}
,isc.A.setBodyStyleName=function isc_ListGrid_setBodyStyleName(_1){this.bodyStyleName=_1;if(this.body&&(!this.alternateBodyStyleName||!this.alternateRecordStyles)){this.body.setStyleName(_1)}}
,isc.A.setAlternateBodyStyleName=function isc_ListGrid_setAlternateBodyStyleName(_1){this.alternateBodyStyleName=_1;if(this.body&&this.alternateRecordStyles){this.body.setStyleName(_1||this.bodyStyleName)}}
,isc.A.setAlternateRecordStyles=function isc_ListGrid_setAlternateRecordStyles(_1){if(this.alternateRecordStyles==_1)return;this.alternateRecordStyles=_1;if(this.body&&(this.alternateBodyStyleName!=null)){if(_1)this.body.setStyleName(this.alternateBodyStyleName);else this.body.setStyleName(this.bodyStyleName)}}
,isc.A.hasInherentHeight=function isc_ListGrid_hasInherentHeight(_1,_2,_3,_4){if(this.inherentHeight!=null)return this.inherentHeight;if(this.autoFitData==isc.Canvas.VERTICAL||this.autoFitData==isc.Canvas.BOTH){return true}
return this.invokeSuper(isc.ListGrid,"hasInherentHeight",_1,_2,_3,_4)}
,isc.A.hasInherentWidth=function isc_ListGrid_hasInherentWidth(_1,_2,_3,_4){if(this.inherentWidth!=null)return this.inherentWidth;if(this.autoFitData==isc.Canvas.HORIZONTAL||this.autoFitData==isc.Canvas.BOTH){return true}
return this.invokeSuper(isc.ListGrid,"hasInherentWidth",_1,_2,_3,_4)}
,isc.A.setAutoFitData=function isc_ListGrid_setAutoFitData(_1){this.autoFitData=_1;if(this.$60v&&(_1=="both"||_1=="horizontal"||_1=="vertical")){delete this.$60v;delete this.canFreezeFields}
if(_1==null&&this.$58o){this.setOverflow(this.$58o)}else if(this.overflow!="visible"){this.$58o=this.overflow;this.setOverflow("visible")}
if(this.body){this.body.autoFitData=this.autoFitData;this.body.adjustOverflow()}}
,isc.A.setAutoFitExtraRecords=function isc_ListGrid_setAutoFitExtraRecords(_1){this.autoFitExtraRecords=_1;if(this.body){this.body.autoFitExtraRecords=_1;this.body.adjustOverflow()}}
,isc.A.setAutoFitMaxRecords=function isc_ListGrid_setAutoFitMaxRecords(_1){this.autoFitMaxRecords=_1;if(this.body){this.body.autoFitMaxRecords=_1;this.body.adjustOverflow()}}
,isc.A.setAutoFitMaxHeight=function isc_ListGrid_setAutoFitMaxHeight(_1){this.autoFitMaxHeight=_1;if(this.body){this.body.adjustOverflow()}}
,isc.A.getAutoFitMaxBodyHeight=function isc_ListGrid_getAutoFitMaxBodyHeight(){if(this.autoFitMaxHeight==null)return null;var _1=this.getVBorderPad();if(this.showHeader)_1+=this.headerHeight;if(this.showFilterEditor)_1+=this.filterEditorHeight;return this.autoFitMaxHeight-_1}
,isc.A.getAutoFitMinBodyHeight=function isc_ListGrid_getAutoFitMinBodyHeight(){var _1=this.getHeight(),_2=this.getVBorderPad();if(this.showHeader)_2+=this.headerHeight;if(this.showFilterEditor)_2+=this.filterEditorHeight;return(_1-_2)}
,isc.A.setAutoFitMaxColumns=function isc_ListGrid_setAutoFitMaxColumns(_1){this.autoFitMaxColumns=_1;if(this.body){this.body.autoFitMaxColumns=_1;this.body.adjustOverflow()}}
,isc.A.setAutoFitMaxWidth=function isc_ListGrid_setAutoFitMaxWidth(_1){this.autoFitMaxWidth=_1;if(this.body){this.body.autoFitMaxWidth=_1;this.body.adjustOverflow()}}
,isc.A.autoFitField=function isc_ListGrid_autoFitField(_1,_2){var _3=this.getField(_1),_4=this.getFieldNum(_3);if(_3==null||_4==-1)return;var _5=this.getFieldAutoFitWidth(_3);if(_5==null||_5==this.$26a[_4])return;var _6=this.shouldAutoFitField(_3);this.resizeField(_1,_5,!_6);if(_6)_3.$776=this.$26a[_4];if(_2)this.scrollColumnIntoView(_4,false);return _5}
,isc.A.autoFitFields=function isc_ListGrid_autoFitFields(_1){if(_1==null)_1=this.fields;for(var i=0;i<_1.length;i++){this.autoFitField(_1[i])}}
,isc.A.shouldAutoFitField=function isc_ListGrid_shouldAutoFitField(_1){if(_1.autoFitWidth!=null)return _1.autoFitWidth;return this.autoFitFieldWidths}
,isc.A.getFieldAutoFitWidth=function isc_ListGrid_getFieldAutoFitWidth(_1,_2){if(this.body==null)return;var _3=this.getAutoFitWidthApproach(_1),_4=_3!="value",_5=_3!="title",_6,_7,_8;var _9=this.getColNum(_1);if(_4){var _10=this.getFieldHeaderButton(_9);if(_10!=null){var _11=_10.getWidth(),_12=_10.getOverflow();_10.setWidth(_2||this.minFieldWidth||1);_10.setOverflow("visible");if(_10.label&&_10.label.isDirty())_10.label.redraw();_10.parentElement.reflow();_6=_10.getVisibleWidth();_10.setWidth(_11);_8=_6}}
if(_5){var _13=this.getAutoFitValueWidths([this.fields[_9]]),_7=_13?_13[_9]:null;if(_7!=null&&(_8==null||_8<_7))_8=_7}
if(_8!=null){if(_2==null||_2<this.minFieldWidth)_2=this.minFieldWidth;if(_2!=null&&_8<_2)_8=_2}
return _8}
,isc.A.setAutoFitWidth=function isc_ListGrid_setAutoFitWidth(_1,_2){var _3=this.getField(_1);if(_3==null)return;if(_3.autoFitWidth==_2)return;_3.autoFitWidth=_2;if(_2)this.autoFitField(_3)}
,isc.A.setAutoFitFieldWidths=function isc_ListGrid_setAutoFitFieldWidths(_1,_2){if(_1==this.autoFitFieldWidths)return;this.autoFitFieldWidths=_1
if(_1){this.$45m("autoFitFieldWidths enabled")}else if(!_2){if(this.showHeader&&this.headerHeight>0)this.updateHeader();this.fields.$775=false;this.$45m("autoFitFieldWidths disabled")}}
,isc.A.setAutoFitWidthApproach=function isc_ListGrid_setAutoFitWidthApproach(_1){if(this.autoFitWidthApproach==_1)return;this.autoFitWidthApproach=_1;if(this.showHeader&&this.headerHeight>0)this.updateHeader();this.fields.$775=false;this.$45m("autoFitFieldWidthApproach changed")}
,isc.A.$25a=function isc_ListGrid__markBodyForRedraw(_1){if(this.bodies){this.bodies.map("markForRedraw",_1)}else{this.markForRedraw(_1)}}
,isc.A.redraw=function isc_ListGrid_redraw(_1,_2,_3,_4){if(this.body){if(this.body.$30e){this.$45m("scrollbar change during animation");delete this.body.$30e}}
this.invokeSuper(isc.ListGrid,"redraw",_1,_2,_3,_4)}
,isc.A.$31m=function isc_ListGrid__observeData(_1){if(!this.isObserving(_1,"dataChanged")){this.observe(_1,"dataChanged","observer.dataChanged("+(isc.ResultSet&&isc.isA.ResultSet(_1)?"arguments[0],arguments[1],arguments[2],arguments[3],arguments[4])":")"))}
if(!this.isObserving(_1,"dataArrived")){if(isc.ResultSet&&isc.isA.ResultSet(_1)){this.observe(_1,"dataArrived","observer.$66d(arguments[0],arguments[1])")}else if(isc.ResultTree&&isc.isA.ResultTree(_1)){this.observe(_1,"dataArrived","observer.$66d(arguments[0])")}}
if(isc.isA.Tree(_1)){this.observe(_1,"changeDataVisibility","observer.$34u(node, newState)")}}
,isc.A.groupTreeChanged=function isc_ListGrid_groupTreeChanged(){if(this.$67i)return;if(!this.$31o&&!this.suppressEditRowRemap)this.$31n();var _1=this.getTotalRows()-1;if(this.body){if(this.body.lastOverRow>_1)delete this.body.lastOverRow;if(this.body.lastMouseOverRow>_1)delete this.body.lastMouseOverRow;if(this.body.$31p>_1)delete this.body.$31p}
if(this.$31q>_1)delete this.$31q;if(this.hilites)this.applyHilites();if(!this.$50w)this.$25a(this.$81l)}
,isc.A.$606=function isc_ListGrid__observeGroupData(_1){this.observe(_1,"dataChanged","observer.groupTreeChanged()");this.observe(_1,"changeDataVisibility","observer.$34u(node,newState)")}
,isc.A.$34u=function isc_ListGrid__folderToggleObservation(_1,_2){if(_1!=null&&this.hilites&&_2&&this.data.getLoadState(_1)==isc.Tree.LOADED){this.applyHilites(true)}
if(this.$34v){this.$31n();return}
if(this.body)this.body.finishRowAnimation();this.$31n();this.$25a('folderToggled')}
,isc.A.toggleFolder=function isc_ListGrid_toggleFolder(_1){if(this.data.isOpen(_1)){this.closeFolder(_1)}else{this.openFolder(_1);if(this.frozenBody)this.frozenBody.markForRedraw()}}
,isc.A.openFolder=function isc_ListGrid_openFolder(_1){if(this.folderOpened!=null){this.convertToMethod("folderOpened");if(this.folderOpened(_1)==false)return false}
if(this.animateFolders){this.animateOpen(_1)}else{this.data.openFolder(_1)}}
,isc.A.animateOpen=function isc_ListGrid_animateOpen(_1){var _2=this.data;if(_2.isOpen(_1))return;this.$34v=true;_2.openFolder(_1);delete this.$34v;var _3=_2.getParent(_1);if(_3&&!_2.isOpen(_3))return;if(_2.getLoadState(_1)!=isc.Tree.LOADED){this.$34z=_1;return}
this.$340(_1)}
,isc.A.closeFolder=function isc_ListGrid_closeFolder(_1){if(this.folderClosed!=null){this.convertToMethod("folderClosed");if(this.folderClosed(_1)==false)return false}
if(this.getEditRow()!=null){var _2=this.getRecord(this.getEditRow());if(this.data.isDescendantOf(_2,_1))this.endEditing()}
if(this.shouldAnimateFolder(_1))
this.animateClose(_1);else
this.data.closeFolder(_1)}
,isc.A.animateClose=function isc_ListGrid_animateClose(_1){if(!this.data.isOpen(_1))return;var _2=this.data.getParent(_1);if(_2&&!this.data.isOpen(_2)){return this.closeFolder(_1)}
var _3=this.data,_4=_3.indexOf(_1),_5=_3.getOpenList(_1).getLength()-1;this.startRowAnimation(false,_4+1,_4+_5+1,{target:this,methodName:"redraw"},this.animateFolderSpeed,this.animateFolderTime,this.animateFolderEffect,true);this.$34v=true;this.data.closeFolder(_1);delete this.$34v
if(this.body&&this.body.$279!=null){this.body.$34y=_1}}
,isc.A.$340=function isc_ListGrid__startFolderAnimation(_1){if(!this.shouldAnimateFolder(_1)){this.markForRedraw();return}
var _2=this.data,_3=_2.indexOf(_1),_4=_2.getOpenList(_1).getLength()-1;if(_3<0||_4<=0)return;this.startRowAnimation(true,_3+1,(_3+_4+1),{target:(this.bodyLayout||this.body),methodName:"redraw"},this.animateFolderSpeed,this.animateFolderTime,this.animateFolderEffect,true)}
,isc.A.$45q=function isc_ListGrid__addNodeToOpenState(_1,_2,_3,_4){if(!_1.isOpen(_2)||!_1.isLoaded(_2))return false;var _5=_1.getFolders(_2),_6=false;if(_5!=null){for(var i=0;i<_5.length;i++){_6=this.$45q(_1,_5[i],_3,_4)||_6}}
if(_4){var _8={};_8[_2.groupName]=_2.groupValue;_3.add(_8)}else{_3[_3.length]=_1.getPath(_2)}
return true}
,isc.A.dataChanged=function isc_ListGrid_dataChanged(_1,_2,_3,_4,_5){if(isc.$cv)arguments.$cw=this;this.$67i=true;var _6=_5||_1=="add"||_1=="remove";if(!_6&&_1=="replace"){if(_2==null||_3==null)_6=true;else{var _7=_11.get(_3);if(_7==null)_6=true;else{for(var i=0;i<this.fields.length;i++){if(this.shouldAutoFitField(this.fields[i])){var _9=this.getFieldName(this.fields[i]);if(_7[_9]!=_4[_9]){_6=true;break}}}}}}
this.calculateRecordSummaries(null,false);var _10=this.getGroupByFields();if(_10!=null&&!this.$52y){if(_1=="add"||_1=="remove"||_1=="replace"||(_1=="update"&&(_2==null||_3==null)))
{this.$52y=true}else if(_1=="update"){var _11=this.data;if(this.data.isGroupedOutput&&this.originalData)_11=this.originalData;var _7=_11.get(_3);if(_7==null)this.$52y=true;if(!this.$52y){var _12=(this.dataSource!=null?this.getDataSource().getPrimaryKeyFieldNames():[]);for(var i=0;i<_12.length;i++){if(_2[_12[i]]!=_7[_12[i]]){this.$52y=true;break}}}
if(!this.$52y)for(var i=0;i<_10.length;i++){var _13,_9=_10[i];if(_2[_9]!==_13&&!this.fieldValuesAreEqual(_9,_2[_9],_7[_9])){this.$52y=true;break}}
if(!this.$52y){var _14=this.getDataSource().getPrimaryKeyFieldNames()[0];var _15=this.data.find(_14,_7[_14]);isc.addProperties(_15,_7)}}}
if(this.$52y&&!this.$31o&&(!isc.isA.ResultSet(this.data)||this.data.lengthIsKnown()))
{this.$52y=false;this.$75p=this.getSelectedState(true);this.regroup();if(this.$75p){this.setSelectedState(this.$75p);delete this.$75p}}
if(this.body)this.body.finishRowAnimation();if(!this.$31o&&!this.suppressEditRowRemap)this.$31n();this.$74c();if(this.$686()&&!this.$30a){this.startEditing(null,null,true,null,true)}
var _16=this.getTotalRows()-1;if(this.body){if(this.body.lastOverRow>_16)delete this.body.lastOverRow;if(this.body.lastMouseOverRow>_16)delete this.body.lastMouseOverRow;if(this.body.$31p>_16)delete this.body.$31p}
if(this.$31q>_16)delete this.$31q;if(this.hilites)this.applyHilites();if(!this.$50w){if(_6)this.updateFieldWidthsForAutoFitValue(this.$81l);this.$25a(this.$81l);if(this.summaryRow&&this.showGridSummary)this.summaryRow.$855()}
if(this.$75p&&(!isc.isA.ResultSet(this.data)||this.data.lengthIsKnown()))
{this.setSelectedState(this.$75p);delete this.$75p}
delete this.$67i}
,isc.A.$66d=function isc_ListGrid__dataArrived(_1,_2){var _3=this.$60z();if(_3!=null&&_3!=-1){var _4=this.getFieldNum(_3),_5=this.getField(_4);if(_5&&_5.canSortClientOnly&&!this.$91k(_5)){this.$600(null);if(_3!=null&&this.header&&isc.isA.Toolbar(this.header)){this.header.deselectButton(_3);var _6=this.header.getButton(_3);if(_6)_6.setTitle(this.getHeaderButtonTitle(_6))}
if(this.sorter)this.sorter.setTitle(this.sorter.getTitle())}}
if(this.getCurrentCheckboxField()){var _7=this.getCheckboxFieldPosition(),_5=this.getField(_7),_8=this.checkboxFieldFalseImage||this.booleanFalseImage;if(isc.ResultSet&&isc.isA.ResultSet(this.data)&&!this.data.allMatchingRowsCached())
{var _9={disabled:true,showHover:true,prompt:this.selection.selectionRangeNotLoadedMessage,title:this.canSelectAll==false?"&nbsp;":this.getValueIconHTML(_8.replace(".","_Disabled."),_5)}
this.setFieldProperties(_7,_9)}else{var _9={disabled:false,showHover:false,prompt:null,title:this.canSelectAll==false?"&nbsp;":this.getValueIconHTML(_8,_5)}
this.setFieldProperties(_7,_9)}}
if(isc.screenReader&&this.body!=null){if(isc.isA.Tree(this.data)){var _10=_1;if(this.data.isOpen(_10)&&this.data.hasChildren(_10)){var _11=this.data.getChildren(_10);if(_11&&_11.length>0)_10=_11[0]}
var _12=this.data.indexOf(_10);this.body.$86a(_12,!this.hasFocus)}}
this.dataArrived(_1,_2)}
,isc.A.dataArrived=function isc_ListGrid_dataArrived(_1,_2){}
,isc.A.$31j=function isc_ListGrid__ignoreData(_1){if(this.body)this.body.finishRowAnimation();if(isc.isA.Tree(this.data))this.ignore(_1,"changeDataVisibility");this.ignore(_1,"dataChanged");if(this.isObserving(_1,"dataArrived")){this.ignore(_1,"dataArrived")}
if(this.selection)this.selection.deselectAll()}
,isc.A.applyFieldDefaults=function isc_ListGrid_applyFieldDefaults(_1){if(_1==null)return;for(var i=0;i<_1.length;i++){var _3=_1[i];if(_3==null)continue;if(!this.allowNamelessFields&&_3[this.fieldIdProperty]==null){if(_3.dataPath==null){this.logWarn("unable to process field with no name / dataPath:"+this.echo(_3));continue}
_3[this.fieldIdProperty]="field"+this.$70q++}
var _4=this.isRTL()?isc.Canvas.RIGHT:isc.Canvas.LEFT;var _5=this.getFieldDisplayType(_3);var _6=(_5!=null?isc.SimpleType.getBaseType(_5):null);if(isc.SimpleType.inheritsFrom(_5,"image")){_3.$31r=this.$30s}else if(_6==this.$gx){if(_3.width==null&&_3.length!=null){if(_3.length<15&&!_3.valueMap){_3.width=_3.length*7}}}else if(_6=="integer"||_6=="float"){_4=isc.Canvas.RIGHT
_3.$31r=this.$30n}else if(_6=="date"){var _7=(this.canEdit==true&&_3.canEdit!=false)||(this.canEdit!=false&&_3.canEdit==true);_3.width=_3.width||(_7?100:80);_4=isc.Canvas.RIGHT;_3.$31r=this.$30m}else if(_6=="time"){_3.width=_3.width||80;_3.$31r=this.$30p;_4=isc.Canvas.RIGHT}else if(_5=="binary"||_5=="blob"||_5=="upload"||_5=="imageFile"){_3.$31r=this.$30q}else if(_5=="link"){_3.$31r=this.$30r}else if(_5=="icon"){if(_3.width==null&&_3.autoFitWidth==null){if(this.autoFitIconFields!="none"){_3.autoFitWidth=true;_3.autoFitWidthApproach=(this.autoFitIconFields=="title")?"both":"value"}}
if(_3.width==null&&_3.autoFitWidth==null){if(this.autoFitIconFields!="none"){_3.autoFitWidth=true;_3.autoFitWidthApproach=(this.autoFitIconFields=="title")?"both":"value";_3.width=this.getDefaultFieldWidth(_3)}}
_3.align=_3.align||"center";_3.$31r=this.$59e;_3.title=_3.title||"&nbsp;"}else if(_5=="boolean"||_5=="checkbox"){if(_3.canToggle==null)_3.canToggle=true}
if(_3.formatCellValue!=null&&!isc.isA.Function(_3.formatCellValue))
isc.Func.replaceWithMethod(_3,"formatCellValue","value,record,rowNum,colNum,grid");if(this.showValueIconOnly(_3)){_4=isc.Canvas.CENTER;if(_3.width==null&&_3.autoFitWidth==null){if(this.autoFitIconFields!="none"){_3.autoFitWidth=true;_3.autoFitWidthApproach=(this.autoFitIconFields=="title")?"both":"value";_3.width=this.getDefaultFieldWidth(_3)}}}
if(!_3.align)_3.align=_4;if(_3.multiple&&_3.validateEachItem==null)_3.validateEachItem=true}}
,isc.A.getDisplayField=function isc_ListGrid_getDisplayField(_1){var _2=_1.displayField;if(_2!=null){var _3=_1.optionDataSource?isc.DataSource.get(_1.optionDataSource):null;if(_3!=null)_2=_3.getField(_2);else{_2=this.getField(_2);if(_2==null&&this.dataSource!=null){_2=this.getDataSource().getField(_1.displayField)}}}
return _2}
,isc.A.getFieldDisplayType=function isc_ListGrid_getFieldDisplayType(_1){var _2=this.getDisplayField(_1),_3;if(_2!=null)_3=_2.type;if(_3==null)_3=_1.type;return _3}
,isc.A.$31s=function isc_ListGrid__formatBooleanFieldAsImages(_1){if(this.booleanTrueImage==null&&this.booleanFalseImage==null)return false;var _2=this.getFieldDisplayType(_1),_3=(_2!=null?isc.SimpleType.getBaseType(_2):null);if(_3!=this.$g2)return false;return(!_1.suppressValueIcon&&_1.showValueIconOnly==null&&_1.valueIcons==null&&_1.formatCellValue==null)}
,isc.A.setFieldProperties=function isc_ListGrid_setFieldProperties(_1,_2){var _3,_4=this.getAllFields();var _5=_1;if(isc.isA.Number(_1)){_3=this.getField(_1)}else{var _6=isc.Class.getArrayItemIndex(_1,_4,this.fieldIdProperty);_3=_4[_6];_1=this.getFieldNum(_3)}
if(!_3)return;isc.addProperties(_3,_2);if(this.header!=null&&this.header.isDrawn()){var _7=this.getFieldHeader(_1),_8=_7.getMember(this.getLocalFieldNum(_1));if(_8)_8.setProperties(_2)}}
,isc.A.setFieldTitle=function isc_ListGrid_setFieldTitle(_1,_2){this.setFieldProperties(_1,{title:_2})}
,isc.A.setFieldIcon=function isc_ListGrid_setFieldIcon(_1,_2){var _3=this.getField(_1);this.setFieldProperties(_1,{icon:_2});if(_3&&_3.type=="icon"&&_3.cellIcon==null){delete _3.$59f
this.body.markForRedraw("Field icon changed")}}
,isc.A.setFieldCellIcon=function isc_ListGrid_setFieldCellIcon(_1,_2){this.setFieldProperties(_1,{cellIcon:_2});var _3=this.getField(_1);if(_3&&_3.type=="icon"){delete _3.$59f
this.body.markForRedraw("Field cell icon changed")}}
,isc.A.setAutoComplete=function isc_ListGrid_setAutoComplete(_1){this.autoComplete=_1}
,isc.A.setFieldAutoComplete=function isc_ListGrid_setFieldAutoComplete(_1,_2){_1=this.getField(_1);if(_1)_1.autoComplete=_2}
,isc.A.showFields=function isc_ListGrid_showFields(_1,_2){return this.showField(_1,_2)}
,isc.A.showField=function isc_ListGrid_showField(_1,_2){arguments.$cw=this;if(!isc.isAn.Array(_1)){_1=[_1]}
var _3=true,_4=true;var _5=this.completeFields!=null,_6=(!_5||this.frozenFields||this.$54d);for(var i=0;i<_1.length;i++){var _8=_1[i],_9=_8;_9=this.getSpecifiedField(_9);if(_9==null){_1[i]=null;this.logWarn("showField(): unable to find field object for field: "+_8+". Taking no action. Call setFields() to add new fields.")
continue}
_3=false;if(_9.showIf!=null)_9.showIf=null;if(_9.frozen)_6=true;if(this.spanMap&&this.spanMap[_9.name]!=null)_6=true;if(_6)continue;if(this.fields.contains(_9)){_1[i]=null;continue}
_4=false;_1[i]=_9}
if(_6){this.setFields(this.completeFields||this.fields);this.handleFieldStateChanged();return}
if(_3||_4)return;this.deriveVisibleFields();_1.removeEmpty();var _10=[],_11=0;for(var i=0;i<this.fields.length;i++){var _12=_1.indexOf(this.fields[i]);if(_12!=-1){_10[_12]=i;_11++;if(_11==_1.length)break}}
var _13=this.header;if(_13!=null){if(!_2)this.header.hPolicy="fill";this.header.addButtons(_1.duplicate(),_10)}
if(this.body){if(this.$30a){var _14=this.getEditRow(),_15=this.getRecord(_14),_16=this.getEditedRecord(_14),_17=false,_18=this.getEditForm().items,_19=_18.length-1,_20=_18[_19],_21=_20.colNum;_10.sort();for(var i=_10.length-1;i>=0;i--){var _22=i+1,_23=_10[i],_24=(_23-i);if(!_17&&this.$30u>=_23){this.$30u+=_22}
var _9=this.fields[_23],_25=this.getEditFormItemFieldWidths(_15)[_23],_26;while(_20!=null&&_21>=_24){_20.colNum+=_22;_19--;_20=(_19>=0)?_18[_19]:null;_21=(_20!=null)?_20.colNum:null}
var _27=this.body.getDrawArea();if(!this.editByCell&&_23>=_27[2]&&_23<=_27[3]){_26=this.getEditItem(_9,_15,_16,_14,_23,_25)}
if(_26!=null){this.$286.addItems([_26],_19+1)}}}
this.body.fields=this.normalFields||this.fields;this.setBodyFieldWidths(this.getFieldWidths());this.$75n(this.body);if(this.body.isDrawn())this.body.redraw("show field")}
if(this.sortField!=null){this.sortFieldNum=null;this.sortFieldNum=this.$60z()}
if(this.filterEditor!=null)this.filterEditor.showField(_1,_2);this.recalculateSummaries();if(this.summaryRow!=null&&this.showGridSummary){this.summaryRow.showField(_1,_2)}
this.markForRedraw("showField");this.handleFieldStateChanged()}
);isc.evalBoundary;isc.B.push(isc.A.hideFields=function isc_ListGrid_hideFields(_1,_2){return this.hideField(_1,_2)}
,isc.A.hideField=function isc_ListGrid_hideField(_1,_2){arguments.$cw=this;var _3=true,_4=true;if(!isc.isAn.Array(_1)){_1=[_1]}
var _5=[];var _6=(this.completeFields==null||this.frozenFields);var _7=this.$30a,_8=_7?this.getEditRow():null,_9=_7?this.getEditCol():null,_10=false;for(var i=0;i<_1.length;i++){var _12=_1[i],_13=_12;_13=this.getSpecifiedField(_13);if(_13==null){this.logWarn("hideField(): unable to find field object for field: "+_12+". Taking no action. To add this field use the setFields() method.");_1[i]=null;continue}
_3=false;_13.showIf=this.$18r;if(!this.fields.contains(_13)){_1[i]=null;continue}
_4=false;if(_6)continue;var _14=this.fields.indexOf(_13),_15=this.getFieldName(_14);_5.add(_14);if(_7){if(_9==_14)_10=true;var _16=this.getEditFormItem(_15);if(_16&&_16.hasFocus)_16.blurItem();this.clearEditValue(_8,_14,true)}}
if(_3||_4)return;if(_6){this.setFields(this.completeFields||this.fields);this.handleFieldStateChanged();return}
_1.removeEmpty();if(_7){if(_10){if(this.editByCell){this.cancelEditing(isc.ListGrid.PROGRAMMATIC);_7=false}else{var _17=_9-1,_18=false;while(_17>=0){if(!_5.contains(_17)&&this.canEditCell(_8,_17)&&this.$60w(_8,_17))
{_18=true;break}
_17--}
if(!_18){_17=_9+1;while(_17<this.fields.length){if(!_5.contains(_17)&&this.canEditCell(_8,_17)&&this.$60w(_8,_17))
{_18=true;break}
_17++}}
if(!_18){this.cancelEditing(isc.ListGrid.PROGRAMMATIC);_7=false}else{this.$31u(_8,_17,!this.getEditForm().hasFocus)}}}}
this.deriveVisibleFields();var _19=this.header;if(_19!=null){if(!_2)this.header.hPolicy="fill";var _20=[];for(var i=0;i<_5.length;i++){var _14=_5[i];var _21=this.header.getButton(_14);_20[_20.length]=_21;if(this.headerMenuButton&&this.headerMenuButton.masterElement==_21){this.headerMenuButton.depeer()}}
this.header.removeButtons(_20.duplicate());_20.map("destroy")}
var _22=[];if(_7){_5.sort();var _23=this.$286,_24=_23.getItems(),_25=_24.length-1,_26=_24[_25],_27=_26.colNum,_28=false;for(var i=_5.length-1;i>=0;i--){var _29=i+1,_30=_5[i];if(!_28&&this.$30u>_30){this.$30u-=_29;_28=true}
while(_26!=null&&_27>=_30){if(_27==_30)_22.add(_26);else _26.colNum-=_29;_25--;_26=(_25>=0)?_24[_25]:null;_27=(_26!=null)?_26.colNum:null}}}
if(this.body){this.body.fields=this.normalFields||this.fields;this.setBodyFieldWidths(this.getFieldWidths());this.$75n(this.body);if(this.body.isDrawn())this.body.redraw("hide field")}
if(_7&&_22.length>0){for(var i=0;i<_22.length;i++){var _26=_22[i];this.$286.removeItems([_26])}}
if(this.sortField!=null){this.sortFieldNum=null;this.sortFieldNum=this.$60z()}
if(this.filterEditor!=null)this.filterEditor.hideField(_1,_2);if(this.summaryRow&&this.showGridSummary){this.summaryRow.$855();this.summaryRow.hideField(_1,_2)}
this.$74c();this.handleFieldStateChanged()}
,isc.A.fieldIsVisible=function isc_ListGrid_fieldIsVisible(_1){var _2=_1;if(!isc.isAn.Object(_2))_2=this.getSpecifiedField(_1);return this.fields.contains(_2)}
,isc.A.showActionInPanel=function isc_ListGrid_showActionInPanel(_1){if(_1.name=="editNew")return true;return this.Super("showActionInPanel",arguments)}
,isc.A.setFields=function isc_ListGrid_setFields(_1){if(isc.$cv)arguments.$cw=this;if(_1!=null&&this.fields!=null&&this.fields!=_1&&this.completeFields!=_1)
{delete this.fields.$775}
if(!_1&&this.getDataSource()&&!this.getDataSource().hasFields()){this.logWarn("ListGrid.setFields() : neither this ListGrid nor its dataSource have fields")}
if(!this.booleanFalseImage&&!this.booleanTrueImage&&!this.booleanPartialImage){this.booleanTrueImage=isc.CheckboxItem?isc.CheckboxItem.getInstanceProperty("checkedImage"):null;this.booleanFalseImage=isc.CheckboxItem?isc.CheckboxItem.getInstanceProperty("uncheckedImage"):null;this.booleanPartialImage=isc.CheckboxItem?isc.CheckboxItem.getInstanceProperty("partialSelectedImage"):null;this.booleanImageWidth=isc.CheckboxItem?isc.CheckboxItem.getInstanceProperty("valueIconWidth"):null;this.booleanImageHeight=isc.CheckboxItem?isc.CheckboxItem.getInstanceProperty("valueIconHeight"):null}
var _2=this.$60z(),_3=(_2!=null&&this.fields?this.fields[_2]:null);var _4=this.$30a,_5=this.getEditRow(),_6=this.getEditCol(),_7=this.$286,_8=this.fields?this.getEditFieldName():null;if(this.completeFields==null)this.fields=[];this.completeFields=this.bindToDataSource(_1,this.canPickOmittedFields);if(this.completeFields==null)this.completeFields=[];if(this.shouldShowRowNumberField()){var _9=this.getCurrentRowNumberField(),_10=this.getRowNumberFieldPosition(),_11=!_9;if(_11&&_1&&_1.find(this.fieldIdProperty,"$74y")!=null)
{_11=false}
if(_11)_9=this.getRowNumberField();if(_11)this.completeFields.addAt(_9,_10);else this.completeFields.slideList([_9],_10)}else{var _9=this.getCurrentRowNumberField();if(_9)this.completeFields.remove(_9)}
if(this.shouldShowExpansionField()){var _12=this.getCurrentExpansionField(),_13=this.getExpansionFieldPosition(),_11=!_12;if(_11)_12=this.getExpansionField();if(_11)this.completeFields.addAt(_12,_13);else this.completeFields.slideList([_12],_13)}else{var _12=this.getCurrentExpansionField();if(_12)this.completeFields.remove(_12)}
if(this.shouldShowCheckboxField()){var _14=this.getCurrentCheckboxField(),_15=this.getCheckboxFieldPosition(),_11=!_14;if(_11&&_1&&_1.find(this.fieldIdProperty,"_checkboxField")){_11=false}
if(_11)_14=this.getCheckboxField();if(_15>this.completeFields.length)_15=this.completeFields.length;if(_11)this.completeFields.addAt(_14,_15);else this.completeFields.slideList([_14],_15)}else{var _14=this.getCurrentCheckboxField();if(_14)this.completeFields.remove(_14)}
if(this.shouldShowRemoveField()){var _16=this.completeFields.findIndex("isRemoveField",true),_17=(_16>=0)?this.completeFields[_16]:{excludeFromState:true,isRemoveField:true};if(_16==-1&&_1){_16=_1.findIndex("isRemoveField",true)}
if(!_17.$61a){isc.addProperties(_17,this.removeFieldDefaults,this.removeFieldProperties);if(_17.name==null)_17.name="$61b";if(_17.title==null)_17.title=this.removeFieldTitle;if(_17.cellIcon==null&&_17.formatCellValue==null){_17.formatCellValue=function(_32,_33,_34,_35,_36){if(!this.removeIconHTML){this.removeIconHTML=isc.Canvas.imgHTML(_36.removeIcon,_36.removeIconSize);this.unremoveIconHTML=isc.Canvas.imgHTML(_36.unremoveIcon,_36.removeIconSize)}
if(_36.recordMarkedAsRemoved(_34)){return this.unremoveIconHTML}else{return this.removeIconHTML}}}
if(_16==-1){this.completeFields.add(_17)}
_17.$61a=true}}
if(isc.DataSource)this.$45n();if(this.fieldState!=null)this.setFieldState();this.applyFieldDefaults(this.completeFields);this.completeFields.$696=true;this.completeFields.setProperty("$697",true);this.deriveVisibleFields();if(this.fields.length==0&&this.completeFields.length>0){this.logWarn("All specified fields for this component are hidden. Note that fields "+"may be hidden via 'showIf' or 'detail' attribute values. "+"In order to display data this grid must have some visible fields.")}
this.$26a=null;var _18,_19,_20;if(_4){this.storeUpdatedEditorValue();_18=this.fields.findIndex(this.fieldIdProperty,_8);if(_18!=-1&&!this.canEditCell(_5,_18))
_18=-1;if(_18==-1){var _21;if(!this.editByCell)_21=this.findNextEditCell(_5,0,1,true,true,false,true);if(_21!=null&&_21[0]==_5){_18=_21[1]}
if(_18==-1){this.cancelEditing(isc.ListGrid.PROGRAMMATIC);_4=false}}else{var _22=_7.getItem(_8);if(_22){_20=_22.hasFocus;if(_20)_7.$106()}
_19=true}
if(_4)this.hideInlineEditor(false,true)}
var _23=(this.canFreezeFields==null||this.$60v)&&this.fixedRecordHeights!=false&&this.fixedFieldWidths!=false&&this.autoFitData!="horizontal"&&this.autoFitData!="both"&&this.bodyOverflow!="visible";if(_23){if(this.completeFields.getProperty("overflow").contains("visible"))_23=false}
if(_23){this.$60v=true;this.canFreezeFields=true}else if(this.$60v){delete this.$60v;this.canFreezeFields=null}
if(this.canSelectCells)this.selection.numCols=this.fields.length;if(_3){var _24=this.fields.indexOf(_3);if(_24==-1)_24=null;this.$600(_24)}
if(this.showHeader&&this.headerHeight>0&&this.header!=null){this.updateHeader();if(this.body!=null){this.syncHeaderScrolling(this.body.getScrollLeft())}}
this.updateBody();if(this.filterEditor){this.filterEditor.updateDataSource(this.getDataSource());var _25=this.completeFields||[];this.filterEditor.setFields(_25.duplicate())}
this.layoutChildren(this.$604);if(this.$52b!=null){var _26={};for(var i in this.$52b){if(this.$52b[i]==null)continue;var _28=this.$52b[i].$31x,_29=(this.dataSource!=null?this.getDataSource().getPrimaryKeyFieldNames():[]);for(var _30 in _28){if(!this.fields.containsProperty(this.fieldIdProperty,_30)&&!_29.contains(_30)){_26[_30]=true;this.clearEditValue(_28,_30,true)}}}
_26=isc.getKeys(_26);if(_26.length>0){this.logInfo("'setFields()' removed the following fields which had pending edit "+"values for some row[s]: '"+_26.join("', '")+"'.\n"+"Edit values for these fields have been dropped.","gridEdit")}}
if(_4){if(_19){this.showInlineEditor(_5,_18,false,false,true);if(_20)_7.$11b(_31)}else{this.$31u(_5,_18,!_20)}
if(_20){var _31=this.getEditFieldName();if(_31==_8){_7.$11b(_31)}else{_7.focusInItem(_31)}}}
var _2=this.$60z();if(_2!=null&&this.$91k(_2))this.resort();if(this.summaryRow&&this.showGridSummary){this.summaryRow.setFields(this.completeFields.duplicate());this.summaryRow.$855()}
if(!this.$92q)this.defaultFieldState=this.getFieldState()}
,isc.A.addField=function isc_ListGrid_addField(_1,_2){return this.Super("addField",[_1,_2,this.completeFields],arguments)}
,isc.A.removeField=function isc_ListGrid_removeField(_1){return this.Super("removeField",[_1,this.completeFields],arguments)}
,isc.A.shouldShowCheckboxField=function isc_ListGrid_shouldShowCheckboxField(){if(this.fieldSourceGrid)return this.fieldSourceGrid.shouldShowCheckboxField();return(this.selectionAppearance==this.$12c&&this.selectionType!=this.$27r&&!isc.isA.TreeGrid(this))}
,isc.A.focusInFilterEditor=function isc_ListGrid_focusInFilterEditor(_1){if(this.filterEditor==null)return;var _2=_1!=null?this.getColNum(_1):null;this.filterEditor.startEditing(0,_2)}
,isc.A.filterByEditor=function isc_ListGrid_filterByEditor(){if(this.filterEditor!=null)this.filterEditor.performAction()}
,isc.A.bindToDataSource=function isc_ListGrid_bindToDataSource(_1,_2,_3,_4,_5,_6){var _7=false;var _8=this.invokeSuper(isc.ListGrid,"bindToDataSource",_1,_2,_3,_4,_5,_6);if(this.showDetailFields&&_8!=null){for(var i=0;i<_8.length;i++){var _10=_8[i];if(_10.showIf==null&&_10.detail==true){_10.showIf=this.$18r}
if(isc.isA.Number(parseInt(_10.name))&&parseInt(_10.name).toString()==_10.name)
{_7=true}
_10.$81f=true}}
this.$81g=!_7;return _8}
,isc.A.setFieldState=function isc_ListGrid_setFieldState(_1){if(this.completeFields==null)this.setFields(this.fields);if(_1==null&&this.fieldState!=null){if(isc.isA.String(this.fieldState)){_1=this.evalViewState(this.fieldState,"fieldState")}else{_1=this.fieldState}
this.completeFields=this.$31y(_1);this.fieldState=null;return}
_1=this.evalViewState(_1,"fieldState")
if(_1){this.completeFields=this.$31y(_1);this.refreshFields()}}
,isc.A.handleFieldStateChanged=function isc_ListGrid_handleFieldStateChanged(){this.fieldStateChanged();this.handleViewStateChanged()}
,isc.A.getCheckboxField=function isc_ListGrid_getCheckboxField(){var _1=this,_2={width:this.$63g+this.$65a(),getAutoFreezePosition:function(){return _1.getCheckboxFieldPosition()}};isc.addProperties(_2,this.checkboxFieldDefaults,this.checkboxFieldProperties);var _3=this.checkboxFieldFalseImage||this.booleanFalseImage;_2.title=(this.canSelectAll==false||this.selectionType=="single"?"&nbsp;":this.getValueIconHTML(_3,_2));return _2}
,isc.A.getCurrentCheckboxField=function isc_ListGrid_getCurrentCheckboxField(){var _1=this.completeFields||this.fields;if(!_1)return null;var _2=_1.find(this.fieldIdProperty,"_checkboxField");return!_2?null:isc.isAn.Array(_2)?_2[0]:_2}
,isc.A.$65a=function isc_ListGrid__getCheckboxFieldImageWidth(){return this.checkboxFieldImageWidth||this.booleanImageWidth||(isc.CheckboxItem?isc.CheckboxItem.getInstanceProperty("valueIconWidth"):null)}
,isc.A.$65b=function isc_ListGrid__getCheckboxFieldImageHeight(){return this.checkboxFieldImageHeight||this.booleanImageHeight||(isc.CheckboxItem?isc.CheckboxItem.getInstanceProperty("valueIconWidth"):null)}
,isc.A.isCheckboxField=function isc_ListGrid_isCheckboxField(_1){if(!_1||!_1.$63f)return false;else return true}
,isc.A.getCheckboxFieldPosition=function isc_ListGrid_getCheckboxFieldPosition(){if(this.fieldSourceGrid)return this.fieldSourceGrid.getCheckboxFieldPosition();if(this.selectionAppearance!="checkbox"||isc.isA.TreeGrid(this))return-1;var _1=0;if(this.showRowNumbers)_1+=1;if(this.showingGroupTitleColumn())_1+=1;if(this.canExpandRecords)_1+=1;return _1}
,isc.A.getSelectedState=function isc_ListGrid_getSelectedState(_1){if(!this.selection)return null;if(!this.dataSource||isc.isAn.emptyObject(this.getDataSource().getPrimaryKeyFields()))
{if(!_1){this.logWarn("can't getSelectedState without a DataSource "+"with a primary key field set")}
return null}
var _2=this.selection.getSelection()||[],_3=[];for(var i=0;i<_2.length;i++){_3[i]=this.getPrimaryKeys(_2[i])}
return isc.Comm.serialize(_3,false)}
,isc.A.setSelectedState=function isc_ListGrid_setSelectedState(_1){_1=this.evalViewState(_1,"selectedState")
if(!_1){if(this.selection)this.selection.deselectAll();return}
var _2=this.selection,_3=this.originalData||this.data;if(isc.ResultSet&&isc.isA.ResultSet(_3)&&!_3.lengthIsKnown())return;if(_3&&_2){_2.deselectAll();var _4=[];for(var i=0;i<_1.length;i++){var _6=_1[i];var _7=_3.findByKeys(_1[i],this.getDataSource());if(_7!=-1)_4.add(_3.get(_7))}
this.selection.selectList(_4)}}
,isc.A.getSortState=function isc_ListGrid_getSortState(){if(this.logIsInfoEnabled("sorting")){this.logInfo("\n"+"grid.sortFieldNum is: "+this.sortFieldNum+"\n"+"grid.sortField is: "+this.sortField+"\n"+"grid.getField(grid.sortFieldNum) is:\n"+isc.echoAll(this.getField(this.sortFieldNum))+"\n"+"-----------------------------------------\n"+"grid.$60z() is: "+this.$60z()+"\n"+"grid.getField(grid.$60z()) is:\n"+isc.echoAll(this.getField(this.$60z()))+"\n"+"","sorting")}
var _1=this.$60z(),_2=(_1!=null?this.getField(_1):null),_3=_2!=null?this.getFieldName(_2):null,_4=this.$766(_2),_5={fieldName:_3,sortDir:_4};if(this.$73p&&this.$73p.length>0){var _6=isc.shallowClone(this.$73p);_6.clearProperty("primarySort");_6.clearProperty("sortIndex");_6.clearProperty("normalizer");_6.clearProperty("context");_5.sortSpecifiers=_6}
return"("+isc.Comm.serialize(_5,false)+")"}
,isc.A.setSortState=function isc_ListGrid_setSortState(_1){_1=this.evalViewState(_1,"sortState")
if(!_1){this.clearSort();return}
if(_1.sortSpecifiers){this.setSort(isc.shallowClone(_1.sortSpecifiers))}else if(_1.fieldName==null){this.clearSort()}else{var _2=this.getFieldNum(_1.fieldName)
if(_2!=-1)this.sort(_2,_1.sortDir)}}
,isc.A.getViewState=function isc_ListGrid_getViewState(_1){var _2={selected:this.getSelectedState(true),field:this.getFieldState(),sort:this.getSortState(),hilite:this.getHiliteState(),group:this.getGroupState()};if(_1)return _2;return"("+isc.Comm.serialize(_2,false)+")"}
,isc.A.getGroupState=function isc_ListGrid_getGroupState(){var _1=this.getGroupByFields();if(_1==null)_1="";else _1=_1.join(",");return _1}
,isc.A.setGroupState=function isc_ListGrid_setGroupState(_1){if(_1)this.groupBy(_1.split(","))
else this.ungroup()}
,isc.A.setViewState=function isc_ListGrid_setViewState(_1){_1=this.evalViewState(_1,"viewState")
if(!_1)return;if(_1.field)this.setFieldState(_1.field);this.setSortState(_1.sort);this.setGroupState(_1.group);this.setHiliteState(_1.hilite);this.setSelectedState(_1.selected)}
,isc.A.handleViewStateChanged=function isc_ListGrid_handleViewStateChanged(){this.fireOnPause("viewStateChangeNotification",{target:this,methodName:"viewStateChanged"},0)}
,isc.A.getViewStateChangedFunction=function isc_ListGrid_getViewStateChangedFunction(){if(this.$84i==null){var _1=this;this.$84i=function(){if(_1.destroyed)return;_1.viewStateChanged()}}
return this.$84i}
,isc.A.viewStateChanged=function isc_ListGrid_viewStateChanged(){}
,isc.A.setDataSource=function isc_ListGrid_setDataSource(_1,_2){var _3=this.getDataSource();if(_3!=null&&_3!=_1&&_3.ID!=_1){var _4=this.getGroupByFields();if(_4!=null&&_4.length>0&&_4[0]!=null&&this.originalData!=null)
{this.ungroup()}}
this.Super("setDataSource",arguments);this.clearFilterValues();this.discardAllEdits()}
,isc.A.deriveVisibleFields=function isc_ListGrid_deriveVisibleFields(){this.fields.setArray(this.getVisibleFields(this.completeFields));this.deriveFrozenFields();this.refreshMasterIndex()}
,isc.A.refreshFields=function isc_ListGrid_refreshFields(){this.$92q=true;this.setFields(this.completeFields);delete this.$92q}
,isc.A.getFieldWidths=function isc_ListGrid_getFieldWidths(_1){var _2=this.$78q();if(this.autoFitFieldWidths&&!this.$813){this.$813=true;var _3=_2.duplicate(),_4=null;if(this.frozenFields!=null){var _5=this.freezeLeft;if(_5){_4=_3.slice(0,this.frozenFields.length);_3=_3.slice(this.frozenFields.length)}else{_4=_3.slice(this.frozenFields.length);_3=_3.slice(0,this.frozenFields.length)}}
var _6=this.getAvailableFieldWidth(true),_7=_3.sum();var _8=_6;if(_4!=null)_8-=_4.sum();var _9=true;if(this.autoFitWidthApproach!="title"){var _10=this.getDrawArea();if(!this.data||Array.isLoading(this.data.get(_10[0]))){_9=false}}
if(_7<_8&&_9){var _11=this.getAutoFitExpandField();if(_11){var _12=this.getFieldNum(_11);var _13=_8-_7;_2[_12]+=_13;var _14=this.getFieldHeaderButton(_12);if(_14&&_14.isDrawn()){_14.setWidth(_2[_12])}}}else if(_7>_8&&this.autoFitClipFields!=null){var _15;if(this.header&&this.header.isDrawn()){_15=this.header.hPolicy;this.header.hPolicy="fill"}
var _16=this.autoFitClipFields;for(var i=0;i<_16.length;i++){var _18=this.getField(_16[i]),_19=this.getFieldNum(_18);if(_18==null||_19<0)continue;if(!this.$54d&&_18.frozen){this.logInfo("auto-fitting field:"+_18.name+" is present in the autoFitClipFields array for this grid, but is"+" currently frozen. This is not supported - the field will not be clipped.","frozenFields");continue}
delete _18.$776;var _20=this.getFieldHeader(_19);if(_20&&_20.isDrawn()){_14=_20.getMember(this.getLocalFieldNum(_19));_14.setWidth(_18.width||"*");_14.setOverflow("hidden")}}
if(this.header&&this.header.isDrawn()){var _21=this.$812;if(_1!=null)_21+=_1;this.header.reflowNow(_21);this.header.hPolicy=_15;if(this.frozenHeader){this.frozenHeader.hPolicy="fill";this.frozenHeader.reflowNow(_21);this.frozenHeader.hPolicy=_15}}
_2=this.$78q()}
this.$813=false}
return _2}
,isc.A.$78q=function isc_ListGrid__getCalculatedFieldWidths(){var _1=this.header;if(isc.isA.Layout(_1)&&_1.isDrawn()){var _2=_1.members;for(var i=0;i<_2.length;i++){if(_2[i].isDirty())_2[i].redraw();if(_2[i].label!=null&&_2[i].label.isDirty())_2[i].label.redraw()}
var _4=_1.getMemberSizes();if(_4.length>0){if(this.allowMismatchedHeaderBodyBorder){var _5=_1.getLeftBorderSize()+_1.getLeftMargin(),_6=_1.getRightBorderSize()+_1.getRightMargin();if(_5!=0){_4[0]+=_5}}
var _7=_4.sum(),_8=_1.getInnerWidth();if(this.allowMismatchedHeaderBodyBorder){var _9=_7-_8;if(_9>0){_4[_4.length-1]+=Math.min(_9,_6)}}}
if(this.frozenFields){var _10=this.frozenHeader.getMemberSizes();_4.addListAt(_10,this.freezeLeft()?0:_4.length)}}else{var _4=this.getStretchResizeWidths()}
return _4}
,isc.A.getStretchResizeWidths=function isc_ListGrid_getStretchResizeWidths(){if(this.fields==null)return[];var _1=this.fields.getProperty("width"),_2=this.fields.getProperty("$776");for(var i=0;i<_1.length;i++){if(_2[i]!=null)_1[i]=_2[i]}
return isc.Canvas.applyStretchResizePolicy(_1,(this.innerWidth!=null?this.innerWidth:this.getAvailableFieldWidth()),this.minFieldSize)}
,isc.A.getAvailableFieldWidth=function isc_ListGrid_getAvailableFieldWidth(_1){if(_1==null){_1=this.autoFitData!="both"&&this.autoFitData!="horizontal"}
var _2=(!_1?this.getVisibleWidth():this.getWidth())-this.getHMarginBorderPad();var _3=this.$54u();if(_3){_2-=this.body?this.body.getScrollbarSize():this.getScrollbarSize()}
return _2}
,isc.A.getFieldWidth=function isc_ListGrid_getFieldWidth(_1){_1=this.getFieldNum(_1);if(_1==-1||!this.fields||_1>=this.fields.length)return null;if(this.body!=null)return this.getColumnWidth(_1);return this.getFieldWidths()[_1]}
,isc.A.$310=function isc_ListGrid__adjustFieldSizesForBodyStyling(_1,_2){if(_2==null)_2=false;if(_1==null||_1.length==0)return _1;if(!this.body){return _1}
_1[_1.length-1]=this.$311(_1[_1.length-1],_1.sum(),_2);_1[0]=this.$312(_1[0],_2);return _1}
,isc.A.$312=function isc_ListGrid__adjustFirstFieldForBodyStyling(_1,_2){if(!this.body)return _1;var _3=(_2?this.body.getTopBorderSize()+this.body.getTopMargin():this.body.getLeftBorderSize()+this.body.getLeftMargin());if(_3!=0)_1-=_3;return Math.max(0,_1)}
,isc.A.$311=function isc_ListGrid__adjustLastFieldForBodyStyling(_1,_2,_3){if(!this.body)return _1;var _4=(_3?this.body.getBottomBorderSize()+this.body.getBottomMargin():this.body.getRightBorderSize()+this.body.getRightMargin());if(_4!=0){var _5=_2-
(_3?this.body.getInnerHeight():this.body.getInnerWidth());if(_5>0){_1-=Math.min(_5,_4)}}
return Math.max(_1,1)}
,isc.A.setBodyFieldWidths=function isc_ListGrid_setBodyFieldWidths(_1){this.$26a=_1;var _2=_1;if(this.allowMismatchedHeaderBodyBorder){if(isc.isAn.Array(_1)){_1=this.$310(_1.duplicate())}}
var _3=this.frozenFields;if(_3){var _4=this.getFrozenSlots(_1);_1=this.getUnfrozenSlots(_1);this.frozenBody.setColumnWidths(_4);var _5=_4.sum();this.frozenBody.setWidth(_5);this.frozenBody.$pn=_5;if(this.frozenHeader)this.frozenHeader.setWidth(_5)}
if(this.body!=null)this.body.setColumnWidths(_1);if(this.$30a){var _6=this.$286.getItems(),_7=this.getRecord(this.getEditRow()),_8=this.getEditFormItemFieldWidths(_7);for(var i=0;i<_6.length;i++){var _10=_6[i].colNum;if(_6[i].width!=_8[_10])
_6[i].setWidth(_8[_10])}}}
,isc.A.getGridMembers=function isc_ListGrid_getGridMembers(){var _1=this.gridComponents,_2=[],_3;for(var i=0;i<_1.length;i++){var _5=_1[i],_6=null;if(isc.isA.Canvas(_5)){_6=_5}else if(isc.isA.String(_5)){if(!this.shouldShowGridComponent(_5))continue;switch(_5){case"filterEditor":if(this.filterEditor==null)this.makeFilterEditor();_6=this.filterEditor;break;case"header":if(this.header==null){this.makeHeader()}
_6=this.headerLayout||this.header;break;case"body":_3=true;if(this.body==null){this.createBodies()}
_6=this.bodyLayout||this.body;break;case"summaryRow":_6=this.getSummaryRow();break}}
if(_5!=null&&_6==null){_6=this.createCanvas(_5)}
_2.add(_6)}
if(!_3){this.logWarn("ListGrid specified with gridComponents:"+_1+".  This does not include a \"body\" entry. ListGrids with no body are unsupported,"+" displaying the body as the last member in the grid.");_2[_2.length]=this.createBodies()}
return _2}
,isc.A.shouldShowGridComponent=function isc_ListGrid_shouldShowGridComponent(_1){if(_1==this.$880)return true;if(_1==this.$881&&this.headerHeight==0){return false}
var _2=this.showComponentPropertyMap[_1];if(_2==null){this.showComponentPropertyMap[_1]=_2="show"+_1.substring(0,1).toUpperCase+_1.substring(1)}
return this[_2]}
,isc.A.createChildren=function isc_ListGrid_createChildren(){this.updateGridComponents();if(isc.Browser.isMoz&&isc.Browser.geckoVersion>=20051111){if(this.header){this.body.mozOutlineOffset="0px";if(this.body.mozOutlineColor==null)
this.body.mozOutlineColor=this.mozBodyOutlineColor}else{if(this.body.mozOutlineColor==null)
this.body.mozOutlineColor=this.mozBodyNoHeaderOutlineColor;this.body.mozOutlineOffset="-1px"}}
this._useNativeTabIndex=false}
,isc.A.updateGridComponents=function isc_ListGrid_updateGridComponents(){this.setMembers(this.getGridMembers())}
,isc.A.layoutChildren=function isc_ListGrid_layoutChildren(_1,_2,_3){if(this.body!=null){this.$45m(_1,_2,_3)}
isc.VLayout.$b4.layoutChildren.call(this,_1,_2,_3);if(this.body!=null){if(this.frozenBody){var _4=this.shrinkForFreeze&&this.body.hscrollOn;var _5=this.bodyLayout.getInnerHeight();if(_4)_5-=this.body.getScrollbarSize();this.frozenBody.setHeight(_5);if(this.frozenBody.$276())this.frozenBody.markForRedraw("height changed");this.frozenBody.$po=_5}}}
,isc.A.$45m=function isc_ListGrid__updateFieldWidths(_1,_2,_3){if(this.body==null)return;if(this.$92r)return;this.$92r=true;this.$26a=null;if(this.fields&&!this.fields.$775&&!this.skipAutoFitWidths){var _4=this.getAutoFitValueWidths(null,true);if(_4==null){this.fields.setProperty("$776",null)}else{for(var i=0;i<this.fields.length;i++){var _6=this.fields[i];if(_4[i]==null){_6.$776=null;continue}
var _7=_6.width;if(!isc.isA.Number(_7))_7=this.minFieldWidth;if(!isc.isA.Number(_7))_7=1;if(_7<_4[i]){_6.$776=_4[i];var _8=this.getFieldHeaderButton(i);if(_8!=null){_8.setWidth(_4[i]);_8.parentElement.reflow()}}else if(_6.$776!=null){_6.$776=null;var _8=this.getFieldHeaderButton(i);if(_8!=null){_8.setWidth(_7);_8.parentElement.reflow()}}}
this.fields.$775=true}}
var _9=this.getAvailableFieldWidth(true),_10=(_9!=this.innerWidth);this.innerWidth=_9;var _11=this.header,_12=(this.showHeader?this.headerHeight:0);if(_11!=null){var _13=(this.isRTL()&&this.$54u()?this.body.getScrollbarSize():0);_11.hPolicy="fill";if(this.frozenHeader)this.frozenHeader.hPolicy="fill";var _14=this.innerWidth,_15;if(this.autoFitData=="horizontal"||this.autoFitData=="both"){_15=this.getFieldWidths(_1+" [sizing horizontal auto-fit header]");_14=Math.max(_14,_15.sum())}
if(!this.leaveScrollbarGap&&_11.isDrawn()&&_14!=_11.getWidth()&&_1=="body scroll changed"){if(this.$54e||!this.resizeFieldsForScrollbar){_11.hPolicy="none"}}
var _16=this.headerLayout||_11;_16.resizeTo(_14,_12);var _17=(_1=="initial draw");if(!_11.isDrawn()&&(_17||this.isDrawn())){if(!this.frozenFields){if(_17)this.$20(_11);_11.draw()}else{if(_15==null){_15=this.getFieldWidths(_1+" [sizing frozen fields]")}
var _18=this.getFrozenSlots(_15);this.frozenHeader.setWidth(_18.sum());if(_17)this.$20(this.headerLayout);this.headerLayout.draw()}}
if(_11.isDrawn())_11.hPolicy="none";if(this.frozenHeader&&this.frozenHeader.isDrawn())this.frozenHeader.hPolicy="none";if(this.sorter){this.updateSorter()}}
if((!this.$26a||_10)&&((_11&&_11.isDrawn())||_12==0))
{var _15=this.getFieldWidths(_1);this.setBodyFieldWidths(_15);if(this.filterEditor&&this.filterEditor.body!=null){this.filterEditor.setBodyFieldWidths(_15.duplicate())}
if(this.summaryRow&&this.showGridSummary&&this.summaryRow.body!=null){this.summaryRow.setBodyFieldWidths(_15.duplicate())}
if(this.logIsDebugEnabled("layout")){this.logDebug("new field widths: "+this.$26a,"layout")}}
if((this.autoFitData=="horizontal"||this.autoFitData=="both")&&this.filterEditor){this.filterEditor.setWidth(this.body.getVisibleWidth())}
this.$92r=false}
,isc.A.getAutoFitValueWidths=function isc_ListGrid_getAutoFitValueWidths(_1,_2){if(this.data==null)return;if(isc.isA.ResultSet(this.data)){var _3=this.body.getDrawArea();if(!this.data.rangeIsLoaded(_3[0],_3[1])){if(_1==null){this.updateFieldWidthsForAutoFitValue("Delayed resize pending data load")}
return}}
var _4=(_1==null);if(_1==null){_1=[];for(var i=0;i<this.fields.length;i++){var _6=this.fields[i];if(this.shouldAutoFitField(_6)){if(!_2||this.getAutoFitWidthApproach(_6)!="title"){_1.add(_6);_4=false}}}}
if(_4)return;var _7=[],_8=[];for(var i=0;i<_1.length;i++){var _6=_1[i],_9=this.getFieldNum(_6);if(!this.$54d&&_6.frozen){_7.add(_9)}else{_8.add(_9)}}
var _10=[];for(var i=0;i<this.fields.length;i++){if(_1.contains(this.fields[i])){_10[i]=this.getDefaultFieldWidth(this.fields[i])}}
return _10}
,isc.A.getDefaultFieldWidth=function isc_ListGrid_getDefaultFieldWidth(_1){if(_1.type=="icon"){return(_1.iconWidth||_1.iconSize)+2*this.cellPadding+2*this.iconPadding}else if(this.showValueIconOnly(_1)){return this.getValueIconWidth(_1)+(2*this.cellPadding)+this.getValueIconRightPadding(_1)+this.getValueIconLeftPadding(_1)}
var _2=this.getLocalFieldNum(this.getFieldNum(_1));var _3;if(!this.$54d&&_1.frozen){_3=this.frozenBody.getColumnAutoSize(_2)}else{_3=this.body.getColumnAutoSize(_2)}
if(_3==null)_3=_1.width;return _3}
,isc.A.getAutoFitWidthApproach=function isc_ListGrid_getAutoFitWidthApproach(_1){if(_1.autoFitWidthApproach!=null)return _1.autoFitWidthApproach;return this.autoFitWidthApproach}
,isc.A.draw=function isc_ListGrid_draw(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;if(!this.readyToDraw())return this;this.prepareForDraw();this.invokeSuper(isc.ListGrid,"draw",_1,_2,_3,_4);this.body.getColumnSizes();for(var i=0;i<this.bodies.length;i++){var _6=this.bodies[i];if(_6.$29a)this.markForRedraw()}
var _7=this.$60z();if(this.header&&_7!=null){var _8=this.getFieldHeader(_7),_9=this.getFieldHeaderButton(_7);_8.selectButton(_9)}
this.bodyScrolled();if(this.sorter)this.updateSorter();return this}
,isc.A.$686=function isc_ListGrid__alwaysShowEditors(_1,_2){if(!_2&&this.getTotalRows()==0)return false;if(this.alwaysShowEditors)return true;var _3=_1?[_1]:this.fields;if(_3){for(var i=0;i<_3.length;i++){if(_3[i].alwaysShowEditors){return true}}}
return false}
,isc.A.prepareForDraw=function isc_ListGrid_prepareForDraw(){if(this.completeFields==null)this.setFields(this.fields);if(this.getEditRow()==null){var _1=this.$686();if(_1)this.startEditing(null,null,true,null,true)}
if(this.groupByField){var _2;if(isc.isA.Array(this.groupByField)){_2=this.groupByField}else{_2=[this.groupByField]}
this.groupByField=null;this.groupBy(_2)}
this.createChildren()}
,isc.A.getGroupByFields=function isc_ListGrid_getGroupByFields(){var _1=this.groupByField;if(_1!=null&&!isc.isAn.Array(_1)){_1=[_1]}
return _1}
,isc.A.destroy=function isc_ListGrid_destroy(_1){if(this._dragLine){this._dragLine.destroy();this._dragLine=null}
if(this.$314)this.$314.destroy();if(this.$58q)this.$58q.destroy();if(this.cellContextMenu)this.cellContextMenu.destroy();if(this.$286){this.$286.destroy();delete this.$286;delete this.$30a}
if(this.data){if(this.data.$31k&&isc.isA.Function(this.data.destroy)){this.data.destroy()}else{this.$31j(this.data);delete this.data}}
if(this.selection){this.destroySelectionModel()}
if(this.selectionCanvas)this.selectionCanvas.destroy();if(this.selectionUnderCanvas)this.selectionUnderCanvas.destroy();if(this.rollOverCanvas)this.rollOverCanvas.destroy();if(this.rollUnderCanvas)this.rollUnderCanvas.destroy();this.$67a();var _2=this.getRecordComponentPool();if(_2){for(var i=0;i<_2.length;i++){var _4=_2[i];if(_4&&_4.destroy&&!_4.destroyed){if(!_4.dontAutoDestroy)_4.destroy();else _4.deparent()}}}
if(this.$89r!=null){for(var _5 in this.$89r){var _4=this.$89r[_5];if(!_4||_4.destroyed||_4.destroying||_4.$65i)
{continue}
if(!_4.dontAutoDestroy)_4.destroy();else _4.deparent()}}
this.Super("destroy",arguments)}
,isc.A.redrawHeader=function isc_ListGrid_redrawHeader(){if(this.header)this.header.markForRedraw()}
,isc.A.getBaseStyle=function isc_ListGrid_getBaseStyle(_1,_2,_3){if(this.canEdit==true&&!this.isPrinting){if(this.editFailedBaseStyle&&this.cellHasErrors(_2,_3))
return this.editFailedBaseStyle;if(this.editPendingBaseStyle&&this.cellHasChanges(_2,_3,false))
return this.editPendingBaseStyle}
if(_1&&this.recordBaseStyleProperty&&_1[this.recordBaseStyleProperty])
return _1[this.recordBaseStyleProperty];var _4=this.getField(_3);if(_4&&_4.baseStyle)return _4.baseStyle;if(_4&&_4.type=="summary"&&this.recordSummaryBaseStyle)
return this.recordSummaryBaseStyle;if(_4&&_4.frozen&&!this.$54d&&this.frozenBaseStyle){return this.frozenBaseStyle}
if(this.isPrinting&&(this.printBaseStyle!=null))return this.printBaseStyle;var _5=this.baseStyle;if(_5==null){if(this.cellHeight!=this.normalCellHeight||this.fastCellUpdates||!this.shouldFixRowHeight(_1,_2)||(_1!=null&&_1.$29a))
{_5=this.tallBaseStyle}else{_5=this.normalBaseStyle}}
return _5}
,isc.A.getCellCSSText=function isc_ListGrid_getCellCSSText(_1,_2,_3){if(_1){var _4=_1[this.recordCSSTextProperty];if(_4!=null)return _4}
var _4;if(this.recordMarkedAsRemoved(_1)&&this.removedCSSText){_4=this.removedCSSText}else{if(this.isEditable()){if(this.editFailedBaseStyle==null&&this.editFailedCSSText&&this.cellHasErrors(_2,_3))
{_4=this.editFailedCSSText}else if(this.editPendingBaseStyle==null&&this.editPendingCSSText&&this.cellHasChanges(_2,_3,false))
{_4=this.editPendingCSSText}}}
_4=this.getRecordHiliteCSSText(_1,_4,this.getField(_3));return _4}
,isc.A.getRawCellValue=function isc_ListGrid_getRawCellValue(_1,_2,_3,_4){var _5,_6,_7;if(_4){_6=_3
_5=this.completeFields?isc.Class.getArrayItem(_6,this.completeFields,this.fieldIdProperty):this.getField(_6)}else{_5=this.fields[_3];_6=_5[this.fieldIdProperty]}
_7=_5?_5.dataPath:null;if(_6==null&&_7==null)return this.emptyCellValue;var _8,_9,_10;if(this.rowEditNotComplete(_2)){_8=this.$30y(_2,_3)}
if(_8!==_10){return _8}else{if(_1==null)return this.emptyCellValue;if(this.data.getFieldValue&&_5){_9=this.data.getFieldValue(_1,_6,_5)}else if(_1.ownerDocument&&_5){_9=isc.xml.getFieldValue(_1,_6,_5)}else{if(_7!=null){_9=isc.Canvas.$70o(this.$840(_7),_1,this,true)}else{_9=_1[_6]}}}
if(this.shouldShowRecordSummary(_5,_1)&&!this.shouldApplyRecordSummaryToRecord(_5))
{_9=this.getRecordSummary(_2,_5)}
if(_5&&_5.getRawCellValue){isc.Func.replaceWithMethod(_5,"getRawCellValue","viewer,record,recordNum,field,fieldNum,value");_9=_5.getRawCellValue(this,_1,_2,_5,_3,_9)}
if(_5){if(_5.userFormula)_9=this.getFormulaFieldValue(_5,_1);if(_5.userSummary)this.getSummaryFunction(_5)}
return _9}
,isc.A.$840=function isc_ListGrid__trimDataPath(_1){if(!_1)return _1;var _2=_1.trim(isc.Canvas.$70l);if(!_2.contains(isc.Canvas.$70l))return _1;var _3=this.getFullDataPath();if(_3==null||_3=="")return _2;_3=_3.trim(isc.Canvas.$70l);var _4=_3.split(isc.Canvas.$70l);var _5=_2.split(isc.Canvas.$70l);for(var i=0;i<_4.length;i++){if(_4[i]!=_5[i]){break}}
if(i==0)return _1;var _7="";for(var j=i;j<_5.length;j++){_7+=_5[j];_7+="/"}
return _7}
,isc.A.shouldShowRecordSummary=function isc_ListGrid_shouldShowRecordSummary(_1,_2){if(_1&&_1.type==this.$71t){if(_2[this.groupSummaryRecordProperty]){return(_1.summaryFunction==null&&_1.getGroupSummary==null)}else if(_2[this.gridSummaryRecordProperty]){return(_1.summaryFunction==null&&_1.getGridSummary==null)}
return true}
return false}
,isc.A.getCellValue=function isc_ListGrid_getCellValue(_1,_2,_3,_4){if(_4==null)_4=this.getFieldBody(_3);if(_1==null){if(this.showNewRecordRow&&this.$299(_2)){return this.getNewRecordRowCellValue()}
_1=this.$300(_2,_3)}else{if(_1[this.isSeparatorProperty])return this.$302;if(_1.$52e){var _5=this.fields[_3],_6=this.getGroupTitleField(),_7;if(_6==null){_7=_5.$84g}else{_7=_5.name==_6}
if(this.singleCellGroupHeaders()||_7){return this.getGroupNodeHTML(_1,_4)}else if(!this.showGroupSummaryInHeader){return"&nbsp;"}}}
var _8=this.fields[_3],_9=null;if(_8==null)return"";if(this.$92s&&_8.summaryValue){return _8.summaryValue}
if(this.isCheckboxField(_8)){var _10;if(!this.body.canSelectRecord(_1)){_10="[SKINIMG]/blank.gif"}else{var _11=this.selection.isSelected(_1)?true:false;_10=_11?(this.checkboxFieldTrueImage||this.booleanTrueImage):(this.checkboxFieldFalseImage||this.booleanFalseImage)}
if(_1&&_1[this.recordEnabledProperty]==false){_10=_10.replace(".","_Disabled.")}
var _12=this.getValueIconHTML(_10,_8);return _12}
var _10,_13=this.showValueIconOnly(_8),_14;if(_1!=null){if(_1[this.singleCellValueProperty]!=null){return _1[this.singleCellValueProperty]}
if(Array.isLoading(_1)){if(!isc.Browser.isSafari||_3==0){return this.loadingMessage}
return"&nbsp;"}
_14=(this.$30a&&this.$285==_2&&(!this.editByCell||this.$30u==_3)&&this.canEditCell(_2,_3));if(_14){if(_4&&_4.$92p){_9=this.getInactiveEditorCellValue(_1,_2,_3)}else{_9=this.getEditItemCellValue(_1,_2,_3)}}else if(this.$68z(_3)&&this.canEditCell(_2,_3)){_9=this.getInactiveEditorCellValue(_1,_2,_3);_14=true}else{var _8=this.fields[_3],_15,_16;if(_8.displayField!=null){_16=!_8.valueMap&&!_8.getCellValue&&this.$425(_8);if(_16){var _17=_8.displayField;_15=this.getRawCellValue(_1,_2,_17,true)}}
_9=this.getRawCellValue(_1,_2,_3);if(_8.getCellValue){isc.Func.replaceWithMethod(_8,"getCellValue","viewer,record,recordNum,field,fieldNum,value");_9=_8.getCellValue(this,_1,_2,_8,_3,_9)}
_10=this.getValueIcon(_8,_9,_1,_2);if(!_13){var _18=_8.valueMap;if(_18){if(isc.isA.String(_18))_18=this.getGlobalReference(_18);if(!isc.isAn.Array(_18)){if(isc.isAn.Array(_9)){var _19=[];for(var i=0;i<_9.length;i++){var _21=isc.getValueForKey(_9[i],_18,_9[i]);_19[_19.length]=_21}
_9=_19}else{_9=isc.getValueForKey(_9,_18)}}}}
if(_16)_9=_15}}
if(!_14){if(_1&&((_1[this.groupSummaryRecordProperty]&&!this.shouldShowGroupSummary(_8))||(_1[this.gridSummaryRecordProperty]&&!this.shouldShowGridSummary(_8))))
{_9=this.emptyCellValue}else{var _22=null;if(_10!=null){_22=this.getValueIconHTML(_10,_8)}
if(_13){if(!_22||isc.isAn.emptyString(_22))_22=this.emptyCellValue;_9=_22}else{_9=this.$315(_9,_1,_8,_2,_3);var _23=this.getFieldHilites(_1,_8);if(_23)_9=this.applyHiliteHTML(_23,_9);if(_22){if(_8.valueIconOrientation!=isc.Canvas.RIGHT)
_9=_22+_9;else
_9=_9+_22}}
if(this.isEditable()&&this.showErrorIcons&&this.cellHasErrors(_2,_3)){_9=this.getErrorIconHTML(_2,_3)+_9}}}
var _24=this.isGrouped?this.getGroupTitleField():null;if(_24&&this.fields[_3].name==_24){var _25=isc.Canvas.spacerHTML(this.groupIndentSize+this.groupLeadingIndent,1);_9=_25+_9}
return _9}
);isc.evalBoundary;isc.B.push(isc.A.getTitleFieldValue=function isc_ListGrid_getTitleFieldValue(_1){var _2=this.getDataSource().getTitleField(),_3=this.getCellValue(_1,this.getRecordIndex(_1),this.getFieldNum(_2),this.body);if(!_3||_3==""){_3=this.getRawCellValue(_1,this.getRecordIndex(_1),_2,true)}
return _3}
,isc.A.getRawValue=function isc_ListGrid_getRawValue(_1,_2){var _3=this.getRecordIndex(_1),_4=this.getSpecifiedField(_2);if(_1[_4[this.fieldIdProperty]]!=null)return _1[_4[this.fieldIdProperty]];else return this.getRawCellValue(_1,_3,_2,true)}
,isc.A.getFormattedValue=function isc_ListGrid_getFormattedValue(_1,_2,_3){var _4=this.getRecordIndex(_1),_5=this.getSpecifiedField(_2),_6=this.getFieldNum(_2);if(this.fieldIsVisible(_5)&&_3==null)
return this.getCellValue(_1,_4,_6,this.body);if(_3==null)_3=this.getRawValue(_1,_2);var _7=_5?_5.valueMap:null;if(_7){if(isc.isA.String(_7))_7=this.getGlobalReference(_7);if(!isc.isAn.Array(_7)){if(isc.isAn.Array(_3)){var _8=[];for(var i=0;i<_3.length;i++){var _10=isc.getValueForKey(_3[i],_7,_3[i]);_8[_8.length]=_10}
_3=_8}else{_3=isc.getValueForKey(_3,_7)}}}
var _11=this.$315(_3,_1,_5,_4,_6);return _11}
,isc.A.getSpecifiedFieldWidth=function isc_ListGrid_getSpecifiedFieldWidth(_1){_1=this.getField(_1);var _2=_1[this.fieldIdProperty],_3=this.header?this.header.members:null,_4=this.frozenFields&&this.frozenHeader?this.frozenHeader.members:null,_5;if(_3||_4){var _6;if(_3)_6=_3.find(this.fieldIdProperty,_2);if(!_6&&_4){_6=_4.find(this.fieldIdProperty,_2)}
if(_6&&_6.$pn&&isc.isA.Number(_6.$pn)){_5=_6.$pn}}
return _5}
,isc.A.getValueIconHTML=function isc_ListGrid_getValueIconHTML(_1,_2){var _3=_2.imageURLPrefix||_2.baseURL||_2.imgDir,_4=_2.imageURLSuffix,_5=this.getValueIconWidth(_2),_6=this.getValueIconHeight(_2),_7=this.getValueIconLeftPadding(_2),_8=this.getValueIconRightPadding(_2);if(_4!=null)_1+=_4;var _9=isc.Canvas.$xq(_1,_3,_5,_6,_7,_8,null,this);return _9}
,isc.A.getCellAlign=function isc_ListGrid_getCellAlign(_1,_2,_3){if(_1&&_1[this.singleCellValueProperty]!=null&&(!this.showSingleCellCheckboxField(_1)||!this.isCheckboxField(this.getField(_3))))
{return this.isRTL()?isc.Canvas.RIGHT:isc.Canvas.LEFT}
var _4=_3;var _5=this.fields[_4];if(!_5)return isc.Canvas.LEFT;if(_5.userFormula||_5.userSummary){return this.isRTL()?isc.Canvas.LEFT:isc.Canvas.RIGHT}
return _5.cellAlign||_5.align}
,isc.A.showSingleCellCheckboxField=function isc_ListGrid_showSingleCellCheckboxField(_1){return(this.getCurrentCheckboxField()!=null)&&_1&&!_1.$52e&&!_1[this.isSeparatorProperty]}
,isc.A.$425=function isc_ListGrid__useDisplayFieldValue(_1){if(!_1||_1.valueMap||_1.displayField==null||(_1.displayField==_1.name))
{return false}
if(_1.optionDataSource==null){if(_1.displayValueFromRecord==false)return false;return true}
var _2=_1.autoFetchDisplayMap;if(_2==null)_2=this.autoFetchDisplayMap;if(_2)return false;if(_1.displayValueFromRecord!=null)return _1.displayValueFromRecord;return(_1.valueField==null||_1.valueField==_1.name)&&(isc.DS.get(_1.optionDataSource)==this.getDataSource())}
,isc.A.getValueIcon=function isc_ListGrid_getValueIcon(_1,_2,_3,_4){if(!_1.valueIcons||_1.suppressValueIcon){if(this.$31s(_1)){var _5=(_2?this.booleanTrueImage:this.booleanFalseImage);_4=(_4!=null)?_4:this.findRowNum(_3);var _6=_1.masterIndex;if(!this.canEditCell(_4,_6)&&_1.canToggle){_5=isc.Img.urlForState(_5,false,false,"Disabled")}
if(_5==null)_5=isc.Canvas.getImgURL(isc.Canvas.$wz);return _5}
return null}
var _7=_1.valueIcons[_2];return _7}
,isc.A.getValueIconLeftPadding=function isc_ListGrid_getValueIconLeftPadding(_1){return(_1&&_1.valueIconLeftPadding!=null?_1.valueIconLeftPadding:this.valueIconLeftPadding||0)}
,isc.A.getValueIconRightPadding=function isc_ListGrid_getValueIconRightPadding(_1){return(_1&&_1.valueIconRightPadding!=null?_1.valueIconRightPadding:this.valueIconRightPadding||0)}
,isc.A.showValueIconOnly=function isc_ListGrid_showValueIconOnly(_1){if(_1.showValueIconOnly!=null)return _1.showValueIconOnly;if(_1.valueIcons!=null&&_1.valueMap!=null)return true;return this.$31s(_1)}
,isc.A.getValueIconWidth=function isc_ListGrid_getValueIconWidth(_1){if(this.isCheckboxField(_1))return this.$65a();if(this.$31s(_1))return this.booleanImageWidth;return(_1.valueIconWidth!=null?_1.valueIconWidth:(_1.valueIconSize!=null?_1.valueIconSize:(this.valueIconWidth!=null?this.valueIconWidth:this.valueIconSize)))}
,isc.A.getValueIconHeight=function isc_ListGrid_getValueIconHeight(_1){if(this.isCheckboxField(_1))return this.$65b();if(this.isExpansionField(_1))return this.$72z();if(this.$31s(_1))return this.booleanImageHeight;return(_1.valueIconHeight!=null?_1.valueIconHeight:(_1.valueIconSize!=null?_1.valueIconSize:(this.valueIconHeight!=null?this.valueIconHeight:this.valueIconSize)))}
,isc.A.$299=function isc_ListGrid__isNewRecordRow(_1){return this.showNewRecordRow&&(_1==this.getTotalRows()-1)}
,isc.A.getNewRecordRowCellValue=function isc_ListGrid_getNewRecordRowCellValue(){return'<div align="center">'+this.newRecordRowMessage+'</div>'}
,isc.A.getErrorIconHTML=function isc_ListGrid_getErrorIconHTML(_1,_2){var _3=this.getCellErrors(_1,_2);if(_3==null)return isc.$ad;var _4=this.imgHTML(this.errorIconSrc,this.errorIconWidth,this.errorIconHeight,null," isErrorIcon='true'");if(isc.Browser.isIE&&this.$30a&&this.getEditRow()==_1&&(!this.editByCell||this.getEditCol()==_2)){_4+=" "}else{_4+=isc.Canvas.spacerHTML(this.cellPadding,"auto")}
return _4}
,isc.A.$79z=function isc_ListGrid__handleErrorIconOver(_1,_2){this.$79y=[_1,_2];isc.Hover.setAction(this,this.$79v)}
,isc.A.$79v=function isc_ListGrid__handleErrorIconHover(){if(this.$79y==null)return;var _1=this.$79y[0],_2=this.$79y[1];var _3=this.getCellErrors(_1,_2);if(_3==null)return;var _4=isc.FormItem.getErrorPromptString(_3);isc.Hover.show(_4,this.$wc())}
,isc.A.$790=function isc_ListGrid__handleErrorIconOut(){delete this.$79y;if(isc.Hover.isActive){isc.Hover.clear()}}
,isc.A.$315=function isc_ListGrid__formatCellValue(_1,_2,_3,_4,_5){if(_3&&_3.userSummary){_1=this.getSummaryFieldValue(_3,_2)}else{if(_3&&_3.formatCellValue!=null){_1=_3.formatCellValue(_1,_2,_4,_5,this)}else if(_3&&_3.cellValueTemplate){_1=_3.cellValueTemplate.evalDynamicString(this,{value:_1,record:_2,field:_3})}else if(this.formatCellValue!=null){_1=this.formatCellValue(_1,_2,_4,_5)}else{_1=this.applyCellTypeFormatters(_1,_2,_3,_4,_5)}}
_1=this.formatValueAsString(_1,_2,_3,_4,_5);if(this.formatDisplayValue){_1=this.formatDisplayValue(_1,_2,_4,_5)}
if(_3&&_3.escapeHTML){if(isc.isA.String(_1)&&_1!=this.$54t){_1=_1.asHTML()}}
return _1}
,isc.A.getDefaultFormattedValue=function isc_ListGrid_getDefaultFormattedValue(_1,_2,_3){var _4=this.getField(_3);var _5=this.applyCellTypeFormatters(this.getRawCellValue(_1,_2,_3),_1,_4,_2,_3);return this.formatValueAsString(_5,_1,_4,_2,_3)}
,isc.A.applyCellTypeFormatters=function isc_ListGrid_applyCellTypeFormatters(_1,_2,_3,_4,_5){if(_3&&_3.$63!=null){_1=_3.$62.shortDisplayFormatter(_1,_3,this,_2,_4,_5)}else if(_3&&_3.$31r!=null){_1=_3.$31r(_1,_3,this,_2,_4,_5)}
return _1}
,isc.A.formatValueAsString=function isc_ListGrid_formatValueAsString(_1,_2,_3,_4,_5){if(_1==null||isc.is.emptyString(_1)){if(_3.emptyCellValue!=null){_1=_3.emptyCellValue}else if(_3.type=="summary"){_1=this.invalidSummaryValue}else{_1=this.emptyCellValue}}else if(this.$54y[_1]==true){_1=this.$54t}else if(!isc.isA.String(_1)){if(isc.isA.Date(_1)){if(this.$851(_3)){var _6=this.$30o(_3);var _7=isc.SimpleType.inheritsFrom(_3.type,"time");_1=isc.Time.toTime(_1,_6,_7)}else{var _8=_3&&isc.SimpleType.inheritsFrom(_3.type,"datetime"),_9=!_8&&_3&&isc.SimpleType.inheritsFrom(_3.type,"date"),_6=this.$45i(_3);if(_8)_1=_1.toShortDateTime(_6,true);else _1=_1.toShortDate(_6,!_9)}}else{_1=isc.iscToLocaleString(_1)}}
return _1}
,isc.A.getEditItemCellValue=function isc_ListGrid_getEditItemCellValue(_1,_2,_3){var _4=this.getEditorName(_2,_3);if(!this.$286||!this.$286.getItem(_4)){return"&nbsp;"}
var _5=this.getCellErrors(_2,_3);if(_5){this.$286.setFieldErrors(_4,_5)}
var _6=this.$286.getItem(_4),_7=_6.getStandaloneItemHTML(_6.getValue(),false,true);if(_5){this.$286.setFieldErrors(_4,null)}
var _8=_6.containerWidget;if(!_8.$316)_8.$316=[];if(!_8.$316.contains(_6)){_8.$316.add(_6)}
return _7}
,isc.A.$68z=function isc_ListGrid__showInactiveEditor(_1){return this.$686(_1)}
,isc.A.getInactiveEditorCellValue=function isc_ListGrid_getInactiveEditorCellValue(_1,_2,_3){var _4=this.getEditorName(_2,_3);var _5=this.$286.getItem(_4),_6=isc.DynamicForm.getEditorType(_5,this.$286),_7=this.getRawCellValue(_1,_2,_3),_8;if(this.shouldShowEditorPlaceholder(this.getField(_3),_6)){_8=this.getEditorPlaceholderHTML(_6,_7,_1,_2,_3)}else{var _9={grid:this.getID(),record:_1,rowNum:_2,colNum:_3}
_8=_5.getInactiveEditorHTML(_7,false,true,_9);if(!this.$69m)this.$69m={};var _10="_"+_2+"_"+_3;if(this.$69m[_10]){this.logWarn("creating new inactiveEditor context for a cell without having cleared "+"the previous one."+this.getStackTrace(),"inactiveEditorHTML")}
this.$69m[_10]=_9}
return _8}
,isc.A.shouldShowEditorPlaceholder=function isc_ListGrid_shouldShowEditorPlaceholder(_1,_2){if(_1.showEditorPlaceholder!=null)return _1.showEditorPlaceholder;return!this.$687[_2]}
,isc.A.getEditorPlaceholderHTML=function isc_ListGrid_getEditorPlaceholderHTML(_1,_2,_3,_4,_5){return _2}
,isc.A.$30b=function isc_ListGrid__editItemsDrawingNotification(_1,_2,_3){var _4;if(_1)_4=[_1];else{_4=[];var _5=this.$286.getItems();for(var i=0;i<_5.length;i++){if(_5[i].containerWidget==_3)_4.add(_5[i])}}
var _7=_3.$316;for(var i=0;i<_4.length;i++){var _8=_4[i],_9=_8.isDrawn(),_10=_7?_7.contains(_8):false,_11=isc.CanvasItem&&isc.isA.CanvasItem(_8);if(_9){if(_10){_8.redrawn();if(_2)_8.moved()}else{_8.cleared()}}else if(_10){if(_11)_8.placeCanvas();_8.drawn()}}
if(this.$286){this.$286.destroyOrphanedItems("Grid edit-items removed")}
delete _3.$316}
,isc.A.$69l=function isc_ListGrid__clearingInactiveEditorHTML(_1,_2){if(this.$69m==null)return;if(!this.$286||!this.$686((_2!=null?this.getField(_2):null),true))return;if(_1!=null){var _3="_"+_1+"_"+_2,_4=this.$69m[_3];if(_4){_4.formItem.clearInactiveEditorContext(_4);delete this.$69m[_3]}}else{var _5=this.getEditForm(),_6=_5.getItems();for(var i=0;i<_6.length;i++){_6[i].clearAllInactiveEditorContexts()}
delete this.$69m}}
,isc.A.setRecordValues=function isc_ListGrid_setRecordValues(_1,_2){if(!this.shouldSaveLocally()){this.logWarn("setRecordValues() not supported for grids where saveLocally is false");return}
if(!this.data)return;var _3=this.data.indexOf(_1),_4=this.data.get(_3);var _5=isc.Canvas.$70o(this.dataPath,_2,this);this.combineObjects(_4,_5);this.calculateRecordSummaries([_4]);if(this.useCellRecords){_3=this.findRowNum(_4);var _6=this.findColNum(_4);this.refreshCell(_3,_6)}else{this.refreshRow(_3)}
if(this.valuesManager!=null){this.valuesManager.$71e(_3,null,_4,this)}}
,isc.A.combineObjects=function isc_ListGrid_combineObjects(_1,_2){return isc.combineObjects(_1,_2)}
,isc.A.setRawCellValue=function isc_ListGrid_setRawCellValue(_1,_2,_3,_4){var _5=this.fields[_3];if(!_1||!_5)return;if(_5.setRawCellValue){isc.Func.replaceWithMethod(_5,"setRawCellValue","viewer,record,recordNum,field,fieldNum,value");_5.setRawCellValue(this,_1,_2,_5,_3,_4)}else{if(_5.dataPath){isc.Canvas.$70n(this.$840(_5.dataPath),_4,_1,this,true)}else{_1[_5[this.fieldIdProperty]]=_4}}
this.data.dataChanged()}
,isc.A.getCellBooleanProperty=function isc_ListGrid_getCellBooleanProperty(_1,_2,_3,_4){var _5=false,_6=this[_1];if(_6==false||_6==this.$18r)return false;var _7=this.fields[_3][_1];if(_7==false||_7==this.$18r)return false;if(_4!=null){var _8=this.getRecord(_2,_3),_9=(_8!=null?_8[_4]:false);if(_9==false||_9==this.$18r)return false;if(_9==true||_9==this.$18q)return true}
return(_6==true)||(_7==true)||(_6==this.$18q)||(_7==this.$18q)}
,isc.A.setShowRecordComponents=function isc_ListGrid_setShowRecordComponents(_1){if(this.showRecordComponents==_1)return;if(_1){if(this.animateFolders){this.$805=true
this.animateFolders=false}}else{if(this.$805){this.animateFolders=true;delete this.$805}}
this.showRecordComponents=_1;this.$80i();if(_1){this.$74p=this.drawAllMaxCells;this.drawAllMaxCells=0;if(this.body!=null)this.body.drawAllMaxCells=0}else{if(this.$74p!=null){this.drawAllMaxCells=this.$74p;if(this.body!=null)this.body.drawAllMaxCells=this.$74p;delete this.$74p}}
this.invalidateRecordComponents()}
,isc.A.$80i=function isc_ListGrid__updateVirtualScrollingForRecordComponents(){if(!this.showRecordComponents){if(this.$80j){delete this.virtualScrolling;delete this.$80j}}else{if(this.virtualScrolling==null||this.$80j){if(this.frozenFields==null){this.virtualScrolling=true;this.$80j=true}else{if(this.recordComponentHeight==null){this.logWarn("This grid has frozen fields and is showing "+"recordComponents. This may lead to unpredictable row heights which "+"are not supported with frozen fields. Setting "+"listGrid.recordComponentHeight will avoid this issue.","recordComponents")}
if(this.$80j){delete this.virtualScrolling;delete this.$80j}}}}
if(this.body&&this.virtualScrolling!=this.body.virtualScrolling){this.body.virtualScrolling=this.virtualScrolling;if(this.frozenBody){this.frozenBody.virtualScrolling=this.virtualScrolling}}}
,isc.A.getDrawArea=function isc_ListGrid_getDrawArea(){if(this.body){var _1=this.body.getDrawArea();if(this.frozenFields&&this.freezeLeft()){_1[2]+=this.frozenFields.length;_1[3]+=this.frozenFields.length}
return _1}
return null}
,isc.A.$74l=function isc_ListGrid__drawAreaChanged(_1,_2,_3,_4,_5){if(this.frozenFields&&this.freezeLeft()){_3+=this.frozenFields.length;_4+=this.frozenFields.length}
var _6=[_1,_2,_3,_4];if(_6.equals(this.getDrawArea()))return;this.drawAreaChanged(_1,_2,_3,_4)}
,isc.A.drawAreaChanged=function isc_ListGrid_drawAreaChanged(){}
,isc.A.updateRecordComponents=function isc_ListGrid_updateRecordComponents(){var _1=this.logIsDebugEnabled("recordComponents");if(this.$81r){if(_1){this.logDebug("updateRecordComponents called recursively - returning","recordComponents")}
return}
var _2=this.body,_3=this.frozenBody;if(_2==null)return;if(_2.$28c!=null){return}
if((!_2.isDrawn()||_2.$26a==null||(_2.$26a.length==0&&_2.fields.length>0))||(_3&&(!_3.isDrawn()||_3.$26a==null||(_3.$26a.length==0&&_3.fields.length>0))))
{return}
this.$81r=true;var _4=this.$81s||[],_5=this.$81t||{};if(_1){this.logDebug("updateRecordComponents - old record components before refreshing:"+this.echo(_4),"recordComponents")}
this.$81t={};this.$81s=[];if(this.showRecordComponents||this.showBackgroundComponents){var _6=this.body.getDrawArea(),_7=this.showRecordComponentsByCell,_8=this.body.getID(),_9=this.frozenBody?this.frozenBody.getID():null;if(_1){this.logDebug("updating to potentially show recordComponents for drawArea:"+_6,"recordComponents")}
for(var _10=_6[0];_10<=_6[1];_10++){var _11=this.getRecord(_10);if(_11==null||Array.isLoading(_11))continue;if(this.showRecordComponents){if(!_7){var _12=this.shouldShowRecordComponent(_11),_13=null;if(_12){_13=this.$81u(_11,null,_8);if(_13!=null){if(_13.isNullMarker){_13=null}else{var _14=_13.getID();_5[_14]=null}}else{_13=this.$81v(_11,null,this.body,_10)}}
if(_13!=null){var _14=_13.getID();this.$81t[_14]=true;this.$81s[this.$81s.length]=_13}}else{if(this.frozenBody!=null){for(var _15=0;_15<this.frozenBody.fields.length;_15++){var _16=this.frozenBody.fields[_15],_17=_16.name;var _12=this.shouldShowRecordComponent(_11,_16.masterIndex),_13=null;if(_12){_13=this.$81u(_11,_17,_9);if(_13!=null){if(!_13.isNullMarker){var _14=_13.getID();_5[_14]=null}else{_13=null}}else{_13=this.$81v(_11,_17,this.frozenBody,_10,_15)}}
if(_13!=null){var _14=_13.getID();this.$81t[_14]=true;this.$81s[this.$81s.length]=_13}}}
for(var _18=_6[2];_18<=_6[3];_18++){var _16=this.body.fields[_18],_17=_16.name;var _12=this.shouldShowRecordComponent(_11,_16.masterIndex),_13=null;if(_12){var _13=this.$81u(_11,_17,_8);if(_13!=null){if(!_13.isNullMarker){var _14=_13.getID();_5[_14]=null}else{_13=null}}else{_13=this.$81v(_11,_17,this.body,_10,_18)}}
if(_13!=null){var _14=_13.getID();this.$81t[_14]=true;this.$81s[this.$81s.length]=_13}}}}
if(this.showBackgroundComponents){if(_11&&_11.backgroundComponent){var _19=_11.$29a?_11.$29a.find("isBackgroundComponent",true):null;if(!_19){if(isc.isA.Canvas(_11.backgroundComponent)){var _20=_11.backgroundComponent.addProperties(this.backgroundComponentProperties,{isBackgroundComponent:true})}else{var _21=isc.addProperties({isBackgroundComponent:true},this.backgroundComponentProperties,_11.backgroundComponent);var _20=this.createAutoChild("backgroundComponent",_21)}
var _22=_2.getTableZIndex();_20.setZIndex(_22-49);_20.setWidth("100%");_20.setHeight("100%");_20.setOverflow("hidden");this.addEmbeddedComponent(_11.backgroundComponent,_11,_10,null,"within")}}}}}
if(this.logIsInfoEnabled("recordComponents")){this.logInfo("updateRecordComponents - new recordComponents:"+this.echo(this.$81t)+", old record components (will be cleaned up if value is 'true'):"+this.echo(_5),"recordComponents")}
for(var i=0;i<_4.length;i++){var _14=_4[i].getID();if(_5[_14]!=true){continue}
if(_1){this.logDebug("cleaning up RecordComponent:"+_4[i],"recordComponents")}
this.$81w(_4[i]);_5[_14]=null}
delete this.$81r}
,isc.A.$81v=function isc_ListGrid__applyNewRecordComponent(_1,_2,_3,_4,_5){if(this.logIsDebugEnabled("recordComponents")){this.logDebug("getting record component for row/field:"+[_4,_2],"recordComponents")}
var _6=_3.getID();var _7=this.recordComponentPoolingMode=="recycle",_8,_9,_10=_2==null?null:this.getColNum(_2);if(_7){var _11=this.getFromRecordComponentPool(_1,_2);_8=_11?_11[0]:null;_9=_11?_11[1]:null}
if(!_8){if(this.createRecordComponent&&isc.isA.Function(this.createRecordComponent)){_8=this.createRecordComponent(_1,this.getColNum(_2));if(_8!=null)_8.isRecordComponent=true;this.logDebug("created new record component:"+_8,"recordComponents")}}else{if(this.updateRecordComponent&&isc.isA.Function(this.updateRecordComponent)){var _12=_8;_8=this.updateRecordComponent(_1,_10,_8,!_9);if(_8==null){if(this.logIsInfoEnabled("recordComponents")){this.logInfo("showRecordComponents: updateRecordComponent() method "+"failed to return an updated component.","recordComponents")}
this.addToRecordComponentPool(_12)}
this.logDebug("updated record component from pool:"+_8,"recordComponents")}}
var _13=_8==null;if(_13){_8={isNullMarker:true,$669:_6,$81x:this.$81o}}
if(_1.$81y==null){_1.$81y={}}
if(_2==null)_2=this.$81m;_1.$81y[_2]=_8;if(_7&&!_13){_8.currentFieldName=_2;_8.currentRecord=_1}
if(!_13){return _3.addEmbeddedComponent(_8,_1,_4,_5,this.getRecordComponentPosition())}}
,isc.A.setDontAutoDestroyComponent=function isc_ListGrid_setDontAutoDestroyComponent(_1,_2){_1.dontAutoDestroy=_2}
,isc.A.$81w=function isc_ListGrid__cleanUpRecordComponent(_1,_2){if(this.logIsDebugEnabled("recordComponents")){this.logDebug("cleaning up recordComponent:"+_1,"recordComponents")}
var _3=this.recordComponentPoolingMode;if(_2)_3="viewport";if(_3=="data"){if(!_1.destroyed&&!_1.destroying&&!_1.$65i){if(this.$89r==null){this.$89r={}}
var _4=_1.ID;if(this.$89r[_4]!=_1){this.$89r[_1.ID]=_1;if(_1.pointersToThis==null)_1.pointersToThis=[];_1.pointersToThis.add({object:this.$89r,property:_1.ID})}}}else{var _4=_1.ID,_5=isc.Canvas.getById(_1.$669),_6=_1.embeddedRecord,_7=_1.$81k;if(_6.$81y){if(_6.$81y[_7]==_1){delete _6.$81y[_7]}
delete _6.$81y[_7]}
if(_5!=null){_5.removeEmbeddedComponent(_1.embeddedRecord,_1)}
if(_3=="viewport"){if(!_1.dontAutoDestroy)_1.markForDestroy()}else{if(_1.destroying||_1.destroyed||_1.$65i)return;this.addToRecordComponentPool(_1)}}}
,isc.A.getLiveRecordComponent=function isc_ListGrid_getLiveRecordComponent(_1,_2,_3){if(!_1)return null;if(isc.isA.Number(_1))_1=this.getRecord(_1);if(!_3)_3=this.body.getID();var _4=this.$81u(_1,_2,_3);return _4}
,isc.A.$81u=function isc_ListGrid__getLiveRecordComponent(_1,_2,_3){if(_2==null)_2=this.$81m;var _4=_1.$81y;if(_4==null||_4[_2]==null)return null;var _5=_4[_2];if(_5.$669!=_3){return null}
if(_5.isNullMarker&&_5.$81x!=this.$81z){return null}
if(_5.destroyed||_5.destroying||_5.$65i){this.logWarn("Destroyed or Destroying record component:"+_5+" present on record. Ignoring","recordComponents");_4[_2]=null;return null}
return _5}
,isc.A.invalidateRecordComponents=function isc_ListGrid_invalidateRecordComponents(){this.dropRecordComponents(true);if(this.showRecordComponents&&this.isDrawn()){this.updateRecordComponents()}}
,isc.A.dropRecordComponents=function isc_ListGrid_dropRecordComponents(_1){this.$81o++;var _2=this.$81s||[];delete this.$81s;delete this.$81t;for(var i=0;i<_2.length;i++){this.$81w(_2[i],_1)}}
,isc.A.refreshRecordComponent=function isc_ListGrid_refreshRecordComponent(_1,_2){if(!this.showRecordComponents||_1==null||this.body==null)return;if(this.showRecordComponentsByCell&&_2==null){this.logWarn("refreshRecordComponent() called with no colNum. This parameter is required when "+"showRecordComponentsByCell is true. Taking no action.");return}
var _3=this.getRecord(_1);if(_3==null||Array.isLoading(_3))return;var _4=this.body,_5=null,_6=null;if(this.showRecordComponentsByCell){_4=this.getFieldBody(_2);_5=this.getLocalFieldNum(_2);_6=this.getFieldName(_2)}
var _7=this.$81u(_3,_6,_4.getID());if(_7!=null&&_7.isNullMarker)_7=null;if(_7!=null){this.$81w(_7,(this.recordComponentPoolingMode!="recycle"))}
var _8;if(this.shouldShowRecordComponent(_3,_2)){_8=this.$81v(_3,_6,_4,_1,_5);if(_8&&_8.isNullMarker)_8=null}
var _9=this.$81s.length;if(_7){var _10=_7.getID();this.$81t[_7.getID()]=null;if(_8!=null){_9=this.$81s.indexOf(_7);this.$81s[_9]=null}else{this.$81s.remove(_7)}}
if(_8!=null){var _10=_8.getID();this.$81t[_10]=true;this.$81s[_9]=_8}}
,isc.A.getRecordComponentPosition=function isc_ListGrid_getRecordComponentPosition(){if(this.recordComponentPosition!=null)return this.recordComponentPosition;return(this.showRecordComponentsByCell?"within":"expand")}
,isc.A.getRecordComponentPool=function isc_ListGrid_getRecordComponentPool(){if(!this.$74m)this.$74m=[];return this.$74m}
,isc.A.getFromRecordComponentPool=function isc_ListGrid_getFromRecordComponentPool(_1,_2){var _3=this.getRecordComponentPool(),_4=[],_5;if(!_3||_3.length==0)return null;if(this.showRecordComponentsByCell&&this.poolComponentsPerColumn==true){_4=_3.findAll("currentFieldName",_2)}else{_4=_3}
if(!_4||_4.length==0)return null;for(var i=0;i<_4.length;i++){_5=_4[i];var _7=_5.currentRecord;if(this.comparePrimaryKeys(_7,_1)){_3.remove(_5);return[_5,true]}}
_3.length-=1;return[_5,false]}
,isc.A.addToRecordComponentPool=function isc_ListGrid_addToRecordComponentPool(_1){var _2=this.getRecordComponentPool();_2.add(_1)}
,isc.A.shouldShowRecordComponent=function isc_ListGrid_shouldShowRecordComponent(_1,_2){if(_1==null||_1.$52e||_1[this.isSeparatorProperty]||Array.isLoading(_1))
{return false}
return this.showRecordComponent(_1,_2)}
,isc.A.showRecordComponent=function isc_ListGrid_showRecordComponent(){return true}
,isc.A.bodyDrawing=function isc_ListGrid_bodyDrawing(_1){if(isc.$cv)arguments.$cw=this;if(_1!=this.body)return;var _2;if(this.$31v){_2=!isc.RPCManager.startQueue();this.$67b()}
this.requestVisibleRows();if(_2)isc.RPCManager.sendQueue();this.$31v=null}
,isc.A.setRecordComponentHeight=function isc_ListGrid_setRecordComponentHeight(_1){this.recordComponentHeight=_1;if(this.isDrawn())this.markForRedraw()}
,isc.A.getAvgRowHeight=function isc_ListGrid_getAvgRowHeight(_1){if(this.showRecordComponents&&this.recordComponentHeight!=null){return this.getRecordComponentRowHeight()}
return _1.fixedRowHeights?_1.cellHeight:Math.max(_1.cellHeight,_1.avgRowHeight)}
,isc.A.getRecordComponentRowHeight=function isc_ListGrid_getRecordComponentRowHeight(){if(this.recordComponentHeight==null)return null;var _1=this.getRecordComponentPosition();if(_1==this.$57m)return this.cellHeight+this.recordComponentHeight;else return Math.max(this.recordComponentHeight,this.cellHeight)}
,isc.A.$45n=function isc_ListGrid__setOptionDataSources(){this.$31v=null;var _1=this.$45o;this.$45o=[];var _2=this.getDataSource();for(var i=0;i<this.completeFields.length;i++){var _4=this.completeFields[i];if(_4==null){this.logWarn("Fields array contains an empty entry");continue}
var _5=_4.displayField||_4[this.fieldIdProperty],_6=_4.valueField||_4[this.fieldIdProperty];if(_4.optionDataSource==null||_4.displayField==null||(_4.displayField==_4.valueField)||(_4.valueField==null&&(_4.displayField==_4[this.fieldIdProperty]))||(_4.autoFetchDisplayMap==false)||(this.autoFetchDisplayMap==false&&_4.autoFetchDisplayMap==null))
{continue}
var _7=isc.DS.get(_4.optionDataSource);if(_7==null){this.logWarn(_4.optionDataSource+" dataSource not found, check value of "+"optionDataSource property on the "+_4[this.fieldIdProperty]+" field");continue}
var _8=_7.ID,_9=_4[this.fieldIdProperty],_10=_4.optionCriteria;var _11=false;if(_1&&!_4.optionFilterContext){for(var _12=0;_12<_1.length;_12++){var _13=_1[_12];if(_13==null)continue;if(this.$68m(_4,_13)){_13.$51u=[_4];this.$45o.add(_13);_1[_12]=null;_11=true;break}}}
if(!_11&&!_4.optionFilterContext){for(var _12=0;_12<this.$45o.length;_12++){var _13=this.$45o[_12];if(this.$68m(_4,_13)){_13.$51u.add(_4);_11=true;break}}}
if(!_11){this.$45o.add({$68n:_8,$51u:[_4],$51w:_4.optionTextMatchStyle,$68o:_10,$68p:_4.optionFilterContext,$68r:_4.optionOperationId});this.$31v=true}}
for(var i=0;i<this.$45o.length;i++){if(this.$45o[i].$68q!=null){this.$67c(this.$45o[i])}}
if(_1!=null){for(var i=0;i<_1.length;i++){if(_1[i]&&_1[i].$68q)_1[i].$68q.destroy()}}}
,isc.A.$68m=function isc_ListGrid__fieldMatchesODSConfig(_1,_2){return(_2.$68n==_1.optionDataSource&&(_1.optionTextMatchStyle==_2.$51w)&&(_1.optionOperationId==_2.$68r)&&(isc.DataSource.getDataSource(_2.$68n).compareCriteria(_1.optionCriteria||{},_2.$68o||{})==0))}
,isc.A.$67c=function isc_ListGrid__updateValueMapFromODS(_1){var _2=_1.$68n,_3=_1.$51u;if(_3==null||_3.length==0){this.logWarn("$67c fired for dataSource:"+_2+" which no longer applies to any fields in this ListGrid");return}
var _4=_1.$68q;if(_4==null||(isc.isA.ResultSet(_4)&&!_4.lengthIsKnown()))return;var _5=this.getGroupByFields()||[],_6=_3.getProperty(this.fieldIdProperty);if(!isc.isAn.Array(_5))_5=[_5];for(var i=0;i<_3.length;i++){var _8=_3[i],_9=(_8.valueField||_8[this.fieldIdProperty]),_10=_4.getValueMap(_9,_8.displayField);if(this.getField(_8[this.fieldIdProperty])==null){_8.valueMap=_10}else{this.setValueMap(_8[this.fieldIdProperty],_10)}
if(_5.contains(_8.valueField)||_5.contains(_8.displayField)){this.$92t=true}}
if(this.$92t){var _11=true;for(var i=0;i<this.$45o.length;i++){var _1=this.$45o[i];if(!_1.$68q){_11=false;break}}
if(_11){this.regroup(true);delete this.$92t}}}
,isc.A.$67b=function isc_ListGrid__fetchValueMapData(){var _1;for(var i=0;i<this.$45o.length;i++){var _3=this.$45o[i];if(_3.$68q==null){if(_1==null){_1=!isc.RPCManager.startQueue()}
var _4=_3.$68n;var _5,_6=_3.$51u;if(_6&&_6.length>0){_5="";for(var _7=0;_7<_6.length;_7++){_5+=_6[_7].name;if(_7<_6.length-1)_5+=","}}
var _8=_3.$68p||{};isc.addProperties(_8,{showPrompt:false,clientContext:{ODSConfig:_3},componentContext:_5,textMatchStyle:_3.$51w});if(_3.$68r!=null){_8.operationId=_3.$68r}
isc.DataSource.getDataSource(_4).fetchData(_3.$68o,{target:this,methodName:"$317"},_8)}}
if(_1)isc.RPCManager.sendQueue()}
,isc.A.$317=function isc_ListGrid__fetchValueMapCallback(_1,_2,_3){var _4=_3.clientContext.ODSConfig;if(!_4||(_4.$68q!=null)||!this.$45o||!this.$45o.contains(_4))
{return}
_4.$68q=isc.ResultSet.create({dataSource:_4.$68n,ODSConfig:_4,targetGrid:this,dataChanged:"this.targetGrid.$67c(this.ODSConfig)",initialData:_2})
this.$67c(_4);return true}
,isc.A.$67a=function isc_ListGrid__dropODSData(){if(this.$45o==null)return;for(var i=0;i<this.$45o.length;i++){var _2=this.$45o[i].$68q;if(_2)_2.destroy()}
delete this.$45o}
,isc.A.requestVisibleRows=function isc_ListGrid_requestVisibleRows(){if(isc.ResultSet&&isc.isA.ResultSet(this.data)){if(this.body==null)return this.data.getRange(0,this.dataPageSize);if(this.data.lengthIsKnown()&&this.data.getLength()==0)return;if(!this.data.lengthIsKnown()){this.body.showAllRows=false}else{this.body.showAllRows=(this.body.overflow==isc.Canvas.VISIBLE?true:this.showAllRecords)}
var _1=this.body.getDrawArea();if(this.$30d&&isc.isAn.Array(this.$30d)){var _2=_1[1]-_1[0];_1[0]=this.$30d[0];_1[1]=_1[0]+_2}
if(this.isGrouped){return this.data.getRange(0,this.groupByMaxRecords)}else{return this.data.getRange(_1[0],_1[1])}}
return null}
,isc.A.getPrintHeaders=function isc_ListGrid_getPrintHeaders(_1,_2){var _3=isc.SB.create();var _4=(this.isRTL()?isc.Canvas.LEFT:isc.Canvas.RIGHT);var _5,_6,_7=(this.headerSpans==null?null:[]),_8=[];var _9=["<TD CLASS=",(this.printHeaderStyle||this.headerBaseStyle)," ALIGN="].join("");for(var _10=_1;_10<_2;_10++){var _11=this.body.fields[_10];if(this.headerSpans!=null){if(_5==null){_5=this.headerSpans[0];_6=1}else{if(!_5.fields.contains(_11[this.fieldIdProperty])){_7.addList([_9,"center colspan=",_6,">",_5.title,"</TD>"]);_5=this.headerSpans[this.headerSpans.indexOf(_5)+1];_6=1}else{_6++}}}
var _12=_11.align||_4;_8.addList([_9,_12,">",this.getHeaderButtonTitle(_11.masterIndex),"</TD>"])}
if(_5!=null){_7.addList([_9,"center colspan=",_6,">",_5.title,"</TD>"]);_3.append("<TR>",_7.join(""),"</TR>")}
_3.append("<TR>",_8.join(""),"</TR>");return _3.toString()}
,isc.A.getPrintFooters=function isc_ListGrid_getPrintFooters(_1,_2){if(!(this.summaryRow)||!(this.summaryRow.body)||!(this.showGridSummary)){return""}
var _3={startRow:0,endRow:this.summaryRow.getTotalRows(),maxRows:this.summaryRow.printMaxRows,printProps:this.printProperties||{},html:[]}
var _4=isc.Canvas.applyStretchResizePolicy(this.fields.getProperty("width"),_3.printProps.width||isc.Page.getWidth());_3.printWidths=_4;this.summaryRow.body.printChunkOnly=true;var _5=this.summaryRow.body.getPrintHTMLChunk(_3,true);delete this.summaryRow.body.printChunkOnly;return _5}
,isc.A.getPrintHTML=function isc_ListGrid_getPrintHTML(_1,_2){var _3=this.body;if(_3==null){this.createChildren();_3=this.body}
if(this.isDirty()||_3.isDirty()){this.redraw("updating HTML for printing")}
var _4=isc.addProperties({},_1,this.printProperties);var _5,_6;if(isc.isA.ResultSet(this.data)&&!this.data.allMatchingRowsCached()){var _7=this.body.getVisibleRows(),_8=_7?_7[0]:null,_9=this.data.getCachedRange(_8);if(_9!=null){_5=_9[0];_6=_9[1]}}
var _10=isc.Canvas.applyStretchResizePolicy(this.fields.getProperty("width"),_4.width||isc.Page.getWidth());return _3.getTablePrintHTML({startRow:_5,endRow:_6,callback:_2,printWidths:_10,printProps:_4})}
,isc.A.$569=function isc_ListGrid__prepareForPrinting(_1,_2){this.isPrinting=this.body.isPrinting=true;this.currentPrintProperties=_2;var _3=this.body,_4=this.$30a;var _5=isc.getProperties(_3,["autoFit","wrapCells","showAllRows","showAllColumns","fixedRowHeights","$26a","fields"]);_3.showAllRows=true;_3.showAllColumns=true;this.$30a=false;_3.autoFit=this.printAutoFit;_3.wrapCells=this.printWrapCells;_3.fixedRowHeights=!this.printWrapCells;var _6=this.fields.duplicate();_6.removeAll(_6.findAll("shouldPrint",false));_3.fields=_6;var _7=this.frozenFields;delete this.frozenFields;_3.$26a=_1;return{oldEditorShowing:_4,oldFrozenFields:_7,origProps:_5}}
,isc.A.$57a=function isc_ListGrid__donePrinting(_1){var _2=this.body,_3=_1.origProps,_4=_1.oldEditorShowing;isc.addProperties(_2,_3);if(_3.showAllRows==null)_2.showAllRows=null;this.$30a=_4;this.frozenFields=_1.oldFrozenFields;delete this.currentPrintProperties;this.isPrinting=this.body.isPrinting=false;this.body.redraw()}
,isc.A.rowClick=function isc_ListGrid_rowClick(_1,_2,_3,_4){this.$31q=_2;var _1=this.getCellRecord(_2,_3),_5=this.fields[_3];if(Array.isLoading(_1))return;var _6=this.getCellValue(_1,_2,_3);var _7=this.getRawCellValue(_1,_2,_3);if(_1!=null&&_1.$52e){if(this.canCollapseGroup==false)return;if(_4){var _8=isc.EH.getKey();if(_8!="Space"&&_8!="Enter")return}
var _9=this,_10=_1;if(this.getEditRow()!=null)this.saveAllEdits(null,function(){_9.toggleFolder(_10)});else this.toggleFolder(_1);return}
if(_5.recordClick&&!(_4&&_5.keyboardFiresRecordClick==false)){isc.Func.replaceWithMethod(_5,"recordClick","viewer,record,recordNum,field,fieldNum,value,rawValue");var _11=_5.recordClick(this,_1,_2,_5,_3,_6,_7);if(_11==false)return false}
if(this.recordClick){var _11=this.recordClick(this,_1,_2,_5,_3,_6,_7);if(_11==false)return false}
var _12=this.isEditable()&&(this.editEvent==isc.EH.CLICK||this.editOnFocus);if(_5.canToggle&&this.canEditCell(_2,_3)&&this.shouldToggle(_5)){var _13=this.getEditorValueMap(_5,this.getEditedRecord(_2,_3));if(_13==null&&isc.SimpleType.getBaseType(_5.type)==this.$g2){_13=[true,false]}
if(_13!=null){if(!isc.isAn.Array(_13))_13=isc.getKeys(_13);if(_13.length>1){var _14=this.getFieldName(_3),_15=this.getEditedCell(_2,_3),_16=_13.indexOf(_15);_16+=1;if(_16>=_13.length)_16=0;var _17=_15;_15=_13[_16];var _18;if(_5.change!=null){this.logInfo("canToggle firing specified field.change() event directly","gridEdit");_18=this.fireCallback(_5.change,"form,item,value,oldValue",[null,null,_15,_17])==false}
if(!_18){if(!_12&&this.autoSaveEdits){this.setEditValue(_2,_3,_15,true,false)}else{this.setEditValue(_2,_3,_15)}
if(_5.changed!=null){this.logInfo("canToggle firing specified field.changed() event directly","gridEdit");this.fireCallback(_5.changed,"form,item,value",[null,null,_15])}
if(this.autoSaveEdits)this.saveEdits(null,null,_2,_3)}}}}
if(_12){if(this.handleEditCellEvent(_2,_3,isc.ListGrid.CLICK)==true){return true}
if(_4){for(var i=0;i<this.fields.length;i++){if(i==_3)continue;if(this.handleEditCellEvent(_2,i,isc.ListGrid.CLICK)==true){return true}}}}}
);isc.evalBoundary;isc.B.push(isc.A.shouldToggle=function isc_ListGrid_shouldToggle(_1){if(!this.$31s(_1))return true;var _2=this.getEventPart();return(_2&&_2.part=="valueicon")}
,isc.A.rowDoubleClick=function isc_ListGrid_rowDoubleClick(_1,_2,_3,_4){var _5=this.fields[_3],_6=this.getCellValue(_1,_2,_3),_7=this.getRawCellValue(_1,_2,_3);if(_1!=null&&_1.$52e)return;if(_5.recordDoubleClick){isc.Func.replaceWithMethod(_5,"recordDoubleClick","viewer,record,recordNum,field,fieldNum,value,rawValue");var _8=_5.recordDoubleClick(this,_1,_2,_5,_3,_6,_7);if(_8==false)return _8}
if(this.recordDoubleClick!=null){var _8=this.recordDoubleClick(this,_1,_2,_5,_3,_6,_7);if(_8==false)return _8}
if(this.isEditable()&&this.editEvent==isc.EH.DOUBLE_CLICK){if(this.handleEditCellEvent(_2,_3,isc.ListGrid.DOUBLE_CLICK)==true)return true;if(_4){for(var i=0;i<this.fields.length;i++){if(i==_3)continue;if(this.handleEditCellEvent(_2,i,isc.ListGrid.DOUBLE_CLICK)==true)return true}}}}
,isc.A.$298=function isc_ListGrid__cellContextClick(_1,_2,_3){this.cellContextItems=null;if(this.cellContextClick){var _1=this.getCellRecord(_2,_3);if(this.cellContextClick(_1,_2,_3)==false)return false}
if(this.showCellContextMenus){if(!this.cellContextMenu)this.cellContextMenu=this.getMenuConstructor().create(this.contextMenuProperties);if(!this.cellContextItems){this.cellContextItems=this.makeCellContextItems(this.getCellRecord(_2,_3),_2,_3)}
if(isc.isAn.Array(this.cellContextItems)&&this.cellContextItems.length>0){this.cellContextMenu.setData(this.cellContextItems);this.cellContextMenu.showContextMenu(this)}
return false}else{return true}}
,isc.A.getShowChildDataSourceContextMenuItemTitle=function isc_ListGrid_getShowChildDataSourceContextMenuItemTitle(_1){return"Show "+_1.getPluralTitle()}
,isc.A.makeCellContextItems=function isc_ListGrid_makeCellContextItems(_1,_2,_3){if(this.dataSource!=null){var _4=[];if(this.canOpenRecordDetailGrid){var _5=isc.DS.get(this.getRecordDataSource(_1)),_6=_5.getChildDataSources();if(_6!=null){for(var i=0;i<_6.length;i++){var _8=_6[i];_4.add({title:this.getShowChildDataSourceContextMenuItemTitle(_8),record:_1,dataSource:_8,click:"target.openRecordDetailGrid(item.record, item.dataSource)"})}}}
if(this.canOpenRecordEditor){_4.add({title:this.openRecordEditorContextMenuItemTitle,record:_1,click:"target.endEditing();target.openRecordEditor(item.record)"})}
if(_1!=null&&this.$32o==_1){_4.add({title:this.dismissEmbeddedComponentContextMenuItemTitle,click:"target.closeRecord()"})}
_4.add({title:this.deleteRecordContextMenuItemTitle,click:"target.removeSelectedData()"});return(_4.length>0?_4:null)}
return null}
,isc.A.getCanHover=function isc_ListGrid_getCanHover(){if(this.canHover!=null)return this.canHover;var _1=this.getFields();if(_1!=null){for(var i=0;i<_1.length;i++){if(_1[i].showHover)return true}}
return this.canHover}
,isc.A.cellHoverHTML=function isc_ListGrid_cellHoverHTML(_1,_2,_3){if(this.$30a&&this.getEditRow()==_2&&this.canEditCell(_2,_3)&&(!this.editByCell||this.getEditCol()==_3)){return null}
var _4=this.getField(_3);if(_4.showHover==false)return null;if(_4.showHover==null&&!this.canHover)return null;var _5=this.getCellValue(_1,_2,_3);if(_4.hoverHTML){isc.Func.replaceWithMethod(_4,"hoverHTML","record,value,rowNum,colNum,grid");return _4.hoverHTML(_1,_5,_2,_3,this)}
if(_5!=null&&!isc.isAn.emptyString(_5)&&_5!=this.emptyCellValue){return _5}}
,isc.A.$784=function isc_ListGrid__getCellHoverComponent(_1,_2,_3){if(!this.showHoverComponents)return null;if(this.$30a&&this.getEditRow()==_2&&(!this.editByCell||this.getEditCol()==_3))return null;var _4=this.getField(_3);if(_4.showHover==false)return null;if(_4.showHover==null&&!this.canHover)return null;if(this.getCellHoverComponent&&isc.isA.Function(this.getCellHoverComponent)){return this.getCellHoverComponent(_1,_2,_3)}else return null}
,isc.A.getCellHoverComponent=function isc_ListGrid_getCellHoverComponent(_1,_2,_3){return this.$84d(_1,false,true,_2,_3)}
,isc.A.$84d=function isc_ListGrid__getStockEmbeddedComponent(_1,_2,_3,_4,_5){var _6=this.getFields(),_7=this.dataSource?isc.getValues(this.getDataSource().getFields()):_6,_8=(!_3?null:this.hoverWidth||this.hoverSize||this.defaultCellHoverComponentWidth),_9=(!_3?null:this.hoverHeight||this.hoverSize||this.defaultCellHoverComponentHeight),_10=[],_11;for(var i=0;i<_7.length;i++){var _13=_7.get(i);if(this.dataSource){if(!_6.find("name",_13.name)){_10.add(_13)}}else{if(!this.isExpansionField(_13)){_10.add(_13)}}}
var _14=(_3?this.hoverMode:(_2?this.expansionMode:null));var _15;if(_14=="detailField"){_11=this.createAutoChild("expansionDetailField",{contents:_1[this.detailField]});_15={width:(_3?_8:"100%"),height:(_3?_9:"100%"),members:[_11]};if(_3){_15=isc.addProperties(_15,{hoverAutoDestroy:this.hoverAutoDestroy,overflow:"auto"})}
_11=isc.VLayout.create(_15)}else if(_14=="details"){_15={dataSource:this.dataSource,fields:_10};if(_3){_15=isc.addProperties(_15,{width:_8,height:_9,hoverAutoDestroy:this.hoverAutoDestroy})}
_11=this.createAutoChild("expansionDetails",_15);_11.setData(_1)}else if(_14=="related"){_15={dataSource:this.getRelatedDataSource(_1)};if(_3){_15=isc.addProperties(_15,{canEdit:false,width:_8,height:_9,dataProperties:{context:{showPrompt:false}},hoverAutoDestroy:this.hoverAutoDestroy})}
if(_2){_15=isc.addProperties(_15,{canExpandRecords:this.childExpansionMode?true:false,expansionMode:this.childExpansionMode,canEdit:this.expansionCanEdit})}
_11=this.createAutoChild("expansionRelated",_15);if(this.expansionCanEdit)_11.autoSaveEdits=true;_11.delayCall("fetchRelatedData",[_1,this.dataSource])}else if(_14=="detailRelated"){_15={dataSource:this.dataSource,fields:_10};if(_3){_15=isc.addProperties(_15,{dataProperties:{context:{showPrompt:false}},hoverAutoDestroy:this.hoverAutoDestroy})}
var _16=this.createAutoChild("expansionDetails",_15)
_16.setData(_1);_15={dataSource:this.getRelatedDataSource(_1),height:"100%",canEdit:(_3?false:(_2?this.expansionCanEdit:null))};if(_3){_15=isc.addProperties(_15,{dataProperties:{context:{showPrompt:false}},hoverAutoDestroy:this.hoverAutoDestroy})}
if(_2){_15=isc.addProperties(_15,{canExpandRecords:this.childExpansionMode?true:false,expansionMode:this.childExpansionMode})}
var _17=this.createAutoChild("expansionRelated",_15);_15={members:[_16,_17]};if(_3){_15=isc.addProperties(_15,{width:_8,height:_9,hoverAutoDestroy:this.hoverAutoDestroy})}
_11=this.createAutoChild("expansionDetailRelated",_15)}else if(_14=="editor"){_11=this.createAutoChild("expansionEditor",{dataSource:this.dataSource,fields:_10});_11.editRecord(this.getEditedRecord(_4)||_1)}
return _11}
,isc.A.selectAllRecords=function isc_ListGrid_selectAllRecords(){this.$67j=true;this.selection.selectAll();this.$67j=null;this.$25a("select all");if(this.getCurrentCheckboxField()!=null)this.$63m(true);this.fireSelectionUpdated()}
,isc.A.deselectAllRecords=function isc_ListGrid_deselectAllRecords(){this.$67j=true;this.selection.deselectAll();this.$67j=null;this.$25a("deselect all");if(this.getCurrentCheckboxField()!=null)this.$63m(false);this.fireSelectionUpdated()}
,isc.A.bodyKeyPress=function isc_ListGrid_bodyKeyPress(_1,_2){if(this.onBodyKeyPress(_1,_2)==false)return false;if(this.$30a){var _3=_1.keyTarget,_4;while(_4==null&&_3!=this&&_3!=null){_4=_3.canvasItem;_3=_3.parentElement}
if(_4!=null&&_4.form==this.getEditForm()){var _5=this.editorKeyPress(_4,isc.EH.getKey(),isc.EH.getKeyEventCharacterValue());return(_5==null?isc.EH.STOP_BUBBLING:_5)}}
if(this.data.getLength()>0){var _6=isc.EventHandler,_7=_1.keyName;var _8=this.editOnKeyPress&&this.isEditable();if(_8&&this.$51j(_1,_2))return false;if(_7==this.$20s){return this.$318(-1)}else if(_7==this.$20t){return this.$318(1)}else if(_7==this.$304){if(this.generateClickOnSpace)
if(this.$240()==false)return false;if(this.generateDoubleClickOnSpace)
return this.$241()}else if(_7==this.$10j){if(this.generateClickOnEnter)
if(this.$240()==false)return false;if(this.generateDoubleClickOnEnter)
return this.$241()}else if(_7==this.$51g&&this.editOnF2Keypress&&this.isEditable()&&this.editEvent!="none")
{var _9=this.getFocusRow();if(_9==null)_9=0;this.startEditing(_9);return false}}
return true}
,isc.A.$51j=function isc_ListGrid__editOnKeyPress(_1,_2){var _3=_2.keyName,_4=isc.EH.getKeyEventCharacter(_1);if(_3!=this.$51g&&_3!=this.$51i&&_3!=this.$51h&&(_3==this.$20w||isc.EH.$i8[_3]||_4==null||_4==isc.emptyString))
{return false}
var _5=this.getFocusCell(),_6=_5[0]||0,_7=_5[1]||0;if(this.$30a)return false;var _8;if(_3==this.$10j||_3==this.$51g)_4=null;var _9;if(_4!=null){if(_3==this.$51i||_3==this.$51h){_9=null}else if(this.autoSelectEditors){_9=_4}else{_9=this.getEditedCell(_6,_7)+_4}
this.$51m=true}
return this.handleEditCellEvent(_5[0],_5[1],this.$51l,_9)}
,isc.A.getArrowKeyAction=function isc_ListGrid_getArrowKeyAction(){var _1=this.arrowKeyAction;if(_1==this.$27r)return this.$27r;if(isc.EH.ctrlKeyDown())return this.$305;return _1}
,isc.A.$318=function isc_ListGrid__navigateToNextRecord(_1){var _2=this.getArrowKeyAction();if(_2==this.$27r)return true;if(_1==null)_1=1;var _3;_3=this.getFocusRow(_1>0);if(_3==null)_3=this.$31q;var _4=isc.isA.Number(_3)?_3:0;if(isc.isA.Number(_3))_3+=_1;else _3=0;var _5=this.getTotalRows()-1;if(_3<0||_3>_5){if(_5<0)return true;_3=_4}
while(!this.recordIsEnabled(_3,0)){_3+=_1;if(_3<0||_3>_5){_3=_4;break}}
if(isc.screenReader){this.body.$86a(_3)}
if(_2==this.$305)this.$88(_3);else{if(_3==_4){var _6=this.$32b();if(this.body.selectionEnabled()&&this.recordIsEnabled(_3,_6))
{this.selection.selectOnMouseDown(this,_3,_6);this.selection.selectOnMouseUp(this,_3,_6)}}
else if(_2==this.$12b)this.$319(_3);else if(_2==this.$306)this.$32a(_3)}
this.scrollRecordIntoView(_3)
return false}
,isc.A.$32b=function isc_ListGrid__getKeyboardClickNum(){var _1=this.keyboardClickField;if(_1==null)return 0;if(isc.isA.Number(_1)&&_1>0&&_1<this.fields.length)return _1;var _2=this.fields.find(this.fieldIdProperty,_1),_3=(_2?this.fields.indexOf(_2):0);return _3}
,isc.A.$319=function isc_ListGrid__generateRecordClick(_1){this.clearLastHilite();if(isc.isAn.Object(_1))_1=this.getRecordIndex(_1);if(!isc.isA.Number(_1)||_1<0)_1=0;if(_1>=this.data.getLength())_1=this.data.getLength()-1;this.body.$31p=_1;var _2=this.$32b();var _3=(this.body.selectionEnabled()&&this.recordIsEnabled(_1,_2));if(_3)this.body.selectOnMouseDown(this,_1,_2);this.rowClick(this.getCellRecord(_1,_2),_1,_2,true);if(_3)this.body.selectOnMouseUp(this,_1,_2);return false}
,isc.A.getFocusRow=function isc_ListGrid_getFocusRow(_1){if(this.body.$31p!=null&&((this.body.$31p==this.body.lastOverRow)||(this.body.$31p==this.$31q)))
{return this.body.$31p}
delete this.body.$31p;var _2=this.getSelection();if(_2.length==0)return null;_2=_2[(_1?_2.length-1:0)]
return this.getRecordIndex(_2)}
,isc.A.getFocusCell=function isc_ListGrid_getFocusCell(){return[this.getFocusRow(),this.$32b()]}
,isc.A.$240=function isc_ListGrid__generateFocusRecordClick(){var _1=this.getFocusRow();if(_1!=null){this.$319(_1);return false}
return true}
,isc.A.$32a=function isc_ListGrid__generateRecordDoubleClick(_1){var _2=this.$32b();this.rowDoubleClick(this.getCellRecord(_1,_2),_1,_2,true)}
,isc.A.$241=function isc_ListGrid__generateFocusRecordDoubleClick(){var _1=this.$32b(),_2=this.getFocusRow();if(_2!=null){this.$32a(_2);return false}
return true}
,isc.A.scrollRecordToTop=function isc_ListGrid_scrollRecordToTop(_1){return this.scrollRecordIntoView(_1,false)}
,isc.A.scrollRecordIntoView=function isc_ListGrid_scrollRecordIntoView(_1,_2){return this.scrollCellIntoView(_1,null,_2)}
,isc.A.scrollColumnIntoView=function isc_ListGrid_scrollColumnIntoView(_1,_2){return this.scrollCellIntoView(null,_1,_2)}
,isc.A.scrollToRow=function isc_ListGrid_scrollToRow(_1){this.scrollCellIntoView(_1,0);return this}
,isc.A.scrollCellCallback=function isc_ListGrid_scrollCellCallback(_1,_2,_3,_4,_5){if(_5==this.$77o){this.$77o=null;this.scrollCellIntoView(_1,_2,_3,_4)}}
,isc.A.scrollCellIntoView=function isc_ListGrid_scrollCellIntoView(_1,_2,_3,_4){if((isc.isAn.Array(this.data)&&this.data.length==0&&this.dataSource)||(isc.ResultSet&&isc.isA.ResultSet(this.data)&&!this.data.lengthIsKnown())){if(!this.$77o)this.$77o=1;else this.$77o+=1;var _5=this.$77o;isc.Page.waitFor(this,"dataArrived",{method:this.scrollCellCallback,args:[_1,_2,_3,_4,_5],target:this});return}
if(!this.body||!this.body.isDrawn()){this.logInfo("scrollCellIntoView() called before the body has been drawn.  Cell "+_1+","+_2+" will scrolled into view on draw().");this.$30d=[_1,_2];return}
if(_3==null)_3=true;var x,y,_8,_9,_10=this.body;if(_1!=null){if(!_10.$60s){y=_10.getRowTop(_1);_9=_10.getRowSize(_1)}else{var _11=_10.$252==null||_1<_10.$252||_1>_10.$253,_12=!_11;if(_12){var _13=_10.getRowTop(_1),_14=_10.getRowHeight?_10.getRowHeight(this.getCellRecord(_1),_1):_10.cellHeight,_15=_10.getScrollTop();if(_15>_13||((_10.getViewportHeight()+_15)<(_13+_14)))
{_12=false}}
if(!_12){_10.$27y=_1;if(_3){_10.$27z=-1*((_10.getViewportHeight()/2)-_10.cellHeight)}else{_10.$27z=0}
_10.$270()}
return}}
if(_2!=null){if(this.frozenFields!=null&&_2<this.frozenFields.length){_2=null}else{x=this.getColumnLeft(_2);_8=this.getColumnWidth(_2)}}
if(this.isDirty()||this.body.isDirty()){var _16;if(_1!=null){var _17=_10.getScrollHeight();if(y+_9>_17)_16=true}
if(!_16&&_2!=null){var _18=_10.getScrollWidth();if(x+_8>_18)_16=true}
if(_16)this.redraw("scrollIntoView")}
_10.scrollIntoView(x,y,_8,_9,(_3?"center":"left"),(_3?"center":"top"),null,null,_4)}
,isc.A.bodyScrolled=function isc_ListGrid_bodyScrolled(_1,_2,_3){if(_3){this.body.$80h=true;var _4=this.frozenBody;if(_4.$274){this.body.$27y=_4.$27y;this.body.$27z=_4.$27z;this.body.$271=_4.$271;this.body.$270()}else{this.body.scrollTo(null,_2,"scrollSync")}
delete this.body.$80h
return}
if(this.frozenBody!=null){this.frozenBody.$80h=true;var _5=this.body,_4=this.frozenBody;if(_5.$274){_4.$27y=_5.$27y;_4.$27z=_5.$27z;_4.$271=_5.$271;_4.$270()}else{_4.scrollTo(null,_2,"bodyScrollSync")}
delete this.frozenBody.$80h}
this.syncHeaderScrolling(_1,_2);this.syncFilterEditorScrolling(_1,_2);this.syncSummaryRowScrolling(_1,_2);if(this.$30a&&this.$519){var _6=this.getEditForm(),_7=this.getEditRow(),_8=this.getEditCol(),_9=_6.getItem(this.getEditorName(_7,_8));if(_9){if(!_9.hasFocus&&(_6.hasFocus||isc.EH.getFocusCanvas()==null))
{this.$518(_8)}else{delete this.$519}}}}
,isc.A.syncHeaderScrolling=function isc_ListGrid_syncHeaderScrolling(_1,_2){if(_1!=null&&this.header){if(!this.isRTL()){if(_1!=this.header.getScrollLeft())this.header.scrollTo(_1,null,"headerScrollSync")}else{var _3=this.header,_4=this.body,_5=_3.getScrollWidth()-_3.getViewportWidth(),_6=_5-_3.getScrollLeft(),_7=_4.getScrollWidth()-_4.getViewportWidth(),_8=_7-_1;if(_8!=_6){_3.scrollTo(_5-_8,null,"scrollSync")}}}}
,isc.A.headerScrolled=function isc_ListGrid_headerScrolled(){if(!this.$32c){this.$32c=this.delayCall("syncBodyScrolling")}}
,isc.A.syncBodyScrolling=function isc_ListGrid_syncBodyScrolling(){delete this.$32c;var _1=this.header.getScrollLeft();if(this.body){if(!this.isRTL()){if(_1!=this.body.getScrollLeft())this.body.scrollTo(_1,null,"scrollSync")}else{var _2=this.header,_3=this.body,_4=_2.getScrollWidth()-_2.getViewportWidth(),_5=_4-_2.getScrollLeft(),_6=_3.getScrollWidth()-_3.getViewportWidth(),_7=_6-_1;if(_7!=_5){_3.scrollTo(_6-_5,null,"scrollSync")}}}}
,isc.A.syncFilterEditorScrolling=function isc_ListGrid_syncFilterEditorScrolling(_1,_2){if(this.filterEditor!=null&&this.filterEditor.body!=null&&this.filterEditor.body.getScrollLeft()!=_1)
{this.filterEditor.body.scrollTo(_1,null,"scrollSync")}}
,isc.A.syncSummaryRowScrolling=function isc_ListGrid_syncSummaryRowScrolling(_1,_2){if(this.summaryRow!=null&&this.showGridSummary&&this.summaryRow.body!=null&&this.summaryRow.body.getScrollLeft()!=_1)
{this.summaryRow.body.scrollTo(_1,null,"scrollSync")}}
,isc.A.$88=function isc_ListGrid__hiliteRecord(_1){if(!isc.isA.Number(_1)){_1=this.getRecordIndex(_1)}
if(!isc.isA.Number(_1)||_1<0)_1=0;if(_1>=this.data.getLength())_1=this.data.getLength()-1;this.clearLastHilite();this.body.$31p=_1;this.body.lastOverRow=_1;this.body.lastOverCol=0;this.bodies.map("setRowStyle",_1)}
,isc.A.clearLastHilite=function isc_ListGrid_clearLastHilite(){if(!this.body)return;this.body.$31p=null;var _1=this.body.lastOverRow;if(isc.isA.Number(_1)){delete this.body.lastOverRow;if(this.showRollOver)this.body.updateRollOver(_1)}}
,isc.A.setAccessKey=function isc_ListGrid_setAccessKey(_1){this.Super("setAccessKey",arguments)
if(this.body!=null)this.body.setAccessKey(_1)}
,isc.A.setFocus=function isc_ListGrid_setFocus(_1){if(this.body!=null)this.body.setFocus(_1)}
,isc.A.focusAtEnd=function isc_ListGrid_focusAtEnd(_1){if(!this.$90d){this.$90d=true;var _2=false;var _3=isc.EH.getFocusCanvas();if(this.contains(_3)||(isc.isA.DynamicForm(_3)&&(_3.grid==this||this.contains(_3.grid))))
{_2=true}
if(_2)this.$kf(_1);this.$90d=false;if(_2)return}
if(_1)this.members[0].focusAtEnd(_1);else this.members.last().focusAtEnd(_1)}
,isc.A.$kk=function isc_ListGrid__canFocus(){if(this.body)return this.body.$kk();return false}
,isc.A.recordClick=function isc_ListGrid_recordClick(){}
,isc.A.recordDoubleClick=function isc_ListGrid_recordDoubleClick(){}
,isc.A.setShowGridSummary=function isc_ListGrid_setShowGridSummary(_1){if(this.showGridSummary==_1)return;this.showGridSummary=_1;if(this.showGridSummary){this.showSummaryRow()}else{this.clearSummaryRow()}}
,isc.A.recalculateSummaries=function isc_ListGrid_recalculateSummaries(_1,_2){_2=(_2!=null?_2:true);this.calculateRecordSummaries(_1,_2,true);this.$855(_2)}
,isc.A.$855=function isc_ListGrid__recalculateSummaries(_1){if(this.showGridSummary&&this.summaryRow!=null&&_1!=false){this.summaryRow.$855()}
if(this.showGroupSummary){this.refreshGroupSummary()}}
,isc.A.recalculateGridSummary=function isc_ListGrid_recalculateGridSummary(){if(this.showGridSummary&&this.summaryRow!=null){this.summaryRow.$855()}}
,isc.A.shouldShowGridSummary=function isc_ListGrid_shouldShowGridSummary(_1){if(_1.showGridSummary!=null)return _1.showGridSummary;return(_1.getGridSummary!=null||this.getGridSummaryFunction(_1)!=null)}
,isc.A.getGridSummaryFunction=function isc_ListGrid_getGridSummaryFunction(_1){if(!_1)return;var _2=_1.summaryFunction||isc.SimpleType.getDefaultSummaryFunction(_1.type);return _2}
,isc.A.getGridSummary=function isc_ListGrid_getGridSummary(_1){if(!_1||!this.data||(isc.isA.ResultSet(this.data)&&!this.data.lengthIsKnown()))
return;var _2=this.getOriginalData(),_3=isc.ResultSet&&isc.isA.ResultSet(_2),_4=isc.isA.Tree(_2);if(_4){_2=_2.getDescendants(_2.getRoot());_3=isc.ResultSet&&isc.isA.ResultSet(_2)}
if(_3&&!_2.allMatchingRowsCached()){this.logWarn("Unable to show summary values - dataset not completely loaded");return}
var _5=_3?_2.getRange(0,_2.getLength()):_2;var _6=this.getAllEditRows();if(_6!=null&&_6.length>0){_5=_5.duplicate();for(var i=0;i<_5.length;i++){var _8=_5[i];var _9=this.getEditSessionRowNum(_8);if(_9!=null)_5[i]=this.getEditedRecord(_9,null,true)}}
if(_1.getGridSummary){var _10;if(this.isGrouped&&this.showGroupSummary&&(this.groupTree!=null)){_10=this.assembleGroupSummaries()}
return _1.getGridSummary(_5,_1,_10)}
return this.getSummaryValue(_5,_1)}
,isc.A.getSummaryRowDataSource=function isc_ListGrid_getSummaryRowDataSource(){return this.summaryRowDataSource}
,isc.A.getSummaryRowCriteria=function isc_ListGrid_getSummaryRowCriteria(){if(this.summaryRowCriteria!=null)return this.summaryRowCriteria;var _1=this.getOriginalData();if(isc.ResultSet&&isc.isA.ResultSet(_1)){return this.data.getCriteria()}
return this.getInitialCriteria()}
,isc.A.getSummaryRowFetchRequestConfig=function isc_ListGrid_getSummaryRowFetchRequestConfig(){return isc.addProperties({textMatchStyle:this.autoFetchTextMatchStyle,showPrompt:false,startRow:0,endRow:1},this.summaryRowFetchRequestDefaults,this.summaryRowFetchRequestProperties)}
,isc.A.getGridSummaryData=function isc_ListGrid_getGridSummaryData(_1){if(this.$84c&&!_1)return this.$84c;var _2=this.completeFields||this.fields,_3=[];for(var i=0;i<_2.length;i++){var _5=_2[i],_6=_2[i].name;if(!this.shouldShowGridSummary(_5))continue;var _7=this.getGridSummary(_5);if(!isc.isAn.Array(_7)){_7=[_7]}
for(var _8=0;_8<_7.length;_8++){if(_3[_8]==null){_3[_8]={};_3[_8][this.gridSummaryRecordProperty]=true;_3[_8][this.recordCanSelectProperty]=false}
_3[_8][_6]=_7[_8]}}
this.$84c=_3;return _3}
,isc.A.getGroupSummaryData=function isc_ListGrid_getGroupSummaryData(_1,_2){var _3=[];for(var i=0;i<this.fields.getLength();i++){var _5=this.getField(i),_6=_5.name,_7;if(this.shouldShowGroupSummary(_5)){_7=this.getGroupSummary(_1,_5,_2);if(!isc.isAn.Array(_7))_7=[_7];for(var _8=0;_8<_7.length;_8++){var _9=_3[_8];if(_9==null){_9=_3[_8]={};_9.customStyle=this.groupSummaryStyle;_9[this.recordEnabledProperty]=false;_9[this.includeInSummaryProperty]=false;_9[this.groupSummaryRecordProperty]=true}
var _10=_7[_8];if(_10==null){_10=this.invalidSummaryValue}else{if(_5.formatGroupSummary){if(isc.isA.String(_5.formatGroupSummary)){_5.formatGroupSummary=isc.Func.expressionToFunction("value",_5.formatGroupSummary)}
_10=_5.formatGroupSummary(_10)}else{var _11=this.getGridSummaryFunction(_5);if(!isc.isAn.Array(_11))_11=[_11];if(_11[_8]=="count"){var _12=_5.pluralTitle;if(_12==null)_12=_5.title;if(_12!=null)_10+=" "+_12}}}
_9[_6]=_10}}}
return _3}
,isc.A.assembleGroupSummaries=function isc_ListGrid_assembleGroupSummaries(_1,_2){var _3=this.groupTree;if(!_1)_1=_3.getRoot();if(!_2)_2=[];var _4=_3.getFolders(_1);for(var i=0;i<_4.length;i++){var _6=_4[i];if(this.groupByFieldSummaries==null||this.groupByFieldSummaries.contains(_6.groupName))
{var _7=_3.combineWithEditVals(_3.getRecordsInGroup(_6));var _8=this.getGroupSummaryData(_7,_6);for(var _9=0;_9<_8.length;_9++){var _10=isc.addProperties({},_8[_9]);delete _10.customStyle;delete _10[this.recordEnabledProperty];delete _10[this.includeInSummaryProperty];_10.groupName=_6.groupName;_10.groupValue=_6.groupValue;_2.add(_10)}}
this.assembleGroupSummaries(_4[i],_2)}
return _2}
,isc.A.getSummaryValue=function isc_ListGrid_getSummaryValue(_1,_2){var _3=[];for(var i=0;i<_1.length;i++){var _5=_1[i];if(!_5||(_5[this.includeInSummaryProperty]==false))continue;_3[_3.length]=_5}
var _6=this.getGridSummaryFunction(_2);if(_6!=null){if(!isc.isAn.Array(_6)){_6=[_6]}
var _7=[];for(var i=0;i<_6.length;i++){var _8=_6[i];if(_8!=null){_7[i]=isc.SimpleType.applySummaryFunction(_3,_2,_8)}}
return _7}
return null}
,isc.A.shouldShowGroupSummary=function isc_ListGrid_shouldShowGroupSummary(_1){if(_1.showGroupSummary!=null)return _1.showGroupSummary;return(_1.getGroupSummary!=null||this.getGridSummaryFunction(_1)!=null)}
,isc.A.getGroupSummary=function isc_ListGrid_getGroupSummary(_1,_2,_3){var _4;if(_2.getGroupSummary!=null){_4=_2.getGroupSummary(_1,_2,_3)}else{_4=this.getSummaryValue(_1,_2)}
return _4}
,isc.A.getRecordSummary=function isc_ListGrid_getRecordSummary(_1,_2){var _3=isc.isAn.Object(_1)?_1:this.getEditedRecord(_1);if(_2.getRecordSummary!=null){return _2.getRecordSummary(_3,_2)}
var _4=[],_5=this.fields;for(var i=0;i<_5.length;i++){var _7=_5[i];if(_7.name==_2.name){if(_2.partialSummary)break;continue}
var _8=_7.includeInRecordSummary;if(_8==null&&(_7.type=="integer"||_7.type=="float")){_8=true}
if(_8&&_7.includeInRecordSummaryFields!=null){if((isc.isA.String(_7.includeInRecordSummaryFields)&&_7.includeInRecordSummaryFields!=_2.name)||(isc.isAn.Array(_7.includeInRecordSummaryFields)&&!_7.includeInRecordSummaryFields.contains(_2.name)))
{_8=false}}
if(_8){_4.add(_7)}}
var _9=_2.recordSummaryFunction||"sum";var _10=isc.DataSource.applyRecordSummaryFunction(_9,_3,_4,_2);return _10}
,isc.A.shouldApplyRecordSummaryToRecord=function isc_ListGrid_shouldApplyRecordSummaryToRecord(_1){return _1&&(_1.type==this.$71t)&&(_1.summaryFunction!=null||_1.getGroupSummary!=null)}
,isc.A.calculateRecordSummaries=function isc_ListGrid_calculateRecordSummaries(_1,_2,_3){if(!this.fields)return;if(_1==null){_1=this.getOriginalData()}
if(_1==null||(isc.isA.ResultSet(_1)&&!_1.lengthIsKnown()))
return;var _4=false;var _5=[];for(var i=0;i<this.fields.length;i++){var _7=this.getField(i);if(this.shouldApplyRecordSummaryToRecord(_7)){_5.add(_7)}}
if(_5.length>0){for(var i=0;i<_1.getLength();i++){var _8=_1.get(i),_9=this.getEditValues(_8),_10=_9?isc.addProperties({},_8,_9):_8;if(_8==null)continue;for(var _11=0;_11<_5.length;_11++){var _7=_5[_11];if(!this.shouldShowRecordSummary(_7,_8))continue;var _12=_8[_7.name];var _13=this.getRecordSummary(_10,_7);_8[_7.name]=_13;if(!this.fieldValuesAreEqual(_7,_12,_13)){_4=true}}}}
if((_3||_4)&&!_2){if(!this.isDirty())this.markForRedraw();if(this.showGridSummary&&this.summaryRow)this.summaryRow.$855()}}
,isc.A.getSummaryRow=function isc_ListGrid_getSummaryRow(){if(!this.summaryRow){var _1=this.getSummaryRowDataSource();var _2;if(this.completeFields)_2=this.completeFields.duplicate();else if(this.fields)_2=this.fields.duplicate();var _3=this.summaryRowHeight;this.summaryRow=this.createAutoChild("summaryRow",{warnOnReusedFields:false,autoDraw:false,width:"100%",height:_3,autoFitData:"vertical",bodyOverflow:"hidden",showHeader:false,getBaseStyle:function(){return this.creator.summaryRowStyle},alternateRecordStyles:false,disabled:this.disabled,$67b:function(){},$31s:function(_10){return false},dataSource:_1,data:_1==null?this.getGridSummaryData():null,$855:function(){var _4=this.creator,_5=_4.data;if(!_4.fields||(isc.isA.ResultSet(_5)&&!_5.lengthIsKnown()))return;if(this.dataSource!=null){var _6=_4.getSummaryRowCriteria(),_7=isc.ResultSet&&isc.isA.ResultSet(this.data)&&!this.data.willFetchData(_6);this.fetchData(_6,null,_4.getSummaryRowFetchRequestConfig());if(_7)this.invalidateCache()}else{this.setData(_4.getGridSummaryData(true))}},fieldSourceGrid:this,fields:_2,skipAutoFitWidths:true,getFieldWidths:function(){return this.creator.getFieldWidths()},$315:function(_10,_11,_12,_13,_14){var _4=this.creator;if(_4.shouldShowGridSummary(_12)){if(_10==null)return this.invalidSummaryValue;if(_12.formatGridSummary){if(!isc.isA.Function(_12.formatGridSummary)){_12.formatGridSummary=isc.Func.expressionToFunction("value",_12.formatGridSummary)}
if(isc.isA.Function(_12.formatGridSummary)){return _12.formatGridSummary(_10)}}else{var _8=this.creator.getGridSummaryFunction(_12);if(!isc.isAn.Array(_8)){_8=[_8]}
if(_8[_13]=="count"){var _9=_12.pluralTitle;if(_9==null)_9=_12.title;if(_9!=null)_10+=" "+_9;return _10}}
return this.Super("$315",arguments)}
return this.Super("$315",arguments)},$92s:true})}else{this.summaryRow.setDataSource(this.getSummaryRowDataSource(),this.completeFields.duplicate());this.summaryRow.$855()}
return this.summaryRow}
,isc.A.showSummaryRow=function isc_ListGrid_showSummaryRow(){var _1=this.gridComponents.indexOf("summaryRow");if(_1==-1){this.logWarn("showGridSummary set to true, but gridComponents array does not include an "+"entry for the summary row - not showing.");return}
this.updateGridComponents()
this.syncSummaryRowScrolling(this.body.getScrollLeft(),this.body.getScrollTop)}
,isc.A.clearSummaryRow=function isc_ListGrid_clearSummaryRow(){if(this.summaryRow&&this.summaryRow.parentElement==this){this.removeMember(this.summaryRow)}}
,isc.A.setShowFilterEditor=function isc_ListGrid_setShowFilterEditor(_1){if(this.showFilterEditor==_1)return;this.showFilterEditor=_1;if(_1){var _2=this.gridComponents.indexOf("filterEditor");if(_2==-1){_1=false;this.logWarn("setShowFilterEditor(true) called, but gridComponents array does not "+"include the filterEditor. FilterEditor will not be shown.")}}
if(_1){if(this.isDrawn()){this.updateGridComponents()}}else if(this.filterEditor){this.filterEditor.destroy();this.filterEditor=null}
this.layoutChildren()}
,isc.A.makeFilterEditor=function isc_ListGrid_makeFilterEditor(){var _1=isc.addProperties({autoDraw:false,warnOnReusedFields:false,$67b:function(){},height:this.filterEditorHeight,disabled:this.disabled,sourceWidget:this,ID:this.getID()+"filterEditor",_generated:true,fieldShouldBeVisible:function(_2,_3){return this.sourceWidget.fieldShouldBeVisible(_2,_3)},fieldSourceGrid:this,actionType:"filter",actionButtonPrompt:this.filterButtonPrompt,actionButtonProperties:this.filterButtonProperties,fetchDelay:this.fetchDelay,allowFilterExpressions:this.allowFilterExpressions,expressionDataSource:this.getDataSource()},this.filterEditorDefaults,this.filterEditorProperties);if(_1.bodyDefaults==null)_1.bodyDefaults={};_1.bodyDefaults.focusChanged=function(_2){if(_2)this.parentElement.startEditing()}
this.filterEditor=isc.RecordEditor.create(_1)}
,isc.A.onBodyKeyPress=function isc_ListGrid_onBodyKeyPress(){}
,isc.A.getFilterEditorValueMap=function isc_ListGrid_getFilterEditorValueMap(_1){return _1.filterEditorValueMap||_1.valueMap}
,isc.A.getFilterEditorType=function isc_ListGrid_getFilterEditorType(_1){if(_1.filterEditorType!=null)return _1.filterEditorType;if(isc.SimpleType.inheritsFrom(_1.type,"date")&&this.getDataSource()&&this.getDataSource().supportsAdvancedCriteria())
{return"MiniDateRangeItem"}
var _2=isc.addProperties({},_1,{canEdit:_1.canFilter!==false,length:null});if(_2._constructor!=null)delete _2._constructor;if(_1.filterEditorType!=null)_2.editorType=_1.filterEditorType;isc.addProperties(_2,_1.filterEditorProperties);var _3=isc.DynamicForm.getEditorType(_2,this);return _3}
,isc.A.getFilterEditorProperties=function isc_ListGrid_getFilterEditorProperties(_1){var _2=_1.filterEditorProperties||{};if(_1.filterOperator)_2.operator=_1.filterOperator;return _2}
,isc.A.getFilterEditor=function isc_ListGrid_getFilterEditor(){return this.filterEditor}
,isc.A.setFilterEditorCriteria=function isc_ListGrid_setFilterEditorCriteria(_1){if(this.filterEditor){this.setFilterValues(_1)}}
,isc.A.getFilterEditorCriteria=function isc_ListGrid_getFilterEditorCriteria(_1){if(this.filterEditor){if(!_1||!this.filterEditor.getEditForm()){return this.filterEditor.getValuesAsCriteria(this.autoFetchTextMatchStyle)}else{var _2=this.filterEditor.getEditForm().getItems(),_3=true,_4={},_5={operator:"and",criteria:[]};for(var i=0;i<_2.length;i++){if(_2[i].hasAdvancedCriteria()){var _7=_2[i].getCriterion();if(_7!=null){_3=false;_5.criteria.add(_7)}}else{var _8=_2[i].getValue();if(_8!=null){_4[_2[i].getFieldName()]=_8}}}
if(!_3){_4=isc.DataSource.combineCriteria(_4,_5)}
return _4}}}
,isc.A.setCriteria=function isc_ListGrid_setCriteria(_1){if(this.filterEditor!=null){this.setFilterValues(_1)}
return this.Super("setCriteria",arguments)}
,isc.A.setFilterValues=function isc_ListGrid_setFilterValues(_1){this.$32d=isc.addProperties({},_1);this.updateFilterEditor()}
,isc.A.updateFilterEditor=function isc_ListGrid_updateFilterEditor(){var _1=this.filterEditor;if(!_1)return;var _2=this.$32e();this.filterEditor.setValuesAsCriteria(_2,true)}
,isc.A.$32e=function isc_ListGrid__getFilterEditorValues(){var _1=isc.addProperties({},this.$32d);if(this.updateFilterEditorValues!=null){_1=this.updateFilterEditorValues(_1,this.autoFetchTextMatchStyle)}
if(_1==null){_1={};for(var i=0;i<this.completeFields.length;i++){_1[this.completeFields[i].name]=this.completeFields[i].defaultFilterValue}}
return _1}
,isc.A.clearFilterValues=function isc_ListGrid_clearFilterValues(){this.$32d=null;this.updateFilterEditor()}
,isc.A.handleFilterEditorSubmit=function isc_ListGrid_handleFilterEditorSubmit(_1,_2){if(this.filterEditorSubmit!=null&&this.filterEditorSubmit(_1)==false)return;this.filterData(_1,null,_2)}
,isc.A.getInitialCriteria=function isc_ListGrid_getInitialCriteria(){var _1={},_2,_3=this.getFields(),_4;for(var i=0;i<_3.length;i++){if(_3[i].defaultFilterValue!==_4){_2=true;var _6=this.getFieldName(_3[i]);_1[_6]=_3[i].defaultFilterValue}}
if(!_2){_1=this.initialCriteria||this.getCriteria()}else{isc.addProperties(_1,this.initialCriteria||this.getCriteria())}
return _1}
,isc.A.$v6=function isc_ListGrid__adjustSpecialPeers(_1){if(this.filterEditor!=null)this.filterEditor.setZIndex(_1-1);return this.Super("$v6",arguments)}
,isc.A.canEditCell=function isc_ListGrid_canEditCell(_1,_2){if(_2<0||_2>=this.fields.length)return false;var _3=this.getCellRecord(_1,_2);if(_3!=null){if(!this.recordIsEnabled(_1,_2))return false;if(this.$32o==_3)return false}
var _4=this.getField(_2);if(_4&&_4.disabled)return false;if(_4&&_4.type=="summary")return false;if(!this.isEditable()||this.getCellBooleanProperty("canEdit",_1,_2,(_3!=null?this.recordEditProperty:null))==false){return false}
return true}
,isc.A.isEditable=function isc_ListGrid_isEditable(){if(this.canEdit==false)return false;if(this.canEdit==true||this.canEdit==this.$18q){var _1=this.getFields()||[];for(var i=0;i<_1.length;i++){if(_1[i].canEdit!=false)return true}
return false}else{var _1=this.getFields()||[];for(var i=0;i<_1.length;i++){if(_1[i].canEdit==true)return true}
return false}}
,isc.A.setCanEdit=function isc_ListGrid_setCanEdit(_1){if(_1==false){if(this.getEditRow()!=null)this.cancelEditing(isc.ListGrid.PROGRAMMATIC);this.canEdit=false}else{this.canEdit=_1}}
,isc.A.setFieldCanEdit=function isc_ListGrid_setFieldCanEdit(_1,_2){if(isc.isA.String(_1))_1=this.getField(_1);if(_1==null||!this.completeFields.contains(_1)||_1.canEdit==_2)return;_1.canEdit=_2;if(this.$30a){var _3=this.getEditRow(),_4=_1[this.fieldIdProperty],_5=this.getColNum(_1);if(this.editByCell){if(!_2&&_5==this.getEditCol()){this.cancelEditing(isc.ListGrid.PROGRAMMATIC)}}else if(_5>=0){if(!_2&&_5==this.getEditCol()){var _6=this.$286.getItem(_4),_7=_6.hasFocus;var _8=this.findNextEditCell(_3,_5,-1,true,false,false,true);if(_8==null||_8[0]!=_3)
_8=this.findNextEditCell(_3,_5,1,true,false,false,true);if(_8==null||_8[0]!=_3){this.cancelEditing(isc.ListGrid.PROGRAMMATIC);return}
this.startEditing(_8[0],_8[1],!_7)}
this.refreshCell(this.getEditRow(),_5)}}}
,isc.A.handleEditCellEvent=function isc_ListGrid_handleEditCellEvent(_1,_2,_3,_4){if(_3==this.$51l)this.$51k=true;else delete this.$51k
if(_1<0||_2<0)return false;if(this.editByCell){if(!this.canEditCell(_1,_2))return false}else{var _5=this.findNextEditCell(_1,_2,-1,true,true,false,true);if(_5==null||_5[0]!=_1)
_5=this.findNextEditCell(_1,_2,1,true,false,false,true);if(_5==null||_5[0]!=_1)return false;_2=_5[1]}
var _6;if(_4!==_6){this.setEditValue(_1,_2,_4)}
return this.startEditing(_1,_2,null,_3)}
,isc.A.startEditing=function isc_ListGrid_startEditing(_1,_2,_3,_4,_5){if(!this.canEdit&&!(this.completeFields||this.fields).getProperty("canEdit").or()){this.canEdit=true}
if(this.completeFields==null)this.setFields(this.fields);var _6=(_1==null),_7=(_2==null);if(_6||_7){var _8=(_6?0:_1),_9=(_7?0:_2);var _10=this.findNextEditCell(_8,_9,1,_7,true);if(_10==null){this.logInfo("startEditing() passed bad cell coordinates:"+[_1,_2],"gridEdit")}else{this.logInfo("startEditing() using derived coordinates:"+_10,"gridEdit");_1=_10[0];_2=_10[1]}}
if(_1==null||_1<0||_1>this.getTotalRows()){if(!_5){this.logWarn("startEditing() passed bad cell coordinates:"+[_1,_2]+", can't edit"+this.getStackTrace(),"gridEdit")}
return false}
if(!this.canEditCell(_1,_2)){this.logInfo("startEditing(): cell "+[_1,_2]+" is non editable. Returning.","gridEdit");return false}
if(this.$30a){this.$32f((_4||isc.ListGrid.PROGRAMMATIC),this.getEditRow(),this.getEditCol(),_1,_2)}else{this.$31u(_1,_2,_3)}
return true}
);isc.evalBoundary;isc.B.push(isc.A.$32f=function isc_ListGrid__changeEditCell(_1,_2,_3,_4,_5){var _6=this.getEditValue(_2,_3);var _7=this.getFieldName(_3),_8=this.$286,_9=_8?_8.getItem(_7):null,_10=_9?!_9.$30w:true;if(_9){delete _9.$30w;delete _9.$30v;if(this.$32g(_9,_2,_4,_5)){this.$32h(_9,_3)}}
var _11=(_4!=_2),_12=this.$300(_2,_3);if(_10){var _13=!this.$32i(_1,_2,_3,_6);if(_11&&!_13){_13=!this.$32j(_1,_2,_12)}
if(_13){if(_1==isc.ListGrid.EDIT_FIELD_CHANGE){var _14=this.getFieldName(_5);if(_9&&_8.getItem(_14).hasFocus){_9.focusInItem()}}
return false}}
var _15=(this.autoSaveEdits&&((_11&&this.shouldSaveOnRowExit(_2,_3,_1))||this.shouldSaveOnCellExit(_2,_3,_1)));if(!_15&&this.$32k()){var _16,_17=_11&&this.shouldValidateByRow(_2,_3,_1);if(_17&&!this.usingCellRecords){_16=!this.validateRow(_2)}else{if(_17||this.shouldValidateByCell(_2,_3,_1))
{_16=!this.validateCell(_2,_3)}}
if(this.stopOnErrors&&_16)return false}
if(_15){return this.$32l(_4,_5,_1)}else{this.$31u(_4,_5)}
if(_11&&this.isGrouped&&(!_15||!this.shouldSaveLocally())){this.$75i(_2)}}
,isc.A.$75i=function isc_ListGrid__updateGroupForEditValueChange(_1){if(!this.isGrouped||!isc.isA.Tree(this.data))return;var _2=this.data.get(_1),_3=_2,_4=false;if(_2==null)return;var _5=this.getGroupByFields();if(!isc.isAn.Array(_5))_5=[_5];var _6=this.getEditedRecord(_1);for(var i=_5.length-1;i>=0;i--){var _8=_5[i],_3=this.data.getParent(_3);if(_3==null||_3.groupName!=_8){this.logWarn("error updating group for edit value change - unexpected group "+"tree structure. Regrouping.");_4=true;break}
var _9=_6[_8];if(_3.groupValue!=_9){_4=true;break}}
if(_4){this.regroup();this.$31n();this.markForRedraw()}
return _4}
,isc.A.$32g=function isc_ListGrid__shouldParkFocus(_1,_2,_3,_4){if(!isc.Browser.isIE)return false;var _5=(isc.isA.PopUpTextAreaItem(_1)&&_1.$32m&&_1.$21t.hasFocus)||(_1.hasFocus&&isc.FormItem.$12r(_1));if(!this.editByCell&&_3==_2)return false;var _6=this.getEditorType(this.getField(_4),this.getCellRecord(_3,_4));return!(_6==null||isc.FormItem.$12r(_6,true))}
,isc.A.$31u=function isc_ListGrid__startEditing(_1,_2,_3){if(_1=="delayed"){var _4=this.$32n;if(_4==null)return;_1=_4[0];_2=_4[1];_3=_4[2]}else if(this.isDrawn()&&(!this.body.readyToRedraw()||(this.frozenBody&&!this.frozenBody.readyToRedraw())))
{if(!this.$32n){this.delayCall("$31u",['delayed'],0)}
this.$32n=[_1,_2,_3];return}
delete this.$32n;var _5=this.getEditRow()!=_1;if(!_5&&!_3&&(this.getEditCol()==_2)){this.getEditForm().focusInItem(this.getEditorName(_1,_2));return}
this.logInfo("Starting editing at row "+_1+", colNum "+_2,"gridEdit");if(this.$32o!=null)this.closeRecord();if(this.$30a){if(this.editByCell||_5){this.hideInlineEditor(false,true)}}
var _6=(this.$32p==null)||_5||this.saveByCell;if(_6){this.$32p=this.$32q()}
var _7=this.$30a&&(this.getEditRow()==_1)
this.initializeEditValues(_1,_2,_7);var _8=this.getCellRecord(_1,_2);if(this.selectOnEdit&&_8!=null)this.selectRecordForEdit(_8);if(this.modalEditing)this.clearLastHilite();if(_8==null&&this.addNewBeforeEditing){this.$285=_1;this.$30u=_2;var _9=this.shouldWaitForSave();var _10="this.$32r("+_9+","+_3+")";this.saveEdits(isc.ListGrid.PROGRAMMATIC,_10);if(_9)return;else{_1=this.$285;_2=this.$30u}}
this.showInlineEditor(_1,_2,true,_5,_3);return true}
,isc.A.selectRecordForEdit=function isc_ListGrid_selectRecordForEdit(_1){if(!this.editByCell)_1.$29m=true;if(this.canSelectCells){var _2=this.getRecordCellIndex(_1);this.selection.selectSingleCell(_2[0],_2[1])}else if(this.selection!=null&&(!this.selection.isSelected(_1)||this.selection.multipleSelected())){if(this.selectionType==isc.Selection.NONE){this.logInfo("selectOnEdit is true, but this.selectionType is set to 'none'."+" Unable to perform a selection on edit.","gridEdit")}else if(this.selectionType==isc.Selection.SIMPLE)this.selection.select(_1);else this.selection.selectSingle(_1)}
delete _1.$29m}
,isc.A.$51n=function isc_ListGrid__updateEditorSelection(_1){if(!isc.isA.TextItem(_1)&&!isc.isA.TextAreaItem(_1)&&!(isc.isA.DateItem(_1)&&_1.useTextField))return;if(_1.$897)return;var _2=isc.isA.DateItem(_1)?_1.dateTextField.getDataElement():_1.getDataElement();if(!_2)return;if(this.$51m||!this.autoSelectEditors){var _3=_1.getDataElement().value||"";_1.setSelectionRange(_3.length,_3.length);delete this.$51m}else{_2.select()}}
,isc.A.$32r=function isc_ListGrid__updateNewEditRowValues(_1,_2){var _3=this.$285,_4=this.$30u,_5=this.getCellRecord(_3,_4);if(_5!=null&&_5!="loading"){var _6=this.getEditValues(_3,_4);for(var i in _5){if(_5[i]!=null&&_6[i]==null){_6[i]=_5[i]}}}
if(_1)this.showInlineEditor(_3,_4,true,true,_2);else this.updateEditRow(_3)}
,isc.A.editField=function isc_ListGrid_editField(_1,_2){if(this.completeFields==null)this.setFields(this.fields);var _3;if(isc.isA.Number(_1))_3=_1;else _3=this.fields.findIndex(this.fieldIdProperty,_1);if(_2==null){_2=this.getEditRow();if(_2==null){this.logWarn("editField(): unable to determine which row to edit - returning.","gridEdit");return}}
return this.startEditing(_2,_3)}
,isc.A.showInlineEditor=function isc_ListGrid_showInlineEditor(_1,_2,_3,_4,_5){if(this.$30a){if(_1!=this.getEditRow()||(this.editByCell&&_2!=this.getEditCol())){this.logWarn("Unexpected call to 'showInlineEditor' during another edit "+"- cancelling previous edit","gridEdit");this.cancelEditing();this.startEditing(_1,_2);return}}
if(this.$82k&&this.$82k[_1]==_1&&this.$82k[_2]==_2)
{return}
this.$82k=[_1,_2];var _6=this.suppressEditScrollIntoView||_5;this.logDebug("showing inline editor at: "+[_1,_2]+", will focus: "+!_5,"gridEdit");var _7=this.body&&(!this.body.shouldShowAllColumns()||!this.body.showAllRows);if(_7&&!_6){var _8=this.body.scrollRedrawDelay;this.body.scrollRedrawDelay=0;this.scrollCellIntoView(_1,_2,false);this.body.scrollRedrawDelay=_8}
if(this.rollOverCanvas&&this.rollOverCanvas.$289==_1){this.updateRollOverCanvas(this.rollOverCanvas.$289,this.rollOverCanvas.$57n,true)}
var _9;if(!this.$30a){var _10=this.makeEditForm(_1,_2);if(this.$686()&&_10){this.logInfo("Edit Form rebuilt with alwaysShowEditors:true, requires full redraw","inactiveEditorHTML");_9=true}}
this.$285=_1;this.$30u=_2;this.$31q=_1;this.$32s(_1,_2,_9);this.$82k=null;if(this.body&&!_6){var _11;if(!_7)_11=true;else{var _12=this.body,_13=_12.getRowTop(_1),_14=_12.getRowSize(_1),_15=_12.getScrollTop(),_16=_12.getViewportHeight();_11=(_13<_15)||(_13+_14>_15+_16)}
if(_11){this.scrollCellIntoView(_1,_2,false)}}
if(!this.isDrawn()){return}
this.$32t();var _17=this.getEditorName(_1,_2),_18=this.$286.getItem(_17);if(_18==null){this.logWarn("ListGrid showing inline editor. Unable to get a pointer to the edit "+"form item for field:"+_17)}else{if(_3)_18.$30w=true;if(_4)_18.$30v=true;if(!_5){if(isc.Browser.isMoz){var _19=this.body.getClipHandle(),_20=_19.scrollTop}
var _21=isc.EH.lastEvent;var _22=false;if(isc.Browser.isIE&&_21.eventType==isc.EH.MOUSE_DOWN){var _23=_21.target;if(_23!=this.$286){_22=true;var _24=this.$286.getCanvasItemCanvii();for(var i=0;i<_24.length;i++){if(_24[i]==_23||_24[i].contains(_23)){_22=false;break}}}}
if(isc.Browser.isIE){var _26=isc.ListGrid.$32u;if(_26&&(_26.hasFocus||_26.itemHasFocus()))_22=true}
if(_22){this.$32v=isc.Timer.setTimeout(this.$286.getID()+".focusInItem('"+_17+"');",0)}else{if(isc.Browser.isMoz&&this.body.overflow==isc.Canvas.VISIBLE){this.adjustOverflow()}
this.$286.focusInItem(_17)}}}}
,isc.A.$32s=function isc_ListGrid__showEditForm(_1,_2,_3){var _4=this.$30a;this.$30a=true;if(!this.isDrawn()||!this.body)return;var _5=this.isEmpty()||(!_4&&(_1>=this.data.getLength())&&(this.showNewRecordRow||(this.body.getTableElement(_1)==null)));var _6=this.$686();var _7=this.getCellRecord(_1,_2);if(_7&&_7.$29a!=null&&_7.$29a.length>0){_3=true}
if(_3||_5||this.body.isDirty()||(this.frozenBody&&this.frozenBody.isDirty())){var _8=this.bodyLayout?this.bodyLayout:this.body;_8.redraw("Showing editor");return}
if(this.editByCell){var _9=(this.frozenFields!=null)||(this.baseStyle==null);if(_9)this.refreshRow(_1);else this.refreshCell(_1,_2)}else{var _10=this.getDrawnFields();for(var i=0;i<_10.length;i++){if(this.isCheckboxField(_10[i]))continue;var _12=_10[i],_13=_10[i][this.fieldIdProperty],_14=this.$286.getItem(_13),_2=_14.colNum,_15=!!(_4&&_14.isDrawn()),_16=this.canEditCell(_1,_2);if(_15!=_16)this.refreshCell(_1,_2);else if(this.selectOnEdit||this.lastOverRow){var _8=(_12.frozen&&!this.$54d)?this.frozenBody:this.body;_8.$29h(this.getCellRecord(_1,_2),_1,_2)}}}}
,isc.A.$32t=function isc_ListGrid__showEditClickMask(){if(!this.modalEditing){if(this.canHover)this.stopHover();return}
if(!this.$286.clickMaskUp()){if(!this.$32w)
this.$32w=new Function(this.getID()+".$32x()");this.$286.showClickMask(this.$32w,(this.stopOnErrors?isc.EH.SOFT_CANCEL:isc.EH.SOFT),this.$286)}}
,isc.A.stopHover=function isc_ListGrid_stopHover(){if(this.$30a&&isc.EH.getTarget()==this.getEditForm())return;return this.Super("stopHover",arguments)}
,isc.A.shouldWaitForSave=function isc_ListGrid_shouldWaitForSave(){if(this.stopOnErrors&&!this.waitForSave){var _1="Note: ListGrid initialized with 'waitForSave' false, and 'stopOnErrors' true."+" In this case user input will be be blocked during save, to allow server side "+" errors to be determined before the editor is hidden. Setting 'waitForSave' to true.";if(this.waitForSave==false)this.logWarn(_1,"gridEdit");else this.logInfo(_1,"gridEdit");this.waitForSave=true}
return!!(this.waitForSave||this.stopOnErrors)}
,isc.A.hideInlineEditor=function isc_ListGrid_hideInlineEditor(_1,_2){_1=_1&&(this.hasFocus||(this.body&&this.body.hasFocus)||(this.getEditForm()&&this.getEditForm().hasFocus)||(isc.ListGrid.$32u&&isc.ListGrid.$32u.hasFocus));if(!this.$30a)return false;this.$30a=null;this.$519=null;var _3=this.$285,_4=this.$30u;this.$285=this.$30u=null;if(!_2)this.$286.hideClickMask();if(this.getEditValues(_3,_4)!=null&&(!this.$31o||!this.$31o[this.getEditValuesID(_3,_4)])&&!this.recordHasChanges(_3,_4,false)&&!this.recordMarkedAsRemoved(_3))
{this.logInfo("hideInlineEditor for row with no edits - dropping edit values","gridEdit");this.$321(_3,_4)}
if(!this.body)return true;var _5=this.$286;if(_5.hasFocus){_5.blur()}
if(isc.Browser.isIE){var _6=_5.getFocusSubItem(),_7=isc.EH.$ld;if(_5.hasFocus||(_7&&((_7==_5)||(isc.EH.$ld.form==_5))))
{_6.elementBlur();this.$32h(_6,_4)}}
var _8=this.getCellRecord(_3,_4),_9=false;if(_8&&_8.$29a!=null&&_8.$29a.length>0){_9=true}
if(!this.body.isDirty()&&(!this.bodyLayout||!this.bodyLayout.isDirty())&&!this.isDirty())
{if(_9||_3>=this.getTotalRows()){var _10=this.bodyLayout||this.body;_10.markForRedraw("Editor Hidden")}else{if(this.editByCell){var _11=(this.frozenFields!=null)||(this.baseStyle==null);if(_11)this.refreshRow(_3);else this.refreshCell(_3,_4)}
else this.refreshRow(_3);this.refreshGroupSummary(_3)}}
if(_1){this.$30c=true;this.body.focus()}
return true}
,isc.A.$32h=function isc_ListGrid__parkFocus(_1,_2){if(isc.isA.TextItem(_1)||isc.isA.TextAreaItem(_1)||isc.isA.PopUpTextAreaItem(_1))
{var _3=isc.ListGrid.$32u;if(!_3){_3=isc.ListGrid.$32u=isc.DynamicForm.create({pointersToThis:[{object:isc.ListGrid,property:"$32u"}],getFocusParkItem:function(){return this.getItem(0)},autoDraw:false,_redrawWithParent:false,ID:"$322",_generated:true,selectOnFocus:true,tabIndex:-1,numCols:1,items:[{name:"focusPark",type:"text",showTitle:false,handleKeyPress:function(){return false}}],width:1,height:1,overflow:isc.Canvas.HIDDEN,itemHasFocus:function(){var _4=this.getFocusParkItem();if(_4.hasFocus)return true;if(isc.Browser.isIE&&this.isDrawn()&&this.isVisible()&&(this.getActiveElement()==this.getFocusParkItem().getFocusElement()))
return true;return false},redraw:function(){var _5=false;if(this.itemHasFocus())_5=true;this.Super("redraw",arguments);if(_5)this.focusInItem(this.getFocusParkItem())}})}
if(_3.isVisible())_3.hide();_3.moveTo(this.getPageLeft()+(this.getColumnLeft(_2)-this.body.getScrollLeft()),this.getPageTop()+Math.min(((this.showHeader?this.headerHeight:0)+this.getRowTop(this.getEditRow())
-this.body.getScrollTop()),this.getScrollHeight()-1));_3.sendToBack();_3.show();if(this.$286.clickMaskUp())_3.unmask(this.$286.getID());_3.getFocusParkItem().focusInItem();_1.form.hasFocus=false}}
,isc.A.makeEditForm=function isc_ListGrid_makeEditForm(_1,_2){var _3=this.getCellRecord(_1,_2),_4=this.getEditDisplayValues(_1,_2),_5;if(this.updateEditorItemsInPlace&&this.$286!=null){_5=true;var _6=this.editByCell?[this.getField(_2)]:this.getDrawnFields();if(_6){if(_6.length!=this.$286.getItems().length){_5=false}else{for(var i=0;i<_6.length;i++){var _8=_6[i],_9=this.getEditorName(_1,_8),_10=this.$286.getItem(_9);if(_10==null||(this.getEditorType(_8,_4)!=this.$286.getEditorType(_10)))
{_5=false;break}}}}
if(_5&&_6){var _11=this.getDrawnFieldWidths(_3,_6);for(var i=0;i<_6.length;i++){var _8=_6[i],_12=this.getColNum(_8),_9=this.getEditorName(_1,_8),_10=this.$286.getItem(_9),_3=this.getRecord(_1),_13=this.getEditedRecord(_1),_14=this.getEditItem(_8,_3,_13,_1,_12,_11[i],true);_10.setProperties(_14);_10.$8t=null;if(_8.frozen){_10.containerWidget=this.frozenBody}else{_10.containerWidget=this.body}}}else{var _15=this.getEditRowItems(_3,_1,_2,this.editByCell);this.$286.setItems(_15)}
this.$286.setValues(_4)}else{var _15=this.getEditRowItems(_3,_1,_2,this.editByCell);var _16=isc.addProperties({},this.editFormDefaults,{grid:this,locatorParent:this,showErrorIcons:this.showErrorIcons,tabIndex:this.getTabIndex(),dataSource:this.dataSource,autoComplete:this.autoComplete,uniqueMatch:this.uniqueMatch,autoFocus:false,items:_15,values:_4},this.editFormProperties);if(this.dateFormatter!=null&&_16.dateFormatter==null){_16.dateFormatter=this.dateFormatter}
if(this.datetimeFormatter!=null&&_16.datetimeFormatter==null){_16.datetimeFormatter=this.datetimeFormatter}
if(this.timeFormatter!=null&&_16.timeFormatter==null){_16.timeFormatter=this.timeFormatter}
this.$286=isc.DynamicForm.create(_16)}
if(this.logIsDebugEnabled()){this.logDebug("editRowForm created with values: "+this.echo(this.$286.getValues()),"gridEdit")}
return!_5}
,isc.A.getEditForm=function isc_ListGrid_getEditForm(){return this.$30a?this.$286:null}
,isc.A.getEditFormItem=function isc_ListGrid_getEditFormItem(_1){var _2=this.getEditForm();if(!_2)return null;var _3=this.getEditorName(this.getEditRow(),_1),_4=_2.getItem(_3);if(!isc.isA.Number(_1)||(_4&&_4.colNum==_1))return _4}
,isc.A.getEditFormValue=function isc_ListGrid_getEditFormValue(_1){var _2=this.getEditFormItem(_1);return(_2?_2.getValue():null)}
,isc.A.$33g=function isc_ListGrid__updateEditItemValues(){if(!this.$286)return;var _1=this.getEditRow(),_2=this.getEditCol(),_3=this.getEditDisplayValues(_1,_2);this.$286.setValues(_3)}
,isc.A.getEditDisplayValues=function isc_ListGrid_getEditDisplayValues(_1,_2){var _3=this.getEditValues(_1,_2),_4=this.getCellRecord(_1,_2),_5={};for(var _6 in _4){_5[_6]=_4[_6]}
for(var _6 in _3){_5[_6]=_3[_6]}
return _5}
,isc.A.getEditFormItemFieldWidths=function isc_ListGrid_getEditFormItemFieldWidths(_1){var _2=[];for(var i=0;i<this.fields.length;i++){var _4=this.getLocalFieldNum(i),_5=this.fields[i],_6=(!this.$54d&&_5.frozen)?this.frozenBody:this.body;_2[i]=_6.getInnerColumnWidth(_4)}
return _2}
,isc.A.getEditorValueMap=function isc_ListGrid_getEditorValueMap(_1,_2){if(_1.getEditorValueMap!=null){isc.Func.replaceWithMethod(_1,"getEditorValueMap","values,field,grid");return _1.getEditorValueMap(_2,_1,this)}
if(_1.editorValueMap!=null){return _1.editorValueMap}
if(_1.editorProperties&&_1.editorProperties.valueMap){return _1.editorProperties.valueMap}
return _1.valueMap}
,isc.A.getEditorValueIcons=function isc_ListGrid_getEditorValueIcons(_1,_2){return _1.editorValueIcons||_1.valueIcons}
,isc.A.getEditorValueIconWidth=function isc_ListGrid_getEditorValueIconWidth(_1){if(_1.editorValueIconWidth!=null)return _1.editorValueIconWidth;return(_1.valueIconWidth!=null?_1.valueIconWidth:_1.valueIconSize)}
,isc.A.getEditorValueIconHeight=function isc_ListGrid_getEditorValueIconHeight(_1){if(_1.editorValueIconHeight!=null)return _1.editorValueIconHeight;return _1.valueIconHeight!=null?_1.valueIconHeight:_1.valueIconSize}
,isc.A.setEditorValueMap=function isc_ListGrid_setEditorValueMap(_1,_2){var _3=this.getColNum(_1),_4=this.getField(_1),_5=_4[this.fieldIdProperty];_4.editorValueMap=_2;if(this.$30a){var _6=this.getEditRow(),_7=this.getEditedRecord(_6,_3);this.$286.setValueMap(_5,this.getEditorValueMap(_4,_7))}}
,isc.A.getEditorType=function isc_ListGrid_getEditorType(_1,_2){var _3=isc.addProperties({},_1,_1.editorProperties);return isc.DynamicForm.getEditorType(_3,this)}
,isc.A.getEditorProperties=function isc_ListGrid_getEditorProperties(_1,_2,_3){return isc.addProperties({},this.editorProperties,_1.editorProperties)}
,isc.A.getEditRowItems=function isc_ListGrid_getEditRowItems(_1,_2,_3,_4){var _5=this.body;if(_5==null)return[];var _6,_7,_8=[],_9=[];var _10=this.getEditedRecord(_2,_3);var _11;if(_4)_11=[this.getField(_3)]
else _11=this.getDrawnFields();_8=this.getDrawnFieldWidths(_1,_11);if(_11!=null){for(var i=0;i<_11.length;i++){var _13=this.getColNum(_11[i]);var _14=this.getEditItem(_11[i],_1,_10,_2,_13,_8[i])
if(_14==null)continue;_9[_9.length]=_14}}
return _9}
,isc.A.getDrawnFields=function isc_ListGrid_getDrawnFields(){if(!this.body)return null;var _1=[],_2=this.body;var _3=this.frozenFields,_4=this.freezeLeft();if(_3&&_4){_1.addList(_3)}
var _5,_6;if((_2.$254==null)||!_2.isDrawn()||_2.isDirty()||_2.$58p){var _7=_2.getDrawArea();_5=_7[2];_6=_7[3]}else{_5=_2.$254;_6=_2.$255}
if(_3&&_4){_5+=_3.length;_6+=_3.length}
for(var i=_5;i<=_6;i++){_1.add(this.fields[i])}
if(_3&&!_4){_1.addList(_3)}
return _1}
,isc.A.getDrawnFieldWidths=function isc_ListGrid_getDrawnFieldWidths(_1,_2){if(!_2)_2=this.getDrawnFields();if(!_2)return null;var _3=this.getEditFormItemFieldWidths(_1),_4=[];for(var i=0;i<_2.length;i++){_4[i]=_3[_2[i].masterIndex]}
return _4}
,isc.A.$323=function isc_ListGrid__popUpTextAreaItemKeyPress(_1,_2,_3){return this.grid.editorKeyPress(this,_2,_3)}
,isc.A.$54z=function isc_ListGrid__popUpTextAreaItemKeyDown(_1,_2,_3){return this.grid.editorKeyDown(this,_2,_3)}
,isc.A.$62k=function isc_ListGrid__popUpTextAreaItemFocus(){var _1=this.form,_2=this.grid,_3,_4,_5,_6;if(_2.$30a){_3=_2.$285;if(!_2.editByCell){_3=_2.$285;_5=this.getFieldName(),_4=_2.fields.findIndex(_2.fieldIdProperty,_5);_6=(_2.$30u!=_4);if(_6){_2.setNewEditCell(_3,_4);_2.cellEditEnd(isc.ListGrid.EDIT_FIELD_CHANGE)}}}}
,isc.A.$324=function isc_ListGrid__getPopUpTextAreaTop(){var _1=this.grid,_2=this.getFieldName(),_3=_1.getEditRow(),_4=_1.getCellStyle(_1.getRecord(_3),_3,_1.getColNum(_2)),_5=_1.getRowPageTop(_3)+isc.Element.$tn(_4)+isc.Element.$tt(_4);return _5}
,isc.A.$581=function isc_ListGrid__checkboxClick(_1,_2,_3,_4){if(!this.hasFocus){var _5=this.grid;_5.setNewEditCell(this.rowNum,this.colNum);_5.cellEditEnd(isc.ListGrid.EDIT_FIELD_CHANGE);this.$30w=true}
return this.invokeSuper("CheckboxItem","handleClick",_1,_2,_3,_4)}
,isc.A.handleEditorChanged=function isc_ListGrid_handleEditorChanged(_1){var _2=_1.getFieldName(),_3=this.getField(_2),_4;if(_3&&_3.validateOnChange!=null)_4=_3.validateOnChange;else _4=this.validateOnChange;var _5=this.getEditRow();if(_5==null||_5!=_1.rowNum)return;if(_4){this.validateCell(_5,_2,false,true)}else if(!_1.hasFocus&&!this.editByCell){if(_1.isDrawn()&&_1.isVisible()){this.storeUpdatedEditorValue(false,_1.colNum);if(this.saveByCell&&this.autoSaveEdits){var _6=isc.ListGrid.EDIT_FIELD_CHANGE;this.saveEdits(_6,null,_5,_1.colNum)}else if(this.validateByCell)this.validateCell(_5,_2)}}}
,isc.A.$325=function isc_ListGrid__editorHandleChangedOverride(_1,_2,_3,_4){this.invokeSuper(this.getClassName(),"handleChanged",_1,_2,_3,_4);if(!this.destroyed)this.grid.handleEditorChanged(this)}
,isc.A.$45p=function isc_ListGrid__editorGetAutoComplete(){var _1=this.grid;if(!_1)return null;var _2=_1.getField(this.getFieldName());if(_2.autoComplete!=null)return _2.autoComplete;if(_1.autoComplete!=null)return _1.autoComplete;return this.Super("$17h",arguments)}
,isc.A.getEditItem=function isc_ListGrid_getEditItem(_1,_2,_3,_4,_5,_6,_7){var _8={};_8.width=_6;_8.record=_2;_8.rowNum=_4;_8.colNum=_5;_8.getGlobalTabIndex=function(){if(!this.containerWidget)return this.Super("getGlobalTabIndex",arguments);return this.containerWidget.getTabIndex()};var _9=this.getEditorName(_4,_1);_8[this.fieldIdProperty]=_9;if(_1.title!=null)_8.title=_1.title;_8.valueMap=this.getEditorValueMap(_1,_3);if(_1.multiple!=null)_8.multiple=_1.multiple;if(_1.valueField!=null)_8.valueField=_1.valueField
if(_1.displayField!=null)_8.displayField=_1.displayField
if(_1.optionDataSource)_8.optionDataSource=_1.optionDataSource
if(_1.optionFilterContext)_8.optionFilterContext=_1.optionFilterContext
if(_1.optionCriteria)_8.optionCriteria=_1.optionCriteria
if(_1.optionOperationId!=null)_8.optionOperationId=_1.optionOperationId;_8.valueIcons=this.getEditorValueIcons(_1,_3);var _10=this.getEditorValueIconWidth(_1),_11=this.getEditorValueIconHeight(_1);if(_10)_8.valueIconWidth=_10;if(_11)_8.valueIconHeight=_11;_8.imageURLPrefix=(_1.editorImageURLPrefix||_1.imageURLPrefix);_8.imageURLSuffix=(_1.editorimageURLSuffix||_1.imageURLSuffix);_8.baseURL=_1.baseURL;_8.imgDir=_1.imgDir;var _12;if(_1.icons!==_12)_8.icons=_1.icons;if(_1.showPickerIcon!==_12)_8.showPickerIcon=_1.showPickerIcon;if(_1.pickerIconSrc!==_12)_8.pickerIconSrc=_1.pickerIconSrc;if(_1.pickerIconWidth!==_12)_8.pickerIconWidth=_1.pickerIconWidth;if(_1.pickerIconHeight!==_12)_8.pickerIconHeight=_1.pickerIconHeight;if(_1.defaultIconSrc!==_12)_8.defaultIconSrc=_1.defaultIconSrc;var _13=(_1.editorIconHeight||_1.iconHeight);if(_13!==_12)_8.iconHeight=_13
var _14=(_1.editorIconWidth||_1.iconWidth);if(_14!==_12)_8.iconWidth=_14;if(_1.iconPrompt!==_12)_8.iconPrompt=_1.iconPrompt;if(_1.iconHSpace!==_12)_8.iconHSpace=_1.iconHSpace;if(_1.iconVAlign!==_12)_8.iconVAlign=_1.iconVAlign;if(this.showValueIconOnly(_1)){if(_1.editorProperties==null)_1.editorProperties={};_1.editorProperties.showValueIconOnly=true}
if(_1.pickListWidth!=null)_8.pickListWidth=_1.pickListWidth;if(_1.pickListFields!=null)_8.pickListFields=_1.pickListFields;_8.textAlign=(_1.cellAlign||_1.align);if(_1.editorProperties!=null){for(var i=0;i<this.$31c.length;i++){var _16=this.$31c[i],_17=_1.editorProperties[_16];if(_17!=null&&!isc.isA.Function(_17)){var _18=isc.FormItem._stringMethodRegistry;_1.editorProperties[_16]=_17=isc.Func.expressionToFunction(_18[_16],_17)}
if(_17!=null)_1.editorProperties[_16]=_17}}
for(var i=0;i<this.$45k.length;i++){var _16=this.$45k[i];if(_1.editorProperties&&_1.editorProperties[_16]!=null){continue}
var _17=_1[_16];if(_17!=null){if(_1.editorProperties==null)_1.editorProperties={};if(!isc.isA.Function(_17)){var _18=isc.FormItem._stringMethodRegistry;_17=isc.Func.expressionToFunction(_18[_16],_17)}
_1.editorProperties[_16]=_17}}
_8.valueIconLeftPadding=this.getValueIconLeftPadding(_1);_8.valueIconRightPadding=this.getValueIconRightPadding(_1);if(!_7){_8.$17h=this.$45p;_8.autoCompleteCandidates=_1.autoCompleteCandidates;_8.uniqueMatch=_1.uniqueMatch;_8.containerWidget=(!this.$54d&&_1.frozen)?this.frozenBody:this.body;_8.grid=this;_8.handleChanged=this.$325;_8.keyDown=function(_8,_24,_25){return this.form.grid.editorKeyDown(_8,_25)}
_8.inactiveEditorMouseDown=function(_24,_25){if(_24&&(_24.grid==this.form.grid.getID())&&_24.rowNum!=null&&_24.colNum!=null)
{this.form.grid.startEditing(_24.rowNum,_24.colNum)}}
_8.type=_1.type;_8.editorType=this.getEditorType(_1,_2);var _19=_8.editorType;if(_1.dateFormatter!=null)_8.dateFormatter=_1.dateFormatter;if(_1.timeFormatter!=null)_8.timeFormatter=_1.timeFormatter;if(_1.displayFormat!=null)_8.displayFormat=_1.displayFormat;var _20=this.$45j(_1);if(_20)_8.inputFormat=_20;if(this.$308[_19]==true||this.$68k[_19]==true||(_1.type==this.$g4&&_19==null)){_8.editorType=(this.$68k[_19]==true?this.$68l:this.$g4);_8.useTextField=true;_8.cellPadding=0;_8.itemCellStyle=null;_8.pickerIconHSpace=0}
if(this.$309[_19]==true){_8.getTextBoxStyle=function(){var _21=this.grid,_2=_21.getCellRecord(this.rowNum,this.colNum);this.textBoxStyle=_21.getCellStyle(_2,this.rowNum,this.colNum);return this.Super("getTextBoxStyle",arguments)}
_8.gridCellStyleChanged=function(_2,_4,_5,_24){var _22=this.$15h();if(_22){_22.className=this.textBoxStyle=_24}}
_8.textBoxCellCSS=isc.Canvas.$42a
_8.textAreaKeyDown=this.$54z;_8.textAreaKeyPress=this.$323;_8.getTextAreaTop=this.$324;if(_8.popUpOnEnter==null)_8.popUpOnEnter=true;_8.textAreaFocus=this.$62k}
if(this.$31a[_19]==true||(_1.type==this.$g2&&_19==null)){if(_8.showLabel==null)_8.showLabel=false;if(_8.handleClick==null){_8.handleClick=this.$581}}
if(_19=="RichTextItem"||_19=="richText"){if(_8.overflow==null)_8.overflow="auto"}
_8.elementFocus=this.$30t;_8.canTabToIcons=false;_8.focusInItem=this.$51o;_8.$109=this.$898}
var _23=this.getEditorProperties(_1,_2,_4);isc.addProperties(_8,_23);return _8}
,isc.A.$51o=function isc_ListGrid__editFormItem_focusInItem(){this.Super("focusInItem",arguments);this.grid.$51n(this)}
,isc.A.$898=function isc_ListGrid__editFormItem_refocusAfterRedraw(){this.$897=true;this.Super("$109",arguments);this.$897=false}
,isc.A.getEditorName=function isc_ListGrid_getEditorName(_1,_2,_3){_2=this.getField(_2);if(!_2)return null;if(_3&&_2.dataPath)return _2.dataPath;return _2[this.fieldIdProperty]}
,isc.A.refreshCell=function isc_ListGrid_refreshCell(_1,_2,_3,_4){if(_1==null){this.logInfo("ListGrid.refreshCell(): first parameter rowNum not present, returning");return}
if(!this.isDrawn()||!this.body)return;var _5=this.getFieldBody(_2);if(_5.isDirty()){this.logDebug("refresh cell redrawing body","gridEdit");_5.redraw("refresh cell");return false}
if(this.$686(this.getField(_2),true)){this.$69l(_1,_2)}
var _6=this.getLocalFieldNum(_2);_5.refreshCellStyle(_1,_6);if(!_5.shouldRefreshCellHTML()){this.refreshCellValue(_1,_2,_3,_4)}}
,isc.A.refreshCellValue=function isc_ListGrid_refreshCellValue(_1,_2,_3,_4){if(!this.isDrawn()||!this.body)return;var _5=this.getFieldBody(_2),_6=this.getLocalFieldNum(_2);if(!_5.$29l(_1,_6)){this.delayCall("refreshCellValue",[_1,_2,_3,_4]);return}
var _7=this.getEditorName(_1,_2),_8=this.getFieldName(_2),_9=this.$286,_10,_11=false,_12,_13;if(_9){var _10=_9.getItem(_7),_14=(this.$30a&&_1==this.getEditRow());if(_10&&_10.colNum!=_2)_10=null;if(_14){_12=(_10&&_10.isDrawn());_13=this.canEditCell(_1,_2);if(_10!=null&&_9.hasFocus){var _15=_9.getFocusSubItem();_11=(_15==_10||(_10.items&&_10.items.contains(_15)))}}else if(_10&&_10.rowNum==_1){_13=false;_12=_10.isDrawn()}}
if(!_4&&(_11&&_12&&_13))
{return}
if(_12){this.getUpdatedEditorValue();if(_10!=null){if(_11){if(_13){_9.$106()}
else _10.blurItem()}
if(_13)_10.redrawing()}}
delete _5.$316;_5.refreshCellValue(_1,_6);if(_10&&(_12||_13)){this.$30b(_10,null,_5);if(_13){_10.setValue(this.getEditDisplayValue(_1,_2));if(_11){_9.$11b(_10)}}}}
,isc.A.refreshRow=function isc_ListGrid_refreshRow(_1){if(!this.body||!this.isDrawn())return;var _2=this.frozenFields&&this.frozenFields.length>0;if(_2&&!this.frozenBody)_2=false;var _3=false;if(this.body.isDirty())_3=true;if(_2&&!_3){if(this.frozenBody.isDirty()||this.bodyLayout.isDirty())_3=true}
if(_3){var _4=_2?this.bodyLayout:this.body;return _4.redraw("refresh row")}
if(this.$30a&&this.$286!=null){this.logInfo("refresh row: "+_1,"gridEdit")}
if(_2){for(var i=0;i<this.frozenFields.length;i++){this.refreshCell(_1,this.getFieldNum(this.frozenFields[i]),true)}}
var _6=this.body.$254,_7=this.body.$255;for(var i=_6;i<=_7;i++){var _8=this.getFieldNumFromLocal(i,this.body);this.refreshCell(_1,_8,true)}}
,isc.A.startEditingNew=function isc_ListGrid_startEditingNew(_1,_2){if(!this.canEdit&&!(this.completeFields||this.fields).getProperty("canEdit").or()){this.canEdit=true}
if(isc.isAn.Array(this.data)&&this.data.length==0&&this.dataSource&&!this.shouldSaveLocally())
{this.fetchData(null,null,{$326:true});this.data.setFullLength(0)}
var _3=this.body?this.body.getTotalRows():this.getTotalRows();if(this.showNewRecordRow)_3-=1;var _4=this.findNextEditCell(_3,0,1,true,true,true);if(_4==null){this.logInfo("startEditingNew() failed to find any editable fields in this grid.","gridEdit");return}
if(_1!=null)this.setEditValues(_4,isc.addProperties({},_1),true);this.startEditing(_4[0],_4[1],_2)}
,isc.A.updateEditRow=function isc_ListGrid_updateEditRow(_1){if(this.$285!=_1||!this.$286){return}
this.$286.setItemValues();delete this.$286.$10u;this.$286.$327=false}
,isc.A.shouldFixRowHeight=function isc_ListGrid_shouldFixRowHeight(_1,_2){if(this.canEdit!=false&&this.$30a&&_2==this.$285){return false}
return this.fixedRecordHeights}
,isc.A.$32q=function isc_ListGrid__getNextEditFlowID(){if(this.$328==null)this.$328=isc.timeStamp();return this.$328++}
,isc.A.$32x=function isc_ListGrid__handleClickOutsideEditor(){var _1=this.getEditRow();this.cellEditEnd(isc.ListGrid.CLICK_OUTSIDE)}
,isc.A.getEditDisplayValue=function isc_ListGrid_getEditDisplayValue(_1,_2,_3){var _4;if(_3===_4)_3=this.getCellRecord(_1,_2);var _5=this.$30y(_1,_2);if(_5===_4&&_3!=null){_5=this.getRawCellValue(_3,_1,_2)}
_5=this.$329(_5,_3,_1,_2);return _5}
,isc.A.$329=function isc_ListGrid__formatEditorValue(_1,_2,_3,_4){if(_2==null)_2=this.$300(_3,_4);var _5=this.fields[_4];if(_5&&_5.formatEditorValue!=null){isc.Func.replaceWithMethod(_5,"formatEditorValue","value,record,rowNum,colNum,grid");_1=_5.formatEditorValue(_1,_2,_3,_4,this)}else if(this.formatEditorValue!=null){_1=this.formatEditorValue(_1,_2,_3,_4)}
return _1}
,isc.A.getEditValuesID=function isc_ListGrid_getEditValuesID(_1){if(_1==null||this.$52b==null)return null;if(isc.isA.String(_1)&&this.$52b[_1]!=null)return _1;if(isc.isA.Number(_1)){return this.$33a[_1]}
for(var i in this.$52b){var _3=this.$52b[i];if(_3==_1)return i;var _4=_3.$33b;if(_4&&this.comparePrimaryKeys(_4,_1)){return i}}
return null}
,isc.A.getEditSession=function isc_ListGrid_getEditSession(_1,_2){if(this.$52b==null)return null;if(!isc.isA.String(_1))_1=this.getEditValuesID(_1,_2);return this.$52b[_1]}
,isc.A.getEditSessionRowNum=function isc_ListGrid_getEditSessionRowNum(_1){_1=this.getEditSession(_1);return(_1!=null?_1.$24t:null)}
,isc.A.getEditSessionColNum=function isc_ListGrid_getEditSessionColNum(_1){_1=this.getEditSession(_1);return(_1!=null?_1.$24u:null)}
,isc.A.getAllEditRows=function isc_ListGrid_getAllEditRows(_1){return this.getAllEditCells(_1,true)}
,isc.A.getAllEditCells=function isc_ListGrid_getAllEditCells(_1,_2){var _3=this.$52b,_4=[];if(!_3)return _4;if(_1)return isc.getKeys(this.$52b);for(var i in _3){var _6=_3[i].$24t;_6=parseInt(_6);if(_6==null||_6<0||isNaN(_6))continue;if(_2)_4[_4.length]=_6
else{var _7=_3[i].$24u;if(_7==null){_7=this.$30a&&(_6==this.getEditRow())?this.getEditCol():this.getRowEditColNum(_6)}
if(_7!=null&&!isc.isA.Number(_7))_7=parseInt(_7);_4[_4.length]=[_6,_7]}}
return _4}
,isc.A.getEditValues=function isc_ListGrid_getEditValues(_1,_2){if(_1==null){return this.logWarn("getEditValues() called with no valuesID. "+(this.logIsDebugEnabled("gridEdit")?this.getStackTrace():""))}
if(_2==null&&isc.isA.Array(_1)){_2=_1[1];_1=_1[0]}
var _3=(isc.isA.Number(_1)?_1:this.getEditSessionRowNum(_1));if(this.$30a&&(this.getEditRow()==_3)){this.storeUpdatedEditorValue()}
var _4=isc.addProperties({},this.$300(_1,_2));if(_4!=null){delete _4[this.recordRemovedProperty]}
return _4}
,isc.A.$300=function isc_ListGrid__getEditValues(_1,_2){var _3=this.getEditSession(_1,_2);return _3!=null?_3.$31x:null}
,isc.A.getEditedRecord=function isc_ListGrid_getEditedRecord(_1,_2,_3){if(_1==null)return this.logWarn("getEditedRecord() called with no valuesID");if(!isc.isA.Number(_1)){_1=this.getEditSessionRowNum(_1);_2=this.getEditSessionColNum(_1)}
var _4=this.getCellRecord(_1,_2),_5=_3?this.$300(_1,_2):this.getEditValues(_1,_2);if(_5!=null){delete _5[this.recordRemovedProperty]}
return isc.addProperties({},_4,_5)}
,isc.A.getEditedCell=function isc_ListGrid_getEditedCell(_1,_2){if(_1==null||_2==null)
return this.logWarn("getEditedCell() called with no record / field parameter");var _3=this.getEditValues(_1,_2),_4=isc.isA.Number(_1)?_1:this.getEditSessionRowNum(_1),_5=isc.isA.Number(_2)?_2:this.getFieldNum(_2),_1=this.getCellRecord(_4,_5);var _6=this.getEditorName(_4,this.getField(_5))
var _7;if(_3&&_3[_6]!==_7)return _3[_6];return _1?_1[_6]:null}
,isc.A.rememberSubmittedEditValues=function isc_ListGrid_rememberSubmittedEditValues(_1,_2){var _3=this.getEditSession(_1);if(_3!=null){_3.$33e=isc.addProperties({},_3.$31x)}}
,isc.A.getSubmittedEditValues=function isc_ListGrid_getSubmittedEditValues(_1,_2){var _3=this.getEditSession(_1,_2);return _3!=null?_3.$33e:null}
,isc.A.clearSubmittedEditValues=function isc_ListGrid_clearSubmittedEditValues(_1,_2){var _3=this.getEditSession(_1);if(_3==null)return;var _4=_3.$33e;if(!_4)return;for(var _5 in _2){if(_4[_5]==_2[_5])delete _4[_5]}
if(isc.isA.emptyObject(_4))_3.$33e=null}
,isc.A.createEditValues=function isc_ListGrid_createEditValues(_1){var _2=this.body.getTotalRows();this.setEditValues(_2,_1);return this.getEditValuesID(_2)}
,isc.A.initializeEditValues=function isc_ListGrid_initializeEditValues(_1,_2,_3){if(this.$300(_1,_2)==null){this.setEditValues([_1,_2],{},_3)}}
,isc.A.setEditValues=function isc_ListGrid_setEditValues(_1,_2,_3){var _4;if(isc.isAn.Array(_1)){_4=_1[1];_1=_1[0]}
if(!isc.isA.Number(_1)){this.logWarn("setEditValues() called with bad rowNum: "+this.echo(_1));return}
if(_2==null)_2={};var _5,_6,_7=true;if(!_3){var _8=this.getCellRecord(_1,_4);if(_8==null)_8={};else _7=false;_5=this.getEditValues(_1,_4);if(_5!=null)_7=false;_6=isc.addProperties({},_5);for(var i in _6){_6[i]=_8[i]}
isc.addProperties(_6,_2)}
if(this.logIsInfoEnabled("gridEdit")){_5=_5||this.getEditValues(_1,_4);if(!_5){this.logInfo("establishing new edit session at row: "+_1+(_4!=null?", col:"+_4:"")+(this.logIsDebugEnabled("gridEdit")?" with values: "+this.echo(_2):""),"gridEdit")}}
var _10=this.recordMarkedAsRemoved(_1);if(_10)_2[this.recordRemovedProperty]=true;this.$33f(_1,_4,_2);var _11=!isc.isAn.emptyObject(_6);if(_3||!this.isDrawn()||!this.body){if(_11){if(this.summaryRow&&this.showGridSummary)this.summaryRow.$855()}
return}
if(_11&&_1<this.data.getLength()){this.calculateRecordSummaries([this.data.get(_1)],true)}
var _12=(_7||this.body.isDirty());if(_12){var _13=this.isEditingRecord(_1,_4)&&this.$286!=null
if(_13)this.$33g();this.body.markForRedraw();if(_11&&this.summaryRow&&this.showGridSummary){this.summaryRow.$855()}}else{this.$50r(_1,_4,_6)}}
);isc.evalBoundary;isc.B.push(isc.A.$50r=function isc_ListGrid__displayNewEditValues(_1,_2,_3,_4){if(!_3||isc.isAn.emptyObject(_3)){return}
var _5=this.isEditingRecord(_1,_2)&&this.$286!=null
var _6=false;for(var _7 in _3){var _8=this.getColNum(_7);var _9;if(_5){this.$286.setValue(_7,_3[_7]);var _10=this.$286.getItem(_7);_9=(_8>=0&&_10&&this.canEditCell(_1,_8))}
if(_8==-1)continue;_6=true;if(!_9){this.refreshCell(_1,_8)}else if(_4&&_4[_7]){this.showCellErrors(_1,_8)}}
if(_6&&this.summaryRow&&this.showGridSummary){this.summaryRow.$855();this.refreshGroupSummary(_1)}}
,isc.A.$33f=function isc_ListGrid__storeEditValues(_1,_2,_3,_4){var _5=this.getCellRecord(_1,_2);var _6=this.getEditSession(_1,_2)||this.createEditSession(_1,_2,_5,_4);if(this.dataSource!=null){if(_5=="loading"){_6.$33i=true}else if(_5!=null){var _7=this.getDataSource(),_8=_7.getPrimaryKeyFieldNames();for(var i=0;i<_8.length;i++){_3[_8[i]]=_5[_8[i]]}}}
if(_5==null){_6.$33i=true;var _10=this.completeFields||this.fields||[],_11;for(var i=0;i<_10.length;i++){var _12=_10[i],_13=_12[this.fieldIdProperty];if(_3[_13]===_11){var _14=this.getDefaultEditValue(_13,_12);if(_14!=null){_3[_13]=_14}}}}
for(var i in _6.$31x){delete _6.$31x[i]}
for(var i in _3){_6.$31x[i]=_3[i]}
if(this.$33j==null||_1>=this.$33j){this.$33j=_1}}
,isc.A.getDefaultEditValue=function isc_ListGrid_getDefaultEditValue(_1,_2){var _3=_2.defaultValue;if(_3==null&&this.enumCriteriaAsInitialValues&&_2.type=="enum"&&this.$32d!=null&&this.$32d[_1]!=null)
{_3=this.$32d[_1]}
return _3}
,isc.A.createEditSession=function isc_ListGrid_createEditSession(_1,_2,_3,_4){var _5={};if(_3!=null&&_3!=Array.LOADING)
_5.$33b=this.getPrimaryKeys(_3);_5.$24t=_1;if(this.useCellRecords){_5.$24u=_2;if(this.getCellFacetValues)_5.$507=this.getCellFacetValues(_1,_2)}
_5.$31x={};if(this.$52b==null)this.$52b={};if(_4==null){if(this.$33k==null)this.$33k=0;_4="_"+this.$33k++}
this.$52b[_4]=_5;if(this.$33a==null)this.$33a={};this.$33a[_1]=_4;return _5}
,isc.A.setEditValue=function isc_ListGrid_setEditValue(_1,_2,_3,_4,_5){if(isc.isA.String(_1)){isc.logWarn("Warning: first parameter rowNum is a string, expecting a number");return}
var _6=isc.isA.String(_2)?_2:this.getEditorName(_1,_2,true);_6=this.$840(_6);if(isc.isA.String(_2))_2=this.getFieldNum(_2);var _7=this.$33l(_1,_2,_6,_3,_5);if(!_7)return;var _8=this.getField(_6);if(_8&&this.$425(_8)){var _9=false,_10;var _11=this.getEditForm(),_12=_11?_11.getItem(_6):null;if(_12&&this.getEditRow()==_1&&this.fieldValuesAreEqual(_8,_11.getValue(_6),_3))
{_9=true;_10=_12.mapValueToDisplay(_3)}
if(!_9&&(_8.valueField==null||_8.valueField==_8.name)&&(!_8.optionDataSource||isc.DS.get(_8.optionDataSource)==this.getDataSource()))
{var _13=this.data;if(isc.ResultSet&&isc.isA.ResultSet(_13))_13=_13.localData;if(_13){var _14=_13.find(_6,_3);if(_14){_9=true;_10=_14[_8.displayField]}}}
if(_9){this.setEditValue(_1,_8.displayField,_10,_4,true)}else{if(this.warnOnUnmappedValueFieldChange){this.logWarn("Edit value updated for field:"+_6+". This field has 'displayField' attribute specified as '"+_8.displayField+((_8.optionDataSource==null||isc.DataSource.get(_8.optionDataSource)==this.getDataSource())?"', and no unique optionDataSource, ":"', ")+"so display value is derived from the "+"current record. In order to ensure the display value is updated to "+"reflect the new edit-value for this field, developers can explicitly update the "+"edit value for the display-field on this record. To avoid seeing this method "+"set listGrid.warnOnUnmappedValueFieldChange to false.")}}}
if(_4){if(this.summaryRow&&this.showGridSummary)this.summaryRow.$855();return}
this.setRowEditFieldName(_1,_6);if(!isc.isA.Number(_1)){_2=this.getEditSessionColNum(_1);_1=this.getEditSessionRowNum(_1)}else if(!isc.isA.Number(_2)){_2=this.getFieldNum(_2)}
var _15={};_15[_6]=_3;this.$50r(_1,_2,_15)}
,isc.A.$33l=function isc_ListGrid__storeEditValue(_1,_2,_3,_4,_5){var _6=true,_7=false,_8,_9,_10,_11;_8=this.getEditSession(_1,_2)
if(_8!=null){_9=_8.$31x;_10=isc.Canvas.$70o(_3,_9,this,true)}else{this.logInfo("creating new edit values for row: "+_1,"gridEdit");this.initializeEditValues(_1,_2,true);_8=this.getEditSession(_1,_2);_9=_8.$31x}
if(_10===_11){var _12=this.getCellRecord(_1,_2);_10=_12?isc.Canvas.$70o(_3,_12,this,true):null}else _7=true;var _13=this.getField(_3);if(this.fieldValuesAreEqual(_13,_10,_4))_6=false;if(_4===_11){isc.Canvas.$70m(_3,_9,this,true)}else if(_7||_6){isc.Canvas.$70n(_3,_4,_9,this,true)}
if(_6&&!_5){this.$33m(_1,_2,_4,_10)}
return _6}
,isc.A.setRowEditFieldName=function isc_ListGrid_setRowEditFieldName(_1,_2){var _3=this.getEditSession(_1);if(!_3){var _4=this.getColNum(_2);this.setEditValues([_1,_4],null,true);_3=this.getEditSession(_1)}
if(isc.isA.Number(_2))_2=this.getFieldName(_2);_3.$33n=_2}
,isc.A.getRowEditFieldName=function isc_ListGrid_getRowEditFieldName(_1){var _2=this.getEditSession(_1);return(_2?_2.$33n:null)}
,isc.A.getRowEditColNum=function isc_ListGrid_getRowEditColNum(_1){var _2=this.getRowEditFieldName(_1);return _2?this.getColNum(_2):null}
,isc.A.getEditValue=function isc_ListGrid_getEditValue(_1,_2){var _3=_2
if(isc.isA.String(_2))_2=this.getColNum(_2);if(this.$30a&&(this.getEditRow()==_1)&&(this.getEditCol()==_2))
{this.storeUpdatedEditorValue()}
return this.$30y(_1,_3)}
,isc.A.$30y=function isc_ListGrid__getEditValue(_1,_2){var _3=this.$300(_1,_2);if(_3==null)return;if(!isc.isA.String(_2))_2=this.getEditorName(_1,_2,true);return isc.Canvas.$70o(this.$840(_2),_3,this)}
,isc.A.clearEditValue=function isc_ListGrid_clearEditValue(_1,_2,_3,_4){var _5=(isc.isA.Number(_1)?_1:this.getEditSessionRowNum(_1));var _6=_2;if(isc.isA.Number(_6))_6=this.getEditorName(_5,_6);else _2=this.getColNum(_6);if(this.$30a){if(this.getEditRow()==_5&&this.getEditFieldName()==_6){this.storeUpdatedEditorValue(true)}}
var _7=this.getEditSession(_1,_2);if(_7==null)return;var _8=_7.$31x,_9=false,_10=_7.$33e,_11=_7.$33o,_12=_11&&_11[_6],_5=_7.$24t;if(_8!=null){var _13=this.getRecord(_5);_9=isc.propertyDefined(_8,_6)&&((_5==null||_13==null)||!this.fieldValuesAreEqual(this.getField(_6),_8[_6],_13[_6]));delete _8[_6];if(_10)delete _10[_6];if(_11!=null)delete _11[_6];if(!_4&&isc.isAn.emptyObject(_8)){this.logDebug("no edit values left for row, discarding editSession","gridEdit");return this.$321(_1,_2,_3)}}
var _14=this.getField(_6);if(_14&&this.$425(_14)&&(_14.displayField!=_6)){this.clearEditValue(_1,_14.displayField,_3,_4)}
if(_3||_5==null)return;if(_9){var _15;if(this.$30a&&this.getEditRow()==_5){var _16=_13?_13[_6]:null;this.$286.setValue(_6,_16);_15=_2>=0&&this.canEditCell(_5,_2)&&this.$286.getItem(_6)}
if(_2>=0&&!_15)this.refreshCell(_5,_2)}else if(_12&&_2>=0){if(_12)this.showCellErrors(_5,_2)}}
,isc.A.$321=function isc_ListGrid__clearEditValues(_1,_2,_3){if(_1==null){return}
if(!isc.isA.String(_1))_1=this.getEditValuesID(_1,_2);var _4=this.getEditSession(_1);if(_4==null)return;var _5=_4.$24t;if(_5!=null)delete this.$33a[_5];if(_2==null)_2=_4.$24u;var _6=this.$52b[_1];delete this.$52b[_1];var _7=this.$34e();delete this.$33j;if(_5!=null&&_4.$33i){var _8=this.getEditRow();for(var i=_5+1;i<=_7;i++){var _10=this.getEditSession(i);var _11=_10.$24t;_10.$24t=i-1;this.$33a[i-1]=this.$33a[i];delete this.$33a[i]}
if(_8!=null&&_8>_5)this.$285-=1;if(!_3){if(this.$30a){if(_5==_8)this.hideInlineEditor();else{if(_5<_8)this.$50s(_8-1,null,"Earlier temp edit row removed")}}
if(this.body){var _12=this.frozenBody!=null?this.bodyLayout:this.body;_12.markForRedraw("clear edit values, remove row")}}}else if(_5!=null&&!_3){var _13={},_14=this.data.get(_5);for(var _15 in _6.$31x){_13[_15]=_14?_14[_15]:null}
this.$50r(_5,_2,_13,_6.$33o)}}
,isc.A.newRecordForEditValues=function isc_ListGrid_newRecordForEditValues(_1,_2){var _3=this.getEditSession(_1),_4=_3.$31x;_3.$33b=this.getPrimaryKeys(_2);var _5=this.getDataSource().getPrimaryKeyFieldNames();for(var i=0;i<_5.length;i++){var _7=_5[i];_4[_7]=_2[_7]}
delete _3.$33i}
,isc.A.$31n=function isc_ListGrid__remapEditRows(_1){delete this.$33j;delete this.$33q;if(this.$52b==null)return false;var _2=false;var _3=this.getEditRow(),_4=this.getEditCol(),_5=false;var _6=this.data.getLength(),_7={};var _8=isc.getKeys(this.$52b);for(var i=0;i<_8.length;i++){var _10=_8[i],_11=this.$52b[_10];var _12=_11.$24t;var _13=this.$52p(_11,_6);if(_11.$33b==null)_6++;if(_13==null)continue;var _14=_13[0],_15=_13[1];if(_14!=null&&_14>=0){if(_14!=_12)_2=true;if(_3!=null&&_3==_11.$24t&&(!this.useCellRecords||(_4==_11.$24u)))
{_5=true;if(!_1)this.$50s(_14,_15,"remapEditRows")}
_7[_14]=_10;_11.$24t=_14;if(this.useCellRecords)_11.$24u=_15}else{if(_12!=null&&_12>=0)_2=true;var _16=this.getOriginalData();var _17=!this.dataSource||this.shouldSaveLocally()||(isc.ResultSet&&isc.isA.ResultSet(_16)&&_16.allRowsCached());if(_17&&!isc.isA.Tree(this.data)&&(!this.isGrouped||!this.data.find(_11.$33b)))
{this.logWarn("Record:"+this.echo(_11.$33b)+", is no longer present in this List.<br>Clearing edit values for this record.");delete this.$52b[this.getEditValuesID(_10)]}else{this.logWarn("Record:"+this.echo(_11.$33b)+(_17?", hidden in grouped tree data. ":", lost from local cache in paged result set. ")+"Pending edits for this record will be maintained.");delete _11.$24t;delete _11.$24u;this.$33q=true}}}
this.$33a=_7;if(_3!=null&&!_5&&!_1){this.hideInlineEditor(true)}
return _2}
,isc.A.$74c=function isc_ListGrid__remapEmbeddedComponents(){if(!this.body||this.body.$29a==null)return;var _1=this.body.$29a,_2=[];for(var i=0;i<_1.length;i++){var _4=_1[i],_5=_4.embeddedRecord,_6=this.getPrimaryKeys(_5),_7=this.findRowNum(_6),_8;if(_7>=0){_8=this.data.get(_7);if(!_8.$29a)_8.$29a=[];if(!_8.$29a.contains(_4)){_4.$289=_7;_4.embeddedRecord=_8;_8.$29a.add(_4);if(_4.isExpansionComponent){_8.expanded=true
_8.hasExpansionComponent=true;if(!this.canExpandMultipleRecords)this.$74b=_8}}}else{if(this.$74d==false||(this.showRecordComponents&&this.recordComponentPoolingMode=="data")){_2.add(_4)}}}
if(_2.length>0){for(var i=0;i<_2.length;i++){var _9=_2[i];if(this.canExpandRecords){this.$74a--;if(this.$74b&&this.$74b==_9.embeddedRecord)
delete this.$74b}
this.body.$29a.remove(_9);if(this.shouldDestroyOnUnembed(_9,this.$81l)){_9.markForDestroy()}else{_9.deparent()}}}}
,isc.A.$75n=function isc_ListGrid__remapEmbeddedComponentColumns(_1){if(_1.$29a==null)return;var _2=_1.$29a,_3={},_4=false;for(var i=0;i<_1.fields.length;i++){_3[_1.fields[i].name]=i}
var _6=[];for(var i=0;i<_2.length;i++){var _7=_2[i].$81k;if(_7==null){continue}
var _8=_3[_7];if(_8==null){_6[_6.length]=_2[i]}else{if(_2[i].$57n!=_8){_4=true;_2[i].$57n=_8}}}
if(_6.length>0){for(var i=0;i<_6.length;i++){var _9=_6[i];_1.removeEmbeddedComponent(_9.embeddedRecord,_9);if(this.shouldDestroyOnUnembed(_9,this.$81p)){_9.markForDestroy()}}}
if(_4){_1.$29d()}}
,isc.A.$52p=function isc_ListGrid__calculateEditCell(_1,_2){var _3,_4,_5=_1.$33b;if(_5==null){_3=_2}else{var _6=_1.$31x,_7=false,_8;if(_1.$33o!=null&&!isc.isA.emptyObject(_1._validationErrors_))
{_7=true}else{for(var _9 in _6){if(_6[_9]!=_5[_9]||_5[_9]===_8){_7=true;break}}}
if(!_7&&_1.$24t!=null&&!this.isEditingRecord(_1.$24t,_1.$24u))
{this.logInfo("dropping empty editSession","gridEdit");delete this.$52b[this.getEditValuesID(_1)];return null}
_3=this.findRowNum(_5,_1);_4=this.findColNum(_5,_1)}
return[_3,_4]}
,isc.A.$wo=function isc_ListGrid__filter(_1,_2,_3,_4,_5){if(!_5&&this.confirmDiscardEdits&&this.dataSource!=null){var _6=this.getOriginalData();if(this.hasChanges()&&!(isc.ResultSet&&isc.isA.ResultSet(_6)&&_6.compareCriteria(_2,_6.getCriteria())==0))
{this.showLostEditsConfirmation({target:this,methodName:"$33r"},{target:this,methodName:"$33s"});this.$33t={type:_1,criteria:_2,callback:_3,requestProperties:_4}
return}}
var _7=_2
if(isc.isA.Class(_7)){_7=isc.DynamicForm.getFilterCriteria(_7)}
this.setFilterValues(_7);return this.Super("$wo",[_1,_2,_3,_4],arguments)}
,isc.A.$33r=function isc_ListGrid__continueFilter(){var _1=this.$33t,_2=_1.type,_3=_1.criteria,_4=_1.callback,_5=_1.requestProperties;delete this.$33t;this.$wo(_2,_3,_4,_5,true)}
,isc.A.$33s=function isc_ListGrid__cancelFilter(){delete this.$33t}
,isc.A.showLostEditsConfirmation=function isc_ListGrid_showLostEditsConfirmation(_1,_2){this.$33u=_1;this.$33v=_2;isc.confirm(this.confirmDiscardEditsMessage,"if(window[this.targetGridID])window[this.targetGridID].lostEditsCallback(value, this);",{targetGridID:this.getID(),buttons:[isc.Dialog.OK,{title:this.discardEditsSaveButtonTitle,width:75,click:"this.hide();this.topElement.returnValue('save');"},isc.Dialog.CANCEL]})}
,isc.A.lostEditsCallback=function isc_ListGrid_lostEditsCallback(_1,_2){var _3=this.$33u,_4=this.$33v;delete this.$33u;delete this.$33v;if(_1==null){this.fireCallback(_4)}else if(_1==true){this.discardAllEdits();this.fireCallback(_3)}else if(_1=="save"){this.saveAllEdits(null,_3)}
_2.targetGrid=null}
,isc.A.findRowNum=function isc_ListGrid_findRowNum(_1){return this.data.indexOf(_1)}
,isc.A.findColNum=function isc_ListGrid_findColNum(_1){return-1}
,isc.A.$50s=function isc_ListGrid__moveEditor(_1,_2,_3){if(!this.$30a||_1==this.$285){return}
var _4=this.$285;this.logInfo(_3+": editSession: "+this.getEditValuesID(_1)+" with values: "+this.echo(this.$300(_1,_2))+" was being edited at row: "+_4+", will now edit at row: "+_1,"gridEdit");this.$285=_1;var _5=this.$286,_6=_5?_5.getItems():null;if(_6){for(var i=0;i<_6.length;i++){_6[i].rowNum=_1}}}
,isc.A.rowEditNotComplete=function isc_ListGrid_rowEditNotComplete(_1){return(this.$33a!=null&&this.$33a[_1]!=null)}
,isc.A.$33m=function isc_ListGrid__editorChange(_1,_2,_3,_4){var _5=this.getCellRecord(_1,_2);if(this.editorChange!=null)this.editorChange(_5,_3,_4,_1,_2);var _6=this.getFieldName(_2);if(this.editValueChanged!=null)
this.editValueChanged(_1,_6,_3,_4);if(this.$30a&&this.isEditingRecord(_1,_2)&&this.fields!=null){_5=isc.addProperties({},this.$300(_1,_2),_5);var _7=!this.editByCell?this.fields:[this.getEditField()];for(var i=0;i<_7.length;i++){var _9=_7[i],_10=this.$286.getItem(_9[this.fieldIdProperty]);if(_10==null)continue;var _11=this.getEditorValueMap(_9,this.getEditedRecord(_1,_2,true));if(_10.valueMap!=_11){var _12=true;if(isc.isAn.Array(_11)&&isc.isAn.Array(_10.valueMap)){_12=!_11.equals(_10.valueMap)}else if(isc.isAn.Object(_11)&&isc.isAn.Object(_10.valueMap)){if(isc.getKeys(_11).equals(isc.getKeys(_10.valueMap))){_12=false;for(var _13 in _11){if(_11[_13]!=_10.valueMap[_13]){_12=true;break}}}}
if(_12)_10.setValueMap(_11)}}}}
,isc.A.getEditRow=function isc_ListGrid_getEditRow(){return this.$285}
,isc.A.getEditCol=function isc_ListGrid_getEditCol(){return this.$30u}
,isc.A.getEditField=function isc_ListGrid_getEditField(){return this.getField(this.getEditCol())}
,isc.A.getEditFieldName=function isc_ListGrid_getEditFieldName(){return this.getFieldName(this.getEditCol())}
,isc.A.getEditRecord=function isc_ListGrid_getEditRecord(){return this.getCellRecord(this.getEditRow(),this.getEditCol())}
,isc.A.cancelEditing=function isc_ListGrid_cancelEditing(_1){if(!this.$30a)return;this.discardEdits(this.getEditRow(),this.getEditCol(),false,_1)}
,isc.A.cellEditEnd=function isc_ListGrid_cellEditEnd(_1,_2){if(this.$51k&&_1!=isc.ListGrid.TAB_KEYPRESS&&_1!=isc.ListGrid.SHIFT_TAB_KEYPRESS&&_1!=isc.ListGrid.UP_ARROW_KEYPRESS&&_1!=isc.ListGrid.DOWN_ARROW_KEYPRESS&&_1!=isc.ListGrid.LEFT_ARROW_KEYPRESS&&_1!=isc.ListGrid.RIGHT_ARROW_KEYPRESS)delete this.$51k
var _3=this.getEditRow(),_4=this.getEditCol();if(_3==null&&_4==null)return;if(arguments.length>=2){this.setEditValue(_3,_4,_2)}
var _5=this.getFieldName(_4),_6=this.shouldCancelEdit(_3,_4,_1);if(_6)return this.cancelEditing(_1);var _7=this.getNextEditCell(_3,_4,_1);this.clearNewEditCell();if(_7==null){this.logInfo("cellEditEnd: ending editing, completion event: "+_1,"gridEdit");this.$33x(_1);return}
if(Array.isLoading(this.getCellRecord(_7[0],_7[1]))){return false}
this.$32f(_1,_3,_4,_7[0],_7[1])}
,isc.A.getUpdatedEditorValue=function isc_ListGrid_getUpdatedEditorValue(){if(!this.$30a)return;var _1=this.$286.getItem(this.getEditFieldName()),_2=this.getEditField(),_3;if(_1){if(_1.$10v())_1.updateValue();_3=_1.getValue();return this.$33y(_3,_2,this.getEditRow(),this.getEditCol())}else{return this.getEditDisplayValue(this.getEditRow(),this.getEditCol())}}
,isc.A.storeUpdatedEditorValue=function isc_ListGrid_storeUpdatedEditorValue(_1,_2){if(!this.isDrawn()||!this.$30a)return;var _3=this.getEditRow();if(_2==null)_2=this.getEditCol();var _4=this.getField(_2),_5=this.$286,_6=this.getEditFormItem(_2),_7;if(_6){if(_6.$10v())_6.updateValue();_7=this.$33y(_6.getValue(),_4,_3,_2);var _8;if(_7===_8)_7=null;var _9=this.$30y(_3,_2),_10;if(_9===_10||!this.fieldValuesAreEqual(_4,_9,_7)){this.setEditValue(_3,_2,_7,true,_1)}}}
,isc.A.$32i=function isc_ListGrid__handleEditorExit(_1,_2,_3,_4){var _5=this.getCellRecord(_2,_3),_6=this.getField(_3),_7=this.getEditorName(_2,_6),_8=true;var _9=this.$286.getItem(_7);if(isc.isA.PopUpTextAreaItem(_9))_9.hidePopUp();if(_6&&_6.editorExit!=null){isc.Func.replaceWithMethod(_6,"editorExit","editCompletionEvent,record,newValue,rowNum,colNum,grid");_8=(_6.editorExit(_1,_5,_4,_2,_3,this)
!=false)}
if(_8&&this.editorExit!=null)
_8=this.editorExit(_1,_5,_4,_2,_3)!=false;return _8}
,isc.A.$32j=function isc_ListGrid__handleRowEditorExit(_1,_2,_3){var _4=this.getRecord(_2);if(this.rowEditorExit!=null)
return(this.rowEditorExit(_1,_4,_3,_2)!=false);return true}
,isc.A.$30x=function isc_ListGrid__handleEditorEnter(_1,_2,_3,_4){var _5=this.getCellRecord(_2,_3),_6=this.getFieldName(_3),_7=this.getField(_3),_8=true;if(isc.isA.PopUpTextAreaItem(_1)&&_1.popUpOnEnter){_1.showPopUp(true)}
if(_7&&_7.editorEnter!=null){isc.Func.replaceWithMethod(_7,"editorEnter","record,value,rowNum,colNum,grid");_8=_7.editorEnter(_5,_4,_2,_3,this)!=false}
if(_8&&this.editorEnter!=null)
_8=this.editorEnter(_5,_4,_2,_3)!=false;return _8}
,isc.A.$30z=function isc_ListGrid__handleRowEditorEnter(_1,_2,_3){var _4=this.getRecord(_2);if(this.rowEditorEnter!=null)
return this.rowEditorEnter(_4,_3,_2)!=false;return true}
,isc.A.$32k=function isc_ListGrid__validationEnabled(){if(!isc.DS)return false;var _1=isc.DS.get(this.dataSource);return!this.neverValidate&&!(_1&&_1.useLocalValidators!=null&&_1.useLocalValidators==false)}
,isc.A.shouldSaveOnCellExit=function isc_ListGrid_shouldSaveOnCellExit(_1,_2){if(this.getCellRecord(_1,_2)==null)return false;return(this.saveByCell!=null?this.saveByCell:this.editByCell)}
,isc.A.shouldSaveOnRowExit=function isc_ListGrid_shouldSaveOnRowExit(_1,_2,_3){return true}
,isc.A.shouldValidateByCell=function isc_ListGrid_shouldValidateByCell(_1,_2,_3){var _4=this.getField(_2);if(_4&&_4.validateByCell!=null)return _4.validateByCell;return(this.validateByCell!=null?this.validateByCell:this.editByCell)}
,isc.A.shouldValidateByRow=function isc_ListGrid_shouldValidateByRow(_1,_2,_3){return this.autoValidate}
,isc.A.shouldCancelEdit=function isc_ListGrid_shouldCancelEdit(_1,_2,_3){if(_3==isc.ListGrid.ESCAPE_KEYPRESS){var _4=this.getField(_2),_5=_4.escapeKeyEditAction||this.escapeKeyEditAction;if(_5=="cancel")return true}
return false}
,isc.A.$33w=function isc_ListGrid__killEdit(_1,_2,_3){if(this.$32p!=_1)return;if(this.confirmCancelEditing){if(_3==null){var _4=this.getID()+".$33w('"+_1+"','"+_2+"',value);";isc.ask(this.cancelEditingConfirmationMessage,_4);var _5=isc.Dialog.Warn.toolbar.getButton(0);if(_5.isDrawn())_5.focus()
else isc.Timer.setTimeout({target:_5,methodName:"focus"},0);return}else if(_3==false)return}
var _6=this.getEditRow(),_7=this.getEditCol(),_8=this.getUpdatedEditorValue(),_9=this.getEditValues(_6,_7);if(!this.$32i(_2,_6,_7,_8)||!this.$32j(_2,_6,_9))
{return}
var _10,_11=[],_12=this.$285,_13=this.getEditValues(_12,_7),_14=this.body?this.body.getDrawArea():[null,null,1,0],_15=_14[2],_16=_14[3],_17;if(_13!=null){if(this.isNewEditRecord(_12,_7)){if(this.isDrawn())this.body.markForRedraw("clearing extra edit row");_10=true}else{for(var i=_15;i<=_16;i++){if(_13[this.getFieldName(i)]!==_17&&((this.editByCell&&this.$30u!=i)||!this.canEditCell(_12,i)))
{_11.add(i)}}}
this.$321(this.$285,_7,true);if(this.isGrouped&&this.$75i(_12)){_10=true}}
if(this.body){this.hideInlineEditor(true);if(this.isDrawn()){if(!_10){for(var i=0;i<_11.length;i++){this.refreshCell(_12,_11[i])}}}}}
,isc.A.isNewEditRecord=function isc_ListGrid_isNewEditRecord(_1){var _2=this.getEditSession(_1);return _2&&_2.$33i}
,isc.A.endEditing=function isc_ListGrid_endEditing(){if(this.getEditRow()!=null)this.$33x(isc.ListGrid.PROGRAMMATIC)}
,isc.A.$33x=function isc_ListGrid__saveAndHideEditor(_1){if(this.$686()){this.saveEdits(_1);return}
var _2=this.getEditRow(),_3=this.getEditCol(),_4=this.getEditValue(_2,_3),_5=this.getEditValues(_2,_3);if(!this.$32i(_1,_2,_3,_4)||!this.$32j(_1,_2,_5))return;var _6=(this.autoSaveEdits&&this.shouldWaitForSave()),_7=(this.autoSaveEdits&&this.stopOnErrors),_8;if(_6)_8="if((success||!this.stopOnErrors)&&"+"(this.$32p=="+this.$32p+"))"+"this.hideInlineEditor(true);";var _9=_1==isc.ListGrid.ESCAPE_KEYPRESS,_10=this.autoSaveEdits&&!_9;if(_10){this.saveEdits(_1,_8)}else{var _11=this.getFieldName(_3);var _12=!_9&&this.$32k()&&(this.shouldValidateByCell(_2,_3,_1)||this.shouldValidateByRow(_2,_3,_1));if(_12){var _13;if(this.useCellRecords){_13=!this.validateCell(_2,_3)}else{_13=!this.validateRow(_2)}
if(this.stopOnErrors&&_13)return false}
if(!this.rowHasChanges(_2,false)){this.logInfo("editor hiding at "+[_2,_3]+", no actual changes, dropping editSession","gridEdit");this.$321(_2,_3)}}
if(!_6)this.hideInlineEditor(true);if(this.isGrouped&&(!_10||!this.shouldSaveLocally())){this.$75i(_2)}}
,isc.A.saveAndEditNewCell=function isc_ListGrid_saveAndEditNewCell(_1,_2,_3){if(_3==null)_3=isc.ListGrid.PROGRAMMATIC;this.$32l(_1,_2,_3)}
,isc.A.$32l=function isc_ListGrid__saveAndStartEditing(_1,_2,_3){var _4=this.shouldWaitForSave(),_5;if(_4){_5="if((success||!this.stopOnErrors)&&"+"(this.$32p=="+this.$32p+"))"+"this.$31u("+_1+","+_2+");"}
this.saveEdits(_3,_5);if(!_4){this.$31u(_1,_2)}}
,isc.A.$33z=function isc_ListGrid__saveAndEditNextCell(_1,_2){var _3=this.getEditRow(),_4=this.getEditCol(),_5=isc.ListGrid.PROGRAMMATIC,_6=this.findNextEditCell(_3,_4,_1,_2,false);this.$32l(_6[0],_6[1],_5)}
,isc.A.saveAndEditNextCell=function isc_ListGrid_saveAndEditNextCell(){this.$33z(1,true)}
,isc.A.saveAndEditPreviousCell=function isc_ListGrid_saveAndEditPreviousCell(){this.$33z(-1,true)}
,isc.A.saveAndEditNextRow=function isc_ListGrid_saveAndEditNextRow(){this.$33z(1,false)}
,isc.A.saveAndEditPreviousRow=function isc_ListGrid_saveAndEditPreviousRow(){this.$33z(-1,false)}
,isc.A.getNextEditCell=function isc_ListGrid_getNextEditCell(_1,_2,_3){switch(_3){case isc.ListGrid.CLICK_OUTSIDE:case isc.ListGrid.ESCAPE_KEYPRESS:return null;case isc.ListGrid.ENTER_KEYPRESS:var _4=this.getField(_2),_5=_4.enterKeyEditAction||this.enterKeyEditAction;if(_5=="done")return null;else if(_5=="nextCell")
return this.findNextEditCell(_1,_2,1,true,false);else if(_5=="nextRow")
return this.findNextEditCell(_1,_2,1,false,false);else if(_5=="nextRowStart"){var _6=this.listEndEditAction||"done";if(_1+1<this.getTotalRows()||_6=="next"){return this.findNextEditCell(_1+1,0,1,true,true)}else{if(_6=="stop")return[_1,_2];return null}}
case isc.ListGrid.EDIT_FIELD_CHANGE:case isc.ListGrid.PROGRAMMATIC:return this.getNewEditCell();case isc.ListGrid.UP_ARROW_KEYPRESS:return this.findNextEditCell(_1,_2,-1,false,false);case isc.ListGrid.DOWN_ARROW_KEYPRESS:return this.findNextEditCell(_1,_2,1,false,false);case isc.ListGrid.TAB_KEYPRESS:case isc.ListGrid.RIGHT_ARROW_KEYPRESS:var _4=this.getField(_2);if(_4.nextTabColNum!=null){return this.findNextEditCell(_1+1,_4.nextTabColNum,1,true,true)}else{return this.findNextEditCell(_1,_2,1,true,false)}
case isc.ListGrid.SHIFT_TAB_KEYPRESS:case isc.ListGrid.LEFT_ARROW_KEYPRESS:var _4=this.getField(_2)
if(_4.previousTabColNum!=null){return this.findNextEditCell(_1-1,_4.previousTabColNum,-1,true,true)}else
return this.findNextEditCell(_1,_2,-1,true,false)}
this.logWarn("getNextEditCell(): Passed unrecognized editCompletionEvent type:"+_3+", returning null");return null}
,isc.A.findNextEditCell=function isc_ListGrid_findNextEditCell(_1,_2,_3,_4,_5,_6,_7,_8){var _9=_1,_10=_2,_11,_12=this.getFields().length;if(_12==0)return;_11=(_7?_9:(this.listEndEditAction=="next"||_6)?this.getTotalRows():this.getTotalRows()-1);if(_9>this.getTotalRows())_11=_9;if((_5!=false||_3==0)&&_9<=_11&&_9>=0&&_10<_12&&_10>=0&&this.canEditCell(_9,_10)&&(_8||this.$60w(_9,_10)))
{return[_9,_10]}
if(_3==0)return null;var _13=this.canEditCell(_9,_10)&&this.$60w(_9,_10),_14=_7?_9:0;if(_4){var _15=this.rowEndEditAction||"next";while(_9<(_11+1)&&_9>=_14){if(isc.ResultSet&&isc.isA.ResultSet(this.data)){if(_9<this.data.getLength()&&!this.data.rowIsLoaded(_9))return null}
_10+=_3;if(_15=="same"&&_2==_10){return _13?[_1,_2]:null}
if(_10<0||_10>=_12){if(_15=="done"||isc.isA.RecordEditor(this))return null;if(_15=="stop")return(_13?[_1,_2]:null)
else{_10=(_3>0?-1:_12);if(_15=="next")_9+=_3}}else if(this.canEditCell(_9,_10)&&(_8||this.$60w(_9,_10)))
{return[_9,_10]}}
if(this.listEndEditAction=="stop"||(this.listEndEditAction=="next"&&_9<_14))
{if(_13)return[_1,_2];else return null}else{return null}}else{_9+=_3
while(_9>=_14&&_9<=_11){if(this.canEditCell(_9,_10)&&(_8||this.$60w(_9,_10))){return[_9,_10]}
_9+=_3}
if(this.listEndEditAction=="stop"||(this.listEndEditAction=="next"&&_9<_14))
{return _13?[_1,_2]:null}
return null}}
,isc.A.$60w=function isc_ListGrid__canFocusInEditor(_1,_2){var _3=this.getFieldName(_2),_4=this.$286;if(_4==null){this.makeEditForm(_1,_2);_4=this.$286}
var _5=_4?_4.getItem(_3):null;if(_5)return _5.$kk();var _6=this.getField(_3);if(_6.canFocus!=null)return _6.canFocus;var _7=this.getEditorType(_6,this.getEditedRecord(_1));_7=isc.FormItemFactory.getItemClassName(_6,_7,null,true);var _8=_7=isc.FormItemFactory.getItemClass(_7);return _8?_8.getPrototype().$kk():false}
,isc.A.setNewEditCell=function isc_ListGrid_setNewEditCell(_1,_2){this.$330=[_1,_2]}
,isc.A.getNewEditCell=function isc_ListGrid_getNewEditCell(){if(this.canEditCell(this.$330[0],this.$330[1]))return this.$330;return null}
,isc.A.clearNewEditCell=function isc_ListGrid_clearNewEditCell(){this.$330=null}
,isc.A.discardAllEdits=function isc_ListGrid_discardAllEdits(_1,_2){if(_1==null)_1=this.getAllEditRows(true);else{for(var i=0;i<_1.length;i++){if(isc.isA.Array(_1[i]))_1[i]=this.getEditValuesID(_1[i][0],_1[i][1]);if(isc.isA.Number(_1[i]))_1[i]=this.getEditValuesID(_1[i])}}
if(_1==null)return;for(var i=0;i<_1.length;i++)this.discardEdits(_1[i],null,_2)}
,isc.A.discardEdits=function isc_ListGrid_discardEdits(_1,_2,_3,_4){if(_1==null)return;var _5;if(isc.isA.Number(_1)){_5=this.getEditValuesID(_1,_2)}else{_5=_1;_1=this.getEditSessionRowNum(_5,_2)}
var _6=this.recordMarkedAsRemoved(_1);if(!_3&&this.$686()&&(!(this.getTotalRows()==1&&_1==0)))
{this.logInfo("Refusing to hide editor on 'discardEdits' [editCompletionEvent:"+_4+"] due to alwaysShowEditors settings","inactiveEditorHTML");_3=true}
if(!_3&&this.$30a&&this.getEditRow()==_1){if(_4==null)_4=isc.ListGrid.PROGRAMMATIC;return this.$33w(this.$32p,_4)}else{if(this.$30a&&this.getEditRow()==_1){this.storeUpdatedEditorValue(true)}
this.$321(_5,_2)}
if(_6)this.refreshRow(_1)}
,isc.A.saveEdits=function isc_ListGrid_saveEdits(_1,_2,_3,_4,_5,_6){if(_1==null)_1=isc.ListGrid.PROGRAMMATIC;if(_3==null){_3=this.getEditRow()
_4=this.getEditCol()}else if(_4==null){_4=(_3==this.getEditRow()?this.getEditCol():this.getRowEditColNum(_3))}
if(_3==null)return false;if(this.recordMarkedAsRemoved(_3)){if(_5){return true}
var _7=this.isNewEditRecord(_3,_4);if(_7){this.discardEdits(_3);this.$332(_2,_3,_4,_1,true)}else{var _8=this;var _9=function(_18,_19,_20){_8.fireCallback(_2,"rowNum,colNum,editCompletionEvent,success",[_3,_4,_1,true])};this.removeData(this.getCellRecord(_3,_4),_9);return}}
var _10=this.getEditValues(_3,_4),_11=this.getCellRecord(_3,_4),_12=this.getEditValuesID(_3,_4);var _13;if(_11!=null&&!Array.isLoading(_11)){if(isc.isA.Tree(this.data)){_13=this.data.getCleanNodeData(_11,false)}else{_13=isc.addProperties({},_11)}
if(_13&&this.selection)delete _13[this.selection.selectionProperty]}
var _14={rowNum:_3,colNum:_4,oldValues:_13,editValuesID:_12,values:_10,editCompletionEvent:_1,newRecord:(_3>this.data.getLength())};if(this.logIsDebugEnabled("gridEdit")){this.logDebug("change detection: newValues: "+this.echo(_10)+", oldValues: "+this.echo(_13),"gridEdit")}
var _15=this.recordHasChanges(_3,_4,false);if(!_6&&this.$32k()){this.$81c=(_5||_15);var _16;if(this.useCellRecords){_16=!this.validateCell(_3,_4)}else{_16=!this.validateRow(_3)}
if(_16){if(!_5)this.$331(_14,_2);return false}}
if(_5)return true;if(!_15){this.logInfo("saveEdits: no actual change, not saving","gridEdit");this.$321(_12,_4);this.$332(_2,_3,_4,_1,true);return}
_10=this.$300(_3,_4);this.logInfo("Saving newValues '"+isc.echoAll(_10)+"'","gridEdit");this.rememberSubmittedEditValues(_3,_4);_10=isc.addProperties({},_10);var _17=this.saveEditedValues(_3,_4,_10,_13,_12,_1,_2);if(_17===false){this.$331(_14,_2);return false}
return true}
,isc.A.rowHasChanges=function isc_ListGrid_rowHasChanges(_1,_2){return this.recordHasChanges(_1,null,_2)}
,isc.A.recordHasChanges=function isc_ListGrid_recordHasChanges(_1,_2,_3){if(_3==null)_3=true;var _4=false,_5=(_3?this.getEditValues(_1,_2):this.$300(_1,_2)),_6=this.getCellRecord(_1,_2);if(!_6)return true;var _7=isc.addProperties({},_6,this.getSubmittedEditValues(_1,_2));for(var _8 in _5){if(_8==this.removeRecordProperty)continue;var _9=_7[_8],_10=_5[_8];if(!this.fieldValuesAreEqual(this.getField(_8),_9,_10)){_4=true;break}}
return _4}
,isc.A.hasChanges=function isc_ListGrid_hasChanges(_1){var _2=this.getAllEditRows();if(_2!=null){for(var i=0;i<_2.length;i++){if(this.recordMarkedAsRemoved(_2[i]))return true;if(this.rowHasChanges(_2[i],_1))return true}}
return false}
,isc.A.cellHasChanges=function isc_ListGrid_cellHasChanges(_1,_2,_3){if(_1==null||_2==null)return false;var _4=this.getField(_2),_5=_4?_4.dataPath:null,_6=this.getEditorName(_1,_2),_7=(_3?this.getEditValues(_1,_2):this.$300(_1,_2));if(!_7)return false;var _8=this.getCellRecord(_1,_2);if(!_8)return true;var _9;if(_5){_9=isc.Canvas.$70o(this.$840(_5),_7)}else{_9=_7[_6]}
var _10;if(_9===_10)return false;var _11=this.getSubmittedEditValues(_1,_2),_12;if(_5){if(_11){_12=isc.Canvas.$70o(this.$840(_5),_11)}
if(_12==null){_12=isc.Canvas.$70o(this.$840(_5),_8)}
if(_12==null){_12=isc.Canvas.$70o(this.$840(_5),_11)}}else{_12=_11!=null?this.$du(_11[_6],_8[_6]):_8[_6]}
return!this.fieldValuesAreEqual(_4,_12,_9)}
,isc.A.saveAllEdits=function isc_ListGrid_saveAllEdits(_1,_2){this.$333=this.$333||{};this.$90a=this.$90a||{};this.$334=this.$334||{};if(_1==null)_1=this.getAllEditCells();if(_1==null)return false;if(!isc.isAn.Array(_1))_1=[_1];if(this.$32k()){for(var i=0;i<_1.length;i++){var _4=isc.isAn.Array(_1[i])?_1[i][0]:_1[i],_5=isc.isAn.Array(_1[i])?_1[i][1]:null;var _6=this.recordMarkedAsRemoved(_4)||this.saveEdits(null,null,_4,_5,true);if(!_6){if(this.stopOnErrors)return false;else{_1[i]=null;_2=null}}}}
var _7=(this.dataSource!=null&&!this.shouldSaveLocally()),_8=false;if(_7)_8=!isc.RPCManager.startQueue();var _9=this.$31e++,_10=this.$333[_9]=[],_11=this.$90a[_9]=[];this.$334[_9]=_2;var _12=false;var _13=["this.$335('",,"',rowNum,"+_9+",colNum,editCompletionEvent,success)"];for(var i=0;i<_1.length;i++){if(_1[i]==null)continue;var _4=_1[i],_5;if(isc.isAn.Array(_4)){_5=_4[1];_4=_4[0]}
var _14=this.recordMarkedAsRemoved(_4);if(!this.recordHasChanges(_4,_5)&&!_14){continue}
_12=true;var _15=this.getEditValuesID(_4);_13[1]=_15;if(_14){_11[_11.length]=_15}else{_10[_10.length]=_15}
this.saveEdits(null,_13.join(""),_4,_5,false,true)}
if(_8){isc.RPCManager.sendQueue(null,null,null,true)}
return _12}
,isc.A.$335=function isc_ListGrid__saveAllEditsRowCallback(_1,_2,_3,_4,_5,_6){if(!_6){delete this.$334[_3];delete this.$333[_3];delete this.$90a[_3];return}
var _7=this.$333[_3],_8=this.$90a[_3];if(_7==null&&_8==null)return;var _9,_10,_11;for(_9=0;_9<_7.length;_9++){var _12=_7[_9];if(_12==_1){_10=true;break}}
if(!_10){for(_9=0;_9<_8.length;_9++){var _12=_8[_9];if(_12==_1){_10=true;break}}
if(_10)_11=true}
if(_11){_8.removeAt(_9)}else{_7.removeAt(_9)}
if(_7.length==0&&_8.length==0){this.$332(this.$334[_3],_2,_4,_5);delete this.$334[_3];delete this.$333[_3];delete this.$90a[_3]}}
,isc.A.$33y=function isc_ListGrid__parseEditorValue(_1,_2,_3,_4,_5){var _6;if(_5===_6)_5=this.getCellRecord(_3,_4);if(_2&&_2.parseEditorValue!=null){isc.Func.replaceWithMethod(_2,"parseEditorValue","value,record,rowNum,colNum,grid");_1=_2.parseEditorValue(_1,_5,_3,_4,this)}else if(this.parseEditorValue!=null){_1=this.parseEditorValue(_1,_5,_3,_4)}
return _1}
,isc.A.saveEditedValues=function isc_ListGrid_saveEditedValues(_1,_2,_3,_4,_5,_6,_7){var _8={editValuesID:_5,rowNum:_1,colNum:_2,values:_3,oldValues:_4,editCompletionEvent:_6};if(this.shouldSaveLocally()){if(this.useRemoteValidators==true){var _9=false,_10=this.getDataSource(),_11=_10?_10.getFieldNames():null;if(_11!=null){for(var i=0;i<_11.length;i++){var _13=_10.getField(_11[i]),_14=_13.validators;if(_14==null)continue;if(!isc.isAn.Array(_14))_14=[_14];for(var _15=0;_15<_14.length;_15++){if(isc.Validator.isServerValidator(_14[_15])){_9=true;break}}
if(_9)break}}
if(_9){var _16={validationMode:"partial",prompt:isc.RPCManager.validateDataPrompt,showPrompt:this.shouldWaitForSave(),clientContext:{editInfo:_8,saveCallback:_7},componentID:this.ID};_10.validateData(_8.values,{target:this,methodName:"remoteValidationForLocalSaveComplete"},_16);if(this.$31o==null)this.$31o={};this.$31o[_8.editValuesID]=true;return}}
return this.$336(_8,_7)}
if(isc.Offline&&isc.Offline.isOffline()&&!this.dataSource.clientOnly){isc.warn(this.offlineSaveMessage);return false}
var _17=this.getID()+".$337(dsResponse, dsRequest)",_18={operation:(_4==null?this.addOperation:this.updateOperation)||this.saveOperation,application:this.application,willHandleError:true,showPrompt:this.shouldWaitForSave(),oldValues:_4,$52s:this.getRecord(_1,_2),clientContext:{saveCallback:_7,newValues:_3,editInfo:_8},componentId:this.ID};if(this.saveRequestProperties){isc.addProperties(_18,this.saveRequestProperties)}
this.$338(_3,_4);if(this.$31o==null)this.$31o={};this.$31o[_8.editValuesID]=true;var _10=isc.DS.get(this.dataSource);if(_4==null){var _19=isc.addProperties({},_4,_3);_10.addData(_19,_17,_18)}else{var _20=isc.DS.get(this.dataSource).filterPrimaryKeyFields(_4),_21=isc.addProperties({},_20,_3);_10.updateData(_21,_17,_18)}}
);isc.evalBoundary;isc.B.push(isc.A.$336=function isc_ListGrid__saveLocally(_1,_2){var _3=_1.rowNum,_4=_1.colNum,_5=_1.oldValues,_6=_1.values;var _7=this.getCellRecord(_3,_4),_8=(_7==null);if(_8){var _9=this.getData();var _10=this.getEditSession(_3);_10.$33b=this.getPrimaryKeys(_6);delete _10.$33i;_9.add(_6);_9.dataChanged()}else{var _11,_12;var _13={};for(var i=0;i<this.completeFields.length;i++){var _15=this.completeFields[i],_16=this.getEditorName(_3,_15,true);_11=isc.Canvas.$70o(this.$840(_16),_6,this,true);if(_11!==_12){var _17=this.fields.indexOf(_15);if(_17!=-1){this.setRawCellValue(_7,_3,_17,_11)}else{if(_15.dataPath){isc.Canvas.$70n(this.$840(_15.dataPath),_11,_7,this,true)}else{_7[_15[this.fieldIdProperty]]=_11}}}
var _18=_16;if(_16.indexOf("/")!=null){_18=_16.substring(0,_16.indexOf("/"))}
_13[_18]=true}
for(var _19 in _6){if(_13[_19]==true)continue;_7[_19]=_6[_19]}}
this.$338(_6,_5);this.$339(_1,_2);if(this.valuesManager!=null){this.valuesManager.$71e(_3,null,_7,this)}}
,isc.A.remoteValidationForLocalSaveComplete=function isc_ListGrid_remoteValidationForLocalSaveComplete(_1,_2,_3){var _4=_1.clientContext,_5=_4.editInfo;if(this.$31o&&this.$31o[_5.editValuesID]){delete this.$31o[_5.editValuesID];if(isc.isAn.emptyObject(this.$31o))delete this.$31o}
if(_1.status<0&&_1.status!=isc.RPCResponse.STATUS_VALIDATION_ERROR){isc.logWarn("Server-side validation failed: "+_1.data);isc.RPCManager.handleError(_1,_3);return}
var _6=this.$300(_5.editValuesID);if(_1.errors){var _7=isc.DynamicForm.getSimpleErrors(_1.errors),_8=false;for(var _9 in _7){_8=true;if(_6==null){this.logWarn("Asynchronous remote validation failed for attempted save of edits "+"for row:"+_5.rowNum+" but edit values have subsequently been "+"discarded. Ignoring validation failure.  Validation failure details:\n"+this.echoFull(_7));break}
var _10=_7[_9],_11=this.getField(_9);if(_10!=null&&_11!=null){var _12=_5.values[_9],_13=_6[_9];if(_12!=_13&&!(isc.isA.Date(_13)&&isc.isA.Date(_12)&&(_13.getTime()==_12.getTime())))
{this.logWarn("Asynchronous remote validation failed for attempted save of edits "+"for row:"+_5.rowNum+". Submitted value for field "+_9+" was "+_12+", but the edit value has subsequently been modified "+"and is now:"+_13+". Not showing the following validation "+"error[s] for this field:\n"+this.echoAll(_10));continue}
if(!isc.isAn.Array(_10))_10=[_10];_10=_10.getProperty("errorMessage");this.setFieldError(_5.rowNum,_9,_10,false)}}
if(_8)return}
this.$336(_5,_4.saveCallback)}
,isc.A.$338=function isc_ListGrid__unsortOnChange(_1,_2){var _3=this.$60z();if(_3==null)return;var _4=this.fields[_3];if(_4==null)return;var _5=_4[this.fieldIdProperty];var _6;if(_5!=null&&_1[_5]!==_6&&(_2==null||_1[_5]!=_2[_5]))
{this.unsort()}}
,isc.A.$337=function isc_ListGrid__updateRecordReply(_1,_2){var _3=_1.data,_4=_1.clientContext,_5=_4.editInfo,_6=_5.colNum,_7=this.fields[_6];if(this.$31o&&this.$31o[_5.editValuesID]){delete this.$31o[_5.editValuesID];if(isc.isAn.emptyObject(this.$31o))delete this.$31o}
var _8=this.getEditSession(_5.editValuesID);if(_8!=null){_5.rowNum=_8.$24t}else{if(_3&&_3[0]){_5.rowNum=this.findRowNum(_3[0]);if(this.useCellRecords)_5.colNum=this.findColNum(_3[0])}
else _5.rowNum=-1}
var _9=_5.rowNum,_6=_5.colNum;if(_1.status<0){var _10=this.parseServerErrors(_1.errors);this.logInfo("error on save, status: "+_1.status+", errors: "+this.echo(_1.errors),"gridEdit");if(isc.isAn.Array(_10)){if(_10.length!=1)
this.logWarn("Server reports validation errors for multiple records - only "+"displaying errors for the first record.","gridEdit");_10=_10[0]}
if(this.useCellRecords)this.setCellErrors(_9,_6,_10[this.getEditorName(_9,_6)]);else this.setRowErrors(_9,_10);this.$331(_5,_4.saveCallback,_1,_2);return false}
this.$339(_5,_4.saveCallback,_1,_2)}
,isc.A.parseServerErrors=function isc_ListGrid_parseServerErrors(_1){if(isc.isAn.Array(_1)){if(_1.length>1){this.logWarn("server returned errors for multiple records - dropping all but the "+"first record returned")}
_1=_1[0]}
if(_1&&_1.recordPath)delete _1.recordPath;for(var _2 in _1){var _3=_1[_2];if(_3==null)_3="Unspecified error";if(isc.isAn.Array(_3)){for(var i=0;i<_3.length;i++){_3[i]=_3[i].errorMessage}}else if(_3.errorMessage){_1[_2]=[_3.errorMessage]}else{_1[_2]=[_3]}}
return _1}
,isc.A.$339=function isc_ListGrid__editCompleteCallback(_1,_2,_3,_4){if(this.$30a&&this.getEditForm().hasFocus)this.storeUpdatedEditorValue(true);var _5=_1.rowNum,_6=_1.colNum,_7=_1.editValuesID,_8=this.getEditSession(_7);var _9;if(_3!=null){var _10=isc.DataSource.getUpdatedData(_4,_3,true),_9=isc.isAn.Array(_10)?_10[0]:_10;if(_9==null){return}
if(_8&&_8.$33b==null){this.newRecordForEditValues(_7,_9)}}else{_9=this.getCellRecord(_5,_6)}
var _11=_1.values,_12=this.$300(_7),_13=this.isEditingRecord(_5,_6),_14=this.dataSource?isc.DS.get(this.dataSource).getPrimaryKeyFieldNames():null;var _15=false;for(var _16 in _12){var _17=_14&&_14.contains(_16);if(this.fieldValuesAreEqual(this.getField(_16),_12[_16],_11[_16]))
{if(!(_13&&_17))this.clearEditValue(_7,_16,true,true)}else{if(!_17)_15=true}}
if(!_13&&!_15){this.$321(_7,_6)}
if(_3!=null||_1.newRecord){this.displayUpdatedDSRecord(_5,_6,_9)}
var _18=_1.values,_19=_1.oldValues,_20=_1.editCompletionEvent;if(_5==-1)_5=_1.rowNum;if(this.convertToMethod("editComplete")){this.editComplete(_5,_6,_18,_19,_20,_3)}
this.$50u(_9,_5,_6,_18,_19,_4);if(_2)this.$332(_2,_5,_6,_20,true)}
,isc.A.displayUpdatedDSRecord=function isc_ListGrid_displayUpdatedDSRecord(_1,_2,_3){if(this.$31n()){this.suppressEditRowRemap=true;this.dataChanged();this.suppressEditRowRemap=false}else if(this.isGrouped){this.regroup()}}
,isc.A.isEditingRecord=function isc_ListGrid_isEditingRecord(_1,_2){return this.getEditRow()==_1}
,isc.A.$332=function isc_ListGrid__fireSaveCallback(_1,_2,_3,_4,_5){if(_1!=null){this.fireCallback(_1,this.$31f,[_2,_3,_4,_5])}}
,isc.A.$50u=function isc_ListGrid__fireCellChanged(_1,_2,_3,_4,_5,_6){var _7,_8;for(var i in _4){var _10=(_5==null?null:_5[i]);if(_4[i]==_10)continue;var _11=this.fields.find(this.fieldIdProperty,i);if(!_11)continue;if(this.isGrouped&&_11){var _12=this.getGroupByFields();if(_12.contains(_11.name))_7=true}
if(this.shouldAutoFitField(_11)){_8=true}
this.$34a(_1,_11,_4[i],_10,_2,this.fields.indexOf(_11))}
if(_8){if(this.shouldSaveLocally()){this.updateFieldWidthsForAutoFitValue("Local edit")}}
if(_7){if(this.shouldSaveLocally()){this.$607(_1,_1,_2,_4)}
this.$34v=false;this.$34u()}}
,isc.A.$34a=function isc_ListGrid__cellChanged(_1,_2,_3,_4,_5,_6){if(_2&&_2.cellChanged){if(!isc.isA.Function(_2.cellChanged)){isc.Func.replaceWithMethod(_2,"cellChanged","record,newValue,oldValue,rowNum,colNum,grid,recordNum,fieldNum")}
_2.cellChanged.call(this,_1,_3,_4,_5,_6,this,_5,_6)}else if(this.cellChanged){this.cellChanged(_1,_3,_4,_5,_6,this,_5,_6)}}
,isc.A.$331=function isc_ListGrid__editFailedCallback(_1,_2,_3,_4){var _5=_1.rowNum,_6=_1.colNum,_7=_1.values,_8=_1.oldValues,_9=_1.editCompletionEvent;if(this.convertToMethod("editFailed")){this.editFailed(_5,_6,_7,_8,_9,_3,_4)}
if(_2!=null)this.$332(_2,_5,_6,_9,false);this.clearSubmittedEditValues(_1.editValuesID,_7)}
,isc.A.editFailed=function isc_ListGrid_editFailed(_1,_2,_3,_4,_5,_6,_7){if(_6!=null&&_6.errors==null){isc.RPCManager.$a0(_6,_7)}}
,isc.A.validateRow=function isc_ListGrid_validateRow(_1,_2){return this.validateRecord(_1,_2)}
,isc.A.validateRecord=function isc_ListGrid_validateRecord(_1,_2){var _3,_4;if(isc.isAn.Array(_1)){_3=_1[0];_4=_1[1]}else{_3=_1}
var _5=this.getEditValues(_3,_4);if(_5==null)return true;var _6=this.getCellRecord(_3,_4),_7=this.getFields().getProperty(this.fieldIdProperty);var _8=isc.rpc.startQueue();var _9=this.validateRowValues(_5,_6,_3,_7);if(!_8)isc.rpc.sendQueue();this.setRowErrors(_3,_9,_2);return(_9==null)}
,isc.A.validateRowValues=function isc_ListGrid_validateRowValues(_1,_2,_3,_4){var _5=true,_6={};if(!isc.isAn.Array(_4))_4=[_4];var _7=false;if(_2==null){_7=true;_2={}}
for(var i=0;i<_4.length;i++){var _9,_10;if(isc.isA.Number(_4[i])){_10=_4[i];_9=_4[i]=this.getFieldName(_10)}else{_9=_4[i];_10=this.getFields().findIndex(this.fieldIdProperty,_9)}
if(_10<0)continue;var _11=((_7&&this.canEditCell(_3,_10))||isc.propertyDefined(_1,_9));if(!_11)continue;var _12=_1[_9],_13=_2[_9],_14=this.validateCellValue(_3,_10,_12,_13);if(_14!=null){_5=false;_6[_9]=_14}}
if(!_5)return _6;return null}
,isc.A.validateCell=function isc_ListGrid_validateCell(_1,_2,_3,_4){var _5;if(isc.isA.String(_2))_5=this.getColNum(_2);else{_5=_2;_2=this.getEditorName(_1,_5)}
if(this.$30a&&(this.getEditRow()==_1)&&this.getEditForm().getItem(_2)!=null)
{this.storeUpdatedEditorValue(null,_5)}
var _6=this.getEditValues(_1,_5),_7=this.cellHasErrors(_1,_2),_8=_6?_6[_2]:null,_9=this.getCellRecord(_1,_5),_10=_9?_9[_2]:null;if(_9!=null&&(!_6||!isc.propertyDefined(_6,_2))){return true}
var _11=this.validateCellValue(_1,_5,_8,_10,_4);if(_11!=null){this.setFieldError(_1,_2,_11);return false}
if(_7){this.setFieldError(_1,_2,null)}
return true}
,isc.A.validateCellValue=function isc_ListGrid_validateCellValue(_1,_2,_3,_4,_5){var _6=this.getCellRecord(_1,_2);var _7=this.getField(_2);return this.validateFieldValue(_3,_4,_6,_7,_1,_2,_5)}
,isc.A.getRequiredFieldMessage=function isc_ListGrid_getRequiredFieldMessage(_1,_2){return isc.Validator.requiredField}
,isc.A.validateFieldValue=function isc_ListGrid_validateFieldValue(_1,_2,_3,_4,_5,_6,_7){var _8=[],_9=true;var _10=this.cellIsRequired(_5,_6);if(_10&&(_1==null||isc.isAn.emptyString(_1))){var _11=this.getRequiredFieldMessage(_4,_3);_8.add(_11);_9=false}
var _12=this.getCellValidators(_5,_6);if(_12){if(this.logIsDebugEnabled("gridEdit")){this.logDebug((this.useCellRecords?"At col:"+_6:"At field: "+_4.name)+" applying validators: "+this.echoAll(_12)+" to value:"+_1,"gridEdit")}
var _13,_14=this.getEditedRecord(_5,_6),_15=null,_16={rowNum:_5};if(this.$81c==true){_16.skipServerValidation=true;this.$81c=null}
if(_7){_13=this.validateFieldAndDependencies(_4,_12,_1,_14,_16)}else{_13=this.validateField(_4,_12,_1,_14,_16)}
if(_13!=null){if(_13.valid!=true){_9=false;if(_7)
_8=_13.errors[_4.name]||[];else
_8=_13.errors||[]}else{if(_13.resultingValue!=null){this.setEditValue(_5,_6,_13.resultingValue);_1=_13.resultingValue}}
_15=_13.errors;if(this.logIsInfoEnabled("gridEdit")){this.logInfo("validateFieldValue, newValue: "+this.echo(_1)+", passed validation: "+_9+", resultingValue: "+this.echo(_13.resultingValue),"gridEdit")}}}
if(_7){for(var _17 in _15){if(_17!=_4.name){this.setFieldError(_5,_17,_15[_17],true)}}}
if(_9)return null
else return _8}
,isc.A.cellIsRequired=function isc_ListGrid_cellIsRequired(_1,_2){var _3=this.getField(_2);return _3&&(_3.required||this.isXMLRequired(_3))}
,isc.A.getCellValidators=function isc_ListGrid_getCellValidators(_1,_2){var _3=this.getField(_2);return _3?_3.validators:null}
,isc.A.hasErrors=function isc_ListGrid_hasErrors(){var _1=this.getAllEditRows(true);for(var i=0;i<_1.length;i++){if(this.rowHasErrors(_1[i]))return true}
return false}
,isc.A.rowHasErrors=function isc_ListGrid_rowHasErrors(_1,_2){var _3=this.getEditSession(_1,_2);return(_3&&_3.$33o&&!isc.isA.emptyObject(_3.$33o))}
,isc.A.cellHasErrors=function isc_ListGrid_cellHasErrors(_1,_2){var _3=this.getEditorName(_1,_2),_4=this.getEditSession(_1,_2),_5=_4?_4.$33o:null;return(_5!=null&&_5[_3]!=null)}
,isc.A.getRowValidationErrors=function isc_ListGrid_getRowValidationErrors(_1){return this.getRowErrors(_1)}
,isc.A.getRowErrors=function isc_ListGrid_getRowErrors(_1,_2){var _3=this.getEditSession(_1,_2);return(_3!=null?_3.$33o:null)}
,isc.A.getCellErrors=function isc_ListGrid_getCellErrors(_1,_2){var _3=this.getEditSession(_1,_2);if(_3==null)return null;if(isc.isA.Number(_2))_2=this.getEditorName(_1,_2);var _4=_3.$33o;return(_4==null?null:_4[_2])}
,isc.A.setCellErrors=function isc_ListGrid_setCellErrors(_1,_2,_3,_4){return this.setFieldError(_1,_2,_3,_4)}
,isc.A.setFieldError=function isc_ListGrid_setFieldError(_1,_2,_3,_4){var _5=_2;if(isc.isA.Number(_5))_5=this.getEditorName(_1,_5);if(_3==null||(isc.isAn.Array(_3)&&_3.length==0)){if(!this.cellHasErrors(_1,_2))return;var _6=this.getEditSession(_1,_2);delete _6.$33o[_5];if(isc.isAn.emptyObject(_6.$33o))delete _6.$33o}else{if(_5==null){this.logWarn("setFieldError() passed field identifier '"+_2+"'. "+"Unable to find corresponding field in this grid - not setting error.");return}
var _7=this.getFields().getProperty(this.fieldIdProperty);if(!_7.contains(_5))
{this.logWarn("setFieldError() passed field identifier '"+_2+"'. This "+"is not a visible field in the grid - error may not be visible to user.")}
var _8;if(isc.isAn.Array(_3))_8=_3
else _8=[_3];var _6=this.getEditSession(_1,_2);if(_6==null){var _9=this.getColNum(_2),_10=this.getCellRecord(_1,_9);_6=this.createEditSession(_1,_9,_10)}
if(_6.$33o==null)_6.$33o={};_6.$33o[_5]=_8}
if(!_4){if(this.useCellRecords)this.showCellErrors(_1,_2)
else this.showErrors(_1,[_5])}}
,isc.A.addFieldErrors=function isc_ListGrid_addFieldErrors(_1,_2,_3,_4){this.setFieldError(_4,_1,_2)}
,isc.A.setRowErrors=function isc_ListGrid_setRowErrors(_1,_2,_3){if(_1==null||_1==-1)return;var _4=this.getRowErrors(_1);if(!_2&&!_4)return;var _5=this.getEditSession(_1);if(_5==null)_5=this.createEditSession(_1,null,this.getRecord(_1));if(_2){var _6=this.getFields().getProperty(this.fieldIdProperty);for(var _7 in _2){if(!_3&&!_6.contains(_7)){this.logWarn("setRowErrors() passed error for non-visible field: '"+_7+"'")}
if(!isc.isAn.Array(_2[_7]))_2[_7]=[_2[_7]]}}
_5.$33o=_2;if(!_3){var _8=_2?isc.getKeys(_2):[];for(var _7 in _4){var _9=this.getColNum(_7);if(!_8.contains(_7))_8.add(_7)}
this.showErrors(_1,_8)}}
,isc.A.clearFieldError=function isc_ListGrid_clearFieldError(_1,_2,_3){return this.setFieldError(_1,_2,null,_3)}
,isc.A.clearRowErrors=function isc_ListGrid_clearRowErrors(_1,_2){this.setRowErrors(_1,null,_2)}
,isc.A.showErrors=function isc_ListGrid_showErrors(_1,_2){if(_2==null)_2=this.getFields().getProperty(this.fieldIdProperty);var _3=this.getRowErrors(_1);if(_3==null)_3={};var _4;for(var i=0;i<_2.length;i++){var _6=_2[i];this.showCellErrors(_1,_6);if(_4==null&&_3[_6])_4=_6}
var _7=this.assembleErrorMessage(_3,_2);if(_7){this.displayRowErrorMessages(_7)}
if(this.stopOnErrors&&_4!=null){var _8=this.getFields().findIndex(this.fieldIdProperty,_4);this.showInlineEditor(_1,_8)}}
,isc.A.showCellErrors=function isc_ListGrid_showCellErrors(_1,_2){var _3;if(isc.isA.Number(_2)){this.getEditorName(_1,_2)}else{_3=_2;_2=this.getColNum(_3)}
if(_2==-1)return;if(this.showErrorIcons){this.refreshCell(_1,_2,null,true)}else{this.body.$29h(null,_1,_2)}}
,isc.A.assembleErrorMessage=function isc_ListGrid_assembleErrorMessage(_1,_2){var _3=false,_4="Validation Errors occurred:\r\n";for(var _5 in _1){if(_2&&!_2.contains(_1))continue;var _6=this.$34b(_5,_1[_5]);if(_6!=null){_3=true;_4+=_6}}
return(_3?_4:null)}
,isc.A.$34b=function isc_ListGrid__createFieldErrorString(_1,_2){var _3,_4,_5=this.getField(_1),_6=_5&&_5.title?_5.title:_1;for(var i=0;i<_2.length;i++){var _8=_2[i];if(_8!=null){if(_4==null){_4="<br>Field '"+_6+"':"}
_4+="<br>* "+_8}}
return _4}
,isc.A.displayRowErrorMessages=function isc_ListGrid_displayRowErrorMessages(_1){if(this.stopOnErrors)isc.warn(_1,"var LG="+this.getID()+";if(LG.$286)LG.$286.focus()")}
,isc.A.removeData=function isc_ListGrid_removeData(_1,_2,_3){_3=isc.addProperties({},_3);if(_3.clientContext==null){_3.clientContext={}}
_3.clientContext.removeDataCallback=_2;if(this.getDataSource()!=null&&!this.shouldSaveLocally()){return this.Super("removeData",[_1,{target:this,methodName:"removeDataComplete"},_3],arguments)}
if(this.data){var _4=this.getEditValuesID(_1);if(_4!=null)this.discardEdits(_4);this.getOriginalData().remove(_1);this.updateFieldWidthsForAutoFitValue("removeData");this.regroup()}
this.fireCallback({target:this,methodName:"removeDataComplete"},"dsResponse,data,dsRequest",[null,_1,_3])}
,isc.A.removeDataComplete=function isc_ListGrid_removeDataComplete(_1,_2,_3){if(_1&&_1.status!=0){}else{if(!isc.isAn.Array(_2))_2=[_2];for(var i=0;i<_2.length;i++){var _5=this.getEditValuesID(_2[i]);if(_5!=null){this.discardEdits(_5)}}}
if(_3&&_3.clientContext&&_3.clientContext.removeDataCallback){this.fireCallback(_3.clientContext.removeDataCallback,"dsResponse,data,dsRequest",[_1,_2,_3])}}
,isc.A.removeRecordClick=function isc_ListGrid_removeRecordClick(_1,_2){var _3=this.warnOnRemoval,_4=this.shouldDeferRemoval();if(_3&&_4&&this.recordMarkedAsRemoved(_1))_3=false;if(_3){isc.ask(this.warnOnRemovalMessage,this.getID()+".completeRemoveRecordClick(value, "+_1+","+_2+")")}else{this.completeRemoveRecordClick(true,_1,_2)}}
,isc.A.completeRemoveRecordClick=function isc_ListGrid_completeRemoveRecordClick(_1,_2,_3){if(!_1)return;if(this.shouldDeferRemoval()){if(this.recordMarkedAsRemoved(_2)){this.unmarkRecordRemoved(_2)}else{this.markRecordRemoved(_2)}}else{var _4=this.getAllEditRows(),_5=_4.contains(_2);if(_5)this.discardEdits(_2,_3);var _6=this.getCellRecord(_2,_3);if(_6==null)return;this.delayCall("removeRecord",[_2,_6])}}
,isc.A.removeRecord=function isc_ListGrid_removeRecord(_1,_2){if(_2==null)_2=this.data.get(_1);else _1=(this.data?this.data.indexOf(_2):null)
if(_2==null||_1==null||_1==-1||!this.data)return;if(isc.Offline&&isc.Offline.isOffline()&&this.dataSource&&!this.dataSource.clientOnly){isc.warn(this.offlineSaveMessage);return}
var _3=this.animateRemoveRecord&&this.isDrawn()&&this.isVisible()&&this.body&&_1>=this.body.$252&&_1<=this.body.$253;if(_2.$29a&&_2.$29a.length>0){_3=false}
if(_3){this.$50w=true;var _4=1;if(isc.isA.Tree(this.data)&&this.data.isFolder(_2)&&this.data.isOpen(_2)){var _5=this.data.getChildren(_2);if(_5)_4+=_5.getLength()}
this.$63o=_4;this.body.$28b(false,_1,_1+_4)}
var _6=this.getDataSource(),_7=(_6==null||this.shouldSaveLocally());if(!_7){if(!_6.getPrimaryKeyField){this.logWarn("DataSource:"+_6+" has no primary key field - unable to remove records");return}}
var _8;if(_3){_8=this.getID()+".showRemoveCompleteAnimation("+_1+", data,dsResponse);"}
this.removeData(_2,_8,{showPrompt:false})}
,isc.A.showRemoveCompleteAnimation=function isc_ListGrid_showRemoveCompleteAnimation(_1,_2,_3){if(_3&&_3.status<0){this.logWarn("Removal of record failed");_2=null}
if(!_2){delete this.$50w;if(this.body){delete this.body.$28c;delete this.body.$28e;delete this.body.$28f;delete this.body.$28h}
this.markForRedraw()}else{this.startRowAnimation(false,_1,_1+this.$63o,{target:this,methodName:"$63d"},this.animateRemoveSpeed,this.animateRemoveTime);delete this.$63o}}
,isc.A.$63d=function isc_ListGrid__removeDataAnimationComplete(){delete this.$50w;this.redraw()}
,isc.A.startRowAnimation=function isc_ListGrid_startRowAnimation(_1,_2,_3,_4,_5,_6,_7,_8,_9){if(!this.body)return;this.finishRowAnimation();this.$64v=_4;if(this.frozenBody){this.frozenBody.startRowAnimation(_1,_2,_3,{target:this,methodName:"rowAnimationComplete",argNames:"body,hasFrozenBody",args:[this.frozenBody,true]},_5,_6,_7,_8,true,_9)}
this.body.startRowAnimation(_1,_2,_3,{target:this,methodName:"rowAnimationComplete",argNames:"body,hasFrozenBody",args:[this.body,(this.frozenBody!=null)]},_5,_6,_7,_8,true,_9)}
,isc.A.rowAnimationComplete=function isc_ListGrid_rowAnimationComplete(_1,_2){var _3=!_2||this.$64w;if(!_3){this.$64w=true}else{delete this.$64w;var _4=this.$64v;delete this.$64v;if(_4!=null)this.fireCallback(_4)}}
,isc.A.finishRowAnimation=function isc_ListGrid_finishRowAnimation(){if(this.body)this.body.finishRowAnimation();if(this.frozenBody)this.frozenBody.finishRowAnimation()}
,isc.A.animateRowHeight=function isc_ListGrid_animateRowHeight(_1,_2,_3,_4,_5,_6,_7){if(!this.body)return;return this.body.animateRowHeight(_1,_2,_3,_4,_5,_6,_7,true)}
,isc.A.setCellHeight=function isc_ListGrid_setCellHeight(_1){var _2=this.cellHeight;this.cellHeight=_1;if(this.body)this.body.cellHeight=_1;if(_2!=_1&&this.isDrawn()){this.body.markForRedraw("Cell height changed");if(this.frozenBody)this.frozenBody.markForRedraw("Cell height changed")}}
,isc.A.setRowHeight=function isc_ListGrid_setRowHeight(_1,_2){if(!this.body)return;if(this.frozenBody!=null)this.frozenBody.setRowHeight(_1,_2);return this.body.setRowHeight(_1,_2)}
,isc.A.getDragTrackerIcon=function isc_ListGrid_getDragTrackerIcon(_1){var _2=_1?_1[0]:null,_3;if(_2){var _4=this.getTitleField();_3=this.getValueIcon(this.getField(_4),_2[_4],_2)}
if(_3==null)_3=this.trackerImage;return _3}
,isc.A.getDragTrackerTitle=function isc_ListGrid_getDragTrackerTitle(_1,_2){var _3=this.getTitleField(),_4=this.getColNum(_3),_5=this.getCellValue(_1,_2,_4);return"<nobr>"+_5+"</nobr>"}
,isc.A.dragStart=function isc_ListGrid_dragStart(){if(this.canDragSelect)return true;var _1=this.selection.getSelection();if(!_1||_1.length==0)return false;for(var i=0;i<_1.length;i++){if(_1[i].canDrag==false)return false}
this.$758=_1;return true}
,isc.A.dragMove=function isc_ListGrid_dragMove(){var _1=isc.EH.dropTarget;if(!this.canDragRecordsOut&&_1!=null&&_1!=this&&!this.contains(_1))
{return false}}
,isc.A.dropMove=function isc_ListGrid_dropMove(){if(!this.canAcceptDroppedRecords&&isc.EH.dragTarget!=this)return true;if(!this.willAcceptDrop()){this.body.setNoDropIndicator()}else{this.body.clearNoDropIndicator()}
if(!this.canReorderRecords)return true;this.showDragLineForRecord()}
,isc.A.dropOut=function isc_ListGrid_dropOut(){this.body.clearNoDropIndicator();this.hideDragLine()}
,isc.A.dragStop=function isc_ListGrid_dragStop(){this.body.clearNoDropIndicator();this.hideDragLine();this.$758=null}
,isc.A.willAcceptDrop=function isc_ListGrid_willAcceptDrop(){var _1=this.ns.EH;if(!this.Super("willAcceptDrop",arguments)&&_1.dragTarget!=this.body)return false;if(_1.dragTarget==this){if(!this.$34c())return false}else{if(!this.canAcceptDroppedRecords)return false}
if(!isc.isAn.Object(_1.dragTarget.getDragData()))return false;isc.$54j=true;var _2=this.getEventRecordNum(),_3=this.getEventFieldNum();var _4=this.getReorderPosition(_2);if(_2!=-2&&_4==isc.ListGrid.AFTER)_2+=1;isc.$54j=false;if(_2<-1)return true;if(_2==-1)return false;var _5=this.getRecord(_2,_3);if(!this.recordIsEnabled(_2,_3)||(_5!=null&&_5.canAcceptDrop==false))return false;return true}
,isc.A.$34c=function isc_ListGrid__canDragRecordsToSelf(){return this.canReorderRecords}
,isc.A.drop=function isc_ListGrid_drop(){if(this.willAcceptDrop()==false)return false;var _1=this.ns.EH.dragTarget;var _2=this.getDropIndex();var _3=_1.cloneDragData();var _4=(_2!=-2?this.data.get(_2):null);if(this.onRecordDrop!=null&&(this.onRecordDrop(_3,_4,_2,_1)==false))
{return false}
return this.recordDrop(_3,_4,_2,_1)}
,isc.A.recordDrop=function isc_ListGrid_recordDrop(_1,_2,_3,_4){this.transferRecords(_1,_2,(this.canReorderRecords?_3:null),_4);if(this.recordsDropped)this.recordsDropped(_1,_3,this,_4);return false}
,isc.A.viewRow=function isc_ListGrid_viewRow(_1){this.getDataSource().viewFile(this.getRecord(_1))}
,isc.A.downloadRow=function isc_ListGrid_downloadRow(_1){this.getDataSource().downloadFile(this.getRecord(_1))}
,isc.A.autoSizeColumn=function isc_ListGrid_autoSizeColumn(_1){var _2=this.body.getColumnAutoSize(_1);if(_2!=null)this.resizeField(_1,_2)}
,isc.A.getRecord=function isc_ListGrid_getRecord(_1,_2){return this.getCellRecord(_1,_2)}
,isc.A.getCellRecord=function isc_ListGrid_getCellRecord(_1,_2){if(!isc.isA.Number(_1)){this.logWarn("getCellRecord called with bad rowNum: "+this.echo(_1));return}
if(!this.data||_1>=this.data.getLength())return null;var _3=this.data.get(_1);if(this.$33q)this.$34d(_3,_1);return _3}
,isc.A.$34d=function isc_ListGrid__testRowEditData(_1,_2){if(_1==null||_1[this.editValuesTestedProperty])return;if(this.$33a==null)this.$33a={};var _3=true;for(var i in this.$52b){var _5=this.$52b[i];if(_5!=null&&_5.$24t==null){if(this.comparePrimaryKeys(_1,_5.$33b)){_5.$24t=_2;this.$33a[_2]=i}else{_3=false}}}
if(_3)delete this.$33q;_1[this.editValuesTestedProperty]=true}
,isc.A.comparePrimaryKeys=function isc_ListGrid_comparePrimaryKeys(_1,_2){var _3=this.getPrimaryKeys(_1);if(!this.dataSource||this.shouldSaveLocally())return(_3==_2);for(var _4 in _3){if(_3[_4]!=_2[_4])return false}
return true}
,isc.A.getPrimaryKeys=function isc_ListGrid_getPrimaryKeys(_1){if(this.shouldSaveLocally())return _1;var _2=this.getDataSource(),_3=_2.getPrimaryKeyFieldNames(),_4={};if(!isc.isAn.Array(_3))_3=[_3];for(var i=0;i<_3.length;i++){_4[_3[i]]=_1[_3[i]]}
return _4}
,isc.A.getTotalRows=function isc_ListGrid_getTotalRows(){if(this.data==null)return 0;if(this.data.getLength==null){this.logWarn("GetTotalRows: ListGrid has invalid data object:"+this.echo(this.data));return 0}
var _1=this.data.getLength(),_2=this.$34e();if(_2!=null&&_2+1>_1)_1=_2+1;if(this.showNewRecordRow)_1+=1;return _1}
,isc.A.$34e=function isc_ListGrid__getLastEditRow(){if(this.$33j==null){var _1=-1,_2=(this.$33a?isc.getKeys(this.$33a):[]);for(var i=0;i<_2.length;i++){var _4=parseInt(_2[i]);if(_4>_1)_1=_4}
this.$33j=_1}
return this.$33j}
,isc.A.recordIsEnabled=function isc_ListGrid_recordIsEnabled(_1,_2){var _3=this.getCellRecord(_1,_2);if(_3==null)return true;if(this.recordMarkedAsRemoved(_1))return false;return(_3[this.recordEnabledProperty]!=false&&_3[this.isSeparatorProperty]!=true)}
,isc.A.cellIsEnabled=function isc_ListGrid_cellIsEnabled(_1,_2){return this.recordIsEnabled(_1,_2)}
,isc.A.getCellField=function isc_ListGrid_getCellField(_1,_2){return this.getField(_2)}
,isc.A.getFields=function isc_ListGrid_getFields(){return this.fields}
,isc.A.getAllFields=function isc_ListGrid_getAllFields(){return this.completeFields||this.fields}
,isc.A.getSpecifiedField=function isc_ListGrid_getSpecifiedField(_1){var _2=this.completeFields;if(_2==null)_2=this.fields;if(_2==null)return;if(isc.isAn.Object(_1)){if(_2.contains(_1))return _1;return null}else if(isc.isA.String(_1))return _2.find(this.fieldIdProperty,_1);else return(_2[_1])}
,isc.A.getFieldName=function isc_ListGrid_getFieldName(_1){var _2=this.getField(_1);return _2?_2[this.fieldIdProperty]:null}
,isc.A.getField=function isc_ListGrid_getField(_1){if(this.fields==null||_1==null)return null;var _2;if(this.$81g){_2=this.fields[_1];if(_2!=null)return _2}else{if(isc.isA.Number(_1))return this.fields[_1]}
if(_1.$81f||isc.isAn.Object(_1))return _1;if(isc.isA.String(_1)){var _3=_1.contains(isc.Canvas.$70l);if(!_3){return this.fields.find(this.fieldIdProperty,_1)}else{var _4,_5,_6,_7=_1.trim(isc.Canvas.$70l),_8,_9,_10,_11;if(_1.startsWith(isc.Canvas.$70l)){_5=_1.substring(1);if(_1.endsWith(isc.Canvas.$70l)){_6=_1.substring(0,_1.length)}else{_4=_1+"/"}}else{if(_1.endsWith(isc.Canvas.$70l)){_6=_1.substring(0,_1.length);_4="/"+_1}else{_4="/"+_1+"/"}
_8="/"+_1}
var _12=_7.lastIndexOf(isc.Canvas.$70l);if(_12!=-1){_9=_7.substring(_12+1);_10=_9+"/"}
for(var i=0;i<this.fields.length;i++){var _14=this.fields[i].dataPath;if(!_14)continue;if(_14==_1||(_5&&_14==_5)||(_6&&_14==_6)||(_7&&_14==_7)||(_8&&_14==_8)||(_4&&_14==_4)||(_9&&_14==_9)||(_10&&_14==_10))
{return this.fields[i]}}}}
return null}
,isc.A.getColNum=function isc_ListGrid_getColNum(_1){return this.getFieldNum(_1)}
,isc.A.getFieldTitle=function isc_ListGrid_getFieldTitle(_1){var _2;if(isc.isAn.Object(_1))_2=_1;else _2=this.fields[_1];if(!_2)return"Unknown field";if(_2.getFieldTitle){isc.Func.replaceWithMethod(_2,"getFieldTitle","viewer,fieldNum");return _2.getFieldTitle(this,_1)}
return _2.title||_2.name}
,isc.A.getSummaryTitle=function isc_ListGrid_getSummaryTitle(_1){var _2;if(_1.getSummaryTitle!=null){isc.Func.replaceWithMethod(_1,"getSummaryTitle","viewer,field");_2=_1.getSummaryTitle(this,_1)}else if(_1.summaryTitle!=null){_2=_1.summaryTitle}else{_2=this.getFieldTitle(_1)}
return _2}
,isc.A.setValueMap=function isc_ListGrid_setValueMap(_1,_2){this.Super("setValueMap",arguments);if(this.$30a){var _3,_4;var _5=this.getColNum(_1);if(_5!=-1&&_5<this.fields.length){var _4=this.getField(_1),_3=_4[this.fieldIdProperty];this.$286.setValueMap(_3,this.getEditorValueMap(_4,this.getEditedRecord(this.getEditRow(),_5)))}}
if(this.summaryRow){this.summaryRow.setValueMap(_1,_2)}
if(this.isDrawn()&&this.isVisible()){this.$25a("setValueMap")}}
,isc.A.getDisplayValue=function isc_ListGrid_getDisplayValue(_1,_2){var _3=this.getField(_1),_4=_3?_3.valueMap:null;if(isc.isAn.Object(_4)&&!isc.isAn.Array(_4)&&isc.propertyDefined(_4,_2))
{return _4[_2]}
return _2}
,isc.A.getData=function isc_ListGrid_getData(){return this.data}
,isc.A.getRecordIndex=function isc_ListGrid_getRecordIndex(_1){var _2=-1;if(this.body&&this.body.isDrawn()){_2=this.data.indexOf(_1,this.body.$252,this.body.$253)}
if(_2==-1)_2=this.data.indexOf(_1);return _2}
,isc.A.getRecordCellIndex=function isc_ListGrid_getRecordCellIndex(_1){return[this.getRecordIndex(),0]}
,isc.A.getEventRow=function isc_ListGrid_getEventRow(_1){if(this.header&&this.header.containsPoint(this.ns.EH.getX(),this.ns.EH.getY())){return-1}
return this.body.getEventRow(_1)}
,isc.A.getEventColumn=function isc_ListGrid_getEventColumn(_1){return this.body.getEventColumn(_1)}
,isc.A.getEventRecordNum=function isc_ListGrid_getEventRecordNum(_1){return this.getEventRow(_1)}
,isc.A.getEventFieldNum=function isc_ListGrid_getEventFieldNum(_1){return this.getEventColumn(_1)}
,isc.A.getReorderPosition=function isc_ListGrid_getReorderPosition(_1,_2){if(_2==null)_2=this.body.getOffsetY();if(_1==null)_1=this.getEventRow(_2);if(_1==-2)return isc.ListGrid.AFTER;if(_1>=this.getTotalRows())return isc.ListGrid.BEFORE;var _3=this.body.getRowTop(_1),_4=this.body.getRowSize(_1);if(_2>Math.round(_3+_4/ 2)){return isc.ListGrid.AFTER}else{return isc.ListGrid.BEFORE}}
,isc.A.getDropIndex=function isc_ListGrid_getDropIndex(_1,_2){if(this.data.getLength()==0){return 0}
if(_1==null)_1=this.getEventRow();if(_2==null)_2=this.getReorderPosition(_1);if(this.canReorderRecords){if(_1==-2)_1=this.data.getLength()-1;if(_2==isc.ListGrid.AFTER)_1++}
return _1}
,isc.A.selectionChanged=function isc_ListGrid_selectionChanged(){}
,isc.A.getSerializeableFields=function isc_ListGrid_getSerializeableFields(_1,_2){_1.addList(["header","selection"]);return this.Super("getSerializeableFields",arguments)}
,isc.A.deriveFrozenFields=function isc_ListGrid_deriveFrozenFields(){var _1=this.frozenFields=this.fields.findAll("frozen",true),_2=this.fields.findAll("autoFreeze",true),_3=this.fields.findAll("$74j",true);if(_1&&_3&&_1.length==_3.length){for(var i=0;i<_3.length;i++){var _5=_3[i];_5.frozen=false;delete _5.$74j}
_1=this.frozenFields=null}
var _6=this.completeFields.findAll("frozen",true);if(_1&&_1.length==this.fields.length){_1=this.frozenFields=null;this.$54d=true}else this.$54d=false;if(_1){if(_2){for(var i=0;i<_2.length;i++){var _5=_2[i];if(!_5.frozen){_5.frozen=true;_5.$74j=true;var _7=_5.getAutoFreezePosition?_5.getAutoFreezePosition():this.freezeLeft()?0:this.fields.length,_8=_5.getAutoFreezePosition?_5.getAutoFreezePosition():this.freezeLeft()?0:this.completeFields.length;_1.addAt(_5,_7);_6.addAt(_5,_8)}}}
this.fields.slideList(_1,this.freezeLeft()?0:this.fields.length);this.completeFields.slideList(_6,this.freezeLeft()?0:this.completeFields.length);this.resizeFieldsInRealTime=false}
for(var i=0;i<this.fields.length;i++){var _5=this.fields[i];_5.masterIndex=i}
if(_1){var _9=this.normalFields=[];for(var i=0;i<this.fields.length;i++){var _5=this.fields[i];if(!_5.frozen)_9.add(_5)}}else this.normalFields=null}
,isc.A.rebuildForFreeze=function isc_ListGrid_rebuildForFreeze(_1){if(!this.body)return;if(this.filterEditor){this.filterEditor.deriveVisibleFields();this.filterEditor.updateBody(_1);this.filterEditor.remapEditFieldsForFreeze();this.filterEditor.layoutChildren(this.$60m)}
this.endEditing();this.deriveVisibleFields();this.updateHeader();this.updateBody(_1);this.layoutChildren(this.$60m);if(this.frozenBody){this.frozenBody.markForRedraw("Recalculating draw area from initial sizing of body")}
if(this.summaryRow&&this.showGridSummary){this.summaryRow.setFields(this.completeFields.duplicate());this.summaryRow.rebuildForFreeze(_1);this.summaryRow.$855()}}
,isc.A.setCanFreezeFields=function isc_ListGrid_setCanFreezeFields(_1){this.canFreezeFields=_1;delete this.$60v}
,isc.A.refreshMasterIndex=function isc_ListGrid_refreshMasterIndex(){if(!this.body)return;if(this.header)this.$51c(this.header.getMembers());if(this.body)this.$51c(this.body.fields);if(!this.frozenFields)return;if(this.frozenHeader)this.$51c(this.frozenHeader.getMembers());if(this.frozenBody)this.$51c(this.frozenBody.fields)}
,isc.A.$51c=function isc_ListGrid__refreshMasterIndexForFields(_1){for(var i=0;i<_1.length;i++){_1[i].masterIndex=this.fields.findIndex("name",_1[i].name)}}
,isc.A.getFreezeOn=function isc_ListGrid_getFreezeOn(){return(this.freezeOn!=null?this.freezeOn:this.isRTL()?"right":"left")}
,isc.A.freezeLeft=function isc_ListGrid_freezeLeft(){return this.getFreezeOn()=="left"}
,isc.A.getLeftBody=function isc_ListGrid_getLeftBody(){return this.frozenFields&&this.freezeLeft()?this.frozenBody:this.body}
,isc.A.getRightBody=function isc_ListGrid_getRightBody(){return this.frozenFields&&!this.freezeLeft()?this.frozenBody:this.body}
,isc.A.setFreezeOn=function isc_ListGrid_setFreezeOn(_1){var _2=this.get
this.freezeOn=_1;if(_1!=_2)this.rebuildForFreeze(true)}
,isc.A.getFrozenSlots=function isc_ListGrid_getFrozenSlots(_1){var _2=this.frozenFields;if(!_2)return _1;return this.freezeLeft()?_1.slice(0,_2.length):_1.slice(_1.length-_2.length)}
,isc.A.getUnfrozenSlots=function isc_ListGrid_getUnfrozenSlots(_1){var _2=this.frozenFields;if(!_2)return _1;return this.freezeLeft()?_1.slice(_2.length):_1.slice(0,_1.length-_2.length)}
,isc.A.fieldIsFrozen=function isc_ListGrid_fieldIsFrozen(_1){if(!this.frozenFields)return false;var _2=this.getField(_1);return _2&&_2.frozen}
,isc.A.getFieldBody=function isc_ListGrid_getFieldBody(_1){if(!this.frozenFields)return this.body;return this.fieldIsFrozen(_1)?this.frozenBody:this.body}
,isc.A.getFieldHeader=function isc_ListGrid_getFieldHeader(_1){if(!this.frozenFields)return this.header;return this.fieldIsFrozen(_1)?this.frozenHeader:this.header}
,isc.A.getFieldHeaderButton=function isc_ListGrid_getFieldHeaderButton(_1){var _2=this.getFieldHeader(_1);return _2?_2.getMember(this.getLocalFieldNum(_1)):null}
,isc.A.getLocalFieldNum=function isc_ListGrid_getLocalFieldNum(_1){if(this.body&&this.body.isPrinting){var _2=this.fields[_1];if(!this.$54d&&_2.frozen&&this.frozenBody){return this.frozenBody.fields.indexOf(_2)}else{return this.body.fields.indexOf(_2)}}
if(!this.frozenFields)return _1;var _3;_3=this.frozenFields.length;if(!this.fieldIsFrozen(_1))return _1-_3;return this.freezeLeft()?_1:_1-(this.fields.length-this.frozenFields.length)}
,isc.A.getFieldNumFromLocal=function isc_ListGrid_getFieldNumFromLocal(_1,_2){if(this.isPrinting){var _3=this.frozenFields!=null?(_2==this.frozenBody?this.frozenFields:this.unfrozenFields):this.fields;var _4=_2.fields[_1];for(var i=0;i<_3.length;i++){if(_3[i].name==_4.name)break;if(_3[i].shouldPrint==false)_1++}}
if(!this.frozenFields)return _1;if(_2==this.frozenBody){if(this.freezeLeft())return _1;else{var _6=this.fields.length-this.frozenFields.length;return _1+_6}}else{var _7=this.freezeLeft()?this.frozenFields.length:0;return _1+_7}}
,isc.A.getColumnLeft=function isc_ListGrid_getColumnLeft(_1){var _2=this.getFieldBody(_1);return _2.getColumnLeft(this.getLocalFieldNum(_1))}
,isc.A.getColumnPageLeft=function isc_ListGrid_getColumnPageLeft(_1){var _2=this.getFieldBody(_1);return _2.getColumnPageLeft(this.getLocalFieldNum(_1))}
,isc.A.getColumnWidth=function isc_ListGrid_getColumnWidth(_1){var _2=this.getFieldBody(_1);return _2.getColumnWidth(this.getLocalFieldNum(_1))}
,isc.A.refreshCellStyle=function isc_ListGrid_refreshCellStyle(_1,_2,_3){var _4=this.getFieldBody(_2);return _4.refreshCellStyle(_1,this.getLocalFieldNum(_2),_3)}
);isc.evalBoundary;isc.B.push(isc.A.freezeField=function isc_ListGrid_freezeField(_1){return this.toggleFrozen(_1,true)}
,isc.A.unfreezeField=function isc_ListGrid_unfreezeField(_1){return this.toggleFrozen(_1,false)}
,isc.A.toggleFrozen=function isc_ListGrid_toggleFrozen(_1,_2){if(!isc.isAn.Array(_1))_1=[_1];var _3=false;for(var i=0;i<_1.length;i++){var _5=this.getField(_1[i]);if(!_5||!this.fieldIsVisible(_5)||this.isCheckboxField(_5))
{continue}
var _6=_2;if(_6==null)_6=!_5.frozen;if(_5.frozen==_2)continue;_3=true;_5.frozen=_6}
if(!_3)return false;this.rebuildForFreeze();this.handleViewStateChanged();return true}
,isc.A.updateBody=function isc_ListGrid_updateBody(_1){if(_1||(this.frozenFields&&!this.frozenBody)||(this.frozenBody&&!this.frozenFields))
{_1=true;this.dropRecordComponents();if(this.frozenFields){this.$62b=this.showRollOverCanvas;this.$62c=this.showSelectionCanvas;this.showRollOverCanvas=false;this.showSelectionCanvas=false;if(this.$62b)this.updateRollOverCanvas();if(this.$62c)this.updateSelectionCanvas()}else{if(this.$62b)this.showRollOverCanvas=true;if(this.$62c)this.showSelectionCanvas=true;delete this.$62b;delete this.$62c}
if(this.body){this.$810()}
if(this.bodyLayout)this.bodyLayout.destroy();if(this.body)this.body.destroy();this.body=this.bodyLayout=this.frozenBody=null;this.createBodies()}
if(this.isDrawn()){this.updateGridComponents()}
if(this.body){this.body.fields=this.normalFields||this.fields;if(!_1)this.$75n(this.body);this.body.markForRedraw("fields change")}
if(this.frozenBody){this.frozenBody.fields=this.frozenFields;if(!_1)this.$75n(this.frozenBody);this.frozenBody.markForRedraw("fields change")}}
,isc.A.shouldDestroyOnUnembed=function isc_ListGrid_shouldDestroyOnUnembed(_1,_2){if(_1.dontAutoDestroy)return false;if(_1.isBackgroundComponent){if(_2==this.$81l&&_1.creator==this)return true;return false}
if(_1.isRecordComponent){if(_2==this.$81l)return true;return false}
if(_1.destroyOnUnembed!=null)return _1.destroyOnUnembed;if(_2==this.$81p)return false;return true}
,isc.A.$810=function isc_ListGrid__destroyEmbeddedComponentsForRebuild(){var _1=this.body.$29a;if(this.frozenBody){var _2=[];if(_1==null)_1=_2;else _1=_2.addList(_1);_1.addList(this.frozenBody.$29a||[])}
if(_1&&_1.length>0){for(var i=0;i<_1.length;i++){var _4=_1[i].embeddedRecord,_5=false;if(_4.$29a){for(var j=0;j<_4.$29a.length;j++){var _7=_4.$29a[j];_5=_5||_7.isExpansionComponent;if(this.shouldDestroyOnUnembed(_7,this.$81q)){_7.markForDestroy()}else{_7.deparent()}}
_4.$29a=null;if(_5)_4.expanded=false;delete _4.$29a}}}}
,isc.A.createBodies=function isc_ListGrid_createBodies(){if(this.body!=null)return;this.$80i();this.body=this.createBody(this.ID+"_body",this.normalFields||this.fields);this.body.resizeTo("100%","100%");this.bodies=[this.body];this.dragScrollTarget=this.body;var _1=this.frozenFields;if(_1==null||_1.length==0){return this.body}
this.frozenBody=this.createBody(this.ID+"$51d",_1,true);if(this.body.hscrollOn&&!this.shrinkForFreeze){this.frozenBody.setEndSpace(this.body.getScrollbarSize())}
if(this.freezeLeft()){this.bodies.unshift(this.frozenBody)}else{this.bodies.add(this.frozenBody)}
this.bodyLayout=this.createAutoChild("bodyLayout",{width:"100%",height:"100%",autoDraw:false,members:this.bodies},isc.HLayout)}
,isc.A.bodyOverflowed=function isc_ListGrid_bodyOverflowed(){if(this.summaryRow!=null&&this.summaryRow.body){this.summaryRow.body.setRightSpace(this.body.vscrollOn?this.body.getScrollbarSize():0)}
if(!this.frozenBody)return;this.frozenBody.setEndSpace(this.body.hscrollOn&&!this.shrinkForFreeze?this.body.getScrollbarSize():0)}
,isc.A.createBody=function isc_ListGrid_createBody(_1,_2,_3){var _4=isc.ClassFactory.getClass(this.bodyConstructor).createRaw();_4.ID=_1;_4.autoDraw=false;_4.grid=this;_4.fields=_2;_4.frozen=_3;_4.overflow=_3?"hidden":this.bodyOverflow;_4.backgroundColor=this.bodyBackgroundColor;var _5=this.bodyStyleName;if(this.alternateBodyStyleName!=null&&this.alternateRecordStyles){_5=this.alternateBodyStyleName}
_4.styleName=_5;_4.allowContentAndChildren=true;_4.fixedRowHeights=this.fixedRecordHeights;_4.fixedColumnWidths=this.fixedFieldWidths;_4.alternateRowStyles=this.alternateRecordStyles;_4.alternateRowFrequency=this.alternateRecordFrequency;_4.canSelectText=this.canDragSelectText;_4.showAllRows=this.showAllRecords;if(this.virtualScrolling!=null)_4.virtualScrolling=this.virtualScrolling;if(_4.virtualScrolling)_4.fixedRowHeights=false;_4.dragTarget=this.canDragSelect?null:this;_4.dragAppearance=isc.EventHandler.NONE;_4.locatorParent=this;_4.selectionAppearance=this.selectionAppearance;var _6=isc.ListGrid.$295;for(var i=0;i<_6.length;i++){var _8=_6[i],_9=this[_8];if(_9!=null)_4[_8]=_9}
isc.addProperties(_4,this.bodyDefaults,this.bodyProperties);this.$51e(_4);if(_3)_4.showEmptyMessage=false;_4.completeCreation();return _4}
,isc.A.getRowHeight=function isc_ListGrid_getRowHeight(_1,_2){var _3=this.cellHeight;if(this.frozenFields&&this.getEditRow()==_2){var _4=this.getEditForm(),_5=_4?_4.getItems():[];for(var i=0;i<_5.length;i++){var _7=(_5[i].getHeight()+2*this.cellPadding);if(_7>_3)_3=_7}}
return this.body.updateHeightForEmbeddedComponents(_1,_2,_3)}
,isc.A.makeSelectionCanvas=function isc_ListGrid_makeSelectionCanvas(_1){this.selectionCanvas=this.createAutoChild("selectionCanvas",{eventProxy:this.body,snapTo:"TL",width:"100%",height:"100%",bubbleMouseEvents:true,destroyOnUnEmbed:false,percentSource:this,percentBox:"custom",grid:this,autoDraw:false});this.selectionUnderCanvas=this.createAutoChild("selectionUnderCanvas",{eventProxy:this.body,snapTo:"TL",width:"100%",height:"100%",bubbleMouseEvents:true,destroyOnUnEmbed:false,percentSource:this,percentBox:"custom",grid:this,autoDraw:false})}
,isc.A.getSelectionCanvas=function isc_ListGrid_getSelectionCanvas(){if(!this.selectionCanvas)this.makeSelectionCanvas();return this.selectionCanvas}
,isc.A.getSelectionUnderCanvas=function isc_ListGrid_getSelectionUnderCanvas(){if(!this.selectionUnderCanvas)this.makeSelectionCanvas();return this.selectionUnderCanvas}
,isc.A.updateSelectionCanvas=function isc_ListGrid_updateSelectionCanvas(){var _1=this.selection,_2,_3,_4;if(isc.isA.CellSelection(_1)){var _5=_1.getSelectedCells()[0];_2=_5?_5[0]:-1,_3=_5?_5[1]:-1;_4=_5?this.getCellRecord(_2,_3):null}else{var _4=this.getSelectedRecord();_2=this.data?this.data.indexOf(_4):-1}
var _6=this.selectionCanvas,_7=_6?_6.embeddedRecord:null,_8=_6?_6.$289:-1,_9=_6?_6.$57n:-1;if(this.selectionCanvas==null){if(_4==null||!this.showSelectionCanvas)return}else if(this.showSelectionCanvas&&_7==_4&&_8==_2&&_9==_3)
{return}
if(this.showSelectionCanvas&&this.selectionType!=isc.Selection.SINGLE&&this.selectionType!=isc.Selection.NONE)
{this.logWarn("showSelectionCanvas is set to true, but not supported for selectionType "+this.selectionType);this.showSelectionCanvas=false}
if(!_4||_2==-1||!this.showSelectionCanvas){if(_7){this.removeEmbeddedComponent(_7,_6);var _10=this.selectionUnderCanvas;this.removeEmbeddedComponent(_7,_10)}
return}
var _6=this.getSelectionCanvas(),_10=this.getSelectionUnderCanvas();_6.record=_4;_10.record=_4;this.addEmbeddedComponent(_6,_4,_2,_3,"within");this.addEmbeddedComponent(_10,_4,_2,_3,"within")}
,isc.A.makeRollOverCanvas=function isc_ListGrid_makeRollOverCanvas(){this.rollOverCanvas=this.createAutoChild("rollOverCanvas",{eventProxy:this.body,percentSource:this,percentBox:"custom",bubbleMouseEvents:true,destroyOnUnEmbed:false,grid:this,autoDraw:false});this.rollUnderCanvas=this.createAutoChild("rollUnderCanvas",{eventProxy:this.body,percentSource:this,percentBox:"custom",bubbleMouseEvents:true,destroyOnUnEmbed:false,grid:this,autoDraw:false})}
,isc.A.getRollOverCanvas=function isc_ListGrid_getRollOverCanvas(_1,_2){if(!this.rollOverCanvas)this.makeRollOverCanvas();return this.rollOverCanvas}
,isc.A.getRollUnderCanvas=function isc_ListGrid_getRollUnderCanvas(_1,_2){if(!this.rollUnderCanvas)this.makeRollOverCanvas();return this.rollUnderCanvas}
,isc.A.updateRollOverCanvas=function isc_ListGrid_updateRollOverCanvas(_1,_2,_3){var _4=false,_5;if(_3||!this.showRollOverCanvas||_1==-1){_4=true}else{var _5=this.getCellRecord(_1,_2);if(_5==null||Array.isLoading(_5)){_4=true}}
if(_4){if(this.rollOverCanvas){this.removeEmbeddedComponent(this.rollOverCanvas.embeddedRecord,this.rollOverCanvas);this.removeEmbeddedComponent(this.rollUnderCanvas.embeddedRecord,this.rollUnderCanvas)}
return}
if(!this.useCellRollOvers)_2=null;var _6=this.rollOverCanvas=this.getRollOverCanvas(_1,_2),_7=this.rollUnderCanvas=this.getRollUnderCanvas(_1,_2);_6.record=_5;_7.record=_5;this.addEmbeddedComponent(_6,_5,_1,_2,"within");this.addEmbeddedComponent(_7,_5,_1,_2,"within")}
,isc.A.updateEmbeddedComponentZIndex=function isc_ListGrid_updateEmbeddedComponentZIndex(_1){if(!_1)return;if(this.selectionCanvas==_1){var _2=this.body.getTableZIndex();this.selectionCanvas.setZIndex(_2+50)}
if(this.selectionUnderCanvas==_1){var _2=this.body.getTableZIndex();this.selectionUnderCanvas.setZIndex(_2-100)}
if(this.rollOverCanvas==_1){var _2=this.body.getTableZIndex();_1.setZIndex(_2+100)}
if(this.rollUnderCanvas==_1){var _2=this.body.getTableZIndex();_1.setZIndex(_2-50)}}
,isc.A.$80d=function isc_ListGrid__handleEmbeddedComponentResize(_1,_2,_3,_4){if(this.frozenBody!=null&&_4!=null&&_4!=0){var _5=(_1==this.frozenBody)?this.body:this.frozenBody;_5.markForRedraw("Embedded component requires row resizing")}}
,isc.A.getButtonProperties=function isc_ListGrid_getButtonProperties(){var _1={align:null};if(this.headerTitleStyle!=null)_1.titleStyle=this.headerTitleStyle;if(this.headerBaseStyle!=null)_1.baseStyle=this.headerBaseStyle;if(this.headerButtonSrc!=null)_1.src=this.headerButtonSrc;if(this.frozenHeaderBaseStyle!=null)
_1.frozenBaseStyle=this.frozenHeaderBaseStyle;if(this.frozenHeaderTitleStyle!=null)
_1.frozenTitleStyle=this.frozenHeaderTitleStyle;if(this.frozenHeaderButtonSrc!=null)
_1.frozenSrc=this.frozenHeaderButtonSrc;var _2=isc.addProperties({},this.headerButtonDefaults,_1,this.headerButtonProperties);return _2}
,isc.A.getHeaderButtonProperties=function isc_ListGrid_getHeaderButtonProperties(_1){var _2=this.getButtonProperties();if(this.buttonTitleFunction==null){this.buttonTitleFunction=new Function("return this.parentElement.grid.getHeaderButtonTitle(this)")}
isc.addProperties(_2,{defaultRadioGroup:this.getID()+"$34f",getActionType:function(_9,_10,_11,_12){var _3=this.parentElement,_4;if(_3)_4=_3.grid;if(_4&&isc.isA.ListGrid(_4)){var _5=_4.fields[_3.getButtonNumber(this)];var _6=(_4.canSort!=false);if(_6&&_5!=null)_6=(_4.$66c(_5)!=false);if(_6)return isc.Button.RADIO}
return this.invokeSuper(null,"getActionType",_9,_10,_11,_12)},getTitle:this.buttonTitleFunction,mouseOver:function(){var _4=this.parentElement.grid;if(_4.shouldShowHeaderMenuButton(this)){var _7=_4.getHeaderMenuButton(this);this.addPeer(_7);_7.addProperties({dragTarget:this});if(this.zIndex>_7.zIndex)_7.bringToFront();if(!_7.isVisible()){_7.show()}}
return this.Super("mouseOver",arguments)},mouseOut:function(){var _4=this.parentElement.grid,_8=_4.headerMenuButton;if(_8&&_8.isVisible()&&_8.masterElement==this&&isc.EH.getTarget()!=_8)
{_4.headerMenuButton.hide()}
return this.Super("mouseOut",arguments)}},_1);return _2}
,isc.A.makeHeader=function isc_ListGrid_makeHeader(){if(this.header!=null)return;if(this.headerSpans){this.spanMap={};for(var i=0;i<this.headerSpans.length;i++){var _2=this.headerSpans[i];for(var j=0;j<_2.fields.length;j++){this.spanMap[_2.fields[j]]=_2}}}
var _4=this.header=this.makeHeaderForFields(this.normalFields||this.fields);_4.locatorParent=this;if(this.sorter==null)this.makeCornerSortButton();this.headers=[_4];if(this.frozenFields){var _5=this.frozenHeader=this.makeHeaderForFields(this.frozenFields,"visible",this.getID()+"$51f");if(this.freezeLeft()){this.headers.unshift(this.frozenHeader)}else{this.headers.add(this.frozenHeader)}
var _6=this.headers.duplicate();this.headerLayout=this.createAutoChild("headerLayout",{autoDraw:false,overflow:"hidden",height:this.headerHeight,members:_6},isc.HLayout)}
var _7=this.headerLayout||this.header;this.observe(_7,"moved","observer.updateSorter()");this.observe(_7,"resized","observer.updateSorter()")}
,isc.A.updateSorter=function isc_ListGrid_updateSorter(){if(!this.sorter)return;if(this.$rm||this.$qz){this.delayCall("updateSorter");return}
var _1=(this.header||this.headerLayout)&&this.$313();if(_1){this.sorter.moveTo(this.getSorterLeft(),this.getSorterTop())}else{this.sorter.moveTo(0,0)}
this.sorter.setVisibility(_1?isc.Canvas.INHERIT:isc.Canvas.HIDDEN);if(_1&&this.isDrawn()&&!this.sorter.isDrawn())this.sorter.draw()}
,isc.A.makeHeaderForFields=function isc_ListGrid_makeHeaderForFields(_1,_2,_3){var _4=this.createHeader({ID:_3,grid:this,buttons:_1.duplicate(),reorderOnDrop:false,height:null,minMemberSize:this.minFieldWidth,tabWithinToolbar:false,overflow:_2||"hidden",_redrawWithParent:!this.fixedFieldWidths})
this.observe(_4,"scrollTo","observer.headerScrolled()");return _4}
,isc.A.createHeader=function isc_ListGrid_createHeader(_1){var _2=this;var _3=this.canTabToHeader||(this.canTabToHeader==null&&isc.screenReader);var _4=isc.addProperties({makeButton:function(_6,_26,_27,_28,_29){_6=isc.addProperties({},_6);if(_6.headerTitleStyle)_6.titleStyle=_6.headerTitleStyle;else if(_6.frozen&&this.buttonProperties.frozenTitleStyle)
_6.titleStyle=_6.frozenTitleStyle;if(_6.frozen&&this.buttonProperties.frozenSrc)
_6.src=this.buttonProperties.frozenSrc;_6.showIf=null;_6.grid=_2;if(_2.shouldAutoFitField(_6)){var _5=_2.getAutoFitWidthApproach(_6);if(_5=="title"||_5=="both"){_6.overflow="visible";_6.resized=function(){if(this.isDrawn()&&this.grid){this.grid.headerButtonResized(this)}}}}
if(_6.$776){_6.width=_6.$776}else if(_6.width==null&&_2.shouldAutoFitField(_6)){_6.width=this.minFieldWidth||1}
_6.sizeImageToFitOverflow=true;var _6=this.Super("makeButton",[_6,_26,_27,_28,_29]);var _7;if(_6.headerBaseStyle){_6.baseStyle=_6.headerBaseStyle;_7=true}else if(!_7){if(_6.frozen&&this.buttonProperties.frozenBaseStyle)
_6.setBaseStyle(this.buttonProperties.frozenBaseStyle);else if(this.buttonProperties.baseStyle!=null)
_6.setBaseStyle(this.buttonProperties.baseStyle)}
return _6},buttonProperties:this.getHeaderButtonProperties(),border:this.headerBorder,styleName:this.headerBarStyle,backgroundColor:this.headerBackgroundColor,backgroundImage:this.headerBackgroundImage,canResizeItems:this.canResizeFields,canReorderItems:this.canReorderFields,dontObserve:false,childVisibilityChanged:function(){this.Super("childVisibilityChanged",arguments);this.$55g()},$3j:function(){this.Super("$3j",arguments);this.$55g()},spannedFields:Array.create({sortUnique:true}),addSpan:function(_18,_26){if(!this.$55h)this.$55h=[];this.$55h.add(_18);this.spannedFields.addList(_26);if(this.isDrawn()){this.$55i(_18);this.addChild(_18)}},$55g:function(){if(!this.$55h||!this.isDrawn())return;var _8=this.instantRelayout;this.instantRelayout=false;for(var i=0;i<this.$55h.length;i++)this.$55i(this.$55h[i]);this.instantRelayout=_8},$82w:function(_18){if(_18.$82y!=null){return _18.$82y}
var _10=[],_11=_18.fields.length,_12=true;for(var i=0;i<this.members.length;i++){if(_18.$55k[this.members[i].name]==true){_12=false;_10[_10.length]=this.members[i];_11--}
if(_11==0)break}
_18.$82y=_10;return _12?null:_10},$55i:function(_18){if(!this.isDrawn()){return}
if(_18.$55j){_18.setHeight(Math.floor(this.getHeight()/2))}
var _13=this.$82w(_18);if(!_13){if(this.isVisible()){_18.hide()}
return}
var _14,_15=0;for(var i=0;i<_13.length;i++){var _16=_13[i];_16.canReorder=false;_16.layoutAlign="bottom";if(_16.visibility!=isc.Canvas.HIDDEN){_15+=_16.getVisibleWidth();if(!_14||this.isRTL())_14=_16}}
if(_14){_18.setVisibility(isc.Canvas.INHERIT);_18.setLeft(_14.getLeft());_18.setWidth(_15)}},$58r:function(){if(!this.$55h)return;this.$55g();var _17=this.getVisibleHeight();for(var i=0;i<this.$55h.length;i++){var _18=this.$55h[i];this.addChild(_18);var _13=this.$82w(_18);if(!_13)continue;for(var j=0;j<_13.length;j++){var _20=_13[j],_21=_20.getVisibleHeight(),_22=_18.getVisibleHeight();if(_22+_21>_17)_20.setHeight(_21-_22)}
_18.bringToFront()}},removeButtons:function(_26){if(_2.headerSpans)_2.$82z(_26,this);this.Super("removeButtons",arguments)},addButtons:function(_26,_27){this.Super("addButtons",arguments);if(_2.headerSpans)_2.$60x(_26,this);this.$58r()},draw:function(){if(this.$55h&&_2.unspannedHeaderVAlign){for(var i=0;i<this.buttons.length;i++){var _6=this.buttons[i];if(!this.spannedFields.contains(_6.name))_6.valign=_2.unspannedHeaderVAlign}}
this.Super("draw",arguments);this.$58r()},getStandardChildLocator:function(_26){var _23=_26.masterIndex,_2=this.grid;if(_23!=null&&_2!=null){var _24=_2.getFieldName(_23);return isc.AutoTest.createLocatorFallbackPath("headerButton",{fieldName:_24})}
return this.Super("getStandardChildLocator",_26)},getChildFromLocatorSubstring:function(_26){if(isc.isA.Canvas(this[_26]))return this[_26];var _25=isc.AutoTest.parseLocatorFallbackPath(_26);if(this.grid&&_25&&_25.name=="headerButton"){var _24=_25.config.fieldName,_23=this.grid.getFieldNum(_24),_20=this.grid.getFieldHeader(_23);if(_23==-1||_20==null){this.logWarn("fieldName:"+_24+", gave fieldNum:"+_23+" unable to find header button for this field.");return}
if(_20!=this){this.logWarn("AutoTest locator string specified this header for field "+_24+" but that header button is contained in header "+_20+". This can happen if headers have been frozen / "+"unfrozen since the locator string was created. Redirecting to that widget.");return _20.getChildFromLocatorSubstring(_26)}
return this.members[this.grid.getLocalFieldNum(_23)]}
return this.Super("getChildFromLocatorSubstring",arguments)}},_1);if(!_3){_4.tabIndex=-1}
if(this.headerButtonConstructor!=null)
_4.buttonConstructor=this.headerButtonConstructor;var _20=this.createAutoChild("header",_4,isc.Toolbar);if(_20.canReorderItems&&!_20.dontObserve){this.observe(_20,"itemDragReordered","observer.headerDragReordered(itemNum,newPosition,this)")}
if(_20.canResizeItems&&!_20.dontObserve)this.$62l(_20);if(this.headerSpans)this.$60x(_1.buttons,_20);return _20}
,isc.A.headerButtonResized=function isc_ListGrid_headerButtonResized(_1){this.$45m("header button resized")}
,isc.A.$60x=function isc_ListGrid__addHeaderSpans(_1,_2){var _3=[],_4=_1.getProperty(this.fieldIdProperty);for(var i=0;i<this.headerSpans.length;i++){var _6=this.headerSpans[i];if(_4.intersect(_6.fields).length){_3.add(_6)}}
for(var i=0;i<_3.length;i++){var _6=_3[i];var _7=this,_8=_6.liveObject;if(_8==null||_8.destroyed||_8.destroying||_8.$65i){var _9=isc.addProperties(this.getButtonProperties(),{_constructor:_2.buttonConstructor,height:this.headerSpanHeight,align:"center",valign:this.headerSpanVAlign,showRollOver:false,showContextMenu:function(){return _7.headerSpanContextClick(this)}},_6);_8=_6.liveObject=this.createAutoChild("headerSpan",_9);_8.$55j=this.headerSpanHeight==null;if(_8.$55j){var _10=_6.height||(this.headerSpanProperties?this.headerSpanProperties.height:null)||(this.headerSpanDefaults?this.headerSpanDefaults.height:null);if(_10!=null)_8.$55j=false}}
_8.$55k={};for(var _11=0;_11<_6.fields.length;_11++){_8.$55k[_6.fields[_11]]=true}
_8.$82y=null;_2.addSpan(_8,_8.fields)}}
,isc.A.$82z=function isc_ListGrid__removeHeaderSpans(_1,_2){var _3=[],_4=_1.getProperty(this.fieldIdProperty);for(var i=0;i<this.headerSpans.length;i++){var _6=this.headerSpans[i];if(_4.intersect(_6.fields).length){var _7=_6.liveObject;if(_7)_7.$82y=null}}}
,isc.A.dirtyHeader=function isc_ListGrid_dirtyHeader(){if(this.header)this.header.markForRedraw()}
,isc.A.updateHeader=function isc_ListGrid_updateHeader(){var _1=this.frozenHeader?this.headerLayout:this.header;if(_1){_1.markForDestroy();_1.clear();if(this.showHeader){var _2=[this.frozenHeader,this.header];for(var i=0;i<_2.length;i++){if(_2[i]==null)continue;if(_2[i].$55h!=null){for(var _4=0;_4<_2[i].$55h.length;_4++){var _5=_2[i].$55h[_4];if(_5)_5.deparent()}}}}
_1.clearIDs()}
this.header=this.frozenHeader=this.headerLayout=null;if(this.headerMenuButton&&this.headerMenuButton.destroyed){this.headerMenuButton=null}
var _6=this.showHeader,_7;if(_6){_7=this.gridComponents.indexOf(this.$881);if(_7==-1){_6=false;this.logWarn("showHeader set to true, but header not included in grid header components."+" The header will not be shown.")}}
if(_6){this.makeHeader();if(this.isDrawn()){this.updateGridComponents()}}else if(this.sorter){this.sorter.hide()}}
,isc.A.setHeaderHeight=function isc_ListGrid_setHeaderHeight(_1){var _2=this.headerHeight;if(_2==_1)return;this.headerHeight=_1;if(this.showHeader==false)return;if(!this.header&&_1>0){var _3=this.gridComponents.indexOf("header");if(_3==-1){return}
if(this.isDrawn()){this.updateGridComponents()}}else{if(_2==0)this.updateHeader();this.layoutChildren(this.$616)}}
,isc.A.setShowHeader=function isc_ListGrid_setShowHeader(_1){if(_1==this.showHeader)return;this.showHeader=_1;this.updateHeader();this.layoutChildren(this.$617)}
,isc.A.shouldShowHeaderMenuButton=function isc_ListGrid_shouldShowHeaderMenuButton(_1){var _2;if(_1.showDefaultContextMenu==false){_2=false}else if(_1.showHeaderContextMenuButton!=null){_2=_1.showHeaderContextMenuButton}else{_2=this.showHeaderMenuButton}
if(this.showHeaderContextMenu&&_2){var _3=this.getHeaderContextMenuItems(_1.masterIndex);return(_3&&_3.length>0)}
return false}
,isc.A.getHeaderMenuButton=function isc_ListGrid_getHeaderMenuButton(_1){if(!this.headerMenuButton||this.headerMenuButton.destroyed){var _2={};if(this.headerMenuButtonHeight)_2.height=this.headerMenuButtonHeight;if(this.headerMenuButtonWidth)_2.width=this.headerMenuButtonWidth;if(this.headerMenuButtonIcon)_2.icon=this.headerMenuButtonIcon;if(this.headerMenuButtonIconHeight){_2.iconHeight=this.headerMenuButtonIconHeight}
if(this.headerMenuButtonIconWidth){_2.iconWidth=this.headerMenuButtonIconWidth}
if(this.headerMenuButtonBaseStyle){_2.baseStyle=this.headerMenuButtonBaseStyle}
if(this.headerMenuButtonTitleStyle){_2.titleStyle=this.headerMenuButtonTitleStyle}
if(this.headerMenuButtonSrc)_2.src=this.headerMenuButtonSrc;this.createAutoChild("headerMenuButton",_2,"Button",true)}
this.headerMenuButton.canDragResize=(_1&&_1.canDragResize!=null?_1.canDragResize&&this.canResizeFields:this.canResizeFields);this.headerMenuButton.addProperties({doubleClick:function(){this.creator.headerMenuButtonDoubleClick()}});return this.headerMenuButton}
,isc.A.headerMenuButtonClick=function isc_ListGrid_headerMenuButtonClick(){var _1=this.header,_2=_1.containsEvent()?_1.getMouseOverButtonIndex():-1;if(_2<0&&this.frozenHeader){_1=this.frozenHeader;_2=_1.getMouseOverButtonIndex()}
if(_2==-1)return;var _3=_1.getMember(_2);var _4=_3.getPageBottom(),_5=this.$314?this.$314.getVisibleWidth():isc.Menu.getPrototype().defaultWidth,_6=Math.max(_3.getPageLeft(),(_3.getPageRight()-_5));this.displayHeaderContextMenu(_3,[_6,_4]);this.headerMenuButton.bringToFront()}
,isc.A.headerMenuButtonDoubleClick=function isc_ListGrid_headerMenuButtonDoubleClick(){var _1=this.header,_2=_1.getMouseOverButtonIndex();if(_2==-1&&this.frozenHeader){_1=this.frozenHeader;_2=_1.getMouseOverButtonIndex()}
if(_2==-1)return;var _3=_1.getMember(_2);this.headerDoubleClick(_2,_1);isc.Menu.hideAllMenus()}
,isc.A.getHeaderButtonTitle=function isc_ListGrid_getHeaderButtonTitle(_1){var _2=_1;if(isc.isA.Object(_1))_2=_1.masterIndex;var _3=this.fields[_2];if(!_3)return"";var _4=_3[this.fieldIdProperty],_5=this.isSortField(_4),_6=(_5)&&(!this.showSortArrow||this.showSortArrow==isc.ListGrid.FIELD||this.showSortArrow==isc.ListGrid.BOTH),_7=(this.showSortNumerals==false?false:_5&&this.getSortFieldCount()>1),_8=this.getFieldTitle(_2);var _9;if(_7){var _10=this.getSortSpecifier(_4);_9=_10.sortIndex}
var _11=_8+(_6?"&nbsp;"+this.getSortArrowImage(_2):"")+(_7?"&nbsp;"+this.getSortNumeralHTML(_4,_9):"");if(this.$913(_1)&&this.shouldShowHeaderMenuButton(_1)){_11+=isc.Canvas.spacerHTML(this.headerMenuButtonWidth,1)}
return _3.showTitle!=false?_11:isc.nbsp}
,isc.A.$913=function isc_ListGrid__shouldLeaveHeaderMenuButtonSpace(_1){if(this.leaveHeaderMenuButtonSpace!=null)return this.leaveHeaderMenuButtonSpace;else return _1.align!=isc.Canvas.CENTER}
,isc.A.$63m=function isc_ListGrid__setCheckboxHeaderState(_1){var _2=this.getCheckboxFieldPosition(),_3=this.fields[_2];if(!this.isCheckboxField(_3))return;var _4=_1?(this.checkboxFieldTrueImage||this.booleanTrueImage):(this.checkboxFieldFalseImage||this.booleanFalseImage),_5=(this.canSelectAll==false||this.selectionType=="single")?"&nbsp;":this.getValueIconHTML(_4,_3);this.setFieldTitle(_2,_5);_3.$63h=_1}
,isc.A.updateCheckboxHeaderState=function isc_ListGrid_updateCheckboxHeaderState(){var _1=(isc.isAn.Array(this.data)||(isc.isA.ResultSet(this.data)&&this.data.allMatchingRowsCached())),_2=this.getSelection()||[];if(_1){if(_2.length==this.data.getLength()){this.$63m(true)}else{this.$63m(false)}}}
,isc.A.$666=function isc_ListGrid__headerClick(_1,_2){var _3=_2.getMember(_1).masterIndex;return this.headerClick(_3,_2)}
,isc.A.headerClick=function isc_ListGrid_headerClick(_1,_2){if(this.onHeaderClick&&(this.onHeaderClick(_1,_2)==false)){return}
var _3=this.fields[_1];if(this.isCheckboxField(_3)&&this.selectionType!="single"&&this.canSelectAll!=false){if(_3.$63h){this.deselectAllRecords();this.$63m(false)}else{this.selectAllRecords();if(!isc.ResultSet||!isc.isA.ResultSet(this.data)||this.data.allMatchingRowsCached())
{this.$63m(true)}}
return false}
if(this.canAutoFitFields&&this.headerAutoFitEvent=="click"){this.autoFitField(_1)}
if(!this.$66c(_3))return false;var _4=_3[this.fieldIdProperty],_5=this.isSortField(_4)?this.getSortSpecifier(_4):null,_6=isc.EventHandler,_7=_6.getKey(),_8=_6.shiftKeyDown(),_9=this.$765,_10=_3.sortDirection;if(_8&&this.$91d()&&this.$91k(_3)){if(this.isSortField(_4)){if(_10==_9||this.canUnsort==false){this.toggleSort(_4)}else{this.toggleSort(_4,"unsort")}}else{this.$73z(_3)}}else{if(this.isSortField(_4)&&this.getSortFieldCount()==1){if(_10==_9||this.canUnsort==false){this.toggleSort(_4)}else{this.toggleSort(_4,"unsort")}}else{var _11=this.$766(_3);this.setSort([{property:_4,direction:_11}])}}
return false}
,isc.A.$774=function isc_ListGrid__headerDoubleClick(_1,_2){var _3=_2.getMember(_1).masterIndex;return this.headerDoubleClick(_3,_2)}
,isc.A.headerDoubleClick=function isc_ListGrid_headerDoubleClick(_1,_2){var _3=this.fields[_1];if(this.isCheckboxField(_3))return;if(this.canAutoFitFields&&this.headerAutoFitEvent=="doubleClick"){this.autoFitField(_1)}
return false}
,isc.A.fieldDragResizeStart=function isc_ListGrid_fieldDragResizeStart(){this.makeDragLine();this._dragLine.resizeTo(2,this.getVisibleHeight()-(this.body.hscrollOn?this.body.getScrollbarSize():0));var _1=this.ns.EH.dragTarget;var _2=this.getField(_1.masterIndex);if(_2)_2.autoFitWidth=false;_1.setWidth(_1.getVisibleWidth());_1.setOverflow("hidden")}
,isc.A.fieldDragResizeMove=function isc_ListGrid_fieldDragResizeMove(_1){var _2=this.ns.EH,_3=_2.dragTarget;var _4=this.header.$6j;this.showDragLineForField(_4);if(this.resizeFieldsInRealTime){var _5=_2.dragResizeWidth;this.$26a[_4]=_5;this.$54e=true;this.setBodyFieldWidths(this.$26a);this.body.useQuickDrawAheadRatio=true;this.body.redraw("fieldDragResize");delete this.body.useQuickDrawAheadRatio;delete this.body.$54e;var _6=this.body.getScrollLeft();if(_6>0)this.header.scrollTo(_6,null,"headerResize")}}
,isc.A.fieldDragResizeStop=function isc_ListGrid_fieldDragResizeStop(){this.hideDragLine()}
,isc.A.headerDragReordered=function isc_ListGrid_headerDragReordered(_1,_2,_3){var _4=_3.getMember(_1).masterIndex,_5=_3.getMember(_2).masterIndex;this.reorderField(_4,_5)}
,isc.A.reorderField=function isc_ListGrid_reorderField(_1,_2){this.reorderFields(_1,_1+1,_2-_1)}
,isc.A.reorderFields=function isc_ListGrid_reorderFields(_1,_2,_3){if(this.showFilterEditor&&this.filterEditor!=null){this.filterEditor.reorderFields(_1,_2,_3)}
if(this.summaryRow&&this.showGridSummary){this.summaryRow.reorderFields(_1,_2,_3)}
var _4=this.fields[_1],_5=this.fields[_2-1],_6=_1+_3,_7=this.fields[_6];var _8=this.$60z(),_9=this.fields[_8];if(this.$26a!=null)this.$26a.slideRange(_1,_2,_1+_3);if(this.completeFields==null)this.setFields(this.fields);if(this.completeFields.contains(_7)){var _10=this.completeFields.indexOf(_4),_11=this.completeFields.indexOf(_5)+1,_12=this.completeFields.indexOf(_7);this.completeFields.slideRange(_10,_11,_12)}
this.deriveVisibleFields();if(_8!=null){_8=this.fields.indexOf(_9);if(_8==-1)_8=null;this.$600(_8)}
var _13;if(!this.frozenFields){_13=this.getFieldHeader(_1);if(_13){_13.reorderItems(this.getLocalFieldNum(_1),this.getLocalFieldNum(_2),this.getLocalFieldNum(_1+_3))}}else{_13=this.getFieldHeader(_1);this.rebuildForFreeze();this.syncHeaderScrolling(this.body.getScrollLeft())}
if(_8!=null&&_13)
_13.selectButton(this.getLocalFieldNum(_8));var _14=this.$30a;if(_14){var _15=this.$286,_16=this.getEditRow(),_17=this.getEditField(),_18=[]
for(var i=0;i<_15.items.length;i++){var _20=_15.items[i];_20.colNum=this.fields.findIndex(this.fieldIdProperty,_20.getFieldName());for(var j=0;j<=_18.length;j++){if(_18[j]==null||_18[j].colNum>_20.colNum){_18.addAt(_20,j);break}}}
_15.items.setArray(_18);this.$30u=this.fields.indexOf(_17)}
var _22=this.body;if(this.frozenFields&&_1<this.frozenFields.length){_22=this.frozenBody}
if(_22){this.body.fields=this.normalFields||this.fields;if(this.frozenBody)this.frozenBody.fields=this.frozenFields;this.setBodyFieldWidths(this.$26a);if(_22.$29a!=null){this.$75n(_22)}
if(!_22.isDirty())this.$25a("reorderFields")}
this.handleFieldStateChanged()}
,isc.A.remapEditFieldsForFreeze=function isc_ListGrid_remapEditFieldsForFreeze(){if(this.$30a){var _1=this.getEditForm(),_2=_1.getItems();for(var i=0;i<_2.length;i++){var _4=_2[i];_4.colNum=this.fields.findIndex(this.fieldIdProperty,_4.getFieldName());var _5=this.fields[_4.colNum];if(!this.$54d&&_5.frozen)_4.containerWidget=this.frozenBody;else _4.containerWidget=this.body}}}
,isc.A.headerDragResized=function isc_ListGrid_headerDragResized(_1,_2,_3){var _4=_3.getMember(_1).masterIndex;this.resizeField(_4,_2,true)}
,isc.A.setCanResizeFields=function isc_ListGrid_setCanResizeFields(_1){if(this.canResizeFields==_1)return;this.canResizeFields=_1;if(this.header)this.header.setCanResizeItems(_1);if(this.frozenHeader)this.frozenHeader.setCanResizeItems(_1);if(this.headerMenuButton)this.headerMenuButton.canDragResize=_1;if(_1){if(this.header&&!this.header.dontObserve)this.$62l(this.header);if(this.frozenHeader&&!this.frozenHeader.dontObserve)
this.$62l(this.frozenHeader)}else{if(this.header)this.$62m(this.header);if(this.frozenHeader)this.$62m(this.frozenHeader)}}
,isc.A.$62l=function isc_ListGrid__observeHeaderResize(_1){this.observe(_1,"itemDragResized","observer.headerDragResized(itemNum,newSize,this)");this.observe(_1,"dragResizeMemberStart","observer.fieldDragResizeStart()");this.observe(_1,"dragResizeMemberMove","observer.fieldDragResizeMove()");this.observe(_1,"dragResizeMemberStop","observer.fieldDragResizeStop()")}
,isc.A.$62m=function isc_ListGrid__ignoreHeaderResize(_1){if(this.isObserving(_1,"itemDragResized"))
this.ignore(_1,"itemDragResized");if(this.isObserving(_1,"dragResizeMemberStart"))
this.ignore(_1,"dragResizeMemberStart");if(this.isObserving(_1,"dragResizeMemberMove"))
this.ignore(_1,"dragResizeMemberMove");if(this.isObserving(_1,"dragResizeMemberStop"))
this.ignore(_1,"dragResizeMemberStop")}
,isc.A.resizeField=function isc_ListGrid_resizeField(_1,_2,_3){if(!isc.isA.Number(_1))_1=this.getFieldNum(_1);if(_1==-1)return;if(this.header&&this.header.isDrawn()){var _4=this.getFieldHeader(_1),_5=this.getLocalFieldNum(_1);_4.getMember(_5).setWidth(_2)}
if(_3==null||_3){this.fields[_1].width=_2;this.fields[_1].autoFitWidth=false}
if(!this.isDrawn())return;this.$26a[_1]=_2;this.$54e=true;this.setBodyFieldWidths(this.$26a);if(this.body.isDirty())this.body.redraw("setting body field widths");delete this.$54e;if(this.showFilterEditor&&this.filterEditor){this.filterEditor.resizeField(_1,_2,_3)}
if(this.summaryRow&&this.showGridSummary){this.summaryRow.resizeField(_1,_2,_3)}
this.handleFieldStateChanged()}
,isc.A.$313=function isc_ListGrid__showSortButton(){var _1=this.showSortArrow;if(_1!=null){return(_1==isc.ListGrid.CORNER||_1==isc.ListGrid.BOTH)}
return this.$54u()}
,isc.A.$54u=function isc_ListGrid__shouldLeaveScrollbarGap(){if(this.leaveScrollbarGap)return true;if(!this.body||this.isEmpty())return false;if(this.body.vscrollOn)return true;var _1=this.body,_2=this.headerHeight,_3=this.getInnerHeight()-this.body.getVMarginBorder()-
(this.showHeader?_2:0);if(this.autoFitData==isc.Canvas.VERTICAL||this.autoFitData==isc.Canvas.BOTH){_3=(this.getTotalRows()*this.cellHeight);var _4=this.getAutoFitMaxBodyHeight();if(_4&&_4<_3){_3=_4}
if(this.autoFitMaxRows&&(this.autoFitMaxRows*this.cellHeight)>_3){_3=(this.autoFitMaxRows*this.cellHeight)}}
if(_3<=0)return false;return this.predictScrollbarGap&&this.bodyOverflow==isc.Canvas.AUTO&&(this.getTotalRows()*this.cellHeight>_3)}
,isc.A.getSorterTop=function isc_ListGrid_getSorterTop(){return(this.headerLayout||this.header).getTop()}
,isc.A.getSorterLeft=function isc_ListGrid_getSorterLeft(){if(this.isRTL()){return this.getLeftMargin()+this.getLeftBorderSize()+this.getLeftPadding();}else{var _1=(this.headerLayout||this.header);return _1.getWidth()+_1.getLeft()}}
,isc.A.makeCornerSortButton=function isc_ListGrid_makeCornerSortButton(){this.sorter=this.createAutoChild("sorter",isc.addProperties(this.getButtonProperties(),this.sorterDefaults,{ID:this.getID()+"_sorter",width:this.getScrollbarSize(),height:this.headerHeight,backgroundColor:this.headerBackgroundColor,imgDir:this.widgetImgDir,visibility:(this.$313()?isc.Canvas.INHERIT:isc.Canvas.HIDDEN)}));this.addChild(this.sorter,"sorter",false)}
,isc.A.sorterContextClick=function isc_ListGrid_sorterContextClick(){var _1=this.showHeaderContextMenu;if(this.showCornerContextMenu!=null)_1=this.showCornerContextMenu;if(_1)return this.displayHeaderContextMenu(this.sorter)}
,isc.A.headerBarContextClick=function isc_ListGrid_headerBarContextClick(_1){var _2=this.showHeaderContextMenu;if(this.showCornerContextMenu!=null)_2=this.showCornerContextMenu;if(_2){return this.displayHeaderContextMenu(_1.getMember(_1.getMouseOverButtonIndex()))}}
,isc.A.headerSpanContextClick=function isc_ListGrid_headerSpanContextClick(_1){var _2=this.getHeaderSpanContextMenuItems(_1);if(!_2||_2.length==0)return false;if(!this.$58q){this.$58q=this.getMenuConstructor().create({items:_2})}else{this.$58q.setItems(_2)}
this.$58q.showContextMenu();return false}
,isc.A.displayHeaderContextMenu=function isc_ListGrid_displayHeaderContextMenu(_1,_2){if(!_1)return;var _3=this.fields[_1.masterIndex];if(_3&&_3.showDefaultContextMenu==false)return false;var _4=this.getHeaderContextMenuItems(_1.masterIndex);if(_4.length==0)return;if(!this.$314)this.$314=this.getHeaderContextMenu(_1);this.$314.setData(_4);this.$92m=true;if(_2!=null&&_2.length>0){this.$314.moveTo(0,0);this.$314.setVisibility("hidden");if(!this.$314.isDrawn())this.$314.draw();else this.$314.redraw();this.$314.placeNear(_2[0],_2[1]);this.$314.show();return false}else{return this.$314.showContextMenu(_1)}}
,isc.A.getToggleFreezeText=function isc_ListGrid_getToggleFreezeText(_1){var _2={field:_1,viewer:this,title:this.getSummaryTitle(_1)}
return(!this.$54d&&_1.frozen)?this.unfreezeFieldText.evalDynamicString(this,_2):this.freezeFieldText.evalDynamicString(this,_2)}
,isc.A.getGroupByText=function isc_ListGrid_getGroupByText(_1){var _2={field:_1,title:this.getSummaryTitle(_1),viewer:this}
return this.groupByText.evalDynamicString(this,_2)}
,isc.A.$58f=function isc_ListGrid__shouldGroupByField(_1){var _1=this.getField(_1);return!!(_1&&this.$58g(_1)&&this.data.getLength()<=this.groupByMaxRecords)}
,isc.A.$58g=function isc_ListGrid__canGroupByField(_1){var _1=this.getField(_1);return!!(_1&&((this.canGroupBy==true&&_1.canGroupBy!=false)||(this.canGroupBy!=false&&_1.canGroupBy==true)))}
);isc.evalBoundary;isc.B.push(isc.A.getHeaderSpanContextMenuItems=function isc_ListGrid_getHeaderSpanContextMenuItems(_1){if(!this.showTreeColumnPicker&&!this.showHeaderSpanContextMenu)return false;var _2=[{title:this.fieldVisibilitySubmenuTitle,submenu:this.getColumnPickerItems(),icon:"[SKINIMG]actions/column_preferences.png"}];var _3=this,_4;for(var i=0;i<_1.fields.length;i++){var _6=_1.fields[i],_7=this.getField(_6);if(_7&&!this.$54d&&_7.frozen){_4=true;break}}
if(this.canFreezeFields&&this.fields.length>1&&(_4||this.frozenFields==null||this.normalFields.length>_1.fields.length))
{_2.add({isSeparator:true});_2.add({title:(_4?"Unfreeze ":"Freeze ")+_1.title,grid:this,spanFields:_1.fields,frozen:_4,icon:(_4?"[SKINIMG]actions/unfreeze.png":"[SKINIMG]actions/freezeLeft.png"),click:function(){for(var i=0;i<this.spanFields.length;i++){_3.completeFields.find(_3.fieldIdProperty,this.spanFields[i]).frozen=!this.frozen}
_3.rebuildForFreeze()}})}
return _2}
,isc.A.shouldShowColumnPicker=function isc_ListGrid_shouldShowColumnPicker(_1){if(_1.treeField||_1.canHide===false)return false;var _2=this.getSummaryTitle(_1);if(_2==null||isc.isAn.emptyString(_2))return false;return true}
,isc.A.getColumnPickerItems=function isc_ListGrid_getColumnPickerItems(){var _1=[],_2=this;for(var i=0;i<this.completeFields.length;i++){var _4=this.completeFields[i];if(!this.shouldShowColumnPicker(_4))continue;var _5=this.getSummaryTitle(_4);var _6=this.spanMap?this.spanMap[_4.name]:null;if(this.showTreeColumnPicker&&_6){if(!_1.find("spanConfig",_6)){_1.add({title:_6.title,autoDismiss:false,spanConfig:_6,click:function(_16,_9,_17){var _7=this.spanConfig.fields;var _8=this.anySpannedFieldsVisible();if(_8)_2.hideFields(_7)
else _2.showFields(_7);_17.body.delayCall("markForRedraw")},anySpannedFieldsVisible:function(){var _7=this.spanConfig.fields;for(var i=0;i<_7.length;i++){if(_2.fieldIsVisible(_7[i]))return true}
return false},enableIf:function(_16,_17,_9){var _6=this.spanConfig;for(var i=0;i<_17.data.length;i++){var _9=_17.data[i];if(_2.fieldIsVisible(_9.fieldName)&&!_6.fields.contains(_9.fieldName))return true}
return false},checkIf:function(_16,_17,_9){return this.anySpannedFieldsVisible()}})}
_5="&nbsp;&nbsp;&nbsp;&nbsp;"+_5}
_1.add({title:_5,fieldName:_4.name,prompt:_4.prompt,autoDismiss:false,checkIf:function(_16,_17,_9){return _2.fieldIsVisible(this.fieldName)},enableIf:function(_16,_17,_9){return!(_2.fields.length==1&&_2.fieldIsVisible(this.fieldName))},click:function(_16,_9,_17){var _10=_17.$35q,_11=_10?_10.data.findIndex("groupItem",true):null,_12=_10?_10.data[_11]:null,_13=(_12&&_12.fieldName==_9.fieldName);if(_2.fieldIsVisible(this.fieldName)){var _14=_2.fields.length;if(_2.getCurrentCheckboxField()!=null)_14-=1;if(_14>1){_2.hideField(_9.fieldName)}
if(_14==1){var _15=_17.data.find("fieldName",_2.fields[0].name);_17.setItemEnabled(_15,false);this.$58s=_15}}else{_2.showField(_9.fieldName);if(this.$58s){_17.setItemEnabled(this.$58s,true);this.$58s=null}}
if(_13){_10.setItemEnabled(_11,_2.$58f(_12.fieldName))}
_17.body.delayCall("markForRedraw")}})}
return _1}
,isc.A.getColumnPickerMenu=function isc_ListGrid_getColumnPickerMenu(_1){var _2={canHover:true,showIcons:true,showHover:true,cellHoverHTML:function(_3){return _3.prompt},items:_1};return{title:this.fieldVisibilitySubmenuTitle,submenu:_2,icon:"[SKINIMG]actions/column_preferences.png"}}
,isc.A.getHeaderContextMenuItems=function isc_ListGrid_getHeaderContextMenuItems(_1){var _2=this.getField(_1);var _3=[],_4=false;var _5=this.$66c(_2);if(_5||!_2){if(_2){var _6=_1!=null?_1:this.$60z();_3[0]={title:this.sortFieldAscendingText,icon:"[SKINIMG]actions/sort_ascending.png",click:"menu.doSort("+_6+", 'ascending')"};_3[1]={title:this.sortFieldDescendingText,icon:"[SKINIMG]actions/sort_descending.png",click:"menu.doSort("+_6+", 'descending')"};_4=true}
if(this.$91d()){_3.add({title:this.configureSortText,click:"menu.grid.askForSort();"});_4=true;if(!_2||this.isSortField(_2[this.fieldIdProperty])){_3.add({title:_2?this.clearSortFieldText:this.clearAllSortingText,field:_2,enableIf:function(_14,_15,_16){return(_2||(!_2&&_15.grid.getSortFieldCount()>0))},click:_2?"menu.doSort("+_6+", 'unsort')":"menu.grid.clearSort();"});_4=true}}}
var _7=this.canAutoFitFields&&_2;if(_7){_3.add({title:this.autoFitAllText,click:"menu.grid.autoFitFields()"});_3.add({title:this.autoFitFieldText,click:"menu.grid.autoFitField("+_1+",true);"})}
if(!_2&&this.showFilterEditor){if(_4)_3.add({isSeparator:true});_3.add({title:this.clearFilterText,click:"menu.grid.setFilterEditorCriteria(null); menu.grid.filterByEditor();"});_4=true}
if(this.canPickFields&&this.completeFields.length>1){var _8=this.getColumnPickerItems();if(_8.length>1){if(_4)_3.add({isSeparator:true});_3.add(this.getColumnPickerMenu(_8));_4=true}}
var _9=_2&&this.$58g(_2),_10=(this.canGroupBy!=false)&&_2&&_2.canGroupBy!=false&&this.isGrouped;if(_4&&(_10||_9)){_3.add({isSeparator:true})}
if(_9){var _11=(!_2.getGroupValue?_2.groupingModes?_2.groupingModes:(_2.$62?_2.$62.groupingModes:false):false);var _12=null;if(_11){_12=[];for(var _13 in _11){_12.add({title:_11[_13],groupType:_13,targetField:_2,fieldName:_2.name,prompt:_2.prompt,checked:(_2.groupingMode==_13),click:function(_14,_15,_16){this.targetField.groupingMode=this.groupType;_16.$35q.groupField(_15)}})}}
_3.add({groupItem:true,title:this.getGroupByText(_2),fieldName:_2.name,targetField:_2,prompt:_2.prompt,icon:"[SKINIMG]actions/groupby.png",click:function(_14,_15,_16){this.targetField.groupingMode=this.targetField.defaultGroupingMode||null;_16.groupField(_15)},enabled:this.$58f(_2),canSelectParent:true,submenu:_12});_4=true}
if(_10){_3.add({title:this.ungroupText,click:"menu.ungroup()",icon:"[SKINIMG]actions/ungroup.png"});_4=true}
if(_2){if(this.canFreezeFields&&this.fields.length>1&&(_2.frozen||(this.frozenFields==null||this.normalFields.length>1))&&!(this.spanMap&&this.spanMap[_2.name])&&_2.canFreeze!=false)
{if(_4)_3.add({isSeparator:true});_3.add({title:this.getToggleFreezeText(_2),grid:this,field:_2,icon:(_2.frozen?"[SKINIMG]actions/unfreeze.png":"[SKINIMG]actions/freezeLeft.png"),click:"item.grid.toggleFrozen(this.field,!this.field.frozen)"});_4=true}}
if(this.canAddFormulaFields){if(_4)_3.add({isSeparator:true});if(_2&&_2.userFormula){_3.add({title:this.editFormulaFieldText,grid:this,field:_2,click:"item.grid.editFormulaField(item.field)",icon:"[SKINIMG]ListGrid/formula_menuItem.png"});_3.add({title:this.removeFormulaFieldText,grid:this,field:_2,click:"item.grid.removeField(item.field.name)",icon:"[SKINIMG]ListGrid/formula_menuItem.png"})}
_3.add({title:this.addFormulaFieldText,grid:this,click:"item.grid.addFormulaField()",icon:"[SKINIMG]ListGrid/formula_menuItem.png"});_4=true}
if(this.canAddSummaryFields){if(_4)_3.add({isSeparator:true});if(_2&&_2.userSummary){_3.add({title:this.editSummaryFieldText,grid:this,field:_2,click:"item.grid.editSummaryField(item.field)",icon:"[SKINIMG]ListGrid/formula_menuItem.png"});_3.add({title:this.removeSummaryFieldText,grid:this,field:_2,click:"item.grid.removeField(item.field.name)",icon:"[SKINIMG]ListGrid/formula_menuItem.png"})}
_3.add({title:this.addSummaryFieldText,grid:this,click:"item.grid.addSummaryField()",icon:"[SKINIMG]ListGrid/formula_menuItem.png"})}
return _3}
,isc.A.getHeaderContextMenu=function isc_ListGrid_getHeaderContextMenu(){return this.createAutoChild("headerContextMenu",{ID:this.getID()+"$314",grid:this})}
,isc.A.getSortArrowImage=function isc_ListGrid_getSortArrowImage(_1){var _2;if(_1==null||_1==_2)_1=this.$60z();if(this.showSortArrow!=false&&_1!=null){return this.imgHTML(Array.shouldSortAscending(this.getField(_1).sortDirection)?this.sortAscendingImage:this.sortDescendingImage,null,null,null,null,this.widgetImgDir)}else{return isc.Canvas.spacerHTML(1,1)}}
,isc.A.sorterClick=function isc_ListGrid_sorterClick(){var _1=this.$60z();if(!this.$66c(_1))return false;if(_1!=null){var _2=this.getField(_1),_3=_2[this.fieldIdProperty],_4=this.$765,_5=this.$766(_2);if(this.isSortField(_3)){if(_4==_5||this.canUnsort==false){this.toggleSort(_3)}else{this.toggleSort(_3,"unsort")}}else{this.setSort({property:_3,direction:_4})}}else{this.sort(_1,(_1!=null?!Array.shouldSortAscending(this.getField(_1).sortDirection):null))}}
,isc.A.showDragLineForRecord=function isc_ListGrid_showDragLineForRecord(_1,_2){var _3=this.body;if(_1==null)_1=this.getEventRecordNum();var _4=_3.getVisibleRows()[1];if(_1==-2)_1=_4;if(this._dragLine&&!this.recordIsEnabled(_1)&&_1!=_4)return this._dragLine.hide();if(_2==null)_2=this.getReorderPosition(_1);var _5=this.getLeftBody();var _6=_5.getPageLeft()+(this.isRTL()&&_5.vscrollOn?_5.getScrollbarSize():0);if(_2==isc.ListGrid.BEFORE){this.showHDragLine(_6,_3.getRowPageTop(_1));this._dragLine.afterRow=_1-1}else if(_2==isc.ListGrid.AFTER){this.showHDragLine(_6,(_3.getRowPageTop(_1)+_3.getRowSize(_1)));this._dragLine.afterRow=_1}else{return this._dragLine.hide()}
if(this.recordDropMove){this.fireCallback("recordDropMove","viewer,recordNum,record,position",[this,_1,this.getRecord(_1),_2])}}
,isc.A.showHDragLine=function isc_ListGrid_showHDragLine(_1,_2){this.makeDragLine();this._dragLine.resizeTo(this.getViewportWidth()-(this.body.vscrollOn?this.body.getScrollbarSize():0),2);var _3=this.body.getPageTop(),_4=_3+this.body.getVisibleHeight();if(_2<_3)_2=_3;else if(_2>_4)_2=_4;this._dragLine.setPageRect(_1,_2);this._dragLine.show();this._dragLine.bringToFront()}
,isc.A.showDragLineForField=function isc_ListGrid_showDragLineForField(_1,_2){this.makeDragLine();if(_2==true){this._dragLine.resizeTo(2,this.headerHeight)}else{this._dragLine.resizeTo(2,this.getOuterViewportHeight())}
if(_1==null)_1=this.getEventFieldNum();if(_1<0){this._dragLine.hide();return}
var _3=this.ns.EH.dragTarget,_4=(!this.isRTL()?_3.getPageLeft()+this.ns.EH.dragResizeWidth:_3.getPageRight()-this.ns.EH.dragResizeWidth)-1;this._dragLine.setPageRect(_4,this.getPageTop()+(this.showFilterEditor?this.filterEditorHeight:0));this._dragLine.bringToFront();this._dragLine.show()}
,isc.A.unsort=function isc_ListGrid_unsort(){this.setSort(null)}
,isc.A.resort=function isc_ListGrid_resort(){if(this.$73p)return this.setSort(isc.shallowClone(this.$73p));var _1=this.$60z();if(_1!=null){var _2=this.getField(_1),_3=this.$766(_2);this.sort(_1,_3)}}
,isc.A.sort=function isc_ListGrid_sort(_1,_2){var _3,_4;if(isc.isA.String(_1)){_3=_1;_1=this.getFieldNum(_3);if(_1<0){_4=this.getUnderlyingField(_3)}}
if(!_4){var _5=this.$60z(),_6=(_5!=null?Array.shouldSortAscending(this.getField(_5).sortDirection):null);if(_1==null){if(_5!=null){_1=_5}else{for(var i=0;i<this.fields.length;i++){if(this.$91k(this.fields[i])!=false){_1=i;break}}}}
if(_1==null)return false;_4=this.getField(_1)}
if(_4==null){return}
if(_2==null){_2=this.$766(_4)}
if(_5==_1&&_2==_4.sortDirection)return;var _8={property:_4[this.fieldIdProperty],direction:Array.shouldSortAscending(_2)?"ascending":"descending"};return this.setSort([_8])}
,isc.A.$60z=function isc_ListGrid__getSortFieldNum(){if(this.sortFieldNum!=null)return this.sortFieldNum;if(this.sortField!=null){var _1=this.getFieldNum(this.sortField);if(_1==-1)_1=null;this.sortFieldNum=_1;var _2=this.getFieldName(this.sortFieldNum);if(_2)this.sortField=_2;return _1}
return null}
,isc.A.$600=function isc_ListGrid__setSortFieldNum(_1){this.sortFieldNum=_1;var _2=this.getFieldName(_1);this.sortField=_2}
,isc.A.getSortField=function isc_ListGrid_getSortField(){return this.sortField}
,isc.A.getUnderlyingField=function isc_ListGrid_getUnderlyingField(_1){if(!this.fields&&!this.completeFields&&!this.dataSource){this.logWarn("fields and completeFields are null and there is no DataSource");return null}
var _2=null;if(this.fields){_2=isc.Class.getArrayItem(_1,this.fields,this.fieldIdProperty)}
if(!_2&&this.completeFields){_2=isc.Class.getArrayItem(_1,this.completeFields,this.fieldIdProperty)}
if(!_2&&this.dataSource){if(!isc.isA.DataSource(this.dataSource))this.dataSource=this.getDataSource(this.dataSource);_2=this.dataSource.getField(_1)}
return _2}
,isc.A.toggleSort=function isc_ListGrid_toggleSort(_1,_2){var _3=this.getSpecifiedField(_1),_4=this.$73p?isc.shallowClone(this.$73p):[],_5=_4.find("property",_1);if(!_5&&_3.displayField){_5=_4.find("property",_3.displayField);if(!_5)return}
if(!_2)
_2=Array.shouldSortAscending(_5.direction)?"descending":"ascending";if(_2=="unsort")_4.remove(_5);else _5.direction=_2;this.setSort(_4)}
,isc.A.$73z=function isc_ListGrid__addSort(_1,_2){if(!isc.isAn.Object(_1)){_1=this.getSpecifiedField(_1)}
var _3=_2||this.$766(_1);this.addSort({property:_1[this.fieldIdProperty],direction:Array.shouldSortAscending(_3)?"ascending":"descending"})}
,isc.A.addSort=function isc_ListGrid_addSort(_1){var _2=this.$73p?isc.shallowClone(this.$73p):[];_2.add(_1);this.setSort(_2)}
,isc.A.getSort=function isc_ListGrid_getSort(){if(this.$73p){return isc.shallowClone(this.$73p)}else if(this.initialSort){return isc.shallowClone(this.initialSort)}else{var _1;if(this.sortFieldNum!=null)_1=this.getField(this.sortFieldNum);if(!_1&&this.sortField!=null){_1=this.getUnderlyingField(this.sortField)}
if(_1){var _2=this.$766(_1);return[{property:_1[this.fieldIdProperty],direction:_2}]}}}
,isc.A.getSortSpecifier=function isc_ListGrid_getSortSpecifier(_1){var _2=null;if(this.$73p&&this.$73p.length>0){_2=this.$73p.find("property",_1);if(!_2){var _3=this.getSpecifiedField(_1);if(_3&&_3.displayField&&!_3.optionDataSource){_2=this.$73p.find("property",_3.displayField)}}}
return _2}
,isc.A.getSortFieldCount=function isc_ListGrid_getSortFieldCount(){return this.$73p?this.$73p.length:0}
,isc.A.isSortField=function isc_ListGrid_isSortField(_1){var _2=this.getSortSpecifier(_1)?true:false;if(!_2){var _3=this.getSpecifiedField(_1);if(_3&&_3.displayField&&!_3.optionDataSource){_2=this.getSortSpecifier(_3.displayField)?true:false}}
return _2}
,isc.A.getSortNumeralHTML=function isc_ListGrid_getSortNumeralHTML(_1,_2){return"<span class="+this.sortNumeralStyle+">"+(_2+1)+"</span>"}
,isc.A.clearSort=function isc_ListGrid_clearSort(){this.setSort(null)}
,isc.A.setSort=function isc_ListGrid_setSort(_1){if(this.logIsInfoEnabled("sorting"))this.logInfo("Entering setSort","sorting");if(!this.fields&&!this.getDataSource()){if(this.logIsInfoEnabled("sorting")){this.logInfo("setSort() not performing sort - no fields","sorting")}
return false}
if(this.confirmDiscardEdits&&this.dataSource!=null){if(this.hasChanges()&&isc.ResultSet&&isc.isA.ResultSet(this.data)&&!this.data.allMatchingRowsCached())
{this.showLostEditsConfirmation({target:this,methodName:"$34g"},{target:this,methodName:"$34h"});this.$34i=_1
return}}
var _2=[],_3;if(this.$73p){for(var i=0;i<this.$73p.length;i++){var _5=this.$73p[i],_6=_1?_1.find("property",_5.property):null;if(!_6){_2.add(isc.shallowClone(_5));if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort - marking field "+_5.property+" for removal","sorting")}}}}
_1=_1||[];if(!_1||_1.length==0){if(this.logIsInfoEnabled("sorting")){this.logInfo("null or zero-length sortSpecifiers - unsorting only","sorting")}}
var _7=[];for(var i=0;i<_1.length;i++){var _8=_1[i],_9=this.getUnderlyingField(_8.property),_10=this.getField(_8.property),_11=null;if(_9){if(!this.$91k(_9)){isc.logWarn("Removing field '"+_9[this.fieldIdProperty]+"' from the "+"sortSpecifier array because it specifies canSortClientOnly: true and all "+"data is not yet client-side.","sorting");continue}
if(_10){if(!_3)_3=_10}
if(!_8.normalizer){if(_9.sortNormalizer){_11=_9.sortNormalizer}else if(_9.valueMap){_11=isc.isA.String(_9.valueMap)?this.getGlobalReference(_9.valueMap):_9.valueMap}else if(_9.type!=null){_11=_9.type}
_8.normalizer=_11}
if(_8.direction==null){_8.direction=this.$766(_8[this.fieldIdProperty])}
if(_3&&_3==_10){_8.primarySort=true}
if(_8.primarySort){this.sortDirection=Array.shouldSortAscending(_8.direction)}
if(!_8.context)_8.context=this;_8.sortIndex=i;_7.add(_8)}}
this.$73p=_7;var _12=this.$73p[0],_13=_12?_12.property:null,_14=_13?this.getFieldNum(_13):null,_15=_13?this.getUnderlyingField(_13):null,_16=_12?Array.shouldSortAscending(_12.direction):null;if(!_15){if(_12)this.logWarn("Field does not exist: "+_12.property)}
if(_14>=0){this.$600(_14)}else{this.$600(null)}
if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort - ready to sort on specifiers:\n"+isc.echoAll(this.$73p),"sorting")}
if(this.$73p&&this.$73p.length>0){if(this.data&&(this.data.setSort||this.data.length>0||isc.isA.ResultTree(this.data)||isc.isA.Tree(this.data)))
{if(this.data.setSort){if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort -  Calling data.setSort with specifiers:\n"+isc.echoAll(this.$73p),"sorting")}
this.data.setSort(this.$73p)}else if(this.data.sortByProperty){if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort - Calling data.sortByProperty with specifier:\n"+isc.echoAll(_12),"sorting")}
this.data.sortByProperty(_12.property,Array.shouldSortAscending(_12.direction),_12.normalizer,_12.context)}}else{if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort - not sorting:\nthis.data is"+this.echoAll(this.data),"sorting")}}}else{if(this.data){if(this.data.setSort!=null)this.data.setSort([]);else if(this.data.unsort)this.data.unsort()}
if(this.invalidateCacheOnUnsort){this.invalidateCache()}}
var _17=(this.header&&isc.isA.Toolbar(this.header));if(_2){if(_2.length>0){if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort - Removing sort-media from now unsorted fields:\n"+isc.echoAll(_2),"sorting")}}
for(var i=0;i<_2.length;i++){var _18=_2[i],_9=this.getSpecifiedField(_18.property),_19=[];if(_9){_19.add(_9);if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort, removing sort-media for fieldName '"+_9.name+"'","sorting")}}
var _20=this.getFields();if(_20){var _21=_20.findAll("displayField",_18.property);if(_21){_21.removeUnless("optionDataSource",null);if(_21.length>0){_19.addList(_21);if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort, removing sort-media for fields with displayField "+"'"+_9.name+"' - these are: "+isc.echoFull(_21.getProperty("name").join(", ")),"sorting")}}}}
for(var j=0;j<_19.length;j++){var _23=_19[j],_24=this.getFieldNum(_23.name);if(_23){_23.sortDirection=_23.originalSortDirection}
if(_24>=0&&_17){var _25=this.getFieldHeaderButton(_24);if(_25)_25.setTitle(_25.getTitle())}}}}
if(this.$73p&&this.$73p.length>0){this.logInfo("In setSort - Setting sort-media for sorted fields:\n"+isc.echoAll(this.$73p),"sorting");var _26=false;for(var i=0;i<this.$73p.length;i++){var _18=this.$73p[i],_9=this.getSpecifiedField(_18.property),_19=[];if(_9){_19.add(_9);if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort, adding sort-media for fieldName '"+_9.name+"'","sorting")}}
var _20=this.getFields();if(_20){var _21=_20.findAll("displayField",_18.property);if(_21){_19.addList(_21);if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort, adding sort-media for fields with displayField "+"'"+_9.name+"' - these are: "+isc.echoFull(_21.getProperty("name").join(", ")),"sorting")}}}
for(var j=0;j<_19.length;j++){var _23=_19[j],_24=this.getFieldNum(_23.name);if(_23){if(_23.sortDirection&&!_23.originalSortDirection){_23.originalSortDirection=_23.sortDirection}
_23.sortDirection=_18.direction}
if(_17&&_24>=0){var _27=this.getFieldHeader(_24),_25=this.getFieldHeaderButton(_24);if(_25){if(!_26){_27.selectButton(_25);_26=true}
_25.setTitle(this.getHeaderButtonTitle(_25))}}}}}else if(_17){var _28;if(this.header){_28=this.header.getSelectedButton();if(_28)_28.deselect()}
if(this.frozenHeader){_28=this.frozenHeader.getSelectedButton();if(_28)_28.deselect()}}
if(_14>=0){if(this.logIsInfoEnabled("sorting")){this.logInfo("In setSort - $600 called with fieldNum: "+_14+" - sortField is now: "+this.sortField+"\ngetSortState() now returns: "+isc.echoAll(this.getSortState()),"sorting")}}
if(this.sorter){this.sorter.setTitle(this.sorter.getTitle())}
if(this.body&&this.showRecordComponents)delete this.body.$74k;this.$25a(this.$73y);if(this.logIsInfoEnabled("sorting"))this.logInfo("Leaving setSort","sorting");this.handleSortChanged(this.$73p);return true}
,isc.A.handleSortChanged=function isc_ListGrid_handleSortChanged(_1){this.sortChanged(_1);this.handleViewStateChanged()}
,isc.A.sortChanged=function isc_ListGrid_sortChanged(_1){}
,isc.A.$34g=function isc_ListGrid__continueSort(){var _1=this.$34i;delete this.$34i;this.setSort(_1)}
,isc.A.$34h=function isc_ListGrid__cancelSort(){var _1;var _1=this.$34i?this.$34i[0]:null;delete this.$34i;if(_1!=null){var _2=this.getFieldNum(_1.property);if(_2!=-1&&this.sortFieldNum!=_2){this.header.deselectButton(_2)}}}
,isc.A.sortData=function isc_ListGrid_sortData(){if(!this.data||!this.fields)return;var _1=this.fields[this.$60z()],_2=null;if(_1==null){this.$600(0);_1=this.fields[0]}
if(_1.sortNormalizer){_2=_1.sortNormalizer}else if(_1.valueMap){_2=_1.valueMap;if(isc.isA.String(_1.valueMap))_2=this.getGlobalReference(_1.valueMap)}else if(_1.type!=null){_2=_1.type}
this.data.sortByProperty(_1[this.fieldIdProperty],_1.sortDirection,_2,this)}
,isc.A.getEmbeddedComponentCount=function isc_ListGrid_getEmbeddedComponentCount(_1){var _2=this.body?this.body.$29a:null;if(this.frozenBody&&this.frozenBody.$29a!=null){_2=(_2?_2.duplicate():[]).addList(this.frozenBody.$29a)}
if(!_2)return 0;if(_1=="recordComponent"){_2=_2.findAll("isRecordComponent",true)}else if(_1=="backgroundComponent"){_2=_2.findAll("isBackgroundComponent",true)}
return _2==null?0:_2.length}
,isc.A.addEmbeddedComponent=function isc_ListGrid_addEmbeddedComponent(_1,_2,_3,_4,_5){var _6=this.getFieldBody(_4),_4=this.getLocalFieldNum(_4),_3=(_3!=null?_3:this.getRecordIndex(_2));_6.addEmbeddedComponent(_1,_2,_3,_4,_5);if(this.frozenBody!=null){var _7=_6==this.frozenBody?this.body:this.frozenBody;if(_3>=0&&_7.isDrawn()&&!_7.isDirty()){var _8=_7.getRowHeight(_2,_3);if(_8!=_6.getRowSize(_3)){_7.markForRedraw()}}}}
,isc.A.removeEmbeddedComponent=function isc_ListGrid_removeEmbeddedComponent(_1,_2,_3){var _4;if(isc.isA.Canvas(_1)){_2=_1;_1=_2.embeddedRecord}
if(isc.isA.Number(_2)){_4=this.getFieldBody(_2);_2=this.getLocalFieldNum(_2)}else{if(!_2){if(!_1.$29a||_1.$29a.length==0)return;_2=_1.$29a[0]}
_4=isc.Canvas.getById(_2.$669)}
if(_4==null){return}
_4.removeEmbeddedComponent(_1,_2,_3)}
,isc.A.getEmbeddedComponent=function isc_ListGrid_getEmbeddedComponent(_1,_2){var _3;if(isc.isA.Number(_2)){_3=this.getFieldBody(_2);_2=this.getLocalFieldNum(_2)}else{_3=isc.Canvas.getById(_2.$669)}
return _3.getEmbeddedComponent(_1,_2)}
,isc.A.getRecordDataSource=function isc_ListGrid_getRecordDataSource(_1){return this.dataSource}
,isc.A.openRecordEditor=function isc_ListGrid_openRecordEditor(_1){if(this.$32o!=null)this.closeRecord();var _2=isc.addProperties({},_1);var _3=this.ns.DynamicForm.create(this.recordEditorProperties,{autoDraw:false,dataSource:this.getRecordDataSource(_1),numCols:4,values:_2,$34j:_1});var _4=this.ns.VStack.create({autoDraw:false,width:this.getAvailableFieldWidth()-this.embeddedComponentIndent,left:this.embeddedComponentIndent,destroyOnUnEmbed:true,members:[_3,this.ns.Toolbar.create({autoDraw:false,width:200,buttons:[{title:this.recordEditorSaveButtonTitle,click:this.getID()+".embeddedSaveRecord("+_3.getID()+")",extraSpace:10},{title:this.recordEditorCancelButtonTitle,record:_1,grid:this,click:function(){var _5=this.parentElement.parentElement;this.grid.closeRecord(this.record,_5)}}]})]});this.addEmbeddedComponent(_4,_1,this.data.indexOf(_1));this.$32o=_1;this.$57o=_4}
,isc.A.embeddedSaveRecord=function isc_ListGrid_embeddedSaveRecord(_1){_1.saveData({target:this,methodName:"embeddedEditComplete"},{$34j:_1.$34j,$57p:_1})}
,isc.A.embeddedEditComplete=function isc_ListGrid_embeddedEditComplete(_1,_2,_3){if(_1.status==0){this.removeEmbeddedComponent(_3.$34j,_3.$57p)}}
,isc.A.closeRecord=function isc_ListGrid_closeRecord(_1,_2){if(!_1)_1=this.$32o;if(!_2)_2=this.$57o;this.removeEmbeddedComponent(_1,_2);this.$32o=null;this.$57o=null}
,isc.A.openRecordDetailGrid=function isc_ListGrid_openRecordDetailGrid(_1,_2){if(this.$32o!=null)this.closeRecord();var _3=this.getRecordDetailGrid(_1,_2);var _4=isc.VLayout.create({autoDraw:false,destroyOnUnEmbed:true,height:this.cellHeight,left:this.embeddedComponentIndent,width:this.getAvailableFieldWidth()-this.embeddedComponentIndent,resizeBarSize:4,members:[_3]});this.addEmbeddedComponent(_4,_1,this.data.indexOf(_1));var _2=isc.DataSource.getDataSource(_3.dataSource);_3.fetchRelatedData(_1,this.getRecordDataSource(_1));this.$32o=_1;this.$57o=_4}
,isc.A.getRecordDetailGrid=function isc_ListGrid_getRecordDetailGrid(_1,_2){_2=isc.DataSource.getDataSource(_2);var _3=isc.ListGrid.create(this.recordDetailGridProperties,{autoDraw:false,dataSource:_2,showResizeBar:true,showCellContextMenus:this.showCellContextMenus},this.nestedGridDefaults);return _3}
,isc.A.chartData=function isc_ListGrid_chartData(_1,_2,_3,_4,_5){this.checkChartConstructor();if(_1)_1=this.getField(_1);if(_2)_2=this.map("getField",_2);else{_2=this.fields.duplicate();_2.remove(_1)}
if(!_3){if(!isc.ResultSet||!isc.isA.ResultSet(this.data)){_3=this.data}else if(this.data.allMatchingRowsCached()){_3=this.data.getAllRows()}else{var _6=this.getVisibleRows();_3=this.data.getRange(_6[0],_6[1])}}
var _7,_8;if(_2.length>=1){for(var i=0;i<_2.length;i++){var _10=_2[i];_2[i]={id:_10.name,title:this.htmlUnescapeExportFieldValue(_10.title),type:_10.type}}
_8={id:"columnFacet",title:this.valueTitle,values:_2,inlinedValues:true}}
if(_3.length>=1&&_1){_7={id:_1.name}}
var _11=[_8,_7];_11.removeAll([null]);if(_5)_11.reverse();var _12=isc.addProperties({data:_3,facets:_11,title:this.chartTitle,chartType:this.chartType},_4);if(_7&&!_8)_12.valueProperty=_2[0].name;return this.createAutoChild("chart",_12,this.chartConstructor)}
,isc.A.checkChartConstructor=function isc_ListGrid_checkChartConstructor(){var _1=this.chartConstructor;if(isc.isA.String(_1)){_1=window.isc[this.chartConstructor]}
if(_1==null){this.logWarn('Component chartConstructor attribute set to "'+this.chartConstructor+'" - this cannot be resolved to a valid SmartClient class. Verify that all '+'appropriate modules are loaded and that this class exists.')}else if(_1.invalidClass){var _2='Component chartConstructor attribute set to "'+this.chartConstructor+'". ';if(_1.invalidErrorMessage)_2+=_1.invalidErrorMessage;else _2+=".This class is invalid - verify all appropriate modules are loaded.";this.logWarn(_2)}}
,isc.A.chartRow=function isc_ListGrid_chartRow(_1,_2,_3){return this.chartData(null,_2,[this.getRecord(_1)],_3)}
,isc.A.chartColumn=function isc_ListGrid_chartColumn(_1,_2,_3){return this.chartData(_2,[_1],null,_3)}
,isc.A.regroup=function isc_ListGrid_regroup(_1){if(!this.isGrouped)return;var _2=this.getGroupByFields();if(_2==null||_2.length==0)return;if(this.inhibitRegroup||!(this.originalData||this.data))
{return}
var _3;if(this.data.isGroupedOutput&&this.originalData)_3=this.originalData;else this.originalData=_3=this.data;var _4=_3.getLength();if(isc.ResultSet&&isc.isA.ResultSet(_3)&&(!_3.lengthIsKnown()||(_4<this.groupByMaxRecords&&!_3.rangeIsLoaded(0,_4-1))))
{_3.getRange(0,_4-1);this.$52y=true;this.logInfo("postponing grouping until data is loaded","grouping");return}
if(_4>=this.groupByMaxRecords){this.logInfo("Results too numerous - disabling grouping.","grouping");this.clearGroupBy();return}
var _5;if(this.groupTree){if(!_1&&this.canCollapseGroup&&this.retainOpenStateOnRegroup&&(this.groupTree.getLength()>0)&&(this.groupTree.$684==this.groupByField))
{_5=[];this.$45q(this.groupTree,this.groupTree.getRoot(),_5,true)}
this.ignore(this.groupTree,"changeDataVisibility");this.groupTree.destroy()}
this.groupTree=this.createAutoChild("groupTree",{idField:this.groupIdField,parentIdField:this.groupParentIdField,titleProperty:"groupValue",childrenProperty:"groupMembers",parentProperty:"_groupTree_"+this.ID,showRoot:false,isGroupedOutput:true,alwaysSortGroupHeaders:(this.showGroupSummary&&this.showGroupSummaryInHeader),$684:this.groupByField,separateFolders:true,getCriteria:function(){var _6=this.creator.originalData;if(_6&&_6.getCriteria)return _6.getCriteria();return null},getChildren:function(_31,_32,_33,_34,_35,_36,_37){var _7=this.Super("getChildren",arguments);if(_37||_32==isc.Tree.FOLDERS_ONLY||_31==null||_31==this.getRoot())
{return _7}
var _8=this.creator;if(_8&&_8.showGroupSummary&&!_8.showGroupSummaryInHeader){var _9=_31.groupName;if(_8.groupByFieldSummaries!=null&&!_8.groupByFieldSummaries.contains(_9))
{return _7}
var _10=_7?_7.duplicate():[],_11=this.getRecordsInGroup(_31);_11=this.combineWithEditVals(_11);var _12=_8.getGroupSummaryData(_11,_31)
if(!_12!=null){if(!isc.isAn.Array(_12))_12=[_12];_10.addList(_12)}
return _10}else{return _7}},getRecordsInGroup:function(_31){var _8=this.creator,_13=_8.getGroupByFields(),_9=_31.groupName;var _7=this.getChildren(_31,null,null,null,null,null,true);var _14=[];if(_9==_13[_13.length-1]){_14.addList(_7)}else{if(_7!=null){for(var i=0;i<_7.length;i++){var _16=this.getRecordsInGroup(_7[i]);if(_16!=null&&_16.length>0)_14.addList(_16)}}}
return _14},combineWithEditVals:function(_7){var _8=this.creator,_11=[];_11.addList(_7);var _17=_8.getAllEditRows();if(_17.length>0){var _18;for(var i=0;i<_11.length;i++){var _19=_8.getEditSession(_11[i]);if(_19){var _20=isc.addProperties({},_11[i],_19.$31x);_11[i]=_20}}}
return _11},primaryKeyFields:this.dataSource?this.getDataSource().getPrimaryKeyFieldNames():null,indexOf:function(_31){var _21=this.Super("indexOf",arguments);if(_21==-1&&this.creator.getDataSource()!=null){var _22=this.$27l();_21=_22.findByKeys(_31,this.creator.getDataSource())}
return _21}},isc.Tree);this.logInfo("Adding "+_3.getLength()+" records to groups","grouping");for(var i=0;i<_3.getLength();i++){this.$52u(_3.get(i),false,_5)}
var _23=this.groupTree.getRoot(),_24=0,_25=0,_26=this.groupTree,_2=this.getGroupByFields();while(_23!=null&&_25<_2.length){var _27=_26.getChildren(_23),_28=this.getUnderlyingField(_2[_25]);for(var i=0;i<_27.length;i++){var _29=_27[i],_30=this.getGroupTitle(_29,_28);if(!this.singleCellGroupHeaders()){_29.groupTitle=_30}else _29[this.singleCellValueProperty]=_30;if(this.showGroupSummary&&this.showGroupSummaryInHeader){this.applyGroupSummaryToHeader(_29)}}
if(_26.getParent(_23)&&_26.getChildren(_26.getParent(_23))[_24+1]){_24++;_23=_26.getChildren(_26.getParent(_23))[_24]}else{_23=_26.getChildren(_23).first();_24=0;_25++}}
if(!_5)this.openInitialGroups();this.data=_26;if(!_1){this.$606(this.data)}else{this.observe(_26,"changeDataVisibility","observer.$34u(node, newState)")}
this.createSelectionModel();if(this.$75p){this.setSelectedState(this.$75p);delete this.$75p}
if(this.fields.find("$84g",true)!=null){this.updateFieldWidthsForAutoFitValue("regroup with group title column showing")}
this.markForRedraw("regroup")}
,isc.A.$607=function isc_ListGrid__incrementalRegroup(_1,_2,_3,_4){this.$34v=true;var _5=this.data.getParents(_2);var _6=this.data.remove(_2);var _7=this.getGroupByFields();for(var i=0,j=_7.length-1;i<_5.length-1;i++){var _10=_5[i];if(_10.groupMembers.getLength()==0)this.data.remove(_10);else{var _11=(this.completeFields||this.fields).find(this.fieldIdProperty,_10);if(this.singleCellGroupHeaders()){_10[this.singleCellValueProperty]=this.getGroupTitle(_10,_11)}else{_10.groupTitle=this.getGroupTitle(_10,_11)}}
j--}
if(!(_2&&!_6)&&(_1||_4))
this.$52u(_1||_4,true)}
,isc.A.openInitialGroups=function isc_ListGrid_openInitialGroups(){var _1=this.groupTree;if(this.groupStartOpen=="all"){_1.openAll()}else if(this.groupStartOpen=="first"){_1.openAll(_1.getChildren(_1.getRoot()).first())}else if(isc.isAn.Array(this.groupStartOpen)){var _2=_1.getChildren(_1.getRoot());for(var i=0;i<this.groupStartOpen.length;i++){var _4=this.groupStartOpen[i];var _5=_2.find("groupValue",_4);if(_5)_1.openFolder(_5)}}}
,isc.A.groupBy=function isc_ListGrid_groupBy(_1){var _2=[];if(isc.isAn.Array(_1)){_2=_1}else{for(var i=0;i<arguments.length;i++){_2[i]=arguments[i]}}
for(var i=0;i<_2.length;i++){if(_2[i]!=null&&!this.getUnderlyingField(_2[i])){this.logWarn("groupBy() passed field:"+_2[i]+" - this is not a valid field"+" within this grid - ignoring","grouping");return}}
if(this.handleGroupBy!=null&&this.handleGroupBy(_2)==false){return}
this.handleViewStateChanged();if(_2.length==0||_2[0]==null){this.clearGroupBy();return}
if(isc.isAn.Array(this.groupByField))this.groupByField.setLength(0);else this.groupByField=[];for(var i=0;i<_2.length;i++){if(this.isCheckboxField(_2[i]))continue;var _4=this.getUnderlyingField(_2[i]);if(_4&&_4.displayField!=null&&this.getField(_4.displayField)&&_4.optionDataSource==null){this.groupByField.add(_4.displayField)}else{this.groupByField.add(_2[i])}}
this.isGrouped=true;if(this.showGroupSummary&&this.showGroupSummaryInHeader&&this.getGroupTitleField()==null&&this.showGroupTitleColumn)
{if(this.groupTitleColumn==null){this.groupTitleColumn=this.getGroupTitleColumn()}
if(this.fields.indexOf(this.groupTitleColumn)==-1){this.addField(this.groupTitleColumn,this.getGroupTitleColumnPosition())}}
this.logInfo("groupBy: "+this.groupByField,"grouping");this.$52y=true;this.dataChanged()}
,isc.A.clearGroupBy=function isc_ListGrid_clearGroupBy(){this.logInfo("ungrouping","grouping");this.isGrouped=false;if(this.originalData){var _1=this.preserveEditsOnSetData;this.preserveEditsOnSetData=true;this.setData(this.originalData);this.preserveEditsOnSetData=_1;delete this.originalData;delete this.groupTree;if(this.groupByField)this.groupByField=null}
if(this.groupTitleColumn!=null){var _2=this.completeFields||this.fields,_3=_2.indexOf(this.groupTitleColumn);if(_3!=-1){var _4=[];for(var i=0;i<_2.length;i++){if(i==_3)continue;_4[_4.length]=_2[i]}
this.setFields(_4)}}}
,isc.A.getGroupTitle=function isc_ListGrid_getGroupTitle(_1,_2){if(!_2){var _3=this.data.getLevel(_1)-1;var _4=this.getGroupByFields(),_2=this.getUnderlyingField(_4[_3])}
if(_2==null){return}
if(_2.getGroupTitle){return _2.getGroupTitle(_1.groupValue,_1,_2,_2.name,this)}
else if(!_2.getGroupValue&&_2.$62&&_2.$62.getGroupTitle){return _2.$62.getGroupTitle(_1.groupValue,_1,_2,_2.name,this)}else if(!this.singleCellGroupHeaders()){return _1.groupTitle}else{return _1[this.singleCellValueProperty]}}
,isc.A.getGroupNodeHTML=function isc_ListGrid_getGroupNodeHTML(_1,_2){var _3=this.frozenBody===_2;if(this.frozenBody&&!_3)return this.emptyCellValue;var _4=this.data.isOpen(_1)?"opened":"closed",_5=isc.Img.urlForState(this.groupIcon,null,null,_4),_6=isc.Canvas.spacerHTML(this.groupIconPadding,1),_7=isc.Canvas.spacerHTML((this.data.getLevel(_1)-1)*this.groupIndentSize+this.groupLeadingIndent,1);var _8=this.imgHTML(_5,this.groupIconSize,this.groupIconSize);var _9=(this.canCollapseGroup?_7+_8+_6+this.getGroupTitle(_1):_7+_6+this.getGroupTitle(_1));return _9}
,isc.A.$52u=function isc_ListGrid__addRecordToGroup(_1,_2,_3){var _4,_5=this.groupTree.getRoot(),_6=this.getGroupByFields();for(var i=0;i<_6.length;i++){var _8=_6[i],_9=this.getUnderlyingField(_8),_10=this.data.indexOf(_1);var _11=this.getRawCellValue(_1,_10,_8,true);if(_9.getGroupValue){_11=_9.getGroupValue(_11,_1,_9,_8,this)}else if(_9.userFormula){_11=this.getFormulaFieldValue(_9,_1)}else if(_9.userSummary){_11=this.getSummaryFieldValue(_9,_1)}else if(_9.$62&&_9.$62.getGroupValue){_11=_9.$62.getGroupValue(_11,_1,_9,_8,this)}
if(_9.valueMap&&_9.valueMap[_11]){_11=_9.valueMap[_11]}
if(_11==null||isc.isAn.emptyString(_11)){_11=this.nullGroupTitle}
var _12=this.groupTree.getChildren(_5);if(_12==null)_4=null;else _4=_12.find('groupValue',_11);if(!_4){_4={groupName:_8,groupValue:_11,$52e:true,canDrag:false,canSelect:false};if(this.singleCellGroupHeaders())_4.singleCellValue=_11;else _4.groupTitle=_11;_4[_8]=_11;_4[this.recordCustomStyleProperty]=this.groupNodeStyle
_4[this.recordEditProperty]=false
if(_3!=null){_4[this.groupTree.openProperty]=_3.find(_8,_11)?true:false}
this.groupTree.add(_4,_5)}
_5=_4}
this.groupTree.add(_1,_5);if(_2){var _13=this.groupTree.getParents(_1);for(var i=_13.length-2,j=0;i>=0;i--,j++){var _15=this.getGroupTitle(_13[i]);if(!this.singleCellGroupHeaders())_13[i].groupTitle=_15;else _13[i][this.singleCellValueProperty]=_15}}}
,isc.A.applyGroupSummaryToHeader=function isc_ListGrid_applyGroupSummaryToHeader(_1){var _2=_1.groupName;if(this.groupByFieldSummaries!=null&&!this.groupByFieldSummaries.contains(_2)){return}
var _3=this.groupTree,_4=_3.combineWithEditVals(_3.getRecordsInGroup(_1));var _5=this.getGroupSummaryData(_4,_1);if(isc.isAn.Array(_5))_5=_5[0];var _6=this.completeFields||this.fields;for(var i=0;i<_6.length;i++){var _8=_6[i].name;if(_8=="groupTitle")continue;_1[_8]=_5[_8]}
_1.isGroupSummary=true}
);isc.evalBoundary;isc.B.push(isc.A.setShowGroupSummaryInHeader=function isc_ListGrid_setShowGroupSummaryInHeader(_1){if(this.showGroupSummaryInHeader==_1)return;this.showGroupSummaryInHeader=_1;var _2=this.getGroupByFields();if(_2!=null&&_2.length>0){this.ungroup();this.groupBy(_2)}}
,isc.A.ungroup=function isc_ListGrid_ungroup(){this.$75p=this.getSelectedState(true);this.groupBy(null)}
,isc.A.setHeaderSpans=function isc_ListGrid_setHeaderSpans(_1){this.headerSpans=_1;this.updateHeader();this.layoutChildren("headerSpans changed")}
,isc.A.setHeaderSpanTitle=function isc_ListGrid_setHeaderSpanTitle(_1,_2){var _3=this.headerSpans.find("name",_1);if(!_3){this.logWarn("setHeaderSpanTitle() - unable to locate span named: "+_1+this.getStackTrace());return}
_3.title=_2;if(_3.liveObject)_3.liveObject.setTitle(_2)}
,isc.A.refreshGroupSummary=function isc_ListGrid_refreshGroupSummary(_1){if(!this.isGrouped||!this.showGroupSummary||!this.groupTree)return;if(this.showGroupSummaryInHeader){if(_1!=null){var _2=this.groupTree.getParent(this.groupTree.get(_1));this.applyGroupSummaryToHeader(_2);this.refreshRow(this.groupTree.indexOf(_2))}else{var _3=this.groupTree.findAll("$52e",true);for(var i=0;i<_3.length;i++){this.applyGroupSummaryToHeader(_3[i])}
this.markForRedraw("refresh group summaries")}}else{var _5;if(_1!=null)_5=this.groupTree.getLength();this.groupTree.$736();if(_1==null||(_5!=this.groupTree.getLength())){this.body.markForRedraw("refresh group summaries")}else{var _6=this.groupTree.getParent(this.groupTree.get(_1));if(_6){var _7=this.groupTree.indexOf(_6),_8=this.groupTree.getChildren(_6);for(var i=0;i<_8.length;i++){if(_8[i].isGroupSummary)this.refreshRow(_7+1+i)}}}}}
,isc.A.addBodyPassthroughMethods=function isc_ListGrid_addBodyPassthroughMethods(_1){if(this.body)this.$51e(this.body);if(this.frozenBody)this.$51e(this.frozenBody)}
,isc.A.$51e=function isc_ListGrid__addBodyPassthroughMethods(_1){var _2={},_3=isc.getKeys(isc.ListGrid.$30f);for(var i=0;i<_3.length;i++){var _5=_3[i],_6=this[_5];if(_5=="cellContextClick")continue;if(_6==null){_2[_5]=_1.getClass().getPrototype()[_5]}else if(_6!=isc.ListGrid.$30g[_5]){_2[_5]=isc.ListGrid.$30f[_5]}}
_1.addMethods(_2)}
,isc.A.addProperties=function isc_ListGrid_addProperties(_1){this.Super("addProperties",arguments);this.addBodyPassthroughMethods()}
,isc.A.addMethods=function isc_ListGrid_addMethods(_1){this.Super("addMethods",arguments);this.addBodyPassthroughMethods()}
,isc.A.propertyChanged=function isc_ListGrid_propertyChanged(_1,_2){this.invokeSuper(isc.ListGrid,"propertyChanged",_1,_2);if(this.$31h[_1])this.$34k=true;if(this.body==null)return;if(isc.ListGrid.$295.contains(_1)){this.body[_1]=_2}
if(this.$45l[_1]!=null){this.body.setProperty(this.$45l[_1],_2);this.markForRedraw()}}
,isc.A.doneSettingProperties=function isc_ListGrid_doneSettingProperties(){if(this.$34k)this.$31i()}
,isc.A.setChildEditableProperties=function isc_ListGrid_setChildEditableProperties(_1,_2,_3,_4){var _5=isc.DS.get(_3.type);if(_5&&_5.inheritsSchema("ListGridField")){isc.addProperties(_1,_2);this.setFields(this.completeFields||this.fields)}else{this.Super("setChildEditableProperties",arguments)}}
,isc.A.getExportFieldValue=function isc_ListGrid_getExportFieldValue(_1,_2,_3){var _4=this.getSpecifiedField(_2);if(this.isCheckboxField(_4)||_4.valueIcons||this.$31s(_4))
{var _5=(_4.displayField!=null&&!_4.valueMap&&!_4.getCellValue&&this.$425(_4));var _6=this.getRecordIndex(_1),_7;_7=this.getRawCellValue(_1,_6,_5?_4.displayField:_2,true);_7=this.$315(_7,_1,_4,_6,_3);var _8=this.getFieldHilites(_1,_4);if(_8)_7=this.applyHiliteHTML(_8,_7);return this.htmlUnescapeExportFieldTitle(_7.toString())}
return this.Super("getExportFieldValue",arguments)}
,isc.A.getOriginalData=function isc_ListGrid_getOriginalData(){return(this.isGrouped&&this.originalData)?this.originalData:this.getData()}
);isc.B._maxIndex=isc.C+696;isc.ListGrid.registerStringMethods(isc.GridRenderer.$294);isc.ListGrid.registerStringMethods({recordClick:"viewer,record,recordNum,field,fieldNum,value,rawValue",recordDoubleClick:"viewer,record,recordNum,field,fieldNum,value,rawValue",recordsDropped:"records,rowNum,viewer,sourceWidget",recordDropMove:"viewer,recordNum,record,position",editValueChanged:"rowNum,fieldName,newValue,oldValue",editorChange:"record,newValue,oldValue,rowNum,colNum",cellChanged:"record,newValue,oldValue,rowNum,colNum,grid,recordNum,fieldNum",editComplete:"rowNum,colNum,newValues,oldValues,editCompletionEvent,dsResponse",editFailed:"rowNum,colNum,newValues,oldValues,editCompletionEvent,dsResponse,dsRequest",editorEnter:"record,value,rowNum,colNum",rowEditorEnter:"record,editValues,rowNum",editorExit:"editCompletionEvent,record,newValue,rowNum,colNum",rowEditorExit:"editCompletionEvent,record,newValues,rowNum",validateCellValue:"rowNum,colNum,newValue,oldValue",validateFieldValue:"newValue,oldValue,record,field,rowNum,colNum",formatCellValue:"value,record,rowNum,colNum",formatEditorValue:"value,record,rowNum,colNum",parseEditorValue:"value,record,rowNum,colNum",sortChanged:"sortSpecifiers",fieldStateChanged:"",viewStateChanged:"",dataArrived:"startRow,endRow",headerClick:"fieldNum",onHeaderClick:"fieldNum",onRecordDrop:"dropRecords,targetRecord,index,sourceWidget",onExpandRecord:"record",onCollapseRecord:"record",drawAreaChanged:"oldStartRow,oldEndRow,oldStartCol,oldEndCol",showRecordComponent:"record,colNum",createRecordComponent:"record,colNum",updateRecordComponent:"record,colNum,component,recordChanged",updateFilterEditorValues:"criteria",filterEditorSubmit:"criteria",handleGroupBy:"fields"});isc.ListGrid.$24j=isc.addProperties({},isc.ListGrid.getInstanceProperty("headerDefaults"));isc.ListGrid.$24k=isc.addProperties({},isc.ListGrid.getInstanceProperty("headerButtonDefaults"));isc.ListGrid.classInit();isc.defineClass("LineEditor",isc.ListGrid);isc.A=isc.LineEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canEdit=true;isc.A.editEvent="click";isc.A.editOnFocus=true;isc.A.modalEditing=true;isc.A.enterKeyEditAction="nextRowStart";isc.A.listEndEditAction="next";isc.A.height=50;isc.A.emptyMessage="Click to add data";isc.A.emptyMessageStyle="normal";isc.A.autoFitData="vertical";isc.A.leaveScrollbarGap=false;isc.B.push(isc.A.click=function isc_LineEditor_click(){var _1=this.getRecord(this.getEventRow());this.Super("click",arguments);if(_1==null&&isc.EH.lastEvent.target==this.body)this.startEditingNew()}
);isc.B._maxIndex=isc.C+1;isc.ClassFactory.defineClass("TreeGrid","ListGrid");isc.addGlobal("TreeViewer",isc.TreeGrid);isc.defineClass("TreeGridBody",isc.GridBody);isc.A=isc.TreeGridBody.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$34w="TABLE";isc.A.$34x="padding:0px;border:0px;";isc.B.push(isc.A.$29h=function isc_TreeGridBody__updateCellStyle(_1,_2,_3,_4,_5){if(_4==null)_4=this.getTableElement(_2,_3);if(_4==null)return;if(!this.showHiliteInCells&&_3==this.grid.getLocalFieldNum(this.grid.getTreeFieldNum()))
{if(_1==null)_1=this.getCellRecord(_2,_3);if(_5==null)_5=this.getCellStyle(_1,_2,_3);var _6=_4.childNodes[0];while(_6&&_6.tagName!=this.$34w)_6=_6.childNodes[0];if(_6){var _7;if(this.getCellCSSText){_7=this.getCellCSSText(_1,_2,_3);if(_7!=null&&!isc.isAn.emptyString(_7)){_7+=isc.Canvas.$42a}else _7=null}
_6.className=_5;if(_7!=null)_6.cssText=_7;var _8=_6.rows,_9=_8[0].cells;if(_9&&_9.length>0){for(var i=0;i<_9.length;i++){_9[i].className=_5;if(_7){if(i==_9.length-1){_7+="paddingLeft:"+this.iconPadding}
_9[i].cssText=_7}}}}}
return isc.GridRenderer.getPrototype().$29h.apply(this,[_1,_2,_3,_4,_5])}
,isc.A.click=function isc_TreeGridBody_click(_1,_2){if(!this.$29p()){var _3=this.grid,_4=_3.getEventRecordNum(),_5=_3.getRecord(_4);if(_3.data.isFolder(_5)&&_3.clickInOpenArea(_5)){if(isc.screenReader){this.$86a(_4)}
_3.toggleFolder(_5);_3.clearLastHilite();_3.$31q=null;return isc.EH.STOP_BUBBLING}}
return this.Super("click",arguments)}
,isc.A.mouseDown=function isc_TreeGridBody_mouseDown(){var _1=this.getEventRow(),_2=this.grid.data.get(_1);if(_2!=null&&this.grid.clickInOpenArea(_2)){return isc.EH.STOP_BUBBLING}else if(this.grid.clickInCheckboxArea(_2)&&this.canSelectRecord(_2)){var _3=this.grid.selectionType;if(_3==isc.Selection.SINGLE){this.selectSingleRecord(_2)}else if(_3==isc.Selection.SIMPLE||_3==isc.Selection.MULTIPLE){if(this.selection.isSelected(_2)){this.deselectRecord(_2)}else{try{this.selectRecord(_2)}catch(e){isc.logWarn(isc.echoFull(e))}}}
return isc.EH.STOP_BUBBLING}else{return this.Super("mouseDown",arguments)}}
,isc.A.mouseUp=function isc_TreeGridBody_mouseUp(){var _1=this.getEventRow(),_2=this.grid.data.get(_1);if(_2!=null&&(this.grid.clickInOpenArea(_2)||this.grid.clickInCheckboxArea(_2)))
{return isc.EH.STOP_BUBBLING}else{return this.Super("mouseUp",arguments)}}
,isc.A.placeEmbeddedComponent=function isc_TreeGridBody_placeEmbeddedComponent(_1){if(this.grid.indentRecordComponents){var _2=_1.$57n;if(_2==this.grid.getTreeFieldNum()&&!_1.snapOffsetLeft){var _3=_1.embeddedRecord;if(_3!=null){_1.snapOffsetLeft=this.grid.getOpenAreaWidth(_3)+this.grid.iconPadding}}}
return this.Super("placeEmbeddedComponent",arguments)}
);isc.B._maxIndex=isc.C+5;isc.A=isc.TreeGrid;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.TREE_FIELD={name:"nodeTitle",treeField:true,getCellValue:function(_1,_2,_3,_4){if(!_1.getNodeTitle){var _5=_4==null?null:_1.getFieldName(_4);return _2==null||_5==null?null:_2[_5]}
return _1.getNodeTitle(_2,_3,this)},canFilter:false,getFieldTitle:function(_1,_2){var _3=_1.getField(_2);if(_3.name=="nodeTitle")return _1.treeFieldTitle;return _3.title||_3.name}};isc.B.push(isc.A.$82d=function isc_c_TreeGrid__getTreeCellTemplate(){if(!this.$819){isc.Canvas.$816.add({target:this,methodName:"$82a"});this.$819=true}
if(this.$34p==null){this.$34p=["<table role='presentation' cellpadding=0 cellspacing=0 class='",,"' style='",,isc.Canvas.$42a+"'><tr><td style='",,isc.Canvas.$42a+"' class='",,"'>",,"</td>"]}
return this.$34p}
,isc.A.$82e=function isc_c_TreeGrid__getTreeCellTitleTemplate(){if(!this.$819){isc.Canvas.$816.add({target:this,methodName:"$82a"});this.$819=true}
if(this.$34q==null){this.$34q=["<td style='",,";"+isc.Canvas.$42a+"' class='",,"'>"+(isc.Browser.isSafari||isc.Browser.isIE?"<nobr>":""),,,,(isc.Browser.isSafari?"</nobr>":"")+"</td><td style='",,";"+isc.Canvas.$42a+"padding-left:",,"px;' class='",,"'>",,,"</td>"]}
return this.$34q}
,isc.A.$82a=function isc_c_TreeGrid__doublingStringsChanged(){this.$34p=null;this.$34q=null}
);isc.B._maxIndex=isc.C+3;isc.A=isc.TreeGrid.getPrototype();isc.A.autoFetchTextMatchStyle="exact";isc.A.cascadeSelection=false;isc.A.showPartialSelection=false;isc.A.treeFieldTitle="Name";isc.A.autoAssignTreeField=true;isc.A.showRoot=false;isc.A.displayNodeType=isc.Tree.FOLDERS_AND_LEAVES;isc.A.canDragRecordsOut=false;isc.A.dragDataAction=isc.ListGrid.MOVE;isc.A.openDropFolderDelay=600;isc.A.parentAlreadyContainsChildMessage="This item already contains a child item with that name.";isc.A.cantDragIntoSelfMessage="You can't drag an item into itself.";isc.A.cantDragIntoChildMessage="You can't drag an item into one of it's children.";isc.A.fixedFieldWidths=true;isc.A.wrapCells=false;isc.A.showHiliteInCells=false;isc.A.indentSize=20;isc.A.extraIconGap=2;isc.A.iconSize=16;isc.A.skinImgDir="images/TreeGrid/";isc.A.folderIcon="[SKIN]/folder.gif";isc.A.dropIconSuffix="drop";isc.A.openIconSuffix="open";isc.A.closedIconSuffix="closed";isc.A.nodeIcon="[SKIN]/file.gif";isc.A.showOpenIcons=true;isc.A.showDropIcons=true;isc.A.customIconProperty="icon";isc.A.customIconOpenProperty="showOpenIcon";isc.A.customIconDropProperty="showDropIcon";isc.A.showCustomIconOpen=false;isc.A.showCustomIconDrop=false;isc.A.manyItemsImage="[SKIN]folder_file.gif";isc.A.showConnectors=false;isc.A.showFullConnectors=true;isc.A.showOpener=true;isc.A.openerImage="[SKIN]opener.gif";isc.A.connectorImage="[SKIN]connector.gif";isc.A.offlineNodeMessage="This data not available while offline";isc.A.indentRecordComponents=true;isc.A.canGroupBy=false;isc.A.ignoreEmptyCriteria=false;isc.A.drawAllMaxCells=50;isc.A.drawAheadRatio=1.0;isc.A.$34l="open_icon_";isc.A.$349="extra_icon_";isc.A.$34n="icon_";isc.A.$34o="nodeTitle";isc.A=isc.TreeGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.bodyConstructor="TreeGridBody";isc.A.iconPadding=3;isc.A.$64o="</tr></table>";isc.A.$39=";";isc.A.$12c="checkbox";isc.A.$34r="absmiddle";isc.A.$76g=["style='margin-right:",,"px;'"];isc.A.$4s={};isc.B.push(isc.A.initWidget=function isc_TreeGrid_initWidget(){this.invokeSuper(isc.TreeGrid,this.$oc);if(!this.dataSource&&this.data!=null&&this.data.dataSource){this.dataSource=this.data.dataSource}
if(!this.fields||this.fields.length==0){this.fields=[isc.TreeGrid.TREE_FIELD]}}
,isc.A.setDataSource=function isc_TreeGrid_setDataSource(_1,_2){if(_2==null||_2.length==0){_2=[isc.TreeGrid.TREE_FIELD]}
return this.Super("setDataSource",[_1,_2])}
,isc.A.$34s=function isc_TreeGrid__initTreeField(){if(!this.fields||this.fields.length==0){this.fields=[isc.TreeGrid.TREE_FIELD]}else{var _1=this.completeFields,_2=this.fields,_3;for(var i=0;i<_1.length;i++){if(_1[i].treeField){_3=_2.indexOf(_1[i]);break}}
if(_3==null){if(!this.autoAssignTreeField)return;var _5=this.data.titleProperty,_6=_2.findIndex(this.fieldIdProperty,_5);if(_6!=-1)_3=_6}
if(_3==null)_3=0;if(this.isCheckboxField(this.fields[_3]))_3+=1;this.$34t=_3;var _7=_2[_3],_8=isc.TreeGrid.TREE_FIELD,_9=_7.formatCellValue!=null||_7.displayField!=null;for(var _10 in _8){if(_9&&_10=="getCellValue"){continue}
if(_7[_10]==null){_7[_10]=_8[_10]}}}}
,isc.A.deriveVisibleFields=function isc_TreeGrid_deriveVisibleFields(_1,_2,_3,_4){this.invokeSuper(isc.TreeGrid,"deriveVisibleFields",_1,_2,_3,_4);this.$34s()}
,isc.A.getEmptyMessage=function isc_TreeGrid_getEmptyMessage(){if(this.isOffline()){return this.offlineMessage}
if(isc.isA.Tree(this.data)&&this.data.getLoadState(this.data.getRoot())==isc.Tree.LOADING)
return this.loadingDataMessage==null?"&nbsp;":this.loadingDataMessage.evalDynamicString(this,{loadingImage:this.imgHTML(isc.Canvas.loadingImageSrc,isc.Canvas.loadingImageSize,isc.Canvas.loadingImageSize)});return this.emptyMessage.evalDynamicString(this,{loadingImage:this.imgHTML(isc.Canvas.loadingImageSrc,isc.Canvas.loadingImageSize,isc.Canvas.loadingImageSize)})}
,isc.A.isEmpty=function isc_TreeGrid_isEmpty(){if(!isc.isA.Tree(this.data))return true;var _1=this.data.getRoot();if(_1==null)return true;var _2=this.data.hasChildren(_1);if(_2||this.showRoot||this.data.showRoot)return false;return true}
,isc.A.getOpenState=function isc_TreeGrid_getOpenState(){var _1=this.data;if(_1==null){this.logWarn("getOpenState() called for a treeGrid with no data");return[]}
if(_1.getOpenState)return _1.getOpenState();var _2=_1.getRoot(),_3=[];this.$45q(_1,_2,_3);return isc.Comm.serialize(_3)}
,isc.A.setOpenState=function isc_TreeGrid_setOpenState(_1){if(this.data&&this.data.setOpenState){this.data.setOpenState(_1);return}
_1=this.evalViewState(_1,"openState")
if(!_1)return;if(!this.data){this.logWarn("unable to set open state for this treeGrid as this.data is unset");return}
this.data.closeAll();this.data.openFolders(_1)}
,isc.A.getSelectedPaths=function isc_TreeGrid_getSelectedPaths(){if(!this.selection)return null;var _1=this.selection.getSelection()||[],_2=[];for(var i=0;i<_1.length;i++){_2[i]=this.data.getPath(_1[i])}
return isc.Comm.serialize(_2)}
,isc.A.showActionInPanel=function isc_TreeGrid_showActionInPanel(_1){return this.Super("showActionInPanel",arguments)}
,isc.A.setSelectedPaths=function isc_TreeGrid_setSelectedPaths(_1){_1=this.evalViewState(_1,"selectedPaths")
if(!_1)return;var _2=this.selection,_3=this.data;if(_3&&_2){_2.deselectAll();var _4=[];for(var i=0;i<_1.length;i++){var _6=_3.find(_1[i]);if(_6)_4.add(_6)}
this.selection.selectList(_4)}}
,isc.A.getViewState=function isc_TreeGrid_getViewState(){var _1=this.Super("getViewState",[true]);_1.open=this.getOpenState();return"("+isc.Comm.serialize(_1)+")"}
,isc.A.setViewState=function isc_TreeGrid_setViewState(_1){this.Super("setViewState",arguments);_1=this.evalViewState(_1,"viewState",true)
if(!_1)return;if(_1.open)this.setOpenState(_1.open);if(_1.selected)this.setSelectedState(_1.selected)}
,isc.A.getDefaultData=function isc_TreeGrid_getDefaultData(){return isc.Tree.create({$31k:true})}
,isc.A.setData=function isc_TreeGrid_setData(_1,_2,_3,_4){this.invokeSuper(isc.TreeGrid,"setData",_1,_2,_3,_4);if(!this.data)return;if(this.separateFolders!=null)this.data.separateFolders=this.separateFolders;if(this.sortFoldersBeforeLeaves!=null)
this.data.sortFoldersBeforeLeaves=this.sortFoldersBeforeLeaves;if(this.showRoot&&isc.ResultTree&&isc.isA.ResultTree(this.data)){this.logWarn("showRoot may not be set with a databound treeGrid, unexpected "+"results may occur")}
this.data.showRoot=this.showRoot;this.data.openDisplayNodeType=this.displayNodeType}
,isc.A.draw=function isc_TreeGrid_draw(_1,_2,_3,_4){if(this.initialData&&(!isc.ResultSet||!isc.isA.ResultSet(this.data))){this.setData(this.createResultTree())}
this.invokeSuper(isc.TreeGrid,"draw",_1,_2,_3,_4)}
,isc.A.bodyKeyPress=function isc_TreeGrid_bodyKeyPress(_1){var _2=this.selection;if(this.selectionType!=isc.Selection.NONE&&this.data.getLength()>0&&_2.anySelected()&&!_2.multipleSelected())
{var _3=this.selection.getSelectedRecord();if(_1.keyName=="Arrow_Left"){if(this.data.isFolder(_3)&&this.data.isOpen(_3)){this.closeFolder(_3)}else{this.$319(this.data.getParent(_3),true)}
return false}else if(_1.keyName=="Arrow_Right"){if(this.data.isFolder(_3)){if(!this.data.isOpen(_3)){this.openFolder(_3);return false}else{var _4=this.getRecord(this.data.indexOf(_3)+1);if(_4!=null&&this.data.getParent(_4)==_3){this.$319(_4,true);return false}}}}}
return this.Super("bodyKeyPress",arguments)}
,isc.A.$298=function isc_TreeGrid__cellContextClick(_1,_2,_3){if(_2<0||_3<0)return true;var _4=this.data.isFolder(_1);if(this.nodeContextClick&&this.nodeContextClick(this,_1,_2)==false){return false}
if(_4){if(this.folderContextClick&&this.folderContextClick(this,_1,_2)==false){return false}}else{if(this.leafContextClick&&this.leafContextClick(this,_1,_2)==false){return false}}
return this.Super("$298",arguments)}
,isc.A.handleEditCellEvent=function isc_TreeGrid_handleEditCellEvent(_1,_2){var _3=this.getRecord(_1);if(this.clickInOpenArea(_3)||this.clickInCheckboxArea(_3))return false;return this.Super("handleEditCellEvent",arguments)}
,isc.A.canEditCell=function isc_TreeGrid_canEditCell(_1,_2){if(this.Super("canEditCell",arguments)==false)return false;if(this.getField(_2)[this.fieldIdProperty]==this.data.nameProperty)return false;if(this.getField(_2)[this.fieldIdProperty]==this.$34o)return false;return true}
,isc.A.getEditFormItemFieldWidths=function isc_TreeGrid_getEditFormItemFieldWidths(_1){var _2=this.data.getLevel(_1);if(!this.showRoot)_2--;var _3=this.getOpenerIconSize(_1),_4=_2*(this.showConnectors?_3:this.indentSize);_4+=this.iconSize+_3;if(this.$739(_1)){_4+=(this.$65a()+this.extraIconGap)}else if(this.getExtraIcon(_1)){_4+=(this.iconSize+this.extraIconGap)}
var _5=this.Super("getEditFormItemFieldWidths",arguments),_6=this.getTreeFieldNum();_5[_6]-=_4;return _5}
,isc.A.getRecordDataSource=function isc_TreeGrid_getRecordDataSource(_1){return this.data.getNodeDataSource(_1)}
,isc.A.rowClick=function isc_TreeGrid_rowClick(_1,_2,_3){var _4=_1;if(this.clickInOpenArea(_4)||this.clickInCheckboxArea(_4))return false;this.$31q=_2;if(_2<0||_3<0)return false;var _4=this.getRecord(_2),_5=this.data.isFolder(_4);if(this.nodeClick)this.nodeClick(this,_4,_2);if(_5){if(this.folderClick)this.folderClick(this,_4,_2)}else{if(this.leafClick)this.leafClick(this,_4,_2)}
return this.Super("rowClick",arguments)}
,isc.A.recordDoubleClick=function isc_TreeGrid_recordDoubleClick(_1,_2,_3,_4,_5,_6,_7){if(this.clickInOpenArea(_2)||this.clickInCheckboxArea(_2))return false;if(this.isEditable()&&this.editEvent==isc.EH.DOUBLE_CLICK&&this.canEditCell(_3,_5))
{return true}
if(this.data.isFolder(_2)){return this.toggleFolder(_2)}else
return this.openLeaf(_2)}
,isc.A.dataChanged=function isc_TreeGrid_dataChanged(){this.Super("dataChanged",arguments);var _1=this.$34z;if(_1&&this.data.isOpen(_1)&&this.data.getLoadState(_1)==isc.Tree.LOADED)
{this.$340(_1);this.$34z=null}}
,isc.A.openLeaf=function isc_TreeGrid_openLeaf(_1){}
,isc.A.getDragTrackerIcon=function isc_TreeGrid_getDragTrackerIcon(_1){var _2;if(_1&&_1.length>1&&this.manyItemsImage!=null)
_2=this.manyItemsImage;else if(_1&&_1[0])_2=this.getIcon(_1[0],true);return _2}
,isc.A.getDragTrackerTitle=function isc_TreeGrid_getDragTrackerTitle(_1,_2,_3,_4,_5,_6){var _7=this.getFieldNum(this.getTitleField());if(_7!=this.getTreeFieldNum())
return this.invokeSuper(isc.TreeGrid,"getDragTrackerTitle",_1,_2,_3,_4,_5,_6);var _8=this.getCellStyle(_1,_2,_7),_9=this.getCellCSSText(_1,_2,_7);if(this.selection.isSelected(_1)){var _10=this.body.getCellStyleIndex(_1,_2,_7),_11=this.body.getCellStyleName(_10,_1,_2,_7);if(_11==_8){_10-=2;_8=this.body.getCellStyleName(_10,_1,_2,_7)}}
var _12=this.invokeSuper(isc.TreeGrid,"getCellValue",_1,_2,_7);var _13=this.$342(_12,_1,_2,_7,false,_8,_9).join(isc.emptyString);return["<table class='",_8,"' style='",_9,"'><tr>",_13,"</tr></table>"].join(isc.emptyString)}
,isc.A.willAcceptDrop=function isc_TreeGrid_willAcceptDrop(){if(!this.Super("willAcceptDrop",arguments))return false;isc.$54j=true;var _1=this.getEventRecordNum(),_2=this.data.get(_1);isc.$54j=false;if(_2==null)_2=this.data.getRoot();if(!_2||_2.canAcceptDrop==false)return false;var _3=this.data.isFolder(_2);if(!_3&&!(this.canReorderRecords||this.canDropOnLeaves))return false;var _4=isc.EH.dragTarget.getDragData();if(!isc.isAn.Object(_4)||this.getDropError(_4,_2)!=null){return false}
if(!_3){_2=this.data.getParent(_2);if(_2.canAcceptDrop==false)return false}
if(isc.EH.dragTarget!=this)return true;var _5=this.canReparentNodes;if(_5==null&&this.canAcceptDroppedRecords)_5=true;if(!_5){if(!isc.isAn.Array(_4))_4=[_4];var _6;_6=this.data.getParent(_4[0]);if(_6!=_2)return false;for(var i=1;i<_4.length;i++){if(_6!=this.data.getParent(_4[i]))return false}}
return true}
,isc.A.$31i=function isc_TreeGrid__setUpDragProperties(){this.canDrag=(this.canDrag||this.canDragRecordsOut||this.$34c()||this.canDragSelect);this.canDrop=(this.canDrop||this.canDragRecordsOut||this.$34c());this.canAcceptDrop=(this.canAcceptDrop||this.canAcceptDroppedRecords||this.$34c())}
,isc.A.$34c=function isc_TreeGrid__canDragRecordsToSelf(){var _1=this.canReparentNodes;if(_1==null&&this.canAcceptDroppedRecords){if(!this.$343){this.logInfo("'canReparentNodes' is unset. Allowing node reparenting as "+"'canAcceptDroppedRecords' is set to true. For explicit control, "+"use 'canReparentNodes' instead.","dragDrop");this.$343=true}
_1=this.canAcceptDroppedRecords}
return this.canReorderRecords||_1}
,isc.A.getDropError=function isc_TreeGrid_getDropError(_1,_2){for(var i=0,_4=_1.length;i<_4;i++){if(this.data.isDescendantOf(_2,_1[i])){return this.cantDragIntoChildMessage}}
var _5=this.data.isFolder(_2);if(_5){for(i=0;i<_4;i++){if(_1[i]==_2){return this.cantDragIntoSelfMessage}}}
return null}
,isc.A.dropMove=function isc_TreeGrid_dropMove(){var _1=this.getEventRow();if(_1==-1)return;var _2=(_1==-2?this.data.getRoot():this.data.get(_1)),_3=this.getDropFolder(),_4=(this.canReorderRecords?this.getReorderPosition(_1):null);if(_3!=this.lastDropFolder||_2!=this.$344||_4!=this.$345){if(!this.$346){this.$346=this.getID()+".openDropFolder()"}
if(this.openDropFolderTimer)isc.Timer.clear(this.openDropFolderTimer);if(!this.data.isOpen(_3)){this.openDropFolderTimer=isc.Timer.setTimeout(this.$346,this.openDropFolderDelay)}
this.updateDropFolder(_3)}
if(!this.willAcceptDrop()){this.body.setNoDropIndicator()}else{this.body.clearNoDropIndicator()}
if(this.canReorderRecords){if(this.data.isOpen(_3))this.showDragLineForRecord(_1,_4);else this.hideDragLine()}
this.$344=_2;this.$345=_4}
,isc.A.getDropFolder=function isc_TreeGrid_getDropFolder(){var _1=this.getEventRow(),_2=this.data,_3=(_1<0?_2.getRoot():_2.get(_1));if(_2.isRoot(_3))return _2.getRoot();var _4=_2.isFolder(_3);if(!this.canReorderRecords)return(_4?_3:_2.getParent(_3));var _5=this.getReorderPosition(_3);if(!_4||_5==isc.ListGrid.BEFORE||(_5==isc.ListGrid.AFTER&&(!_2.isOpen(_3)||!_2.hasChildren(_3))))
{return _2.getParent(_3)}else{return _3}}
,isc.A.openDropFolder=function isc_TreeGrid_openDropFolder(){var _1=this.lastDropFolder;if(!_1||!this.data.isFolder(_1)||this.data.isOpen(_1))return false;this.openFolder(_1);if(this.canReorderRecords)
this.showDragLineForRecord(this.data.indexOf(_1),isc.ListGrid.OVER)}
,isc.A.getReorderPosition=function isc_TreeGrid_getReorderPosition(_1,_2,_3,_4,_5){if(_2==null)_2=this.body.getOffsetY();if(_1==null)_1=this.getEventRow(_2);var _6=this.data;if(!isc.isA.Number(_1))_1=_6.indexOf(_1);var _7=_6.get(_1);if(_7&&_6.isFolder(_7)){var _8=_2-this.body.getRowTop(_1),_9=this.body.getRowSize(_1);if(_8<(_9/ 4)){return isc.ListGrid.BEFORE}else if(_8>(3*_9/ 4)){return isc.ListGrid.AFTER}else{return isc.ListGrid.OVER}}
return this.invokeSuper(isc.TreeGrid,"getReorderPosition",_1,_2,_3,_4,_5)}
,isc.A.showDragLineForRecord=function isc_TreeGrid_showDragLineForRecord(_1,_2,_3,_4,_5){if(_1==null)_1=this.getEventRecordNum();if(_2==null)_2=this.getReorderPosition(_1);if(_2==isc.ListGrid.OVER){var _6=this.getRecord(_1),_7=this.data;if(_7.isFolder(_6)&&_7.isOpen(_6))_2=isc.ListGrid.AFTER}
return this.invokeSuper(isc.TreeGrid,"showDragLineForRecord",_1,_2,_3,_4,_5)}
,isc.A.dropOut=function isc_TreeGrid_dropOut(){this.hideDragLine();this.body.clearNoDropIndicator();this.$344=null;this.updateDropFolder();if(this.openDropFolderTimer)isc.Timer.clear(this.openDropFolderTimer)}
,isc.A.updateDropFolder=function isc_TreeGrid_updateDropFolder(_1){var _2=this.lastDropFolder;this.lastDropFolder=_1;if(_1){_1.$347=this.body.willAcceptDrop(_1)
this.setRowIcon(_1,this.getIcon(_1))}
if(_2&&_2!=_1){delete _2.$347;this.setRowIcon(_2,this.getIcon(_2))}}
,isc.A.transferSelectedData=function isc_TreeGrid_transferSelectedData(_1,_2,_3,_4){if(!this.isValidTransferSource(_1)){if(_4)this.fireCallback(_4);return}
if(_3==null)_3=0;if(_2==null)_2=this.data.getRoot();var _5=_1.cloneDragData();this.transferNodes(_5,_2,_3,_1,_4)}
,isc.A.drop=function isc_TreeGrid_drop(){if(!this.willAcceptDrop())return false;var _1=isc.EH.dragTarget.cloneDragData(),_2=this.getEventRecordNum(),_3=this.getReorderPosition(_2),_4=this.data.get(_2)||this.data.getRoot(),_5=this.getDropFolder();var _6=isc.EH.dragTarget.getData(),_7=(isc.isA.Tree(_6)&&isc.isA.Tree(this.data)&&_6.getRoot()==this.data.getRoot());for(var i=0;i<_1.length;i++){var _9=_1[i];var _10=(this.data.findChildNum(_5,this.data.getName(_9))!=-1);var _11=_7&&this.canReorderRecords&&_5==this.data.getParent(_9);if(_10&&!_11){this.logInfo("already a child named: "+this.data.getName(_9)+" under parent: "+this.data.getPath(_5));isc.warn(this.parentAlreadyContainsChildMessage);return false}}
var _12=null;if(this.canReorderRecords){if(_2<0){_5=_4;_12=this.data.getChildren(_5).getLength()}else if(_4==_5){_12=0}else{_12=(_3==isc.ListGrid.AFTER?1:0)+this.data.getChildren(_5).indexOf(_4)}}
if(this.onFolderDrop!=null&&(this.onFolderDrop(_1,_5,_12,isc.EH.dragTarget)==false))return false;this.folderDrop(_1,_5,_12,isc.EH.dragTarget);this.data.openFolder(_5);return false}
,isc.A.folderDrop=function isc_TreeGrid_folderDrop(_1,_2,_3,_4,_5){this.transferNodes(_1,_2,_3,_4,_5)}
,isc.A.transferNodes=function isc_TreeGrid_transferNodes(_1,_2,_3,_4,_5){if(!this.$67u("transferNodes",_1,_2,_3,_4,_5)){return}
_2=_2||this.data.root;var _6=_4.getData(),_7=(isc.isA.Tree(_6)&&isc.isA.Tree(this.data)&&_6.getRoot()==this.data.getRoot());var _8=this.getDataSource(),_9=_4.getDataSource();if(_7&&(this.dragDataAction!=isc.TreeGrid.COPY&&this.dragDataAction!=isc.TreeGrid.CLONE))
{if(_8!=null&&this.data!=null&&isc.ResultTree&&isc.isA.ResultTree(this.data))
{this.$67k[0].noRemove=true;var _10=isc.rpc.startQueue();var _11=_6.getChildren(_2);var _12,_13;if(_3!=null){if(_3<_11.length){_12=_11[_3]}}
if(_12==_13){_12=_11[_11.length-1]}
for(var i=0;i<_1.length;i++){var _15=_1[i];if(this.shouldSaveLocally()||_15[this.data.parentIdField]==_2[this.data.idField])
{if(_3!=null){_11=_6.getChildren(_2);_6.move(_15,_2,_11.indexOf(_12))}}else{var _16=_1[i]["_isOpen_"+this.data.ID];var _15=isc.addProperties({},this.data.getCleanNodeData(_1[i],true,false)),_17=isc.addProperties({},_15);if(_16!=null)_15["_isOpen_"+this.data.ID]=_16;_15[this.data.parentIdField]=_2[this.data.idField];var _18=null,_19=this.data.getChildren(_2);if(_3==null){_18=_19.get(_19.length-1)}else if(_3>0){_18=_19.get(_3-1)}
this.updateDataViaDataSource(_15,_8,{oldValues:_17,parentNode:this.data.getParent(_1[i]),newParentNode:_2,dragTree:_6,draggedNode:_15,draggedNodeList:_1,dropNeighbor:_18,dropIndex:_3},_4)}}}else{_6.moveList(_1,_2,_3)}}else if(_8!=null){var _20;if(this.dragRecategorize||(_9!=null&&_9!=_8&&this.data!=null&&isc.ResultTree&&isc.isA.ResultTree(this.data)&&_4.dragDataAction==isc.TreeGrid.MOVE))
{var _21=_9.getTreeRelationship(_8);if(_21!=null&&_21.parentIdField){var _22=false,_23=_9.getPrimaryKeyFields();for(var _24 in _23){if(_24==_21.parentIdField){this.logWarn("dragRecategorize: data source has dataSource:"+_9.getID()+". foreignKey relationship with "+"target dataSource "+_8.getID()+" is based on primary key which cannot be modified.");_22=true}}
if(!_22)_20=true;this.logInfo("Recategorizing dropped nodes in dataSource:"+_9.getID())}
this.$67k[0].noRemove=true;var _10=isc.rpc.startQueue();for(var i=0;i<_1.length;i++){var _15={};var _25=_9.getPrimaryKeyFieldNames();for(var j=0;j<_25.length;j++){_15[_25[j]]=_1[i][_25[j]]}
if(_20){_15[_21.parentIdField]=_2[_21.idField]}
isc.addProperties(_15,this.getDropValues(_15,_9,_2,_3,_4));this.updateDataViaDataSource(_15,_9,null,_4)}}else{var _10=isc.rpc.startQueue();for(var i=0;i<_1.length;i++){var _27=_1[i],_28=this.data;if(_28){_27[_28.parentIdField]=_2[_28.idField]}
isc.addProperties(_27,this.getDropValues(_27,_9,_2,_3,_4));this.$61d(_27,_9,_4,null,_3,_2)}}}else{for(var i=0;i<_1.length;i++){this.$61d(_1[i],_9,_4,null,_3,_2)}}
if(!this.$67l){isc.Log.logDebug("Invoking transferDragData from inside transferNodes - no server "+"queries needed?","dragDrop");_4.transferDragData(this.$67n,this);if(_8){if(!this.$67o)isc.rpc.sendQueue()}}
this.$67m=false}
,isc.A.$67w=function isc_TreeGrid__updateComplete(_1,_2,_3){if(!_3.dragTree)return;if(_3.newParentNode!=this.data.root&&_3.dragTree.getParent(_3.newParentNode)==null)
{isc.logWarn("Target folder is no longer in the Tree in TreeGrid cache sync");return}
var _4=_3.dropNeighbor,_5=_3.dragTree,_6=_5.getChildren(_3.newParentNode),_7=_3.draggedNodeList,_8=_5.idField,_9=_7.findIndex(_8,_3.draggedNode[_8]),_10,_11;if(_4==null){_10=0}else{for(var i=0;i<_6.length;i++){var _13=_6[i];if(_13==_4){_10=i+1;break}}}
if(_10!==_11){while(_10<_6.length){var _14=_7.findIndex(_8,_6[_10][_8]);if(_14==-1||_14>_9)break;_10++}}
if(_10===_11){isc.logWarn("Could not order dropped node by reference to neighbor; trying absolute index");_10=_3.dropIndex}
if(_10===_11){isc.logWarn("Unable to determine drop location in TreeGrid cache sync");return}
var _15=this.data.find(_8,_3.draggedNode[_8]);_5.move(_15,this.data.getParent(_15),_10);this.Super("$67w",arguments)}
,isc.A.getTreeCellValue=function isc_TreeGrid_getTreeCellValue(_1,_2,_3,_4){if(_2==null){return _1}
var _5=this.data.getLevel(_2),_6=isc.TreeGrid.$82d(),_7=this.getCellCSSText(_2,_3,_4),_8=this.getCellStyle(_2,_3,_4);_6[1]=_8
_6[3]=_7
if(_6[3]!=null&&!_6[3].endsWith(this.$39))_6[3]+=this.$39;_6[5]=_7;_6[7]=_8;_6[9]=this.getIndentHTML(_5,_2);var _9=this.$342(_1,_2,_3,_4,true,_8,_7);for(var i=0,j=11;i<_9.length;i++){_6[j]=_9[i];j++}
_6[j]=this.$64o
return _6.join(isc.emptyString)}
,isc.A.$342=function isc_TreeGrid__getTreeCellTitleArray(_1,_2,_3,_4,_5,_6,_7){if(_7==null)_7=this.getCellCSSText(_2,_3,_4);if(_6==null)_6=this.getCellStyle(_2,_3,_4);var _8=isc.TreeGrid.$82e();_8[1]=_7;_8[3]=_6;if(_5){var _9=this.getOpenIcon(_2),_10=this.openerIconSize||(this.showConnectors?this.cellHeight:null),_11=(_3!=null?this.$34l+_3:null);if(_9){_8[5]=this.getIconHTML(_9,_11,_10)}else{_8[5]=this.$348(_10||this.iconSize)}}else _8[5]=null;var _12=this.$739(_2),_13=_12||this.getExtraIcon(_2),_14=(_3!=null?this.$349+_3:null),_15=(_12!=null?this.$65a():this.iconSize),_16=this.extraIconGap,_17=this.getIcon(_2),_18=(_3!=null?this.$34n+_3:null);_8[6]=(_13?this.getIconHTML(_13,_14,_15,_16):null);_8[7]=this.getIconHTML(_17,_18,_2.iconSize);_8[9]=_7;_8[11]=this.iconPadding;_8[13]=_6;_8[15]=this.wrapCells?null:"<NOBR>"
_8[16]=_1;return _8}
,isc.A.getCellAlign=function isc_TreeGrid_getCellAlign(_1,_2,_3){var _4=this.getField(_3);if(_4&&_4.treeField){return this.isRTL()?"right":"left"}
return this.Super("getCellAlign",arguments)}
,isc.A.getCellValue=function isc_TreeGrid_getCellValue(_1,_2,_3,_4,_5,_6,_7){var _8=this.invokeSuper(isc.TreeGrid,"getCellValue",_1,_2,_3,_4,_5,_6,_7);if(_3==this.getTreeFieldNum()){_8=this.getTreeCellValue(_8,_1,_2,_3)}
return _8}
,isc.A.bodyDrawing=function isc_TreeGrid_bodyDrawing(_1,_2,_3,_4,_5){this.$35a={};return this.invokeSuper(isc.TreeGrid,"bodyDrawing",_1,_2,_3,_4,_5)}
,isc.A.getNodeTitle=function isc_TreeGrid_getNodeTitle(_1,_2,_3){if(_3.name&&_3.name!=this.$34o){if(_2==-1)return _1[_3.name];return this.getEditedRecord(_2)[_3.name]}
return this.data.getTitle(_1)}
,isc.A.getTitleField=function isc_TreeGrid_getTitleField(){if(this.titleField!=null)return this.titleField;return this.getFieldName(this.getTreeFieldNum())}
,isc.A.getTreeFieldNum=function isc_TreeGrid_getTreeFieldNum(){return this.$34t}
,isc.A.getOpenAreaWidth=function isc_TreeGrid_getOpenAreaWidth(_1){var _2=this.getOpenerIconSize(_1),_3=(this.showConnectors?_2:this.indentSize);return((this.data.getLevel(_1)-(this.showRoot?0:1))*_3)+_2}
,isc.A.getOpenerIconSize=function isc_TreeGrid_getOpenerIconSize(_1){return(this.openerIconSize||(this.showConnectors?this.cellHeight:this.iconSize))}
,isc.A.clickInOpenArea=function isc_TreeGrid_clickInOpenArea(_1){if(!this.data.isFolder(_1))return false;var _2=this.getTreeFieldNum(),_3=this.getFieldBody(_2),_4=this.getLocalFieldNum(_2),_5=_3.getColumnLeft(_4),_6=_3.getColumnWidth(_4),_7=this.getOpenAreaWidth(_1),x=_3.getOffsetX();if(this.isRTL()){var _9=_5+_6;return x>=(_9-_7)&&x<=_9}else{return x>=_5&&x<_5+_7}}
,isc.A.clickInCheckboxArea=function isc_TreeGrid_clickInCheckboxArea(_1){if(this.selectionAppearance!=this.$12c)return false;var _2=this.getTreeFieldNum(),_3=this.getFieldBody(_2),_4=this.getLocalFieldNum(_2),_5=_3.getColumnLeft(_4),_6=_3.getColumnWidth(_4),_7=this.getOpenAreaWidth(_1),_8=this.$65a(),x=_3.getOffsetX();if(this.isRTL()){var _10=_5+_6;return(x>=(_10-_7-_8)&&x<=(_10-_7))}else{return(x>=(_5+_7)&&x<(_5+_7+_8))}}
,isc.A.getIndentHTML=function isc_TreeGrid_getIndentHTML(_1,_2){var _3=_1;if(!this.showRoot)_3--;var _4=(this.showConnectors?this.getOpenerIconSize(_2):this.indentSize);if(this.showConnectors&&this.showFullConnectors){var _5=this.data.$59a(_2);_5.remove(_1);if(!this.showRoot)_5.remove(0);if(_5.length!=0){if(!this.$59b){var _6=isc.Img.urlForState(this.connectorImage,null,null,"ancestor"),_7=this.getIconHTML(_6,null,this.cellHeight);this.$59c=_7}
var _8=this.$348(_4),_9=isc.StringBuffer.create(isc.emptyString);_9.append("<NOBR>");for(var i=(this.showRoot?0:1);i<_1;i++){if(_5.contains(i))_9.append(this.$59c);else _9.append(_8)}
_9=_9.release();return _9}}
var _11=this.$348(_3*_4);if(isc.Browser.isIE9||(isc.Browser.isStrict&&(isc.Browser.isIE7||isc.Browser.isIE8))){_11="<NOBR>"+_11+"</NOBR>"}
return _11}
,isc.A.$348=function isc_TreeGrid__indentHTML(_1){if(_1==0)return isc.emptyString;var _2=isc.TreeGrid.$35c;if(_2==null)_2=isc.TreeGrid.$35c={};if(_2[_1]==null)_2[_1]=isc.Canvas.spacerHTML(_1,1);return _2[_1]}
,isc.A.getOpenIcon=function isc_TreeGrid_getOpenIcon(_1){if(this.showOpener==false)return null;if(!this.data)return null;if(isc.isA.Number(_1))_1=this.data.get(_1);if(_1==null)return null;if(_1.openIcon){return _1.openIcon}else{var _2=this.data.isFolder(_1),_3=_2,_4=_2,_5,_6;if(_2){var _7=this.data.getLoadState(_1);if(_7==isc.Tree.UNLOADED||(_7==isc.Tree.FOLDERS_LOADED&&this.displayNodeType!=isc.Tree.FOLDERS_ONLY))
{_3=true;_4=false}else{_3=this.data.hasChildren(_1,this.displayNodeType);_4=_3&&this.data.isOpen(_1)}}
if(_4&&!this.showFullConnectors)_6=true
else{_6=!this.$35d(_1)}
_5=!this.$35e(_1);return this.getOpenerImageURL(_3,_4,_5,_6)}}
,isc.A.$35e=function isc_TreeGrid__shouldShowPreviousLine(_1){var _2=this.data.indexOf(_1);if(_2==0)return false;if(this.showFullConnectors)return true;var _3=this.getRecord(_2-1),_4=this.data.getParent(_1);if(_3==null)return false;return(_4==_3||_4==this.data.getParent(_3))}
,isc.A.$35d=function isc_TreeGrid__shouldShowNextLine(_1){if(this.showFullConnectors){var _2=this.data,_3=_2.getParent(_1),_4=_2.getChildren(_3);return _4.indexOf(_1)!=_4.length-1}
var _5=this.data.indexOf(_1),_6=this.getRecord(_5+1);if(_6==null)return false;return(this.data.getParent(_1)==this.data.getParent(_6))}
,isc.A.getOpenerImageURL=function isc_TreeGrid_getOpenerImageURL(_1,_2,_3,_4){if(!this.$35f){var _5=this.openerImage;this.$35f={opened:isc.Img.urlForState(_5,null,null,"opened"),closed:isc.Img.urlForState(_5,null,null,(this.isRTL()?"closed_rtl":"closed")),opening:isc.Img.urlForState(_5,null,null,"opening")}}
if(this.showConnectors&&!this.$35g){var _5=this.connectorImage,_6=["single","start","end","middle","opened_single","opened_start","opened_middle","opened_end","closed_single","closed_start","closed_middle","closed_end"],_7={},_8=this.isRTL(),_9="$35h";for(var i=0;i<_6.length;i++){var _11=_6[i],_12=_11;if(_8)_12+=_9;_7[_11]=isc.Img.urlForState(_5,null,null,_12)}
this.$35g=_7}
if(this.showConnectors){var _13=this.$35g;if(_1){if(_2){if(!this.showFullConnectors){if(_3)return _13.opened_single;return _13.opened_end}
if(_3&&_4)return _13.opened_single;else if(_3)return _13.opened_start;else if(_4)return _13.opened_end;else return _13.opened_middle}else{if(_3&&_4)return _13.closed_single;if(_3)return _13.closed_start;if(_4)return _13.closed_end;return _13.closed_middle}}else{if(_3&&_4)return _13.single;if(_3)return _13.start;if(_4)return _13.end;return _13.middle}}else{var _13=this.$35f;if(!_1)return null;if(_2)return _13.opened;return _13.closed}}
,isc.A.$739=function isc_TreeGrid__getCheckboxIcon(_1){var _2=null;if(this.selectionAppearance==this.$12c){var _3=this.selection.isSelected(_1)?true:false;var _4=(_3&&this.showPartialSelection&&this.selection.isPartiallySelected(_1))?true:false;_2=_4?(this.checkboxFieldPartialImage||this.booleanPartialImage):_3?(this.checkboxFieldTrueImage||this.booleanTrueImage):(this.checkboxFieldFalseImage||this.booleanFalseImage);if(!this.body.canSelectRecord(_1)){if(this.showDisabledSelectionCheckbox){_2=isc.Img.urlForState(_2,null,null,"Disabled")}else{_2="[SKINIMG]/blank.gif"}}}
return _2}
,isc.A.getExtraIcon=function isc_TreeGrid_getExtraIcon(_1){return null}
,isc.A.getIcon=function isc_TreeGrid_getIcon(_1,_2){if(isc.isA.Number(_1))_1=this.data.get(_1);if(!_1)return null;var _3=_1[this.customIconProperty],_4=(_3!=null),_5=this.data.isFolder(_1);if(!_4){if(_5)_3=this.folderIcon;else _3=this.nodeIcon}
var _6;if(_5){var _7=_2?false:(this.lastDropFolder==_1&&_1.$347),_8=_2?false:!!this.data.isOpen(_1);if(_7){if(_1.dropIcon!=null)_3=_1.dropIcon;else if(!_4&&this.folderDropImage!=null)_3=this.folderDropImage;else{var _9;if(_4){_9=_1[this.customIconDropProperty];if(_9==null)_9=this.showCustomIconDrop}else{_9=this.showDropIcons}
if(_9)_6=this.dropIconSuffix}}else if(_8){if(_1.openedIcon!=null)_3=_1.openedIcon;else if(!_4&&this.folderOpenImage!=null)_3=this.folderOpenImage;else{var _10;if(_4){_10=_1[this.customIconOpenProperty];if(_10==null)_10=this.showCustomIconOpen}else{_10=this.showOpenIcons}
if(_10)_6=this.openIconSuffix;else if(!_4)_6=this.closedIconSuffix}}else{if(!_4){if(this.folderClosedImage)_3=this.folderClosedImage;else _6=this.closedIconSuffix}}}else{if(!_4&&this.fileImage)_3=this.fileImage}
return isc.Img.urlForState(_3,false,false,_6)}
,isc.A.getIconHTML=function isc_TreeGrid_getIconHTML(_1,_2,_3,_4){if(_1==null)return isc.emptyString;if(_3==null)_3=this.iconSize;var _5=this.$35a.iconHTML;if(_5==null)_5=this.$35a.iconHTML={};if(_5[_1]==null){var _6;if(_4){var _7=this.$76g;_7[1]=_4;_6=_7.join(isc.emptyString)}
var _8=this.$4s;_8.src=_1;_8.width=_8.height=_3;_8.name=_2;_8.align=this.$34r;_8.extraStuff=_6;_5[_1]=this.$wf(_8)}
var _9=_5[_1];_9[14]=_2;return _9.join(isc.$ad)}
,isc.A.setRowIcon=function isc_TreeGrid_setRowIcon(_1,_2){if(!isc.isA.Number(_1))_1=this.data.indexOf(_1);if(_1!=-1&&this.getIcon(_1)!=null){this.setImage(this.$34n+_1,_2)}}
,isc.A.setNodeIcon=function isc_TreeGrid_setNodeIcon(_1,_2){_1[this.customIconProperty]=_2;this.setImage(this.$34n+this.getRecordIndex(_1),_2)}
,isc.A.getPrintHTML=function isc_TreeGrid_getPrintHTML(_1,_2){var _3=this.printExpandTree;if(_3==null)_3=_1?_1.expandTrees:null;if(_3&&this.data){if(isc.ResultTree&&isc.isA.ResultTree(this.data)&&this.data.loadDataOnDemand){this.logWarn("Printing TreeGrid with option to expand folders on print not supported "+"for load on demand trees.")}else{this.data.openAll()}}
return this.Super("getPrintHTML",arguments)}
,isc.A.getExportFieldValue=function isc_TreeGrid_getExportFieldValue(_1,_2,_3){var _4=this.Super("getExportFieldValue",arguments);if(_3==this.getTreeFieldNum()&&this.exportIndentString){var _5=this.data.getLevel(_1);while(--_5>0)_4=this.exportIndentString+_4}
return _4}
);isc.B._maxIndex=isc.C+70;isc.TreeGrid.registerStringMethods({folderOpened:"node",folderClosed:"node",folderClick:"viewer,folder,recordNum",leafClick:"viewer,leaf,recordNum",nodeClick:"viewer,node,recordNum",folderContextClick:"viewer,folder,recordNum",leafContextClick:"viewer,leaf,recordNum",nodeContextClick:"viewer,node,recordNum",dataArrived:"parentNode",onFolderDrop:"nodes,folder,index,sourceWidget"});isc.defineInterface("Observer").addInterfaceProperties({registerObserved:isc.ClassFactory.TARGET_IMPLEMENTS,unregisterObserved:isc.ClassFactory.TARGET_IMPLEMENTS});isc.defineInterface("AutoObserver","Observer").addInterfaceProperties({observedName:"observed",observations:{},registerObserved:function(_1){this[this.observedName]=_1;for(var _2 in this.observations){this.observe(_1,_2,this.observations[_2])}
if(isc.isA.Canvas(_1)){this.observe(_1,"destroy","observer.unregisterObserved(observed)")}},unregisterObserved:function(_1){this[this.observedName]=null;for(var _2 in this.observations){this.ignore(_1,_2)}}});isc.defineInterface("GridAutoObserver","AutoObserver").addInterfaceProperties({observedName:"grid"});isc.defineClass("GridTotalRowsIndicator","Label","GridAutoObserver");isc.A=isc.GridTotalRowsIndicator.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.height=1;isc.A.overflow="visible";isc.A.valign="center";isc.A.observations={"dataArrived":"observer.gridDataChanged()","setData":"observer.gridDataChanged()"};isc.A.dynamicContents=true;isc.A.contents="Total Rows: ${this.rowCount}";isc.A.rowCount="N/A";isc.B.push(isc.A.gridDataChanged=function isc_GridTotalRowsIndicator_gridDataChanged(){var _1=this.grid.data;if(!_1)this.rowCount="N/A";if(isc.isA.ResultSet(_1)){if(_1.lengthIsKnown())this.rowCount=_1.getLength();else this.rowCount="N/A"}else if(isc.isAn.Array(_1)){this.rowCount=_1.getLength()}
this.markForRedraw()}
);isc.B._maxIndex=isc.C+1;isc.defineClass("ObserverToolStrip","ToolStrip");isc.A=isc.ObserverToolStrip.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.initWidget=function isc_ObserverToolStrip_initWidget(){this.Super("initWidget",arguments);for(var i=0;i<this.members.length;i++){var m=this.members[i];if(isc.isAn.Observer(m)){m.registerObserved(this.grid)}}}
);isc.B._maxIndex=isc.C+1;isc.defineClass("GridToolStrip","ObserverToolStrip");isc.A=isc.GridToolStrip.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.membersMargin=5;isc.A.addButtonDefaults={_constructor:"Img",size:16,layoutAlign:"center",src:"[SKIN]/actions/add.png",click:"this.creator.grid.startEditingNew()"};isc.A.removeButtonDefaults={_constructor:"Img",size:16,layoutAlign:"center",src:"[SKIN]/actions/remove.png",click:"this.creator.grid.removeSelectedData()"};isc.A.refreshButtonDefaults={_constructor:"Img",size:16,layoutAlign:"center",src:"[SKIN]/actions/refresh.png",click:"this.creator.grid.invalidateCache()"};isc.A.exportButtonDefaults={_constructor:"IButton",title:"Export to CSV",layoutAlign:"center",click:"this.creator.grid.exportData()"};isc.A.totalRowsIndicatorDefaults={_constructor:"GridTotalRowsIndicator",layoutAlign:"center"};isc.A.members=["autoChild:removeButton","autoChild:addButton","autoChild:exportButton","starSpacer","autoChild:refreshButton","autoChild:totalRowsIndicator"];isc.B.push(isc.A.initWidget=function isc_GridToolStrip_initWidget(){this.Super("initWidget",arguments)}
);isc.B._maxIndex=isc.C+1;isc.ClassFactory.defineClass("RecordEditor","ListGrid");isc.A=isc.RecordEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.autoDraw=false;isc.A.cellSpacing=0;isc.A.cellPadding=0;isc.A.selectionType="none";isc.A.showRollOver=false;isc.A.baseStyle="recordEditorCell";isc.A.showHeader=false;isc.A.showEmptyMessage=false;isc.A.bodyOverflow="hidden";isc.A.fixedRecordHeights=true;isc.A.drawAllMaxCells=0;isc.A.skinImgDir="images/RecordEditor/";isc.A.saveImg={src:"[SKIN]add.png",width:16,height:16,showOver:true};isc.A.filterImg={src:"[SKIN]filter.png",width:16,height:16,showOver:true};isc.A.actionButtonStyle="normal";isc.A.listEndEditAction="next";isc.A.canEdit=true;isc.A.editByCell=false;isc.A.$jo=false;isc.A.bodyStyleName="normal";isc.A.styleName="normal";isc.B.push(isc.A.shouldAutoFitField=function isc_RecordEditor_shouldAutoFitField(){return false}
,isc.A.$32t=function isc_RecordEditor__showEditClickMask(){}
);isc.B._maxIndex=isc.C+2;isc.A=isc.RecordEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$65g="filter";isc.B.push(isc.A.initWidget=function isc_RecordEditor_initWidget(){if(this.sourceWidget!=null){var _1=this.sourceWidget;this.setWidth(_1.getInnerContentWidth());this.observe(_1,"resized","observer.sourceWidgetResized(observed)");this.leaveScrollbarGap=this.sourceWidget.leaveScrollbarGap;if(this.isAFilterEditor()){this.actOnCellChange=this.sourceWidget.filterByCell;this.actOnKeypress=this.sourceWidget.filterOnKeypress}else{this.actOnCellChange=this.sourceWidget.saveByCell}
isc.addProperties(this.editFormDefaults,{autoFocus:false});this.fieldIDProperty=this.sourceWidget.fieldIDProperty;this.fields=this.sourceWidget.completeFields.duplicate();this.cellHeight=this.getInnerHeight()}else{this.logWarn("RecordEditor initialized without a sourceWidget property. "+"This widget is not supported as a standalone component.")}
return this.Super(this.$oc)}
,isc.A.destroy=function isc_RecordEditor_destroy(){this.ignore(this.sourceWidget,"resized");this.Super("destroy",arguments)}
,isc.A.sourceWidgetResized=function isc_RecordEditor_sourceWidgetResized(_1){this.setWidth(_1.getInnerContentWidth())}
,isc.A.isAFilterEditor=function isc_RecordEditor_isAFilterEditor(){return(this.actionType==this.$65g)}
,isc.A.$315=function isc_RecordEditor__formatCellValue(_1,_2,_3,_4,_5){if(_3.isRemoveField)return"&nbsp;"
return this.Super("$315",arguments)}
,isc.A.$425=function isc_RecordEditor__useDisplayFieldValue(_1){return false}
,isc.A.draw=function isc_RecordEditor_draw(){var _1=this.sourceWidget;this.setWidth(_1.getInnerContentWidth());var _2=this.findNextEditCell(0,0,1,true,true,false,true,true);if(_2==null){this.logWarn("No editable fields in this record editor."+(this.isAFilterEditor()?" Check the 'canFilter' property for each field in ":" Check the 'canEdit' property for each field in ")+this.sourceWidget.getID())}else{var _3=this.isAFilterEditor(),_4;if(_3){_4=this.sourceWidget.$32e()}else{var _5;_4={};for(var i=0;i<this.fields.length;i++){var _7=this.fields[i];if(_7.defaultValue!==_5){_4[_7[this.fieldIdProperty]]=_7.defaultValue}}}
if(this.isAFilterEditor())this.setValuesAsCriteria(_4);else this.setEditValues(0,_4);var _8=_2[1];this.$31u(0,_8)}
this.Super("draw",arguments);this.$35i.bringToFront()}
,isc.A.setFields=function isc_RecordEditor_setFields(){this.Super("setFields",arguments);var _1=this.findNextEditCell(0,0,1,true,true);if(_1==null)_1=[0,0];if(this.isDrawn()&&!this.$30a)this.$31u(0,_1[1])}
,isc.A.createChildren=function isc_RecordEditor_createChildren(){this.Super("createChildren",arguments);var _1=this.getEditForm();if(_1!=null&&this.$789){_1.setValuesAsCriteria(this.$789);delete this.$789;if(this.isAFilterEditor())_1.isSearchForm=true}
if(!this.$35i)this.makeActionButton()}
,isc.A.makeActionButton=function isc_RecordEditor_makeActionButton(){var _1;if(this.isAFilterEditor()){_1=this.filterImg}else{_1=this.saveImg}
this.$35i=isc.Button.create({recordEditor:this,left:this.getInnerWidth()-this.getScrollbarSize(),width:this.getScrollbarSize(),autoDraw:false,baseStyle:this.actionButtonStyle,skinImgDir:this.skinImgDir,icon:_1.src,showRollOverIcon:_1.showOver,showFocusedIcon:_1.showFocused,showFocusedAsOver:false,prompt:this.actionButtonPrompt,click:function(){this.recordEditor.performAction()}},this.actionButtonDefaults,this.actionButtonProperties);this.addChild(this.$35i)}
,isc.A.performAction=function isc_RecordEditor_performAction(_1){if(this.isAFilterEditor())this.performFilter(_1);else this.performSave(_1)}
,isc.A.setValuesAsCriteria=function isc_RecordEditor_setValuesAsCriteria(_1,_2){var _3=this.getEditForm();if(_3==null){this.$789=_1;return}
_3.setValuesAsCriteria(_1);if(_2)this.refreshRow(0)}
,isc.A.makeEditForm=function isc_RecordEditor_makeEditForm(){var _1=this.editFormProperties=this.editFormProperties||{};if(_1.allowExpressions==null&&this.allowFilterExpressions!=null)
_1.allowExpressions=this.allowFilterExpressions;_1.expressionDataSource=this.expressionDataSource;var _2=this.getEditForm(),_3;if(_2==null){_3=this.$789}else{_3=_2.getValuesAsCriteria();_2.expressionDataSource=this.expressionDataSource}
this.Super("makeEditForm",arguments);_2=this.getEditForm();if(_2!=null){_2.setValuesAsCriteria(_3);delete this.$789;if(this.isAFilterEditor())_2.isSearchForm=true}}
,isc.A.updateDataSource=function isc_RecordEditor_updateDataSource(_1){this.expressionDataSource=_1;var _2=this.getEditForm();if(_2)_2.expressionDataSource=this.expressionDataSource}
,isc.A.getEditDisplayValue=function isc_RecordEditor_getEditDisplayValue(_1,_2,_3){if(this.isAFilterEditor()&&this.$286!=null){var _4=this.getFieldName(_2);return this.$286.getValue(_4)}
return this.Super("getEditDisplayValue",arguments)}
,isc.A.performFilter=function isc_RecordEditor_performFilter(_1){var _2=this.$74n(this.sourceWidget.autoFetchTextMatchStyle);var _3={};if(_1)_3.showPrompt=false;_3.textMatchStyle=this.sourceWidget.autoFetchTextMatchStyle;var _4=this.sourceWidget.data;if((isc.isA.ResultSet(_4)&&_4.willFetchData(_2,_3.textMatchStyle))||(isc.isA.Tree(_4)&&this.sourceWidget.dataSource!=null))
{this.fireOnPause("performFilter",{target:this.sourceWidget,methodName:"handleFilterEditorSubmit",args:[_2,_3]},this.fetchDelay)}else{this.sourceWidget.handleFilterEditorSubmit(_2,_3)}}
,isc.A.$74n=function isc_RecordEditor__getFilterCriteria(_1){var _2=this.getEditForm();if(_2==null){return this.$789}
return _2.getValuesAsCriteria(null,_1)}
,isc.A.performSave=function isc_RecordEditor_performSave(_1){var _2=this.getEditRow(),_3=this.getEditCol(),_4=this.getFieldName(_3),_5=this.$286.getValue(_4);this.setEditValue(_2,_3,_5);var _6=this.getEditValues(0),_7=this.getFields().getProperty(this.fieldIdProperty);if(!this.validateRowValues(_6,{},0,_7))return;this.$321(0);this.$31u(0,0);for(var _4 in _6){this.refreshCell(0,_3)}}
,isc.A.getValues=function isc_RecordEditor_getValues(){var _1=this.getEditCol(),_2=this.getEditFieldName();this.setEditValue(0,_1,this.$286.getValue(_2));var _3=this.$74n();return _3}
,isc.A.getValuesAsCriteria=function isc_RecordEditor_getValuesAsCriteria(_1){var _2=this.getEditCol(),_3=this.getEditFieldName();var _4=this.$74n(_1);return _4}
,isc.A.canEditCell=function isc_RecordEditor_canEditCell(_1,_2){if(this.isAFilterEditor()){var _3=this.getField(_2);if(_3==null)return false;return(_3.canFilter!=false)}else{return this.sourceWidget.canEditCell(_1,_2)}}
,isc.A.getEditorValueMap=function isc_RecordEditor_getEditorValueMap(_1,_2){if(this.isAFilterEditor()){return this.sourceWidget.getFilterEditorValueMap(_1)}else{return this.sourceWidget.getEditorValueMap(_1,_2)}}
,isc.A.getEditorType=function isc_RecordEditor_getEditorType(_1,_2){if(this.isAFilterEditor()){return this.sourceWidget.getFilterEditorType(_1)}else{return this.sourceWidget.getEditorType(_1,_2)}}
,isc.A.$45r=function isc_RecordEditor__editorChanged(){this.form.grid.editorChanged(this)}
,isc.A.editorChanged=function isc_RecordEditor_editorChanged(_1){var _2=_1.actOnKeypress!=null?_1.actOnKeypress:this.actOnKeypress;if(_2){this.performAction(true)}}
,isc.A.getEditorProperties=function isc_RecordEditor_getEditorProperties(_1){var _2={height:this.cellHeight};if(_1.displayField&&!_1.optionDataSource)
_2.optionDataSource=this.sourceWidget.dataSource;if(this.isAFilterEditor()){_2.allowEmptyValue=true;_2.changed=this.$45r;_2.actOnKeypress=_1.filterOnKeypress;return isc.addProperties(_2,this.sourceWidget.getFilterEditorProperties(_1))}else{return isc.addProperties(_2,this.sourceWidget.getEditorProperties(_1))}}
,isc.A.getEditItem=function isc_RecordEditor_getEditItem(_1,_2,_3,_4,_5,_6){var _7=this.Super("getEditItem",arguments);if(!this.isAFilterEditor())return _7;var _8=this.sourceWidget.getFilterEditorProperties(_1),_9;if(!_8)_8={};if(_1.defaultValue!=null&&_8.defaultValue===_9){delete _7.defaultValue}
if(_1.change!=null&&_8.change===_9){delete _7.change}
if(_1.defaultDynamicValue!=null&&_8.defaultDynamicValue!=null)
{delete _7.defaultDynamicValue}
if(_1.icons!=null&&_8.icons===_9){delete _7.icons}
if(_1.showPickerIcon!=null&&_8.showPickerIcon===_9){delete _7.showPickerIcon}
return _7}
,isc.A.getDefaultEditValue=function isc_RecordEditor_getDefaultEditValue(_1,_2){if(this.isAFilterEditor())return null;return this.Super("getDefaultEditValue",arguments)}
,isc.A.cellEditEnd=function isc_RecordEditor_cellEditEnd(_1,_2){if(_1!=isc.ListGrid.ENTER_KEYPRESS&&_1!=isc.ListGrid.TAB_KEYPRESS&&_1!=isc.ListGrid.SHIFT_TAB_KEYPRESS&&_1!=isc.ListGrid.EDIT_FIELD_CHANGE)return true;var _3;if(_2===_3)_2=this.getUpdatedEditorValue();var _4=this.getEditRow(),_5=this.getEditCol();this.setEditValue(_4,_5,_2);if(_1==isc.ListGrid.ENTER_KEYPRESS||this.actOnCellChange){this.performAction();if(_1==isc.ListGrid.ENTER_KEYPRESS)return}
var _6=this.getNextEditCell(_4,_5,_1);if(_6==null||_6[0]!=_4){if(_1==isc.ListGrid.TAB_KEYPRESS){this.$35i.focus()}else{this.$kf(false)}
return}
return this.Super("cellEditEnd",arguments)}
,isc.A.clearEditValue=function isc_RecordEditor_clearEditValue(_1,_2){return this.Super("clearEditValue",[_1,_2,null,true])}
,isc.A.layoutChildren=function isc_RecordEditor_layoutChildren(){this.Super("layoutChildren",arguments);if(this.$35i)
this.$35i.setLeft(this.getInnerWidth()-this.getScrollbarSize())
if(this.body){var _1=this.body;if(this.bodyLayout)_1=this.bodyLayout;_1.setWidth(this.getInnerWidth()-this.getScrollbarSize())}
if(this.$35i&&this.body)this.$35i.$sq(this.body)}
,isc.A.adjustOverflow=function isc_RecordEditor_adjustOverflow(){this.Super("adjustOverflow",arguments);if(this.$35i){this.$35i.setHeight(this.body.getVisibleHeight())}}
,isc.A.bodyScrolled=function isc_RecordEditor_bodyScrolled(_1,_2){this.Super("bodyScrolled",arguments);if(this.$35j!=null){isc.Timer.clear(this.$35j)}
this.$35j=this.delayCall("syncSourceScrolling",[],0)}
,isc.A.syncSourceScrolling=function isc_RecordEditor_syncSourceScrolling(){var _1=this.body.getScrollLeft();if(this.sourceWidget&&this.sourceWidget.body&&this.sourceWidget.body.getScrollLeft()!=_1){this.sourceWidget.body.scrollTo(_1)}}
,isc.A.getFieldWidths=function isc_RecordEditor_getFieldWidths(){var _1=this.sourceWidget.getFieldWidths();if(isc.isA.Array(_1))_1=_1.duplicate();return _1}
,isc.A.$57q=function isc_RecordEditor__correctForActionButtonClipping(){return(this.sourceWidget&&this.sourceWidget.body&&!this.sourceWidget.body.vscrollOn)}
,isc.A.getEditFormItemFieldWidths=function isc_RecordEditor_getEditFormItemFieldWidths(_1){var _2=this.Super("getEditFormItemFieldWidths",arguments);if(this.$57q()){var _3=_2.sum(),_4=this.getInnerWidth()-this.getScrollbarSize();if(_3>_4){_2[_2.length-1]-=Math.min(this.getScrollbarSize(),(_3-_4))}}
return _2}
,isc.A.getCellAlign=function isc_RecordEditor_getCellAlign(_1,_2,_3,_4){if(!_4&&_3==this.fields.getLength()-1){return isc.Browser.isRTL?"right":"left"}
return this.Super("getCellAlign",arguments)}
,isc.A.getCellValue=function isc_RecordEditor_getCellValue(_1,_2,_3,_4){var _5=this.fields[_3];if(_5&&(this.isCheckboxField(_5)||this.isExpansionField(_5)||this.isRowNumberField(_5)))
return"&nbsp;"
var _6=this.Super("getCellValue",arguments);if(_3==this.fields.getLength()-1){var _5=this.getField(_3),_7=this.getEditForm(),_8=_7?_7.getItem(_5.name):null,_9=(_8!=null)?_8.getWidth():null;if(_9!=null){_6="<div style='text-align:"+this.getCellAlign(_1,_2,_3,true)+";width:"+_9+"px;'>"+_6+"</div>"}}
return _6}
,isc.A.rebuildForFreeze=function isc_RecordEditor_rebuildForFreeze(){}
,isc.A.rowClick=function isc_RecordEditor_rowClick(){}
,isc.A.rowDoubleClick=function isc_RecordEditor_rowDoubleClick(){}
);isc.B._maxIndex=isc.C+42;isc.ClassFactory.defineClass("Menu","ListGrid");isc.A=isc.Menu;isc.A.standardFields={icon:"ICON_FIELD",title:"TITLE_FIELD",key:"KEY_FIELD",subMenu:"SUBMENU_FIELD"};isc.A.ICON_FIELD={name:"icon",width:25,getCellValue:function(_1,_2){return _1.getIcon(_2)}};isc.A.TITLE_FIELD={name:"title",width:"*",getCellValue:function(_1,_2){return _1.getItemTitle(_2)}};isc.A.KEY_FIELD={name:"keys",width:35,getCellValue:function(_1,_2){return _1.getKeyTitle(_2)}};isc.A.SUBMENU_FIELD={name:"submenu",width:18,getCellValue:function(_1,_2){return _1.getSubmenuImage(_2)}};isc.A.$kc=[];isc.A.SHIFT=(isc.Browser.isWin?"Shift+":"shift-");isc.A.CTRL=(isc.Browser.isWin?"Ctrl+":"ctrl-");isc.A.ALT=(isc.Browser.isWin?"Alt+":"option-");isc.A.META=(isc.Browser.isWin?"Windows+":"command-");isc.A=isc.Menu.getPrototype();isc.A.fixedIconWidth=true;isc.A.styleName="normal";isc.A.bodyStyleName="normal";isc.A.submenuDelay=200;isc.A.submenuOffset=-4;isc.A.defaultWidth=150;isc.A.defaultHeight=20;isc.A.enforceMaxHeight=true;isc.A.maxHeight=null;isc.A.backgroundColor=null;isc.A.overflow=isc.Canvas.VISIBLE;isc.A.bodyOverflow=isc.Canvas.VISIBLE;isc.A.arrowKeyAction="focus";isc.A.selectionType=isc.Selection.NONE;isc.A.autoDraw=false;isc.A.tableStyle="menuTable";isc.A.showRollOver=true;isc.A.showFocusOutline=false;isc.A.showAllRecords=true;isc.A.fixedFieldWidths=false;isc.A.fixedRecordHeights=false;isc.A.leaveScrollbarGap=false;isc.A.baseStyle="menu";isc.A.alternateRecordStyles=false;isc.A.showHeader=false;isc.A.showSortArrow=isc.ListGrid.NONE;isc.A.canDrag=false;isc.A.canAcceptDrop=false;isc.A.canReorderRecords=false;isc.A.useKeys=true;isc.A.showKeys=true;isc.A.showIcons=true;isc.A.showSubmenus=true;isc.A.submenuDirection="right";isc.A.showFieldsSeparately=false;isc.A.emptyMessage="[Empty menu]";isc.A.cellSpacing=0;isc.A.cellPadding=2;isc.A.iconWidth=16;isc.A.iconHeight=16;isc.A.autoSetDynamicItems=true;isc.A.skinImgDir="images/Menu/";isc.A.submenuImage={src:"[SKIN]submenu.gif",width:7,height:7};isc.A.submenuDisabledImage={src:"[SKIN]submenu_disabled.gif",width:7,height:7};isc.A.checkmarkImage={src:"[SKIN]check.gif",width:9,height:9};isc.A.checkmarkDisabledImage={src:"[SKIN]check_disabled.gif",width:9,height:9};isc.A.useBackMask=true;isc.A.submenuInheritanceMask=["submenuConstructor","$35k","className","submenuDelay","submenuOffset","defaultWidth","backgroundColor","tableStyle","showRollOver","baseStyle","emptyMessage","canDrag","canAcceptDrop","canReorderRecords","useKeys","showKeys","showIcons","showSubmenus","submenuDirection","cellPadding","iconWidth","iconHeight","autoSetDynamicItems","skinImgDir","submenuImage","submenuDisabledImage","checkmarkImage","checkmarkDisabledImage","bodyDefaults","itemClick","canSelectParentItems","childrenProperty","inheritedProperties"];isc.A.mergeSingleParent=true;isc.A.autoDismiss=true;isc.A.cascadeAutoDismiss=true;isc.A.autoDismissOnBlur=true;isc.A.fetchSubmenus=true;isc.A=isc.Menu.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$oz="left";isc.A.$o1="right";isc.B.push(isc.A.initWidget=function isc_Menu_initWidget(){this.$35l();this.position=isc.Canvas.ABSOLUTE;if(this.dataSource!=null&&!this.hasFlatDataSource()){var _1=this.initialCriteria||this.criteria;var _2=this.createResultTree(_1,null,{showPrompt:false,dataProperties:{autoOpenRoot:false}});if(this.items)_2.addList(this.items,_2.getRoot());if(this.loadDataOnDemand==false){_2.loadChildren(_2.getRoot(),{caller:this,methodName:"treeDataLoaded"})}
this.data=_2}else if(this.dataSource!=null){var _3=isc.DataSource.get(this.dataSource);_3.fetchData(null,{caller:this,methodName:"flatDataLoaded"})}
if(this.data==null&&this.items!=null)this.data=this.items;if(isc.Tree&&isc.isA.Tree(this.data)){this.$35k=this.data;this.childrenProperty=this.data.childrenProperty;var _4=this.data.getChildren(),_5=this.mergeSingleParent&&!isc.isA.ResultTree(this.$35k)&&_4.length==1&&this.data.hasChildren(_4[0]);if(_5){var _6=_4[0];this.data=this.data.getChildren(_6).duplicate();this.data.add({isSeparator:true});var _7={};isc.addProperties(_7,_6);_7[this.childrenProperty]=null;this.data.add(_7)}else{this.data=null}}
this.Super(this.$oc);if(!this.fields){this.$51r=true;this.fields=[];var _8=(this.submenuDirection==this.$oz);if(_8&&this.showSubmenus)this.fields.add(isc.Menu.SUBMENU_FIELD);if(this.showIcons)this.fields.add(isc.Menu.ICON_FIELD);this.fields.add(isc.Menu.TITLE_FIELD);if(this.showKeys)this.fields.add(isc.Menu.KEY_FIELD);if(!_8&&this.showSubmenus)this.fields.add(isc.Menu.SUBMENU_FIELD)}else{this.$51r=false;for(var i=0;i<this.fields.length;i++){var _10=this.fields[i];if(isc.isA.String(_10)){if(isc.Menu.standardFields[_10]!=null){this.fields[i]=isc.Menu[isc.Menu.standardFields[_10]]}else{this.logWarn("Menu field specified as :"+_10+". This is not a recognized standard field name");this.fields.removeAt(i);i-=1}}}}
if(this.iconBodyStyleName!=null&&this.fields!=null){for(var i=0;i<this.fields.length;i++){if(this.fields[i]=="icon"||this.fields[i]==isc.Menu.ICON_FIELD){this.bodyStyleName=this.iconBodyStyleName;break}}}
if(isc.Browser.isSafari){isc.addProperties(this.submenuImage,{align:"BOTTOM"});isc.addProperties(this.submenuDisabledImage,{align:"BOTTOM"})}
if(this.useKeys)this.setUpKeyListening();if(this.$35k){if(!this.treeParentNode)this.treeParentNode=this.$35k.getRoot();this.setTreeNode(this.treeParentNode)}}
,isc.A.hasFlatDataSource=function isc_Menu_hasFlatDataSource(){var _1=isc.DataSource.get(this.dataSource);var _2=_1.getFieldNames();var _3=false,_4=false;for(var i=0;i<_2.length;i++){var _6=_1.getField(_2[i]);if(_6.primaryKey)_3=true;if(_6.foreignKey)_4=true}
return!(_3&&_4)}
,isc.A.setFields=function isc_Menu_setFields(_1,_2,_3,_4,_5){if(_1&&(_1!=this.fields)){this.$51r=false}
this.invokeSuper(isc.Menu,"setFields",_1,_2,_3,_4,_5)}
,isc.A.treeDataLoaded=function isc_Menu_treeDataLoaded(){this.treeDataArrived(this.$35m)}
,isc.A.setTreeNode=function isc_Menu_setTreeNode(_1){var _2=this.$35k.getLoadState(_1);this.$35m=_1;if(_2==isc.Tree.LOADED){this.treeDataArrived(_1)}else if(_2!=isc.Tree.LOADING){this.$35k.loadChildren(_1,this.getID()+".treeDataArrived(node)");this.$35n=true;this.setData(null)}}
,isc.A.treeDataArrived=function isc_Menu_treeDataArrived(_1){delete this.$35n;if(_1==this.$35m){this.setData(this.$35k.getChildren(_1));if(this.masterMenu&&this.masterMenu.isVisible())
this.masterMenu.placeSubmenu(_1,this)}}
,isc.A.flatDataLoaded=function isc_Menu_flatDataLoaded(_1,_2){this.setData(_2)}
,isc.A.getEmptyMessage=function isc_Menu_getEmptyMessage(){if(this.$35n){return this.loadingDataMessage==null?"&nbsp;":this.loadingDataMessage.evalDynamicString(this,{loadingImage:this.imgHTML(isc.Canvas.loadingImageSrc,isc.Canvas.loadingImageSize,isc.Canvas.loadingImageSize)})}
return this.Super("getEmptyMessage",arguments)}
,isc.A.isEmpty=function isc_Menu_isEmpty(){if(this.$35n)return true;return this.Super("isEmpty",arguments)}
,isc.A.$35l=function isc_Menu__setUpEmptyMessage(){isc.addProperties(this,{emptyMessageTableStyle:this.tableStyle,emptyMessageStyle:this.baseStyle+isc.GridRenderer.standardStyleSuffixes[4]})}
,isc.A.$31m=function isc_Menu__observeData(_1,_2,_3,_4,_5){var _6;if(this.$35k){_6=this.isObserving(this.$35k,"dataChanged");_1=this.$35k}
if(!_6)this.invokeSuper(isc.Menu,"$31m",_1,_2,_3,_4,_5);if(this.autoSetDynamicItems){this.$35o()}}
,isc.A.$31j=function isc_Menu__ignoreData(_1){this.Super("$31j",arguments);if(this.autoSetDynamicItems){delete this.setDynamicItems}}
,isc.A.rowClick=function isc_Menu_rowClick(_1,_2,_3){this.Super("rowClick",arguments);this.selectMenuItem(_2,_3)}
,isc.A.selectMenuItem=function isc_Menu_selectMenuItem(_1,_2){if(_1==null)_1=this.getEventRecordNum();_1=this.getItem(_1);var _3=true;if(_1==null||!this.itemIsEnabled(_1)){isc.Menu.hideAllMenus("itemClick");return false}
if(this.hasSubmenu(_1)&&!this.canSelectParentItems&&!_1.canSelectParent){if(this.submenuTimer)this.submenuTimer=isc.Timer.clear(this.submenuTimer);if(this.$35p!=_1)this.hideSubmenu();this.showSubmenu(_1);return false}
var _4=this;while(_4.$35q){_4=_4.$35q}
if(this.autoDismiss&&(_1.autoDismiss||_1.autoDismiss==null)){isc.Menu.hideAllMenus("itemClick")}
if(_1.action){if(!isc.isA.Function(_1.action)){isc.Func.replaceWithMethod(_1,"action","")}
if(_1.action()==false)return false}
if(_1.click){if(!isc.isA.Function(_1.click)){isc.Func.replaceWithMethod(_1,"click","target,item,menu,colNum")}
var _5=(this.target?this.target:this);_3=_1.click(_5,_1,this,_2)}
if(_3!=false){_3=this.itemClick(_1,_2)}
if(!(this.autoDismiss&&(_1.autoDismiss||_1.autoDismiss==null))){this.refreshRow(this.getRecordIndex(_1))}
return _3}
,isc.A.mouseOver=function isc_Menu_mouseOver(){var _1=this.$35q;if(_1&&_1.body.lastOverRow!=this.$35r){if(_1.submenuTimer)_1.submenuTimer=isc.Timer.clear(_1.submenuTimer);_1.$88(this.$35r)}}
,isc.A.rowOver=function isc_Menu_rowOver(_1,_2){if(this.submenuTimer)this.submenuTimer=isc.Timer.clear(this.submenuTimer);this.submenuTimer=isc.Timer.setTimeout({target:this,method:this.changeSubmenu},this.submenuDelay)}
,isc.A.itemClick=function isc_Menu_itemClick(_1,_2){}
,isc.A.getShowSubmenuKey=function isc_Menu_getShowSubmenuKey(){return this.submenuDirection=="right"?"Arrow_Right":"Arrow_Left"}
,isc.A.getHideSubmenuKey=function isc_Menu_getHideSubmenuKey(){return this.submenuDirection=="right"?"Arrow_Left":"Arrow_Right"}
,isc.A.bodyKeyPress=function isc_Menu_bodyKeyPress(_1,_2){var _3=isc.EventHandler.lastEvent.keyName;if(_3==this.getHideSubmenuKey()){if(this.$35q!=null){this.$35q.hideSubmenu();this.$35q.focus();return false}}else if(_3==this.getShowSubmenuKey()){var _4=this.getItem(this.getFocusRow());if(this.hasSubmenu(_4)){this.changeSubmenu();this.$35s.$318(1);return false}}else if(_3=="Escape"&&this.autoDismissOnBlur!=false){if(this.$35q!=null){this.$35q.hideSubmenu();this.$35q.focus()}else{isc.Menu.hideAllMenus("outsideClick")}
return false}else if(_3=="Enter"){return this.$240()}
return this.Super("bodyKeyPress",arguments)}
,isc.A.$318=function isc_Menu__navigateToNextRecord(_1){var _2=this.getFocusRow();if(_2==null)_2=0;if(_1==-1){do{_2+=_1;if(_2<0){this.hide();return false}}while(!this.itemIsEnabled(_2))}
return this.Super("$318",arguments)}
,isc.A.show=function isc_Menu_show(_1){if(this.$35t){this.moveTo(this.$35u[0],this.$35u[1]);this.$35t=null}
if(_1==null)_1=this.showAnimationEffect;var _2=!this.$35v&&(_1!=null)&&(_1!="none");if(_2){this.$35v=true;this.animateShow(_1,"this.$35w()")
return}
if(this.setDynamicItems)this.setDynamicItems()
if(!this.isDrawn()){this.draw(true)}
this.body.focusOnHide=isc.EH.getFocusCanvas();isc.Menu.$kc.add(this);if(this.autoDismissOnBlur)isc.Menu.$781();this.bringToFront();this.Super("show",arguments);if(!this.$35v)this.$35w()}
,isc.A.$35w=function isc_Menu__showComplete(){if(this.$35v)delete this.$35v;if(isc.Browser.isMoz){this.getClipHandle().offsetLeft}
this.body.focus()}
,isc.A.hide=function isc_Menu_hide(){if(this.visibility==isc.Canvas.HIDDEN)return;this.Super("hide",arguments);this.$91y();this.clearLastHilite();this.$31q=null;if(this.$35p)delete this.$35p;if(this.submenuTimer)isc.Timer.clearTimeout(this.submenuTimer)}
,isc.A.showContextMenu=function isc_Menu_showContextMenu(_1){if(_1&&(_1.target==this||(this.body&&_1.target==this.body))){if(this.body){if(isc.Browser.isSafari){this.body.$29u=this.getEventRow();this.body.$29v=this.getEventColumn()}
this.body.click()}
return false}
var _2;if(isc.isA.Canvas(_1))_2=_1;if(_1!=null&&_1.target!=null)_2=_1.target;if(_2!=null)this.target=_2;this.positionContextMenu();this.show();return false}
,isc.A.getMaxHeight=function isc_Menu_getMaxHeight(){if(this.maxHeight!=null)return this.maxHeight;return isc.Page.getHeight()-this.getScrollbarSize()}
,isc.A.$8h=function isc_Menu__showOffscreen(){if(!this.isDrawn()){this.setVisibility(isc.Canvas.HIDDEN);this.draw()}
this.setVisibility(isc.Canvas.VISIBLE);this.$91y();if(this.isDirty()||this.body.isDirty())this.redraw();if(this.$rm)this.adjustOverflow();if(!this.$35y&&this.enforceMaxHeight){if(this.overflow!=isc.Canvas.VISIBLE){this.leaveScrollbarGap=false;this.setOverflow(isc.Canvas.VISIBLE);this.setHeight(this.defaultHeight);this.setWidth(this.$35z||this.defaultWidth);this.adjustOverflow()}
var _1=this.getVisibleHeight(),_2=this.getVisibleWidth(),_3=this.getMaxHeight();if(this.overflow==isc.Canvas.VISIBLE&&_1>_3){this.leaveScrollbarGap=true;this.setHeight(_3);this.$35z=this.getWidth();this.setWidth(this.getVisibleWidth()+this.getScrollbarSize())
this.setOverflow(isc.Canvas.AUTO);this.adjustOverflow()}
this.$35y=true}
this.setVisibility(isc.Canvas.HIDDEN)}
,isc.A.dataChanged=function isc_Menu_dataChanged(_1,_2,_3,_4){if(this.$35k&&this.$35m!=null){if(!this.$501(this.$35m)){if(this.$35q==null){this.setTreeNode(this.$35k.getRoot())}else{this.destroy(true);return}}}
var _5=this.invokeSuper(isc.Menu,"dataChanged",_1,_2,_3,_4);delete this.$35y;if(this.autoSetDynamicItems){this.$795()}
return _5}
,isc.A.$501=function isc_Menu__treeContains(_1){while(_1){if(this.$35k.isRoot(_1))return true;_1=this.$35k.getParent(_1)}
return false}
,isc.A.setData=function isc_Menu_setData(_1,_2,_3,_4){var _5=this.invokeSuper(isc.Menu,"setData",_1,_2,_3,_4);delete this.$35y;return _5}
,isc.A.setItems=function isc_Menu_setItems(_1,_2,_3,_4){return this.setData(_1,_2,_3,_4)}
,isc.A.getMenuItem=function isc_Menu_getMenuItem(_1){return isc.Class.getArrayItem(_1,this.data,"name")}
,isc.A.$91y=function isc_Menu__moveMenuOffscreen(){if(this.parentElement!=null)return;if(this.$35t)return;this.$35u=[this.getLeft(),this.getTop()];this.moveTo(null,-9999);this.$35t=true}
,isc.A.moveBy=function isc_Menu_moveBy(){var _1=this.Super("moveBy",arguments);if(this.$35t)this.$35t=false;return _1}
,isc.A.resizeBy=function isc_Menu_resizeBy(_1,_2,_3,_4,_5,_6){if((_1!=null&&_1!=0)||(_2!=null&&_2!=0))delete this.$35y;return this.invokeSuper(isc.Menu,"resizeBy",_1,_2,_3,_4,_5,_6)}
,isc.A.hideContextMenu=function isc_Menu_hideContextMenu(){this.hide()}
,isc.A.positionContextMenu=function isc_Menu_positionContextMenu(){this.$8h();var _1=isc.EH.getLastEvent();this.placeNear(_1.x,_1.y)}
,isc.A.getItem=function isc_Menu_getItem(_1){return isc.Class.getArrayItem(_1,this.data,"name")}
,isc.A.setItemProperties=function isc_Menu_setItemProperties(_1,_2){var _1=this.getItem(_1);if(_1!=null){isc.addProperties(_1,_2)}
if(this.isVisible())this.redraw()}
,isc.A.getItemNum=function isc_Menu_getItemNum(_1){return isc.Class.getArrayItemIndex(_1,this.data,"name")}
,isc.A.getItems=function isc_Menu_getItems(){return this.getData()}
,isc.A.addItem=function isc_Menu_addItem(_1,_2){if(_2==null)_2=this.data.getLength();this.data.addAt(_1,_2);this.markForRedraw()}
,isc.A.removeItem=function isc_Menu_removeItem(_1){this.data.remove(_1);this.markForRedraw()}
,isc.A.changeSubmenu=function isc_Menu_changeSubmenu(){var _1=this.getItem(this.body.lastOverRow);if(_1&&this.$35p==_1)return;this.hideSubmenu();if(_1!=null)this.showSubmenu(_1)}
,isc.A.hasSubmenu=function isc_Menu_hasSubmenu(_1){if(!_1)return false;if(_1.submenu)return true;if(this.fetchSubmenus==false||_1.fetchSubmenus==false)return false;if(isc.isA.Tree(this.$35k)){return(this.$35k.isFolder(_1)&&((isc.ResultTree&&isc.isA.ResultTree(this.$35k))||this.$35k.hasChildren(_1)))}
return false}
,isc.A.showSubmenu=function isc_Menu_showSubmenu(_1){var _2=this.getSubmenu(_1);if(!_2)return;this.placeSubmenu(_1,_2)}
,isc.A.getSubmenu=function isc_Menu_getSubmenu(_1){_1=this.getItem(_1);if(!this.hasSubmenu(_1))return;var _2=isc.applyMask(this,this.submenuInheritanceMask);if(!this.$51r){var _3=[];for(var i=0;i<this.fields.length;i++){_3[i]=isc.addProperties({},this.fields[i])}
_2.fields=_3}
if(this.cascadeAutoDismiss){_2.autoDismiss=this.autoDismiss}
var _5=_1.submenu;if(!_5){var _6=(this.$350||this),_7=(_6==this?0:this.$351);if(!_6.$352)_6.$352=[];this.logDebug("RootMenu:"+_6.getID()+", submenus:"+_6.$352+", Level:"+_7);_5=_6.$352[_7];if(!_5){isc.addProperties(_2,{ID:(_6.getID()+"_childrenSubMenu_"+_7),$350:_6,$351:_7+1,autoDraw:false,treeParentNode:this.$35k?_1:null,masterMenu:this});var _8=this.submenuConstructor||isc.Menu;_5=_8.create(_2);_6.$352[_7]=_5;_6.observe(_5,"destroy","observer.submenuDestroyed("+_7+");")}}else if(!isc.isA.Menu(_5)){if(!this.$352)this.$352=[];if(isc.isA.String(_5)){_5=window[_5]}else if(isc.isAn.Array(_5)){_5=this.getMenuConstructor().create({autoDraw:false,data:_5},_2);this.$352.add(_5)}else if(isc.isAn.Object(_5)){_5=this.getMenuConstructor().create(isc.addProperties({autoDraw:false},_2,_5));this.$352.add(_5)}
_1.submenu=_5}
if(this.$35k)_5.setTreeNode(_1);return _5}
,isc.A.submenuDestroyed=function isc_Menu_submenuDestroyed(_1){delete this.$352[_1]}
,isc.A.placeSubmenu=function isc_Menu_placeSubmenu(_1,_2){if(this.$35p==_1&&this.$35s==_2)return;this.$35p=_1;this.$35s=_2;_2.$8h();var _3=this.getItemNum(_1),_4=_2.getPeerRect(),_5=isc.Canvas.$t7(_4[2],_4[3],{left:this.getPageLeft()-this.submenuOffset,width:this.getVisibleWidth()+this.submenuOffset,top:this.body.getRowPageTop(_3)},this.submenuDirection==this.$oz?this.$oz:this.$o1,false)
_2.setPageRect(_5[0],_5[1]);if(this.target!=this&&_2.target!=_2){_2.target=this.target}
_2.show();_2.$35q=this;_2.$35r=_3;if(isc.Menu.$353)delete isc.Menu.$353[this.getID()]}
,isc.A.hideMenuTree=function isc_Menu_hideMenuTree(){this.hideSubmenu();this.hide()}
,isc.A.hideSubmenu=function isc_Menu_hideSubmenu(){if(this.$35s){this.$35s.hideSubmenu();this.$35s.hide();delete this.$35s;delete this.$35p}}
,isc.A.getSubmenuImage=function isc_Menu_getSubmenuImage(_1){if(!this.hasSubmenu(_1))return"&nbsp;";if(!this.$354){var _2=(this.submenuDirection==this.$oz),_3=isc.addProperties({},this.submenuImage),_4=isc.addProperties({},this.submenuDisabledImage);_3.src=isc.Img.urlForState(_3.src,null,null,(_2?this.$oz:null));_4.src=isc.Img.urlForState(_4.src,null,null,(_2?this.$oz:null));this.$354=this.imgHTML(_3);this.$355=this.imgHTML(_4)}
return(this.itemIsEnabled(_1)?this.$354:this.$355)}
,isc.A.itemIsEnabled=function isc_Menu_itemIsEnabled(_1){_1=this.getItem(_1);return(_1&&_1.enabled!=false&&_1.isSeparator!=true)}
,isc.A.$35o=function isc_Menu__makeDynamicItemsFunction(){var _1=isc.SB.create();if(this.enableIf)_1.append(this.enableIf,";");_1.append("var menu=this;\r");for(var i=0;i<this.data.length;i++){var _3=this.getItem(i);if(!_3)continue;_1.append("var item=this.data["+i+"];\r");if(_3.enableIf){if(isc.isA.String(_3.enableIf)){_1.append("changed|=this.$59g(",i,",",_3.enableIf,");\r")}else if(isc.isA.Function(_3.enableIf)){_1.append("changed|=this.$59g(",i,",this.data[",i,"].enableIf(target,menu,item));\r")}}
if(_3.checkIf){if(isc.isA.String(_3.checkIf)){_1.append("changed|=this.$59h(",i,",",_3.checkIf,");\r")}else if(isc.isA.Function(_3.checkIf)){_1.append("changed|=this.$59h(",i,",this.data[",i,"].checkIf(target,menu,item));\r")}}
if(_3.dynamicTitle){if(isc.isA.String(_3.dynamicTitle)){_1.append("changed|=this.setItemTitle(",i,",",_3.dynamicTitle,");\r")}else if(isc.isA.Function(_3.dynamicTitle)){_1.append("changed|=this.setItemTitle(",i,",this.data[",i,"].dynamicTitle(target,menu,item));\r")}}
if(_3.dynamicIcon){if(isc.isA.String(_3.dynamicIcon)){_1.append("changed|=this.setItemIcon(",i,",",_3.dynamicIcon,");\r")}else if(isc.isA.Function(_3.dynamicIcon)){_1.append("changed|=this.setItemIcon(",i,",this.data[",i,"].dynamicIcon(target,menu,item));\r")}}}
var _4=_1.toString();if(_4=="")return;_1=isc.SB.create();_1.append("var target=this.target, changed=false;\r",_4,"if(changed&&this.isDrawn()){\r","this.redraw('dynamic item change');\r",(isc.Browser.isIE?"this.body.setRowStyle(0);\r":""),"}");this.addMethods({setDynamicItems:new Function(_1.toString())});this.setDynamicItems.$796=this.data.duplicate()}
,isc.A.$795=function isc_Menu__refreshDynamicItemsFunction(){if(this.setDynamicItems==null||!this.setDynamicItems.$796.equals(this.data)){this.$35o()}}
,isc.A.refreshRow=function isc_Menu_refreshRow(){if(this.setDynamicItems)this.setDynamicItems();return this.Super("refreshRow",arguments)}
,isc.A.$59g=function isc_Menu__setItemEnabled(_1,_2){return this.setItemEnabled(_1,!!_2)}
,isc.A.$59h=function isc_Menu__setItemChecked(_1,_2){return this.setItemChecked(_1,!!_2)}
,isc.A.setItemEnabled=function isc_Menu_setItemEnabled(_1,_2){if(_2==null)_2=true;_1=this.getItem(_1);if(!_1)return;if(_1.enabled!=_2){_1.enabled=_2;this.markForRedraw("itemEnabled");return true}
return false}
,isc.A.setItemChecked=function isc_Menu_setItemChecked(_1,_2){if(_2==null)_2=true;_1=this.getItem(_1);if(!_1)return;if(_1.checked!=_2){_1.checked=_2;this.markForRedraw("itemChecked");return true}
return false}
,isc.A.setItemTitle=function isc_Menu_setItemTitle(_1,_2){_1=this.getItem(_1);if(!_1)return;if(_1.title!=_2){_1.title=_2;this.markForRedraw("item title change");return true}
return false}
,isc.A.setItemIcon=function isc_Menu_setItemIcon(_1,_2,_3){_1=this.getItem(_1);if(!_1)return;if(_1.icon!=_2){_1.icon=_2;if(_3)_1.disabledIcon=_3;this.markForRedraw("item icon change");return true}
return false}
,isc.A.getIcon=function isc_Menu_getIcon(_1){var _2=this.fixedIconWidth&&this.getRecordIndex(_1)==0,_3=_2?this.iconWidth:null;var _4;if(_1.icon){var _5=(this.itemIsEnabled(_1)||!_1.disabledIcon?_1.icon:_1.disabledIcon);_4=this.imgHTML(_5,(_1.iconWidth?_1.iconWidth:this.iconWidth),(_1.iconHeight?_1.iconHeight:this.iconHeight));if(_2&&(_1.iconWidth==null||_1.iconWidth>=_3)){_2=false}else{_3-=_1.iconWidth}}
if(_1.checked){_4=this.getCheckmarkImage(_1);if(_2){var _6=this.checkmarkImage?this.checkmarkImage.width:this.iconWidth;if(_6<_3){_3-=_6}else{_2=false}}}
if(_2){if(_4)return _4+this.imgHTML("[SKIN]/../blank.gif",_3,1);else return this.imgHTML("[SKIN]/../blank.gif",_3,1)}
return _4||"&nbsp;"}
,isc.A.getItemTitle=function isc_Menu_getItemTitle(_1){var _2;if(this.$35k){_2=this.$35k.getTitle(_1)}else{_2=_1.title||_1.name||_1.id}
_2=_2||"&nbsp;";return _2}
,isc.A.getKeyTitle=function isc_Menu_getKeyTitle(_1){if(_1.keyTitle)return _1.keyTitle;return"&nbsp;"}
,isc.A.getCheckmarkImage=function isc_Menu_getCheckmarkImage(_1){if(!this.$356){this.$356=this.imgHTML(this.checkmarkImage);this.$357=this.imgHTML(this.checkmarkDisabledImage)}
return(this.itemIsEnabled(_1)?this.$356:this.$357)}
,isc.A.setUpKeyListening=function isc_Menu_setUpKeyListening(){var _1="";var _2,_3,_4=this.data.length;for(var i=0;i<_4;i++){_2=this.getItem(i);if(!_2)continue;_3=_2.keys;if(!_3)continue;if(!isc.isAn.Array(_3))_3=[_3];for(var _6,k=0,_8=_3.length;k<_8;k++){_6=_3[k];if(_6==null)continue;isc.Page.registerKey(_6,"target.menuKey("+i+");",this);if(!this.registeredKeys)this.registeredKeys=[];this.registeredKeys.add(_6)}
if(!_2.keyTitle)this.setItemKeyTitle(_2,_3[0])}}
,isc.A.destroy=function isc_Menu_destroy(_1){if(this.registeredKeys){for(var i=0;i<this.registeredKeys.length;i++){isc.Page.unregisterKey(this.registeredKeys[i],this)}}
if(this.$352)this.$352.map("destroy");this.Super("destroy",arguments);if(this.$35k){var _3=this.$35k;if(!_1&&_3.$31k&&isc.isA.Function(_3.destroy)&&(_3.componentId==this.ID))
{_3.destroy()}else{this.$31j(_3)}
delete this.$35k}}
,isc.A.menuKey=function isc_Menu_menuKey(_1){if(this.setDynamicItems)this.setDynamicItems();return this.selectMenuItem(_1)}
,isc.A.setItemKeyTitle=function isc_Menu_setItemKeyTitle(_1,_2){var _3;if(isc.isA.String(_2))_3=_2
else if(isc.isAn.Object(_2)){if(_2.title)_3=_2.title
else _3=_2.keyName}
_1.keyTitle=_3}
);isc.B._maxIndex=isc.C+70;isc.A=isc.Menu;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.hideAllMenus=function isc_c_Menu_hideAllMenus(_1){var _2=_1=="itemClick",_3=_1=="outsideClick";var _4=true;if(isc.Menu.$kc.length>0){var _5=isc.Menu.$kc,_6=false,_7,_8=isc.EH.getFocusCanvas();isc.Menu.$kc=[];for(var i=_5.length-1;i>=0;i--){var _10=_5[i];if(!_10.isVisible()){continue}
if(_2&&_10.autoDismiss==false){isc.Menu.$kc.addAt(_10,0);_4=false;continue}
if(_3&&_10.autoDismissOnBlur==false){isc.Menu.$kc.addAt(_10,0);_4=false;continue}
if(_10.$sr(_8)){if(_7==null)_7=_10;_6=true}
_10.hide()}
if(_6&&isc.isA.Canvas(_7.body.focusOnHide)){_7.body.focusOnHide.focus()}}
isc.Menu.$353={};if(_4){if(isc.Menu.$35x){isc.EH.hideClickMask(isc.Menu.$35x);isc.Menu.$35x=null}}else{if(!isc.EH.clickMaskUp(isc.Menu.$35x)){this.$781()}}
if(isc.Menu.$358!=null){var _11=isc.Menu.$358;delete isc.Menu.$358;if(isc.EH.lastEvent.eventType==isc.EH.MOUSE_DOWN&&isc.EH.lastEvent.target==_11)
{_11.$359=true;isc.Page.setEvent(isc.EH.CLICK,_11,isc.Page.FIRE_ONCE,"$36a")}}}
,isc.A.$79j=function isc_c_Menu__getAutoDismissOnBlurMenus(){if(this.$kc==null||this.$kc.length==0)return[];var _1=[];for(var i=0;i<this.$kc.length;i++){if(this.$kc[i].autoDismissOnBlur!=false)_1.add(this.$kc[i])}
return _1}
,isc.A.$781=function isc_c_Menu__showMenuClickMask(){if(isc.Menu.$79j().length>0&&(isc.Menu.$35x==null||!isc.EH.clickMaskUp(isc.Menu.$35x)))
{isc.Menu.$35x=isc.EH.showClickMask("isc.Menu.hideAllMenus('outsideClick')",true)}}
,isc.A.menuForValueMap=function isc_c_Menu_menuForValueMap(_1,_2){var _3=[];if(isc.isA.String(_1))_1=this.getPrototype().getGlobalReference(_1);if(isc.isAn.Array(_1)){for(var i=0;i<_1.length;i++){_3[i]={value:_1[i],title:_1[i]}}}else{for(var _5 in _1){_3.add({value:_5,title:_1[_5]})}}
var _6=isc.Menu.$36b;if(_2==false||isc.Menu.$36b==null){_6=isc.Menu.newInstance({autoDraw:false,itemClick:function(_7){if(this.target.valueMapMenuSelected){this.target.valueMapMenuSelected(_7.value)}}})}
if(_2!=false&&isc.Menu.$36b==null)isc.Menu.$36b=_6;_6.setData(_3);return _6}
);isc.B._maxIndex=isc.C+4;isc.Menu.registerStringMethods({itemClick:"item"})
isc.A=isc.ListGrid.getPrototype();isc.A.showHeaderContextMenu=true;isc.A=isc.ListGrid.getPrototype();isc.A.showHeaderMenuButton=false;isc.$75j={title:"Show Menu",height:22,showMenuButtonImage:true,menuButtonImage:"[SKIN]menu_button.gif",menuButtonImageUp:"[SKIN]menu_button_up.gif",hiliteAccessKey:true,iconWidth:7,iconHeight:7,iconOrientation:"right",iconAlign:"right",align:"left",showMenuBelow:true,alignMenuLeft:true,menu:null,initWidget:function(_1,_2,_3,_4){if(this.showMenuButtonImage)this.$36c();return this.invokeSuper(isc.MenuButton,"initWidget",_1,_2,_3,_4)},autoDestroyMenu:true,destroy:function(_1,_2,_3,_4,_5){if(this.menu!=null&&this.autoDestroyMenu&&this.menu.destroy!=null&&!this.menu.destroyed&&!this.menu.destroying)
{this.menu.destroy();this.menu=null}
return this.invokeSuper(isc.MenuButton,"destroy",_1,_2,_3,_4,_5)},setShowMenuButtonImage:function(_1){if(_1==this.showMenuButtonImage)return;this.showMenuButtonImage=_1;if(_1)this.$36c();else this.icon=null;if(this.isDrawn())this.markForRedraw()},setShowMenuBelow:function(_1){if(_1!=this.showMenuBelow){this.showMenuBelow=_1;if(this.showMenuButtonImage){this.$36c();if(this.isDrawn())this.markForRedraw()}}},$36c:function(){var _1=this.showMenuBelow?this.menuButtonImage:this.menuButtonImageUp;if(isc.isAn.Object(_1)){if(_1.width)this.iconWidth=_1.width;if(_1.height)this.iconHeight=_1.height;this.icon=_1.src}else{this.icon=_1}},handleClick:function(){if(this.Super("handleClick",arguments)==false)return;if(this.$359)return;this.showMenu()},$36a:function(){if(this.$359){var _1=this;isc.Page.setEvent(isc.EH.IDLE,function(){_1.$359=null},isc.Page.FIRE_ONCE)}},keyPress:function(){if(isc.EventHandler.lastEvent.keyName=="Arrow_Down"){this.showMenu();return false}
return this.Super("keyPress",arguments)},$ur:function(){this.Super("$ur",arguments);this.markForRedraw()},$86e:function(_1,_2){if(!_1||!_2)return;_1.rootMenuButton=_2;_1.autoDismissOnBlur=false;_1.mouseOver=function(){var _3=this.rootMenuButton;if(!_3.showMenuOnRollOver)return this.Super("mouseOver",arguments);_3.checkRollOverMouseOver(_3,this)};_1.mouseOut=function(){var _3=this.rootMenuButton;if(!_3.showMenuOnRollOver)return this.Super("mouseOut",arguments);_3.checkRollOverMouseOut(_3,this)}},checkRollOverMouseOver:function(_1,_2){if(_1.$86f!=null){isc.Timer.clear(_1.$86f)}
if(_1.menu!=null&&!(_1.menu.isVisible()&&_1.menu.isDrawn())){isc.Menu.hideAllMenus();_1.showMenu()}},checkRollOverMouseOut:function(_1,_2){if(_1.$86f!=null){_1.$86f=null}
if(_1.menu!=null&&_1.menu.isVisible()&&_1.menu.isDrawn()){_1.$86f=isc.Timer.setTimeout(function(){if(_1.menu!=null&&_1.menu.isVisible()&&_1.menu.isDrawn()){isc.Menu.hideAllMenus();_1.$86f=null}},_1.rollOverMenuHideDelay)}},showMenu:function(){if(isc.isA.String(this.menu))this.menu=window[this.menu];if(!isc.isA.Menu(this.menu))this.$36d(this.menu);if(!isc.isA.Menu(this.menu))return;isc.Menu.$358=this;var _1=this.menu;if(this.showMenuOnRollOver){var _2=this;this.$86e(_1,this);_1.getSubmenu=function(_6){var _3=this.Super("getSubmenu",arguments);if(_3)_2.$86e(_3,_2);return _3}}
_1.$8h();var _4=this.getPageLeft();if(!this.alignMenuLeft){_4=_4-(_1.getVisibleWidth()-this.getVisibleWidth())}
var _5=this.showMenuBelow?this.getPageTop()+this.getVisibleHeight()+1:this.getPageTop()-_1.getVisibleHeight()+2;_1.placeNear(_4,_5);_1.show(this.menuAnimationEffect)},$36d:function(_1){if(!_1)return;_1.autoDraw=false;if(this.showMenuOnRollOver){var _2=this;_1.getSubmenu=function(_5){var _3=this.Super("getSubmenu",arguments);_2.$86e(_3,_2);return _3}}
var _4=this.menuConstructor||isc.Menu;this.menu=_4.create(_1)},rollOverMenuHideDelay:250,mouseMove:function(){if(this.showMenuOnRollOver)this.mouseOver()},mouseOver:function(){if(!this.showMenuOnRollOver)return this.Super("mouseOver",arguments);this.checkRollOverMouseOver(this,this)},mouseOut:function(){if(!this.showMenuOnRollOver)return this.Super("mouseOut",arguments);this.checkRollOverMouseOut(this,this)}};isc.ClassFactory.defineClass("MenuButton","Button");isc.MenuButton.addProperties(isc.$75j)
isc.A=isc.MenuButton.getPrototype();isc.A.skinImgDir="images/Menu/";isc.A.baseStyle="menuButton";isc.ClassFactory.defineClass("IMenuButton","StretchImgButton");isc.IMenuButton.addProperties(isc.$75j)
isc.A=isc.IMenuButton.getPrototype();isc.A.labelSkinImgDir="images/Menu/";isc.defineClass("ToolStripMenuButton","IMenuButton");isc.A=isc.ToolStripMenuButton.getPrototype();isc.A.showTitle=true;isc.A.showRollOver=true;isc.A.showDown=true;isc.A.labelVPad=0;isc.A.labelHPad=7;isc.A.autoFit=true;isc.A.src="[SKIN]/ToolStrip/button/button.png";isc.A.capSize=3;isc.A.height=22;isc.ClassFactory.defineClass("SelectionTreeMenu","Menu")
isc.A=isc.SelectionTreeMenu.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.itemClick=function isc_SelectionTreeMenu_itemClick(_1){this.inheritedProperties.button.$21j(_1)}
,isc.A.getBaseStyle=function isc_SelectionTreeMenu_getBaseStyle(_1,_2,_3){var _4=this.inheritedProperties.button;if(_4.$36e(_1))return _4.selectedBaseStyle;return this.Super("getBaseStyle",arguments)}
,isc.A.show=function isc_SelectionTreeMenu_show(){if(this.body){for(var i=0;i<this.getTotalRows();i++){this.body.setRowStyle(i)}}
return this.Super("show",arguments)}
,isc.A.getItemTitle=function isc_SelectionTreeMenu_getItemTitle(_1,_2,_3,_4,_5){var _6=this.inheritedProperties.button;if(_6.displayField)return _1[_6.displayField];return this.invokeSuper(isc.SelectionTreeMenu,"getItemTitle",_1,_2,_3,_4,_5)}
);isc.B._maxIndex=isc.C+4;isc.ClassFactory.defineClass("TreeMenuButton","MenuButton");isc.ClassFactory.defineClass("ITreeMenuButton","IMenuButton");isc.$75k={title:null,unselectedTitle:"Choose a value",showPath:false,pathSeparatorString:"&nbsp;&gt;&nbsp;",selectedBaseStyle:"treeMenuSelected",overflow:isc.Canvas.VISIBLE,menuConstructor:isc.SelectionTreeMenu,getTitle:function(){if(this.title)return this.title;var _1=this.getSelectedItem();if(_1){if(!this.showPath){if(!isc.isA.Menu(this.menu))this.$36d(this.menu);return this.menu.getItemTitle(_1)}else{var _2=this.getTree();var _3=_2.getParents(_1),_4=[];for(var i=_3.length-1;i>=0;i--){if(!_2.showRoot&&i==_3.length-1)continue;_4.add(this.menu.getItemTitle(_3[i]))}
_4.add(this.menu.getItemTitle(_1));return _4.join(this.pathSeparatorString)}}else{return this.unselectedTitle}},$36d:function(_1){_1=isc.addProperties(this.menuDefaults||{},_1,{inheritedProperties:{button:this},canSelectParentItems:this.canSelectParentItems,submenuConstructor:isc.SelectionTreeMenu,dataSource:this.dataSource,criteria:this.criteria,data:this.data});if(this.emptyMenuMessage)_1.emptyMessage=this.emptyMenuMessage;if(this.loadDataOnDemand!=null)_1.loadDataOnDemand=this.loadDataOnDemand;var _2=this.Super("$36d",[_1]);this.observe(this.menu,"treeDataLoaded","observer.$21l()");return _2},$21l:function(){if(this.treeDataLoaded)this.treeDataLoaded()},getTree:function(){if(!isc.isA.Menu(this.menu))this.$36d(this.menu);return this.menu.$35k},$21j:function(_1){if(this.itemSelected&&this.itemSelected(_1,this.$36f)==false)
return;this.setSelectedItem(_1)},setSelectedItem:function(_1){this.$36f=_1;this.setTitle()},getSelectedItem:function(){return this.$36f},$36e:function(_1){var _2=this.getSelectedItem(),_3=this.getTree();while(_2){if(_1==_2)return true;_2=_3.getParent(_2)}
return false}};isc.TreeMenuButton.addProperties(isc.$75k)
isc.ITreeMenuButton.addProperties(isc.$75k)
isc.TreeMenuButton.registerStringMethods({itemSelected:"item, oldItem"});isc.ITreeMenuButton.registerStringMethods({itemSelected:"item, oldItem"});isc.ClassFactory.defineClass("TileLayout","Canvas");isc.A=isc.TileLayout.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.layoutPolicy="fit";isc.A.tileSize=50;isc.A.tileMargin=10;isc.A.layoutMargin=5;isc.A.paddingAsLayoutMargin=true;isc.A.animateTileChange=true;isc.A.orientation="horizontal";isc.A.overflow="auto";isc.A.canFocus=true;isc.A.expandMargins=true;isc.A.autoWrapLines=true;isc.A.dragLineDefaults={overflow:"hidden",styleName:"layoutDropLine"};isc.A.$66i=true;isc.A.relayoutProperties=["tilesPerLine","orientation","tileWidth","tileHeight","expandMargins"];isc.A.canDrop=true;isc.A.$66e=2;isc.B.push(isc.A.initWidget=function isc_TileLayout_initWidget(){this.$73g();this.invokeSuper(isc.TileLayout,"initWidget");if(!this.tiles)this.tiles=[];this.data=this.tiles}
,isc.A.draw=function isc_TileLayout_draw(_1,_2,_3,_4){this.invokeSuper(isc.TileLayout,"draw",_1,_2,_3,_4);this.$73k();this.logDebug('calling layoutTiles from draw',"TileLayout");this.layoutTiles()}
,isc.A.resized=function isc_TileLayout_resized(){this.Super("resized",arguments);this.logDebug('calling layoutTiles from resized',"TileLayout");this.layoutTiles()}
,isc.A.childResized=function isc_TileLayout_childResized(_1,_2,_3,_4){this.invokeSuper(isc.TileLayout,"childResized",_1,_2,_3,_4);this.logDebug('calling layoutTiles from childResized',"TileLayout");isc.Timer.setTimeout(this.ID+".layoutTiles()",100)}
,isc.A.$73g=function isc_TileLayout__enforceLegalLayoutPolicy(){if(this.layoutPolicy!="fit"&&this.layoutPolicy!="flow"){this.layoutPolicy="fit"}}
,isc.A.willScroll=function isc_TileLayout_willScroll(){var _1=this.orientation=="horizontal";var _2=this.$td;var _3=this.$te;var _4=this.$tb;var _5=this.$tc;var _6,_7;if(this.$66i){_7=this.getUserVisibleTiles();_6=_7.getLength()}else{_6=this.getLength()}
if(this.layoutPolicy=="flow"){if(this.overflow!="auto"&&this.overflow!="scroll")return false;var _8=_1?this.getInnerWidth()-_5:this.getInnerHeight()-_3;var _9=_1?this.getInnerHeight()-_3:this.getInnerWidth()-_4;var _10=_1?_4:_2;var _11=_1?_2:_4;var _12=0;var _13=0;var _14=_1?this.getTileHMargin():this.getTileVMargin();var _15=_1?this.getTileVMargin():this.getTileHMargin();for(var i=0;i<_6;i++){var _17=_7?_7[i]:this.getTile(i);if(!this.autoWrapLines&&_17.startLine)_10=0;var _18=_1?_17.getVisibleWidth():_17.getVisibleHeight();if((this.autoWrapLines&&_10+_18>_8)||(!this.autoWrapLines&&_17.endLine)){_10=_1?_4:_2;;_11+=_12+_15;_12=0;_13++}
var _19=_1?_17.getVisibleHeight():_17.getVisibleWidth();if(_11+_19>_9)return true;if(_12<_19){_12=_19}
_10+=_18+_14}
return false}else{var _9=_1?this.getVisibleHeight()-(_2+_3):this.getVisibleWidth()-(_5+_4);var _19=_1?this.getTileHeight():this.getTileWidth();var _15=_1?this.getTileVMargin():this.getTileHMargin();var _20=this.getTilesPerLine();var _21=Math.floor(_9/(_19+_15));var _22=_20*_21;return(_6>_22)}}
,isc.A.layoutTiles=function isc_TileLayout_layoutTiles(_1){if(!this.isDrawn()||this.getLength()==0)return;this.$585=[];this.$608=0;this.$619=[this.getScrollLeft(),this.getScrollTop(),this.getInnerWidth(),this.getInnerHeight()];var _2,_3,_4,_5;if(this.paddingAsLayoutMargin){var _6=this.$tq();_4=_6.left;_5=_6.right;_2=_6.top;_3=_6.bottom}
var _7=this.$td=this.$du(this.layoutMargin,_2,0);var _8=this.$te=this.$du(this.layoutMargin,_3,0);var _9=this.$tb=this.$du(this.layoutMargin,_4,0);var _10=this.$tc=this.$du(this.layoutMargin,_5,0);var _11,_12;if(this.$66i){_12=this.getUserVisibleTiles();_11=_12.getLength()}else{_11=this.getLength()}
if(this.layoutPolicy=="flow"){this.logDebug("starting flow layout","TileLayout");var _13=this.orientation=="horizontal";var _14=_13?this.getInnerWidth()-_10:this.getInnerHeight()-_8;if(this.willScroll())_14-=this.getScrollbarSize();var _15=_13?_9:_7;var _16=_13?_7:_9;var _17=0;var _18=0;var _19=_13?this.getTileHMargin():this.getTileVMargin();var _20=_13?this.getTileVMargin():this.getTileHMargin();for(var i=0;i<_11;i++){var _22=_12?_12[i]:this.getTile(i);if(!this.autoWrapLines&&_22.startLine)_15=0;var _23=_13?_22.getVisibleWidth():_22.getVisibleHeight();if((this.autoWrapLines&&_15+_23>_14)||(!this.autoWrapLines&&_22.endLine)){_15=_13?_9:_7;;_16+=_17+(i==0?0:_20);_17=0;_18++}
var _24=_13?_16:_15;var _25=_13?_15:_16;var _26=_12?_12[i]:i;this.processTile(_26,_24,_25);var _27=_13?_22.getVisibleHeight():_22.getVisibleWidth();if(_17<_27){_17=_27}
_15+=_23+_19}}else{this.logDebug("starting fit layout:"+this.$35v,"TileLayout");var _28=this.getTilesPerLine();var _29=this.getTileHeight();var _30=this.getTileWidth();var _13=this.orientation=="horizontal";var _31,_32,_33;var _34,_35,_36,_37,_38,_39;if(this.shouldUseIncrRendering()){this.logDebug("fit layout, using incremental rendering","TileLayout");var _40=this.getVisibleTiles();this.$57r=_40;_35=_40[0];_34=_11;var _41=this.getVisibleLines();_38=_41[0];_39=_41[0]+_41[1];if(!this.hasAllVisibleTiles(_40,true))return}else{this.logDebug("fit layout, rendering all tiles","TileLayout");_34=_11;_35=0;_38=0;_39=Math.ceil(_34/ _28);_31=this.getTileHMargin();_32=this.getTileVMargin()}
var _42=Math.ceil(_11/ _28);var _32=this.getTileVMargin();var _31=this.getTileHMargin();var _27=_13?_29:_30;var _43=_13?_32:_31;var _44=((_27+_43)*_42)-_43+(_13?_7+_8:_9+_10);_37=this.$58t();_13?_37.setHeight(_44):_37.setWidth(_44);_37.sendToBack();_33=this.getExtraMarginPixels(_28,_29,_30,_31,_32);for(var i=_38;i<_39;i++){var _45=_33;for(var j=0;j<_28;j++){var _47=_13?i:j;var _48=_13?j:i;var _24=(_47*_29)+(_47*_32)+_7;if(!_13&&j+1<=_33)_24+=j+1;var _25=(_48*_30)+(_48*_31)+_9;if(_13&&j+1<=_33)_25+=j+1;var _26=_12?_12[_35]:_35
var _22=this.processTile(_26,_24,_25,this.getTileHeight(),this.getTileWidth());_35++;if(_35>=_34)break}
if(_35>=_34)break}}}
,isc.A.hasAllVisibleTiles=function isc_TileLayout_hasAllVisibleTiles(){return true}
,isc.A.getUserVisibleTiles=function isc_TileLayout_getUserVisibleTiles(){var _1=[];for(var i=0;i<this.getLength();i++){var _3=this.getTile(i);if(!_3.$66j)_1.add(_3)}
return _1}
,isc.A.processTile=function isc_TileLayout_processTile(_1,_2,_3,_4,_5){var _6;if(this.$35v){if(isc.isA.Canvas(_1))_6=_1;else _6=this.getRecordTile(_1);if(!_6)return;if(this.$584!=null&&this.$584.findIndex("ID",_6.ID)==-1)return;if(!_6)return;if(_4)_6.setHeight(_4);if(_5)_6.setWidth(_5);if(_6.getTop()!=_2||_6.getLeft()!=_3){_6.$589=_2;_6.$588=_3;this.$586.add(_6)}
var _7=[_3,_2,_6.getVisibleWidth(),_6.getVisibleHeight()];if(isc.Canvas.rectsIntersect(this.$619,_7)){this.$585.add(_6)}}else{if(isc.isA.Canvas(_1))_6=_1;else _6=this.getTile(_1);if(!_6)return;if(_6.isDirty())_6.redraw();if(_4)_6.setHeight(_4);if(_5)_6.setWidth(_5);_6.setTop(_2);_6.setLeft(_3);this.addChild(_6);_6.$66k=true;_6.show();_6.$66k=null;var _7=[_6.getLeft(),_6.getTop(),_6.getVisibleWidth(),_6.getVisibleHeight()];if(isc.Canvas.rectsIntersect(this.$619,_7)){this.$585.add(_6)}
this.$608+=1;return _6}}
,isc.A.$58t=function isc_TileLayout__getSpacerCanvas(){if(!this.$58u){this.$58u=isc.Canvas.create({autoDraw:false});this.addChild(this.$58u)}
return this.$58u}
,isc.A.getDrawnStartIndex=function isc_TileLayout_getDrawnStartIndex(){if(this.$57r)return this.$57r[0];else return null}
,isc.A.getDrawnEndIndex=function isc_TileLayout_getDrawnEndIndex(){if(this.$57r)return this.$57r[1];else return null}
,isc.A.shouldUseIncrRendering=function isc_TileLayout_shouldUseIncrRendering(){if(this.$35v){return true}else if(!this.showAllRecords&&this.layoutPolicy=="fit"&&(this.overflow=="auto"||this.overflow=="hidden")){return true}else{return false}}
,isc.A.getLength=function isc_TileLayout_getLength(){if(!this.tiles)return 0;else return this.tiles.getLength()}
,isc.A.getTilesPerLine=function isc_TileLayout_getTilesPerLine(){if(this.tilesPerLine)return this.tilesPerLine;else{var _1=this.orientation=="horizontal"?this.getTileWidth():this.getTileHeight();var _2=this.orientation=="horizontal"?(this.tileHMargin||this.tileMargin):(this.tileVMargin||this.tileMargin);var _3=this.orientation=="horizontal"?this.getInnerWidth():this.getInnerHeight();var _4=Math.floor(_3/ _1);var _5=this.orientation=="horizontal"?this.$tb+this.$tc:this.$td+this.$te;var _6=_2*(_4-1)+(_4*_1)+_5;if(_6>_3){var _7=Math.ceil((_6-_3)/_1);_4-=_7}
if(_4<1)_4=1;return _4}}
,isc.A.$73k=function isc_TileLayout__setTileSize(){var _1=this.orientation=="horizontal";if(this.layoutPolicy!="fit"||this.expandMargins||!this.tilesPerLine||(_1&&this.tileWidth)||(!_1&&this.tileHeight))return;var _2=_1?(this.tileHMargin||this.tileMargin):(this.tileVMargin||this.tileMargin);var _3=_1?this.getInnerWidth():this.getInnerHeight();var _4=this.layoutMargin*2;var _5=_3-_4;if(!this.tilesPerLine){this.tileSize=50}else{var _6=Math.floor(_5/ this.tilesPerLine);_6-=_2;isc.logWarn('setTileSize:'+[_6,_3,this.layoutMargin]);if(this.orientation=="horizontal")this.tileWidth=_6;else this.tileHeight=_6}}
,isc.A.getVisibleLines=function isc_TileLayout_getVisibleLines(){var _1=(this.orientation=="horizontal");var _2=_1?this.getScrollTop():this.getScrollLeft();var _3=_1?this.getTileHeight()+this.getTileVMargin():this.getTileWidth()+this.getTileHMargin();var _4=_1?this.getInnerHeight():this.getInnerWidth();var _5=Math.floor(_2/ _3);if(_5>0)_5--;var _6=Math.ceil(_4/ _3)+2;return[_5,_6]}
,isc.A.getVisibleTiles=function isc_TileLayout_getVisibleTiles(){var _1=this.getVisibleLines();var _2=this.getTilesPerLine();var _3=_1[0]*_2;var _4=(_1[0]+_1[1])*_2;return[_3,_4]}
,isc.A.scrolled=function isc_TileLayout_scrolled(){if(this.shouldUseIncrRendering()){if(this.$57s)isc.Timer.clear(this.$57s);this.$57s=isc.Timer.setTimeout(this.ID+".layoutAfterScroll()")}}
,isc.A.layoutAfterScroll=function isc_TileLayout_layoutAfterScroll(){this.logDebug('layoutAfterScroll',"TileLayout");if(this.shouldLayoutTiles()){this.logDebug('calling layoutTiles from layoutAfterScroll',"TileLayout");this.layoutTiles()}}
,isc.A.shouldLayoutTiles=function isc_TileLayout_shouldLayoutTiles(){var _1=this.getVisibleTiles();if(_1[0]==this.getDrawnStartIndex()&&_1[1]==this.getDrawnEndIndex()){return false}else{return true}}
,isc.A.getTileWidth=function isc_TileLayout_getTileWidth(){if(this.tileWidth){if(isc.isA.String(this.tileWidth)){this.tileWidth=parseInt(this.tileWidth);if(!isc.isA.Number(this.tileWidth))this.tileWidth=this.tileSize}
return this.tileWidth}
else return this.tileSize}
,isc.A.getTileHeight=function isc_TileLayout_getTileHeight(){if(this.tileHeight){if(isc.isA.String(this.tileHeight)){this.tileHeight=parseInt(this.tileHeight);if(!isc.isA.Number(this.tileHeight))this.tileHeight=this.tileSize}
return this.tileHeight}
else return this.tileSize}
,isc.A.getInnerBreadth=function isc_TileLayout_getInnerBreadth(){var _1=this.orientation=="horizontal"?this.getInnerWidth():this.getInnerHeight();return _1}
,isc.A.getTileHMargin=function isc_TileLayout_getTileHMargin(){var _1;if(this.tileHMargin){_1=this.tileHMargin}else{_1=this.tileMargin}
if(this.layoutPolicy=="fit"&&this.expandMargins&&this.orientation=="horizontal"){var _2=this.getTilesPerLine();var _3=this.$tb+this.$tc;var _4=_2-1;if(_4==0)_4=1;var _5=Math.floor((this.getInnerBreadth()-(_2*this.getTileWidth())-_3)/_4);if(_5<_1)return _1;else return _5}else{return _1}}
,isc.A.getTileVMargin=function isc_TileLayout_getTileVMargin(){var _1;if(this.tileHMargin){_1=this.tileVMargin}else{_1=this.tileMargin}
if(this.layoutPolicy=="fit"&&this.expandMargins&&this.orientation=="vertical"){var _2=this.getTilesPerLine();var _3=this.$td+this.$te;var _4=_2-1;if(_4==0)_4=1;var _5=Math.floor((this.getInnerBreadth()-(_2*this.getTileHeight())-_3)/_4);if(_5<_1)return _1;else return _5}else{return _1}}
,isc.A.getExtraMarginPixels=function isc_TileLayout_getExtraMarginPixels(_1,_2,_3,_4,_5){if(this.expandMargins&&this.orientation=="horizontal"){var _6=(_1*_3)+((_1-1)*_4)+(this.$tb+this.$tc);return this.getInnerBreadth()-_6}else if(this.expandMargins&&this.orientation=="vertical"){var _6=(_1*_2)+((_1-1)*_5)+(this.$td+this.$te);return this.getInnerBreadth()-_6}else{return 0}}
,isc.A.getTile=function isc_TileLayout_getTile(_1){return isc.Class.getArrayItem(_1,this.tiles)}
,isc.A.addTile=function isc_TileLayout_addTile(_1,_2){if(!this.tiles)return;if(!_2)_2=this.tiles.getLength();this.tiles.addAt(_1,_2);this.reLayout()}
,isc.A.removeTile=function isc_TileLayout_removeTile(_1){if(!this.tiles)return;if(!isc.isA.Canvas(_1)){var _2=this.tiles.findIndex("ID",_1);if(_2==-1)_1=this.tiles.get(_1);else _1=this.tiles.get(_2)}
this.removeChild(_1)}
,isc.A.removeChild=function isc_TileLayout_removeChild(_1,_2){var _3=this.tiles.findIndex("ID",_1.ID);if(_3>-1)this.tiles.removeAt(_3);this.invokeSuper(isc.TileLayout,"removeChild",_1,_2);this.reLayout()}
,isc.A.getRecordTile=function isc_TileLayout_getRecordTile(_1){if(_1==null)return null;return this.tiles.get(_1)}
,isc.A.childVisibilityChanged=function isc_TileLayout_childVisibilityChanged(_1,_2){if(!this.$35v&&!_1.$66l){if(_2=="hidden")_1.$66j=true;else _1.$66j=null;this.reLayout()}}
,isc.A.propertyChanged=function isc_TileLayout_propertyChanged(_1,_2){this.invokeSuper(isc.TileLayout,"propertyChanged",_1,_2);if(isc.endsWith(_1,"Margin")||this.relayoutProperties.contains(_1))
{this.layoutTiles()}}
,isc.A.showDragLineForRecord=function isc_TileLayout_showDragLineForRecord(){if(isc.isAn.Array(this.data)||(isc.isA.ResultSet(this.data))){var x=this.getOffsetX(),y=this.getOffsetY(),_3=this.getPageLeft(),_4=this.getPageTop();if(this.data.getLength()==0){return}
var _5=this.findIndexForCoord(x,y);if(this.$66f==null)this.$66f=_5;var _6,_7,_8=this.getRecordTile(_5);if(_8!=null){if((this.orientation=="horizontal"&&x>_8.getLeft()+(_8.getVisibleWidth()/2))||(this.orientation=="vertical"&&y>_8.getTop()+(_8.getVisibleHeight()/2))){_5++;_8=this.getRecordTile(_5)}}
if(_5==null||_8==null){_5=this.data.getLength();_8=this.getRecordTile(_5-1);if(this.orientation=="horizontal"){_6=_8.getLeft()+_8.getVisibleWidth()-this.$66e;_7=_8.getTop()}else{_6=_8.getLeft();_7=_8.getTop()+_8.getVisibleHeight()-this.$66e}}else{_6=_8.getLeft();_7=_8.getTop()}
this.$610=_5;var _9,_10;if(this.orientation=="horizontal"){_10=_8.getVisibleHeight();_9=this.$66e}
else{_9=_8.getVisibleWidth();_10=this.$66e}
this.showDragLine(_3+(_6-this.getScrollLeft()),_4+(_7-this.getScrollTop()),_9,_10)}}
,isc.A.showDragLine=function isc_TileLayout_showDragLine(_1,_2,_3,_4){this.makeDragLine();var _5=this.getVisibleHeight();var _6=Math.round((_5-this.getInnerHeight())/2);var _7=this.getPageTop();var _8=_7+_6;var _9=_7+_5-_6;if(_2<_8){_4=_4-(_8-_2);_2=_8}else if(_2+_4>_9){if(_2>=_9){_4=0}else{_4=_4-((_2+_4)-_9)}}
this._dragLine.moveTo(_1,_2);this._dragLine.resizeTo(_3,_4);this._dragLine.show()}
,isc.A.dropOut=function isc_TileLayout_dropOut(){this.hideDragLine()}
,isc.A.dropMove=function isc_TileLayout_dropMove(){this.showDragLineForRecord()}
,isc.A.findIndexForCoord=function isc_TileLayout_findIndexForCoord(_1,_2){var _3=this.getDrawnStartIndex(),_4=this.getDrawnEndIndex();if(_3==null||_4==null){_3=0;_4=this.data.getLength()}
var _5=this.ns.EH;var _6=_5.dragTarget,_7;if(_6)_7=_6.ID;for(var i=_3;i<_4;i++){var _9=this.getRecordTile(i);if(!_9)continue;if(_9.ID==_7)continue;if(_9.getLeft()+_9.getVisibleWidth()>_1&&_9.getTop()+_9.getVisibleHeight()>_2)return i}
return null}
,isc.A.drop=function isc_TileLayout_drop(){var _1=this.$610||0;var _2=this.ns.EH.dragTarget;var _3=this.$66f;this.$66f=null;if(!isc.isAn.Array(_2))_2=[_2];var _4=_2[0].parentElement;var _5=this.data.get(_1);this.transferRecords(_2,_5,_1,_4);this.reLayout()}
,isc.A.reLayout=function isc_TileLayout_reLayout(){if(this.destroying)return;if(this.animateTileChange){isc.Timer.setTimeout(this.ID+".$583()",200)}else{this.logDebug('calling layoutTiles from reLayout',"TileLayout");this.layoutTiles()}}
,isc.A.$583=function isc_TileLayout__animateChange(){this.logDebug("starting $583()","TileLayout");this.$35v=true;this.$0j=[];var _1=this.$584=this.$585;this.$586=[];this.layoutTiles();if(_1!=null){for(var i=0;i<_1.length;i++){if(!this.$585.contains(_1[i])){_1[i].hide()}}}
var _3=this.$586.length;if(_3==0){this.$587();return}
for(var i=0;i<_3;i++){var _4=this.$586[i];_4.show();var _5;if(i==_3-1){_5=_4.animateMove(_4.$588,_4.$589,this.getID()+".$587()")}else{_5=_4.animateMove(_4.$588,_4.$589)}
this.$0j.add({ID:_5,tile:_4})}}
,isc.A.$587=function isc_TileLayout__finishAnimating(){this.$35v=false;this.$584=null;delete this.$584;this.$586=null;delete this.$586;this.logDebug('calling layoutTiles from $587',"TileLayout");this.layoutTiles()}
,isc.A.setTileSize=function isc_TileLayout_setTileSize(_1){this.tileSize=_1;this.layoutTiles()}
,isc.A.setTileWidth=function isc_TileLayout_setTileWidth(_1){this.tileWidth=_1;this.layoutTiles()}
,isc.A.setTileHeight=function isc_TileLayout_setTileHeight(_1){this.tileHeight=_1;this.layoutTiles()}
,isc.A.setTileMargin=function isc_TileLayout_setTileMargin(_1){this.tileMargin=_1;this.layoutTiles()}
,isc.A.setTileHMargin=function isc_TileLayout_setTileHMargin(_1){this.tileHMargin=_1;this.layoutTiles()}
,isc.A.setTileVMargin=function isc_TileLayout_setTileVMargin(_1){this.tileVMargin=_1;this.layoutTiles()}
);isc.B._maxIndex=isc.C+50;isc.ClassFactory.defineClass("FlowLayout","TileLayout");isc.A=isc.FlowLayout.getPrototype();isc.A.layoutPolicy="flow";isc.ClassFactory.defineClass("TileGrid","TileLayout","DataBoundComponent");isc.A=isc.TileGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.tileValueStyle="tileValue";isc.A.valuesShowRollOver=false;isc.A.valuesShowSelected=true;isc.A.valuesShowDown=false;isc.A.tileValueAlign="center";isc.A.showLabels=false;isc.A.tileLabelStyle="tileLabel";isc.A.wrapValues=false;isc.A.canSortFields=true;isc.A.autoFetchTextMatchStyle="substring";isc.A.selectionType=isc.Selection.MULTIPLE;isc.A.tileConstructor="SimpleTile";isc.A.detailViewerConstructor="DetailViewer";isc.A.recycleTiles=true;isc.A.animateTileChange=true;isc.A.styleName="tileGrid";isc.A.$66i=false;isc.A.$726=[];isc.A.$727=10;isc.A.$20s="Arrow_Up";isc.A.$20t="Arrow_Down";isc.A.$60n="Arrow_Left";isc.A.$60o="Arrow_Right";isc.A.dragAppearance=isc.EH.TRACKER;isc.A.dragTrackerMode="title";isc.A.tileDragAppearance=isc.EH.TRACKER;isc.B.push(isc.A.initWidget=function isc_TileGrid_initWidget(){this.$73g();if(isc.FormulaBuilder==null)this.canAddFormulaFields=false;if(isc.SummaryBuilder==null)this.canAddSummaryFields=false;if(this.layoutPolicy=="flow"){isc.logWarn("TileGrid does not support layoutPolicy 'flow'; there may by unexpected behavior."+"Use a TileLayout instead for flow layout.")}
this.$31i();this.invokeSuper(isc.TileLayout,"initWidget");if(!this.tiles)this.tiles=[];if(this.showAllRecords)this.recycleTiles=false;if(this.getDataSource()){this.$601={};if(this.getDataSource().getPrimaryKeyFieldNames().length==0){this.animateTileChange=false}}
this.detailViewer=this.createAutoChild("detailViewer",{tileGrid:this,showLabel:this.showLabels,showBorder:false,cellStyle:this.tileValueStyle,labelStyle:this.tileLabelStyle,blockStyle:"normal",wrapValues:this.wrapValues,cellPadding:0,valueAlign:this.tileValueAlign,useInnerWidth:false,clipValues:true,width:10,height:10,data:[],dataSource:this.getDataSource(),getCellStyle:function(_2,_3,_4,_5){var _1=(_3.cellStyle||this.cellStyle);if(this.tileGrid.valuesShowRollOver&&this.currentTile.state==isc.StatefulCanvas.STATE_OVER){_1+=this.currentTile.getStateSuffix()}else if(this.tileGrid.valuesShowDown&&this.currentTile.state==isc.StatefulCanvas.STATE_DOWN){_1+=this.currentTile.getStateSuffix()}else if(this.tileGrid.valuesShowSelected&&this.currentTile.isSelected()){_1+=this.currentTile.getStateSuffix()}
return _1}});if(this.fieldState!=null)this.setFieldState(this.fieldState);else this.setFields(this.fields,true);this.membersMargin=this.tileMargin;this.setData(this.data)}
,isc.A.setDataSource=function isc_TileGrid_setDataSource(_1,_2){this.Super("setDataSource",arguments);if(this.getDataSource()){this.$601={};if(this.getDataSource().getPrimaryKeyFieldNames().length==0){this.animateTileChange=false}}}
,isc.A.shouldUseField=function isc_TileGrid_shouldUseField(_1,_2){if(this.Super("shouldUseField",arguments))return true;if(_2){var _3=isc.DS.get(_2).getIconField();if(_1==_3||_1.name==_3||(_3&&_1.name==_3.name))
{return true}}
return false}
,isc.A.setFields=function isc_TileGrid_setFields(_1,_2){if(!_1&&this.getDataSource()){var _3=this.getDataSource().getIconField();if(_3){_1=[];_1.add({name:_3,type:_3.type});_1.add({name:this.getDataSource().getTitleField()})}}
if(this.completeFields==null)this.fields=[];this.completeFields=this.bindToDataSource(_1);if(this.completeFields==null)this.completeFields=[];if(!this.completeFields)return;this.deriveVisibleFields();this.detailViewer.fields=this.completeFields.duplicate();if(!_2){this.logDebug('calling layoutTiles from setFields',"TileGrid");this.layoutTiles()}}
,isc.A.deriveVisibleFields=function isc_TileGrid_deriveVisibleFields(){this.fields.setArray(this.getVisibleFields(this.completeFields))}
,isc.A.getVisibleFields=function isc_TileGrid_getVisibleFields(_1){var _2=_1.duplicate();for(var i=0;i<_1.length;i++){var _4=_1.get(i);if(!this.fieldShouldBeVisible(_4)||_4.visible==false)_2.remove(_4)}
return _2}
,isc.A.computeTileDimensions=function isc_TileGrid_computeTileDimensions(_1){if(((this.tileHeight&&this.tileWidth)||(this.tileSize))&&!_1)return;if(!((isc.ResultSet&&isc.isA.ResultSet(this.data)&&this.data.resultSize>=this.data.getLength())||isc.isAn.Array(this.data))){return}
if(this.layoutPolicy!="fit")return;var _2=0,_3=0;this.detailViewer.clipValues=false;for(var i=0;i<this.data.getLength();i++){var t=this.getTile(i);var _6=t.overflow;t.setOverflow("visible");t.redraw();t.show();var _7=t.getVisibleHeight();var _8=t.getVisibleWidth();if(_7>_2)_2=_7;if(_8>_3)_3=_8;t.setOverflow(_6);t.hide()}
this.detailViewer.clipValues=true;if(!this.tileHeight&&_2>0)this.tileHeight=_2;if(!this.tileWidth&&_3>0)this.tileWidth=_3}
,isc.A.getTileID=function isc_TileGrid_getTileID(_1){if(!_1)return null;var _2=this.getDataSource();if(_2&&_2.getPrimaryKeyFieldNames().length>0){var _3=_2.getPrimaryKeyFields();var _4="";for(var _5 in _3){_4+=_1[_5]}
return this.$601[_4]}else{return _1.$57v}}
,isc.A.setTileID=function isc_TileGrid_setTileID(_1,_2){var _3=this.getDataSource();if(_3&&_3.getPrimaryKeyFieldNames().length>0){var _4=_3.getPrimaryKeyFields();var _5="";for(var _6 in _4){_5+=_1[_6]}
this.$601[_5]=_2}else{_1.$57v=_2}}
,isc.A.getTileRecord=function isc_TileGrid_getTileRecord(_1){var _2=this;var _3=_2.data;var _4,_5;if(isc.isA.ResultSet(this.data)&&!this.data.lengthIsKnown())return null;if(this.showAllRecords||_2.getDrawnStartIndex()==null||_2.getDrawnEndIndex()==null){_4=0;_5=_3.getLength()}else{_4=_2.getDrawnStartIndex();_5=_2.getDrawnEndIndex()+1;if(_5>_3.getLength())_5=_3.getLength()}
for(var i=_4;i<_5;i++){var _7=_3.get(i);if(_2.getTileID(_7)==_1.ID)return _7}
return null}
,isc.A.setTileRecord=function isc_TileGrid_setTileRecord(_1,_2){return null}
,isc.A.setData=function isc_TileGrid_setData(_1){if(this.$35v){return false}
if(!_1)return;if(this.data){this.ignore(this.data,"dataChanged");this.ignore(this.data,"dataArrived")}
if(_1)this.data=_1;if(!this.data)return;if(this.data){if(isc.ResultSet&&isc.isA.ResultSet(this.data)){this.observe(this.data,"dataArrived","observer.dataArrived(arguments[0],arguments[1])");this.observe(this.data,"dataChanged","observer.dataChanged(operationType, originalRecord, rowNum, updateData)")}else{this.observe(this.data,"dataChanged","observer.dataChanged()")}}
if(!this.selection||(this.data!=this.selection.data)){this.createSelectionModel()}
this.dataChanged()}
,isc.A.getData=function isc_TileGrid_getData(){return this.data}
,isc.A.getPrimaryKeys=function isc_TileGrid_getPrimaryKeys(_1){var _2=this.data;if(!isc.ResultSet||!isc.isA.ResultSet(_2))return _1;var _3=this.getDataSource(),_4=_3.getPrimaryKeyFieldNames(),_5={};if(!isc.isAn.Array(_4))_4=[_4];for(var i=0;i<_4.length;i++){_5[_4[i]]=_1[_4[i]]}
return _5}
,isc.A.setRecordValues=function isc_TileGrid_setRecordValues(_1,_2){if(!this.data)return;var _3=this.data.indexOf(_1);if(_3==-1)return;var _4=this.data.get(_3);isc.combineObjects(_4,_2);if(this.valuesManager!=null){this.valuesManager.$71e(_3,null,_4,this)}
this.logDebug('calling layoutTiles from setRecordValues',"TileGrid");this.layoutTiles()}
,isc.A.dataArrived=function isc_TileGrid_dataArrived(_1,_2){}
,isc.A.dataChanged=function isc_TileGrid_dataChanged(_1,_2,_3,_4){if(!this.data||(isc.ResultSet&&isc.isA.ResultSet(this.data)&&!this.data.lengthIsKnown()))
{this.logDebug("dataChanged: returning due to no data yet","TileGrid");return}
this.computeTileDimensions();if(!this.$257)this.$257=0;if(_1=="add"){this.logDebug("add","TileGrid");this.layoutTiles()}else if(_1=="remove"){this.logDebug("remove","TileGrid");if((this.recycleTiles&&this.data.getLength()<this.getDrawnEndIndex()-this.getDrawnStartIndex()+1)||!this.recycleTiles){var _5=this.tiles[_3];this.tiles.remove(_5);_5.destroy()}
this.layoutTiles()}else if(_1=="update"){this.logDebug("update","TileGrid");this.layoutTiles()}else if(this.data.getLength()>=this.$257){this.logDebug("filter or sort, new data same or longer","TileGrid");if(this.$257>0)this.$582();else this.layoutTiles()}else{this.logDebug("filter or sort, new data shorter","TileGrid");this.selection.deselectAll();var _6=this.getDrawnEndIndex()+1;var _7=_6>this.data.getLength()?this.data.getLength():_6;var _8=this.getTilesPerLine();if(Math.floor(_6/ _8)>Math.floor(_7/ _8)&&this.getScrollTop()!=0&&this.recycleTiles){this.scrollToTop();this.layoutTiles()}else{this.$582()}}
this.$257=this.data.getLength();if(this.data.getLength()==0){this.cleanupExtraTiles(0)}}
,isc.A.$582=function isc_TileGrid__layoutAfterDataChange(){if(this.destroying)return;if(this.animateTileChange){if(this.$35v){var _1=this.$0j;for(var i=0;i<_1.length;i++){this.finishAnimation(_1[i].ID);_1[i].tile.hide()}
return}
this.fireOnPause("tileGridAnimate",this.$583)}else{this.logDebug('calling layoutTiles from layoutAfterDataChange',"TileGrid");this.layoutTiles()}}
,isc.A.cleanupExtraTiles=function isc_TileGrid_cleanupExtraTiles(_1){var _2=this.tiles;for(var i=_1;i<_2.length;i++){var _4=_2[i];_4.hide();_4.moveTo(0,0)}}
,isc.A.destroy=function isc_TileGrid_destroy(){if(this.data){this.ignore(this.data,"dataChanged");this.ignore(this.data,"dataArrived");if(this.data.$31k&&isc.isA.Function(this.data.destroy))
this.data.destroy()}
this.Super("destroy",arguments)}
,isc.A.$57t=function isc_TileGrid__getTileID(_1){return this.ID+"_tile_"+_1}
,isc.A.getLength=function isc_TileGrid_getLength(){if(!this.data||(isc.ResultSet&&isc.isA.ResultSet(this.data)&&!this.data.lengthIsKnown()))return 0;else return this.data.getLength()}
,isc.A.makeTile=function isc_TileGrid_makeTile(_1,_2){var _3={ID:this.$57t(_2),tileNum:_2,canHover:true,handleHover:function(){if(this.creator.itemHover)this.creator.fireCallback("itemHover","item",[this])},mouseDown:function(){this.creator.$673(this);this.creator.focus()},rightMouseDown:function(){var _4=this.creator.$90l(this);if(_4==false)return false;this.creator.focus();return _4},mouseUp:function(){this.creator.$674(this)},doubleClick:function(){var _5=this.creator.getTileRecord(this);return this.creator.recordDoubleClick(this.creator,this,_5)}};if(_1.tileProperties)isc.addProperties(_3,_1.tileProperties);var _6=_1.tileConstructor?_1.tileConstructor:this.tileConstructor;var _7=this.createAutoChild("tile",_3,_6);this.detailViewer.setWidth(_7.getInnerWidth());this.detailViewer.setHeight(_7.getInnerHeight());return _7}
,isc.A.getTileHTML=function isc_TileGrid_getTileHTML(_1){return this.detailViewer.getBlockHTML([_1])}
,isc.A.getTile=function isc_TileGrid_getTile(_1){var _2,_3,_4;if(isc.isAn.Object(_1)){_3=_1;_4=this.data.indexOf(_1);_2=this.getTileID(_1)}else{_3=this.data.get(_1);if(!_3)return null;_2=this.$57t(_1);_4=_1}
if(!_2)_2=this.$57t(_4);if(this.canReclaimTile(_4)&&!_3.tileConstructor){var _5=this.$57w(_4);_5.redraw();if(this.selection.isSelected(_3)){_5.setSelected(true)}else{_5.setSelected(false)}
return _5}else if(_2&&window[_2]){var _5=this.$57w(_4,window[_2]);_5.redraw();if(this.selection.isSelected(_3)){_5.setSelected(true)}else{_5.setSelected(false)}
return _5}else{var _6=this.$57t(_4),_7;this.setTileID(_3,_6);_7=this.makeTile(_3,_4);if(!this.tiles)this.tiles=[];this.tiles.add(_7);return _7}}
,isc.A.$728=function isc_TileGrid__clearLogs(){this.$726=[]}
,isc.A.$729=function isc_TileGrid__limitLog(_1,_2){if(!this.$726.find("key",_2)){this.$726.add({key:_2,logs:this.$727})}
if(this.$726.find("key",_2).logs>0){isc.logWarn(_1);this.$726.find("key",_2).logs-=1}}
,isc.A.layoutTiles=function isc_TileGrid_layoutTiles(){this.computeTileDimensions();this.invokeSuper(isc.TileGrid,"layoutTiles");var _1=this.tiles?this.tiles.length:0;var _2=this.$608;if(!this.$35v&&_2<_1)this.cleanupExtraTiles(_2)}
,isc.A.$57w=function isc_TileGrid__reclaimTile(_1,_2){var _3=this.data.get(_1),_4;if(!_2){var _5=_1-this.getDrawnStartIndex();_4=this.tiles[_5]}else{_4=_2}
var _6=this.getTileRecord(_4);if(_6)this.setTileID(_6,null);this.setTileID(_3,_4.ID);_4.tileNum=_1;return _4}
,isc.A.canReclaimTile=function isc_TileGrid_canReclaimTile(_1){var _2=this.getDrawnStartIndex()||0;if(this.recycleTiles&&this.tiles&&this.tiles.length>_1-_2){return true}else{return false}}
,isc.A.$90l=function isc_TileGrid__tileRightMouseDown(_1){var _2=this.getTileRecord(_1);if(this.recordContextClick!=null){if(this.recordContextClick(this,_1,_2)==false)return false}
return this.$673(_1)}
,isc.A.$673=function isc_TileGrid__tileMouseDown(_1){var _2=this.getTileRecord(_1);if(_2)this.selection.selectOnMouseDown(this,_1.tileNum);this.recordClick(this,_1,_2);var _3,_4;if(_1.getTop()<this.getScrollTop()){_4="top"}else if(_1.getTop()+_1.getVisibleHeight()>this.getScrollTop()+this.getInnerHeight()){_4="bottom"}
if(_1.getLeft()<this.getScrollLeft()){_3="left"}else if(_1.getLeft()+_1.getVisibleWidth()>this.getScrollLeft()+this.getInnerWidth()){_3="right"}
if(_3||_4){this.scrollIntoView(_1.getLeft(),_1.getTop(),_1.getVisibleWidth(),_1.getVisibleHeight(),_3,_4,true)}}
,isc.A.$674=function isc_TileGrid__tileMouseUp(_1){this.selection.selectOnMouseUp(this,_1.tileNum)}
,isc.A.recordClick=function isc_TileGrid_recordClick(){return true}
,isc.A.recordDoubleClick=function isc_TileGrid_recordDoubleClick(){return true}
,isc.A.recordContextClick=function isc_TileGrid_recordContextClick(){}
,isc.A.selectionChange=function isc_TileGrid_selectionChange(_1,_2){if(this.selectionChanged&&(this.selectionChanged(_1,_2)==false))return false;var _3=this.selection,_4=_3.lastSelectionItem;var _5=window[this.getTileID(_4)];if(_5&&_5.setSelected){_5.setSelected(_2)}}
,isc.A.keyPress=function isc_TileGrid_keyPress(_1,_2){if(this.isAnimating("scroll"))return false;var _3=this.selection.lastSelectionItem;if(!_3)return;var _4=_1.keyName,_5=this.selection.data.indexOf(_3),_6=this.orientation=="horizontal",_7;if(_4==this.$20s){_7=_6?this.$60p(_5,"above"):_5-1}else if(_4==this.$20t){_7=_6?this.$60p(_5,"below"):_5+1}else if(_4==this.$60n){_7=_6?_5-1:this.$60p(_5,"above")}else if(_4==this.$60o){_7=_6?_5+1:this.$60p(_5,"below")}else{return}
if(_7==-1||_7>this.data.getLength()-1)return;if(_7==null)return false;var _8=this.selection.data.get(_7),_9=window[this.getTileID(_8)];if(_9){this.$673(_9)}
return false}
,isc.A.$60p=function isc_TileGrid__adjacentTileIndex(_1,_2){var _3=this.selection.data,_4=_1,_5=window[this.getTileID(_3.get(_4))],_6=this.orientation=="horizontal",_7=_6?_5.getTop():_5.getLeft(),_8=_6?_5.getLeft():_5.getTop(),_9=_6?_5.getVisibleWidth():_5.getVisibleHeight(),_10=_5;while(_7==(_6?_10.getTop():_10.getLeft())){_4=_2=="above"?_4-1:_4+1;if(_4<0||_4>_3.getLength()-1){return-1}
_10=window[this.getTileID(_3.get(_4))];if(!_10)return-1}
var _11=_6?_10.getTop():_10.getLeft();var _12=-1,_13=0;while((_6?_10.getTop():_10.getLeft())==_11){var _14=_6?_10.getLeft():_10.getTop(),_15=_6?_10.getVisibleWidth():_10.getVisibleHeight(),_16=this.$60q([_8,_8+_9],[_14,_14+_15]);if(_16>_13){_12=_4;_13=_16}
_4=_2=="above"?_4-1:_4+1;if(_4<0||_4>_3.getLength()-1)break;_10=window[this.getTileID(_3.get(_4))];if(!_10)break}
return _12}
,isc.A.$60q=function isc_TileGrid__getCommonRange(_1,_2){if((_2[0]>=_1[0]&&_2[0]<=_1[1])||(_2[1]>=_1[0]&&_2[1]<=_1[1])||(_2[0]<=_1[0]&&_2[1]>=_1[1]))
{var _3=_2[0]>_1[0]?_2[0]:_1[0];var _4=_2[1]>_1[1]?_1[1]:_2[1];return _4-_3}
return 0}
,isc.A.addTile=function isc_TileGrid_addTile(){return false}
,isc.A.removeTile=function isc_TileGrid_removeTile(){return false}
,isc.A.getRecordTile=function isc_TileGrid_getRecordTile(_1){if(_1==null)return null;if(_1>=this.data.getLength())return null;var _2=this.getTileID(this.data.get(_1));if(!_2)return null;else return window[_2]}
,isc.A.childVisibilityChanged=function isc_TileGrid_childVisibilityChanged(_1,_2){this.invokeSuper(isc.TileLayout,"childVisibilityChanged",_1,_2)}
,isc.A.hasAllVisibleTiles=function isc_TileGrid_hasAllVisibleTiles(_1,_2){if(isc.isA.ResultSet(this.data)){if(!this.data.lengthIsKnown())return false;var _3=_1[1]+1;if(_3>this.data.getLength())_3=this.data.getLength();if(this.data.rangeIsLoaded(_1[0],_3)){return true}else{if(_2){this.logDebug("in hasAllVisibleTiles, fetching range: "+_1[0]+" to "+_3+", total length: "+this.data.getLength(),"TileGrid");this.data.getRange(_1[0],_3)}
return false}}else{return true}}
,isc.A.$31i=function isc_TileGrid__setUpDragProperties(){this.canReorderTiles=(this.canDrag||this.canReorderTiles);this.canDragTilesOut=(this.canDrag||this.canDragTilesOut);this.canAcceptDroppedRecords=(this.canAcceptDrop||this.canAcceptDroppedRecords)
this.canDrag=(this.canDrag||this.canDragTilesOut||this.canReorderTiles);this.canAcceptDrop=(this.canAcceptDrop||this.canAcceptDroppedRecords||this.canReorderTiles)}
,isc.A.getDragTrackerTitle=function isc_TileGrid_getDragTrackerTitle(_1){var _2=this.getTitleField(),_3=_1[_2];return"<nobr>"+_3+"</nobr>"}
,isc.A.drop=function isc_TileGrid_drop(){var _1=this.$610||0;if(_1>this.data.getLength())_1=0;var _2=this.ns.EH.dragTarget;var _3=this.$66f;this.$66f=null;var _4=_2.getDataSource(),_5=_2.cloneDragData();var _6=this.data.get(_1);this.transferRecords(_5,_6,_1,_2)}
,isc.A.dropMove=function isc_TileGrid_dropMove(){if(!this.canReorderTiles)return true;if(!this.canAcceptDroppedRecords&&isc.EH.dragTarget!=this)return true;this.showDragLineForRecord()}
,isc.A.dragMove=function isc_TileGrid_dragMove(){var _1=isc.EH.dropTarget;if(!this.canDragTilesOut&&_1!=null&&_1!=this)
{return false}
var _2=this.getSelectedRecord();if(this.tileDragAppearance=="outline"){var _3=this.ns.EH;var _4=this.getTileID(_2);var _5=window[_4];var _6="<div style='width:"+_5.getVisibleWidth()+";height:"+_5.getVisibleHeight()+"'>"+_3.getDragOutline(_5).getInnerHTML()+"</div>";_3.setDragTracker(_6)}else if(this.tileDragAppearance=="target"){var _3=this.ns.EH;var _4=this.getTileID(_2);var _5=window[_4];var _6="<div style='width:"+_5.getVisibleWidth()+";height:"+_5.getVisibleHeight()+"'>"+_5.getInnerHTML()+"</div>";_3.setDragTracker(_6);_5.hide();this.$815=_5;if(!_3.dragMoveAction)_3.dragMoveAction=_3.$ll;if(_3.dragTarget.showDragShadow)_3.$lm();if(_3.dragTarget.dragOpacity!=null)_3.$ln()}}
,isc.A.willAcceptDrop=function isc_TileGrid_willAcceptDrop(){var _1=this.ns.EH;if(!this.Super("willAcceptDrop",arguments))return false;var _2=_1.dragTarget;if(_2==this){if(!this.canReorderTiles)return false}else{if(!this.canAcceptDroppedRecords)return false}
if(!isc.isAn.Object(_2.getDragData()))return false;return true}
,isc.A.dragStop=function isc_TileGrid_dragStop(){this.Super("dropOut",arguments);if(this.$815){this.$815.show();this.$815=null}}
,isc.A.getCellValue=function isc_TileGrid_getCellValue(_1,_2){return this.detailViewer.getStandaloneFieldValue(_1,_2[this.fieldIdProperty])}
,isc.A.getStandaloneFieldValue=function isc_TileGrid_getStandaloneFieldValue(_1,_2){var _3=this.getCellValue(_1,this.getField(_2));return _3}
,isc.A.getTitleFieldValue=function isc_TileGrid_getTitleFieldValue(_1){var _2=this.getDataSource().getTitleField(),_3=this.getCellValue(_1,this.getDataSource().getField(_2));return _3}
,isc.A.hideField=function isc_TileGrid_hideField(_1){this.getField(_1).showIf="false";this.getField(_1).hidden=true;this.fieldStateChanged()}
,isc.A.showField=function isc_TileGrid_showField(_1){this.getField(_1).showIf="true";this.getField(_1).hidden=false;this.fieldStateChanged()}
,isc.A.getField=function isc_TileGrid_getField(_1){if(!this.fields)return null;return isc.Class.getArrayItem(_1,this.fields,this.fieldIdProperty)}
,isc.A.getFields=function isc_TileGrid_getFields(){return this.fields}
,isc.A.getAllFields=function isc_TileGrid_getAllFields(){return this.fields}
,isc.A.setFieldState=function isc_TileGrid_setFieldState(_1){if(isc.isA.String(_1))_1=this.evalViewState(_1,"fieldState")
if(_1){this.completeFields=this.$31y(_1);this.setFields(_1);this.markForRedraw();this.fieldStateChanged()}}
,isc.A.showActionInPanel=function isc_TileGrid_showActionInPanel(_1){if(_1.name=="sort")return true;return this.Super("showActionInPanel",arguments)}
,isc.A.getPrintHTML=function isc_TileGrid_getPrintHTML(){if(!this.data.lengthIsKnown()){isc.logWarn("Attempt to print TileGrid "+this.ID+" while data is loading will be ignored");return""}
var _1=this.data.getLength();if(!this.data.rangeIsLoaded(0,_1)){isc.logWarn("Make sure all data is loaded before attempting to print "+"TileGrid: "+this.ID);return""}
var _2;var _3=this.printTilesPerLine?this.printTilesPerLine:this.getTilesPerLine();if(this.orientation=="horizontal"){var _4=this.getInnerWidth();_2="<table width='"+_4+"'>";for(var i=0;i<_1;i++){var _6=this.getTile(i);if(i%_3==0){if(i==0)_2+="<tr>";else if(i<_1-1)_2+="</tr><tr>"}
_2+="<td>"+_6.getPrintHTML()+"</td>"}
_2+="</tr></table>"}else{_2="<table>";for(var i=0;i<_3;i++){_2+="<tr>";for(var j=i;j<_1;j+=_3){var _6=this.getTile(j);_2+="<td>"+_6.getPrintHTML()+"</td>"}
_2+="</tr>"}
_2+="</table>"}
return _2}
);isc.B._maxIndex=isc.C+63;isc.ClassFactory.defineClass("SimpleTile","StatefulCanvas");isc.A=isc.SimpleTile.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.baseStyle="simpleTile";isc.A.overflow="hidden";isc.A.showRollOver=true;isc.A.redrawOnStateChange=true;isc.A._redrawWithParent=false;isc.B.push(isc.A.initWidget=function isc_SimpleTile_initWidget(){this.invokeSuper(isc.SimpleTile,"initWidget",arguments);this.showDown=this.creator.valuesShowDown}
,isc.A.getInnerHTML=function isc_SimpleTile_getInnerHTML(){this.creator.detailViewer.currentTile=this;var _1=this.creator.getTileRecord(this);if(!_1)return null;return this.creator.getTileHTML(_1)}
,isc.A.getRecord=function isc_SimpleTile_getRecord(){return this.creator.getTileRecord(this)}
);isc.B._maxIndex=isc.C+3;isc.TileGrid.registerStringMethods({dataArrived:"startRecord,endRecord",selectionChanged:"record,state",itemHover:"item",itemClick:"item",recordClick:"viewer,tile,record",recordDoubleClick:"viewer,tile,record",recordContextClick:"viewer,tile,record",fieldStateChanged:""});isc.ClassFactory.defineClass("ColumnTree","Layout","DataBoundComponent");isc.A=isc.ColumnTree;isc.A.TREE_FIELD={name:"treeField",width:"*",getCellValue:function(_1,_2,_3,_4){return _1.creator.getCellValue(_1,_2,_3,_4)}};isc.A=isc.ColumnTree.getPrototype();isc.A.orientation="horizontal";isc.A.animateMemberEffect={effect:"slide",startFrom:"L",endAt:"R"};isc.A.folderIcon="[SKIN]/folder.gif";isc.A.customIconProperty="icon";isc.A.skinImgDir="images/TreeGrid/";isc.A.nodeIcon="[SKIN]file.gif";isc.A.openIconSuffix="open";isc.A.closedIconSuffix="closed";isc.A.showOpenIcons=true;isc.A.showCustomIconOpen=false;isc.A.customIconOpenProperty="showOpenIcon";isc.A.showColumn=true;isc.A.columnConstructor="ListGrid";isc.A.columnDefaults={animateTime:100,animateEffect:"slide",canAddFormulaFields:false,canAddSummaryFields:false,canSort:false,canGroupBy:false,showHeaderMenuButton:false,selectionChanged:function(_1,_2){if(_2){this.creator.nodeSelected(this,_1)}},bodyProperties:{$29h:function(_1,_2,_3,_4,_5){var _6="padding:0px;border:0px;";if(_4==null)_4=this.getTableElement(_2,_3);if(_4==null)return;if(!this.showHiliteInCells)
{if(_1==null)_1=this.getCellRecord(_2,_3);if(_5==null)_5=this.getCellStyle(_1,_2,_3);var _7=_4.childNodes[0];while(_7&&_7.tagName!="TABLE")_7=_7.childNodes[0];if(_7){_7.className=_5;if(this.getCellCSSText){_4.style.cssText=isc.StringBuffer.concat(this.$29i(_1,_2,_3,_5),this.zeroBorderPadding)}}}
return isc.GridRenderer.getPrototype().$29h.apply(this,[_1,_2,_3,_4,_5])}}};isc.A.showHeaders=false;isc.A.firstColumnTitle="&nbsp;";isc.A.showNodeCount=false;isc.A.wrapCells=false;isc.A.iconPadding=3;isc.A.ignoreEmptyCriteria=false;isc.A.backButtonTitle="Back";isc.A.backButtonDefaults={_constructor:"IButton",snapTo:"TR",left:5,top:5,autoFit:true,click:function(){this.creator.navigateBack()}};isc.A.overflow="hidden";isc.A=isc.ColumnTree.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$34p=["<table cellpadding=0 cellspacing=0 class='",,"' style='",,"border:0px;padding:0px;'><tr><td>",,"</td>","<td>"+(isc.Browser.isSafari||isc.Browser.isIE?"<nobr>":""),,,,(isc.Browser.isSafari?"</nobr>":"")+"</td><td style='padding-left:",,"px;'>",,,"</td>","</tr></table>"];isc.A.$34q=["<td>"+(isc.Browser.isSafari||isc.Browser.isIE?"<nobr>":""),,,,(isc.Browser.isSafari?"</nobr>":"")+"</td><td style='padding-left:",,"px;'>",,,"</td>"];isc.A.$34r="absmiddle";isc.A.$4s={};isc.B.push(isc.A.getDynamicDefaults=function isc_ColumnTree_getDynamicDefaults(_1){if(_1=="column"){return{autoDraw:false,showHiliteInCells:true,leaveScrollbarGap:false,selectionType:"single",showHeader:false,fields:isc.clone(this.fields)}}}
,isc.A.initWidget=function isc_ColumnTree_initWidget(){this.Super("initWidget",arguments);if(this.showMultipleColumns==null)
this.showMultipleColumns=!isc.Browser.isHandset;this.columns=[];if(!this.dataSource&&this.data!=null&&this.data.dataSource){this.dataSource=this.data.dataSource}
if(!this.fields||this.fields.length==0){this.fields=[isc.ColumnTree.TREE_FIELD]}
if(this.showMultipleColumns==false&&this.showHeaders&&this.showBackButton!=false){this.backButton=this.createAutoChild("backButton",{title:this.backButtonTitle,disabled:true});this.addChild(this.backButton);this.backButton.bringToFront()}
this.columns[0]=this.createAutoChild("column",this.getColumnProperties(this.data?this.data.getRoot():null,0),null,false);this.addColumn(this.columns[0],0);this.currentColumn=0;if(this.data)this.populateFirstColumn()}
,isc.A.populateFirstColumn=function isc_ColumnTree_populateFirstColumn(){if(this.data.showRoot){this.columns[0].setData([this.data.getRoot()])}else{this.columns[0].setData(this.data.getChildren(this.data.getRoot()))}
if(this.shouldShowHeader(null,0)){this.columns[0].setShowHeader(true);this.columns[0].setFieldProperties(0,{title:this.getColumnTitle(null,0)})}}
,isc.A.treeIsTied=function isc_ColumnTree_treeIsTied(_1,_2){return false}
,isc.A.getSelectedRecord=function isc_ColumnTree_getSelectedRecord(){if(this.currentColumn<=0)return this.data.getRoot();var _1=this.getColumn(this.currentColumn-1);return _1.getSelectedRecord()}
,isc.A.navigateBack=function isc_ColumnTree_navigateBack(){if(this.currentColumn<=0)return;var _1,_2;if(this.currentColumn>=2){_1=this.getColumn(this.currentColumn-2);_2=_1.getSelectedRecord()}else{_1=this.getColumn(0);_2=this.data.getRoot()}
this.logInfo("navigating to column: "+(this.currentColumn-1)+" to node: "+this.data.getTitle(_2));this.nodeSelected(_1,_2,true)}
,isc.A.slideTransition=function isc_ColumnTree_slideTransition(_1,_2,_3,_4){if(!isc.Browser.isWebKit){if(_4){_2.deselectAllRecords();_2.animateShow()}else{_1.animateHide();_2.show()}
_2.bringToFront();return}
this.logInfo((_4?"right":"left")+" slideTransition from: "+_1+" to "+_2+" within "+_3);_2.hide();_3.addChild(_2);if(!_2.isDrawn())_2.draw();var _5=_1.getStyleHandle();var _6=_2.getStyleHandle();_6.setProperty("-webkit-transition","none");var _7="translate3d("+(_4?"-":"")+_3.getViewportWidth()+"px, 0%, 0%)";_6.setProperty("-webkit-transform",_7);var _8=_3.overflow;_3.setOverflow("hidden");_2.show();isc.Timer.setTimeout(function(){_5.setProperty("-webkit-transition","-webkit-transform 0.3s ease-in-out");_6.setProperty("-webkit-transition","-webkit-transform 0.3s ease-in-out");_7="translate3d("+(_4?"":"-")+_3.getViewportWidth()+"px, 0%, 0%)";_5.setProperty("-webkit-transform",_7);_6.setProperty("-webkit-transform","translate3d(0px, 0%, 0%)");isc.Timer.setTimeout(function(){_1.hide();_3.setOverflow(_8)},350)},0)}
,isc.A.nodeSelected=function isc_ColumnTree_nodeSelected(_1,_2,_3){if(this.onNodeSelected!=null&&(this.onNodeSelected(_1,_2)==false)){return}
var _4=this.getColumnIndex(_2),_5=this.data.isFolder(_2);var _6=_4+1;if(!_5)_6-=1;var _7=this.columns[_6];if(!this.treeIsTied(_1,_2)){if(this.showMultipleColumns!=false)this.hideColumnsToRight(_6);if(!_5)return;this.data.openFolder(_2);if(isc.isA.ListGrid(_7)){_7.deselectAllRecords();_7.setData(this.data.getChildren(_2));this.addColumn(_7,_6)}else{_7=this.columns[_6]=this.createAutoChild("column",this.getColumnProperties(_2,_4+1),null,false);_7.setData(this.data.getChildren(_2));this.addColumn(_7,_6)}
if(this.shouldShowHeader(_2,_6)){_7.setShowHeader(true);var _8=this.getColumnTitle(_2,_6);_7.setFieldProperties(0,{title:_8})}
if(_7.data.getLength()>0){this.updateHeadingNodeCount(_2)}}
var _9=(_3?this.columns[this.currentColumn]:_1);var _10=_7;if(this.showMultipleColumns==false){this.slideTransition(_9,_10,this,_3?true:false)}else{_10.show()}
this.currentColumn=(_6<0?0:_6);this.logInfo("currentColumn is now: "+this.currentColumn);if(this.backButton){this.backButton.bringToFront();this.backButton.setDisabled(this.currentColumn<=0)}}
,isc.A.addColumn=function isc_ColumnTree_addColumn(_1,_2){if(this.showMultipleColumns==false){_1.resizeTo("100%","100%");this.addChild(_1,_2)}else{this.addMember(_1,_2)}}
,isc.A.getCurrentTitle=function isc_ColumnTree_getCurrentTitle(){return this.columns[this.currentColumn].getFieldTitle(0)}
,isc.A.getPreviousTitle=function isc_ColumnTree_getPreviousTitle(){if(this.currentColumn<=0)return"";return this.columns[this.currentColumn-1].getFieldTitle(0)}
,isc.A.updateHeadingNodeCount=function isc_ColumnTree_updateHeadingNodeCount(_1){var _2=this.getColumnIndex(_1);if(!this.shouldShowHeader(_1,_2)||!this.showNodeCount)return;if(_2<0)return;if(this.columns[_2+1].data.getLength()==0)return;var _3=this.data.getTitle(_1);if(this.showNodeCount){_3=_3+" ("+this.columns[_2+1].data.getLength()+")"}
this.columns[_2+1].setFieldProperties(0,{title:_3})}
,isc.A.getColumnIndex=function isc_ColumnTree_getColumnIndex(_1){if(this.data.showRoot){return this.data.getLevel(_1)}else{var _2=this.data.getLevel(_1);return _2-1}}
,isc.A.hideColumnsToRight=function isc_ColumnTree_hideColumnsToRight(_1){for(var i=_1+1;i<this.columns.length;i++){this.columns[i].hide();this.columns[i].deselectAllRecords()}}
,isc.A.shouldShowHeader=function isc_ColumnTree_shouldShowHeader(_1,_2){return this.showHeaders}
,isc.A.getColumnTitle=function isc_ColumnTree_getColumnTitle(_1,_2){if(_2==0){return this.firstColumnTitle}else{return this.data.getTitle(_1)}}
,isc.A.getRecord=function isc_ColumnTree_getRecord(_1,_2){if(_1==null||_1<0)return null;if(_2!=null){if(_2<0||_2>this.columns.length){return null}
if(_1>this.columns[_2].data.length||!this.columns[_2].isVisible()){return null}
return this.columns[_2].data[_1]}
var _3=0;for(var _4=0;_4<this.columns.length;_4++){if(!this.columns[_4].isVisible())continue;if(_3+this.columns[_4].data.length>_1){return this.columns[_4].data[_1-_3]}
_3+=this.columns[_4].data.length}
return null}
,isc.A.getTreeCellValue=function isc_ColumnTree_getTreeCellValue(_1,_2,_3,_4,_5){if(_3==null){return _1}
var _6=this.$34p;_6[1]=_2.getCellStyle(_3,_4,_5);_6[3]=_2.getCellCSSText(_3,_4,_5);var _7=this.$342(_1,_3,_4,true);for(var i=0;i<10;i++){_6[6+i]=_7[i]}
return _6.join(isc.emptyString)}
,isc.A.$342=function isc_ColumnTree__getTreeCellTitleArray(_1,_2,_3,_4){var _5=this.$34q;_5[1]=null;var _6=this.getIcon(_2),_7=(_3!=null?this.$34n+_3:null);_5[2]=null;_5[3]=this.getIconHTML(_6,_7,_2.iconSize);_5[5]=this.iconPadding;_5[7]=this.wrapCells?null:"<NOBR>"
_5[8]=_1;return _5}
,isc.A.getCellValue=function isc_ColumnTree_getCellValue(_1,_2,_3,_4,_5,_6,_7,_8){var _9=this.getNodeTitle(_2,_3);_9=this.getTreeCellValue(_9,_1,_2,_3,_4);return _9}
,isc.A.getIcon=function isc_ColumnTree_getIcon(_1,_2){if(isc.isA.Number(_1))_1=this.data.get(_1);if(!_1)return null;var _3=_1[this.customIconProperty],_4=(_3!=null),_5=this.data.isFolder(_1);if(!_4){if(_5)_3=this.folderIcon;else _3=this.nodeIcon}
var _6;if(_5){var _7=_2?false:(this.lastDropFolder==_1&&_1.$347),_8=_2?false:!!this.data.isOpen(_1);if(_7){if(_1.dropIcon!=null)_3=_1.dropIcon;else if(!_4&&this.folderDropImage!=null)_3=this.folderDropImage;else{var _9;if(_4){_9=_1[this.customIconDropProperty];if(_9==null)_9=this.showCustomIconDrop}else{_9=this.showDropIcons}
if(_9)_6=this.dropIconSuffix}}else if(_8){if(_1.openedIcon!=null)_3=_1.openedIcon;else if(!_4&&this.folderOpenImage!=null)_3=this.folderOpenImage;else{var _10;if(_4){_10=_1[this.customIconOpenProperty];if(_10==null)_10=this.showCustomIconOpen}else{_10=this.showOpenIcons}
if(_10)_6=this.openIconSuffix;else if(!_4)_6=this.closedIconSuffix}}else{if(!_4){if(this.folderClosedImage)_3=this.folderClosedImage;else _6=this.closedIconSuffix}}}else{if(!_4&&this.fileImage)_3=this.fileImage}
return isc.Img.urlForState(_3,false,false,_6)}
,isc.A.getIconHTML=function isc_ColumnTree_getIconHTML(_1,_2,_3){if(_1==null)return isc.emptyString;if(_3==null)_3=this.iconSize;var _4=this.$4s;_4.src=_1;_4.width=_4.height=_3;_4.name=_2;_4.align=this.$34r;var _5=this.$wf(_4);_5[14]=_2;return _5.join(isc.$ad)}
,isc.A.getNodeTitle=function isc_ColumnTree_getNodeTitle(_1,_2,_3){return this.data.getTitle(_1)}
,isc.A.getData=function isc_ColumnTree_getData(){return this.data}
,isc.A.setData=function isc_ColumnTree_setData(_1,_2,_3,_4){if(!isc.isA.Tree(_1))return;this.data=_1;this.data.columnTree=this;this.data.dataArrived="this.columnTree.updateHeadingNodeCount(parentNode);";this.data.separateFolders=this.separateFolders;if(this.showRoot&&isc.isA.ResultTree(this.data)){this.logWarn("showRoot may not be set with a databound columnTree, unexpected "+"results may occur")}
this.data.showRoot=this.showRoot;this.data.openDisplayNodeType=this.displayNodeType;this.data.openFolder(this.data.root);this.hideColumnsToRight(0);this.populateFirstColumn()}
,isc.A.useExistingDataModel=function isc_ColumnTree_useExistingDataModel(_1,_2,_3){return false}
,isc.A.createDataModel=function isc_ColumnTree_createDataModel(_1,_2,_3){return this.createResultTree(_1,_3.afterFlowCallback,_3,null)}
,isc.A.updateDataModel=function isc_ColumnTree_updateDataModel(_1,_2,_3){}
,isc.A.getColumn=function isc_ColumnTree_getColumn(_1){if(isc.isAn.Object(_1)){var _2=this.getColumnIndex(_1)+1;if(this.columns[_2]&&this.columns[_2].isVisible())return this.columns[_2]}else{if(this.columns[_1]&&_1<=this.currentColumn)return this.columns[_1]}
return null}
,isc.A.getColumnProperties=function isc_ColumnTree_getColumnProperties(_1,_2){}
,isc.A.selectAllRecords=function isc_ColumnTree_selectAllRecords(_1){if(_1==null)_1=0;if(!this.columns[_1])return;this.columns[_1].selectAllRecords()}
,isc.A.deselectAllRecords=function isc_ColumnTree_deselectAllRecords(_1){if(_1==null)_1=0;if(!this.columns[_1])return;this.columns[_1].deselectAllRecords()}
,isc.A.anySelected=function isc_ColumnTree_anySelected(_1){if(_1==null)_1=0;if(!this.columns[_1])return false;return this.columns[_1].anySelected()}
,isc.A.getSelection=function isc_ColumnTree_getSelection(_1){if(_1==null)_1=0;if(!this.columns[_1])return[];return this.columns[_1].getSelection()}
,isc.A.getSelectionObject=function isc_ColumnTree_getSelectionObject(_1){if(_1==null)_1=0;if(!this.columns[_1])return null;return this.columns[_1].selection}
);isc.B._maxIndex=isc.C+35;isc.ColumnTree.registerStringMethods({nodeSelected:"column, node",onNodeSelected:"column,node"})
isc.ClassFactory.defineClass("TableView","ListGrid");isc.A=isc.TableView;isc.A.PLAIN="plain";isc.A.GROUPED="grouped";isc.A.TITLE_ONLY="titleOnly";isc.A.TITLE_DESCRIPTION="titleAndDescription";isc.A.SUMMARY_INFO="summaryInfo";isc.A.SUMMARY_DATA="summaryData";isc.A.SUMMARY_FULL="summaryFull";isc.A.WHOLE_RECORD="wholeRecord";isc.A.NAVICON_ONLY="navIconOnly";isc.A=isc.TableView.getPrototype();isc.A.iconField="icon";isc.A.showIconField=true;isc.A.titleField="title";isc.A.infoField="info";isc.A.dataField="data";isc.A.descriptionField="description";isc.A.recordNavigationProperty="$79p";isc.A.tableMode=isc.TableView.PLAIN;isc.A.recordLayout=isc.TableView.TITLE_ONLY;isc.A.navIcon="[SKINIMG]/iOS/listArrow_button.png";isc.A.wholeRecordNavIcon="[SKINIMG]/iOS/listArrow.png";isc.A.navigationMode=isc.TableView.WHOLE_RECORD;isc.A.recordTitleStyle="recordTitle";isc.A.recordDescriptionStyle="recordDescription";isc.A.recordDataStyle="recordData";isc.A.recordInfoStyle="recordInfo";isc.A.iconFieldDefaults={width:50,imageSize:30,align:"center",type:"image"};isc.A.titleFieldDefaults={name:"TVtitleField",width:"*",type:"text",formatCellValue:function(_1,_2,_3,_4,_5){if(_5.formatRecord!=null){return _5.formatRecord(_2)}
var _6=_5.$80v(_2,_5.titleField),_7=_5.$80v(_2,_5.descriptionField),_8=_5.$80v(_2,_5.infoField),_9=_5.$80v(_2,_5.dataField),_10="";if(_5.recordLayout==isc.TableView.SUMMARY_INFO||_5.recordLayout==isc.TableView.SUMMARY_FULL)
{_10+="<span class='"+_5.recordInfoStyle+"'>"+_8+"</span>"}
_10+="<span class='"+_5.recordTitleStyle+"'>"+_6+"</span>";if(_5.recordLayout!=isc.TableView.TITLE_ONLY){_10+="<span class='"+_5.recordDescriptionStyle+"'>"+_7+"</span>"}
if(_5.recordLayout==isc.TableView.SUMMARY_DATA||_5.recordLayout==isc.TableView.SUMMARY_FULL)
{_10+="<span class='"+_5.recordDataStyle+"'>"+_9+"</span>"}
return _10}};isc.A.navigationFieldDefaults={name:"TVnavigationField",width:54,align:"right",formatCellValue:function(_1,_2,_3,_4,_5){if(_5.getShowNavigation(_2)){var _6=isc.Img.create({autoDraw:false,autoFit:true,imageType:"normal",src:_5.getNavigationIcon(_2)});return _6.getInnerHTML()}
return _5.$54t}};isc.A.groupByFieldDefaults={showIf:"false"};isc.A.canAddFormulaFields=false;isc.A.canAddSummaryFields=false;isc.A.showHeader=false;isc.A.selectionType="none";isc.A.skinImgDir="images/iOS/";isc.A.baseStyle="tableCell";isc.A.border="0px";isc.A.wrapCells=false;isc.A.cellHeight=44;isc.A.alternateRecordStyles=false;isc.A.canCollapseGroup=false;isc.A.groupStartOpen="all";isc.A.ignoreEmptyCriteria=false;isc.A=isc.TableView.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.initWidget=function isc_TableView_initWidget(){this.Super("initWidget",arguments);this.$80w={};if(this.formatRecord!=null&&!isc.isA.Function(this.formatRecord))
isc.Func.replaceWithMethod(this,"formatRecord","record")}
,isc.A.setFields=function isc_TableView_setFields(_1){this.invokeSuper(isc.TableView,"setFields",this.$80x(_1))}
,isc.A.$80x=function isc_TableView__defineTableFields(_1){var _2=_1||[];for(var i=0;i<_2.length;i++){_2[i].showIf="false"}
if(this.showIconField){var _4=_2.find(this.fieldIdProperty,this.iconField);if(_4)_2.remove(_4);this.$79q=_2.length;_2[_2.length]=isc.addProperties({name:this.iconField},this.iconFieldDefaults,this.iconFieldProperties)}
var _4=_2.find(this.fieldIdProperty,this.titleFieldDefaults.name);if(_4)_2.remove(_4);_2[_2.length]=isc.addProperties({},this.titleFieldDefaults,this.titleFieldProperties);var _4=_2.find(this.fieldIdProperty,this.navigationFieldDefaults.name);if(_4)_2.remove(_4);this.$79r=_2.length;_2[_2.length]=isc.addProperties({},this.navigationFieldDefaults,this.navigationFieldProperties);if(this.groupByField){var _5;if(isc.isA.Array(this.groupByField)){_5=this.groupByField}else{_5=[this.groupByField]}
for(var i=0;i<_5.length;i++){var _6=_2.find(this.fieldIdProperty,_5[i]);if(_6){isc.addProperties(_6,this.groupByFieldDefaults,this.groupByFieldProperties)}else{_2[_2.length]=isc.addProperties({name:_5[i]},this.groupByFieldDefaults,this.groupByFieldProperties)}}}
return _2}
,isc.A.$80v=function isc_TableView__getFormattedFieldValue(_1,_2){var _3=_1[_2]||this.$54t,_4=this.$80w[_2],_5;if(_4==null||_4==_5){_4=isc.Class.getArrayItemIndex(_2,this.getAllFields(),this.fieldIdProperty);this.$80w[_2]=_4}
if(_4>=0){_3=this.getFormattedValue(_1,_2,_3)}
return _3}
,isc.A.getNavigationIcon=function isc_TableView_getNavigationIcon(_1){return(this.navigationMode==isc.TableView.NAVICON_ONLY?this.navIcon:this.wholeRecordNavIcon)}
,isc.A.getShowNavigation=function isc_TableView_getShowNavigation(_1){if(_1&&_1[this.recordNavigationProperty]!=null){return _1[this.recordNavigationProperty]}
return this.showNavigation}
,isc.A.canSelectRecord=function isc_TableView_canSelectRecord(_1){return this.body.canSelectRecord(_1)}
,isc.A.recordClick=function isc_TableView_recordClick(_1,_2,_3,_4,_5,_6,_7){if(_5!=this.$79q&&_5!=this.$79r&&this.canSelectRecord(_2))
{this.selectSingleRecord(_2)}
if(_5==this.$79r||this.navigationMode==isc.TableView.WHOLE_RECORD){if(this.recordNavigationClick){isc.Func.replaceWithMethod(this,"recordNavigationClick","record");this.recordNavigationClick(_2)}}else if(_5==this.$79q){if(this.imageClick){isc.Func.replaceWithMethod(this,"imageClick","record");this.imageClick(_2)}}}
,isc.A.getBaseStyle=function isc_TableView_getBaseStyle(_1,_2,_3){if(this.isGrouped){var _4=this.data.get(_2),_5=this.data.isFirst(_4),_6=this.data.isLast(_4);if(_5&&_6){return(_3==0?"cellOnlyLeft":(_3==this.fields.length-1?"cellOnlyRight":"cellOnly"))}else if(_5){return(_3==0?"cellTopLeft":(_3==this.fields.length-1?"cellTopRight":"cellTop"))}else if(_6){return(_3==0?"cellBottomLeft":(_3==this.fields.length-1?"cellBottomRight":"cellBottom"))}}
return this.Super("getBaseStyle",arguments)}
);isc.B._maxIndex=isc.C+9;isc.TableView.registerStringMethods({recordNavigationClick:"record",imageClick:"record",formatRecord:"record"});isc.ClassFactory.defineClass("DOMTree","Tree");isc.A=isc.DOMTree.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.tagNameProperty="tagName";isc.A.elementProperty="$9b";isc.A.loadOnInit=true;isc.A.loadDataOnDemand=true;isc.A.hideTextNodes=true;isc.B.push(isc.A.makeRoot=function isc_DOMTree_makeRoot(){var _1=this.Super("makeRoot",arguments);_1[this.elementProperty]=this.rootElement;return _1}
,isc.A.getElement=function isc_DOMTree_getElement(_1){return _1[this.elementProperty]}
,isc.A.getElementTitle=function isc_DOMTree_getElementTitle(_1){var _2=_1.getAttribute(this.titleProperty);if(!this.valueIsEmpty(_2))return _2;_2=_1.getAttribute(this.nameProperty);if(!this.valueIsEmpty(_2))return _2;if(!isc.xml.hasElementChildren(_1)){_2=isc.xml.getElementText(_1);if(!this.valueIsEmpty(_2))return _2}
return _1.tagName||_1.nodeName}
,isc.A.valueIsEmpty=function isc_DOMTree_valueIsEmpty(_1){return _1==null||isc.isAn.emptyString(_1)}
,isc.A.isFolder=function isc_DOMTree_isFolder(_1){if(_1==this.root||_1.children!=null)return true;var _2=_1[this.elementProperty];if(!_2||!_2.childNodes||_2.childNodes.length==0)return false;if(!this.hideTextNodes)return true;return isc.xml.hasElementChildren(_2)}
,isc.A.moveList=function isc_DOMTree_moveList(_1,_2,_3){var _4=_1[0],_5=this.getElement(_4);this.logWarn("moveList: "+this.echoAll(_1)+", newParent: "+this.echo(_2)+", index: "+_3);this.$36g(_5,_2,_3);this.Super("moveList",arguments)}
,isc.A.remove=function isc_DOMTree_remove(_1){var _2=this.getElement(_1);_2.parentNode.removeChild(_2);return this.Super("remove",arguments)}
,isc.A.addElement=function isc_DOMTree_addElement(_1,_2,_3){this.$36g(_1,_2,_3);if(this.isLoaded(_2)){var _4=this.nodeForElement(_1);this.add(_4,_2,_3)}else{this.dataChanged()}}
,isc.A.$36g=function isc_DOMTree__addToDOM(_1,_2,_3){var _4=this.getElement(_2);if(_3==null){this.logWarn("appending: "+this.echoLeaf(_1)+" to: "+this.echoLeaf(_4));_4.appendChild(_1)}else{var _5=this.getChildren(_2)[_3],_6=this.getElement(_5);this.logWarn("inserting into: "+this.echoLeaf(_4)+", before: "+this.echoLeaf(_6));_4.insertBefore(_1,_6)}}
,isc.A.nodeForElement=function isc_DOMTree_nodeForElement(_1){var _2={};_2[this.elementProperty]=_1;_2[this.titleProperty]=this.getElementTitle(_1);if(this.tagNameProperty){_2[this.tagNameProperty]=_1.tagName||_1.nodeName}
if(this.copyAttributes){for(var j=0;j<this.copyAttributes.length;j++){var _4=this.copyAttributes[j];_2[_4]=_1.getAttribute(_4)}}
return _2}
,isc.A.loadChildren=function isc_DOMTree_loadChildren(_1){if(this.isLoaded(_1))return;try{var _2=_1.$9b;if(_2==null)return;var _3=_2.childNodes;if(isc.Browser.isMoz&&_2.contentDocument){_3=[_2.contentDocument.documentElement]}else{if(this.loadingBatch()&&!isc.xml.hasElementChildren(_2))return}
_1[this.openProperty]=true;if(_3!=null){for(var i=0;i<_3.length;i++){var _5=_3[i];if(this.hideTextNodes&&_5.nodeName.startsWith("#"))continue;var _6=this.nodeForElement(_5);this.add(_6,_1)}}
this.setLoadState(_1,isc.Tree.LOADED)}catch(e){this.logWarn("parent node: "+this.echo(_1)+", at path: "+this.getPath(_1)+", error: "+this.echo(e)+this.getStackTrace())}}
);isc.B._maxIndex=isc.C+11;isc.defineClass("DOMGrid","TreeGrid");isc.A=isc.DOMGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canDragRecordsOut=true;isc.A.canAcceptDroppedRecords=true;isc.A.canReorderRecords=true;isc.A.showRoot=true;isc.A.$36h="&lt;";isc.A.$36i="&gt;";isc.A.$36j=[" ",,'="',,'"'];isc.A.colorMap={table:"#009900",tr:"#333399",td:"#663366",form:"#CC6600",input:"#3333FF",textarea:"#3333FF",div:"#663300",span:"#663300"};isc.A.colorPrefix="color:";isc.A.$36k={};isc.B.push(isc.A.initWidget=function isc_DOMGrid_initWidget(){this.Super(this.$oc);if(this.url){isc.xml.loadXML(this.url,this.getID()+".setRootElement(xmlDoc.documentElement)")}}
,isc.A.getDefaultData=function isc_DOMGrid_getDefaultData(){return null}
,isc.A.getElement=function isc_DOMGrid_getElement(_1){return this.data.getElement(_1)}
,isc.A.setRootElement=function isc_DOMGrid_setRootElement(_1){this.rootElement=_1;var _2=isc.DOMTree.create({rootElement:_1});this.setData(_2)}
,isc.A.getIcon=function isc_DOMGrid_getIcon(){}
,isc.A.getNodeTitle=function isc_DOMGrid_getNodeTitle(_1,_2,_3){if(_1==null)return null;if(_1.$9g)return _1.$9g;var _4=this.htmlMode?this.$36l(_1):this.$36m(_1);return(_1.$9g=_4)}
,isc.A.dataChanged=function isc_DOMGrid_dataChanged(){this.Super("dataChanged",arguments);this.data.getOpenList().setProperty("$9g",null)}
,isc.A.$36m=function isc_DOMGrid__getXMLNodeTitle(_1){if(_1.$9b==null){this.logWarn("no element for node: "+this.echo(_1))}
var _2=_1.$9b,_3=isc.emptyString,_4=this.$36n;if(_4==null)_4=this.$36n=isc.SB.create();else _4.clear();_4.append(this.$36h,(_2.tagName||_2.nodeName));var _5=_2.attributes;if(_5!=null){var _6=this.$36j;for(var i=0;i<_5.length;i++){var _8=_5[i];_6[1]=_8.name;_6[3]=_8.value;_4.append(_6)}}
if(!isc.xml.hasElementChildren(_2)){_4.append(this.$36i,isc.xml.getElementText(_2),"&lt;/",(_2.tagName||_2.nodeName),this.$36i)}else if(_2.childNodes.length>0){_4.append(this.$36i)}else{_4.append("/&gt;")}
return _4.toString()}
,isc.A.$36l=function isc_DOMGrid__getHTMLNodeTitle(_1){var _2=_1.$9b,_3=isc.emptyString,_4,_5;if(isc.Browser.isIE&&_2.scopeName=="VML"){_4=(_2.style?_2.style.width:null);_5=(_2.style?_2.style.height:null)}else{_4=_2.width||(_2.style?_2.style.width:null);_5=_2.height||(_2.style?_2.style.height:null)}
var _6=(_2.tagName&&_2.tagName.toLowerCase()=="td");return isc.SB.concat(this.$36h,(_2.tagName||_2.nodeName),(_2.id?" ID="+_2.id:_3),(!this.valueIsEmpty(_4)?" WIDTH="+_4:_3),(!this.valueIsEmpty(_5)?" HEIGHT="+_5:_3),(_6&&_2.rowSpan>1?" ROWSPAN="+_2.rowSpan:_3),(_6&&_2.colSpan>1?" COLSPAN="+_2.colSpan:_3),this.$36i)}
,isc.A.valueIsEmpty=function isc_DOMGrid_valueIsEmpty(_1){return _1==null||isc.isAn.emptyString(_1)}
,isc.A.getCellCSSText=function isc_DOMGrid_getCellCSSText(_1,_2,_3){var _4=this.data.getElement(_1);if(_4==null)return null;var _5=_4.tagName;if(_5==null)return null;if(this.$36k[_5]){_5=this.$36k[_5]}else{this.$36k=_5=_5.toLowerCase()}
if(this.colorMap[_5]!=null){return isc.SB.concat(this.colorPrefix,this.colorMap[_5],isc.semi)}}
);isc.B._maxIndex=isc.C+11;isc.ClassFactory.defineClass("MenuBar","Toolbar");isc.addGlobal("Menubar",isc.MenuBar);isc.A=isc.MenuBar.getPrototype();isc.A.overflow=isc.Canvas.VISIBLE;isc.A.defaultHeight=22;isc.A.menuConstructor="Menu";isc.A.buttonConstructor="MenuBarButton";isc.A.tabIndex=-1;isc.A.tabWithinToolbar=false;isc.A.buttonDefaults={showDown:false,showRollOver:true,showFocused:true,showFocusedAsOver:true};isc.A=isc.MenuBar.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.initWidget=function isc_MenuBar_initWidget(){this.Super("initWidget",arguments)}
,isc.A.setButtons=function isc_MenuBar_setButtons(){var _1=[];if(this.menus){for(var i=0;i<this.menus.length;i++){var _3=this.menus[i];_1[i]=this.$36o(_3,i)}}
return this.Super("setButtons",[_1],arguments)}
,isc.A.$36o=function isc_MenuBar__getButtonProperties(_1,_2){return{title:_1.title,width:(_1.menuButtonWidth?_1.menuButtonWidth:_1.width),menuNum:_2,focusChanged:function(_3){if(isc.Browser.isMoz&&_3)this.bringToFront()}}}
,isc.A.setMenus=function isc_MenuBar_setMenus(_1){if(!isc.isAn.Array(_1))_1=[_1];for(var i=0;i<this.members.length;i++){var _3=this.members[i],_4=this.menus[_3.menuNum];if(_3.isObserving(_4,"hide")){_3.ignore(_4,"hide")}}
this.menus=_1;this.setButtons()}
,isc.A.$36p=function isc_MenuBar__remapButton(_1,_2){if(!_1)return;if(_2==-1){var _3=_1.menuNum,_4=this.menus[_1.menuNum];if(_1.isObserving(_4,"hide")){_1.ignore(_4,"hide")}}
_1.menuNum=_2}
,isc.A.addMenus=function isc_MenuBar_addMenus(_1,_2){if(!_1)return;if(!isc.isAn.Array(_1))_1=[_1];if(!this.menus)this.menus=[];if(_2==null)_2=this.menus.length;if(!this.$6c){this.menus.addListAt(_1,_2)}else{for(var i=_2;i<this.members.length;i++){this.$36p(this.members[i],(i+_1.length))}
this.menus.addListAt(_1,_2);var _4=[];for(var i=0;i<_1.length;i++){var _5=this.menus.indexOf(_1[i]);_4[i]=this.$36o(_1[i],_5)}
this.addButtons(_4,_2)}}
,isc.A.removeMenus=function isc_MenuBar_removeMenus(_1){if(_1==null)return;if(!isc.isAn.Array(_1))_1=[_1];var _2=[],_3=this.menus.duplicate();for(var i=0;i<_1.length;i++){var _5=_1[i];if(isc.isA.Number(_5))_5=this.menus[_5];else if(!this.menus.contains(_5))continue;_3.remove(_5);var _6=this.menus.indexOf(_5);if(this.$6c)_2.add(this.members[_6])}
if(!this.$6c){this.menus=_3;return}
for(var i=0;i<this.menus.length;i++){if(this.menus[i]==_3[i])continue;this.$36p(this.members[i],_3.indexOf(this.menus[i]))}
this.menus=_3;this.removeButtons(_2)}
,isc.A.showMenu=function isc_MenuBar_showMenu(_1){var _2;if(isc.isA.Number(_1))_2=this.menus[_1];else{_2=_1;_1=this.menus.indexOf(_2)}
if(!_2){this.logWarn("showMenu() called with invalid menu number: "+_1+".  No effect.");return}
var _3;for(var i=0;i<this.members.length;i++){if(this.members[i].menuNum==_1){_3=this.members[i]}}
if(!isc.isA.Canvas(_2)){if(_2.ID==null)_2.ID=this.getID()+"_menu"+_1;_2.autoDraw=false;_2=this.menus[_1]=isc.ClassFactory.newInstance(this.menuConstructor,_2,this.menuDefaults)}
if(this.activeMenu!=null){this.menus[this.activeMenu].hideMenuTree()}
_2.keyEventParent=this;_2.moveTo(_3.getPageLeft(),_3.getPageBottom());_2.show();_3.$36q=_3.showRollOver;_3.showRollOver=false;_3.setState(isc.StatefulCanvas.STATE_DOWN);this.activeMenu=_1;if(!_3.isObserving(_2,"hide")){_3.observe(_2,"hide","observer.menuHidden(observed)")}
var _5=isc.EH;if(_5.targetIsMasked(this))this.bringToFront();var _6=_5.clickMaskRegistry.last(),_7=_5.getMaskedFocusCanvas(_6);if(this.members.contains(_7))_5.setMaskedFocusCanvas(null,_6);_2.body.focusOnHide=_3}
,isc.A.$6d=function isc_MenuBar__focusInNextButton(_1){if(!this.activeMenu==null)return this.Super("$6d",arguments);if(_1==null)_1=true;var _2=this.activeMenu,_3=_1?1:-1,_4=_2+_3,_5=this.getMembers();while(_2!=_4){if(_4<0)_4=_5.length-1;else if(_4>=this.members.length)_4=0;var _6=_5[_4];if(!_6.isDisabled()){_6.showMenu();break}
_4+=_3}}
,isc.A.getFocusButtonIndex=function isc_MenuBar_getFocusButtonIndex(){if(this.activeMenu!=null)return this.activeMenu;return this.Super("getFocusButtonIndex",arguments)}
);isc.B._maxIndex=isc.C+10;isc.ClassFactory.defineClass("MenuBarButton","MenuButton");isc.A=isc.MenuBarButton.getPrototype();isc.A.showMenuButtonImage=false;isc.A.showDown=false;isc.A.autoDraw=false;isc.A.align="center";isc.A=isc.MenuBarButton.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.mouseOver=function isc_MenuBarButton_mouseOver(){this.Super("mouseOver",arguments);var _1=this.parentElement.activeMenu;if(_1!=null&&_1!=this.menuNum){this.showMenu()}}
,isc.A.mouseDown=function isc_MenuBarButton_mouseDown(){if(this.parentElement.activeMenu==this.menuNum){isc.Menu.hideAllMenus()}else{this.showMenu()}}
,isc.A.mouseUp=function isc_MenuBarButton_mouseUp(){}
,isc.A.click=function isc_MenuBarButton_click(){}
,isc.A.mouseOut=function isc_MenuBarButton_mouseOut(){if(this.parentElement.activeMenu!=this.menuNum){this.Super("mouseOut",arguments)}}
,isc.A.handleKeyPress=function isc_MenuBarButton_handleKeyPress(_1,_2){if(_1.keyName=="Space"||_1.keyName=="Enter")return this.showMenu();if(this.keyPress){this.convertToMethod("keyPress");return this.keyPress(_1,_2)}}
,isc.A.showMenu=function isc_MenuBarButton_showMenu(){this.parentElement.showMenu(this.menuNum)}
,isc.A.menuHidden=function isc_MenuBarButton_menuHidden(_1){if(isc.$cv)arguments.$cw=this;if(this.state==isc.StatefulCanvas.STATE_DOWN){if(this.hasFocus&&this.showFocused)this.setState(isc.StatefulCanvas.STATE_OVER);else this.setState(isc.StatefulCanvas.STATE_UP)}
this.showRollOver=this.$36q;delete this.$36q;this.menuIsDown=false;if(this.parentElement.activeMenu==this.menuNum){this.parentElement.activeMenu=null}
delete _1.eventParent;this.ignore(_1,"hide")}
);isc.B._maxIndex=isc.C+8;isc.ClassFactory.defineClass("CellSelection");isc.A=isc.CellSelection;isc.A.$24e=0;isc.A.COL_SELECTION_FLAGS=null;isc.A=isc.CellSelection;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.generateFlagTable=function isc_c_CellSelection_generateFlagTable(){isc.CellSelection.COL_SELECTION_FLAGS=[];for(var i=0;i<32;i++)
isc.CellSelection.COL_SELECTION_FLAGS[i]=Math.pow(2,i)}
);isc.B._maxIndex=isc.C+1;isc.A=isc.CellSelection.getPrototype();isc.A.data=null;isc.A.numCols=0;isc.A.selectionProperty=null;isc.A.$q7=true;isc.A.$24f=[];isc.A.lastSelectedCell=[];isc.A.changedCells=[];isc.A=isc.CellSelection.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.init=function isc_CellSelection_init(){if(!isc.CellSelection.COL_SELECTION_FLAGS)isc.CellSelection.generateFlagTable();if(!this.selectionProperty)this.selectionProperty="_cellSelection_"+isc.CellSelection.$24e++;this.setData((this.data?this.data:[]))}
,isc.A.setData=function isc_CellSelection_setData(_1){if(this.data!=null)this.ignoreData(this.data);this.data=_1;if(this.data!=null)this.observeData(this.data)}
,isc.A.observeData=function isc_CellSelection_observeData(_1){this.observe(_1,"dataChanged","observer.$q7 = true")}
,isc.A.ignoreData=function isc_CellSelection_ignoreData(_1){this.ignore(_1,"dataChanged")}
,isc.A.cellIsSelected=function isc_CellSelection_cellIsSelected(_1,_2){var _3=this.data[_1],_4=(_3?_3[this.selectionProperty]:null),_5=(_4?_4[Math.floor(_2/ 32)]:null),_6=isc.CellSelection.COL_SELECTION_FLAGS[_2%32];return(_5!=null&&((_5&_6)!=0))}
,isc.A.rowHasSelection=function isc_CellSelection_rowHasSelection(_1){var _2=this.data[_1],_3=(_2?_2[this.selectionProperty]:null),_4=Math.ceil(this.numCols/ 32);if(!_2||!_2[this.selectionProperty])return false;for(var i=0;i<_4;i++){if(_3[i])return true}
return false}
,isc.A.colHasSelection=function isc_CellSelection_colHasSelection(_1){if(_1>this.numCols-1)return false;var _2=isc.CellSelection.COL_SELECTION_FLAGS[_1%32],_3=Math.floor(_1/ 32);var _4=this.data,_5=_4.length;for(var i=0;i<_5;i++){var _7=_4[i][this.selectionProperty];if(_7&&_7[_3]&&((_7[_3]&_2)!=0))
return true}
return false}
,isc.A.anySelected=function isc_CellSelection_anySelected(){var _1=Math.ceil(this.numCols/ 32);var _2=this.data,_3=_2.length;for(var i=0;i<_3;i++){var _5=_2[i][this.selectionProperty];if(!_5)continue;for(var j=0;j<_1;j++){if(_5[j])return true}}
return false}
,isc.A.getSelectedCells=function isc_CellSelection_getSelectedCells(){if(!this.$q7)return this.$24f;var _1=[],_2=isc.CellSelection.COL_SELECTION_FLAGS,_3=Math.ceil(this.numCols/ 32),_4=this.data,_5=_4.length,_6;for(var i=0;i<_5;i++){_6=_4[i][this.selectionProperty];if(!_6)continue;for(var j=0,_9,_10;j<_3;j++){_9=_6[j];if(!_9)continue;_10=(j==_3-1&&this.numCols%32!=0)?this.numCols%32:32;for(var k=0;k<_10;k++){if((_9&_2[k])!=0){_1[_1.length]=[i,j*32+k]}}}}
this.$24f=_1;this.$q7=false;return _1}
,isc.A.getSelectionRowNums=function isc_CellSelection_getSelectionRowNums(){var _1=[],_2=Math.ceil(this.numCols/ 32),_3=this.data,_4=_3.length,_5;for(var i=0;i<_4;i++){_5=_3[i][this.selectionProperty];if(!_5)continue;for(var j=0,_8;j<_2;j++){if(_5[j]){_1[_1.length]=i;break}}}
return _1}
,isc.A.getSelectionColNums=function isc_CellSelection_getSelectionColNums(){var _1=[],_2=[],_3=isc.CellSelection.COL_SELECTION_FLAGS,_4=Math.ceil(this.numCols/ 32),_5=this.data,_6=_5.length,_7;for(var i=0;i<_6;i++){_7=_5[i][this.selectionProperty];if(!_7)continue;for(var j=0,_10;j<_4;j++){if(_7[j]){_2[j]=_2[j]|_7[j]}}}
if(_2.length==0)return _1;for(var i=0,_11=this.numCols;i<_11;i++){if((_2[Math.floor(i/ 32)]&_3[i%32])!=0)
_1[_1.length]=i}
return _1}
,isc.A.getSelectionBounds=function isc_CellSelection_getSelectionBounds(){var _1=this.getSelectionRowNums(),_2=this.getSelectionColNums();return[_1.first(),_2.first(),_1.last(),_2.last()]}
,isc.A.$24g=function isc_CellSelection__setCellSelection(_1,_2,_3){var _4=this.data[_1],_5=(_4?_4[this.selectionProperty]:null),_6=Math.floor(_2/ 32),_7=(_5?_5[Math.floor(_2/ 32)]:0),_8=isc.CellSelection.COL_SELECTION_FLAGS[_2%32];if(!_4||_2>this.numCols-1)return false;if(_4.enabled==false)return false;if(_5==null){_5=_4[this.selectionProperty]=[];for(var i=0,_10=Math.ceil(this.numCols/ 32);i<_10;i++)_5[i]=0}
else if(_7==null){_5[_6]=0}
if(((_7&_8)!=0)==_3)return false;_5[_6]=_7^_8;if(_3)this.lastSelectedCell=[_1,_2];this.$q7=true;return true}
,isc.A.setCellRangeSelection=function isc_CellSelection_setCellRangeSelection(_1,_2,_3,_4,_5){this.changedCells=this.$24h(_1,_2,_3,_4,_5);return this.$24i()}
,isc.A.$24h=function isc_CellSelection__setCellRangeSelection(_1,_2,_3,_4,_5){var _6=[],_7,_8,_9,_10;if(_1<=_3){_7=_1;_8=_3}else{_7=_3;_8=_1}
if(_2<=_4){_9=_2;_10=_4}else{_9=_4;_10=_2}
if(this.logIsDebugEnabled()){this.logDebug((_5?"selecting ":"deselecting ")+[_7,_9]+" through "+[_8,_10])}
for(var _11=_7;_11<=_8;_11++){for(var _12=_9;_12<=_10;_12++){if(this.$24g(_11,_12,_5)){_6[_6.length]=[_11,_12]}}}
return _6}
,isc.A.setCellListSelection=function isc_CellSelection_setCellListSelection(_1,_2){if(!_1)return false;var _3=[];for(var i=0,_5=_1.length,_6,_7;i<_5;i++){_6=_1[i][0];_7=_1[i][1];if(this.$24g(_6,_7,_2))
_3[_3.length]=[_6,_7]}
this.changedCells=_3;return this.$24i()}
,isc.A.$24i=function isc_CellSelection__cellSelectionsChanged(){if(this.changedCells.length>0){this.selectionChanged();return true}else
return false}
,isc.A.selectionChanged=function isc_CellSelection_selectionChanged(){}
,isc.A.setCellSelection=function isc_CellSelection_setCellSelection(_1,_2,_3){if(this.$24g(_1,_2,_3)){this.changedCells=[[_1,_2]];this.selectionChanged();return true}else
return false}
,isc.A.selectCell=function isc_CellSelection_selectCell(_1,_2){return this.setCellSelection(_1,_2,true)}
,isc.A.deselectCell=function isc_CellSelection_deselectCell(_1,_2){return this.setCellSelection(_1,_2,false)}
,isc.A.selectCellRange=function isc_CellSelection_selectCellRange(_1,_2,_3,_4){this.changedCells=this.$24h(_1,_2,_3,_4,true);return this.$24i()}
,isc.A.deselectCellRange=function isc_CellSelection_deselectCellRange(_1,_2,_3,_4){this.changedCells=this.$24h(_1,_2,_3,_4,false);return this.$24i()}
,isc.A.selectRow=function isc_CellSelection_selectRow(_1){return this.selectCellRange(_1,0,_1,this.numCols-1)}
,isc.A.deselectRow=function isc_CellSelection_deselectRow(_1){return this.deselectCellRange(_1,0,_1,this.numCols-1)}
,isc.A.selectCol=function isc_CellSelection_selectCol(_1){return this.selectCellRange(0,_1,this.data.length-1,_1)}
,isc.A.deselectCol=function isc_CellSelection_deselectCol(_1){return this.deselectCellRange(0,_1,this.data.length-1,_1)}
,isc.A.selectAll=function isc_CellSelection_selectAll(){return this.selectCellRange(0,0,this.data.length-1,this.numCols-1)}
,isc.A.deselectAll=function isc_CellSelection_deselectAll(){return this.deselectCellRange(0,0,this.data.length-1,this.numCols-1)}
,isc.A.selectCellList=function isc_CellSelection_selectCellList(_1){return this.setCellListSelection(_1,true)}
,isc.A.deselectCellList=function isc_CellSelection_deselectCellList(_1){return this.setCellListSelection(_1,false)}
,isc.A.selectSingleCell=function isc_CellSelection_selectSingleCell(_1,_2){var _3=this.cellIsSelected(_1,_2);this.changedCells=this.$24h(0,0,this.data.length-1,this.numCols-1,false);this.$24g(_1,_2,true);if(!_3)
this.changedCells[this.changedCells.length]=[_1,_2];return this.$24i()}
,isc.A.selectSingleRow=function isc_CellSelection_selectSingleRow(_1){var _2=[];if(_1>0)
_2=this.$24h(0,0,_1-1,this.numCols-1,false);_2=_2.concat(this.$24h(_1,0,_1,this.numCols-1,true));if(_1<this.data.length-1)
_2=_2.concat(this.$24h(_1+1,0,this.data.length-1,this.numCols-1,false));this.changedCells=_2;return this.$24i()}
,isc.A.selectSingleCol=function isc_CellSelection_selectSingleCol(_1){var _2=[];if(_1>0)
_2=this.$24h(0,0,this.data.length-1,_1-1,false);_2=_2.concat(this.$24h(0,_1,this.data.length-1,_1,true));if(_1<this.numCols-1)
_2=_2.concat(this.$24h(0,_1+1,this.data.length-1,this.numCols-1,false));this.changedCells=_2;return this.$24i()}
,isc.A.selectOnMouseDown=function isc_CellSelection_selectOnMouseDown(_1,_2,_3){if(_1.selectionType==isc.Selection.NONE)return false;this.startRow=this.lastRow=_2;this.startCol=this.lastCol=_3;var _4=this.cellIsSelected(_2,_3),_5=this.getSelectedCells(),_6=this.getSelectionBounds();this.deselectCellOnMouseUp=false;this.deselectOthersOnMouseUp=false;if(_1.selectionType==isc.Selection.SINGLE){this.selectSingleCell(_2,_3);return true}else if(isc.EventHandler.shiftKeyDown()){if(_5.length==0){this.selectCell(_2,_3);return true}else{var _7=_6[0],_8=_6[1],_9=_6[2],_10=_6[3];if(_2<_7)
_7=_2;else if(_2>=_9)
_9=_2;else{this.deselectCellRange(_2+1,_8,_9,_10);_9=_2}
if(_3<_8)
_8=_3;else if(_3>=_10)
_10=_3;else{this.deselectCellRange(_7,_3+1,_9,_10);_10=_3}
this.selectCellRange(_7,_8,_9,_10);return true}}else if(_1.selectionType==isc.Selection.SIMPLE){if(!_4){this.selectCell(_2,_3);return true}else{this.deselectCellOnMouseUp=true;return false}}else if(isc.Browser.isMac?isc.EventHandler.metaKeyDown():isc.EventHandler.ctrlKeyDown()){this.setCellSelection(_2,_3,!_4);return true}else{if(!_4){this.selectSingleCell(_2,_3);return true}else if(isc.EventHandler.rightButtonDown()){this.deselectOnDragMove=true;return false}else{if(this.dragSelection){if(this.simpleDeselect){this.deselectAll();this.selectOriginOnDragMove=true;return true}
this.selectSingleCell(_2,_3);return true}else{if(this.simpleDeselect){this.deselectAllOnMouseUp=true}else{this.deselectOthersOnMouseUp=(_5.length>1)}
return false}}}}
,isc.A.selectOnDragMove=function isc_CellSelection_selectOnDragMove(_1,_2,_3){var _4=this.startRow,_5=this.startCol,_6=this.lastRow,_7=this.lastCol;if(_2<0||_3<0){this.logWarn("selectOnDragMove: aborting due to negative coordinate: "+[_2,_3]);return}
if(_2==_6&&_3==_7)return;if(_1.selectionType==isc.Selection.SINGLE){this.selectSingleCell(_2,_3);return}
var _8=[];if(this.selectOriginOnDragMove){this.$24g(_4,_5);_8.add([_4,_5]);this.selectOriginOnDragMove=false}else if(this.deselectOnDragMove||this.deselectAllOnMouseUp||this.deselectOthersOnMouseUp)
{this.selectSingleCell(_4,_5);this.deselectAllOnMouseUp=this.deselectOthersOnMouseUp=this.deselectOnDragMove=false}
if((_2!=_6&&((_6>=_4&&_4>=_2)||(_2>=_4&&_4>=_6)))||(_3!=_7&&((_7>=_5&&_5>=_3)||(_3>=_5&&_5>=_7))))
{this.$24g(_4,_5,false);_8.addList(this.$24h(_4,_5,_6,_7,false));this.$24g(_4,_5,true);_8.addList(this.$24h(_4,_5,_2,_3,true));this.changedCells=_8;this.$24i();this.lastRow=_2;this.lastCol=_3;return}
if(_2>=0&&_2!=_6){if(_4>=_6&&_6>_2){_8.addList(this.$24h(_2,_5,_6-1,_7,true))}else if(_4>=_2&&_2>_6){_8.addList(this.$24h(_6,_5,_2-1,_7,false))}else if(_4<=_2&&_2<_6){_8.addList(this.$24h(_2+1,_5,_6,_7,false))}else if(_4<=_6&&_6<_2){_8.addList(this.$24h(_6+1,_5,_2,_7,true))}
_6=this.lastRow=_2}
if(_3>=0&&_3!=_7){if(_5>=_7&&_7>_3){_8.addList(this.$24h(_4,_3,_6,_7-1,true))}else if(_5>=_3&&_3>_7){_8.addList(this.$24h(_4,_7,_6,_3-1,false))}else if(_5<=_3&&_3<_7){_8.addList(this.$24h(_4,_3+1,_6,_7,false))}else if(_5<=_7&&_7<_3){_8.addList(this.$24h(_4,_7+1,_6,_3,true))}
this.lastCol=_3}
this.changedCells=_8;this.$24i()}
,isc.A.selectOnMouseUp=function isc_CellSelection_selectOnMouseUp(_1,_2,_3){if(_1.selectionType==isc.Selection.NONE)return false;if(this.deselectOthersOnMouseUp){this.selectSingleCell(_2,_3);this.deselectOthersOnMouseUp=false;return true}else if(this.deselectRecordOnMouseUp){this.deselectCell(_2,_3);this.deselectRecordOnMouseUp=false;return true}else if(this.deselectAllOnMouseUp){this.deselectAll();this.deselectAllOnMouseUp=false}else
return false}
);isc.B._maxIndex=isc.C+37;if(isc.Window){isc.ClassFactory.defineClass("FieldEditor","Window");isc.A=isc.FieldEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.isModal=true;isc.A.showMinimizeButton=false;isc.A.autoCenter=true;isc.A.autoSize=true;isc.A.defaultWidth=475;isc.A.visibleFieldsConstructor="ListGrid";isc.A.hiddenFieldsConstructor="ListGrid";isc.A.showFooter=false;isc.A.title="Customize Fields";isc.A.showInstructionsPane=true;isc.A.bodyProperties={layoutMargin:5};isc.A.instructionsPaneDefaults={_constructor:isc.HTMLFlow,padding:5,height:1};isc.A.instructions="Drag fields between grids to control which fields are visible "+"and the order in which fields are displayed";isc.B.push(isc.A.initWidget=function isc_FieldEditor_initWidget(){this.invokeSuper(isc.FieldEditor,"initWidget");if(!this.fields){isc.logWarn('FieldEditor can not be created because no fields were provided');return}
this.addItem(this.addAutoChild("instructionsPane",{contents:this.instructions}));this.visibleFieldsDefaults=this.hiddenFieldsDefaults={height:200,width:200,leaveScrollbarGap:false,canDragRecordsOut:true,canAcceptDroppedRecords:true,canReorderRecords:true,dragDataAction:"move"};var _1=this.visibleFieldsGrid=this.createAutoChild("visibleFields",{fields:[{name:"title",title:"Visible Fields",formatCellValue:"value || record.name"}]});var _2=this.fields;var _3=_2.findAll("visible",null);var _4=_2.findAll("visible",false);_1.setData(_3);var _5=this.hiddenFieldsGrid=this.createAutoChild("hiddenFields",{canReorderRecords:false,fields:[{name:"title",title:"Hidden Fields",formatCellValue:"value || record.name"}]});_5.setData(_4);var _6=isc.HLayout.create({membersMargin:10,layoutMargin:5,height:1,overflow:"visible",members:[_1,isc.VStack.create({width:32,height:74,layoutAlign:"center",membersMargin:10,members:[isc.Img.create({src:"[SKINIMG]actions/back.png",width:16,height:16,visFieldsGrid:_1,hidFieldsGrid:_5,layoutAlign:"center",click:"this.visFieldsGrid.transferSelectedData(this.hidFieldsGrid)"}),isc.Img.create({src:"[SKINIMG]actions/forward.png",width:16,height:16,layoutAlign:"center",visFieldsGrid:_1,hidFieldsGrid:_5,click:"this.hidFieldsGrid.transferSelectedData(this.visFieldsGrid)"})]}),_5]});this.addItem(_6);var _7=this.createAutoChild("okButton",{autoDraw:false,title:"Done",fieldEditor:this,click:function(){this.creator.okClick()},layoutAlign:"center"},isc.IButton);this.addItem(_7)}
,isc.A.okClick=function isc_FieldEditor_okClick(){var _1=isc.clone(this.visibleFieldsGrid.data);var _2=isc.clone(this.hiddenFieldsGrid.data);_1.setProperty("visible",null);_2.setProperty("visible",false);_1.addList(_2);var _3=_1.getProperties(["name","visible"]);this.done(_1,_3);this.hide();this.destroy()}
,isc.A.done=function isc_FieldEditor_done(_1,_2){}
);isc.B._maxIndex=isc.C+3}
isc.ClassFactory.defineClass("FormulaBuilder","VLayout");isc.A=isc.FormulaBuilder.getPrototype();isc.A.vertical=true;isc.A.padding=10;isc.A.showFormulaField=true;isc.A.formulaFieldDefaults={type:"text",formItemType:"AutoFitTextAreaItem",height:20,width:"*",hoverWidth:300,keyPress:function(){if(this.form.creator.autoTest){this.fireOnPause("autoTest",{target:this.form.creator,methodName:"testFunction"},this.form.creator.autoTestDelay)}}};isc.A.showTitleField=true;isc.A.titleFieldDefaults={type:"text",width:"*"};isc.A.showHelpIcon=true;isc.A.helpIconDefaults={src:"[SKIN]actions/help.png"};isc.A.autoHideCheckBoxLabel="Auto hide fields used in formula";isc.A.showAutoHideCheckBox=true;isc.A.autoHideCheckBoxDefaults={type:"boolean"};isc.A.builderTypeText="Formula";isc.A.helpTextIntro="For basic arithmetic, type in symbols (+-/%) directly.<P>The following functions are also available:";isc.A.fieldKeyDefaults={_constructor:"ListGrid",leaveScrollbarGap:false,showResizeBar:true,autoFitData:"vertical",autoFitMaxRecords:6,autoFetchData:true,showRollOver:false,selectionType:"none",defaultFields:[{name:"mappingKey",width:40},{name:"title"},{name:"sourceDS",showIf:"list.creator.dataSources != null"},{name:"name",showIf:"false"},{name:"type",showIf:"false"},{name:"length",showIf:"false"}],recordClick:function(_1,_2){var _3=this.creator.formulaField;if(_3){var _4=_3.getEnteredValue()||"";var _5=_3.getSelectionRange();if(_5!=null){_4=_4.substring(0,_5[0])+_2.mappingKey+_4.substring(_5[1])}else{_4+=_2.mappingKey}
_3.setValue(_4);_3.focusInItem();if(this.creator.autoTest){this.fireOnPause("autoTest",{target:this.creator,methodName:"testFunction"},this.creator.autoTestDelay)}}}};isc.A.instructionsTextStart="The following fields are available for use in this \${builderType}";isc.A.instructionsDefaults={_constructor:"Label",height:1,extraSpace:10,overflow:"visible"};isc.A.titleFormDefaults={_constructor:"DynamicForm",extraSpace:5};isc.A.formulaFormDefaults={_constructor:"DynamicForm",extraSpace:5};isc.A.hideFieldsFormDefaults={_constructor:"DynamicForm",extraSpace:20};isc.A.sampleHeaderDefaults={_constructor:"Label",height:15,extraSpace:5};isc.A.sampleLabelDefaults={_constructor:"Canvas",height:40,width:"100%",align:"center",valign:"top",extraSpace:10,showHover:true,overflow:"hidden",styleName:"sampleOutput"};isc.A.messageLabelDefaults={_constructor:"Label",height:20,width:"100%",align:"right",valign:"center",overflow:"hidden",showHover:true};isc.A.buttonLayoutDefaults={_constructor:"HLayout",width:"100%",align:"right"};isc.A.cancelButtonDefaults={_constructor:"IButton",autoParent:"buttonLayout",autoFit:true,extraSpace:10,click:function(){this.creator.completeEditing(true)}};isc.A.testButtonDefaults={_constructor:"IButton",autoParent:"buttonLayout",autoFit:true,extraSpace:10,click:function(){this.creator.testFunction()}};isc.A.saveAddAnotherButtonDefaults={_constructor:"IButton",autoParent:"buttonLayout",autoFit:true,extraSpace:10,click:function(){if(!this.creator.showTitleForm||this.creator.titleForm.validate())this.creator.saveAddAnother()}};isc.A.saveButtonDefaults={_constructor:"IButton",autoParent:"buttonLayout",autoFit:true,click:function(){if(!this.creator.showTitleForm||this.creator.titleForm.validate())this.creator.save()}};isc.A.fieldType="float";isc.A.allowEscapedKeys=false;isc.A.invalidBuilderPrompt="Invalid \${builderType}: \${errorText}";isc.A.defaultErrorText="[No Explicit Error]";isc.A.invalidBlankPrompt="Invalid blank \${builderType}";isc.A.validBuilderPrompt="Valid \${builderType}";isc.A.helpWindowTitle="\${builderType} Help";isc.A.titleFieldTitle="Title";isc.A.defaultNewFieldTitle="New Field";isc.A.keyColumnTitle="Key";isc.A.sourceFieldColumnTitle="Source Field";isc.A.sourceDSColumnTitle="Source DataSource";isc.A.cancelButtonTitle="Cancel";isc.A.saveAddAnotherButtonTitle="Save & Add Another";isc.A.saveButtonTitle="Save";isc.A.saveConfirmationPrompt="Save changes to this \${builderType}?";isc.A.invalidGeneratedFunctionPrompt="The generated function is invalid - Check your \${builderType} and retry.";isc.A.sampleHeaderTitle="Sample:";isc.A.testButtonTitle="Test";isc.A=isc.FormulaBuilder.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.warnDuplicateTitlesMessage="Another field already has the title '${fieldTitle}'.  Continue anyway?";isc.A.autoTest=true;isc.A.autoTestDelay=200;isc.A.samplePrompt="<nobr>For Record: ${title}</nobr><br><nobr>Output: ${output}</nobr>";isc.B.push(isc.A.getValue=function isc_FormulaBuilder_getValue(){return this.formulaField?this.formulaField.getValue():null}
,isc.A.setValue=function isc_FormulaBuilder_setValue(_1){if(this.formulaField){this.formulaField.setValue(_1)}}
,isc.A.setFormula=function isc_FormulaBuilder_setFormula(_1){this.setValue(_1)}
,isc.A.getFieldIdProperty=function isc_FormulaBuilder_getFieldIdProperty(){return this.getClass().getFieldIdProperty(this.component)}
,isc.A.getTitle=function isc_FormulaBuilder_getTitle(){return this.titleField?this.titleField.getValue():null}
,isc.A.setTitle=function isc_FormulaBuilder_setTitle(_1){if(this.titleField){this.titleField.setValue(_1)}}
,isc.A.getFieldFromMappingKey=function isc_FormulaBuilder_getFieldFromMappingKey(_1){var _2=this.getAvailableFields();for(var i=0;i<_2.length;i++){var _4=_2.get(i);if(_4.mappingKey==_1)return _4}
return null}
,isc.A.getFields=function isc_FormulaBuilder_getFields(){if(this.fields)return this.fields;if(this.component)return this.component.getAllFields();var _1;if(this.dataSources){_1=[];for(var i=0;i<this.dataSources.length;i++){var _3=this.dataSources[i],_4=_3.getFields();for(var _5 in _4){var _6=isc.addProperties({},_4[_5],{name:_3.getID()+"."+_5,sourceDS:_3.getID()});_1.add(_6)}}}else{_1=isc.getValues(this.dataSource.getFields())}
return _1}
,isc.A.shouldHideUsedFields=function isc_FormulaBuilder_shouldHideUsedFields(){if(this.showAutoHideCheckBox&&this.autoHideCheckBox&&this.autoHideCheckBox.getValue()){return this.autoHideCheckBox.getValue()}else return false}
,isc.A.getHelpText=function isc_FormulaBuilder_getHelpText(){return this.getHoverText()}
,isc.A.initWidget=function isc_FormulaBuilder_initWidget(){this.Super("initWidget",arguments);if(this.dataSource)this.dataSource=isc.DataSource.get(this.dataSource);if(this.dataSources){var _1=[];for(var i=0;i<this.dataSources.length;i++){_1[i]=isc.DataSource.get(this.dataSources[i])}}
var _3=this.getAvailableFields();if(!this.field){this.field={name:this.getUniqueFieldName(),title:this.defaultNewFieldTitle,type:this.fieldType,width:"50",canFilter:false,canSortClientOnly:true,originalOrder:this.availableFields.length}}
this.instructions=this.createAutoChild("instructions",{contents:this.instructionsTextStart.evalDynamicString(this,{builderType:this.builderTypeText})});this.addMember(this.instructions);this.fieldKeyDS=isc.DataSource.create({ID:this.getID()+"DS",clientOnly:true,testData:_3,fields:[{name:"mappingKey",title:this.keyColumnTitle,width:40},{name:"title",title:this.sourceFieldColumnTitle},{name:"sourceDS",title:this.sourceDSColumnTitle,showIf:"false"},{name:"name",showIf:"false",primaryKey:true},{name:"type",showIf:"false"},{name:"length",showIf:"false"}]});this.fieldKey=this.createAutoChild("fieldKey",{dataSource:this.fieldKeyDS});if(this.fieldKey.showFilterEditor!==false&&this.fieldKey.autoFitMaxRecords&&_3.length>this.fieldKey.autoFitMaxRecords)
{this.fieldKey.setShowFilterEditor(true)}
this.addMember(this.fieldKey);if(this.showTitleField){this.addAutoChild("titleForm",{fields:[isc.addProperties(this.titleFieldDefaults,this.titleFieldProperties,{title:this.titleFieldTitle,name:"titleField"})]});this.titleField=this.titleForm.getField("titleField");this.setTitle(this.field.title||isc.DataSource.getAutoTitle(this.field.name))}
if(this.showFormulaField){this.addAutoChild("formulaForm",{fields:[isc.addProperties({title:this.builderTypeText},this.formulaFieldDefaults,this.formulaFieldProperties,this.showHelpIcon?{icons:[isc.addProperties({prompt:this.getHelpText()},this.helpIconDefaults,this.helpIconProperties,{click:"form.creator.showHelpWindow();"})]}:{},{name:"formulaField"})]});this.formulaField=this.formulaForm.getField("formulaField");if(this.showHelpIcon)this.helpIcon=this.formulaField.icons[0]}
this.addAutoChild("messageLabel");this.addAutoChild("sampleHeader",{contents:this.sampleHeaderTitle});this.addAutoChild("sampleLabel");if(this.showAutoHideCheckBox){this.addAutoChild("hideFieldsForm",{fields:[isc.addProperties({title:this.autoHideCheckBoxLabel},this.autoHideCheckBoxDefaults,this.autoHideCheckBoxProperties,{name:"autoHide"})]});this.autoHideCheckBox=this.hideFieldsForm.getField("autoHide")}
this.addAutoChild("buttonLayout");this.addAutoChild("cancelButton",{title:this.cancelButtonTitle});if(!this.autoTest)this.addAutoChild("testButton",{title:this.testButtonTitle});this.addAutoChild("saveAddAnotherButton",{title:this.saveAddAnotherButtonTitle});this.addAutoChild("saveButton",{title:this.saveButtonTitle});if(this.showTitleField)this.titleForm.focusInItem(this.titleField);else this.formulaForm.focusInItem(this.formulaField);this.setInitialValue();if(this.editMode&&this.autoTest)this.testFunction()}
,isc.A.getUniqueFieldName=function isc_FormulaBuilder_getUniqueFieldName(){return this.getNewUniqueFieldName("formulaField")}
,isc.A.getNewUniqueFieldName=function isc_FormulaBuilder_getNewUniqueFieldName(_1){if(!_1||_1=="")_1="field";var _2=this.getFields(),_3=1,_4=_1.length;for(var i=0;i<_2.length;i++){var _6=_2.get(i);if(_6.name.startsWith(_1)){var _7=_6.name.substr(_4),_8=new Number(_7);if(_8&&_8>=_3)_3=_8+1}}
return _1+_3}
,isc.A.destroy=function isc_FormulaBuilder_destroy(){if(this.fieldKeyDS)this.fieldKeyDS.destroy();this.Super("destroy",arguments)}
,isc.A.setInitialValue=function isc_FormulaBuilder_setInitialValue(){if(this.editMode&&this.field.userFormula){this.initialValue=this.field.userFormula.text;if(this.field.userFormula.allowEscapedKeys)
this.allowEscapedKeys=this.field.userFormula.allowEscapedKeys}
this.initialValue=this.initialValue||"";this.setValue(this.initialValue)}
,isc.A.showHelpWindow=function isc_FormulaBuilder_showHelpWindow(){var _1=this.locatorParent,_2=_1?_1.getTop():this.top,_3=_1?_1.getLeft():this.left,_4=_1?_1.getWidth():this.width,_5=_1?_1.getVisibleHeight():this.getVisibleHeight();if(_1)_1.centerInPage();if(this.helpWindow&&this.helpWindow!=null){this.hideHelpWindow()}else{this.helpIcon.prompt=null;this.formulaField.stopHover();_3-=(_4/ 2);if(_1)_1.setLeft(_3);this.helpWindow=isc.Window.create({autoDraw:true,title:this.helpWindowTitle.evalDynamicString(this,{builderType:this.builderTypeText}),showMinimizeButton:false,showMaximizeButton:false,showCloseButton:false,isModal:false,headerIconProperties:{src:"[SKIN]actions/help.png"},items:[isc.Label.create({contents:this.getHelpText(),padding:10})]});this.helpWindow.moveTo(_3+_4,_2);this.helpWindow.resizeTo(_4,_5)}}
,isc.A.hideHelpWindow=function isc_FormulaBuilder_hideHelpWindow(){if(this.helpWindow){this.helpWindow.destroy();this.helpWindow=null}
this.helpIcon.prompt=this.getHelpText();this.formulaField.stopHover()}
,isc.A.getHoverText=function isc_FormulaBuilder_getHoverText(){var _1=isc.SB.create();_1.append("<b>",this.helpTextIntro,"</b> <P>");_1.append("<ul>");var _2=isc.MathFunction.getRegisteredFunctionIndex(),_3=this.mathFunctions;if(_3&&_3.length>0){for(var i=0;i<_3.length;i++){var _5=_2[_3[i]];_1.append("<li> <b>",_5.name,": </b> ",_5.description,"<p>");_1.append("<i>usage: ",_5.usage,"</i> </li>")}}
_1.append("</ul>");return _1.toString()}
,isc.A.getAvailableFields=function isc_FormulaBuilder_getAvailableFields(){if(!this.availableFields){this.availableFields=this.getClass().getAvailableFields(this.getFields(),this.field)}
return this.availableFields}
,isc.A.getUsedFields=function isc_FormulaBuilder_getUsedFields(){return this.getClass().getUsedFields(this.getValue(),this.getAvailableFields(),this.field)}
,isc.A.getCompleteValueObject=function isc_FormulaBuilder_getCompleteValueObject(){var _1=this.getUsedFields(),_2=this.generateFunction(),_3={sortNormalizer:_2,$65w:_2,type:this.fieldType,userFormula:{text:this.getValue(),formulaVars:{}}},_4=this.getFieldIdProperty();if(this.allowEscapedKeys)_3.userFormula.allowEscapedKeys=true;for(var i=0;i<_1.length;i++){var _6=_1.get(i);_3.userFormula.formulaVars[_6.mappingKey]=_6[_4]}
return _3}
,isc.A.getBasicValueObject=function isc_FormulaBuilder_getBasicValueObject(){var _1=this.getUsedFields(),_2={text:this.getValue(),formulaVars:{}},_3=this.getFieldIdProperty();if(this.allowEscapedKeys)_2.allowEscapedKeys=true;for(var i=0;i<_1.length;i++){var _5=_1.get(i);_2.formulaVars[_5.mappingKey]=_5[_3]}
return _2}
,isc.A.getUpdatedFieldObject=function isc_FormulaBuilder_getUpdatedFieldObject(){return isc.addProperties(this.field,{title:this.getTitle()},this.getCompleteValueObject())}
,isc.A.testFunction=function isc_FormulaBuilder_testFunction(){var _1=this.getClass().testFunction(this.field,this.getBasicValueObject(),this.component,this.getFields());var _2="",_3=_1.errorText||this.defaultErrorText;if(_1.failedGeneration||_1.failedExecution){_2=this.invalidBuilderPrompt.evalDynamicString(this,{builderType:this.builderTypeText,errorText:_3})}else if(_1.emptyTestValue){_2=this.invalidBlankPrompt.evalDynamicString(this,{builderType:this.builderTypeText})}else{_2=this.validBuilderPrompt.evalDynamicString(this,{builderType:this.builderTypeText})}
this.setTestMessage(_2);this.setSamplePrompt(this.getSamplePrompt(_1));return _1}
,isc.A.getTestRecord=function isc_FormulaBuilder_getTestRecord(){if(this.testRecord)return this.testRecord;return this.getClass().getTestRecord(this.component,this.getAvailableFields())}
,isc.A.setTestMessage=function isc_FormulaBuilder_setTestMessage(_1){this.messageLabel.setContents(_1)}
,isc.A.setSamplePrompt=function isc_FormulaBuilder_setSamplePrompt(_1){this.sampleLabel.setContents("<center>"+_1+"</center>")}
,isc.A.generateFunction=function isc_FormulaBuilder_generateFunction(){return this.getClass().generateFunction(this.getBasicValueObject(),this.getUsedFields(),this.component)}
,isc.A.saveAddAnother=function isc_FormulaBuilder_saveAddAnother(){this.restartBuilder=true;this.save()}
,isc.A.fieldTitleIsUnique=function isc_FormulaBuilder_fieldTitleIsUnique(_1){var _2=this.component?this.component.getAllFields():null,_3=_2?_2.findAll({"title":_1}):null,_4=true;if(_3&&_3.length>0){for(var i=0;i<_3.length;i++){if(_3[i].name!=this.field.name){_4=false;break}}}
return _4}
,isc.A.save=function isc_FormulaBuilder_save(){var _1=this.testFunction();if(this.warnDuplicateTitles&&!this.duplicateTitleAccepted){var _2=this.getTitle();if(!this.fieldTitleIsUnique(_2)){var _3=this.warnDuplicateTitlesMessage.evalDynamicString(this,{fieldTitle:_2})
var _4=this;isc.confirm(_3,function(_5){if(_5){_4.duplicateTitleAccepted=true;_4.delayCall("save")}else{_4.restartBuilder=false}});return null}}
delete this.duplicateTitleAccepted;if(_1.emptyTestValue){isc.warn(this.invalidBlankPrompt.evalDynamicString(this,{builderType:this.builderTypeText}));return}else if(_1.failedGeneration||_1.failedExecution){isc.warn(this.invalidGeneratedFunctionPrompt.evalDynamicString(this,{builderType:this.builderTypeText}));return}
this.completeEditing(false)}
,isc.A.completeEditing=function isc_FormulaBuilder_completeEditing(_1,_2){this.cancelled=_1;if(_1){if(this.editMode&&!_2){if(this.getValue()!=this.initialValue){var _3=this;var _4=this.saveConfirmationPrompt.evalDynamicForm(this,{builderType:this.builderTypeText});isc.confirm(_4,function(_5){if(_5){_3.save()}else{_3.completeEditing(true,true)}});return}}}
if(this.helpWindow)this.hideHelpWindow();if(this.availableFields){this.availableFields=this.availableFields.sortByProperty("originalOrder",true);this.availableFields.clearProperty("originalOrder")}
this.fireOnClose()}
,isc.A.fireOnClose=function isc_FormulaBuilder_fireOnClose(){}
,isc.A.getSamplePrompt=function isc_FormulaBuilder_getSamplePrompt(_1){var _2=this.dataSource?this.dataSource.getTitleField():isc.firstKey(_1.record),_3=_1.result!=null?_1.result:this.invalidBuilderPrompt.evalDynamicString(this,{builderType:this.builderTypeText,errorText:_1.errorText||this.defaultErrorText}),_4=_1.record[_2];return this.samplePrompt.evalDynamicString(this,{title:_4,output:_3})}
);isc.B._maxIndex=isc.C+34;isc.A=isc.FormulaBuilder;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.mappingKeyForIndex=function isc_c_FormulaBuilder_mappingKeyForIndex(_1){var _2="",_3=Math.floor(_1/(26*27)),_4=_1%(26*26),_5=Math.floor(_4/ 26);if(_3>=1)_2+=String.fromCharCode(65+(_3-1));if(_5>=1){if(_3>=1){_2+=String.fromCharCode(65+(_5-1));_2+=String.fromCharCode(65+(_1-(26*27))%26)}else{_2+=String.fromCharCode(65+(_5-1));_2+=String.fromCharCode(65+_1%26)}}else{if(_3>=1){_2+=String.fromCharCode(65)}
_2+=String.fromCharCode(65+_1%26)}
return _2}
,isc.A.getFieldIdProperty=function isc_c_FormulaBuilder_getFieldIdProperty(_1){return _1?_1.fieldIdProperty:"name"}
,isc.A.getAvailableFields=function isc_c_FormulaBuilder_getAvailableFields(_1,_2){var _3=[],j=0;if(!_1)return _3;for(var i=0;i<_1.getLength();i++){var _6=_1.get(i),_7=_6.type;_6.originalOrder=i;if(_2&&_2.name==_6.name)continue;if(_6.userFormula||isc.SimpleType.inheritsFrom(_7,"integer")||isc.SimpleType.inheritsFrom(_7,"float"))
{_6.mappingKey=isc.FormulaBuilder.mappingKeyForIndex(j++);if(!_6.title)_6.title=isc.DataSource.getAutoTitle(_6.name);_3.add(_6)}}
var _8=_2&&_2.userFormula?_2.userFormula.formulaVars:{};for(var _9 in _8){var _10=_3.find("mappingKey",_9),_11=_3.find("name",_8[_9]),_12=_11.mappingKey;_11.mappingKey=_10.mappingKey;_10.mappingKey=_12}
_3=_3.sortByProperties(["mappingKey"],[true],[function(_6,_14,_15){var _13=_6[_14];if(_13.length==1)_13='  '+_13;else if(_13.length==2)_13=' '+_13;return _13}]);return _3}
,isc.A.getUsedFields=function isc_c_FormulaBuilder_getUsedFields(_1,_2,_3){var _4=this.getAvailableFields(_2,_3),_5=[];if(!_4||!_1)return _5;_4=_4.sortByProperties(["mappingKey"],[false],[function(_8,_9,_10){var _6=_8[_9];if(_6.length==1)_6='  '+_6;else if(_6.length==2)_6=' '+_6;return _6}]);for(var i=0;i<_4.length;i++){var _8=_4.get(i);if(this.allowEscapedKeys){if(_1.indexOf("#"+_8.mappingKey)>=0||_1.indexOf("#{"+_8.mappingKey+"}")>=0)
{_5.add(_8)}}else if(_1.indexOf(_8.mappingKey)>=0){_5.add(_8)}}
return _5}
,isc.A.getFieldDetailsFromValue=function isc_c_FormulaBuilder_getFieldDetailsFromValue(_1,_2,_3){var _4=_1,_5=this.getFieldIdProperty(_3),_6={usedFields:[],missingFields:[]};for(var _7 in _4){var _8=_4[_7],_9=_2.findIndex(_5,_8);if(!_2[_9]){isc.logWarn("Field "+_8+" is not in the list of available-fields");_6.missingFields.add(_8)}else{var _10=isc.addProperties({},_2[_9]);_10.mappingKey=_7;_6.usedFields.add(_10)}}
return _6}
,isc.A.testFunction=function isc_c_FormulaBuilder_testFunction(_1,_2,_3,_4){var _5={};try{_5.component=_3;_5.record=this.getTestRecord(_3,_4);if(_2.text==""){_5.emptyTestValue=true;return _5}
_5.jsFunction=this.generateFunction(_2,_4,_3);_5.result=_5.jsFunction(_5.record,_3)}catch(err){if(!_5.jsFunction)_5.failedGeneration=true;_5.failedExecution=true;_5.errorText=err.message}
return _5}
,isc.A.getTestRecord=function isc_c_FormulaBuilder_getTestRecord(_1,_2){var _3=this.getFieldIdProperty(_1),_4;if(_1){_4=_1.getSelectedRecord();if(!_4){if(_1.body){var _5=_1.body.getVisibleRows();_4=_5?_1.getRecord(_5[0]):_1.data.get(0)}else{_4=_1.data.get(0)}}}
if(!_4&&_2){_4={};for(var i=0;i<_2.length;i++){var _7=_2.get(i);if(_7.userFormula){_7.$65w=_7.sortNormalizer=isc.FormulaBuilder.generateFunction(_7.userFormula,_2,_1)}
if(_7.$65w){isc.DataSource.setPathValue(_4,_7[_3],_7.$65w(_4,_1))}else if(_7.type)
if(isc.SimpleType.inheritsFrom(_7.type,"integer")||isc.SimpleType.inheritsFrom(_7.type,"float"))
{isc.DataSource.setPathValue(_4,_7[_3],1)}else{isc.DataSource.setPathValue(_4,_7[_3],_7[_3])}
else{isc.DataSource.setPathValue(_4,_7[_3],_7[_3])}}}
return _4}
,isc.A.generateFunction=function isc_c_FormulaBuilder_generateFunction(_1,_2,_3,_4){var _5=isc.SB.create(),_6=_1.text,_7=this.getFieldIdProperty(_3),_8=this.getFieldDetailsFromValue(_1.formulaVars,_2,_3),_9=_8.usedFields,_10=_8.missingFields;_9=_9.sortByProperties(["mappingKey"],[false],[function(_13,_18,_19){var _11=_13[_18];if(_11.length==1)_11='  '+_11;else if(_11.length==2)_11=' '+_11;return _11}]);if(_10.length==0){if(_9.length>0){for(var i=0;i<_9.length;i++){var _13=_9.get(i);var _14=_13[_7];_5.append("var ");_5.append(_13.mappingKey,"=isc.DataSource.getPathValue(record,'",_14,"');","\nif(",_13.mappingKey,"==null)",_13.mappingKey,"= (component ? component.getStandaloneFieldValue(record, '",_14,"', true) : "+(_4?"null)":"0)"));_5.append(";\n");if(_1.allowEscapedKeys){_6=_6.replaceAll("#"+_13.mappingKey,_13.mappingKey);_6=_6.replaceAll("#{"+_13.mappingKey+"}",_13.mappingKey)}}
_5.append("\n")}
var _15=isc.MathFunction.getRegisteredFunctions();if(_15&&_15.length>0){_5.append("var functions=isc.MathFunction.getRegisteredFunctionIndex(),\n");for(var i=0;i<_15.length;i++){var _13=_15.get(i);_5.append("        ");_5.append(_13.name,"=","functions.",_13.name,".jsFunction");_5.append(i==_15.length-1?";":",","\n")}
_5.append("\n")}
_5.append("var value=",_6,";",(_4?null:"if (isNaN(value)) return (component && component.badFormulaResultValue) || '.'; "),"return value;")}else{this.logWarn("Formula failed due to missing fields: "+_10.join(", ")+".");var _11=(_3&&_3.badFormulaResultValue)||".";if(_11)_11="'"+_11+"'";_5.append("return ",_11,";")}
var _16=_5.toString();var _17=new Function("record,component",_16);return _17}
);isc.B._maxIndex=isc.C+8;isc.ClassFactory.defineClass("SummaryBuilder","FormulaBuilder");isc.A=isc.SummaryBuilder.getPrototype();isc.A.builderTypeText="Summary";isc.A.fieldType="text";isc.A.autoHideCheckBoxLabel="Auto hide fields used in Summary";isc.A.helpTextIntro="Building Summary Columns";isc.A.allowBasicMultiCharKeys=false;isc.A=isc.SummaryBuilder.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.setSummary=function isc_SummaryBuilder_setSummary(_1){this.setValue(_1)}
,isc.A.setInitialValue=function isc_SummaryBuilder_setInitialValue(){if(this.editMode&&this.field.userSummary){this.initialValue=this.field.userSummary.text}
this.initialValue=this.initialValue||"";this.setValue(this.initialValue)}
,isc.A.getUniqueFieldName=function isc_SummaryBuilder_getUniqueFieldName(){return this.getNewUniqueFieldName("summaryField")}
,isc.A.getHoverText=function isc_SummaryBuilder_getHoverText(){var _1=isc.SB.create(),_2=this.getTestRecord(),_3=this.getFieldIdProperty(),_4=this.getFieldFromMappingKey("A"),_5=_4[_3],_6=_4?_4.title||_4.name:null,_7=this.getFieldFromMappingKey("B"),_8=_7?_7[_3]:null,_9=_7?_7.title||_7.name:null;_1.append("<b>",this.helpTextIntro,"</b> <P>");_1.append("Summary columns are user-created fields that combine dynamic-values "+"from other fields in the current record with static text specified by the user.<P>");_1.append("Dynamic-values are specified by prefixing a mapping-key from the table "+"opposite with #");if(this.getFields().length>26)_1.append(", or by using #{key} when the key "+"is 2 or more characters long,");_1.append(" and everything else is copied directly into the output.<P>");if(this.dataSource){_1.append("For example, in the current DataSource, key <b>A</b> maps to field <i>",_6,"</i> and <b>B</b> is <i>",!_7?"missing":_9,"</i>.<P>");_1.append("So, if we enter the Summary format-string as:<P>","<i>#A is relative to #B</i><P>","then example output using the current data would look like:<P>");if(_2){_1.append("<i>",_2[_5]," is relative to ",!_7?"{missing}":_2[_8],"</i><P>")}}
return _1.toString()}
,isc.A.getAvailableFields=function isc_SummaryBuilder_getAvailableFields(){if(!this.availableFields){this.availableFields=this.getClass().getAvailableFields(this.getFields(),this.field)}
return this.availableFields}
,isc.A.getUsedFields=function isc_SummaryBuilder_getUsedFields(){return this.getClass().getUsedFields(this.getValue(),this.getAvailableFields(),this.allowBasicMultiCharKeys,this.field)}
,isc.A.getCompleteValueObject=function isc_SummaryBuilder_getCompleteValueObject(){var _1=this.getUsedFields(),_2=this.generateFunction(),_3=this.getFieldIdProperty(),_4={sortNormalizer:_2,$652:_2,type:this.fieldType,userSummary:{text:this.getValue()}};if(_1&&_1.length>0){_4.userSummary.summaryVars={};for(var i=0;i<_1.length;i++){var _6=_1.get(i);_4.userSummary.summaryVars[_6.mappingKey]=_6[_3]}}
return _4}
,isc.A.getBasicValueObject=function isc_SummaryBuilder_getBasicValueObject(){var _1=this.getUsedFields(),_2=this.getFieldIdProperty(),_3={text:this.getValue(),summaryVars:{}};for(var i=0;i<_1.length;i++){var _5=_1.get(i);_3.summaryVars[_5.mappingKey]=_5[_2]}
return _3}
,isc.A.generateFunction=function isc_SummaryBuilder_generateFunction(){return this.getClass().generateFunction(this.getBasicValueObject(),this.getUsedFields(),this.component)}
,isc.A.initWidget=function isc_SummaryBuilder_initWidget(){this.Super("initWidget",arguments)}
);isc.B._maxIndex=isc.C+10;isc.A=isc.SummaryBuilder;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.getAvailableFields=function isc_c_SummaryBuilder_getAvailableFields(_1,_2){var _3=[];if(!_1)return _3;for(var i=0,j=0;i<_1.getLength();i++){var _6=_1.get(i);if(_2&&_2.name==_6.name)continue;_6.originalOrder=i;_6.mappingKey=isc.FormulaBuilder.mappingKeyForIndex(j++);if(!_6.title)_6.title=isc.DataSource.getAutoTitle(_6.name);_3.add(_6)}
var _7=_2&&_2.userSummary?_2.userSummary.summaryVars:{};for(var _8 in _7){var _9=_3.find("mappingKey",_8),_10=_3.find("name",_7[_8]),_11=_10.mappingKey;_10.mappingKey=_9.mappingKey;_9.mappingKey=_11}
_3=_3.sortByProperties(["mappingKey"],[true],[function(_6,_13,_14){var _12=_6[_13];if(_12.length==1)_12='  '+_12;else if(_12.length==2)_12=' '+_12;return _12}]);return _3}
,isc.A.getUsedFields=function isc_c_SummaryBuilder_getUsedFields(_1,_2,_3,_4){var _5=this.getAvailableFields(_2,_4),_6=[];if(!_5||!_1)return _6;_5=_5.sortByProperties(["mappingKey"],[false],[function(_9,_10,_11){var _7=_9[_10];if(_7.length==1)_7='  '+_7;else if(_7.length==2)_7=' '+_7;return _7}]);for(var i=0;i<_5.length;i++){var _9=_5.get(i);if(_1.indexOf("#{"+_9.mappingKey+"}")>=0)
_6.add(_9);else if((_9.mappingKey.length==1||_3)&&_1.indexOf("#"+_9.mappingKey)>=0)_6.add(_9)}
return _6}
,isc.A.testFunction=function isc_c_SummaryBuilder_testFunction(_1,_2,_3,_4){var _5={},_6=this.getFieldIdProperty(_3);try{_5.component=_3;_5.record=this.getTestRecord(_3,_4);if(_2.text==""){_5.emptyTestValue=true;return _5}
_5.jsFunction=this.generateFunction(_2,_4,_3);_5.result=_5.jsFunction(_5.record,_1[_6],_3)}catch(err){if(!_5.jsFunction)_5.failedGeneration=true;_5.failedExecution=true;_5.errorText=err.message}
return _5}
,isc.A.generateFunction=function isc_c_SummaryBuilder_generateFunction(_1,_2,_3){var _4=isc.SB.create(),_5=_1.text,_6=this.getFieldIdProperty(_3),_7=this.getFieldDetailsFromValue(_1.summaryVars,_2,_3),_8=_7.usedFields,_9=_7.missingFields;_8=_8.sortByProperties(["mappingKey"],[false],[function(_12,_15,_16){var _10=_12[_15];if(_10.length==1)_10='  '+_10;else if(_10.length==2)_10=' '+_10;return _10}]);if(_8.length>0){_4.append("var ");for(var i=0;i<_8.length;i++){var _12=_8.get(i);if(i>0)_4.append("        ");_4.append(_12.mappingKey,"=(component ? component.getStandaloneFieldValue(record,'",_12[_6],"') : record['",_12[_6],"']");_4.append(i==_8.length-1?");":"),","\n");_5=_5.replaceAll("#{"+_12.mappingKey+"}","'+"+_12.mappingKey+"+'");_5=_5.replaceAll("#"+_12.mappingKey,"'+"+_12.mappingKey+"+'")}
_4.append("\n")}
_5=_5.replace(/(#({[A-Z][A-Z]?}|[A-Z][A-Z]?))/g,(_3&&_3.missingSummaryFieldValue)||"-");if(_5.substr(0,2)=="'+"){_5=_5.substr(2)}else if(_5.substr(0,1)!="'"){_5="'"+_5}
if(_5.substr(_5.length-2)=="+'"){_5=_5.substr(0,_5.length-2)}else if(_5.substr(_5.length-1)!="'"){_5=_5+"'"}
_4.append("return ",_5,";");var _13=_4.toString(),_14=new Function("record,fieldName,component",_13);return _14}
);isc.B._maxIndex=isc.C+4;isc.defineClass("HiliteRule","HLayout");isc.A=isc.HiliteRule.getPrototype();isc.A.height=1;isc.A.width="100%";isc.A.overflow="visible";isc.A.clauseConstructor="FilterClause";isc.A.clauseProperties={width:"100%",fieldPickerWidth:"*",clauseDefaults:{canEditField:function(){return true}},operatorPickerWidth:140,valueItemWidth:130,excludeNonFilterableFields:false};isc.A.hiliteFormDefaults={_constructor:"DynamicForm",numCols:3,colWidths:[90,40,80],width:210,items:[{name:"colorType",type:"SelectItem",showTitle:false,valign:"center",valueMap:{foreground:"Foreground",background:"Background"},defaultValue:"foreground",width:"*"},{name:"color",title:"Color",type:"ColorItem",width:"*"}]};isc.A.advancedClauseLayoutDefaults={_constructor:"HLayout",height:1,width:"100%"};isc.A.advancedClauseLabelDefaults={_constructor:"Label",autoParent:"advancedClauseLayout",width:"*",overflow:"hidden",height:18,valign:"center",wrap:false,padding:1};isc.A.advancedClauseEditButtonDefaults={_constructor:"ImgButton",autoParent:"advancedClauseLayout",width:18,height:18,layoutAlign:"center",src:"[SKINIMG]/actions/edit.png",showRollOver:false,showDown:false,showDisabled:false,click:function(){this.creator.editAdvancedRule()}};isc.A.showRemoveButton=true;isc.A.removeButtonPrompt="Remove";isc.A.removeButtonDefaults={_constructor:isc.ImgButton,width:18,height:18,layoutAlign:"center",src:"[SKIN]/actions/remove.png",showRollOver:false,showDown:false,showDisabled:false,hoverWidth:80,click:function(){this.creator.remove()}};isc.A=isc.HiliteRule.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.initWidget=function isc_HiliteRule_initWidget(){if(!this.isAdvanced&&this.hilite){var _1=this.hilite.criteria;if(_1&&_1.criteria&&isc.isAn.Array(_1.criteria))
this.isAdvanced=true}
if(isc.isA.String(this.dataSource))this.dataSource=isc.DS.getDataSource(this.dataSource);if(this.hilite)this.checkHiliteProperties(this.hilite);if(this.isAdvanced){var _2=isc.FilterBuilder.getFilterDescription(this.hilite.criteria,this.dataSource);var _3=(_2.indexOf(isc.FilterBuilder.missingFieldPrompt)>=0);this.membersMargin=2;this.addAutoChild("advancedClauseLayout");this.addAutoChild("removeButton",{disabled:_3?true:false,autoParent:"advancedClauseLayout"});this.addAutoChild("advancedClauseLabel",{contents:_2,prompt:_2,disabled:_3?true:false});this.addAutoChild("advancedClauseEditButton",{disabled:_3?true:false})}else{var _3=(this.dataSource.getField(this.fieldName)==null);this.addAutoChild("clause",{dataSource:this.dataSource,field:this.dataSource.getField(this.fieldName),fieldName:this.fieldName,criterion:this.hilite?this.hilite.criteria:null,showRemoveButton:this.showRemoveButton,disabled:_3?true:false,remove:function(){this.creator.remove()}});this.addMember(this.clause);this.addAutoChild("hiliteForm",{disabled:_3?true:false});if(this.hilite){this.hiliteForm.setValues({colorType:(this.hilite.textColor?"foreground":"background"),color:(this.hilite.textColor?this.hilite.textColor:this.hilite.backgroundColor)})}
this.addMember(this.hiliteForm)}}
,isc.A.checkHiliteProperties=function isc_HiliteRule_checkHiliteProperties(_1){if(!_1)return;if(_1.cssText){var _2=_1.cssText.split(";");for(var i=0;i<_2.length;i++){var _4=_2[i],_5=_4.split(":");if(_5[0]=="textColor"&&!_1.textColor)
_1.textColor=_5[1];else if(_5[0]=="backgroundColor"&&!_1.backgroundColor)
_1.backgroundColor=_5[1]}}else if(_1.textColor||_1.backgroundColor){_1.cssText="";if(_1.textColor)
_1.cssText+="color:"+_1.textColor+";";if(_1.backgroundColor)
_1.cssText+="background-color:"+_1.backgroundColor+";"}}
,isc.A.remove=function isc_HiliteRule_remove(){this.markForDestroy()}
,isc.A.getHilite=function isc_HiliteRule_getHilite(){if(this.isAdvanced){return this.hilite}
var _1=this.hilite=isc.addProperties(this.hilite||{},{fieldName:this.fieldName}),_2=this.hiliteForm.getValue("colorType"),_3=this.hiliteForm.getValue("color"),_4=this.clause.getCriterion();_1.criteria=_4;if(_2=="foreground"){_1.textColor=_3;_1.cssText="color:"+_3+";"}else{_1.backgroundColor=_3;_1.cssText="background-color:"+_3+";"}
if(this.hilite&&this.hilite.id)_1.id=this.hilite.id;return _1}
,isc.A.editAdvancedRule=function isc_HiliteRule_editAdvancedRule(){var _1=this.getID()+".editAdvancedRuleReply(hilite)";this.advancedHiliteDialog=isc.Window.create({title:"Advanced Highlight Editor",width:Math.round(isc.Page.getWidth()/2),height:1,isModal:true,showModalMask:true,showResizer:true,autoSize:true,autoCenter:true,items:[isc.AdvancedHiliteEditor.create({width:"100%",height:"100%",dataSource:this.fieldDataSource?null:this.dataSource,fieldDataSource:this.fieldDataSource,hilite:this.hilite,callback:_1})]});this.advancedHiliteDialog.show()}
,isc.A.editAdvancedRuleReply=function isc_HiliteRule_editAdvancedRuleReply(_1){this.advancedHiliteDialog.hide();this.advancedHiliteDialog.markForDestroy();if(_1){this.hilite=_1;var _2=isc.FilterBuilder.getFilterDescription(this.hilite.criteria,this.dataSource);this.advancedClauseLabel.setContents(_2);this.advancedClauseLabel.setPrompt(_2)}}
);isc.B._maxIndex=isc.C+6;isc.defineClass("HiliteEditor","VLayout");isc.A=isc.HiliteEditor.getPrototype();isc.A.mainLayoutDefaults={_constructor:"HLayout",width:"100%",extraSpace:5};isc.A.fieldLayoutDefaults={_constructor:"VLayout",width:180,autoParent:"mainLayout",showResizeBar:true};isc.A.addAdvancedHiliteButtonDefaults={_constructor:"IButton",title:"Add Advanced Rule",align:"center",width:"100%",height:22,autoParent:"fieldLayout",click:function(){this.creator.addAdvancedHilite()}};isc.A.fieldListDefaults={_constructor:"ListGrid",width:"100%",height:"*",autoParent:"fieldLayout",fields:[{name:"name",showIf:"false"},{name:"title",title:"Available Fields"}],recordClick:function(_1,_2){this.creator.addHilite(_2)}};isc.A.ruleLayoutDefaults={_constructor:"VLayout",top:22,membersMargin:1,padding:1,overflow:"auto",autoParent:"mainLayout",border:"1px solid grey",width:"100%",height:"100%"};isc.A.hiliteRuleDefaults={_constructor:"HiliteRule"};isc.A.hiliteButtonsDefaults={_constructor:"HLayout",layoutMargin:5,membersMargin:8,height:1};isc.A.saveButtonDefaults={_constructor:"IButton",autoParent:"hiliteButtons",title:"Save",click:function(){this.creator.saveHilites()}};isc.A.cancelButtonDefaults={_constructor:"IButton",autoParent:"hiliteButtons",title:"Cancel",click:function(){this.creator.completeEditing()}};isc.A.defaultWidth=800;isc.A.defaultHeight=300;isc.A=isc.HiliteEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.initWidget=function isc_HiliteEditor_initWidget(){this.Super("initWidget",arguments);this.addAutoChildren(["mainLayout","fieldLayout","addAdvancedHiliteButton","fieldList","ruleLayout","hiliteButtons","saveButton","cancelButton"]);this.setDataSource(this.dataSource);this.setHilites(this.hilites)}
,isc.A.setDataSource=function isc_HiliteEditor_setDataSource(_1){this.dataSource=_1;if(this.fieldDataSource&&!this.fieldDataSource.$782){this.setupFieldList()}else if(this.dataSource){this.getClientOnlyFieldDS()}else{this.logWarn("No DataSource present, can't edit hilites")}
this.fieldList.markForRedraw()}
,isc.A.setFieldDataSource=function isc_HiliteEditor_setFieldDataSource(_1){this.fieldDataSource=_1;this.setupFieldList()}
,isc.A.setupFieldList=function isc_HiliteEditor_setupFieldList(){this.fieldList.showFilterEditor=true;this.fieldList.setDataSource(this.fieldDataSource);this.fieldList.setFields([{name:"name",showIf:"false"},{name:"title",title:"Available Fields"},{name:"type",showIf:"false"}]);this.fieldList.fetchData()}
,isc.A.getClientOnlyFieldDS=function isc_HiliteEditor_getClientOnlyFieldDS(){var _1=isc.getValues(this.dataSource.getFields());var _2=[];for(var i=0;i<_1.length;i++){var _4=_1[i];if(!_4.hidden)_2.add(_4)}
this.fieldDataSource=isc.DataSource.create({$782:true,fields:[{name:"name",showIf:"false"},{name:"title",title:"Available Fields"},{name:"type",showIf:"false"}],cacheData:_2,clientOnly:true});this.setupFieldList()}
,isc.A.addHilite=function isc_HiliteEditor_addHilite(_1){var _2=this.createAutoChild("hiliteRule",{width:"100%",fieldName:_1.name,dataSource:this.dataSource});this.showNewHilite(_2)}
,isc.A.removeHilite=function isc_HiliteEditor_removeHilite(_1){this.ruleLayout.members.remove(_1);_1.destroy()}
,isc.A.showNewHilite=function isc_HiliteEditor_showNewHilite(_1){this.ruleLayout.addMember(_1)}
,isc.A.addAdvancedHilite=function isc_HiliteEditor_addAdvancedHilite(){var _1=this.getID()+".addAdvancedHiliteReply(hilite)";this.advancedHiliteDialog=isc.Window.create({title:"Advanced Hilite Editor",width:Math.round(isc.Page.getWidth()/2),height:1,isModal:true,showModalMask:true,showResizer:true,canDragResize:true,autoSize:true,autoCenter:true,items:[isc.AdvancedHiliteEditor.create({width:"100%",height:"100%",dataSource:this.fieldDataSource?null:this.dataSource,fieldDataSource:this.fieldDataSource,callback:_1})]});this.advancedHiliteDialog.show()}
,isc.A.addAdvancedHiliteReply=function isc_HiliteEditor_addAdvancedHiliteReply(_1){this.advancedHiliteDialog.hide();this.advancedHiliteDialog.markForDestroy();if(!_1)return;var _2=this.createAutoChild("hiliteRule",{width:"100%",isAdvanced:true,dataSource:this.dataSource,fieldDataSource:this.fieldDataSource,fieldName:_1.fieldName,hilite:_1});this.showNewHilite(_2)}
,isc.A.clearHilites=function isc_HiliteEditor_clearHilites(){for(var i=this.ruleLayout.members.length-1;i>=0;i--)
this.removeHilite(this.ruleLayout.getMember(i))}
,isc.A.setHilites=function isc_HiliteEditor_setHilites(_1){_1=this.hilites=_1||[];for(var i=0;i<_1.length;i++){var _3=_1[i],_4=this.createAutoChild("hiliteRule",{fieldName:_3.fieldName,hilite:_3,dataSource:this.dataSource});this.showNewHilite(_4)}}
,isc.A.saveHilites=function isc_HiliteEditor_saveHilites(_1){var _2=this.getHilites();this.completeEditing(_2)}
,isc.A.getHilites=function isc_HiliteEditor_getHilites(){var _1=this.ruleLayout.members,_2=[];for(var i=0;i<_1.length;i++){var _4=_1[i],_5=_4.getHilite();_2.add(_5)}
return _2}
,isc.A.getHiliteState=function isc_HiliteEditor_getHiliteState(){var _1=this.getHilites();if(_1==null)return null;return"("+isc.JSON.encode(_1,{dateFormat:"dateConstructor"})+")"}
,isc.A.completeEditing=function isc_HiliteEditor_completeEditing(_1){if(this.logIsInfoEnabled())this.logInfo("returning hilites: "+isc.echoFull(_1));if(this.callback)this.fireCallback(this.callback,"hilites",[_1])}
);isc.B._maxIndex=isc.C+16;isc.defineClass("AdvancedHiliteEditor","VStack");isc.A=isc.AdvancedHiliteEditor.getPrototype();isc.A.padding=10;isc.A.membersMargin=10;isc.A.filterBuilderDefaults={_constructor:"FilterBuilder",isGroup:true,groupTitle:"Filter",padding:8,maxHeight:200,overflow:"visible"};isc.A.hiliteFormDefaults={_constructor:"DynamicForm",isGroup:true,groupTitle:"Appearance",extraSpace:4,padding:8,width:"100%",numCols:6,colWidths:[200,150,100,150,100,150]};isc.A.hiliteButtonsDefaults={_constructor:isc.HLayout,membersMargin:8,height:1};isc.A.saveButtonDefaults={_constructor:"IButton",autoParent:"hiliteButtons",title:"Save",click:function(){this.creator.saveHilite()}};isc.A.cancelButtonDefaults={_constructor:"IButton",autoParent:"hiliteButtons",title:"Cancel",click:function(){this.creator.cancelEditing()}};isc.A.defaultWidth=800;isc.A.defaultHeight=600;isc.A.visibilityMode="multiple";isc.A.invalidHilitePrompt="Enter at least one rule, a color and a target field, or press 'Cancel' to abandon changes.";isc.A=isc.AdvancedHiliteEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.initWidget=function isc_AdvancedHiliteEditor_initWidget(){this.Super("initWidget",arguments);var _1=this.getDataSource(),_2=this;this.addAutoChild("filterBuilder",{dataSource:_1,fieldDataSource:this.fieldDataSource,fieldNameChanged:function(_10){this.Super("fieldNameChanged",arguments);_2.fieldChosen(_10.getFieldName())}});var _3=[{title:"Target Field(s)",name:"fieldName",multiple:true,allowMultiSelect:true,type:"select"},{title:"Text",name:"textColor",type:"color"},{title:"Background",name:"backgroundColor",type:"color"}];this.addAutoChild("hiliteForm");if(this.fieldDataSource){_3[0]=isc.addProperties({},_3[0],{valueField:"name",displayField:"title",optionDataSource:this.fieldDataSource});delete _3[0].defaultDynamicValue;this.hiliteForm.addItems(_3)}else{var _4=this.fieldNames||_1.getFieldNames(),_5=this.fieldMap={};for(var i=0;i<_4.length;i++){var _7=_4[i],_8=_1.getField(_7),_9=_8.title;if(_8.hidden)continue;_9=_9?_9:_7;_5[_7]=_9}
this.fieldMap=_5;_3[0].valueMap=_5;this.hiliteForm.addItems(_3)}
this.addAutoChildren(["hiliteButtons","saveButton","cancelButton"]);this.addMembers([this.filterBuilder,this.hiliteForm,this.hiliteButtons]);if(this.hilite!=null){this.filterBuilder.setCriteria(this.hilite.criteria);this.hiliteForm.editRecord(this.hilite)}}
,isc.A.fieldChosen=function isc_AdvancedHiliteEditor_fieldChosen(_1){if(_1&&this.hiliteForm.getValue("fieldName")==null){this.hiliteForm.setValue("fieldName",_1)}}
,isc.A.saveHilite=function isc_AdvancedHiliteEditor_saveHilite(){this.hiliteForm.setValue("criteria",this.filterBuilder.getCriteria());var _1=this.hiliteForm.getValues();if(_1.criteria.criteria==null||_1.criteria.criteria.length==0||(!_1.textColor&&!_1.backgroundColor)||_1.fieldName==null)
{isc.say(this.invalidHilitePrompt);return}
var _2="";if(_1.textColor&&_1.textColor!=""){_2+="color:"+_1.textColor+";"}
if(_1.backgroundColor&&_1.backgroundColor!=""){_2+="background-color:"+_1.backgroundColor+";"}
_1.cssText=_2;if(this.hilite&&this.hilite.id)_1.id=this.hilite.id;this.completeEditing(_1)}
,isc.A.cancelEditing=function isc_AdvancedHiliteEditor_cancelEditing(){this.completeEditing(null)}
,isc.A.completeEditing=function isc_AdvancedHiliteEditor_completeEditing(_1){if(this.callback)this.fireCallback(this.callback,["hilite"],[_1])}
);isc.B._maxIndex=isc.C+5;isc.ClassFactory.defineClass("ReportBuilder","VLayout");isc.A=isc.ReportBuilder;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.reportIdField="reportId";isc.A.reportNameField="reportName";isc.A.reportCategoryField="reportCategory";isc.A.reportCreatorField="reportCreator";isc.A.reportIsSharedField="reportIsShared";isc.A.reportIsDefaultField="reportIsDefault";isc.A.reportViewStateField="reportViewState";isc.A.defaultDataSource="ReportChooserDS";isc.B.push(isc.A.showReportBuilder=function isc_c_ReportBuilder_showReportBuilder(_1,_2,_3,_4,_5){var _6=isc.Window.create(isc.addProperties({},{isModal:true,width:"90%",height:"90%",title:"Report Builder",vertical:true,autoSize:true,autoCenter:true,visible:false,callback:_2,showMinimizeButton:false,closeClick:function(){if(this.callback)this.fireCallback(this.callback);return this.Super("closeClick")}},_4));var _7=isc.ReportBuilder.create(isc.addProperties({},{width:"100%",height:"100%",dataSource:_5||isc.DS.get(this.defaultDataSource),grid:_1,callback:_2},_3));_6.body.addChild(_7);_6.show()}
,isc.A.setLinkedGridState=function isc_c_ReportBuilder_setLinkedGridState(_1,_2){_2=_2||this.previewGrid;if(!_2||!_1)return;var _3=_1["reportViewState"],_4=isc.ReportBuilder.getObjectFromState(_3);_2.setCriteria(_4&&_4.criteria?isc.ReportBuilder.getObjectFromState(_4.criteria):null);var _5=isc.ReportBuilder.getObjectFromState(_4.field);_2.setFieldState(_5?_5.field:null);_2.setSortState(_5?_5.sort:null);_2.ungroup();if(_5.group&&_5.group.length>0)
_2.groupBy(_5?_5.group:null);_2.setHiliteState(_4.hilite)}
,isc.A.getObjectFromState=function isc_c_ReportBuilder_getObjectFromState(_1){if(_1==null)return null;var _2=eval(_1);return _2}
,isc.A.getStateForObject=function isc_c_ReportBuilder_getStateForObject(_1){if(_1==null)return null;return"("+isc.JSON.encode(_1,{dateFormat:"dateConstructor"})+")"}
);isc.B._maxIndex=isc.C+4;isc.A=isc.ReportBuilder.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.vertical=true;isc.A.padding=10;isc.A.width=830;isc.A.height=600;isc.A.layoutDefaults={_constructor:"VLayout",width:"100%",height:1};isc.A.reportGridDefaults={_constructor:"ListGrid",width:"100%",height:122,autoParent:"layout",canEdit:false,autoFetchData:true,recordClick:function(_1,_2){this.creator.showRecord(_2)},dataArrived:function(_1,_2){var _3=this.getSelection();if(_3&&_3.length==0){this.selectSingleRecord(0);this.recordClick(this,this.getRecord(0))}},fields:[{name:"reportName",title:"Report Name"},{name:"reportDescription",title:"Description"},{name:"reportCreator",title:"Created By"},{name:"reportIsDefault",type:"boolean",title:"Default"},{name:"reportIsShared",type:"boolean",title:"Shared"},{name:"reportCategory",title:"Category"}]};isc.A.reportGridButtonLayoutDefaults={_constructor:"HLayout",width:"100%",height:1,overflow:"visible",autoParent:"layout",align:"right"};isc.A.cloneSelectedButtonTitle="Clone";isc.A.cloneSelectedButtonDefaults={_constructor:"IButton",autoFit:true,click:"this.creator.cloneSelected();",autoParent:"reportGridButtonLayout"};isc.A.addNewButtonTitle="+";isc.A.addNewButtonDefaults={_constructor:"IButton",width:18,click:"this.creator.addNew();",autoParent:"reportGridButtonLayout"};isc.A.removeSelectedButtonTitle="-";isc.A.removeSelectedButtonDefaults={_constructor:"IButton",width:18,click:"this.creator.removeSelected();",autoParent:"reportGridButtonLayout"};isc.A.editorLayoutDefaults={_constructor:"VLayout",width:"100%",height:1,overflow:"visible",layoutTopMargin:15,autoParent:"layout"};isc.A.editorFormDefaults={_constructor:"DynamicForm",width:"100%",numCols:7,colWidths:[100,"*","*",120,60,60,80],fields:[{name:"reportName",type:"text",colSpan:2,width:"*",title:"Report Name"},{name:"reportIsDefault",type:"boolean",colSpan:1,width:"*",showTitle:false},{name:"reportIsShared",type:"boolean",colSpan:1,width:"*",title:"Shared",showTitle:false},{name:"reportCategory",type:"select",width:"*",title:"Category",endRow:true,valueMap:["Financial","HR"]},{name:"reportDescription",type:"textArea",colSpan:"*",width:"*",height:66,title:"Description",endRow:true}],autoParent:"editorLayout"};isc.A.editorTabSetDefaults={_constructor:"TabSet",width:"100%",height:200,autoParent:"editorLayout"};isc.A.editorButtonLayoutDefaults={_constructor:"HLayout",width:"100%",height:1,overflow:"visible",autoParent:"editorLayout",backgroundColor:"lightblue",membersMargin:5,padding:3,align:"right"};isc.A.editorPreviewLabelTitle="Preview";isc.A.editorPreviewLabelDefaults={_constructor:"Label",width:"*",height:22,layoutAlign:"right",autoParent:"editorButtonLayout"};isc.A.editorTryItButtonTitle="Apply";isc.A.editorTryItButtonDefaults={_constructor:"IButton",autoFit:true,click:"this.creator.tryIt();",layoutAlign:"right",autoParent:"editorButtonLayout"};isc.A.editorSaveButtonTitle="Save";isc.A.editorSaveButtonDefaults={_constructor:"IButton",autoFit:true,click:"this.creator.saveEditor();",layoutAlign:"right",autoParent:"editorButtonLayout"};isc.A.editorCancelButtonTitle="Revert";isc.A.editorCancelButtonDefaults={_constructor:"IButton",autoFit:true,click:"this.creator.cancelEditor();",layoutAlign:"right",autoParent:"editorButtonLayout"};isc.A.criteriaPaneDefaults={_constructor:"VLayout",width:"100%",height:"100%",formDefaults:{_constructor:"DynamicForm",width:160,height:22,fields:[{name:"criteriaType",type:"radioGroup",colSpan:"*",width:"*",showTitle:false,vertical:false,valueMap:["Basic","Advanced"],defaultValue:"Advanced",changed:function(_1,_2,_3){_1.creator.showFilterBuilder(_3)}}],extraSpace:10},filterBuilderDefaults:{_constructor:"FilterBuilder",width:"100%",height:"100%"},initWidget:function(){this.Super("initWidget",arguments);if(this.criteriaState){this.initialCriteria=this.getState(this.criteriaState)}
this.addAutoChild("form");this.addAutoChild("filterBuilder",{dataSource:this.dataSource,criteria:this.initialCriteria});this.addMember(this.filterBuilder)},showFilterBuilder:function(_1){var _2=this.filterBuilder.getCriteria();this.removeMember(this.filterBuilder);this.filterBuilder.destroy();this.filterBuilder=null;var _3={dataSource:this.dataSource,criteria:_2};if(_1=="Basic")_3.topOperatorAppearance="radio";this.addAutoChild("filterBuilder",_3);this.addMember(this.filterBuilder)},getCriteria:function(){var _1=this.filterBuilder.getCriteria();if(!_1||_1.criteria.length==0)return null;return _1},setCriteria:function(_1){return this.filterBuilder.setCriteria(_1)},getState:function(_1){_1=_1||this.getCriteria();if(_1==null)return null;return"("+isc.JSON.encode(_1,{dateFormat:"dateConstructor"})+")"},setState:function(state){if(state==null)this.setCriteria(null);var crit=eval(state);this.setCriteria(crit)}};isc.A.columnPaneDefaults={_constructor:"VLayout",width:"100%",height:"100%",layoutDefaults:{_constructor:"VLayout",width:"100%",height:"100%"},layoutLabelDefaults:{_constructor:"Label",width:"100%",height:"30",contents:"Use the arrows or drag and drop column-names to configure visible columns."+"  You may also directly manipulate the Preview grid below.",autoParent:"layout"},childLayoutDefaults:{_constructor:"HLayout",width:"100%",height:"100%",autoParent:"layout"},fieldGridDefaults:{_constructor:"ListGrid",width:"30%",height:"100%",autoParent:"childLayout",fields:[{name:"title",title:"Title"},{name:"type",title:"Type"}],canDragRecordsOut:true,canAcceptDroppedRecords:true,canReorderRecords:true,dragDataAction:"move",transferRecords:function(_1,_2,_3,_4,_5){_1.setProperty("isInGrid",false);this.Super("transferRecords",arguments)}},buttonLayoutDefaults:{_constructor:"VLayout",width:1,height:"100%",autoParent:"childLayout",padding:10,align:"center",layoutAlign:"center"},moveLeftButtonDefaults:{_constructor:"IButton",width:30,height:30,title:"<<",autoParent:"buttonLayout",layoutAlign:"center",disabled:true,click:"this.creator.moveSelectionLeft()"},moveRightButtonDefaults:{_constructor:"IButton",width:30,height:30,title:">>",autoParent:"buttonLayout",layoutAlign:"center",disabled:true,click:"this.creator.moveSelectionRight()"},configGridDefaults:{_constructor:"ListGrid",width:"*",height:"100%",autoParent:"childLayout",fields:[{name:"name",showIf:"false",title:"Name"},{name:"title",title:"Title"},{name:"width",title:"Width"},{name:"frozen",canToggle:true,type:"boolean",title:"Frozen"},{name:"sortIndex",title:"Sort Order"},{name:"sortDirection",type:"select",title:"Sort Direction",valueMap:{ascending:"Ascending",descending:"Descending"}},{name:"groupIndex",title:"Group By Order"},{name:"masterIndex",showIf:"false"}],initialCriteria:{_constructor:"AdvancedCriteria",operator:"and",criteria:[{fieldName:"isInGrid",operator:"equal",value:true}]},initialSort:[{property:"masterIndex",direction:"ascending"}],canEdit:true,autoSaveEdits:true,canDragRecordsOut:true,canAcceptDroppedRecords:true,canReorderRecords:true,dragDataAction:"move",transferRecords:function(_1,_2,_3,_4,_5){_1.setProperty("isInGrid",true);this.Super("transferRecords",arguments)}},getPreviewGrid:function(){if(!this.previewGrid){this.previewGrid=this.creator.previewGrid;var _1=this;this.observe(this.previewGrid,"viewStateChanged","observer.setData(observer.previewGrid);")}
return this.previewGrid},initWidget:function(){this.Super("initWidget",arguments);this.addAutoChild("layout");this.addAutoChild("layoutLabel");this.addAutoChild("childLayout");this.addAutoChild("fieldGrid");this.addAutoChildren(["buttonLayout","moveLeftButton","moveRightButton"]);this.addAutoChild("configGrid",{previewGrid:this.getPreviewGrid()});if(this.columnState){this.setState(this.columnState)}else{this.setInitialData()}},moveSelectionLeft:function(){var _1=this.configGrid.getSelection();_1.setProperty("isInGrid",false);if(_1)this.fieldGrid.transferRecords(_1,null,null,this.configGrid)},moveSelectionRight:function(){var _1=this.fieldGrid.getSelection();if(_1)this.configGrid.transferRecords(_1,this.configGrid.data.length-1,this.configGrid.data.length,this.fieldGrid)},getState:function(){return isc.ReportBuilder.getStateForObject(this.getData())},setState:function(_1){if(!_1){this.setInitialData();return}
var _2=isc.ReportBuilder.getObjectFromState(_1),_3=this.getPreviewGrid(),_4=this.linkedGrid;_3.setFieldState(_2.field);_3.setSortState(_2.sort);if(_2&&_2.group=="")_2.group=null;_3.groupByFields=_2.group;_3.groupBy(_2.group);this.setData(_3)},setInitialData:function(){this.setData(this.linkedGrid)},getData:function(){var _1=this.getPreviewGrid();var _2={field:isc.ReportBuilder.getObjectFromState(_1.getFieldState()),sort:{}};var _3=this.configGrid.data,_4=[],_5=[]
for(var i=0;i<_2.field.length;i++){var _7=_2.field[i],_8=_3.find("name",_7.name);if(_8){delete _7.visible;_7.frozen=_8.frozen;delete _7.$776;if(_8.width=="AutoFit"){_7.autoFitWidth=true;delete _7.width}else{var _9=_8.width!=null?_8.width:"*";delete _7.autoFitWidth;var _10=parseInt(_9);_7.width=!isNaN(_10)?_10:_9}
if(_8.groupIndex!=null){_4.add({name:_8.name,groupIndex:_8.groupIndex})}
if(_8.sortIndex!=null){_5.add({name:_8.name,sortDirection:_8.sortDirection,sortIndex:_8.sortIndex})}}else{_7.visible=false}}
if(_5){_5=_5.sortByProperty("sortIndex",true);for(var i=0;i<_5.length;i++){if(!_2.sort.sortSpecifiers)_2.sort.sortSpecifiers=[];_2.sort.sortSpecifiers.add({property:_5[i].name,direction:_5[i].sortDirection})}}
if(_4){_4=_4.sortByProperty("groupIndex",true);_2.group=_4.getProperty("name").join(",")}
return _2},setData:function(_1){var _2=_1.getAllFields();var _3=_1.getFields(),_4=_1.getSort(),_5=_1.getGroupByFields(),_6=[];for(var i=0;i<_2.length;i++){var _8=isc.shallowClone(_2[i]),_9=_3.find("name",_8.name),_10=_1.getSortSpecifier(_8.name),_11=_10?_4.indexOf(_4.find("property",_8.name)):0,_12=_5?_5.contains(_8.name):false,_13=_12?_5.indexOf(_8.name):0;if(_9&&_9.visible!=false){_8.isInGrid=true}else{_8.isInGrid=false}
if(_8.autoFitWidth)_8.width="AutoFit";if(!_8.width)_8.width="*";if(_10){_8.sortIndex=_11+1;_8.sortDirection=_10.direction}else{delete _8.sortIndex;delete _8.sortDirection}
if(_12){_8.groupIndex=_13+1}else{delete _8.groupIndex}
_6.add(_8)}
var _14;if(this.fieldGrid){_14=_6.findAll("isInGrid",false);this.fieldGrid.setData(_14?_14.duplicate():[])}
if(this.configGrid){_14=_6.findAll("isInGrid",true);this.configGrid.setData(_14?_14.duplicate():[])}}};isc.A.hilitePaneDefaults={_constructor:"VLayout",width:"100%",height:"100%",overflow:"auto",hiliteEditorDefaults:{_constructor:"HiliteEditor",width:"100%",height:"100%",showHiliteButtons:false},initWidget:function(){this.Super("initWidget",arguments);if(this.hiliteState){this.hilites=this.getState(this.hiliteState)}
this.addAutoChild("hiliteEditor",{dataSource:this.dataSource,hilites:this.hilites});this.addMember(this.hiliteEditor)},getState:function(_1){var _2=_1||this.hiliteEditor.getHiliteState();return _2},setState:function(state){this.hiliteEditor.clearHilites();if(state==null){this.hiliteEditor.setHilites(null);return}
var hilites=eval(state);this.hiliteEditor.setHilites(hilites)}};isc.A.formulaPaneDefaults={_constructor:"VLayout",width:"100%",height:"100%",layoutDefaults:{_constructor:"VLayout",width:"100%",height:"100%"},childLayoutDefaults:{_constructor:"HLayout",width:"100%",height:"100%",autoParent:"layout"},fieldGridDefaults:{_constructor:"ListGrid",width:"30%",height:"100%",autoParent:"childLayout",fields:[{name:"title",title:"Title"},{name:"type",title:"Type"}]},builderDefaults:{_constructor:"FormulaBuilder",width:"*",height:"100%",autoParent:"childLayout"},initWidget:function(){this.Super("initWidget",arguments);if(this.formulaState){}
this.addAutoChild("layout");this.addAutoChild("layoutLabel");this.addAutoChild("childLayout");this.addAutoChild("fieldGrid");this.addAutoChild("builder",{dataSource:this.dataSource});this.addMember(this.layout)},getState:function(_1){},setState:function(state){}};isc.A.summaryPaneDefaults={_constructor:"VLayout",width:"100%",height:"100%",layoutDefaults:{_constructor:"VLayout",width:"100%",height:"100%"},childLayoutDefaults:{_constructor:"HLayout",width:"100%",height:"100%",autoParent:"layout"},fieldGridDefaults:{_constructor:"ListGrid",width:"30%",height:"100%",autoParent:"childLayout",fields:[{name:"title",title:"Title"},{name:"type",title:"Type"}]},builderDefaults:{_constructor:"SummaryBuilder",width:"*",height:"100%",autoParent:"childLayout"},initWidget:function(){this.Super("initWidget",arguments);if(this.summaryState){}
this.addAutoChild("layout");this.addAutoChild("layoutLabel");this.addAutoChild("childLayout");this.addAutoChild("fieldGrid");this.addAutoChild("builder",{dataSource:this.dataSource});this.addMember(this.layout)},getState:function(_1){},setState:function(state){}};isc.A.previewGridDefaults={_constructor:"ListGrid",width:"100%",height:122,autoParent:"layout",canEdit:false,autoFetchData:true};isc.A.normalAutoChildren=["layout","reportGrid","reportGridButtonLayout","cloneSelectedButton","adddNewButton","removeSelectedButton"];isc.A.editorAutoChildren=["editorLayout","editorForm","editorTabSet","editorButtonLayout","editorPreviewLabel","editorSaveButton","editorCancelButton"];isc.A.otherAutoChildren=["previewGrid"];isc.B.push(isc.A.initWidget=function isc_ReportBuilder_initWidget(){this.Super("initWidget",arguments);this.reportIdField=this.reportIdField||this.getClass().reportIdField;this.reportNameField=this.reportNameField||this.getClass().reportNameField;this.reportCategoryField=this.reportCategoryField||this.getClass().reportCategoryField;this.reportCreatorField=this.reportCreatorField||this.getClass().reportCreatorField;this.reportIsSharedField=this.reportSharedField||this.getClass().reportIsSharedField;this.reportIsDefaultField=this.reportIsDefaultField||this.getClass().reportIsDefaultField;this.reportViewStateField=this.reportViewStateField||this.getClass().reportViewStateField;this.dataSource=isc.DS.get(this.dataSource);if(!isc.isA.DataSource(this.dataSource))
this.dataSource=isc.DS.get(this.getClass().defaultDataSource);this.addAutoChild("layout");var _1={dataSource:this.dataSource};var _2=null;this.addAutoChild("reportGrid",{dataSource:this.dataSource,initialCriteria:_2});this.addAutoChild("reportGridButtonLayout");this.addAutoChild("cloneSelectedButton",{title:this.cloneSelectedButtonTitle});this.addAutoChild("addNewButton",{title:this.addNewButtonTitle});this.addAutoChild("removeSelectedButton",{title:this.removeSelectedButtonTitle});this.createEditorLayout();var _3=this.getGrid();this.addAutoChild("previewGrid",{dataSource:_3.dataSource});var _4=_3.getViewState();this.previewGrid.setViewState(_4)}
,isc.A.createEditorLayout=function isc_ReportBuilder_createEditorLayout(){if(!this.editorLayout){this.addAutoChild("editorLayout");this.addAutoChild("editorForm",{dataSource:this.dataSource,extraSpace:10});this.addMember(isc.LayoutSpacer.create({width:"100%",height:10}));this.addAutoChild("editorTabSet");this.editorTabSet.addTabs([{ID:"criteria_tab",name:"criteriaPane",title:"Criteria",selected:true},{ID:"columns_tab",name:"columnsPane",title:"Columns"},{ID:"hilites_tab",name:"hilitesPane",title:"Hilites"},{ID:"formula_tab",name:"formulaPane",title:"Calculated Columns"},{ID:"summary_tab",name:"summaryPane",title:"Summary Columns"}]);this.editorTabSet.selectTab(0);this.addAutoChild("criteriaPane",{dataSource:this.getGridDataSource()});this.editorTabSet.setTabPane(0,this.criteriaPane);this.addAutoChild("columnPane",{dataSource:this.getGridDataSource(),linkedGrid:this.getGrid()});this.editorTabSet.setTabPane(1,this.columnPane);this.addAutoChild("hilitePane",{dataSource:this.getGridDataSource()});this.editorTabSet.setTabPane(2,this.hilitePane);this.addAutoChild("formulaPane",{dataSource:this.getGridDataSource()});this.editorTabSet.setTabPane(3,this.formulaPane);this.addAutoChild("summaryPane",{dataSource:this.getGridDataSource()});this.editorTabSet.setTabPane(4,this.summaryPane);this.addAutoChild("editorButtonLayout");this.addAutoChild("editorPreviewLabel",{contents:this.editorPreviewLabelTitle});this.addAutoChild("editorTryItButton",{title:this.editorTryItButtonTitle});this.addAutoChild("editorSaveButton",{title:this.editorSaveButtonTitle});this.addAutoChild("editorCancelButton",{title:this.editorCancelButtonTitle})}else{this.editorLayout.show()}}
,isc.A.getUserID=function isc_ReportBuilder_getUserID(){return"testUser"}
,isc.A.getSelected=function isc_ReportBuilder_getSelected(){var _1=this.reportGrid.getSelection();if(isc.isAn.Array(_1)&&_1.length>0)return _1[0];return null}
,isc.A.cloneSelected=function isc_ReportBuilder_cloneSelected(){var _1=this.getSelected();if(_1){var _2=isc.addProperties({},_1);_2[this.dataSource.getPrimaryKeyFieldNames()[0]]=null;this.showEditor(_2,"add")}}
,isc.A.showRecord=function isc_ReportBuilder_showRecord(_1){if(_1)this.showEditor(_1)}
,isc.A.addNew=function isc_ReportBuilder_addNew(){this.showEditor(null,"add")}
,isc.A.removeSelected=function isc_ReportBuilder_removeSelected(){var _1=this.getSelected();if(_1){this.reportGrid.removeRecord(null,_1)}}
,isc.A.getGrid=function isc_ReportBuilder_getGrid(){return this.grid}
,isc.A.setGrid=function isc_ReportBuilder_setGrid(_1){this.grid=_1}
,isc.A.getGridDataSource=function isc_ReportBuilder_getGridDataSource(){var _1=this.getGrid();if(!_1)return this.dataSource;return _1.getDataSource()}
,isc.A.showEditor=function isc_ReportBuilder_showEditor(_1,_2){this.setEditorData(_1,_2)}
);isc.B._maxIndex=isc.C+12;isc.A=isc.ReportBuilder.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.getEditorCriteriaState=function isc_ReportBuilder_getEditorCriteriaState(){return this.criteriaPane?this.criteriaPane.getState():null}
,isc.A.setEditorCriteriaState=function isc_ReportBuilder_setEditorCriteriaState(_1){if(this.criteriaPane)
this.criteriaPane.setState(_1?_1.criteria:null)}
,isc.A.getEditorColumnState=function isc_ReportBuilder_getEditorColumnState(){return this.columnPane?this.columnPane.getState():null}
,isc.A.setEditorColumnState=function isc_ReportBuilder_setEditorColumnState(_1){if(this.columnPane)this.columnPane.setState(_1?_1.field:null)}
,isc.A.getEditorHiliteState=function isc_ReportBuilder_getEditorHiliteState(){return this.hilitePane?this.hilitePane.getState():null}
,isc.A.setEditorHiliteState=function isc_ReportBuilder_setEditorHiliteState(_1){if(this.hilitePane)this.hilitePane.setState(_1?_1.hilite:null)}
,isc.A.getEditorSummaryState=function isc_ReportBuilder_getEditorSummaryState(){return this.summaryPane?this.summaryPane.getState():null}
,isc.A.setEditorSummaryState=function isc_ReportBuilder_setEditorSummaryState(_1){if(this.summaryPane)this.summaryPane.setState(_1?_1.summary:null)}
,isc.A.getEditorFormulaState=function isc_ReportBuilder_getEditorFormulaState(){return this.formulaPane?this.formulaPane.getState():null}
,isc.A.setEditorFormulaState=function isc_ReportBuilder_setEditorFormulaState(_1){if(this.formulaPane)this.formulaPane.setState(_1?_1.formula:null)}
,isc.A.setEditorData=function isc_ReportBuilder_setEditorData(_1,_2){var _3=this.editorForm;if(!_1||_2=="add"){_3.editNewRecord(isc.addProperties({},_1,{reportCreator:this.getUserID()}))}else _3.editRecord(_1);var _4=_1?isc.ReportBuilder.getObjectFromState(_1[this.reportViewStateField]):null;this.setEditorCriteriaState(_4);this.setEditorColumnState(_4);this.setEditorHiliteState(_4);this.setEditorFormulaState(_4);this.setEditorSummaryState(_4);this.tryIt()}
,isc.A.clearEditorData=function isc_ReportBuilder_clearEditorData(){this.setEditorData(null,null)}
,isc.A.hideEditor=function isc_ReportBuilder_hideEditor(){this.editorLayout.hide();var _1=this.getSelected();this.cloneSelectedButton.setDisabled(!_1);this.addNewButton.enable();this.removeSelectedButton.setDisabled(!_1);this.reportGrid.enable()}
,isc.A.updateFormRecord=function isc_ReportBuilder_updateFormRecord(){var _1=this.editorForm;var _2={criteria:this.getEditorCriteriaState(),field:this.getEditorColumnState(),hilite:this.getEditorHiliteState(),formula:this.getEditorFormulaState(),summary:this.getEditorSummaryState()};_1.setValue(this.reportViewStateField,isc.ReportBuilder.getStateForObject(_2))}
,isc.A.saveEditor=function isc_ReportBuilder_saveEditor(){this.updateFormRecord();this.editorForm.saveData()}
,isc.A.cancelEditor=function isc_ReportBuilder_cancelEditor(){var _1=this.getSelected(),_2=this.editorForm.formOperationType,_3=0;if(_2!="add"){_3=this.reportGrid.getRecordIndex(_1)}
this.clearEditorData();this.reportGrid.deselectAllRecords();this.reportGrid.selectSingleRecord(_3);this.showEditor(this.reportGrid.getRecord(_3))}
,isc.A.tryIt=function isc_ReportBuilder_tryIt(){if(this.columnPane&&this.columnPane.configGrid)this.columnPane.configGrid.endEditing();this.updateFormRecord();isc.ReportBuilder.setLinkedGridState(this.editorForm.getData(),this.previewGrid)}
);isc.B._maxIndex=isc.C+17;isc._moduleEnd=isc._Grids_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('Grids module init time: ' + (isc._moduleEnd-isc._moduleStart) + 'ms','loadTime');delete isc.definingFramework;}else{if(window.isc && isc.Log && isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'Grids'.");}
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

