package com.example.roshambo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.AnticipateOvershootInterpolator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator


/**
 * @author Sam Thibodeau
 * @date 2019-03-11
 * @description Asn 2
 */
class MainActivity : AppCompatActivity() {
    var rps = Rochambo()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rps = Rochambo()

        btn_paper.setOnClickListener {
            rps.playerMakesMove(rps.PAPER)
            img_player.setImageResource(R.drawable.paper)
            process()
        }
        btn_rock.setOnClickListener {
            rps.playerMakesMove(rps.ROCK)
            img_player.setImageResource(R.drawable.rock)
            process()
        }
        btn_scissors.setOnClickListener {
            rps.playerMakesMove(rps.SCISSOR)
            img_player.setImageResource(R.drawable.scissors)
            process()
        }

    }

    fun process() {
        var gameChoice: Int = R.drawable.none
        when (rps.gameMove){
            0-> gameChoice = R.drawable.rock
            1-> gameChoice = R.drawable.paper
            2-> gameChoice = R.drawable.scissors
        }
        img_game.setImageResource(gameChoice)
        animateChoices()
        lbl_result.setText(rps.winLoseOrDraw())

    }

    private fun animateChoices() {
        val animatorPlayer = ObjectAnimator.ofFloat(img_player, "rotationX", 0f, 360f).setDuration(500)
        val animatorGame = ObjectAnimator.ofFloat(img_game, "rotationY", 0f, 360f).setDuration(500)
        val set = AnimatorSet()

        set.playTogether(animatorGame, animatorPlayer)
        set.interpolator = AnticipateOvershootInterpolator()
        set.start()
    }
}
