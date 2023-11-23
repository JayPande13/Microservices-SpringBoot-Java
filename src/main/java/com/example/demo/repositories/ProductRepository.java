package com.example.demo.repositories;


import com.example.demo.entities.QProducts;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import com.example.demo.entities.Products;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer>,
        QuerydslPredicateExecutor<Products>, QuerydslBinderCustomizer<QProducts>, JpaSpecificationExecutor<Products> {

    @Override
    default void customize(QuerydslBindings bindings, QProducts root){
        // Exclude the Id from query filter
        bindings.excluding(root.productId);
        // Perform IgnoreCase & EqualsLike comparison
        bindings.bind(String.class).first(
                (StringPath path, String value) ->
                        path.containsIgnoreCase(value)
        );
    }


}
