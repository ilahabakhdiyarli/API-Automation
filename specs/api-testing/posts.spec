# Postlar (Posts) — public GET endpointləri

Tags: api, posts

## Yalnız published postlar {total, data[]} strukturunda qaytarılır

Tags: posts, list, smoke

* "/posts?category=Mobile" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "200" olur
* Cavab boş deyil
* "total" açarı null deyil
* "total" açarının dəyəri "0" -dən böyükdür
* "data" massivi boş deyil
* "data" massivinin hər elementində "id, title, author, category" açarları null deyil
* "data[0].author.name" açarı null deyil

## Tək post tam mətni və nested strukturu ilə qaytarılır

Tags: posts, detail

* "/posts/post_001" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "200" olur
* "id" açarının dəyəri "post_001" olur
* "title" açarının dəyəri "Cloudflare Workers ilə serverless API qurma" olur
* "content" açarı null deyil
* "author.name" açarının dəyəri "Anar Abbasov" olur
* "comments" massivi boş deyil

## Postun şərhləri ayrıca {postId, total, comments[]} ilə qaytarılır

Tags: posts, comments

* "/posts/post_001/comments" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "200" olur
* "postId" açarının dəyəri "post_001" olur
* "total" açarının dəyəri "0" -dən böyükdür
* "total" açarı "comments" massivinin ölçüsünə bərabərdir
* "comments" massivinin hər elementində "id, author, content, replies" açarları null deyil
* "comments[0].id" açarı null deyil
* "comments[0].author.name" açarı null deyil
* "comments[0].content" açarı null deyil