import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.jetbrains.kotlin.jvm") version "1.3.21"
	id("org.jetbrains.kotlin.kapt") version "1.3.21"
	id("org.springframework.boot") version "2.1.4.RELEASE" apply false
	id("org.jetbrains.kotlin.plugin.spring") version "1.3.21" apply false
	id("com.gorylenko.gradle-git-properties") version "1.5.1" apply false
}



allprojects {
	repositories {
		jcenter()
	}
}

subprojects {
	apply(plugin = "kotlin") // 요부분을 apply { plugin("kotlin")} -> apply(plugin="kotlin")
	apply(plugin = "kotlin-kapt")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "com.gorylenko.gradle-git-properties")

	group = "com.lyj.ddalivery"
	version = "0.0.1"

	dependencies {
		compile("com.fasterxml.jackson.module:jackson-module-kotlin")
		compile("org.jetbrains.kotlin:kotlin-reflect")
		compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		compile("org.springframework.boot:spring-boot-starter-logging")

		/**
		 * @see <a href="https://kotlinlang.org/docs/reference/kapt.html">Annotation Processing with Kotlin</a>
		 */

		kapt("org.springframework.boot:spring-boot-configuration-processor")
		compileOnly("org.springframework.boot:spring-boot-configuration-processor")

		testCompile("org.springframework.boot:spring-boot-starter-test")
	}

	tasks {
		compileKotlin {
			kotlinOptions {
				freeCompilerArgs = listOf("-Xjsr305=strict")
				jvmTarget = "1.8"
			}
			dependsOn(processResources) // kotlin 에서 ConfigurationProperties
		}


		compileTestKotlin {
			kotlinOptions {
				freeCompilerArgs = listOf("-Xjsr305=strict")
				jvmTarget = "1.8"
			}
		}
	}
}

project("ddalivery-api"){
	dependencies {
		compile(project(":ddalivery-common"))

		compile("de.codecentric:spring-boot-admin-starter-client:2.1.4")
		compile("org.springframework.boot:spring-boot-starter-web")
		compile("org.springframework.boot:spring-boot-starter-security")
		compile("org.springframework.boot:spring-boot-starter-actuator")

		runtime("org.springframework.boot:spring-boot-devtools")
	}
}
