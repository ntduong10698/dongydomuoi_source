package com.bksoftwarevn.adminthuocdongy.userservice.repo;

import com.bksoftwarevn.adminthuocdongy.userservice.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findById(int id);

    @Query("select au from AppUser  au where au.deleted = false and au.username = ?1 and au.companyId = ?2")
    Optional<AppUser> findByUsername(String username, int comId);

    @Query("select au from AppUser  au where au.deleted = false and au.email = ?1 and au.companyId = ?2")
    Optional<AppUser> findByEmail(String email, int comId);

    @Query("from AppUser au where (au.username = ?1 or au.email = ?1) and au.password = ?2 and au.companyId = ?3 and au.deleted = false ")
    Optional<AppUser> checkLogin(String username, String password, int comId);

    @Query("select count (au) from AppUser au where (au.username = ?1 or au.email = ?2) and au.companyId = ?3 and au.deleted = false ")
    int checkRegister(String username, String email, int comId);

    //List

    @Query("from AppUser  x where x.deleted = false and x.companyId = ?1 and (x.id = ?2 or ?2 = 0) and (x.name like concat('%',?3,'%') or x.email like concat('%',?3,'%') or x.username like  concat('%',?3,'%'))")
    Page<AppUser> findByCompany(int comId, int id, String text, Pageable pageable);

    @Query("from AppUser x where  x.deleted = false and x.companyId = ?1 and x.enableFeedback = true")
    List<AppUser> getFeedback(int comId);

    @Query("from AppUser x where  x.deleted = false and x.companyId = ?1 and x.id in ?2")
    List<AppUser> findByIds(int comId, List<Integer> ids);
}
