import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import dalmau.oscar.quizzapp.R
import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Test

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(val savedStateHandle: Any) : ViewModel(), Parcelable {

        @Test
        fun providesExpectedQuestionText() {
            val savedStateHandle = SavedStateHandle()
            val quizViewModel = QuizViewModel(savedStateHandle)
            assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
        }


    @Test
    fun wrapsAroundQuestionBank() {
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 5))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_asia, quizViewModel.currentQuestionText)
        quizViewModel.moveToNext()
        assertEquals(R.string.question_australia, quizViewModel.currentQuestionText)
    }

    private val IS_CHEATER_KEY: String
        get() {
            TODO()
        }

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex: Int = 0


    var isCheater: Comparable<*>
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)



    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId


    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuizViewModel> {
        override fun createFromParcel(parcel: Parcel): QuizViewModel {
            return QuizViewModel(parcel)
        }

        override fun newArray(size: Int): Array<QuizViewModel?> {
            return arrayOfNulls(size)
        }
    }

}

private fun Any.set(currentIndexKey: String, value: Comparable<*>) {

}

private fun Any.get(key: String): Int {

    return TODO("Provide the return value")
}
