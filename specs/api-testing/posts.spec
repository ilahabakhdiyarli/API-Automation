# Postlar (Posts) — public GET endpointləri

Tags: api, posts

## Yalnız published postlar {total, data[]} strukturunda qaytarılır

Tags: posts, list, smoke

* "/posts?category=Mobile" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "200" olur
* Cavab boş deyil
* "total" açarı null deyil
* "total" açarının ədədi dəyəri "1" olur
* "data" massivi boş deyil
* "total" açarı "data" massivinin ölçüsünə bərabərdir
* "data" massivinin hər elementində "id, title, author, category" açarları null deyil
* "data[0].id" açarının dəyəri "post_002" olur
* "data[0].title" açarının dəyəri "Flutter ilə cross-platform mobil tətbiq inkişafı" olur
* "data[0].author.name" açarının dəyəri "Anar Abbasov" olur
* "data[0].category" açarının dəyəri "Mobile" olur

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
* "total" açarının ədədi dəyəri "2" olur
* "total" açarı "comments" massivinin ölçüsünə bərabərdir
* "comments" massivinin hər elementində "id, author, content, replies" açarları null deyil
* "comments[0].id" açarının dəyəri "cmt_001" olur
* "comments[0].author.name" açarının dəyəri "Leyla Həsənova" olur
* "comments[0].content" açarının dəyəri "Çox faydalı məqalə idi! Workers-ə keçməyi düşünürəm." olur