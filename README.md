
<div align="center">
  
# Welcome to my Kotlin Compose Multiplatform Playground

## This project uses:

[![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?logo=kotlin)](https://kotlinlang.org/docs/multiplatform.html)
[![Compose Multiplatform](https://img.shields.io/badge/Compose-Multiplatform-4285F4?logo=jetpackcompose)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![Platform](https://img.shields.io/badge/Platform-iOS%20%7C%20Android-lightgrey)]()

 • [Features](#-features) • [Architecture](#%EF%B8%8F-architecture) • 

</div>

## ✨ Features
### Core Features
- 📱 Single codebase for iOS and Android platforms using Compose MultiPlatform
- 🌍 [WIP-iOS] Google Maps
- 🎨 Modern, intuitive user interface

### Platform-Specific Features
- 🤖 **Android**
    - Google Maps integration with pin display
    - Optimized for various Android versions

- 🍎 **iOS**
    - [WIP] 🏗️ 

## 🛠️ Technology Stack

### Core Technologies
- **Kotlin Multiplatform Mobile (KMM)**
    - Share business logic between platforms
    - Efficient code sharing strategy

- **Jetpack Compose Multiplatform**
    - Modern declarative UI
    - Consistent design across platforms
    - Rich component library
    - Custom composables for specific needs
    - Usage of MVI with actions

- **Dependency Injection**
    - Koin for dependency management
    - Clean architecture support
    - Easy testing capabilities

## 🏗️ Architecture

The project follows Clean Architecture principles with MVI (Model-View-Intent) pattern:

```
├── commonMain
│   ├── data
│   │   ├── database
│   │   ├── dta
│   │   ├── mappers
│   │   ├── network
│   │   └── repository
│   ├── di
│   │   └── modules
│   ├── domain
│   │   ├── enums
│   │   ├── factory
│   │   ├── models
│   │   └── repository
│   ├── presentation
│   │   ├── viewmodel
│   │   ├── screens 
│   │   └── components 
├── androidMain
│   └── platform
└── iosMain
    └── platform
``` 
