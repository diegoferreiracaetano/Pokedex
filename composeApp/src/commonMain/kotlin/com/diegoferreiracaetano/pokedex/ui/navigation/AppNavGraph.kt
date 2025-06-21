package com.diegoferreiracaetano.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diegoferreiracaetano.pokedex.domain.session.SessionManager
import com.diegoferreiracaetano.pokedex.domain.user.CreateAccountStepType
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.CreateAccount
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.CreateAccount.STEP_ARG
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.EditName
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.EditPassword
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.Home
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.Login
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.Onboarding
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.OnboardingFinish
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.PreLogin
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.PreLogin.TYPE_ARG
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.Profile
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.SendCode
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.SendCode.CONTACT_ARG
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.ValidateEmail
import com.diegoferreiracaetano.pokedex.ui.navigation.ScreenRouter.ValidateEmail.VALIDATE_ARG
import com.diegoferreiracaetano.pokedex.ui.screens.account.CreateAccountScreen
import com.diegoferreiracaetano.pokedex.ui.screens.email.ValidateEmailScreen
import com.diegoferreiracaetano.pokedex.ui.screens.email.ValidateEmailType
import com.diegoferreiracaetano.pokedex.ui.screens.email.ValidateEmailType.FORGOT
import com.diegoferreiracaetano.pokedex.ui.screens.email.ValidateEmailType.UPDATE
import com.diegoferreiracaetano.pokedex.ui.screens.favorites.FavoritesScreen
import com.diegoferreiracaetano.pokedex.ui.screens.home.HomeScreen
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType.LOGIN
import com.diegoferreiracaetano.pokedex.ui.screens.login.AuthScreenType.SIGN_UP
import com.diegoferreiracaetano.pokedex.ui.screens.login.LoginScreen
import com.diegoferreiracaetano.pokedex.ui.screens.login.PreLoginScreen
import com.diegoferreiracaetano.pokedex.ui.screens.onboarding.OnboardingFinishScreen
import com.diegoferreiracaetano.pokedex.ui.screens.onboarding.OnboardingScreen
import com.diegoferreiracaetano.pokedex.ui.screens.otp.SendCodeScreen
import com.diegoferreiracaetano.pokedex.ui.screens.password.ChangePasswordScreen
import com.diegoferreiracaetano.pokedex.ui.screens.profile.ProfileScreen
import com.diegoferreiracaetano.pokedex.ui.screens.regions.RegionsScreen
import com.diegoferreiracaetano.pokedex.ui.screens.user.ChangeUserNameScreen
import com.diegoferreiracaetano.pokedex.util.getLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun AppNavGraph(
    sessionManager: SessionManager = koinInject(),
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {

    val isLoggedIn by sessionManager.isLoggedIn.collectAsStateWithLifecycle()
    val startDestination = if (isLoggedIn) Home.route else Onboarding.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Home.route) {
            HomeScreen(
                onTabSelected = { route->
                    navController.navigateToRoute(route)
                },
                modifier = modifier
            )
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
                onChangePassword = {
                    navController.navigate(ValidateEmail.routeWithType(FORGOT))
                },
                modifier = modifier
            )
        }

        composable(ValidateEmail.route) { backStackEntry ->
            val type = backStackEntry.readEnumOrDefault(VALIDATE_ARG, ValidateEmailType.entries.first())
            ValidateEmailScreen(
                type = type,
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

        composable(Profile.route) {

            ProfileScreen(
                isLoggedIn = isLoggedIn,
                name = sessionManager.user()?.name.orEmpty(),
                email = sessionManager.user()?.email.orEmpty(),
                showSuccess = false,
                onLoginClick = {
                    navController.navigate(PreLogin.routeWithType(LOGIN))
                },
                onNameClick = {
                    navController.navigate(EditName.route)
                },
                onEmailClick = {
                    navController.navigate(ValidateEmail.routeWithType(UPDATE))
                },
                onPasswordClick = {
                    navController.navigate(EditPassword.route)
                },
                onTabSelected = { route->
                    navController.navigate(route)
                },
                onLogoutClick = {
                    coroutineScope.launch{
                        sessionManager.logout()
                    }
                },
                modifier = modifier
            )
        }

        composable(EditName.route) {
            ChangeUserNameScreen(
                onBack = { navController.popBackStack() },
                onFinish = { navController.navigateClearBackStackTo(Profile.route) },
                modifier = modifier
            )
        }

        composable(EditPassword.route) {
            ChangePasswordScreen(
                onBack = { navController.popBackStack() },
                onFinish = { navController.navigateClearBackStackTo(Profile.route) },
                modifier = modifier
            )
        }

        composable(ScreenRouter.Regions.route) {
            RegionsScreen(
                onTabSelected = { route->
                    navController.navigate(route)
                },
                modifier
            )
        }

        composable(ScreenRouter.Favorites.route) {
            FavoritesScreen(
                onTabSelected = { route->
                    navController.navigate(route)
                },
                modifier
            )
        }
    }
}