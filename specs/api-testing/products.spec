# Məhsullar (Products) — public GET endpointləri

Tags: api, products

## Filtrlə məhsul siyahısı qaytarılır və qiymət filtri işləyir

Tags: products, list, filter

* "/products?category=elektronika&maxPrice=2500" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "200" olur
* Cavab boş deyil
* "data" massivi boş deyil
* "total" açarı "data" massivinin ölçüsünə bərabərdir
* "data" massivinin hər elementində "id, name, price, category" açarları null deyil
* "data.price" dəyərlərinin hamısı "2500" -dən böyük deyil
* "data[0].id" açarının dəyəri "prod_001" olur
* "data[0].name" açarının dəyəri "iPhone 15 Pro" olur
* "data[0].price" açarının ədədi dəyəri "2499.99" olur
* "data[0].category.name" açarının dəyəri "Elektronika" olur
* "data[0].rating" açarının ədədi dəyəri "4.5" olur

## Tək məhsulun tam (nested) detalları qaytarılır

Tags: products, detail

* "/products/prod_001" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "200" olur
* "id" açarının dəyəri "prod_001" olur
* "name" açarının dəyəri "iPhone 15 Pro" olur
* "price" açarının ədədi dəyəri "2499.99" olur
* "category.name" açarının dəyəri "Elektronika" olur
* "category.parent.name" açarının dəyəri "Texnika" olur
* "specs.chip" açarının dəyəri "Apple A17 Pro" olur
* "specs.colors" açarı null deyil

## Məhsulun rəyləri və reytinq uyğunluğu yoxlanılır

Tags: products, reviews

* "/products/prod_001/reviews" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "200" olur
* "productId" açarının dəyəri "prod_001" olur
* "rating" açarının ədədi dəyəri "4.5" olur
* "reviewCount" açarının ədədi dəyəri "2" olur
* "reviews" massivi boş deyil
* "reviewCount" açarı "reviews" massivinin ölçüsünə bərabərdir
* "reviews[0].id" açarının dəyəri "rev_001" olur
* "reviews[0].userName" açarının dəyəri "Leyla Həsənova" olur
* "reviews[0].rating" açarının ədədi dəyəri "5" olur

## Mövcud olmayan məhsul üçün 404 qaytarılır

Tags: products, negative

* "/products/prod_999" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "404" olur