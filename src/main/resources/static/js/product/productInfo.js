// jQuery를 사용하여 페이지가 로드되면 실행될 함수
$(document).ready(function () {
    // 페이징 링크를 클릭할 때 이벤트 핸들러 등록
    $('.pagination__option a').on('click', function (e) {
        e.preventDefault(); // 기본 동작 막기 (페이지 새로고침 방지)

        // 선택한 페이지의 URL 가져오기
        var pageUrl = $(this).attr('href');

        // tabs-3 div를 비우거나 로딩 중 표시할 수 있습니다.
        $('#tabs-3').empty().html('<p>Loading...</p>');

        // Ajax 요청 보내기
        $.ajax({
            url: pageUrl, // 선택한 페이지의 URL
            type: 'GET', // GET 요청
            dataType: 'html', // HTML 응답을 기대
            success: function (data) {
                // 성공적으로 데이터를 받았을 때
                $('#tabs-3').html(data); // tabs-3 div를 업데이트
            },
            error: function () {
                // 오류 발생 시 처리
                $('#tabs-3').html('<p>Failed to load content.</p>');
            }
        });
    });
});

function addCart(){
    const quantity = $("#quantity").val();
    const productStockSeq = $('input[name=size]:checked').val();
    if(typeof productStockSeq == "undefined"){
        alert("사이즈를 선택해주세요");
        return;
    }
    $.ajax({
        type: "GET",
        url: "/product/addProductCart",
        data:{
            quantity : quantity,
            productStockSeq : productStockSeq
        },
        dataType: "json",
        success: function(){
        }
    });
}