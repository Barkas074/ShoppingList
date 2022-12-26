package org.example.shoppinglist;

import org.example.shoppinglist.form.ProductForm;
import org.example.shoppinglist.model.Product;
import org.example.shoppinglist.repository.ProductRepository;
import org.example.shoppinglist.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ModuleTests {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void addProduct() {
        Product product = new Product(new ProductForm("Ìÿסמ"));
        productService.save(product);
        assertThat(product).isNotNull();
        assertThat(product.getName().equals("Ìÿסמ"));
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    public void deleteProduct() {
        Optional<Product> product = Optional.of(new Product(new ProductForm("Ïמלטהמנû")));
        Long id = product.get().getId();
        Mockito.doReturn(product).when(productRepository).findById(id);
        productService.remove(id);
        assertThat(product.get().getId()).isNull();
    }
}
