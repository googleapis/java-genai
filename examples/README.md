# Examples

You can run these examples using:

    git clone https://github.com/googleapis/java-genai.git
    cd java-genai
    mvn clean install -DskipTests

    cd examples
    export GOOGLE_API_KEY=...
    mvn compile exec:java -Dexec.mainClass="com.google.genai.examples.GenerateContent"

The `skipTests` just makes `install` faster.

[Issue #801](https://github.com/googleapis/java-genai/issues/801) may avoid having to first `install` in the future.
