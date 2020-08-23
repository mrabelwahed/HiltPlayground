package com.ramadan.hiltplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Qualifier

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var aClass:A
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        aClass.doSomething()
        aClass.doAnotherThing()
    }
}

@AndroidEntryPoint
class  FragmentA : Fragment() {
    @Inject lateinit var aclass : A

}

@ActivityScoped
class A @Inject constructor(@Impl1 private val someInterface1: SomeInterface,
                           @Impl2 private val someInterface2: SomeInterface){
    fun doSomething(){
        println("Look I am doing something")
    }

    fun doAnotherThing(){
      someInterface1.doSomethingMore()
        someInterface2.doSomethingMore()
    }
}

class BImpl1 @Inject constructor():SomeInterface{
    fun soSomethingB(){
        println("Look I am doing another something")
    }

    override fun doSomethingMore() {
        println("look I am doing more fun1 ")
    }
}

class BImpl2 @Inject constructor():SomeInterface{
    fun soSomethingB(){
        println("Look I am doing another something2")
    }

    override fun doSomethingMore() {
        println("look I am doing more fun2 ")
    }
}

interface SomeInterface{
    fun doSomethingMore()
}

//@InstallIn(ActivityComponent::class)
//@Module
//abstract  class  ModuleA{
//    @ActivityScoped
//    @Binds
//  abstract  fun bindSomeInterface(b: B):SomeInterface
//}

@InstallIn(ActivityComponent::class)
@Module
  class  ModuleA{
    @ActivityScoped
    @Provides
    @Impl1
      fun provideSomeInterface():SomeInterface{
        return  BImpl1()
    }

    @ActivityScoped
    @Provides
    @Impl2
    fun provideSomeInterface2():SomeInterface{
        return  BImpl2()
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Impl2