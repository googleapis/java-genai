# Contributing

The Google Gen AI SDK will accept contributions in the future.

## Running tests

```sh
mvn clean test -Dtest='**/*Test' -Djacoco.skip=true
```

## JDK version

The tests may fail if you use the wrong JDK version. See the currently supported JDK versions in [.github/workflows/unit-tests.yml].

To run the tests with a specific JDK version, which you must have installed, set the `JAVA_HOME` environment variable. For example:

```sh
# Run with JDK 21
JAVA_HOME=$(/usr/libexec/java_home -v 21) mvn clean test -Dtest='**/*Test' -Djacoco.skip=true
```
