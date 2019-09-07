# Simple Network File Server

 The server has a list of available text files (**.txt**) that can be downloaded by multiple clients.
 
## Prerequisites

 ### File Directory
You have to configure **fileDir** as required argument when starting server

```text
jar ***
```
## Usage
Some common usage
```bash
# List all available text files on server
index

# Get specific file
# please note if exist file, 'ok' message will be followed with the content of the file
# otherwise, "error" message indicates that the specified file does not exist on the server
get [file-name]
```