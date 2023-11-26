import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"

	/** jacoco **/
	id("jacoco")
}

group = "yapp.study"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.0.0")
	runtimeOnly("com.mysql:mysql-connector-j")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
	testImplementation("io.kotest:kotest-assertions-core:5.7.2")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
	testImplementation("io.mockk:mockk:1.13.8")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

noArg {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}


/** jacoco **/
tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	/** when finished test-all */
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		html.required.set(true)
		csv.required.set(false)
		xml.required.set(false)
	}

	classDirectories.setFrom(
		files(
			classDirectories.files.map {
				fileTree(it) {
					exclude(
						"**/*Application*",
						"**/*Config*",
						"**/*Dto*",
						"**/*Request*",
						"**/*Response*",
						"**/*Interceptor*",
						"**/*Exception*",
						"**/Q*.class"
					)
				}
			}
		)
	)

	finalizedBy(tasks.jacocoTestCoverageVerification)
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			// https://docs.gradle.org/current/javadoc/org/gradle/testing/jacoco/tasks/rules/JacocoViolationRule.html#getElement--
			limit {
				minimum = 0.30.toBigDecimal()
			}
		}

		rule {
			enabled = true

			// https://docs.gradle.org/current/javadoc/org/gradle/testing/jacoco/tasks/rules/JacocoLimit.html#getCounter--
			limit {
				counter = "BRANCH"
				value = "COVEREDRATIO"
				minimum = 0.30.toBigDecimal()
			}

			limit {
				counter = "LINE"
				value = "COVEREDRATIO"
				minimum = 0.30.toBigDecimal()
			}
		}
	}
}