plugins {
    id("java")
}

group = "com.petermassmann"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains:annotations:24.0.0")
    implementation("com.mysql:mysql-connector-j:8.0.33")
    implementation("org.apache.commons:commons-dbcp2:2.10.0")
}

tasks.test {
    useJUnitPlatform()
}
