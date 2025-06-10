package com.bksoftwarevn.adminthuocdongy.productservice.controller.public_api;

import com.bksoftwarevn.adminthuocdongy.entities.RestBuilder;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.CategoryHasProduct;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.Product;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.ProductCost;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.course.Course;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.course.CourseView;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.ServiceView;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.DecreaseForm;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.ProductJson;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.json.product.ProductView;
import com.bksoftwarevn.adminthuocdongy.productservice.entities.promo.Promotion;
import com.bksoftwarevn.adminthuocdongy.productservice.service.*;
import com.bksoftwarevn.adminthuocdongy.service.impl.RestService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/public/products")
public class ProductPublicController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CostService costService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RestService restService;

    @Autowired
    private StatisticService statisticService;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id,
                                               @RequestParam(value = "cost", defaultValue = "true") boolean cost,
                                               @RequestParam(value = "property", defaultValue = "false") boolean property,
                                               @RequestParam(value = "file", defaultValue = "false") boolean file,
                                               @RequestParam(value = "category", defaultValue = "false") boolean category,
                                               @RequestParam(value = "promotion", defaultValue = "false") boolean promo,
                                               @RequestParam(value = "statistic", defaultValue = "true") boolean statistic) {
        try {
            ProductJson json = new ProductJson();
            json.setProduct(productService.findById(id).orElse(null));
            if (cost)
                json.setCosts(costService.findByProduct(id).stream().peek(productCost -> productCost.setProduct(null)).collect(Collectors.toList()));
            if (property) json.setProperties(productService.findPHPByProduct(id));
            if (file)
                json.setFiles(fileService.findByProduct(id, 0).stream().peek(productFile -> productFile.setProduct(null)).collect(Collectors.toList()));
            if (category)
                json.setCategories(categoryService.findByProduct(id).stream().map(CategoryHasProduct::getCategory).collect(Collectors.toList()));
            if (promo)
                json.setPromotion(restService.callGet(RestBuilder.build().service("").uri("api/v1/public/promotions/product/" + id)));
            if (statistic)
                json.setStatistic(statisticService.findTotal(id).orElse(null));
            return JsonResult.success(json);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping
    public ResponseEntity<JsonResult> findByIds(@RequestParam(value = "ids") List<Integer> ids) {
        try {
            return JsonResult.success(productService.findByIds(ids));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("ids/more")
    public ResponseEntity<JsonResult> findByIdsAndProperties(@RequestParam(value = "ids") List<Integer> ids) {
        try {
            List<Product> productList = productService.findByIds(ids);
            if (productList != null && !productList.isEmpty()) {
                return JsonResult.success(listProductToView(productList));
            } else {
                return JsonResult.badRequest("ids not found");
            }
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }


    @GetMapping("/category/{category-id}")
    public ResponseEntity<JsonResult> findAllProductIdByCategory(@PathVariable("category-id") int id) {
        try {
            return JsonResult.success(productService.findProductIdByCate(id));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/{id}/properties")
    public ResponseEntity<JsonResult> findPropertyByProduct(@PathVariable(name = "id") int id) {
        try {
            return JsonResult.success(productService.findPHPByProduct(id));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/filter")
    @ApiOperation(value = "bộ lọc sản phẩm", notes = "1: dụng cụ, 2: dịch vụ, 3: khóa học," +
            "  sort-type: date, price, view, sold ")
    public ResponseEntity<JsonResult> filter(@RequestParam(value = "company-id", required = false, defaultValue = "1") int comId,
                                             @RequestParam(name = "product-type-id", defaultValue = "1") int productTypeId,
                                             @RequestParam(name = "categories", required = false) List<Integer> categories,
                                             @RequestParam(name = "brand-id", defaultValue = "0", required = false) int brandId,
                                             @RequestParam(name = "text", required = false, defaultValue = "") String text,
                                             @RequestParam(name = "status", defaultValue = "0", required = false) int status,
                                             @RequestParam(name = "sort-type", defaultValue = "date", required = false) String sort,
                                             @RequestParam(name = "asc", defaultValue = "true", required = false) boolean asc,
                                             @RequestParam(name = "page", defaultValue = "1") int page,
                                             @RequestParam(name = "size", defaultValue = "15") int size
    ) {
        try {
            Pageable request = PageRequest.of(page - 1, size);
            switch (sort) {
                case "date": {
                    return JsonResult.success(productToView(new PageJson<>(productService.adminFilterDate(productTypeId, categories != null, categories, brandId, text, status, comId, request, asc))));
                }
                case "view": {
                    return JsonResult.success(productToView(new PageJson<>(productService.adminFilterViewed(productTypeId, categories != null, categories, brandId, text, status, comId, request, asc))));
                }
                case "sold":
                    return JsonResult.success(productToView(new PageJson<>(productService.adminFilterSold(productTypeId, categories != null, categories, brandId, text, status, comId, request, asc))));
                case "price":
                    return JsonResult.success(productToView(new PageJson<>(productService.adminFilterCost(productTypeId, categories != null, categories, brandId, text, status, comId, request, asc))));
            }
            return JsonResult.badRequest("bad");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e);
        }
    }

    @GetMapping("/services")
    public ResponseEntity<JsonResult> findService(@RequestParam(name = "product-type") int productTypeId,
                                                  @RequestParam(name = "category", defaultValue = "0") int category,
                                                  @RequestParam(name = "text", required = false, defaultValue = "") String text,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "6") int size) {
        try {
            if (text == null) text = "";
            Pageable request = PageRequest.of(page - 1, size);
            return JsonResult.success(productToService(new PageJson<>(productService.filterService(productTypeId, category, text, request))));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<JsonResult> findCourses(@RequestParam(name = "product-type") int productTypeId,
                                                  @RequestParam(name = "category", defaultValue = "0") int category,
                                                  @RequestParam(name = "text", required = false, defaultValue = "") String text,
                                                  @RequestParam(name = "online", required = false, defaultValue = "true") boolean online,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "50") int size) {
        try {
            if (text == null) text = "";
            Pageable request = PageRequest.of(page - 1, size);
            return JsonResult.success(productToCourse(new PageJson<>(productService.filterService(productTypeId, category, text, request)), online));
        } catch (Exception ex) {
            ex.printStackTrace();
            return JsonResult.error(ex);
        }
    }

    @PutMapping("/decrease")
    public ResponseEntity<JsonResult> decrease(@RequestBody List<DecreaseForm> forms, @RequestParam("password") String password) {
        try {
            if (!password.equals("Bksoftwarevn"))
                return JsonResult.badRequest("No permisstion");
            productService.decreaseByIds(forms);
            forms.forEach(form -> statisticService.increaseSold(form.getProductId(), form.getNumber()));
            return JsonResult.updated(null);
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    private PageJson<ProductView> productToView(PageJson<Product> page) {
        PageJson<ProductView> pageView = new PageJson<>();
        List<ProductView> views = page.getContent().stream().map(product -> {
            ProductView view = new ProductView();
            view.setId(product.getId());
            view.setName(product.getName());
            view.setQuantity(product.getQuantity());
            view.setImage(product.getImage());
            view.setPreview(product.getPreview());
            view.setAlias(product.getAlias());
            try {
                ProductCost cost = costService.findDefaultCost(product.getId()).orElse(null);
                if (cost == null) return view;
                view.setCost(cost.getCost());
                view.setUnit(cost.getUnit());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //promo
            return view;
        }).collect(Collectors.toList());
        String proIds = views.stream().map(view -> view.getId() + "").collect(Collectors.joining(","));
        try {
            List<List<Promotion>> listsPromos = (List<List<Promotion>>) restService.callGet(RestBuilder.build()
                    .service("")
                    .uri("api/v1/public/promotions/products")
                    .param("ids", proIds));
            for (int i = 0; i < views.size(); i++) {
                views.get(i).setPromotions(listsPromos.get(i));
            }
        } catch (Exception ex) {
        }
        pageView.setContent(views);
        pageView.setTotalElements(page.getTotalElements());
        pageView.setTotalPages(page.getTotalPages());
        pageView.setLast(page.isLast());
        return pageView;
    }

    private List<ProductView> listProductToView(List<Product> listProduct) {
        List<ProductView> views = listProduct.stream().map(product -> {
            ProductView view = new ProductView();
            view.setId(product.getId());
            view.setName(product.getName());
            view.setImage(product.getImage());
            view.setPreview(product.getPreview());
            view.setAlias(product.getAlias());
            view.setQuantity(product.getQuantity());
            try {
                ProductCost cost = costService.findDefaultCost(product.getId()).orElse(null);
                if (cost == null) return view;
                view.setCost(cost.getCost());
                view.setUnit(cost.getUnit());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //promo
            return view;
        }).collect(Collectors.toList());
        String proIds = views.stream().map(view -> view.getId() + "").collect(Collectors.joining(","));
        try {
            List<List<Promotion>> listsPromos = (List<List<Promotion>>) restService.callGet(RestBuilder.build()
                    .service("")
                    .uri("api/v1/public/promotions/products")
                    .param("ids", proIds.toString()));
            for (int i = 0; i < views.size(); i++) {
                views.get(i).setPromotions(listsPromos.get(i));
            }
        } catch (Exception ex) {
        }
        return views;
    }

    private PageJson<ServiceView> productToService(PageJson<Product> page) {
        PageJson<ServiceView> pageView = new PageJson<>();
        List<ServiceView> views = page.getContent().stream().map(product -> {
            ServiceView view = new ServiceView();
            view.setId(product.getId());
            view.setName(product.getName());
            view.setAlias(product.getAlias());
            view.setImage(product.getImage());
            view.setPreview(product.getPreview());
            view.setAlias(product.getAlias());
            return view;
        }).collect(Collectors.toList());
        pageView.setContent(views);
        pageView.setTotalElements(page.getTotalElements());
        pageView.setTotalPages(page.getTotalPages());
        pageView.setLast(page.isLast());
        return pageView;
    }

    private PageJson<CourseView> productToCourse(PageJson<Product> page, boolean online) {
        PageJson<CourseView> pageView = new PageJson<>();
        List<CourseView> views = page.getContent().stream().map(course -> {
            CourseView view = new CourseView();
            view.setId(course.getId());
            view.setName(course.getName());
            view.setImage(course.getImage());
            view.setPreview(course.getPreview());
            view.setAlias(course.getAlias());
            try {
                ProductCost cost = costService.findDefaultCost(course.getId()).orElse(null);
                if (cost == null) return view;
                view.setCost(cost.getCost());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return view;
        }).collect(Collectors.toList());
        String proIds = views.stream().map(view -> view.getId() + "").collect(Collectors.joining(","));
        try {
            List<List<Promotion>> listsPromos = (List<List<Promotion>>) restService.callGet(RestBuilder.build()
                    .service("")
                    .uri("api/v1/public/promotions/products")
                    .param("ids", proIds));
            List<Course> courses = Arrays.asList(new ModelMapper().map(restService.callGet(RestBuilder.build()
                    .service("")
                    .uri("api/v1/public/courses/list")
                    .param("ids", proIds)), Course[].class));

            for (int i = 0; i < views.size(); i++) {
                views.get(i).setPromotions(listsPromos.get(i));
                int finalI = i;
                List<CourseView> finalViews = views;
                views.get(i).setCourse(courses.stream().filter(c -> c.getId() == finalViews.get(finalI).getId()).findFirst().orElse(null));
            }
            views = views.stream().filter(view -> view.getCourse() != null && view.getCourse().getType() == (online ? 1 : 2)).collect(Collectors.toList());
        } catch (Exception ex) {
        }
        pageView.setContent(views);
        pageView.setTotalElements(0);
        pageView.setTotalPages(0);
        pageView.setLast(true);
        return pageView;
    }
}
