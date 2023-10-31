Apache Avro example project in Java.

The project follows the tutorial at https://avro.apache.org/docs/current/getting-started-java/.

## Avro

Apache Avro is a data serialization system.

[Advantages of Avro](https://cloud.google.com/bigquery/docs/loading-data-cloud-storage-avro#advantages_of_avro):

> Avro is the preferred format for loading data into BigQuery. 
Loading Avro files has the following advantages over CSV and JSON (newline delimited):
>
> - The Avro binary format:
>   - It Is faster to load. The data can be read in parallel, even if the data blocks are [compressed](https://cloud.google.com/bigquery/docs/batch-loading-data#loading_compressed_and_uncompressed_data).
>   - It Doesn't require typing or serialization.
>   - It Is easier to parse because there are no encoding issues found in other formats such as ASCII.
> - When you load Avro files into BigQuery, the table schema is automatically retrieved from the self-describing source data.

## Requirements

* Java 20
* Maven 3.9.3

## Build

```bash
mvn clean package
```

It creates `example.avro.User` class from `src/main/avro/user.avsc` and compiles the project.

![img.png](img/file-structure.png)