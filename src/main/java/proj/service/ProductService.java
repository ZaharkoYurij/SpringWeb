package proj.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import proj.entity.Product;
import proj.form.Filter.ProductFilterForm;
import proj.form.Filter.ProductFrontFilterForm;
import proj.form.ProductForm;

import java.util.List;

/**
 * Created by SC on 28.08.2016.
 */
public interface ProductService {
    void save(ProductForm productForm);

    Product findById(int id);

    Product findByName(String name);

    Product findByPartNumber(String partNumber);

    void delete(int id);

    List<Product> findAll();

    ProductForm findForForm(int id);

    Page<Product> findAll(Pageable pageable, ProductFilterForm productFilterForm);

    Page findProduct(Pageable pageable, ProductFrontFilterForm productFrontFilterForm);

    Page<Product> findProductByCategoryId(Pageable pageable, ProductFrontFilterForm productFrontFilterForm, int id);
}
