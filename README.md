# line-server-problem
[Line Server Problem](https://salsify.github.io/line-server.html)
# Build and Run
```
sh build.sh
```
```
sh run.sh
```
# How does your system work? 
Using Java Spring Boot to build the server. It includes one endpoint which lives at http://localhost:8080/. 
The endpoint includes a GET method to retrieve a line from a file stored on the server. 
For example GET http://localhost:8080/ 4 you will get line 4 of the file
.I've implemented a singleton class to store the offset at the start of each line, having this information 
allows me to get the start byte index and end byte index for each line, therefore I can retrieve
the right line from the file.
The time complexity to retrieve a line is constant.
# How will your system perform with a 1 GB file? a 10 GB file? a 100 GB file?
The system should be able to function with all file sizes. 
If the file is large enough and the byte-indexes that are stored exceeds the memory available, 
we can change the way how to generate those indexes, we can generate one byte-index every 10 or 100 lines,
which will slow down the process each time retrieving a line from file as we need to go through 10 or 100 lines each time.
# How will your system perform with 100 users? 10000 users? 1000000 users?
Ideally, the system would handle incoming requests in parallel.  
But it greatly depends on how it would be deployed.
I think the system will be able to handle scaling up since there isn't heavy computation on each request.
# What documentation, websites, papers, etc did you consult in doing this assignment?
1. https://docs.spring.io
2. https://stackoverflow.com
3. Postman for testing.
# What third-party libraries or other tools does the system use? How did you choose each library or framework you used?
I choose to use Java Spring Boot to build the server as Java is a recommended language and I'm very familiar with it.
To deal with file read problem, I've compared many Java libraries and ended up using RandomAccessFile as 
the seek() function is the fast method to read a file from a given offset.
# How long did you spend on this exercise? If you had unlimited more time to spend on this, how would you spend it and how would you prioritize each item?
If I had unlimited time:
1. I would definitely spend more time writing unit tests and doing more tests, especially performance tests 
with a large file and a huge amount of users.
2. There is still more to do to make the system perform better. 
For example, we can add some temporary cache of lines that users are commonly requesting, which will reduce the number of file reads.
# If you were to critique your code, what would you have to say about it?
It's just like a demo project and definitely there are more things to do to make it better. 
