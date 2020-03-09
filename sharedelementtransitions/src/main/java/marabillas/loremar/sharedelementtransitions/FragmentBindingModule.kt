package marabillas.loremar.sharedelementtransitions

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [AFragmentModule::class])
    abstract fun contributeAFragment(): AFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [BFragmentModule::class])
    abstract fun contributeBFragment(): BFragment
}