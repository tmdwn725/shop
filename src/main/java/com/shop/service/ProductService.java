package com.shop.service;

import com.shop.common.FileUtil;
import com.shop.common.ModelMapperUtil;
import com.shop.domain.*;
import com.shop.domain.enums.ProductType;
import com.shop.dto.HeartDTO;
import com.shop.dto.ProductDTO;
import com.shop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Value("${root.path}")
    private String rootPth;
    @Value("${image.product.path}")
    private String imageUploadPath;
    private final ProductRepository productRepository;
    private final ProductStockRepository productStockRepository;
    private final FileRepository fileRepository;
    private final ProductFileRepository productFileRepository;
    private final MemberRepository memberRepository;
    private final HeartRepository heartRepository;

    /**
     * 상품목록조회
     * @param start
     * @param limit
     * @return
     */
    public Page<ProductDTO> selectProductList(int start, int limit, String memberId, ProductType productType, String searchStr){
        Member member = memberRepository.fingByMemberId(memberId);
        PageRequest pageRequest = PageRequest.of(start-1, limit);
        Page<Product> result = productRepository.selectProductList(pageRequest, 0L, member.getMemberSeq(), productType,searchStr);
        int total = result.getTotalPages();
        pageRequest = PageRequest.of((total-1), limit);
        List<ProductDTO> list = ModelMapperUtil.mapAll(result.getContent(), ProductDTO.class);
        return new PageImpl<>(list, pageRequest, total);
    }

    /**
     * 상품정보조회
     * @param productSeq
     * @return
     */
    public ProductDTO selectProductInfo(Long productSeq, String memberId){
        Member member = memberRepository.fingByMemberId(memberId);
        Product productInfo = productRepository.selectProduct(productSeq, member.getMemberSeq());
        ProductDTO product = ModelMapperUtil.map(productInfo, ProductDTO.class);
        HeartDTO heart = Optional.ofNullable(product)
                .map(ProductDTO::getHeartList)
                .orElse(null)
                .stream()
                .filter(heartDTO -> heartDTO.getMember() != null && heartDTO.getMember().getMemberSeq() == member.getMemberSeq())
                .findFirst()
                .orElse(null);
        product.setHeart(heart);
        return product;
    }

    /**
     * 내 상품관리목록 조회
     * @param start
     * @param limit
     * @param memberSeq
     * @return
     */
    public Page<ProductDTO> selectMyProductList(int start, int limit, Long memberSeq){
        PageRequest pageRequest = PageRequest.of(start-1, limit);
        Page<Product> result = productRepository.selectProductList(pageRequest, memberSeq, memberSeq, null, null);
        int total = result.getTotalPages();
        if (total > 0) {
            pageRequest = PageRequest.of((total-1), limit);
        }
        List<ProductDTO> list = ModelMapperUtil.mapAll(result.getContent(), ProductDTO.class);
        return new PageImpl<>(list, pageRequest, total);
    }

    /**
     * 상품정보 저장
     * @param productDTO
     */
    @Transactional
    public void saveProductInfo(ProductDTO productDTO, MultipartFile[] imageFileList){
        // 현재 날짜와 시간 취득
        LocalDateTime nowDate = LocalDateTime.now();
        Product product = new Product();
        product.createProduct(productDTO.getProductSeq(), productDTO.getSellerSeq(), productDTO.getProductName(), productDTO.getProductContent(), productDTO.getProductType(), productDTO.getPrice(),nowDate, null);

        // 상품정보 수정
        if(productDTO.getProductSeq() > 0){
            productRepository.updateProductInfo(product);
            for (Map.Entry<String, String> entry : productDTO.getSizeTypes().entrySet()) {
                ProductStock productStock = productStockRepository.selectProductStock(product.getProductSeq(),entry.getKey());
                if(productStock != null){
                    productStockRepository.updateProductStockCount(product.getProductSeq(),entry.getKey(),Integer.parseInt(entry.getValue()));
                }else{
                    ProductStock ps = new ProductStock();
                    ps.createProductStock(product, entry.getKey(), Integer.parseInt(entry.getValue()));
                    productStockRepository.save(ps);
                }
            }

            Arrays.stream(imageFileList)
                .filter(imageFile -> imageFile.getSize() > 0)
                .forEach(imageFile -> {
                    String fileClsCd = "030102";
                    String filePth = imageUploadPath + "/" + product.getProductSeq();
                    // index 구하기
                    int index = Arrays.asList(imageFileList).indexOf(imageFile);
                    if (index == 0) {
                        fileClsCd = "030101";
                    }
                    // 기준 상품 파일 삭제
                    productFileRepository.deleteProductFile(product.getProductSeq(),fileClsCd,index+1);
                    String saveFilePth = FileUtil.saveFile(imageFile, rootPth, filePth);
                    File fileInfo = new File();
                    fileInfo.CreateFile(imageFile.getSize(), nowDate, null, imageFile.getOriginalFilename(), saveFilePth, "jpg");
                    fileRepository.save(fileInfo);

                    ProductFile productFile = new ProductFile();
                    productFile.createProductFile(product, fileClsCd, fileInfo, index+1);
                    productFileRepository.save(productFile);
                });
        }else{ // 상품정보 등록
            productRepository.save(product);
            List<ProductStock> productStockList = productDTO.getSizeTypes().entrySet().stream()
                .map(entry -> {
                    String sizeType = entry.getKey();
                    String quantity = entry.getValue();
                    ProductStock ps = new ProductStock();
                    ps.createProductStock(product, sizeType, Integer.parseInt(quantity));
                    return ps;
                })
                .collect(Collectors.toList());
            productStockRepository.saveAll(productStockList);

            // 상품 이미지 사진 저장
            Arrays.stream(imageFileList)
                .filter(imageFile -> imageFile.getSize() > 0)
                .forEach(imageFile -> {

                    String filePth = imageUploadPath + "/" + product.getProductSeq();
                    String fileClsCd = "030102";

                    String saveFilePth = FileUtil.saveFile(imageFile, rootPth, filePth);
                    File fileInfo = new File();
                    fileInfo.CreateFile(imageFile.getSize(), nowDate, null, imageFile.getOriginalFilename(), saveFilePth, "jpg");
                    fileRepository.save(fileInfo);

                    // index 구하기
                    int index = Arrays.asList(imageFileList).indexOf(imageFile);
                    if (index == 0) {
                        fileClsCd = "030101";
                    }
                    ProductFile productFile = new ProductFile();
                    productFile.createProductFile(product, fileClsCd, fileInfo, index+1);
                    productFileRepository.save(productFile);
                });
        }
    }

    /**
     * 상품정보 삭제
     * @param productDTO
     */
    @Transactional
    public void removeProduct(ProductDTO productDTO){
        Product product = productRepository.findById(productDTO.getProductSeq()).get();
        List<ProductStock> productStockList = product.getProductStockList();
        fileRepository.delete(product.getProductFileList().get(0).getFile());
        productFileRepository.deleteAll(product.getProductFileList());
        productStockRepository.deleteAll(productStockList);
        productRepository.delete(product);
    }

    /**
     * 좋아요 등록/삭제
     * @param productDTO
     * @param memberId
     */
    @Transactional
    public void updateHeartInfo(ProductDTO productDTO, String memberId) {
        LocalDateTime nowDate = LocalDateTime.now();
        Heart heart = new Heart();
        Member member = memberRepository.fingByMemberId(memberId);
        Product product = productRepository.findById(productDTO.getProductSeq()).get();
        heart.createHeart(member, product, nowDate);
        // 좋아요 취소시 삭제
        if("Y".equals(productDTO.getUpdateYn())) {
            heartRepository.save(heart);
        }else {
            heart = heartRepository.selectHeartInfo(heart);
            heartRepository.delete(heart);
        }
    }
}
