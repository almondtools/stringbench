if exist benchmarks.jar del benchmarks.jar
if exist target\benchmarks.jar copy target\benchmarks.jar .
if exist benchmarks.jar java -jar benchmarks.jar -rf csv -rff result.csv -o result.txt %*