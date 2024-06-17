BASE_URL := http://localhost:8080
API_VERSION := v1
ROLE_PATH := /role/$(API_VERSION)
USER_PATH := /user/$(API_VERSION)
EMPLOYEE_PATH := /employee/$(API_VERSION)

.PHONY: insert get get-all update delete get-all-roles get-role create-role update-role delete-role get-all-users get-user-by-id create-user update-user delete-user

insert:
	curl --location '$(BASE_URL)$(EMPLOYEE_PATH)' --header 'Content-Type: application/json' --data '{"first_name":"Pabitra","last_name":"Jena","age":25,"designation":"Engineer - Target Tech","phone_number":"+91 1234567890","joined_on":"2021-10-06","address":"Bangalore","date_of_birth":"1999-05-26"}'

get:
	curl --location '$(BASE_URL)$(EMPLOYEE_PATH)/1'

get-all:
	curl --location '$(BASE_URL)$(EMPLOYEE_PATH)'

update:
	curl --location --request PUT '$(BASE_URL)$(EMPLOYEE_PATH)' --header 'Content-Type: application/json' --data '{"id":1,"first_name":"Pabitra","last_name":"Jena","age":20,"designation":"Engineer","phone_number":"+91 9999999999","joined_on":"2020-10-06","address":"Odisha","date_of_birth":"1999-05-26"}'

delete:
	curl --location --request DELETE '$(BASE_URL)$(EMPLOYEE_PATH)/1'

get-all-roles:
	curl -X GET $(BASE_URL)$(ROLE_PATH)

get-role:
	curl -X GET $(BASE_URL)$(ROLE_PATH)/$1

create-role:
	curl -X POST $(BASE_URL)$(ROLE_PATH) -H "Content-Type: application/json" -d '{"name":"Admin"}'

update-role:
	curl -X PUT $(BASE_URL)$(ROLE_PATH)/$1 -H "Content-Type: application/json" -d '{"name":"UpdatedRoleName"}'

delete-role:
	curl -X DELETE $(BASE_URL)$(ROLE_PATH)/$1

get-all-users:
	curl -X GET $(BASE_URL)$(USER_PATH) -H 'Content-Type: application/json'

get-user-by-id:
	curl -X GET $(BASE_URL)$(USER_PATH)/1 -H 'Content-Type: application/json'

create-user:
	curl -X POST $(BASE_URL)$(USER_PATH) -H 'Content-Type: application/json' -d '{ "username": "newuser", "password": "password", "role": { "id": 1 } }'

update-user:
	curl -X PUT $(BASE_URL)$(USER_PATH)/4 -H 'Content-Type: application/json' -d '{ "username": "updateduser", "password": "newpassword", "role": { "id": 1 } }'

delete-user:
	curl -X DELETE $(BASE_URL)$(USER_PATH)/2 -H 'Content-Type: application/json'