CS731A  -  Blockchain Technology and Applications
-----------------------------------------------------------
Project Submitted by Group no 9
Title: Publisher-Subscriber-Event_Queue-for-Ethereum-Smart-Contracts

Group members
1) Anurag Kamal(22111010 , akamal22@iitk.ac.in)
2)  Ashitosh More (21111017, ashitoshvm21@iitk.ac.in)
3) Shivam Kharwar (21111058, skharwar21@iitk.ac.in)
-----------------------------------------------------------------------------
Requirements and Dependencies
----------------------------------------------------------------------------
1.Apache Kafka Along with Zookeeper <br />
2.MongoDB Atlas <br />
3.Java 17 or above <br />
4.Springboot Framework <br />
5.Maven Dependencies like SpringWeb MVC, Apache Kafka, MongoDB <br />
6.Ngrok <br />
7.Metamask Account with sufficient Sepolia Ether and Seploia LINK for testing on Sepolia TestNet <br />
8.ChainLink.sol <br />
>>>>>Requirement and Dependencies Setup >>>>> 
>>>>>--------------------------------------------------
1.Run Zookeeper using command ".\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties"
for windows from the kafka folder. ("/bin/zookeeper-server-start.sh /config/zookeeper.properties" for linux) <br />

2.Run Kafka Server using command ".\bin\windows\kafka-server-start.bat .\config\server.properties" 
for windows from the kafka folder. ("/bin/kafka-server-start.sh /config/server.properties" for linux) <br />

3.Add Current System IP in mongoDB atlas on mongoDB Atlas website under connect tab and update its url in
"application.properties" file in EventQueue project. <br />

4.Run ngrok and bind the "localhost:8080" to a global ip using command "ngrok.exe 8080" on ngrok terminal. <br />

>>>>
>>>>  Steps to Run -> >>>>>>
>>>>-----------------------------------------------------------------------

1.Start the EventQueue Project in IntelliJ IDEA(preferred) or any other IDE. <br />

2.Start Solidity IDE (preferred Remix) and complie and deploy the smart contracts "'GetMethodAPI.sol", "PostMethodAPI.sol" and "Demo.sol". <br />

3.Open "Demo.sol" deployed contract. <br />

4.Set the deployed address of PostMethodAPI contract using "setAddrPost" method. <br />

5.Set the deployed address of GetMethodAPI contract using "setAddrGet" method. <br />

6.Fund the Deployed "GetMethodAPI.sol" and 'PostMethodAPI.sol" contracts with 'LINK' using metamask account
(0.1 LINK per get or post request, refer to official documentation of ChainLink). <br />

7.Set the parameter of "postRequest(url,user_id,topic_id,msg)" method for publishing data where 'url' is appened
as the '{url}/rest/api/publish?' which we got from ngrok. (Referf to Offical Documentation of ChainLink for any issue) <br />

8.Transact "postRequest" Method and wait for few seconds and call "postMessage" to fetch output of the request. <br />

9.Set the parameter of "getRequest(url,user_id,topic_id,path)" method for publishing data where 'url' is appened as the
'{url}/rest/api/view?' which we got from ngrok and {path} is the address of variable for which you want to get value.
(Referf to Offical Documentation of ChainLink for any issue) <br />

10.Transact "getRequest" Method and wait for few seconds and call "getMessage" to fetch output of the request. <br />

11.See the output of postRequest in postResponse variable and getRequest in getResponse variable. <br />

12.Reset Contract using reset method and use again with new Parameters. <br />

