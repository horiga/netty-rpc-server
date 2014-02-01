# http-rpc-server
* netty 4.0.15
* thrift 0.9.0

```xml
  <dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-all</artifactId>
    <version>4.0.15.Final</version>
  </dependency>
  <dependency>
    <groupId>org.apache.thrift</groupId>
    <artifactId>libthrift</artifactId>
    <version>0.9.0</version>
  </dependency>
```

## setup
### Mac OX
Install `thrift`

```
brew install thrift
```

## Generate thrift message source file
```
cd src/main/message/rpc/thrift
thrift --out ../../../java --gen java sample.thrift
```





