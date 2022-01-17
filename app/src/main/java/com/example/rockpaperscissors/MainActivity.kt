package com.example.rockpaperscissors

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.rockpaperscissors.data.PartyData
import com.example.rockpaperscissors.databinding.ActivityMainBinding
import java.io.Console
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding : ActivityMainBinding
    private lateinit var playerData : PartyData
    private lateinit var enemyData : PartyData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rockButton.setOnClickListener(this)
        binding.paperButton.setOnClickListener(this)
        binding.scissorsButton.setOnClickListener(this)
        playerData = PartyData()
        enemyData = PartyData()
    }

    override fun onClick(p0: View) {
       when(p0)
       {
           (binding.rockButton)->{
               Log.i("log","Hi from rockButton")
               playerData = PartyData(rock=true,paper=false,scissors=false,playerData.score)
               binding.userCardText.text = "Rock"
               evaluate()

           }
           (binding.paperButton)->{
               Log.i("log","Hi from paperButton")
               playerData = PartyData(rock=false,paper=true,scissors=false,playerData.score)
               binding.userCardText.text = "Paper"
               evaluate()}

           (binding.scissorsButton)->{
               Log.i("log","Hi from scissorsButton")
               playerData = PartyData(rock=false,paper=false,scissors=true,playerData.score)
               binding.userCardText.text = "Scissors"
           evaluate()}
       }
    }

    private fun evaluate() {
        val random = Random()
        when (random.nextInt(3))
        {
            (0)->{enemyData = PartyData(rock=true,paper=false,scissors = false,enemyData.score)
                binding.enemyCardText.text = "Rock"
                Log.i("log","Enemy picks rock")}
            (1)->{enemyData = PartyData(rock=false,paper=true,scissors = false,enemyData.score)
                binding.enemyCardText.text = "Paper"
                Log.i("log","Enemy picks paper")}
            (2)->{enemyData = PartyData(rock=false,paper=false,scissors = true,enemyData.score)
                binding.enemyCardText.text = "Scissors"
                Log.i("log","Enemy picks scissors")}

        }
        when {
            playerData.rock -> {
                when(playerData.rock)
                {
                    (enemyData.paper)->{enemyData.score++}
                    (enemyData.scissors)->{playerData.score++}

                }

            }
            playerData.paper -> {
                when(playerData.paper){

                    (enemyData.rock)->{playerData.score++}
                    (enemyData.scissors)->{enemyData.score++}
                }
            }
            playerData.scissors -> {
                when(playerData.scissors)
                {
                    (enemyData.rock)->{enemyData.score++}
                    (enemyData.paper)->{playerData.score++}
                }

            }
        }
        binding.enemyScore.text = getString(R.string.enemy_score,enemyData.score)
        binding.playerScore.text = getString(R.string.player_score,playerData.score)
        Log.i("information", "Player $playerData Enemy $enemyData")
    }
}