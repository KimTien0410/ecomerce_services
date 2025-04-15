package com.rookies.ecomerce_services.repository;

import com.rookies.ecomerce_services.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
