# Agents COMMAND

```powershell
  cp java/*.java .

	javac -cp jade.jar:json.jar *.java

	java -cp mysql-connector.jar:jade.jar:json.jar:classes:. jade.Boot -agents "server:AdministrateurAgent;reception:ReceptionAgent;consulting:ConsultingAgent;nurseryAgent:NurseryAgent"

  rm *.class

```


# link SQL in Java COMMAND

javac  connect.java
java -classpath .:mysql-connector.jar connect 


# Create User in SQL !!!!

USE mysql;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'P@ssW0rd';
GRANT ALL ON *.* TO 'user'@'localhost';
FLUSH PRIVILEGES;

# Create TABLES


create table patients (
  id int,
  name varchar(32),
  cin varchar(32),
  mobile varchar(32) ,
  gender varchar(32),
  dob varchar(32),
  service varchar(32) ,
  email varchar(32),
  address varchar(100),
  date_created timestamp default now()
);

create table visitors (
  id int,
  name varchar(32),
  cin varchar(32),
  mobile varchar(32) ,
  gender varchar(32),
  dob varchar(32),
  service varchar(32) ,
  email varchar(32),
  address varchar(100),
  name2 varchar(100),
  date_created timestamp default now()
);





create table ward (
  idPatient int,
  room varchar(32),
  date_created timestamp default now()
);


create table agents (
  departement varchar(50),
  username varchar(50),
  password varchar(50)
);


INSERT INTO agents (username,password) VALUES("Abdo","0000");

"Administration",
"Consulting",
"Laboratory", 
"Pharmacy", 
Emergency",  
"surgery",   
"surgeon", 
"ward",
"Quarantine",
"BloodDonation"
INSERT INTO patients (id,name) VALUES(101,"Hamza");




INSERT INTO patients (id,name) VALUES(101,"Hamza");
INSERT INTO patients (id,name) VALUES(102,"Ayoub");
INSERT INTO patients (id,name) VALUES(103,"Salma");

INSERT INTO ward (id,room) VALUES(101,"A51");
INSERT INTO ward (id,room) VALUES(102,"A52");
INSERT INTO ward (id,room) VALUES(103,"A53");


# fill query
https://alvinalexander.com/java/java-mysql-insert-example-preparedstatement/



ACLMessage message = new ACLMessage(ACLMessage.INFORM);
message.addReceiver(new AID("server", AID.ISLOCALNAME));
System.out.println("Reception>>Sending to Server ...");
message.setContent(data);
send(message);



"Reception",
"Administration",
"Consulting",
"Laboratory", 
"Pharmacy", 
Emergency",  
"surgery",   
"surgeon", 
"ward",
"Quarantine",
"BloodDonation"



