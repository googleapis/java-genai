# Examples

You can run these examples using:

    git clone https://github.com/googleapis/java-genai.git
    cd java-genai
    mvn clean install -Djacoco.skip=true -DskipTests
    
    cd examples
    export GOOGLE_API_KEY=...
    mvn compile exec:java -Dexec.mainClass="com.google.genai.examples.GenerateContent"

The `jacoco.skip` is required due to [issue #756](https://github.com/googleapis/java-genai/issues/756), and the `skipTests` just makes it faster.

[Issue #801](https://github.com/googleapis/java-genai/issues/801) may avoid having to first `install` in the future.
