$(document).ready(function () {
    let duration = 300;
    let $sidebar = $('.sidebar');
    let $sidebarButton = $sidebar.find('.sidebar-btn').on('click', function(){
        $sidebar.toggleClass('close');
        if($sidebar.hasClass('close')){
            $sidebar.stop(true).animate({left: '-270px'}, duration, 'easeInBack');
            $sidebar.fadeIn();
            $sidebarButton.find('span').text('>>');
        }else{
            $sidebar.stop(true).animate({left: '0px'}, duration, 'easeOutBack');
            $sidebarButton.find('span').text('<<');
        };
    });
})