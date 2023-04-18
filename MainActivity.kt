package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var wordList : List<String>    // The list of words
    private lateinit var word : String    // The selected word
    private var gameOver = false    // Is the game over?
    private var guess = "     "    // The user's guess

    // Check if user's word exists in the file
    private fun legitGuess():Boolean = guess.lowercase() in wordList

    // build a map<character,count> for the word
    // Key is a letter, value counts occurrences of the letter
    private fun countCharacterOccurrences(str:String):Map<Char, Int> {
        val charCountMap = mutableMapOf<Char, Int>()    // initialize the map
        // how many times the same character appeared in the word
        for (c in str) charCountMap[c] = charCountMap.getOrDefault(c, 0) + 1
        return charCountMap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Read a file (list of words) used in the game
        // IMPORTANT: You must put your txt file into res/raw
        wordList = BufferedReader(InputStreamReader(resources.openRawResource(
            resources.getIdentifier("wordle", "raw", packageName)))).readLines()
        // Pick a word from the file, randomly
        word = wordList.random()
        word = "quite"  // test only
        // Tell the user what the word is, for debugging
        findViewById<TextView>(R.id.message).text = "The word is $word"
    }
    // Track the cursor position in the Wordle grid
    private var row = 1
    private var col = 1

    // get textView (e.g., textView23) corresponding row and column
    private fun getTextView(row : Int, col : Int): TextView {
        // e.g., idName is textView31
        val idName = if (col > 5) "textView${row}5" else "textView${row}${col}"
        // resources.getIdentifier will return corresponding number (e.g.,2131231192)
        val id = resources.getIdentifier(idName, "id", packageName)
        println("idName is $idName and id is $id")    //for debugging
        return findViewById<TextView>(id)
    }
    // get letter button (e.g., buttonS, buttonQ)
    private fun getButton(letter : String): Button {
        // e.g., idName is buttonA, buttonB, buttonC, etc
        val idName = "button${letter.uppercase()}"
        // resources.getIdentifier will return corresponding number (e.g., 2131231192)
        val id = resources.getIdentifier(idName, "id", packageName)
        //println("idName is $idName and id is $id")    // for debugging
        return findViewById<Button>(id)
    }

    //TODO
    // themes.xml - OnClickListener for letter buttons
    fun letterHandler(view: View) {
        // if game is over, just return

        // when a user press a letter, show the letter to current textView
        getTextView(row, col).text = (view as Button).text.toString()

        println((view as Button).text.toString())     // for debugging
        // advance cursor to next textView
        if (col != 5){
            col ++

        }

    }

    //TODO
    // themes.xml - OnClickListener for back space
    fun backspaceHandler(view: View) {
        // if game is over, just return
        if (gameOver) return
        // if we went past the end, so clamp down
        // Go back if we advanced
        // Erase the text
    }

    // TODO:
    // themes.xml - OnClickListener for enter
    fun enterHandler(view: View) {
        // No change to game state if the word is incomplete
        if (col != 5) {
            findViewById<TextView>(R.id.message).text = "Your guess is not finished"
            return
        }

        // grab text from textView and concatenate
        for (i in 0..4) {
            guess[i] = getTextView(row, i)  // TODO: figure out this error
        }
        // No change to game state if the word is not in dictionary
        if (!legitGuess()) {
            findViewById<TextView>(R.id.message).text = "Please use a legit word"
            return
        }
        // At this point, reveal the game state
        colorCode()
        // If we got here, the guessed word is in the dictionary
        // If it matches the word, the game is over

        // If we're on the last row, the game is over
        if (row == 6){
            gameOver = true
        }
    }

    // TODO:
    // grab text from textView and concatenate
    private fun getGuess() {

    }

    //TODO:
    private fun updateTextColor(row: Int, col: Int, color: Int) {

    }

    private val colorMap = mutableMapOf<String,Int>()

    //TODO
    private fun updateButtonColor(letter: String, color: Int) {

        // Pick the best color for the button


        // Green beats yellow and gray

        // Yellow beats gray

    }

    //TODO: this
    // based on a map<letter, occurrence>, update textView colors and keyboard button colors
    private fun colorCode() {
        // Store user's guess as array of strings. Five letters, index 0 to 4.

        // First, highlight in green where the characters lined up properly

        // If the guessed letter matches the word letter, color code in green
        // and decrement the occurrences for the corresponding letter

        // If the guessed letter is in the word, highlight in yellow
        // and decrement the occurrences for the corresponding letter
        // Otherwise, highlight non-matches with a black background

    }
}