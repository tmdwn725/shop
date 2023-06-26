/**
상품수 증가
*/
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

/**
상품수 감소
*/
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

/**
장바구니 삭제
*/
function removeCartInfo(cartSeq){

    $.ajax({
        url: "/cart/removeCartInfo",
        type: "DELETE",
        data:{ cartSeq : cartSeq },
        success: function(response) {
          // 성공한 경우 해당 페이지로 리다이렉트
          window.location.href = "http://localhost:8081/cart/getCartInfo";
        },
        error: function(xhr, status, error) {
            alert("삭제에 실패했습니다.");
        }
        });
}

/**
상품수 변경
*/
function creaseProductQuantity(cartSeq, quantity){
    $.ajax({
        type: "POST",
        url: "/cart/modProductQuantity",
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