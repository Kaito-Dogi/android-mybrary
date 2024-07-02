plugins {
  alias(libs.plugins.androidLibrary)
  alias(libs.plugins.hilt)
  alias(libs.plugins.kotlinAndroid)
  alias(libs.plugins.parcelize)
  alias(libs.plugins.serialization)
  id("kotlin-kapt")
}

android {
  namespace = "app.kaito_dogi.mybrary.feature.mybookdetail"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()

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
    jvmTarget = libs.versions.jvmTarget.get()
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
  }
}

dependencies {
  implementation(project(":core:common"))
  implementation(project(":core:designsystem"))
  implementation(project(":core:domain"))
  implementation(project(":core:ui"))

  implementation(platform(libs.androidxComposeBom))
  implementation(libs.androidxComposeMaterial3)
  implementation(libs.androidxComposeUiTooling)
  implementation(libs.androidxComposeUiToolingPreview)
  implementation(libs.androidxHiltNavigationCompose)
  implementation(libs.androidxLifecycleRuntimeCompose)
  implementation(libs.androidxNavigationCompose)
  implementation(libs.coilCompose)
  implementation(libs.hiltAndroid)
  implementation(libs.serialization)

  testImplementation(libs.junit)

  androidTestImplementation(platform(libs.androidxComposeBom))
  androidTestImplementation(libs.androidxTestExtJunitKtx)
  androidTestImplementation(libs.androidxTestEspressoCore)

  kapt(libs.hiltCompiler)
}

kapt {
  correctErrorTypes = true
}
