/*============================================================
    "Enterprise" theme programmatic settings
    Copyright 2003 and beyond, Isomorphic Software
============================================================*/


isc.loadSkin = function (theWindow) {
if (theWindow == null) theWindow = window;
with (theWindow) {


//----------------------------------------
// Specify skin directory
//----------------------------------------
    // must be relative to your application file or isomorphicDir
    isc.Page.setSkinDir("[ISOMORPHIC]/skins/Graphite/")


//----------------------------------------
// Load skin style sheet(s)
//----------------------------------------
    isc.Page.loadStyleSheet("[SKIN]/skin_styles.css", theWindow)



//============================================================
//  Component Skinning
//============================================================
//   1) Scrollbars
//   2) Buttons
//   3) Resizebars
//   4) Sections
//   5) Progressbars
//   6) TabSets
//   7) Windows
//   8) Dialogs
//   9) Pickers
//  10) Menus
//  11) Hovers
//  12) ListGrids
//  13) TreeGrids
//  14) Form controls
//  15) Drag & Drop
//  16) Edges
//  17) Sliders
//  18) TileList
//  19) CubeGrid
//  20) FilterBuilder
//  21) Printing
//  22) ToolStrip
//============================================================


	isc.Canvas.addProperties({
		groupBorderCSS: "1px solid #A7ABB4"
	});

    if(isc.Browser.isIE && isc.Browser.version >= 7) {
        isc.Canvas.setAllowExternalFilters(false);
        isc.Canvas.setNeverUseFilters(true);
        if(isc.Window) {
          isc.Window.addProperties({
                modalMaskOpacity:null,
                modalMaskStyle:"normal"
            });
            isc.Window.changeDefaults("modalMaskDefaults", { src : "[SKIN]opacity.png" });
        }
    }

    if(isc.RPCManager) {
        isc.RPCManager.addClassProperties({ promptStyle:"cursor" });
    }

//----------------------------------------
// 1) Scrollbars
//----------------------------------------
    isc.Canvas.addProperties({
        showCustomScrollbars:true,
        scrollbarSize:16,
		cornerSize: 16
    })
    isc.ScrollThumb.addProperties({
        capSize:2,
        vSrc:"[SKIN]vthumb.png",
        hSrc:"[SKIN]hthumb.png",
        showGrip:true,
        gripLength:10,
        gripBreadth:10,        
		showRollOver: true,
		//showDown: true,
        backgroundColor:"transparent"
    })
    isc.Scrollbar.addProperties({
        btnSize:18,
        showRollOver:true,
        //showDown: true,
        thumbMinSize:20,
        thumbInset:0,
        thumbOverlap:2,
        backgroundColor:"#FFFFFF",
        vSrc:"[SKIN]vscroll.png",
        hSrc:"[SKIN]hscroll.png"
    })


//----------------------------------------
// 2) Buttons
//----------------------------------------
    
    // "IButton" is the new standard button class for SmartClient applications. Application
    // code should use IButton instead of Button for all standalone buttons. Other skins may
    // map IButton directly to Button, so this single class will work everywhere. Button remains
    // for internal and advanced uses (eg if you need to mix both CSS-based and image-based
    // standalone buttons in the same application).
    isc.defineClass("IButton", "StretchImgButton").addProperties({
        src:"[SKIN]button/button.png",
        height:22,
        width:100,
        capSize:4,
        vertical:false,
        titleStyle:"buttonTitle",
        showFocused:true,
        showFocusedAsOver:true
    });

    isc.defineClass("IAutoFitButton", "IButton").addProperties({
        autoFit: true,
        autoFitDirection: isc.Canvas.HORIZONTAL
    });
    if (isc.IButton.markAsFrameworkClass != null) isc.IButton.markAsFrameworkClass();
    if (isc.IAutoFitButton.markAsFrameworkClass != null) isc.IAutoFitButton.markAsFrameworkClass();


    isc.ImgButton.addProperties({
        showFocused: true,
        showFocusedAsOver:true

    });

    isc.defineClass("HeaderImgButton", "ImgButton").addProperties({
        showFocused: false,
        showRollOver:false,
        showFocusedAsOver: false,
        showDown:false
    });

	isc.Button.addProperties({
		height:22,
		showFocused: true,
		showFocusedAsOver: false
	});
	
	isc.Label.addProperties({
		showFocused: false
	});



//----------------------------------------
// 3) Resizebars
//----------------------------------------
    // StretchImgSplitbar class renders as resize bar with 
    // end caps, body, grip
    isc.StretchImgSplitbar.addProperties({
        // modify vSrc / hSrc for custom appearance
        //vSrc:"[SKIN]vsplit.gif",
        //hSrc:"[SKIN]hsplit.gif",
        capSize:10,
        showGrip:true
    })
    
    // ImgSplitbar renders as resizebar with resize grip only
    isc.ImgSplitbar.addProperties({
        // modify these properties for custom appearance
        //vSrc:"[SKIN]vgrip.png",
        //hSrc:"[SKIN]hgrip.png",
        //showDown:true,
        //styleName:"splitbar"
    })
    
    isc.Snapbar.addProperties({
        vSrc:"[SKIN]vsplit.png",
        hSrc:"[SKIN]hsplit.png",
        baseStyle:"splitbar",
	    /*items : [
    	    {name:"blank", width:"capSize", height:"capSize"},
    		{name:"blank", width:"*", height:"*"},
	    	{name:"blank", width:"capSize", height:"capSize"}
        ],*/
        items : [
    	    {name:"bg", width:"*", height:"*"}
        ],
        showDownGrip:false,
        showClosedGrip:false,
        showRollOver:false,
        showDown:false,
        gripBreadth:3,
        gripLength:20
        //capSize:8
    })
    
    isc.Layout.addProperties({
        resizeBarSize:5,
        // Use the Snapbar as a resizeBar by default - subclass of Splitbar that 
        // shows interactive (closed/open) grip images
        // Other options include the Splitbar, StretchImgSplitbar or ImgSplitbar
        resizeBarClass:"Snapbar"
    })

    
//----------------------------------------
// 4) Sections
//----------------------------------------
    if (isc.SectionItem) {
        isc.SectionItem.addProperties({
            sectionHeaderClass:"ImgSectionHeader",
            height:24
        })
    }
    if (isc.SectionStack) {

        isc.SectionStack.addProperties({
            backgroundColor:null,
            sectionHeaderClass:"ImgSectionHeader",
            headerHeight:24
        })
        isc.ImgSectionHeader.changeDefaults("backgroundDefaults", {
            showRollOver:false,
            showDown:false,
            showDisabledIcon:false,
            showRollOverIcon:false,
            src:"[SKIN]SectionHeader/header.png",
            icon:"[SKIN]SectionHeader/opener.png",
			iconSize: 16,
            capSize:2,
            titleStyle:"imgSectionHeaderTitle",
            baseStyle:"imgSectionHeader",
            backgroundColor:"transparent"
        })
        isc.SectionHeader.addProperties({
            icon:"[SKIN]SectionHeader/opener.png",
			iconSize: 16
        })
    }


//----------------------------------------
// 5) Progressbars
//----------------------------------------
    if (isc.Progressbar) {
        isc.Progressbar.addProperties({
            horizontalItems: [
            {name:"h_start",size:2},
            {name:"h_stretch",size:0},
            {name:"h_end",size:2},
            {name:"h_empty_start",size:2},
            {name:"h_empty_stretch",size:0},
            {name:"h_empty_end",size:2}
            ],
            verticalItems: [
            {name:"v_empty_start",size:2},
            {name:"v_empty_stretch",size:0},
            {name:"v_empty_end",size:0},
            {name:"v_start",size:2},
            {name:"v_stretch",size:0},
            {name:"v_end",size:2}
            ],
            breadth:24,
            length : 300
        })
    }


//----------------------------------------
// 6) TabSets
//----------------------------------------
    if (isc.TabSet) {
        isc.TabSet.addProperties({
            tabBarThickness:28,
            scrollerButtonSize:19,
            pickerButtonSize:20,
       
            symmetricScroller:false,
            symmetricPickerButton:false,

            scrollerSrc:"[SKIN]scroll.png",
            pickerButtonSrc:"[SKIN]picker.png",

            closeTabIconSize:12,
            iconOrientation:"right",

            showEdges:false,
            paneContainerClassName:"tabSetContainer",
            
            paneMargin:5,
            
            showScrollerRollOver: false
        });
        isc.TabSet.changeDefaults("paneContainerDefaults", {
            showEdges:false
        })
        isc.TabBar.addProperties({
            membersMargin:3,

            // keep the tabs from reaching the curved edge of the pane (regardless of align)
            layoutStartMargin:5,
            layoutEndMargin:5,

            styleName:"tabBar",
            leftStyleName:"tabBarLeft",
            topStyleName:"tabBarTop",
            rightStyleName:"tabBarRight",
            bottomStyleName:"tabBarBottom",

            // have the baseline overlap the top edge of the TabSet, using rounded media
            baseLineConstructor:"Canvas",
            baseLineProperties : {
                backgroundColor: "#767F92",
                overflow:"hidden",
                height:5
            },
            baseLineThickness:3
        })
    }    
    if (isc.ImgTab) {
        isc.ImgTab.addProperties({
            src:"[SKIN]tab.png",
            capSize:6,
            showRollOver:true,
            showDown:false,
            showDisabled:true,
            showDisabledIcon:false,
            titleStyle:"tabTitle"
        })
    }


//----------------------------------------
// 7) Windows
//----------------------------------------
    if (isc.Window) {

        isc._edgeOpacity = isc.Browser.isChrome ? 100 : 90;
        
        isc.Window.addProperties({
            edgeOpacity:isc._edgeOpacity,
            maskEdgeCenterOnly: isc._edgeOpacity < 100,            
            // rounded frame edges
            showEdges:true,
            edgeImage: "[SKINIMG]Window/window.png",
            customEdges:null,
            edgeSize:3,
            edgeTop:23,
            edgeBottom:3,
			edgeOffsetTop:2,
			edgeOffsetRight:3,
			edgeOffsetBottom:3,
            showHeaderBackground:false, // part of edges
            showHeaderIcon:true,
            modalMaskOpacity:10,
            // clear backgroundColor and style since corners are rounded
            backgroundColor:null,
			border: null,
            styleName:"normal",
            edgeCenterBackgroundColor:"#FFFFFF",
            //bodyColor:null,
            bodyColor:"transparent",
            bodyStyle:"windowBody",

            layoutMargin:0,
            membersMargin:0,

            showFooter:false,

            showShadow:false,
            shadowDepth:5
        })

        isc.Window.changeDefaults("headerDefaults", {
            layoutMargin:0,
            height:21
        })
        isc.Window.changeDefaults("resizerDefaults", {
            src:"[SKIN]/Window/resizer.png"
        })

        isc.Window.changeDefaults("headerIconDefaults", {
            width:15,
            height:15,
            src:"[SKIN]/Window/headerIcon.png"
        })
        isc.Window.changeDefaults("restoreButtonDefaults", {
             src:"[SKIN]/headerIcons/cascade.png",
             showRollOver:true,
             showDown:false,
             width:15,
             height:15
        })
        isc.Window.changeDefaults("closeButtonDefaults", { 
             src:"[SKIN]/headerIcons/close.png",
             showRollOver:true,
             showDown:false,
             width:15,
             height:15
        })
        isc.Window.changeDefaults("maximizeButtonDefaults", { 
             src:"[SKIN]/headerIcons/maximize.png",
             showRollOver:true,
             width:15,
             height:15
        })
        isc.Window.changeDefaults("minimizeButtonDefaults", { 
             src:"[SKIN]/headerIcons/minimize.png",
             showRollOver:true,
             showDown:false,
             width:15,
             height:15
        })
        isc.Window.changeDefaults("toolbarDefaults", {
            buttonConstructor: "IButton"
        })
        
        if (isc.ColorPicker) {
            isc.ColorPicker.addProperties({
                layoutMargin:0
            })
        }

//----------------------------------------
// 8) Dialogs
//----------------------------------------
        if (isc.Dialog) {
            isc.Dialog.addProperties({
                bodyColor:"transparent",
                hiliteBodyColor:"transparent"
            })
            // even though Dialog inherits from Window, we need a separate changeDefaults block
            // because Dialog defines its own toolbarDefaults
            isc.Dialog.changeDefaults("toolbarDefaults", {
                buttonConstructor: "IButton",
                height:42, // 10px margins + 22px button
                membersMargin:10
            })
            if (isc.Dialog.Warn && isc.Dialog.Warn.toolbarDefaults) {
                isc.addProperties(isc.Dialog.Warn.toolbarDefaults, {
                    buttonConstructor: "IButton",
                    height:42,
                    membersMargin:10
                })
            }
        }
        
    } // end isc.Window


//----------------------------------------
// 9) Pickers
//----------------------------------------
    // add bevels and shadows to all pickers
    isc.__pickerDefaults = {
        showEdges:true,
        edgeSize:6,
        edgeImage: "[SKINIMG]Window/window.png",
        backgroundColor:"#FFFFFF",
        showShadow:false,
        shadowDepth:6,
        shadowOffset:5
    }
    if (isc.ButtonTable) {
        isc.ButtonTable.addProperties({
            backgroundColor:"#FFFFFF"
        })
    }
    if (isc.FormItem) {
        isc.FormItem.changeDefaults("pickerDefaults", isc.__pickerDefaults)
        isc.FormItem.addProperties({
            defaultIconSrc:"[SKIN]/DynamicForm/default_formItem_icon.png"
        });
    }
    if (isc.CheckboxItem) {
        isc.CheckboxItem.addProperties({
            checkedImage:"[SKINIMG]/DynamicForm/checked.png",
            uncheckedImage:"[SKINIMG]/DynamicForm/unchecked.png",
            unsetImage:"[SKINIMG]/DynamicForm/unsetcheck.png",
            partialSelectedImage:"[SKINIMG]/DynamicForm/partialcheck.png",
            valueIconWidth:13,
            valueIconHeight:13,
            showValueIconOver:false,
            showValueIconFocused:false
        })
    }
    if(isc.RelationItem) {
        isc.RelationItem.changeDefaults("removeButtonDefaults", {
            src: "[SKIN]DynamicForm/Remove_icon.png"
        });
    }

    if (isc.DateChooser) {
        isc.DateChooser.addProperties({
            headerStyle:"dateChooserButton",
            weekendHeaderStyle:"dateChooserWeekendButton",
            baseNavButtonStyle:"dateChooserNavButton",
            baseWeekdayStyle:"dateChooserWeekday",
            baseWeekendStyle:"dateChooserWeekend",
            baseBottomButtonStyle:"dateChooserBottomButton",
            alternateWeekStyles:false,
        
            showEdges:true,

            edgeImage: "[SKINIMG]Window/window.png",
            edgeSize:3,
            edgeTop:26,
            edgeBottom:3,
			edgeOffsetTop:1,
			edgeOffsetRight:3,
			edgeOffsetLeft:3,
			edgeOffsetBottom:5,

            todayButtonHeight:20,

            headerHeight:24,

            edgeCenterBackgroundColor:"#FFFFFF",
            backgroundColor:null,
    
            showShadow:false,
            shadowDepth:6,
            shadowOffset:5,

            showDoubleYearIcon:false,
            skinImgDir:"images/DateChooser/",
            prevYearIcon:"[SKIN]doubleArrow_left.png",
            prevYearIconWidth:16,
            prevYearIconHeight:16,
            nextYearIcon:"[SKIN]doubleArrow_right.png",
            nextYearIconWidth:16,    
            nextYearIconHeight:16,
            prevMonthIcon:"[SKIN]arrow_left.png",
            prevMonthIconWidth:16,
            prevMonthIconHeight:16,
            nextMonthIcon:"[SKIN]arrow_right.png",
            nextMonthIconWidth:16,
            nextMonthIconHeight:16
        });
    }
    if (isc.MultiFilePicker) {
        isc.MultiFilePicker.addProperties({
            backgroundColor:"#C7C7C7"
        })
    }
    if (isc.RelationPicker) {
        isc.RelationPicker.addProperties({
            backgroundColor:"#C7C7C7"    
        })
    }

    // Native FILE INPUT items are rendered differently in Safari from other browsers
    // Don't show standard textbox styling around them as it looks odd
    if (isc.UploadItem && isc.Browser.isSafari) {
        isc.UploadItem.addProperties({
            textBoxStyle:"normal"
        });
    }
//----------------------------------------
// 10) Menus
//----------------------------------------
    if (isc.Menu) {
        isc.Menu.addProperties({
            cellHeight:22,
            fastCellUpdates:false,
            showShadow:false,
            shadowDepth:5,
            showEdges:false,
            submenuImage:{src:"[SKIN]submenu.png", height:7, width:4},
            submenuDisabledImage:{src:"[SKIN]submenu_disabled.png", height:7, width:4},
	        checkmarkImage:{src:"[SKIN]check.png", width:9, height:8},
	        checkmarkDisabledImage:{src:"[SKIN]check_disabled.png", width:7, height:6},
            bodyStyleName:"gridBody",
			iconBodyStyleName:"menuMain",
            bodyBackgroundColor:null
        });
		isc.addProperties(isc.Menu.ICON_FIELD, {
			width:24,
			baseStyle:"menuIconField"
		});
		isc.Menu.TITLE_FIELD.baseStyle = "menuTitleField";
    }
    
    if (isc.MenuButton) {
        isc.MenuButton.addProperties({
			baseStyle: "menuButton",
            menuButtonImage:"[SKIN]menu_button.png",
            menuButtonImageUp:"[SKIN]menu_button_up.png",
            iconWidth:7,
            iconHeight:4,
            showFocusedAsOver:true
        });
    }
    if (isc.IMenuButton) {
        isc.IMenuButton.addProperties({
            src:"[SKIN]button/button.png",
            height:22,
            capSize:4,
            vertical:false,
            titleStyle:"buttonTitle",
            showFocused:true,
            showFocusedAsOver:true,
            
            menuButtonImage:"[SKIN]menu_button.png",
            menuButtonImageUp:"[SKIN]menu_button_up.png",
            iconWidth:7,
            iconHeight:4
        });
    }
	
	if (isc.SelectionTreeMenu) {
		isc.SelectionTreeMenu.addProperties({
			showIcons:false,
			showKeys:false,
            bodyStyleName:"treeMenuBody",
            bodyBackgroundColor:null
		});
	}

//----------------------------------------
// 11) Hovers
//----------------------------------------
    if (isc.Hover) {
        isc.addProperties(isc.Hover.hoverCanvasDefaults, {
            showShadow:false,
            shadowDepth:5
        })
    }


//----------------------------------------
// 12) ListGrids
//----------------------------------------
    if (isc.ListGrid) {										  
        isc.ListGrid.addProperties({
            alternateRecordStyles : true,            
            editFailedCSSText:"color:FF6347;",
            errorIconSrc : "[SKINIMG]actions/exclamation.png",
			tallBaseStyle: "tallCell",

            headerButtonConstructor:"Button",
            sorterConstructor:"ImgButton",
            headerMenuButtonConstructor:"ImgButton",
            
            sortAscendingImage:{src:"[SKIN]sort_ascending.png", width:9, height:6},
            sortDescendingImage:{src:"[SKIN]sort_descending.png", width:9, height:6},
            
            backgroundColor:null, bodyBackgroundColor:null,

            headerHeight:23,
            summaryRowHeight:21,
            cellHeight:22,
            normalCellHeight:22,
            headerBackgroundColor:null,
            headerBaseStyle:"headerButton",
            
            bodyStyleName:"gridBody",
            alternateBodyStyleName:null,
            
            summaryRowStyle:"gridSummaryCell",
            groupSummaryStyle:"groupSummaryCell",

            showHeaderMenuButton:true,
            headerMenuButtonConstructor:"HeaderImgButton",
            headerMenuButtonWidth:17,
            headerMenuButtonSrc:"[SKIN]/ListGrid/header_menu.png",
            headerMenuButtonIcon:"[SKINIMG]/ListGrid/sort_descending.png",
            headerMenuButtonIconWidth: 9,
            headerMenuButtonIconHeight: 6,

            groupLeadingIndent : 1,
            groupIconPadding : 3,            
            groupIcon: "[SKINIMG]/ListGrid/group.png",

            expansionFieldTrueImage : "[SKINIMG]/ListGrid/row_expanded.png",
            expansionFieldFalseImage: "[SKINIMG]/ListGrid/row_collapsed.png",
            expansionFieldImageWidth : 16,
            expansionFieldImageHeight : 16,
            checkboxFieldImageWidth : 13,
            checkboxFieldImageHeight : 13
            
        })
        isc.ListGrid.changeDefaults("sorterDefaults", { 
            // baseStyle / titleStyle is auto-assigned from headerBaseStyle
            showFocused:false,
            src:"[SKIN]ListGrid/header.png",
            baseStyle:"sorterButton"
        })
        isc.ListGrid.changeDefaults("headerButtonDefaults", {
            showRollOver:true,
            showDown:false,
            showFocused:false,
            // baseStyle / titleStyle is auto-assigned from headerBaseStyle
            baseStyle:"headerButton"
        })
        isc.ListGrid.changeDefaults("headerMenuButtonDefaults", {
            showDown:false,
            showTitle:true,
            src:"[SKIN]ListGrid/header.png"
        })
        isc.ListGrid.changeDefaults("summaryRowDefaults", {
            bodyBackgroundColor:null,
            bodyStyleName:"summaryRowBody"
        });

    }

   if (isc.TreeGrid) {
        isc.TreeGrid.addProperties({
            alternateRecordStyles : false,
			tallBaseStyle: "treeTallCell",
			normalBaseStyle: "treeCell",
            openerImage:"[SKIN]opener.png",
            sortAscendingImage:{src:"[SKINIMG]ListGrid/sort_ascending.png", width:9, height:6},
            sortDescendingImage:{src:"[SKINIMG]ListGrid/sort_descending.png", width:9, height:6}            
        })
    }

    if(isc.MultiSortPanel) {
        isc.MultiSortPanel.changeDefaults("levelUpButtonDefaults", {
			src: "[SKINIMG]TransferIcons/up.png",
            height: 22,
            width: 24
		});
        isc.MultiSortPanel.changeDefaults("levelDownButtonDefaults", {
			src: "[SKINIMG]TransferIcons/down.png",
            height: 22,
            width: 24
		});
    }
//----------------------------------------
// 13) TreeGrids
//----------------------------------------
    if (isc.TreeGrid) {
        isc.TreeGrid.addProperties({
			openerIconSize: 22,
            folderIcon:"[SKIN]folder.png",
            nodeIcon:"[SKIN]file.png",
            manyItemsImage:"[SKIN]folder_file.png"
        })
    }
    if (isc.ColumnTree) {
        isc.ColumnTree.addProperties({
            folderIcon:"[SKIN]folder.png",
            nodeIcon:"[SKIN]file.png"
        });
    }


//----------------------------------------
// 14) Form controls
//----------------------------------------
    if (isc.FormItem) {isc.FormItem.addProperties({
        defaultIconSrc:"[SKIN]/DynamicForm/default_formItem_icon.png",
        errorIconSrc : "[SKINIMG]actions/exclamation.png",
        iconHeight:18,
        iconWidth:18,
        iconVAlign:"middle"
        
    })}
    
    if (isc.PickTreeItem) {isc.PickTreeItem.addProperties({
        buttonDefaults: {
            height:21
        }
    })}

    if (isc.TextItem) {isc.TextItem.addProperties({
        height:22,
        showFocused: true
    })}

    if (isc.TextAreaItem) {isc.TextAreaItem.addProperties({
        showFocused: true     
    })}
    
    if (isc.SelectItem) {isc.SelectItem.addProperties({            
        textBoxStyle:"selectItemText",
        showFocusedPickerIcon:false,
        pickerIconSrc:"[SKIN]/pickers/comboBoxPicker.png",
        height:22,
        pickerIconWidth:18
    })}
    
    if (isc.ComboBoxItem) {isc.ComboBoxItem.addProperties({
        textBoxStyle:"selectItemText",
        showFocusedPickerIcon:false,
        pendingTextBoxStyle:"comboBoxItemPendingText",
        pickerIconSrc:"[SKIN]/pickers/comboBoxPicker.png",
        height:22,
        pickerIconWidth:18
    })}
    // used by SelectItem and ComboBoxItem for picklist
    if (isc.ScrollingMenu) {isc.ScrollingMenu.addProperties({
        showShadow:false,
        shadowDepth:5
    })}
    if (isc.DateItem) {
        isc.DateItem.addProperties({
            height:22,
            pickerIconWidth:16,
            pickerIconHeight:14,
            pickerIconSrc:"[SKIN]/DynamicForm/date_control.png"
        })
    }
    if (isc.SpinnerItem) {
        isc.SpinnerItem.addProperties({
            textBoxStyle:"selectItemText",
            height:22
        })
        isc.SpinnerItem.INCREASE_ICON = isc.addProperties(isc.SpinnerItem.INCREASE_ICON, {
            width:16,
            height:11,
            showRollOver:true,
            showFocused:true,
            showDown:true,
            imgOnly:true,
            src:"[SKIN]/DynamicForm/spinner_control_increase.png"
        })
        isc.SpinnerItem.DECREASE_ICON = isc.addProperties(isc.SpinnerItem.DECREASE_ICON, {
            width:16,
            height:11,
            showRollOver:true,
            showFocused:true,
            showDown:true,
            imgOnly:true,
            src:"[SKIN]/DynamicForm/spinner_control_decrease.png"
        })
    }
    
    
    
    if (isc.PopUpTextAreaItem) {isc.PopUpTextAreaItem.addProperties({
        popUpIconSrc: "[SKIN]/DynamicForm/text_control.gif",
        popUpIconWidth:16,
        popUpIconHeight:16
    })}
    if (isc.ButtonItem && isc.IButton) {isc.ButtonItem.addProperties({
        showFocused:true,
        showFocusAsOver:false,
        buttonConstructor:isc.IButton,
        height:22
    })}

    if (isc.ToolbarItem && isc.IAutoFitButton) {isc.ToolbarItem.addProperties({
        buttonConstructor:isc.IAutoFitButton,
        buttonProperties: {
            autoFitDirection: isc.Canvas.BOTH
        }
    })}

    if(isc.DateRangeDialog) {
        isc.DateRangeDialog.changeDefaults("headerIconProperties", {
            src: "[SKIN]/DynamicForm/date_control.png"
        });
    }
    if(isc.MiniDateRangeItem) {
        isc.MiniDateRangeItem.changeDefaults("pickerIconDefaults", {
            src: "[SKIN]/DynamicForm/date_control.png"
        });
    }
    if(isc.RelativeDateItem) {
        isc.RelativeDateItem.changeDefaults("pickerIconDefaults", {
            src: "[SKIN]/DynamicForm/date_control.png"
        });
    }
 

//----------------------------------------
// 15) Drag & Drop
//----------------------------------------
    // drag tracker drop shadow (disabled by default because many trackers are irregular shape)
    //isc.addProperties(isc.EH.dragTrackerDefaults, {
    //    showShadow:false,
    //    shadowDepth:4
    //});
    // drag target shadow and opacity
    isc.EH.showTargetDragShadow = true;
    isc.EH.targetDragOpacity = 50;


    
//----------------------------------------
// 16) Edges
//----------------------------------------
    // default edge style serves as a pretty component frame/border - just set showEdges:true
    if (isc.EdgedCanvas) {
        isc.EdgedCanvas.addProperties({
            edgeSize:3,
            edgeImage: "[SKINIMG]edges/edge.png"
        })
    }


//----------------------------------------
// 17) Sliders
//----------------------------------------
    if (isc.Slider) {
        isc.Slider.addProperties({
            thumbThickWidth:14,
            thumbThinWidth:14,
            trackWidth:5,
            trackCapSize:2,
            thumbSrc:"thumb.png",
            trackSrc:"track.png"
        })
    }

//----------------------------------------
// 18) TileList
//----------------------------------------
    if (isc.TileGrid) {
        isc.TileGrid.addProperties({
            valuesShowRollOver: true,
            styleName:null,
            showEdges:true
        })
    }
    
// ----------------------------------------
// 19) CubeGrid
//----------------------------------------
    if (isc.CubeGrid) {
        isc.CubeGrid.addProperties({
            bodyStyleName:"cubeGridBody",
            alternateBodyStyleName:"alternateCubeGridBody"
        });
    }

// ----------------------------------------
// 20) FilterBuilder
//----------------------------------------
	if (isc.FilterBuilder) {
		isc.FilterBuilder.changeDefaults("addButtonDefaults", {
			showFocused: false
		});
		isc.FilterBuilder.changeDefaults("removeButtonDefaults", {
			showFocused: false
		});
	}

// -------------------------------------------
// 21) Printing
// -------------------------------------------
    if (isc.PrintWindow) {
        isc.PrintWindow.changeDefaults("printButtonDefaults", {
            height: 18
        });
    }

// -------------------------------------------
// 21) Printing
// -------------------------------------------
    if (isc.Calendar) {
        isc.Calendar.changeDefaults("datePickerButtonDefaults", {
            showDown:false,
            showOver : false,
            src:"[SKIN]/DynamicForm/date_control.png"
        });

        isc.Calendar.changeDefaults("controlsBarDefaults", {
            height:10,
            layoutTopMargin :5
        });
        isc.Calendar.changeDefaults("addEventButtonDefaults", {
            src:"[SKINIMG]actions/plus.png"
        });
    }

// -------------------------------------------
// 22) ToolStrip
// -------------------------------------------
    if(isc.ToolStrip) {
        isc.ToolStrip.addProperties({
            height:30,
            defaultLayoutAlign:"center",
			verticalStyleName:"toolStripVertical"
        })
    }

// -------------------------------------------
// ExampleViewPane - used in the feature explorer
// -------------------------------------------
    if (isc.ExampleViewPane) {
        isc.ExampleViewPane.addProperties({
            styleName:"normal"
        });
    }    

// specify where the browser should redirect if not supported
isc.Page.checkBrowserAndRedirect("[SKIN]/unsupported_browser.html");

}   // end with()
}   // end loadSkin()

isc.loadSkin()

