package br.com.cvaccari.orders.previousorders

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@LooperMode(LooperMode.Mode.PAUSED)
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class PreviousOrdersFragmentTest {

    fun robot(func: PreviousOrdersRobot.() -> Unit) =
        PreviousOrdersRobot().apply { func() }

    @Test
    fun `request items with success, show items`() {
        robot {
                injectSuccessfulRequest()
                launch {
                    checkTitleIsShown()
                    checkListIsShown()
                }
        }
    }

    @Test
    fun `request items with error, show error container`() {
        robot {
            injectFailureRequest()
            launch {
                checkErrorContainerIsShown()
            }
        }
    }
}