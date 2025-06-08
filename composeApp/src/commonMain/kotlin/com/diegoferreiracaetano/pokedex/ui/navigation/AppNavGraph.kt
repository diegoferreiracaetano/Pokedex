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
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.Home
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.Onboarding
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.OnboardingFinish
import com.diegoferreiracaetano.pokedex.ui.screens.HomeScreen
import com.diegoferreiracaetano.pokedex.ui.screens.account.CreateAccountScreen
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
                    navController.navigate(CreateAccount.route)
                },
                onLogin = {},
                onSkip = {},
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
                onFinish = {
                    navController.navigateClearBackStackTo(Home.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
