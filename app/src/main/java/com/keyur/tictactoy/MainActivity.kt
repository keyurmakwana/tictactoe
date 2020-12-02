package com.keyur.tictactoy

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Config.DEBUG
import android.util.Log
import android.util.Log.DEBUG
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {
        var buSelected = view as Button
        var cellID = 0
        when (buSelected.id) {
            R.id.button -> cellID = 1
            R.id.button2 -> cellID = 2
            R.id.button3 -> cellID = 3
            R.id.button4 -> cellID = 4
            R.id.button5 -> cellID = 5
            R.id.button6 -> cellID = 6
            R.id.button7 -> cellID = 7
            R.id.button8 -> cellID = 8
            R.id.button9 -> cellID = 9
        }
        playGame(cellID, buSelected)
    }

    var activePlayer = 1

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    fun playGame(cellID: Int, buSelected: Button) {
        if (activePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundColor(0xffE6E6FA.toInt())
            player1.add(cellID)
            activePlayer = 2
            autoPlay()
        } else {
            buSelected.text = "O"
            buSelected.setBackgroundColor(0xffDE5D83.toInt())
            player2.add(cellID)
            activePlayer = 1
        }
        buSelected.isEnabled = false

        checkWinner()
    }

    fun autoPlay() {
        var emptyList = ArrayList<Int>()
        for (cellID in 1..9) {
            if (!(player1.contains(cellID) || player2.contains(cellID)))
                emptyList.add(cellID)
        }
        var r = Random()
        var randIndex: Int = r.nextInt(emptyList.size)
        val cellID: Int = emptyList[randIndex]

        var buSelected: Button?
        buSelected = when (cellID) {
            1 -> findViewById(R.id.button)
            2 -> findViewById(R.id.button2)
            3 -> findViewById(R.id.button3)
            4 -> findViewById(R.id.button4)
            5 -> findViewById(R.id.button5)
            6 -> findViewById(R.id.button6)
            7 -> findViewById(R.id.button7)
            8 -> findViewById(R.id.button8)
            9 -> findViewById(R.id.button9)
            else -> {
                findViewById(R.id.button)
            }
        }
        playGame(cellID, buSelected)
    }

    fun checkWinner() {
        var winner = -1
        //Row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //Row 2
        else if (player1.contains(4) && player1.contains(5) && player1.contains(6))
            winner = 1
        else if (player2.contains(4) && player2.contains(5) && player2.contains(6))
            winner = 2

        //Row 3
        else if (player1.contains(7) && player1.contains(8) && player1.contains(9))
            winner = 1
        else if (player2.contains(7) && player2.contains(8) && player2.contains(9))
            winner = 2

        //Column 1
        else if (player1.contains(1) && player1.contains(4) && player1.contains(7))
            winner = 1
        else if (player2.contains(1) && player2.contains(4) && player2.contains(7))
            winner = 1

        //Column 2

        else if (player1.contains(2) && player1.contains(5) && player1.contains(8))
            winner = 1
        else if (player2.contains(2) && player2.contains(5) && player2.contains(8))
            winner = 1

        //column 3
        else if (player1.contains(3) && player1.contains(6) && player1.contains(9))
            winner = 1
        else if (player2.contains(3) && player2.contains(6) && player2.contains(9))
            winner = 1

        //Diagonal 1
        else if (player1.contains(1) && player1.contains(5) && player1.contains(9))
            winner = 1
        else if (player2.contains(1) && player2.contains(5) && player2.contains(9))
            winner = 1

        //Diagonal 2
        else if (player1.contains(3) && player1.contains(5) && player1.contains(7))
            winner = 1
        else if (player2.contains(3) && player2.contains(5) && player2.contains(7))
            winner = 1

        if (winner == 1) {
            Playey1Wins++
            Toast.makeText(this, "Player 1 win", Toast.LENGTH_LONG).show()
            restartGame()
        } else if (winner == 2) {
            Playey2Wins++
            Toast.makeText(this, "Player 2 win", Toast.LENGTH_LONG).show()
            restartGame()
        }

    }

    var Playey1Wins=0
    var Playey2Wins=0

    fun restartGame() {
        activePlayer = 1
        player1.clear()
        player2.clear()

        for (index in 1..9) {
            var buSelected: Button? = when (index) {
                1 -> findViewById(R.id.button)
                2 -> findViewById(R.id.button2)
                3 -> findViewById(R.id.button3)
                4 -> findViewById(R.id.button4)
                5 -> findViewById(R.id.button5)
                6 -> findViewById(R.id.button6)
                7 -> findViewById(R.id.button7)
                8 -> findViewById(R.id.button8)
                9 -> findViewById(R.id.button9)
                else -> {
                    findViewById(R.id.button)
                }
            }
            buSelected!!.text = ""
            buSelected.setBackgroundColor(0xff0abab5.toInt())
            buSelected.isEnabled=true
        }
    }
}

