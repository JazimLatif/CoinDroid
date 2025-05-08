# CoinDroid

**CoinDroid** is an Android project I built to sharpen my development skills, explore modern Android architecture patterns, and experiment with Jetpack Compose.

This project helped me solidify my understanding of clean architecture, SOLID principles, and the MVVM design pattern. I aimed to write maintainable, scalable, and testable code.

---

## Key Features

- **Jetpack Compose UI** with state handling using `rememberSaveable` for persistence through configuration changes.
- **Navigation Component** integration for scalable app navigation.
- **Alphabetical Sorting** of crypto coins by name.
- **Search Functionality** to filter the coin list dynamically as you type.
- **Scroll to Top Button** with smooth animation for easy list navigation.
- **Responsive Design** that adapts to various screen sizes (avoiding hardcoded dimensions).

---

## Technical Decisions

- **minSdk Version: 26** – Aligns with Android 8.0 (Oreo) support.
- Clean separation of concerns across layers, following the MVVM pattern.
- Thoughtful UI state management and animations for a smooth user experience.

---

## Areas for Future Improvement

- ViewModel testing currently has issues – still working on resolving test failures.
- Improving testability of the **BottomSheet** by refactoring it to hoist state and accept coin data as parameters.
- Handling edge cases in sorting (e.g., coin names starting with spaces) with a preprocessing step, if needed.
- Using feature branches and PRs for a more production-like workflow.

---

## Thanks

Thanks for checking out **CoinDroid** – a personal playground for exploring Android dev techniques and staying sharp with Compose and Kotlin!
