$(function () {
                            var dialog;

                            // a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
                            $("#dialog:ui-dialog").dialog("destroy");



                            $("#uitleen" + index).hide();

                            dialog = $("#uitleen" + index).dialog({
                                autoOpen: false,
                                height: 400,
                                width: 350,
                                modal: true,
                                closeOnEscape: true,
                                buttons: {
                                    "Uitlenen": function () {
                                        
                                        $("#doen" + index).submit();
                                    }

                                },
                                close: function () {
                                    dialog.dialog("close");

                                }
                            });




                            $("#uitlenen" + index).on("click", function (event) {
                                event.preventDefault();
                                dialog.dialog("open");
                            });
                        }
                        );