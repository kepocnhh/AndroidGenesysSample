repositories {
    mavenCentral()
    google()
}

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("29.0.3")

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        applicationId = "test.android.genesys"
        versionCode = 1
        versionName = "0.0.1"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".$name"
            versionNameSuffix = "-$name"
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    applicationVariants.all {
        outputs.forEach { output ->
            check(output is com.android.build.gradle.internal.api.ApkVariantOutputImpl)
            output.versionCodeOverride = versionCode
            output.outputFileName = "$applicationId-$versionName-$versionCode.apk"
        }
    }
}

dependencies {
    implementation(
        group = "com.genesys.gms.android",
        name = "sdk",
        version = "8.5.201.00.rev.65d2ee0"
//        version = "8.5.201.00.rev.8453f22"
    )
}
