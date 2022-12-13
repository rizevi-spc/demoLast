Demo application about book ordering
To run application in intellij ide after project is buid application could be run

![img_3.png](additional_content/readme-img/img_3.png)

For docker after mvn clean package is run docker file can be run with following configuration

![img_4.png](additional_content/readme-img/img_4.png)

integration tests for controllers unit tests for services is present
and could be run from below package:

![img_5.png](additional_content/readme-img/img_5.png)

swagger ui can be found at:

![img.png](additional_content/readme-img/img_6.png)

Postman apis dbdump and api_documentation are 
provided inside project folder additional content:

![img_1.png](additional_content/readme-img/img_1.png)

![img.png](additional_content/readme-img/img.png)

auth access api should be called and then access_token 
and refresh_token variables will be set on collecion level

![img_2.png](additional_content/readme-img/img_2.png)

insert book can be used to insert books (body is ready in collection)

insert customer can be used to insert customer customer_id variable will be set from response

insert order can be used after books and customer are created customer_id will be ready on request body ids inside book 
should be set according to inserted book ids (ids can be seen insert book response after inserted or from get book api all books can be fetched)
after datas are created by insertion apis put and get apis can be called according to inserted data

Field validations will be based on annotated DTO validations can be clearly 

![img.png](additional_content/readme-img/img_7.png)

Validations can be seen on DTO objects like below

![img_1.png](additional_content/readme-img/img_8.png)
