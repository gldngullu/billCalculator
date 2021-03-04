# Bill Calculator

The application can be run through the BillCalculatorApplication and runs on the port 8090 which is configured in 'application.properties'.

Goal of the application is to calculate the total cost of a bill. To demonstrate, some example bills are included in the project. 

After running, endpoints can be found in the address http://localhost:8090/swagger-ui/#/bill-calculator-controller.
The application has two endpoints. getExamples endpoint creates example bill objects and calculateBill endpoint calculates the total payable amount of the bills.
After executing the getExamples endpoint, there will be 4 example bills returned. Any of them can be used to execute calculateBill endpoint.
Copying one of the bills to the second endpoint and executing, there will be the bill with calculated costs returned. The bill will include cost, discount and net cost. 

The unit tests are included in the BillCalculatorServiceTest. The tests can be run from this file. 

By running the tests from Maven Lifecycle, code coverage report is generated in target folder named as “jacoco.exe”. Coverage report can be viewed afterwards. 

Project can be build from the command line using maven command “mvn spring-boot:run”.



