Demo application about book ordering
To run application in intellij ide after project is buid application could be run
![img_3.png](img_3.png)
For docker after mvn clean package is run docker file can be run with following configuration
![img_4.png](img_4.png)

Postman apis are provided:
![img_1.png](img_1.png)
![img.png](img.png)
auth access api should be called and then access_token and refresh_token variables will be set on collecion level
![img_2.png](img_2.png)

insert book can be used to insert books (body is ready in collection)

insert customer can be used to insert customer customer_id variable will be set from response

insert order can be used after books and customer are created customer_id will be ready on request body ids inside book 
should be set according to inserted book ids (ids can be seen insert book response after inserted or from get book api all books can be fetched)
after datas are created by insertion apis put and get apis can be called according to inserted data
