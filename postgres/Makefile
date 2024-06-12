insert:
	curl --location 'http://localhost:8080/employee/v1/' --header 'Content-Type: application/json' --data '{"id":1,"first_name":"Pabitra","last_name":"Jena","age":25,"designation":"Engineer - Target Tech","phone_number":"+91 1234567890","joined_on":"2021-10-06","address":"Bangalore","date_of_birth":"1999-05-26"}'

get:
	curl --location 'http://localhost:8080/employee/v1/1'

get-all:
	curl --location 'http://localhost:8080/employee/v1/'

update:
	curl --location --request PUT 'http://localhost:8080/employee/v1/' --header 'Content-Type: application/json' --data '{"id":1,"first_name":"Pabitra","last_name":"Jena","age":20,"designation":"Engineer","phone_number":"+91 9999999999","joined_on":"2020-10-06","address":"Odisha","date_of_birth":"1999-05-26"}'

delete:
	curl --location --request DELETE 'http://localhost:8080/employee/v1/1'