function getPayment(){
    //가맹점 식별코드
    IMP.init("imp17552170");
    const memberNm = $("#memberNm").val();
    const address = $("#address").val() + " " + $("#detailAddress").val();
    const telNo = $("#telNo").val();
    const email = $("#email").val();
    const totalPrice = $("#totalPrice").val();
    const cartElements = document.getElementsByName("cart-seq");
    const cartSeqList = [];
    for (const input of cartElements) {
        cartSeqList.push(input.value);
    }
     console.log("문승주1");
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
        console.log("문승주2");
    	 if ( rsp.success ) {
    	    console.log("문승주3");
    	    var msg = '결제가 완료되었습니다.';
    	    console.log("문승주4");
    	    $.ajax({
                type: "POST",
                url: "/payment/saveOrderInfo",
                data:{
                    totalPrice : totalPrice,
                    payType : 'CARD',
                    address : address,
                    cartSeqList : cartSeqList
                },
                dataType: "json",
                success: function(){
                }
            });

        } else {
            var msg = '결제에 실패하였습니다.';
        }
        alert(msg);
    });
}