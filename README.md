ElasticSearch Command Helper
=======

### Overview
ElasticSearch Command Helper is an API that aims to facilitate integration with ElasticSearch. It uses annotated Java classes to map index types. This project is based on  [ElasticSearch Java API](http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/) and [ElasticSearch OSEM](https://github.com/kzwang/elasticsearch-osem) (ElasticSearch object search engine mapping).

### Documentation
Visit the wiki page in order to get access to documentation. https://github.com/aureliano/es-cmd-helper/wiki

### Installation
Clone this repository with GIT `git clone https://github.com/aureliano/es-cmd-helper.git` or download source code from release `https://github.com/aureliano/es-cmd-helper/releases/tag/x.x.x`, or even getting the edge source from `https://github.com/aureliano/es-cmd-helper/archive/master.zip`. Extract files and go to project directory. Install locally with Maven by typing `mvn install`.

Unfortunatelly this API is not available at Maven Central Repository yet. So at this point you have to install it manually. After installation from source code all you have to do is add this dependency tag.
```xml
<dependency>
  <groupId>com.github.aureliano</groupId>
  <artifactId>es-cmd-helper</artifactId>
  <version>x.x.x</version>
</dependency>
```

=======
License - [MIT](https://github.com/aureliano/es-cmd-helper/blob/master/LICENSE)
