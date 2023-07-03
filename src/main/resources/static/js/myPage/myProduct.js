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
const upload = document.querySelector('.upload');

upload.addEventListener('click', () => realUpload.click());
upload.addEventListener('click', () => realUpload1.click());
upload.addEventListener('click', () => realUpload2.click());
upload.addEventListener('click', () => realUpload3.click());

realUpload.addEventListener('change', getImageFiles);
realUpload1.addEventListener('change', getImageFiles);
realUpload2.addEventListener('change', getImageFiles);
realUpload2.addEventListener('change', getImageFiles);