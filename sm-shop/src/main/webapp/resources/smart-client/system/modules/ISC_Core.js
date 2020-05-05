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

var isc = window.isc ? window.isc : {};if(window.isc&&!window.isc.module_Core){isc.module_Core=1;isc._moduleStart=isc._Core_start=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc._moduleEnd&&(!isc.Log||(isc.Log && isc.Log.logIsDebugEnabled('loadTime')))){isc._pTM={ message:'Core load/parse time: ' + (isc._moduleStart-isc._moduleEnd) + 'ms', category:'loadTime'};
if(isc.Log && isc.Log.logDebug)isc.Log.logDebug(isc._pTM.message,'loadTime')
else if(isc._preLog)isc._preLog[isc._preLog.length]=isc._pTM
else isc._preLog=[isc._pTM]}isc.definingFramework=true;var isc=window.isc?window.isc:{};isc.$d=new Date().getTime();isc.version="v8.2p_2012-06-03/LGPL Development Only";isc.versionNumber="v8.2p_2012-06-03";isc.buildDate="2012-06-03";isc.expirationDate="";isc.licenseType="LGPL";isc.licenseCompany="Isomorphic Software";isc.licenseSerialNumber="ISC_LGPL_NIGHTLY";isc.licensingPage="http://smartclient.com/product/";isc.$41r={SCServer:{present:"false",name:"SmartClient Server",serverOnly:true,isPro:true},Drawing:{present:"true",name:"Drawing Module"},PluginBridges:{present:"true",name:"PluginBridges Module"},RichTextEditor:{present:"true",name:"RichTextEditor Module"},Calendar:{present:"true",name:"Calendar Module"},Analytics:{present:"false",name:"Analytics Module"},Charts:{present:"false",name:"Charts Module"},Tools:{present:"${includeTools}",name:"Portal and Tools Module"},NetworkPerformance:{present:"false",name:"Network Performance Module"},FileLoader:{present:"false",name:"Network Performance Module"},RealtimeMessaging:{present:"false",name:"RealtimeMessaging Module"},serverCriteria:{present:"false",name:"Server Advanced Filtering",serverOnly:true,isFeature:true},customSQL:{present:"false",name:"SQL Templating",serverOnly:true,isFeature:true},chaining:{present:"false",name:"Transaction Chaining",serverOnly:true,isFeature:true},batchDSGenerator:{present:"false",name:"Batch DS-Generator",serverOnly:true,isFeature:true},batchUploader:{present:"false",name:"Batch Uploader",serverOnly:true,isFeature:true},transactions:{present:"false",name:"Automatic Transaction Management",serverOnly:true,isFeature:true}};isc.canonicalizeModules=function(_1){if(!_1)return null;if(isc.isA.String(_1)){if(_1.indexOf(",")!=-1){_1=_1.split(",");var _2=/^\s+/,_3=/\s+$/;for(var i=0;i<_1.length;i++){_1[i]=_1[i].replace(_2,"").replace(_3,"")}}else _1=[_1]}
return _1};isc.hasOptionalModules=function(_1){if(!_1)return true;_1=isc.canonicalizeModules(_1);for(var i=0;i<_1.length;i++)if(!isc.hasOptionalModule(_1[i]))return false;return true};isc.getMissingModules=function(_1){var _2=[];_1=isc.canonicalizeModules(_1);for(var i=0;i<_1.length;i++){var _4=_1[i];if(!isc.hasOptionalModule(_4))_2.add(isc.$41r[_4])}
return _2};isc.hasOptionalModule=function(_1){var v=isc.$41r[_1];if(!v){if(isc.Log)isc.Log.logWarn("isc.hasOptionalModule - unknown module: "+_1);return false}
return v.present=="true"||v.present.charAt(0)=="$"};isc.getOptionalModule=function(_1){return isc.$41r[_1]};isc.$a=window.isc_useSimpleNames;if(isc.$a==null)isc.$a=true;if(window.OpenAjax){isc.$b=isc.versionNumber.replace(/[a-zA-Z_]+/,".0");OpenAjax.registerLibrary("SmartClient","http://smartclient.com/SmartClient",isc.$b,{namespacedMode:!isc.$a,iscVersion:isc.version,buildDate:isc.buildDate,licenseType:isc.licenseType,licenseCompany:isc.licenseCompany,licenseSerialNumber:isc.licenseSerialNumber});OpenAjax.registerGlobals("SmartClient",["isc"])}
isc.$e=window.isc_useLongDOMIDs;isc.$f="isc.";isc.addGlobal=function(_1,_2){if(_1.indexOf(isc.$f)==0)_1=_1.substring(4);isc[_1]=_2;if(isc.$a)window[_1]=_2}
isc.onLine=true;isc.isOffline=function(){return!isc.onLine};isc.goOffline=function(){isc.onLine=false};isc.goOnline=function(){isc.onLine=true};if(window.addEventListener){window.addEventListener("online",isc.goOnline,false);window.addEventListener("offline",isc.goOffline,false)}
isc.addGlobal("Browser",{isSupported:false});isc.Browser.isOpera=(navigator.appName=="Opera"||navigator.userAgent.indexOf("Opera")!=-1);isc.Browser.isNS=(navigator.appName=="Netscape"&&!isc.Browser.isOpera);isc.Browser.isIE=(navigator.appName=="Microsoft Internet Explorer"&&!isc.Browser.isOpera);isc.Browser.isMSN=(isc.Browser.isIE&&navigator.userAgent.indexOf("MSN")!=-1);isc.Browser.minorVersion=parseFloat(isc.Browser.isIE?navigator.appVersion.substring(navigator.appVersion.indexOf("MSIE")+5):navigator.appVersion);isc.Browser.version=parseInt(isc.Browser.minorVersion);isc.Browser.isIE6=isc.Browser.isIE&&isc.Browser.version<=6;isc.Browser.isMoz=(navigator.userAgent.indexOf("Gecko")!=-1)&&(navigator.userAgent.indexOf("Safari")==-1)&&(navigator.userAgent.indexOf("AppleWebKit")==-1);isc.Browser.isCamino=(isc.Browser.isMoz&&navigator.userAgent.indexOf("Camino/")!=-1);if(isc.Browser.isCamino){isc.Browser.caminoVersion=navigator.userAgent.substring(navigator.userAgent.indexOf("Camino/")+7)}
isc.Browser.isFirefox=(isc.Browser.isMoz&&navigator.userAgent.indexOf("Firefox/")!=-1);if(isc.Browser.isFirefox){isc.Browser.firefoxVersion=navigator.userAgent.substring(navigator.userAgent.indexOf("Firefox/")+8)}
if(isc.Browser.isMoz){isc.Browser.$g=navigator.userAgent.indexOf("Gecko/")+6;isc.Browser.geckoVersion=parseInt(navigator.userAgent.substring(isc.Browser.$g,isc.Browser.$g+8));if(isc.Browser.isFirefox){if(isc.Browser.firefoxVersion.match(/^1\.0/))isc.Browser.geckoVersion=20050915;else if(isc.Browser.firefoxVersion.match(/^2\.0/))isc.Browser.geckoVersion=20071108}}
isc.Browser.isStrict=document.compatMode=="CSS1Compat";if(isc.Browser.isStrict&&isc.Browser.isMoz){isc.Browser.$51p=document.doctype.publicId;isc.Browser.$51q=document.doctype.systemId}
isc.Browser.isTransitional=/.*(Transitional|Frameset)/.test((document.all&&document.all[0]&&document.all[0].nodeValue)||(document.doctype&&document.doctype.publicId));isc.Browser.isIE7=isc.Browser.isIE&&isc.Browser.version==7;isc.Browser.isIE8=isc.Browser.isIE&&isc.Browser.version>=8&&document.documentMode==8
isc.Browser.isIE8Strict=isc.Browser.isIE&&isc.Browser.isStrict&&document.documentMode>=8;isc.Browser.isIE9=isc.Browser.isIE&&isc.Browser.version>=9&&document.documentMode>=9;isc.Browser.isAIR=(navigator.userAgent.indexOf("AdobeAIR")!=-1);isc.Browser.AIRVersion=(isc.Browser.isAIR?navigator.userAgent.substring(navigator.userAgent.indexOf("AdobeAir/")+9):null);isc.Browser.isWebKit=navigator.userAgent.indexOf("WebKit")!=-1;isc.Browser.isSafari=isc.Browser.isAIR||navigator.userAgent.indexOf("Safari")!=-1||navigator.userAgent.indexOf("AppleWebKit")!=-1;isc.Browser.isChrome=isc.Browser.isSafari&&(navigator.userAgent.indexOf("Chrome/")!=-1);if(isc.Browser.isSafari){if(isc.Browser.isAIR){isc.Browser.safariVersion=530}else{if(navigator.userAgent.indexOf("Safari/")!=-1){isc.Browser.rawSafariVersion=navigator.userAgent.substring(navigator.userAgent.indexOf("Safari/")+7)}else if(navigator.userAgent.indexOf("AppleWebKit/")!=-1){isc.Browser.rawSafariVersion=navigator.userAgent.substring(navigator.userAgent.indexOf("AppleWebKit/")+12)}else{isc.Browser.rawSafariVersion="530"}
isc.Browser.safariVersion=(function(){var _1=isc.Browser.rawSafariVersion,_2=_1.indexOf(".");if(_2==-1)return parseInt(_1);var _3=_1.substring(0,_2+1),_4;while(_2!=-1){_2+=1;_4=_1.indexOf(".",_2);_3+=_1.substring(_2,(_4==-1?_1.length:_4));_2=_4}
return parseFloat(_3)})()}}
isc.Browser.isWin=navigator.platform.toLowerCase().indexOf("win")>-1;isc.Browser.isWin2k=navigator.userAgent.match(/NT 5.01?/)!=null;isc.Browser.isMac=navigator.platform.toLowerCase().indexOf("mac")>-1;isc.Browser.isUnix=(!isc.Browser.isMac&&!isc.Browser.isWin);isc.Browser.isAndroid=navigator.userAgent.indexOf("Android")>-1;isc.Browser.isRIM=isc.Browser.isBlackBerry=navigator.userAgent.indexOf("BlackBerry")>-1||navigator.userAgent.indexOf("PlayBook")>-1;isc.Browser.isMobileWebkit=(isc.Browser.isSafari&&navigator.userAgent.indexOf(" Mobile/")>-1||isc.Browser.isAndroid||isc.Browser.isBlackBerry);isc.Browser.isMobile=(isc.Browser.isMobileWebkit);isc.Browser.isTouch=(isc.Browser.isMobileWebkit);isc.Browser.isIPhone=(isc.Browser.isMobileWebkit&&navigator.userAgent.indexOf("AppleWebKit"));isc.Browser.isHandset=(isc.Browser.isMobileWebkit&&navigator.userAgent.indexOf("iPad")==-1);isc.Browser.isIPad=(isc.Browser.isIPhone&&navigator.userAgent.indexOf("iPad"));isc.Browser.isTablet=(isc.Browser.isIPad)||(isc.Browser.isRIM&&navigator.userAgent.indexOf("Tablet")>-1)
isc.Browser.isBorderBox=(isc.Browser.isIE&&!isc.Browser.isStrict);isc.Browser.lineFeed=(isc.Browser.isWin?"\r\n":"\r");isc.Browser.$h=false;isc.Browser.isDOM=(isc.Browser.isMoz||isc.Browser.isOpera||isc.Browser.isSafari||(isc.Browser.isIE&&isc.Browser.version>=5));isc.Browser.isSupported=((isc.Browser.isIE&&isc.Browser.minorVersion>=5.5&&isc.Browser.isWin)||isc.Browser.isMoz||isc.Browser.isOpera||isc.Browser.isSafari||isc.Browser.isAIR);isc.Browser.allowsXSXHR=((isc.Browser.isFirefox&&isc.Browser.firefoxVersion>="3.5")||(isc.Browser.isChrome)||(isc.Browser.isSafari&&isc.Browser.safariVersion>=531));isc.noOp=function(){};isc.emptyObject={};isc.$ac=[];isc.emptyString=isc.$ad="";isc.dot=".";isc.semi=";";isc.colon=":";isc.slash="/";isc.star="*";isc.auto="auto";isc.px="px";isc.nbsp="&nbsp;";isc.xnbsp="&amp;nbsp;";isc.$ae="false";isc.$af="FALSE";isc.$ag="_";isc.$75g="$";isc.$ah="_$observed_";isc.$ai="_$SuperProto_";isc.gwtRef="__ref";isc.logWarn=function(_1,_2){isc.Log.logWarn(_1,_2)}
isc.echo=function(_1){return isc.Log.echo(_1)}
isc.echoAll=function(_1){return isc.Log.echoAll(_1)}
isc.echoLeaf=function(_1){return isc.Log.echoLeaf(_1)}
isc.echoFull=function(_1){return isc.Log.echoFull(_1)}
isc.logEcho=function(_1,_2){if(_2)_2+=": ";isc.Log.logWarn((_2||isc.$ad)+isc.echo(_1))}
isc.logEchoAll=function(_1,_2){if(_2)_2+=": ";isc.Log.logWarn((_2||isc.$ad)+isc.echoAll(_1))}
isc.$aq=function(_1,_2){var _3=_2||_1;return _2==null?new Function(_3):new Function(_1,_3)};isc.doEval=function(_1){if(isc.Browser.isMoz)return isc.$as(_1);if(!isc.$at)isc.$at=[];isc.$at[isc.$at.length]=_1;return null}
isc.finalEval=function(){if(isc.$at){if(isc.Browser.isMoz){for(var i=0;i<isc.$at.length;i++){isc.eval(isc.$at[i])}}
var _2=isc.$at.join("");if(isc.Browser.isSafari)_2=isc.$as(_2);if(isc.Browser.isIE)window.execScript(_2,"javascript");else isc.eval(_2)}
isc.$at=null}
isc.$au="//$0";isc.$av="//$1";isc.$aw=0;isc.$ax=true;isc.$as=function(_1){isc.$ar=true;var _2=isc.timeStamp?isc.timeStamp():new Date().getTime();var _3=isc.$aj,_4=isc.$ay;if(isc.$ax)_3=isc.$an+_3;var _5=_1.split(isc.$az),_6=[];var _5=_1.split(isc.$au);_1=_5.join(_3);_5=_1.split(isc.$av);_1=_5.join(_4);if(isc.$ax){_5=_1.split("//$2");_1=_5.join(isc.$ap)}
var _7=isc.timeStamp?isc.timeStamp():new Date().getTime();isc.$aw+=(_7-_2);return _1}
isc.$ay="}catch($al){isc.eval(isc.$a0(";isc.$a0=function(_1){var _2="var _ = {";if(_1!=""){var _3=_1.split(",");for(var i=0;i<_3.length;i++){var _5=_3[i];_2+=_5+":"+_5;if(i<_3.length-1)_2+=","}}
_2+="};";_2+="if(isc.Log)isc.Log.$am($al,arguments,this,_);throw $al;";return _2}
isc.fillList=function(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13,_14,_15,_16,_17,_18,_19,_20,_21,_22,_23,_24,_25,_26,_27){if(_1==null)_1=[];else _1.length=0;var _28;if(_25===_28&&_26===_28&&_27===_28){_1[0]=_2;_1[1]=_3;_1[2]=_4;_1[3]=_5;_1[4]=_6;_1[5]=_7;_1[6]=_8;_1[7]=_9;_1[8]=_10;_1[9]=_11;_1[10]=_12;_1[11]=_13;_1[12]=_14;_1[13]=_15;_1[14]=_16;_1[15]=_17;_1[16]=_18;_1[17]=_19;_1[18]=_20;_1[19]=_21;_1[20]=_22;_1[21]=_23;_1[22]=_24}else{for(var i=1;i<arguments.length;i++){_1[i-1]=arguments[i]}}
return _1}
isc.$a1=[];isc.addGlobal("addProperties",function(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13,_14,_15,_16,_17,_18,_19,_20,_21,_22,_23,_24,_25,_26,_27){var _28,_29=isc.$a1;if(_25===_28&&_26===_28&&_27===_28){isc.fillList(_29,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13,_14,_15,_16,_17,_18,_19,_20,_21,_22,_23,_24,_25,_26,_27)}else{_29.length=0;for(var i=1;i<arguments.length;i++){_29[i-1]=arguments[i]}}
var _31=isc.addPropertyList(_1,_29);_29.length=0;return _31});isc.$a2={};isc.$a3={};isc.$a4=function(_1){var _2=_1.Class,_3;if(isc.isA.ClassObject(_1)){_3=isc.$a3[_2]=isc.$a3[_2]||[]}else if(isc.isAn.InstancePrototype(_1)){_3=isc.$a2[_2]=isc.$a2[_2]||[]}
return _3}
isc.addPropertyList=function(_1,_2){if(_1==null){if(isc.Log)isc.Log.logWarn("Attempt to add properties to a null object. "+"Creating a new object for the list of properties.");_1={}}
var _3,_4=(isc.isA!=null),_5=(isc.isAn&&isc.isAn.Instance(_1)?_1.getClass()._stringMethodRegistry:_1._stringMethodRegistry);if(_5==null)_5=isc.emptyObject;var _6=_1.$a5?isc.$a4(_1):null;var _7;for(var i=0,l=_2.length;i<l;i++){var _10=_2[i];if(_10==null)continue;for(var _11 in _10){var _12=_10[_11];var _13=_4&&isc.isA.Function(_12);if(_5[_11]!==_7||_13)
{if(_3==null)_3={};_3[_11]=_12}else{if(_6!=null)_6[_6.length]=_11;var _14=_1[_11];if(!_13&&_14!=null&&isc.isA.Function(_1[_11]))
{if(isc.Log!=null){isc.Log.logWarn("method "+_11+" on "+_1+" overridden with non-function: '"+_12+"'")}}
_1[_11]=_12}}}
if(_3!=null)isc.addMethods(_1,_3);return _1}
isc.$a6="string";isc.$a7="function";isc.$a8="constructor";isc.$bl="object";isc.addGlobal("addMethods",function(_1,_2){if(!_1||!_2)return _1;var _3=_1.$a5?isc.$a4(_1):null;if(!isc.$a9)isc.$a9={};for(var _4 in _2){if(_3!=null)_3[_3.length]=_4;var _5=_2[_4];if(isc.isAn.Instance!=null&&_5!=null&&(typeof _5==isc.$a6||typeof _5==isc.$bl))
{var _6=(isc.isAn.Instance(_1)?_1.getClass()._stringMethodRegistry:_1._stringMethodRegistry);var _7;if(_6&&!(_6[_4]===_7)&&_4!=isc.$a8)
{_5=isc.Func.expressionToFunction(_6[_4],_2[_4])}}
var _8=_1.$ba,_9=(_8!=null&&_8[_4]!=null?isc.$ah+_4:_4);if(_5!==_1[_9]){if(_5!=null){this.$bb(_5,_4,_1)}
_1[_9]=_5;if(_5!=null){if(isc.$a9[_4]){var _10=(_1.$ba!=null&&_1.$ba[isc.$a9[_4]]!=null?isc.$ah+isc.$a9[_4]:isc.$a9[_4]);_1[_10]=_5}}}}
return _1});isc._allFuncs=[]
isc._allFuncs._maxIndex=0;isc._funcClasses=new Array(5000);isc.$bb=function(_1,_2,_3){if(typeof _1!=isc.$a7)return;if(_3.Class==null)return _1.$bc=_2;if(isc.isA!=null&&isc.isAn.InstancePrototype!=null&&(isc.isAn.InstancePrototype(_3)||isc.isA.ClassObject(_3)))
{var _4=isc._allFuncs;_4[_4._maxIndex]=_1;isc._funcClasses[_4._maxIndex]=_3.Class;_4._maxIndex++;return}
var _5=(_3==isc.isA?"isA":_3.Class);_1.$bd=_5;if(isc[_3.Class]==null)_1.$bc=_2;if(isc.isA!=null&&isc.isAn.Instance!=null&&isc.isAn.Instance(_3)&&!isc.isAn.InstancePrototype(_3))
{_1.$bc=_2;_1.$be=true;if(_3[_2]!=null)_1.$bf=true}}
isc.addGlobal("getKeys",function(_1){var _2=[];if(_1!=null){for(var _3 in _1){_2[_2.length]=_3}}
return _2});isc.addGlobal("firstKey",function(_1){for(var _2 in _1)return _2});isc.addGlobal("getValues",function(_1){var _2=[];if(_1!=null){for(var _3 in _1){_2[_2.length]=_1[_3]}}
return _2});isc.addGlobal("sortObject",function(_1,_2){if(!isc.isA.Object(_1))return _1;if(isc.isAn.Array(_1)){if(_2!=null)return _1.sort(_2);return _1.sort()}
var _3=isc.getKeys(_1);_3=(_2==null?_3.sort():_3.sort(_2));var _4={};for(var i=0;i<_3.length;i++){_4[_3[i]]=_1[_3[i]]}
return _4});isc.addGlobal("sortObjectByProperties",function(_1,_2){if(!isc.isA.Object(_1))return _1;if(isc.isAn.Array(_1)){if(_2!=null)return _1.sort(_2);return _1.sort()}
var _3=isc.getValues(_1);_3=(_2==null?_3.sort():_3.sort(_2));var _4={};for(var i=0;i<_3.length;i++){var _6=_3[i];for(var _7 in _1){if(_1[_7]===_6){_4[_7]=_1[_7];continue}}}
return _4});isc.addGlobal("addDefaults",function(_1,_2){if(_1==null)return;var _3;for(var _4 in _2){if(_1[_4]===_3)_1[_4]=_2[_4]}
return _1});isc.addGlobal("propertyDefined",function(_1,_2){if(_1==null)return false;var _3;if(_1[_2]!==_3)return true;var _4=isc.getKeys(_1);return(_4.contains(_2))});isc.$829="__ref";isc.addGlobal("objectsAreEqual",function(_1,_2){if(_1===_2)return true;else if(isc.isAn.Object(_1)&&isc.isAn.Object(_2)){if(isc.isA.Date(_1)){return isc.isA.Date(_2)&&(Date.compareDates(_1,_2)==0)}else if(isc.isAn.Array(_1)){if(isc.isAn.Array(_2)&&_1.length==_2.length){for(var i=0;i<_1.length;i++){if(!isc.objectsAreEqual(_1[i],_2[i]))return false}
return true}
return false}else{if(isc.isAn.Array(_2))return false;var _4=0;for(var _5 in _1){if(_5==isc.$829)continue;if(!isc.objectsAreEqual(_1[_5],_2[_5]))return false;_4++}
var _6=0;for(var _7 in _2){if(_5==isc.$829)continue;_6++;if(_6>_4)return false}
if(_6!=_4)return false;return true}}else{return false}});isc.addGlobal("combineObjects",function(_1,_2){if(_1==null||!isc.isAn.Object(_1))return _2;if(_2==null||!isc.isAn.Object(_2))return _1;for(var _3 in _2){var _4=_1[_3],_5=_2[_3];if(isc.isAn.Object(_4)&&!isc.isAn.Array(_4)&&!isc.isA.Date(_4)&&isc.isAn.Object(_5)&&!isc.isAn.Array(_5)&&!isc.isA.Date(_5))
{isc.combineObjects(_4,_5)}else{_1[_3]=_5}}});isc.applyMask=function(_1,_2){var _3={};if(_1==null)return _3;if(_2==null){return isc.addProperties(_3,_1)}
var _4=false;if(!isc.isAn.Array(_1)){_4=true;_1=[_1]}
if(!isc.isAn.Array(_2))_2=isc.getKeys(_2);var _3=[],_5,_6,_7,_8;for(var i=0;i<_1.length;i++){_5=_1[i];_6=_3[i]={};for(var j=0;j<_2.length;j++){_7=_2[j];if(_5[_7]===_8)continue;_6[_7]=_5[_7]}}
return(_4?_3[0]:_3)}
isc.getProperties=function(_1,_2){if(_1==null)return null;var _3={};if(_2==null)return _3;for(var i=0;i<_2.length;i++){var _5=_2[i];_3[_5]=_1[_5]}
return _3}
isc.$bg={};isc.$bh=Math.floor;isc.$bi="-";for(isc.$bj=0;isc.$bj<10;isc.$bj++)
isc.$bg[isc.$bj]=isc.$bj.toString();isc.$bk=function(_1,_2,_3,_4){var _5=_3+_4-1,_6=_2,_7=false,_8;if(_2<0){_8=true;_2=-_2;_1[_3]=this.$bi;_3+=1;_4-=1}
while(_2>9){var _9=this.$bh(_2/ 10),_10=_2-(_9*10);_1[_5]=this.$bg[_10];_2=_9;if(_5==(_3+1)&&_2>9){isc.Log.logWarn("fillNumber: number too large: "+_6+isc.Log.getStackTrace());_7=true;break}
_5-=1}
if(_7){_5=_3+_4-1
_1[_5--]=(!_8?_6:-_6)}else{_1[_5--]=this.$bg[_2]}
for(var i=_5;i>=_3;i--){_1[i]=null}}
isc.booleanValue=function(_1,_2){if(_1==null)return _2;if(isc.isA.String(_1))return _1!=isc.$ae&&_1!=isc.$af;return _1?true:false}
isc.iscToLocaleString=function(_1){if(_1!=null){return _1.iscToLocaleString?_1.iscToLocaleString():(_1.toLocaleString?_1.toLocaleString():(_1.toString?_1.toString():isc.emptyString+_1))}
return isc.emptyString+_1}
isc.addGlobal("isA",{});isc.addGlobal("isAn",isc.isA);isc.addGlobal("is",isc.isA);isc.isA.Class="isA";isc.isA.isc=isc.isA;Function.$k=1;Array.$k=2;Date.$k=3;String.$k=4;Number.$k=5;Boolean.$k=6;RegExp.$k=7;Object.$k=8;Function.prototype.$k=1;isc.A=isc.isA;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.useTypeOf=isc.Browser.isMoz||isc.Browser.isSafari;isc.A.$bl="object";isc.A.$73x="String";isc.A.$a7="function";isc.A.$bm="text/xml";isc.A.$58k={SelectItem:true,Time:true};isc.B.push(isc.A.emptyString=function isc_isA_emptyString(_1){return isc.isA.String(_1)&&_1==isc.emptyString}
,isc.A.nonemptyString=function isc_isA_nonemptyString(_1){return isc.isA.String(_1)&&_1!=isc.emptyString}
,isc.A.Object=function isc_isA_Object(_1){if(_1==null)return false;if(isc.Browser.isIE&&typeof _1==this.$a7)return false;if(this.useTypeOf){var _2=typeof _1;return(_2=="object"||_2=="array"||_2=="date"||(isc.Browser.isMoz&&_2=="function"&&isc.isA.RegularExpression(_1)))}
if(_1.constructor&&_1.constructor.$k!=null){var _3=_1.constructor.$k;if(_3==1){}else{return(_3==8||_3==7||_3==3||_3==2)}}
if(_1.Class!=null&&_1.Class==this.$73x)return false;if(typeof _1==this.$bl){if(isc.Browser.isIE&&isc.isA.Function(_1))return false;else return true}else return false}
,isc.A.emptyObject=function isc_isA_emptyObject(_1){if(!isc.isAn.Object(_1))return false;for(var i in _1){return false}
return true}
,isc.A.emptyArray=function isc_isA_emptyArray(_1){return isc.isAn.Array(_1)&&_1.length==0}
,isc.A.String=function isc_isA_String(_1){if(_1==null)return false;if(this.useTypeOf){return typeof _1=="string"||(_1.Class!=null&&_1.Class==this.$73x)}
if(_1.constructor&&_1.constructor.$k!=null){return _1.constructor.$k==4}
if(_1.Class!=null&&_1.Class==this.$73x)return true;return typeof _1=="string"}
,isc.A.Array=function isc_isA_Array(_1){if(_1==null)return false;if(this.useTypeOf&&typeof _1=="array")return true;if(typeof _1==this.$a7)return false;if(_1.constructor&&_1.constructor.$k!=null){return _1.constructor.$k==2}
if(isc.Browser.isSafari){var _2=""+_1.splice;return(_2=="function splice() {\n    [native code]\n}"||_2=="(Internal function)")}
return""+_1.constructor==""+Array}
,isc.A.Function=function isc_isA_Function(_1){if(_1==null)return false;if(isc.Browser.isIE&&typeof _1==this.$a7)return true;var _2=_1.constructor;if(_2&&_2.$k!=null){if(_2.$k!=1)return false;if(_2===Function)return true}
return isc.Browser.isIE?(isc.emptyString+_1.constructor==Function.toString()):(typeof _1==this.$a7)}
,isc.A.Number=function isc_isA_Number(_1){if(_1==null)return false;if(this.useTypeOf&&typeof _1=="number"){return!isNaN(_1)&&_1!=Number.POSITIVE_INFINITY&&_1!=Number.NEGATIVE_INFINITY}
if(_1.constructor&&_1.constructor.$k!=null){if(_1.constructor.$k!=5)return false}else{if(typeof _1!="number")return false}
return!isNaN(_1)&&_1!=Number.POSITIVE_INFINITY&&_1!=Number.NEGATIVE_INFINITY}
,isc.A.SpecialNumber=function isc_isA_SpecialNumber(_1){if(_1==null)return false;if(_1.constructor&&_1.constructor.$k!=null){if(_1.constructor.$k!=5)return false}else{if(typeof _1!="number")return false}
return(isNaN(_1)||_1==Number.POSITIVE_INFINITY||_1==Number.NEGATIVE_INFINITY)}
,isc.A.Boolean=function isc_isA_Boolean(_1){if(_1==null)return false;if(_1.constructor&&_1.constructor.$k!=null){return _1.constructor.$k==6}
return typeof _1=="boolean"}
,isc.A.Date=function isc_isA_Date(_1){if(_1==null)return false;if(_1.constructor&&_1.constructor.$k!=null){return _1.constructor.$k==3}
return(""+_1.constructor)==(""+Date)&&_1.getDate&&isc.isA.Number(_1.getDate())}
,isc.A.RegularExpression=function isc_isA_RegularExpression(_1){if(_1==null)return false;if(_1.constructor&&_1.constructor.$k!=null){return _1.constructor.$k==7}
return(""+_1.constructor)==(""+RegExp)}
,isc.A.XMLNode=function isc_isA_XMLNode(_1){if(_1==null)return false;if(isc.Browser.isIE){return _1.specified!=null&&_1.parsed!=null&&_1.nodeType!=null&&_1.hasChildNodes!=null}
var _2=_1.ownerDocument;if(_2==null)return false;return _2.contentType==this.$bm}
,isc.A.AlphaChar=function isc_isA_AlphaChar(_1){var _2=_1.charCodeAt(0)
return((_2>=65&&_2<=90)||(_2>=97&&_2<=122))}
,isc.A.NumChar=function isc_isA_NumChar(_1){var _2=_1.charCodeAt(0)
return(_2>=48&&_2<=57)}
,isc.A.AlphaNumericChar=function isc_isA_AlphaNumericChar(_1){return(isc.isA.AlphaChar(_1)||isc.isA.NumChar(_1))}
,isc.A.WhitespaceChar=function isc_isA_WhitespaceChar(_1){var _2=_1.charCodeAt(0)
return(_2>=0&&_2<=32)}
,isc.A.color=function isc_isA_color(_1){if(!isc.isA.String(_1))return false;if(!this.$bn){this.$bn=new RegExp("^(#([\\dA-F]{2}){3}|"+"rgb\\((\\s*[\\d]{1,3}\\s*,\\s*){2}\\s*[\\d]{1,3}\\s*\\)|"+"[a-z]+)$","i")}
return this.$bn.test(_1)}
,isc.A.ResultSet=function isc_isA_ResultSet(_1){return false}
,isc.A.ResultTree=function isc_isA_ResultTree(_1){return false}
,isc.A.SelectItem=function isc_isA_SelectItem(_1){if(!_1||!isc.isA.FormItem(_1))return false;var _2=_1.getClass();return(_2==isc.SelectItem||_2==isc.NativeSelectItem)}
,isc.A.SelectOtherItem=function isc_isA_SelectOtherItem(_1){if(!_1||!isc.isA.FormItem(_1))return false;var _2=_1.getClass();return((_2==isc.SelectItem||_2==isc.NativeSelectItem)&&_1.isSelectOther)}
,isc.A.Time=function isc_isA_Time(_1){return isc.isA.Date(_1)}
);isc.B._maxIndex=isc.C+24;isc.addGlobal("ClassFactory",{});isc.ClassFactory.Class="ClassFactory";isc.A=isc.isA;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.Instance=function isc_isA_Instance(_1){return(_1!=null&&_1.$76y!=null)}
,isc.A.ClassObject=function isc_isA_ClassObject(_1){return(_1!=null&&_1.$bp==true)}
,isc.A.Interface=function isc_isA_Interface(_1){return(_1!=null&&_1.$a5==true)}
,isc.A.InstancePrototype=function isc_isA_InstancePrototype(_1){return(isc.isAn.Instance(_1)&&_1.$76y==_1)}
);isc.B._maxIndex=isc.C+4;isc.A=isc.ClassFactory;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$f="isc.";isc.A.$bq="Window";isc.A.$br="Selection";isc.A.$bs={};isc.A.$bt="object";isc.A.$bu=["if(object==null||object.isA==null||object.isA==isc.isA)return false;return object.isA(isc.",null,")"];isc.A.$60l={toolbar:true,parent:true,window:true,top:true,opener:true,event:true};isc.A._$isc_OID_="isc_OID_";isc.A._$isc_="isc_";isc.A.$bv="_";isc.A.$bw=[];isc.A.$75m={};isc.A.reuseGlobalIDs=true;isc.A.globalIDClassPoolSize=1000;isc.A.$63v={};isc.A.$bx=0;isc.A._$isc_="isc_";isc.A.$by=[null,"_",null];isc.A.reuseDOMIDs=false;isc.A.DOMIDPoolSize=10000;isc.A.$63w=[];isc.A.$bz=["0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];isc.A.$b0=[];isc.A.$b1="a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p";isc.B.push(isc.A.defineClass=function isc_ClassFactory_defineClass(_1,_2,_3,_4){return this.$b2(_1,_2,_3,null,_4)}
,isc.A.overwriteClass=function isc_ClassFactory_overwriteClass(_1,_2,_3,_4){return this.$b2(_1,_2,_3,null,_4,true)}
,isc.A.defineInterface=function isc_ClassFactory_defineInterface(_1,_2){return this.$b2(_1,_2,null,true)}
,isc.A.defineRootClass=function isc_ClassFactory_defineRootClass(_1){return this.$b3(_1,null)}
,isc.A.$b2=function isc_ClassFactory__defineNonRootClass(_1,_2,_3,_4,_5,_6){_2=(_2||isc.ClassFactory.defaultSuperClass);if(!_2){isc.Log.logWarn("isc.ClassFactory.defineClass("+_1+") called with null"+" superClass and no ClassFactory.defaultRootClass is defined.");return null}
return this.$b3(_1,_2,_3,_4,_5,_6)}
,isc.A.$b3=function isc_ClassFactory__defineClass(_1,_2,_3,_4,_5,_6){var _7=(isc.Browser.isMoz&&(_1==this.$bq||_1==this.$br))||(isc.Browser.isChrome&&_1=="DataView");var _8,_9,_10=(isc.$a&&!_5);_8=isc[_1];if(_8!=null)_9=true
else if(_10&&!_7){_8=window[_1]}
if(_8!=null&&_1!="IButton"&&_6!=true)
{var _11="New Class ID: '"+_1+"' collides with ID of existing "+(isc.isA&&isc.isA.Function(isc.isA.Class)&&isc.isA.Class(_8)?"Class object '":"object with value '")+_8+"'.  Existing object will be replaced.";if(!_9)_11+="\nThis conflict would be avoided by disabling "+"ISC Simple Names mode.  See documentation for "+"further information."
if(window.isc.Log)isc.Log.logWarn(_11)}
_2=this.getClass(_2);var _12=(_2?new _2.$b4.$b5():{});var _13=this.$b6(_2);_12.$b5=this.$b7(_12);_13.Class=_1;_13.$bp=true;if(isc.definingFramework==true)_13.isFrameworkClass=true;else _13.isFrameworkClass=false;if(!_13.isFrameworkClass){var _14=_2;while(_14&&!_14.isFrameworkClass){_14=_14.getSuperClass()}
if(_14)_13.$75y=_14.Class}
if(!_13.$75y)_13.$75y=_13.Class;_13.$a5=_12.$a5=!!_4;_13.$b8=_2;_13.$b4=_12;_12.Class=_1;_12.$b9=_13;_12.$76y=_12;_12.isFrameworkClass=_13.isFrameworkClass;_12.$75y=_13.$75y;isc[_1]=_13;if(_10)window[_1]=_13;this.classList[this.classList.length]=_1
if(!(isc.isA.$58k[_1]&&isc.isA[_1])){isc.isA[_1]=this.makeIsAFunc(_1)}
if(_3!=null){if(!isc.isAn.Array(_3))_3=[_3];for(var i=0;i<_3.length;i++){this.mixInInterface(_1,_3[i])}}
return _13}
,isc.A.makeIsAFunc=function isc_ClassFactory_makeIsAFunc(_1){if(this.isFirefox2==null){this.isFirefox2=(isc.Browser.isFirefox&&isc.Browser.geckoVersion>=20061010)}
if(this.isFirefox2){return function(_3){if(_3==null||_3.isA==null||_3.isA==isc.isA)return false;return _3.isA(_1)}}else{var _2=this.$bu;_2[1]=_1;return new Function(this.$bt,_2.join(isc.$ad))}}
,isc.A.$b6=function isc_ClassFactory__makeSubClass(_1){if(!_1)return{};var _2=_1.$b8,_3=_1.$ca;if(!
(_3&&(_2==null||_3!==_2.$ca)))
{_3=_1.$ca=this.$b7(_1)}
return new _3()}
,isc.A.getClass=function isc_ClassFactory_getClass(_1){if(isc.isA.String(_1)){var _2=isc[_1];if(_2&&isc.isA.ClassObject(_2)){return _2}}
if(isc.isA.ClassObject(_1))return _1;if(isc.isAn.Instance(_1))return _1.$b9;return null}
,isc.A.newInstance=function isc_ClassFactory_newInstance(_1,_2,_3,_4,_5,_6){var _7=this.getClass(_1);if(_7==null&&isc.isAn.Object(_1)){var _8;for(var i=0;i<arguments.length;i++){var _10=arguments[i];if(_10!=null&&_10._constructor!=null)
{_8=_10._constructor}}
_6=_5;_5=_4;_4=_3;_3=_2;_2=_1;_1=_8;if(isc.isA.String(_2.constructor)){if(_1==null)_1=_2.constructor;isc.Log.logWarn("ClassFactory.newInstance() passed an object with illegal 'constructor' "+"property - removing this property from the final object. "+"To avoid seeing this message in the future, "+"specify the object's class using '_constructor'.","ClassFactory");_2.constructor=null}
_7=this.getClass(_8)}
if(_7==null){isc.Log.logWarn("newInstance("+_1+"): class not found","ClassFactory");return null}
return _7.newInstance(_2,_3,_4,_5,_6)}
,isc.A.$b7=function isc_ClassFactory__getConstructorFunction(_1){var _2=(isc.Browser.isSafari?function(){}:new Function());_2.prototype=_1;return _2}
,isc.A.addGlobalID=function isc_ClassFactory_addGlobalID(_1,_2,_3){_1.ID=_2||_1.ID;if(_1.ID==null){_1.ID=this.getNextGlobalID(_1);_1.$541=true}
var _4=this.getWindow();var _5,_6;if(_4[_1.ID]!=null){var _7=isc.isA.Canvas(_4[_1.ID]);if(!_3){isc.Log.logWarn("ClassFactory.addGlobalID: ID:'"+_1.ID+"' for object '"+_1+"' collides with ID of existing object '"+_4[_1.ID]+"'."+(_7?" The pre-existing widget will be destroyed.":" The global reference to this object will be replaced"))}
if(_7)_4[_1.ID].destroy();if(!_7){if(this.$60l[_2])_5=true;else _6=true}}
if(!_5){if(_6){try{_4[_1.ID]=_1}catch(e){_5=true}
if(_4[_1.ID]!=_1){_5=true}}else{_4[_1.ID]=_1}}
if(_5){var _8=this.getNextGlobalID(_1);isc.logWarn("ClassFactory.addGlobalID: ID specified as:"+_1.ID+". This is a reserved word in Javascript or a native property of the"+" browser window object and can not be used as an ID."+" Setting ID to "+_8+" instead.");_1.ID=_8;_1.$541=true;_4[_1.ID]=_1}
if(isc.globalsSnapshot)isc.globalsSnapshot.add(_1.ID)}
,isc.A.getNextGlobalID=function isc_ClassFactory_getNextGlobalID(_1){var _2=_1!=null&&isc.isA.String(_1.Class)?_1.Class:null;return this.getNextGlobalIDForClass(_2)}
,isc.A.getNextGlobalIDForClass=function isc_ClassFactory_getNextGlobalIDForClass(_1){if(_1){var _2=this.$63v[_1]
if(_2&&_2.length>0){var _3=_2[_2.length-1];_2.length=_2.length-1;return _3}
var _4;if(this.$75m[_1]==null)this.$75m[_1]=0;_4=this.$75m[_1]++;var _5=this.$bw;_5[0]=this._$isc_;_5[1]=_1;_5[2]=this.$bv;isc.$bk(_5,_4,3,5);var _6=_5.join(isc.emptyString);return _6}
return this._$isc_OID_+this.$cb++}
,isc.A.dereferenceGlobalID=function isc_ClassFactory_dereferenceGlobalID(_1){if(window[_1.ID]==_1){window[_1.ID]=null;if(_1.Class!=null&&_1.$541){this.releaseGlobalID(_1.Class,_1.ID)}}}
,isc.A.releaseGlobalID=function isc_ClassFactory_releaseGlobalID(_1,_2){if(!this.reuseGlobalIDs)return;var _3=this.$63v[_1];if(!_3)this.$63v[_1]=[_2];else if(_3.length<=this.globalIDClassPoolSize)_3[_3.length]=_2}
,isc.A.releaseDOMID=function isc_ClassFactory_releaseDOMID(_1){if(!this.reuseDOMIDs||this.$63w.length>this.DOMIDPoolSize)return;this.$63w[this.$63w.length]=_1}
,isc.A.getDOMID=function isc_ClassFactory_getDOMID(_1,_2){if(!isc.$e||!_1||!_2){var _3=this.$63w.length
if(_3>0){var _1=this.$63w[_3-1];this.$63w.length=_3-1;return _1}
var _4=this.$bx++;return this.$cc(_4,this._$isc_)}
this.$by[0]=_1;this.$by[2]=_2;return this.$by.join(isc.emptyString)}
,isc.A.$cc=function isc_ClassFactory__convertToBase36(_1,_2){var _3=this.$bz,_4=this.$b0;_4.length=0;if(_2)_4[0]=_2;var _5=3;if(_1>46655){while(Math.pow(36,_5)<=_1)_5+=1}
while(_1>=36){var _6=_1%36;_4[_5-(_2?0:1)]=_3[_6];_5-=1;_1=Math.floor(_1/ 36)}
_4[_5-(_2?0:1)]=_3[_1];return _4.join(isc.emptyString)}
,isc.A.mixInInterface=function isc_ClassFactory_mixInInterface(_1,_2){var _3=this.getClass(_2),_4=this.getClass(_1);if(!_3||!_4)return null;if(!_3.$a5){isc.Log.logWarn("ClassFactory.mixInInterface asked to mixin a class which was not"+" declared as an Interface: "+_2+" onto "+_1);return}
if(!_4.$cd)_4.$cd=[];else _4.$cd=_4.$cd.duplicate();while(_3){this.$ce(_3,_4,true);this.$ce(_3,_4);_4.$cd[_4.$cd.length]=_2;_3=_3.getSuperClass();if(_3&&!_3.$a5)break}}
,isc.A.$ce=function isc_ClassFactory__mixInProperties(_1,_2,_3){var _4;if(_3){_4=isc.$a3[_1.Class]}else{_4=isc.$a2[_1.Class];_1=_1.getPrototype();_2=_2.getPrototype()}
if(_4==null)return;for(var i=0;i<_4.length;i++){var _6=_4[i];if(_2[_6]!=null)continue;var _7=_1[_6];if(isc.isA.String(_7)&&_7==this.TARGET_IMPLEMENTS){var _8=(_3?"Class":"Instance")+" method "+_6+" of Interface "+_1.Class+" must be implemented by "+"class "+_2.Class;_2[_6]=new Function('this.logError("'+_8+'")')}else{_2[_6]=_7}}}
,isc.A.makePassthroughMethods=function isc_ClassFactory_makePassthroughMethods(_1,_2,_3,_4){if(!_2)_2="parentElement";var _5;if(!_3){_5=this.$cf;if(_5==null){_5=this.$cf=["return this.",,".",,"("+this.$b1+")"]}}else{_5=this.$89q;if(_5==null){_5=this.$89q=["if(this.",,"==null){\n",,"return}\nreturn this.",,".",,"("+this.$b1+")"]}}
var _6={};for(var i=0;i<_1.length;i++){var _8=_1[i];if(_3){_5[1]=_2;if(_4!=null){var _9={methodName:_8,propName:_2};var _10=_4.evalDynamicString(this,_9);_5[3]="isc.logWarn(\""+_10+"\");"}
_5[5]=_2;_5[7]=_8}else{_5[1]=_2;_5[3]=_8}
_6[_8]=new Function(this.$b1,_5.join(isc.emptyString))}
return _6}
,isc.A.writePassthroughFunctions=function isc_ClassFactory_writePassthroughFunctions(_1,_2,_3){var _4=this.makePassthroughMethods(_3,_2);_1.addMethods(_4)}
);isc.B._maxIndex=isc.C+23;isc.A=isc.ClassFactory;isc.A.TARGET_IMPLEMENTS="TARGET_IMPLEMENTS";isc.A.$cb=0;isc.A.classList=[];isc.defineClass=function(_1,_2,_3,_4){return isc.ClassFactory.defineClass(_1,_2,_3,_4)}
isc.overwriteClass=function(_1,_2,_3,_4){return isc.ClassFactory.overwriteClass(_1,_2,_3,_4)}
isc.defineInterface=function(_1,_2){return isc.ClassFactory.defineInterface(_1,_2)}
isc.defer=function(_1){var _2=isc.ClassFactory.getClass(isc.ClassFactory.classList.last()),_3=_2.$cg;isc.Log.logWarn("deferred code being placed on class: "+_2);if(!_3)_2.$cg=[_1];else _3.add(_1)}
if(!isc.Browser.isSafari){isc.$ch=window;isc.$ci=window.document}
if(window.isc_enableCrossWindowCallbacks&&isc.Browser.isIE){isc.enableCrossWindowCallbacks=true;Object.$ch=window}
isc.ClassFactory.defineRootClass('Class');isc.ClassFactory.defaultSuperClass=isc.Class;isc.A=isc.Class;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.addClassMethods=function isc_Class_addClassMethods(){for(var i=0;i<arguments.length;i++)
isc.addMethods(this,arguments[i])}
);isc.B._maxIndex=isc.C+1;isc.A=isc.Class;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$cj={};isc.A.dontDup={StringBuffer:true,Action:true,MathFunction:true,JSONEncoder:true};isc.A.$ck={};isc.A.fireOnPauseDelay=200;isc.A.$cl="$cm";isc.A.$cn={};isc.A.$co={};isc.A.useFastEvalWithVars=isc.Browser.isMoz&&isc.Browser.geckoVersion>=20061010;isc.A.$cp="ID";isc.A.getWindow=(isc.Browser.isSafari?function(){return window}:function(){return this.ns.$ch});isc.A.getDocument=(isc.Browser.isSafari?function(){return window.document}:function(){return this.ns.$ci});isc.B.push(isc.A.create=function isc_c_Class_create(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13){var _14=this.createRaw();_14=_14.completeCreation(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13);return _14}
,isc.A.createRaw=function isc_c_Class_createRaw(){if(!this.initialized())this.init();var _1=new this.$b4.$b5();_1.ns=this.ns;return _1}
,isc.A.init=function isc_c_Class_init(){var _1=this.getSuperClass();if(_1&&!_1.initialized())_1.init();var _2=this.$cg;if(_2){this.$cg=null;_2.map(eval)}
if(this.autoDupMethods){isc.Class.duplicateMethods(this,this.autoDupMethods)}
this.$cj[this.Class]=true}
,isc.A.duplicateMethods=function isc_c_Class_duplicateMethods(_1,_2){if(_1.Class&&this.dontDup[_1.Class])return;for(var i=0;i<_2.length;i++){var _4=_2[i];this.duplicateMethod(_4,_1)}}
,isc.A.duplicateMethod=function(methodName,target){if(!target)target=this;var method=target[methodName];if(method==null)return;if(method.$761){while(method.$761)method=method.$761}
var dup;if(method.toSource==null){dup=eval("dup = "+method.toString())}else{dup=eval(method.toSource())}
if(!method.$dx)isc.Func.getName(method,true);dup.$dx=method.$dx+"[d]";dup.$761=method;target[methodName]=dup;return dup}
,isc.A.initialized=function isc_c_Class_initialized(){return this.$cj[this.Class]}
,isc.A.getClassName=function isc_c_Class_getClassName(){return this.Class}
,isc.A.getSuperClass=function isc_c_Class_getSuperClass(){return this.$b8}
,isc.A.getPrototype=function isc_c_Class_getPrototype(){return this.$b4}
,isc.A.addMethods=function isc_c_Class_addMethods(){if(this.$a5){this.logWarn("Use addInterfaceMethods() to add methods to interface "+this)}
for(var i=0;i<arguments.length;i++)
isc.addMethods(this.$b4,arguments[i]);return this.$b4}
,isc.A.addInterfaceMethods=function isc_c_Class_addInterfaceMethods(){for(var i=0;i<arguments.length;i++)
isc.addMethods(this.$b4,arguments[i])}
,isc.A.addInterfaceProperties=function isc_c_Class_addInterfaceProperties(){isc.addPropertyList(this.$b4,arguments)}
,isc.A.registerStringMethods=function isc_c_Class_registerStringMethods(_1,_2){var _3=this._stringMethodRegistry;if(!this.isOverridden("_stringMethodRegistry")){var _4={},_5=_4.$cq=(_3.$cq?_3.$cq.duplicate():[]);for(var i=0;i<_5.length;i++){_4[_5[i]]=_3[_5[i]]}
this._stringMethodRegistry=_3=_4}
if(!isc.isA.String(_1)){var _7=_1;if(!isc.isAn.Object(_7)){this.logWarn("registerStringMethods() called with a bad argument: "+_1);return false}
for(var _1 in _7){_3[_1]=_7[_1]
_3.$cq.add(_1)}}else{if(_2==null)_2=null;_3[_1]=_2;_3.$cq.add(_1)}
return true}
,isc.A.registerDupProperties=function isc_c_Class_registerDupProperties(_1,_2){if(this.$769==null||this.$769.$bd!=this.getClassName()){if(this.$769!=null){var _3=this.$769;this.$769=this.$769.duplicate();if(_3.$77a!=null){this.$769.$77a=isc.shallowClone(_3.$77a)}}else{this.$769=[]}
this.$769.$bd=this.getClassName()}
if(!this.$769.contains(_1)){this.$769.add(_1)}
if(_2!=null){var _4=this.$769.$77a||{};_4[_1]=_2;this.$769.$77a=_4}}
,isc.A.isDupProperty=function isc_c_Class_isDupProperty(_1){return this.$769!=null&&this.$769.contains(_1)}
,isc.A.cloneDupPropertyValue=function isc_c_Class_cloneDupPropertyValue(_1,_2){if(isc.isA.Array(_2)){var _3=[];for(var i=0;i<_2.length;i++){_3[i]=this.cloneDupPropertyValue(_1,_2[i])}
return _3}
if(isc.Canvas&&isc.isA.Canvas(_2)){this.logWarn("Default value for property '"+_1+"' is set to a live Canvas (with ID '"+_2.getID()+"') at the Class or AutoChild-defaults level. "+"SmartClient cannot clone a live widget, so each instance of this "+"class may end up pointing to the same live component. "+"To avoid unpredictable behavior and suppress this warning, use the "+"AutoChild subsystem to set up re-usable default properties for sub-components.");return _2}
var _5=isc.shallowClone(_2);var _6=this.$769;if(_6.$77a!=null&&_6.$77a[_1]!=null&&_5!=null)
{for(var i=0;i<_6.$77a[_1].length;i++){var _7=_6.$77a[_1][i];if(_5[_7]!=null){_5[_7]=isc.shallowClone(_5[_7])}}}
return _5}
,isc.A.evaluate=function isc_c_Class_evaluate(_1,_2,_3){if(!isc.$611)isc.$611=0;isc.$611++;var _4;if(_2){with(_2){if(_3)_4=window.eval(_1)
else _4=eval(_1)}}else{if(_3)_4=window.eval(_1)
else _4=eval(_1)}
if(isc.$611!=null)isc.$611--;if(isc.$611==0)delete isc.$611;return _4}
,isc.A.addClassProperties=function isc_c_Class_addClassProperties(){isc.addPropertyList(this,arguments);return this}
,isc.A.markAsFrameworkClass=function isc_c_Class_markAsFrameworkClass(){this.isFrameworkClass=true;this.$b4.isFrameworkClass=true;this.$75y=this.Class;this.$b4.$75y=this.Class}
,isc.A.addProperties=function isc_c_Class_addProperties(){if(this.$a5){this.logWarn("Use addInterfaceProperties() to add methods to interface "+this)}
isc.addPropertyList(this.$b4,arguments);return this}
,isc.A.addPropertyList=function isc_c_Class_addPropertyList(_1){isc.addPropertyList(this.$b4,_1);return this}
,isc.A.changeDefaults=function isc_c_Class_changeDefaults(_1,_2){var _3=this.$cr(_1),_4=false;var _5=this.getSuperClass();if(_5){var _6=_5.$cr(_1);if(_6!=null&&_6==_3){_3=isc.addProperties({},_3);_4=true}}
if(_3==null){_3=_2||{};_4=true}else{isc.addProperties(_3,_2)}
if(_4){var _7={};_7[_1]=_3;this.addProperties(_7)}}
,isc.A.$cr=function isc_c_Class__getDefaults(_1){var _2=this.$ck[this.Class],_3=this.getInstanceProperty(_1)||(_2?_2[_1]:null);return _3}
,isc.A.replaceDefaults=function isc_c_Class_replaceDefaults(_1,_2){this.changeDefaults(_1,_2)}
,isc.A.setProperties=function isc_c_Class_setProperties(){var _1;if(arguments.length==1){_1=arguments[0]}else{_1={};for(var i=0;i<arguments.length;i++){isc.addProperties(_1,arguments[i])}}
this.$b4.setProperties(_1)}
,isc.A.isOverridden=function isc_c_Class_isOverridden(_1){return(!(this[_1]===this.$b8[_1]))}
,isc.A.isA=function isc_c_Class_isA(_1){if(_1==null)return false;if(!isc.isA.String(_1)){_1=_1.Class;if(!isc.isA.String(_1))return false}
if(isc.startsWith(_1,isc.ClassFactory.$f)){_1=_1.substring(4)}
var _2=this;while(_2){if(_2.Class==_1)return true;_2=_2.$b8}
if(this.$cd){for(var i=0;i<this.$cd.length;i++){var _4=isc.ClassFactory.getClass(this.$cd[i]);while(_4){if(_4.Class==_1)return true;_4=_4.$b8}}}
return false}
,isc.A.$cs=function isc_c_Class__getNextImplementingSuper(_1,_2,_3,_4){var _5;for(;;){if(_2==null){_5=null;break}
var _5=isc.Class.$ct(_3,_2);if(_5==null)break;if(_1!=_5){break}
if(_4){_2=_2.$b8}else{_2=_2.$b9.$b8.$b4}}
if(_5!=null)return _2;return null}
,isc.A.Super=function isc_c_Class_Super(_1,_2,_3){if(isc.$cv)arguments.$cw=this;if(this.autoDupMethods&&isc.isAn.Instance(this)){this.duplicateMethod("Super");this.duplicateMethod("invokeSuper")}
if(_2!=null&&(_2.length==null||isc.isA.String(_2)))_2=[_2];if(_2==null)_2=isc.$ac;this.$cx=_3||_2;this.$85s=_2;this.$cy=isc.Class.$cz(_1,this);this.$c0=true;return this.invokeSuper(null,_1)}
,isc.A.$ct=function isc_c_Class__getOriginalMethod(_1,_2){var _3=_2[_1];while(_3!=null&&_3.$c1){_3=_2[_3.$c1]}
if(_3!=null&&_3.$761!=null)_3=_3.$761;return _3}
,isc.A.invokeSuper=function isc_c_Class_invokeSuper(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10){var _11=this.$bp;var _12=this.$c0;this.$c0=null;var _13=this.$cx;this.$cx=null;var _14=this.$85s;this.$85s=null;var _15;if(_12){_15=this.$cy;this.$cy==null}else{if(_1!=null){_15=_11?_1:_1.$b4}}
var _16,_17;if(_15==null){_16=isc.Class.$ct(_2,this);_17=_11?this:this.getPrototype()}else{_16=isc.Class.$ct(_2,_15);if(_11){_17=_15.$b8}else{_17=_15.$b9.$b8.$b4}
if(_13&&_13.callee!=null&&_13.callee!=_16)
{_16=isc.Class.$ct(_2,this);_17=_11?this:this.getPrototype()}}
_17=isc.Class.$cs(_16,_17,_2,_11);if(_17==null){if(isc.Log)isc.Log.logWarn("Call to Super for method: "+_2+" failed on: "+this+": couldn't find a superclass implementation of : "+(_15?_15.Class:this.Class)+"."+_2+this.getStackTrace());return null}
var _18=_17[_2];isc.Class.$c2(_2,_17,this);var _19;if(_12){if(_14!=null||_13!=null){_19=_18.apply(this,_14==null?_13:_14)}else{_19=_18.apply(this)}}else{_19=_18.call(this,_3,_4,_5,_6,_7,_8,_9,_10)}
isc.Class.$c3(_2,this);return _19}
,isc.A.$cz=function isc_c_Class__getLastProto(_1,_2){var _3=_2.$c4,_4=_3==null?null:_3[_1];if(isc.isAn.Array(_4))return _4.last();return _4}
,isc.A.$c3=function isc_c_Class__clearLastProto(_1,_2){var _3=_2.$c4,_4=_3[_1];if(_4==null){return}
if(!_4.$81d){_3[_1]=null}else{_4.length=Math.max(0,_4.length-1);if(_4.length==0)_3[_1]=null}}
,isc.A.$c2=function isc_c_Class__addProto(_1,_2,_3){var _4=_3.$c4=_3.$c4||{},_5=_4[_1];if(_5==null){_4[_1]=_2}else{if(isc.isAn.Array(_5))_5.add(_2);else{_4[_1]=[_5,_2];_4[_1].$81d=true}}}
,isc.A.map=function isc_c_Class_map(_1,_2,_3,_4,_5,_6,_7){if(_1==null)return _2;var _8=[];for(var i=0;i<_2.length;i++){_8.add(this[_1](_2[i],_3,_4,_5,_6,_7))}
return _8}
,isc.A.getInstanceProperty=function isc_c_Class_getInstanceProperty(_1){var _2=this.$b4[_1];return _2}
,isc.A.setInstanceProperty=function isc_c_Class_setInstanceProperty(_1,_2){this.$b4[_1]=_2}
,isc.A.getArgString=function isc_c_Class_getArgString(_1){var _2=this._stringMethodRegistry[_1];var _3;if(_2!==_3)return _2||isc.emptyString;var _4=this.getInstanceProperty(_1);if(_4==null)return"";return isc.Func.getArgString(_4)}
,isc.A.fireCallback=function isc_c_Class_fireCallback(_1,_2,_3,_4,_5){arguments.$cw=this;if(_1==null)return;var _6;if(_2==null)_2=_6;var _7=_1;if(isc.isA.String(_1)){if(_4!=null&&isc.isA.Function(_4[_1]))_7=_4[_1];else _7=this.$c5(_1,_2)}else if(isc.isAn.Object(_1)&&!isc.isA.Function(_1)){if(_1.caller!=null)_4=_1.caller;else if(_1.target!=null)_4=_1.target;if(_1.args)_3=_1.args;if(_1.argNames)_2=_1.argNames;if(_1.method)_7=_1.method;else if(_1.methodName&&_4!=null)_7=_4[_1.methodName];else if(_1.action)
_7=this.$c5(_1.action,_2)}
if(!isc.isA.Function(_7)){this.logWarn("fireCallback() unable to convert callback: "+this.echo(_1)+" to a function.  target: "+_4+", argNames: "+_2+", args: "+_3);return}
if(_4==null)_4=window;else if(_4.destroyed){if(this.logIsInfoEnabled("callbacks")){this.logInfo("aborting attempt to fire callback on destroyed target:"+_4+". Callback:"+isc.Log.echo(_1)+",\n stack:"+this.getStackTrace())}
return}
_7.$c6=true;if(_3==null)_3=[];if(isc.enableCrossWindowCallbacks&&isc.Browser.isIE){var _8=_4.constructor?_4.constructor.$ch:_4;if(_8&&_8!=window&&_8.isc){var _9=_8.Array.newInstance();for(var i=0;i<_3.length;i++)_9[i]=_3[i];_3=_9}}
var _11;if(!_5||isc.Log.supportsOnError){_11=_7.apply(_4,_3)}else{try{_11=_7.apply(_4,_3)}catch(e){isc.Log.$am(e);throw e;}}
return _11}
,isc.A.delayCall=function isc_c_Class_delayCall(_1,_2,_3,_4){if(_4==null)_4=this;if(_3==null)_3=0;return isc.Timer.setTimeout({target:_4,methodName:_1,args:_2},_3)}
,isc.A.$c5=function isc_c_Class__makeCallbackFunction(_1,_2){if(_2==null){var _3;_2=_3}
var _4=isc.$aq(_2,_1);_4.$c7=true;return _4}
,isc.A.fireOnPause=function isc_c_Class_fireOnPause(_1,_2,_3,_4,_5){if(!_1)return;if(!_3)_3=this.fireOnPauseDelay;if(_5==null)_5=this.getClassName();if(!this.$cn[_1]){this.$cn[_1]={}}
this.$cn[_1][_5]={fireTime:_3,callback:_2,target:_4};var _6=isc.timeStamp(),_7=this.$c8?_6-this.$c8:null;this.$c8=_6;if(_7&&this.$c9!=null&&_3>=(this.$c9-_7))return;if(this.$da)isc.Timer.clearTimeout(this.$da);this.$da=this.delayCall(this.$cl,null,_3);this.$c9=_3}
,isc.A.$cm=function isc_c_Class__fireActionsOnPause(){var _1;var _2=isc.timeStamp()-this.$c8,_1;for(var _3 in this.$cn){var _4=this.$cn[_3];for(var _5 in _4){var _6=_4[_5];if(_6.fireTime<=_2){this.fireCallback(_6.callback,null,null,_6.target);delete this.$cn[_3][_5]}else{_6.fireTime-=_2;if(_1==null)_1=_6.fireTime;else _1=Math.min(_1,_6.fireTime)}}
if(isc.isAn.emptyObject(this.$cn[_3]))delete this.$cn[_3]}
if(_1!=null){this.$c9=_1;this.$c8=isc.timeStamp();this.delayCall(this.$cl,null,_1)}else{this.$c9=null;this.$c8=null}}
,isc.A.evalWithVars=function isc_c_Class_evalWithVars(_1,_2,_3){if(!_3)_3=window;if(this.useFastEvalWithVars){return this.evaluate.call(_3,_1,_2)}
var _4="_1";while(_2&&isc.propertyDefined(_2,_4)){_4+="1"}
var _5=[_4];var _6=[_1];if(_2){for(var _7 in _2){_5.push(_7);_6.push(_2[_7])}}
var _8=isc.$aq(_5.join(","),"return eval("+_4+")");return _8.apply(_3,_6)}
,isc.A.evalWithCapture=function isc_c_Class_evalWithCapture(_1,_2,_3){var _4=isc.globalsSnapshot=[];this.evalWithVars(_1,_2,_3);isc.globalsSnapshot=null;return _4}
,isc.A.destroyGlobals=function isc_c_Class_destroyGlobals(_1){if(!isc.isAn.Array(_1))_1=[_1];for(var i=0;i<_1.length;i++){var _3=_1[i];if(window[_3]&&isc.isA.Function(window[_3].destroy))window[_3].destroy();else window[_3]=null}}
,isc.A.globalEvalWithCapture=function isc_c_Class_globalEvalWithCapture(_1,_2,_3,_4){if(_4==null)_4=true;this.$db=_3;this.$dc=_2;if(isc.Browser.isSafari){_1="isc.Class.$dd();\n"+_1+"\nisc.Class.$de();";window.setTimeout(_1,0);return}
this.$dd(_3);var _5;try{if(isc.Browser.isIE){window.execScript(_1,"javascript")}else{isc.Class.evaluate(_1,null,true)}}catch(e){if(_4)isc.Log.$am(e);_5=e}
this.$de(_5)}
,isc.A.$dd=function isc_c_Class__globalEvalWithCaptureStart(){var _1,_2=this.$db;this.$df={};if(_2){for(var _3 in _2){var _4=window[_3];if(_4!==_1)this.$df[_3]=_4;window[_3]=_2[_3]}}
isc.globalsSnapshot=[]}
,isc.A.$de=function isc_c_Class__globalEvalWithCaptureEnd(_1){var _2,_3=this.$db;if(_3){for(var _4 in _3){var _5=this.$df[_4];if(_5!==_2)window[_4]=this.$df[_4];else window[_4]=_2}}
var _6=this.$dc;var _7=isc.globalsSnapshot;isc.globalsSnapshot=this.$dc=this.$db=this.$df=null;this.fireCallback(_6,["globals","error"],[_7,_1])}
,isc.A.$dg=function isc_c_Class__notifyFunctionComplete(_1,_2,_3){_3.$dh-=1;if(_3.$dh)return;var _4=false;for(var i=0;i<_3.length;i++){if(_3[i].$di){_4=true;_3.removeItem(i);i--;continue}
if(_3[i].$dj){delete _3[i].$dj;_4=true}}
if(_4){if(_3.length==0){var _6=isc.$ah+_2;_1[_2]=_1[_6];delete _1[_6]}else{_1[_2]=_1.$dk(_2,_3)}}}
,isc.A.getArrayItem=function isc_c_Class_getArrayItem(_1,_2,_3){if(_2==null)return null;if(isc.isA.Number(_1))return _2[_1];if(isc.isAn.Object(_1))return _1;if(isc.isA.String(_1))return _2.find(_3||this.$cp,_1);return null}
,isc.A.getArrayItemIndex=function isc_c_Class_getArrayItemIndex(_1,_2,_3){if(isc.isA.Number(_1))return _1;var _4=isc.Class.getArrayItem(_1,_2,_3);return _2.indexOf(_4)}
,isc.A.getDocumentBody=function isc_c_Class_getDocumentBody(_1){var _2=(!_1&&isc.Browser.isIE&&isc.Browser.isStrict);var _3=(_2?this.ns.$dl:this.ns.$dm);if(_3!=null)return _3;var _4=this.getDocument();if(_2){this.ns.$dl=_4.documentElement;return this.ns.$dl}
if(isc.Browser.isIE){_3=_4.body}else{if(_4.body!=null)_3=_4.body;else{var _5=_4.documentElement.namespaceURI;_3=_4.getElementsByTagNameNS(_5,"body")[0];if(_3==null){_3=_4.documentElement.childNodes[1];if(_3!=null&&_3.tagName!="body")_3=null}
if(!_3)return null}}
this.ns.$dm=_3;return _3}
,isc.A.getActiveElement=function isc_c_Class_getActiveElement(){try{return this.getDocument().activeElement}catch(e){this.logWarn("error accessing activeElement: "+e.message)}
return null}
);isc.B._maxIndex=isc.C+54;isc.A=isc.Class;isc.A.newInstance=isc.Class.create;isc.Class.ns=isc;isc.A=isc.ClassFactory;isc.A.ns=isc;isc.A.getWindow=isc.Class.getWindow;isc.A.getDocument=isc.Class.getDocument;isc.A=isc.Class.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.getWindow=(isc.Browser.isSafari?function(){return window}:function(){return this.ns.$ch});isc.A.getDocument=(isc.Browser.isSafari?function(){return window.document}:function(){return this.ns.$ci});isc.A.$dn="_autoMaker";isc.A.$58e="creator";isc.A.$do="show";isc.A.$dp="Constructor";isc.A.$dq="Defaults";isc.A.$dr="Properties";isc.A.map=isc.Class.map;isc.A.Super=isc.Class.Super;isc.A.invokeSuper=isc.Class.invokeSuper;isc.B.push(isc.A.init=function isc_Class_init(){}
,isc.A.destroy=function isc_Class_destroy(){}
,isc.A.completeCreation=function isc_Class_completeCreation(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13){if(this.addPropertiesOnCreate!=false){if(isc.captureInitData){var _14={className:this.Class,defaults:isc.addProperties({},_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13)}
if(!isc.capturedComponents)isc.capturedComponents=[];isc.capturedComponents.add(_14);if(_14.defaults.ID){isc.ClassFactory.addGlobalID(_14,_14.defaults.ID)}
return _14}
isc.addProperties(this,_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13)}
var _15=this.getClass(),_16=_15.$769||[];for(var i=0;i<_16.length;i++){var _18=_16[i];if(this[_18]==_15.$b4[_18])
{this[_18]=_15.cloneDupPropertyValue(_18,this[_18])}}
this.init(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13);if(this.autoDupMethods){isc.Class.duplicateMethods(this,this.autoDupMethods)}
return this}
,isc.A.duplicateMethod=function isc_Class_duplicateMethod(_1){isc.Class.duplicateMethod(_1,this)}
,isc.A.getUniqueProperties=function isc_Class_getUniqueProperties(_1){if(_1==null)_1={};var _2=this.getPrototype();for(var _3 in this){if(_3.startsWith("_"))continue;if(_3=="ns")continue;if(_3=="ID"&&this.ID.startsWith("isc_OID_"))continue;var _4=this[_3];if(isc.isA.Function(_4))continue;if(_4!=_2[_3]){_1[_3]=this[_3]}}
return _1}
,isc.A.clone=function isc_Class_clone(){return this.getClass().create(this.getUniqueProperties())}
,isc.A.serialize=function isc_Class_serialize(_1){return isc.Comm.serialize(this,_1)}
,isc.A.xmlSerialize=function isc_Class_xmlSerialize(_1){return isc.Comm.xmlSerialize(this.getClassName(),this,_1)}
,isc.A.getSerializeableFields=function isc_Class_getSerializeableFields(_1,_2){var _3=isc.DS?isc.DS.getNearestSchema(this):null;var _4=this.getUniqueProperties();if(_3==null){this.logDebug("No schema available for class"+this.getClassName());return _4}else{this.logDebug("Constraining serializeable fields for class: "+this.getClassName()+" with schema : "+_3.ID)}
var _5=isc.applyMask(_4,_3.getFields());_1=_1||[];_2=_2||[];_1.map(function(_7){delete _5[_7]});for(var i=0;i<_2.length;i++){_5[_2[i]]=this[_2[i]]}
return _5}
,isc.A.getID=function isc_Class_getID(){return this.ID}
,isc.A.getClass=function isc_Class_getClass(){return this.$b9}
,isc.A.getSuperClass=function isc_Class_getSuperClass(){return this.$b9.$b8}
,isc.A.getClassName=function isc_Class_getClassName(){return this.getClass().getClassName()}
,isc.A.getPrototype=function isc_Class_getPrototype(){return this.$76y}
,isc.A.getGlobalReference=function isc_Class_getGlobalReference(_1){if(typeof _1=="string")return this.evaluate(_1);return _1}
,isc.A.addMethods=function isc_Class_addMethods(){for(var i=0;i<arguments.length;i++){return isc.addMethods(this,arguments[i])}}
,isc.A.addProperties=function isc_Class_addProperties(){return isc.addPropertyList(this,arguments)}
,isc.A.addPropertyList=function isc_Class_addPropertyList(_1){return isc.addPropertyList(this,_1)}
,isc.A.$ds=function isc_Class__getSetter(_1){var _2="set"+_1.substring(0,1).toUpperCase()+_1.substring(1);return(isc.isA.Function(this[_2])?_2:null)}
,isc.A.$dt=function isc_Class__getGetter(_1){var _2="get"+_1.substring(0,1).toUpperCase()+_1.substring(1);return(isc.isA.Function(this[_2])?_2:null)}
,isc.A.setProperty=function isc_Class_setProperty(_1,_2){var _3={};_3[_1]=_2;this.setProperties(_3)}
,isc.A.setProperties=function isc_Class_setProperties(){var _1=isc.isA,_2,_3={};if(arguments.length<1)return;if(arguments.length==1){_2=arguments[0];if(_2==null)return}else{_2={};for(var i=0;i<arguments.length;i++){isc.addProperties(_2,arguments[i])}}
for(var _5 in _2){var _6=_2[_5],_7=this.$ds(_5);if(isc.isA.StringMethod(_6))_6=_6.getValue();if(_7){this[_7](_6);if(this.propertyChanged)this.propertyChanged(_5,_6)}else{_3[_5]=_6}}
this.addProperties(_3)
if(this.propertyChanged){for(var _5 in _3){this.propertyChanged(_5,_3[_5])}}
if(this.doneSettingProperties)this.doneSettingProperties(_2)}
,isc.A.getProperty=function isc_Class_getProperty(_1){var _2=this.$dt(_1);if(_2)return this[_2]();return this[_1]}
,isc.A.$du=function isc_Class__firstNonNull(_1,_2,_3,_4,_5,_6){return _1!=null?_1:(_2!=null?_2:(_3!=null?_3:(_4!=null?_4:(_5!=null?_5:_6))))}
,isc.A.isA=function isc_Class_isA(_1){return this.getClass().isA(_1)}
,isc.A.observe=function isc_Class_observe(_1,_2,_3){if(_1==null){this.logWarn("Invalid observation: Target is not an object.  target: "+_1+", methodName: "+_2+", action: '"+_3+"'");return false}
if(!isc.Func.convertToMethod(_1,_2)){this.logWarn("Invalid observation: property: '"+_2+"' is not a method on "+_1);return false}
var _4=isc.$a9[_2];if(_1[_4])this.observe(_1,_4,_3)
var _5=_1[_2],_6;if(isc.isAn.Instance(_1)&&_1.getClass().getInstanceProperty(_2)){_6=_1.getClass().getArgString(_2)}else{_6=isc.Func.getArgString(_5)}
var _7=_6.split(",");if(!_1.$ba)_1.$ba={};if(!_1.$ba[_2]){var _8=_1.$ba[_2]=[];if(_7.length>0){_8.argStr=_6}}else{var _8=_1.$ba[_2];for(var i=0,_10=_8.length;i<_10;i++){if(_8[i].target==this){if(_8[i].$di){_8[i].$di=false;_8[i].action=_3;return true}
this.logWarn("Observer: "+this+" is already observing method '"+_2+"' on object '"+_1+"', ignoring");return false}}}
if(_3==null||isc.is.emptyString(_3)){if(!this[_2]||!this.convertToMethod(_2)){this.logWarn("Invalid Observation - no action specified, and observer: "+this+" has no method '"+_2+"', ignoring");return false}
_3="it."+_2+"("+_6+")"}
var _11=_8.$dh;_8.add({target:this,action:_3,$dj:_11});var _12=isc.$ah+_2;if(!_1[_12]){_1[_12]=_5}else if(!_1[_2].$dw){this.logWarn("Observation error: method "+_2+" is being observed on object "+_1+" but the function appears to have "+"been directly overridden. This may lead to unexpected behavior - to avoid "+"seeing this message in the future, ensure the addMethods() or addProperties() "+"API is used to modify methods on live SmartClient instances, rather than simply "+"reassigning the method name to a new function instance.");_1[_12]=_1[_2]}
if(!_11)_1[_2]=this.$dk(_2,_8);return true}
,isc.A.$dk=function isc_Class__makeNotifyFunction(_1,_2){var _3=isc.StringBuffer.create();_3.append((isc.$cv?"arguments.$cw=this;":""),"var queue=this.$ba.",_1,";\r","queue.$dh=queue.$dh?queue.$dh+1:1;\r","var returnVal=this.",isc.$ah,_1,"(",(_2.argStr?_2.argStr:""),"),\r","observed=this,observer,it;\r");for(var i=0,_5=_2.length;i<_5;i++){_3.append("if(!queue)return;\r");_3.append("observer=it=queue[",i,"].target;\r");if(isc.isA.String(_2[i].action))_3.append(_2[i].action,";\r");if(isc.isA.Function(_2[i].action)){_3.append("queue[",i,"].action.apply(it, ",(_2.argStr?"'"+_2.argStr+"'":"null"),");\r")}}
if(isc.Browser.isSafari){_3.append("arguments.callee.$dv.Class.$dg(this,'",_1,"',queue);\r")}else{_3.append("isc.Class.$dg(this,'",_1,"',queue);\r")}
_3.append("return returnVal;\r");var _6=isc.$aq(_2.argStr,_3);_6.$dw=true;_6.$dx=_1+"Observation";_6.$c1=isc.$ah+_1;if(isc.Browser.isSafari)_6.$dv=isc;return _6}
,isc.A.ignore=function isc_Class_ignore(_1,_2){var _3;var _4=isc.$a9[_2];if(_4!==_3&&_1[_4])this.ignore(_1,_4);var _5=isc.$ah+_2;if(!_1[_5]||!_1.$ba)return false;var _6=_1.$ba[_2],_7=_6.$dh;for(var i=0,_9=_6.length;i<_9;i++){if(_6[i].target==this){if(_7)
_6[i].$di=true;else
_6.removeAt(i);break}}
if(!_1[_2]||!_1[_2].$dw){this.logWarn("Observation error caught in ignore(): Method "+_2+" was being observed on object "+_1+" but the function appears to have "+"been directly overridden. This may lead to unexpected behavior - to avoid "+"seeing this message in the future, ensure the addMethods() or addProperties() "+"API is used to modify methods on live SmartClient instances, rather than simply "+"reassigning the method name to a new function instance.");_1[_5]=_1[_2]}
if(_6.length==0){_1[_2]=_1[_5];delete _1[_5];delete _1.$ba[_2]}else{if(!_7){_1[_2]=this.$dk(_2,_6)}}
return true}
,isc.A.getObserversOf=function isc_Class_getObserversOf(_1){if(!this.$ba||!this.$ba[_1])return null;var _2=this.$ba[_1];for(var _3=[],i=0;i<_2.length;i++){_3[i]=(_2[i]?_2[i].target:null)}
return _3}
,isc.A.isObserving=function isc_Class_isObserving(_1,_2){if(!_1.$ba)return false;var _3=_1.$ba[_2];if(!_3)return false;for(var i=0;i<_3.length;i++){if(_3[i].target==this)return true}
return false}
,isc.A.convertToMethod=function isc_Class_convertToMethod(_1){return isc.Func.convertToMethod(this,_1)}
,isc.A.evaluate=function isc_Class_evaluate(_1,_2){return isc.Class.evaluate.apply(this,[_1,_2])}
,isc.A.fireCallback=function isc_Class_fireCallback(_1,_2,_3,_4){return this.getClass().fireCallback(_1,_2,_3,this,_4)}
,isc.A.delayCall=function isc_Class_delayCall(_1,_2,_3){return this.getClass().delayCall(_1,_2,_3,this)}
,isc.A.fireOnPause=function isc_Class_fireOnPause(_1,_2,_3){return this.getClass().fireOnPause(_1,_2,_3,this,this.getID())}
,isc.A.evalWithVars=function isc_Class_evalWithVars(_1,_2){return isc.Class.evalWithVars(_1,_2,this)}
,isc.A.getDocumentBody=function isc_Class_getDocumentBody(){return isc.Class.getDocumentBody()}
,isc.A.getActiveElement=function isc_Class_getActiveElement(){return isc.Class.getActiveElement()}
,isc.A.addAutoChildren=function isc_Class_addAutoChildren(_1,_2,_3){if(_1==null)return;if(!isc.isAn.Array(_1))_1=[_1];for(var i=0;i<_1.length;i++){var _5=_1[i];if(isc.isA.Canvas(_5)){_2=_2||this;this.$dy(_5,_2,_3);continue}
this.addAutoChild(_5,null,null,_2,_3)}}
,isc.A.addAutoChild=function isc_Class_addAutoChild(_1,_2,_3,_4,_5){var _6=this[_1];if(isc.isAn.Instance(_6))return _6;if(isc.isAn.Object(_1)&&_1.autoChildName){_2=_1;_3=_2._constructor||_3;_1=_2.autoChildName}
if(isc.isA.String(_6)&&window[_6]){this[_1]=window[_6];return this[_1]}
if(_1!=null&&!this.shouldCreateChild(_1))return;var _7,_8=_1+this.$dn;if(_1!=null&&this[_8])_7=this[_8](_2);else{_7=this.createAutoChild(_1,_2,_3,true)}
if(!_7)return;this[_1]=_7;this.$dz(_1,_7,_4,_5);return _7}
,isc.A.$dz=function isc_Class__addToParent(_1,_2,_3,_4){if(_3==null){_3=_2.autoParent||this.getAutoChildParent(_1)}
if(isc.isA.String(_3)){if(_3==isc.Canvas.NONE){if(this.isDrawn())_2.draw();return}
var _5=this[_3]||window[_3]||_3;if(!isc.isA.Canvas(_5)){this.logWarn("no valid parent could be found for String '"+_3+"'")}else _3=_5}
if(!isc.isA.Canvas(_2)||!isc.isA.Canvas(_3))return;this.$dy(_2,_3,_4)}
,isc.A.$dy=function isc_Class__addAutoChildToParent(_1,_2,_3){if(_1.addAsPeer||_1.snapEdge)_2.addPeer(_1);else if(isc.isA.Layout(_2)&&!_1.addAsChild&&!_1.snapTo)_2.addMember(_1,_3);else if(isc.TileLayout&&isc.isA.TileLayout(_2)&&!_1.addAsChild&&!_1.snapTo)_2.addTile(_1,_3);else _2.addChild(_1)}
,isc.A.shouldCreateChild=function isc_Class_shouldCreateChild(_1){var _2=this.$do+_1.charAt(0).toUpperCase()+_1.substring(1);if(this[_2]!=null&&this[_2]==false)return false;var _3=this.$d0(_1);if(_3==null)return true;return(this.shouldCreateChild(_3))}
,isc.A.getAutoChildClass=function isc_Class_getAutoChildClass(_1,_2,_3,_4,_5){_4=_4||this.$d1(_1);var _6=this[_4];_5=_5||this.$543(_1);var _7=this[_5];return this[_1+this.$dp]||(_2?_2._constructor:null)||(_7?_7._constructor:null)||(_6?_6._constructor:null)||_3||isc.Canvas}
,isc.A.applyBaseDefaults=function isc_Class_applyBaseDefaults(_1,_2,_3){_1.autoDraw=false;_1._generated=true;_1.creator=this;var _4=this.creatorName;if(_4)_1[_4]=this;var _5;if(_3==null||_3.ID===_5){_1.ID=this.getID()+isc.$ag+_2;if(window[_1.ID]){_1.ID=_1.ID+isc.$ag+isc.ClassFactory.getNextGlobalID()}}}
,isc.A.getDynamicDefaults=function isc_Class_getDynamicDefaults(){}
,isc.A.$d1=function isc_Class__getDefaultsName(_1){var _2=isc.Class.$d2;if(!_2)isc.Class.$d2=_2={};if(_2[_1])return _2[_1];var _3=_1+this.$dq;if(this[_3])_2[_1]=_3;return _3}
,isc.A.$543=function isc_Class__getPropertiesName(_1){var _2=isc.Class.$544;if(!_2)isc.Class.$544=_2={};if(_2[_1])return _2[_1];var _3=_1+this.$dr;if(this[_3])_2[_1]=_3;return _3}
,isc.A.createAutoChild=function isc_Class_createAutoChild(_1,_2,_3,_4){var _5=this.getDynamicDefaults(_1);if(_5!=null&&_2!=null){_5=isc.addProperties({},_5,_2)}else{_5=_2||_5}
var _6=this.$d1(_1),_7=this[_6],_8=this.$543(_1),_9=this[_8],_10=this.getAutoChildClass(_1,_5,_3,_6,_8),_11=isc.ClassFactory.getClass(_10);if(_11==null){this.logWarn("Unable to create autoChild '"+_1+"' of type '"+_10+"' - no such class in runtime.");return null}
_5=this.applyDuplicateAutoChildDefaults(_11,_6,_5);var _12=_11.createRaw();var _13=this.autoPassthroughs,_14,_15;if(_13){for(var _16 in _13){var _17=_13[_16];if(_1==_17&&this[_16]!==_15){_12[_16]=this[_16]}}}
this.applyBaseDefaults(_12,_1,_2);isc.addProperties(_12,this.autoChildDefaults,_7,_14,_5);if(_4)this[_1]=_12;if(_12.autoConfigure)_12.autoConfigure(this,_1);if(this.configureAutoChild)this.configureAutoChild(_12,_1);isc.addProperties(_12,this[_8]);_12.init();if(!this.$542)this.$542={};var _18=_12.getID?_12.getID():null;if(_18!=null){if(!isc.isAn.Array(this.$542[_1])){if(this.$542[_1]!=null){isc.logWarn(this+".createAutoChild(): Creating auto child named:"+_1+" appears to be replacing autoChild with same name...")}
this.$542[_1]=[_18]}else{this.$542[_1].add(_18)}}
return _12}
,isc.A.applyDuplicateAutoChildDefaults=function isc_Class_applyDuplicateAutoChildDefaults(_1,_2,_3){var _4=_1.$769;if(_4&&_4.length>0){var _5=this[_2];if(_5!=null||this.autoChildDefaults!=null){for(var i=0;i<_4.length;i++){var _7=_4[i],_8;if(_5!=null&&_5[_7]!=null){if(_3==null)_3={};if(_3[_7]===_8){_3[_7]=_1.cloneDupPropertyValue(_7,_5[_7])}}else if(this.autoChildDefaults!=null&&this.autoChildDefaults[_7]!=null)
{if(_3==null)_3={};if(_3[_7]===_8){_3[_7]=_1.cloneDupPropertyValue(_7,this.autoChildDefaults[_7])}}}}}
return _3}
,isc.A.$d3=function isc_Class__completeCreationWithDefaults(_1,_2,_3){this.applyBaseDefaults(_2,_1,_3);var _4=this.$d1(_1),_5=this.$543(_1);var _6=_2.getClass();_3=this.applyDuplicateAutoChildDefaults(_6,_4,_3);_2.completeCreation(this.autoChildDefaults,this[_4],_3,this[_5])}
,isc.A.$d0=function isc_Class__getAutoChildParentName(_1){var _2=this.autoChildParentMap;if(_2)return _2[_1]}
,isc.A.getAutoChildParent=function isc_Class_getAutoChildParent(_1){var _2=this.$d0(_1);if(_2)return this[_2];return this}
,isc.A.setAutoChild=function isc_Class_setAutoChild(_1,_2){if(!this.shouldCreateChild(_1)){if(this[_1])this[_1].destroy();delete this[_1]}else{if(isc.isA.Canvas(_2)){var _3=_2;if(this[_1])this[_1].destroy();this[_1]=_3;this.$dz(_1,_3);return}
return this.addAutoChild(_1,_2)}}
);isc.B._maxIndex=isc.C+54;isc.Class.toString=function(){return"[Class "+this.Class+"]"}
isc.Class.getPrototype().toString=function(){return"["+this.Class+" ID:"+this.ID+"]"}
isc.A=isc.Class;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.ns=isc;isc.A._stringMethodRegistry={};isc.B.push(isc.A.NO_OP=function isc_c_Class_NO_OP(){}
);isc.B._maxIndex=isc.C+1;isc.A=isc.ClassFactory;isc.A.observe=isc.Class.getPrototype().observe;isc.A.ignore=isc.Class.getPrototype().ignore;isc.A.$dk=isc.Class.getPrototype().$dk;isc.A=isc.Class;isc.A.$dk=isc.Class.getPrototype().$dk;isc.eval=function(_1){return isc.Class.evaluate(_1)}
Function.prototype.Class="Function";isc.ClassFactory.defineClass("Func");isc.A=isc.Func;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$d5=new RegExp("function\\s+([\\w$]+)\\s*\\(");isc.B.push(isc.A.getName=function isc_c_Func_getName(_1,_2){if(_1==Function.prototype.apply)return"Function.apply";if(_1==Function.prototype.call)return"Function.call";if(_1.$dx==null){if(_1.$bd==null&&isc._allFuncs){var _3=isc._allFuncs.indexOf(_1);if(_3!=-1){for(var _4=isc._funcClasses[_3];_4==null;_3--){_4=isc._funcClasses[_3]}
_1.$bd=_4}}
var _5=_1.$bc,_6;if(_5==null&&_1.$bd!=null){var _7;var _8=isc.ClassFactory.getClass(_1.$bd);if(_8==null){_8=isc[_1.$bd]||window[_1.$bd]}else{_7=_8.getPrototype()}
if(_7!=null){for(var _9 in _7){if(_7[_9]===_1){_5=_9;break}}}
if(_5==null&&_8!=null){for(var _9 in _8){if(_8[_9]===_1){_5=_9;_6=true;break}}
if(_5==null&&!isc.isA.Class(_8)&&_8.prototype!=null){for(var _9 in _8.prototype){if(_8.prototype[_9]===_1){_5=_9;break}}}}}
if(_5!=null){_1.$dx=(_1.$be?(_1.$bf?"[o]":"[a]"):isc.$ad)+(_6?"[c]":isc.$ad)+(_1.$bd?_1.$bd+isc.dot:isc.$ad)+_5}else{if(_1.$c6)_1.$dx="callback";else{var _10=isc.Func.$d5.exec(_1.toString());if(_10)_1.$dx=_10[1];else _1.$dx="anonymous"}}}
return _1.$dx}
,isc.A.getArgs=function isc_c_Func_getArgs(_1){var _2=isc.Func.getArgString(_1);if(_2=="")return[];return _2.split(",")}
,isc.A.getArgString=function isc_c_Func_getArgString(_1){var _2=_1.toString(),_3=_2.substring(_2.indexOf("(")+1,_2.indexOf(")"));return _3}
,isc.A.getBody=function isc_c_Func_getBody(_1){var _2=_1.toString();return _2.substring(_2.indexOf("{")+1,_2.lastIndexOf("}"))}
,isc.A.getShortBody=function isc_c_Func_getShortBody(_1){var _2=_1.toString();return _2.substring(_2.indexOf("{")+1,_2.lastIndexOf("}")).replace(/[\r\n\t]*/g,"")}
);isc.B._maxIndex=isc.C+5;if(!Function.prototype.apply){isc.A=Function.prototype;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.apply=function(targetObject,args){if(targetObject==null)targetObject=window;var tempFunctionName="__TEMPF_"+Function.prototype.$d6++;var returnValue;targetObject[tempFunctionName]=this;if(!args)args=[];if(args.length<=10){returnValue=targetObject[tempFunctionName](args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8],args[9])}else{var functionString='targetObject[tempFunctionName](';for(var i=0;i<args.length;i++){functionString+="args"+'['+i+']';if(i+1<args.length){functionString+=','}}
functionString+=');';isc.eval('returnValue ='+functionString)}
delete targetObject[tempFunctionName];return returnValue}
);isc.B._maxIndex=isc.C+1;Function.prototype.$d6=0}
isc.A=isc.Func;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$d7=[["//","\n"],["//","\\n"],["/*","*/"]];isc.A.$d8=["\"","\'"];isc.A.$d9=["switch","while","if","return","for","var"];isc.A.$ea=["(",")","[","]","{","}",":","?","!","+","-","/","*","=",">","<","|","&",",","\\"];isc.B.push(isc.A.expressionToFunction=function isc_c_Func_expressionToFunction(_1,_2,_3){var _4=this.$eb(_1,_2,_3);return _4}
,isc.A.$eb=function isc_c_Func__expressionToFunction(_1,_2,_3){if(_2==null){isc.Log.logInfo("makeFunctionExpression() called with empty expression");_2=""}
if(isc.isAn.Object(_2)){if(isc.isA.StringMethod(_2))_2=_2.getValue();else if(_2.Action&&!_2.target)_2=_2.Action;var _4=_1;if(isc.isA.String(_4))_4=_1.split(",");else if(isc.isAn.Array(_4)){_1=_4.join()}
if(!isc.isAn.Array(_4))_4=[];var _5=["if (!window.",,"){var message='Component ID \"",,"\", target of action \"",,"\" does not exist';isc.Log.logWarn(message);if(isc.designTime)isc.say(message)}",,".",,"(",,")"];_5[1]=_5[3]=_5[7]=_2.target;_5[9]=_2.name;if(_2.title)_5[5]=_2.title;else _5[5]="[No title specified]"
var _6=_2.mapping||[];if(!isc.isAn.Array(_6))_6=[];_5[11]=_6.join();var _7=_5.join(isc.emptyString);var _8;try{_8=isc.$aq(_1,_7)}catch(e){this.logWarn("invalid code: "+_7+" generated from action: "+this.echo(_2));_8=new Function()}
_8.iscAction=_2;return _8}
var _9="swirfv";if(isc.isAn.Array(_1)){_1=_1.join()}
var _10=true;var i=0;var _12=this.$d7;var _13=this.$d8;var _14=this.$d9;var _15=this.$ea;var _16=false;var _17=isc.$ad,_18=isc.slash,_19="\n",_20="\\",_21="+",_22=isc.semi;var _23=_17;var _24=_17;while(i<_2.length){var _25=_2.charAt(i);if(_25==_18){for(var j=0;j<_12.length;j++){var _27=_12[j],_28=_27[0],_29=_27[1];if(_2.indexOf(_28,i)==i){var k=i+_28.length;while(k<_2.length){if(_2.substring(k,k+_29.length)==_29){k=k+_29.length;break}
k++}
i=k;_23=_17;_24=this.$ec(_2,i)}}}
if(_16){if(_24==_17){break}else{if(isc.isA.WhitespaceChar(_25)){i++;continue}else{_10=false;break}}}
for(var j=0;j<_13.length;j++){var _31=_13[j]
if(_25==_31){var k=i+1;while(k<_2.length){if(_2.charAt(k)==_20)k=k+2;if(_2.charAt(k)==_31){k++;break}
k++}
i=k;_23=_31.charAt(0);_24=this.$ec(_2,i)}}
if(_25==_19){var _32=false;for(var j=0;j<_15.length;j++){if(_23==_15[j]){_32=true;break}}
if(_32||_24==_21){_23=_17}else{_10=false;break}}
if(_25==_22){_16=true}
if(_9.indexOf(_25)!=-1){for(var j=0;j<_14.length;j++){var _33=_14[j],_34=_33.length;if((i+_34<=_2.length)&&(_2.substring(i,i+_34)==_33)&&(i+_34==_2.length||!isc.isA.AlphaNumericChar(_2.charAt(i+_34)))&&(i==0||!isc.isA.AlphaNumericChar(_2.charAt(i-1)))){_10=false;break}}}
if(!isc.isA.WhitespaceChar(_25))_23=_25;i++;_24=this.$ec(_2,i)}
if(_10){_2="return "+_2}
if(_3)_2="//"+_3+"\r\n"+_2;var _8=isc.$aq(_1,_2);return _8}
,isc.A.$ec=function isc_c_Func__getNextNonWhitespaceChar(_1,_2){var _3=isc.$ad;for(var j=(_2+1);j<_1.length;j++){if(!isc.isA.WhitespaceChar(_1.charAt(j))){_3=_1.charAt(j);break}}
if(j>=_1.length)_3=isc.$ad;return _3}
,isc.A.convertToMethod=function isc_c_Func_convertToMethod(_1,_2){if(!isc.isAn.Object(_1)||!isc.isA.nonemptyString(_2)){isc.Log.logWarn("convertToMethod() called with bad parameters.  Cannot convert "+" property '"+_2+"' on object "+_1+" to a function.  Returning false.");return false}
if(_1[_2]&&isc.isA.Function(_1[_2]))return true;var _3=(isc.isAn.Instance(_1)?_1.getClass()._stringMethodRegistry:_1._stringMethodRegistry);if(_3==null)return false;var _4;var _5=_3[_2];if(_5===_4)return false;isc.Func.replaceWithMethod(_1,_2,_5);return true}
,isc.A.replaceWithMethod=function isc_c_Func_replaceWithMethod(_1,_2,_3,_4){if(_1[_2]==null){_1[_2]=isc.is.emptyString(_3)?isc.Class.NO_OP:new Function(_3,isc.$ad)}
var _5=_1[_2];if(isc.isA.Function(_5))return;var _6;if(isc.isA.String(_5)||isc.isA.Object(_5)){_6=isc.Func.expressionToFunction(_3,_5,_4)}else{isc.Log.logWarn("Property '"+_2+"' on object "+_1+" is of type "+typeof _5+".  This can not be converted to a method.","Function");return}
var _7={};_7[_2]=_6;isc.addMethods(_1,_7)}
);isc.B._maxIndex=isc.C+5;Array.prototype.Class="Array";Array.newInstance=function(){var _1=[];isc.addPropertyList(_1,arguments);return _1}
Array.create=Array.newInstance;Array.LOADING="loading";Array.isLoading=function(_1){return _1!=null&&!isc.isAn.XMLNode(_1)&&(_1===Array.LOADING)}
Array.CASE_INSENSITIVE=function(_1,_2,_3){if(isc.isA.String(_1)&&isc.isA.String(_2)&&_1.toLowerCase()==_2.toLowerCase()){return true}else{return _1==_2}}
Array.DATE_VALUES=function(_1,_2,_3){if(isc.isA.Date(_1)&&isc.isA.Date(_2)&&Date.compareLogicalDates(_1,_2)==0){return true}else{return _1==_2}}
Array.DATETIME_VALUES=function(_1,_2,_3){if(isc.isA.Date(_1)&&isc.isA.Date(_2)&&Date.compareDates(_1,_2)==0){return true}else{return _1==_2}}
if(!Array.prototype.localeStringFormatter)
Array.prototype.localeStringFormatter="toString";isc.A=Array.prototype;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.newInstance=Array.newInstance;isc.A.create=Array.newInstance;isc.A.slice=(Array.prototype.slice?Array.prototype.slice:function(_1,_2){if(_2==null)_2=this.length;for(var _3=[],l=this.length;_1<_2&&_1<l;_1++)
_3[_3.length]=this[_1];return _3});isc.A.observe=isc.Class.getPrototype().observe;isc.A.ignore=isc.Class.getPrototype().ignore;isc.A.$dk=isc.Class.getPrototype().$dk;isc.B.push(isc.A.iscToLocaleString=function isc_Arra_iscToLocaleString(){return this[this.localeStringFormatter]()}
,isc.A.getPrototype=function isc_Arra_getPrototype(){return Array.prototype}
,isc.A.get=function isc_Arra_get(_1){return this[_1]}
,isc.A.getLength=function isc_Arra_getLength(){return this.length}
,isc.A.isEmpty=function isc_Arra_isEmpty(){return this.getLength()==0}
,isc.A.first=function isc_Arra_first(){return this[0]}
,isc.A.last=function isc_Arra_last(){return this[this.length-1]}
,isc.A.indexOf=function isc_Arra_indexOf(_1,_2,_3){if(_2==null)_2=0;if(_3==null)_3=this.length-1;for(var i=_2;i<=_3;i++)
if(this[i]==_1)return i;return-1}
,isc.A.lastIndexOf=function isc_Arra_lastIndexOf(_1,_2,_3){if(_2==null)_2=this.length-1;if(_3==null)_3=0;for(var i=_2;i>=_3;i--)
if(this[i]==_1)return i;return-1}
,isc.A.contains=function isc_Arra_contains(_1,_2){return(this.indexOf(_1,_2)!=-1)}
,isc.A.containsAll=function isc_Arra_containsAll(_1){if(_1==null)return true;var _2=_1.getLength();for(var i=0;i<_2;i++){if(!this.contains(_1.get(i)))return false}
return true}
,isc.A.intersect=function isc_Arra_intersect(){var _1=[];for(var i=0;i<this.length;i++){var _3=this.get(i),_4=true;if(_3==null)continue;for(var a=0;a<arguments.length;a++){if(!arguments[a].contains(_3)){_4=false;break}}
if(_4)_1.add(_3)}
return _1}
,isc.A.equals=function isc_Arra_equals(_1){if(_1==null||!isc.isA.List(_1))return false;var _2=_1.getLength();if(_2!=this.getLength())return false;for(var i=0;i<_2;i++){if(_1.get(i)!=this.get(i))return false}
return true}
,isc.A.getItems=function isc_Arra_getItems(_1){var _2=[],_3=_1.getLength();for(var i=0;i<_3;i++){_2[i]=this.get(_1.get(i))}
return _2}
,isc.A.getRange=function isc_Arra_getRange(_1,_2){if(_2==null)_2=this.length-1;return this.slice(_1,_2)}
,isc.A.duplicate=function isc_Arra_duplicate(){return isc.$ac.concat(this)}
,isc.A.set=function isc_Arra_set(_1,_2){this[_1]=_2;this.dataChanged()}
,isc.A.addAt=function isc_Arra_addAt(_1,_2){if(_2==null)_2=0;for(var i=this.length-1;i>=_2;i--){this[i+1]=this[i]}
this[_2]=_1;this.dataChanged();return _1}
,isc.A.removeAt=function isc_Arra_removeAt(_1){var _2=this.length;if(_1>=_2||_1<0)return null;var _3=this[_1];for(;_1<_2-1;_1++)
this[_1]=this[_1+1];this.length--;this.dataChanged();return _3}
,isc.A.add=function isc_Arra_add(_1,_2){var _3;if(_2!==_3){return this.addAt(_1,_2)}
var _4;if(this.sortUnique){_4=this.indexOf(_1);if(_4==-1)_4=this.length}else{_4=this.length}
this[_4]=_1;if(this.sortProps&&this.sortProps.length>0){this.sortByProperties(this.sortProps,this.sortDirections,this.sortNormalizers)}
this.dataChanged();return _1}
,isc.A.addList=function isc_Arra_addList(_1,_2,_3){if(_1==null)return null;this.$ed();if(_2==null)_2=0;if(_3==null)_3=_1.getLength();for(var _4=_2;_4<_3;_4++){this.add(_1.get(_4))}
this.$ee();return _1}
,isc.A.setLength=function isc_Arra_setLength(_1){this.length=_1}
,isc.A.addListAt=function isc_Arra_addListAt(_1,_2){if(_1==null)return null;for(var i=this.length-1,l=_1.length;i>=_2;i--){this[i+l]=this[i]}
for(i=0;i<l;i++){this[i+_2]=_1[i]}
this.dataChanged();return _1}
,isc.A.remove=function isc_Arra_remove(_1){var _2=this.indexOf(_1);if(_2==-1)return false;for(var i=_2;i<this.length;i++)this[i]=this[i+1];this.length=this.length-1;this.dataChanged();return true}
,isc.A.removeList=function isc_Arra_removeList(_1){if(_1==null)return null;for(var _2=[],i=0,l=this.length;i<l;i++){if(!_1.contains(this[i]))_2.add(this[i])}
this.setArray(_2);return _1}
,isc.A.removeEvery=function isc_Arra_removeEvery(_1){this.removeList([_1]);return this}
,isc.A.$ed=function isc_Arra__startChangingData(){var _1;if(this.$ef===_1)this.$ef=0;this.$ef++}
,isc.A.$ee=function isc_Arra__doneChangingData(){if(--this.$ef==0)this.dataChanged()}
,isc.A.dataChanged=function isc_Arra_dataChanged(){if(this.onDataChanged)this.onDataChanged()}
,isc.A.$52z=function isc_Arra__isChangingData(){return(this.$ef!=null&&this.$ef>0)}
,isc.A.setArray=function isc_Arra_setArray(_1){this.setLength(_1.length);for(var i=0;i<_1.length;i++)this[i]=_1[i];this.dataChanged()}
,isc.A.addAsList=function isc_Arra_addAsList(_1){if(!isc.isAn.Array(_1))_1=[_1];return this.addList(_1)}
,isc.A.removeRange=function isc_Arra_removeRange(_1,_2){var _3;if(_1===_3)return this;if(!isc.isA.Number(_1))_1=0;if(!isc.isA.Number(_2))_2=this.length;return this.splice(_1,_2-_1)}
,isc.A.removeWhere=function isc_Arra_removeWhere(_1,_2){for(var i=0,_4=[];i<this.length;i++){if(!this[i]||this[i][_1]!=_2){_4.add(this[i])}}
this.setArray(_4)}
,isc.A.removeUnless=function isc_Arra_removeUnless(_1,_2){for(var i=0,_4=[];i<this.length;i++){if(this[i]&&this[i][_1]==_2){_4.add(this[i])}}
this.setArray(_4)}
,isc.A.removeEmpty=function isc_Arra_removeEmpty(_1,_2){for(var i=0,_4=[];i<this.length;i++){if(this[i]!=null){_4.add(this[i])}}
this.setArray(_4)}
,isc.A.getProperty=function isc_Arra_getProperty(_1){for(var _2=[],i=0,l=this.length;i<l;i++)
_2[_2.length]=(this[i]?this[i][_1]:null);return _2}
,isc.A.getValueMap=function isc_Arra_getValueMap(_1,_2){var _3={};for(var i=0,l=this.getLength();i<l;i++){var _6=this.get(i);if(!isc.isAn.Object(_6))continue;if(_6&&_6[_1]!=null){_3[_6[_1]]=_6[_2]}}
return _3}
,isc.A.map=function isc_Arra_map(_1,_2,_3,_4,_5,_6){var _7=isc.isA.Function(_1),_8=[],_9=this.getLength();var _10,_11=_7&&(_2===_10||isc.isAn.Object(_2))&&_3===_10&&_4===_10&&_5===_10&&_6===_10;for(var i=0;i<_9;i++){var _13=this.get(i);if(_11){if(_2==null)_8[i]=_1(_13,i,this);else{_2.$838=_1;_8[i]=_2.$838(_13,i,this);delete _2.$838}}else if(_7){_8[i]=_1(_13,_2,_3,_4,_5,_6)}else{_8[i]=(_13&&_13[_1]!=null?_13[_1](_2,_3,_4,_5,_6):null)}}
return _8}
,isc.A.setProperty=function isc_Arra_setProperty(_1,_2){for(var i=0,l=this.length;i<l;i++)
if(this[i])this[i][_1]=_2}
,isc.A.clearProperty=function isc_Arra_clearProperty(_1){var _2=false,_3;for(var i=0,l=this.length;i<l;i++){_2=_2||this[i]!==_3;if(this[i])delete this[i][_1]}
return _2}
,isc.A.getProperties=function isc_Arra_getProperties(_1){return isc.applyMask(this,_1)}
,isc.A.getUniqueItems=function isc_Arra_getUniqueItems(){for(var _1=[],i=0,l=this.length;i<l;i++){if(!_1.contains(this[i]))_1[_1.length]=this[i]}
return _1}
,isc.A.findIndex=function isc_Arra_findIndex(_1,_2,_3){return this.findNextIndex(0,_1,_2,null,_3)}
,isc.A.findNextIndex=function isc_Arra_findNextIndex(_1,_2,_3,_4,_5){if(_1==null)_1=0;else if(_1>=this.length)return-1;if(_4==null)_4=this.length-1;if(_2==null)return-1;if(isc.isA.String(_2)){if(_5){for(var i=_1;i<=_4;i++){if(this[i]&&_5(this[i][_2],_3,_2))return i}}else{for(var i=_1;i<=_4;i++){if(this[i]&&this[i][_2]==_3)return i}}
return-1}else if(isc.isA.Function(_2)){for(var i=_1;i<=_4;i++){if(_2(this[i]))return i}
return-1}else{return this.findNextMatch(_2,_1,_4,_5)}}
,isc.A.findAllIndices=function isc_Arra_findAllIndices(_1,_2,_3){var _4=[];var _5=0;var _6;do{_6=this.findNextIndex(_5,_1,_2,null,_3);if(_6!=-1){_4.add(_6);_5=_6+1}}while(_6!=-1);return _4}
,isc.A.findNextMatch=function isc_Arra_findNextMatch(_1,_2,_3,_4){var _5=isc.getKeys(_1);if(_4){for(var i=_2;i<=_3;i++){var _7=this.get(i);if(!_7)continue;var _8=true;for(var j=0;j<_5.length;j++){var _10=_5[j];if(!_4(_7[_10],_1[_10],_10)){_8=false;break}}
if(_8)return i}}else{for(var i=_2;i<=_3;i++){var _7=this.get(i);if(!_7)continue;var _8=true;for(var j=0;j<_5.length;j++){var _10=_5[j];if(_7[_10]!=_1[_10]){_8=false;break}}
if(_8)return i}}
return-1}
,isc.A.find=function isc_Arra_find(_1,_2,_3){var _4=this.findIndex(_1,_2,_3);return(_4!=-1)?this.get(_4):null}
,isc.A.findByKeys=function isc_Arra_findByKeys(_1,_2,_3,_4){if(_1==null){isc.Log.logWarn("findByKeys: passed null record");return-1}
var _5={},_6=_2.getPrimaryKeyFields(),_7=false;for(var _8 in _6){_7=true;if(_1[_8]==null){isc.Log.logWarn("findByKeys: passed record does not have a value for key field '"+_8+"'");return-1}
_5[_8]=_1[_8]}
if(!_7){isc.Log.logWarn("findByKeys: dataSource '"+_2.ID+"' does not have primary "+"keys declared, can't find record");return-1}
return this.findNextIndex(_3,_5,null,_4)}
,isc.A.containsProperty=function isc_Arra_containsProperty(_1,_2){var _3=this.findIndex(_1,_2);return(_3!=-1)}
,isc.A.findAll=function isc_Arra_findAll(_1,_2){if(_1==null)return null;if(isc.isA.String(_1)){var _3=null,l=this.length;var _5=isc.isAn.Array(_2);for(var i=0;i<l;i++){var _7=this[i];if(_7&&(_5?_2.contains(_7[_1]):_7[_1]==_2)){if(_3==null)_3=[];_3.add(_7)}}
return _3}else if(isc.isA.Function(_1)){var _3=null,l=this.length,_8=_1,_9=_2;for(var i=0;i<l;i++){var _7=this[i];if(_8(_7,_9)){if(_3==null)_3=[];_3.add(_7)}}
return _3}else{return this.findAllMatches(_1)}}
,isc.A.findAllMatches=function isc_Arra_findAllMatches(_1){var l=this.getLength(),_3=isc.getKeys(_1),_4=null;for(var i=0;i<l;i++){var _6=this.get(i);if(!_6)continue;var _7=true;for(var j=0;j<_3.length;j++){var _9=_3[j];if(_6[_9]!=_1[_9]){_7=false;break}}
if(_7){if(_4==null)_4=[];_4.add(_6)}}
return _4}
,isc.A.slide=function isc_Arra_slide(_1,_2){this.slideRange(_1,_1+1,_2)}
,isc.A.slideRange=function isc_Arra_slideRange(_1,_2,_3){var _4=this.splice(_1,_2-_1);this.addListAt(_4,_3)}
,isc.A.slideList=function isc_Arra_slideList(_1,_2){var _3=[],i;if(_2<0)_2=0;for(i=0;i<_2;i++)
if(!_1.contains(this[i]))
_3.add(this[i]);for(i=0;i<_1.length;i++)
_3.add(_1[i]);for(i=_2;i<this.length;i++)
if(!_1.contains(this[i]))
_3.add(this[i]);this.setArray(_3)}
,isc.A.makeIndex=function isc_Arra_makeIndex(_1,_2,_3){var _4={};var _5=(_2==-1);_2=(_2!=null&&_2!=0);for(var i=0;i<this.length;i++){var _7=this[i],_8=_7[_1];if(_8==null){if(!_3)continue;_8=i}
if(_5){_4[_8]=_7;continue}
var _9=_4[_8];if(_9==null){if(_2){_4[_8]=[_7]}else{_4[_8]=_7}}else{if(_2){_4[_8].add(_7)}else{if(isc.isAn.Array(_9)){_4[_8].add(_7)}else{_4[_8]=[_9,_7]}}}}
return _4}
,isc.A.arraysToObjects=function isc_Arra_arraysToObjects(_1){var _2=_1.length;for(var _3=[],i=0,l=this.length;i<l;i++){var _6=_3[i]={};for(var p=0;p<_2;p++){var _8=_1[p];_6[_8]=this[i][p]}}
return _3}
,isc.A.objectsToArrays=function isc_Arra_objectsToArrays(_1){var _2=_1.length;for(var _3=[],i=0,l=this.length;i<l;i++){var _6=_3[i]=[];for(var p=0;p<_2;p++){var _8=_1[p];_6[p]=this[i][_8]}}
return _3}
,isc.A.spliceArray=function isc_Arra_spliceArray(_1,_2,_3){var _4;if(_1===_4)return this.splice();if(_2===_4)return this.splice(_1);if(_3===_4)return this.splice(_1,_2);if(!isc.isAn.Array(_3)){isc.Log.logWarn("spliceArray() method passed a non-array third parameter. Ignoring...","Array");return this.splice(_1,_2)}
return this.splice.apply(this,[_1,_2].concat(_3))}
,isc.A.peek=function isc_Arra_peek(){var _1=this.pop();this.push(_1);return _1}
,isc.A.removeItem=function isc_Arra_removeItem(_1){return this.removeAt(_1)}
,isc.A.getItem=function isc_Arra_getItem(_1){return this.get(_1)}
,isc.A.setItem=function isc_Arra_setItem(_1){return this.set(_1)}
,isc.A.clearAll=function isc_Arra_clearAll(_1){return this.removeList(this)}
,isc.A.size=function isc_Arra_size(){return this.getLength()}
,isc.A.subList=function isc_Arra_subList(_1,_2){return this.getRange(_1,_2)}
,isc.A.addAll=function isc_Arra_addAll(_1){return this.addList(_1)}
,isc.A.removeAll=function isc_Arra_removeAll(_1){var _2=this.getLength();this.removeList(_1);return this.getLength()!=_2}
,isc.A.clear=function isc_Arra_clear(){this.setLength(0)}
,isc.A.toArray=function isc_Arra_toArray(){return this.duplicate()}
);isc.B._maxIndex=isc.C+70;Number.prototype.Class="Number";isc.A=Number.prototype;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.stringify=function isc_Numbe_stringify(_1,_2){if(!_1)_1=2;var _3=this.toString(),_4=_1-_3.length;if(_2){var _5=_3.indexOf(isc.dot);if(_5!=-1){_4+=(_3.length-_5)}}
var _6=Number.$eg(_4);if(_6==null)return _3;return _6+_3}
,isc.A.toCurrencyString=function isc_Numbe_toCurrencyString(_1,_2,_3,_4){var _5=this<0?Math.ceil(this):Math.floor(this),_6=Math.abs(Math.round((this-_5)*100)),_7=isc.StringBuffer.create();if(!isc.isA.String(_1))_1="$";if(!isc.isA.nonemptyString(_2))_2=".";if(_3==null)_3=true;if(_4!=true)_7.append(_1);_7.append(_5.stringify(1));if(_3){_7.append(_2);_7.append(_6.stringify(2))}else if(_6!=0){_7.append(_2);if(_6%10==0)_7.append(_6/ 10);else _7.append(_6.stringify(2))}
if(_4==true)_7.append(_1);return _7.toString()}
);isc.B._maxIndex=isc.C+2;isc.A=Number;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A._1zero="0";isc.A._2zero="00";isc.A._3zero="000";isc.A._4zero="0000";isc.B.push(isc.A.setStandardFormatter=function isc_Number_setStandardFormatter(_1){if(isc.isA.Function(Number.prototype[_1]))
Number.prototype.formatter=_1}
,isc.A.setStandardLocaleStringFormatter=function isc_Number_setStandardLocaleStringFormatter(_1){if(isc.isA.Function(Number.prototype[_1]))
Number.prototype.localeStringFormatter=_1}
,isc.A.$eg=function isc_Number__getZeroString(_1){if(_1<=0)return;var _2;while(_1>4){if(_2==null)_2=this._4zero;else _2+=this._4zero;_1-=4}
var _3;switch(_1){case 4:_3=this._4zero;break;case 3:_3=this._3zero;break;case 2:_3=this._2zero;break;case 1:_3=this._1zero;break}
if(_2==null)return _3;return _2+_3}
);isc.B._maxIndex=isc.C+3;if(!Number.prototype.formatter)Number.prototype.formatter="toString";if(!Number.prototype.localeStringFormatter)
Number.prototype.localeStringFormatter="toString";isc.A=Number.prototype;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.localeProperties={decimalSymbol:".",groupingSymbol:",",negativeSymbol:"-",currencySymbol:"$",negativeFormat:1,groupingFormat:1};isc.A.$eh=".";isc.B.push(isc.A.iscToLocaleString=function isc_Numbe_iscToLocaleString(){return this[this.localeStringFormatter]()}
,isc.A.toFormattedString=function isc_Numbe_toFormattedString(_1){return this[(_1?_1:this.formatter)]()}
,isc.A.toLocalizedString=function isc_Numbe_toLocalizedString(_1,_2,_3,_4){var _5=!_1?this:Math.round(this*Math.pow(10,_1))/Math.pow(10,_1);var _6=Math.abs(_5),_7=Math.floor(_6),_8,_9,_10=[];if(_1){var _11=Math.round((_6-_7)*Math.pow(10,_1));_9=_11.stringify(_1)}else if(_1==0){_7=Math.round(_6)}else{if(_6-_7>0){var _12=_6.toString();_9=_12.substring(_12.indexOf(this.$eh)+1)}}
_8=_7.toString();var _13=_8.length;var _14=Math.floor(_13/ 3);if(_13%3){_10[0]=_8.substr(0,_13%3)}
for(var i=0;i<_14;i++){_10[_10.length]=_8.substr(_13%3+i*3,3)}
var _16=_10.join(_3||this.localeProperties.groupingSymbol);if(_9)_16=_16+(_2||this.localeProperties.decimalSymbol)+_9;if(_5<0)_16=(_4||this.localeProperties.negativeSymbol)+_16;return _16}
,isc.A.toUSString=function isc_Numbe_toUSString(_1){return this.toLocalizedString(_1)}
,isc.A.toUSDollarString=function isc_Numbe_toUSDollarString(_1){return this.localeProperties.currencySymbol+this.toLocalizedString(_1)}
);isc.B._maxIndex=isc.C+5;isc.defineClass("Format");isc.A=isc.Format;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.toUSString=function isc_c_Format_toUSString(_1,_2){if(!isc.isA.Number(_1))return _1;return _1.toUSString(_2)}
,isc.A.toUSDollarString=function isc_c_Format_toUSDollarString(_1,_2){if(!isc.isA.Number(_1))return _1;return _1.toUSDollarString(_2)}
,isc.A.toCurrencyString=function isc_c_Format_toCurrencyString(_1,_2,_3,_4,_5){if(!isc.isA.Number(_1))return _1;return _1.toCurrencyString(_2,_3,_4,_5)}
);isc.B._maxIndex=isc.C+3;isc.Math={random:function(_1,_2){if(_2==null){return Math.round(Math.random()*_1)}else{return Math.round(Math.random()*(_2-_1))+_1}}}
isc.defineClass("DateUtil");isc.addGlobal("timeStamp",function(){return new Date().getTime()});isc.addGlobal("timestamp",isc.timeStamp);Date.prototype.Class="Date";Date.Class="Date";isc.Date=Date;isc.A=Date;isc.A.INVALID_DATE_STRING="Invalid date format";isc.A=Date;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$64g={toUSShortDate:"MDY",toUSShortDateTime:"MDY",toUSShortDatetime:"MDY",toEuropeanShortDate:"DMY",toEuropeanShortDateTime:"DMY",toEuropeanShortDatetime:"DMY",toJapanShortDate:"YMD",toJapanShortDateTime:"YMD",toJapanShortDatetime:"YMD"};isc.B.push(isc.A.newInstance=function isc_Date_newInstance(_1,_2,_3,_4,_5,_6,_7){return new Date(_1,_2,_3,_4,_5,_6,_7)}
,isc.A.create=function isc_Date_create(_1,_2,_3,_4,_5,_6,_7){var _8;if(_1===_8)return new Date();if(_2===_8)return new Date(_1);if(_3===_8)_3=0;if(_4===_8)_4=0;if(_5===_8)_5=0;if(_6===_8)_6=0;if(_7===_8)_7=0;return new Date(_1,_2,_3,_4,_5,_6,_7)}
,isc.A.createLogicalDate=function isc_Date_createLogicalDate(_1,_2,_3,_4){var d=new Date();d.setHours(12);d.setMinutes(0);d.setSeconds(0);d.setMilliseconds(0);if(_3!=null)d.setDate(1);if(_1!=null)d.setYear(_1);if(_2!=null)d.setMonth(_2);if(_3!=null)d.setDate(_3);if(_4){var _6=(d.getFullYear()==_1&&d.getMonth()==_2&&d.getDate()==_3);if(!_6)return null}
d.logicalDate=true;return d}
,isc.A.createLogicalTime=function isc_Date_createLogicalTime(_1,_2,_3,_4){return isc.Time.createLogicalTime(_1,_2,_3,_4)}
,isc.A.createDatetime=function isc_Date_createDatetime(_1,_2,_3,_4,_5,_6,_7,_8){var _9=_4!=null,_10=_5!=null,_11=_6!=null;if(isc.isA.String(_4))_4=parseInt(_4||12,10);if(isc.isA.String(_5))_5=parseInt(_5||0,10);if(isc.isA.String(_6))_6=parseInt(_6||0,10);var _12;if(!isc.Time.$854){_12=new Date(_1,_2,_3);if(_9){if(_4!=null)_12.setHours(_4);if(_5!=null)_12.setMinutes(_5);if(_6!=null)_12.setSeconds(_6);if(_7!=null)_12.setMilliseconds(_7)}
if(!_8)return _12;var _13=(_12.getFullYear()==_1&&_12.getMonth()==_2&&_12.getDate()==_3&&(!_9||_12.getHours()==_4)&&(!_10||_12.getMinutes()==_5)&&(!_11||_12.getSeconds()==_6));return(_13?_12:null)}else{if(_4==null)_4=0;if(_5==null)_5=0;if(_6==null)_6=0;if(_7==null)_7=0;_12=new Date(Date.UTC(_1,_2,_3,_4,_5,_6,_7));if(_8){var _13=(_12.getUTCFullYear()==_1&&_12.getUTCMonth()==_2&&_12.getUTCDate()==_3&&(!_9||_12.getUTCHours()==_4)&&(!_10||_12.getUTCMinutes()==_5)&&(!_11||_12.getUTCSeconds()==_6));if(!_13)_12=null}
if(_12!=null){_12.$68d(-isc.Time.getUTCHoursDisplayOffset(_12),-isc.Time.getUTCMinutesDisplayOffset(_12))}
return _12}}
,isc.A.compareDates=function isc_Date_compareDates(_1,_2){if(_1==_2)return 0;var _3=(isc.isA.Date(_1)?_1.getTime():0),_4=(isc.isA.Date(_2)?_2.getTime():0);return _3>_4?-1:(_4>_3?1:0)}
,isc.A.compareLogicalDates=function isc_Date_compareLogicalDates(_1,_2){if(_1==_2)return 0;if(!isc.isA.Date(_1)||!isc.isA.Date(_2))return false;var _3=_1.getFullYear(),_4=_1.getMonth(),_5=_1.getDate(),_6=_2.getFullYear(),_7=_2.getMonth(),_8=_2.getDate();var _9=_3*10000+_4*100+_5,_10=_6*10000+_7*100+_8;return _9>_10?-1:(_10>_9?1:0)}
,isc.A.setInputFormat=function isc_Date_setInputFormat(_1){this.$ei=_1}
,isc.A.getInputFormat=function isc_Date_getInputFormat(){if(this.$ei!=null)return this.$ei;return this.mapDisplayFormatToInputFormat("toShortDate")}
,isc.A.mapDisplayFormatToInputFormat=function isc_Date_mapDisplayFormatToInputFormat(_1){if(_1==null||_1=="toShortDate"){_1=Date.prototype.$el}else if(_1=="toNormalDate"){_1=Date.prototype.formatter}
if(isc.isA.Function(_1)){isc.Log.logInfo("Unable to determine input format associated with display format "+"function - returning default input format","Date");return this.$ei||"MDY"}
var _2=this.$64g[_1];if(_2!=null&&isc.isA.String(_2))return _2;if(_1=="toSerializeableDate")return this.parseSchemaDate;isc.Log.logInfo("Unable to determine input format associated with display format "+_1+" - returning default input format","Date");return this.$ei||"MDY"}
,isc.A.parseInput=function isc_Date_parseInput(_1,_2,_3,_4,_5){var _6=(_5==false);if(isc.isA.Date(_1))return _1;if(!isc.isA.String(_1)||isc.isAn.emptyString(_1)){return null}
if(_2==null)_2=this.getInputFormat();if(isc.isA.Function(Date[_2]))_2=Date[_2];if(isc.isA.Function(_2)){return _2(_1,_3,_4)}
var _7=this.$ej(_1,_2);if(_7!=null){var _8=_7[0],_9=_8&&_8.contains("-");if(_8&&_9)_8=_8.replaceAll("-","");if(_8){if(_8.length<=2){_8=parseInt(_8,10);if(_3!=null){if(_8<_3)_8+=2000;else _8+=1900}
_7[0]=_8}else if(_8.length==3){_7[0]="0"+_8.toString()}else{_7[0]=_8}
if(_9)_7[0]="-"+_7[0]}
if(_6){return Date.createLogicalDate(_7[0],_7[1],_7[2],_4)}else{return Date.createDatetime(_7[0],_7[1],_7[2],_7[3],_7[4],_7[5],null,_4)}}else{return null}}
,isc.A.parseSchemaDate=function isc_Date_parseSchemaDate(_1){if(isc.isA.Date(_1))return _1;if(!isc.isA.String(_1))_1=(_1.toString?_1.toString():_1+"");var _2=_1.match(/(\d{4})[\/-](\d{2})[\/-](\d{2})([T ](\d{2}):(\d{2}):(\d{2}))?(\.(\d+))?([+-]\d{2}:\d{2}|Z)?/);if(_2==null)return null;var _3;if(!_2[4]){_3=Date.createLogicalDate(_2[1],_2[2]-1,_2[3])}else if(!_2[9]){_3=new Date(Date.UTC(_2[1],_2[2]-1,_2[3],_2[5],_2[6],_2[7]))}else{var _4=_2[9];if(_4.length!=3){var _5=Math.pow(10,3-_4.length);_4=Math.round(parseInt(_4,10)*_5)}
_3=new Date(Date.UTC(_2[1],_2[2]-1,_2[3],_2[5],_2[6],_2[7],_4))}
if(_2[10]&&_2[10].toLowerCase()!="z"){var _6=_2[10].split(":"),H=_6[0],_8=H&&H.startsWith("-"),M=_6[1];H=parseInt(H,10);M=parseInt(M,10);var _10=_3.getTime();if(isc.isA.Number(H))_10-=(3600000*H);if(isc.isA.Number(M))_10-=(60000*M*(_8?-1:1));_3.setTime(_10)}
return _3}
,isc.A.parseDate=function isc_Date_parseDate(_1,_2,_3,_4){return this.parseInput(_1,_2,_3,_4)}
,isc.A.parseDateTime=function isc_Date_parseDateTime(_1,_2,_3,_4){return this.parseDatetime(_1,_2,_3,_4)}
,isc.A.parseDatetime=function isc_Date_parseDatetime(_1,_2,_3,_4){return this.parseInput(_1,_2,_3,_4)}
,isc.A.parseServerDate=function isc_Date_parseServerDate(_1,_2,_3){return Date.createLogicalDate(_1,_2,_3)}
,isc.A.parseServerTime=function isc_Date_parseServerTime(_1,_2,_3){return Date.createLogicalTime(_1,_2,_3)}
,isc.A.$ej=function isc_Date__splitDateString(_1,_2){var _3,_4,_5,_6,_7,_8;var _9=_2?_2.indexOf("M"):0,_10=_2?_2.indexOf("D"):1,_11=_2?_2.indexOf("Y"):2;if(isc.Browser.isSafari&&isc.Browser.safariVersion<=312){var _12=this.$ek(_1,_9,_10,_11);_5=_12[0];_3=_12[1];_4=_12[2];_6=_12[3];_7=_12[4];_8=_12[5]}else{var _13=new RegExp(/^\s*(-?\d{1,4})[^\d](-?\d{1,4})[^\d](-?\d{1,4})([^\d](\d{1,2})[^\d](\d\d)[^\d]?(\d\d)?)?\s*$/),_14=_1.match(_13);if(_14==null)return null;_3=_14[_9+1]-1;_4=_14[_10+1];_5=_14[_11+1];_6=_14[5]||0;_7=_14[6]||0;_8=_14[7]||0}
if(isc.isA.Number(_5-_3-_4-_6-_7-_8))
return([_5,_3,_4,_6,_7,_8]);else return null}
,isc.A.setNormalDisplayFormat=function isc_Date_setNormalDisplayFormat(_1){if(isc.isA.Function(Date.prototype[_1])||isc.isA.Function(_1)){Date.prototype.formatter=_1}}
,isc.A.setShortDisplayFormat=function isc_Date_setShortDisplayFormat(_1){if(isc.isA.Function(Date.prototype[_1])||isc.isA.Function(_1)){Date.prototype.$el=_1}}
,isc.A.setDefaultDateSeparator=function isc_Date_setDefaultDateSeparator(_1){Date.prototype.$em=[,,,,_1,,,,,_1,,,,null];Date.prototype.$665=_1}
,isc.A.getDefaultDateSeparator=function isc_Date_getDefaultDateSeparator(_1){if(Date.prototype.$665)return Date.prototype.$665;else return"/"}
,isc.A.setShortDatetimeDisplayFormat=function isc_Date_setShortDatetimeDisplayFormat(_1){if(isc.isA.Function(Date.prototype[_1])||isc.isA.Function(_1)){Date.prototype.$68e=_1}}
,isc.A.setFormatter=function isc_Date_setFormatter(_1){Date.setNormalDisplayFormat(_1)}
,isc.A.setLocaleStringFormatter=function isc_Date_setLocaleStringFormatter(_1){if(isc.isA.Function(Date.prototype[_1])||isc.isA.Function(_1))
Date.prototype.localeStringFormatter=_1}
,isc.A.getShortMonthNames=function isc_Date_getShortMonthNames(_1){_1=_1||3;var _2=Date.shortMonthNames;if(_2==null)_2=Date.$41t;if(_2==null){var _3=Date.$41t=[];for(var i=0;i<12;i++){var _5=Date.createLogicalDate(2000,i,2);_3[i]=_5.deriveShortMonthName()}
_2=Date.$41t}
var _6=[];for(var i=0;i<12;i++){_6[i]=_2[i].substring(0,_1)}
return _6}
,isc.A.getShortDayNames=function isc_Date_getShortDayNames(_1){_1=_1||3;var _2=Date.shortDayNames;if(_2==null)_2=Date.$41s;if(_2==null){Date.$41s=[];var _3=new Date();_3.setDate(1);if(_3.getDay()>0)_3.setDate(_3.getDate()+(7-_3.getDay()));var _4=_3.getDate();for(var i=0;i<7;i++){_3.setDate(_4+i);Date.$41s[i]=_3.deriveShortDayName()}
_2=Date.$41s}
var _6=[];for(var i=0;i<7;i++){_6[i]=_2[i].substring(0,_1)}
return _6}
,isc.A.getWeekendDays=function isc_Date_getWeekendDays(){var _1=Date.weekendDays;if(_1==null)_1=Date.$54i;if(_1==null){_1=Date.$54i=[0,6]}
return _1}
,isc.A.getFormattedDateRangeString=function isc_Date_getFormattedDateRangeString(_1,_2){if(_1!=null&&!isc.isA.Date(_1)){_1=null}
if(_2!=null&&!isc.isA.Date(_2)){_2=null}
var _3=_1?_1.getMonth():null,_4=_1?_1.getShortMonthName():null,_5=_1?_1.getFullYear():null,_6=_1?_1.getDate():null,_7=_2?_2.getMonth():null,_8=_2?_2.getShortMonthName():null,_9=_2?_2.getFullYear():null,_10=_2?_2.getDate():null,_11="";if(_1&&_2){if(_5==_9){if(_3==_7){if(_6==_10){_11=_4+" "+_1.getDate()+", "+_5}else{_11=_4+" "+_1.getDate()+" - "+_2.getDate()+", "+_5}}else{_11=_4+" "+_1.getDate()+" - "+_8+" "+_2.getDate()+", "+_5}}else{_11=_4+" "+_1.getDate()+", "+_5+" - "+_8+" "+_2.getDate()+", "+_9}}else if(_1){_11=_4+" "+_1.getDate()+", "+_5}else if(_2){_11=_8+" "+_2.getDate()+", "+_9}
return _11}
);isc.B._maxIndex=isc.C+29;isc.A=Date.prototype;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$em=[,,,,"/",,,,,"/",,,,null];isc.A.$en="MDY";isc.A.$eo="DMY";isc.A.$ep="YMD";isc.A.$en="MDY";isc.A.$w0="0";isc.A.$68f=[null,null];isc.B.push(isc.A.duplicate=function isc_Dat_duplicate(){var _1=new Date();_1.setTime(this.getTime());_1.logicalDate=this.logicalDate;_1.logicalTime=this.logicalTime;return _1}
,isc.A.clearTimeFields=function isc_Dat_clearTimeFields(){this.setHours(0);this.setMinutes(0);this.setSeconds(0);this.setMilliseconds(0);return this}
,isc.A.deriveShortDayName=function isc_Dat_deriveShortDayName(_1){var _2=this.toString();if(_1==null||_1<=0||_1>3)_1=3;return _2.substring(0,_1)}
,isc.A.getShortDayName=function isc_Dat_getShortDayName(){return Date.getShortDayNames()[this.getDay()]}
,isc.A.deriveShortMonthName=function isc_Dat_deriveShortMonthName(_1){var _2=this.toUTCString();var _3=8;if(_1==null||_1<0||_1>3)_1=3;if(_2.substring(6,7)==' '){_3=7}
return _2.substring(_3,(_3+_1))}
,isc.A.getShortMonthName=function isc_Dat_getShortMonthName(){return Date.getShortMonthNames()[this.getMonth()]}
,isc.A.getShortYear=function isc_Dat_getShortYear(){var _1=this.getFullYear();return(_1%100).stringify(2)}
,isc.A.getWeek=function isc_Dat_getWeek(){var _1=new Date(this.getFullYear(),0,1);return Math.ceil((((this-_1)/86400000)+_1.getDay())/7)}
,isc.A.toDateStamp=function isc_Dat_toDateStamp(){return this.getUTCFullYear()+(this.getUTCMonth()+1).stringify()+this.getUTCDate().stringify()+"T"+this.getUTCHours().stringify()+this.getUTCMinutes().stringify()+this.getUTCSeconds().stringify()+"Z"}
,isc.A.toNormalDate=function isc_Dat_toNormalDate(_1,_2){if(!_1)_1=this.formatter;if(isc.isA.Function(_1)){return _1.apply(this,[_2])}else if(this[_1]){return this[_1](_2)}}
,isc.A.toShortDate=function isc_Dat_toShortDate(_1,_2){if(!_1)_1=this.$el;if(isc.isA.Function(_1))return _1.apply(this,[_2]);else if(isc.isA.Function(this[_1]))return this[_1](_2);isc.logWarn("Date.toShortDate() specified formatter not understood:"+_1);return this.toUSShortDate()}
,isc.A.toShortDateTime=function isc_Dat_toShortDateTime(_1,_2){return this.toShortDatetime(_1,_2)}
,isc.A.toShortDatetime=function isc_Dat_toShortDatetime(_1,_2){if(!_1)_1=this.$68e;return this.toShortDate(_1,_2)}
,isc.A.setDefaultDateSeparator=function isc_Dat_setDefaultDateSeparator(_1){this.$em=[,,,,_1,,,,,_1,,,,null];this.$665=_1}
,isc.A.getDefaultDateSeperator=function isc_Dat_getDefaultDateSeperator(_1){if(this.$665)return this.$665;else return"/"}
,isc.A.$68d=function isc_Dat__applyTimezoneOffset(_1,_2,_3){if(_3==null)_3=this.getTime();if(isc.isA.Number(_1))_3+=(3600000*_1);if(isc.isA.Number(_2))_3+=(60000*_2);this.setTime(_3)}
,isc.A.$68g=function isc_Dat__getTimezoneOffsetDate(_1,_2){var _3=Date.$68h;if(_3==null)_3=Date.$68h=new Date();_3.$68d(_1,_2,this.getTime());return _3}
,isc.A.$es=function isc_Dat__toShortDate(_1,_2){if(_2==null){_2=!this.logicalDate}
var _3=this.$em,_4,_5,_6;if(!_2||!isc.Time.$854){_4=this.getMonth()+1;_5=this.getDate();_6=this.getFullYear()}else{var _7=this.$68g(isc.Time.getUTCHoursDisplayOffset(this),isc.Time.getUTCMinutesDisplayOffset(this));_4=_7.getUTCMonth()+1;_5=_7.getUTCDate();_6=_7.getUTCFullYear()}
var _8,_9,_10;if(_1==this.$en){_8=0;_9=5;_10=10}else if(_1==this.$eo){_9=0;_8=5;_10=10}else if(_1==this.$ep){_10=0;_8=5;_9=10}else{_9=_1.indexOf("D")*5;_10=_1.indexOf("Y")*5;_8=_1.indexOf("M")*5}
_3[_9]=_5<10?this.$w0:null
isc.$bk(_3,_5,_9+1,3);_3[_8]=_4<10?this.$w0:null
isc.$bk(_3,_4,_8+1,3);isc.$bk(_3,_6,_10,4);return _3.join(isc.emptyString)}
,isc.A.toUSShortDate=function isc_Dat_toUSShortDate(_1){return this.$es(this.$en,_1)}
,isc.A.$68i=function isc_Dat__toShortTime(_1){return isc.Time.toShortTime(this,"toShortPadded24HourTime")}
,isc.A.toUSShortDateTime=function isc_Dat_toUSShortDateTime(_1){return this.toUSShortDatetime(_1)}
,isc.A.toUSShortDatetime=function isc_Dat_toUSShortDatetime(_1){return this.toUSShortDate(_1)+" "+this.$68i(_1)}
,isc.A.toEuropeanShortDate=function isc_Dat_toEuropeanShortDate(_1){return this.$es(this.$eo,_1)}
,isc.A.toEuropeanShortDateTime=function isc_Dat_toEuropeanShortDateTime(_1){return this.toEuropeanShortDatetime()}
,isc.A.toEuropeanShortDatetime=function isc_Dat_toEuropeanShortDatetime(_1){return this.toEuropeanShortDate(_1)+" "+this.$68i(_1)}
,isc.A.toJapanShortDate=function isc_Dat_toJapanShortDate(_1){return this.$es(this.$ep,_1)}
,isc.A.toJapanShortDateTime=function isc_Dat_toJapanShortDateTime(_1){return this.toJapanShortDatetime(_1)}
,isc.A.toJapanShortDatetime=function isc_Dat_toJapanShortDatetime(_1){return this.toJapanShortDate(_1)+" "+this.$68i(_1)}
,isc.A.$eu=function isc_Dat__serialize(){if(isc.Comm.$ev){return isc.SB.concat('"'+this.toDBDate(),'"')}else{return isc.SB.concat("new Date(",this.getTime(),")")}}
,isc.A.$ew=function isc_Dat__xmlSerialize(_1,_2,_3,_4){return isc.Comm.$ex(_1,this.toSchemaDate(),_2||(this.logicalDate?"date":(this.logicalTime&&!isc.DataSource.serializeTimeAsDatetime?"time":"datetime")),_3,_4)}
,isc.A.toSchemaDate=function isc_Dat_toSchemaDate(_1){if((_1=="date")||this.logicalDate){return isc.SB.concat(this.getFullYear().stringify(4),"-",(this.getMonth()+1).stringify(2),"-",this.getDate().stringify(2))};if((!isc.DataSource||!isc.DataSource.serializeTimeAsDatetime)&&(_1=="time"||this.logicalTime))
{return isc.SB.concat(this.getHours().stringify(2),":",this.getMinutes().stringify(2),":",this.getSeconds().stringify(2))}
return isc.SB.concat(this.getUTCFullYear().stringify(4),"-",(this.getUTCMonth()+1).stringify(2),"-",this.getUTCDate().stringify(2),"T",this.getUTCHours().stringify(2),":",this.getUTCMinutes().stringify(2),":",this.getUTCSeconds().stringify(2))}
,isc.A.toSerializeableDate=function isc_Dat_toSerializeableDate(_1){var _2=isc.SB.create();_2.append(this.getFullYear().stringify(4),"-",(this.getMonth()+1).stringify(2),"-",this.getDate().stringify(2));if(!_1)_2.append((isc.Comm.xmlSchemaMode?"T":" "),this.getHours().stringify(2),":",this.getMinutes().stringify(2),":",this.getSeconds().stringify(2));return _2.toString()}
,isc.A.toDBDate=function isc_Dat_toDBDate(){return isc.StringBuffer.concat("$$DATE$$:",this.toSerializeableDate())}
,isc.A.toDBDateTime=function isc_Dat_toDBDateTime(){return this.toDBDate()}
,isc.A.setFormatter=function isc_Dat_setFormatter(_1){this.setNormalDisplayFormat(_1)}
,isc.A.setLocaleStringFormatter=function isc_Dat_setLocaleStringFormatter(_1){if(isc.isA.Function(this[_1])||isc.isA.Function(_1))
this.localeStringFormatter=_1}
,isc.A.isBeforeToday=function isc_Dat_isBeforeToday(_1){var _2=new Date(this.getFullYear(),this.getMonth(),this.getDate(),0).getTime();if(_1.getTime()<_2)return true;else return false}
,isc.A.isToday=function isc_Dat_isToday(_1){if(this.getFullYear()==_1.getFullYear()&&this.getMonth()==_1.getMonth()&&this.getDate()==_1.getDate())
return true;else return false}
,isc.A.isTomorrow=function isc_Dat_isTomorrow(_1){var _2=new Date(this.getFullYear(),this.getMonth(),this.getDate()+1,0);var _3=new Date(this.getFullYear(),this.getMonth(),this.getDate()+1,23);var _4=_1.getTime();if(_4>=_2.getTime()&&_4<=_3.getTime()){return true}else{return false}}
,isc.A.isThisWeek=function isc_Dat_isThisWeek(_1){var _2=new Date(this.getFullYear(),this.getMonth(),this.getDate()-this.getDay(),0);var _3=new Date(this.getFullYear(),this.getMonth(),this.getDate()+(7-this.getDay()),23);var _4=_1.getTime();if(_4>=_2.getTime()&&_4<=_3.getTime()){return true}else{return false}}
,isc.A.isNextWeek=function isc_Dat_isNextWeek(_1){var _2=new Date(this.getFullYear(),this.getMonth(),(this.getDate()-this.getDay())+7,0);var _3=new Date(this.getFullYear(),this.getMonth(),(this.getDate()-this.getDay())+14,23);var _4=_1.getTime();if(_4>=_2.getTime()&&_4<=_3.getTime()){return true}else{return false}}
,isc.A.isNextMonth=function isc_Dat_isNextMonth(_1){var _2=new Date(this.getFullYear(),this.getMonth());_2.setMonth(_2.getMonth()+1);if(_2.getFullYear()==_1.getFullYear()&&_2.getMonth()==_1.getMonth()){return true}else{return false}}
);isc.B._maxIndex=isc.C+42;Date.prototype.toBrowserString=Date.prototype.toString;Date.prototype.toBrowserLocaleString=Date.prototype.toLocaleString;if(!Date.prototype.formatter)Date.prototype.formatter="toLocaleString"
if(!Date.prototype.$el)Date.setShortDisplayFormat("toUSShortDate");if(!Date.prototype.$68e)Date.setShortDatetimeDisplayFormat("toUSShortDatetime");Date.prototype.iscToLocaleString=function(){var _1=this.localeStringFormatter;if(isc.isA.Function(_1))return _1.apply(this);else if(this[_1])return this[_1]()}
if(!Date.prototype.localeStringFormatter)
Date.prototype.localeStringFormatter="toLocaleString";isc.A=Date;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.$ek=function isc_Date__splitDateViaSubstring(_1,_2,_3,_4){var _5=_4*3,_6=_1.substring(_5,_5+4);var _7=_6.length;var _8=0,_9=0;if(_2>_3)_8+=3;else _9+=3;if(_2>_4)_8+=_7+1;if(_3>_4)_9+=_7+1;var _10=_1.substring(_8,_8+2)-1;var _11=_1.substring(_9,_9+2);var _12=7+_7,_13=(_1.substring(_12,_12+2)||0),_14=(_1.substring(_12+3,_12+5)||0),_15=(_1.substring(_12+6,_12+8)||0);return[_6,_10,_11,_13,_14,_15]}
);isc.B._maxIndex=isc.C+1;isc.A=Date.prototype;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.toPrettyString=function isc_Dat_toPrettyString(){return this.toUSShortDatetime()}
);isc.B._maxIndex=isc.C+1;isc.A=Date;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.parseStandardDate=function isc_Date_parseStandardDate(_1){if(!isc.isA.String(_1))return null;var _2=_1.substring(0,4),_3=_1.substring(5,7)-1,_4=_1.substring(8,10),_5=_1.substring(11,13),_6=_1.substring(14,16),_7=_1.substring(17,19);if(_1.length<19){if(!isc.isA.Number(_2-_3-_4))return null}else{if(!isc.isA.Number(_2-_3-_4-_5-_6-_7))return null}
return new Date(_2,_3,_4,_5,_6,_7)}
,isc.A.parseSerializeableDate=function isc_Date_parseSerializeableDate(_1){return this.parseStandardDate(_1)}
,isc.A.parseDBDate=function isc_Date_parseDBDate(_1){if(isc.isA.String(_1)&&_1.startsWith("$$DATE$$:")){_1=_1.substring(9)
return this.parseStandardDate(_1)}
return null}
,isc.A.parseDateStamp=function isc_Date_parseDateStamp(_1){if(_1==null||isc.isA.Date(_1))return _1;var _2=new Date(Date.UTC(_1.substring(0,4),parseInt(_1.substring(4,6),10)-1,_1.substring(6,8),_1.substring(9,11),_1.substring(11,13),_1.substring(13,15)));if(isc.isA.Date(_2))return _2;else return null}
,isc.A.parseShortDate=function isc_Date_parseShortDate(_1,_2){return this.parseInput(_1,"MDY",_2)}
,isc.A.parseShortDateTime=function isc_Date_parseShortDateTime(_1,_2){return this.parseShortDate(_1,_2)}
,isc.A.parsePrettyString=function isc_Date_parsePrettyString(_1,_2){return this.parseShortDate(_1,_2)}
,isc.A.parseEuropeanShortDate=function isc_Date_parseEuropeanShortDate(_1,_2){return this.parseInput(_1,"DMY",_2)}
,isc.A.parseEuropeanShortDateTime=function isc_Date_parseEuropeanShortDateTime(_1,_2){return this.parseInput(_1,"DMY",_2)}
,isc.A.setToZeroTime=function isc_Date_setToZeroTime(_1){if(_1==null||!isc.isA.Date(_1))return _1;var _2=_1.logicalDate;_1.logicalDate=false;var _3=_1.getTime();var _4=isc.Time.getUTCHoursDisplayOffset(_1),_5=isc.Time.getUTCMinutesDisplayOffset(_1);if(_2){var _6=new Date(_1);_6.setHours(0);_6.setMinutes(0);var _7=isc.Time.getUTCHoursDisplayOffset(_6);if(_4!=_7){_4=_7}}
var _8=_4>0?24-_4:0-_4,_9=_5>0?60-_5:0-_5;var _10;if(_2){_10=_1.getDate()}else{var _11=_1.$68g(_4,_5);_10=_11.getUTCDate()}
_1.setUTCHours(_8);var _12=_1.$68g(_4,_5),_13=_12.getUTCDate(),_14=_8;if(_13!=_10){var _15=_1.getTime()<_3;_14+=_15?24:-24;_1.setUTCHours(_14)}
if(_1.getUTCHours()!=_8){_1.setTime(_3);_1.setUTCHours(_14+1);if(_1.getUTCHours()!=_8+1){_1.setTime(_3);_1.setUTCHours(_14+2)}}
_1.setUTCMinutes(_9)}
);isc.B._maxIndex=isc.C+10;isc.A=isc.DateUtil;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$86v={s:true,S:true,mn:true,MN:true,h:true,H:true,d:true,D:true};isc.B.push(isc.A.mapRelativeDateShortcut=function isc_c_DateUtil_mapRelativeDateShortcut(_1,_2){switch(_1){case"$now":return"+0MS";case"$today":if(_2=="end"){return"+0D"}else{return"-0D"}
case"$startOfToday":return"-0D";case"$endOfToday":return"+0D";case"$yesterday":if(_2=="end"){return"-1d[+0D]"}else{return"-1D"}
case"$startOfYesterday":return"-1D";case"$endOfYesterday":return"-1d[+0D]";case"$tomorrow":if(_2=="end"){return"+1D"}else{return"+1d[-0D]"}
case"$startOfTomorrow":return"+1d[-0D]";case"$endOfTomorrow":return"+1D";case"$startOfWeek":return"-0W";case"$endOfWeek":return"+0W";case"$startOfMonth":return"-0M";case"$endOfMonth":return"+0M";case"$startOfYear":return"-0Y";case"$endOfYear":return"+0Y";case"$weekFromNow":if(_2=="end"){return"+1w[+0D]"}else{return"+1w[-0D]"}
case"$weekAgo":if(_2=="end"){return"-1w[+0D]"}else{return"-1w[-0D]"}
case"$monthFromNow":if(_2=="end"){return"+1m[+0D]"}else{return"+1m[-0D]"}
case"$monthAgo":if(_2=="end"){return"-1m[+0D]"}else{return"-1m[-0D]"}}
return _1}
,isc.A.getAbsoluteDate=function isc_c_DateUtil_getAbsoluteDate(_1,_2,_3,_4){if(this.isRelativeDate(_1)){if(!_3)_3=_1.rangePosition;_1=_1.value}
if(_1.startsWith("$")){_1=this.mapRelativeDateShortcut(_1,_3)}
var _5=_1,_6=_4?Date.createLogicalDate():new Date();if(_2!=null)_6.setTime(_2.getTime());var _7=this.getRelativeDateParts(_5);if(_7.qualifier){_7.qualifier=_7.qualifier.toUpperCase();var _8=this.getRelativeDateParts(_7.qualifier);var _9=["S","MN","H","D","W","M","Q","Y"];if(_9.contains(_8.period)){_6=this.dateAdd(_6,_8.period,_8.countValue,(_8.direction=="+"?1:-1),_4)}else{isc.logWarn("Invalid date-offset qualifier provided: "+_8.period+".  Valid "+"options are: S, MN, H, D, W, M, Q and Y.")}}
var _10=this.dateAdd(_6,_7.period,_7.countValue,(_7.direction=="+"?1:-1),_4);return _10}
,isc.A.isRelativeDate=function isc_c_DateUtil_isRelativeDate(_1){if(isc.isA.Date(_1))return false;if(isc.isAn.Object(_1)&&_1._constructor=="RelativeDate")return true;return false}
,isc.A.getRelativeDateParts=function isc_c_DateUtil_getRelativeDateParts(_1){var _2=_1,_3=_2.substring(0,1),_4=_2.indexOf("["),_5=(_4>0?_2.substring(_4):null),_6=(_5!=null?_2.substring(1,_4):_2.substring(1)),_7=parseInt(_6),_8=_6.replace(_7,"");return{direction:(_3=="+"||_3=="-"?_3:"+"),qualifier:_5?_5.replace("[","").replace("]","").replace(",",""):null,countValue:isc.isA.Number(_7)?_7:0,period:_8?_8:_3}}
,isc.A.dateAdd=function isc_c_DateUtil_dateAdd(_1,_2,_3,_4,_5){var _6=false;switch(_2){case"MS":case"ms":_1.setMilliseconds(_1.getMilliseconds()+(_3*_4));break;case"S":_6=true;case"s":_1.setSeconds(_1.getSeconds()+(_3*_4));break;case"MN":_6=true;case"mn":_1.setMinutes(_1.getMinutes()+(_3*_4));break;case"H":_6=true;case"h":_1.setHours(_1.getHours()+(_3*_4));break;case"D":_6=true;case"d":_1.setDate(_1.getDate()+(_3*_4));break;case"W":_6=true;case"w":_1.setDate(_1.getDate()+((_3*7)*_4));break;case"M":_6=true;case"m":_1.setMonth(_1.getMonth()+(_3*_4));break;case"Q":_6=true;case"q":_1.setMonth(_1.getMonth()+((_3*3)*_4));break;case"Y":_6=true;case"y":_1.setFullYear(_1.getFullYear()+(_3*_4));break;case"DC":_6=true;case"dc":_1.setFullYear(_1.getFullYear()+((_3*10)*_4));break;case"C":_6=true;case"c":_1.setFullYear(_1.getFullYear()+((_3*100)*_4));break}
if(_6){if(_4>0){_1=this.getEndOf(_1,_2,_5)}else{_1=this.getStartOf(_1,_2,_5)}}
return _1}
,isc.A.getStartOf=function isc_c_DateUtil_getStartOf(_1,_2,_3){var _4,_5,_6,_7,_8,_9,_10;if(_3==null)_3=_1.logicalDate;if(_3&&this.$86v[_2]==true){this.logInfo("DateUtil.getStartOf() passed period:"+_2+" for logical date. Ignoring");var _11=new Date(_1.getTime());_1.logicalDate=true;return _11}
if(!isc.Time.$854||_3){_5=_1.getMonth();_6=_1.getDate();_4=_1.getFullYear();_7=_1.getHours();_8=_1.getMinutes();_9=_1.getSeconds();_10=_1.getDay()}else{var _12=_1.$68g(isc.Time.getUTCHoursDisplayOffset(_1),isc.Time.getUTCMinutesDisplayOffset(_1));_5=_12.getUTCMonth();_6=_12.getUTCDate();_4=_12.getUTCFullYear();_7=_12.getUTCHours();_8=_12.getUTCMinutes();_9=_12.getUTCSeconds();_10=_12.getDay()}
switch(_2){case"s":case"S":return Date.createDatetime(_4,_5,_6,_7,_8,_9,0);case"mn":case"MN":return Date.createDatetime(_4,_5,_6,_7,_8,0,0);case"h":case"H":return Date.createDatetime(_4,_5,_6,_7,0,0,0);case"d":case"D":return Date.createDatetime(_4,_5,_6,0,0,0,0);case"w":case"W":if(_3){return Date.createLogicalDate(_4,_5,(_6-_10))}else{return Date.createDatetime(_4,_5,(_6-_10),0,0,0,0)}
case"m":case"M":if(_3){return Date.createLogicalDate(_4,_5,1)}else{return Date.createDatetime(_4,_5,1,0,0,0,0)}
case"q":case"Q":var _13=_5-(_5%3);if(_3){return Date.createLogicalDate(_4,_13,1)}else{return Date.createDatetime(_4,_13,1,0,0,0,0)}
case"y":case"Y":if(_3){return Date.createLogicalDate(_4,0,1)}else{return Date.createDatetime(_4,0,1,0,0,0,0)}
case"dc":case"DC":var _14=_4-(_4%10);if(_3){return Date.createLogicalDate(_14,0,1)}else{return Date.createDatetime(_14,0,1,0,0,0,0)}
case"c":case"C":var _15=_4-(_4%100);if(_3){return Date.createLogicalDate(_15,0,1)}else{return Date.createDatetime(_15,0,1,0,0,0,0)}}
return _1.duplicate()}
,isc.A.getEndOf=function isc_c_DateUtil_getEndOf(_1,_2,_3){var _4,_5,_6,_7,_8,_9,_10;if(_3==null)_3=_1.logicalDate;if(_3&&this.$86v[_2]==true){this.logInfo("DateUtil.getEndOf() passed period:"+_2+" for logical date. Ignoring");var _11=new Date(_1.getTime());_1.logicalDate=true;return _11}
if(!isc.Time.$854||_3){_5=_1.getMonth();_6=_1.getDate();_4=_1.getFullYear();_7=_1.getHours();_8=_1.getMinutes();_9=_1.getSeconds();_10=_1.getDay()}else{var _12=_1.$68g(isc.Time.getUTCHoursDisplayOffset(_1),isc.Time.getUTCMinutesDisplayOffset(_1));_5=_12.getUTCMonth();_6=_12.getUTCDate();_4=_12.getUTCFullYear();_7=_12.getUTCHours();_8=_12.getUTCMinutes();_9=_12.getUTCSeconds();_10=_12.getDay()}
switch(_2){case"s":case"S":return Date.createDatetime(_4,_5,_6,_7,_8,_9,999);case"mn":case"MN":return Date.createDatetime(_4,_5,_6,_7,_8,59,999);case"h":case"H":return Date.createDatetime(_4,_5,_6,_7,59,59,999);case"d":case"D":return Date.createDatetime(_4,_5,_6,23,59,59,999);case"w":case"W":var _13=_6+(6-_10);if(_3){return Date.createLogicalDate(_4,_5,_13)}else{return Date.createDatetime(_4,_5,_13,23,59,59,999)}
case"m":case"M":var _11;if(_3){_11=Date.createLogicalDate(_4,_5+1,1);_11.setTime(_11.getTime()-(24*60*60*1000))}else{_11=Date.createDatetime(_4,_5+1,1,0,0,0,0);_11.setTime(_11.getTime()-1)}
return _11;case"q":case"Q":var _14=_5+3-(_5%3),_11;if(_3){_11=Date.createLogicalDate(_4,_14,1);_11.setDate(_11.getDate()-1)}else{_11=Date.createDatetime(_4,_14,1,0,0,0,0);_11.setTime(_11.getTime()-1)}
return _11;case"y":case"Y":if(_3){return Date.createLogicalDate(_4,11,31)}else{return Date.createDatetime(_4,11,31,23,59,59,999)}
case"dc":case"DC":var _15=_4+10-(_4%10);if(_3){return Date.createLogicalDate(_15,11,31)}else{return Date.createDatetime(_15,11,31,23,59,59,999)}
case"c":case"C":var _16=_4+100-(_4%100);if(_3){return Date.createLogicalDate(_16,11,31)}else{return Date.createDatetime(_16,11,31,23,59,59,999)}}
return _1.duplicate()}
);isc.B._maxIndex=isc.C+7;String.prototype.Class="String";isc.$ey=function(){var _1=[Array,Number,Date].getProperty("prototype");for(var i=0;i<_1.length;i++){var _3=_1[i];if(_3.toLocaleString==null){_3.toLocaleString=_3.toString}}
var _4=String.prototype;if(!_4.toLocaleUpperCase){_4.toLocaleUpperCase=_4.toUpperCase;_4.toLocaleLowerCase=_4.toLowerCase}
if(isc.Browser.isMoz){var _5="x",_6=_5.toLocaleString();if(_6!=_5){_4.toBrowserLocaleString=_4.toLocaleString;_4.toLocaleString=_4.toString}
_5=true;_6=_5.toLocaleString();if(_6!=_5+""){Boolean.prototype.toBrowserLocaleString=Boolean.prototype.toLocaleString;Boolean.prototype.toLocaleString=Boolean.prototype.toString}}}
isc.$ey();isc.A=String;isc.A.$ez=new RegExp("'","g");isc.A.$e0=new RegExp("\"","g");isc.A=String.prototype;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.replaceAll=function isc_Strin_replaceAll(_1,_2){return isc.replaceAll(this,_1,_2)}
,isc.A.contains=function isc_Strin_contains(_1){if(_1&&!isc.isA.String(_1))_1=_1.toString();return isc.contains(this,_1)}
,isc.A.startsWith=function isc_Strin_startsWith(_1){if(_1&&!isc.isA.String(_1))_1=_1.toString();return isc.startsWith(this,_1)}
,isc.A.endsWith=function isc_Strin_endsWith(_1){if(_1&&!isc.isA.String(_1))_1=_1.toString();return isc.endsWith(this,_1)}
,isc.A.trim=function isc_Strin_trim(_1){var _2=_1||" \t\n\r",l=this.length,_4=0,_5=l-1,i=0;while(_4<l&&_2.contains(this.charAt(i++)))_4++;i=l-1;while(_5>=0&&_5>=_4&&_2.contains(this.charAt(i--)))_5--;return this.substring(_4,_5+1)}
,isc.A.convertTags=function isc_Strin_convertTags(_1,_2){return(_1?_1:"")+this.replace(/</g,"&lt;").replace(/>/g,"&gt;")+(_2?_2:"")}
,isc.A.asHTML=function isc_Strin_asHTML(_1){var s=this.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/(\r\n|\r|\n) /g,"<BR>&nbsp;").replace(/(\r\n|\r|\n)/g,"<BR>").replace(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;");return(_1?s.replace(/ /g,"&nbsp;"):s.replace(/  /g," &nbsp;"))}
,isc.A.unescapeHTML=function isc_Strin_unescapeHTML(){return this.replace(/&nbsp;/g," ").replace(/<BR>/gi,"\n").replace(/&gt;/g,">").replace(/&lt;/g,"<").replace(/&amp;/g,"&")}
,isc.A.toInitialCaps=function isc_Strin_toInitialCaps(){var _1=this.toLowerCase().split(" ");for(var i=0;i<_1.length;i++){_1[i]=_1[i].substring(0,1).toLocaleUpperCase()+_1[i].substring(1)}
return _1.join(" ")}
,isc.A.evalDynamicString=function isc_Strin_evalDynamicString(_1,_2){if(this.indexOf("${")<0)return this.toString();var _3=this,_4,_5,_6,_7;var _8=isc.StringBuffer.create();while((_5=_3.indexOf("${"))!=-1){_6=_3.indexOf("}",_5+1);if(_6==-1)break;if(_3.charAt(_5-1)=='\\'){_8.append(_3.slice(0,_5-1),_3.slice(_5,_6+1));_3=_3.substring(_6+1,_3.length);continue}
var _7=_3.slice(_5+2,_6);var _9;if(_2!=null&&_2[_7]){_9=_2[_7]}else{try{_9=isc.Class.evalWithVars(_7,_2,_1)}catch(e){var _10=_1?_1:isc.Log;_10.logWarn("dynamicContents eval error - returning empty string for block -->${"+_7+"}<-- error was: "+isc.Log.echo(e));_9=isc.emptyString}}
_8.append(_3.slice(0,_5),_9);_3=_3.substring(_6+1,_3.length)}
_8.append(_3);_3=_8.toString();return _3}
,isc.A.asSource=function isc_Strin_asSource(_1){return String.asSource(this,_1)}
,isc.A.cssToCamelCaps=function isc_Strin_cssToCamelCaps(){return this.replace(/-([^a-z]*)([a-z])/g,function(_1,_2,_3,_4,_5){return _2+_3.toUpperCase()})}
);isc.B._maxIndex=isc.C+12;isc.A=String;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.asSource=function isc_String_asSource(_1,_2){if(!isc.isA.String(_1))_1=""+_1;var _3=_2?String.$ez:String.$e0,_4=_2?"'":'"';return _4+_1.replace(/\\/g,"\\\\").replace(_3,'\\'+_4).replace(/\t/g,"\\t").replace(/\r/g,"\\r").replace(/\n/g,"\\n")+_4}
);isc.B._maxIndex=isc.C+1;isc.addMethods(isc,{replaceAll:function(_1,_2,_3){return _1.split(_2).join(_3)},contains:function(_1,_2){if(_1==null)return false;return _1.indexOf(_2)>-1},startsWith:function(_1,_2){if(_1==null)return false;return(_1.lastIndexOf(_2,0)==0)},endsWith:function(_1,_2){if(_1==null)return false;var _3=_1.length-_2.length;if(_3<0)return false;return(_1.indexOf(_2,_3)==_3)},makeXMLSafe:function(_1,_2,_3,_4,_5,_6,_7){if(_1==null)return isc.emptyString;else if(!isc.isA.String(_1))_1=_1.toString();if(_2!=false)_1=_1.replace(this.$914,this.$915);if(_3!=false)_1=_1.replace(this.$916,this.$36h);if(_4!=false)_1=_1.replace(this.$917,this.$36i);if(_5!=false)_1=_1.replace(String.$e0,this.$918);if(_6!=false)_1=_1.replace(String.$ez,this.$919);if(_7!=false)_1=_1.replace(this.$92a,this.$92b);return _1},$915:"&amp;",$36h:"&lt;",$36i:"&gt;",$918:"&quot;",$919:"&apos;",$92b:"&#x000D;",$914:/&/g,$916:/</g,$917:/>/g,$92a:/\r/g,makeCDATA:function(_1){return"<![CDATA["+_1.replace(/\]\]>/,"]]<![CDATA[>")+"]]>"}});isc.ClassFactory.defineClass("StringBuffer");isc.SB=isc.StringBuffer;isc.A=isc.StringBuffer;isc.A.$e1=[];isc.A.$e2=50;isc.A=isc.StringBuffer.getPrototype();isc.A.maxStreamLength=(isc.Browser.isIE6?1000:100000);isc.A.addPropertiesOnCreate=false;isc.A=isc.StringBuffer.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.init=function isc_StringBuffer_init(){this.$e3=[]}
,isc.A.append=function isc_StringBuffer_append(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13,_14,_15,_16,_17,_18,_19,_20,_21,_22,_23,_24,_25,_26,_27){var _28=this.$e3,_29,_30;if(_1!=null&&_1.constructor.$k==2){var _31=_1.length;if(_31<=30){var _31=_28.length;for(var i=0;i<_1.length;i++){_28[_31++]=_1[i]}}else{_28[_28.length]=_1.join(isc.emptyString)}}else{if(_27===_30&&_26===_30&&_25===_30){if(_1!=null)_28[_28.length]=_1;if(_2!=null)_28[_28.length]=_2
if(_3!=null)_28[_28.length]=_3
if(_4!=null)_28[_28.length]=_4
if(_5!=null)_28[_28.length]=_5
if(_6!=null)_28[_28.length]=_6
if(_7!=null)_28[_28.length]=_7
if(_8!=null)_28[_28.length]=_8
if(_9!=null)_28[_28.length]=_9
if(_10!=null)_28[_28.length]=_10
if(_11!=null)_28[_28.length]=_11
if(_12!=null)_28[_28.length]=_12
if(_13!=null)_28[_28.length]=_13
if(_14!=null)_28[_28.length]=_14
if(_15!=null)_28[_28.length]=_15
if(_16!=null)_28[_28.length]=_16
if(_17!=null)_28[_28.length]=_17
if(_18!=null)_28[_28.length]=_18
if(_19!=null)_28[_28.length]=_19
if(_20!=null)_28[_28.length]=_20
if(_21!=null)_28[_28.length]=_21
if(_22!=null)_28[_28.length]=_22
if(_23!=null)_28[_28.length]=_23
if(_24!=null)_28[_28.length]=_24}else{_29=arguments;for(var i=0,l=_29.length;i<l;i++){_28[_28.length]=_29[i]}}}
if(_28.length>this.maxStreamLength){_28[0]=_28.join(isc.emptyString);_28.length=1}
return this}
,isc.A.appendNumber=function isc_StringBuffer_appendNumber(_1,_2){var _3=this.$e3;if(_2==null){_2=5;var _4=_1;if(_4<0){_4=0-_4;_2+=1}
if(_4>=100000){_4=_4/ 100000;while(_4>=1){_2+=1;_4=_4/ 10}}}
isc.$bk(_3,_1,_3.length,_2)}
,isc.A.clear=function isc_StringBuffer_clear(){this.$e3.length=0}
,isc.A.release=function isc_StringBuffer_release(){var _1=isc.SB,_2=_1.$e1,_3=this.toString();if(_2.length<_1.$e2){this.clear();_2[_2.length]=this}
return _3}
,isc.A.getArray=function isc_StringBuffer_getArray(){return this.$e3}
);isc.B._maxIndex=isc.C+6;isc.StringBuffer.getPrototype().toString=function(){return this.$e3.join(isc.emptyString)}
isc.StringBuffer.$e4=Array.prototype.join;isc.A=isc.StringBuffer;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$bw=[];isc.B.push(isc.A.create=function isc_c_StringBuffer_create(){var _1=this.$e1,_2=_1.length;if(_2>0){var _3=_1[_2-1];_1.length=_2-1;return _3}else{return isc.Class.create.apply(this)}}
,isc.A.concat=function isc_c_StringBuffer_concat(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13,_14,_15,_16,_17,_18,_19,_20,_21,_22,_23,_24,_25,_26,_27,_28,_29,_30,_31,_32,_33,_34,_35,_36,_37,_38,_39,_40,_41,_42,_43,_44,_45,_46,_47,_48,_49,_50,_51,_52){var _53,_54;if(isc.Browser.isIE&&_50===_53&&_51===_53&&_52===_53){var _55=this.$bw;_55.length=0;if(_1!=null)_55[_55.length]=_1;if(_2!=null)_55[_55.length]=_2;if(_3!=null)_55[_55.length]=_3;if(_4!=null)_55[_55.length]=_4;if(_5!=null)_55[_55.length]=_5;if(_6!=null)_55[_55.length]=_6;if(_7!=null)_55[_55.length]=_7;if(_8!=null)_55[_55.length]=_8;if(_9!=null)_55[_55.length]=_9;if(_10!=null)_55[_55.length]=_10;if(_11!=null)_55[_55.length]=_11;if(_12!=null)_55[_55.length]=_12;if(_13!=null)_55[_55.length]=_13;if(_14!=null)_55[_55.length]=_14;if(_15!=null)_55[_55.length]=_15;if(_16!=null)_55[_55.length]=_16;if(_17!=null)_55[_55.length]=_17;if(_18!=null)_55[_55.length]=_18;if(_19!=null)_55[_55.length]=_19;if(_20!=null)_55[_55.length]=_20;if(_21!=null)_55[_55.length]=_21;if(_22!=null)_55[_55.length]=_22;if(_23!=null)_55[_55.length]=_23;if(_24!=null)_55[_55.length]=_24;if(_25!=null)_55[_55.length]=_25;if(_26!=null)_55[_55.length]=_26;if(_27!=null)_55[_55.length]=_27;if(_28!=null)_55[_55.length]=_28;if(_29!=null)_55[_55.length]=_29;if(_30!=null)_55[_55.length]=_30;if(_31!=null)_55[_55.length]=_31;if(_32!=null)_55[_55.length]=_32;if(_33!=null)_55[_55.length]=_33;if(_34!=null)_55[_55.length]=_34;if(_35!=null)_55[_55.length]=_35;if(_36!=null)_55[_55.length]=_36;if(_37!=null)_55[_55.length]=_37;if(_38!=null)_55[_55.length]=_38;if(_39!=null)_55[_55.length]=_39;if(_40!=null)_55[_55.length]=_40;if(_41!=null)_55[_55.length]=_41;if(_42!=null)_55[_55.length]=_42;if(_43!=null)_55[_55.length]=_43;if(_44!=null)_55[_55.length]=_44;if(_45!=null)_55[_55.length]=_45;if(_46!=null)_55[_55.length]=_46;if(_47!=null)_55[_55.length]=_47;if(_48!=null)_55[_55.length]=_48;if(_49!=null)_55[_55.length]=_49;if(_50!=null)_55[_55.length]=_50;if(_51!=null)_55[_55.length]=_51;if(_52!=null)_55[_55.length]=_52;_54=_55.join(isc.emptyString)}else{arguments.join=this.$e4;_54=arguments.join(isc.emptyString)}
return _54}
);isc.B._maxIndex=isc.C+2;isc.defineClass("StringMethod");isc.A=isc.StringMethod.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.toString=function isc_StringMethod_toString(){var _1=this.getValue();if(_1==null||isc.isA.String(_1))return _1;return _1.toString()}
,isc.A.getValue=function isc_StringMethod_getValue(){return this.value}
,isc.A.getDisplayValue=function isc_StringMethod_getDisplayValue(){var _1=this.getValue();if(_1==null||isc.isA.String(_1))return _1;if(_1.title!=null)return"["+_1.title+"]"
return _1}
,isc.A.cdata=function isc_StringMethod_cdata(_1){var _2=_1.indexOf("]]>");if(_2==-1)return"<![CDATA["+_1+"]]>";return this.cdata(_1.slice(0,_2))+"]]&gt;"+this.cdata(_1.slice(_2+3))}
,isc.A.$ew=function isc_StringMethod__xmlSerialize(_1,_2,_3,_4,_5,_6){var _7=this.value;if(isc.isA.String(_7))return isc.Comm.$ex(_1,this.cdata(_7),_2||"stringMethod",_3,_4);else
return isc.StringMethod.$41u(_7,_1,_4,_5,_6)}
);isc.B._maxIndex=isc.C+5;isc.A=isc.StringMethod;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$41v="Action";isc.B.push(isc.A.$41u=function isc_c_StringMethod__xmlSerializeAction(_1,_2,_3,_4,_5){var _6=isc.DataSource.get(this.$41v);if(!_6)return isc.Comm.$36t(_2,_1,_5,_4,_3);return[isc.Comm.$36u(_2),_6.xmlSerialize(_1,null,_3+"        ",this.$41v),"\n",_3,isc.Comm.$36v(_2)].join(isc.emptyString)}
);isc.B._maxIndex=isc.C+1;isc.ClassFactory.defineClass("Cookie");isc.A=isc.Cookie;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.init=function isc_c_Cookie_init(){isc.Cookie.list={};if(document.cookie=="")return;var _1=(""+document.cookie).split("; ");for(var i=0,_3=_1.length,_4;_4=_1[i],i<_3;i++){var _5=_4.indexOf('='),_6=(_5==-1?_4:_4.substring(0,_5));isc.Cookie.list[_6]=(_5==-1?'':unescape(_4.substring(_5+1)))}}
,isc.A.get=function isc_c_Cookie_get(_1){isc.Cookie.init();return isc.Cookie.list[_1]}
,isc.A.set=function isc_c_Cookie_set(_1,_2,_3,_4,_5){isc.Cookie.init();document.cookie=_1+"="+escape(_2)+(_3?";path="+_3:"")+(_4?";domain="+_4:"")+(_5?";expires="+(isc.isA.String(_5)?_5:_5.toGMTString()):"")}
,isc.A.clear=function isc_c_Cookie_clear(_1,_2,_3){isc.Cookie.init();this.set(_1,"",_2,_3,"Thu, 01-Jan-70 00:00:01 GMT")}
,isc.A.getList=function isc_c_Cookie_getList(){isc.Cookie.init();return isc.getKeys(isc.Cookie.list)}
);isc.B._maxIndex=isc.C+5;isc.defineClass("StackTrace");isc.A=isc.StackTrace;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.fromNativeStack=function isc_c_StackTrace_fromNativeStack(_1){if(isc.Browser.isMoz){return isc.MozStackTrace.create({stack:_1})}else if(isc.Browser.isChrome){return isc.ChromeStackTrace.create({stack:_1})}else{return isc.UnsupportedStackTrace.create({stack:_1})}}
);isc.B._maxIndex=isc.C+1;isc.A=isc.StackTrace.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.stack=null;isc.A.$83t="";isc.B.push(isc.A.init=function isc_StackTrace_init(){if(this.stack){this.$83u()}}
,isc.A.extractFunctionFromLine=function isc_StackTrace_extractFunctionFromLine(_1){this.logError("Should implement extractFunctionFromLine in subclass")}
,isc.A.extractArgumentsFromLine=function isc_StackTrace_extractArgumentsFromLine(_1){this.logError("Should implement extractArgumentsFromLine in subclass")}
,isc.A.extractSourceFromLine=function isc_StackTrace_extractSourceFromLine(_1){this.logError("Should implement extractSourceFromLine in subclass")}
,isc.A.$83u=function isc_StackTrace__parseStack(){try{var _1=this.stack.split("\n"),_2=isc.StringBuffer.create(),_3=isc.Page.getAppDir(),_4=window.location.protocol+"//"+window.location.host;for(var i=0;i<_1.length;i++){var _6=_1[i],_7=null,_8=null,_9=null;var _10=this.extractFunctionFromLine(_6);if(_10==""){_10="unnamed"}else if(_10.startsWith("isc_")){var _11;if(_10.startsWith("isc_c_")){_10=_10.substring(6);_11=true}else{_10=_10.substring(4)}
_8=_10.substring(0,_10.indexOf("_"));_9=_10.substring(_8.length+1);var _12=isc.ClassFactory.getClass(_8),_13=null;if(_12){_13=_11?_12[_9]:_12.getInstanceProperty(_9)}
if(_13!=null){_10=isc.Func.getName(_13,true);var _14;if(!_11){_14=_12.getArgString(_9)}else{_14=isc.Func.getArgString(_13)}
_7=_14.split(",")}else{_10=_10.replace(/_{1}/,".");_10=_10.replace(/_{2}/,"._")}}
_2.append("    ",_10,"(");var _14=this.extractArgumentsFromLine(_6);var _15=0;while(_14&&_14.length>0){if(_15>0)_2.append(", ");if(_7)_2.append(_7[_15]+"=>");var _16=_14.length;_14=this.$83v(_14,_2);if(_14.length==_16){isc.logWarn("failure to parse next arg at:\n"+_14);break}
_15++}
_2.append(")");var _17=_6.lastIndexOf("@");_2.append(this.$83w(this.extractSourceFromLine(_6),_3,_4));_2.append("\n")}
this.$83t=_2.toString()}
catch(e){this.$83t=this.stack}}
,isc.A.$83v=function isc_StackTrace__parseArgument(_1,_2){var _3=_1.charAt(0);if(_3=="\""){var _4=_1.search(/[^\\]"/);if(_4==-1)_4=_1.length;var _5=_1.substring(0,_4+2);if(_5.length>40){_5=_5.substring(0,40)+"...\"[ "+_5.length+"]"}
_2.append(_5);return _1.substring(_4+3)}else if(_3=="["){var _6=_1.substring(1).indexOf("]"),_7=_1.substring(0,_6+2);if(_7=="[object Object]")_7="{Obj}";_2.append(_7);return _1.substring(_6+3)}else if(_1.startsWith("(void 0)")){_2.append("undef");return _1.substring(9)}else if(_1.startsWith("undefined")){_2.append("undef");return _1.substring(10)}else if(_1.startsWith("(function ")){var _8=_1.substring(1,_1.indexOf("{"));if(_8.endsWith(" "))_8=_8.substring(0,_8.length-1);_2.append(_8);var _9=_1.indexOf("}),");if(_9==-1)return"";return _1.substring(_9+3)}else{var _10=_1.indexOf(",");if(_10==-1)_10=_1.length;_2.append(_1.substring(0,_10));return _1.substring(_10+1)}}
,isc.A.$83w=function isc_StackTrace__getSourceLine(_1,_2,_3){var _4=_1.indexOf("/system/modules/ISC_"),_5=_1.indexOf("/system/development/ISC_");if(_4!=-1){_1=_1.substring(_4+16)}else if(_5!=-1){_1=_1.substring(_5+20)+"[d]"}
if(_4!=-1||_5!=-1){if(!this.logIsDebugEnabled("traceLineNumbersCore"))return"";var _6=_1.indexOf("?isc_version");if(_6!=-1){_1=_1.substring(0,_6)+_1.substring(_1.indexOf(":"))}}
if(_1.startsWith(_2)){_1=_1.substring(_2.length)}else if(_1.startsWith(_3)){_1=_1.substring(_3.length)}
return" @ "+_1}
,isc.A.toString=function isc_StackTrace_toString(){return this.$83t}
);isc.B._maxIndex=isc.C+8;isc.defineClass("MozStackTrace",isc.StackTrace);isc.A=isc.MozStackTrace.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.extractFunctionFromLine=function isc_MozStackTrace_extractFunctionFromLine(_1){var _2=_1.indexOf("(");return _1.substring(0,_2)}
,isc.A.extractArgumentsFromLine=function isc_MozStackTrace_extractArgumentsFromLine(_1){var _2=_1.indexOf("(");var _3=_1.lastIndexOf("@");return _1.substring(_2+1,_3-1)}
,isc.A.extractSourceFromLine=function isc_MozStackTrace_extractSourceFromLine(_1){var _2=_1.lastIndexOf("@");if(_2>=0){return _1.substring(_2+1)}else{return""}}
);isc.B._maxIndex=isc.C+3;isc.defineClass("ChromeStackTrace",isc.StackTrace);isc.A=isc.ChromeStackTrace.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$83x=/Object\.([^ ]+)/;isc.A.$83y=/\((.+)\)/;isc.B.push(isc.A.extractFunctionFromLine=function isc_ChromeStackTrace_extractFunctionFromLine(_1){var _2=_1.match(this.$83x);return _2?_2[1]:""}
,isc.A.extractArgumentsFromLine=function isc_ChromeStackTrace_extractArgumentsFromLine(_1){return""}
,isc.A.extractSourceFromLine=function isc_ChromeStackTrace_extractSourceFromLine(_1){var _2=_1.match(this.$83y);return _2?_2[1]:""}
);isc.B._maxIndex=isc.C+3;isc.defineClass("UnsupportedStackTrace",isc.StackTrace);isc.A=isc.UnsupportedStackTrace.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.$83u=function isc_UnsupportedStackTrace__parseStack(){}
,isc.A.toString=function isc_UnsupportedStackTrace_toString(){return this.stack}
);isc.B._maxIndex=isc.C+2;isc.$e5={getCallTrace:function(_1,_2,_3){if(_1==null)_1=arguments.caller;if(_1==null)return"[getCallTrace(): Error: couldn't get arguments object]";var _4,_5=_1.callee;if(_5==null){_4="[args.callee == null]"}else if(!isc.Func){_4="[Func utility class not loaded]"}else{_4=isc.Func.getName(_5,true)}
_4+="(";var _6=(_5!=null?isc.Func.getArgs(_5):[]);var _7=Math.max(_1.length,_6.length);for(var i=0;i<_7;i++){var _9=_6[i],_10=_1[i];if(i>0)_4+=", ";if(_9!=null){_4+=_9+"=>"}
_4+=this.echoLeaf(_10)}
_4+=")";_2=_2||_1.$cw;if(_2)_4+=" on "+this.echoLeaf(_2);if(!_3&&!_5.$c7)return _4;var _11=this.$e6(_5);if(!_5.$c7){var _12=_11.split(/[\r\n]+/);if(_12.length>1||_12[0].length>200)return _4}
_4+='\n        "'+_11+'"';return _4},$e6:function(_1){var _2=isc.Func.getBody(_1);return _2.trim()},getStackTrace:function(_1,_2,_3,_4){var _5="";_5+=this.$76x(_1,_2,_3);if(this.hasFireBug()&&!_4){isc.Log.$50m=isc.Log.$50m||0;var _6="FBugTrace"+isc.Log.$50m++;_5+="\r\n"+this.fireBugTrace(_6)}
return _5},$76x:function(_1,_2,_3){if(!arguments||!arguments.callee||!arguments.callee.caller){return" [Stack trace not supported in this browser]"}
if(_1==null)_1=arguments.caller||arguments.callee.caller.arguments;var _4=[];var _5=isc.Browser.isIE&&isc.Browser.version<=5;if(_2!=null){for(var i=0;i<_2;i++){if(_1==null)break;if(!_5){_1=_1.callee.caller.arguments}else{_1=_1.caller}}}
if(_1==null){return""}
var _7=_1.callee;var _8=[];var _9=true;if(_3==null)_3=Number.MAX_VALUE;var _10=0;while(_7!=null&&_1!=null&&_10<_3){if(_1.timerTrace){_4.add("\nStack trace for setTimeout() call:   "+_1.timerTrace);break}
if(!_5){if(_8.contains(_7)){_4.add("    ** recursed on "+isc.Func.getName(_7,true));break}
_8.add(_7)}
_4.add("    "+this.getCallTrace(_1,null,(_9||_1.callee.caller==null)));if(_10==0){var _11=this.$e7(_1.$e8);if(_11)_4.add(_11)}
_7=_1.callee;if(!_5){_7=_7.caller;if(_7)_1=_7.arguments}else _1=_1.caller;_9=false;_10++}
if(_4.length==0)return"";return"\r\n"+_4.join("\r")+"\r"},hasFireBug:function(){return isc.Browser.isMoz&&window.console!=null&&window.console.trace!=null},fireBugVersion:function(){return this.hasFireBug()?window.console.firebug:null},fireBugTrace:function(_1){window.console.trace(_1);return" [Complete stack trace logged via Firebug: "+_1+"]"},$e7:function(_1){var _2=isc.SB.create();for(var _3 in _1){var _4=_1[_3],_5;if(_4===_5)continue;if(isc.startsWith(_3,isc.$ag))continue;_2.append("\n        "+_3+" = "+this.echoLeaf(_4))}
return _2.toString()},$am:function(_1,_2,_3,_4){if(_1.$fa)return;_1.$fa=true;var _5=_1.toString();if(_1.stack){_5+="\n";_5+=isc.StackTrace.fromNativeStack(_1.stack).toString()}else{_5+="  [No error.stack available]"}
this.logWarn(_5)},transformMozStackTrace:function(_1){return isc.StackTrace.fromNativeStack(_1).toString()},echoLeaf:function(_1,_2){var _3="",_4;if(_1===_4)return"undef";try{if(isc.isA.Class(_1)){_3+=_1.toString()}else if(isc.isAn.Array(_1)){_3+="Array["+_1.length+"]"}else if(isc.isA.Date(_1)){_3+="Date("+_1.toShortDate()+")"}else if(isc.isA.Function(_1)){_3+=isc.Func.getName(_1,true)+"()"}else{switch(typeof _1){case"string":if(_1.length<=40||_2){_3+='"'+_1+'"';break}
_3+='"'+_1.substring(0,40)+'..."['+_1.length+']';_3=_3.replaceAll("\n","\\n").replaceAll("\r","\\r");break;case"object":if(_1==null){_3+="null";break}
if(_1.tagName!=null){_3+="["+_1.tagName+"Element]"+this.getIDText(_1);break}
var _5=""+_1;if(_5!=""&&_5!="[object Object]"&&_5!="[object]")
{_3+=_5;break}
_3+="Obj"+this.getIDText(_1);break;default:_3+=""+_1}}
return _3}catch(e){var _6="[Error in echoLeaf: "+e+"]";_3+=_6;this.logDebug(_6,"Log");return _3}},getIDText:function(_1){var _2=_1.name||(isc.isAn.XMLNode(_1)?_1.getAttribute("name"):null);if(_2!=null&&!isc.isAn.emptyString(_2))return"{name:"+_2+"}";var _3=_1.ID!=null?_1.ID:_1.id!=null?_1.id:(isc.isAn.XMLNode(_1)?_1.getAttribute("id"):null);if(_3!=null&&!isc.isAn.emptyString(_3))return"{ID:"+_3+"}";if(_1.nodeName!=null&&!isc.isAn.emptyString(_1.nodeName)){return"{nodeName:"+_1.nodeName+"}"}
var _4=_1.title||(isc.isAn.XMLNode(_1)?_1.getAttribute("title"):null);if(_4!=null&&!isc.isAn.emptyString(_4))return"{title:"+_4+"}";var _5=_1.type||(isc.isAn.XMLNode(_1)?_1.getAttribute("type"):null);if(_5!=null&&!isc.isAn.emptyString(_5))return"{type:"+_5+"}";var _5=_1._constructor;if(_5!=null&&!isc.isAn.emptyString(_5))return"{_constructor:"+_5+"}";var _6=_1.label||(isc.isAn.XMLNode(_1)?_1.getAttribute("label"):null);if(_6!=null&&!isc.isAn.emptyString(_6))return"{label:"+_6+"}";var _5=_1.className;if(_5!=null&&!isc.isAn.emptyString(_5))return"{className:"+_5+"}";if(_1.length!=null)return"{length:"+_1.length+"}";return""},echo:function(_1,_2,_3,_4){if(_1==null)return this.echoLeaf(_1);if(_2==null)_2=true;if(_1.tagName)return this.echoDOM(_1);if(typeof _1!="object"||isc.isA.Date(_1))return this.echoLeaf(_1,true);if(isc.isAn.Array(_1)){var _5=(_3?"[\n":"[");for(var i=0;i<_1.length;i++){_5+=(_3?this.echo(_1[i],_2):this.echoLeaf(_1[i]));if(i+1<_1.length)_5+=(_3?",\n":", ")}
_5+="\n]";return _5}
var _5="{";if(_1.getUniqueProperties!=null){_5=_1.getClassName()+"{";_1=_1.getUniqueProperties();if(_4==null)_4=false}
if(_4==null)_4=true;var _7;try{_7=isc.getKeys(_1)}catch(e){return this.echoLeaf(_1)}
if(isc.Browser.isSafari){var _8=false,_9="[object CSSStyleDeclaration]";try{_8=(_1+""==_9)}catch(e){}
if(_8){_5=_9+"{\n[standard props only]\n";_7=isc.getKeys(isc.Canvas.$fi());_7.add("cssText")}}
for(var i=0;i<_7.length;i++){var _10=_7[i],_11;try{_11=_1[_10]}catch(e){_11="[error accessing property: "+e+"]"}
if(!_4&&isc.isA.Function(_11))continue;if(_10.startsWith("$"))continue;var _12;if(_10==isc.gwtRef){_12="{GWT Java Obj}"}else{_12=this.echoLeaf(_11)}
_5+=_10+": "+_12;if(i+1<_7.length)_5+=(_2?",\r":", ")}
_5+="}";return _5},echoAll:function(_1,_2){return this.echo(_1,_2,true)},echoFull:function(_1){return isc.JSON.encode(_1,{prettyPrint:true,showDebugOutput:true})},echoShort:function(_1){return this.echo(_1,false,false)},echoArray:function(_1){if(!isc.isAn.Array(_1))return this.echo(_1);if(_1.length==0)return"[empty array]";var _2=["["];for(var i=0;i<_1.length;i++){_2.addList([i,":",_1[i],"\n"])}
_2.add("]");return _2.join("")},$fj:{outerText:false,innerText:false,parentTextEdit:false,isTextEdit:false,parentTextEdit:false,contentEditable:false,canHaveHTML:true,isMultiLine:false,filters:false,canHaveChildren:false,behaviorUrns:false,sourceIndex:false,accelerator:false,textDecorationUnderline:false,textDecorationNone:false},echoDOM:function(_1){return this.echoDelta(_1,window.Node,_1.tagName+this.getIDText(_1))},echoEvent:function(_1){return this.echoDelta(_1,(isc.Browser.isMoz?window.KeyEvent:window.Event))},echoDelta:function(_1,_2,_3){if(_1==null)return null;if(isc.Browser.isIE&&isc.isAn.XMLNode(_1)){var _4="<"+_1.tagName+" [XMLNode] ";var _5=_1.attributes;for(var i=0;i<_5.length;i++){var _7=_5[i];if(i>0)_4+=" ";_4+=_7.name+"="+this.echoLeaf(_7.value)}
_4+=(i>0?" [":"")+_1.childNodes.length+" child nodes]>";return _4}
var _4=(_3||isc.emptyString)+"{",_8=isc.getKeys(_1);for(var i=0;i<_8.length;i++){var _9=_8[i];if(this.$fj[_9]!=null)continue;if(_2!=null&&_2[_9]!=null)continue;if(_9.length>3&&_9.toUpperCase()==_9)continue;try{var _10=_1[_9];if(_10==null||_10=="")continue;if(isc.isA.Function(_10))continue;_4+=_9+": "+this.echoLeaf(_1[_9])}catch(e){_4+=_9+": "+this.echoLeaf(e)}
if(i+1<_8.length)_4+=", "}
_4+="}";return _4},echoElementSize:function(_1){var _2;return this.echo({scrollLeft:_1.scrollLeft,scrollTop:_1.scrollTop,scrollWidth:_1.scrollWidth,scrollHeight:_1.scrollHeight,clientWidth:_2,clientHeight:_2,offsetWidth:_1.offsetWidth,offsetHeight:_1.offsetHeight,styleLeft:_1.style.left,styleTop:_1.style.top,styleWidth:_1.style.width,styleHeight:_1.style.height,styleClip:_1.style.clip})}};isc.Class.addProperties(isc.$e5)
isc.Class.addClassProperties(isc.$e5)
isc.$fk={logMessage:function(_1,_2,_3,_4){var _5=isc.Log;if(!_5)return;if(_1==null)_1=_5.defaultPriority;if(_1<=_5.stackTracePriority&&this.getStackTrace!=null){_2+="\nStack trace:\n"+this.getStackTrace(arguments,2)}
if(!_3)_3=this.Class;_5.log(_1,_2,_3,this.ID,this,_4)},logDebug:function(_1,_2){return this.logMessage(isc.Log.DEBUG,_1,_2)},logInfo:function(_1,_2){return this.logMessage(isc.Log.INFO,_1,_2)},logWarn:function(_1,_2){return this.logMessage(isc.Log.WARN,_1,_2)},logError:function(_1,_2){return this.logMessage(isc.Log.ERROR,_1,_2)},logFatal:function(_1,_2){return this.logMessage(isc.Log.FATAL,_1,_2)},logIsEnabledFor:function(_1,_2){return(isc.Log.isEnabledFor&&isc.Log.isEnabledFor((_2?_2:this.Class),_1,this))},logIsDebugEnabled:function(_1){return this.logIsEnabledFor(isc.Log.DEBUG,_1)},logIsInfoEnabled:function(_1){return this.logIsEnabledFor(isc.Log.INFO,_1)},logIsWarnEnabled:function(_1){return this.logIsEnabledFor(isc.Log.WARN,_1)},logIsErrorEnabled:function(_1){return this.logIsEnabledFor(isc.Log.ERROR,_1)},setLogPriority:function(_1,_2){isc.Log.setPriority(_1,_2,this)},setDefaultLogPriority:function(_1){isc.Log.setDefaultPriority(_1,this)},getDefaultLogPriority:function(){return isc.Log.getDefaultPriority(this)},clearLogPriority:function(_1){isc.Log.clearPriority(_1,this)}};isc.Class.addMethods(isc.$fk)
isc.Class.addClassMethods(isc.$fk)
isc.ClassFactory.defineClass("Log");isc.A=isc.Log;isc.A.FATAL=1;isc.A.ERROR=2;isc.A.WARN=3;isc.A.INFO=4;isc.A.DEBUG=5;isc.A.PRIORITY_NAMES=["NONE","FATAL","ERROR","WARN","INFO","DEBUG"];isc.A=isc.Log;isc.A.defaultPriority=isc.Log.WARN;isc.A.stackTracePriority=isc.Log.ERROR;isc.A.$fl={};isc.A.$fm={};isc.A.$fn=1000;isc.A.$fo=0;isc.A.$fp=[];isc.A.$fq=":";isc.A.$fr=".";isc.A._allCategories="_allCategories";isc.A.$fs="$fs";isc.A=isc.Log;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A._1zero="0";isc.A._2zero="00";isc.A.showInlineLogs=false;isc.A.$ft="$T_";isc.A.$73n={};isc.A.$fu=["Timed ",,": ",,"ms"];isc.A.flashHiliteCount=7;isc.A.flashHilitePeriod=500;isc.B.push(isc.A.applyLogPriorities=function isc_c_Log_applyLogPriorities(_1){if(!this.$fl){this.$fl={}}
if(_1){isc.addProperties(this.$fl,_1)}}
,isc.A.getLogPriorities=function isc_c_Log_getLogPriorities(_1,_2){var _3;if(_1!=null){var _4=this.$fv(_1);_3=this.$fm[_4];if(_2){return isc.addProperties({},_3)}}
var _5=isc.addProperties({},this.$fl);if(_3)_5=isc.addProperties(_5,_3);return _5}
,isc.A.$fv=function isc_c_Log__getObjectID(_1){var _2;if(_1==null)_2=isc.emptyString;else _2=(_1.getID?_1.getID():_1.getClassName());return _2}
,isc.A.getPriority=function isc_c_Log_getPriority(_1,_2){if(_2!=null){var _3=this.$fv(_2),_4=this.$fm[_3];if(_4){if(_4._allCategories!=null)return _4._allCategories;if(_4[_1]!=null)return _4[_1];if(_4.$fs!=null)return _4.$fs}}
var _5=this.$fl;return _5[_1]||_5.$fs}
,isc.A.setPriority=function isc_c_Log_setPriority(_1,_2,_3){if(_3!=null){var _4=this.$fv(_3);if(this.$fm[_4]==null)
this.$fm[_4]={};if(!_1)_1=this._allCategories;this.$fm[_4][_1]=_2}else{this.$fl[_1]=_2}}
,isc.A.setDefaultPriority=function isc_c_Log_setDefaultPriority(_1,_2){if(!_2||_2==isc.Log)isc.Log.defaultPriority=_1;else isc.Log.setPriority("$fs",_1,_2)}
,isc.A.getDefaultPriority=function isc_c_Log_getDefaultPriority(_1){var _2;if(_1&&_1!=isc.Log)_2=this.getPriority("$fs",_1);return _2||isc.Log.defaultPriority}
,isc.A.clearPriority=function isc_c_Log_clearPriority(_1,_2){if(_2){var _3=this.$fv(_2);if(!_1)
delete this.$fm[_3];else if(this.$fm[_3])
delete this.$fm[_3][_1]}else{delete this.$fl[_1]}}
,isc.A.isEnabledFor=function isc_c_Log_isEnabledFor(_1,_2,_3){if(!_1)_1=isc.$ad;while(_1!=isc.$ad){var _4=this.getPriority(_1,_3);if(_4!=null){return _2<=_4}
var _5=_1.lastIndexOf(this.$fr);if(_5>0){_1=_1.substring(0,_5)}else{break}}
return _2<=isc.Log.defaultPriority}
,isc.A.log=function isc_c_Log_log(_1,_2,_3,_4,_5,_6){if(this.isEnabledFor(_3,_1,_5))
this.addLogMessage(_1,_2,_3,_4,_6);else if(this.reportSuppressedLogs){this.logWarn("suppressed log, category: "+_3+": "+_2)}}
,isc.A.getLogTimestamp=function isc_c_Log_getLogTimestamp(_1){var _2=this.$fw;if(_2==null){_2=this.$fw=[];_2[2]=this.$fq;_2[5]=this.$fq;_2[8]=this.$fr}
if(_1==null)_1=new Date();var _3=_1.getHours(),_4=_1.getMinutes(),_5=_1.getSeconds(),_6=_1.getMilliseconds();_2[1]=_3;if(_3<10)_2[0]=this._1zero;else _2[0]=null;_2[4]=_4;if(_4<10)_2[3]=this._1zero;else _2[3]=null;_2[7]=_5;if(_5<10)_2[6]=this._1zero;else _2[6]=null;_2[10]=_6;if(_6<10)_2[9]=this._2zero;else if(_6<100)_2[9]=this._1zero;else _2[9]=null;return _2.join(isc.$ad)}
,isc.A.getPriorityName=function isc_c_Log_getPriorityName(_1){if(_1==null)return isc.$ad;return this.PRIORITY_NAMES[_1]}
,isc.A.$fc=function isc_c_Log__makeLogMessage(_1,_2,_3,_4,_5){var _6=this.$fx;if(_6==null){_6=this.$fx=[]}
if(!_3)_3=this.category;_6[0]=this.getLogTimestamp(_5);_6[1]=this.$fq;if(this.ns.EH&&this.ns.EH.$lc!=null){_6[2]=this.ns.EH.$lc;_6[3]=this.$fq}
if(_1!=null){_6[4]=this.getPriorityName(_1);_6[5]=this.$fq}
_6[6]=_3;_6[7]=this.$fq;if(_4){_6[8]=_4
_6[9]=this.$fq}
_6[10]=_2;var _7=_6.join(isc.$ad);_6.length=0;return _7}
,isc.A.addLogMessage=function isc_c_Log_addLogMessage(_1,_2,_3,_4,_5){var _6=this.$fc(_1,_2,_3,_4,_5);this.addToMasterLog(_6);if(this.warningLogged!=null&&_1!=null&&_1<=this.WARN){this.warningLogged(_6)}
if(_1!=null&&_1<=this.ERROR){alert(_2)}}
,isc.A.addToMasterLog=function(message){this.$fp[this.$fo]=message;this.$fo++;if(this.$fo>this.$fn){this.$fo=0}
if(this.showInlineLogs){this.updateInlineLogResults()}}
,isc.A.updateInlineLogResults=function isc_c_Log_updateInlineLogResults(){if(isc.Canvas==null||this.$fp==null)return;if(!this.inlineLogCanvas){this.inlineLogCanvas=isc.Canvas.create({width:"50%",height:"100%",overflow:"auto",backgroundColor:"white",canDragReposition:true,autoDraw:true})}
this.inlineLogCanvas.setContents(this.$fp.join("<br>"));this.inlineLogCanvas.bringToFront()}
,isc.A.getMessages=function isc_c_Log_getMessages(){var _1=this.$fp,_2=this.$fo,_3=this.$fn;return _1.slice(_3-_2,_3).concat(_1.slice(0,_2))}
,isc.A.show=function isc_c_Log_show(_1,_2,_3,_4,_5){if(!this.logViewer)this.logViewer=isc.LogViewer.create();this.logViewer.showLog(_1,_2,_3,_4,_5)}
,isc.A.clear=function isc_c_Log_clear(){this.$fp=[];this.$fo=0;if(this.logViewer)this.logViewer.clear()}
,isc.A.evaluate=function isc_c_Log_evaluate(_1,_2){var _3=isc.timeStamp();var _4,_5;if(isc.Log.supportsOnError){_5=isc.Class.evalWithVars(_1,_2,this)}else{try{_5=isc.Class.evalWithVars(_1,_2,this)}catch(e){_4=e}}
var _6=isc.timeStamp(),_7=isc.Log.getLogTimestamp()+":";var _8=_1.split(/[\r\n]+/);if(_8.length>1)_1=_8[0]+"...";if(_1.length>200)_1=_1.substring(0,200)+"...";if(_4){if(!isc.Log.supportsOnError){isc.Log.$am(_4);return}
_7+="Evaluator: '"+_1+"' returned a script error: \r\n"+"'"+_4+"'"}else{_7="Evaluator: result of '"+_1+"' ("+(_6-_3)+"ms):\r\n"+this.echo(_5)}
if(this.logViewer)this.logViewer.addToLog(_7,true)}
,isc.A.updateStats=function isc_c_Log_updateStats(_1){if(this.logViewer)this.logViewer.updateStats(_1)}
,isc.A.$fy=function isc_c_Log__logPrelogs(){var _1=isc.$j;if(!_1)return;for(var i=0;i<_1.length;i++){var _3=_1[i];if(isc.isA.String(_3))this.logDebug(_3);else this.logMessage(_3.priority||isc.Log.INFO,_3.message,_3.category,_3.timestamp)}
isc.$j=null}
,isc.A.traceMethod=function isc_c_Log_traceMethod(_1,_2,_3){var _4=this.validObservation(_1,_2);if(!_4)return;if(!this.$fz)this.$fz={};if(!this.$fz[_1])this.$fz[_1]=[];if(!this.$f0)this.$f0=isc.Class.create();var _5=this.$f0;if(_5.isObserving(_4,_2)&&this.$fz[_1].contains(_2))
{_5.ignore(_4,_2);this.logWarn("MethodTimer: Stopped logging stack traces for "+_2+" method on "+_1);this.$fz[_1].remove(_2)}else{var _6=_4.ID?_4.ID:(_4.Class?_4.Class:_4),_7="isc.Log.logWarn('"+_6+"."+_2+"() - trace:' +";if(_3){_7+="'\\n' + isc.Log.getCallTrace(arguments))"}else{_7+="isc.Log.getStackTrace())"}
this.logWarn("expression is: "+_7);_5.observe(_4,_2,_7);this.logWarn("MethodTimer: Logging traces whenever "+_2+" method on "+_1+" is called");this.$fz[_1].add(_2)}}
,isc.A.traceCall=function isc_c_Log_traceCall(_1,_2){this.traceMethod(_1,_2,true)}
,isc.A.timeMethod=function isc_c_Log_timeMethod(_1,_2,_3,_4,_5){var _6=this.validObservation(_1,_2);if(!_6)return;if(!this.$f1)this.$f1={};if(!this.$f1[_1])this.$f1[_1]=[];if(this.$f1[_1].contains(_2))return;var _7=isc.Log.$ft+_2,_8=isc.$ah+_2,_9=(_6[_8]?_8:_2);_6[_7]=_6[_9];_6[_9]=isc.Log.makeTimerFunction(_2,_6,_3,_4,_5);this.logWarn("MethodTimer: Timing "+_2+" method on "+_1);this.$f1[_1].add(_2)}
,isc.A.stopTimingMethod=function isc_c_Log_stopTimingMethod(_1,_2){var _3=this.validObservation(_1,_2);if(!_3)return;if(this.$f1[_1].contains(_2)){var _4=isc.Log.$ft+_2,_5=isc.$ah+_2,_6=(_3[_5]?_5:_2)
if(!_3[_4]){this.logWarn("Not timing method '"+_2+"' on object '"+_1+"'.");this.$f1[_1].remove(_2);return}
_3[_6]=_3[_4];delete _3[_4];this.logWarn("MethodTimer: "+_2+" method on "+_1+" is no longer being timed");this.$f1[_1].remove(_2);return}}
,isc.A.makeTimerFunction=function isc_c_Log_makeTimerFunction(_1,_2,_3,_4,_5){var _6=_2[_1],_7=isc.Func.getName(_6,true);var _8=function(_12,_13,_14,_15,_16,_17,_18,_19,_20,_21,_22){if(_5)isc.Log.$f2();var _9=isc.timeStamp();var _10=_6.call(this,_12,_13,_14,_15,_16,_17,_18,_19,_20,_21,_22);var _11=(isc.timeStamp()-_9);if(!_4)isc.Log.$f3(this,_7,_11);return _10}
_8.$dx=(_2.ID||_2.Class||"")+"_"+_1+"Timing";_8.$f4=true;_8.$c1=isc.Log.$ft+_1;return _8}
,isc.A.$f3=function isc_c_Log__logTimerResult(_1,_2,_3){if(this.deferTimerLogs)return this.$f5(_1,_2,_3);var _4=isc.Log.$fu;_4[1]=(_1.logWarn?_2:_2+" on "+this.echoLeaf(_1));_4[3]=_3.toFixed(3);var _5=_4.join(isc.emptyString);if(_1.logMessage)_1.logWarn(_5);else isc.Log.logWarn(_5)}
,isc.A.validObservation=function isc_c_Log_validObservation(_1,_2){if(isc.isAn.emptyString(_1)||isc.isAn.emptyString(_2))return false;var _3=_1;if(isc.isA.String(_1)){_3=isc.Class.evaluate(_1);if(!_3){this.logWarn("MethodTimer: "+_1+" is not an object.");return false}}
if(_2.indexOf("(")!=-1){_2=_2.slice(0,_2.indexOf("("))}
if(isc.isA.ClassObject(_3)){var _4=_3.getPrototype();if(isc.isA.Function(_4[_2]))return _4;if(!_3[_2]){this.logWarn("MethodTimer: "+_2+" could not be found as a static or instance property on "+_1);return false}}else if(!_3[_2]){this.logWarn("MethodTimer: "+_2+" is undefined or null on "+_1);return false}
if(!isc.Func.convertToMethod(_3,_2)){this.logWarn("MethodTimer: "+_2+" is not a method on "+_1);return false}
return _3}
,isc.A.hiliteCanvas=function isc_c_Log_hiliteCanvas(_1){var _2=_1;if(isc.isA.String(_1))_2=window[_1];if(!isc.isA.Canvas(_2)){this.logWarn("Unable to find specified canvas '"+_1+"'.");return}
this.showHiliteCanvas(_2.getPageRect())}
,isc.A.hiliteElement=function isc_c_Log_hiliteElement(_1){var _2=_1||this.elementToHilite;if(isc.isA.String(_1))_2=isc.Element.get(_1);if(_2==null){this.logWarn("Unable to find specified element '"+_1+"'.");return}
this.showHiliteCanvas(isc.Element.getElementRect(_2));this.elementToHilite=null}
,isc.A.showHiliteCanvas=function isc_c_Log_showHiliteCanvas(_1){var _2=this._hiliteCanvas;if(!_2){_2=this._hiliteCanvas=isc.Canvas.create({ID:"logHiliteCanvas",autoDraw:false,overflow:"hidden",hide:function(){this.Super("hide",arguments);this.resizeTo(1,1);this.setTop(-20)},border1:"2px dotted red",border2:"2px dotted white"})}
_2.setPageRect(_1);isc.Page.setEvent("click",_2.getID()+".hide()");_2.setBorder(_2.border1);_2.bringToFront();_2.show();this.$f6()}
,isc.A.hideHiliteCanvas=function isc_c_Log_hideHiliteCanvas(){if(this._hiliteCanvas)this._hiliteCanvas.hide()}
,isc.A.$f6=function isc_c_Log__flashHiliteCanvas(){var _1=[this._hiliteCanvas.border1,this._hiliteCanvas.border2];for(var i=0;i<this.flashHiliteCount;i++){isc.Timer.setTimeout({target:this._hiliteCanvas,methodName:"setBorder",args:[_1[i%2]]},(this.flashHilitePeriod*i))}}
);isc.B._maxIndex=isc.C+34;isc.ClassFactory.defineClass("LogViewer");isc.A=isc.LogViewer;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.getGlobalLogCookie=function isc_c_LogViewer_getGlobalLogCookie(){var _1=isc.Cookie.get("GLog");if(!_1)return null;try{var _2=new Function("return "+_1);return _2()}catch(e){this.logWarn("bad log cookie: "+_1+this.getStackTrace())}}
,isc.A.getLogCookie=function isc_c_LogViewer_getLogCookie(){var _1=isc.Cookie.get("Log");if(!_1)return null;try{var _2=new Function("return "+_1);return _2()}catch(e){this.logWarn("bad log cookie: "+_1+this.getStackTrace())}}
);isc.B._maxIndex=isc.C+2;isc.A=isc.LogViewer.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.showConsoleInline=isc.Browser.isTouch;isc.A.$891=0;isc.A.$f7=25;isc.A.$f8="count";isc.B.push(isc.A.logWindowLoaded=function isc_LogViewer_logWindowLoaded(){return(this._logWindowLoaded&&this._logWindow!=null&&!this._logWindow.closed)}
,isc.A.showLog=function isc_LogViewer_showLog(_1,_2,_3,_4,_5){if(_5==null)_5=this.showConsoleInline;if(_2)this._logWindow=_2;if(this.logWindowLoaded()){this._logWindow.setResultsValue(isc.Log.getMessages().join("\r"));if(!this.$892){this._logWindow.focus()}
return}
if(!isc.Log.logViewer)isc.Log.logViewer=this;if(this._logWindow&&!this._logWindow.closed){return}
var _6={},_7=(_3?null:isc.LogViewer.getGlobalLogCookie());if(_7!=null){_6=_7}else{_6.left=100;_6.top=100;_6.width=640;_6.height=480}
if(_5){if(this.inlineWindow==null){this.inlineWindow=isc.Window.create({title:"Inline Developer Console",src:isc.Page.getIsomorphicClientDir()+"helpers/Log.html",animateMinimize:false,width:"50%",height:Math.round(isc.Page.getHeight()*0.8),headerControls:["headerIcon","headerLabel",isc.Button.create({width:16,height:14,title:"TL",layoutAlign:"center",click:function(){isc.Log.logViewer.inlineWindow.moveTo(0,0)}}),isc.Button.create({width:16,height:14,title:"BL",layoutAlign:"center",click:function(){isc.Log.logViewer.inlineWindow.moveTo(0,isc.Page.getHeight()-isc.Log.logViewer.inlineWindow.getHeight())}}),isc.Button.create({width:16,height:14,title:"TR",layoutAlign:"center",click:function(){isc.Log.logViewer.inlineWindow.moveTo(isc.Page.getWidth()-isc.Log.logViewer.inlineWindow.getWidth(),0)}}),isc.Button.create({width:16,height:14,title:"BR",layoutAlign:"center",click:function(){isc.Log.logViewer.inlineWindow.moveTo(isc.Page.getWidth()-isc.Log.logViewer.inlineWindow.getWidth(),isc.Page.getHeight()-isc.Log.logViewer.inlineWindow.getHeight())}}),"minimizeButton","maximizeButton","closeButton"],showMaximizeButton:true,showMinimizeButton:true,canDragReposition:true,canDragResize:true})}
if(!this.inlineWindow.isDrawn()){this.inlineWindow.draw()}
this.$892=true}else{var _8="RESIZABLE,WIDTH="+_6.width+",HEIGHT="+_6.height;if(_7){if(isc.Browser.isIE){_8+=",left="+_6.left+",top="+_6.top}else{_8+=",screenX="+_6.left+",screenY="+_6.top}
if(_7.evals)this.$f9=_7.evals.length-1}
_4=_4||"_simpleLog";this._logWindow=window.open(isc.Page.getIsomorphicClientDir()+"helpers/Log.html",_4+(isc.version.contains("version")?"Dev":""),_8)}
this.$ga(_3)}
,isc.A.$ga=function isc_LogViewer__initLogWindow(_1){if(this._logWindow==null&&this.inlineWindow!=null){var _2=this.inlineWindow.body.$sk();if(_2){this._logWindow=this.inlineWindow.body.$sk().contentWindow}
if(this._logWindow==null){return}}
if(this._logWindow==null)return;if(isc.Browser.isIE){try{this._logWindow.$gb=true}catch(e){this.delayCall("$ga",[_1],this.$f7);return}}
if(isc.Browser.isIE||this.$892){this._logWindow.launchWindow=window;if(this.$892){this._logWindow.showingInline=true}}
if(_1)this._logWindow.dontSaveState=true;var _3=function(){if(isc.Log.logViewer){var _4=isc.Log.logViewer._logWindow;if(_4&&!_4.closed)_4.focus()}}
isc.Page.setEvent("idle",_3,isc.Page.FIRE_ONCE);if(this._logWindow.initializePage)this._logWindow.initializePage()}
,isc.A.addToLog=function isc_LogViewer_addToLog(_1,_2){if(this.logWindowLoaded()&&!this.$gc){this._logWindow.addToLog(_1,_2)}}
,isc.A.updateStats=function isc_LogViewer_updateStats(_1){if(isc.$gd)return;if(!this.logWindowLoaded())return;var _2=isc.Canvas,_3=this._logWindow.staticForm;if(_1==this.$f8){_3.setValue(_1,_2._canvasList.length-_2._iscInternalCount)}else{_3.setValue(_1,_2._stats[_1])}}
,isc.A.displayEventTarget=function isc_LogViewer_displayEventTarget(){var _1=isc.EH.lastTarget?isc.EH.lastTarget.getID():"";if(_1==this.$ge)return;this.$ge=_1;if(this.logWindowLoaded()){this._logWindow.staticForm.setValue("currentCanvas",_1)}
var _2=isc.EH.lastEvent.nativeTarget;var _3=(_2?(_2.id||_2.ID||_2.tagName):'none')
if(this.logWindowLoaded()){this._logWindow.staticForm.setValue("nativeTarget",_3)}}
,isc.A.displayFocusTarget=function isc_LogViewer_displayFocusTarget(){var _1=isc.EH.getFocusCanvas(),_2=_1?_1.getID():"";if(_2==this.$gf)return;this.$gf=_2;if(this.logWindowLoaded()){this._logWindow.staticForm.setValue("currentFocusCanvas",_2)}}
,isc.A.displayMouseDownTarget=function isc_LogViewer_displayMouseDownTarget(){var _1=isc.EH.mouseDownEvent.target,_2=_1?_1.getID():"";if(this.logWindowLoaded()){this._logWindow.staticForm.setValue("lastMouseDown",_2);if(isc.AutoTest!=null&&isc.Log.showLocatorOnMouseDown){var _3=isc.AutoTest.getLocator();this._logWindow.staticForm.setValue("autoTestLocator",_3||"none")}}}
,isc.A.updateRPC=function isc_LogViewer_updateRPC(){if(this.logWindowLoaded()&&this._logWindow.RPCTracker)
this._logWindow.RPCTracker.dataChanged()}
,isc.A.evaluate=function isc_LogViewer_evaluate(_1,_2){return isc.Log.evaluate(_1,_2)}
,isc.A.clear=function isc_LogViewer_clear(){if(this.logWindowLoaded())this._logWindow.clearResults()}
);isc.B._maxIndex=isc.C+11;isc.$gg=isc.LogViewer.getGlobalLogCookie();if(isc.$gg!=null){isc.Log.applyLogPriorities(isc.$gg.priorityDefaults)
if(isc.$gg.defaultPriority!=null)
isc.Log.defaultPriority=isc.$gg.defaultPriority}else{isc.Log.setPriority("Log",isc.Log.INFO)}
isc.showConsole=function(_1,_2,_3,_4){isc.showLog(_1,_2,_3,_4)}
isc.addGlobal("showLog",function(_1,_2,_3,_4){isc.Log.show(_1,_2,_3,_4)})
isc.addGlobal("showConsoleInline",function(){isc.Log.show(null,null,null,null,true)});isc.Log.logInfo("initialized");isc.Log.$fy();isc.Log.supportsOnError=(isc.Browser.isIE);if(isc.Log.supportsOnError&&!(window.isc_installOnError==false)){window.onerror=function(_1,_2,_3){var _4=arguments.caller,_5;if(_4==null&&arguments.callee.caller!=null){_5=arguments.callee.caller;_4=_5.arguments}
if(_4&&_4.$e9){return}
var _6="Error:\r\t'"+_1+"'\r\tin "+_2+"\r\tat line "+_3;if(_5!=null&&_4==null&&isc.Browser.isIE&&isc.Browser.version>=9)
{_6+="\r\n    crashed in:  "+isc.Func.getName(_5,true)+"()"+"\r\n    Use a pre-9.0 Internet Explorer for best diagnostics, otherwise Firefox or Chrome"}else if(_4!=null){_6+=isc.Log.getStackTrace(_4)}
isc.Log.logWarn(_6);if(isc.Browser.isIE&&isc.useIEDebugger){if(confirm("Run debugger?\r\r"+_6)){debugger}}}}
isc.$63a=function(){return"["+this.Class+" ID:"+this.ID+" (created by: "+this.componentId+")]"}
isc.$63b=function(_1,_2,_3,_4){var _5=isc.Log;if(!_5)return;if(_1==null)_1=_5.defaultPriority;if(_1<=_5.stackTracePriority&&this.getStackTrace!=null){_2+="\nStack trace:\n"+this.getStackTrace(arguments,2)}
if(!_3)_3=this.Class;_5.log(_1,_2,_3,this.ID+" (created by: "+this.componentId+")",this,_4)}
isc.A=Array;isc.A.ASCENDING=true;isc.A.DESCENDING=false;isc.A=Array;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.shouldSortAscending=function isc_Array_shouldSortAscending(_1){if(_1==Array.ASCENDING)return true;if(_1==Array.DESCENDING)return false;if(isc.isA.String(_1)){if(_1.toLowerCase()=="ascending")return true;if(_1.toLowerCase()=="descending")return false}
return null}
);isc.B._maxIndex=isc.C+1;isc.A=Array.prototype;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.sortByProperty=function isc_Arra_sortByProperty(_1,_2,_3,_4){return this.sortByProperties({property:_1,direction:_2,normalizer:_3,context:_4})}
,isc.A.setSort=function isc_Arra_setSort(_1){var _2=[],_3=[],_4=[],_5=[];for(var i=0;i<_1.length;i++){var _7=_1[i];_2[i]=_7.property;_3[i]=Array.shouldSortAscending(_7.direction);_4[i]=_7.normalizer;_5[i]=_7.context}
return this.sortByProperties(_2,_3,_4,_5)}
,isc.A.sortByProperties=function isc_Arra_sortByProperties(){var _1=isc.$gh,_2=isc.$gi;if(isc.isAn.Array(arguments[0])){this.sortProps=arguments[0];this.sortDirections=arguments[1]||[];this.normalizers=arguments[2]||[];this.contexts=arguments[3]||[]}else{if(!this.sortProps){this.sortProps=[];this.normalizers=[];this.sortDirections=[];this.contexts=[]}else{this.sortProps.clear();this.sortDirections.clear();this.normalizers.clear();this.contexts.clear()}
for(var i=0;i<arguments.length;i++){this.sortProps[i]=arguments[i].property;this.sortDirections[i]=arguments[i].direction;this.normalizers[i]=arguments[i].normalizer;this.contexts[i]=arguments[i].context}}
if(this.sortProps==null||this.sortProps.length==0)return this;var _4=this.sortProps,_5=this.normalizers,_6=this.contexts;var _7=isc.timestamp();for(var i=0;i<_4.length;i++){isc.$506[i]=this.sortDirections[i];var _8=_4[i],_9=_5[i],_10=_6[i];var _11;if(_9==null){_11=this.$gj(_4[i])}else if(isc.isA.String(_9)){_11=_9}
if(_11!=null)_9=Array.$gk(_11);if(_9==null)_9=Array.$gl;this.normalizers[i]=_9;if(this.length==0)continue;_1[i]=[];_2[i]=[];if(isc.isA.Function(_9)){for(var _12=0,l=this.length,_14;_12<l;_12++){_14=this[_12];if(_14==null){isc.$gq=true;continue}
_14.$gm=_12;var _15=_9(_14,this.sortProps[i],_10);_1[i][_12]=_15;if(_11!=null&&!Array.$gn(_14[this.sortProps[i]],_11)){_2[i][_12]=_14[this.sortProps[i]]}
var _16;if(isc.isA.SpecialNumber(_15)&&isNaN(_15)){_1[i][_12]=0-Number.MAX_VALUE}}}else{var _17=this.normalizers[i];for(var _12=0,l=this.length,_14;_12<l;_12++){_14=this[_12];if(_14==null){isc.$gq=true;continue}
var _18=_14[this.sortProps[i]];if(_18==null)_18='';var _15=_17[_18];if(_15==null)_15=_18;_14.$gm=_12;_1[i][_12]=_15}}}
var _19=false;for(var i=0;i<isc.$gi.length;i++){if(isc.$gi[i].length>0){_19=true;break}}
isc.$752=_19;var _20=isc.$gh,_21=isc.$506,_19=isc.$752;var _22=this;_22.compareAscending=Array.compareAscending;_22.compareDescending=Array.compareDescending;var _23=function(_32,_33){var _24=(_32!=null?_32.$gm:null),_25=(_33!=null?_33.$gm:null);for(var i=0;i<_20.length;i++){var _26=_20[i][_24],_27=_20[i][_25];if(_19&&_26!=null&&_27!=null){var _28=isc.$gi,_29=_28[i][_24],_30=_28[i][_25];if(_29!=null&&_30!=null){_26=_29;_27=_30}}
var _31=(_21[i]?_22.compareAscending(_26,_27):_22.compareDescending(_26,_27));if(_31!=0)return _31}
return 0};var _7=isc.timeStamp();this.sort(_23);if(isc.$gq){isc.Log.logWarn("Attempt to sort array by property hit null entry where a record should be. Array:"+isc.Log.echo(this));isc.$gq=null}
this.clearProperty("$gm");_1.clear();_2.clear();isc.$506.clear();this.dataChanged();return this}
,isc.A.unsort=function isc_Arra_unsort(){if(this.sortProps)this.sortProps.clear();return true}
,isc.A.$gj=function isc_Arra__getSortDataType(_1,_2){var _3=(_2!=null?(isc.isAn.Array(_2)?_2:[_2]):this);for(var i=0;i<_3.length;i++){if(!isc.isAn.Object(_3[i]))continue;_2=_3[i][_1];if(_2==null)continue;var _5=Array.$gr(_2);if(_5!=null)return _5}
return null}
,isc.A.$gs=function isc_Arra__getNormalizer(_1,_2){var _3=this.$gj(_1,_2);var _4=Array.$gk(_3);return _4||Array.$gl}
,isc.A.normalize=function isc_Arra_normalize(_1,_2){var _3=(this.normalizer?this.normalizer:this.$gs(_2,_1));return _3(_1,_2)}
);isc.B._maxIndex=isc.C+7;isc.A=Array;isc.A.$gt="$gu";isc.A.$gv="$gw";isc.A=Array;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$a6="string";isc.A.$gx="text";isc.A.$gy="number";isc.A.$gz="integer";isc.A.$g0="float";isc.A.$g1="int";isc.A.$g2="boolean";isc.A._$Date_="Date";isc.A.$g3="Time";isc.A.$68l="datetime";isc.A.$89w="Datetime";isc.A.$g4="date";isc.A.$g5="time";isc.A.$bl="object";isc.A.$g6={"float":"number","int:":"number","integer":"number","text":"string","Date":"date","Time":"date","time":"date"};isc.B.push(isc.A.$gl=function isc_Array__normalizeObj(_1,_2){return _1[_2]}
,isc.A.$g7=function isc_Array__normalizeStr(_1,_2){return(isc.isA.String(_1[_2])?_1[_2].toLowerCase():isc.emptyString)}
,isc.A.$g8=function isc_Array__normalizeNum(_1,_2){var _3=_1[_2];return isc.isA.Number(_3)?_3:(0-Number.MAX_VALUE)}
,isc.A.$g9=function isc_Array__normalizeBool(_1,_2){var _3=_1[_2];if(_3==true)return 1;if(_3==false)return 0;if(_3==null)return-1;return-2}
,isc.A.$ha=function isc_Array__normalizeDate(_1,_2){var _3=(_1[_2]&&isc.isA.Date(_1[_2])?_1[_2].getTime():new Date(_1[_2]).getTime())
if(isNaN(_3))return-8640000000000000;return _3}
,isc.A.$hb=function isc_Array__normalizeTime(_1,_2){var _3=_1[_2];if(!isc.isA.Date(_3)&&_3!=null)_3=isc.Time.parseInput(_3);if(isc.isA.Date(_3))return _3.getTime();return 0}
,isc.A.textToNumericNormalizer=function isc_Array_textToNumericNormalizer(_1,_2){var _3=parseInt(_1[_2],10);if(isc.isA.Number(_3))return _3;else return 0}
,isc.A.$gk=function isc_Array__getNormalizerFromType(_1){if(!_1||!isc.isA.String(_1))return null;switch(_1){case this.$a6:case this.$gx:return Array.$g7;case this.$g2:return Array.$g9;case this._$Date_:case this.$g4:case this.$89w:case this.$68l:return Array.$ha;case this.$g3:case this.$g5:return Array.$hb;case this.$gy:case this.$gz:case this.$g1:case this.$g0:return Array.$g8}
return Array.$gl}
,isc.A.$gr=function isc_Array__getType(_1){var _2=typeof _1;if(_2==this.$bl){if(isc.isA.Date(_1))_2=this.$g4}
return _2}
,isc.A.$gn=function isc_Array__matchesType(_1,_2){var _3=this.$gr(_1);if(_3==_2)return true;return(this.$g6[_2]==_3)}
,isc.A.compareAscending=function isc_Array_compareAscending(_1,_2){if(_1!=null&&_1.localeCompare!=null){var _3=_1.localeCompare(_2);return _3}
if(_2!=null&&_2.localeCompare!=null){var _3=_2.localeCompare(_1);return _3}
return(_2>_1?-1:_2<_1?1:0)}
,isc.A.compareDescending=function isc_Array_compareDescending(_1,_2){if(_1!=null&&_1.localeCompare!=null){var _3=_1.localeCompare(_2);return-1*_3}
if(_2!=null&&_2.localeCompare!=null){var _3=_2.localeCompare(_1);return-1*_3}
return(_2<_1?-1:_2>_1?1:0)}
,isc.A.safariCompareAscending=function isc_Array_safariCompareAscending(_1,_2){if(_1!=null&&_1.localeCompare!=null){var _3=_1.localeCompare(_2);return _3-2}
if(_2!=null&&_2.localeCompare!=null){var _3=_2.localeCompare(_1);return _3-2}
return(_2>_1?-1:_2<_1?1:0)}
,isc.A.safariCompareDescending=function isc_Array_safariCompareDescending(_1,_2){if(_1!=null&&_1.localeCompare!=null){var _3=_1.localeCompare(_2);return-1*(_3-2)}
if(_2!=null&&_2.localeCompare!=null){var _3=_2.localeCompare(_1);return-1*(_3-2)}
return(_2<_1?-1:_2>_1?1:0)}
);isc.B._maxIndex=isc.C+14;isc.$gh=[];isc.$gi=[];isc.$506=[];(function(){if(isc.Browser.isSafari){var b="b";if(b.localeCompare("a")==3){Array.compareAscending=Array.safariCompareAscending;Array.compareDescending=Array.safariCompareDescending}}})();isc.A=Array.prototype;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.max=function isc_Arra_max(_1,_2){if(_1==null)_1=0;if(_2==null)_2=this.length;var _3=null;for(var i=_1;i<_2;i++){var _5=this[i];if(isc.isA.Number(_5)){if(_3==null)_3=_5;else _3=Math.max(_3,_5)}}
return _3}
,isc.A.min=function isc_Arra_min(_1,_2){if(_1==null)_1=0;if(_2==null)_2=this.length;var _3=null;for(var i=_1;i<_2;i++){var _5=this[i];if(isc.isA.Number(_5)){if(_3==null)_3=_5;else _3=Math.min(_3,_5)}}
return _3}
,isc.A.sum=function isc_Arra_sum(_1,_2){if(_1==null)_1=0;if(_2==null)_2=this.length;var _3=0;for(var i=_1;i<_2;i++)
if(isc.isA.Number(this[i]))_3+=this[i];return _3}
,isc.A.and=function isc_Arra_and(_1,_2){if(_1==null)_1=0;if(_2==null)_2=this.length;for(var i=_1;i<_2;i++)
if(!this[i])return false;return true}
,isc.A.or=function isc_Arra_or(_1,_2){if(_1==null)_1=0;if(_2==null)_2=this.length;var _3=0;for(var i=_1;i<_2;i++)
if(this[i])return true;return false}
);isc.B._maxIndex=isc.C+5;isc.getValueForKey=function(_1,_2,_3){if(_2&&_2[_1]!=null&&!isc.isAn.Array(_2))return _2[_1];return(arguments.length<3?_1:_3)}
isc.getKeyForValue=function(_1,_2,_3){if(_2){for(var _4 in _2){if(_2[_4]==_1)return _4}}
return(arguments.length<3?_1:_3)}
isc.makeReverseMap=function(_1){var _2={},_3;for(var _4 in _1){_3=_1[_4];_2[_3]=_4}
return _2}
isc.sortByKey=function(_1){var _2={},_3=isc.getKeys(_1).sort();for(var i=0;i<_3.length;i++){_2[_3[i]]=_1[_3[i]]}
return _2}
isc.sortByValue=function(_1){return isc.makeReverseMap(isc.sortByKey(isc.makeReverseMap(_1)))}
isc.ClassFactory.defineClass("Time");isc.A=isc.Time;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$he=[/^\s*(\d?\d)\s*[: ]\s*(\d?\d)\s*[: ]\s*(\d?\d)?\s*([AaPp][Mm]?)?\s*([+-]\d{2}:\d{2}|Z)?\s*$/,/^\s*(\d?\d)\s*[: ]\s*(\d?\d)(\s*)([AaPp][Mm]?)?\s*([+-]\d{2}:\d{2}|Z)?\s*$/,/^\s*(\d\d)(\d\d)(\d\d)?\s*([AaPp][Mm]?)?\s*([+-]\d{2}:\d{2}|Z)?\s*$/,/^\s*(\d)(\d\d)(\d\d)?\s*([AaPp][Mm]?)?\s*([+-]\d{2}:\d{2}|Z)?\s*$/,/^\s*(\d\d?)(\s)?(\s*)([AaPp][Mm]?)?\s*([+-]\d{2}:\d{2}|Z)?\s*$/];isc.A.formatterMap={toTime:{showSeconds:true,padded:false,show24:false},to24HourTime:{showSeconds:true,padded:false,show24:true},toPaddedTime:{showSeconds:true,padded:true,show24:false},toPadded24HourTime:{showSeconds:true,padded:true,show24:true},toShortTime:{showSeconds:false,padded:false,show24:false},toShort24HourTime:{showSeconds:false,padded:false,show24:true},toShortPaddedTime:{showSeconds:false,padded:true,show24:false},toShortPadded24HourTime:{showSeconds:false,padded:true,show24:true}};isc.A.displayFormat="toTime";isc.A.shortDisplayFormat="toShortTime";isc.A.AMIndicator=" am";isc.A.PMIndicator=" pm";isc.B.push(isc.A.setDefaultDisplayTimezone=function isc_c_Time_setDefaultDisplayTimezone(_1,_2){this.$854=!_2;if(_1==null)return;var _3,_4;if(isc.isA.Number(_1)){_1=-_1;_3=Math.floor(_1/ 60);_4=_1-(_3*60)}else if(isc.isA.String(_1)){var _5=_1.split(":");_3=_5[0];var _6=_3&&_3.startsWith("-");if(_6)_3=_3.substring(1);_4=_5[1];_3=(_6?-1:1)*parseInt(_3,10);_4=(_6?-1:1)*parseInt(_4,10)}
if(isc.isA.Number(_3)&&isc.isA.Number(_4)){this.UTCHoursDisplayOffset=_3;this.UTCMinutesDisplayOffset=_4}}
,isc.A.getDefaultDisplayTimezone=function isc_c_Time_getDefaultDisplayTimezone(){var H=this.UTCHoursDisplayOffset,M=this.UTCMinutesDisplayOffset,_3=H<0;return(!_3?"+":"-")+((_3?-1:1)*H).stringify(2)+":"+((_3?-1:1)*M).stringify(2)}
);isc.B._maxIndex=isc.C+2;isc.A=isc.Time;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$hf=[null,":",null,":"];isc.A.$hg=[null,":"];isc.B.push(isc.A.toTime=function isc_c_Time_toTime(_1,_2,_3){return this.format(_1,_2,false,_3)}
,isc.A.toShortTime=function isc_c_Time_toShortTime(_1,_2,_3){return this.format(_1,_2,true,_3)}
,isc.A.format=function isc_c_Time_format(_1,_2,_3,_4){if(!isc.isA.Date(_1))return _1;var _5=_2;if(!_2&&!isc.isA.String(_2)&&!isc.isA.Function(_2)){_2=_3?this.shortDisplayFormat:this.displayFormat}
if(isc.isA.Function(_2))return _2(_1,_4);if(isc.isA.String(_2))_2=this.formatterMap[_2];if(!isc.isAn.Object(_2)){this.logWarn("Invalid time formatter:"+_5+" - using 'toTime'");_2=this.formatterMap.toTime}
var _6=_2.showSeconds,_7=_2.padded,_8=_2.show24;var _9;if(_4!=null)_9=!_4;else _9=!_1.logicalTime&&!_1.logicalDate;var _10,_11;if(!_9){_10=_1.getHours();_11=_1.getMinutes()}else{var _10=_1.getUTCHours(),_11=_1.getUTCMinutes();var _12=this.$68d(_10,_11,this.getUTCHoursDisplayOffset(_1),this.getUTCMinutesDisplayOffset(_1));_10=_12[0];_11=_12[1]}
var _13=_6?_1.getUTCSeconds():null,_14=_8?null:(_10>=12);if(!_8){if(_10>12)_10=_10-12;if(_10==0)_10=12}
if(_7)_10=_10.stringify(2);var _15=_6?this.$hf:this.$hg;_15[0]=_10;_15[2]=_11.stringify();if(_6)_15[4]=_13.stringify();if(!_8)_15[5]=(_14?this.PMIndicator:this.AMIndicator);else _15[5]=null;return _15.join(isc.emptyString)}
,isc.A.parseInput=function isc_c_Time_parseInput(_1,_2,_3,_4,_5){var _6=0,_7=0,_8=0,_9=0;var _10,_11;if(isc.isA.Date(_1)){_3=true;_6=_1.getUTCHours();_7=_1.getUTCMinutes();_8=_1.getUTCSeconds();_9=_1.getUTCMilliseconds()}else if(_1){for(var i=0;i<isc.Time.$he.length;i++){var _13=isc.Time.$he[i].exec(_1);if(_13)break}
if(_13){var _6=Math.min(parseInt(_13[1]|0,10),23),_7=Math.min(parseInt(_13[2]|0,10),59),_8=Math.min(parseInt(_13[3]|0,10),59),_14=_13[4];;if(_14){if(!this.$hh)this.$hh={p:true,P:true,pm:true,PM:true,Pm:true};if(this.$hh[_14]==true){if(_6<12)_6+=12}else if(_6==12)_6=0}
if(_4&&_13[5]!=null&&_13[5]!=""&&_13[5].toLowerCase()!="z"){var _15=_13[5].split(":"),H=_15[0],_17=H&&H.startsWith("-"),M=_15[1];_10=parseInt(H,10);_11=(_17?-1:1)*parseInt(M,10)}}else if(_2)return null}else if(_2)return null;var _19=_4&&_5!=null?_5.duplicate():new Date(null);if(_4||_3){if(_10==null){_10=_3?0:this.getUTCHoursDisplayOffset(_19)}
if(_11==null){_11=_3?0:this.getUTCMinutesDisplayOffset(_19)}
var _20=this.$68d(_6,_7,(0-_10),(0-_11));_6=_20[0];_7=_20[1];if(_6!=null)_19.setUTCHours(_6);if(_7!=null)_19.setUTCMinutes(_7);if(_8!=null)_19.setUTCSeconds(_8);if(_9!=null)_19.setUTCMilliseconds(_9)}else{if(_6!=null)_19.setHours(_6);if(_7!=null)_19.setMinutes(_7);if(_8!=null)_19.setSeconds(_8);if(_9!=null)_19.setMilliseconds(_9)}
if(!_4)_19.logicalTime=true;return _19}
,isc.A.$68d=function isc_c_Time__applyTimezoneOffset(_1,_2,_3,_4){if(_2==null||_1==null){this.logWarn("applyTimezoneOffset passed null hours/minutes");return[_1,_2]}
if(_3==null)_3=0;if(_4==null)_3=0;if(_3==0&&_4==0)return[_1,_2,0];_1+=_3;_2+=_4;while(_2>=60){_2-=60;_1+=1}
while(_2<0){_2+=60;_1-=1}
var _5=0;while(_1>=24){_1-=24;_5+=1}
while(_1<0){_1+=24;_5-=1}
return[_1,_2,_5]}
,isc.A.createDate=function isc_c_Time_createDate(_1,_2,_3,_4,_5){return this.createLogicalTime(_1,_2,_3,_4,_5)}
,isc.A.createLogicalTime=function isc_c_Time_createLogicalTime(_1,_2,_3,_4,_5){var _6=new Date(null);if(_1==null)_1=0;if(_2==null)_2=0;if(_3==null)_3=0;if(_4==null)_4=0;if(_5){_6.setUTCHours(_1);_6.setUTCMinutes(_2);_6.setUTCSeconds(_3);_6.setUTCMilliseconds(_4)}else{_6.setHours(_1);_6.setMinutes(_2);_6.setSeconds(_3);_6.setMilliseconds(_4)}
_6.logicalTime=true;return _6}
,isc.A.setShortDisplayFormat=function isc_c_Time_setShortDisplayFormat(_1){this.shortDisplayFormat=_1}
,isc.A.setNormalDisplayFormat=function isc_c_Time_setNormalDisplayFormat(_1){this.displayFormat=_1}
,isc.A.compareTimes=function isc_c_Time_compareTimes(_1,_2){if(isc.isA.String(_1))_1=isc.Time.parseInput(_1);if(isc.isA.String(_2))_2=isc.Time.parseInput(_2);if(_1==null&&_2==null)return true;if(!isc.isA.Date(_1)||!isc.isA.Date(_2))return false;return((_1.getUTCHours()==_2.getUTCHours())&&(_1.getUTCMinutes()==_2.getUTCMinutes())&&(_1.getUTCSeconds()==_2.getUTCSeconds()))}
,isc.A.$76r=function isc_c_Time__performDstInit(){var _1=new Date(),_2=new Date(0),_3=new Date(0);_2.setUTCFullYear(_1.getUTCFullYear());_2.setUTCMonth(0);_2.setUTCDate(1);_3.setUTCFullYear(_1.getUTCFullYear());_3.setUTCMonth(6);_3.setUTCDate(1);var _4=_1.getTimezoneOffset();this.januaryDstOffset=_2.getTimezoneOffset();var _5=_3.getTimezoneOffset();this.dstDeltaMinutes=this.januaryDstOffset-_5;if(this.dstDeltaMinutes>0){this.southernHemisphere=false;this.adjustForDST=true;if(_4==_5)this.currentlyInDST=true}else if(this.dstDeltaMinutes<0){this.southernHemisphere=true;this.adjustForDST=true;if(_4==this.januaryDstOffset)this.currentlyInDST=true}else{this.adjustForDST=false}
this.dstDeltaMinutes=Math.abs(this.dstDeltaMinutes);this.dstDeltaHours=Math.floor(this.dstDeltaMinutes/ 60);this.dstDeltaMinutes-=(this.dstDeltaHours*60)}
,isc.A.getUTCHoursDisplayOffset=function isc_c_Time_getUTCHoursDisplayOffset(_1){var _2=this.currentlyInDST?-(this.dstDeltaHours):0;if(this.adjustForDST){if(_1.getTimezoneOffset()==this.januaryDstOffset){if(this.southernHemisphere){_2+=this.dstDeltaHours}}else{if(!this.southernHemisphere){_2+=this.dstDeltaHours}}}
return this.UTCHoursDisplayOffset+(this.adjustForDST?_2:0)}
,isc.A.getUTCMinutesDisplayOffset=function isc_c_Time_getUTCMinutesDisplayOffset(_1){var _2=this.currentlyInDST?-(this.dstDeltaMinutes):0;if(this.adjustForDST){if(_1.getTimezoneOffset()==this.januaryDstOffset){if(this.southernHemisphere){_2+=this.dstDeltaMinutes}}else{if(!this.southernHemisphere){_2+=this.dstDeltaMinutes}}}
return this.UTCMinutesDisplayOffset+(this.adjustForDST?_2:0)}
);isc.B._maxIndex=isc.C+13;isc.Time.$76r();isc.Time.setDefaultDisplayTimezone(new Date().getTimezoneOffset(),true);isc.ClassFactory.defineClass("Page");isc.A=isc.Page;isc.A.$hi=[];isc.A.$hj=false;isc.A.defaultUnsupportedBrowserURL="[SKIN]/unsupported_browser.html";isc.A.$hk={};isc.A.protocolURLs=window.isc_protocolURLs||["http://","https://","file://","mailto:","app-resource:","data:"];isc.A.textDirection=null;isc.A.LTR="ltr";isc.A.RTL="rtl";isc.A=isc.Page;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$hm="[SKIN]";isc.A.$hn="[SKIN]/";isc.A.$ho="[";isc.A.$hp="./";isc.A.$hq="..";isc.A.leaveScrollbarGap=isc.Browser.isMoz&&isc.Browser.geckoVersion<20051107;isc.A.getWidth=(isc.Browser.isNS?function(_1,_2){if(!_1)_1=window;_2=_2||(isc.Browser.isMoz&&isc.Browser.geckoVersion>=20080529);if(isc.Browser.isMoz&&_1==window&&!_2){if(this.width!=null){return this.width-(this.leaveScrollbarGap?(isc.Element?isc.Element.getNativeScrollbarSize():16):0)}
this.logInfo("NOTE: isc.Page.getWidth() unable to determine page width.  Returning 500","sizing");return 500}else{var _3=isc.Browser.geckoVersion!=null&&isc.Browser.geckoVersion<20051111;var _4=!this.leaveScrollbarGap&&!_3&&_1.document.body!=null,_5;if(_4){var _6=isc.Browser.isStrict?_1.document.documentElement:_1.document.body;if(_6!=null)_5=_6.clientWidth}
if(_5==null||_5==0){_5=_1.innerWidth}
if(_1==window)this.width=_5;return _5}}:function(_1){if(!_1)_1=window;var _2=_1.document.body;if(isc.Browser.isStrict&&!isc.Browser.isOpera)
_2=_1.document.documentElement;if(_2){return _2.clientWidth}else{if(!isc.Page.isLoaded()){isc.Page.setEvent("load","isc.EH.$hr()",isc.Page.FIRE_ONCE)}
this.logWarn("NOTE: isc.Page.getWidth() called before <BODY> tag was written out -- "+"value cannot be determined.  Returning 500");return 500}});isc.A.getHeight=(isc.Browser.isNS?function(_1,_2){if(!_1)_1=window;_2=_2||(isc.Browser.isMoz&&isc.Browser.geckoVersion>=20080529);if(isc.Browser.isMoz&&_1==window&&!_2){if(this.height!=null)return this.height;return 500}else{var _3=isc.Browser.isMobileWebkit||(!isc.Browser.isStrict&&isc.Browser.geckoVersion>=20051111)&&_1.document.body!=null,_4;if(_3){_4=_1.document.body.clientHeight}
if(_4==null||_4==0){_4=_1.innerHeight}
if(isc.Browser.isTouch&&(isc.Browser.isAndroid||isc.Browser.isRIM)){if(this.$893==null){this.$893=_4;this.$26u=isc.Page.getWidth(_1,_2);this.$92c=this.getOrientation()}else{if(_4!=this.$893){var _5=this.getOrientation();var _6=isc.Page.getWidth(_1,_2);if(_6!=this.$26u||_5!=this.$92c){this.$92c=_5;this.$893=_4;this.$26u=_6}else{_4=this.$893}}}}
if(_1==window)this.height=_4;return _4}}:function(_1){if(!_1)_1=window;var _2=_1.document.body;if(isc.Browser.isStrict&&!isc.Browser.isOpera)
_2=_1.document.documentElement;if(_2){return _2.clientHeight}else{this.logWarn("NOTE: isc.Page.getHeight() called before <BODY> tag was written out -- value cannot be determined.  Returning 500");return 500}});isc.A.getScrollLeft=(isc.Browser.isNS?function(){return window.pageXOffset}:function(){if(document==null||document.body==null)return 0;return(isc.Browser.isStrict?document.documentElement.scrollLeft:document.body.scrollLeft)});isc.A.getScrollTop=(isc.Browser.isNS?function(){return window.pageYOffset}:function(){if(document==null||document.body==null)return 0;return(isc.Browser.isStrict?document.documentElement.scrollTop:document.body.scrollTop)});isc.A.unsupportedBrowserAction="continue";isc.B.push(isc.A.finishedLoading=function isc_c_Page_finishedLoading(){isc.Page.$hj=true;isc.Log.logInfo("isc.Page is loaded");isc.EH.startIdleTimer();if(isc.Browser.isSafari)isc.Canvas.clearCSSCaches();if(!window.suppressAutoLogWindow){var _1=isc.LogViewer.getLogCookie();if(_1!=null&&_1.keepOpen){isc.Timer.setTimeout("isc.Log.show(true)",1000)}}
if(isc.Time&&isc.Time.UTCHoursOffset!=null){isc.logWarn("This application includes code to set the Time.UTCHoursOffset attribute. "+"This property will be respected but has been deprecated in favor of the "+"classMethod isc.Time.setDefaultDisplayTimezone().");isc.Time.setDefaultDisplayTimezone(isc.Time.UTCHoursOffset.stringify()+":00")}
if(isc.Page.pollPageSize){isc.EH.$hr()}else{isc.EH.delayCall("$hr",[true],200)}}
,isc.A.isLoaded=function isc_c_Page_isLoaded(){return this.$hj}
,isc.A.getBlankFrameURL=function isc_c_Page_getBlankFrameURL(){if(isc.Browser.isIE&&("https:"==window.location.protocol||document.domain!=location.hostname)){return this.getURL("[HELPERS]empty.html")}
return"about:blank"}
,isc.A.setTitle=function isc_c_Page_setTitle(_1){document.title=_1}
,isc.A.setDirectories=function isc_c_Page_setDirectories(_1){if(_1==null){_1={imgDir:window.imgDir,isomorphicDir:(window.isomorphicDir?window.isomorphicDir:window.IsomorphicDir),isomorphicClientDir:window.isomorphicClientDir,isomorphicDocsDir:window.isomorphicDocsDir,skinDir:window.skinDir,helperDir:window.helperDir}}
this.$hs();this.setIsomorphicDir(_1.isomorphicDir);this.setIsomorphicClientDir(_1.isomorphicClientDir);this.setIsomorphicDocsDir(_1.isomorphicDocsDir);this.setAppImgDir(_1.imgDir);this.setSkinDir(_1.skinDir);this.setHelperDir(_1.helperDir)}
,isc.A.$hs=function isc_c_Page__deriveAppDir(){var _1=window.location.href;if(_1.contains("?"))_1=_1.substring(0,_1.indexOf("?"));if(_1.contains("#"))_1=_1.substring(0,_1.indexOf("#"));if(_1.charAt(_1.length-1)!="/"){_1=_1.substring(0,_1.lastIndexOf("/")+1)}
this.$hk.APP=_1;if(this.logIsInfoEnabled()){this.logInfo("app dir is "+this.$hk.APP)}
this.setAppImgDir()}
,isc.A.getAppDir=function isc_c_Page_getAppDir(){return this.$hk.APP}
,isc.A.setAppImgDir=function isc_c_Page_setAppImgDir(_1){this.$hk.APPIMG=this.combineURLs(this.getAppDir(),_1!=null?_1:"[APP]images/")}
,isc.A.getAppImgDir=function isc_c_Page_getAppImgDir(_1){if(_1!=null&&(isc.startsWith(_1,isc.slash)||this.getProtocol(_1)!=isc.emptyString))
{return _1}
if(_1)return this.$hk.APPIMG+_1;else return this.$hk.APPIMG}
,isc.A.setAppFilesDir=function isc_c_Page_setAppFilesDir(_1){this.$hk.APPFILES=this.combineURLs(this.getAppDir(),_1)}
,isc.A.getAppFilesDir=function isc_c_Page_getAppFilesDir(_1){return this.$hk.APPFILES}
,isc.A.setIsomorphicDir=function isc_c_Page_setIsomorphicDir(_1){this.$hk.ISOMORPHIC=this.combineURLs(this.getAppDir(),_1!=null?_1:"../isomorphic/");this.setIsomorphicClientDir();this.setIsomorphicDocsDir()}
,isc.A.getIsomorphicDir=function isc_c_Page_getIsomorphicDir(){return this.$hk.ISOMORPHIC}
,isc.A.setSkinDir=function isc_c_Page_setSkinDir(_1){this.$hk.SKIN=this.combineURLs(this.getAppDir(),_1!=null?_1:"[ISOMORPHIC]/skins/standard/");this.$hk.SKINIMG=this.$hk.SKIN+"images/";if(isc.Canvas)isc.Canvas.$ht=isc.Canvas.$hu=null}
,isc.A.getSkinDir=function isc_c_Page_getSkinDir(){return this.$hk.SKIN}
,isc.A.getSkinImgDir=function isc_c_Page_getSkinImgDir(_1){if(_1==null)return this.$hk.SKINIMG;return this.combineURLs(this.$hk.SKIN,_1)}
,isc.A.setIsomorphicClientDir=function isc_c_Page_setIsomorphicClientDir(_1){this.$hk.ISOMORPHIC_CLIENT=this.combineURLs(this.getAppDir(),_1!=null?_1:"[ISOMORPHIC]/system/");this.setSkinDir();this.setHelperDir()}
,isc.A.getIsomorphicClientDir=function isc_c_Page_getIsomorphicClientDir(){return this.$hk.ISOMORPHIC_CLIENT}
,isc.A.setIsomorphicDocsDir=function isc_c_Page_setIsomorphicDocsDir(_1){this.$hk.ISOMORPHIC_DOCS=this.combineURLs(this.getAppDir(),_1!=null?_1:"[ISOMORPHIC]/system/reference/");this.setIsomorphicDocsSkinDir()}
,isc.A.getIsomorphicDocsDir=function isc_c_Page_getIsomorphicDocsDir(){return this.$hk.ISOMORPHIC_DOCS}
,isc.A.setIsomorphicDocsSkinDir=function isc_c_Page_setIsomorphicDocsSkinDir(_1){this.$hk.ISO_DOCS_SKIN=this.combineURLs(this.getIsomorphicDocsDir(),_1!=null?_1:"skin/")}
,isc.A.getIsomorphicDocsSkinDir=function isc_c_Page_getIsomorphicDocsSkinDir(){return this.$hk.ISO_DOCS_SKIN}
,isc.A.setHelperDir=function isc_c_Page_setHelperDir(_1){this.$hk.HELPERS=this.combineURLs(this.getAppDir(),_1!=null?_1:"[ISOMORPHIC_CLIENT]/helpers/")}
,isc.A.getHelperDir=function isc_c_Page_getHelperDir(){return isc.Page.$hk.HELPERS}
,isc.A.getImgURL=function isc_c_Page_getImgURL(_1,_2){var _3;if(isc.startsWith(_1,this.$hm)){_3=isc.Page.getSkinImgDir(_2);var _4=isc.startsWith(_1,this.$hn)?7:6;_1=_1.substring(_4)}else{_3=isc.Page.getAppImgDir(_2)}
return isc.Page.combineURLs(_3,_1)}
,isc.A.getURL=function isc_c_Page_getURL(_1){if(isc.startsWith(_1,this.$ho)){var _2=_1.indexOf("]");if(_2>0){var _3=_1.substring(1,_2).toUpperCase(),_4=isc.Page.$hk[_3];if(_4!=null){_1=isc.Page.combineURLs(_4,_1.substring(_2+(_1.charAt(_2+1)!="/"?1:2)))}else{this.logDebug("getURL("+_1+"): couldn't find cached directory "+_3)}}else{this.logDebug("getURL("+_1+"): didn't find matching ']' in URL")}}
return _1}
,isc.A.combineURLs=function isc_c_Page_combineURLs(_1,_2){if(!isc.isA.String(_2))return _1;if(isc.startsWith(_2,this.$ho)){return this.getURL(_2)}
var _3=isc.$ad;if(_1==null||_1==_3||isc.Page.getProtocol(_2)!=_3){return _2}
var _4=isc.slash;var _5=isc.Page.getProtocol(_1);if(isc.startsWith(_2,_4)){if(isc.isAn.emptyString(_5)){_1=isc.emptyString}else if(_1.indexOf(_4,_5.length)!=-1){_1=_1.substring(0,_1.indexOf(_4,_5.length))}}else if(_2.indexOf(this.$hp)>-1){_1=_1.substring(_5.length,_1.length-1);var _6=_1.split(_4),_7=_2.split(_4);var _8=_6[0];_6.shift();while(_7[0]==isc.dot||_7[0]==this.$hq){if(_7[0]==isc.dot){_7.shift();continue}
_7.shift();if(_6.length==0)break;_6.pop()}
_1=_5+_8+_4;if(_6.length>0)_1+=_6.join(_4)+_4;_2=_7.join(_4)}
return _1+_2}
,isc.A.getProtocol=function isc_c_Page_getProtocol(_1){for(var i=0;i<isc.Page.protocolURLs.length;i++){if(isc.startsWith(_1,isc.Page.protocolURLs[i]))return isc.Page.protocolURLs[i]}
return isc.$ad}
,isc.A.getLastSegment=function isc_c_Page_getLastSegment(_1){if(_1==null)return isc.emptyString;var _2=_1.lastIndexOf(isc.slash);if(_2==-1)return _1;return _1.substring(_2+1)}
,isc.A.isXHTML=function isc_c_Page_isXHTML(){if(this.$hv!=null)return this.$hv;if(isc.Browser.isIE)return false;var _1=this.getWindow();return(this.$hv=(this.getDocument().constructor==this.getWindow().XMLDocument))}
,isc.A.isRTL=function isc_c_Page_isRTL(){return this.getTextDirection()==isc.Canvas.RTL}
,isc.A.getTextDirection=function isc_c_Page_getTextDirection(){if(this.textDirection==null){var _1=document.documentElement,_2=document.body,_3=(_2?_2.dir:null)||_1.dir;if(_3)return(this.textDirection=_3.toLowerCase());else if(_2)return(this.textDirection=this.LTR);return this.LTR}
return this.textDirection}
,isc.A.loadStyleSheet=function isc_c_Page_loadStyleSheet(_1,_2,_3){var _4=isc.Page.getURL(_1);var _5="<link rel='stylesheet' type='text/css' href=\""+_4+"\"\/>";if(_2==null)_2=window;if(isc.Page.isLoaded()&&_2==window){if(isc.FileLoader){var _6=isc.FileLoader.$hw;if(_6!=null){for(var i=0;i<_6.length;i++){if(_4.indexOf(_6[i])!=-1){this.logDebug("skin "+_6[i]+" already loaded by FileLoader - not loading css file");return}}}
isc.FileLoader.loadCSSFile(_4,_3)}else{this.logWarn("isc.Page.loadStylesheet('"+_1+"') called after page load.  Stylesheet not loaded.")}}else{if(this.isXHTML()){var _8=this.getDocument(),_9=_8.documentElement.firstChild,_10=_8.createElementNS(_8.documentElement.namespaceURI,"link");_10.rel="stylesheet";_10.type="text/css";_10.href=_4;_9.appendChild(_10);this.logWarn("added stylesheet DOM style")}else{_2.document.write(_5)}}}
,isc.A.resizeTo=function isc_c_Page_resizeTo(_1,_2){window.resizeTo(_1,_2)}
,isc.A.moveTo=function isc_c_Page_moveTo(_1,_2){window.moveTo(_1,_2)}
,isc.A.scrollTo=function isc_c_Page_scrollTo(_1,_2){window.scroll(_1,_2)}
,isc.A.getOrientation=function isc_c_Page_getOrientation(){if(window.orientation!=null){return window.orientation==0||window.orientation==180?"portrait":"landscape"}
return this.getWidth()>this.getHeight()?"landscape":"portrait"}
,isc.A.updateViewport=function isc_c_Page_updateViewport(_1,_2,_3,_4){var _5=[];if(_1!=null){if(isc.isA.Number(_1))_1=_1.toFixed(2);_5[_5.length]=("initial-scale="+_1)}
if(_2!=null)_5[_5.length]=("width="+_2);if(_3!=null)_5[_5.length]=("height="+_3);if(_4!=null){_5[_5.length]=("user-scalable="+(_4==false?"no":"yes"));if(_4==false&&_1!=null){_5[_5.length]="minimum-scale="+_1+", maximum-scale="+_1}}
_5=_5.join(", ");var _6=document.getElementsByTagName("meta"),_7;for(var i=_6.length-1;i>=0;i--){if(_6[i].name=="viewport"){_7=_6[i];_7.parentNode.removeChild(_7);_7=null}}
if(_7!=null){_7.content=_5}else{_7=document.createElement('meta');_7.name='viewport';_7.content=_5;document.getElementsByTagName('head')[0].appendChild(_7)}}
,isc.A.getScrollWidth=function isc_c_Page_getScrollWidth(_1){var _1=_1||document;if(_1==null||_1.body==null)return 500;if(isc.Browser.isIE&&isc.Browser.version>=6){return Math.max(_1.body.scrollWidth,_1.documentElement.clientWidth)}
return _1.body.scrollWidth}
,isc.A.getScrollHeight=function isc_c_Page_getScrollHeight(_1){var _1=_1||document;if(_1==null||_1.body==null){return this.getHeight()}
var _2=_1.body.scrollHeight;if(isc.Browser.isStrict){var _3=_1.documentElement;if(_3){var _4=_3.scrollHeight;if(!isc.isA.Number(_3.scrollHeight)){_4=Math.max(_3.offsetHeight,_3.clientHeight)}
if(_4&&_4>_2){_2=_4}}}
return _2}
,isc.A.getScreenWidth=function isc_c_Page_getScreenWidth(){return screen.width}
,isc.A.getScreenHeight=function isc_c_Page_getScreenHeight(){return screen.height}
,isc.A.getWindowRect=function isc_c_Page_getWindowRect(_1){if(!_1)_1=window;return{left:(isc.Browser.isIE||isc.Browser.isOpera?_1.screenLeft:_1.screenX),top:(isc.Browser.isIE||isc.Browser.isOpera?_1.screenTop:_1.screenY),width:isc.Page.getWidth(_1),height:isc.Page.getHeight(_1)}}
,isc.A.setUnloadMessage=function isc_c_Page_setUnloadMessage(_1){if(_1==null)window.onbeforeunload=null;else window.onbeforeunload=function(){return _1}}
,isc.A.goBack=function isc_c_Page_goBack(){if(history.length==0&&window.opener){window.close()}else{history.back()}}
,isc.A.print=function isc_c_Page_print(_1){if(!_1)_1=window;if(_1.print){_1.print()}else{var _2=_1.document;if(!_2||!_2.body){this.logError("isc.Page.print() called on a window that doesn't have a document.body defined.  Exiting.");return}
if(isc.Browser.isWin){_2.body.insertAdjacentHTML('beforeEnd','<OBJECT ID="printControl" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>');var _3=_2.all.printControl;if(!_3){this.logError("isc.Page.print() couldn't create or find print control.  Exiting.");return}
_3.ExecWB(6,1);_3.outerHTML=""}else{alert("Choose 'Print...' from the File menu to print this page.")}}}
,isc.A.observe=function isc_c_Page_observe(_1,_2,_3){var _4=isc.Class.create();return _4.observe(_1,_2,_3)}
,isc.A.waitFor=function isc_c_Page_waitFor(_1,_2,_3,_4,_5){var _6=isc.Class.create({$545:_1,$546:_2,$547:_3,_fired:function(_8){if(this.$59i){isc.Timer.clear(this.$59i)}
this.ignore(this.$545,this.$546);this.fireCallback(this.$547,"observed",[_8]);this.destroy()},$59j:function(){this.ignore(this.$545,this.$546);this.fireCallback(this.$59k);this.destroy()}});isc.ClassFactory.addGlobalID(_6);var _7=_6.observe(_1,_2,_6.getID()+"._fired(observed)");if(!_7)_6.destroy();if(_4&&_5){_6.$59k=_5;_6.$59i=isc.Timer.setTimeout(function(){_6.$59j()},_4)}
return _7}
,isc.A.waitForMultiple=function isc_c_Page_waitForMultiple(_1,_2,_3,_4){var _5=true;var _6=isc.Class.create({$59l:_1,$59m:[],$547:_2,$59n:function(_9){this.$59m.remove(_9);if(this.$59m.isEmpty()){if(this.$59i){isc.Timer.clear(this.$59i)}
this.fireCallback(this.$547);this.destroy()}},$59j:function(){var _7=this.$59m;for(var i=0;i<_7.length;i++){_7[i].ignore(_7[i].$545,_7[i].$546);_7[i].destroy()}
this.fireCallback(this.$59k);this.destroy()}});for(var i=0;i<_1.length;i++){var _9=isc.Class.create({$545:_1[i].object,$546:_1[i].method,$59o:_6,_fired:function(_11){this.ignore(this.$545,this.$546);this.$59o.$59n(this);this.destroy()}});isc.ClassFactory.addGlobalID(_9);var _10=_9.observe(_1[i].object,_1[i].method,_9.getID()+"._fired(observed)");if(_10){_6.$59m.add(_9)}else{_9.destroy();_5=false}}
if(_3&&_4){_6.$59k=_4;_6.$59i=isc.Timer.setTimeout(function(){_6.$59j()},_3)}
return _5}
,isc.A.checkBrowserAndRedirect=function isc_c_Page_checkBrowserAndRedirect(_1){if(!isc.Browser.isSupported){if(isc.Log){isc.Log.logWarn("Unsupported browser detected - userAgent:"+navigator.userAgent)}
if(this.unsupportedBrowserAction=="continue")return;var _2=this.unsupportedBrowserAction=="confirm"&&confirm(this.getUnsupportedBrowserPromptString())
if(_2)return;if(_1==null)_1=isc.Page.defaultUnsupportedBrowserURL;var _3=true;window.location.replace(isc.Page.getURL(_1))}}
,isc.A.getUnsupportedBrowserPromptString=function isc_c_Page_getUnsupportedBrowserPromptString(){var _1="This page uses the Isomorphic SmartClient web presentation layer "+"(Version"+isc.version+" - "+isc.buildDate+"). The web browser you are using is not supported by this version of SmartClient"+" and you may encounter errors on this page. Would you like to continue anyway?\n\n"+"(Reported userAgent string for this browser:"+navigator.userAgent+")";return _1}
);isc.B._maxIndex=isc.C+51;if(isc.Page.isXHTML())isc.nbsp=isc.xnbsp;isc.Page.setDirectories();if(isc.Browser.isMoz){isc.Page.getWidth(null,true);isc.Page.getHeight(null,true)}
isc.addGlobal("Params",function(_1){if(!_1)_1=window;var _2=isc.isA.String(_1)?_1:_1.location.href;var _3=_2.indexOf("?"),_4=_2.indexOf("#");if(_4<0||_4<_3)_4=_2.length;if(_3!=-1){var _5=_2.substring(_3+1,_4).split("&");for(var i=0,_7,_8;i<_5.length;i++){_7=_5[i];if(!_7)continue;_8=_7.indexOf("=");this[_7.substring(0,_8)]=unescape(_7.substring(_8+1))}}})
isc.params=new isc.Params();isc.getParams=function(_1){return new isc.Params(_1)}
isc.ClassFactory.defineClass("Comm");isc.A=isc.Comm;isc.A.sendMethod="POST";isc.A.$hz=0;isc.A=isc.Comm;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.xmlHttpConstructors=["MSXML2.XMLHTTP","Microsoft.XMLHTTP","MSXML.XMLHTTP","MSXML3.XMLHTTP"];isc.A.$50c=[];isc.B.push(isc.A.$h0=function isc_c_Comm__fireXMLCallback(_1,_2){isc.EH.$h1("XRP");isc.Class.fireCallback(_2,"xmlHttpRequest",[_1],null,true);isc.EH.$h2()}
,isc.A.$h3=function isc_c_Comm__getStateChangeHandler(){return function(){var _1=arguments.callee.request;if(!_1)return;if(_1.readyState!=4)return;arguments.callee.request=null;isc.Timer.setTimeout({target:isc.Comm,methodName:"$h0",args:[_1,arguments.callee.callback]},0)}}
,isc.A.createXMLHttpRequest=function isc_c_Comm_createXMLHttpRequest(){if(isc.Browser.isIE){var _1;if(this.preferNativeXMLHttpRequest){_1=this.getNativeRequest();if(!_1)_1=this.getActiveXRequest()}else{_1=this.getActiveXRequest();if(!_1)_1=this.getNativeRequest()}
if(!_1)isc.rpc.logWarn("Couldn't create XMLHttpRequest");return _1}else{return new XMLHttpRequest()}}
,isc.A.getNativeRequest=function isc_c_Comm_getNativeRequest(){var _1;if(isc.Browser.version>=7){isc.rpc.logDebug("Using native XMLHttpRequest");_1=new XMLHttpRequest()}
return _1}
,isc.A.getActiveXRequest=function isc_c_Comm_getActiveXRequest(){var _1;if(!this.$h4){for(var i=0;i<this.xmlHttpConstructors.length;i++){try{var _3=this.xmlHttpConstructors[i];_1=new ActiveXObject(_3);if(_1){this.$h4=_3;break}}catch(e){}}}else{_1=new ActiveXObject(this.$h4)}
if(_1)isc.rpc.logDebug("Using ActiveX XMLHttpRequest via constructor: "+this.$h4);return _1}
,isc.A.sendScriptInclude=function isc_c_Comm_sendScriptInclude(_1){var _2=_1.URL,_3=_1.fields,_4=_1.data,_5=_1.callbackParam,_6=_1.transaction;var _7="_scriptIncludeReply_"+_6.transactionNum;this[_7]=function(){var _8=arguments.length==1?arguments[0]:[];if(arguments.length>1){for(var i=0;i<arguments.length;i++)_8[i]=arguments[i]}
isc.Comm.performScriptIncludeReply(_6.transactionNum,_8)}
var _10="isc.Comm."+_7;var _11={};_11[_5?_5:"callback"]=_10;_2=isc.rpc.addParamsToURL(_2,_3);_2=isc.rpc.addParamsToURL(_2,_11);if(_6)_6.mergedActionURL=_2;isc.rpc.logInfo("scriptInclude call to: "+_2);this.$50c[_6.transactionNum]=_6.callback;var _12=this.getDocument(),_13=this.getDocumentBody(),_14=_12.createElement("script");_14.src=_2;_13.appendChild(_14)}
,isc.A.performScriptIncludeReply=function isc_c_Comm_performScriptIncludeReply(_1,_2){delete this["_scriptIncludeReply_"+_1];var _3=this.$50c[_1];delete this.$50c[_1];this.logDebug("scriptInclude reply for transactionNum: "+_1+", data: "+this.echoLeaf(_2),"xmlBinding");this.fireCallback(_3,"transactionNum,results,wd",[_1,_2])}
,isc.A.sendXmlHttpRequest=function isc_c_Comm_sendXmlHttpRequest(_1){var _2=_1.URL,_3=_1.fields,_4=_1.httpMethod,_5=_1.contentType,_6=_1.httpHeaders,_7=_1.data,_8=_1.transaction,_9=_1.blocking!=null?_1.blocking:false;this.$50c[_8.transactionNum]=_8.callback;var _10="isc.Comm.performXmlTransactionReply("+_8.transactionNum+", xmlHttpRequest)";if(!_4)_4="POST";var _11=this.createXMLHttpRequest();var _12;if(isc.Browser.isIE){_12=this.$h3();_12.request=_11;_12.callback=_10}else{_12=function(){if(_11.readyState!=4)return;isc.Comm.$h0(_11,_10)}}
_11.onreadystatechange=_12;if(isc.rpc.logIsDebugEnabled()){this.lastXmlHttpRequest=_11}
if(_4=="POST"||_4=="PUT"){if(_7){_5=_5||"text/xml";_2=isc.rpc.addParamsToURL(_2,_3)}else{_5=_5||"application/x-www-form-urlencoded; charset=UTF-8";_7=isc.SB.create();var _13=true;for(var _14 in _3){if(!_13)_7.append("&");var _15=_3[_14];_7.append(isc.rpc.encodeParameter(_14,_15));_13=false}
_7=_7.toString()}
if(isc.rpc.logIsDebugEnabled()){isc.rpc.logDebug("XMLHttpRequest POST to "+_2+" contentType: "+_5+" with body -->"+decodeURIComponent(_7)+"<--")}
_11.open(_4,_2,!_9);_11.setRequestHeader("Content-Type",_5);this.$h5(_11,_6);if(_8){_8.xhrHeaders=_6;_8.xhrData=_7}
if(_7!=null&&!isc.isA.String(_7)){this.logWarn("Non-string data object passed to sendXML as request.data:"+this.echo(_7)+" attempting to convert to a string.");_7=_7.toString?_7.toString():""+_7}
_11.send(_7)}else{var _16=isc.rpc.addParamsToURL(_2,_3);_11.open(_4,_16,!_9);if(_1.bypassCache){_11.setRequestHeader("If-Modified-Since","Thu, 01 Jan 1970 00:00:00 GMT")}
this.$h5(_11,_6);if(isc.rpc.logIsDebugEnabled()){isc.rpc.logDebug("XMLHttpRequest GET from "+_2+" with fields: "+isc.Log.echoAll(_3)+" full URL string: "+_16)}
_11.send(null)}
return _11}
,isc.A.performXmlTransactionReply=function isc_c_Comm_performXmlTransactionReply(_1,_2){var _3=this.$50c[_1]
delete this.$50c[_1];this.fireCallback(_3,"transactionNum,results,wd",[_1,_2])}
,isc.A.$h5=function isc_c_Comm__setHttpHeaders(_1,_2){if(_2==null)return;for(var _3 in _2){var _4=_2[_3];if(_4!=null)_1.setRequestHeader(_3,_4)}}
);isc.B._maxIndex=isc.C+10;isc.ClassFactory.defineClass("Timer");isc.A=isc.Timer;isc.A.$ii=null;isc.A.listEvent={action:null,iterationInterval:null,iterationsRemaining:0,$ij:null,$ik:null};isc.A.MSEC=1;isc.A.SEC=1000;isc.A.MIN=60000;isc.A.HOUR=3600000;isc.A.DEFAULT_TIMEOUT_LENGTH=100;isc.A.$il=null;isc.A=isc.Timer;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$im=["isc.Timer.$in('",null,"')"];isc.A.$io=0;isc.A.$ip={};isc.A.$iq="TMR";isc.A.$612=5000;isc.B.push(isc.A.setTimeout=function isc_c_Timer_setTimeout(_1,_2,_3,_4){if(_1==null)return;if(_1.action!=null){_2=_1.delay;_3=_1.units;_1=_1.action}
if(_3==null)_3=isc.Timer.MSEC;if(_2==null)_2=isc.Timer.DEFAULT_TIMEOUT_LENGTH;_2=_2*_3;var _5="$ir"+this.$io++;this.$im[1]=_5;this[_5]=_1;if(this.logIsDebugEnabled("traceTimers"))
{_1.timerTrace=this.getStackTrace(null,1,null,true)}
var _6=this.$im.join(isc.emptyString);var _7=setTimeout(_6,_2);this.$ip[_7]=_5;return _7}
,isc.A.$in=function isc_c_Timer__fireTimeout(_1){if(isc.$611!=null){if(this.logIsInfoEnabled()){this.logInfo("timer ID:"+_1+" fired during eval. Delaying until this "+"thread completes")}
if(!this.$613)this.$613=isc.timeStamp();if((isc.timeStamp()-this.$613)>this.$612){this.logWarn("timer ID:"+_1+" fired during eval thread lasting more than "+this.$612+"ms. Thread may have caused an "+"error and failed to complete. Allowing delayed action to fire.");delete isc.$611}else{this.$im[1]=_1;var _2=this.$im.join(isc.emptyString);var _3=setTimeout(_2,0);if(!this.$614)this.$614={};this.$614[_1]=_3;return}}
delete this.$613;var _4=this[_1];delete this[_1];var _5=this.$ip;for(var i in _5){if(_5[i]=_1){delete _5[i];break}}
var _7=this.$614;if(_7){for(var i in _7){if(_7[i]=_1){delete _7[i];break}}}
if(_4==null)return;isc.EH.$h1(this.$iq);arguments.timerTrace=_4.timerTrace;this.fireCallback(_4,null,null,null,true);isc.EH.$h2()}
,isc.A.clear=function isc_c_Timer_clear(_1){if(isc.isAn.Array(_1))
for(var i=0;i<_1.length;i++)this.clear(_1[i]);else{var _3=this.$ip[_1];delete this[_3]
delete this.$ip[_1];if(this.$614&&this.$614[_3]){_1=this.$614[_3];delete this.$614[_3]}
clearTimeout(_1)}
return null}
,isc.A.clearTimeout=function isc_c_Timer_clearTimeout(_1){return this.clear(_1)}
);isc.B._maxIndex=isc.C+4;isc.A=isc.Page;isc.A.$is={};isc.A.$it=0;isc.A.FIRE_ONCE="once";isc.A.$iu={};isc.A=isc.Page;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$cp="ID";isc.B.push(isc.A.setEvent=function isc_c_Page_setEvent(_1,_2,_3,_4){if(isc.isA.String(_2)){if(_1==isc.EH.LOAD||_1==isc.EH.IDLE||_1==isc.EH.RESIZE||_1==isc.EH.ORIENTATION_CHANGE)
{_2=new Function("target,eventInfo",_2)}else{_2=isc.Func.expressionToFunction("target,eventInfo",_2)}}
if(this.logIsDebugEnabled()){this.logDebug("setEvent("+_1+"): action => "+(isc.isA.Function(_2)?isc.Func.getShortBody(_2):_2))}
var _5=isc.Page.$it++,_6={action:_2,functionName:_4,fireStyle:_3,ID:_5};var _7=this.$is;if(!isc.isAn.Array(_7[_1]))_7[_1]=[];_7[_1].add(_6);if(_1==isc.EH.IDLE){isc.EventHandler.startIdleTimer()}
return _5}
,isc.A.clearEvent=function isc_c_Page_clearEvent(_1,_2){if(_2==null){this.$is[_1]=[]}else{if(this.$iv==_1){var _3=this.$is[_1],_4=isc.isA.Array(_3)?_3.findIndex(this.$cp,_2):-1;if(_4!=-1)_3[_4]=null}else{if(isc.isA.Array(this.$is[_1]))
this.$is[_1].removeWhere(this.$cp,_2)}}}
,isc.A.$iw=function isc_c_Page__getPageEventName(_1){var _2=this.$ix=this.$ix||{};if(!_2[_1]){_2[_1]="page"+_1.charAt(0).toUpperCase()+_1.substring(1)}
return _2[_1]}
,isc.A.handleEvent=function isc_c_Page_handleEvent(_1,_2,_3){if(_2==isc.EH.UNLOAD)isc.Canvas.$iy();var _4=isc.Page.$is[_2];if(!isc.isAn.Array(_4)||_4.length==0)return true;var _5=this.$iw(_2);var _6=true;this.$iv=_2;for(var i=0,_8=_4.length;_6&&(i<_8);i++){var _9=_4[i];if(!_9)continue;if(_9.fireStyle==isc.Page.FIRE_ONCE)_4[i]=null;if(this.logIsDebugEnabled()){this.logDebug("handleEvent("+_2+"): firing action => "+isc.Func.getShortBody(_9.action))}
if(isc.isA.Function(_9.action)){_6=(_9.action(_1,_3)!=false)}else{var _10=_9.action;if(!_10||_10.destroyed){_4[i]=null;continue}
var _11=_9.functionName||_5;if(isc.isA.Function(_10[_11])){_6=(_10[_11](_1,_3)!=false)}}}
this.$iv=null;this.$is[_2].removeEmpty();return _6}
,isc.A.actionsArePendingForEvent=function isc_c_Page_actionsArePendingForEvent(_1){return(isc.isAn.Array(this.$is[_1])&&this.$is[_1].length!=0)}
,isc.A.registerKey=function isc_c_Page_registerKey(_1,_2,_3){if(_1==null||_2==null)return;var _4=_1,_5,_6,_7,_8;if(isc.isAn.Object(_1)){_4=_1.keyName;_5=_1.ctrlKey;_6=_1.shiftKey;_7=_1.altKey;_8=_1.metaKey}
if(_4.length==1)_4=_4.toUpperCase();var _9=false;for(var i in isc.EH.$iz){if(isc.EH.$iz[i]==_4){_9=true;break}}
if(!_9){this.logWarn("Page.registerKey() passed unrecognized key name '"+_1+"'. Not registering","events");return}
var _11=this.$iu;if(!_11[_4])_11[_4]=[];_11[_4].add({target:_3,action:_2,ctrlKey:_5,shiftKey:_6,altKey:_7,metaKey:_8})}
,isc.A.unregisterKey=function isc_c_Page_unregisterKey(_1,_2){if(!this.$iu[_1]){isc.Log.logInfo("Page.unregisterKey(): No events registered for key "+isc.Log.echo(_1)+".","events");return false}
this.$iu[_1].removeWhere("target",_2)}
,isc.A.handleKeyPress=function isc_c_Page_handleKeyPress(){var _1=isc.EH,_2=_1.getKey(),_3=this.$iu;if(!_3[_2])return true;var _4=_3[_2],_5=_4.duplicate(),_6=_5.length,_7=true;for(var i=0;i<_6;i++){var _9=_5[i];if(!_4.contains(_9))continue;if(_9.ctrlKey!=null&&_9.ctrlKey!=_1.ctrlKeyDown())continue;if(_9.altKey!=null&&_9.altKey!=_1.altKeyDown())continue;if(_9.shiftKey!=null&&_9.shiftKey!=_1.shiftKeyDown())continue;if(_9.metaKey!=null&&_9.metaKey!=_1.metaKeyDown())continue;if(_9.action!=null&&!isc.isA.Function(_9.action)){isc.Func.replaceWithMethod(_9,"action","key,target")}
_7=((_9.action(_2,_9.target)!=false)&&_7)}
return _7}
);isc.B._maxIndex=isc.C+8;isc.ClassFactory.defineClass("EventHandler");isc.EH=isc.Event=isc.EventHandler;isc.A=isc.EventHandler;isc.A.lastEvent={};isc.A.$i0=[];isc.A.$i1=[];isc.A.passThroughEvents=true;isc.A.maskNativeTargets=true;isc.A.STILL_DOWN_DELAY=100;isc.A.DOUBLE_CLICK_DELAY=500;isc.A.IDLE_DELAY=10;isc.A.STOP_BUBBLING="***STOP***";isc.A.ALL_EDGES=["T","L","B","R","TL","TR","BL","BR"];isc.A.eventTypes={MOUSE_DOWN:"mouseDown",RIGHT_MOUSE_DOWN:"rightMouseDown",MOUSE_MOVE:"mouseMove",MOUSE_UP:"mouseUp",SHOW_CONTEXT_MENU:"showContextMenu",CLICK:"click",DOUBLE_CLICK:"doubleClick",MOUSE_OUT:"mouseOut",MOUSE_STILL_DOWN:"mouseStillDown",MOUSE_OVER:"mouseOver",TOUCH_START:"touchStart",TOUCH_MOVE:"touchMove",TOUCH_END:"touchEnd",TOUCH_CANCEL:"touchCancel",LONG_TOUCH:"longTouch",SET_DRAG_TRACKER:"setDragTracker",GET_DRAG_DATA:"getDragData",RELEASE_DRAG_DATA:"releaseDragData",DRAG_START:"dragStart",DRAG_STOP:"dragStop",DRAG_MOVE:"dragMove",DRAG_OUT:"dragOut",DRAG_REPOSITION_START:"dragRepositionStart",DRAG_REPOSITION_MOVE:"dragRepositionMove",DRAG_REPOSITION_STOP:"dragRepositionStop",DRAG_RESIZE_START:"dragResizeStart",DRAG_RESIZE_MOVE:"dragResizeMove",DRAG_RESIZE_STOP:"dragResizeStop",DROP_OVER:"dropOver",DROP_MOVE:"dropMove",DROP_OUT:"dropOut",DROP:"drop",KEY_DOWN:"keyDown",KEY_UP:"keyUp",KEY_PRESS:"keyPress",MOUSE_WHEEL:"mouseWheel",SELECT_START:"selectStart",SELECTION_CHANGE:"selectionChange",FOCUS_IN:"focusIn",FOCUS_OUT:"focusOut",IDLE:"idle",LOAD:"load",UNLOAD:"unload",RESIZE:"resize",ORIENTATION_CHANGE:"orientationChange"};isc.A.$i2={mousemove:"mouseMove",mousedown:"mouseDown",mouseup:"mouseUp",contextmenu:"contextMenu",mousewheel:"mouseWheel",selectionchange:"selectionChange",DOMMouseScroll:"mouseWheel",mouseMove:"mouseMove",mouseDown:"mouseDown",mouseUp:"mouseUp",mouseWheel:"mouseWheel",touchstart:"touchStart",touchmove:"touchMove",touchend:"touchEnd",touchStart:"touchStart",touchMove:"touchMove",touchEnd:"touchEnd",selectionstart:"selectionStart",selectionStart:"selectionStart",selectionchange:"selectionChange",selectionChange:"selectionChange"};isc.A.$92d={READY_FOR_TOUCH:"ready",TOUCH_STARTED:"started",TOUCH_COMPLETE:"complete"};isc.A.$i3="event,eventInfo";isc.A.DRAG_RESIZE="dragResize";isc.A.DRAG_REPOSITION="dragReposition";isc.A.DRAG_SCROLL="dragScroll";isc.A.DRAG_SELECT="dragSelect";isc.A.DRAG="drag";isc.A.NONE="none";isc.A.TRACKER="tracker";isc.A.TARGET="target";isc.A.OUTLINE="outline";isc.A.INTERSECT_WITH_MOUSE="mouse";isc.A.INTERSECT_WITH_RECT="rect";isc.A.dragTargetShadowDepth=10;isc.A.$i4={A:true,AREA:true};isc.A.$i5={INPUT:true,TEXTAREA:true,SELECT:true,OPTION:true};isc.A.$i6="LABEL";isc.A.$i7={keydown:"keyDown",keyup:"keyUp",keypress:"keyPress",contextmenu:"contextMenu"};isc.A.$i8={Backspace:8,Tab:9,Shift:16,Ctrl:17,Alt:18,Pause_Break:19,Caps_Lock:20,Page_Up:33,Page_Down:34,End:35,Home:36,Arrow_Left:37,Arrow_Up:38,Arrow_Right:39,Arrow_Down:40,Insert:45,Delete:46,Meta:91,f1:112,f2:113,f3:114,f4:115,f5:116,f6:117,f7:118,f8:119,f9:120,f10:121,f11:122,f12:123,Num_Lock:144,Scroll_Lock:145};isc.A.$iz={'0':'$i9','8':'Backspace','9':'Tab','13':'Enter','16':'Shift','17':'Ctrl','18':'Alt','19':'Pause_Break','20':'Caps_Lock','27':'Escape','32':'Space','33':'Page_Up','34':'Page_Down','35':'End','36':'Home','37':'Arrow_Left','38':'Arrow_Up','39':'Arrow_Right','40':'Arrow_Down','44':'Print_Screen','45':'Insert','46':'Delete','48':'0',"49":"1","50":"2","51":"3","52":"4","53":"5","54":"6","55":"7","56":"8","57":"9",'58':';','59':';','60':',','61':'=','62':"/",'65':'A','66':'B','67':'C','68':'D','69':'E','70':'F','71':'G','72':'H','73':'I','74':'J','75':'K','76':'L','77':'M','78':'N','79':'O','80':'P','81':'Q','82':'R','83':'S','84':'T','85':'U','86':'V','87':'W','88':'X','89':'Y','90':'Z','91':'Meta','92':'Meta','93':'Menu','96':'0','97':'1','98':'2','99':'3','100':'4','101':'5','102':'6','103':'7','104':'8','105':'9','106':'*','107':'+','109':'-','110':'.','111':'/','112':'f1','113':'f2','114':'f3','115':'f4','116':'f5','117':'f6','118':'f7','119':'f8','120':'f9','121':'f10','122':'f11','123':'f12','144':'Num_Lock','145':'Scroll_Lock','160':'Shift','161':'Shift','162':'Ctrl','163':'Ctrl','164':'Alt','165':'Alt','186':';','187':'=','188':',','189':'-','190':'.','191':'/','192':'`','219':'[','220':'\\','221':']','222':"'",'224':"Meta"};isc.A.$ja={'8':'Backspace','9':'Tab','13':'Enter','27':'Escape','32':'Space','33':'1','34':"'",'35':'3','36':'4','37':'5','38':'7','39':"'",'40':'9','41':'0','42':'8','43':'=','44':',','45':'-','46':'.','47':'/','48':'0','49':'1','50':'2','51':'3','52':'4','53':'5','54':'6','55':'7','56':'8','57':'9','58':';','59':';','60':',','61':'=','62':'.','63':'/','64':'2','65':'A','66':'B','67':'C','68':'D','69':'E','70':'F','71':'G','72':'H','73':'I','74':'J','75':'K','76':'L','77':'M','78':'N','79':'O','80':'P','81':'Q','82':'R','83':'S','84':'T','85':'U','86':'V','87':'W','88':'X','89':'Y','90':'Z','91':'[','92':'\\','93':']','94':'6','95':'-','96':'`','97':'A','98':'B','99':'C','100':'D','101':'E','102':'F','103':'G','104':'H','105':'I','106':'J','107':'K','108':'L','109':'M','110':'N','111':'O','112':'P','113':'Q','114':'R','115':'S','116':'T','117':'U','118':'V','119':'W','120':'X','121':'Y','122':'Z','123':'[','124':'\\','125':']','126':'`'};isc.A.$jb={'3':"Enter",'25':"Tab",'63232':"Arrow_Up",'63233':"Arrow_Down",'63234':"Arrow_Left",'63235':"Arrow_Right",'64236':"f1",'64237':"f2",'64238':"f3",'64239':"f4",'64240':"f5",'64241':"f6",'64242':"f7",'64243':"f8",'64244':"f9",'64245':"f10",'64246':"f11",'63247':"f12",'63273':"Home",'63275':"End",'63276':"Page_Up",'63277':"Page_Down"};isc.A.$jc={};isc.A.dynamicBackMask=false;isc.A.alwaysBackMask=false;isc.A.dragTrackerDefaults={ID:"isc_dragTracker",width:10,height:10,offsetX:-10,offsetY:-10,autoDraw:false,visibility:"hidden",overflow:"visible",cursor:"arrow"};isc.EventHandler.addClassProperties(isc.EventHandler.eventTypes)
isc.A=isc.EventHandler;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$je="f10";isc.A.$20w="Escape";isc.A.$85t=[];isc.A.$85z={};isc.A.$jf="Tab";isc.A.$70a={keydown:true,keyup:true,keypress:true};isc.A.$jg="IMG";isc.A.$jh="progid:DXImageTransform.Microsoft.AlphaImageLoader";isc.A.longTouchDelay=500;isc.A.$ji={};isc.A.$jj="BODY";isc.A.$jk="HTML";isc.A.$jl="eventProxy";isc.A.$jm="[object Text]";isc.A.$jn={autoDraw:false,$jo:true,$jp:false,overflow:"hidden",visibility:"hidden",$jq:false,getTarget:function(){return this.$jr},show:function(){var _1=this.masterElement;this.moveAbove(_1);return this.Super("show",arguments)}};isc.A.$js=isc.Browser.isIE?isc.undef:true;isc.A.$jt="handleNativeEvents";isc.A.$49r="APPLET";isc.A.$ju={mouseMove:true,mouseOver:true,mouseOut:true};isc.A.$41z="selectionChange";isc.A.currentOrientation=isc.Page.getOrientation();isc.A.$jv="$jw";isc.A.$jx=0;isc.A.$jy={load:"LOD",mousedown:"MDN",mouseup:"MUP",mousemove:"MMV",mouseout:"MOU",touchstart:"TDN",touchmove:"TMVP",touchend:"TUP",contextmenu:"CXT",keypress:"KPR",keydown:"KDN",keyup:"KUP",resize:"RSZ"};isc.A.$jz="nativeEvents";isc.A.$j0="event";isc.A.$j1="if (!isc.Browser.isIE && event == null) return;"+(isc.Browser.isMoz?"if(event.getPreventDefault&&event.getPreventDefault())return;":isc.Browser.isSafari?"if(event.returnValue==false)return;":"")+"var returnVal=arguments.callee.$ch.isc.EH.dispatch(arguments.callee.$j2,event);"+(!isc.Browser.isIE&&isc.Browser.isDOM?"if(returnVal==false)event.preventDefault();else if(returnVal==isc.EH.STOP_BUBBLING)event.stopPropogation();":"")+"return returnVal;";isc.A.$j3={};isc.A.$j4={onmousedown:"mousedown",onmouseup:"mouseup",onclick:"click",ondblclick:"dblclick",oncontextmenu:"contextmenu",onmousewheel:"mousewheel",onmouseover:"mouseover",onmouseout:"mouseout",onmousemove:"mousemove",onresize:"resize",onload:"load",onunload:"unload",onselecttext:"selecttext",onselectionchanged:"selectionchanged",onkeydown:"keydown",onkeyup:"keyup",onkeypress:"keypress"};isc.A.$649={};isc.A.getMouseEventProperties=(isc.Browser.isIE?function(_1){var _2=this.lastEvent;if(!_1)_1=this.getWindow().event;_2.DOMevent=_1;_2.eventType=this.$i2[_1.type];_2.y=parseInt(_1.clientY)+this.ns.Page.getScrollTop();_2.x=parseInt(_1.clientX);if(!isc.Page.isRTL()){_2.x+=this.ns.Page.getScrollLeft()}else{var _3=this.ns.Page.getScrollLeft();if(_3>0){var _4=(this.ns.Page.getScrollWidth()-this.ns.Page.getWidth());_2.x-=(_4-_3)}
_2.x-=15}
_2.nativeTarget=_1.srcElement;var _5=_1.wheelDelta;if(_5!=null)_2.wheelDelta=-Math.round(_5/ 120);else _2.wheelDelta=null;_2.screenX=_1.screenX;_2.screenY=_1.screenY;_2.buttonNum=_1.button;_2.shiftKey=(_1.shiftKey==true);_2.ctrlKey=(_1.ctrlKey==true);_2.altKey=(_1.altKey==true);_2.metaKey=(_1.metaKey==true);_2.target=this.getEventTargetCanvas(_1,_2.nativeTarget,_2);return _2}:function(_1){var _2=this.lastEvent;_2.DOMevent=_1;_2.eventType=this.$i2[_1.type];var _3=false;if(isc.Browser.isMobileWebkit){if(isc.startsWith(_2.eventType,"touch")){if(_2.eventType==isc.EH.TOUCH_END){_3=true}else if(_1.touches!=null&&_1.touches[0]!=null){var _4=_1.touches[0];_2.clientX=_4.clientX;_2.clientY=_4.clientY;_2.screenX=_4.screenX;_2.screenY=_4.screenY;_2.x=_4.pageX;_2.y=_4.pageY}}else if(isc.Browser.isAndroid&&_1.type==isc.EH.CLICK){_2.screenX=_1.screenX;_2.screenY=_1.screenY;_2.x=parseInt(_1.clientX)+isc.Page.getScrollLeft();_2.y=parseInt(_1.clientY)+isc.Page.getScrollTop()}}else{_2.screenX=_1.screenX;_2.screenY=_1.screenY;if(isc.Browser.isSafari){var _5=isc.Browser.safariVersion>=523.12
_2.x=parseInt(_1.x);_2.y=parseInt(_1.y);if(_5){_2.x+=isc.Page.getScrollLeft();_2.y+=isc.Page.getScrollTop()}
if(_2.eventType!=this.MOUSE_WHEEL){var _5=true;_2.x=parseInt(_1.clientX)+(_5?isc.Page.getScrollLeft():0);_2.y=parseInt(_1.clientY)+(_5?isc.Page.getScrollTop():0)}}else{if(_2.eventType!=this.MOUSE_WHEEL){var _5=true;_2.x=parseInt(_1.clientX)+(_5?isc.Page.getScrollLeft():0);_2.y=parseInt(_1.clientY)+(_5?isc.Page.getScrollTop():0)}}}
_2.nativeTarget=_1.target;_2.$j5=null;_2.target=this.getEventTargetCanvas(_1,_2.nativeTarget,_2);if(_2.eventType==this.MOUSE_WHEEL){var _6=_1.wheelDelta,_7=_1.detail;if(_7==0||_7==null&&_6!=null){_2.wheelDelta=-Math.round(_6/ 120);if(!isc.isA.Number(_2.wheelDelta))_2.wheelDelta=null}else{if(isc.Canvas.useNativeWheelDelta&&_1.detail==_1.SCROLL_PAGE_UP){_2.wheelDelta=-Math.floor(_2.target.height/ isc.Canvas.scrollWheelDelta)}else if(isc.Canvas.useNativeWheelDelta&&_1.detail==_1.SCROLL_PAGE_DOWN){_2.wheelDelta=Math.floor(_2.target.height/ isc.Canvas.scrollWheelDelta)}else{var _8=_1.detail/ 3;if(!isc.isA.Number(_8))_8=0;if(_8>15||_8<-15)_8=(_8>0?1:-1);_2.wheelDelta=_8}}}else{_2.wheelDelta=null}
if(_2.eventType==isc.EH.MOUSE_MOVE||_2.eventType==isc.EH.TOUCH_MOVE){if(!this.$j6)_2.buttonNum=0}else if(isc.Browser.isTouch){if(_1.targetTouches&&_1.targetTouches.length>1){_2.buttonNum=2}else{_2.buttonNum=1}}else{_2.buttonNum=((_1.which==1||isc.Browser.isSafari&&_1.which==65536)?1:2)}
_2.shiftKey=(_1.shiftKey==true);_2.ctrlKey=(_1.ctrlKey==true);_2.altKey=(_1.altKey==true);_2.metaKey=(_1.metaKey==true);return _2});isc.A.$688="f1";isc.A.$689="help";isc.A.HARD="hard";isc.A.SOFT="soft";isc.A.SOFT_CANCEL="softCancel";isc.A.$j7=0;isc.A.clickMaskRegistry=[];isc.A.$cp='ID';isc.B.push(isc.A.handleSyntheticEvent=function isc_c_EventHandler_handleSyntheticEvent(_1){var _2=_1.target;_1.$49s=true;if(_2){_1.clientX+=_2.getPageLeft();_1.clientY+=_2.getPageTop();if(isc.Browser.isIE){_1.clientX+=_2.getLeftMargin()+_2.getLeftBorderSize()+_2.getLeftPadding()+2;_1.clientY+=_2.getTopMargin()+_2.getRightBorderSize()+_2.getTopPadding()+2}
switch(_1.type){case"mouseup":this.handleMouseUp(_1);break;case"mousedown":this.handleMouseDown(_1);break;case"mousemove":this.handleMouseMove(_1);break}}}
,isc.A.handleEvent=function isc_c_EventHandler_handleEvent(_1,_2,_3){this.$77e=_2;var _4=isc.EH;var _5;if(isc.Page.handleEvent(_1,_2,_3)==false){_5=false}else if(_4.targetIsEnabled(_1)&&_4.bubbleEvent(_1,_2,_3)==false){_5=false}else{_5=true}
delete this.$77e;return _5}
,isc.A.handleLoad=function isc_c_EventHandler_handleLoad(_1){if(isc.SA_Page)isc.SA_Page.$o();if(!isc.Browser.isMoz){if(isc.EH.$j8()&&document&&document.body){document.body.addEventListener("unload",isc.EH.handleUnload,false)}
return(isc.Page.handleEvent(null,isc.EH.LOAD)!=false)}else{try{return(isc.Page.handleEvent(null,isc.EH.LOAD)!=false)}catch(e){isc.Log.$am(e);throw e;}}}
,isc.A.handleUnload=function isc_c_EventHandler_handleUnload(_1){var _2=isc.EH;var _3=(isc.Page.handleEvent(null,_2.UNLOAD)!=false);if(_3==true){this.releaseEvents()}
return _3}
,isc.A.$j9=function isc_c_EventHandler__handleNativeKeyDown(_1,_2){if(!isc.Page.isLoaded())return false;var _3=isc.EH;var _4=_3.lastEvent;if(!_1)_1=_3.getWindow().event;_3.getKeyEventProperties(_1);if(isc.Browser.isIE&&_4.keyName==this.$688&&!_2){return}
var _5=isc.Browser.isIE&&_3.$i8[_4.keyName]!=null;var _6=true;var _7=_4.keyName,_8=_4.characterValue;var _9=_3.$85t.duplicate();for(var i=0;i<_9.length;i++){var _11=_9[i];if(_11==null||_11==_7)break;_4.characterValue=null;_4.keyName=_11;this.handleKeyPress();_3.$85z[_4.keyName]=true}
_4.keyName=_7;_4.characterValue=_8;if(!_5&&_3.$85t.indexOf(_4.keyName)!=-1){_6=_3.handleKeyPress();_3.$85z[_4.keyName]=true}else{_6=_3.handleKeyDown(_1)}
_3.$85t[_3.$85t.length]=_4.keyName;_3.$885=_4.ctrlKey;_3.$886=_4.altKey;if(_6!=false&&(_5||(isc.Browser.isMoz&&_4.keyName==this.$je&&this.shiftKeyDown())))
{_6=_3.handleKeyPress(_1);_3.$85z[_4.keyName]=true}
if(_6==false){this.cancelKeyEvent(_1)}
return _6}
,isc.A.handleKeyDown=function isc_c_EventHandler_handleKeyDown(_1,_2){var _3=isc.EH,_4=_3.lastEvent,_5;var _6=_3.eventHandledNatively(_4.eventType,_4.nativeKeyTarget);if(_6)_5=_3.$js;if(_2!=null)isc.addProperties(_4,_2);if(!_6){var _7=[_4,_4.target,_4.keyName];var _8=_4.keyTarget;if(_8==null)_8=this.getEventTargetCanvas(_1,_4.nativeKeyTarget);if(_3.targetIsEnabled(_8))
_5=(_3.bubbleEvent(_8,_3.KEY_DOWN,_7)!=false)}
var _9=false;if(_3.$ke&&_3.$ke.useSyntheticTabIndex)
{_9=true}
if(_5!=false&&(_9||this.clickMaskUp())&&_4.keyName==this.$jf)
{var _10,_11=this.clickMaskRegistry;for(var i=_11.length-1;i>=0;i--){if(this.isHardMask(_11[i])){_10=_11[i];break}}
if(_9||_10)_5=false;if(_9){this.logInfo("Widget:"+_3.$ke+" explicitly marked as requesting synthetic "+"tab index mgmt. Intercepting keyDown to achieve this.","syntheticTabIndex");_3.syntheticTabSource=_3.$ke}}
return _5}
,isc.A.$kb=function isc_c_EventHandler__handleNativeKeyUp(_1){if(!isc.Page.isLoaded())return false;var _2=isc.EH,_3=_2.lastEvent;if(!_1)_1=_2.getWindow().event;_2.getKeyEventProperties(_1);_2.$885=_3.ctrlKey
_2.$886=_3.altKey
_2.$85z[_3.keyName]=null;if(_2.$85t.indexOf(_2.lastEvent.keyName)!=-1){if(_2.handleKeyPress(_1)==false){this.cancelKeyEvent(_1);return false}}
var _4=_2.handleKeyUp(_1)
return _4}
,isc.A.handleKeyUp=function isc_c_EventHandler_handleKeyUp(_1,_2){var _3=isc.EH,_4=_3.lastEvent,_5=[_4,_4.target,_4.keyName];if(_3.eventHandledNatively(_4.eventType,_4.nativeKeyTarget)){return _3.$js}
var _6=true;if(_2!=null){isc.addProperties(_4,_2)}
var _7=_4.keyTarget;if(_7==null)_7=this.getEventTargetCanvas(_1,_4.nativeKeyTarget);if(_3.targetIsEnabled(_7))
_6=(_3.bubbleEvent(_7,_3.KEY_UP,_5)!=false);if(!isc.Browser.isMac&&_4.keyName==_3.$je&&_3.shiftKeyDown()&&isc.Menu&&isc.Menu.$kc&&isc.Menu.$kc.length>0)
{_6=false}
_3.clearKeyEventProperties(_4.keyName);return _6}
,isc.A.$kd=function isc_c_EventHandler__handleNativeKeyPress(_1){if(!isc.Page.isLoaded())return false;var _2=isc.EH;var _3=_2.lastEvent,_4=_2.KEY_PRESS;if(!_1)_1=_2.getWindow().event;_2.getKeyEventProperties(_1);_3.eventType=_4;_3.ctrlKey=_2.$885;_3.altKey=_2.$886;if(_2.$85z[_3.keyName]==true){_2.$85z[_3.keyName]=null;return}
var _5=_2.handleKeyPress(_1);if(_5==false){this.cancelKeyEvent(_1)}
return _5}
,isc.A.cancelKeyEvent=function isc_c_EventHandler_cancelKeyEvent(_1){if(isc.Browser.isIE||isc.Browser.isSafari){if(this.$70a[_1.type]==true){try{_1.keyCode=0}catch(e){}}}}
,isc.A.handleKeyPress=function isc_c_EventHandler_handleKeyPress(_1,_2){var _3=isc.EH,_4=_3.lastEvent,_5=_3.KEY_PRESS;if(_2!=null){isc.addProperties(_4,_2)}
var _6={keyName:_4.keyName,characterValue:_4.characterValue};_4.eventType=_5;_3.$85t.removeAt(0);if(isc.Page.handleEvent(_4.keyTarget,_5)==false)return false;var _7=(_3.eventHandledNatively(_5,_4.nativeKeyTarget));if(_7!==false){_3.logDebug("keyPress handled natively");return _3.$js}else{_3.logDebug("keyPress not handled natively")}
var _8=_4.keyTarget;if(_8==null)_8=this.getEventTargetCanvas(_1,_4.nativeKeyTarget);if(_3.targetIsEnabled(_8)){var _9=_3.bubbleEvent(_8,_4.eventType,_6)
if(_9==false)return false}
if(_9!=_3.STOP_BUBBLING&&isc.Page.handleKeyPress()==false)return false;if((_3.syntheticTabSource||this.clickMaskUp())&&_4.keyName==this.$jf){var _10,_11=this.clickMaskRegistry;for(var i=_11.length-1;i>=0;i--){if(this.isHardMask(_11[i])){_10=_11[i];break}}
if(_3.syntheticTabSource){this.logInfo("Telling canvas:"+_3.syntheticTabSource+" [marked as 'useSyntheticTabIndex:true'] to shift focus","syntheticTabIndex")
_3.syntheticTabSource.delayCall("$kf",[!this.shiftKeyDown(),_10]);delete _3.syntheticTabSource;return false}else if(_10!=null){var _13=_3.$ke;if(_13!=null){this.logInfo("Telling focus canvas:"+_13+" to shift focus","syntheticTabIndex")
_13.$kf(!this.shiftKeyDown(),_10)}else{if(this.shiftKeyDown()){this.logInfo("Putting focus into last widget in response to Tab keydown","syntheticTabIndex")
this.$kg(_10)}else{this.logInfo("Putting focus into first widget in response to Tab keydown","syntheticTabIndex")
this.$kh(_10)}}
return false}}
if((isc.Browser.isIE||isc.Browser.isMoz)&&_4.keyName==isc.EH.$je&&isc.EH.shiftKeyDown()){var _14=this.handleContextMenu(_1);if(_14){this.$ki=true}
return _14}
if(isc.Browser.isMoz&&isc.RPCManager&&isc.RPCManager.$410.length>0&&_4.keyName==isc.EH.$20w)
{return false}
return true}
,isc.A.$kh=function isc_c_EventHandler__focusInFirstWidget(_1){var _2=this.$kj;if(_2){if((!_1||!this.targetIsMasked(_2,_1))&&!_2.isDisabled()&&_2.$kk())
{_2.focusAtEnd(true)}else{_2.$kf(true,_1)}}}
,isc.A.$kg=function isc_c_EventHandler__focusInLastWidget(_1){var _2=this.$kl;if(_2){if((!_1||!this.targetIsMasked(_2,_1))&&!_2.isDisabled()&&_2.$kk())
{_2.focusAtEnd()}else{_2.$kf(false,_1)}}}
,isc.A.handleMouseDown=function isc_c_EventHandler_handleMouseDown(_1,_2){if(isc.Browser.isTouch&&!_2)return;var _3=isc.EH;_3.$km=true;var _4=_3.doHandleMouseDown(_1,_2);_3.$km=false;return _4}
,isc.A.doHandleMouseDown=function isc_c_EventHandler_doHandleMouseDown(_1,_2){if(!isc.Page.isLoaded())return false;var _3=this;_3.$j6=true;_3.$92e=null;var _4=_2||_3.getMouseEventProperties(_1);var _5=_3.$ke,_6=_5!=null&&(_5!=_4.target)&&!_5._useNativeTabIndex&&!_5._useFocusProxy&&!(isc.isA.DynamicForm!=null&&isc.isA.DynamicForm(_5)&&_5.getFocusSubItem()&&_5.getFocusSubItem().hasFocus);if(_6){if(isc.Browser.isIE){var _7=_3.$ke.getID();if(_3.$ko==null)
_3.$ko=["if (",_7," && ",_7,".hasFocus)",_7,".blur()"]
else
_3.$ko[1]=_3.$ko[3]=_3.$ko[5]=_7;isc.Timer.setTimeout(_3.$ko.join(isc.emptyString),0)}else{_3.$ke.blur()}}
_3.mouseDownEvent=isc.addProperties({},_4);var _8=_4.target;var _9=(_3.clickMaskClick(_8)==false);if(_9){_3.$kp=true;return false}else{_3.$kp=false}
var _10=_3.rightButtonDown()?_3.RIGHT_MOUSE_DOWN:_3.MOUSE_DOWN;if(isc.Page.handleEvent(_8,_10)==false){return false}
if(_3.eventHandledNatively(_10,_4.nativeTarget))
return _3.$js;if(!_3.targetIsEnabled(_8))return false;var _11;if(_8&&!_8.hasFocus){if(((isc.Browser.isMoz&&_8.canSelectText)||isc.Browser.isSafari)&&_8._useFocusProxy)
{_3.focusInCanvas(_8)}else if(!_8._useNativeTabIndex){_8.focus("focus on mousedown")}else if(isc.Browser.isMoz||isc.Browser.isSafari){_8.focus("focus on mousedown")}else if(isc.Browser.isIE){var _12=_4.nativeTarget;if(isc.Browser.isStrict){_11=_8}else{if(_12&&_12.tagName==this.$jg){var _13=_12.style,_14=_13?_13.filter:null;if(_14.contains(this.$jh)){_11=_8}}}}}
if(_8)_3.prepareForDragging(_8);var _15=_3.bubbleEvent(_8,_10,null,_9);if(_11!=null)_11.focus();if(_15==false){delete _3.dragTarget;delete _3.dragTargetLink}
if(_3.rightButtonDown()){if(!this.useSyntheticRightButtonEvents())return true;if(_8&&(_3.getBubbledProperty(_8,"contextMenu")||_3.getBubbledProperty(_8,"showContextMenu")!=isc.Canvas.getInstanceProperty("showContextMenu")))
{_4.returnValue=false;return false}
return true}
if(_15!=false){if(_3.hasEventHandler(_8,_3.MOUSE_STILL_DOWN)){_3.$kq()}}
var _16=_3.dragTarget!=null&&_3.dragOperation!=_3.DRAG_SELECT;var _17=(!_16&&(!(isc.Browser.isMoz||isc.Browser.isSafari)||!!_8.$kr(_4)));return _17}
,isc.A.stillWithinMouseDownTarget=function isc_c_EventHandler_stillWithinMouseDownTarget(){var _1=this.mouseDownTarget();if(!_1)return false;var _2=this.lastEvent;var _3=(_1==_2.target);if(!_3)return false;if(_2.$j5!=null)return _2.$j5;if(!(isc.Browser.isMoz&&_1.$ks))
{return _3}
var x=_2.x,y=_2.y,_6=_1.visibleAtPoint(x,y,true);if(!_6&&_1.$kt!=null){for(var i=0;i<_1.$kt.length;i++){_6=_1.$kt[i].visibleAtPoint(_2.x,_2.y,true);if(_6)break}}
_2.$j5=_6;return _6}
,isc.A.handleMouseMove=function isc_c_EventHandler_handleMouseMove(_1){if(isc.Browser.isTouch)return;if(!isc.Page.isLoaded())return false;var _2=isc.EH;if(_2.$km||_2.$ku)return;var _3=_2.getMouseEventProperties(_1);if((isc.Browser.isMoz||isc.Browser.isIE)&&!_2.immediateMouseMove){if(_2.delayedMouseMoveTimer==null){_2.delayedMouseMoveTimer=isc.Timer.setTimeout({target:_2,methodName:"$kv",args:[isc.timeStamp()]},0,true)}
_2.$kw=0;return true}
var _4=isc.timeStamp();var _5=_2.$kx(_1,_3);_2.$kw=isc.timeStamp()-_4;return _5}
,isc.A.$kv=function isc_c_EventHandler__delayedMouseMove(_1){this.delayedMouseMoveTimer=null;this.$kx(null,this.lastEvent)}
,isc.A.$kx=function isc_c_EventHandler__handleMouseMove(_1,_2){this.$ky=true;var _3=this.$kz(_1,_2);this.$ky=null;return _3}
,isc.A.$kz=function isc_c_EventHandler___handleMouseMove(_1,_2){var _3=this;var _4;if(isc.Browser.isIE){var _5=_3.$j6,_6=_2.buttonNum;if(_5){if(_6==0){_4=true}}else if(_6==1&&_2.eventType==_3.MOUSE_MOVE){if(_3.$92e){}else{_2.eventType=_3.MOUSE_DOWN;_3.handleMouseDown(null,_2);_2.eventType=_3.MOUSE_MOVE}}}
var _7=_2.target,_8=_3.eventHandledNatively(_3.MOUSE_MOVE,_2.nativeTarget);if(_3.$k0){_3.handleMouseUp(_1,true)}else if(_4){_3.logInfo("sythesizing mouseUp "+(_3.$k1?"due to mouseUp outside window,":"[buttonNum cleared on mouseMove with no mouseUp event],")+" buttonNum: "+_2.buttonNum);_3.handleMouseUp(_1,true)}
delete _3.$k1;var _9=_3.mouseIsDown();if(isc.Browser.isMoz&&(isc.Browser.geckoVersion<20100914)&&_9&&_2.target&&_2.target.$ks&&_2.target!=_3.mouseDownTarget())
{_2.nativeDraggingTarget=_2.nativeTarget;_2.nativeTarget=null;_7=_2.target=_3.mouseDownTarget()}
if(_9&&_3.dragTarget&&!_3.dragging&&(Math.abs(_2.x-_3.mouseDownEvent.x)>_3.dragTarget.dragStartDistance||Math.abs(_2.y-_3.mouseDownEvent.y)>_3.dragTarget.dragStartDistance))
{_3.handleDragStart(_2)}
if(_3.dragging){return _3.handleDragMove()}
if(_3.rightButtonDown()){if(!isc.Browser.isMac||!_3.ctrlKeyDown())return true}
if(_9){_7=_3.stillWithinMouseDownTarget()?_3.mouseDownTarget():null}else{_7=_2.target}
if(_7!=_3.lastMoveTarget){if(this.logIsDebugEnabled()){this.logDebug((_3.lastMoveTarget?"mousing out of "+_3.lastMoveTarget+"  ":"")+(_7?"mousing over "+_7:""))}
var _10=_3.lastMoveTarget,_11,_12=_3.lastHoverTarget;if(_10){_3.handleEvent(_10,_3.MOUSE_OUT)}
if(_7){_3.handleEvent(_7,_3.MOUSE_OVER);_11=_7.getHoverTarget(_2)}
if(_11!=_12){if(_12)_12.stopHover();if(_11)_11.startHover();_3.lastHoverTarget=_11}
_3.lastMoveTarget=_7}
if(isc.Page.handleEvent(_7,_3.MOUSE_MOVE)==false)return false;if(_8)return _3.$js;if(!_3.targetIsEnabled(_7))return false;_3.bubbleEvent(_7,_3.MOUSE_MOVE);if(_7)_7.$k2();return true}
,isc.A.getNativeMouseTarget=function isc_c_EventHandler_getNativeMouseTarget(_1){if(!this.nativeTargetWarningLogged){this.nativeTargetWarningLogged=true;this.logWarn("getNativeMouseTarget(). This method will return the DOM element "+"the browser reports as the target or source of the current mouse event. "+"Please note that SmartClient cannot guarantee that the same element will "+"be reported in all browser/platform configurations for all event types. "+"If you wish to make use of this value, we recommend testing your use case "+"in all target browser configurations.")}
if(_1==null)_1=this.lastEvent;return _1.nativeTarget||_1.nativeDraggingTarget}
,isc.A.handleNativeMouseOut=function isc_c_EventHandler_handleNativeMouseOut(_1){if(isc.Browser==null)return;var _2=isc.EH;if(_2.$km||_2.$ku)return;var _3=(_1?_1:_2.getWindow().event),_4=(isc.Browser.isDOM?_3.target:_3.srcElement),_5=false;if(isc.Browser.isIE){_5=(_3.toElement==null)}else{_5=(_3.relatedTarget==null)}
if(_5)_2.$k1=true;if(_5&&_2.lastMoveTarget!=null){_2.$k3(_3);_2.handleEvent(_2.lastMoveTarget,_2.MOUSE_OUT);_2.lastMoveTarget=null;if(_2.lastHoverTarget){_2.lastHoverTarget.stopHover();delete _2.lastHoverTarget}}}
,isc.A.$k3=function isc_c_EventHandler__updateMouseOutEventProperties(_1){var _2=isc.EH;var _3=_2.lastEvent;if(isc.Browser.isIE){_3.nativeTarget=_1.toElement}else{_3.nativeTarget=_1.relatedTarget}
if(_3.nativeTarget==null)_3.target=null
else _3.target=this.getEventTargetCanvas(_1,_3.nativeTarget)}
,isc.A.$kq=function isc_c_EventHandler__handleMouseStillDown(_1){if(!isc.Page.isLoaded())return false;var _2=this;_2.$k4=isc.Timer.clear(_2.$k4);if(!_2.mouseIsDown()||!_2.mouseDownTarget())return false;if(_2.bubbleEvent(_2.mouseDownTarget(),_2.MOUSE_STILL_DOWN)==false)return false;var _3=_2.mouseDownTarget(),_4=this.$km?_3.mouseStillDownInitialDelay:_3.mouseStillDownDelay;_2.$k4=this.delayCall("$kq",[],_4);return true}
,isc.A.handleMouseUp=function isc_c_EventHandler_handleMouseUp(_1,_2){if(isc.Browser.isTouch&&!_2)return;var _3=isc.EH;if(isc.Browser.isIE&&!_3.$j6){if(_3.$92e)return;var _4=_3.lastEvent;_4.eventType=_3.MOUSE_DOWN;_3.handleMouseDown(null,_3.lastEvent)}
_3.$92e=_2;if(!_2)_3.$ku=true;var _5=_3.$k5(_1,_2);_3.$ku=false;if(isc.Browser.isSafari)_5=true;return _5}
,isc.A.$k5=function isc_c_EventHandler__handleMouseUp(_1,_2){if(!isc.Page.isLoaded())return false;var _3=this,_4=(!_2?_3.getMouseEventProperties(_1):_3.lastEvent),_5=false;_3.$j6=false;delete _3.$k6;_3.$k4=isc.Timer.clear(_3.$k4);var _6=_3.$k7;if(_6){_6.focus();_3.$k7=null}
var _7=_3.$kp;_3.$kp=null;var _8;if(_7==null){_7=(_3.clickMaskClick(_4.target)==false);_8=_7}
if(_7==true){if(_3.logIsDebugEnabled())_3.logDebug("mouseUp cancelled by clickMask");return false}
var _9=false;if(_3.dragging){_9=_3.handleDragStop()}
if(_3.rightButtonDown(_4)){if(this.useSyntheticRightButtonEvents()){_3.handleContextMenu()}
_3.$k6=false}else{if(!_9){if(isc.Page.handleEvent(_4.target,_3.MOUSE_UP)!=false){var _10=true,x=this.lastEvent.x,y=this.lastEvent.y,_13=_3.mouseDownTarget();_5=_3.eventHandledNatively(_3.MOUSE_UP,_4.nativeTarget);if(!_5&&_3.targetIsEnabled(_13)){if(_13.visibleAtPoint(x,y))
_10=_3.bubbleEvent(_13,_3.MOUSE_UP,null,_8);else if(_13.containsPoint(x,y))
_10=_3.bubbleEvent(_13,_3.MOUSE_OUT,null,_8)}
if(_10!=false){_3.$k6=_3.handleClick(_4.target)}}}}
delete _3.redrawnWhileDown;_3.clearDragProperties();if(_3.$k0)_3.$k0=false;var _14=_4.target,_15=isc.isA.DynamicForm!=null&&isc.isA.DynamicForm(_14);if(_5&&(_15||_3.$k6==true))
return _3.$js;return(_15&&_3.$k6==true)}
,isc.A.clearDragProperties=function isc_c_EventHandler_clearDragProperties(){var _1=this;_1.dragging=false;delete _1.dragTarget;delete _1.dragTargetStartRect;delete _1.dragTargetLink;delete _1.dragMoveTarget;delete _1.dragMoveAction;delete _1.dragOperation;delete _1.dragAppearance;delete _1.dropTarget;delete _1.lastDropTarget}
,isc.A.handleContextMenu=function isc_c_EventHandler_handleContextMenu(_1){if(!isc.Page.isLoaded())return false;var _2=isc.EH;_2.$ku=true;var _3=_2.$k8(_1);_2.$ku=false;return _3}
,isc.A.$k8=function isc_c_EventHandler__handleContextMenu(_1){var _2=isc.Browser.isSafari||(this.isMouseEvent(this.lastEvent.eventType));if(this.$ki){delete this.$ki;return true}
if(_1)this.getMouseEventProperties(_1);var _3=this,_4=_3.lastEvent,_5=!_2?_4.keyTarget||_4.target:_4.target;_4.keyboardContextMenu=!_2;if(!_2&&!isc.Browser.isMoz){_4.x=_5?_5.getPageLeft():0;_4.y=_5?_5.getPageTop():0}
if(isc.Browser.isSafari&&_3.clickMaskClick(_5)==false){return false}
if(isc.Page.handleEvent(_5,_3.SHOW_CONTEXT_MENU)==false){return false}
var _6=true;if(_3.targetIsEnabled(_5)){_6=_3.bubbleEvent(_5,_3.SHOW_CONTEXT_MENU)}
if(_6!=false){if(_3.lastMoveTarget)_3.handleEvent(_3.lastMoveTarget,_3.MOUSE_OUT);delete _3.lastMoveTarget}
return _6}
,isc.A.handleNativeClick=function isc_c_EventHandler_handleNativeClick(_1){var _2=isc.EH,_3=(_2.$k6!=false);delete _2.$k6;if(isc.Browser.isAndroid){_2.DOMevent=_1;var _4=_2.getMouseEventProperties(_1);switch(this.$92f){case _2.$92d.READY_FOR_TOUCH:_4.originalType=_2.CLICK;_4.eventType=_2.MOUSE_DOWN;_2.doHandleMouseDown(_1,_4);case _2.$92d.TOUCH_STARTED:_4.originalType=_2.CLICK;_4.eventType=_2.MOUSE_UP;_2.$k5(_1,true);break;case _2.$92d.TOUCH_COMPLETE:break}
this.$92f=_2.$92d.READY_FOR_TOUCH}}
,isc.A.handleClick=function isc_c_EventHandler_handleClick(_1,_2){if(!isc.Page.isLoaded())return false;var _3=this,_4=_3.lastEvent,_5;if(!_2)_2=(_3.isDoubleClick(_1)?_3.DOUBLE_CLICK:_3.CLICK);if(isc.Page.handleEvent(_1,_2)==false){_5=false}else if(_3.eventHandledNatively(_2,_4.nativeTarget)){_5=_3.$js}else if(!_3.targetIsEnabled(_1)){_5=false}else if(!_3.stillWithinMouseDownTarget()){_5=false}else{var _1=_3.mouseDownTarget();_5=_3.bubbleEvent(_1,_2)}
_3.$k9=isc.timeStamp();return _5}
,isc.A.isDoubleClick=function isc_c_EventHandler_isDoubleClick(_1){var _2=this,_1=_1||_2.lastEvent.nativeTarget;var _3=_2.useNativeEventTime!=null?_2.useNativeEventTime:(isc.Browser.isMoz&&isc.Browser.isWin),_4,_5;if(_2._isSecondClick!=null){_5=_2._isSecondClick}else{if(_3){var _6=_2.lastEvent.DOMevent
_4=_6?_6.timeStamp:null;if(_4==0||!isc.isA.Number(_4)){this.logDebug("Unable to derive native 'timeStamp' attribute from DOM event");_4=isc.timeStamp()}
_5=((_4-_2.lastClickTime)<_2.DOUBLE_CLICK_DELAY)}else{_4=isc.timeStamp();_5=((_2.$k9-_2.lastClickTime)<_2.DOUBLE_CLICK_DELAY)?((_4-_2.lastClickTime)<_2.DOUBLE_CLICK_DELAY):((_4-_2.$k9)<100)}}
_2.lastClickTime=_4;if(!_5){delete _2.lastClickTarget}
var _7=false;if(_1==_2.lastClickTarget){_7=!_1.noDoubleClicks;if(_7){var _8=_1;while(_8.parentElement){_8=_8.parentElement;if(_8.noDoubleClicks){_7=false;break}}}}
_2.lastClickTarget=(_7?null:_1);return _7}
,isc.A.targetIsEnabled=function isc_c_EventHandler_targetIsEnabled(_1){if(!_1)return false;if(_1.destroyed)return false;if(isc.isA.Function(_1.isDisabled))return!_1.isDisabled();return true}
,isc.A.$77p=function isc_c_EventHandler__handleTouchStart(_1){var _2=isc.EH;_2.DOMevent=_1;var _3=_2.getMouseEventProperties(_1);this.$92f=_2.$92d.TOUCH_STARTED;var _4=_2.handleEvent(_3.target,_2.TOUCH_START);if(_4!==false){_3.originalType=_2.TOUCH_START;_3.eventType=_2.MOUSE_DOWN;_2.doHandleMouseDown(_1,_3);if(_2.$78m!=null)isc.Timer.clear(_2.$78m);_2.$78m=this.delayCall("$78n",[],_2.longTouchDelay)}}
,isc.A.$78n=function isc_c_EventHandler__handleLongTouch(){var _1=this;if(!_1.mouseIsDown()||!_1.mouseDownTarget()||!_1.stillWithinMouseDownTarget())return;_1.bubbleEvent(_1.mouseDownTarget(),_1.LONG_TOUCH)}
,isc.A.$77q=function isc_c_EventHandler__handleTouchMove(_1){var _2=isc.EH;_2.DOMevent=_1;var _3=_2.getMouseEventProperties(_1);this.$92f=_2.$92d.READY_FOR_TOUCH;var _4=_2.handleEvent(_3.target,_2.TOUCH_MOVE);if(_4!==false){_3.originalType=_2.TOUCH_MOVE;_3.eventType=_2.MOUSE_MOVE;_2.$kx(_1,_3);if(_2.dragging&&window.event!=null)window.event.preventDefault()}
if(_2.$78m!=null)isc.Timer.clear(_2.$78m)}
,isc.A.$77r=function isc_c_EventHandler__handleTouchEnd(_1){var _2=isc.EH;_2.DOMevent=_1;var _3=_2.getMouseEventProperties(_1);if(this.$92f==_2.$92d.TOUCH_STARTED){this.$92f=_2.$92d.TOUCH_COMPLETE}
var _4=_2.handleEvent(_3.target,_2.TOUCH_END);if(_4!==false){_3.originalType=_2.TOUCH_END;_3.eventType=_2.MOUSE_UP;_2.$k5(_1,true)}
if(_2.$78m!=null)isc.Timer.clear(_2.$78m)}
,isc.A.$86w=function isc_c_EventHandler__handleTouchCancel(_1){var _2=isc.EH;_2.DOMevent=_1;var _3=_2.getMouseEventProperties(_1);if(this.$92f==_2.$92d.TOUCH_STARTED){this.$92f=_2.$92d.TOUCH_COMPLETE}
this.delayCall("$894",[_3,_1])}
,isc.A.$894=function isc_c_EventHandler__handleDelayedTouchCancel(_1,_2){var _3=isc.EH;var _4=_3.handleEvent(_1.target,_3.TOUCH_END);if(_4!==false){_1.originalType=_3.TOUCH_CANCEL
_1.eventType=_3.MOUSE_UP;_3.$k5(_2,true)}
if(_3.$78m!=null)isc.Timer.clear(_3.$78m)}
,isc.A.getFocusCanvas=function isc_c_EventHandler_getFocusCanvas(){return this.$ke}
,isc.A.$la=function isc_c_EventHandler__logFocus(_1,_2){if(!this.logIsDebugEnabled("nativeFocus"))return;this.logDebug((_2?"onfocus":"onblur")+" fired on: "+_1+this.$lb(),"nativeFocus")}
,isc.A.$lb=function isc_c_EventHandler__getActiveElementText(){if(!isc.Browser.isIE)return isc.$ad;var _1=this.getActiveElement();if(_1==null)return isc.$ad;return", activeElement: "+(_1.tagName)}
,isc.A.blurFocusCanvas=function isc_c_EventHandler_blurFocusCanvas(_1,_2){var _3=this.$lc;if(_2){this.$h1("BLR");this.$la(_1);isc.EH.$ld=null}
this.$le(_1,_2);if(_2)this.$lc=_3}
,isc.A.$le=function isc_c_EventHandler__blurFocusCanvas(_1,_2){if(this.$ke){var _3=this.$ke;if(_1!=null&&_3!=_1)return;this.$ke=null;_3.$lf(false)}}
,isc.A.focusInCanvas=function isc_c_EventHandler_focusInCanvas(_1,_2){var _3=this.$lc;if(_2){this.$h1("FCS");this.$la(_1,true);isc.EH.$lg=null}
if(isc.Browser.isMoz){if(_2&&(this.lastEvent.eventType!=this.KEY_DOWN&&this.lastEvent.eventType!=this.KEY_PRESS&&this.lastEvent.eventType!=this.KEY_UP))
{if(_1&&_1.showFocusOutline)_1.setShowFocusOutline(false,true)}else{if(_1&&_1.showFocusOutline)_1.setShowFocusOutline(true,true)}}
this._focusInCanvas(_1,_2);if(_2)this.$lc=_3}
,isc.A._focusInCanvas=function isc_c_EventHandler__focusInCanvas(_1,_2){if(!_1||_1.hasFocus||!_1.$kk()||_1.isDisabled())return;if(this.$ke==_1)return;this.checkMaskedFocus(_1);if(this.targetIsMasked(_1)){var _3=this.clickMaskRegistry.last();this.setMaskedFocusCanvas(_1,_3)}
if(_2&&isc.Browser.isMoz){if(_1.parentElement)_1.parentElement.$lh(null,true)}
var _4=this.$ke;this.$ke=_1;if(_4)_4.$lf(false)
if(this.$ke!=_1)return;_1.$lf(true)}
,isc.A.setMaskedFocusCanvas=function isc_c_EventHandler_setMaskedFocusCanvas(_1,_2){if(!_2)return;_2.$li=_1}
,isc.A.getMaskedFocusCanvas=function isc_c_EventHandler_getMaskedFocusCanvas(_1){if(_1==null)_1=this.clickMaskRegistry.last();else _1=this.getClickMask(_1);if(_1)return _1.$li}
,isc.A.checkMaskedFocus=function isc_c_EventHandler_checkMaskedFocus(_1){if(isc.Browser.isIE){var _2=this.getActiveElement();var _3=_1?_1.getHandle():null;if(!_3)return;var _4;while(_2&&_2.tagName){if(_2==_3){_4=true;break}
if(_2.eventProxy){_4=(_2.eventProxy==_1.getID());break}
_2=_2.parentElement}
if(!_4)return}
if(isc.Browser.isMobileWebkit){if(isc.EH.isMouseEvent(isc.EH.lastEvent.eventType)&&(isc.EH.mouseDownTarget()==_1))
{return}}
var _5=this.clickMaskRegistry;for(var i=_5.length-1;i>=0;i--){var _7=_5[i];if(!this.targetIsMasked(_1,_7))return;else{if(this.isHardMask(_7))return false;this.$lj(_7)}}}
,isc.A.prepareForDragging=function isc_c_EventHandler_prepareForDragging(_1){var _2=this;if(_2.dragging)_2.handleDragStop();delete _2.dragMoveAction;delete _2.dragTarget;_2.bubbleEvent(_1,"prepareForDragging");if(!_2.dragTarget){if(this.logIsDebugEnabled("dragDrop"))this.logDebug("No dragTarget, not dragging","dragDrop");return}
if(this.logIsInfoEnabled("dragDrop"))
this.logInfo("target is draggable with dragOperation: "+_2.dragOperation+", dragTarget is : "+_2.dragTarget+(_2.dragTarget!=_1?" (delegated from: "+_1+")":""),"dragDrop");_2.dragTargetStartRect=_2.dragTarget.getRect()}
,isc.A.handleDragStart=function isc_c_EventHandler_handleDragStart(){var _1=this,_2=_1.lastEvent;if(!_1.mouseIsDown()||!_1.dragTarget)return false;delete _1.dropTarget;delete _1.dragMoveTarget;_1.dragOffsetX=-10;_1.dragOffsetY=-10;_1.handleEvent(_1.lastMoveTarget,_1.MOUSE_OUT);if(_1.lastMoveTarget!=_1.mouseDownTarget()){_1.handleEvent(_1.mouseDownTarget(),_1.MOUSE_OUT)}
if(isc.Hover)isc.Hover.clear();_1.dragStartOffsetX=_1.mouseDownEvent.x-_1.dragTarget.getPageLeft();_1.dragStartOffsetY=_1.mouseDownEvent.y-_1.dragTarget.getPageTop();var _3=_1.dragOperation+"Start";if(_1.handleEvent(_1.dragTarget,_3)==false){this.logInfo("drag cancelled by false return from: "+_3+" on "+_1.dragTarget,"dragDrop");delete _1.dragTarget;delete _1.dragTargetLink;_1.handleEvent(_1.dragTarget,_1.MOUSE_OVER);return false}
delete _1.lastMoveTarget;var _4=_1.dragTarget.getDragAppearance(_1.dragOperation);if(_4!=_1.TRACKER)
{_1.dragOffsetX=_1.dragStartOffsetX;_1.dragOffsetY=_1.dragStartOffsetY}
if(_1.dragOperation==_1.DRAG_SCROLL){_1.dragAppearance=_1.NONE}else{_1.dragAppearance=_1.dragTarget.getDragAppearance(_1.dragOperation)}
if(_1.dragAppearance==_1.TRACKER){_1.dragMoveTarget=_1.$lk();if(!_1.dragMoveAction)_1.dragMoveAction=_1.$ll;_1.dragTracker.setOverflow(isc.Canvas.VISIBLE);_1.bubbleEvent(_1.dragTarget,_1.SET_DRAG_TRACKER);_1.dragOffsetX=_1.dragTracker.offsetX;_1.dragOffsetY=_1.dragTracker.offsetY}else if(_1.dragAppearance==_1.OUTLINE){_1.dragMoveTarget=_1.getDragOutline(_1.dragTarget);if(!_1.dragMoveAction)_1.dragMoveAction=_1.$ll}else if(_1.dragAppearance==_1.TARGET){_1.dragMoveTarget=_1.dragTarget;if(!_1.dragMoveAction)_1.dragMoveAction=_1.$ll;if(_1.dragTarget.showDragShadow)this.$lm();if(_1.dragTarget.dragOpacity!=null)this.$ln()}else{}
if(_1.dragMoveTarget){if(_1.dragMoveTarget!=_1.dragTarget){_1.dragMoveTarget.dragIntersectStyle=_1.dragTarget.dragIntersectStyle}
_1.dragMoveTarget.show();_1.dragMoveTarget.bringToFront()}
var _5=_1.dragMoveTarget?_1.dragMoveTarget:_1.dragTarget;if((isc.Browser.isIE||isc.Browser.isMoz)&&_1.dragAppearance!=_1.OUTLINE&&!(_5.$lo||_5.neverBackMask))
{if(_1.alwaysBackMask){this.$lp(_5)}else{var _6=[];if(isc.BrowserPlugin){var _7=isc.BrowserPlugin.instances;for(var i=0;i<_7.length;i++){var _9=_7[i];if(_9.isVisible()&&(_5.parentElement==null||_5.parentElement.contains(_9,true)))
{_6.add({instance:_9,rect:_9.getPageRect()})}}}
if(isc.Browser.isIE&&isc.Browser.minorVersion>=5.5&&isc.NativeSelectItem){var _10=isc.NativeSelectItem.instances;for(var i=0;i<_10.length;i++){var _11=_10[i];if(_11.isVisible()&&(_5.parentElement==null||_5.parentElement.contains(_11.containerWidget,true)))
{_6.add({instance:_11,rect:_11.getPageRect()})}}}
if(_6.length>0&&_1.dynamicBackMask===false)
{this.$lp(_5)}else{_1.$lq=_6}}}
_1.showEventMasks((_1.dragOperation==_1.DRAG_RESIZE));_1.dragging=true;this.logInfo("Started dragOperation: "+_1.dragOperation+" with dragTarget: "+_1.dragTarget+" dragAppearance: "+_1.dragAppearance,"dragDrop");return true}
,isc.A.$lm=function isc_c_EventHandler__showTargetDragShadow(){var _1=isc.EH;var _2=_1.dragTarget;_1.$lr=(!_2.showShadow);_1.$ls=_2.shadowDepth;_2.shadowDepth=_1.dragTargetShadowDepth;_2.updateShadow();if(!_2.showShadow)_2.setShowShadow(true)}
,isc.A.$lt=function isc_c_EventHandler__hideTargetDragShadow(){var _1=isc.EH;var _2=_1.dragTarget;if(_1.$lr)_2.setShowShadow(false);_2.shadowDepth=_1.$ls;_2.updateShadow();delete _1.$lr;delete _1.$ls}
,isc.A.$ln=function isc_c_EventHandler__setTargetDragOpacity(){var _1=isc.EH;var _2=_1.dragTarget;_1.$lu=_2.opacity;_2.setOpacity(_2.dragOpacity)}
,isc.A.$lv=function isc_c_EventHandler__resetTargetDragOpacity(){var _1=isc.EH,_2=_1.dragTarget;_2.setOpacity(_1.$lw)}
,isc.A.$lp=function isc_c_EventHandler__showBackMask(_1){if(_1._backMask){if(!_1._backMask.isVisible())_1._backMask.show()}else{_1.makeBackMask({$lx:true})}}
,isc.A.$ly=function isc_c_EventHandler__hideBackMask(_1){if(_1._backMask&&_1._backMask.$lx&&_1._backMask.isVisible())
{_1._backMask.hide()}}
,isc.A.$lz=function isc_c_EventHandler__getDragMoveComponents(){var _1=this.dragMoveTarget;if(!_1)return;var _2=[_1];if(_1._backMask)_2.add(_1._backMask);if(_1.$l0)_2.add(_1.$l0);if(_1._shadow)_2.add(_1._shadow);return _2}
,isc.A.$l1=function isc_c_EventHandler__getDragMoveEventName(_1){var _2=this.$ji;if(!_2[_1]){_2[_1]=_1+"Move"}
return _2[_1]}
,isc.A.handleDragMove=function isc_c_EventHandler_handleDragMove(){var _1=this,_2=_1.lastEvent;isc.$54j=true;_1.dropTarget=_1.getDropTarget(_2);isc.$54j=false;if(_1.$lq&&_1.dynamicBackMask){var _3=false;var _4=_1.dragMoveTarget?_1.dragMoveTarget:_1.dragTarget;var _5=_4.getRect();for(var i=0;i<_1.$lq.length;i++){var _7=_1.$lq[i];if(isc.Canvas.rectsIntersect(_7.rect,_5))
{_1.$l2=_7.instance;_3=true;break}}
if(_1.$l2){if(_1.$l2.repaintIfRequired)_1.$l2.repaintIfRequired()}
if(_3){this.$lp(_4)}else{this.$ly(_4);delete _1.$l2}}else if(isc.BrowserPlugin){isc.BrowserPlugin.handleDragMoveNotify()}
if(_1.dragMoveAction)_1.dragMoveAction();if(_1.handleEvent(_1.dragTarget,this.$l1(_1.dragOperation))==false){delete _1.dropTarget;return false}
if(_1.dropTarget!=_1.lastDropTarget){this.logDebug("New drop target: "+_1.dropTarget,"dragDrop");if(_1.lastDropTarget){_1.handleEvent(_1.lastDropTarget,_1.DROP_OUT)}
if(_1.dropTarget){_1.handleEvent(_1.dropTarget,_1.DROP_OVER)}
_1.lastDropTarget=_1.dropTarget}
if(_1.dropTarget){_1.handleEvent(_1.dropTarget,_1.DROP_MOVE)}
isc.$54j=true;this.$l3();isc.$54j=false;return false}
);isc.evalBoundary;isc.B.push(isc.A.$l3=function isc_c_EventHandler__handleDragScroll(){var _1=this,_2=_1.dragTarget;if(_1.dragOperation==_1.DRAG_SCROLL)return;if(_1.dragOperation==_1.DRAG_SELECT){if(_2.overflow==isc.Canvas.VISIBLE)return;if(!_2.containsEvent()||_2.$l4(_2.dragScrollDirection))
{_2.$l5(_2.dragScrollDirection,true)}}
var _3=[];var _4=_2.dragScrollType=="parentsOnly"?_2.getParentElements():isc.Canvas._canvasList;;if(_4==null||_4.length==0)return;for(var i=0;i<_4.length;i++){if((_4[i].hscrollOn||_4[i].vscrollOn)&&_4[i].isDrawn()&&_4[i].isVisible()&&_4[i].shouldDragScroll()){_3.add(_4[i])}}
var _6=_1.lastEvent,_7=_6.x,_8=_6.y,_9=[];for(var i=0;i<_3.length;i++){if(_3[i].visibleAtPoint(_7,_8,false,_1.$lz()))
_9.add(_3[i])}
if(_9.length>0){var _10;for(var i=0;i<_9.length;i++){if(_9[i].$l4(_2.dragScrollDirection)){if(_10==null||_10.contains(_9[i],true))
_10=_9[i]}}
if(_10!=null)_10.$l5(_2.dragScrollDirection)}}
,isc.A.handleDragStop=function isc_c_EventHandler_handleDragStop(){var _1=this,_2=_1.lastEvent,_3=false;_1.dragging=false;this.logInfo("end of drag interaction","dragDrop");_1.dragOffsetX=_1.dragOffsetY=0;var _4=_1.dragTarget,_5=_1.dragMoveTarget,_6=_1.dragOperation;if(_5&&(_5==_1.dragTracker||_5==_1.dragOutline))
{_5.hide()}else{if(_4.showDragShadow)_1.$lt();if(_4.dragOpacity!=null)_1.$lv()}
if(this.dragTracker&&this.dragTracker.$l6){this.dragTracker.destroy();delete this.dragTracker}
var _7=_1.dragMoveTarget?_1.dragMoveTarget:_1.dragTarget;this.$ly(_7);if(_1.$lq)delete _1.$lq;var _8=_1.dropTarget;if(_8){_1.handleEvent(_1.dropTarget,_1.DROP_OUT);if(_8.willAcceptDrop())_1.handleEvent(_8,_1.DROP);_3=true}
var _9=(_4==_5);if(_1.handleEvent(_4,_6+"Stop")!=false){_3=true;if(_6==_1.DRAG_RESIZE){if(!_9){if(_5!=null&&this.dragAppearance!=this.TRACKER){_4.setPageRect(_5.getPageLeft(),_5.getPageTop(),_5.getWidth(),_5.getHeight(),true)}else{var _10=isc.EH.resizeEdge;if(_10!=null){var X=isc.EH.getX(),Y=isc.EH.getY(),_13=_10.contains("L")?X-_1.dragTargetStartRect[0]:0,_14=_10.contains("T")?Y-_1.dragTargetStartRect[1]:0;_4.setPageRect(_10.contains("L")?X:_1.dragTargetStartRect[0],_10.contains("T")?Y:_1.dragTargetStartRect[1],_10.contains("R")?X-_4.getPageLeft():_1.dragTargetStartRect[2]-_13,_10.contains("B")?isc.EH.getY()-_4.getPageTop():_1.dragTargetStartRect[3]-_14,true)}}}
var _15=_4.getVisibleWidth()-_1.dragTargetStartRect[2],_16=_4.getVisibleHeight()-_1.dragTargetStartRect[3];_4.dragResized(_15,_16)}else if(_6==_1.DRAG_REPOSITION){if(!_9){if(_5!=null){_4.setPageRect(_5.getPageLeft(),_5.getPageTop())}else{_4.setPageRect(isc.EH.getX(),isc.EH.getY())}
_4.bringToFront()}
_1.dragTarget.dragRepositioned()}}else{if(_6==_1.DRAG_RESIZE){if(_9){_4.setRect(_1.dragTargetStartRect)}}else if(_1.dragOperation==_1.DRAG_REPOSITION){if(_9){_4.moveTo(_1.dragTargetStartRect[0],_1.dragTargetStartRect[1])}}}
_1.clearDragProperties();_1.hideEventMasks();var _17=_1.lastEvent.target;if(_17)_1.handleEvent(_17,_1.MOUSE_OVER);_1.lastMoveTarget=_17;return _3}
,isc.A.getEventTargetCanvas=function isc_c_EventHandler_getEventTargetCanvas(_1,_2,_3){if(_1==null)_1={};var _4=this,_5=this.getWindow();if(!_2)_2=(isc.Browser.isIE?_1.srcElement:_1.target);if(!_4.$l7(_2)){return _4.lastTarget}
if(_1&&_1.$49s)return _1.target;if(!_2||_2.tagName==this.$jj||_2.tagName==this.$jk){return(_4.lastTarget=null)}
if(_2&&_2.tagName&&_2.tagName==this.$49r){var _6=isc.Applet?isc.Applet.idForName(_2.name):null;return _6?window[_6]:_4.lastTarget}
if(isc.Browser.isIE&&_2.parentElement==null){_2=_4.lastTarget}else{var _7=this.$jl;if(isc.Browser.isIE&&!isc.Browser.isIE9){while(_2!=null){if(_2.eventProxy)break;_2=_2.parentElement}}else{while(_2!=null){if(_2.eventProxy!=null||(_2.hasAttribute!=null&&_2.hasAttribute(_7)))break;_2=_2.parentNode}}
if(!_2)return(_4.lastTarget=null);_2=_5[_2.getAttribute(_7)];while(_2&&_2.eventProxy){if(isc.isA.String(_2.eventProxy)){_2.eventProxy=_5[_2.eventProxy]}
_2=_2.eventProxy}
if(this.logIsInfoEnabled()&&!_1||(_1.type!="mousemove"&&_1.type!="selectstart"))
{if(_2!=null){this.logInfo("Target Canvas for event '"+_1.type+"': "+_2)}else{this.logDebug("No target Canvas for event '"+_1.type+"'")}}
if(_2==_4.dragTracker){_2=_4.lastTarget}
_4.lastTarget=_2}
if(isc.isA.Canvas(_2)){if(_3&&_2.getEventTarget){_2=_2.getEventTarget(_3)}
return _2}
return null}
,isc.A.$l7=function isc_c_EventHandler__canAccessNativeTargetProperties(_1){try{if(!(isc.Browser.isMoz&&_1==this.$jm))return true;_1.parentNode}catch(e){return false}
return true}
,isc.A.getDropTarget=function isc_c_EventHandler_getDropTarget(_1){var _2=this;if(!_2.dragTarget||!_2.dragTarget.canDrop||_2.dragOperation==_2.DRAG_RESIZE)return null;var _3=(_2.dragMoveTarget||_2.dragTarget),_4=_2.$i0,_5=[],i=0,_7=_4.length,_8=(_3.getDragAppearance(_2.dragOperation)!=isc.EH.TARGET);if(_3.dragIntersectStyle==_2.INTERSECT_WITH_MOUSE){if((_1.target!=this.mouseDownTarget()||(isc.Browser.isIE||(isc.Browser.isSafari&&!isc.Browser.isTouch)||(isc.Browser.isMoz&&isc.Browser.geckoVersion>20040616&&!this.mouseDownTarget().$ks))))
{var _9=_1.target;while(_9&&_9.dropTarget)_9=_9.dropTarget;if((_8||_9!=_3)&&(_4.contains(_9)))
{return _9}}
for(;i<_7;i++){var _10=_4[i];if(_10.canAcceptDrop&&!_10.isDisabled()&&(_10.visibleAtPoint(_1.x,_1.y,false,_2.$lz()))&&(_8||_10!=_3))
{_5.add(_10)}}}else{for(;i<_7;i++){var _10=_4[i];if(!_8&&_10==_3)continue;if(_10.intersects(_3)&&_10.canAcceptDrop&&!_10.isDisabled())
{_5.add(_10)}}}
if(_5.length<2)return _5[0];var _11=_5[0];for(var i=1;i<_5.length;i++){var _12=_5[i];if(_11.contains(_12,true)){_11=_12}else if(_3.dragIntersectStyle==_2.INTERSECT_WITH_RECT){var _13=null,_14=_11,_15=_12;while(_13==null){if(_14.parentElement==null){_13=true;_15=_12.topElement||_12}else if(_14.parentElement.contains(_12,true)){_13=_14.parentElement;while(_15.parentElement!=_13){_15=_15.parentElement}}else{_14=_14.parentElement}}
if(_15.getZIndex()>_14.getZIndex()){_11=_12}}}
return _11}
,isc.A.registerDroppableItem=function isc_c_EventHandler_registerDroppableItem(_1){if(!_1.$ma){this.$i0.add(_1);_1.$ma=true}}
,isc.A.unregisterDroppableItem=function isc_c_EventHandler_unregisterDroppableItem(_1){this.$i0.remove(_1);delete _1.$ma}
,isc.A.registerMaskableItem=function isc_c_EventHandler_registerMaskableItem(_1,_2){if(!this.$i1.contains(_1)){this.$i1.add(_1);if(_2)this.makeEventMask(_1,{eventProxy:_1})}}
,isc.A.unregisterMaskableItem=function isc_c_EventHandler_unregisterMaskableItem(_1){this.$i1.remove(_1);if(_1._eventMask)_1._eventMask.destroy();delete _1._eventMask}
,isc.A.makeEventMask=function isc_c_EventHandler_makeEventMask(_1,_2,_3){if(isc.isA.Function(_1.makeEventMask))return _1.makeEventMask(_2,_3);var _4=this.$jn;if(!_4.contents)_4.contents=isc.Browser.isIE&&isc.Browser.version>6?isc.Canvas.blankImgHTML(3200,2400):isc.Canvas.spacerHTML(3200,2400);var _5=isc.Canvas.create({ID:_1.getID()+"_eventMask",cursor:_1.cursor,$jr:_1},_4,_2);_5.setRect(_3?_3:_1.getRect());_1._eventMask=_5;_1.addPeer(_5);return _5}
,isc.A.showEventMasks=function isc_c_EventHandler_showEventMasks(_1,_2){var _3=this,_4=_3.$i1;if(_1){if(!_3._eventMask)_3._eventMask=isc.ScreenSpan.create({ID:"isc_EH_eventMask",mouseDown:function(){this.hide()},pointersToThis:[{object:_3,property:"_eventMask"}]});_3._eventMask.show();_3._eventMask.bringToFront();if(isc.BrowserPlugin){_4.intersect(isc.BrowserPlugin.instances).map("$mb")}}else{for(var i=0;i<_4.length;i++){var _6=_4[i];if(_2&&_2[_6.getID()]){_6.$mc()}else{_6.$mb()}}}}
,isc.A.hideEventMasks=function isc_c_EventHandler_hideEventMasks(){var _1=this,_2=_1.$i1;if(_1._eventMask&&_1._eventMask.isVisible()){_1._eventMask.hide();if(isc.BrowserPlugin){_2.intersect(isc.BrowserPlugin.instances).map("$mc")}}else{for(var i=0;i<_2.length;i++){_2[i].$mc()}}}
,isc.A.eventHandledNatively=function isc_c_EventHandler_eventHandledNatively(_1,_2,_3){var _4=_1;if(!this.reverseEventTypes[_1]){if(this.$i2[_1])
_4=this.$i2[_1];else if(this.$i7[_1])
_4=this.$i7[_1]}
var _5=this.$md(_4,_2,_3);if(_5&&this.logIsDebugEnabled()&&_4!="mouseMove"){this.logDebug(_1+" event on "+(_3?" native target:"+_2:this.lastTarget)+" handled natively")}
return _5}
,isc.A.$md=function isc_c_EventHandler__eventHandledNatively(_1,_2,_3){_1=(_1||"");var _4=this,_5=_4.lastEvent;if(!_4.$l7(_2)){return true}
if(_2&&_2.tagName==this.$49r)return true;var _6=_4.isMouseEvent(_1),_7=_6?_5.target:_5.keyTarget;if(!_3&&_6&&_7==null)return true;if((this.logIsInfoEnabled()&&_1==_4.KEY_DOWN)||(this.logIsDebugEnabled()&&(_1==_4.KEY_UP||_1==_4.KEY_PRESS)))
{this.logInfo(_1+" event with Canvas target: "+this.lastEvent.keyTarget+", native target: "+this.echoLeaf(_2))}
if(_4.passThroughEvents&&_2){var _8=_2,_9=(_8.handleNativeEvents||(_8.getAttribute?_8.getAttribute(this.$jt):null)),_10=_8.tagName,_11;if(!_4.$me)_4.$me="false";if(_9==null){_11=(!_8.focusProxy&&((_8.form!=null&&_10!=_4.$i6)||_4.$i5[_10]!=null||(_8.isContentEditable&&!_8.eventProxy)));if(!_11&&(_1!=_4.MOUSE_WHEEL)&&(_1!=_4.MOUSE_MOVE)){while(_8&&_8.tagName!=_4.BODY_TAG&&_8.tagName!=this.$jk)
{if(_8.eventProxy!=null||(_8.hasAttribute!=null&&_8.hasAttribute(this.$jl)))break;if(_4.$i4[_8.tagName]!=null){var _12=(_8.handleNativeEvents||(_8.getAttribute?_8.getAttribute(this.$jt):null));if(_12!=null&&!isc.isA.emptyString(_12)){if(isc.isA.String(_12))
_12=(_12==isc.EH.$me?false:true)}
if(_12!=false){_11=true;break}}
_8=_8.parentNode}}}else{_11=_9;if(_11==_4.$me)_11=false}
if(_11){return true}else if(_9!=null){return false}}
if(!_3&&_6&&this.$mf(_7,_1,_5))
{return true}
return false}
,isc.A.isMouseEvent=function isc_c_EventHandler_isMouseEvent(_1){_1=_1||this.lastEvent.eventType;if(this.$mg==null){this.$mg={mouseOver:true,mouseover:true,mouseDown:true,mousedown:true,rightMouseDown:true,mouseMove:true,mousemove:true,mouseOut:true,mouseout:true,mouseUp:true,mouseup:true,DOMMouseScroll:true,mousewheel:true,mouseWheel:true,click:true,doubleClick:true,doubleclick:true,showContextMenu:true,showcontextmenu:true,selectStart:true,selectstart:true}}
if(this.$mg[_1]==true)return true;if(_1=="selectionChange"){return(this.lastEvent.keyName==null||this.lastEvent.keyName=="")}
if(_1=="contextMenu"||_1=="contextmenu"){return!this.lastEvent.keyboardContextMenu}
return false}
,isc.A.isKeyEvent=function isc_c_EventHandler_isKeyEvent(_1){_1=_1||this.lastEvent.eventType;if(this.$mh==null){this.$mh={};var _2=this.$mh;_2[this.KEY_DOWN]=true;_2[this.KEY_PRESS]=true;_2[this.KEY_UP]=true;var _3=this.$i7;for(var _4 in _3)_2[_4]=true}
if(this.$mh[_1]==true)return true;if(_1=="contextMenu"||_1=="contextmenu"){return!!this.lastEvent.keyboardContextMenu}
return false}
,isc.A.$mf=function isc_c_EventHandler__eventOverCSSScrollbar(_1,_2,_3){if(isc.Browser.isTouch)return false;var _4=this;if(!_1||_1.showCustomScrollbars||!(_1.vscrollOn||_1.hscrollOn))return false;var _5=isc.Element.getNativeScrollbarSize();if(_1.isRTL()){if((_1.vscrollOn&&(_3.x<_1.getPageLeft()+_5))||(_1.hscrollOn&&(_3.y>_1.getPageTop()+_1.getHeight()-_5)))
{if(_2==_4.MOUSE_DOWN)_4.$k0=true;return true}}else{if((_1.vscrollOn&&(_3.x>_1.getPageRight()-_5))||(_1.hscrollOn&&(_3.y>_1.getPageBottom()-_5)))
{if(_2==_4.MOUSE_DOWN)_4.$k0=true;return true}}
return false}
,isc.A.bubbleEvent=function isc_c_EventHandler_bubbleEvent(_1,_2,_3,_4){var _5=this,_6=_5.lastEvent;var _7=this.logIsDebugEnabled()&&!this.$ju[_2];var _8=this.isMouseEvent(_2);if(_8){if(_4==null){_4=this.targetIsMasked(_1,null)}
if(_4){if(_7){this.logDebug(_2+" on "+_1+" blocked by clickmask")}
return false}}
var _9=this.$mi(_2);while(_1){if(_1.destroyed)break;var _10=null;var _11=null;if(_1.mouseEventParent&&_2.startsWith("mouse")){_10=_1.mouseEventParent}else if(_1.keyEventParent&&_2.startsWith("key")){_10=_1.keyEventParent}else{_10=(_1.eventParent||_1.parentElement)}
if(_10&&_10.eventProxy)_10=_10.eventProxy;if(_1[_9]!=null){_11=_9}else if(_1[_2]!=null&&_1[_2]!=isc.Class.NO_OP&&!isc.is.emptyString(_1[_2])){_11=_2;if(isc.isA.String(_1[_2])){_1.convertToMethod(_2)}
if(_7){this.logDebug("Bubbling event '"+_2+"', target '"+_1+"' has handler: "+this.echoLeaf(_1[_2]))}}
if(_11!=null&&_1[_11]!=null){var _12;_12=_1[_11](_6,_3);if(_12==false){if(_7){this.logDebug("Bubbling for event '"+_2+"' cancelled via false return value by target: "+_1)}
return false}
if(_12==_5.STOP_BUBBLING){if(_7){this.logDebug("Bubbling for event '"+_2+"' cancelled via STOP_BUBBLING return value by target: "+_1)}
return _5.STOP_BUBBLING}}
if(_1.bubbleEvents==false||(_1.bubbleMouseEvents==false&&_5.isMouseEvent(_2)))
{if(_7){this.logDebug("Bubbling for event '"+_2+"' stopped by '"+_1+"' which does not allow bubbling")}
return true}else if(isc.isAn.Array(_1.bubbleMouseEvents)){if(_1.bubbleMouseEvents.contains(_2)){if(_7){this.logDebug("Bubbling for event '"+_2+"' stopped by '"+_1+"' which does not allow bubbling")}
return true}}
_1=_10}
if(_7)this.logDebug("Event '"+_2+"' bubbled to top");return true}
,isc.A.$mi=function isc_c_EventHandler__getInternalHandlerName(_1){if(!this.$jc[_1]){this.$jc[_1]="handle"+_1.charAt(0).toUpperCase()+_1.substring(1)}
return this.$jc[_1]}
,isc.A.hasEventHandler=function isc_c_EventHandler_hasEventHandler(_1,_2){if(!isc.isAn.Object(_1)||!isc.isA.String(_2)){isc.Log.logWarn("EventHandler.hasEventHandler() passed bad parameters ["+[_1,_2]+"]. returning null;","event");return null}
var _3=this.$mi(_2);if(this.getBubbledProperty(_1,_2)!=null||this.getBubbledProperty(_1,_3)!=null)return true;return false}
,isc.A.getBubbledProperty=function isc_c_EventHandler_getBubbledProperty(_1,_2){while(_1){if(_1[_2])return _1[_2];_1=(_1.eventParent||_1.parentElement);if(_1&&_1.eventProxy)_1=_1.eventProxy}
return null}
,isc.A.handleSelectStart=function isc_c_EventHandler_handleSelectStart(){var _1=isc.EH;if(_1.$904)return true;var _2=_1.getWindow(),_3=_2.event?_2.event.srcElement:null,_4=_1.mouseDownEvent?_1.mouseDownEvent.nativeTarget:null;if(_3&&_4==_3&&_3.form&&!_1.dragging)
{return true}
if(isc.EH.$mj)return true;var _5=isc.EH.mouseIsDown()?_1.mouseDownTarget():null,_6=_1.getEventTargetCanvas(_2.event);var _7=(_1.dragging||_1.dragTarget)&&_1.dragOperation!=_1.DRAG_SELECT;var _8=!_7&&(_5!=null?_5.$kr():true)&&(_6!=null?_6.$kr():true);if(_8)return true;return _1.killEvent()}
,isc.A.handleSelectionChange=function isc_c_EventHandler_handleSelectionChange(_1){if(!_1)_1=window.event;var _2=isc.EH;var _3=_2.lastEvent;var _4=isc.Element.$mk(document);if(_4){var _5=_2.getEventTargetCanvas(_1,_4);_3.nativeKeyTarget=_4
_3.keyTarget=_5;_3.eventType=this.$41z;if(_5){_5.keyTarget=_5;_2.bubbleEvent(_3.keyTarget,"selectionChange")}}
return true}
,isc.A.handleNativeHelp=function isc_c_EventHandler_handleNativeHelp(){if(this.$69a){if(this.$69a()==false)return false}
if(this.$69b){if(this.$69b()==false)return false}
return isc.EH.$j9(window.event,true)}
,isc.A.handleNativeDragStart=function isc_c_EventHandler_handleNativeDragStart(){if(isc.EH.dragTarget)return false;var _1=isc.EH.mouseDownTarget();if(_1)return!!(_1.$kr());if(this.$ml)return this.$ml();if(this.$mm)return this.$mm()}
,isc.A.handleResize=function isc_c_EventHandler_handleResize(_1){if(isc.EH.resizeTimer==null){isc.EH.resizeTimer=isc.Timer.setTimeout("isc.EH.$hr()",0)}
return true}
,isc.A.handleOrientationChange=function isc_c_EventHandler_handleOrientationChange(_1){this.$78p()}
,isc.A.$mn=function isc_c_EventHandler__pageResizePollMethod(){isc.EH.$hr(true)}
,isc.A.$hr=function isc_c_EventHandler__pageResize(_1){isc.EH.resizeTimer=null;var _2=isc.Page.getOrientation();if(!_1){this.$mo=isc.Page.getWidth(window,true);this.$mp=isc.Page.getHeight(window,true);if(this.resizingPollTimer!=null)isc.Timer.clearTimeout(this.resizingPollTimer);this.resizingPollTimer=isc.Timer.setTimeout(this.$mn,100)}else{var _3=isc.Page.getWidth(window,true),_4=isc.Page.getHeight(window,true),_5=(_2==this.currentOrientation)&&(_3==this.$mo&&_4==this.$mp)
if(isc.Page.pollPageSize){isc.Page.setEvent(isc.EH.IDLE,this.$mn,isc.Page.FIRE_ONCE)}
if(_5)return;this.$mo=_3;this.$mp=_4}
this.$78p(_2)}
,isc.A.$78p=function isc_c_EventHandler__fireResizeEvent(_1){isc.Page.handleEvent(null,isc.EH.RESIZE);if(_1==null)_1=isc.Page.getOrientation();if(_1!=this.currentOrientation){this.currentOrientation=_1;isc.Page.handleEvent(null,isc.EH.ORIENTATION_CHANGE)}}
,isc.A.handleMouseWheel=function isc_c_EventHandler_handleMouseWheel(_1){var _2=isc.EH;if(!_1)_1=_2.getWindow().event;var _3=(_1.srcElement||_1.target);if(_2.eventHandledNatively(_1.type,_3))return _2.$js;_2.getMouseEventProperties(_1);var _4=_2.getEventTargetCanvas(_1);if(_2.bubbleEvent(_4,_2.eventTypes.MOUSE_WHEEL)==false){if(_1.preventDefault)_1.preventDefault();return false}
return true}
,isc.A.getWheelDelta=function isc_c_EventHandler_getWheelDelta(_1){return(_1||this.lastEvent).wheelDelta}
,isc.A.handleDOMMouseScroll=function isc_c_EventHandler_handleDOMMouseScroll(_1){return isc.EH.handleMouseWheel(_1)}
,isc.A.handleScroll=function isc_c_EventHandler_handleScroll(_1){}
,isc.A.prepareForLinkDrag=function isc_c_EventHandler_prepareForLinkDrag(_1,_2){this.dragTarget=(isc.isA.String(_1)?this.getWindow()[_1]:_1);this.dragTargetLink=_2;return false}
,isc.A.setDragTracker=function isc_c_EventHandler_setDragTracker(_1,_2,_3,_4,_5,_6){var _7=this.$lk(_6);_2=_2||10;_3=_3||10;_7.resizeTo(_2,_3);_7.setContents(_1);_7.redrawIfDirty("setDragTracker");if(_4)_7.offsetX=_4;if(_5)_7.offsetY=_5;_7.$l6=true}
,isc.A.$lk=function isc_c_EventHandler__makeDragTracker(_1){if(!this.dragTracker){var _2=this.dragTrackerDefaults;_2.contents=isc.Canvas.imgHTML("[SKIN]black.gif",10,10);this.dragTracker=isc.Canvas.create(_2,_1)}else if(_1!=null)this.dragTracker.setProperties(_1);return this.dragTracker}
,isc.A.getDragOutline=function isc_c_EventHandler_getDragOutline(_1,_2,_3){if(!this.dragOutline){this.dragOutline=isc.Canvas.create({autoDraw:false,overflow:isc.Canvas.HIDDEN})
if(isc.Browser.isIE)this.dragOutline.setContents(isc.Canvas.spacerHTML(3200,2400))}
var _4=this.dragOutline;if(isc.Element.getStyleDeclaration(_1.dragOutlineStyle)){_4.setStyleName(_1.dragOutlineStyle)}else{_4.setBorder((_2||1)+"px solid "+(_3||"black"))}
_4.setPageRect(_1.getPageLeft(),_1.getPageTop(),_1.getVisibleWidth(),_1.getVisibleHeight());_4.minWidth=_1.minWidth;_4.minHeight=_1.minHeight;_4.maxWidth=_1.maxWidth;_4.maxHeight=_1.maxHeight;if(isc.isAn.Array(_1.keepInParentRect)){_4.keepInParentRect=_1.keepInParentRect}else if(_1.keepInParentRect==true){_4.keepInParentRect=_1.getParentPageRect()}else{_4.keepInParentRect=null}
return _4}
,isc.A.getDragRect=function isc_c_EventHandler_getDragRect(){var _1=this.dragMoveTarget||this.dragTarget;if(!_1)return null;return _1.getPageRect()}
,isc.A.$ll=function isc_c_EventHandler__moveDragMoveTarget(){var _1=this;var _2=_1.dragMoveTarget;if(!_2)return true;var _3=(isc.Browser.isMoz&&isc.Browser.geckoVersion<20031007&&!_2.keepInParentRect);if(_3&&(_2.parentElement&&!_2.parentElement.containsPoint(_1.lastEvent.x,_1.lastEvent.y)))
{return true}
isc.$54j=true;_1.dragMoveTarget.moveToEvent(_1.dragOffsetX,_1.dragOffsetY);isc.$54j=false;return true}
,isc.A.$mq=function isc_c_EventHandler__resizeDragMoveTarget(){var _1=this;if(_1.dragMoveTarget)_1.dragMoveTarget.resizeToEvent(_1.resizeEdge);return true}
,isc.A.killEvent=function isc_c_EventHandler_killEvent(){isc.EH.getWindow().event.cancelBubble=true;return false}
,isc.A.stopBubbling=function isc_c_EventHandler_stopBubbling(){return isc.EH.STOP_BUBBLING}
,isc.A.startIdleTimer=function isc_c_EventHandler_startIdleTimer(){if(!isc.Page.isLoaded())return;if(!this.idleTimer){this.idleTimer=isc.Timer.setTimeout({target:isc.EH,methodName:this.$jv},this.IDLE_DELAY)}}
,isc.A.$jw=function isc_c_EventHandler__handleIdle(){this.idleTimer=null;var _1=isc.Page.handleEvent(null,this.IDLE);if(isc.Page.actionsArePendingForEvent(this.IDLE))this.startIdleTimer();return _1}
,isc.A.$h1=function isc_c_EventHandler__setThread(_1){var _2=_1+this.$jx++;if(this.$lc!=null)this.$90m=this.$lc;this.$lc=_2;if(this.$jx>9)this.$jx=0}
,isc.A.$h2=function isc_c_EventHandler__clearThread(){if(this.$mr!=null)this.runTeas();if(this.$90m){this.$lc==this.$90m;this.$90m=null}else{this.$lc=null}}
,isc.A.$ms=function isc_c_EventHandler__setThreadExitAction(_1){isc.Timer.setTimeout(_1,0);var _2=this.$mr;if(_2==null)_2=this.$mr=[];_2.add(_1)}
,isc.A.runTeas=function isc_c_EventHandler_runTeas(){this.$lc+="[E]";while(this.$mr!=null){var _1=this.$mr;this.$mr=null;if(this.logIsDebugEnabled()){this.logDebug("firing threadExitActions: "+this.echoAll(_1))}
for(var i=0;i<_1.length;i++){var _3=_1[i];if(isc.isA.String(_3))isc.eval(_3);else _3()}}}
,isc.A.dispatch=function isc_c_EventHandler_dispatch(_1,_2){if(isc.$611!=null){delete isc.$611}
if(isc.Browser.isIE)_2=this.getWindow().event;this.$h1(this.$jy[_2.type]||_2.type);if(isc.Log.supportsOnError){var _3=_1.call(this,_2)}else{try{var _3=_1.call(this,_2)}catch(e){isc.Log.$am(e);throw e;}}
this.$h2();if(_3!=false&&this.$j3[_2.type]){var _4=this.$j3[_2.type](_2);if(_4==false)_3=false}
return _3}
,isc.A.captureEvent=function isc_c_EventHandler_captureEvent(_1,_2,_3,_4){var _5=this.getWindow(),_6=this.$mt;var _7=isc.$aq(this.$j0,this.$j1);_7.$ch=_5;_7.$j2=_4;var _8;if(!_6){if(_1[_2]!=null){var _8=this.$j4[_2]||_2.substring(2);this.$j3[_8]=_1[_2]}
_1[_2]=_7}else{if(isc.Browser.isIE){_1.attachEvent(_2,_7)}else if(isc.Browser.isDOM){_8=this.$j4[_2]||_2.substring(2);_1.addEventListener(_8,_7,false)}else{this.logWarn("Unable to use event listeners in this browser");this.$mt=false;return this.captureEvent(_1,_2,_3,_4)}}
if(_1===_5.document){var _9=(!_6||isc.Browser.isIE)?_2:_8;this.$649[_9]=_7}}
,isc.A.captureEvents=function isc_c_EventHandler_captureEvents(_1){var _2=this;if(window.isc_useEventListeners!=null)_2.$mt=window.isc_useEventListeners;var _3=isc.makeReverseMap(_2.eventTypes);isc.addProperties(_2,{reverseEventTypes:_3});if(_1==null)_1=this.getWindow();var _4=_1.document;isc.Page.setEvent(_2.LOAD,isc.Page.finishedLoading);if(isc.Browser.isIE){_1.attachEvent("onload",_2.handleLoad)}else if(isc.Browser.isDOM&&!isc.Browser.isOpera){_1.addEventListener("load",_2.handleLoad,true)}else{this.captureEvent(_1,"onload",_2.LOAD,_2.handleLoad)}
if(!this.$j8()){this.captureEvent(_1,"onunload",_2.UNLOAD,_2.handleUnload)}
this.captureEvent(_1,"onresize",_2.RESIZE,_2.handleResize);this.captureEvent(_4,"onmousedown",_2.MOUSE_DOWN,_2.handleMouseDown);this.captureEvent(_4,"onmousemove",_2.MOUSE_MOVE,_2.handleMouseMove);this.captureEvent(_4,"onmouseup",_2.MOUSE_UP,_2.handleMouseUp);this.captureEvent(_4,"onclick",_2.CLICK,_2.handleNativeClick);this.captureEvent(_4,"ondblclick",_2.DOUBLE_CLICK,_2.handleNativeClick);this.captureEvent(_4,"onscroll","scroll",_2.handleScroll);this.captureEvent(_4,"onmousewheel",_2.MOUSE_WHEEL,_2.handleMouseWheel);if(isc.Browser.isMoz){_1.addEventListener("DOMMouseScroll",_2.handleDOMMouseScroll,true)}
this.captureEvent(_4,"onmouseout",_2.MOUSE_OUT,_2.handleNativeMouseOut);this.captureEvent(_4,"oncontextmenu",_2.SHOW_CONTEXT_MENU,_2.handleContextMenu);this.captureEvent(_4,"onselectstart",_2.SELECT_START,_2.handleSelectStart);this.captureEvent(_1,"onselectstart",_2.SELECT_START,_2.handleSelectStart);if(isc.Browser.isIE){this.captureEvent(_4,"onselectionchange",_2.SELECTION_CHANGE,_2.handleSelectionChange)}
if(_1.isc_captureKeyEvents!=false){this.captureEvent(_4,"onkeydown",_2.KEY_DOWN,_2.$j9);this.captureEvent(_4,"onkeypress",_2.KEY_PRESS,_2.$kd);this.captureEvent(_4,"onkeyup",_2.KEY_UP,_2.$kb)}
if(isc.Browser.isIE){this.$mm=_1.ondragstart;this.$ml=_4.ondragstart;_4.ondragstart=_1.ondragstart=_2.handleNativeDragStart;this.$69b=_1.onhelp;this.$69a=_4.onhelp;_4.onhelp=_1.onhelp=_2.handleNativeHelp}
if(isc.Browser.isTouch){this.$92f=_2.$92d.READY_FOR_TOUCH;this.captureEvent(_4,"ontouchstart",_2.TOUCH_START,_2.$77p);this.captureEvent(_4,"ontouchmove",_2.TOUCH_MOVE,_2.$77q);this.captureEvent(_4,"ontouchend",_2.TOUCH_END,_2.$77r);this.captureEvent(_4,"ontouchcancel",_2.TOUCH_CANCEL,_2.$86w)}
if(isc.Browser.isMobile){isc.Page.pollPageSize=true}}
,isc.A.$j8=function isc_c_EventHandler__useEventListenerForUnload(){return(isc.Browser.isSafari&&isc.Browser.safariVersion<=412)}
,isc.A.releaseEvents=function isc_c_EventHandler_releaseEvents(_1){var _2=this;if(_1==null)_1=this.getWindow();var _3=_1.document,_4=this.$649;for(var _5 in _4){if(!this.$mt){_3[_5]=null}else{if(isc.Browser.isIE){_3.detachEvent(_5,_4[_5])}else if(isc.Browser.isDOM){_3.removeEventListener(_5,_4[_5],false)}}}
if(isc.Browser.isIE){_3.ondragstart=_1.onhelp=null;_3.onhelp=_1.onhelp=null}
delete this.$649}
,isc.A.getLastEvent=function isc_c_EventHandler_getLastEvent(){return this.lastEvent}
,isc.A.getEventType=function isc_c_EventHandler_getEventType(_1){return(_1||this.lastEvent).eventType}
,isc.A.getTarget=function isc_c_EventHandler_getTarget(_1){return(_1||this.lastEvent).target}
,isc.A.getDragTarget=function isc_c_EventHandler_getDragTarget(){return this.dragTarget}
,isc.A.getX=function isc_c_EventHandler_getX(_1){return(_1||this.lastEvent).x}
,isc.A.getY=function isc_c_EventHandler_getY(_1){return(_1||this.lastEvent).y}
,isc.A.getScreenX=function isc_c_EventHandler_getScreenX(_1){return(_1||this.lastEvent).screenX}
,isc.A.getScreenY=function isc_c_EventHandler_getScreenY(_1){return(_1||this.lastEvent).screenY}
,isc.A.mouseIsDown=function isc_c_EventHandler_mouseIsDown(){return(this.$j6)}
,isc.A.mouseDownTarget=function isc_c_EventHandler_mouseDownTarget(){return(this.mouseDownEvent?this.mouseDownEvent.target:null)}
,isc.A.getButtonNum=function isc_c_EventHandler_getButtonNum(_1){return(_1||this.lastEvent).buttonNum}
,isc.A.leftButtonDown=function isc_c_EventHandler_leftButtonDown(_1){return((_1||this.lastEvent).buttonNum==1)}
,isc.A.rightButtonDown=function isc_c_EventHandler_rightButtonDown(_1){if(!_1)_1=this.lastEvent;return(_1.buttonNum==2)||(_1.button==2)||(isc.Browser.isMac&&_1.ctrlKey)||(isc.Browser.isOpera&&(_1.ctrlKey&&_1.shiftKey))||((isc.Browser.isSafari&&(isc.Browser.safariVersion<125))&&_1.altKey)}
,isc.A.useSyntheticRightButtonEvents=function isc_c_EventHandler_useSyntheticRightButtonEvents(){return isc.Browser.isOpera||(isc.Browser.isSafari&&(isc.Browser.safariVersion<125))}
,isc.A.getKeyEventCharacterValue=function isc_c_EventHandler_getKeyEventCharacterValue(_1){return(_1||this.lastEvent).characterValue}
,isc.A.getKeyEventCharacter=function isc_c_EventHandler_getKeyEventCharacter(_1){return String.fromCharCode(this.getKeyEventCharacterValue(_1))}
,isc.A.getKey=function isc_c_EventHandler_getKey(_1){return(_1||this.lastEvent).keyName||null}
,isc.A.getKeyName=function isc_c_EventHandler_getKeyName(_1){return this.getKey(_1)}
,isc.A.shiftKeyDown=function isc_c_EventHandler_shiftKeyDown(_1){return!!((_1||this.lastEvent).shiftKey)}
,isc.A.ctrlKeyDown=function isc_c_EventHandler_ctrlKeyDown(_1){return!!((_1||this.lastEvent).ctrlKey)}
,isc.A.altKeyDown=function isc_c_EventHandler_altKeyDown(_1){return!!((_1||this.lastEvent).altKey)}
,isc.A.metaKeyDown=function isc_c_EventHandler_metaKeyDown(_1){return!!((_1||this.lastEvent).metaKey)}
,isc.A.getKeyEventProperties=function isc_c_EventHandler_getKeyEventProperties(_1){if(_1==null)_1=this.getWindow().event;var _2=this.lastEvent;_2.nativeKeyTarget=(_1.target||_1.srcElement);_2.keyTarget=this.$ke;if(isc.isA&&isc.DynamicForm&&isc.isA.DynamicForm(this.$ke)){var _3=isc.DynamicForm.$mu(_2.nativeKeyTarget,_2.keyTarget);if(_3&&_3.item)_2.keyTarget=_3.item}
_2.eventType=this.getKeyEventType(_1.type);if(_2.eventType==this.KEY_PRESS){_2.characterValue=this.$mv(_1)}
var _4=this.determineEventKeyName(_1);if(_4!=null){_2.keyName=_4}else if(_2.eventType!=isc.EH.keyPress)delete _2.keyName;_2.nativeKeyCode=_1.keyCode;_2.shiftKey=(_1.shiftKey==true||(isc.Browser.isMoz&&_2.shiftKey));_2.ctrlKey=(_1.ctrlKey==true);_2.altKey=(_1.altKey==true);_2.metaKey=(_1.metaKey==true)}
,isc.A.getKeyEventType=function isc_c_EventHandler_getKeyEventType(_1){if(!_1)return;return this.$i7[_1]}
,isc.A.$mv=function isc_c_EventHandler__determineKeyEventCharacterValue(_1){if(isc.Browser.isIE)return(_1.keyCode||null);if(isc.Browser.isMoz){return(_1.which||null)}
return(_1.which||_1.keyCode||null)}
,isc.A.determineEventKeyName=function isc_c_EventHandler_determineEventKeyName(_1){if(_1==null)return;var _2=_1.keyCode,_3=_1.which,_4=isc.EH,_5=_4.getKeyEventType(_1.type),_6=this.getWindow().event;if(_1.type==this.$689)return this.$688;if(isc.Browser.isIE){if(_5==_4.KEY_DOWN||_5==_4.KEY_UP){return _4.$iz[_2]}
if(_5==_4.KEY_PRESS){var _7=_4.$85t[_4.$85t.length-1];if(_7!=null)return _7;var _8=_4.$ja[_2];if(!_8&&_6&&_6.ctrlKey){_8=isc.EH.$mw(_2)}
return _8}}else if(isc.Browser.isMoz){if(_5==_4.KEY_DOWN||_5==_4.KEY_UP){return _4.$iz[_2]}else if(_5==_4.KEY_PRESS){if(_3==0&&_2!=0)return _4.$iz[_2];var _7=_4.$85t[_4.$85t.length-1];if(_7!=null)return _7;if(_2==0){return _4.$ja[_3]}else{return _4.$iz[_3]}}}else if(isc.Browser.isSafari){if(_5==_4.KEY_DOWN||_5==_4.KEY_UP){return _4.$iz[_2]}
var _9=(_3!=null?_3:_2);if(_9!=null&&_9!=0){if(_6&&_6.ctrlKey){var _10=isc.EH.$ja[_9];if(_10==null){if(_9==10)_10="Enter";else _10=isc.EH.$mw(_9)}
return _10}
var _10=isc.EH.$ja[_9];if(_10==null)_10=isc.EH.$jb[_9]
return _10}else if(_5==this.KEY_PRESS){return null}}else{var _9=_3;if(_9==null||(_9==0&&_2))_9=_2
if(_9!=null)return isc.EH.$ja[_9]}
isc.Log.logWarn("EventHandler.determineEventKeyName(): Unable to determine key for '"+_1.type+"' event. Returning null");return null}
,isc.A.$mw=function isc_c_EventHandler__getKeyNameFromCtrlCharValue(_1){if(_1==30)return"6";if(_1==31)return"-";return String.fromCharCode(_1+64)}
,isc.A.clearKeyEventProperties=function isc_c_EventHandler_clearKeyEventProperties(_1){var _2=this.lastEvent;delete _2.eventType;delete _2.nativeKeyTarget;delete _2.characterValue;delete _2.keyName;delete _2.shiftKey;delete _2.ctrlKey;delete _2.altKey;delete _2.metaKey;this.$85t.remove(_1)}
,isc.A.canvasDestroyed=function isc_c_EventHandler_canvasDestroyed(_1){if(this.clickMaskUp())isc.EH.maskTarget(_1);if(_1.$rq)isc.Page.clearEvent(_1.$nx,_1.$rq);if(this.mouseDownEvent&&this.mouseDownEvent.target==_1)
this.mouseDownEvent.target=null;if(this.lastClickTarget==_1)this.lastClickTarget=null;if(this.lastEvent.target==_1)this.lastEvent.target=null;if(this.lastEvent.keyTarget==_1)this.lastEvent.keyTarget=null;if(this.$ke==_1)this.$ke=null;if(this.$k7==_1)this.$k7=null}
,isc.A.showClickMask=function isc_c_EventHandler_showClickMask(_1,_2,_3,_4){var _5;if(_2==true){_5=true;_2=isc.EH.SOFT}else if(_2==false||_2==null){_5=false;_2=isc.EH.HARD}else{_5=(_2!=isc.EH.HARD)}
if(_3==null)_3=[];else if(!isc.isAn.Array(_3))_3=[_3]
var _6=this,_7=_6.clickMaskRegistry,_8=_6.getFocusCanvas();if(this.logIsInfoEnabled("clickMask")){this.logInfo("showing click mask, action: "+_1+(_5?", autoHide true ":", autoHide false ")+(_4?", ID: "+_4:"")+", focusCanvas: "+_8,"clickMask")}
if(_6.lastMoveTarget)_6.handleEvent(_6.lastMoveTarget,_6.MOUSE_OUT);delete _6.lastMoveTarget;var _9={autoHide:_5,mode:_2,ID:(_4!=null?_4:"cm_"+_6.$j7++),$mx:{}};this.$my(_3,_9);var _10=_7.last();_7.add(_9);_9.clickAction=_1;if(_8!=null&&!_3.contains(_8)){_8.blur("showing clickMask");this.setMaskedFocusCanvas(_8,_9)}else if(_10!=null){this.setMaskedFocusCanvas(_10.$li,_9)}
var _11=this.isHardMask(_9);if(_11){var _12=isc.timeStamp();var _13;if(_7.length>1){var _14=false,_15=[];for(var i=_7.length-2;i>=0;i--){_15.add(_7[i]);if(this.isHardMask(_7[i])){_14=true;break}}
if(_14){for(var i=0;i<_15.length;i++){var _17=_15[i].$mx;if(_17){this.$mz(_17,_3,true,true)}}}else{_13=true;this.$mz(isc.Canvas._canvasList,_3,false,true)}}else{_13=true;this.$mz(isc.Canvas._canvasList,_3,false,true)}}
if(this.maskNativeTargets){if(_10==null){this.showScreenSpan(_9)}else if(_11){this.$m0(_9.$mx)}}
this.updateEventMasks();return _9.ID}
,isc.A.updateEventMasks=function isc_c_EventHandler_updateEventMasks(){var _1=this.clickMaskRegistry,_2=_1?_1[_1.length-1]:null;if(_2&&_2.autoHide){var _3={};isc.addProperties(_3,_2.$mx);this.showEventMasks(false,_3)}else{this.hideEventMasks()}}
,isc.A.$my=function isc_c_EventHandler__applyUnmaskedTargets(_1,_2){_1=this.$m1(_1,_2);for(var i=0;i<_1.length;i++){if(_1[i]==null)continue;_2.$mx[_1[i].getID()]=true}}
,isc.A.$m1=function isc_c_EventHandler__getFullSetOfTargetsToUnmask(_1,_2){if(!_1||_1.length==0||!_2)return _1;for(var i=0;i<_1.length;i++)
_1[i]=this.$m2(_1[i]);var _4=_2.$mx;if(!_2.autoHide&&_1.length>0){var _5=_1.length;for(var i=0;i<_5;i++){var _6=_1[i];if(_6.topElement&&!_4[_6.topElement.getID()]&&!_1.contains(_6.topElement))
{this.logWarn("Attempting to unmask target canvas:"+_6.getID()+" with respect to a hard click mask. "+"This is not a top level Canvas - all ancestors of "+"this Canvas will also be unmasked.","clickMask");_1.add(_6.topElement)}}}
this.$m3(_1);this.$m4(_1);return _1}
,isc.A.$m4=function isc_c_EventHandler__combineDescendantsIntoList(_1){var _2=_1.length;for(var i=0;i<_2;i++){if(_1[i]==null)continue;this.$m5(_1[i],_1)}}
,isc.A.$m5=function isc_c_EventHandler__addDescendantsToList(_1,_2,_3){if(_3&&!_2.contains(_1))_2.add(_1);if(_1.children){for(var i=0;i<_1.children.length;i++){this.$m5(_1.children[i],_2,true)}}
if(isc.DynamicForm&&isc.CanvasItem&&isc.isA.DynamicForm(_1)){var _5=_1.getItems()||[];for(var i=0;i<_5.length;i++){if(_5[i].containerWidget==_1)continue;if(isc.isA.CanvasItem(_5[i])&&isc.isA.Canvas(_5[i].canvas)){this.$m5(_5[i].canvas,_2,true)}}}}
);isc.evalBoundary;isc.B.push(isc.A.$m3=function isc_c_EventHandler__combineTopPeersIntoList(_1){for(var i=0,_3=_1.length;i<_3;i++){var t=_1[i];if(t.parentElement&&_1.contains(t.parentElement))continue;this.$m6(_1[i],_1)}}
,isc.A.$m6=function isc_c_EventHandler__addPeersToList(_1,_2,_3){if(_3&&!_2.contains(_1))_2.add(_1);var _4=_1.peers;if(_4){for(var i=0;i<_4.length;i++){this.$m6(_4[i],_2,true)}}
this.$m5(_1,_2)}
,isc.A.getClickMask=function isc_c_EventHandler_getClickMask(_1){var _2=this.clickMaskRegistry;if(isc.isAn.Object(_1)){return _2.contains(_1)?_1:null}
return _2.find(this.$cp,_1)}
,isc.A.isHardMask=function isc_c_EventHandler_isHardMask(_1){if(!isc.isAn.Object(_1))_1=this.getClickMask(_1);return _1==null?false:(_1.mode==isc.EH.HARD)}
,isc.A.getTopHardMask=function isc_c_EventHandler_getTopHardMask(){var _1=this.clickMaskRegistry;for(var i=_1.length-1;i>=0;i--){if(this.isHardMask(_1[i]))return _1[i]}
return null}
,isc.A.$mz=function isc_c_EventHandler__hardMaskTargets(_1,_2,_3,_4){if(!_1)return;if(_3){for(var _5 in _1){var _6=this.$m2(_5);this.$m7(_6,_2,_4)}}else{for(var i=0;i<_1.length;i++){var _6=this.$m2(_1[i]);this.$m7(_6,_2,_4)}}}
,isc.A.$m7=function isc_c_EventHandler__hardMaskTarget(_1,_2,_3){if(!isc.isA.Canvas(_1)||_1.destroyed){isc.Log.logWarn("showClickMask - attempting to remove invalid object :"+isc.Log.echo(_1)+" from tab order","clickMask");return}
if(_2&&_2[_1.getID()])return;if(_1.isDrawn()){if(_1.parentElement==null){if(!_3&&this.$m8&&this.$m8.isDrawn()&&_1.getZIndex()>=this.$m8.getZIndex())
{this.logDebug("lowering zIndex of: "+_1,"clickMask");_1.setZIndex(isc.EH.$m8.getZIndex()-1)}}}
if(_1.accessKey!=null&&_1.isDrawn()){_1.$m9(null)}}
,isc.A.hideClickMask=function isc_c_EventHandler_hideClickMask(_1){if(this.logIsInfoEnabled("clickMask"))
this.logInfo("hideClickMask called with ID: "+_1,"clickMask");var _2=this.clickMaskRegistry;if(_2.length==0)return;if(_1==null){this.hideClickMask(_2[0].ID)
if(_2.length>0){this.hideClickMask()}else{this.logInfo("all clickmasks hidden","clickMask")}
return}
var _3=this.getClickMask(_1);if(_3==null)return;var _4=_2.indexOf(_3),_5=(_4==(_2.length-1)),_6=this.isHardMask(_3),_7=(_4>0?_2[_4-1]:null),_8,_9;if(this.logIsInfoEnabled("clickMask")){var _10="hiding clickMask ID: "+_1;if(_6)_10+="[autoHide:false]";else _10+="[autoHide:true]";if(_2.length<2){_10+=", all masks hidden"}else{_10+=" with index: "+_4+" of "+(_2.length-1)}
this.logInfo(_10,"clickMask")}
if(_6){_9=this.$na(_4,false);var _11=this.$na(_4,true);_8=(_11==null)}
var _12=_3.$li,_13=_3.$mx;_2.remove(_3);if(_7!=null){if(_13!=null){if(_7.$mx==null)_7.$mx={};isc.addProperties(_7.$mx,_13)}
if(_12&&!_7.$mx[_12.getID()]){this.setMaskedFocusCanvas(_12,_7)}}
if(this.$m8){if(_5&&_7==null){if(isc.Browser.isIE){isc.Timer.setTimeout({target:this.$m8,methodName:"hide"},0)}else{this.$m8.hide()}}else if(_8){if(_9){var _13=isc.addProperties({},_9.$mx);var _14=_2.length-1,_15=_2[_14];while(_15!=_9){isc.addProperties(_13,_15.$mx);_14--;_15=_2[_14]}
this.$m0(_13)}else this.$m8.sendToBack()}
if(_6){var _16;if(_9!=null){_16=[];for(var i=_4-1;i>=0;i--){var _18=_2[i];_16.addList(isc.getKeys(_18.$mx));if(_18==_9)break}}else{_16=isc.Canvas._canvasList}
this.$nb(_16,true)}
if(_12!=null&&!_12.destroyed&&!this.targetIsMasked(_12)){if(this.logIsInfoEnabled("clickMask")){this.logInfo("focusing in "+_12+" on clickMask hide "+"with current focusCanvas: "+isc.EH.$ke,"clickMask")}
var _19=(isc.Browser.isIE&&this.lastEvent.eventType==this.MOUSE_DOWN)
if(_19){this.$k7=_12}else{try{_12.focus()}catch(e){}}}}
this.updateEventMasks()}
,isc.A.$na=function isc_c_EventHandler__getNextHardMask(_1,_2){var _3=this.clickMaskRegistry;if(_2){for(var i=_1+1;i<_3.length;i++){if(this.isHardMask(_3[i]))return _3[i]}}else{for(var i=_1-1;i>=0;i--){if(this.isHardMask(_3[i]))return _3[i]}}
return null}
,isc.A.$m2=function isc_c_EventHandler__getCanvas(_1){if(isc.isA.String(_1))return window[_1];return _1}
,isc.A.$nb=function isc_c_EventHandler__hardUnmaskTargets(_1,_2){if(!_1||_1.length==0)return;for(var i=0;i<_1.length;i++){var _4=this.$m2(_1[i]);if(!_4)continue;if(_4.accessKey!=null&&_4.isDrawn()){_4.$m9(_4.accessKey)}
if(!_4.isDrawn()&&isc.isA.DynamicForm&&isc.isA.DynamicForm(_4)&&_4.items&&_4.items.length>0)
{var _5=_4.items[0];if(_5.containerWidget!=_4)_4=_5.containerWidget}
if(!_2&&_4.parentElement==null&&_4.getZIndex()<=this.$m8.getZIndex()&&_4!=this.$m8)
{_4.setZIndex(this.$m8.getZIndex()+1);this.logDebug("raised above screenspan: "+_4,"clickMask")}}}
,isc.A.clickMaskUp=function isc_c_EventHandler_clickMaskUp(_1){var _2=this.clickMaskRegistry;if(_1==null)return(_2.length>0);else return(_2.find("ID",_1)!=null)}
,isc.A.getAllClickMaskIDs=function isc_c_EventHandler_getAllClickMaskIDs(){var _1=this.clickMaskRegistry;if(_1.length<1)return[];return _1.getProperty("ID")}
,isc.A.showScreenSpan=function isc_c_EventHandler_showScreenSpan(_1){if(!this.$m8){this.$m8=isc.ScreenSpan.create({ID:"isc_EH_screenSpan",pointersToThis:[{object:this,property:"$m8"}]},this.clickMaskProperties)}
var _2=this.$m8;_2.show();if(!this.isHardMask(_1)){_2.sendToBack()}else{this.$m0(_1.$mx)}}
,isc.A.$m0=function isc_c_EventHandler__adjustSpanZIndex(_1){this.$nc=true;var _2;for(var _3 in _1){var _4=this.$m2(_3);if(!_4||_4.destroyed||_4.parentElement!=null){continue}
if(_4.masterElement&&_1[_4.masterElement.getID()])continue;_4.bringToFront();if(_2==null)_2=_4.getZIndex(true);if(_4.peers){for(var i=0;i<_4.peers.length;i++){if(!_4.peers[i].isDrawn())continue;_2=Math.min(_2,_4.peers[i].getZIndex(true))}}}
if(_2!=null)this.$m8.setZIndex(_2-1);else this.$m8.bringToFront();this.$nc=false}
,isc.A.maskTarget=function isc_c_EventHandler_maskTarget(_1,_2){return this.maskTargets(_1,_2)}
,isc.A.maskTargets=function isc_c_EventHandler_maskTargets(_1,_2,_3){var _4=this.clickMaskRegistry;if(_1==null||_4.length==0)return;if(!isc.isAn.Array(_1))_1=[_1];else if(_1.length==0)return;var _5=(_2==null?_4[0]:(isc.isA.String(_2)?this.getClickMask(_2):_2));if(_5==null){this.logInfo("maskTargets called with invalid maskID - returning.","event")
return}
var _6=_1.length
for(var i=0;i<_6;i++){var _8=_1[i];if(!_3&&_8.children!=null){this.$m5(_8,_1)}
var _9=_8.parentElement;while(_9!=null){if(!_1.contains(_9)){_1.add(_9);if(_3)this.$m6(_9,_1)}
_9=_9.parentElement}
if(_3||!_9){this.$m6(_8,_1)}
if(_9)this.$m6(_9,_1)}
var _10=_4.indexOf(_5);var _11;for(var i=_10;i<_4.length;i++){_14=_4[i];if(this.isHardMask(_14))_11=i}
var _12;if(_11!=null)_12=[];for(var n=0;n<_1.length;n++){var _8=_1[n];if(_8.hasFocus)_8.blur();var _14,_11,_15=null;for(var i=_10;i<_4.length;i++){_14=_4[i];if(_14.$mx[_8.getID()]){_15=i;delete _14.$mx[_8.getID()]}}
if(_11!=null&&_15!=null&&(_11<=_15)){_12.add(_8)}}
if(_11!=null)this.$mz(_12,null,false,false)}
,isc.A.addUnmaskedTarget=function isc_c_EventHandler_addUnmaskedTarget(_1,_2){return this.addUnmaskedTargets(_1,_2)}
,isc.A.addUnmaskedTargets=function isc_c_EventHandler_addUnmaskedTargets(_1,_2){if(isc.$nd&&this.$nc)return;var _3=this.clickMaskRegistry;if(_1==null||_3.length==0)return;if(!isc.isAn.Array(_1))_1=[_1];if(_1.length==0)return;var _4;if(_2==null){_4=_3.last()}else{if(isc.isA.String(_2))_4=this.getClickMask(_2);else _4=_2}
if(_4==null){this.logInfo("addUnmaskedTargets called with invalid maskID - returning.","clickMask")
return}
var _5=_4;while(_5&&!this.isHardMask(_5)){_5=_3[_3.indexOf(_5)-1]}
if(_5!=null){if(isc.$nd&&_1.length==1&&_1[0].topElement!=null){return}}
_1=this.$m1(_1,_4);if(this.logIsDebugEnabled("clickMask")){this.logDebug("Added unmasked targets:"+_1.getProperty("ID")+" [+ decendants] to clickMask with ID: "+_4.ID,"clickMask")}
var _6=false;for(var i=_3.indexOf(_4)+1;i<_3.length;i++){if(this.isHardMask(_3[i]))_6=true}
for(var n=0;n<_1.length;n++){var _9=_1[n];if(_4.$mx==null)_4.$mx={};_4.$mx[_9.getID()]=true}
if(!_6){this.$nb(_1)}}
,isc.A.targetIsMasked=function isc_c_EventHandler_targetIsMasked(_1,_2,_3){var _4=this.clickMaskRegistry;if(_4.length==0)return false;if(_1==null)return true;var _5;if(_2==null)_5=_4.last();else if(isc.isA.String(_2))_5=_4.find("ID",_2);else _5=_2;if(!isc.isAn.Object(_5)){this.logWarn("EventHandler.targetIsMasked() passed invalid maskID:"+_2,"clickMask");return false}
var _6=_4.indexOf(_5);var _7=false;for(var i=_6;i<_4.length;i++){if(i!=_6)_5=_4[i];if(_3){if(_5.mode==isc.EH.HARD||_5.mode==isc.EH.SOFT_CANCEL){_7=true}else{continue}}
if(_5.$mx){if(_5.$mx[_1.getID()])return false;if(isc.isA.DynamicForm!=null&&isc.isA.DynamicForm(_1)){var _9=_1.$ne(isc.EH.lastEvent);if(_9&&_9.item&&_9.item.form==_1&&_9.item.containerWidget!=_1&&_5.$mx[_9.item.containerWidget.getID()])return false}}}
return(_3&&!_7?false:true)}
,isc.A.clickMaskClick=function isc_c_EventHandler_clickMaskClick(_1){var _2=this.clickMaskRegistry.duplicate(),_3=_2.last();while(_3!=null&&(this.targetIsMasked(_1)||_1==this.$m8)){if(this.logIsInfoEnabled("clickMask")){this.logInfo("mouseDown on masked "+_1+(_3.clickAction!=null?" firing clickAction, ":"")+(_3.autoHide?"will hide mask"+(_3.mode==isc.EH.SOFT_CANCEL?" and block click":""):"will block click"))}
var _4=(_3.mode!=isc.EH.SOFT);this.$lj(_3)
if(_4)return false;_3=_2[_2.indexOf(_3)-1]}
return true}
,isc.A.$lj=function isc_c_EventHandler__clickMaskClick(_1){var _2=_1.autoHide,_3=_1.clickAction;if(_2==true)this.hideClickMask(_1.ID);if(_3!=null)this.fireCallback(_3)}
);isc.B._maxIndex=isc.C+171;isc.EventHandler.captureEvents();isc.ClassFactory.defineClass("Element",null,null,true);isc.A=isc.Element;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$wq=window.isc_insertAfterBodyStart;isc.A.$wr="isc_global_insertion_marker";isc.A.$n7="afterBegin";isc.A.$n9="afterEnd";isc.A.$n6="beforeBegin";isc.A.$n8="beforeEnd";isc.A.$84e=(isc.Browser.isIE&&!isc.Browser.isIE9)||isc.Browser.isOpera;isc.A.$w2=isc.Browser.isMoz?"border-left-width":"borderLeftWidth";isc.A.$w3=isc.Browser.isMoz?"border-top-width":"borderTopWidth";isc.A.$oi=isc.Browser.isMoz?"margin-left":"marginLeft";isc.A.$ok=isc.Browser.isMoz?"margin-top":"marginTop";isc.A.$27r="none";isc.A.cacheCount=0;isc.A.uncachedCount=0;isc.A.$808="$808";isc.A.$809="$809";isc.A.cacheOffsetCoords=true;isc.A.$nk={};isc.A.$419={border:"borderStyle",borderWidth:"borderStyle",borderLeft:"borderLeftStyle",borderRight:"borderRightStyle",borderTop:"borderTopStyle",borderBottom:"borderBottomStyle",borderLeftWidth:"borderLeftStyle",borderRightWidth:"borderRightStyle",borderBottomWidth:"borderBottomStyle",borderTopWidth:"borderTopStyle"};isc.A.$nl={};isc.A.$39=";";isc.A.vendorCSSPrefix=(isc.Browser.isMoz?"-moz-":isc.Browser.isSafari?"-webkit-":isc.Browser.isOpera?"-o-":"");isc.B.push(isc.A.get=function isc_c_Element_get(_1,_2){_2=_2||this.getDocument();if(isc.Browser.isDOM)return _2.getElementById(_1)}
,isc.A.$mk=function isc_c_Element__getElementFromSelection(_1){if(!_1)_1=document;if(isc.Browser.isIE){var _2=_1.selection,_3=_2.type.toLowerCase(),_4=(_3=="text"||_3=="none");if(!_2)return null;if(_4){var _5;try{_5=_2.createRange()}catch(e){}
return _5?_5.parentElement():null}else{var _5=_2.createRange(),_6;for(var i=0;i<_5.length;i++){if(!_6){_6=_5(i).parentElement}else{while(!_6.contains(_5(i))){_6=_6.parentElement}}}
return _6}}}
,isc.A.findAttribute=function isc_c_Element_findAttribute(_1,_2,_3){if(!_1)return null;if(_1[_2]==_3||(_1.getAttribute&&_1.getAttribute(_2)==_3)){return _1}
var _4=_1.childNodes;for(var i=0;i<_4.length;i++){var _6=this.findAttribute(_4[i],_2,_3);if(_6)return _6}
return null}
,isc.A.getInsertionMarkerHTML=function isc_c_Element_getInsertionMarkerHTML(){return"<span id='"+this.$wr+"' style='display:none'></span>"}
,isc.A.getInsertionMarker=function isc_c_Element_getInsertionMarker(){return document.getElementById(this.$wr)}
,isc.A.createAbsoluteElement=function isc_c_Element_createAbsoluteElement(_1,_2){var _3=_2||this.getWindow(),_4=this.getDocumentBody(true);if(_4==null&&!isc.Element.noBodyTagMessageShown){isc.Element.noBodyTagMessageShown=true;var _5="Error: Attempt to write content into a page outside the BODY tag.  Isomorphic "+"SmartClient requires this tag be present and all widgets be written out inside "+"it.\r"+"Please ensure your file has a BODY tag and any code to draw SmartClient widgets "+"is enclosed in this tag.";this.logError(_5);return}
if(this.$wq){return isc.Element.insertAdjacentHTML(_4,this.$n7,_1,true)}
if(isc.Browser.isIE){if(!this.$w7){if(_4.childNodes.length<2){isc.Element.insertAdjacentHTML(_4,this.$n7,this.getInsertionMarkerHTML())}else{var _6=_4.lastChild;while(_6&&_6.nodeType==3)_6=_6.previousSibling;if(_6!=null){isc.Element.insertAdjacentHTML(_6,this.$n6,this.getInsertionMarkerHTML())}else{isc.Element.insertAdjacentHTML(_4,this.$n7,this.getInsertionMarkerHTML())}}
this.$w7=this.getInsertionMarker()}
return isc.Element.insertAdjacentHTML(this.$w7,this.$n9,_1,true)}else{return isc.Element.insertAdjacentHTML(_4,this.$n8,_1,true)}}
,isc.A.insertAdjacentHTML=function isc_c_Element_insertAdjacentHTML(_1,_2,_3,_4){if(isc.isA.String(_1))_1=isc.Element.get(_1);if(!_1)this.logWarn("insertAdjacentHTML: element is null for where: '"+_2+"' with html: "+_3);if(isc.Browser.isIE||isc.Browser.isOpera){_1.insertAdjacentHTML(_2,_3);return}
var _5;if(_4){var _6=_1.ownerDocument.createElement("DIV");_6.innerHTML=_3;_5=_6.firstChild}else{var _7=_1.ownerDocument.createRange();_7.setStartBefore(_1);_5=_7.createContextualFragment(_3)}
switch(_2){case"beforeBegin":_1.parentNode.insertBefore(_5,_1)
break;case"afterBegin":_1.insertBefore(_5,_1.firstChild);break;case"beforeEnd":_1.appendChild(_5);break;case"afterEnd":if(_1.nextSibling)_1.parentNode.insertBefore(_5,_1.nextSibling);else _1.parentNode.appendChild(_5);break}
if(_4)return _5}
,isc.A.clear=function isc_c_Element_clear(_1,_2){if(_1==null)return;if(!_2&&isc.Page.isLoaded()&&isc.Browser.isIE){_1.outerHTML=isc.emptyString;return}
if(_1.parentNode){_1.parentNode.removeChild(_1)}else{isc.Log.logWarn("element parentNode null");_1.innerHTML=""}}
,isc.A.isBorderBox=function isc_c_Element_isBorderBox(_1){if(!_1)return;if(!isc.Browser.isMoz)return isc.Browser.isBorderBox;return(_1.style.MozBoxSizing=="border-box")}
,isc.A.getScrollHeight=function isc_c_Element_getScrollHeight(_1){if(_1==null)return 0;var _2=((_1.scrollHeight!=null&&_1.scrollHeight!="undefined")?_1.scrollHeight:_1.offsetHeight);var _3=this.$yq(_1);return _3>_2?_3:_2}
,isc.A.$yq=function isc_c_Element__getPositionedChildrenBottom(_1){if(_1.childNodes==null)return 0;var _2=0,_3=document.ELEMENT_NODE||1,_4=this.logIsDebugEnabled("sizing");for(var i=0;i<_1.childNodes.length;i++){var _6=_1.childNodes.item(i);if(_6.nodeType!=_3)continue;var _7=isc.Element.getComputedStyleAttribute(_6,"position");var _8=0;if(_7==isc.Canvas.ABSOLUTE||_7==isc.Canvas.RELATIVE){_8+=isc.Element.getOffsetTop(_6)}else{continue}
var _9=_6.getAttribute("eventProxy"),_10;if(_9!=null&&!isc.isAn.emptyString(_9)&&!window[_9].$s0&&isc.isA.Function(window[_9].getVisibleHeight))
{_10=window[_9].getVisibleHeight()}else{_10=isc.Element.getVisibleHeight(_6)}
var _11=_8+_10;if(_7==isc.Canvas.ABSOLUTE&&(_1.style.overflow==isc.Canvas.SCROLL||_1.style.overflow==isc.Canvas.AUTO||_1.style.overflow==isc.Canvas.HIDDEN))
_11-=isc.Element.getBottomMargin(_6);if(_11>_2)_2=_11}
return _2}
,isc.A.getScrollWidth=function isc_c_Element_getScrollWidth(_1){if(_1==null)return 0;var _2=((_1.scrollWidth!=null&&_1.scrollWidth!="undefined")?_1.scrollWidth:_1.offsetWidth);var _3=this.$yr(_1);return _3>_2?_3:_2}
,isc.A.$yr=function isc_c_Element__getPositionedChildrenRight(_1){if(_1.childNodes==null)return 0;var _2=0,_3=document.ELEMENT_NODE||1,_4=this.logIsDebugEnabled("sizing");for(var i=0;i<_1.childNodes.length;i++){var _6=_1.childNodes.item(i);if(_6.nodeType!=_3)continue;var _7=isc.Element.getComputedStyle(_6,["position","display","left"]);var _8=0;if(_7.position==isc.Canvas.ABSOLUTE||_7.position==isc.Canvas.RELATIVE)
{_8=isc.Element.getOffsetLeft(_6)}else{continue}
var _9=_6.getAttribute("eventProxy"),_10;if(_9!=null&&!isc.isAn.emptyString(_9)&&!window[_9].$sv&&isc.isA.Function(window[_9].getVisibleWidth))
{_10=window[_9].getVisibleWidth()}else{_10=isc.Element.getVisibleWidth(_6)}
var _11=_8+_10;if(_1.style.overflow==isc.Canvas.SCROLL||_1.style.overflow==isc.Canvas.HIDDEN||_1.style.overflow==isc.Canvas.AUTO){_11-=isc.Element.getRightMargin(_6)}
if(_11>_2)_2=_11;if(_4){this.logInfo("getChildNodesRight: child node "+i+" of "+_1.childNodes.length+" ("+this.echoLeaf(_6)+")"+" left:"+_8+", width: "+_10+", right:"+_11,"sizing")}}
return _2}
,isc.A.getElementRect=function isc_c_Element_getElementRect(_1){var _2=this.getDocumentBody(),_3=this.getLeftOffset(_1,_2),_4=this.getTopOffset(_1,_2);var _5=0,_6=0;if(_1.style&&_1.style.overflow=="visible"){_5=this.getScrollWidth(_1);_6=this.getScrollHeight(_1)}
_5=Math.max(_1.offsetWidth,_1.clientWidth,_5);_6=Math.max(_1.offsetHeight,_1.clientHeight,_6);return[_3,_4,_5,_6]}
,isc.A.getInnerWidth=function isc_c_Element_getInnerWidth(_1){var _2=_1.style.width;if(_2!=null&&!isc.isAn.emptyString(_2)){_2=parseInt(_2);if(isc.isA.Number(_2))return _2}
var _3=_1.clientWidth,_4=parseInt(this.getComputedStyleAttribute("paddingLeft")),_5=parseInt(this.getComputedStyleAttribute("paddingRight")),_6=_4+_5;if(isc.isA.Number(_6))_3-=_6;return _3}
,isc.A.getInnerHeight=function isc_c_Element_getInnerHeight(_1){var _2=_1.style.height;if(_2!=null&&!isc.isAn.emptyString(_2)){_2=parseInt(_2);if(isc.isA.Number(_2))return _2}
var _3=_1.clientHeight,_4=parseInt(this.getComputedStyleAttribute("paddingTop")),_5=parseInt(this.getComputedStyleAttribute("paddingBottom")),_6=_4+_5;if(isc.isA.Number(_6))_3-=_6;return _3}
,isc.A.getNativeInnerWidth=function isc_c_Element_getNativeInnerWidth(_1){if(isc.Browser.isMoz)return this.getInnerWidth(_1);var _2=_1.offsetWidth;if(!_2)_2=this.getInnerWidth(_1);return _2}
,isc.A.getNativeInnerHeight=function isc_c_Element_getNativeInnerHeight(_1){if(isc.Browser.isMoz)return this.getInnerHeight(_1);var _2=_1.offsetHeight;if(!_2)_2=this.getInnerHeight(_1);return _2}
,isc.A.getTopMargin=function isc_c_Element_getTopMargin(_1){if(_1!=null){var _2;if(_1.style!=null)_2=parseInt(_1.style.marginTop);if(isc.isA.Number(_2))return _2;if(_1.className!=null)return isc.Element.$th(_1.className)}
return 0}
,isc.A.getBottomMargin=function isc_c_Element_getBottomMargin(_1){if(_1!=null){var _2;if(_1.style!=null)_2=parseInt(_1.style.marginBottom);if(isc.isA.Number(_2))return _2;if(_1.className!=null)return isc.Element.$ti(_1.className)}
return 0}
,isc.A.getLeftMargin=function isc_c_Element_getLeftMargin(_1){if(_1!=null){var _2;if(_1.style!=null)_2=parseInt(_1.style.marginLeft);if(isc.isA.Number(_2))return _2;if(_1.className!=null)return isc.Element.$tf(_1.className)}
return 0}
,isc.A.getRightMargin=function isc_c_Element_getRightMargin(_1){if(_1!=null){var _2;if(_1.style!=null)_2=parseInt(_1.style.marginRight);if(isc.isA.Number(_2))return _2;if(_1.className!=null)return isc.Element.$tg(_1.className)}
return 0}
,isc.A.getHMarginSize=function isc_c_Element_getHMarginSize(_1){return isc.Element.getLeftMargin(_1)+isc.Element.getRightMargin(_1)}
,isc.A.getVMarginSize=function isc_c_Element_getVMarginSize(_1){return isc.Element.getTopMargin(_1)+isc.Element.getBottomMargin(_1)}
,isc.A.getTopBorderSize=function isc_c_Element_getTopBorderSize(_1){if(_1==null)return 0;if(isc.Browser.isOpera&&_1.currentStyle.borderTopStyle==this.$27r)return 0;var _2=(this.$84e?parseInt(_1.currentStyle.borderTopWidth):parseInt(isc.Element.getComputedStyleAttribute(_1,"borderTopWidth")));return isNaN(_2)?0:_2}
,isc.A.getBottomBorderSize=function isc_c_Element_getBottomBorderSize(_1){if(_1==null)return 0;if(isc.Browser.isOpera&&_1.currentStyle.borderBottomStyle==this.$27r)return 0;var _2=(this.$84e?parseInt(_1.currentStyle.borderBottomWidth):parseInt(isc.Element.getComputedStyleAttribute(_1,"borderBottomWidth")));return isNaN(_2)?0:_2}
,isc.A.getLeftBorderSize=function isc_c_Element_getLeftBorderSize(_1){if(_1==null)return 0;if(isc.Browser.isOpera&&_1.currentStyle.borderLeftStyle==this.$27r)return 0;var _2=(this.$84e?parseInt(_1.currentStyle.borderLeftWidth):parseInt(isc.Element.getComputedStyleAttribute(_1,"borderLeftWidth")));return isNaN(_2)?0:_2}
,isc.A.getRightBorderSize=function isc_c_Element_getRightBorderSize(_1){if(_1==null)return 0;if(isc.Browser.isOpera&&_1.currentStyle.borderRightStyle==this.$27r)return 0;var _2=(this.$84e?parseInt(_1.currentStyle.borderRightWidth):parseInt(isc.Element.getComputedStyleAttribute(_1,"borderRightWidth")));return isNaN(_2)?0:_2}
,isc.A.getVBorderSize=function isc_c_Element_getVBorderSize(_1){return isc.Element.getTopBorderSize(_1)+isc.Element.getBottomBorderSize(_1)}
,isc.A.getHBorderSize=function isc_c_Element_getHBorderSize(_1){return isc.Element.getLeftBorderSize(_1)+isc.Element.getRightBorderSize(_1)}
,isc.A.getVisibleWidth=function isc_c_Element_getVisibleWidth(_1){if(_1==null)return 0;var _2=isc.Element.getComputedStyleAttribute(_1,"overflow"),_3;if(_2==isc.Canvas.VISIBLE||!isc.isA.Number(parseInt(_1.style.width))){_3=isc.Element.getScrollWidth(_1)+isc.Element.getHBorderSize(_1)}else{_3=parseInt(_1.style.width)}
return _3+isc.Element.getHMarginSize(_1)}
,isc.A.getVisibleHeight=function isc_c_Element_getVisibleHeight(_1){if(_1==null)return 0;var _2=isc.Element.getComputedStyleAttribute(_1,"overflow"),_3;if(_2==isc.Canvas.VISIBLE||!isc.isA.Number(parseInt(_1.style.height))){_3=isc.Element.getScrollHeight(_1)+isc.Element.getVBorderSize(_1)}else{_3=parseInt(_1.style.height)}
return _3+isc.Element.getVMarginSize(_1)}
,isc.A.getOffsetLeft=function isc_c_Element_getOffsetLeft(_1){if(_1==null){this.logWarn("getOffsetLeft: passed null element");return 0}
var _2=_1.offsetLeft;if(isc.Browser.isIE&&isc.Page.isRTL()&&_2<0){_2=-_2}
if(_1.$ys==_2){return _1.$yt}else{}
var _3=parseInt(isc.Element.getComputedStyleAttribute(_1,"marginLeft"));if(isc.isA.Number(_3)&&_3>0){_2-=_3}
var _4=this.getDocumentBody(),_5,_6="px",_7=_1.style.position;if(isc.Browser.isMoz){if(_1.offsetParent==null)return _2;if(_1.offsetParent!=_4){_5=this.ns.Element.getComputedStyle(_1.offsetParent,["borderLeftWidth","overflow"]);var _8=isc.Browser.geckoVersion,_9=(_5.overflow!="visible")&&(_8>=20051111||(_7==isc.Canvas.ABSOLUTE&&_5.overflow!="hidden")),_10=(_8>20020826&&(_1.offsetParent.style.MozBoxSizing=="border-box"));if(_10!=_9){if(_10){_2-=(isc.isA.Number(parseInt(_5.borderLeftWidth))?parseInt(_5.borderLeftWidth):0)}
if(_9){_2+=(isc.isA.Number(parseInt(_5.borderLeftWidth))?parseInt(_5.borderLeftWidth):0)}}}}
if(isc.Browser.isIE&&!isc.Browser.isIE8Strict&&!isc.Browser.isIE9){var _11=_1.offsetParent,_5;if(_5!=_4)_5=_11.currentStyle;var _12=(_1.currentStyle.height!=isc.Canvas.AUTO||_1.currentStyle.width!=isc.Canvas.AUTO);var _13=true;while(_11!=_4){if(_5.position==isc.Canvas.ABSOLUTE)_13=false;if(_5.width==isc.Canvas.AUTO&&_5.height==isc.Canvas.AUTO&&_5.position==isc.Canvas.RELATIVE){if(_13&&isc.isA.String(_5.borderLeftWidth)&&_5.borderLeftWidth.contains(_6)){_2-=parseInt(_5.borderLeftWidth)}
if(_12){if(isc.isA.String(_5.marginLeft)&&_5.marginLeft.contains(_6))
{var _14=parseInt(_5.marginLeft);if(_14>0)_2-=_14}
if(_11.offsetParent!=_4){var _15=_11.offsetParent.currentStyle.padding;if(isc.isA.String(_15)&&_15.contains(_6)){_2-=parseInt(_15)}}else{_2-=(_4.leftMargin?parseInt(_4.leftMargin):0)}}}
_7=_11.style.position;_11=_11.offsetParent;if(_11!=document.body){_5=_11.currentStyle}}}
if(isc.Browser.isSafari&&isc.Browser.safariVersion<525.271){if(_1.offsetParent!=null&&_1.offsetParent!=_4){var _16=this.ns.Element.getComputedStyle(_1.offsetParent,["borderLeftWidth"]).borderLeftWidth;if(_16!=null)_16=parseInt(_16);if(isc.isA.Number(_16))_2-=_16}}
_1.$ys=_1.offsetLeft;_1.$yt=_2;return _2}
,isc.A.getOffsetTop=function isc_c_Element_getOffsetTop(_1){if(_1==null){this.logWarn("getOffsetTop: passed null element");return 0}
var _2=_1.offsetTop;if(_1.$yu==_2){return _1.$yv}else{}
var _3=parseInt(isc.Element.getComputedStyleAttribute(_1,"marginTop"));if(isc.isA.Number(_3)&&_3>0){_2-=_3}
var _4=this.getDocumentBody(),_5,_6="px",_7=_1.style.position;if(isc.Browser.isMoz){if(_1.offsetParent==null)return _2;if(_1.offsetParent!=_4){_5=this.ns.Element.getComputedStyle(_1.offsetParent,["overflow","borderTopWidth"]);var _8=(_5.overflow!="visible")&&(isc.Browser.geckoVersion>=20051111||(_7==isc.Canvas.ABSOLUTE&&_5.overflow!="hidden")),_9=(isc.Browser.geckoVersion>20020826&&_1.offsetParent.style.MozBoxSizing=="border-box");if(_9!=_8){if(_9){_2-=(isc.isA.Number(parseInt(_5.borderTopWidth))?parseInt(_5.borderTopWidth):0)}
if(_8){_2+=(isc.isA.Number(parseInt(_5.borderTopWidth))?parseInt(_5.borderTopWidth):0)}}}}
if(isc.Browser.isIE&&!isc.Browser.isIE9){if(_1.offsetParent&&_1.offsetParent!=_4){_5=_1.offsetParent.currentStyle;if(_5.position==isc.Canvas.RELATIVE&&_5.height==isc.Canvas.AUTO&&_5.width==isc.Canvas.AUTO&&isc.isA.String(_5.borderTopWidth)&&_5.borderTopWidth.contains(_6)){_2-=parseInt(_5.borderTopWidth)}}}
if(isc.Browser.isSafari&&isc.Browser.safariVersion<525.271){if(_1.offsetParent&&_1.offsetParent!=_4){var _10=this.ns.Element.getComputedStyle(_1.offsetParent,["borderTopWidth"]).borderTopWidth;if(_10!=null)_10=parseInt(_10);if(isc.isA.Number(_10))_2-=_10}}
_1.$yu=_1.offsetTop;_1.$yv=_2;return _2}
,isc.A.getLeftOffset=function isc_c_Element_getLeftOffset(_1,_2,_3,_4){return this.getOffset(isc.Canvas.LEFT,_1,_2,_3,_4)}
,isc.A.getTopOffset=function isc_c_Element_getTopOffset(_1,_2,_3){return this.getOffset(isc.Canvas.TOP,_1,_2,null,_3)}
,isc.A.getOffset=function isc_c_Element_getOffset(_1,_2,_3,_4,_5){var _6=_5||isc.isA.Canvas(_2),_7=_5||_3==null||isc.isA.Canvas(_3);var _8=_6&&_7&&this.cacheOffsetCoords&&(_2.cacheOffsetCoords!=false);var _9=(_1==isc.Canvas.LEFT)?this.$808:this.$809;if(_8&&_2[_9]!=null){var _10=_2[_9][_3?_3.ID:this.$27r];if(_10!=null){this.cacheCount++;return _10}}
this.uncachedCount++;var _11=_6?_2.getClipHandle():_2;var _12;if(_3==null)_12=this.getDocumentBody();else if(_7)_12=_3.getHandle();else _12=_3;if(_12==null||_11==null){return 0}
var _13=_11.offsetParent;if(isc.Browser.isMoz&&_13==null)return 0;var _14=_12.offsetParent,_15=_11,_16=0,_17=(_1==isc.Canvas.LEFT),_18=(_17?this.$w2:this.$w3),_19=(_17?this.$oi:this.$ok);if(!_17)_4=false;else if(_4==null)_4=(isc.Page.getTextDirection()==isc.Canvas.RTL);var _20=0;while(_13!=_12&&_13!=_14){var _21=(_17?this.ns.Element.getOffsetLeft(_15):this.ns.Element.getOffsetTop(_15));_16+=_21;if(!_4){_16-=((_17?_13.scrollLeft:_13.scrollTop)||0)}else{if(isc.isA.Number(_13.scrollLeft)){var _22=(_13.scrollWidth-_13.clientWidth);_16+=(_22-_13.scrollLeft)}}
var _23,_24,_25;if(this.$84e){_23=_13.currentStyle;if(isc.Browser.isOpera&&(_17?_23.borderLeftStyle==this.$27r:_23.borderTopStyle==this.$27r))_24=null;else _24=parseInt(_23[_18]);if(isc.isA.Number(_24))_16+=_24;_25=parseInt(_23[_19]);if(isc.isA.Number(_25)&&_25>0)_16+=_25}else if(isc.Browser.isMoz){_23=document.defaultView.getComputedStyle(_13,null);_24=parseInt(_23.getPropertyValue(_18));_16+=_24;_25=parseInt(_23.getPropertyValue(_19));if(_25>0)_16+=_25}else{_24=parseInt(this.getComputedStyleAttribute(_13,_18));if(isc.isA.Number(_24))_16+=_24;_25=parseInt(this.getComputedStyleAttribute(_13,_19));if(isc.isA.Number(_25)&&_25>0)_16+=_25}
_15=_13;_13=_15.offsetParent;_20++}
_16+=(_17?this.ns.Element.getOffsetLeft(_15):this.ns.Element.getOffsetTop(_15));if(_13==_14){_16-=(_17?this.ns.Element.getOffsetLeft(_12):this.ns.Element.getOffsetTop(_12))}
if(_8){var _26=_2[_9]=_2[_9]||{};_26[_3?_3.ID:this.$27r]=_16}
return _16}
,isc.A.getStyleEdges=function isc_c_Element_getStyleEdges(_1){if(isc.Browser.isSafari&&!isc.Element.$x1){isc.Browser.isStrict=isc.Element.$x2();isc.Element.$x1=true}
if(_1==null)return null;var _2;if(this.$nk[_1]!==_2)return this.$nk[_1];var _3=(isc.Browser.isMoz&&isc.Browser.geckoVersion<20040616),_4;if(_3){_4=this.getStyleDeclaration(_1)}else{var _5=isc.Browser.isIE?this.$x3:this.$x4;_4=this.$x5(_1,_5)}
this.$nk[_1]=_4;return _4}
,isc.A.$x2=function isc_c_Element__testForSafariStrictMode(){if(document.compatMode!=null){return document.compatMode=="CSS1Compat"}
var _1="<TABLE cellspacing=0 cellpadding=2 border=0><tr><td height=30>x</td></tr></TABLE>"
var _2=isc.Element.createAbsoluteElement(_1);var _3=_2.offsetHeight>30;isc.Element.clear(_2);return _3}
,isc.A.$x5=function isc_c_Element__deriveStyleProperties(_1,_2){var _3=(isc.Browser.isIE||isc.Browser.isOpera||isc.Browser.isSafari||(isc.Browser.isMoz&&isc.Browser.geckoVersion>=20080205));if(!this.$x6){this.createAbsoluteElement("<TABLE CELLPADDING=81 STYLE='position:absolute;left:0px;top:-2000px;'><TR><TD "+(isc.Browser.isIE8Strict?" ID=isc_cellStyleTester STYLE='border:0px;margin:0px'><DIV ID=isc_cellInnerStyleTester>"+isc.Canvas.blankImgHTML(30,30)+"</DIV></TD>":" ID=isc_cellStyleTester>&nbsp;</TD>"+"<TD ID=isc_cellNoStyleTester>&nbsp;</TD></TR></TABLE>"));this.$x6=isc.Element.get("isc_cellStyleTester");if(isc.Browser.isIE8Strict){this.$62a=isc.Element.get("isc_cellInnerStyleTester")}
this.$x7="81px";if(isc.Browser.isSafari||isc.Browser.isChrome){var _4=isc.Element.get("isc_cellNoStyleTester");var _5=["paddingLeft"];var _6=this.getComputedStyle(_4,_5).paddingLeft;if(_6!=this.$x7){this.logDebug("Browser natively misreporting cell-padding (81px reported as:"+_6+"). This behavior is known to occur when the view is "+"zoomed in certain browsers but is worked around by SmartClient and "+"should have no visible effect on the application.","sizing");this.$x7=_6}}
this.$x8="-16384px";if(_3){this.createAbsoluteElement("<DIV ID=isc_styleTester STYLE='position:absolute;left:0px;top:-2000px;'>&nbsp;</DIV>");this.$x9=isc.Element.get("isc_styleTester");this.$ya=["marginLeft","marginTop","marginRight","marginBottom"];if(isc.Browser.isIE8Strict){this.$ya.addList(["borderLeftWidth","borderTopWidth","borderRightWidth","borderBottomWidth"])}}}
this.$x6.className=_1;var _7=this.getComputedStyle(this.$x6,_2);var _8=this.$x7;if(_7.paddingLeft==_8)_7.paddingLeft=null;if(_7.paddingTop==_8)_7.paddingTop=null;if(_7.paddingRight==_8)_7.paddingRight=null;if(_7.paddingBottom==_8)_7.paddingBottom=null;if(isc.Browser.isIE8Strict){var _9=this.$62a,_10=_9.offsetLeft,_11=_9.offsetTop;if(_10==81)_7.paddingLeft=null;if(_11==81)_7.paddingTop=null;if(this.$x6.offsetWidth-_10-30==81){_7.paddingRight=null}
if(this.$x6.offsetHeight-_11-30==81){_7.paddingBottom=null}}
if(isc.Browser.isSafari){if(isc.Browser.safariVersion<419.3){_8=isc.Canvas.AUTO;if(_7.paddingLeft==_8)_7.paddingLeft=null;if(_7.paddingTop==_8)_7.paddingTop=null;if(_7.paddingRight==_8)_7.paddingRight=null;if(_7.paddingBottom==_8)_7.paddingBottom=null}
_8=this.$x8;if(_7.marginTop==_8)_7.marginTop=null;if(_7.marginBottom==_8)_7.marginBottom=null}
if(_3){this.$x9.className=_1;var _12=this.getComputedStyle(this.$x9,this.$ya);_7.marginLeft=_12.marginLeft;_7.marginRight=_12.marginRight;_7.marginTop=_12.marginTop;_7.marginBottom=_12.marginBottom;if(isc.Browser.isIE8Strict){_7.borderLeftWidth=_12.borderLeftWidth;_7.borderRightWidth=_12.borderRightWidth;_7.borderTopWidth=_12.borderTopWidth;_7.borderBottomWidth=_12.borderBottomWidth}}
return _7}
,isc.A.getComputedStyle=function isc_c_Element_getComputedStyle(_1,_2){var _3,_4,_5;if(isc.isA.String(_1)){_3=isc.Element.get(_1)}else{_3=_1}
if(_3==null||!isc.isAn.Object(_3)){this.logWarn("getComputedStyle: Unable to get to DOM element specified by '"+_1+"'."+this.getStackTrace());return null}
if(this.$84e){_4=_3.currentStyle;if(_2==null)_2=this.$yb;var _6=isc.applyMask(_4,_2);return _6}
if(_2==null){_2=this.$yc}else if(isc.isAn.Array(_2)){var _7={},_8=this.$yc;for(var i=0;i<_2.length;i++){_7[_2[i]]=_8[_2[i]]}
_2=_7}
var _10=isc.Browser.isSafari&&isc.Browser.safariVersion<312,_11;if(_10){_4=_3.style;_11=this.getStyleDeclaration(_3.className)}else{_4=document.defaultView.getComputedStyle(_3,null)}
_5={};for(var _12 in _2){_5[_12]=_4.getPropertyValue(_2[_12]);if(_10&&_5[_12]==null&&_11!=null&&_11[_12]!=null&&!isc.isAn.emptyString(_11[_12]))
{_5[_12]=_11[_12]}}
return _5}
,isc.A.getComputedStyleAttribute=function isc_c_Element_getComputedStyleAttribute(_1,_2){if(_1==null||_2==null)return null;if(this.$84e){if(_1.currentStyle==null)return null;if(isc.Browser.isOpera&&this.$419[_2]!=null&&_1.currentStyle[this.$419[_2]]==this.$27r)return 0;return _1.currentStyle[_2]}
if(isc.Browser.isSafari){var _3=null;if(_1.style)_3=_1.style[_2];if((_3==null||isc.isAn.emptyString(_3))&&_1.className)
{var _4=isc.Element.getStyleEdges(_1.className);if(_4)_3=_4[_2]}
if(isc.isAn.emptyString(_3))return null;return _3}
var _5=this.$yc;var _6=this.$yd=this.$yd||document.defaultView;var _7=(_5[_2]||_2),_8=_6.getComputedStyle(_1,null);return _8.getPropertyValue(_7)}
,isc.A.getStyleDeclaration=function isc_c_Element_getStyleDeclaration(_1,_2){if(!_1)return null;if(!isc.allowDuplicateStyles)_2=false;if(isc.Browser.isSafari&&isc.Browser.safariVersion>=312){_1=_1.toLowerCase()}
var _3="."+_1,_4=", ";var _5,_6=_2?[]:null;for(var i=document.styleSheets.length-1;i>=0;i--){var _8=this.$ye(document.styleSheets[i]);if(_8==null)continue;for(var j=_8.length-1;j>=0;j--){var _10=_8[j].selectorText;if(_10==null)continue;if(isc.Browser.isSafari&&isc.Browser.safariVersion>=312){_10=_10.toLowerCase()}
if(isc.Browser.isMoz||isc.Browser.isIE9){var _11=_10.split(_4);for(var k=0;k<_11.length;k++){if(_11[k]==_3){_5=_8[j].style;if(_5!=null){if(_2)_6[_6.length]=_5;else return _5}}}}else{if(_10==_3){_5=_8[j].style;if(_5!=null){if(_2)_6[_6.length]=_5;else return _5}}}}}
if(_2&&_6.length>0)return _6;return null}
,isc.A.$ye=function isc_c_Element__getCSSRules(_1){if(!this.$yf){var _2="try{return $yg.rules||$yg.cssRules}"+"catch(e){isc.Page.$yh = true}";this.$yf=new Function("$yg",_2)}
return this.$yf(_1)}
,isc.A.getStyleText=function isc_c_Element_getStyleText(_1,_2){if(!isc.allowDuplicateStyles)_2=false;var _3=this.$nl,_4=_3[_1];if(_4!=null)return _4;var _5=this.getStyleDeclaration(_1,_2);if(_5==null){if(!isc.Browser.isSafari||isc.Page.isLoaded())
this.$nl[_1]=isc.emptyString;return isc.emptyString}
if(_2){for(var i=_5.length-1;i>-1;i--){var _7=_5[i];var _8=_7.cssText;if(_8==null)continue;if(!isc.endsWith(_8,this.$39))_8+=this.$39;if(_4==null)_4=_8;else _4+=_8}
if(_4==null)_4=isc.$ad}else{_4=(_5.cssText||isc.$ad)}
if(!isc.endsWith(_4,isc.semi))_4+=isc.semi;return(_3[_1]=_4)}
,isc.A.$50f=function isc_c_Element__clearCSSCaches(){isc.Element.$nk={};isc.Element.$nl={};isc.Element.$yj=isc.Element.$yk=isc.Element.$yl=null}
,isc.A.$th=function isc_c_Element__getTopMargin(_1){return this.$sd(_1).top}
,isc.A.$ti=function isc_c_Element__getBottomMargin(_1){return this.$sd(_1).bottom}
,isc.A.$tf=function isc_c_Element__getLeftMargin(_1){return this.$sd(_1).left}
,isc.A.$tg=function isc_c_Element__getRightMargin(_1){return this.$sd(_1).right}
,isc.A.$sd=function isc_c_Element__calculateMargins(_1){if(this.$yk==null)this.$yk={};else if(this.$yk[_1]!=null){return this.$yk[_1]}
var _2={top:0,bottom:0,left:0,right:0},_3=isc.Element.getStyleEdges(_1);if(_3==null)return _2;var _4=_3.marginTop,_5=_3.marginBottom,_6=_3.marginLeft,_7=_3.marginRight,_8=isc.px;if(isc.isA.String(_4)&&isc.endsWith(_4,_8))
_2.top=parseInt(_4);if(isc.isA.String(_5)&&isc.endsWith(_5,_8))
_2.bottom=parseInt(_5);if(isc.isA.String(_6)&&isc.endsWith(_6,_8))
_2.left=parseInt(_6);if(isc.isA.String(_7)&&isc.endsWith(_7,_8))
_2.right=parseInt(_7);this.$yk[_1]=_2;return _2}
,isc.A.$tn=function isc_c_Element__getTopBorderSize(_1){return this.$tj(_1).top}
,isc.A.$to=function isc_c_Element__getBottomBorderSize(_1){return this.$tj(_1).bottom}
,isc.A.$tl=function isc_c_Element__getLeftBorderSize(_1){return this.$tj(_1).left}
,isc.A.$tm=function isc_c_Element__getRightBorderSize(_1){return this.$tj(_1).right}
,isc.A.$tj=function isc_c_Element__calculateBorderSize(_1){if(this.$yj==null)this.$yj={};else if(this.$yj[_1]!=null){return this.$yj[_1]}
var _2={top:0,bottom:0,left:0,right:0},_3=isc.Element.getStyleEdges(_1);if(_3==null)return _2;var _4=_3.borderTopWidth,_5=_3.borderBottomWidth,_6=_3.borderLeftWidth,_7=_3.borderRightWidth,_8=isc.px;if(isc.isA.String(_4)&&isc.endsWith(_4,_8))
_2.top=parseInt(_4);if(isc.isA.String(_5)&&isc.endsWith(_5,_8))
_2.bottom=parseInt(_5);if(isc.isA.String(_6)&&isc.endsWith(_6,_8))
_2.left=parseInt(_6);if(isc.isA.String(_7)&&isc.endsWith(_7,_8))
_2.right=parseInt(_7);this.$yj[_1]=_2;return _2}
,isc.A.$ym=function isc_c_Element__getVBorderSize(_1){return this.$tn(_1)+this.$to(_1)}
,isc.A.$yn=function isc_c_Element__getHBorderSize(_1){return this.$tl(_1)+this.$tm(_1)}
,isc.A.$tt=function isc_c_Element__getTopPadding(_1,_2){var _3=this.$tq(_1);if(_2&&_3.nullTop)return null;return _3.top}
,isc.A.$tu=function isc_c_Element__getBottomPadding(_1,_2){var _3=this.$tq(_1);if(_2&&_3.nullBottom)return null;return _3.bottom}
,isc.A.$tr=function isc_c_Element__getLeftPadding(_1,_2){var _3=this.$tq(_1);if(_2&&_3.nullLeft)return null;return _3.left}
,isc.A.$ts=function isc_c_Element__getRightPadding(_1,_2){var _3=this.$tq(_1);if(_2&&_3.nullRight)return null;return _3.right}
,isc.A.$tq=function isc_c_Element__calculatePadding(_1){if(this.$yl==null)this.$yl={};else if(this.$yl[_1]!=null){return this.$yl[_1]}
var _2={top:0,bottom:0,left:0,right:0},_3=isc.Element.getStyleEdges(_1);if(_3==null){_2.nullLeft=true;_2.nullRight=true;_2.nullTop=true;_2.nullBottom=true;return _2}
var _4=_3.paddingTop,_5=_3.paddingBottom,_6=_3.paddingLeft,_7=_3.paddingRight,_8=isc.px;_2.nullTop=(_4==null||_4==isc.emptyString);_2.nullBottom=(_5==null||_5==isc.emptyString)
_2.nullLeft=(_6==null||_6==isc.emptyString);_2.nullRight=(_7==null||_7==isc.emptyString);if(isc.isA.String(_4)&&isc.endsWith(_4,_8))
_2.top=parseInt(_4);if(isc.isA.String(_5)&&isc.endsWith(_5,_8))
_2.bottom=parseInt(_5);if(isc.isA.String(_6)&&isc.endsWith(_6,_8))
_2.left=parseInt(_6);if(isc.isA.String(_7)&&isc.endsWith(_7,_8))
_2.right=parseInt(_7);this.$yl[_1]=_2;return _2}
,isc.A.$s1=function isc_c_Element__getVPadding(_1){return this.$tt(_1)+this.$tu(_1)}
,isc.A.$sw=function isc_c_Element__getHPadding(_1){return this.$tr(_1)+this.$ts(_1)}
,isc.A.$yo=function isc_c_Element__getVBorderPad(_1){return this.$ym(_1)+this.$s1(_1)}
,isc.A.$yp=function isc_c_Element__getHBorderPad(_1){return this.$yn(_1)+this.$sw(_1)}
,isc.A.getNativeScrollbarSize=function isc_c_Element_getNativeScrollbarSize(){if(isc.Element.$yi==null){if(isc.Browser.isMobileWebkit){return(isc.Element.$yi=16)}
var _1="<div id=isc_ScrollbarTest "+"style='position:absolute;top:-100px;border:0px;padding:0px;margin:0px;height:100px;width:100px;overflow:scroll;'>"+isc.nbsp+"</div>";this.createAbsoluteElement(_1);var _2=this.get('isc_ScrollbarTest');isc.Element.$yi=parseInt(_2.style.height)-_2.clientHeight;this.clear(_2)}
return isc.Element.$yi}
,isc.A.getRotationCSS=function isc_c_Element_getRotationCSS(_1,_2){var _3=this.vendorCSSPrefix;var _4=_3+"transform: rotate("+_1+"deg);";if(_2!=null){_4+=(_3+"transform-origin: "+_2+";")}
return _4}
);isc.B._maxIndex=isc.C+69;isc.Element.$50g=function(){var _1=this.$x4={borderLeftWidth:"border-left-width",borderRightWidth:"border-right-width",borderTopWidth:"border-top-width",borderBottomWidth:"border-bottom-width",marginLeft:"margin-left",marginRight:"margin-right",marginTop:"margin-top",marginBottom:"margin-bottom",paddingLeft:"padding-left",paddingRight:"padding-right",paddingTop:"padding-top",paddingBottom:"padding-bottom"}
var _2=this.$yc=isc.addProperties({position:"position",overflow:"overflow",top:"top",left:"left",width:"width",height:"height",display:"display"},_1);if(isc.Browser.isIE||isc.Browser.isOpera){this.$yb=isc.getKeys(_2);this.$x3=isc.getKeys(_1)}}
isc.Element.$50g();isc.ClassFactory.defineClass("Canvas");isc.isA.Canvas=function(_1){return(_1!=null&&_1._isA_Canvas)}
isc.A=isc.Canvas;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A._isA_Canvas=true;isc.A.AUTO="auto";isc.A.ANYTHING="**anything**";isc.A.ABSOLUTE="absolute";isc.A.RELATIVE="relative";isc.A.INHERIT="inherit";isc.A.VISIBLE="visible";isc.A.HIDDEN="hidden";isc.A.COMPLETE="complete";isc.A.DRAWN="complete";isc.A.DRAWING_HANDLE="drawingHandle";isc.A.HANDLE_DRAWN="handleDrawn";isc.A.UNDRAWN="undrawn";isc.A.SCROLL="scroll";isc.A.CLIP_H="clip-h";isc.A.CLIP_V="clip-v";isc.A.IGNORE="ignore";isc.A.NATIVE="native";isc.A.CLIP="clip";isc.A.NESTED_DIV="nestedDiv";isc.A.CENTER="center";isc.A.LEFT="left";isc.A.RIGHT="right";isc.A.TOP="top";isc.A.BOTTOM="bottom";isc.A.UP="up";isc.A.DOWN="down";isc.A.BOTH="both";isc.A.NONE="none";isc.A.VERTICAL="vertical";isc.A.HORIZONTAL="horizontal";isc.A.MARKED="marked";isc.A.MIDDLE="middle";isc.A.ALL="all";isc.A.DEFAULT="default";isc.A.ARROW="default";isc.A.WAIT="wait";isc.A.HAND=(isc.Browser.isMoz||(isc.Browser.isSafari&&isc.Browser.isStrict)?"pointer":"hand");isc.A.MOVE="move";isc.A.HELP="help";isc.A.TEXT="text";isc.A.CROSSHAIR="crosshair";isc.A.NOT_ALLOWED="not-allowed";isc.A.COL_RESIZE=(isc.Browser.isIE&&isc.Browser.version>=6?"col-resize":"e-resize");isc.A.ROW_RESIZE=(isc.Browser.isIE&&isc.Browser.version>=6?"row-resize":"n-resize");isc.A.TILE="tile";isc.A.STRETCH="stretch";isc.A.NORMAL="normal";isc.A.REPEAT="repeat";isc.A.NO_REPEAT="no-repeat";isc.A.REPEAT_X="repeat-x";isc.A.REPEAT_Y="repeat-y";isc.A.LTR="ltr";isc.A.RTL="rtl";isc.A.BEFORE="before";isc.A.AFTER="after";isc.A.NEAREST="nearest";isc.A.$ng=200000;isc.A.$nh=199950;isc.A.$ni=800000;isc.A.TAB_INDEX_GAP=50;isc.A.TAB_INDEX_FLOOR=1000;isc.A.TAB_INDEX_CEILING=32766;isc.A.$nj=[];isc.A.textStyleAttributes=["fontFamily","fontSize","color","backgroundColor","fontWeight","fontStyle","textDecoration","textAlign"];isc.A.$816=[];isc.A.allowExternalFilters=true;isc.A.$nm=[];isc.A._redrawQueueDelay=(0);isc.A.$nn=200;isc.A._canvasList=[];isc.A._iscInternalCount=0;isc.A._stats={redraws:0,clears:0,destroys:0,draws:0};isc.A.$no={};isc.A.$np={};isc.A.$nq=[];isc.A.useMozBackMasks=false;isc.A.useNativeWheelDelta=true;isc.A.scrollWheelDelta=50;isc.A.loadingImageSrc="[SKINIMG]loadingSmall.gif";isc.A.loadingImageSize=16;isc.B.push(isc.A.$814=function isc_c_Canvas__setDoublingStrings(){this.$42a=isc.Browser.isIE&&(!this.neverUseFilters||this.allowExternalFilters)?"margin:0px;border:0px;padding:0px;background-image:none;background-color:transparent;filter:none;":"margin:0px;border:0px;padding:0px;background-image:none;background-color:transparent;";isc.Canvas.addProperties({$4o:"' style='"+isc.Canvas.$42a});for(var i=0;i<this.$816.length;i++){var _2=this.$816[i];if(_2.target==null||_2.target.destroyed)continue;_2.target[_2.methodName](this.$42a)}}
,isc.A.setNeverUseFilters=function isc_c_Canvas_setNeverUseFilters(_1){this.neverUseFilters=_1;this.$814()}
,isc.A.setAllowExternalFilters=function isc_c_Canvas_setAllowExternalFilters(_1){this.allowExternalFilters=_1;this.$814()}
);isc.B._maxIndex=isc.C+3;isc.Canvas.$814();isc.A=isc.Canvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A._isA_Canvas=true;isc.A.autoDraw=true;isc.A.allowContentAndChildren=true;isc.A.htmlPosition="afterBegin";isc.A.position=null;isc.A.left=0;isc.A.top=0;isc.A.defaultWidth=100;isc.A.defaultHeight=100;isc.A.minWidth=10;isc.A.maxWidth=10000;isc.A.minHeight=10;isc.A.maxHeight=10000;isc.A.zIndex=isc.Canvas.AUTO;isc.A.autoShowParent=false;isc.A.visibility=isc.Canvas.INHERIT;isc.A.styleName="normal";isc.A.contents=isc.nbsp;isc.A.backgroundRepeat=isc.Canvas.REPEAT;isc.A.mozOutlineOffset="-1px";isc.A.appImgDir="";isc.A.skinImgDir="images/";isc.A.cursor=isc.Canvas.DEFAULT;isc.A.disabledCursor=isc.Canvas.DEFAULT;isc.A.noDropCursor=isc.Canvas.NOT_ALLOWED;isc.A.$65q=(isc.Browser.isMoz&&isc.Browser.geckoVersion<20081201);isc.A.overflow=isc.Canvas.VISIBLE;isc.A.alwaysShowVScrollbar=false;isc.A.showCustomScrollbars=!((isc.Browser.isOpera||isc.Browser.isIE&&isc.Browser.version>4)||(isc.Browser.isUnix&&isc.Browser.isMoz&&isc.Browser.geckoVersion>=20020826&&isc.Browser.geckoVersion<=20031007));isc.A.scrollbarSize=16;isc.A.scrollbarConstructor="Scrollbar";isc.A.scrollLeft=0;isc.A.scrollTop=0;isc.A.scrollDelta=20;isc.A.$nr="unset";isc.A.enabled="unset";isc.A.redrawOnDisable=false;isc.A.$jp=true;isc.A.$jo=true;isc.A.$ns=true;isc.A.$nt=true;isc.A.$jq=true;isc.A._redrawWithParent=true;isc.A.showFocusOutline=true;isc.A._useNativeTabIndex=(isc.Browser.isIE&&isc.Browser.version>=5)||isc.Browser.isSafari||(isc.Browser.isMoz&&isc.Browser.geckoVersion>=20051111);isc.A._useFocusProxy=(isc.Browser.isMoz&&isc.Browser.geckoVersion<20051111)||isc.Browser.isOpera;isc.A.contextMenuProperties={autoDraw:false,width:200,showIcons:true};isc.A.menuConstructor="Menu";isc.A.clippedCorners=["TL","TR","BL","BR"];isc.A.cornerClipColor="FFFFFF";isc.A.cornerClipImage="[SKIN]corner.gif";isc.A.cornerClipSize=10;isc.A.$nv={_generated:true,overflow:"hidden",$jp:false,$jo:false,autoDraw:false,skinImgDir:"images/corners/",draw:function(){this.Super("draw",arguments)}};isc.A.dragOutlineStyle="dragOutline";isc.A.dragStartDistance=5;isc.A.canDragScroll=true;isc.A.dragScrollDelay=100;isc.A.dragScrollThreshold="10%";isc.A.minDragScrollIncrement=1;isc.A.maxDragScrollIncrement="5%";isc.A.dragIntersectStyle=isc.EventHandler.INTERSECT_WITH_MOUSE;isc.A.dragRepositionCursor=isc.Canvas.MOVE;isc.A.dragScrollType="any";isc.A.hoverDelay=300;isc.A.showHover=true;isc.A.edgeMarginSize=5;isc.A.edgeCursorMap={"T":"n-resize","L":"w-resize","B":"s-resize","R":"e-resize","TL":"nw-resize","TR":"ne-resize","BL":"sw-resize","BR":"se-resize"};isc.A.dragAppearance=isc.EventHandler.OUTLINE;isc.A.dropTypes=isc.Canvas.ANYTHING;isc.A.mouseStillDownInitialDelay=400;isc.A.mouseStillDownDelay=100;isc.A.doubleClickDelay=250;isc.A.refreshVariable="refresh";isc.A.$ks=(isc.Browser.isMoz&&(!isc.Browser.isUnix||isc.Browser.geckoVersion>20031007));isc.A.useClipDiv=(isc.Browser.isMoz||isc.Browser.isSafari||isc.Browser.isOpera);isc.A.manageChildOverflow=true;isc.A.$nw={};isc.A.percentBox="visible";isc.A.$520="viewport";isc.A.snapHGap=20;isc.A.snapVGap=20;isc.A.snapHDirection=isc.Canvas.AFTER;isc.A.snapVDirection=isc.Canvas.AFTER;isc.A.snapAxis=isc.Canvas.BOTH;isc.A.snapOnDrop=true;isc.B.push(isc.A.getDragAppearance=function isc_Canvas_getDragAppearance(_1){if(_1==isc.EH.DRAG_RESIZE&&this.dragResizeAppearance!=null)
return this.dragResizeAppearance;if(_1==isc.EH.DRAG_REPOSITION&&this.dragRepositionAppearance!=null)
return this.dragRepositionAppearance;return this.dragAppearance}
);isc.B._maxIndex=isc.C+1;isc.A=isc.Canvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$nx="resize";isc.A.$ny="draw";isc.A.$nz="hidden";isc.A.$n0="redraw";isc.A.$n1="undefined";isc.A.$n2="draws";isc.A.$n3="drawing";isc.A.$n4="redraws";isc.A.$n5="autoDraw";isc.A.$n6="beforeBegin";isc.A.$n7="afterBegin";isc.A.$n8="beforeEnd";isc.A.$n9="afterEnd";isc.A.$oa=">";isc.A.$ob="'";isc.A.$77b='"';isc.A.$oc="initWidget";isc.A.$55w="html";isc.A.$54t="&nbsp;";isc.A.$od="initial draw";isc.A.$oe="parentDrawn";isc.A.notifyAncestorsOnReflow=false;isc.A.$of="eventProxy";isc.A.reuseDOMIDs=false;isc.A.$og="canvas";isc.A.$oh="canvas_clipDiv";isc.A.clipHandleIsFocusHandle=true;isc.A.$oi="MARGIN-LEFT:";isc.A.$oj="MARGIN-RIGHT:";isc.A.$ok="MARGIN-TOP:";isc.A.$ol="MARGIN-BOTTOM:";isc.A.$om="MARGIN:";isc.A.$on="</div>";isc.A.$oo="</div></div>";isc.A.$op=[];isc.A.isBorderBox=(isc.Browser.isMoz||isc.Browser.isBorderBox);isc.A.$oq="isc.EH.focusInCanvas(";isc.A.$or="if(event.target!=this)return;isc.EH.focusInCanvas(";isc.A.$os="if(window.isc)isc.EH.blurFocusCanvas(";isc.A.$ot=",true);";isc.A.$ou="autoChild:";isc.A.$90j="spacer:";isc.A.$ov=["<DIV ID='",null,"'style='position:absolute;width:1px;height:1px;overflow:hidden;left:",null,"px;top:",null,"px;'>&nbsp;</DIV>"];isc.A.$ow="scrollSizeDiv";isc.A.$ox="enforceScrollSize";isc.A.$oy="-1px";isc.A.useClientRectAPI=(isc.Browser.isMoz&&isc.Browser.geckoVersion>20071109);isc.A.useBoxObjectAPI=false;isc.A.useBoxObjectAPISelectively=true;isc.A.$65h="0px";isc.A.$oz="left";isc.A.$o0="top";isc.A.$o1="right";isc.A.$o2="bottom";isc.A.$o3="center";isc.A.$o4="childMoved";isc.A.$o5="height";isc.A.$o6="width";isc.A.$oz="left";isc.A.$o0="top";isc.A.$o7="$o8";isc.A.$o9="%";isc.A.$pa="*";isc.A.$pb={height:"_percent_height",width:"_percent_width",left:"_percent_left",top:"_percent_top"};isc.A.$90f={height:"minHeight",width:"minWidth"};isc.A.$90u={height:"maxHeight",width:"maxWidth"};isc.A.$pc="resized";isc.A.$pd="childResized";isc.A.momentumScrolling=true;isc.A.momentumScrollTime=1500;isc.A.momentumScrollAcceleration="smoothStart";isc.A.hoopSelectorDefaults={_constructor:"Canvas",keepInParentRect:true,redrawOnResize:false,overflow:"hidden",border:"1px solid blue",opacity:10,backgroundColor:"blue"};isc.A.hoopSelectAxis="both";isc.A.shouldSetNoDropTracker=isc.Browser.isOpera;isc.A.noDropTracker="[SKIN]/shared/no_drop.png";isc.A.adjustOverflowWhileDirty=true;isc.A.$pe={hidden:true,visible:true,scroll:true,auto:true,"clip-v":true,"clip-h":true,ignore:true};isc.A.$pf="sizing";isc.A.$pg="overflow";isc.A.cancelNativeScrollOnKeyDown=isc.Browser.isSafari;isc.A.$90e={Page_Up:true,Page_Down:true,Arrow_Up:true,Arrow_Down:true,Arrow_Left:true,Arrow_Right:true,Home:true,End:true};isc.A.$ph="px";isc.A.$27r="none";isc.A.$411="relative";isc.A.$pi="disabled";isc.A.$27r="none";isc.A.$pj="styleName";isc.A.$pk="eventpart";isc.A.$f8="count";isc.A.$pl=["edgeImage","edgeColor","customEdges","shownEdges","edgeSize","edgeTop","edgeBottom","edgeLeft","edgeRight","edgeOffset","edgeOffsetTop","edgeOffsetBottom","edgeOffsetLeft","edgeOffsetRight","canDragResize","canDragReposition"];isc.A.shadowDepth=4;isc.A.dragResizeFromShadow=true;isc.A.$pm="shadow";isc.A.isGroup=false;isc.A.groupBorderCSS="2px solid black";isc.A.groupLabelPadding=10;isc.A.showGroupLabel=true;isc.A.groupLabelStyleName="groupLabel";isc.A.groupLabelDefaults={_constructor:"Label",overflow:"visible",height:1,width:1,wrap:false,vAlign:"center",align:"center"};isc.B.push(isc.A.init=function isc_Canvas_init(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13){if(isc.$cv)arguments.$cw=this;if(!isc.Canvas.$80c){if(this.getDocumentBody(true)==null){isc.logWarn("Canvas created in a page outside the BODY tag. This is not supported. "+"Isomorphic Software requires the tag to be present and all widgets be created "+"and drawn inside it. Canvas details follow:\n"+isc.Log.echo(this))}
isc.Canvas.$80c=true}
this.ns.ClassFactory.addGlobalID(this);this._canvasList(true);if(this.position==null){this.position=this.htmlElement!=null?isc.Canvas.RELATIVE:isc.Canvas.ABSOLUTE}
if(this.className!=null&&this.logIsInfoEnabled(this.$pj)){this.logInfo("'className' property specified. This property has been deprecated in "+"favor of 'styleName' as of SmartClient 5.5.",this.$pj)}
if(this.styleName!=null){if(this.className!=null){var _14=this.getPrototype(),_15=(this.styleName!=_14.styleName),_16=(this.className!=_14.className);if(_15)this.className=this.styleName;else if(_16)this.styleName=this.className;else this.styleName=this.className}else{this.className=this.styleName}}else if(this.className!=null){this.styleName=this.className}
if(this.size!=null)this.height=this.width=this.size;this.$pn=this.width;this.$po=this.height;if(this.width==null)this.width=this.defaultWidth;if(this.height==null)this.height=this.defaultHeight;this.$o8=this.height;if(isc.isA.String(this.margin)){var _17=parseInt(this.margin);if(isc.isA.Number(_17))this.margin=_17;else{this.logWarn("Invalid setting for this.margin:"+this.margin+". This should be a numeric value - ignoring");this.margin=null}}
if(isc.isA.String(this.padding)){var _18=parseInt(this.padding);if(isc.isA.Number(_18))this.padding=_18;else{this.logWarn("Invalid setting for this.padding:"+this.padding+". This should be set to a numeric value - ignoring");this.padding=null}}
if(this.border!=null&&!isc.isA.String(this.border)){this.border=this.$63e(this.border)}
if(this.percentSource)this.setPercentSource(this.percentSource,true);this.$pp=true;this.resizeTo(this.width,this.$o8);this.moveTo(this.left,this.top);this.$pp=null;if(this.children&&!isc.isAn.Array(this.children))this.children=[this.children];if(this.peers&&!isc.isAn.Array(this.peers))this.peers=[this.peers];if(this.enabled!=this.$nr){this.logWarn("Widget initialized with explicitly specified 'enabled' property. "+"This property has been deprecated - use 'disabled' instead.");this.disabled=!this.enabled}
if(this.redrawOnEnable!=null){this.logWarn("Widget initialized with deprecated 'redrawOnEnable' - use 'redrawOnDisable' instead.");this.redrawOnDisable=this.redrawOnEnable}
this.initWidget(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13);this.$pq();if(this.showShadow)this.$pr();if(this.clipCorners)this.$ps();if(this.useBackMask&&((isc.Browser.isIE&&isc.Browser.minorVersion>=5.5)||(isc.Canvas.useMozBackMasks&&isc.Browser.isMoz))){this.makeBackMask()}
if(this.isGroup){delete this.isGroup;this.setIsGroup(true)}
if(this.children)this.children.setProperty(this.$n5,false);if(this.peers)this.peers.setProperty(this.$n5,false);if(this.observes){var _19,_20,_21=this.observes,_22=_21.length;for(var i=0;i<_22;i++){var _19=_21[i];if(!_19)continue;if(isc.isA.String(_19.source))_20=this.getGlobalReference(_19.source);else _20=_19.source;if(_20){this.observe(_20,_19.message,_19.action)}}}
this.$pt();if(this.autoChildren)this.addAutoChildren(this.autoChildren);if(this.addOns)this.addAutoChildren(this.addOns);if(this._adjacentHandle&&!this.drawContext){this.drawContext={element:this._adjacentHandle}}
if(this.htmlElement){var _24=this.htmlElement;delete this.htmlElement;this.setHtmlElement(_24)}
if(this.eventProxy!=null){if(!isc.isA.Canvas(this.eventProxy)){this.logWarn("Canvas ID:'"+this.getID()+"' initialized with bad eventProxy. "+"This property should be set to another Canvas instance. Clearing this property.")
delete this.eventProxy}else{if(this.eventProxy.$kt==null)this.eventProxy.$kt=[];this.eventProxy.$kt.add(this)}}
var _25=this.parentElement;if(_25){this.parentElement=null;if(isc.isA.String(_25))_25=window[_25];_25.addChild(this)}
if(this.autoFetchAsFilter!=null){var _26=this.autoFetchAsFilter?"substring":"exact";this.logWarn("This component has autoFetchAsFilter explicitly specified as:"+this.autoFetchAsFilter+". This attribute is deprecated in favor of "+"this.autoFetchTextMatchStyle. Defaulting autoFetchTextMatchStyle to \""+_26+"\" based on this setting.");this.autoFetchTextMatchStyle=_26}
this.initializeValuesManager();if(this.showPanelHeader==true){if(this.setupPanelHeader)this.setupPanelHeader();if(this.refreshPanelControls)this.refreshPanelControls()}
if(this.autoDraw&&!this.parentElement&&!isc.noAutoDraw){if(isc.Browser.isSafari&&!isc.Browser.isChrome&&isc.deferAutoDraw&&!isc.Page.isLoaded()&&this.position!="relative")
{isc.Page.setEvent("load","if(window."+this.getID()+")"+this.getID()+".$77f()")}else{this.draw()}}}
,isc.A.$77f=function isc_Canvas__deferredAutoDraw(){if(this.destroyed||this.isDrawn())return;this.draw()}
,isc.A.initWidget=function isc_Canvas_initWidget(){}
,isc.A.setID=function isc_Canvas_setID(_1){var _2=this.pointersToThis=this.pointersToThis||[];_2.add({object:window,property:this.ID});this.ID=_1;window[this.ID]=this;this.clear();this.draw()}
,isc.A.clearIDs=function isc_Canvas_clearIDs(){this.clear();window[this.ID]=null;if(this.children){for(var i=0;i<this.children.length;i++){this.children[i].clearIDs()}}
if(this.peers){for(var i=0;i<this.peers.length;i++){this.peers[i].clearIDs()}}}
,isc.A.getDrawnState=function isc_Canvas_getDrawnState(){if(this.$if==true)return isc.Canvas.COMPLETE;if(this.$pu==true)return isc.Canvas.HANDLE_DRAWN;if(this.$pv==true)return isc.Canvas.DRAWING_HANDLE;return isc.Canvas.UNDRAWN}
,isc.A.setDrawnState=function isc_Canvas_setDrawnState(_1){if(_1==isc.Canvas.COMPLETE)this.$if=true;else this.$if=false;if(_1==isc.Canvas.HANDLE_DRAWN)this.$pu=true;else this.$pu=false;if(_1==isc.Canvas.DRAWING_HANDLE)this.$pv=true;else this.$pv=false}
,isc.A.isDrawn=function isc_Canvas_isDrawn(){return!!this.$if}
,isc.A.handleDrawn=function isc_Canvas_handleDrawn(){return!!this.$pu}
,isc.A.getID=function isc_Canvas_getID(){if(this.ID==null)this.ns.ClassFactory.addGlobalID(this);return this.ID}
,isc.A.getAttribute=function isc_Canvas_getAttribute(_1){return this[_1]}
,isc.A.getInnerHTML=function isc_Canvas_getInnerHTML(){var _1;if(!this.containsIFrame())_1=this.getContents();else{var _2=this.getContentsURL();_2=isc.Page.getURL(_2);if(isc.rpc)_2=isc.rpc.addParamsToURL(_2,this.contentsURLParams);isc.EventHandler.registerMaskableItem(this,true);_1=this.getIFrameHTML(_2)}
return _1}
,isc.A.getIFrameHTML=function isc_Canvas_getIFrameHTML(_1){return"<iframe height='100%' width='100%' scrolling='"+(this.overflow==isc.Canvas.HIDDEN?"no'":"auto'")+(isc.Browser.isSafari?" id="+this.$qs("iframe"):"")+" frameborder='0'"+" src=\""+_1+"\"></iframe>"}
,isc.A.$pw=function isc_Canvas__sizeIFrame(){var _1=this.getDrawnState();if(_1!=isc.Canvas.COMPLETE&&_1!=isc.Canvas.HANDLE_DRAWN)return;var _2=this.getHandle(),_3=_2?_2.firstChild:null;if(_3==null)return;_3.style.height=(this.getInnerContentHeight()-2)+isc.px}
,isc.A.$px=function isc_Canvas__getInnerHTML(_1){if(isc.$cv)arguments.$cw=this;var _2=this.getInnerHTML(_1);if(this.$pz){var _3=this.$pz.join(isc.emptyString);_2=(_2==null||_2==isc.nbsp?_3:_2+_3)}
return _2}
,isc.A.readyToDraw=function isc_Canvas_readyToDraw(){var _1=this.getDrawnState();if(this.getDrawnState()!=isc.Canvas.UNDRAWN){var _1=this.getDrawnState();this.logWarn("draw() called on widget with current drawn state: "+_1+(_1==isc.Canvas.COMPLETE?", use redraw() instead.":", ignoring.")+this.getStackTrace(),"drawing");return false}
if(this.showIf!=null){this.convertToMethod("showIf");if(this.showIf(this)==false)return false}
if(this.getHeight()<=0||this.getWidth()<=0){if(this.$77g){this.$77h();return false}
this.logWarn("negative or zero area: height: "+this.getHeight()+", width: "+this.getWidth()+", refusing to draw"+this.getStackTrace(),"drawing");return false}
if(this.deferredDrawEvent!=null){this.logInfo("draw() called while object already pending a delayed draw - no action to take","drawing");return false}
if(this.parentElement!=null&&(!isc.isA.Canvas(this.parentElement)||this.parentElement.getDrawnState()==isc.Canvas.UNDRAWN))
{this.logWarn("Attempt to draw child of an undrawn parent - ignoring"+this.getStackTrace(),"drawing");return false}
if(isc.Browser.isSafari&&!isc.Page.isLoaded()){var _2=isc.Browser.safariVersion;if(parseInt(_2)<100){this.drawDeferred();return false}else{}}
return true}
,isc.A.$77h=function isc_Canvas__deferDrawForPageSize(){if(isc.Page.isLoaded())this.drawDeferred();else{isc.Page.setEvent("load",this.getID()+".$77i()")}}
,isc.A.$77i=function isc_Canvas__fireDeferredDrawForPageResize(){if(this.destroyed)return;if(isc.Page.getWidth()==0||isc.Page.getHeight()==0){this.delayCall("draw",null,100)}
else{this.draw()}}
,isc.A.$p2=function isc_Canvas__mustDocumentWrite(){return false}
,isc.A.$p4=function isc_Canvas__requestsDocumentWrite(){if(this.$p5)return true;var _1=this.parentElement;while(_1){if(_1.$p5)return true;_1=_1.parentElement}
if(this.children){for(var i=0;i<this.children.length;i++){if(this.children[i].$p2())return true}}
return false}
,isc.A.draw=function isc_Canvas_draw(_1){if(isc.$cv)arguments.$cw=this;if(!this.readyToDraw())return this;if(this.overflow==isc.Canvas.AUTO)this.getTabIndex();if(this.logIsInfoEnabled(this.$n2)){this.logInfo("draw(): drawing "+this.Class+(this.parentElement?" with parent: "+this.parentElement:"")+(!isc.Page.isLoaded()?" before page load":"")+(this.logIsDebugEnabled(this.$n2)?this.getStackTrace():""),this.$n2)}
this.$p6(this.$n2);var _2=this.doInitialFetch();if(this.peers!=null&&this.peers.getLength()>0){this.predrawPeers()}
var _3=(isc.Browser.isIE&&this.fixIEOpacity&&!this.masterElement),_4=isc.Element.cacheOffsetCoords;if(this.position==isc.Canvas.RELATIVE){this.cacheOffsetCoords=false;_4=false}
if(_3||_4){var _5=this.parentElement;while(_5){if(_3){if(_5.opacity!=null&&_5.opacity!=100){this.setOpacity(100,null,true);_3=false;if(!_4)break}}
if(_4){if(_5.position==isc.Canvas.RELATIVE){this.cacheOffsetCoords=false;_4=false;if(!_3)break}}
_5=_5.parentElement}}
if(_4)this.cacheOffsetCoords=true;if(this.htmlElement!=null&&this.matchElement){if(isc.isA.String(this.htmlElement))this.htmlElement=isc.Element.get(this.htmlElement);var _6=isc.Element.getNativeInnerWidth(this.htmlElement),_7=isc.Element.getNativeInnerHeight(this.htmlElement);this.setWidth(_6);this.setHeight(_7)}
var _8=this.parentElement;var _9=(!isc.Page.isLoaded()&&!this.drawContext&&(_8==null&&this.position==isc.Canvas.RELATIVE));_9=_9||this.$p2();var _10=this.separateContentInsertion;if(isc.Page.isLoaded()||!_9){this.$p8(!_10);if(_10)this.$p9();this.drawChildren();this.$qa()}else{var _5=this.parentElement;if((isc.Browser.isOpera||isc.Browser.isIE)&&this.getDocument().readyState=="complete")
{isc.Page.finishedLoading()}
this.$qb()}
if(_2)isc.RPCManager.sendQueue();if(this._useFocusProxy&&this.$kk())this.makeFocusProxy();if(this.accessKey!=null&&this.$qc()&&this.$kk()){this.$qd()}
if(this.$qe!=null)
this.enforceScrollSize(this.$qe[0],this.$qe[1]);if(this.$qf())isc.EH.$mz([this]);if(this.clipCorners)this.$qg();this.$806=this.isVisible();if(!_1&&this.$806)this.show();if(this.parentElement)this.parentElement.childDrawn(this);if(this.masterElement)this.masterElement.peerDrawn(this);if(this.parentElement==null&&isc.Page.isLoaded()&&!isc.Page.pollPageSize)
{if(this.getPageRight()>=isc.Page.getWidth()||this.getPageBottom()>=isc.Page.getHeight())
{isc.EH.fireOnPause("checkForBodyOverflowChange",{target:isc.Canvas,methodName:"checkForPageResize"},100)}}
if(this.parentElement==null&&this.position==this.$411){this.$412=this.getPageLeft();this.$413=this.getPageTop();isc.Page.setEvent("resize",this,isc.Page.FIRE_ONCE,"$414")}
this.onDraw();return this}
,isc.A.onDraw=function isc_Canvas_onDraw(){}
,isc.A.doInitialFetch=function isc_Canvas_doInitialFetch(){}
,isc.A.$qb=function isc_Canvas__writeHTML(){this.setDrawnState(isc.Canvas.DRAWING_HANDLE);var _1=this.getDocument(),_2=this.separateContentInsertion;if(this.children!=null&&this.$p2()){this.$p5=true;var _3=this.getTagStart(),_4=this.getTagEnd();_1.write(_2?_3:_3+this.$px())
this.drawChildren();_1.write(_2?this.$px()+_4:_4);this.setDrawnState(isc.Canvas.HANDLE_DRAWN)}else{_1.write(isc.SB.concat(this.getTagStart(),(_2?null:this.$px()),this.getTagEnd()));this.setDrawnState(isc.Canvas.HANDLE_DRAWN);if(_2)this.$p9();this.drawChildren()}
this.$qa();if(isc.Browser.isMoz&&this.getScrollingMechanism()==isc.Canvas.NATIVE)
this.checkNativeScroll();return this}
,isc.A.drawDeferred=function isc_Canvas_drawDeferred(){var _1=(isc.Page.isLoaded()?"idle":"load");if(this.deferredDrawEvent!=null){this.logInfo("drawDeferred() called when object is already pending drawing "+"- No action to take.");return}
var _2=this.getID();this.deferredDrawEvent=isc.Page.setEvent(_1,"delete "+_2+".deferredDrawEvent;"+_2+".draw();",isc.Page.FIRE_ONCE)}
,isc.A.getPrintHTML=function(printProperties,callback){this.isPrinting=true;printProperties=isc.addProperties({},printProperties,this.printProperties);if(printProperties.topLevelCanvas==null){printProperties.topLevelCanvas=this;printProperties.isDrawn=this.isDrawn();printProperties.isVisible=this.isVisible()}
if(printProperties.omitControls==null)
printProperties.omitControls=isc.Canvas.printOmitControls;if(printProperties.includeControls==null)
printProperties.includeControls=isc.Canvas.printIncludeControls;var absPos=printProperties.absPos;this.currentPrintProperties=printProperties||{};var HTML=[this.getPrintTagStart(absPos),,,this.getPrintTagEnd(absPos)];if(!this.children||this.children.length==0||this.allowContentAndChildren){HTML[1]=this.getPrintInnerHTML()}
delete printProperties.inline;printProperties.absPos=this.printChildrenAbsolutelyPositioned;if(printProperties.omitComponents){var omitComponents=printProperties.omitComponents
for(var i=0;i<omitComponents.length;i++){if(isc.isA.String(omitComponents[i]))
omitComponents[i]=window[omitComponents[i]];if(!isc.isAn.Instance(omitComponents[i]))omitComponents[i]=[]}
omitComponents.removeEmpty()}
var children=this.getPrintChildren();var self=this;var completePrintHTML=this.getCompletePrintHTMLFunction(HTML,callback);if(children){var childrenHTML=[],childCount=children.length,completedCount=0;var childHTMLComplete=function(childIndex,html){childrenHTML[childIndex]=html;++completedCount;if(completedCount==childCount){return completePrintHTML(childrenHTML)}
return null}
var thisHTML=null;for(var i=0;i<childCount;i++){var child=children[i];var func=(function(i){return function(html){childHTMLComplete(i,html)}})(i);var childHTML=this.getChildPrintHTML(child,printProperties,func);if(childHTML!==null){thisHTML=childHTMLComplete(i,childHTML)}}
return thisHTML}else{return completePrintHTML()}}
,isc.A.getChildPrintHTML=function isc_Canvas_getChildPrintHTML(_1,_2,_3){return _1.getPrintHTML(_2,_3)}
,isc.A.getCompletePrintHTMLFunction=function isc_Canvas_getCompletePrintHTMLFunction(_1,_2){var _3=this;return function(_4){_3.isPrinting=false;if(isc.isAn.Array(_4))_4=_4.join(isc.emptyString);if(_4)_1[2]=_4;_1=_1.join(isc.emptyString);delete _3.currentPrintProperties.absPos;delete _3.currentPrintProperties;if(_2){_3.fireCallback(_2,"HTML,callback",[_1,_2]);return null}else{return _1}}}
,isc.A.getPrintInnerHTML=function isc_Canvas_getPrintInnerHTML(){var _1=this.children!=null&&this.children.length>0;var _2=this.$px();if(_1&&_2==this.$54t)return null;return _2}
,isc.A.getPrintChildren=function isc_Canvas_getPrintChildren(){var _1=this.children;if(!_1||_1.length==0)return;var _2=[];for(var i=0;i<_1.length;i++){if(this.shouldPrintChild(_1[i]))_2.add(_1[i])}
return(_2.length>0)?_2:null}
,isc.A.shouldPrintChild=function isc_Canvas_shouldPrintChild(_1){if(_1.shouldPrint!=null)return _1.shouldPrint;if(_1.masterElement)return false;var _2=this.currentPrintProperties,_3=_2.omitControls,_4=_2.omitComponents;if(!isc.isAn.Instance(_1)||(_4&&_4.contains(_1)))
{return false}
if(_3){var _5=_2.includeControls;if(_5&&_5.length>0){for(var i=0;i<_5.length;i++){var _7=_5[i];if(isc.isA[_7]&&isc.isA[_7](_1))return true}}
for(var i=0;i<_3.length;i++){var _7=_3[i];if(isc.isA[_7]&&isc.isA[_7](_1)){return false}}}
if((!_1.isDrawn()&&_2.isDrawn)||(!_1.isVisible()&&_2.isVisible))return false;return true}
,isc.A.$xo=function isc_Canvas__fixPNG(){if(this.isPrinting)return false;return true}
,isc.A.getPrintStyleName=function isc_Canvas_getPrintStyleName(){return this.printStyleName||this.styleName}
,isc.A.getPrintTagStart=function isc_Canvas_getPrintTagStart(_1){var _2=this.currentPrintProperties,_3=_2.topLevelCanvas==this,_4=!_3&&!_1&&_2.inline,_5=this.getPrintStyleName();return[(_4?"<span ":"<div "),(_5?"class='"+_5+"' ":null),this.getPrintTagStartAttributes(_1),">"].join(isc.emptyString)}
,isc.A.getPrintTagStartAttributes=function isc_Canvas_getPrintTagStartAttributes(_1){if(_1){return" style='position:absolute;left:"+this.getLeft()+"px;top:"+this.getTop()+"px;width:"+this.getWidth()+"px;height:"+this.getHeight()+"px;' "}else if(this.printChildrenAbsolutelyPositioned){return" style='position:relative;width:"+this.getScrollWidth()+"px;height:"+this.getScrollHeight()+"px;background-color:lightblue;' "}
return null}
,isc.A.getPrintTagEnd=function isc_Canvas_getPrintTagEnd(_1){var _2=this.currentPrintProperties,_3=_2.topLevelCanvas==this,_4=!_3&&!_1&&_2.inline;return _4?"</span>":"</div>"}
,isc.A.makeBackMask=function isc_Canvas_makeBackMask(_1){if(isc.Browser.isMoz&&!isc.Page.isLoaded()){this.$49t=_1;isc.Page.setEvent("load",this,isc.Page.FIRE_ONCE,"makeBackMask");return}
if(this.$49t){_1=this.$49t;delete this.$49t}
this._backMask=isc.BackMask.create(_1);this.addPeer(this._backMask);this._backMask.setZIndex(this.getZIndex(true)-2);this.$qi()}
,isc.A.makeFocusProxy=function isc_Canvas_makeFocusProxy(){if(!this._useFocusProxy||this.$qj||this.$qk||!this.isDrawn()||this.$ql!=null)return;this.$qk=true;this.$qm();this.$qk=null}
,isc.A.$qm=function isc_Canvas__makeFocusProxy(){if(!isc.Page.isLoaded()&&isc.Browser.isSafari){this.getTabIndex();this.$ql=isc.Page.setEvent("load",this,null,"delayedMakeFocusProxy");return}
var _1=this.getTabIndex();if(this.isDisabled())_1=-1;if(isc.Browser.isSafari&&_1==-1){return}
var _2=(isc.Browser.isSafari?1:this.getViewportWidth()),_3=(isc.Browser.isSafari?1:this.getViewportHeight());var _4=isc.Canvas.getFocusProxyString(this.getCanvasName(),true,this.getOffsetLeft()-1,this.getOffsetTop()-1,_2,_3,this.isVisible(),this.$kk(),_1,this.accessKey,false,this.$qn(),this.$qo());isc.Element.insertAdjacentHTML(this.getClipHandle(),"afterEnd",_4)
this.$qj=true}
,isc.A.delayedMakeFocusProxy=function isc_Canvas_delayedMakeFocusProxy(){this.$ql=null;this.makeFocusProxy()}
,isc.A.$qp=function isc_Canvas__clearFocusProxy(){if(!this._useFocusProxy)return;if(this.$ql!=null){isc.Page.clearEvent("load",this.$ql);this.$ql=null}
if(!this.$qj)return;var _1=this.$qq();if(_1!=null){if(isc.Browser.isDOM){if(_1.parentNode){_1.parentNode.removeChild(_1)}else{this.logWarn("Unable clear focusProxy for this widget - element has no parentNode.")}}
this.$qr=null}
this.$qj=null}
,isc.A.$qc=function isc_Canvas__useAccessKeyProxy(){return(isc.Browser.isChrome||(isc.Browser.isMoz&&this._useNativeTabIndex))}
,isc.A.$qd=function isc_Canvas__makeAccessKeyProxy(){var _1=this.accessKey;if(!_1||!this.isDrawn()||!this.$kk())return;var _2=this.$qs("focusProxy");var _3=isc.StringBuffer.concat("<a id='",_2,"' href='javascript:void(0)'",(isc.Browser.isChrome?"' onClick":"' onfocus"),"='var _0=window.",this.getID(),";if(_0){_0.focus()}' ","accessKey='"+_1+"'></a>");isc.Element.insertAdjacentHTML(this.getClipHandle(),"beforeEnd",_3);this.$qt=isc.Element.get(_2)}
,isc.A.$qu=function isc_Canvas__clearAccessKeyProxy(){var _1=this.$qt;delete this.$qt;if(_1)isc.Element.clear(_1)}
,isc.A.drawChildren=function isc_Canvas_drawChildren(){if(this.children==null)return true;if(this.isDrawn()){this.logWarn("drawChildren() is only safe to call BEFORE a canvas has been drawn"+this.getStackTrace());return}
if(this.children&&this.logIsInfoEnabled(this.$n3)){this.logInfo("drawChildren(): "+this.children.length+" children",this.$n3)}
this.$pt();this.layoutChildren(this.$od);if(this.manageChildOverflow)this.$qv=true;for(var i=0;i<this.children.length;i++){var _2=this.children[i];if(_2.masterElement)continue;if(!_2.isDrawn())_2.draw()}}
,isc.A.$qx=function isc_Canvas__completeChildOverflow(_1){if(!this.manageChildOverflow)return;this.$qv=null;this.$qy();var _2=0;for(var i=0;i<_1.length;i++){var _4=_1[i];if(_4!=null&&_4.$qz){_2++;_4.$qz=null;_4.adjustOverflow(this.$oe)}}}
,isc.A.predrawPeers=function isc_Canvas_predrawPeers(){if(!this.peers)return;for(var i=0;i<this.peers.getLength();i++){var _2=this.peers[i];if(_2.$q0==true){if(!isc.isA.Canvas(_2)||_2.masterElement!=this){this.peers.remove(_2);this.addPeer(_2)}
if(!_2.isDrawn())_2.draw()}}}
,isc.A.drawPeers=function isc_Canvas_drawPeers(){if(!this.peers)return true;if(this.logIsInfoEnabled(this.$n3)){this.logInfo("drawPeers(): "+this.peers.length+" peers","drawing")}
var _1=this.peers;this.peers=[];for(var i=0,_3;i<_1.length;i++){_3=_1[i];if(!isc.isA.Canvas(_3)||_3.masterElement!=this){this.addPeer(_3)}else{this.peers.add(_3)}}
for(i=0;i<this.peers.length;i++){var _3=this.peers[i];if(_3.snapTo||_3.snapEdge)_3.$qw();if(!_3.isDrawn())_3.draw()}}
,isc.A.$p8=function isc_Canvas__insertHTML(_1){this.setDrawnState(isc.Canvas.DRAWING_HANDLE);var _2=_1?this.$px():null,_3=this.getTagStart(true),_4=isc.isAn.Array(_3),_5;if(_4){var _6=_3.length;_3[_3.length]=_2;_3[_3.length]=this.getTagEnd();_5=_3.join(isc.$ad);_3.length=_6}else{_5=isc.SB.concat(_3,_2,this.getTagEnd())}
var _7;var _8=this.logIsInfoEnabled(this.$n3);var _9=this.drawContext;if(_9){var _10=_9.element,_11=_9.position||"beforeBegin";this.logInfo("$p8(): drawing with "+_11+" relative to element: "+this.echoLeaf(_10),"drawing");if(_11=="replace"){_11="beforeBegin";if(isc.isA.String(_10))_10=isc.Element.get(_10);_7=this.$q1(_10,_11,_5,true);_10.parentNode.removeChild(_10);this.drawContext=null;if(this.htmlElement)this.htmlElement=null}else{_7=this.$q1(_10,_11,_5,true)}}else if(this.masterElement&&(this.masterElement.getClipHandle()!=null)){if(_8){this.logInfo("inserting HTML next to master element: "+this.masterElement,"drawing")}
var _12=this.masterElement.getClipHandle();_7=this.$q1(_12,this.$n9,_5,true)}else if(this.parentElement){if(_8){this.logInfo("inserting HTML into parent: "+this.parentElement,"drawing")}
var _13=this.parentElement.getHandle();_7=this.$q1(_13,this.$n8,_5,true)}else{if(_8){this.logDebug("inserting HTML at top level","drawing")}
_7=this.$q2(_5)}
if(!(isc.Browser.isIE||isc.Browser.isOpera)){if(_7!=null){if(this.useClipDiv){this._clipDiv=_7;this.$q3=_7.firstChild}else{this.$q3=_7}}else{}}
this.setDrawnState(isc.Canvas.HANDLE_DRAWN)}
,isc.A.$q2=function isc_Canvas__createAbsoluteElement(_1){return this.ns.Element.createAbsoluteElement(_1)}
,isc.A.$q1=function isc_Canvas__insertAdjacentHTML(_1,_2,_3,_4){return this.ns.Element.insertAdjacentHTML(_1,_2,_3,_4)}
,isc.A.$qa=function isc_Canvas__completeHTMLInit(){this.modifyContent();if(isc.Browser.isMoz&&isc.Browser.isStrict&&this.containsIFrame())this.$pw();if(this.manageChildOverflow&&this.children!=null){this.$qx(this.children)}
this.setUpEvents();if(this.$q4){this.$q5(this.left,this.top,this.width,this.$o8);var _1=this.$q6;if(isc.isAn.Array(_1))this.setClip(_1)}
this.setDrawnState(isc.Canvas.COMPLETE);this.$q7=false;if(this.parentElement==null)isc.Canvas.$q8(this);if(this.parentElement!=null&&this.parentElement.$qv){this.$qz=true}else{this.adjustOverflow(this.$ny)}
this.drawPeers()}
,isc.A.setHtmlElement=function isc_Canvas_setHtmlElement(_1){if(this.htmlElement==_1)return;this.htmlElement=_1;if(!this.htmlPosition)this.htmlPosition="afterBegin";var _2=_1?{position:this.htmlPosition,element:this.htmlElement}:null;this.setDrawContext(_2)}
,isc.A.setHtmlPosition=function isc_Canvas_setHtmlPosition(_1){if(_1==null)_1="afterBegin";if(this.htmlPosition==_1)return;this.htmlPosition=_1;if(this.htmlElement==null)return;var _2={position:this.htmlPosition,element:this.htmlElement};this.setDrawContext(_2)}
,isc.A.isDirty=function isc_Canvas_isDirty(){return this.$q7==true}
,isc.A.markForRedraw=function isc_Canvas_markForRedraw(_1){if(isc.$cv)arguments.$cw=this;if(this.isDrawn()&&!this.isDirty()){this.$q9(_1);isc.Canvas.scheduleRedraw(this);this.$q7=true}}
,isc.A.readyToRedraw=function isc_Canvas_readyToRedraw(_1,_2){if(isc.$cv)arguments.$cw=this;if(!this.isDrawn()){return false}
var _3=this.ns.EH;if(_3.lastTarget==this&&(_3.$ku||_3.$km||(isc.Browser.isMobileWebkit&&_3.dragOperation==_3.DRAG_SCROLL)))
{if(_2){this.$q9(_1,true);this.priorityRedraw=true;this.$q7=false;this.markForRedraw(false)}
return false}
return true}
,isc.A.$q9=function isc_Canvas__logRedraw(_1,_2){if(_1==false||!this.logIsInfoEnabled(this.$n4))return;var _3=(!_1&&this.logIsDebugEnabled(this.$n4)||this.logIsDebugEnabled("redrawTrace"));var _4;if(_2==null)_4="Scheduling redraw ";else _4=(_2==true?"DEFERRED ":"")+"Immediate redraw ";this.logInfo(_4+(this.isDirty()&&_2!=null?"of dirty widget ":"")+(this.children&&this.children.length>0?"("+this.getChildCount()+" children) ":"")+"("+(_1?_1:"no reason provided")+")"+(_3?this.getStackTrace():""),this.$n4)}
,isc.A.redraw=function isc_Canvas_redraw(_1){if(isc.$cv)arguments.$cw=this;if(!this.readyToRedraw(_1,true))return this;this.$q9(_1,false);this.$p6(this.$n4);var _2=isc.timeStamp();this.$ra();this.$rb=isc.timeStamp()-_2;return this}
,isc.A.redrawIfDirty=function isc_Canvas_redrawIfDirty(_1){if(this.isDrawn()&&this.isDirty())return this.redraw(_1)}
,isc.A.$ra=function isc_Canvas__updateHTML(){var _1=this.logIsDebugEnabled(this.$n3),_2=this.logIsInfoEnabled(this.$n3),_3;if(_1)_3=isc.timeStamp();if(_2)this.logInfo("$ra(): redrawing","drawing");if(this.peers!=null&&this.peers.getLength()>0)this.redrawPredrawnPeers();var _4=this.children&&this.children.length>0,_5=this.allowContentAndChildren&&_4;if(_4&&!_5&&this.shouldRedrawOnResize()){_5=true}
if((!_4||_5)&&(this.getVisibleWidth()>this.getWidth()||this.getVisibleHeight()>this.getHeight()))
{if(this.notifyAncestorsOnReflow&&this.parentElement!=null){this.notifyAncestorsAboutToReflow()}
this.$q5(null,null,this.width,this.$o8)}
if(_4){if(_5)this.$p9();this.redrawChildren()}else{this.$rd()}
if(this.$qe&&!_4){delete this.$re;this.enforceScrollSize(this.$qe[0],this.$qe[1])}
this.modifyContent();this.setUpEvents();this.$q7=false;this.adjustOverflow(this.$n0,null,true);this.redrawPeers();if(_1){this.logDebug("Redraw() - Total time to redraw in DOM:"+(isc.timeStamp()-_3),"drawing")}
if(this.notifyAncestorsOnReflow&&this.parentElement!=null){this.notifyAncestorsReflowComplete()}
return this}
,isc.A.notifyAncestorsAboutToReflow=function isc_Canvas_notifyAncestorsAboutToReflow(){if(this.parentElement)this.parentElement.$86k(this)}
,isc.A.notifyAncestorsReflowComplete=function isc_Canvas_notifyAncestorsReflowComplete(){if(this.parentElement)this.parentElement.$86l(this)}
,isc.A.$86k=function isc_Canvas__childAboutToReflow(_1){if(this.overflow!=isc.Canvas.VISIBLE){this.$86m=this.getScrollTop();this.$86n=this.getScrollLeft();this.$417=true}else{if(this.parentElement)this.parentElement.$86k(_1)}}
,isc.A.$86l=function isc_Canvas__childReflowComplete(_1){if(this.overflow!=isc.Canvas.VISIBLE){delete this.$417;var _2=false,_3,_4;if(this.$86m!=null&&this.$86m!=this.getScrollTop()){_2=true;_4=this.$86m}
if(this.$86n!=null&&this.$86n!=this.getScrollLeft()){_2=true;_4=this.$86m}
if(_2){this.scrollTo(_3,_4,"Reset scroll position for child content reflow")}}else{if(this.parentElement)this.parentElement.$86l(_1)}}
,isc.A.$p9=function isc_Canvas__updateParentHTML(){var _1=this.$px(),_2=this.getHandle();while(_2.hasChildNodes()){var _3=_2.firstChild.getAttribute?_2.firstChild.getAttribute(this.$of):null;if(_3&&isc.isA.Canvas(window[_3]))break;_2.removeChild(_2.firstChild)}
isc.Element.insertAdjacentHTML(_2,this.$n7,_1)}
,isc.A.$rd=function isc_Canvas__updateInnerHTML(){var _1=this.isPrinting;this.isPrinting=false;var _2=this.$px();this.getHandle().innerHTML=_2;this.isPrinting=_1}
,isc.A.modifyContent=function isc_Canvas_modifyContent(){}
,isc.A.redrawChildren=function isc_Canvas_redrawChildren(){if(!this.children)return true;this.logInfo("redrawChildren(): "+this.children.length+" children","drawing");for(var _1=this.children,i=0;i<_1.length;i++){var _3=_1[i];if(!isc.isA.Canvas(_3))continue;if(_3._redrawWithParent){_3.redraw(false)}}}
,isc.A.redrawPredrawnPeers=function isc_Canvas_redrawPredrawnPeers(){if(!this.peers||this.peers.getLength<1)return;for(var _1=this.peers,i=0;i<_1.length;i++){if(_1[i]&&_1[i].$jp&&_1[i].$q0){_1[i].redraw("redrawPeers")}}}
,isc.A.redrawPeers=function isc_Canvas_redrawPeers(){if(!this.peers)return true;this.logInfo("redrawPeers(): "+this.peers.length+" peers","drawing");for(var _1=this.peers,i=0;i<_1.length;i++){if(_1[i]&&_1[i].$jp&&!_1[i].$q0){_1[i].redraw("redrawPeers")}}}
,isc.A.updateFromServer=function isc_Canvas_updateFromServer(_1){_1=isc.clone(_1);isc.addProperties(_1,{useXmlHttpRequest:true,evalResult:true,suppressAutoDraw:true});if(!_1.evalVars)_1.evalVars={};if(!_1.evalVars.targetComponent)_1.evalVars.targetComponent=this;isc.rpc.sendRequest(_1)}
,isc.A.refreshFromServer=function isc_Canvas_refreshFromServer(_1,_2,_3,_4){this.$rf("refresh",_1,_2,_3,_4)}
,isc.A.replaceFromServer=function isc_Canvas_replaceFromServer(_1,_2,_3,_4){this.$rf("replace",_1,_2,_3,_4)}
,isc.A.$rf=function isc_Canvas__refreshOrReplaceFromURL(_1,_2,_3,_4,_5){if(this.$rg){this.logWarn("Attempt to "+_1+" while "+this.$rh+" is in progress - ignoring.");return}
this.$rg=true;this.$rh=_1;this.$ri=_5;this.logDebug("Submitting to "+_1+" URL: "+_2+", with data: "+this.echo(_3));isc.Comm.sendFieldsToServer({URL:_2,fields:_3,prompt:_4,callback:this.getID()+".$rj(frame)",resultVarName:this.refreshVariable})}
,isc.A.$rj=function isc_Canvas__refreshReply(_1){this.$rg=false;var _2=this.$rh;var _3=_1[this.refreshVariable];if(!isc.isAn.Object(_3)){this.logError("Expected object literal for "+_2+", but got: "+isc.Log.echo(_3));return}
_3=isc.clone(_3);var _4=this;if(_2=="refresh")this.setProperties(_3);else{if(!_3._constructor)_3._constructor=this.getClassName();_4=this.replaceWith(_3)}
isc.clearPrompt();if(this.$ri){if(!isc.isA.Function(this.$ri)){this.$ri=isc.Func.expressionToFunction("canvas",this.$ri)}
if(!isc.isA.Function(this.$ri)){this.logError("Can't convert "+_2+" callback '"+this.$ri+" to a function - not firing callback!");return}
this.$ri(_4)}}
,isc.A.clear=function isc_Canvas_clear(_1){this.$66p=true;if(!_1&&this.logIsInfoEnabled("clears")){var _2="clear()"+(this.children&&this.children.length>0?" ("+this.getChildCount()+" children) ":"")+(this.logIsDebugEnabled("clears")?this.getStackTrace():"");this.logInfo(_2,"clears")}
this.$rk();if(this._eventMask)this.ns.EH.unregisterMaskableItem(this);if(this==isc.Canvas.$rl)isc.Canvas.hideResizeThumbs();if(this._useFocusProxy)this.$qp();if(this.children){for(var _3=this.children,i=0;i<_3.length;i++){var _5=_3[i];if(!isc.isA.Canvas(_5))continue;_5.$74w=true;_5.clear(true);_5.$74w=null}}
if(this.getHandle())this.clearHandle();if(this.parentElement)this.parentElement.childCleared(this);if(this.masterElement)this.masterElement.peerCleared(this);delete this.$re;delete this.$qt;if(this.deferredDrawEvent){isc.Page.clearEvent(this.deferredDrawEvent);delete this.deferredDrawEvent}
if(this.peers){for(var _3=this.peers,i=0;i<_3.length;i++){if(this.$74w)_3[i].$74w=true;_3[i].clear(true);_3[i].$74w=null}}
if(this.canAcceptDrop)this.ns.EH.unregisterDroppableItem(this);this.setDrawnState(isc.Canvas.UNDRAWN);this.$808=this.$809=null;delete this.$66p}
);isc.evalBoundary;isc.B.push(isc.A.destroy=function isc_Canvas_destroy(_1){if(this.doNotDestroy){this.clear();return}
if(this.destroyed)return;this.destroying=true;if(this.$rm)isc.Timer.clearTimeout(this.$rn);this.hideClickMask();this.$75s(true,_1);if(isc.Hover.lastHoverCanvas==this)isc.Hover.hide();this.clear(true);this.deparent();this.depeer();if(this.children){for(var _2=this.children.duplicate(),i=0;i<_2.length;i++){var _4=_2[i];if(!isc.isA.Canvas(_4))continue;_4.destroy(true)}}
if(this.peers){for(var _2=this.peers.duplicate(),i=0;i<_2.length;i++){_2[i].destroy(true)}}
delete this.peers;delete this.children;if(this.hscrollbar&&!this.hscrollbar.destroyed){this.hscrollbar.destroy(true);delete this.hscrollbar}
if(this.vscrollbar&&!this.vscrollbar.destroyed){this.vscrollbar.destroy(true);delete this.vscrollbar}
if(this.$542){var _5=this.$542;for(var _6 in _5){var _7=_5[_6];for(var i=0;i<_7.length;i++){var _8=_7[i],_4=_8?window[_8]:null;if(_4&&!_4.destroyed&&_4.destroy&&!_4.dontAutoDestroy)
{_4.destroy()}}
delete this[_6]}}
if(this.eventProxy!=null)this.clearEventProxy();if(this.$kt!=null){for(var _2=this.$kt.duplicate(),i=0;i<_2.length;i++){_2[i].clearEventProxy()}}
if(this.locatorParent&&this.locatorParent.locatorChildDestroyed){this.locatorParent.locatorChildDestroyed(this)}
delete this.locatorParent;this._canvasList();isc.Canvas.$ro(this);this.$rp();isc.EH.canvasDestroyed(this);isc.ClassFactory.dereferenceGlobalID(this);if(this.pointersToThis!=null){for(var i=0;i<this.pointersToThis.length;i++){var _9=this.pointersToThis[i];if(_9.object&&(_9.object[_9.property]==this)){var _10;_9.object[_9.property]=_10}}
delete this.pointersToThis}
if(this.$rr){for(var _11 in this){delete this[_11]}}
this.$63x();this.destroyed=true}
,isc.A.markForDestroy=function isc_Canvas_markForDestroy(){if(isc.$cv)arguments.$cw=this;if(this.destroyed||this.destroying||this.isPendingDestroy())return;this.$65i=true;this.$75s(false,false);isc.Canvas.scheduleDestroy(this)}
,isc.A.isPendingDestroy=function isc_Canvas_isPendingDestroy(){return!this.destroyed&&!this.destroying&&(this.$65i==true)}
,isc.A.$75s=function isc_Canvas__logDestroy(_1,_2){if(this.$ih)return;if(_1)this.$p6("destroys");if(!_2&&this.logIsInfoEnabled("destroys")){this.logInfo((_1?"destroy()":"markForDestroy()")+(this.children&&this.children.length>0?" ("+this.getChildCount()+" children) ":"")+(this.logIsDebugEnabled("destroys")?this.getStackTrace():""),"destroys")}}
,isc.A.clearHandle=function isc_Canvas_clearHandle(){if(!this.getHandle())return;this.$p6("clears");this.getHandle().eventProxy=null;this.getClipHandle().eventProxy=null;var _1=this.getClipHandle();this.$q3=null;this.$rs=null;this._clipDiv=null;isc.Element.clear(_1,this._clearWithRemoveChild)}
,isc.A.replaceWith=function isc_Canvas_replaceWith(_1){if(!isc.isAn.Object(_1))return;var _2;if(isc.Browser.isDOM){var _3=isc.ClassFactory.getNextGlobalID();isc.Element.insertAdjacentHTML(this.getClipHandle(),"afterEnd","<DIV ID="+_3+"></DIV>");var _2=this.getDocument().getElementById(_3);_1.drawContext={element:_2}}
var _4=this.parentElement,_5=this.masterElement,_6=(isc.isA.Layout(_4)&&_4.hasMember(this)),_7=(_6?_4.getMemberNumber(this):0);this.destroy();if(isc.isA.Canvas(_1)){_1.clear()}else{_1.autoDraw=false;_1=isc.ClassFactory.newInstance(_1);if(_1==null){this.logWarn("canvas.replaceWith(): Unable to create a widget "+"instance from the argument passed in.  Returning.")
return}}
if(_6){_4.addMember(_1,_7)}else if(_4){_4.addChild(_1)}else if(_5){_5.addPeer(_1)}
if(!_1.isDrawn())_1.draw();if(isc.Browser.isDOM){if(_2.parentNode){_2.parentNode.removeChild(_2)}else{this.logWarn("unable to clear marker")}}
return _1}
,isc.A.setDrawContext=function isc_Canvas_setDrawContext(_1){var _2=this.isDrawn();this.deparent();if(_2)this.clear();this.drawContext=_1;if(_2)this.draw()}
,isc.A.$qs=function isc_Canvas__getDOMID(_1,_2,_3){if(_2){var _4=isc.ClassFactory.getDOMID(this.getID(),_1);if(this.reuseDOMIDs){if(!this.$63y)this.$63y=[];this.$63y[this.$63y.length]=_4}
return _4}
if(!this.$rt)this.$rt={};if(!this.$rt[_1])
this.$rt[_1]=isc.ClassFactory.getDOMID(this.getID(),_1);return this.$rt[_1]}
,isc.A.$557=function isc_Canvas__getDOMPartName(_1){if(!this.$rt)return null;for(var _2 in this.$rt){if(this.$rt[_2]==_1)return _2}}
,isc.A.$63x=function isc_Canvas__releaseDOMIDs(){if(!this.reuseDOMIDs)return;if(this.$63y){for(var i=0;i<this.$63y.length;i++){isc.ClassFactory.releaseDOMID(this.$63y[i])}}
if(this.$rt){for(var i in this.$rt){isc.ClassFactory.releaseDOMID(this.$rt[i])}}}
,isc.A.getCanvasName=function isc_Canvas_getCanvasName(){if(!this.$ru)this.$ru=this.$qs(this.$og,true);return this.$ru}
,isc.A.$rv=function isc_Canvas__getClipDivDOMID(){return this.$qs(this.$oh)}
,isc.A.getTransformCSS=function isc_Canvas_getTransformCSS(){if(this.rotation!=null)return";"+isc.Element.getRotationCSS(this.rotation,this.transformOrigin);return null}
,isc.A.getTagStart=function isc_Canvas_getTagStart(_1){var _2=isc.Canvas,_3=this.$rw();if(this.zIndex==_2.AUTO)this.zIndex=_2.getNextZIndex();var _4=(this.eventProxy?this.eventProxy.ID:this.ID);var _5=this.$rx(),_6=_5[0],_7=_5[1];if(!_2.$ry){_2.$ry=" onfocus=";_2.$rz=" onblur=";_2.$r0=" tabindex="
_2.$r1=" accessKey="}
var _8=isc.Browser.isMoz;var _9=this.opacity;if(!isc.Browser.isIE){if(_9!=null)_9=_9/ 100}
if(isc.Browser.isMoz){if(this.smoothFade&&(_9==1||_9==null))_9=0.9999}
if(this.useClipDiv){var _10=this.getCurrentCursor(),_11,_12,_13=this._useNativeTabIndex;if(this.clipHandleIsFocusHandle==false)_13=false;if(_13&&this.$kk()){_11=isc.SB.concat(_2.$ry,this.$qn(),_2.$rz,this.$qo(),!this.isDisabled()?_2.$r0+this.getTabIndex():null,(!this.$qc()&&this.accessKey!=null)?_2.$r1+this.accessKey:null);if(isc.Browser.isMoz){_12=isc.StringBuffer.concat((this.mozOutlineOffset!=null?";-moz-outline-offset:"+this.mozOutlineOffset:null),(this.mozOutlineColor!=null?";-moz-outline-color:"+this.mozOutlineColor:null),(!this.showFocusOutline?";-moz-outline-style:none":null))}else if(isc.Browser.isSafari){if(!this.showFocusOutline){_12=";outline-style:none"}}}
var _14=isc.Browser.isMoz&&isc.Browser.geckoVersion>=20080529;var _15=isc.StringBuffer.concat("<div id='",this.$rv(),"' eventProxy=",_4,(_14&&this.ariaRole?" role='"+this.ariaRole+"'":""),(_14&&this.ariaState?this.getAriaStateAttributes():""),(this.className?" class='"+this.className+"'":""),_11," style='","POSITION:",this.position,";LEFT:",this.left,"px;TOP:",this.top,"px;WIDTH:",_6,"px;HEIGHT:",_7,"px;Z-INDEX:",this.zIndex,(this.visibility==_2.INHERIT?"":";VISIBILITY:"+this.visibility),(this.backgroundColor==null?"":";BACKGROUND-COLOR:"+this.backgroundColor),(this.backgroundImage==null?"":";BACKGROUND-IMAGE:url("+this.getImgURL(this.backgroundImage)+")"+";BACKGROUND-REPEAT:"+this.backgroundRepeat+(this.backgroundPosition?";BACKGROUND-POSITION:"+this.backgroundPosition:"")),(this.border?";BORDER:"+this.border:""),(this.padding!=null||this.$415?";PADDING:0px":""),this.$r2(),(_9!=null?(this.$65q?";-moz-opacity:":";opacity:")+_9:""),(_8?";-moz-box-sizing:border-box":null),_12,this.getTransformCSS(),(isc.Browser.isTouch?(!this.canSelectText?";-webkit-user-select:none":";-webkit-user-select:text"):null),";OVERFLOW:",_3,";' ONSCROLL='return "+_4+".$lh()'>","<div id='",this.getCanvasName(),"' eventProxy='",_4,(this.textDirection!=null?"' dir='"+this.textDirection:""),"' style='POSITION:relative;VISIBILITY:inherit",";Z-INDEX:",this.zIndex,(_10==_2.AUTO?"":";CURSOR:"+_10),(this.padding!=null?";PADDING:"+this.padding+"px":""),(this.topPadding!=null?";padding-top:"+this.topPadding+"px":""),(this.bottomPadding!=null?";padding-bottom:"+this.bottomPadding+"px":""),(this.leftPadding!=null?";padding-left:"+this.leftPadding+"px":""),(this.rightPadding!=null?";padding-right:"+this.rightPadding+"px":""),";'>")}else{if(!_2.$r4){_2.$r5=" style='POSITION:absolute;LEFT:";_2.$r6=" style='POSITION:relative;LEFT:";_2.$bd=" class='";_2.$416="'";_2.$r7=";VISIBILITY:";_2.$r8=";CURSOR:";var _16=_2.$r4=[];_16[0]="<div id=";_16[2]=" eventProxy=";_16[15]="px;TOP:";_16[22]="px;WIDTH:";_16[28]="px;HEIGHT:";_16[34]="px;Z-INDEX:";_16[44]=";OVERFLOW:";_16[59]="' ONSCROLL='return ";_16[61]=".$lh()' "}
var _16=_2.$r4;_16[1]=this.getCanvasName();_16[3]=_4;if(this.className!=null){_16[4]=_2.$bd;_16[5]=this.className;_16[6]=_2.$416}else{_16[4]=_16[5]=_16[6]=null}
_16[7]=(this.textDirection!=null?" dir="+this.textDirection:null);_16[8]=(this.position==_2.RELATIVE?_2.$r6:_2.$r5);isc.$bk(_16,this.left,9,6);isc.$bk(_16,this.top,16,6);isc.$bk(_16,_6,23,5);isc.$bk(_16,_7,29,5);if(this.zIndex!=_2.AUTO)isc.$bk(_16,this.zIndex,35,9);else{_16[35]=this.zIndex;_16[36]=_16[37]=_16[38]=_16[39]=_16[40]=_16[41]=_16[42]=_16[43]=null}
_16[45]=_3;if(this.visibility!=_2.INHERIT){_16[46]=_2.$r7;_16[47]=this.visibility}else{_16[46]=_16[47]=null}
_16[48]=(this.backgroundColor==null?null:";BACKGROUND-COLOR:"+this.backgroundColor);_16[49]=(this.backgroundImage==null?null:";BACKGROUND-IMAGE:url("+this.getImgURL(this.backgroundImage)+");BACKGROUND-REPEAT:"+this.backgroundRepeat+(this.backgroundPosition?";BACKGROUND-POSITION:"+this.backgroundPosition:""));_16[50]=(_8?";-moz-box-sizing:border-box":null);var _10=this.getCurrentCursor();if(_10==_2.AUTO){_16[51]=_16[52]=null}else{_16[51]=_2.$r8;_16[52]=_10}
_16[53]=this.$r2();_16[54]=(this.padding!=null?";PADDING:"+this.padding+isc.px:null);if(this.topPadding!=null)
_16[54]=(_16[54]||"")+";padding-top:"+this.topPadding+"px";if(this.bottomPadding!=null)
_16[54]=(_16[54]||"")+";padding-bottom:"+this.bottomPadding+"px";if(this.leftPadding!=null)
_16[54]=(_16[54]||"")+";padding-left:"+this.leftPadding+"px";if(this.rightPadding!=null)
_16[54]=(_16[54]||"")+";padding-right:"+this.rightPadding+"px";_16[55]=(this.border?";BORDER:"+this.border:null);if(isc.Browser.isIE){if(!isc.Canvas.neverUseFilters||this.useOpacityFilter){_16[56]=(_9==null?null:";filter:progid:DXImageTransform.Microsoft.Alpha(opacity="+_9+")")}else{_16[56]=null}
if(!isc.Canvas.neverUseFilters){if(this.$r9){_16[57]=";filter:progid:DXImageTransform.Microsoft.iris(irisStyle=circle)"}else{_16[57]=null}}else{_16[57]=null}}else{if(_9!=null){_16[56]=(this.$65q?";-moz-opacity:":";opacity:")+_9}else{_16[56]=null}}
_16[58]=this.getTransformCSS();_16[60]=_4;var _17=64;if(this.$kk()&&this._useNativeTabIndex&&this.clipHandleIsFocusHandle){_16[64]=_2.$ry;_16[65]=this.$qn();_16[66]=_2.$rz;_16[67]=this.$qo();if(!this.isDisabled()){_16[68]=_2.$r0;isc.$bk(_16,this.getTabIndex(),69,5);if(this.accessKey!=null){_16[74]=_2.$r1;_16[75]=this.accessKey;_17=76}else _17=74;if(!this.showFocusOutline){if(!_2.$sa)_2.$sa=" hideFocus=true";_16[_17]=_2.$sa;_17+=1}}else _17=68}
if((this.ariaRole||this.ariaState)&&isc.Canvas.ariaEnabled()&&!isc.Canvas.useLiteAria())
{if(this.ariaRole){_16[_17++]=" role='";_16[_17++]=this.ariaRole;_16[_17++]="' "}
if(this.ariaState){_16[_17++]=this.getAriaStateAttributes()}}
_16.length=_17;_16[_17]=this.$oa;if(_1)return _16;return _16.join(isc.emptyString)}
return _15}
,isc.A.$r2=function isc_Canvas__getMarginHTML(){if(!this.$sb()&&this.$sc==null){if(this.margin==null)return null;return isc.SB.concat(isc.semi,this.$om,this.margin,isc.px)}
var _1=this.$sd(),_2=isc.SB.concat(isc.semi,this.$oi,_1.left,isc.px,isc.semi,this.$oj,_1.right,isc.px,isc.semi,this.$ok,_1.top,isc.px,isc.semi,this.$ol,_1.bottom,isc.px);return _2}
,isc.A.getTagEnd=function isc_Canvas_getTagEnd(){if(this.useClipDiv)return this.$oo;return this.$on}
,isc.A.$rw=function isc_Canvas__getHandleOverflow(){var _1=this.overflow;var _2=(this.overflow==isc.Canvas.SCROLL||this.overflow==isc.Canvas.AUTO),_3=_2&&this.showCustomScrollbars,_4=_2&&!this.showCustomScrollbars;if(this.overflow==isc.Canvas.HIDDEN||_3)
{if(this.$ks){_1=this.$se?this.$nz:"-moz-scrollbars-none";this.$r3=true}else{_1=this.$nz}}else if(isc.Browser.isOpera&&this.overflow==isc.Canvas.VISIBLE){_1=this.$nz}else if(isc.Browser.isMoz){if(_4)this.$r3=true;else if(this.$ks){_1=this.$se?this.$nz:"-moz-scrollbars-none";this.$r3=true}}
if(this.useClipDiv&&(this.overflow==isc.Canvas.CLIP_H||this.overflow==isc.Canvas.CLIP_V))
{_1=this.$nz}
return _1}
,isc.A.$rx=function isc_Canvas__getInitialHandleSize(){var _1=this.getInitialWidth(),_2=this.getInitialHeight();return this.$sf(_1,_2)}
,isc.A.getInitialWidth=function isc_Canvas_getInitialWidth(){return this.getWidth()}
,isc.A.getInitialHeight=function isc_Canvas_getInitialHeight(){return this.getHeight()}
,isc.A.$sf=function isc_Canvas__adjustHandleSize(_1,_2){var _3=this.$sd();if(_1!=null){if(this.showCustomScrollbars&&this.vscrollOn){_1-=this.getScrollbarSize()}
_1-=(_3.left+_3.right);if(this.isBorderBox){}else if(this.useClipDiv){if(this.padding==null&&!this.$415){_1-=this.getHBorderPad()}else{_1-=this.getHBorderSize()}}else{_1-=this.getHBorderPad()}}
if(_2!=null){if(this.showCustomScrollbars&&this.hscrollOn){_2-=this.getScrollbarSize()}
_2-=(_3.top+_3.bottom);if(this.isBorderBox){}else if(this.useClipDiv){if(this.padding==null&&!this.$415){_2-=this.getVBorderPad()}else{_2-=this.getVBorderSize()}}else{_2-=this.getVBorderPad()}}
if(_1!=null&&_1<1){this.logInfo("Specified width:"+this.getInitialWidth()+" adjusted for border, margin, "+"and scrollbars would cause initial handle size to be less than or equal to "+"zero, which is not supported. Clamping handle width to 1px.","sizing");_1=1}
if(_2!=null&&_2<1){this.logInfo("Specified height:"+this.getInitialHeight()+" adjusted for border, margin, "+"and scrollbars would cause initial handle size to be less than or equal to "+"zero, which is not supported. Clamping handle height to 1px.","sizing");_2=1}
var _4=this.$op;_4[0]=_1;_4[1]=_2;return _4}
,isc.A.$qn=function isc_Canvas__getNativeFocusHandlerString(_1){var _2=this.getID();var _3=_1?null:this.$ob;if(isc.Browser.isMoz)
return isc.SB.concat(_3,this.$or,_2,this.$ot,_3);return isc.SB.concat(_3,this.$oq,this.getID(),this.$ot,_3)}
,isc.A.$qo=function isc_Canvas__getNativeBlurHandlerString(_1){var _2=_1?null:this.$ob;return isc.SB.concat(_2,this.$os,this.getID(),this.$ot,_2)}
,isc.A.$sg=function isc_Canvas__getNativeFocusHandlerMethod(){if(!this.$sh){this.$sh=new Function("event",this.$qn(true))}
return this.$sh}
,isc.A.$si=function isc_Canvas__getNativeBlurHandlerMethod(){if(!this.$sj){this.$sj=new Function("event",this.$qo(true))}
return this.$sj}
,isc.A.getHandle=function isc_Canvas_getHandle(){if(isc.$cv)arguments.$cw=this;if(this.destroyed){this.logWarn("Attempt to access destroyed widget in the DOM - "+"destroy() called at invalid time (eg: mid-draw) or invalid method "+"called on destroy()d widget. Stack Trace:"+this.getStackTrace())}
if(!(this.$pu||this.$if))return null;if(this.$q3==null){var _1=this.getCanvasName();this.$q3=this.ns.Element.get(_1);if(this.$q3==null){this.logWarn("Unable to find handle for drawn Canvas, elementId: "+_1)}}
return this.$q3}
,isc.A.getClipHandle=function isc_Canvas_getClipHandle(){if(!this.useClipDiv)return this.getHandle();if(!(this.$pu||this.$if))return null;if(this._clipDiv==null){var _1=this.$rv();this._clipDiv=this.ns.Element.get(_1);if(this._clipDiv==null){this.logWarn("Unable to find clipHandle for drawn Canvas, elementId: "+_1)}}
return this._clipDiv}
,isc.A.getScrollHandle=function isc_Canvas_getScrollHandle(){return this.getClipHandle()}
,isc.A.$sk=function isc_Canvas__getURLHandle(){if(!this.containsIFrame())return null;var _1=this.getHandle();if(!_1)return null;_1=_1.firstChild;if(_1&&_1.tagName&&(_1.tagName.toLowerCase()=="iframe"))return _1
return null}
,isc.A.$sl=function isc_Canvas__getFocusProxyHandle(){if(!this._useFocusProxy||!this.$qj)return null;if(!this.$qr){var _1=this.getCanvasName()+"__focusProxy";this.$qr=this.getDocument().getElementById(_1)}
return this.$qr}
,isc.A.$qq=function isc_Canvas__getFocusProxyParentHandle(){if(!this._useFocusProxy)return null;if(!this.$qr)this.$qr=this.$sl();return(this.$qr!=null?this.$qr.parentNode:null)}
,isc.A.getStyleHandle=function isc_Canvas_getStyleHandle(){if(!this.$rs){this.$rs=(this.getClipHandle()?this.getClipHandle().style:null)}
return this.$rs}
,isc.A.setUpEvents=function isc_Canvas_setUpEvents(){if(this.canAcceptDrop)this.ns.EH.registerDroppableItem(this)}
,isc.A.$pt=function isc_Canvas__instantiateChildren(_1){if(!_1)_1=this.children;if(!_1)return;this.children=[];for(var i=0,_3;i<_1.length;i++){_3=_1[i];if(!_3)continue;if(!isc.isA.Canvas(_3)||_3.parentElement!=this){this.addChild(_3)}else{this.children.add(_3)}}}
,isc.A.$sm=function isc_Canvas__lazyAutoChildCreate(_1){_1=_1.substring(this.$ou.length);var _2=this.$d1(_1);var _3=this[_2]?this:isc.isA.Canvas(this.creator)&&this.creator[_2]?this.creator:this;if(isc.isA.Canvas(_3[_1]))return _3[_1];return(_3[_1]=_3.createAutoChild(_1))}
,isc.A.createCanvas=function isc_Canvas_createCanvas(_1){if(isc.isA.Canvas(_1))return _1;if(_1==null)return;if(isc.isA.String(_1)){if(isc.startsWith(_1,this.$ou)){return this.$sm(_1)}
if(isc.startsWith(_1,this.$90j)){var _2=_1.substring(this.$90j.length);var _3="width";if(this.orientation==isc.Layout.VERTICAL)_3="height";var _4={autoDraw:false};_4[_3]=_2;return isc.LayoutSpacer.create(_4)}
return window[_1]}
var _5=_1.autoChildName;if(_5){return this[_5]=this.createAutoChild(_5,_1)}
var _6=_1._constructor;if(_6==null||isc.ClassFactory.getClass(_6)==null){_6=isc.Canvas}
_1._constructor=null;_1.autoDraw=false;return isc.ClassFactory.newInstance(_6,_1)}
,isc.A.createCanvii=function isc_Canvas_createCanvii(_1){if(_1==null)return;for(var i=0;i<_1.length;i++){_1[i]=this.createCanvas(_1[i])}
return _1}
,isc.A.setEventProxy=function isc_Canvas_setEventProxy(_1){var _2=this.eventProxy;if(_2==_1)return;if(_2!=null){_2.$kt.remove(this);if(this.isDrawn()){if(this.getHandle()!=null)this.getHandle().eventProxy=null;if(this.getClipHandle()!=this.getHandle())this.getClipHandle().eventProxy=null}}
this.eventProxy=_1;if(_1!=null){if(!isc.isA.Canvas(_1)){this.logWarn("setEventProxy() passed invalid eventProxy - clearing this property");this.eventProxy=null}else{if(_1.$kt==null)_1.$kt=[];_1.$kt.add(this)}}
if(this.isDrawn())this.redraw("eventProxy updated")}
,isc.A.clearEventProxy=function isc_Canvas_clearEventProxy(){this.setEventProxy()}
,isc.A.addChild=function isc_Canvas_addChild(_1,_2,_3){if(isc.$cv)arguments.$cw=this;if(!_1)return null;if(_1==this){this.logWarn("Attempt to add a child to itself");return}
if(!isc.isAn.Instance(_1))_1=this.createCanvas(_1);if(!isc.isA.Canvas(_1)){this.logWarn("addChild(): trying to install a non-canvas as a child.  Returning.");return null}
if(_1.parentElement==this)return _1;var _4=_1.isDrawn();if(_1.parentElement)_1.deparent(_2);isc.Canvas.$ro(_1);if(_1.drawContext)_1.drawContext=null;if(_1.htmlElement)_1.htmlElement=null;_1.parentElement=this;_1.topElement=(this.topElement||this);_1.$sn();if(_2)this[_2]=_1;if(!this.children)this.children=[];if(!this.children.contains(_1))this.children.add(_1);var _5=_1.masterElement;if(_5&&_5.parentElement!=this){_5.peers.remove(_1);if(_5[_2]==_1)_5[_2]=null;_1.masterElement=null}
if(_1.peers){for(var i=0;i<_1.peers.length;i++)this.addChild(_1.peers[i])}
if(_1.isDrawn())_1.clear();if(_4&&!this.warnAboutClear&&!isc.Page.isLoaded()){this.logWarn("Adding already drawn widget:"+_1.getID()+" to new parent:"+this.getID()+". Child has been cleared so it can be drawn inside the new "+"parent. This may be a result of autoDraw being enabled for the child.")}
if(this.isDrawn())_1.$qw();var _7=this.ns.EH;if(_7.clickMaskUp()){var _8=_7.getAllClickMaskIDs();for(var i=_8.length-1;i>=0;i--){var _9=_7.targetIsMasked(this,_8[i]);if(!_9){_7.addUnmaskedTarget(_1,_8[i]);break}else{var _10=_7.targetIsMasked(_1,_8[i]);if(!_10)_7.maskTarget(_1,_8[i])}}}
if(_3==false||_1.$so){_1.$so=null;return _1}
var _11=false,_12=(_1.$sp||!_1.tabIndex);if(isc.isA&&isc.isA.Layout&&_12&&(_1.$kk()||(_1.children!=null&&_1.children.length>0)))
{var _13=_1;while(_13.parentElement){if(isc.isA.Layout(_13.parentElement)&&_13.parentElement.isDrawn())
{_13.parentElement.updateMemberTabIndex(_13);if(_13.parentElement==this)_11=true}
_13=_13.parentElement}}
if(this.isDrawn()&&!_1.masterElement){if(this.logIsDebugEnabled(this.$n3)){this.logInfo("child added to already drawn parent: "+(isc.Page.isLoaded()?"page loaded, will draw immediately":"page not loaded, will defer child drawing"),"drawing")}
if(!_11&&_1.$kk()&&_12){var _14;if(this.children.length>1){for(var i=this.children.length-2;i>=0;i--){if(this.children[i].$kk()&&this.children[i].$sp){_14=this.children[i];break}}}
if(_14==null&&this.$kk()&&this.$sp){_14=this}
if(_14!=null)_1.$sq(_14)}
_1.draw();this.adjustOverflow("addChild")}
return _1}
,isc.A.$sn=function isc_Canvas__updateChildrenTopElement(){if(this.dataPath)this.setDataPath(this.dataPath);var _1=this.children;if(!_1||_1.length==0)return;for(var i=0;i<_1.length;i++){var _3=_1[i];_3.topElement=this.topElement;_3.$sn()}}
,isc.A.reparent=function isc_Canvas_reparent(_1){if(this.getID()==_1.getID())return false;if((this.parentElement==_1.parentElement)&&this.getClipHandle()&&_1.getClipHandle()&&(this.getClipHandle().parentNode==_1.getClipHandle().parentNode)){return false}
this._adjacentHandle=_1.getClipHandle();if(_1.parentElement){_1.parentElement.addChild(this)}else{if(this.parentElement)this.deparent();else this.clear();this.draw()}
return true}
,isc.A.removePeer=function isc_Canvas_removePeer(_1,_2){if(_1==null)return;var _3=this.peers,_4;if(!_3||(_4=_3.indexOf(_1))==-1){this.logWarn("Attempt to remove peer: "+_1+" from Canvas that is not its master");return}
_3.removeAt(_4);if(this[_2]==_1)this[_2]=null;_1.masterElement=null;if(_1.depeered)_1.depeered(this,_2);if(this.peerRemoved)this.peerRemoved(_1,_2)}
,isc.A.depeer=function isc_Canvas_depeer(_1){if(!this.masterElement)return;this.masterElement.removePeer(this,_1)}
,isc.A.removeChild=function isc_Canvas_removeChild(_1,_2){if(isc.$cv)arguments.$cw=this;if(_1==null)return;var _3=this.children,_4;if(!_3||(_4=_3.indexOf(_1))==-1){this.logWarn("Attempt to remove child: "+_1+" from Canvas that is not its parent");return}
_3.removeAt(_4);if(this[_2]==_1)this[_2]=null;if(_1.isDrawn())_1.clear();delete _1.parentElement;delete _1.topElement;isc.Canvas.$q8(_1);if(_1.peers)_1.peers.map("deparent");if(_1.deparented)_1.deparented(this,_2);if(this.childRemoved)this.childRemoved(_1,_2)}
,isc.A.deparent=function isc_Canvas_deparent(_1){if(!this.parentElement)return;this.parentElement.removeChild(this,_1)}
,isc.A.addPeer=function isc_Canvas_addPeer(_1,_2,_3,_4){if(!_1)return null;if(!isc.isAn.Instance(_1))_1=this.createCanvas(_1);if(_4==true)_1.$q0=true;if(_1.masterElement==this)return null;if(_1.masterElement)_1.depeer(_2);_1.masterElement=this;if(_2)this[_2]=_1;if(!this.peers)this.peers=[];if(!this.peers.contains(_1))this.peers.add(_1);if(this.parentElement){this.parentElement.addChild(_1,_2)}else if(_1.parentElement){_1.deparent()}
if(_1.$nt&&(_1.opacity!=this.opacity))
_1.setOpacity(this.opacity);if(_1.$jq&&(_1.visibility!=this.visibility)){_1.setVisibility(this.visibility)}
if(_1.snapTo||_1.snapEdge)_1.$qw();var _5=this.ns.EH;if(_5.clickMaskUp()){var _6=_5.getAllClickMaskIDs();for(var i=_6.length-1;i>=0;i--){var _8=_5.targetIsMasked(this,_6[i]);if(!_8){_5.addUnmaskedTarget(_1,_6[i]);break}else{var _9=_5.targetIsMasked(_1,_6[i]);if(!_9)_5.maskTarget(_1,_6[i])}}}
if(_3==false)return _1;if(this.isDrawn()&&!_1.isDrawn()){_1.draw();if(_1.$q0)this.redraw()}
return _1}
,isc.A.setSnapTo=function isc_Canvas_setSnapTo(_1){this.snapTo=_1;this.parentResized()}
,isc.A.getSnapTo=function isc_Canvas_getSnapTo(){return this.snapTo}
,isc.A.setSnapEdge=function isc_Canvas_setSnapEdge(_1){this.snapEdge=_1;this.parentResized()}
,isc.A.getSnapEdge=function isc_Canvas_getSnapEdge(){return this.snapEdge}
,isc.A.getFieldMethod=function isc_Canvas_getFieldMethod(_1,_2,_3){if(_2=="children"){if(_3=="add")return"addChild";if(_3=="remove")return"removeChild"}
return this.Super("getFieldMethod",arguments)}
,isc.A.getParentElements=function isc_Canvas_getParentElements(){var _1=[],_2=this.parentElement;while(_2){_1.add(_2);_2=_2.parentElement}
return _1}
,isc.A.contains=function isc_Canvas_contains(_1,_2){if(!_2&&_1)_1=_1.parentElement;while(_1){if(_1==this)return true;_1=_1.parentElement}
return false}
,isc.A.$sr=function isc_Canvas__isVisibilityAncestorOf(_1){var _2=_1;while(_2){if(_2==this)return true;var _3=(_2.visibility==isc.Canvas.INHERIT);if(!_3)return false;_2=_2.parentElement}
return false}
,isc.A.getChildCount=function isc_Canvas_getChildCount(){if(this.children==null)return;return this.children.map("getChildCount").sum()+this.children.length}
,isc.A.showClickMask=function isc_Canvas_showClickMask(_1,_2,_3){var _4=this.getID();if(!this.ns.EH.clickMaskUp(_4)){return this.ns.EH.showClickMask(_1,_2,_3,_4)}}
,isc.A.hideClickMask=function isc_Canvas_hideClickMask(_1){if(_1==null)_1=this.getID();if(this.ns.EH.clickMaskUp(_1))this.ns.EH.hideClickMask(_1)}
,isc.A.clickMaskUp=function isc_Canvas_clickMaskUp(_1){if(_1==null)_1=this.getID();return this.ns.EH.clickMaskUp(_1)}
,isc.A.unmask=function isc_Canvas_unmask(_1){this.ns.EH.addUnmaskedTarget(this,_1)}
,isc.A.mask=function isc_Canvas_mask(_1){this.ns.EH.maskTarget(this,_1)}
,isc.A.isMasked=function isc_Canvas_isMasked(_1){return this.ns.EH.targetIsMasked(this,_1)}
,isc.A.$qf=function isc_Canvas__isHardMasked(){var _1=isc.EH.clickMaskRegistry;if(!_1||_1.length==0)return false;for(var i=_1.length-1;i>=0;i--){var _3=_1[i];if(!this.isMasked(_3))return false;if(isc.EH.isHardMask(_3))return true}
return false}
,isc.A.showComponentMask=function isc_Canvas_showComponentMask(_1){if(!this.componentMask){this.componentMask=this.addAutoChild("componentMask",isc.addProperties({},_1,{disabled:true,autoDraw:false,$nt:false}),isc.Canvas);this.componentMask.setRect(this.getOffsetLeft(),this.getOffsetTop(),this.getVisibleWidth(),this.getVisibleHeight());this.addPeer(this.componentMask)}else if(!this.componentMask.isDrawn())this.componentMask.draw();this.disableKeyboardEvents(true,true)}
,isc.A.hideComponentMask=function isc_Canvas_hideComponentMask(){if(this.componentMask)this.componentMask.clear();this.disableKeyboardEvents(false,true)}
,isc.A.setRect=function isc_Canvas_setRect(_1,_2,_3,_4,_5){if(isc.$cv)arguments.$cw=this;if(isc.isAn.Array(_1)){_2=_1[1];_3=_1[2];_4=_1[3];_1=_1[0]}else if(_1!=null&&_1.top!=null){_2=_1.top;_3=_1.width;_4=_1.height;_1=_1.left}
if(this.logIsDebugEnabled()){this.logDebug("setRect: "+this.echo({left:_1,top:_2,width:_3,height:_4}))}
var _6=this.resizeTo(_3,_4,_5,true);if(_6)this.$ss=true;this.moveTo(_1,_2,_5,true);this.$ss=null;return _6}
,isc.A.getRect=function isc_Canvas_getRect(){return[this.getLeft(),this.getTop(),this.getVisibleWidth(),this.getVisibleHeight()]}
,isc.A.getLeft=function isc_Canvas_getLeft(){var _1=this.getStyleHandle();if(_1==null)return this.left;var _2=(isc.Browser.isIE?_1.pixelLeft:parseInt(_1.left));if(this.vscrollOn&&this.showCustomScrollbars&&this.isRTL()){return _2-this.getScrollbarSize()}
return _2}
,isc.A.getOffsetLeft=function isc_Canvas_getOffsetLeft(){var _1=this.getClipHandle();if(isc.Browser.isMoz&&this.$st())_1=null;if(_1==null){if(this.logIsInfoEnabled()){this.logInfo("getOffsetLeft() called before widget is drawn - unable to calculate offset "+"coordinates.  Returning specified coordinates")}
return this.left}
var _2=isc.Element.getOffsetLeft(_1);if(this.vscrollOn&&this.showCustomScrollbars&&this.isRTL()){_2-=this.getScrollbarSize()}
return _2}
,isc.A.setLeft=function isc_Canvas_setLeft(_1){this.moveTo(_1,null)}
,isc.A.getTop=function isc_Canvas_getTop(){var _1=this.getStyleHandle();if(_1==null)return this.top;var _2=(isc.Browser.isIE?_1.pixelTop:parseInt(_1.top));return _2}
,isc.A.getOffsetTop=function isc_Canvas_getOffsetTop(){var _1=this.getClipHandle();if(isc.Browser.isMoz&&this.$st())_1=null;if(_1==null)return this.top;var _2=isc.Element.getOffsetTop(_1);return _2}
,isc.A.setTop=function isc_Canvas_setTop(_1){this.moveTo(null,_1)}
,isc.A.getWidth=function isc_Canvas_getWidth(){return this.width}
,isc.A.setWidth=function isc_Canvas_setWidth(_1){this.resizeTo(_1)}
,isc.A.getHeight=function isc_Canvas_getHeight(){return this.$o8}
,isc.A.setHeight=function isc_Canvas_setHeight(_1){this.resizeTo(null,_1)}
,isc.A.getMinWidth=function isc_Canvas_getMinWidth(){return this.minWidth}
,isc.A.getMinHeight=function isc_Canvas_getMinHeight(){return this.minHeight}
,isc.A.getMaxWidth=function isc_Canvas_getMaxWidth(){return this.maxWidth}
,isc.A.getMaxHeight=function isc_Canvas_getMaxHeight(){return this.maxHeight}
,isc.A.getRight=function isc_Canvas_getRight(){return this.getLeft()+this.getVisibleWidth()}
,isc.A.setRight=function isc_Canvas_setRight(_1){if(isc.isA.Number(_1)){this.resizeTo(_1-this.getLeft(),null)}else{this.logWarn("setRight() expects an integer value")}}
,isc.A.getBottom=function isc_Canvas_getBottom(){return this.getTop()+this.getVisibleHeight()}
,isc.A.setBottom=function isc_Canvas_setBottom(_1){if(isc.isA.Number(_1)){this.resizeTo(null,_1-this.getTop())}else{this.logWarn("setBottom() expects an integer value")}}
,isc.A.enforceScrollSize=function isc_Canvas_enforceScrollSize(_1,_2){if(this.logIsDebugEnabled(this.$ox)){this.logDebug("enforcing scroll size:"+[_1,_2],"enforceScrollSize")}
if(!this.$pu&&!this.$if)return;if(_1==null)_1=0;if(_2==null)_2=0;if(isNaN(_1)||isNaN(_2)||_1<0||_2<0){this.logWarn("Invalid width or height in Canvas.enforceScrollSize()"+" on component: "+this.getID()+" with sizes: "+[_1,_2]+this.getStackTrace());return}
if(this.$re==null){var _3=this.$ov;var _4=this.$qs(this.$ow);_3[1]=_4;_3[3]=_1-1;_3[5]=_2-1;var _5=_3.join(isc.emptyString);this.$re=isc.Element.insertAdjacentHTML(this.getHandle(),this.$n8,_5,true);if(this.$re==null){this.$re=document.getElementById(_4)}}else if(!this.$qe||this.$qe[0]!=_1||this.$qe[1]!=_2)
{this.$re.style.left=(_1-1)+isc.px;this.$re.style.top=(_2-1)+isc.px}
this.$qe=[_1,_2]}
,isc.A.stopEnforcingScrollSize=function isc_Canvas_stopEnforcingScrollSize(){if(this.logIsDebugEnabled(this.$ox)){this.logDebug("stop enforcing scroll size","enforceScrollSize")}
delete this.$qe;if(!this.isDrawn())return;if(this.$re){this.$re.style.left=this.$oy;this.$re.style.top=this.$oy}}
,isc.A.getScrollWidth=function isc_Canvas_getScrollWidth(_1){if(isc.$cv)arguments.$cw=this;if(this.$qz){this.$qz=null;this.adjustOverflow("widthCheckWhileDeferred")}
if(!_1&&this.$su!=null)return this.$su;var _2=0,_3=this.getClipHandle();if(_3==null){this.logDebug("No size info available from DOM, returning user-specified size");return this.getInnerWidth()}
if(this.allowNativeContentPositioning){this.$sv=true;if(isc.Browser.isSafari||(isc.Browser.isMoz&&((_3.scrollWidth||_3.offsetWidth)<=parseInt(_3.style.width))))
{_2=isc.Element.getScrollWidth(this.getHandle())}else{_2=isc.Element.getScrollWidth(_3)}
delete this.$sv}else{var _4=this.children,_5=_4&&_4.length>0,_6=0;if(!_5||this.allowContentAndChildren){if(isc.Browser.isSafari&&this.overflow==isc.Canvas.VISIBLE){_2=this.getHandle().scrollWidth;if(this.useClipDiv&&this.padding==null){_2+=isc.Element.$sw(this.styleName)}}else{_6=(_3.scrollWidth||_3.offsetWidth);if(_6!=null&&_6!=this.$n1){_2=_6;if(isc.Browser.isOpera){_2-=(this.getLeftBorderSize()+this.getLeftPadding())}
if(isc.Browser.isMoz)_2-=this.$sx();if(isc.Browser.isMoz&&this.getScrollingMechanism()==isc.Canvas.NESTED_DIV)
{var _7=this.getHandle().offsetLeft;if(_7<0)_7=-_7;_2-=_7}}
if(isc.Browser.isSafari||(isc.Browser.isMoz&&_2<=parseInt(_3.style.width)))
{var _8=this.getHandle(),_9=_8.scrollWidth||_8.offsetWidth;if(_9>_2)_2=_9}}}
if(_5){var _10=this.$sy(this.children);_2=Math.max(_10,_2);if(this.$qe!=null){var _11=this.$qe[0];_2=Math.max(_2,_11)}}}
this.$su=_2;return _2}
,isc.A.$sy=function isc_Canvas__getWidthSpan(_1,_2){var _3=0,_4=0,_5=this.overflow==isc.Canvas.VISIBLE||this.overflow==isc.Canvas.CLIP_H,_6;for(var i=0;i<_1.length;i++){var _8=_1[i];if(!_8.isDrawn()&&!_8.$59d)continue;if(_2&&_8.visibility==isc.Canvas.HIDDEN)continue;var _9=(_8.position!=isc.Canvas.RELATIVE),_10=_8.getVisibleWidth(),_11=(_9?_8.getLeft():_8.getOffsetLeft());if(!_5&&_9)_10-=_8.getRightMargin();if(_11+_10>_4){_4=_11+_10;_6=_8}
if(_11<_3)_3=this.isRTL()?_11:Math.max(0,_11)}
return _4-_3}
,isc.A.getScrollHeight=function isc_Canvas_getScrollHeight(_1){if(isc.$cv)arguments.$cw=this;if(this.$qz){this.$qz=null;this.adjustOverflow("heightCheckWhileDeferred")}
if(!_1&&this.$sz!=null)return this.$sz;var _2=0,_3=this.getClipHandle();if(_3==null){this.logDebug("No size info available from DOM, returning user-specified size");return this.getInnerHeight()}
if(this.allowNativeContentPositioning){this.$s0=true;if(isc.Browser.isSafari||(isc.Browser.isMoz&&((_3.scrollHeight||_3.offsetHeight)<=parseInt(_3.style.height))))
{_2=isc.Element.getScrollHeight(this.getHandle())}else{_2=isc.Element.getScrollHeight(_3)}
delete this.$s0}else{var _4=(this.children&&this.children.length>0);if(!_4||this.allowContentAndChildren){if(isc.Browser.isSafari&&this.overflow==isc.Canvas.VISIBLE){_2=this.getHandle().scrollHeight;if(this.useClipDiv&&this.padding==null){_2+=isc.Element.$s1(this.styleName)}}else{var _5=(_3.scrollHeight||_3.offsetHeight);if(_5!=null&&_5!=this.$n1){_2=_5;if(isc.Browser.isMoz)_2-=this.$s2();if(this.useClipDiv&&(isc.Browser.isSafari||(isc.Browser.isMoz&&_2<=parseInt(_3.style.height))))
{var _6=this.getHandle(),_7=_6.scrollHeight||_6.offsetHeight;if(_7>_2)_2=_7}}}}
if(_4){var _8=this.$s3(this.children);if(_8>_2){_2=_8}
if(this.$qe!=null){var _9=this.$qe[1];_2=Math.max(_2,_9)}}}
this.$sz=_2;return _2}
,isc.A.$s2=function isc_Canvas__offscreenChildrenHeight(){if(!isc.isAn.Array(this.children))return 0;var _1=0;for(var i=0;i<this.children.length;i++){var _3=this.children[i],_4=(_3.position==isc.Canvas.ABSOLUTE?_3.getTop():_3.getOffsetTop());if(_4<_1)_1=_4}
return-_1}
);isc.evalBoundary;isc.B.push(isc.A.$sx=function isc_Canvas__offscreenChildrenWidth(){if(!isc.isAn.Array(this.children))return 0;if(!this.useClipDiv)return 0;var _1=0;for(var i=0;i<this.children.length;i++){var _3=this.children[i],_4=(_3.position==isc.Canvas.ABSOLUTE?_3.getLeft():_3.getOffsetLeft());if(_4<_1)_1=_4}
return-_1}
,isc.A.$s3=function isc_Canvas__getHeightSpan(_1,_2){var _3=0,_4=0,_5=this.overflow==isc.Canvas.VISIBLE||this.overflow==isc.Canvas.CLIP_H;for(var i=0;i<_1.length;i++){var _7=_1[i];if(!_7.isDrawn()&&!_7.$59d)continue;if(_2&&_7.visibility==isc.Canvas.HIDDEN)continue;var _8=_7.position!=isc.Canvas.RELATIVE,_9=_7.getVisibleHeight(),_10=(_8?_7.getTop():_7.getOffsetTop());if(!_5&&_8)_9-=_7.getBottomMargin();if(_9+_10>_4)_4=_9+_10;if(_10<_3)_3=Math.max(0,_10)}
return _4-_3}
,isc.A.getScrollLeft=function isc_Canvas_getScrollLeft(){if(!this.isDrawn()||this.getScrollingMechanism()!=isc.Canvas.NATIVE){return this.scrollLeft}
return this.getScrollHandle().scrollLeft}
,isc.A.getScrollTop=function isc_Canvas_getScrollTop(){if(!this.isDrawn()||this.getScrollingMechanism()!=isc.Canvas.NATIVE){return this.scrollTop}
return this.getScrollHandle().scrollTop}
,isc.A.setPageLeft=function isc_Canvas_setPageLeft(_1){this.moveBy(_1-this.getPageLeft(),0)}
,isc.A.setPageTop=function isc_Canvas_setPageTop(_1){this.moveBy(0,_1-this.getPageTop())}
,isc.A.getParentPageRect=function isc_Canvas_getParentPageRect(){if(this.parentElement){var _1=this.parentElement,_2=_1.getPageRect();var _3=_1.getLeftMargin(),_4=_1.getTopMargin();_2[0]+=_3;_2[1]+=_4;_2[2]-=(_3+_1.getRightMargin());_2[3]-=(_4+_1.getBottomMargin());if(this.peers&&this.peers.length>0){var _5=this.getPeerRect(),_6=this.getPageRect();_2[0]+=(_6[0]-_5[0]);_2[1]+=(_6[1]-_5[1]);_2[2]-=(_5[2]-_6[2]);_2[3]-=(_5[3]-_6[3])}
var _7=_1.$tj();_2[0]+=_7.left;_2[1]+=_7.top;_2[2]-=_7.right+_7.left;_2[3]-=_7.bottom+_7.top;var _8=_1.getScrollbarSize();if(_1.vscrollOn)_2[2]-=_8;if(_1.hscrollOn)_2[3]-=_8;return _2}
else return[0,0,isc.Page.getWidth(),isc.Page.getHeight()]}
,isc.A.setPageRect=function isc_Canvas_setPageRect(_1,_2,_3,_4,_5){if(isc.isAn.Array(_1)){_2=_1[1];_3=_1[2];_4=_1[3];_1=_1[0]}
if(this.keepInParentRect&&this.ns.EH.dragging&&this==this.ns.EH.dragMoveTarget){var _6=(_3==null&&_4==null);if(_3==null)_3=this.getVisibleWidth();if(_4==null)_4=this.getVisibleHeight();var _7=_1+_3,_8=_2+_4,_9;var _10=isc.isAn.Array(this.keepInParentRect);if(_10){_9=this.keepInParentRect}else{_9=this.getParentPageRect()}
var _11=_9[0],_12=_9[1],_13=_9[2],_14=_9[3],_15=_11+_13,_16=_12+_14;var _17=this.ns.EH,_18=_17.getDragTarget(_17.getLastEvent()).parentElement;if(_18){var _19=_18.getScrollLeft(),_20=_18.getScrollWidth()-
_18.getViewportWidth()-_19,_21=_18.getScrollTop(),_22=_18.getScrollHeight()-
_18.getViewportHeight()-_21}else{var _19=isc.Page.getScrollLeft(),_20=isc.Page.getScrollWidth()-
isc.Page.getWidth()-_19,_21=isc.Page.getScrollTop(),_22=isc.Page.getScrollHeight()-
isc.Page.getHeight()-_21}
if(_20<0)_20=0;if(_22<0)_22=0;if(_6){if(_1<_11-_19){_1=_11-_19}
else if(_7>_15+_20){_1=_15+_20-_3}
if(_2<_12-_21){_2=_12-_21}
else if(_8>_16+_22){_2=_16+_22-_4}}else{if(_1<_11){_3=_3-(_11-_1);_1=_11}else if(_7>_15){_3=_3-(_7-_15)}
if(_2<_12){_4=_4-(_12-_2);_2=_12}else if(_8>_16){_4=_4-(_8-_16)}}}
this.moveBy(_1-this.getPageLeft(),_2-this.getPageTop());if(_5){var _23=this.getVisibleWidth(),_24=this.getVisibleHeight(),_25=_23-_3,_26=_24-_4;this.resizeTo(_3,_4);this.redrawIfDirty("setPageRect");var _27=(_23-this.getVisibleWidth()),_28=(_24-this.getVisibleHeight());if(_1>this.getPageLeft())_1-=(_25-_27);if(_2>this.getPageTop())_2-=(_26-_28)}else{this.resizeTo(_3,_4)}}
,isc.A.getCanvasLeft=function isc_Canvas_getCanvasLeft(_1){if(_1!=null){if(!_1.contains(this,false)){this.logWarn("getCanvasTop passed ancestor:"+_1+". This is not an ancestor of this component - ignoring");_1=this.parentElement}}else{_1=this.parentElement}
if(!this.isDrawn()||(isc.Browser.isMoz&&this.$st()))
{if(!this.isDrawn()&&this.position==isc.Canvas.RELATIVE){this.logWarn("getCanvasLeft(): Called on undrawn relatively-position widget '"+this.getID()+"'.  The drawn coordinates can not be reliably "+"calculated until the widget has drawn - returning estimated position")}
var _2=this.left,_3=this.parentElement;while(_1!=_3){_2+=_3.left;_3=_3.parentElement}
return _2}
var _4=this.getLeftOffset(_1);return _4}
,isc.A.getPageLeft=function isc_Canvas_getPageLeft(){if(isc.$cv)arguments.$cw=this;var _1=this.getClipHandle();if(_1&&isc.Browser.isMoz&&this.$st())_1=null;if(_1==null){if(!this.isDrawn()&&this.position==isc.Canvas.RELATIVE){this.logWarn("getPageLeft(): Called on undrawn relatively-position widget '"+this.getID()+"'.  The page level coordinates can not be reliably "+"calculated until the widget has drawn - returning estimated position")}
var _2=this.parentElement;if(_2){var _3=0;if(_2.hscrollOn){if(!this.isRTL())_3=_2.getScrollLeft();else{var _4=_2.getScrollWidth()-_2.getViewportWidth();_3=-1*(_4-_2.getScrollLeft())}}
return this.getOffsetLeft()+_2.getLeftBorderSize()+_2.getLeftMargin()+_2.getPageLeft()-_3}else{return this.getOffsetLeft()}}
if(this.useClientRectAPI&&_1.getBoundingClientRect!=null){var _5=_1.getBoundingClientRect().left;_5-=this.getLeftMargin();_5+=isc.Page.getScrollLeft();return _5}
return this.getLeftOffset()}
,isc.A.getLeftOffset=function isc_Canvas_getLeftOffset(_1){var _2=this.ns.Element.getOffset(isc.Canvas.LEFT,this,_1,this.isRTL(),true);return _2}
,isc.A.getCanvasTop=function isc_Canvas_getCanvasTop(_1){if(_1!=null){if(!_1.contains(this,false)){this.logWarn("getCanvasTop passed ancestor:"+_1+". This is not an ancestor of this component - ignoring");_1=this.parentElement}}else{_1=this.parentElement}
if(!this.isDrawn()||(isc.Browser.isMoz&&this.$st()))
{if(!this.isDrawn()&&this.position==isc.Canvas.RELATIVE){this.logWarn("getCanvasTop(): Called on undrawn relatively-position widget '"+this.getID()+"'.  The drawn coordinates can not be reliably "+"calculated until the widget has drawn - returning estimated position")}
var _2=this.top,_3=this.parentElement;while(_1!=_3){_2+=_3.top;_3=_3.parentElement}
return _2}
var _4=this.getTopOffset(_1);return _4}
,isc.A.getPageTop=function isc_Canvas_getPageTop(){var _1=this.getClipHandle();if(_1&&isc.Browser.isMoz&&this.$st())_1=null;if(_1==null){if(!this.isDrawn()&&this.position==isc.Canvas.RELATIVE){this.logWarn("getPageTop(): Called on undrawn relatively-positioned widget '"+this.getID()+"'.  The page level coordinates can not be reliably "+"calculated until the widget has drawn - returning estimated position")}
var _2=this.parentElement;if(_2){return this.getOffsetTop()+_2.getTopBorderSize()+_2.getTopMargin()+_2.getPageTop()-_2.getScrollTop()}else{return this.getOffsetTop()}}
if(this.useClientRectAPI&&_1.getBoundingClientRect!=null){var _3=_1.getBoundingClientRect().top;_3-=this.getTopMargin();_3+=isc.Page.getScrollTop();return _3}
return this.getTopOffset()}
,isc.A.getTopOffset=function isc_Canvas_getTopOffset(_1){var _2=this.ns.Element.getOffset(isc.Canvas.TOP,this,_1,null,true);return _2}
,isc.A.getPageRight=function isc_Canvas_getPageRight(){return this.getPageLeft()+this.getVisibleWidth()}
,isc.A.getPageBottom=function isc_Canvas_getPageBottom(){return this.getPageTop()+this.getVisibleHeight()}
,isc.A.getPageRect=function isc_Canvas_getPageRect(){return[this.getPageLeft(),this.getPageTop(),this.getVisibleWidth(),this.getVisibleHeight()]}
,isc.A.usingCSSScrollbars=function isc_Canvas_usingCSSScrollbars(){return!this.showCustomScrollbars&&(this.overflow==isc.Canvas.AUTO||this.overflow==isc.Canvas.SCROLL)}
,isc.A.getScrollingMechanism=function isc_Canvas_getScrollingMechanism(){if(!this.$s6){if(!this.showCustomScrollbars&&(this.overflow==isc.Canvas.AUTO||this.overflow==isc.Canvas.SCROLL))
{this.$s6=isc.Canvas.NATIVE}else{if((isc.Browser.isSafari&&isc.Browser.SafariVersion<125)||(isc.Browser.isMoz&&isc.Browser.isUnix&&isc.Browser.geckoVersion<=20031007))
{this.$s6=isc.Canvas.NESTED_DIV}else{this.$s6=isc.Canvas.NATIVE}}}
return this.$s6}
,isc.A.setMargin=function isc_Canvas_setMargin(_1){this.$s7=null;this.$s8=null;if(_1==null){delete this.margin}else{var _2=_1;if(isc.isA.String(_1))_1=parseInt(_1);if(!isc.isA.Number(_1)){this.logWarn("setMargin() passed invalid margin:"+_2+", ignoring.");return}
this.margin=_1}
var _3=this.getStyleHandle();if(!_3)return;this.$883();this.adjustOverflow("setMargin");this.innerSizeChanged("Margin thickness changed")}
,isc.A.$883=function isc_Canvas__applyFullMargins(){var _1=this.getClipHandle();if(!_1)return;if(!this.$sb()&&this.$sc==null){_1.style.marginTop="";_1.style.marginBottom="";_1.style.marginLeft="";_1.style.marginRight="";if(this.margin==null)_1.style.margin=0;else _1.style.margin=this.margin+isc.px;return}
var _2=this.$sd();_1.style.marginTop=_2.top+isc.px;_1.style.marginLeft=_2.left+isc.px;_1.style.marginBottom=_2.bottom+isc.px;_1.style.marginRight=_2.right+isc.px}
,isc.A.getMargin=function isc_Canvas_getMargin(){return this.margin}
,isc.A.getTopMargin=function isc_Canvas_getTopMargin(){return this.$sd().top}
,isc.A.getLeftMargin=function isc_Canvas_getLeftMargin(){return this.$sd().left}
,isc.A.getBottomMargin=function isc_Canvas_getBottomMargin(){return this.$sd().bottom}
,isc.A.getRightMargin=function isc_Canvas_getRightMargin(){return this.$sd().right}
,isc.A.$88x=function isc_Canvas__removeDestroyedPeers(_1,_2){var _3=[];for(var i=0;i<_1.length;i++){if(_1[i].destroyed){_3[_3.length]={peer:_1[i],side:_2};_1[i]=null}}
_1.removeEmpty();return _3}
,isc.A.$sd=function isc_Canvas__calculateMargins(){var _1=this.$sc,_2=(_1!=null),_3,_4,_5,_6;if(_2){_3=_1.top;_6=_1.bottom;_4=_1.left;_5=_1.right;var _7=[];if(_3!=null)_7.addList(this.$88x(_3,"top"));if(_6!=null)_7.addList(this.$88x(_6,"bottom"));if(_4!=null)_7.addList(this.$88x(_4,"left"));if(_5!=null)_5.addList(this.$88x(_5,"right"));if(_7.length>0){for(var i=0;i<_7.length;i++){this.$wj(_7[i].peer,_7[i].side)}}
if((_3==null||_3.length==0)&&(_6==null||_6.length==0)&&(_4==null||_4.length==0)&&(_5==null||_5.length==0))_2=false}
if(!this.$sb()&&!_2)return this.$s9();var _9=this.$s8;if(_9)return _9;var _10=this.$ta();_9={left:_10.left,right:_10.right,top:_10.top,bottom:_10.bottom};if(_2){if(_3){for(var i=0;i<_3.length;i++){var _11=_3[i];_9.top+=_11.getVisibleHeight();if(_11.$55s!=null){_9.top-=_11.$55s}}}
if(_6){for(var i=0;i<_6.length;i++){var _12=_6[i];_9.bottom+=_12.getVisibleHeight();if(_12.$55s!=null){_9.bottom-=_12.$55s}}}
if(_4){for(var i=0;i<_4.length;i++){var _13=_4[i];_9.left+=_13.getVisibleWidth();if(_13.$55s!=null){_9.left-=_13.$55s}}}
if(_5){for(var i=0;i<_5.length;i++){var _14=_5[i];_9.right+=_14.getVisibleWidth();if(_14.$55s!=null){_9.right-=_14.$55s}}}}
if(this.$sb()){var _15=this.$pq();_9.left+=_15.$tb,_9.right+=_15.$tc,_9.top+=_15.$td,_9.bottom+=_15.$te}
return(this.$s8=_9)}
,isc.A.$ta=function isc_Canvas__getSpecifiedMargins(){var _1=this.$if;this.$if=false;var _2=this.$s9();this.$if=_1;return _2}
,isc.A.$s9=function isc_Canvas__calculateNormalMargins(){if(this.$s7!=null)return this.$s7;var _1={},_2=isc.px;if(!this.isDrawn()){var _3=this.margin;if(isc.isA.String(_3)){if(isc.endsWith(_3,_2)||parseInt(_3)+isc.emptyString==_3)
_3=parseInt(_3)}
if(isc.isA.Number(_3)){_1.top=_3;_1.bottom=_3;_1.left=_3;_1.right=_3;this.$s7=_1;return _1}}else{var _4=this.getStyleHandle(),_5=_4.marginLeft,_6=_4.marginRight,_7=_4.marginTop,_8=_4.marginBottom;if(isc.isA.String(_5)&&isc.endsWith(_5,_2))
_5=parseInt(_5);if(isc.isA.String(_6)&&isc.endsWith(_6,_2))
_6=parseInt(_6)
if(isc.isA.String(_7)&&isc.endsWith(_7,_2))
_7=parseInt(_7);if(isc.isA.String(_8)&&isc.endsWith(_8,_2))
_8=parseInt(_8)
if(isc.isA.Number(_5))_1.left=_5;if(isc.isA.Number(_6))_1.right=_6;if(isc.isA.Number(_7))_1.top=_7;if(isc.isA.Number(_8))_1.bottom=_8}
if(this.className){if(!isc.isA.Number(_1.left))
_1.left=isc.Element.$tf(this.className);if(!isc.isA.Number(_1.right))
_1.right=isc.Element.$tg(this.className);if(!isc.isA.Number(_1.top))
_1.top=isc.Element.$th(this.className);if(!isc.isA.Number(_1.bottom))
_1.bottom=isc.Element.$ti(this.className)}else{if(!isc.isA.Number(_1.left))
_1.left=0;if(!isc.isA.Number(_1.right))
_1.right=0;if(!isc.isA.Number(_1.top))
_1.top=0;if(!isc.isA.Number(_1.bottom))
_1.bottom=0}
return(this.$s7=_1)}
,isc.A.getTopBorderSize=function isc_Canvas_getTopBorderSize(){return this.$tj().top}
,isc.A.getBottomBorderSize=function isc_Canvas_getBottomBorderSize(){return this.$tj().bottom}
,isc.A.getLeftBorderSize=function isc_Canvas_getLeftBorderSize(){return this.$tj().left}
,isc.A.getRightBorderSize=function isc_Canvas_getRightBorderSize(){return this.$tj().right}
,isc.A.getHBorderSize=function isc_Canvas_getHBorderSize(){return(this.getLeftBorderSize()+this.getRightBorderSize())}
,isc.A.getVBorderSize=function isc_Canvas_getVBorderSize(){return this.getTopBorderSize()+this.getBottomBorderSize()}
,isc.A.$tj=function isc_Canvas__calculateBorderSize(){if(this.$tk!=null)return this.$tk;var _1={},_2=isc.px;if(!this.isDrawn()){var _3=this.border;if(_3!=null&&isc.contains(_3,_2)){var _4=_3.match(/\s*\d+px/g);if(isc.isAn.Array(_4))_4=parseInt(_4[0]);else _4=parseInt(_4);if(isc.isA.Number(_4)){this.$tk={left:_4,right:_4,top:_4,bottom:_4}
return this.$tk}}}else{var _5=this.getStyleHandle(),_6=_5.borderLeftWidth,_7=_5.borderRightWidth,_8=_5.borderTopWidth,_9=_5.borderBottomWidth;if(isc.isA.String(_6)&&isc.endsWith(_6,_2))
_6=parseInt(_6);if(isc.isA.String(_7)&&isc.endsWith(_7,_2))
_7=parseInt(_7)
if(isc.isA.String(_8)&&isc.endsWith(_8,_2))
_8=parseInt(_8);if(isc.isA.String(_9)&&isc.endsWith(_9,_2))
_9=parseInt(_9)
if(isc.isA.Number(_6))_1.left=_6;if(isc.isA.Number(_7))_1.right=_7;if(isc.isA.Number(_8))_1.top=_8;if(isc.isA.Number(_9))_1.bottom=_9}
if(this.className){if(!isc.isA.Number(_1.left))
_1.left=isc.Element.$tl(this.className);if(!isc.isA.Number(_1.right))
_1.right=isc.Element.$tm(this.className);if(!isc.isA.Number(_1.top))
_1.top=isc.Element.$tn(this.className);if(!isc.isA.Number(_1.bottom))
_1.bottom=isc.Element.$to(this.className)}else{if(!isc.isA.Number(_1.left))
_1.left=0;if(!isc.isA.Number(_1.right))
_1.right=0;if(!isc.isA.Number(_1.top))
_1.top=0;if(!isc.isA.Number(_1.bottom))
_1.bottom=0}
return(this.$tk=_1)}
,isc.A.setTopPadding=function isc_Canvas_setTopPadding(_1){this.$tp=null;this.topPadding=_1;if(isc.isA.Number(_1))_1+="px";if(this.isDrawn())this.getHandle().paddingTop=_1}
,isc.A.setLeftPadding=function isc_Canvas_setLeftPadding(_1){this.$tp=null;this.leftPadding=_1;if(isc.isA.Number(_1))_1+="px";if(this.isDrawn())this.getHandle().paddingLeft=_1}
,isc.A.setRightPadding=function isc_Canvas_setRightPadding(_1){this.$tp=null;this.rightPadding=_1;if(isc.isA.Number(_1))_1+="px";if(this.isDrawn())this.getHandle().paddingRight=_1}
,isc.A.setBottomPadding=function isc_Canvas_setBottomPadding(_1){this.$tp=null;this.bottomPadding=_1;if(isc.isA.Number(_1))_1+="px";if(this.isDrawn())this.getHandle().paddingBottom=_1}
,isc.A.setPadding=function isc_Canvas_setPadding(_1){this.$tp=null;if(_1!=null){var _2=_1;if(isc.isA.String(_1))_1=parseInt(_1);if(!isc.isA.Number(_1)){this.logWarn("setPadding passed unrecognized value:"+_2+" - ignoring");return}}
this.padding=_1;var _3=isc.Browser.isDOM?this.getHandle():null;if(!_3){return}
if(_1==null){_3.style.padding=null;if(this.useClipDiv)this.getClipHandle().style.padding=null}else{_3.style.padding=this.padding+isc.px;if(this.useClipDiv)this.getClipHandle().style.padding=this.$65h}}
,isc.A.getPadding=function isc_Canvas_getPadding(){return this.padding}
,isc.A.getTopPadding=function isc_Canvas_getTopPadding(){return this.$tq().top}
,isc.A.getBottomPadding=function isc_Canvas_getBottomPadding(){return this.$tq().bottom}
,isc.A.getLeftPadding=function isc_Canvas_getLeftPadding(){return this.$tq().left}
,isc.A.getRightPadding=function isc_Canvas_getRightPadding(){return this.$tq().right}
,isc.A.getVPadding=function isc_Canvas_getVPadding(){return this.getTopPadding()+this.getBottomPadding()}
,isc.A.getHPadding=function isc_Canvas_getHPadding(){return this.getLeftPadding()+this.getRightPadding()}
,isc.A.$tq=function isc_Canvas__calculatePadding(){if(this.$tp!=null)return this.$tp;var _1={},_2=isc.px;if(this.isDrawn()&&this.getHandle()!=null){var _3=this.getHandle().style;if(_3.paddingTop!=null&&!isc.isAn.emptyString(_3.paddingTop)&&isc.endsWith(_3.paddingTop,_2)){_1.top=parseInt(_3.paddingTop)}
if(_3.paddingBottom!=null&&!isc.isAn.emptyString(_3.paddingBottom)&&isc.endsWith(_3.paddingBottom,_2)){_1.bottom=parseInt(_3.paddingBottom)}
if(_3.paddingLeft!=null&&!isc.isAn.emptyString(_3.paddingLeft)&&isc.endsWith(_3.paddingLeft,_2)){_1.left=parseInt(_3.paddingLeft)}
if(_3.paddingRight!=null&&!isc.isAn.emptyString(_3.paddingRight)&&isc.endsWith(_3.paddingRight,_2)){_1.right=parseInt(_3.paddingRight)}}else{if(this.topPadding!=null)_1.top=this.topPadding;if(this.leftPadding!=null)_1.left=this.leftPadding;if(this.rightPadding!=null)_1.right=this.rightPadding;if(this.bottomPadding!=null)_1.bottom=this.bottomPadding;if(this.padding!=null){var _4=parseInt(this.padding);if(_1.left==null)_1.left=_4;if(_1.top==null)_1.top=_4;if(_1.bottom==null)_1.bottom=_4;if(_1.right==null)_1.right=_4}}
if(this.className){if(!isc.isA.Number(_1.left))_1.left=isc.Element.$tr(this.className);if(!isc.isA.Number(_1.right))_1.right=isc.Element.$ts(this.className);if(!isc.isA.Number(_1.top))_1.top=isc.Element.$tt(this.className);if(!isc.isA.Number(_1.bottom))_1.bottom=isc.Element.$tu(this.className)}else{if(!isc.isA.Number(_1.left))_1.left=0;if(!isc.isA.Number(_1.right))_1.right=0;if(!isc.isA.Number(_1.top))_1.top=0;if(!isc.isA.Number(_1.bottom))_1.bottom=0}
return(this.$tp=_1)}
,isc.A.containsPoint=function isc_Canvas_containsPoint(_1,_2,_3){if(isc.$cv)arguments.$cw=this;if(!this.isVisible()||!this.isDrawn())return false;if(_3==null)_3=false;var _4=this.getPageLeft()+this.getLeftMargin();if(_1<_4)return false;var _5=this.getPageTop()+this.getTopMargin();if(_2<_5)return false;var _6=_3?this.getViewportWidth():(this.getVisibleWidth()-this.getHMarginSize());if(_1>_4+_6)return false;var _7=_3?this.getViewportHeight():(this.getVisibleHeight()-this.getVMarginSize());if(_2>_5+_7)return false;var _8=0,_9=0;var _10=this.$tv=this.$tv||[];_10.length=1;_10[0]=this;var i=1,_12=this;while(_12.parentElement!=null){_12=_12.parentElement
_10[i]=_12;i++}
var _13,_14;for(var j=_10.length-1;j>=0;j--){var _16=_10[j];_8+=_16.getCanvasLeft();_9+=_16.getCanvasTop();if(j+1<_10.length){var _17=_10[j+1];_8-=_17.getScrollLeft();_9-=_17.getScrollTop()}
_8+=_16.getLeftMargin();_9+=_16.getTopMargin();if(_16==this&&!_3){_13=_16.getVisibleWidth()-_16.getHMarginSize();_14=_16.getVisibleHeight()-_16.getVMarginSize()}else{_8+=_16.getLeftBorderSize();_9+=_16.getTopBorderSize();_13=_16.getViewportWidth();_14=_16.getViewportHeight()}
if(!((_1>=_8)&&(_1<=_8+_13)&&(_2>=_9)&&(_2<=_9+_14)))
{return false}}
return true}
,isc.A.visibleAtPoint=function isc_Canvas_visibleAtPoint(_1,_2,_3,_4,_5){if(isc.$cv)arguments.$cw=this;if(!this.containsPoint(_1,_2,_3)){return false}
if(!isc.isAn.Array(_4))_4=[_4];var _6=this;while(_6!=null&&_6!=_5){var _7=(_6.parentElement!=null?_6.parentElement.children:isc.Canvas.$tw);for(var i=0;i<_7.length;i++){var _9=_7[i];if(_9==null||_9==_6||!_9.isDrawn()||!_9.isVisible()||_4.contains(_9)||_9.isMouseTransparent||(_9.getZIndex()<_6.getZIndex()))
{continue}
if(_9.$jr)continue;if(isc.isA.Scrollbar(_9)||isc.isA.ScrollThumb(_9))continue;if(isc.EdgedCanvas&&isc.isA.EdgedCanvas(_9)&&_9.masterElement&&_9.masterElement.$l0==_9)continue;if(isc.Layout&&isc.isA.Layout(_9.parentElement)&&_9.parentElement.hasMember(_9)&&_9.parentElement.hasMember(_6))
{continue}
if(isc.TabSet&&isc.isA.TabBar(_9)&&isc.isA.TabSet(_9.parentElement)&&_9.parentElement.paneContainer&&_9.parentElement.paneContainer.contains(this))
{continue}
if(_9.containsPoint(_1,_2,false)){return false}}
_6=_6.parentElement}
return true}
,isc.A.scrollIntoView=function isc_Canvas_scrollIntoView(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10){if(_3==null)_3=0;if(_4==null)_4=0;if(_10==null){_10=this}
var _11=true;var _12,_13;if(this.overflow!=isc.Canvas.VISIBLE&&this.overflow!=isc.Canvas.IGNORE){if(_1!=null){var _14=this.getScrollLeft(),_15=this.getViewportWidth(),_16=_14+_15,_17=false,_18=false;if(_1+_3>_16){_17=true}
if(_1<_14){_18=true}
if(_17!=_18||_9){if(_5==this.$oz){_12=_1}else if(_5==this.$o1){_12=(_1+_3)-this.getViewportWidth()}else{_12=(_1+parseInt(_3/ 2))
-parseInt(this.getViewportWidth()/2)}}}
if(_2!=null){var _19=this.getScrollTop(),_20=_19+this.getViewportHeight(),_21=false,_22=false;if(_2+_4>_20)_22=true;if(_2<_19)_21=true;if(_21!=_22||_9){if(_6==this.$o0){_13=_2}else if(_6==this.$o2){_13=(_2+_4)-this.getViewportHeight()}else{_13=(_2+parseInt(_4/ 2))
-parseInt(this.getViewportHeight()/2)}}}
if(_12!=null||_13!=null){if(_7){this.animateScroll(_12,_13,_8);_11=false}else{this.scrollTo(_12,_13,"scrollIntoView")}}}
if(this.parentElement!=null){var _23=_1,_24=_2;if(_23!=null){_23-=(_12!=null?_12:this.getScrollLeft());_23+=this.getOffsetLeft()}
if(_24!=null){_24-=(_13!=null?_13:this.getScrollTop());_24+=this.getOffsetTop()}
this.parentElement.scrollIntoView(_23,_24,_3,_4,null,null,null,null,null,_10)}
if(_8&&_11)this.fireCallback(_8)}
,isc.A.intersects=function isc_Canvas_intersects(_1){var _2=_1.getPageLeft(),_3=_1.getVisibleWidth(),_4=_1.getPageTop(),_5=_1.getVisibleHeight();return this.intersectsRect(_2,_4,_3,_5)}
,isc.A.intersectsRect=function isc_Canvas_intersectsRect(_1,_2,_3,_4){var _5,_6=[];if(isc.isAn.Array(_1))_5=_1;else _5=[_1,_2,_3,_4];return isc.Canvas.rectsIntersect(_5,[this.getPageLeft(),this.getPageTop(),this.getVisibleWidth(),this.getVisibleHeight()])}
,isc.A.containsEvent=function isc_Canvas_containsEvent(){return this.containsPoint(this.ns.EH.getX(),this.ns.EH.getY())}
,isc.A.getEventEdge=function isc_Canvas_getEventEdge(_1){var _2=this.ns.EH;if(!_1)_1=(this.resizeFrom||_2.ALL_EDGES);var _3=this.edgeMarginSize;if(!isc.isAn.Array(_1))_1=[_1];var _4=this.$ta(),_5=_4.left,_6=_4.right,_7=_4.top,_8=_4.bottom;var _9=this.getPageLeft()+_5,_10=this.getPageTop()+_7,_11=(this.getPageRight()-_6)+1,_12=(this.getPageBottom()-_8)+1,y=_2.getY(),x=_2.getX(),_15="",_16="";if(y<_10||y>_12||x<_9||x>_11)return null;if(y>=(_12-_3)&&y<=_12)_16="B";else if(y>=_10&&y<=(_10+_3+1))_16="T";if(x>=(_11-_3)&&x<=_11)_15="R";else if(x>=_9&&x<=(_9+_3+1))_15="L";if(_15!=""||_16!=""){var _17=_16+_15;if(_1.contains(_17))return _17;else if(_15!=""&&_1.contains(_15))return _15;else if(_16!=""&&_1.contains(_16))return _16}
return null}
,isc.A.getOffsetX=function isc_Canvas_getOffsetX(){var _1=this.ns.EH.getX()
-(this.getPageLeft()+this.getLeftBorderSize())+this.getScrollLeft()
-(this.vscrollOn&&this.isRTL()?this.getScrollbarSize():0);return _1}
,isc.A.getOffsetY=function isc_Canvas_getOffsetY(){return this.ns.EH.getY()+this.getScrollTop()
-(this.getPageTop()+this.getTopBorderSize())}
,isc.A.setClip=function isc_Canvas_setClip(_1,_2,_3,_4){if(isc.isAn.Array(_1))
this.$q6=_1;else
this.$q6=[_1,_2,_3,_4];var _5=this.getClipHandle();if(_5!=null){var _6=this.$q6;_5.style.clip="rect("+_6.join("px ")+"px)"}}
,isc.A.getScrollbarSize=function isc_Canvas_getScrollbarSize(){if(this.showCustomScrollbars)return this.getCustomScrollbarSize();return isc.Element.getNativeScrollbarSize()}
,isc.A.getViewportWidth=function isc_Canvas_getViewportWidth(){return this.getVisibleWidth()-
(this.vscrollOn?this.getScrollbarSize():0)-
this.getHMarginBorder()}
,isc.A.getViewportHeight=function isc_Canvas_getViewportHeight(){return this.getVisibleHeight()-
(this.hscrollOn?this.getScrollbarSize():0)-
this.getVMarginBorder()}
,isc.A.getOuterViewportWidth=function isc_Canvas_getOuterViewportWidth(){return this.getVisibleWidth()-(this.vscrollOn?this.getScrollbarSize():0)-
this.getHMarginSize()}
,isc.A.getOuterViewportHeight=function isc_Canvas_getOuterViewportHeight(){return this.getVisibleHeight()-(this.hscrollOn?this.getScrollbarSize():0)-
this.getVMarginSize()}
,isc.A.getInnerHeight=function isc_Canvas_getInnerHeight(){return this.getHeight()
-((this.hscrollOn||this.overflow==isc.Canvas.SCROLL)?this.getScrollbarSize():0)
-this.getVMarginBorder()}
,isc.A.getInnerWidth=function isc_Canvas_getInnerWidth(){var _1=this.getWidth();if(this.vscrollOn||this.overflow==isc.Canvas.SCROLL||this.alwaysShowVScrollbar)
_1-=this.getScrollbarSize();return _1-this.getHMarginBorder()}
,isc.A.getInnerContentHeight=function isc_Canvas_getInnerContentHeight(){return this.getHeight()
-(this.hscrollOn||this.overflow==isc.Canvas.SCROLL?this.getScrollbarSize():0)
-this.getVMarginBorderPad()}
,isc.A.getInnerContentWidth=function isc_Canvas_getInnerContentWidth(){var _1=this.getWidth();if(this.vscrollOn||this.overflow==isc.Canvas.SCROLL||this.alwaysShowVScrollbar)
_1-=this.getScrollbarSize();return _1-this.getHMarginBorderPad()}
,isc.A.getVBorderPad=function isc_Canvas_getVBorderPad(){return this.getVBorderSize()+this.getVPadding()}
,isc.A.getHBorderPad=function isc_Canvas_getHBorderPad(){return this.getHBorderSize()+this.getHPadding()}
,isc.A.getHMarginSize=function isc_Canvas_getHMarginSize(){return this.getLeftMargin()+this.getRightMargin()}
,isc.A.getVMarginSize=function isc_Canvas_getVMarginSize(){return this.getTopMargin()+this.getBottomMargin()}
,isc.A.getVMarginBorder=function isc_Canvas_getVMarginBorder(){var _1=this.$sd(),_2=this.$tj();return _1.top+_1.bottom+_2.top+_2.bottom}
,isc.A.getHMarginBorder=function isc_Canvas_getHMarginBorder(){var _1=this.$sd(),_2=this.$tj();return _1.left+_1.right+_2.left+_2.right}
,isc.A.getVMarginBorderPad=function isc_Canvas_getVMarginBorderPad(){return this.getVMarginSize()+this.getVBorderPad()}
,isc.A.getHMarginBorderPad=function isc_Canvas_getHMarginBorderPad(){return this.getHMarginSize()+this.getHBorderPad()}
,isc.A.getClipWidth=function isc_Canvas_getClipWidth(){return this.getVisibleWidth()}
,isc.A.getClipHeight=function isc_Canvas_getClipHeight(){return this.getVisibleHeight()}
,isc.A.getVisibleWidth=function isc_Canvas_getVisibleWidth(_1){if((this.$if||this.$pu)&&(this.overflow==isc.Canvas.VISIBLE||this.overflow==isc.Canvas.CLIP_V)){return Math.max(this.width,(this.getScrollWidth(_1)+this.getHMarginBorder()))}else{var _2=this.isAnimating(this.$do)?this.$showAnimationInfo:this.isAnimating(this.$zb)?this.$hideAnimationInfo:null;if(_2!=null&&!_2.$1l&&this.vscrollOn){var _3=0;if(this.vscrollbar.visibility==isc.Canvas.HIDDEN){_3=this.getScrollbarSize()}else{_3=this.getScrollbarSize()-this.getScrollbarSize()}
return Math.max(this.getWidth()-_3,1)}
return this.getWidth()}}
,isc.A.getVisibleHeight=function isc_Canvas_getVisibleHeight(_1){if((this.$if||this.$pu)&&(this.overflow==isc.Canvas.VISIBLE||this.overflow==isc.Canvas.CLIP_H))
{return Math.max(this.getHeight(),(this.getScrollHeight(_1)+this.getVMarginBorder()))}else{if(this.isAnimating()){var _2=this.isAnimating(this.$do)?this.$showAnimationInfo:this.isAnimating(this.$zb)?this.$hideAnimationInfo:null;if(_2!=null&&_2.$1l&&this.hscrollOn){var _3=0;if(this.hscrollbar&&this.hscrollbar.visibility==isc.Canvas.HIDDEN){_3=this.getScrollbarSize()}else{_3=this.getScrollbarSize()-this.getScrollbarSize()}
return Math.max(this.getHeight()-_3,1)}}
return this.getHeight()}}
,isc.A.getPeerRect=function isc_Canvas_getPeerRect(){var _1=this.getPageRect();if(this.peers==null)return _1;for(var i=0;i<this.peers.length;i++){var _3=this.peers[i];if(!_3.isDrawn()||(this.isVisible()&&!_3.isVisible()))continue;if((!this.vscrollOn&&_3==this.vscrollbar)||(!this.hscrollOn&&_3==this.hscrollbar))continue;var _4=_3.getPageRect();if(_4[0]<_1[0])_1[0]=_4[0];if(_4[1]<_1[1])_1[1]=_4[1];var _5=_4[0]+_4[2];if(_5>_1[0]+_1[2])_1[2]=_5-_1[0];var _6=_4[1]+_4[3];if(_6>_1[1]+_1[3])_1[3]=_6-_1[1]}
return _1}
,isc.A.moveBy=function isc_Canvas_moveBy(_1,_2,_3,_4){var _5=_3&&_4;if(!_5&&this.rectAnimation)this.finishAnimation("rect");else if(!_3&&this.moveAnimation)this.finishAnimation("move");if(isc.$cv)arguments.$cw=this;if(isc.isA.Number(_1))
this.left+=_1;else
_1=0;if(isc.isA.Number(_2))
this.top+=_2;else
_2=0;var _6=(_1!=0||_2!=0);if(!_6&&!_4)return false;this.$tx=_1;this.$ty=_2;var _7=(_4&&this.$tz?this.width:null),_8=(_4&&this.$t0?this.$o8:null);this.$q5(this.left,this.top,_7,_8);if(_4)this.$t1();this.$t2();return _6}
,isc.A.$t2=function isc_Canvas__completeMoveBy(){var _1=(this.$tx||0),_2=(this.$ty||0),_3;this.$tx=_3;this.$ty=_3;if(!_1&&!_2)return;this.$t3(this,_1,_2);this.$t4(_1,_2);if(this.parentElement)this.parentElement.childMoved(this,_1,_2);if(this.masterElement)this.masterElement.peerMoved(this,_1,_2);if(this._useFocusProxy&&this.$qj){var _4=this.$qq();if(_4!=null){var _5=parseInt(_4.style.left)+_1,_6=parseInt(_4.style.top)+_2;_4.style.left=_5+"px";_4.style.top=_6+"px"}}
this.$808=this.$809=null;this.handleMoved(_1,_2)}
,isc.A.handleMoved=function isc_Canvas_handleMoved(_1,_2){if(!this.$832&&this.isDrawn()&&this.parentElement==null&&!isc.Page.pollPageSize)
{isc.EH.fireOnPause("checkForBodyOverflowChange",{target:isc.Canvas,methodName:"checkForPageResize"},100)}
this.moved(_1,_2)}
,isc.A.moved=function(deltaX,deltaY){}
,isc.A.parentMoved=function isc_Canvas_parentMoved(_1,_2,_3){}
,isc.A.handleParentMoved=function isc_Canvas_handleParentMoved(_1,_2,_3){this.$808=this.$809=null;this.parentMoved(_1,_2,_3);this.$t3(_1,_2,_3)}
,isc.A.$t3=function isc_Canvas__fireParentMoved(_1,_2,_3){var _4=this.children;if(_4!=null){for(var i=0;i<_4.length;i++){if(isc.isA.Canvas(_4[i])){_4[i].handleParentMoved(_1,_2,_3)}}}}
,isc.A.childMoved=function isc_Canvas_childMoved(_1,_2,_3){if(_1&&_1.masterElement!=null&&_1.containedPeer==true)return;if(this.allowContentAndChildren&&this.overflow==isc.Canvas.VISIBLE)
this.$t5=true;this.$t6(this.$o4)}
,isc.A.$t4=function isc_Canvas__fireMasterMoved(_1,_2){var _3=this.peers;if(_3==null)return;for(var i=0;i<_3.length;i++){if(_3[i])_3[i].masterMoved(_1,_2)}}
,isc.A.masterMoved=function isc_Canvas_masterMoved(_1,_2){if(this.$ns)this.moveBy(_1,_2)}
,isc.A.peerMoved=function isc_Canvas_peerMoved(_1,_2,_3){}
,isc.A.dragRepositioned=function isc_Canvas_dragRepositioned(){}
,isc.A.getDelta=function isc_Canvas_getDelta(_1,_2,_3){if(_2==null)return null;var _4=_1,_5=this.$pb[_1];if(_1==this.$o5)_4=this.$o7;if(isc.isA.Number(_2)){var _6=Math.round(_2);if(_6!=_2){this.logWarn(_1+" specified as fractional coordinate:"+_2+". Rounded to:"+_6);_2=_6}}else if(isc.isA.String(_2)&&isc.endsWith(_2,this.$o9)){this[_5]=_2;if(this.masterElement==null&&this.parentElement==null&&this.$rq==null){this.$rq=isc.Page.setEvent(this.$nx,this,isc.Page.FIRE_ONCE)}
if(this.$pp){_3=this[_4]=0;if(this.percentBox=="custom")this[_4]=1}
if(this.percentBox=="custom")return 0;var _7,_8,_9,_10=(_1==this.$oz||_1==this.$o6);if(this.percentSource||(this.snapTo&&this.masterElement)){_7=this.percentSource||this.masterElement;_9=(this.percentBox==this.$520),_8=_10?(_9?_7.getViewportWidth():_7.getVisibleWidth()):(_9?_7.getViewportHeight():_7.getVisibleHeight())}else{_7=this.parentElement;_8=(_10?(_7?_7.getInnerWidth():isc.Page.getWidth()):(_7?_7.getInnerHeight():isc.Page.getHeight()))}
if(isc.Browser.isIE&&!isc.Page.isLoaded()&&((isc.Page.getWidth()==0)||(isc.Page.getHeight()==0)))
{isc.Page.setEvent("load","if(window["+this.ID+"])"+this.ID+".pageResize()",isc.Page.FIRE_ONCE);this.$77g=true}
if(isc.Browser.isChrome&&(!isc.Page.isLoaded()||isc.EH.$77e=="load")&&(isc.Page.getWidth()==0||isc.Page.getHeight()==0))
{if(isc.Page.isLoaded()){isc.Page.setEvent("idle","if(window."+this.ID+")"+this.ID+".pageResize()",isc.Page.FIRE_ONCE)}else{isc.Page.setEvent("load","if(window."+this.ID+")"+this.ID+".delayCall('pageResize',[],100)",isc.Page.FIRE_ONCE)}
this.$77g=true}
_2=Math.round((parseInt(_2,10)/100)*_8);var _11=this[this.$90f[_1]];if(_11!=null&&_2<_11){_2=_11}
var _12=this[this.$90u[_1]];if(_12!=null&&_2>_12){_2=_12}
return _2-_3}
var _13=_2;if(!isc.isA.Number(_2)){_2=parseInt(_2);if(isc.isA.Number(_2)&&isc.isA.String(_3)){this[_4]=_3=_2}}
this[_5]=null;var _14=false;if(!isc.isA.Number(_2)||(_2<0&&(_1==this.$o6||_1==this.$o5)))
{if(_13!="*"){this.logWarn("ignoring bad or negative "+_1+": "+_13+(this.logIsDebugEnabled("sizing")?this.getStackTrace():" [enable 'sizing' log for stack trace]"))}else{_1==this.$o6?this.$pn="*":this.$po="*";var _7=this.parentElement;if(isc.isA.Layout(_7)&&_7.hasMember(this)){_7.reflow(this.getID()+" set "+_1+" to '*'");_14=true}}
if(!_14&&(_3==this[_1]||_3==this[_4]))
{_3=this.restoreDefaultSize(_1==this.$o5)}
this.adjustOverflow();return null}
return _2-_3}
,isc.A.restoreDefaultSize=function isc_Canvas_restoreDefaultSize(_1){var _2=_1?this.$o5:this.$o6,_3=this.getClass().getInstanceProperty(_2);if(!isc.isA.Number(_3)){if(_1)_3=this.defaultHeight;else _3=this.defaultWidth}
var _4=this[_2]=(isc.isA.Number(_3)?_3:0);if(_1)this.$o8=_4;return _4}
,isc.A.pageResize=function isc_Canvas_pageResize(){this.$832=true;this.$rq=null;this.$77g=null;this.$qw();delete this.$832}
,isc.A.moveTo=function isc_Canvas_moveTo(_1,_2,_3,_4){if(!_4&&_1==null&&_2==null)return false;if(isc.$cv)arguments.$cw=this;if(_1!=null&&_1.top!=null){_2=_1.top;_1=_1.left}
var _5=this.getDelta(this.$oz,_1,this.getLeft()),_6=this.getDelta(this.$o0,_2,this.getTop());return this.moveBy(_5,_6,_3,_4)}
,isc.A.moveToEvent=function isc_Canvas_moveToEvent(_1,_2){var _3=this.ns.EH.getLastEvent(),x=_3.x,y=_3.y;if(isc.isA.Number(_1))x-=_1;if(isc.isA.Number(_2))y-=_2;var _6=this.ns.EH;var _7=_6.getDragTarget(_3);var _8;if(_6.getDragTarget().canDrop){_8=_6.getDropTarget(_3);if(_8){if(!_7.snapOnDrop||!_8.shouldSnapOnDrop(_7)){_8=null}}else{_8=_6.getDragTarget(_3).parentElement}}else{_8=_6.getDragTarget(_3).parentElement}
if(isc.isA.Canvas(_8)&&(_7.snapToGrid==true||(_7.snapToGrid==null&&_8.childrenSnapToGrid==true)))
{if(_8.noSnapDragOffset(this)){x=_3.x,y=_3.y}
if(_8.suppressHSnapOffset==true)x=_3.x;if(_8.suppressVSnapOffset==true)y=_3.y;if(_8.snapAxis==isc.Canvas.HORIZONTAL||_8.snapAxis==isc.Canvas.BOTH)
{var _9=(_8.getPageLeft()+_8.getLeftBorderSize()+_8.getLeftMargin()-_8.getScrollLeft());x-=_9;x=_8.getHSnapPosition(x)+_8.getHSnapOrigin(_7);x+=_9}
if(_8.snapAxis==isc.Canvas.VERTICAL||_8.snapAxis==isc.Canvas.BOTH)
{var _9=(_8.getPageTop()+_8.getTopBorderSize()+_8.getTopMargin()-_8.getScrollTop())
y-=_9;y=_8.getVSnapPosition(y)+_8.getVSnapOrigin(_7);y+=_9}}
this.setPageRect(x,y)}
,isc.A.getVSnapOrigin=function isc_Canvas_getVSnapOrigin(_1){return this.VSnapOrigin?this.VSnapOrigin:0}
,isc.A.getHSnapOrigin=function isc_Canvas_getHSnapOrigin(_1){return this.HSnapOrigin?this.HSnapOrigin:0}
,isc.A.placeNextTo=function isc_Canvas_placeNextTo(_1,_2,_3,_4){var _5=_1.getPeerRect(),_6=this.getPeerRect(),_7=isc.Canvas.$t7(_6[2],_6[3],_5,_2,_3,_4);this.setPageRect(_7[0],_7[1])}
,isc.A.showNextTo=function isc_Canvas_showNextTo(_1,_2,_3){if(_2==null)_2="right";if(_3==null)_3=false;this.placeNextTo(_1,_2,_3);this.animateShow("fade")}
,isc.A.placeNear=function isc_Canvas_placeNear(_1,_2){if(isc.isAn.Array(_1)){_2=_1[1];_1=_1[0]}else if(isc.isAn.Object(_1)){_2=_1.top;_1=_1.left}
var _3=this.getPeerRect(),_4=isc.Canvas.$t7(_3[2],_3[3],{left:_1,top:_2});this.setPageRect(_4[0],_4[1])}
,isc.A.resizeBy=function isc_Canvas_resizeBy(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;var _5=_3&&_4;if(!_5&&this.rectAnimation)this.finishAnimation("rect");if(!_3){if(_5&&this.resizeAnimation)this.finishAnimation("resize");if(this.hideAnimation)this.finishAnimation("hide");if(this.showAnimation)this.finishAnimation("show")}
var _6=this.getWidth(),_7=this.getHeight();if(isc.isA.Number(_1)){this.width+=_1;if(!this.$pp)this.$t8=true}else{_1=0}
if(isc.isA.Number(_2)){this.height=this.$o8=_7+_2;if(!this.$pp)this.$t9=true}else{_2=0}
if(_1==0&&_2==0)return false;this.$tz=_1;this.$t0=_2;this.$ua=_3;if(this.isDrawn()&&this.logIsInfoEnabled(this.$nx)){this.logInfo("resize of drawn component: "+"new width/height: "+[this.width,this.$o8]+", old width/height: "+[_6,_7]+", delta width/height: "+[_1,_2]+(this.logIsDebugEnabled(this.$nx)?this.getStackTrace():""),this.$nx)}
if(!_4){var _8=this.$q6;if(isc.isAn.Array(_8)){_8[1]+=_1;_8[2]+=_2}
var _9=this.getDrawnState();if(_9==isc.Canvas.COMPLETE){this.$q5(this.left,this.top,this.width,this.$o8);if(isc.isAn.Array(_8))this.setClip(_8)}else if(_9!=isc.Canvas.UNDRAWN){this.$q4=true}
this.$t1()}
return true}
);isc.evalBoundary;isc.B.push(isc.A.$t1=function isc_Canvas__completeResizeBy(){var _1=(this.$tz||0),_2=(this.$t0||0),_3=this.$ua,_4;this.$tz=_4;this.$t0=_4;this.$ua=_4;if(!_1&&!_2)return;var _5;if(this.isDrawn()){_5=this.shouldRedrawOnResize(_1,_2,_3);if(_5){this.markForRedraw(this.$nx)}}
if(!_3)this.layoutChildren(this.$pc,_1,_2)
if(isc.Browser.isMoz&&this.containsIFrame())this.$pw();this.$ub(_1,_2);if(!_5)this.adjustOverflow(this.$nx);if(!_3&&this._useFocusProxy&&this.$qj){var _6=this.$sl();if(_6!=null){_6.style.width=this.getWidth()+isc.px;_6.style.height=this.getHeight()+isc.px}}
this.resizePeersBy(_1,_2);this.$5y(_1,_2)}
,isc.A.shouldRedrawOnResize=function isc_Canvas_shouldRedrawOnResize(_1,_2){var _3=this.redrawOnResize;if(_3==null){_3=!((this.children!=null&&this.children.length>0&&!this.allowContentAndChildren)||(this.getInnerHTML==isc.Canvas.$b4.getInnerHTML&&!isc.isA.Function(this.contents)))}
return _3}
,isc.A.dragResizing=function isc_Canvas_dragResizing(){var _1=isc.EH;return(_1.dragging&&_1.dragOperation==_1.DRAG_RESIZE&&_1.dragTarget==this)}
,isc.A.$5y=function(deltaX,deltaY,reason){if(isc.$cv)arguments.$cw=this;if(this.snapTo)this.$qw(true);if(this.parentElement)this.parentElement.childResized(this,deltaX,deltaY,reason);if(this.masterElement)this.masterElement.peerResized(this,deltaX,deltaY,reason);var peers=this.peers;if(peers){for(var i=0;i<peers.length;i++){if(isc.isA.Canvas(peers[i]))peers[i].masterResized(deltaX,deltaY,reason)}}
if(this.clipCorners&&this.$uc){var clips=this.$uc;if(clips.TR)clips.TR.moveBy(deltaX,null);if(clips.BL)clips.BL.moveBy(null,deltaY);if(clips.BR)clips.BR.moveBy(deltaX,deltaY)}
if(this.$ud!=null)delete this.$ud;if(this.$ue!=null)delete this.$ue;this.resized(deltaX,deltaY,reason);if(!this.$832&&this.isDrawn()&&this.parentElement==null&&!isc.Page.pollPageSize)
{isc.EH.fireOnPause("checkForBodyOverflowChange",{target:isc.Canvas,methodName:"checkForPageResize"},100)}}
,isc.A.$ub=function isc_Canvas__handleResized(){}
,isc.A.resized=function isc_Canvas_resized(_1,_2){}
,isc.A.innerSizeChanged=function isc_Canvas_innerSizeChanged(_1){this.$81b();this.layoutChildren(_1);var _2=this.peers;if(_2){for(var i=0;i<_2.length;i++){if(!_2[i].percentSource&&_2[i].snapTo&&_2[i].percentBox==this.$520)
{_2[i].$qw()}}}}
,isc.A.setPercentSource=function isc_Canvas_setPercentSource(_1,_2){if(isc.isA.String(_1))_1=window[_1];if(!_2&&this.percentSource==_1)return;if(this.percentSource&&this.isObserving(this.percentSource,"innerSizeChanged")){this.ignore(this.percentSource,"innerSizeChanged");this.ignore(this.percentSource,"resized")}
if(!isc.isA.Canvas(_1)){this.percentSource=null;return}
this.percentSource=_1;this.observe(_1,"innerSizeChanged","observer.percentSourceInnerSizeChanged()");this.observe(_1,"resized","observer.$qw()")}
,isc.A.percentSourceInnerSizeChanged=function isc_Canvas_percentSourceInnerSizeChanged(){if(this.percentBox==this.$520)this.$qw()}
,isc.A.childResized=function isc_Canvas_childResized(_1,_2,_3,_4){if(this.allowContentAndChildren&&this.overflow==isc.Canvas.VISIBLE)
this.$t5=true;this.$t6(this.$pd)}
,isc.A.peerResized=function isc_Canvas_peerResized(_1,_2,_3,_4){}
,isc.A.masterResized=function isc_Canvas_masterResized(_1,_2,_3){this.$qw()}
,isc.A.dragResized=function isc_Canvas_dragResized(){}
,isc.A.resizePeersBy=function isc_Canvas_resizePeersBy(_1,_2){var _3=this.peers;if(_3){for(var i=0;i<_3.length;i++){if(_3[i]&&_3[i].masterElement==this&&_3[i].$jo){_3[i].resizeBy(_1,_2)}}}}
,isc.A.layoutChildren=function isc_Canvas_layoutChildren(_1,_2,_3){if(this.children)this.$uf()}
,isc.A.$uf=function isc_Canvas__resolveChildPercentSizes(){var _1=this.children;if(_1!=null&&_1.length>0){for(var i=0;i<_1.length;i++){if(isc.isA.Canvas(_1[i]))_1[i].parentResized()}}}
,isc.A.resizeTo=function isc_Canvas_resizeTo(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;if(_1==null&&_2==null)return false;var _5=this.getDelta(this.$o6,_1,this.getWidth()),_6=this.getDelta(this.$o5,_2,this.getHeight());return this.resizeBy(_5,_6,_3,_4)}
,isc.A.resizeToEvent=function isc_Canvas_resizeToEvent(_1){var _2=this.ns.EH,_3=_2.getLastEvent(),x=_3.x,y=_3.y,_6=this.getPageLeft(),_7=this.getPageTop(),_8=this.getPageRight(),_9=this.getPageBottom();var _10=_2.getDragTarget(_3);var _11=_2.getDragTarget(_3).parentElement;if(_11){if(_10.snapResizeToGrid==true||(_10.snapResizeToGrid==null&&_10.snapToGrid==true)||(_10.snapResizeToGrid==null&&(_11.childrenSnapResizeToGrid==true||(_11.childrenSnapResizeToGrid==null&&_11.childrenSnapToGrid==true)))){if(_11.snapAxis==isc.Canvas.HORIZONTAL||_11.snapAxis==isc.Canvas.BOTH){var _12=(_11.getPageLeft()+_11.getLeftBorderSize()+_11.getLeftMargin()-_11.getScrollLeft());x-=_12;x=_11.getHSnapPosition(x)+_11.getHSnapOrigin(_10);x+=_12}
if(_11.snapAxis==isc.Canvas.VERTICAL||_11.snapAxis==isc.Canvas.BOTH){_12=(_11.getPageTop()+_11.getTopBorderSize()+_11.getTopMargin()-_11.getScrollTop());y-=_12;y=_11.getVSnapPosition(y)+_11.getVSnapOrigin(_10);y+=_12}}}
if(this.logIsDebugEnabled("dragResize")){this.logDebug("resizeToEvent: coords: "+isc.Log.echo({x:x,y:y,left:_6,top:_7,right:_8,bottom:_9}),"dragResize")}
_1=_1||_2.resizeEdge||"BR";if(_1.contains("T")){var _13=Math.min(this.maxHeight,Math.max(_9-y,this.minHeight));_7=_9-_13}else if(_1.contains("B")){var _13=Math.min(this.maxHeight,Math.max(y-_7,this.minHeight));_9=_7+_13}
if(_1.contains("L")){var _14=Math.min(this.maxWidth,Math.max(_8-x,this.minWidth));_6=_8-_14}else if(_1.contains("R")){var _14=Math.min(this.maxWidth,Math.max(x-_6,this.minWidth));_8=_6+_14}
var _15=_8-_6,_16=_9-_7;this.setPageRect(_6,_7,_15,_16,true);_2.dragResizeWidth=_15;_2.dragResizeHeight=_16;if(this==this.ns.EH.dragTracker)this.redrawIfDirty()}
,isc.A.resizeTarget=function isc_Canvas_resizeTarget(_1,_2,_3,_4,_5,_6,_7){_5=_5||0;_4=_4||0;if(_6==null)_6=_2?isc.EH.getY():isc.EH.getX();_6+=_4;if(this.parentElement){var _8=this.getParentPageRect(),_9=_2?(_8[1]+_8[3]):(_8[0]+_8[2]);_9-=_2?this.getVisibleHeight():this.getVisibleWidth();if(_6>_9)_6=_9}
_7=_7!=null?_7:!_2&&this.isRTL();var _10=_2?_1.getMinHeight():_1.getMinWidth(),_11=_2?_1.getMaxHeight():_1.getMaxWidth();var _12;if(_7){_12=(_2?_1.getPageBottom():_1.getPageRight())
-(_2?this.getVisibleHeight():this.getVisibleWidth())}else{_12=_2?_1.getPageTop():_1.getPageLeft()}
var _13=!_7?_6-_12-_5:_12-_6-_5;if(_13<_10){_13=_10}else if(_13>_11){_13=_11}
this.$ug=_13;_6=_12+_5+(_7?-_13:_13);if(_3){_2?_1.setHeight(this.$ug):_1.setWidth(this.$ug)}else{_2?this.setPageTop(_6):this.setPageLeft(_6)}}
,isc.A.finishTargetResize=function isc_Canvas_finishTargetResize(_1,_2,_3){if(_3)return;_2?_1.setHeight(this.$ug):_1.setWidth(this.$ug)}
,isc.A.parentResized=function isc_Canvas_parentResized(){if(isc.$cv)arguments.$cw=this;this.$qw()}
,isc.A.$qw=function isc_Canvas__resolvePercentageSize(_1){if(this.snapTo!=null&&this.percentBox!="custom"){if((this._percent_width||this._percent_height)&&!_1){this.resizeTo(this._percent_width,this._percent_height)}
var _2,_3,_4;_2=(this.masterElement?this.masterElement:this.parentElement);if(!_2)return;isc.Canvas.snapToEdge(_2,this.snapTo,this,this.snapEdge)}
if(this.snapTo==null&&!_1){if(this._percent_left||this._percent_top||this._percent_width||this._percent_height)
{this.setRect(this._percent_left,this._percent_top,this._percent_width,this._percent_height)}}}
,isc.A.prepareForDragging=function isc_Canvas_prepareForDragging(){var _1=this.ns.EH;if(_1.dragTarget)return;var _2=false,_3=this.dragOperation;if(isc.Browser.isTouch&&this.touchDragOperation&&_1.lastEvent.originalType==_1.TOUCH_START)
{_3=this.touchDragOperation}
if(_3){_2=true;_1.dragOperation=_3}else if(this.canDragResize){_1.resizeEdge=this.getEventEdge();if(_1.resizeEdge){_2=true;_1.dragOperation=_1.DRAG_RESIZE;var _4=this.getDragAppearance(_1.DRAG_RESIZE);_1.dragMoveAction=(_4=="tracker")?_1.$ll:_1.$mq}}
if(!_2){if(this.canDragReposition){_2=true;_1.dragOperation=_1.DRAG_REPOSITION;_1.dragMoveAction=_1.$ll}else if(isc.Browser.isTouch&&(this.hscrollOn||this.vscrollOn)&&!this.dragOperation)
{_2=true;_1.dragOperation=_1.DRAG_SCROLL}else if(this.canDrag){_2=true;_1.dragOperation=_1.DRAG}else if(this.canSelectText&&this.overflow!="visible"){_2=true;_1.dragOperation=_1.DRAG_SELECT;this.dragAppearance="none"}}
if(_2){var _5=this;if(this.dragTarget!=null){if(isc.isA.Canvas(this.dragTarget)){_5=this.dragTarget}else if(this.dragTarget=="top"&&this.topElement){_5=this.topElement}else if(this.dragTarget=="parent"&&this.parentElement){_5=this.parentElement}else if(this.dragTarget=="creator"&&this.creator){_5=this.creator}else if(isc.isA.String(this.dragTarget)&&isc.isA.Canvas(window[this.dragTarget]))
{_5=window[this.dragTarget]}else{this.logWarn('prepareForDragging():  target.dragTarget not understood : '+this.dragTarget)}}
_1.dragTarget=_5}}
,isc.A.dragScrollStart=function isc_Canvas_dragScrollStart(){var _1=this.dragScrollTarget||this;this.$77s=isc.EH.getX();this.$77t=isc.EH.getY();this.$77u=_1.scrollLeft||0;this.$77v=_1.scrollTop||0;this.$77w=this.$77x=isc.EH.getX();this.$77y=this.$77z=isc.EH.getY();this.$770=this.$771=isc.timestamp()}
,isc.A.dragScrollMove=function isc_Canvas_dragScrollMove(){var _1=this.dragScrollTarget||this;var _2=this.$77s-isc.EH.getX(),_3=this.$77t-isc.EH.getY();_1.scrollTo(this.$77u+_2,this.$77v+_3,"dragScrollMove");if(window.event)window.event.preventDefault();this.$77w=this.$77x;this.$77y=this.$77z;this.$770=this.$771;this.$77x=isc.EH.getX();this.$77z=isc.EH.getY();this.$771=isc.timestamp()}
,isc.A.dragScrollStop=function isc_Canvas_dragScrollStop(){if(!this.momentumScrolling)return;var _1=(this.$771-this.$770);if(_1==0)return;if(isc.timestamp()-this.$771>100)return;var _2=(this.$77x-this.$77w)/_1,_3=(this.$77z-this.$77y)/_1,_4=this,_5=this.dragScrollTarget||this;if(!_5.hscrollOn)_2=0;if(!_5.vscrollOn)_3=0;if(this.logIsDebugEnabled("dragScroll")){this.logDebug("dragScroll: x/y: "+[this.$77x,this.$77z]+", last: "+[this.$77w,this.$77y]+", elapsed: "+_1+", speed: "+[_2,_3],"dragScroll")}
if(_2==0&&_3==0)return;var _6=this.$772=this.registerAnimation(function(_14){var _7=isc.timestamp(),_1=_7-_4.$771;_4.$771=_7;var _8=_2*(1-_14),_9=_3*(1-_14);var _10=Math.round(_8*_1),_11=Math.round(_9*_1);if(this.logIsDebugEnabled("dragScroll")){this.logDebug("animating: elapsed: "+_1+", frame speed: "+[_8,_9]+", distance: "+[_10,_11],"dragScroll")}
if(_10==0&&_11==0)_4.cancelAnimation(_6);var _12=_5.getScrollLeft(),_13=_5.getScrollTop();_5.scrollTo(_5.getScrollLeft()-_10,_5.getScrollTop()-_11,"dragScrollStop");if(_12==_5.getScrollLeft()&&_13==_5.getScrollTop())
{_4.cancelAnimation(_6)}},this.momentumScrollTime,this.momentumScrollAcceleration)}
,isc.A.hoopSelectStart=function isc_Canvas_hoopSelectStart(){if(!this.hoopSelector)this.hoopSelector=this.createAutoChild("hoopSelector");if(this.hoopSelectorRect)this.hoopSelector.keepInParentRect=this.hoopSelectorRect;var _1=this.$79e=this.hoopSelectorRect||[this.getPageLeft()+this.getLeftBorderSize(),this.getPageTop()+this.getTopBorderSize(),this.getViewportWidth(),this.getViewportHeight()];this.$79f=this.hoopSelectAxis=="horizontal"?_1[3]:null;this.$79g=this.hoopSelectAxis=="vertical"?_1[2]:null;this.$79h=this.getOffsetX();this.$79i=this.getOffsetY();this.resizeHoopSelector();this.hoopSelector.show();return isc.EH.STOP_BUBBLING}
,isc.A.hoopSelectMove=function isc_Canvas_hoopSelectMove(){this.resizeHoopSelector()}
,isc.A.hoopSelectStop=function isc_Canvas_hoopSelectStop(){if(this.hoopSelector)this.hoopSelector.hide()}
,isc.A.resizeHoopSelector=function isc_Canvas_resizeHoopSelector(){if(!this.hoopSelector)return;var x=this.getOffsetX(),y=this.getOffsetY();if(this.hoopSelector.keepInParentRect){if(x<0)x=0;var _3=this.$79e[3];if(y>_3)y=_3}
var _4=Math.max(1,this.$79f?this.$79f:Math.abs(y-this.$79i));var _5=Math.max(1,this.$79g?this.$79g:Math.abs(x-this.$79h));this.hoopSelector.resizeTo(_5,_4);if(!this.$79g){if(x<this.$79h)this.hoopSelector.setLeft(x);else this.hoopSelector.setLeft(this.$79h)}else{this.hoopSelector.setLeft(this.$79e[0])}
if(!this.$79f){if(y<this.$79i)this.hoopSelector.setTop(y);else this.hoopSelector.setTop(this.$79i)}else{this.hoopSelector.setTop(this.$79e[1])}
this.updateHoopSelection()}
,isc.A.updateHoopSelection=function isc_Canvas_updateHoopSelection(){}
,isc.A.setNoDropIndicator=function isc_Canvas_setNoDropIndicator(){this.$uh=true;this.$k2();if(this.shouldSetNoDropTracker&&isc.EH.dragTracker&&isc.EH.dragTracker.isVisible()){if(!this.$ui)this.$ui=isc.EH.dragTracker.getContents();isc.EH.setDragTracker(this.imgHTML(this.noDropTracker))}}
,isc.A.clearNoDropIndicator=function isc_Canvas_clearNoDropIndicator(){if(!this.$uh)return;delete this.$uh;this.$k2();if(this.shouldSetNoDropTracker&&isc.EH.dragTracker){isc.EH.setDragTracker(this.$ui);delete this.$ui}}
,isc.A.shouldDragScroll=function isc_Canvas_shouldDragScroll(){return this.canDragScroll}
,isc.A.$uj=function isc_Canvas__getVDragScrollDirection(_1){var _2=this.getVDragScrollThreshold();if(_1<_2)return-1;if(_1>(this.getViewportHeight()-_2))return 1;return 0}
,isc.A.$uk=function isc_Canvas__getHDragScrollDirection(_1){var _2=this.getHDragScrollThreshold();if(_1<_2)return-1;if(_1>(this.getViewportWidth()-_2))return 1;return 0}
,isc.A.$l4=function isc_Canvas__overDragThreshold(_1){var _2=(this.getOffsetY()-this.getScrollTop()),_3=(this.getOffsetX()-this.getScrollLeft());if(_1!=null){if(_1==isc.Canvas.VERTICAL)
return this.$uj(_2)!=0;else
return this.$uk(_3)!=0}
return(this.$uj(_2)!=0||this.$uk(_3)!=0)}
,isc.A.getHDragScrollThreshold=function isc_Canvas_getHDragScrollThreshold(){if(this.$ud!=null)return this.$ud;var _1=this.dragScrollThreshold;if(isc.isA.Number(_1))this.$ud=_1;else{_1=parseInt(_1);if(!isNaN(_1)){this.$ud=parseInt(_1*this.getViewportWidth()/100);return this.$ud}else{isc.Log.logWarn("Unable to resolve specified drag scroll threshold '"+this.dragScrollThreshold+"' to a valid size. Should be specified as"+" an absolute pixel value, or a percentage of widget viewport.");return 0}}}
,isc.A.getVDragScrollThreshold=function isc_Canvas_getVDragScrollThreshold(){if(this.$ue!=null)return this.$ue;var _1=this.dragScrollThreshold;if(isc.isA.Number(_1))this.$ue=_1;else{_1=parseInt(_1);if(!isNaN(_1)){this.$ue=parseInt(_1*this.getViewportHeight()/100);return this.$ue}else{isc.Log.logWarn("Unable to resolve specified drag scroll threshold '"+this.dragScrollThreshold+"' to a valid size. Should be specified as"+" an absolute pixel value, or a percentage of widget viewport.");return 0}}}
,isc.A.$l5=function isc_Canvas__setupDragScroll(_1,_2){if(this.$ul!=null)return;var _3=(this.getOffsetY()-this.getScrollTop()),_4=(this.getOffsetX()-this.getScrollLeft()),_5=this.$uk(_4),_6=this.$uj(_3);this.$ul=isc.Timer.setTimeout({target:this,methodName:"$um",args:[_5,_6,true,_1,_2]},this.dragScrollDelay)}
,isc.A.$um=function isc_Canvas__performDragScroll(_1,_2,_3,_4,_5){this.$ul=null;var _6=0,_7=0;var _8=this.containsEvent();if(this.ns.EH.dragging&&(_5||_8)){var _9=this.getOffsetX()-this.getScrollLeft(),_10=this.getOffsetY()-this.getScrollTop(),_11=this.getViewportWidth(),_12=this.getViewportHeight();if(!isc.isA.Number(this.maxDragScrollIncrement)){var _13=parseInt(this.maxDragScrollIncrement);if(!isc.isA.Number(_13))
this.logWarn("Unable to resolve this.maxDragScrollIncrement '"+this.maxDragScrollIncrement+"' to a valid value. This should be an "+"absolute pixel value or a percentage to scroll by.");this.$un=parseInt(_13/ 100*this.getScrollWidth());this.$uo=parseInt(_13/ 100*this.getScrollHeight())}else{this.$un=this.$uo=this.maxDragScrollIncrement}
if(!isc.isA.Number(this.minDragScrollIncrement)){var _14=parseInt(this.minDragScrollIncrement);if(!isc.isA.Number(_14))
this.logWarn("Unable to resolve this.minDragScrollIncrement '"+this.minDragScrollIncrement+"' to a valid value. This should be an "+"absolute pixel value or a percentage to scroll by.");this.$up=parseInt(_14/ 100*(this.getScrollWidth()-_11));this.$uq=parseInt(_14/ 100*(this.getScrollHeight()-_12))}else{this.$up=this.$uq=this.minDragScrollIncrement}
var _15=(_4==isc.Canvas.VERTICAL?0:this.$uk(_9)),_16=(_4==isc.Canvas.HORIZONTAL?0:this.$uj(_10));if(_3){if(_1!=0&&_1!=_15)
_1=0;if(_2!=0&&_2!=_16)
_2=0}else{_1=_15;_2=_16}
if(_8){_6=this.getScrollIncrement(_1,_9,_11,this.getHDragScrollThreshold(),this.$un,this.$up);_7=this.getScrollIncrement(_2,_10,_12,this.getVDragScrollThreshold(),this.$uo,this.$uq)}else{_6=_1*this.$un;_7=_2*this.$uo}
if((_6>0&&(this.getScrollLeft()>=this.getScrollRight()))||(_6<0&&(this.getScrollLeft()<=0)))_6=0;if((_7>0&&(this.getScrollTop()>=this.getScrollBottom()))||(_7<0&&(this.getScrollTop()<=0)))_7=0}
if(_6!=0||_7!=0){this.scrollBy(_6,_7);this.$ul=isc.Timer.setTimeout({target:this,methodName:"$um",args:[null,null,null,_4,_5]},50)}else{delete this.$un;delete this.$up;delete this.$uo;delete this.$uq}}
,isc.A.getScrollIncrement=function isc_Canvas_getScrollIncrement(_1,_2,_3,_4,_5,_6){if(_1==null||_1==0)return 0;if(_1>0){_2=_2-(_3-_4)}else if(_1<0){_2=_4-_2}
if(_2<0||_2>_4)return 0;var _7=_1*
((_2/ _4)*(_5-_6)+_6);return parseInt(_7)}
,isc.A.hasInherentHeight=function isc_Canvas_hasInherentHeight(){if(this.inherentHeight!=null)return this.inherentHeight;return(this.children==null&&(this.overflow==isc.Canvas.VISIBLE||this.overflow==isc.Canvas.CLIP_H))}
,isc.A.hasInherentWidth=function isc_Canvas_hasInherentWidth(){if(this.inherentWidth!=null)return this.inherentWidth;return(this.children==null&&(this.overflow==isc.Canvas.VISIBLE||this.overflow==isc.Canvas.CLIP_V))}
,isc.A.canOverflowWidth=function isc_Canvas_canOverflowWidth(){return this.overflow==isc.Canvas.VISIBLE||this.overflow==isc.Canvas.CLIP_H}
,isc.A.canOverflowHeight=function isc_Canvas_canOverflowHeight(){return this.overflow==isc.Canvas.VISIBLE||this.overflow==isc.Canvas.CLIP_V}
,isc.A.getOverflow=function isc_Canvas_getOverflow(){return this.overflow}
,isc.A.setOverflow=function isc_Canvas_setOverflow(_1){if(this.$va!=null&&!this.$vb)
this.finishAnimation(this.$va);if(this.$vc!=null&&!this.$vd)
this.finishAnimation(this.$vc);if(this.overflow==_1)return;var _2=this.overflow;this.overflow=_1;if(!this.isDrawn())return;if(_1!=isc.Canvas.SCROLL&&_1!=isc.Canvas.AUTO&&(this.hscrollOn||this.vscrollOn))
{this.hscrollOn=this.vscrollOn=false;if(this.hscrollbar!=null)this.hscrollbar.hide();if(this.vscrollbar!=null)this.vscrollbar.hide()}
if(isc.Browser.isIE&&(_1==isc.Canvas.CLIP_H||_1==isc.Canvas.CLIP_V))
{this.markForRedraw();return}
var _3=this.getStyleHandle();_3.overflow=this.$rw();var _4=this.$rx();_3.width=_4[0];_3.height=_4[1];if(_3.clip!=null&&_3.clip!=""&&_3.clip!="rect(auto auto auto auto)")
{_3.clip=(isc.Browser.isIE?"rect(auto)":"")}
this.adjustOverflow("setOverflow");if(_2==isc.Canvas.VISIBLE&&_1!=isc.Canvas.VISIBLE){var _5=Math.max(this.getScrollWidth()-this.getInnerWidth(),0),_6=Math.max(this.getScrollHeight()-this.getInnerHeight(),0);if(_5>0||_6>0)this.$5y(-_5,-_6,"overflow changed")}else if(_2!=isc.Canvas.VISIBLE&&_1==isc.Canvas.VISIBLE){var _5=Math.max(this.getScrollWidth()-this.getInnerWidth(),0),_6=Math.max(this.getScrollHeight()-this.getInnerHeight(),0);if(_5>0||_6>0)this.$5y(_5,_6,"overflow changed")}
if((_1==isc.Canvas.HIDDEN||_1==isc.Canvas.VISIBLE)&&(_2==isc.Canvas.HIDDEN||_2==isc.Canvas.VISIBLE)){}else{this.$ur()}}
,isc.A.$t6=function isc_Canvas__markForAdjustOverflow(_1){if(!this.isDrawn()||this.isDirty()||this.destroying||this.$66p)return;if(!this.$rm){if(this.logIsDebugEnabled())
this.logDebug("delaying adjustOverflow: "+(_1?_1:this.getStackTrace()));var _2=this;this.$rn=isc.Timer.setTimeout(function(){if(!_2.destroyed)_2.adjustOverflow(_1,true)},0)}
this.$rm=true}
,isc.A.adjustForContent=function isc_Canvas_adjustForContent(_1){var _2="adjustForContent() called";if(_1)this.adjustOverflow(_2);else this.$t6(_2)}
,isc.A.$qy=function isc_Canvas__browserDoneDrawing(){var _1=this.getHandle();if(isc.Browser.isOpera){var _1=this.getHandle();return!(_1.scrollHeight==0&&_1.scrollWidth==0)}
if(!isc.Browser.isIE){var _2=this.getClipHandle();if(_2==null)return false;var _3=_2.scrollHeight;if(_3==null||_3==0)_3=this.getClipHandle().offsetHeight;return _3!=0}
var _4;if(isc.Browser.isWin){return _1!=null&&_1.scrollHeight!=this.$n1&&_1.scrollHeight!=0}}
,isc.A.adjustOverflow=function isc_Canvas_adjustOverflow(_1,_2,_3){if(isc.$cv)arguments.$cw=this;if(_2&&!this.$rm){return}
this.$rm=false;if(!this.isDrawn()||this.overflow==isc.Canvas.IGNORE)return true;if(!this.adjustOverflowWhileDirty&&!_3&&this.isDirty()&&(this.overflow!=isc.Canvas.VISIBLE))
{return}
if(!isc.Page.isLoaded()&&(isc.Browser.isSafari||(isc.Browser.isMoz&&isc.Browser.geckoVersion<20040616)))
{isc.Page.setEvent("load",this,isc.Page.FIRE_ONCE,"$us");if(isc.Browser.isMoz)return}
if(this.$417)return;if(this.$qy())return this.$ut(_1);if(this.logIsDebugEnabled("overflow")){this.logDebug("browser not done drawing, deferring overflow.","overflow");if(this.useClipDiv){this.logDebug("clipHandle sizes: "+this.echoElementSize(this.getClipHandle()),"overflow")}
this.logDebug("handle sizes: "+this.echoElementSize(this.getHandle()),"overflow")}
if(!this.$uu){this.$t6();this.$uu=true}else{this.logDebug("still waiting for size to become available","overflow");this.$uv()}
return false}
,isc.A.$us=function isc_Canvas__adjustOverflowForPageLoad(){if(!this.destroyed&&this.isDrawn())this.adjustOverflow("pageLoad")}
,isc.A.$uv=function isc_Canvas__queueForDelayedAdjustOverflow(){isc.Canvas.$uv(this.getID())}
,isc.A.$ut=function isc_Canvas__adjustOverflow(_1){if(this.$uw){return}
this.$uw=true;this.$ux(_1);this.$uw=false}
,isc.A.$ux=function isc_Canvas___adjustOverflow(_1){if(!this.$pe[this.overflow]){this.logWarn("This widget has overflow specified as "+this.echo(this.overflow)+".  This overflow setting is not supported - defaulting to overflow:\"visible\".");this.overflow=isc.Canvas.VISIBLE}
if(this.$su!=null)delete this.$su;if(this.$sz!=null)delete this.$sz;var _2=this.$uy,_3=this.$uz;delete this.$uy;delete this.$uz;var _4=this.$u0;this.$u0=false;var _5=isc.Canvas;this.$uu=null;if(this.getHandle()==null)this.logWarn("adjustOverflow: handle null");if(this.getClipHandle()==null)this.logWarn("adjustOverflow: clipHandle null");if(this.alwaysShowVScrollbar){if(this.overflow!=isc.Canvas.AUTO||this.overflow!=isc.Canvas.SCROLL){this.logInfo("alwaysShowVScrollbar specified as true, but overflow set to \""+this.overflow+"\". Property will be ignored.")}else if(this.showCustomScrollbars==false){this.logWarn("alwaysShowVScrollbar property not supported when showing native scrollbars")}}
if(this.logIsInfoEnabled(this.$pf)){this.logInfo("Specified size: "+this.getWidth()+"x"+this.getHeight()+", drawn scroll size: "+this.getScrollWidth(true)+"x"+this.getScrollHeight(true)+", border: "+this.getVBorderSize()+"x"+this.getHBorderSize()+", margin: "+this.getVMarginSize()+"x"+this.getHMarginSize()+(_2==null?"":", old size: "+_2+"x"+_3)+", reason: "+_1,"sizing")}
if(this.logIsDebugEnabled(this.$pf)){if(this.useClipDiv){this.logDebug("clipHandle sizes: "+this.echoElementSize(this.getClipHandle()),"sizing")}
this.logDebug("handle sizes: "+this.echoElementSize(this.getHandle()),"sizing")}
if(this.overflow==_5.IGNORE){}else if(this.overflow==_5.VISIBLE){if(this.$t5){if(this.getWidth()<this.getVisibleWidth()||this.getHeight()<this.getVisibleHeight())
{this.$q5(null,null,this.width,this.$o8)}
delete this.$t5}
var _6=this.getScrollWidth(true),_7=this.getScrollHeight(true);if(this.$ks){var _8=this.getScrollHandle();if(_8.scrollTop!=0||_8.scrollLeft!=0){_8.scrollTop=_8.scrollLeft=0}}
var _9=this.getInnerWidth(),_10=this.getInnerHeight();var _11=this.$u0=(_6>_9||_7>_10);if(!_11&&!_4)
{this.$uy=_6;this.$uz=_7;return}
var _12=this.getHMarginBorder(),_13=this.getVMarginBorder();this.$q5(this.left,this.top,Math.max((_6+_12),this.getWidth()),Math.max((_7+_13),this.getHeight()));var _14=this.children&&this.children.length>0;if(!_14||this.allowContentAndChildren){var _15=this.getScrollHeight(true),_16=this.getScrollWidth(true);if(_15!=_7||_16!=_6){_6=_16;_7=_15;this.$q5(this.left,this.top,Math.max((_6+_12),this.getWidth()),Math.max((_7+_13),this.getHeight()))}}
if(this.snapTo!=null&&_11&&(_1==this.$oe||_1==this.$ny))
{this.$qw(true)}
this.$uy=_6;this.$uz=_7;if((_2!=null&&_2!=_6)||(_3!=null&&_3!=_7))
{if(!_11&&_1==this.$nx)return;this.$5y(_6-_2,_7-_3,this.$pg)}}else if(this.overflow==_5.HIDDEN){this.$q5(this.left,this.top,this.getWidth(),this.getHeight());if(isc.Browser.isIE&&this.isRTL()){this.scrollLeft=this.getClipHandle().scrollLeft}
if(this.scrollLeft!=0||this.scrollTop!=0)this.$51s()}else if(this.overflow==_5.CLIP_H){var _7=this.getScrollHeight(),_13=this.getVMarginBorder(),_17=Math.max(_7+_13,this.getHeight());this.$uz=_17;this.setClip(0,this.getWidth(),_17,0);this.$q5(this.left,this.top,this.getWidth(),_17)}else if(this.overflow==_5.CLIP_V){var _6=this.getScrollWidth(),_12=this.getHMarginBorder();if((isc.Browser.isIE||isc.Browser.isMoz||isc.Browser.isOpera)&&(_6>this.getInnerWidth())&&(this.$uy==_6)){this.$q5(this.left,this.top,this.getWidth(),this.getHeight());_6=this.getScrollWidth(true)
if(_6>this.getInnerWidth()){this.$q5(this.left,this.top,_6+_12,this.getHeight())}}else{this.$q5(this.left,this.top,Math.max(_6+_12,this.getWidth()),this.getHeight())}
var _18=Math.max(_6+_12,this.getWidth());this.setClip(0,_18,this.getHeight(),0);this.$uy=_18}else{if(isc.Browser.isIE&&this.showCustomScrollbars&&this.getScrollingMechanism()==isc.Canvas.NATIVE)
{var _19=this.scrollLeft,_20=this.scrollTop;if(this.getScrollLeft()!=_19||this.getScrollTop()!=_20){this.$lh()}}
var _21=this.vscrollOn,_22=this.hscrollOn,_23=this.$kk();var _24=(this.alwaysShowVScrollbar&&this.showCustomScrollbars);if(this.overflow==isc.Canvas.SCROLL){this.hscrollOn=this.vscrollOn=true}else{var _7=this.getScrollHeight(),_25=this.getHeight(),_6=this.getScrollWidth(),_26=this.getWidth(),_27=this.getScrollbarSize(),_28;var _13=this.getVMarginBorder(),_12=this.getHMarginBorder();if(!this.showCustomScrollbars&&this.getHandle().clientHeight!=null){this.hscrollOn=(this.getClipHandle().clientHeight<_25-_13);this.vscrollOn=_24||(this.getClipHandle().clientWidth<_26-_12)}else{this.vscrollOn=_24||((_7-(_25-_13))>0);this.hscrollOn=(_6-(_26-_12))>0}
if((this.vscrollOn&&!_21&&!this.hscrollOn)||(this.hscrollOn&&!_22&&!this.vscrollOn))
{if(this.showCustomScrollbars){this.$q5(this.left,this.top,this.getWidth(),this.getHeight())}
_28=(this.vscrollOn?"V":"")+(this.hscrollOn?"H":"");this.innerSizeChanged("introducing scrolling");var _16=this.getScrollWidth(true),_15=this.getScrollHeight(true);if(this.logIsDebugEnabled("scrolling")){this.logDebug("Rechecking scrollWidth/Height on introduction of scroll:"+" old: "+[_6,_7]+", new: "+[_16,_15],"scrolling")}
_6=_16;_7=_15}
if(this.vscrollOn&&!this.hscrollOn){if(this.showCustomScrollbars||(this.getClipHandle().clientHeight==null))
this.hscrollOn=_6-(_26-_12-_27)>0;else
this.hscrollOn=(_25>this.getClipHandle().clientHeight+this.getVBorderSize())}else if(this.hscrollOn){if(this.showCustomScrollbars||(this.getClipHandle().clientWidth==null))
this.vscrollOn=_24||(_7-(_25-_13-_27)>0);else
this.vscrollOn=_24||(_26>this.getClipHandle().clientWidth+this.getHBorderSize())}}
if(this.logIsInfoEnabled("scrolling")){this.logInfo("Drawn size: "+this.getScrollWidth(true)+" by "+this.getScrollHeight(true)+", specified: "+this.getWidth()+" by "+this.getHeight()+", scrollbar state: "+(this.hscrollOn?"h":"")+(this.vscrollOn?"v":""),"scrolling")}
if(this.showCustomScrollbars&&(this.hscrollOn!=_22||this.vscrollOn!=_21))
{this.$q5(this.left,this.top,this.getWidth(),this.getHeight());if(this.$su!=null)delete this.$su;if(this.$sz!=null)delete this.$sz}
var _29=((_21?"V":"")+(_22?"H":"")),_30=((this.vscrollOn?"V":"")+(this.hscrollOn?"H":""));if(_29!=_30){this.logInfo("Scrollbar state: "+_29+" -> "+_30,"scrolling");if(_28==null||_30!=_28)
{this.innerSizeChanged("scrolling state changed")}}
if(this.isRTL()&&this.hscrollOn&&!_22){var _31=this.getClipHandle().scrollLeft;this.scrollLeft=_31}
if(this.showCustomScrollbars){if(!this.hscrollOn&&_22)this.hscrollbar.hide();if(!this.vscrollOn&&_21)this.vscrollbar.hide();if(this.hscrollOn){this.$u1()}else{if(_22)this.scrollTo(0,null,"ending hscroll")}
if(this.vscrollOn){this.$u2()}else{if(_21)this.scrollTo(null,0,"ending vscroll")}
this.$51s()}
if((this._useNativeTabIndex||this._useFocusProxy)&&_23!=this.$kk())
{this.$ur()}}
return true}
,isc.A.$51s=function isc_Canvas__clampToContent(){if(this.scrollLeft==0&&this.scrollTop==0)return;var _1=Math.max(0,this.getScrollBottom()),_2=Math.max(0,this.getScrollRight()),_3=this.getScrollLeft(),_4=this.getScrollTop(),_5=false;if(_3>_2){_5=true;_3=_2}
if(_4>_1){_5=true;_4=_1}
if(_5){this.scrollTo(_3,_4,"clampToContent")}}
,isc.A.checkNativeScroll=function isc_Canvas_checkNativeScroll(){var _1=this.getScrollHandle();if(this.getScrollingMechanism()!=isc.Canvas.NATIVE||_1==null)return;if(_1.scrollLeft!=this.scrollLeft||_1.scrollTop!=this.scrollTop){this.scrollTo(this.scrollLeft,this.scrollTop,"removing native scroll")}}
,isc.A.$u1=function isc_Canvas__setHorizontalScrollbar(){var _1=this.hscrollbar;if(!_1){_1=this.hscrollbar=isc.ClassFactory.newInstance(this.scrollbarConstructor,{ID:this.getID()+"_hscroll",autoDraw:false,_generated:true,zIndex:this.getZIndex()+1,vertical:false,scrollTarget:this,visibility:this.visibility,$jp:false,$jo:false,_redrawWithParent:false,$u3:false})}
if(!isc.Page.isLoaded()){var _2=this;isc.Page.setEvent("load",function(){if(!_2.destroyed)_2.$u1()});return}
if(!this.hscrollOn)return;_1.setRect(this.getOffsetLeft()+this.getLeftMargin()+(this.vscrollOn&&this.isRTL()?this.getCustomScrollbarSize():0),this.getOffsetTop()+this.getHeight()-
(this.getBottomMargin()+this.getCustomScrollbarSize()),this.getOuterViewportWidth(),this.getCustomScrollbarSize());if(!_1.masterElement){this.addPeer(_1)}else{if(this.visibility!=isc.Canvas.HIDDEN)_1.show()}}
,isc.A.getCustomScrollbarSize=function isc_Canvas_getCustomScrollbarSize(){var _1=this.scrollbarConstructor;if(isc.isA.String(_1))_1=isc[_1];if(isc.NativeScrollbar!=null&&_1==isc.NativeScrollbar)return isc.NativeScrollbar.getScrollbarSize();return this.scrollbarSize}
,isc.A.$u2=function isc_Canvas__setVerticalScrollbar(){var _1=this.vscrollbar
if(!_1){_1=this.vscrollbar=isc.ClassFactory.newInstance(this.scrollbarConstructor,{ID:this.getID()+"_vscroll",autoDraw:false,_generated:true,zIndex:this.getZIndex()+1,vertical:true,scrollTarget:this,visibility:this.visibility,$jp:false,$jo:false,_redrawWithParent:false,$u3:false})}
if(!isc.Page.isLoaded()){var _2=this;isc.Page.setEvent("load",function(){if(!_2.destroyed)_2.$u2()});return}
if(!this.vscrollOn)return;_1.setShowCorner(this.hscrollOn&&this.vscrollOn);_1.setRect(this.getOffsetLeft()+(this.isRTL()?this.getLeftMargin():this.getWidth()-(this.getRightMargin()+this.getScrollbarSize())),this.getOffsetTop()+this.getTopMargin(),this.getScrollbarSize(),this.getHeight()-this.getVMarginSize());if(!_1.masterElement){this.addPeer(_1)}else{if(this.visibility!=isc.Canvas.HIDDEN)_1.show()}}
,isc.A.scrollByPage=function isc_Canvas_scrollByPage(_1,_2,_3){var _4=(_1?this.getViewportHeight():this.getViewportWidth())-
this.scrollDelta;this.$u4(_1,_2*_4,_3||"scrollByPage")}
,isc.A.scrollByDelta=function isc_Canvas_scrollByDelta(_1,_2,_3){this.$u4(_1,_2*this.scrollDelta,_3||"scrollByDelta")}
,isc.A.$u4=function isc_Canvas__scrollByAmount(_1,_2,_3){if(_1){this.scrollTo(null,this.getScrollTop()+_2,_3)}else{this.scrollTo(this.getScrollLeft()+_2,_3)}}
,isc.A.canScroll=function isc_Canvas_canScroll(_1){var _2=_1?this.getScrollHeight():this.getScrollWidth(),_3=_1?this.getViewportHeight():this.getViewportWidth();return(_2>_3)}
,isc.A.getScrollRatio=function isc_Canvas_getScrollRatio(_1){var _2=_1?this.getScrollHeight():this.getScrollWidth(),_3=_1?this.getViewportHeight():this.getViewportWidth(),_4=_1?this.getScrollTop():this.getScrollLeft(),_5=_2-_3;if(_5==0)return 0;return _4/ _5}
,isc.A.scrollToRatio=function isc_Canvas_scrollToRatio(_1,_2,_3){var _4=Math.max(0,(_1?this.getScrollBottom():this.getScrollRight())),_5=Math.round(_4*_2),_3=_3||"scrollToRatio";if(_1){this.scrollTo(null,_5,_3)}else{this.scrollTo(_5,null,_3)}}
,isc.A.getViewportRatio=function isc_Canvas_getViewportRatio(_1){if(_1){return this.getViewportHeight()/this.getScrollHeight()}else{return this.getViewportWidth()/this.getScrollWidth()}}
,isc.A.getScrollBottom=function isc_Canvas_getScrollBottom(){if(this.overflow==isc.Canvas.VISIBLE)return 0;return this.getScrollHeight()-this.getViewportHeight()}
,isc.A.getScrollRight=function isc_Canvas_getScrollRight(){if(this.overflow==isc.Canvas.VISIBLE)return 0;return this.getScrollWidth()-this.getViewportWidth()}
,isc.A.scrollToTop=function isc_Canvas_scrollToTop(){this.scrollTo(null,0,"scrollToTop")}
,isc.A.scrollToBottom=function isc_Canvas_scrollToBottom(){this.scrollTo(null,this.getScrollBottom(),"scrollToBottom")}
,isc.A.scrollToLeft=function isc_Canvas_scrollToLeft(){this.scrollTo(0,null,"scrollToLeft")}
,isc.A.scrollToRight=function isc_Canvas_scrollToRight(){this.scrollTo(this.getScrollRight(),null,"scrollToRight")}
,isc.A.scrollBy=function isc_Canvas_scrollBy(_1,_2,_3){var _4,_5;if(_1!=null)_4=this.getScrollLeft()+_1;if(_2!=null)_5=this.getScrollTop()+_2;return this.scrollTo(_4,_5,_3||"scrollBy")}
,isc.A.scrollByPercent=function isc_Canvas_scrollByPercent(_1,_2){if(isc.isA.String(_1))_1=parseInt(_1);if(isc.isA.String(_2))_2=parseInt(_2);if(!isc.isA.Number(_1))_1=0;else
_1=parseInt(_1/ 100*Math.max(0,(this.getScrollWidth()-this.getViewportWidth())));if(!isc.isA.Number(_2))_2=0;else
_2=parseInt(_2/ 100*Math.max(0,(this.getScrollHeight()-this.getViewportHeight())));this.scrollBy(_1,_2)}
);isc.evalBoundary;isc.B.push(isc.A.scrollTo=function(left,top,reason,animating){if(isc.$cv)arguments.$cw=this;if(!animating){if(this.scrollAnimation)this.finishAnimation("scroll");if(this.hideAnimation&&this.$hideAnimationInfo.slideOut)
this.$hideAnimationInfo.slideOut=false;if(this.showAnimation&&this.$showAnimationInfo.slideIn)
this.$showAnimationInfo.slideIn=false}
if(this.logIsDebugEnabled("scrolling")){this.logDebug("scrollTo("+left+", "+top+"), reason: "+reason,"scrolling")}
if(!isc.isA.Number(left))left=this.getScrollLeft();if(!isc.isA.Number(top))top=this.getScrollTop();var actuallyMoved=false;if((left!=null&&left!=this.scrollLeft)||(top!=null&&top!=this.scrollTop)){actuallyMoved=true;this.lastScrollLeft=this.scrollLeft;this.lastScrollTop=this.scrollTop;this.lastScrollDirection=(left!=null&&left!=this.scrollLeft&&top!=null&&top!=this.scrollTop?"both":top!=null&&top!=this.scrollTop?"vertical":"horizontal")}
if(reason=="nativeScroll"||!this.isDrawn()){this.scrollLeft=left;this.scrollTop=top}else{var maxScrollLeft=this.getScrollRight();this.scrollLeft=Math.max(0,Math.min(maxScrollLeft,left));var maxScrollTop=this.getScrollBottom();this.scrollTop=Math.max(0,Math.min(maxScrollTop,top));this.$u5(this.scrollLeft,this.scrollTop)}
if(this.showCustomScrollbars){if(this.hscrollOn&&this.hscrollbar)this.hscrollbar.setThumb();if(this.vscrollOn&&this.vscrollbar)this.vscrollbar.setThumb()}
if(actuallyMoved)this.$u6()}
,isc.A.scrolled=function isc_Canvas_scrolled(){}
,isc.A.$u6=function isc_Canvas__scrolled(){if(!isc.EH.$ky){var _1=isc.EH.lastEvent,_2=isc.EH.isMouseEvent(_1.eventType),_3=_2?_1.target:isc.EH.lastMoveTarget;if(_3!=null){if(!this.contains(_3,true))_3=null;else if(!_2&&_3!=this){var _4=this.getOffsetX(),_5=this.getOffsetY();if(!_3.visibleAtPoint(isc.EH.getX(),isc.EH.getY(),false,null,this))
{_3=null}}
if(_3!=null){isc.EH.$kx(null,isc.EH.lastEvent)}}}
this.$81b();if(this.scrolled)this.scrolled()}
,isc.A.$81b=function isc_Canvas__childrenCoordsChanged(){if(!isc.Element.cacheOffsetCoords)return;var _1=this.children;if(_1!=null&&_1.length>0){for(var i=0;i<_1.length;i++){_1[i].$808=_1[i].$809=null;_1[i].$81b()}}}
,isc.A.scrollToPercent=function isc_Canvas_scrollToPercent(_1,_2,_3){if(isc.isA.String(_1))_1=parseInt(_1);if(isc.isA.String(_2))_2=parseInt(_2);if(!isc.isA.Number(_1))_1=0;if(!isc.isA.Number(_2))_2=0;_1=parseInt(_1/ 100*Math.max(0,(this.getScrollWidth()-this.getViewportWidth())));_2=parseInt(_2/ 100*Math.max(0,(this.getScrollHeight()-this.getViewportHeight())));this.scrollTo(_1,_2,_3||"scrollToPercent")}
,isc.A.$u5=function isc_Canvas__scrollHandle(_1,_2){var _3=this.getScrollingMechanism();if(_3==isc.Canvas.NATIVE){var _4=this.getScrollHandle();if(_4){this.$u7=true;_4.scrollLeft=_1;_4.scrollTop=_2;delete this.$u7;if(_4.scrollLeft!=this.scrollLeft||_4.scrollTop!=this.scrollTop){this.scrollLeft=_4.scrollLeft;this.scrollTop=_4.scrollTop}}}else if(_3==isc.Canvas.NESTED_DIV){var _4=this.getHandle();if(_4==null){this.logWarn(this.getCallTrace(arguments)+" in NS6 with null handle");return}
_4=_4.style;_4.left=-_1+"px";_4.top=-_2+"px"}}
,isc.A.$lh=function isc_Canvas__handleCSSScroll(_1,_2){isc.EH.$h1("SCR");if(isc.$cv)arguments.$cw=this;if(this.$u7)return;if(isc.Browser.isMoz&&!_1&&(_2||isc.Browser.geckoVersion<20030312)){if(!this.$u8)
this.$u8=this.delayCall("$lh",[true],10);return}
this.$u8=null;if(!this.isDrawn())return;var _3=this.getScrollHandle(),_4=_3.scrollLeft,_5=_3.scrollTop;if(_4==this.scrollLeft&&_5==this.scrollTop)return;var _6=this.getScrollingMechanism();if(_6!=isc.Canvas.NATIVE){this.logWarn("unsupported native scroll occurred on this widget - resetting");if(_6==isc.Canvas.NESTED_DIV){_3.scrollLeft=_3.scrollTop=0}else{_3.scrollLeft=this.scrollLeft;_3.scrollTop=this.scrollTop}
return}
this.scrollTo(_4,_5,"nativeScroll");isc.EH.$h2()}
,isc.A.mouseWheel=function isc_Canvas_mouseWheel(){if((this.overflow==isc.Canvas.AUTO||this.overflow==isc.Canvas.SCROLL)&&this.showCustomScrollbars&&this.vscrollOn)
{var _1=this.ns.EH.lastEvent.wheelDelta;var _2=this.scrollTop+Math.round(_1*isc.Canvas.scrollWheelDelta);this.scrollTo(this.getScrollLeft(),_2,"mouseWheel");return false}
return true}
,isc.A.isDragScrolling=function isc_Canvas_isDragScrolling(){if(this.vscrollOn&&this.vscrollbar&&this.vscrollbar.isDragScrolling())return true;if(this.hscrollOn&&this.hscrollbar&&this.hscrollbar.isDragScrolling())return true;return false}
,isc.A.isRepeatTrackScrolling=function isc_Canvas_isRepeatTrackScrolling(){if(this.vscrollOn&&this.vscrollbar&&this.vscrollbar.isRepeatTrackScrolling())return true;if(this.hscrollOn&&this.hscrollbar&&this.hscrollbar.isRepeatTrackScrolling())return true;return false}
,isc.A.handleKeyPress=function isc_Canvas_handleKeyPress(_1,_2){var _3;if(this.convertToMethod("keyPress")){_3=this.keyPress(_1,_2)}
if(_3!=false&&this.shouldCancelKey!=null&&this.shouldCancelKey(_1,_2))
{_3=false}
if(_3==false)return false;var _4=_1.keyName;if(this._useFocusProxy&&((isc.Browser.isMoz&&this.canSelectText)||isc.Browser.isSafari)&&_4=="Tab")
{this.setFocus(true)}
if((this.overflow==isc.Canvas.AUTO||this.overflow==isc.Canvas.SCROLL)&&this.showCustomScrollbars)
{_3=this.handleKeyboardScroll(_4)}
return _3}
,isc.A.handleKeyboardScroll=function isc_Canvas_handleKeyboardScroll(_1){var _2=0,_3=0;if(_1=="Page_Up")_3-=this.getViewportHeight();else if(_1=="Page_Down")_3+=this.getViewportHeight();else if(_1=="Arrow_Up")_3-=10;else if(_1=="Arrow_Down")_3+=10;else if(_1=="Arrow_Left")_2-=10;else if(_1=="Arrow_Right")_2+=10;var _4="cancel native keyPress scrolling";if(_2!=0||_3!=0){this.scrollTo(this.scrollLeft+_2,this.scrollTop+_3,_4);return false}
if(_1=="Home"){this.scrollTo(null,0,_4);return false}else if(_1=="End"){this.scrollTo(null,(this.getScrollHeight()-this.getViewportHeight()),_4);return false}}
,isc.A.handleKeyDown=function isc_Canvas_handleKeyDown(_1,_2){var _3
if(this.convertToMethod("keyDown")){_3=this.keyDown(_1,_2)}
if(this.cancelNativeScrollOnKeyDown&&(this.overflow==isc.Canvas.AUTO||this.overflow==isc.Canvas.SCROLL)&&this.showCustomScrollbars)
{var _4=isc.EH.getKey();if(this.$90e[_4]==true)_3=false}
return _3}
,isc.A.$q5=function isc_Canvas__setHandleRect(_1,_2,_3,_4){if(this.showCustomScrollbars&&this.vscrollOn&&_1!=null&&this.isRTL()){_1+=this.getScrollbarSize()}
if(_3!=null||_4!=null){var _5=this.$sf(_3,_4);_3=_5[0];_4=_5[1]}
var _6=this.getStyleHandle();if(_6){if(_1!=null&&isc.isA.Number(_1))this.$u9(_6,isc.Canvas.LEFT,_1);if(_2!=null&&isc.isA.Number(_2))this.$u9(_6,isc.Canvas.TOP,_2);if(_3!=null&&isc.isA.Number(_3))this.$u9(_6,this.$o6,Math.max(_3,1));if(_4!=null&&isc.isA.Number(_4))this.$u9(_6,this.$o5,Math.max(_4,1))}}
,isc.A.$u9=function isc_Canvas__assignSize(_1,_2,_3){if(isc.Browser.isIE||isc.Browser.isOpera){if(!isc.Browser.isStrict){_1[_2]=_3}else{if(_3<0&&(_2==this.$o6||_2==this.$o5))_3=0;_1[_2]=_3+this.$ph}}else{if(_1==null){return}
_1[_2]=_3+this.$ph}}
,isc.A.$qi=function isc_Canvas__sizeBackMask(){var _1=this._backMask;if(!_1)return;if(this.showEdges){var _2=this.$l0,_3=this.maskEdgeCenterOnly,_4=_3?_2.$y4:_2.$tb,_5=_3?_2.$y5:_2.$tc,_6=_3?_2.$y6:_2.$td,_7=_3?_2.$y7:_2.$te,_8=this.getVisibleWidth()-(_4+_5),_9=this.getVisibleHeight()-(_6+_7);if(_8<=0||_9<=0)_1.hide();else{if(this.isVisible())_1.show();_1.setRect(this.getLeft()+_4,this.getTop()+_6,_8,_9)}}else{_1.setRect(this.getRect())}}
,isc.A.getTextDirection=function isc_Canvas_getTextDirection(){if(this.$81a)return this.$81a;var _1=this;while(_1){if(_1.textDirection!=null){return(this.$81a=_1.textDirection)}
_1=_1.parentElement;if(_1&&_1.eventProxy)_1=_1.eventProxy}
return(this.$81a=isc.Page.getTextDirection())}
,isc.A.isRTL=function isc_Canvas_isRTL(){return(this.getTextDirection()==isc.Canvas.RTL)}
,isc.A.getRTLSign=function isc_Canvas_getRTLSign(){return this.isRTL()?-1:1}
,isc.A.setVisibility=function isc_Canvas_setVisibility(_1){if(this.$va!=null&&!this.$vb)
this.finishAnimation(this.$va);if(this.$vc!=null&&!this.$vd)
this.finishAnimation(this.$vc);if(this.fadeAnimation)this.finishAnimation("fade");if(!isc.isA.String(_1)){_1=(_1!=false?isc.Canvas.INHERIT:isc.Canvas.HIDDEN)}
if(this.visibility==_1)return;var _2=this.isVisible();this.visibility=_1;if(this.isDrawn()){if(!_2&&this.isVisible()){if(this.isDirty()){this.redraw("show() while dirty")}else if(this.children&&this.children.length>0){var _3=isc.Canvas.$nm.duplicate();for(var i=0;i<_3.length;i++){var _5=_3[i];if(_5&&_5.isDirty()&&this.$sr(_5)){_5.redraw("show() on parent while dirty")}}}
this.$ve()}
this.$vf(_1)}
if(_2&&!this.isVisible()){this.$ve()}
if(this.peers){for(var i=0;i<this.peers.length;i++){var _6=this.peers[i];if(this.isVisible()&&((_6==this.hscrollbar&&!this.hscrollOn)||(_6==this.vscrollbar&&!this.vscrollOn)))continue;if(this.isVisible()&&_6==this._shadow&&!this.showShadow)continue;if(_6.$jq)_6.setVisibility(_1)}}
if(this.children)this.children.map("parentVisibilityChanged",_1,this);if(this.parentElement)this.parentElement.childVisibilityChanged(this,_1);if(this._useFocusProxy)this.$vg();this.$807()}
,isc.A.$807=function isc_Canvas__visibilityChanged(){if(!this.isDrawn())return;var _1=this.isVisible();if(_1!=this.$806){this.$806=_1;if(this.visibilityChanged!=null){this.visibilityChanged(this.isVisible())}}}
,isc.A.parentVisibilityChanged=function isc_Canvas_parentVisibilityChanged(_1,_2){if(this.children)this.children.map("parentVisibilityChanged",_1,_2);this.$ve();if(this==isc.Canvas.$rl)isc.Canvas.hideResizeThumbs();if(this._useFocusProxy)this.$vg();if(_2.$sr(this))this.$807()}
,isc.A.childVisibilityChanged=function isc_Canvas_childVisibilityChanged(_1,_2){this.$t6("childVisChange")}
,isc.A.childCleared=function isc_Canvas_childCleared(_1){if(!this.destroying)this.$t6("childClear")}
,isc.A.peerCleared=function isc_Canvas_peerCleared(_1){}
,isc.A.childDrawn=function isc_Canvas_childDrawn(_1){if(this.isDrawn())this.$t6("childDraw")}
,isc.A.peerDrawn=function isc_Canvas_peerDrawn(_1){}
,isc.A.$vg=function isc_Canvas__updateFocusProxyVisibility(){if(!this._useFocusProxy||!this.$qj)return;var _1=this.isVisible(),_2=this.$sl();if(_2){if(_1&&_2.style.visibility==isc.Canvas.HIDDEN)
_2.style.visibility=isc.Canvas.VISIBLE
if(!_1&&_2.style.visibility!=isc.Canvas.HIDDEN)
_2.style.visibility=isc.Canvas.HIDDEN}}
,isc.A.$vf=function isc_Canvas__setHandleVisibility(_1){var _2=this.getStyleHandle();if(_2!=null)_2.visibility=_1}
,isc.A.$ve=function isc_Canvas__updateHandleDisplay(){if(!this.hideUsingDisplayNone||!this.isDrawn())return;var _1=this.getStyleHandle();if(!this.isVisible()&&!this.$62u){this.$vh=_1.display;this.$62u=true;_1.display=this.$27r;this.$808=this.$809=null}else if(this.isVisible()&&this.$62u){_1.display=(this.$vh?this.$vh:isc.emptyString);delete this.$62u}}
,isc.A.$418=function isc_Canvas__drawOnShow(){return(this.getDrawnState()==isc.Canvas.UNDRAWN)&&!this.parentElement&&!this.masterElement}
,isc.A.show=function isc_Canvas_show(){if(isc.$cv)arguments.$cw=this;var _1=this.hasFocus;if(this.$418()){this.draw(true)}
this.setVisibility(isc.Canvas.INHERIT);if(_1&&this.hasFocus){this.logInfo("Show: Hidden / Undrawn widget marked as having focus - calling focus()","events");this.hasFocus=false;this.focus()}
if(this.autoShowParent&&this.parentElement)this.parentElement.show()}
,isc.A.showRecursively=function isc_Canvas_showRecursively(){var _1=this.getParentElements();if(this.$86x==null&&_1.isEmpty()){this.show()}else{this.setVisibility(isc.Canvas.INHERIT);if(this.$86x!=null){_1.add(window[this.$86x])}
for(var i=0;i<_1.length;i++){_1[i].showRecursively();if(isc.TabSet!=null&&isc.isA.TabSet(_1[i])){_1[i].selectTab(_1[i].tabForPane(this))}else if(isc.SectionStack!=null&&isc.isA.SectionStack(_1[i])){_1[i].expandSection(_1[i].sectionForItem(this))}}}}
,isc.A.$414=function isc_Canvas__relativePageResized(){if(!this.isDrawn()||this.parentElement||this.position!=this.$411)return;var _1=this.$412,_2=this.$413,_3=this.getPageLeft(),_4=this.getPageTop();this.$tx=(_3-_1);this.$ty=(_4-_2);this.$t2();this.$412=_3;this.$413=_4;isc.Page.setEvent("resize",this,isc.Page.FIRE_ONCE,"$414")}
,isc.A.hide=function isc_Canvas_hide(){this.$rk();this.setVisibility(isc.Canvas.HIDDEN)}
,isc.A.isVisible=function isc_Canvas_isVisible(){var _1=this;while(_1){if(_1.visibility==isc.Canvas.HIDDEN)return false;if(_1.visibility==isc.Canvas.VISIBLE)return true;_1=_1.parentElement}
return true}
,isc.A.$st=function isc_Canvas__isDisplayNone(){var _1=this;while(_1){if(_1.visibility==isc.Canvas.HIDDEN&&_1.hideUsingDisplayNone)return true;_1=_1.parentElement}
return false}
,isc.A.setEnabled=function isc_Canvas_setEnabled(_1){this.logWarn("call to deprecated method 'setEnabled()' - use 'setDisabled()' instead.");var _2=((_1==null||isc.isA.Boolean(_1))?!_1:(_1==this.$pi));this.setDisabled(_2)}
,isc.A.setDisabled=function isc_Canvas_setDisabled(_1){if(_1==null)_1=false;if(!isc.isA.Boolean(_1))_1=(_1==this.$pi);if(this.disabled==_1)return;if(this.peers)this.peers.map("masterDisabled",_1);var _2=this.isDisabled()
this.disabled=_1;var _3=this.isDisabled();if(_2!=_3){this.setHandleDisabled(_3);if(this.children)this.children.map("parentDisabled",_3)}}
,isc.A.masterDisabled=function isc_Canvas_masterDisabled(_1){this.setDisabled(_1)}
,isc.A.parentDisabled=function isc_Canvas_parentDisabled(_1){if(this.disabled)return;if(!this.parentElement.redrawOnDisable)this.setHandleDisabled(_1);if(this.children)this.children.map("parentDisabled",_1)}
,isc.A.setHandleDisabled=function isc_Canvas_setHandleDisabled(_1){if(!this.isDrawn())return;if(this.redrawOnDisable)this.markForRedraw("setDisabled");if(this.$kk())this.disableKeyboardEvents(_1)}
,isc.A.disableKeyboardEvents=function isc_Canvas_disableKeyboardEvents(_1,_2){if(_1){this.$vi(-1);if(this.accessKey!=null)this.$m9(null)}else{this.$vi(this.getTabIndex());if(this.accessKey!=null)this.$m9(this.accessKey)}
if(_1&&this.hasFocus)this.blur();if(_2&&this.children){for(var i=0;i<this.children.length;i++){this.children[i].disableKeyboardEvents(_1,true)}}}
,isc.A.enable=function isc_Canvas_enable(){if(this.disabled)this.setDisabled(false)}
,isc.A.disable=function isc_Canvas_disable(){if(!this.disabled)this.setDisabled(true)}
,isc.A.isDisabled=function isc_Canvas_isDisabled(){var _1=this;while(_1){if(_1.disabled)return true;_1=_1.parentElement;if(_1&&_1.eventProxy)_1=_1.eventProxy}
return false}
,isc.A.isEnabled=function isc_Canvas_isEnabled(){this.logWarn("Call to deprecated 'isEnabled()' method - should use isDisabled() instead");return!this.isDisabled()}
,isc.A.$kk=function isc_Canvas__canFocus(){if(this.canFocus!=null)return this.canFocus;if((this.overflow==isc.Canvas.SCROLL)||((this.overflow==isc.Canvas.AUTO)&&(this.vscrollOn||this.hscrollOn))){return true}
return false}
,isc.A.setCanFocus=function isc_Canvas_setCanFocus(_1){this.canFocus=_1;this.$ur()}
,isc.A.$ur=function isc_Canvas__updateCanFocus(){this.$vj(this.$kk());this.canFocusChanged()}
,isc.A.$vj=function isc_Canvas__updateHandleForFocus(_1){var _2;if(this._useFocusProxy){if(_1){_2=this.$sl();if(!_2)return this.makeFocusProxy()}else{this.$qp();return}
if(isc.Browser.isSafari&&this.getTabIndex()==-1){this.$qp();return}}
if(this.$qc()){if(_1&&this.accessKey){this.$qd()}else if(this.$qt){this.$qu()}}
if(this._useNativeTabIndex)_2=this.getFocusHandle();if(_1){this.$vk(this.getTabIndex(),this.$sp);if(_2!=null){var _3=this.$sg(),_4=this.$si();_2.onfocus=_3;_2.onblur=_4;if(this.accessKey)this.$m9(this.accessKey)}}else{if(_2!=null){_2.onFocus=null;_2.onBlur=null;this.$vi(-1);if(_2.accessKey!=null)this.$m9(null)}}}
,isc.A.canFocusChanged=function isc_Canvas_canFocusChanged(){var _1=this.parentElement;while(_1){_1.childCanFocusChanged(this);_1=_1.parentElement}}
,isc.A.childCanFocusChanged=function isc_Canvas_childCanFocusChanged(_1){}
,isc.A.setShowFocusOutline=function isc_Canvas_setShowFocusOutline(_1,_2){if(!_2&&this.showFocusOutline==_1)return;if(!_2)this.showFocusOutline=_1;if(isc.Browser.isMoz){var _3=this.getClipHandle();if(_3){_3.style.MozOutlineStyle=(_1?isc.emptyString:this.$27r)}}else{var _3=this.getHandle();if(_3)_3.hideFocus=!_1}}
,isc.A.$vl=function isc_Canvas__readyToSetFocus(_1){return(this.isDrawn()&&this.visibleInDOM()&&(!_1||!this.isDisabled()))}
,isc.A.visibleInDOM=function isc_Canvas_visibleInDOM(){if(!this.isVisible())return false;var _1=this;while(_1.parentElement)_1=_1.parentElement;if(_1.position==isc.Canvas.ABSOLUTE)return true;var _2=this.getDocumentBody();var _3=_1.getClipHandle().parentNode;while(_3&&_3!=_2){var _4=_3.style;if(_4&&_4.visibility==this.$nz)return false;if(_4&&_4.display==this.$27r)return false;_3=_3.parentNode}
return true}
,isc.A.getFocusHandle=function isc_Canvas_getFocusHandle(){if(this._useNativeTabIndex){return this.getClipHandle()}else if(this._useFocusProxy&&this.$qj){return this.$sl()}
return null}
,isc.A.setFocus=function isc_Canvas_setFocus(_1,_2){if(!this.$vl(_1))return;var _3=this.getFocusHandle(_1);if(_1&&this.$kk()){if(_3!=null){if(isc.Browser.isIE&&document.activeElement==_3){this.logInfo("setFocus() not calling element.focus() as element already has "+"native focus","nativeFocus")}else{this.logInfo("about to call native focus()"+(this.logIsDebugEnabled("traceFocus")?this.getStackTrace():""),"nativeFocus");isc.EH.$lg=this;_3.focus();isc.EH.$vm=this}}else{this.ns.EH.focusInCanvas(this)}}else if(this.hasFocus){if(_3){this.logInfo("about to call native blur()"+(this.logIsDebugEnabled("traceBlur")?this.getStackTrace():""),"nativeFocus");isc.EH.$ld=this;_3.blur()}else{this.ns.EH.blurFocusCanvas(this)}}}
,isc.A.$vn=function isc_Canvas__restoreFocus(){var _1=isc.EH.$ke;if(_1!=null&&_1!=this){this.logDebug("not restoring focus; focus moved to: "+_1,"nativeFocus");return}
var _2=isc.EH.$lg;if(_2!=null&&_2!=this){this.logDebug("not restoring focus; focus about to move to:"+_2,"nativeFocus");return}
this.logDebug("restoring focus from zIndex change","nativeFocus");this.$vo(true)}
,isc.A.focus=function isc_Canvas_focus(_1){if(isc.$cv)arguments.$cw=this;this.setFocus(true,_1)}
,isc.A.blur=function isc_Canvas_blur(_1){if(isc.$cv)arguments.$cw=this;this.setFocus(false,_1)}
,isc.A.focusAtEnd=function isc_Canvas_focusAtEnd(_1){return this.focus()}
,isc.A.$vo=function isc_Canvas__setFocusWithoutHandler(_1,_2){this.$vp=true;this.setFocus(_1,_2)}
,isc.A.$lf=function isc_Canvas__focusChanged(_1){if(_1==null)_1=(this.ns.EH.$ke==this);this.hasFocus=_1;if(this.$vp){delete this.$vp;return false}
this.$vq=true;if(this.focusChanged!=null){this.convertToMethod("focusChanged");this.focusChanged(_1)}
if(this.redrawOnFocus)this.markForRedraw("setFocus");this.$vq=false}
,isc.A.$rk=function isc_Canvas__updateFocusForHide(){var _1=this.ns.EH.getFocusCanvas();if(this.$sr(_1)){if(isc.isA.Canvas(_1.focusOnHide)&&_1.focusOnHide.isDrawn()&&_1.focusOnHide.isVisible()){_1.focusOnHide.focus()}
else{_1.blur();if(_1.hasFocus)isc.EH.blurFocusCanvas(_1)}}}
,isc.A.containsFocus=function isc_Canvas_containsFocus(){var _1=this.ns.EH.getFocusCanvas();return this.contains(_1,true)}
,isc.A.setAccessKey=function isc_Canvas_setAccessKey(_1){this.accessKey=_1;if(this.$kk()&&!this.isDisabled()){this.$m9(this.accessKey)}}
,isc.A.$m9=function isc_Canvas__setHandleAccessKey(_1){if(this.$qc()){if(_1==null)this.$qu();else{if(this.$qt)this.$qt.accessKey=_1;else this.$qd()}
return}
if(this._useNativeTabIndex){var _2=this.getHandle();if(_2!=null)_2.accessKey=_1}
if(this._useFocusProxy&&this.$qj){var _2=this.$sl();if(_2!=null){if(isc.Browser.isMoz){this.$qp();this.makeFocusProxy()}else{_2.accessKey=_1}}}}
,isc.A.getAccessKey=function isc_Canvas_getAccessKey(){return this.accessKey}
,isc.A.getTabIndex=function isc_Canvas_getTabIndex(){if(this.tabIndex==null){this.$vr()}
return this.tabIndex}
,isc.A.getTabIndexSpan=function isc_Canvas_getTabIndexSpan(){return 1}
,isc.A.setTabIndex=function isc_Canvas_setTabIndex(_1){var _2=isc.Canvas.TAB_INDEX_FLOOR;if(_1>=_2){var _3=_2-1;this.logWarn("setTabIndex(): Passed index of "+_1+". This method does not support setting a tab index greater than "+_3+".  Setting tab index for this widget to "+_3+this.getStackTrace());_1=_3}
this.$rp();this.$vk(_1,false)}
,isc.A.$vk=function isc_Canvas__setTabIndex(_1,_2){this.$sp=_2;this.tabIndex=_1;if(this.$kk()&&!this.isDisabled()){this.$vi(_1)}}
,isc.A.$vi=function isc_Canvas__setHandleTabIndex(_1){if(this._useNativeTabIndex&&this.isDrawn()){var _2=this.getFocusHandle();_2.tabIndex=_1;if(isc.Browser.isIE)isc.Canvas.$vs()}
if(this._useFocusProxy){if(!this.$qj)return this.makeFocusProxy();var _2=this.$sl();var _3=(this.hasFocus&&!this.$vq);if(_3&&_2)_2.blur();if(isc.Browser.isSafari&&_1<0)return this.$qp();if(_2!=null){_2.tabIndex=_1;if(isc.Browser.isMoz){_2.style.MozUserFocus=(_1<0?"ignore":"normal")}
if(_3)_2.focus()}}}
,isc.A.$vr=function isc_Canvas__autoAllocateTabIndex(){var _1=isc.Canvas;if(_1.$vt==null){_1.$vt=_1.TAB_INDEX_FLOOR}
var _2=isc.EH.$kl;if(_2)_1.$vt+=_2.getTabIndexSpan();_1.$vt+=_1.TAB_INDEX_GAP
if(_1.$vt>isc.Canvas.TAB_INDEX_CEILING&&!isc.Canvas.$vu)
{isc.Canvas.logWarn("Auto allocation of tab-indices has reached native browser ceiling "+"- tab-order cannot be guaranteed for widgets on this page.");isc.Canvas.$vu=true}
this.$vk(_1.$vt,true);if(_2){_2.$vv(this);this.$vw=_2}else{isc.EH.$kj=this}
isc.EH.$kl=this}
,isc.A.$vx=function isc_Canvas__setTabBefore(_1){if(this==_1||this.$vy()==_1)return;var _2=_1.getTabIndex();if(!_1.$sp){this.logWarn("$vx() attempting to set tab index adjacent to widget "+_1+" with explicitly specified tabIndex ["+_1.tabIndex+"]. This method can only manipulate widgets with auto-assigned tab indexes.");return}
var _3=_1.$vw;var _4=this.$vz(),_5=this.$vy();if(isc.EH.$kl==this)isc.EH.$kl=_4;if(isc.EH.$kj==this)isc.EH.$kj=_5;if(_4!=null)
_4.$vv(_5);if(_5!=null)
_5.$v0(_4);this.$v0(null);this.$vv(null);this.$v1(_1.$vz(),_1);this.$v2()}
,isc.A.$sq=function isc_Canvas__setTabAfter(_1){if(this==_1||this.$vw==_1)return;_1.getTabIndex();if(!_1.$sp){this.logWarn("$sq() attempting to set tab index adjacent to widget "+_1+" with explicitly specified tabIndex ["+_1.tabIndex+"]. This method can only manipulate widgets with auto-assigned tab indexes.");return}
var _2=_1,_3=this.$vz(),_4=this.$vy();if(isc.EH.$kl==this)isc.EH.$kl=_3;if(isc.EH.$kj==this)isc.EH.$kj=_4;if(_3!=null)
_3.$vv(_4);if(_4!=null)
_4.$v0(_3);this.$v0(null);this.$vv(null);this.$v1(_1,_1.$vy());this.$v2()}
,isc.A.$v1=function isc_Canvas__slotTabBetween(_1,_2){if(_2==null)return this.$vr();if(_1==null){var _3=_2.$vy();_2.$rp();this.$vv(_3);this.$v0(null);this.$vk(_2.tabIndex,true);isc.EH.$kj=this;_2.$v1(this,_3);return}
this.$vv(_2);_2.$v0(this);this.$v0(_1);_1.$vv(this);var _4=_1.tabIndex+_1.getTabIndexSpan(),_5=_2.tabIndex,_6=_4+Math.floor((_5-_4)/2),_7=this.getTabIndexSpan();if((_6+_7)>_5){_2.$v3((_6+_7)-_5)}
if(this.logIsDebugEnabled("tabIndex")){this.logDebug("Putting "+this.getID()+" in tab order between: "+_1.getID()+":"+_1.tabIndex+", and :"+_2.getID()+":"+_2.tabIndex+". Resulting tabIndex:"+_6,"tabIndex")}
this.$vk(_6,true)}
,isc.A.$v3=function isc_Canvas__shiftTabIndexForward(_1){var _2=this.$vy();if(_2==null){this.$vk(this.tabIndex+_1+isc.Canvas.TAB_INDEX_GAP,true);return}
var _3=_2.getTabIndex(),_4=_3-this.getTabIndexSpan();if(this.tabIndex+_1<_4)this.$vk(_4,true);else{_2.$v3(_1-(_4-this.tabIndex));this.$vk(_2.tabIndex-this.getTabIndexSpan(),true)}}
,isc.A.$vy=function isc_Canvas__getNextTabWidget(_1){if(!_1)return this.$v4;else return this.$vw}
,isc.A.$vz=function isc_Canvas__getPreviousTabWidget(){return this.$vy(true)}
,isc.A.$vv=function isc_Canvas__setNextTabWidget(_1,_2){if(!_2)this.$v4=_1;else this.$vw=_1}
,isc.A.$v0=function isc_Canvas__setPreviousTabWidget(_1){return this.$vv(_1,true)}
,isc.A.$kf=function isc_Canvas__focusInNextTabElement(_1,_2){if(isc.CanvasItem&&this.canvasItem!=null&&this.canvasItem.form!=null){this.canvasItem.form.$kf(_1,_2);return}
var _3=this;do{_3=(_1?_3.$vy():_3.$vz())}while(_3&&(isc.EH.targetIsMasked(_3,_2)||_3.isDisabled()||!_3.isDrawn()||!_3.isVisible()||!_3.$kk())&&(!isc.CanvasItem||_3.canvasItem==null||_3.canvasItem.form==null))
if(_3){this.logInfo("focusInNextTabElement() shifting focus to:"+_3,"syntheticTabIndex");_3.focusAtEnd(_1)}else if(_1){this.logInfo("focusInNextTabElement() shifting focus to first widget","syntheticTabIndex");if(isc.EH.$kj==null||(isc.EH.$kj==this&&(this.isDisabled()||!this.isDrawn()||!this.isVisible()||!this.$kk()||this.isMasked(_2))))
{return}
isc.EH.$kh(_2)}else{this.logInfo("focusInNextTabElement() shifting focus to last widget","syntheticTabIndex");if(isc.EH.$kl==null||(isc.EH.$kl==this&&(this.isDisabled()||!this.isDrawn()||!this.isVisible()||!this.$kk()||this.isMasked(_2))))
{return}
isc.EH.$kg(_2)}}
,isc.A.$v2=function isc_Canvas__slotChildrenIntoTabOrder(){var _1=isc.isA.Layout(this)?this.members:this.children;if(!_1||_1.length==0)return;var _2=this.$vy();for(var i=_1.length-1;i>=0;i--){if(_1[i]==null||(_1[i].tabIndex!=null&&!_1[i].$sp))continue;if(_2==null)_1[i].$sq(this);else _1[i].$vx(_2);_2=_1[i]}}
,isc.A.$v5=function isc_Canvas__getLastAutoIndexDescendant(){var _1=this.children;if(isc.Layout&&isc.isA.Layout(this))_1=this.members;if(_1!=null){for(var i=_1.length-1;i>=0;i--){if(_1[i]==null)continue;var _3=_1[i].$v5();if(_3!=null)return _3}}
if(this.tabIndex==null||this.$sp)return this;return null}
,isc.A.$rp=function isc_Canvas__removeFromAutoTabOrder(){if(!this.$sp||!this.tabIndex)return;var _1=this.$vz(),_2=this.$vy();if(_1==null&&_2==null&&isc.EH.$kl!=this&&isc.EH.$kj!=this)return;if(_1){_1.$vv(_2)}else{isc.EH.$kj=_2}
if(_2){_2.$v0(_1)}else{isc.EH.$kl=_1}
this.$v0(null);this.$vv(null)}
,isc.A.getZIndex=function isc_Canvas_getZIndex(_1){if(!this.isDrawn()||isc.Browser.isSafari){if(_1&&this.zIndex==isc.Canvas.AUTO){this.setZIndex(isc.Canvas.getNextZIndex())}
return this.zIndex}
return parseInt(this.getStyleHandle().zIndex)}
,isc.A.setZIndex=function isc_Canvas_setZIndex(_1){var _2=this.zIndex;if(_2==_1)return;var _3=false;if(isc.Browser.isIE&&this.hasFocus&&this._useNativeTabIndex)
{_3=true;this.logDebug("blurring due to zIndex change","nativeFocus");this.$vo(false)}
if(_1<_2)this.$v6(_1);this.zIndex=_1;if(this.isDrawn()){if(this.useClipDiv)this.getHandle().style.zIndex=_1
this.getStyleHandle().zIndex=_1}
if(_1>_2)this.$v6(_1);if(this.hscrollbar)this.hscrollbar.moveAbove(this);if(this.vscrollbar)this.vscrollbar.moveAbove(this);if(this.clipCorners){var _4=this.$uc;if(_4.TL)_4.TL.moveAbove(this);if(_4.TR)_4.TR.moveAbove(this);if(_4.BL)_4.BL.moveAbove(this);if(_4.BR)_4.BR.moveAbove(this)}
if(_3){this.delayCall("$vn",[],0)}
this.zIndexChanged(_2,_1)}
,isc.A.$v6=function isc_Canvas__adjustSpecialPeers(_1){if(this.$sb())this.$l0.setZIndex(_1-1);if(this._backMask)this._backMask.setZIndex(_1-2);if(this._shadow)this._shadow.setZIndex(_1-3);if(this.modalMask)this.modalMask.setZIndex(_1-4)}
,isc.A.zIndexChanged=function isc_Canvas_zIndexChanged(_1,_2){if(this.children)this.children.map("parentZIndexChanged")}
,isc.A.parentZIndexChanged=function isc_Canvas_parentZIndexChanged(){if(this.children)this.children.map("parentZIndexChanged")}
,isc.A.bringToFront=function isc_Canvas_bringToFront(_1){if(isc.$cv)arguments.$cw=this;isc.Canvas.$ni+=18;this.setZIndex(isc.Canvas.$ni);if(_1&&!this.$qf())return;isc.$nd=true;this.unmask();isc.$nd=false}
,isc.A.sendToBack=function isc_Canvas_sendToBack(){isc.Canvas.$nh-=18;this.setZIndex(isc.Canvas.$nh)}
,isc.A.moveAbove=function isc_Canvas_moveAbove(_1){var z=_1.getZIndex(true);this.setZIndex(z+6)}
,isc.A.moveBelow=function isc_Canvas_moveBelow(_1){var z=_1.getZIndex(true);this.setZIndex(z-6)}
,isc.A.getContents=function isc_Canvas_getContents(){var _1=(isc.isA.Function(this.contents)?this.contents():this.contents);return this.dynamicContents?_1.evalDynamicString(this,this.dynamicContentsVars):_1}
,isc.A.setContents=function isc_Canvas_setContents(_1){if(_1!=null)this.contents=_1;this.markForRedraw("setContents")}
,isc.A.containsIFrame=function isc_Canvas_containsIFrame(){return this.contentsURL!=null&&this.contentsType=="page"}
,isc.A.getContentsURL=function isc_Canvas_getContentsURL(){return this.contentsURL}
,isc.A.setContentsURL=function isc_Canvas_setContentsURL(_1,_2){this.contentsURL=_1;_1=isc.Page.getURL(_1);var _3=isc.addProperties({},this.contentsURLParams,_2),_1=isc.rpc.addParamsToURL(_1,_3);if(!this.isDrawn())return;if(this.containsIFrame()){var _4=this.$sk();if(!_4||!_1)this.markForRedraw("setContentsURL");else _4.src=_1}}
,isc.A.setBackgroundColor=function isc_Canvas_setBackgroundColor(_1){if(_1)this.backgroundColor=_1;if(this.isDrawn()){return this.getStyleHandle().backgroundColor=_1}}
,isc.A.setBackgroundImage=function isc_Canvas_setBackgroundImage(_1){if(_1)this.backgroundImage=_1;if(this.isDrawn()){if(this.isDrawn())this.getStyleHandle().backgroundImage='url('+this.getImgURL(this.backgroundImage)+')'}}
,isc.A.setBorder=function isc_Canvas_setBorder(_1){this.$tk=null;if(_1!=null&&!isc.isA.String(_1)){_1=this.$63e(_1)}
if(_1==null)return;if(isc.endsWith(_1,isc.semi))_1=_1.slice(0,_1.length-1);this.border=_1;var _2=this.getStyleHandle();if(!_2)return;if(_2.border!=_1){_2.border=_1}
this.adjustOverflow("setBorder");this.innerSizeChanged("Border thickness changed")}
,isc.A.$63e=function isc_Canvas__convertBorderToString(_1){var _2=_1;if(isc.isA.Number(_1)){_1+="px solid"}else{_1=null;this.logWarn("this.border defined as "+_2+". This property should have a string value - dropping this attribute.")}
return _1}
,isc.A.getBorder=function isc_Canvas_getBorder(){return this.border}
,isc.A.setOpacity=function isc_Canvas_setOpacity(_1,_2,_3){if(!_2&&this.fadeAnimation)this.finishAnimation("fade");var _4=this.opacity;this.opacity=_1;if(this.opacity==100&&!_3&&!(this.smoothFade&&isc.Browser.isMoz))this.opacity=null;if(this.isDrawn()){if(isc.Browser.isMoz){var _5=(this.opacity!=null)?this.opacity/ 100:"";if(this.smoothFade&&(_5==1||this.opacity==null))_5=0.9999;if(this.$65q)this.getStyleHandle().MozOpacity=_5;else this.getStyleHandle().opacity=_5}else if(isc.Browser.isIE){if(!isc.Canvas.neverUseFilters||this.useOpacityFilter){this.getStyleHandle().filter=(this.opacity==null?"":"progid:DXImageTransform.Microsoft.Alpha(opacity="+this.opacity+")")}}else{var _5=(this.opacity!=null)?this.opacity/ 100:"";this.getStyleHandle().opacity=_5}}
this.$v7(_1,_2,_3||_1!=null);if(isc.Browser.isIE&&this.fixIEOpacity&&this.children){for(var i=0;i<this.children.length;i++){var _7=this.children[i];if(_7.opacity==null&&(_3||_1!=null)){_7.setOpacity(100,_2,true)}else if(_7.opacity==100){_7.setOpacity(null)}}}
this.opacityChanged(_1,_2)}
,isc.A.opacityChanged=function isc_Canvas_opacityChanged(_1,_2){}
,isc.A.$v7=function isc_Canvas__setPeersOpacity(_1,_2,_3){if(!this.peers)return;for(var i=0;i<this.peers.length;i++){if(this.peers[i].$nt){if(this.useOpacityFilter!=null){this.peers[i].useOpacityFilter=this.useOpacityFilter}
this.peers[i].setOpacity(_1,_2,_3)}else if(this.peers[i]==this.edgedCanvas&&this.edgeOpacity){if(this.useOpacityFilter!=null){this.peers[i].useOpacityFilter=this.useOpacityFilter}
var _5=Math.round(this.opacity*(this.edgeOpacity*.01));this.peers[i].setOpacity(_5,_2,_3)}}}
,isc.A.setPrompt=function isc_Canvas_setPrompt(_1){this.prompt=_1;this.updateHover()}
,isc.A.setCursor=function isc_Canvas_setCursor(_1){if(_1&&_1!=this.cursor){this.cursor=_1;this.$k2()}}
,isc.A.$v8=function isc_Canvas__applyCursor(_1){if(this.isDrawn()){if((isc.Browser.isMoz||(isc.Browser.isStrict&&isc.Browser.isSafari))&&_1=="hand")_1=isc.Canvas.HAND;this.$v9=_1;this.getStyleHandle().cursor=_1;if(this.useClipDiv)this.getHandle().style.cursor=_1;if(this.$kt){for(var i=0;i<this.$kt.length;i++){this.$kt[i].$v8(_1)}}
if(this.ns.EH.$wa&&(this==this.ns.EH.getTarget())){this.ns.EH.$wa.setCursor(_1)}}
if(isc.Browser.isOpera&&isc.EH.lastEvent.target==this)this.markForRedraw()}
,isc.A.$k2=function isc_Canvas__updateCursor(){var _1=this.getCurrentCursor();if(this.$v9==_1)return;this.$v8(_1)}
,isc.A.getCurrentCursor=function isc_Canvas_getCurrentCursor(){var _1=this.cursor;if(isc.EH.dragging&&this.$uh&&(isc.EH.dragMoveTarget!=this)){_1=this.noDropCursor}else if(this.isDisabled())_1=this.disabledCursor;else{var _2;if(this.canDragResize&&this.edgeCursorMap){var _3=this.getEventEdge();if(_3&&this.edgeCursorMap[_3]){_1=this.edgeCursorMap[_3];_2=true}}
if(!_2&&this.canDragReposition&&this.dragRepositionCursor){_1=this.dragRepositionCursor}}
return _1}
,isc.A.getHoverTarget=function isc_Canvas_getHoverTarget(_1,_2){var _3=this;while(_3){if(_3.getCanHover()==null){if(_3.prompt!=null)return _3;_3=_3.parentElement}else if(_3.getCanHover()){return _3}else{return null}}
return null}
,isc.A.startHover=function isc_Canvas_startHover(_1){isc.Hover.setAction(this,this.$wb,null,this.hoverDelay)}
,isc.A.stopHover=function isc_Canvas_stopHover(_1){isc.Hover.clear()}
,isc.A.$wb=function isc_Canvas__handleHover(){var _1=isc.EH,_2=_1.lastMoveTarget;var _3=_1.lastEvent;if(!_2||_2.getHoverTarget(_3)!=this)return;return this.handleHover()}
,isc.A.getCanHover=function isc_Canvas_getCanHover(){return this.canHover}
,isc.A.getHoverComponent=function isc_Canvas_getHoverComponent(){}
,isc.A.handleHover=function isc_Canvas_handleHover(){if(this.hover&&this.hover()==false)return;if(this.showHover){var _1=this.showHoverComponents&&this.getHoverComponent?this.getHoverComponent():null;if(_1!=null&&isc.isA.Canvas(_1)){var _2=this.$wc();isc.Hover.show(_1,_2,null,this)}else{var _3=this.getHoverHTML();if(_3!=null&&!isc.isAn.emptyString(_3)){var _2=this.$wc();isc.Hover.show(_3,_2,null,this)}}}}
);isc.evalBoundary;isc.B.push(isc.A.updateHover=function isc_Canvas_updateHover(_1){if(isc.Hover.lastHoverCanvas!=this||!isc.Hover.hoverCanvas.isVisible())return;if(_1==null)_1=this.getHoverHTML();isc.Hover.show(_1,this.$wc(),null,this)}
,isc.A.$80l=function isc_Canvas__hoverHidden(){if(this.hoverCanvas&&this.hoverCanvas.hoverAutoDestroy!=false){this.hoverCanvas.markForDestroy();this.hoverCanvas=null;delete this.hoverCanvas}
this.hoverHidden()}
,isc.A.hoverHidden=function isc_Canvas_hoverHidden(){}
,isc.A.$wc=function isc_Canvas__getHoverProperties(){var _1=isc.EH.getTarget(),_2=isc.isA.CanvasItem(_1)?_1:_1.canvasItem,_3;if(_2){_3={width:(_2.hoverWidth!=null?_2.hoverWidth:this.hoverWidth),height:(_2.hoverHeight!=null?_2.hoverHeight:this.hoverHeight),align:(_2.hoverAlign!=null?_2.hoverAlign:this.hoverAlign),valign:(_2.hoverVAlign!=null?_2.hoverVAlign:this.hoverVAlign),baseStyle:(_2.hoverStyle!=null?_2.hoverStyle:this.hoverStyle),opacity:(_2.hoverOpacity!=null?_2.hoverOpacity:this.hoverOpacity),moveWithMouse:(_2.hoverMoveWithMouse!=null?_2.hoverMoveWithMouse:this.hoverMoveWithMouse),wrap:(_2.hoverWrap!=null?_2.hoverWrap:this.hoverWrap)};return _3}
return{width:this.hoverWidth,height:this.hoverHeight,align:this.hoverAlign,valign:this.hoverVAlign,baseStyle:this.hoverStyle,opacity:this.hoverOpacity,moveWithMouse:this.hoverMoveWithMouse,wrap:this.hoverWrap}}
,isc.A.getHoverHTML=function isc_Canvas_getHoverHTML(){return this.prompt}
,isc.A.setClassName=function isc_Canvas_setClassName(_1){if(this.logIsInfoEnabled(this.$pj)){this.logInfo("call to deprecated setClassName() property - use setStyleName() instead")}
return this.setStyleName(_1)}
,isc.A.setStyleName=function isc_Canvas_setStyleName(_1){this.$tk=null;this.$tp=null;this.$81b();if(_1){this.styleName=_1;this.className=_1}
if(this.getClipHandle())this.getClipHandle().className=this.styleName;if(this.overflow!=isc.Canvas.HIDDEN)this.adjustOverflow("setStyleName")}
,isc.A.getStateName=function isc_Canvas_getStateName(){var _1=this.getClipHandle().className;return(_1!=null?_1:this.styleName)}
,isc.A.handleShowContextMenu=function isc_Canvas_handleShowContextMenu(_1){if(_1.target==this&&this.useEventParts){var _2=this.getEventPart(_1);if(_2.part){if(this.$wd(_2.part,"showContextMenu",_2.element,_2.ID,_1)==false)return false}}
if(this.showContextMenu)return this.showContextMenu(_1)}
,isc.A.showContextMenu=function isc_Canvas_showContextMenu(){var _1=this.contextMenu;if(_1){_1.target=this;if(!isc.isA.Canvas(_1)){_1.autoDraw=false;this.contextMenu=_1=this.getMenuConstructor().create(_1)}
_1.showContextMenu()}
return(_1==null)}
,isc.A.getMenuConstructor=function isc_Canvas_getMenuConstructor(){var _1=isc.ClassFactory.getClass(this.menuConstructor);if(!_1){isc.logWarn("Class not found for menuConstructor:"+this.menuConstructor+". Defaulting to isc.Menu class");_1=isc.ClassFactory.getClass("Menu")}
return _1}
,isc.A.hideContextMenu=function isc_Canvas_hideContextMenu(){if(this.contextMenu)this.contextMenu.hideContextMenu()}
,isc.A.$kr=function isc_Canvas__allowNativeTextSelection(_1){return this.canSelectText}
,isc.A.handleMouseMove=function isc_Canvas_handleMouseMove(_1,_2){if(_1.target==this&&this.useEventParts){var _3=this.getEventPart(_1),_4=this.$nw;if(_4&&_4.part&&(_4.part!=_3.part||_4.ID!=_3.ID))
{this.$wd(_4.part,isc.EH.MOUSE_OUT,_4.element,_4.ID,_1)}
if(_3.part){var _5=!_4||(_4.ID!=_3.ID),_6=(_5?isc.EH.MOUSE_OVER:isc.EH.MOUSE_MOVE);this.$wd(_3.part,_6,_3.element,_3.ID,_1);if(_5){isc.Hover.setAction(this,this.$we,[_3.element,_3.ID],this.hoverDelay)}}
this.$nw=_3}
if(this.mouseMove)return this.mouseMove(_1,_2)}
,isc.A.$we=function isc_Canvas__handleRectHover(_1,_2){if(this.$nw)this.$wd(this.$nw.part,"hover",_1,_2)}
,isc.A.handleMouseOut=function isc_Canvas_handleMouseOut(_1,_2){if(_1.target==this&&this.useEventParts){var _3=this.$nw;if(_3&&_3.part){this.$wd(_3.part,isc.EH.MOUSE_OUT,_3.element,_3.ID,_1)}}
if(this.mouseOut)return this.mouseOut(_1,_2)}
,isc.A.handleMouseDown=function isc_Canvas_handleMouseDown(_1,_2){var _3=this.$772;if(_3!=null){this.cancelAnimation(_3)}
if(_1.target==this&&this.useEventParts)this.firePartEvent(_1,isc.EH.MOUSE_DOWN);if(this.mouseDown)return this.mouseDown(_1,_2)}
,isc.A.handleMouseUp=function isc_Canvas_handleMouseUp(_1,_2){if(_1.target==this&&this.useEventParts)this.firePartEvent(_1,isc.EH.MOUSE_UP);if(this.mouseUp)return this.mouseUp(_1,_2)}
,isc.A.handleClick=function isc_Canvas_handleClick(_1,_2){if(_1.target==this&&this.useEventParts)this.firePartEvent(_1,isc.EH.CLICK);if(this.click)return this.click(_1,_2)}
,isc.A.handleDoubleClick=function isc_Canvas_handleDoubleClick(_1,_2){if(_1.target==this&&this.useEventParts)this.firePartEvent(_1,isc.EH.DOUBLE_CLICK);if(this.doubleClick)return this.doubleClick(_1,_2)}
,isc.A.handleLongTouch=function isc_Canvas_handleLongTouch(_1,_2){return this.handleShowContextMenu(_1,_2)}
,isc.A.getEventPart=function isc_Canvas_getEventPart(_1){if(!_1)_1=isc.EH.lastEvent;var _2=_1.nativeTarget;return this.getElementPart(_2)}
,isc.A.getElementPart=function isc_Canvas_getElementPart(_1){var _2,_3;if(_1&&_1.getAttribute)_2=_1.getAttribute(this.$pk);if(_2&&_2!=isc.emptyString){var _4=_1.id;if(_4&&_4!=isc.emptyString){_3=_4.substring(this.getID().length+_2.length+2)}}
return{part:_2,ID:_3,element:_1}}
,isc.A.getPartElement=function isc_Canvas_getPartElement(_1){var _2=_1.part,_3=_1.partID,_4=this.getID+"_"+_2;if(_3)_4+=_3;var _5=isc.Element.get(_4);if(_5)return _5;return isc.Element.findAttribute(this.getHandle(),this.$pk,_2)}
,isc.A.firePartEvent=function isc_Canvas_firePartEvent(_1,_2){if(!this.useEventParts||!_1)return;var _3=this.getEventPart(_1);if(!_3.part)return;if(!_2)_2=_1.eventType;return this.$wd(_3.part,_2,_3.element,_3.ID,_1)}
,isc.A.$wd=function isc_Canvas__firePartEvent(_1,_2,_3,_4,_5){var _6=this.getPartEventHandler(_1,_2);if(this[_6]){return this[_6](_3,_4,_5)}}
,isc.A.getPartEventHandler=function isc_Canvas_getPartEventHandler(_1,_2){if(!isc.Canvas.$np[_1])isc.Canvas.$np[_1]={};if(!isc.Canvas.$np[_1][_2]){var _3=_2.substring(0,1).toUpperCase()+_2.substring(1);isc.Canvas.$np[_1][_2]=_1+_3}
return isc.Canvas.$np[_1][_2]}
,isc.A.getDragType=function isc_Canvas_getDragType(){return this.dragType}
,isc.A.willAcceptDrop=function isc_Canvas_willAcceptDrop(){if(this.ns.EH.dragTarget==null)return false;if(this.dropTypes==isc.Canvas.ANYTHING||this.dropTypes==null||isc.is.emptyString(this.dropTypes))
{return true}
var _1=this.ns.EH.dragTarget.getDragType();if(_1==null||isc.is.emptyString(_1))return false;if(isc.isA.String(_1)){return this.dropTypes.contains(_1)}else if(isc.isAn.Array(_1)){for(var i=0,_3=true,_4=_1.length;i<_4&&_3;i++){_3=_3&&(this.dropTypes.contains(_1))}
return _3}
return false}
,isc.A.$mb=function isc_Canvas__showDragMask(){if(this._eventMask.visibility==isc.Canvas.HIDDEN)this._eventMask.show()}
,isc.A.$mc=function isc_Canvas__hideDragMask(){if(this._eventMask.visibility!=isc.Canvas.HIDDEN)this._eventMask.hide()}
,isc.A.handleDrop=function isc_Canvas_handleDrop(_1,_2){if(this.onDrop!=null&&(this.onDrop()==false))return false;return this.drop(_1,_2)}
,isc.A.getHSnapPosition=function isc_Canvas_getHSnapPosition(_1,_2){if(!_2){_2=this.snapHDirection}
if(_2!=isc.Canvas.BEFORE&&_2!=isc.Canvas.AFTER&&_2!=isc.Canvas.NEAREST){return _1}
var _3=Math.floor(_1/ this.snapHGap)*this.snapHGap;var _4=_3+this.snapHGap;var _5=_3+this.snapHGap/ 2;if(_2==isc.Canvas.BEFORE){return _3}else if(_2==isc.Canvas.AFTER){return _4}else{if(_1<=_5)return _3;else return _4}}
,isc.A.getVSnapPosition=function isc_Canvas_getVSnapPosition(_1,_2){if(!_2){_2=this.snapVDirection}
if(_2!=isc.Canvas.BEFORE&&_2!=isc.Canvas.AFTER&&_2!=isc.Canvas.NEAREST){return _1}
var _3=Math.floor(_1/ this.snapVGap)*this.snapVGap;var _4=_3+this.snapVGap;var _5=_3+this.snapVGap/ 2;if(_2==isc.Canvas.BEFORE){return _3}else if(_2==isc.Canvas.AFTER){return _4}else{if(_1<=_5)return _3;else return _4}}
,isc.A.shouldSnapOnDrop=function isc_Canvas_shouldSnapOnDrop(_1){return true}
,isc.A.noSnapDragOffset=function isc_Canvas_noSnapDragOffset(_1){return false}
,isc.A.setAppImgDir=function isc_Canvas_setAppImgDir(_1){if(_1)this.appImgDir=_1}
,isc.A.getAppImgDir=function isc_Canvas_getAppImgDir(){return isc.Page.getImgURL("",this.appImgDir)}
,isc.A.setSkinImgDir=function isc_Canvas_setSkinImgDir(_1){if(_1)this.skinImgDir=_1}
,isc.A.getSkinImgDir=function isc_Canvas_getSkinImgDir(){return isc.Page.getSkinImgDir(this.skinImgDir)}
,isc.A.getImgURL=function isc_Canvas_getImgURL(_1,_2){return isc.Canvas.getImgURL(_1,_2,this)}
,isc.A.imgHTML=function isc_Canvas_imgHTML(_1,_2,_3,_4,_5,_6,_7){return isc.Canvas.imgHTML(_1,_2,_3,_4,_5,_6,_7,this)}
,isc.A.$wf=function isc_Canvas__getImgHTMLTemplate(_1,_2,_3,_4,_5,_6,_7){return isc.Canvas.imgHTML(_1,_2,_3,_4,_5,_6,_7,this,true)}
,isc.A.getImage=function isc_Canvas_getImage(_1){if(isc.isA.String(_1))_1=this.getCanvasName()+_1;var _2=this.getHandle();if(_2){if(isc.Page.isXHTML()){return document.getElementById(_1)}else{if(_2.document){return _2.document.images[_1]}else{return document.images[_1]}}}
return null}
,isc.A.setImage=function isc_Canvas_setImage(_1,_2,_3){var _4=this.getImage(_1);if(_4==null){this.logWarn("setImage: image '"+_1+"' couldn't be found");return}
isc.Canvas.$wg(_4,_2,_3,this)}
,isc.A.linkHTML=function isc_Canvas_linkHTML(_1,_2,_3,_4,_5,_6,_7){return isc.Canvas.linkHTML(_1,_2,_3,_4,_5,_6,_7)}
,isc.A.inWhichPosition=function isc_Canvas_inWhichPosition(_1,_2,_3){if(!_1||_2<0)return-1;if(_3==isc.Page.RTL){var _4=_1.sum();for(var c=0,_6=_1.length;c<_6;c++){if(_2>=_4-_1[c])return c;_4-=_1[c]}}else{for(var c=0,_6=_1.length;c<_6;c++){if(_2<=_1[c]){return c}
_2-=_1[c]}}
return-2}
,isc.A._canvasList=function isc_Canvas__canvasList(_1){var _2=isc.Canvas._canvasList;if(_1)_2.add(this);else _2.remove(this);if(this.$ih){isc.Canvas._iscInternalCount+=(_1?1:-1)}else{isc.Log.updateStats(this.$f8)}}
,isc.A.$p6=function isc_Canvas__addStat(_1){if(!this.$ih){isc.Canvas._stats[_1]++;isc.Log.updateStats(_1)}}
,isc.A.$wh=function isc_Canvas__attachedPeers(_1){var _2=this.$sc;if(!_2)return null;if(_1)return _2[_1]}
,isc.A.$wi=function isc_Canvas__registerAttachedPeer(_1,_2,_3,_4){if(_1==null||_2==null)return;if(!this.$sc)this.$sc={};if(!this.$sc[_2])this.$sc[_2]=[];this.$sc[_2].add(_1);if(_3!=null)_1.$55s=_3
if(_4){this.observe(_1,"resized","observer.$88y(observed)")}
delete this.$s7;delete this.$s8}
,isc.A.$wj=function isc_Canvas__unRegisterAttachedPeer(_1,_2,_3){if(_1==null||_2==null)return;if(!this.$sc||!this.$sc[_2])return;this.$sc[_2].remove(_1);if(this.isObserving(_1,"resized")){this.ignore(_1,"resized")}
delete _1.$55s;delete this.$s7;delete this.$s8}
,isc.A.$88y=function isc_Canvas__attachedPeerResized(_1){this.refreshMargin()}
,isc.A.refreshMargin=function isc_Canvas_refreshMargin(){this.setMargin(this.margin)}
,isc.A.$ps=function isc_Canvas__makeCornerClips(){this.$uc={};for(var i=0;i<this.clippedCorners.length;i++){this.$wk(this.clippedCorners[i])}}
,isc.A.$wk=function isc_Canvas__makeCornerClip(_1){var _2=this.$uc,_3=this.left,_4=this.top,_5=this.cornerClipWidth||this.cornerClipSize,_6=this.cornerClipHeight||this.cornerClipSize;if(_1=="TR"||_1=="BR"){_3=_3+this.getWidth()-_5}
if(_1=="BL"||_1=="BR"){_4=_4+this.getHeight()-_6}
if(this.noCornerClipImages&&!(isc.Browser.isIE&&isc.Browser.minorVersion>=5.5)){this.noCornerClipImages=false}
var _7=_2[_1]=isc.ClassFactory.newInstance({_constructor:(this.noCornerClipImages?"Canvas":"Img"),left:_3,top:_4,width:_5,height:_6,eventProxy:this,src:(this.noCornerClipImages?null:this.$wl(_1)),contents:(this.noCornerClipImages?this.$wm(_5,_6,_1):null)},this.$nv);this.addPeer(_7);_7.moveAbove(this)}
,isc.A.$qg=function isc_Canvas__finishCornerClips(){if(!this.noCornerClipImages)return;for(var _1 in this.$uc){var _2=this.$uc[_1],_3=_2.getHandle().firstChild,_4=_3.style;_3.filters[0].apply();_4.visibility="hidden";_3.filters[0].percent=71}}
,isc.A.$wl=function isc_Canvas__getCornerImage(_1){return isc.Img.urlForState(this.cornerClipImage,null,null,this.cornerClipColor,_1)}
,isc.A.$wm=function isc_Canvas__getCornerHTML(_1,_2,_3){var _4=isc.SB.create();_4.append("<DIV STYLE='width:",2*_1,"px;height:",2*_2,"px;filter:progid:DXImageTransform.Microsoft.iris(irisStyle=circle,motion=out);");if(_3.contains("R"))_4.append("margin-left:",-_1,"px;");if(_3.contains("B"))_4.append("margin-top:",-_2,"px;");_4.append("'><DIV STYLE='overflow:hidden;width:",_1,"px;height:",_2,"px;background-color:",this.cornerClipColor,";");if(_3.contains("R"))_4.append("margin-left:",_1,"px;");if(_3.contains("B"))_4.append("margin-top:",_2,"px;");_4.append("'></DIV></DIV>");return _4.toString()}
,isc.A.$sb=function isc_Canvas__edgesAsPeer(){return this.showEdges&&!this.edgesAsChild}
,isc.A.$pq=function isc_Canvas__createEdges(){if(!this.showEdges||isc.isA.EdgedCanvas(this)||this.$l0!=null){return this.$l0}
var _1=this.$l0=this.$wn();if(this.edgesAsChild){_1.resizeTo("100%","100%");_1.sendToBack();this.addChild(_1)}else{this.addPeer(_1)}
return _1}
,isc.A.setEdgeOpacity=function isc_Canvas_setEdgeOpacity(_1){var _2=this.edgeOpacity=_1;if(this.opacity>0&&this.opacity<100){_2=this.opacity*(this.edgeOpacity/ 100)}
this.$l0.setOpacity(_2)}
,isc.A.$wn=function isc_Canvas__createEdgedCanvas(){var _1=this.$pl,_2=isc.EdgedCanvas.createRaw();_2.autoDraw=false;_2._generated=true;_2.containedPeer=true;_2.dragTarget=this;_2.visibility=this.visibility;_2.opacity=this.opacity;_2.useOpacityFilter=this.useOpacityFilter;if(this.edgeOpacity!=null){_2.opacity=this.edgeOpacity;_2.$nt=false}
_2.smoothFade=this.smoothFade;if(this.edgeOverflow!=null)_2.overflow=this.edgeOverflow;_2.eventProxy=this;for(var i=0;i<_1.length;i++){var _4=_1[i];if(this[_4]!=null)_2[_4]=this[_4]}
if(this.edgeBackgroundColor)_2.backgroundColor=this.edgeBackgroundColor;if(this.edgeCenterBackgroundColor){_2.centerBackgroundColor=this.edgeCenterBackgroundColor}
if(this.edgeShowCenter!=null)_2.showCenter=this.edgeShowCenter;if(!this.edgesAsChild)_2.zIndex=this.getZIndex(true)-1;_2.completeCreation();return _2}
,isc.A.setShowShadow=function isc_Canvas_setShowShadow(_1){this.showShadow=_1;if(_1){if(!this._shadow)this.$pr();else if(this.isDrawn())this._shadow.show()}else{if(this._shadow)this._shadow.hide()}}
,isc.A.$pr=function isc_Canvas__createShadow(){var _1=this._shadow=this.createAutoChild("shadow",{visibility:this.visibility,zIndex:this.getZIndex(true)-3},isc.DropShadow);this.updateShadow(true);this.addPeer(_1);_1.moveBelow(this)}
,isc.A.updateShadow=function isc_Canvas_updateShadow(_1){if(!_1)this.setShowShadow(this.showShadow);var _2=this._shadow;if(!_2)return;_2.offset=this.shadowOffset;_2.offsetX=this.shadowOffsetX;_2.offsetY=this.shadowOffsetY;_2.softness=this.shadowSoftness;if(this.shadowImage)_2.setEdgeImage(this.shadowImage);_2.setDepth(this.shadowDepth);if(this.dragResizeFromShadow&&this.canDragResize){_2.canDragResize=this.canDragResize;_2.resizeFrom=this.resizeFrom;_2.dragTarget=this}}
,isc.A.propertyChanged=function isc_Canvas_propertyChanged(_1,_2){if(isc.contains(_1,this.$pm)&&this.updateShadow)this.updateShadow()}
,isc.A.setIsGroup=function isc_Canvas_setIsGroup(_1){if(_1==this.isGroup)return;var _2=this.shouldShowGroupLabel()&&this.isDrawn();if(_2)this.clear();if(_1){this.$55t=this.border;this.setBorder(this.groupBorderCSS);if(this.shouldShowGroupLabel())this.$55u()}else{this.setBorder(this.$55t||"");if(this.shouldShowGroupLabel())this.$55v()}
this.isGroup=_1;if(_2)this.draw()}
,isc.A.shouldShowGroupLabel=function isc_Canvas_shouldShowGroupLabel(){return this.showGroupLabel}
,isc.A.makeGroupLabel=function isc_Canvas_makeGroupLabel(){if(!this.groupLabel){var _1={autoDraw:false,$jo:false,$ns:true,backgroundColor:this.getGroupLabelBackgroundColor(),eventProxy:this,styleName:this.groupLabelStyleName}
if(this.groupTitle!=null)_1.contents=this.groupTitle;this.groupLabel=this.createAutoChild("groupLabel",_1)}else{if(this.groupTitle!=null)this.groupLabel.setContents(this.groupTitle);this.groupLabel.setBackgroundColor(this.getGroupLabelBackgroundColor())}}
,isc.A.getGroupLabelBackgroundColor=function isc_Canvas_getGroupLabelBackgroundColor(){if(this.groupLabelBackgroundColor)return this.groupLabelBackgroundColor;if(this.backgroundColor!=null)return this.backgroundColor;return"white"}
,isc.A.$55u=function isc_Canvas__showGroupLabel(){this.makeGroupLabel();var _1=this.groupLabel;var _2;if(_1.overflow==isc.Canvas.VISIBLE){if(_1.parentElement!=null)_1.deparent();_1.setTop(-1000);_1.draw();_2=_1.getVisibleHeight()}else{_2=_1.getVisibleHeight()}
var _3=Math.round(_2/ 2);this.$wi(_1,isc.Canvas.TOP,_3);var _4=_2-_3;if(this.padding)_4+=this.padding;this.setTopPadding(_4);_1.setLeft(this.getLeft()+this.groupLabelPadding);_1.setTop(this.getTop());if(_1.masterElement!=this)this.addPeer(_1);if(this.isDrawn()){if(!_1.isDrawn())_1.draw()}
this.getTopMargin();_1.moveAbove(this);if(_1.isDrawn()&&!this.isDrawn())_1.clear()}
,isc.A.$55v=function isc_Canvas__hideGroupLabel(){if(!this.groupLabel)return;var _1=this.groupLabel;this.$wj(_1,isc.Canvas.TOP);this.setTopPadding(null);_1.clear();_1.depeer()}
,isc.A.setGroupTitle=function isc_Canvas_setGroupTitle(_1){this.groupTitle=_1;if(this.groupLabel){this.groupLabel.setContents(this.groupTitle)}else{this.$55u()}}
);isc.B._maxIndex=isc.C+544;isc.A=isc.Canvas;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$hm="[SKIN]";isc.A.printOmitControls=["Button","StretchImgButton","ImgButton","MenuButton","Toolbar","ToolStrip","ButtonItem","ToolbarItem"];isc.A.printIncludeControls=["Label"];isc.A.$ws=0;isc.A.$wt="ID='";isc.A.$ob="'";isc.A.$wu="absmiddle";isc.A.$wv=[,,," eventpart='valueicon' style='vertical-align:middle;margin-left:",,"px;margin-right:",,"px;'"];isc.A.$ww={};isc.A.$wx={png:true,PNG:true,Png:true};isc.A.$wy=["<a",," href='",,"' target='",,"'",,,,">",,"</a>"];isc.A.$wz="[SKINIMG]/blank.gif";isc.A.$w0="0";isc.A.$w1="clearRedrawQueue";isc.A.$65j=[];isc.A.$65k=0;isc.A.$65l="clearDestroyQueue";isc.A.$tw=[];isc.B.push(isc.A.stripScriptTags=function isc_c_Canvas_stripScriptTags(_1){return _1.replace(/<script([^>]*)?>(.|\n|\r)*?<\/script>/ig,isc.emptyString)}
,isc.A.stripLinkTags=function isc_c_Canvas_stripLinkTags(_1){return _1.replace(/<link([^>]*)?>/ig,isc.emptyString)}
,isc.A.getById=function isc_c_Canvas_getById(_1){var _2=window[_1]||null;return _2?(isc.isA.Canvas(_2)?_2:null):null}
,isc.A.getNextZIndex=function isc_c_Canvas_getNextZIndex(){return(isc.Canvas.$ng+=18)}
,isc.A.getFocusProxyString=function isc_c_Canvas_getFocusProxyString(_1,_2,_3,_4,_5,_6,_7,_8,_9,_10,_11,_12,_13,_14,_15,_16){if(this.$w8==null){this.$w9="' ONFOCUS=";this.$xa="' ";this.$xb=" ONBLUR=";this.$w8=["<div"," id='",null,"$xc'"+" style='overflow:hidden;width:0px;height:0px;position:",,";left:",null,"px;top:",null,"px;'>",(isc.Browser.isSafari?"<textarea":(isc.Browser.isMoz&&isc.Browser.geckoVersion>=20051111?"<div":"<button onclick='event.cancelBubble=true;return false;'"))," id='",null,"__focusProxy'"," style='VISIBILITY:",null,"left:1px;top:1px;"+"width:",(isc.Browser.isSafari?"1":null),"px;height:",(isc.Browser.isSafari?"1":null),"px;",null,this.$w9,null,this.$xb,null,null,null,null," tabindex='",null,null,"' focusProxy='true' handleNativeEvents='",null,"'>",(isc.Browser.isSafari?"</textarea>":(isc.Browser.isMoz&&isc.Browser.geckoVersion>=20051111?"</div>":"</button>")),"</div>"]}
var _17=this.$w8;_17[2]=_1;_17[4]=(_2?"absolute":"inline");_17[6]=_3;_17[8]=_4;_17[12]=_1;_17[15]=(_7?"visible;":"hidden;");_17[17]=_5;_17[19]=_6;if(isc.Browser.isMoz){if(!_8||_9==-1)_17[21]="-moz-user-focus:ignore;";else _17[21]="-moz-user-focus:normal;"}
if(_12&&_12!=isc.emptyString){_17[22]=this.$w9;_17[23]=_12}else{_17[22]=this.$xa;_17[23]=null}
if(_13&&_13!=isc.emptyString){_17[24]=this.$xb;_17[25]=_13}else{_17[24]=null;_17[25]=null}
_17[26]=(_14!=null?" onkeydown="+_14:null);_17[27]=(_15!=null?" onkeypress="+_15:null);_17[28]=(_16!=null?" onkeyup="+_16:null);_17[30]=(_8?_9:-1);_17[31]=(_8&&_10?"' accesskey='"+_10:null);_17[33]=(_11?true:false);return _17.join(isc.$ad)}
,isc.A.showAllocatedTabChain=function isc_c_Canvas_showAllocatedTabChain(){var _1=isc.EH.$kj,_2=isc.EH.$kl;var _3="First tab widget:"+_1+", and last:"+_2+"\nFull chain:";var _4=_1;do{_3+="\n\t"+_4.getID()+" - "+_4.getTabIndex()+" -->";_4=_4.$v4}while(_4!=null&&_4!=_2)
this.logWarn(_3)}
,isc.A.clearCSSCaches=function isc_c_Canvas_clearCSSCaches(){isc.Element.$50f();var _1=isc.Canvas._canvasList;for(var i=0;i<_1.length;i++){var _3=_1[i];if(_3==null||_3.destroyed)continue;_3.$s8=_3.$s7=_3.$tk=_3.$tp=null}}
,isc.A.setAppImgDir=function isc_c_Canvas_setAppImgDir(_1){this.getPrototype().appImgDir=_1}
,isc.A.getAppImgDir=function isc_c_Canvas_getAppImgDir(){return isc.Page.getImgURL(isc.emptyString,this.getPrototype().appImgDir)}
,isc.A.setSkinImgDir=function isc_c_Canvas_setSkinImgDir(_1){this.getPrototype().skinImgDir=_1}
,isc.A.getSkinImgDir=function isc_c_Canvas_getSkinImgDir(){return isc.Page.getSkinImgDir(this.getPrototype().skinImgDir)}
,isc.A.getImgURL=function isc_c_Canvas_getImgURL(_1,_2,_3){if(_1==null||isc.isAn.emptyString(_1))return isc.$ad;_3=_3||this.getPrototype();if(_1.imgDir!=null&&_2==null)_2=_1.imgDir;if(_1.src!=null)_1=_1.src;if(_2==null){_2=(isc.startsWith(_1,this.$hm)?_3.skinImgDir:_3.appImgDir)}
var _4=isc.Page.getImgURL(_1,_2);return _4}
,isc.A.setShowCustomScrollbars=function isc_c_Canvas_setShowCustomScrollbars(_1){isc.Canvas.addProperties({showCustomScrollbars:_1})}
,isc.A.getPrintHTML=function isc_c_Canvas_getPrintHTML(_1,_2,_3,_4,_5,_6){if(!isc.isAn.Array(_1))_1=[_1];if(_5==null)_5=[];if(_6==null)_6=0;var _7,_8={target:this,methodName:"gotComponentPrintHTML",components:_1,printProperties:_2,callback:_3,HTML:_5,index:_6,separator:_4};for(;_6<_1.length;_6++){_8.index+=1;var _9=_1[_6];var _10;if(isc.isA.String(_9))_10=_9;else _10=_9.getPrintHTML(_2,_8);if(_10!=null){_5.add(_10)}else{_7=true;break}}
if(_7){if(!_3){this.logWarn("getPrintHTML(): HTML generated asynchronously, but no callback passed in")}
return null}
if(_3){this.fireCallback(_3,"HTML,callback",[_5.join(_4||isc.emptyString),_3])}
return _5.join(_4||isc.emptyString)}
,isc.A.gotComponentPrintHTML=function isc_c_Canvas_gotComponentPrintHTML(_1,_2){_2.HTML.add(_1);this.getPrintHTML(_2.components,_2.printProperties,_2.callback,_2.separator,_2.HTML,_2.index)}
,isc.A.getImgHTML=function isc_c_Canvas_getImgHTML(_1,_2,_3,_4,_5,_6,_7,_8,_9){return this.imgHTML(_1,_2,_3,_4,_5,_6,_7,_8,_9)}
,isc.A.$wf=function isc_c_Canvas__getImgHTMLTemplate(_1,_2,_3,_4,_5,_6,_7){return isc.Canvas.imgHTML(_1,_2,_3,_4,_5,_6,_7,null,true)}
,isc.A.imgHTML=function isc_c_Canvas_imgHTML(_1,_2,_3,_4,_5,_6,_7,_8,_9){var _10;if(isc.isAn.Object(_1)){if(_1.width!=null)_2=_1.width;if(_1.height!=null)_3=_1.height;if(_1.name!=null)_4=_1.name;if(_1.extraStuff!=null)_5=_1.extraStuff;if(_1.imgDir!=null)_6=_1.imgDir;if(_1.align!=null)_10=_1.align;if(_1.activeAreaHTML!=null)_7=_1.activeAreaHTML;_1=_1.src}
if(_1==null||isc.isAn.emptyString(_1)){return(_9?[isc.$ad]:isc.$ad)}
var _11=this.$xd;if(!_11){this.$xe="<img src='";this.$xf="' width='";this.$xg="' height='";this.$xh="' align='";this.$xi=(isc.Page.isXHTML()?"' id='":"' name='");this.$xj="' ";this.$xk=isc.Browser.isOpera?"middle":"TEXTTOP";this.$xl=" border='0' suppress='TRUE'/>";this.$xd=_11=[this.$xe];this.$xm="' style='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\"";this.$xn="\",sizingMethod=\"scale\");"}
if(_10==null)_10=this.$xk;if(!this.$hu)this.$hu=this.getImgURL("[SKIN]/blank.gif");if(isc.Browser.isSafari&&(_2>32000||_3>32000)){this.logWarn("Attempting to draw an image of size "+_2+" x "+_3+".  Images larger than 32000 pixels in either direction are not reliably "+" rendered in this browser.")}
var _12=this.getImgURL(_1,_6,_8);if(_9)_11=[this.$xe];if(isc.Page.isXHTML())_12=isc.makeXMLSafe(_12);if(isc.screenReader){if(_5==null||!isc.contains(_5,"alt=")){_11[0]="<img role='presentation' src='"}else{_11[0]=this.$xe}}
if(!this.$xo(_8)||!this.$xp(_1)){_11[1]=_12}else{_11[1]=this.$hu;_11[3]=this.$xm;_11[4]=_12;_11[5]=this.$xn;if(_2==null)_2=16;if(_3==null)_3=16}
if(_2){_11[6]=this.$xf;_11[7]=_2}
if(_3){_11[8]=this.$xg;_11[9]=_3}
_11[10]=this.$xh;_11[11]=_10;if(_4){_11[12]=this.$xi;if(_8)_11[13]=_8.getCanvasName();_11[14]=_4}
var _13;if(_7){_13="ISC_IMGMAP_"+this.$ws++;_11[15]="' usemap='#"+_13}
_11[16]=this.$xj;if(_5){_11[17]=_5}
_11[18]=this.$xl;if(_7){_11[19]="<map name='"+_13+"'>"+_7+"</map>"}
if(_9)return _11;var _14=_11.join(isc.$ad);_11.length=3;return _14}
,isc.A.$xq=function isc_c_Canvas__getValueIconHTML(_1,_2,_3,_4,_5,_6,_7,_8){var _9=this.$wv;if(_7!=null){_9[0]=this.$wt;_9[1]=_7;_9[2]=this.$ob}else{_9[0]=_9[1]=_9[2]=null}
_9[4]=_5||0;_9[6]=_6||0;var _1=isc.Canvas.getImgURL(_1,_2,_8),_10=_9.join(isc.emptyString),_11=this.$ww;_11.src=_1;_11.width=_3
_11.height=_4
if(_4!=null&&_4<16&&(isc.Browser.isMoz||isc.Browser.isSafari)){_11.align=null}else{_11.align=this.$wu}
_11.imgDir=_2;_11.extraStuff=_10;return isc.Canvas.imgHTML(_11)}
,isc.A.$xo=function isc_c_Canvas__fixPNG(_1){if(this.usePNGFix==false)return false;var _2=isc.Browser.isIE&&isc.Browser.minorVersion>=5.5&&isc.Browser.isWin&&(!isc.Canvas.neverUseFilters&&this.neverUsePNGWorkaround!=true);if(_2&&_1&&_1.$xo&&!_1.$xo()){_2=false}
return _2}
,isc.A.$xp=function isc_c_Canvas__isPNG(_1){return(_1&&this.$wx[_1.substring(_1.lastIndexOf(isc.dot)+1)])}
,isc.A.$wg=function isc_c_Canvas__setImageURL(_1,_2,_3,_4){var _5=this.getImgURL(_2,_3,_4);if(!this.$xo(_4)){_1.src=_5}else{var _6=_1.src,_7=this.$xp(_6),_8=this.$xp(_2);if(_8){_1.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\""+_5+"\",sizingMethod=\"scale\")";if(!_7)_1.src=this.$hu}else{if(_7)_1.style.filter="";_1.src=_5}}}
,isc.A.linkHTML=function isc_c_Canvas_linkHTML(_1,_2,_3,_4,_5,_6,_7){_1=_1.replaceAll("'","\\'");if(_2==null)_2=_1;_3=_3?_3.replaceAll("'","\\'"):"_blank";var _8=this.$wy;if(_4!=null)_8[1]=" ID='"+_4+"'";else _8[1]=null;_8[3]=_1;_8[5]=_3;if(_5!=null)_8[7]=" tabIndex="+_5;else _8[7]=null;if(_6!=null)_8[8]=" accessKey='"+_6+"'";else _8[8]=null;if(_7)_8[9]=" "+_7;_8[11]=_2;return _8.join(isc.emptyString)}
,isc.A.blankImgHTML=function isc_c_Canvas_blankImgHTML(_1,_2){var _3=this.$ht;if(!_3){_3=this.$ht=this.$wf(this.$wz,1,1)}
_3[7]=_1||this.$w0;_3[9]=_2||this.$w0;return _3.join(isc.$ad)}
,isc.A.spacerHTML=function isc_c_Canvas_spacerHTML(_1,_2,_3){if(_1==0&&_2==0)return isc.$ad;if(isc.Browser.isMoz||isc.Browser.isSafari||isc.Browser.isOpera||isc.Browser.isStrict||(_2<3&&isc.Browser.isIE&&(isc.Browser.minorVersion==5.5||isc.Browser.isMac)))
{var _4;if(isc.Browser.isSafari){_4=32000}else if(isc.Browser.isFirefox&&isc.Browser.geckoVersion>=20090219){_4=17895580}else if(isc.Browser.isIE&&isc.Browser.isStrict){_4=16000}
if(_4!=null&&(_1>_4||_2>_4)){var _5=isc.SB.create(),_6=_4,_7=Math.floor(_2/ _6),_8=Math.floor(_1/ _6);_5.append("<TABLE role='presentation' CELLPADDING=0 CELLSPACING=0 BORDER=0 MARGIN=0>");for(var i=0;i<=_7;i++){_5.append("<TR>");for(var j=0;j<=_8;j++){_5.append("<TD>");var _11=((i==j)||(i>_8&&j==0)||(j>_7&&i==0));if(_11){var _12=(i<_7?_6:_2-(i*_6)),_13=(j<_8?_6:_1-(j*_6));_5.append(this.blankImgHTML(_13,_12))}
_5.append("</TD>")}
_5.append("</TR>")}
_5.append("</TABLE>");return _5.toString()}
return this.blankImgHTML(_1,_2)}
var _14=1300000;if(_2>_14){var _15=[];var _16=0;while(_16<_2){var _17,_18;if(_16+1400>=_2){_17=true;_18=_2-_16}else{_18=1400;_17=false}
_15[_15.length]=this.spacerHTML(_1,_18);_15[_15.length]="<br>";_16+=_18}
return _15.join(isc.$ad)}
var _19=this.$xs;if(_19==null){_19=this.$xs=["<SPAN STYLE='WIDTH:",null,"px;HEIGHT:",null,"px;overflow:hidden;'>",null,"</SPAN>"]}
_19[1]=_1;_19[3]=_2;_19[5]=_3?_3:isc.nbsp;return _19.join(isc.$ad)}
,isc.A.hiliteCharacter=function isc_c_Canvas_hiliteCharacter(_1,_2,_3,_4){if(!isc.isA.String(_1)||!isc.isA.String(_2)||_2.length!=1)
return _1;if(_2==" ")return _1;if(_3==null||_4==null){_3="<span style='text-decoration:underline;'>";_4="</span>"}
var _5=_1.indexOf(_2.toUpperCase());if(_5==-1)_5=_1.indexOf(_2.toLowerCase());if(_5!=-1){var _6=_1.slice(0,_5),_7=_1.slice(_5,_5+1),_8=_1.slice(_5+1);_7=_3+_7+_4;_1=_6.concat(_7,_8)}
return _1}
,isc.A.scheduleRedraw=function isc_c_Canvas_scheduleRedraw(_1){if(_1&&_1.priorityRedraw){this.$nm.addAt(_1,0)}else{this.$nm.add(_1)}
if(!this.$xu){this.$xu=isc.Timer.setTimeout({target:isc.Canvas,methodName:this.$w1},this._redrawQueueDelay)}}
,isc.A.clearRedrawQueue=function isc_c_Canvas_clearRedrawQueue(){isc.EH.$h1("RDQ");var _1=isc.timeStamp();this.$xu=null;var _2=this.$nm;this.$nm=[];if(this.logIsDebugEnabled()){var _3="";for(var i=0;i<_2.length;i++){_3+=_2[i];if(i!=_2.length-1)_3+=", "}
this.logDebug("clearRedrawQueue: "+_3,"drawing")}
var _5,_6;for(var i=0;i<_2.length;i++){_5=_2[i];if(_5&&_5.priorityRedraw){_5.priorityRedraw=false;if(_6==null)_6=[];_6.add(_5);_2[i]=null}}
if(_6!=null){this.logInfo("Priority redraw: postponing non-priority items","drawing");this.$nm=_2;this.scheduleRedraw(_2[0]);_2=_6}
var _7=0,_5;for(var i=0;i<_2.length;i++){_5=_2[i];if(_5==null||_5.destroyed)continue;if(_5&&_5.isDirty()){_5.redraw(false);_7++}}
if(this.logIsDebugEnabled("redraws")){this.logDebug("clearRedrawQueue: "+_7+" redraws ("+_2.length+" items), "+(isc.timeStamp()-_1)+"ms","redraws")}
isc.EH.$h2()}
,isc.A.$uv=function isc_c_Canvas__queueForDelayedAdjustOverflow(_1){if(!isc.Canvas.$xv)isc.Canvas.$xv=[];isc.Canvas.$xv.add(_1);if(!isc.Canvas.$xw){isc.Canvas.$xw=isc.Timer.setTimeout({target:isc.Canvas,methodName:"$xx"},isc.Canvas.$nn)}}
,isc.A.$xx=function isc_c_Canvas__clearDelayedAdjustOverflowQueue(){var _1=isc.Canvas.$xv;isc.Canvas.$xv=[];isc.Canvas.$xw=null;if(!_1||_1.length==0)return;for(var i=0;i<_1.length;i++){var _3=window[_1[i]];if(isc.isA.Canvas(_3))_3.adjustOverflow("delayed")}}
,isc.A.checkForPageResize=function isc_c_Canvas_checkForPageResize(){isc.EH.$hr(true)}
,isc.A.moveOffscreen=function isc_c_Canvas_moveOffscreen(_1){if(_1.isDrawn())return;var _2=(!(!isc.Browser.isWin&&isc.Browser.isMoz&&_1.showCustomScrollbars==false&&(_1.overflow==isc.Canvas.AUTO)));if(_2)_1.moveTo(null,-9999)}
,isc.A.scheduleDestroy=function isc_c_Canvas_scheduleDestroy(_1){if(!_1||_1.destroyed||_1.destroying||!_1.destroy)return;this.$65j.add(_1);if(!this.$65m){this.$65m=isc.Timer.setTimeout({target:isc.Canvas,methodName:this.$65l},this.$65k)}}
,isc.A.clearDestroyQueue=function isc_c_Canvas_clearDestroyQueue(){isc.EH.$h1("DSQ");var _1=isc.timeStamp();this.$65m=null;var _2=this.$65j;this.$65j=[];if(this.logIsDebugEnabled("destroys")){var _3="";for(var i=0;i<_2.length;i++){_3+=_2[i];if(i!=_2.length-1)_3+=", "}
this.logDebug("clearDestroyQueue: "+_3,"destroys")}
var _5=0,_6;for(var i=0;i<_2.length;i++){_6=_2[i];if(_6==null||_6.destroyed||_6.destroying)continue;_6.destroy(false);_5++}
if(this.logIsDebugEnabled("destroys")){this.logDebug("clearDestroyQueue: "+_5+" direct destroy() calls ("+_2.length+" items), "+(isc.timeStamp()-_1)+"ms","destroys")}
isc.EH.$h2()}
,isc.A.outsetRect=function isc_c_Canvas_outsetRect(_1,_2){if(!_2)return _1;if(isc.isAn.Array(_1)){_1[0]-=_2;_1[1]-=_2;_1[2]+=2*_2;_1[3]+=2*_2;return _1}
_1.left-=_2;_1.top-=_2;_1.width+=2*_2;_1.height+=2*_2;return _1}
,isc.A.rectsIntersect=function isc_c_Canvas_rectsIntersect(_1,_2){var _3=_1[0],_4=_1[1],_5=_1[2],_6=_1[3],_7=_2[0],_8=_2[1],_9=_2[2],_10=_2[3],_11=((_3>_7+_9-1)||(_3+_5-1<_7)),_12=((_4>_8+_10-1)||(_4+_6-1<_8));return!_11&&!_12}
,isc.A.$vs=function isc_c_Canvas__forceNativeTabOrderUpdate(){if(!this.$xy){this.ns.Element.createAbsoluteElement("<DIV ID='$xz'"+" style='position:absolute;left:0px;top:-100px'>&nbsp;</DIV>");this.$xy=document.all["$xz"]}else{this.$xy.innerHTML="&nbsp;"}}
,isc.A.$q8=function isc_c_Canvas__addToTopLevelCanvasList(_1){if(!isc.isA.Canvas(_1)||_1.$x0!=null)return;this.$tw.add(_1);_1.$x0=this.$tw.length-1}
,isc.A.$ro=function isc_c_Canvas__removeFromTopLevelCanvasList(_1){if(!isc.isA.Canvas(_1)||_1.$x0==null)return;this.$tw[_1.$x0]=null;_1.$x0=null}
,isc.A.showClickMask=function isc_c_Canvas_showClickMask(_1,_2,_3){return this.ns.EH.showClickMask(_1,_2,_3)}
,isc.A.hideClickMask=function isc_c_Canvas_hideClickMask(_1){this.ns.EH.hideClickMask(_1)}
,isc.A.$t7=function isc_c_Canvas__placeRect(_1,_2,_3,_4,_5,_6){if(isc.isAn.Array(_3)){_3={left:_3[0],top:_3[1],width:_3[2],height:_3[3]}}else if(_3==null){_3={left:this.ns.EH.getX(),top:this.ns.EH.getY()}}
if(_3.width==null)_3.width=0;if(_3.height==null)_3.height=0;if(_4==null)_4="bottom";if(_5==null)_5=true;var _7=(_4=="bottom"||_4=="top");if(_7){if(_6=="inside-right")_6="right";if(_6!="right"&&_6!="outside-right"&&_6!="outside-left")_6="left"}else{if(_6=="inside-bottom")_6="bottom";if(_6!="bottom"&&_6!="outside-bottom"&&_6!="outside-top")_6="top"}
var _8=_3.left;if(_7){if(_6=="right")_8+=(_3.width-_1);else if(_6=="outside-right")_8+=_3.width;else if(_6=="outside-left")_8-=_1}else{if(_4=="left")_8-=_1;else _8+=_3.width}
var _9=_3.top;if(_7){if(_4=="top")_9-=_2;else _9+=_3.height}else{if(_6=="bottom")_9+=(_3.height-_2);else if(_6=="outside-bottom")_9+=_3.height;else if(_6=="outside-top")_9-=_2}
var _10=isc.Page.getWidth(),_11=isc.Page.getHeight(),_12=isc.Page.getScrollLeft(),_13=isc.Page.getScrollTop();var _14=_12-_8,_15=_8+_1-(_10+_12),_16=_13-_9,_17=_9+_2-(_11+_13);;if(_14<=0&&_15<=0&&_16<=0&&_17<=0){return[_8,_9]}
if(_14>0){if(_4=="left"&&!_5){if(_3.left+_3.width<_12){_8=_12}else{_8=_3.left+_3.width}}else{_8=_12}}else if(_15>0){if(_4=="right"&&!_5){if((_3.left-_1)>=_12){if(_3.left>(_12+_10))
_8=(_12+_10)-_1;else _8=_3.left-_1}}else{if(_10<_1){_8=_12}else{_8=_12+_10-_1}}}
if(_16>0){if(_4=="top"&&!_5){if(_3.top+_3.height<_13){_9=_13}else{_9=_3.top+_3.height}}else{_9=_13}}else if(_17>0){if(_4=="bottom"&&!_5){if((_3.top-_2)>=_13){if(_3.top>(_13+_11))
_9=(_13+_11)-_2;else _9=_3.top-_2}}else{if(_11<_2){_9=_13}else{_9=_13+_11-_2}}}
return[_8,_9]}
,isc.A.$iy=function isc_c_Canvas__handleUnload(){if(isc.Browser.isIE)this.$xt();var _1=isc.Log.logViewer;if(_1&&_1.logWindowLoaded()){_1._logWindow.openerUnloading();_1._logWindow=null}}
,isc.A.$xt=function isc_c_Canvas__clearDOMHandles(){var _1=this._canvasList;for(var i=0;i<_1.length;i++){var _3=_1[i];if(_3){if(_3.$q3){_3.$q3.eventProxy=null;_3.$q3=null}}}
return true}
,isc.A.snapToEdge=function isc_c_Canvas_snapToEdge(_1,_2,_3,_4,_5){var _6,_7,_8;if(isc.isAn.Array(_1)){_7=false;_8=[_1[1],_1[0]];_6=[_1[2],_1[3]]}else if(_3.masterElement){_7=(_3.percentBox==_3.$520),_6=[_7?_1.getViewportWidth():_1.getVisibleWidth(),_7?_1.getViewportHeight():_1.getVisibleHeight()];_8=[_1.getTop()+(_7?(_1.getTopBorderSize()+_1.getTopMargin()):0),_1.getLeft()+(_7?(_1.getLeftBorderSize()+_1.getLeftMargin()):0)]}else if(isc.isA.Canvas(_5)){_7=(_3.percentBox==_3.$520),_6=[_7?_5.getViewportWidth():_5.getVisibleWidth(),_7?_5.getViewportHeight():_5.getVisibleHeight()];_8=[_5.getPageTop()+(_7?(_5.getTopBorderSize()+_5.getTopMargin()):0),_5.getPageLeft()+(_7?(_5.getLeftBorderSize()+_5.getLeftMargin()):0)]}else{_7=true;_6=[_1.getViewportWidth(),_1.getViewportHeight()];_8=[0,0]}
var _9=isc.Canvas.$52c(_2,_8,_6,false);var _10=isc.Canvas.$52c((_4||_2),_9,[_3.getVisibleWidth(),_3.getVisibleHeight()],true);if(_3.snapOffsetLeft!=null)_10[1]+=_3.snapOffsetLeft;if(_3.snapOffsetTop!=null)_10[0]+=_3.snapOffsetTop;_3.moveTo(_10[1],_10[0]);_3.$jo=false}
,isc.A.$52c=function isc_c_Canvas__getSnapPoint(_1,_2,_3,_4){var _5=_3[0],_6=_3[1];var _7;if(_1=="TL")_7=[0,0];else if(_1=="T")_7=[0,_5/ 2];else if(_1=="TR")_7=[0,_5];else if(_1=="R")_7=[_6/ 2,_5];else if(_1=="BR")_7=[_6,_5];else if(_1=="B")_7=[_6,_5/ 2];else if(_1=="BL")_7=[_6,0];else if(_1=="L")_7=[_6/ 2,0];else if(_1=="C")_7=[_6/ 2,_5/ 2];else _7=[0,0];_7[0]=Math.floor(_7[0]);_7[1]=Math.floor(_7[1]);if(_4)return[_2[0]-_7[0],_2[1]-_7[1]];else return[_2[0]+_7[0],_2[1]+_7[1]]}
,isc.A.ariaEnabled=function isc_c_Canvas_ariaEnabled(){return false}
,isc.A.useLiteAria=function isc_c_Canvas_useLiteAria(){return false}
);isc.B._maxIndex=isc.C+48;isc.Canvas.registerStringMethods({resized:"deltaX,deltaY",showIf:"canvas",childRemoved:"child,name",peerRemoved:"peer,name",deparented:"oldParent,name",depeered:"oldMaster,name",parentMoved:"parent,deltaX,deltaY",moved:"deltaX,deltaY",focusChanged:"hasFocus",scrolled:null,hover:"",onDrop:"",visibilityChanged:"isVisible"});isc.Canvas.$yx=function(){var _1=isc.EH,_2={};for(var _3 in _1.eventTypes){this.registerStringMethods(_1.eventTypes[_3],_1.$i3);var _4=_1.eventTypes[_3];if(this.getInstanceProperty(_4)==null){_2[_4]=isc.Class.NO_OP}}
this.addMethods(_2)}
isc.Canvas.$yx();isc.defineClass("BackMask","Canvas");isc.A=isc.BackMask.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.autoDraw=false;isc.A.$lo=true;isc.A._generated=true;isc.A.useClipDiv=false;isc.A.hideUsingDisplayNone=isc.Browser.isMoz;isc.A.overflow=isc.Canvas.HIDDEN;isc.A.contents="<iframe width='100%' height='100%' border='0' frameborder='0' src=\""+isc.Page.getBlankFrameURL()+"\" marginwidth='0' marginheight='0' scrolling='no' tabIndex='-1' tabStop='false'></iframe>";isc.A.$ns=false;isc.A.$jo=false;isc.A.$jp=false;isc.A._redrawWithParent=false;isc.B.push(isc.A.masterMoved=function isc_BackMask_masterMoved(){this.masterElement.$qi()}
,isc.A.masterResized=function isc_BackMask_masterResized(){this.masterElement.$qi()}
,isc.A.draw=function isc_BackMask_draw(_1,_2,_3){if(this.suppressed)return this;if(!this.readyToDraw())return this;this.invokeSuper(isc.BackMask,this.$ny,_1,_2,_3);if(this.masterElement.overflow==isc.Canvas.VISIBLE)this.masterElement.$qi();return this}
,isc.A.show=function isc_BackMask_show(){if(!this.suppressed)this.invokeSuper(isc.BackMask,"show")}
);isc.B._maxIndex=isc.C+4;isc.defineClass("ScreenSpan","Canvas");isc.A=isc.ScreenSpan.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A._generated=true;isc.A.$92g=3200;isc.A.$92h=2400;isc.A.src="[SKINIMG]/blank.gif";isc.A.redrawOnResize=false;isc.A.overflow="hidden";isc.B.push(isc.A.getInnerHTML=function isc_ScreenSpan_getInnerHTML(){if(!this.$yy){this.$yy=isc.Browser.isIE&&isc.Browser.version>6?isc.Canvas.imgHTML(this.src,this.$92g,this.$92h):isc.Canvas.spacerHTML(this.$92g,this.$92h)}
return this.$yy}
,isc.A.hide=function isc_ScreenSpan_hide(_1,_2,_3,_4){this.resizeTo(1,1);this.moveTo(null,-this.getHeight());return this.invokeSuper(isc.ScreenSpan,"hide",_1,_2,_3,_4)}
,isc.A.show=function isc_ScreenSpan_show(_1,_2,_3,_4){this.fitToScreen();isc.Page.setEvent("resize",this,isc.Page.FIRE_ONCE,"pageResized");return this.invokeSuper(isc.ScreenSpan,"show",_1,_2,_3,_4)}
,isc.A.pageResized=function isc_ScreenSpan_pageResized(){if(!this.isVisible())return;this.resizeTo(isc.Page.getWidth(),isc.Page.getHeight());this.fitToScreen();isc.Page.setEvent("resize",this,isc.Page.FIRE_ONCE,"pageResized")}
,isc.A.fitToScreen=function isc_ScreenSpan_fitToScreen(){var _1=Math.max(isc.Page.getWidth(),isc.Page.getScrollWidth()),_2=Math.max(isc.Page.getHeight(),isc.Page.getScrollHeight());this.resizeTo(_1,_2);if(_1>this.$92g||_2>this.$92h){this.$92g=Math.max(_1,this.$92g);this.$92h=Math.max(_2,this.$92h);delete this.$yy;this.markForRedraw("Resizing spacer HTML to fit large page content.")}
this.moveTo(0,0)}
);isc.B._maxIndex=isc.C+5;isc.$yz={getForm:function(_1){if(_1&&typeof _1=="object")return _1;var _2;if(_1!=null&&isc.Browser.isDOM){_2=document.getElementById(_1)}
if(_2!=null)return _2;if(_1==null)_1=0;if(_2==null)return document.forms[_1];return _2},getFormElementValue:function(_1,_2){var _3=this.getFormElement(_1,_2);if(!_3)return;switch(_3.type){case"radio":return(_3.checked?_3.value:null)
case"checkbox":return _3.checked;case"select-one":if(!_3.options||_3.options.length==0)return null;var _4=_3.options[_3.selectedIndex];return _4.value;case"select-multiple":var _5=[];for(var i=0,_7=_3.options.length;i<_7;i++){var _4=_3.options[i];if(_4.selected)
_5.add(_4.value)}
return _5;case"button":case"reset":case"submit":return null;default:return _3.value}},getFormValues:function(_1){var _2=this.getForm(_1);if(!_2)return null;var _3={};if(!_2.elements){this.logWarn("Form '"+_1+"' contains no elements - returning empty map for data.");return{}}
for(var i=0;i<_2.elements.length;i++){var _5=_2.elements[i];if(_5.name!=null){var _6=this.getFormElementValue(_2,_2.elements[i]);if(_6!=null)_3[_5.name]=_6}}
return _3},getFormElement:function(_1,_2){if(typeof _2=="object")return _2;var _3=this.getForm(_1);if(_3)return _3.elements[_2];return null}};isc.Canvas.addClassMethods(isc.$yz)
isc.Canvas.addMethods(isc.$yz)
isc.setAutoDraw=function(_1){if(_1==null)_1=true;isc.Canvas.addProperties({autoDraw:_1})};isc.allowDuplicateStyles=true;isc.defineClass("PrintCanvas","Canvas");isc.A=isc.PrintCanvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.redrawOnResize=false;isc.A.overflow="hidden";isc.A.useExplicitHeight=isc.Browser.isSafari||(isc.Browser.isFirefox&&isc.Browser.isStrict&&isc.Browser.geckoVersion>=20100101);isc.A.printFrameURL="[HELPERS]printFrame.html";isc.B.push(isc.A.initWidget=function isc_PrintCanvas_initWidget(){this.Super("initWidget",arguments)}
,isc.A.resized=function isc_PrintCanvas_resized(){if(this.useExplicitHeight){var _1=this.getIFrameHandle();if(_1){_1.style.width=this.getInnerWidth();_1.style.height=this.getInnerHeight()}}}
,isc.A.getInnerHTML=function isc_PrintCanvas_getInnerHTML(){var _1="100%",_2="100%";if(this.useExplicitHeight){_1=this.getInnerWidth();_2=this.getInnerHeight()}
return"<iframe height='"+_2+"' width='"+_1+"' scrolling='auto' id='"+this.getIFrameID()+"' frameborder='0' src=\""+this.getPrintFrameURL(this.title)+"\"></iframe>"}
,isc.A.getIFrameID=function isc_PrintCanvas_getIFrameID(){return this.getID()+"$55y"}
,isc.A.getPrintFrameURL=function isc_PrintCanvas_getPrintFrameURL(_1){return isc.Page.getURL(this.printFrameURL+"?id="+this.getID()+"&title="+(_1||""))}
,isc.A.getIFrameHandle=function isc_PrintCanvas_getIFrameHandle(){return document.getElementById(this.getIFrameID())}
,isc.A.getIFrameWindow=function isc_PrintCanvas_getIFrameWindow(){return this.getIFrameHandle().contentWindow}
,isc.A.iframeLoad=function isc_PrintCanvas_iframeLoad(){this.iframeLoaded=true;if(isc.Browser.isIE){var _1=this.getIFrameWindow().document.body;if(_1)_1.style.overflow="auto"}}
,isc.A.setHTML=function isc_PrintCanvas_setHTML(_1,_2){if(!this.isDrawn()){this.$741={HTML:_1,callback:_2};return}
if(!this.iframeLoaded){this.delayCall("setHTML",[_1,_2],100);return}
var _3=this.getIFrameWindow();_3.assignHTML(_1);if(isc.Browser.isIE&&isc.Browser.hasVML&&_1.contains("class=rvml")){_3.document.createStyleSheet().addRule(".rvml","behavior:url(#default#VML)")}
this.fireCallback(_2,["printPreview","callback"],[this,_2])}
,isc.A.draw=function isc_PrintCanvas_draw(){this.Super("draw",arguments);if(this.$741!=null){var _1=this.$741;this.$741=null;this.setHTML(_1.HTML,_1.callback)}}
,isc.A.setTitle=function isc_PrintCanvas_setTitle(_1){this.title=_1;if(!this.isDrawn()&&!this.iframeLoaded)return;delete this.iframeLoaded;if(this.isDrawn())this.redraw()}
,isc.A.printHTML=function isc_PrintCanvas_printHTML(_1,_2,_3){var _4=this;this.setTitle(_2);this.setHTML(_1,function(){_4.print()})}
,isc.A.print=function isc_PrintCanvas_print(){if(!this.isDrawn()){this.logWarn("print(): Attempt to print an undrawn PrintCanvas. Ignoring.");return}
if(!this.iframeLoaded){this.delayCall("print",[],100);return}
this.getIFrameWindow().doPrint()}
,isc.A.printComplete=function isc_PrintCanvas_printComplete(){}
);isc.B._maxIndex=isc.C+14;isc.A=isc.Canvas;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.printComponents=function isc_c_Canvas_printComponents(_1,_2,_3,_4){isc.Canvas.getPrintHTML(_1,_2,{target:this,methodName:"$57f",title:_3,debugOnly:_4})}
,isc.A.$57f=function isc_c_Canvas__printComponentHTML(_1,_2){var _3=_2.title,_4=_2.debugOnly;if(!this.$55z)this.$55z=isc.PrintCanvas.create({width:"100%",height:"100%",autoDraw:false,backgroundColor:"white"});this.$55z.moveTo(null,-isc.Page.getHeight());if(!this.$55z.isDrawn())this.$55z.draw();this.$55z.printHTML(_1,_3,_4)}
,isc.A.getPrintPreview=function isc_c_Canvas_getPrintPreview(_1,_2,_3,_4,_5){if(_3==null)_3={};_3.autoDraw=true;isc.Canvas.getPrintHTML(_1,_2,{target:this,methodName:"$57g",origCallback:_4,previewProperties:_3},_5)}
,isc.A.$57g=function isc_c_Canvas__createPrintPreview(_1,_2){var _3=isc.PrintCanvas.create(_2.previewProperties);_3.setHTML(_1,{target:this,methodName:"$57h",origCallback:_2.origCallback})}
,isc.A.$57h=function isc_c_Canvas__printPreviewGenerated(_1,_2){if(_2.origCallback){this.fireCallback(_2.origCallback,["printPreview"],[_1])}}
,isc.A.showPrintPreview=function isc_c_Canvas_showPrintPreview(_1,_2,_3,_4,_5){if(!isc.PrintWindow){isc.definePrintWindow()}
if(!isc.PrintWindow)return;if(_3==null)_3={};_3.autoDraw=false;if(_3.width==null)_3.width="100%";if(_3.height==null)_3.height="100%";if(_3.left==null)_3.left=0;if(_3.top==null)_3.top=0;if(!this.$57i){this.$57i=isc.PrintWindow.create(_3)}else{this.$57i.setProperties(_3)}
this.$57i.showPrintPreview(_1,_2,_4,_5)}
);isc.B._maxIndex=isc.C+6;isc.definePrintWindow=function(){if(!isc.Window){isc.logWarn("Attempting to create PrintWindow class with no defined Window class. "+"Ensure the required 'Containers' module is laoded");return}
isc.defineClass("PrintWindow","Window");isc.PrintWindow.addProperties({isModal:true,headerControls:["headerIcon","headerLabel","printButton","closeButton"],printButtonDefaults:{_constructor:"IButton",height:20,click:"this.creator.printClicked()"},showMinimizeButton:false,showShadow:false,title:"Print Preview",printButtonTitle:"Print",setPrintButtonTitle:function(_3){this.printButtonTitle=_3;if(this.printButton!=null)this.printButton.setTitle(_3)},initWidget:function(){this.printButtonDefaults.title=this.printButtonTitle;this.Super("initWidget",arguments)},showPrintPreview:function(_3,_4,_5,_6){if(!isc.isAn.Array(_3))_3=[_3];isc.Canvas.getPrintHTML(_3,_4,{target:this,methodName:"$57j",origCallback:_5},_6)},$57j:function(_3,_4){if(!this.previewPane){this.previewPane=this.createPreviewPane();this.previewPane.addProperties({title:this.title});this.addItem(this.previewPane)}else{this.previewPane.setTitle(this.title)}
this.setVisibility("hidden");if(!this.isDrawn())this.draw();this.previewPane.setHTML(_3,{target:this,methodName:"$57h",origCallback:_4.origCallback})},$57h:function(_3,_4){if(!this.isVisible())this.show();this.bringToFront();if(_4.origCallback){this.fireCallback(_4.origCallback,["printPreview","printWindow"],[_3,this])}},printClicked:function(){var _1=this.getPrintCanvas();if(!_1)return;_1.print()},createPreviewPane:function(_3){var _2=isc.PrintCanvas.create({width:"100%",height:"100%"});return _2},getPrintCanvas:function(){return this.previewPane},closeClick:function(){this.Super("closeClick",arguments);this.clear()}})}
isc.ClassFactory.defineInterface("DataBoundComponent");isc.A=isc.Canvas;isc.A.COPY="copy";isc.A.MOVE="move";isc.A.CLONE="clone";isc.A=isc.Canvas;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$70l="/";isc.B.push(isc.A.getFieldImageDimensions=function isc_c_Canvas_getFieldImageDimensions(_1,_2){var _3,_4;var _5,_6,_7;if(isc.isA.String(_1.imageWidth)){_5=_1.imageWidth}else{_3=_1.imageWidth}
if(isc.isA.String(_1.imageHeight)){_6=_1.imageHeight}else{_4=_1.imageHeight}
if(isc.isA.String(_1.imageSize)){_7=_1.imageSize}else{_3=_3||_1.imageSize;_4=_4||_1.imageSize}
if(_2!=null){_3=_3||_2[_5]||_2[_7];_4=_4||_2[_6]||_2[_7]}
return{width:_3,height:_4}}
,isc.A.$833=function isc_c_Canvas__performActionOnValue(_1,_2,_3,_4,_5,_6,_7){if(!_3||_2==null||isc.isAn.emptyString(_2))return;var _8=_3;var _9=_2.contains(this.$70l);if(_9){_2=_2.trim(isc.Canvas.$70l);var _10=_2.split(this.$70l),_11=[],_12;if(_10[0]&&_3[_10[0]]===_12&&_1=="get"&&!_7)
{if(_4&&isc.ValuesManager&&isc.isA.ValuesManager(_4.valuesManager))
{return this.$833(_1,_2,_4.valuesManager.getValues(),_4,_5,_6,true)}}
if(isc.isAn.emptyString(_10.last()))_10.length-=1;for(var i=0;i<_10.length;i++){if(isc.isAn.emptyString(_10[i]))continue;if(_3==null){_11.length=0;break}
_11.add(_3);if(i==_10.length-1){if(_1=="get"){return _3[_10[i]]}else if(_1=="clear"){delete _3[_10[i]]}else if(_1=="save"){_3[_10[i]]=_6}}else{var _14=_3[_10[i]];if(_14==_12){if(_1=="get"){return _12}else if(_1=="clear"){return}else if(_1=="save"){_14=_3[_10[i]]={}}}
_3=_14;if(isc.isAn.Array(_3)){var _15=null;var _16=(parseInt(_10[i+1])==_10[i+1])
if(_16){_15=parseInt(_10[i+1])
_10.removeAt(i+1)}else if(_4&&_4.selectionComponent){var _17=isc.Canvas.$70l,_18=_4,_19;for(var j=0;j<=i;j++){_17+=_10[j]+isc.Canvas.$70l}
_17=_17.trim(isc.Canvas.$70l);_18=_4.selectionComponent;while(_18){var _21=_18.dataPath;if(_21)_21=_21.trim(isc.Canvas.$70l);if(_17==_21){var _22=_18.getSelectedRecord();if(_22){_15=_18.getRecordIndex(_22)}else{_19=true}
break}
_18=_18.selectionComponent}
if(_15==null){if(!_19&&_5){_15=0}else{return}}}else{if(_5){_15=0}else{return}}
_3=_3[_15]}}}
if(_1=="clear"){for(var i=_11.length-1;i>0;i--){if(isc.isAn.emptyObject(_11[i])){delete _11[i-1][_10[i-1]]}}}}else{if(_1=="get")return _3[_2];else if(_1=="clear")delete _3[_2];else if(_1=="save")_3[_2]=_6}}
,isc.A.$70m=function isc_c_Canvas__clearFieldValue(_1,_2,_3,_4){this.$833("clear",_1,_2,_3,_4,null,false)}
,isc.A.$70n=function isc_c_Canvas__saveFieldValue(_1,_2,_3,_4,_5){this.$833("save",_1,_3,_4,_5,_2,false);return _3}
,isc.A.$70o=function isc_c_Canvas__getFieldValue(_1,_2,_3,_4){return this.$833("get",_1,_2,_3,_4,null,false)}
,isc.A.$702=function isc_c_Canvas__combineDataPaths(_1,_2){if(_1==null&&_2==null)return null;if(isc.isA.String(_2)&&_2.startsWith(this.$70l))return _2;if(_1==null)return""+_2;if(_2==null)return _1+"";if(isc.isA.String(_1)&&_1.endsWith(this.$70l)){return _1+_2}else{return _1+this.$70l+_2}}
,isc.A.evalViewState=function isc_c_Canvas_evalViewState(_1,_2,_3,_4){if(isc.isA.String(_1)){var _5=_1;try{_1=isc.eval(_1)}catch(e){if(!_3){var _6="Unable to parse "+_2+" object passed in: "+isc.Log.echo(_5)+" Ignoring."
if(!_4||_4.logWarn==null){if(_4)_6+=" [target:"+isc.Log.echo(_4)+"]";this.logWarn(_6)}else{_4.logWarn(_6)}}
return}}
return _1}
);isc.B._maxIndex=isc.C+7;isc.A=isc.Canvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.showComplexFields=true;isc.A.exportIncludeSummaries=true;isc.A.ignoreEmptyCriteria=true;isc.A.dragRecategorize=false;isc.A.duplicateDragMessage="Duplicates not allowed";isc.A.showOfflineMessage=true;isc.A.offlineMessage="This data not available while offline";isc.A.offlineMessageStyle="offlineMessage";isc.A.offlineSaveMessage="Data cannot be saved because you are not online";isc.A.addDropValues=true;isc.A.fieldIdProperty="name";isc.A.dataArity="multiple";isc.A.autoTrackSelection=true;isc.A.$308={date:true,DateItem:true};isc.A.$301="false;";isc.A.$18r="false";isc.A.styleOpposite="cellHiliteOpposite";isc.A.hiliteProperty="_hilite";isc.A.hiliteMarker="$63i";isc.A.$578=0;isc.A.$579=[];isc.A.dragDataAction=isc.Canvas.MOVE;isc.A.dragTrackerStyle="gridDragTracker";isc.A.canExport=true;isc.A.canPrint=true;isc.A.panelControls=["action:edit","action:editNew","action:sort","action:export","action:print"];isc.A.dbcProperties=["autoFetchData","autoFetchTextMatchStyle","autoFetchAsFilter","dataSource"];isc.A.badFormulaResultValue=".";isc.A.missingSummaryFieldValue="-";isc.A.canAddFormulaFields=false;isc.A.addFormulaFieldText="Add formula column...";isc.A.editFormulaFieldText="Edit formula...";isc.A.removeFormulaFieldText="Remove formula";isc.A.canAddSummaryFields=false;isc.A.addSummaryFieldText="Add summary column...";isc.A.editSummaryFieldText="Edit summary format...";isc.A.removeSummaryFieldText="Remove summary column..";isc.A.formulaFieldNamePrefix="formulaField";isc.A.summaryFieldNamePrefix="summaryField";isc.A.uniqueFieldNamePrefix="field";isc.A.exportDataChunkSize=50;isc.A.emptyExportMessage="You are attempting to export an empty dataset";isc.A.unknownErrorMessage="Invalid value";isc.A.$685=["isInteger","isFloat","isBoolean","isString"];isc.A.$746="partial";isc.A.$75a={};isc.A.$75b=null;isc.B.push(isc.A.setValuesManager=function isc_Canvas_setValuesManager(_1){if(_1)_1.addMember(this)}
,isc.A.initializeValuesManager=function isc_Canvas_initializeValuesManager(){var _1=this.valuesManager;delete this.valuesManager;if(_1!=null){if(isc.ValuesManager==null){this.logWarn("Widget initialized with specified 'valuesManager' property but "+"ValuesManager class is not loaded. This functionality requires the "+"Forms module.");return}
if(isc.isA.ValuesManager(_1)){_1.addMember(this)}else if(isc.isA.ValuesManager(window[_1])){window[_1].addMember(this)}else if(isc.isA.String(_1)){isc.ValuesManager.create({ID:_1,dataSource:this.dataSource,members:[this]})}else{this.logWarn("Widget initialized with invalid 'valuesManager' property:"+isc.Log.echo(_1)+", clearing this property out")}}}
,isc.A.setDataPath=function isc_Canvas_setDataPath(_1){this.dataPath=_1;if(this.getFields==null||this.getFields()==null)return;if(_1==null){delete this.$704;if(this.valuesManager&&this.$703){this.valuesManager.removeMember(this);delete this.$703}
return}
var _2;var _3=this;while(_3&&(!_3.valuesManager||_3.$703)&&!_3.dataSource)
{if(_3.dataPath){if(_2){_2=isc.Canvas.$702(_3.dataPath,_2)}else{_2=_3.dataPath}}
_3=_3.parentElement}
this.$704=_2;if(_3){if(_3!=this||!this.dataSource){if(_3.valuesManager==null){_3.createDefaultValuesManager()}
var _4=isc.isA.DynamicForm(this)?this.$834:this.getFields();_4=_4||this.getFields();if(_3.valuesManager.getDataSource()){this.setDataSource(_3.valuesManager.getDataSource(),_4)}
_3.valuesManager.addMember(this,true)}}}
,isc.A.getFullDataPath=function isc_Canvas_getFullDataPath(){var _1=this.$704||this.dataPath;if(!_1&&this.masterElement){return this.masterElement.$704||this.masterElement.dataPath}
return _1}
,isc.A.buildFieldDataPath=function isc_Canvas_buildFieldDataPath(_1,_2){var _3=_2.dataPath||_2.name;if(_1&&_3&&!_3.startsWith("/")){_3=_1+"/"+_3}
return!_3?null:_3.replace(/^\/*/,"")}
,isc.A.createDefaultValuesManager=function isc_Canvas_createDefaultValuesManager(_1){if(!_1)_1=[];_1.add(this);isc.ValuesManager.create({members:_1,ID:this.getID()+"$705",dataSource:this.dataSource})}
,isc.A.getDataPathField=function isc_Canvas_getDataPathField(_1){if(!_1)return null;var _2=this.getDataSource(),_3=_1.split(isc.slash),_4;if(!_2)return;for(var i=0;i<_3.length;i++){var _6=_3[i],_7=_2.getField(_6);_2=_7?(_2.getSchema(_7.type)||_2):_2;if(_7==null){this.logWarn("Unable to find dataSource field matching specified dataPath: '"+_1+"'");return}}
return _7}
,isc.A.registerWithDataView=function isc_Canvas_registerWithDataView(_1){if(!this.inputDataPath)return;_1=this.parentElement;while(_1&&!isc.isA.DataView(_1))_1=_1.parentElement;if(!_1){this.logWarn("Component initialized with an inputDataPath property, but no DataView "+"was found in the parent hierarchy. inputDataPath is only applicable to "+"DataBoundComponents and FormItems being managed by a DataView");return}
_1.registerItem(this)}
,isc.A.bindToDataSource=function isc_Canvas_bindToDataSource(_1,_2){if(this.dataPath)this.setDataPath(this.dataPath);if(this.dataSource==null&&this.data!=null)this.dataSource=this.data.dataSource;var _3=this.fields||this.items;if(isc.isAn.Array(_3))this.originalFields=_3.duplicate();var _4=this.getDataSource();if(_4==null&&this.valuesManager&&this.valuesManager.getDataSource){_4=this.valuesManager.getDataSource()}
if(_4!=null&&isc.isA.String(_4)){this.logWarn("unable to look up DataSource: "+_4+", databinding will not be used");return _1}
var _5=(_1==null||_1.length==0),_6;if(_4){var _7=this.useFlatFields;if(_7==null)_7=_4.useFlatFields;_6=_7?_4.getFlattenedFields():_4.getFields()}
if(_4==null||_6==null){if(_1!=null&&isc.SimpleType){for(var i=0;i<_1.length;i++){if(_1[i].type==null&&this.$308[_1[i].editorType]==true)
{_1[i].type="date"}
isc.SimpleType.addTypeDefaults(_1[i])}}
this.addFieldValidators(_1);return _1}
if(this.doNotUseDefaultBinding)return[];if(_4!=null&&_5){_1=[];for(var _9 in _6){var _10=_6[_9];if(!this.shouldUseField(_10,_4))continue;var _11=isc.addProperties({},_10)
var _12=this.getDefaultCanEdit(_10);var _13;if(_12===_13){delete _11.canEdit}else{_11.canEdit=_12}
_1.add(_11)}
this.addFieldValidators(_1);return _1}
if(_4!=null&&!_5){var _14={};for(var i=_1.length-1;i>=0;i--){var _10=_1[i];if(_10==null)continue;var _15=(_10.name!=null)?_4.getField(_10.name):null;if(_15&&_15.canView===false){this.logInfo("Dropping explicitly-named field "+_10.name+" because it is marked canView: false");_1.removeAt(i)}
if(_10.type==null&&this.$308[_10.editorType]==true){var _16=_10.name;var _15=(_16!=null)?_4.getField(_16):null;if(_15==null||_15.type==null){_10.type="date"}}
if(_15){var _12=_10.canEdit;if(_12==null){_12=this.getDefaultCanEdit(_15)}else{this.logDebug("DataBoundComponent respecting explicit 'canEdit' on target field "+_15.name,"canEditField")}
_14[_10.name]=_12}else{_14[_10.name]=_10.canEdit}
if(_10.type!=null)isc.SimpleType.addTypeDefaults(_10)}
if(this.useAllDataSourceFields||_2){var _17=this;var _18=_4.combineFieldOrders(_6,_1,function(_10,_4){return _17.shouldUseField(_10,_4)});for(var i=0;i<_18.length;i++){var _10=_18[i];if(!_1.containsProperty("name",_10.name)){if(_2&&_10.showIf==null){_10.showIf="return false"}}else{if(_10.includeFrom!=null&&_4.getField(_10.name)==null){this.$785(_10)}}
var _12;if(_1.contains(_10)){_12=_14[_10.name]}else{_12=this.getDefaultCanEdit(_10)}
var _13;if(_12===_13){delete _10.canEdit}else{_10.canEdit=_12}}
this.addFieldValidators(_18);return _18}else{for(var i=0;i<_1.length;i++){var _10=_1[i];if(!_10)continue;_10=this.combineFieldData(_10);_10.canEdit=_14[_10.name]}
this.addFieldValidators(_1);return _1}}}
,isc.A.getDefaultCanEdit=function isc_Canvas_getDefaultCanEdit(_1){var _2=this.canEditFieldAttribute;if(_2==null)_2="canEdit";var _3=_1[_2];if(_3==null){if(_1.canSave==false&&!this.$66m){_3=false}}
this.logDebug("DataBoundComponent using canEditFieldAttribute:"+_2+" setting 'canEdit' to "+_3+" on target field "+_1.name,"canEditField");return _3}
,isc.A.combineFieldData=function isc_Canvas_combineFieldData(_1){var _2=this.getDataSource();if(this.getFullDataPath()||_1.dataPath){var _3=this.buildFieldDataPath(this.getFullDataPath(),_1);isc.DataSource.combineFieldData(_1,this.getDataPathField(_3));return _1}else if(_2!=null&&_2.getField(_1.name)){return _2.combineFieldData(_1)}else if(_1.includeFrom!=null){return this.$785(_1)}
return _1}
,isc.A.$785=function isc_Canvas__combineIncludeFromFieldData(_1){var _2=_1.includeFrom.split(".");if(_2==null||_2.length!=2){this.logWarn("This component includes a field with includeFrom set to:"+_1.includeFrom+". Format not understood.")}else{var _3=isc.DataSource.get(_2[0]),_4=_2[1];if(_3==null){this.logWarn("Field specifies includeFrom:"+_1.includeFrom+". Unable to find dataSource with ID:"+_2[0])}else{if(_1.name==null)_1.name=_4;return _3.combineFieldData(_1,_3.getField(_4))}}}
,isc.A.shouldUseField=function isc_Canvas_shouldUseField(_1,_2){if(_1.canView===false)return false;if(_1.hidden&&!this.showHiddenFields)return false;if(_1.canFilter==false&&this.showFilterFieldsOnly){return false}
if(_1.detail&&!this.showDetailFields)return false;if(!this.showComplexFields&&_2.fieldIsComplexType(_1.name))return false;return true}
,isc.A.addFieldValidators=function isc_Canvas_addFieldValidators(_1){if(_1==null)return;for(var i=0;i<_1.length;i++){var _3=_1[i];if(_3.required){var _4=this.getRequiredValidator(_3),_5=_4.errorMessage;if(!_3.validators){_3.validators=[_4]}else{if(!isc.isAn.Array(_3.validators)){_3.validators=[_3.validators]}
if(!_3.validators.containsProperty("type",_4.type)&&!_3.validators.containsProperty("_constructor",_4.type))
{if(_3.validators.$69){_3.validators=_3.validators.duplicate()}
_3.validators.add(_4)}else if(_5!=null){var _6=this.getDataSource(),v=(_3.validators.find("type",_4.type)||_3.validators.find("_constructor",_4.type));if(v.errorMessage==null||(_6&&v.errorMessage==_6.requiredMessage)){v.errorMessage=_5}}}}else if(_3.required==false){var _8=_3.validators;if(_3.validators!=null){var _9=_3.validators.findIndex("type","required");if(_9!=-1){_3.validators.removeAt(_9)}}}
if(_3.multiple&&_3.validateEachItem==null)_3.validateEachItem=true}}
,isc.A.getRequiredValidator=function isc_Canvas_getRequiredValidator(_1){var _2={type:"required"},_3=_1.requiredMessage||this.requiredMessage;if(_3!=null)_2.errorMessage=_3;return _2}
,isc.A.getAllFields=function isc_Canvas_getAllFields(){return this.completeFields||this.fields}
,isc.A.getField=function isc_Canvas_getField(_1){if(!this.fields)return null;return isc.Class.getArrayItem(_1,this.fields,this.fieldIdProperty)}
,isc.A.getFieldNum=function isc_Canvas_getFieldNum(_1){if(!this.fields)return-1;if(isc.isA.Object(_1)&&(_1[this.fieldIdProperty]!=null)){_1=_1[this.fieldIdProperty]}
return isc.Class.getArrayItemIndex(_1,this.fields,this.fieldIdProperty)}
,isc.A.isXMLRequired=function isc_Canvas_isXMLRequired(_1){if(!_1||!this.useXMLRequired||!_1.xmlRequired)return false;if(!_1.dataPath)return true;var _2=this.getDataSource();if(!_2)return true;var _3=_1.dataPath.split(isc.slash),_1;for(var i=0;i<_3.length;i++){var _5=_3[i];if(!_2)return true;_1=_2.getField(_5);if(!_1)return true;if(_1.xmlMinOccurs!=null&&_1.xmlMinOccurs<1){return false}
_2=_2.getSchema(_1.type)}
return true}
,isc.A.evalViewState=function isc_Canvas_evalViewState(_1,_2,_3){return isc.Canvas.evalViewState(_1,_2,_3,this)}
,isc.A.getFieldState=function isc_Canvas_getFieldState(_1){var _2=[];var _3=this.getAllFields();if(_3){for(var i=0;i<_3.length;i++){var _5=_3[i];if(!_5||_5.excludeFromState)continue;var _6=_5[this.fieldIdProperty],_7=this.getStateForField(_6,_1);_2.add(_7)}}
return isc.Comm.serialize(_2,false)}
,isc.A.getStateForField=function isc_Canvas_getStateForField(_1,_2){var _3=this.getAllFields().find(this.fieldIdProperty,_1),_4={name:_1};if(!_3)return null;if(_3.frozen==true)_4.frozen=true;if(!this.fieldShouldBeVisible(_3,this.getFieldNum(_1)))_4.visible=false;if(_3.userFormula)_4.userFormula=_3.userFormula;if(_3.userSummary)_4.userSummary=_3.userSummary;if(_2||_3.userSummary||_3.userFormula){_4.title=_3.title}
if(this.getSpecifiedFieldWidth)_4.width=this.getSpecifiedFieldWidth(_3);if(_3.autoFitWidth)_4.autoFitWidth=true;return _4}
,isc.A.$31y=function isc_Canvas__setFieldState(_1,_2){if(_1==null)return;var _3=this.getAllFields();var _4=_3.getProperty(this.fieldIdProperty),_5=[];for(var i=0;i<_1.length;i++){var _7=_1[i],_8=_3.find(this.fieldIdProperty,_7.name);if(_8==null){if(_7.userFormula||_7.userSummary){_8={};_8[this.fieldIdProperty]=_7.name}else continue}
_4.remove(_7.name);if(_7.visible==false){_8.showIf=this.$18r}else{_8.showIf=null;_8.detail=false}
if(_7.width!=null&&(!isNaN(_7.width)||_7.width=="*"))_8.width=_7.width;_8.frozen=_7.frozen;if(_7.title)_8.title=_7.title;if(_7.userFormula!=null)_8.userFormula=_7.userFormula;if(_7.userSummary!=null)_8.userSummary=_7.userSummary;if(_7.autoFitWidth!=_8.autoFitWidth)_8.autoFitWidth=_7.autoFitWidth;_5.add(_8)}
var _9=this.defaultFieldState;if(_9!=null)_9=this.evalViewState(_9,"fieldState");for(var i=0;i<_4.length;i++){var _10=_4[i],_11=_3.findIndex(this.fieldIdProperty,_10),_8=_3[_11],_12=_3[_11-1];var _13=_9?_9.find("name",_10):null;var _14=_13!=null?_13.visible==false:_2;if(_14){_8.showIf=this.$18r}else{_8.showIf=null;_8.detail=false}
if(_13){if(_13.width!=null&&(!isNaN(_13.width)||_13.width=="*"))
{_8.width=_13.width}
_8.frozen=_13.frozen;if(_13.title)_8.title=_13.title;if(_13.userFormula!=null)_8.userFormula=_13.userFormula;if(_13.userSummary!=null)_8.userSummary=_13.userSummary;if(_13.autoFitWidth!=_8.autoFitWidth)_8.autoFitWidth=_13.autoFitWidth}
if(_12!=null){var _15=_5.indexOf(_12);if(_15!=-1){_5.addAt(_8,_15+1);continue}}
if(this.fieldShouldBeVisible(_8,_11)&&!_2){_5.addAt(_8,this.$31z(_5)+1)}else{_5.add(_8)}}
return _5}
,isc.A.fieldStateChanged=function isc_Canvas_fieldStateChanged(){}
,isc.A.$31z=function isc_Canvas__lastVisibleFieldIndex(_1){if(_1==null)_1=this.completeFields;var _2=this.getVisibleFields(_1);if(_2.length==0)return-1;return _1.lastIndexOf(_2.last())}
,isc.A.getVisibleFields=function isc_Canvas_getVisibleFields(_1){var _2=[];for(var i=0;i<_1.length;i++){var _4=_1[i];if(_4==null)continue;if(this.fieldShouldBeVisible(_4,i))_2.add(_4)}
return _2}
,isc.A.fieldShouldBeVisible=function isc_Canvas_fieldShouldBeVisible(_1,_2){if(_1.showIf!=null){if(_1.showIf==this.$18r||_1.showIf==this.$301)return false;isc.Func.replaceWithMethod(_1,"showIf","list,field,fieldNum");if(!_1.showIf(this,_1,_2))return false}
return true}
,isc.A.setValueMap=function isc_Canvas_setValueMap(_1,_2){if(!isc.isAn.Object(_1))_1=this.getField(_1);if(!_1)return;_1.valueMap=_2}
,isc.A.setDataSource=function isc_Canvas_setDataSource(_1,_2){if(isc.$cv)arguments.$cw=this;this.dataSource=_1||this.dataSource;if(this.setFields)this.setFields(_2);if(this.dataSource){if(this.isA("DynamicForm"))this.setData({});else this.setData([])}
this.markForRedraw("bind")}
,isc.A.bind=function isc_Canvas_bind(_1,_2){this.setDataSource(_1,_2)}
,isc.A.getDataSource=function isc_Canvas_getDataSource(){if(isc.isA.String(this.dataSource)){if(this.serviceNamespace||this.serviceName){this.dataSource=this.lookupSchema()}else{var _1=isc.DS.get(this.dataSource);if(_1!=null)return _1;_1=this.getWindow()[this.dataSource];if(_1&&isc.isA.DataSource(_1))return(this.dataSource=_1)}}
return this.dataSource}
,isc.A.setData=function isc_Canvas_setData(_1){this.data=_1}
,isc.A.lookupSchema=function isc_Canvas_lookupSchema(){var _1;if(this.serviceName)_1=isc.WebService.getByName(this.serviceName,this.serviceNamespace);else _1=isc.WebService.get(this.serviceNamespace);if((this.serviceNamespace||this.serviceName)&&_1==null){this.logWarn("Could not find WebService definition: "+(this.serviceName?"serviceName: "+this.serviceName:"")+(this.serviceNamespace?"   serviceNamespace: "+this.serviceNamespace:""))}
if(!isc.isA.String(this.dataSource)){this.logWarn("this.dataSource was not a String in lookupSchema");return}
var _2;if(_1)_2=_1.getSchema(this.dataSource);return _2||this.dataSource}
,isc.A.fieldValuesAreEqual=function isc_Canvas_fieldValuesAreEqual(_1,_2,_3){if(_2==_3)return true;if(_1==null)return false;if(_1.type=="date"){if(isc.isA.Date(_2)&&isc.isA.Date(_3)){return(Date.compareLogicalDates(_2,_3)==0)}}else if(_1.type=="datetime"){if(isc.isA.Date(_2)&&isc.isA.Date(_3)){return(Date.compareDates(_2,_3)==0)}}else if(_1.type=="valueMap"){if(isc.isAn.Array(_2)&&isc.isAn.Array(_3)){return _2.equals(_3)}else if(isc.isAn.Object(_2)&&isc.isAn.Object(_3)){for(var i in _2){if(_3[i]!=_2[i])return false}
for(var j in _3){if(_2[j]!=_3[j])return false}
return true}}
return false}
,isc.A.setFields=function isc_Canvas_setFields(_1){_1=this.bindToDataSource(_1);this.fields=_1}
,isc.A.getSerializeableFields=function isc_Canvas_getSerializeableFields(_1,_2){_1.addList(["zIndex","data"]);if(this.ID&&this.ID.startsWith("isc_"))_1.add("ID");if(this.dataSource)_1.addList(["fields","items"]);if(this.getClassName()!="Canvas"&&this.getClassName()!="Layout"){_1.add("children")}
return this.Super("getSerializeableFields",arguments)}
,isc.A.addField=function isc_Canvas_addField(_1,_2,_3){if(_1==null)return;if(_3==null)_3=(this.fields||this.items||isc.$ac);_3=_3.duplicate();var _4=this.getField(_1.name);if(_4)_3.remove(_4);if(_2==null||_2>_3.length)_2=_3.length;_3.addAt(_1,_2);this.setFields(_3)}
,isc.A.removeField=function isc_Canvas_removeField(_1,_2){if(_2==null)_2=(this.fields||this.items||isc.$ac);_2=_2.duplicate();var _3=_1.name?_1.name:_1;_2.remove(_2.find("name",_3));this.setFields(_2)}
,isc.A.setCanEdit=function isc_Canvas_setCanEdit(_1){this.canEdit=_1}
,isc.A.filterData=function isc_Canvas_filterData(_1,_2,_3){this.$wo("filter",_1,_2,_3)}
,isc.A.fetchData=function isc_Canvas_fetchData(_1,_2,_3){if(!_3)_3={};if(!_3.textMatchStyle)_3.textMatchStyle="exact";this.$wo("fetch",_1,_2,_3)}
,isc.A.$71s=function isc_Canvas__canExportField(_1){return(this.canExport!=false&&_1.canExport!=false&&!_1.userFormula&&!_1.userSummary&&!_1.hidden)}
,isc.A.exportData=function isc_Canvas_exportData(_1,_2){if(!_1)_1={};var _3=this.getSort();if(_3){_1.sortBy=isc.DS.getSortBy(_3)}else if(this.sortField){_1.sortBy=(Array.shouldSortAscending(this.sortDirection)?"":"-")+this.sortField}
if(!_1.textMatchStyle){var _4=this.data.context;if(_4&&_4.textMatchStyle){_1.textMatchStyle=_4.textMatchStyle}}
if(!this.exportAll&&!_1.exportFields){var _5=this.exportFields,_6="",_7=this.getDataSource();if(!_5){_5=[];for(var i=0;i<this.fields.length;i++){var _9=this.fields.get(i),_10=_7?_7.getField(_9.name):null;if(this.$71s(_9)){if(_9.includeFrom||(_10&&_10.includeFrom)){var _11=_9.includeFrom?_9.includeFrom:_10.includeFrom;_6+=_9.name+":"+_11+",";_5.add(_9.name)}else{_5.add(_9.name)}
if(_9.displayField&&!_9.optionDataSource){_5.add(_9.displayField)}}}}
if(_6.length>1){if(_6.endsWith(",")){_6=_6.substring(0,_6.length-1)}
_1.additionalOutputs=_6}
if(_5&&_5.length>0)_1.exportFields=_5}
var _12=_1.exportFields||this.exportFields||this.fields;var _13={};for(var i=0;i<_12.length;i++){var _9=_12[i];var _14;if(isc.isA.String(_9)){_14=_9;_9=this.getField(_14)}
if(_9){_13[_9.name]=_9.exportTitle||_9.title}else{_13[_14]=_14}}
_1.exportFieldTitles=_13;this.getDataSource().exportData(this.getCriteria(),_1,_2,this)}
,isc.A.setCriteria=function isc_Canvas_setCriteria(_1){if(this.data&&this.data.setCriteria)this.data.setCriteria(_1);else this.initialCriteria=_1}
,isc.A.getCriteria=function isc_Canvas_getCriteria(){if(!this.isDrawn()&&(!this.data||this.data.getLength()==0)){return isc.shallowClone(this.initialCriteria)}
else if(this.data&&this.data.getCriteria){if(isc.isA.Tree(this.data)){return isc.shallowClone(this.data.getCriteria(this.getDataSource()))}else{return isc.shallowClone(this.data.getCriteria())}}else return null}
,isc.A.doInitialFetch=function isc_Canvas_doInitialFetch(){var _1=false;if(this.autoFetchData&&!this.$p7&&this.fetchData){if(!this.dataSource){this.logWarn("autoFetchData is set, but no dataSource is specified, can't fetch")}else{_1=!isc.RPCManager.startQueue();this.fetchData(this.getInitialCriteria(),null,this.getInitialFetchContext());this.$p7=true}}
return _1}
,isc.A.getInitialCriteria=function isc_Canvas_getInitialCriteria(){return this.initialCriteria}
,isc.A.getInitialFetchContext=function isc_Canvas_getInitialFetchContext(){var _1={};_1.textMatchStyle=this.autoFetchTextMatchStyle;return _1}
,isc.A.fetchRelatedData=function isc_Canvas_fetchRelatedData(_1,_2,_3,_4){var _5=isc.isA.DataSource(_2)?_2:isc.isA.String(_2)?isc.DS.get(_2):isc.isA.Canvas(_2)?_2.dataSource:null;if(!_5){this.logWarn("schema not understood: "+this.echoLeaf(_2));return}
var _6=this.getDataSource().getTreeRelationship(_5);var _7={};_7[_6.parentIdField]=_1[_6.idField];this.fetchData(_7,_3,_4)}
,isc.A.clearCriteria=function isc_Canvas_clearCriteria(_1,_2){this.$wo("filter",null,_1,_2)}
,isc.A.$wo=function isc_Canvas__filter(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;if(_4==null&&isc.isAn.Object(_3)&&_3.methodName==null)
{_4=_3;_3=null}
_4=this.buildRequest(_4,_1,_3);if(this.onFetchData!=null){this.onFetchData(_2,_4)}
var _5=this.getAllFields();if(_5!=null){for(var i=0;i<_5.length;i++){if(_5[i].includeFrom!=null&&this.getDataSource().getField(_5[i].name)==null)
{if(_4.additionalOutputs==null)_4.additionalOutputs="";else _4.additionalOutputs+=",";_4.additionalOutputs+=[_5[i].name,_5[i].includeFrom].join(":")}}}
if(_2==null){_2={}}else if(isc.isA.Class(_2)){_2=isc.DynamicForm.getFilterCriteria(_2)}
this.filterWithCriteria(_2,_4.operation,_4)}
,isc.A.filterWithCriteria=function isc_Canvas_filterWithCriteria(_1,_2,_3){_3.prompt=(_3.prompt||isc.RPCManager.fetchDataPrompt);var _4=_1;if(this.ignoreEmptyCriteria){_4=isc.DataSource.filterCriteriaForFormValues(_1)}else{_4=isc.addProperties({},_4)}
_4=isc.DS.checkEmptyCriteria(_4);var _5=this.getData();if(this.useExistingDataModel(_1,_2,_3)){var _6=this.updateDataModel(_4,_2,_3);if(_6!=null)_5=_6}else{_5=this.createDataModel(_4,_2,_3)}
this.setData(_5);if(!_3.$326&&this.requestVisibleRows!=null){var _7=this.data,_8=_7.fetchDelay;_7.fetchDelay=0;this.requestVisibleRows();_7.fetchDelay=_8}}
,isc.A.useExistingDataModel=function isc_Canvas_useExistingDataModel(_1,_2,_3){var _4=this.getData();if(!(isc.isA.ResultSet(_4)||isc.isA.ResultTree(_4))){_4=this.originalData;if(_4==null)return false;if(!isc.isA.ResultSet(_4)&&!isc.isA.ResultTree(_4))return false}
var _5=_4.getOperationId("fetch");return _5==null||_5==_2.ID}
,isc.A.createDataModel=function isc_Canvas_createDataModel(_1,_2,_3){if(this.logIsInfoEnabled("ResultSet")){this.logInfo("Creating new isc.ResultSet for operation '"+_2.ID+"' with filterValues: "+this.echoFull(_1),"ResultSet")}
var _4=this.getDataSource();if(!_4){this.logWarn("No DataSource or invalid DataSource specified, can't create data model"+this.getStackTrace());return null}
var _5=this.dataProperties||{};if(_5.context)_3=isc.addProperties({},_5.context,_3);if(this.dataFetchDelay)_5.fetchDelay=this.dataFetchDelay;isc.addProperties(_5,{operation:_2,filter:_1,context:_3,componentId:this.ID});if(this.getSort!=null){var _6=this.getSort();if(_6!=null&&_6.length>0){_5.$73p=_6;_5.$39x=isc.DS.getSortBy(_5.$73p)}}
return _4.getResultSet(_5)}
,isc.A.updateDataModel=function isc_Canvas_updateDataModel(_1,_2,_3){this.logDebug("Setting filter to: "+this.echoFull(_1));var _4=this.getData();if(!isc.isA.ResultSet(_4))_4=this.originalData;if(!isc.isA.ResultSet(_4)){return}
_4.setContext(_3);if(!_4.willFetchData(_1))delete _3.afterFlowCallback;_4.setCriteria(_1);return _4}
,isc.A.requestVisibleRows=function isc_Canvas_requestVisibleRows(){return this.data.get(0)}
,isc.A.invalidateCache=function isc_Canvas_invalidateCache(){if(this.data&&this.data.invalidateCache!=null)return this.data.invalidateCache();else if(this.isGrouped&&isc.isA.ResultSet(this.originalData)){this.originalData.invalidateCache();this.regroup()}}
,isc.A.willFetchData=function isc_Canvas_willFetchData(_1,_2){var _3=this.data;if(_3&&_3.willFetchData==null&&this.originalData!=null)_3=this.orginalData;if(_3&&_3.willFetchData!=null){return _3.willFetchData(_1,_2)}
return true}
,isc.A.findByKey=function isc_Canvas_findByKey(_1){return this.data.findByKey(_1)}
,isc.A.shouldSaveLocally=function isc_Canvas_shouldSaveLocally(){return(!this.dataSource||this.getFullDataPath()!=null||this.saveLocally)}
,isc.A.addData=function isc_Canvas_addData(_1,_2,_3){return this.$wp("add",_1,_2,_3)}
,isc.A.updateData=function isc_Canvas_updateData(_1,_2,_3){return this.$wp("update",_1,_2,_3)}
,isc.A.removeData=function isc_Canvas_removeData(_1,_2,_3){return this.$wp("remove",_1,_2,_3)}
,isc.A.$wp=function isc_Canvas__performDSOperation(_1,_2,_3,_4){if(isc.$cv)arguments.$cw=this;if(_4==null&&isc.isAn.Object(_3)&&_3.methodName==null)
{_4=_3;_3=null}
if(this.shouldSaveLocally()||this.getDataSource()==null){if(_1=="update"){var _5=this.getDataSource();if(!_5){isc.logWarn("Update by primary key cannot be performed without a DataSource."+"Modify the record directly instead");return}
var _6=this.data.get(_5.findByKeys(_2,this.data));isc.addProperties(_6,_2);return this.data.dataChanged()}else if(_1=="add"){if(this.originalData){this.originalData.add(_2);this.dataChanged("add",null,null,_2)}else{if(isc.isA.Tree(this.data)){var _7=this.data.getParent(_2)||this.data.getRoot();this.data.add(_2,_7)}else{this.data.add(_2)}}
return}}
_4=this.buildRequest(_4,_1);return this.getDataSource().performDSOperation(_1,_2,_3,_4)}
,isc.A.removeSelectedData=function isc_Canvas_removeSelectedData(_1,_2){if(_2==null&&isc.isAn.Object(_1)&&_1.methodName==null)
{_2=_1;_1=null}
var _3=this.getSelection();if(isc.isA.ListGrid(this)&&this.canEdit&&this.selectOnEdit&&(_3==null||_3.length==0)&&this.getEditRow()!=null&&this.getRecord(this.getEditRow())==null)
{this.discardEdits(this.getEditRow());return}
if(this.dataSource==null||this.shouldSaveLocally()){if(this.data){this.data.removeList(this.getSelection());if(_1)this.fireCallback(_1)}
return}
var _4=this.buildRequest(_2,"remove",_1),_5=this.getDataSource();if(_3.length>0)this.deleteRecords(_3,_4.operation,_4,_5)}
,isc.A.deleteRecords=function isc_Canvas_deleteRecords(_1,_2,_3,_4){isc.addProperties(_3,{prompt:(_3.prompt||isc.RPCManager.removeDataPrompt)});var _5=isc.RPCManager.startQueue();if(!isc.isAn.Array(_1))_1=[_1];for(var i=0;i<_1.length;i++){if(_1[i].$52e)continue;_4.performDSOperation(_2.type,_1[i],null,_3)}
if(!_5)isc.RPCManager.sendQueue()}
,isc.A.createSelectionModel=function isc_Canvas_createSelectionModel(){if(this.selection)this.destroySelectionModel();if(this.canSelectCells){var _1=[];if(this.numRows!=null){for(var i=0;i<this.numRows;i++){_1[i]={}}}}else{var _1=this.data}
var _3,_4={ID:this.getID()+"_selection",data:_1,target:this,selectionProperty:this.selectionProperty,simpleDeselect:this.simpleDeselect,dragSelection:this.canDragSelect};if(this.canSelectCells&&this.fields!=null)_4.numCols=this.fields.length;if(this.recordEnabledProperty!=null)_4.enabledProperty=this.recordEnabledProperty;if(this.recordCanSelectProperty!=null)_4.canSelectProperty=this.recordCanSelectProperty;if(this.cascadeSelection!=null)_4.cascadeSelection=this.cascadeSelection;if(this.data.getNewSelection){_3=this.data.getNewSelection(_4)}
if(_3==null){if(this.canSelectCells){_3=isc.CellSelection.create(_4)}else{_3=isc.Selection.create(_4)}}
this.selection=_3}
,isc.A.destroySelectionModel=function isc_Canvas_destroySelectionModel(){if(!this.selection)return;if(this.selection.destroy)this.selection.destroy();delete this.selection}
,isc.A.removeSelectionMarkers=function isc_Canvas_removeSelectionMarkers(_1){var _2=true;if(!isc.isAn.Array(_1)){_1=[_1];_2=false}
_1.clearProperty(this.selectionProperty||this.selection?this.selection.selectionProperty:null);return _2?_1:_1[0]}
,isc.A.getSelection=function isc_Canvas_getSelection(_1){if(!this.selection)return null;if(this.canSelectCells){var _2=this.selection.getSelectedCells();if(_2==null)return null;var _3=[];for(var i=0;i<_2.length;i++){var _5=_2[i],_6=this.getCellRecord(_5[0],_5[1]);if(_6==null)continue;_3.add(_6)}
return _3}else{return this.selection.getSelection(_1)}}
,isc.A.getSelectedRecords=function isc_Canvas_getSelectedRecords(_1){return this.getSelection(_1)}
,isc.A.getSelectedRecord=function isc_Canvas_getSelectedRecord(){if(!this.selection)return null;return this.selection.getSelectedRecord()}
,isc.A.getSelectionObject=function isc_Canvas_getSelectionObject(){return this.selection}
,isc.A.isSelected=function isc_Canvas_isSelected(_1){if(!_1||!this.selection)return false;return this.selection.isSelected(_1)}
,isc.A.isPartiallySelected=function isc_Canvas_isPartiallySelected(_1){if(!_1||!this.selection)return false;return this.selection.isPartiallySelected(_1)}
,isc.A.selectRecord=function isc_Canvas_selectRecord(_1,_2,_3){this.selectRecords(_1,_2,_3)}
,isc.A.selectSingleRecord=function isc_Canvas_selectSingleRecord(_1){this.selection.deselectAll();this.selectRecord(_1)}
,isc.A.deselectRecord=function isc_Canvas_deselectRecord(_1,_2){this.selectRecord(_1,false,_2)}
,isc.A.selectRecords=function isc_Canvas_selectRecords(_1,_2,_3){if(_2==null)_2=true;if(!isc.isAn.Array(_1))_1=[_1];if(isc.isA.ResultSet(this.data)&&!this.data.lengthIsKnown()){this.logWarn("ignoring attempt to select records while data is loading");return}
for(var i=0;i<_1.length;i++){if(_1[i]==null)continue;if(isc.isA.Number(_1[i])){var _5=_1[i];_1[i]=this.getRecord(_5,_3)}}
var _6=this.getSelectionObject(_3);if(_6){_6.selectList(_1,_2,_3);this.fireSelectionUpdated()}}
,isc.A.deselectRecords=function isc_Canvas_deselectRecords(_1,_2){this.selectRecords(_1,false)}
,isc.A.selectAllRecords=function isc_Canvas_selectAllRecords(){this.selection.selectAll();this.fireSelectionUpdated()}
,isc.A.deselectAllRecords=function isc_Canvas_deselectAllRecords(){this.selection.deselectAll();this.fireSelectionUpdated()}
,isc.A.anySelected=function isc_Canvas_anySelected(){return this.selection&&this.selection.anySelected()}
,isc.A.getRecord=function isc_Canvas_getRecord(_1,_2){if(this.data)return this.data.get(_1);return null}
,isc.A.fireSelectionUpdated=function isc_Canvas_fireSelectionUpdated(){if(this.selectionUpdated){var _1=this.getSelection(),_2=(_1&&_1.length>0?_1[0]:null);this.selectionUpdated(_2,_1)}}
,isc.A.getHilites=function isc_Canvas_getHilites(){return this.hilites}
,isc.A.setHilites=function isc_Canvas_setHilites(_1){this.hilites=_1;this.$63j(this.hilites)}
,isc.A.getHiliteState=function isc_Canvas_getHiliteState(){var _1=this.getHilites();if(_1==null)return null;return"("+isc.JSON.encode(_1,{dateFormat:"dateConstructor",prettyPrint:false})+")"}
,isc.A.setHiliteState=function isc_Canvas_setHiliteState(_1){if(_1==null)this.setHilites(null);var _2=eval(_1);this.setHilites(_2)}
,isc.A.$63j=function isc_Canvas__setupHilites(_1,_2){if(_1!=null){for(var i=0;i<_1.length;i++){if(_1[i].id==null){this.$63k=this.$63k||0;_1[i].id=this.$63k++}}
this.$58b=_1.makeIndex("id")}
if(!_2)this.applyHilites()}
,isc.A.applyHilites=function isc_Canvas_applyHilites(_1){var _2=this.hilites,_3=this.data;if(_2&&!this.$58b)this.$63j(_2,true);if(isc.isA.ResultSet(_3))_3=_3.getAllLoadedRows();if(isc.isA.Tree(_3))_3=_3.getAllItems();_3.setProperty(this.hiliteMarker,null);var _4=this.getAllFields();for(var i=0;i<_4.length;i++){var _6=_4[i],_7=_6[this.fieldIdProperty];if(_6.userFormula||_6.userSummary){if(_6.userSummary&&!_6.$652)
this.getSummaryFunction(_6);for(var j=0;j<_3.length;j++){if(_6.userFormula)
_3[j][_7]=this.getFormulaFieldValue(_6,_3[j]);else
_3[j][_7]=_6.$652(_3[j],_7,this)}}}
if(_2!=null){for(var i=0;i<_2.length;i++){this.applyHilite(_2[i],_3)}}
if(!_1)this.redrawHilites()}
,isc.A.getHilite=function isc_Canvas_getHilite(_1){if(isc.isAn.Object(_1))return _1;if(this.hilites==null)return null;if(!this.$58b&&this.hilites){this.$63j(this.hilites)}
var _2=this.$58b[_1];if(_2==null)_2=this.hilites[_1];return _2}
,isc.A.applyHilite=function isc_Canvas_applyHilite(_1,_2,_3){_1=this.getHilite(_1);if(!_1.criteria)return;if(_1.disabled)return;var _3=_3||_1.fieldName;if(_3==null)_3=this.fields.getProperty("name");var _4=[];if(this.getDataSource()){_4=this.getDataSource().applyFilter(_2,_1.criteria)}else{_4=this.unboundApplyFilter(_2,_1.criteria)}
var _5=isc.isAn.Array(_3)?_3:[_3];if(this.logIsDebugEnabled("hiliting")){this.logDebug("applying filter: "+this.echoFull(_1.criteria)+", produced matches: "+isc.echoLeaf(_4)+", on fields: "+_5,"hiliting")}
for(var j=0;j<_5.length;j++){var _7=this.getField(_5[j]);for(var i=0;i<_4.length;i++){var _9=_4[i];this.hiliteRecord(_9,_7,_1)}}}
,isc.A.unboundApplyFilter=function isc_Canvas_unboundApplyFilter(_1,_2){var _3=[];if(_1){if(_2){for(var _4=0;_4<_1.length;_4++){if(this.evaluateCriterion(_1[_4],_2)){_3.add(_1[_4])}}}else{_3=_1}}
return _3}
,isc.A.evaluateCriterion=function isc_Canvas_evaluateCriterion(_1,_2){var _3=isc.DataSource.$57z[_2.operator];if(_3==null){isc.logWarn("Attempted to use unknown operator "+_2.operator);return false}
var _4=this.getDataSource();return _3.condition(_2.value,_1,_2.fieldName,_2,_3,_4||this)}
,isc.A.compareValues=function isc_Canvas_compareValues(_1,_2,_3,_4){if(isc.isA.Date(_1)&&isc.isA.Date(_2)){if(_1.logicalDate||_2.logicalDate){return Date.compareLogicalDates(_1,_2)}else{return Date.compareDates(_1,_2)}}else{var _5=_4&&_1.toLowerCase?_1.toLowerCase():_1,_6=_4&&_2.toLowerCase?_2.toLowerCase():_2;if(_5==null&&_6!=null)return 1;if(_5!=null&&_6==null)return-1;return _5>_6?-1:(_5<_6?1:(_5==_6?0:2))}}
,isc.A.hiliteRecord=function isc_Canvas_hiliteRecord(_1,_2,_3){if(!_2)return;var _4=_1[this.hiliteMarker];if(_4==null)_4=_1[this.hiliteMarker]=this.$578++;var _5=_2.$58c=_2.$58c||{},_6=_5[_4];if(_6==null)_5[_4]=_3.id;else _5[_4]=[_6,_3.id]}
,isc.A.getHiliteCSSText=function isc_Canvas_getHiliteCSSText(_1){var _1=this.getHilite(_1);if(!_1)return;var _2=_1.cssText||"";if(_2==""){if(_1.textColor)_2+="color:"+_1.textColor+";";if(_1.backgroundColor)_2+="background-color:"+_1.backgroundColor+";";if(_2=="")_2==null}
return _2||_1.style}
,isc.A.addHiliteCSSText=function isc_Canvas_addHiliteCSSText(_1,_2,_3){if(!_1)return _3;var _4=_1[this.hiliteMarker],_2=this.getField(_2);if(!_2||!_2.$58c)return _3;var _5=_2.$58c[_4];if(_5==null)return _3;if(!isc.isAn.Array(_5)){this.$579[0]=_5;_5=this.$579}
for(var i=0;i<_5.length;i++){var _7=this.getHiliteCSSText(_5[i]);if(_7!=null){_3=_3?_3+isc.semi+_7:_7}}
return _3}
,isc.A.addObjectHilites=function isc_Canvas_addObjectHilites(_1,_2,_3){if(!this.hilites||!_1)return _2;var _4;if(!isc.isAn.Array(_1)){this.$579[0]=_1;_4=this.$579}
if(_4&&_4.length>0){for(var i=0;i<_4.length;i++){var _6,_7,_8;var _9=_4[i];if(isc.isA.String(_9))_6=_9;else _6=(_9!=null?_9[this.hiliteProperty]:null);_7=this.getHilite(_6);if(_7!=null&&!_7.disabled){_8=_7.cssText||_7.style;var _10=[];if(_7)
_10=isc.isAn.Array(_7.fieldName)?_7.fieldName:[_7.fieldName];var _11=(!_7.fieldName||!_3||_10.contains(_3.name));if(_8!=null&&_8!=isc.emptyString&&_11){if(_2==null)_2=_8;else _2+=isc.semi+_8}}}}
return _2}
,isc.A.getFieldHilites=function isc_Canvas_getFieldHilites(_1,_2){if(!_1||!_2)return null;if(_1[this.hiliteProperty]!=null){var _3=this.getHilite(_1[this.hiliteProperty]),_4;if(_3)
_4=isc.isAn.Array(_3.fieldName)?_3.fieldName:[_3.fieldName];if(_4&&_4.contains(_2.name))return[_3];else return null}
if(_1[this.hiliteMarker]!=null){var _5=_1[this.hiliteMarker];if(!_2.$58c)return null;else return _2.$58c[_5]}}
,isc.A.applyHiliteHTML=function isc_Canvas_applyHiliteHTML(_1,_2){if(!this.hilites)return _2;var _3,_4,_5;if(!isc.isAn.Array(_1)){this.$579[0]=_1;_1=this.$579}
for(var i=0;i<_1.length;i++){_5=_1[i];_3=this.getHilite(_5);if(_3!=null){if(_3.htmlValue!=null)_2=_3.htmlValue;if(!_3.disabled){_4=_3.htmlBefore;if(_4!=null&&_4.length>0){_2=_4+_2}
_4=_3.htmlAfter;if(_4!=null&&_4.length>0){_2=_2+_4}
var _7=_3.htmlOpposite,_8=_3.styleOpposite||this.styleOpposite;if(_7){if(!isc.Browser.isIE){_2="<nobr><div class='"+_8+"' style='float:left'>&nbsp;"+_7+"&nbsp;</div>"+_2+"</nobr>"}else{_2="<nobr><table role='presentation' align=left><tr><td class='"+_8+"'>"+_7+"</td></tr></table>"+_2+"</nobr>"}}}}}
return _2}
,isc.A.enableHilite=function isc_Canvas_enableHilite(_1,_2){if(_2==null)_2=true;var _3=this.getHilite(_1);if(_3==null)return;_3.disabled=!_2;this.redrawHilites()}
,isc.A.disableHilite=function isc_Canvas_disableHilite(_1){this.enableHilite(_1,false)}
,isc.A.enableHiliting=function isc_Canvas_enableHiliting(_1){if(_1==null)_1=true;if(this.hilites)this.hilites.setProperty("disabled",!_1);this.redrawHilites()}
,isc.A.disableHiliting=function isc_Canvas_disableHiliting(){this.enableHiliting(false)}
,isc.A.redrawHilites=function isc_Canvas_redrawHilites(){this.markForRedraw()}
,isc.A.editHilites=function isc_Canvas_editHilites(){var _1=this.getAllFields();if(!_1)return;var _2=_1?_1.findAll("canHilite",false):null;if(_2&&_2.length>0){_1.removeList(_2)}
var _3=isc.DataSource.create({fields:_1});if(this.hiliteWindow){this.hiliteEditor.setDataSource(_3);this.hiliteEditor.clearHilites();this.hiliteEditor.setHilites(this.getHilites());this.hiliteWindow.show();return}
var _4=this,_5=this.hiliteEditor=isc.HiliteEditor.create({autoDraw:false,dataSource:_3,hilites:this.getHilites(),callback:function(_7){if(_7!=null)_4.setHilites(_7);_4.hiliteWindow.hide()}}),_6=this.hiliteWindow=isc.Window.create({autoDraw:true,items:[_5],autoSize:true,autoCenter:true,isModal:true,showModalMask:true,closeClick:function(){this.hide()},title:"Edit Highlights",bodyProperties:{layoutMargin:8,membersMargin:8}});return _6}
);isc.evalBoundary;isc.B.push(isc.A.transferRecords=function isc_Canvas_transferRecords(_1,_2,_3,_4,_5){if(!this.$67u("transferRecords",_1,_2,_3,_4,_5)){return}
if(isc.isAn.Array(this.data)&&this.data.length==0&&this.dataSource&&!this.shouldSaveLocally())
{this.fetchData(null,null,{$326:true});this.data.setFullLength(0)}
if(_4==this){if(_3!=null&&!this.isGrouped)this.data.slideList(_1,_3)}else{var _6=this.getDataSource();var _7=_4.getDataSource();if(_6&&_6==_7&&_4.dragDataAction==isc.Canvas.MOVE&&!(_4.shouldSaveLocally()||this.shouldSaveLocally()))
{var _8=isc.rpc.startQueue();for(var i=0;i<_1.length;i++){var _10={};var _11=_6.getPrimaryKeyFieldNames();for(var j=0;j<_11.length;j++){_10[_11[j]]=_1[i][_11[j]]}
isc.addProperties(_10,this.getDropValues(_10,_7,_2,_3,_4));this.updateDataViaDataSource(_10,_7,null,_4)}
if(!_8)isc.rpc.sendQueue()}else{if(!isc.isAn.Array(_1))_1=[_1];var _13=true;if(_7!=null&&_6!=null){var _14=_6.getPrimaryKeyField();_13=_14&&(_7.getField(_14.name)!=null)}
if(_13){if(this.selectionType==isc.Selection.MULTIPLE||this.selectionType==isc.Selection.SIMPLE)
{this.selection.deselectAll();this.selection.selectList(_1)}else if(this.selectionType==isc.Selection.SINGLE){this.selection.selectSingle(_1[0])}}
if(_6){this.$67o=isc.rpc.startQueue();for(var i=0;i<_1.length;i++){if(_1[i].$52e)continue;var _10={};isc.addProperties(_10,_1[i]);isc.addProperties(_10,this.getDropValues(_10,_7,_2,_3,_4));if(_6!=_7){var _15=_6.getForeignKeysByRelation(_10,_7);var _16=false;var _17=[];if(_7)_17=_7.getPrimaryKeyFields();isc.addProperties(_10,_15);if(_6.titleField&&_7&&_7.titleField&&_6.titleField!=_7.titleField){var _18;if(_10[_6.titleField]===_18){_10[_6.titleField]=_10[_7.titleField]}}}
this.$61d(_10,_7,_4,_15)}}else{if(this.isGrouped){for(var i=0;i<_1.length;i++){var _10={};isc.addProperties(_10,_1[i]);isc.addProperties(_10,this.getDropValues(_10,_7,_2,_3,_4));if(!this.$61e(_10)){this.$52u(_10,true);this.originalData.add(_10)}}}else{for(var i=0;i<_1.length;i++){var _10={};isc.addProperties(_10,_1[i]);isc.addProperties(_10,this.getDropValues(_10,_7,_2,_3,_4));if(_3!=null){if(this.$61d(_10,null,_4,null,_3)){_3++}}else{this.$61d(_10,null,_4)}}}}}}
if(this.canReorderRecords&&this.$60z()!=null){this.unsort()}
if(!this.$67l){isc.Log.logDebug("Invoking transferDragData from inside transferRecords - no server "+"queries needed?","dragDrop");_4.transferDragData(this.$67n,this);if(_6){if(!this.$67o)isc.rpc.sendQueue()}}
this.$67m=false}
,isc.A.$67u=function isc_Canvas__storeTransferState(_1,_2,_3,_4,_5,_6){if(!isc.isAn.Array(this.$67k))this.$67k=[];if(this.$67l&&this.$67l!=0){isc.logWarn("transferRecords was invoked but the prior transfer is not yet complete - \
                     the transfer will be queued up to run after the current transfer");this.$67k.add({implementation:_1,dropRecords:_2,targetRecord:_3,index:_4,sourceWidget:_5,callback:_6});return false}
this.$67k.addAt({implementation:_1,dropRecords:_2,targetRecord:_3,index:_4,sourceWidget:_5,callback:_6},0);this.$67m=true;this.$67n=[];this.$67l=0;return true}
,isc.A.updateDataViaDataSource=function isc_Canvas_updateDataViaDataSource(_1,_2,_3,_4){var _5=this;if(this.updateOperation){if(_3==null)_3={};isc.addProperties(_3,{operationId:this.updateOperation})}
if(!this.preventDuplicates){if(!_4.$67v)_4.$67v=0;_4.$67v++;_2.updateData(_1,function(_7,_8,_9){_4.$67w(_7,_8,_9)},_3);return}
var _6=this.getCleanRecordData(_1);if(this.data.find(_6,null,Array.DATETIME_VALUES)){isc.Log.logDebug("Found client-side duplicate, skipping update for '"+_1[isc.firstKey(_1)]+"'","dragDrop");this.$67n.add(this.getCleanRecordData(_1))}else{if(this.data.allMatchingRowsCached()){if(!_4.$67v)_4.$67v=0;_4.$67v++;_2.updateData(_1,function(_7,_8,_9){_4.$67w(_7,_8,_9)},_3)}else{isc.Log.logDebug("Incrementing dup query count: was "+_5.$67l,"dragDrop");this.$67l++;_2.fetchData(_6,function(_7,_8,_9){if(_8&&_8.length>0){isc.Log.logDebug("Found server-side duplicate, skipping update for '"+_1[isc.firstKey(_1)]+"'","dragDrop");_5.$67n.add(_5.getCleanRecordData(_8[0]))}else{if(!_4.$67v)_4.$67v=0;_4.$67v++;_2.updateData(_1,function(_7,_8,_9){_4.$67w(_7,_8,_9)},_3)}
isc.Log.logDebug("Decrementing dup query count: was "+_5.$67l,"dragDrop");if(--_5.$67l==0&&!_5.$67m){if(_4.dragDataAction==isc.Canvas.MOVE){isc.Log.logDebug("Invoking transferDragData from inside callback","dragDrop");_4.transferDragData(_5.$67n,_5);delete _5.$67n;if(!_5.$67o)isc.rpc.sendQueue()}}},{sendNoQueue:true})}}}
,isc.A.$61d=function isc_Canvas__addIfNotDuplicate(_1,_2,_3,_4,_5,_6){var _7=this.getDataSource(),_8,_9=this,_10={};if(this.addOperation){isc.addProperties(_10,{operationId:this.addOperation})}
if(_7)_8=_7.getPrimaryKeyFields();if(_7){var _11;if(_8&&isc.firstKey(_8)!=null){for(var _12 in _8){if(_8[_12].type=="sequence"){_11=true;break}}}
if(_11){var _13;for(var _12 in _8){_1[_12]=_13}
if(!_3.$67v)_3.$67v=0;_3.$67v++;this.addData(_1,function(_15,_16,_17){_3.$67w(_15,_16,_17)});return true}}
if(!this.preventDuplicates){if(_7){if(!_3.$67v)_3.$67v=0;_3.$67v++;this.addData(_1,function(_15,_16,_17){_3.$67w(_15,_16,_17)},_10)}else{if(isc.Tree&&isc.isA.Tree(this.data)){this.data.add(_1,_6,_5)}else{if(_5!=null)this.data.addAt(_1,_5);else this.data.add(_1)}}
return true}
if(this.$61e(_1,_2,_4)){if(this.duplicateDragMessage!=null)isc.warn(this.duplicateDragMessage);isc.Log.logDebug("Found client-side duplicate, adding '"+_1[isc.firstKey(_1)]+"' to exception list","dragDrop");this.$67n.add(this.getCleanRecordData(_1));return false}else{if(!_7){if(isc.Tree&&isc.isA.Tree(this.data)){this.data.add(_1,_6,_5);return true}else{if(_5!=null)this.data.addAt(_1,_5);else this.data.add(_1);return true}}else{if(!isc.ResultSet||!isc.isA.ResultSet(this.data)){if(!_3.$67v)_3.$67v=0;_3.$67v++;this.addData(_1,function(_15,_16,_17){_3.$67w(_15,_16,_17)},_10);return true}else{if(this.data.allRowsCached()||(_4&&isc.firstKey(_4)&&this.data.allMatchingRowsCached())){if(!_3.$67v)_3.$67v=0;_3.$67v++;this.addData(_1,function(_15,_16,_17){_3.$67w(_15,_16,_17)},_10);return true}
if(_7&&_2==_7){if(_8&&isc.firstKey(_8)!=null){var _14=isc.applyMask(_1,_8)}else{_14=this.getCleanRecordData(_1)}}else if(_4&&isc.firstKey(_4)){_14=isc.addProperties({},this.data.getCriteria());isc.addProperties(_14,_4)}else if(_7&&_8&&isc.firstKey(_8)!=null){_14=isc.applyMask(_1,_8)}else{_14=this.getCleanRecordData(_1)}
isc.Log.logDebug("Incrementing dup query count: was "+_9.$67l,"dragDrop");this.$67l++;_7.fetchData(_14,function(_15,_16,_17){if(_16&&_16.length>0){if(_9.duplicateDragMessage!=null)isc.warn(_9.duplicateDragMessage);isc.Log.logDebug("Found server-side duplicate, adding '"+_1[isc.firstKey(_1)]+"' to exception list","dragDrop");_9.$67n.add(_9.getCleanRecordData(_1))}else{if(!_3.$67v)_3.$67v=0;_3.$67v++;_7.addData(_1,function(_15,_16,_17){_3.$67w(_15,_16,_17)},_10)}
isc.Log.logDebug("Decrementing dup query count: was "+_9.$67l,"dragDrop");if(--_9.$67l==0&&!_9.$67m){if(_3.dragDataAction==isc.Canvas.MOVE){isc.Log.logDebug("Invoking transferDragData from inside callback","dragDrop");_3.transferDragData(_9.$67n,_9);delete _9.$67n;if(!_9.$67o)isc.rpc.sendQueue()}}},{sendNoQueue:true})}}}}
,isc.A.$61e=function isc_Canvas__isDuplicateOnClient(_1,_2,_3){var _4=this.getDataSource(),_5;if(!this.preventDuplicates)return false;if(_4)_5=_4.getPrimaryKeyFields();if(_4&&_4==_2){if(_5&&isc.firstKey(_5)!=null){for(var _6 in _5){if(_5[_6].type=="sequence"){return false}}}}
if(!_4){var _7=this.getCleanRecordData(_1)}else if(_4&&_2==_4){if(_5&&isc.firstKey(_5)!=null){_7=isc.applyMask(_1,_5)}else{_7=this.getCleanRecordData(_1)}}else if(_3&&isc.firstKey(_3)){_7={};var _8=this.data.getCriteria();if(!_4.isAdvancedCriteria(_8)){var _9=this.data.context;if(_9&&(_9.textMatchStyle==null||_9.textMatchStyle=="exact")){isc.addProperties(_7,_8)}}
isc.addProperties(_7,_3)}else if(_4&&_5&&isc.firstKey(_5)!=null){_7=isc.applyMask(_1,_5)}else{_7=this.getCleanRecordData(_1)}
if(this.data.find(_7,null,Array.DATETIME_VALUES))return true;else return false}
,isc.A.getCleanRecordData=function isc_Canvas_getCleanRecordData(_1){if(isc.Tree&&isc.isA.Tree(this.data)){return this.data.getCleanNodeData(_1,false)}
var _2={};for(var _3 in _1){if(_3.startsWith("_selection_"))continue;if(_3=="$81y")continue;if(_3=="$29a")continue;_2[_3]=_1[_3]}
return _2}
,isc.A.$67w=function isc_Canvas__updateComplete(_1,_2,_3){if(this.$67v){isc.Log.logDebug("Decrementing update count - was "+this.$67v,"dragDrop");this.$67v-=1}
if(!this.$67v){isc.Log.logDebug("All updates complete, calling dragComplete()","dragDrop");if(isc.isA.Function(this.dragComplete))this.dragComplete()}}
,isc.A.getDropValues=function isc_Canvas_getDropValues(_1,_2,_3,_4,_5,_6){if(!this.addDropValues)return;var _7={},_8;if(this.data&&this.data.getNodeDataSource){_8=this.data.getNodeDataSource(_3)}
if(!_8){_8=this.getDataSource()}
if(this.data&&this.data.getCriteria)_7=this.data.getCriteria(_8);var _9;if(isc.isAn.emptyObject(_7)||(_8&&!_8.isAdvancedCriteria(_7))){var _10=this.data.context;if(_10&&(_10.textMatchStyle==null||_10.textMatchStyle=="exact")){_9=isc.addProperties({},_7);if(this.dropValues){_9=isc.addProperties(_9,this.dropValues)}
return _9}}
return this.dropValues}
,isc.A.transferDragData=function isc_Canvas_transferDragData(_1,_2){var _3=[],_4,_5,_6;if(_2&&_2.$67k){_6=_2.$67k.shift();_4=_6.dropRecords;_5=_6.callback}else{_4=this.getDragData();_6={}}
if(_4==null)_4=[];for(var i=0;i<_4.length;i++){var _8=this.getCleanRecordData(_4[i]);if(!_1||!_1.find(_8,null,Array.DATETIME_VALUES)){_3.add(_4[i])}}
if(this.dragDataAction==isc.Canvas.MOVE&&_2!=this&&!_6.noRemove){if(this.dataSource&&!this.shouldSaveLocally()){var _9=_2.getDataSource();if(_9!=this.getDataSource()){var _10=isc.rpc.startQueue();for(var i=0;i<_3.length;i++){this.getDataSource().removeData(_3[i])}
if(!_10)isc.rpc.sendQueue()}}else if(this.data){for(var i=0;i<_3.length;i++){this.data.remove(_3[i]);if(this.isGrouped){this.originalData.remove(_3[i])}}}
if(this.selection&&this.selection.deselectList){this.selection.deselectList(_4)}}
if(_2){if(isc.isA.Function(_2.dropComplete))_2.dropComplete(_3);if(_5){this.fireCallback(_5,"records",[_3])}
if(_2.$67k&&_2.$67k.length>0){var _11=_2.$67k.shift();isc.Timer.setTimeout(function(){if(_11.implementation=="transferNodes"){_2.transferNodes(_11.dropRecords,_11.targetRecord,_11.index,_11.sourceWidget,_11.callback)}else{_2.transferRecords(_11.dropRecords,_11.targetRecord,_11.index,_11.sourceWidget,_11.callback)}},0)}}
return _3}
,isc.A.getDragData=function isc_Canvas_getDragData(){var _1=(this.selection&&this.selection.getSelection)?this.selection.getSelection():null;return _1}
,isc.A.cloneDragData=function isc_Canvas_cloneDragData(){var _1=this.$758;if(_1==null){_1=(this.selection&&this.selection.getSelection)?this.selection.getSelection():null}
this.$758=null;var _2=this.dragDataAction==isc.Canvas.COPY||this.dragDataAction==isc.Canvas.CLONE;var _3=[]
if(_2&&_1){if(isc.isA.Tree(this.data)){_1=this.data.getCleanNodeData(_1)}else{if(!isc.isAn.Array(_1))_1=[_1];var _4=[];for(var i=0;i<_1.length;i++){_4[i]=this.getCleanRecordData(_1[i])}
_1=_4}}
return _1}
,isc.A.transferSelectedData=function isc_Canvas_transferSelectedData(_1,_2,_3){if(!this.isValidTransferSource(_1)){if(_3)this.fireCallback(_3);return}
if(_2!=null)_2=Math.min(_2,this.data.getLength());var _4=_1.cloneDragData();var _5;if(_2!=null)_5=this.data.get(_2);this.transferRecords(_4,_5,_2,_1,_3)}
,isc.A.isValidTransferSource=function isc_Canvas_isValidTransferSource(_1){if(!_1||!_1.transferDragData){this.logWarn("transferSelectedData(): "+(_1?"Invalid ":"No ")+"source widget passed in - "+(_1||"")+" taking no action.");return false}
if(_1==this){this.logWarn("transferSelectedData(): target parameter contains a pointer back to this grid - ignoring");return false}
return true}
,isc.A.setDragTracker=function isc_Canvas_setDragTracker(){var _1=isc.EH,_2=this.dragTrackerMode;if(_2=="none"||_1.dragOperation==_1.DRAG_SCROLL){_1.setDragTracker("");return false}else if(_2=="icon"){var _3=this.getSelection(),_4=this.getDragTrackerIcon(_3);_1.setDragTracker(this.imgHTML(_4),null,null,null,null,this.getDragTrackerProperties());return false}else{var _5=this.getSelectedRecord(),_6=_5&&this.data?this.data.indexOf(_5):-1;if(_2=="title"){var _7=this.getDragTrackerTitle(_5,_6);_1.setDragTracker(_7,null,null,null,null,this.getDragTrackerProperties());return false}else if(_2=="record"){var _8=this.body.getTableHTML([0,this.fields.length-1],_6,_6+1);_1.setDragTracker(_8,null,null,null,null,this.getDragTrackerProperties());return false}}}
,isc.A.getDragTrackerProperties=function isc_Canvas_getDragTrackerProperties(){var _1=isc.addProperties({},this.dragTrackerProperties);_1.styleName=this.dragTrackerStyle;if(this.dragTrackerMode=="record")_1.opacity=50;return _1}
,isc.A.makeDragLine=function isc_Canvas_makeDragLine(){if(this._dragLine)return false;var _1={ID:this.getID()+"_dragLine",width:2,height:2,overflow:isc.Canvas.HIDDEN,visibility:isc.Canvas.HIDDEN,isMouseTransparent:true,dropTarget:this,redrawOnResize:false,styleName:"dragLine"};if(this.ns.Element.getStyleEdges(_1.styleName)==null){_1.backgroundColor="black"}
isc.addProperties(_1,this.dragLineDefaults,this.dragLineProperties);this._dragLine=this.ns.Canvas.create(_1);return true}
,isc.A.hideDragLine=function isc_Canvas_hideDragLine(){if(this._dragLine)this._dragLine.hide()}
,isc.A.configureFrom=function isc_Canvas_configureFrom(_1){var _2=this.dbcProperties;for(var i=0;i<_2.length;i++){this[_2[i]]=_1[_2[i]];if(_2[i]=="dataSource"){var _4=this.autoFetchData;this.autoFetchData=false;this.setDataSource(isc.DS.getDataSource(this.dataSource));this.autoFetchData=_4}}
this.setCriteria(_1.getCriteria());this.setData(_1.getData())}
,isc.A.addFormulaField=function isc_Canvas_addFormulaField(){this.editFormulaField()}
,isc.A.editFormulaField=function isc_Canvas_editFormulaField(_1){if(isc.FormulaBuilder==null)return;var _2=this,_3=!_1?false:true;if(!_3){_1={name:_2.getUniqueFieldName(this.formulaFieldNamePrefix),title:"New Field",width:"50",canFilter:false,canExport:false,canSortClientOnly:true}}
this.$65y=isc.Window.create({title:"Formula Editor ["+_1.title+"]",showMinimizeButton:false,showMaximizeButton:false,isModal:true,showModalMask:true,autoSize:true,autoCenter:true,autoDraw:true,headerIconProperties:{padding:1,src:"[SKINIMG]ListGrid/formula_menuItem.png"},closeClick:function(){this.items.get(0).completeEditing(true);return this.Super('closeClick',arguments)},items:[isc.FormulaBuilder.create({width:300,component:_2,dataSource:_2.getDataSource(),editMode:_3,field:_1,mathFunctions:isc.MathFunction.getDefaultFunctionNames(),fireOnClose:function(){_2.userFieldCallback(this)}},this.formulaBuilderProperties)]},this.formulaBuilderProperties)}
,isc.A.getFormulaFieldValue=function isc_Canvas_getFormulaFieldValue(_1,_2){return this.getFormulaFunction(_1)(_2,this)}
,isc.A.getFormulaFunction=function isc_Canvas_getFormulaFunction(_1){if(!_1.userFormula)return null;var _2=_1.$65w;if(_2!=null)return _2;_2=_1.$65w=_1.sortNormalizer=isc.FormulaBuilder.generateFunction(_1.userFormula,this.getAllFields(),this);return _2}
,isc.A.addSummaryField=function isc_Canvas_addSummaryField(){this.editSummaryField()}
,isc.A.editSummaryField=function isc_Canvas_editSummaryField(_1){if(isc.FormulaBuilder==null)return;var _2=this,_3=!_1?false:true;if(isc.isA.String(_1)){_1=this.getField(_1)}
if(!_3){_1={name:_2.getUniqueFieldName(this.summaryFieldNamePrefix),title:"New Field",width:"50",canFilter:false,canExport:false,canSortClientOnly:true}}
this.$65y=isc.Window.create({title:"Summary Editor ["+_1.title+"]",showMinimizeButton:false,showMaximizeButton:false,isModal:true,showModalMask:true,autoSize:true,autoCenter:true,autoDraw:true,headerIconProperties:{padding:1,src:"[SKINIMG]ListGrid/formula_menuItem.png"},closeClick:function(){this.items.get(0).completeEditing(true);return this.Super('closeClick',arguments)},items:[isc.SummaryBuilder.create({width:300,component:_2,dataSource:_2.getDataSource(),editMode:_3,field:_1,fireOnClose:function(){_2.userFieldCallback(this)}},this.summaryBuilderProperties)]},this.summaryEditorProperties)}
,isc.A.userFieldCallback=function isc_Canvas_userFieldCallback(_1){if(!_1)return;var _2=this.$65y;if(_1.cancelled){_2.destroy();return}
var _3=_1.getUpdatedFieldObject();if(this.userAddedField&&this.userAddedField(_3)==false){_2.destroy();return}
if(this.hideField&&_1.shouldHideUsedFields()){var _4=_1.getUsedFields();for(var i=0;i<_4.length;i++){var _6=_4.get(i);this.hideField(_6.name)}}
var _7=this.getAllFields();var _8=isc.Class.getArrayItemIndex(_3.name,_7,this.fieldIdProperty);if(_8>=0)_7[_8]=_3;else _7.addAt(_3,this.getFields().length);this.setFields(_7);if(this.markForRedraw)this.markForRedraw();var _9=_1.restartBuilder,_10=_1.builderTypeText;_2.destroy();if(_9){if(_10=="Formula")this.addFormulaField();else this.addSummaryField()}}
,isc.A.getUniqueFieldName=function isc_Canvas_getUniqueFieldName(_1){if(!_1||_1=="")_1=this.uniqueFieldNamePrefix;var _2=this.getFields(),_3=1,_4=_1.length;for(var i=0;i<_2.length;i++){var _6=_2.get(i);if(_6.name.startsWith(_1)){var _7=_6.name.substr(_4),_8=new Number(_7);if(_8&&_8>=_3)_3=_8+1}}
return _1+_3}
,isc.A.getSummaryFunction=function isc_Canvas_getSummaryFunction(_1){if(!_1.userSummary)return null;var _2=_1.$652;if(_2!=null)return _2;_2=_1.$652=_1.sortNormalizer=isc.SummaryBuilder.generateFunction(_1.userSummary,this.getAllFields(),this);return _2}
,isc.A.getSummaryFieldValue=function isc_Canvas_getSummaryFieldValue(_1,_2){return this.getSummaryFunction(_1)(_2,_1[this.fieldIdProperty],this)}
,isc.A.getRecordIndex=function isc_Canvas_getRecordIndex(_1){return this.data.indexOf(_1)}
,isc.A.getTitleFieldValue=function isc_Canvas_getTitleFieldValue(_1){}
,isc.A.getTitleField=function isc_Canvas_getTitleField(){if(this.titleField!=null)return this.titleField;if(this.dataSource!=null){var _1=this.getDataSource().getTitleField();if(!this.getField(_1))_1=this.getFields()[0][this.fieldIdProperty];this.titleField=_1}else{var _2=this.getFields().getProperty(this.fieldIdProperty);this.titleField=_2.contains("title")?"title":_2.contains("label")?"label":_2.contains("name")?"name":_2.contains("id")?"id":_2.first()}
return this.titleField}
,isc.A.getRecordHiliteCSSText=function isc_Canvas_getRecordHiliteCSSText(_1,_2,_3){_2=this.addObjectHilites(_1,_2,_3);_2=this.addHiliteCSSText(_1,_3,_2);return _2}
,isc.A.convertCSSToProperties=function isc_Canvas_convertCSSToProperties(_1,_2){if(_1==null)return null;var _3=_1.split(";"),_4;_3.map(function(_9){var _5=_9.split(":");if(_5.length!=2)return null;var _6=/^\s*(\S*)\s*$/,_7=_5[0].cssToCamelCaps().replace(_6,"$1"),_8=_5[1].replace(_6,"$1");if(!_2||_2.contains(_7)){if(!_4)_4={};_4[_7]=_8}});return _4}
,isc.A.getExportFieldValue=function isc_Canvas_getExportFieldValue(_1,_2,_3){return this.htmlUnescapeExportFieldValue(this.getStandaloneFieldValue(_1,_2,false))}
,isc.A.addDetailedExportFieldValue=function isc_Canvas_addDetailedExportFieldValue(_1,_2,_3,_4,_5,_6,_7,_8){var _9=_4.name,_10=this.getRecordHiliteCSSText(_3,null,_4),_11,_12={};if(!_8){_12=this.getDateFormattingProperties(_4,_3[_4.name],_1[_4.title])}
if(_4.exportRawValues||(this.exportRawValues&&_4.exportRawValues!=false))
_11=_3[_4[this.fieldIdProperty]];else
_11=this.getExportFieldValue(_3,_4.name,_5);if(!_4.userSummary){if(_10||_12){var _13=this.convertCSSToProperties(_10,_6);if(_12){if(!_13)_13={};isc.addProperties(_13,_12)}
if(_13){if(_7)
_1[_2]=[{value:_11,style:_13}];else
_1[_2]=_13}}
return}
if(!_4.userSummary.text)this.logError("Summary field does not have text format");var _14=[],_15={},_16={};var _17=(_10&&_10!="");for(var _18 in _4.userSummary.summaryVars){var _19=_4.userSummary.summaryVars[_18],_20=this.getField(_19);if(!_20)_14.add(_19);else{_15[_18]=_20;var _21=this.getRecordHiliteCSSText(_3,null,_20);if(_21){_16[_18]=_21;_17=true}}}
if(!_17)return;if(_14.length!=0&&_10){if(_7){_1[_2]={style:this.convertCSSToProperties(_10,_6),value:_11}}else{_1[_2]=this.convertCSSToProperties(_10,_6)}
return}
var _22=null,_23=null,_24=[];var _25=this;var _26=function(_37,_38){if(_37){_37=_25.htmlUnescapeExportFieldValue(_37);if(_22&&_23==_38){_22.value+=_37}else{if(_22)_24.push(_22);_22={value:_37};_23=_38;if(_38)_22.style=_25.convertCSSToProperties(_38,_6)}}};var _27=_4.userSummary.text.split("#"),_28=/^\{([A-Z]+)\}/;if(_27[0])_26(_27[0],_10);for(var i=1;i<_27.length;i++){var _30=_27[i],_31,_32,_33,_34,_35,_36;_33=_30.charAt(0);_32=_15[_33];if(_32)_36=_30.substr(1);else if(_31=_30.match(_28)){_36=_30.substr(_31[0].length);_33=_31[1];_32=_15[_33];if(!_32)_36=this.missingSummaryFieldValue+_36}else _36="#"+_30;if(_32){_34=this.getExportFieldValue(_3,_32.name,this.getFieldNum(_32.name));_35=null;if(_10)_35=(_35||"")+_10;if(_16[_33])_35=(_35||"")+_16[_33]}
_26(_34,_35);_26(_36,_10)}
if(_22)_24.push(_22);_1[_2]=_24}
,isc.A.getClientExportData=function isc_Canvas_getClientExportData(_1,_2){var _3=this.originalData||this.data,_4=[],_5=this.getClientExportFields(_1),_6,_7,_8,_9,_10=_1&&_1.exportFields;if(_1==null)_1={};if(_1.exportData!=null)_3=_1.exportData;_6=_1.includeHiddenFields;_7=_1.allowedProperties;_8=_1.includeCollapsedNodes;_9=_1.alwaysExportExpandedStyles;if(_10){if(_6!==false)_6=true}
if(isc.isA.ResultSet(_3))_3=_3.getAllLoadedRows();if(isc.isA.Tree(_3)){if(_8)_3=_3.getAllNodes();else _3=_3.getOpenList()}
var _11={settings:_1,callback:_2,chunkSize:this.exportDataChunkSize,data:_3,exportData:_4,fields:_5,includeHiddenFields:_6,allowedProperties:_7,includeCollapsedNodes:_8,alwaysExportExpandedStyles:_9,totalRows:_3.getLength(),startRow:0,endRow:Math.min(this.exportDataChunkSize,_3.getLength()),exportFieldsSpecified:_10};_11.firstTimeStamp=_11.thisTimeStamp=isc.timeStamp();this.logInfo("starting export chunking process - "+_11.firstTimeStamp,"export");this.getClientExportDataChunk(_11);return}
,isc.A.getClientExportDataChunk=function isc_Canvas_getClientExportDataChunk(_1){var _2=_1.settings,_3=_1.data,_4=_1.exportData,_5=_1.fields,_6=_1.includeHiddenFields,_7=_1.allowedProperties,_8=_1.includeCollapsedNodes,_9=_1.alwaysExportExpandedStyles,_10=_1.totalRows,_11=_1.startRow,_12=_1.endRow,_13=_1.settings.exportValueFields,_14=_1.exportFieldsSpecified,_15=_1.settings.exportDatesAsFormattedString;for(var _16=_11;_16<_12;_16++){var _17=_3[_16],_18=this.getRecordExportObject(_17,_5,_7,_6,_8,_9,_13,_14,_15);_4.push(_18)}
if(_1.endRow<_1.totalRows){_1.lastTimeStamp=_1.thisTimeStamp;_1.thisTimeStamp=isc.timeStamp();if(this.logIsInfoEnabled("export")){this.logInfo("processed "+_1.endRow+" rows - starting next chunk - "+((_1.thisTimeStamp-_1.lastTimeStamp)/1000),"export")}
_1.startRow=_1.endRow;_1.endRow=Math.min(_1.startRow+_1.chunkSize,_1.totalRows);return this.delayCall("getClientExportDataChunk",[_1],0)}
if(this.showGridSummary&&this.summaryRow&&this.exportIncludeSummaries){var _19=this.summaryRow,_3=this.getGridSummaryData(true);for(var _16=0;_16<_3.getLength();_16++){var _17=_3[_16],_18=this.getRecordExportObject(_17,_5,_7,_6,_8,_9,_15);_4.push(_18)}}
if(_1.callback){var _3=_1.exportData;if(this.logIsInfoEnabled("export")){this.logInfo("finished processing "+_1.endRow+" rows - about to export - "+isc.timestamp(),"export")}
this.fireCallback(_1.callback,"data,context",[_3,_1.settings])}}
,isc.A.getClientExportFields=function isc_Canvas_getClientExportFields(_1){var _2=this.getAllFields();if(isc.isA.Object(_1)){if(_1&&_1.exportFields){var _3=[];for(var i=0;i<_2.length;i++){if(_1.exportFields.contains(_2[i].name))_3.add(_2[i])}
_2=_3}}
return _2}
,isc.A.getRecordExportObject=function isc_Canvas_getRecordExportObject(_1,_2,_3,_4,_5,_6,_7,_8,_9){var _10={};for(var _11=0;_11<_2.length;_11++){var _12=_2[_11];if((!this.fields.contains(_12))&&!_4)continue;var _13=this.getFieldNum(_12.name),_14=this.htmlUnescapeExportFieldTitle(_12.exportTitle||_12.title||_12.name),_15=_14+"$style",_16;if(_12.exportRawValues||(this.exportRawValues&&_12.exportRawValues!=false))
_16=_1[_12[this.fieldIdProperty]];else
_16=this.getExportFieldValue(_1,_12.name,_13);if(_16==null||_16=="&nbsp;")_16="";if(!_8){if(_7){if(_12.displayField){var _17=_12.name;if(_17==_14)_17+="_value";_10[_17]=_1[_12.name]}}}
_10[_14]=_16;this.addDetailedExportFieldValue(_10,_15,_1,_12,_13,_3,_6,_9)}
return _10}
,isc.A.htmlUnescapeExportFieldTitle=function isc_Canvas_htmlUnescapeExportFieldTitle(_1){return this.htmlUnescapeExportFieldValue(_1)}
,isc.A.htmlUnescapeExportFieldValue=function isc_Canvas_htmlUnescapeExportFieldValue(_1){if(isc.isA.String(_1))return _1.unescapeHTML().replace(/<.*?>/g,isc.emptyString);return _1}
,isc.A.addHiliteSpan=function isc_Canvas_addHiliteSpan(_1,_2,_3){var _4=this.getRecordHiliteCSSText(_1,null,_2);if(_4)return"<span style=\""+_4+"\">"+_3+"</span>";else return _3}
,isc.A.getRawValue=function isc_Canvas_getRawValue(_1,_2){return this.getCellValue(_1,this.getField(_2))}
,isc.A.getFormattedValue=function isc_Canvas_getFormattedValue(_1,_2,_3){return _3}
,isc.A.fieldIsVisible=function isc_Canvas_fieldIsVisible(_1){return true}
,isc.A.getSpecifiedField=function isc_Canvas_getSpecifiedField(_1){return this.getField(_1)}
,isc.A.getStandaloneFieldValue=function isc_Canvas_getStandaloneFieldValue(_1,_2,_3){var _4=this.getSpecifiedField(_2),_5;if(!_4)return;if(_4.userFormula)_5=this.getFormulaFieldValue(_4,_1);else if(_4.userSummary)_5=this.getSummaryFieldValue(_4,_1);else{if(this.$425&&this.$425(_4)){_5=_1[_4.displayField]}else{_5=this.getRawValue(_1,_2)}
if(!_3)_5=this.getFormattedValue(_1,_2,_5)}
var _6=this.addHiliteSpan(_1,_4,_5);return _6}
,isc.A.getDateFormattingProperties=function isc_Canvas_getDateFormattingProperties(_1,_2,_3){if(!isc.SimpleType.inheritsFrom(_1.type,"date"))return;if(!isc.isA.Date(_2))return;var _4=isc.SimpleType.inheritsFrom(_1.type,"datetime");var _5;if(_1.dateFormatter&&isc.isA.Function(Date.prototype[_1.dateFormatter])){_5=_1.dateFormatter}else if(_1.displayFormat&&isc.isA.Function(Date.prototype[_1.displayFormat])){_5=_1.displayFormat}
if(!_5){var _6=this.getDataSource().getField(_1.name),_7=_6?_6.dateFormatter||_6.displayFormat:null;if(_7&&isc.isA.Function(Date.prototype[_7])){_5=_7}}
if(!_5){var _8;if(this.datetimeFormatter!=null&&_4){_8=this.datetimeFormatter}else{_8=this.dateFormatter}
if(_8&&isc.isA.Function(Date.prototype[_8])){_5=_8}}
if(!_5){var _9=!_4?Date.prototype.$el:Date.prototype.$68e;if(_9&&isc.isA.Function(Date.prototype[_9])){_5=_9}}
var _10={rawValue:_2,dateFormatter:_5};return _10}
,isc.A.exportClientData=function isc_Canvas_exportClientData(_1,_2){if(_2)_1.$90b=_2;this.getClientExportData(_1,this.getID()+".exportClientDataReply(data,context)");return}
,isc.A.exportClientDataReply=function isc_Canvas_exportClientDataReply(_1,_2){if(_1==null||_1.length==0){isc.warn(this.emptyExportMessage);return}
var _3=_2||{},_4=_3.exportAs?_3.exportAs:"csv",_5=_3.exportFilename?_3.exportFilename:"export",_6=_3.exportDisplay?_3.exportDisplay:"download";var _7={showPrompt:false,transport:_3.exportToClient===false?"xmlHttpRequest":"hiddenFrame",exportResults:true,downloadResult:!(_3.exportToClient===false),downloadToNewWindow:(_6=="window"),download_filename:(_6=="window"?_5:null)};var _8={exportAs:_3.exportAs,exportToClient:_3.exportToClient,exportToFilesystem:_3.exportToFilesystem,exportPath:_3.exportPath,exportFilename:_5,exportDelimiter:_3.exportDelimiter,exportHeader:_3.exportHeader,exportFooter:_3.exportFooter,exportTitleSeparatorChar:_3.exportTitleSeparatorChar,lineBreakStyle:_3.lineBreakStyle,exportDatesAsFormattedString:_3.exportDatesAsFormattedString};if(_3.exportFields){var _9=this.getAllFields(),_10=[],_11=[];for(var i=0;i<_3.exportFields.length;i++){var _13=_3.exportFields[i],_14=_9.find("name",_13),_15=_14?(_14.exportTitle?_14.exportTitle:_14.title):null;if(_15){var _16=this.htmlUnescapeExportFieldTitle(_15);_16=_16.replace("\n"," ");if(_15!=_16&&_1&&_1.length){for(var j=0;j<_1.length;j++){_1[j][_16]=_1[j][_15];delete _1[j][_15]}}
_10.add(_16)}else{_11.add(_13)}}
if(_11.length>0){this.logWarn("exportFields was specified but contains the following field-names "+"that are not available in this component: "+_11.join(",")+".")}
if(_10.length>0)_8.exportFields=_10}
isc.DMI.callBuiltin({methodName:"downloadClientExport",arguments:[_1,_4,_5,_6,_8],requestParams:_7,callback:_2.$90b});if(_2.$90b&&_7.downloadResult)this.fireCallback(_2.$90b)}
,isc.A.getSort=function isc_Canvas_getSort(){return this.$73p?isc.shallowClone(this.$73p):null}
,isc.A.setSort=function isc_Canvas_setSort(_1){this.$73p=isc.shallowClone(_1);if(this.data&&this.$73p&&this.$73p.length>0){if(this.data.setSort)this.data.setSort(this.$73p);else if(this.data.sortByProperty){var _2=this.$73p[0];this.data.sortByProperty(_2.property,Array.shouldSortAscending(_2.direction),_2.normalizer,_2.context)}}}
,isc.A.askForSort=function isc_Canvas_askForSort(){if(isc.MultiSortDialog&&this.canMultiSort!=false){isc.MultiSortDialog.askForSort(this,this.getSort(),this.getID()+".multiSortReply(sortLevels)")}}
,isc.A.multiSortReply=function isc_Canvas_multiSortReply(_1){if(_1!=null){this.setSort(_1)}}
,isc.A.addValidationError=function isc_Canvas_addValidationError(_1,_2,_3){var _4=false;if(isc.isAn.Array(_3)){for(var i=0;i<_3.length;i++){_4=this.addValidationError(_1,_2,_3[i])||_4}
return _4}
var _6=_2.contains(this.$70l);if(_6){var _7=_1,_8=_2.trim(this.$70l).split();for(var i=0;i<_8.length;i++){if(!_7[_8[i]]){if(i<_8.length-1){if(parseInt(_8[i+1])==_8[i+1]){_7[_8[i]]=[]}else{_7[_8[i]]={}}}else{_7[_8[i]]=_3;_4=true}}
_7=_7[_8[i]]}}else{if(!_1[_2]){_1[_2]=_3;_4=true}else{if(!isc.isAn.Array(_1[_2]))_1[_2]=[_1[_2]];if(!_1[_2].contains(_3)){_1[_2].add(_3);_4=true}}}
return _4}
,isc.A.isFieldDependentOnOtherField=function isc_Canvas_isFieldDependentOnOtherField(_1,_2){if(!_1.validators)return false;var _3=this.getDataSource();for(var i=0;i<_1.validators.length;i++){var _5=_1.validators[i];if(!_5)continue;if(!_5.$74r&&_5.applyWhen&&_3!=null){_5.$74r=_3.getCriteriaFields(_5.applyWhen)}
if(_5.dependentFields&&_5.dependentFields.contains(_2)){return true}
if(_5.$74r&&_5.$74r.length>0&&_5.$74r.contains(_2))
{return true}}
return false}
,isc.A.getFieldDependencies=function isc_Canvas_getFieldDependencies(_1){if(!_1.validators)return null;var _2=this.getDataSource(),_3=[];for(var i=0;i<_1.validators.length;i++){var _5=_1.validators[i];if(!_5)continue;if(!_5.$74r&&_5.applyWhen&&_2!=null){_5.$74r=_2.getCriteriaFields(_5.applyWhen)}
if(_5.dependentFields){if(!isc.isAn.Array(_5.dependentFields)){_5.dependentFields=[_5.dependentFields]}
for(var i=0;i<_5.dependentFields.length;i++){_3.add(_5.dependentFields[i])}}
if(_5.$74r&&_5.$74r.length>0)
{_3.addList(_5.$74r)}}
return(_3.length==0?null:_3)}
,isc.A.validateFieldAndDependencies=function isc_Canvas_validateFieldAndDependencies(_1,_2,_3,_4,_5){var _6={},_7=false,_8={valid:true,errors:null,resultingValue:null};_4[_1.name]=_3;var _9=this.validateField(_1,_1.validators,_3,_4,_5);if(_9!=null){_8.valid=_9.valid;_8.stopOnError=_9.stopOnError;if(_9.errors!=null){this.addValidationError(_6,_1.name||_1.dataPath,_9.errors)}
if(_9.resultingValue!=null){_8.resultingValue=_9.resultingValue;_4[_1.name]=_9.resultingValue}
_7=true}
var _10=_1.name||_1.dataPath,_11=this.getFields()||[];for(var i=0;i<_11.length;i++){var _13=_11[i];if(_13.name!=_10&&_13.dataPath!=_10&&this.isFieldDependentOnOtherField(_13,_10))
{_9=this.validateField(_13,_13.validators,_4[_13.name],_4,_5);if(_9!=null){if(_9.errors!=null){this.addValidationError(_6,_13.name||_13.dataPath,_9.errors)}else{this.addValidationError(_6,_13.name||_13.dataPath,null)}
if(_9.resultingValue!=null){_4[_13.name]=_9.resultingValue}}}}
_8.errors=_6;return(_7?_8:null)}
,isc.A.validateField=function isc_Canvas_validateField(_1,_2,_3,_4,_5){if(!_2)return null;var _6=[],_7=false,_8=null,_9={valid:true,errors:null,resultingValue:null},_10=false,_11=false;if(!isc.isAn.Array(_2)){_2=[_2]}
for(var i=0;i<_2.length;i++){var _13=_2[i];if(!_13)continue;var _14=isc.Validator.getValidatorType(_13);if(_5&&_5.typeValidationsOnly&&!this.$685.contains(_14))
{continue}
if(_5&&_5.dontValidateNullValue&&_3==null&&_14!="required"&&_14!='requiredIf')
{continue}
if(!_5||!_5.changing||(_13.validateOnChange!=false&&(_13.validateOnChange||_1.validateOnChange||this.validateOnChange)))
{if(isc.Validator.isServerValidator(_13)){_10=true;if(_13.stopOnError)_11=true;continue}
if(_13.applyWhen){var _15=this.getDataSource(),_16=_13.applyWhen;if(_15==null){isc.logWarn("Conditional validator criteria ignored because form has no dataSource")}else{var _17=_15.applyFilter([_4],_16);if(_17.length==0){isc.Validator.performAction(null,_1,_13,_4,this);continue}}}
_7=true;var _18=(isc.Validator.processValidator(_1,_13,_3,null,_4)==true);isc.Validator.performAction(_18,_1,_13,_4,this);if(!_18){var _19=isc.Validator.getErrorMessage(_13);if(_19==null){if(_5&&_5.unknownErrorMessage){_19=_5.unknownErrorMessage}else{_19=this.unknownErrorMessage}}
_6.add(_19);if(_13.stopOnError)_8=true}
if(_13.resultingValue!=null){_9.resultingValue=_13.resultingValue;_3=_13.resultingValue}
if(!_18&&_13.stopIfFalse)break}}
if(_10&&(!_5||_5.skipServerValidation!=true)){_11=this.$75e(_11,_1.stopOnError,this.stopOnError);var _20=((_5&&_5.serverValidationMode)?_5.serverValidationMode:this.$746),_21=isc.addProperties({},_4),_22=(_11||_1.synchronousValidation||this.synchronousValidation||false);_21[_1.name]=_3;this.fireServerValidation(_1,_21,_20,_22,_5.rowNum)}
_9.stopOnError=(_6.length>0&&this.$75e(_8,_1.stopOnError,this.stopOnError));_9.errors=(_6.length==0?null:_6);_9.valid=(_6.length==0);return(_7?_9:null)}
,isc.A.$75e=function isc_Canvas__resolveStopOnError(_1,_2,_3){if(_1!=null)return _1;return(_2==null&&_3)||_2||false}
,isc.A.fireServerValidation=function isc_Canvas_fireServerValidation(_1,_2,_3,_4,_5){var _6=this.getDataSource();if(_6==null)return;var _7={showPrompt:_4,prompt:isc.RPCManager.validateDataPrompt,validationMode:_3,clientContext:{component:this,fieldName:_1.name,rowNum:_5}};if(_3==this.$746){for(var _8 in _2){if(_2[_8]===null)delete _2[_8]}}
if(!_4){var _9=this.$75c(_1);_7.clientContext.pendingFields=_9}
_6.validateData(_2,this.$742,_7)}
,isc.A.$742=function isc_Canvas__handleServerValidationReply(_1,_2,_3){if(_1.status==isc.DSResponse.STATUS_FAILURE){isc.logWarn("Server-side validation failed: "+_1.data);isc.say(_1.data)}
var _4=_1.clientContext,_5=_4.component,_6=_4.pendingFields,_7=_1.errors==null?null:isc.DynamicForm.getSimpleErrors(_1.errors);if(_1.errors){for(var _8 in _7){var _9=_7[_8],_10=_5.getField(_8);if(_9!=null&&_10!=null){if(!isc.isAn.Array(_9))_9=[_9];var _11=null;for(var i=0;i<_9.length;i++){_5.addFieldErrors(_8,_9[i].errorMessage,false,_4.rowNum);if(_9[i].stopOnError)_11=true}
if(_10.redraw)_10.redraw();_11=_5.$75e(_11,_10.stopOnError,_5.stopOnError);if(_8==_4.fieldName&&_11==true&&!_10.hasFocus){if(!_10.synchronousValidation&&!_5.synchronousValidation){isc.logWarn("Server validation for "+_8+" signaled stopOnError but validation is not set for"+" synchronousValidation:true - stop ignored.")}else{_5.focusInItem(_10)}}}}}
if(_6){_5.$75d(_6)}
if(_5&&_5.handleAsyncValidationReply!=null){if(_7!=null){_7=isc.DynamicForm.formatValidationErrors(_7)}
_5.handleAsyncValidationReply(_7==null,_7)}}
,isc.A.handleAsyncValidationReply=function isc_Canvas_handleAsyncValidationReply(_1,_2){}
);isc.evalBoundary;isc.B.push(isc.A.isPendingAsyncValidation=function isc_Canvas_isPendingAsyncValidation(){return!isc.isAn.emptyObject(this.$75a)}
,isc.A.$75c=function isc_Canvas__registerAsyncValidation(_1){var _2=this.getFields()||[],_3=[_1.name],_4=_1.name;this.$75a[_4]=(this.$75a[_4]==null?1:this.$75a[_4]++);for(var i=0;i<_2.length;i++){var _6=_2[i];if(_6.name!=_4&&this.isFieldDependentOnOtherField(_6,_4)){var _7=_6.name;_3.add(_7);this.$75a[_7]=(this.$75a[_7]==null?1:this.$75a[_7]++)}}
return _3}
,isc.A.$75d=function isc_Canvas__clearAsyncValidation(_1){var _2=false;for(var i=0;i<_1.length;i++){this.$75a[_1[i]]--;if(this.$75a[_1[i]]==0){delete this.$75a[_1[i]];_2=true}}
if(_2&&this.$75b!=null){var _4=true;for(var i=0;i<this.$75b;i++){if(this.$75a[this.$75b[i]]>0){_4=false;break}}
if(_4){this.$75b=null;isc.clearPrompt()}}}
,isc.A.blockOnFieldBusy=function isc_Canvas_blockOnFieldBusy(_1){if(this.$75b!=null)return true;var _2=false;for(var _3 in this.$75a){_2=true;break}
if(!_2)return false;var _4=this.getFieldDependencies(_1)||[];_4.add(_1.name);var _5=[];for(var i=0;i<_4.length;i++){var _7=_4[i];if(this.$75a[_7]>0){_5.add(_7)}}
if(_5.length>0){this.$75b=_5;this.delayCall("showValidationBlockingPrompt");return true}
return false}
,isc.A.showValidationBlockingPrompt=function isc_Canvas_showValidationBlockingPrompt(){if(this.$75b)isc.showPrompt(isc.RPCManager.validateDataPrompt)}
,isc.A.enableField=function isc_Canvas_enableField(_1){if(_1==null||isc.isAn.emptyString(_1))return;var _2=this.getField(_1);if(_2){_2.disabled=false;this.redraw()}}
,isc.A.disableField=function isc_Canvas_disableField(_1){if(_1==null||isc.isAn.emptyString(_1))return;var _2=this.getField(_1);if(_2){_2.disabled=true;this.redraw()}}
,isc.A.showField=function isc_Canvas_showField(_1){if(_1==null||isc.isAn.emptyString(_1))return;var _2=this.getField(_1);if(_2)_2.show()}
,isc.A.hideField=function isc_Canvas_hideField(_1){if(_1==null||isc.isAn.emptyString(_1))return;var _2=this.getField(_1);if(_2)_2.hide()}
,isc.A.setFieldCanEdit=function isc_Canvas_setFieldCanEdit(_1,_2){if(_1==null||isc.isAn.emptyString(_1))return;var _3=this.getField(_1);if(_3){_3.canEdit=_2;this.redraw()}}
,isc.A.isOffline=function isc_Canvas_isOffline(){if(this.data&&this.data.$78c)return true;return false}
,isc.A.setSelectionComponent=function isc_Canvas_setSelectionComponent(_1,_2){if(!_1){if(this.selectionComponent!=null){this.ignore(this.selectionComponent,"selectionChanged");this.ignore(this.selectionComponent,"cellSelectionChanged")}
delete this.selectionComponent;if(this.valuesManager){this.ignore(this.valuesManager,"$71e")}}else{var _3=_1;if(isc.isA.String(_1))_1=window[_1];if(!_1||!isc.isA.Canvas(_1)||_1.dataArity!="multiple"){this.logWarn("setSelectionComponent() - selection component specified as:"+_3+" this is not a valid component");return}
if(!_1.getSelection){this.logWarn("setSelectionComponent() - specified selection component:"+_1+" does not support selection - ignoring");return}
if(!_2&&this.selectionComponent){if(this.selectionComponent==_1)return
if(this.isObserving(this.selectionComponent,"selectionChanged")){this.ignore(this.selectionComponent,"selectionChanged")}
if(this.isObserving(this.selectionComponent,"cellSelectionChanged")){this.ignore(this.selectionComponent,"cellSelectionChanged")}}
this.selectionComponent=_1;if(!this.selectionComponent.useCellRecords){this.observe(this.selectionComponent,"selectionChanged","observer.selectionComponentSelectionChanged(observed, record,state)")}else{this.observe(this.selectionComponent,"cellSelectionChanged","observer.selectionComponentCellSelectionChanged(observed, cellList)")}
var _4=this.selectionComponent.getSelection}}
,isc.A.selectionComponentSelectionChanged=function isc_Canvas_selectionComponentSelectionChanged(_1,_2,_3){if(!_3){if(this.dataArity=="single"){_2=null}else{return}}
if(this.dataArity=="single"){this.setData(_2)}else{var _4=this.dataPath.split("/");this.setData(_2[_4[_4.length-1]]);if(this.dataArity=="multiple"&&isc.isA.Function(this.deselectAllRecords)){this.deselectAllRecords()}}}
,isc.A.selectionComponentCellSelectionChanged=function isc_Canvas_selectionComponentCellSelectionChanged(_1,_2){for(var i=0;i<_2.length;i++){var _4=_2[i],_5=this.selectionComponent.getCellRecord(_4[0],_4[1]);if(_1.cellIsSelected(_5))break;_5=null}
if(_5){this.$71d=_1.getPrimaryKeys(_5);this.editRecord(_5)}}
);isc.B._maxIndex=isc.C+185;isc.ClassFactory.defineClass("MathFunction","Class");isc.A=isc.MathFunction;isc.A.$65z={};isc.A=isc.MathFunction;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.registerFunction=function isc_c_MathFunction_registerFunction(_1){if(!this.$65z[_1.name]){this.$65z[_1.name]=_1}}
,isc.A.getRegisteredFunctionNames=function isc_c_MathFunction_getRegisteredFunctionNames(){return isc.getKeys(this.$65z)}
,isc.A.getDefaultFunctionNames=function isc_c_MathFunction_getDefaultFunctionNames(){var _1=this.getDefaultFunctions(),_2=_1.makeIndex("name",false);return isc.getKeys(_2)}
,isc.A.getRegisteredFunctions=function isc_c_MathFunction_getRegisteredFunctions(){return isc.getValues(this.$65z)}
,isc.A.getDefaultFunctions=function isc_c_MathFunction_getDefaultFunctions(){var _1=this.getRegisteredFunctions(),_2=_1.findAll("defaultSortPosition",-1)||[];for(var i=0;i<_2.length;i++){var _4=_2[i];_1.remove(_4)}
_1.sortByProperties(["defaultSortPosition"],["true"]);return _1}
,isc.A.getRegisteredFunctionIndex=function isc_c_MathFunction_getRegisteredFunctionIndex(){var x=this.getRegisteredFunctions();var _2=x.makeIndex("name",false);return _2}
,isc.A.getDefaultFunctionIndex=function isc_c_MathFunction_getDefaultFunctionIndex(){return this.getDefaultFunctions().makeIndex("name",false)}
,isc.A.isRegistered=function isc_c_MathFunction_isRegistered(_1){if(this.$65z[_1])return true;return false}
);isc.B._maxIndex=isc.C+8;isc.A=isc.MathFunction.getPrototype();isc.A.defaultSortPosition=-1;isc.MathFunction.registerFunction(isc.MathFunction.create({name:"max",description:"Maximum of two values",usage:"max(value1, value2)",defaultSortPosition:1,jsFunction:function(_1,_2){return Math.max(_1,_2)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"min",description:"Minimum of two values",usage:"min(value1, value2)",defaultSortPosition:2,jsFunction:function(_1,_2){return Math.min(_1,_2)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"round",description:"Round a value up or down, optionally providing <i>decimalDigits</i> "+"as the maximum number of decimal places to round to.  For fixed or precision "+"rounding, use <i>toFixed()</i> and <i>toPrecision()</i> respectively.",usage:"round(value,decimalDigits)",defaultSortPosition:3,jsFunction:function(_1,_2){if(_2){var _3=Math.pow(10,_2),_4=Math.round(_1*_3)/_3;return _4}
return Math.round(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"ceil",description:"Round a value up",usage:"ceil(value)",defaultSortPosition:4,jsFunction:function(_1){return Math.ceil(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"floor",description:"Round a value down",usage:"floor(value)",defaultSortPosition:5,jsFunction:function(_1){return Math.floor(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"abs",description:"Absolute value",usage:"abs(value)",defaultSortPosition:6,jsFunction:function(_1){return Math.abs(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"pow",description:"Value1 to the power of Value2",usage:"pow(value1, value2)",defaultSortPosition:7,jsFunction:function(_1,_2){return Math.pow(_1,_2)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"sin",description:"Sine of a value",usage:"sin(value)",defaultSortPosition:8,jsFunction:function(_1){return Math.sin(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"cos",description:"Cosine of a value",usage:"cos(value)",defaultSortPosition:9,jsFunction:function(_1){return Math.cos(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"tan",description:"Tangent of a value",usage:"tan(value)",defaultSortPosition:10,jsFunction:function(_1){return Math.tan(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"ln",description:"Natural logarithm of a value",usage:"ln(value)",defaultSortPosition:11,jsFunction:function(_1){return Math.log(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"log",description:"logarithm of a value with the specified <i>base</i>",usage:"log(base, value)",defaultSortPosition:12,jsFunction:function(_1,_2){return Math.log(_2)/Math.log(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"asin",description:"Arcsine of a value",usage:"asin(value)",defaultSortPosition:13,jsFunction:function(_1){return Math.asin(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"acos",description:"Arccosine of a value",usage:"acos(value)",defaultSortPosition:14,jsFunction:function(_1){return Math.acos(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"atan",description:"Arctangent of a value (-PI/2 to PI/2 radians)",usage:"atan(value)",defaultSortPosition:15,jsFunction:function(_1){return Math.atan(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"atan2",description:"Angle theta of a point (-PI to PI radians)",usage:"atan2(value1,value2)",defaultSortPosition:16,jsFunction:function(_1,_2){return Math.atan2(_1,_2)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"exp",description:"The value of E<sup>value</sup>",usage:"exp(value)",defaultSortPosition:17,jsFunction:function(_1){return Math.exp(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"random",description:"Random number between 0 and 1",usage:"random()",defaultSortPosition:18,jsFunction:function(){return Math.random()}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"sqrt",description:"Square root of a value",usage:"sqrt(value)",defaultSortPosition:19,jsFunction:function(_1){return Math.sqrt(_1)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"toPrecision",description:"Format a number to a length of <i>precision</i> digits, rounding or "+"adding a decimal point and zero-padding as necessary.  Note that the values "+"123, 12.3 and 1.23 have an equal precision of 3.  Returns a formatted "+"string and should be used as the outermost function call in a formula. "+"For rounding, use <i>round()</i>.",usage:"toPrecision(value,precision)",defaultSortPosition:20,jsFunction:function(_1,_2){var _3=_1;if(isc.isA.String(_3))_3=parseFloat(_3);if(isNaN(_3))return _1;return _3.toPrecision(_2)}}));isc.MathFunction.registerFunction(isc.MathFunction.create({name:"toFixed",description:"Round or zero-pad a number to <i>digits</i> decimal places.  Returns "+"a formatted string and should be used as the outermost function call in a "+"formula.  To round values or restrict precision, use <i>round()</i> and "+"<i>toPrecision()</i> respectively.",usage:"toFixed(value,digits)",defaultSortPosition:21,jsFunction:function(_1,_2){var _3=_1;if(isc.isA.String(_3))_3=parseFloat(_3);if(isNaN(_3))return _1;return _3.toFixed(_2)}}));isc.Canvas.registerStringMethods({userAddedField:"field",selectionUpdated:"record,recordList",onFetchData:"criteria,requestProperties"});isc.defineClass("EdgedCanvas","Canvas");isc.A=isc.EdgedCanvas.getPrototype();isc.A.redrawOnResize=false;isc.A._redrawWithParent=false;isc.A.$jp=false;isc.A.$jo=false;isc.A.useClipDiv=false;isc.A.overflow=isc.Browser.isMoz?isc.Canvas.VISIBLE:isc.Canvas.HIDDEN;isc.A.$ks=false;isc.A.$tb=0;isc.A.$td=0;isc.A.$tc=0;isc.A.$te=0;isc.A.$y0=["TL","T","TR","L","center","R","BL","B","BR"];isc.A.skinImgDir="images/edges/";isc.A.edgeImage="[SKIN]/rounded/frame/FFFFFF/6.png";isc.A.shownEdges={TL:true,T:true,TR:true,L:true,R:true,BL:true,B:true,BR:true};isc.A.edgeSize=6;isc.A=isc.EdgedCanvas.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.$61h="<TD class='";isc.A.$61i="' ></TD>";isc.A.$y2="<TABLE CELLPADDING=0 CELLSPACING=0 "+"STYLE='height:100%;width:100%;table-layout:fixed'>"+"<COL WIDTH=";isc.A.$y3="><COL><COL WIDTH=";isc.A.$4n=" class=";isc.A.addEdgeStyleSuffix=false;isc.A.forceMozRowHeight=true;isc.B.push(isc.A.initWidget=function isc_EdgedCanvas_initWidget(){this.invokeSuper(isc.EdgedCanvas,this.$oc);var _1=this.customEdges;if(_1){var _2=this.shownEdges={};if(_1.contains("T")){_2.T=_2.TL=_2.TR=true}
if(_1.contains("B")){_2.B=_2.BL=_2.BR=true}
if(_1.contains("L")){_2.L=_2.TL=_2.BL=true}
if(_1.contains("R")){_2.R=_2.TR=_2.BR=true}}
this.updateEdgeSizes()}
,isc.A.updateEdgeSizes=function isc_EdgedCanvas_updateEdgeSizes(){var _1=this.edgeSize;this.$y4=this.$du(this.edgeLeft,_1);this.$y5=this.$du(this.edgeRight,_1);this.$y6=this.$du(this.edgeTop,_1);this.$y7=this.$du(this.edgeBottom,_1);var _2=this.shownEdges,_3=this.edgeOffset;if(_2.L)this.$tb=this.$du(this.edgeOffsetLeft,_3,this.$y4);if(_2.R)this.$tc=this.$du(this.edgeOffsetRight,_3,this.$y5);if(_2.T)this.$td=this.$du(this.edgeOffsetTop,_3,this.$y6);if(_2.B)this.$te=this.$du(this.edgeOffsetBottom,_3,this.$y7);this.markForRedraw()}
,isc.A.getInnerWidth=function isc_EdgedCanvas_getInnerWidth(_1,_2,_3){var _4=this.invokeSuper(isc.EdgedCanvas,"getInnerWidth",_1,_2,_3);return _4-this.$tb-this.$tc}
,isc.A.getInnerHeight=function isc_EdgedCanvas_getInnerHeight(_1,_2,_3){var _4=this.invokeSuper(isc.EdgedCanvas,"getInnerHeight",_1,_2,_3);return _4-this.$td-this.$te}
,isc.A.getInnerHTML=function isc_EdgedCanvas_getInnerHTML(){var _1=isc.SB.create(),_2=this.edgeImage,_3=_2.lastIndexOf(isc.dot),_4=_2.substring(0,_3),_5=_2.substring(_3),_6=this.getImgURL(_4),_7="<TD HEIGHT=",_8="<TD",_9,_10;if(!((isc.Browser.isStrict&&isc.Browser.isIE&&isc.Browser.version>=8)||(isc.Browser.isMoz&&isc.Browser.isUnix)))
{var _11=isc.EdgedCanvas.$y8;if(!_11){_11=isc.EdgedCanvas.$y8={width:"100%",height:"100%"};if(isc.Browser.isSafari)_11.align="middle";if(isc.Browser.isStrict&&!isc.Browser.isTransitional)
_11.extraStuff="style='display:block;'"}
_11.src=_2;var _12=this.imgHTML(_11);if(isc.Browser.isSafari){_12="<DIV style='overflow:hidden;width:100%;height:100%'>"+_12+"</DIV>"}
var _13=_12.lastIndexOf(isc.dot);_9=this.$oa+_12.substring(0,_13);_10=_12.substring(_13)+"</TD>"}else{_9=" STYLE='background:url("+_6;_10=_5+")'></TD>"}
if(this.edgeColor)_9+=isc.$ag+this.edgeColor;var _14=this.shownEdges;_1.append(this.$y2,this.$y4,this.$y3,this.$y5,this.$oa,"<TR HEIGHT=",this.$y6,this.$oa);this.$y9(_7,this.$y6,_9,_10,0,2,_14,_1);var _15=this.getHeight()-this.$y6-this.$y7;if(isc.Browser.isIE&&isc.Browser.isStrict){_1.append("</TR><TR HEIGHT=",_15,">")}else{_1.append("</TR><TR>")}
if(isc.Browser.isMoz){this.$y9(_7,"100%",_9,_10,3,5,_14,_1)}else if(isc.Browser.isWebKit){this.$y9(_7,_15,_9,_10,3,5,_14,_1)}else{this.$y9(_8,null,_9,_10,3,5,_14,_1)}
_1.append("</TR><TR HEIGHT=",this.$y7,">");this.$y9(_7,this.$y7,_9,_10,6,8,_14,_1);_1.append("</TR></TABLE>");return _1.toString()}
,isc.A.$y9=function isc_EdgedCanvas__writeEdgeCells(_1,_2,_3,_4,_5,_6,_7,_8){for(var i=_5;i<=_6;i++){var _10=this.$y0[i];var _11=this.getEdgeStyleName(_10),_12=_11?this.$4n:null;if(_7[_10]||(this.showCenter&&_10==isc.Canvas.CENTER)){_8.append(_1,_2,_12,_11,_3,this.getEdgePrefix(_10),isc.$ag,_10,_4)}else{if(this.centerBackgroundColor&&_10==isc.Canvas.CENTER){_8.append("<TD ",_12,_11," style='background-color:",this.centerBackgroundColor,"'></TD>")}else{_8.append(this.$61h,_12,_11,this.$61i)}}}}
,isc.A.getEdgeStyleName=function isc_EdgedCanvas_getEdgeStyleName(_1){if(this.edgeStyleName==null)return;if(!this.addEdgeStyleSuffix)return this.edgeStyleName;if(!this.$61j||this.$61j.base!=this.edgeStyleName){var _2=this.edgeStyleName;this.$61j={base:_2,TL:_2+"$61o",T:_2+"$61p",TR:_2+"$61q",L:_2+"$61r",C:_2+"$61s",R:_2+"$61t",BL:_2+"$61u",B:_2+"$61v",BR:_2+"$61w"}}
return this.$61j[_1]}
,isc.A.getEdgePrefix=function isc_EdgedCanvas_getEdgePrefix(_1){}
,isc.A.$ub=function isc_EdgedCanvas__handleResized(){if(!this.isDrawn()||this.$za)return;if(isc.Browser.isOpera){this.masterElement.bringToFront();return}
if(isc.Browser.isIE&&isc.Browser.isStrict){var _1=this.getHandle().firstChild.rows[1];this.$u9(_1.style,this.$o5,this.getHeight()-this.$y6-this.$y7);return}
if(isc.Browser.isWebKit){var _1=this.getHandle().firstChild.rows[1];var _2=Math.max(0,this.getHeight()-this.$y6-this.$y7);for(var i=0;i<_1.cells.length;i++){this.$u9(_1.cells[i].style,this.$o5,_2)}
return}
if(!isc.Browser.isMoz)return;var _4=this.getHandle().firstChild.rows[1].cells[1],_5=this.getHeight()-this.$y6-this.$y7;if(_5<0)_5=0;this.$u9(_4.style,this.$o5,_5);if(this.forceMozRowHeight){var _6=_4.parentNode.cells;this.$u9(_6[0].style,this.$o5,_5);this.$u9(_6[2].style,this.$o5,_5)}}
,isc.A.layoutChildren=function isc_EdgedCanvas_layoutChildren(_1,_2,_3){var _4=this.children;if(!_4)return;isc.Canvas.$b4.layoutChildren.call(this,_1,_2,_3);if(_4.length==0)return;var _5=_4[0];_5.setRect(this.$tb,this.$td,this.getInnerWidth(),this.getInnerHeight())}
,isc.A.addChild=function isc_EdgedCanvas_addChild(_1,_2,_3){isc.Canvas.$b4.addChild.call(this,_1,_2,_3);this.layoutChildren("addChild")}
,isc.A.draw=function isc_EdgedCanvas_draw(_1,_2,_3,_4){if(!this.readyToDraw())return this;if(this.masterElement){var _5=this.masterElement,_6=false;while(_5){if(_5.position==this.$411){_6=true;break}
_5=_5.parentElement}
if(!_6||isc.Page.isLoaded())this.fitToMaster();else isc.Page.setEvent("load",this,isc.Page.FIRE_ONCE,"fitToMaster")}
this.invokeSuper(isc.EdgedCanvas,"draw",_1,_2,_3,_4);this.$ub();return this}
,isc.A.fitToMaster=function isc_EdgedCanvas_fitToMaster(){if(this.destroyed)return;var _1=this.masterElement;if(_1.$uu){isc.Timer.setTimeout({target:this,methodName:"fitToMaster"},200);return}else if(_1.$rm){_1.adjustOverflow()}
var _2=_1.$ta();this.setRect(_1.getOffsetLeft()+_2.left,_1.getOffsetTop()+_2.top,Math.max(1,(_1.getVisibleWidth()-_2.left-_2.right)),Math.max(1,(_1.getVisibleHeight()-_2.top-_2.bottom)))}
,isc.A.redraw=function isc_EdgedCanvas_redraw(){this.Super("redraw",arguments);this.$ub();return this}
,isc.A.masterResized=function isc_EdgedCanvas_masterResized(){var _1=this.masterElement;if(this.masterElement.isAnimating([this.$do,this.$zb]))return;var _2=isc.Browser.isSafari;if(_2){this.delayCall("$663",[_1])}else{this.$663(_1)}}
,isc.A.$663=function isc_EdgedCanvas__sizeToMaster(_1){if(this.destroyed||_1!=this.masterElement)return;var _2=_1.$ta();this.resizeTo(Math.max(1,_1.getVisibleWidth()-_2.left-_2.right),Math.max(1,_1.getVisibleHeight()-_2.top-_2.bottom))}
,isc.A.setEdgeImage=function isc_EdgedCanvas_setEdgeImage(_1){if(this.edgeImage==_1)return;this.edgeImage=_1;this.markForRedraw("setEdgeImage")}
);isc.B._maxIndex=isc.C+17;isc.defineClass("DropShadow","EdgedCanvas");isc.A=isc.DropShadow.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.skinImgDir="images/shared/shadows/";isc.A.edgeImage="[SKIN]ds.png";isc.A.isMouseTransparent=true;isc.A.shownEdges={center:true,TL:true,T:true,TR:true,L:true,R:true,BL:true,B:true,BR:true};isc.A.depth=4;isc.A.showShadow=false;isc.B.push(isc.A.initWidget=function isc_DropShadow_initWidget(){this.setDepth(this.depth);this.Super(this.$oc)}
,isc.A.setDepth=function isc_DropShadow_setDepth(_1){if(_1!=null)this.depth=_1;var _2=(this.softness||this.depth),_3=this.$zc!=_2;this.$zc=_2;this.edgeSize=2*this.$zc;var _4=this.$du(this.offset,Math.round(this.depth/ 2));this.$zd=this.$du(this.offsetX,_4);this.$ze=this.$du(this.offsetY,_4);this.updateEdgeSizes();if(this.isDrawn()){this.masterMoved();if(_3||this.isDirty())this.redraw();this.masterResized()}}
,isc.A.getEdgePrefix=function isc_DropShadow_getEdgePrefix(_1){if(_1!=isc.Canvas.CENTER)return this.$zc}
,isc.A.masterMoved=function isc_DropShadow_masterMoved(){var _1=this.visibility==isc.Canvas.HIDDEN,_2=this.masterElement,_3=_2.getOffsetLeft(),_4=_2.getOffsetTop();if(!_1){_3+=this.$zd-this.$zc;_4+=this.$ze-this.$zc}
this.moveTo(_3,_4)}
,isc.A.masterResized=function isc_DropShadow_masterResized(){if(this.visibility==isc.Canvas.HIDDEN)return;var _1=this.masterElement;this.resizeTo(_1.getVisibleWidth()+2*this.$zc,_1.getVisibleHeight()+2*this.$zc)}
,isc.A.fitToMaster=function isc_DropShadow_fitToMaster(){this.masterMoved();this.masterResized()}
,isc.A.setVisibility=function isc_DropShadow_setVisibility(_1,_2,_3,_4,_5){var _6=(_1!=this.visibility);this.invokeSuper(isc.DropShadow,"setVisibility",_1,_2,_3,_4,_5);if(_6){if(_1==isc.Canvas.HIDDEN){this.resizeTo(1,1);var _7=this.masterElement;this.moveTo(_7.getOffsetLeft(),_7.getOffsetTop())}else{this.fitToMaster()}}}
,isc.A.getCurrentCursor=function isc_DropShadow_getCurrentCursor(_1,_2,_3,_4){var _5=this.masterElement;if(_5&&_5.dragResizeFromShadow)this.canDragResize=_5.canDragResize;return this.invokeSuper(isc.DropShadow,"getCurrentCursor",_1,_2,_3,_4)}
,isc.A.prepareForDragging=function isc_DropShadow_prepareForDragging(_1,_2,_3,_4){var _5=this.masterElement;if(_5&&_5.dragResizeFromShadow)this.canDragResize=_5.canDragResize;return this.invokeSuper(isc.DropShadow,"prepareForDragging",_1,_2,_3,_4)}
);isc.B._maxIndex=isc.C+9;isc.ClassFactory.defineClass("Hover");isc.A=isc.Hover;isc.A.delay=500;isc.A.leftOffset=15;isc.A.topOffset=15;isc.A.hoverCanvasDefaults={defaultWidth:100,defaultHeight:1,baseStyle:"canvasHover",align:isc.Canvas.LEFT,valign:isc.Canvas.TOP,wrap:true,autoDraw:false};isc.A=isc.Hover;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.show=function isc_c_Hover_show(_1,_2,_3,_4){if(isc.isA.Canvas(_1)){this.showingHoverComponent=true;this.hoverCanvas=_1;this.hoverCanvas.hide=function(){this.Super("hide",arguments);isc.Hover.hoverCanvasHidden()};_4.hoverCanvas=_1}
if(!this.hoverCanvas)this.$zf();var _5=this.hoverCanvas;if(_1==null||_1==""){_5.hide();return}
this.lastHoverCanvas=_4;if(!this.showingHoverComponent)_5.setContents(_1);if(_2==null)_2={};var _6=this.hoverCanvasDefaults;if(_5.setAlign)_5.setAlign(_2.align||_6.align);if(_5.setVAlign)_5.setVAlign(_2.valign||_6.valign);if(_5.setBaseStyle)_5.setBaseStyle(_2.baseStyle||_6.baseStyle);if(_5.setOpacity)_5.setOpacity(_2.opacity||_6.opacity);if(_5.setWrap)_5.setWrap(_2.wrap!=null?_2.wrap:_6.wrap);if(_2.moveWithMouse!=null)this.$zg=_2.moveWithMouse
else this.$zg=this.moveWithMouse;var _7=isc.EH.getX(),_8=isc.EH.getY(),_9=_2.left,_10=_2.top,_11=_2.width||(this.showingHoverComponent?_5.width:_6.defaultWidth),_12=_2.height||(this.showingHoverComponent?_5.height:_6.defaultHeight);if(_9!=null||_10!=null){_9=_9?_9:_7+this.leftOffset;_10=_10?_10:_8+this.topOffset}else{_5.setRect(null,-9999,_11,_12);if(!_5.isDrawn())_5.draw();if(!_5.isVisible())_5.show();else _5.redrawIfDirty("placing hover");var _13=_3?_3:[_7-this.leftOffset,_8-this.topOffset,2*this.leftOffset,2*this.topOffset];var _14=_5.getPeerRect();var _15=isc.Canvas.$t7(_14[2],_14[3],_13,"bottom",false,"outside-right");_9=_15[0];_10=_15[1]}
_5.setRect(_9,_10,_11,_12);_5.bringToFront();if(!_5.isDrawn()||!_5.isVisible())_5.show();if(this.$zg){this.$zh=isc.Page.setEvent("mouseMove",function(){isc.Hover.$zi()})}
return}
,isc.A.hoverCanvasHidden=function isc_c_Hover_hoverCanvasHidden(){var _1=this.lastHoverCanvas;delete this.lastHoverCanvas;if(_1!=null){_1.$80l()}}
,isc.A.hide=function isc_c_Hover_hide(){var _1=isc.Hover.hoverCanvas;if(_1!=null){if(this.$zh){isc.Page.clearEvent("mouseMove",this.$zh);delete this.$zh}
_1.hide();if(this.showingHoverComponent){if(!_1)return;delete this.hoverCanvas;this.showingHoverComponent=false}else{var _2=this.hoverCanvasDefaults;_1.setRect(0,-1000)}}}
,isc.A.$zf=function isc_c_Hover__makeHoverCanvas(){var _1=isc.addProperties({hide:function(){this.Super("hide",arguments);isc.Hover.hoverCanvasHidden()}},this.hoverCanvasDefaults);this.hoverCanvas=isc.Label.create(_1)}
,isc.A.$zi=function isc_c_Hover__moveWithMouse(){var _1=this.hoverCanvas.getPeerRect();var _2=isc.Canvas.$t7(_1[2],_1[3],this.getMousePointerRect(),"bottom",false,"outside-right");this.hoverCanvas.moveTo(_2[0],_2[1])}
,isc.A.getMousePointerRect=function isc_c_Hover_getMousePointerRect(){return[isc.EH.getX()-this.leftOffset,isc.EH.getY()-this.topOffset,2*this.leftOffset,2*this.topOffset]}
,isc.A.setAction=function isc_c_Hover_setAction(_1,_2,_3,_4){if(_4==null)_4=this.delay;if(this.isActive||_4==0){_2.apply((_1?_1:this),_3?_3:[]);this.isActive=true}
else{if(this.timer!=null)this.timer=isc.Timer.clear(this.timer);this.actionTarget=(_1?_1:this);this.action=_2;this.actionArgs=_3?_3:[];this.timer=isc.Timer.setTimeout({target:isc.Hover,methodName:"$zj"},_4)}}
,isc.A.$zj=function isc_c_Hover__doAction(){if(this.action&&!this.actionTarget.destroyed){this.action.apply(this.actionTarget,this.actionArgs)}
this.actionTarget=this.action=this.actionArgs=null;this.isActive=true}
,isc.A.clear=function isc_c_Hover_clear(){this.hide();if(this.timer!=null)this.timer=isc.Timer.clear(this.timer);this.actionTarget=this.action=this.actionArgs=null;this.isActive=false}
);isc.B._maxIndex=isc.C+9;if(!isc.Comm)isc.ClassFactory.defineClass("Comm");isc.A=isc.Comm;isc.A.$zk=/^[\$_a-zA-Z][\$\w]*$/;isc.A.BACKREF_PREFIX="$$BACKREF$$:";isc.A.indent="    ";isc.A=isc.Comm;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.serialize=function isc_c_Comm_serialize(_1,_2){var _3={strictQuoting:false,dateFormat:"dateConstructor"};if(_2!=null)_3.prettyPrint=_2;return isc.JSON.encode(_1,_3)}
);isc.B._maxIndex=isc.C+1;isc.ClassFactory.defineClass("JSON",null,null,true);isc.A=isc.JSON;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.encode=function isc_c_JSON_encode(_1,_2){return isc.JSONEncoder.create(_2).encode(_1)}
,isc.A.decode=function isc_c_JSON_decode(_1){return eval("("+_1+")")}
);isc.B._maxIndex=isc.C+2;isc.ClassFactory.defineClass("JSONEncoder");isc.A=isc.JSONEncoder;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.B.push(isc.A.$zm=function isc_c_JSONEncoder__serialize_remember(_1,_2,_3){_1.obj.add(_2);_1.path.add(_3)}
,isc.A.$42b=function isc_c_JSONEncoder__serialize_cleanNode(_1){var _2=_1["$42c"];if(_2!=null){var _3=window[_2];if(_3&&_3.parentProperty&&_1[_3.parentProperty]){_1=_3.getCleanNodeData(_1)}}
return _1}
,isc.A.$zl=function isc_c_JSONEncoder__serialize_alreadyReferenced(_1,_2){var _3=_1.obj.indexOf(_2);if(_3==-1)return null;return _1.path[_3]}
,isc.A.$zp=function isc_c_JSONEncoder__serialize_addToPath(_1,_2){if(isc.isA.Number(_2)){return _1+"["+_2+"]"}else if(!isc.Comm.$zk.test(_2)){return _1+'["'+_2+'"]'}else{return _1+"."+_2}}
);isc.B._maxIndex=isc.C+4;isc.A=isc.JSONEncoder.getPrototype();isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.serializeInstances=true;isc.A.dateFormat="xmlSchema";isc.A.strictQuoting=true;isc.A.circularReferenceMode="path";isc.A.circularReferenceMarker="$$BACKREF$$";isc.A.prettyPrint=true;isc.B.push(isc.A.encode=function isc_JSONEncoder_encode(_1){this.objRefs={obj:[],path:[]};var _2=this.$eu(_1,this.prettyPrint?"":null,null);this.objRefs=null;return _2}
,isc.A.encodeDate=function isc_JSONEncoder_encodeDate(_1){if(this.dateFormat=="dateConstructor"){return _1.$eu()}else{return'"'+_1.toSchemaDate()+'"'}}
,isc.A.$eu=function isc_JSONEncoder__serialize(_1,_2,_3){if(!_3){if(_1&&_1.getID)_3=_1.getID();else _3=""}
if(_1==null)return null;if(isc.isA.String(_1))return(_1.asSource!=null?_1.asSource():String.asSource(_1));if(isc.isA.Function(_1))return null;if(isc.isA.Number(_1)||isc.isA.SpecialNumber(_1))return _1;if(isc.isA.Boolean(_1))return _1;if(isc.isA.Date(_1))return this.encodeDate(_1);if(isc.isAn.Instance(_1)){if(this.serializeInstances=="skip")return null;else if(this.serializeInstances=="short")return isc.echoLeaf(_1)}
if(isc.isA.Class(_1)){if(this.serializeInstances=="skip")return null;else if(this.serializeInstances=="short")return isc.echoLeaf(_1)}
var _4=isc.JSONEncoder.$zl(this.objRefs,_1);if(_4!=null&&_3.contains(_4)){var _5=_3.substring(_4.length,_4.length+1);if(_5=="."||_5=="["||_5=="]"){var _6=this.circularReferenceMode;if(_6=="marker"){return"'"+this.circularReferenceMarker+"'"}else if(_6=="path"){return"'"+this.circularReferenceMarker+":"+_4+"'"}else{return null}}}
if(_1==window){this.logWarn("Serializer encountered the window object at path: "+_3+" - returning null for this slot.");return null}
isc.JSONEncoder.$zm(this.objRefs,_1,_3);if(isc.isA.Function(_1.$eu))return _1.$eu(_2,this.objRefs,_3);if(isc.isAn.Array(_1))return this.$zn(_1,_3,this.objRefs,_2);var _7;if(_1.getSerializeableFields){_7=_1.getSerializeableFields([],[])}else{_7=_1}
return this.$zo(_7,_3,this.objRefs,_2)}
,isc.A.$zn=function isc_JSONEncoder__serializeArray(_1,_2,_3,_4){var _5=isc.SB.create();_5.append("[");for(var i=0,_7=_1.length;i<_7;i++){var _8=_1[i];if(_4!=null)_5.append("\r",_4,isc.Comm.indent);var _2=isc.JSONEncoder.$zp(_2,i);var _9=this.$eu(_8,(_4!=null?_4+isc.Comm.indent:null),_2);_5.append(_9+",");if(_4!=null)_5.append(" ")}
_5=_5.toString();var _10=_5.lastIndexOf(",");if(_10>-1)_5=_5.substring(0,_10);if(_4!=null)_5+="\r"+_4;_5+="]";return _5}
,isc.A.$zo=function isc_JSONEncoder__serializeObject(_1,_2,_3,_4){var _5=isc.SB.create(),_6;_1=isc.JSONEncoder.$42b(_1);try{for(var _7 in _1)break}catch(e){if(this.showDebugOutput){if(isc.isAn.XMLNode(_1))return isc.echoLeaf(_1);var _8;if(e.message){_8=(e.message.asSource!=null?e.message.asSource():String.asSource(e.message));return"{ cantEchoObject: "+_8+"}"}else{return"{ cantEchoObject: 'unspecified error' }"}}else return null}
_5.append("{");for(var _7 in _1){if(_7==null)continue;if(this.skipInternalProperties&&(isc.startsWith(_7,isc.$ag)||isc.startsWith(_7,isc.$75g)))continue;var _9=_1[_7];if(isc.isA.Function(_9))continue;if(_7!=isc.gwtRef&&isc.isAn.Instance(_9)&&this.serializeInstances=="skip")continue;var _10=_7.toString();if(!isc.Comm.$zk.test(_10)||this.strictQuoting){if(_10.contains('"')){if(_10.contains("'")){_10='"'+this.convertToEncodedQuotes(_10)+'"'}else{_10="'"+_10+"'"}}else{_10='"'+_10+'"'}}
var _2=isc.JSONEncoder.$zp(_2,_7);var _11;if(_7==isc.gwtRef){if(!this.showDebugOutput)continue;_11="{GWT Java Obj}"}else{_11=this.$eu(_9,(_4!=null?_4+isc.Comm.indent:null),_2)}
if(_4!=null)_5.append("\r",_4,isc.Comm.indent);_5.append(_10,":"+_11,",");if(_4!=null)_5.append(" ")}
_5=_5.toString();var _12=_5.lastIndexOf(",");if(_12>-1)_5=_5.substring(0,_12);if(_4!=null)_5+="\r"+_4;_5+="}";return _5}
,isc.A.convertToEncodedQuotes=function isc_JSONEncoder_convertToEncodedQuotes(_1){return _1.replace(String.$e0,"&quot;").replace(String.$ez,"&apos;")}
,isc.A.convertFromEncodedQuotes=function isc_JSONEncoder_convertFromEncodedQuotes(_1){return _1.replace(new RegExp("&quot;","g"),'"').replace(new RegExp("&apos;","g"),"'")}
);isc.B._maxIndex=isc.C+7;isc.addGlobal("clone",function(_1,_2){return isc.Comm.$360(_1)});isc.addGlobal("shallowClone",function(_1){return isc.Comm.$675(_1)});isc.A=isc.Comm;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.clone=isc.clone;isc.A.shallowClone=isc.shallowClone;isc.B.push(isc.A.$360=function isc_c_Comm__clone(_1){var _2;if(_1===_2)return _2;if(_1==null)return null;if(isc.isA.String(_1)||isc.isA.Boolean(_1)||isc.isA.Number(_1)||isc.isA.Function(_1))return _1;if(isc.isA.Date(_1))return _1.duplicate();if(isc.isAn.Array(_1))return isc.Comm.$361(_1);if(isc.isA.Function(_1.clone)){if(isc.isA.Class(_1))return isc.echoLeaf(_1);return _1.clone()}
return isc.Comm.$362(_1)}
,isc.A.$361=function isc_c_Comm__cloneArray(_1){var _2=[];for(var i=0,_4=_1.length;i<_4;i++){_2[i]=isc.Comm.$360(_1[i])}
return _2}
,isc.A.$362=function isc_c_Comm__cloneObject(_1){var _2={};for(var _3 in _1){var _4=_1[_3];if(_3==isc.gwtRef)continue;_2[_3]=isc.Comm.$360(_4)}
return _2}
,isc.A.$675=function isc_c_Comm__shallowClone(_1){var _2;if(_1===_2)return _2;if(_1==null)return null;if(isc.isA.String(_1)||isc.isA.Boolean(_1)||isc.isA.Number(_1)||isc.isA.Function(_1))return _1;if(isc.isA.Date(_1))return _1.duplicate();if(isc.isAn.Array(_1))return isc.Comm.$676(_1);return isc.addProperties({},_1)}
,isc.A.$676=function isc_c_Comm__shallowCloneArray(_1){var _2=[];for(var i=0,_4=_1.length;i<_4;i++){if(isc.isAn.Array(_1[i]))_2[i]=_1[i];else _2[i]=isc.Comm.$675(_1[i])}
return _2}
);isc.B._maxIndex=isc.C+5;isc.defineClass("AutoTest");isc.A=isc.AutoTest;isc.B=isc._allFuncs;isc.C=isc.B._maxIndex;isc.D=isc._funcClasses;isc.D[isc.C]=isc.A.Class;isc.A.fallback_valueOnlyField="$75w";isc.A.fallback_startMarker="[";isc.A.fallback_endMarker="]";isc.A.fallback_separator="||";isc.A.fallback_equalMarker="=";isc.A.slashMarker="$fs$";isc.A.robustLocatorWarning="If you are seeing unexpected results in recorded tests, it is likely"+" that the application has been modified since the test was recorded. We would recommend re-recording"+" your test script with the latest version of your application. Note that you may be able to"+" avoid seeing this message in future by using the AutoChild subsystem or providing explicit"+" global IDs to components whose function within the page is unlikely to change.";isc.B.push(isc.A.getLocator=function isc_c_AutoTest_getLocator(_1,_2){var _3;if(_1==null){_3=true;_1=isc.EH.lastEvent?isc.EH.lastEvent.nativeTarget:null}
var _4;if(isc.isA.Canvas(_1))_4=_1;else{_4=isc.AutoTest.locateCanvasFromDOMElement(_1)}
var _5=_4?_4.getLocator(_1,_3):"";if(_2&&_5&&_5!=""&&_4.checkLocatorForNativeElement(_5,_1))
{_5=""}
return _5}
,isc.A.locateCanvasFromDOMElement=function isc_c_AutoTest_locateCanvasFromDOMElement(_1){return isc.EH.getEventTargetCanvas(null,_1)}
,isc.A.getElement=function isc_c_AutoTest_getElement(_1){if(!_1)return null;if(_1.startsWith("'")||_1.startsWith('"'))_1=_1.substring(1);if(_1.endsWith("'")||_1.endsWith('"'))_1=_1.substring(0,_1.length-1);if(!_1.startsWith("//")){if(_1.startsWith("ID=")||_1.startsWith("id=")){_1=_1.substring(3)}
_1='//*any*[ID="'+_1+'"]'}
var _2=_1.split("/"),_3;var _4=_2[2];if(!_4)return null;_2=_2.slice(3);var _5=this.getBaseComponentFromLocatorSubstring(_4);var _6=_5?_5.getElementFromSplitLocator(_2):null;return _6}
,isc.A.getPageCoords=function isc_c_AutoTest_getPageCoords(_1){var _2=this.getElement(_1);if(_2==null)return;var _3=this.locateCanvasFromDOMElement(_2);return _3?_3.getAutoTestLocatorCoords(_1,_2):null}
,isc.A.getBaseComponentFromLocatorSubstring=function isc_c_AutoTest_getBaseComponentFromLocatorSubstring(_1){var _2=_1.match("(.*)\\[");var _3=_2?_2[1]:null;if(_3=="autoID"){var _4=isc.AutoTest.parseLocatorFallbackPath(_1),_5=_4.config,_6="name",_7="Class";return isc.Canvas.getCanvasFromFallbackLocator(_1,_5,isc.Canvas.$tw,_6,_7)}else{var _8=_3,_2=_1.match('ID=[\\"\'](.*)[\'\\"]'),_9=_2?_2[1]:null;if(_9==null)return null;var _10=window[_9];if(!_10)return null;if(_10&&_8!="*any*"&&(!isc.isA[_8]||!isc.isA[_8](_10)))
{this.logWarn("AutoTest.getElement(): Component:"+_10+" expected to be of class:"+_8)}
return _10}}
,isc.A.getLocatorCanvas=function isc_c_AutoTest_getLocatorCanvas(_1){if(_1==null||isc.isAn.emptyString(_1))return null;var _2=_1.split("/"),_3;if(_2==null||_2.length<3)return null;var _4=_2[2];var _5=_2.length;for(var i=3;i<_5;i++){_2[i-3]=_2[i]}
_2.length=_5-3;if(!_4)return null;var _7=this.getBaseComponentFromLocatorSubstring(_4);if(_7){var i=0,_8=_7.getChildFromLocatorSubstring(_2[i],i,_2);while(_8!=null){i++;_7=_8;_8=_7.getChildFromLocatorSubstring(_2[i],i,_2)}
return _7}
return null}
,isc.A.getLocatorFormItem=function isc_c_AutoTest_getLocatorFormItem(_1){if(_1==null||isc.isAn.emptyString(_1))return null;var _2=_1.split("/"),_3;if(_2==null||_2.length<3)return null;var _4=_2[2];var _5=_2.length;for(var i=3;i<_5;i++){_2[i-3]=_2[i]}
_2.length=_5-3;if(!_4)return null;var _7=this.getBaseComponentFromLocatorSubstring(_4);if(_7){var _8=_7.getChildFromLocatorSubstring(_2[0],0,_2);while(_8!=null){_2.removeAt(0);_7=_8;_8=_7.getChildFromLocatorSubstring(_2[0],0,_2)}}
if(isc.isA.DynamicForm(_7)){return _7.getItemFromSplitLocator(_2)}
return null}
,isc.A.createLocatorFallbackPath=function isc_c_AutoTest_createLocatorFallbackPath(_1,_2){var _3=[];for(var _4 in _2){var _5=_2[_4];if(isc.isA.String(_5)){_5=_5.replace("/",this.slashMarker);_5=escape(_5)}
if(_4==this.fallback_valueOnlyField){_3.add(_5)}else{_3.add(_4+this.fallback_equalMarker+_5)}}
return _1+this.fallback_startMarker+_3.join(this.fallback_separator)+this.fallback_endMarker}
,isc.A.parseLocatorFallbackPath=function isc_c_AutoTest_parseLocatorFallbackPath(_1){var _2=_1.split(this.fallback_startMarker);if(_2==null||_2.length<2)return;var _3=_2[0],_1=_2[1].substring(0,_2[1].length-this.fallback_endMarker.length);var _4=_1.split(this.fallback_separator),_5={};for(var i=0;i<_4.length;i++){var _7=_4[i],_8=_7.indexOf(this.fallback_equalMarker),_9;if(_8==-1){_9=this.fallback_valueOnlyField}else{_9=_7.substring(0,_8);_7=_7.substring(_8+1)}
_7=_7.replace(this.slashMarker,"/");_7=unescape(_7);_5[_9]=_7}
if(_2[2]!=null){var _7=_2[2].substring(0,_2[2].length-this.fallback_endMarker.length),_8=_7.indexOf(this.fallback_equalMarker),_10=_7.substring(0,_8),_11=_7.substring(_8+1);if(_11.startsWith("\""))_11=_11.substring(1,_11.length-1);_5[_10]=_11}
return{name:_3,config:_5}}
,isc.A.getObjectLocatorFallbackPath=function isc_c_AutoTest_getObjectLocatorFallbackPath(_1,_2,_3,_4){if(_3==null)_3={};if(_4==null)_4={title:"title",Class:"ClassName"};if(isc.isAn.Array(_4)){for(var i=0;i<_4.length;i++){var _6=_2.getProperty?_2.getProperty(_4[i]):_2[_4[i]];if(_6!=null&&!isc.isAn.emptyString(_6))_3[_4[i]]=_6}}else{for(var _7 in _4){var _6=_2.getProperty?_2.getProperty(_4[_7]):_2[_4[_7]];if(_6!=null&&!isc.isAn.emptyString(_6))_3[_7]=_6}}
return isc.AutoTest.createLocatorFallbackPath(_1,_3)}
,isc.A.logRobustLocatorWarning=function isc_c_AutoTest_logRobustLocatorWarning(){if(this.$75z)return;this.logWarn(this.robustLocatorWarning,"AutoTest");this.$75z=true}
);isc.B._maxIndex=isc.C+11;isc.ApplyAutoTestMethods=function(){isc.Canvas.addClassMethods({getCanvasLocatorFallbackPath:function(_9,_39,_165,_166,_167){if(_166==null)_166={};if(_167==null)_167={};else if(isc.isAn.Array(_167)){var _1={};for(var i=0;i<_167.length;i++){_1[_167[i]]=_167[i]}
_167=_1}
if(_167.title==null)_167.title="title";if(_167.scRole==null)_167.scRole="ariaRole";if(_167.name==null)_167.name="name";var _3=_39.getClassName(),_4=_39.getClass();_166.Class=_3;var _5;if(!_4.isFrameworkClass){_5=_4.$750}
if(_5!=null)_166.scClass=_5;if(_165!=null){_166.index=_165.indexOf(_39);_166.length=_165.length;var _6=_165.findAll("Class",_3);_166.classIndex=_6.indexOf(_39);_166.classLength=_6.length;if(_5!=null){var _7=_165.findAll("$75y",_5);_166.scClassIndex=_7.indexOf(_39);_166.scClassLength=_7.length}
if(_39.ariaRole!=null){var _8=_165.findAll("ariaRole",_39.ariaRole);_166.roleIndex=_8.indexOf(_39);_166.roleLength=_8.length}}
return isc.AutoTest.getObjectLocatorFallbackPath(_9,_39,_166,_167)},getCanvasFromFallbackLocator:function(_165,_43,_44,_45,_46){var _9=_43.name;var _10=_43.Class,_5=_43.scClass||_43.Class,_11=_43.scRole;switch(_45){case"name":if(_9!=null){var _12=_44.find("name",_9);if(_12){switch(_46){case"Class":if(_10&&isc.isA[_10]&&isc.isA[_10](_12)){if(this.logIsDebugEnabled("AutoTest")){this.logDebug("Locator string:"+_165+" - returning widget with matching name and ClassName:"+_12,"AutoTest")}
return _12}
case"scClass":if(_5&&isc.isA[_5]&&isc.isA[_5](_12))
{if(this.logIsDebugEnabled("AutoTest")){this.logDebug("Locator string:"+_165+" - returning widget with matching name and scClassName:"+_12,"AutoTest")}
return _12}
case"role":var _13=_43.scRole;if(_12.ariaRole==_13){if(this.logIsDebugEnabled("AutoTest")){this.logDebug("Locator string:"+_165+" - returning widget with matching name and role:"+_12,"AutoTest")}
return _12}
default:if(_46!="none"){isc.AutoTest.logRobustLocatorWarning();this.logWarn("Locator string:"+_165+". Returning closest match:"+_12+". This has the same name "+"as the recorded component but does not match class or role. ","AutoTest")}else{if(this.logIsDebugEnabled("AutoTest")){this.logDebug("Locator string:"+_165+" - returning widget with matching name:"+_12,"AutoTest")}}
return _12}}}
case"title":var _14=_43.title;if(_14!=null){var _15=_44.findAll("title",_14);if(_15&&_15.length>0){var _16;switch(_46){case"Class":if(_10){var _17=_15.findAll("Class",_10);if(_17!=null){_16=_17[0];if(_17.length==1&&_16){if(this.logIsDebugEnabled("AutoTest")){this.logDebug("Locator string:"+_165+" - returning widget with matching title and ClassName:"+_16,"AutoTest")}
return _16}}}
case"scClass":if(_5){var _17=_15.findAll("$75y",_5);if(_17!=null){if(_17.length==1||_16==null)
_16=_17[0];if(_17.length==1&&_16){if(this.logIsDebugEnabled("AutoTest")){this.logDebug("Locator string:"+_165+" - returning widget with matching name and scClassName:"+_16,"AutoTest")}
return _16}}}
case"role":if(_11){var _17=_15.findAll("ariaRole",_11);if(_17!=null){if(_17.length==1||_16==null)
_16=_17[0];if(_17.length==1&&_16){if(this.logIsDebugEnabled("AutoTest")){this.logDebug("Locator string:"+_165+" - returning widget with matching title and role:"+_16,"AutoTest")}
return _16}}}
default:if(_15.length==1){if(_46!="none"){isc.AutoTest.logRobustLocatorWarning();this.logWarn("Locator string:"+_165+". Returning closest match:"+_15[0]+". This has the same title "+"as the recorded component but does not match class or role.","AutoTest")}else{if(this.logIsDebugEnabled("AutoTest")){this.logDebug("Locator string:"+_165+" - returning widget with matching title:"+_16,"AutoTest")}}
return _15[0]}else{this.logWarn("Locator string:"+_165+", attempt to match by title failed -- multiple candidate components have this "+"same title. Attempting to match by index instead.","AutoTest")}}}}
default:var _18,_19,_20;switch(_46){case"Class":if(_10&&_43.classIndex){var _21=_44.findAll("Class",_10);if(_21&&_21.length>0){_18=_21[parseInt(_43.classIndex)];if(_21.length==parseInt(_43.classLength)){if(this.logIsInfoEnabled("AutoTest")){this.logInfo("Locator string:"+_165+" - returning widget with matching ClassName / index by ClassName:"+_18,"AutoTest")}
return _18}}}
case"scClass":if(_5&&_43.scClassIndex){var _22=_44.findAll("$75y",_5);if(_22&&_22.length>0){_19=_22[parseInt(_43.scClassIndex)];if(_22.length==parseInt(_43.scClassLength)){if(this.logIsInfoEnabled("AutoTest")){this.logInfo("Locator string:"+_165+" - returning widget with matching SmartClient superclass / index by ClassName:"+_19,"AutoTest")}
return _19}}}
case"role":if(_11&&_43.roleIndex){var _23=_44.findAll("ariaRole",_11);if(_23&&_23.length>0){_20=_23[parseInt(_43.roleIndex)];if(_23.length==parseInt(_43.roleLength)){if(this.logIsInfoEnabled("AutoTest")){this.logInfo("Locator string:"+_165+" - returning widget with matching role / index by role:"+_20,"AutoTest")}
return _20}}}
default:if((_46!="none"&&(_10||_5||_11))||(_43.length!=null&&(parseInt(_43.length)!=_44.length)))
{isc.AutoTest.logRobustLocatorWarning()}
var _24=_18||_19||_20;if(_24==null){var _25=_43[isc.AutoTest.fallback_valueOnlyField];if(_25==null)_25=_43.index;_25=parseInt(_25);_24=_44[_25]}
if(_24){this.logWarn("Locator string:"+_165+" matching by index gave "+_24+". Reliability cannot be guaranteed for matching by index if the underlying "+"application undergoes any changes.","AutoTest");return _24}}}
this.logDebug("AutoTest.getElement(): locator substring:"+_165+" parsed to fallback locator name:"+_9+", unable to find relevant child - may refer to inner element.","AutoTest")}});isc.Canvas.addMethods({getLocator:function(_50,_165){var _26,_27;if(this._generated||this.locatorParent||this.creator||this.$541){_27=this.getLocatorParent()}
if(!_27){_26=this.getLocatorRoot()}else{_26=_27.getLocator()+"/"+_27.getChildLocator(this)}
if(_50)return[_26,this.getInteriorLocator(_50,_165)].join("/");return _26},$549:["//",,'[ID="',,'"]'],getLocatorRoot:function(){if(!this.locatorRoot){if(this.$541&&this.parentElement==null){this.locatorRoot="//"+isc.Canvas.getCanvasLocatorFallbackPath("autoID",this,isc.Canvas.$tw)}else{this.$549[1]=this.getClassName();this.$549[3]=this.getID();this.locatorRoot=this.$549.join(isc.emptyString)}}
return this.locatorRoot},containsLocatorChild:function(_39){if(this.namedLocatorChildren!=null){for(var i=0;i<this.namedLocatorChildren.length;i++){var _9=this.namedLocatorChildren[i];if(isc.isAn.Object(_9))_9=_9.attribute;if(_39==this[_9]){return true}}}
return false},getLocatorParent:function(){if(this.locatorParent&&this.locatorParent.containsLocatorChild&&this.locatorParent.containsLocatorChild(this))
{return this.locatorParent}
if(this.creator&&(isc.isA.Canvas(this.creator)||isc.isA.FormItem(this.creator))){var _28=this.creator.getAutoChildLocator(this);if(_28==null){this.logInfo("Locator code failed to find relationship between parent:"+this.creator.getID()+" and autoChild:"+this.getID(),"AutoTest")}else{return this.creator}}
return this.masterElement||this.parentElement},$55a:[,"[",,'][Class="',,'"]'],getChildLocator:function(_39){if(_39==this.hscrollbar){return"hscrollbar"}
if(_39==this.vscrollbar){return"vscrollbar"}
if(_39.creator==this){var _29=this.getAutoChildLocator(_39);if(_29)return _29}
return this.getStandardChildLocator(_39)},checkLocatorForNativeElement:function(_67,_50){if(_50==null||_67==null)return false;return(isc.EventHandler.eventHandledNatively("mousedown",_50,true)&&(isc.AutoTest.getElement(_67)!=_50))},getCanvasLocatorFallbackPath:function(_9,_39,_165,_166,_167){return isc.Canvas.getCanvasLocatorFallbackPath(_9,_39,_165,_166,_167)},getAutoChildLocator:function(_39){if(this.$542){var _30=_39.getID();for(var _31 in this.$542){var _32=this.$542[_31];if(_32.contains(_30)){if(_39==this[_31])return _31;else{var _33=[];for(var i=0;i<_32.length;i++){_33[i]=window[_32[i]]}
return this.getCanvasLocatorFallbackPath(_31,_39,_33)}}}}
return null},getNamedLocatorChildString:function(_39){if(_39.locatorParent==this&&this.namedLocatorChildren){for(var i=0;i<this.namedLocatorChildren.length;i++){var _9=this.namedLocatorChildren[i],_34=_9;if(isc.isA.Object(_9)){_34=_9.attribute,_9=_9.name}
if(_39==this[_34]){return _9}}}},getStandardChildLocator:function(_39){var _35=this.getNamedLocatorChildString(_39);if(_35)return _35;if(_39.masterElement==this){return this.getCanvasLocatorFallbackPath("peer",_39,this.peers)}else if(_39.parentElement==this){return this.getCanvasLocatorFallbackPath("child",_39,this.children)}else{this.logWarn("unexpected error - failed to find relationship between parent:"+this.getID()+" and child:"+_39.getID());return _39.getLocatorRoot()}},getInteriorLocator:function(_50,_165){if(_50&&this.useEventParts){var _36=this.getElementPart(_50);if(_36!=null&&_36.part!=null){return(_36.partID&&_36.partID!=isc.emptyString)?_36.part+"_"+_36.partID:_36.part}}
return isc.emptyString},getElementFromSplitLocator:function(_165){var _37=this.getChildFromLocatorSubstring(_165[0],0,_165);if(_37){_165.removeAt(0);return _37.getElementFromSplitLocator(_165)}
return this.getInnerElementFromSplitLocator(_165)},getChildFromLocatorSubstring:function(_165,_25,_166){if(_165==null||_165=="")return null;if(isc.isA.Canvas(this[_165])){return this[_165]}
if(this.namedLocatorChildren!=null){var _38=this.namedLocatorChildren.find("name",_165);if(_38!=null){var _39=this[_38.attribute];if(isc.isA.Canvas(_39))return _39;this.logWarn("Locator substring:"+_165+" remaps to attribute:"+_38.attribute+" but no canvas exists under that attribute name.","AutoTest")}}
var _40=isc.AutoTest.parseLocatorFallbackPath(_165);if(_40!=null){return this.getChildFromFallbackLocator(_165,_40)}
return null},getChildLocatorStrategy:function(_165){if(isc.AutoTest.locStrategyNames==null){isc.AutoTest.locStrategyNames={}}
var _34=isc.AutoTest.locStrategyNames[_165];if(_34==null){var _41=_165;if(isc.isA.String(this.$558[_165]))_41=this.$558[_165];_34=isc.AutoTest.locStrategyNames[_165]="locate"+_41.substring(0,1).toUpperCase()+_41.substring(1)+"By"}
return this[_34]},getChildLocatorTypeStrategy:function(_165){if(isc.AutoTest.locStrategyTypes==null){isc.AutoTest.locStrategyTypes={}}
var _34=isc.AutoTest.locStrategyTypes[_165];if(_34==null){var _41=_165;if(isc.isA.String(this.$558[_165]))_41=this.$558[_165];_34=isc.AutoTest.locStrategyTypes[_165]="locate"+_41.substring(0,1).toUpperCase()+_41.substring(1)+"Type"}
return this[_34]},getChildFromFallbackLocator:function(_165,_40){var _42=_40.name,_43=_40.config;var _44=this.getFallbackLocatorCandidates(_42);if(_44&&_44.length>0){var _45=this.getChildLocatorStrategy(_42);if(_45==null)_45="name";var _46=this.getChildLocatorTypeStrategy(_42);if(_46==null)_46="Class";var _24=isc.Canvas.getCanvasFromFallbackLocator(_165,_43,_44,_45,_46);if(_24!=null)return _24}
this.logDebug("AutoTest.getElement(): locator substring:"+_165+" parsed to fallback locator name:"+_42+", unable to find relevant child - may refer to inner element.","AutoTest")},$558:{peer:"peers",child:"children"},getFallbackLocatorCandidates:function(_9){var _44;if(this.$542!=null&&this.$542[_9]!=null){var _47=this.$542[_9];_44=[];for(var i=0;i<_47.length;i++){_44[i]=window[_47[i]]}}else if(isc.isA.String(this.$558[_9])){_44=this[this.$558[_9]]}else if(this[_9]&&isc.isAn.Array(this[_9])){_44=this[_9]}
return _44},emptyLocatorArray:function(_165){return _165==null||_165.length==0||(_165.length==1&&_165[0]=="")},getInnerElementFromSplitLocator:function(_165){if(!this.emptyLocatorArray(_165)){if(_165.length==1){var _48=_165[0].split("_");var _49={part:_48[0],partID:_48[1]};var _50=this.getPartElement(_49);if(_50)return _50}}
return this.getHandle()},getAutoTestLocatorCoords:function(_67,_50){if(_67==null||_50==null)return null;var _51=isc.Element.getElementRect(_50);var _52=_51[0],_53=_51[2];_52+=Math.floor(_53/ 2);var _54=_51[1],_55=_51[3];_54+=Math.floor(_55/ 2);return[_52,_54]}});if(isc.Layout){isc.Layout.addProperties({getStandardChildLocator:function(_39){var _35=this.getNamedLocatorChildString(_39);if(_35)return _35;if(this.members.contains(_39)){return this.getCanvasLocatorFallbackPath("member",_39,this.members)}
return this.Super("getStandardChildLocator",arguments)},$558:{member:"members",peer:"peers",child:"children"}})}
if(isc.Window){isc.Window.addProperties({containsLocatorChild:function(_39){if(this.items&&this.items.contains(_39))return true;return this.Super("containsLocatorChild",arguments)},getStandardChildLocator:function(_39){if(this.items&&this.items.contains(_39)){var _56=this.$55a;_56[0]="item";_56[2]=this.items.indexOf(_39);_56[4]=_39.getClassName();return _56.join(isc.emptyString)}
return this.invokeSuper(isc.Window,"getStandardChildLocator",_39)},$558:{item:"items",member:"members",peer:"peers",child:"children"}})}
if(isc.SectionStack){isc.ImgSectionHeader.changeDefaults("$558",{item:"items"});isc.SectionHeader.changeDefaults("$558",{item:"items"});isc.SectionStack.changeDefaults("$558",{section:"sections"});isc.SectionStack.addProperties({getStandardChildLocator:function(_39){var _57=this.sections||[],_58;for(var i=0;i<_57.length;i++){var _59=_57[i].items,_60,_61;if(_39==_57[i]){_60=_39}else if(_59&&_59.contains(_39)){_60=_57[i];_61=_39}
if(_60!=null){_58=this.getCanvasLocatorFallbackPath("section",_60,this.sections)}
if(_61!=null){_58+="/"+this.getCanvasLocatorFallbackPath("item",_61,_60.items)}
if(_58!=null)return _58}
return this.Super("getStandardChildLocator",arguments)}})}
if(isc.StretchImg){isc.StretchImg.addProperties({getInteriorLocator:function(_50,_165){var _62=_50,_63=this.getHandle(),_64=this.getCanvasName();while(_50&&_50!=_63&&_50.getAttribute){var _30=_50.getAttribute("name");if(_30&&_30.startsWith(_64)){return _30.substring(_64.length)}
_50=_50.parentNode}
return this.Super("getInteriorLocator",[_62,_165])},getInnerElementFromSplitLocator:function(_165){if(!this.emptyLocatorArray(_165)&&_165.length==1){var _65=this.getImage(_165[0]);if(_65)return _65}
return this.Super("getInnerElementFromSplitLocator",arguments)}})}
if(isc.DynamicForm){isc.DynamicForm.addProperties({getInteriorLocator:function(_50){var _66=isc.DynamicForm.$mu(_50,this);if(!_66.item)return this.Super("getInteriorLocator",arguments);var _61=_66.item,_67=[this.getItemLocator(_61),'/'];if(_66.overElement)_67[_67.length]="element";else if(_66.overTitle)_67[_67.length]="title";else if(_66.overTextBox)_67[_67.length]="textbox";else if(_66.overControlTable)_67[_67.length]="controltable";else if(_66.overIcon)_67[_67.length]="[icon='"+_66.overIcon+"']"
return _67.join(isc.emptyString)},getItemLocator:function(_61){if(_61.parentItem&&(_61.parentItem!=this)){return this.getItemLocator(_61.parentItem)+"/"+_61.parentItem.getItemLocator(_61)}
var _68={};if(_61.name!=null)_68.name=_61.name;var _14=_61.getTitle();if(_14!=null)_68.title=_14;var _69=_61.getValue();if(_69!=null)_68.value=_69;_68.index=this.getItems().indexOf(_61);_68.Class=_61.getClassName();var _70=isc.AutoTest.createLocatorFallbackPath("item",_68);return _70},containsLocatorChild:function(_39){if(isc.isA.DateChooser(_39)&&_39.callingForm==this)return true;return this.Super("containsLocatorChild",arguments)},getChildLocator:function(_39){if(_39.canvasItem){var _61=_39.canvasItem;return this.getItemLocator(_61)+"/canvas"}
if(isc.isA.PickListMenu(_39)){var _61=_39.formItem;return this.getItemLocator(_61)+"/pickList"}
if(isc.isA.DateChooser(_39)){var _61=_39.callingFormItem;return this.getItemLocator(_61)+"/picker"}
return this.Super("getChildLocator",arguments)},getItemFromSplitLocator:function(_165){var _71=_165[0],_10;if(_71.contains("[Class=")){var _72=_71.match("item\\[(.+)'\\]\\[Class=\"(.+)\"\\]");_10=_72[1].substring(6,_72[1].length-2);_71=_72[0]}
var _73=isc.AutoTest.parseLocatorFallbackPath(_71);if(_73&&_73.name=="item"&&_73.config!=null){var _43=_73.config;_10=_43.Class;var _61;if(_43.name!=null){_61=this.getItem(_43.name)}else{for(var i=0;i<this.items.length;i++){var _74=this.items[i],_75=_74.locateItemBy;if(_75==null)_75="title";if(_75=="title"&&_43.title!=null&&_74.title==_43.title)
{_61=_74}else if(_75=="value"&&_43.value!=null&&_74.getValue()==_43.value)
{_61=_74}}
if(_61==null){var _25=_43.index;if(isc.isA.String(_25)){if(_25.startsWith("'")||_25.startsWith('"'))
{_25=_25.substring(1)}
_25=parseInt(_25)}
_61=this.items[_25]}}
if(!_61){this.logWarn("AutoTest.getElement(): Unable to find item from "+"locator string:"+_71);return null}
if(!isc.isA[_10]||!isc.isA[_10](_61)){this.logWarn("AutoTest.getElement(): identifier:"+_71+" returned an item of class:"+_61.getClassName())}
return _61}
return null},getInnerElementFromSplitLocator:function(_165){if(this.emptyLocatorArray(_165)){return this.getHandle()}
var _61=this.getItemFromSplitLocator(_165);if(_61!=null){_165.removeAt(0);return _61.getInnerElementFromSplitLocator(_165)}
return this.getHandle()}});isc.ContainerItem.addProperties({getItemLocator:isc.DynamicForm.getPrototype().getItemLocator,getItemFromSplitLocator:isc.DynamicForm.getPrototype().getItemFromSplitLocator,getInnerElementFromSplitLocator:function(_165){if(!this.emptyLocatorArray(_165)){var _76=this.getItemFromSplitLocator(_165);if(_76!=null){_165.removeAt(0);return _76.getInnerElementFromSplitLocator(_165)}}
return this.Super("getInnerElementFromSplitLocator",arguments)}});isc.FormItem.addProperties({getChildLocator:function formItem_getChildLocator(_165){if(_165.creator==this){var _29=this.getAutoChildLocator(_165);if(_29)return _29}},getAutoChildLocator:isc.Canvas.getPrototype().getAutoChildLocator,getLocator:function formItem_getLocator(){var _77=this.form;return _77.getLocator()+"/"+_77.getItemLocator(this)},getElementFromSplitLocator:function(_165){return this.getInnerElementFromSplitLocator(_165)},getInnerElementFromSplitLocator:function(_165){if(!this.emptyLocatorArray(_165)){var _49=_165[0];if(_49=="element")return this.getDataElement();if(_49=="title")return this.form.getTitleCell(this);if(_49=="textbox")return this.$15h();if(_49=="controltable")return this.$56a();if(_49=="canvas"){if(this.canvas){_165.removeAt(0);return this.canvas.getElementFromSplitLocator(_165)}}
if(_49=="picker"){if(this.picker){_165.removeAt(0);return this.picker.getElementFromSplitLocator(_165)}}
if(_49=="pickList"){if(!this.pickList)this.makePickList(false);_165.removeAt(0);return this.pickList.getElementFromSplitLocator(_165)}
var _78=_49.match("\\[icon='(.+)'\\]"),_79=_78?_78[1]:null;if(_79){return this.$16v(_79)}
if(this.$542){var _80=this.$903(_49);if(_80){_165.removeAt(0);return _80.getElementFromSplitLocator(_165)}}}else{var _50=this.getFocusElement();if(_50==null)_50=this.$15h();return _50}},$903:function(_9){var _80;var _32=this.$542?this.$542[_9]:null;if(_32&&_32.length>0){if(this[_9]!=null)_80=this[_9];else{}}
if(_80)return _80},emptyLocatorArray:isc.Canvas.getPrototype().emptyLocatorArray});isc.HeaderItem.addProperties({locateItemBy:"value"});if(isc.PickListMenu){isc.PickListMenu.addProperties({getLocatorParent:function(){if(this.formItem)return this.formItem.form;return this.Super("getLocatorParent",arguments)}})}}
if(isc.GridRenderer){isc.GridRenderer.addProperties({getInteriorLocator:function(_50,_165){var _81=this.getCellFromDomElement(_50);if(_81==null)return this.Super("getInteriorLocator",[_50,_165]);var _82=_81[0],_83=_81[1];return this.getCellLocator(_82,_83)},getCellFromDomElement:function(_50){var _63=this.getHandle(),_84=this.getTableElement();if(!_84)return null;var _85=_84.rows,_86,_87,_81,_88="tr",_89="TR",_90="td",_91="TD";while(_50&&_50!=_84&&_50!=_63){_86=_50.tagName;if(_86==_90||_86==_91){_81=_50}
if(_86==_88||_86==_89){_87=_50}
_50=_50.parentNode}
if(!_87||!_81)return null;var _85=_84.rows,_82,_92;for(var i=0;i<_85.length;i++){if(_85[i]==_87){_82=i;break}}
var _93=_87.cells,_83,_94;for(var i=0;i<_93.length;i++){if(_93[i]==_81){_83=i;break}}
_92=_82+(this.$252||0);_94=_83+(this.$254||0);return[_92,_94]},getCellLocator:function(_82,_83){return"row["+_82+"]/col["+_83+"]"},getInnerElementFromSplitLocator:function(_165){if(this.emptyLocatorArray(_165))return this.getHandle();if(_165.length==2){var _81=this.getCellFromLocator(_165[0],_165[1]),_82=_81[0],_83=_81[1];if(isc.isA.Number(_82)&&isc.isA.Number(_83)){if(this.$29p())return null;return this.getTableElement(_82,_83)}}
return this.Super("getInnerElementFromSplitLocator",arguments)},getCellFromLocator:function(_113,_110){var _95=_113.substring(4,_113.length-1),_96=_110.substring(4,_110.length-1);return[_82,_83]}})}
if(isc.ListGrid){isc.ListGrid.addProperties({namedLocatorChildren:["header","frozenHeader","body","frozenBody",{attribute:"$286",name:"editRowForm"}]});isc.GridBody.addProperties({getInteriorLocator:function(_50,_165){if(_165){var _32=this.children;if(_32!=null&&_32.length>0){for(var i=0;i<_32.length;i++){var _37=_32[i];if(_37&&_37.eventProxy==this){var _63=_37.getHandle();if(_63!=null){var _97=_50;while(_97!=this.getHandle()&&_97!=null)
{if(_97==_63){var _82=this.getEventRow(),_83=this.getEventColumn();return this.getCellLocator(_82,_83)}
_97=_97.parentNode}}}}}}
return this.Super("getInteriorLocator",arguments)},getCellLocator:function(_82,_83){var _98=this.grid;if(_98==null)return this.Super("getCellLocator",arguments);return _98.getCellLocator(this,_82,_83)}});isc.ListGrid.addProperties({getCellLocator:function(_109,_82,_83){var _99=this.getRowLocatorOptions(_109,_82,_83),_100=this.getColLocatorOptions(_109,_82,_83);return isc.AutoTest.createLocatorFallbackPath("row",_99)+"/"+isc.AutoTest.createLocatorFallbackPath("col",_100)},getRowLocatorOptions:function(_109,_82,_83){var _101={},_102=this.getFieldNumFromLocal(_83,_109),_103=this.getCellRecord(_82,_102),_104=this.getDataSource();if(_103!=null){if(_104!=null){var _105=_104.getPrimaryKeyFieldName();if(_105!=null&&_103[_105]!=null){_101[_105]=_103[_105]}}
var _106=this.getTitleField();if(_106!=null&&_103[_106]!=null){_101[_106]=_103[_106]}
var _107=this.getFieldName(_102);if(_107!=null&&_103[_107]!=null){_101[_107]=_103[_107]}}
_101[isc.AutoTest.fallback_valueOnlyField]=_82;return _101},getColLocatorOptions:function(_109,_82,_83){var _101={},_102=this.getFieldNumFromLocal(_83,_109);var _108=this.getField(_102);if(this.isCheckboxField(_108)){_101.isCheckboxField=true}else{var _107=this.getFieldName(_102);if(_107!=null)_101.fieldName=_107}
_101[isc.AutoTest.fallback_valueOnlyField]=_83;return _101},getChildFromLocatorSubstring:function(_165,_25,_166){if(_165=="frozenBody"||_165=="body"){if(_166.length==_25+3&&_166[_25+1].startsWith("row[")&&_166[_25+2].startsWith("col["))
{return null}}
return this.Super("getChildFromLocatorSubstring",arguments)},getInnerElementFromSplitLocator:function(_165){if(this.emptyLocatorArray(_165))return this.getHandle();var _109=_165[0];if(_165.length==3&&(_109=="body"||_109=="frozenBody")){var _110=_165[2],_111=isc.AutoTest.parseLocatorFallbackPath(_110);if(_111.name!="col"){this.logWarn("Error parsing locator:"+_165.join("")+" returning ListGrid handle");return this.getHandle()}
var _108=this.getFieldFromColLocatorConfig(_111.config),_112;if(_108==null){_112=parseInt(_111.config[isc.AutoTest.fallback_valueOnlyField]);if(_109=="frozenBody"&&this.frozenBody==null){_109="body"}
_109=this[_109]}else{_112=this.getLocalFieldNum(this.getFieldNum(_108));if(this.fieldIsFrozen(_108))_109=this.frozenBody;else _109=this.body}
if(_109==null)return null;var _113=_165[1],_114=isc.AutoTest.parseLocatorFallbackPath(_113),_82=this.getRowNumFromLocatorConfig(_114.config);if(isc.isA.Number(_82)&&isc.isA.Number(_112)){if(_109.$29p())return null;return _109.getTableElement(_82,_112)}}
return this.Super("getInnerElementFromSplitLocator",arguments)},getFieldFromColLocatorConfig:function(_165){if(_165.isCheckboxField!=null){for(var i=0;i<this.fields.length;i++){if(this.isCheckboxField(this.fields[i])){return this.fields[i]}
this.logWarn("AutoTest stored a locator for interaction with "+"checkbox field - but this grid is not showing a checkbox field - "+"recorded test may be invalid.","AutoTest");return-1}}else{var _115=this.locateColumnsBy;if(_115=="fieldName"||_115==null){var _107=_165.fieldName;if(_107!=null){return this.getField(_107)}}}},getRowNumFromLocatorConfig:function(_165){var _116=this.locateRowsBy;if(_116==null)_116="primaryKey";var _117=this.data,_118;switch(_116){case"primaryKey":var _104=this.getDataSource();if(_104!=null){var _119=_104.getPrimaryKeyFieldName();if(_104!=null&&_165[_119]!=null){return this.findRowNum(_165)}}
case"titleField":var _106=this.getTitleField();if(_106!=null&&_165[_106]!=null){var _120=_117.findAllIndices(_106,_165[_106]);if(_120.length==0)return-1
if(_120.length==1)return _120[0];var _121=[];for(var i=0;i<_120.length;i++){_121[_120[i]]=_117.get(_120[i])}
_118=_120[0];_117=_121}
case"targetCellValue":for(var _107 in _165){if(_107==isc.AutoTest.fallback_valueOnlyField)continue;if(_165[_107]!=null){var _120=_117.findAllIndices(_107,_165[_107]);if(_120.length==0)return-1
if(_120.length==1)return _120[0];var _121=[];for(var i=0;i<_120.length;i++){_121[_120[i]]=_117.get(_120[i])}
_118=_120[0];_117=_121}}
default:var _82=parseInt(_165[isc.AutoTest.fallback_valueOnlyField]);var _122;if(_118==null||_117[_82]!==_122)return _82;else return _118}}})}
if(isc.TreeGrid){isc.TreeGridBody.addProperties({getInteriorLocator:function(_50){var _62=_50;var _63=this.getHandle(),_123=this.getTableElement();if(!_50||!_63||!_123)return isc.emptyString;var _124=this.grid.getCanvasName()+this.grid.$34l,_82,_83;var _125=this.grid.getCanvasName()+this.grid.$349;while(_50!=this.tableElement&&_50!=_63&&_50.getAttribute){var _30=_50.getAttribute("name");if(_30){if(_30.startsWith(_124)){_82=parseInt(_30.substring(_124.length));_83=this.grid.getTreeFieldNum();return this.getCellLocator(_82,_83)+"/open"}
if(_30.startsWith(_125)){_82=parseInt(_30.substring(_125.length));_83=this.grid.getTreeFieldNum();return this.getCellLocator(_82,_83)+"/extra"}}
_50=_50.parentNode}
return this.Super("getInteriorLocator",[_62])},getInnerElementFromSplitLocator:function(_165){if(this.emptyLocatorArray(_165))return this.getHandle();if(_165.length==3){if(_165[2]=="open"){if(this.$29p())return null;var _113=_165[0];var _82;if(_113.charAt(3)!="["){_82=parseInt(_113.substring(3))}else{var _114=isc.AutoTest.parseLocatorFallbackPath(_113);if(_114==null||_114.name!="row"){this.logInfo("Locator appears to be click-in-open-area locator but "+"doesn't contain row/col info? returning null.\n"+_165.join("/"),"AutoTest")}
_82=this.grid.getRowNumFromLocatorConfig(_114.config)}
var _126=this.grid.$34l+_82,_65=this.grid.getImage(_126);if(_65)return _65}else if(_165[2]=="extra"){if(this.$29p())return null;var _113=_165[0];var _82;if(_113.charAt(3)!="["){_82=parseInt(_113.substring(3))}else{var _114=isc.AutoTest.parseLocatorFallbackPath(_113);if(_114==null||_114.name!="row"){this.logInfo("Locator appears to be click-in-open-area locator but "+"doesn't contain row/col info? returning null.\n"+_165.join("/"),"AutoTest")}
_82=this.grid.getRowNumFromLocatorConfig(_114.config)}
var _126=this.grid.$349+_82,_65=this.grid.getImage(_126);if(_65)return _65}}
return this.Super("getInnerElementFromSplitLocator",arguments)},getAutoTestLocatorCoords:function(_67,_50){var _127=this.Super("getAutoTestLocatorCoords",arguments);if(_127==null)return _127;var _128=this.grid;if(_128==null||_67.endsWith("open")||_67.endsWith("extra"))return _127;var y=_127[1],_82=this.getEventRow(y),_83=this.getEventColumn(_127[0]),_117=_128.data,_130=_128.getRecord(_82),_131=_128.getTreeFieldNum()==_128.getFieldNumFromLocal(_83,this);if(_131&&_128.data&&_128.data.isFolder(_130)){var _132=_128.getOpenAreaWidth(_130),_51=isc.Element.getElementRect(_50),_52=(_51[0]+_132),_53=_51[2]-_52;_127[0]=_52+Math.floor(_53/ 2)}
return _127}})}
if(isc.TabSet){isc.TabSet.addProperties({containsLocatorChild:function(_39){if(this.Super("containsLocatorChild",arguments))return true;if(this.getTabNumber(_39)!=-1)return true;return false},getStandardChildLocator:function(_39){var _133=this.getTabNumber(_39);if(_133!=-1){var _134=this.getTabObject(_133);var _135={};if(_134.ID!=null)_135.ID=_134.ID;if(_134.title!=null)_135.title=_134.title;_135.index=_133;return isc.AutoTest.createLocatorFallbackPath("tab",_135)}
return this.Super("getStandardChildLocator",arguments)},getChildFromLocatorSubstring:function(_165){if(_165&&_165.startsWith("tab[")){var _136=isc.AutoTest.parseLocatorFallbackPath(_165),_43=_136.config;if(_43.ID!=null){return this.getTab(_43.ID)}
var _137=this.locateTabsBy;if(_137==null)_137="title";if(_43.title&&_137=="title"){var _133=this.tabs.findIndex("title",_43.title);return this.getTab(_133)}
return this.getTab(parseInt(_43.index))}
return this.Super("getChildFromLocatorSubstring",arguments)}})}
if(isc.StatefulCanvas){isc.StatefulCanvas.addProperties({getInnerElementFromSplitLocator:function(_165){if(!this.emptyLocatorArray(_165)&&this.label){return this.label.getInnerElementFromSplitLocator(_165)}
return this.Super("getInnerElementFromSplitLocator",arguments)}})}
if(isc.DateChooser){isc.DateChooser.addMethods({getInteriorLocator:function(_50){var _63=this.getHandle();if(!_63||!_50)return"";var _138=_50.$73l;if(_138!=null&&_138!="")return _138;return _50.$73l=this.$73m(_50,_63)},$73m:function(_50,_63){var _139=_50;while(_139&&_139!=null){if(_139==_63){_139=null;break}
if(_139.tagName&&_139.tagName.toLowerCase()=="td"){break}
_139=_139.parentElement}
if(_139==null)return"";var _140=_63.childNodes,_141=[];for(var i=0;i<_140.length;i++){if(!_140[i].tagName||_140[i].tagName.toLowerCase()!="table"){continue}
_141[_141.length]=_140[i]}
var _142=_141.length==2?_141[0]:null,_143=_141.length==2?_141[1]:_141[0];if(_142!=null&&_139.offsetParent==_142){var _144=_139.onclick,_145=_144?_144.toString():null;if(!_145)return"";if(_145.contains("showPrevYear")){return"prevYearButton"}else if(_145.contains("showNextYear")){return"nextYearButton"}else if(_145.contains("showPrevMonth")){return"prevMonthButton"}else if(_145.contains("showNextMonth")){return"nextMonthButton"}else if(_145.contains("showMonthMenu")){return"monthMenuButton"}else if(_145.contains("showYearMenu")){return"yearMenuButton"}
return""}else if(_143!=null&&_139.offsetParent==_143){var _144=_139.onclick,_145=_144?_144.toString():null;if(!_145)return"";if(_145.contains("cancelClick"))return"cancelButton";else if(_145.contains("todayClick"))return"todayButton";else{var _146=_145.match("dateClick\\(\(.*\)\\)");if(_146&&_146[1]){var _147=_146[1].split(",");for(var i=0;i<_147.length;i++){_147[i]=_147[i].trim()}
return _147.join("/")}}}
return""},getInnerElementFromSplitLocator:function(_165){if(this.emptyLocatorArray(_165))return this.getHandle();var _63=this.getHandle();if(_63==null)return;var _148=(_165.length==3);if(!_148){var _58=_165[0];if(_58=="")return _63;var _149=(_58=="todayButton"),_150=!_149?(_58=="cancelButton"):false;var _140=_63.childNodes;if(_149||_150){if(_149&&!this.showTodayButton){this.logWarn("DateChooser attempting to locate element for "+"'todayButton' but showTodayButton is false. Returning handle.","AutoTest");return _63}
if(_150&&!this.showCancelButton){this.logWarn("DateChooser attempting to locate element for "+"'cancelButton' but showCancelButton is false. Returning handle.","AutoTest");return _63}
var _143;for(var i=_140.length-1;i>=0;i--){if(_140[i].tagName&&_140[i].tagName.toLowerCase()=="table")
{_143=_140[i];break}}
var _151=_143.rows[_143.rows.length-1],_93=_151.cells;for(var i=0;i<_93.length;i++){if(this.getInteriorLocator(_93[i])==_58){return _93[i]}}}else{if(!this.showHeader){this.logWarn("DateChooser attempting to locate element for "+_165+" but this.showHeader is false so this element will not be present. "+"Returning handle.","AutoTest");return _63}
var _142
for(var i=0;i<_140.length;i++){if(_140[i].tagName&&_140[i].tagName.toLowerCase()=="table")
{_142=_140[i];break}}
var _87=_142.rows[0],_93=_87.cells;for(var i=0;i<_93.length;i++){if(this.getInteriorLocator(_93[i])==_58){return _93[i]}}}}else{var _152=_165[0],_153=_165[1],_154=_165[2];if((_152==this.year)&&(this.month==_153||this.month==_153+1||this.month==_153-1))
{var _155=Date.createLogicalDate(_152,_153,_154),_156=_155.getDay();if(this.showWeekends||!Date.getWeekendDays().contains(_156)){var _157=Date.createLogicalDate(this.year,this.month,1);var _158=_157.getDay(),_159=_158+this.firstDayOfWeek-
(_158<this.firstDayOfWeek?7:0);_157.setDate(_157.getDate()-_159);if(Date.compareDates(_155,_157)!=1){var _160=Date.createLogicalDate(this.year,this.month+1,1);_160.setTime(_160.getTime()-86400000);var _161=_160.getDay(),_162=this.firstDayOfWeek+6;if(_162>6)_162-=7;var _163=_162>_161?_162-_161:_162+7-_161;if(_163!=0){_160.setTime(_160.getTime()+(86400000*_163))}
if(Date.compareDates(_155,_160)!=-1){var _82=Math.floor(((parseInt(_154)+_159)/7))
_82+=1;var _164=this.firstDayOfWeek;if(!this.showWeekends){while(Date.getWeekendDays().contains(_164)){_164+=1;if(_164==7)_164=0}}
var _83=_155.getDay()-_164;if(_83<0)_83+=7;var _140=_63.childNodes,_143;for(var i=_140.length-1;i>=0;i--){if(_140[i].tagName&&_140[i].tagName.toLowerCase()=="table")
{_143=_140[i];break}}
if(_143)return _143.rows[_82].cells[_83]}else{this.logInfo("DateChooser Passed ID for a date after end. "+"end date:"+[_160.getFullYear(),_160.getMonth(),_160.getDay()]+" vs:"+[_152,_153,_154],"AutoTest")}}else{this.logInfo("DateChooser Passed ID for a date before start date. "+"startDate:"+[_157.getFullYear(),_157.getMonth(),_157.getDay()]+" vs:"+[_152,_153,_154],"AutoTest")}}else{this.logInfo("DateChooser Passed ID for a weekend - not showing weekends","AutoTest")}}else{this.logInfo("DateChooser passed ID for the wrong year or month - passed:"+_165+", showing:"+[this.year,this.month],"AutoTest")}
this.logWarn("DateChooser - passed inner locator for date ("+_165.join("/")+") -- not currently showing this date.","AutoTest")}
this.logWarn("DateChooser, unable to find element for inner locator:"+_165+" returning handle");return _63}})}}
isc.AutoTest.customizeCalendar=function(){isc.$755={getRowLocatorOptions:function(_47,_28,_29){var _1=this.Super("getRowLocatorOptions",arguments);var _2=this.creator.chosenDate;_1.date=_2.toSchemaDate("date");_1.minutes=_28*30;return _1},getRowNumFromLocatorConfig:function(_47){var _3=this.creator.locateCellsBy;if((_3=="date"||_3==null)&&_47.date!=null)
{var _2=isc.Date.parseSchemaDate(_47.date);if(!this.showingDate(_2)){this.logWarn("Locator for cell in this calendar day-view grid has date "+"stored as:"+_2.toUSShortDate()+", but we're currently showing "+this.creator.chosenDate.toUSShortDate()+". The stored date doesn't map to a visible cell so not returning a cell "+"- if this is not the intended behavior in this test case you may need to "+"set calendar.locateCellsBy to 'index'.","AutoTest");return-1}
return parseInt(_47.minutes)/30}
this.locateRowsBy="index";return this.Super("getRowNumFromLocatorConfig",arguments)},showingDate:function(_2){return(isc.Date.compareLogicalDates(_2,this.creator.chosenDate)==0)}}
isc.DaySchedule.addProperties(isc.$755);isc.WeekSchedule.addProperties(isc.$755,{showingDate:function(_2){for(var i=0;i<this.fields.length;i++){var _5=this.fields[i];if(_5.$66a==null)continue;if(Date.compareLogicalDates(Date.createLogicalDate(_5.$66a,_5.$659,_5.$658),_2)==0)
{this.logWarn("does contain date"+_2.toShortDate());return true}
this.logWarn("date passed in:"+_2.toShortDate()+"compared with:"+Date.createLogicalDate(_5.$66a,_5.$659,_5.$658).toShortDate())}
this.logWarn("doesn't contain date:"+_2);return false},getColLocatorOptions:function(_47,_28,_29){var _6=this.Super("getColLocatorOptions",arguments),_7=this.getFieldNumFromLocal(_29,_47),_5=this.getField(_7);if(_5&&_5.$658!=null){_6.date=[_5.$66a,(_5.$659+1),_5.$658].join("-")}
return _6},getFieldFromColLocatorConfig:function(_47){if((this.locateCellsBy=="date"||this.locateCellsBy==null)&&(_47.date!=null))
{var _8=_47.date.split("-");return this.getFields().find("$658",_8[2])}
return this.Super("getFieldFromColLocatorConfig",arguments)}});isc.MonthSchedule.addProperties({getRowLocatorOptions:function(_47,_28,_29){var _1=this.Super("getRowLocatorOptions",arguments);var _9=this.getRecord(_28);if(!_9)return _1;var _5=this.getField(_29);var _10=_5.$66b;_1.dayIndex=_10;var _2=_9["date"+_10];_1.date=_2.toSchemaDate("date");var _11=_9["event"+_10];if(_11==null){_1.isHeaderRow=true}else{_1.isHeaderRow=false}
return _1},getRowNumFromLocatorConfig:function(_47){var _3=this.creator.locateCellsBy;if((_3=="date"||_3==null)&&_47.date!=null)
{var _2=isc.Date.parseSchemaDate(_47.date),_12=(_47.isHeaderRow=="true"),_13="date"+_47.dayIndex,_14="event"+_47.dayIndex;for(var i=0;i<this.data.length;i++){var _15=(this.data[i][_14]==null);if(_15==_12){if(Date.compareLogicalDates(this.data[i][_13],_2)==0){return i}}}
return-1}
this.locateRowsBy="index";return this.Super("getRowNumFromLocatorConfig",arguments)},getColLocatorOptions:function(_47,_28,_29){var _1=this.Super("getColLocatorOptions",arguments);_1.dayIndex=this.getField(_29).$66b;return _1},getColNumFromLocatorConfig:function(_47){var _3=this.locateCellsBy;if(_3==null||_3=="date"){return this.fields.findIndex("$66b",parseInt(_47.dayIndex))}
this.locateColsBy="index";return this.Super("getColNumFromLocatorConfig",arguments)}});isc.MonthScheduleBody.addProperties({getInteriorLocator:function(_47){if(_47.tagName.toLowerCase()=="a"){var _16=_47.href;if(_16!=null){var _17=_16.match("javascript:.*monthViewEventClick\\((\\d+),(\\d+),(\\d+)\\);");if(_17){var _18=parseInt(_17[1]),_19=parseInt(_17[2]),_20=parseInt(_17[3]);var _11=this.grid.getEvents(_18,_19),_21=_11[_20];if(_21==null){this.logWarn("Unable to determine event associated with apparent event "+"link element -- returning cell");return this.Super("getInteriorLocator",arguments)}
var _22=this.grid.creator,_23=_22.getEventLocatorConfig(_21);var _24=isc.AutoTest.createLocatorFallbackPath("eventLink",_23);return _24}}}
return this.Super("getInteriorLocator",arguments)},getInnerElementFromSplitLocator:function(_47){if(this.emptyLocatorArray(_47))return this.getHandle();if(_47.length==1&&_47[0].startsWith("eventLink")){var _25=isc.AutoTest.parseLocatorFallbackPath(_47[0]);var _22=this.grid.creator;var _21=_22.getEventFromLocatorConfig(_25.config);var _26=this.grid.getEventCell(_21);if(_26!=null){var _27=this.grid.data,_28=_26[0],_29=_26[1],_10=this.grid.getField(_29).$66b;var _30=this.getTableElement(_28,_29),_31=_30.getElementsByTagName("A");if(_31!=null){for(var _32=0;_32<_31.length;_32++){var _16=_31[_32].href;if(_16!=null){var _17=_16.match("javascript:.*monthViewEventClick\\((\\d+),(\\d+),(\\d+)\\);");if(_17&&_27[_28]["event"+_10][parseInt(_17[3])]==_21)
{return _31[_32]}}}}}
return this.Super("getInnerElementFromSplitLocator",arguments)}}});isc.Calendar.addProperties({getCanvasLocatorFallbackPath:function(_45,_47,_48,_49,_50){if(_45=="eventWindow"){var _1=this.getEventLocatorConfig(_47.event);return isc.AutoTest.createLocatorFallbackPath("eventWindow",_1)}
return this.Super("getCanvasLocatorFallbackPath",arguments)},getEventLocatorConfig:function(_21){var _23={};if(this.dataSource){var _33=this.getDataSource().getPrimaryKeyFieldName();_23[_33]=_21[_33]}
var _34=this.nameField;_23[_34]=_21[_34];var _35=this.startDateField;var _36=_21[_35];_23[_35]=_36.toSchemaDate();var _37=this.endDateField;var _38=_21[_37];_23[_37]=_38.toSchemaDate();_23.index=this.data.indexOf(_21);return _23},getChildFromFallbackLocator:function(_47,_48){var _39=_48.name,_23=_48.config;if(_39=="eventWindow"){var _40=this.mainView.getSelectedTab().viewName;if(_40=="day"){var _41=this.dayView.body.children}else if(_40=="week"){var _41=this.weekView.body.children}
if(_41!=null){var _21=this.getEventFromLocatorConfig(_23),_42=_41.find("event",_21);return _42}
this.logWarn("unable to find event window associated with event:"+this.echo(_21)+" based on locator string:"+_47+". It's possible that this event is not visible in the current view of "+"this Calendar","AutoTest");return null}
return this.Super("getChildFromFallbackLocator",arguments)},getEventFromLocatorConfig:function(_23){var _43=this.locateEventsBy;if(_43==null)_43="primaryKey";switch(_43){case"primaryKey":var _44=this.getDataSource();if(_44){var _33=_44.getPrimaryKeyFieldName();if(_33&&_23[_33]!=null){return this.data[this.data.findByKey(_23)]}}
case"name":var _45=_23[this.nameField];if(_45!=null)return this.data.find(this.nameField,_45);case"date":var _36=_23[this.startDateField],_38=_23[this.endDateField];for(var i=0;i<this.data.length;i++){var _46=this.data.get(i);if(_46==null)continue;if(_46[this.startDateField].toSchemaDate()==_36&&_46[this.endDateField].toSchemaDate()==_38)
{return _46}
this.logWarn("attempt to match calendar event by startDate / endDate "+"unable to locate any events. Backing off to index within data array")}
default:var _20=parseInt(_23.index);return this.data.get(_20)}}})}
if(isc.Calendar)isc.AutoTest.customizeCalendar();if(!isc.Page.isLoaded()){isc.Page.setEvent("load","isc.ApplyAutoTestMethods()")}else{isc.ApplyAutoTestMethods()}
isc.Page.logInfo("SmartClient Core ("+isc.version+" "+isc.buildDate+") initialized: "+(isc.timeStamp()-isc.$d)+"ms");isc.Page.logInfo("document.compatMode: "+document.compatMode);if(isc.Log.hasFireBug()){isc.Log.logWarn("NOTE: Firebug is enabled. Firebug greatly slows the performance of "+"applications that make heavy use of JavaScript. Isomorphic highly recommends Firebug "+"for troubleshooting, but Firebug and other development tools should be disabled when "+"assessing the real-world performance of SmartClient applications.")}
isc._moduleEnd=isc._Core_end=(isc.timestamp?isc.timestamp():new Date().getTime());if(isc.Log&&isc.Log.logIsInfoEnabled('loadTime'))isc.Log.logInfo('Core module init time: ' + (isc._moduleEnd-isc._moduleStart) + 'ms','loadTime');delete isc.definingFramework;}else{if(window.isc && isc.Log && isc.Log.logWarn)isc.Log.logWarn("Duplicate load of module 'Core'.");}
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

