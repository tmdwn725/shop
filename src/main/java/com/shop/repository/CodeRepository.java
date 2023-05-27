package com.shop.repository;

import com.shop.domain.Code;
import com.shop.repository.custom.CodeConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, String>, CodeConfig {
}
