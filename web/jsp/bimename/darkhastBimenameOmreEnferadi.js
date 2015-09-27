$(function() {
    $(".tabsbtn").each(function() {
        $(this).click(function() {
            changeMenu(this.id.split('_')[1]);
        });
    });

    $(".jtable th").each(function() {
        $(this).addClass("ui-state-default");
    });
    $(".jtable td").each(function() {
        $(this).addClass("ui-widget-content");
    });
    $(".jtable tr").hover(function() {
//        $(this).children("td").addClass("ui-state-hover");
    }, function() {
//        $(this).children("td").removeClass("ui-state-hover");
    });
//    $(".jtable tr").click(function() {
//        $(this).children("td").toggleClass("ui-state-highlight");
//    });

    $("input, textarea").each(function() {
        $(this).addClass("ui-state-default  ui-corner-all");
        $(this).change();
        $(this).bind('change',function(){
            $('.notSavedStar').text('*');
        })
    });

    $(".help, .comment").tipsy({gravity:'s'});
    $(".help").addClass("ui-icon ui-icon-help");
    $(".comment").addClass("ui-icon ui-icon-comment");

    $("select").each(function() {
        $(this).addClass("ui-state-default  ui-corner-all");
        $(this).change();
        $(this).bind('change',function(){
            $('#notSavedStar').text('*');
        })
        //	$(this).children("input").addClass("{button:{icons:{primary:'ui-icon-triangle-1-s'},text:false}} ui-button ui-widget ui-state-default ui-corner-all ui-button-icons-only");
    });

});

function changeMenu(menuId){
    $.validationEngine.closePrompt('.formError', true);
    $('.content').hide();
    $('#content_' + menuId).show();
    $('.tabsbtn').removeClass('activesubmit');
    $('#tab_' + menuId).addClass('activesubmit');
}
function activate(id) {
    $('#' + id).removeClass('ui-state-disabled');
    $('#' + id).removeAttr("disabled");
}

function deactivate(id) {
    $('#' + id).addClass('ui-state-disabled');
    $('#' + id).attr("disabled", true);
}

function activateArray(ids) {
    for (var idsCounter = 0; idsCounter < ids.length; idsCounter++) {
        $('#' + ids[idsCounter]).removeClass('ui-state-disabled');
        $('#' + ids[idsCounter]).removeAttr("disabled");
    }
}

function deactivateArray(ids) {
    for (var idsCounter = 0; idsCounter < ids.length; idsCounter++) {
        $('#' + ids[idsCounter]).addClass('ui-state-disabled');
        $('#' + ids[idsCounter]).attr("disabled", true);
    }
}
function and(ids) {
    var output = true;
    for (var idsCounter = 0; idsCounter < ids.length; idsCounter++) {
        if ($('#' + ids[idsCounter]).val() == 'kheir')
            output = false;
    }
    return output;
}
function sMainForm() {
if (validatePishnehadForm()) {
            $("#mainForm").submit();
    }
}
