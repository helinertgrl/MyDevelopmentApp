# Coffee Shop App - Geliştirme Süreci

## 📋 Proje Hakkında

Bu proje, Android geliştirme sürecimdeki öğrenimlerimi belgelemek ve kendimi geliştirmek amacıyla oluşturduğum bir kahve dükkanı uygulamasıdır. Uygulama başlangıçta yerel verilerle çalışırken, yeni versiyonda internet bağlantısı eklenerek Fake bir API entegrasyonu sağlanmıştır.

---

## 🚀 Sürüm Geçmişi

### **v2.0 - Başlangıç Versiyonu**
- Temel Compose UI yapısı
- Yerel ürün listesi
- Sepet yönetimi
- Navigasyon sistemi

### **v3.0 - Güncel Versiyon** (Yeni Özellikler)
- **MockAPI.io** entegrasyonu
- **Ktor** HTTP client implementasyonu
- **Room Database** ile offline cache
- **Coil/AsyncImage** ile network görsel yükleme
- Repository pattern implementasyonu

---

## 🛠️ Kullanılan Teknolojiler

### **UI & Framework**
| Teknoloji | Amaç |
|-----------|------|
| Jetpack Compose | Modern UI oluşturma |
| Navigation Component | Sayfa geçişleri |
| Material 3 | Tasarım sistemi |
| Coil | Görsel yükleme |

### **Network & Data**
| Teknoloji | Amaç |
|-----------|------|
| Ktor | HTTP istemci |
| Kotlin Serialization | JSON parsing |
| MockAPI.io | Fake REST API |

### **Database**
| Teknoloji | Amaç |
|-----------|------|
| Room Database | Yerel veri depolama |
| DAO Pattern | Veri erişim katmanı |

### **Architecture**
| Teknoloji | Amaç |
|-----------|------|
| Repository Pattern | Data katmanı soyutlama |
| MVVM (kısmi) | Mimari yapı |

---



## 🔄 Eklenen Yeni Özellikler (v3.0)

### **1. Network Entegrasyonu**
- ✅ MockAPI.io üzerinden fake REST API
- ✅ Ktor HTTP client konfigürasyonu
- ✅ JSON serialization/deserialization
- ✅ Hata yönetimi ve loading state'leri

### **2. Veri Yönetimi**
- ✅ Room database implementasyonu
- ✅ Entity, DAO, Database sınıfları
- ✅ Repository pattern implementasyonu
- ✅ Offline cache mekanizması

### **3. UI Geliştirmeleri**
- ✅ AsyncImage ile network görsel yükleme
- ✅ Loading ve error state gösterimi
- ✅ Ürün kartlarının responsive tasarımı
- ✅ Görsel placeholder'ları

### **4. Mimari İyileştirmeler**
- ✅ Katmanlı mimari
- ✅ Data class'ların ayrılması
- ✅ Extension fonksiyonlar
- ✅ Clean code prensipleri

---

## 📁 Paket Yapısı

```
com.example.mydevelopmentapp/
├── data/
│   ├── api/
│   │   ├── CoffeeApiService.kt    # API servisi
│   │   └── KtorClient.kt          # Ktor konfigürasyonu
│   ├── local/
│   │   ├── ProductEntity.kt       # Room entity
│   │   ├── ProductDao.kt          # Database operations
│   │   └── AppDatabase.kt         # Database instance
│   ├── model/
│   │   └── ProductResponse.kt     # API response model
│   └── repository/
│       └── ProductRepository.kt   # Data repository
├── ui/
│   ├── screens/
│   │   ├── MainScreen.kt
│   │   ├── ShopScreen.kt
│   │   ├── ProfileScreen.kt
│   │   ├── CartScreen.kt
│   │   └── ShopandProfile.kt
│   └── navigation/
│       └── Navigation.kt
├── utils/
│   └── Extensions.kt              # Extension fonksiyonlar
└── MainActivity.kt
```

---

## 🎯 Projenin Amacı

Bu projeyi geliştirirken temel hedeflerim:

1. **Öğrenme Sürecini Belgelemek** - Her yeni teknolojiyi adım adım uygulamak
2. **Gerçek Dünya Senaryoları** - Network, database, cache gibi gerçek ihtiyaçları çözmek
3. **Kod Kalitesi** - Clean architecture ve best practices uygulamak
4. **UI/UX Deneyimi** - Modern ve kullanıcı dostu arayüzler tasarlamak
5. **Problem Çözme** - Karşılaşılan hataları araştırıp çözüm üretmek

---

## 📱 Ekranlar ve Özellikler

| Ekran | Özellikler |
|-------|------------|
| **MainScreen** | Giriş/Üye olma/Guest modu |
| **ShopScreen** | Ürün listesi, filtreleme, sıralama, sepete ekleme |
| **ProfileScreen** | Kullanıcı profili görüntüleme |
| **CartScreen** | Sepet özeti, ürün çıkarma, toplam tutar |

---

## 📝 Öğrenilen Kavramlar

- Jetpack Compose ile modern UI tasarımı
- Ktor ile network işlemleri
- Room Database ile lokal veri depolama
- Repository pattern ve clean architecture
- State management ve lifecycle
- Asenkron programlama (coroutines)
- Error handling ve loading state'leri
- API entegrasyonu ve JSON parsing

---

# Coffee Shop App - Development Process

## 📋 About the Project

This project is a coffee shop application I created to document my Android development learning process and improve my skills. The app initially worked with local data, and in the new version, internet connectivity has been added with real API integration.

---

## 🚀 Version History

### **v2.0 - Initial Version**
- Basic Compose UI structure
- Local product list
- Cart management
- Navigation system

### **v3.0 - Current Version** (New Features)
- **MockAPI.io** integration
- **Ktor** HTTP client implementation
- **Room Database** for offline caching
- **Coil/AsyncImage** for network image loading
- Repository pattern implementation

---

## 🛠️ Technologies Used

### **UI & Framework**
| Technology | Purpose |
|------------|---------|
| Jetpack Compose | Modern UI creation |
| Navigation Component | Page transitions |
| Material 3 | Design system |
| Coil | Image loading |

### **Network & Data**
| Technology | Purpose |
|------------|---------|
| Ktor | HTTP client |
| Kotlin Serialization | JSON parsing |
| MockAPI.io | Fake REST API |

### **Database**
| Technology | Purpose |
|------------|---------|
| Room Database | Local data storage |
| DAO Pattern | Data access layer |

### **Architecture**
| Technology | Purpose |
|------------|---------|
| Repository Pattern | Data layer abstraction |
| MVVM (partial) | Architecture structure |

---

## 🔄 Added New Features (v3.0)

### **1. Network Integration**
- ✅ Fake REST API via MockAPI.io
- ✅ Ktor HTTP client configuration
- ✅ JSON serialization/deserialization
- ✅ Error handling and loading states

### **2. Data Management**
- ✅ Room database implementation
- ✅ Entity, DAO, Database classes
- ✅ Repository pattern implementation
- ✅ Offline cache mechanism

### **3. UI Improvements**
- ✅ AsyncImage for network image loading
- ✅ Loading and error state display
- ✅ Responsive product card design
- ✅ Image placeholders

### **4. Architectural Improvements**
- ✅ Layered architecture
- ✅ Separation of data classes
- ✅ Extension functions
- ✅ Clean code principles

---

## 📁 Package Structure

```
com.example.mydevelopmentapp/
├── data/
│   ├── api/
│   │   ├── CoffeeApiService.kt    # API service
│   │   └── KtorClient.kt          # Ktor configuration
│   ├── local/
│   │   ├── ProductEntity.kt       # Room entity
│   │   ├── ProductDao.kt          # Database operations
│   │   └── AppDatabase.kt         # Database instance
│   ├── model/
│   │   └── ProductResponse.kt     # API response model
│   └── repository/
│       └── ProductRepository.kt   # Data repository
├── ui/
│   ├── screens/
│   │   ├── MainScreen.kt
│   │   ├── ShopScreen.kt
│   │   ├── ProfileScreen.kt
│   │   ├── CartScreen.kt
│   │   └── ShopandProfile.kt
│   └── navigation/
│       └── Navigation.kt
├── utils/
│   └── Extensions.kt              # Extension functions
└── MainActivity.kt
```

---

## 🎯 Project Goals

My main goals while developing this project:

1. **Document Learning Process** - Implement each new technology step by step
2. **Real-World Scenarios** - Solve real needs like network, database, cache
3. **Code Quality** - Apply clean architecture and best practices
4. **UI/UX Experience** - Design modern and user-friendly interfaces
5. **Problem Solving** - Research and solve encountered errors

---

## 📱 Screens and Features

| Screen | Features |
|--------|----------|
| **MainScreen** | Login/Sign Up/Guest mode |
| **ShopScreen** | Product list, filtering, sorting, add to cart |
| **ProfileScreen** | User profile display |
| **CartScreen** | Cart summary, remove items, total price |

---

## 📝 Learned Concepts

- Modern UI design with Jetpack Compose
- Network operations with Ktor
- Local data storage with Room Database
- Repository pattern and clean architecture
- State management and lifecycle
- Asynchronous programming (coroutines)
- Error handling and loading states
- API integration and JSON parsing

---

**Developer Note:** This project represents my ongoing journey in Android development. Each commit and version update reflects new concepts learned and applied. The goal is not just to build an app, but to understand the underlying principles and best practices of modern Android development.
