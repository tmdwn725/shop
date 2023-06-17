function addCart(){
    const quantity = $("#quantity").val();
    const productStockSeq = $('input[name=size]:checked').val();
    if(typeof productStockSeq == "undefined"){
        alert("사이즈를 선택해주세요");
        return;
    }
    $.ajax({
        type: "GET",
        url: "/addProductCart",
        data:{
            quantity : quantity,
            productStockSeq : productStockSeq
        },
        dataType: "json",
        success: function(){
        }
    });

}