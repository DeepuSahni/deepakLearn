## Suggest Words For Number

### Overview
- A command line application that takes in phone numbers and prints dictionary word suggestions.

### Technologies
- Java 8
- Maven 3

### Code Analysis
- PMD
- FindBug

### How To Build And Run
1. Build `./build.sh`
3. Run Application. 
* Interactive mode: `java -jar target/suggest-1.0-RELEASE.jar`
* With user dictionary: `java -Ddictionary=userDictionary.txt -jar target/suggest-1.0-RELEASE.jar`
* With file input: `java -Ddictionary=userDictionary.txt -jar target/suggest-1.0-RELEASE.jar sampleNumbers.txt`

### Troubleshooting
1. mvn: command not found?
  - Please install maven. Won't tell how :)
2. Cannot clone. Git says host not found?
  - Please check your proxy settings.
  - You may need to export these variables into environment:
    ```
    export HTTP_PROXY="<your url here>"
    export HTTPS_PROXY=$HTTP_PROXY
    export http_proxy=$HTTP_PROXY
    export https_proxy=$HTTP_PROXY
    export NO_PROXY=“<your url here>”
    export no_proxy=$NO_PROXY
    ```
2. Maven build error?
  - This might be because of maven failing to find the JAVA_HOME.
  - Please export your JAVA_HOME. `export JAVA_HOME=<your java home>`
3. Cannot download maven dependencies?
  - Please check the proxy settings for maven
  - You can add proxies for maven in your  settings file `~/.m2/settings.xml`.
  - You may not need username and password in proxy elements.
  ```
  <proxy>
    <id>http8080</id>
    <active>true</active>
    <protocol>http</protocol>
    <host>your host here</host>
    <port>8080</port>
    <username></username>
    <password></password>
    <nonProxyHosts>your host|localhost|home</nonProxyHosts>
  </proxy>
  ```
  
### IntelliJ : View Code 
1. Click 'Import Project'
2. Navigate to `pom.xml` in any of the projects.
3. Click 'Open'
4. Check 'Import Maven projects automatically'.
5. Keep hitting 'Next' and then 'Finish'. 

### Future Improvements
- Add feature: print application output to file. Will be handy for larger input files.
