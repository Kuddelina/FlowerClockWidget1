# 🌸 Flower Clock Widget

A hand-painted analogue clock Android home screen widget with three separate hands (hour, minute, second).

---

## How to build & install

### What you need
- A computer (Windows, Mac, or Linux)
- [Android Studio](https://developer.android.com/studio) — free, ~1 GB download
- Your Android phone with **USB debugging** enabled

---

### Step 1 — Enable Developer Options on your phone
1. Go to **Settings → About phone**
2. Tap **Build number** 7 times
3. Go back to Settings → **Developer options**
4. Enable **USB debugging**

---

### Step 2 — Install Android Studio
Download and install from https://developer.android.com/studio  
During setup, let it install the Android SDK automatically.

---

### Step 3 — Open the project
1. Launch Android Studio
2. Choose **Open** (not "New Project")
3. Navigate to and select the **FlowerClockWidget** folder
4. Wait for Gradle to sync (first time takes a few minutes — it downloads dependencies)

---

### Step 4 — Build & run
1. Connect your phone via USB
2. Accept the "Allow USB debugging" prompt on your phone
3. In Android Studio, select your phone from the device dropdown at the top
4. Click the green **▶ Run** button
5. The app installs silently (there's no launcher icon — that's normal for widget-only apps)

---

### Step 5 — Add the widget to your home screen
1. Long-press an empty area of your home screen
2. Tap **Widgets**
3. Scroll to find **Flower Clock**
4. Long-press it and drag it onto your home screen
5. Resize it by dragging the corners — it looks best at 3×3 cells or larger

---

## Troubleshooting

| Problem | Fix |
|---|---|
| Gradle sync fails | File → Invalidate Caches → Restart |
| Phone not detected | Try a different USB cable; make sure it's a data cable not a charge-only cable |
| Widget not in list | Uninstall and reinstall the app |
| Hands don't move | Toggle the widget off and back on; the tick service starts with the widget |

---

## Project structure

```
FlowerClockWidget/
├── app/src/main/
│   ├── AndroidManifest.xml
│   ├── java/com/flowerclock/widget/
│   │   ├── FlowerClockWidget.kt     ← AppWidgetProvider (draws the clock)
│   │   └── ClockTickService.kt      ← Service that ticks every second
│   └── res/
│       ├── drawable/
│       │   ├── clock_face.jpg       ← The painted clock face
│       │   ├── hand_hour.jpg        ← Red/yellow hour hand
│       │   ├── hand_minute.jpg      ← Red minute hand
│       │   └── hand_second.jpg      ← Yellow-green second hand
│       ├── layout/
│       │   └── flower_clock_widget.xml
│       └── xml/
│           └── flower_clock_widget_info.xml
```
