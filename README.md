# KtValidate
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)  
A validation library for Kotlin Multiplatform. This is extreamly basic at this point. I just have 3 rules, but
it is a good starting point for a DSL based validation system that can easily be expanded on. 


Example usage
```kotlin
        val validate = Validate("aaaa") {
            fieldName = "Password"
            +Max(10)
            +Min(0, "Custome message here")
        }
        
        validate.isValid // true
        // if it's not valid validate.messages will have messages about what did not pass. 
```

To add a new custom validator simply implement the `Rule` interface
and you should now be able to now do +CustomeRule or whatever you named your rule. 

Feel free to submit a PR if there's not something in here that you want. 

To add this to your project first add jitpack to your gradle repos 
```gradle
repositories {
   ...
   maven { url 'https://jitpack.io' }
}
```
Then add this:
```gradle 
dependencies {
	implementation 'com.github.alexwhb:KtValidate:0.1.0'
}
```
