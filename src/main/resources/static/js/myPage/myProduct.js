function getImageFiles(e) {
    const uploadFiles = [];
    const files = e.currentTarget.files;
    const imageId = this.id;
    const imagePreview = document.querySelector('.image-preview');
    const docFrag = new DocumentFragment();

    if ([...files].length >= 7) {
        alert('이미지는 최대 6개 까지 업로드가 가능합니다.');
        return;
    }

    // 파일 타입 검사
    [...files].forEach(file => {
    if (!file.type.match("image/.*")) {
      alert('이미지 파일만 업로드가 가능합니다.');
      return
    }

    // 파일 갯수 검사
    if ([...files].length < 7) {
        uploadFiles.push(file);
        const reader = new FileReader();
        reader.onload = (e) => {
        addImageFile(imageId.replace('file','upload'),e, file);
      };
      reader.readAsDataURL(file);
    }
  });
}

function addImageFile(id, e, file) {
    const div = document.getElementById(id);
    const imgElements = div.querySelectorAll('img'); // div 요소 안의 모든 img 요소를 선택합니다.
    const img = document.createElement('img');
    img.setAttribute('src', e.target.result);
    img.setAttribute('class', "product-image");
    img.setAttribute('data-file', file.name);
    div.appendChild(img);
}

const realUpload = document.querySelector('.real-upload');
const realUpload1 = document.querySelector('.real-upload1');
const realUpload2 = document.querySelector('.real-upload2');
const realUpload3 = document.querySelector('.real-upload3');

const upload = document.querySelector('.upload');
const upload1 = document.querySelector('.upload1');
const upload2 = document.querySelector('.upload2');
const upload3 = document.querySelector('.upload3');

upload.addEventListener('click', () => realUpload.click());
upload1.addEventListener('click', () => realUpload1.click());
upload2.addEventListener('click', () => realUpload2.click());
upload3.addEventListener('click', () => realUpload3.click());

realUpload.addEventListener('change', getImageFiles);
realUpload1.addEventListener('change', getImageFiles);
realUpload2.addEventListener('change', getImageFiles);
realUpload3.addEventListener('change', getImageFiles);

// Selectbox 이벤트 핸들러
function onProductTypeChange(value) {
    const options = $('#product-detail-type option');

    for(var i = 0; i < options.length; i++) {
        var option = options[i];
        var parentCategory = option.getAttribute('name');

        if (parentCategory === value) {
            option.style.display = 'block';
        }else {
            option.style.display = 'none';
        }
    }

    const selectBox = $('#product-detail-type'); // Selectbox의 ID를 선택

    // 가장 위에 있는 옵션을 선택
    selectBox.prop('selectedIndex', 0);
}

function saveProductInfo() {
    const name = $("#product-name").val();
    const content = $("#product-content").val();
    const price = $("#product-price").val();
    const imagePath = $("#upload-img1 img").attr("src");
    const type = $("#product-type").val();
    const detailType = $("#product-detail-type").val();
    let sizeType = {};

    $('input[name="product-size"]').each(function() {
      const id = $(this).attr('id');
      const value = $(this).val();
      sizeType[id] = value;
    });

    console.log(sizeType);

    $.ajax({
        type: "POST",
        url: "/myPage/saveMyProduct",
        data:{
            productName : name,
            productContent : content,
            price : price,
            filePth : "/img/product/related/rp-4.jpg",
            productTypeCd : detailType,
            sizeTypes : sizeType
        },
        dataType: "json",
        success: function(res){
            alert("등록되었습니다.");
        }
    });
}