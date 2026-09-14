# POST Content — rəy, şərh, bəyənmə əlavə etmək

Tags: api, post, content

* "admin@test.com" və "Admin1234" ilə giriş edilir və token saxlanılır

## Məhsula yeni rəy əlavə olunur

Tags: post, reviews, create

* "/products/prod_002/reviews" endpointinə token ilə POST göndərilir, body: "{'rating':5,'title':'Əla','comment':'Çox məmnun qaldım.'}"
* Cavabın status kodu "201" olur
* Cavab boş deyil
* "id" açarı null deyil
* "createdAt" açarı null deyil
* "rating" açarının ədədi dəyəri "5" olur
* "comment" açarının dəyəri "Çox məmnun qaldım." olur

## Posta yeni şərh əlavə olunur

Tags: post, comments, create

* "/posts/post_002/comments" endpointinə token ilə POST göndərilir, body: "{'content':'Çox faydalı məqalə idi!'}"
* Cavabın status kodu "201" olur
* Cavab boş deyil
* "id" açarı null deyil
* "createdAt" açarı null deyil
* "content" açarının dəyəri "Çox faydalı məqalə idi!" olur
* "author.name" açarı null deyil

## Post bəyənilir və likes sayı qaytarılır

Tags: post, like

* "/posts/post_001/like" endpointinə token ilə POST göndərilir, body: "{}"
* Cavabın status kodu "200" olur
* "likes" açarı null deyil
* "likes" açarının dəyəri "0" -dən böyükdür