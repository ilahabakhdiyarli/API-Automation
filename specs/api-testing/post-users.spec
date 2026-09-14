# POST İstifadəçilər — yeni resurs yaratmaq

Tags: api, post, users

* "admin@test.com" və "Admin1234" ilə giriş edilir və token saxlanılır

## Yeni istifadəçi yaradılır və cavab tam yoxlanılır

Tags: post, users, create

* "/users" endpointinə token ilə POST göndərilir, body: "{'name':'Ilaha Test','email':'ilaha_{{ts}}@test.az','role':'user'}"
* Cavabın status kodu "201" olur
* Cavab boş deyil
* "id" açarı null deyil
* "createdAt" açarı null deyil
* "name" açarının dəyəri "Ilaha Test" olur
* "role" açarının dəyəri "user" olur