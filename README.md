
# Simple Network File Server  
  
  The server has a list of available text files (**.txt**) that can be downloaded by multiple clients.  
   
## Prerequisites  
  ### Default Socker Configuration
  Below are some constant configure on socket
  | Variable                | Description                                             |
| ----------------------- | ------------------------------------------------------- |
| SERER_PORT| localhost (127.0.0.1)                                  |
| SERVER_PORT| 7777                                  |
  
 ### Location server and client program
 >Server:  /out/artifacts/simplefileserver_jar          
 >Client:  /out/artifacts/simplefileclient_jar                         
 ### File Directory
 You have to configure **fileDir** as required argument when starting server  
 eg: file directory as D drive on Windows machine
```text  
java -jar simplefileserver.jar "fileDir=D:\\"  
```  
## Usage  
Main usage to start server
```shell
$ java -jar simplefileserver "fileDir=[DIR]"

DIR: file directory which server manages
```
Main usage to start client
```shell
$ java -jar simplefileclient
```
Some common usage  on client
```bash  
# List all available text files on server  
index  
  
# Get specific file  
# please note if exist file, 'ok' message will be followed with the content of the file  
# otherwise, "error" message indicates that the specified file does not exist on the server  
get [file-name]  
```