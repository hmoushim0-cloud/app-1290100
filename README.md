# مشروع التطبيق هنا ليس مجرد نموذ (Project 1290100)

![Platform: Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat&logo=android&logoColor=white)
![Kotlin 2.1](https://img.shields.io/badge/Language-Kotlin%202.1-7F52FF?style=flat&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=flat&logo=jetpackcompose&logoColor=white)
![Clean Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture%20(MVVM)-orange?style=flat)
![Security Verified](https://img.shields.io/badge/Security-Zero--Vulnerability%20Gate-success?style=flat)
![Design: Pure Vectors](https://img.shields.io/badge/Design-Pure%20Vector%20Drawables-blue?style=flat)

> **نظام رقمي سحابي متكامل فائق الأداء والأمان**

---

## Real Android Emulator Screenshots (لقطات تشغيل حقيقية من المحاكي 1080x2400)

| الشاشة الرئيسية واستعراض الخدمات | تفاصيل الطلب والتسعير | التتبع المباشر وحالة المعالجة |
|:---:|:---:|:---:|
| ![Main Screen](screenshots/01_home.png) | ![Details](screenshots/02_details.png) | ![Live Tracking](screenshots/03_tracking.png) |

---

## Project Overview (نظرة عامة على المشروع)

مشروع متكامل لتنفيذ متطلبات: التطبيق هنا ليس مجرد نموذج حجز، بل منصة تشغيل لخدمة ميدانية تعتمد على إدارة الطلبات، التخصيص الذكي للفنيين، والدفع الإلكتروني مع شفافية كاملة للمستخدم. القيمة المضافة الأساسية تأتي من تقليل الاحتكاك: اختيار الخدمة، إدخال العنوان، معرفة السعر مسبقًا، متابعة الفني على الخريطة، ثم إغلاق الطلب تلقائيًا بعد الإنجاز مع سجل واضح للحالة والدفعات.

هندسيًا، يُفضل تصميمه على Clean Architecture مع فصل واضح بين Presentation وDomain وData، بحيث تتعامل واجهات المستخدم مع حالات الطلب والباقات والخريطة عبر Use Cases مستقلة. على مستوى السحابة، يُنصح بدمج Backend API لإدارة الحجوزات والفنيين والمدفوعات، مع خدمات إشعارات فورية، تتبع مواقع لحظي، وتخزين آمن للبيانات الحساسة، إضافة إلى طبقة مزامنة مقاومة لانقطاع الشبكة لضمان تجربة سلسة حتى في البيئات ضعيفة الاتصال.. تم بناء وتوثيق النظام بأحدث معايير هندسة البرمجيات وأمان البيانات.

---

## Core Features (المميزات الرئيسية)

- **تصميم عصري داعم للعربية**
- **Clean Architecture MVVM**
- **دعم الدفع الإلكتروني والفوترة**
- **تتبع لحظي بالـ GPS**

---

## Clean Architecture Layers

```
sa.factory.app1290100
├── data/
│   └── OrderRequestRepository.kt          # Flow-based reactive data source
├── model/
│   └── OrderRequestModels.kt              # Immutable business data structures
└── ui/
    ├── MainActivity.kt                # Jetpack Compose Entry Activity
    ├── OrderRequestViewModel.kt           # StateFlow UI state holder
    └── OrderRequestScreens.kt             # Material 3 Compose vector screens
```

---

## Architectural Invariants & Security Threats

### Invariants (القواعد الصارمة):
- **`totalCalculationExact`**: Total price must equal subtotal plus VAT with zero floating point inaccuracy
- **`statusTransitionsMonotonic`**: Status progression must strictly advance through state machine

### Threat Model (حماية البيانات والتهديدات):
- Customer PII must be encrypted at rest with SQLCipher and redacted from logs
- Payment tokens must be strictly ephemeral and signed with TLS certificate pinning

---

## Build Instructions

```bash
# Clone the repository
git clone https://github.com/hmoushim0-cloud/app-1290100.git
cd app-1290100

# Assemble Debug APK
./gradlew :app:assembleDebug
```

---

### Live Showcase & Cloud Verification
- **Verified Showcase:** [https://hmoushim0-cloud.github.io/live-previews/?id=1290100](https://hmoushim0-cloud.github.io/live-previews/?id=1290100)
