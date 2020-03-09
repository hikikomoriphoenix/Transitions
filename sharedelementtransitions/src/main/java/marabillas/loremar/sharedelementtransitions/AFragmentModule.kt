package marabillas.loremar.sharedelementtransitions

import dagger.Module
import dagger.Provides

@Module
class AFragmentModule {
    @FragmentScope
    @Provides
    fun provideBFragment() = BFragment()
}