//jqgrid高度自适应
function autoHeight(){
    var height = $(window).height();
    var height_old = 750;
    if(height > height_old){
        height = height - 128;
        $(".ui-jqgrid-bdiv").height(height);
    }else {return false;}
}
$(function(){
    autoHeight();
    $(window).resize(autoHeight());
});