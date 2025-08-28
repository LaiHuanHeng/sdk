plugins {
    alias(libs.plugins.android.library )
    alias(libs.plugins.mavenpublish )
    alias(libs.plugins.kotlin.android )
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

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"]) // 这里的 release 是 buildType
                groupId = "com.github.LaiHuanHeng"
                artifactId = "sdk"
                version = "BleMeshApiV1.0.0"
                // 指向你本地的 AAR 文件
                artifact("$projectDir/libs/MeshApi-1.0.0-release.aar") {
                    extension = "aar"
                }
            }
        }
    }
}
