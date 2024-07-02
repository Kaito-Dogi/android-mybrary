import java.util.Properties

plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.hilt)
  alias(libs.plugins.kotlinAndroid)
  id("kotlin-kapt")
}

android {
  namespace = "app.kaito_dogi.mybrary"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    applicationId = "app.kaito_dogi.mybrary"
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = libs.versions.versionCode.get().toInt()
    versionName = libs.versions.versionName.get()

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
    }

    debug {
      applicationIdSuffix = ".debug"
      isDebuggable = true
    }

    create("mock") {
      initWith(getByName("debug"))
      applicationIdSuffix = ".mock"
      matchingFallbacks += listOf("debug")
    }
  }

  val localProperties = rootProject.file("./secrets.properties")
  val (supabaseUrl, supabaseKey) = if (localProperties.exists()) {
    val properties = Properties().apply { load(localProperties.inputStream()) }
    properties.getProperty("SUPABSE_URL_PROD", "") to properties.getProperty("SUPABSE_KEY_PROD", "")
  } else {
    System.getenv("SUPABSE_URL_PROD") to System.getenv("SUPABSE_KEY_PROD")
  }

  flavorDimensions += "env"
  productFlavors {
    create("prod") {
      dimension = "env"

      buildConfigField(
        type = "String",
        name = "SUPABASE_URL",
        value = supabaseUrl,
      )
      buildConfigField(
        type = "String",
        name = "SUPABASE_KEY",
        value = supabaseKey,
      )
    }

    create("dev") {
      applicationIdSuffix = ".dev"
      dimension = "env"
      isDefault = true

      buildConfigField(
        type = "String",
        name = "SUPABASE_URL",
        value = supabaseUrl,
      )
      buildConfigField(
        type = "String",
        name = "SUPABASE_KEY",
        value = supabaseKey,
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
    buildConfig = true
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
  }
}

dependencies {
  implementation(project(":core:config"))
  implementation(project(":core:data"))
  implementation(project(":core:designsystem"))
  implementation(project(":core:domain"))
  implementation(project(":core:supabase"))
  implementation(project(":feature:mybooklist"))
  implementation(project(":feature:mybookdetail"))
  implementation(project(":feature:searchbook"))

  implementation(platform(libs.androidxComposeBom))
  implementation(libs.androidxNavigationCompose)
  implementation(libs.hiltAndroid)
  implementation(libs.material)

  kapt(libs.hiltCompiler)
}

// TODO: core:data モジュール実装後に削除
kapt {
  correctErrorTypes = true
}
