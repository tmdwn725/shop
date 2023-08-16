function getImageFiles(e) {
    const uploadFiles = [];
    const files = e.currentTarget.files;
    const imageId = this.id;
    const imagePreview = document.querySelector('.image-preview');
    const docFrag = new DocumentFragment();

    if ([...files].length >= 4) {
        alert('이미지는 최대 4개 까지 업로드가 가능합니다.');
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

// 이미지 파일 추가
function addImageFile(id, e, file) {
    const div = document.getElementById(id);
    const imgElements = div.querySelector('img'); // div 요소 안의 모든 img 요소를 선택합니다.

    // 이미지가 이미 존재하는지 확인합니다.
    if (imgElements != null) {
        // 이미지의 src 속성을 변경합니다.
        $(imgElements[0]).attr('src', e.target.result);
        $(imgElements[0]).attr('data-file', file.name);
    } else {
        // 이미지가 없으면 새로 생성하여 추가합니다.
        const img = document.createElement('img');
        img.setAttribute('src', e.target.result);
        img.setAttribute('class', "product-image");
        img.setAttribute('data-file', file.name);
        div.appendChild(img);
    }
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

function saveMyReview() {
    let seq = $("#order-info-seq").val();
    if(seq == ''){
        seq = 0;
    }

    const content = $("#review-content").val();
    const imagePath = $("#upload-img1 img").attr("src");
    const imageForm = document.getElementById('product-image-form');
    let formData = new FormData(imageForm);
    let sizeType = {};

    formData.append("orderSeq", seq);
    formData.append("content", content);

    $.ajax({
        type: "POST",
        url: "/myPage/saveMyReview",
        contentType: false, // 필수: FormData를 사용하기 때문에 false로 설정
        processData: false, // 필수: FormData를 사용하기 때문에 false로 설정
        data:formData,
        success: function(response){
            console.log("등록");
            alert("등록되었습니다.");
            window.location.href = "http://localhost:8081/myPage/getMyProductList";
        },
        error: function(xhr, status, error) {
            alert("삭제에 실패했습니다.");
        }
    });
}
