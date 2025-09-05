plugins {
    alias(libs.plugins.android.library )
    alias(libs.plugins.mavenpublish )
    alias(libs.plugins.kotlin.android )
    alias(libs.plugins.hilt.android) apply false
}

android {
    namespace = "com.lhh.meshapisdk"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        lint.targetSdk = 36
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}
dependencies {

    implementation(libs.log)
    implementation(libs.scanner)
// Android BLE Library
    implementation(libs.ble)
    implementation(libs.hilt.android)
    // Spongycastle - Android implementation of Bouncy Castle
    implementation (libs.core)
    implementation (libs.prov)
    implementation (libs.gson)

    // Room - Lets keep this as it is so that we don't have to increase the minSdk version
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    androidTestImplementation(libs.room.testing)

    annotationProcessor(libs.hilt.android.compiler)
    annotationProcessor(libs.hilt.compiler)
}
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
//                from(components["release"]) // 这里的 release 是 buildType
                groupId = "com.github.LaiHuanHeng"
                artifactId = "sdk"
                version = "BleMeshApiV0.0.3"
                // 指向你本地的 AAR 文件
                artifact("$projectDir/libs/MeshApi-0.0.3-release.aar") {
                    extension = "aar"
                }
            }
        }
    }
}
