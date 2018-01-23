package com.example.ljn.kotlinproject.injection.scope

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Suppress("DEPRECATED_JAVA_ANNOTATION")
@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class FragmentScope