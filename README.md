# scala-json-micro-benchmark

- circe
- jackson
- upickle
- json4s
- spray
- zio-json
- jsoniter

Test command:

```
sbt jmh:run -i 3 -wi 3 -f1 -t1
```

Result see [wiki](https://github.com/gcnyin/scala-json-micro-benchmark/wiki).
