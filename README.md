# Prank Generator

This project is about sending pranks to a group of email addresses. We will use a mail robot that creates pranks and send it.
This project was made during a laboratory of a SMTP protocol course at the HEIG-VD school in Switzerland.

## Getting Started

It is better to use a mock server to test the programm first. That is why we put "localhost" as smtp adress server in the config file. But if you want directly be connected to another server, you just have to change this field with, for example "smtp.heig-vd.ch" for the server mail of our school (HEIG-VD).
So you can clone our project named "prank generator" and open it with an IDE like IntelliJ for example. Just run the MailRobot.java.

## Prerequisites

For the mock server that simulates a server smtp on your host machine, go to the tweakers-dev/mockMock on github to get it. Then clone it and execute the MockMock-1.4.0.one-jar.jar like "java -jar MockMock-1.4.0.one-jar.jar -p 25 -h 8282" if you want the server listens on the 25 port number (host 8282) that is the same we use in our config file. By default the mock server runs on the default ports 25 (for SMTP) and 8282 (the web interface). Please note you might need root permissions on some systems to let it listen on port 25.

## Files Description
Here we are going to present you the different classes used by the MailRobot to send the pranks.

- config.properties: specifies the smtp server domain, smtp server port, number of group, number of victims per group
- messages.utf8: contains the different messages that will be sent, each message contains the subject and the body of the mail, in this file each message is separated by the delimiter "==", please use this delimiter if you want to add messages
- victims.utf8: contains the email addresses of victims, one of them will be the sender and the others will be the recipients
- ConfigurationManager.java: contains the tools to parse and get the config files
- Group.java: represents a group of persons
- Mail.java: represents a mail by the sender, the recipients and the body of the mail (including Subject and Datas)
- Person.java: represents a person by his name, his first name and his email address, the Mail Robot just use the email address from persons
- ErrorConfigPropertiesException.java: this exception class represents errors of the config.properties file, it can be possible to get the error message with the "getMessage()" method of the exception
- Prank.java: represents the prank by the message, the list of the recipients and the sender
- PrankGenerator.java: this class is used to generate pranks, by reading the config files, connecting to the smtp server, sending the pranks and disconecting, it uses some recipients and a sender by choosing these elements in the messages.utf8 file and victims.utf8 file depending the config.properties file
- ISmtpClient.java: is the interface of the client smtp that bind it to implement a "sendMail" method, this interface also contains SMTP commands
- SmtpClient.java: represents the SMTP client which implements the interface ISmtpClient, this class allows to send emails
- MailRobot.java: main class that launch the programm by launching the Prank Generator

## Running the tests
If you runned the MailRobot.java, then you can check your mock mock server interface by your browser with the search: localhost:8282.
Then you will see there are three pranks received. That is because we specify in the config.properties file that we want 3 groups of four victims to send different pranks by choosing it randomly.

## Authors
Luca Sivillica & Dany Tchente Simo

## Notes
If you want to change the config.properties file by adding somme victims or groups, be sure that the number of groups and victims per group is coherent. The total number of victims must be a multiple of number of victims per group and there must be victims enough to create the required number of groups, otherwise the program will stop showing an error message.
There is some encoding problems with the subject of the email.
