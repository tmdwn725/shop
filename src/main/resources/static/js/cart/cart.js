function increaseQuantity(element){
    const input = $(element).prev();
    const value = parseInt(input.val()) + 1;
    const name = input.attr("name");
    const total = $("#price"+name).val() * value;
    const totalPrice = parseInt($("#totalPrice").val()) + parseInt($("#price"+name).val());
    const formattedTotal = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(total);
    $('#total'+name).text(formattedTotal);
    $("#totalPrice").val(totalPrice);

    const totalText = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(totalPrice);
    $("#totalSubText").text(totalText);
    $("#totalText").text(totalText);
}

function decreaseQuantity(element){
    const input = $(element).next();
    let value = parseInt(input.val());
    const name = input.attr("name");
    if (value > 1) {
        value = value - 1;
    }
    const total = $("#price"+name).val() * value;
    const totalPrice = parseInt($("#totalPrice").val()) - parseInt($("#price"+name).val());
    const formattedTotal =new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(total);
    $('#total'+name).text(formattedTotal);
    $("#totalPrice").val(totalPrice);

    const totalText = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(totalPrice);
    $("#totalSubText").text(totalText);
    $("#totalText").text(totalText);
}

function getPaymentInfo(){
    // input 리스트를 담을 배열 생성
    var inputList = [];

    // tr 요소들을 순회하며 원하는 name 속성을 가진 input 값을 배열에 추가
    // form 요소 생성
    var form = document.getElementById("myCart");
    form.submit();
}