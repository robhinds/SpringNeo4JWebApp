$('body').ready(function(){
  $('.clearText').each( function () {
    $(this).val($(this).attr('defaultVal'));
    $(this).css({color:'grey'});
      });

  $('.clearText').focus(function(){
    if ( $(this).val() == $(this).attr('defaultVal') ){
      $(this).val('');
      $(this).css({color:'black'});
    }
    });
  $('.clearText').blur(function(){
    if ( $(this).val() == '' ){
      $(this).val($(this).attr('defaultVal'));
      $(this).css({color:'grey'});
    }
    });
});