In case you want to use your own database you can run the following script to create the tables for it


```sql
 DROP TABLE IF EXISTS `projects_AirHockey`.`scores`;
 CREATE TABLE  `projects_AirHockey`.`scores` (
   `highscore` int(10) unsigned NOT NULL DEFAULT '0',
   `nickname` varchar(45) NOT NULL,
   PRIMARY KEY (`highscore`) USING BTREE
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 ```

and 

```sql
 DROP TABLE IF EXISTS `projects_AirHockey`.`users`;
 CREATE TABLE  `projects_AirHockey`.`users` (
   `username` varchar(255) NOT NULL,
   `password` varchar(255) NOT NULL,
   `email` varchar(255) NOT NULL,
   PRIMARY KEY (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

Make sure to adjust the database.properties file with the credentials of your own database