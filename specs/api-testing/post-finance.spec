# POST Finance — köçürmə, transfer, kredit

Tags: api, post, finance

* "admin@test.com" və "Admin1234" ilə giriş edilir və token saxlanılır

## Hesablar arası əməliyyat (transaction) yaradılır

Tags: post, transactions, create

* "/transactions" endpointinə token ilə POST göndərilir, body: "{'senderAccountId':'acc_001','receiverAccountId':'acc_002','amount':50,'description':'Test köçürməsi'}"
* Cavabın status kodu "201" olur
* Cavab boş deyil
* "id" açarı null deyil
* "type" açarının dəyəri "transfer" olur
* "status" açarının dəyəri "completed" olur
* "amount" açarının ədədi dəyəri "50" olur

## Hesabdan hesaba transfer icra olunur

Tags: post, transfers, create

* "/transfers" endpointinə token ilə POST göndərilir, body: "{'senderAccountId':'acc_001','receiverAccountId':'acc_002','amount':10,'description':'Test transfer'}"
* Cavabın status kodu "201" olur
* Cavab boş deyil
* "receipt.id" açarı null deyil
* "receipt.status" açarının dəyəri "completed" olur
* "receipt.amount" açarının ədədi dəyəri "10" olur
* "receipt.currency" açarının dəyəri "AZN" olur

## Balans çatmayanda transfer 400 qaytarır

Tags: post, transfers, negative

* "/transfers" endpointinə token ilə POST göndərilir, body: "{'senderAccountId':'acc_001','receiverAccountId':'acc_002','amount':9999999,'description':'Çox böyük məbləğ'}"
* Cavabın status kodu "400" olur
* "error" açarı null deyil