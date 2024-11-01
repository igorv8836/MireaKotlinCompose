package com.example.mireakotlincompose.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mireakotlincompose.presentation.viewmodel.CatFactViewModel
import com.example.mireakotlincompose.presentation.viewmodel.SharedViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")

        return try {
            @Suppress("UNCHECKED_CAST")
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatFactViewModel::class)
    abstract fun bindCatFactViewModel(viewModel: CatFactViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindSharedViewModel(viewModel: SharedViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}