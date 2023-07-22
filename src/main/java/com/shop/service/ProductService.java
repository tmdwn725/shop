package com.shop.service;

import com.shop.common.FileUtil;
import com.shop.common.ModelMapperUtil;
import com.shop.domain.File;
import com.shop.domain.Product;
import com.shop.domain.ProductFile;
import com.shop.domain.ProductStock;
import com.shop.dto.FileDTO;
import com.shop.dto.ProductDTO;
import com.shop.repository.FileRepository;
import com.shop.repository.ProductFileRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Value("${root.path}")
    private String rootPath;
    @Value("${image.upload.path}")
    private String imageUploadPath;
    private final ProductRepository productRepository;
    private final ProductStockRepository productStockRepository;
    private final FileRepository fileRepository;
    private final ProductFileRepository productFileRepository;


    /**
     * 상품목록조회
     * @param start
     * @param limit
     * @return
     */
    public Page<ProductDTO> selectProductList(int start, int limit){
        PageRequest pageRequest = PageRequest.of(start-1, limit);
        Page<Product> result = productRepository.selectProductList(pageRequest, 0L);
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
    public ProductDTO selectProductInfo(Long productSeq){
        Product productInfo = productRepository.selectProduct(productSeq);
        ProductDTO product = ModelMapperUtil.map(productInfo, ProductDTO.class);
        return product;
    }

    /**
     * 내 상품관리목록 조회
     * @param start
     * @param limit
     * @param MemberSeq
     * @return
     */
    public Page<ProductDTO> selectMyProductList(int start, int limit, Long MemberSeq){
        PageRequest pageRequest = PageRequest.of(start-1, limit);
        Page<Product> result = productRepository.selectProductList(pageRequest, MemberSeq);
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
    public void saveProductInfo(ProductDTO productDTO, MultipartFile[] imageFileList){
        // 현재 날짜와 시간 취득
        LocalDateTime nowdatetime = LocalDateTime.now();
        Product product = new Product();
        product.createProduct(productDTO.getSellerSeq(), productDTO.getProductName(), productDTO.getProductContent(), productDTO.getProductType(), productDTO.getPrice(),nowdatetime);
        if(product.getProductSeq() > 0){
            // 상품정보 수정
            productRepository.updateProductInfo(product);
        }else{
            // 상품정보 등록
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
            for(int i = 0; i < imageFileList.length; i++){
                File file = new File();
                String fileClsCd = "030102";
                if(imageFileList[i].getSize() > 0){
                    FileUtil.saveFile(imageFileList[i],rootPath+imageUploadPath);
                    file.CreateFile(imageFileList[i].getOriginalFilename(), imageUploadPath + "/" + imageFileList[i].getOriginalFilename(),"jpg");
                }
                fileRepository.save(file);
                if(i==0){
                    fileClsCd = "030101";
                }
                ProductFile productFile = new ProductFile();
                productFile.createProductFile(product,fileClsCd,file);
                productFileRepository.save(productFile);
            }
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
}
