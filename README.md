<div align="center">

<!-- Animated Typing Header -->
<a href="https://github.com/helinertgrl">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=13&pause=1000&color=7F52FF&center=true&vCenter=true&width=600&lines=Firebase+%E2%80%A2+Ktor+%E2%80%A2+Room+%E2%80%A2+Jetpack+Compose" alt="stack"/>
</a>

<br/>

<picture>
  <img src="https://capsule-render.vercel.app/api?type=venom&color=gradient&customColorList=6,11,20&height=300&text=☕%20Coffee%20Shop&fontSize=70&fontColor=fff&animation=twinkling&fontAlignY=55&desc=A%20Modern%20Android%20Shopping%20Experience&descAlignY=75&descSize=18&descColor=cccccc" width="100%"/>
</picture>

<!-- Badges -->
<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white"/>
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"/>
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white"/>
  <img src="https://img.shields.io/badge/Firebase-Auth-FFCA28?style=for-the-badge&logo=firebase&logoColor=black"/>
  <img src="https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Min%20SDK-24-green?style=flat-square"/>
  <img src="https://img.shields.io/badge/Target%20SDK-36-blue?style=flat-square"/>
  <img src="https://img.shields.io/badge/Version-1.0-orange?style=flat-square"/>
  <img src="https://img.shields.io/badge/Build-Passing-brightgreen?style=flat-square&logo=githubactions"/>
</p>

<br/>

> **A full-stack Android application built with modern best practices — featuring Firebase Authentication, a REST API via Ktor, local persistence with Room, and a reactive UI powered by Jetpack Compose.**

<br/>

</div>

---

## 📱 Screenshots

<div align="center">

| Login | Shop | Cart | Profile |
|:---:|:---:|:---:|:---:|
| <img src="screenshot/Main.png" width="180"/> | <img src="screenshot/Shop.png" width="180"/> | <img src="screenshot/Cart.png" width="180"/> | <img src="screenshot/Profile.png" width="180"/> |
| Firebase Auth | Search & Filter | Room DB | User Profile |

</div>

---

## ✨ Features

<div align="center">

| 🔐 Authentication | 🛍️ Shopping | 🛒 Cart | 👤 Profile |
|---|---|---|---|
| Firebase Email/Password Login | Product listing from REST API | Add / Remove items | Display user info |
| Sign Up & Sign In flows | Search by name | Real-time total price | Logout functionality |
| Session persistence | Filter by price & premium | Persistent via Room DB | — |
| — | Sort by name or price | Checkout button | — |

</div>

---

## 🏗️ Architecture

This project follows **MVVM (Model-View-ViewModel)** architecture with a clean **layered package structure** for scalability and separation of concerns.

```mermaid
graph TD
    subgraph UI ["🖥️  UI LAYER — Jetpack Compose"]
        A["🛍️ ShopScreen"]
        B["🛒 CartScreen"]
        C["👤 ProfileScreen"]
    end

    subgraph VM ["⚙️  VIEWMODEL LAYER"]
        D["ShopViewModel\nState + Business Logic"]
        E["CartViewModel\nState + Business Logic"]
    end

    subgraph DATA ["💾  DATA LAYER"]
        F["ProductRepository\nSingle Source of Truth"]
        G["🗄️ Room Database\nLocal Persistence"]
        H["🌐 Ktor / MockAPI\nRemote Data Source"]
    end

    subgraph AUTH ["🔥  FIREBASE"]
        I["Firebase Auth\nLogin · Session"]
    end

    A -->|observes state| D
    B -->|observes state| E
    C -->|reads user| I
    D -->|reads & writes| F
    E -->|reads & writes| F
    F -->|cache| G
    F -->|fetch| H

    style UI fill:#1a2a4a,stroke:#4285F4,color:#fff
    style VM fill:#2a1a4a,stroke:#7F52FF,color:#fff
    style DATA fill:#1a3a2a,stroke:#0f9d58,color:#fff
    style AUTH fill:#2a2a1a,stroke:#FFCA28,color:#fff
```

<br/>

### 🔄 Data Flow

```mermaid
flowchart LR
    API["☁️ MockAPI\nREST Endpoint"]
    KTOR["⚡ Ktor Client\nHTTP Request"]
    REPO["📦 Repository\nSource of Truth"]
    VM["🧠 ViewModel\nStateFlow"]
    UI["📱 Compose UI\ncollectAsState()"]
    ROOM["🗄️ Room DB\nLocal Cache"]

    API -->|"GET /products"| KTOR
    KTOR -->|"parse JSON"| REPO
    REPO <-->|"read / write"| ROOM
    REPO -->|"emit Flow"| VM
    VM -->|"State → recompose"| UI

    style API fill:#0f9d58,stroke:#0f9d58,color:#fff
    style KTOR fill:#1c2128,stroke:#7F52FF,color:#fff
    style REPO fill:#1c2128,stroke:#4285F4,color:#fff
    style VM fill:#1c2128,stroke:#7F52FF,color:#fff
    style UI fill:#4285F4,stroke:#4285F4,color:#fff
    style ROOM fill:#1c2128,stroke:#0f9d58,color:#fff
```

---

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|---|---|---|
| **Language** | Kotlin 2.0.21 | Primary programming language |
| **UI** | Jetpack Compose + Material3 | Declarative modern UI |
| **Architecture** | MVVM | Separation of concerns |
| **Navigation** | Navigation Compose 2.9.6 | Screen routing |
| **Networking** | Ktor Client 3.2.1 | REST API calls |
| **Serialization** | Kotlinx Serialization | JSON parsing |
| **Local DB** | Room 2.6.1 | Cart persistence |
| **Image Loading** | Coil Compose 2.5.0 | Async image loading |
| **Authentication** | Firebase Auth (BOM 33.7.0) | Secure login/signup |
| **Build System** | Gradle KTS + Version Catalog | Dependency management |
| **CI/CD** | GitHub Actions | Automated build & test |
| **Min SDK** | API 24 (Android 7.0) | Wide device coverage |
| **Target SDK** | API 36 | Latest Android features |

---

## ⚙️ CI/CD Pipeline

This project uses **GitHub Actions** for continuous integration. Every push to `master` triggers an automated build pipeline.

```mermaid
flowchart LR
    PUSH["📤 Git Push\nto master"]
    TRIGGER["⚡ GitHub Actions\nWorkflow Triggered"]
    BUILD["🔨 Gradle Build\nassembleDebug · JDK 17"]
    TEST["🧪 Tests\nlint + unit tests"]
    SUCCESS["✅ PASSING\nCI/CD Test #3 · d19bc86"]

    PUSH --> TRIGGER --> BUILD --> TEST --> SUCCESS

    style PUSH fill:#1c2128,stroke:#6e7681,color:#fff
    style TRIGGER fill:#1c2128,stroke:#2088FF,color:#fff
    style BUILD fill:#1c2128,stroke:#7F52FF,color:#fff
    style TEST fill:#1c2128,stroke:#FFCA28,color:#fff
    style SUCCESS fill:#238636,stroke:#2ea043,color:#fff
```

> **Latest build:** `build: upgrade JDK to 17` — ✅ Android CI/CD Test #3 passed · commit `d19bc86`

---

## 🚀 Getting Started

### Prerequisites

- Android Studio **Hedgehog** or newer
- JDK **17**
- Android device or emulator (API 24+)
- A **Firebase project** (for Authentication)

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/helinertgrl/MyDevelopmentApp.git

# 2. Open in Android Studio
# File → Open → Select the cloned folder

# 3. Add your google-services.json
# Place it in the /app directory from your Firebase Console

# 4. Build & Run
./gradlew assembleDebug
```

### Firebase Setup

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Create a new project
3. Enable **Email/Password** sign-in method
4. Download `google-services.json` and place it in `/app`

---

## 📦 Dependencies Overview

```toml
[versions]
kotlin              = "2.0.21"
agp                 = "8.12.3"
composeBom          = "2024.09.00"
room                = "2.6.1"
ktor                = "3.2.1"
coil                = "2.5.0"
navigationCompose   = "2.9.6"
firebaseBom         = "33.7.0"
```

---


<div align="center">

## 👩‍💻 Author

**helinertgrl**

*Android Developer in progress — learning, building, and growing every day.*

<br/>

[![LinkedIn](https://img.shields.io/badge/LinkedIn-helin--ertuğrul-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/helin-ertu%C4%9Frul/)

<br/>

---

*Built with ☕ and lots of Kotlin*

<br/>
</div>
