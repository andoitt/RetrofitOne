package com.example.retrofitone.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.retrofitone.R
import com.example.retrofitone.core.di.ManageViewModels
import com.example.retrofitone.databinding.ActivityMainBinding
import com.example.retrofitone.load.presentation.LoadNavigation
import com.example.retrofitone.load.presentation.LoadScreen
import com.example.retrofitone.load.presentation.LoadViewModel
import com.example.retrofitone.user.presentation.UserNavigation
import com.example.retrofitone.user.presentation.UserScreen


class MainActivity : AppCompatActivity(), Navigation, ManageViewModels {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainViewModel =
            viewModel(MainViewModel::class.java)

        val lastScreen = viewModel.init(savedInstanceState == null)
        navigate(lastScreen)

    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }

    override fun clear(clazz: Class<out MyViewModel>) {
        (application as ManageViewModels).clear(clazz)
    }

    override fun <T : MyViewModel> viewModel(clazz: Class<T>): T {
        return (application as ManageViewModels).viewModel(clazz)
    }
}

interface Navigation : UserNavigation, LoadNavigation {

    fun navigate(screen: Screen)

    override fun navigateFromUser() {
        navigate(LoadScreen)
    }

    override fun navigateFromLoad() {
        navigate(UserScreen)
    }

}

interface Screen {

    fun show(containerId: Int, fragmentManager: FragmentManager) = Unit

    object Empty : Screen

    abstract class Replace : Screen {

        abstract fun fragment(): Fragment

        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(containerId, fragment())
                .commit()

        }
    }
}

