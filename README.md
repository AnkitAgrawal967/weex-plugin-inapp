# weex-plugin-inapp
Enables iOS and Android inapp purchases in Weex platform


### Running on Android

cd weex-plugin-inapp` (root of the project)
`npm run build && `npm run start`. This opens the webbroswer

Open plaground/android in Android Studio
Connect an android device
Build and run
Scan the QR code in browser.

### Bintray

`cd weex-plugin-inapp/android/library`

**install**

`../../playground/android/gradlew install`

**upload**
`../../playground/android/gradlew bintrayUpload -Dbintray.user=<> -Dbintray.apikey=<>`
