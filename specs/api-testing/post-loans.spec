# POST Loans — kredit müraciəti və taksit

Tags: api, post, loans

* "admin@test.com" və "Admin1234" ilə giriş edilir və token saxlanılır

## Yanlış məbləğlə kredit müraciəti 400 qaytarır

Tags: post, loans, negative

* "/loans" endpointinə token ilə POST göndərilir, body: "{'amount':100,'termMonths':24,'purpose':'car','accountId':'acc_001'}"
* Cavabın status kodu "400" olur
* "error" açarı null deyil

## Mövcud olmayan kreditə taksit ödənişi 404 qaytarır

Tags: post, loans, pay, negative

* "/loans/loan_999/pay" endpointinə token ilə POST göndərilir, body: "{'installmentId':'inst_999_01'}"
* Cavabın status kodu "404" olur
* "error" açarı null deyil