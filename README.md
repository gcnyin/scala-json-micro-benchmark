# scala-json-micro-benchmark

- circe
- jackson
- upickle

Test command:

```
sbt jmh:run -i 3 -wi 3 -f1 -t1
```

Result:

```
[info] Benchmark                         Mode  Cnt        Score         Error  Units
[info] HelloWorld.circeParse            thrpt    3  1201955.590 ± 3407333.918  ops/s
[info] HelloWorld.circeSerialization    thrpt    3  1313866.384 ±  787559.186  ops/s
[info] HelloWorld.jacksonParse          thrpt    3  1814344.164 ± 1036969.707  ops/s
[info] HelloWorld.jacksonSerialization  thrpt    3  3583824.658 ± 1186229.379  ops/s
[info] HelloWorld.upickleParse          thrpt    3  2376119.572 ±  507319.333  ops/s
[info] HelloWorld.upickleSerialization  thrpt    3  2512859.710 ± 3314032.195  ops/s
```
