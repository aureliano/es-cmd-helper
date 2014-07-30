ElasticSearch Command Helper
=======

### Overview
ElasticSearch Command Helper is an API that aims to facilitate integration with ElasticSearch. It uses annotated Java classes to map index types. This project is based on  [ElasticSearch Java API](http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/) and [ElasticSearch OSEM](https://github.com/kzwang/elasticsearch-osem) (ElasticSearch object search engine mapping).

### Stand alone application
If you are only interested in creating or deleting indexes and mappings, just download the [binary distribution](http://1drv.ms/1n3tgxR) (Java 1.6 required),  extract the jars and type 'java -jar es-cmd-helper.jar -h' for help.

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
