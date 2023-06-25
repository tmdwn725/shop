function increaseQuantity(element, cartSeq){
    const input = $(element).prev();
    const value = parseInt(input.val()) + 1;
    const total = $("#price"+cartSeq).val() * value;
    const totalPrice = parseInt($("#totalPrice").val()) + parseInt($("#price"+cartSeq).val());
    const formattedTotal = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(total);
    $('#total'+cartSeq).text(formattedTotal);
    $("#totalPrice").val(totalPrice);
    const totalText = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(totalPrice);
    $("#totalSubText").text(totalText);
    $("#totalText").text(totalText);

    // 상품수변경
    creaseProductQuantity(cartSeq, value);
}

function decreaseQuantity(element,cartSeq){
    const input = $(element).next();
    let value = parseInt(input.val());
    if (value > 1) {
        value = value - 1;
    }else{
       return;
    }
    const total = $("#price"+cartSeq).val() * value;
    const totalPrice = parseInt($("#totalPrice").val()) - parseInt($("#price"+cartSeq).val());
    const formattedTotal =new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(total);
    $('#total'+cartSeq).text(formattedTotal);
    $("#totalPrice").val(totalPrice);

    const totalText = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(totalPrice);
    $("#totalSubText").text(totalText);
    $("#totalText").text(totalText);

    // 상품수변경
    creaseProductQuantity(cartSeq, value);
}

function removeCartInfo(cartSeq){

}

function creaseProductQuantity(cartSeq, quantity){
    $.ajax({
        type: "POST",
        url: "/modProductQuantity",
        data:{
            cartSeq : cartSeq,
            quantity : quantity
        },
        dataType: "json",
        success: function(res){
            alert("변경되었습니다.");
        }
    });
}

function getPaymentInfo(){
    // form 요소 생성
    document.getElementById("myCart").submit();
}