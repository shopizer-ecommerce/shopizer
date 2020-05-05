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

if(window.isc&&window.isc.module_Core&&!window.isc.module_DataBinding){isc.module_DataBinding=1;isc._moduleStart=isc._DataBinding_start=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc._moduleEnd&&(!isc.Log||(isc.Log && isc.Log.logIsDebugEnabled('loadTime')))){isc._pTM={ message:'DataBinding load/parse time: ' + (isc._moduleStart-isc._moduleEnd) + 'ms', category:'loadTime'};
if(isc.Log && isc.Log.logDebug)isc.Log.logDebug(isc._pTM.message,'loadTime')
else if(isc._preLog)isc._preLog[isc._preLog.length]=isc._pTM
else isc._preLog=[isc._pTM]}isc.definingFramework=true;if(!isc.Comm)isc.defineClass("Comm");isc.A=isc.Comm;isc.A.XML_BACKREF_PREFIX="$$BACKREF$$:";isc.A.$36r=/^([_:A-Za-z])([_:.A-Za-z0-9]|-)*$/;isc.A.serializeBackrefs=true;isc.A=isc.Comm;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.xmlSerialize=function isc_c_Comm_xmlSerialize(_1,_2,_3){return isc.Comm.$ew(_1,_2,_3?"":null)}
,isc.A.$ew=function isc_c_Comm__xmlSerialize(_1,_2,_3,_4){var _5=_1!=null;if(!_4||!_4.objRefs){_4=isc.addProperties({},_4);_4.objRefs={obj:[],path:[]};if(!_4.objPath){if(_2&&_2.getID)_4.objPath=_2.getID();else _4.objPath=""}
if(_1==null){if(isc.isA.Class(_2))_1=_2.getClassName();else if(isc.isAn.Array(_2))_1="Array";else if(isc.isA.Object(_2))_1=_2.$schemaId||"Object";else _1="ISC_Auto"}}
if(_2==null){if(isc.Comm.xmlSchemaMode||!isc.Comm.$850){return isc.Comm.$ex(_1,"")}else{return isc.Comm.$ex(_1,null,"nil")}}
if(isc.isA.String(_2)){return isc.Comm.$ex(_1,isc.makeXMLSafe(_2),(isc.Comm.xmlSchemaMode?"string":null))}
if(isc.isA.Function(_2)){if(_2.iscAction)return isc.StringMethod.$41u(_2.iscAction);return null}
if(_2==window){this.logWarn("Serializer encountered the window object at path: "+_4.objPath+" - returning null for this slot.");return null}
if(isc.RPCManager.preserveTypes){if(isc.isA.Number(_2)||isc.isA.SpecialNumber(_2)){if(_2.toString().contains("."))
return isc.Comm.$ex(_1,_2,"double");return isc.Comm.$ex(_1,_2,"long")}
if(isc.isA.Boolean(_2))return isc.Comm.$ex(_1,_2,"boolean")}else{if(isc.isA.Number(_2)||isNaN(_2)){return isc.Comm.$ex(_1,_2)}
if(isc.isA.Boolean(_2))return isc.Comm.$ex(_1,_2)}
var _6=isc.JSONEncoder.$zl(_4.objRefs,_2);if(_6!=null&&_4.objPath.contains(_6)){var _7=_4.objPath.substring(_6.length,_6.length+1);if(_7=="."||_7=="["||_7=="]"){if(this.serializeBackrefs){return isc.Comm.$36u(_1)+isc.Comm.XML_BACKREF_PREFIX+_6+isc.Comm.$36v(_1)}
return isc.emptyString}}
isc.JSONEncoder.$zm(_4.objRefs,_2,_4.objPath);if(isc.isA.Function(_2.$ew)){return _2.$ew(_1,null,null,_3,_4.objRefs,_4.objPath)}else if(isc.isA.Class(_2)){this.logWarn("Attempt to serialize class of type: "+_2.getClassName()+" at path: "+_4.objPath+" - returning null for this slot.");return null}
var _8=_4.isRoot==false?false:true;if(isc.isAn.Array(_2))
return isc.Comm.$36s(_1,_2,_4.objPath,_4.objRefs,_3,_8);var _9;if(_2.getSerializeableFields){_9=_2.getSerializeableFields([],[])}else{_9=_2}
return isc.Comm.$36t(_1,_9,_4.objPath,_4.objRefs,_3,_8)}
,isc.A.$36s=function isc_c_Comm__xmlSerializeArray(_1,_2,_3,_4,_5,_6){var _7=isc.Comm.$36u(_1,"List",null,null,null,_6);for(var i=0,_9=_2.length;i<_9;i++){var _10=_2[i];var _11={objRefs:_4,objPath:isc.JSONEncoder.$zp(_3,i),isRoot:false};_7=isc.StringBuffer.concat(_7,(_5!=null?isc.StringBuffer.concat("\r",_5,"\t"):""),isc.Comm.$ew((_10!=null?_10.$schemaId:null)||"elem",_10,(_5!=null?isc.StringBuffer.concat(_5,"\t"):null),_11))}
_7=isc.StringBuffer.concat(_7,(_5!=null?isc.StringBuffer.concat("\r",_5):""),isc.Comm.$36v(_1));return _7}
,isc.A.$36w=function isc_c_Comm__isValidXMLIdentifier(_1){return isc.Comm.xmlSchemaMode||_1.match(this.$36r)}
,isc.A.$36t=function isc_c_Comm__xmlSerializeObject(_1,_2,_3,_4,_5,_6){if(isc.isAn.Instance(_2))_1=_2.getClassName();else if(_2._constructor&&_2._constructor!="AdvancedCriteria"&&_2._constructor!="RelativeDate")_1=_2._constructor;var _7=isc.Comm.$36u(_1,"Object",null,null,null,_6);var _8;_2=isc.JSONEncoder.$42b(_2);for(var _9 in _2){if(_9==null)continue;if(_9==isc.gwtRef)continue;if(_9.startsWith('$'))continue;var _10=_2[_9];if(_10===_8)continue;if(isc.isA.Function(_10)&&!_10.iscAction)continue;var _11=_9.toString();var _12={objRefs:_4,objPath:isc.JSONEncoder.$zp(_3,_9),isRoot:false};_7=isc.StringBuffer.concat(_7,(_5!=null?isc.StringBuffer.concat("\r",_5,"\t"):""),isc.Comm.$ew(_11,_10,(_5!=null?isc.StringBuffer.concat(_5,"\t"):null),_12))}
_7=isc.StringBuffer.concat(_7,(_5!=null?isc.StringBuffer.concat("\r",_5):""),isc.Comm.$36v(_1));return _7}
,isc.A.$36x=function isc_c_Comm__getPrefix(_1,_2){if(_1[_2]!=null){return _1[_2]}else{if(_1.$36y==null)_1.$36y=0;return(_1[_2]="ns"+_1.$36y++)}}
,isc.A.$36u=function isc_c_Comm__xmlOpenTag(_1,_2,_3,_4,_5,_6){var _7=isc.SB.create();var _8=_3!=null;if(_3!=null&&isc.isAn.Object(_4)){_8=false;_4=this.$36x(_4,_3)}
var _9='';if(!this.$36w(_1)){_9=' _isc_name="'+isc.makeXMLSafe(_1)+'"';_1="Object"}
if(_3){_4=_4||"schNS";_7.append("<",_4,":",_1);if(_8)_7.append(" xmlns:",_4,"=\"",_3,"\"")}else{_7.append("<",_1)}
if(_9)_7.append(_9);if(_6&&!this.omitXSI){_7.append(" xmlns:xsi=\"http://www.w3.org/2000/10/XMLSchema-instance\"")}
if(_2&&!this.omitXSI){_7.append(" xsi:type=\"xsd:",isc.makeXMLSafe(_2),"\"")}
if(!_5)_7.append(">");return _7.toString()}
,isc.A.$36v=function isc_c_Comm__xmlCloseTag(_1,_2,_3){if(_2!=null&&isc.isAn.Object(_3)){_3=this.$36x(_3,_2)}
if(!this.$36w(_1))_1="Object";if(_2){_3=_3||"schNS";return isc.SB.concat("</",_3,":",_1,">")}else{return isc.SB.concat("</",_1,">")}}
,isc.A.$ex=function isc_c_Comm__xmlValue(_1,_2,_3,_4,_5){if(_3=="base64Binary"){_2="<xop:Include xmlns:xop=\"http://www.w3.org/2004/08/xop/include\" href=\""+_2+"\"/>"}
if(_3=="nil"){return isc.Comm.$36u(_1,null,_4,_5,true)+" xsi:nil=\"true\"/>"}
return isc.StringBuffer.concat(isc.Comm.$36u(_1,_3,_4,_5),_2,isc.Comm.$36v(_1,_4,_5))}
);isc.B._maxIndex=isc.C+9;isc.defineClass("XMLDoc").addMethods({addPropertiesOnCreate:false,init:function(_1,_2){this.nativeDoc=_1;this.namespaces=_2;this.documentElement=this.nativeDoc.documentElement},hasParseError:function(){if(isc.Browser.isIE){var _1=this.nativeDoc.parseError;return _1!=null&&_1!=0}
return this.nativeDoc.documentElement&&this.nativeDoc.documentElement.tagName=="parsererror"},addNamespaces:function(_1){this.namespaces=this.$363(_1);if(this.$364){var _2=isc.xml.xmlResponses.find("ID",this.$364);if(_2)_2.xmlNamespaces=this.namespaces}},$363:function(_1){if(_1==null)return this.namespaces;if(this.namespaces==null)return _1;return isc.addProperties({},this.namespaces,_1)},selectNodes:function(_1,_2,_3){return isc.xml.selectNodes(this.nativeDoc,_1,this.$363(_2),_3)},selectString:function(_1,_2){return isc.xml.selectString(this.nativeDoc,_1,this.$363(_2))},selectNumber:function(_1,_2){return isc.xml.selectNumber(this.nativeDoc,_1,this.$363(_2))},selectScalar:function(_1,_2,_3){return isc.xml.selectScalar(this.nativeDoc,_1,this.$363(_2),_3)},selectScalarList:function(_1,_2){return isc.xml.selectScalarList(this.nativeDoc,_1,this.$363(_2))},getElementById:function(_1){return this.nativeDoc.getElementById(_1)},getElementsByTagName:function(_1){return this.nativeDoc.getElementsByTagName(_1)}});isc.XMLDoc.getPrototype().toString=function(){return"[XMLDoc <"+this.documentElement.tagName+">]"};isc.defineClass("XMLTools").addClassProperties({httpProxyURL:"[ISOMORPHIC]/HttpProxy"});isc.A=isc.XMLTools;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.xmlResponses=[];isc.A.$365=0;isc.A.xmlDOMConstructors=["MSXML2.DOMDocument","MSXML.DOMDocument","Microsoft.XMLDOM"];isc.A.mozAnchorBug=isc.Browser.isMoz&&(isc.Browser.geckoVersion<20080205)&&window.location.href.indexOf("#")!=-1;isc.A.$pa="*";isc.A.$366=":";isc.A.$27c="List";isc.A.$45s="xmlToJS";isc.A.$45t="type";isc.A.$367="xsi:type";isc.A.$71v="ref";isc.A.$gy="number";isc.A.$71f="xmlns:";isc.A.$45u={nil:"xsi:nil","null":"xsi:null",type:"xsi:type"};isc.A.xsiNamespaces=["http://www.w3.org/2001/XMLSchema-instance","http://www.w3.org/1999/XMLSchema-instance"];isc.A.$45v="nil";isc.A.$45w="null";isc.A.$18r="false";isc.A.$w0="0";isc.A.$ho="[";isc.A.useClientXML=true;isc.B.push(isc.A.loadXML=function isc_c_XMLTools_loadXML(_1,_2,_3){_3=_3||{};_3.operationType=_3.operationType||"loadXML";this.getXMLResponse(isc.addProperties({actionURL:_1,httpMethod:"GET",callback:_2},_3))}
,isc.A.getXMLResponse=function isc_c_XMLTools_getXMLResponse(_1){_1.$37b=_1.callback;_1.callback={target:this,methodName:"$37c"};_1.httpMethod=_1.httpMethod||"POST";this.logInfo("loading XML from: "+_1.actionURL,"xmlComm");isc.rpc.sendProxied(_1)}
,isc.A.$37c=function isc_c_XMLTools__getXMLResponseReply(_1,_2,_3){if(_1.isStructured&&!_3.isRestRequest){this.fireCallback(_3.$37b,"xmlDoc,xmlText,rpcResponse,rpcRequest",[null,null,_1,_3]);return}
var _4=_3.isRestRequest?_1.results:_1.httpResponseText,_5=this.parseXML(_4);if(this.logIsInfoEnabled("xmlComm")){this.logInfo("XML reply with text: "+(this.logIsDebugEnabled("xmlComm")?_4:this.echoLeaf(_4)),"xmlComm")}
var _6=this.xmlResponses;var _7=isc.Log.logViewer;if(this.logIsDebugEnabled("xmlComm")||(isc.Page.isLoaded()&&_7&&_7.logWindowLoaded()))
{var _8=this.$365++;_6.add({ID:_8,text:_4});if(_5)_5.$364=_8;if(_6.length>10)_6.shift();if(_7&&_7.logWindowLoaded()&&_7._logWindow!=null){_7._logWindow.updateCommWatcher()}}else{_6.length=0}
this.fireCallback(_3.$37b,"xmlDoc,xmlText,rpcResponse,rpcRequest",[_5,_4,_1,_3])}
,isc.A.parseXML=function isc_c_XMLTools_parseXML(_1,_2){if(_1==null)return;_1=this.trimXMLStart(_1);var _3;if(!isc.Browser.isIE){try{if((this.mozAnchorBug||this.useAnchorWorkaround)&&this.useAnchorWorkaround!==false)
{var _4="<IFRAME STYLE='position:absolute;visibility:hidden;top:-1000px'"+" ID='isc_parseXMLFrame'></IFRAME>";if(!isc.Page.isLoaded()){document.write(_4)}else{isc.Element.insertAdjacentHTML(document.getElementsByTagName("body")[0],"beforeEnd",_4)}
var _5=document.getElementById("isc_parseXMLFrame");var _6=_5.contentWindow;window.isc.xmlSource=_1;_6.location.href="javascript:top.isc.parsedXML="+"new top.isc.XMLTools.getXMLParser().parseFromString(top.isc.xmlSource, 'text/xml')";_3=window.isc.parsedXML;isc.xmlSource=isc.parsedXML=null;_5.parentNode.removeChild(_5)}else{_3=this.getXMLParser().parseFromString(_1,"text/xml")}}catch(e){if(!_2)this.$37d(this.echo(e));return null}
if(!_2&&_3.documentElement&&_3.documentElement.tagName=="parsererror")
{this.$37d(_3.documentElement.textContent);return null}
return isc.XMLDoc.create(_3)}
_3=this.getXMLParser();if(!_3){this.$37a("XMLTools.parseXML()");return}
_3.loadXML(_1);if(_3.parseError!=0){var _7=_3.parseError;if(!_2){this.$37d("\rReason: "+_7.reason+"Line number: "+_7.line+", character: "+_7.linepos+"\rLine contents: "+_7.srcText+(!isc.isA.emptyString(_7.url)?"\rSource URL: "+_7.url:""))}
return null}
return isc.XMLDoc.create(_3)}
,isc.A.trimXMLStart=function isc_c_XMLTools_trimXMLStart(_1){if(_1.indexOf("<?xml")!=-1)
{var _2=_1.match(new RegExp("^\\s*<\\?[^?]*\\?>"));if(_2){_1=_1.substring(_2[0].length)}}
if(isc.Browser.isIE&&_1.indexOf("<!DOCTYPE")!=-1){var _2=_1.match(new RegExp("^\\s*<!DOCTYPE .*>"));if(_2){_1=_1.substring(_2[0].length)}}
return _1}
,isc.A.$37d=function isc_c_XMLTools__logParseError(_1,_2){this.logWarn("Error parsing XML: "+_1+(this.logIsDebugEnabled("parseXML")?"\rXML was:\r"+_2+"\rTrace:"+this.getStackTrace():""),"parseXML")}
,isc.A.getXMLParser=function isc_c_XMLTools_getXMLParser(){if(!isc.Browser.isIE){if(!this.$37e)this.$37e=new DOMParser();return this.$37e}
var _1;if(this.$37f){_1=new ActiveXObject(this.$37f)}else{for(var i=0;i<this.xmlDOMConstructors.length;i++){try{var _3=this.xmlDOMConstructors[i];_1=new ActiveXObject(_3);if(_1){this.logInfo("Using XML DOM constructor: "+_3);this.$37f=_3;break}}catch(e){}}
if(!_1){this.logWarn("Couldn't create XML DOM parser - tried the following"+" constructors: "+this.echoAll(this.xmlDOMConstructors))}}
return _1}
,isc.A.nativeXMLAvailable=function isc_c_XMLTools_nativeXMLAvailable(){if(isc.Browser.isSafari&&!isc.Browser.isApollo&&(isc.Browser.safariVersion<522))
return false;return this.$37e!=null||this.getXMLParser()!=null}
,isc.A.$37a=function isc_c_XMLTools__warnIfNativeXMLUnavailable(_1){if(this.nativeXMLAvailable()||!this.logIsWarnEnabled())return false;var _2="Feature "+_1+" requires a native XML parser which is "+"not available ";if(isc.Browser.isSafari){_2+="because this version of Safari does not support native XML processing."}else{_2+="because ActiveX is currently disabled."}
_2+=" Please see the 'Features requiring ActiveX or Native support'"+" topic in the client-side reference under Client Reference/System"+" for more information";this.logWarn(_2);return true}
,isc.A.serverToJS=function isc_c_XMLTools_serverToJS(_1,_2,_3){isc.DMI.callBuiltin({methodName:"xmlToJS",callback:_2,arguments:_1,requestParams:{evalVars:_3,evalResult:true}})}
,isc.A.toJSCode=function isc_c_XMLTools_toJSCode(_1,_2){isc.DMI.callBuiltin("xmlToJS",_1,_2)}
,isc.A.elementToObject=function isc_c_XMLTools_elementToObject(_1){if(_1==null)return null;var _2=this.getAttributes(_1);var _3=_1.getElementsByTagName(this.$pa);for(var i=0;i<_3.length;i++){var _5=_3[i];_2[_5.tagName]=this.getElementText(_5)}
return _2}
,isc.A.getLocalName=function isc_c_XMLTools_getLocalName(_1){if(!isc.Browser.isIE){var _2=_1.localName;if(_2==null)return _1.nodeName;return _2}
var _3=_1.nodeName,_4=_3.indexOf(this.$366);if(_4!=-1)return _3.substring(_4+1);return _3}
,isc.A.toJS=function isc_c_XMLTools_toJS(_1,_2,_3,_4,_5){if(_1==null)return null;if(isc.isAn.XMLDoc(_1))_1=_1.nativeDoc;if(_1.documentElement)_1=_1.documentElement;_5=_5||isc.emptyObject;if(isc.isAn.Array(_1)){var _6=[];for(var i=0;i<_1.length;i++){_6[i]=this.toJS(_1[i],_2,_3,_4,_5)}
return _6}
var _8,_9;var _10=this.getExplicitType(_1,_4);if(_4||!_3||(_3&&isc.DS.get(_10)==null)){if(_4){var _11=this.isRefElement(_1);if(_11)
{var _12=isc.Canvas.getById(_11);if(_12!=null)return _12}
var _13=this.firstElementChild(_1),_11=_13?this.isRefElement(_13):null;if(_11&&this.getElementChildren(_1).length==1)
{var _12=isc.Canvas.getById(_11);if(_12!=null)return _12}
if(!_10){var _14=_1.tagName;if(_14==this.$27c||isc.DS.get(_14))_10=_1.tagName}}
if(_10!=null&&_10==this.$27c){var _15=this.getElementChildren(_1);return this.toJS(_15,_2,_3,_4,_5)}
if(_10){if(isc.DS.get(_10)!=null){_3=isc.DS.get(_10)}else if(isc.SimpleType.getType(_10)!=null){return isc.SimpleType.validateValue(_10,this.getElementText(_1))}}}
if(_3&&_3.xmlToJS)return _3.xmlToJS(_1,_5);if(this.elementIsNil(_1))return null;if(_3){_9=_2||_3.getFieldNames();_8={};for(var i=0;i<_9.length;i++){var _16=_9[i],_17=_3.getField(_16);if(_17==null||(_17.valueXPath==null&&_17.getFieldValue==null))continue;var _18=_3.getFieldValue(_1,_16,_17);if(_18!=null){if(this.logIsDebugEnabled(this.$45s)){this.logDebug("valueXPath / getFieldValue() field: "+_3.ID+"."+_16+" on element: "+this.echoLeaf(_1)+" got value: "+_18,"xmlToJS")}
_8[_16]=_18}}}
_8=this.getAttributes(_1,_2,_8,_3!=null,_3);if(!this.$37g(_8)&&!this.hasElementChildren(_1))
{return this.getElementText(_1)}
if(_8[this.$367]&&_8[this.$367]=="xsd:Object"){delete _8[this.$367]}
var _15=_1.childNodes;if(this.logIsDebugEnabled(this.$45s)){this.logDebug("using DataSource: "+_3+" for complex element: "+this.echoLeaf(_1)+" childNodes: "+this.echoLeaf(_15)+" has attributes: "+this.$37g(_8),"xmlToJS")}
var _19=false;for(var i=0;i<_15.length;i++){var _20=_15[i];var _21=this.getLocalName(_20);if(this.isTextNode(_20))continue;_19=true;if(_2&&!_2.contains(_21))continue;var _17=_3?_3.getField(_21):null;if(_17&&(_17.valueXPath||_17.getFieldValue))continue;var _22;if(this.logIsInfoEnabled(this.$45s)){this.logInfo("dataSource: "+_3+", field: "+this.echoLeaf(_17)+(_17!=null?" type: "+_17.type:"")+", XML element: "+this.echoLeaf(_20),"xmlToJS")}
var _23=_20;if(_17&&_17.multiple){var _24=this.getElementChildren(_20);if(_24.length>0)_23=_24}
if(!_3||_17==null||_17.type==null){if(this.logIsDebugEnabled(this.$45s)){this.logDebug("applying schemaless transform at: "+(_3?_3.ID:"[schemaless]")+"."+_21,"xmlToJS")}
_22=this.toJS(_23,null,null,_4,_5)}else{var _25=_3.getSchema(_17.type);if(_25!=null){var _26=_17.propertiesOnly?{propertiesOnly:true}:_5;_22=this.toJS(_23,null,_25,_4,_26);if(this.logIsDebugEnabled(this.$45s)){this.logDebug("complexType field: "+this.echoLeaf(_17)+" got value: "+this.echoLeaf(_22),"xmlToJS")}}else{if(isc.isAn.Array(_23)){_22=[];for(var j=0;j<_23.length;j++){_22.add(_3.validateFieldValue(_17,this.getElementText(_23[j])))}}else{_22=_3.validateFieldValue(_17,this.getElementText(_23))}
if(this.logIsDebugEnabled(this.$45s)){this.logDebug("simpleType field: "+this.echoLeaf(_17)+" got value: "+this.echoLeaf(_22),"xmlToJS")}}}
if(_17&&_17.multiple){if(_22==null||isc.isA.emptyString(_22))_22=[];else if(!isc.isAn.Array(_22))_22=[_22]}
if(_8[_21]){if(!isc.isAn.Array(_8[_21]))_8[_21]=[_8[_21]];if(_17&&_17.multiple&&isc.isAn.Array(_22)){_8[_21].addList(_22)}else{_8[_21].add(_22)}}else{_8[_21]=_22}}
if(!_19){var _28=this.getElementText(_1),_29=_5.textContentProperty||(_3?_3.textContentProperty:"xmlTextContent");if(_3){_17=_3.getTextContentField();if(_17)_28=_3.validateFieldValue(_17,_28)}
if(_28!=null&&!isc.isAn.emptyString(_28)){_8[_29]=_28}}
if(_4&&_3&&(_3.instanceConstructor||_3.Constructor)){var _30=_3.instanceConstructor||_3.Constructor;if(_5!=null&&_5.propertiesOnly){_8._constructor=_30}else if(isc.ClassFactory.getClass(_30)!=null){return isc.ClassFactory.newInstance(_30,_8)}}
return _8}
,isc.A.getExplicitType=function isc_c_XMLTools_getExplicitType(_1,_2){if(_1==null||this.isTextNode(_1))return;var _3=this.getXSIAttribute(_1,this.$45t);if(_3){if(_3.contains(isc.colon))_3=_3.substring(_3.indexOf(isc.colon)+1);return _3}
if(_2)_3=_1.getAttribute("constructor");return _3}
,isc.A.isRefElement=function isc_c_XMLTools_isRefElement(_1){if(_1==null||this.isTextNode(_1)){return false}
var _2=_1.getAttribute(this.$71v);if(_2&&_1.attributes.length==1&&!this.hasElementChildren(_1))return _2}
,isc.A.toComponents=function isc_c_XMLTools_toComponents(_1,_2){if(isc.DS.get("Canvas")==null){this.logWarn("Can't find schema for Canvas - make sure you've loaded"+" component schema via <isomorphic:loadSystemSchema/> jsp tag"+" or by some other mechanism")}
if(isc.isA.String(_1)){var _3=this.parseXML(_1,true);if(_3.hasParseError()){this.logWarn("xml failed to parse xmlDoc, wrapping in root node");_3=this.parseXML("<isomorphicXML>"+_1+"</isomorphicXML>")}
_1=_3}
return this.toJS(_1,null,null,true,_2)}
,isc.A.getFieldValue=function isc_c_XMLTools_getFieldValue(_1,_2,_3,_4,_5){if(_1.ownerDocument==null)return _1[_2];_3=_3||(_4?_4.getField(_2):isc.emptyObject);try{var _6;if(_3.valueXPath){var _7=(_4?_4.getSchema(_3.type):isc.DS.get(_3.type));if(_7){var _8=isc.xml.selectNodes(_1,_3.valueXPath,_5),_9=isc.xml.toJS(_8,null,_7);if(!_3.multiple&&_9.length==1)_9=_9[0];return _9}else{_6=isc.xml.selectScalar(_1,_3.valueXPath,_5)}}else{_6=isc.xml.getXMLFieldValue(_1,_2)}
_4=_4||isc.DS.get("Object");_6=_4.validateFieldValue(_3,_6);return _6}catch(e){this.logWarn("error getting value for field: '"+_2+(_3.valueXPath?"', valueXPath: '"+_3.valueXPath:"")+"' in record: "+this.echo(_1)+"\r: "+this.echo(e)+this.getStackTrace());return null}}
,isc.A.getXMLFieldValue=function isc_c_XMLTools_getXMLFieldValue(_1,_2){var _3=_1.getAttribute(_2);if(_3!=null)return _3;var _4=_1.getElementsByTagName(_2)[0];if(_4==null)return null;return(isc.Browser.isIE?_4.text:_4.textContent)}
,isc.A.$37g=function isc_c_XMLTools__hasDataAttributes(_1){for(var _2 in _1){if(_2==this.$367)continue;return true}
return false}
,isc.A.getAttributes=function isc_c_XMLTools_getAttributes(_1,_2,_3,_4,_5){_3=_3||{};var _6;if(_2){if(!isc.isAn.Array(_2))_2=[_2];for(var i=0;i<_2.length;i++){var _8=_2[i];if(_4&&_3[_8]!==_6)continue;var _9=_1.getAttribute(_8);if(_9==null||isc.isAn.emptyString(_9))continue;if(_5&&_5.getField(_8)){_9=_5.validateFieldValue(_5.getField(_8),_9)}
_3[_8]=_9}
return _3}
var _10=_1.attributes;if(_10!=null){for(var i=0;i<_10.length;i++){var _11=_10[i],_8=_11.name;if(_4&&_3[_8]!==_6)continue;if(isc.startsWith(_8,this.$71f)&&_5&&_5.dropNamespaceDeclarations)continue;var _9=_11.value;if(_9==null||isc.isAn.emptyString(_9))continue;if(_5&&_5.getField(_8)){_9=_5.validateFieldValue(_5.getField(_8),_9)}
_3[_8]=_9}}
return _3}
,isc.A.getXSIAttribute=function isc_c_XMLTools_getXSIAttribute(_1,_2){var _3;if(isc.Browser.isOpera){for(var i=0;i<this.xsiNamespaces.length;i++){_3=_1.getAttributeNS(this.xsiNamespaces[i],_2);if(_3!=null)return _3}
return _3}
return _1.getAttribute(this.$45u[_2])}
,isc.A.elementIsNil=function isc_c_XMLTools_elementIsNil(_1){if(_1==null||!isc.isA.XMLNode(_1)||_1.nodeType!=1)return false;var _2=this.getXSIAttribute(_1,this.$45v);if(_2&&_2!=this.$18r&&_2!=this.$w0)return true;var _2=this.getXSIAttribute(_1,this.$45w);if(_2&&_2!=this.$18r&&_2!=this.$w0)return true;return false}
,isc.A.getElementText=function isc_c_XMLTools_getElementText(_1){if(this.elementIsNil(_1))return null;if(!_1)return null;var _2=_1.firstChild;if(!_2)return isc.emptyString;var _3=_2.data;if(isc.Browser.isMoz&&_3!=null&&_3.length>4000)return _1.textContent;return _3}
,isc.A.isTextNode=function isc_c_XMLTools_isTextNode(_1){if(_1==null)return false;var _2=_1.nodeType;return(_2==3||_2==4||_2==8)}
,isc.A.hasElementChildren=function isc_c_XMLTools_hasElementChildren(_1){return this.firstElementChild(_1)!=null}
,isc.A.firstElementChild=function isc_c_XMLTools_firstElementChild(_1){if(_1==null||(_1.hasChildNodes!=null&&_1.hasChildNodes()==false))return null;var _2=_1.childNodes;if(!_2)return null;var _3=_2.length;for(var i=0;i<_3;i++){var _5=_2[i];if(!this.isTextNode(_5))return _5}
return null}
,isc.A.setAttributes=function isc_c_XMLTools_setAttributes(_1,_2){var _3;for(var _4 in _2){var _5=_2[_4];if(_5==null){_1.removeAttribute(_4);continue}
if(isc.Browser.isIE&&(_5===true||_5===false)){_5=isc.emptyString+_5}
_1.setAttribute(_4,_2[_4])}}
,isc.A.$37h=function isc_c_XMLTools__makeIEDefaultNamespaces(_1,_2){var _3=isc.SB.create(),_4=_1.documentElement,_2=_2||isc.emptyObject,_5;if(!_2["default"]){_5=this.$45x(_4);if(_5)_3.append('xmlns:default="',_5,'" ')}
var _6=_1.documentElement.attributes;for(var i=0;i<_6.length;i++){var _8=_6[i],_9=_8.prefix;if(_9=="xmlns"&&_9!=_8.name){if(_2[_8.baseName]!=null)continue;_3.append(_8.name,'="',_8.value,'" ')}}
return _3.toString()}
,isc.A.$45x=function isc_c_XMLTools__deriveDefaultNamespace(_1){var _2=this.logIsDebugEnabled("xmlSelect");if((_1.prefix==null||isc.isAn.emptyString(_1.prefix))&&_1.namespaceURI)
{if(_2){this.logWarn("using docElement ns, prefix: "+_1.prefix,"xmlSelect")}
return _1.namespaceURI}else if(_1.firstChild){var _3
for(var i=0;i<_1.childNodes.length;i++){var _5=_1.childNodes[i];if(_5.nodeType==3)continue;var _6=_5.namespaceURI;if(!_6)break;if(_5.prefix==null||isc.isAn.emptyString(_5.prefix)){_3=_5.namespaceURI;break}}
if(_3!=null){if(_2){this.logDebug("using default namespace detected on child: "+_3,"xmlSelect")}}
if(_3==null&&_1.namespaceURI){_3=_1.namespaceURI;if(_2){this.logDebug("using document element's namespace as default namespace: "+_3,"xmlSelect")}}
if(!_3)_3="http://openuri.org/defaultNamespace";return _3}}
,isc.A.selectObjects=function isc_c_XMLTools_selectObjects(_1,_2,_3){if(isc.contains("|")){var _4=_2.split(/|/),_5=[];for(var i=0;i<_4.length;i++){_5.addList(this.selectObjects(_4[i],_1))}
return _5}
var _7=isc.isAn.Array(_1)?_1:[_1];if(_2!=isc.slash){if(isc.startsWith(_2,isc.slash))_2=_2.substring(1);var _8=_2.split(/[\/@]/);_7=this.$37i(_8,_7,isc.slash)}
if(_3&&_7.length<=1)return _7[0];return _7}
,isc.A.$37i=function isc_c_XMLTools__selectObjects(_1,_2,_3){var _4=_1[0];_1=_1.length>1?_1.slice(1):null;if(_2==null)return null;var _5,_6=_4,_7=_4.indexOf(this.$ho);if(_7!=-1){_6=_4.substring(0,_7);_5=_4.substring(_7+1,_4.length-1)}
var _8=[];for(var i=0;i<_2.length;i++){var _10=_2[i];if(_6!=isc.star){_10=_10[_6]}else{var _11=isc.getValues(_10);_10=[];for(var i=0;i<_11.length;i++){if(!isc.isAn.Array(_11[i]))_10.add(_11[i]);else _10.addList(_11[i])}}
if(_10==null)continue;if(!isc.isAn.Array(_10)){_8.add(_10)}else{_8.addList(_10)}}
if(_5){var _12=this.$37j(_8,_5);_8=_12}
if(_1==null||_1.length==0)return _8;_3+=_4+isc.slash;return this.$37i(_1,_8,_3)}
,isc.A.$37j=function isc_c_XMLTools__applyPredicateExpression(_1,_2){var _3=parseInt(_2);if(!isNaN(_3)){return[_1[_3-1]]}
if(_2=="last()")return[_1.last()];var _4=_2.match(/^([a-zA-Z_0-9:\-\.\(\)]*)\s*(<|>|!=|=|<=|>=|)\s*(.*)$/),_5,_6,_7;if(_4==null){if(!_2.match(/^[a-zA-Z_0-9:\-\.]*$/)){this.logWarn("couldn't parse predicate expression: "+_2);return null}
_5=_2}else{_5=_4[1],_6=_4[2],_7=_4[3]}
if(_6=="=")_6="==";if(_7=="true()")_7=true;else if(_7=="false()")_7=false;if(_5=="position()")_5="position";var _8=new Function("item,position","return "+(_5!="position"?"item.":"")+_5+(_6?_6+_7:""));var _9=[];for(var i=0;i<_1.length;i++){if(_8(_1[i],i+1))_9.add(_1[i])}
return _9}
,isc.A.selectNodes=function isc_c_XMLTools_selectNodes(_1,_2,_3,_4){if(isc.isA.String(_1)){_1=this.parseXML(_1)}
if(isc.Browser.isSafari&&(isc.Browser.isApollo||(isc.Browser.safariVersion<522)))
{this.$37a("XPath");return this.safariSelectNodes(_1,_2,_3,_4)}
if(isc.isAn.XMLDoc(_1)){return _1.selectNodes(_2,_3,_4)}
var _5=isc.timestamp();var _6=this.$37k(_1,_2,_3,_4);var _7=isc.timestamp();if(this.logIsInfoEnabled("xmlSelect")){this.logInfo("selectNodes: expression: "+_2+" returned "+this.echoLeaf(_6)+": "+(_7-_5)+"ms","xmlSelect")}
return _6}
,isc.A.safariSelectNodes=function isc_c_XMLTools_safariSelectNodes(_1,_2,_3,_4){var _5=[];if(!_2){return null}
var _6=_2.substring(_2.indexOf(":")+1);var _7;if(_6.endsWith("/*")){_7=true;_6=_6.substring(0,_6.indexOf("/*"))}
var _8=_1.getElementsByTagName(_6);if(_7&&_8.length>0){var _9=_8[0];_8=_9.childNodes}
for(var i=0;i<_8.length;i++){if(_8[i].nodeType==3)continue;_5.add(_8[i])}
if(_7&&_5.length==1)_5=_5[0];return _5}
,isc.A.$37l=function isc_c_XMLTools__generateNamespaces(_1,_2,_3){if(_1==null)return isc.emptyString;if(_2==null)_2=isc.getKeys(_1);var _4=isc.SB.create(),_3=(_3!=null?"\n"+_3:"");for(var i=0;i<_2.length;i++){var _6=_2[i];_4.append(_3," xmlns:",_6,'="',_1[_6],'"')}
return _4.toString()}
,isc.A.$53z=function isc_c_XMLTools__getDefaultNamespace(_1){var _2=_1.lookupNamespaceURI("");if(isc.Browser.isSafari&&(_2==null||_2=="")){_2=_1.getAttribute("xmlns")}
if(_2==null)_2=_1.namespaceURI;if(_2==null)_2="";return _2}
,isc.A.$37k=function isc_c_XMLTools__selectNodes(_1,_2,_3,_4){if(_1==null)return;var _5=_1.ownerDocument;if(_5==null&&_1.documentElement){_5=_1;_1=_5.documentElement}
if(_5==null)return null;if(isc.Browser.isIE){if(isc.Browser.version>5.5){_5.setProperty("SelectionLanguage","XPath");var _6=this.$37h(_5,_3);if(_3)_6+=this.$37l(_3);if(this.logIsDebugEnabled("xmlSelect")){this.logDebug("selectNodes: expression: "+_2+", using namespaces: "+_6,"xmlSelect")}
_5.setProperty("SelectionNamespaces",_6)}
if(_4)return _1.selectSingleNode(_2);var _7=_1.selectNodes(_2);return this.$37m(_7)}
var _8=_5.createNSResolver(_5.documentElement),_9=this.$53z(_5.documentElement);if(this.logIsDebugEnabled("xmlSelect")){this.logDebug("Using namespaces: "+isc.echo(_3)+", defaultNamespace: '"+_9+"'","xmlSelect")}
var _10=function(_12){if(_3&&_3[_12])return _3[_12];if(_12=="default")return _9;return _8.lookupNamespaceURI(_12)};var _11=_5.evaluate(_2,_1,_10,0,null);if(_4)return _11.iterateNext();return this.$37m(_11)}
,isc.A.$37m=function isc_c_XMLTools__nodeListToArray(_1){var _2=[];if(isc.Browser.isIE||_1.iterateNext==null){for(var i=0;i<_1.length;i++){_2.add(_1.item(i))}}else{var _4;while(_4=_1.iterateNext()){_2.add(_4)}}
return _2}
,isc.A.getElementChildren=function isc_c_XMLTools_getElementChildren(_1){var _2=[],_3=_1.childNodes;for(var i=0;i<_3.length;i++){var _5=_3[i];if(this.isTextNode(_5))continue;_2.add(_5)}
return _2}
,isc.A.selectString=function isc_c_XMLTools_selectString(_1,_2,_3){return this.selectScalar(_1,_2,_3)}
,isc.A.selectNumber=function isc_c_XMLTools_selectNumber(_1,_2,_3){return this.selectScalar(_1,_2,_3,true)}
,isc.A.selectScalar=function isc_c_XMLTools_selectScalar(_1,_2,_3,_4){if(isc.isA.String(_1))_1=this.parseXML(_1);if(isc.isAn.XMLDoc(_1))return _1.selectScalar(_2,_3,_4);var _5;if(isc.Browser.isSafari&&isc.Browser.isApollo||(isc.Browser.safariVersion<522)){var _6=_2.substring(_2.indexOf(":")+1);_5=_1.getElementsByTagName(_6)[0]}else{_5=this.selectNodes(_1,_2,_3,true)}
if(_5==null)return null;var _7=this.getElementText(_5);return _4?parseInt(_7):_7}
,isc.A.selectScalarList=function isc_c_XMLTools_selectScalarList(_1,_2,_3){if(isc.isA.String(_1))_1=this.parseXML(_1);if(isc.isAn.XMLDoc(_1))return _1.selectScalarList(_2,_3);var _4=this.selectNodes(_1,_2,_3);for(var i=0;i<_4.length;i++){_4[i]=_4[i].nodeValue}
return _4}
,isc.A.transformNodes=function isc_c_XMLTools_transformNodes(_1,_2){if(isc.isAn.XMLDoc(_1))_1=_1.nativeDoc;if(isc.isAn.XMLDoc(_2))_2=_2.nativeDoc;if(isc.Browser.isIE){return _1.transformNode(_2)}
var _3=new XSLTProcessor();_3.importStylesheet(_2);if(isc.Browser.isMoz&&this.mozAnchorBug&&isc.Browser.geckoVersion<20051107){var _4=document.implementation.createDocument("","test",null);var _5=_3.transformToFragment(_1,_4);return new XMLSerializer().serializeToString(_5)}
if(_1){var _6=_3.transformToDocument(_1);return new XMLSerializer().serializeToString(_6)}
return null}
,isc.A.serializeToString=function isc_c_XMLTools_serializeToString(_1){this.$37n=this.$37n||isc.xml.parseXML('<xsl:stylesheet version=\'1.0\' xmlns:xsl=\'http://www.w3.org/1999/XSL/Transform\'>\r'+'<xsl:output method="xml" indent="yes"/>\r'+'<xsl:strip-space elements="*"/>\r'+'<xsl:template match="/">\r'+'  <xsl:copy-of select="."/>\r'+'</xsl:template>\r'+'</xsl:stylesheet>');return this.transformNodes(_1,this.$37n)}
,isc.A.loadXMLSchema=function isc_c_XMLTools_loadXMLSchema(_1,_2,_3,_4,_5){_3=_3||{};_3.operationType=_3.operationType||"loadXMLSchema";this.loadWSDL(_1,_2,_3,_4,_5,true)}
,isc.A.loadWSDL=function isc_c_XMLTools_loadWSDL(_1,_2,_3,_4,_5,_6){if(!this.$37o){var _7=isc.Page.getIsomorphicClientDir()+"schema/schemaTranslator.xsl";_7=_7.replace(/https?:\/\/[^\/]*\//,"/");this.$37o="LOADING";isc.xml.loadXML(_7,function(_9,_10,_11){isc.xml.logDebug("schema translator loaded");if(isc.Browser.isMoz&&_11.xmlHttpRequest&&_11.xmlHttpRequest.responseXML)
{isc.xml.$37o=isc.XMLDoc.create(_11.xmlHttpRequest.responseXML)}else{isc.xml.$37o=_9}
isc.xml.loadWSDL(_1,_2,_3,_4,_5,_6)});return}
_3=_3||{};_3.operationType=_3.operationType||"loadWSDL";var _8={location:_1,callback:_2,autoLoadImports:_4,wsProperties:_5||{},returnSchemaSet:_6};isc.xml.loadXML(_1,function(_9,_10,_11,_12){_8.rpcResponse=_11;_8.rpcRequest=_12;isc.xml.$37p(_9,_8)},_3)}
,isc.A.loadWSDLFromXML=function isc_c_XMLTools_loadWSDLFromXML(_1,_2,_3,_4,_5){if(isc.isA.String(_1))_1=isc.xml.parseXML(_1);this.$37p(_1,{callback:_2,autoLoadImports:_3,wsProperties:_4,returnSchemaSet:_5})}
,isc.A.$37p=function isc_c_XMLTools__loadSchemaReply(_1,_2){if(!isc.isAn.XMLDoc(this.$37o)){this.logInfo("deferred schema translator, schema translator not loaded","xmlComm");isc.Timer.setTimeout({methodName:"$37p",target:this,args:[_1,_2]});return}
this.logInfo("transforming schema: "+this.echoLeaf(_1)+" with translator "+this.echoLeaf(this.$37o),"xmlComm");var _3=this.transformNodes(_1,this.$37o);if(this.logIsDebugEnabled("xmlComm")){this.logWarn("XML service definition is: \n"+_3)}
var _4=_2.wsProperties,_5=_4.initiator;if(_4.captureXML){_4.xmlSource=_3;if(_5)_5.addImportXMLSource(_3,_2.location)}
if(this.useClientXML){var _1=isc.xml.parseXML(_3),_6=_1?this.$37m(_1.documentElement.childNodes):null,_7=_6?this.toJS(_6,null,null,true):null;this.$37q(_2);return}
this.logInfo("about to call serverToJS with: "+this.echoLeaf(_3)+", callback: "+this.echo(_2.callback),"xmlComm");this.serverToJS(_3,function(){isc.Log.logWarn("serverToJS returned");isc.xml.$37q(_2)})}
,isc.A.$37q=function isc_c_XMLTools__loadSchemaToJSReply(_1){var _2;if(_1.returnSchemaSet){_2=isc.SchemaSet.$37r}else{_2=isc.WebService.$37r||isc.SchemaSet.$37r}
isc.WebService.$37r=isc.SchemaSet.$37r=null;if(!_2&&_1.rpcRequest.willHandleError==false)return;if(_2)_2.location=_1.location;if(_2&&_1.wsProperties)_2.setProperties(_1.wsProperties);var _3=(isc.isA.WebService(_2)?"service":"schemaSet")+",rpcRequest";var _4=[_2,_1.rpcRequest];if(_1.autoLoadImports&&_2&&_2.loadImports){var _5=this;_2.loadImports(function(){_5.$41k(_1.callback,_3,_4)})}else{this.$41k(_1.callback,_3,_4)}}
,isc.A.$41k=function isc_c_XMLTools__completeLoad(_1,_2,_3){this.fireCallback(_1,_2,_3)}
,isc.A.getCompleteSource=function isc_c_XMLTools_getCompleteSource(_1,_2,_3){var _4=_1.importSources;if(!_4)return"";_4=_4.getProperty("xmlText");_4.unshift(_1.xmlSource);_4=this.map("trimXMLStart",_4);var _5=_4.join("\n");if(_3){this.fireCallback(_2,"source",[_5]);return}
this.toJSCode(_5,function(_6,_7){this.fireCallback(_2,"source",[_7])})}
);isc.B._maxIndex=isc.C+53;isc.xml=isc.XML=isc.XMLTools;isc.defineClass("DataSource");isc.DS=isc.DataSource;isc.A=isc.DataSource;isc.A.dataSourceObjectSuffix="DS";isc.A._dataSources={};isc.A.$54v={};isc.A.$54w={};isc.A.$532="element";isc.A.$45t="type";isc.A.TABLE="table";isc.A.VIEW="view";isc.A.loaderURL="[ISOMORPHIC]/DataSourceLoader";isc.A.$37t="<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/' ";isc.A.$37w="</soap:Envelope>";isc.A.$37u="<soap:Header>";isc.A.$51y="</soap:Header>";isc.A.$37v="<soap:Body";isc.A.$51z="</soap:Body>";isc.A=isc.DataSource;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$748="ref:";isc.A.serializeTimeAsDatetime=false;isc.A.$78d=[];isc.A.maxResponsesToCache=100;isc.A.offlineMessage="This data not available while offline";isc.B.push(isc.A.isLoaded=function isc_c_DataSource_isLoaded(_1){if(!_1)return false;if(isc.isA.DataSource(_1)||this._dataSources[_1])return true;return false}
,isc.A.getDataSource=function isc_c_DataSource_getDataSource(_1,_2,_3,_4){if(!_1)return null;if(isc.isA.DataSource(_1))return _1;if(isc.startsWith(_1,this.$748)){_1=_1.substring(4)}
if(_4&&isc.WebService){if(_4==isc.DS.$532)return this.$54v[_1];if(_4==isc.DS.$45t)return this.$54w[_1];return null}
var _5=this._dataSources[_1];if(!_5){_5=this.$37x(_1,_2);if(_5)_5.ID=_1}
if(_5){if(_2){this.fireCallback(_2,"ds",[_5],_5)}
return _5}
if(_2){this.loadSchema(_1,_2,_3)}
return null}
,isc.A.loadSchema=function isc_c_DataSource_loadSchema(_1,_2,_3){this.logWarn("Attempt to load schema for DataSource '"+_1+"'. This dataSource cannot be found. To load DataSources from the server without "+"explicit inclusion in your application requires optional SmartClient server "+"support - not present in this build.");return null}
,isc.A.get=function isc_c_DataSource_get(_1,_2,_3,_4){return this.getDataSource(_1,_2,_3,_4)}
,isc.A.$37x=function isc_c_DataSource__loadDataSource(_1,_2){if(_2)return null;if(_1!=isc.auto&&this.logIsDebugEnabled()){this.logDebug("isc.DataSource '"+_1+"' not present")}
return null}
,isc.A.getRegisteredDataSources=function isc_c_DataSource_getRegisteredDataSources(){return isc.getKeys(this._dataSources)}
,isc.A.isRegistered=function isc_c_DataSource_isRegistered(_1){if(this._dataSources[_1])return true;return false}
,isc.A.getForeignFieldName=function isc_c_DataSource_getForeignFieldName(_1){var _2=_1.foreignKey,_3=_2.indexOf(".");if(_3==-1)return _2;return _2.substring(_3+1)}
,isc.A.getForeignDSName=function isc_c_DataSource_getForeignDSName(_1,_2){var _3=_1.foreignKey,_4=_3.indexOf(".");if(_4==-1)return isc.isA.String(_2)?_2:_2.ID;return _3.substring(0,_4)}
,isc.A.registerDataSource=function isc_c_DataSource_registerDataSource(_1){if(this.logIsInfoEnabled()){this.logInfo("Registered new isc.DataSource '"+_1.ID+"'")}
if(_1.ID){var _2=this._dataSources[_1.ID];if(!_2||!_1.schemaNamespace){this._dataSources[_1.ID]=_1}}
if(isc.Schema&&isc.isA.Schema(_1)){if(isc.isAn.XSElement(_1))this.$54v[_1.ID]=_1;else if(isc.isAn.XSComplexType(_1))this.$54w[_1.ID]=_1;return}
var _3=_1.getLocalFields(true);var _4=this.$37z=(this.$37z||{});for(var _5 in _3){var _6=_3[_5];if(_6.foreignKey==null)continue;var _7=this.getForeignDSName(_6,_1);if(isc.DS.isRegistered(_7)){isc.DS.get(_7).addChildDataSource(_1)}else{if(_4[_7]==null){_4[_7]=[]}
_4[_7].add(_1)}}
var _8=_4[_1.ID];if(_8!=null){_1.map("addChildDataSource",_8);_4[_1.ID]=null}
var _9=this.$370=this.$370||{};if(_1.childRelations){for(var i=0;i<_1.childRelations.length;i++){var _11=_1.childRelations[i],_12=_11.dsName,_13=isc.DS.get(_12);if(_13){this.$371(_1,_13,_11)}else{if(_9[_12]==null){_9[_12]=[]}
_11.parentDS=_1.ID;_9[_12].add(_11)}}}
var _14=_9[_1.ID];if(_14){for(var i=0;i<_14.length;i++){var _11=_14[i];this.$371(isc.DS.get(_11.parentDS),_1,_11)}}}
,isc.A.$371=function isc_c_DataSource__addChildRelation(_1,_2,_3){_1.addChildDataSource(_2);if(!_3.fieldName)return;var _4=_2.getField(_3.fieldName);if(!_4.foriegnKey){_4.foreignKey=_1.ID+"."+_1.getPrimaryKeyFieldNames()[0]}}
,isc.A.getInheritanceDistance=function isc_c_DataSource_getInheritanceDistance(_1,_2){var _3=isc.ClassFactory.getClass(_1),_4=isc.ClassFactory.getClass(_2);if(_3==null||_4==null){this.logWarn("Invalid superclass and/or subclass argument provided");return-1}
if(!_4.isA(_1)){this.logWarn(_2+" is not a subclass of "+_1);return-1}
for(var _5=0;_4!=_3;_5++){_4=_4.getSuperClass()}
return _5}
,isc.A.isSimpleTypeValue=function isc_c_DataSource_isSimpleTypeValue(_1){if(_1!=null&&(!isc.isAn.Object(_1)||isc.isA.Date(_1)))return true;return false}
,isc.A.getNearestSchema=function isc_c_DataSource_getNearestSchema(_1){if(_1==null)return null;var _2;if(isc.isA.String(_1))_2=_1;else{_2=isc.isAn.Instance(_1)?_1.getClassName():_1._constructor||_1.type||_1.$schemaId}
var _3=isc.DS.get(_2);var _4=isc.ClassFactory.getClass(_2);if(_4!=null){var _5=null;while(_3==null&&(_4=_4.getSuperClass())!=null&&_4!=_5)
{_3=isc.DS.get(_4.getClassName());_5=_4}}
return _3||isc.DS.get("Object")}
,isc.A.getNearestSchemaClass=function isc_c_DataSource_getNearestSchemaClass(_1){if(_1==null)return null;var _2;while(_2==null){var _1=isc.DS.get(_1);if(_1==null)return null;_2=isc.ClassFactory.getClass(_1._constructor||_1.Constructor||_1.type);if(_2!=null)return _2;_1=_1.inheritsFrom;if(!_1)return null}
return null}
,isc.A.$372=function isc_c_DataSource__getStandardOperationType(_1){switch(_1){case"fetch":case"select":case"filter":return"fetch";case"add":case"insert":return"add";case"update":return"update";case"remove":case"delete":return"remove";default:return _1}}
,isc.A.isClientOnly=function isc_c_DataSource_isClientOnly(_1){if(isc.isA.String(_1))_1=this.getDataSource(_1);if(!_1)return false;return _1.clientOnly}
,isc.A.makeDefaultOperation=function isc_c_DataSource_makeDefaultOperation(_1,_2,_3){var _4=isc.rpc.app();if(isc.isA.DataSource(_1))_1=_1.ID;if(!_1){_1="auto"}else if(_3){var _5=isc.DataSource.get(_1);if(isc.isA.DataSource(_5)){if(!_5.createdOperations)_5.createdOperations={};var _6=_5.createdOperations[_3];if(_6==null){_6={ID:_3,dataSource:_1,type:_2,filterType:"paged",loadDataOnDemand:true};_5.createdOperations[_3]=_6}
return _6}}
if(_4.operations==null)_4.operations={};_3=_3||_1+"_"+_2;var _6=_4.operations[_3];if(_6==null){_6={ID:_3,dataSource:_1,type:_2,filterType:"paged",loadDataOnDemand:true,source:"auto"};_4.operations[_3]=_6}
return _6}
,isc.A.handleUpdate=function isc_c_DataSource_handleUpdate(_1,_2){if(!this.isUpdateOperation(_2.operationType))return;var _3=this.get(_2.dataSource);_3.updateCaches(_1,_2)}
,isc.A.isUpdateOperation=function isc_c_DataSource_isUpdateOperation(_1){if(_1=="add"||_1=="update"||_1=="remove"||_1=="replace"||_1=="delete"||_1=="insert")return true}
,isc.A.getUpdatedData=function isc_c_DataSource_getUpdatedData(_1,_2,_3){var _4=this.get(_1.dataSource);return _4.getUpdatedData(_1,_2,_3)}
,isc.A.filterCriteriaForFormValues=function isc_c_DataSource_filterCriteriaForFormValues(_1){if(isc.DS.isAdvancedCriteria(_1))return _1;var _2={};for(var _3 in _1){var _4=_1[_3];if(_4==null||isc.is.emptyString(_4))continue;if(isc.isAn.Array(_4)){if(_4.length==0)continue;for(var i=0;i<_4.length;i++){var _6=_4[i];if(isc.isAn.emptyString(_6))continue}}
_2[_3]=_4}
return _2}
,isc.A.checkEmptyCriteria=function isc_c_DataSource_checkEmptyCriteria(_1,_2){if((_2||this.isAdvancedCriteria(_1))&&_1.criteria){if(_1.criteria.length==0)return null;for(var i=_1.criteria.length-1;i>=0;i--){var _4=_1.criteria[i],_5=false;if(!_4)_5=true;else{if(!_4.criteria){if(isc.isA.emptyObject(_4))_5=true}else{var _6=this.checkEmptyCriteria(_4,true);if(_6)_1.criteria[i]=_6;else _5=true}}
if(_5)_1.criteria.removeAt(i)}}
return _1}
,isc.A.load=function isc_c_DataSource_load(_1,_2,_3){if(!isc.isAn.Array(_1))_1=[_1];if(_1.length<=0){this.logWarn("No DataSource IDs passed in.");return}
var _4=[];for(var i=0;i<_1.length;i++){if(!this.isLoaded(_1[i])||_3)_4.add(_1[i])}
var _6=_4.join(","),_7=isc.DataSource.loaderURL+"?dataSource="+_6,_8=_1;;if(_4.length>0){isc.RPCManager.send(null,function(_9,_10,_11){if(_9.httpResponseCode==404){isc.warn("The DataSourceLoader servlet is not installed.");return null}
eval(_10);if(_2)this.fireCallback(_2,["dsID"],[_8])},{actionURL:_7,httpMethod:"GET",willHandleError:true})}else{this.logWarn("DataSource(s) already loaded: "+_1.join(",")+"\nUse forceReload to reload such DataSources");if(_2)this.fireCallback(_2,["dsID"],[_8])}}
,isc.A.getSortBy=function isc_c_DataSource_getSortBy(_1){if(!isc.isA.Array(_1))_1=[_1];var _2=[];for(var i=0;i<_1.length;i++){var _4=_1.get(i);_2.add((!Array.shouldSortAscending(_4.direction)?"-":"")+_4.property)}
return _2}
,isc.A.getSortSpecifiers=function isc_c_DataSource_getSortSpecifiers(_1){if(!isc.isA.Array(_1))_1=[_1];var _2=[];for(var i=0;i<_1.length;i++){var _4=_1.get(i),_5="ascending",_6=_4;if(_4.substring(0,1)=="-"){_5="descending";_6=_4.substring(1)}
_2.add({property:_6,direction:_5})}
return _2}
,isc.A.isAdvancedCriteria=function isc_c_DataSource_isAdvancedCriteria(_1,_2){if(!_1)return false;if(!_2){return(_1&&_1._constructor=="AdvancedCriteria")}
if(!isc.isA.DataSource(_2))_2=this.get(_2);if(_1._constructor=="AdvancedCriteria")return true;var _3=false;if(_2.getField(_1.fieldName)&&_2.getSearchOperator(_1.operator))
{_3=true}
else if(_2.getField("fieldName")||_2.getField("operator"))_3=false;else if(_1.fieldName&&_1.value&&(_1.operator&&_2.getSearchOperator(_1.operator))){_3=true}
else{var _4;if(_1.operator!=_4){var _5=_2.getSearchOperator(_1.operator);if(_5!=null&&(_5.isAnd||_5.isOr)){_3=true}}}
this.logInfo("Criteria object:"+this.echo(_1)+" not explicitly marked as AdvancedCriteria"+(_3?" but treating as Advanced based on format.":" - treating as SimpleCriteria.","AdvancedCriteria"));return _3}
,isc.A.copyCriteria=function isc_c_DataSource_copyCriteria(_1){if(_1==null)return null;return isc.clone(_1)}
,isc.A.getCriteriaFields=function isc_c_DataSource_getCriteriaFields(_1,_2,_3){if(_2&&!isc.isA.DataSource(_2))_2=this.get(_2);if(_3||(_2?_2.isAdvancedCriteria(_1):this.isAdvancedCriteria(_1)))
{var _4=[];this.$74s(_1,_4);return _4}
return isc.getKeys(_1)}
,isc.A.$74s=function isc_c_DataSource__getAdvancedCriteriaFields(_1,_2){if(_1.criteria){for(var i=0;i<_1.criteria.length;i++){isc.DS.$74s(_1.criteria[i],_2)}}else{_2.add(_1.fieldName)}}
,isc.A.cacheResponse=function isc_c_DataSource_cacheResponse(_1,_2){if(isc.Offline){var _3=isc.Offline.serialize(isc.Offline.trimRequest(_1));var _4=this.$78d.findIndex("requestKey",_3);if(_4!=-1){this.$78d.set(_4,_2)}else{if(this.$78d.length>=this.maxResponsesToCache){this.$78d.removeAt(0)}
this.$78d.add({requestKey:_3,dsResponse:_2})}}}
,isc.A.getCachedResponse=function isc_c_DataSource_getCachedResponse(_1){if(isc.Offline){var _2=isc.Offline.serialize(isc.Offline.trimRequest(_1));return this.$78d.find("requestKey",_2)}
return null}
,isc.A.getPathValue=function isc_c_DataSource_getPathValue(_1,_2){if(_1==null)return null;var _3=_2.split("."),_4=_1;for(var i=0;i<_3.length;i++){_4=_4[_3[i]];if(_4==null)break}
return _4}
,isc.A.setPathValue=function isc_c_DataSource_setPathValue(_1,_2,_3){if(!_2.contains("."))return _1[_2]=_3;_2=_2.split(".");var _4=_1;for(var i=0;i<_2.length-1;i++){var _6=_2[i];if(!isc.isAn.Object(_4[_6])){_4[_6]={}}
_4=_4[_6]}
_4[_2.last()]=_3}
,isc.A.getCombinedDataSourceFields=function isc_c_DataSource_getCombinedDataSourceFields(_1){var _2=[];for(var i=0;i<_1.length;i++){var _4=_1[i];if(isc.isA.String(_4))_4=isc.DataSource.get(_4);if(_4==null){this.logWarn("getCombinedDataSourceFields() - unable to locate dataSource:"+_1[i]);continue}
var _5=_4.getID(),_6=isc.getKeys(_4.getFields());for(var j=0;j<_6.length;j++){_2[_2.length]=_5+"."+_6[j]}}
return _2}
,isc.A.getFieldFromDataSources=function isc_c_DataSource_getFieldFromDataSources(_1,_2){var _3=this.getDataSourceForField(_1,_2);if(_3==null){this.logWarn("getFieldFromDataSources() - unable to find dataSource "+" from combined field ID "+_1);return null}
var _4=_1.split("."),_5=_4[0],_6=_4[1];return _3.getField(_6)}
,isc.A.getDataSourceForField=function isc_c_DataSource_getDataSourceForField(_1,_2){var _3=_1.split("."),_4=_3[0],_5=_3[1];if(_4==null||_5==null){this.logWarn("getDataSourceForField() passed invalid field ID:"+_1+". Expected format is to use dot-notation to indicate the dataSource plus "+"fieldName - for example 'countryDS.countryCode'");return null}
var _6;if(_2==null)_6=isc.DataSource.get(_4);else{if(!isc.isAn.Array(_2))_2=[_2];for(var i=0;i<_2.length;i++){if(_2[i].getID()==_4){_6=_2[i];break}}}
return _6}
);isc.B._maxIndex=isc.C+37;isc.A=isc.DataSource.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.addGlobalId=true;isc.A.dataFormat="iscServer";isc.A.callbackParam="callback";isc.A.preventHTTPCaching=true;isc.A.sendExtraFields=true;isc.A.transformResponseToJS=true;isc.A.supportsRequestQueuing=true;isc.A.copyLocalResults=true;isc.A.criteriaPolicy="dropOnShortening";isc.A.showPrompt=true;isc.A.autoDeriveTitles=true;isc.A.canMultiSort=true;isc.A.nullStringValue="";isc.A.nullIntegerValue=0;isc.A.nullFloatValue=0.0;isc.A.nullBooleanValue=false;isc.A.nullDateValue=new Date(0);isc.A.cacheMaxAge=60;isc.A.cacheLastFetchTime=0;isc.A.autoCacheAllData=false;isc.A.autoConvertRelativeDates=true;isc.B.push(isc.A.setCacheAllData=function isc_DataSource_setCacheAllData(_1){if(!_1){if(this.cacheAllData==true){if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("setCacheAllData(false): clearing the cache and any "+"deferred requests","cacheAllData")}
this.cacheAllData=false;delete this.$85n;this.invalidateCache();this.clearDeferredRequests()}}else{if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("setCacheAllData(true): invalidate the cache","cacheAllData")}
this.cacheAllData=true;this.$85n=isc.timestamp();this.invalidateCache()}}
,isc.A.cacheNeedsRefresh=function isc_DataSource_cacheNeedsRefresh(){var _1=new Date().getTime(),_2=((_1-this.cacheLastFetchTime)/1000),_3=(this.cacheLastFetchTime==0||_2>this.cacheMaxAge);if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("cacheNeedsRefresh returns "+_3,"cacheAllData")}
return _3}
,isc.A.setCacheData=function isc_DataSource_setCacheData(_1,_2){if(this.cacheAllData||this.clientOnly){if(_2){if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("setCacheData: invalidating the cache","cacheAllData")}
this.invalidateCache();this.clearDeferredRequests()}
this.cacheData=this.testData=_1;this.cacheLastFetchTime=new Date().getTime();if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("setCacheData: cacheData has been set","cacheAllData")}}}
,isc.A.clearDeferredRequests=function isc_DataSource_clearDeferredRequests(_1){if(!this.$498)return;_1=_1||"any";if(!isc.isAn.Array(_1))_1=[_1];if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("clearDeferredRequests: "+this.$498.length+" requests, "+"clearing those of type "+isc.echoAll(_1),"cacheAllData")}
if(_1.contains("any"))delete this.$498;else{if(this.$498){var _2=this.$498;for(var i=_2.length;i>=0;i--){var _4=_2[i].operationType||"fetch";if(_1.contains(_4))this.$498.removeAt(i)}
if(this.$498.length==0)delete this.$498}}}
,isc.A.processDeferredRequests=function isc_DataSource_processDeferredRequests(){if(!this.$498)return;if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("processDeferredRequests: processing "+this.$498.length+" deferred requests","cacheAllData")}
var _1=this.$498;this.clearDeferredRequests();for(var i=0;i<_1.length;i++){this.sendDSRequest(_1[i])}}
,isc.A.invalidateCache=function isc_DataSource_invalidateCache(){if(!this.cacheAllData&&!this.clientOnly)return;if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("invalidateCache: invalidating client-side cache","cacheAllData")}
delete this.cacheData;delete this.testData;this.cacheLastFetchTime=0;if(this.cacheResultSet){this.cacheResultSet.destroy();delete this.cacheResultSet}}
,isc.A.setClientOnly=function isc_DataSource_setClientOnly(_1){if(_1){this.clientOnly=true
if(this.cacheAllData){if(this.cacheResultSet){if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("setClientOnly: sourcing from client-cache","cacheAllData")}
this.cacheData=this.testData=this.cacheResultSet.getAllRows()}}else{this.clearDeferredRequests();this.invalidateCache();this.performDSOperation("fetch")}}}
,isc.A.hasAllData=function isc_DataSource_hasAllData(){if(this.cacheResultSet)return this.cacheResultSet.lengthIsKnown();else return false}
,isc.A.convertRelativeDates=function isc_DataSource_convertRelativeDates(_1,_2,_3,_4){if(!_1)return null;if(!this.isAdvancedCriteria(_1)&&_1.operator==null){return _1}
var _5=isc.RelativeDate,_6=isc.clone(_1);_4=_4||new Date();if(_3==null)_3=isc.DateChooser?isc.DateChooser.firstDayOfWeek:0;if(_6.criteria&&isc.isAn.Array(_6.criteria)){var _7=_6.criteria;for(var i=_7.length-1;i>=0;i--){var _9=_7[i];if(!_9){if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Removing NULL subcriteria...","relativeDates")}
_6.criteria.removeAt(i)}else{if(_9.criteria&&isc.isAn.Array(_9.criteria)){if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Calling convertRelativeDates from convertRelativeDates "+"- data is:\n\n"+isc.echoFull(_9)+"\n\n"+"criteria is: \n\n"+isc.echoFull(_1),"relativeDates")}
_6.criteria[i]=this.convertRelativeDates(_9,_2,_3,_4);if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Called convertRelativeDates from convertRelativeDates "+"- data is\n\n"+isc.echoFull(_6.criteria[i]),"relativeDates")}}else{_6.criteria[i]=this.mapRelativeDate(_9,_4)}}}}else{_6=this.mapRelativeDate(_6,_4)}
if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Returning from convertRelativeDates - result is:\n\n"+isc.echoFull(_6)+"\n\n"+"original criteria is: \n\n"+isc.echoFull(_1),"relativeDates")}
return _6}
,isc.A.mapRelativeDate=function isc_DataSource_mapRelativeDate(_1,_2){var _3=isc.addProperties({},_1),_4,_5;_2=_2||new Date();var _6=_3.fieldName,_7=_6?this.getField(_6):null,_8=_7?_7.type:null;var _9=isc.SimpleType.inheritsFrom(_8,"date")&&!isc.SimpleType.inheritsFrom(_8,"datetime");if(_3.value&&isc.isAn.Object(_3.value)&&_3.value._constructor=="RelativeDate")
{_5=_3.value.value;_3.value=isc.DateUtil.getAbsoluteDate(_5,_2,_3.value.rangePosition,_9)}else{if(_3.start&&isc.isAn.Object(_3.start)&&_3.start._constructor=="RelativeDate")
{_5=_3.start.value;_3.start=_4=isc.DateUtil.getAbsoluteDate(_5,_2,"start",_9)}
if(_3.end&&isc.isAn.Object(_3.end)&&_3.end._constructor=="RelativeDate")
{_5=_3.end.value;_3.end=isc.DateUtil.getAbsoluteDate(_5,_2,"end",_9)}}
return _3}
);isc.B._maxIndex=isc.C+10;isc.A=isc.DataSource.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$41v="Action";isc.A.resultBatchSize=150;isc.A.$2j=[];isc.A.emptyExportMessage="You are attempting to export an empty dataset";isc.A.canExport=true;isc.A.defaultTitleFieldNames=["title","label","name","id"];isc.A.textContentProperty="xmlTextContent";isc.A.$dq="Defaults";isc.A.$dr="Properties";isc.A.$375="name";isc.A.$45t="type";isc.A.dropUnknownCriteria=true;isc.A.$45y="startsWith";isc.A.$19q="substring";isc.A.$50i="exact";isc.A.$50j="iscServer";isc.B.push(isc.A.init=function isc_DataSource_init(){if(this.serverType=="sql")this.dataFormat="iscServer";if(this.dataFormat=="iscServer"&&(this.serviceNamespace!=null||this.recordXPath!=null))this.dataFormat="xml";this.canQueueRequests=(this.dataFormat=="iscServer"||this.clientOnly);if(this.testData&&!this.cacheData)this.cacheData=this.testData;else if(this.clientOnly&&this.cacheData&&!this.testData)
this.testData=this.cacheData;if(this.ID==null&&this.id!=null)this.ID=this.id;if(this.name==null)this.name=this.ID;var _1=isc.DS.get(this.ID);if(_1&&_1.builtinSchema)return _1;var _2=window[this.ID];if(this.addGlobalId&&this.addGlobalId!=isc.$ae&&(!_2||(!isc.isA.ClassObject(_2)&&isc.isA.DataSource(_2))))
{isc.ClassFactory.addGlobalID(this)}
var _3=this.fields;if(isc.isAn.Array(_3)){var _4={};for(var i=0;i<_3.length;i++){var _6=_3[i];if(_6.includeFrom!=null){var _7=_6.includeFrom.split(".");if(_7==null||_7.length!=2){this.logWarn("Field has includeFrom specified as :"+_6.includeFrom+" format not understood - clearing this property");_6.includeFrom=null}else{if(_6.name==null)_6.name=_7[1]}}
if(_4[_6.name]!=null){this.logWarn("field.name collision: first field: "+this.echo(_4[_6.name])+", discarded field: "+this.echo(_6));continue}
_4[_6.name]=_6}
this.fields=_4}
if(this.dataSourceType==isc.DataSource.VIEW)this.initViewSources();isc.DataSource.registerDataSource(this)}
,isc.A.destroy=function isc_DataSource_destroy(){var _1=this.ID,_2=isc.DS;if(_1&&this==window[_1])window[_1]=null;if(_2._dataSources[_1]==this)_2._dataSources[_1]=null;if(_2.$54v[_1]==this)_2.$54v[_1]=null;if(_2.$54w[_1]==this)_2.$54w[_1]=null}
,isc.A.getResultSet=function isc_DataSource_getResultSet(_1){var _2=isc.ClassFactory.getClass(this.resultSetClass||isc.ResultSet);if(!isc.isA.Class(_2)){this.logWarn("getResultSet(): Unrecognized 'resultSetClass' property:"+_2+", returning a standard isc.ResultSet.");_2=isc.ResultSet}
return _2.create(_1,{$31k:true})}
,isc.A.dataChanged=function(dsResponse,dsRequest){}
,isc.A.updateCaches=function isc_DataSource_updateCaches(_1,_2){if(_2==null){_2={operationType:_1.operationType,dataSource:this};if(_1.clientContext!=null){_2.clientContext=_1.clientContext}}else{_2.dataSource=_2.dataSource||_1.dataSource||this}
var _3=_1.data,_4=_1.invalidateCache,_5=_1.httpResponseCode;if(!_3&&!_4&&!(_5>=200&&_5<300)){this.logWarn("Empty results returned on '"+_2.operationType+"' on dataSource '"+_2.dataSource+"', unable to update resultSet(s) on DataSource "+this.ID+".  Return affected records to ensure cache consistency.");return}
if(this.cacheAllData&&this.hasAllData()){this.invalidateCache()}
this.dataChanged(_1,_2)}
,isc.A.getLegalChildTags=function isc_DataSource_getLegalChildTags(){var _1=this.getFieldNames(),_2=[];for(var i=0;i<_1.length;i++){if(this.fieldIsComplexType(_1[i]))_2.add(_1[i])}
return _2}
,isc.A.getOperationBinding=function isc_DataSource_getOperationBinding(_1,_2){if(_1==null||this.operationBindings==null)return this;if(isc.isAn.Object(_1)){var _3=_1;_1=_3.operationType;_2=_3.operationId}
var _4;if(_2){var _5=this.operationBindings.find("operationId",_2);if(_5)return _5}
if(_1){var _5=this.operationBindings.find("operationType",_1);if(_5)return _5}
return this}
,isc.A.getDataFormat=function isc_DataSource_getDataFormat(_1,_2){return this.getOperationBinding(_1,_2).dataFormat||this.dataFormat}
,isc.A.shouldBypassCache=function isc_DataSource_shouldBypassCache(_1,_2){var _3=this.getOperationBinding(_1,_2).preventHTTPCaching;if(_3==null)_3=this.preventHTTPCaching;return _3}
,isc.A.copyRecord=function isc_DataSource_copyRecord(_1){if(_1==null)return null;var _2={};var _3=this.getFieldNames(false);for(var i=0;i<_3.length;i++){var _5=_3.get(i);var _6=_1[_5];var _7=this.getField(_5);if(isc.isA.Date(_6)){var _8=new Date();_8.setTime(_6.getTime());_8.logicalDate=_6.logicalDate;_8.logicalTime=_6.logicalTime;_2[_5]=_8}else if(isc.isAn.Array(_6)&&_7.multiple==true&&_7.type==null)
{var _9=[];for(var j=0;j<_6.length;j++){if(isc.isA.Date(_6[j])){var _8=new Date();_8.setTime(_6[j].getTime());_8.logicalDate=_6[j].logicalDate;_8.logicalTime=_6[j].logicalTime;_9[j]=_8}else{_9[j]=_6[j]}}
_2[_5]=_9}else{_2[_5]=_6}}
return _2}
,isc.A.copyRecords=function isc_DataSource_copyRecords(_1){if(_1==null)return null;var _2=[];for(var i=0;i<_1.length;i++){var _4=_1[i];var _5=this.copyRecord(_4);_2[i]=_5}
return _2}
,isc.A.transformRequest=function isc_DataSource_transformRequest(_1){return _1.data}
,isc.A.getUpdatedData=function isc_DataSource_getUpdatedData(_1,_2,_3){var _4=_2.data;if(_3&&_2.status==0&&(_4==null||(isc.isA.Array(_4)&&_4.length==0)||isc.isAn.emptyString(_4)))
{this.logInfo("dsResponse for successful operation of type "+_1.operationType+" did not return updated record[s]. Using submitted request data to update"+" ResultSet cache.","ResultSet");var _5=_1.data;if(_1.data&&isc.isAn.Object(_1.data)){if(_1.operationType=="update"){_4=isc.addProperties({},_1.oldValues);if(isc.isAn.Array(_5)){_4=isc.addProperties(_4,_5[0])}else{_4=isc.addProperties(_4,_5)}
_4=[_4]}else{if(!isc.isAn.Array(_5))_5=[_5];_4=[];for(var i=0;i<_5.length;i++){_4[i]=isc.addProperties({},_5[i])}}
if(this.logIsDebugEnabled("ResultSet")){this.logDebug("Submitted data to be integrated into the cache:"+this.echoAll(_4),"ResultSet")}}}
return _4}
,isc.A.serializeFields=function isc_DataSource_serializeFields(_1,_2){if(!_1&&_2!=null)_1=_2.data;if(!_1)return _1;if(isc.DS.isSimpleTypeValue(_1))return _1;if(isc.isAn.Array(_1)){var _3=[];for(var i=0;i<_1.length;i++){_3[i]=this.serializeFields(_1[i],_2)}
return _3}else if(this.isAdvancedCriteria(_1)){return this.serializeAdvancedCriteria(_1)}
_1=isc.addProperties({},_1);if(_1.__ref)delete _1.__ref;var _5=this.getFields();for(var _6 in _5){if(!isc.isAn.Object(_1[_6]))continue;var _7=_5[_6],_8=_7.type,_9=_8!=null?isc.DataSource.get(_8):null;if(_9&&_9.serializeFields){_1[_6]=_9.serializeFields(_1[_6])}else if(isc.isA.Date(_1[_6])){if(isc.SimpleType.getBaseType(_7.type)=="date"&&!isc.SimpleType.inheritsFrom(_7.type,"datetime"))
{_1[_6].logicalDate=true}else if(isc.SimpleType.getBaseType(_7.type)=="time"){_1[_6].logicalTime=true}}}
return _1}
,isc.A.serializeAdvancedCriteria=function isc_DataSource_serializeAdvancedCriteria(_1){_1=isc.clone(_1);if(_1.criteria){for(var i=0;i<_1.criteria.length;i++){_1.criteria[i]=this.serializeAdvancedCriteria(_1.criteria[i])}}else{if(isc.isA.Date(_1.value)||isc.isA.Date(_1.start)||isc.isA.Date(_1.end)){var _3=this.getField(_1.fieldName);if(_3!=null){if(isc.SimpleType.getBaseType(_3.type)=="date"&&!isc.SimpleType.inheritsFrom(_3.type,"datetime"))
{if(_1.value)_1.value.logicalDate=true;if(_1.start)_1.start.logicalDate=true;if(_1.end)_1.end.logicalDate=true}else if(isc.SimpleType.getBaseType(_3.type)=="time"){if(_1.value)_1.value.logicalTime=true;if(_1.start)_1.start.logicalTime=true;if(_1.end)_1.end.logicalTime=true}}}}
return _1}
,isc.A.getDataProtocol=function isc_DataSource_getDataProtocol(_1){var _2=this.getOperationBinding(_1),_3=this.getWebService(_1);return(_2.dataProtocol!=null?_2.dataProtocol:isc.isA.WebService(_3)?"soap":this.dataProtocol||"getParams")}
,isc.A.$754=function isc_DataSource__storeCustomRequest(_1){if(!this.$624)this.$624={};this.$624[_1.requestId]=_1}
,isc.A.getServiceInputs=function isc_DataSource_getServiceInputs(_1){var _2=this.getOperationBinding(_1),_3=this.getWebService(_1),_4=this.getWSOperation(_1);this.addDefaultCriteria(_1,_2);_1.originalData=_1.data;this.$754(_1);this.applySendExtraFields(_1);if(!this.clientOnly){if(this.fulfilledFromOffline(_1))return{dataProtocol:"clientCustom"};_1.unconvertedDSRequest=isc.shallowClone(_1)}
if(this.autoConvertRelativeDates==true){if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Calling convertRelativeDates from getServiceInputs "+"- data is\n\n"+isc.echoFull(_6))}
var _5=this.convertRelativeDates(_1.data);if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Called convertRelativeDates from getServiceInputs "+"- data is\n\n"+isc.echoFull(_6))}
_1.data=_5}
var _6=this.transformRequest(_1);if(_6!==_1){_1.data=_6}
var _7=this.getDataProtocol(_1),_8=_7=="clientCustom";if(_8){return{dataProtocol:"clientCustom"}}else{delete this.$624[_1.requestId]}
if(isc.isA.WebService(_3)){if(_1.wsOperation==null&&isc.isAn.Object(_4)){_1.wsOperation=_4.name}
this.logInfo("web service: "+_3+", wsOperation: "+this.echoLeaf(_4),"xmlBinding")}
_1.$85o=isc.timestamp();var _9=this.getDataURL(_1);_9=_1.actionURL||_1.dataURL||_9;if(_1.useHttpProxy==null){_1.useHttpProxy=this.$du(_2.useHttpProxy,this.useHttpProxy)}
var _10,_11=_2.defaultParams||this.defaultParams,_12=_1.params;if(_11||_12){_10=isc.addProperties({},_11,_12)}
var _13=_7=="getParams"||_7=="postParams";if(_13){_10=isc.addProperties(_10||{},_1.data)}
if(_13){if(_10)_10=this.serializeFields(_10,_1);return{actionURL:_9,httpMethod:_1.httpMethod||(_7=="getParams"?"GET":"POST"),params:_10}}
var _14={actionURL:_9,httpMethod:_1.httpMethod||"POST"};if(_10)_14.params=_10;if(_7=="postMessage"){_14.data=(_1.data||"").toString()}
if(_7=="postXML"||_7=="soap"){var _15=this.getSerializeFlags(_1);var _16=_14.data=this.getXMLRequestBody(_1);_14.contentType=_1.contentType||"text/xml";this.logDebug("XML post requestBody is: "+_16,"xmlBinding")}
if(_7=="soap"){var _17=this.$du(_2.soapAction,_4.soapAction);if(_17==null)_17='""';_14.httpHeaders=isc.addProperties({SOAPAction:_17},_1.httpHeaders);var _18=isc.isA.WebService(_3)&&this.$du(_2.spoofResponses,this.spoofResponses);if(_18){_14.spoofedResponse=_3.getSampleResponse(_4.name);this.logInfo("Using spoofed response:\n"+_14.spoofedResponse,"xmlBinding")}}
if(this.logIsDebugEnabled("xmlBinding")){this.logDebug("serviceInputs are: "+this.echo(_14),"xmlBinding")}
return _14}
,isc.A.addDefaultCriteria=function isc_DataSource_addDefaultCriteria(_1,_2){var _3=_2.defaultCriteria||this.defaultCriteria;if(!_3)return;_3=isc.addProperties({},_3);if(_3&&_1.operationType=="fetch"){if(this.isAdvancedCriteria(_1.data)){var _4=this.mineCriteriaFieldNames(_1.data);for(var _5 in _3){if(_4.contains(_5))delete _3[_5]}}
if(isc.getValues(_3).length>0){_1.data=isc.DataSource.combineCriteria(_1.data,_3,"and",null)}}}
,isc.A.mineCriteriaFieldNames=function isc_DataSource_mineCriteriaFieldNames(_1){var _2=[];if(!_1.criteria)return _2;for(var i=0;i<_1.criteria.length;i++){if(_1.criteria[i].criteria){_2.addList(this.mineCriteriaFieldNames(_1.criteria[i]))}else{_2.add(_1.criteria[i].fieldName)}}
return _2}
,isc.A.applySendExtraFields=function isc_DataSource_applySendExtraFields(_1){if(!this.sendExtraFields){var _2=_1.data;if(!isc.isAn.Array(_2))_2=[_2];for(var i=0;i<_2.length;i++){var _4=_2[i];if(!isc.isAn.Object(_4))continue;for(var _5 in _4){if(!this.getField(_5))delete _4[_5]}}}}
,isc.A.processResponse=function isc_DataSource_processResponse(_1,_2){var _3=this.$624[_1];if(_3==null){this.logWarn("DataSource.processResponse(): Unable to find request corresponding to ID "+_1+", taking no action.");return}
delete this.$624[_1];if(_2.status==null)_2.status=0;if(_2.status==0){var _4=_2.data;if(_4==null)_2.data=_4=[];if(_2.startRow==null)_2.startRow=_3.startRow||0;if(_2.endRow==null)_2.endRow=_2.startRow+_4.length;if(_2.totalRows==null){_2.totalRows=Math.max(_2.endRow,_4.length)}}
this.$38b(_4,_2,_3)}
,isc.A.$50e=function isc_DataSource__handleClientOnlyReply(_1,_2,_3){var _4=this.cacheAllData&&!this.clientOnly&&this.cacheResultSet?this.cacheResultSet.getAllRows():null,_5=this.getClientOnlyResponse(_3._dsRequest,_4),_6=_3._dsRequest;this.$38b(_2,_5,_6,_1,_3)}
,isc.A.$38a=function isc_DataSource__handleCustomReply(_1,_2,_3){var _4={data:_2,startRow:0,endRow:0,totalRows:0,status:0};var _5=_3._dsRequest;this.$38b(_2,_4,_5,_1,_3)}
,isc.A.$377=function isc_DataSource__handleJSONReply(_1,_2,_3){var _4=_3._dsRequest,_5=this.getOperationBinding(_4).recordXPath||this.recordXPath;if((_1.$38c||_1.$69j)&&this.logIsDebugEnabled("xmlBinding")){this.logDebug("Raw response data: "+this.echoFull(_2),"xmlBinding")}
var _6=_2;if(_2){if(_5){_2=isc.xml.selectObjects(_2,_5);this.logInfo("JSON recordXPath: '"+_5+"', selected: "+this.echoLeaf(_2),"xmlBinding")}
_2=this.recordsFromObjects(_2);if(this.logIsDebugEnabled("xmlBinding")){this.logDebug("Validated dsResponse.data: "+isc.echoFull(_2),"xmlBinding")}
var _7={data:_2,startRow:_4.startRow||0,status:0};_7.endRow=_7.startRow+Math.max(0,_2.length);_7.totalRows=Math.max(_7.endRow,_2.length)}else{var _8=_1.status;if(_8==0||_8==null)_8=-1;var _7={status:_8,data:_1.data}}
this.$38b(_6,_7,_4,_1,_3)}
,isc.A.$69k=function isc_DataSource__handleCSVTextReply(_1,_2,_3){if(_1.status!=0)return;var _4=_1.data.split(/[\r\n]+/);var _5=_4[0].split(",");_5=_5.map(function(_12){return _12.trim()});var _6=[];for(var i=1;i<_4.length;i++){var _8=_4[i].split(",");var _9={};for(var j=0;j<_8.length;j++){var _11=_8[j];if(_11!=null)_11=_11.trim();if(isc.startsWith(_11,"\""))_11=_11.substring(1);if(isc.endsWith(_11,"\""))_11=_11.substring(0,_11.length-1);_9[_5[j]]=_11}
_6.add(_9)}
_1.$69j=true;this.$377(_1,_6,_3)}
,isc.A.$379=function(rpcResponse,jsonText,rpcRequest){if(rpcRequest.isRestRequest&&!isc.isA.String(jsonText)){var jsonObjects=jsonText}else{if(rpcResponse.status>=0){var evalText=jsonText;if(rpcRequest.transport!="scriptInclude"&&evalText!=null){var re;if(this.jsonPrefix){re=new RegExp(/^\s*/);evalText=evalText.replace(re,"");if(evalText.startsWith(this.jsonPrefix)){evalText=evalText.substring(this.jsonPrefix.length)}else{this.logInfo("DataSource specifies jsonPrefix, but not present in "+"response returned from server. Processing response anyway.")}}
if(this.jsonSuffix){re=new RegExp(/\s*$/)
evalText=evalText.replace(re,"");if(evalText.endsWith(this.jsonSuffix)){evalText=evalText.substring(0,(evalText.length-this.jsonSuffix.length))}else{this.logInfo("DataSource specifies jsonSuffix, but not present in "+"response returned from server. Processing response anyway.")}}}
if(evalText&&evalText.match(/^\s*\{/)){evalText="var evalText = "+evalText+";evalText;"}
try{var jsonObjects=isc.eval(evalText)}catch(e){rpcResponse.status=-1;rpcResponse.data="Error: server returned invalid JSON response";this.logWarn("Error evaluating JSON: "+e.toString()+", JSON text:\r"+jsonText)}
if(jsonObjects==null){rpcResponse.status=-1;rpcResponse.data="Error: server returned invalid JSON response";this.logWarn("Evaluating JSON reply resulted in empty value. JSON text:\r"+this.echo(jsonText))}
rpcResponse.$38c=true}}
this.$377(rpcResponse,jsonObjects,rpcRequest)}
,isc.A.recordsFromObjects=function isc_DataSource_recordsFromObjects(_1){if(!isc.isAn.Array(_1))_1=[_1];if(this.skipJSONValidation)return _1;for(var i=0;i<_1.length;i++){_1[i]=this.validateJSONRecord(_1[i])}
return _1}
,isc.A.validateJSONRecord=function isc_DataSource_validateJSONRecord(_1){var _2=this.getFieldNames(),_3={};for(var i=0;i<_2.length;i++){var _5=_2[i],_6=this.getField(_5),_7;if(_6.valueXPath){_7=isc.xml.selectObjects(_1,_6.valueXPath,true)}else{_7=_1[_5]}
if(_6.getFieldValue){if(!isc.isA.Function(_6.getFieldValue)){isc.Func.replaceWithMethod(_6,"getFieldValue","record,value,field,fieldName")}
_7=_6.getFieldValue(_1,_7,_6,_5)}
var _8;if(_7!=_8){_3[_5]=this.validateFieldValue(_6,_7)}}
if(this.dropExtraFields)return _3;for(var i=0;i<_2.length;i++){var _5=_2[i];_1[_5]=_3[_5]}
return _1}
,isc.A.getMessageSerializer=function isc_DataSource_getMessageSerializer(_1,_2){var _3=this.getOperationBinding(_1,_2);if(_3.wsOperation){var _4=this.getWebService(_1,_2);return _4.getMessageSerializer(_3.wsOperation)}
return this}
,isc.A.getXMLRequestBody=function isc_DataSource_getXMLRequestBody(_1,_2){if(isc.$cv)arguments.$cw=this;var _3=isc.SB.create(),_4=this.getDataProtocol(_1);if(_4=="soap"){_3.append(this.getSoapStart(_1),"\r");_3.append(this.getSoapBody(_1,_2));_3.append("\r",this.getSoapEnd(_1))}else{if(this.messageStyle=="template"){_3.append(this.$38d(_1))}else{var _5=this.getMessageSerializer(_1);var _2=this.getSerializeFlags(_1,_2);_3.append(_5.xmlSerialize(_1.data,_2))}}
if(this.logIsDebugEnabled("xmlComm")){this.logDebug("outbound XML message: "+_3,"xmlComm")}
return _3.toString()}
,isc.A.$38d=function isc_DataSource__createTemplatedRequestBody(_1){var _2=isc.SB.create(),_3=this.soapBodyTemplate,_4;_4=_3.evalDynamicString(this,_1);return _4}
,isc.A.getSchemaSet=function isc_DataSource_getSchemaSet(){return isc.SchemaSet.get(this.schemaNamespace)}
,isc.A.hasWSDLService=function isc_DataSource_hasWSDLService(_1){return isc.isA.WebService(this.getWebService(_1))}
,isc.A.getWebService=function isc_DataSource_getWebService(_1){var _2=this.getOperationBinding(_1),_3=(_1?_1.serviceNamespace:null)||_2.serviceNamespace||this.serviceNamespace,_4=(_1?_1.serviceName:null)||_2.serviceName||this.serviceName;var _5;if(_4)_5=isc.WebService.getByName(_4,_3);else _5=isc.WebService.get(_3);if((_3!=null||_4!=null)&&_5==null){this.logWarn("Could not find WebService definition: "+(_4?"serviceName: "+_4:"")+(_3?"   serviceNamespace: "+_3:"")+this.getStackTrace())}
return _5||this}
,isc.A.getWSOperation=function isc_DataSource_getWSOperation(_1){var _2=this.getOperationBinding(_1),_3=(isc.isAn.Object(_1)?_1.wsOperation:null)||_2.wsOperation||this.wsOperation,_4=this.getWebService(_1);if(_3!=null&&isc.isA.WebService(_4)){var _5=_4.getOperation(_3);if(!_5){isc.logWarn("DataSource.getWSOperation() : could not retrieve the operation "+_3)}
return _5}
return this}
,isc.A.getDataURL=function isc_DataSource_getDataURL(_1){var _2=this.getOperationBinding(_1);if(_2!=this&&_2.dataURL)return _2.dataURL;if(this.dataURL!=null)return this.dataURL;if(this.hasWSDLService(_1)){var _3=this.getWebService(_1);return _3.getDataURL(this.getWSOperation(_1).name)}
return this.dataURL}
,isc.A.getGlobalNamespaces=function isc_DataSource_getGlobalNamespaces(_1){var _2=this.getWebService(_1),_3=this.globalNamespaces;if(_2&&_2.globalNamespaces){_3=isc.addProperties({},_3,_2.globalNamespaces)}
return _3}
,isc.A.getSoapStart=function isc_DataSource_getSoapStart(_1){var _2=this.getWebService(_1);if(_2.getSoapStart)return _2.getSoapStart(_1);return isc.SB.concat(isc.DataSource.$37t,isc.xml.$37l(this.getGlobalNamespaces(_1),null,"         "),">",isc.DataSource.$37u,this.getSoapHeader(_1),isc.DataSource.$51y)}
,isc.A.getSoapHeader=function isc_DataSource_getSoapHeader(_1){var _2=this.getWebService(_1);if(_2.getSoapHeader)return _2.getSoapHeader(_1);var _3=_1.headerData||_2.getHeaderData(_1);if(!_3)return;this.logDebug("headerData is: "+this.echo(_3),"xmlBinding");var _4=_2.getInputHeaderSchema(this.getWSOperation(_1))||isc.emptyObject;var _5="",_6=_1.useFlatHeaderFields;for(var _7 in _3){var _8=_4[_7];if(_8!=null){if(isc.isA.DataSource(_8)){_5+=_8.xmlSerialize(_3[_7],{useFlatFields:_6})}else{_5+="\r     "+this.$38g(_7,_8,_3[_7],_8.partNamespace)}}else{this.logWarn("headerData passed for SOAP header partName: "+_7+", no schema available, not outputting")}}
return _5}
,isc.A.getSoapBody=function isc_DataSource_getSoapBody(_1,_2){if(isc.$cv)arguments.$cw=this;var _3=isc.SB.create(),_4=this.getWebService(_1),_5=this.getSoapStyle(_1),_6=this.getWSOperation(_1),_7=this.xmlNamespaces?isc.makeReverseMap(this.xmlNamespaces):null,_2=isc.addProperties({nsPrefixes:isc.addProperties({},_7)},_2),_8=_2.generateResponse?_4.getResponseMessage(_6.name):_4.getRequestMessage(_6.name),_9=_2.bodyPartNames||_4.getBodyPartNames(_6.name,_2.generateResponse);_2=this.getSerializeFlags(_1,_2);isc.Comm.omitXSI=_6.inputEncoding!="encoded";var _10=isc.Comm.xmlSchemaMode;isc.Comm.xmlSchemaMode=true;var _11="        ";if(_5=="rpc"){_3.append("\n",_11,isc.Comm.$36u(_6.name,null,_6.inputNamespace,"opNS",true),">");_11+="    "}
this.logInfo("soap:body parts in use: '"+_9+"', soapStyle: "+_5,"xmlSerialize");if(this.logIsDebugEnabled("xmlSerialize")){this.logDebug("SOAP data is: "+this.echoFull(_1.data),"xmlSerialize")}
for(var i=0;i<_9.length;i++){var _13=_9[i];var _14=_9.length<2&&_5=="document"?_1.data:(_1.data?_1.data[_13]:null);var _15=_8.getMessagePart(_13,_14,_2,_11);_3.append("\r"+_11+_15)}
if(_5=="rpc"){_3.append("\n","        ",isc.Comm.$36v(_6.name,_6.inputNamespace,"opNS"))}
isc.Comm.omitXSI=null;isc.Comm.xmlSchemaMode=_10;return isc.SB.concat("    ",isc.DS.$37v,this.outputNSPrefixes(_2.nsPrefixes,"        "),">",_3.toString(),"\r    ",isc.DS.$51z)}
,isc.A.getMessagePart=function isc_DataSource_getMessagePart(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;var _5=this.getPartField(_1),_6=this.getSchema(_5.type,_5.xsElementRef?"element":null),_7=this.logIsInfoEnabled("xmlSerialize");if(isc.isA.DataSource(_6)){if(_7){this.logInfo("soap:body part '"+_1+"' is complex type with schema: "+_6+" has value: "+(this.logIsDebugEnabled("xmlSerialize")?this.echo(_2):this.echoLeaf(_2)),"xmlSerialize")}
var _8=_5.xsElementRef?null:_1;return _6.xmlSerialize(_2,_3,_4,_8)}else{if(_2!=null&&!isc.DS.isSimpleTypeValue(_2)){_2=_2[_5.name]||_2[_1]||_2}
if(_7){this.logInfo("soap:body part '"+_1+"' is of simple type '"+_5.type+"'"+" has value: '"+this.echoLeaf(_2)+"'","xmlSerialize")}
var _9=this.getType(_5.type),_10=_5.partNamespace;if(!_10&&_9&&_9.schemaNamespace){_10=_9.schemaNamespace}
return this.$38g(_5.name||_1,_5,_2,_10,_3)}}
,isc.A.getPartField=function isc_DataSource_getPartField(_1){var _2=isc.getValues(this.getFields()).find("partName",_1);if(_2!=null)return _2;return this.getField(_1)}
,isc.A.getSoapEnd=function isc_DataSource_getSoapEnd(_1){var _2=this.getWebService(_1);if(_2.getSoapEnd)return _2.getSoapEnd(_1);return isc.DataSource.$37w}
,isc.A.getSoapStyle=function isc_DataSource_getSoapStyle(_1){if(!this.hasWSDLService(_1))return"document";return this.getWebService(_1).getSoapStyle(this.getWSOperation(_1).name)}
,isc.A.getSerializeFlags=function isc_DataSource_getSerializeFlags(_1,_2){_2=isc.addProperties({soapStyle:this.getSoapStyle(_1)},_2);var _3=this.getOperationBinding(_1);_2.flatData=this.$du(_1.useFlatFields,_3.useFlatFields,this.useFlatFields);_2.recursiveFlatFields=this.$du(_1.recursiveFlatFields,_3.recursiveFlatFields,this.recursiveFlatFields);_2.textContentProperty=this.$du(_1.textContentProperty,_3.textContentProperty);_2.dsRequest=_1;_2.startRowTag=_3.startRowTag||this.startRowTag;_2.endRowTag=_3.endRowTag||this.endRowTag;return _2}
,isc.A.xmlSerialize=function isc_DataSource_xmlSerialize(_1,_2,_3,_4){if(!_2)_2={};if(_2.useFlatFields)_2.flatData=true;var _5=this.getSchemaSet(),_6=(_2.qualifyAll==null);if(_5&&_5.qualifyAll){_2.qualifyAll=true}
var _7;if(_2.nsPrefixes==null){var _8=this.xmlNamespaces?isc.makeReverseMap(this.xmlNamespaces):null;_2.nsPrefixes=isc.addProperties({},_8);_7=true}
var _9=isc.Comm.xmlSchemaMode;isc.Comm.xmlSchemaMode=true;var _10;if(isc.Comm.omitXSI==null){_10=isc.Comm.omitXSI=true}
var _11=this.$ew(_1,_2,_3,_4,_7);if(_6)_2.qualifyAll=null;isc.Comm.xmlSchemaMode=_9;if(_10)isc.Comm.omitXSI=null;return _11}
,isc.A.$ew=function isc_DataSource__xmlSerialize(_1,_2,_3,_4,_5){if(isc.$cv)arguments.$cw=this;if(this.logIsDebugEnabled("xmlSerialize")){this.logDebug("schema: "+this+" serializing: "+this.echo(_1)+" with flags: "+this.echo(_2),"xmlSerialize")}
var _6=this.mustQualify||_2.qualifyAll,_4=_4||this.tagName||this.ID;if(!_2.ignoreConstructor){var _7;if(_1!=null&&(_1._constructor||isc.isAn.Instance(_1))){var _7=isc.isAn.Instance(_1)?_1.Class:_1._constructor}}
if(isc.DS.isSimpleTypeValue(_1)){if(isc.isA.String(_1)&&isc.startsWith(_1,"ref:")){return"<"+_4+" ref=\""+_1.substring(4)+"\"/>"}
this.logDebug("simple type value: "+this.echoLeaf(_1)+" passed to xmlSerialize on "+this,"xmlSerialize");return isc.Comm.$ew(_4||this.tagName||this.ID,_1)}
if(isc.isAn.Instance(_1))_1=_1.getSerializeableFields();if(isc.isAn.Array(_1)&&!this.canBeArrayValued)return this.map("xmlSerialize",_1,_2,_3).join("\n");var _8=isc.SB.create(),_3=_3||"";_8.append("\r",_3);var _9;if(_6){_9=(this.isA("XSComplexType")?_2.parentSchemaNamespace:null)||this.schemaNamespace}
_8.append(isc.Comm.$36u(_4,this.ID,_9,_2.nsPrefixes,true));_1=this.serializeAttributes(_1,_8,_2);if(_7&&_4!=_7){_8.append(" constructor=\"",_7,"\"")}
var _10;if(_1!=null){_10=this.xmlSerializeFields(_1,_2,_3+"    ")}
if(_5){_8.append(this.outputNSPrefixes(_2.nsPrefixes,_3+"     "))}
var _11=this.$45z;this.$45z=null;if(_10==null||isc.isAn.emptyString(_10)){_8.append("/>");return _8.toString()}
_8.append(">",_10,(_11?"":"\r"+_3));_8.append(isc.Comm.$36v(_4,_9,_2.nsPrefixes));return _8.toString()}
,isc.A.outputNSPrefixes=function isc_DataSource_outputNSPrefixes(_1,_2){delete _1.$36y;_1=isc.makeReverseMap(_1);var _3=isc.xml.$37l(_1,null,_2+"        ");return _3}
,isc.A.serializeAttributes=function isc_DataSource_serializeAttributes(_1,_2,_3){var _4=this.getFieldNames(),_5=true;for(var i=0;i<_4.length;i++){var _7=_4[i],_8=this.getField(_7);if(_8.xmlAttribute&&((_1&&_1[_7]!=null)||_8.xmlRequired)){if(_5){_1=isc.addProperties({},_1);_5=false}
var _9=_1[_7];if(_3&&_3.spoofData)_9=this.getSpoofedData(_8);_2.append(" ",_7,"=\"",this.$38f(_8,_9),"\"");delete _1[_7]}}
return _1}
,isc.A.xmlSerializeFields=function isc_DataSource_xmlSerializeFields(_1,_2,_3){var _4=isc.SB.create(),_2=_2||isc.emptyObject,_5=_2.flatData,_6=_2.spoofData,_3=_3||"";var _1=isc.addProperties({},_1);if(_1.__ref!=null)delete _1.__ref;var _7=this.getFields();for(var _8 in _7){var _9=this.getField(_8),_10=_1[_8],_11=this.fieldIsComplexType(_8);var _12=_1[_8];if(_2.startRowTag==_9.name&&_12==null){_12=_2.dsRequest?_2.dsRequest.startRow:null}else if(_2.endRowTag==_9.name&&_12==null){_12=_2.dsRequest?_2.dsRequest.endRow:null}else if(_11&&_5&&_12==null){_12=_1}
var _13=(_9.xmlRequired&&!_9.xmlAttribute)||(_1[_8]!=null||(_6&&!_9.xmlAttribute));if(_5&&_11){var _14=this.getSchema(_9.type),_15=isc.clone(_2.nsPrefixes),_16=_14.xmlSerializeFields(_12,_2);if(_16!=null&&!isc.isAn.emptyString(_16)){_13=true}
_2.nsPrefixes=_15}
if(_13){if(_5&&_11&&_1[_8]!=null&&!isc.DS.isSimpleTypeValue(_1[_8])&&!_2.recursiveFlatFields)
{_2=isc.addProperties({},_2);_2.flatData=false}
_4.append(this.xmlSerializeField(_8,_12,_2,_3))}
if(!_5&&_1[_8]!=null)delete _1[_8]}
if(!_5&&!isc.isA.Schema(this)){for(var _8 in _1){_4.append(this.xmlSerializeField(_8,_1[_8],_2,_3))}}
return _4.toString()}
,isc.A.xmlSerializeField=function isc_DataSource_xmlSerializeField(_1,_2,_3,_4){var _5=isc.SB.create(),_6=this.getField(_1);if(_6==null&&(_1.startsWith("_")||_1.startsWith("$")))return;var _7=(_6?_6.type:null),_8=_3&&_3.flatData,_9=_3&&_3.spoofData,_4=_4||"";if(_9)_2=this.getSpoofedData(_6);if(this.logIsDebugEnabled("xmlSerialize")){this.logDebug("serializing fieldName: "+_1+" with type: "+_7+" with value: "+this.echo(_2),"xmlSerialize")}
var _10=((_6&&_6.mustQualify)||_3.qualifyAll?this.getSchemaSet().schemaNamespace:null);var _11=_3.textContentProperty||this.textContentProperty,_12=this.getTextContentField();if(_1==_11&&(_12!=null||!this.hasXMLElementFields(_11)))
{this.$45z=true;return this.$38f(_12,_2)}
if(_7==this.$41v&&_2!=null){if(_2.iscAction){_2=_2.iscAction}else if(isc.isA.StringMethod(_2)){_2=_2.value}}
var _13=isc.Comm.$36u(_1,_6?_6.type:null,_10,_3.nsPrefixes),_14=isc.Comm.$36v(_1,_10,_3.nsPrefixes);var _15=isc.isAn.Array(_2)?_2:[_2];if(!_6&&_3.schema&&_3.schema.getField){_6=_3.schema.getField(_1)}
if(this.fieldIsComplexType(_1)){var _16=_3.parentSchemaNamespace;_3.parentSchemaNamespace=this.schemaNamespace;var _17=this.getFieldDataSource(_6,_6&&_6.xsElementRef?"element":null);if(_6.multiple){_5.append("\r",_4,_13);for(var i=0;i<_15.length;i++){_5.append(_17.xmlSerialize(_15[i],_3,_4+"    ",_6.childTagName))}
_5.append("\r",_4,_14)}else if(_17.canBeArrayValued&&isc.isAn.Array(_2)){_5.append(_17.xmlSerialize(_2,_3,_4,_1))}else{for(var i=0;i<_15.length;i++){var _2=_15[i];if(_2==null){_5.append("\r",_4)
_5.append(_13,_14)}else if(isc.DS.isSimpleTypeValue(_2)){if(isc.isA.String(_2)&&isc.startsWith(_2,"ref:")){_5.append("\r",_4)
_5.append(_13);var _19=(_6?_6.childTagName||_6.type:"value");_5.append("<",_19," ref=\"",_2.substring(4),"\"/>");_5.append(_14)}else{this.logWarn("simple type value "+this.echoLeaf(_2)+" passed to complex field '"+_6.name+"'","xmlSerialize");_5.append("\r",_4)
_5.append(isc.Comm.xmlSerialize(_1,_2))}}else{_5.append(_17.xmlSerialize(_2,_3,_4,_1))}}}
_3.parentSchemaNamespace=_16}else if(_6!=null){if(_6.xsElementRef){var _20=this.getType(_6.type);if(_20&&_20.schemaNamespace)
{_10=_20.schemaNamespace}}
if(_6.multiple){_5.append("\r",_4,_13,"\r");for(var i=0;i<_15.length;i++){_5.append(this.$38g(_6.childTagName,_6,_15[i],_10,_3),"\r",_4)}
_5.append("\r",_4,_14,"\r")}else{for(var i=0;i<_15.length;i++){_5.append("\r",_4,this.$38g(_1,_6,_15[i],_10,_3))}}}else{for(var i=0;i<_15.length;i++){if(_15[i]==null||isc.isAn.emptyObject(_15[i])){_5.append("\r",_4,_13,_14)}else{_5.append("\r",_4,isc.Comm.$ew(_1,_15[i],_4,{isRoot:false}))}}}
return _5.toString()}
,isc.A.$38g=function isc_DataSource__serializeSimpleTypeTag(_1,_2,_3,_4,_5){var _6=_2.type,_5=_5||{};if(isc.isAn.Object(_3)&&!isc.isA.Function(_3.$ew)){return isc.Comm.xmlSerialize(_1||null,_3)}else{var _6=this.$38h(_6);if(_3==null&&_2.nillable){var _7=_1||"value";return isc.Comm.$36u(_7,null,_4,_5.nsPrefixes,true)+" xsi:nil=\"true\"/>"}
if(isc.isA.Date(_3)){_3=_3.toSchemaDate(_2.type)}else if(_3!=null&&_3.$ew){return _3.$ew(_1,_6,_4)}else{_3=isc.makeXMLSafe(_3)}
return isc.Comm.$ex(_1||"value",_3,_6,_4,_5.nsPrefixes)}}
,isc.A.$38f=function isc_DataSource__serializeSimpleTypeValue(_1,_2){if(isc.isA.Date(_2)){return _2.toSchemaDate(_1?_1.type:null)}else{return isc.makeXMLSafe(_2)}}
,isc.A.$38h=function isc_DataSource__getXMLSchemaType(_1){switch(_1){case"integer":return"int";case"number":return"long";default:return _1}}
,isc.A.xmlSerializeSample=function isc_DataSource_xmlSerializeSample(){return this.xmlSerialize({},{spoofData:true})}
,isc.A.getSpoofedData=function isc_DataSource_getSpoofedData(_1){if(!_1)return"textValue";if(this.getSchema(_1.type)!=null)return{};if(_1.multiple){_1={type:_1.type};return[this.getSpoofedData(_1),this.getSpoofedData(_1)]}
if(_1.valueMap){var _2=!isc.isAn.Array(_1.valueMap)?isc.getKeys(_1.valueMap):_1.valueMap;return _2[Math.round(Math.random()*(_2.length-1))]}
var _3=isc.SimpleType.getBaseType(_1.type);switch(_3){case"boolean":return(Math.random()>0.5);case"integer":case"int":case"number":var _4=0,_5=10;if(_1.validators){var _6=_1.validators.find("type","integerRange")||_1.validators.find("_constructor","integerRange");if(_6){this.logWarn(_1.name+" has "+_6.type+" validator "+" with min "+_6.min+" and max "+_6.max);_4=_6.min||0;_5=_6.max||Math.min(_4,10);if(_4>_5)_4=_5}}
return Math.round(_4+(Math.random()*(_5-_4)));case"float":case"decimal":case"double":var _4=0,_5=10,_7=2;if(_1.validators){var _6=_1.validators.find("type","floatRange")||_1.validators.find("_constructor","floatRange");if(_6){this.logWarn(_1.name+" has "+_6.type+" validator "+" with min "+_6.min+" and max "+_6.max);_4=_6.min||0;_5=_6.max||Math.min(_4,10);if(_4>_5)_4=_5}
var _8=_1.validators.find("type","floatPrecision")||_1.validators.find("_constructor","floatPrecision");if(_8){_7=_8.precision||2}}
return(_4+(Math.random()*(_5-_4))).toFixed(_7);case"date":case"time":case"datetime":var _9=new Date();if(_1.validators){var _6=_1.validators.find("type","dateRange")||_1.validators.find("_constructor","dateRange");if(_6){this.logWarn(_1.name+" has "+_6.type+" validator "+" with min "+_6.min+" and max "+_6.max);if(_6.min)_9=_6.min;else if(_6.max)_9=_6.max}}
return _9;default:return"textValue"}}
,isc.A.getSerializeableFields=function isc_DataSource_getSerializeableFields(_1,_2){var _3=this.Super("getSerializeableFields",arguments);var _4=_3.fields;_4=isc.getValues(_4);for(var i=0;i<_4.length;i++){var _6=_4[i]=isc.addProperties({},_4[i]);var _7=_6.validators;if(_7){_6.validators=_7.findAll("_generated",null);if(_6.validators==null)delete _6.validators}}
_3.fields=_4;return _3}
,isc.A.$378=function isc_DataSource__handleXMLReply(_1,_2,_3,_4){var _5=_1,_6=_4._dsRequest,_7=this.getOperationBinding(_6),_8;if(_3.status<0){var _9=_2||_3.data;this.$38b(_9,{status:_3.status,data:_9},_6,_3,_4);return}
if(_5){if(_7.wsOperation){var _10=this.getWebService(_6),_8=_10.getOutputNamespaces(_7.wsOperation);_5.addNamespaces(_8)}
_5.addNamespaces(this.xmlNamespaces);_5.addNamespaces(_7.xmlNamespaces)}
var _11=isc.addProperties({},_8,this.xmlNamespaces,_7.xmlNamespaces);this.dsResponseFromXML(_5,_6,_11,{target:this,methodName:"$57b",xmlData:_5,dsRequest:_6,rpcRequest:_4,rpcResponse:_3})}
,isc.A.$57b=function isc_DataSource__completeHandleXMLReply(_1,_2){this.$38b(_2.xmlData,_1,_2.dsRequest,_2.rpcResponse,_2.rpcRequest)}
,isc.A.dsResponseFromXML=function isc_DataSource_dsResponseFromXML(_1,_2,_3,_4){if(_1){this.selectRecords(_1,_2,{target:this,methodName:"$57c",dsRequest:_2,callback:_4,xmlData:_1,xmlNamespaces:_3})}else{this.$57c([],_2,_3,_4)}}
,isc.A.$57c=function isc_DataSource__completeDSResponseFromXML(_1,_2,_3,_4){if(!_4&&_2.callback)_4=_2.callback;if(_2.xmlNamespaces)_3=_2.xmlNamespaces;if(_2.dsRequest)_2=_2.dsRequest;if(_3==null)_3=this.xmlNamespaces;var _5={data:_1,startRow:_2.startRow||0,status:0};_5.endRow=_5.startRow+Math.max(0,_1.length-1);_5.totalRows=Math.max(_5.endRow,_1.length);var _6=_4.xmlData;if(_6){if(this.totalRowsXPath){_5.totalRows=isc.xml.selectNumber(_6,this.totalRowsXPath,_3,true)}
if(this.startRowXPath){_5.startRow=isc.xml.selectNumber(_6,this.startRowXPath,_3,true);_5.endRow=_5.startRow+Math.max(0,_1.length-1)}
if(this.endRowXPath){_5.endRow=isc.xml.selectNumber(_6,this.endRowXPath,_3,true);if(!this.startRowXPath){_5.startRow=_5.endRow-Math.max(0,_1.length-1)}}
if(this.statusXPath){_5.status=parseInt(isc.xml.selectScalar(_6,this.statusXPath,_3,true))}
if(this.errorSchema){_5.errors=this.errorSchema.selectRecords(_6,_2)}}
if(_4)this.fireCallback(_4,"dsResponse",[_5,_4])
return _5}
,isc.A.selectRecords=function isc_DataSource_selectRecords(_1,_2,_3){var _4=this.selectRecordElements(_1,_2);var _5=this.getOperationBinding(_2),_6=this.getSchema(_5.responseDataSchema)||this;return _6.recordsFromXML(_4,_3)}
,isc.A.recordsFromXML=function isc_DataSource_recordsFromXML(_1,_2){if(_1&&!isc.isAn.Array(_1)){if(_1.length!=null)_1=isc.xml.$37m(_1);else _1=[_1]}
if(_1&&this.transformResponseToJS){if(_1.length>this.resultBatchSize){var _3={startingRow:0,callback:_2,elements:_1};return this.$57d(_3)}
var _4=this.dropExtraFields?this.getFieldNames():null;_1=isc.xml.toJS(_1,_4,this);if(this.logIsDebugEnabled("xmlBinding")){this.logDebug("transformed response: "+this.echoFull(_1)+"xmlBinding")}}
if(_2){this.fireCallback(_2,"records",[_1,_2])}
return _1}
,isc.A.$57d=function isc_DataSource__asyncRecordsFromXML(_1){var _2=_1.elements,_3=_1.startingRow,_4=_1.callback,_5=Math.min(_2.length,_3+this.resultBatchSize),_6=this.dropExtraFields?this.getFieldNames():null;if(!_1.$57e){_1.$57e=isc.xml.toJS(_2.slice(_3,_5+1),_6,this)}else{var _7=isc.xml.toJS(_2.slice(_3,_5+1),_6,this);_1.$57e.addList(_7)}
if(_5<_2.length){_1.startingRow=_5+1;this.delayCall("$57d",[_1])}else if(_4){this.fireCallback(_4,"records",[_1.$57e,_4])}}
,isc.A.selectRecordElements=function isc_DataSource_selectRecordElements(_1,_2){if(isc.isA.String(_1))_1=isc.xml.parseXML(_1);var _3=this.getOperationBinding(_2);var _4=_3==this?null:_3.recordXPath,_5=_3==this?null:_3.recordName,_6=this.recordXPath,_7=this.recordName;if(_4==null&&(_5!=null||(_6==null&&_7!=null))&&this.hasWSDLService(_2))
{var _8=this.getWebService(_2);return _8.selectByType(_1,_3.wsOperation||this.wsOperation,_5||_7)}
var _9=_4||_6,_10;if(_9){_10=isc.xml.selectNodes(_1,_9,this.xmlNamespaces);this.logDebug("applying XPath: "+_9+(this.xmlNamespaces?" with namespaces: "+this.echo(this.xmlNamespaces):"")+" got "+(_10?_10.length:null)+" elements","xmlBinding")}else{_10=[];var _11=_5||_7||this.ID;var _12=_1.getElementsByTagName(_11);for(var i=0;i<_12.length;i++)_10.add(_12[i]);this.logDebug("getting elements of tag name: "+_11+" got "+_10.length+" elements","xmlBinding")}
return _10}
,isc.A.$38b=function isc_DataSource__completeResponseProcessing(_1,_2,_3,_4,_5){if(!_2){_2={status:_4.status,httpResponseCode:_4.httpResponseCode}}
if(_4!=null&&_5!=null){_2.httpResponseCode=_4.httpResponseCode;_2.transactionNum=_4.transactionNum;_2.clientContext=_5.clientContext;_2.httpHeaders=_4.httpHeaders}else{_2.clientContext=_3.clientContext}
if(this.logIsInfoEnabled("xmlBinding")){this.logInfo("dsResponse is: "+this.echo(_2),"xmlBinding")}
_2.context=_5;var _6=this.transformResponse(_2,_3,_1);_2=_6||_2;_2.startRow=this.$52v(_2.startRow,0);var _7=_2.endRow;if(_7==null){if(_2.status<0)_7=0;else if(isc.isAn.Array(_2.data))_7=_2.data.length;else _7=1}
_2.endRow=this.$52v(_7);_2.totalRows=this.$52v(_2.totalRows,_2.endRow);if(this.useOfflineStorage&&_2.status==0&&!this.clientOnly){isc.DataSource.cacheResponse(_3,_2);if(isc.Offline&&!_2.fromOfflineCache){if(_3.unconvertedDSRequest){isc.Offline.storeResponse(_3.unconvertedDSRequest,_2)}else{isc.Offline.storeResponse(_3,_2)}}}
if(_3&&_3.resultSet){if(_2.status==isc.RPCResponse.STATUS_OFFLINE){_3.resultSet.$78c=true}else{_3.resultSet.$78c=false}}else if(_3&&_3.resultTree){if(_2.status==isc.RPCResponse.STATUS_OFFLINE){_3.resultTree.$78c=true}else{_3.resultTree.$78c=false}}
if(_2.relatedUpdates!=null){for(var i=0;i<_2.relatedUpdates.length;i++){if(_2.relatedUpdates[i].operationType==null){_2.relatedUpdates[i].operationType=_2.operationType}
isc.DS.get(_2.relatedUpdates[i].dataSource).updateCaches(_2.relatedUpdates[i],null)}}
this.fireResponseCallbacks(_2,_3,_4,_5)}
);isc.evalBoundary;isc.B.push(isc.A.fireResponseCallbacks=function isc_DataSource_fireResponseCallbacks(_1,_2,_3,_4){if(!_1.clientContext)_1.clientContext={};if(_1.status>=0){isc.DataSource.handleUpdate(_1,_2)}else if(!_2.willHandleError){isc.RPCManager.$a0(_1,_2)}
var _5=[_2.$376,_2.afterFlowCallback],_6=[];for(var i=0;i<_5.length;i++){var _8=_5[i];if(_6.contains(_8)){this.logWarn("Suppressed duplicate callback: "+_8);continue}
var _9=this.fireCallback(_8,"dsResponse,data,dsRequest",[_1,_1.data,_2]);if(_4&&_4.willHandleError&&_9===false){this.logDebug("performOperationReply: Further processing cancelled by callback");break}
if(_3){var _10=isc.RPCManager.getTransaction(_3.transactionNum);if(_10&&_10.suspended)return}}}
,isc.A.$52v=function isc_DataSource__parseNumber(_1,_2){if(_1==null)return _2;if(!isc.isA.String(_1))return _1;var _3=parseInt(_1);if(isNaN(_3))return _2!=null?_2:_1;else return _3}
,isc.A.transformResponse=function isc_DataSource_transformResponse(_1,_2,_3){return _1}
,isc.A.getFieldValue=function isc_DataSource_getFieldValue(_1,_2,_3){var _4=isc.xml.getFieldValue(_1,_2,_3,this,this.xmlNamespaces);if(!_3.getFieldValue)return _4;if(!isc.isA.Function(_3.getFieldValue)){isc.Func.replaceWithMethod(_3,"getFieldValue","record,value,field,fieldName")}
return _3.getFieldValue(_1,_4,_3,_2)}
,isc.A.validateFieldValue=function isc_DataSource_validateFieldValue(_1,_2){if(!isc.Validator)return _2;var _3=_1.validators;if(!_3)return _2;if(!isc.isAn.Array(_3)){this.$2j[0]=_3;_3=this.$2j}
var _4=_2;for(var i=0;i<_3.length;i++){var _6=_3[i];var _7=isc.Validator.processValidator(_1,_6,_2,null,null);if(!_7){this.logWarn(this.ID+"."+_1.name+": value: "+this.echoLeaf(_2)+" failed on validator: "+this.echo(_6));return _2}
var _8;if(_6.resultingValue!==_8){_2=_6.resultingValue;_6.resultingValue=_8}
if(!_7&&_6.stopIfFalse)break}
this.$2j.length=0;return _2}
,isc.A.getCriteriaFields=function isc_DataSource_getCriteriaFields(_1){return isc.DS.getCriteriaFields(_1,this)}
,isc.A.$74s=function isc_DataSource__getAdvancedCriteriaFields(_1,_2){return isc.DS.$74s(_1,_2)}
,isc.A.fetchRecord=function isc_DataSource_fetchRecord(_1,_2,_3){var _4={},_5=this.getPrimaryKeyField();if(_5==null){this.logWarn("This datasource has no primary key field. Ignoring fetchRecord call");return}
var _6=_5.name;var _7;if(isc.isAn.Object(_1)&&_1[_6]!==_7){_4=_1}else{_4[_6]=_1}
return this.fetchData(_4,_2,_3)}
,isc.A.fetchData=function isc_DataSource_fetchData(_1,_2,_3){this.performDSOperation("fetch",_1,_2,_3)}
,isc.A.filterData=function isc_DataSource_filterData(_1,_2,_3){if(!_3)_3={};if(_3.textMatchStyle==null)_3.textMatchStyle="substring";this.performDSOperation("fetch",_1,_2,_3)}
,isc.A.exportClientData=function isc_DataSource_exportClientData(_1,_2,_3){return isc.DataSource.exportClientData(_1,_2,_3)}
,isc.A.exportData=function isc_DataSource_exportData(_1,_2,_3,_4){if(!_2)_2={};if(this.canExport==false){isc.logWarn("Exporting is disabled for this DataSource.  Set "+"DataSource.canExport to true to enable it.");return}
if(_2.exportAs&&_2.exportAs.toLowerCase()=="json"&&_2.exportToClient!==false)
{isc.logWarn("Export in JSON format with download to client is not allowed from a "+"client call - either specify exportToClient: false, or set "+"operationBinding.exportAs on your DataSource instead.  The server "+"will reject this attempt.")}
if(_2.textMatchStyle==null)_2.textMatchStyle="substring";var _5={};_5.exportResults=true;_5.exportAs=_2.exportAs||"csv";_5.exportDelimiter=_2.exportDelimiter||",";_5.exportTitleSeparatorChar=_2.exportTitleSeparatorChar||"";_5.exportFilename=_2.exportFilename||"Results."+(_5.exportAs=="ooxml"?"xlsx":_5.exportAs);_2.exportFilename=_5.exportFilename;_5.exportPath=_2.exportPath;_5.exportDisplay=_2.exportDisplay||"download";_5.lineBreakStyle=_2.lineBreakStyle||"default";_5.exportFields=this.getExportableDSFields(_2.exportFields||this.getVisibleDSFields(),_4);_5.exportHeader=_2.exportHeader;_5.exportFooter=_2.exportFooter;_5.exportFieldTitles=_2.exportFieldTitles;if(!_5.exportFieldTitles){var _6=_5.exportFields;var _7={};for(var i=0;i<_6.length;i++){var _9=_6[i];var _10;if(isc.isA.String(_9)){_10=_9;_9=this.getField(_10)}
if(_9){if(_9.hidden)continue;_7[_9.name]=_9.exportTitle||_9.title}else{_7[_10]=_10}}
_5.exportFieldTitles=_7}
_2.downloadResult=(_2.exportToClient!==false);_2.downloadToNewWindow=_2.exportDisplay=="window"?true:false;if(_2.downloadToNewWindow){if(_5.exportFilename.endsWith(".xml")&&_5.exportAs!="xml"){_5.exportFilename=_5.exportFilename+".txt"}
_2.download_filename=_2.exportFilename;_1=_1||{};_1.download_filename=_2.download_filename}
_2.showPrompt=false;_2.parameters=_5;this.performDSOperation("fetch",_1,_3,_2)}
,isc.A.getVisibleDSFields=function isc_DataSource_getVisibleDSFields(){var _1=[];var _2=this.fields;if(!isc.isAn.Array(_2)){_2=[];for(var _3 in this.fields){_2.add(this.fields[_3])}}
for(var i=0;i<_2.length;i++){var _5=_2.get(i);if(!_5.hidden)_1.add(_5.name)}
return _1}
,isc.A.getExportableDSFields=function isc_DataSource_getExportableDSFields(_1,_2){var _3=[];if(this.canExport){for(var i=0;i<_1.length;i++){var _5=this.getField(_1[i]);if(!_5&&_2&&_2.getField){_5=_2.getField(_1[i]);if(_5&&_5.includeFrom&&_5.canExport!=false){_3.add(_1[i])}
continue}
if(_5&&_5.canExport!=false)
_3.add(_5.name)}}
return _3}
,isc.A.getClientOnlyDataSource=function isc_DataSource_getClientOnlyDataSource(_1,_2,_3,_4){var _5=_1,_6=_2,_7=this;if(this.cacheAllData&&this.hasAllData()){var _8=isc.DataSource.create({inheritsFrom:_7,clientOnly:true,useParentFieldOrder:true,testData:this.cacheResultSet.getAllRows()},_4);_7.fireCallback(_6,"dataSource",[_8]);return _8}else{this.fetchData(_5,function(_10,_11){var _9=_10.totalRows;_7.fetchData(_5,function(_10,_11){var _8=isc.DataSource.create({inheritsFrom:_7,clientOnly:true,useParentFieldOrder:true,testData:_11},_4);_7.fireCallback(_6,"dataSource",[_8])},isc.addProperties({},_3,{startRow:0,endRow:_9}))},isc.addProperties({},_3,{startRow:0,endRow:0}))}}
,isc.A.addData=function isc_DataSource_addData(_1,_2,_3){if(isc.Offline&&isc.Offline.isOffline()&&this.contactsServer()){isc.logWarn("Data cannot be saved because you are not online");return}
this.performDSOperation("add",_1,_2,_3)}
,isc.A.updateData=function isc_DataSource_updateData(_1,_2,_3){if(isc.Offline&&isc.Offline.isOffline()&&this.contactsServer()){isc.logWarn("Data cannot be saved because you are not online");return}
this.performDSOperation("update",_1,_2,_3)}
,isc.A.removeData=function isc_DataSource_removeData(_1,_2,_3){if(isc.Offline&&isc.Offline.isOffline()&&this.contactsServer()){isc.logWarn("Data cannot be saved because you are not online");return}
var _4=this.getPrimaryKeyFields(),_1=isc.applyMask(_1,_4);this.performDSOperation("remove",_1,_2,_3)}
,isc.A.contactsServer=function isc_DataSource_contactsServer(){return!this.clientOnly&&this.dataProtocol!="clientCustom"}
,isc.A.validateData=function isc_DataSource_validateData(_1,_2,_3){if(!_3)_3={};_3=isc.addProperties(_3,{willHandleError:true});if(_3.validationMode==null)_3.validationMode="full";return this.performDSOperation("validate",_1,_2,_3)}
,isc.A.performCustomOperation=function isc_DataSource_performCustomOperation(_1,_2,_3,_4){if(!_4)_4={};isc.addProperties(_4,{operationId:_1});this.performDSOperation("custom",_2,_3,_4)}
,isc.A.$625=function isc_DataSource__getNextRequestId(){if(!this.$626)this.$626=[this.getID(),"$627"];this.$626[2]=isc.DataSource.$625();return this.$626.join(isc.emptyString)}
,isc.A.performDSOperation=function isc_DataSource_performDSOperation(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;var _5=isc.addProperties({operationType:_1,dataSource:this.ID,data:_2,callback:_3,requestId:this.$625()},_4);if(_5.sortBy!=null){if(!isc.isAn.Array(_5.sortBy))_5.sortBy=[_5.sortBy];if(isc.isAn.Object(_5.sortBy[0])){_5.sortBy=isc.DS.getSortBy(_5.sortBy)}
for(var i=0;i<_5.sortBy.length;i++){var _7=_5.sortBy[i];if(!isc.isA.String(_7))continue;var _8=this.getField(_7.charAt(0)=="-"?_7.substring(1):_7);if(_8&&_8.canSortClientOnly)_5.sortBy[i]=null}
_5.sortBy.removeEmpty();if(_5.sortBy.length==0)delete _5.sortBy}
return this.sendDSRequest(_5)}
,isc.A.sendDSRequest=function isc_DataSource_sendDSRequest(_1){isc.addDefaults(_1,this.getOperationBinding(_1.operationType).requestProperties);isc.addDefaults(_1,this.requestProperties);var _2=this.getDataFormat(_1);if(_2=="iscServer"&&!this.clientOnly&&!isc.hasOptionalModule("SCServer")){if(this.dataURL==null&&this.testFileName==null){this.logError("DataSource: "+this.ID+": attempt to use DataSource of type iscServer without SmartClient Server option."+" Please either set clientOnly: true for one-time fetch against"+" dataURL/testFileName or upgrade to SmartClient Pro, Power or Enterprise");return}
this.logInfo("Switching to clientOnly - no SmartClient Server installed.");this.clientOnly=true}
if(_1.bypassCache==null){_1.bypassCache=this.shouldBypassCache(_1)}
if(_1.showPrompt==null){_1.showPrompt=_1.downloadResult?false:this.showPrompt}
if(!this.cacheAllData&&this.autoCacheAllData&&_1.downloadResult!=true&&_1.operationType=="fetch"&&_1.startRow==null&&_1.endRow==null&&(_1.data==null||isc.isAn.emptyObject(_1.data)))
{if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("sendDSRequest: switching on cacheAllData","cacheAllData")}
this.cacheAllData=true;this.$85n=isc.timestamp()}
if(this.fetchingClientOnlyData(_1))return;if(this.logIsDebugEnabled()){this.logDebug("Outbound DSRequest: "+this.echo(_1))}
_1.$376=_1.callback;var _3=_1.operationType;if((_3=="update"||_3=="add")&&(this.sparseUpdates||this.noNullUpdates))
{_1.data=this.$708(_1.data,this);this.$79c(_1.data,_1.oldValues,_3,this)}
if(_2=="iscServer"){this.$754(_1);_1.unconvertedDSRequest=isc.shallowClone(_1);if(this.autoConvertRelativeDates==true){if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Calling convertRelativeDates from sendDSRequest "+"- data is\n\n"+isc.echoFull(_4))}
var _4=this.convertRelativeDates(_1.data);if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Called convertRelativeDates from sendDSRequest "+"- data is\n\n"+isc.echoFull(_4))}
_1.data=_4}
var _5=this.transformRequest(_1);if(_5!==_1){_4=_1.data=_5}
var _6=this.getDataProtocol(_1);if(_6=="clientCustom")return;if((!this.clientOnly&&(!this.cacheAllData||_1.downloadResult))||(_1.cachingAllData||(this.cacheAllData&&_1.operationType&&_1.operationType!="fetch")))
{if(this.logIsInfoEnabled("cacheAllData")&&_1.cachingAllData){this.logInfo("sendDSRequest: processing cacheAllData request","cacheAllData")}
this.addDefaultCriteria(_1,this.getOperationBinding(_1));this.applySendExtraFields(_1);var _7=this.getOperationBinding(_1);if(_7==null)_7={};var _8=_7.defaultParams||this.defaultParams;if(_8){_1.data=isc.addProperties({},_8,_1.data)}
_4=_1.data;return this.performSCServerOperation(_1,_4)}}
var _9=this.getServiceInputs(_1);var _10=(this.cacheAllData&&_1.operationType=="fetch"&&!_1.cachingAllData&&!_1.downloadResult);if(_9.dataProtocol=="clientCustom"&&!_10)return;var _11=isc.addProperties({},_1,_9);_11._dsRequest=_1;if(_9.data==null)_11.data=null;if(this.clientOnly||_10){_11.clientOnly=true;_11.callback={target:this,methodName:"$50e"};isc.RPC.sendRequest(_11);return}
var _12=this.getOperationBinding(_1);_11.transport=_12.dataTransport||this.dataTransport;if(_11.transport=="scriptInclude"){_11.callback={target:this,methodName:"$377"};if(!_11.callbackParam){_11.callbackParam=_12.callbackParam||this.callbackParam}
isc.rpc.sendRequest(_11);return}
var _2=this.getDataFormat(_1);if(_2=="xml"){var _13=_11.spoofedResponse;if(!_13){_11.callback={target:this,method:this.$378};isc.xml.getXMLResponse(_11)}else{var _14=this;isc.Timer.setTimeout(function(){_14.$378(isc.xml.parseXML(_13),_13,{status:0,httpResponseCode:200,data:_13},_11)})}}else if(_2=="json"){_11.callback={target:this,method:this.$379};isc.rpc.sendProxied(_11)}else if(_2=="csv"){_11.callback={target:this,method:this.$69k};isc.rpc.sendProxied(_11)}else{_11.serverOutputAsString=true;_11.callback={target:this,method:this.$38a};isc.rpc.sendProxied(_11)}}
,isc.A.$79c=function isc_DataSource__applySparseAndNoNullUpdates(_1,_2,_3,_4){var _5=_4||this;if(!_5.noNullUpdates){if(!_5.sparseUpdates)return;if(_2==null)return}
var _6={__ref:true,$81y:true,$29a:true};for(var _7 in _1){if(isc.isA.Function(_1[_7]))continue;if(_6[_7]==true)continue;if(isc.isAn.Instance(_1[_7])||isc.isA.Class(_1[_7]))continue;var _8=_1[_7];if(_5.noNullUpdates&&_8===null){if(_3=="add"&&_5.omitNullDefaultsOnAdd==true){delete _1[_7]}else{var _9=_5.getField(_7),_10;if(_9&&_9.nullReplacementValue!==_10){_1[_7]=_9.nullReplacementValue}else{var _11=isc.SimpleType.getBaseType(_9.type,_5);if(_11=="integer"){_1[_7]=_5.nullIntegerValue}else if(_11=="float"){_1[_7]=_5.nullFloatValue}else if(_11=="date"||_11=="time"){_1[_7]=_5.nullDateValue}else if(_11=="boolean"){_1[_7]=_5.nullBooleanValue}else{_1[_7]=_5.nullStringValue}}}}else if(_5.sparseUpdates&&_3=="update"){if(_2==null)continue;var _12=_2[_7];if(_12==null&&!(_8==null))continue;if(_5!=null){var _9=_5.getField(_7);if(_9&&_9.primaryKey)continue}
if(isc.isA.Date(_8)&&Date.compareDates(_8,_12)==0){delete _1[_7]}else if(isc.isAn.Array(_8)){for(var i=0;i<_8.length;i++){this.$79c(_8[i],_12[i],_3,_9==null?null:isc.DataSource.get(_9.type));var _14=0;for(var _15 in _8[i])_14++;if(_14==0)delete _8[i]}
var _16=false;for(var i=0;i<_8.length;i++){if(_8[i]!=null){_16=true;break}}
if(!_16)delete _1[_7]}else if(isc.isAn.Object(_8)){this.$79c(_8,_12,_3,_9==null?null:isc.DataSource.get(_9.type));var _14=0;for(var _15 in _8)_14++;if(_14==0)delete _1[_7]}else if(_8==_12){delete _1[_7]}}}}
,isc.A.$708=function isc_DataSource__cloneValues(_1,_2,_3){if(_1==null)return;var _4=_2?_2.deepCloneOnEdit:this.deepCloneOnEdit,_5=_4==null?isc.DataSource.deepCloneOnEdit:_4;if(isc.isAn.Array(_1)){var _6=[];for(var i=0;i<_1.length;i++){var _8=_1[i];if(isc.isA.Function(_8))continue;if(isc.isAn.Instance(_1[_10])||isc.isA.Class(_1[_10]))continue;if(_8==null||isc.isA.String(_8)||isc.isA.Boolean(_8)||isc.isA.Number(_8))
{_6[_6.length]=_8}else if(isc.isA.Date(_8)){_6[_6.length]=new Date(_8.getTime())}else if(isc.isAn.Object(_8)){_6[_6.length]=this.$708(_8,_2,_3)}}
return _6}
var _6={};if(_1.$42c!=null){_1=isc.JSONEncoder.$42b(_1)}
var _9={__ref:true,$81y:true,$29a:true};if(isc.DataSource.cloneValuesSafely){if(!_3)_3=[];if(_3.contains(_1)){_6=_1;return}
_3.add(_1)}
for(var _10 in _1){if(isc.isA.Function(_1[_10]))continue;if(_9[_10]==true)continue;if(isc.isAn.Instance(_1[_10])||isc.isA.Class(_1[_10]))continue;var _11=_1[_10];if(isc.isA.Date(_11)){_6[_10]=_11.duplicate()}else if(isc.isAn.Object(_11)){var _12=_2?_2.getField(_10):null;if(!_12){_6[_10]=_1[_10]}else{if(_12.deepCloneOnEdit==true||(_12.deepCloneOnEdit==null&&_5))
{if(isc.DataSource.cloneValuesSafely){if(_3.contains(_11)){_6[_10]=_1[_10];continue}
_3.add(_11)}
_6[_10]=this.$708(_11,isc.DataSource.get(_12.type))}else{_6[_10]=_1[_10]}}}else{_6[_10]=_1[_10]}}
return _6}
,isc.A.fulfilledFromOffline=function isc_DataSource_fulfilledFromOffline(_1){var _2=_1.unconvertedDSRequest?_1.unconvertedDSRequest:_1;if(this.useOfflineStorage&&isc.Offline){var _3=_1.dataSource+"."+_1.operationType;if(isc.Offline.isOffline()){var _4=isc.Offline.getResponse(_2);this.logInfo("currently offline, for request: "+_3+" found cached response: "+this.echoLeaf(_4),"offline");if(this.useOfflineResponse&&!this.useOfflineResponse(_2,_4)){this.logInfo("User-written useOfflineResponse() method returned false; "+"not using cached response","offline");_4=null}
this.processOfflineResponse(_1,_4);return true}else if(_2.useOfflineCache||_2.useOfflineCacheOnly){var _4=isc.Offline.getResponse(_2);if(_4!=null){this.logInfo("request: "+_3+", returning cached offline response","offline");if(this.useOfflineResponse&&!this.useOfflineResponse(_2,_4)){this.logInfo("User-written useOfflineResponse() method returned false; "+"not using cached response","offline");_4=null}
this.processOfflineResponse(_1,_4);return true}else if(_1.useOfflineCacheOnly){this.logInfo("request: "+_3+": useOfflineCacheOnly: no response available","offline");this.processOfflineResponse(_1);return true}
this.logInfo("request: "+_3+", no cached response, proceeding with network request","offline")}}
return false}
,isc.A.processOfflineResponse=function isc_DataSource_processOfflineResponse(_1,_2){if(!_2)_2={status:isc.RPCResponse.STATUS_OFFLINE,data:isc.DataSource.offlineMessage,clientContext:_1.clientContext};_2.isCachedResponse=true;this.fireResponseCallbacks(_2,_1)}
,isc.A.performSCServerOperation=function isc_DataSource_performSCServerOperation(_1,_2){this.logWarn("Attempt to perform iscServer request requires options SmartClient server "+"support - not present in this build.\nRequest details:"+this.echo(_1));return}
,isc.A.getSchema=function isc_DataSource_getSchema(_1,_2){var _3=this.getSchemaSet();if(_3!=null){var _4=_3.getSchema(_1,_2);if(_4!=null)return _4}
var _5=this.getWebService();if(isc.isA.WebService(_5))return _5.getSchema(_1,_2);return isc.DS.get(_1,null,null,_2)}
,isc.A.getTitle=function isc_DataSource_getTitle(){return this.title||this.ID}
,isc.A.getPluralTitle=function isc_DataSource_getPluralTitle(){return this.pluralTitle||(this.getTitle()+"s")}
,isc.A.getTitleField=function isc_DataSource_getTitleField(){if(this.titleField==null){var _1=isc.getKeys(this.getFields());var _2=_1.map("toLowerCase");for(var i=0;i<this.defaultTitleFieldNames.length;i++){var _4=_2.indexOf(this.defaultTitleFieldNames[i]);if(_4!=-1&&!this.getField(_1[_4]).hidden){this.titleField=_1[_4];break}}
if(this.titleField==null){for(var i=0;i<_1.length;i++){if(this.getField(_1[i]).hidden)continue;this.titleField=_1[i];break}
if(this.titleField==null)this.titleField=_1[0]}}
return this.titleField}
,isc.A.getIconField=function isc_DataSource_getIconField(){var _1;if(this.iconField===_1){this.iconField=null;var _2=isc.getKeys(this.getFields());var _3=["picture","thumbnail","icon","image","img"];for(var i=0;i<_3.length;i++){var _5=_3[i],_6=this.getField(_5);if(_6&&isc.SimpleType.inheritsFrom(_6.type,"image")){this.iconField=_5}}}
return this.iconField}
,isc.A.initViewSources=function isc_DataSource_initViewSources(){var _1=this.fields={};for(var _2 in this.sources){var _3=isc.DS.get(_2);if(!_3)continue;var _4=this.sources[_2].fields;for(var _5 in _4){var _6=_4[_5],_7=null;if(_6=="*"){_7=_3.fields[_5]}else if(isc.isA.String(_6)){_7=_3.fields[_6]}else if(isc.isAn.Object(_6)){_7=isc.addProperties({},_3.fields[_3.fields[_6.field]]);isc.addProperties(_7,_6)}
if(_7)_1[_5]=_7}}}
,isc.A.inheritsSchema=function isc_DataSource_inheritsSchema(_1){if(_1==null)return false;if(isc.isA.String(_1))_1=this.getSchema(_1);if(_1==this||_1==isc.DS.get("Object"))return true;if(!this.hasSuperDS())return false;return this.superDS().inheritsSchema(_1)}
,isc.A.getInheritedProperty=function isc_DataSource_getInheritedProperty(_1){if(this[_1])return this[_1];var _2=this.superDS();return _2?_2.getInheritedProperty(_1):null}
,isc.A.hasSuperDS=function isc_DataSource_hasSuperDS(){if(this.inheritsFrom)return true;return false}
,isc.A.superDS=function isc_DataSource_superDS(){if(this.hasSuperDS())return this.getSchema(this.inheritsFrom);return null}
,isc.A.getField=function isc_DataSource_getField(_1){if(isc.isAn.Object(_1))_1=_1.name;var _2=this.getFields();return _2?_2[_1]:null}
,isc.A.getFieldForDataPath=function isc_DataSource_getFieldForDataPath(_1){if(isc.isAn.Object(_1))_1=_1.dataPath;if(!_1)return null;var _2=_1.trim(isc.Canvas.$70l).split(isc.Canvas.$70l);var _3=this;for(var i=0;i<_2.length;i++){var _5=_3.getField(_2[i]);if(!_5)return null;_3=isc.DataSource.get(_5.type)}
return _5}
,isc.A.getDataSourceForDataPath=function isc_DataSource_getDataSourceForDataPath(_1){if(isc.isAn.Object(_1))_1=_1.dataPath;if(!_1)return null;var _2=_1.trim(isc.Canvas.$70l).split(isc.Canvas.$70l);var _3=this;for(var i=0;i<_2.length-1;i++){var _5=_3.getField(_2[i]);if(!_5)return null;_3=isc.DataSource.get(_5.type)}
return _3}
,isc.A.getFieldByTitle=function isc_DataSource_getFieldByTitle(_1){var _2=isc.getValues(this.getFields());for(var i=0;i<_2.length;i++){var _4=_2[i],_5=_4.title||isc.DS.getAutoTitle(_2[i].name);if(_5==_1)return _4}
return null}
,isc.A.getDisplayValue=function isc_DataSource_getDisplayValue(_1,_2){var _3=this.getField(_1);if(_3==null)return _2;if(isc.isAn.Object(_3.valueMap)&&!isc.isAn.Array(_3.valueMap)&&isc.propertyDefined(_3.valueMap,_2))
{return _3.valueMap[_2]}
return _2}
,isc.A.getFieldNames=function isc_DataSource_getFieldNames(_1){if(isc.$cv)arguments.$cw=this;if(!_1)return isc.getKeys(this.getFields());var _2=this.getFields(),_3=[],_4=0;for(var _5 in _2){if(!_2[_5].hidden)_3[_4++]=_5}
return _3}
,isc.A.getLocalFields=function isc_DataSource_getLocalFields(_1){if(this.$38k)return this.fields;if(_1)return this.fields;this.$38l();this.$63p();this.$38k=true;return this.fields}
,isc.A.getFields=function isc_DataSource_getFields(){if(isc.$cv)arguments.$cw=this;if(this.mergedFields)return this.mergedFields;if(!this.hasSuperDS()||this==this.superDS()){return this.mergedFields=this.getLocalFields()}
if(!this.superDS()){this.logWarn("DataSource "+this.ID+" inheritsFrom "+this.inheritsFrom+", but there is no DataSource of that name currently loaded. "+"Ignoring the inheritsFrom declaration.");return this.mergedFields=this.getLocalFields()}
var _1=this.superDS();if(this.showLocalFieldsOnly||this.restrictToLocalFields){this.useParentFieldOrder=false}
var _2=isc.addProperties({},this.getLocalFields()),_3;if(!this.useParentFieldOrder){_3=_2}else{_3={}}
var _4=(this.restrictToLocalFields?isc.getKeys(this.getLocalFields()):_1.getFieldNames());for(var i=0;i<_4.length;i++){var _6=_4[i],_7=_2[_6];if(_7!=null){var _8=_1.getField(_6);if(_8.hidden&&_7.hidden==null&&!_7.inapplicable)
{_7.hidden=false}
if(_8.visibility!=null&&_7.visibility==null&&!_7.inapplicable&&!_7.hidden&&_8.visibility=="internal")
{_7.visibility="external"}
var _9=isc.addProperties({},_7);_3[_6]=_1.combineFieldData(_7);if(_9.$76s)_3[_6].title=_8.title}else{if(this.showLocalFieldsOnly){_3[_6]=isc.addProperties({},_1.getField(_6));_3[_6].hidden="true"}else{_3[_6]=_1.getField(_6)}}
if(this.useParentFieldOrder)delete _2[_6]}
if(this.useParentFieldOrder)isc.addProperties(_3,_2);if(this.restrictToLocalFields&&isc.Schema&&isc.isA.Schema(this)){var _10=_1.getFieldNames();for(var i=0;i<_10.length;i++){var _6=_10[i],_11=_1.getField(_6);if(_11.xmlAttribute){_3[_6]=_3[_6]||_11}}}
return this.mergedFields=_3}
,isc.A.hasFields=function isc_DataSource_hasFields(){if(this.fields)return true;else if(this.inheritsFrom){var _1=this;while(_1.inheritsFrom){_1=isc.DataSource.get(this.inheritsFrom);if(_1.fields)return true}}
return false}
,isc.A.getFlattenedFields=function isc_DataSource_getFlattenedFields(_1,_2,_3){_1=_1||{};var _4=this.getFieldNames();for(var i=0;i<_4.length;i++){var _6=_4[i],_7=this.getField(_6);if(!this.fieldIsComplexType(_6)){if(_1[_6]==null){_7.sourceDS=this.ID;if(_2){_7=isc.addProperties({},_7);_7[_3]=_2+"/"+_6}
_1[_6]=_7}}else{var _8=this.getFieldDataSource(_7);if(_2!=null)_2=(_2?_2+"/":"")+_6;_8.getFlattenedFields(_1,_2,_3)}}
return _1}
,isc.A.fieldIsComplexType=function isc_DataSource_fieldIsComplexType(_1){var _2=this.getField(_1);if(_2==null)return false;return(_2.type!=null&&!_2.xmlAttribute&&this.getSchema(_2.type)!=null)||this.fieldIsAnonDataSource(_2)}
,isc.A.fieldIsAnonDataSource=function isc_DataSource_fieldIsAnonDataSource(_1){if(!_1.fields)return false;var _2=isc.isAn.Array(_1.fields)?_1.fields:isc.getValues(_1.fields);return _2.length>0&&isc.isAn.Object(_2.get(0))}
,isc.A.getFieldDataSource=function isc_DataSource_getFieldDataSource(_1,_2){if(!_1)return null;if(this.fieldIsAnonDataSource(_1)){if(!_1.$67z){var _3=isc.DataSource.create({"class":"DataSource",fields:_1.fields});_1.$67z=_3}
return _1.$67z}
return _1.type!=null?this.getSchema(_1.type,_2):null}
,isc.A.findTagOfType=function isc_DataSource_findTagOfType(_1,_2,_3){var _4=this.getFieldNames();for(var i=0;i<_4.length;i++){var _6=_4[i],_7=this.getField(_6);if(_7.type==_1)return[this,_6,_2,_3];if(this.fieldIsComplexType(_6)){var _8=this.getFieldDataSource(_7),_9=_8.findTagOfType(_1,this,_6);if(_9)return _9}}}
,isc.A.getTextContentField=function isc_DataSource_getTextContentField(){return this.getField(this.textContentProperty)}
,isc.A.hasXMLElementFields=function isc_DataSource_hasXMLElementFields(_1){_1=_1||this.textContentProperty;var _2=this.getFieldNames();for(var i=0;i<_2.length;i++){if(_2[i]==_1)continue;if(this.getField(_2[i]).xmlAttribute)continue;return true}
return false}
,isc.A.getGroups=function isc_DataSource_getGroups(){var _1=this;while(_1.groups==null&&_1.hasSuperDS())_1=_1.superDS();return _1.groups}
,isc.A.getObjectField=function isc_DataSource_getObjectField(_1,_2,_3){if(!_1)return null;var _4=this.getLocalFields(),_5=isc.getKeys(_4).reverse(),_6=isc.DataSource.getNearestSchemaClass(_1);if(_3==null)_3={};var _7=-1,_8=null;for(var i=0;i<_5.length;i++){var _10=_5[i],_11=_4[_10],_12;if(isc.endsWith(_10,this.$dr)||isc.endsWith(_10,this.$dq))continue;if(!_2&&(_3[_10]||_11.advanced||_11.inapplicable||_11.hidden||(_11.visibility!=null&&_11.visibility=="internal")))
{_3[_10]=_10;continue}
if(!_6&&_11.type==_1)return _10;if(_6&&_6.isA(_11.type)){_12=isc.DS.getInheritanceDistance(_11.type,_1);if(_8==null||_12<_7){_8=_10;_7=_12}}}
if(_8!=null){if(_7==0||!this.hasSuperDS()){return _8}else{var _13=this.superDS().getObjectField(_1,_2,_3);if(_13){var _14=this.getField(_13).type,_15=isc.DS.getInheritanceDistance(_14,_1)}
return(_13&&(_15<_7))?_13:_8}}else if(this.hasSuperDS()){return this.superDS().getObjectField(_1,_2,_3)}
return null}
,isc.A.getLocalPrimaryKeyFields=function isc_DataSource_getLocalPrimaryKeyFields(){if(!this.primaryKeys){this.primaryKeys={};var _1=this.getFields();for(var _2 in _1){var _3=_1[_2];if(_3.primaryKey){this.primaryKeys[_2]=_3}}}
return this.primaryKeys}
,isc.A.filterPrimaryKeyFields=function isc_DataSource_filterPrimaryKeyFields(_1){var _2=this.getPrimaryKeyFields();return isc.applyMask(_1,isc.getKeys(_2))}
,isc.A.filterDSFields=function isc_DataSource_filterDSFields(_1){var _2=this.getFields();return isc.applyMask(_1,isc.getKeys(_2))}
,isc.A.recordHasAllKeys=function isc_DataSource_recordHasAllKeys(_1){var _2=this.getPrimaryKeyFields();for(var _3 in _2){if(_1[_3]==null)return false}
return true}
,isc.A.getForeignKeysByRelation=function isc_DataSource_getForeignKeysByRelation(_1,_2){var _3=this.getForeignKeyFields(_2);if(!_3)return{};var _4={};for(var _5 in _3){var _6=_3[_5];var _7=isc.DataSource.getForeignFieldName(_6);var _8=_1[_7];if(_8||_8===0)_4[_5]=_8}
return _4}
,isc.A.getPrimaryKeyFields=function isc_DataSource_getPrimaryKeyFields(){if(!this.mergedPrimaryKeys){this.mergedPrimaryKeys={};if(this.hasSuperDS()){isc.addProperties(this.mergedPrimaryKeys,this.superDS().getPrimaryKeyFields())}
isc.addProperties(this.mergedPrimaryKeys,this.getLocalPrimaryKeyFields())}
return this.mergedPrimaryKeys}
,isc.A.getForeignKeyFields=function isc_DataSource_getForeignKeyFields(_1){if(isc.isA.DataSource(_1))_1=_1.ID;var _2=this.getFields();if(!_2)return null;var _3={};for(var _4 in _2){var _5=_2[_4];if(_5.foreignKey){if(_1){var _6=isc.DataSource.getForeignDSName(_5,(_1||this));if(_6!=_1)continue}
_3[_5.name]=_5}}
return _3}
,isc.A.getLocalPrimaryKeyFieldNames=function isc_DataSource_getLocalPrimaryKeyFieldNames(){var _1=this.getLocalPrimaryKeyFields();var _2=[];for(var _3 in _1){_2.add(_3)}
return _2}
,isc.A.getPrimaryKeyFieldNames=function isc_DataSource_getPrimaryKeyFieldNames(){return isc.getKeys(this.getPrimaryKeyFields())}
,isc.A.getPrimaryKeyField=function isc_DataSource_getPrimaryKeyField(){var _1=this.getPrimaryKeyFields();for(var _2 in _1){return _1[_2]}}
,isc.A.getPrimaryKeyFieldName=function isc_DataSource_getPrimaryKeyFieldName(){return this.getPrimaryKeyFieldNames()[0]}
,isc.A.addChildDataSource=function isc_DataSource_addChildDataSource(_1){var _2=this.$38m=(this.$38m||[]);_2.add(_1)}
,isc.A.getChildDataSources=function isc_DataSource_getChildDataSources(){return this.$38m}
,isc.A.getChildDataSource=function isc_DataSource_getChildDataSource(_1){var _2=this.getChildDataSources();if(_2==null)return null;var _3;for(var i=0;i<_2.length;i++){if(!_2[i]||(_1&&_2[i]==this))continue;if(!_3){_3=_2[i]}else if(_3!=_2[i]){this.logInfo("getChildDatasource(): This DataSource has multiple child "+"DataSources defined making getChildDataSource() ambiguous. Returning the "+"first child dataSource only - call getChildDataSources() to retrieve a "+"complete list.");break}}
return _3}
,isc.A.getTreeRelationship=function isc_DataSource_getTreeRelationship(_1,_2){if(isc.isA.String(_1))_1=this.getSchema(_1);var _3=this.getFields();if(_2==null){for(var _4 in _3){var _5=_3[_4];if(_5.foreignKey!=null){if(!_1||(_1.getID()==isc.DataSource.getForeignDSName(_5,this)))
{_2=_4;break}}}}
var _6;if(_2==null&&_1){_2=_6=isc.getKeys(this.fields).intersect(isc.getKeys(_1.fields))[0];this.logWarn("no foreign key declaration, guessing tree relationship "+"is on field name: "+_2+" which occurs in both DataSources")}
var _7;if(_2)_7=_3[_2];if(_7==null){this.logDebug("getTreeRelationship(): Unable to find foreignKeyField."+"foreignKeyFieldName specified as:"+_2)}
if(!_1){if(!_7)_1=this;else{var _8=isc.DataSource.getForeignDSName(_7,this);_1=this.getSchema(_8)}}
if(!_6)_6=_7?isc.DataSource.getForeignFieldName(_7):null;if(_6==null){var _9=_1.getPrimaryKeyFieldNames();if(isc.isAn.Array(_9)){if(_9.length>1){this.logWarn("getTreeRelationship: dataSource '"+_1.ID+"' has multi-field primary key, which is not "+"supported for tree viewing.  Using field '"+_9[0]+"' as the only primary key field")}
_9=_9[0]}
_6=_9}
var _10;var _11;if(this.childrenField)_11=this.childrenField;for(_4 in _3){var _7=_3[_4];if(_7.isFolderProperty)_10=_4;if(_7.childrenProperty)_11=_4;if(_11==_4&&(_7.multiple==null)){_7.multiple=true}}
var _12={childDS:this,parentDS:_1,isFolderProperty:_10}
if(_2){_12.parentIdField=_2;_12.idField=_6}
if(_11)_12.childrenProperty=_11;if(_11==null&&_2==null){this.logInfo("getTreeRelationship(): No specified foreignKeyField or childrenProperty.")}
if(_1==this){var _13=_2?this.getField(_2).rootValue:null;if(_13==null)_12.rootValue=null;else _12.rootValue=_13}
return _12}
,isc.A.combineFieldOrders=function isc_DataSource_combineFieldOrders(_1,_2,_3){var _4=[];this.$38n(_2,0,_1,_4,_3);for(var _5 in _1){var _6=_1[_5],_7=_2.findIndex(this.$375,_5);if(_7!=-1){var _8=_2[_7],_9=this.combineFieldData(_8);if(_3==null||_3(_9,this))_4.add(_9);this.$38n(_2,_7+1,_1,_4,_3)}else{if(_3==null||_3(_6,this)){_4.add(isc.addProperties({},_6))}}}
return _4}
,isc.A.$38n=function isc_DataSource__addNonDSFields(_1,_2,_3,_4,_5){for(var i=_2;i<_1.length;i++){var _7=_1[i];if(_7.name!=null&&_3[_7.name]!=null)return;if(_5==null||!_5(_7,this))continue;isc.SimpleType.addTypeDefaults(_7);_4.add(_7)}}
,isc.A.combineFieldData=function isc_DataSource_combineFieldData(_1,_2){var _3;if(isc.isAn.Object(_2))_3=_2;else _3=this.getField(_2||_1.name);return isc.DataSource.combineFieldData(_1,_3)}
,isc.A.$38l=function isc_DataSource__addTypeDefaults(_1){if(_1==null)_1=this.fields;for(var _2 in _1){var _3=_1[_2];if(_3&&_3.required==null&&_3.xmlRequired!=null&&_3.xmlNonEmpty!=null)
{_3.required=_3.xmlRequired&&_3.xmlNonEmpty}
if(_3&&(_3.childrenProperty||_3.name==this.childrenField)){if(!_3.type)_3.type=this.ID}
isc.SimpleType.addTypeDefaults(_3,this);this.$75f(_3)}}
,isc.A.$75f=function isc_DataSource__addFieldValidators(_1){var _2={type:"required"};if(_1.required){var _3=isc.addProperties({},_2),_4=_1.requiredMessage||this.requiredMessage;if(_4!=null)_3.errorMessage=_4;if(!_1.validators){_1.validators=[_3]}else{if(!isc.isAn.Array(_1.validators)){_1.validators=[_1.validators]}
if(_1.validators.$69){_1.validators=_1.validators.duplicate()}
_1.validators.add(_3)}}}
,isc.A.$63p=function isc_DataSource__autoDeriveTitles(){if(!this.autoDeriveTitles)return;for(var _1 in this.fields){var _2=this.fields[_1];if(_2.title!=null)continue;_2.title=this.getAutoTitle(_1);_2.$76s=true}}
,isc.A.getAutoTitle=function isc_DataSource_getAutoTitle(_1){return isc.DataSource.getAutoTitle(_1)}
,isc.A.getType=function isc_DataSource_getType(_1){if(this.schemaNamespace){var _2=isc.SchemaSet.get(this.schemaNamespace),_3=_2.getSimpleType(_1);if(_3)return _3}
var _3=isc.SimpleType.getType(_1);if(_3!=null)return _3;if(this.types&&this.types[_1])return this.types[_1];return null}
,isc.A.firstCacheAllDataRequest=function isc_DataSource_firstCacheAllDataRequest(_1){if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("firstCacheAllDataRequest: refreshing cache","cacheAllData")}
this.$498=[_1];if(this.$839==null){var _2=this.transformRequest,_3=this.transformResponse,_4=this.$ba;if(_4){if(_4.transformRequest){_2=this[isc.$ah+"transformRequest"]}
if(_4.transformResponse){_3=this[isc.$ah+"transformResponse"]}}
this.transformServerRequest=_2;this.transformServerResponse=_3;this.addMethods({transformRequest:function(_1){var _5=(_1.cachingAllData||(_1.operationType&&_1.operationType!="fetch"));if(!_5)return _1;return this.transformServerRequest(_1)},transformResponse:function(_9,_1,_10){var _5=(_1.cachingAllData||(_1.operationType&&_1.operationType!="fetch"));if(!_5){var _6=this.$85n,_7=_1.$85o;if(!_6||!_7||_7>=_6)
return _9}
return this.transformServerResponse(_9,_1,_10)}});this.$839=true}
this.cacheResultSet=isc.ResultSet.create({dataSource:this,fetchMode:"local",allRows:this.cacheData?this.cacheData:null,cachingAllData:true,dataArrived:function(_9,_10){if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("cacheAllData - cacheResultSet.dataArrived: startRow/endRow: "+_9+"/"+_10)}
if(this.lengthIsKnown()){var _8=this.getDataSource();if(_8.cacheResultSet==null)return;_8.cacheLastFetchTime=new Date().getTime();if(_8.clientOnly)_8.testData=_8.cacheData=this.getAllRows();_8.processDeferredRequests()}}});if(!this.cacheData){if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("firstCacheAllDataRequest: issuing fetch","cacheAllData")}
this.cacheResultSet.get(0);return true}else{if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("firstCacheAllDataRequest: updating last fetch time","cacheAllData")}
this.cacheLastFetchTime=new Date().getTime();if(this.clientOnly)this.testData=this.cacheData;this.processDeferredRequests();return true}}
,isc.A.fetchingClientOnlyData=function isc_DataSource_fetchingClientOnlyData(_1){if(_1.cachingAllData){return false}
if(_1.downloadResult){return false}
var _2=(this.useTestDataFetch==null?this.shouldUseTestDataFetch():this.useTestDataFetch);if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("fetchingClientOnlyData: useTestDataFetch is "+_2,"cacheAllData")}
if(this.clientOnly){_1.clientOnly=true;if(this.testData&&!this.cacheData)this.cacheData=this.testData;else if(this.cacheData&&!this.testData)this.testData=this.cacheData}
if(this.$498){this.$498.add(_1);return true}
if(_2==false&&this.clientOnly&&this.hasTestData())
_2=true;if(!_2&&((this.cacheAllData&&this.cacheNeedsRefresh())||(this.clientOnly&&!this.testData&&this.hasTestData())))
{return this.firstCacheAllDataRequest(_1)}else{if(this.clientOnly&&!this.testData&&(this.testFileName||this.dataURL)||(this.cacheAllData&&this.cacheNeedsRefresh())){if(this.logIsInfoEnabled("cacheAllData")){this.logInfo("fetchingClientOnlyData: issuing oneTimeDS fetch","cacheAllData")}
this.$498=[_1];var _3=this.dataURL||this.testFileName;var _4=this.getDataFormat(_1);if(_4=="iscServer")_4=_3.match(/\.xml$/i)?"xml":"json";var _5=this.getOperationBinding(_1);var _6=this.transformRequest,_7=this.transformResponse,_8=this.$ba;if(_8){if(_8.transformRequest){_6=this[isc.$ah+"transformRequest"]}
if(_8.transformResponse){_7=this[isc.$ah+"transformResponse"]}}
var _9=_5.recordName||this.recordName||(this.inheritsFrom?(isc.isA.String(this.inheritsFrom)?this.inheritsFrom:this.inheritsFrom.ID):this.ID);var _10=isc[this.Class].create({ID:this.ID+"$499",inheritsFrom:this.ID,dataURL:_3,dataFormat:_4,recordXPath:this.recordXPath,transformRequest:_6,transformResponse:_7,recordName:_9,showPrompt:this.showPrompt});this.logInfo("clientOnly datasource performing one-time "+_4+" fetch via: "+_3);this.addProperties({transformRequest:isc.DataSource.getInstanceProperty("transformRequest"),transformResponse:isc.DataSource.getInstanceProperty("transformResponse")});var _11=this;if(this.cacheAllData){_10.cacheAllData=false}
_10.sendDSRequest({operationType:"fetch",willHandleError:true,callback:function(_13,_14){var _12;if(_13.status!=isc.DSResponse.STATUS_SUCCESS){_11.logWarn("one-time fetch failed with status: "+_13.status+" and messsage: "+(_14?_14:"N/A")+".  Initializing an empty Array as testData.");_12=[]}else{_11.logInfo("One-time fetch complete: "+(_14?_14.length:"null")+" records");_12=_11.initializeSequenceFields(_14)}
if(_11.cacheAllData){_11.cacheLastFetchTime=new Date().getTime();_11.cacheResultSet=isc.ResultSet.create({dataSource:_11.ID,fetchMode:"local",allRows:_12});_11.cacheLastFetchTime=new Date().getTime()}
if(_11.clientOnly){_11.cacheData=_11.testData=_12}
_11.processDeferredRequests();_10.destroy()}});return true}else{return false}}}
);isc.evalBoundary;isc.B.push(isc.A.shouldUseTestDataFetch=function isc_DataSource_shouldUseTestDataFetch(){return this.clientOnly==true&&this.cacheAllData!=true&&(this.dataURL!=null||this.testFileName!=null)}
,isc.A.hasTestData=function isc_DataSource_hasTestData(){return this.dataURL!=null||this.testFileName!=null}
,isc.A.getClientOnlyResponse=function isc_DataSource_getClientOnlyResponse(_1,_2){_2=_2||this.testData;if(_2&&!this.testData&&this.clientOnly)
this.cacheData=this.testData=_2;if(!_2||isc.isA.String(_2)){if(isc.isA.String(_2)){this.logInfo(this.ID+" datasource: using testData property as data");this.cacheData=this.testData=isc.eval(_2)}else if(window[this.ID+"TestData"]){this.logInfo(this.ID+" datasource: using "+this.ID+"TestData object as data");this.cacheData=this.testData=window[this.ID+"TestData"]}else{this.logInfo(this.ID+" datasource: testData property and "+this.ID+"TestData object not found, using empty list as data");this.cacheData=this.testData=[]}
_2=this.testData}
var _3=_1.operationType,_4={status:0};switch(_3){case"fetch":case"select":case"filter":var _5=_1.data;if(isc.isAn.Array(_5))_5=_5[0];var _6=this.applyFilter(_2,_5,_1),_7=_6;if(_1.startRow!=null){var _8=_1.startRow,_9=_1.endRow,_10=_6.length;var _11=isc.shallowClone(_1.sortBy);if(_11){if(!isc.isAn.Array(_11))_11=[_11];if(isc.isAn.Object(_11[0])){_11=isc.DS.getSortBy(_11)}
var _12=[];for(var i=0;i<_11.length;i++){var _14=true;if(_11[i].startsWith("-")){_11[i]=_11[i].substring(1);_14=false}
_12[i]=_14}
_6.sortByProperties(_11,_12)}
_9=Math.min(_9,_10-1);_7=_6.slice(_8,_9+1);_4.startRow=_8;_4.endRow=_9;_4.totalRows=_10}
if(this.copyLocalResults){for(var i=0;i<_7.length;i++){_7[i]=isc.addProperties({},_7[i])}}
_4.data=_7;break;case"remove":case"delete":var _15=this.findByKeys(_1.data,_2);if(_15==-1){this.logWarn("clientOnly remove operation: Unable to find record matching criteria:"+this.echo(_1.data))}else{_2.removeAt(_15);_4.data=isc.addProperties({},_1.data)}
break;case"add":case"insert":var _16=isc.addProperties({},_1.data);_16=this.applySequenceFields(_16);_2.add(_16);_4.data=isc.addProperties({},_16);break;case"replace":case"update":var _15=this.findByKeys(_1.data,_2);if(_15==-1){this.logWarn("clientOnly update operation: Unable to find record matching criteria:"+this.echo(_1.data))}else{var _16=_2[_15];var _17=_1.data,_18;if(_17&&_17.__ref){_18=_17.__ref;delete _17.__ref}
isc.addProperties(_16,_1.data);if(_18)_17.__ref=_18;_4.data=isc.addProperties({},_16)}
break;case"validate":default:break}
return _4}
,isc.A.getNextSequenceValue=function isc_DataSource_getNextSequenceValue(_1){var _2=this.testData,_3=0;for(var i=0;i<_2.length;i++){var _5=_2[i][_1.name];if(_5!=null&&_5>_3)_3=_5}
return _3+1}
,isc.A.applySequenceFields=function isc_DataSource_applySequenceFields(_1){if(!this.clientOnly){return}
var _2=this.getFields();for(var _3 in _2){var _4=_2[_3];if((_4.type=="sequence"||_4.primaryKey)&&_1[_3]==null){_1[_3]=this.getNextSequenceValue(_4)}}
return _1}
,isc.A.initializeSequenceFields=function isc_DataSource_initializeSequenceFields(_1){if(!isc.isAn.Array(_1))return;var _2=this.getFields();var _3=[];for(var _4 in _2){if(_2[_4].type=="sequence"||_2[_4].primaryKey)_3.add(_4)}
for(var i=0;i<_1.length;i++){for(var j=0;j<_3.length;j++){var _4=_3[j];if(_1[i][_4]==null)_1[i][_4]=i}}
return _1}
,isc.A.findByKeys=function isc_DataSource_findByKeys(_1,_2,_3,_4){return _2.findByKeys(_1,this,_3,_4)}
,isc.A.applyFilter=function isc_DataSource_applyFilter(_1,_2,_3){var _4=[];if(!_1||_1.length==0)return _4;if(this.isAdvancedCriteria(_2)){var _5=this.normalizeAdvancedCriteria(_2);return this.recordsMatchingAdvancedFilter(_1,_5,_3)}
return this.recordsMatchingFilter(_1,_2,_3)}
,isc.A.recordsMatchingFilter=function isc_DataSource_recordsMatchingFilter(_1,_2,_3){var _4=isc.getKeys(_2),_5=_4.length,_6=[],_7,_8,_9,_10,_11,j;if(_3&&_3.operation&&this.operationBindings){var _13=_3.operation;if(_13.ID==_13.dataSource+"_"+_13.type){var _14=this.operationBindings.find({operationId:null,operationType:_13.type})}else{var _14=this.operationBindings.find({operationId:_3.operation.ID,operationType:_13.type})}
if(_14){var _15=_14.customCriteriaFields;if(isc.isA.String(_15)){_15=_15.split(",");for(var k=0;k<_15.length;k++){_15[k]=_15[k].replace(/^\s+|\s+$/g,'')}}}}
for(var i=0,l=_1.length;i<l;i++){_7=_1[i];if(_7==null)continue;_8=true;for(j=0;j<_5;j++){_9=_4[j];if(_9==null)continue;if(this.dropUnknownCriteria&&!this.getField(_9))continue;var _19=false;if(isc.isA.List(_15)&&_15.contains(_9)){_19=true}
if(!_19&&this.getField(_9).customSQL)continue;_10=isc.DataSource.getPathValue(_7,_9);_11=_2[_9];if(!this.fieldMatchesFilter(_10,_11,_3)){_8=false;break}}
if(_8)_6.add(_7)}
return _6}
,isc.A.recordMatchesFilter=function isc_DataSource_recordMatchesFilter(_1,_2,_3){if(this.isAdvancedCriteria(_2)){return this.recordsMatchingAdvancedFilter([_1],_2,_3).length>0}
return this.recordsMatchingFilter([_1],_2,_3).length>0}
,isc.A.fieldMatchesFilter=function isc_DataSource_fieldMatchesFilter(_1,_2,_3){if(isc.isAn.Array(_2)){if(_2.contains(_1))return true;return false}
if(isc.isA.Date(_1)&&isc.isA.Date(_2)){if(_2.logicalDate)
return(Date.compareLogicalDates(_1,_2)==0);return(Date.compareDates(_1,_2)==0)}
if(!isc.isA.String(_1)&&!isc.isA.String(_2)){if(this.logIsDebugEnabled()){this.logDebug("Direct compare: "+_1+"=="+_2)}
return(_1==_2)}
if(_2==null)_2=isc.emptyString;if(_1==null)_1=isc.emptyString;if(!isc.isA.String(_1))_1=_1.toString();if(!isc.isA.String(_2))_2=_2.toString();if(!this.filterIsCaseSensitive){_1=_1.toLocaleLowerCase();_2=_2.toLocaleLowerCase()}
var _4;if(_3)_4=_3.textMatchStyle;if(!this.supportsTextMatchStyle(_4)){if(!this.$63c)this.$63c={};if(!this.$63c[_4]){this.logWarn("Text match style specified as '"+_4+"': This is not supported for"+" this dataSource - performing a substring match instead");this.$63c[_4]=true}
_4=this.getTextMatchStyle(_4)}
if(_4==this.$45y){return isc.startsWith(_1,_2)}else if(_4==this.$19q){return isc.contains(_1,_2)}else{return _1==_2}}
,isc.A.supportsTextMatchStyle=function isc_DataSource_supportsTextMatchStyle(_1,_2){if(!this.clientOnly&&(this.dataFormat!=this.$50j))return true;return(_1==null||_1==this.$19q||_1==this.$50i||_1==this.$45y)}
,isc.A.getTextMatchStyle=function isc_DataSource_getTextMatchStyle(_1){if(_1==null)_1=this.$50i;if(!this.supportsTextMatchStyle(_1)){_1=this.$19q}
return _1}
,isc.A.compareTextMatchStyle=function isc_DataSource_compareTextMatchStyle(_1,_2){_1=this.getTextMatchStyle(_1);_2=this.getTextMatchStyle(_2);if(_1==_2)return 0;if(_1==this.$50i)return 1;if(_2==this.$50i)return-1;if(_1==this.$45y)return 1;return-1}
,isc.A.compareCriteria=function isc_DataSource_compareCriteria(_1,_2,_3,_4){if(this.logIsInfoEnabled()){this.logInfo("Comparing criteria, oldCriteria:\n"+this.echo(_2)+"\nnewCriteria:\n"+this.echo(_1)+", policy: "+(_4||this.criteriaPolicy))}
if(_2==null)return-1;var _5=this.getTextMatchStyle(_3?_3.textMatchStyle:null);if(this.isAdvancedCriteria(_1)||this.isAdvancedCriteria(_2)){var _6,_7;if(this.isAdvancedCriteria(_1)){if(this.isAdvancedCriteria(_2)){_7=this.compareAdvancedCriteria(_1,_2,_3)}else{var j=0;for(var i in _2)j++;if(j==0)_7=1}
if(_7==_6){_2=isc.DataSource.convertCriteria(_2,_5);_7=this.compareAdvancedCriteria(_1,_2,_3)}}else{_1=isc.DataSource.convertCriteria(_1,_5);_7=this.compareAdvancedCriteria(_1,_2,_3)}
if(_7==_6)_7=-1;_4=_4||this.criteriaPolicy;if(_4=="dropOnShortening"){return _7}else{return _7==0?0:-1}}
_4=_4||this.criteriaPolicy;if(_4=="dropOnShortening"){if(_5==this.$50i){return this.dropOnFieldChange(_1,_2,_3)}else{return this.dropOnShortening(_1,_2,_3)}}else{return this.dropOnChange(_1,_2,_3)}}
,isc.A.dropOnChange=function isc_DataSource_dropOnChange(_1,_2,_3){if(isc.getKeys(_2).length!=isc.getKeys(_1).length)return-1;for(var _4 in _2){var _5=_2[_4],_6=_1[_4];if(isc.isAn.Array(_5)){if(!isc.isAn.Array(_6))return-1;if(_5.length!=_6.length)return-1;if(_5.intersect(_6).length!=_5.length)
{return-1}}else if(isc.isA.Date(_5)&&isc.isA.Date(_6))
{if(_5.getTime()!=_6.getTime())return-1}else if(_5!=_6){return-1}}
return 0}
,isc.A.dropOnFieldChange=function isc_DataSource_dropOnFieldChange(_1,_2,_3){var _4=isc.getKeys(_1),_5=isc.getKeys(_2),_6=_4.length-_5.length;if(_6<0)return-1;for(var _7 in _2){var _8=_2[_7],_9=_1[_7];if(_9==null)return-1;if(isc.isAn.Array(_8)){if(!isc.isAn.Array(_9))return-1;if(_8.length!=_9.length)return-1;if(_8.intersect(_9).length!=_8.length)
{return-1}}else if(isc.isA.Date(_8)&&isc.isA.Date(_9))
{if(_8.getTime()!=_9.getTime())return-1}else if(_8!=_9){return-1}}
if(_6>0){_4.removeList(_5);for(var i=0;i<_4.length;i++){if(this.getField(_4[i])==null)return-1}
return 1}
return 0}
,isc.A.dropOnShortening=function isc_DataSource_dropOnShortening(_1,_2,_3){var _4=isc.getKeys(_1),_5=isc.getKeys(_2),_6=_4.length-_5.length;if(_6<0)return-1;var _7=0;for(var _8 in _2){var _9=_2[_8],_10=_1[_8];if(_10==null)return-1;if(this.getField(_8)==null&&_9!=_10)
return-1;if(isc.isAn.Array(_9)){if(!isc.isAn.Array(_10))return-1;if(_9.length!=_10.length)return-1;if(_9.intersect(_10).length!=_9.length)
{return-1}}else if(isc.isA.String(_9)){if(!isc.isA.String(_10))return-1;if(_10.indexOf(_9)==-1)return-1;if(_9.length>_10.length)return-1;if(_9.length<_10.length)_7=1}else if(isc.isA.Date(_9)&&isc.isA.Date(_10))
{if(_9.getTime()!=_10.getTime())return-1}else if(_9!=_10){return-1}}
if(_6>0){_4.removeList(_5);for(var i=0;i<_4.length;i++){if(this.getField(_4[i])==null)return-1}
return 1}
return _7}
,isc.A.compareDates=function isc_DataSource_compareDates(_1,_2,_3,_4){var _5=this.getField(_3),_6=_4?this.getField(_4):null;if((_5&&_5.type=="datetime")||_6&&_6.type=="datetime")
{return Date.compareDates(_1,_2)}else{return Date.compareLogicalDates(_1,_2)}}
,isc.A.compareValues=function isc_DataSource_compareValues(_1,_2,_3,_4){if(isc.isA.Date(_1)&&isc.isA.Date(_2)){return this.compareDates(_1,_2,_3)}else{var _5=_4&&_1.toLowerCase?_1.toLowerCase():_1,_6=_4&&_2.toLowerCase?_2.toLowerCase():_2;if(_5==null&&_6!=null)return 1;if(_5!=null&&_6==null)return-1;return _5>_6?-1:(_5<_6?1:(_5==_6?0:2))}}
);isc.B._maxIndex=isc.C+169;isc.A=isc.DataSource;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$628=0;isc.A.$71l={sum:function(_1,_2,_3){var _4=0;for(var i=0;i<_2.length;i++){var _6=_1[_2[i].name],_7=parseFloat(_6);if(isc.isA.Number(_7)&&_7==_6){_4+=_7}else{if(_6!=null&&_6!=isc.emptyString){return null}}}
return _4},avg:function(_1,_2,_3){var _4=0,_5=0;for(var i=0;i<_2.length;i++){var _7=_1[_2[i].name],_8=parseFloat(_7);if(isc.isA.Number(_8)&&(_8==_7)){_5+=1;_4+=_8}else{if(_7!=null&&_7!=isc.emptyString){return null}}}
return _5>0?_4/ _5:null},max:function(_1,_2,_3){var _4,_5;for(var i=0;i<_2.length;i++){var _7=_1[_2[i].name];if(isc.isA.Date(_7)){if(_5)return null;if(_4==null)_4=_7.duplicate();else if(_4.getTime()<_7.getTime())_4=_7.duplicate()}else{_5=true;var _8=parseFloat(_7);if(isc.isA.Number(_8)&&(_8==_7)){if(_4==null)_4=_8;else if(_4<_7)_4=_8}else{if(_7!=null&&_7!=isc.emptyString){return null}}}}
return _4},min:function(_1,_2,_3){var _4,_5
for(var i=0;i<_2.length;i++){var _7=_1[_2[i].name];if(isc.isA.Date(_7)){if(_5)return null;if(_4==null)_4=_7.duplicate();if(_7.getTime()<_4.getTime())_4=_7.duplicate()}else{if(_7==null||_7==isc.emptyString)continue;_5=true;var _8=parseFloat(_7);if(isc.isA.Number(_8)&&(_8==_7)){if(_4==null)_4=_8;else if(_4>_7)_4=_8}else{return null}}}
return _4},multiplier:function(_1,_2,_3){var _4=0;for(var i=0;i<_2.length;i++){var _6=_1[_2[i].name],_7=parseFloat(_6);if(isc.isA.Number(_7)&&(_7==_6)){if(i==0)_4=_7;else _4=(_4*_7)}else{return null}}
return _4}};isc.B.push(isc.A.addSearchOperator=function isc_c_DataSource_addSearchOperator(_1){if(!_1||!_1.ID){isc.logWarn("Attempted to add null search operator, or operator with no ID");return}
if(!isc.DataSource.$57z)isc.DataSource.$57z={};var _2=isc.DataSource.$57z,_3;if(_2[_1.ID]!==_3){isc.logWarn("Attempted to add existing operator "+_1.ID+" - replacing")}
isc.DataSource.$57z[_1.ID]=_1}
,isc.A.getSearchOperators=function isc_c_DataSource_getSearchOperators(){return isc.DataSource.$57z}
,isc.A.setTypeOperators=function isc_c_DataSource_setTypeOperators(_1,_2){if(!_2)return;if(!isc.isAn.Array(_2))_2=[_2];if(!isc.DataSource.$570)isc.DataSource.$570={};isc.DataSource.$570[_1||"_all_"]=_2}
,isc.A.$625=function isc_c_DataSource__getNextRequestId(){return this.$628++}
,isc.A.getAutoTitle=function isc_c_DataSource_getAutoTitle(_1,_2){_2=_2||/[_\$]/g;if(!_1)return"";if(!isc.isA.String(_1))_1=_1.toString();var _3;_4=_1.replace(_2," ");var _4=_4.replace(/^\s+|\s+$/g,"");if(_4==_4.toUpperCase()||_4==_4.toLowerCase()){_4=_4.toLowerCase();var _5=true;_3="";for(var i=0;i<_4.length;i++){var _7=_4.substr(i,1);if(_5){_7=_7.toUpperCase();_5=false}
if(_7==' ')_5=true;_3=_3+_7}}else{_3=_4.substr(0,1).toUpperCase();var _8=_4.substr(0,1)==_4.substr(0,1).toUpperCase();var _9=false;for(var i=1;i<_4.length;i++){var _7=_4.substr(i,1);if(_9&&_7==_7.toLowerCase()){_9=false;_3=_3.substr(0,_3.length-1)+" "+_3.substr(_3.length-1)}
if(_8&&_7==_7.toUpperCase()){_9=true}
if(!_8&&_7==_7.toUpperCase()){_3=_3+" "}
_8=_7==_7.toUpperCase();_3=_3+_7}}
return _3}
,isc.A.convertCriteria=function isc_c_DataSource_convertCriteria(_1,_2){var _3={_constructor:"AdvancedCriteria",operator:"and"}
var _4=[];for(var _5 in _1){if(isc.isA.Array(_1[_5])){var _6={_constructor:"AdvancedCriteria",operator:"or",criteria:[]}
for(var i=0;i<_1[_5].length;i++){var _8=_1[_5][i];_6.criteria.add({fieldName:_5,operator:this.getCriteriaOperator(_8,_2),value:_8})}
_4.add(_6)}else{_4.add({fieldName:_5,operator:this.getCriteriaOperator(_1[_5],_2),value:_1[_5]})}}
_3.criteria=_4;return _3}
,isc.A.getCriteriaOperator=function isc_c_DataSource_getCriteriaOperator(_1,_2){var _3;if(isc.isA.Number(_1)||isc.isA.Date(_1)||isc.isA.Boolean(_1)){_3="equals"}else if(_2=="equals"||_2=="exact"){_3="iEquals"}else if(_2=="startsWith"){_3="iStartsWith"}else{_3="iContains"}
return _3}
,isc.A.combineCriteria=function isc_c_DataSource_combineCriteria(_1,_2,_3,_4,_5){if(!_1)return _2;if(!_2)return _1;if(!_3)_3="and";if(_3!="and"&&_3!="or"){isc.logWarn("combineCriteria called with invalid outerOperator '"+_3+"'");return null}
var _6,_7;if(!_5&&_1._constructor!="AdvancedCriteria"&&_2._constructor!="AdvancedCriteria"&&_3=="and")
{for(var _8 in _1){if(_2[_8]!=_6){_7=true;break}}}else{_7=true}
if(!_7){return isc.addProperties({},_1,_2)}
var _9,_10;if(_5||_1._constructor=="AdvancedCriteria"){_9=_1}else{_9=isc.DataSource.convertCriteria(_1,_4)}
if(_5||_2._constructor=="AdvancedCriteria"){_10=_2}else{_10=isc.DataSource.convertCriteria(_2,_4)}
var _11={operator:_3};if(!_5){_11._constructor="AdvancedCriteria"}
if(_9.operator==_3&&_10.operator==_3){_11.criteria=[];_11.criteria.addAll(_9.criteria);_11.criteria.addAll(_10.criteria)}else{_11.criteria=[_9,_10]}
return _11}
,isc.A.simplifyAdvancedCriteria=function isc_c_DataSource_simplifyAdvancedCriteria(_1,_2){if(!this.isAdvancedCriteria(_1)){return _1}
_1=this.$79a(_1,_2);if(_1==null)return null;_1._constructor="AdvancedCriteria";return _1}
,isc.A.$79a=function isc_c_DataSource__simplifyAdvancedCriteria(_1,_2){var _3=_1.operator;if(_3=="and"||_3=="or"){var _4=_1.criteria;if(_4==null||_4.length==0){if(_2)return null;return _1}
if(_4.length==1){_1=_4[0];return this.$79a(_1,true)}
var _5=[];for(var i=0;i<_4.length;i++){var _7=_4[i];_7=this.$79a(_7,true);if(_7!=null){if(_7.operator==_3){_5.addList(_7.criteria)}else{_5.add(_7)}}}
if(_5.length==0&&_2)return null;if(_5.length==1)return _5[0];_1.criteria=_5;return _1}else{return _1}}
,isc.A.combineFieldData=function isc_c_DataSource_combineFieldData(_1,_2){if(_2==null)return _1;for(var _3 in _2){if(_3=="validators"&&_1.validators!=null&&_2.validators!=_1.validators)
{if(_2.validators.$69){if(isc.SimpleType.getBaseType(_1.type)!=isc.SimpleType.getBaseType(_2.type))
{continue}}
for(var i=0;i<_2.validators.length;i++){var _5=_2.validators[i];if(!_1.validators.contains(_5)){if(_1.validators.$69){_1.validators=_1.validators.duplicate()}
_1.validators.add(_5)}}
continue}
if(_1[_3]!=null)continue;if(_3=="name")continue;_1[_3]=_2[_3]}
return _1}
,isc.A.applyRecordSummaryFunction=function isc_c_DataSource_applyRecordSummaryFunction(_1,_2,_3,_4){if(!_2||!_3)return;if(isc.isA.String(_1)){if(this.$71l[_1]){_1=this.$71l[_1]}else{_1=isc.Func.expressionToFunction("record,fields,summaryField",_1)}}
if(isc.isA.Function(_1))return _1(_2,_3,_4)}
,isc.A.registerRecordSummaryFunction=function isc_c_DataSource_registerRecordSummaryFunction(_1,_2){if(isc.isA.String(_2)){_2=isc.Func.expressionToFunction("record,fields,summaryField",_2)}
this.$71l[_1]=_2}
,isc.A.exportClientData=function isc_c_DataSource_exportClientData(_1,_2,_3){var _4=_2||{},_5=_4&&_4.exportAs?_4.exportAs:"csv",_6=_4&&_4.exportFilename?_4.exportFilename:"export",_7=_4&&_4.exportDisplay?_4.exportDisplay:"download";var _8={showPrompt:false,transport:_4.exportToClient===false?"xmlHttpRequest":"hiddenFrame",exportResults:true,downloadResult:!(_4.exportToClient===false),downloadToNewWindow:(_7=="window"),download_filename:(_7=="window"?_6:null)};var _9={exportAs:_4.exportAs,exportFilename:_6,exportPath:_4.exportPath,exportToClient:_4.exportToClient,exportToFilesystem:_4.exportToFilesystem,exportDelimiter:_4.exportDelimiter,exportFields:_4.exportFields,exportHeader:_4.exportHeader,exportFooter:_4.exportFooter,exportTitleSeparatorChar:_4.exportTitleSeparatorChar,lineBreakStyle:_4.lineBreakStyle};isc.DMI.callBuiltin({methodName:"downloadClientExport",arguments:[_1,_5,_6,_7,_9],requestParams:_8,callback:_3})}
);isc.B._maxIndex=isc.C+14;isc.A=isc.DataSource.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.supportsAdvancedCriteria=function isc_DataSource_supportsAdvancedCriteria(){if(this.clientOnly||this.cacheData)return true;if(this.willHandleAdvancedCriteria!=null)return this.willHandleAdvancedCriteria;return false}
,isc.A.isAdvancedCriteria=function isc_DataSource_isAdvancedCriteria(_1){return isc.DS.isAdvancedCriteria(_1,this)}
,isc.A.normalizeAdvancedCriteria=function isc_DataSource_normalizeAdvancedCriteria(_1,_2){var _3={};if(!_2){_3._constructor="AdvancedCriteria";if(_1.strictSQLFiltering)_3.strictSQLFiltering=true}
if(_1.criteria){if(!isc.isAn.Array(_1.criteria)){_1.criteria=[_1.criteria]}
_3.operator=_1.operator;_3.criteria=[];for(var i=0;i<_1.criteria.length;i++){_3.criteria.add(this.normalizeAdvancedCriteria(_1.criteria[i],true))}}else{if(!isc.isAn.Array(_1.value)){isc.addProperties(_3,_1)}else{var _5=this.getSearchOperator(_1.operator);if(!_5||!_5.canNormalize){isc.addProperties(_3,_1)}else{_3.operator=_5.negate?"and":"or";_3.criteria=[];for(var i=0;i<_1.value.length;i++){_3.criteria.add({fieldName:_1.fieldName,operator:_1.operator,value:_1.value[i]})}}}}
return _3}
,isc.A.addSearchOperator=function isc_DataSource_addSearchOperator(_1,_2){if(!_1||!_1.ID){isc.logWarn("Attempted to add null search operator, or operator with no ID");return}
isc.DataSource.addSearchOperator(_1);if(!this.$570)this.$570={$58d:true};if(_2){for(var _3=0;_3<this.$570.length;_3++){this.$570[_3].remove(_1.ID)}
for(var _3=0;_3<_2.length;_3++){if(!this.$570[_2[_3]]){this.$570[_2[_3]]=[_1.ID]}
if(!this.$570[_2[_3]].contains(_1.ID)){this.$570[_2[_3]].add(_1.ID)}}}else{if(!this.$570["_all_"]){this.$570["_all_"]=[_1.ID]}
if(!this.$570["_all_"].contains(_1.ID)){this.$570["_all_"].add(_1.ID)}}}
,isc.A.getSearchOperator=function isc_DataSource_getSearchOperator(_1){return isc.DataSource.$57z[_1]}
,isc.A.getTypeOperators=function isc_DataSource_getTypeOperators(_1){var _2=[];_1=_1||"text";var _3=isc.SimpleType.getType(_1);var _4=_3;if(this.$570){while(_4&&!this.$570[_4.name]){_4=isc.SimpleType.getType(_4.inheritsFrom,this)}
if(_4&&this.$570[_4.name]){_2=this.$570[_4.name]}
_2.addList(this.$570["_all_"]);if(!this.$570.$58d){return _2}}
_4=isc.SimpleType.getType(_1);while(_4&&!isc.DataSource.$570[_4.name]){_4=isc.SimpleType.getType(_4.inheritsFrom,this)}
if(_4&&isc.DataSource.$570[_4.name]){_2.addList(isc.DataSource.$570[_4.name])}
_2.addList(isc.DataSource.$570["_all_"]);return _2}
,isc.A.setTypeOperators=function isc_DataSource_setTypeOperators(_1,_2){if(!_2)return;if(!isc.isAn.Array(_2))_2=[_2];if(!this.$570){this.$570={}}else{this.$570.$58d=false}
this.$570[_1||"_all_"]=_2;this.$570.$58d=false}
,isc.A.getFieldOperators=function isc_DataSource_getFieldOperators(_1){if(isc.isA.String(_1))_1=this.getField(_1);if(!_1)return[];if(_1&&_1.validOperators)return _1.validOperators;var _2=isc.SimpleType.getType(_1.type);var _3=_1.type||"text";if(!_2)_3="text";return this.getTypeOperators(_3)}
,isc.A.getFieldOperatorMap=function isc_DataSource_getFieldOperatorMap(_1,_2,_3,_4){if(isc.isA.String(_1))_1=this.getField(_1);var _5={},_6=this.getFieldOperators(_1);_2=_2||_1.validOperators!=null;for(var _7=0;_7<_6.length;_7++){var _8=_6[_7];var _9=this.getSearchOperator(_8);if(!_9)continue;if(_9.hidden&&!_2&&(!isc.isAn.Array(_1.validOperators)||!_1.validators.contains(_8)))continue;if(!_3||(_9.valueType==_3)==!_4){_5[_6[_7]]=_9.titleProperty==null?_9.title:isc.Operators[_9.titleProperty]}}
return _5}
,isc.A.getTypeOperatorMap=function isc_DataSource_getTypeOperatorMap(_1,_2,_3,_4){var _5={},_6=this.getTypeOperators(_1);for(var _7=0;_7<_6.length;_7++){var _8=this.getSearchOperator(_6[_7]);if(_8&&(!_8.hidden||_2)){if(!_3||(_8.valueType==_3)==!_4)
_5[_6[_7]]=_8.titleProperty==null?_8.title:isc.Operators[_8.titleProperty]}}
return _5}
);isc.B._maxIndex=isc.C+10;isc.A=isc.DataSource.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.evaluateCriterion=function isc_DataSource_evaluateCriterion(_1,_2){if(_2.requiresServer==true)return true;var _3=this.getSearchOperator(_2.operator);if(_3==null){isc.logWarn("Attempted to use unknown operator "+_2.operator);return false}
var _4=true;if(this.$908){for(var i=0;i<this.$909.length;i++){if(this.$909[i]==_2){_4=false;if(this.$91a[i]==true){return true}}}}
if(_4){var _6;if(this.$908){_6=this.$909.length;this.$909[_6]=_2}
if(_2.fieldName){var _7=this,_8=this.getField(_2.fieldName);if(_8==null){_7=this.getDataSourceForDataPath(_2.fieldName);_8=this.getFieldForDataPath(_2.fieldName);if(_7==null){_7=this}}
if(_8==null){if(this.dropUnknownCriteria){this.logInfo("evaluateCriterion passed criterion for field not explicitly "+"listed in this dataSource:"+_2.fieldName+" - dropping this filter","AdvancedCriteria")
if(this.$908){this.$91a[_6]=true}
return true}else{this.logInfo("evaluateCriterion passed criterion for field not explicitly "+"listed in this dataSource:"+_2.fieldName+" - continuing with filter","AdvancedCriteria")}}else{var _9=_7.getFieldOperators(_8.name);if(!_9.contains(_3.ID)){this.logWarn("Operator "+_3.ID+" is not valid for field "+_2.fieldName+". Continuing anyway.")}}}}
return _3.condition(_2.value,_1,_2.fieldName,_2,_3,this)}
,isc.A.recordsMatchingAdvancedFilter=function isc_DataSource_recordsMatchingAdvancedFilter(_1,_2,_3){var _4=[];this.$59u=false;this.$59v=_2.strictSQLFiltering;if(this.$59v==null)this.$59v=this.strictSQLFiltering;var _5=false;if(!this.$908){this.$91b()}else{_5=true}
_2=this.convertRelativeDates(_2);for(var _6=0;_6<_1.length;_6++){if(this.evaluateCriterion(_1[_6],_2)){_4.add(_1[_6])}
if(!_5)this.$91c()}
return _4}
,isc.A.$91b=function isc_DataSource__startApplyingCriteriaToList(){this.$908=true;this.$909=[];this.$91a=[]}
,isc.A.$91c=function isc_DataSource__endApplyingCriteriaToList(){delete this.$909;delete this.$91a;delete this.$908}
,isc.A.compareAdvancedCriteria=function isc_DataSource_compareAdvancedCriteria(_1,_2,_3){_1=isc.DataSource.simplifyAdvancedCriteria(_1,true);_2=isc.DataSource.simplifyAdvancedCriteria(_2,true);if(_2==null)return _1==null?0:1;if(_1==null)return-1;_1=this.normalizeAdvancedCriteria(_1);_2=this.normalizeAdvancedCriteria(_2);var _4=this.getSearchOperator(_2.operator),_5=this.getSearchOperator(_1.operator);if(_4!=_5){if(_4.ID=="or"){var _6={operator:"or",criteria:[_1]};return _4.compareCriteria(_6,_2,_4,this)}else if(_5.ID=="and"){var _6={operator:"and",criteria:[_2]};return _5.compareCriteria(_1,_6,_5,this)}
return-1}
return _4.compareCriteria(_1,_2,_4,this)}
,isc.A.splitCriteria=function isc_DataSource_splitCriteria(_1,_2){var _3={},_4=_1,_5=false;if(_1==null||_2==null)return _3;if(this.isAdvancedCriteria(_1)){_4=this.$867(_1);_5=true}
if(_4==null)return _3;for(var i=0;i<_2.length;i++){var _7=_2[i];if(_4[_7]!=null){_3[_7]=_4[_7];if(!_5){delete _1[_7]}else{if(_1.fieldName){delete _1.fieldName;delete _1.value}else{for(var c=0;c<_1.criteria.length;c++){var _9=_1.criteria[c];if(_9.fieldName==_7){_1.criteria.removeAt(c);break}}}}}}
if(_5&&_1.criteria&&_1.criteria.length==0){delete _1.criteria;delete _1.operator;delete _1._constructor}
return _3}
,isc.A.$867=function isc_DataSource__convertAdvancedCriteriaToSimple(_1){var _2={};if(_1.fieldName){if(_1.operator!="equals"){isc.logWarn("splitCriteria: advanced criteria operator, "+_1.operator+", not supported.");return null}
_2[_1.fieldName]=_1.value;return _2}
if(_1.operator!="and"){isc.logWarn("splitCriteria: advanced criteria top-level operator, "+_1.operator+", not supported.");return null}
for(var i=0;i<_1.criteria.length;i++){var _4=_1.criteria[i];if(_4.operator!="equals"){isc.logWarn("splitCriteria: advanced criteria second-level operator, "+_1.operator+", not supported.");return null}
_2[_4.fieldName]=_4.value}
return(isc.isAn.emptyObject(_2)?null:_2)}
);isc.B._maxIndex=isc.C+7;isc.DataSource.registerStringMethods({transformRequest:"dsRequest",transformResponse:"dsResponse,dsRequest,data"});isc.$571=function(){var _1=function(_65,_66,_45,_67,_68,_69){var _2;if(!_67.criteria){_67.criteria=[]}
if(!isc.isAn.Array(_67.criteria)){isc.logWarn("AdvancedCriteria: found boolean operator where subcriteria was not "+"an array.  Subcriteria was: "+isc.echoFull(_67.criteria));return false}
if(_68.isNot)_69.$59u=!_69.$59u;for(var _3=0;_3<_67.criteria.length;_3++){var _4=_69.evaluateCriterion(_66,_67.criteria[_3]);if(_68.isAnd&&!_4)_2=false;if(_68.isNot&&_4)_2=false;if(_68.isOr&&_4)_2=true;if(_2!=null)break}
if(_2==null){if(_68.isOr)_2=false;else _2=true}
if(_68.isNot)_69.$59u=!_69.$59u;return _2};var _5=function(_65,_66,_45,_67,_68,_69){var _6=isc.DataSource.getPathValue(_66,_45);if(_69.$59v){if(_6==null||_65==null){return _69.$59u}}
var _7=(_65==_6);if(isc.isA.Date(_65)&&isc.isA.Date(_6)){_7=(_69.compareDates(_65,_6,_45)==0)}
if(_68.negate)return!_7;else return _7};var _8=function(_65,_66,_45,_67,_68,_69){var _9=_65,_10=_65,_6=isc.DataSource.getPathValue(_66,_45);if(_67.start)_9=_67.start;if(_67.end)_10=_67.end;if(_69.$59v){if(_6==null||(_68.lowerBounds&&_9==null)||(_68.upperBounds&&_10==null)){return _69.$59u}}
var _11=true;var _12=true;var _13=isc.isA.Date(_6);var _14=isc.isA.Number(_6);var _15=isc.isA.String(_6);if(_68.lowerBounds&&_9&&((_14&&isNaN(_9))||(_9&&_13&&(!isc.isA.Date(_9)))||(_9&&_15&&(!isc.isA.String(_9)&&!isc.isA.Number(_9))))){return false}
if(_68.upperBounds&&_10&&((_14&&isNaN(_10))||(_10&&_13&&(!isc.isA.Date(_10)))||(_10&&_15&&(!isc.isA.String(_10)&&!isc.isA.Number(_10))))){return false}
var _16;if(_9===null||_9===_16){_11=false}
if(_10===null||_10===_16){_12=false}
if(_13&&!isc.isA.Date(_9))_11=false;if(_13&&!isc.isA.Date(_10))_12=false;_13=isc.isA.Date(_9)||isc.isA.Date(_10);_14=isc.isA.Number(_9)||isc.isA.Number(_10);_15=isc.isA.String(_9)||isc.isA.String(_10);_6=isc.DataSource.getPathValue(_66,_45);var _17=_15&&_68.caseInsensitive;if(_6===null||_6===_16){if(_13)_6=new Date(-8640000000000000);else if(_14)_6=Number.MIN_VALUE;else _6=""}else{if(_14&&isNaN(_6)){_9=""+_9;_10=""+_10}
if(_13&&!isc.isA.Date(_6)){return false}}
if(_68.lowerBounds&&_11){if(_69.compareValues(_9,_6,_45,_17)<=(_68.inclusive?-1:0))
{return false}}
if(_68.upperBounds&&_12){if(_69.compareValues(_10,_6,_45,_17)>=(_68.inclusive?1:0))
{return false}}
return true};var _18=function(_65,_66,_45,_67,_68,_69){var _6=isc.DataSource.getPathValue(_66,_45),_19=_65;if(isc.isA.Number(_6))_6=""+_6;if(_6!=null&&!isc.isA.String(_6)){return _68.negate}
if(_6==null)return _69.$59v?_69.$59u:_68.negate;if(_19==null)_19="";if(isc.isA.Number(_19))_19=""+_19;if(!isc.isA.String(_19)||!isc.isA.String(_6))return _68.negate;if(_68.caseInsensitive){_6=_6.toLowerCase();_19=_19.toLowerCase()}
if(_68.startsWith)var _20=isc.startsWith(_6,_19);else if(_68.endsWith)_20=isc.endsWith(_6,_19);else if(_68.equals)_20=(_6==_19);else _20=isc.contains(_6,_19);if(_68.negate)return!_20;else return _20};var _21=function(_65,_66,_45,_67,_68){var _22=(isc.DataSource.getPathValue(_66,_45)==null);if(_68.negate)return!_22;else return _22};var _23=function(_65,_66,_45,_67,_68){var _24;var _6=isc.DataSource.getPathValue(_66,_45);var _16;if(_65===_16)return false;if(isc.isA.Date(_65)||isc.isA.Date(_6))return false;if(_68.caseInsensitive)_24=new RegExp(_65,"i");else _24=new RegExp(_65);return _24.test(_6)};var _25=function(_65,_66,_45,_67,_68,_69){var _6=isc.DataSource.getPathValue(_66,_45);if(_65==null)_65=[]
else if(!isc.isAn.Array(_65))_65=[_65];if(!isc.isA.Date(_6)){var _26=_65.contains(_6)}else{_26=false;for(var i=0;i<_65.length;i++){if(isc.isA.Date(_65[i])&&_69.compareDates(_65[i],_6,_45)==0)
{_26=true;break}}}
if(_68.negate)return!_26;else return _26};var _28=function(_65,_66,_45,_67,_68,_69){if(_65==null)return true;var _6=isc.DataSource.getPathValue(_66,_45);var _29=isc.DataSource.getPathValue(_66,_65);var _30=(_29==_6);if(isc.isA.Date(_29)&&isc.isA.Date(_6)){_30=(_69.compareDates(_29,_6,_45,_65)==0)}
if(_68.negate)return!_30;else return _30};var _31=function(_65,_66,_45,_67,_68,_69){if(_65==null)return true;return _8(isc.DataSource.getPathValue(_66,_65),_66,_45,_67,_68,_69)};var _32=function(_65,_66,_45,_67,_68,_69){if(_65==null)return true;return _18(isc.DataSource.getPathValue(_66,_65),_66,_45,_67,_68,_69)};var _33=function(_65,_66,_67,_68){if(!_66.criteria)_66.criteria=[];if(!isc.isAn.Array(_66.criteria)){isc.logWarn("AdvancedCriteria: boolean compareCriteria found "+"where old subcriteria was not an array");return-1}
if(!_65.criteria)_65.criteria=[];if(!isc.isAn.Array(_65.criteria)){isc.logWarn("AdvancedCriteria: boolean compareCriteria found "+"where new subcriteria was not an array");return-1}
var _34,_35=0,_36=_66.criteria.length,_37=_65.criteria.length;if(_37>_36&&_67.isOr){return-1}
var _38=isc.clone(_66.criteria);var _39=isc.clone(_65.criteria);for(var i=0;i<_36;i++){var _40=_38[i];var _41=i>_37?null:_39[i];if(!_41||(_41&&_41.fieldName!=_40.fieldName||_41.operator!=_40.operator||_41.processed==true)){_41=null;for(var j=0;j<_37;j++){if(_39[j].processed)continue;if(_39[j].fieldName==_40.fieldName&&_39[j].operator==_40.operator){_41=_39[j];break}}}
if(_41&&_40){_41.processed=true;_34=_68.compareAdvancedCriteria(_41,_40)}else{if(_40&&!_41){if(_67.isOr)_34=1;if(_67.isAnd)_34=-1;if(_67.isNot)_34=-1}}
if(_67.isAnd&&_34==-1)return-1;if(_67.isOr&&_34==-1)return-1;if(_67.isNot&&_34==1)return-1;if(_34!=0)_35=1}
for(var i=0;i<_37;i++){if(!_39[i].processed){if(_67.isOr)return-1;if(_67.isAnd)return 1;if(_67.isNot)return-1}}
return _35};var _43=function(_65,_66,_67,_68){if(_65.fieldName==_66.fieldName){var _7=_68.compareValues(_65.value,_66.value,_65.fieldName)==0;if(_7){return 0}else{return-1}}else{return-1}};var _44=function(_65,_66,_67,_68){var _45=_65.fieldName;if(_65.fieldName==_66.fieldName){if(_67.upperBounds&&_67.lowerBounds){if(_68.compareValues(_65.start,_66.start,_45)==0){if(_68.compareValues(_65.end,_66.end,_45)==0){return 0}}}else{if(_68.compareValues(_65.value,_66.value,_45)==0){return 0}}
var _46=_65.start==null?_65.value:_65.start,_47=_66.start==null?_66.value:_66.start,_48=_65.start==null?_65.value:_65.end,_49=_66.start==null?_66.value:_66.end;var _13,_50;var _51=true,_52=true,_53=true,_54=true;if(_47==null)_51=false;if(_49==null)_52=false;if(_46==null)_53=false;if(_48==null)_54=false;if(_67.lowerBounds&&!_67.upperBounds&&!_53&&!_51){return 0}
if(_67.lowerBounds&&!_67.upperBounds){if(_53&&!_51){return 1}
if(_51&&!_53){return-1}
var _55=_68.compareValues(_46,_47,_45);if(_55==-1){return 1}else if(_55==0){return 0}}
if(_67.upperBounds&&!_67.lowerBounds&&!_54&&!_52){return 0}
if(_67.upperBounds&&!_67.lowerBounds){if(_54&&!_52){return 1}
if(_52&&!_54){return-1}
var _55=_68.compareValues(_46,_47,_45);if(_55==1){return 1}else if(_55==0){return 0}}
if(_67.lowerBounds&&_67.upperBounds){if(_68.compareValues(_46,_47,_45)<=0&&_68.compareValues(_46,_49,_45)>=0&&_68.compareValues(_48,_49,_45)>=0&&_68.compareValues(_48,_47,_45)<=0)
{return 1}
if((_53&&!_51)||(_54&&!_52)){return 1}
if(!_53&&!_51&&!_54&&!_51){return 0}}}
return-1};var _56=function(_65,_66,_67){var _57=_66.value;var _58=_65.value;if(isc.isA.Number(_57))_57=""+_57;if(isc.isA.Number(_58))_58=""+_58;if(!isc.isA.String(_57)||!isc.isA.String(_58))return-1;if(_67.caseInsensitive){_57=_57.toLowerCase();_58=_58.toLowerCase()}
if(_65.fieldName==_66.fieldName&&_65.value==_66.value&&!_67.equals)
{return 0}
if(_67.equals){return _57==_58?0:-1}
if(_67.startsWith&&!_67.negate&&_58.length>_57.length&&isc.startsWith(_58,_57))
{return 1}
if(_67.startsWith&&_67.negate&&_57.length>_58.length&&isc.startsWith(_57,_58))
{return 1}
if(_67.endsWith&&!_67.negate&&_58.length>_57.length&&isc.endsWith(_58,_57))
{return 1}
if(_67.endsWith&&_67.negate&&_57.length>_58.length&&isc.endsWith(_57,_58))
{return 1}
if(!_67.startsWith&&!_67.endsWith&&!_67.negate&&_58.length>_57.length&&isc.contains(_58,_57))
{return 1}
if(!_67.startsWith&&!_67.endsWith&&_67.negate&&_57.length>_58.length&&isc.contains(_57,_58))
{return 1}
return-1};var _59=function(_65,_66,_67){if(_65.fieldName==_66.fieldName)return 0;else return-1};var _60=function(_65,_66,_67){if(_65.value==_66.value&&_65.fieldName==_66.fieldName){return 0}else{return-1}};var _61=function(_65,_66,_67){if(_65.fieldName==_66.fieldName){if(!isc.isAn.Array(_66.value)||!isc.isAn.Array(_65.value)){return-1}
if(_65.value.equals(_66.value)){return 0}
if(!_67.negate&&_66.value.containsAll(_65.value)){return 1}
if(_67.negate&&_65.value.containsAll(_66.value)){return 1}}
return-1};var _62=function(_65,_66,_67){if(_65.value==_66.value&&_65.fieldName==_66.fieldName){return 0}else{return-1}};var _63=[{ID:"equals",titleProperty:"equalsTitle",negate:false,valueType:"fieldType",condition:_5,compareCriteria:_43,symbol:"==",wildCard:"*",canNormalize:true,getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"notEqual",titleProperty:"notEqualTitle",negate:true,valueType:"fieldType",condition:_5,compareCriteria:_43,symbol:"!",wildCard:"*",canNormalize:true,getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"greaterThan",titleProperty:"greaterThanTitle",lowerBounds:true,valueType:"fieldType",condition:_8,compareCriteria:_44,symbol:">",getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"lessThan",titleProperty:"lessThanTitle",upperBounds:true,valueType:"fieldType",condition:_8,compareCriteria:_44,symbol:"<",getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"greaterOrEqual",titleProperty:"greaterOrEqualTitle",lowerBounds:true,inclusive:true,valueType:"fieldType",condition:_8,compareCriteria:_44,symbol:">=",getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"lessOrEqual",titleProperty:"lessOrEqualTitle",upperBounds:true,inclusive:true,valueType:"fieldType",condition:_8,compareCriteria:_44,symbol:"<=",getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"between",titleProperty:"betweenTitle",lowerBounds:true,upperBounds:true,hidden:true,valueType:"valueRange",condition:_8,compareCriteria:_44,getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"iBetween",titleProperty:"iBetweenTitle",lowerBounds:true,upperBounds:true,hidden:true,valueType:"valueRange",condition:_8,caseInsensitive:true,compareCriteria:_44,getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"betweenInclusive",titleProperty:"betweenInclusiveTitle",lowerBounds:true,upperBounds:true,valueType:"valueRange",inclusive:true,condition:_8,compareCriteria:_44,symbol:"...",getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"iBetweenInclusive",titleProperty:"iBetweenInclusiveTitle",lowerBounds:true,upperBounds:true,valueType:"valueRange",inclusive:true,condition:_8,compareCriteria:_44,symbol:"...",caseInsensitive:true,getCriterion:function(_45,_65){var _20={fieldName:_45,operator:this.ID};if(isc.isA.RelativeDateItem(_65))
_20.value=_65.getRelativeDate()||_65.getValue();else _20.value=_65.getValue();return _20}},{ID:"iEquals",titleProperty:"iEqualsTitle",equals:true,caseInsensitive:true,valueType:"fieldType",condition:_18,symbol:"==",wildCard:"*",canNormalize:true,compareCriteria:_56},{ID:"iContains",titleProperty:"iContainsTitle",caseInsensitive:true,valueType:"fieldType",condition:_18,symbol:"~",canNormalize:true,compareCriteria:_56},{ID:"iStartsWith",titleProperty:"iStartsWithTitle",startsWith:true,caseInsensitive:true,valueType:"fieldType",condition:_18,symbol:"^",canNormalize:true,compareCriteria:_56},{ID:"iEndsWith",titleProperty:"iEndsWithTitle",endsWith:true,caseInsensitive:true,valueType:"fieldType",condition:_18,symbol:"|",canNormalize:true,compareCriteria:_56},{ID:"contains",titleProperty:"containsTitle",hidden:true,valueType:"fieldType",condition:_18,symbol:"~",canNormalize:true,compareCriteria:_56},{ID:"startsWith",titleProperty:"startsWithTitle",startsWith:true,hidden:true,valueType:"fieldType",condition:_18,symbol:"^",canNormalize:true,compareCriteria:_56},{ID:"endsWith",titleProperty:"endsWithTitle",endsWith:true,hidden:true,valueType:"fieldType",condition:_18,symbol:"|",canNormalize:true,compareCriteria:_56},{ID:"iNotEqual",titleProperty:"iNotEqualTitle",caseInsensitive:true,equals:true,negate:true,valueType:"fieldType",condition:_18,symbol:"!",wildCard:"*",canNormalize:true,compareCriteria:_56},{ID:"iNotContains",titleProperty:"iNotContainsTitle",caseInsensitive:true,negate:true,valueType:"fieldType",condition:_18,symbol:"!~",canNormalize:true,compareCriteria:_56},{ID:"iNotStartsWith",titleProperty:"iNotStartsWithTitle",startsWith:true,caseInsensitive:true,negate:true,valueType:"fieldType",condition:_18,symbol:"!^",canNormalize:true,compareCriteria:_56},{ID:"iNotEndsWith",titleProperty:"iNotEndsWithTitle",endsWith:true,caseInsensitive:true,negate:true,valueType:"fieldType",condition:_18,symbol:"!@",canNormalize:true,compareCriteria:_56},{ID:"notContains",titleProperty:"notContainsTitle",negate:true,hidden:true,valueType:"fieldType",condition:_18,symbol:"!~",canNormalize:true,compareCriteria:_56},{ID:"notStartsWith",titleProperty:"notStartsWithTitle",startsWith:true,negate:true,hidden:true,valueType:"fieldType",condition:_18,symbol:"!^",canNormalize:true,compareCriteria:_56},{ID:"notEndsWith",titleProperty:"notEndsWithTitle",endsWith:true,negate:true,hidden:true,valueType:"fieldType",condition:_18,symbol:"!@",canNormalize:true,compareCriteria:_56},{ID:"isNull",titleProperty:"isNullTitle",valueType:"none",condition:_21,symbol:"#",compareCriteria:_59},{ID:"notNull",titleProperty:"notNullTitle",negate:true,valueType:"none",condition:_21,symbol:"!#",compareCriteria:_59},{ID:"regexp",titleProperty:"regexpTitle",hidden:true,valueType:"custom",condition:_23,symbol:"/regex/",compareCriteria:_60},{ID:"iregexp",titleProperty:"iregexpTitle",hidden:true,caseInsensitive:true,valueType:"custom",condition:_23,symbol:"/regex/",compareCriteria:_60},{ID:"inSet",titleProperty:"inSetTitle",hidden:true,valueType:"valueSet",condition:_25,compareCriteria:_61,symbol:"=(",closingSymbol:")",valueSeparator:"|",processValue:function(_65,_66){return _65.split(this.valueSeparator)}},{ID:"notInSet",titleProperty:"notInSetTitle",negate:true,hidden:true,valueType:"valueSet",condition:_25,compareCriteria:_61,symbol:"!=(",closingSymbol:")",valueSeparator:"|",processValue:function(_65,_66){return _65.split(this.valueSeparator)}},{ID:"equalsField",titleProperty:"equalsFieldTitle",valueType:"fieldName",condition:_28,symbol:"=.",canNormalize:true,compareCriteria:_62,processValue:function(_65,_66){if(!_66)return _65;var _64=_66.getField(_65);if(_64)return _65;_64=_66.getFieldByTitle(_65);if(_64)return _64.name;return null}},{ID:"notEqualField",titleProperty:"notEqualFieldTitle",negate:true,valueType:"fieldName",condition:_28,canNormalize:true,compareCriteria:_62},{ID:"greaterThanField",titleProperty:"greaterThanFieldTitle",lowerBounds:true,valueType:"fieldName",condition:_31,compareCriteria:_62},{ID:"lessThanField",titleProperty:"lessThanFieldTitle",upperBounds:true,valueType:"fieldName",condition:_31,compareCriteria:_62},{ID:"greaterOrEqualField",titleProperty:"greaterOrEqualFieldTitle",lowerBounds:true,inclusive:true,valueType:"fieldName",condition:_31,compareCriteria:_62},{ID:"lessOrEqualField",titleProperty:"lessOrEqualFieldTitle",upperBounds:true,inclusive:true,valueType:"fieldName",condition:_31,compareCriteria:_62},{ID:"containsField",titleProperty:"containsFieldTitle",hidden:true,valueType:"fieldName",condition:_32,canNormalize:true,compareCriteria:_62},{ID:"startsWithField",titleProperty:"startsWithTitleField",startsWith:true,hidden:true,valueType:"fieldName",condition:_32,canNormalize:true,compareCriteria:_62},{ID:"endsWithField",titleProperty:"endsWithTitleField",endsWith:true,hidden:true,valueType:"fieldName",condition:_32,canNormalize:true,compareCriteria:_62},{ID:"and",titleProperty:"andTitle",isAnd:true,valueType:"criteria",condition:_1,symbol:" and ",compareCriteria:_33},{ID:"not",titleProperty:"notTitle",isNot:true,valueType:"criteria",condition:_1,compareCriteria:_33},{ID:"or",titleProperty:"orTitle",isOr:true,valueType:"criteria",condition:_1,symbol:" or ",compareCriteria:_33}];for(var _3=0;_3<_63.length;_3++){isc.DataSource.addSearchOperator(_63[_3])}
isc.DataSource.setTypeOperators(null,["equals","notEqual","lessThan","greaterThan","lessOrEqual","greaterOrEqual","between","betweenInclusive","isNull","notNull","inSet","notInSet","equalsField","notEqualField","greaterThanField","lessThanField","greaterOrEqualField","lessOrEqualField","and","or","not","inSet","notInSet"]);isc.DataSource.setTypeOperators("text",["regexp","iregexp","contains","startsWith","endsWith","iEquals","iNotEqual","iBetween","iBetweenInclusive","iContains","iStartsWith","iEndsWith","notContains","notStartsWith","notEndsWith","iNotContains","iNotStartsWith","iNotEndsWith","containsField","startsWithField","endsWithField"]);isc.DataSource.setTypeOperators("integer",["iContains","iStartsWith","iEndsWith","iNotContains","iNotStartsWith","iNotEndsWith","containsField","startsWithField","endsWithField"]);isc.DataSource.setTypeOperators("float",["iContains","iStartsWith","iEndsWith","iNotContains","iNotStartsWith","iNotEndsWith","containsField","startsWithField","endsWithField"])};isc.$571();isc.DataSource.create({ID:"Object",fields:{},addGlobalId:false});isc.DataSource.create({ID:"ValueMap",addGlobalId:false,builtinSchema:true,canBeArrayValued:true,fields:{},$cp:"ID",$450:"id",xmlToJS:function(_1,_2){if(_1==null||isc.xml.elementIsNil(_1))return null;var _3=isc.xml.getElementChildren(_1),_4=isc.xml.getAttributes(_1),_5=!isc.isAn.emptyObject(_4);for(var i=0;i<_3.length;i++){var _7=_3[i],_8=_7.getAttribute(this.$cp)||_7.getAttribute(this.$450),_9=isc.xml.getElementText(_7);if(_8!=null&&_9!=null){_5=true;_4[_8]=_9}else if(_8!=null){_4[_8]=_8}else if(_9!=null){_4[_9]=_9}else{_4[isc.emptyString]=isc.emptyString}}
if(_5)return _4;return isc.getValues(_4)},xmlSerializeFields:function(_1,_2,_3){if(_1==null||isc.DS.isSimpleTypeValue(_1)){return this.Super("xmlSerializeFields",arguments)}
var _4=isc.SB.create(),_3=(_3||"")+"    ";if(isc.isAn.Array(_1)){for(var i=0;i<_1.length;i++){var _6=_1[i];_4.append("\r",_3,"<value>",isc.makeXMLSafe(_6),"</value>")}}else{for(var _7 in _1){var _6=_1[_7];_4.append("\r",_3,"<value id=\"",isc.makeXMLSafe(_7),"\">",isc.makeXMLSafe(_6),"</value>")}}
return _4.toString()}});isc.ClassFactory.defineInterface("DataModel");isc.DataModel.addInterfaceMethods({getDataSource:function(){if(isc.isA.String(this.dataSource))this.dataSource=isc.DS.get(this.dataSource);return this.dataSource},getOperationId:function(_1){var _2=this.getOperation(_1);return _2==null?null:(isc.isA.String(_2)?_2:_2.ID)},getOperation:function(_1){var _2=isc.rpc.getDefaultApplication(),_3,_4;var _5=_1+"Operation";if(this[_5]){_3=this[_5];if(isc.isAn.Object(_3))return _3;_4=_3}
if(_4==null||isc.isA.String(_4)){var _6=this.getDataSource();if(_6==null){this.logWarn("can't getOperation for type: "+_1+", no "+_5+" specified, and no dataSource to "+"create an auto-operation");return null}
this.logInfo("creating auto-operation for operationType: "+_1);_3=isc.DataSource.makeDefaultOperation(_6,_1,_4);_4=_3.ID;this[_5]=_4}
return _3}});isc.defineClass("XJSONDataSource","DataSource");isc.A=isc.XJSONDataSource.getPrototype();isc.A.dataFormat="json";isc.A.dataTransport="scriptInclude";isc.defineClass("Schema","DataSource");isc.A=isc.Schema.getPrototype();isc.A.dataFormat="xml";isc.A.dropNamespaceDeclarations=true;isc.A.addGlobalId=false;isc.defineClass("WSDLMessage","Schema");isc.A=isc.WSDLMessage.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.getWSOperation=function isc_WSDLMessage_getWSOperation(_1){var _2=this.getWebService(_1);if(_1&&_1.wsOperation)return _2.getOperation(_1.wsOperation);else return _2.getOperationForMessage(this.ID.substring(8))}
);isc.B._maxIndex=isc.C+1;isc.defineClass("XSElement","Schema");isc.defineClass("XSComplexType","Schema");isc.defineClass("SchemaSet").addMethods({init:function(){this.ns.ClassFactory.addGlobalID(this);var _1=this.schemaNamespace,_2=isc.SchemaSet.schemaSets,_3=_2[_1];if(_3==null||((_3.schema==null&&_3.schema.length==0)&&(this.schema!=null&&this.schema.length!=0)))
{_2[_1]=this}
var _4=this.serviceNamespace;if(this.schema){this.$530={};this.$531={};this.$693={};for(var i=0;i<this.schema.length;i++){var _6=this.schema[i];_6.serviceNamespace=_4;_6.schemaNamespace=_1;_6.location=this.location;if(isc.isA.SimpleType(_6)){if(_6.inheritsFrom&&_6.inheritsFrom==_6.name&&_6.xmlSource=="XSElement")continue;this.$693[_6.name]=_6}else if(_6.ID){if(isc.isAn.XSElement(_6)){this.$531[_6.ID]=_6}else{this.$530[_6.ID]=_6}}}}
isc.SchemaSet.$37r=this},getSchema:function(_1,_2,_3){if(!_3)_3=[this];else _3.add(this);var _4;if(_2==isc.DS.$532)_4=this.$531[_1];else if(_2==isc.DS.$45t)_4=this.$530[_1];if(_2==null){_4=this.$530[_1]||this.$531[_1];if(_4!=null)return _4}
if(!this.$70w){isc.SchemaSet.findLoadedImports(this);this.$70w=true}
var _5=this.$38q;if(_5!=null){for(var i=0;i<_5.length;i++){var _7=_5[i];if(_3.contains(_7))continue;_4=_7.getSchema(_1,_2,_3);if(_4!=null)return _4}}},getSimpleType:function(_1,_2){if(!_2)_2=[this];else _2.add(this);var _3;if(this.$693){_3=this.$693[_1];if(_3)return _3}
if(this.$38q!=null){for(var i=0;i<this.$38q.length;i++){var _5=this.$38q[i];if(_2.contains(_5))continue;_3=_5.getSimpleType(_1,_2);if(_3!=null)return _3}}},setLocation:function(_1){this.location=_1;if(!this.schema)return;for(var i=0;i<this.schema.length;i++){var _3=this.schema[i];_3.location=_1}},loadImports:function(_1){isc.SchemaSet.loadImports(_1,this)},loadImport:function(_1,_2,_3,_4){return isc.SchemaSet.loadImport(_1,_2,_3,_4,this)},doneImporting:function(){this.fireCallback(this.$69o)},addImportXMLSource:function(_1,_2){this.importSources=this.importSources||[];this.importSources.add({xmlText:_1,location:_2})},addSchemaSet:function(_1,_2){this.$69n=this.$69n||[];this.$69n.add(_1)}});isc.A=isc.SchemaSet;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.schemaSets={};isc.A.$69p=["http://www.w3.org/2001/xml.xsd","http://www.w3.org/2001/XMLSchema","http://www.w3.org/XML/1998/namespace"];isc.A.$69q=[];isc.B.push(isc.A.get=function isc_c_SchemaSet_get(_1){return this.schemaSets[_1]}
,isc.A.findLoadedImports=function isc_c_SchemaSet_findLoadedImports(_1){var _2=this.getAllImports(_1);if(!_2)return;var _3=_1.$38q=_1.$38q||[];var _4=_1.$70x=_1.$70x||[];for(var i=0;i<_2.length;i++){var _6=_2[i],_7=_6.isWSDL,_8=_6.namespace;if(this.$69p.contains(_8))continue;if((_7&&_4.find("serviceNamespace",_8))||(!_7&&_3.find("schemaNamespace",_8)))
continue;var _9=_7?isc.WebService.get(_8):isc.SchemaSet.get(_8);if(_9==null){var _10;if(isc.isA.WebService(_1)){_10="WebService with targetNamespace '"+_1.serviceNamespace}else{_10="SchemaSet with targetNamespace '"+_1.schemaNamespace}
var _11=_6.location?"logWarn":"logInfo";_1[_11](_10+"' could not find "+(_7?"webService":"SchemaSet")+" for namespace: '"+_8+"'. Pass autoLoadImports to loadWSDL()/loadXMLSchema() or "+"separately load via loadWSDL/loadXMLSchema jsp tag or method","schemaLoader");continue}
if(_9.location==null)_9.setLocation(_1.location);_7?_4.add(_9):_3.add(_9)}}
,isc.A.getAllImports=function isc_c_SchemaSet_getAllImports(_1){var _2=_1.schemaImports;if(_1.wsdlImports){_1.wsdlImports.setProperty("isWSDL",true);_2=_2||[];_2=_2.concat(_1.wsdlImports)}
return _2}
,isc.A.loadImports=function isc_c_SchemaSet_loadImports(_1,_2){_2.$69o=_1;var _3=this.getAllImports(_2);if(!_3)return _2.doneImporting();_2.$69r=0;for(var i=0;i<_3.length;i++){var _5=_3[i],_6=_5.namespace;if(_6){var _7=(_5.isWSDL?isc.WebService.get(_6):isc.SchemaSet.get(_6));if(_7!=null){_2.logDebug("import already loaded: "+_6+", skipping","schemaLoader");continue}}
if(_5.location&&_5.location!=_2.location){var _8=_2.loadImport(_6,_5.location,function(_9){if(isc.isA.WebService(_9)){_2.addWebService(_9,_6)}else{_2.addSchemaSet(_9,_6)}
_2.$69r--;_2.logInfo(_2+" loaded import: "+_9+" as namespace: "+_6+", remaining imports: "+_2.$69r,"schemaLoader");if(_2.$69r==0)_2.doneImporting()},_5.isWSDL);if(_8)_2.$69r++}}
if(_2.$69r==0)_2.doneImporting()}
,isc.A.loadImport=function isc_c_SchemaSet_loadImport(_1,_2,_3,_4,_5){var _6=_5.location.substring(0,_5.location.lastIndexOf("/"));if(!_6.endsWith("/"))_6+="/";var _7=isc.Page.combineURLs(_6,_2);if(_7==_5.location){_5.logDebug("skipping self-reference import: "+_7,"schemaLoader");return false}
if(this.$69p.contains(_7)){_5.logDebug("skipping pedantic import: "+_7,"schemaLoader");return false}
if(this.$69q.contains(_7)){_5.logDebug("skipping redundant import: "+_7,"schemaLoader");return false}
this.$69q.add(_7);_5.logInfo("loading import from: "+_7+"\nschema/service base dir: "+_6+"\nimport location: "+_2,"schemaLoader");var _8=_4?"loadWSDL":"loadXMLSchema";isc.xml[_8](_7,function(_9){_5.fireCallback(_3,"schemaSet",[_9])},null,true,{initiator:_5.initiator||_5,captureXML:_5.captureXML});return true}
);isc.B._maxIndex=isc.C+5;isc.SchemaSet.getPrototype().toString=function(){return"["+this.Class+" ns="+this.echoLeaf(this.schemaNamespace)+(this.location?" location="+isc.Page.getLastSegment(this.location):"")+"]"};isc.defineClass("WebService").addMethods({init:function(){var _1=this.serviceNamespace;if(this.messages){for(var i=0;i<this.messages.length;i++){this.messages[i].serviceNamespace=_1}}
this.logInfo("registered service with serviceNamespace: "+_1+" service name: "+this.name);isc.WebService.services.add(this);isc.WebService.$37r=this},loadImports:function(_1){isc.SchemaSet.loadImports(_1,this)},loadImport:function(_1,_2,_3,_4){return isc.SchemaSet.loadImport(_1,_2,_3,_4,this)},doneImporting:function(){this.fireCallback(this.$69o)},addSchemaSet:function(_1,_2){this.$38q=this.$38q||[];this.$38q.add(_1)},addWebService:function(_1,_2){this.$70x=this.$70x||[];this.$70x.add(_1)},addImportXMLSource:function(_1,_2){this.importSources=this.importSources||[];this.importSources.add({xmlText:_1,location:_2})},getOperation:function(_1,_2){if(isc.isAn.Object(_1))return _1;if(!this.$70w){isc.SchemaSet.findLoadedImports(this);this.$70w=true}
var _3=this.getBindingOperation(_1,_2);var _4=this.getPortTypeOperation(_1,_2);if(!_3&&!_4){this.logWarn(this+": no such operation: '"+_1+"'");return null}
return isc.addProperties({},_4,_3)},findOperation:function(_1,_2,_3,_4){if(!_3)return;if(_2)_3=_3.findAll("portTypeName",_2);if(!_3)return;if(this.$70x){for(var i=0;i<this.$70x.length;i++){var _6=this.$70x[i],_7=_4?"getPortTypeOperation":"getBindingOperation",_8=_6[_7](_1,_2);if(_8!=null)return _8}}
for(var i=0;i<_3.length;i++){var _9=_3[i].operation;if(!isc.isAn.Array(_9))_9=[_9];var _8=_9.find("name",_1);if(_8!=null)return _8}},getPortTypeOperation:function(_1,_2){return this.findOperation(_1,_2,this.portTypes,true)},getBindingOperation:function(_1,_2){return this.findOperation(_1,_2,this.bindings)},getOperationForMessage:function(_1){var _2=this.getOperations();if(!_2)return;var _3=_2.find("inputMessage",_1);if(_3)return _3;_3=_2.find("outputMessage",_1);if(_3)return _3},getOperationNames:function(){var _1=this.operationNames;if(_1)return _1;if(!this.$70w){isc.SchemaSet.findLoadedImports(this);this.$70w=true}
_1=this.operationNames=[];if(this.bindings){for(var i=0;i<this.bindings.length;i++){var _3=this.bindings[i],_4=_3.operation;if(!isc.isAn.Array(_4))_4=[_4];_1.addList(_4.getProperty("name"));for(var j=0;j<_1.length;j++){var _6=this.getPortTypeOperation(_1[j],_3.portTypeName);if(_6)_6.hasBinding=true}}}
if(this.portTypes){for(var i=0;i<this.portTypes.length;i++){var _7=this.portTypes[i],_4=_7.operation;if(!isc.isAn.Array(_4))_4=[_4];var _8=_4.findAll("hasBinding",true);if(_8){_4=_4.duplicate();_4.removeAll(_8)}
_1.addList(_4.getProperty("name"))}}
return(this.operationNames=_1)},getOperations:function(_1){var _2=this.getOperationNames(),_3=[];for(var i=0;i<_2.length;i++){var _5=this.getOperation(_2[i]);if(_1&&!_5.hasBinding)continue;_3.add(_5)}
return _3},getSchema:function(_1,_2){if(!this.$70w){isc.SchemaSet.findLoadedImports(this);this.$70w=true}
var _3=this.$38q;if(_3!=null){for(var i=0;i<_3.length;i++){var _5=_3[i];var _6=_5.getSchema(_1,_2);if(_6)return _6}}
return isc.DS.get(_1,null,null,_2)},getRequestMessage:function(_1){var _2=this.getOperation(_1);return this.getMessage(_2.inputMessage)},getResponseMessage:function(_1){var _2=this.getOperation(_1);return this.getMessage(_2.outputMessage)},getMessage:function(_1){var _2=this.messages.find("ID","message:"+_1);if(_2)return _2;if(!this.$70w){isc.SchemaSet.findLoadedImports(this);this.$70w=true}
if(this.$70x){for(var i=0;i<this.$70x.length;i++){var _4=this.$70x[i];_2=_4.getMessage(_1);if(_2)return _2}}},getBodyPartNames:function(_1,_2){var _3=this.getOperation(_1),_4=_2?_3.outputParts:_3.inputParts;if(_4==null||isc.isAn.emptyString(_4)){var _5=_2?this.getResponseMessage(_1):this.getRequestMessage(_1);return _5.getFieldNames()}else{return _4.split(" ")}},globalNamespaces:{xsi:"http://www.w3.org/2001/XMLSchema-instance",xsd:"http://www.w3.org/2001/XMLSchema"},callOperation:function(_1,_2,_3,_4,_5){var _6=this.getOperation(_1);if(_6==null){this.logWarn("No such operation: "+_1);return}
_5=_5||isc.emptyObject;var _7=isc.addProperties({actionURL:this.getDataURL(_1),httpMethod:"POST",contentType:"text/xml",data:_2,serviceNamespace:this.serviceNamespace,serviceName:this.name,wsOperation:_1},_5);_7.httpHeaders=isc.addProperties({},{SOAPAction:_6.soapAction||'""'},_5.httpHeaders);_7.headerData=_5.headerData||this.getHeaderData(_7);_7.data=this.getSoapMessage(_7);_7.clientContext={$38r:_4,$38s:_1,$38u:_3,$38v:_5.xmlResult};if(this.spoofResponses){var _8=this.getSampleResponse(_1);if(this.logIsDebugEnabled("xmlBinding")){this.logDebug("spoofed response:\n"+_8,"xmlBinding")}
this.delayCall("$38w",[isc.xml.parseXML(_8),_8,{status:0,clientContext:_7.clientContext,httpResponseCode:200,httpResponseText:_8},_7]);return}
_7.callback={target:this,methodName:"$38w"};isc.xml.getXMLResponse(_7)},$38w:function(_1,_2,_3,_4){var _5=_4.clientContext,_6=_5.$38s,_7=_5.$38u;if(_3.status<0){this.fireCallback(_5.$38r,"data,xmlDoc,rpcResponse,wsRequest",[_3.data,_1,_3,_4]);return}
_1.addNamespaces(this.getOutputNamespaces(_6));if(_4.xmlNamespaces){_1.addNamespaces(_4.xmlNamespaces)}
var _8=(_7!=null&&_7.contains("/")),_9=(_8?_7:null),_10;if(_8){_10=_1.selectNodes(_9)}else if(_7){_10=this.selectByType(_1,_6,_7)}else{_10=_1.selectNodes("//s:Body/*",{s:"http://schemas.xmlsoap.org/soap/envelope/"});if(_10.length==1)_10=_10[0]}
if(this.logIsDebugEnabled()){this.logDebug("selected response data is: "+this.echoFull(_10))}
if(_5.$38v){this.fireCallback(_5.$38r,"data,xmlDoc,rpcResponse,wsRequest",[_10,_1,_3,_4]);return}
var _11;if(_8){_11=null}else if(_7){_11=this.getSchema(_5.$38u)}else{var _12=this.getSchema("message:"+this.getOperation(_6).outputMessage);if(this.getSoapStyle(_6)!="document"){_11=_12}else{var _13=_12.getFieldNames().first();_11=_12.getSchema(_12.getField(_13).type)}}
_10=isc.xml.toJS(_10,null,_11);this.fireCallback(_5.$38r,"data,xmlDoc,rpcResponse,wsRequest",[_10,_1,_3,_4])},getOutputNamespaces:function(_1,_2){var _3=this.getDefaultOutputDS(_1);return isc.addProperties({"default":_3.schemaNamespace||this.serviceNamespace,schema:_3.schemaNamespace,service:this.serviceNamespace},_2)},getDataURL:function(_1){var _2=this.getOperation(_1);if(_2&&_2.dataURL)return _2.dataURL;return this.dataURL},getMessageSerializer:function(_1,_2){var _3=_2?this.getResponseMessage(_1):this.getRequestMessage(_1);if(_3==null){this.logWarn("no "+(_2?"response":"request")+" message definition found for operation: '"+_1+"'");return}
if(this.getSoapStyle(_1)!="document")return _3;var _4=_3.getFieldNames();if(_4.length==1&&_3.fieldIsComplexType(_4[0])){var _5=_3.getField(_4[0]);_3=_3.getSchema(_5.type,_5.xsElementRef?"element":null);if(_3==null){this.logWarn("can't find schema: "+_5.type+", part of "+(_2?"response":"request")+" message for operation '"+_1+"'")}}
return _3},useSimplifiedInputs:function(_1,_2){var _3=_2?this.getResponseMessage(_1):this.getRequestMessage(_1);return this.getMessageSerializer(_1,_2)!=_3},getSoapMessage:function(_1,_2){_1.serviceNamespace=_1.serviceNamespace||this.serviceNamespace;var _3=_1.wsOperation;if(this.getOperation(_3)==null){this.logWarn("no such operation: '"+_3+"' in service: "+this.serviceNamespace);return""}
var _4=this.getMessageSerializer(_1.wsOperation,_2&&_2.generateResponse);if(_4==null)return"";return _4.getXMLRequestBody(_1,_2)},getSampleResponse:function(_1,_2,_3,_4){return this.getSoapMessage({wsOperation:_1,data:_2||{}},isc.addProperties({spoofData:true,generateResponse:!_4},_3))},getSampleRequest:function(_1,_2,_3){return this.getSampleResponse(_1,_2,_3,true)},getSoapStyle:function(_1){return this.getOperation(_1).soapStyle||this.soapStyle},getInputDS:function(_1){return this.getMessageSerializer(_1)},getHeaderSchema:function(_1,_2){var _3=this.getOperation(_1),_4=_2?_3.inputHeaders:_3.outputHeaders;if(!_4)return null;var _5={};for(var i=0;i<_4.length;i++){var _7=_4[i].part,_8=this.getSchema("message:"+_4[i].message);var _9=_8.getPartField(_7);_5[_7]=this.getSchema(_9.type)||_9}
return _5},getInputHeaderSchema:function(_1){return this.getHeaderSchema(_1,true)},getOutputHeaderSchema:function(_1){return this.getHeaderSchema(_1,false)},getHeaderData:function(_1){},selectByType:function(_1,_2,_3){var _4=this.getOperation(_2),_5=this.getSchema("message:"+_4.outputMessage),_6=this.getSchema(_3);if(_6==null){this.logWarn("selectByType: type '"+_3+"' not present in schema for message: "+_4.outputMessage);return null}
var _7=_5.findTagOfType(_6.ID);if(_7==null){this.logWarn("selectByType: no tag of type '"+_3+"' could be found in message: "+_4.outputMessage);return null}
var _8=_7[0],_9=_7[1],_10=_7[2],_11=_7[3],_12=_8.getField(_9);_9=_9||_6.ID;var _13=_6.mustQualify,_14=_6.schemaNamespace,_15="//"+(_13?"ns0:":"")+_9;if(_12&&_12.multiple)_15=_15+"/*";var _16=isc.xml.selectNodes(_1,_15,{ns0:_14});if(this.logIsDebugEnabled("xmlBinding")){this.logDebug("selecting type: '"+_6+"' within message '"+_4.outputMessage+" via XPath: "+_15+(_13?" using ns0: "+_6.schemaNamespace:"")+" got "+_16.length+" elements","xmlBinding")}
return _16},getDefaultOutputDS:function(_1){var _2=this.getResponseMessage(_1);if(!_2)return null;var _3=_2.getFieldNames();if(_3.length==1&&_2.fieldIsComplexType(_3[0])){return _2.getSchema(_2.getField(_3[0]).type)}
return _2},getFetchDS:function(_1,_2,_3){if(_2==null)_2=this.getDefaultOutputDS(_1);_2=isc.isA.Object(_2)?_2.ID:_2;if(_2!=null&&this.getSchema(_2)==null){this.logWarn("getFetchDS: resultType: '"+_2+"' not present in web service - missing XML files?")}
var _4=isc.DS.create({serviceNamespace:this.serviceNamespace,inheritsFrom:_2,operationBindings:[isc.addProperties({operationType:"fetch",wsOperation:_1,recordName:_2},_3)]});return _4},setLocation:function(_1,_2){if(_2)this.getBindingOperation(_2).dataURL=_1;else this.dataURL=_1}});isc.A=isc.WebService;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.services=[];isc.B.push(isc.A.get=function isc_c_WebService_get(_1){return this.services.find("serviceNamespace",_1)}
,isc.A.getByName=function isc_c_WebService_getByName(_1,_2){if(_1=="")_1=null;if(_2!=null){return this.services.find({name:_1,serviceNamespace:_2})}else{return this.services.find("name",_1)}}
);isc.B._maxIndex=isc.C+2;isc.WebService.getPrototype().toString=function(){return"["+this.Class+" ns="+this.echoLeaf(this.serviceNamespace)+(this.location?" location="+isc.Page.getLastSegment(this.location):"")+"]"};isc.ClassFactory.defineClass("RPCManager");isc.RPC=isc.rpc=isc.RPCManager;isc.Page.observe(isc,"goOffline","isc.rpc.goOffline()");isc.Page.observe(isc,"goOnline","isc.rpc.goOnline()");isc.ClassFactory.defineClass("RPCRequest");isc.A=isc.RPCRequest;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.create=function isc_c_RPCRequest_create(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13){this.logWarn("An RPCRequest does not need to be created. Instead, pass properties to methods "+"such as RPCManager.send() and RPCManger.sendRequest.");return isc.addProperties({},_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13)}
);isc.B._maxIndex=isc.C+1;isc.ClassFactory.defineClass("RPCResponse");isc.A=isc.RPCResponse;isc.A.errorCodes={STATUS_SUCCESS:0,STATUS_OFFLINE:1,STATUS_FAILURE:-1,STATUS_VALIDATION_ERROR:-4,STATUS_LOGIN_INCORRECT:-5,STATUS_MAX_LOGIN_ATTEMPTS_EXCEEDED:-6,STATUS_LOGIN_REQUIRED:-7,STATUS_LOGIN_SUCCESS:-8,STATUS_UPDATE_WITHOUT_PK_ERROR:-9,STATUS_TRANSACTION_FAILED:-10,STATUS_TRANSPORT_ERROR:-90,STATUS_UNKNOWN_HOST_ERROR:-91,STATUS_CONNECTION_RESET_ERROR:-92,STATUS_SERVER_TIMEOUT:-100};isc.RPCResponse.addClassProperties(isc.RPCResponse.errorCodes)
isc.addGlobal("DSResponse",isc.RPCResponse);isc.A=isc.RPCManager;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.maxErrorMessageLength=1000;isc.A.maxLogMessageLength=25000;isc.A.defaultTimeout=240000;isc.A.defaultPrompt="Contacting server...";isc.A.timeoutErrorMessage="Operation timed out";isc.A.removeDataPrompt="Deleting record(s)...";isc.A.saveDataPrompt="Saving form...";isc.A.validateDataPrompt="Validating...";isc.A.promptStyle=isc.Dialog?"dialog":"cursor";isc.A.useCursorTracker=(isc.Browser.isMoz&&isc.Browser.geckoVersion<20051111);isc.A.cursorTrackerConstructor="Img";isc.A.cursorTrackerDefaults={src:"[SKINIMG]shared/progressCursorTracker.gif",size:16,offsetX:12,offsetY:0,$38x:function(_1){var _2=(isc.EH.getX()+this.offsetX),_3=(isc.EH.getY()+this.offsetY);if(_2+this.size>=isc.Page.getWidth()||_3+this.size>=isc.Page.getHeight()){this.hide();return}
if(isNaN(_2))_2=0;if(isNaN(_3))_3=0;this.setLeft(_2);this.setTop(_3);if(!_1&&!this.isVisible())this.show()},initWidget:function(){this.Super("initWidget",arguments);this.$38x(true);this.$38y=isc.Page.setEvent("mouseMove",this.getID()+".$38x()");this.$69s=isc.Page.setEvent("mouseOut",this.getID()+".hide()");this.bringToFront()},destroy:function(){isc.Page.clearEvent("mouseMove",this.$38y);isc.Page.clearEvent("mouseOut",this.$69s);this.Super("destroy",arguments)}};isc.A.promptCursor=isc.Browser.isSafari||(isc.Browser.isMoz&&isc.Browser.geckoVersion<20051111)||(isc.Browser.isIE&&isc.Browser.minorVersion<=5.5)?"wait":"progress";isc.A.fetchDataPrompt="Finding records that match your criteria...";isc.A.getViewRecordsPrompt="Loading record...";isc.A.showPrompt=false;isc.A.neverShowPrompt=false;isc.A.actionURL="[ISOMORPHIC]/IDACall";isc.A.defaultTransport="xmlHttpRequest";isc.A.dataEncoding="XML";isc.A.preserveTypes=true;isc.A.credentialsURL=isc.Page.getIsomorphicDir()+"login/loginSuccessMarker.html";isc.A.loginWindowSettings="WIDTH=550,HEIGHT=250";isc.A.maxLoginPageLength=1048576;isc.A.$38z=Array.create({addTrack:function(_1,_2){this.$451=_1;this.add(_1,_2);this.$451=null},setLastChanged:function(_1){this.$451=_1},clearLastChanged:function(){this.$451=null},getLastChanged:function(){return this.$451}});isc.A.$452=0;isc.A.$410=[];isc.B.push(isc.A.getTransactions=function isc_c_RPCManager_getTransactions(){return this.$38z}
);isc.B._maxIndex=isc.C+1;isc.A=isc.RPCManager;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.allowCrossDomainCalls=false;isc.A.$380=0;isc.A.onLine=!isc.isOffline();isc.A.$89f="text/xml";isc.A.loginStatusCodeMarker="<SCRIPT>//'\"]]>>isc_";isc.A.loginRequiredMarker="<SCRIPT>//'\"]]>>isc_loginRequired";isc.A.loginSuccessMarker="<SCRIPT>//'\"]]>>isc_loginSuccess";isc.A.maxLoginAttemptsExceededMarker="<SCRIPT>//'\"]]>>isc_maxLoginAttemptsExceeded";isc.A.$89j="<responses>";isc.A.$89k="</responses>";isc.A.$89l="<response>";isc.A.$89m="</response>";isc.B.push(isc.A.xmlHttpRequestAvailable=function isc_c_RPCManager_xmlHttpRequestAvailable(){if(isc.Browser.isIE)return(isc.Comm.createXMLHttpRequest()!=null);return true}
,isc.A.getActionURL=function isc_c_RPCManager_getActionURL(){return isc.Page.getURL(this.actionURL)}
,isc.A.send=function isc_c_RPCManager_send(_1,_2,_3){var _4=(_3||{});isc.addProperties(_4,{data:_1,callback:_2});return this.sendRequest(_4)}
,isc.A.$383=function isc_c_RPCManager__warnIfXmlHttpRequestUnavailable(_1){if(this.xmlHttpRequestAvailable()||!this.logIsWarnEnabled())return false;var _2="Feature "+_1+" requires the xmlHttpRequest transport"+" which is not currently available because ActiveX is disabled."+" Please see the 'Features requiring ActiveX or Native support'"+" topic in the client-side reference under Client Reference/System"+" for more information.";this.logWarn(_2);return true}
,isc.A.sendProxied=function isc_c_RPCManager_sendProxied(_1,_2){_1.serverOutputAsString=true;if(!_1.isRestRequest)_1.sendNoQueue=true;var _3=_1.actionURL||isc.RPCManager.actionURL;var _4=(_1.useHttpProxy!=null?_1.useHttpProxy:(isc.RPCManager.useHttpProxy&&_3.startsWith("http")&&!this.isLocalURL(_3)));if(!isc.RPCManager.allowCrossDomainCalls){if(!_4&&_3.startsWith("http")&&!this.isLocalURL(_3)){isc.warn("SmartClient can't directly contact URL '"+_3+"' due to "+"browser same-origin policy.  Remove the host and port number "+"(even if localhost) to avoid this problem, or use XJSONDataSource "+"for JSONP protocol (which allows cross-site calls), or use the "+"server-side HttpProxy included with SmartClient Server."+"<BR>"+"This warning may be suppressed by setting "+"<b>RPCManager.allowCrossDomainCalls</b> to true.")}}
if(!_4)
{if(!_2)_1.useSimpleHttp=true}else{_1=isc.addProperties({},_1,{actionURL:isc.XMLTools.httpProxyURL,isProxied:true,useSimpleHttp:true,proxiedURL:_3,params:{data:isc.Comm.xmlSerialize("data",{url:_3,httpMethod:_1.httpMethod,params:_1.params,contentType:_1.contentType,requestBody:_1.data,username:_1.username,password:_1.password,httpHeaders:_1.httpHeaders,uploadFileName:_1.uploadFileName})},transport:"xmlHttpRequest",httpMethod:null,data:null,contentType:null})}
return isc.rpc.sendRequest(_1)}
,isc.A.$59w=function isc_c_RPCManager__getHostAndPort(_1){var _2=isc.Page.getProtocol(_1),_3=_1.indexOf("/",_2.length),_4=_1.substring(_2.length,_3),_5;var _6=_4.indexOf(":");if(_6!=-1){_5=_4.substring(_6+1);_4=_4.substring(0,_6)}
return[_4,_5]}
,isc.A.isLocalURL=function isc_c_RPCManager_isLocalURL(_1){var _2=this.$59w(_1),_3=_2[0],_4=_2[1];if(_4==null||_4=="")_4=80;var _5=this.getWindow().location,_6=_5.hostname,_7=_5.port;if(_7==null||_7=="")_7=80;return(_3=="localhost"||_3==_6)&&_4==_7}
,isc.A.sendRequest=function isc_c_RPCManager_sendRequest(_1){if(_1.useHttpProxy&&!_1.isProxied)return this.sendProxied(_1);if(_1.canDropOnDelay&&this.delayingTransactions)return;_1=isc.addProperties({},_1);if(_1.suppressAutoDraw!==false)_1.suppressAutoDraw=true;_1.actionURL=isc.Page.getURL(_1.actionURL||_1.url||_1.URL||this.getActionURL());var _2=_1.transport;if(!_2){if(_1.useXmlHttpRequest!=null||this.useXmlHttpRequest!=null){if(_1.useXmlHttpRequest==null){if(this.useXmlHttpRequest!=null){_1.transport=this.useXmlHttpRequest?"xmlHttpRequest":"hiddenFrame"}else{_1.transport=this.defaultTransport}}else{_1.transport=_2=_1.useXmlHttpRequest?"xmlHttpRequest":"hiddenFrame"}}else{_1.transport=this.defaultTransport}}
this.checkTransportAvailable(_1,(_2!=null));if(_1.useSimpleHttp==null)_1.useSimpleHttp=_1.paramsOnly;isc.addDefaults(_1,{showPrompt:this.showPrompt,promptStyle:this.promptStyle,promptCursor:this.promptCursor,useCursorTracker:this.useCursorTracker,cursorTrackerConstructor:this.cursorTrackerConstructor});_1.cursorTrackerProperties=isc.addProperties({},this.cursorTrackerDefaults,this.cursorTrackerProperties,_1.cursorTrackerProperties);if(_1.cursorTrackerProperties==null)
_1.cursorTrackerProperties=this.cursorTrackerProperties;if(!_1.operation){_1.operation={ID:"custom",type:"rpc"}}
if(this.canQueueRequest(_1,(_2!=null))){if(!this.currentTransaction)this.currentTransaction=this.$385();this.$386(_1,this.currentTransaction);if(!this.queuing)return this.sendQueue();return _1}else{return this.sendNoQueue(_1)}}
,isc.A.checkTransportAvailable=function isc_c_RPCManager_checkTransportAvailable(_1,_2){var _3=this.xmlHttpRequestAvailable();var _4=_1.transport||this.defaultTransport;if(!_3){if(_4=="xmlHttpRequest"){if(_2){this.logWarn("RPC/DS request specifically requesting the xmlHttpRequest"+" transport, but xmlHttpRequest not currently available -"+" switching transport to hiddenFrame.")}else{this.logWarn("RPCManager.defaultTransport specifies xmlHttpRequest, but"+" xmlHttpRequest not currently available - switching transport "+"to hiddenFrame.")}}
_1.transport="hiddenFrame"}}
,isc.A.canQueueRequest=function isc_c_RPCManager_canQueueRequest(_1,_2){if(_1.ignoreTimeout)_1.sendNoQueue=true;var _3=_1.transport;if(_1.containsCredentials){return false}
if(_1.sendNoQueue||_1.transport=="scriptInclude")return false;var _4=(this.currentTransaction&&this.currentTransaction.requestData.operations.length>0);if(_4&&(_1.actionURL!=this.currentTransaction.URL)){this.logWarn("RPCRequest specified (or defaulted to) URL: "+_1.actionURL+" which is different than the URL for which the RPCManager is currently queuing: "+this.currentTransaction.URL+" - sending this request to server and continuing to queue");return false}
if(_4&&(this.currentTransaction.transport!=_1.transport))
{this.logWarn("RPCRequest with conflicting transport while queuing, sending request to"+" server and continuing to queue.");return false}
return true}
,isc.A.sendNoQueue=function isc_c_RPCManager_sendNoQueue(_1){var _2=this.currentTransaction;var _3=this.queuing;this.currentTransaction=this.$385();this.$386(_1,this.currentTransaction);var _4=this.sendQueue();this.queuing=_3;this.currentTransaction=_2;return _4}
,isc.A.$385=function isc_c_RPCManager__createTransaction(){var _1=this.$452++;var _2={timeout:this.defaultTimeout,transactionNum:_1,operations:[],responses:[],requestData:{transactionNum:_1,operations:[]},prompt:this.defaultPrompt,showPrompt:false,changed:function(){isc.RPCManager.$38z.setLastChanged(this);isc.RPCManager.$38z.dataChanged();isc.RPCManager.$38z.clearLastChanged()}}
this.$38z.addTrack(_2);this.$38z.clearLastChanged();return _2}
,isc.A.$386=function isc_c_RPCManager__addRequestToTransaction(_1,_2){_2.URL=_1.actionURL;if(_1.containsCredentials)_2.containsCredentials=true;if(_1.exportFilename)_2.URL+="/"+_1.exportFilename;if(!_2.download_filename)_2.download_filename=_1.download_filename;if((_1.downloadResult||_1.downloadToNewWindow)&&_1.download_filename){_2.download_filename=_1.download_filename;_2.URL+="/"+encodeURIComponent(_1.download_filename);_2.ignoreError=true}
if(_1.prompt&&!_2.customPromptIsSet){this.logDebug("Grabbed prompt from first request that defined one: "+_1.prompt);_2.prompt=_1.prompt;_2.customPromptIsSet=true}
if(_1.showPrompt&&!_2.showPrompt&&!this.neverShowPrompt){_1.showedPrompt=true;isc.addProperties(_2,{showPrompt:true,promptStyle:_1.promptStyle,promptCursor:_1.promptCursor,useCursorTracker:_1.useCursorTracker,cursorTrackerConstructor:_1.cursorTrackerConstructor,cursorTrackerProperties:_1.cursorTrackerProperties})}
if(_1.isProxied){isc.addProperties(_2,{isProxied:true,proxiedURL:_1.proxiedURL})}
_2.transport=_1.transport;if(_1.ignoreReloginMarkers)_2.ignoreReloginMarkers=true;_2.operations.add(_1);var _3=_1.data;if(_3==null)_3="__ISC_NULL__";else if(_3==="")_3="__ISC_EMPTY_STRING__";if(!_1.clientOnly)_2.requestData.operations.add(_3);if(_2.omitNullMapValuesInResponse!==false&&_1.omitNullMapValuesInResponse!=null){_2.omitNullMapValuesInResponse=_2.requestData.omitNullMapValuesInResponse=_1.omitNullMapValuesInResponse}else{_2.omitNullMapValuesInResponse=false}
if(_1.ignoreTimeout)_2.$387=true;_1.transactionNum=_2.transactionNum;if(_1.timeout||_1.timeout===0)_2.timeout=_1.timeout;_2.changed()}
,isc.A.startQueue=function isc_c_RPCManager_startQueue(_1){var _2=this.queuing;this.queuing=(_1==null?true:_1);return _2}
,isc.A.doShowPrompt=function isc_c_RPCManager_doShowPrompt(_1,_2){if(this.$380++!=0)return;if(_1.promptStyle=="dialog"&&_2!=null){isc.showPrompt(_2);this.$388=true}else{isc.EH.showClickMask(null,"hard",null,"blockingRPC");if(_1.useCursorTracker){this.$389=isc.ClassFactory.getClass(_1.cursorTrackerConstructor).create(_1.cursorTrackerProperties);this.$389.show()}else{isc.EH.$m8.setCursor(_1.promptCursor)}}}
,isc.A.doClearPrompt=function isc_c_RPCManager_doClearPrompt(_1){if(_1.clearedPrompt)return;_1.clearedPrompt=true;if(--this.$380!=0){if(this.$380<0)this.$380=0;return}
if(this.$388){isc.clearPrompt()}else{if(this.$389){this.$389.destroy();this.$389=null}else{isc.EH.$m8.setCursor(isc.Canvas.DEFAULT)}
isc.EH.hideClickMask("blockingRPC")}
this.$388=null}
,isc.A.getCurrentTransactionId=function isc_c_RPCManager_getCurrentTransactionId(){return this.currentTransaction?this.currentTransaction.transactionNum:null}
,isc.A.cancelQueue=function isc_c_RPCManager_cancelQueue(_1){if(!_1){this.currentTransaction=null;return}
var _2=this.getTransaction(_1);if(_2==null)return;if(_2.showPrompt)this.doClearPrompt(_2);if(_2.transportRequest&&_2.transportRequest.abort){_2.transportRequest.abort()}
this.clearTransaction(_2)}
,isc.A.getTransaction=function isc_c_RPCManager_getTransaction(_1){if(_1==null)return null;if(_1.location&&_1.document){var _2=_1;var _3=isc.HiddenFrame.$h6;for(var i=0;i<_3.length;i++){if(_2==_3[i].getHandle()){_1=_3[i].transactionNum;break}}
if(_1==_2){this.logDebug("Can't find transactionNum in getTransaction from iframe");return null}}
if(isc.isA.Number(_1)||isc.isA.String(_1)){_1=this.$38z.find({transactionNum:_1})}
if(_1&&_1.cleared)return null;return _1}
,isc.A.getCurrentTransaction=function isc_c_RPCManager_getCurrentTransaction(){return this.currentTransaction}
,isc.A.getLastSubmittedTransaction=function isc_c_RPCManager_getLastSubmittedTransaction(){return this.$38z[this.$38z.length-1]}
,isc.A.clearTransaction=function isc_c_RPCManager_clearTransaction(_1){var _2=this.getTransaction(_1);if(_2==null){this.logWarn("clearTransaction: no such transaction: "+this.echo(_1));return}
this.clearTransactionTimeout(_2);if(!this.$453&&isc.Page.isLoaded()){var _3=isc.LogViewer.getGlobalLogCookie();this.setTrackRPC(_3?_3.trackRPC:false)}
_2.cleared=true;if(!this.$454)this.$38z.remove(_2);else _2.changed()}
,isc.A.setTrackRPC=function isc_c_RPCManager_setTrackRPC(_1){this.$454=_1;if(!_1)this.removeClearedRPC()}
,isc.A.removeClearedRPC=function isc_c_RPCManager_removeClearedRPC(){var _1=this.$38z.findAll("cleared",true);if(_1)this.$38z.removeList(_1)}
,isc.A.delayAllPendingTransactions=function isc_c_RPCManager_delayAllPendingTransactions(){this.delayingTransactions=true;for(var i=0;i<this.$38z.length;i++){var _2=this.$38z[i];this.delayTransaction(_2)}}
,isc.A.suspendTransaction=function isc_c_RPCManager_suspendTransaction(_1){var _2=this.getTransaction(_1)||this.getCurrentTransaction();if(_2==null){this.logWarn("No transaction to suspend");return}
if(_2.suspended)return;_2.suspended=true;if(_2.$66n)_2.abortCallbacks=true;this.clearTransactionTimeout(_2);if(_2.showPrompt)this.doClearPrompt(_2);_2.changed()}
,isc.A.delayTransaction=function isc_c_RPCManager_delayTransaction(_1){_1=this.getTransaction(_1);if(_1.delayed)return;_1.delayed=true;this.clearTransactionTimeout(_1);_1.changed()}
,isc.A.goOffline=function isc_c_RPCManager_goOffline(){this.logInfo("Going offline...");this.onLine=false}
,isc.A.goOnline=function isc_c_RPCManager_goOnline(){this.logInfo("Going online...");this.offlinePlayback=true;this.playbackNextOfflineTransaction()}
,isc.A.offlineTransactionPlaybackComplete=function isc_c_RPCManager_offlineTransactionPlaybackComplete(){}
,isc.A.playbackNextOfflineTransaction=function isc_c_RPCManager_playbackNextOfflineTransaction(){var _1=this.offlineTransactionLog?this.offlineTransactionLog.removeAt(0):null;if(_1==null){this.logInfo("Offline transaction playback complete");this.offlinePlayback=false;this.onLine=!isc.isOffline();this.offlineTransactionPlaybackComplete();return}
this.resubmitTransaction(_1)}
,isc.A.offlineTransaction=function isc_c_RPCManager_offlineTransaction(_1){if(_1.offline)return;_1=this.getTransaction(_1);_1.offline=true;this.clearTransactionTimeout(_1);if(!this.offlineTransactionLog){this.offlineTransactionLog=[];this.offlineTransactionLog.sortByProperty("timestamp",Array.ASCENDING)}
this.offlineTransactionLog.add(_1);_1.changed();var _2=_1.operations;for(var i=0;i<_2.length;i++){var _4=_2[i];var _5=this.createRPCResponse(_1,_4,{httpResponseCode:200,offlineResponse:true});this.delayCall("fireReplyCallbacks",[_4,_5],0)}}
,isc.A.resendTransaction=function isc_c_RPCManager_resendTransaction(_1){this.resendTransactionsFlagged(_1,"suspended")}
,isc.A.resendDelayedTransactions=function isc_c_RPCManager_resendDelayedTransactions(){this.delayingTransactions=false;this.resendTransactionsFlagged(null,"delayed")}
,isc.A.resendTransactionsFlagged=function isc_c_RPCManager_resendTransactionsFlagged(_1,_2){var _3=_1?[this.getTransaction(_1)]:this.$38z;for(var i=0;i<_3.length;i++){_1=_3[i];if(_1[_2]){delete _1[_2];this.resubmitTransaction(_1)}}}
,isc.A.getTransactionRequests=function isc_c_RPCManager_getTransactionRequests(_1){return this.getTransaction(_1).operations}
,isc.A.$39a=function isc_c_RPCManager__setTransactionTimeoutTimer(_1){_1=this.getTransaction(_1);var _2=_1.timeout;if(!_2&&_2!==0)_2=this.defaultTimeout;if(_2==0)return;_1.timeoutTimer=isc.Timer.setTimeout("isc.RPCManager.$39b("+_1.transactionNum+")",_2)}
,isc.A.clearTransactionTimeout=function isc_c_RPCManager_clearTransactionTimeout(_1){_1=this.getTransaction(_1)||this.getCurrentTransaction()||this.getLastSubmittedTransaction();if(!_1)return;isc.Timer.clear(_1.timeoutTimer)}
,isc.A.$39b=function isc_c_RPCManager__timeoutTransaction(_1){_1=this.getTransaction(_1);if(_1.$387){this.clearTransaction(_1);return}
if(!this.onLine){this.offlineTransaction(_1);return}
_1.results=this.$39c(_1,{data:isc.RPCManager.timeoutErrorMessage,status:isc.RPCResponse.STATUS_SERVER_TIMEOUT});this.$39d(_1.transactionNum)}
,isc.A.$39c=function isc_c_RPCManager__makeErrorResults(_1,_2){var _3=[];for(var i=0;i<_1.operations.length;i++){if(_1.operations[i].dataFormat=="xml"){_3[i]=isc.Comm.xmlSerialize("response",_2)}else{_3[i]={response:isc.clone(_2)}}}
return _3}
,isc.A.resubmitTransaction=function isc_c_RPCManager_resubmitTransaction(_1){_1=this.getTransaction(_1)||this.getLastSubmittedTransaction();_1.status=null;var _2=this.currentTransaction;this.currentTransaction=_1;if(_1!=null){this.logInfo("Resubmitting transaction number: "+_1.transactionNum);delete _1.suspended;delete _1.clearedPrompt;var _3=_1.$76k||isc.emptyObject;this.sendQueue(_3.callback,_3.prompt,_3.URL)}else{this.logWarn("No transaction to resubmit: transaction number "+_1+" does not exist")}
this.currentTransaction=_2}
,isc.A.retryOperation=function isc_c_RPCManager_retryOperation(_1){this.logDebug("Server-initiated operation retry for commFrameID: "+_1);var _2=window[_1];if(!_2){this.logError("comm operation retry failed - can't locate object: "+_1);return}
_2.sendData()}
,isc.A.transactionAsGetRequest=function isc_c_RPCManager_transactionAsGetRequest(_1,_2,_3){if(!_1.cleared)_1=this.getTransaction(_1)||this.getCurrentTransaction();_2=(_2||_1.URL||this.getActionURL());if(!_3)_3={};_3._transaction=this.serializeTransaction(_1);return this.addParamsToURL(this.markURLAsRPC(_2),_3)}
,isc.A.encodeParameter=function isc_c_RPCManager_encodeParameter(_1,_2){if(isc.isA.Date(_2)){isc.Comm.xmlSchemaMode=true;_2=_2.toSchemaDate();isc.Comm.xmlSchemaMode=null}else if(isc.isA.Array(_2)){var _3=isc.SB.create();for(var i=0;i<_2.length;i++){_3.append(this.encodeParameter(_1,_2[i]));if(i<_2.length-1)_3.append("&")}
return _3.toString()}if(!isc.isA.String(_2)){_2=isc.JSON.encode(_2,{prettyPrint:false})}
return isc.SB.concat(encodeURIComponent(_1),"=",encodeURIComponent(_2))}
,isc.A.addParamsToURL=function isc_c_RPCManager_addParamsToURL(_1,_2){var _3=_1;if(!_2)return _1;for(var _4 in _2){var _5=_2[_4];_3+=_3.contains("?")?"&":"?";_3+=this.encodeParameter(_4,_5)}
return _3}
,isc.A.serializeTransaction=function isc_c_RPCManager_serializeTransaction(_1){var _2;if(this.dataEncoding=="JS"){isc.Comm.$ev=true;_2=isc.Comm.serialize(_1.requestData);isc.Comm.$ev=null}else{isc.Comm.$850=true;_2=isc.Comm.xmlSerialize("transaction",_1.requestData);isc.Comm.$850=null}
return _2}
,isc.A.markURLAsRPC=function isc_c_RPCManager_markURLAsRPC(_1){if(!_1.contains("isc_rpc="))_1+=(_1.contains("?")?"&":"?")+"isc_rpc=1&isc_v="+isc.versionNumber;return _1}
,isc.A.markURLAsXmlHttp=function isc_c_RPCManager_markURLAsXmlHttp(_1){if(!_1.contains("isc_xhr="))_1+=(_1.contains("?")?"&":"?")+"isc_xhr=1";return _1}
,isc.A.addDocumentDomain=function isc_c_RPCManager_addDocumentDomain(_1){if(!_1.contains("isc_dd="))_1+=(_1.contains("?")?"&":"?")+"isc_dd="+document.domain;return _1}
,isc.A.sendQueue=function isc_c_RPCManager_sendQueue(_1,_2,_3,_4){var _5=this.currentTransaction;this.currentTransaction=null;this.queuing=false;if(!_5){this.logInfo("sendQueue called with no current queue, ignoring");return false}
if(_4)this.delayCall("$84s",[_1,_2,_3,_5]);else return this.$84s(_1,_2,_3,_5)}
,isc.A.$84s=function isc_c_RPCManager__sendQueue(_1,_2,_3,_4){var _5=_4.operations[0];if(!isc.Page.isLoaded()||this.delayingTransactions){_4.$76k={callback:_1,prompt:_2,URL:_3};if(!this.delayingTransactions){isc.Page.setEvent("load",this,isc.Page.FIRE_ONCE,"resendDelayedTransactions");this.delayingTransactions=true}
this.delayTransaction(_4);return _5}
_4.timestamp=new Date().getTime();if(!this.onLine&&!this.offlinePlayback){this.offlineTransaction(_4);return _5}
var _6=true;for(var i=0;i<_4.operations.length;i++){if(!_4.operations[i].clientOnly){_6=false;break}}
if(_6){_4.allClientOnly=true;_4.sendTime=isc.timeStamp();if(_1!=null){_4.$40c=_1}
this.delayCall("$39d",[_4.transactionNum],0);return _5}
_3=_4.URL=isc.Page.getURL(_3||_4.URL||this.getActionURL());var _8=false;if(!_5.useSimpleHttp&&_4.transport!="scriptInclude"){_3=this.markURLAsRPC(_3);if(_4.transport=="xmlHttpRequest"){_3=this.markURLAsXmlHttp(_3);_8=true}else{_3=this.addParamsToURL(_3,{isc_tnum:_4.transactionNum})}
if(document.domain!=location.hostname)_3=this.addDocumentDomain(_3)}
_2=_4.prompt=((_4.showPrompt==null||_4.showPrompt)?(_2||_4.prompt||this.defaultPrompt):null);if(_2)this.doShowPrompt(_4,_2);var _9={};var _10=false;for(var i=0;i<_4.operations.length;i++){var _11=_4.operations[i];var _12=_11.params;if(_8){_12=_12||{};_12.isc_tnum=_4.transactionNum}
var _13=_11.queryParams;var _14=_12;if(_13&&isc.isAn.Object(_13)){_3=_4.URL=this.addParamsToURL(_3,_13)}
if(_12&&_10)
this.logWarn("Multiple RPCRequests with params attribute in one transaction - merging");if(_12){if(isc.isA.String(_12)){if(window[_12])_12=window[_12];else if(isc.Canvas.getForm(_12))_12=isc.Canvas.getForm(_12);else{this.logWarn("RPCRequest: "+isc.Log.echo(_11)+" was passed a params value: "+_12+" which does not resolve to a component or a native"+" form - request to server will not include these params");_12=null}}
if(isc.isA.Class(_12)){if(_12.getValues)_12=_12.getValues();else{this.logWarn("RPCRequest: "+isc.Log.echo(_11)+" was passed an instance of class "+_12.getClassName()+" (or a global ID that resolved to this class)"+" - this class does not support the getValues() method - request to"+" server will not include these params")}}
if(_12&&!isc.isAn.Object(_12)){this.logWarn("params value: "+_14+" for RPCrequest: "+isc.Log.echo(_11)+" resolved to non-object: "+isc.Log.echo(_12)+" - request to server will not include these params");_12=null}
if(_12){isc.addProperties(_9,_12);_10=true}}}
if(this.logIsInfoEnabled()){this.logInfo("sendQueue["+_4.transactionNum+"]: "+_4.operations.length+" RPCRequest(s); transport: "+_4.transport+"; target: "+_3)}
_4.sendTime=isc.timeStamp();_4.changed();_4.callback="isc.RPCManager.performTransactionReply(transactionNum,results,wd)";if(_1)_4.$40c=_1;var _12=_9;var _15=_4.transport,_16="send"+(_15.substring(0,1).toUpperCase())+_15.substring(1);if(isc.Comm[_16]==null){this.logWarn("Attempt to send transaction with specified transport '"+_4.transport+"' failed - unsupported transaction type.");return}
this.$39a(_4);var _17;if(_5.isRestRequest){if(_4.operations.length>1){if(_5.contentType==this.$89f){_17="<transaction transactionNum=\""+_4.transactionNum+"\">";_17+="<operations>"
for(var i=0;i<_4.operations.length;i++){if(_4.operations[i].clientOnly)continue;_17+=_4.operations[i].data}
_17+="</operations>";_17+="</transaction>"}else{_17="{ \"transaction\": { \"transactionNum\": "+_4.transactionNum+", ";_17+="\"operations\": [";for(var i=0;i<_4.operations.length;i++){if(_4.operations[i].clientOnly)continue;if(i>0)_17+=", ";_17+=_4.operations[i].data}
_17+="]}}"}}else{_17=_5.data}}else if(_5.useSimpleHttp){_17=_5.data}
isc.RPCManager.$410.push(_4.transactionNum);_4.transactionRequest=isc.Comm[_16]({URL:_3,httpMethod:_5.httpMethod,contentType:_5.contentType,httpHeaders:_5.httpHeaders,bypassCache:_5.bypassCache,data:_17,fields:_12,target:_5.target,callbackParam:_5.callbackParam,transport:_4.transport,blocking:_5.blocking,useSimpleHttp:_5.useSimpleHttp,transactionNum:_4.transactionNum,transaction:_4});if(isc.isA.Function(this.queueSent))this.queueSent(_4.operations);return _5}
,isc.A.performTransactionReply=function isc_c_RPCManager_performTransactionReply(_1,_2,_3){var _4=this.getTransaction(_1);if(!_4){this.logWarn("performTransactionReply: No such transaction "+_1);return false}
delete _4.$66n;delete _4.abortCallbacks;_4.receiveTime=isc.timeStamp();_4.changed();isc.RPCManager.$410.remove(_1);this.logInfo("transaction "+_1+" arrived after "+(_4.receiveTime-_4.sendTime)+"ms");if(_2==null){this.logFatal("No results for transaction "+_1);return false}
if(_4.transport=="xmlHttpRequest"){var _5=_2;_4.xmlHttpRequest=_5;_2=_5.responseText;var _6;try{_6=_5.status}catch(e){this.logWarn("Unable to access XHR.status - network cable unplugged?");_6=-1}
if(_6==1223)_6=204;if(_6==0&&(location.protocol=="file:"||location.protocol=="app-resource:"))
_6=200;_4.httpResponseCode=_6;_4.httpResponseText=_5.responseText;if(_6!=-1&&!_4.ignoreReloginMarkers&&this.processLoginStatusText(_5,_1))
{return}
if(_6!=-1&&this.responseRequiresLogin(_5,_1)){this.handleLoginRequired(_1);return}
if(_6!=-1&&this.responseIsRelogin(_5,_1)){this.handleLoginRequired(_1);return}
if(_6==12030&&isc.Browser.isIE){this.logWarn("Received HTTP status code 12030, resubmitting request");this.resubmitTransaction(_1);return}
var _7=_4.URL;var _8;if(_4.isProxied){_7=_4.proxiedURL+" (via proxy: "+_7+")";var _9=this.getHttpHeaders(_5,_4);var _10;if(_9){for(var _11 in _9){if(_11.toLowerCase()=="x-isc-httpproxy-status"){_10=_9[_11];break}}}
if(_10&&_10=="-91")_8=-91;if(_10&&_10=="-92")_8=-92}
if(_8)_6=500;if(_6>299||_6<200){_2=this.$39c(_4,{data:"Transport error - HTTP code: "+_6+" for URL: "+_7+(_6==302?" This error is likely the result"+" of a redirect to a server other than the origin"+" server or a redirect loop.":""),status:_8?_8:isc.RPCResponse.STATUS_TRANSPORT_ERROR});this.logDebug("RPC request to: "+_7+" returned with http response code: "+_6+". Response text:\n"+_5.responseText);_4.status=_8?_8:isc.RPCResponse.STATUS_TRANSPORT_ERROR;_4.$66n=true;this.handleTransportError(_1,_4.status,_4.httpResponseCode,_4.httpResponseText);if(_4.suspended||_4.abortCallbacks){delete _4.abortCallbacks;delete _4.$66n
return}
delete _4.$66n}}
_4.results=_2;this.$39d(_1);return true}
,isc.A.responseIsRelogin=function isc_c_RPCManager_responseIsRelogin(_1,_2){var _3=_1.status;if(document.domain!=location.hostname&&((_3==302&&this.treatRedirectAsRelogin)||(_3==0)||(_3==200&&_1.getAllResponseHeaders()==isc.emptyString&&_1.responseText==isc.emptyString)))
{this.logDebug("Detected document.domain 302 relogin condition - status: "+_3);return true}
return false}
,isc.A.processLoginStatusText=function isc_c_RPCManager_processLoginStatusText(_1,_2){var _3=_1.responseText;if(_3&&_3.length<this.maxLoginPageLength){var _4=_3.indexOf(this.loginStatusCodeMarker);if(_4==-1)return false;if(_3.indexOf(this.loginRequiredMarker,_4)!=-1){this.handleLoginRequired(_2);return true}else if(_3.indexOf(this.loginSuccessMarker,_4)!=-1){this.handleLoginSuccess(_2);return true}else if(_3.indexOf(this.maxLoginAttemptsExceededMarker,_4)!=-1){this.handleMaxLoginAttemptsExceeded(_2);return true}}
return false}
,isc.A.processLoginStatusCode=function isc_c_RPCManager_processLoginStatusCode(_1,_2){if(_1.status==isc.RPCResponse.STATUS_LOGIN_REQUIRED){this.handleLoginRequired(_1.transactionNum);return true}else if(_1.status==isc.RPCResponse.STATUS_LOGIN_SUCCESS){this.handleLoginSuccess(_1.transactionNum);return true}else if(_1.status==isc.RPCResponse.STATUS_MAX_LOGIN_ATTEMPTS_EXCEEDED){this.handleMaxLoginAttemptsExceeded(_1.transactionNum);return true}
return false}
,isc.A.responseRequiresLogin=function isc_c_RPCManager_responseRequiresLogin(_1,_2){return false}
,isc.A.createRPCResponse=function isc_c_RPCManager_createRPCResponse(_1,_2,_3){var _4=isc.addProperties({operationId:_2.operation.ID,clientContext:_2.clientContext,context:_2,transactionNum:_1.transactionNum,httpResponseCode:_1.httpResponseCode,httpResponseText:_1.httpResponseText,xmlHttpRequest:_1.xmlHttpRequest,transport:_1.transport,status:_1.status,clientOnly:_2.clientOnly},_3);if(_1.transport=="xmlHttpRequest"){isc.addProperties(_4,{httpHeaders:this.getHttpHeaders(_1.xmlHttpRequest,_1)})}
return _4}
,isc.A.getHttpHeaders=function isc_c_RPCManager_getHttpHeaders(_1,_2){if(_2.allClientOnly){return}
if(!_1){this.logWarn("getHttpHeaders called with a null XmlHttpRequest object");return}
if(!isc.Browser.isIE&&!_1.getAllResponseHeaders){return null}
var _3;try{_3=_1.getAllResponseHeaders()}catch(e){this.logWarn("Exception thrown by xmlHttpRequest.getAllResponseHeaders(): "+e)}
if(!_3){this.logWarn("xmlHttpRequest.getAllResponseHeaders() returned null");return null}
var _4=_3.split('\n');var _5={};for(var i=0;i<_4.length;i++){if(_4[i].replace(/^\s+|\s+$/g,'')=="")continue;var _7=_4[i].indexOf(':');if(_7==-1){this.logWarn("GetAllResponseHeaders string had malformed entry at line "+1+".  Line reads "+_4[i]);continue}
var _8=_4[i].substring(0,_7);_5[_8]=_4[i].substring(_7+1).replace(/^\s+|\s+$/g,'');if(_5[_8]=="true")_5[_8]=true;if(_5[_8]=="false")_5[_8]=false}
if(_5["X-Proxied-Set-Cookie"]!=null){_5["Set-Cookie"]=_5["X-Proxied-Set-Cookie"]}
return _5}
,isc.A.$89o=function isc_c_RPCManager__stripXMLTransactionTags(_1){var _2=_1.indexOf(this.$89j);if(_2!=-1){return _1.substring(_2+this.$89j.length,_1.lastIndexOf(this.$89k))}
return _1}
,isc.A.$89g=function isc_c_RPCManager__getXMLResponses(_1){_1=this.$89o(_1);var _2=[];var _3=0;if(_1){while(true){var _4=_1.indexOf(this.$89l,_3);if(_4==-1)break;_3=_1.indexOf(this.$89m,_4);if(_3==-1)break;_2.add(_1.substring(_4,_3+this.$89m.length))}}
return _2}
,isc.A.$89n=function isc_c_RPCManager__stripRestTags(_1,_2){if(!_1||!_2)return _1;var _3=_2.jsonPrefix?_1.indexOf(_2.jsonPrefix):0,_4=_2.jsonPrefix?_2.jsonPrefix.length:0,_5=_2.jsonSuffix?_1.lastIndexOf(_2.jsonSuffix):_1.length;if(_3==-1){this.logWarn("DataSource "+_2.ID+": REST response did not contain the "+"jsonPrefix configured for this DataSource ('"+_2.jsonPrefix+"'). Response evaluation may well fail as a result. Check your "+"server logic and/or DataSource definition.");_3=0}
if(_5==-1){this.logWarn("DataSource "+_2.ID+": REST response did not contain the "+"jsonSuffix configured for this DataSource ('"+_2.jsonSuffix+"'). Response evaluation may well fail as a result. Check your "+"server logic and/or DataSource definition.");_3=_1.length}
return _1.substring(_3+_4,_5)}
,isc.A.$39d=function isc_c_RPCManager__performTransactionReply(_1){var _2=this.getTransaction(_1);this.clearTransactionTimeout(_1);if(!_2)return;if(this.logIsDebugEnabled()){this.logDebug("Result string for transaction "+_1+": "+isc.Log.echoAll(_2.results))}
var _3;if(_2.transport=="scriptInclude"){}else if(isc.isAn.Array(_2.results)){_3=true}else if(_2.allClientOnly){_2.results={status:0};_2.receiveTime=isc.timeStamp()}else{var _4=_2.operations[0];if(_4&&_4.isRestRequest){var _5=isc.DataSource.get(_4.dataSource);if(_5){if(_4&&_4.dataFormat=="json"&&_5.jsonPrefix==null)
{_3=true}else{var _6=_2.results?_2.results.indexOf(_5.jsonPrefix):-1;_3=_6!=-1}
if(_3){var _7=_2.results;_7=this.$89n(_7,_5);try{_2.results=isc.eval("("+_7+")")}catch(e){this.logWarn("Error evaling REST RPC response: "+e+" response text: "+_7)}}}}
if(!_3){var _8=isc.isAn.Array(_2.operations)?_2.operations[0]:null;if(_8&&_8.isRestRequest&&_2.operations.length>1){if(_8.dataFormat=="json"){this.logWarn("Found a REST request that appears to be in JSON format, "+"but the response was not wrapped as configured by the jsonPrefix "+"and jsonSuffix properties - aborting")
return}else{_2.results=this.$89g(_2.results);_3=_2.results!=null}}}}
var _9=_2.results;var _10=_2.operations,_11=[];_2.$66n=true;for(var i=0,j=0;i<_10.length;i++){var _4=_10[i];var _14;if(_3&&_4.clientOnly){_14=isc.addProperties(this.createRPCResponse(_2,_4),{isStructured:false})}else{_14=isc.addProperties(this.createRPCResponse(_2,_4),{isStructured:_3,callbackArgs:_2.transport=="scriptInclude"?_9:null,results:_3?_9[j]:_9});if(_3&&_4.isRestRequest){isc.addProperties(_14,{data:_9[j]})}
j++}
if(_14.status==null)_14.status=0;if(_14.isStructured){if(_14.results.errors){var _15=_14.results.errors;if(isc.isAn.Array(_15)&&_15.length==1){_15=_15[0]}}
if(_14.results&&!_4.isRestRequest){if(!_14.results.response){isc.addProperties(_14,_14.results)}else{isc.addProperties(_14,_14.results.response)}}}
_11[i]=_14;_2.responses[i]=_14;_2.changed()}
var _16=0;while(_16<_10.length&&!_2.suspended&&!_2.abortCallbacks)
{var _4=_10[_16],_14=_11[_16];this.performOperationReply(_4,_14);_16++}
if(_2.showPrompt)this.doClearPrompt(_2);if(!_2.suspended&&!_2.abortCallbacks){this.clearTransaction(_1)}
delete _2.abortCallbacks;delete _2.$66n;if(_2.offline)this.playbackNextOfflineTransaction();if(_2.$40c){var _17=_4.application?_4.application:this.getDefaultApplication();if(isc.isA.String(_17))_17=window[_17];_17.fireCallback(_2.$40c,"responses",[_2.responses])}}
,isc.A.performOperationReply=function isc_c_RPCManager_performOperationReply(_1,_2){var _3=_2.results,_4=_1.operation;if(this.logIsInfoEnabled()){this.logInfo("rpcResponse(unstructured) results -->"+isc.Log.echoAll(_3)+"<--")}
if(this.processLoginStatusCode(_2,_2.transactionNum))return;return this.fireReplyCallbacks(_1,_2)}
,isc.A.fireReplyCallback=function isc_c_RPCManager_fireReplyCallback(_1,_2,_3,_4){var _5=_2.application?_2.application:this.getDefaultApplication();if(isc.isA.String(_5))_5=window[_5];var _6=_5.fireCallback(_1,"rpcResponse,data,rpcRequest",[_3,_4,_2]);return _6}
,isc.A.evalResult=function isc_c_RPCManager_evalResult(_1,_2,_3){var _4=_1.evalVars;this.logDebug("evaling result"+(_4?" with evalVars: "+isc.Log.echo(_4):""));var _5=isc.Canvas.getInstanceProperty("autoDraw");if(_1.suppressAutoDraw)isc.Canvas.setInstanceProperty("autoDraw",false);if(_3.match(/^\s*\{/)){_3="var evalText="+_3+";evalText;"}
var _6=isc.Class.evalWithVars(_3,_4);if(_1.suppressAutoDraw)isc.Canvas.setInstanceProperty("autoDraw",_5);return _6}
,isc.A.fireReplyCallbacks=function isc_c_RPCManager_fireReplyCallbacks(_1,_2){var _3=_1.operation,_4=_2.results,_5=_1.evalResult&&_1.transport!="scriptInclude"?this.evalResult(_1,_2,_4):null;var _6;_6=(_1.evalResult?_5:_4);_2.data=_6;var _7=this.getTransaction(_2.transactionNum);var _8=_1.callback;if(_8!=null){this.fireReplyCallback(_8,_1,_2,_6)}}
,isc.A.$a0=function isc_c_RPCManager__handleError(_1,_2){if(_1.ignoreError)return;if(_2.dataSource){var _3=isc.DataSource.get(_2.dataSource);if(_3&&_3.handleError){var _4=_3.handleError(_1,_2);if(_4==false)return}}
return this.handleError(_1,_2)}
,isc.A.handleError=function isc_c_RPCManager_handleError(_1,_2){var _3=(_1.context?_1.context:{}),_4;if(isc.isA.String(_1.data)){_4=_1.data;if(isc.isA.String(_4)){var _5=_4;if(_5.length>this.maxErrorMessageLength){var _6=_5.length-this.maxErrorMessageLength;_5=_5.substring(0,this.maxErrorMessageLength)+"<br><br>...("+_6+" bytes truncated - set"+" isc.RPCManager.maxErrorMessageLength > "+this.maxErrorMessageLength+" to see more or check the Developer Console for full error)..."}
isc.warn(_5.asHTML())}}else{var _7=isc.getKeyForValue(_1.status,isc.RPCResponse.errorCodes);if(isc.isA.String(_7)){if(_7.startsWith("STATUS_"))_7=_7.substring(7)}else{_7=(_1.status!=null?"error code: "+_1.status:"unknown error code")}
var _8=_1.operationId||_1.operationType;_4="Server returned "+_7+" with no error message"+(_8?" performing operation '"+_8+"'.":".");isc.warn(_4.asHTML())}
this.logWarn(_4+", response: "+this.echo(_1));return false}
,isc.A.handleTransportError=function isc_c_RPCManager_handleTransportError(_1,_2,_3,_4){}
,isc.A.handleLoginRequired=function isc_c_RPCManager_handleLoginRequired(_1){if(this.$39j&&this.$39j==_1)return;var _2=this.getTransaction(_1);if(_2==null)return;_1=_2.transactionNum;this.clearTransactionTimeout(_2);var _3=_2.operations[0],_4=this.createRPCResponse(_2,_3);this.logInfo("loginRequired for transaction: "+_1+(_2.containsCredentials?", transaction containsCredentials":""));if(_2.containsCredentials){if(_3.callback){_4.status=isc.RPCResponse.STATUS_LOGIN_INCORRECT;this.fireReplyCallbacks(_3,_4);this.clearTransaction(_2);return}
this.clearTransaction(_2)}
this.suspendTransaction(_2);if(this.loginRequired){_4.status=isc.RPCResponse.STATUS_LOGIN_REQUIRED;this.loginRequired(_1,_3,_4);return}
var _5=this.addParamsToURL(this.credentialsURL,{ts:new Date().getTime()});this.$39j=window.open(_5,this.loginWindowSettings)}
,isc.A.handleLoginSuccess=function isc_c_RPCManager_handleLoginSuccess(_1){var _2=this.getTransaction(_1);if(_2&&_2.containsCredentials){this.clearTransactionTimeout(_2);var _3=_2.operations[0];if(_3.callback){var _4=this.createRPCResponse(_2,_3,{status:isc.RPCResponse.STATUS_SUCCESS});this.fireReplyCallbacks(_3,_4);this.clearTransaction(_2);return}
this.clearTransaction(_2)}
if(this.$39j)this.$39j.close();if(this.loginSuccess&&this.loginSuccess()===false)return;this.resendTransaction()}
,isc.A.handleMaxLoginAttemptsExceeded=function isc_c_RPCManager_handleMaxLoginAttemptsExceeded(_1){var _2=this.getTransaction(_1);if(_2&&_2.containsCredentials){this.clearTransactionTimeout(_2);var _3=_2.operations[0];if(_3.callback){var _4=this.createRPCResponse(_2,_3,{status:isc.RPCResponse.STATUS_MAX_LOGIN_ATTEMPTS_EXCEEDED});this.fireReplyCallbacks(_3,_4);this.clearTransaction(_2);return}
this.clearTransaction(_2)}
if(this.$39j)this.$39j.close();if(this.maxLoginAttemptsExceeded)this.maxLoginAttemptsExceeded();else{var _5="Max login attempts exceeded.";if(isc.warn)isc.warn(_5);else alert(_5)}}
,isc.A.exportContent=function isc_c_RPCManager_exportContent(_1,_2){var _3=function(_7){var _4={showPrompt:false,transport:"hiddenFrame",exportResults:true,downloadResult:true,downloadToNewWindow:null,download_filename:null};var _5={skinName:_2.skinName,pdfName:(_2.exportFilename==null)?_2.pdfName:_2.exportFilename,defaultSkinName:isc.Page.getSkinDir()};isc.DMI.callBuiltin({methodName:"getPdfObject",arguments:[_7,_5],requestParams:_4})};var _6=_1.getPrintHTML(null,_3);if(_6!=null){_3(_6)}}
);isc.B._maxIndex=isc.C+73;isc.RPCManager.rpc_logMessage=isc.RPCManager.logMessage;isc.RPCManager.logMessage=function(_1,_2,_3,_4){if(this.logIsEnabledFor(_1,_3)){if(isc.isA.String(_2)&&_2.length>this.maxLogMessageLength&&!this.logIsEnabledFor(_1,"RPCManagerResponse"))
{var _5=_2.length-this.maxLogMessageLength;_2=_2.substring(0,this.maxLogMessageLength)+"\n...("+_5+" bytes truncated).  Enable RPCManagerResponse log at same threshold to see full message."}}
this.rpc_logMessage(_1,_2,_3,_4)};isc.addGlobal("InstantDataApp",isc.RPCManager);isc.isA.InstantDataApp=isc.isA.RPCManager;isc.A=isc.InstantDataApp;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.addDefaultOperation=function isc_c_InstantDataApp_addDefaultOperation(_1,_2,_3){if(!_1)_1={};_1.operation=isc.DataSource.makeDefaultOperation(_2,_3,_1.operation);return _1}
,isc.A.setDefaultApplication=function isc_c_InstantDataApp_setDefaultApplication(_1){isc.InstantDataApp.defaultApplication=_1}
,isc.A.getDefaultApplication=function isc_c_InstantDataApp_getDefaultApplication(){if(this.defaultApplication==null){this.create({ID:"builtinApplication",dataSources:[],operations:{},pointersToThis:[{object:this,property:"defaultApplication"}]})}
return this.defaultApplication}
,isc.A.app=function isc_c_InstantDataApp_app(){return this.getDefaultApplication()}
);isc.B._maxIndex=isc.C+4;isc.A=isc.InstantDataApp.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.init=function isc_InstantDataApp_init(){if(this.ID!="builtinApplication")isc.ClassFactory.addGlobalID(this);if(isc.rpc.defaultApplication==null||isc.rpc.defaultApplication.getID()=="builtinApplication")
{isc.rpc.setDefaultApplication(this)}}
);isc.B._maxIndex=isc.C+1;isc.defineClass("DMI").addClassProperties({actionURL:isc.RPCManager.actionURL,call:function(_1,_2,_3){var _4=[];for(var i=0;i<arguments.length;i++)_4[_4.length]=arguments[i];var _6={};if(isc.isAn.Object(_1)&&_4.length==1){var _7=isc.clone(_1);if(_7.requestParams){isc.addProperties(_6,this.requestParams,_7.requestParams);delete _7.requestParams;if(_7.downloadResult==true){_7.showPrompt=false;_7.transport="hiddenFrame"}}
_6.callback=_7.callback;delete _7.callback;_6.data=_7}else{_6.data={appID:_1,className:_2,methodName:_3,arguments:_4.slice(3,_4.length-1)};_6.callback=_4[_4.length-1]}
_4=_6.data.arguments;if(!isc.isAn.Array(_4)){if(_4==null)_4=[];else _4=[_4]}
_6.data.arguments=_4;_6.data.is_ISC_RPC_DMI=true;if(this.addMetaDataToQueryString){if(!_6.queryParams)_6.queryParams={};isc.addProperties(_6.queryParams,{dmi_appID:_6.data.appID,dmi_class:_6.data.className,dmi_method:_6.data.methodName})}
return isc.RPCManager.sendRequest(_6)},callTemplate:"(function(){var x = function (firstArg) { "+"var isCall = ${isCall};"+"var obj = {};"+"obj.requestParams=this.requestParams;"+"if(isc.isAn.Object(firstArg) && arguments.length == 1){"+"isc.addProperties(obj,{appID:'${appID}',className:'${className}',methodName:'${methodName}'},firstArg);"+"} else {"+"var args = [];for (var i = 0; i < arguments.length; i++) args[args.length] = arguments[i];"+"isc.addProperties(obj,{appID:'${appID}',className:'${className}',methodName:isCall?firstArg:'${methodName}',"+"arguments:args.slice(isCall ? 1 : 0,args.length-1),callback:args[args.length-1]});"+"}isc.DMI.call(obj);"+"};return x})()",bind:function(_1,_2,_3,_4){if(!isc.isAn.Array(_3))_3=[_3];_4=isc.addProperties({},this.requestParams,_4)
var _5=isc.defineClass(_2).addClassProperties({requestParams:_4});var _6={appID:_1,className:_2,methodName:"firstArg",isCall:true};_5.call=isc.eval(this.callTemplate.evalDynamicString(this,_6));for(var i=0;i<_3.length;i++){var _8={appID:_1,className:_2,methodName:_3[i],isCall:false};_5[_3[i]]=isc.eval(this.callTemplate.evalDynamicString(this,_8))}
window[_2]=_5;return _5},makeDMIMethod:function(_1,_2,_3,_4){var _5={appID:_1,className:_2,isCall:_3,methodName:_3?"firstArg":_4};return isc.eval(this.callTemplate.evalDynamicString(this,_5))}});isc.DMI.callBuiltin=isc.DMI.makeDMIMethod("isc_builtin","builtin",true);isc.ClassFactory.defineClass("ResultSet",null,["List","DataModel"]);isc.A=isc.ResultSet;isc.A.UNKNOWN_LENGTH=1000;isc.A=isc.ResultSet.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.cachedRows=0;isc.A.fetchAhead=true;isc.A.resultSize=75;isc.A.fetchDelay=0;isc.A.useClientSorting=true;isc.A.useClientFiltering=true;isc.A.updateCacheFromRequest=true;isc.A.updatePartialCache=true;isc.B.push(isc.A.shouldUseClientSorting=function isc_ResultSet_shouldUseClientSorting(){if(!isc.RPCManager.onLine)return true;return this.useClientSorting}
,isc.A.shouldUseClientFiltering=function isc_ResultSet_shouldUseClientFiltering(){if(!isc.RPCManager.onLine)return true;return this.useClientFiltering}
,isc.A.shouldNeverDropUpdatedRows=function isc_ResultSet_shouldNeverDropUpdatedRows(){if(!isc.RPCManager.onLine)return true;return this.neverDropUpdatedRows}
,isc.A.shouldUpdatePartialCache=function isc_ResultSet_shouldUpdatePartialCache(){if(!isc.RPCManager.onLine)return true;return this.updatePartialCache}
);isc.B._maxIndex=isc.C+4;isc.A=isc.ResultSet.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.dynamicDSFieldValues=false;isc.A.$39r=0;isc.A.notifyOnUnchangedCache=false;isc.B.push(isc.A.init=function isc_ResultSet_init(){isc.ClassFactory.addGlobalID(this);if(this.operation!=null)this.fetchOperation=this.operation;var _1=this.getOperation("fetch");var _2=_1.dataSource;if(!isc.isAn.Array(_2))_2=[_2];for(var i=0;i<_2.length;i++){var _4=isc.DS.get(_2[i]);this.observe(_4,"dataChanged","observer.dataSourceDataChanged(dsRequest,dsResponse)");if(!this.$39s)this.$39s=[];this.$39s.add(_4);if(!this.dataSource)this.dataSource=_4}
if(!this.getDataSource()){this.logError("Invalid dataSource: "+this.echoLeaf(this.dataSource)+", a ResultSet must be created with a valid DataSource")}
var _5=this.context;this.resultSize=(_5&&_5.dataPageSize!=null?_5.dataPageSize:this.resultSize);if(this.allRows){this.fetchMode="local"}else{this.fetchMode=(_5&&_5.dataFetchMode!=null?_5.dataFetchMode:this.fetchMode||"paged")}
if(this.dropCacheOnUpdate==null){this.dropCacheOnUpdate=this.$du(_1.dropCacheOnUpdate,this.getDataSource().dropCacheOnUpdate)}
this.context=this.context||{};var _6=this.criteria||this.filter||{};this.criteria=null;this.setCriteria(_6);if(this.initialData){this.prepareSparseInitialData();this.fillCacheData(this.initialData);this.setFullLength(this.initialLength||this.totalRows||this.initialData.length);if(this.sortSpecifiers)this.setSort(this.sortSpecifiers,true);if(this.sortBy)this.setSort(isc.DS.getSortSpecifiers(this.sortBy),true)}else if(this.isPaged()){this.localData=[]}
this.observe(isc,"goOffline",this.getID()+".goOffline()");this.observe(isc.RPCManager,"offlineTransactionPlaybackComplete",this.getID()+".offlinePlaybackComplete()")}
,isc.A.goOffline=function isc_ResultSet_goOffline(){}
,isc.A.offlinePlaybackComplete=function isc_ResultSet_offlinePlaybackComplete(){if(this.haveOfflineRecords){this.invalidateCache();this.haveOfflineRecords=false}}
,isc.A.destroy=function isc_ResultSet_destroy(){if(window[this.ID]==this)window[this.ID]=null;this.ignore(isc,"goOffline");this.ignore(isc.RPCManager,"offlineTransactionPlaybackComplete");if(!this.$39s)return;for(var i=0;i<this.$39s.length;i++){var _2=this.$39s[i];if(_2){this.ignore(_2,"dataChanged")}}}
,isc.A.prepareSparseInitialData=function isc_ResultSet_prepareSparseInitialData(){for(var i=0;i<this.initialData.length;i++){if(this.initialData[i]==Array.LOADING)this.initialData[i]=null}}
,isc.A.getFirstUsedIndex=function isc_ResultSet_getFirstUsedIndex(){if(!this.lengthIsKnown())return 0;if(this.localData){for(var i=0;i<this.localData.length;i++){if(this.localData[i]!=null&&this.localData[i]!=Array.LOADING)return i}}
return 0}
,isc.A.isPaged=function isc_ResultSet_isPaged(){return this.fetchMode=="paged"}
,isc.A.isLocal=function isc_ResultSet_isLocal(){return this.fetchMode=="local"}
,isc.A.allMatchingRowsCached=function isc_ResultSet_allMatchingRowsCached(){return(this.allRows!=null&&(!this.allRowsCriteria||this.$505))||(this.localData!=null&&(!this.isPaged()||(this.allRows!=null||(this.cachedRows==this.totalRows))))}
,isc.A.allRowsCached=function isc_ResultSet_allRowsCached(){return((this.allRows!=null&&(!this.allRowsCriteria||this.$505))||(this.allMatchingRowsCached()&&this.$39t))}
,isc.A.isEmpty=function isc_ResultSet_isEmpty(){if(this.isPaged()){if(this.allMatchingRowsCached()){return this.getLength()==0}else if(this.cachedRows>0)return false}
return!this.lengthIsKnown()||this.getLength()<=0}
,isc.A.canSortOnClient=function isc_ResultSet_canSortOnClient(){return this.shouldUseClientSorting()&&(this.allMatchingRowsCached()||(isc.Offline&&isc.Offline.isOffline()))}
,isc.A.canFilterOnClient=function isc_ResultSet_canFilterOnClient(){return this.shouldUseClientFiltering()&&this.allRowsCached()}
,isc.A.getLength=function isc_ResultSet_getLength(){var _1=this.unknownLength||isc.ResultSet.UNKNOWN_LENGTH;if(!this.lengthIsKnown())return _1;return(this.isPaged()&&!this.allRows?this.totalRows:this.localData.length)}
,isc.A.indexOf=function isc_ResultSet_indexOf(_1,_2,_3){if(this.localData==null)return-1;if(Array.isLoading(_1))return-1;var _4=this.localData.indexOf(_1,_2,_3);if(_4!=-1)return _4;return this.getDataSource().findByKeys(_1,this.localData,_2,_3)}
,isc.A.slideList=function isc_ResultSet_slideList(_1,_2){if(!this.allMatchingRowsCached()&&!this.shouldUpdatePartialCache()){isc.logWarn('updatePartialCache is disabled: record position will not be shifted.');return}
var _3=[],i;if(_2<0)_2=0;for(i=0;i<_2;i++){if(Array.isLoading(this.localData[i])){isc.logWarn('Sliding from a row position that has not yet been loaded, ignoring');return}
if(!_1.contains(this.localData[i]))
_3.add(this.localData[i])}
for(i=0;i<_1.length;i++){_3.add(_1[i])}
for(i=_2;i<this.localData.length;i++){if(Array.isLoading(this.localData[i])){isc.logWarn('Sliding into a row position that has not yet been loaded, ignoring');return}
if(!_1.contains(this.localData[i]))
_3.add(this.localData[i])}
if(this.shouldUpdatePartialCache()){this.invalidateRowOrder()}
this.localData=_3;this.dataChanged()}
,isc.A.get=function isc_ResultSet_get(_1){if(_1<0){this.logWarn("get: invalid index "+_1);return null}
if(this.localData!=null&&this.localData[_1]!=null)return this.localData[_1];if(this.fetchStartRow!=null&&_1>=this.fetchStartRow&&_1<=this.fetchEndRow){return Array.LOADING}
return this.getRange(_1,_1+1)[0]}
,isc.A.getRange=function isc_ResultSet_getRange(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;if(_1==null){this.logWarn("getRange() called with no specified range - ignoring.");return}
if(_2==null)_2=_1+1;if(this.isPaged()){return this.$39u(_1,_2,_3,_4)}
if(this.localData==null){this.localData=[];var _5=this.getServerFilter();this.setRangeLoading(_1,_2);this.fetchRemoteData(_5)}
return this.localData.slice(_1,_2)}
,isc.A.getAllRows=function isc_ResultSet_getAllRows(){if(!this.lengthIsKnown())return[];return this.getRange(0,this.getLength())}
,isc.A.getAllLoadedRows=function isc_ResultSet_getAllLoadedRows(){if(!this.lengthIsKnown())return[];var _1=[];for(var i=0;i<this.getLength();i++){if(this.rowIsLoaded(i))_1.add(this.localData[i])}
return _1}
,isc.A.getFieldValue=function isc_ResultSet_getFieldValue(_1,_2,_3){if(this.dynamicDSFieldValues){return this.getDataSource().getFieldValue(_1,_2,_3)}else{if(_3&&_3.dataPath){return isc.Canvas.$70o(_3.dataPath,_1)}
return _1[_2]}}
,isc.A.lengthIsKnown=function isc_ResultSet_lengthIsKnown(){return this.localData!=null&&(this.isPaged()?this.totalRows!=null:this.$39v==null)}
,isc.A.rowIsLoaded=function isc_ResultSet_rowIsLoaded(_1){if(this.localData!=null){var _2=this.localData[_1];if(_2!=null&&!Array.isLoading(_2))return true}
return false}
,isc.A.rangeIsLoaded=function isc_ResultSet_rangeIsLoaded(_1,_2){if(this.localData==null)return false;for(var i=_1;i<_2;i++){var _4=this.localData[i];if(_4==null||Array.isLoading(_4))return false}
return true}
,isc.A.findLastCached=function isc_ResultSet_findLastCached(_1,_2){if(!this.rowIsLoaded(_1))return null;if(_2){for(var i=_1;i>=0;i--){var _4=this.localData[i];if(_4==null||Array.isLoading(_4))break}
return i+1}else{var _5=this.getLength();for(var i=_1;i<_5;i++){var _4=this.localData[i];if(_4==null||Array.isLoading(_4))break}
return i-1}}
,isc.A.getCachedRange=function isc_ResultSet_getCachedRange(_1){if(_1==null)_1=this.lastRangeStart;if(_1==null)_1=0;if(!this.rowIsLoaded(_1))return null;var _2=this.getLength();if(this.allMatchingRowsCached())return[0,_2-1];var _3=this.findLastCached(_1,true),_4=this.findLastCached(_1);return[_3,_4]}
,isc.A.setRangeLoading=function isc_ResultSet_setRangeLoading(_1,_2){if(this.localData==null)this.localData=[];for(var i=_1;i<_2;i++){if(this.localData[i]==null)this.localData[i]=Array.LOADING}}
,isc.A.fillRangeLoading=function isc_ResultSet_fillRangeLoading(_1,_2){for(var i=0;i<_2;i++){if(_1[i]==null)_1[i]=Array.LOADING}
return _1}
,isc.A.getServerFilter=function isc_ResultSet_getServerFilter(){if(this.isLocal())return null;return isc.shallowClone(this.criteria)}
,isc.A.$39w=function isc_ResultSet__fetchRemoteData(){var _1=this.fetchStartRow,_2=this.fetchEndRow;if(_1==null||_2==null)return;this.setRangeLoading(_1,_2);this.fetchStartRow=null;this.fetchEndRow=null;this.logInfo("fetching rows "+[_1,_2]+" from server");return this.fetchRemoteData(this.getServerFilter(),_1,_2)}
,isc.A.fetchRemoteData=function isc_ResultSet_fetchRemoteData(_1,_2,_3){if(isc.Offline.isOffline()){this.haveOfflineRecords=true}
this.$39r+=1;var _4;if(this.context&&this.context.clientContext){this.context.clientContext.requestIndex=this.$39r}else{_4={requestIndex:this.$39r}}
var _5=isc.addProperties({operation:this.getOperationId("fetch"),startRow:_2,endRow:_3,sortBy:isc.shallowClone(this.$39x),resultSet:this,clientContext:_4},this.context);_5.clientContext.$69t=_5.willHandleError;_5.willHandleError=true;if(this.rowOrderInvalid()){this.logInfo("invalidating rows on fetch due to 'add'/'update' operation "+" with updatePartialCache");this.invalidateRows()}
if(this.logIsDebugEnabled("fetchTrace")){this.logWarn("ResultSet server fetch with server criteria: "+this.echoFull(_1)+this.getStackTrace())}
if(this.cachingAllData)_5.cachingAllData=true;this.getDataSource().fetchData(_1,{caller:this,methodName:"fetchRemoteDataReply"},_5);this.$39v=this.$39r}
,isc.A.fetchRemoteDataReply=function(dsResponse,data,request){var index=dsResponse.clientContext.requestIndex;if(!this.$39y)this.$39y=0;if(index!=(this.$39y+1)&&!dsResponse.isCachedResponse){this.logInfo("server returned out-of-sequence response for fetch remote data request "+" - delaying processing: last processed:"+this.$39y+", returned:"+index);if(!this.$39z)this.$39z=[];this.$39z.add({dsResponse:dsResponse,data:data,request:request});return}
if(this.cachingAllData==true)delete this.cachingAllData;if(this.$39v==index)delete this.$39v;var newData;var hasError=dsResponse.status<0;if(hasError||dsResponse.offlineResponse){newData=[]}else{newData=dsResponse.data}
var numResults=newData.length;this.document=dsResponse.document;this.logInfo("Received "+numResults+" records from server");if(dsResponse.startRow==null)dsResponse.startRow=request.startRow;if(dsResponse.endRow==null)dsResponse.endRow=dsResponse.startRow+numResults;if(dsResponse.totalRows==null&&dsResponse.endRow<request.endRow)
dsResponse.totalRows=dsResponse.endRow;if(this.transformData){var result=this.transformData(newData,dsResponse);newData=result!=null?result:newData;if(newData.length!=numResults){this.logInfo("Transform applied, "+newData.length+" records resulted, from "+dsResponse.startRow+" to "+dsResponse.endRow);dsResponse.endRow=dsResponse.startRow+newData.length;if(dsResponse.totalRows!=null&&dsResponse.totalRows<dsResponse.endRow){dsResponse.totalRows=dsResponse.endRow}}}
if(!isc.isA.List(newData)){this.logWarn("Bad data returned, ignoring: "+this.echo(newData));return}
if(dsResponse.totalRows!=null&&dsResponse.totalRows<dsResponse.endRow){this.logWarn("fetchData callback: dsResponse.endRow set to:"+dsResponse.endRow+". dsResponse.totalRows set to:"+dsResponse.totalRows+". endRow cannot exceed total dataset size. "+"Clamping endRow to the end of the dataset ("+dsResponse.totalRows+").");dsResponse.endRow=dsResponse.totalRows}
var startRow=dsResponse.startRow,endRow=dsResponse.endRow;this.$521();this.$390(newData,dsResponse);this.$522(startRow,endRow,hasError);delete this.context.afterFlowCallback;this.$39y=index;if(this.$39z&&this.$39z.length>0){for(var i=0;i<this.$39z.length;i++){var reply=this.$39z[i];if(reply==null)continue;var requestIndex=reply.dsResponse.clientContext.requestIndex;if(requestIndex==this.$39y+1){this.logInfo("Delayed out of sequence data response being processed now "+requestIndex);this.$39z[i]=null;this.fetchRemoteDataReply(reply.dsResponse,reply.data,reply.request);break}}}
var willHandleError=request.clientContext.$69t;if(!willHandleError&&hasError){isc.RPCManager.$a0(dsResponse,request)}}
,isc.A.$390=function isc_ResultSet__handleNewData(_1,_2){if(this.isLocal()){this.$78z(_1);this.filterLocalData();return}else if(!this.isPaged()){this.$ed();this.localData=_1;if(this.canSortOnClient()){this.$391()}
if(this.allRowsCached()){this.$78z(this.localData,this.criteria)}
this.$ee();return}
var _3=_2.context;this.$ed()
if(this.dropCacheOnLengthChange&&this.lengthIsKnown()&&this.totalRows!=_2.totalRows)
{this.logInfo("totalRows changed from "+this.totalRows+" to "+_2.totalRows+", invalidating cache");this.$394()}
if(this.localData==null)this.localData=[];this.setFullLength(_2.totalRows);this.fillCacheData(_1,_2.startRow);var _4=this.localData;for(var i=_2.startRow+_1.length;i<this.totalRows;i++){if(Array.isLoading(_4[i]))_4[i]=null;else break}
this.logInfo("cached "+_1.getLength()+" rows, from "+_2.startRow+" to "+_2.endRow+" ("+this.totalRows+" total rows, "+this.cachedRows+" cached)");if(this.allMatchingRowsCached()){if(this.allRowsCached()){this.logInfo("Cache for entire DataSource complete")}else{this.logInfo("Cache for current criteria complete")}
if(this.canSortOnClient())this.$391()}
this.$ee()}
,isc.A.setContext=function isc_ResultSet_setContext(_1){this.context=_1}
,isc.A.findByKey=function isc_ResultSet_findByKey(_1){var _2=isc.DataSource.getDataSource(this.dataSource);if(!_2)return;if(!_2.getPrimaryKeyField()||!this.lengthIsKnown())return;var _3;if(isc.isAn.Object(_1)){_3=_1}else{_3={};_3[_2.getPrimaryKeyFieldName()]=_1}
var _4=this.localData.findByKeys(_3,_2);if(_4!=null)return this.localData[_4];else return null}
,isc.A.setCriteria=function isc_ResultSet_setCriteria(_1){if(_1==null)_1={};var _2=this.$780(_1);if(_2==null){if(this.localData==null&&this.allRows!=null)this.filterLocalData();this.logInfo("setCriteria: filter criteria unchanged");return false}
if(!this.getDataSource().isAdvancedCriteria(_1)){_1=isc.clone(_1)}
var _3=this.criteria;this.criteria=_1;this.$39t=(isc.getKeys(_1).length==0);this.$51w=(this.context&&this.context.textMatchStyle)?this.context.textMatchStyle:null;if(_2){this.logInfo("setCriteria: filter criteria changed, invalidating cache");this.invalidateCache()}else{if(this.allRows==null){this.$78z(this.localData,_3)}
this.filterLocalData()}
return true}
,isc.A.$78z=function isc_ResultSet__setAllRows(_1,_2){this.allRows=_1;this.allRowsCriteria=_2||{};this.$505=(isc.getKeys(this.allRowsCriteria).length==0)}
,isc.A.setFilter=function isc_ResultSet_setFilter(_1){return this.setCriteria(_1)}
,isc.A.getCriteria=function isc_ResultSet_getCriteria(){return this.criteria}
,isc.A.compareCriteria=function isc_ResultSet_compareCriteria(_1,_2,_3,_4){return this.getDataSource().compareCriteria(_1,_2,_3?_3:this.context,_4?_4:this.criteriaPolicy)}
,isc.A.compareTextMatchStyle=function isc_ResultSet_compareTextMatchStyle(_1,_2){return this.getDataSource().compareTextMatchStyle(_1,_2)}
,isc.A.willFetchData=function isc_ResultSet_willFetchData(_1,_2){return(this.$780(_1,_2)==true)}
,isc.A.$780=function isc_ResultSet__willFetchData(_1,_2){if(_1==null)_1={};if(this.localData==null&&this.allRows==null)return true;if(_1==null)_1={};var _3=this.criteria||{},_4=this.$51w,_5=this.getDataSource();if(_2==null){_2=(this.context&&this.context.textMatchStyle)?this.context.textMatchStyle:null}
var _6=this.compareTextMatchStyle(_2,_4);var _7=this.allRows&&this.shouldUseClientFiltering()&&(_3!=this.allRowsCriteria);if(_6>=0){var _8=_7?this.allRowsCriteria:_3;if(_8==null)_8={};if(_5.isAdvancedCriteria(_1)&&!_5.isAdvancedCriteria(_8)){_8=isc.DataSource.convertCriteria(_8,_2)}
if(!_5.isAdvancedCriteria(_1)&&_5.isAdvancedCriteria(_8)){_1=isc.DataSource.convertCriteria(_1,_2)}
var _9=isc.addProperties({},this.context);if(_2!=null)_9.textMatchStyle=_2;var _10=this.compareCriteria(_1,_8,_9);if(_10!=0)_6=_10}
if(_6==0){if(_7){if(_5.isAdvancedCriteria(_1)&&!_5.isAdvancedCriteria(_3)){_3=isc.DataSource.convertCriteria(_3,_2)}
if(this.compareCriteria(_1,_3)!=0){return false}}
return null}else{if(!this.allMatchingRowsCached()){return true}
if(this.isLocal()){return false}
if(_6==-1){return true}else if(_6==1){if(this.shouldUseClientFiltering()){return false}
return true}}}
,isc.A.sortByProperty=function isc_ResultSet_sortByProperty(_1,_2,_3,_4){if(_3==null){var _5=this.getDataSource().getField(_1);if(_5)_3=_5.type}
if(this.$395==_1&&this.$392==_2&&this.$393==_3)return;var _5;if(_4){_5=_4.getField(_1);if(_5&&_5.displayField&&_5.sortByDisplayField!=false){var _6;if(_5.optionDataSource){_6=isc.DataSource.getDataSource(_5.optionDataSource)}
if(!_6||_6==isc.DataSource.getDataSource(this.dataSource)){_1=_5.displayField}}}
this.$395=_1;this.$392=_2;this.$393=_3;this.$45g=_4;if(this.isPaged()||!this.shouldUseClientSorting()){this.$39x=(this.$392?"":"-")+this.$395}
if(this.canSortOnClient()){this.localData.sortByProperties([_1],[_2],[_3],[_4]);if(this.allRows&&(this.localData!==this.allRows)){this.allRows.sortByProperties([_1],[_2],[_3],[_4])}
delete this.$572;delete this.$573;delete this.$574;delete this.$575;if(!this.$52z())this.dataChanged();return}
this.invalidateCache()}
,isc.A.unsort=function isc_ResultSet_unsort(){if(!this.allMatchingRowsCached())return false;this.$395=null;if(this.localData)this.localData.unsort();return true}
,isc.A.$391=function isc_ResultSet__doSort(){var _1=this.$73p;if(this.localData==null||!_1||_1.length==0)return;var _2=[],_3=[],_4=[],_5=[];for(var i=0;i<_1.length;i++){var _7=_1[i];_2[i]=_7.property;_3[i]=Array.shouldSortAscending(_7.direction);_4[i]=_7.normalizer;_5[i]=_7.context}
if(this.canSortOnClient()){if(_1&&_1.length>0){this.logInfo("$391: sorting on properties ["+_2.join(",")+"] : "+"directions ["+_3.join(",")+"]"+" : full cache allows local sort");this.localData.sortByProperties(_2,_3,_4,_5);delete this.$572;delete this.$573;delete this.$574;delete this.$575;if(!this.$52z())this.dataChanged()}
return}
this.logInfo("$391: sorting on properties ["+_2.join(",")+"] : "+"directions ["+_3.join(",")+"]"+" : invalidating cache");this.invalidateCache()}
,isc.A.getSort=function isc_ResultSet_getSort(){return this.$73p}
,isc.A.setSort=function isc_ResultSet_setSort(_1,_2){var _3=[],_4=[],_5;if(_1==null)_1=[];for(var i=0;i<_1.length;i++){var _7=_1[i];if(_7.normalizer==null){var _5=this.getDataSource().getField(_7.property);if(_5){_7.normalizer=_5.type;_7.$78e=_7.normalizer}}
if(_7.context){_5=_7.context.getField(_7.property)||this.getDataSource().getField(_7.property);if(_5&&_5.displayField&&_5.sortByDisplayField!=false){var _8;if(_5.optionDataSource){_8=isc.DataSource.getDataSource(_5.optionDataSource)}
if(!_5.optionDataSource||_8==isc.DataSource.getDataSource(this.dataSource)){this.logInfo("Field:"+_5.name+" has displayField:"+_5.displayField+" (with optionDataSource:"+_8+"). "+"Sorting by displayField. Set field.sortByDisplayField to false to disable this.","sorting");_7.property=_5.displayField}}}
if(this.isPaged()||!this.shouldUseClientSorting()){_3[i]=(Array.shouldSortAscending(_7.direction)?"":"-")+_7.property}
if(this.$73p&&this.$73p.length>0){var _9={property:_7.property,direction:_7.direction};if(_7.normalizer!=null&&_7.normalizer!=_7.$78e){_9.normalizer=_7.normalizer}
var _10=this.$73p.findIndex(_9);if(_10==i){_4.add(_7)}}}
if((this.$73p?this.$73p.length:0)==_1.length&&_1.length==_4.length)
{return}
this.$73p=isc.shallowClone(_1);this.$39x=isc.shallowClone(_3);if(!_2)this.$391()}
,isc.A.$521=function isc_ResultSet__startDataArriving(){var _1;if(this.$523===_1)this.$523=0;this.$523++}
,isc.A.$522=function isc_ResultSet__doneDataArriving(_1,_2,_3){if(--this.$523==0){if(!_3&&this.dataArrived)this.dataArrived(_1,_2)}}
,isc.A.$524=function isc_ResultSet__isDataArriving(){return(this.$523!=null&&this.$523>0)}
,isc.A.dataSourceDataChanged=function isc_ResultSet_dataSourceDataChanged(_1,_2){if(this.disableCacheSync)return;if(this.logIsDebugEnabled())this.logDebug("dataSource data changed firing");var _3=this.getDataSource().getUpdatedData(_1,_2,this.updateCacheFromRequest);if(this.transformData&&this.transformUpdateResponses!==false){var _4=this.transformData(_3,_2);_3=_4==null?_3:_4}
this.handleUpdate(_1.operationType,_3,_2.invalidateCache,_1)}
,isc.A.handleUpdate=function isc_ResultSet_handleUpdate(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;var _5=(this.allMatchingRowsCached()?", allMatchingRowsCached true":(", cached rows: "+this.cachedRows+", total rows: "+this.totalRows));if(this.dropCacheOnUpdate||_3||(_1!="remove"&&!this.allMatchingRowsCached()&&!this.shouldUpdatePartialCache()))
{this.invalidateCache();return}
this.logInfo("updating cache in place after operationType: "+_1+_5);this.$ed();if(!isc.isAn.Array(_2)||_2.length==1){this.$572=_1;this.$573=_2}
this.updateCache(_1,_2,_4);this.$ee();this.$572=this.$573=null}
,isc.A.$ee=function isc_ResultSet__doneChangingData(_1){var _2;if(!this.notifyOnUnchangedCache&&this.$573&&this.$575==null){_2=true}
var _3,_4,_5;if(!_2&&this.$573){_3=this.$572;_4=this.$574;_5=this.$575}
if(--this.$ef==0&&!_2){this.dataChanged(_3,_4,_5,this.$573,_1);delete this.$572;delete this.$574;delete this.$575;delete this.$576;delete this.$573}}
,isc.A.updateCache=function isc_ResultSet_updateCache(_1,_2,_3){if(_2==null)return;_1=isc.DS.$372(_1);if(!isc.isAn.Array(_2))_2=[_2];if(this.logIsInfoEnabled()){var _4=(_3.componentId?" submitted by '"+_3.componentId+"'":" (no componentID) ");this.logInfo("Updating cache: operationType '"+_1+"'"+_4+","+_2.length+" rows update data"+(this.logIsDebugEnabled()?":\n"+this.echoAll(_2):""))}
switch(_1){case"remove":this.removeCacheData(_2,_3);break;case"add":this.addCacheData(_2,_3);break;case"replace":case"update":this.updateCacheData(_2,_3);break}
if(this.shouldUpdatePartialCache()&&_1!="remove"&&!this.allMatchingRowsCached())
{this.invalidateRowOrder()}
var _5=((_1=="remove")||(_1=="update"&&this.$576==null));if(this.allRows&&!this.shouldNeverDropUpdatedRows()){this.filterLocalData()}
var _6=this.$576||this.$574;if(!_5&&_6!=null){var _7=this.indexOf(_6);if(_7==-1){delete this.$575;delete this.$574}else{this.$575=_7}}}
,isc.A.updateCacheData=function isc_ResultSet_updateCacheData(_1,_2){if(!isc.isAn.Array(_1))_1=[_1];var _3=this.allRows!=null,_4=_3?this.allRows:this.localData,_5=0,_6=0,_7=0;var _8=this.getDataSource().getPrimaryKeyFields();for(var i=0;i<_1.length;i++){var _10=_1[i],_11=isc.applyMask(_10,_8);var _12=this.getDataSource().findByKeys(_11,_4),_13;if(_12==-1){var _14=_2.data;if(isc.isAn.Array(_14))_14=_14[0];_14=isc.applyMask(_14,_8);var _15=this.getDataSource().findByKeys(_14,_4);if(_15!=-1){this.logWarn("Update operation - submitted record with primary key value[s]:"+this.echo(_14)+" returned with modified primary key:"+this.echo(_11)+". This may indicate bad server logic. Updating cache to reflect new primary key.");_6++;_4.removeAt(_15);delete this.$573}}else if(_1.length==1){_13=_4.get(_12);if(_3&&!this.getDataSource().recordMatchesFilter(_13,this.criteria,this.context))
{_13=null}
this.$574=_13;if(_13)this.$575=this.indexOf(_13)}
var _16=_3?this.allRowsCriteria:this.criteria,_17=this.getDataSource().recordMatchesFilter(_10,_16,this.context),_18=this.shouldNeverDropUpdatedRows();if(_12==-1&&_17){this.logInfo("updated row returned by server doesn't match any cached row, "+" adding as new row.  Primary key values: "+this.echo(_11)+", complete row: "+this.echo(_10));_7++;_4.add(_10);if(_1.length==1){this.$576=_10;this.$575=_4.length-1}}else if(_12!=-1){if(_17||_18){_5++;_4.set(_12,_10)}else{if(this.logIsDebugEnabled()){this.logDebug("row dropped:\n"+this.echo(_10)+"\ndidn't match filter: "+this.echo(_16))}
_6++;_4.removeAt(_12)}}else{}}
if(this.logIsDebugEnabled()){this.logDebug("updated cache: "+_7+" row(s) added, "+_5+" row(s) updated, "+_6+" row(s) removed.")}
if(!_3&&this.isPaged())
this.setFullLength(this.totalRows-_6+_7);if(!_3&&!this.shouldUpdatePartialCache())this.$391()}
,isc.A.removeCacheData=function isc_ResultSet_removeCacheData(_1){if(!isc.isAn.Array(_1))_1=[_1];var _2=this.allRows!=null,_3=_2?this.allRows:this.localData,_4=this.getDataSource(),_5=0;for(var i=0;i<_1.length;i++){var _7=_4.findByKeys(_1[i],_3);if(_7!=-1){if(_1.length==1){var _8=_3[_7];if(!_2||_4.recordMatchesFilter(_8,this.criteria,this.context))
{this.$574=_8;this.$575=this.indexOf(this.$574)}}
_3.removeAt(_7);this.cachedRows-=1;_5++}else{if(this.allMatchingRowsCached())continue;if(_4.applyFilter([_1[i]],this.criteria,this.context).length>0){if(this.logIsDebugEnabled()){this.logDebug("removed record matches filter criteria: "+this.echo(_1[i]))}
if(this.$574==null)delete this.$573;_5++}else{if(this.logIsDebugEnabled()){this.logIsDebugEnabled("cache sync ignoring 'remove' operation, removed "+" row doesn't match filter criteria: "+this.echo(_1[i]))}}}}
if(!_2&&this.isPaged())
this.setFullLength(this.totalRows-_5)}
,isc.A.addCacheData=function isc_ResultSet_addCacheData(_1){if(!isc.isAn.Array(_1))_1=[_1];if(_1==null)return;var _2;if(this.allRows==null||!this.shouldUseClientFiltering()){_2=this.getDataSource().applyFilter(_1,this.criteria,this.context)}else{_2=_1;if(this.allRowsCriteria){_2=this.getDataSource().applyFilter(_2,this.allRowsCriteria,this.context)}}
var _3;if(_2.length!=_1.length){this.logInfo("Adding rows to cache, "+_2.length+" of "+_1.length+" rows match filter criteria")}else if(_2.length==1){_3=true}
var _4=this.allRows||this.localData;if(!_4)return;if(!this.allMatchingRowsCached()&&this.shouldUpdatePartialCache()){var _5=this.getCachedRange();if(_5){if(_5[1]==this.getLength()-1||!this.rowIsLoaded(0)){var _6=_5[1]+1;_4.addListAt(_2,_6);if(_3)this.$575=_6}else{_4.addListAt(_2,0);if(_3)this.$575=0}}else{_4.addList(_2);if(_3)this.$575=_4.length-1}}else{_4.addList(_2);if(_3)this.$575=_4.length-1}
if(this.$575!=null)this.$576=_4[this.$575];this.cachedRows+=_2.length;if(this.canSortOnClient())this.$391();if(this.isPaged()&&!this.allRows)
this.setFullLength(this.totalRows+_2.length)}
,isc.A.insertCacheData=function isc_ResultSet_insertCacheData(_1,_2){if(!isc.isAn.Array(_1))_1=[_1];if(this.allRows&&(this.allRows!=this.localData)){this.allRows.addListAt(_1,_2)}
var _3=this.localData;_3.addListAt(_1,_2);if(this.isPaged())this.setFullLength(this.totalRows+_1.length)}
,isc.A.findFirstCachedRow=function isc_ResultSet_findFirstCachedRow(_1){for(var i=_1;i>=0;i--){if(this.localData[i]==null)return i+1}
return 0}
,isc.A.findLastCachedRow=function isc_ResultSet_findLastCachedRow(_1){for(var i=_1;i<this.totalRows;i++){if(this.localData[i]==null)return i-1}
return this.totalRows-1}
,isc.A.$39u=function isc_ResultSet__getRangePaged(_1,_2,_3,_4){if(this.$397){var _5=(this.ignoreCache?[]:this.localData)||[];return this.fillRangeLoading(_5.slice(_1,_2),_2-_1)}
this.$397=true;var _6=this.getRangePaged(_1,_2,_3,_4);delete this.$397;return _6}
,isc.A.getRangePaged=function isc_ResultSet_getRangePaged(_1,_2,_3,_4){if(_1<0||_2<0){this.logWarn("getRange("+_1+", "+_2+"): negative indices not supported, clamping start to 0");if(_1<0)_1=0}
if(_2<=_1){this.logDebug("getRange("+_1+", "+_2+"): returning empty list");return[]}
if(!_3&&this.lengthIsKnown()){var _5=this.getLength();if(_1>_5-1&&_1!=0){this.logWarn("getRange("+_1+", "+_2+"): start beyond end of rows, returning empty list");return[]}else if(_2>_5){_2=_5}}
if(this.localData==null)this.localData=[];if(_3){this.realCache=this.localData;this.localData=[]}
var _6=this.localData;this.lastRangeStart=_1;this.lastRangeEnd=_2;var _7,_8,_9;for(var i=_1;i<_2;i++){if(_6[i]==null){_9=true;if(_7==null)_7=i;if(_8==null||_8<i)_8=i}}
if(!_9){this.logDebug("getRange("+_1+", "+_2+") satisfied from cache");return _6.slice(_1,_2)}
var _11,_12;if(this.fetchAhead){var _13=this.$398(_1,_2,_7,_8,_3);_11=_13[0];_12=_13[1]}else{_11=_7;_12=_8+1}
this.fetchStartRow=_11;this.fetchEndRow=_12;var _14;if(_4||this.fetchDelay==0){this.$39w();_14=_6.slice(_1,_2)}else{this.fireOnPause("fetchRemoteData","$39w",this.fetchDelay);_14=this.fillRangeLoading(_6.slice(_1,_2),_2-_1)}
if(_3){this.localData=this.realCache;this.realCache=null}
return _14}
,isc.A.$398=function isc_ResultSet__addFetchAhead(_1,_2,_3,_4,_5){var _6=_5?[]:this.localData,_7=_5?Number.MAX_VALUE:this.getLength(),_8=_4-_3,_9=Math.floor((this.resultSize-_8)/2),_10=Math.max(0,_3-_9),_11=Math.min(_7,_4+_9);for(var i=_10;i<=_3;i++){var _13=_6[i];if(_13==null||Array.isLoading(_13))break}
_3=i;for(var i=_11;i>=_4;i--){var _13=_6[i];if(_13==null||Array.isLoading(_13))break}
_4=i;this.logDebug("getRange("+[_1,_2]+"), cache check: "+[_10,_11]+" firstMissingRow: "+_3+" lastMissingRow: "+_4);var _14,_15;if(_3==0||(_3>_10&&_4==_11))
{this.logDebug("getRange: guessing forward scrolling");_14=_3;_15=_14+this.resultSize;if(_15<_2)_15=_2}else if(_3==_10&&_4<_11){this.logDebug("getRange: guessing backward scrolling");_15=_4+1;_14=_15-this.resultSize;if(_14<0)_14=0;if(_14>_1)_14=_1}else{this.logDebug("getRange: no scrolling direction detected");_14=_10;_15=_11;if(_14>_1)_14=_1;if(_15<_2)_15=_2}
for(var i=_14;i<_10;i++){var _13=_6[i];if(_13==null||Array.isLoading(_13))break}
_14=i;for(var i=_15-1;i>_11;i--){var _13=_6[i];if(_13==null||Array.isLoading(_13))break}
_15=i+1;this.logInfo("getRange("+_1+", "+_2+") will fetch from "+_14+" to "+_15);return[_14,_15]}
,isc.A.filterLocalData=function isc_ResultSet_filterLocalData(){this.$ed();this.localData=this.applyFilter(this.allRows,this.criteria,isc.addProperties({dataSource:this},this.context));this.logInfo("Local filter applied: "+this.localData.length+" of "+this.allRows.length+" records matched filter:"+this.echoFull(this.criteria),"localFilter");if(this.allRows!=null&&this.shouldUseClientSorting())this.$391();if(!this.$524()&&this.dataArrived)this.dataArrived(0,this.localData.length-1);this.$ee(true)}
,isc.A.applyFilter=function isc_ResultSet_applyFilter(_1,_2,_3){return this.getDataSource().applyFilter(_1,_2,_3)}
,isc.A.getValuesList=function isc_ResultSet_getValuesList(_1){this.logInfo("asked for valuesList for property '"+_1+"'");if(this.isLocal()){if(!this.allRows){this.logWarn("asked for valuesList before data has been loaded");return[]}
var _2=this.allRows.getProperty(_1);if(!_2)return[];return _2.getUniqueItems()}
var _3=this.getCachedRange(),_4=[];for(var i=_3[0];i<=_3[1];i++){var _6=this.get(i);if(!_4.contains(_6[_1]))_4[_4.length]=_6[_1]}
return _4}
,isc.A.fillCacheData=function isc_ResultSet_fillCacheData(_1,_2){if(_2==null)_2=0;this.logDebug("integrating "+_1.length+" rows into cache at position "+_2);if(this.localData==null){this.localData=[];this.localData.length=this.getLength()}else{var _3=this.getLength(),_4=this.localData.length;if(_4>_3){this.localData=this.localData.slice(0,_3)}else if(_4!=_3){this.localData.length=_3}}
for(var i=0;i<_1.length;i++){var _6=_2+i,_7=this.localData[_6];if(_7==null||Array.isLoading(_7)){this.cachedRows++}
this.localData[_6]=_1[i]}
if(this.allRowsCached()){this.$78z(this.localData,this.criteria)}}
,isc.A.setFullLength=function isc_ResultSet_setFullLength(_1){if(!isc.isA.Number(_1))return;this.logDebug("full length set to: "+_1);if(this.isPaged())this.totalRows=_1;if(this.localData){var _2=this.localData.length;if(_2>_1){this.localData=this.localData.slice(0,_1)}else if(_2!=_1){this.localData.length=_1}}
if(this.cachedRows>_1)this.cachedRows=_1}
,isc.A.invalidateCache=function isc_ResultSet_invalidateCache(){delete this.$572;delete this.$576;delete this.$573;delete this.$574;delete this.$575;this.$394();if(!this.$52z())this.dataChanged(null,null,null,null,true)}
,isc.A.$394=function isc_ResultSet__invalidateCache(){this.invalidateRows();this.totalRows=null;this.logInfo("Invalidating cache")}
,isc.A.invalidateRows=function isc_ResultSet_invalidateRows(){this.localData=this.allRows=null;this.allRowsCriteria=null;delete this.$505;this.cachedRows=0;this.$50k=null}
,isc.A.invalidateRowOrder=function isc_ResultSet_invalidateRowOrder(){this.$50k=true}
,isc.A.rowOrderInvalid=function isc_ResultSet_rowOrderInvalid(){return this.$50k}
,isc.A.dataChanged=function isc_ResultSet_dataChanged(){if(this.onDataChanged)this.onDataChanged()}
,isc.A.getNewSelection=function isc_ResultSet_getNewSelection(_1){var _2=this.getDataSource().selectionClass||"Selection";return isc.ClassFactory.getClass(_2).create(_1)}
);isc.B._maxIndex=isc.C+76;isc.ResultSet.registerStringMethods({transformData:"newData,dsResponse",dataArrived:"startRow,endRow",dataChanged:"operationType,originalRecord,rowNum,updateData,filterChanged"});isc.ResultSet.getPrototype().toString=isc.$63a;isc.ResultSet.getPrototype().logMessage=isc.$63b;isc.ClassFactory.defineClass("LocalResultSet",isc.ResultSet);isc.A=isc.LocalResultSet.getPrototype();isc.A.fetchMode="local";isc.ClassFactory.defineClass("WindowedResultSet",isc.ResultSet);isc.ResultSet.addMethods(isc.ClassFactory.makePassthroughMethods(["find","findIndex","findNextIndex","findAllIndices","findAll","getProperty"],"localData",true,"ResultSet.${methodName} called with no cached local data."+" Calling code can use ResultSet.lengthIsKnown() to determine whether the ResultSet has"+" loaded any data."))
isc.ClassFactory.defineClass("ResultTree",isc.Tree);isc.A=isc.ResultTree.getPrototype();isc.A.nameProperty="$399";isc.A.nodeTypeProperty="nodeType";isc.A.childTypeProperty="childType";isc.A.modelType="parent";isc.A.loadDataOnDemand=true;isc.A.fetchMode="basic";isc.A.defaultNewNodesToRoot=false;isc.A.updateCacheFromRequest=true;isc.A=isc.ResultTree.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.init=function isc_ResultTree_init(_1,_2,_3,_4,_5,_6){isc.ClassFactory.addGlobalID(this);if(!this.criteria)this.criteria={};if(!this.operation)this.operation={operationType:"fetch"};if(!this.dataSource)this.dataSource=this.operation.dataSource;if(!this.operation.dataSource)this.operation.dataSource=this.dataSource;if(isc.isAn.Array(this.dataSource)){this.dataSource=this.dataSource[0];this.operation.dataSource=this.dataSource}
if(!this.isMultiDSTree()){if(!this.root)this.root=this.makeRoot();var _7=this.getTreeRelationship(this.root);var _8;if(this.rootValue===_8)this.rootValue=_7.rootValue;if(!this.loadDataOnDemand&&(this.rootValue!=null||(this.root!=null&&this.root[this.idField]!=null))&&this.discardParentlessNodes==null)
{this.discardParentlessNodes=true}
if(this.idField==null)this.idField=_7.idField;if(this.parentIdField==null)this.parentIdField=_7.parentIdField;if(_7.childrenProperty)this.childrenProperty=_7.childrenProperty;this.root[this.idField]=this.rootValue}
this.setupProperties();if(this.keepParentsOnFilter&&!this.isLocal())this.fetchMode="local";if(this.initialData){if("parent"==this.modelType)this.data=this.initialData;else if("children"==this.modelType)this.root=this.initialData}
var _9=isc.DataSource.getDataSource(this.dataSource);this.observe(_9,"dataChanged","observer.dataSourceDataChanged(dsRequest,dsResponse);");this.dropCacheOnUpdate=this.operation.dropCacheOnUpdate;if(this.defaultIsFolder==null)this.defaultIsFolder=this.loadDataOnDemand;this.invokeSuper(isc.ResultTree,"init",_1,_2,_3,_4,_5,_6);this.defaultLoadState=this.loadDataOnDemand?isc.Tree.UNLOADED:isc.Tree.LOADED}
,isc.A.destroy=function isc_ResultTree_destroy(){this.Super("destroy",arguments);var _1=isc.DataSource.getDataSource(this.dataSource);if(_1)this.ignore(_1,"dataChanged")}
,isc.A.isLocal=function isc_ResultTree_isLocal(){return this.fetchMode=="local"}
,isc.A.haveCriteria=function isc_ResultTree_haveCriteria(_1){return _1!=null&&!isc.isAn.emptyObject(_1)}
,isc.A.setContext=function isc_ResultTree_setContext(_1){this.context=_1}
,isc.A.getTreeRelationship=function isc_ResultTree_getTreeRelationship(_1){var _2=this.getChildDataSource(_1);var _3=_2.getTreeRelationship();return _3}
,isc.A.getChildDataSource=function isc_ResultTree_getChildDataSource(_1,_2){var _3=_1[this.childTypeProperty];if(_3!=null)return isc.DS.get(_3);var _2=_2||this.getNodeDataSource(_1);if(_2==null||!this.isMultiDSTree())return this.getRootDataSource();var _4=this.treeRelations,_5=_2.getChildDataSources();if(_4){_3=_4[_2.ID];if(_3!=null)return isc.DS.get(_3)}
if(_5!=null)return _5[0]}
,isc.A.getNodeDataSource=function isc_ResultTree_getNodeDataSource(_1){var _2=_1[this.nodeTypeProperty];if(_2==null){var _3=this.getParent(_1);if(_3==null){return null}else if(_3==this.root){_2=this.getRootDataSource().ID}else{_2=_3.$40a;if(_2==null)_2=this.getRootDataSource().ID}}
return isc.DS.get(_2)||this.getRootDataSource()}
,isc.A.isMultiDSTree=function isc_ResultTree_isMultiDSTree(){return this.multiDSTree||this.treeRelations!=null}
,isc.A.getRootDataSource=function isc_ResultTree_getRootDataSource(){if(this.operation&&this.operation.dataSource)return isc.DS.get(this.operation.dataSource);else return isc.DS.get(this.dataSource)}
,isc.A.getCriteria=function isc_ResultTree_getCriteria(_1,_2,_3){if(this.getRootDataSource()==_1)return this.criteria;return null}
,isc.A.getOperationId=function isc_ResultTree_getOperationId(_1,_2,_3){return this.operation?this.operation.ID:null}
,isc.A.loadChildren=function isc_ResultTree_loadChildren(_1,_2){var _3=(_1==null||_1==this.root),_4,_5,_6;if(_3&&this.isMultiDSTree()){_5=this.getRootDataSource();_4={childDS:_5}}else{_4=this.getTreeRelationship(_1);_5=_4.childDS;_6=_4.parentDS}
if(!this.isMultiDSTree()){_4.idField=this.idField;_4.parentIdField=this.parentIdField;_4.rootValue=_4.rootValue}
if(this.logIsDebugEnabled()){this.logDebug("parent id: "+(_3?"[root]":_1[_4.idField])+" (type: "+(_3?"[root]":_6.ID)+")"+" has childDS: "+_5.ID+", relationship: "+this.echo(_4))}
_1.$40a=_5.ID;var _7={};if(!this.isLocal()){_7=isc.addProperties({},this.getCriteria(_5,_6,_1))}else if(this.$868!=null){_7=isc.addProperties({},this.$868)}
if(_3&&this.isMultiDSTree()){}else if(this.loadDataOnDemand){var _8=_1[_4.idField];var _9;if(_3&&_8===_9){_8=_4.rootValue}
_7[_4.parentIdField]=_8}else{this.defaultLoadState=isc.Tree.LOADED}
var _10=isc.addProperties({parentNode:_1,relationship:_4,childrenReplyCallback:_2},this.context?this.context.clientContext:null);if(!this.$84a){_10.$84b=true;this.$84a=true}
var _11=isc.addProperties({parentNode:_1,resultTree:this},this.context,{clientContext:_10});var _12=this.getOperationId(_5,_6,_1);if(_12)_11.operationId=_12;_11.willHandleError=true;if(_1!=null)this.setLoadState(_1,isc.Tree.LOADING);_5.fetchData(_7,{caller:this,methodName:'loadChildrenReply'},_11)}
,isc.A.loadChildrenReply=function isc_ResultTree_loadChildrenReply(_1,_2,_3){var _4=_1.clientContext;var _5=_4.parentNode;var _6=(this.isLocal()&&this.haveCriteria(this.$869||this.criteria));var _7=_4.relationship,_8=_1.data;if(_1.status<0)_8=null;if(_1.status==isc.RPCResponse.STATUS_OFFLINE){_8=[];if(_5!=null&&!this.isRoot(_5)){isc.say(window[_3.componentId].offlineNodeMessage)}}
var _9=this;if(_6){if(!this.completeTree)this.completeTree=this.duplicate(true,true);_9=this.completeTree;var _10=this.getPath(_5);_5=_9.find(_10)}
if(_8==null||_8.length==0){if(_1.status==isc.RPCResponse.STATUS_OFFLINE){_9.setLoadState(_5,isc.Tree.UNLOADED);_9.delayCall("closeFolder",[_5],0)}else{_9.setLoadState(_5,isc.Tree.LOADED)}
if(_8==null){if(_1.status<0){isc.RPCManager.$a0(_1,_3)}else{this.logWarn("null children returned; return empty List instead")}
_8=[]}}
if(_9.isMultiDSTree()){for(var i=0;i<_8.length;i++){var _12=_8[i];var _13=this.getChildDataSource(_12,_7.childDS);if(_13!=null)this.convertToFolder(_12);_9.add(_12,_5)}}else{if(_1.status==isc.RPCResponse.STATUS_OFFLINE){_9.setLoadState(_5,isc.Tree.UNLOADED);_9.delayCall("closeFolder",[_5],0)}else{var _14=_6;_9.linkNodes(_8,_7.idField,_7.parentIdField,_7.rootValue,_7.isFolderProperty,_5,_14)}}
if(_6){var _15=(this.$87a?this.$87a:this.$87b());this.filterLocalData();this.$87c(_15);if(this.$87a)delete this.$87a}
if(this.$87a){this.$87c(this.$87a);delete this.$87a}
if(_4.childrenReplyCallback){this.fireCallback(_4.childrenReplyCallback,"node",[_5],this)}
if(this.dataArrived!=null){isc.Func.replaceWithMethod(this,"dataArrived","parentNode");this.dataArrived(_5)}}
,isc.A.getDataSource=function isc_ResultTree_getDataSource(){return isc.DataSource.getDataSource(this.dataSource)}
,isc.A.invalidateCache=function isc_ResultTree_invalidateCache(){if(!this.isLoaded(this.root))return;this.$87a=this.$87b();this.defaultLoadState=isc.Tree.UNLOADED;this.setRoot(this.makeRoot(),true);this.defaultLoadState=this.loadDataOnDemand?isc.Tree.UNLOADED:isc.Tree.LOADED;if(!this.loadDataOnDemand)this.reloadChildren(this.root)}
,isc.A.dataSourceDataChanged=function isc_ResultTree_dataSourceDataChanged(_1,_2){if(this.disableCacheSync)return;var _3=isc.DataSource.getUpdatedData(_1,_2,this.updateCacheFromRequest);this.handleUpdate(_1.operationType,_3,_2.invalidateCache)}
,isc.A.handleUpdate=function isc_ResultTree_handleUpdate(_1,_2,_3){if(isc.$cv)arguments.$cw=this;if(this.dropCacheOnUpdate||_3){this.invalidateCache();if(!this.getDataSource().canQueueRequests)this.dataChanged();return}
this.updateCache(_1,_2);this.dataChanged()}
,isc.A.updateCache=function isc_ResultTree_updateCache(_1,_2){if(_2==null)return;_1=isc.DS.$372(_1);if(!isc.isAn.Array(_2))_2=[_2];if(this.logIsInfoEnabled()){this.logInfo("Updating cache: operationType '"+_1+"', "+_2.length+" rows update data"+(this.logIsDebugEnabled()?":\n"+this.echoAll(_2):""))}
switch(_1){case"remove":this.removeCacheData(_2);break;case"add":this.addCacheData(_2);break;case"replace":case"update":this.updateCacheData(_2);break}}
,isc.A.addCacheData=function isc_ResultTree_addCacheData(_1){if(!isc.isAn.Array(_1))_1=[_1];var _2=this.getDataSource().applyFilter(_1,this.criteria,this.context);this.logInfo("Adding rows to cache: "+_2.length+" of "+_1.length+" rows match filter criteria");var _3=this.getDataSource().getPrimaryKeyFieldNames()[0];for(var i=0;i<_2.length;i++){if(this.completeTree){this.$651(this.completeTree,_2[i],_3)}
this.$651(this,_2[i],_3)}}
,isc.A.$651=function isc_ResultTree__addNodeToCache(_1,_2,_3){if(_3==null)_3=this.getDataSource().getPrimaryKeyFieldNames()[0];var _4=_2[this.parentIdField],_5;if(_4!=null)_5=_1.find(_3,_4);else{if(this.defaultNewNodesToRoot||_1.rootValue==null)_5=_1.getRoot();else _5=null}
if(_5!=null&&(_1.getLoadState(_5)==isc.Tree.LOADED)){_2=isc.clone(_2);_1.add(_2,_5);return true}
return false}
,isc.A.updateCacheData=function isc_ResultTree_updateCacheData(_1){if(!isc.isAn.Array(_1))_1=[_1];var _2={addedRows:0,updatedRows:0,removedRows:0};var _3=(this.isLocal()&&this.haveCriteria(this.$869||this.criteria));var _4=this.getDataSource(),_5=this.$87d||this.criteria,_6=this.haveCriteria(_5);for(var i=0;i<_1.length;i++){var _8=_1[i];var _9=true;if(_6){var _10=_4.applyFilter([_8],_5,this.context);_9=(_10!=null&&_10.length>0)}
if(this.logIsDebugEnabled()&&!_9){this.logDebug("updated node :\n"+this.echo(_8)+"\ndidn't match filter: "+this.echo(_5))}
if(this.completeTree){this.$87e(this.completeTree,_8,true,null)}
this.$87e(this,_8,_9,_2)}
if(this.logIsDebugEnabled()){this.logDebug("updated cache: "+_2.addedRows+" row(s) added, "+_2.updatedRows+" row(s) updated, "+_2.removedRows+" row(s) removed.")}}
,isc.A.$87e=function isc_ResultTree__updateNodeInCache(_1,_2,_3,_4){var _5=this.getDataSource(),_6=_5.getPrimaryKeyFieldNames()[0],_7=_1.find(_6,_2[_6]);if(_7==null){if(_3){if(this.$651(_1,_2,_6)){this.logInfo("updated row returned by server doesn't match any cached row, "+" adding as new row.  Primary key value: "+this.echo(_2[_6])+", complete row: "+this.echo(_2));if(_4)_4.addedRows++}}
return}
if(_3){if(_2[this.parentIdField]!=_7[this.parentIdField]){var _8=_1.find(_6,_2[this.parentIdField]);if(_8==null&&(this.defaultNewNodesToRoot||this.rootValue==null))
{_8=_1.getRoot()}
if(_8==null||(_1.getLoadState(_8)!=isc.Tree.LOADED)){_1.remove(_7)
if(_4)_4.removedRows++;return}
_1.move(_7,_8)}
isc.addProperties(_7,_2);var _9=_5.getFieldNames();for(var i=0;i<_9.length;i++){var _11=_9[i];if(!_2.hasOwnProperty(_11))delete _7[_11]}
if(_4)_4.updatedRows++}else{_1.remove(_7);if(_4)_4.removedRows++}}
,isc.A.removeCacheData=function isc_ResultTree_removeCacheData(_1){if(!isc.isAn.Array(_1))_1=[_1];var _2=this.getDataSource().getPrimaryKeyFieldNames()[0];if(this.completeTree){this.$87f(this.completeTree,_1,_2)}
this.$87f(this,_1,_2)}
,isc.A.$87f=function isc_ResultTree__removeNodesFromCache(_1,_2,_3){if(_3==null)_3=this.getDataSource().getPrimaryKeyFieldNames()[0];var _4=[];for(var i=0;i<_2.length;i++){var _6=_1.find(_3,_2[i][_3]);if(_6==null){this.logWarn("Cache synch: couldn't find deleted node:"+this.echo(_2[i]));continue}
_4.add(_6)}
_1.removeList(_4)}
,isc.A.getTitle=function isc_ResultTree_getTitle(_1){var _2=this.getNodeDataSource(_1);if(!_2)return"root";var _3=_1[_2.getTitleField()];if(_3!=null)return _3;return this.Super("getTitle",arguments)}
,isc.A.indexOf=function isc_ResultTree_indexOf(_1,_2,_3,_4,_5){var _6=this.getDataSource().getPrimaryKeyFieldNames();for(var i=0;i<_6.length;i++){var _8=_6[i];if(_1[_8]!=null)return this.findIndex(_8,_1[_8])}
return this.invokeSuper(isc.ResultTree,"indexOf",_1,_2,_3,_4,_5)}
,isc.A.setCriteria=function isc_ResultTree_setCriteria(_1){var _2=this.criteria||{},_3=this.$868||{},_4=this.getDataSource();if(!_4.isAdvancedCriteria(_1)){_1=isc.clone(_1)}
this.criteria=_1;if(_4.isAdvancedCriteria(_1)&&!_4.isAdvancedCriteria(_2)){_2=isc.DataSource.convertCriteria(_2)}
if(!_4.isAdvancedCriteria(_1)&&_4.isAdvancedCriteria(_2)){_1=isc.DataSource.convertCriteria(_1);this.criteria=_1}
if(this.serverFilterFields!=null&&this.serverFilterFields.length>0){this.$87d=isc.DataSource.copyCriteria(_1);this.$868=_4.splitCriteria(this.$87d,this.serverFilterFields)}else{delete this.$87d;delete this.$868}
var _5=this.compareCriteria(_1,_2);if(_5!=0){if(!this.isLocal()){this.logInfo("setCriteria: filter criteria changed, invalidating cache");this.invalidateCache()}else{if((isc.isAn.emptyObject(this.$868)&&!isc.isAn.emptyObject(_3))||(!isc.isAn.emptyObject(this.$868)&&this.compareCriteria(this.$868,_3)!=0))
{this.logInfo("setCriteria: filter criteria changed, invalidating cache");this.invalidateCache()}else{var _6=this.$87b();if(isc.isAn.emptyObject(_2)){this.completeTree=this.duplicate(true,true)}
this.filterLocalData();this.$87c(_6);if(this.dataArrived!=null){isc.Func.replaceWithMethod(this,"dataArrived","parentNode");this.dataArrived(this.getRoot())}}}}}
,isc.A.filterLocalData=function isc_ResultTree_filterLocalData(_1){if(!_1)_1=this.getRoot();var _2=this.$87d||this.criteria,_3=this.completeTree;if(this.haveCriteria(_2)){var _4=(this.keepParentsOnFilter?isc.Tree.KEEP_PARENTS:isc.Tree.STRICT),_5=this.getDataSource();_3=this.applyFilter(this.completeTree,_2,_4,_5);this.logInfo("Local filter applied: "+_3.getNodeList().length+" of "+this.completeTree.getNodeList().length+" records matched filter:"+this.echoFull(_2))}else{this.logInfo("Local filter applied: all "+_3.getNodeList().length+" records matched filter: none");delete this.completeTree}
this.data=null;this.setRoot(this.getCleanNodeData(this.getRoot(),false,true));var _6=_3.getAllNodes();_6=_3.getCleanNodeData(_6,false,true);this.linkNodes(_6);this.$736(true)}
,isc.A.applyFilter=function isc_ResultTree_applyFilter(_1,_2,_3,_4){return _1.getFilteredTree(_2,_3,_4)}
,isc.A.compareCriteria=function isc_ResultTree_compareCriteria(_1,_2,_3,_4){return this.getDataSource().compareCriteria(_1,_2,_3?_3:this.context,"dropOnChange")}
,isc.A.willFetchData=function isc_ResultTree_willFetchData(_1){var _2;var _3=this.criteria||{},_4=this.$868||{},_5=this.getDataSource();if(!_5.isAdvancedCriteria(_1)){_1=isc.DataSource.copyCriteria(_1)}
if(_5.isAdvancedCriteria(_1)&&!_5.isAdvancedCriteria(_3)){_3=isc.DataSource.convertCriteria(_3)}
if(!_5.isAdvancedCriteria(_1)&&_5.isAdvancedCriteria(_3)){_1=isc.DataSource.convertCriteria(_1);this.criteria=_1}
var _6=this.compareCriteria(_1,_3);if(_6==0)return false;if(this.isLocal())return true;var _7={};if(this.serverFilterFields!=null&&this.serverFilterFields.length>0){var _8=isc.DataSource.copyCriteria(_1);_7=_5.splitCriteria(_8,this.serverFilterFields)}
return((isc.isAn.emptyObject(_7)&&!isc.isAn.emptyObject(_4))||(!isc.isAn.emptyObject(_7)&&this.compareCriteria(_7,_4)!=0))}
,isc.A.getOpenState=function isc_ResultTree_getOpenState(){var _1=this.$87b();return isc.Comm.serialize(_1)}
,isc.A.$87b=function isc_ResultTree__getOpenState(){var _1=this.getRoot(),_2=[];this.$45q(this,_1,_2);return _2}
,isc.A.setOpenState=function isc_ResultTree_setOpenState(_1){_1=isc.Canvas.evalViewState(_1,"openState",false,this)
if(!_1)return;this.$87c(_1)}
,isc.A.$87c=function isc_ResultTree__setOpenState(_1){if(!_1)return;this.closeAll();this.openFolders(_1)}
,isc.A.$45q=function isc_ResultTree__addNodeToOpenState(_1,_2,_3){if(!_1.isOpen(_2)||!_1.isLoaded(_2))return false;var _4=_1.getFolders(_2),_5=false;if(_4!=null){for(var i=0;i<_4.length;i++){_5=this.$45q(_1,_4[i],_3)||_5}}
_3[_3.length]=_1.getPath(_2);return true}
);isc.B._maxIndex=isc.C+37;isc.ResultTree.getPrototype().toString=isc.$63a;isc.ResultTree.getPrototype().logMessage=isc.$63b;isc.ResultTree.registerStringMethods({dataArrived:"parentNode"});isc.A=isc.Canvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.buildRequest=function isc_Canvas_buildRequest(_1,_2,_3){if(!_1)_1={};if(_3)_1.afterFlowCallback=_3;if(_2=="filter"){_2="fetch";if(_1.textMatchStyle==null)_1.textMatchStyle="substring"}
if(this.textMatchStyle!=null)_1.textMatchStyle=this.textMatchStyle;_2=isc.DS.$372(_2);if(this.dataPageSize)_1.dataPageSize=this.dataPageSize;if(this.dataFetchMode)_1.dataFetchMode=this.dataFetchMode;var _4=_1.operationId||_1.operation;if(_4==null){switch(_2){case"fetch":_4=this.fetchOperation;break;case"add":_4=this.addOperation||this.saveOperation;break;case"update":_4=this.updateOperation||this.saveOperation;break;case"remove":_4=this.removeOperation||this.deleteOperation;break;case"validate":_4=this.validateOperation;break}}
_1.operation=_4||this.operation;if(_1.operationId==null){_1.operationId=_1.operation}
_1.componentId=this.ID;return isc.rpc.addDefaultOperation(_1,this.dataSource,_2)}
,isc.A.createResultTree=function isc_Canvas_createResultTree(_1,_2,_3,_4){if(_4==null)_4="fetch";if(_3==null)_3={};if(_3.clientContext==null)_3.clientContext={};_3.clientContext.$53w=_2;var _5=_3.clientContext
if(_3.afterFlowCallback!=null){if(_5.$53w==null){_5.$53w=_3.afterFlowCallback}else if(_5.$53w!=_3.afterFlowCallback){this.logWarn("createResultTree called with request.afterFlowCallback:"+this.echo(_3.afterFlowCallback)+" as well as explicit callback parameter "+this.echo(_2)+". The request.afterFlowCallback will not be fired.")}}
_3.afterFlowCallback={target:this,methodName:"$53x"};var _6=isc.addProperties({initialData:this.initialData},this.dataProperties,_3.dataProperties,this.treeProperties,_3.treeProperties);_6.criteria=_1;_6.operation=_3.operation;_6.context=_3;_6.dataSource=this.dataSource;_6.componentId=this.ID;_6.$31k=true;if(this.loadDataOnDemand!=null)_6.loadDataOnDemand=this.loadDataOnDemand;if(this.keepParentsOnFilter!=null)_6.keepParentsOnFilter=this.keepParentsOnFilter;if(this.dataFetchMode!=null)_6.fetchMode=this.dataFetchMode;if(this.serverFilterFields!=null)_6.serverFilterFields=this.serverFilterFields;if(this.treeRootValue!=null)_6.rootValue=this.treeRootValue;if(this.treeDataRelations)_6.treeRelations=this.treeDataRelations;if(this.multiDSTree!=null)_6.multiDSTree=this.multiDSTree;var _7=this.getDataSource().resultTreeClass||"ResultTree";return isc.ClassFactory.getClass(_7).create(_6)}
,isc.A.$53x=function isc_Canvas__fireFetchCallback(_1,_2,_3){var _4=_3.clientContext;if(_4&&_4.$84b&&_4.$53w!=null){var _5=_4.$53w;this.fireCallback(_5,"dsResponse,data,dsRequest",arguments)}}
);isc.B._maxIndex=isc.C+3;if(isc.ValuesManager){isc.A=isc.ValuesManager.getPrototype();isc.A.buildRequest=isc.Canvas.getInstanceProperty("buildRequest")}
isc.ClassFactory.defineInterface("EditorActionMethods");isc.EditorActionMethods.addInterfaceMethods({save:function(_1){return this.saveData(_1)},editSelected:function(_1,_2){return this.editSelectedData(_1,_2)},editNew:function(_1,_2){return this.editNewRecord(_1,_2)},editNewRecord:function(_1){this.setSaveOperationType("add");this.$39m(_1)},editRecord:function(_1){var _2=(_1==null?"add":"update");this.setSaveOperationType(_2);this.$39m(_1)},$39m:function(_1){delete this.$50l;delete this.$39l;var _1=isc.addProperties({},_1);this.setData(_1);if(this.getFileItemForm!=null){var _2=this.getFileItemForm();if(_2){_2.editNewRecord();_2.clearErrors(true)}}},editSelectedData:function(_1){if(isc.isA.String(_1))_1=window[_1];if(!_1)return;var _2=_1.selection.getSelection();if(_2&&_2.length>0){var _3=[];for(var i=0;i<_2.length;i++){_3[i]=_1.getCleanRecordData(_2[i])}
this.editList(_3)}},editList:function(_1){this.setSaveOperationType("update");this.$50b(_1)},$50b:function(_1){this.$50l=0;this.$39l=_1;var _2=isc.addProperties({},_1[this.$50l]);this.editRecord(_2)},editNextRecord:function(){this.editOtherRecord(true)},editPrevRecord:function(){this.editOtherRecord(false)},editOtherRecord:function(_1){if(!this.$39l)return;if(this.isVisible()&&this.valuesHaveChanged()){this.$39n=_1;this.saveData({target:this,methodName:"editOtherReply"});return};if(_1&&this.$50l>=this.$39l.length-1){this.logWarn("Unable to edit next record - this is the last selected record");return false}
if(!_1&&this.$50l<=0){this.logWarn("Unable to edit previous record - this is the first selected record");return false}
this.$50l+=(_1?1:-1);var _2=isc.addProperties({},this.$39l[this.$50l]);this.setData(_2)},editOtherReply:function(_1,_2,_3){var _4=this.$39n;delete this.$39n;if(_1.status<0&&_1.errors){return this.setErrors(_1.errors,true)}
if(_1.status<0)return isc.RPCManager.$a0(_1,_3);this.rememberValues();this.$39l[this.$50l]=this.getValues();this.editOtherRecord(_4)
return true},validateData:function(_1,_2){if(!this.validate())return false;var _3=this.getValues();var _4=this.buildRequest(_2,"validate");_4.editor=this;if(_4.valuesAsParams){if(!_4.params)_4.params={};isc.addProperties(_4.params,_3)}
var _5=this.getDataSource();return _5.validateData(_3,_1?_1:{target:this,methodName:"saveEditorReply"},_4)},reset:function(){this.resetValues()},cancel:function(_1){var _2={actionURL:this.action,target:window,sendNoQueue:true,ignoreTimeout:true,useXmlHttpRequest:false,params:{},useSimpleHttp:true};_2.params[this.cancelParamName]=this.cancelParamValue;if(!_2.actionURL){this.logWarn("No actionURL defined for the cancel RPC - set 'action' on your form or"+"provide an actionURL in the requestProperties to cancel()");return}
isc.addProperties(_2,_1);isc.rpc.sendRequest(_2)},cancelEditing:function(){if(this.valuesManager!=null){this.valuesManager.cancelEditing()}
if(this.userTask!=null){this.userTask.cancelEditing()}},completeEditing:function(){if(this.valuesManager!=null){this.valuesManager.completeEditing()}
if(this.userTask!=null){this.userTask.completeEditing()}},submit:function(_1,_2){if(this.valuesManager!=null){return this.valuesManager.submit(_1,_2)}
if(this.submitValues!=null){return this.submitValues(this.getValues(),this)}
if(this.canSubmit)return this.submitForm();else return this.saveData(_1,_2)},saveOperationIsAdd:function(){if(this.saveOperationType)return this.saveOperationType=="add";if(this.dataSource){var _1=isc.DataSource.getDataSource(this.dataSource);return!_1.recordHasAllKeys(this.getValues())}
return false},saveData:function(_1,_2,_3){if(this.selectionComponent!=null){var _4=this.$71d;if(_4&&this.selectionComponent.setRecordValues){this.selectionComponent.setRecordValues(_4,this.getValues())}
return}
if(this.dataSource==null){this.logWarn("saveData() called on a non-databound "+this.Class+". This is not supported. "+" for information on databinding of components look at the documentation"+" for the DataSource class.  "+"If this was intended to be a native HTML form submission, set the "+"canSubmit property to true on this form.");return}
if(isc.ValuesManager&&isc.isA.ValuesManager(this.valuesManager)&&this.dataPath){var _5=this.getFields();for(var i=0;i<_5.length;i++){var _7=_5[i].dataPath||_5[i].name;var _8=this.getValue(_7);this.valuesManager.$10y(_7,_8,this)}
return}
if(isc.Offline&&isc.Offline.isOffline()&&!this.dataSource.clientOnly){isc.warn(this.offlineSaveMessage);return}
if(_2==null&&isc.isAn.Object(_1)&&_1.methodName==null)
{_2=_1;_1=_2.afterFlowCallback}
if(_2==null)_2={};var _9=this.getSaveOperationType(_2);if(!_2.oldValues){_2.oldValues=_9!="add"?this.$10s:{}}
if(this.validationURL&&!_3){var _10={};isc.addProperties(_10,_2);isc.addProperties(_10,{actionURL:this.validationURL,valuesAsParams:true,sendNoQueue:true});_10.$40b=_2;_10.$40c=_1;this.performingServerValidation=true;this.validateData(this.getID()+".$40d(rpcRequest,rpcResponse,data)",_10);return}
var _11=this.getFileItemForm();if(_11&&_11.isDrawn()){this.updateFileItemForm();if(!this.validate())return false;return _11.saveData(_1,_2,_3)}
this.$40c=_1;_1=this.getID()+".$49z(dsRequest, dsResponse, data)";_2=this.buildRequest(_2,_9,_1);var _12=false;if(this.submitParamsOnly)_2.useSimpleHttp=true;if(isc.DynamicForm&&isc.isA.DynamicForm(this)){if(this.$66g){_2.actionURL=this.action;_2.target=this.target?this.target:window;_12=true}
if(this.method!=isc.DynamicForm.getInstanceProperty("method")){_2.httpMethod=this.method}}
if(this.disableValidation)this.clearErrors(true);else{if(!this.validate(null,null,null,null,true))return false}
if(this.rulesEngine){var _13=this.rulesEngine.processSubmit(this);if(_13==false)return}
var _14=this.getValues();if((isc.DynamicForm&&isc.isA.DynamicForm(this)&&this.isMultipart())||this.canSubmit||_12)
{return this.submitEditorValues(_14,_2.operation,_2.callback,_2)}else{return this.saveEditorValues(_14,_2.operation,_2.callback,_2)}},updateFileItemForm:function(){var _1=this.getFileItemForm();if(_1==null)return;var _2=_1.getValues(),_3=this.getValues(),_4=_1.getItem(0).getFieldName();for(var _5 in _2){if(_5==_4)continue;_1.setValue(_5,null)}
for(var _5 in _3){if(_5==_4)continue;_1.setValue(_5,_3[_5])}
if(this.$66g)_1.setAction(this.action);_1.dataSource=this.dataSource},isNewRecord:function(){return this.getSaveOperationType()=="add"},setSaveOperationType:function(_1){this.saveOperationType=_1},getSaveOperationType:function(_1){var _2;if(!_1||!_1.operation){_2=(_1&&_1.operationType)?_1.operationType:this.saveOperationType;if(!_2&&this.dataSource!=null){var _3=isc.DataSource.getDataSource(this.dataSource).getPrimaryKeyFieldNames(),_4=this.getValues(),_5;for(var i=0;i<_3.length;i++){var _7=_3[i],_8=_4[_3];if(_8==null){_2="add";break}
if(this.$10s[_7]!==_5&&this.$10s[_7]!=_8){_2="add"}
var _9=this.getItem(_7);if(_9&&_9.isVisible()&&(_9.shouldSaveValue&&_9.isEditable())){_2="add"
break}}
if(_2==null){_2="update"}}}
return _2},$49z:function(_1,_2,_3){this.$490=0;if(!this.suppressServerDataSync&&_2&&_2.status>=0&&_3!=null){if(isc.isAn.Array(_3))_3=_3[0];if(_1.originalData)_1.originalData=isc.shallowClone(_1.originalData);if(_1.data)_1.data=isc.shallowClone(_1.data);var _4=(_1.originalData||_1.data),_5=this.getValues();var _6=false;for(var i in _3){var _8=this.getField(i);if(!this.fieldValuesAreEqual(_8,_4[i],_3[i])&&this.fieldValuesAreEqual(_8,_5[i],_4[i]))
{_5[i]=_3[i];_6=true}}
if(_6){this.$10r(_5);for(var i=0;i<this.items.length;i++){var _9=this.items[i];if(isc.isAn.UploadItem(_9))continue;var _10=_9.dataPath||_9.name;if(_10==null)continue;var _11=isc.DynamicForm.$70o(_10,_4),_12=isc.DynamicForm.$70o(_10,_3);if(!this.fieldValuesAreEqual(_9,_11,_12)&&this.fieldValuesAreEqual(_9,_11,_9.getValue()))
{_9.setValue(_12)}}}
if(this.saveOperationType=="add")delete this.saveOperationType}
this.$491={request:_1,response:_2,data:_3};this.formSavedComplete();var _13=this.getItem("_transaction");if(_13&&isc.isA.HiddenItem(_13)){_13.clearValue()}},formSavedComplete:function(){var _1=this.getFields();for(var i=this.$490;i<_1.length;i++){this.$490++;var _3=_1[i];if(isc.isA.Function(_3.formSaved)&&_3.formSaved(this.$491.request,this.$491.response,this.$491.data)===false)return}
if(this.$40c){this.fireCallback(this.$40c,"dsResponse,data,dsRequest",[this.$491.response,this.$491.data,this.$491.request])}
delete this.$492;delete this.$40c},saveEditorValues:function(_1,_2,_3,_4){var _5;if(!_4)_4={};isc.addProperties(_4,{prompt:(_4.prompt||isc.RPCManager.saveDataPrompt),editor:this});if(_4.clientContext==null)_4.clientContext={};_4.clientContext.$69t=_4.willHandleError;_4.willHandleError=true;if(_4.valuesAsParams){if(!_4.params)_4.params={};isc.addProperties(_4.params,_1)}
var _6=this.getDataSource();return _6.performDSOperation(_2.type,_1,_3?_3:{target:this,methodName:"saveEditorReply"},_4)},submitEditorValues:function(_1,_2,_3,_4){if(!_4)_4={};isc.addProperties(_4,{directSubmit:true,submitForm:this});return this.saveEditorValues(_1,_2,_3,_4)},saveEditorReply:function(_1,_2,_3){if(_3.clientContext){_3.willHandleError=_3.clientContext.$69t}
if(_1.status==isc.RPCResponse.STATUS_VALIDATION_ERROR&&_1.errors){if(isc.isA.FileItem(this.targetItem))
this.parentElement.setErrors(_1.errors,true);else this.setErrors(_1.errors,true);return this.suppressValidationErrorCallback?false:_3.willHandleError==true}
if(_1.status<0&&!_3.willHandleError)
return isc.RPCManager.$a0(_1,_3);return true},$40d:function(_1,_2,_3){if(_2.status==isc.RPCResponse.STATUS_SUCCESS){this.performingServerValidation=false;this.markForRedraw("serverValidationSuccess");this.saveData(_1.$40c,_1.$40b,true);_1.$40c=null;_1.$40b=null}else{this.setErrors(_2.errors,true)}}});if(isc.DynamicForm){isc.ClassFactory.mixInInterface("DynamicForm","EditorActionMethods");isc.A=isc.DynamicForm.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.selectionComponentSelectionChanged=function isc_DynamicForm_selectionComponentSelectionChanged(_1,_2,_3){if(!_3)_2={};this.$71d=_1.getPrimaryKeys(_2);this.clearErrors(true);if(this.valuesManager&&this.valuesManager.$707){this.valuesManager.$707(this)}else{this.editRecord(isc.addProperties({},_2))}}
,isc.A.selectionComponentCellSelectionChanged=function isc_DynamicForm_selectionComponentCellSelectionChanged(_1,_2){for(var i=0;i<_2.length;i++){var _4=_2[i],_5=this.selectionComponent.getCellRecord(_4[0],_4[1]);if(_1.cellIsSelected(_5))break;_5=null}
if(_5){this.$71d=_1.getPrimaryKeys(_5);if(this.valuesManager&&this.valuesManager.$707){this.valuesManager.$707(this)}else{this.editRecord(isc.addProperties({},_5))}}}
);isc.B._maxIndex=isc.C+2}
isc.$457={fetchData:function(_1,_2,_3){var _4=this.getDataSource();if(!_4){this.logWarn("Ignoring call to fetchData() on a DynamicForm with no valid dataSource");return}
if(this.$458==null)this.$458=[];this.$458.add(_2);_3=this.buildRequest(_3,"fetch");_4.fetchData(_1,{target:this,methodName:"fetchDataReply"},_3)},fetchDataReply:function(_1,_2,_3){if(_2==null||isc.isAn.emptyObject(_2)||(isc.isAn.Array(_2)&&_2.getLength()==0))
{if(_1.status==isc.RPCResponse.STATUS_OFFLINE){isc.say(this.offlineMessage)}}
var _4;if(isc.isAn.Array(_2)){_4=_2.get(0)}else{_4=_2}
if(_1.status==isc.RPCResponse.STATUS_SUCCESS||_1.status==isc.RPCResponse.STATUS_VALIDATION_ERROR)
{this.editRecord(_4)}
var _5=this.$458.pop();if(_5)this.fireCallback(_5,"dsResponse,data,dsRequest",[_1,_2,_3])},filterData:function(_1,_2,_3){var _4=this.getDataSource();if(!_4){this.logWarn("Ignoring call to filterData() on a DynamicForm with no valid dataSource");return}
if(this.$458==null)this.$458=[];this.$458.add(_2);_4.filterData(_1,{target:this,methodName:"fetchDataReply"},_3)}}
if(isc.DynamicForm)isc.DynamicForm.addMethods(isc.$457)
if(isc.ValuesManager)isc.ClassFactory.mixInInterface("ValuesManager","EditorActionMethods");if(isc.ValuesManager)isc.ValuesManager.addMethods(isc.$457)
if(isc.ValuesManager){isc.A=isc.ValuesManager.getPrototype();isc.A.fieldValuesAreEqual=isc.Canvas.getPrototype().fieldValuesAreEqual}
if(isc.TreeGrid){isc.A=isc.TreeGrid.getPrototype();isc.A.ignoreEmptyCriteria=true;isc.A=isc.TreeGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.useExistingDataModel=function isc_TreeGrid_useExistingDataModel(_1,_2,_3){return false}
,isc.A.createDataModel=function isc_TreeGrid_createDataModel(_1,_2,_3){return this.createResultTree(_1,_3.afterFlowCallback,_3,null)}
);isc.B._maxIndex=isc.C+2}
if(isc.DetailViewer){isc.A=isc.DetailViewer.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.viewSelectedData=function isc_DetailViewer_viewSelectedData(_1,_2,_3){if(isc.isA.String(_1))_1=window[_1];_3=_3||{};var _4=_1.selection.getSelection();if(_4&&_4.length>0){if(!_3.operation){this.setData(_4)}else{var _5=_3.operation,_6=this.getDataSource(),_7=_6.filterPrimaryKeyFields(_4);if(_3.prompt==null)
_3.prompt=isc.RPCManager.getViewRecordsPrompt;_3.viewer=this;return _6.performDSOperation(_5.type,_7,(_2?_2:{target:this,methodName:"viewSelectedDataReply"}),_3)}}
return false}
,isc.A.viewSelected=function isc_DetailViewer_viewSelected(_1,_2){return this.viewSelectedData(_1,_2)}
,isc.A.viewSelectedDataReply=function isc_DetailViewer_viewSelectedDataReply(_1,_2,_3){this.setData(_2)}
);isc.B._maxIndex=isc.C+3}
isc.defineClass("DataView",isc.VLayout);isc.A=isc.DataView.getPrototype();isc.A.autoLoadServices=true;isc.A.autoBindDBCs=true;isc.A=isc.Canvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.dataViewInit=function isc_Canvas_dataViewInit(){var _1=this.operations;if(_1==null){this.bindToServices();return}
this.operations.setProperty("dataView",this);if(!this.autoLoadServices)return;var _2=_1.getProperty("location").getUniqueItems();this.logInfo("loading services: "+_2);this.$72q=_2.length;var _3=this;for(var i=0;i<_2.length;i++){isc.xml.loadWSDL(_2[i],function(_6){_3.$72q--;_3.logInfo("service loaded: "+_6+", remaining: "+_3.$72q);if(window.avosConfig&&_6.dataURL&&_6.dataURL.contains("active-bpel/services/")&&_6.dataURL.contains("localhost"))
{var _5=_6.dataURL;_5=window.avosConfig.baseUrl+"/"+_5.substring(_5.indexOf("services/"));isc.logWarn("revising AV service location from: "+_6.dataURL+" to "+_5);_6.setLocation(_5)}
if(_3.$72q==0){_3.dataViewLoaded();_3.bindToServices()}},null,true)}}
,isc.A.addVM=function isc_Canvas_addVM(_1){this.addedVMs=this.addedVMs||[];this.addedVMs.add(_1)}
,isc.A.bindToServices=function isc_Canvas_bindToServices(){if(!this.autoBindDBCs)return;var _1=this.getAllDBCs(this);if(!_1)return;var _2=this.getAllVMs();if(this.logIsDebugEnabled("DataView")){this.logDebug("vms: "+this.echoAll(_2.getProperties(["dataSource","serviceNamespace","serviceName"]))+", all dbcs: "+this.echoAll(_1.getProperties(["dataSource","serviceNamespace","serviceName"])),"DataView")}
for(var i=0;i<_1.length;i++){var _4=_1[i];if(_4.dataSource){if(this.canEdit!=null&&_4.canEdit==null)_4.setCanEdit(this.canEdit);var _5=this.findVM(_4,_2);if(_5){if(this.logIsInfoEnabled("dataRegistration")){this.logWarn("dbc: "+_4+" binding to dataSource: "+_4.dataSource+" and vm: "+_5+", with fields: "+this.echoAll(_4.originalFields),"dataRegistration")}
if(_4.originalFields)_4.setDataSource(_4.dataSource,_4.originalFields);_5.addMember(_4)}else{this.logInfo("no VM for DBC: "+this.echoLeaf(_4),"DataView")}}
if(isc.isA.DynamicForm(_4)&&_4.items){_4.items.map("registerWithDataView",this)}}}
,isc.A.getAllVMs=function isc_Canvas_getAllVMs(){var _1=[];var _2=this.operations;if(_2){_1.addAll(_2.getProperty("inputVM"));_1.addAll(_2.getProperty("outputVM"))}
_1.addAll(this.addedVMs);_1.removeList([null]);return _1}
,isc.A.findVM=function isc_Canvas_findVM(_1,_2){if(!_2)_2=this.getAllVMs();var _3=(isc.isA.DataSource(_1)?_1:_1.getDataSource());for(var i=0;i<_2.length;i++){var _5=_2[i];if(_3==_5.getDataSource())return _5}}
,isc.A.getAllDBCs=function isc_Canvas_getAllDBCs(_1){var _2=_1.children;if(!_2)return null;var _3=[];for(var i=0;i<_2.length;i++){var _1=_2[i];if(isc.isA.DataBoundComponent(_1))_3.add(_1);_3.addAll(this.getAllDBCs(_1))}
return _3}
,isc.A.registerItem=function isc_Canvas_registerItem(_1){if(!_1.inputDataPath)return;var _2=isc.WebService.getByName(_1.inputServiceName,_1.inputServiceNamespace);if(!_2){this.logWarn("Member: "+_1+" could not find webService with name '"+_1.inputServiceName+"', namespace '"+_1.inputServiceNamespace+"'. Has it been loaded?");return}
var _3=_1.inputSchemaDataSource;var _4=this.itemRegistry=this.itemRegistry||{};var _5=_4[_3]=_4[_3]||[];_5.add(_1)}
,isc.A.populateListeners=function isc_Canvas_populateListeners(_1){var _2=_1.getDataSource().getID();var _3=this.itemRegistry;if(this.logIsInfoEnabled("DataView")){this.logInfo("message: "+_2+", registry: "+this.echo(_3)+", data: "+this.echo(_1.getValues()),"DataView")}
if(!_3)return;var _4=_3[_2];if(!_4)return;for(var i=0;i<_4.length;i++){var _6=_4[i];var _7=_1.getValue(_6.inputDataPath);this.logWarn("component: "+_6+" with inputDataPath: "+_6.inputDataPath+" got data: "+this.echo(_7));if(isc.isA.FormItem(_6)){_6.setValue(_7)}else{_6.setData(_7)}}}
,isc.A.dataViewLoaded=function isc_Canvas_dataViewLoaded(){}
);isc.B._maxIndex=isc.C+9;isc.DataView.registerStringMethods({dataViewLoaded:""});isc.defineClass("ServiceOperation").addClassMethods({getServiceOperation:function(_1,_2,_3){if(this.$725)return this.$725.find({operationName:_1,serviceName:_2,serviceNamespace:_3})}});isc.A=isc.ServiceOperation.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.init=function isc_ServiceOperation_init(){isc.ClassFactory.addGlobalID(this);this.Super("init",arguments);if(!isc.ServiceOperation.$725)isc.ServiceOperation.$725=[];isc.ServiceOperation.$725.add(this)}
,isc.A.invoke=function isc_ServiceOperation_invoke(){var _1=this.service=isc.WebService.getByName(this.serviceName,this.serviceNamespace);if(!_1){this.logWarn("Unable to find web service with serviceName '"+this.serviceName+"' and serviceNamespace '"+this.serviceNamespace+"'. Has it been "+"loaded?");return}
var _2=this.inputVM.getValues();if(_1.useSimplifiedInputs(this.operationName)){_2=_2[isc.firstKey(_2)]}
var _3=this;_1.callOperation(this.operationName,_2,null,function(_2,_4,_5,_6){_3.invocationCallback(_2,_4,_5,_6)})}
,isc.A.invocationCallback=function isc_ServiceOperation_invocationCallback(_1,_2,_3,_4){if(!this.outputVM)return;if(this.service.getSoapStyle(this.operationName)=="document"){var _5=this.outputVM.getDataSource().getFieldNames();if(_5.length==1){var _6=_5.first(),_7={};_7[_6]=_1;_1=_7}}
this.outputVM.setValues(_1);if(this.logIsInfoEnabled()){this.logInfo("populating listeners on dataView: "+this.dataView+", vm has values: "+this.echo(this.outputVM.getValues()))}
if(this.dataView)this.dataView.populateListeners(this.outputVM)}
);isc.B._maxIndex=isc.C+3;(function(){var Offline={explicitOffline:null,isOffline:function(){if(this.explicitOffline!==null)return this.explicitOffline;var offline=window.navigator.onLine?false:true;return offline},goOffline:function(){this.explicitOffline=true},goOnline:function(){this.explicitOffline=false},useNativeOfflineDetection:function(){this.explicitOffline=null},KEY_PREFIX:"isc-",LOCAL_STORAGE:"localStorage",GLOBAL_STORAGE:"globalStorage",DATABASE_STORAGE:"databaseStorage",GEARS_DATABASE_API:"gears database api",USERDATA_PERSISTENCE:"userData persistence",GOOGLE_GEARS:"google gears",NO_MECHANISM:"no discovered mechanism",maxResponsesToPersist:100,userDataPersistenceInIE8:false,localStorageType:function(){if(window.localStorage){if(!this.userDataPersistenceInIE8||!isc.Browser.isIE){return this.LOCAL_STORAGE}}
if(window.globalStorage)return this.GLOBAL_STORAGE;if(window.openDatabase)return this.DATABASE_STORAGE;if(isc.Browser.isIE&&isc.Browser.version>=5){if(!UserDataPersistence.isInitialized)UserDataPersistence.init();return this.USERDATA_PERSISTENCE}
return this.NO_MECHANISM},getNativeStoredValuesCount:function(){switch(this.localStorageType()){case this.LOCAL_STORAGE:return localStorage.length;break;case this.USERDATA_PERSISTENCE:return UserDataPersistence.getNativeStoredValuesCount();break;case this.GLOBAL_STORAGE:case this.DATABASE_STORAGE:case this.GEARS_DATABASE_API:case this.GOOGLE_GEARS:break}},getSCStoredValuesCount:function(){var entries=this.get(this.countKey);entries=entries?entries*1:0;return entries},getKeyForNativeIndex:function(index){switch(this.localStorageType()){case this.LOCAL_STORAGE:return localStorage.key(index);case this.USERDATA_PERSISTENCE:return UserDataPersistence.getKeyForNativeIndex(index);break;case this.GLOBAL_STORAGE:case this.DATABASE_STORAGE:case this.GEARS_DATABASE_API:case this.GOOGLE_GEARS:break}},clearCache:function(){var count=this.getSCStoredValuesCount();while(this.getSCStoredValuesCount()>0){this.removeOldestEntry();if(this.getSCStoredValuesCount()==count)break;count=this.getSCStoredValuesCount()}},clearCacheNatively:function(){if(this.localStorageType()==this.USERDATA_PERSISTENCE){UserDataPersistence.clearCacheNatively();return}
var count=this.getNativeStoredValuesCount();this.logDebug("Removing all "+count+" entries from local storage");for(var i=0;i<count;i++){this.$786(this.getKeyForNativeIndex(0),false)}},logCacheContents:function(maxEntryLength){var contents=this.getCacheContents();this.logDebug("Dumping the contents of the browser's local storage:");for(var key in contents){var value=contents[key];if(value&&value.length>maxEntryLength){value=value.substring(0,maxEntryLength)}
this.logDebug(key+" ===> "+value)}},getCacheContents:function(){var index=0,contents={},count=this.getSCStoredValuesCount();while(index<count){var key=this.getPriorityQueueKey(index);if(key.indexOf(this.KEY_PREFIX)==0)key=key.substring(this.KEY_PREFIX.length);contents[key]=this.get(key);index++}
return contents},getCacheKeys:function(){var index=0,keys=[],count=this.getSCStoredValuesCount();while(index<count){var obj=this.getPriorityQueueKey(index);keys[keys.length]=key;index++}
return keys},removeOldestEntry:function(){var key=this.getAndRemoveOldestFromPriorityQueue();if(key==null)return;this.remove(key,true)},$787:function(maxEntryLength){var doc=this.userDataSpan.xmlDocument;var maxEntryLength=100;this.logDebug("Dumping the contents of raw userData storage:");for(var i=0;i<this.userDataSpan.xmlDocument.firstChild.attributes.length;i++){var key=this.userDataSpan.xmlDocument.firstChild.attributes[i].name;var value=this.userDataSpan.getAttribute(key);if(value&&value.length>maxEntryLength){value=value.substring(0,maxEntryLength)+"..."}
this.logDebug(key+" ===> "+value)}},put:function(key,value,recycleEntries){var ts=new Date().getTime();var oldValue=this.get(key);while(true){try{this.$788(key,value);break}catch(e){if(recycleEntries!==false&&this.isStorageException(e)){var entries=this.getStorageMetrics().storedEntries;if(entries>0){this.logDebug("Cache full; removing oldest entry and trying again");this.removeOldestEntry()}else{this.logDebug("Can't add this entry to browser-local storage, even "+"though the cache is empty - the item must be larger "+"than the browser's permitted cache space.");break}}else{throw e}}}
var end=new Date().getTime();var pqOK=false,metricsOK=false;while(!pqOK||!metricsOK){try{if(!pqOK)this.addToPriorityQueue(key);pqOK=true;if(!metricsOK)this.updateMetrics("put",key,value,oldValue);metricsOK=true}catch(e){if(this.isStorageException(e)){if(recycleEntries!==false){var entries=this.getStorageMetrics().storedEntries;if(entries>0){this.logDebug("Cache full when updating priority queue or metrics; "+"removing oldest entry and trying again");this.removeOldestEntry();continue}}
this.logDebug("Cache full when updating priority queue or metrics; rolling "+"back the entire update");this.$786(key);if(pqOK)this.removeFromPriorityQueue(key);this.rebuildMetrics();throw e}else{throw e}}}
this.logDebug("put() with key: "+key+"\nitem: "+this.echoLeaf(value)+": "+(end-ts)+"ms. Maintaining the priority queue and metrics took "+"a further "+new Date().getTime()-end+"ms")},$788:function(key,value,applyPrefix){key=(applyPrefix===false?"":this.KEY_PREFIX)+key;switch(this.localStorageType()){case this.LOCAL_STORAGE:localStorage.setItem(key,value);break;case this.USERDATA_PERSISTENCE:UserDataPersistence.putValue(key,value);break;case this.GLOBAL_STORAGE:case this.DATABASE_STORAGE:case this.GEARS_DATABASE_API:case this.GOOGLE_GEARS:this.logError("Persistence method '"+this.localStorageType()+"' not yet supported");break}},isStorageException:function(e){switch(this.localStorageType()){case this.LOCAL_STORAGE:if(isc.Browser.isIE){return(e.number==-2147024882)}else if(isc.Browser.isMoz){return(e.name=="NS_ERROR_DOM_QUOTA_REACHED")}else{return(e.name=="QUOTA_EXCEEDED_ERR")}
break;case this.USERDATA_PERSISTENCE:return(e.number==-2147024857)}},get:function(key){var ts=new Date().getTime(),item;switch(this.localStorageType()){case this.LOCAL_STORAGE:item=localStorage.getItem(this.KEY_PREFIX+key);break;case this.USERDATA_PERSISTENCE:item=UserDataPersistence.getValue(this.KEY_PREFIX+key);break;case this.GLOBAL_STORAGE:case this.DATABASE_STORAGE:case this.GEARS_DATABASE_API:case this.GOOGLE_GEARS:break}
if(item)item=isc.clone(item);var end=new Date().getTime();this.logDebug("get() with key: "+key+"\nitem is: "+this.echoLeaf(item)+": "+(end-ts)+"ms");return item},remove:function(key,skipPriorityQueueUpdate){this.logDebug("Removing item for key: "+key);this.updateMetrics("remove",key);if(!skipPriorityQueueUpdate)this.removeFromPriorityQueue(key);this.$786(key)},$786:function(key,applyPrefix){key=(applyPrefix===false?"":this.KEY_PREFIX)+key;switch(this.localStorageType()){case this.LOCAL_STORAGE:localStorage.removeItem(key);break;case this.USERDATA_PERSISTENCE:UserDataPersistence.removeValue(key);break;case this.GLOBAL_STORAGE:case this.DATABASE_STORAGE:case this.GEARS_DATABASE_API:case this.GOOGLE_GEARS:break}},getUndecoratedKey:function(key){if(key&&key.startsWith(this.KEY_PREFIX)){key=key.substring(this.KEY_PREFIX.length)}
return key},priorityQueueKey:"pq",addToPriorityQueue:function(userKey){this.removeFromPriorityQueue(userKey);var key=this.toInternalKey(userKey);var pqText=this.get(this.priorityQueueKey);if(pqText){eval("var pq = "+pqText)}else{var pq=[]}
pq.push(key);this.$788(this.priorityQueueKey,this.serialize(pq))},removeFromPriorityQueue:function(userKey){var key=this.toInternalKey(userKey);var pqText=this.get(this.priorityQueueKey);if(pqText){eval("var pq = "+pqText)}else{var pq=[]}
for(var i=0;i<pq.length;i++){if(pq[i]==key){var leading=pq.slice(0,i);var trailing=pq.slice(i+1);pq=leading.concat(trailing);break}}
this.$788(this.priorityQueueKey,this.serialize(pq))},getAndRemoveOldestFromPriorityQueue:function(){var pqText=this.get(this.priorityQueueKey);if(pqText){eval("var pq = "+pqText)}else{var pq=[]}
var oldest=pq.shift();this.$788(this.priorityQueueKey,this.serialize(pq));return this.toUserKey(oldest)},getPriorityQueueEntry:function(index){var key=this.getPriorityQueueKey(index);var value=this.get(key);var entry={};entry[key]=value;return entry},getPriorityQueueValue:function(index){var key=this.getPriorityQueueKey(index);return this.get(key)},getPriorityQueueKey:function(index){var pqText=this.get(this.priorityQueueKey);if(pqText){eval("var pq = "+pqText)}else{var pq=[]}
return this.toUserKey(pq[index])},getPriorityQueue:function(){var pqText=this.get(this.priorityQueueKey);if(pqText){eval("var pq = "+pqText)}else{var pq=[]}
return pq},toInternalKey:function(userKey){if(this.localStorageType()==this.USERDATA_PERSISTENCE){return UserDataPersistence.getDataStoreKey(this.KEY_PREFIX+userKey)}
return userKey},toUserKey:function(internalKey){if(this.localStorageType()==this.USERDATA_PERSISTENCE){return this.getUndecoratedKey(UserDataPersistence.getUserKey(internalKey))}
return internalKey},countKey:"storedEntryCount__",keyKey:"storedKeyBytes__",valueKey:"storedValueBytes__",updateMetrics:function(mode,key,value,oldValue){var realKey=this.KEY_PREFIX+key,storedEntries=this.get(this.countKey)||0,storedKeyBytes=this.get(this.keyKey)||0,storedValueBytes=this.get(this.valueKey)||0;storedKeyBytes=1*storedKeyBytes;storedValueBytes=1*storedValueBytes;if(mode=="remove"){var item=this.get(key);storedEntries--;storedKeyBytes-=realKey.length;storedValueBytes-=item.length}else{if(oldValue==null){storedEntries++;storedKeyBytes+=realKey.length;storedValueBytes+=value.length}else{storedValueBytes+=value.length-oldValue.length}}
this.$788(this.countKey,storedEntries);this.$788(this.keyKey,storedKeyBytes);this.$788(this.valueKey,storedValueBytes)},rebuildMetrics:function(){var pq=this.getPriorityQueue(),entries=0,keyBytes=0,valueBytes=0;for(var i=0;i<pq.length;i++){var value=this.get(pq[i]);entries++;keyBytes+=pq[i].length;valueBytes+=value.length}
this.$788(this.countKey,entries);this.$788(this.keyKey,keyBytes);this.$788(this.valueKey,valueBytes)},getStorageMetrics:function(){var storedEntries=this.get(this.countKey)||0,storedKeyBytes=this.get(this.keyKey)||0,storedValueBytes=this.get(this.valueKey)||0,countLen=0,keyLen=0,valueLen=0;if(storedEntries)countLen=storedEntries.length;if(storedKeyBytes)keyLen=storedKeyBytes.length;if(storedValueBytes)valueLen=storedValueBytes.length;storedEntries=1*storedEntries;storedKeyBytes=1*storedKeyBytes;storedValueBytes=1*storedValueBytes;var pqText=this.get(this.priorityQueueKey);var overhead=this.countKey.length+this.keyKey.length+this.valueKey.length+countLen+keyLen+valueLen;var pqLength=pqText==null?0:pqText.length+(this.KEY_PREFIX+this.priorityQueueKey).length;return{storedEntries:storedEntries,storedKeyBytes:storedKeyBytes,storedValueBytes:storedValueBytes,metricsOverhead:overhead,priorityQueue:pqLength,total:storedKeyBytes+storedValueBytes+overhead+pqLength}},getTotalStorageUsed:function(){var metrics=this.getStorageMetrics();return metrics.storedKeyBytes+metrics.storedValueBytes+metrics.metricsOverhead+metrics.priorityQueue},storeResponse:function(dsRequest,dsResponse){var ts=new Date().getTime();dsResponse.offlineTimestamp=ts;var trimmedRequest=this.trimRequest(dsRequest),key=this.serialize(trimmedRequest),value=this.serialize(this.trimResponse(dsResponse));this.logDebug("storeResponse serializing: "+(new Date().getTime()-ts)+"ms");if(this.get(key)==null){if(this.getSCStoredValuesCount()>=this.maxResponsesToPersist){this.removeOldestEntry()}}
this.put(key,value)},trimRequest:function(dsRequest){var keyProps=["dataSource","operationType","operationId","textMatchStyle","values","sortBy","startRow","endRow","data"],trimmed={},undef;for(var i=0;i<keyProps.length;i++){if(dsRequest[keyProps[i]]!==undef){trimmed[keyProps[i]]=dsRequest[keyProps[i]]}}
return trimmed},trimResponse:function(dsResponse){var keyProps=["dataSource","startRow","endRow","totalRows","data","offlineTimestamp","status","errors","invalidateCache","cacheTimestamp"],trimmed={},undef;for(var i=0;i<keyProps.length;i++){if(dsResponse[keyProps[i]]!==undef){trimmed[keyProps[i]]=dsResponse[keyProps[i]]}}
return trimmed},getResponse:function(dsRequest){var trimmedRequest=this.trimRequest(dsRequest),key=this.serialize(trimmedRequest),value=this.get(key),returnValue;eval('returnValue = '+value);if(returnValue)returnValue.fromOfflineCache=true;return returnValue},serialize:function(obj){return isc.Comm.serialize(obj,false)},showStorageInfo:function(){if(!this.storageBrowser){if(isc.Offline.localStorageType()==isc.Offline.USERDATA_PERSISTENCE){isc.Timer.setTimeout(function(){isc.say("WARNING:  This browser uses an old storage mechanism that does not "+"permit arbitrary key/value pair storage.  This means we have to "+"store extra management data, with the upshot that the metrics reported "+"for 'priority queue' and 'overhead' are indicative, but not accurate")},0)}
this.metricsDF=isc.DynamicForm.create({width:"100%",numCols:6,fields:[{name:"storedEntries",title:"No. entries",disabled:true},{name:"storedKeyBytes",title:"Used by keys",disabled:true},{name:"storedValueBytes",title:"Used by values",disabled:true},{name:"priorityQueue",title:"Used by Priority Queue",disabled:true},{name:"metricsOverhead",title:"Metrics overhead",disabled:true},{name:"total",title:"Total Bytes",disabled:true}]});this.storageLG=isc.ListGrid.create({width:"100%",height:"*",canRemoveRecords:true,removeData:function(record){isc.ask("Remove this entry?",function(value){if(value){isc.Offline.remove(record.key);isc.Offline.refreshStorageInfo()}})},rowDoubleClick:function(record){isc.Offline.createStorageEditorWindow();isc.Offline.storageEditorWindow.show();isc.Offline.storageEditor.editRecord(record)},fields:[{name:"key",width:"25%",title:"Key"},{name:"value",title:"Value"}]});this.storageBrowser=isc.Window.create({autoCenter:true,canDragResize:true,width:Math.floor(isc.Page.getWidth()*0.5),height:Math.floor(isc.Page.getHeight()*0.5),title:"Offline Storage",items:[this.metricsDF,this.storageLG,isc.HLayout.create({width:"100%",height:1,members:[isc.LayoutSpacer.create({width:"*"}),isc.Button.create({title:"Add Entry",click:function(){isc.Offline.createStorageEditorWindow();isc.Offline.storageEditorWindow.show();isc.Offline.storageEditor.editNewRecord()}})]})]})}
this.storageBrowser.show();this.refreshStorageInfo()},createStorageEditorWindow:function(){if(!isc.Offline.storageEditorWindow){isc.Offline.storageEditor=isc.DynamicForm.create({fields:[{name:"key",title:"Key",editorType:"TextAreaItem",width:400},{name:"value",title:"Value",editorType:"TextAreaItem",width:400},{name:"saveButton",type:"button",title:"Save",click:function(){var form=isc.Offline.storageEditor;if(form.saveOperationType=="update"&&form.getValue("key")!=form.getOldValue("key"))
{isc.ask("Key has changed - this will create a new entry. "+"Do you want to retain the old entry as well? (if "+"you answer 'No', it will be removed",function(value){if(value===false){isc.Offline.remove(form.getOldValue("key"))}
if(value!=null){isc.Offline.put(form.getValue("key"),form.getValue("value"));isc.Offline.storageEditorWindow.hide();isc.Offline.refreshStorageInfo()}})}else{isc.Offline.put(form.getValue("key"),form.getValue("value"));isc.Offline.storageEditorWindow.hide();isc.Offline.refreshStorageInfo()}}}]});isc.Offline.storageEditorWindow=isc.Window.create({bodyProperties:{margin:5},title:"Edit Offline Storage Entry",isModal:true,autoCenter:true,height:280,width:480,items:[isc.Offline.storageEditor]})}},refreshStorageInfo:function(){this.metricsDF.editRecord(isc.Offline.getStorageMetrics());var dataObj=isc.Offline.getCacheContents();var data=[];for(var key in dataObj){data.add({key:key,value:dataObj[key]})}
this.storageLG.setData(data)}};var UserDataPersistence={isInitialized:false,poolSize:10,keyIndexKey:"keyIndex",reverseKeyIndexKey:"reverseKeyIndex",init:function(){this.userDataSpan=[];for(var i=0;i<this.poolSize;i++){this.userDataSpan[i]=document.createElement('span');this.userDataSpan[i].ID='isc_userData_'+i;this.userDataSpan[i].style.behavior='url(#default#userdata)';document.body.appendChild(this.userDataSpan[i]);this.userDataSpan[i].load("isc_userData_"+i)}
this.keyIndexStore=document.createElement('span');this.keyIndexStore.ID='isc_userData_keyIndex';this.keyIndexStore.style.behavior='url(#default#userdata)';document.body.appendChild(this.keyIndexStore);this.keyIndexStore.load("isc_userData_keyIndex");this.keyIndex=this.getKeyIndexFromStore();this.reverseKeyIndex=this.getReverseKeyIndexFromStore();if(!this.keyIndex){this.keyIndex={};this.reverseKeyIndex={}}else if(!this.reverseKeyIndex)this.buildReverseKeyIndex();this.buildNextAttributeInfo();this.isInitialized=true},clearCacheNatively:function(){for(var i=0;i<this.poolSize;i++){var attrs=this.userDataSpan[i].xmlDocument.firstChild.attributes;while(attrs.length>0){this.userDataSpan[i].removeAttribute(attrs[0].name)}}
this.keyIndex={};this.reverseKeyIndex={};this.saveKeyIndex()},getNativeStoredValuesCount:function(){var count=0;for(var i=0;i<this.poolSize;i++){count+=this.userDataSpan[i].xmlDocument.firstChild.attributes.length}
return count},getKeyForNativeIndex:function(index){var iCounter=0;for(var i=0;i<this.poolSize;i++){if(iCounter+this.userDataSpan[i].xmlDocument.firstChild.attributes.length>index){var offsetIndex=index-iCounter;var attrName=this.userDataSpan[i].xmlDocument.firstChild.attributes[offsetIndex].name,attrNum=attrName.substring(1),dsKey=this.getKeyIndexValue(i,attrNum);return this.getUserKey(dsKey)}}},getKeyIndexValue:function(index,attrName){var attrNum=attrName.substring(1);if(index==0){return"00000".substring(attrNum.length)+attrNum}
return index*10000+(1*attrNum)},getUserKey:function(userKey){return this.reverseKeyIndex[userKey]},getDataStoreKey:function(key){return this.keyIndex[key]},$70p:function(dataStore,attr){return this.userDataSpan[dataStore].getAttribute(attr)},getValue:function(userKey){var key=this.getDataStoreKey(userKey),undef;if(key===undef)return null;var dataStore=(""+key).substring(0,1),attr="v"+((""+key).substring(1)*1);return this.$70p(dataStore,attr)},putValue:function(userKey,value){var key=this.getDataStoreKey(userKey);if(key){var dataStore=(""+key).substring(0,1),attr="v"+((""+key).substring(1)*1),savedValue=this.$70p(dataStore,attr)}else{var dataStore=this.getDataStoreForNewItem(),attr=this.getNextAttributeName(dataStore)}
this.userDataSpan[dataStore].setAttribute(attr,value);try{this.userDataSpan[dataStore].save("isc_userData_"+dataStore);this.addToKeyIndex(userKey,dataStore,attr)}catch(e){if(isc.Offline.isStorageException(e)){if(savedValue){this.userDataSpan[dataStore].setAttribute(attr,savedValue)}else{this.userDataSpan[dataStore].removeAttribute(attr);this.removeFromKeyIndex(userKey)}}
throw e}},removeValue:function(userKey){var key=this.getDataStoreKey(userKey),undef;if(key===undef){Offline.logDebug("userData: in removeValue, no value for key '"+userKey+"' was found");return}
var dataStore=(""+key).substring(0,1),attr="v"+((""+key).substring(1)*1);this.userDataSpan[dataStore].removeAttribute(attr);this.userDataSpan[dataStore].save("isc_userData_"+dataStore);this.removeFromKeyIndex(userKey);this.unusedAttributeNumbers[dataStore].push(attr.substring(1)*1)},getDataStoreForNewItem:function(){var undef;if(this.nextDataStoreToUse===undef){this.nextDataStoreToUse=Math.floor(Math.random()*this.poolSize)}
var rtnValue=this.nextDataStoreToUse++;if(this.nextDataStoreToUse>=this.poolSize)this.nextDataStoreToUse=0;return rtnValue},buildNextAttributeInfo:function(){this.nextAttributeNumber=[];this.unusedAttributeNumbers=[];for(var i=0;i<this.poolSize;i++){this.unusedAttributeNumbers[i]=[];var attrs=this.userDataSpan[i].xmlDocument.firstChild.attributes;var work=[];for(var j=0;j<attrs.length;j++){var num=attrs[j].name.substring(1)*1;if(!isNaN(num))work.add(attrs[j].name.substring(1)*1)}
if(work.sort)work.sort();else this.sort(work);var counter=0;for(j=0;j<work.length;j++){if(work[j]==counter){counter++;continue}
while(work[j]!=counter&&counter<=9999){this.unusedAttributeNumbers[i].push(counter++)}
counter++}
this.nextAttributeNumber[i]=counter}},sort:function(array){for(var i=0;i<array.length;i++){var swapped=false;for(var j=1;j<array.length-i;j++){if(array[j]<array[j-1]){var temp=array[j];array[j]=array[j-1];array[j-1]=temp;swapped=true}}
if(!swapped)break}},getNextAttributeName:function(dataStore){if(this.unusedAttributeNumbers[dataStore]&&this.unusedAttributeNumbers[dataStore].length>0)
{return"v"+this.unusedAttributeNumbers[dataStore].shift()}
if(this.nextAttributeNumber[dataStore]==null){this.nextAttributeNumber[dataStore]=1}
return"v"+this.nextAttributeNumber[dataStore]++},addToKeyIndex:function(userKey,dataStore,attr){var keyIndexValue=this.getKeyIndexValue(dataStore,attr);this.keyIndex[userKey]=keyIndexValue;this.reverseKeyIndex[keyIndexValue]=userKey;this.saveKeyIndex()},removeFromKeyIndex:function(userKey){var keyIndexValue=this.keyIndex[userKey];delete this.keyIndex[userKey];delete this.reverseKeyIndex[keyIndexValue];this.saveKeyIndex()},saveKeyIndex:function(){this.keyIndexStore.setAttribute(this.keyIndexKey,Offline.serialize(this.keyIndex));this.keyIndexStore.setAttribute(this.reverseKeyIndexKey,Offline.serialize(this.reverseKeyIndex));this.keyIndexStore.save("isc_userData_keyIndex")},buildReverseKeyIndex:function(){this.reverseKeyIndex={};for(var key in this.keyIndex){this.reverseKeyIndex[keyIndex[key]]=key}},getKeyIndexFromStore:function(){var kiText=this.keyIndexStore.getAttribute(this.keyIndexKey);if(kiText){eval("var ki = "+kiText)}else{var ki=null}
return ki},getReverseKeyIndexFromStore:function(){var kiText=this.keyIndexStore.getAttribute(this.reverseKeyIndexKey);if(kiText){eval("var ki = "+kiText)}else{var ki=null}
return ki}};if(window.isc){isc.defineClass("Offline").addClassProperties(Offline);isc.defineClass("UserDataPersistence").addClassProperties(UserDataPersistence)}else{isc.addProperties=function(objOne,objTwo){for(var propName in objTwo)objOne[propName]=objTwo[propName]}
isc.addProperties(isc.Offline,{serialize:function(object){return isc.OfflineJSONEncoder.encode(object)},logDebug:function(message){if(console)console.log(message)},logError:function(message){if(console){console.log(message)}else{alert(message)}},echoLeaf:function(obj){var output="",undefined;if(obj===undefined)return"undef";try{if(typeof obj=="Array"){output+="Array["+obj.length+"]"}else if(typeof obj=="Date"){output+="Date("+obj.toShortDate()+")"}else if(typeof obj=="Function"){output+=isc.Func.getName(obj,true)+"()"}else{switch(typeof obj){case"string":if(obj.length<=40){output+='"'+obj+'"';break}
output+='"'+obj.substring(0,40)+'..."['+obj.length+']';output=output.replaceAll("\n","\\n").replaceAll("\r","\\r");break;case"object":if(obj==null){output+="null";break}
if(obj.tagName!=null){output+="["+obj.tagName+"Element]";break}
var toString=""+obj;if(toString!=""&&toString!="[object Object]"&&toString!="[object]")
{output+=toString;break}
output+="Obj";break;default:output+=""+obj}}
return output}catch(e){var message="[Error in echoLeaf: "+e+"]";output+=message;return output}},echo:function(object){return this.serialize(object)}});isc.OfflineJSONEncoder={$zm:function(objRefs,object,path){objRefs.obj.add(object);objRefs.path.add(path)},$42b:function(object){var treeId=object["$42c"];if(treeId!=null){var theTree=window[treeId];if(theTree&&theTree.parentProperty&&object[theTree.parentProperty]){object=theTree.getCleanNodeData(object)}}
return object},$zl:function(objRefs,object){var rowNum=objRefs.obj.indexOf(object);if(rowNum==-1)return null;return objRefs.path[rowNum]},$zp:function(objPath,newIdentifier){if(isc.isA.Number(newIdentifier)){return objPath+"["+newIdentifier+"]"}else if(!isc.Comm.$zk.test(newIdentifier)){return objPath+'["'+newIdentifier+'"]'}else{return objPath+"."+newIdentifier}},encode:function(object){this.objRefs={obj:[],path:[]};var retVal=this.$eu(object,this.prettyPrint?"":null,null);this.objRefs=null;return retVal},dateFormat:"xmlSchema",encodeDate:function(date){if(this.dateFormat=="dateConstructor"){return"new Date("+date.getTime()+")"}else{return'"'+this.toSchemaDate(date)+'"'}},toSchemaDate:function(date){var dd=""+date.getDate(),mm=""+(date.getMonth()+1),yyyy=""+(date.getyear()+1900),hh=""+date.getHours(),mi=""+date.getMinutes(),ss=""+date.getSeconds();dd=dd.length==1?"0"+dd:dd;mm=mm.length==1?"0"+mm:mm;hh=hh.length==1?"0"+hh:ff;mi=mi.length==1?"0"+mi:mi;ss=ss.length==1?"0"+ss:ss;return yyyy+"-"+mm+"-"+dd+"T"+hh+":"+mi+":"+ss},strictQuoting:true,circularReferenceMode:"path",circularReferenceMarker:"$$BACKREF$$",prettyPrint:false,$eu:function(object,prefix,objPath){if(!objPath){if(object&&object.getID)objPath=object.getID();else objPath=""}
if(object==null)return null;if(this.isAString(object))return this.asSource(object);if(this.isAFunction(object))return null;if(this.isANumber(object)||this.isASpecialNumber(object))return object;if(this.isABoolean(object))return object;if(this.isADate(object))return this.encodeDate(object);if(this.isAnInstance(object))return null;var prevPath=this.$zl(this.objRefs,object);if(prevPath!=null&&objPath.contains(prevPath)){var nextChar=objPath.substring(prevPath.length,prevPath.length+1);if(nextChar=="."||nextChar=="["||nextChar=="]"){var mode=this.circularReferenceMode;if(mode=="marker"){return"'"+this.circularReferenceMarker+"'"}else if(mode=="path"){return"'"+this.circularReferenceMarker+":"+prevPath+"'"}else{return null}}}
if(this.isAClassObject(object))return null;if(object==window)return null;this.$zm(this.objRefs,object,objPath);if(this.isAFunction(object.$eu)){return object.$eu(prefix,this.objRefs,objPath)}
if(this.isAnArray(object)){return this.$zn(object,objPath,this.objRefs,prefix)}
var data;if(object.getSerializeableFields){data=object.getSerializeableFields([],[])}else{data=object}
return this.$zo(data,objPath,this.objRefs,prefix)},$zn:function(object,objPath,objRefs,prefix){var output="[";for(var i=0,len=object.length;i<len;i++){var value=object[i];if(prefix!=null)output+="\r"+prefix;var objPath=this.$zp(objPath,i);var serializedValue=this.$eu(value,(prefix!=null?prefix:null),objPath);output+=serializedValue+",";if(prefix!=null)output+=" "}
var commaChar=output.lastIndexOf(",");if(commaChar>-1)output=output.substring(0,commaChar);if(prefix!=null)output+="\r"+prefix;output+="]";return output},$zo:function(object,objPath,objRefs,prefix){var output="{",undef;object=this.$42b(object);try{for(var key in object)break}catch(e){return null}
for(var key in object){if(key==null)continue;if(key=="xmlHttpRequest")continue;if(key.substring(0,1)=="_"||key.substring(0,1)=="$")continue;var value=object[key];if(this.isAFunction(value))continue;if(this.isAnInstance(value))continue;var keyStr=key.toString();keyStr='"'+keyStr+'"';var objPath=this.$zp(objPath,key);var serializedValue;if(key!="__ref"){serializedValue=this.$eu(value,(prefix!=null?prefix:null),objPath)}
if(prefix!=null)output+="\r"+prefix;output+=keyStr+":"+serializedValue+",";if(prefix!=null)output.append(" ")}
var commaChar=output.lastIndexOf(",");if(commaChar>-1)output=output.substring(0,commaChar);if(prefix!=null)output+="\r"+prefix;output+="}";return output},isAString:function(object){if(object==null)return false;if(typeof object==this.$a7)return false;if(object.constructor&&object.constructor.$k!=null){return object.constructor.$k==4}
if(object.Class!=null&&object.Class==this.$73x)return true;return typeof object=="string"},isAnArray:function(object){if(object==null)return false;if(typeof object==this.$a7)return false;if(object.constructor&&object.constructor.$k!=null){return object.constructor.$k==2}
if(isc.Browser.isSafari)return""+object.splice=="(Internal function)";return""+object.constructor==""+Array},isAFunction:function(object){if(object==null)return false;if(isc.Browser.isIE&&typeof object==this.$a7)return true;var cons=object.constructor;if(cons&&cons.$k!=null){if(cons.$k!=1)return false;if(cons===Function)return true}
return isc.Browser.isIE?(isc.emptyString+object.constructor==Function.toString()):(typeof object==this.$a7)},isANumber:function(object){if(object==null)return false;if(object.constructor&&object.constructor.$k!=null){if(object.constructor.$k!=5)return false}else{if(typeof object!="number")return false}
return!isNaN(object)&&object!=Number.POSITIVE_INFINITY&&object!=Number.NEGATIVE_INFINITY},isASpecialNumber:function(object){if(object==null)return false;if(object.constructor&&object.constructor.$k!=null){if(object.constructor.$k!=5)return false}else{if(typeof object!="number")return false}
return(isNaN(object)||object==Number.POSITIVE_INFINITY||object==Number.NEGATIVE_INFINITY)},isABoolean:function(object){if(object==null)return false;if(object.constructor&&object.constructor.$k!=null){return object.constructor.$k==6}
return typeof object=="boolean"},isADate:function(object){if(object==null)return false;if(object.constructor&&object.constructor.$k!=null){return object.constructor.$k==3}
return(""+object.constructor)==(""+Date)&&object.getDate&&isc.isA.Number(object.getDate())},isAnXMLNode:function(object){if(object==null)return false;if(isc.Browser.isIE){return object.specified!=null&&object.parsed!=null&&object.nodeType!=null&&object.hasChildNodes!=null}
var doc=object.ownerDocument;if(doc==null)return false;return doc.contentType==this.$bm},isAnInstance:function(object){return(object!=null&&object.$76y!=null)},isAClassObject:function(object){return(object!=null&&object.$bp==true)},asSource:function(string,singleQuote){if(!this.isAString(string))string=""+string;var quoteRegex=singleQuote?String.$ez:String.$e0,outerQuote=singleQuote?"'":'"';return outerQuote+string.replace(/\\/g,"\\\\").replace(quoteRegex,'\\'+outerQuote).replace(/\t/g,"\\t").replace(/\r/g,"\\r").replace(/\n/g,"\\n")+outerQuote}}}})();isc.defineClass("RulesEngine");isc.A=isc.RulesEngine.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.init=function isc_RulesEngine_init(){this.Super("init",arguments);isc.ClassFactory.addGlobalID(this);if(this.members==null)this.members=[];else{for(var i=0;i<this.members.length;i++){var _2=this.members[i];if(isc.isA.String(_2)){this.members[i]=_2=window[_2]}
this.$889(_2,true)}}}
,isc.A.addMember=function isc_RulesEngine_addMember(_1){if(!this.members.contains(_1)){this.members.add(_1);this.$889(_1)}}
,isc.A.$889=function isc_RulesEngine__addMember(_1){if(_1.rulesEngine!=this){if(_1.rulesEngine!=null){_1.rulesEngine.removeMember(_1)}
_1.rulesEngine=this}}
,isc.A.processEditStart=function isc_RulesEngine_processEditStart(_1){this.$89b("editStart",_1)}
,isc.A.processEditorEnter=function isc_RulesEngine_processEditorEnter(_1,_2){this.$89c("editorEnter",_1,_2)}
,isc.A.processChanged=function isc_RulesEngine_processChanged(_1,_2){this.$89c("changed",_1,_2)}
,isc.A.processEditorExit=function isc_RulesEngine_processEditorExit(_1,_2){this.$89c("editorExit",_1,_2)}
,isc.A.processSubmit=function isc_RulesEngine_processSubmit(_1){return this.$89b("submit",_1)}
,isc.A.$89b=function isc_RulesEngine__processComponentTriggerEvent(_1,_2){var _3=this.rulesData;if(!_3||_3.length==0)return;var _4=[];for(var i=0;i<_3.length;i++){if(_3[i].triggerEvent==_1){_4[_4.length]=_3[i]}}
if(_4.length>0)return this.processRules(_4);return null}
,isc.A.$89c=function isc_RulesEngine__processFieldTriggerEvent(_1,_2,_3){var _4=this.rulesData;if(!_4||_4.length==0)return;var _5=[];for(var i=0;i<_4.length;i++){if(_4[i].triggerEvent==_1){var _7;if(_4[i].type=="populate"){_7=[];for(var _8 in _4[i].formulaVars){_7.add(_4[i].formulaVars[_8])}}else{_7=_4[i].dependentFields||_4[i].fieldName;if(!isc.isAn.Array(_7))_7=_7.split(",")}
for(var j=0;j<_7.length;j++){var _10=_7[j].indexOf("."),_11=_7[j].substring(0,_10),_12=_7[j].substring(_10+1);if(_2.getDataSource()==isc.DataSource.get(_11)&&_12==_3.name)
{_5.add(_4[i]);break}}}}
if(_5.length>0)return this.processRules(_5);return null}
,isc.A.getValues=function isc_RulesEngine_getValues(){var _1={};for(var i=0;i<this.members.length;i++){var _3=this.members[i];var _4=_3.getValues(),_5=_3.getDataSource(),_6=_5?_5.getID():null;if(_6!=null){_1[_6]=isc.addProperties(_1[_6]||{},_4)}else{_1.addProperties(_4)}}
return _1}
,isc.A.processRules=function isc_RulesEngine_processRules(_1){var _2=this.getValues(),_3=null;for(var i=0;i<_1.length;i++){var _5=_1[i],_6=_5.fieldName;if(isc.isA.String(_6)){_6=_6.split(",")}
for(var j=0;j<_6.length;j++){var _8=_6[j],_9=_8.substring(0,_8.indexOf(".")),_10=_9?isc.DataSource.get(_9):null,_11=_8.substring(_9.length+1),_12=isc.DataSource.getPathValue(_2,_8),_13=this.getComponentInfo(_8),_14=_13?_13.component:null,_15=_13?_13.item:(_10?_10.getField(_11):null);;if(_15==null){this.logWarn("RulesEngine contains rule definition with specified fieldName:"+_8+" - unable to find associated "+(_14==null?"member component":"field")+" for this rule.");continue}
if(_5.applyWhen){var _16=_5.applyWhen;if(_10==null){isc.logWarn("Conditional rule criteria ignored because form has no dataSource")}else{var _17=_10.applyFilter([_2],_16);if(_17.length==0){isc.Validator.performAction(null,_15,_5,_2,_14);continue}}}
var _18=(isc.Validator.processValidator(_15,_5,_12,null,_2)==true);isc.Validator.performAction(_18,_15,_5,_2,_14);if(!_18){_3=false;_14.addFieldErrors(_15.name,isc.Validator.getErrorMessage(_5),true)}else{if(_3==null)_3=true}}}
return _3}
,isc.A.getComponentInfo=function isc_RulesEngine_getComponentInfo(_1){var _2,_3=_1.indexOf("."),_4;if(_3!=-1){_4=isc.DataSource.get(_1.substring(0,_3));_1=_1.substring(_3+1)}
for(var i=0;i<this.members.length;i++){if(this.members[i].getDataSource()==_4){_2=this.members[i].getItem(_1);if(_2!=null){return{component:this.members[i],item:_2}}}}}
,isc.A.removeMember=function isc_RulesEngine_removeMember(_1){if(this.members.contains(_1)){this.$89a(_1)}}
,isc.A.$89a=function isc_RulesEngine__removeMember(_1){this.members.remove(_1);_1.rulesEngine=null}
,isc.A.setRulesData=function isc_RulesEngine_setRulesData(_1){this.rulesData=_1}
);isc.B._maxIndex=isc.C+16;isc.A=isc.Canvas;isc.A.resizeThumbConstructor=isc.Canvas;isc.A.resizeThumbDefaults={width:8,height:8,overflow:"hidden",styleName:"resizeThumb",canDrag:true,canDragResize:true,getEventEdge:function(){return this.edge},autoDraw:false};isc.A=isc.Canvas;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.$40f=function isc_c_Canvas__makeResizeThumbs(){var _1=isc.Canvas.getInstanceProperty("edgeCursorMap"),_2={},_3=isc.ClassFactory.getClass(this.resizeThumbConstructor);for(var _4 in _1){_2[_4]=_3.create({ID:"isc_resizeThumb_"+_4,edge:_4},this.resizeThumbDefaults,this.resizeThumbProperties)}
isc.Canvas.$40g=_2}
,isc.A.showResizeThumbs=function isc_c_Canvas_showResizeThumbs(_1){if(!_1)return;if(!isc.Canvas.$40g)isc.Canvas.$40f();var _2=isc.Canvas.resizeThumbDefaults.width,_3=isc.Canvas.$40g;var _4=_1.getPageRect(),_5=_4[0],_6=_4[1],_7=_4[2],_8=_4[3],_9=Math.floor(_5+(_7/ 2)-(_2/ 2)),_10=Math.floor(_6+(_8/ 2)-(_2/ 2));_3.T.moveTo(_9,_6-_2);_3.B.moveTo(_9,_6+_8);_3.L.moveTo(_5-_2,_10);_3.R.moveTo(_5+_7,_10);_3.TL.moveTo(_5-_2,_6-_2);_3.TR.moveTo(_5+_7,_6-_2);_3.BL.moveTo(_5-_2,_6+_8);_3.BR.moveTo(_5+_7,_6+_8);for(var _11 in _3){var _12=_3[_11];_12.dragTarget=_1;_12.bringToFront();_12.show()}
this.$rl=_1}
,isc.A.hideResizeThumbs=function isc_c_Canvas_hideResizeThumbs(){var _1=this.$40g;for(var _2 in _1){_1[_2].hide()}
this.$rl=null}
);isc.B._maxIndex=isc.C+3;isc.A=isc.Canvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.editMaskDefaults={draw:function(){this.Super("draw",arguments);this.observe(this.masterElement,"setZIndex","observer.moveAbove(observed)");isc.Canvas.showResizeThumbs(this);this.observe(this.masterElement,"setPrompt","observer.setPrompt(observed.prompt)");return this},parentVisibilityChanged:function(){this.Super("parentVisibilityChanged",arguments);if(isc.Canvas.$rl==this)isc.Canvas.hideResizeThumbs()},click:function(){isc.Canvas.showResizeThumbs(this);return isc.EH.STOP_BUBBLING},bringToFront:function(){},mouseDown:function(){this.Super("mouseDown",arguments);return isc.EH.STOP_BUBBLING},mouseUp:function(){this.Super("mouseUp",arguments);return isc.EH.STOP_BUBBLING},doubleClick:function(){this.$jr.bringToFront();return this.click()},canDrag:true,canDragReposition:true,setDragTracker:function(){return isc.EH.STOP_BUBBLING},moved:function(){this.Super("moved",arguments);var _1=this.masterElement;if(_1){var _2=this.getOffsetLeft()-_1.getLeft();var _3=this.getOffsetTop()-_1.getTop();this.$ns=false;_1.moveTo(this.getOffsetLeft(),this.getOffsetTop());this.$ns=true}
if(isc.Canvas.$rl==this)isc.Canvas.showResizeThumbs(this)},resized:function(){this.Super("resized",arguments);if(this.$40h)return;this.$40h=true;var _1=this.masterElement;if(_1){this.$jo=false;_1.resizeTo(this.getWidth(),this.getHeight());this.$jo=true;_1.redrawIfDirty();this.resizeTo(_1.getVisibleWidth(),_1.getVisibleHeight())}
isc.Canvas.showResizeThumbs(this);this.$40h=false},showContextMenu:function(){if(!this.editContext)return;var _1=this.masterElement,_2;if(this.editContext.selectedComponents.length>0){_2=(_1.editMultiMenuItems||[]).concat(this.multiSelectionMenuItems)}else{_2=(_1.editMenuItems||[]).concat(this.standardMenuItems)}
if(!this.contextMenu)this.contextMenu=this.getMenuConstructor().create({});this.contextMenu.setData(_2);this.contextMenu.showContextMenu(_1);return false},standardMenuItems:[{title:"Remove",click:"target.destroy()"},{title:"Bring to Front",click:"target.bringToFront()"},{title:"Send to Back",click:"target.sendToBack()"}],multiSelectionMenuItems:[{title:"Remove Selected Items",click:"target.editContext.removeSelection(target)"}]};isc.A.useEditMask=true;isc.A.dropMargin=15;isc.B.push(isc.A.addedToEditContext=function isc_Canvas_addedToEditContext(_1,_2,_3,_4){}
,isc.A.wrapChildNode=function isc_Canvas_wrapChildNode(_1,_2,_3,_4){return _3}
,isc.A.setEditMode=function isc_Canvas_setEditMode(_1,_2,_3){if(_1==null)_1=true;if(this.editingOn==_1)return;this.editingOn=_1;if(this.editingOn){this.editContext=_2}else{this.hideEditMask()}
this.editNode=_3;if(this.editingOn){this.saveToOriginalValues(["click","doubleClick","willAcceptDrop","clearNoDropIndicator","setNoDropCursor","canAcceptDrop","canDropComponents","drop","dropMove","dropOver","setDataSource"]);this.setProperties({click:this.editModeClick,doubleClick:this.editModeDoubleClick,willAcceptDrop:this.editModeWillAcceptDrop,clearNoDropIndicator:this.editModeClearNoDropIndicator,setNoDropIndicator:this.editModeSetNoDropIndicator,canAcceptDrop:true,canDropComponents:true,drop:this.editModeDrop,dropMove:this.editModeDropMove,dropOver:this.editModeDropOver,baseSetDataSource:this.setDataSource,setDataSource:this.editModeSetDataSource})}else{this.restoreFromOriginalValues(["click","doubleClick","willAcceptDrop","clearNoDropIndicator","setNoDropCursor","canAcceptDrop","canDropComponents","drop","dropMove","dropOver","setDataSource"])}
this.markForRedraw()}
,isc.A.showEditMask=function isc_Canvas_showEditMask(){var _1=this.getID()+":<br>"+this.src;if(!this.$40i){var _2={};if(isc.SVG&&isc.isA.SVG(this)&&isc.Browser.isIE){isc.addProperties(_2,{backgroundColor:"gray",mouseOut:function(){this.$jr.Super("$mc")},contents:isc.Canvas.spacerHTML(10000,10000,_1)})}
var _3=isc.addProperties({},this.editMaskDefaults,this.editMaskProperties,{editContext:this.editContext||this.parentElement,keepInParentRect:this.keepInParentRect},_2);this.$40i=isc.EH.makeEventMask(this,_3)}
this.$40i.show();if(isc.SVG&&isc.isA.SVG(this)){if(isc.Browser.isIE)this.showNativeMask();else{this.setBackgroundColor("gray");this.setContents(_1)}}}
,isc.A.hideEditMask=function isc_Canvas_hideEditMask(){if(this.$40i)this.$40i.hide()}
,isc.A.editModeClick=function isc_Canvas_editModeClick(){if(this.editNode){isc.EditContext.selectCanvasOrFormItem(this,true);return isc.EH.STOP_BUBBLING}}
,isc.A.editModeDoubleClick=function isc_Canvas_editModeDoubleClick(){}
,isc.A.editModeWillAcceptDrop=function isc_Canvas_editModeWillAcceptDrop(_1){this.logInfo("editModeWillAcceptDrop for "+this.ID,"editModeDragTarget");var _2=this.ns.EH.dragTarget.getDragData(),_3,_4=true;if(_2==null||(isc.isAn.Array(_2))&&_2.length==0){_4=false;this.logInfo("dragData is null - using the dragTarget itself","editModeDragTarget");_2=this.ns.EH.dragTarget;if(isc.isA.FormItemProxyCanvas(_2)){this.logInfo("The dragTarget is a FormItemProxyCanvas for "+_2.formItem,"editModeDragTarget");_2=_2.formItem}
_3=_2._constructor||_2.Class}else{if(isc.isAn.Array(_2))_2=_2[0];_3=_2.className||_2.type}
this.logInfo("Using dragType "+_3,"editModeDragTarget");if(!this.canAdd(_3)){this.logInfo(this.ID+" does not accept drop of type "+_3,"editModeDragTarget");var _5=this.parentElement;while(_5&&!_5.editorRoot){if(_5.editingOn){var _6=_5.editModeWillAcceptDrop();if(!_6){this.logInfo("No ancestor accepts drop","editModeDragTarget");if(_1!=false){if(_4)isc.EditContext.hideDragHandle();isc.SelectionOutline.hideOutline();this.setNoDropIndicator()}
return false}
this.logInfo("An ancestor accepts drop","editModeDragTarget");return true}
_5=_5.parentElement}
this.logInfo(this.ID+" has no parentElement in editMode","editModeDragTarget");if(_1!=false){if(_4)isc.EditContext.hideDragHandle();isc.SelectionOutline.hideOutline();this.setNoDropIndicator()}
return false}
this.logInfo(this.ID+" is accepting the "+_3+" drop","editModeDragTarget");var _7=this.findEditNode(_3);if(_7){if(_1!=false){this.logInfo(this.ID+": selecting editNode object "+_7.ID);if(_4)isc.EditContext.hideDragHandle();isc.SelectionOutline.select(_7,false);_7.clearNoDropIndicator()}
return true}else{this.logInfo("findEditNode() returned null for "+this.ID,"editModeDragTarget")}
if(_1!=false){this.logInfo("In editModeWillAcceptDrop, '"+this.ID+"' was willing to accept a '"+_3+"' drop but we could not find an ancestor with an editNode")}}
,isc.A.findEditNode=function isc_Canvas_findEditNode(_1){if(!this.editNode){this.logInfo("Skipping '"+this+"' - has no editNode","editModeDragTarget");if(this.parentElement&&this.parentElement.findEditNode){return this.parentElement.findEditNode(_1)}else{return null}}
return this}
,isc.A.canAdd=function isc_Canvas_canAdd(_1){if(this.getObjectField(_1)==null){var _2=isc.ClassFactory.getClass(_1);if(isc.isA.FormItem(_2)){return(this.getObjectField("Canvas")!=null)}else{return false}}else{return true}}
,isc.A.editModeClearNoDropIndicator=function isc_Canvas_editModeClearNoDropIndicator(_1){if(this.$uh)delete this.$uh;this.$k2()}
,isc.A.editModeSetNoDropIndicator=function isc_Canvas_editModeSetNoDropIndicator(){this.$uh=true;this.$v8(this.noDropCursor)}
,isc.A.shouldPassDropThrough=function isc_Canvas_shouldPassDropThrough(){var _1=isc.EH.dragTarget,_2,_3;if(!_1.isA("Palette")){_3=_1.isA("FormItemProxyCanvas")?_1.formItem.Class:_1.Class}else{_2=_1.getDragData();if(isc.isAn.Array(_2))_2=_2[0];_3=_2.className||_2.type}
this.logInfo("Dropping a "+_3,"formItemDragDrop");if(isc.isA.TabBar(this)){if(_3!="Tab"){return true}
return false}
if(!this.canAdd(_3)){this.logInfo("This canvas cannot accept a drop of a "+_3,"formItemDragDrop");return true}
if(this.parentElement&&!this.parentElement.editModeWillAcceptDrop(false)){this.logInfo(this.ID+" is not passing drop through - no ancestor is willing to "+"accept the drop","editModeDragTarget");return false}
var x=isc.EH.getX(),y=isc.EH.getY(),_6=this.getPageRect(),_7={left:_6[0],top:_6[1],right:_6[0]+_6[2],bottom:_6[1]+_6[3]}
if(!this.orientation||this.orientation=="vertical"){if(x<_7.left+this.dropMargin||x>_7.right-this.dropMargin){this.logInfo("Close to right or left edge - passing drop through to parent for "+this.ID,"editModeDragTarget");return true}}
if(!this.orientation||this.orientation=="horizontal"){if(y<_7.top+this.dropMargin||y>_7.bottom-this.dropMargin){this.logInfo("Close to top or bottom edge - passing drop through to parent for "+this.ID,"editModeDragTarget");return true}}
this.logInfo(this.ID+" is not passing drop through","editModeDragTarget");return false}
,isc.A.editModeDrop=function isc_Canvas_editModeDrop(){if(this.shouldPassDropThrough()){return}
var _1=isc.EH.dragTarget,_2,_3;if(!_1.isA("Palette")){if(_1.isA("FormItemProxyCanvas")){_1=_1.formItem}
_3=_1._constructor||_1.Class}else{_2=_1.transferDragData();if(isc.isAn.Array(_2))_2=_2[0];_2.dropped=true;_3=_2.className||_2.type}
if(!_1.isA("Palette")){if(isc.EditContext.$70r)isc.EditContext.$70r.hide();if(_1==this)return;var _4=this.editContext.data,_5=_4.getParent(_1.editNode);this.editContext.removeComponent(_1.editNode);var _6;if(_1.isA("FormItem")){if(_1.isA("CanvasItem")){_6=this.editContext.addNode(_1.canvas.editNode,this.editNode)}else{_6=this.editContext.addWithWrapper(_1.editNode,this.editNode)}}else{_6=this.editContext.addNode(_1.editNode,this.editNode)}
if(_6&&_6.liveObject){isc.EditContext.selectCanvasOrFormItem(_6.liveObject,true)}}else{if(_2.loadData&&!_2.isLoaded){var _7=this;_2.loadData(_2,function(_8){_8=_8||_2;_8.isLoaded=true;_7.completeItemDrop(_8)
_8.dropped=_2.dropped});return isc.EH.STOP_BUBBLING}
this.completeItemDrop(_2);return isc.EH.STOP_BUBBLING}}
,isc.A.completeItemDrop=function isc_Canvas_completeItemDrop(_1){if(!this.editContext)return;var _2=_1.className||_1.type;var _3=isc.ClassFactory.getClass(_2);if(_3&&isc.isA.FormItem(_3)){this.editContext.addWithWrapper(_1,this.editNode)}else{this.editContext.addComponent(_1,this.editNode)}}
,isc.A.editModeDropMove=function isc_Canvas_editModeDropMove(){if(!this.editModeWillAcceptDrop())return false;if(!this.shouldPassDropThrough()){this.Super("dropMove",arguments);if(this.parentElement&&this.parentElement.hideDropLine){this.parentElement.hideDropLine();if(isc.isA.FormItem(this.parentElement)){this.parentElement.form.hideDragLine()}}
return isc.EH.STOP_BUBBLING}}
,isc.A.editModeDropOver=function isc_Canvas_editModeDropOver(){if(!this.editModeWillAcceptDrop())return false;if(!this.shouldPassDropThrough()){this.Super("dropOver",arguments);if(this.parentElement&&this.parentElement.hideDropLine){this.parentElement.hideDropLine();if(isc.isA.FormItem(this.parentElement)){this.parentElement.form.hideDragLine()}}
return isc.EH.STOP_BUBBLING}}
,isc.A.editModeSetDataSource=function isc_Canvas_editModeSetDataSource(_1,_2,_3){if(isc.$75u){this.baseSetDataSource(_1,_2);return}
if(_1==null)return;if(_1==this.dataSource&&!_3)return;var _2=this.getFields(),_4=[],_5=[];if(_2){for(var i=0;i<_2.length;i++){var _7=_2[i];if(_7.editNode&&_7.editNode.autoGen&&!this.fieldEdited(_7)){_5.add(_7)}else{_4.add(_7)}}
this.setFields(_4);for(var i=0;i<_5.length;i++){this.editContext.removeComponent(_5[i].editNode,true)}}
var _8,_2=_1.fields;if(_2&&isc.getKeys(_2).length==1&&_1.fieldIsComplexType(_2[isc.firstKey(_2)].name))
{_8=_1.getSchema(_2[isc.firstKey(_2)].type)}else{_8=_1}
var _9=_8.getFields();_2={};for(var _10 in _9){var _7=_9[_10];if(!this.shouldUseField(_7,_1))continue;_2[_10]=_9[_10];_2[_10]=isc.addProperties({},_9[_10])}
_4.addList(isc.getValues(_2));this.baseSetDataSource(_1,_4);for(var _10 in _2){var _7=_2[_10];var _11=this.getFieldEditNode(_7,_8);var _12=this.editContext.makeEditNode(_11);this.editContext.addNode(_12,this.editNode,null,null,true)}}
,isc.A.fieldEdited=function isc_Canvas_fieldEdited(_1){return _1.editNode.$71u}
,isc.A.getFieldEditNode=function isc_Canvas_getFieldEditNode(_1,_2){var _3=this.Class+"Field";var _4={type:_3,autoGen:true,defaults:{name:_1.name,title:_1.title||_2.getAutoTitle(_1.name)}}
return _4}
);isc.B._maxIndex=isc.C+20;isc.A=isc.Class.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.getSchema=function isc_Class_getSchema(){return isc.DS.get(this.schemaName||this.Class)}
,isc.A.getSchemaField=function isc_Class_getSchemaField(_1){return this.getSchema().getField(_1)}
,isc.A.getObjectField=function isc_Class_getObjectField(_1){var _2=this.$40j;if(isc.isA.Canvas(this)){var _3;if(_2&&_2[_1]!==_3){return _2[_1]}}
var _4=this.getSchema();if(!_4){this.logWarn("getObjectField: no schema exists for: "+this);return}
var _5=_4.getObjectField(_1);if(isc.isA.Canvas(this)){if(!_2)this.$40j=_2={};_2[_1]=_5}
return _5}
,isc.A.addChildObject=function isc_Class_addChildObject(_1,_2,_3,_4){return this.$40k("add",_1,_2,_3,_4)}
,isc.A.removeChildObject=function isc_Class_removeChildObject(_1,_2,_3){return this.$40k("remove",_1,_2,_3)}
,isc.A.$40k=function isc_Class__doVerbToChild(_1,_2,_3,_4,_5){var _6=_5||this.getObjectField(_2);var _7=this.getSchemaField(_6);if(!_7.multiple){var _8={};if(_1=="remove"){_8[_6]=null}else{_8[_6]=_3}
this.logInfo(_1+"ChildObject calling setProperties for fieldName '"+_6+"'","editing");this.setProperties(_8);return true}
var _9=this.getFieldMethod(_2,_6,_1);if(_9!=null){this.logInfo("calling "+_9+"("+this.echoLeaf(_3)+(_4!=null?","+_4+")":")"),"editing");this[_9](_3,_4);return true}
return false}
,isc.A.getChildObject=function isc_Class_getChildObject(_1,_2,_3){var _4=_3||this.getObjectField(_1),_5=this.getSchemaField(_4);if(_5==null){if(_3){this.logWarn("getChildObject: no such field '"+_3+"' in schema: "+this.getSchema())}else{this.logWarn("getChildObject: schema for Class '"+this.Class+"' does not have a field accepting type: "+_1)}
return null}
if(!_5.multiple)return this.getProperty(_4);var _6;if(isc.isA.ListGrid(this)&&_4=="fields"){_6="getSpecifiedField"}else{_6=this.getFieldMethod(_1,_4,"get")}
if(_6==null)var _6=this.getFieldMethod(_1,_4,"get");if(_6&&this[_6]){this.logInfo("getChildObject calling: "+_6+"('"+_2+"')","editing");return this[_6](_2)}else{this.logInfo("getChildObject calling getArrayItem('"+_2+"',this."+_4+")","editing");return isc.Class.getArrayItem(_2,this[_4])}}
,isc.A.getFieldMethod=function isc_Class_getFieldMethod(_1,_2,_3){var _4=_3+_1;if(isc.isA.Function(this[_4])&&isc.Func.getArgs(this[_4]).length>0)
{return _4}
if(_2.endsWith("s")){_4=_3+_2.slice(0,-1).toInitialCaps();if(isc.isA.Function(this[_4])&&isc.Func.getArgs(this[_4]).length>0)
{return _4}}}
,isc.A.getEditableProperties=function isc_Class_getEditableProperties(_1){var _2={},_3;if(!this.editModeOriginalValues)this.editModeOriginalValues={};if(!isc.isAn.Array(_1))_1=[_1];for(var i=0;i<_1.length;i++){var _5=isc.isAn.Object(_1[i])?_1[i].name:_1[i];var _6=null;if(this.editModeOriginalValues[_5]===_3){this.logInfo("Field "+_5+" - value ["+this[_5]+"] is "+"coming from live values","editModeOriginalValues");_6=this[_5];if(isc.isA.Function(_6)&&_6.$dw){_6=this[_6.$c1]}}else{this.logInfo("Field "+_5+" - value ["+this.editModeOriginalValues[_5]+"] is coming from "+"original values","editModeOriginalValues");_6=this.editModeOriginalValues[_5]}
_2[_5]=_6}
return _2}
,isc.A.setEditableProperties=function isc_Class_setEditableProperties(_1){var _2;if(!this.editModeOriginalValues)this.editModeOriginalValues={};for(var _3 in _1){if(this.editModeOriginalValues[_3]===_2){this.logInfo("Field "+_3+" - value is going to live values","editModeOriginalValues");this.setProperty(_3,_1[_3])}else{this.logInfo("Field "+_3+" - value is going to original values","editModeOriginalValues");this.editModeOriginalValues[_3]=_1[_3]}}
this.editablePropertiesUpdated()}
,isc.A.setChildEditableProperties=function isc_Class_setChildEditableProperties(_1,_2,_3,_4){isc.addProperties(_1,_2)}
,isc.A.saveToOriginalValues=function isc_Class_saveToOriginalValues(_1){var _2;if(!this.editModeOriginalValues)this.editModeOriginalValues={};for(var i=0;i<_1.length;i++){var _4=isc.isAn.Object(_1[i])?_1[i].name:_1[i];if(this[_4]===_2){this.editModeOriginalValues[_4]=null}else{if(this[_4]&&this[_4].$dw){var _5=isc.$ah+_4;this.editModeOriginalValues[_4]=this[_5]}else{this.editModeOriginalValues[_4]=this[_4]}}}}
,isc.A.restoreFromOriginalValues=function isc_Class_restoreFromOriginalValues(_1){var _2;if(!this.editModeOriginalValues)this.editModeOriginalValues={};var _3="Retrieving fields from original values:"
var _4={};for(var i=0;i<_1.length;i++){var _6=isc.isAn.Object(_1[i])?_1[i].name:_1[i];if(this.editModeOriginalValues[_6]!==_2){_4[_6]=this.editModeOriginalValues[_6];delete this.editModeOriginalValues[_6]}else{}}
this.setProperties(_4)}
,isc.A.propertyHasBeenEdited=function isc_Class_propertyHasBeenEdited(_1){var _2;if(!this.editModeOriginalValues)return false;if(isc.isAn.Object(_1))_1=_1.name;if(this.editModeOriginalValues[_1]!==_2){if(isc.isA.Function(this.editModeOriginalValues[_1]))return false;if(this.editModeOriginalValues[_1]!=this[_1])return true}
return false}
,isc.A.editablePropertiesUpdated=function isc_Class_editablePropertiesUpdated(){if(this.parentElement)this.parentElement.editablePropertiesUpdated()}
);isc.B._maxIndex=isc.C+15;isc.A=isc.DataSource;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.getSchema=function isc_c_DataSource_getSchema(_1){if(isc.isA.Class(_1))return _1.getSchema();return isc.DS.get(_1.schemaName||_1._constructor||_1.Class)}
,isc.A.getObjectField=function isc_c_DataSource_getObjectField(_1,_2){if(_1==null)return null;if(isc.isA.Class(_1))return _1.getObjectField(_2);var _3=isc.DS.getSchema(_1);if(_3)return _3.getObjectField(_2)}
,isc.A.getSchemaField=function isc_c_DataSource_getSchemaField(_1,_2){var _3=isc.DS.getSchema(_1);if(_3)return _3.getField(_2)}
,isc.A.addChildObject=function isc_c_DataSource_addChildObject(_1,_2,_3,_4,_5){return this.$40k(_1,"add",_2,_3,_4,_5)}
,isc.A.removeChildObject=function isc_c_DataSource_removeChildObject(_1,_2,_3,_4){return this.$40k(_1,"remove",_2,_3,_4)}
,isc.A.$40k=function isc_c_DataSource__doVerbToChild(_1,_2,_3,_4,_5,_6){var _7=_6||isc.DS.getObjectField(_1,_3);if(_7==null){this.logWarn("No field for child of type "+_3);return false}
this.logInfo(_2+" object "+this.echoLeaf(_4)+" in field: "+_7+" of parentObject: "+this.echoLeaf(_1),"editing");var _8=isc.DS.getSchemaField(_1,_7);if(isc.isA.Class(_1)){if(_1.$40k(_2,_3,_4,_5,_6))return true}
if(!_8.multiple){if(_2=="add")_1[_7]=_4;else if(_2=="remove"){if(_1[_7]!=null)delete _1[_7]}else{this.logWarn("unrecognized verb: "+_2);return false}
return true}
this.logInfo("using direct Array manipulation for field '"+_7+"'","editing");var _9=_1[_7];if(_2=="add"){if(_9!=null&&!isc.isAn.Array(_9)){this.logWarn("unexpected field value: "+this.echoLeaf(_9)+" in field '"+_7+"' when trying to add child: "+this.echoLeaf(_4));return false}
if(_9==null)_1[_7]=_9=[];if(_5!=null)_9.addAt(_4,_5);else _9.add(_4)}else if(_2=="remove"){if(!isc.isAn.Array(_9))return false;if(_5!=null)_9.removeAt(_4,_5);else _9.remove(_4)}else{this.logWarn("unrecognized verb: "+_2);return false}
return true}
,isc.A.getChildObject=function isc_c_DataSource_getChildObject(_1,_2,_3,_4){if(isc.isA.Class(_1))return _1.getChildObject(_2,_3,_4);var _5=isc.DS.getObjectField(_1,_2),_6=isc.DS.getSchemaField(_1,_5);var _7=_1[_5];if(!_6.multiple)return _7;if(!isc.isAn.Array(_7))return null;return isc.Class.getArrayItem(_3,_7)}
,isc.A.getAutoIdField=function isc_c_DataSource_getAutoIdField(_1){var _2=this.getNearestSchema(_1);return _2?_2.getAutoIdField():"ID"}
,isc.A.getAutoId=function isc_c_DataSource_getAutoId(_1){var _2=this.getAutoIdField(_1);return _2?_1[_2]:null}
);isc.B._maxIndex=isc.C+9;isc.A=isc.DataSource.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.getAutoIdField=function isc_DataSource_getAutoIdField(){return this.getInheritedProperty("autoIdField")||"ID"}
,isc.A.shouldCreateStandalone=function isc_DataSource_shouldCreateStandalone(){if(this.createStandalone!=null)return this.createStandalone;if(!this.superDS())return true;return this.superDS().shouldCreateStandalone()}
);isc.B._maxIndex=isc.C+2;var sharedEditModeFunctions={editModeClick:function(){if(isc.VisualBuilder&&isc.VisualBuilder.titleEditEvent=="click")this.editClick();return this.Super("editModeClick",arguments)},editModeDoubleClick:function(){if(isc.VisualBuilder&&isc.VisualBuilder.titleEditEvent=="doubleClick")this.editClick();return this.Super("editModeDoubleClick",arguments)}}
isc.Button.addProperties(sharedEditModeFunctions)
isc.ImgButton.addMethods(sharedEditModeFunctions)
isc.StretchImgButton.addMethods(sharedEditModeFunctions)
isc.SectionHeader.addMethods(sharedEditModeFunctions)
isc.ImgSectionHeader.addMethods(sharedEditModeFunctions)
isc.A=isc.ImgSectionHeader.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.setEditMode=function isc_ImgSectionHeader_setEditMode(_1,_2,_3){if(_1==null)_1=true;if(_1==this.editingOn)return;this.invokeSuper(isc.TabSet,"setEditMode",_1,_2,_3);if(this.editingOn){var _4=this;isc.Timer.setTimeout(function(){_4.saveToOriginalValues(["background"]);_4.background.setProperties({iconClick:_4.editModeIconClick})},0)}else{this.restoreFromOriginalValues(["background"])}}
,isc.A.editModeIconClick=function isc_ImgSectionHeader_editModeIconClick(){var _1=this.creator;if(_1){var _2=_1.layout;if(_2.sectionIsExpanded(_1))_2.collapseSection(_1);else _2.expandSection(_1);var _3=_1.editContext;if(_3){_3.setNodeProperties(_1.editNode,{"expanded":_2.sectionIsExpanded(_1)})}}
return this.Super("editModeClick",arguments)}
,isc.A.editClick=function isc_ImgSectionHeader_editClick(){var _1=this.getPageLeft()+this.getLeftBorderSize()+this.getLeftMargin()+1
-this.getScrollLeft(),_2=this.getVisibleWidth()-this.getLeftBorderSize()-this.getLeftMargin()
-this.getRightBorderSize()-this.getRightMargin()-1;isc.Timer.setTimeout({target:isc.EditContext,methodName:"manageTitleEditor",args:[this,_1,_2]},100)}
);isc.B._maxIndex=isc.C+3;isc.A=isc.StatefulCanvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.editClick=function isc_StatefulCanvas_editClick(){var _1,_2;if(isc.isA.Button(this)){_1=this.getPageLeft()+this.getLeftBorderSize()+this.getLeftMargin()+1
-this.getScrollLeft();_2=this.getVisibleWidth()-this.getLeftBorderSize()-this.getLeftMargin()
-this.getRightBorderSize()-this.getRightMargin()-1}else if(isc.isA.StretchImgButton(this)){_1=this.getPageLeft()+this.capSize;_2=this.getVisibleWidth()-this.capSize*2}else{isc.logWarn("Ended up in editClick with a StatefulCanvas of type '"+this.getClass()+"'.  This is neither a Button "+"nor a StretchImgButton - editor will work, but will hide the "+"entire component it is editing");_1=this.getPageLeft();_2=this.getVisibleWidth()}
isc.Timer.setTimeout({target:isc.EditContext,methodName:"manageTitleEditor",args:[this,_1,_2]},0)}
,isc.A.repositionTitleEditor=function isc_StatefulCanvas_repositionTitleEditor(){var _1=this.getPageLeft()+this.capSize,_2=this.getVisibleWidth()-this.capSize*2;isc.EditContext.positionTitleEditor(this,_1,_2)}
);isc.B._maxIndex=isc.C+2;if(isc.TabSet){isc.A=isc.TabSet;isc.A.addTabEditorHint="Enter tab titles (comma separated)";isc.A=isc.TabSet.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.defaultPaneDefaults={_constructor:"VLayout"};isc.B.push(isc.A.setEditMode=function isc_TabSet_setEditMode(_1,_2,_3){if(_1==null)_1=true;if(_1==this.editingOn)return;this.invokeSuper(isc.TabSet,"setEditMode",_1,_2,_3);if(this.editingOn){for(var i=0;i<this.tabs.length;i++){var _5=this.tabs[i];this.saveOriginalValues(_5);this.setCanCloseTab(_5,true)}
this.closeClick=function(_5){this.editContext.removeComponent(_5.editNode);var _6=this;isc.Timer.setTimeout(function(){_6.manageAddIcon()},200)}}else{for(var i=0;i<this.tabs.length;i++){var _5=this.tabs[i];this.restoreOriginalValues(_5);var _7=this.getTab(_5);this.setCanCloseTab(_5,_7.editNode.initData.canClose)}}
this.tabBar.setEditMode(_1,_2,null);this.paneContainer.setEditMode(_1,_2,null);this.manageAddIcon()}
,isc.A.saveOriginalValues=function isc_TabSet_saveOriginalValues(_1){var _2=this.getTab(_1);if(_2){_2.saveToOriginalValues(["closeClick","canClose","icon","iconSize","iconOrientation","iconAlign","disabled"])}}
,isc.A.restoreOriginalValues=function isc_TabSet_restoreOriginalValues(_1){var _2=this.getTab(_1);if(_2){_2.restoreFromOriginalValues(["closeClick","canClose","icon","iconSize","iconOrientation","iconAlign","disabled"])}}
,isc.A.showAddTabEditor=function isc_TabSet_showAddTabEditor(){var _1=this.tabBarPosition,_2=this.tabBarAlign,_3,_4,_5,_6,_7=this.tabBar;if(_1==isc.Canvas.TOP||_1==isc.Canvas.BOTTOM){_3=this.tabBar.getPageTop();_5=this.tabBar.getHeight();if(_2==isc.Canvas.LEFT){_4=this.addIcon.getPageLeft();_6=this.tabBar.getVisibleWidth()-this.addIcon.left;if(_6<150)_6=150}else{_6=this.tabBar.getVisibleWidth();_6=_6-(_6-(this.addIcon.left+this.addIcon.width));if(_6<150)_6=150;_4=this.addIcon.getPageLeft()+this.addIcon.width-_6}}else{_4=this.tabBar.getPageLeft();_6=150;_3=this.addIcon.getPageTop();_5=20}
this.manageAddTabEditor(_4,_6,_3,_5)}
,isc.A.manageAddIcon=function isc_TabSet_manageAddIcon(){if(this.editingOn){if(this.addIcon==null){this.addIcon=isc.Img.create({autoDraw:false,width:16,height:16,cursor:"hand",tabSet:this,src:"[SKIN]/actions/add.png",click:function(){this.tabSet.showAddTabEditor()}});this.tabBar.addChild(this.addIcon)}
var _1=this.tabs.length==0?null:this.getTab(this.tabs[this.tabs.length-1]);var _2=this.tabBarPosition,_3=this.tabBarAlign,_4,_5;if(_1==null){if(_2==isc.Canvas.TOP||_2==isc.Canvas.BOTTOM){if(_3==isc.Canvas.LEFT){_4=this.tabBar.left+10;_5=this.tabBar.top+(this.tabBar.height/ 2)-(8)}else{_4=this.tabBar.left+this.tabBar.width-10-(16);_5=this.tabBar.top+(this.tabBar.height/ 2)-(8)}}else{if(_3==isc.Canvas.TOP){_4=this.tabBar.left+(this.tabBar.width/ 2)-(8);_5=this.tabBar.top+10}else{_4=this.tabBar.left+(this.tabBar.width/ 2)-(8);_5=this.tabBar.top+this.tabBar.height-10-(16)}}}else{if(_2==isc.Canvas.TOP||_2==isc.Canvas.BOTTOM){if(_3==isc.Canvas.LEFT){_4=_1.left+_1.width+10;_5=_1.top+(_1.height/ 2)-(8)}else{_4=_1.left-10-(16);_5=_1.top+(_1.height/ 2)-(8)}}else{if(_3==isc.Canvas.TOP){_4=_1.left+(this.width/ 2)-(8);_5=_1.top+(_1.height)+10}else{_4=_1.left+(this.width/ 2)-(8);_5=_1.top+(_1.height/ 2)-(8)}}}
this.addIcon.setTop(_5);this.addIcon.setLeft(_4);this.addIcon.show()}else{if(this.addIcon&&this.addIcon.hide)this.addIcon.hide()}}
,isc.A.manageAddTabEditor=function isc_TabSet_manageAddTabEditor(_1,_2,_3,_4){if(!isc.isA.DynamicForm(isc.TabSet.addTabEditor)){isc.TabSet.addTabEditor=isc.DynamicForm.create({autoDraw:false,margin:0,padding:0,cellPadding:0,fields:[{name:"addTabString",type:"text",hint:isc.TabSet.addTabEditorHint,showHintInField:true,showTitle:false,keyPress:function(_6,_7,_8){if(_8=="Escape"){_7.discardUpdate=true;_7.hide();return}
if(_8=="Enter")_6.blurItem()},blur:function(_7,_6){if(!_7.discardUpdate){_7.targetComponent.editModeAddTabs(_6.getValue())}
_7.hide()}}]})}
var _5=isc.TabSet.addTabEditor;_5.addProperties({targetComponent:this});_5.discardUpdate=false;var _6=_5.getItem("addTabString");_6.setHeight(_4);_6.setWidth(_2);_6.setValue(_6.hint);_5.setTop(_3);_5.setLeft(_1);_5.show();_6.focusInItem();_6.delayCall("selectValue",[],100)}
,isc.A.editModeAddTabs=function isc_TabSet_editModeAddTabs(_1){if(!_1||_1==isc.TabSet.addTabEditorHint)return;var _2=_1.split(",");for(var i=0;i<_2.length;i++){var _4=isc.addProperties({},this.defaultPaneDefaults);if(!_4.type&&!_4.className){_4.type=_4._constructor}
var _5={type:"Tab",initData:{title:_2[i]}};var _6=this.editContext.addComponent(this.editContext.makeEditNode(_5),this.editNode);if(_6){this.editContext.addComponent(this.editContext.makeEditNode(_4),_6)}}}
,isc.A.addTabsEditModeExtras=function isc_TabSet_addTabsEditModeExtras(_1){this.delayCall("manageAddIcon");if(this.editingOn){for(var i=0;i<_1.length;i++){this.saveOriginalValues(_1[i]);this.setCanCloseTab(_1[i],true)}}}
,isc.A.removeTabsEditModeExtras=function isc_TabSet_removeTabsEditModeExtras(){this.delayCall("manageAddIcon")}
,isc.A.editablePropertiesUpdated=function isc_TabSet_editablePropertiesUpdated(){this.delayCall("manageAddIcon");this.invokeSuper(isc.TabSet,"editablePropertiesUpdated")}
,isc.A.tabScrolledIntoView=function isc_TabSet_tabScrolledIntoView(){if(!this.editingOn)return;for(var i=0;i<this.tabs.length;i++){var _2=this.getTab(this.tabs[i]);if(_2.titleEditor&&_2.titleEditor.isVisible()){_2.repositionTitleEditor()}}}
,isc.A.findEditNode=function isc_TabSet_findEditNode(_1){this.logInfo("In TabSet.findEditNode, dragType is "+_1,"editModeDragTarget");if(_1!="Tab"){var _2=this.getTab(this.getSelectedTabNumber());if(_2&&_2.editNode)return _2;for(var i=0;i<this.tabs.length;i++){_2=this.getTab(i);if(_2.editNode)return _2}
if(this.parentElement)return this.parentElement.findEditNode(_1)}
return this.Super("findEditNode",arguments)}
);isc.B._maxIndex=isc.C+12;isc.A=isc.TabBar.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.findEditNode=function isc_TabBar_findEditNode(_1){if(_1=="Tab"){return this.parentElement.findEditNode(_1)}else if(this.parentElement&&isc.isA.Layout(this.parentElement.parentElement)){return this.parentElement.parentElement.findEditNode(_1)}
return this.Super("findEditNode",arguments)}
);isc.B._maxIndex=isc.C+1}
isc.A=isc.Layout.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.editModeDrop=function isc_Layout_editModeDrop(){if(this.shouldPassDropThrough()){this.hideDropLine();return}
isc.EditContext.hideAncestorDragDropLines(this);var _1=isc.EH.dragTarget,_2,_3;if(!_1.isA("Palette")){if(_1.isA("FormItemProxyCanvas")){_1=_1.formItem}
_3=_1._constructor||_1.Class}else{_2=_1.transferDragData();if(isc.isAn.Array(_2))_2=_2[0];_2.dropped=true;_3=_2.className||_2.type}
var _4=this.findEditNode(_3);if(_4){_4=_4.editNode}
if(this.modifyEditNode){_4=this.modifyEditNode(_2,_4,_3);if(!_4){this.hideDropLine();return isc.EH.STOP_BUBBLING}}
if(!_1.isA("Palette")){if(isc.EditContext.$70r)isc.EditContext.$70r.hide();if(_1==this)return;var _5=this.editContext.data,_6=_5.getParent(_1.editNode),_7=_5.getChildren(_6).indexOf(_1.editNode),_8=this.getDropPosition(_3);this.editContext.removeComponent(_1.editNode);if(_6==this.editNode&&_8>_7)_8--;var _9;if(_1.isA("FormItem")){if(_1.isA("CanvasItem")){_9=this.editContext.addNode(_1.canvas.editNode,_4,_8)}else{_9=this.editContext.addWithWrapper(_1.editNode,_4)}}else{_9=this.editContext.addNode(_1.editNode,_4,_8)}
if(isc.isA.TabSet(_4.liveObject)){_4.liveObject.selectTab(_1)}else if(_9&&_9.liveObject){isc.EditContext.delayCall("selectCanvasOrFormItem",[_9.liveObject,true],200)}}else{var _10;var _11=isc.ClassFactory.getClass(_3);if(_11&&_11.isA("FormItem")){_10=this.editContext.addWithWrapper(_2,_4)}else{_10=this.editContext.addComponent(_2,_4,this.getDropPosition(_3))}
if(_10!=null){var _12=_2.liveObject;if(isc.isA.TabSet(_4.liveObject)){var _13=isc.addProperties({},_4.liveObject.defaultPaneDefaults);if(!_13.type&&!_13.className){_13.type=_13._constructor}
this.editContext.addComponent(this.editContext.makeEditNode(_13,_10));_4.liveObject.selectTab(_12)}
if(isc.isA.TabSet(_12)){_12.delayCall("showAddTabEditor")}else if(isc.isA.ImgTab(_12)||isc.isA.Button(_12)||isc.isA.StretchImgButton(_12)||isc.isA.SectionHeader(_12)||isc.isA.ImgSectionHeader(_12)){_12.delayCall("editClick")}}}
this.hideDropLine();return isc.EH.STOP_BUBBLING}
,isc.A.editModeDropMove=function isc_Layout_editModeDropMove(){if(!this.editModeWillAcceptDrop())return false;if(!this.shouldPassDropThrough()){this.Super("dropMove",arguments);if(this.parentElement&&this.parentElement.hideDropLine){this.parentElement.hideDropLine();if(isc.isA.FormItem(this.parentElement)){this.parentElement.form.hideDragLine()}}
return isc.EH.STOP_BUBBLING}else{this.hideDropLine()}}
,isc.A.editModeDropOver=function isc_Layout_editModeDropOver(){if(!this.editModeWillAcceptDrop())return false;if(!this.shouldPassDropThrough()){this.Super("dropOver",arguments);if(this.parentElement&&this.parentElement.hideDropLine){this.parentElement.hideDropLine();if(isc.isA.FormItem(this.parentElement)){this.parentElement.form.hideDragLine()}}
return isc.EH.STOP_BUBBLING}else{this.hideDropLine()}}
);isc.B._maxIndex=isc.C+3;isc.A=isc.Portlet.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.addedToEditContext=function isc_Portlet_addedToEditContext(_1,_2){this.editContext=_1;this.editNode=_2}
,isc.A.canAdd=function isc_Portlet_canAdd(_1){if(_1=="Portlet")return false;return this.Super("canAdd",arguments)}
);isc.B._maxIndex=isc.C+2;isc.A=isc.PortalRow.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.addedToEditContext=function isc_PortalRow_addedToEditContext(_1,_2){this.editContext=_1;this.editNode=_2;if(_2.generatedType)delete _2.generatedType}
,isc.A.wrapChildNode=function isc_PortalRow_wrapChildNode(_1,_2,_3,_4){var _5=_2.liveObject;if(isc.isA.Portlet(_5)){return _3}else{var _6=_1.makeEditNode({type:"Portlet",defaults:{title:_2.title,destroyOnClose:true}});_1.addNode(_6,_3,_4);return _6}}
,isc.A.handleDroppedEditNode=function isc_PortalRow_handleDroppedEditNode(_1,_2){var _3=this.editContext;var _4=this.editNode;if(isc.isA.Palette(_1)){var _5=_1.transferDragData(),_6=(isc.isAn.Array(_5)?_5[0]:_5);if(_3&&_4){_3.addNode(_6,_4,_2);return false}else{if(isc.isA.Portlet(_6.liveObject)){_1=_6.liveObject}else{_1=isc.Portlet.create({autoDraw:false,title:_6.title,items:[_6.liveObject],destroyOnClose:true})}}}
return _1}
);isc.B._maxIndex=isc.C+3;isc.A=isc.PortalColumnBody.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.handleDroppedEditNode=function isc_PortalColumnBody_handleDroppedEditNode(_1,_2){var _3=this.creator.editContext;var _4=this.creator.editNode;if(isc.isA.Palette(_1)){var _5=_1.transferDragData(),_6=(isc.isAn.Array(_5)?_5[0]:_5);if(_3&&_4){_3.addNode(_6,_4,_2);return false}else{if(isc.isA.Portlet(_6.liveObject)){_1=_6.liveObject}else{_1=isc.Portlet.create({autoDraw:false,title:_6.title,items:[_6.liveObject],destroyOnClose:true})}}}
if(_1){var _7=_1.portalRow;if(_7&&_7.parentElement==this&&_7.getMembers().length==1){if(_3&&_4&&_7.editNode){var _8=this.getMemberNumber(_7);if(_2==_8||_2==_8+1)return;_3.removeNode(_7.editNode);if(_8<_2)_2-=1;_3.addNode(_7.editNode,_4,_2);return null}}else{if(_3&&_4&&_1.editNode){_3.addNode(_1.editNode,_4,_2);return null}}}
return _1}
);isc.B._maxIndex=isc.C+1;isc.A=isc.PortalColumn.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.wrapChildNode=function isc_PortalColumn_wrapChildNode(_1,_2,_3,_4){var _5=_2.liveObject;if(isc.isA.PortalRow(_5)||_2.type=="PortalRow"){return _3}else if(isc.isA.Portlet(_5)){var _6=_1.makeEditNode({type:this.rowConstructor,defaults:{}});_1.addNode(_6,_3,_4);return _6}else{var _7=_1.makeEditNode({type:"Portlet",defaults:{title:_2.title,destroyOnClose:true}});_1.addNode(_7,_3,_4);return _7}}
,isc.A.canAdd=function isc_PortalColumn_canAdd(_1){return false}
,isc.A.addedToEditContext=function isc_PortalColumn_addedToEditContext(_1,_2){this.editContext=_1;this.editNode=_2;if(_2.generatedType)delete _2.generatedType}
,isc.A.setEditMode=function isc_PortalColumn_setEditMode(_1,_2,_3){this.Super("setEditMode",arguments);this.rowLayout.setEditMode(_1,_2,null)}
);isc.B._maxIndex=isc.C+4;isc.A=isc.PortalLayout.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.addedToEditContext=function isc_PortalLayout_addedToEditContext(_1,_2){this.editContext=_1;this.editNode=_2;for(var i=0;i<this.getNumColumns();i++){var _4=this.getPortalColumn(i);if(!_4.editContext){var _5=_1.makeEditNode({type:this.columnConstructor,liveObject:_4,defaults:{ID:_4.ID,_constructor:this.columnConstructor}});_5.initData=_5.defaults;_1.addNode(_5,_2,i,null,true)}}
_2.defaults.numColumns=0}
);isc.B._maxIndex=isc.C+1;if(isc.DynamicForm){isc.A=isc.DynamicForm.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.dropMargin=10;isc.B.push(isc.A.setEditMode=function isc_DynamicForm_setEditMode(_1,_2,_3){if(_1==null)_1=true;if(_1==this.editingOn)return;this.invokeSuper(isc.DynamicForm,"setEditMode",_1,_2,_3);if(this.editingOn){this.saveToOriginalValues(["canDropItems","canAddColumns","dropOut"]);this.setProperties({canDropItems:true,canAddColumns:true,dropOut:this.editModeDropOut});this.resetValues();var _4=this.dropMargin;if(_4*2>this.getVisibleHeight()-10){_4=Math.round((this.getVisibleHeight()-10)/2);if(_4<2)_4=2;this.dropMargin=_4}}else{this.restoreFromOriginalValues(["canDropItems","canAddColumns","dropOut"]);this.resetValues()}}
,isc.A.editModeDropOver=function isc_DynamicForm_editModeDropOver(){if(this.canDropItems!=true)return false;if(!this.editModeWillAcceptDrop())return false;this.$69c=null;this.hideDragLine();return isc.EH.STOP_BUBBLING}
,isc.A.editModeDropMove=function isc_DynamicForm_editModeDropMove(){if(!this.ns.EH.getDragTarget())return false;if(this.canDropItems!=true)return false;if(!this.editModeWillAcceptDrop())return false;var _1=this.ns.EH.getDragTarget().getDragData();if(isc.isAn.Array(_1))_1=_1[0];if(_1!=null&&(_1.type=="DataSource"||_1.className=="DataSource")){this.hideDragLine();return isc.EH.STOP_BUBBLING}
if(this.getItems().length==0){if(this.shouldPassDropThrough()){this.hideDragLine();return}
isc.EditContext.hideAncestorDragDropLines(this);this.showDragLineForForm();return isc.EH.STOP_BUBBLING}
var _2=this.ns.EH.lastEvent,_3=this.getItemAtPageOffset(_2.x,_2.y),_4=this.getNearestItem(_2.x,_2.y);if(_3){isc.EditContext.hideAncestorDragDropLines(this);this.showDragLineForItem(_4,_2.x,_2.y)}else{if(this.shouldPassDropThrough()){this.hideDragLine();return}
if(_4){isc.EditContext.hideAncestorDragDropLines(this);this.showDragLineForItem(_4,_2.x,_2.y)}else{this.hideDragLine()}}
this.$69c=_4;return isc.EH.STOP_BUBBLING}
,isc.A.editModeDropOut=function isc_DynamicForm_editModeDropOut(){this.hideDragLine();return isc.EH.STOP_BUBBLING}
,isc.A.editModeDrop=function isc_DynamicForm_editModeDrop(){var _1=this.ns.EH.getDragTarget().getDragData();if(isc.isAn.Array(_1))_1=_1[0];if((_1&&_1.className=="DataSource")||this.getItems().length==0)
{if(this.shouldPassDropThrough()){this.hideDragLine();return}
this.itemDrop(this.ns.EH.getDragTarget(),0,0,0);return isc.EH.STOP_BUBBLING}
if(!this.$69c){isc.logWarn("lastDragOverItem not set, cannot drop","dragDrop");return}
var _2=this.$69c,_3=this.getItemTableOffsets(_2),_4=_2.dropSide,_5=_2.$69d,_6=this.getItemDropIndex(_2,_4);this.$69c=null;if(this.shouldPassDropThrough()){this.hideDragLine();return}
if(_6!=null&&_6>=0){if(this.parentElement){if(this.parentElement.hideDragLine)this.parentElement.hideDragLine();if(this.parentElement.hideDropLine)this.parentElement.hideDropLine()}
var _7=this.items.$8j.duplicate();this.modifyFormOnDrop(_2,_3.top,_3.left,_4,_7)}
this.hideDragLine();return isc.EH.STOP_BUBBLING}
,isc.A.itemDrop=function isc_DynamicForm_itemDrop(_1,_2,_3,_4,_5,_6){var _7=_1.getDragData();if(_7==null){_7=isc.EH.dragTarget;if(isc.isA.FormItemProxyCanvas(_7)){this.logInfo("The dragTarget is a FormItemProxyCanvas for "+_7.formItem,"editModeDragTarget");_7=_7.formItem}}
if(!_1.isA("Palette")){if(isc.EditContext.$70r)isc.EditContext.$70r.hide();var _8=this.editContext.data,_9=_8.getParent(_7.editNode),_10=_8.getChildren(_9).indexOf(_7.editNode),_11=_7.editNode;if(isc.isA.Function(this.itemDropping)){_11=this.itemDropping(_11,_2,true);if(!_11)return}
this.editContext.removeComponent(_11);if(_9==this.editNode&&_2>_10)_2--;var _12=this.editContext.addNode(_7.editNode,this.editNode,_2);if(_12&&_12.liveObject){isc.EditContext.delayCall("selectCanvasOrFormItem",[_12.liveObject,true],200)}
return _12}else{var _13=_1.transferDragData();if(isc.isAn.Array(_13))_13=_13[0];if(_13.loadData&&!_13.isLoaded){var _14=this;_13.loadData(_13,function(_15){_15=_15||_13
_15.isLoaded=true;_14.completeItemDrop(_15,_2,_3,_4,_5,_6)
_15.dropped=_13.dropped});return}
this.completeItemDrop(_13,_2,_3,_4,_5,_6)}}
,isc.A.completeItemDrop=function isc_DynamicForm_completeItemDrop(_1,_2,_3,_4,_5,_6){var _7=_1.liveObject,_8;if(!isc.isA.FormItem(_7)){if(isc.isA.Button(_7)||isc.isAn.IButton(_7)){_1=this.editContext.makeEditNode({type:"ButtonItem",title:_7.title,defaults:_1.defaults})}else if(isc.isA.Canvas(_7)){_8=_1;_1=this.editContext.makeEditNode({type:"CanvasItem"});isc.addProperties(_1.initData,{showTitle:false,startRow:true,endRow:true,width:"*",colSpan:"*"})}}
_1.dropped=true;if(isc.isA.Function(this.itemDropping)){_1=this.itemDropping(_1,_2,true);if(!_1)return}
var _9=this.editContext.addComponent(_1,this.editNode,_2);if(_9){isc.EditContext.clearSchemaProperties(_9);if(_8){_9=this.editContext.addComponent(_8,_9,0);if(isc.isA.TabSet(_7)){_7.delayCall("showAddTabEditor",[],1000)}}
if(_9.liveObject.dataSource){_9.liveObject.setDataSource(_9.liveObject.dataSource,null,true)}
isc.EditContext.delayCall("selectCanvasOrFormItem",[_1.liveObject,true],200);if(_9.showTitle!=false){_1.liveObject.delayCall("editClick")}}
if(_6)this.fireCallback(_6,"node",[_9])}
,isc.A.modifyFormOnDrop=function isc_DynamicForm_modifyFormOnDrop(_1,_2,_3,_4,_5){if(this.canAddColumns==false)return;var _6=this.ns.EH.getDragTarget().getDragData(),_7,_8,_9,_10=this;if(!_6){_6=this.ns.EH.getDragTarget();if(!isc.isA.FormItemProxyCanvas(_6)){this.logWarn("In modifyFormOnDrop the drag target was not a FormItemProxyCanvas");return}
_6=_6.formItem;var _11=-1;for(var i=0;i<_5.length;i++){for(var j=0;j<_5[i].length;j++){if(_5[i][j]==_11)continue;_11=_5[i][j];if(this.items[_11]==_6){_8=i;_9=_11;break}}}
var _14=true}else{if(isc.isAn.Array(_6))_6=_6[0];var _15=_6.type||_6.className;var _16=isc.ClassFactory.getClass(_15);if(isc.isA.FormItem(_16)){_6=this.createItem(_6,_15)}else{_6=this.createItem({type:"CanvasItem",showTitle:false},"CanvasItem")}
var _14=false}
_7=this.getAdjustedColSpan(_6);if((_6.startRow&&_6.$71r)||(_6.endRow&&_6.$71n)){_6.editContext.setNodeProperties(_6.editNode,{startRow:null,$71r:null,endRow:null,$71n:null})}
var _17=[];if(_14&&_8){var _18=_5[_8],_11=-1;for(var i=0;i<_18.length;i++){if(_18[i]!=_11){_11=_18[i];if(this.items[_11]==_6)continue;if(isc.isA.SpacerItem(this.items[_11])&&this.items[_11].$71m)
{this.logDebug("Marking spacer "+this.items[_11].name+" for removal","formItemDragDrop");_17.add(this.items[_11]);continue}
this.logDebug("Found a non-spacer item on row "+_8+", no spacers will be deleted","formItemDragDrop");_17=null;break}}}
var _19=0;if(_4=="L"||_4=="R"){var _20=true;if(_6.startRow)_20=false;if(_6.endRow&&(_4=="L"||_3<_5[_2].length)){_20=false}
if(_14&&_8==_2)_20=false;if(_20){var _21=_7;var _22=_5[_2][_3];if(_5[_2].contains(_22)){var _23=this.items[_22];if(!isc.isA.SpacerItem(_23)||!_23.$71m){_22+=_4=="L"?-1:1;_23=this.items[_22]}
if(_5[_2].contains(_22)){if(isc.isA.SpacerItem(_23)&&_23.$71m){if(_23.colSpan&&_23.colSpan>_21){_23.editContext.setNodeProperties(_23.editNode,{colSpan:_23.colSpan-_21});_21=0}else{_21-=_23.colSpan;_23.editContext.removeComponent(_23.editNode);if(_4=="R")_19=-1}}}}
if(_21<=0){_20=false}else if(_5[_2].length+_7<=this.numCols){_20=false}else{this.editContext.setNodeProperties(this.editNode,{numCols:this.numCols+_21})}}
for(var i=0;i<_5.length;i++){var _22=_5[i][_3];if(_22==null)_22=this.items.length;else _22+=_19+(_4=="L"?0:1);if(i!=_2){if(!_20)continue;if(_14&&_8&&_2<_8&&i==_8)
{_19--}
if(_17&&_17.length>0&&i==_8){continue}
if(_22>0){var _23=this.items[_22-1];if(!_23||_23==_6||_23.endRow){continue}}
var _24=this.getAdjustedColSpan(_23);if(_4=="R"&&_3+_24>=_5[i].length){if(!_23.endRow){_23.editContext.setNodeProperties(_23.editNode,{endRow:true,$71n:true})}
continue}
var _25=this.editContext.makeEditNode({type:"SpacerItem"});isc.addProperties(_25.initData,{colSpan:_21,height:0,$71m:true});var _26=this.editContext.addComponent(_25,this.editNode,_22);_19++}else{if(_4=="L"){var _23=this.items[_22];if(_23&&_23.startRow&&_23.$71r){_23.editContext.setNodeProperties(_23.editNode,{startRow:null,$71r:null})}}else{var _23=this.items[_22-1];if(_23&&_23.endRow&&_23.$71n){_23.editContext.setNodeProperties(_23.editNode,{endRow:null,$71n:null})}}
this.itemDrop(this.ns.EH.getDragTarget(),_22,i,_3,_4,function(_36){_10.$72s=_36});if(_8==null||_2<_8)_19++}}}else{var _27,_28;if(isc.isA.SpacerItem(_1)&&_1.$71m){_27=_2}else{_27=_2+(_4=="B"?1:0)}
if(_5[_27])_28=_5[_27][_3];var _29;if(_27>=_5.length)_29=this.items.length;else _29=_5[_27][0];var _30=_28==null?null:this.items[_28];if(_30==null||(isc.isA.SpacerItem(_30)&&_30.$71m)){if(_27>_5.length-1||_27<0){if(_3!=0&&!_6.startRow){var _25=this.editContext.makeEditNode({type:"SpacerItem"});isc.addProperties(_25.initData,{colSpan:_3,height:0,$71m:true});this.editContext.addComponent(_25,this.editNode,_29)}
this.itemDrop(this.ns.EH.getDragTarget(),_29+(_3!=0?1:0),_27,_3,_4,function(_36){_10.$72s=_36})}else if(_30==null){var _31=_5[_27].length-1;if(_31<0){isc.logWarn("Found completely empty row in DynamicForm at position ("+_27+","+(_3)+")");return}
var _32=_5[_27][_31];var _23=this.items[_32];if(_23==null){isc.logWarn("Null item in DynamicForm at position ("+_27+","+(_3-1)+")");return}
if(_23.endRow&&_23!=_6){_23.editContext.setNodeProperties(_23.editNode,{endRow:false})}
var _33=(_3-_31)-1;if(_14&&_23==_6){_33+=_7}
if(_33>0){var _25=this.editContext.makeEditNode({type:"SpacerItem"});isc.addProperties(_25.initData,{colSpan:_33,height:0,$71m:true});this.editContext.addComponent(_25,this.editNode,_32+1)}
this.itemDrop(this.ns.EH.getDragTarget(),_32+(_33>0?2:1),_27,_3,_4,function(_36){_10.$72s=_36})}else{var _34=_30.colSpan?_30.colSpan:1,_35=_7;if(_34>_35){_30.editContext.setNodeProperties(_30.editNode,{colSpan:_34-_35});this.itemDrop(this.ns.EH.getDragTarget(),_28,_27,_3,_4,function(_36){_10.$72s=_36})}else{this.itemDrop(this.ns.EH.getDragTarget(),_28,_27,_3,_4,function(_36){_10.$72s=_36});_30.editContext.removeComponent(_30.editNode)}}}else{if(_3!=0){var _25=this.editContext.makeEditNode({type:"SpacerItem"});isc.addProperties(_25.initData,{colSpan:_3,height:0,$71m:true});this.editContext.addComponent(_25,this.editNode,_29)}
this.itemDrop(this.ns.EH.getDragTarget(),_29+(_3==0?0:1),_27,_3,_4,function(_36){if(_36&&_36.liveObject&&_36.liveObject.editContext){_36.liveObject.editContext.setNodeProperties(_36,{endRow:true,$71n:true})}
_10.$72s=_36})}}
if(_14&&_17){for(var i=0;i<_17.length;i++){this.logDebug("Removing spacer item "+_17[i].name,"formItemDragDrop");_17[i].editContext.removeComponent(_17[i].editNode)}}
if(!_14)_6.destroy();if(this.$72s&&this.$72s.liveObject){isc.EditContext.delayCall("selectCanvasOrFormItem(",[this.$72s.liveObject],200)}}
,isc.A.getAdjustedColSpan=function isc_DynamicForm_getAdjustedColSpan(_1){if(!_1)return 0;var _2=_1.colSpan!=null?_1.colSpan:1;if(_2=="*")_2=1;if(_1.showTitle!=false&&(_1.titleOrientation=="left"||_1.titleOrientation=="right"||_1.titleOrientation==null))
{_2++}
return _2}
,isc.A.canAdd=function isc_DynamicForm_canAdd(_1){if(this.getObjectField(_1)!=null)return true;var _2=isc.ClassFactory.getClass(_1);if(_2&&_2.isA("Canvas"))return true;return false}
,isc.A.setEditorType=function isc_DynamicForm_setEditorType(_1,_2){if(!_1.editContext)return;var _3=_1.editContext.data,_4=_3.getParent(_1.editNode),_5=_3.getChildren(_4).indexOf(_1.editNode),_6=_1.editContext,_7={className:_2,defaults:_1.editNode.defaults},_8=_6.makeEditNode(_7);_6.removeComponent(_1.editNode);_6.addComponent(_8,_4,_5)}
,isc.A.itemDropping=function isc_DynamicForm_itemDropping(_1,_2,_3){var _4=_1.liveObject,_5=isc.EditContext.getSchemaInfo(_1);if(!_5.dataSource)return _1;if(!this.dataSource){this.setDataSource(_5.dataSource);this.serviceNamespace=_5.serviceNamespace;this.serviceName=_5.serviceName;return _1}
if(_5.dataSource==isc.DataSource.getDataSource(this.dataSource).ID&&_5.serviceNamespace==this.serviceNamespace&&_5.serviceName==this.serviceName){return _1}
var _6=this.editContext.makeEditNode({className:"CanvasItem",defaults:{cellStyle:"nestedFormContainer"}});isc.addProperties(_6.initData,{showTitle:false,colSpan:2});_6.dropped=true;this.editContext.addComponent(_6,this.editNode,_2);var _7=this.editContext.makeEditNode({className:"DynamicForm",defaults:{numCols:2,canDropItems:false,dataSource:_5.dataSource,serviceNamespace:_5.serviceNamespace,serviceName:_5.serviceName,doNotUseDefaultBinding:true}});_7.dropped=true;this.editContext.addComponent(_7,_6,0);var _8=this.editContext.addComponent(_1,_7,0);isc.EditContext.clearSchemaProperties(_8)}
,isc.A.getFieldEditNode=function isc_DynamicForm_getFieldEditNode(_1,_2){var _3=this.getEditorType(_1);_3=_3.substring(0,1).toUpperCase()+_3.substring(1)+"Item";var _4={type:_3,autoGen:true,defaults:{name:_1.name,title:_1.title||_2.getAutoTitle(_1.name)}}
return _4}
);isc.B._maxIndex=isc.C+13;isc.A=isc.FormItem.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.setEditMode=function isc_FormItem_setEditMode(_1,_2,_3){if(_1==null)_1=true;if(this.editingOn==_1)return;this.editingOn=_1;if(this.editingOn){this.editContext=_2}
this.editNode=_3;if(this.editingOn){this.saveToOriginalValues(["click","doubleClick","changed"]);this.setProperties({click:this.editModeClick,doubleClick:this.editModeDoubleClick,changed:this.editModeChanged})}else{this.restoreFromOriginalValues(["click","doubleClick","changed"])}}
,isc.A.editModeChanged=function isc_FormItem_editModeChanged(_1,_2,_3){this.editContext.setNodeProperties(this.editNode,{defaultValue:_3})}
,isc.A.setEditorType=function isc_FormItem_setEditorType(_1){if(this.form)this.form.setEditorType(this,_1)}
);isc.B._maxIndex=isc.C+3;isc.A=isc.ButtonItem.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.editClick=function isc_ButtonItem_editClick(){var _1=this.canvas.getPageLeft(),_2=this.canvas.getVisibleWidth(),_3=this.canvas.getPageTop(),_4=this.canvas.getHeight();isc.EditContext.manageTitleEditor(this,_1,_2,_3,_4)}
);isc.B._maxIndex=isc.C+1}
isc.A=isc.SectionStack.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.canAdd=function isc_SectionStack_canAdd(_1){if(_1=="SectionStackSection")return true;var _2=isc.ClassFactory.getClass(_1);if(_2&&_2.isA("Canvas"))return true;if(_2&&_2.isA("FormItem"))return true;return false}
,isc.A.modifyEditNode=function isc_SectionStack_modifyEditNode(_1,_2,_3){if(_3=="SectionStackSection")return _2;var _4=this.getDropPosition();if(_4==0){isc.warn("Cannot drop before the first section header");return false}
var _5=this.$700();for(var i=_5.length-1;i>=0;i--){if(_4>_5[i]){return this.getSectionHeader(i).editNode}}
return _2}
,isc.A.getEditModeDropPosition=function isc_SectionStack_getEditModeDropPosition(_1){var _2=this.invokeSuper(isc.SectionStack,"getDropPosition");if(!_1||_1=="SectionStackSection"){return _2}
var _3=this.$700();for(var i=_3.length-1;i>=0;i--){if(_2>_3[i]){return _2-_3[i]-1}}
return 0}
,isc.A.$700=function isc_SectionStack__getHeaderPositions(){var _1=[],j=0;for(var i=0;i<this.getMembers().length;i++){if(this.getMember(i).isA(this.sectionHeaderClass)){_1[j++]=i}}
return _1}
);isc.B._maxIndex=isc.C+4;if(isc.ListGrid!=null){isc.A=isc.ListGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.setEditMode=function isc_ListGrid_setEditMode(_1,_2,_3){if(_1==null)_1=true;if(_1==this.editingOn)return;this.invokeSuper(isc.ListGrid,"setEditMode",_1,_2,_3);if(this.editingOn){this.saveToOriginalValues(["setNoDropIndicator","clearNoDropIndicator","headerClick"]);this.setProperties({setNoDropIndicator:this.editModeSetNoDropIndicator,clearNoDropIndicator:this.editModeClearNoDropIndicator,headerClick:this.editModeHeaderClick})}else{this.restoreFromOriginalValues(["setNoDropIndicator","clearNoDropIndicator","headerClick"])}}
,isc.A.editModeClearNoDropIndicator=function isc_ListGrid_editModeClearNoDropIndicator(_1){this.Super("clearNoDropIndicator",arguments);this.body.editModeClearNoDropIndicator()}
,isc.A.editModeSetNoDropIndicator=function isc_ListGrid_editModeSetNoDropIndicator(){this.Super("setNoDropIndicator",arguments);this.body.editModeSetNoDropIndicator()}
,isc.A.editModeHeaderClick=function isc_ListGrid_editModeHeaderClick(_1){var _2=this.editContext.data,_3=_2.getChildren(_2.findById(this.ID)),_4=_3[_1];_4.liveObject.$73a=this.header.getButton(_1);isc.EditContext.selectCanvasOrFormItem(_4.liveObject);this.$73b=true;return isc.EH.STOP_BUBBLING}
,isc.A.editModeClick=function isc_ListGrid_editModeClick(){if(this.editNode){if(this.$73b)delete this.$73b;else isc.EditContext.selectCanvasOrFormItem(this,true);return isc.EH.STOP_BUBBLING}}
);isc.B._maxIndex=isc.C+5}
if(isc.TreeGrid!=null){isc.A=isc.TreeGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.setEditMode=function isc_TreeGrid_setEditMode(_1,_2,_3){if(_1==null)_1=true;if(_1==this.editingOn)return;this.invokeSuper(isc.TreeGrid,"setEditMode",_1,_2,_3);if(this.editingOn){this.editModeCreateDefaultTreeFieldEditNode();this.saveToOriginalValues(["addField"]);this.setProperties({addField:this.editModeAddField})}else{this.restoreFromOriginalValues(["addField"])}}
,isc.A.editModeCreateDefaultTreeFieldEditNode=function isc_TreeGrid_editModeCreateDefaultTreeFieldEditNode(){if(isc.$75u)return;if(this.dataSource)return;var _1=this.fields;for(var i=0;i<_1.length;i++){if(_1[i].name=="nodeTitle"){var _3={type:"TreeGridField",autoGen:true,defaults:{name:_1[i].name,title:_1[i].title}};var _4=this.editContext.makeEditNode(_3);this.editContext.addNode(_4,this.editNode,null,null,true);return}}}
,isc.A.editModeAddField=function isc_TreeGrid_editModeAddField(_1,_2){this.Super("addField",arguments);if(isc.$75u){if(_1.treeField){var _3=this.getFields();for(var i=0;i<_3.length;i++){if(_3[i].name!=_1.name&&_3[i].treeField){this.removeField(_3[i]);break}}}}}
,isc.A.editModeSetDataSource=function isc_TreeGrid_editModeSetDataSource(_1,_2,_3){if(isc.$75u){this.baseSetDataSource(_1,_2);return}
if(_1==null)return;if(_1==this.dataSource&&!_3)return;var _2=this.getFields();if(_2){for(var i=0;i<_2.length;i++){var _5=_2[i];if(_5.treeField){_5.treeField=null;var _6=_5.editNode;break}}}
var _7=this.getFields();_7.remove(_5);var _8,_2=_1.fields;if(_2&&isc.getKeys(_2).length==1&&_1.fieldIsComplexType(_2[isc.firstKey(_2)].name))
{_8=_1.getSchema(_2[isc.firstKey(_2)].type)}else{_8=_1}
var _2=_8.getFields(),_9=_1.titleField;if(!isc.isAn.Array(_2))_2=isc.getValues(_2);for(var _10=0;_10<_2.length;_10++){if(!this.shouldUseField(_2[_10],_1))continue;if(_9==null||_9==_2[_10].name){var _11=_2[_10];break}}
if(_11)_7.addAt(_11,0);this.baseSetDataSource(_1,_7);var _12=this.getFieldEditNode(_11,_8);_12.defaults.treeField=true;var _13=this.editContext.makeEditNode(_12);this.editContext.addNode(_13,this.editNode,0,null);if(_6)this.editContext.removeNode(_6,true)}
);isc.B._maxIndex=isc.C+4}
var basicSetEditMode=function(_1,_2,_3){if(_1==null)_1=true;if(this.editingOn==_1)return;this.editingOn=_1;if(this.editingOn)this.editContext=_2;this.editNode=_3}
isc.A=isc.ServiceOperation.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.setEditMode=basicSetEditMode;isc.B.push(isc.A.getActionTargetTitle=function isc_ServiceOperation_getActionTargetTitle(){return"Operation: ["+this.operationName+"]"}
);isc.B._maxIndex=isc.C+1;if(isc.ValuesManager!=null){isc.A=isc.ValuesManager.getPrototype();isc.A.setEditMode=basicSetEditMode}
isc.ClassFactory.defineInterface("EditContext");isc.A=isc.EditContext;isc.A.$72t=18;isc.A.$72u=18;isc.A.$70s=-18;isc.A.$70t=0;isc.A=isc.EditContext;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.manageTitleEditor=function isc_c_EditContext_manageTitleEditor(_1,_2,_3,_4,_5){if(!isc.isA.DynamicForm(this.titleEditor)){this.titleEditor=isc.DynamicForm.create({autoDraw:false,margin:0,padding:0,cellPadding:0,fields:[{name:"title",type:"text",showTitle:false,keyPress:function(_9,_11,_12){if(_12=="Escape"){_11.discardUpdate=true;_11.hide();return}
if(_12=="Enter")_9.blurItem()},blur:function(_11,_9){if(!_11.discardUpdate){var _6=_11.targetComponent,_7=_6.editContext;if(_7){_7.setNodeProperties(_6.editNode,{"title":_9.getValue()});_7.nodeClick(_7,_6.editNode)}}
_11.hide()}}]})}
var _8=this.titleEditor;_8.setProperties({targetComponent:_1});_8.discardUpdate=false;var _9=_8.getItem("title");var _10=_1.title;if(!_10){_10=_1.name}
_9.setValue(_10);this.positionTitleEditor(_1,_2,_3,_4,_5);_8.show();_9.focusInItem();_9.delayCall("selectValue",[],100)}
,isc.A.positionTitleEditor=function isc_c_EditContext_positionTitleEditor(_1,_2,_3,_4,_5){if(_4==null)_4=_1.getPageTop();if(_5==null)_5=_1.height;if(_2==null)_2=_1.getPageLeft();if(_3==null)_3=_1.getVisibleWidth();var _6=this.titleEditor;var _7=_6.getItem("title");_7.setHeight(_5);_7.setWidth(_3);_6.setTop(_4);_6.setLeft(_2)}
,isc.A.deselect=function isc_c_EditContext_deselect(){isc.SelectionOutline.deselect();this.hideDragHandle()}
,isc.A.setEditMode=function isc_c_EditContext_setEditMode(_1){var _2=isc.SelectionOutline.getSelectedObject();if(_2==null)return;if(_1){this.setupDragProperties(_2);this.showSelectedObjectDragHandle();isc.SelectionOutline.showOutline()}else{this.resetDragProperties(_2);this.hideDragHandle();isc.SelectionOutline.hideOutline()}}
,isc.A.setupDragProperties=function isc_c_EditContext_setupDragProperties(_1){_1.saveToOriginalValues(["canDrag","canDrop","dragAppearance","dragStart","dragMove","dragStop","setDragTracker"]);_1.setProperties({canDrop:true,dragAppearance:"outline",dragStart:function(){return true},dragMove:function(){return true},setDragTracker:function(){isc.EH.setDragTracker("");return false},dragStop:function(){isc.EditContext.hideProxyCanvas();isc.EditContext.positionDragHandle()}})}
,isc.A.resetDragProperties=function isc_c_EditContext_resetDragProperties(_1){if(this.observer)this.observer.ignore(_1,"dragMove");_1.restoreFromOriginalValues(["canDrag","canDrop","dragAppearance","dragStart","dragMove","dragStop","setDragTracker"])}
,isc.A.selectCanvasOrFormItem=function isc_c_EditContext_selectCanvasOrFormItem(_1,_2){if(!isc.isA.Canvas(_1)&&!isc.isA.FormItem(_1)&&!_1.$73a){return}
if(isc.isA.Menu(_1)){return}
if(this.$70r)this.$70r.hide();var _3=isc.SelectionOutline.getSelectedObject();if(_3)this.resetDragProperties(_3);var _4,_5;if(_1.$73a){var _6=_1.type||_1._constructor;_5="["+_6+" "+(_1.name?"name:":"ID");_5+=_1.name||_1.ID;_5+="]"
_4=_1;_1=_1.$73a}
var _7=_4?_4.editContext:_1.editContext;if(!_7)return;var _8=_7.creator;isc.SelectionOutline.select(_1,false,!(_2&&_8.hideLabelWhenSelecting),_5);if(_4)_1=_4;if(_1.editingOn){this.setupDragProperties(_1);this.showSelectedObjectDragHandle();var _9=_1.editContext;if(_9.selectRecord){_9.deselectAllRecords();if(isc.isA.Canvas(_1)){if(isc.isA.SectionHeader(_1)||isc.isA.ImgSectionHeader(_1)){_9.selectRecord(_9.data.findById(_1.$42i))}else{_9.selectRecord(_9.data.findById(_1.ID))}}else{_9.selectRecord(_9.data.find({ID:_1.name}))}}
_9.creator.editComponent(_1.editNode,_1)}}
,isc.A.showSelectedObjectDragHandle=function isc_c_EditContext_showSelectedObjectDragHandle(){if(!this.$70r){var _1=this;this.$70r=isc.Img.create({src:"[SKIN]/../../ToolSkin/images/controls/dragHandle.gif",prompt:"Grab here to drag component",width:this.$72u,height:this.$72t,cursor:"move",backgroundColor:"white",opacity:80,canDrag:true,canDrop:true,isMouseTransparent:true,mouseDown:function(){this.dragIconOffsetX=isc.EH.getX()-
isc.EditContext.draggingObject.getPageLeft();this.dragIconOffsetY=isc.EH.getY()-
isc.EditContext.draggingObject.getPageTop();_1.$53r=true;this.Super("mouseDown",arguments)},mouseUp:function(){_1.$53r=false}})}
if(this.draggingObject){this.observer.ignore(this.draggingObject,"dragMove");this.observer.ignore(this.draggingObject,"dragStop");this.observer.ignore(this.draggingObject,"hide");this.observer.ignore(this.draggingObject,"destroy")}
var _2=isc.SelectionOutline.getSelectedObject();if(isc.isA.FormItem(_2)){if(!this.$70y){this.$70y=isc.FormItemProxyCanvas.create()}
this.$70y.delayCall("setFormItem",[_2]);_2=this.$70y}
this.$70r.setProperties({dragTarget:_2});isc.Timer.setTimeout("isc.EditContext.positionDragHandle()",0);if(!this.observer)this.observer=isc.Class.create();this.draggingObject=_2;this.observer.observe(this.draggingObject,"dragMove","isc.EditContext.positionDragHandle(true)");this.observer.observe(this.draggingObject,"dragStop","isc.EditContext.$53r = false");this.observer.observe(this.draggingObject,"hide","isc.EditContext.$70r.hide()");this.observer.observe(this.draggingObject,"destroy","isc.EditContext.$70r.hide()");this.$70r.show()}
,isc.A.hideProxyCanvas=function isc_c_EditContext_hideProxyCanvas(){if(this.$70y)this.$70y.hide()}
,isc.A.positionDragHandle=function isc_c_EditContext_positionDragHandle(_1){if(!this.$70r)return;var _2=this.draggingObject;if(_2.destroyed||_2.destroying){this.logWarn("target of dragHandle: "+isc.Log.echo(_2)+" is invalid: "+_2.destroyed?"already destroyed":"currently in destroy()");return}
var _3=_2.getVisibleHeight();if(_3<this.$72t*2){this.$70t=Math.round((_3-this.$70r.height)/2)-1}else{this.$70t=-1}
if(_2.isA("FormItemProxyCanvas")&&!this.$53r){_2.syncWithFormItemPosition()}
if(!_2)return;var _4=_2.getPageLeft()+this.$70s;if(_1){_4+=_2.getOffsetX()-this.$70r.dragIconOffsetX}
this.$70r.setPageLeft(_4);var _5=_2.getPageTop()+this.$70t;if(_1){_5+=_2.getOffsetY()-this.$70r.dragIconOffsetY}
this.$70r.setPageTop(_5);this.$70r.bringToFront()}
,isc.A.hideDragHandle=function isc_c_EditContext_hideDragHandle(){if(this.$70r)this.$70r.hide()}
,isc.A.showDragHandle=function isc_c_EditContext_showDragHandle(){if(this.$70r)this.$70r.show()}
,isc.A.hideAncestorDragDropLines=function isc_c_EditContext_hideAncestorDragDropLines(_1){while(_1&&_1.parentElement){if(_1.parentElement.hideDragLine)_1.parentElement.hideDragLine();if(_1.parentElement.hideDropLine)_1.parentElement.hideDropLine();_1=_1.parentElement;if(isc.isA.FormItem(_1))_1=_1.form}}
,isc.A.getSchemaInfo=function isc_c_EditContext_getSchemaInfo(_1){var _2={},_3=_1.liveObject;if(!_3)return _2;if(isc.isA.FormItem(_3)){if(_3.form&&_3.form.dataSource){var _4=_3.form;_2.dataSource=isc.DataSource.getDataSource(_4.dataSource).ID;_2.serviceName=_4.serviceName;_2.serviceNamespace=_4.serviceNamespace}else{_2.dataSource=_3.schemaDataSource;_2.serviceName=_3.serviceName;_2.serviceNamespace=_3.serviceNamespace}}else if(isc.isA.Canvas(_3)){_2.dataSource=isc.DataSource.getDataSource(_3.dataSource).ID;_2.serviceName=_3.serviceName;_2.serviceNamespace=_3.serviceNamespace}else{_2.dataSource=_3.schemaDataSource;_2.serviceName=_3.serviceName;_2.serviceNamespace=_3.serviceNamespace}
return _2}
,isc.A.clearSchemaProperties=function isc_c_EditContext_clearSchemaProperties(_1){if(_1&&_1.initData&&isc.isA.FormItem(_1.liveObject)){delete _1.initData.schemaDataSource;delete _1.initData.serviceName;delete _1.initData.serviceNamespace;var _2=_1.liveObject.form;if(_2&&_2.inputSchemaDataSource&&isc.DataSource.get(_2.inputSchemaDataSource).ID==_1.initData.inputSchemaDataSource&&_2.inputServiceName==_1.initData.inputServiceName&&_2.inputServiceNamespace==_1.initData.inputServiceNamespace)
{delete _1.initData.inputSchemaDataSource;delete _1.initData.inputServiceName;delete _1.initData.inputServiceNamespace}}}
,isc.A.serializeInitData=function isc_c_EditContext_serializeInitData(_1){if(_1==null)return null;if(!isc.isAn.Array(_1))_1=[_1];var _2=isc.SB.create();isc.Comm.omitXSI=true;for(var i=0;i<_1.length;i++){var _4=isc.DS.getNearestSchema(_1[i]);_2.append(_4.xmlSerialize(_1[i]),"\n\n")}
isc.Comm.omitXSI=null;return _2.toString()}
);isc.B._maxIndex=isc.C+16;isc.EditContext.addInterfaceMethods({addNode:function(_1,_2,_3,_4,_5){var _6=this.getEditNodeTree();if(_2==null)_2=this.getDefaultParent(_1);var _7=this.getLiveObject(_2);this.logInfo("addComponent will add newNode of type: "+_1.type+" to: "+this.echoLeaf(_7),"editing");if(_7.wrapChildNode){_2=_7.wrapChildNode(this,_1,_2,_3);if(!_2)return;_7=this.getLiveObject(_2)}
var _8=_4||isc.DS.getObjectField(_7,_1.type);var _9=isc.DS.getSchemaField(_7,_8);if(!_9){this.logWarn("can't addComponent: can't find a field in parent: "+_7+" for a new child of type: "+_1.type+", parent property:"+_8+", newNode is: "+this.echo(_1));return}
if(!_9.multiple){var _10=isc.DS.getChildObject(_7,_1.type,_4);if(_10){var _11=_6.getChildren(_2).find("ID",isc.DS.getAutoId(_10));this.logWarn("destroying existing child: "+this.echoLeaf(_10)+" in singular field: "+_8);_6.remove(_11);if(isc.isA.Class(_10)&&!isc.isA.DataSource(_10))_10.destroy()}}
var _12;if(_1.generatedType){_12=isc.addProperties({},_1.initData);this.addChildData(_12,_6.getChildren(_1))}else{_12=_1.liveObject}
if(!_5){var _13=isc.DS.addChildObject(_7,_1.type,_12,_3,_4);if(!_13){this.logWarn("addChildObject failed, returning");return}}
if(!_1.liveObject)_1.liveObject=isc.DS.getChildObject(_7,_1.type,isc.DS.getAutoId(_1.initData),_4);this.logDebug("for new node: "+this.echoLeaf(_1)+" liveObject is now: "+this.echoLeaf(_1.liveObject),"editing");if(_1.liveObject==null){this.logWarn("wasn't able to retrieve live object after adding node of type: "+_1.type+" to liveParent: "+_7+", does liveParent have an appropriate getter() method?")}
_6.add(_1,_2,_3);_6.openFolder(_1);this.logInfo("added node "+this.echoLeaf(_1)+" to EditTree at path: "+_6.getPath(_1)+" with live object: "+this.echoLeaf(_1.liveObject),"editing");if(this.nodeAdded)this.nodeAdded(_1);if(_1.liveObject.addedToEditContext)_1.liveObject.addedToEditContext(this,_1,_2,_3);return _1},addComponent:function(_1,_2,_3,_4,_5){return this.addNode(_1,_2,_3,_4,_5)},nodeAdded:function(_1){},getDefaultParent:isc.ClassFactory.TARGET_IMPLEMENTS,addFromPaletteNode:function(_1,_2){var _3=this.makeEditNode(_1,_2);return this.addNode(_3,_2)},makeEditNode:function(_1){var _2=this.getDefaultPalette();return _2.makeEditNode(_1)},getDefaultPalette:function(){if(this.defaultPalette)return this.defaultPalette;return(this.defaultPalette=isc.HiddenPalette.create())},getLiveObject:function(_1){var _2=this.getEditNodeTree();var _3=_2.getParent(_1);if(_3==null)return _1.liveObject;var _4=_3.liveObject;var _5=isc.DS.getChildObject(_4,_1.type,isc.DS.getAutoId(_1));if(_5)_1.liveObject=_5;return _1.liveObject},requestLiveObject:function(_1,_2,_3){var _4=this;if(_1.loadData&&!_1.isLoaded){_1.loadData(_1,function(_6){_6=_6||_1
_6.isLoaded=true;_6.dropped=_1.dropped;_4.fireCallback(_2,"node",[_6])},_3);return}
if(_1.wizardConstructor){this.logInfo("creating wizard with constructor: "+_1.wizardConstructor);var _5=isc.ClassFactory.newInstance(_1.wizardConstructor,_1.wizardDefaults);_5.getResults(_1,function(_6){if(!_6.liveObject){_6=_3.makeEditNode(_6)}
_4.fireCallback(_2,"node",[_6])},_3);return}
this.fireCallback(_2,"node",[_1])},getEditNodeTree:function(){if(!this.editNodeTree){var _1=this.rootComponent||{_constructor:"Object"},_2=isc.isA.Class(_1)?_1.Class:_1._constructor,_3=this.rootLiveObject||_1;var _4={type:_2,_constructor:_2,initData:_1,liveObject:_3};this.editNodeTree=isc.Tree.create({idField:"ID",root:_4,isFolder:function(){return true}})}
return this.editNodeTree},getEditNodeArray:function(){return this.getEditNodeTree().getAllNodes()},getEditComponents:function(){return this.getEditNodeArray()},canAddToParent:function(_1,_2){var _3=_1.liveObject;if(isc.isA.Class(_3)){return(_3.getObjectField(_2)!=null)}
return(isc.DS.getObjectField(_1,_2)!=null)},removeAll:function(){var _1=this.getEditNodeTree();var _2=_1.getChildren(_1.getRoot()).duplicate()
for(var i=0;i<_2.length;i++){this.removeNode(_2[i])}},destroyAll:function(){var _1=this.getEditNodeTree();var _2=_1.getChildren(_1.getRoot()).duplicate()
for(var i=0;i<_2.length;i++){this.destroyNode(_2[i])}},removeNode:function(_1,_2){var _3=this.getEditNodeTree();_3.remove(_1);if(_2)return;var _4=_3.getParent(_1),_5=this.getLiveObject(_4),_6=this.getLiveObject(_1);isc.DS.removeChildObject(_5,_1.type,_6)},removeComponent:function(_1,_2){return this.removeNode(_1,_2)},destroyNode:function(_1){var _2=this.getLiveObject(_1);this.removeNode(_1);if(_2.destroy)_2.destroy()},destroyComponent:function(_1){return this.destroyNode(_1)},getEditDataSource:function(_1){return isc.DataSource.getDataSource(_1.editDataSource||_1.Class||this.editDataSource)},$40l:function(_1){var _2=[];_2.addList(_1.baseEditFields);_2.addList(_1.editFields);for(var i=0;i<_2.length;i++){var _4=_2[i];if(_4.visible==null)_4.visible=true}
if(_2.length==0){_2=this.getEditDataSource(_1).getFields();_2=isc.getValues(_2)}
return _2},getEditFieldsList:function(_1){var _2=[],_3=this.$40l(_1);for(var i=0;i<_3.length;i++){var _5=_3[i];if(isc.isAn.Object(_5)){_2.add(_5.name)}else{_2.add(_5)}}
return _2},getEditFields:function(_1){var _2=this.$40l(_1);for(var i=0;i<_2.length;i++){var _4=_2[i];if(isc.isA.String(_4))_4={name:_4};if(_4.visible==null)_4.visible=true;_2[i]=_4}
return _2},serializeEditComponents:function(){var _1=this.getEditComponents(),_2=[];if(!_1)return[];for(var i=0;i<_1.length;i++){var _4=_1[i].liveObject,_5=_4.getUniqueProperties(),_6=this.getEditFieldsList(_4);_5._constructor=_4.Class;_5=isc.applyMask(_5,_6);_2.add(_5)}
return _2},enableEditing:function(_1){var _2=_1.liveObject;if(_2.setEditMode){_2.setEditMode(true,this,_1)}else{_2.editContext=this;_2.editNode=_1;_2.editingOn=true}},setNodeProperties:function(_1,_2){if(this.logIsDebugEnabled("editing")){this.logDebug("with editNode: "+this.echoLeaf(_1)+" applying properties: "+this.echo(_2),"editing")}
this.creator.setIsDirty(true);isc.addProperties(_1.initData,_2);_1.$71u=true;var _3=_1.liveObject;var _4=isc.DS.get(_1.type);if(_2.name!=null&&(isc.isA.FormItem(_3)||(_4&&(_4.inheritsSchema("ListGridField")||_4.inheritsSchema("DetailViewerField")))))
{var _5=this.data,_6=_5.getParent(_1),_7=_5.getChildren(_6).findIndex(_1);this.logInfo("using remove/re-add cycle to modify liveObject: "+isc.echoLeaf(_3)+" within parent node "+isc.echoLeaf(_6));this.removeComponent(_1);_1.name=_1.ID=_2.name;delete _2.name;this.addComponent(_1,_6,_7);_3=this.getLiveObject(_1)}
if(_1.initData.ID!=null)_1.ID=_1.initData.ID;if(_3.setEditableProperties){_3.setEditableProperties(_2);if(_3.markForRedraw)_3.markForRedraw();else if(_3.redraw)_3.redraw()}else{var _6=this.data.getParent(_1),_8=_6?_6.liveObject:null;if(_8&&_8.setChildEditableProperties)
{_8.setChildEditableProperties(_3,_2,_1,this)}else{isc.addProperties(_3,_2)}}
this.markForRedraw()},addWithWrapper:function(_1,_2){var _3=isc.addProperties({},this.wrapperFormDefaults),_4={type:this.wrapperFormDefaults._constructor,defaults:_3};if(_1.liveObject.schemaDataSource){var _5=_1.liveObject;_3.doNotUseDefaultBinding=true;_3.dataSource=_5.schemaDataSource;_3.serviceNamespace=_5.serviceNamespace;_3.serviceName=_5.serviceName}
var _6=this.makeEditNode(_4);this.addComponent(_6,_2);return this.addComponent(_1,_6)},wrapperFormDefaults:{_constructor:"DynamicForm"}});isc.ClassFactory.defineInterface("Palette");isc.Palette.addInterfaceMethods({makeEditNode:function(_1){return this.makeNewComponent(_1)},makeNewComponent:function(_1){if(!_1)_1=this.getDragData();if(isc.isAn.Array(_1))_1=_1[0];var _2=_1.className||_1.type;var _3={type:_2,_constructor:_2,title:_1.title,icon:_1.icon,iconSize:_1.iconSize,showDropIcon:_1.showDropIcon,useEditMask:_1.useEditMask,autoGen:_1.autoGen};if(isc.isAn.Object(_1.editNodeProperties)){for(var _4 in _1.editNodeProperties){_3[_4]=_1.editNodeProperties[_4]}}
if(_1.makeComponent){_3.liveObject=_1.makeComponent(_3);return _3}
var _5=_1.defaults;_3.ID=_1.ID||(_5?isc.DS.getAutoId(_5):null);var _6=true;if(_1.loadData){_3.loadData=_1.loadData}else if(_1.wizardConstructor){_3.wizardConstructor=_1.wizardConstructor;_3.wizardDefaults=_1.wizardDefaults}else if(_1.liveObject){var _7=_1.liveObject;if(isc.isA.String(_7))_7=window[_7];_3.liveObject=_7}else{_3=this.createLiveObject(_1,_3);_6=false}
if(_6)_3.defaults=_1.defaults;return _3},generateNames:true,typeCount:{},getNextAutoId:function(_1){if(_1==null)_1="Object";var _2;this.typeCount[_1]=this.typeCount[_1]||0;while(window[(_2=_1+this.typeCount[_1]++)]!=null){}
return _2},createLiveObject:function(_1,_2){var _3=_1.className||_1.type,_4=isc.ClassFactory.getClass(_3),_5=isc.DS.getNearestSchema(_3),_6={},_7=(_5?_5.shouldCreateStandalone():true),_8=_1.initData||_1.defaults||{};if(_4&&_4.isA("Canvas"))_6.autoDraw=false;if(_1.initData&&_1.initData.title){_6.title=_1.initData.title}
if(this.generateNames){var _9=_2.ID=_2.ID||_8[_5.getAutoIdField()]||this.getNextAutoId(_3);_6[_5.getAutoIdField()]=_9;if(_5&&_5.getField("title")&&!isc.isA.FormItem(_4)&&!_6.title){_6.title=_9}}
_6=_2.initData=_2.defaults=isc.addProperties(_6,this.componentDefaults,_1.defaults);_6._constructor=_3;var _10;if(_4&&_7){_10=isc.ClassFactory.newInstance(_6)}else{_2.generatedType=true;_10=isc.shallowClone(_6)}
_2.liveObject=_10;this.logInfo("palette created component, type: "+_3+", ID: "+_9+(this.logIsDebugEnabled("editing")?", initData: "+this.echo(_6):"")+", liveObject: "+this.echoLeaf(_10),"editing");return _2}});isc.defineClass("HiddenPalette","Class","Palette");if(isc.TreeGrid){isc.defineClass("TreePalette","TreeGrid","Palette");isc.A=isc.TreePalette.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canDragRecordsOut=true;isc.B.push(isc.A.recordDoubleClick=function isc_TreePalette_recordDoubleClick(){var _1=this.defaultEditContext;if(_1){if(isc.isA.String(_1))_1=this.creator[_1];if(isc.isAn.EditContext(_1)){var _2=this.makeEditNode(this.getDragData());if(_2){if(_1.getDefaultParent(_2,true)==null){isc.warn("No default parent can accept a component of this type")}else{_1.addNode(_2);isc.EditContext.selectCanvasOrFormItem(_2.liveObject,true)}}}}}
,isc.A.transferDragData=function isc_TreePalette_transferDragData(_1){return[this.makeEditNode(this.getDragData())]}
);isc.B._maxIndex=isc.C+2}
if(isc.ListGrid){isc.defineClass("ListPalette","ListGrid","Palette");isc.A=isc.ListPalette.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canDragRecordsOut=true;isc.A.defaultFields=[{name:"title",title:"Title"}];isc.B.push(isc.A.recordDoubleClick=function isc_ListPalette_recordDoubleClick(){var _1=this.defaultEditContext;if(_1){if(isc.isA.String(_1))_1=isc.Canvas.getById(_1);if(isc.isAn.EditContext(_1)){_1.addNode(this.makeEditNode(this.getDragData()))}}}
,isc.A.transferDragData=function isc_ListPalette_transferDragData(){return[this.makeEditNode(this.getDragData())]}
);isc.B._maxIndex=isc.C+2}
if(isc.TileGrid){isc.defineClass("TilePalette","TileGrid","Palette");isc.A=isc.TilePalette.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canDragRecordsOut=true;isc.A.defaultFields=[{name:"title",title:"Title"}];isc.B.push(isc.A.recordDoubleClick=function isc_TilePalette_recordDoubleClick(){var _1=this.defaultEditContext;if(_1){if(isc.isA.String(_1))_1=isc.Canvas.getById(_1);if(isc.isAn.EditContext(_1)){_1.addNode(this.makeEditNode(this.getDragData()))}}}
,isc.A.transferDragData=function isc_TilePalette_transferDragData(){return[this.makeEditNode(this.getDragData())]}
);isc.B._maxIndex=isc.C+2}
if(isc.Menu){isc.defineClass("MenuPalette","Menu","Palette");isc.A=isc.MenuPalette.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canDragRecordsOut=true;isc.A.selectionType="single";isc.B.push(isc.A.itemClick=function isc_MenuPalette_itemClick(_1){var _2=this.defaultEditContext;if(_2){if(isc.isA.String(_2))_2=isc.Canvas.getById(_2);if(isc.isAn.EditContext(_2)){_2.addNode(this.makeEditNode(this.getDragData()))}}}
,isc.A.transferDragData=function isc_MenuPalette_transferDragData(){return[this.makeEditNode(this.getDragData())]}
);isc.B._maxIndex=isc.C+2}
if(!isc.DataSource.get("EditPane")){isc.DataSource.create({ID:"EditPane",Contructor:"EditPane",fields:[{name:"children",type:"Canvas",multiple:true}]})}
isc.defineClass("EditPane","Canvas","EditContext");isc.A=isc.EditPane.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canAcceptDrop=true;isc.A.contextMenu={autoDraw:false,data:[{title:"Clear",click:"target.removeAll()"}]};isc.A.editingOn=true;isc.A.persistCoordinates=true;isc.A.canDrag=true;isc.A.dragAppearance="none";isc.A.overflow="hidden";isc.A.selectedComponents=[];isc.A.canMultiSelect=true;isc.A.outlineBorderStyle="2px dashed red";isc.B.push(isc.A.initWidget=function isc_EditPane_initWidget(){this.rootLiveObject=this;this.rootComponent={_constructor:"EditPane"};this.Super("initWidget",arguments)}
,isc.A.getObjectField=function isc_EditPane_getObjectField(_1){var _2=isc.ClassFactory.getClass(_1);if(isc.isA.Canvas(_2)){return"children"}else{return null}}
,isc.A.drop=function isc_EditPane_drop(){var _1=isc.EH.dragTarget;if(!_1.isA("Palette"))return this.Super("drop",arguments);var _2=_1.transferDragData(),_3=(isc.isAn.Array(_2)?_2[0]:_2);if(!_3)return false;var _4=this;this.requestLiveObject(_3,function(_3){if(_3)_4.addNodeAtCursor(_3)},_1)
return isc.EH.STOP_BUBBLING}
,isc.A.nodeAdded=function isc_EditPane_nodeAdded(_1){if(this.creator&&this.creator.editingOn)this.enableEditing(_1);if(_1.useEditMask)_1.liveObject.showEditMask()}
,isc.A.addNodeAtCursor=function isc_EditPane_addNodeAtCursor(_1){this.addNode(_1);var _2=_1.liveObject;_2.moveTo(this.getOffsetX(),this.getOffsetY())}
,isc.A.getDefaultParent=function isc_EditPane_getDefaultParent(_1,_2){return this.getEditNodeTree().getRoot()}
,isc.A.removeChild=function isc_EditPane_removeChild(_1,_2){this.Super("removeChild",arguments);var _3=this.getEditNodeArray().find("liveObject",_1);if(_3){this.removeNode(_3,true)}
this.selectedComponents.remove(_1)}
,isc.A.removeSelection=function isc_EditPane_removeSelection(_1){if(this.selectedComponents.length>0){while(this.selectedComponents.length>0){this.selectedComponents[0].destroy()}}else{_1.destroy()}}
,isc.A.click=function isc_EditPane_click(){isc.Canvas.hideResizeThumbs()}
,isc.A.setEditMode=function isc_EditPane_setEditMode(_1){if(_1==null)_1=true;if(this.editingOn==_1)return;this.editingOn=_1;var _2=this.getEditNodeArray().getProperty("liveObject");_2.map("setEditMode",_1,this)}
,isc.A.childResized=function isc_EditPane_childResized(_1){var _2=this.Super("childResized",arguments);this.saveCoordinates(_1);return _2}
,isc.A.childMoved=function isc_EditPane_childMoved(_1,_2,_3){var _4=this.Super("childMoved",arguments);this.saveCoordinates(_1);var _5=this.selectedComponents;if(_5.length>0&&_5.contains(_1)){for(var i=0;i<_5.length;i++){if(_5[i]!=_1){_5[i].moveBy(_2,_3)}}}
return _4}
,isc.A.saveCoordinates=function isc_EditPane_saveCoordinates(_1){if(!this.persistCoordinates)return;var _2=this.getEditNodeArray().find("liveObject",_1);if(!_2)return;_2.initData=isc.addProperties(_2.initData,{left:_1.getLeft(),top:_1.getTop(),width:_1.getWidth(),height:_1.getHeight()})}
,isc.A.getSaveData=function isc_EditPane_getSaveData(){var _1=this.getEditNodeTree(),_2=_1.getChildren(_1.getRoot()),_3=[];for(var i=0;i<_2.length;i++){var _5=_2[i],_6=_5.liveObject;var _7={type:_5.type,defaults:_5.defaults};if(_6.getSaveData)_7=_6.getSaveData(_7);_3.add(_7)}
return _3}
,isc.A.mouseDown=function isc_EditPane_mouseDown(){if(!this.editingOn||!this.canMultiSelect||isc.EH.getTarget()!=this)return;var _1=isc.EH.getTarget();if(this.selector==null){this.selector=isc.Canvas.create({autoDraw:false,keepInParentRect:true,left:isc.EH.getX(),top:isc.EH.getY(),redrawOnResize:false,overflow:"hidden",border:"1px solid blue"});this.addChild(this.selector)}
this.startX=this.getOffsetX();this.startY=this.getOffsetY();this.resizeSelector();this.selector.show();this.updateCurrentSelection()}
,isc.A.dragMove=function isc_EditPane_dragMove(){if(this.selector)this.resizeSelector()}
,isc.A.mouseUp=function isc_EditPane_mouseUp(){if(this.selector)this.selector.hide()}
,isc.A.dragStop=function isc_EditPane_dragStop(){if(this.selector)this.selector.hide()}
,isc.A.setOutline=function isc_EditPane_setOutline(_1){if(!_1)return;if(!isc.isAn.Array(_1))_1=[_1];for(var i=0;i<_1.length;i++){_1[i]._eventMask.setBorder(this.outlineBorderStyle)}}
,isc.A.clearOutline=function isc_EditPane_clearOutline(_1){if(!_1)return;if(!isc.isAn.Array(_1))_1=[_1];for(var i=0;i<_1.length;i++){_1[i]._eventMask.setBorder("none")}}
,isc.A.updateCurrentSelection=function isc_EditPane_updateCurrentSelection(){if(!this.children)return;var _1=this.selectedComponents;this.selectedComponents=[];for(var i=0;i<this.children.length;i++){var _3=this.children[i];if(this.selector.intersects(_3)){_3=this.deriveSelectedComponent(_3);if(_3&&!this.selectedComponents.contains(_3)){this.selectedComponents.add(_3)}}}
this.setOutline(this.selectedComponents);_1.removeList(this.selectedComponents);this.clearOutline(_1);var _4=this.selectedComponents.getProperty("ID");window.status=_4.length?"Current Selection: "+_4:""}
,isc.A.deriveSelectedComponent=function isc_EditPane_deriveSelectedComponent(_1){if(_1.masterElement)return this.deriveSelectedComponent(_1.masterElement);if(!_1.parentElement||_1.parentElement==this){if(_1._eventMask)return _1;return null}
return this.deriveSelectedComponent(_1.parentElement)}
,isc.A.resizeSelector=function isc_EditPane_resizeSelector(){var x=this.getOffsetX(),y=this.getOffsetY();if(this.selector.keepInParentRect){if(x<0)x=0;var _3=this.selector.parentElement.getVisibleHeight();if(y>_3)y=_3}
this.selector.resizeTo(Math.abs(x-this.startX),Math.abs(y-this.startY));if(x<this.startX)this.selector.setLeft(x);else this.selector.setLeft(this.startX);if(y<this.startY)this.selector.setTop(y);else this.selector.setTop(this.startY);this.updateCurrentSelection()}
,isc.A.getSelectedComponents=function isc_EditPane_getSelectedComponents(){return this.selectedComponents.duplicate()}
);isc.B._maxIndex=isc.C+24;if(isc.TreeGrid){isc.ClassFactory.defineClass("EditTree","TreeGrid","EditContext");isc.A=isc.EditTree.getPrototype();isc.A.canDragRecordsOut=false;isc.A.canAcceptDroppedRecords=true;isc.A.canReorderRecords=true;isc.A.fields=[{name:"ID",title:"ID",width:"*"},{name:"type",title:"Type",width:"*"}];isc.A.selectionType=isc.Selection.SINGLE;isc.A.autoShowParents=true;isc.A=isc.EditTree.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.initWidget=function isc_EditTree_initWidget(){this.Super("initWidget",arguments);this.setData(this.getEditNodeTree())}
,isc.A.willAcceptDrop=function isc_EditTree_willAcceptDrop(){if(!this.Super("willAcceptDrop",arguments))return false;var _1=this.getEventRow(),_2=this.getDropFolder(),_3=this.ns.EH.dragTarget.getDragData();if(_3==null)return false;if(isc.isAn.Array(_3)){if(_3.length==0)return false;_3=_3[0]}
if(_2==null)_2=this.data.getRoot();var _4=_3.className||_3.type;this.logInfo("checking dragType: "+_4+" against dropLiveObject: "+_2.liveObject,"editing");return this.canAddToParent(_2,_4)}
,isc.A.folderDrop=function isc_EditTree_folderDrop(_1,_2,_3,_4){if(_4!=this&&!_4.isA("Palette")){return this.Super("folderDrop",arguments)}
if(_4!=this){_1=_4.transferDragData()}
var _5=(isc.isAn.Array(_1)?_1[0]:_1);_5.dropped=true;this.logInfo("sourceWidget is a Palette, dropped node of type: "+_5.type," editing");var _6=this;this.requestLiveObject(_5,function(_9){if(_9==null)return;if(_4==_6){var _7=this.data.getParent(_5);if(_2==_7){var _8=this.data.getChildren(_7).indexOf(_5);if(_8!=null&&_8<=_3)_3--}
_6.removeComponent(_5)}
_6.addNode(_9,_2,_3)},_4)}
,isc.A.nodeAdded=function isc_EditTree_nodeAdded(_1){this.selection.selectSingle(_1);if(this.autoShowParents)this.showParents(_1);if(this.creator.editingOn)this.enableEditing(_1)}
,isc.A.getDefaultParent=function isc_EditTree_getDefaultParent(_1,_2){var _3=_1.className||_1.type,_4=this.getSelectedRecord();while(_4&&!this.canAddToParent(_4,_3))_4=this.data.getParent(_4);var _5=this.data.getRoot()
if(_2){if(!_4&&this.canAddToParent(_5,_3))return _5;return _4}
return _4||_5}
,isc.A.showParents=function isc_EditTree_showParents(_1){var _2=this.data.getParents(_1),_3=_2.findAll("type","Tab");if(_3){for(var i=0;i<_3.length;i++){var _5=_3[i],_6=this.data.getParent(_5),_7=this.getLiveObject(_5),_8=this.getLiveObject(_6);_8.selectTab(_7)}}}
,isc.A.serializeComponents=function isc_EditTree_serializeComponents(_1,_2){var _3=_2?[this.data.root]:this.data.getChildren(this.data.root).duplicate();return this.serializeEditNodes(_3,_1)}
,isc.A.serializeEditNodes=function isc_EditTree_serializeEditNodes(_1,_2){for(var i=0;i<_1.length;i++){var _4=_1[i]=isc.addProperties({},_1[i]),_5=isc.ClassFactory.getClass(_4.type),_6=_4.initData=isc.addProperties({},_4.initData);if(_5&&_5.isA("Canvas")&&_6&&_6.visibility!=isc.Canvas.HIDDEN&&_6.autoDraw!==false)
{_6.autoDraw=true}}
this.serverless=_2;this.initDataBlocks=[];this.map("getSerializeableTree",_1);this.serverless=null;var _7=isc.EditContext.serializeInitData(this.initDataBlocks);return _7}
,isc.A.getSerializeableTree=function isc_EditTree_getSerializeableTree(_1,_2){var _3=_1.type,_4=isc.addProperties({},_1.initData);var _5=isc.ClassFactory.getClass(_3);this.logInfo("node: "+this.echoLeaf(_1)+" with type: "+_3);if(_5&&_5.isA("DataSource")&&!_5.isA("MockDataSource")){if(this.initDataBlocks){var _6=this.initDataBlocks.find("ID",_4.ID)||this.initDataBlocks.find("loadID",_4.ID);if(_6&&_6.$schemaId=="DataSource")return}
if(!this.serverless){_4={_constructor:"DataSource",$schemaId:"DataSource",loadID:_4.ID}}else{var _7=_1.liveObject;_4=_7.getSerializeableFields();_4._constructor=_7.Class;_4.$schemaId="DataSource"}}
this.convertActions(_1,_4,_5);var _8=this.data.getChildren(_1);if(!_8){if(this.initDataBlocks)this.initDataBlocks.add(_4);return}
this.addChildData(_4,_8);if(_2)return _4;if(this.initDataBlocks)this.initDataBlocks.add(_4)}
,isc.A.addChildData=function isc_EditTree_addChildData(_1,_2){var _3=isc.DS.get(_1._constructor);for(var i=0;i<_2.length;i++){var _5=_2[i],_6=_5.initData._constructor,_7=isc.addProperties({},_5.initData),_8=_7.parentProperty||_3.getObjectField(_6),_9=_3.getField(_8);this.logInfo("serializing: child of type: "+_6+" goes in parent field: "+_8,"editing");if((isc.isA.Canvas(_5.liveObject)&&!_5.liveObject._generated)||isc.isA.DataSource(_5.liveObject))
{_7="ref:"+_7.ID;this.getSerializeableTree(_5)}else{_7=this.getSerializeableTree(_5,true)}
var _10=_1[_8];if(_9.multiple){if(!_10)_10=_1[_8]=[];_10.add(_7)}else{_1[_8]=_7}}}
,isc.A.convertActions=function isc_EditTree_convertActions(_1,_2,_3){for(var _4 in _2){var _5=_2[_4];if(!isc.isAn.Object(_5)||isc.isA.StringMethod(_5))continue;var _6;if(_3&&_3.getField)_6=_3.getField(_4).type;if(_6&&(_6!="StringMethod"))continue;var _7=_1.liveObject[_4],_8=_7?_7.iscAction:null,_9;if(_8)_9=true;if(_9)_2[_4]=isc.StringMethod.create({value:_5})}}
);isc.B._maxIndex=isc.C+11}
isc.defineClass("FormItemProxyCanvas","Canvas");isc.A=isc.FormItemProxyCanvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.autoDraw=false;isc.A.canDrop=true;isc.B.push(isc.A.setFormItem=function isc_FormItemProxyCanvas_setFormItem(_1){this.formItem=_1;this.syncWithFormItemPosition();this.sendToBack();this.show()}
,isc.A.syncWithFormItemPosition=function isc_FormItemProxyCanvas_syncWithFormItemPosition(){if(!this.formItem||!this.formItem.form)return;this.setPageLeft(this.formItem.getPageLeft());this.setPageTop(this.formItem.getPageTop());this.setWidth(this.formItem.getVisibleWidth());this.setHeight(this.formItem.getVisibleHeight())}
);isc.B._maxIndex=isc.C+2;if(isc.DynamicForm){isc.defineClass("PropertySheet","DynamicForm");isc.A=isc.PropertySheet.getPrototype();isc.A.autoChildItems=true;isc.A.browserSpellCheck=false;isc.A.autoChildDefaults={cellStyle:"propSheetValue",titleStyle:"propSheetTitle",showHint:false};isc.A.GroupItemDefaults={cellStyle:null};isc.A.ExpressionItemDefaults={width:"*",height:18,showActionIcon:true};isc.A.ActionMenuItemDefaults={width:"*",height:18};isc.A.SelectItemDefaults={height:20,width:"*"};isc.A.DateItemDefaults={width:"*"};isc.A.TextItemDefaults={width:"*",height:20};isc.A.StaticTextItemDefaults={width:"*",height:20,textBoxStyle:"propSheetField"};isc.A.ColorItemDefaults={width:"*",height:16,pickerIconHeight:16,pickerIconWidth:16,pickerIconSrc:"[SKIN]/DynamicForm/PropSheet_ColorPicker_icon.png",textBoxStyle:"propSheetField"};isc.A.HeaderItemDefaults={cellStyle:"propSheetHeading"};isc.A.TextAreaItemProperties={width:"*"};isc.A.CheckboxItemDefaults={showTitle:true,showLabel:false,getTitleHTML:function(){if(this[this.form.titleField]!=null)return this[this.form.titleField];return this[this.form.fieldIdProperty]}};isc.A.SectionItemDefaults={cellStyle:"propSheetSectionHeaderCell"};isc.A.titleAlign="left";isc.A.titleWidth=120;isc.A.cellSpacing=0;isc.A.cellPadding=0;isc.A.backgroundColor="white";isc.A.requiredTitlePrefix="<span style='color:green'>";isc.A.requiredTitleSuffix="</span>";isc.A.titleSuffix="";isc.A.clipItemTitles=true}
if(isc.ListGrid&&isc.DynamicForm){isc.defineClass("ListEditor",isc.Layout);isc.A=isc.ListEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.vertical=false;isc.A.gridConstructor=isc.ListGrid;isc.A.gridDefaults={editEvent:"click",listEndEditAction:"next",autoParent:"gridLayout",selectionType:isc.Selection.SINGLE,recordClick:"this.creator.recordClick(record)",editorEnter:"if (this.creator.moreButton) this.creator.moreButton.enable()",selectionChanged:function(){if(this.anySelected()&&this.creator.moreButton){this.creator.moreButton.enable()}},contextMenu:{data:[{title:"Remove",click:"target.creator.removeRecord()"}]}};isc.A.gridButtonsDefaults={_constructor:isc.HLayout,autoParent:"gridLayout",height:10,width:10,layoutMargin:6,membersMargin:10,overflow:isc.Canvas.VISIBLE};isc.A.newButtonTitle="New";isc.A.newButtonDefaults={_constructor:isc.AutoFitButton,autoParent:"gridButtons",click:"this.creator.newRecord()"};isc.A.moreButtonTitle="More..";isc.A.moreButtonDefaults={_constructor:isc.AutoFitButton,autoParent:"gridButtons",click:"this.creator.editMore()",disabled:true};isc.A.removeButtonTitle="Remove";isc.A.removeButtonDefaults={_constructor:isc.AutoFitButton,autoParent:"gridButtons",click:"this.creator.removeRecord()"};isc.A.formDefaults={_constructor:isc.DynamicForm,autoParent:"formLayout",overflow:isc.Canvas.AUTO};isc.A.formButtonsDefaults={_constructor:isc.HLayout,autoParent:"formLayout",height:10,width:10,layoutMargin:6,membersMargin:10,overflow:isc.Canvas.VISIBLE};isc.A.saveButtonTitle="Save";isc.A.saveButtonDefaults={_constructor:isc.AutoFitButton,autoParent:"formButtons",click:"this.creator.saveRecord();"};isc.A.cancelButtonTitle="Cancel";isc.A.cancelButtonDefaults={_constructor:isc.AutoFitButton,autoParent:"formButtons",click:"this.creator.cancelChanges()"};isc.A.resetButtonTitle="Reset";isc.A.resetButtonDefaults={_constructor:isc.AutoFitButton,autoParent:"formButtons",click:"this.creator.form.resetValues()"};isc.A.gridLayoutDefaults={_constructor:isc.VLayout};isc.A.gridButtonsOrientation="left";isc.A.formLayoutDefaults={_constructor:isc.VLayout,autoFocus:true};isc.A.animateMembers=true;isc.A.membersMargin=10;isc.A.confirmLoseChangesMessage="Discard changes?";isc.A.formGroup=["formLayout","form","formButtons","saveButton","cancelButton","resetButton"];isc.A.gridButtonsGroup=["gridButtons","newButton","moreButton"];isc.B.push(isc.A.draw=function isc_ListEditor_draw(){if(isc.$cv)arguments.$cw=this;if(!this.readyToDraw())return this;return this.Super("draw",arguments)}
,isc.A.initWidget=function isc_ListEditor_initWidget(){this.Super("initWidget",arguments);if(!this.inlineEdit)this.showMoreButton=this.showMoreButton||false;this.addAutoChild("gridLayout");this.addAutoChild("grid",{_constructor:this.gridConstructor});this.addAutoChildren(this.gridButtonsGroup);this.addAutoChildren(this.formGroup)}
,isc.A.configureAutoChild=function isc_ListEditor_configureAutoChild(_1,_2){if(isc.isA.Button(_1))_1.title=this[_2+"Title"];if(_1==this.grid){_1.dataSource=this.dataSource;_1.fields=this.fields;_1.saveLocally=this.saveLocally;_1.canEdit=this.inlineEdit}
if(this.gridButtonsOrientation==isc.Canvas.RIGHT){if(_1==this.gridLayout)_1.vertical=false;if(_1==this.formLayout)_1.vertical=false;if(_1==this.gridButtons)_1.vertical=true;if(_1==this.formButtons)_1.vertical=true}
if(_1==this.form){_1.dataSource=this.dataSource;_1.fields=this.formFields}
if(this.inlineEdit){if(_1==this.formLayout)_1.visibility=isc.Canvas.HIDDEN}else{if(_1==this.gridLayout)_1.showResizeBar=true}}
,isc.A.setDataSource=function isc_ListEditor_setDataSource(_1,_2){this.dataSource=_1||this.dataSource;if(this.grid!=null){this.grid.setDataSource(_1,_2);this.form.setDataSource(_1,_2)}}
,isc.A.setData=function isc_ListEditor_setData(_1){if(_1==null)_1=[];if(_1.dataSource)this.setDataSource(_1.dataSource);if(this.grid!=null){this.grid.setData(_1);this.form.clearValues()}else{isc.addProperties(this.gridDefaults,this.gridProperties||{},{data:_1})}}
,isc.A.getData=function isc_ListEditor_getData(){if(this.inlineEdit)this.grid.endEditing();return this.grid.getData()}
,isc.A.cancelChanges=function isc_ListEditor_cancelChanges(){this.form.clearValues();this.showList()}
,isc.A.showList=function isc_ListEditor_showList(){if(this.inlineEdit){this.formLayout.animateHide({effect:"wipe",startFrom:"R"});this.gridLayout.animateShow({effect:"wipe",startFrom:"R"})}}
,isc.A.showForm=function isc_ListEditor_showForm(){if(this.inlineEdit){this.gridLayout.animateHide({effect:"wipe",startFrom:"R"});this.formLayout.animateShow({effect:"wipe",startFrom:"R"})}}
,isc.A.recordClick=function isc_ListEditor_recordClick(_1){if(this.inlineEdit)return;var _2=this;var _3=function(_4){if(_4){_2.currentRecord=_1;if(!_2.inlineEdit)_2.form.editRecord(_1);_2.form.setValues(isc.addProperties({},_2.grid.getSelectedRecord()))}}
if(!this.form.valuesHaveChanged())_3(true);else this.confirmLoseChanges(_3)}
,isc.A.getEditRecord=function isc_ListEditor_getEditRecord(){var _1=this.grid.getEditRow();if(_1!=null){return this.grid.getEditedRecord(_1)}else{return isc.addProperties({},this.grid.getSelectedRecord())}}
,isc.A.editMore=function isc_ListEditor_editMore(){this.currentRecord=this.getEditRecord();this.showForm();this.form.setValues(this.currentRecord)}
,isc.A.newRecord=function isc_ListEditor_newRecord(){if(this.inlineEdit)return this.grid.startEditingNew();var _1=this;var _2=function(_3){if(_3){_1.grid.deselectAllRecords();_1.showForm();_1.form.editNewRecord()}}
if(!this.form.valuesHaveChanged())_2(true);else this.confirmLoseChanges(_2)}
,isc.A.removeRecord=function isc_ListEditor_removeRecord(){this.form.clearValues();this.grid.removeSelectedData()}
,isc.A.saveRecord=function isc_ListEditor_saveRecord(){if(!this.form.validate())return false;var _1=this.form.getValues();this.showList();if(this.form.saveOperationType=="add"){this.grid.addData(_1)}else{if(this.inlineEdit&&this.grid.getEditRow()!=null){var _2=this.grid.getEditRow();if(this.grid.data[_2]!=null)this.grid.updateData(_1)
else this.grid.setEditValues(_2,_1)}else{this.grid.updateData(_1)}}
return true}
,isc.A.confirmLoseChanges=function isc_ListEditor_confirmLoseChanges(_1){isc.confirm(this.confirmLoseChangesMessage,_1)}
,isc.A.validate=function isc_ListEditor_validate(){if(this.form.isVisible()&&this.form.valuesHaveChanged()){return this.form.validate()}
return true}
);isc.B._maxIndex=isc.C+17}
isc.ClassFactory.defineClass("ViewLoader",isc.Label);isc.A=isc.ViewLoader.getPrototype();isc.A.loadingMessage="Loading View...&nbsp;${loadingImage}";isc.A.align=isc.Canvas.CENTER;isc.A.allowContentAndChildren=true;isc.A.httpMethod="GET";isc.A.useSimpleHttp=true;isc.A.transformXML=true;isc.A.overflow="hidden";isc.A=isc.ViewLoader.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.initWidget=function isc_ViewLoader_initWidget(){this.Super(this.$oc);if(this.placeholder)this.addChild(this.placeholder);else this.contents=this.getLoadingMessage()}
,isc.A.draw=function isc_ViewLoader_draw(){if(!this.readyToDraw())return this;this.Super("draw",arguments);if(this.view){this.addChild(this.view);this.view.show()}else if(this.viewURL&&!this.loadingView()){this.setViewURL()}
return this}
,isc.A.layoutChildren=function isc_ViewLoader_layoutChildren(){this.Super("layoutChildren",arguments);var _1=this.children;if(!_1||_1.length==0)return;var _2=this.children[0],_3=this.getWidth(),_4=this.getHeight();if(_2.$pn!=null)_3=null;if(_2.$po!=null)_4=null;_2.setRect(0,0,_3,_4)}
,isc.A.destroy=function isc_ViewLoader_destroy(){if(this.placeholder)this.placeholder.destroy();if(this.view)this.view.destroy();this.Super("destroy",arguments)}
,isc.A.setPlaceholder=function isc_ViewLoader_setPlaceholder(_1){if(this.placeholder)this.placeholder.destroy();this.placeholder=_1;this.addChild(_1);this.placeholder.sendToBack()}
,isc.A.setViewURL=function isc_ViewLoader_setViewURL(_1,_2,_3){if(_1!=null)this.viewURL=_1;_1=this.viewURL;if(this.placeholder){this.placeholder.show();this.placeholder.bringToFront()}
if(this.view!=null){this.view.hide();this.setContents(this.getLoadingMessage())}
var _4={},_5=this.useSimpleHttp,_6=this.httpMethod,_7=false;if(!isc.rpc.xmlHttpRequestAvailable()){this.logInfo("XMLHttpRequest not available, using frames comm and expecting RPCResponse");_4={};_5=false;_6="POST";_7=false}
var _8=isc.addProperties({showPrompt:false,actionURL:this.viewURL,httpMethod:_6,useSimpleHttp:_5,bypassCache:!this.allowCaching,params:isc.addProperties(_4,this.viewURLParams,_2)},this.viewRPCProperties,_3,{evalResult:_7,suppressAutoDraw:true,willHandleError:true,callback:"if(window."+this.getID()+")"+this.getID()+".$40p(rpcRequest, rpcResponse, data)"});if(!_8.evalVars)_8.evalVars={};_8.evalVars.viewLoader=this;this.$40t=isc.rpc.sendProxied(_8,true).transactionNum}
,isc.A.loadingView=function isc_ViewLoader_loadingView(){return this.$40t!=null}
,isc.A.$40p=function isc_ViewLoader__loadViewReply(_1,_2,_3){if(_1.transactionNum!=this.$40t){return}
delete this.$40t;this.$40q=false;if(_2.status!=isc.RPCResponse.STATUS_SUCCESS){if(this.handleError(_1,_2)===false)return}
try{if(_1.actionURL.endsWith(".xml")&&this.transformXML){var _4=isc.Canvas._canvasList;var _5=_4.length;isc.xml.toComponents(_3);if(!this.$40q){for(var i=_4.length;i>=_5;i--){var _7=_4[i];if(_7!=null&&isc.isA.Canvas(_7)&&_7.parentElement==null&&_7.masterElement==null)
{this.setView(this.transformView(_7));break}}}
this.$40r()}else{var _8=this;isc.Class.globalEvalWithCapture(_3,function(_9,_10){isc.Log.logWarn("firing the callback from global eval with...");isc.Log.logWarn('viewLoader is:'+_8);if(_10){_8.handleError(_1,_2,_10)}else{_8.$40r(_9)}},_1.evalVars)}}catch(e){this.handleError(_1,_2,e)}}
,isc.A.$40r=function isc_ViewLoader__loadViewReplyComplete(_1){if(!this.$40q&&_1){for(var i=_1.length;i>=0;i--){var _3=_1[i];var _4=window[_3];if(_4&&isc.isA.Canvas(_4)&&_4.parentElement==null&&_4.masterElement==null)
{this.setView(this.transformView(_4));break}}}
if(!this.$40q){this.logWarn("setView() not explicitly called by loaded view and could"+" not be autodetected for view: "+this.getID())}
this.viewLoaded(this.view)}
,isc.A.transformView=function isc_ViewLoader_transformView(_1){return _1}
,isc.A.handleError=function isc_ViewLoader_handleError(_1,_2,_3){this.logWarn("ViewLoader received bad response:\n"+isc.echo(_2.data));this.setView(isc.Label.create({contents:_3?_3.toString():_2.data}));return false}
,isc.A.setView=function isc_ViewLoader_setView(_1){if(_1!=null&&_1==this.view)return;this.$40q=true;this.setContents("&nbsp;");if(this.view)this.view.destroy();this.view=_1;if(_1==null)return;this.addChild(_1,null,false);this.layoutChildren();_1.draw();this.logInfo("showing view: "+_1);if(this.placeholder)this.placeholder.hide();this.contents="&nbsp;"}
,isc.A.getView=function isc_ViewLoader_getView(){return this.view}
,isc.A.viewLoaded=function isc_ViewLoader_viewLoaded(_1){}
,isc.A.getLoadingMessage=function isc_ViewLoader_getLoadingMessage(){return this.loadingMessage==null?"&nbsp;":this.loadingMessage.evalDynamicString(this,{loadingImage:this.imgHTML(isc.Canvas.loadingImageSrc,isc.Canvas.loadingImageSize,isc.Canvas.loadingImageSize)})}
);isc.B._maxIndex=isc.C+15;isc.ClassFactory.defineClass("HTMLFlow","Canvas");isc.A=isc.HTMLFlow;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$49v=0;isc.A.$49w=[];isc.B.push(isc.A.executeScript=function isc_c_HTMLFlow_executeScript(_1,_2,_3){this.$49w[this.$49v]={callback:_2,displayErrors:_3};this.$49v++;this.getScript(_1,"isc.HTMLFlow.$49x("+this.$49v+",htmlFragments, scripts);")}
,isc.A.$49x=function isc_c_HTMLFlow__completeExecuteScript(_1,_2,_3){var _4=this.$49w[_1];delete this.$49w[_1];var _5=true;for(var i=0;i<this.$49w.length;i++){if(this.$49w[i]!=null){_5=false;break}}
if(_5)this.$49v=0;isc.Class.globalEvalWithCapture(_3,_4.callback,null,_4.displayErrors)}
,isc.A.getScript=function isc_c_HTMLFlow_getScript(_1,_2,_3,_4){var _5=_1;var _6,_7,_8,_9;while((_6=_1.match(/<!--/i))!=null){_7=_1.match(/-->/i);if(_7==null||(_7.index<_6.index)){this.logWarn('HTMLFlow content contains an opening comment tag "<!--"'+' with no closing tag "-->", or vice versa. We recommend you review this '+'HTML (original HTML follows):\n'+_5);if(_7){_8=_7.index;_9=_8+3}else{_8=_6.index;_9=_8+4}}else{_8=_6.index;_9=_7.index+3}
_1=_1.slice(0,_8)+_1.slice(_9,_1.length)}
var _10=[];var _11=[];var _12=[];var _13=_1;_1=null;var _14;while((_14=_13.match(/(<script([^>]*)?>)/i))!=null){var _15=_14[1];_12.add(_13.slice(0,_14.index));_10.add(null);_11.add(null);_13=_13.slice(_14.index+_15.length,_13.length)
var _16=_13.match(/<\/script>/i),_17=_13.match(/(<script([^>]*)?>)/i);if(_16==null||(_17&&(_16.index>_17.index))){this.logWarn("HTMLFlow content contains an opening <script ...> tag "+"with no closing tag, or vice versa. Stripping out this tag:"+_15);continue}
var _18=_13.slice(0,_16.index);_13=_13.slice(_16.index+9,_13.length);var _19=(_15.match(/<script\s*(language|type)/i)==null)||(_15.match(/<script\s*(language|type)\s*=["']?[^'"]*(javascript|ecmascript|jscript)[^'"]*["']?/i)
!=null);if(!this.shouldLoadScript(_15))continue;if(_19){var _20;if(_20=_15.match(/src=('|")?([^'"> ]*)/i)){_11.add(_20[2]);_10.add(null)}else{if(!isc.isA.String(_18)||isc.isAn.emptyString(_18))continue;_10.add(_18);_11.add(null)}
_12.add(null)}else{this.logWarn("html to be evaluated contains non-JS script tags - these will be"+" ignored.  Tag: "+_15)}}
if(_12.length==0)
_12=[_13];else
_12.push(_13);if(_11.length>0&&!_4){if(isc.RPCManager){var _21=false;for(var i=0;i<_11.length;i++){if(_11[i]==null){continue}
isc.RPCManager.sendRequest({actionURL:_11[i],serverOutputAsString:true,httpMethod:"GET",clientContext:{scriptIndex:i,scripts:_10,scriptIncludes:_11,callback:_2,htmlFragments:(_3?_12:[_5])},callback:"isc.HTMLFlow.loadedRemoteScriptBlock(data, rpcResponse.clientContext)"});_21=true}
if(_21)return}else{this.logWarn("html contains <script src=> blocks with the "+"following target URLs: "+_11+" If you want "+"these to be dynamically loaded, please include the "+"DataBinding module or include the contents of "+"these files in inline <script> blocks.")}}
var _23=_10.join("\n");this.fireCallback(_2,"htmlFragments,scripts",[_3?_12:[_5],_10])}
,isc.A.shouldLoadScript=function isc_c_HTMLFlow_shouldLoadScript(_1){var _2=_1.match(/ISC_([^.]*)\.js/i);if(_2&&isc["module_"+_2[1]])return false;var _2=_1.match(/load_skin\.js/i);if(_2)return false;return true}
,isc.A.loadedRemoteScriptBlock=function isc_c_HTMLFlow_loadedRemoteScriptBlock(_1,_2){var _3=_2.scriptIndex,_4=_2.scripts,_5=_2.scriptIncludes;_4[_3]=_1;delete _5[_3];for(var i=0;i<_5.length;i++){if(_5[i]!=null)return}
this.fireCallback(_2.callback,"htmlFragments,scripts",[_2.htmlFragments,_4])}
);isc.B._maxIndex=isc.C+5;isc.A=isc.HTMLFlow.getPrototype();isc.A.defaultWidth=200;isc.A.defaultHeight=1;isc.A.allowContentAndChildren=true;isc.A.cursor="auto";isc.A.loadingMessage="&nbsp;${loadingImage}";isc.A.httpMethod="GET";isc.A.useSimpleHttp=true;isc.A.evalScriptBlocks=true;isc.A.captureSCComponents=true;isc.A=isc.HTMLFlow.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$525=0;isc.B.push(isc.A.initWidget=function isc_HTMLFlow_initWidget(){if(this.contentsType=="page"&&this.overflow=="visible")this.setOverflow("auto")}
,isc.A.draw=function isc_HTMLFlow_draw(){if(!this.readyToDraw())return this;this.Super("draw",arguments);var _1;if(this.containsIFrame())return this;else if(this.canSelectText===_1)this.canSelectText=true;if(this.contentsURL&&!(this.$533==this.contentsURL||this.loadingContent()))
{this.setContentsURL()}
return this}
,isc.A.setContentsURL=function isc_HTMLFlow_setContentsURL(_1,_2,_3){if(this.contentsType=="page"){return this.invokeSuper(isc.HTMLFlow,"setContentsURL",_1,_2)}
if(_1!=null)this.contentsURL=_1;if(this.loadingMessage){var _4=this.loadingMessage==null?"&nbsp;":this.loadingMessage.evalDynamicString(this,{loadingImage:this.imgHTML(isc.Canvas.loadingImageSrc,isc.Canvas.loadingImageSize,isc.Canvas.loadingImageSize)});this.setContents(_4)}
var _5=isc.addProperties({},this.contentsURLParams,_2),_6=this.useSimpleHttp,_7=this.httpMethod,_8=true;var _9=isc.addProperties({showPrompt:false,actionURL:this.contentsURL,httpMethod:_7,useSimpleHttp:_6,bypassCache:!this.allowCaching,params:_5},this.contentRPCProperties,_3,{willHandleError:true,serverOutputAsString:_8,callback:this.getID()+".$40s(rpcRequest, rpcResponse)"});this.$40t=isc.rpc.sendProxied(_9,true).transactionNum}
,isc.A.loadingContent=function isc_HTMLFlow_loadingContent(){return this.$40t!=null}
,isc.A.$40s=function isc_HTMLFlow__loadContentReply(_1,_2){var _3=_2.data;if(_2.status!=isc.RPCResponse.STATUS_SUCCESS){if(this.handleError(_1,_2)===false)return}
if(_1.transactionNum!=this.$40t){return}
isc.HTMLFlow.getScript(_3,{target:this,methodName:"$49y"},true,!this.evalScriptBlocks)}
,isc.A.$526=function isc_HTMLFlow__captureSCComponentsRelPos(_1){if(!_1.parentElement)this.addChild(_1);var _2="HTMLFlow"+this.$525++;_1.htmlElement=_2;var _3='<DIV id="'+_2+'"></DIV>';return _3}
,isc.A.$527=function isc_HTMLFlow__captureSCComponentsAbsPos(_1){if(!_1.parentElement)this.addChild(_1);return null}
,isc.A.$49y=function isc_HTMLFlow__setContentsAndExecute(_1,_2){this.setContents(this.transformHTML(_1.join("")));if(_1.length>1){if(this.evalScriptBlocks){if(this.isDirty())this.redraw();if(this.captureSCComponents){this.$528=isc.Canvas.autoDraw;isc.setAutoDraw(false)}
for(var i=0;i<_1.length;i++){var _4=null;var _5=this;if(this.captureSCComponents)_4=function(_8,_9){if(!_8.length)return;_1[i]=_8.map(function(_10){var _6=window[_10];if(!_6||!isc.isA.Canvas(_6))return null;if(_6.position==isc.Canvas.RELATIVE)
return _5.$526(_6);else return _5.$527(_6)}).join("")};if(_2[i])isc.Class.globalEvalWithCapture(_2[i],_4)}
if(this.captureSCComponents){this.setContents(this.transformHTML(_1.join("")));if(this.$528){isc.setAutoDraw(true);for(var _7 in window)
if(isc.isA.Canvas(_7)&&_7.autoDraw)
_7.markForRedraw()}}}
else{this.logWarn("html returned by server appears to contain <script> blocks.  "+"If you want these to be evaluated, you must set evalScriptBlocks:true.")}}
this.$40u()}
,isc.A.handleError=function isc_HTMLFlow_handleError(_1,_2){this.logWarn(_2.data)}
,isc.A.$40u=function isc_HTMLFlow__loadContentsReplyComplete(){this.$533=this.contentsURL;this.$40t=null;this.contentLoaded()}
,isc.A.transformHTML=function isc_HTMLFlow_transformHTML(_1){return _1}
,isc.A.contentLoaded=function isc_HTMLFlow_contentLoaded(){}
,isc.A.modifyContent=function isc_HTMLFlow_modifyContent(){}
);isc.B._maxIndex=isc.C+13;isc.HTMLFlow.registerStringMethods({contentLoaded:""})
isc.defineClass("HTMLPane",isc.HTMLFlow);isc.A=isc.HTMLPane.getPrototype();isc.A.overflow=isc.Canvas.AUTO;isc.A.defaultHeight=200;isc.defineClass("WSDataSource","DataSource");isc.A=isc.WSDataSource.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.serviceNamespace="urn:operations.smartclient.com";isc.A.operationBindings=[{operationType:"fetch",wsOperation:"fetch",recordXPath:"//data/*"},{operationType:"add",wsOperation:"add",recordXPath:"//data/*"},{operationType:"remove",wsOperation:"remove",recordXPath:"//data/*"},{operationType:"update",wsOperation:"update",recordXPath:"//data/*"}];isc.B.push(isc.A.transformRequest=function isc_WSDataSource_transformRequest(_1){var _2={dataSource:_1.dataSource,operationType:_1.operationType,data:_1.data};if(_1.startRow!=null){_2.startRow=_1.startRow;_2.endRow=_1.endRow}
if(_1.textMatchStyle!=null)_2.textMatchStyle=_1.textMatchStyle;if(_1.operationId!=null)_2.operationId=_1.operationId;if(_1.sortBy!=null)_2.sortBy=_1.sortBy;return _2}
,isc.A.transformResponse=function isc_WSDataSource_transformResponse(_1,_2,_3){if(!_3||!_3.selectString)return;_1.status=_3.selectString("//status");if(isc.isA.String(_1.status)){var _4=isc.DSResponse[_1.status];if(_1.status==null){this.logWarn("Unable to map response code: "+_4+" to a DSResponse code, setting status to DSResponse.STATUS_FAILURE.");_4=isc.DSResponse.STATUS_FAILURE;_1.data=_3.selectString("//data")}else{_1.status=_4}}
if(_1.status==isc.DSResponse.STATUS_VALIDATION_ERROR){var _5=_3.selectNodes("//errors/*");_1.errors=isc.xml.toJS(_5,null,this)}
_1.totalRows=_3.selectNumber("//totalRows");_1.startRow=_3.selectNumber("//startRow");_1.endRow=_3.selectNumber("//endRow")}
);isc.B._maxIndex=isc.C+2;isc.defineClass("RestDataSource","DataSource");isc.A=isc.RestDataSource.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.serverType="generic";isc.A.dataFormat="xml";isc.A.xmlRecordXPath="/response/data/*";isc.A.jsonRecordXPath="/response/data";isc.A.prettyPrintJSON=true;isc.A.dataFormatParamName="isc_dataFormat";isc.A.jsonPrefix="<SCRIPT>//'\"]]>>isc_JSONResponseStart>>";isc.A.jsonSuffix="//isc_JSONResponseEnd";isc.A.operationBindings=[{operationType:"fetch",dataProtocol:"getParams"},{operationType:"add",dataProtocol:"postParams"},{operationType:"remove",dataProtocol:"postParams"},{operationType:"update",dataProtocol:"postParams"}];isc.A.sendMetaData=true;isc.A.metaDataPrefix="_";isc.B.push(isc.A.init=function isc_RestDataSource_init(){this.recordXPath=this.recordXPath||(this.dataFormat=="xml"?this.xmlRecordXPath:this.jsonRecordXPath);return this.Super("init",arguments)}
,isc.A.getDataURL=function isc_RestDataSource_getDataURL(_1){var _2=_1.operationType;if(_2=="fetch"&&this.fetchDataURL!=null)
return this.fetchDataURL;if(_2=="update"&&this.updateDataURL!=null)
return this.updateDataURL;if(_2=="add"&&this.addDataURL!=null)
return this.addDataURL;if(_2=="remove"&&this.removeDataURL!=null)
return this.removeDataURL;return this.Super("getDataURL",arguments)}
,isc.A.getDataProtocol=function isc_RestDataSource_getDataProtocol(_1){var _2=this.Super("getDataProtocol",arguments);if(_2=="postXML")_2="postMessage";return _2}
,isc.A.transformRequest=function isc_RestDataSource_transformRequest(_1){var _2=this.getDataProtocol(_1);_1.isRestRequest=!(this.disableQueuing||this.clientOnly);_1.dataFormat=this.dataFormat;if(_2=="postMessage"){if(_1.params==null){_1.params={}}
_1.params[this.dataFormatParamName]=this.dataFormat;var _3={dataSource:this.getID()};if(_1.operationType!=null)_3.operationType=_1.operationType;if(_1.operationId!=null)_3.operationId=_1.operationId;if(_1.startRow!=null)_3.startRow=_1.startRow;if(_1.endRow!=null)_3.endRow=_1.endRow;if(_1.sortBy!=null)_3.sortBy=_1.sortBy;if(_1.textMatchStyle!=null)_3.textMatchStyle=_1.textMatchStyle;if(this.sendClientContext)_3.clientContext=_1.clientContext;if(_1.componentId)_3.componentId=_1.componentId;if(isc.DataSource.get("$89s")==null){isc.DataSource.create({ID:"$89s",fields:[{name:"_constructor",xmlAttribute:false},{name:"criteria",multiple:true,type:"$89s",childTagName:"criterion"},{name:"oldValues"}]})}
var _4=isc.DataSource.create({fields:[{name:"data",type:"$89s"},{name:"oldValues"}]});if(this.autoConvertRelativeDates==true){if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Calling convertRelativeDates from getServiceInputs "+"- data is\n\n"+isc.echoFull(_1.data))}
var _5=this.convertRelativeDates(_1.data);if(this.logIsInfoEnabled("relativeDates")){this.logInfo("Called convertRelativeDates from getServiceInputs "+"- data is\n\n"+isc.echoFull(_5))}
_1.data=_5}
_3.data=_1.data;_3.oldValues=_1.oldValues;if(!_1.contentType){_1.contentType=(this.dataFormat=="json"?"application/json":"text/xml")}
var _6;_1.$92u=_1.data;if(this.dataFormat=="json"){if(_3.data!=null)_3.data=this.serializeFields(_3.data);if(_3.oldValues!=null)_3.oldValues=this.serializeFields(_3.oldValues);var _7={prettyPrint:this.prettyPrintJSON};_6=isc.JSON.encode(_3,_7)}else{var _8={ignoreConstructor:true,schema:this};_6=_4.xmlSerialize(_3,_8,null,"request")}
_4.destroy();return _6}else{if(_2!="getParams"&&_2!="postParams"){this.logWarn("RestDataSource operation:"+_1.operationID+", of type "+_1.operationType+" has dataProtocol specified as '"+_2+"'. Supported protocols are 'postParams', 'getParams' "+"and 'postMessage' only. Defaulting to 'getParams'.");_1.dataProtocol='getParams'}
var _3=isc.addProperties({},_1.data,_1.params);if(this.sendMetaData){if(!this.parameterNameMap){var _9={};_9[this.metaDataPrefix+"operationType"]="operationType";_9[this.metaDataPrefix+"operationId"]="operationId";_9[this.metaDataPrefix+"startRow"]="startRow";_9[this.metaDataPrefix+"endRow"]="endRow";_9[this.metaDataPrefix+"sortBy"]="sortBy";_9[this.metaDataPrefix+"textMatchStyle"]="textMatchStyle";_9[this.metaDataPrefix+"oldValues"]="oldValues";_9[this.metaDataPrefix+"componentId"]="componentId";this.parameterNameMap=_9}
for(var _10 in this.parameterNameMap){var _11=_1[this.parameterNameMap[_10]];if(_11!=null)_3[_10]=_11}
_3[this.metaDataPrefix+"dataSource"]=this.getID();_3["isc_metaDataPrefix"]=this.metaDataPrefix}
_3[this.dataFormatParamName]=this.dataFormat;return _3}}
,isc.A.getUpdatedData=function isc_RestDataSource_getUpdatedData(_1,_2,_3){var _4=_2?_2.data:null;if(_3&&(!_4||isc.isAn.emptyString(_4)||(isc.isA.Array(_4)&&_4.length==0))&&_2.status==0&&this.getDataProtocol(_1)=="postMessage")
{this.logInfo("dsResponse for successful operation of type "+_1.operationType+" did not return updated record[s]. Using submitted request data to update"+" ResultSet cache.","ResultSet");var _5={},_6=_1.originalData;if(_6&&isc.isAn.Object(_6)){if(_1.operationType=="update"){_5=isc.addProperties({},_1.oldValues);if(isc.isAn.Array(_6)){_5=isc.addProperties(_5,_6[0])}else{_5=isc.addProperties(_5,_6)}
_5=[_5]}else{if(!isc.isAn.Array(_6))_6=[_6];_5=[];for(var i=0;i<_6.length;i++){_5[i]=isc.addProperties({},_6[i])}}
if(this.logIsDebugEnabled("ResultSet")){this.logDebug("Submitted data to be integrated into the cache:"+this.echoAll(_5),"ResultSet")}}
return _5}else{return this.Super("getUpdatedData",arguments)}}
,isc.A.getValidStatus=function isc_RestDataSource_getValidStatus(_1){if(isc.isA.String(_1)){if(parseInt(_1)==_1)_1=parseInt(_1);else{_1=isc.DSResponse[_1];if(_1==null){this.logWarn("Unable to map response code: "+_1+" to a DSResponse code, setting status to DSResponse.STATUS_FAILURE.");_1=isc.DSResponse.STATUS_FAILURE}}}
if(_1==null)_1=isc.DSResponse.STATUS_SUCCESS;return _1}
,isc.A.transformResponse=function isc_RestDataSource_transformResponse(_1,_2,_3){if(_1.status<0||!_3){_1.queueStatus=-1;return _1}
if(this.dataFormat=="json"){var _4=_3.response||{};_1.status=this.getValidStatus(_4.status);_1.queueStatus=this.getValidStatus(_4.queueStatus);if(_1.status==isc.DSResponse.STATUS_VALIDATION_ERROR){var _5=_4.errors;if(isc.isAn.Array(_5)){if(_5.length>1){this.logWarn("server returned an array of errors - ignoring all but the first one")}
_5=_5[0]}
_1.errors=_5}else if(_1.status<0){_1.data=_4.data}
if(_4.totalRows!=null)_1.totalRows=_4.totalRows;if(_4.startRow!=null)_1.startRow=_4.startRow;if(_4.endRow!=null)_1.endRow=_4.endRow}else{if(_2.clientOnly)return _1;_1.status=this.getValidStatus(_3.selectString("//status"));_1.queueStatus=this.getValidStatus(_3.selectString("//queueStatus"));if(_1.status==isc.DSResponse.STATUS_VALIDATION_ERROR){var _5=_3.selectNodes("//errors");_5=isc.xml.toJS(_5);if(_5.length>1){this.logWarn("server returned an array of errors - ignoring all but the first one")}
_5=_5[0];_1.errors=_5}else if(_1.status<0){_1.data=_3.selectString("//data")}
var _6=_3.selectNumber("//totalRows");if(_6!=null)_1.totalRows=_6;var _7=_3.selectNumber("//startRow");if(_7!=null)_1.startRow=_7;var _8=_3.selectNumber("//endRow");if(_8!=null)_1.endRow=_8}
return _1}
,isc.A.shouldUseTestDataFetch=function isc_RestDataSource_shouldUseTestDataFetch(){return this.clientOnly==true&&this.cacheAllData!=true&&this.testFileName!=null}
,isc.A.hasTestData=function isc_RestDataSource_hasTestData(){return this.testFileName!=null}
,isc.A.getClientOnlyResponse=function isc_RestDataSource_getClientOnlyResponse(_1,_2){if(_1.$92u)_1.data=_1.$92u;var _3=this.Super("getClientOnlyResponse",arguments);if(!isc.isAn.Array(_3.data))_3.data=[_3.data];return _3}
);isc.B._maxIndex=isc.C+10;isc.DataSource.create({
Constructor:"DataSource",
ID:"DataSource",
addGlobalId:"false",
fields:{
ID:{
type:"string",
xmlAttribute:"true"
},
inheritsFrom:{
title:"Superclass",
type:"string"
},
useParentFieldOrder:{
type:"boolean"
},
useLocalFieldsOnly:{
type:"boolean"
},
restrictToParentFields:{
type:"boolean"
},
dataFormat:{
title:"DataFormat",
type:"string",
xmlAttribute:"true",
valueMap:{
custom:"Custom Binding",
iscServer:"ISC Java Server",
json:"JSON Web Service",
xml:"XML / WSDL Web Service"
}
},
noAutoFetch:{
type:"boolean",
xmlAttribute:"true"
},
serverType:{
title:"Server Type",
type:"string",
xmlAttribute:"true",
valueMap:{
custom:"Custom Server Binding",
sql:"ISC Server SQL Connectors"
}
},
callbackParam:{
title:"Callback Parameter",
type:"string",
xmlAttribute:"true"
},
requestProperties:{
type:"Object"
},
fields:{
childTagName:"field",
multiple:"true",
propertiesOnly:"true",
type:"DataSourceField"
},
addGlobalId:{
title:"Add Global ID",
type:"boolean"
},
showPrompt:{
type:"boolean"
},
dataSourceVersion:{
title:"DataSource Version",
type:"number",
visibility:"internal",
xmlAttribute:"true"
},
dbName:{
title:"Database Name",
type:"string",
xmlAttribute:"true"
},
schema:{
title:"Schema",
type:"string",
xmlAttribute:"true"
},
tableName:{
title:"Table Name",
type:"string",
xmlAttribute:"true"
},
serverObject:{
type:"ServerObject"
},
operationBindings:{
multiple:"true",
type:"OperationBinding"
},
serviceNamespace:{
type:"string",
xmlAttribute:"true"
},
dataURL:{
type:"string",
xmlAttribute:"true"
},
dataProtocol:{
type:"string",
xmlAttribute:"true"
},
dataTransport:{
type:"string",
xmlAttribute:"true"
},
defaultParams:{
type:"Object"
},
soapAction:{
type:"string"
},
jsonPrefix:{
type:"string"
},
jsonSuffix:{
type:"string"
},
messageTemplate:{
type:"string"
},
defaultCriteria:{
propertiesOnly:"true",
type:"Object",
visibility:"internal"
},
tagName:{
type:"string",
visibility:"xmlBinding"
},
recordXPath:{
type:"XPath"
},
recordName:{
type:"string"
},
xmlNamespaces:{
type:"Object"
},
dropExtraFields:{
type:"boolean"
},
schemaNamespace:{
type:"string",
visibility:"internal",
xmlAttribute:"true"
},
mustQualify:{
type:"boolean",
visibility:"internal"
},
xsdSimpleContent:{
type:"boolean",
visibility:"internal"
},
xsdAnyElement:{
type:"boolean",
visibility:"internal"
},
xsdAbstract:{
type:"boolean",
visibility:"internal"
},
title:{
title:"Title",
type:"string"
},
titleField:{
title:"Title Field",
type:"string"
},
pluralTitle:{
title:"Plural Title",
type:"string"
},
clientOnly:{
title:"Client Only",
type:"boolean",
xmlAttribute:"true"
},
testFileName:{
title:"Test File Name",
type:"URL",
xmlAttribute:"true"
},
testData:{
multiple:"true",
type:"Object"
},
types:{
multiple:"true",
propertiesOnly:"true",
type:"DataSourceField",
uniqueProperty:"ID",
visibility:"internal"
},
groups:{
multiple:"true",
type:"string",
visibility:"internal"
},
methods:{
multiple:"true",
type:"MethodDeclaration",
visibility:"internal"
},
showSuperClassActions:{
type:"boolean"
},
createStandalone:{
type:"boolean"
},
useFlatFields:{
type:"boolean"
},
showLocalFieldsOnly:{
type:"boolean",
xmlAttribute:"true"
},
globalNamespaces:{
type:"Object"
},
autoDeriveSchema:{
type:"boolean",
xmlAttribute:"true"
},
useLocalValidators:{
type:"boolean"
},
autoDeriveTitles:{
type:"boolean"
},
qualifyColumnNames:{
type:"boolean",
xmlAttribute:"true"
},
validateRelatedRecords:{
type:"boolean"
},
requiresAuthentication:{
type:"boolean"
},
requiresRoles:{
type:"boolean"
},
requires:{
type:"string"
},
beanClassName:{
type:"string",
xmlAttribute:"true"
},
autoJoinTransactions:{
type:"boolean",
xmlAttribute:"true"
},
sparseUpdates:{
type:"boolean"
},
noNullUpdates:{
type:"boolean"
},
canExport:{
type:"boolean"
}
}
})
isc.DataSource.create({
ID:"DataSourceField",
addGlobalId:"false",
fields:{
name:{
basic:"true",
primaryKey:"true",
required:"true",
title:"Name",
type:"string",
xmlAttribute:"true"
},
type:{
basic:"true",
title:"Type",
type:"string",
xmlAttribute:"true"
},
disabled:{
title:"Disabled",
type:"boolean"
},
idAllowed:{
title:"ID Allowed",
type:"boolean",
xmlAttribute:"true"
},
required:{
title:"Required",
type:"boolean",
xmlAttribute:"true"
},
valueMap:{
type:"ValueMap"
},
validators:{
multiple:"true",
propertiesOnly:"true",
type:"Validator"
},
length:{
title:"Length",
type:"integer",
xmlAttribute:"true"
},
xmlRequired:{
type:"boolean",
visibility:"internal"
},
xmlMaxOccurs:{
type:"string",
visibility:"internal"
},
xmlMinOccurs:{
type:"integer",
visibility:"internal"
},
xmlNonEmpty:{
type:"boolean",
visibility:"internal"
},
xsElementRef:{
type:"boolean",
visibility:"internal"
},
canHide:{
title:"User can hide",
type:"boolean"
},
xmlAttribute:{
type:"boolean",
visibility:"internal"
},
mustQualify:{
type:"boolean",
visibility:"internal"
},
valueXPath:{
title:"Value XPath",
type:"XPath",
xmlAttribute:"true"
},
childrenProperty:{
type:"boolean"
},
title:{
title:"Title",
type:"string",
xmlAttribute:"true"
},
detail:{
title:"Detail",
type:"boolean",
xmlAttribute:"true"
},
canEdit:{
title:"Can Edit",
type:"boolean",
xmlAttribute:"true"
},
canSave:{
title:"Can Save",
type:"boolean",
xmlAttribute:"true"
},
canView:{
title:"Can View",
type:"boolean",
xmlAttribute:"true"
},
inapplicable:{
inapplicable:"true",
title:"Inapplicable",
type:"boolean"
},
advanced:{
inapplicable:"true",
title:"Advanced",
type:"boolean"
},
visibility:{
inapplicable:"true",
title:"Visibility",
type:"string"
},
hidden:{
inapplicable:"true",
title:"Hidden",
type:"boolean",
xmlAttribute:"true"
},
primaryKey:{
title:"Is Primary Key",
type:"boolean",
xmlAttribute:"true"
},
foreignKey:{
title:"Foreign Key",
type:"string",
xmlAttribute:"true"
},
rootValue:{
title:"Tree Root Value",
type:"string",
xmlAttribute:"true"
},
showFileInline:{
type:"boolean",
xmlAttribute:"true"
},
nativeName:{
hidden:"true",
title:"Native Name",
type:"string"
},
fieldName:{
hidden:"true",
title:"Field Name",
type:"string"
},
fields:{
childTagName:"field",
hidden:"true",
multiple:"true",
propertiesOnly:"true",
type:"DataSourceField",
uniqueProperty:"name"
},
multiple:{
type:"boolean",
xmlAttribute:"true"
},
validateEachItem:{
type:"boolean",
xmlAttribute:"true"
},
pickListFields:{
multiple:"true",
type:"Object"
},
canFilter:{
type:"boolean",
xmlAttribute:"true"
},
ignore:{
type:"boolean"
},
unknownType:{
type:"boolean",
xmlAttribute:"true"
},
canSortClientOnly:{
type:"boolean",
xmlAttribute:"true"
},
childTagName:{
type:"string",
xmlAttribute:"true"
},
basic:{
type:"boolean"
},
maxFileSize:{
type:"integer"
},
frozen:{
title:"Frozen",
type:"boolean",
xmlAttribute:"true"
},
canExport:{
type:"boolean",
xmlAttribute:"true"
},
sqlStorageStrategy:{
type:"string",
xmlAttribute:"true"
},
encodeInResponse:{
type:"boolean",
xmlAttribute:"true"
}
}
})
isc.DataSource.create({
ID:"Validator",
addGlobalId:"false",
fields:{
type:{
type:"string"
},
name:{
type:"string"
},
description:{
type:"string"
},
stopIfFalse:{
type:"boolean"
},
stopOnError:{
type:"boolean"
},
validateOnChange:{
type:"boolean"
},
clientOnly:{
type:"boolean"
},
errorMessage:{
type:"string"
},
max:{
type:"float"
},
min:{
type:"float"
},
exclusive:{
type:"boolean"
},
mask:{
type:"regexp"
},
transformTo:{
type:"regexp"
},
precision:{
type:"integer"
},
expression:{
type:"string"
},
otherField:{
type:"string"
},
list:{
multiple:"true",
type:"text"
},
valueMap:{
type:"ValueMap"
},
substring:{
type:"text"
},
operator:{
type:"text"
},
count:{
type:"integer"
},
applyWhen:{
type:"AdvancedCriteria"
},
dependentFields:{
multiple:"true",
type:"string"
},
serverCondition:{
type:"string"
},
serverObject:{
type:"ServerObject"
},
fieldName:{
type:"string"
},
triggerEvent:{
type:"string"
},
message:{
type:"string"
},
severity:{
type:"string"
},
displayMode:{
type:"string"
},
duration:{
type:"integer"
},
formula:{
type:"string"
},
formulaVars:{
type:"ValueMap"
}
}
})
isc.DataSource.create({
Constructor:"SimpleType",
ID:"SimpleType",
addGlobalId:false,
inheritsFrom:"DataSourceField",
fields:[
{
name:"inheritsFrom",
type:"string"
},
{
name:"editorType",
type:"string"
}
]
})
isc.DataSource.create({
Constructor:"XSComplexType",
ID:"XSComplexType",
addGlobalId:false,
inheritsFrom:"DataSource"
})
isc.DataSource.create({
Constructor:"XSElement",
ID:"XSElement",
addGlobalId:false,
inheritsFrom:"DataSource"
})
isc.DataSource.create({
Constructor:"SchemaSet",
ID:"SchemaSet",
addGlobalId:false,
fields:[
{
name:"schemaNamespace",
type:"url"
},
{
multiple:true,
name:"schemaImports",
type:"Object"
},
{
name:"qualifyAll",
type:"boolean"
},
{
multiple:true,
name:"schema",
type:"DataSource"
}
]
})
isc.DataSource.create({
Constructor:"WSDLMessage",
ID:"WSDLMessage",
addGlobalId:false,
inheritsFrom:"DataSource"
})
isc.DataSource.create({
Constructor:"WebService",
ID:"WebService",
addGlobalId:false,
fields:[
{
name:"location",
type:"url"
},
{
name:"targetNamespace",
type:"url"
},
{
multiple:true,
name:"schemaImports",
type:"Object"
},
{
multiple:true,
name:"wsdlImports",
type:"Object"
},
{
multiple:true,
name:"operations",
type:"WebServiceOperation"
},
{
multiple:true,
name:"portTypes",
type:"Object"
},
{
multiple:true,
name:"bindings",
type:"Object"
},
{
multiple:true,
name:"messages",
type:"WSDLMessage"
},
{
name:"globalNamespaces",
type:"Object"
}
]
})
isc.DataSource.create({
ID:"WebServiceOperation",
addGlobalId:false,
fields:[
{
name:"name",
required:true,
title:"Operation Name"
},
{
name:"soapAction",
title:"SOAPAction Header"
},
{
name:"inputMessage",
title:"Input Message"
},
{
name:"outputMessage",
title:"Output Message"
},
{
multiple:true,
name:"inputHeaders",
type:"WSOperationHeader"
},
{
multiple:true,
name:"outputHeaders",
type:"WSOperationHeader"
}
]
})
isc.DataSource.create({
ID:"WSOperationHeader",
addGlobalId:false,
fields:[
{
name:"encoding"
},
{
name:"message"
},
{
name:"part"
}
]
})
isc.defineClass("Operators","Class");isc.A=isc.Operators;isc.A.equalsTitle="equals";isc.A.notEqualTitle="not equal";isc.A.iEqualsTitle="equals (ignore case)";isc.A.iNotEqualTitle="not equal (ignore case)";isc.A.greaterThanTitle="greater than";isc.A.lessThanTitle="less than";isc.A.greaterOrEqualTitle="greater than or equal to";isc.A.lessOrEqualTitle="less than or equal to";isc.A.betweenTitle="between (match case)";isc.A.iBetweenTitle="between";isc.A.betweenInclusiveTitle="between (inclusive, match case)";isc.A.iBetweenInclusiveTitle="between (inclusive)";isc.A.iContainsTitle="contains";isc.A.iStartsWithTitle="starts with";isc.A.iEndsWithTitle="ends with";isc.A.containsTitle="contains (match case)";isc.A.startsWithTitle="starts with (match case)";isc.A.endsWithTitle="ends with (match case)";isc.A.iNotContainsTitle="does not contain";isc.A.iNotStartsWithTitle="does not start with";isc.A.iNotEndsWithTitle="does not end with";isc.A.notContainsTitle="does not contain (match case)";isc.A.notStartsWithTitle="does not start with (match case)";isc.A.notEndsWithTitle="does not end with (match case)";isc.A.isNullTitle="is null";isc.A.notNullTitle="not null";isc.A.regexpTitle="matches expression (exact case)";isc.A.iregexpTitle="matches expression";isc.A.inSetTitle="is one of";isc.A.notInSetTitle="is not one of";isc.A.equalsFieldTitle="matches other field";isc.A.notEqualFieldTitle="differs from field";isc.A.greaterThanFieldTitle="greater than field";isc.A.lessThanFieldTitle="less than field";isc.A.greaterOrEqualFieldTitle="greater than or equal to field";isc.A.lessOrEqualFieldTitle="less than or equal to field";isc.A.containsFieldTitle="contains (match case) another field value";isc.A.startsWithFieldTitle="starts with (match case) another field value";isc.A.endsWithFieldTitle="ends with (match case) another field value";isc.A.andTitle="and";isc.A.notTitle="not";isc.A.orTitle="or";if(isc.DynamicForm){isc.defineClass("DynamicFilterForm","SearchForm");isc.A=isc.DynamicFilterForm.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$10j="Enter";isc.B.push(isc.A.handleKeyPress=function isc_DynamicFilterForm_handleKeyPress(_1,_2){var _3=this.getFocusSubItem();if(isc.isA.TextItem(_3))_2.firedOnTextItem=true;if(_1.keyName!=this.$10j){return this.Super("handleKeyPress",[_1,_2])}}
,isc.A.itemChanged=function isc_DynamicFilterForm_itemChanged(_1,_2,_3){if(this.creator.itemChanged)this.creator.itemChanged()}
);isc.B._maxIndex=isc.C+2;isc.defineClass("FilterClause","HStack");isc.A=isc.FilterClause.getPrototype();isc.A.height=20;isc.A.showFieldTitles=true;isc.A.validateOnChange=true;isc.A.fieldPickerWidth=150;isc.A.operatorPickerWidth=150;isc.A.valueItemWidth=150;isc.A.fieldPickerDefaults={type:"SelectItem",name:"fieldName",showTitle:false,textMatchStyle:"startsWith",changed:function(){this.form.creator.fieldNameChanged(this.form)}};isc.A.fieldPickerTitle="Field Name";isc.A.operatorPickerDefaults={name:"operator",type:"select",showTitle:false,addUnknownValues:false,defaultToFirstOption:true,changed:function(){this.form.creator.operatorChanged(this.form)}};isc.A.operatorPickerTitle="Operator";isc.A.valueItemTitle="Value";isc.A.valueSetHint="Enter values separated by comma";isc.A.clauseConstructor=isc.DynamicFilterForm;isc.A.showRemoveButton=true;isc.A.removeButtonPrompt="Remove";isc.A.excludeNonFilterableFields=true;isc.A.removeButtonDefaults={_constructor:isc.ImgButton,width:18,height:18,layoutAlign:"center",src:"[SKIN]/actions/remove.png",showRollOver:false,showDown:false,showDisabled:false,click:function(){this.creator.remove()}};isc.A.flattenItems=true;isc.A=isc.FilterClause.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.customGetValuesFunction="getCriterion";isc.A.valueAttribute="value";isc.A.rangeStartAttribute="start";isc.A.rangeEndAttribute="end";isc.A.operatorAttribute="operator";isc.A.setCustomValuseFunction="setCriterion";isc.B.push(isc.A.initWidget=function isc_FilterClause_initWidget(){this.Super("initWidget",arguments);this.setupClause()}
,isc.A.getFilterBuilder=function isc_FilterClause_getFilterBuilder(){return this.filterBuilder}
,isc.A.getPrimaryDS=function isc_FilterClause_getPrimaryDS(_1){if(this.dataSources){if(_1==null){return isc.DataSource.get(this.dataSources[0])}
if(isc.isAn.Object(_1)){if(_1.$92v!=null){_1=_1.$92v}
for(var i=0;i<this.dataSources.length;i++){var _3=isc.getValues(this.dataSources[i].getFields());if(_3.contains(_1))return this.dataSources[i]}
_1=_1.name}
return isc.DataSource.getDataSourceForField(_1,this.dataSources)}
if(this.dataSource)return this.getDataSource();else if(this.fieldDataSource)return this.fieldDataSource}
,isc.A.getField=function isc_FilterClause_getField(_1){if(isc.isAn.Object(_1))return _1;var _2;if(this.dataSources){_2=isc.DataSource.getFieldFromDataSources(_1,this.dataSources)}else if(this.dataSource){_2=this.getDataSource().getField(_1)}else{if(this.clause){_2=this.fieldData?this.fieldData[_1]:null;if(!_2)_2=this.clause.getField("fieldName").getSelectedRecord();if(!_2)_2=this.field;else this.field=_2}}
return _2}
,isc.A.getFieldNames=function isc_FilterClause_getFieldNames(){if(this.dataSources){return isc.DataSource.getCombinedDataSourceFields(this.dataSources)}
if(this.dataSource)return this.getDataSource().getFieldNames(true)}
,isc.A.getFieldOperatorMap=function isc_FilterClause_getFieldOperatorMap(_1,_2,_3,_4){return this.getPrimaryDS(_1).getFieldOperatorMap(_1,_2,_3,_4)}
,isc.A.getSearchOperator=function isc_FilterClause_getSearchOperator(_1,_2){return this.getPrimaryDS(_2).getSearchOperator(_1)}
,isc.A.combineFieldData=function isc_FilterClause_combineFieldData(_1,_2){var _3=this.getPrimaryDS(_2),_4=this.getField(_2);if(_4)
return _3.combineFieldData(_1,_2);else return _1}
,isc.A.setupClause=function isc_FilterClause_setupClause(){this.$85y=true;if(this.dataSource&&!isc.isA.DataSource(this.dataSource))
this.dataSource=isc.DataSource.get(this.dataSource);if(this.fieldDataSource&&!isc.isA.DataSource(this.fieldDataSource))
this.fieldDataSource=isc.DataSource.get(this.fieldDataSource);if(this.showRemoveButton){this.addAutoChild("removeButton",{prompt:this.removeButtonPrompt})}
this.fieldPickerDefaults.title=this.fieldPickerTitle;this.operatorPickerDefaults.title=this.operatorPickerTitle;var _1={};if(this.showClause!=false){if(this.topOperatorAppearance=="inline"){if(this.topOperator=="and"){var _2={and:this.creator.inlineAndTitle,not:this.creator.inlineAndNotTitle}}else{var _2={or:this.creator.inlineOrTitle,not:this.creator.inlineAndNotTitle}}
var _3=0;this.topOperatorFormProperties={layoutAlign:"top"};if(this.creator.showSelectionCheckbox){isc.addProperties(this.topOperatorFormProperties,{numCols:2,width:120,colWidths:["20%","80%"]});this.topOperatorFormProperties.items=this.topOperatorFormDefaults.items;this.topOperatorFormProperties.items.addAt({name:"select",type:"checkbox",showTitle:false,showLabel:false,defaultValue:false,showIf:function(){return this.form.creator.showSelectionCheckbox}},0);_3=1}
this.addAutoChild("topOperatorForm");this.topOperatorForm.items[_3].valueMap=_2;this.topOperatorForm.items[_3].defaultValue=this.negated?"not":this.topOperator;this.updateInlineTopOperator()}
var _4=[isc.addProperties(isc.clone(this.fieldPickerDefaults),{width:this.fieldPickerWidth},this.fieldPickerProperties,{name:"fieldName"}),isc.addProperties(isc.clone(this.operatorPickerDefaults),{width:this.operatorPickerWidth},this.operatorPickerProperties,{name:"operator"})],_5=this.criterion,_6=this.getFieldNames(),_7;if(this.fieldName&&(this.dataSource||this.dataSources)){var _8=this.fieldName;var _9=this.getField(_8),_10;isc.addProperties(_4[0],{type:"staticText",clipValue:true,wrap:false});if(!_9||(this.excludeNonFilterableFields&&_9.canFilter==false))_8=_6[0];else if(this.showFieldTitles){_10=_9.title?_9.title:_8}
_4[0].defaultValue=_8;if(_10!=null){var _11={};_11[_8]=_10;_4[0].valueMap=_11}
_7=_8}else{if(this.fieldDataSource){isc.addProperties(_4[0],{type:"ComboBoxItem",completeOnTab:true,optionDataSource:this.fieldDataSource,valueField:"name",displayField:this.showFieldTitles?"title":"name",pickListProperties:{reusePickList:function(){return false}}});if(this.field)_4[0].defaultValue=this.field.name}else{for(var i=0;i<_6.length;i++){var _13=_6[i],_9=this.getField(_13);if(this.excludeNonFilterableFields&&_9.canFilter==false)continue;if(this.showFieldTitles){var _10=_9.title;_10=_10?_10:_13;_1[_13]=_10}else{_1[_13]=_13}}
_4[0].valueMap=_1;_4[0].defaultValue=isc.firstKey(_1)}}
this.fieldPicker=_4[0];var _14=_4[0],_15=_4[1];if(!this.fieldName){if(_5&&_5.fieldName){if(this.fieldDataSource){_14.defaultValue=_5.fieldName}else{if(_6.contains(_5.fieldName)){_14.defaultValue=_5.fieldName}else{isc.logWarn("Criterion specified field "+_5.fieldName+", which is not"+" in the record. Using the first record field ("+_1?isc.firstKey(_1):_6[0]+") instead");_14.defaultValue=_1?isc.firstKey(_1):_6[0]}}}
_7=_14.defaultValue}
if(_7){var _9=this.field||this.getField(_7);var _11=_9?this.getFieldOperatorMap(_9,false,"criteria",true):null;_15.valueMap=_11;if(_11){if(_5&&_5.operator){_15.defaultValue=_5.operator}else{_15.defaultValue=isc.firstKey(_11)}}
this.$74o=_7;var _16=this.getSearchOperator(_15.defaultValue,_9);if(!_16&&_11.length>0){isc.logWarn("Criterion specified unknown operator "+(_5?_5.operator:"[null criterion]")+". Using the first valid operator ("+isc.firstKey(_11)+") instead");_15.defaultValue=isc.firstKey(_11);_16=this.getSearchOperator(_15.defaultValue,_9)}
var _17=this.buildValueItemList(_9,_16,_7);if(_5){if(_5.value!=null&&_17.containsProperty("name","value")){_17.find("name","value").defaultValue=_5.value}
if(_5.start!=null&&_17.containsProperty("name","start")){_17.find("name","start").defaultValue=_5.start}
if(_5.end!=null&&_17.containsProperty("name","end")){_17.find("name","end").defaultValue=_5.end}}
if(_17)_4.addList(_17)}else{_15.disabled=true}
this.addAutoChild("clause",{flattenItems:this.flattenItems,items:_4});this.fieldPicker=this.clause.getItem("fieldName");this.operatorPicker=this.clause.getItem("operator")}
this.addMembers([this.topOperatorForm,this.removeButton,this.clause]);if(this.fieldPicker&&this.fieldPicker.type=="staticText"){this.fieldPicker.prompt=this.fieldPicker.getValue()}}
,isc.A.updateInlineTopOperator=function isc_FilterClause_updateInlineTopOperator(){if(this.topOperatorAppearance!="inline")return;var _1=this.creator.showSelectionCheckbox?1:0;if(this.creator.isFirstClause(this)){this.topOperatorForm.items[_1].hide()}else{this.topOperatorForm.items[_1].show()}}
,isc.A.getEditorType=function isc_FilterClause_getEditorType(_1,_2){if(isc.isA.FilterBuilder(this.filterBuilder)){return this.filterBuilder.getEditorType(_1,_2)}else{if(this.clause)return this.clause.getEditorType(_1,_2);return null}}
,isc.A.buildValueItemList=function isc_FilterClause_buildValueItemList(_1,_2,_3){if(_2==null)this.logWarn("buildValueItemList passed null operator");if(_1==null){return}
if(_3==null)_3=_1.name;var _4=_2?_2.valueType:"text",_5=isc.SimpleType.getType(_1.type)||isc.SimpleType.getType("text"),_6=[],_7,_8=null;_5=_5.name;var _9={$92v:_1};isc.addProperties(_9,_1);_9.canEdit=_1.canFilter;_1=_9;_8=this.getEditorType(_1,_2.ID);if(_4=="fieldType"||_4=="custom"||_4=="valueSet"){var _10=_5;if(_4=="valueSet"){_8="TextAreaItem"}else if(_4=="custom"&&_2&&_2.editorType){_8=_2.editorType}
var _11=isc.addProperties({type:_10,valueType:_4,name:_1.name,showTitle:false,title:this.valueItemTitle,width:this.valueItemWidth,changed:function(){this.form.creator.valueChanged(this,this.form)}},this.getValueFieldProperties(_1.type,_3,_2.ID,"value"));if(_8)_11.editorType=_8;if(_4=="valueSet"){_11.multiple=true;_11.showHintInField=true;_11.hint=this.valueSetHint;_11.parseEditorValue=function(_17,_18,_19){if(_17==null)return _17;return _17.split(",")};_11.formatEditorValue=function(_17,_18,_19,_20){if(_17==null)return"";if(isc.isAn.Array(_17)){return _17.join(",")}
return _17}}
_11=this.combineFieldData(_11,_1);_11.name="value";if(_1.type=="enum"){_11=isc.addProperties(_11,{valueMap:_1.valueMap})}
if(_5=="boolean"){_11=isc.addProperties(_11,{defaultValue:false})}
if(_1.editorProperties){if(_1.editorType=="SelectItem"||_1.editorType=="ComboBoxItem"||_1.editorType=="select")
{_7=_1.editorProperties;if(_7.optionDataSource!=null)_11.optionDataSource=_7.optionDataSource;if(_7.valueField!=null)_11.valueField=_7.valueField;if(_7.displayField!=null)_11.displayField=_7.displayField}else{_11=isc.addProperties({},_11,_1.editorProperties)}}
_6.add(_11)}else if(_4=="fieldName"){_7=isc.addProperties({},this.fieldPickerDefaults,{type:"select",showTitle:false,valueType:_4,width:this.valueItemWidth,textMatchStyle:this.fieldPicker.textMatchStyle,changed:function(){this.form.creator.valueChanged(this,this.form)}});if(this.fieldDataSource){_7=isc.addProperties(_7,{type:"ComboBoxItem",completeOnTab:true,optionDataSource:this.fieldDataSource,valueField:"name",displayField:this.showFieldTitles?"title":"name",pickListProperties:{reusePickList:function(){return false}}})}else{var _12=this.getFieldNames(true);_12.remove(_3);var _13={};for(var i=0;i<_12.length;i++){var _15=_12[i];if(this.showFieldTitles){var _16=this.getField(_15).title;_16=_16?_16:_15;_13[_15]=_16}else{_13[_15]=_15}}
_7=isc.addProperties(_7,{valueMap:_13})}
_6.add(isc.addProperties(_7,this.getValueFieldProperties(_1.type,_3,_2.ID,"name"),this.fieldPickerProperties,{name:"value"}))}else if(_4=="valueRange"){_7=this.combineFieldData(isc.addProperties({type:_5,editorType:_8,showTitle:false,width:this.valueItemWidth,changed:function(){this.form.creator.valueChanged(this,this.form)}}),_1);_6.addList([isc.addProperties({},_7,{name:"start"},this.getValueFieldProperties(_1.type,_3,_2.ID,"start")),isc.addProperties({type:"staticText",name:"rangeSeparator",showTitle:false,width:1,shouldSaveValue:false,defaultValue:this.filterBuilder?this.filterBuilder.rangeSeparator:null,changed:function(){this.form.creator.valueChanged(this,this.form)}},this.getRangeSeparatorProperties(_1.type,_3,_2.ID)),isc.addProperties({},_7,{name:"end"},this.getValueFieldProperties(_1.type,_3,_2.ID,"end"))])}
if(this.validateOnChange){for(var i=0;i<_6.length;i++){isc.addProperties(_6[i],{blur:function(_17,_18){if(!_17.creator.itemsInError)_17.creator.itemsInError=[];if(!_17.validate(null,null,true)){_18.focusInItem();if(!_17.creator.itemsInError.contains(_18)){_17.creator.itemsInError.add(_18)}}else{if(_17.creator.itemsInError.contains(_18)){_17.creator.itemsInError.remove(_18)}}}})}}
for(var i=0;i<_6.length;i++){if(_6[i].showIf!=null)delete _6[i].showIf}
return _6}
,isc.A.getValueFieldProperties=function isc_FilterClause_getValueFieldProperties(_1,_2,_3,_4){if(this.filterBuilder){return this.filterBuilder.getValueFieldProperties(_1,_2,_3,_4)}}
,isc.A.getRangeSeparatorProperties=function isc_FilterClause_getRangeSeparatorProperties(_1,_2,_3){if(this.filterBuilder)return this.filterBuilder.getRangeSeparatorProperties(_1,_2,_3)}
,isc.A.remove=function isc_FilterClause_remove(){this.markForDestroy()}
,isc.A.getValues=function isc_FilterClause_getValues(){var _1=this.clause;return _1.getValues()}
,isc.A.getFieldName=function isc_FilterClause_getFieldName(){return this.fieldPicker.getValue()||this.fieldName}
,isc.A.getCriterion=function isc_FilterClause_getCriterion(_1){if(!this.clause)return null;var _2=this.getFieldName(),_3=this.operatorPicker.getValue();if(!_2)return null;if(isc.isA.String(_3))_3=this.getSearchOperator(_3,_2);if(_3==null)return;var _4=this.getClauseValues(_2,_3);if(!_1){if(!_3||(_3.valueType!="none"&&_3.valueType!="valueRange"&&(_4.value==null||(isc.isA.String(_4.value)&&_4.value==""))))
{return null}}
return _4}
,isc.A.getClauseValues=function isc_FilterClause_getClauseValues(_1,_2){var _3=this.clause,_4={},_5=_3.getField("value"),_6=_3.getField("start"),_7=_3.getField("end"),_8=_2.valueAttribute||this.valueAttribute,_9=_2.rangeStartAttribute||this.rangeStartAttribute,_10=_2.rangeEndAttribute||this.rangeEndAttribute;_4[this.operatorAttribute]=_3.getValue("operator");if(_1!=null)_4.fieldName=_1;if(_2[this.customGetValuesFunction]&&isc.isA.Function(_2[this.customGetValuesFunction]))
{if(_5){_4=_2[this.customGetValuesFunction](_1,_5)}else{var _11=_2[this.customGetValuesFunction](_1,_6),_12=_2[this.customGetValuesFunction](_1,_7);_4.fieldName=_11.fieldName;_4[this.operatorAttribute]=_11.operator;_4[_9]=_11.value;_4[_10]=_12.value}}else{if(_5)_4[_8]=_5.getValue();if(_6)_4[_9]=_6.getValue();if(_7)_4[_10]=_7.getValue()}
var _13=this.getField(_1);if(isc.isA.Date(_4[this.valueAttribute])&&(!_13||!isc.SimpleType.inheritsFrom(_13.type,"datetime")))
{_4[_8].logicalDate=true}
return _4}
,isc.A.setClauseValues=function isc_FilterClause_setClauseValues(_1,_2,_3){if(this.clause==null)return;var _4=this.clause,_5=_4.getField("value"),_6=_4.getField("start"),_7=_4.getField("end"),_8=_2.valueAttribute||this.valueAttribute,_9=_2.rangeStartAttribute||this.rangeStartAttribute,_10=_2.rangeEndAttribute||this.rangeEndAttribute;if(_2[this.customSetValuesFunction]&&isc.isA.Function(_2[this.customSetValuesFunction]))
{if(_5){_2[this.customSetValuesFunction](_1,_5,_3)}else{_2[this.customSetValuesFunction](_1,_6,_3);_2[this.customSetValuesFunction](_1,_7,_3)}}else{if(_5)_5.setValue(_3[_8]);if(_6)_6.setValue(_3[_9]);if(_7)_7.setValue(_3[_10])}}
,isc.A.setDefaultFocus=function isc_FilterClause_setDefaultFocus(){if(!this.clause)return;if(isc.isA.Function(this.clause.focusInItem))this.clause.focusInItem("fieldName")}
,isc.A.validate=function isc_FilterClause_validate(){return this.clause?this.clause.validate(null,null,true):true}
,isc.A.itemChanged=function isc_FilterClause_itemChanged(){if(this.creator&&isc.isA.Function(this.creator.itemChanged))this.creator.itemChanged()}
,isc.A.valueChanged=function isc_FilterClause_valueChanged(_1,_2){}
,isc.A.fieldNameChanged=function isc_FilterClause_fieldNameChanged(){this.clause.getItem("operator").disabled=false;this.updateFields()}
,isc.A.removeValueFields=function isc_FilterClause_removeValueFields(){if(!this.clause)return;var _1=this.clause;if(_1.getItem("value"))_1.removeItem("value");if(_1.getItem("rangeSeparator"))_1.removeItem("rangeSeparator");if(_1.getItem("start"))_1.removeItem("start");if(_1.getItem("end"))_1.removeItem("end")}
,isc.A.operatorChanged=function isc_FilterClause_operatorChanged(){if(!this.clause)return;var _1=this.clause,_2=this.fieldName||_1.getValue("fieldName");if(_2==null)return;var _3=this.getField(_2);var _4=this.getSearchOperator(_1.getValue("operator"),_3);this.updateValueItems(_3,_4,_2)}
,isc.A.updateValueItems=function isc_FilterClause_updateValueItems(_1,_2,_3){var _4=this.clause;var _5=_4.getItem("value");var _6=_5?_5.valueType:null;this.removeValueFields();var _7=this.buildValueItemList(_1,_2,_3)
_4.addItems(_7);var _8=_4.getItem("value");if(_8&&((_8.valueType!=_6)||(_8.getValueMap()&&_8.$193&&!_8.$193(_8.getValue())||_8.optionDataSource||!this.retainValuesAcrossFields))){_8.clearValue()}}
,isc.A.updateFields=function isc_FilterClause_updateFields(){if(!this.clause)return;var _1=this.clause,_2=this.$74o,_3=this.fieldName||_1.getValue("fieldName");if(_3==null)return;if(_3==_2)return;var _4=this.getField(_3),_5=this.getField(_2);if(!_4)return;var _6=_1.getValue("operator");_1.getItem("operator").setValueMap(this.getFieldOperatorMap(_4,false,"criteria",true));if(_6==null||_1.getValue("operator")!=_6){if(_1.getValue("operator")==null){_1.getItem("operator").setValue(_1.getItem("operator").getFirstOptionValue())}
_6=_1.getValue("operator")}
_6=this.getSearchOperator(_6,_4);var _7;if(_1.getItem("value")){var _8=_1.getItem("value").type,_9=_4.type||"text";_7=(_8!=_9)}
this.removeValueFields();_1.addItems(this.buildValueItemList(_4,_6,_3));if(_7){_1.clearValue("value")}else{var _10=_1.getItem("value"),_11=((_4.valueMap||_4.optionDataSource)||(_5&&(_5.valueMap||_5.optionDataSource))||!this.retainValuesAcrossFields);if(_10&&_11)_10.clearValue()}
if(_1.getItem("start"))_1.setValue("start",null);if(_1.getItem("end"))_1.setValue("end",null);this.$74o=_3}
,isc.A.getFieldOperators=function isc_FilterClause_getFieldOperators(_1){var _2=this.getField(_1)
return this.getPrimaryDS(_1).getFieldOperators(_2)}
,isc.A.topOperatorChanged=function isc_FilterClause_topOperatorChanged(_1){}
);isc.B._maxIndex=isc.C+31;isc.FilterClause.registerStringMethods({remove:""});isc.defineClass("FilterBuilder","Layout");isc.A=isc.FilterBuilder;isc.A.missingFieldPrompt="[missing field definition]";isc.A=isc.FilterBuilder;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.getFilterDescription=function isc_c_FilterBuilder_getFilterDescription(_1,_2){if(!isc.isAn.Array(_2)){if(!isc.isA.DataSource(_2))_2=isc.DS.getDataSource(_2);if(!_2)return"No dataSource"}
var _3="";if(_1.criteria&&isc.isAn.Array(_1.criteria)){var _4=_1.operator,_5=_1.criteria;for(var i=0;i<_5.length;i++){var _7=_5[i];if(i>0)_3+=" "+_4+" ";if(_7.criteria&&isc.isAn.Array(_7.criteria)){_3+="("
_3+=isc.FilterBuilder.getFilterDescription(_7,_2);_3+=")"}else{_3+=isc.FilterBuilder.getCriterionDescription(_7,_2)}}}else{_3+=isc.FilterBuilder.getCriterionDescription(_1,_2)}
return _3}
,isc.A.getCriterionDescription=function isc_c_FilterBuilder_getCriterionDescription(_1,_2){var _3=_1.fieldName,_4=_1.operator,_5=_1.value,_6=_1.start,_7=_1.end,_8;if(isc.isAn.Array(_2)){_2=isc.DataSource.getDataSourceForField(_3,_2);_8=isc.DataSource.getFieldFromDataSources(_3,[_2])}else{if(!isc.isA.DataSource(_2))_2=isc.DS.getDataSource(_2);if(!_2)return"No DataSource";_8=_2.getField(_3)}
var _9=_2.getSearchOperator(_4,_8),_10=_2.getFieldOperatorMap(_8,true,_9.valueType,false),_11="";if(!_8){if(_1.criteria&&isc.isAn.Array(_1.criteria)){isc.logWarn("FilterBuilder.getCriterionDescription: Passed an AdvancedCriteria - "+"returning through getFilterDescription.");return isc.FilterBuilder.getFilterDescription(_1,_2)}
_11=_3+" "+isc.FilterBuilder.missingFieldPrompt+" ";isc.logWarn("FilterBuilder.getCriterionDescription: No such field '"+_3+"' "+"in DataSource '"+_2.ID+"'.")}else{_11=(_8.title?_8.title:_3)+" "}
switch(_4)
{case"lessThan":case"greaterThan":case"lessOrEqual":case"greaterOrEqual":case"between":case"notNull":_11+="is "+_10[_4]||_4;break;case"equals":_11+="is equal to";break;case"notEqual":_11+="is not equal to";break;default:_11+=_10[_4]||_4}
if(_9.valueType=="valueRange")_11+=" "+_6+" and "+_7;else if(_4!="notNull")_11+=" "+_5;return _11}
);isc.B._maxIndex=isc.C+2;isc.A=isc.FilterBuilder.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.vertical=false;isc.A.vPolicy="none";isc.A.height=1;isc.A.defaultWidth=400;isc.A.fieldPickerDefaults={type:"SelectItem",name:"fieldName",textMatchStyle:"startsWith",showTitle:false,changed:function(){this.form.creator.fieldNameChanged(this.form)}};isc.A.fieldPickerTitle="Field Name";isc.A.showFieldTitles=true;isc.A.validateOnChange=true;isc.A.showRemoveButton=true;isc.A.removeButtonPrompt="Remove";isc.A.removeButtonDefaults={_constructor:isc.ImgButton,width:18,height:18,layoutAlign:"center",src:"[SKIN]/actions/remove.png",showRollOver:false,showDown:false,showDisabled:false,click:function(){this.creator.removeButtonClick(this.clause)}};isc.A.showAddButton=true;isc.A.addButtonPrompt="Add";isc.A.addButtonDefaults={_constructor:isc.ImgButton,autoParent:"buttonBar",width:18,height:18,src:"[SKIN]/actions/add.png",showRollOver:false,showDown:false,click:function(){this.creator.addButtonClick(this.clause)}};isc.A.buttonBarDefaults={_constructor:isc.HStack,autoParent:"clauseStack",membersMargin:4,defaultLayoutAlign:"center",height:1};isc.A.retainValuesAcrossFields=true;isc.A.topOperator="and";isc.A.radioOptions=["and","or","not"];isc.A.topOperatorAppearance="bracket";isc.A.radioOperatorFormDefaults={_constructor:isc.DynamicForm,autoParent:"clauseStack",height:1,items:[{name:"operator",type:"radioGroup",showTitle:false,title:"Overall Operator",vertical:false,width:250,changed:function(_1,_2,_3){_1.creator.topOperatorChanged(_3)}}]};isc.A.radioOperatorTitle="Overall Operator";isc.A.topOperatorFormDefaults={height:1,width:80,numCols:1,colWidths:["*"],layoutAlign:"center",_constructor:isc.DynamicForm,items:[{name:"operator",type:"select",showTitle:false,width:"*",changed:function(_1,_2,_3){_1.creator.topOperatorChanged(_3)}}]};isc.A.topOperatorTitle="Clause Operator";isc.A.defaultSubClauseOperator="or";isc.A.matchAllTitle="Match All";isc.A.matchNoneTitle="Match None";isc.A.matchAnyTitle="Match Any";isc.A.inlineAndTitle="and";isc.A.inlineOrTitle="or";isc.A.inlineAndNotTitle="and not";isc.A.clauseStackDefaults={_constructor:isc.VStack,height:1,membersMargin:1,animateMembers:true,animateMemberTime:150};isc.A.clauseConstructor="FilterClause";isc.A.rangeSeparator="and";isc.A.subClauseButtonTitle="+()";isc.A.subClauseButtonPrompt="Add Subclause";isc.A.subClauseButtonDefaults={_constructor:"IButton",autoParent:"buttonBar",autoFit:true,click:function(){this.creator.addSubClause(this.clause)}};isc.A.bracketDefaults={styleName:"bracketBorders",width:10};isc.A.internalSearchFormDefaults={_constructor:"SearchForm",visibility:"hidden",autoDraw:false};isc.A.$10j="Enter";isc.A.allowEmptyValues=false;isc.B.push(isc.A.setDataSource=function isc_FilterBuilder_setDataSource(_1){if(isc.DataSource.get(this.dataSource).ID!=isc.DataSource.get(_1).ID){this.dataSource=_1;this.clearCriteria()}}
,isc.A.setDataSources=function isc_FilterBuilder_setDataSources(_1){this.dataSources=_1;this.clearCriteria()}
,isc.A.addButtonClick=function isc_FilterBuilder_addButtonClick(){this.addNewClause()}
,isc.A.removeButtonClick=function isc_FilterBuilder_removeButtonClick(_1){if(!_1)return;this.removeClause(_1)}
,isc.A.removeClause=function isc_FilterBuilder_removeClause(_1){this.clauses.remove(_1);if(this.clauseStack)this.clauseStack.hideMember(_1,function(){_1.destroy()});this.updateFirstRemoveButton();if(this.clauses[0]&&this.clauses[0].updateInlineTopOperator)this.clauses[0].updateInlineTopOperator();_1.filterBuilder=null;if(isc.isA.Function(this.filterChanged))this.filterChanged()}
,isc.A.updateFirstRemoveButton=function isc_FilterBuilder_updateFirstRemoveButton(){var _1=this.clauses[0];if(!_1||!_1.removeButton)return;if(this.clauses.length==1&&!this.allowEmpty){_1.removeButton.disable();_1.removeButton.setOpacity(50)}else if(this.clauses.length>1){_1.removeButton.enable();_1.removeButton.setOpacity(100)}}
,isc.A.isFirstClause=function isc_FilterBuilder_isFirstClause(_1){return this.clauses[0]==_1}
,isc.A.setTopOperator=function isc_FilterBuilder_setTopOperator(_1){this.topOperator=_1;var _2=this.topOperatorAppearance;if(_2=="bracket"){this.topOperatorForm.setValue("operator",_1)}else if(_2=="radio"){this.radioOperatorForm.setValue("operator",_1)}}
,isc.A.topOperatorChanged=function isc_FilterBuilder_topOperatorChanged(_1){this.topOperator=_1}
,isc.A.getPrimaryDS=function isc_FilterBuilder_getPrimaryDS(_1){if(this.dataSources){if(_1==null){return isc.DataSource.get(this.dataSources[0])}
if(isc.isAn.Object(_1)){if(_1.$92v!=null){_1=_1.$92v}
for(var i=0;i<this.dataSources.length;i++){var _3=isc.getValues(this.dataSources[i].getFields());if(_3.contains(_1))return this.dataSources[i]}
_1=_1.name}
return isc.DataSource.getDataSourceForField(_1,this.dataSources)}
if(this.dataSource)return this.getDataSource();else if(this.fieldDataSource)return this.fieldDataSource}
,isc.A.initWidget=function isc_FilterBuilder_initWidget(){this.Super("initWidget",arguments);if(this.fieldDataSource&&this.criteria)this.$75v=true;this.fieldPickerDefaults.title=this.fieldPickerTitle;this.addButtonDefaults.prompt=this.addButtonPrompt;this.removeButtonDefaults.prompt=this.removeButtonPrompt;this.subClauseButtonDefaults.prompt=this.subClauseButtonPrompt;this.subClauseButtonDefaults.title=this.subClauseButtonTitle;var _1;if(this.showSubClauseButton==_1){this.showSubClauseButton=(this.topOperatorAppearance!="radio"&&this.topOperatorAppearance!="inline")}
this.clauses=[];var _2=this.topOperatorAppearance;if(isc.isA.String(this.fieldDataSource))
this.fieldDataSource=isc.DS.get(this.fieldDataSource);if(isc.isA.String(this.dataSource))
this.dataSource=isc.DS.get(this.dataSource);var _3=this.getPrimaryDS(),_4=_3.getTypeOperatorMap("text",true,"criteria"),_5=[];var _6={"and":this.matchAllTitle,"or":this.matchAnyTitle,"not":this.matchNoneTitle};for(var _7 in _4){_5.add(_7)}
if(_2=="bracket"){if(this.showTopRemoveButton){var _8=this.removeButton=this.createAutoChild("removeButton",{click:function(){this.creator.parentClause.removeButtonClick(this.creator)}});this.addMember(_8)}
this.addAutoChild("topOperatorForm");this.topOperatorForm.items[0].title=this.topOperatorTitle;this.topOperatorForm.items[0].valueMap=_4;this.topOperatorForm.items[0].defaultValue=this.topOperator;this.addAutoChild("bracket")}
this.addAutoChild("clauseStack");this.clauseStack.hide();if(_2=="radio"){this.addAutoChild("radioOperatorForm");var _9={};for(var i=0;i<this.radioOptions.length;i++){_9[this.radioOptions[i]]=_6[this.radioOptions[i]]}
this.radioOperatorForm.items[0].title=this.radioOperatorTitle;this.radioOperatorForm.items[0].valueMap=_9;this.radioOperatorForm.items[0].defaultValue=this.topOperator}
this.addAutoChildren(["buttonBar","addButton","subClauseButton"]);this.stripNullCriteria(this.criteria);this.setCriteria(this.criteria)}
,isc.A.addNewClause=function isc_FilterBuilder_addNewClause(_1,_2,_3){var _4=isc.addProperties({},this.topOperatorFormDefaults);_4.items=[];for(var i=0;i<this.topOperatorFormDefaults.items.length;i++){_4.items.add(isc.addProperties({},this.topOperatorFormDefaults.items[i]))}
var _6=this.createAutoChild("clause",{visibility:"hidden",flattenItems:true,criterion:_1,dataSources:this.dataSources,dataSource:this.dataSource,fieldDataSource:this.fieldDataSource,validateOnChange:this.validateOnChange,showFieldTitles:this.showFieldTitles,showRemoveButton:this.showRemoveButton,removeButtonPrompt:this.removeButtonPrompt,retainValuesAcrossFields:this.retainValuesAcrossFields,field:_2,fieldData:this.fieldData,fieldPickerDefaults:this.fieldPickerDefaults,fieldPickerProperties:this.fieldPickerProperties,remove:function(){this.creator.removeClause(this)},fieldNameChanged:function(){this.Super("fieldNameChanged",arguments);this.creator.fieldNameChanged(this)},topOperatorAppearance:this.topOperatorAppearance,topOperator:this.topOperator,topOperatorFormDefaults:_4,showSelectionCheckbox:this.showSelectionCheckbox,negated:_3,filterBuilder:this});var _7=this.$74e(_6);_6.updateInlineTopOperator();return _7}
,isc.A.addClause=function isc_FilterBuilder_addClause(_1){if(!_1)return _1;var _2=this;_1.fieldDataSource=this.fieldDataSource;_1.remove=function(){_2.removeClause(this)};_1.fieldNameChanged=function(){this.Super("fieldNameChanged",arguments);_2.fieldNameChanged(this)};var _3=this.$74e(_1);_1.updateInlineTopOperator();return _3}
,isc.A.$74e=function isc_FilterBuilder__addClause(_1){_1.filterBuilder=this;_1.updateFields();this.clauses.add(_1);var _2=this.clauseStack,_3=Math.max(0,_2.getMemberNumber(this.buttonBar)),_4=this;_2.addMember(_1,_3);_2.showMember(_1,function(){if(!_4.$845)_1.setDefaultFocus()});this.updateFirstRemoveButton();if(isc.isA.Function(this.filterChanged))this.filterChanged();return _1}
,isc.A.getChildFilters=function isc_FilterBuilder_getChildFilters(){var _1=[];for(var i=0;i<this.clauses.length;i++){var _3=this.clauses[i];if(isc.isA.FilterBuilder(_3))_1.add(_3)}
return _1}
,isc.A.getFilterDescription=function isc_FilterBuilder_getFilterDescription(){return isc.FilterBuilder.getFilterDescription(this.getCriteria(),(this.dataSources||this.dataSource))}
,isc.A.validate=function isc_FilterBuilder_validate(){var _1=true;for(var i=0;i<this.clauses.length;i++){if(!this.clauses[i].validate(null,null,true))_1=false}
return _1}
,isc.A.getFieldOperators=function isc_FilterBuilder_getFieldOperators(_1){var _2=this.getField(_1);return this.getPrimaryDS(_1).getFieldOperators(_2)}
,isc.A.getValueFieldProperties=function isc_FilterBuilder_getValueFieldProperties(_1,_2,_3,_4){if(this.filterBuilder){return this.filterBuilder.getValueFieldProperties(_1,_2,_3,_4)}}
,isc.A.getRangeSeparatorProperties=function isc_FilterBuilder_getRangeSeparatorProperties(_1,_2,_3){return this.rangeSeparatorProperties}
,isc.A.childResized=function isc_FilterBuilder_childResized(){this.Super("childResized",arguments);if(this.clauseStack&&this.bracket)this.bracket.setHeight(this.clauseStack.getVisibleHeight())}
,isc.A.draw=function isc_FilterBuilder_draw(){this.Super("draw",arguments);if(this.clauseStack&&this.bracket)this.bracket.setHeight(this.clauseStack.getVisibleHeight())}
,isc.A.resized=function isc_FilterBuilder_resized(){if(this.clauseStack&&this.bracket)this.bracket.setHeight(this.clauseStack.getVisibleHeight())}
,isc.A.addSubClause=function isc_FilterBuilder_addSubClause(_1){var _2;if(_1){_2=_1.operator}
var _3=this.createAutoChild("subClause",{dataSource:this.dataSource,dataSources:this.dataSources,showFieldTitles:this.showFieldTitles,filterBuilder:this,parentClause:this,showTopRemoveButton:true,topOperatorAppearance:"bracket",topOperator:_2||this.defaultSubClauseOperator,clauseConstructor:this.clauseConstructor,fieldPickerDefaults:this.fieldPickerDefaults,fieldPickerProperties:this.fieldPickerProperties,fieldDataSource:this.fieldDataSource,fieldData:this.fieldData,visibility:"hidden",saveOnEnter:this.saveOnEnter,validateOnChange:this.validateOnChange,dontCreateEmptyChild:_1!=null},this.Class);this.clauses.add(_3);this.clauseStack.addMember(_3,this.clauses.length-1);this.clauseStack.showMember(_3,function(){_3.topOperatorForm.focusInItem("operator");_3.bracket.setHeight(_3.getVisibleHeight())});this.updateFirstRemoveButton();return _3}
,isc.A.getCriteria=function isc_FilterBuilder_getCriteria(_1){if(this.$75v){return this.criteria}
if(this.topOperatorAppearance=="inline"){return this.getInlineCriteria(_1)}
var _2={_constructor:"AdvancedCriteria",operator:this.topOperator,criteria:[]};for(var i=0;i<this.clauses.length;i++){var _4=this.clauses[i],_5,_6=false;if(isc.isA.FilterBuilder(_4)){_5=_4.getCriteria(_1)}else{_5=_4.getCriterion(_1);_6=(_5==null)}
if(!_6){_2.criteria.add(_5)}}
return isc.clone(_2)}
,isc.A.getInlineCriteria=function isc_FilterBuilder_getInlineCriteria(_1){var _2={_constructor:"AdvancedCriteria",operator:this.topOperator,criteria:[]};if(this.topOperator=="or"){var _3;for(var i=0;i<this.clauses.length;i++){if(this.clauses[i].topOperatorForm.getValue("operator")=="not"){_3=true;break}}
if(_3){_2.operator="and";var _5={operator:"or",criteria:[]}
_2.criteria.add(_5)}}
for(var i=0;i<this.clauses.length;i++){var _6=this.clauses[i];var _7=_6.topOperatorForm.getValue("operator");if(_7==this.topOperator){if(_3){_5.criteria.add(_6.getCriterion(_1))}else{_2.criteria.add(_6.getCriterion(_1))}}else{_2.criteria.add({operator:"not",criteria:[_6.getCriterion(_1)]})}}
return _2}
,isc.A.filterReady=function isc_FilterBuilder_filterReady(){}
,isc.A.setCriteria=function isc_FilterBuilder_setCriteria(_1){this.clearCriteria(true);var _2=this.clauseStack?this.clauseStack.animateMembers:null;if(this.clauseStack)this.clauseStack.animateMembers=false;this.stripNullCriteria(_1);this.$845=true;if(!this.$76t&&this.fieldDataSource&&_1){if(isc.isA.String(this.fieldDataSource)){this.fieldDataSource=isc.DS.getDataSource(this.fieldDataSource)}
var _3=this,_4=this.fieldDataSource.getCriteriaFields(_1),_5={};if(_4&&_4.length>0){_5={_constructor:"AdvancedCriteria",operator:"or",criteria:[]};for(i=0;i<_4.length;i++){var _6=_4[i],_7=this.fieldData?this.fieldData[_6]:null;if(!_7){_5.criteria[_5.criteria.length]={fieldName:"name",operator:"equals",value:_6}}}
if(_5.criteria.length!=0){this.$76t=true;this.fieldDataSource.fetchData(_5,function(_11){_3.fetchFieldsReply(_11,_1)});return}}}
if(!_1){if(!this.allowEmpty&&!this.dontCreateEmptyChild)this.addNewClause();this.clauseStack.show();this.redraw();this.filterReady();return}
if(!this.getPrimaryDS().isAdvancedCriteria(_1)){_1=isc.DataSource.convertCriteria(_1,"substring")}
if(this.topOperatorAppearance=="inline"){return this.setInlineCriteria(_1,_2)}
this.setTopOperator(_1.operator);if((!_1.criteria||_1.criteria.length==0)&&!this.radioOptions.contains(_1.operator))
{this.logWarn("Found top-level AdvancedCriteria with no sub-criteria. Converting "+"to a top-level 'and' with a single sub-criterion");this.setTopOperator(this.topOperator);this.addNewClause(_1)}else{for(var i=0;i<_1.criteria.length;i++){var _9=_1.criteria[i],_10=this.fieldData?this.fieldData[_9.fieldName]:null;this.addCriterion(_9,_10)}
if(this.clauses.length==0&&!this.allowEmpty)this.addNewClause()}
delete this.$75v;this.$76t=false;this.$845=false;this.clauseStack.show();this.delayCall("redraw");if(this.clauseStack)this.clauseStack.animateMembers=_2;this.filterReady()}
,isc.A.setInlineCriteria=function isc_FilterBuilder_setInlineCriteria(_1,_2){var _3=true,_4=false,_5=false;if(_1.operator=="and"){for(var i=0;i<_1.criteria.length;i++){var _7=_1.criteria[i];if(!_7.criteria){_4=true}else{if(_7.operator=="or"){_5=true;for(var j=0;j<_7.criteria.length;j++){var _9=_7.criteria[j];if(_9.criteria){_3=false;break}}}else{if(_7.operator=="not"){if(_7.criteria.length!=1||_7.criteria[0].criteria){_3=false}}else{_3=false}}}}}else{_3=false}
if(_3)_3=!(_4&&_5);if(_3)_3=_4||_5;if(!_3){isc.logWarn("Trying to load an AdvancedCriteria into an 'inline' FilterBuilder, but "+"the criteria is too complex to be represented in 'inline' format");return}
this.setTopOperator(_4?"and":"or");if(_4){for(var i=0;i<_1.criteria.length;i++){var _7=_1.criteria[i],_10=this.fieldData?this.fieldData[_7.fieldName]:null;if(!_7.criteria){this.addCriterion(_7,_10)}else{_10=this.fieldData?this.fieldData[_7.criteria[0].fieldName]:null;this.addNewClause(_7.criteria[0],_10,true)}}}else{for(var i=0;i<_1.criteria.length;i++){var _7=_1.criteria[i],_10=this.fieldData?this.fieldData[_7.fieldName]:null;if(_7.operator=="or"){for(var j=0;j<_7.criteria.length;j++){var _9=_7.criteria[j];_10=this.fieldData?this.fieldData[_9.fieldName]:null;this.addCriterion(_9,_10)}}else{_10=this.fieldData?this.fieldData[_7.criteria[0].fieldName]:null;this.addNewClause(_7.criteria[0],_10,true)}}}
delete this.$75v;this.$76t=false;this.$845=false;this.clauseStack.show();this.delayCall("redraw");if(this.clauseStack)this.clauseStack.animateMembers=_2;this.filterReady()}
,isc.A.stripNullCriteria=function isc_FilterBuilder_stripNullCriteria(_1){if(_1&&_1.criteria&&_1.criteria.length>0){for(var i=_1.criteria.length-1;i>=0;i--){if(_1.criteria[i]==null){_1.criteria.removeAt(i)}else{if(_1.criteria[i].criteria)this.stripNullCriteria(_1.criteria[i])}}}}
,isc.A.fetchFieldsReply=function isc_FilterBuilder_fetchFieldsReply(_1,_2){if(this.fieldData){var _3=isc.getValues(this.fieldData);_3.addList(_1.data);this.fieldData=_3.makeIndex("name")}else this.fieldData=_1.data.makeIndex("name");this.setCriteria(_2)}
,isc.A.clearCriteria=function isc_FilterBuilder_clearCriteria(_1){var _2=this.clauseStack?this.clauseStack.animateMembers:null;if(this.clauseStack)this.clauseStack.animateMembers=false;while(this.clauses.length>0){this.removeClause(this.clauses[0])}
if(!_1&&!this.allowEmpty)this.addNewClause();if(this.clauseStack)this.clauseStack.animateMembers=_2}
,isc.A.addCriterion=function isc_FilterBuilder_addCriterion(_1,_2){if(_1.criteria){var _3=this.addSubClause(_1);for(var _4=0;_4<_1.criteria.length;_4++){_2=this.fieldData?this.fieldData[_1.criteria[_4].fieldName]:null;_3.addCriterion(_1.criteria[_4],_2)}}else{this.addNewClause(_1,_2)}}
,isc.A.handleKeyPress=function isc_FilterBuilder_handleKeyPress(_1,_2){if(_1.keyName==this.$10j){if(this.saveOnEnter){if(_2.firedOnTextItem){if(!this.creator&&this.search){this.search(this.getCriteria());return isc.EH.STOP_BUBBLING}}}}}
,isc.A.itemChanged=function isc_FilterBuilder_itemChanged(){if(this.creator&&isc.isA.Function(this.creator.itemChanged)){this.creator.itemChanged()}else{if(!this.creator&&isc.isA.Function(this.filterChanged)){this.filterChanged()}}}
,isc.A.fieldNameChanged=function isc_FilterBuilder_fieldNameChanged(_1){}
,isc.A.getEditorType=function isc_FilterBuilder_getEditorType(_1,_2){var _3=this.getPrimaryDS(_1),_4;var _5={};isc.addProperties(_5,_1);_5.canEdit=_1.canFilter;_1=_5;var _6=_3.getSearchOperator(_2,_1);if(_6.editorType)return _6.editorType;if(_6.getEditorType&&isc.isA.Function(_6.getEditorType)){return _6.getEditorType()}
if(_1){var _7=(_2=="equals"||_2=="notEqual"||_2=="lessThan"||_2=="greaterThan"||_2=="iBetween"||_2=="iBetweenInclusive"||_2=="between"||_2=="betweenInclusive"||_2=="greaterOrEqual"||_2=="lessOrEqual")?"RelativeDateItem":"DateRangeItem";if(!this.internalSearchForm){this.internalSearchForm=this.createAutoChild("internalSearchForm",{useAllDataSourceFields:false,dataSource:_3,fields:[_1],defaultDateEditorType:_7,allowEmptyValues:this.allowEmptyValues})}else{this.internalSearchForm.defaultDateEditorType=_7;this.internalSearchForm.setFields([_1])}
return this.internalSearchForm.getEditorType(_1)}else
return isc.FormItemFactory.getItemClassName({},"text",null)}
,isc.A.getSelectedClauses=function isc_FilterBuilder_getSelectedClauses(){var _1=[];if(this.showSelectionCheckbox){for(var i=0;i<this.clauses.length;i++){var c=this.clauses[i];if(c.topOperatorForm&&c.topOperatorForm.getValue("select")){_1.add(c)}}}
return _1}
);isc.B._maxIndex=isc.C+38;isc.FilterBuilder.registerStringMethods({search:"criteria",filterChanged:""})}
isc.defineClass("RuleEditor","VLayout");isc.A=isc.RuleEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.height=100;isc.A.validatorIsRule=true;isc.A.availableTypes=["matchesField","isOneOf","lengthRange","contains","doesntContain","substringCount","regexp","mask","floatPrecision","required","readOnly","isUnique","hasRelatedRecord","range","message","populate","setRequired"];isc.A.width=400;isc.A.components=["nameForm","mainForm","applyWhenForm","validatorForm","messageForm"];isc.A.showNameForm=false;isc.A.nameFormConstructor="DynamicForm";isc.A.nameFormDefaults={numCols:2};isc.A.nameItemDefaults={editorType:"TextItem",title:"Name"};isc.A.descriptionItemDefaults={editorType:"TextAreaItem",title:"Description"};isc.A.mainFormConstructor="DynamicForm";isc.A.mainFormDefaults={numCols:2,height:20};isc.A.fieldPickerConstructor="SelectItem";isc.A.fieldPickerDefaults={name:"fieldName",multiple:true,title:"FOR",showIf:function(){return this.creator.shouldShowFieldPicker()},pickListProperties:{showHeader:true,canSelectAll:false},getClientPickListData:function(){return this.form.creator.getFieldData()},valueField:"name",getPickListFilterCriteria:function(){var _1=this.getValue();if(_1==null||isc.isAn.emptyArray(_1))return null;if(isc.isAn.Array(_1))_1=_1[0];var _2=this.creator.getField(_1);if(_2)return{type:_2.type||"text"};return null},pickListWidth:200,pickListFields:[{name:"name",title:"Field Name",width:"*"},{name:"type",title:"Type",width:"*"}],changed:function(_1,_2,_3){if(this.pickList&&this.pickList.isVisible())this.filterPickList();this.creator.updateFieldName(_3)}};isc.A.triggerEventPickerConstructor="SelectItem";isc.A.triggerEventPickerDefaults={name:"event",startRow:true,title:"ON",valueMap:["editStart","editorEnter","editorExit","changed","submit","manual"],changed:function(_1,_2,_3){this.creator.updateTriggerEvent(_3)},showIf:function(){return this.creator.shouldShowTriggerEventPicker()}};isc.A.applyWhenFormConstructor="DynamicForm";isc.A.applyWhenFormDefaults={numCols:2,fixedColWidths:true,height:20,defaultItems:[{name:"applyWhen",labelAsTitle:true,showLabel:false,title:"WHEN",editorType:"CheckboxItem",hint:"Enable",width:20,changed:"this.form.creator.updateConditionalForm(value)"},{type:"CanvasItem",showTitle:true,title:null,name:"conditionalItem",showIf:"false",createCanvas:function(){return this.form.creator.createConditionalForm()}}]};isc.A.conditionalFormConstructor="FilterBuilder";isc.A.conditionalFormDefaults={showFieldTitles:false};isc.A.validatorFormConstructor="DynamicForm";isc.A.validatorFormDefaults={numCols:2,fixedColWidths:true,height:20};isc.A.typePickerConstructor="SelectItem";isc.A.typePickerDefaults={name:"type",title:"RULE",showIf:function(){var _1=this.form.creator;return _1.showTypePicker==false?false:true},getValueMap:function(){return this.creator.getTypeValueMap()},changed:function(_1,_2,_3){this.creator.updateValidatorType(_3)}};isc.A.messageFormConstructor="DynamicForm";isc.A.messageFormDefaults={numCols:2,width:"100%",height:20,defaultItems:[{name:"errorMessage",title:"MESSAGE",editorType:"TextItem",width:"*"}]};isc.A.valuesFormConstructor="FilterClause";isc.A.valuesFormDefaults={customGetValuesFunction:"getAttributesFromEditor",customSetValuesFunction:"setEditorAttributes",operatorAttribute:"type",fieldPickerProperties:{showIf:"return false"},getEditorType:function(_1,_2){if(_1&&isc.SimpleType.inheritsFrom(_1.type,"date"))return"RelativeDateItem";return null}};isc.B.push(isc.A.initWidget=function isc_RuleEditor_initWidget(){var _1=this.rule||this.validator;if(_1!=null){this.setRule(_1,true)}
if(this.showTypePicker==null&&this.validatorType!=null){this.showTypePicker=false}
this.addAutoChildren(this.components);if(this.nameForm!=null&&_1!=null){this.nameForm.setValue("name",_1.name);this.nameForm.setValue("description",_1.description)}
if(this.mainForm){this.fieldPicker=this.mainForm.getItem("fieldName");if(this.fieldName!=null)this.fieldPicker.setValue(this.fieldName);this.triggerEventPicker=this.mainForm.getItem("event");if(this.triggerEvent)this.triggerEventPicker.setValue(this.triggerEvent);if(!this.validatorIsRule){this.mainForm.setVisibility(isc.Canvas.HIDDEN)}}
if(this.applyWhenForm){if(this.applyWhen!=null){this.applyWhenForm.setValue("applyWhen",true);this.updateConditionalForm(true)}}
if(this.validatorForm){this.typePicker=this.validatorForm.getItem("type");if(this.validatorType!=null)this.typePicker.setValue(this.validatorType);if(this.validatorType!=null){this.updateValidatorType(this.validatorType)}}
if(this.messageForm){if(this.rule&&this.rule.errorMessage){this.messageForm.setValue("errorMessage",this.rule.errorMessage)}}
if(_1!=null){this.setClauseAttributes(_1)}
return this.Super("initWidget",arguments)}
,isc.A.getDynamicDefaults=function isc_RuleEditor_getDynamicDefaults(_1){switch(_1){case"nameForm":var _2=isc.addProperties({name:"name"},this.nameItemProperties,this.nameItemDefaults),_3=isc.addProperties({name:"description"},this.descriptionItemProperties,this.descriptionItemDefaults);return{items:[_2,_3]};case"mainForm":var _4=isc.addProperties({creator:this,editorType:this.fieldPickerConstructor},this.fieldPickerDefaults,this.fieldPickerProperties),_5=isc.addProperties({creator:this,editorType:this.triggerEventPickerConstructor},this.triggerEventPickerDefaults,this.triggerEventPickerProperties)
return{items:[_4,_5]};case"applyWhenForm":return;case"validatorForm":var _6=isc.addProperties({creator:this,editorType:this.typePickerConstructor},this.typePickerDefaults,this.typePickerProperties);var _7={name:"valuesItem",editorType:"CanvasItem",showTitle:true,title:null,showIf:"false",canvas:this.getValuesForm(this.validatorType)}
return{disabled:(this.fieldName==null),items:[_6,_7]};case"messageForm":return}}
,isc.A.shouldShowFieldPicker=function isc_RuleEditor_shouldShowFieldPicker(){if(this.showFieldPicker!=null)return this.showFieldPicker;return this.validatorIsRule}
,isc.A.getField=function isc_RuleEditor_getField(_1){return this.dataSource?this.dataSource.getField(_1):isc.DataSource.getFieldFromDataSources(_1,this.dataSources)}
,isc.A.getFieldData=function isc_RuleEditor_getFieldData(){if(this.$89u==null){var _1=this.dataSources;if(_1!=null){this.$89u=isc.DataSource.getCombinedDataSourceFields(this.dataSources)}else if(this.dataSource){var _2=isc.getKeys(this.dataSource.getFields());this.$89u=_2.duplicate()}
this.$896=[];for(var i=0;i<this.$89u.length;i++){var _4=this.$89u[i],_5=this.getField(_4);this.$896[i]={name:_4,type:_5.type||"text"}}}
return this.$896}
,isc.A.updateFieldName=function isc_RuleEditor_updateFieldName(_1){this.fieldName=_1;if(this.validatorForm){if(_1==null||(isc.isAn.Array(_1)&&_1.length==0)){this.validatorForm.setValue("type",null);this.validatorType=null;this.validatorForm.setDisabled(true)}else{if(this.validatorForm.isDisabled())this.validatorForm.enable()}}
if(isc.isAn.Array(_1))_1=_1[0];if(_1!=null&&this.validatorType!=null){this.valuesForm.fieldName=_1;this.valuesForm.clause.setValue("fieldName",_1);this.valuesForm.clause.setValue("operator",this.validatorType);var _2=this.getValidatorDefinition(this.validatorType);this.valuesForm.updateValueItems(this.valuesForm.getField(_1),_2,_1)}
this.updateValuesFormVisibility()}
,isc.A.updateValuesFormVisibility=function isc_RuleEditor_updateValuesFormVisibility(){if(this.valuesForm){if(this.fieldName==null||this.validatorType==null){this.validatorForm.getItem("valuesItem").hide()}else{if(!this.valuesForm.isVisible()){this.validatorForm.getItem("valuesItem").show()}}}}
,isc.A.shouldShowTriggerEventPicker=function isc_RuleEditor_shouldShowTriggerEventPicker(){if(this.showTriggerEventPicker!=null)return this.showTriggerEventPicker;return this.validatorIsRule}
,isc.A.createConditionalForm=function isc_RuleEditor_createConditionalForm(){this.conditionalForm=this.createAutoChild("conditionalForm",{dataSource:this.dataSource,dataSources:this.dataSources});return this.conditionalForm}
,isc.A.updateConditionalForm=function isc_RuleEditor_updateConditionalForm(_1){var _2=this.applyWhenForm.getItem("conditionalItem");if(!_1){_2.hide()}else{var _3=this.applyWhen||{};this.conditionalForm.setCriteria(_3);_2.show()}}
,isc.A.getTypeValueMap=function isc_RuleEditor_getTypeValueMap(){var _1=this.availableTypes,_2=this.fieldName,_3;if(_2==null)_2=[];else if(!isc.isAn.Array(_2))_2=[_2];for(var i=0;i<_2.length;i++){var _5=this.getField(_2[i]);if(_5==null){this.logWarn("unable to retrieve field for:"+_2);continue}
var _6=isc.SimpleType.getBaseType(_5.type||"text");if(_3==null){_3=_6}else if(_3!=_6){_3=null;break}}
if(this.availableTypeMap==null){this.availableTypeMap={};for(var i=0;i<this.availableTypes.length;i++){var _7=null;if(this.availableTypes[i]=="range"){_7=["date","time","float","integer"]}else{var _8=this.getValidatorDefinition(this.availableTypes[i]);_7=_8.dataType||"none"}
if(!isc.isA.Array(_7))_7=[_7];for(var _9=0;_9<_7.length;_9++){if(this.availableTypeMap[_7[_9]]==null){this.availableTypeMap[_7[_9]]=[this.availableTypes[i]]}else{this.availableTypeMap[_7[_9]].add(this.availableTypes[i])}}}}
var _1=[];_1.addList(this.availableTypeMap["none"]);if(_3!=null)_1.addList(this.availableTypeMap[_3]);return _1}
,isc.A.updateValidatorType=function isc_RuleEditor_updateValidatorType(_1){this.validatorType=_1;if(_1!=null){var _2=this.valuesForm,_3=this.getValuesForm(_1);if(_2!=_3){this.valuesForm=_3;this.validatorForm.getItem("valuesItem").setCanvas(this.valuesForm)}}
this.updateValuesFormVisibility()}
,isc.A.getValidatorDefinition=function isc_RuleEditor_getValidatorDefinition(_1){if(_1==null)_1=this.validatorType;if(_1==null)return null;if(_1=="range"){var _2=this.fieldName,_1=_2?_2.type:"integer",_3=isc.SimpleType.getBaseType(_1);if(_3=="date"||_3=="datetime"){_1="dateRange"}else if(_3=="time"){_1="timeRange"}else if(_3=="float"){_1="floatRange"}else{_1="integerRange"}}
return isc.Validator.$74u[_1]}
,isc.A.getValuesForm=function isc_RuleEditor_getValuesForm(_1){if(_1!=null){var _2=this.getValidatorDefinition(_1),_3=_2.valueType}
var _4=this.fieldName;if(isc.isAn.Array(_4))_4=_4[0];if(this.valuesForm){this.valuesForm.updateValueItems(this.valuesForm.getField(_4),_2,_4);this.valuesForm.clause.setValue("operator",_1);return this.valuesForm}else{var _5=this.valuesForm=this.createAutoChild("valuesForm",{visibility:(this.fieldName?"inherit":"hidden"),showRemoveButton:false,dataSources:this.dataSources,dataSource:this.dataSource,fieldName:_4,operatorType:_1});var _6=_5.clause;_6.getItem("operator").hide();_6.getItem("operator").addUnknownValues=true;return _5}}
,isc.A.setValidatorType=function isc_RuleEditor_setValidatorType(_1){this.validatorType=_1;this.validatorForm.setValue("type",_1);this.updateValidatorType(_1)}
,isc.A.setFieldName=function isc_RuleEditor_setFieldName(_1){if(this.fieldPicker){this.fieldPicker.setValue(_1)}
this.updateFieldName(_1)}
,isc.A.setTriggerEvent=function isc_RuleEditor_setTriggerEvent(_1){if(this.triggerEventPicker){this.triggerEventPicker.setValue(_1)}
this.updateTriggerEvent(_1)}
,isc.A.updateTriggerEvent=function isc_RuleEditor_updateTriggerEvent(_1){this.triggerEvent=_1}
,isc.A.setApplyWhen=function isc_RuleEditor_setApplyWhen(_1){this.applyWhen=_1;this.applyWhenForm.setValue("applyWhen",(this.applyWhen!=null));this.updateConditionalForm(_1!=null)}
,isc.A.getApplyWhen=function isc_RuleEditor_getApplyWhen(){if(this.applyWhenForm.getValue("applyWhen")){this.applyWhen=this.conditionalForm.getCriteria()}else{this.applyWhen=null}
return this.applyWhen}
,isc.A.getAttributesFromClause=function isc_RuleEditor_getAttributesFromClause(){var _1=this.getValidatorDefinition();var _2=this.fieldName,_3=this.valuesForm;if(isc.isAn.Array(_2))_2=_2[0];var _4=_3.getClauseValues(_2,_1);return _4}
,isc.A.setClauseAttributes=function isc_RuleEditor_setClauseAttributes(_1){if(this.valuesForm==null)return;var _2=this.getValidatorDefinition();var _3=this.fieldName;if(isc.isAn.Array(_3))_3=_3[0];this.valuesForm.setClauseValues(_3,_2,_1)}
,isc.A.getValidator=function isc_RuleEditor_getValidator(){return this.getRule()}
,isc.A.getRule=function isc_RuleEditor_getRule(){if(this.validatorType==null||this.fieldName==null)return null;var _1={};_1.type=this.validatorType;if(this.nameForm!=null){var _2=this.nameForm.getValue("name");if(_2!=null)_1.name=_2;var _3=this.nameForm.getValue("description");if(_3!=null)_1.description=_3}
if(this.valuesForm!=null){var _4=this.getAttributesFromClause();isc.addProperties(_1,_4)}
if(this.validatorIsRule){_1.fieldName=this.fieldName;if(this.triggerEvent)_1.triggerEvent=this.triggerEvent}else{delete _1.fieldName}
_1.errorMessage=this.messageForm.getValue("errorMessage");var _5=this.getApplyWhen();if(_5!=null)_1.applyWhen=_5;return _1}
,isc.A.validate=function isc_RuleEditor_validate(){var _1=false;if(this.nameForm)_1=this.nameForm.validate()==false;if(this.mainForm)_1=(this.mainForm.validate()==false)||_1;if(this.applyWhenForm&&this.applyWhenForm.getValue("applyWhen")){_1=(this.conditionalForm.validate()==false)||_1}
if(this.validatorForm){_1=(this.validatorForm.validate()==false)||_1;if(this.valuesForm)_1=(this.valuesForm.validate()==false)||_1}
if(this.messageForm)_1=(this.messageForm.validate()==false)||_1;return!_1}
,isc.A.setRule=function isc_RuleEditor_setRule(_1,_2){this.validator=this.rule=_1;if(_2){this.validatorType=_1.type;this.applyWhen=_1.applyWhen;this.fieldName=_1.fieldName;this.triggerEvent=_1.triggerEvent}else{if(this.nameForm){this.nameForm.setValue("name",_1.name);this.nameForm.setValue("description",_1.description)}
if(_1.fieldName!=null)this.setFieldName(_1.fieldName);this.setTriggerEvent(_1.triggerEvent);this.setValidatorType(_1.type);this.setApplyWhen(_1.applyWhen);this.messageForm.setValue("errorMessage",_1.errorMessage);this.setClauseAttributes(_1)}}
,isc.A.setValidator=function isc_RuleEditor_setValidator(_1){this.setRule(_1)}
,isc.A.clearRule=function isc_RuleEditor_clearRule(){this.rule=this.validator=null;this.setFieldName(null);this.setValidatorType(null);this.setApplyWhen(null);this.setTriggerEvent(null);if(this.nameForm)this.nameForm.clearValues();if(this.messageForm)this.messageForm.clearValue("errorMessage")}
,isc.A.clearValidator=function isc_RuleEditor_clearValidator(){return this.clearRule()}
);isc.B._maxIndex=isc.C+29;if(isc.DynamicForm){isc.defineClass("SubstringCountEditor","CanvasItem");isc.A=isc.SubstringCountEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canvasConstructor="DynamicForm";isc.A.canvasDefaults={numCols:3};isc.A.substringFieldDefaults={name:"substring",showTitle:false,type:"text",colSpan:"*",width:"*"};isc.A.countFieldDefaults={name:"count",showTitle:false,hint:"Count",showHintInField:true,width:50,type:"integer"};isc.A.operatorFieldDefaults={name:"operator",title:"Operator",editorType:"SelectItem",width:50,defaultValue:"==",allowEmptyValue:false,valueMap:["==","!=","<","<=",">",">="]};isc.B.push(isc.A.createCanvas=function isc_SubstringCountEditor_createCanvas(_1,_2){var _3=isc.addProperties({},this.substringFieldDefaults,this.substringFieldProperties),_4=isc.addProperties({},this.countFieldDefaults,this.countFieldProperties),_5=isc.addProperties({},this.operatorFieldDefaults,this.operatorFieldProperties);return this.canvas=this.createAutoChild("canvas",{items:[_3,_4,_5]})}
);isc.B._maxIndex=isc.C+1;isc.defineClass("FloatRangeEditor","CanvasItem");isc.A=isc.FloatRangeEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canvasConstructor="DynamicForm";isc.A.canvasDefaults={numCols:2};isc.A.minFieldDefaults={name:"min",showTitle:false,type:"float",hint:"Min",showHintInField:true};isc.A.maxFieldDefaults={name:"max",showTitle:false,type:"float",hint:"Max",showHintInField:true};isc.A.exclusiveFieldDefaults={name:"exclusive",title:"Exclusive",colSpan:"*",prompt:"Range is exclusive (does not include min/max values)",type:"boolean",editorType:"CheckboxItem",defaultValue:false};isc.B.push(isc.A.createCanvas=function isc_FloatRangeEditor_createCanvas(_1,_2){var _3=isc.addProperties({},this.minFieldDefaults,this.minFieldProperties),_4=isc.addProperties({},this.maxFieldDefaults,this.maxFieldProperties),_5=isc.addProperties({},this.exclusiveFieldDefaults,this.exclusiveFieldProperties);return this.canvas=this.createAutoChild("canvas",{items:[_3,_4,_5]})}
);isc.B._maxIndex=isc.C+1;isc.defineClass("FloatPrecisionEditor","CanvasItem");isc.A=isc.FloatPrecisionEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canvasConstructor="DynamicForm";isc.A.canvasDefaults={numCols:1};isc.A.precisionFieldDefaults={name:"precision",showTitle:false,type:"float",hint:"Precision",showHintInField:true};isc.A.roundFieldDefaults={showTitle:false,name:"roundToPrecision",title:"Round to precision",type:"boolean",editorType:"CheckboxItem",defaultValue:false};isc.B.push(isc.A.createCanvas=function isc_FloatPrecisionEditor_createCanvas(_1,_2){var _3=isc.addProperties({},this.precisionFieldDefaults,this.precisionFieldProperties),_4=isc.addProperties({},this.roundFieldDefaults,this.roundFieldProperties);return this.canvas=this.createAutoChild("canvas",{items:[_3,_4]})}
);isc.B._maxIndex=isc.C+1;isc.defineClass("MaskRuleEditor","CanvasItem");isc.A=isc.MaskRuleEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.canvasConstructor="DynamicForm";isc.A.canvasDefaults={numCols:1};isc.A.maskFieldDefaults={name:"mask",editorType:"TextItem",showTitle:false,hint:"mask",showHintInField:true};isc.A.transformFieldDefaults={name:"transformTo",editorType:"TextItem",showTitle:false,hint:"transformTo",showHintInField:true};isc.B.push(isc.A.createCanvas=function isc_MaskRuleEditor_createCanvas(_1,_2){var _3=isc.addProperties({},this.maskFieldDefaults,this.maskFieldProperties),_4=isc.addProperties({},this.transformFieldDefaults,this.transformFieldProperties);return this.canvas=this.createAutoChild("canvas",{items:[_3,_4]})}
);isc.B._maxIndex=isc.C+1;isc.defineClass("PopulateRuleEditor","BlurbItem");isc.A=isc.PopulateRuleEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.emptyFormulaText="Click the icon to select a formula";isc.A.formulaVarsTitle="Formula Variables:";isc.A.formulaTitle="Formula:";isc.A.editFormulaPrompt="Click to edit formula";isc.A.icons=[{click:"item.showFormulaWindow()"}];isc.A.formulaWindowConstructor="Window";isc.A.formulaWindowDefaults={title:"Formula Editor",showMinimizeButton:false,showMaximizeButton:false,isModal:true,showModalMask:true,autoSize:true,autoCenter:true,autoDraw:true,headerIconProperties:{padding:1,src:"[SKINIMG]ListGrid/formula_menuItem.png"},closeClick:function(){this.items.get(0).completeEditing(true)}};isc.A.formulaBuilderConstructor="FormulaBuilder";isc.A.formulaBuilderDefaults={width:300,showTitleField:false,showAutoHideCheckBox:false,showSaveAddAnotherButton:false,fireOnClose:function(){this.creator.userEditComplete(!this.cancelled)}};isc.B.push(isc.A.formatValue=function isc_PopulateRuleEditor_formatValue(_1,_2,_3,_4){if(_1==null||_1.formula==null){return this.emptyFormulaText}
return"<table class="+this.getTextBoxStyle()+"><tr><td>"+this.formulaVarsTitle+"</td><td>"+isc.JSON.encode(_1.formulaVars,{prettyPrint:true})+"</td></tr>"+"<tr><td>"+this.formulaTitle+"</td><td>"+_1.formula+"</td></tr></table>"}
,isc.A.init=function isc_PopulateRuleEditor_init(){this.icons[0].prompt=this.editFormulaPrompt;return this.Super("init",arguments)}
,isc.A.showFormulaWindow=function isc_PopulateRuleEditor_showFormulaWindow(){if(this.formulaBuilder==null){this.formulaBuilder=this.createAutoChild("formulaBuilder",{dataSource:this.form.creator.dataSource,dataSources:this.form.creator.dataSources,mathFunctions:isc.MathFunction.getDefaultFunctionNames()});this.formulaWindow=this.createAutoChild("formulaWindow",{items:[this.formulaBuilder]})}
this.formulaBuilder.setValue("");this.formulaWindow.show()}
,isc.A.userEditComplete=function isc_PopulateRuleEditor_userEditComplete(_1){if(_1){var _2=this.formulaBuilder.getBasicValueObject(),_3,_4;if(_2!=null){_3=_2.text;_4=_2.formulaVars}
if(_3!=null){this.storeValue({formula:_3,formulaVars:_4})}else{this.storeValue(null)}
this.redraw()}
this.formulaWindow.clear()}
);isc.B._maxIndex=isc.C+4;isc.defineClass("ReadOnlyRuleEditor","SelectItem");isc.A=isc.ReadOnlyRuleEditor.getPrototype();isc.A.defaultValue=isc.Validator.READONLY;isc.A.valueMap=[isc.Validator.HIDDEN,isc.Validator.DISABLED,isc.Validator.READONLY]}
isc.setScreenReaderMode=function(_1){isc.screenReader=_1}
isc.liteAria=null;isc.A=isc.Canvas;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.ariaEnabled=function isc_c_Canvas_ariaEnabled(){return isc.screenReader||isc.screenReader!==false&&((isc.Browser.isIE&&isc.Browser.version>=8)||!isc.Browser.isIE)}
,isc.A.useLiteAria=function isc_c_Canvas_useLiteAria(){if(isc.liteAria!=null)return isc.liteAria;if(isc.screenReader==true)return false;return(isc.Browser.isIE&&isc.Browser.version<9)}
,isc.A.setAriaRole=function isc_c_Canvas_setAriaRole(_1,_2){if(this.logIsDebugEnabled("aria")){this.logDebug("ARIA role changed to: "+_2+" on element: "+this.echoLeaf(_1),"aria")}
_1.setAttribute("role",_2)}
,isc.A.setAriaState=function isc_c_Canvas_setAriaState(_1,_2,_3){if(!_1)return;if(this.logIsInfoEnabled("aria")){this.logInfo("ARIA state: "+_2+": "+_3+", set on element: "+isc.echoLeaf(_1),"aria")}
_3=isc.makeXMLSafe(_3,false,true,true,false,true,true);_1.setAttribute("aria-"+_2,_3)}
,isc.A.setAriaStates=function isc_c_Canvas_setAriaStates(_1,_2){if(!_1)return;if(_2==null)return;for(var _3 in _2){this.setAriaState(_1,_3,_2[_3])}}
,isc.A.clearAriaState=function isc_c_Canvas_clearAriaState(_1,_2){if(!_1)return;_1.removeAttribute("aria-"+_2)}
,isc.A.getAriaStateAttributes=function isc_c_Canvas_getAriaStateAttributes(_1){var _2="";if(_1){for(var _3 in _1){var _4=_1[_3];if(isc.isA.String(_4)){_4=isc.makeXMLSafe(_4,false,true,true,false,true,true)}
_2+=" aria-"+_3+"='"+_4+"'"}}
return _2}
);isc.B._maxIndex=isc.C+7;isc.A=isc.Canvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.setAriaRole=function isc_Canvas_setAriaRole(_1){isc.Canvas.setAriaRole(this.getClipHandle(),_1)}
,isc.A.setAriaState=function isc_Canvas_setAriaState(_1,_2){isc.Canvas.setAriaState(this.getClipHandle(),_1,_2)}
,isc.A.setAriaStates=function isc_Canvas_setAriaStates(_1){isc.Canvas.setAriaStates(this.getClipHandle(),_1)}
,isc.A.clearAriaState=function isc_Canvas_clearAriaState(_1){isc.Canvas.clearAriaState(this.getClipHandle(),_1)}
,isc.A.getAriaStateAttributes=function isc_Canvas_getAriaStateAttributes(){return isc.Canvas.getAriaStateAttributes(this.ariaState)}
);isc.B._maxIndex=isc.C+5;if(isc.DynamicForm){isc.A=isc.FormItem.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.setAriaRole=function isc_FormItem_setAriaRole(_1){var _2=this.getFocusElement();if(_2!=null)isc.Canvas.setAriaRole(_2,_1)}
,isc.A.setAriaState=function isc_FormItem_setAriaState(_1,_2){var _3=this.getFocusElement();if(_3!=null)isc.Canvas.setAriaState(_3,_1,_2)}
,isc.A.setAriaStates=function isc_FormItem_setAriaStates(_1){var _2=this.getFocusElement();if(_2!=null)isc.Canvas.setAriaStates(_2,_1)}
,isc.A.clearAriaState=function isc_FormItem_clearAriaState(_1){var _2=this.getFocusElement();if(_2!=null)isc.Canvas.clearAriaState(_2,_1)}
,isc.A.getAriaState=function isc_FormItem_getAriaState(){var _1={};if(this.required&&this.form&&this.form.hiliteRequiredFields)_1.required=true;if(this.hasErrors()){_1.invalid=true;var _2=this.getErrorIconId();_1.describedby=_2}
if(this.isDisabled())_1.disabled=true;if(isc.isA.CheckboxItem(this))_1.checked=!!this.getValue();return _1}
,isc.A.addContentRoles=function isc_FormItem_addContentRoles(){if(!isc.Canvas.ariaEnabled()||isc.Canvas.useLiteAria())return;if(!this.$kk()||!this.ariaRole)return;this.setAriaRole(this.ariaRole);var _1;if(this.outerAriaRole){_1=this.getHandle();if(_1!=null)isc.Canvas.setAriaRole(_1,this.outerAriaRole)}
if(this.title){var _2;if(this.hasDataElement()){_2=this.getDataElement()}else if(this.outerAriaRole){_2=_1!=null?_1:this.getHandle()}else{_2=this.$15h()}
if(_2!=null){isc.Canvas.setAriaState(_2,"label",this.title)}}
if(this.ariaState)this.setAriaStates(this.ariaState);this.setAriaStates(this.getAriaState())}
);isc.B._maxIndex=isc.C+6;isc.A=isc.TextAreaItem.getPrototype();isc.A.ariaState={multiline:true};isc.A=isc.ComboBoxItem.getPrototype();isc.A.ariaState={autocomplete:"list"};isc.A.ariaRole="combobox";isc.A=isc.SelectItem.getPrototype();isc.A.ariaRole="option";isc.A.outerAriaRole="listbox";isc.A.ariaState={expanded:false,selected:true};isc.A=isc.StaticTextItem.getPrototype();isc.A.ariaRole="textbox";isc.A.ariaState={disabled:true}}
if(isc.GridRenderer){isc.A=isc.GridRenderer.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.setRowAriaState=function isc_GridRenderer_setRowAriaState(_1,_2,_3){var _4=this.getTableElement(_1);if(_4==null)return;isc.Canvas.setAriaState(_4,_2,_3)}
,isc.A.setRowAriaStates=function isc_GridRenderer_setRowAriaStates(_1,_2){var _3=this.getTableElement(_1);if(_3==null)return;isc.Canvas.setAriaStates(_3,_2)}
);isc.B._maxIndex=isc.C+2;isc.A=isc.ListGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.ariaRole="list";isc.A.rowRole="listitem";isc.B.push(isc.A.getRowRole=function isc_ListGrid_getRowRole(_1,_2){if(_2&&_2.isSeparator)return"separator";return this.rowRole}
,isc.A.getRowAriaState=function isc_ListGrid_getRowAriaState(_1,_2){if(!isc.Canvas.ariaEnabled()||isc.Canvas.useLiteAria())return;var _3;if(!this.showAllRecords&&this.data!=null){_3={setsize:this.getTotalRows(),posinset:_1}}
if(this.selection&&this.selection.isSelected&&this.selection.isSelected(_1)){if(_3==null)_3={}
_3.selected=true}
return _3}
);isc.B._maxIndex=isc.C+2;isc.A=isc.TreeGrid.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.ariaRole="tree";isc.A.rowRole="treeitem";isc.B.push(isc.A.getRowRole=function isc_TreeGrid_getRowRole(_1,_2){return this.rowRole}
,isc.A.getRowAriaState=function isc_TreeGrid_getRowAriaState(_1,_2){if(!isc.Canvas.ariaEnabled()||isc.Canvas.useLiteAria())return;var _3=this.data,_4=!!(this.selection&&this.selection.isSelected&&this.selection.isSelected(_2)),_5=_3.getLevel(_2);var _6={selected:_4,level:_5,setsize:this.getTotalRows(),posinset:_1};if(_3.isFolder(_2))_6.expanded=!!_3.isOpen(_2);return _6}
);isc.B._maxIndex=isc.C+2;isc.A=isc.Menu.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.ariaRole="menu";isc.B.push(isc.A.getRowRole=function isc_Menu_getRowRole(_1,_2){if(!_2||_2.isSeparator)return"separator";if(_2.checked||_2.checkIf||_2.checkable)return"menuitemcheckable";if(_2.radio)return"menuitemradio";return"menuitem"}
,isc.A.getRowState=function isc_Menu_getRowState(_1){if(this.hasSubmenu(this.getItem(_1)))return{haspopup:true}}
);isc.B._maxIndex=isc.C+2;isc.A=isc.MenuButton.getPrototype();isc.A.ariaRole="button";isc.A.ariaState={haspopup:true};isc.A=isc.MenuBar.getPrototype();isc.A.ariaRole="menubar"}
(function(){var _1={Button:"button",StretchImgButton:"button",ImgButton:"button",Label:"label",SectionHeader:"heading",ImgSectionHeader:"heading",CheckboxItem:"checkbox",Slider:"slider",TextItem:"textbox",TextAreaItem:"textbox",Window:"dialog",Toolbar:"toolbar",HTMLFlow:"article",HTMLPane:"article",TabBar:"tablist",PaneContainer:"tabpanel",ImgTab:"tab",EdgedCanvas:"presentation",BackMask:"presentation"}
for(var _2 in _1){var _3=isc.ClassFactory.getClass(_2);if(_3)_3.addProperties({ariaRole:_1[_2]})}})();if(isc.ListGrid!=null){isc.ClassFactory.defineClass("DataSourceEditor","VLayout");isc.A=isc.DataSourceEditor.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.overflow="visible";isc.A.mainEditorDefaults={_constructor:"ComponentEditor",autoDraw:false,numCols:8,overflow:"visible",dataSource:"DataSource",fields:[{name:"ID",title:"ID",required:true},{name:"dropExtraFields"},{name:"autoDeriveSchema"},{type:"section",defaultValue:"XPath Binding",showIf:"values.dataFormat != 'iscServer'",itemIds:["dataURL","selectBy","recordXPath","recordName"]},{name:"dataURL",showIf:"values.dataFormat != 'iscServer'"},{name:"selectBy",title:"Select Records By",shouldSaveValue:false,valueMap:{tagName:"Tag Name",xpath:"XPath Expression"},defaultValue:"xpath",redrawOnChange:true,showIf:"values.dataFormat == 'xml'"},{name:"recordXPath",showIf:"values.dataFormat != 'iscServer' && form.getItem('selectBy').getValue() == 'xpath'"},{name:"recordName",showIf:"values.dataFormat == 'xml' && values.selectBy == 'tagName'"},{type:"section",defaultValue:"SQL Binding",showIf:"values.serverType == 'sql' || values.serverType == 'hibernate'",itemIds:["dbName","schemaName","tableName"]},{name:"dbName",showIf:"values.serverType == 'sql'"},{name:"schema",showIf:"values.serverType == 'sql'"},{name:"tableName",showIf:"values.serverType == 'sql' || values.serverType == 'hibernate'"},{name:"beanClassName",showIf:"values.serverType == 'sql' || values.serverType == 'hibernate'"},{type:"section",defaultValue:"Record Titles",sectionExpanded:false,itemIds:["title","pluralTitle","titleField"]},{name:"title"},{name:"pluralTitle"},{name:"titleField"}]};isc.A.fieldEditorDefaults={_constructor:"ListEditor",autoDraw:false,inlineEdit:true,dataSource:"DataSourceField",saveLocally:true,gridButtonsOrientation:"right",fields:[{name:"name",treeField:true},{name:"title"},{name:"type",width:60},{name:"required",title:"Req.",width:40,canToggle:true},{name:"hidden",width:40},{name:"length",width:60},{name:"primaryKey",title:"is PK",width:40}],formProperties:{numCols:4,initialGroups:10},formFields:[{name:"name",canEdit:false},{name:"type"},{name:"title"},{name:"primaryKey"},{name:"valueXPath",colSpan:2,showIf:function(){var _1=this.form.creator,_2=_1?_1.creator.mainEditor:null;return(_2&&_2.getValues().dataFormat!='iscServer')}},{type:"section",defaultValue:"Value Constraints",itemIds:["required","length","valueMap"]},{name:"valueMap",rowSpan:2},{name:"required"},{name:"length"},{type:"section",defaultValue:"Component Binding",itemIds:["hidden","detail","canEdit"]},{name:"canEdit"},{name:"hidden"},{name:"detail"},{type:"section",defaultValue:"Relations",sectionExpanded:false,itemIds:["foreignKey","rootValue"]},{name:"foreignKey"},{name:"rootValue"}],gridDefaults:{editEvent:"click",listEndEditAction:"next",autoParent:"gridLayout",selectionType:isc.Selection.SINGLE,recordClick:"this.creator.recordClick(record)",modalEditing:true,editorEnter:"if (this.creator.moreButton) this.creator.moreButton.enable()",selectionChanged:function(){if(this.anySelected()&&this.creator.moreButton){this.creator.moreButton.enable()}},contextMenu:{data:[{title:"Remove",click:"target.creator.removeRecord()"}]},styleName:"rightBorderOnly",validateByCell:true,leaveScrollbarGap:false,alternateRecordStyles:true,canRemoveRecords:true,canEdit:true,canEditCell:function(_1,_2){var _3=this.getRecord(_1),_4=this.getField(_2),_5=_4[this.fieldIdProperty],_6=(_5=="name"||_5=="title");if(isc.isA.TreeGrid(this)){if(_3.isFolder&&!(_6||_5=="required"||_5=="hidden")){return false}}
else{if(this.getDataSource().fieldIsComplexType(_4)&&!_6)
return false}
return this.Super('canEditCell',arguments)}},newRecord:function(){if(this.creator.canEditChildSchema){var _1=this.grid,_2=_1.data,_3=this.getSelectedNode();if(!_3)_3=_2.root;var _4=_2.getParent(_3)
if(_3){if(!_3.isFolder)_3=_4;var _5={name:this.getNextUniqueFieldName(_3,"field"),id:this.getNextUnusedNodeId(),parentId:_3?_3.id:null};this.addNode(_5,_3)}}else this.Super("newRecord",arguments)},getSelectedNode:function(){return this.grid.getSelectedRecord()},addNode:function(_1,_2){var _3=this.grid.data;_3.linkNodes([_1])},getNextUniqueFieldName:function(_1,_2){var _3=_1?_1.fields||[]:[],_4=1;if(!_2||_2.length==0)_2="field";if(_3&&_3.length>0){for(var i=0;i<_3.length;i++){var _6=_3.get(i),_7=_6.name;if(_7.substring(0,_2.length)==_2&&_7.length>_2.length){var _8=parseInt(_7.substring(_2.length));if(!isNaN(_8)&&_8>=_4)
_4=_8+1}}}
return _2+_4},getNextUnusedNodeId:function(){var _1=this.grid.data;for(var i=1;i<10000;i++){var _3=_1.findById(i);if(!_3)return i}
return 1}};isc.A.newButtonDefaults={_constructor:isc.AutoFitButton,autoParent:"gridButtons",title:"New Field",click:"this.creator.newRecord()"};isc.A.moreButtonDefaults={_constructor:isc.AutoFitButton,autoParent:"gridButtons",click:"this.creator.editMore()",disabled:true};isc.A.buttonLayoutDefaults={_constructor:"HLayout",width:"100%"};isc.A.saveButtonDefaults={_constructor:"IButton",autoDraw:false,title:"Save",autoFit:true,autoParent:"buttonLayout",click:function(){var _1=true;if(this.creator.showMainEditor!=false)_1=this.creator.mainEditor.validate();if(_1&&this.creator.fieldEditor.validate())this.creator.save()}};isc.A.addChildButtonDefaults={_constructor:"IButton",autoDraw:false,title:"Add Child Object",autoFit:true,click:function(){var _1=this.creator.fieldEditor,_2=_1.grid,_3=_2.data,_4=_2.getSelectedRecord()||_3.root,_5=_3.getParent(_4),_6={isFolder:true,children:[],multiple:true,childTagName:"item"};if(_4){if(!_4.isFolder)_4=_5;_6.name=_1.getNextUniqueFieldName(_4,"child"),_6.id=_1.getNextUnusedNodeId(),_6.parentId=_4.id;_3.linkNodes([_6],_5);_3.openFolder(_6)}}};isc.A.mainStackDefaults={_constructor:"SectionStack",overflow:"visible",width:"100%",height:"100%",visibilityMode:"multiple"};isc.A.instructionsSectionDefaults={_constructor:"SectionStackSection",title:"Instructions",expanded:true,canCollapse:true};isc.A.instructionsDefaults={_constructor:"HTMLFlow",autoFit:true,padding:10};isc.A.mainSectionDefaults={_constructor:"SectionStackSection",title:"DataSource Properties",expanded:true,canCollapse:false,showHeader:false};isc.A.fieldSectionDefaults={_constructor:"SectionStackSection",title:"DataSource Fields &nbsp;<span style='color:#BBBBBB'>(click to edit or press New)</span>",expanded:true,canCollapse:true};isc.A.deriveFieldsSectionDefaults={_constructor:"SectionStackSection",title:"Derive Fields From SQL",expanded:false,canCollapse:true};isc.A.bodyProperties={overflow:"auto",layoutMargin:10};isc.A.deriveFormDefaults={_constructor:"DynamicForm"};isc.A.previewGridDefaults={_constructor:"ListGrid",showFilterEditor:true};isc.A.canEditChildSchema=false;isc.A.canAddChildSchema=false;isc.B.push(isc.A.editNew=function isc_DataSourceEditor_editNew(_1,_2,_3){if(_1.defaults){this.paletteNode=_1;this.start(_1.defaults,_2,true,_3)}else{this.start(_1,_2,true,_3)}}
,isc.A.editSaved=function isc_DataSourceEditor_editSaved(_1,_2,_3){this.start(_1,_2,false,_3)}
,isc.A.start=function isc_DataSourceEditor_start(_1,_2,_3,_4){if(_4){this.mainStack.showSection(0);this.instructions.setContents(_4)}else{this.mainStack.hideSection(0)}
if(this.mainEditor)this.mainEditor.clearValues();if(this.fieldEditor)this.fieldEditor.setData(null);this.saveCallback=_2;this.logWarn("editing "+(_3?"new ":"")+"DataSource: "+this.echo(_1));if(!_1){return this.show()}
this.dsClass=_1.Class;if(_3){if(isc.isA.DataSource(_1)){var _5=_1.sfName;_1=_1.getSerializeableFields();if(_5)_1.sfName=_5;this.logWarn("editing new DataSource from live DS, data: "+this.echo(_1))}else{_1.ID=this.getUniqueDataSourceID()}
this.$31u(_1)}else{isc.DMI.callBuiltin({methodName:"loadSharedXML",callback:this.getID()+".$37p(data)",arguments:["DS",_1.ID]})}}
,isc.A.getUniqueDataSourceID=function isc_DataSourceEditor_getUniqueDataSourceID(){return"newDataSource"}
,isc.A.$37p=function isc_DataSourceEditor__loadSchemaReply(_1){isc.captureInitData=true;var _2=isc.eval(_1.js);isc.captureInitData=null;var _3=_2.defaults;this.logWarn("captured DS initData: "+this.echo(_3));if(_3.serverType=="sql")_3.dataFormat="iscServer";if(_3.recordXPath!=null&&_3.dataFormat==null){_3.dataFormat="xml"}
this.$31u(_3)}
,isc.A.$31u=function isc_DataSourceEditor__startEditing(_1){if(this.mainEditor)this.mainEditor.setValues(_1);else this.mainEditorValues=_1;var _2=_1.fields;if(!isc.isAn.Array(_2))_2=isc.getValues(_1.fields);if(this.fieldEditor){if(this.canEditChildSchema){this.setupIDs(_2,1,null);var _3=isc.Tree.create({modelType:"parent",childrenProperty:"fields",titleProperty:"name",idField:"id",nameProperty:"id",root:{id:0,name:"root"},data:_2});_3.openAll();this.fieldEditor.setData(_3)}else this.fieldEditor.setData(_2)}
this.show()}
,isc.A.setupIDs=function isc_DataSourceEditor_setupIDs(_1,_2,_3){var _4=_2,_5,_6;if(!_4)_4=1;for(var i=0;i<_1.length;i++){var _5=_1.get(i);_5.parentId=_3;_5.id=_4++;if(_5.fields){if(!isc.isAn.Array(_5.fields))_5.fields=isc.getValues(_5.fields);_4=this.setupIDs(_5.fields,_4,_5.id)}}
return _4}
,isc.A.save=function isc_DataSourceEditor_save(){var _1=this.dsClass||"DataSource",_2=isc.addProperties({},this.mainEditor?this.mainEditor.getValues():this.mainEditorValues);if(this.canEditChildSchema){var _3=this.fieldEditor.grid.data,_4=_3.getCleanNodeData(_3.getRoot(),true).fields;_2.fields=this.getExtraCleanNodeData(_4)}else{_2.fields=this.fieldEditor.getData()}
if(_2.serverType=="sql"||_2.serverType=="hibernate"){if(!_2.fields.getProperty("primaryKey").or()){isc.warn("SQL / Hibernate DataSources must have a field marked as the primary key");return}}
this.doneEditing(_2)}
,isc.A.getExtraCleanNodeData=function isc_DataSourceEditor_getExtraCleanNodeData(_1,_2){if(_1==null)return null;var _3=[],_4=false;if(!isc.isAn.Array(_1)){_1=[_1];_4=true}
for(var i=0;i<_1.length;i++){var _6=_1[i],_7={};for(var _8 in _6){if(_8=="id"||_8=="parentId"||_8=="isFolder")continue;_7[_8]=_6[_8];if(_8==this.fieldEditor.grid.data.childrenProperty&&isc.isAn.Array(_7[_8])){_7[_8]=this.getExtraCleanNodeData(_7[_8])}}
_3.add(_7)}
if(_4)return _3[0];return _3}
,isc.A.doneEditing=function isc_DataSourceEditor_doneEditing(_1){var _2=this.dsClass||"DataSource",_3;if(isc.DS.isRegistered(_2)){_3=isc.DS.get(_2)}else{_3=isc.DS.get("DataSource");_1._constructor=_2}
var _4=_3.xmlSerialize(_1);this.logWarn("saving DS with XML: "+_4);isc.DMI.callBuiltin({methodName:"saveSharedXML",arguments:["DS",_1.ID,_4]});var _5=isc.ClassFactory.getClass(_2).create(_1);this.fireCallback(this.saveCallback,"dataSource",[_5]);this.saveCallback=null}
,isc.A.clear=function isc_DataSourceEditor_clear(){if(this.mainEditor)this.mainEditor.clearValues();else this.mainEditorValues=null;this.fieldEditor.setData([])}
,isc.A.initWidget=function isc_DataSourceEditor_initWidget(){this.Super('initWidget',arguments);this.addAutoChildren(["mainStack","instructions","mainEditor","buttonLayout","saveButton"]);if(this.canAddChildSchema){this.canEditChildSchema=true;this.addAutoChild("addChildButton")}
this.addAutoChild("fieldEditor",{formConstructor:isc.TComponentEditor||isc.ComponentEditor,gridConstructor:this.canEditChildSchema?isc.TreeGrid:isc.ListGrid,showMoreButton:this.showMoreButton,newButtonTitle:"New Field",newButtonDefaults:this.newButtonDefaults,newButtonProperties:this.newButtonProperties,moreButtonDefaults:this.moreButtonDefaults,moreButtonProperties:this.moreButtonProperties});this.moreButton=this.fieldEditor.moreButton;this.newButton=this.fieldEditor.newButton;if(this.canAddChildSchema)this.fieldEditor.gridButtons.addMember(this.addChildButton);var _1=this.mainStack;_1.addSections([isc.addProperties(this.instructionsSectionDefaults,this.instructionsSectionProperties,{items:[this.instructions]})]);_1.addSections([isc.addProperties(this.mainSectionDefaults,this.mainSectionProperties,{items:[this.mainEditor]})]);if(this.showMainEditor==false)_1.hideSection(1);_1.addSections([isc.addProperties(this.fieldSectionDefaults,this.fieldSectionProperties,{items:[this.fieldEditor]})]);var _2=this;this.deriveForm=this.createAutoChild("deriveForm",{fields:[{name:"sql",showTitle:false,formItemType:"AutoFitTextAreaItem",width:"*",height:40,colSpan:"*",keyPress:function(_3,_4,_5){if(_5=='Enter'&&isc.EH.ctrlKeyDown()){if(isc.Browser.isSafari)_3.setValue(_3.getElementValue());_2.execSQL();if(isc.Browser.isSafari)return false}}},{type:"button",title:"Execute",startRow:true,click:this.getID()+".execSQL()"}]});_1.addSections({expanded:true,showHeader:false,items:[this.saveButton]})}
,isc.A.execSQL=function isc_DataSourceEditor_execSQL(){var _1=this.deriveForm.getValue("sql");if(_1){_1=_1.trim().replace(/(.*);+/,"$1");var _2=isc.DataSource.get("DataSourceStore");_2.performCustomOperation("dsFromSQL",{dbName:this.mainEditor.getValue("dbName"),sql:_1},this.getID()+".deriveDSLoaded(data)")}}
,isc.A.deriveDSLoaded=function isc_DataSourceEditor_deriveDSLoaded(_1){var _2=_1.ds;this.dsLoaded(_1.ds)}
,isc.A.dsLoaded=function isc_DataSourceEditor_dsLoaded(_1){var _2=isc.DataSource.create(_1);this.currentDS=_2;this.deriveFields(_2);this.previewGrid.setDataSource(_2)}
,isc.A.deriveFields=function isc_DataSourceEditor_deriveFields(_1){var _2=_1.getFieldNames();var _3=[];for(var i=0;i<_2.length;i++){var _5=_2[i]
var _6={};var _7=_1.getField(_5);for(var _8 in _7){if(isc.isA.String(_8)&&_8.startsWith("_"))continue;_6[_8]=_7[_8]}
_3.add(_6)}
var _9=isc.Tree.create({modelType:"parent",childrenProperty:"fields",titleProperty:"name",idField:"id",nameProperty:"id",root:{id:0,name:"root"},data:_3});this.fieldEditor.setData(_9)}
);isc.B._maxIndex=isc.C+16}
isc._moduleEnd=isc._DataBinding_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('DataBinding module init time: ' + (isc._moduleEnd-isc._moduleStart) + 'ms','loadTime');delete isc.definingFramework;}else{if(window.isc && isc.Log && isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'DataBinding'.");}
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

