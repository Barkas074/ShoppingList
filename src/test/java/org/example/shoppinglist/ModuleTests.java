package org.example.shoppinglist;

import org.example.shoppinglist.form.ProductForm;
import org.example.shoppinglist.model.Product;
import org.example.shoppinglist.repository.ProductRepository;
import org.example.shoppinglist.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ModuleTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void addProduct() {
        Product product = new Product(new ProductForm("Meat"));
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("Meat");
    }

    @Test
    public void deleteProduct() {
        Optional<Product> product = Optional.of(new Product(new ProductForm("Помидоры")));
        Long id = product.get().getId();
        productService.remove(id);
        assertThat(product.get().getId()).isNull();
    }
}
