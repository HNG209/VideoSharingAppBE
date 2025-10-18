package com.iuh.se.videoSharingApp.repository;

import com.iuh.se.videoSharingApp.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {
}
