# TextToKeepass
A small java program to configure a text document containing usernames and passwords into a format able to imported into a CSV format which can then be imported into Keepass

## Description
The application converts entries in a .txt file of the format 
```
Account Title 
Username 
Password
Website (optional)
Comments (optional)
```

into a new .txt file of the format

```
"Account Title","Username","Password","Website (optional)", "Comments (optional)"
```
Which can then be easily read into Excel or other CSV editors. 

### Keepass info
Information on importing data into Keepass can be found [here.](http://keepass.info/help/base/importexport.html)
