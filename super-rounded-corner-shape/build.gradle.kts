import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.dokka)
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.binaryCompatibilityValidator)
}

group = "com.adamglin"
version = "1.0.5"

val artifactId = "compose-continuous-rounded-corner-shape"

kotlin {
    androidLibrary {
        namespace = "com.adamglin.composecontinuousroundedcornershape"
        compileSdk = libs.versions.androidCompileSdk.get().toInt()

        minSdk = libs.versions.androidMinSdk.get().toInt()
        lint.targetSdk = libs.versions.androidTargetSdk.get().toInt()

        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosX64()
    macosArm64()

    js(IR) {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.androidx.graphics.shapes)
            implementation(libs.androidx.collection)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(libs.androidx.annotation)
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = group.toString(),
        artifactId = artifactId,
        version = version.toString()
    )
    pom {
        name.set("compose-continuous-rounded-corner-shape")
        description.set("draw a continuous roundedCornerShape in compose multiplatform.")
        url.set("https://github.com/adamglin0/compose-continous-rounded-corner-shape")
        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                name.set("adamglin")
                email.set("dev@adamglin.com")
            }
        }
        issueManagement {
            system.set("Github")
            url.set("https://github.com/adamglin0/compose-continous-rounded-corner-shape/issues")
        }
        scm {
            connection.set("https://github.com/adamglin0/compose-continous-rounded-corner-shape.git")
            url.set("https://github.com/adamglin0/compose-continous-rounded-corner-shape")
        }
    }
    publishToMavenCentral(true)
    signAllPublications()
}
