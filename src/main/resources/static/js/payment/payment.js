function iamport(){
    //가맹점 식별코드
    IMP.init("imp17552170");
    IMP.request_pay({
        pg : 'kcp',
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
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