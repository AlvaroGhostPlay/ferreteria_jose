package com.alvaro.springcloud.msvc.navigation.provider.repositories;

import com.alvaro.springcloud.msvc.navigation.provider.entities.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {

    @EntityGraph(attributePaths = {"items"})
    Optional<Menu> findWithItemsByMenuId(UUID menuId);

    @EntityGraph(attributePaths = {"items"})
    List<Menu> findAll();

    @Query("""
        select distinct m
        from Menu m
        join MenuJob mj on mj.menu = m
        where mj.id.jobRoleId = :roleId
        """)
    List<Menu> findMenusByRole(@Param("roleId") UUID roleId);

    @EntityGraph(attributePaths = {"items"})
    @Query("""
        select distinct m
        from Menu m
        join MenuJob mj on mj.menu = m
        where mj.id.jobRoleId = :roleId and m.isMenu = :isMenu
        """)
    List<Menu> findMenusByRoleWithItems(@Param("roleId") UUID roleId, Boolean isMenu);

    Page<Menu> findAll(Pageable pageable);
}