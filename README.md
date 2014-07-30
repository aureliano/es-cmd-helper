ElasticSearch Command Helper
=======

### Overview
ElasticSearch Command Helper is an API which make easier to interact with some ElasticSearch features. It uses annotated Java classes to map index types in ElasticSearch. This project is based on two Java APIs. The first one is [ElasticSearch Java API](http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/). And the second one is [ElasticSearch OSEM](https://github.com/kzwang/elasticsearch-osem) which is used as an object search engine mapping for ElasticSearch.

### Stand alone application
If you are interested just in pontual commands like create or delete indices, create, read or delete mapping you can just download binary file. It's required you have Java 1.6 or higher installed. Download binary file on [application](http://1drv.ms/1n3tgxR) and extract files. Type `java -jar es-cmd-helper.jar -h` and if everything is ok you'll see a help banner on console.

### Documentation
Visit the wiki page in order to get access to documentation. https://github.com/aureliano/es-cmd-helper/wiki

### Installation
#### Source
Clone this repository with GIT `git clone https://github.com/aureliano/es-cmd-helper.git` or download source code from release `https://github.com/aureliano/es-cmd-helper/releases/tag/x.x.x`. Extract files and go to project directory. Install locally with Maven by typing `mvn install`.

#### Binary - Maven
This API is not available at Maven Central Repository yet. Although you can install it manually by copying the artifacts es-cmd-helper-x.x.x.jar es-cmd-helper-x.x.x-sources.jar to `$HOME/.m2/repository/com/github/aureliano/es-cmd-helper/x.x.x/`. So in your POM you have just to add this dependency tag.
```xml
<dependency>
  <groupId>com.github.aureliano</groupId>
  <artifactId>es-cmd-helper</artifactId>
  <version>x.x.x</version>
</dependency>
```

=======
License - [MIT](https://github.com/aureliano/es-cmd-helper/blob/master/LICENSE)
