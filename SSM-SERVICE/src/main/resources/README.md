Exception in thread "main" java.lang.NoClassDefFoundError: org/apache/curator/RetryPolicy

缺少依赖

添加依赖

```
<dependency>
    <groupId>org.apache.curator</groupId>
    <artifactId>curator-framework</artifactId>
    <version>4.0.1</version>
</dependency>
```