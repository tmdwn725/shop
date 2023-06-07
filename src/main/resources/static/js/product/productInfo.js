function addCart(){
    const quantity = $("#quantity").val();
    const productStockSeq = $('input[name=size]:checked').val();
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