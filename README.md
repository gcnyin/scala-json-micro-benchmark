# scala-json-micro-benchmark

- circe
- upickle

Test command:

```
sbt jmh:run -i 4 -wi 4 -f1 -t1
```

Result:

```
[info] Benchmark                         Mode  Cnt        Score        Error  Units
[info] HelloWorld.circeParse            thrpt    4  1581107.584 ±  31360.811  ops/s
[info] HelloWorld.circeSerialization    thrpt    4  1488287.974 ± 228850.861  ops/s
[info] HelloWorld.upickleParse          thrpt    4  2359793.906 ± 308907.237  ops/s
[info] HelloWorld.upickleSerialization  thrpt    4  2582518.386 ± 795138.193  ops/s
```
