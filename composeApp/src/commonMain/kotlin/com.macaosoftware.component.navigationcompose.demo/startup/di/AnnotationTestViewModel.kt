package com.macaosoftware.component.navigationcompose.demo.startup.di

import com.macaosoftware.component.core.DestinationInfo
import com.macaosoftware.component.viewmodel.DestinationViewModel
import org.koin.android.annotation.KoinViewModel

// @KoinViewModel
class AnnotationTestViewModel : DestinationViewModel() {
    override fun onAttach(destinationInfo: DestinationInfo) {
        TODO("Not yet implemented")
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun onDetach() {
        TODO("Not yet implemented")
    }
}