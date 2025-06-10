package com.bksoftwarevn.adminthuocdongy.productservice.controller.admin_api;

import com.bksoftwarevn.adminthuocdongy.entities.RestBuilder;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.*;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.course.Course;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.*;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.*;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.key.CategoryProductKey;
import com.bksoftwarevn.adminthuocdongy.productservice.service.*;
import com.bksoftwarevn.adminthuocdongy.service.impl.RestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/admin/product")
//@CrossOrigin("http://localhost:6969")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CostService costService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private FileService fileService;

    @Autowired
    private RestService restService;

    private PageJson<ProductAdmin> productToAdmin(PageJson<Product> page) {
        PageJson<ProductAdmin> pageView = new PageJson<>();
        List<ProductAdmin> views = page.getContent().stream().map(product -> {
            ProductAdmin view = new ProductAdmin();
            view.setId(product.getId());
            view.setName(product.getName());
            view.setAlias(product.getAlias());
            view.setImage(product.getImage());
            view.setModel(product.getModel());
            view.setQuantity(product.getQuantity());
            view.setStatus(product.getStatus());
            try {
                ProductCost cost = costService.findDefaultCost(product.getId()).orElse(null);
                if (cost == null) return view;
                view.setCost(cost.getCost());
                view.setUnit(cost.getUnit());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //category
            view.setCategories(categoryService.findByProduct(product.getId()).stream().map(chp -> chp.getCategory().getName()).collect(Collectors.toList()));
            //view and sold
            statisticService.findTotal(product.getId()).ifPresent(sta -> {
                view.setView(sta.getViewed());
                view.setSold(sta.getSold());
            });
            return view;
        }).collect(Collectors.toList());
        pageView.setContent(views);
        pageView.setTotalElements(page.getTotalElements());
        pageView.setTotalPages(page.getTotalPages());
        pageView.setLast(page.isLast());
        return pageView;
    }

    private PageJson<CourseAdmin> productToCourse(PageJson<Product> page) {
        PageJson<CourseAdmin> pageView = new PageJson<>();
        List<CourseAdmin> views = page.getContent().stream().map(product -> {
            CourseAdmin view = new CourseAdmin();
            view.setId(product.getId());
            view.setName(product.getName());
            view.setAlias(product.getAlias());
            view.setImage(product.getImage());
            view.setModel(product.getModel());
            view.setQuantity(product.getQuantity());
            view.setStatus(product.getStatus());
            try {
                ProductCost cost = costService.findDefaultCost(product.getId()).orElse(null);
                if (cost == null) return view;
                view.setCost(cost.getCost());
            } catch (Exception e) {
                e.printStackTrace();
            } //category 
            view.setCategories(categoryService.findByProduct(product.getId()).stream().map(chp -> chp.getCategory().getName()).collect(Collectors.toList())); //view and sold
            statisticService.findTotal(product.getId()).ifPresent(sta -> {
                view.setView(sta.getViewed());
            });
            return view;
        }).collect(Collectors.toList()); //get courses 
        String proIds = views.stream().map(view -> view.getId() + "").collect(Collectors.joining(","));
        try {
            List<Course> courses = Arrays.asList(new ModelMapper().map(restService.callGet(RestBuilder.build()
                    .service("")
                    .uri("api/v1/public/courses/list")
                    .param("ids", proIds)), Course[].class));

            for (int i = 0; i < views.size(); i++) {
                int finalI = i;
                views.get(i).setCourse(courses.stream().filter(c -> c.getId() == views.get(finalI).getId()).findFirst().orElse(null));
            }
        } catch (Exception ex) {
        }
        pageView.setContent(views);
        pageView.setTotalElements(0);
        pageView.setTotalPages(0);
        pageView.setLast(true);
        return pageView;
    }


    @GetMapping("/filter")
    @ApiOperation(value = "bộ lọc sản phẩm", notes = "1: dụng cụ, 2: dịch vụ, 3: khóa học," +
            "  sort-type: date, price, view, sold ")
    public ResponseEntity<JsonResult> filterAdmin(@RequestParam(value = "company-id", required = false, defaultValue = "1") int comId,
                                                  @RequestParam(name = "product-type-id") int productTypeId,
                                                  @RequestParam(name = "categories", required = false) List<Integer> categories,
                                                  @RequestParam(name = "brand-id", defaultValue = "0", required = false) int brandId,
                                                  @RequestParam(name = "text", required = false, defaultValue = "") String text,
                                                  @RequestParam(name = "status", defaultValue = "0", required = false) int status,
                                                  @RequestParam(name = "sort-type", defaultValue = "date", required = false) String sort,
                                                  @RequestParam(name = "asc", defaultValue = "true", required = false) boolean asc,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "20") int size) {
        try {
            Pageable request = PageRequest.of(page - 1, size);
            switch (sort) {
                case "date": {
                    return JsonResult.success(productToAdmin(new PageJson<>(productService.adminFilterDate(productTypeId, categories != null, categories, brandId, text, status, comId, request, asc))));
                }
                case "view": {
                    return JsonResult.success(productToAdmin(new PageJson<>(productService.adminFilterViewed(productTypeId, categories != null, categories, brandId, text, status, comId, request, asc))));
                }
                case "sold":
                    return JsonResult.success(productToAdmin(new PageJson<>(productService.adminFilterSold(productTypeId, categories != null, categories, brandId, text, status, comId, request, asc))));
                case "price":
                    return JsonResult.success(productToAdmin(new PageJson<>(productService.adminFilterCost(productTypeId, categories != null, categories, brandId, text, status, comId, request, asc))));
            }
            return JsonResult.badRequest("bad");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/filter-courses")
    @ApiOperation(value = "bộ lọc sản phẩm", notes = "1: dụng cụ, 2: dịch vụ, 3: khóa học," +
            "  sort-type: date, price, view, sold ")
    public ResponseEntity<JsonResult> filterCourses(@RequestParam(value = "company-id", required = false, defaultValue = "1") int comId,
                                                    @RequestParam(name = "product-type-id") int productTypeId,
                                                    @RequestParam(name = "categories", required = false) List<Integer> categories,
                                                    @RequestParam(name = "text", required = false) String text,
                                                    @RequestParam(name = "status", defaultValue = "0", required = false) int status,
                                                    @RequestParam(name = "sort-type", defaultValue = "date", required = false) String sort,
                                                    @RequestParam(name = "asc", defaultValue = "true", required = false) boolean asc,
                                                    @RequestParam(name = "page", defaultValue = "1") int page,
                                                    @RequestParam(name = "size", defaultValue = "50") int size) {
        try {
            if (text == null) text = "";
            Pageable request = PageRequest.of(page - 1, size);
            switch (sort) {
                case "date": {
                    return JsonResult.success(productToCourse(new PageJson<>(productService.adminFilterDate(productTypeId, categories != null, categories, 0, text, status,comId, request, asc))));
                }
                case "view": {
                    return JsonResult.success(productToCourse(new PageJson<>(productService.adminFilterViewed(productTypeId, categories != null, categories, 0, text, status,comId, request, asc))));
                }
                case "sold":
                    return JsonResult.success(productToCourse(new PageJson<>(productService.adminFilterSold(productTypeId, categories != null, categories, 0, text, status,comId, request, asc))));
                case "price":
                    return JsonResult.success(productToCourse(new PageJson<>(productService.adminFilterCost(productTypeId, categories != null, categories, 0, text, status,comId, request, asc))));
                default:
                    return JsonResult.success(productToCourse(new PageJson<>(productService.adminFilterDate(productTypeId, categories != null, categories, 0, text, status,comId, request, asc))));
            }
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody ProductForm form) {
        try {
            Product product = Product.builder()
                    .name(form.getName())
                    .introduction(form.getIntroduction())
                    .preview(form.getPreview())
                    .model(form.getModel())
                    .image(form.getImage())
                    .guarantee(form.getGuarantee())
                    .quantity(form.getQuantity())
                    .deleted(false)
                    .createDate(new Date())
                    .alias(form.getAlias())
                    .status(1)
                    .brand(brandService.findById(form.getBrandId()).orElse(null))
                    .build();
            Optional<Product> optionalProduct = productService.save(product);
            if (optionalProduct.isPresent()) {
                Product saved = optionalProduct.get();
                form.getCategoryIds().forEach(cateId -> {
                    CategoryHasProduct chp = new CategoryHasProduct();
                    chp.setDeleted(false);
                    chp.setId(new CategoryProductKey());
                    chp.setProduct(saved);
                    try {
                        chp.setCategory(categoryService.findById(cateId).orElse(null));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    categoryService.addProduct(chp);
                });
                ProductCost cost = new ProductCost();
                cost.setDeleted(false);
                cost.setProduct(saved);
                cost.setCost(form.getCost());
                cost.setDefaultCost(true);
                cost.setUnit(form.getUnit());
                cost.setWeight(form.getWeight());
                try {
                    costService.save(cost);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                statisticService.createStatistic(saved.getId());
                return JsonResult.uploaded(saved);
            }
            return JsonResult.badRequest("data is invalid");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id) {
        try {
            UpdateProductJson json = new UpdateProductJson();
            json.setProduct(productService.findById(id).orElse(null));
            json.setCosts(costService.findByProduct(id));
            json.setProperties(productService.findPHPByProduct(id));
            json.setFiles(fileService.findByProduct(id, 0));
            json.setCategories(categoryService.findByProduct(id).stream().map(chp -> CateJson.builder()
                    .newCate(false)
                    .cateId(chp.getCategory().getId())
                    .productTypeId(chp.getCategory().getProductType().getId())
                    .deleted(false)
                    .build()).collect(Collectors.toList()));
            return JsonResult.success(json);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping("/{id}/properties")
    @ApiResponse(code = 200, message = "add properties to product", response = PHPJson.class, responseContainer = "List")
    public ResponseEntity<JsonResult> addProperties(@PathVariable(name = "id") int id,
                                                    @RequestBody List<PHPJson> phpJsons) {
        try {
            productService.addProperty(id, phpJsons);
            return JsonResult.success("added");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping("/properties")
    @ApiResponse(code = 200, message = "update properties of product", response = ProductHasProperty.class, responseContainer = "List")
    public ResponseEntity<JsonResult> updateProperties(@RequestBody List<ProductHasProperty> phps) {
        try {
            productService.updateProperty(phps);
            return JsonResult.updated(phps);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody UpdateProductJson json) {
        try {
            productService.save(json.getProduct());
            productService.updateProperty(json.getProperties());
            categoryService.alterByProduct(json.getProduct().getId(), json.getCategories());
            costService.saveAll(json.getCosts());
            fileService.saveAll(json.getFiles());
            return JsonResult.updated("ok");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> delete(@PathVariable(name = "id") int id) {
        try {
            if (productService.delete(id))
                return JsonResult.deleted();
            return JsonResult.badRequest("id is not exist");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/multi")
    public ResponseEntity<JsonResult> deletes(@RequestParam("ids") List<Integer> ids) {
        try {
            productService.deletes(ids);
            return JsonResult.deleted();
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping("/status/{status}")
    public ResponseEntity<JsonResult> alterStatus(@PathVariable("status") int status,
                                                  @RequestParam("ids") List<Integer> ids) {
        try {
            productService.alterStatus(status, ids);
            return JsonResult.updated(null);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

}
