(function(factory)
{
    /* global define */
    if (typeof define === 'function' && define.amd)
    {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    }
    else if (typeof module === 'object' && module.exports)
    {
        // Node/CommonJS
        module.exports = factory(require('jquery'));
    }
    else
    {
        // Browser globals
        factory(window.jQuery);
    }
}(function($)
{

    // Extends plugins for adding gallery.
    //  - plugin is external module for customizing.
    $.extend($.summernote.plugins,
    {
        /**
         * @param {Object} context - context object has status of editor.
         */
        'gallery': function(context)
        {
            var self = this;

            // ui has renders to build ui elements.
            //  - you can create a button with `ui.button`
            var ui = $.summernote.ui;

            // add gallery button
            context.memo('button.gallery', function()
            {
                // create button
                var button = ui.button(
                {
                    contents: '<i class="fa fa-file-image-o"></i>',
                    tooltip: 'gallery',
                    click: function()
                    {
                        log('Image button clicked');
                        loadImages();
                        //self.fillModal();
                        //self.$modal.modal();
                    }
                });

                // create jQuery object from button instance.
                $gallery = button.render();
                return $gallery;
            });
            
            function loadImages() {

	            $.ajax({
			    	    type:"get",
			    	    crossDomain: true,
			    	    cache: false,
			    	    url: self.image_dialog_images_url,
			    	    dataType: "json",
			    	    headers: {
			    	        "Authorization": "Bearer " + TOKEN
			    	    },
			    	    success: function(data){
			    	       hideLoader();
			    	       var template = self.template_html;
			    	       /**
			    	       image formatting
			    	       **/
			    	       //urlData = urlData + '<div class="col-md-2 img-item"><img class="col-md-12 thumbnail" src="' + 'http://localhost:8080' + contentArray[i].path + contentArray[i].name + '" alt="' + contentArray[i].name +'" /><i class=""></i></div>';
			    	       //urlData = urlData + '<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12"><div class="img-selector img-item"><img src="' + 'http://localhost:8080' + contentArray[i].path + contentArray[i].name + '" alt="' + contentArray[i].name +'" /></div></div>';
			    	       var urlData = '<div class="row">';
			    	       var contentArray = data.content;
			    	       for (var i=0; i < contentArray.length; i++) {
		    	    	     urlData = urlData + '<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12 img-item"><a href="#"><div class="img-selector"><img src="' + 'http://localhost:8080' + contentArray[i].path + contentArray[i].name + '" alt="' + contentArray[i].name +'" width="120" /></div></a></div>';
		    	    	   	//urlData = urlData + '<div class="col-md-2 img-item"><img class="col-md-12 thumbnail" src="' + self.image_dialog_images_url + contentArray[i].path + contentArray[i].name + '" alt="' + contentArray[i].name +'" /><i class=""></i></div>';
		    	    	   }
		    	    	   urlData = urlData + '<div class="row">';
                           self.setModalHtml(urlData);
                           self.$modal.modal();
                           self.$modal.find('.img-selector').click(function(event) {
                              log('Image clicked');
                              $(this).toggleClass(self.select_class);
                           });
			    	    },
			    	    error: function(response,textStatus,errorThrown) {
			    	    	log('Error');
			    	    	hideLoader();
			    	    }
			    });	
            }

            // This events will be attached when editor is initialized.
            this.events = {
                // This will be called after modules are initialized.
                'summernote.init': function(we, e)
                {
                    log('init event');
                    self.editable = context.layoutInfo.editable; //contentEditable element
                    self.editor = this;
                    // get summernote onInit set parameters
                    self.image_dialog_images_url = $(this).data('image_dialog_images_url');
                    self.image_dialog_images_html = $(this).data('image_dialog_images_html');
                    self.image_dialog_title = $(this).data('image_dialog_title');
                    self.image_dialog_close_btn_text = $(this).data('image_dialog_close_btn_text');
                    self.image_dialog_ok_btn_text = $(this).data('image_dialog_ok_btn_text');

                    self.setModalHtml = function(html)
                    {   // set variable parts to modal html
                        var title = self.image_dialog_title;
                        var close = self.image_dialog_close_btn_text;
                        var ok = self.image_dialog_ok_btn_text;

                        if (self.image_dialog_title !== "undefined") self.$modal.find('.modal-title').html(title);
                        if (self.image_dialog_close_btn_text !== "undefined") self.$modal.find('#close').html(close);
                        if (self.image_dialog_ok_btn_text !== "undefined") self.$modal.find('#save').html(ok);

                        self.$modal.find('.modal-body').html(html);
                    }
                    self.getImagesFromUrl = function(callback)
                    {
                        // get images html from url
                        $.get(self.image_dialog_images_url, function(html)
                        {
                            log('Loading images from url');
                            self.setModalHtml(html)
                            callback();

                        }).fail(function()
                        {
                            console.error("error loading from "+self.image_dialog_images_url);
                        })
                    }
                    // set the focus to the last focused element in the editor
                    self.recoverEditorFocus = function ()
                    {
                        var last_focused_el = $(self.editor).data('last_focused_element');
                        if(typeof last_focused_el !== "undefined")
                        {
                            var editor = self.editable;
                            var range = document.createRange();
                            var sel = window.getSelection();
                            var cursor_position =  last_focused_el.length;

                            range.setStart(last_focused_el, cursor_position);
                            range.collapse(true);
                            sel.removeAllRanges();
                            sel.addRange(range);
                            editor.focus();
                        }
                    }

                    self.saveLastFocusedElement = function()
                    {
                        var focused_element = window.getSelection().focusNode;
                        var parent = $(self.editable).get(0);
                        if ($.contains(parent, focused_element))
                        {
                            $(self.editor).data('last_focused_element', focused_element)
                        };
                    }

                    self.editorEvents = function () {
                        $(self.editable).on('keypress, mousemove', function()
                        {
                            self.saveLastFocusedElement();
                        })
                    }
                    self.editorEvents();
                    //self.fillModal();
                },
                // This will be called when user releases a key on editable.
                'summernote.keyup': function(we, e)
                {
                    self.saveLastFocusedElement();
                }
            };

            self.modal_html = '<div class="modal fade" tabindex="-1" role="dialog">'
                                + '<div class="modal-lg modal-dialog ">'
                                    + '<div class="modal-content">'
                                        + '<div class="modal-header">'
                                            + '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
                                            + '<h4 class="modal-title">Image gallery</h4>'
                                        + '</div>'
                                        + '<div class="modal-body">'
                                            + '<p class="text-danger" >no image was set. open the browser console to see if there is any errors messages. if not dig into source file to see what\'s wrong.</p>'
                                            + '<small class="text-muted">Or open an issue on github</small>'
                                        + '</div>'
                                        + '<div class="modal-footer">'
                                            + '<button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>'
                                            + '<button type="button" id="save" class="btn btn-primary">Add</button>'
                                        + '</div>'
                                    + '</div>'
                                + '</div>'
                            + '</div>';
            // This method will be called when editor is initialized by $('..').summernote();
            // You can create elements for plugin
            this.initialize = function()
            {
                log('initialized');
                var $modal = this.$modal = $(self.modal_html).hide();
                // add selected images to summernote editor
                $modal.find("button#save").click(function(event)
                {
                    
                    var $selected_img = $modal.find('.selected-img >:first-child');
                    //var $selected_img = $modal.find('.img-item img.' + self.select_class);

                    $modal.modal('hide')

                    self.recoverEditorFocus();

                    $selected_img.each(function(index, el)
                    {
                        context.invoke('editor.pasteHTML',
                            '<img src="' + $(this).attr('src') + '" alt="' + ($(this).attr('alt') || "") + '" width="200" />');
                        $(this).removeClass(self.select_class)
                    });
                });
                // class to add to images when selected
                this.select_class = "selected-img";
                // style to add to selected image
                this.$css = $('<style>'
                                +'.img-item{'
                                    +'position : relative;'
                                +'}'
                                +'.img-item .fa-check{'
                                    +'position : absolute;'
                                    +'top : -10px;'
                                    +'right : 5px;'
                                    +'font-size: 30px;'
                                    +'color: #337AB7;'
                                +'}'
                                +'.img-item .fa-check{'
                                    +'display : none;'
                                +'}'
                                +'.img-item .'+ this.select_class +' + .fa-check{'
                                    +'display : block;'
                                +'}'
                                +'.'+ this.select_class +'{'
                                    +'background-color: #F5F5F5;'
                                +'}'
                            +'</style>');
                this.$css.appendTo('body');
            };

            // This methods will be called when editor is destroyed by $('..').summernote('destroy');
            // You should remove elements on `initialize`.
            this.destroy = function()
            {
                // this.$panel.remove();
                // this.$panel = null;
            };
        }
    });
}));

