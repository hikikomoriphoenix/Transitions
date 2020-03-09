package marabillas.loremar.sharedelementtransitions

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @ActivityScope
    @Provides
    fun provideAFragment() = AFragment()
}