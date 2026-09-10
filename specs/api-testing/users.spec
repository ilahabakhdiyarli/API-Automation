# İstifadəçilər (Users) — public GET endpointləri

Tags: api, users

## Bütün istifadəçilər siyahısı düzgün qaytarılır

Tags: users, list, smoke

* "/users" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "200" olur
* Cavab boş deyil
* "$" massivi boş deyil
* "$" massivinin hər elementində "id, name, email, role" açarları null deyil
* "[0].id" açarının dəyəri "1" olur
* "[0].name" açarının dəyəri "Admin User" olur
* "[0].email" açarının dəyəri "admin@test.com" olur
* "[0].role" açarının dəyəri "admin" olur

## ID ilə tək istifadəçi tam məlumatı ilə qaytarılır

Tags: users, detail

* "/users/1" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "200" olur
* "id" açarı null deyil
* "name" açarı null deyil
* "email" açarı null deyil
* "role" açarı null deyil
* "avatar" açarı null deyil
* "createdAt" açarı null deyil
* "id" açarının dəyəri "1" olur
* "name" açarının dəyəri "Admin User" olur
* "email" açarının dəyəri "admin@test.com" olur
* "role" açarının dəyəri "admin" olur

## Mövcud olmayan istifadəçi üçün 404 qaytarılır

Tags: users, negative

* "/users/999999" endpointinə GET sorğusu göndərilir
* Cavabın status kodu "404" olur