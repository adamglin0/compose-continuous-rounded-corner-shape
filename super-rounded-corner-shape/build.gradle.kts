import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
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

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
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

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(libs.androidx.annotation)
        }
        val androidxShapeSupportedMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.androidx.graphics.shapes)
                implementation(libs.androidx.collection)
            }
        }
        val webMain by creating {
            dependsOn(commonMain.get())
        }

        jsMain {
            dependsOn(webMain)
        }

        wasmJsMain {
            dependsOn(webMain)
        }

        androidMain {
            dependsOn(androidxShapeSupportedMain)
        }

        jvmMain {
            dependsOn(androidxShapeSupportedMain)
        }

        appleMain {
            dependsOn(androidxShapeSupportedMain)
        }
    }
}

android {
    namespace = "com.adamglin.composecontinuousroundedcornershape"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
        lint.targetSdk = libs.versions.androidTargetSdk.get().toInt()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}


mavenPublishing {
    coordinates(
        groupId = "com.adamglin",
        artifactId = "compose-continuous-rounded-corner-shape",
        version = "1.0.0"
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
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}
