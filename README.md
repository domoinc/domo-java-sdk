<div align="center">
  <img src="domo.png" width="400" height="400"/>
</div>

# Java - Domo API SDK
[![Download](https://api.bintray.com/packages/domoinc/domo-java-sdk/domo-java-sdk/images/download.svg) ](https://bintray.com/domoinc/domo-java-sdk/domo-java-sdk/_latestVersion)
[![License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](http://www.opensource.org/licenses/MIT)
[![CircleCI](https://circleci.com/gh/domoinc/domo-java-sdk.svg?style=svg)](https://circleci.com/gh/domoinc/domo-java-sdk)
[![Coverage Status](https://img.shields.io/coveralls/domoinc/domo-java-sdk.svg?style=flat)](https://coveralls.io/r/domoinc/domo-java-sdk?branch=master)

### About

* The Domo API SDK is the simplest way to automate your Domo instance
* The SDK streamlines the API programming experience, allowing you to significantly reduce your written code
* This package is published to [bintray jcenter](https://bintray.com/domoinc/domo-java-sdk/domo-java-sdk/_latestVersion)

### Features:
- DataSet and Personalized Data Policy (PDP) Management
    - Use DataSets for fairly static data sources that only require occasional updates via data replacement
    - Add Personalized Data Policies (PDPs) to DataSets (hide sensitive data from groups of users)
    - Docs: https://developer.domo.com/docs/domo-apis/data
- Stream Management
    - A Domo Stream is a specialized upload pipeline pointing to a single Domo DataSet
    - Use Streams for massive, constantly changing, or rapidly growing data sources
    - Streams support accelerated uploading via parallel data uploads
    - Docs: https://developer.domo.com/docs/domo-apis/stream-apis
- User Management
    - Create, update, and remove users
    - Major use case: LDAP/Active Directory synchronization
    - Docs: https://developer.domo.com/docs/domo-apis/users
- Group Management
    - Create, update, and remove groups of users
    - Docs: https://developer.domo.com/docs/domo-apis/group-apis
- Account Management
    - Create, update, and remove accounts
    - Docs: https://developer.domo.com/docs/domo-apis/accounts-api-reference

### Setup

<!---
[![JCenter](https://img.shields.io/bintray/v/checketts/domo-java-sdk/domo-java-sdk.svg?label=jcenter)](https://bintray.com/checketts/domo-java-sdk/domo-java-sdk/_latestVersion)
[![Maven Central](https://img.shields.io/maven-central/v/com.domo/domo-java-sdk.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.domo/domo-java-sdk)
-->

The SDK can be added to your project in three ways:

It is currently hosted via bintray and JCenter (It will be in Maven central eventually):
```groovy
repositories {
    maven {
        url  "http://dl.bintray.com/domoinc/domo-java-sdk"
    }
}
```

Maven:

```xml
<dependency>
  <groupId>com.domo</groupId>
  <artifactId>domo-java-sdk-all</artifactId>
  <version>0.4.0</version>
</dependency>
```

Gradle:

```groovy
compile 'com.domo:domo-java-sdk-all:0.4.0'
```

Classic Jar Import:
- Clone this repository
- Using a Bash Terminal, navigate to the cloned repository folder
- Create the Jar files via the Bash command `./gradlew publishToMavenLocal`
- The Jars will be located in `domo-java-sdk-all/build/libs/`
- Copy the Jars to your project folder, and add them to your build path

### Usage
* See the [Client Test File](https://github.com/domoinc/domo-java-sdk/blob/master/domo-java-sdk-all/src/test/java/com/domo/sdk/ClientTest.java) for full usage and examples.
* Create an API Client on the [Domo Developer Portal](https://developer.domo.com/)
* Use your API Client id/secret to instantiate a DomoClient()
* Multiple API Clients can be used by instantiating multiple Domo Clients
* Authentication with the Domo API is handled automatically by the SDK
* If you encounter a 'Not Allowed' error, this is a permissions issue. Please speak with your Domo Administrator.

```java
public class Example {

    public void domoSDKUsage() {

        //Build an SDK configuration
        Config config = Config.with()
                .clientId("MY_CLIENT_ID")
                .clientSecret("MY_CLIENT_SECRET")
                .apiHost("api.domo.com")
                .useHttps(true)
                .scope(USER, DATA)
                .httpLoggingLevel(HttpLoggingInterceptor.Level.BODY)
                .build();

        //Create an instance of the SDK Client
        DomoClient domo = DomoClient.create(config);

        //Manage DataSets
        DataSetClient datasets = domo.dataSetClient();
        datasets.create();

        //Manage Streams
        StreamClient streams = domo.streamClient();
        streams.create();

        //Manage Users
        UserClient users = domo.userClient();
        users.create();

        //Manage User Groups
        GroupClient groups = domo.groupClient();
        groups.create();
    }
}
```

##### Snapshots

You can use snapshot versions through [JitPack](https://jitpack.io):

* Go to [JitPack project page](https://jitpack.io/#domoinc/domo-java-sdk)
* Select `Commits` section and click `Get it` on commit you want to use (top one - the most recent)
* Follow displayed instruction: add repository and change dependency (NOTE: due to JitPack convention artifact group will be different)

[![java lib generator](http://img.shields.io/badge/Powered%20by-%20Java%20lib%20generator-green.svg?style=flat-square)](https://github.com/xvik/generator-lib-java)
