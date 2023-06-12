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

// input 리스트를 담을 배열 생성
        var inputList = [];

        // tr 요소들을 순회하며 원하는 name 속성을 가진 input 값을 배열에 추가
        $("tr input[name='cartTr']").each(function() {
            var cartSeq = $(this).val();
            var productName = $("#productName" + cartSeq).val();
            var size = $("#size" + cartSeq).val();
            var price = $("#price" + cartSeq).val();

            // 값들을 객체로 생성하여 배열에 추가
            var input = {
                cartSeq: cartSeq,
                productName: productName,
                size: size,
                price: price
            };

            inputList.push(input);
        });

        // 컨트롤러로 inputList를 전달
        $.ajax({
            type: "POST",
            url: "/controller-url",
            data: JSON.stringify(inputList),
            contentType: "application/json",
            success: function(response) {
                // 성공적으로 처리되었을 때의 동작
                console.log(response);
            },
            error: function() {
                // 오류 발생 시의 동작
                console.log("오류 발생");
            }
        });
    }