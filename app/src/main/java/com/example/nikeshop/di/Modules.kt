package com.example.nikeshop.di

import com.example.nikeshop.Model.*
import com.example.nikeshop.Presenter.*
import com.example.nikeshop.fragments.*
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.utitlity.PicassoUtility
import com.squareup.picasso.Picasso
import org.koin.dsl.module.module

val fragmentModules = module {
    single { HomeFragment() }
    single { AccountFragment() }
    single { ShoppingCartFragment() }
    single { LoginFragment() }
    single { RegisterFragment() }
    single { AcceptedCommentFragment() }
}

val modelModules = module {
    //single { ModelMainActivity() }
    single { ModelHomeFragment() }
    single { ModelArchiveActivity(get()) }
    factory { ModelQuestionActivity() }
    factory { ModelLoginFragment() }
    factory { ModelRegisterFragment() }
    factory { ModelAcceptedCommentFragment(get()) }
    single { ModelReceiverActivity() }
}

val apiModules = module {
    single { Picasso.get() }
    single { PicassoUtility() }
    single { ApiService() }
}

val presenterModules = module {
    single { PresenterHomeFragment(get() as HomeFragment,get() as ModelHomeFragment) }
    factory { PresenterLoginFragment(get() as LoginFragment, get() as ModelLoginFragment)}
    factory { PresenterRegisterFragment(get() as RegisterFragment, get() as ModelRegisterFragment)}
    factory { PresenterAcceptedCommentFragment(get() as AcceptedCommentFragment,get() as ModelAcceptedCommentFragment) }
}
