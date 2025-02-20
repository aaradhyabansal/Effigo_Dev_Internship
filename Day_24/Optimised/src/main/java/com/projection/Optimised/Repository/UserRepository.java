package com.projection.Optimised.Repository;

import com.projection.Optimised.Model.UserEntity;
import com.projection.Optimised.Projections.BasicUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<BasicUserInfo> findByUserId(UUID id);
}
