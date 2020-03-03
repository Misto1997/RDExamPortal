1- Eureka Server for service discovery.
	~url :- localhost:8761

2- zuul apigateway server for single entry point, security, loggging, tracing etc.
	~url :- localhost:8765

3- admin service for admin to manage exam and student details.
	~url :-localhost:8765/admin/

4- user service for student check his/her dashboard and appear for exam.
	~url :- localhost:8765/user/

5- test service for managing exam.
	~url :- localhost:8765/test/

6- view service collaborating multiple api's use in single api.
	~url :- localhost:8765/view/

7- zipking jar file to run zipkin tracer UI.
	~url :- localhost:9411
	