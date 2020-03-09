package marabillas.loremar.sharedelementtransitions

import dagger.Module
import dagger.Provides

@Module
class BFragmentModule {
    @FragmentScope
    @Provides
    fun provideAFragment() = AFragment()
}