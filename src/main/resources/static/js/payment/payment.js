function getPayment(){
    //가맹점 식별코드
    IMP.init("imp17552170");
    const memberNm = $("#memberNm").val();
    const address = $("#address").val() + $("#detailAddress").val();
    const telNo = $("#telNo").val();
    const email = $("#email").val();
    const totalPrice = $("#totalPrice").val();

    IMP.request_pay({
        pg : 'html5_inicis',
        pay_method : 'card',
        merchant_uid: 'merchant_' + new Date().getTime(), // 상점에서 관리하는 주문 번호를 전달
        name : '주문명:결제테스트',
        amount : totalPrice,
        buyer_email : email,
        buyer_name : memberNm,
        buyer_tel : telNo,
        buyer_addr : address,
        buyer_postcode : '123-456'
    }, function(rsp) { // callback 로직
    	 if ( rsp.success ) {
            var msg = '결제가 완료되었습니다.';
        } else {
            var msg = '결제에 실패하였습니다.';
        }
        alert(msg);
    });
}