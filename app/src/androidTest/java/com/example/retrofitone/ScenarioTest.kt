package com.example.retrofitone

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.retrofitone.load.LoadPage
import com.example.retrofitone.main.presentation.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 *TestCase
 * 1.Первый запуск -> ProgressState
 *
 * 2. Ошибка ErrorState
 *
 * 3. Кликаем на RetryButton ->
 *      ProgressState
 * 4. Время закрузки ->
 *      UserInfoState
 * 5. Клик NextUserButton ->
 *      ProgressState
 */
@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testCase1() {
        val loadPage = LoadPage()
        loadPage.checkProgressState()
        scenarioRule.scenario.recreate()
        loadPage.checkProgressState()

        loadPage.waitUntilError()
        loadPage.checkErrorState(message = "Internet connection problems")
        scenarioRule.scenario.recreate()
        loadPage.checkErrorState(message = "Internet connection problems")

        loadPage.clickRetry()
        loadPage.checkProgressState()
        scenarioRule.scenario.recreate()
        loadPage.checkProgressState()

        loadPage.waitUntilDisappear()
        var userPage = UserPage(
            gender = "female",
            city = "Portsmouth"
        )
        userPage.checkUserInfoState()
        scenarioRule.scenario.recreate()
        userPage.checkUserInfoState()

        userPage.clickNextUser()
        loadPage.checkProgressState()

        scenarioRule.scenario.recreate()
        loadPage.checkProgressState()

        loadPage.waitUntilError()
        loadPage.checkErrorState(message = "Internet connection problems")
        scenarioRule.scenario.recreate()
        loadPage.checkErrorState(message = "Internet connection problems")

        loadPage.clickRetry()
        loadPage.checkProgressState()
        scenarioRule.scenario.recreate()
        loadPage.checkProgressState()

        loadPage.waitUntilDisappear()
        userPage = UserPage(
            gender = "male",
            city = "Norwich"
        )
        userPage.checkUserInfoState()
        scenarioRule.scenario.recreate()
        userPage.checkUserInfoState()
    }
}