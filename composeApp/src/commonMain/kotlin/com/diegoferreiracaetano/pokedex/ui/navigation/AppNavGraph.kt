package com.diegoferreiracaetano.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.CreateAccount
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.CreateAccount.STEP_ARG
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.ForgotPassword
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.Home
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.Login
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.Onboarding
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.OnboardingFinish
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.PreLogin
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.PreLogin.TYPE_ARG
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.SendCode
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.SendCode.CONTACT_ARG
import com.diegoferreiracaetano.pokedex.ui.screens.account.CreateAccountScreen
import com.diegoferreiracaetano.pokedex.ui.screens.forgotPassword.ForgotPasswordScreen
import com.diegoferreiracaetano.pokedex.ui.screens.forgotPassword.SendCodeScreen
import com.diegoferreiracaetano.pokedex.ui.screens.home.HomeScreen
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType.LOGIN
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType.SIGN_UP
import com.diegoferreiracaetano.pokedex.ui.screens.login.LoginScreen
import com.diegoferreiracaetano.pokedex.ui.screens.login.PreLoginScreen
import com.diegoferreiracaetano.pokedex.ui.screens.onboarding.OnboardingFinishScreen
import com.diegoferreiracaetano.pokedex.ui.screens.onboarding.OnboardingScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Onboarding.route
    ) {
        composable(Home.route) {
            HomeScreen(modifier = modifier)
        }

        composable( Onboarding.route) {
            OnboardingScreen(
                onOnboardingFinished = { navController.navigate(OnboardingFinish.route) },
                modifier = modifier
            )
        }

        composable( OnboardingFinish.route) {
            OnboardingFinishScreen(
                onCreateAccount = {
                    navController.navigate(PreLogin.routeWithType(SIGN_UP))
                },
                onLogin = {
                    navController.navigate(PreLogin.routeWithType(LOGIN))
                },
                onSkip = {},
                modifier = modifier
            )
        }

        composable(PreLogin.route) { backStackEntry ->
            val type = backStackEntry.readEnumOrDefault(TYPE_ARG, AuthScreenType.entries.first())
            PreLoginScreen(
                type = type,
                onBack = {
                    navController.popBackStack()
                },
                onAccountCreated = {
                    when (type) {
                        SIGN_UP -> navController.navigate(CreateAccount.route)
                        LOGIN -> navController.navigate(Login.route)
                    }
                },
                modifier = modifier
            )
        }

        composable(CreateAccount.route) { backStackEntry ->
            val step = backStackEntry.readEnumOrDefault(STEP_ARG, CreateAccountStepType.entries.first())
            CreateAccountScreen(
                step = step,
                onNext = { nextStep ->
                    navController.navigate(CreateAccount.routeWithStep(nextStep))
                },
                onFinish = { navController.navigateClearBackStackTo(Home.route) },
                onBack = { navController.popBackStack() },
                modifier = modifier
            )
        }

        composable(Login.route) {
            LoginScreen(
                onFinish = { navController.navigateClearBackStackTo(Home.route) },
                onBack = { navController.popBackStack() },
                onForgotPassword = { navController.navigate(ForgotPassword.route) },
                modifier = modifier
            )
        }

        composable(ForgotPassword.route) {
            ForgotPasswordScreen(
                onSendCode = { contact->
                    navController.navigate(SendCode.routeWithContact(contact))
                },
                onBack = { navController.popBackStack() },
                modifier = modifier
            )
        }

        composable(SendCode.route) {  backStackEntry ->
            val contact = backStackEntry.readOrDefault(CONTACT_ARG, "")
            SendCodeScreen(
                contact = contact,
                onFinish = { navController.navigateClearBackStackTo(Login.route) },
                onBack = { navController.popBackStack() },
                modifier = modifier
            )
        }
    }
}
