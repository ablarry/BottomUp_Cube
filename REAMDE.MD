# BUC: Computing Iceberg Cubes from the Apex Cuboid Downward

BUC is an algrithm for the computation of sparse and iceberg cubes.
BUC allows to share data partitioning costs. This proccessing order also allows BUC to prune during
construction, using the Apriori property.

## Paper reference
* [Bottom-up computation of sparse and Iceberg CUBE](https://dl.acm.org/doi/abs/10.1145/304182.304214)

### Requirements:
* [Java](https://www.java.com/)
* [Maven](https://maven.apache.org/)

### Compile
```
mvn clean install
```

### Test
```
mvn test
```