# Android application for stouza.ru

An Android application for configuring direct funding.

## Building

```
git clone https://github.com/stouza/stouza-android.git
cd stouza-android
gradle build
```

You may need to adjust the path to Android SDK in `local.properties`. 


## Troubleshooting

Problem: 

```
Cannot lock Java compile cache as it has already been locked by this process.
```

Solution: manually remove locks from the Gradle cache

```
find ~/.gradle -type f -name "*.lock" | xargs rm
```


## Credits

Adapted from the orginal sample [repository](https://github.com/felipemendes/Android-Menu-Sample) by Felipe Mendes. There, the integration of [Floating Action Button](https://github.com/Clans/FloatingActionButton) and [Fragment Navigation Drawer](https://github.com/codepath/android_guides/wiki/Fragment-Navigation-Drawer) is shown.

