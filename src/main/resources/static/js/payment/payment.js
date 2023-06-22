function getPayment(){
    //가맹점 식별코드
    IMP.init("imp17552170");
    const totalPrice = $("#totalPrice").val();
    IMP.request_pay({
        pg : 'kakao', // pg사
        pay_method : 'kakaopay', 결제수단
        merchant_uid : 'merchant_' + new Date().getTime(),
        amount: totalPrice
    }, function(res) {

        // 결제검증
        $.ajax({
            type : "POST",
            url : "/verifyIamport/" + res.imp_uid
        }).done(function(data) {

            if(res.paid_amount == data.response.amount){
                alert("결제 및 결제검증완료");

                //결제 성공 시 비즈니스 로직

            } else {
                alert("결제 실패");
            }
        });
    });
}