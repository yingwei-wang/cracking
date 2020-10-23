description = "cracking"

plugins {
    java
    scala
}

repositories {
    mavenCentral()
}

val sparkVersion: String by project

dependencies {
    implementation(group = "org.scala-lang", name = "scala-library", version = "2.11.12")

    compileOnly(group = "org.apache.spark", name = "spark-core_2.11", version = sparkVersion)
    compileOnly(group = "org.apache.spark", name = "spark-sql_2.11", version = sparkVersion)

    testImplementation(group = "junit", name = "junit", version = "4.12")
    testImplementation(group = "org.scalatest", name = "scalatest_2.11", version = "3.0.5")
    testImplementation(group = "org.apache.spark", name = "spark-core_2.11", version = sparkVersion)
    testImplementation(group = "org.apache.spark", name = "spark-sql_2.11", version = sparkVersion)

}
