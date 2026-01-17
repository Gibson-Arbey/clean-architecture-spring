package co.clean_architecture.jpa.repository;

import co.clean_architecture.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    @Modifying
    @Query("UPDATE UserEntity u SET u.status = :status WHERE u.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") String status);

    @Query(value = """
            SELECT DISTINCT
                u.*
            FROM comunity.users u
            JOIN comunity.user_roles ur
                ON u.user_id = ur.user_id
            JOIN comunity.roles r
                ON r.role_id = ur.role_id
            WHERE
                (:username = ''
                    OR u.user_username ILIKE CONCAT('%', :username, '%'))
                AND (:email = ''
                    OR u.user_email ILIKE CONCAT('%', :email, '%'))
                AND (:applyStatusFilter = false
                    OR u.user_status IN (:status))
                AND (:applyRolesFilter = false
                    OR r.role_name IN (:roles))
            LIMIT :limit OFFSET :offset;
            
            """, nativeQuery = true)
    List<UserEntity> findALlByFilters(
            @Param("username") String username,
            @Param("email") String email,
            @Param("applyStatusFilter") boolean applyStatusFilter,
            @Param("status") List<String> status,
            @Param("applyRolesFilter") boolean applyRolesFilter,
            @Param("roles") Set<String> roles,
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}
