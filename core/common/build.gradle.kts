plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  id("kotlin-kapt")
  alias(libs.plugins.hilt)
}

android {
  namespace = "app.kaito_dogi.mybrary.core.common"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = "17"
  }
}

dependencies {
  implementation(libs.hilt.android)

  kapt(libs.hilt.compiler)
}

kapt {
  correctErrorTypes = true
}
