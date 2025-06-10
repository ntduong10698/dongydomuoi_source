package com.bksoftwarevn.adminthuocdongy.productservice.repository;

import com.bksoftwarevn.adminthuocdongy.productservice.entities.CategoryHasProduct;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.CategoryProductKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CateHasProductRepo extends JpaRepository<CategoryHasProduct, CategoryProductKey> {

    @Query("from CategoryHasProduct cp where cp.product.id = ?1 and cp.deleted = false")
    List<CategoryHasProduct> findByProduct(int id);

    @Query("select cp.product.id from CategoryHasProduct cp where cp.id.categoryId = ?1 and cp.deleted = false")
    List<Integer> findProductIdByCate(int cateId);

    @Query("update CategoryHasProduct cp set cp.deleted = true where cp.id in (?1)")
    @Modifying
    @Transactional
    int deleteById(List<CategoryProductKey> ids);

    @Query("select distinct cp.product from CategoryHasProduct cp where cp.category.productType.id = ?1 " +
            "and (?2 = 0 or cp.category.id = ?2) " +
            "and cp.product.name like concat('%',?3,'%') " +
            "and cp.deleted = false and cp.product.deleted = false " +
            "order by cp.product.createDate desc")
    Page<Product> filterService(int productType, int cateId, String text, Pageable pageable);

    @Query("select distinct cp.product from CategoryHasProduct cp where " +
            "cp.category.productType.companyId = ?7 and " +
            "( ?1 = 0 or cp.category.productType.id = ?1 )" +
            "and ( ?2 = false or cp.category.id in (?3) ) " +
            "and ( ?4 = 0 or cp.product.brand.id = ?4 ) " +
            "and ( cp.product.name like concat('%',?5,'%') or  cp.product.id = ?5 or cp.product.model like concat('%',?5,'%') ) " +
            "and ( ?6 = 0 or cp.product.status = ?6 ) " +
            "and cp.deleted = false and cp.product.deleted = false " +
            "order by cp.product.id asc")
    Page<Product> adminFilterDateAsc(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status, int comId, Pageable pageable);

    @Query("select distinct cp.product from CategoryHasProduct cp where " +
            "cp.category.productType.companyId = ?7 and " +
            "( ?1 = 0 or cp.category.productType.id = ?1 ) " +
            "and ( ?2 = false or cp.category.id in (?3) )" +
            "and ( ?4 = 0 or cp.product.brand.id = ?4 )" +
            "and ( cp.product.name like concat('%',?5,'%') or cp.product.id = ?5 or cp.product.model like concat('%',?5,'%') )" +
            "and ( ?6 = 0 or cp.product.status = ?6 )" +
            "and cp.deleted = false and cp.product.deleted = false " +
            "order by cp.product.id desc")
    Page<Product> adminFilterDateDesc(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable);

    @Query("select cp.product from CategoryHasProduct cp join ProductStatistic ps on ps.product.id = cp.product.id " +
            "where " +
            "cp.category.productType.companyId = ?7 and " +
            "( ?1 = 0 or cp.category.productType.id = ?1 ) and " +
            "( ?2 = false or cp.category.id in (?3) )" +
            "and ( ?4 = 0 or cp.product.brand.id = ?4 )" +
            "and ( cp.product.name like concat('%',?5,'%') or cp.product.id = ?5 or cp.product.model like concat('%',?5,'%') )" +
            "and ( ?6 = 0 or cp.product.status = ?6 )" +
            "and cp.deleted = false and cp.product.deleted = false " +
            "and ps.product.id = cp.product.id " +
            "group by cp.product " +
            "order by ps.viewed desc")
    Page<Product> adminFilterViewDesc(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable);

    @Query("select cp.product from CategoryHasProduct cp join ProductStatistic ps on ps.product.id = cp.product.id " +
            " where " +
            "cp.category.productType.companyId = ?7 and " +
            "( ?1 = 0 or cp.category.productType.id = ?1 ) " +
            "and ( ?2 = false or cp.category.id in (?3) )" +
            "and ( ?4 = 0 or cp.product.brand.id = ?4 )" +
            "and ( cp.product.name like concat('%',?5,'%') or cp.product.id = ?5 or cp.product.model like concat('%',?5,'%') )" +
            "and ( ?6 = 0 or cp.product.status = ?6 )" +
            "and cp.deleted = false and cp.product.deleted = false " +
            "and ps.product.id = cp.product.id " +
            "group by cp.product " +
            "order by ps.viewed asc")
    Page<Product> adminFilterViewAsc(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable);

    @Query("select cp.product from CategoryHasProduct cp join ProductStatistic ps on ps.product.id = cp.product.id " +
            "where " +
            "cp.category.productType.companyId = ?7 and " +
            "( ?1 = 0 or cp.category.productType.id = ?1 ) and  " +
            "( ?2 = false or cp.category.id in (?3) )" +
            "and ( ?4 = 0 or cp.product.brand.id = ?4 )" +
            "and ( cp.product.name like concat('%',?5,'%') or cp.product.id = ?5 or cp.product.model like concat('%',?5,'%') )" +
            "and ( ?6 = 0 or cp.product.status = ?6 )" +
            "and ps.product.id = cp.product.id " +
            "and cp.deleted = false and cp.product.deleted = false " +
            "group by cp.product " +
            "order by ps.sold asc")
    Page<Product> adminFilterSoldAsc(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable);

    @Query("select cp.product from CategoryHasProduct cp join ProductStatistic ps on ps.product.id = cp.product.id " +
            "where " +
            "cp.category.productType.companyId = ?7 and " +
            "( ?1 = 0 or cp.category.productType.id = ?1 ) " +
            "and ( ?2 = false or cp.category.id in (?3) )" +
            "and ( ?4 = 0 or cp.product.brand.id = ?4 )" +
            "and ( cp.product.name like concat('%',?5,'%') or cp.product.id = ?5 or cp.product.model like concat('%',?5,'%') )" +
            "and ( ?6 = 0 or cp.product.status = ?6 )" +
            "and cp.deleted = false and cp.product.deleted = false " +
            "group by cp.product " +
            "order by ps.sold desc")
    Page<Product> adminFilterSoldDesc(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable);

    @Query("select cp.product from CategoryHasProduct cp inner join ProductCost pc on (pc.product.id = cp.product.id and pc.defaultCost = true) " +
            "where " +
            "cp.category.productType.companyId = ?7 and " +
            "( ?1 = 0 or cp.category.productType.id = ?1 ) and " +
            "( ?2 = false or cp.category.id in (?3) )" +
            "and ( ?4 = 0 or cp.product.brand.id = ?4 )" +
            "and ( cp.product.name like concat('%',?5,'%') or cp.product.id = ?5 or cp.product.model like concat('%',?5,'%') )" +
            "and ( ?6 = 0 or cp.product.status = ?6 )" +
            "and cp.deleted = false and cp.product.deleted = false " +
            "group by cp.product " +
            "order by pc.cost desc")
    Page<Product> adminFilterCostDesc(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable);

    @Query("select cp.product from CategoryHasProduct cp join ProductCost pc on pc.product.id = cp.product.id and pc.defaultCost = true " +
            "where " +
            "cp.category.productType.companyId = ?7 and " +
            "( ?1 = 0 or cp.category.productType.id = ?1 ) " +
            "and ( ?2 = false or cp.category.id in (?3) )" +
            "and ( ?4 = 0 or cp.product.brand.id = ?4 )" +
            "and ( cp.product.name like concat('%',?5,'%') or cp.product.id = ?5 or cp.product.model like concat('%',?5,'%') )" +
            "and ( ?6 = 0 or cp.product.status = ?6 )" +
            "and cp.deleted = false and cp.product.deleted = false " +
            "group by cp.product " +
            "order by pc.cost asc ")
    Page<Product> adminFilterCostAsc(int productTypeId, boolean enableCate, List<Integer> cateIds, int brandId, String text, int status,int comId, Pageable pageable);
}
