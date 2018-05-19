# Transaction Handler

CLI to handle JSON objects representing a user transaction that includes *user_id*, *amount*, *date*, *description* and *transaction_id*.

Current implementation stores transactions as JSON object strings in the file system at `path/to/jar/data/transactions.txt`.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

- Java 8+

#### SDKMAN

Java can be installed via [SDKMAN](http://sdkman.io/install.html), which is a management tool for technologies related to the Java Virtual Machine.

#### Java 8

```shell
sdk install java 8u151-oracle
```

#### Set up JAVA_HOME

```shell
export JAVA_HOME=~/.sdkman/candidates/java/current
```

### Building and Executing the CLI

1. Clone the repository

   ```shell
   $ git clone https://github.com/jovannypcg/transaction-handler.git && cd transaction-handler
   ```

2. Build the *jar* file

   ```shell
   $ ./gradlew clean build
   ```

   *.jar* file is generated at `build/libs/transaction-handler-1.0.0.jar`

3. Execute the application as described in [Usage](#usage)

   ```shell
   $ java -jar build/libs/transaction-handler-1.0.0.jar 154 list
   ```

### Testing

Tests are executed when running the `build` task, but they can be independently run by typing

```shell
$ ./gradlew clean test
```

## Usage

```shell
$ java -jar path/to/jar/transaction-handler.jar <user_id> [transaction_id] [sum] [list] [add <transaction_json>]
```

### Add a new transaction 

```shell
$ java -jar transaction-handler.java 245 add "{\"date\":\"04/24/2014\",\"description\":\"Simple transaction to be saved\",\"amount\":43.53}"

{"userId":245,"date":"04/24/2014","description":"Simple transaction to be saved","amount":43.53,"transactionId":"e9fff5f9-c7d2-48bb-9369-1c9e5acbaf28"}
```

### List Transactions of a Given User ID

```shell
$ java -jar transaction-handler.jar 154 list

{"userId":154,"date":"02/14/2017","description":"Coffee Shop","amount":43.53,"transactionId":"0a80ed4b-6662-4d7d-a6df-fed727900399"}
{"userId":154,"date":"04/01/2018","description":"Netflix payment","amount":43.53,"transactionId":"e9fff5f9-c7d2-48bb-9369-1c9e5acbaf28"}
```

### Show a Single Transaction

```shell
$ java -jar transaction-handler.jar 154 50b8ec6c-20af-41cd-a47f-024d3926f6ee

{"userId":154,"date":"24/04/2014","description":"Simple transaction to be saved","amount":43.53,"transactionId":"50b8ec6c-20af-41cd-a47f-024d3926f6ee"}
```

### Add Amounts of a Given User

```shell
$ java -jar transaction-handler.jar 154 sum

{"userId":154,"sum":261.18}
```

## Author

- **Jovanny Cruz** - [jovannypcg](https://github.com/jovannypcg)

