import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

class RssReaderBotPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")

        val kotlinVersion: String by project

        dependencies {
            "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
            "implementation"("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
            "implementation"("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinCoroutines")

            "testImplementation"(junitApi)
            "testRuntimeOnly"(junitEngine)
        }

        repositories {
            jcenter()
            maven { url = uri("https://dl.bintray.com/heapy/heap-dev") }
        }

        tasks.withType<KotlinJvmCompile> {
            kotlinOptions {
                jvmTarget = kotbotJvmTarget
                freeCompilerArgs = freeCompilerArgs + listOf("-progressive")
            }
        }

        tasks.withType<JavaCompile> {
            sourceCompatibility = kotbotJvmTarget
            targetCompatibility = kotbotJvmTarget
        }

        Unit
    }
}