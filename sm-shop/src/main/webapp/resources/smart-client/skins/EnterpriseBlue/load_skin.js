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
        isc.Page.setSkinDir("[ISOMORPHIC]/skins/EnterpriseBlue/");
        
        //----------------------------------------
        // Load skin style sheet(s)
        //----------------------------------------
        isc.Page.loadStyleSheet("[SKIN]/skin_styles.css", theWindow)

        //TODO: For development purposes, useCSS3 as false. Later on the boolean value will be
        var useCSS3 = false;
        //determined by checking browser if it is CSS3 compliant.

        if (useCSS3) {

            isc.Canvas.setProperties({
                // this skin uses custom scrollbars
                groupBorderCSS:"1px solid #165fa7",
                showCustomScrollbars:true
            });
            

            if (isc.Browser.isIE && isc.Browser.version >= 7) {
                isc.Canvas.setAllowExternalFilters(false);
                isc.Canvas.setNeverUseFilters(true);
                
                if (isc.Window) {
                    isc.Window.addProperties({
                        modalMaskOpacity:null,
                        modalMaskStyle:"normal"
                    });
                    isc.Window.changeDefaults("modalMaskDefaults", { src:"[SKIN]opacity.png" });
                }
            }

            if (isc.RPCManager) {
                isc.RPCManager.addClassProperties({ 
                    promptStyle:"cursor" 
                });
            }
            
            //----------------------------------------
            // 1) Scrollbars
            //----------------------------------------
            isc.SimpleScrollThumb.addProperties({
                baseStyle:"scrollThumb",
                hSrc:"[SKIN]hthumb_grip.png",
                vSrc:"[SKIN]vthumb_grip.png"
            });
            
            isc.Scrollbar.addProperties({
                baseStyle:"scrollbar",
                btnSize:18,
                hSrc:"[SKIN]hscroll.png",
                hThumbClass:isc.HSimpleScrollThumb,
                showRollOver:true,
                thumbInset:0,
                thumbMinSize:20,
                thumbOverlap:2,
                vSrc:"[SKIN]vscroll.png",
                vThumbClass:isc.VSimpleScrollThumb
            });
            
            
            //----------------------------------------
            // 2) Buttons
            //----------------------------------------
            isc.Button.addProperties({
                height:22,
                baseStyle:"button"
            });

            // define IButton so examples that support the new SmartClient skin image-based
            // button will fall back on the CSS-based Button with this skin
            isc.ClassFactory.defineClass("IButton", "Button").addProperties({
                baseStyle:"buttonRounded"
            });
            isc.ClassFactory.defineClass("IAutoFitButton", "AutoFitButton").addProperties({
                baseStyle:"buttonRounded"
            });
            
            if (isc.IButton.markAsFrameworkClass != null) isc.IButton.markAsFrameworkClass();
            if (isc.IAutoFitButton.markAsFrameworkClass != null) isc.IAutoFitButton.markAsFrameworkClass();

            isc.ClassFactory.defineClass("HeaderMenuButton", "IButton").addProperties({
                baseStyle:"headerButton"
            });

            // Have IMenuButton be just a synonym for IMenuButton
            if (isc.MenuButton) {
                isc.ClassFactory.overwriteClass("IMenuButton", "MenuButton");
                
                if (isc.IMenuButton.markAsFrameworkClass != null) isc.IMenuButton.markAsFrameworkClass();
                
                isc.MenuButton.addProperties({
                    // copy the header (.button) background-color to match when sort arrow is hidden
                    baseStyle:"button"
                });
            }

            if (isc.MenuButton) {
                isc.MenuButton.addProperties({
                    baseStyle:"menuButton",
                    iconHeight:4,
                    iconWidth:7,
                    menuButtonImage:"[SKIN]menu_button.png",
                    menuButtonImageUp:"[SKIN]menu_button_up.png",
                    showFocusedAsOver:true
                });
            }
            
            if (isc.IMenuButton) {
                isc.IMenuButton.addProperties({
                    capSize:4,
                    height:22,
                    iconWidth:7,
                    iconHeight:4,
                    menuButtonImage:"[SKIN]menu_button.png",
                    menuButtonImageUp:"[SKIN]menu_button_up.png",
                    showFocused:true,
                    showFocusedAsOver:true,
                    src:"[SKIN]button/button.png",
                    titleStyle:"buttonTitle",
                    vertical:false,
                    width:100
                });
            }

            if (isc.Menu) {
                isc.Menu.addProperties({
                    bodyBackgroundColor:null,
                    bodyStyleName:"gridBody",
                    cellHeight:22,
                    checkmarkDisabledImage:{src:"[SKIN]check_disabled.png", width:7, height:6},
                    checkmarkImage:{src:"[SKIN]check.png", width:9, height:8},
                    fastCellUpdates:false,
                    iconBodyStyleName:"menuMain",
                    shadowDepth:5,
                    showEdges:false,
                    showShadow:false,
                    submenuDisabledImage:{src:"[SKIN]submenu_disabled.png", height:7, width:4},
                    submenuImage:{src:"[SKIN]submenu.png", height:7, width:4}
                });
                
                isc.addProperties(isc.Menu.ICON_FIELD, {
                    baseStyle:"menuIconField",
                    width:24
                });
                
                isc.Menu.TITLE_FIELD.baseStyle = "menuTitleField";
            }

            if (isc.PickTreeItem) {
                isc.PickTreeItem.addProperties({
                    buttonDefaults:{ height:21 }
                });
            }

            isc.Label.addProperties({
                showFocused:false
            });

            //----------------------------------------
            // 3) Resizebars
            //----------------------------------------
            // StretchImgSplitbar class renders as resize bar
            isc.StretchImgSplitbar.addProperties({
                capSize:10,
                showGrip:true,
                showOver:false
            });

            isc.Snapbar.addProperties({
                hBaseStyle:"hSplitbar",
                vBaseStyle:"vSplitbar",
                gripBreadth:3,
                gripLength:20,
                hSrc:"[SKIN]hsplit.png",
                items:[
                    {name:"blank", width:"*", height:"*"}
                ],
                showClosedGrip:false,
                showDown:false,
                showDownGrip:false,
                showRollOver:false,
                vSrc:"[SKIN]vsplit.png"
            });

            isc.Layout.addProperties({
                resizeBarSize:5,
                // Use the Snapbar as a resizeBar by default - subclass of Splitbar that 
                // shows interactive (closed/open) grip images
                // Other options include the Splitbar, StretchImgSplitbar or ImgSplitbar
                resizeBarClass:"Snapbar"
            })

            if (isc.SectionItem) {
                isc.SectionItem.addProperties({
                    height:26
                });
            }
            if (isc.SectionStack) {
                isc.SectionStack.addProperties({
                    headerHeight:26
                });
            }

            if (isc.ListGrid) {
                isc.ListGrid.addProperties({
                    alternateRecordStyles:true,
                    alternateBodyStyleName:null,
                    backgroundColor:"#e7e7e7",
                    cellHeight:22,
                    checkboxFieldImageHeight:13,
                    checkboxFieldImageWidth:13,
                    editFailedCSSText:"color:FF6347;",
                    errorIconSrc:"[SKINIMG]actions/exclamation.png",
                    expansionFieldImageHeight:16,
                    expansionFieldImageWidth:16,
                    expansionFieldFalseImage:"[SKINIMG]/ListGrid/row_collapsed.png",
                    expansionFieldTrueImage:"[SKINIMG]/ListGrid/row_expanded.png",
                    expansionFieldImageWidth: 16,
                    expansionFieldImageHeight: 16,
                    groupIcon:"[SKINIMG]/ListGrid/group.png",
                    groupIconPadding:3,
                    groupLeadingIndent:1,
                    headerBackgroundColor:null,
                    headerBaseStyle:"headerButton",
                    headerHeight:23,
                    headerMenuButtonIcon:"[SKINIMG]ListGrid/sort_descending.png",
                    headerMenuButtonConstructor:"HeaderMenuButton",
                    headerMenuButtonWidth:17,
                    normalCellHeight:22,
                    showHeaderMenuButton:true,
                    sortAscendingImage:{src:"[SKINIMG]ListGrid/sort_ascending.png", width:9, height:6},
                    sortDescendingImage:{src:"[SKINIMG]ListGrid/sort_descending.png", width:9, height:6}, 
                    summaryRowHeight:21,
                    tallBaseStyle:"tallCell"
                });
            }

            if (isc.TreeGrid) {
                isc.TreeGrid.addProperties({
                    alternateRecordStyles:false,
                    folderIcon:"[SKIN]folder.png",
                    manyItemsImage:"[SKIN]folder_file.png",        
                    nodeIcon:"[SKIN]file.png",
                    normalBaseStyle:"treeCell",
                    openerIconSize:22,
                    openerImage:"[SKIN]opener.png",
                    sortAscendingImage:{src:"[SKINIMG]ListGrid/sort_ascending.png", width:9, height:6},
                    sortDescendingImage:{src:"[SKINIMG]ListGrid/sort_descending.png", width:9, height:6},   
                    tallBaseStyle:"treeTallCell"
                });
            }

            if (isc.TabSet) {
                isc.TabSet.addProperties({
                    closeTabIconSize:12,
                    paneContainerClassName:"tabSetContainer",
                    paneMargin:5,
                    pickerButtonSize:20,
                    pickerButtonSrc:"[SKIN]picker.png",
                    showScrollerRollOver:false,
                    scrollerButtonSize:19,
                    scrollerSrc:"[SKIN]scroll.png",
                    showEdges:false,
                    symmetricScroller:false,
                    symmetricPickerButton:false,
                    tabBarThickness:24,
                    useSimpleTabs:true
                });

                // In Netscape Navigator 4.7x, set the backgroundColor directly since the css
                // background colors are not reliable
                if (isc.Browser.isNav) {
                    isc.TabSet.addProperties({paneContainerDefaults:{backgroundColor:"#FFFFFF"}});
                }

                isc.TabBar.addProperties({
                    baseLineConstructor:"Canvas",
                    baseLineProperties:{ backgroundColor:"#C0C3C7", height:1, overflow:"hidden" },
                    baseLineThickness:1,
                    bottomStyleName:"tabBarBottom",
                    layoutEndMargin:5,
                    layoutStartMargin:5,
                    leadingMargin:5,
                    leftStyleName:"tabBarLeft",
                    membersMargin:1,
                    rightStyleName:"tabBarRight",
                    styleName:"tabBar",
                    topStyleName:"tabBarTop"
                });
            }

            if (isc.ImgTab) isc.ImgTab.addProperties({capSize:6});
            
            if (isc.Window) {
                isc.Window.addProperties({
                    backgroundColor:null,
                    bodyStyle:"windowBody",
                    layoutBottomMargin:4,
                    layoutLeftMargin:4,
                    layoutRightMargin:4,
                    layoutTopMargin:1,
                    modalMaskOpacity:10,
                    membersMargin:0,
                    styleName:"windowBackground",
                    showHeaderBackground:false,
                    showFooter:false
                });
                
                isc.Window.changeDefaults("headerDefaults", {
                    height:20,
                    layoutMargin:0
                });
                
                isc.Window.changeDefaults("resizerDefaults", { src:"[SKIN]/Window/resizer.png" });
                
                isc.Window.changeDefaults("headerIconDefaults", {
                    height:15,
                    src:"[SKIN]/Window/headerIcon.png",
                    width:15
                });
                
                isc.Window.changeDefaults("restoreButtonDefaults", {
                    height:15,
                    showDown:false,
                    showRollOver:true,
                    src:"[SKIN]/headerIcons/cascade.png",
                    width:15
                });
                
                isc.Window.changeDefaults("closeButtonDefaults", {
                    height:15,
                    showDown:false,
                    showRollOver:true,
                    src:"[SKIN]/headerIcons/close.png",
                    width:15
                });
                
                isc.Window.changeDefaults("maximizeButtonDefaults", {
                    height:15,
                    showRollOver:true,
                    src:"[SKIN]/headerIcons/maximize.png",
                    width:15
                });
                
                isc.Window.changeDefaults("minimizeButtonDefaults", {
                    height:15,
                    showDown:false,
                    showRollOver:true,
                    src:"[SKIN]/headerIcons/minimize.png",
                    width:15
                });
                
                isc.Window.changeDefaults("toolbarDefaults", { buttonConstructor:"IButton" });

                if (isc.ColorPicker) {
                    isc.ColorPicker.addProperties({
                        layoutMargin:2
                    });
                }       
            }

            if (isc.Dialog) {
                isc.Dialog.addProperties({
                    bodyColor:"#FFFFFF",
                    bodyStyle:"windowBody",
                    layoutBottomMargin:4,
                    layoutLeftMargin:4,
                    layoutRightMargin:4,
                    layoutTopMargin:1,
                    modalMaskOpacity:10,
                    membersMargin:0,
                    styleName:"windowBackground",
                    showHeaderBackground:false,
                    showFooter:false
                });
                
                // even though Dialog inherits from Window, we need a separate changeDefaults block
                // because Dialog defines its own toolbarDefaults
                isc.Dialog.changeDefaults("toolbarDefaults", {
                    buttonConstructor:"IButton",
                    height:42, // 10px margins + 22px button
                    membersMargin:10
                });
                
                if (isc.Dialog.Warn && isc.Dialog.Warn.toolbarDefaults) {
                    isc.addProperties(isc.Dialog.Warn.toolbarDefaults, {
                        buttonConstructor:"IButton",
                        height:42,
                        membersMargin:10
                    });
                }
            }

            // Dynamic form skinning
            if (isc.SectionHeader) {
                isc.SectionHeader.addProperties({
                    icon:"[SKIN]/SectionHeader/opener.png"
                });
            }
            
            if (isc.FormItem) {
                isc.FormItem.addProperties({
                    defaultIconSrc:"[SKIN]/DynamicForm/default_formItem_icon.png",
                    errorIconSrc:"[SKINIMG]actions/exclamation.png",
                    iconHeight:18,
                    iconVAlign:"middle",
                    iconWidth:18
                });
            }
            
            if (isc.CheckboxItem) {
                isc.CheckboxItem.addProperties({
                    checkedImage:"[SKINIMG]/DynamicForm/checked.png",
                    partialSelectedImage:"[SKINIMG]/DynamicForm/partialcheck.png",
                    showValueIconFocused:false,
                    showValueIconOver:false,
                    uncheckedImage:"[SKINIMG]/DynamicForm/unchecked.png",
                    unsetImage:"[SKINIMG]/DynamicForm/unsetcheck.png",
                    valueIconWidth:13,
                    valueIconHeight:13
                });
            }
            
            if (isc.TextItem) {
                isc.TextItem.addProperties({
                    height:22,
                    showFocused:true
                });
            }

            if (isc.TextAreaItem) {
                isc.TextAreaItem.addProperties({
                    showFocused:true
                });
            }

            if (isc.SelectItem) {
                isc.SelectItem.addProperties({
                    height:22,
                    pickerIconSrc:"[SKIN]/pickers/comboBoxPicker.png",
                    pickerIconWidth:18,
                    showFocusedPickerIcon:false,
                    textBoxStyle:"selectItemText"
                });
            }

            if (isc.ComboBoxItem) {
                isc.ComboBoxItem.addProperties({
                    height:22,
                    pendingTextBoxStyle:"comboBoxItemPendingText",
                    pickerIconSrc:"[SKIN]/pickers/comboBoxPicker.png",
                    pickerIconWidth:18,
                    showFocusedPickerIcon:false,
                    textBoxStyle:"selectItemText"
                });
            }

            // used by SelectItem and ComboBoxItem for picklist
            if (isc.ScrollingMenu) {
                isc.ScrollingMenu.addProperties({
                shadowDepth:5,
                showShadow:false
                });
            }
            
            if (isc.DateItem) {
                isc.DateItem.addProperties({
                    height:22,
                    pickerIconHeight:14,
                    pickerIconSrc:"[SKIN]/DynamicForm/date_control.png",
                    pickerIconWidth:16
                });
            }

            if (isc.SpinnerItem) {
                isc.SpinnerItem.addProperties({
                    height:22,
                    textBoxStyle:"selectItemText"
                });
                
                isc.SpinnerItem.INCREASE_ICON = isc.addProperties(isc.SpinnerItem.INCREASE_ICON, 
                {
                    height:11,
                    imgOnly:true,
                    showDown:false,
                    showFocused:false,
                    showRollOver:false,
                    src:"[SKIN]/DynamicForm/spinner_control_increase.png",
                    width:16
                });
                
                isc.SpinnerItem.DECREASE_ICON = isc.addProperties(isc.SpinnerItem.DECREASE_ICON, 
                {
                    height:11,
                    imgOnly:true,
                    showDown:false,
                    showFocused:false,
                    showRollOver:false,
                    src:"[SKIN]/DynamicForm/spinner_control_decrease.png",
                    width:16
                });
            }
            
            if (isc.PopUpTextAreaItem) {
                isc.PopUpTextAreaItem.addProperties({
                    popUpIconHeight:16,
                    popUpIconSrc:"[SKIN]/DynamicForm/text_control.png",
                    popUpIconWidth:16
                });
            }

            if (isc.ToolbarItem && isc.IAutoFitButton) {
                isc.ToolbarItem.addProperties({
                    buttonConstructor:isc.IAutoFitButton,
                    buttonProperties:{ autoFitDirection:isc.Canvas.BOTH }
                });
            }

            if (isc.DateRangeDialog) {
                isc.DateRangeDialog.changeDefaults("headerIconProperties", { src:"[SKIN]/DynamicForm/date_control.png" });
            }
            
            if (isc.MiniDateRangeItem) {
                isc.MiniDateRangeItem.changeDefaults("pickerIconDefaults", { src:"[SKIN]/DynamicForm/date_control.png" });
            }
            
            if (isc.RelativeDateItem) {
                isc.RelativeDateItem.changeDefaults("pickerIconDefaults", { src:"[SKIN]/DynamicForm/date_control.png" });
            }

            // Native FILE INPUT items are rendered differently in Safari from other browsers
            // Don't show standard textbox styling around them as it looks odd
            if (isc.UploadItem && isc.Browser.isSafari) {
                isc.UploadItem.addProperties({
                    textBoxStyle:"normal"
                });
            }

            if (isc.DateChooser) {
                isc.DateChooser.addProperties({
                    alternateWeekStyles:false,
                    backgroundColor:"#FFFFFF",
                    baseNavButtonStyle:"dateChooserNavButton",
                    baseWeekdayStyle:"dateChooserWeekday",
                    baseWeekendStyle:"dateChooserWeekend",
                    baseBottomButtonStyle:"dateChooserBottomButton",
                    edgeCenterBackgroundColor:"#FFFFFF",
                    headerStyle:"dateChooserButton",
                    nextMonthIcon:"[SKINIMG]/DateChooser/arrow_right.png",
                    nextMonthIconHeight:16,
                    nextMonthIconWidth:16,
                    nextYearIcon:"[SKINIMG]/DateChooser/doubleArrow_right.png",
                    nextYearIconHeight:16,
                    nextYearIconWidth:16,
                    prevMonthIcon:"[SKINIMG]/DateChooser/arrow_left.png",
                    prevMonthIconHeight:16,
                    prevMonthIconWidth:16,
                    prevYearIcon:"[SKINIMG]/DateChooser/doubleArrow_left.png",
                    prevYearIconHeight:16,
                    prevYearIconWidth:16,
                    showDoubleYearIcon:false,
                    showEdges:false,
                    skinImgDir:"images/DateChooser/",
                    todayButtonHeight:20,
                    weekendHeaderStyle:"dateChooserWeekendButton",
                    styleName:"dateChooserBorder"
                });
            }

            if (isc.ToolStrip) {
                isc.ToolStrip.addProperties({
                    defaultLayoutAlign:"center",
                    height:30
                });
                
                isc.ToolStripResizer.addProperties({
                    backgroundColor:"#f6f6f6"
                });

                isc.ToolStrip.changeDefaults("formWrapperDefaults",{cellPadding:3});
            }

            if (isc.ToolStripMenuButton) {
                
                isc.overwriteClass("ToolStripMenuButton", "MenuButton").addProperties({
                    autoFit:true,
                    baseStyle:"toolbarButton",
                    height:22,
                    labelVPad:0,
                    showDown:true,
                    showRollOver:true,
                    showTitle:false
                });
            }

            if (isc.ToolStripButton) {
                
                isc.overwriteClass("ToolStripButton", "Button").addProperties({
                    autoFit:true,
                    baseStyle:"toolbarButton",
                    height:22,
                    labelVPad:0,
                    showTitle:false,
                    showRollOver:true,
                    showDown:true,
                    title:null
                });
            }

            if (isc.RichTextEditor) {
                isc.RichTextEditor.addProperties({
                    showEdges:false,
                    styleName:"richTextEditorBorder"
                });
            }

            if (isc.Slider) {
                isc.Slider.addProperties({
                    hThumbStyle:"hSliderThumb",
                    hTrackStyle:"hSliderTrack",
                    thumbConstructor:"StatefulCanvas",
                    thumbThickWidth:14,
                    thumbThinWidth:14,
                    trackConstructor:"Canvas",
                    trackWidth:5,
                    vThumbStyle:"vSliderThumb",
                    vTrackStyle:"vSliderTrack"
                });
            }


            if (isc.TileGrid) {
                isc.TileGrid.addProperties({
                    showEdges:false,
                    styleName:null,
                    valuesShowRollOver:true
                });
            }

            if (isc.Calendar) {
                isc.Calendar.changeDefaults("datePickerButtonDefaults", {
                    showDown:false,
                    showOver:false,
                    src:"[SKIN]/DynamicForm/date_control.png"
                });

                isc.Calendar.changeDefaults("controlsBarDefaults", {
                    height:10,
                    layoutBottomMargin:10
                });
            }

            if (isc.Hover) {
                isc.addProperties(isc.Hover.hoverCanvasDefaults, {
                    shadowDepth:5,
                    showShadow:false
                });
            }

            //indicate type of media used for various icon types
            isc.pickerImgType = "gif";
            isc.transferImgType = "gif";
            isc.headerImgType = "gif";

            isc.Page.checkBrowserAndRedirect("[SKIN]/unsupported_browser.html");

        } else {

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
                groupBorderCSS:"1px solid #A7ABB4"
            });

            if(isc.Browser.isIE && isc.Browser.version >= 7) {
                isc.Canvas.setAllowExternalFilters(false);
                isc.Canvas.setNeverUseFilters(true);
                
                if(isc.Window) {
                    isc.Window.addProperties({
                        modalMaskOpacity:null,
                        modalMaskStyle:"normal"
                    });
                    
                    isc.Window.changeDefaults("modalMaskDefaults", { src:"[SKIN]opacity.png" });
                }
            }

            if(isc.RPCManager) {
                isc.RPCManager.addClassProperties({ promptStyle:"cursor" });
            }

            //----------------------------------------
            // 1) Scrollbars
            //----------------------------------------
            isc.Canvas.addProperties({
                cornerSize:16,
                scrollbarSize:16,
                showCustomScrollbars:true
            });
            
            isc.ScrollThumb.addProperties({
                backgroundColor:"transparent",
                capSize:2,
                gripBreadth:10,        
                gripLength:10,
                hSrc:"[SKIN]hthumb.png",
                showGrip:true,
                showRollOver:true,
                vSrc:"[SKIN]vthumb.png"
            });
            
            isc.Scrollbar.addProperties({
                backgroundColor:"#FFFFFF",
                btnSize:18,
                hSrc:"[SKIN]hscroll.png",
                showRollOver:true,
                thumbInset:0,
                thumbMinSize:20,
                thumbOverlap:2,
                vSrc:"[SKIN]vscroll.png"
            });

            //----------------------------------------
            // 2) Buttons
            //----------------------------------------

            // "IButton" is the new standard button class for SmartClient applications. Application
            // code should use IButton instead of Button for all standalone buttons. Other skins may
            // map IButton directly to Button, so this single class will work everywhere. Button remains
            // for internal and advanced uses (eg if you need to mix both CSS-based and image-based
            // standalone buttons in the same application).
            isc.defineClass("IButton", "StretchImgButton").addProperties({
                capSize:4,
                height:22,
                showFocused:true,
                showFocusedAsOver:true,
                src:"[SKIN]button/button.png",
                titleStyle:"buttonTitle",
                vertical:false,
                width:100
            });

            isc.defineClass("IAutoFitButton", "IButton").addProperties({
                autoFit:true,
                autoFitDirection:isc.Canvas.HORIZONTAL
            });
            
            if (isc.IButton.markAsFrameworkClass != null) isc.IButton.markAsFrameworkClass();
            if (isc.IAutoFitButton.markAsFrameworkClass != null) isc.IAutoFitButton.markAsFrameworkClass();

            isc.ImgButton.addProperties({
                showFocused:true,
                showFocusedAsOver:true
            });

            isc.defineClass("HeaderImgButton", "ImgButton").addProperties({
                showDown:false,
                showFocused:false,
                showFocusedAsOver:false,
                showRollOver:false
            });

            isc.Button.addProperties({
                height:22,
                showFocused:true,
                showFocusedAsOver:false
            });

            isc.Label.addProperties({
                showFocused:false
            });

            //----------------------------------------
            // 3) Resizebars
            //----------------------------------------
            // StretchImgSplitbar class renders as resize bar with 
            // end caps, body, grip
            isc.StretchImgSplitbar.addProperties({
                capSize:10,
                showGrip:true
            });

            // ImgSplitbar renders as resizebar with resize grip only
            isc.ImgSplitbar.addProperties({
            
            });

            isc.Snapbar.addProperties({
                gripBreadth:3,
                gripLength:20,
                hSrc:"[SKIN]hsplit.png",
                items:[
                    {name:"bg", width:"*", height:"*"}
                ],
                showClosedGrip:false,
                showDown:false,
                showDownGrip:false,
                showRollOver:false,
                vSrc:"[SKIN]vsplit.png"
            });

            isc.Layout.addProperties({
                resizeBarSize:5,
                // Use the Snapbar as a resizeBar by default - subclass of Splitbar that 
                // shows interactive (closed/open) grip images
                // Other options include the Splitbar, StretchImgSplitbar or ImgSplitbar
                resizeBarClass:"Snapbar"
            });


            //----------------------------------------
            // 4) Sections
            //----------------------------------------
            if (isc.SectionItem) {
                isc.SectionItem.addProperties({
                    height:26,
                    sectionHeaderClass:"ImgSectionHeader"
                });
            }
            if (isc.SectionStack) {
                isc.SectionStack.addProperties({
                    backgroundColor:null,
                    headerHeight:26,
                    sectionHeaderClass:"ImgSectionHeader"
                });
                
                isc.ImgSectionHeader.changeDefaults("backgroundDefaults", {
                    baseStyle:"imgSectionHeader",
                    backgroundColor:"transparent",
                    capSize:2,
                    icon:"[SKIN]SectionHeader/opener.png",
                    iconSize:16,
                    showDisabledIcon:false,
                    showDown:false,
                    showRollOver:false,
                    showRollOverIcon:false,
                    src:"[SKIN]SectionHeader/header.png",
                    titleStyle:"imgSectionHeaderTitle"
                });
                
                isc.SectionHeader.addProperties({
                    icon:"[SKIN]SectionHeader/opener.png",
                    iconSize:16
                });
            }

            //----------------------------------------
            // 5) Progressbars
            //----------------------------------------
            if (isc.Progressbar) {
                isc.Progressbar.addProperties({
                    breadth:24,
                    horizontalItems:[
                        {name:"h_start",size:2},
                        {name:"h_stretch",size:0},
                        {name:"h_end",size:2},
                        {name:"h_empty_start",size:2},
                        {name:"h_empty_stretch",size:0},
                        {name:"h_empty_end",size:2}
                    ],
                    length:300,
                    verticalItems:[
                        {name:"v_empty_start",size:2},
                        {name:"v_empty_stretch",size:0},
                        {name:"v_empty_end",size:0},
                        {name:"v_start",size:2},
                        {name:"v_stretch",size:0},
                        {name:"v_end",size:2}
                    ]
                });
            }

            //----------------------------------------
            // 6) TabSets
            //----------------------------------------
            if (isc.TabSet) {
                isc.TabSet.addProperties({
                    closeTabIconSize:12,
                    paneContainerClassName:"tabSetContainer",
                    paneMargin:5,
                    pickerButtonSize:20,
                    pickerButtonSrc:"[SKIN]picker.png",
                    scrollerSrc:"[SKIN]scroll.png",
                    scrollerButtonSize:19,
                    showEdges:false,
                    showScrollerRollOver:false,
                    symmetricPickerButton:false,
                    symmetricScroller:false,
                    tabBarThickness:24
                });
                
                isc.TabSet.changeDefaults("paneContainerDefaults", {
                    showEdges:false
                });
                
                isc.TabBar.addProperties({
                    baseLineConstructor:"Canvas",
                    baseLineProperties:{ 
                        backgroundColor:"#C0C3C7",
                        height:1,
                        overflow:"hidden"
                    },
                    layoutEndMargin:5,
                    layoutStartMargin:5,
                    membersMargin:1,
                    styleName:"tabBar"
                });
            }    
            
            if (isc.ImgTab) {
                isc.ImgTab.addProperties({
                    capSize:6,
                    showDisabled:true,
                    showDisabledIcon:false,
                    showDown:false,
                    showRollOver:true,
                    src:"[SKIN]tab.png",
                    titleStyle:"tabTitle"
                });
            }


            //----------------------------------------
            // 7) Windows
            //----------------------------------------
            if (isc.Window) {
                isc.Window.addProperties({
                    backgroundColor:null,
                    bodyColor:"transparent",
                    bodyStyle:"windowBody",
                    border:null,
                    customEdges:null,
                    edgeBottom:6,
                    edgeCenterBackgroundColor:"#FFFFFF",
                    edgeImage:"[SKINIMG]Window/window.png",
                    edgeOffsetBottom:5,
                    edgeOffsetLeft:5,
                    edgeOffsetRight:5,
                    edgeOffsetTop:2,
                    edgeSize:6,
                    edgeTop:23,
                    minimizeHeight:29,
                    layoutMargin:0,
                    membersMargin:0,
                    shadowDepth:5,
                    showEdges:true,
                    showFooter:false,
                    showHeaderBackground:false, // part of edges
                    showHeaderIcon:true,
                    showShadow:false,
                    styleName:"normal"
                });

                isc.Window.changeDefaults("headerDefaults", {
                    layoutMargin:0,
                    height:20
                });
                
                isc.Window.changeDefaults("resizerDefaults", {
                    src:"[SKIN]/Window/resizer.png"
                });

                isc.Window.changeDefaults("headerIconDefaults", {
                    height:15,
                    src:"[SKIN]/Window/headerIcon.png",
                    width:15
                });
                
                isc.Window.changeDefaults("restoreButtonDefaults", {
                    height:15,
                    showDown:false,
                    showRollOver:true,
                    src:"[SKIN]/headerIcons/cascade.png",
                    width:15
                });
                
                isc.Window.changeDefaults("closeButtonDefaults", { 
                    height:15,
                    showDown:false,
                    showRollOver:true,
                    src:"[SKIN]/headerIcons/close.png",
                    width:15
                });
                
                isc.Window.changeDefaults("maximizeButtonDefaults", { 
                    height:15,
                    showRollOver:true,
                    src:"[SKIN]/headerIcons/maximize.png",
                    width:15
                });
                
                isc.Window.changeDefaults("minimizeButtonDefaults", { 
                    height:15,
                    showDown:false,
                    showRollOver:true,
                    src:"[SKIN]/headerIcons/minimize.png",
                    width:15
                });
                
                isc.Window.changeDefaults("toolbarDefaults", {
                    buttonConstructor:"IButton"
                });

                if (isc.ColorPicker) {
                    isc.ColorPicker.addProperties({
                        layoutMargin:0
                    });
                }

                //----------------------------------------
                // 8) Dialogs
                //----------------------------------------
                if (isc.Dialog) {
                    isc.Dialog.addProperties({
                        backgroundColor:null,
                        bodyColor:"transparent",
                        bodyStyle:"windowBody",
                        border:null,
                        customEdges:null,
                        edgeBottom:6,
                        edgeCenterBackgroundColor:"#FFFFFF",
                        edgeImage:"[SKINIMG]Window/window.png",
                        edgeOffsetBottom:5,
                        edgeOffsetRight:5,
                        edgeOffsetTop:2,
                        edgeSize:6,
                        edgeTop:23,
                        layoutMargin:0,
                        membersMargin:0,
                        shadowDepth:5,
                        showEdges:true,
                        showFooter:false,
                        showHeaderBackground:false, // part of edges
                        showShadow:false,
                        styleName:"normal"
                    });
                    
                    // even though Dialog inherits from Window, we need a separate changeDefaults block
                    // because Dialog defines its own toolbarDefaults
                    isc.Dialog.changeDefaults("toolbarDefaults", {
                        buttonConstructor:"IButton",
                        height:42, // 10px margins + 22px button
                        membersMargin:10
                    });
                    
                    if (isc.Dialog.Warn && isc.Dialog.Warn.toolbarDefaults) {
                        isc.addProperties(isc.Dialog.Warn.toolbarDefaults, {
                            buttonConstructor:"IButton",
                            height:42,
                            membersMargin:10
                        });
                    }
                }
            } // end isc.Window

            //----------------------------------------
            // 9) Pickers
            //----------------------------------------
            // add bevels and shadows to all pickers
            isc.__pickerDefaults = {
                backgroundColor:"#FFFFFF",
                edgeImage:"[SKINIMG]Window/window.png",
                edgeSize:6,
                shadowDepth:6,
                shadowOffset:5,
                showEdges:true,
                showShadow:false
            }
            
            if (isc.ButtonTable) {
                isc.ButtonTable.addProperties({
                    backgroundColor:"#FFFFFF"
                });
            }
            
            if (isc.FormItem) {
                isc.FormItem.changeDefaults("pickerDefaults", isc.__pickerDefaults);
                
                isc.FormItem.addProperties({
                    defaultIconSrc:"[SKIN]/DynamicForm/default_formItem_icon.png"
                });
            }
            
            if (isc.CheckboxItem) {
                isc.CheckboxItem.addProperties({
                    checkedImage:"[SKINIMG]/DynamicForm/checked.png",
                    partialSelectedImage:"[SKINIMG]/DynamicForm/partialcheck.png",
                    showValueIconFocused:false,
                    showValueIconOver:false,
                    uncheckedImage:"[SKINIMG]/DynamicForm/unchecked.png",
                    unsetImage:"[SKINIMG]/DynamicForm/unsetcheck.png",
                    valueIconHeight:13,
                    valueIconWidth:13
                });
            }
            
            if(isc.RelationItem) {
                isc.RelationItem.changeDefaults("removeButtonDefaults", {
                    src:"[SKIN]DynamicForm/Remove_icon.png"
                });
            }

            if (isc.DateChooser) {
                isc.DateChooser.addProperties({
                    alternateWeekStyles:false,
                    backgroundColor:null,
                    baseBottomButtonStyle:"dateChooserBottomButton",
                    baseNavButtonStyle:"dateChooserNavButton",
                    baseWeekdayStyle:"dateChooserWeekday",
                    baseWeekendStyle:"dateChooserWeekend",
                    edgeBottom:5,
                    edgeCenterBackgroundColor:"#FFFFFF",
                    edgeImage:"[SKINIMG]Window/window.png",
                    edgeOffsetTop:1,
                    edgeOffsetRight:5,
                    edgeOffsetLeft:5,
                    edgeOffsetBottom:5,
                    edgeSize:6,
                    edgeTop:26,
                    headerHeight:24,
                    headerStyle:"dateChooserButton",
                    nextMonthIcon:"[SKIN]arrow_right.png",
                    nextMonthIconHeight:16,
                    nextMonthIconWidth:16,
                    nextYearIcon:"[SKIN]doubleArrow_right.png",
                    nextYearIconHeight:16,
                    nextYearIconWidth:16,    
                    prevMonthIcon:"[SKIN]arrow_left.png",
                    prevMonthIconHeight:16,
                    prevMonthIconWidth:16,
                    prevYearIcon:"[SKIN]doubleArrow_left.png",
                    prevYearIconHeight:16,
                    prevYearIconWidth:16,
                    shadowDepth:6,
                    shadowOffset:5,
                    showDoubleYearIcon:false,
                    showEdges:true,
                    showShadow:false,
                    skinImgDir:"images/DateChooser/",
                    todayButtonHeight:20,
                    weekendHeaderStyle:"dateChooserWeekendButton"
                });
            }
            
            if (isc.MultiFilePicker) {
                isc.MultiFilePicker.addProperties({
                    backgroundColor:"#C7C7C7"
                });
            }
            
            if (isc.RelationPicker) {
                isc.RelationPicker.addProperties({
                    backgroundColor:"#C7C7C7"  
                });
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
                    bodyStyleName:"gridBody",
                    bodyBackgroundColor:null,
                    cellHeight:22,
                    checkmarkDisabledImage:{src:"[SKIN]check_disabled.png", width:7, height:6},
                    checkmarkImage:{src:"[SKIN]check.png", width:9, height:8},
                    fastCellUpdates:false,
                    iconBodyStyleName:"menuMain",
                    shadowDepth:5,
                    showEdges:false,
                    showShadow:false,
                    submenuDisabledImage:{src:"[SKIN]submenu_disabled.png", height:7, width:4},
                    submenuImage:{src:"[SKIN]submenu.png", height:7, width:4}
                });
                
                isc.addProperties(isc.Menu.ICON_FIELD, {
                    baseStyle:"menuIconField",
                    width:24
                });
                
                isc.Menu.TITLE_FIELD.baseStyle = "menuTitleField";
            }

            if (isc.MenuButton) {
                isc.MenuButton.addProperties({
                    baseStyle:"menuButton",
                    iconHeight:4,
                    iconWidth:7,
                    menuButtonImage:"[SKIN]menu_button.png",
                    menuButtonImageUp:"[SKIN]menu_button_up.png",
                    showFocusedAsOver:true
                });
            }
            
            if (isc.IMenuButton) {
                isc.IMenuButton.addProperties({
                    capSize:4,
                    height:22,
                    iconHeight:4,
                    iconWidth:7,
                    menuButtonImage:"[SKIN]menu_button.png",
                    menuButtonImageUp:"[SKIN]menu_button_up.png",
                    showFocused:true,
                    showFocusedAsOver:true,
                    src:"[SKIN]button/button.png",
                    titleStyle:"buttonTitle",
                    vertical:false,
                    width:100
                });
            }

            if (isc.SelectionTreeMenu) {
                isc.SelectionTreeMenu.addProperties({
                    bodyBackgroundColor:null,
                    bodyStyleName:"treeMenuBody",
                    showIcons:false,
                    showKeys:false
                });
            }

            //----------------------------------------
            // 11) Hovers
            //----------------------------------------
            if (isc.Hover) {
                isc.addProperties(isc.Hover.hoverCanvasDefaults, {
                    shadowDepth:5,
                    showShadow:false
                });
            }

            //----------------------------------------
            // 12) ListGrids
            //----------------------------------------
            if (isc.ListGrid) {										  
                isc.ListGrid.addProperties({
                    alternateBodyStyleName:null,
                    alternateRecordStyles:true,
                    backgroundColor:null, 
                    bodyBackgroundColor:null,
                    bodyStyleName:"gridBody",
                    cellHeight:22,
                    checkboxFieldImageHeight:13,
                    checkboxFieldImageWidth:13,
                    editFailedCSSText:"color:FF6347;",
                    errorIconSrc:"[SKINIMG]actions/exclamation.png",
                    expansionFieldTrueImage:"[SKINIMG]/ListGrid/row_expanded.png",
                    expansionFieldFalseImage:"[SKINIMG]/ListGrid/row_collapsed.png",
                    groupIcon:"[SKINIMG]/ListGrid/group.png",
                    groupIconPadding:3,            
                    groupLeadingIndent:1,
                    headerBackgroundColor:null,
                    headerBarStyle:"headerBar",
                    headerBaseStyle:"headerButton",	// bgcolor tint and borders
                    headerButtonConstructor:"ImgButton",
                    headerHeight:23,
                    headerMenuButtonConstructor:"HeaderImgButton",
                    headerMenuButtonIcon:"[SKINIMG]/ListGrid/sort_descending.png",
                    headerMenuButtonIconWidth: 9,
                    headerMenuButtonIconHeight: 6,
                    headerMenuButtonSrc:"[SKIN]/ListGrid/header_menu.png",
                    headerMenuButtonWidth:17,
                    headerTitleStyle:"headerTitle",
                    normalCellHeight:22,
                    sortAscendingImage:{src:"[SKIN]sort_ascending.png", width:9, height:6},
                    sortDescendingImage:{src:"[SKIN]sort_descending.png", width:9, height:6},
                    sorterConstructor:"ImgButton",
                    showHeaderMenuButton:true,
                    summaryRowHeight:21,
                    tallBaseStyle:"tallCell"
                });
                
                isc.ListGrid.changeDefaults("sorterDefaults", { 
                    baseStyle:"sorterButton",
                    showFocused:false,
                    src:"[SKIN]ListGrid/header.png"
                });
                
                isc.ListGrid.changeDefaults("headerButtonDefaults", {
                    showDown:false,
                    showFocused:false,
                    showTitle:true,
                    src:"[SKIN]ListGrid/header.png"
                });
                
                isc.ListGrid.changeDefaults("headerMenuButtonDefaults", {
                    showDown:false,
                    showTitle:true,
                    src:"[SKIN]ListGrid/header.png"
                });
            }

            if (isc.TreeGrid) {
                isc.TreeGrid.addProperties({
                    alternateRecordStyles:false,
                    normalBaseStyle:"treeCell",
                    openerImage:"[SKIN]opener.png",
                    sortAscendingImage:{src:"[SKINIMG]ListGrid/sort_ascending.png", width:9, height:6},
                    sortDescendingImage:{src:"[SKINIMG]ListGrid/sort_descending.png", width:9, height:6},            
                    tallBaseStyle:"treeTallCell"
                });
            }

            if(isc.MultiSortPanel) {
                isc.MultiSortPanel.changeDefaults("levelUpButtonDefaults", {
                    height:22,
                    src:"[SKINIMG]TransferIcons/up.png",
                    width:24
                });
                isc.MultiSortPanel.changeDefaults("levelDownButtonDefaults", {
                    height:22,
                    src:"[SKINIMG]TransferIcons/down.png",
                    width:24
                });
            }
            
            //----------------------------------------
            // 13) TreeGrids
            //----------------------------------------
            if (isc.TreeGrid) {
                isc.TreeGrid.addProperties({
                    folderIcon:"[SKIN]folder.png",
                    manyItemsImage:"[SKIN]folder_file.png",
                    nodeIcon:"[SKIN]file.png",
                    openerIconSize:22
                });
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
            if (isc.FormItem) {
                isc.FormItem.addProperties({
                    defaultIconSrc:"[SKIN]/DynamicForm/default_formItem_icon.png",
                    errorIconSrc:"[SKINIMG]actions/exclamation.png",
                    iconHeight:18,
                    iconVAlign:"middle",
                    iconWidth:18
                });
            }

            if (isc.PickTreeItem) {
                isc.PickTreeItem.addProperties({
                    buttonDefaults:{ height:21 }
                });
            }

            if (isc.TextItem) {
                isc.TextItem.addProperties({
                    height:22,
                    showFocused:true
                });
            }

            if (isc.TextAreaItem) {
                isc.TextAreaItem.addProperties({
                    showFocused:true     
                });
            }

            if (isc.SelectItem) {
                isc.SelectItem.addProperties({   
                    height:22,
                    pickerIconSrc:"[SKIN]/pickers/comboBoxPicker.png",
                    pickerIconWidth:18,
                    pickListTallBaseStyle:"tallPickListCell",
                    showFocusedPickerIcon:false,
                    textBoxStyle:"selectItemText"
                });
            }

            if (isc.ComboBoxItem) {
                isc.ComboBoxItem.addProperties({
                    height:22,
                    pendingTextBoxStyle:"comboBoxItemPendingText",
                    pickerIconSrc:"[SKIN]/pickers/comboBoxPicker.png",
                    pickerIconWidth:18,
                    pickListTallBaseStyle:"tallPickListCell",
                    showFocusedPickerIcon:false,
                    textBoxStyle:"selectItemText"
                });
            }

            // used by SelectItem and ComboBoxItem for picklist
            if (isc.ScrollingMenu) {
                isc.ScrollingMenu.addProperties({
                    shadowDepth:5,
                    showShadow:false
                });  
            }
            
            if (isc.DateItem) {
                isc.DateItem.addProperties({
                    height:22,
                    pickerIconHeight:14,
                    pickerIconSrc:"[SKIN]/DynamicForm/date_control.png",
                    pickerIconWidth:16
                });
            }
            
            if (isc.SpinnerItem) {
                isc.SpinnerItem.addProperties({
                    height:22,
                    textBoxStyle:"selectItemText"
                });
                
                isc.SpinnerItem.INCREASE_ICON = isc.addProperties(isc.SpinnerItem.INCREASE_ICON, {
                    height:11,
                    imgOnly:true,
                    showDown:true,
                    showFocused:true,
                    showRollOver:true,
                    src:"[SKIN]/DynamicForm/spinner_control_increase.png",
                    width:16
                });
                
                isc.SpinnerItem.DECREASE_ICON = isc.addProperties(isc.SpinnerItem.DECREASE_ICON, {
                    height:11,
                    imgOnly:true,
                    showDown:true,
                    showFocused:true,
                    showRollOver:true,
                    src:"[SKIN]/DynamicForm/spinner_control_decrease.png",
                    width:16
                });
            }

            if (isc.PopUpTextAreaItem) {
                isc.PopUpTextAreaItem.addProperties({
                    popUpIconHeight:16,
                    popUpIconSrc:"[SKIN]/DynamicForm/text_control.gif",
                    popUpIconWidth:16
                });
            }
            
            if (isc.ButtonItem && isc.IButton) {
                isc.ButtonItem.addProperties({
                    buttonConstructor:isc.IButton,
                    height:22,
                    showFocusAsOver:false,
                    showFocused:true
                });
            }

            if (isc.ToolbarItem && isc.IAutoFitButton) {
                isc.ToolbarItem.addProperties({
                    buttonConstructor:isc.IAutoFitButton,
                    buttonProperties:{ autoFitDirection:isc.Canvas.BOTH }
                });
            }

            if(isc.DateRangeDialog) {
                isc.DateRangeDialog.changeDefaults("headerIconProperties", { src:"[SKIN]/DynamicForm/date_control.png" });
            }
            
            if(isc.MiniDateRangeItem) {
                isc.MiniDateRangeItem.changeDefaults("pickerIconDefaults", { src:"[SKIN]/DynamicForm/date_control.png" });
            }
            
            if(isc.RelativeDateItem) {
                isc.RelativeDateItem.changeDefaults("pickerIconDefaults", { src:"[SKIN]/DynamicForm/date_control.png" });
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
                    edgeImage:"[SKINIMG]edges/edge.png",
                    edgeSize:6
                });
            }

            //----------------------------------------
            // 17) Sliders
            //----------------------------------------
            if (isc.Slider) {
                isc.Slider.addProperties({
                    thumbSrc:"thumb.png",
                    thumbThickWidth:14,
                    thumbThinWidth:14,
                    trackCapSize:2,
                    trackSrc:"track.png",
                    trackWidth:5
                });
            }

            //----------------------------------------
            // 18) TileList
            //----------------------------------------
            if (isc.TileGrid) {
                isc.TileGrid.addProperties({
                    showEdges:true,
                    styleName:null,
                    valuesShowRollOver:true
                });
            }

            // ----------------------------------------
            // 19) CubeGrid
            //----------------------------------------
            if (isc.CubeGrid) {
                isc.CubeGrid.addProperties({
                    alternateBodyStyleName:"alternateCubeGridBody",
                    bodyStyleName:"cubeGridBody"
                });
            }

            // ----------------------------------------
            // 20) FilterBuilder
            //----------------------------------------
            if (isc.FilterBuilder) {
                isc.FilterBuilder.changeDefaults("addButtonDefaults", { showFocused:false });
                isc.FilterBuilder.changeDefaults("removeButtonDefaults", { showFocused:false });
            }

            // -------------------------------------------
            // 21) Printing
            // -------------------------------------------
            if (isc.PrintWindow) {
                isc.PrintWindow.changeDefaults("printButtonDefaults", { height:18 });
            }

            // -------------------------------------------
            // 21) Printing
            // -------------------------------------------
            if (isc.Calendar) {
                isc.Calendar.changeDefaults("datePickerButtonDefaults", {
                    showDown:false,
                    showOver:false,
                    src:"[SKIN]/DynamicForm/date_control.png"
                });

                isc.Calendar.changeDefaults("controlsBarDefaults", {
                    height:10,
                    layoutBottomMargin:10
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
                    defaultLayoutAlign:"center",
                    height:30,
                    verticalStyleName:"toolStripVertical"
                });

                isc.ToolStrip.changeDefaults("formWrapperDefaults", {cellPadding:3} );
            }

            // -------------------------------------------
            // 23) RichTextEditor
            // -------------------------------------------
            if (isc.RichTextEditor) {
                isc.RichTextEditor.addProperties({
                    showEdges:true
                });
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

        } //end useCSS3 else block

    }   // end with()
}   // end loadSkin()

isc.loadSkin()
