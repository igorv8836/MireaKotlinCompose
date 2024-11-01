package com.example.mireakotlincompose

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.mireakotlincompose.di.DataModule
import com.example.mireakotlincompose.di.DatabaseModule
import com.example.mireakotlincompose.di.NetworkModule
import com.example.mireakotlincompose.di.PresentationModule
import com.example.mireakotlincompose.di.ViewModelModule
import com.example.mireakotlincompose.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            androidContext(this@App)
//            modules(
//                databaseModule(),
//                networkModule(),
//                dataModule(),
//                presentationModule()
//            )
//        }

        appComponent = DaggerAppComponent.builder()
            .applicationContext(this)
            .build()
    }
}

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        DataModule::class,
        PresentationModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(activity: MainActivity)
    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(context: Context): Builder
        fun build(): AppComponent
    }
}